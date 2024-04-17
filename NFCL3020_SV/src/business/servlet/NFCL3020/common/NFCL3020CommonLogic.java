package business.servlet.NFCL3020.common;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL3020.NFCL3020BMsg;
import business.servlet.NFCL3020.NFCL3020Bean;
import business.servlet.NFCL3020.NFCL3020_BBMsgArray;
import business.servlet.NFCL3020.constant.NFCL3020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 2016/10/07   Hitachi         K.Kojima        Update          QC#13432
 * 2018/01/10   Fujitsu         T.Murai         Update          QC#21400
 * 2018/01/16   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/03/22   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/04/04   Fujitsu         H.Ikeda         Update          QC#21737-1
 *</pre>
 */
public class NFCL3020CommonLogic implements NFCL3020Constant {

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL3010BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL3020BMsg scrnMsg, S21UserProfileService userProfileService) {
        scrnMsg.clearAllGUIAttribute("NFCL3020Scrn00");

        // START 2018/03/22 H.Ikeda [QC#21737, ADD]
        NFCL3020_BBMsgArray lineMsgArray = scrnMsg.B;
        // START 2018/03/22 H.Ikeda [QC#21737, ADD]
//        S21TableColorController tblColor = new S21TableColorController("NFCL3020Scrn00", scrnMsg);
////        tblColor.clearRowsBG(TABLE_B, scrnMsg.B);
//        tblColor.setAlternateRowsBG(TABLE_B, scrnMsg.B);

        scrnMsg.setInputProtected(false);
        scrnAppli.setButtonProperties("Click_Add", "Click_Add", "Add", 1, null);
        // START 2018/03/22 H.Ikeda [QC#24801, MOD]
        if (lineMsgArray.getValidCount() > 0) {
            scrnAppli.setButtonProperties("Click_Del", "Click_Del", "Del", 1, null);
            
        } else {
            scrnAppli.setButtonProperties("Click_Del", "Click_Del", "Del", 0, null);
        }
        // END   2018/03/22 H.Ikeda [QC#24801, MOD]
        scrnAppli.setButtonProperties("OnClick_Prev", "OnClick_Prev", "Prev", 1, null);
        scrnAppli.setButtonProperties("OnClick_Next", "OnClick_Next", "Next", 1, null);
        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2018/03/22 H.Ikeda [QC#21737, MOD]
        if (lineMsgArray.getValidCount() > 0) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        // END    2018/03/22 H.Ikeda [QC#21737, MOD]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        if(scrnMsg.appFuncId.getValue().equals(FUNC_ID)) {
            scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
            scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 0, null);
        } else {
            scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
            scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 1, null);
        }

        scrnMsg.arBatRcptNm_H.setInputProtected(true);
        scrnMsg.arBatRcptStsCd_H.setInputProtected(true);

        scrnMsg.arLockBoxFileNm_H.setInputProtected(true);
        scrnMsg.arLockBoxCd_H.setInputProtected(true);
        scrnMsg.arLockBoxBatNum_H.setInputProtected(true);

        scrnMsg.dsBankAcctNm_H.setInputProtected(true);
        scrnMsg.dsBankBrNm_H.setInputProtected(true);
        scrnMsg.dsBankAcctNum_H.setInputProtected(true);
        
        scrnMsg.xxTotCnt_H1.setInputProtected(true);
        scrnMsg.xxTotCnt_H2.setInputProtected(true);
        scrnMsg.xxTotCnt_H3.setInputProtected(true);
        scrnMsg.xxTotCnt_H4.setInputProtected(true);
        scrnMsg.xxTotCnt_H5.setInputProtected(true);
        scrnMsg.xxTotCnt_H6.setInputProtected(true);
        scrnMsg.xxTotCnt_H7.setInputProtected(true);
        scrnMsg.xxTotCnt_H8.setInputProtected(true);
        scrnMsg.xxTotCnt_H9.setInputProtected(true);
        scrnMsg.xxTotAmt_H1.setInputProtected(true);
        scrnMsg.xxTotAmt_H2.setInputProtected(true);
        scrnMsg.xxTotAmt_H3.setInputProtected(true);
        scrnMsg.xxTotAmt_H4.setInputProtected(true);
        scrnMsg.xxTotAmt_H5.setInputProtected(true);
        scrnMsg.xxTotAmt_H6.setInputProtected(true);
        scrnMsg.xxTotAmt_H7.setInputProtected(true);
        scrnMsg.xxTotAmt_H8.setInputProtected(true);
        scrnMsg.xxTotAmt_H9.setInputProtected(true);

        // START 2018/01/16 H.Ikeda [QC#22759, ADD]
        scrnMsg.dsAcctNm_BH.setInputProtected(true);
        scrnMsg.locNum_BH.setInputProtected(true);
        // END   2018/01/16 H.Ikeda [QC#22759, ADD]

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(false);
            scrnMsg.B.no(i).rcptNum_B.setInputProtected(true);
