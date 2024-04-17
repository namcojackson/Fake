/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3040;

import static business.servlet.NFCL3040.constant.NFCL3040Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NFCL3040.NFCL3040CMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3040.common.NFCL3040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/04   Hitachi         J.Kim           Create          QC#25695
 *</pre>
 */
public class NFCL3040_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
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

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL3040BMsg scrnMsg = (NFCL3040BMsg) bMsg;
        scrnMsg.arRcptSrcCd_H.setNameForMessage("Receipt Source");
        scrnMsg.arBatRcptNm_H.setNameForMessage("Batch Name");
        scrnMsg.arBatRcptStsCd_H.setNameForMessage("Batch Status");
        scrnMsg.arLockBoxFileNm_H.setNameForMessage("Lockbox File Name");
        scrnMsg.arLockBoxCd_H.setNameForMessage("Lockbox");
        scrnMsg.arLockBoxBatNum_H.setNameForMessage("Lockbox Batch");

        scrnMsg.cratDt_H1.setNameForMessage("Remittance Bank Name");
        scrnMsg.cratDt_H1.setNameForMessage("Remittance Branch Name");
        scrnMsg.cratDt_H1.setNameForMessage("Remittance Account Number");
        scrnMsg.cratDt_H1.setNameForMessage("Creation Date From");
        scrnMsg.cratDt_H2.setNameForMessage("Creation Date To");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).arBatRcptAmt.setAppFracDigit(2);
        }
    }
}
