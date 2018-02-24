package com.izaiqi.platform.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_USER_SESSION")
public class SysUserSession implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 用户TOKEN
     */
    @Column(name = "TOKEN")
    private String token;

    /**
     * 用户ID
     */
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

    @Column(name = "LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    /**
     * UserAgent
     */
    @Column(name = "UA")
    private String ua;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户TOKEN
     *
     * @return TOKEN - 用户TOKEN
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户TOKEN
     *
     * @param token 用户TOKEN
     */
    public void setToken(String token) {
        this.token = token;
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

    /**
     * @return LAST_UPDATE_TIME
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取UserAgent
     *
     * @return UA - UserAgent
     */
    public String getUa() {
        return ua;
    }

    /**
     * 设置UserAgent
     *
     * @param ua UserAgent
     */
    public void setUa(String ua) {
        this.ua = ua;
    }
}
