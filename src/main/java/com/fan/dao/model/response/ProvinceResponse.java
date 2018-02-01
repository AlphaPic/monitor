package com.fan.dao.model.response;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author:fanwenlong
 * @date:2018-01-22 10:32:17
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:省份响应
 * @detail:
 */
public class ProvinceResponse implements Serializable{
    private static final long serialVersionUID = 2727855110886781855L;

    /**
     * 省份
     */
    @VariableVo(comment = "省份")
    private Set<String> provinces;

    public Set<String> getProvinces() {
        return provinces;
    }

    public void setProvinces(Set<String> provinces) {
        this.provinces = provinces;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"provinces\":")
                .append(provinces);
        sb.append('}');
        return sb.toString();
    }
}
