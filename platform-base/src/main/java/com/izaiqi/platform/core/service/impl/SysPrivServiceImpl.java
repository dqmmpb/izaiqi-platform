package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.dao.mapper.ext.SysPrivExtMapper;
import com.izaiqi.platform.core.dao.mapper.ext.SysRolePrivExtMapper;
import com.izaiqi.platform.core.dao.mapper.ext.SysUserRoleExtMapper;
import com.izaiqi.platform.core.domain.BaseService;
import com.izaiqi.platform.core.domain.model.SysPriv;
import com.izaiqi.platform.core.domain.model.SysUserRole;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.service.SysPrivService;
import com.izaiqi.platform.core.util.ListUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 系统权限
 *
 * @author deng.qiming
 * @date 2016-12-25 13:55
 */
@Service
@Transactional(readOnly = true)
public class SysPrivServiceImpl extends BaseService implements SysPrivService {

    @Resource
    private SysPrivExtMapper sysPrivExtMapper;

    @Resource
    private SysRolePrivExtMapper sysRolePrivExtMapper;

    @Resource
    private SysUserRoleExtMapper sysUserRoleExtMapper;

    @Override
    @Transactional(readOnly = false)
    public int addPriv(SysPriv sysPriv) throws BaseAppException {
        if (sysPriv == null) {
            logger.warn("sysPriv obj is null, please check!");
            ExceptionHandler.publish(ErrorCode.PRIV_IS_NULL);
        }

        // 检查编码是否重复
        boolean isExist = sysPrivExtMapper.checkPrivCode(sysPriv.getPrivCode(), null);
        if (isExist) {
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST);
        }
        if (sysPriv.getParentPrivId() == null) {
            sysPriv.setParentPrivId(0L);
        }

        // 其他信息
        Date now = new Date();
        sysPriv.setCreateTime(now);
        sysPriv.setUpdateTime(now);
        sysPriv.setState(Const.STATE_A);

        return sysPrivExtMapper.insert(sysPriv);
    }

    @Override
    @Transactional(readOnly = false)
    public int delPriv(Long privId) throws BaseAppException {
        //判断权限是否被ROLE应用，如果没有则可以删除
        boolean exist = sysRolePrivExtMapper.checkPrivExist(privId);
        if (exist) {
            ExceptionHandler.publish(ErrorCode.PRIV_HAS_ROLE_PRIV_REF);
        }

        return sysPrivExtMapper.deleteByPrimaryKey(privId);
    }

    @Override
    @Transactional(readOnly = false)
    public int updatePriv(SysPriv sysPriv) throws BaseAppException {

        if (sysPriv == null) {
            logger.warn("sysPriv obj is null, please check!");
            ExceptionHandler.publish(ErrorCode.PRIV_IS_NULL);
        }

        // 检查权限编码是否重复,排他性检查
        boolean isExist = sysPrivExtMapper.checkPrivCode(sysPriv.getPrivCode(), sysPriv.getPrivId());
        if (isExist) {
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST);
        }

        //
        sysPriv.setUpdateTime(new Date());
        // 不更新Code，CreateTime，State
        sysPriv.setPrivCode(null);
        sysPriv.setCreateTime(null);
        sysPriv.setState(null);

        return sysPrivExtMapper.updateByPrimaryKeySelective(sysPriv);
    }

    @Override
    public SysPriv getPriv(Long privId) throws BaseAppException {
        return sysPrivExtMapper.selectByPrimaryKey(privId);
    }

    @Override
    public SysPriv getPriv(String privCode) throws BaseAppException {
        Example example = new Example(SysPriv.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("privCode", privCode);
        return sysPrivExtMapper.selectOneByExample(example);
    }

    @Override
    public List<SysPriv> queryPrivs() throws BaseAppException {
        Example example = new Example(SysPriv.class);
        return sysPrivExtMapper.selectByExample(example);
    }

    @Override
    public List<SysPriv> queryPrivs(Integer pageIndex, Integer pageSize) throws BaseAppException {
        RowBounds rowBounds = new RowBounds(pageIndex, pageSize);
        Example example = new Example(SysPriv.class);
        return sysPrivExtMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleIds(List<Long> roleIds) throws BaseAppException {
        return this.queryPrivsByRoleIds(roleIds, null);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleIds(List<Long> roleIds, Integer privType) throws BaseAppException {
        return sysPrivExtMapper.queryPrivsByRoleIds(roleIds, privType);
    }


    @Override
    public List<SysPriv> queryPrivsByRoleId(Long roleId, Integer pageIndex, Integer pageSize) throws BaseAppException {
        RowBounds rowBounds = new RowBounds(pageIndex, pageSize);
        return this.queryPrivsByRoleId(roleId, null, rowBounds);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleId(Long roleId) throws BaseAppException {
        return this.queryPrivsByRoleId(roleId, null);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleId(Long roleId, Integer privType, RowBounds rowBounds) throws BaseAppException {
        List<Long> roleIds = Arrays.asList(roleId);
        return sysPrivExtMapper.queryPrivsByRoleIds(roleIds, privType, rowBounds);
    }

    @Override
    public List<SysPriv> queryPrivsByRoleId(Long roleId, Integer privType) throws BaseAppException {
        List<Long> roleIds = Arrays.asList(roleId);
        return sysPrivExtMapper.queryPrivsByRoleIds(roleIds, privType);
    }

    @Override
    public List<SysPriv> queryPrivsByUserId(Long userId) throws BaseAppException {
        return this.queryPrivsByUserId(userId, null);
    }

    @Override
    public List<SysPriv> queryPrivsByUserId(Long userId, Integer privType) throws BaseAppException {
        List<SysUserRole> userRoles = sysUserRoleExtMapper.queryUserRole(userId, null);
        if (ListUtil.isEmpty(userRoles)) {
            return new ArrayList<SysPriv>();
        }

        List<Long> roleIds = new ArrayList<>(userRoles.size());
        for (SysUserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }
        return sysPrivExtMapper.queryPrivsByRoleIds(roleIds, privType);
    }
}
