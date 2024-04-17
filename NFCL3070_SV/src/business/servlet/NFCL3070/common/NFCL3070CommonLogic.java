/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070.common;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.BIZ_ID;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_APL;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_APR;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_CLR;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_DEL;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_DWL;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_NO;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_RJT;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_RST;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_RTN;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_SAV;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_SUB;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_YES;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.FUNC_ID_UPDATE;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.NFCM0897E;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.NFCM0908E;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.NFCM0789E;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RSN_CD;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RSN_CD_LB;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RSN_NAME;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RSN_NAME_LB;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.ZZSM4300E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.servlet.NFCL3070.NFCL3070BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFCL3070CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/12/26   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/07/17   Hitachi         E.Kameishi      Update          QC#27007
 * 2018/08/29   Fujitsu         S.Ohki          Update          QC#27887
 * 2018/11/01   Fujitsu         T.Ogura         Update          QC#29045
 * 2019/07/18   Fujitsu         S.Ohki          Update          QC#51614
 *</pre>
 */
public class NFCL3070CommonLogic {

    /**
     * Initial Common Button and Field properties.
     * @param handler Event Handler
     * @param scrnMsg NFCL3070BMsg
     */
    public static void initCmnBtnField(S21CommonHandler handler, NFCL3070BMsg scrnMsg) {
        // ## Common Button ##
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
        // Parameter Error
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxPgFlg.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        } else {
            if (hasUpdateFuncId(scrnMsg)) {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            }
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        }

        // ## Request Type Section##
        scrnMsg.xxRadioBtn.setInputProtected(false);

        // ## Parameters Section##
        scrnMsg.custIncdtId.setInputProtected(false);
        scrnMsg.origInvNum.setInputProtected(false);
        scrnMsg.xxLinkProt.setValue(ZYPConstant.FLG_ON_Y);
        // START 2018/05/30 E.Kameishi [QC#26229,MOD]
        //scrnMsg.arCrRebilRsnCd.setInputProtected(false);
        scrnMsg.arCrRebilRsnNm.setInputProtected(false);
        scrnMsg.xxLinkProt.setInputProtected(false);
        // END 2018/05/30 E.Kameishi [QC#26229,MOD]
        scrnMsg.arCrRebilCmntTxt.setInputProtected(false);
        scrnMsg.invNum_C.setInputProtected(true);
        scrnMsg.invNum_R.setInputProtected(true);
        scrnMsg.arCrRebilPk_C.setInputProtected(true);

        // ## Additional Comment Section##
        protectAddCommentSection(handler, scrnMsg);

        // ## Credit Only Section##
        protectCreditOnlySection(scrnMsg, true);
    }

    /**
     * Protect Additional Comments Section
     * @param handler Event Handler
     * @param scrnMsg Screen Msg
     */
    public static void protectAddCommentSection(S21CommonHandler handler, NFCL3070BMsg scrnMsg) {
        boolean protectKey = true;
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.arCashApplyEligFlg.getValue())) {
            protectKey = false;
        }

        scrnMsg.arCrRebilAddlCmntTxt.setInputProtected(protectKey);
        handler.setButtonEnabled(BTN_CMN_NO, !protectKey);
        handler.setButtonEnabled(BTN_CMN_YES, !protectKey);
    }

    /**
     * Protect All Field
     * @param handler Event Handler
     * @param scrnMsg Screen Msg
     */
    public static void protectAllField(S21CommonHandler handler, NFCL3070BMsg scrnMsg) {
        // ## Common Button ##
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        scrnMsg.xxLinkProt.clear();

        // ## Request Type Section ##
        scrnMsg.xxRadioBtn.setInputProtected(true);

        // ## Parameters Section ##
        scrnMsg.custIncdtId.setInputProtected(true);
        scrnMsg.origInvNum.setInputProtected(true);
        scrnMsg.xxLinkProt.setInputProtected(true);
        //START 2018/05/30 E.Kameishi [QC#26229, MOD]
        //scrnMsg.arCrRebilRsnCd.setInputProtected(true);
        scrnMsg.arCrRebilRsnNm.setInputProtected(true);
        //END 2018/05/30 E.Kameishi [QC#26229, MOD]
        scrnMsg.arCrRebilCmntTxt.setInputProtected(true);

        // ## Additional Comment Section ##
        scrnMsg.arCrRebilAddlCmntTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_CMN_NO, false);
        handler.setButtonEnabled(BTN_CMN_YES, false);

        // ## Credit Only Section ##
        scrnMsg.arCrTpCd.setInputProtected(true);
        scrnMsg.arCrPct.setInputProtected(true);
        scrnMsg.crRebilAmt_CO.setInputProtected(true);
        scrnMsg.arAutoCashAppFlg.setInputProtected(true);
        scrnMsg.invLineNum.setInputProtected(true);
        scrnMsg.invBolLineNum.setInputProtected(true);
        // START 2018/08/29 S.Ohki [QC#27887, DEL]
