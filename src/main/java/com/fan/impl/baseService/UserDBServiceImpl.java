package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IUserDBService;
import com.fan.dao.interfaces.baseService.mapper.IUserMapper;
import com.fan.dao.model.basicService.User;
import com.fan.utils.RandomUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

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

    @Autowired
    private SqlSession sqlSession;

    /**
     * 根据用户id去获取用户的相关信息
     * @param userId
     * @return
     */
    @Override
    public User getUser(Integer userId) {
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user = null;
        try{
            user = mapper.getUserInfoByUserId(userId);
            if(user == null){
                logger.error("用户不存在");
            }
        }catch (Exception e){
            logger.error("获取用户信息出现异常");
            return null;
        }
        return user;
    }

    /**
     * 插入用户的必传信息
     * @param user
     * @return
     */
    @Override
    public Boolean insertUserNessaryInfo(User user) {
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        try{
            String  userName    = user.getUserName();
            String  sex         = user.getSex();
            String  userNo      = user.getUserNo();
            Integer userId      = user.getUserId();
            Date    createTime  = user.getCreateTime();
            Boolean isActive    = user.getActive();
            Integer age         = user.getAge();
            Date    born        = user.getBorn();
            if(StringUtils.isEmpty(userName) ||
                    StringUtils.isEmpty(sex) ||
                    age == null ||
                    born == null){
                logger.error("存在默认不能为空的参数," +
                                                    "userName = "   + userName +
                                                    "sex = "        + sex +
                                                    "age = "        + age +
                                                    "born = "       + born);
                return false;
            }
            /** 用户id和用户编号只能获取3次，获取不到则返回 */
            Integer userIdGetCounter = 3;
            Integer userNoGetCounter = 3;
            /** userId为空则获取一个新的userId */
            while (userId == null && userIdGetCounter-- > 0){
                userId = getUserId();
            }
            /** userNo为空则获取一个新的userNo */
            while (StringUtils.isEmpty(userNo) && userNoGetCounter-- > 0){
                userNo = getUserNumber();
            }
            /** 不一定可以获取成功,获取失败则返回 */
            if(userId == null || StringUtils.isEmpty(userNo)){
                logger.error("获取userId和userNo失败");
                return false;
            }
            /** 创建时间为空，则将当前的时间作为创建时间 */
            if(createTime == null){
                createTime = new Date(System.currentTimeMillis());
            }
            /** 激活状态为空，则默认不激活 */
            if(isActive == null){
                isActive = false;
            }
            int res = mapper.insertUserNessaryInfo(userId,userName,userNo,age,sex,born,createTime,isActive == false ? 0 : 1);
            if(res <= 0){
                logger.error("插入必输信息失败");
                return false;
            }
        }catch (Exception e){
            logger.error("插入用户信息出现异常");
            return false;
        }
        return true;
    }

    /**
     * 获取用户编号
     * 用户编号为一个30位随机的16进制数
     * @return
     */
    public String getUserNumber(){
        String userNo = RandomUtils.getRandomHexNumber(30);
        if(userNo == null)
        return null;
    }

    /**
     * 获取用户id，用户id为一个一直增长的整数
     * @return
     */
    public Integer getUserId(){
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        Integer maxId = null;
        try{
            maxId = mapper.getMaxUserId();
            if(maxId == null){
                /** 跑到这里的话则是表中没有任何数据 */
                return 1;
            }else{
                maxId++;
            }
        }catch (Exception e){
            logger.error("获取数据库中userId的最大值失败");
            return null;
        }
        return maxId;
    }

    /**
     * 插入用户的非必传信息
     * @param user
     * @return
     */
    @Override
    public Boolean insertUserAllInfo(User user) {
        return null;
    }
}
