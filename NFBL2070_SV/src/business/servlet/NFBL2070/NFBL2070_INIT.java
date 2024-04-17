/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2070.NFBL2070CMsg;
//import business.servlet.NFBL2070.constant.NFBL2070Constant;

import business.blap.NFBL2070.NFBL2070CMsg;
import business.servlet.NFBL2070.common.NFBL2070CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Compensation Credit I/F Error Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   CUSA            Y.Miyauchi      Create          New
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * 2016/12/21   Fujitsu         H.Ikeda         Update          QC#12865
 * 
 *</pre>
 */
public class NFBL2070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;
        // Start 2016/12/21 H.Ikeda [QC#12865,ADD]
        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFBL2070");
        // End   2016/12/21 H.Ikeda [QC#12865,ADD]

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;

        NFBL2070CMsg bizMsg = new NFBL2070CMsg();
        bizMsg.setBusinessID("NFBL2070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        NFBL2070CommonLogic.initialize(this, scrnMsg, true);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;
        NFBL2070CMsg bizMsg  = (NFBL2070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2070CommonLogic.openSelectedRow(scrnMsg);
    }

    // START 2016/11/28 [QC#16158, ADD]
    @Override
    protected void setNameForMessage(EZDBMsg msg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) msg;
        scrnMsg.serNum_H1.setNameForMessage("Serial No");
        scrnMsg.rtlDivCd_H1.setNameForMessage("Division");
        scrnMsg.sellToCustCd_H1.setNameForMessage("Dealer Sell To");
        scrnMsg.svcDlrCd_H1.setNameForMessage("Dealer Ship To");
        scrnMsg.apInvRossStsCd_H1.setNameForMessage("Status");
        scrnMsg.rtlInvNum_H1.setNameForMessage("Credit No");
        scrnMsg.rtlInvApvlDt_HF.setNameForMessage("Credit Issue From");
        scrnMsg.rtlInvApvlDt_HT.setNameForMessage("Credit Issue To");

        scrnMsg.xxPageShowCurNum_P1.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFBL2070_ABMsg lineMsg = scrnMsg.A.no(i);
            
            lineMsg.rtlInvNum_A1.setNameForMessage("V Number");
            lineMsg.itrlRtlSmryInvNum_A1.setNameForMessage("S Number");
            lineMsg.rtlInvApvlDt_A1.setNameForMessage("Credit Issue Dt");
            lineMsg.serNum_A1.setNameForMessage("Serial No.");
            lineMsg.rtlInvStsCd_A1.setNameForMessage("Status");
            lineMsg.mdlNm_A1.setNameForMessage("Model");
            lineMsg.bllgBizTpCd_A1.setNameForMessage("Bus Type");
            lineMsg.bllgPerFromDt_A1.setNameForMessage("Comp From Date");
            lineMsg.bllgPerThruDt_A1.setNameForMessage("Comp To Date");
            lineMsg.dealGrsUnitPrcAmt_A1.setNameForMessage("Amount");
            lineMsg.apInvRossStsCd_A1.setNameForMessage("Error Status");
            lineMsg.billToCustCd_A1.setNameForMessage("Bill To");
            lineMsg.sellToCustCd_A1.setNameForMessage("Sell To");
            lineMsg.rtlInvLineNum_A1.setNameForMessage("Rtl Inv Line Num");
            lineMsg.rtlInvChrgTpDescTxt_A1.setNameForMessage("Trl inv Chrg To desc Txt");
            lineMsg.shipQty_A1.setNameForMessage("Ship Qty");
            lineMsg.slsTaxRate_A1.setNameForMessage("Sls Tax Rate");
            lineMsg.rtlDivCd_A1.setNameForMessage("Trl Div Cd");
            lineMsg.mdseCd_A1.setNameForMessage("Item Cd");
            lineMsg.svcDlrCd_A1.setNameForMessage("Svc Dlr Cd");
            lineMsg.instlPostCd_A1.setNameForMessage("Instl Post Cd");
            lineMsg.instlCd_A1.setNameForMessage("Instl Cd");
            lineMsg.istlSubLocCd_A1.setNameForMessage("Istl Sub Loc Cd");
            lineMsg.invLineCratDt_A1.setNameForMessage("Inv Line Crat Dt");
            lineMsg.invLineModDt_A1.setNameForMessage("Inv Line Mod Dt");
            lineMsg.rtlInvPk_A1.setNameForMessage("Retail Inv Pk");
            lineMsg.rtlInvLinePk_A1.setNameForMessage("Retail Inv Line Pk");
        }
    }
    // END 2016/11/28 [QC#16158, ADD]
}
