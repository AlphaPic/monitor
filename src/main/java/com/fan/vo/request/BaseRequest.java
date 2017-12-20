package com.fan.vo.request;

import java.io.Serializable;

/**
 * Created by fanwenlong on 2017/12/4.
 */
public class BaseRequest implements Serializable{
    private static final long serialVersionUID = -4401418619425978340L;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 是否是学生
     */
    private Boolean isStudent;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                '}';
    }
}
