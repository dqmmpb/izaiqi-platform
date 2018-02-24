package com.izaiqi.platform.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_PRIV")
public class SysPriv implements Serializable {
    @Id
    @Column(name = "PRIV_ID")
    private Long privId;

    /**
     * 父节点ID
     */
    @Column(name = "PARENT_PRIV_ID")
    private Long parentPrivId;

    /**
     * 权限CODE
     */
    @Column(name = "PRIV_CODE")
    private String privCode;

    /**
     * 权限名称
     */
    @Column(name = "PRIV_NAME")
    private String privName;

    /**
     * 0-目录;1-菜单;2-数据
     */
    @Column(name = "TYPE")
    private Integer type;

    /**
     * 菜单URL路径
     */
    @Column(name = "URL")
    private String url;

    /**
     * 菜单PATH路径
     */
    @Column(name = "PATH")
    private String path;

    /**
     * 权限描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * A-可用;X:不可用
     */
    @Column(name = "STATE")
    private String state;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return PRIV_ID
     */
    public Long getPrivId() {
        return privId;
    }

    /**
     * @param privId
     */
    public void setPrivId(Long privId) {
        this.privId = privId;
    }

    /**
     * 获取父节点ID
     *
     * @return PARENT_PRIV_ID - 父节点ID
     */
    public Long getParentPrivId() {
        return parentPrivId;
    }

    /**
     * 设置父节点ID
     *
     * @param parentPrivId 父节点ID
     */
    public void setParentPrivId(Long parentPrivId) {
        this.parentPrivId = parentPrivId;
    }

    /**
     * 获取权限CODE
     *
     * @return PRIV_CODE - 权限CODE
     */
    public String getPrivCode() {
        return privCode;
    }

    /**
     * 设置权限CODE
     *
     * @param privCode 权限CODE
     */
    public void setPrivCode(String privCode) {
        this.privCode = privCode;
    }

    /**
     * 获取权限名称
     *
     * @return PRIV_NAME - 权限名称
     */
    public String getPrivName() {
        return privName;
    }

    /**
     * 设置权限名称
     *
     * @param privName 权限名称
     */
    public void setPrivName(String privName) {
        this.privName = privName;
    }

    /**
     * 获取0-目录;1-菜单;2-数据
     *
     * @return TYPE - 0-目录;1-菜单;2-数据
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0-目录;1-菜单;2-数据
     *
     * @param type 0-目录;1-菜单;2-数据
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取菜单URL路径
     *
     * @return URL - 菜单URL路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单URL路径
     *
     * @param url 菜单URL路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取菜单PATH路径
     *
     * @return PATH - 菜单PATH路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置菜单PATH路径
     *
     * @param path 菜单PATH路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取权限描述
     *
     * @return DESCRIPTION - 权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限描述
     *
     * @param description 权限描述
     */
    public void setDescription(String description) {
        this.description = description;
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
