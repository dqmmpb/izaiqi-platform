package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.dao.mapper.ext.SysPrivExtMapper;
import com.izaiqi.platform.core.dao.mapper.ext.SysUserExtMapper;
import com.izaiqi.platform.core.domain.BaseService;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.service.SysPrivService;
import com.izaiqi.platform.core.service.SysUserService;
import com.izaiqi.platform.core.util.EncryptPwdUtil;
import com.izaiqi.platform.core.util.EqualsUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author deng.qiming
 * @date 2016年11月8日 上午11:14:17
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Resource
    private SysUserExtMapper sysUserExtMapper;

    @Resource
    private SysPrivExtMapper sysPrivExtMapper;

    @Autowired
    private SysPrivService sysPrivService;

    @Override
    @Transactional(readOnly = false)
    public int addUser(SysUser sysUser) throws BaseAppException {
        Assert.notNull(sysUser, "sysUser obj is null, please check!");
        Assert.notNull(sysUser.getUserName(), "sysUser userName is null, please check!");
        String userName = sysUser.getUserName();

        boolean exist = sysUserExtMapper.checkUserName(userName, null);
        if (exist) {
            ExceptionHandler.publish(ErrorCode.USER_NAME_HAS_EXIST);
        }

        String password = sysUser.getPassword();
        // 默认使用用户名作为密码;
        String pwdText = userName;
        if (StringUtils.isNotEmpty(password)) {
            // 最基本的长度要求
            if (password.length() < 6 || password.length() > 20) {
                ExceptionHandler.publish(ErrorCode.USER_PWD_LENGTH_ERROR);
            }
            // 如果用户设置了密码字段，则使用设置的密码
            pwdText = password;
        }

        EncryptPwdUtil.Encrypt encrypt = getEncryptPwd(pwdText, null);
        sysUser.setPassword(encrypt.getEncrypt());
        sysUser.setSalt(encrypt.getSalt());

        // 其他信息
        Date now = new Date();
        sysUser.setCreateTime(now);
        sysUser.setUpdateTime(now);
        sysUser.setState(Const.STATE_A);
        sysUser.setStateTime(now);

        // 设置生效时间
        if (sysUser.getUserEffTime() == null) {
            sysUser.setUserEffTime(now);
        }

        //生效时间不能在过期时间之后
        if (sysUser.getUserEffTime() != null && sysUser.getUserExpTime() != null) {
            if (sysUser.getUserEffTime().getTime() > sysUser.getUserExpTime().getTime()) {
                ExceptionHandler.publish(ErrorCode.USER_BEGIN_END_DATE_ERROR);
            }
        }

        sysUser.setForceLogin(Const.YES);
        sysUser.setIsLocked(Const.NO);
        sysUser.setLoginFail(0);

        return sysUserExtMapper.insert(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateUser(SysUser sysUser) throws BaseAppException {
        logger.debug("sysUser={}", sysUser);

        // userName不能更新，密码、Salt同样不能更新
        sysUser.setUserName(null);
        sysUser.setPassword(null);
        sysUser.setSalt(null);
        sysUser.setCreateTime(null);

        return sysUserExtMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int delUser(Long userId) throws BaseAppException {
        //注意，不能删除用户
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setState(Const.STATE_X);
        sysUser.setStateTime(new Date());

        return sysUserExtMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int lockUser(Long userId, Long adminUserId) throws BaseAppException {
        if (EqualsUtil.equals(userId, adminUserId)) {
            ExceptionHandler.publish(ErrorCode.USER_LOCK_SELF_ERROR);
        }

        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setIsLocked(Const.YES);
        sysUser.setLoginFail(0);

        return sysUserExtMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int unlockUser(Long userId) throws BaseAppException {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setIsLocked(Const.NO);

        return sysUserExtMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    @Transactional(readOnly = false)
    public int resetPwd(Long userId) throws BaseAppException {
        SysUser sysUser = sysUserExtMapper.selectByPrimaryKey(userId);

        Assert.notNull(sysUser, "sysUser obj is null, please check!");
        logger.debug("sysUser={}", sysUser);

        SysUser record = new SysUser();
        record.setUserId(sysUser.getUserId());
        EncryptPwdUtil.Encrypt encrypt = getEncryptPwd(sysUser.getUserName(), sysUser.getSalt());
        record.setPassword(encrypt.getEncrypt());
        record.setSalt(encrypt.getSalt());

        return sysUserExtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SysUser getUser(Long userId) throws BaseAppException {
        SysUser sysUser = sysUserExtMapper.selectByPrimaryKey(userId);
        // sysUser.setPassword(null); //禁止返回密码
        return sysUser;
    }

    @Override
    public SysUser getUser(String userName) throws BaseAppException {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        return sysUserExtMapper.selectOneByExample(example);
    }

    @Override
    public List<SysUser> queryUsers(Integer pageIndex, Integer pageSize) throws BaseAppException {
        RowBounds rowBounds = new RowBounds(pageIndex, pageSize);
        Example example = new Example(SysUser.class);
        return sysUserExtMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public List<SysUser> queryUsersByRoleId(Long roleId) throws BaseAppException {
        return sysUserExtMapper.getUsersByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = false)
    public int changePwd(Long userId, String oldPwdText, String newPwdText) throws BaseAppException {

        SysUser sysUser = sysUserExtMapper.selectByPrimaryKey(userId);

        Assert.notNull(sysUser, "sysUser obj is null, please check!");
        logger.debug("sysUser={}", sysUser);

        //原密码不能过于简单（不能相同）
        if (EqualsUtil.equals(oldPwdText, newPwdText)) {
            ExceptionHandler.publish(ErrorCode.USER_CHANGE_PWD_ORIGIN_AND_NEW_ARE_SAME_ERROR);
        }
        // 最基本的长度要求
        if (newPwdText.length() < 6 || newPwdText.length() > 20) {
            ExceptionHandler.publish(ErrorCode.USER_PWD_LENGTH_ERROR);
        }

        //TODO 复杂度要求

        //验证原密码
        EncryptPwdUtil.Encrypt encrypt = getEncryptPwd(oldPwdText, sysUser.getSalt());
        String encryptPwd = encrypt.getEncrypt();

        if (!EqualsUtil.equals(sysUser.getPassword(), encryptPwd)) {
            ExceptionHandler.publish(ErrorCode.USER_ORIGIN_PWD_ERROR);
        }

        //更新新密码
        EncryptPwdUtil.Encrypt newEncrypt = getEncryptPwd(newPwdText, sysUser.getSalt());
        String newEncryptPwd = newEncrypt.getEncrypt();

        SysUser newUser = new SysUser();
        newUser.setUserId(sysUser.getUserId());
        newUser.setPassword(newEncryptPwd);
        return sysUserExtMapper.updateByPrimaryKeySelective(newUser);

    }

    @Override
    public void resetLoginFail(Long userId) {

        SysUser newUser = new SysUser();
        newUser.setUserId(userId);
        newUser.setIsLocked(Const.NO);
        newUser.setLoginFail(0);
        sysUserExtMapper.updateByPrimaryKeySelective(newUser);
    }

//    @Override
//    public Boolean checkUserMenuPriv(Long userId, String path) throws BaseAppException {
//        Boolean hit = false;
//        //TODO 全局菜单（不需要权限）
//
//        // 所有菜单
//        List<SysPriv> privs = sysPrivService.queryPrivsByUserId(userId, PrivType.DATA.getValue());
//
//        if (ListUtil.isNotEmpty(privs)) {
//            for (SysPriv priv : privs) {
//                if (EqualsUtil.equals(priv.getPath(), path)) {
//                    hit = true;
//                    break;
//                }
//            }
//        }
//
//        return hit;
//    }


    @Override
    public boolean validatePwd(String password, String salt, String pwdText) throws BaseAppException {
        //验证原密码
        EncryptPwdUtil.Encrypt encrypt = getEncryptPwd(password, salt);
        String encryptPwd = encrypt.getEncrypt();

        if (!EqualsUtil.equals(pwdText, encryptPwd)) {
            ExceptionHandler.publish(ErrorCode.USER_PWD_ERROR);
        }
        return true;
    }

}
