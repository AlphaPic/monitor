package com.fan.impl.baseService;

import com.fan.consts.CountryEnum;
import com.fan.dao.interfaces.baseService.ISpiderThreadService;
import com.fan.dao.model.basicService.MovieInfo;
import com.fan.dao.model.request.SpiderLookUpRequest;
import com.fan.utils.DBUtils;
import com.fan.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:fanwenlong
 * @date:2018-03-08 17:48:45
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:爬虫线程服务
 * @detail:
 */
@Component
public class SpiderThread implements ISpiderThreadService{
    private static final Logger logger = LoggerFactory.getLogger(SpiderThread.class);

    private static String BASE_URL = "https://movie.douban.com/j/new_search_subjects?sort=S&range=0,10&tags=&start=";

    private static String BASE_DIR = "E:\\data\\movie\\";

    private static Random random = new Random(34);

    private static String  ftpHost = "yg45.dydytt.net"; //主机名称
    private static Integer ftpPort = 6085;              //端口
    private static String  ftpUser = "ygdy8";           //用户名
    private static String  ftpPass = "ygdy8";           //密码

    @Override
    public void addSearchElem(SpiderLookUpRequest request) {

    }

    private void scan(String url){
        try {
            List<MovieInfo> movieInfoList = new ArrayList<MovieInfo>();
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            Elements elements = document.select("div.doulist-item");
            Iterator iterator = elements.iterator();

            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();

                movieInfoList.add(parseMovieInfo(element));
            }

            saveToDB(movieInfoList);    /** 保存到数据库 */
        }catch (Exception e){
            logger.error("执行查询出现异常" + e.getMessage());
            return;
        }
    }

    /**
     * 解析为电影信息
     * @return
     */
    public MovieInfo parseMovieInfo(Element element){
        MovieInfo movieInfo = new MovieInfo();
        Elements postInfo   = element.select("div.post>a>img");                 /** 海报 */
        Elements titleInfo  = element.select("div.title>a");                    /** 标题 */
        Elements ratingInfo = element.select("div.rating>span.rating_nums");    /** 评分 */
        Elements infoInfo   = element.select("div.abstract");                   /** 详细信息 */

        String postSrc  = postInfo.attr("src");
        String title    = titleInfo.text();
        String rate     = ratingInfo.text();

        movieInfo.setPost(postSrc);     /** 海报 */
        Integer index = title.indexOf(" ");
        if(index > 0) {
            String chineseTitle = title.substring(0, index);
            String foreignTitle = title.substring(index + 1);
            movieInfo.setChineseTitle(chineseTitle);    /** 电影中文名称 */
            movieInfo.setForeignTitle(foreignTitle);    /** 电影外文名称 */
        }else {
            String chineseTitle = title.substring(0);
            movieInfo.setChineseTitle(chineseTitle);    /** 电影中文名称 */
            movieInfo.setForeignTitle("");
        }

        movieInfo.setRate(rate);        /** 评分 */

        /** 设置详细的信息 */
        String info = infoInfo.text();
        /** 过滤预处理 */
        info = info.replaceAll(" / ","/");
        info = info.replaceAll(": ","-");
        String[] dirInfos = info.split(" ");
        for(String dirInfo : dirInfos){
            String[] details = dirInfo.split("-");
            if(details.length != 2){
                continue;
            }
            if("导演".equalsIgnoreCase(details[0])){
                String[] directors = details[1].split("/");
                movieInfo.setDirector(directors);
            }else if("主演".equalsIgnoreCase(details[0])){
                String[] actors = details[1].split("/");
                movieInfo.setActors(actors);
            }else if("类型".equalsIgnoreCase(details[0])){
                String[] types = details[1].split("/");
                movieInfo.setType(types);
            }else if("制片国家/地区".equalsIgnoreCase(details[0])){
                String[] district = details[1].split("/");
                movieInfo.setCountry(district);
            }else if("年份".equalsIgnoreCase(details[0])){
                movieInfo.setYear(details[1]);
            }else {
                continue;
            }
        }

        System.out.println(movieInfo);
        return movieInfo;
    }

    /**
     * 保存到数据库
     * @param list
     */
    private void saveToDB(List<MovieInfo> list){
    }

    /**
     * 获取豆瓣最佳250部电影
     */
    private void getBest250Film(){
        for (int i = 0;i < 10;i++) {
            scan("https://www.douban.com/doulist/968362/?start=" + (25 * i));
        }
    }

    class Movie{
        List<MovieInfo> movieInfos;

        Long count;

        public void init(){
            movieInfos = new ArrayList<MovieInfo>();
            count = 0L;
        }

        /**
         * 抓取数据
         * @return
         */
        public synchronized boolean fetch(){
            try {
                if (movieInfos.isEmpty() == false) {
                    logger.info("仍然有数据，查询线程转为等待状态");
                    this.wait();
                }else {
                    Integer retryCount = 3;     //重试次数
                    for(int i = 0;i < 10;i++) {
                        String fetch = BASE_URL + count;
                        List<MovieInfo> list = fetchMovieList(fetch);
                        if((list == null || list.isEmpty())){
                            if(retryCount > 0) {
                                retryCount--;
                            }else{
                                retryCount = 3;
                                count += 20;
                            }
                            continue;
                        }
                        movieInfos.addAll(list);    //把查询到的数据全部写到列表中
                        retryCount = 3;
                        logger.info("已经查出" + count + "条数据!!!");
                        count += 20;
                    }
                    this.notify();
                }
            }catch (Exception e){
                logger.error("执行查询时出现异常" + e.getMessage() + ",目前总共查询出的数据为" + count + "条!");
                return false;
            }
            return true;
        }

        /**
         * 存放数据
         * @return
         */
        public synchronized boolean save(){
            try{
                if(movieInfos.isEmpty()){
                    logger.info("没有数据，存储线程转为等待状态");
                    this.wait();
                }else {
                    saveToMovieDB();
                    this.notify();
                    logger.info("存储数据成功");
                }
            }catch (Exception e){
                logger.error("执行存储时出现异常" + e.getMessage());
                return false;
            }
            return true;
        }

        /**
         * 抓取数据
         * @param fetchUrl
         * @return
         */
        private List<MovieInfo> fetchMovieList(String fetchUrl) throws Exception{
            List<MovieInfo> list = new ArrayList<MovieInfo>();

            URL url = new URL(fetchUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET"); //请求类型为GET
            connection.setRequestProperty("User-Agent","Chrome/64.0.3282.140");


            Integer code = connection.getResponseCode();
            if(code != 200){
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb  = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

            in.close();     //记得关闭文件


            /** 解析数据 */
            String jsonString = sb.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jn = objectMapper.readTree(jsonString);
            JsonNode data = jn.path("data");
            if(data == null || data.size() <= 0){
                return null;
            }

            for(int i = 0; i < data.size();i++){
                JsonNode elem = data.get(i);

                MovieInfo movieInfo = new MovieInfo();
                JsonNode directors  = elem.path("directors");   //导演
                JsonNode title      = elem.path("title");       //电影名称
                JsonNode casts      = elem.path("casts");       //卡司
                JsonNode cover      = elem.path("cover");       //封面url
                JsonNode detail     = elem.path("url");         //详情url
                JsonNode rate       = elem.path("rate");        //评分
                JsonNode id         = elem.path("id");          //电影id

                /** 导演 */
                String[] directorArr = new String[directors.size()];
                for(int j = 0;j < directors.size();j++){
                    directorArr[j] = directors.get(j).asText();
                }
                movieInfo.setDirector(directorArr);

                movieInfo.setChineseTitle(title.asText());  //电影名称

                /** 卡司 */
                String[] castArr = new String[casts.size()];
                for(int k = 0;k < casts.size();k++){
                    castArr[k] = casts.get(k).asText();
                }
                movieInfo.setActors(castArr);

                movieInfo.setPost(cover.asText());          //海报
                movieInfo.setDetail(detail.asText());       //详情地址
                movieInfo.setRate(rate.asText());           //评分
                movieInfo.setId(id.asText());               //电影id
                list.add(movieInfo);
            }

            return list;
        }

        /**
         * 保存数据到数据库
         */
        private void saveToMovieDB(){
            if(movieInfos == null || movieInfos.isEmpty()){
                logger.info("电影列表为空!!!");
                return;
            }
            Iterator iterator = movieInfos.iterator();
            while (iterator.hasNext()){
                MovieInfo movieInfo = (MovieInfo) iterator.next();
                String sql = createSQL(movieInfo);
                if(sql == null || sql.isEmpty()){
                    continue;
                }

                executeUpdate(sql);
            }

            movieInfos.clear();//更新完成之后记得清除list
        }

        /**
         * 执行sql的更新
         * @param sql
         */
        private void executeUpdate(String sql){
            DBUtils.update(sql);
        }

        /**
         * 电影信息
         * @param movieInfo
         * @return
         */
        private String createSQL(MovieInfo movieInfo){
            String head = "INSERT INTO MOVIEINFO VALUES(";
            String tail = ");";

            String[] directors = movieInfo.getDirector();   // 电影导演
            String[] actors    = movieInfo.getActors();     // 电影主演
            String[] country   = movieInfo.getCountry();    // 电影国家
            String[] types     = movieInfo.getType();       // 电影类型

            String content = "";

            try {
                String rate = movieInfo.getRate() == null ? "0" : movieInfo.getRate().replace(".","");
                String year = (movieInfo.getYear() == null || "null".equalsIgnoreCase(movieInfo.getYear())) ? "1900" : movieInfo.getYear();
                content =   movieInfo.getId() + "," +                                                                                       //电影id
                            translateString(movieInfo.getChineseTitle()) + "," +                                                            //电影名称(中文)
                            translateString(movieInfo.getForeignTitle()) + "," +                                                            //电影名称(英文)
                            translateString(year + "-1-1") + "," +                                                                          //日期
                            turnArrToString(movieInfo.getDirector()) + "," +                                                                //导演
                            turnArrToString(movieInfo.getActors()) + "," +                                                                  //演员
                            Integer.valueOf((rate == null || rate.isEmpty()) ? "0" : rate) + "," +                                          //评分
                            translateString(movieInfo.getPost()) + "," +                                                                    //海报
                            turnArrToString(movieInfo.getType()) + "," +                                                                    //类型
                            turnArrToString(movieInfo.getCountry()) + "," +                                                                 //国家
                            translateString(movieInfo.getDetail());                                                                         //电影详细地址
            }catch (Exception e){
                logger.info("生成sql错误" + e.getMessage());
                logger.info(movieInfo.toString());
                return null;
            }

            return head + content + tail;
        }

        /**
         * 转换字符串
         * @param str
         * @return
         */
        private String translateString(String str){
            return (str == null || str.isEmpty()) ? "'null'" : ("'" + str.replace("'",".") + "'");
        }

        /**
         * 把字符串数组合并成字符串
         * @param arr
         * @return
         */
        private String turnArrToString(String[] arr){
            if (arr == null || arr.length <= 0){
                return "'null'";
            }
            String base = "";
            for(int i = 0;i < arr.length;i++){
                base += arr[i] + " ";
            }
            return "'" + base.substring(0,base.length() - 1).replace("'",".") + "'";
        }
    }

    /**
     * 获取所有的电影
     * 方式如下
     * 1、开启一个抓取线程，每次抓取20条数据，每抓10次后，暂停1~3秒,知道抓取完成
     * 2、开启一个存储线程，把抓取的到的数据存进数据库
     */
    public void fetchAllFilm(){

        final Movie movie = new Movie();
        movie.init();
        try {
            Thread fetchThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (movie.fetch() == false) {
                                logger.error("查询完成");
                                break;
                            }
                            Thread.sleep((random.nextInt(30) * 200));
                        }
                    }catch (Exception e){
                        logger.error("执行抓取出现异常" + e.getMessage());
                        return;
                    }
                }
            },"fetch-" + 1);

            Thread saveThread  = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (movie.save() == false) {
                                logger.error("存储完成");
                                break;
                            }
