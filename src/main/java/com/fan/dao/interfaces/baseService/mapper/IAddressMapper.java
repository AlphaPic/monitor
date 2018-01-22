package com.fan.dao.interfaces.baseService.mapper;

import com.fan.dao.model.basicService.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author:fanwenlong
 * @date:2018-01-22 11:01:22
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:地址的映射
 * @detail:
 */
@Mapper
public interface IAddressMapper {
    /**
     * 获取地址信息
     * @param country
     * @return
     */
    @Select("SELECT * FROM ADDRESSINFO WHERE country = #{country}")
    List<Address> getProvinces(@Param("country") String country);
}
