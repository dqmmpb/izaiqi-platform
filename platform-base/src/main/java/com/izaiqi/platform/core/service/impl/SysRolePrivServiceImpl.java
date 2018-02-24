package com.izaiqi.platform.core.service.impl;

import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.dao.mapper.ext.SysRolePrivExtMapper;
import com.izaiqi.platform.core.domain.BaseService;
import com.izaiqi.platform.core.domain.model.SysRolePriv;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.service.SysRolePrivService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 系统角色权限关联
 *
 * @author deng.qiming
 * @date 2016-12-25 15:53
 */
@Service
public class SysRolePrivServiceImpl extends BaseService implements SysRolePrivService {

    @Resource
    private SysRolePrivExtMapper sysRolePrivExtMapper;

    @Override
    @Transactional
    public int addRolePriv(Long roleId, Long privId) throws BaseAppException {
        // 检查编码是否重复
        boolean exist = sysRolePrivExtMapper.checkRolePrivExist(roleId, privId);
        if (exist) {
            ExceptionHandler.publish(ErrorCode.ROLEPRIV_HAS_EXIST);
        }

        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);

        // 其他信息
        Date now = new Date();
        sysRolePriv.setCreateTime(now);
        sysRolePriv.setUpdateTime(now);
        sysRolePriv.setState(Const.STATE_A);

        return sysRolePrivExtMapper.insert(sysRolePriv);
    }

    @Override
    public int addRolePriv(List<SysRolePriv> rolePrivs) throws BaseAppException {
        //TODO 如果存在则pass；如果不存在则添加
        return 0;
    }

    @Override
    @Transactional
    public int disableRolePriv(Long roleId, Long privId) throws BaseAppException {
        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);
        sysRolePriv.setState(Const.STATE_X);
        return sysRolePrivExtMapper.updateByPrimaryKeySelective(sysRolePriv);
    }

    @Override
    @Transactional
    public int enableRolePriv(Long roleId, Long privId) throws BaseAppException {
        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);
        sysRolePriv.setState(Const.STATE_A);
        return sysRolePrivExtMapper.updateByPrimaryKeySelective(sysRolePriv);
    }

    @Override
    @Transactional
    public int delRolePriv(Long roleId, Long privId) throws BaseAppException {
        SysRolePriv sysRolePriv = new SysRolePriv();
        sysRolePriv.setRoleId(roleId);
        sysRolePriv.setPrivId(privId);
        return sysRolePrivExtMapper.deleteByPrimaryKey(sysRolePriv);
    }
}
