package com.fan.utils;

import com.fan.consts.InitConfig;
import com.fan.dao.model.basicService.MovieInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author:fanwenlong
 * @date:2017-12-25 21:37:10
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:数据库工具
 */
public class DBUtils {
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    /***
     * 判断是不是已经初始化
     */
    private static boolean isInit = false;

    private static Connection conn = null;    //数据库链接
    private static Statement  stmt = null;    //状态

    private static List<Long> list = null;

    /**
     * 判断用户是不是合法
     * @param userName
     * @param passwordHash
     * @return
     */
    public static Boolean userIlleagle(String userName,String passwordHash){
        return true;
    }

    /**
     * 获取用户信息
     * @return
     */
    public static Object getUserInfo(Long id){
        return null;
    }

    /**
     * 初始化
     */
    private static void init(){
        if(isInit == true){
            return;
        }

        try{
            Class.forName(InitConfig.MYSQL_DRIVER);

            conn = DriverManager.getConnection(InitConfig.MYSQL_URL_MOVIE,InitConfig.MYSQL_USERNAME,InitConfig.MYSQL_PASSWORD);

            if(conn == null){
                logger.error("数据库链接建立失败");
                return;
            }

            logger.info("数据库链接建立成功!!!");

            stmt = conn.createStatement();

            if(stmt == null){
                logger.error("建立数据库访问对象失败");
                return;
            }

            logger.info("数据库操作对象建立成功!!!");
            isInit = true;
        }catch (Exception e){
            logger.error("初始化失败");
        }
    }

    /**
     * 执行更新操作
     * @param sql
     * @return
     */
    public static boolean update(String sql){
        init();
        if(sql == null || sql.isEmpty() || stmt == null || conn == null){
            return false;
        }

        try{
            /** 由于有多个线程同时执行，因此需要加上同步块 */
            synchronized (stmt) {
                stmt.executeUpdate(sql);
            }
        }catch (Exception e){
            logger.error("执行更新失败" + e.getMessage());
            System.out.println(sql);
            return false;
        }
        return true;
    }

    /**
     * 查询单个对象
     * @return
     */
    public static List<Long> querySimpleElem(Integer cur,Integer total){
        init();

        String sql = "SELECT id FROM MOVIEINFO";

        try{
            if(list == null || list.isEmpty()){
                if(stmt == null || conn == null){
                    return null;
                }
                list = new ArrayList<Long>();
                ResultSet result = stmt.executeQuery(sql);
                if(result == null){
                    logger.info("没有查到数据:" + sql);
                    return null;
                }
                /** 把id放到List列表中去 */
                while (result.next()){
                    Long id = result.getLong(1);
                    list.add(id);
                }
                destroy();
            }
        }catch (Exception e){
            logger.info("执行查询失败" + e.getMessage());
            return null;
        }

        List<Long> newList = new ArrayList<Long>();
        for (Long id : list){
            if(id % total == cur) {
                newList.add(id);
            }
        }
        return newList;
    }

    /**
     * 通过id获取电影信息
     * @param id
     * @return
     */
    public static MovieInfo queryMovieById(Long id){
        init();
        MovieInfo info = null;
        try{
            String sql = "SELECT * FROM MOVIEINFO WHERE id = " + id;
            synchronized (stmt) {
                ResultSet resultSet = stmt.executeQuery(sql);
                while (resultSet.next()) {
                    String movieId   = resultSet.getString(1);
                    String name      = resultSet.getString(2);
                    String post      = resultSet.getString(8);
                    String detail    = resultSet.getString(11);
                    info = new MovieInfo();
                    info.setChineseTitle(name);
                    info.setPost(post);
                    info.setDetail(detail);
                    info.setId(movieId);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage() + id);
            return null;
        }
        return info;
    }

    /**
     * 结束数据库的访问
     */
    public static void destroy(){
        try {
            if (stmt != null) {
                stmt.close();
            }
            stmt = null;

            logger.info("数据库对象回收成功!!!");

            if(conn != null){
                conn.close();
            }

            stmt = null;

            logger.info("数据库链接回收成功!!!");
        }catch (Exception e){
            logger.error("关闭数据库操作对象失败" + e.getMessage());
            return;
        }
        isInit = false;
    }

    public static void main(String[] args){
        init();
        destroy();
    }
}
