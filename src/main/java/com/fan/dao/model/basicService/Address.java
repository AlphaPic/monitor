package com.fan.dao.model.basicService;

import com.fan.framework.annotation.ClassVo;
import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-05 15:04:11
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:地址信息
 * @detail:
 */
@ClassVo(name = "BASIC")
public class Address implements Serializable{
    private static final long serialVersionUID = 5697851584102576446L;

    /**
     * 国家
     */
    @VariableVo(comment = "国家")
    private String country;

    /**
     * 省份
     */
    @VariableVo(comment = "省份")
    private String province;

    /**
     * 城市
     */
    @VariableVo(comment = "城市")
    private String city;

    /**
     * 邮政编号
     */
    @VariableVo(comment = "邮政编号")
    private String postCode;

    /**
     * 街道
     */
    @VariableVo(comment = "街道")
    private String street;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"country\":\"")
                .append(country).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"postCode\":\"")
                .append(postCode).append('\"');
        sb.append(",\"street\":\"")
                .append(street).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
