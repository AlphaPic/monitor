package com.fan.dao.model.basicService;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author:fanwenlong
 * @date:2018-03-09 13:14:49
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:电影信息
 * @detail:
 */
public class MovieInfo implements Serializable{
    private static final long serialVersionUID = 6232908237298346467L;

    /**
     * 电影id
     */
    private String id;

    /**
     * 电影名称(中文)
     */
    private String chineseTitle;

    /**
     * 电影名称(英文)
     */
    private String foreignTitle;

    /**
     * 封面
     */
    private String post;

    /**
     * 评分
     */
    private String rate;

    /**
     * 导演
     */
    private String[] director;

    /**
     * 演员
     */
    private String[] actors;

    /**
     * 电影类型
     */
    private String[] type;

    /**
     * 国家
     */
    private String[] country;

    /**
     * 年份
     */
    private String year;

    /**
     * 详情
     */
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChineseTitle() {
        return chineseTitle;
    }

    public void setChineseTitle(String chineseTitle) {
        this.chineseTitle = chineseTitle;
    }

    public String getForeignTitle() {
        return foreignTitle;
    }

    public void setForeignTitle(String foreignTitle) {
        this.foreignTitle = foreignTitle;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String[] getDirector() {
        return director;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"chineseTitle\":\"")
                .append(chineseTitle).append('\"');
        sb.append(",\"foreignTitle\":\"")
                .append(foreignTitle).append('\"');
        sb.append(",\"post\":\"")
                .append(post).append('\"');
        sb.append(",\"rate\":\"")
                .append(rate).append('\"');
        sb.append(",\"director\":")
                .append(Arrays.toString(director));
        sb.append(",\"actors\":")
                .append(Arrays.toString(actors));
        sb.append(",\"type\":")
                .append(Arrays.toString(type));
        sb.append(",\"country\":")
                .append(Arrays.toString(country));
        sb.append(",\"year\":\"")
                .append(year).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