//            scrnMsg.B.no(i).rcptChkNum_B.setInputProtected(true);
            scrnMsg.B.no(i).arRcptTrxTpNm_B.setInputProtected(true);
            scrnMsg.B.no(i).rcptDt_B.setInputProtected(true);
            scrnMsg.B.no(i).funcRcptAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).arRcptStsNm_B.setInputProtected(true);
            scrnMsg.B.no(i).arTrxNum_B.setInputProtected(true);
            scrnMsg.B.no(i).arTrxTpNm_B.setInputProtected(true);
            scrnMsg.B.no(i).funcApplyAmt_B.setInputProtected(true);
            scrnMsg.B.no(i).payerCustCd_B.setInputProtected(true);
            scrnMsg.B.no(i).dsAcctNm_B.setInputProtected(true);
            // START 2018/01/16 H.Ikeda [QC#22759, ADD]
            scrnMsg.B.no(i).locNum_B.setInputProtected(true);
            // END   2018/01/16 H.Ikeda [QC#22759, ADD]
        }

//        if (scrnMsg.arBatRcptStsCd_H.getValue().equals(AR_BAT_RCPT_STS.CLOSED)) {
//            scrnMsg.arRcptSrcCd_H.setInputProtected(true);
//            scrnMsg.arBatRcptCnt_H.setInputProtected(true);
//            scrnMsg.arBatRcptAmt_H.setInputProtected(true);
//            scrnMsg.arBatRcptDt_H.setInputProtected(true);
//            scrnMsg.arBatRcptCmntTxt_H.setInputProtected(true);
//            scrnMsg.arLockBoxFileNm_H.setInputProtected(true);
//            scrnMsg.arLockBoxCd_H.setInputProtected(true);
//            scrnMsg.arLockBoxBatNum_H.setInputProtected(true);
//            scrnMsg.xxNum.setInputProtected(true);
//            scrnMsg.arRcptTrxTpCd_BH.setInputProtected(true);
//            scrnMsg.rcptDt_BH.setInputProtected(true);
//            scrnMsg.funcRcptAmt_BH.setInputProtected(true);
//
//            scrnMsg.dsBankCd_H.setInputProtected(true);
//            scrnMsg.bankRteNum_H.setInputProtected(true);
//            scrnMsg.remDsBankAcctPk_H.setInputProtected(true);
//
//            for (int i = 0; i < scrnMsg.B.length(); i++) {
//                scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
//                scrnMsg.B.no(i).funcRcptAmt_B.setInputProtected(true);
//                scrnMsg.B.no(i).rcptChkNum_B.setInputProtected(true);
//            }
//            scrnAppli.setButtonProperties("Click_Add", "Click_Add", "Add", 0, null);
//            scrnAppli.setButtonProperties("Click_Del", "Click_Del", "Del", 0, null);
//            scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
//            scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
//        }

//        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).rcptNum_B)) {
//                setItemVisibility(scrnMsg, "rcptNum_B#" + i, true);
//            } else {
//                setItemVisibility(scrnMsg, "rcptNum_B#" + i, false);
//            }
//        }

