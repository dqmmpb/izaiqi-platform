package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.dao.mapper.ext.SysUserSessionExtMapper;
import com.izaiqi.platform.core.domain.BaseService;
import com.izaiqi.platform.core.domain.model.SysUserSession;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.SysUserSessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author deng.qiming
 * @date 2016年11月8日 上午11:14:42
 */
@Service
@Transactional(readOnly = true)
public class SysUserSessionServiceImpl extends BaseService implements SysUserSessionService {

    @Resource
    private SysUserSessionExtMapper sysUserSessionExtMapper;

    @Override
    @Transactional(readOnly = false)
    public SysUserSession addSession(Long userId, String userCode, String ua, String token) throws BaseAppException {
        logger.debug("userId={},userCode={},ua={},token={}", userId, userCode, ua, token);
        SysUserSession session = new SysUserSession();

        Date now = new Date();

        session.setUserId(userId);
        session.setToken(token);
        session.setCreateTime(now);
        session.setUpdateTime(now);
        session.setLastUpdateTime(now);
        session.setState(Const.STATE_A);
        session.setUa(ua);

        sysUserSessionExtMapper.insert(session);

//        String key = CacheUtil.getKey(Const.COMMON_SESSION_PREFIX, session.getToken());
//        CacheUtil.setStrObj(redisTemplate, key, session);

        return session;
    }

    @Override
    public SysUserSession getSession(Long userId, String token) throws BaseAppException {
        Example example = new Example(SysUserSession.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("token", token);
        return sysUserSessionExtMapper.selectOneByExample(example);
    }

    @Override
    public boolean validate(String token) throws BaseAppException {
//        String key = CacheUtil.getKey(Const.COMMON_SESSION_PREFIX, token);
//        SysUserSession userSession = CacheUtil.getStrObj(redisTemplate, key, SysUserSession.class);
//
//        if (userSession == null) {
//            userSession = sysUserSessionDao.query(token);
//            if (userSession != null) {
//                // DB中有，缓存中没有
//                Date now = new Date();
//                long delta = DateUtils.differDate(now, userSession.getLastUpdateDate(), DateUtils.DIFFER_IN_MINUTE);
//                if (delta < SysUserSessionDao.DEFAULT_SESSION_TIME) {
//                    // session is valid
//                    // update TIME
//                    userSession.setLastUpdateDate(now);
//                    if (delta > SysUserSessionDao.DEFAULT_SESSION_UPDATE_INTERVAL) {
//                        sysUserSessionDao.updateToken(userSession);
//                    }
//                    CacheUtil.setStrObj(redisTemplate, key, userSession);
//
//                    return true;
//                }
//                return false;
//            }
//            return false;
//        }
//
//        Date now = new Date();
//        long delta = DateUtils.differDate(now, userSession.getLastUpdateDate(), DateUtils.DIFFER_IN_MINUTE);
//
//        // session is valid
//        if (delta < SysUserSessionDao.DEFAULT_SESSION_TIME) {
//            // update TIME
//            userSession.setLastUpdateDate(now);
//            if (delta > SysUserSessionDao.DEFAULT_SESSION_UPDATE_INTERVAL) {
//                sysUserSessionDao.updateToken(userSession);
//            }
//            CacheUtil.setStrObj(redisTemplate, key, userSession);
//
//            return true;
//        }

        return true;
    }

    @Override
    public boolean disableToken(String token) throws BaseAppException {
        return false;
    }

//    //TODO
//    private Boolean checkSessionTimeout(SysUserSession sysUserSession, long delta, String key) throws BaseAppException {
//        Date now = new Date();
//        // session is valid
//        if (delta < SysUserSessionDao.DEFAULT_SESSION_TIME) {
//            // update TIME
//            sysUserSession.setLastUpdateDate(now);
//            if (delta > SysUserSessionDao.DEFAULT_SESSION_UPDATE_INTERVAL) {
//                sysUserSessionDao.updateToken(sysUserSession);
//            }
//            CacheUtil.setStrObj(redisTemplate, key, sysUserSession);
//
//            return true;
//        }
//
//        return true;
//    }

}
