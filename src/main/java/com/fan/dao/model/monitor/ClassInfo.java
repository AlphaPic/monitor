package com.fan.dao.model.monitor;

import java.io.Serializable;

/**
 * @author:fanwenlong
 * @date:2018-01-25 19:34:26
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:类型信息
 * @detail:
 */
public class ClassInfo implements Serializable{
    private static final long serialVersionUID = 2082034606027797497L;

    /**
     * 元素名称
     */
    private String name;

    /**
     * 元素类型
     */
    private String type;

    /**
     * 元素描述
     */
    private String description;

    /**
     * 元素所在类类名
     */
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
