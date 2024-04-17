/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3010.NFCL3010CMsg;
import business.servlet.NFCL3010.common.NFCL3010CommonLogic;
import business.servlet.NFCL3010.constant.NFCL3010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/02/02   Fujitsu         T.Murai         Update          S21_NA#21372
 * 2018/03/20   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/10/17   Fujitsu         T.Noguchi       Update          QC#28428
 *</pre>
 */
public class NFCL3010Scrn00_Click_Search extends S21CommonHandler implements NFCL3010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcCd_H);
        scrnMsg.addCheckItem(scrnMsg.arBatRcptNm_H);
        scrnMsg.addCheckItem(scrnMsg.arBatRcptStsCd_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxFileNm_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxCd_H);
        scrnMsg.addCheckItem(scrnMsg.arLockBoxBatNum_H);
        // Mod Start 2018/02/02 S21_NA#21372
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H);
        // scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H2);
        // Mod End 2018/02/02 S21_NA#21372
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H);
        scrnMsg.addCheckItem(scrnMsg.rcptNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptNum_H2);
        scrnMsg.addCheckItem(scrnMsg.rcptDt_H1);
        scrnMsg.addCheckItem(scrnMsg.rcptDt_H2);
        scrnMsg.addCheckItem(scrnMsg.funcRcptAmt_H1);
        scrnMsg.addCheckItem(scrnMsg.funcRcptAmt_H2);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm_H);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H1);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H2);
        // Del Start 2018/02/02 S21_NA#21372
//        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H2)) {
//            if (scrnMsg.dsAcctNum_H1.getValue().compareTo(scrnMsg.dsAcctNum_H2.getValue()) > 0) {
//                scrnMsg.dsAcctNum_H1.setErrorInfo(1, "NFCM0007E");
//                scrnMsg.dsAcctNum_H2.setErrorInfo(1, "NFCM0007E");
//            }
//        }
        // Del End 2018/02/02 S21_NA#21372
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.rcptNum_H2)) {
            if (scrnMsg.rcptNum_H1.getValue().compareTo(scrnMsg.rcptNum_H2.getValue()) > 0) {
                scrnMsg.rcptNum_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.rcptNum_H2.setErrorInfo(1, "NFCM0007E");
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rcptDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.rcptDt_H2)) {
            if (scrnMsg.rcptDt_H1.getValue().compareTo(scrnMsg.rcptDt_H2.getValue()) > 0) {
                scrnMsg.rcptDt_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.rcptDt_H2.setErrorInfo(1, "NFCM0007E");
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.funcRcptAmt_H1) && ZYPCommonFunc.hasValue(scrnMsg.funcRcptAmt_H2)) {
            if (scrnMsg.funcRcptAmt_H1.getValue().compareTo(scrnMsg.funcRcptAmt_H2.getValue()) > 0) {
                scrnMsg.funcRcptAmt_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.funcRcptAmt_H2.setErrorInfo(1, "NFCM0007E");
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.cratDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.cratDt_H2)) {
            if (scrnMsg.cratDt_H1.getValue().compareTo(scrnMsg.cratDt_H2.getValue()) > 0) {
                scrnMsg.cratDt_H1.setErrorInfo(1, "NFCM0007E");
                scrnMsg.cratDt_H2.setErrorInfo(1, "NFCM0007E");
            }
        }
        // 2018/10/17 QC#28428 Add Start
        NFCL3010CommonLogic.checkMandatorySearchCondition(scrnMsg);
        // 2018/10/17 QC#28428 Add End
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

        // 2018/10/17 QC#28428 Add Start
        NFCL3010CommonLogic.setToSearchCondition(scrnMsg);
        // 2018/10/17 QC#28428 Add End

        NFCL3010CMsg bizMsg = new NFCL3010CMsg();
        bizMsg.setBusinessID("NFCL3010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        NFCL3010CMsg bizMsg  = (NFCL3010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21TableColorController tblColor = new S21TableColorController("NFCL3010Scrn00", scrnMsg);
        NFCL3010CommonLogic.initialize(this, scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        // Del Start 2018/03/20 S21_NA#21737
        //tblColor.clearRowsBG(TABLE_B, scrnMsg.B);
        //tblColor.setAlternateRowsBG(TABLE_B, scrnMsg.B);
        // Del End   2018/03/20 S21_NA#21737
        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.putErrorScreen();
    }
}
