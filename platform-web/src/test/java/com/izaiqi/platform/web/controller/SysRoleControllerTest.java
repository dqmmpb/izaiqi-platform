package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.model.SysRole;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SysRoleControllerTest extends BaseControllerTest {

    @Test
    public void addRole() throws Exception {
        String url = "/sys/role/add";

        Map params = new HashMap();
        params.put("roleName", "新建角色测试");
        params.put("roleCode", "Tester");
        params.put("description", "这是一个测试角色");
        BaseParam param = new BaseParam();
        param.setParams(params);
        String requestJson = JSONObject.toJSONString(param);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSuperAdminSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        assertEquals(true, jsonResult.getBoolean("success"));
    }

    @Test
    public void editRole() throws Exception {
        String url = "/sys/role/edit";

        // privCode和createTime在编辑的时候，不允许修改
        Map params = new HashMap();
        params.put("roleId", "4");
        params.put("description", "这是一个已经编辑过的测试角色");
        BaseParam param = new BaseParam();
        param.setParams(params);
        String requestJson = JSONObject.toJSONString(param);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSuperAdminSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        assertEquals(true, jsonResult.getBoolean("success"));
    }

    @Test
    public void delRole() throws Exception {
        String url = "/sys/role/delete";

        Map params = new HashMap();
        params.put("roleId", 4);
        BaseParam param = new BaseParam();
        param.setParams(params);
        String requestJson = JSONObject.toJSONString(param);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSuperAdminSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        assertEquals(true, jsonResult.getBoolean("success"));
    }

    @Test
    public void getRole() throws Exception {
        String url = "/sys/role/get";

        Map params = new HashMap();
        params.put("roleId", 1);
        BaseParam param = new BaseParam();
        param.setParams(params);
        String requestJson = JSONObject.toJSONString(param);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSuperAdminSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        JSONObject sysRoleJsonObj = jsonResult.getJSONObject("result");
        assertNotNull(sysRoleJsonObj);
        SysRole sysRole = sysRoleJsonObj.toJavaObject(SysRole.class);
        assertEquals("超级管理员", sysRole.getRoleName());
        assertEquals("SuperAdmin", sysRole.getRoleCode());
    }

    @Test
    public void getRolesByUserId() throws Exception {
        String url = "/sys/role/userrole";

        Map params = new HashMap();
        params.put("userId", 2);
        BaseParam param = new BaseParam();
        param.setParams(params);
        String requestJson = JSONObject.toJSONString(param);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSuperAdminSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        JSONArray sysRoleJsonArray = jsonResult.getJSONArray("result");
        assertNotNull(sysRoleJsonArray);
        List<SysRole> sysRoleList = sysRoleJsonArray.toJavaList(SysRole.class);
        assertEquals(1, sysRoleList.size());
    }

    @Test
    public void getRoles() throws Exception {
        String url = "/sys/role/roles";

        Map params = new HashMap();
        params.put("pageIndex", 1);
        params.put("pageSize", 10);
        BaseParam param = new BaseParam();
        param.setParams(params);
        String requestJson = JSONObject.toJSONString(param);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        JSONObject rolesJsonObj = jsonResult.getJSONObject("result");
        PageInfo page = rolesJsonObj.toJavaObject(PageInfo.class);
        assertEquals(3, page.getTotal());
        assertEquals(3, page.getList().size());

        // 第2页
        params.put("pageIndex", 2);
        param = new BaseParam();
        param.setParams(params);
        requestJson = JSONObject.toJSONString(param);
        resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSession())
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
        );
        mvcResult = resultActions.andReturn();
        result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        jsonResult = JSONObject.parseObject(result);
        rolesJsonObj = jsonResult.getJSONObject("result");
        page = rolesJsonObj.toJavaObject(PageInfo.class);
        assertEquals(3, page.getTotal());
        assertEquals(3, page.getList().size());
    }
}
