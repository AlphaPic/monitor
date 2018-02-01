package com.fan.dao.model.response;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-02-01 11:36:59
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:刷新DB的响应
 * @detail:
 */
public class RefreshVODBResponse implements Serializable {
    private static final long serialVersionUID = 4000407149604220309L;

    @VariableVo(comment = "刷新类型的个数")
    private Integer refreshTypeAmount;

    @VariableVo(comment = "刷新的vo个数")
    private Long refreshVoAmount;

    public Integer getRefreshTypeAmount() {
        return refreshTypeAmount;
    }

    public void setRefreshTypeAmount(Integer refreshTypeAmount) {
        this.refreshTypeAmount = refreshTypeAmount;
    }

    public Long getRefreshVoAmount() {
        return refreshVoAmount;
    }

    public void setRefreshVoAmount(Long refreshVoAmount) {
        this.refreshVoAmount = refreshVoAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"refreshTypeAmount\":")
                .append(refreshTypeAmount);
        sb.append(",\"refreshVoAmount\":")
                .append(refreshVoAmount);
        sb.append('}');
        return sb.toString();
    }
}
