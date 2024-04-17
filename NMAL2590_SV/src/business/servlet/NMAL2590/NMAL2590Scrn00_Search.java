/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2590;

import static business.servlet.NMAL2590.constant.NMAL2590Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2590.constant.NMAL2590Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2590.NMAL2590CMsg;
import business.servlet.NMAL2590.common.NMAL2590CommonLogic;
import business.servlet.NMAL2590.constant.NMAL2590Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/25   Fujitsu         C.Yokoi         Create          CSA-QC#4096
 *</pre>
 */
public class NMAL2590Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddr) && !ZYPCommonFunc.hasValue(scrnMsg.cntyNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.stCd) && !ZYPCommonFunc.hasValue(scrnMsg.postCd)) {
            scrnMsg.ctyAddr.setErrorInfo(1, NMAL2590Constant.NMAM0288E);
            scrnMsg.stCd.setErrorInfo(1, NMAL2590Constant.NMAM0288E);
            scrnMsg.cntyNm.setErrorInfo(1, NMAL2590Constant.NMAM0288E);
            scrnMsg.postCd.setErrorInfo(1, NMAL2590Constant.NMAM0288E);

            scrnMsg.addCheckItem(scrnMsg.ctyAddr);
            scrnMsg.addCheckItem(scrnMsg.stCd);
            scrnMsg.addCheckItem(scrnMsg.cntyNm);
            scrnMsg.addCheckItem(scrnMsg.postCd);

            scrnMsg.putErrorScreen();
        }
     }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;

        NMAL2590CMsg bizMsg = new NMAL2590CMsg();
        bizMsg.setBusinessID("NMAL2590");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2590BMsg scrnMsg = (NMAL2590BMsg) bMsg;
        NMAL2590CMsg bizMsg  = (NMAL2590CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        NMAL2590CommonLogic.setRowsBGWithClear(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.ctyAddr);
    }
}
