/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.*;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.BUSINESS_ID;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.FUNCTION_SUBMIT;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.SCRN_ID;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import business.blap.NSBL0450.NSBL0450CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
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
public class NSBL0450Scrn00_CMN_Submit extends S21CommonHandler {

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
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = (NSBL0450CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        showDetail(this, scrnMsg, 0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, BigDecimal.valueOf(0));
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcSupplTaskNum_S, scrnMsg.svcSupplTaskNum_S2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.techPsnNm_S, scrnMsg.techPsnNm_S2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mgrNm_S, scrnMsg.mgrNm_S2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcSupplTaskTpCd_SS, scrnMsg.svcSupplTaskTpCd_S2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_S, scrnMsg.cratDt_S2);

    }
}