//        scrnMsg.invLineSubNum.setInputProtected(true);
//        scrnMsg.invLineSubTrxNum.setInputProtected(true);
        // END 2018/08/29 S.Ohki [QC#27887, DEL]
    }

    /**
     * UnProtect All Field
     * @param handler Event Handler
     * @param scrnMsg Screen Msg
     */
    public static void unprotectAllField(S21CommonHandler handler, NFCL3070BMsg scrnMsg) {
        // ## Common Button ##
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        scrnMsg.xxLinkProt.setInputProtected(true);

        // ## Request Type Section ##
        scrnMsg.xxRadioBtn.setInputProtected(false);

        // ## Parameters Section ##
        scrnMsg.custIncdtId.setInputProtected(false);
        scrnMsg.origInvNum.setInputProtected(false);
        scrnMsg.xxLinkProt.setInputProtected(false);
        //START 2018/05/30 E.Kameishi [QC#26229, MOD]
        //scrnMsg.arCrRebilRsnCd.setInputProtected(false);
        scrnMsg.arCrRebilRsnNm.setInputProtected(false);
        //END 2018/05/30 E.Kameishi [QC#26229, MOD]
        scrnMsg.arCrRebilCmntTxt.setInputProtected(false);

        // ## Additional Comment Section ##
        scrnMsg.arCrRebilAddlCmntTxt.clear();
        scrnMsg.arCrRebilAddlCmntTxt.setInputProtected(true);
        handler.setButtonEnabled(BTN_CMN_NO, false);
        handler.setButtonEnabled(BTN_CMN_YES, false);

        // ## Credit Only Section ##
        scrnMsg.arCrTpCd.setInputProtected(false);
        scrnMsg.arCrPct.setInputProtected(false);
        scrnMsg.crRebilAmt_CO.setInputProtected(false);
        scrnMsg.arAutoCashAppFlg.setInputProtected(false);
        scrnMsg.invLineNum.setInputProtected(false);
        scrnMsg.invBolLineNum.setInputProtected(false);
        // START 2018/08/29 S.Ohki [QC#27887, DEL]
//        scrnMsg.invLineSubNum.setInputProtected(false);
//        scrnMsg.invLineSubTrxNum.setInputProtected(false);
        // END 2018/08/29 S.Ohki [QC#27887, DEL]

    }

    /**
     * Protect Credit Only Section
     * @param scrnMsg Screen Msg
     * @param protectKey boolean
     */
    public static void protectCreditOnlySection(NFCL3070BMsg scrnMsg, boolean protectKey) {
        scrnMsg.arCrTpCd.setInputProtected(protectKey);
        scrnMsg.arCrPct.setInputProtected(protectKey);
        scrnMsg.crRebilAmt_CO.setInputProtected(protectKey);
        scrnMsg.arAutoCashAppFlg.setInputProtected(protectKey);
        scrnMsg.invLineNum.setInputProtected(protectKey);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg.getValue())) {
            protectKey = false;
        } else {
            protectKey = true;
        }
        scrnMsg.invLineNum.setIndispensable(!protectKey);
        scrnMsg.invBolLineNum.setInputProtected(protectKey);
        // START 2018/08/29 S.Ohki [QC#27887, DEL]
