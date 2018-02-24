package com.izaiqi.platform.core.dao.mapper.ext;

import com.izaiqi.platform.core.domain.model.SysUserRole;
import com.izaiqi.platform.core.exception.BaseAppException;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserRoleExtMapper extends Mapper<SysUserRole> {

    /**
     * 判断role是否被引用
     *
     * @param roleId
     * @return
     */
    Boolean checkRoleExist(Long roleId);

    /**
     * 判断用户角色是否存在
     *
     * @param userId
     * @param roleId
     * @return
     */
    Boolean checkUserRoleExist(@Param("userId") Long userId, @Param("roleId") Long roleId);


    /**
     * 查询用户的角色
     *
     * @param userId
     * @return
     * @throws BaseAppException
     */
    List<SysUserRole> queryUserRole(@Param("userId") Long userId, @Param("state") Integer state);
}
