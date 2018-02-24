package com.izaiqi.platform.core.i18n;

import com.izaiqi.platform.core.BaseTest;
import com.izaiqi.platform.core.common.ErrorCode;
import com.izaiqi.platform.core.exception.BaseAppException;
import com.izaiqi.platform.core.exception.ExceptionHandler;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class I18NMgrTest extends BaseTest {

    @Test
    public void testGetMessageEn() {
        try {
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST.getCodeString());
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("Priv Code Has Exist", I18NMgr.getMessage(e.getCode()));
        }
    }

    @Test
    public void testGetMessageZh() {
        try {
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST.getCodeString());
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("权限代码已存在", I18NMgr.getMessage(e.getCode(), Locale.CHINESE));
        }
    }

    @Test
    public void testGetMessageChange() {
        try {
            LocaleContextHolder.setLocale(Locale.CHINESE);
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST.getCodeString());
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("权限代码已存在", I18NMgr.getMessage(e.getCode()));
        }
        try {
            LocaleContextHolder.setLocale(Locale.ENGLISH);
            ExceptionHandler.publish(ErrorCode.PRIV_CODE_HAS_EXIST.getCodeString());
        } catch (BaseAppException e) {
//            e.printStackTrace();
            Assert.assertEquals("Priv Code Has Exist", I18NMgr.getMessage(e.getCode()));
        }
    }


}
