package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.web.BaseTest;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 测试基类
 * <p>
 * Created by alphabeta on 17-9-24 下午6:47.
 */

public abstract class BaseControllerTest extends BaseTest {

    protected MockMvc mockMvc;

    protected HttpSession session;

    @Resource
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = getSession(true);
    }

    protected HttpSession getSuperAdminSession() throws Exception {
        // 默认强制登录
        return getSuperAdminSession(true);
    }

    protected HttpSession getSuperAdminSession(boolean forceLogin) throws Exception {
        // 使用单例模式返回session，默认强制登录
        return this.getSession("13819493700", "123456", forceLogin);
    }

    protected HttpSession getSession(String userName, String password) throws Exception {
        // 默认强制登录
        return getSession(userName, password, true);
    }

    protected HttpSession getSession(String userName, String password, boolean forceLogin) throws Exception {
        // 使用单例模式返回session，默认强制登录
        if (forceLogin) {
            String url = "/sys/login";

            Map params = new HashMap();
            params.put("userName", userName);
            params.put("password", password);
            BaseParam param = new BaseParam();
            param.setParams(params);
            String requestJson = JSONObject.toJSONString(param);

            ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
            );
            MvcResult mvcResult = resultActions.andReturn();
            String result = mvcResult.getResponse().getContentAsString();
            assertNotNull(result);
            JSONObject jsonResult = JSONObject.parseObject(result);
            JSONObject sysUserLogin = jsonResult.getJSONObject("result");
            assertNotNull(sysUserLogin);
            assertEquals(userName, sysUserLogin.getString("userName"));

            HttpSession httpSession = mvcResult.getRequest().getSession();
            assertNotNull(httpSession);
            session = httpSession;
        }
        return session;
    }

    protected HttpSession getSession() throws Exception {
        // 默认强制登录
        return getSession(true);
    }

    protected HttpSession getSession(boolean forceLogin) throws Exception {
        // 使用单例模式返回session，默认强制登录
        return this.getSession("13819493701", "123456", forceLogin);
    }
}
