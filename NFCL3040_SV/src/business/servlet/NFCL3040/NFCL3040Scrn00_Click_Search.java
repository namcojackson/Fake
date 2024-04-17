/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3040;

import static business.servlet.NFCL3040.constant.NFCL3040Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3040.NFCL3040CMsg;
import business.servlet.NFCL3040.common.NFCL3040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/14   Hitachi         J.Kim           Create          QC#25695
 *</pre>
 */
public class NFCL3040Scrn00_Click_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3040BMsg scrnMsg = (NFCL3040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcCd_H);
        scrnMsg.addCheckItem(scrnMsg.arBatRcptNm_H);
        scrnMsg.addCheckItem(scrnMsg.arBatRcptStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxFileNm_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxCd_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxBatNum_H);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H1);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H2);

        if (ZYPCommonFunc.hasValue(scrnMsg.cratDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.cratDt_H2)) {
            if (scrnMsg.cratDt_H1.getValue().compareTo(scrnMsg.cratDt_H2.getValue()) > 0) {
                scrnMsg.cratDt_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.cratDt_H2.setErrorInfo(1, "NFCM0007E");
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3040BMsg scrnMsg = (NFCL3040BMsg) bMsg;

        NFCL3040CMsg bizMsg = new NFCL3040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3040BMsg scrnMsg = (NFCL3040BMsg) bMsg;
        NFCL3040CMsg bizMsg = (NFCL3040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3040CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, TABLE_A);
        NFCL3040CommonLogic.initialize(this, scrnMsg);
        scrnMsg.putErrorScreen();

    }
}