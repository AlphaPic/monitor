package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;

/**
 * @author:fanwenlong
 * @date:2018-01-22 10:34:34
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:城市请求
 * @detail:
 */
public class CityRequest extends AlphaRequest{
    private static final long serialVersionUID = 467134136918854501L;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"country\":\"")
                .append(country).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
