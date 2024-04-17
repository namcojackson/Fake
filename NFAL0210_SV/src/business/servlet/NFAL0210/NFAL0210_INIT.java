/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0210;

import static business.servlet.NFAL0210.constant.NFAL0210Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0210.NFAL0210CMsg;
import business.servlet.NFAL0210.common.NFAL0210CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         K.Kojima        Create          N/A
 * 2016/12/01   Fujitsu         T.Murai         Update          QC#16158
 *</pre>
 */
public class NFAL0210_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) bMsg;
        NFAL0210CMsg bizMsg = new NFAL0210CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) bMsg;
        NFAL0210CMsg bizMsg = (NFAL0210CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFAL0210CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.manJrnlNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFAL0210BMsg scrnMsg = (NFAL0210BMsg) arg0;
        scrnMsg.manJrnlNm.setNameForMessage("Journal Name");
        scrnMsg.glPerNm.setNameForMessage("GL Period");
        scrnMsg.xxFromDt.setNameForMessage("Accounting Date(From)");
        scrnMsg.xxToDt.setNameForMessage("Accounting Date(To)");
        scrnMsg.jrnlCatgCd.setNameForMessage("Category");
        scrnMsg.glSendCpltFlg_SV.setNameForMessage("GL Send");
        scrnMsg.rvslCpltFlg_SV.setNameForMessage("Reversed");
        scrnMsg.manJrnlCpltFlg_SV.setNameForMessage("Complete");
        scrnMsg.autoRvslFlg_SV.setNameForMessage("Auto Reversal");

        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName()); //ADD 2016/12/01 [QC#16158]
    }
}
