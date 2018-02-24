package com.izaiqi.platform.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.izaiqi.platform.core.common.Const;
import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.model.SysUser;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.web.common.Error;
import com.izaiqi.platform.web.util.SessionUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SysLoginControllerTest extends BaseControllerTest {

    @Test
    public void isLogin() throws Exception {
        String url = "/sys/isLogin";

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSession())
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        JSONObject sysUserLogin = jsonResult.getJSONObject("result");
        assertThat(sysUserLogin.getBoolean("login"), instanceOf(Boolean.class));
        assertThat(sysUserLogin.getBooleanValue("login"), anyOf(equalTo(true), equalTo(false)));
        assertThat(sysUserLogin.getBooleanValue("login"), equalTo(true));
    }

    @Test
    public void login() throws Exception {
        String url = "/sys/login";

        Map params = new HashMap();
        params.put("userName", "13819493701");
        params.put("password", "123456");
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
        assertEquals("13819493701", sysUserLogin.getString("phone"));
//        SysUser sysUser = SessionUtil.getSessionUser();
//        assertNotNull(sysUser);
//        assertEquals("13819493701", sysUser.getPhone());
    }

    @Test
    public void logout() throws Exception {
        String url = "/sys/logout";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .session((MockHttpSession) getSession())
        );
        MvcResult mvcResult = resultActions.andReturn();
        HttpSession httpSession = mvcResult.getRequest().getSession();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        JSONObject sysUserLogin = jsonResult.getJSONObject("result");
        assertNull(sysUserLogin);
        Object userObj = httpSession.getAttribute(Const.SESSION_LOGIN_USER);
        assertNull(userObj);
        try {
            SysUser sysUser = SessionUtil.getSessionUser();
        } catch (BaseAppException be) {
            assertEquals(Error.USER_SESSION_TIMEOUT.getCodeString(), be.getCode());
        }
    }
}
