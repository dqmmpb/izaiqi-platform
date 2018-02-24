package com.izaiqi.platform.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_USER_ROLE")
public class SysUserRole implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 用户ID
     */
    @Id
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * A-可用;X-不可用
     */
    @Column(name = "STATE")
    private String state;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取角色ID
     *
     * @return ROLE_ID - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取A-可用;X-不可用
     *
     * @return STATE - A-可用;X-不可用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置A-可用;X-不可用
     *
     * @param state A-可用;X-不可用
     */
    public void setState(String state) {
        this.state = state;
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
