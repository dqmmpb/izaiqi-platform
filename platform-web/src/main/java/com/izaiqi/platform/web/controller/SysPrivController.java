package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.github.pagehelper.PageInfo;
import com.izaiqi.platform.core.annotation.RequiresPermissions;
import com.izaiqi.platform.core.common.PrivType;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.core.domain.PageParam;
import com.izaiqi.platform.core.domain.model.SysPriv;
import com.izaiqi.platform.core.domain.model.SysRole;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.service.SysPrivService;
import com.izaiqi.platform.core.service.SysRoleService;
import com.izaiqi.platform.core.util.EqualsUtil;
import com.izaiqi.platform.core.util.ListUtil;
import com.izaiqi.platform.web.result.SysMenuResult;
import com.izaiqi.platform.web.result.model.SysMenuModel;
import com.izaiqi.platform.web.util.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权限
 *
 * @author deng.qiming
 * @date 2016-12-26 9:39
 */
@RestController
@RequestMapping("/sys/priv")
public class SysPrivController extends BaseController {


    @Resource
    private SysPrivService sysPrivService;


    @Resource
    private SysRoleService sysRoleService;

    /**
     * 新增权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:priv:add")
    public BaseResult addPriv(@RequestBody BaseParam param) throws BaseAppException {

        logger.debug("add priv begin");

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysPriv sysPriv = JSONObject.parseObject(paramsJSONString, SysPriv.class);

        BaseResult result = new BaseResult();
        sysPrivService.addPriv(sysPriv);

        return result;
    }

    /**
     * 编辑权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @RequiresPermissions("sys:priv:edit")
    public BaseResult editPriv(@RequestBody BaseParam param) throws BaseAppException {

        logger.debug("edit priv begin");

        Map params = parseParams(param);
        String paramsJSONString = JSONObject.toJSONString(params);
        SysPriv sysPriv = JSONObject.parseObject(paramsJSONString, SysPriv.class);

        BaseResult result = new BaseResult();
        sysPrivService.updatePriv(sysPriv);

        return result;
    }

    /**
     * 删除权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:priv:delete")
    public BaseResult delPriv(@RequestBody BaseParam param) throws BaseAppException {

        logger.debug("delete priv begin");

        Map params = parseParams(param);
        Long privId = ((Number) params.get("privId")).longValue();

        BaseResult result = new BaseResult();
        sysPrivService.delPriv(privId);

        return result;
    }

    /**
     * 查询权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @FastJsonView(
        exclude = {@FastJsonFilter(clazz = SysPriv.class, props = {"createTime", "updateTime"})})
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public BaseResult getPriv(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        Long privId = ((Number) params.get("privId")).longValue();

        BaseResult result = new BaseResult();
        result.setResult(sysPrivService.getPriv(privId));

        return result;
    }

    /**
     * 获取指定Role下的权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @FastJsonView(
        exclude = {@FastJsonFilter(clazz = SysPriv.class, props = {"createTime", "updateTime"})})
    @RequestMapping(value = "rolepriv", method = RequestMethod.POST)
    public BaseResult getRolePrivs(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        Long roleId = ((Number) params.get("roleId")).longValue();
        PageParam pageParam = parsePage(params);

        BaseResult result = new BaseResult();
        List<SysPriv> sysPrivList = sysPrivService.queryPrivsByRoleId(roleId, pageParam.getPageIndex(), pageParam.getPageSize());
        PageInfo page = new PageInfo(sysPrivList);
        result.setResult(page);

        return result;
    }

    /**
     * 查询用户所有权限
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @FastJsonView(
        exclude = {@FastJsonFilter(clazz = SysPriv.class, props = {"createTime", "updateTime"})})
    @RequestMapping(value = "userpriv", method = RequestMethod.POST)
    public BaseResult getUserPrivsByUserId(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        Long userId = ((Number) params.get("userId")).longValue();

        BaseResult result = new BaseResult();
        List<SysPriv> sysPrivList = sysPrivService.queryPrivsByUserId(userId);
        result.setResult(sysPrivList);

        return result;
    }

    /**
     * 获取用户菜单
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public SysMenuResult getMenu(@RequestBody BaseParam param) throws BaseAppException {
        SysMenuResult result = new SysMenuResult();

        SysUser sysUser = SessionUtil.getSessionUser();

        //查询用户所有的角色
        List<SysRole> sysRoles = sysRoleService.queryRolesByUserId(sysUser.getUserId());

        if (ListUtil.isEmpty(sysRoles)) {
            logger.warn("userId={},userName={} has no role", sysUser.getUserId(), sysUser.getUserName());
            return result;
        }

        //查询所有角色的权限
        List<Long> roleIds = new ArrayList<>(sysRoles.size());
        for (SysRole sysRole : sysRoles) {
            roleIds.add(sysRole.getRoleId());
        }

        List<SysPriv> sysPrivs = sysPrivService.queryPrivsByRoleIds(roleIds);
        if (ListUtil.isEmpty(sysPrivs)) {
            logger.warn("userId={},userName={} has no sysPrivs", sysUser.getUserId(), sysUser.getUserName());
            return result;
        }

        //组装成菜单
        List<SysMenuModel> menus = makeMenu(sysPrivs);
        result.setResult(menus);

        return result;
    }

    /**
     * 查询所有菜单【扁平列表】
     *
     * @param param
     * @return
     * @throws BaseAppException
     */
    @FastJsonView(
        exclude = {@FastJsonFilter(clazz = SysPriv.class, props = {"createTime", "updateTime"})})
    @RequestMapping(value = "privs", method = RequestMethod.POST)
    public BaseResult getPrivs(@RequestBody BaseParam param) throws BaseAppException {
        Map params = parseParams(param);
        PageParam pageParam = parsePage(params);

        BaseResult result = new BaseResult();
        List<SysPriv> sysPrivList = sysPrivService.queryPrivs(pageParam.getPageIndex(), pageParam.getPageSize());
        PageInfo page = new PageInfo(sysPrivList);
        result.setResult(page);

        return result;
    }

//    /**
//     * 获取所有权限【树形结构】
//     *
//     * @param request
//     * @return
//     * @throws BaseAppException
//     */
//    @RequestMapping("/privsTree")
//    public SysMenuResult getAllMenu(HttpServletRequest request) throws BaseAppException {
//        SysMenuResult result = new SysMenuResult();
//
//        List<SysPriv> sysPrivs = sysPrivService.queryPrivs(null, null);
//        if (ListUtil.isEmpty(sysPrivs)) {
//            logger.warn("there is no sysPrivs in system db.");
//            return result;
//        }
//
//        //组装成菜单
//        List<SysMenuModel> menus = makePriv(sysPrivs, false);
//        result.setResult(menus);
//
//        return result;
//    }

//    /**
//     * 获取更目录权限
//     *
//     * @return
//     */
//    @RequestMapping(value = "/rootPrivs", method = RequestMethod.GET)
//    public BaseResult getRootPrivs() throws BaseAppException {
//        BaseResult result = new BaseResult();
//        result.setResult(sysPrivService.queryRootPrivs());
//        return result;
//    }

