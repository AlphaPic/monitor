package com.fan.utils;

import com.fan.dao.model.basicService.Address;
import com.fan.framework.annotation.VariableVo;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-29 15:58:54
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:扫描请求类vo的工具类
 * @detail:
 */
public class ScanClassVoUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScanClassVoUtils.class);

    private static String [] BaseType = {"basicService","monitor"};

    private static String [] requestType = {"request"};

    private static String [] responseType = {"response"};

    /**
     * 把vo的信息全部扫描出来,并且弄成特定的格式
     * @param type (1-基本的vo，2-请求vo，3-响应vo)
     * @param local 是本地还是在线
     */
    public static List<InsertVo> scanAllVo(Integer type,Boolean local){
        String baseDir = "E:\\data\\git\\monitor\\target\\classes\\com\\fan\\dao\\model\\";
        String basePkg = "com.fan.dao.model.";


        String[] scanDir = null;
        List<InsertVo> sqlList = new ArrayList<InsertVo>();
        switch (type){
            case 1:/** 基本的vo */
                if(BaseType == null || BaseType.length <= 0){
                    logger.error("基本的类型文件夹不存在");
                    return null;
                }
                scanDir = BaseType;
                break;
            case 2:/** 请求的vo */
                if(requestType == null || requestType.length <= 0){
                    logger.error("请求类型文件夹不存在");
                    return null;
                }
                scanDir = requestType;
                break;
            case 3:/** 响应的vo */
                if(responseType == null || responseType.length <= 0){
                    logger.error("响应类型文件夹不存在");
                    return null;
                }
                scanDir = responseType;
                break;
            default:
                break;
        }
        /** 扫描基础类包 */
        for(int i = 0;i < scanDir.length;i++){
            if(local == true) {
                String targetDir = baseDir + scanDir[i];
                File folder = new File(targetDir);
                if (folder.isDirectory() == false) {
                    logger.error("目录不存在" + targetDir);
                    continue;
                }
                /** 扫描文件中包含的类 */
                File[] file = folder.listFiles();
                if (file == null || file.length <= 0) {
                    continue;
                }

                for (int k = 0; k < file.length; k++) {
                    InsertVo insertVo = parseFileByFolder(file[k].getPath(), type);
                    if (insertVo != null) {
                        sqlList.add(insertVo);
                    }
                }
            }else{
                String targetPkg = basePkg + scanDir[i];
            }
        }

        return sqlList;
    }

    /**
     * 生成sql进行返回
     * @param insertVo
     */
    private static List<String> generateSQL(InsertVo insertVo){
        if(insertVo == null || insertVo.classType > 3 || insertVo.classType < 1){
            return null;
        }
        List<String> list = new ArrayList<String>();
        String head = null;


        String className = insertVo.className;
        List<TypeVo> sqlList = insertVo.list;
        if(sqlList == null || sqlList.isEmpty()){
            return list;
        }
        Iterator iterator = sqlList.iterator();
        while (iterator.hasNext()){
            TypeVo typeVo = (TypeVo) iterator.next();
            switch (insertVo.classType){
                case 1:/** 存放到普通的表 */
                    head = "insert into classinfo values('" + typeVo.name + "','" + typeVo.type + "','" + typeVo.comment + "','" + className + "');";
                    break;
                case 2:/** 放到请求表 */
                    head = "insert into requestvoinfo values('" + typeVo.name + "','" + typeVo.type + "','" + typeVo.comment + "','" + className + "','" + typeVo.demoVal +"');";
                    break;
                case 3:/** 放到响应表 */
                    head = "insert into responsevoinfo values('" + typeVo.name + "','" + typeVo.type + "','" + typeVo.comment + "','" + className + "');";
                    break;
                default:
                    break;
            }
            list.add(head);
            head = null;
        }

        return list;
    }

    public static class TypeVo{
        /**
         * 成员名称
         */
        String name;

        /**
         * 类型
         */
        String type;

        /**
         * 示例值
         */
        String demoVal;

        /**
         * 注释
         */
        String comment;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDemoVal() {
            return demoVal;
        }

        public void setDemoVal(String demoVal) {
            this.demoVal = demoVal;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    public static class InsertVo{
        /**
         * 类类型
         */
        Integer classType;
        /**
         * 类名称
         */
        String className;

        List<TypeVo> list;

        public Integer getClassType() {
            return classType;
        }

        public void setClassType(Integer classType) {
            this.classType = classType;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<TypeVo> getList() {
            return list;
        }

        public void setList(List<TypeVo> list) {
            this.list = list;
        }
    }

    /**
     * 解析文件
     */
    private static InsertVo parseFileByFolder(String file,Integer voType){
        if(StringUtils.isEmpty(file) == true){
            return null;
        }
        /** 预处理 */
        /** 因为是目录形式的文件，因此需要把后缀和前缀去掉，另外把\变成. */
        String pre1 = file.split("\\.")[0];
        String pre2 = pre1 == null ? "" : pre1.split("classes\\\\")[1];
        if(pre2 == null){
            return null;
        }
        pre2 = pre2.replaceAll("\\\\",".");



        return getInfoFromClass(pre2,voType);
    }

    /**
     * 从包中获取
     * @param classWholeName
     * @return
     */
    private static InsertVo getInfoFromClass(String classWholeName,Integer voType){
        InsertVo insertVo = new InsertVo();
        insertVo.classType = voType;
        /** 获取类中的关键信息 */
        try {
            Class clazz = Class.forName(classWholeName);
            String className = clazz.getName();

            insertVo.className = className.substring(className.lastIndexOf(".") + 1);
            Field[] fields = clazz.getDeclaredFields();
            if(fields == null || fields.length <= 0){
                return null;
            }
            List<TypeVo> list = new ArrayList<TypeVo>();
            insertVo.list = list;
            for(Field field : fields){
                /** 排除序列号 */
                if(field.getName().equals("serialVersionUID")){
                    continue;
                }
                String member  = field.getName();
                String type    = field.getType().getName();
                String comment = "";
                String demoval = "";
                /** 对类进行包装 */
                VariableVo annotation = field.getAnnotation(VariableVo.class);
                if(annotation != null){
                    comment = annotation.comment();
                    demoval = annotation.demoVal();
                }
                TypeVo typeVo = new TypeVo();
                typeVo.comment = comment;
                typeVo.name    = member;
                typeVo.type    = type.substring(type.lastIndexOf(".") + 1);
                typeVo.demoVal = demoval;
                list.add(typeVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return insertVo;
    }

    public static void main(String[] args){
        scanAllVo(1,true);
    }
}