//        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).rcptNum_B)) {
//                setItemVisibility(scrnMsg, "rcptNum_B#" + i, true);
//            } else {
//                setItemVisibility(scrnMsg, "rcptNum_B#" + i, false);
//            }
//        }

        NFCL3020CommonLogic.setInputProtect(scrnMsg);
        NFCL3020CommonLogic.setDeleteProtect(scrnMsg);
        
        if (!hasFunctionUpdate(userProfileService)) {
            scrnMsg.setInputProtected(true);
            scrnMsg.B.setInputProtected(true);
            scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        }

        // START 2016/10/07 K.Kojima [QC#13432,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.arBatRcptNm_H)) {
            scrnAppli.setButtonProperties(ATTACHMENT[0], ATTACHMENT[1], ATTACHMENT[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(ATTACHMENT[0], ATTACHMENT[1], ATTACHMENT[2], 0, null);
        }
        // END 2016/10/07 K.Kojima [QC#13432,ADD]
    }

    /**
     * @param scrnMsg NFCL3020BMsg
     */
    public static void clearScrnBackgroundColor(NFCL3020BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFCL3020Scrn00");
    }

    /**
     * Set Item Visibility
     * @param scrnMsg NFCL3020BMsg
     * @param itemNm String
     * @param visibility boolean
     */
    
    public static void setRecptNum(NFCL3020BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).rcptNum_B)) {
                NFCL3020CommonLogic.setItemVisibility(scrnMsg, "rcptNum_B#" + i, true);
            } else {
                NFCL3020CommonLogic.setItemVisibility(scrnMsg, "rcptNum_B#" + i, false);
            }
        }
    }

    public static void setItemVisibility(NFCL3020BMsg scrnMsg, String itemNm, boolean visibility) {
        EZDGUIAttribute itemVisibility = new EZDGUIAttribute("NFCL3020Scrn00", itemNm);
        itemVisibility.setVisibility(visibility);
        scrnMsg.addGUIAttribute(itemVisibility);
    }

    /**
     * 
     * @param scrnMsg
     */
    public static void clearRecptNum(NFCL3020BMsg scrnMsg) {
        for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.clearGUIAttribute("NFCL3020Scrn00", "rcptNum_B#" + i);
        }
    }


    /**
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean hasFunctionUpdate(S21UserProfileService userProfileService) {

        List<String> list = userProfileService.getFunctionCodeListForBizAppId(FUNC_ID);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(FUNCTION_UPDATE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param scrnMsg
     */
    public static void setInputProtect(NFCL3020BMsg scrnMsg) {
        
        // Header
        // Mod Start 2018/01/10 S21_NA#21400
        // if(scrnMsg.arBatRcptStsCd_H.getValue().equals(AR_BAT_RCPT_STS.COMPLETED)) {
        if(scrnMsg.arBatRcptStsCd_H.getValue().equals(AR_BAT_RCPT_STS.CLOSED)) {
        // Mod End 2018/01/10 S21_NA#21400
            scrnMsg.arBatRcptCnt_H.setInputProtected(true);
            scrnMsg.arBatRcptAmt_H.setInputProtected(true);
            scrnMsg.arBatRcptCnt_H.setIndispensable(false);
            scrnMsg.arBatRcptAmt_H.setIndispensable(false);
        } else {
            scrnMsg.arBatRcptCnt_H.setInputProtected(false);
            scrnMsg.arBatRcptAmt_H.setInputProtected(false);
            scrnMsg.arBatRcptCnt_H.setIndispensable(true);
            scrnMsg.arBatRcptAmt_H.setIndispensable(true);
        }
        
        // Detail
        for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            boolean isProtect = false;

            if(scrnMsg.B.no(i).arCashApplyStsCd_B.getValue().equals(AR_CASH_APPLY_STS.PARTIAL) 
                    || scrnMsg.B.no(i).arCashApplyStsCd_B.getValue().equals(AR_CASH_APPLY_STS.APPLIED)
                    || scrnMsg.B.no(i).arCashApplyStsCd_B.getValue().equals(AR_CASH_APPLY_STS.VOID)) {
                isProtect = true;
            }
            if(!scrnMsg.B.no(i).ajeCratCpltFlg_B.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                isProtect = true;
            }

            // START 2018/04/04 H.Ikeda [QC#21737-1, ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).rcptNum_B)) {
                isProtect = true;
            }
            // END   2018/04/04 H.Ikeda [QC#21737-1, ADD]
            
            if(isProtect) {
                scrnMsg.B.no(i).funcRcptAmt_B.setInputProtected(true);
                scrnMsg.B.no(i).funcRcptAmt_B.setIndispensable(false);
            } else {
                scrnMsg.B.no(i).funcRcptAmt_B.setInputProtected(false);
                scrnMsg.B.no(i).funcRcptAmt_B.setIndispensable(true);
            }
        }
    }
    
    /**
     * 
     * @param scrnMsg
     */
    public static void setDeleteProtect(NFCL3020BMsg scrnMsg) {
        for(int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(true);
            if(scrnMsg.B.no(i).arRcptStsCd_B.getValue().equals(AR_RCPT_STS.NEW) || !ZYPCommonFunc.hasValue(scrnMsg.B.no(i).arRcptStsCd_B.getValue())) {
                if(scrnMsg.B.no(i).ajeCratCpltFlg_B.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                    scrnMsg.B.no(i).xxChkBox_B.setInputProtected(false);
                }
            }
        }
    }

    /**
     * 
     * @param scrnMsg
     */
    public static void setTableCursor(NFCL3020BMsg scrnMsg) {
/**/
        scrnMsg.clearGUIAttribute( "NFCL3020Scrn00", ZYPGUITableFocusRule.PREFIX_KEY + NFCL3020Bean.B );
        
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule( "NFCL3020Scrn00", NFCL3020Bean.B);
        scrnMsg.addGUIAttribute( tblFocusRule );
        
        // tHead.Right TBL - 5
        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule("tHead.Right TBL - 5");
        fRule.setNextId(NFCL3020Bean.xxChkBox_B + "#0");
        tblFocusRule.addRule(fRule);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            fRule = new ZYPGUIFocusRule(NFCL3020Bean.xxChkBox_B + "#" + i);
            if (i > 0) {
                fRule.setPrevId(NFCL3020Bean.funcRcptAmt_B + "#" + (i - 1));
            }
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NFCL3020Bean.rcptChkNum_B + "#" + i);
            fRule.setNextId(NFCL3020Bean.funcRcptAmt_B + "#" + (i));
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NFCL3020Bean.funcRcptAmt_B + "#" + i);
            fRule.setPrevId(NFCL3020Bean.rcptChkNum_B + "#" + (i));
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(NFCL3020Bean.funcRcptAmt_B + "#" + i);
            if (i < scrnMsg.B.getValidCount() - 1) {
                fRule.setNextId(NFCL3020Bean.xxChkBox_B + "#" + (i + 1));
            }
            tblFocusRule.addRule(fRule);
        }
/**/
    }
        
}