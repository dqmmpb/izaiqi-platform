package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.domain.model.SysUserRole;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.annotation.RequiresPermissions;
import com.izaiqi.platform.core.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户角色
 *
 * @author deng.qiming
 * @date 2017-01-05 13:10
 */
@RestController
@RequestMapping("/sys/userrole")
public class SysUserRoleController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 绑定用户和角色
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:allocate")
    public BaseResult addUserRole(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysUserRole sysUserRole = JSONObject.parseObject(paramsJSONString, SysUserRole.class);

        BaseResult result = new BaseResult();
        sysUserRoleService.addUserRole(sysUserRole.getUserId(), sysUserRole.getRoleId());

        return result;
    }

    /**
     * 删除用户角色权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:allocate")
    public BaseResult delUserRole(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysUserRole sysUserRole = JSONObject.parseObject(paramsJSONString, SysUserRole.class);

        BaseResult result = new BaseResult();
        sysUserRoleService.delUserRole(sysUserRole.getUserId(), sysUserRole.getRoleId());

        return result;
    }

}
