package com.izaiqi.platform.core.dao.mapper.ext;

import com.izaiqi.platform.core.domain.model.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleExtMapper extends Mapper<SysRole> {


    Boolean checkRoleCode(@Param("roleCode") String roleCode, @Param("roleId") Long roleId);

    List<SysRole> queryRolesByUserId(Long userId);
}
