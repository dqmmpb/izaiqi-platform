package com.izaiqi.platform.core.dao.mapper.ext;

import com.izaiqi.platform.core.domain.model.SysRolePriv;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SysRolePrivExtMapper extends Mapper<SysRolePriv> {
    /**
     * 判断Role是否被引用
     *
     * @param roleId
     * @return
     */
    Boolean checkRoleExist(Long roleId);

    /**
     * 判断Priv是否被引用
     *
     * @param privId
     * @return
     */
    Boolean checkPrivExist(Long privId);

    /**
     * 判断角色权限是否存在
     *
     * @param roleId
     * @param privId
     * @return
     */
    Boolean checkRolePrivExist(@Param("roleId") Long roleId, @Param("privId") Long privId);

}
