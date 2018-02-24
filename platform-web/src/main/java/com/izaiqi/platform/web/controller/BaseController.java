package com.izaiqi.platform.web.controller;

import com.izaiqi.platform.core.domain.BaseParam;
import com.izaiqi.platform.core.domain.PageParam;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.util.ObjectUtil;
import com.izaiqi.platform.web.common.Error;
import com.izaiqi.platform.web.util.RequestUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目基类
 *
 * @author deng.qiming
 * @date 2016年11月8日 上午10:54:39
 */
public abstract class BaseController extends com.izaiqi.platform.core.domain.BaseController {

    @Resource
    protected RedisTemplate<String, String> redisTemplate;

    public Map parseParams(BaseParam param) throws BaseAppException {
        return parseParams(param, false);
    }

    public Map parseParams(BaseParam param, boolean validSign) throws BaseAppException {
        return RequestUtils.parseParams(param, validSign);
    }

    /**
     * 分页参数提取
     *
     * @param params
     * @return
     */
    protected PageParam parsePage(Map params) {
       return RequestUtils.parsePage(params);
    }
}
