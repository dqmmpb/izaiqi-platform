package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.izaiqi.platform.core.annotation.RequiresPermissions;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.domain.PageParam;
import com.izaiqi.platform.core.domain.model.SysRole;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @authordeng.qiming
 * @date 2016-12-26 9:39
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;


    /**
     * 新增Role
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:add")
    public BaseResult addRole(@RequestBody BaseParam param) throws BaseAppException {

        logger.debug("add role begin");

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysRole sysRole = JSONObject.parseObject(paramsJSONString, SysRole.class);

        BaseResult result = new BaseResult();
        sysRoleService.addRole(sysRole.getRoleCode(), sysRole.getRoleName(), sysRole.getDescription());

        return result;
    }

    /**
     * 更新Role
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:edit")
    public BaseResult editRole(@RequestBody BaseParam param) throws BaseAppException {

        logger.debug("edit role begin");

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysRole sysRole = JSONObject.parseObject(paramsJSONString, SysRole.class);

        BaseResult result = new BaseResult();
        sysRoleService.updateRole(sysRole);

        return result;
    }

    /**
     * 删除角色
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:delete")
    public BaseResult delRole(@RequestBody BaseParam param) throws BaseAppException {

        logger.debug("delete role begin");

        Map params = parseParams(param);
        Long roleId = ((Number) params.get("roleId")).longValue();

        BaseResult result = new BaseResult();
        sysRoleService.delRole(roleId);

        return result;
    }

    /**
     * 查询Role
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public BaseResult getRole(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long roleId = ((Number) params.get("roleId")).longValue();

        BaseResult result = new BaseResult();
        SysRole sysRole = sysRoleService.getRole(roleId);
        result.setResult(sysRole);

        return result;
    }


    /**
     * 通过userId查询用户角色列表
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "userrole", method = RequestMethod.POST)
    public BaseResult getRolesByUserId(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        BaseResult result = new BaseResult();
        result.setResult(sysRoleService.queryRolesByUserId(userId));

        return result;
    }

    /**
     * 查询角色列表
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "roles", method = RequestMethod.POST)
    public BaseResult getRoles(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        PageParam pageParam = parsePage(params);

        BaseResult result = new BaseResult();
        List<SysRole> sysRoleList = sysRoleService.queryRoles(pageParam.getPageIndex(), pageParam.getPageSize());
        PageInfo page = new PageInfo(sysRoleList);
        result.setResult(page);

        return result;
    }

}
