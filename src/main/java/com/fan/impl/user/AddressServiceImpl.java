package com.fan.impl.user;

import com.fan.consts.AuthEnum;
import com.fan.consts.UserConfig;
import com.fan.dao.interfaces.baseService.IUserDBService;
import com.fan.dao.interfaces.user.IAddressService;
import com.fan.dao.model.AlphaResponse;
import com.fan.dao.model.request.CityRequest;
import com.fan.dao.model.request.ProvinceRequest;
import com.fan.dao.model.response.CityResponse;
import com.fan.dao.model.response.ProvinceResponse;
import com.fan.framework.annotation.Auth;
import com.fan.framework.annotation.MonitorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @author:fanwenlong
 * @date:2018-01-22 10:36:03
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:地址接口实现
 * @detail:
 */
@MonitorController
public class AddressServiceImpl implements IAddressService {
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private IUserDBService userDBService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 通过国家获取省份
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/com.fan.user.getProvinces/1.0.0",method = {RequestMethod.GET})
    @Auth(AuthEnum.UNCESSARY)
    @Override
    public AlphaResponse<ProvinceResponse> getProvincesByCountry(ProvinceRequest request) {
        AlphaResponse<ProvinceResponse> response = new AlphaResponse<ProvinceResponse>();
        ProvinceResponse provinceResponse = new ProvinceResponse();
        response.setDate(provinceResponse);

        String countryName = request.getCountry();
        if("请选择".equals(countryName)){
            return AlphaResponse.error("-1","传入的参数不为国家名称");
        }
        /** 不存在则直接加载数据库中的资源 */
        if(userDBService.countryExists(countryName) == false){
            if(userDBService.loadAddressFromDB(countryName) == false){
                logger.error("加载国家数据库失败");
                return AlphaResponse.error("-1","获取国籍信息失败");
            }
        }

        try {
            String countryKey = UserConfig.REDIS_ADDRESS_COUNTRY + countryName;
            Set<String> provinces = redisTemplate.opsForSet().members(countryKey);
            provinceResponse.setProvinces(provinces);
        }catch (Exception e){
            logger.error("redis操作是出现错误" + e.getMessage());
            return AlphaResponse.error("-1","获取省份失败");
        }
        return response;
    }

    /**
     * 通过省份获取城市
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/com.fan.user.getCities/1.0.0",method = {RequestMethod.GET,RequestMethod.POST})
    @Auth(AuthEnum.UNCESSARY)
    @Override
    public AlphaResponse<CityResponse> getCitiesByProvince(CityRequest request) {
        AlphaResponse<CityResponse> response = new AlphaResponse<CityResponse>();
        CityResponse cityResponse = new CityResponse();
        response.setDate(cityResponse);

        String provinceName = request.getProvince();
        String countryName  = request.getCountry();

        if("请选择".equals(provinceName)){
            return AlphaResponse.error("-1","传入的参数不为省份名称");
        }
        /** 不存在则直接加载数据库中的资源 */
        if(userDBService.countryExists(provinceName) == false){
            if(userDBService.loadAddressFromDB(countryName) == false){
                logger.error("加载国家数据库失败");
                return AlphaResponse.error("-1","获取国籍信息失败");
            }
        }

        try{
            String provinceKey = UserConfig.REDIS_ADDRESS_PROVINCE + provinceName;
            Set<String> cities = redisTemplate.opsForSet().members(provinceKey);
            cityResponse.setCities(cities);
        }catch (Exception e){
            logger.error("获取省份信息失败" + e.getMessage());
            return AlphaResponse.error("-1","获取省份信息失败");
        }
        return response;
    }
}
