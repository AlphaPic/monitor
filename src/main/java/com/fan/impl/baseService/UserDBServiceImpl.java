package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IUserDBService;
import com.fan.dao.interfaces.baseService.mapper.IUserMapper;
import com.fan.dao.model.basicService.*;
import com.fan.utils.RandomUtils;
import com.fan.utils.RegexUtils;
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
    public User getUserByUserId(Integer userId) {
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
     * 通过用户名称获取用户,用户名称也是唯一的
     * @param userName
     * @return
     */
    @Override
    public User getUserByUserName(String userName) {
        if(com.fan.utils.StringUtils.isEmptyExists(userName)){
            logger.error("用户名称不能为空");
            return null;
        }
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user = null;
        try{
            user = mapper.getUserInfoByUserName(userName);
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
                sqlSession.rollback();
                return false;
            }
            sqlSession.commit();
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
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        if(userNo == null){
            return null;
        }

        try{
            /** 只有用户号不存在才能返回新用户号，否则返回空 */
            User user = mapper.getUserInfoByUserUserNo(userNo);
            if(user != null){
                logger.error("用户号已存在");
                return null;
            }
        }catch (Exception e){
            logger.error("获取用户的编号失败");
            return null;
        }
        return userNo;
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
        if(insertUserNessaryInfo(user) == false){
            logger.error("插入必要信息失败");
            return false;
        }
        /** 走到这一步就一定要返回true，因为必要的信息已经插入成功了 */


        /** 根据用户的名称获取用户 */
        String userName = user.getUserName();
        Integer userId  = user.getUserId();
        if(userId == null){
            if(StringUtils.isEmpty(userName)) {
                logger.error("插入非必要信息的时候，userId和userName不能同时为空");
                return true;
            }else{
                User insertedUser = getUserByUserName(userName);
                if(insertedUser == null){
                    logger.error("用户名为" + userName + "的用户找不到!!!");
                    return true;
                }
                userId = insertedUser.getUserId();
            }
        }

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        try{

            Address address     = user.getAddress();
            if(address == null){
                logger.error("地址信息不能为空");
                return true;
            }
            String country      = address.getCountry();
            String province     = address.getProvince();
            String city         = address.getCity();
            String street       = address.getStreet();
            String collage      = user.getCollage();
            String company      = user.getCompany();
            String mobile       = null;
            String email        = null;
            String hobby        = user.getHobby();

            /** 如果是email，则设置为 */
            if(RegexUtils.isEmail(user.getUserName())){
                email  = user.getUserName();
                mobile = user.getMobile();
            }else if(RegexUtils.isMobile(user.getMobile())){
                email  = user.getEmail();
                mobile = user.getUserName();
            }
            if(com.fan.utils.StringUtils.isEmptyExists(country,province,city,street,collage,company,mobile,email,hobby) == true){
                logger.error("非必要信息存在为空的情况" + user);
                return true;
            }
            int res = mapper.insertUserUnnessaryInfo(userId,country,province,city,street,collage,mobile,company,email,hobby);
            if(res <= 0){
                logger.error("插入非必须信息失败");
                sqlSession.rollback();
            }
            sqlSession.commit();
        }catch (Exception e){
            /** 即使抛出异常，也算注册成功 */
            logger.error("更新用户信息失败" + e.getMessage());
            return true;
        }
        return true;
    }

    /**
     * 激活用户
     * @param status
     * @return
     */
    public Boolean activeUser(UserStatus status){
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        Integer userId = status.getUserId();
        String activeCode = status.getActiveCode();
        String registryChannel = status.getRegistryChannel();
        if(userId == null || userId <= 0 || StringUtils.isEmpty(activeCode) || StringUtils.isEmpty(registryChannel)){
            return false;
        }
        try {
            UserStatus userStatus = mapper.getUserStatus(status.getUserId());
            if(userId.equals(userStatus.getUserId()) &&
                    activeCode.equals(status.getActiveCode()) &&
                    registryChannel.equals(status.getRegistryChannel())){
                logger.error("传入的信息错误" + status);
            }
            /** 修改用户的激活状态 */
            if(mapper.activeUser(userId) <= 0){
                sqlSession.rollback();
                return false;
            }
            sqlSession.commit();
        }catch (Exception e){
            logger.error("获取激活信息出现异常" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 插入激活码信息
     * @param status
     * @return
     */
    public Boolean insertActiveCode(UserStatus status){
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        Integer userId = status.getUserId();
        String activeCode = status.getActiveCode();
        String registryChannel = status.getRegistryChannel();
        if(userId == null || userId <= 0 ||StringUtils.isEmpty(activeCode) || StringUtils.isEmpty(registryChannel)){
            return false;
        }
        try {
            if(mapper.insertUserActiveCode(userId,activeCode,registryChannel) <= 0){
                sqlSession.rollback();
                logger.error("插入用户激活信息失败");
                return false;
            }
            sqlSession.commit();
        }catch (Exception e){
            logger.error("插入激活信息出现异常" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 保存用户密码
     * @param security
     * @return
     */
    public Boolean saveUserPassword(UserSecurity security){
        return false;
    }

    /**
     * 更新密码
     * @param security
     * @return
     */
    @Override
    public Boolean updateUserPassword(UserSecurity security) {
        return null;
    }

    /**
     * 通过姓名获取密码信息
     * @param userName
     * @return
     */
    @Override
    public UserSecurity getUserPasswordInfo(String userName) {
        return null;
    }

    /**
     * 记录用户的行为
     * @return
     */
    public Boolean recordUserBehavior(UserBehavior userBehavior){
        return true;
    }
}