    /**
     * 菜单权限
     *
     * @param sysPrivs
     * @return
     */
    private List<SysMenuModel> makeMenu(List<SysPriv> sysPrivs) {
        return makePriv(sysPrivs, true);
    }

    /**
     * 所有权限
     *
     * @param sysPrivs
     * @param filterDataPriv 是否要过滤数据权限
     * @return
     */
    private List<SysMenuModel> makePriv(List<SysPriv> sysPrivs, Boolean filterDataPriv) {
        if (filterDataPriv == null) {
            filterDataPriv = false;
        }
        List<SysMenuModel> menus = new ArrayList<>(sysPrivs.size());

        for (SysPriv sysPriv : sysPrivs) {
            logger.debug("sysPriv={}", sysPriv);
            SysMenuModel menuModel = new SysMenuModel();

            menuModel.setId(sysPriv.getPrivId());
            menuModel.setCode(sysPriv.getPrivCode());
            menuModel.setName(sysPriv.getPrivName());
            menuModel.setType(sysPriv.getType());
            menuModel.setUrl(sysPriv.getUrl());
            menuModel.setPath(sysPriv.getPath());
            menuModel.setDescription(sysPriv.getDescription());

            if (EqualsUtil.equals(sysPriv.getType().intValue(), PrivType.DIR.getValue())) {
                List<SysMenuModel> subMenus = new ArrayList<>();
                menuModel.setChildren(subMenus);
                menus.add(menuModel);

            } else if (EqualsUtil.equals(sysPriv.getType().intValue(), PrivType.MENU.getValue())) {
                //如果是菜单则查找父层目录，如果没有，则添加到列表末尾
                boolean hit = false;
                for (SysMenuModel menu : menus) {
                    if (EqualsUtil.equals(menu.getId().longValue(), sysPriv.getParentPrivId().longValue())) {
                        menu.getChildren().add(menuModel);
                        hit = true;
                        break;
                    }
                }
                if (!hit) {
                    menus.add(menuModel);
                }
            } else if (EqualsUtil.equals(sysPriv.getType().intValue(), PrivType.DATA.getValue())) {
                if (!filterDataPriv) {
                    menus.add(menuModel);
                }
            } else {
                logger.warn("unkown type sysPriv,{}", sysPriv);
            }

        }

        return menus;
    }

}
