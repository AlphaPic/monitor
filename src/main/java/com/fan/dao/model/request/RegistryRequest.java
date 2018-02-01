package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;
import com.fan.framework.annotation.VariableVo;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author:fanwenlong
 * @date:2018-01-04 14:59:51
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:注册请求
 * @detail:
 */
public class RegistryRequest extends AlphaRequest{
    private static final long serialVersionUID = -6514924998514459936L;
    /** 基本信息，必传 */
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @VariableVo(comment = "性别",demoVal = "male")
    private String sex;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @VariableVo(comment = "年龄",demoVal = "12")
    private Integer age;

    /**
     * 出生年月
     */
    @NotNull(message = "出生年月不能为空")
    @VariableVo(comment = "出生年月",demoVal = "1992-12-09")
    private String born;

    /** 地址信息，选传 */
    /**
     * 国家
     */
    @VariableVo(comment = "国家",demoVal = "中国")
    private String country;
    /**
     * 省份
     */
    @VariableVo(comment = "省份",demoVal = "安徽")
    private String province;
    /**
     * 城市
     */
    @VariableVo(comment = "城市",demoVal = "安庆")
    private String city;
    /**
     * 街道
     */
    @VariableVo(comment = "街道",demoVal = "猛鬼街148号")
    private String street;

    /** 与职业相关信息，选传 */
    /**
     * 大学
     */
    @VariableVo(comment = "大学",demoVal = "武汉理工")
    private String collage;
    /**
     * 公司
     */
    @VariableVo(comment = "公司",demoVal = "驴妈妈")
    private String company;

    /** 联系方式，不需传，从缓存之中获取 */
    /**
     * 手机
     */
    @VariableVo(comment = "手机",demoVal = "18603074401")
    private String mobile;

    /**
     * 邮箱
     */
    @VariableVo(comment = "邮箱",demoVal = "alpha18603074401@gmail.com")
    private String email;

    /**
     * cookie
     */
    @VariableVo(comment = "cookie",demoVal = "1872634161876347128946189")
    private String alphaCookie;

    /** 技术特长，选传 */
    @VariableVo(comment = "技术特长",demoVal = "篮球、跑步")
    private String hobby;

    public String getAlphaCookie() {
        return alphaCookie;
    }

    public void setAlphaCookie(String alphaCookie) {
        this.alphaCookie = alphaCookie;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"born\":\"")
                .append(born).append('\"');
        sb.append(",\"country\":\"")
                .append(country).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"street\":\"")
                .append(street).append('\"');
        sb.append(",\"collage\":\"")
                .append(collage).append('\"');
        sb.append(",\"company\":\"")
                .append(company).append('\"');
        sb.append(",\"mobile\":\"")
                .append(mobile).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"alphaCookie\":\"")
                .append(alphaCookie).append('\"');
        sb.append(",\"hobby\":\"")
                .append(hobby).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
