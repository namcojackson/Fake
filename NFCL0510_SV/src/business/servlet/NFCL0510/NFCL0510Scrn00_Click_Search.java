/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0510.NFCL0510CMsg;
import business.servlet.NFCL0510.common.NFCL0510CommonLogic;
import business.servlet.NFCL0510.constant.NFCL0510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 *</pre>
 */
public class NFCL0510Scrn00_Click_Search extends S21CommonHandler implements NFCL0510Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.arLockBoxFileNm_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxCd_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxBatNum_H);
        scrnMsg.addCheckItem(scrnMsg.arBatInfoMsgTxt_H);
        scrnMsg.addCheckItem(scrnMsg.rcvDt_H1);
        scrnMsg.addCheckItem(scrnMsg.rcvDt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H);

        if (!ZYPCommonFunc.hasValue(scrnMsg.arLockBoxFileNm_H)
             && !ZYPCommonFunc.hasValue(scrnMsg.arLockBoxCd_H)
             && !ZYPCommonFunc.hasValue(scrnMsg.arLockBoxStsCd_H)
             && !ZYPCommonFunc.hasValue(scrnMsg.arLockBoxBatNum_H)
             && !ZYPCommonFunc.hasValue(scrnMsg.arBatInfoMsgTxt_H)
             && !ZYPCommonFunc.hasValue(scrnMsg.rcvDt_H1)
             && !ZYPCommonFunc.hasValue(scrnMsg.rcvDt_H2)) {
                scrnMsg.arLockBoxFileNm_H.setErrorInfo(1, "NFCM0007E");
                scrnMsg.arLockBoxCd_H.setErrorInfo(1, "NFCM0007E");
                scrnMsg.arLockBoxStsCd_H.setErrorInfo(1, "NFCM0007E");
                scrnMsg.arLockBoxBatNum_H.setErrorInfo(1, "NFCM0007E");
                scrnMsg.arBatInfoMsgTxt_H.setErrorInfo(1, "NFCM0007E");
                scrnMsg.rcvDt_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.rcvDt_H2.setErrorInfo(1, "NFCM0007E");
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rcvDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.rcvDt_H2)) {
            if (scrnMsg.rcvDt_H1.getValue().compareTo(scrnMsg.rcvDt_H2.getValue()) > 0) {
                scrnMsg.rcvDt_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.rcvDt_H2.setErrorInfo(1, "NFCM0007E");
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;

        NFCL0510CMsg bizMsg = new NFCL0510CMsg();
        bizMsg.setBusinessID("NFCL0510");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0510BMsg scrnMsg = (NFCL0510BMsg) bMsg;
        NFCL0510CMsg bizMsg  = (NFCL0510CMsg) cMsg;

        S21TableColorController tblColor = new S21TableColorController("NFCL0510Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL0510CommonLogic.setProtected(this, scrnMsg, getUserProfileService());
    }
}
