package com.izaiqi.platform.web.result.model;

import java.util.List;

/**
 * 用户菜单模型
 *
 * @author deng.qiming
 * @date 2016-12-26 19:07
 */
public class SysMenuModel {

    private Long id;
    private String code;
    private String name;
    private Integer type;
    private String path;
    private String url;
    private String icon;
    private String description;
    private List<SysMenuModel> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysMenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuModel> children) {
        this.children = children;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
