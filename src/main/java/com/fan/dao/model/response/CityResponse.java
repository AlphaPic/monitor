package com.fan.dao.model.response;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author:fanwenlong
 * @date:2018-01-22 10:34:01
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:城市请求
 * @detail:
 */
public class CityResponse implements Serializable{
    private static final long serialVersionUID = 5855758597494422257L;

    /**
     * 城市
     */
    @VariableVo(comment = "城市")
    private Set<String> cities;

    public Set<String> getCities() {
        return cities;
    }

    public void setCities(Set<String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"cities\":")
                .append(cities);
        sb.append('}');
        return sb.toString();
    }
}
