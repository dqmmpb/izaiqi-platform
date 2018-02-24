package com.izaiqi.platform.web.result;

import com.izaiqi.platform.core.domain.BaseResult;
import com.izaiqi.platform.web.result.model.SysMenuModel;

import java.util.List;

/**
 * 模块名
 *
 * @author deng.qiming
 * @date 2016-12-26 19:10
 */
public class SysMenuResult extends BaseResult {

    private List<SysMenuModel> result;

    @Override
    public List<SysMenuModel> getResult() {
        return result;
    }

    public void setResult(List<SysMenuModel> result) {
        this.result = result;
    }
}
