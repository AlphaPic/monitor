package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;
import com.fan.framework.annotation.VariableVo;

/**
 * @author:fanwenlong
 * @date:2018-01-22 10:33:22
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:省份请求
 * @detail:
 */
public class ProvinceRequest extends AlphaRequest{
    private static final long serialVersionUID = 4719221817608677995L;

    /**
     * 国家
     */
    @VariableVo(comment = "国家",demoVal = "中国")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"country\":\"")
                .append(country).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
