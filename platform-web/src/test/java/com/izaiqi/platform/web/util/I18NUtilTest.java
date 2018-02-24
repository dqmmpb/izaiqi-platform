package com.izaiqi.platform.web.util;

import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import com.izaiqi.platform.core.i18n.I18NMgr;
import com.izaiqi.platform.web.BaseTest;
import com.izaiqi.platform.web.common.Error;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class I18NUtilTest extends BaseTest {

    @Test
    public void getMessageEn() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        try {
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST.getCodeString());
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("Priv Code Has Exist", I18NMgr.getMessage(e.getCode()));
        }
    }

    @Test
    public void getMessageZh() {
        LocaleContextHolder.setLocale(Locale.CHINESE);
        try {
            ExceptionHandler.publish(Error.I18N_MESSAGE_TEST.getCodeString());
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("测试国际化", I18NMgr.getMessage(e.getCode()));
        }

        // 使用标准日期格式定义i18n中的日期格式化
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 测试参数化的Message
            ExceptionHandler.publish(Error.I18N_MESSAGE_TEST_PARAMS.getCodeString(),
                I18NUtil.getMessage(Error.I18N_MESSAGE_TEST_PARAMS.getCodeString(), new Object[]{
                    "deng.qiming",
                    now,
                }));
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("测试参数化。欢迎deng.qiming，今天是" + sdf.format(now) + "！", e.getDesc());
        }
    }
}
