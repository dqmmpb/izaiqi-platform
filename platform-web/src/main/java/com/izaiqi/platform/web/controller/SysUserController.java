package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.izaiqi.platform.core.annotation.RequiresPermissions;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.domain.PageParam;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.*;
import com.izaiqi.platform.web.util.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户 controller
 *
 * @author deng.qiming
 * @date 2016-12-26 9:38
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 新增用户
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:add")
    public BaseResult addUser(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysUser sysUser = JSONObject.parseObject(paramsJSONString, SysUser.class);

        BaseResult result = new BaseResult();
        sysUserService.addUser(sysUser);

        return result;
    }

    /**
     * 更新用户信息
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:edit")
    public BaseResult editUser(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysUser sysUser = JSONObject.parseObject(paramsJSONString, SysUser.class);

        BaseResult result = new BaseResult();
        sysUserService.updateUser(sysUser);

        return result;
    }

    /**
     * 删除用户
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:delete")
    public BaseResult delUser(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        BaseResult result = new BaseResult();
        sysUserService.delUser(userId);

        return result;
    }


    /**
     * 禁用用户
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "lock", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:lock")
    public BaseResult lockUser(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        SysUser sysUser = SessionUtil.getSessionUser();

        BaseResult result = new BaseResult();
        sysUserService.lockUser(userId, sysUser.getUserId());

        return result;
    }


    /**
     * 启用用户
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "unlock", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:unlock")
    public BaseResult unlockUser(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        BaseResult result = new BaseResult();
        sysUserService.unlockUser(userId);

        return result;
    }

    /**
     * 修改密码
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    public BaseResult changePwd(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        String oldPwd = (String) params.get("oldPwd");
        String newPwd = (String) params.get("newPwd");

        SysUser sysUser = SessionUtil.getSessionUser();

        BaseResult result = new BaseResult();
        sysUserService.changePwd(sysUser.getUserId(), oldPwd, newPwd);

        return result;
    }

    /**
     * 重置密码
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "resetPwd", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:resetPwd")
    public BaseResult resetPwd(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        BaseResult result = new BaseResult();
        sysUserService.resetPwd(userId);

        return result;
    }


    /**
     * 通过userId查询用户
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public BaseResult getUser(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        BaseResult result = new BaseResult();
        SysUser sysUser = sysUserService.getUser(userId);

        result.setResult(sysUser);

        return result;
    }

    /**
     * 通过roleId查询用户列表
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "userrole", method = RequestMethod.POST)
    public BaseResult getUsersByRoleId(@RequestBody BaseParam param) throws BaseAppException {

        Map params = parseParams(param);
        Long roleId = ((Number) params.get("roleId")).longValue();

        BaseResult result = new BaseResult();
        result.setResult(sysUserService.queryUsersByRoleId(roleId));

        return result;
    }

    /**
     * 获取用户列表，按 user name排序
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @RequiresPermissions("sys:user:view")
    public BaseResult getUsers(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        PageParam pageParam = parsePage(params);

        List<SysUser> sysUserList = sysUserService.queryUsers(pageParam.getPageIndex(), pageParam.getPageSize());
        PageInfo page = new PageInfo(sysUserList);
        BaseResult result = new BaseResult();
        result.setResult(page);
        return result;
    }

//    /**
//     * 检查用户是否有权限
//     *
//     * @param path
//     * @return
//     */
//    @RequestMapping(value = "/checkUserMenuPriv", method = RequestMethod.POST)
//    public BaseResult checkUserMenuPriv(@RequestParam(name = "path") String path) throws BaseAppException {
//        SysUser sysUser = SessionUtil.getSessionUser();
//
//        BaseResult result = new BaseResult();
//        result.setResult(sysUserService.checkUserMenuPriv(sysUser.getUserId(), path));
//
//        return result;
//    }


}
