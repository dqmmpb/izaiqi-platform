package com.izaiqi.platform.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_ROLE")
public class SysRole implements Serializable {
    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 角色CODE
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * A-可用;X:不可用
     */
    @Column(name = "STATE")
    private String state;

    /**
     * 角色描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return ROLE_ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色CODE
     *
     * @return ROLE_CODE - 角色CODE
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色CODE
     *
     * @param roleCode 角色CODE
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取A-可用;X:不可用
     *
     * @return STATE - A-可用;X:不可用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置A-可用;X:不可用
     *
     * @param state A-可用;X:不可用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取角色描述
     *
     * @return DESCRIPTION - 角色描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置角色描述
     *
     * @param description 角色描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
