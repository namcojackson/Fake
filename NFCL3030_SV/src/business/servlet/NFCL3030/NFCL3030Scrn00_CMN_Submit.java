/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3030.NFCL3030CMsg;
//import business.servlet.NFCL3030.constant.NFCL3030Constant;

import business.blap.NFCL3030.NFCL3030CMsg;
import business.servlet.NFCL3030.common.NFCL3030CommonLogic;
import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 02/10/2016   Fujitsu         T.Tanaka        Update          Def#2742
 * 01/18/2018   Hitachi         Y.Takeno        Update          QC#21406
 * 02/02/2018   Fujitsu         T.Murai         Update          QC#21372
 *</pre>
 */
public class NFCL3030Scrn00_CMN_Submit extends S21CommonHandler implements NFCL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        // START 2018/01/18 [QC#21406, ADD]
        scrnMsg.addCheckItem(scrnMsg.rcptChkNum_H);
        // END   2018/01/18 [QC#21406, ADD]
        scrnMsg.addCheckItem(scrnMsg.rcptDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptTrxTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcCd_H);
        scrnMsg.addCheckItem(scrnMsg.glDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptNoteTxt_H);
        scrnMsg.addCheckItem(scrnMsg.xxTotAmt_H1);
//        scrnMsg.addCheckItem(scrnMsg.arRcptRemDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxNum);
        scrnMsg.addCheckItem(scrnMsg.crCardApvlCd_H);
        scrnMsg.addCheckItem(scrnMsg.pmtSvcOrdNum_H);
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H); // ADD 2018/02/02 [QC#21372]
        scrnMsg.addCheckItem(scrnMsg.xxNum);
        scrnMsg.addCheckItem(scrnMsg.arRcptRefTxt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptCmntTxt_H);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

        NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3030CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        scrnMsg.addCheckItem(scrnMsg.rcptDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptTrxTpCd_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptSrcCd_H);
        scrnMsg.addCheckItem(scrnMsg.glDt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptNoteTxt_H);
        scrnMsg.addCheckItem(scrnMsg.xxTotAmt_H1);
        scrnMsg.addCheckItem(scrnMsg.arRcptRemDt_H);
        scrnMsg.addCheckItem(scrnMsg.xxNum);
        scrnMsg.addCheckItem(scrnMsg.crCardApvlCd_H);
        scrnMsg.addCheckItem(scrnMsg.pmtSvcOrdNum_H);
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_H);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H); // ADD 2018/02/02 [QC#21372]
        scrnMsg.addCheckItem(scrnMsg.locNum_H); // ADD 2018/02/02 [QC#21372]
        scrnMsg.addCheckItem(scrnMsg.xxNum);
        scrnMsg.addCheckItem(scrnMsg.arRcptRefTxt_H);
        scrnMsg.addCheckItem(scrnMsg.arRcptCmntTxt_H);

        if(ZYPCommonFunc.hasValue(scrnMsg.xxTotAmt_H1.getValue())) {
            if(scrnMsg.xxTotAmt_H1.getValue().compareTo(BigDecimal.ZERO) < 0) {
                scrnMsg.xxTotAmt_H1.setErrorInfo(1, "NFDM0016E");
            }
        }

        if(ZYPCommonFunc.hasValue(scrnMsg.arRcptRemDt_H.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.arRcptRemDt_H);
        }
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.rcptChkNum_H);
    }
}
