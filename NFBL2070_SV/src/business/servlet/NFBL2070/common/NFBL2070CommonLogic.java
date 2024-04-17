/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2070.common;


import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NFBL2070.NFBL2070BMsg;
import business.servlet.NFBL2070.NFBL2070_ABMsg;
import business.servlet.NFBL2070.constant.NFBL2070Constant;
import parts.common.EZDBMsg;
import parts.servletcommon.EZDCommonHandler;

/** 
 *<pre>
 * Compensation Credit I/F Error Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   CUSA            Y.Miyauchi      Create          New
 * 2016/11/22   Hitachi         T.Tsuchida      Update          QC#16041
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 * 2016/12/21   Fujitsu         H.Ikeda         Update          QC#12865
 * 
 *</pre>
 */
public class NFBL2070CommonLogic implements NFBL2070Constant {

   /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL2660BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFBL2070BMsg scrnMsg, boolean iniFlg) {

        if ( iniFlg ) {
            scrnAppli.setButtonProperties("Search", "Search", "Search", 1, null);
            scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
            // Start 2016/12/21 H.Ikeda [QC#12865,ADD]
            if (hasUpdateFuncId(scrnMsg)) {
                scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
            } else {
                scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
            }
            // End   2016/12/21 H.Ikeda [QC#12865,ADD]
            scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
            scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
            scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
            scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
            scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
            scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
            scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
            scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);
        }
    }

    /**
     * @param scrnAppli
     * @param scrnMsg
     */
    public static void openSelectedRow(NFBL2070BMsg scrnMsg) {
        
        for (int iCnt=0; iCnt<scrnMsg.A.getValidCount(); iCnt++) {
            if (scrnMsg.A.no(iCnt).xxChkBox_A1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                //
                scrnMsg.A.no(iCnt).rtlInvPk_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvLinePk_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvStsCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).billToCustCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).sellToCustCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).serNum_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).mdlNm_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).rtlInvApvlDt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).bllgPerFromDt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).bllgPerThruDt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).rtlInvLineNum_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).rtlInvChrgTpDescTxt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).shipQty_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).slsTaxRate_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).bllgBizTpCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).rtlDivCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).rtlInvNum_A1.setInputProtected(false);
                // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
                //scrnMsg.A.no(iCnt).rtlSmryInvNum_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).itrlRtlSmryInvNum_A1.setInputProtected(false);
                // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
                scrnMsg.A.no(iCnt).mdseCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).svcDlrCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).instlPostCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).instlCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).istlSubLocCd_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).invLineCratDt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).invLineModDt_A1.setInputProtected(false);
                scrnMsg.A.no(iCnt).apInvRossStsCd_A1.setInputProtected(false);
           } else {
                scrnMsg.A.no(iCnt).rtlInvPk_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvLinePk_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvStsCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).billToCustCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).sellToCustCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).mdlNm_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).bllgPerFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).bllgPerThruDt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvChrgTpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).shipQty_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).dealGrsUnitPrcAmt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).slsTaxRate_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).bllgBizTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlDivCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).rtlInvNum_A1.setInputProtected(true);
                // START 2016/11/22 T.Tsuchida [QC#16041,MOD]
                //scrnMsg.A.no(iCnt).rtlSmryInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).itrlRtlSmryInvNum_A1.setInputProtected(true);
                // END 2016/11/22 T.Tsuchida [QC#16041,MOD]
                scrnMsg.A.no(iCnt).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).svcDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).instlPostCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).instlCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).istlSubLocCd_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).invLineCratDt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).invLineModDt_A1.setInputProtected(true);
                scrnMsg.A.no(iCnt).apInvRossStsCd_A1.setInputProtected(true);
           }
        }
        initAppFracDigit(scrnMsg);
        setTblBackColor(scrnMsg);
    }

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
        }
    }