//                            Thread.sleep((random.nextInt(30) * 200));
                        }

                    }catch (Exception e){
                        logger.error("执行存储时出现异常" + e.getMessage());
                        return;
                    }
                }
            });

            saveThread.start();
            fetchThread.start();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 下载海报，并将海报存放到文件夹中去
     */
    public void downloadPost(){
        Integer threadNum = 2;
        List<Long>[] idList = new List[threadNum];
        for (int i = 0;i < threadNum;i++){
            idList[i] = DBUtils.querySimpleElem(i,threadNum);//查询id的列表
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for(int j = 0;j < threadNum;j++) {
            executorService.submit(new DownloadThread("thread-" + j,idList[j]));
        }
    }

    /**
     * 下载线程类
     */
    class DownloadThread implements Runnable{
        private String threadName;

        private List<Long> searchList;

        public DownloadThread(String name,List<Long> idList){
            this.threadName = name;
            this.searchList = idList;
        }

        public String getThreadName(){
            return threadName;
        }

        /**
         * 下载图片
         * @param info
         * @return
         */
        private boolean downLoadPic(MovieInfo info){
            if(info == null || info.getPost() == null || info.getPost().isEmpty()){
                return false;
            }

            try{
                URL url = new URL(info.getPost());
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");     //GET方式
                connection.setConnectTimeout(5000);     //超时时间5s
                InputStream stream = connection.getInputStream();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = stream.read(buffer)) != -1){
                    outputStream.write(buffer,0,len);
                }
                stream.close();
                /** 把数据放到byte数组中去 */
                byte[] data = outputStream.toByteArray();
                outputStream.close();
                File file = new File(BASE_DIR + info.getChineseTitle() + ".jpg");
                FileOutputStream fops = new FileOutputStream(file);
                fops.write(data);
                fops.flush();
                fops.close();
            }catch (Exception e){
                logger.error(e.getMessage());
                return false;
            }
            return true;
        }

        /**
         * 更新电影信息
         * @return
         */
        private boolean downLoadMovieInfo(MovieInfo info){
            if(info == null || info.getDetail() == null || info.getDetail().isEmpty()){
                return false;
            }
            String detail = info.getDetail();
            Connection connection =  Jsoup.connect(detail);
            try {
                Document doc = connection.get();
                Elements types      = doc.select("span[property='v:genre']");
                Elements year       = doc.select("h1>span.year");
                Elements country    = doc.select("div#info");

                String typeString = "";
                /** 类型 */
                if(types != null && types.size() > 0){
                    for (Element element : types){
                        String value = element.text();
                        typeString += value + " ";
                    }
                }

                String yearString = "";
                /** 年份 */
                if(year != null && year.size() == 1){
                    Element value = year.get(0);
                    yearString = value.text();
                }
                yearString = yearString.replace("(","").replace(")","");    //替换掉不需要的字符

                String countryString = "";
                /** 国家 */
                if(country != null && country.size() == 1){
                    Element element = country.first();
                    List<TextNode> nodes = element.textNodes();
                    Iterator iterator = nodes.iterator();
                    while (iterator.hasNext()){
                        TextNode node = (TextNode) iterator.next();
                        if(CountryEnum.isCountryName(node.text())){
                            countryString += node.text();
                            break;
                        }
                    }
                }
                typeString      = (typeString == null || typeString.isEmpty()) ? "null" : typeString;
                yearString      = (yearString == null || yearString.isEmpty()) ? "null" : yearString;
                countryString   = (countryString == null || countryString.isEmpty()) ? "null" : countryString;
                String updateSQL = "UPDATE MOVIEINFO SET type = '" + typeString.trim() + "',year = '" + yearString.trim() + "-01-01',country = '" + countryString.trim() + "' where id = " + info.getId() + ";";
                DBUtils.update(updateSQL);
            }catch (Exception e){
                logger.error(e.getMessage());
                return false;
            }
            return true;
        }

        @Override
        public void run() {
            Iterator iterator = searchList.iterator();
            while (iterator.hasNext()) {
                Long searchId = (Long) iterator.next();
                MovieInfo info = DBUtils.queryMovieById(searchId);
                if(info == null || downLoadMovieInfo(info) == false){
                    logger.error("下载失败:",info.getPost());
                    sleepForFewSecs(random.nextInt(10));
                    continue;
                }
                logger.info("写入成功:[" + info + "]");
                sleepForFewSecs(random.nextInt(10));
            }
            logger.info("下载线程" + getThreadName() + "已经结束");
        }

        /**
         * 为了防止ip被封，休眠一段时间
         * @param secs
         */
        private void sleepForFewSecs(Integer secs){
            try{
                if(secs < 2){
                    secs = 2;
                }
                Thread.sleep(1000 * secs);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 下载ftp协议定义文件
     * 这个有点难度，目前暂不实现
     * @param url
     */
    public void downLoadFTPMovie(String[] url) {
        FTPClient client = new FTPClient();
        try {
            client.connect(ftpHost,ftpPort);    //链接
            boolean code = client.login(ftpUser,ftpPass);      //登录
            if(code == false){
                logger.error("登录失败");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载磁力链接
     */
    public void downLoadMagnetFile(){

    }

    public static void main(String[] args){
        SpiderThread spiderThread = new SpiderThread();
//        spiderThread.getBest250Film();
//        spiderThread.downloadPost();
//        spiderThread.downLoadFTPMovie(new String[]{""});
        spiderThread.downloadPost();
        spiderThread.downLoadMagnetFile();
    }
}
































