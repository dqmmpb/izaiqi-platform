package com.izaiqi.platform.core.dao.mapper.ext;

import com.izaiqi.platform.core.domain.model.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserExtMapper extends Mapper<SysUser> {

    /**
     * 检查用户是否存在
     *
     * @param userName
     * @param userId
     * @return
     */
    Boolean checkUserName(@Param("userName") String userName, @Param("userId") Long userId);

    /**
     * 通过roleId查询用户
     *
     * @param roleId
     * @return
     */
    List<SysUser> getUsersByRoleId(Long roleId);

}
