package com.fan.dao.model.basicService;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:fanwenlong
 * @date:2017-12-27 14:52:51
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户信息
 * @detail:
 */
public class User implements Serializable{
    private static final long serialVersionUID = 8410842405931439043L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月
     */
    private Date born;

    /**
     * 地址信息
     */
    private Address address;

    /**
     * 大学
     */
    private String collage;

    /**
     * 公司
     */
    private String company;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户的特长和爱好
     */
    private String hobby;

    /**
     * 是否激活
     */
    private Boolean isActive;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
        sb.append("\"userId\":")
                .append(userId);
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"userNo\":\"")
                .append(userNo).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"born\":\"")
                .append(born).append('\"');
        sb.append(",\"address\":")
                .append(address);
        sb.append(",\"collage\":\"")
                .append(collage).append('\"');
        sb.append(",\"company\":\"")
                .append(company).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"mobile\":\"")
                .append(mobile).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"hobby\":\"")
                .append(hobby).append('\"');
        sb.append(",\"isActive\":")
                .append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