//        scrnMsg.invLineSubNum.setInputProtected(protectKey);
//        scrnMsg.invLineSubTrxNum.setInputProtected(protectKey);
        // END 2018/08/29 S.Ohki [QC#27887, DEL]

    }

    /**
     * Clear NumberSection
     * @param scrnMsg Screen Msg
     */
    public static void clearNumberSection(NFCL3070BMsg scrnMsg) {
        scrnMsg.invNum_C.clear();
        scrnMsg.invNum_R.clear();
        scrnMsg.arCrRebilPk_C.clear();
    }

    /**
     * Set Initial Value
     * @param scrnMsg Screen Msg
     */
    public static void setInitialValue(NFCL3070BMsg scrnMsg) {
        scrnMsg.arAutoCashAppFlg.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.invLineNum.clear();
        scrnMsg.invBolLineNum.clear();
        // START 2018/08/29 S.Ohki [QC#27887, DEL]
//        scrnMsg.invLineSubNum.clear();
//        scrnMsg.invLineSubTrxNum.clear();
        // END 2018/08/29 S.Ohki [QC#27887, DEL]
        scrnMsg.arCrPct.clear();
        scrnMsg.crRebilAmt_CO.clear();
        // START 2018/07/17 E.Kameishi [QC#27007,DEL]
        //scrnMsg.arCrTpCd.setValue(AR_CR_TP.RECEIVABLES);
        // END 2018/07/17 E.Kameishi [QC#27007,DEL]
        // START 2019/07/18 S.Ohki [QC#51614,ADD]
        scrnMsg.arCrTpCd.clear();
        // END 2019/07/18 S.Ohki [QC#51614,ADD]
    }

    /**
     * check Percentage and Amount of Credit
     * @param scrnMsg Screen Msg
     * @return boolean
     */
    public static boolean isDuplicateCrPercentAndAmount(NFCL3070BMsg scrnMsg) {

        // error if percentage and amount of credit is both entered
        if (ZYPCommonFunc.hasValue(scrnMsg.crRebilAmt_CO)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.arCrPct)) {
                scrnMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0789E);
                scrnMsg.arCrPct.setErrorInfo(1, NFCM0789E);
                return false;
            }
        }
        return true;
    }

    // START 2019/07/18 S.Ohki [QC#51614,ADD]
    /**
     * Check Type Tax
     * @param scrnMsg Screen Msg
     */
    public static void isCheckTypeTax(NFCL3070BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.arCrPct)) {
        	scrnMsg.arCrPct.setErrorInfo(1, NFCM0908E, new String[] {scrnMsg.arCrPct.getNameForMessage() });
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.crRebilAmt_CO)) {
        	scrnMsg.crRebilAmt_CO.setErrorInfo(1, NFCM0908E, new String[] {scrnMsg.crRebilAmt_CO.getNameForMessage() });
        }
    }
    // END 2019/07/18 S.Ohki [QC#51614,ADD]

    /**
     * addCheckItemAllItems
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemAllItems(NFCL3070BMsg scrnMsg) {
        // ### Request Section ###
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);

        // ### Parameter Section ###
        scrnMsg.addCheckItem(scrnMsg.custIncdtId);
        scrnMsg.addCheckItem(scrnMsg.origInvNum);
        //START 2018/05/30 E.Kameishi [QC#26229, MOD]
        //scrnMsg.addCheckItem(scrnMsg.arCrRebilRsnCd);
        scrnMsg.addCheckItem(scrnMsg.arCrRebilRsnNm);
        //END 2018/05/30 E.Kameishi [QC#26229, MOD]
        scrnMsg.addCheckItem(scrnMsg.arCrRebilCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.invNum_C);
        scrnMsg.addCheckItem(scrnMsg.invNum_R);
        scrnMsg.addCheckItem(scrnMsg.arCrRebilPk_C);

        // ### Additional Comment Section ###
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.arCashApplyEligFlg.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.arCrRebilAddlCmntTxt);
        }

        // ### Credit Only Section ###
        if (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn) && RQST_TP_CREDIT_ONLY.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            scrnMsg.addCheckItem(scrnMsg.invLineNum);
            scrnMsg.addCheckItem(scrnMsg.arCrTpCd);
            scrnMsg.addCheckItem(scrnMsg.arCrPct);
            scrnMsg.addCheckItem(scrnMsg.crRebilAmt_CO);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.invBolLineNum);
                // START 2018/08/29 S.Ohki [QC#27887, DEL]
//                scrnMsg.addCheckItem(scrnMsg.invLineSubNum);
//                scrnMsg.addCheckItem(scrnMsg.invLineSubTrxNum);
                // END 2018/08/29 S.Ohki [QC#27887, DEL]
            }
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * setReasonWhereList
     * @param scrnMsg NFCL3070BMsg
     * @return List<Objext[]> List
     */
    public static List<Object[]> setReasonWhereList(NFCL3070BMsg scrnMsg) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = RSN_CD_LB;
        whereArray0[1] = RSN_CD;
        //START 2018/05/30 E.Kameishi [QC#26229, DEL]
        //if (ZYPCommonFunc.hasValue(scrnMsg.arCrRebilRsnCd)) {
        //    whereArray0[2] = scrnMsg.arCrRebilRsnCd.getValue();
        //}
        //END 2018/05/30 E.Kameishi [QC#26229, DEL]
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = RSN_NAME_LB;
        whereArray1[1] = RSN_NAME;
        //START 2018/05/30 E.Kameishi [QC#26229, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.arCrRebilRsnNm)) {
            whereArray1[2] = scrnMsg.arCrRebilRsnNm.getValue();
        }
        //END 2018/05/30 E.Kameishi [QC#26229, ADD]
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }

    /**
     * setReasonTblColumnList
     * @param scrnMsg NFCL3070BMsg
     * @return List<Objext[]> List
     */
    public static List<Object[]> setReasonTblColumnList(NFCL3070BMsg scrnMsg) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = RSN_CD_LB;
        whereArray0[1] = RSN_CD;
        whereArray0[2] = BigDecimal.valueOf(20);
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = RSN_NAME_LB;
        whereArray1[1] = RSN_NAME;
        whereArray1[2] = BigDecimal.valueOf(60);
        whereArray1[3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        return whereList;
    }

    /**
     * setReasonSortList
     * @param scrnMsg NFCL3070BMsg
     * @return List<Objext[]> List
     */
    public static List<Object[]> setReasonSortList(NFCL3070BMsg scrnMsg) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[2];
        whereArray0[0] = RSN_CD;
        whereArray0[1] = "ASC";
        whereList.add(whereArray0);

        return whereList;
    }

    // Start 2016/12/26 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL3070BMsg scrnMsg) {
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
    // End  2016/12/26 H.Ikeda [QC#12865,ADD]

    // START 2018/11/01 T.Ogura [QC#29045,ADD]
    /**
     * checkTypeOfCreditAndCreditAmount
     * @param scrnMsg Screen Msg
     */
    public static void checkTypeOfCreditAndCreditAmount(NFCL3070BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.arCrTpCd) && ZYPCommonFunc.hasValue(scrnMsg.crRebilAmt_CO)) {
            scrnMsg.arCrTpCd.setErrorInfo(1, NFCM0897E);
        }
    }
    // END   2018/11/01 T.Ogura [QC#29045,ADD]
}
