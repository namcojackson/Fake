/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.checkInputForDetail;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.initialControlScreen;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.reflectToLine;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.showDetail;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.BUSINESS_ID;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.FUNCTION_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0450.NSBL0450CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSBL0450Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        checkInputForDetail(this, scrnMsg, ctx.getEventName());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        reflectToLine(this, scrnMsg);

        NSBL0450CMsg bizMsg = new NSBL0450CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = (NSBL0450CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        showDetail(this, scrnMsg, 0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, BigDecimal.valueOf(0));

    }
}
