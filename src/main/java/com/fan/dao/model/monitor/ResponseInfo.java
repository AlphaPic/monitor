package com.fan.dao.model.monitor;

import com.fan.framework.annotation.VariableVo;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-25 18:13:29
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:响应信息
 * @detail:
 */
public class ResponseInfo implements Serializable {
    private static final long serialVersionUID = 4171499922480641423L;

    /**
     * 元素名称
     */
    @VariableVo(comment = "元素名称")
    private String name;

    /**
     * 元素类型
     */
    @VariableVo(comment = "元素类型")
    private String type;

    /**
     * 描述
     */
    @VariableVo(comment = "描述")
    private String description;

    /**
     * 类名
     */
    @VariableVo(comment = "类名")
    private String classname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"classname\":\"")
                .append(classname).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
