package com.fan.dao.model.basicService;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-02-01 11:05:51
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:vo的基本信息
 * @detail:
 */
public class VoInfo implements Serializable{
    private static final long serialVersionUID = -6813265886389776285L;

    @VariableVo(comment = "成员名称")
    private String memberName;

    @VariableVo(comment = "成员类型")
    private String memberType;

    @VariableVo(comment = "描述")
    private String description;

    @VariableVo(comment = "类名称")
    private String classname;

    @VariableVo(comment = "示例值")
    private String demoval;

    @VariableVo(comment = "类类型,1-普通vo，2-请求vo，3-响应vo")
    private Integer classtype;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDemoval() {
        return demoval;
    }

    public void setDemoval(String demoval) {
        this.demoval = demoval;
    }

    public Integer getClasstype() {
        return classtype;
    }

    public void setClasstype(Integer classtype) {
        this.classtype = classtype;
    }
}
