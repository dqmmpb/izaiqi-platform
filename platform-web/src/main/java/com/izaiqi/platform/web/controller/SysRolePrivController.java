package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.domain.model.SysRolePriv;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.annotation.RequiresPermissions;
import com.izaiqi.platform.core.service.SysRolePrivService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 角色权限
 *
 * @author deng.qiming
 * @date 2017-01-05 13:10
 */
@RestController
@RequestMapping("/sys/rolepriv")
public class SysRolePrivController extends BaseController {

    @Resource
    private SysRolePrivService sysRolePrivService;

    /**
     * 绑定角色和权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:allocate")
    public BaseResult addRolePriv(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysRolePriv sysRolePriv = JSONObject.parseObject(paramsJSONString, SysRolePriv.class);

        sysRolePrivService.addRolePriv(sysRolePriv.getRoleId(), sysRolePriv.getPrivId());
        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 删除角色和权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:role:allocate")
    public BaseResult delRolePriv(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysRolePriv sysRolePriv = JSONObject.parseObject(paramsJSONString, SysRolePriv.class);

        sysRolePrivService.delRolePriv(sysRolePriv.getRoleId(), sysRolePriv.getPrivId());

        BaseResult result = new BaseResult();
        return result;
    }

}
