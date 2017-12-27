package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IUserDBService;
import com.fan.dao.model.basicService.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author:fanwenlong
 * @date:2017-12-27 15:04:52
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:用户相关的数据服务
 * @detail:
 */
@Component
public class UserDBServiceImpl implements IUserDBService{
    private static final Logger logger = LoggerFactory.getLogger(UserDBServiceImpl.class);

    @Override
    public User getUser(Long userId) {
        return null;
    }
}
