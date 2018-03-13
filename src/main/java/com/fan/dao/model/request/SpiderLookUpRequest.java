package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;

/**
 * @author:fanwenlong
 * @date:2018-03-08 15:24:01
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:爬虫的查询类型
 * @detail:
 */
public class SpiderLookUpRequest extends AlphaRequest{
    private static final long serialVersionUID = 9008979408961267616L;

    /**
     * 电影名称
     */
    private String name;

    /**
     * 电影导演
     */
    private String director;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 电影演员
     */
    private String actor;

    /**
     * 国籍
     */
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"director\":\"")
                .append(director).append('\"');
        sb.append(",\"year\":\"")
                .append(year).append('\"');
        sb.append(",\"month\":\"")
                .append(month).append('\"');
        sb.append(",\"actor\":\"")
                .append(actor).append('\"');
        sb.append(",\"country\":\"")
                .append(country).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
