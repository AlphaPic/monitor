package com.fan.dao.interfaces.user;

import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.CityRequest;
import com.fan.dao.model.request.ProvinceRequest;
import com.fan.dao.model.response.CityResponse;
import com.fan.dao.model.response.ProvinceResponse;

/**
 * @author:fanwenlong
 * @date:2018-01-22 10:29:05
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:地址服务
 * @detail:
 */
public interface IAddressService {

    /**
     * 通过国家获取省份
     * @param request
     * @return
     */
    public AlphaResponse<ProvinceResponse> getProvincesByCountry(ProvinceRequest request);

    /**
     * 通过省份获取城市
     * @param request
     * @return
     */
    public AlphaResponse<CityResponse> getCitiesByProvince(CityRequest request);
}
