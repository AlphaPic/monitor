package com.fan.impl.baseService;

import com.fan.dao.interfaces.baseService.IVoDbservice;
import com.fan.dao.interfaces.baseService.mapper.IUserMapper;
import com.fan.dao.interfaces.baseService.mapper.IVoMapper;
import com.fan.dao.model.basicService.VoInfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:fanwenlong
 * @date:2018-02-01 11:02:49
 * @E-mail:alpha18603074401@gmail.com
 * @mobile:186-0307-4401
 * @description:vo的数据库服务
 * @detail:
 */
@Component
public class VoDBServiceImpl implements IVoDbservice {
    private static final Logger logger = LoggerFactory.getLogger(VoDBServiceImpl.class);

    @Autowired
    private SqlSession sqlSession;

    /**
     * 将vo的信息插入到数据库中
     * @param voInfo
     * @return
     */
    @Override
    public Boolean InsertVoInfoToDb(VoInfo voInfo) {
        IVoMapper mapper = sqlSession.getMapper(IVoMapper.class);
        if(voInfo == null){
            logger.error("传入的vo不能为空");
            return false;
        }
        String memberName  = voInfo.getMemberName();
        String memberType  = voInfo.getMemberType();
        String classname   = voInfo.getClassname();
        String description = voInfo.getDescription();
        String demoVal     = voInfo.getDemoval();
        Integer classType  = voInfo.getClasstype();
        try{
            int res = -1;
            switch (classType){
                case 1:/** 基本类型 */
                    res = mapper.insertBasicVo(classname,memberName,memberType,description);
                    break;
                case 2:/** 请求类型 */
                    res = mapper.insertRequestVo(classname,memberName,memberType,description,demoVal);
                    break;
                case 3:/** 响应类型 */
                    res = mapper.insertResponseVo(classname,memberName,memberType,description);
                    break;
                default:
                    logger.error("不支持的插入类型" + classType);
                    return false;
            }
            if(res <= 0){
                logger.error("插入数据失败");
                sqlSession.rollback();
                return false;
            }
            sqlSession.commit();
        }catch (Exception e){
            logger.error("在插入vo信息过程中出现了异常" + e.getMessage());
            return false;
        }
        return true;
    }
}
