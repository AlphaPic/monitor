package com.fan.dao.model.response;

import com.fan.dao.model.monitor.ClassInfo;
import com.fan.dao.model.monitor.VoType;

import java.io.Serializable;
import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-25 19:42:46
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:类类型查找信息
 * @detail:
 */
public class ClassVoSearchResponse extends VoType implements Serializable {
    private static final long serialVersionUID = 1174807767393545436L;

    /**
     * 类的信息
     */
    private List<ClassInfo> classInfoList;

    public List<ClassInfo> getClassInfoList() {
        return classInfoList;
    }

    public void setClassInfoList(List<ClassInfo> classInfoList) {
        this.classInfoList = classInfoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"classInfoList\":")
                .append(classInfoList);
        sb.append('}');
        return sb.toString();
    }
}
