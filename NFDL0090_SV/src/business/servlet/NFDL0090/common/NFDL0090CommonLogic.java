/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090.common;

import java.math.BigDecimal;
import java.util.List;

import business.servlet.NFDL0090.NFDL0090BMsg;
import business.servlet.NFDL0090.constant.NFDL0090Constant;
import static business.servlet.NFDL0090.constant.NFDL0090Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2017/01/04   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/01/15   Hitachi         Y.Takeno        Update          QC#21427
 * 2018/07/17   Hitachi         Y.Takeno        Create          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno        Update          QC#26989-1
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 * 2019/02/05   Hitachi         H.Umeda         Update          QC#30162
 * 2021/03/02   CITS            A.Raguero       Update          QC#56205
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 * 2022/12/02   Hitachi         S.Fujita        Update          QC#60034
 *</pre>
 */
public class NFDL0090CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NFDL0090BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NFDL0090BMsg scrnMsg) {

        int enable = 0;
        // START 2018/09/11 [QC#24884, MOD]
        // if (scrnMsg.A.getValidCount() > 0) {
        if (scrnMsg.A.getValidCount() > 0 && !ZYPCommonFunc.hasValue(scrnMsg.arWrtOffRqstPk_H1)) {
            handler.setButtonEnabled("Check_All", true);
            handler.setButtonEnabled("Un_Check_All", true);
            // START 2019/02/05 [QC#30162, ADD]
            handler.setButtonEnabled("WrtOffTotalCalc", true);
            // END   2019/02/05 [QC#30162, ADD]
            enable = 1;
        } else if (scrnMsg.A.getValidCount() > 0 && ZYPCommonFunc.hasValue(scrnMsg.arWrtOffRqstPk_H1)) {
            handler.setButtonEnabled("Check_All", false);
            handler.setButtonEnabled("Un_Check_All", false);
            // START 2019/02/05 [QC#30162, ADD]
            handler.setButtonEnabled("WrtOffTotalCalc", false);
            scrnMsg.xxDealApplyAmtNum_H1.setValue((int) 0.00);
            // END   2019/02/05 [QC#30162, ADD]
            enable = 1;
        } else {
            handler.setButtonEnabled("Check_All", false);
            handler.setButtonEnabled("Un_Check_All", false);
            // START 2019/02/05 [QC#30162, ADD]
            handler.setButtonEnabled("WrtOffTotalCalc", false);
            scrnMsg.xxDealApplyAmtNum_H1.setValue((int) 0.00);
            // END   2019/02/05 [QC#30162, ADD]
        }
        // END   2018/09/11 [QC#24884, MOD]
        // START 2018/09/11 [QC#24884, MOD]
        handler.setButtonEnabled("Click_Search", true);
        handler.setButtonEnabled("OpenWin_AttachFile", false);
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(scrnMsg.arAdjTpCd_FS.getValue())) {
            handler.setButtonEnabled(BTN_A, true);
        } else {
            handler.setButtonEnabled(BTN_A, false);
        }
        // END   2022/11/24 S.Naya [QC#57252,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.arWrtOffRqstPk_H1)) {
            handler.setButtonEnabled("Click_Search", false);
            handler.setButtonEnabled("OpenWin_AttachFile", true);
            // START 2022/11/24 S.Naya [QC#57252,ADD]
            handler.setButtonEnabled(BTN_A, false);
            // END   2022/11/24 S.Naya [QC#57252,ADD]
        }
        // END   2018/09/11 [QC#24884, MOD]

        handler.setButtonProperties(NFDL0090Constant.BTN_01_SAV_NAME, NFDL0090Constant.BTN_01_SAV_GUARD, NFDL0090Constant.BTN_01_SAV_LABEL, 0, null);
        // Start 2017/01/04 H.Ikeda [QC#12865,MOD]
        // START 2018/09/11 [QC#24884, MOD]
        // if (hasUpdateFuncId(scrnMsg)) {
        if (hasUpdateFuncId(scrnMsg) && !ZYPCommonFunc.hasValue(scrnMsg.arWrtOffRqstPk_H1)) {
        // END   2018/09/11 [QC#24884, MOD]
            handler.setButtonProperties(NFDL0090Constant.BTN_02_SUB_NAME, NFDL0090Constant.BTN_02_SUB_GUARD, NFDL0090Constant.BTN_02_SUB_LABEL, enable, null);
        } else {
            handler.setButtonProperties(NFDL0090Constant.BTN_02_SUB_NAME, NFDL0090Constant.BTN_02_SUB_GUARD, NFDL0090Constant.BTN_02_SUB_LABEL, 0, null);
        }
        // End   2017/01/04 H.Ikeda [QC#12865,MOD]
        handler.setButtonProperties(NFDL0090Constant.BTN_03_APL_NAME, NFDL0090Constant.BTN_03_APL_GUARD, NFDL0090Constant.BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_04_APR_NAME, NFDL0090Constant.BTN_04_APR_GUARD, NFDL0090Constant.BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_05_REJ_NAME, NFDL0090Constant.BTN_05_REJ_GUARD, NFDL0090Constant.BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_06_DWL_NAME, NFDL0090Constant.BTN_06_DWL_GUARD, NFDL0090Constant.BTN_06_DWL_LABEL, enable, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_07_DEL_NAME, NFDL0090Constant.BTN_07_DEL_GUARD, NFDL0090Constant.BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_08_CLE_NAME, NFDL0090Constant.BTN_08_CLE_GUARD, NFDL0090Constant.BTN_08_CLE_LABEL, enable, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_09_RST_NAME, NFDL0090Constant.BTN_09_RST_GUARD, NFDL0090Constant.BTN_09_RST_LABEL, 0, null);
        handler.setButtonProperties(NFDL0090Constant.BTN_10_RTR_NAME, NFDL0090Constant.BTN_10_RTR_GUARD, NFDL0090Constant.BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * checkDetail.
     * @param scrnMsg NFDL0090BMsg
     */
    public static void checkDetail(NFDL0090BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).xxDealApplyAmtNum_A1.isInputProtected()) {
                continue;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }

            if (scrnMsg.A.no(i).xxDealApplyAmtNum_A1.isClear()
                    || BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) == 0) {
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, NFDL0090Constant.NFDM0006E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                continue;
            }
            if ((BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) <= 0
                    && BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue()) > 0)
                || (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) > 0
                    && BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue()) <= 0)) {
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, NFDL0090Constant.NFDM0006E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
            }
            if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue()) > 0) {
                if (scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue().compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) > 0) {
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, NFDL0090Constant.NFDM0011E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                }
            } else {
                if (scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue().compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) < 0) {
                    scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, NFDL0090Constant.NFDM0011E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
                }
            }
            BigDecimal sub = scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.getValue().subtract(scrnMsg.A.no(i).dealApplyAdjRsvdAmt_A1.getValue());
            if (sub.compareTo(scrnMsg.A.no(i).xxDealApplyAmtNum_A1.getValue()) < 0) {
                scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setErrorInfo(1, NFDL0090Constant.NFDM0011E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDealApplyAmtNum_A1);
            }
        }
        scrnMsg.putErrorScreen();
    }
    /**
     * protectDetail.
     * @param scrnMsg NFDL0090BMsg
     */
    public static void protectDetail(NFDL0090BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // START 2018/09/11 [QC#24884, MOD]
            // if (AR_TRX_TP.INVOICE.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())
            //         || AR_TRX_TP.DEBIT_MEMO.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())
            //         || AR_TRX_TP.DEDUCTION.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.arWrtOffRqstPk_H1) && (
                    AR_TRX_TP.INVOICE.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())
                        || AR_TRX_TP.DEBIT_MEMO.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())
                        || AR_TRX_TP.DEDUCTION.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue()))) {
            // END   2018/09/11 [QC#24884, MOD]
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                
                //---- start 2016/03/31 QC#6386 check only when editable
                //---- start added 2016/02/19
                  // if reserved amount is not zero, another request should not be entered.
                  if (scrnMsg.A.no(i).dealApplyAdjRsvdAmt_A1.getValue().compareTo(BigDecimal.ZERO) == 0) {
                      // START 2018/01/15 [QC#21427, DEL]
                      // scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(false);
                      // END   2018/01/15 [QC#21427, DEL]
                      scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                  } else {
                      // START 2018/01/15 [QC#21427, DEL]
                      // scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
                      // END   2018/01/15 [QC#21427, DEL]
                      scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                  }
                  
                  //---- end 2016/02/19
                  //---- end 2016/03/31
                  
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);

            }

            // START 2018/01/15 [QC#21427, MOD]
            // if (AR_TRX_TP.INVOICE.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())) {
            //     scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(false);
            // } else {
            //     scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            // }
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setInputProtected(true);
            // END   2018/01/15 [QC#21427, MOD]

        }
    }

    // Start 2017/01/07 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFDL0090BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NFDL0090Constant.BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(NFDL0090Constant.ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        // START 2022/12/02 S.Fujita [QC#60034,MOD]
        //  if (funcList.contains("NFDL0090T020")) {
        //      return true;
        //  }
        //  return false;
                
        if (funcList.contains("NFDL0090T010") && funcList.size() == 1){
            return false;
        }
        return true;
        // END 2022/12/02 S.Fujita [QC#60034,MOD]
}
    // End  2017/01/07 H.Ikeda [QC#12865,ADD]

    // START 2018/07/17 [QC#26989,  ADD]
    /**
     * checkDetail.
     * @param scrnMsg NFDL0090BMsg
     */
    public static void checkHeader(NFDL0090BMsg scrnMsg) {
        // START 2018/07/17 [QC#26989-1, MOD]
        scrnMsg.addCheckItem(scrnMsg.xxQueryFltrTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum_H1);
        scrnMsg.putErrorScreen();
        // END   2018/07/17 [QC#26989-1, MOD]
    }
    // END   2018/07/17 [QC#26989,  ADD]

    // START 2018/09/11 [QC#24884, ADD]
    /**
     * protectHeader
     * @param scrnMsg NFDL0090BMsg
     */
    public static void protectHeader(NFDL0090BMsg scrnMsg) {
        // START 2021/03/02 A.Raguero [QC#56205, ADD]
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        // END 2021/03/02 A.Raguero [QC#56205, ADD]
        scrnMsg.xxQueryFltrTxt_H1.setInputProtected(false);
        scrnMsg.custIssPoNum_H1.setInputProtected(false);
        scrnMsg.arAdjTpCd_FS.setInputProtected(false);
        scrnMsg.arWrtOffNoteCd_FS.setInputProtected(false);
        scrnMsg.arWrtOffNoteTxt_FS.setInputProtected(false);
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(scrnMsg.arAdjTpCd_FS.getValue())) {
            scrnMsg.xxCmntTxt_FS.setInputProtected(false);
        } else {
            scrnMsg.xxCmntTxt_FS.setInputProtected(true);
        }
        // END   2022/11/24 S.Naya [QC#57252,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.arWrtOffRqstPk_H1)) {
            scrnMsg.xxQueryFltrTxt_H1.setInputProtected(true);
            scrnMsg.custIssPoNum_H1.setInputProtected(true);
            scrnMsg.arAdjTpCd_FS.setInputProtected(true);
            scrnMsg.arWrtOffNoteCd_FS.setInputProtected(true);
            scrnMsg.arWrtOffNoteTxt_FS.setInputProtected(true);
            // START 2022/11/24 S.Naya [QC#57252,ADD]
            scrnMsg.xxCmntTxt_FS.setInputProtected(true);
            // END   2022/11/24 S.Naya [QC#57252,ADD]
        }
    }
    // END   2018/09/11 [QC#24884, ADD]
    // START 2022/11/24 S.Naya [QC#57252,ADD]
    /**
     * Get Param NMAL2550 For Charge Account
     * @param scrnMsg NFDL0090BMsg
     * @return Param NMAL2550 For Payment Term
     */
    public static Object[] getParamForChargeAccount(NFDL0090BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        if (!splitCOA9Seg(scrnMsg)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, BIZ_ID);

        Object[] params = new Object[PARAM_INDEX_10];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_I0;
        params[index++] = scrnMsg.xxPopPrm_I1;
        params[index++] = scrnMsg.xxPopPrm_I7;
        params[index++] = scrnMsg.xxPopPrm_I2;
        params[index++] = scrnMsg.xxPopPrm_I3;
        params[index++] = scrnMsg.xxPopPrm_I4;
        params[index++] = scrnMsg.xxPopPrm_I5;
        params[index++] = scrnMsg.xxPopPrm_I6;
        params[index++] = scrnMsg.xxPopPrm_I8;
        params[index++] = scrnMsg.xxPopPrm_I9;
        return params;
    }

    /**
     * Clear PopUpParam
     * @param scrnMsg NFDL0090BMsg
     */
    private static void clearPopUpParam(NFDL0090BMsg scrnMsg) {
        scrnMsg.xxPopPrm_I0.clear();
        scrnMsg.xxPopPrm_I1.clear();
        scrnMsg.xxPopPrm_I2.clear();
        scrnMsg.xxPopPrm_I3.clear();
        scrnMsg.xxPopPrm_I4.clear();
        scrnMsg.xxPopPrm_I5.clear();
        scrnMsg.xxPopPrm_I6.clear();
        scrnMsg.xxPopPrm_I7.clear();
        scrnMsg.xxPopPrm_I8.clear();
        scrnMsg.xxPopPrm_I9.clear();
    }

    /**
     * splitCOA9Seg
     * @param scrnMsg NFDL0090BMsg
     * @return boolean
     */
    private static boolean splitCOA9Seg(NFDL0090BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_FS)) {
            // Set Default Value
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, scrnMsg.coaCmpyCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, scrnMsg.coaBrCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, scrnMsg.coaCcCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, scrnMsg.coaAcctCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, scrnMsg.coaProdCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, scrnMsg.coaChCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, scrnMsg.coaAfflCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, scrnMsg.coaProjCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, scrnMsg.coaExtnCd_DF.getValue());

            return true;
        }

        String coa[] = scrnMsg.xxCmntTxt_FS.getValue().split("\\.", PARAM_INDEX_9);

        if(coa.length != PARAM_INDEX_9) {
            String errMsg = "9 segments format";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            scrnMsg.xxCmntTxt_FS.setErrorInfo(1, NFBM0041E, new String[]{errMsg});
            return false;
        }

        int index = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, coa[index++]);

        return true;
    }
    // END   2022/11/24 S.Naya [QC#57252,ADD]
}