// START 2016/11/28 [QC#16158, ADD]
    public static void itemCheckForSearch(NFBL2070BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.serNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlDivCd_H1);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.svcDlrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.apInvRossStsCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlInvNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlInvApvlDt_HF);
        scrnMsg.addCheckItem(scrnMsg.rtlInvApvlDt_HT);

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlInvApvlDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.rtlInvApvlDt_HT)) {
            if (scrnMsg.rtlInvApvlDt_HF.getValue().compareTo(scrnMsg.rtlInvApvlDt_HT.getValue()) > 0) {
                scrnMsg.rtlInvApvlDt_HF.setErrorInfo(1, ZZZM9010E, new String[] {"Credit Issue From", "Credit Issue To"});
                scrnMsg.rtlInvApvlDt_HT.setErrorInfo(1, ZZZM9010E, new String[] {"Credit Issue From", "Credit Issue To"});
            }
        }
    }

    public static void itemCheckForSubmit(NFBL2070BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFBL2070_ABMsg lineMsg = scrnMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxChkBox_A1.getValue())) {
                
                scrnMsg.addCheckItem(lineMsg.rtlInvNum_A1);
                scrnMsg.addCheckItem(lineMsg.itrlRtlSmryInvNum_A1);
                scrnMsg.addCheckItem(lineMsg.rtlInvApvlDt_A1);
                scrnMsg.addCheckItem(lineMsg.serNum_A1);
                scrnMsg.addCheckItem(lineMsg.rtlInvStsCd_A1);
                scrnMsg.addCheckItem(lineMsg.mdlNm_A1);
                scrnMsg.addCheckItem(lineMsg.bllgBizTpCd_A1);
                scrnMsg.addCheckItem(lineMsg.bllgPerFromDt_A1);
                scrnMsg.addCheckItem(lineMsg.bllgPerThruDt_A1);
                scrnMsg.addCheckItem(lineMsg.dealGrsUnitPrcAmt_A1);
                scrnMsg.addCheckItem(lineMsg.apInvRossStsCd_A1);
                scrnMsg.addCheckItem(lineMsg.billToCustCd_A1);
                scrnMsg.addCheckItem(lineMsg.sellToCustCd_A1);
                scrnMsg.addCheckItem(lineMsg.rtlInvLineNum_A1);
                scrnMsg.addCheckItem(lineMsg.rtlInvChrgTpDescTxt_A1);
                scrnMsg.addCheckItem(lineMsg.shipQty_A1);
                scrnMsg.addCheckItem(lineMsg.slsTaxRate_A1);
                scrnMsg.addCheckItem(lineMsg.rtlDivCd_A1);
                scrnMsg.addCheckItem(lineMsg.mdseCd_A1);
                scrnMsg.addCheckItem(lineMsg.svcDlrCd_A1);
                scrnMsg.addCheckItem(lineMsg.instlPostCd_A1);
                scrnMsg.addCheckItem(lineMsg.instlCd_A1);
                scrnMsg.addCheckItem(lineMsg.istlSubLocCd_A1);
                scrnMsg.addCheckItem(lineMsg.invLineCratDt_A1);
                scrnMsg.addCheckItem(lineMsg.invLineModDt_A1);
                scrnMsg.addCheckItem(lineMsg.rtlInvPk_A1);
                scrnMsg.addCheckItem(lineMsg.rtlInvLinePk_A1);

                if (ZYPCommonFunc.hasValue(lineMsg.bllgPerFromDt_A1) && ZYPCommonFunc.hasValue(lineMsg.bllgPerThruDt_A1)) {
                    if (lineMsg.bllgPerFromDt_A1.getValue().compareTo(lineMsg.bllgPerThruDt_A1.getValue()) > 0) {
                        lineMsg.bllgPerFromDt_A1.setErrorInfo(1, ZZZM9010E, new String[] {"Comp From Date", "Comp To Date"});
                        lineMsg.bllgPerThruDt_A1.setErrorInfo(1, ZZZM9010E, new String[] {"Comp From Date", "Comp To Date"});
                    }
                }
            }
        }
        
    }
    /**
     * set Table's Back Color
     * @param scrnMsg NFBL2070BMsg
     */
    public static final void setTblBackColor(NFBL2070BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }
    // END 2016/11/28 [QC#16158, ADD]

    // Start 2016/12/21 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFBL2070BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/21 H.Ikeda [QC#12865,ADD]
}
