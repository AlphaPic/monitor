package com.fan.dao.model.request;

import com.fan.dao.model.AlphaRequest;
import com.fan.framework.annotation.VariableVo;

/**
 * @author:fanwenlong
 * @date:2018-02-01 11:40:54
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:vo刷新请求
 * @detail:
 */
public class RefreshVODBRequest extends AlphaRequest {
    private static final long serialVersionUID = 2209283241625080807L;

    @VariableVo(comment = "是否强制刷新",demoVal = "true")
    private Boolean forceRefresh;

    @VariableVo(comment = "vo类型,1-普通的vo，2-请求vo，3-响应vo,4-扫描全部",demoVal = "1")
    private Integer voType;

    public Boolean getForceRefresh() {
        return forceRefresh;
    }

    public void setForceRefresh(Boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
    }

    public Integer getVoType() {
        return voType;
    }

    public void setVoType(Integer voType) {
        this.voType = voType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"forceRefresh\":")
                .append(forceRefresh);
        sb.append(",\"voType\":")
                .append(voType);
        sb.append('}');
        return sb.toString();
    }
}
