/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3140;

import static business.blap.NFCL3140.constant.NFCL3140Constant.*;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3140.common.NFCL3140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#12142
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13200
 * 2016/11/25   Hitachi         J.Kim           Update          QC#16240
 * 2016/11/30   Fujitsu         T.Murai         Update          QC#15823
 *</pre>
 */
public class NFCL3140BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFCL3140CMsg cMsg = (NFCL3140CMsg) arg0;
        super.preDoProcess(arg0, arg1);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3140Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3140Scrn00_CMN_Submit((NFCL3140CMsg) cMsg);
            }
        } finally {
            super.postDoProcess(arg0, arg1);
        }
    }

    private void doProcess_NFCL3140Scrn00_CMN_Submit(NFCL3140CMsg cMsg) {
        boolean checkResult = true;

        // Check : Name
        if (!NFCL3140CommonLogic.checkSameNmData(cMsg)) {
            cMsg.dsInvTpNm.setErrorInfo(1, NFCM0580E, new String[] {"Name" });
            checkResult = false;
        }

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Check : Class, Associated Credit Type
        if (INV_TP.INVOICE.equals(cMsg.invTpCd_SV.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.dsInvTpCd_AC)) {
                // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                cMsg.dsInvTpNm_AC.setErrorInfo(1, ZZM9000E, new String[] {"Associated Credit Type" });
                // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                checkResult = false;
            } else if (ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
                if (!ZYPCodeDataUtil.hasCodeValue(DS_INV_TP.class, glblCmpyCd, cMsg.dsInvTpCd_AC.getValue())) {
                    // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                    cMsg.dsInvTpNm_AC.setErrorInfo(1, NFCM0841E);
                    // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                    checkResult = false;
                }
            }
        } else if (INV_TP.CREDIT_MEMO.equals(cMsg.invTpCd_SV.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.dsInvTpCd_AC)) {
                // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                cMsg.dsInvTpNm_AC.setErrorInfo(1, NFCM0836E, new String[] {"Class is " + ZYPCodeDataUtil.getName(INV_TP.class, glblCmpyCd, cMsg.invTpCd_SV.getValue()), "Associated Credit Type" });
                // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
                checkResult = false;
            }
        }

        // Check : Active Associated Credit Type
        if (ZYPCommonFunc.hasValue(cMsg.dsInvTpCd) && INV_TP.CREDIT_MEMO.equals(cMsg.invTpCd_SV.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg.getValue())) {
            if (!NFCL3140CommonLogic.checkCrDsInvTpCd(cMsg)) {
                cMsg.actvFlg.setErrorInfo(1, NFCM0840E);
                checkResult = false;
            }
        }

        // START 2016/08/09 K.Kojima [QC#13200,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.autoInvNumFlg) && ZYPConstant.FLG_ON_Y.equals(cMsg.autoInvNumFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.autoSeqCd_SV)) {
                cMsg.autoSeqCd_SV.setErrorInfo(1, ZZM9000E, new String[] {"Auto Sequence" });
                checkResult = false;
            }
        }
        // END 2016/08/09 K.Kojima [QC#13200,ADD]

        // Check : Grouping Attributed
        if (!NFCL3140CommonLogic.checkGroupingAttributed(cMsg, NFCM0580E, new String[] {"Grouping Attributed" })) {
            checkResult = false;
        }

        // Check : Default Accounting

        // [Receivable]
        if (!NFCL3140CommonLogic.checkCoaCmpyAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaCmpyCd.getValue())) {
            cMsg.arCoaCmpyCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_COMPANY });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaBrAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaBrCd.getValue())) {
            cMsg.arCoaBrCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_BRANCH });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaCcCdAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaCcCd.getValue())) {
            cMsg.arCoaCcCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_COST_CENTER });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAcctAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaAcctCd.getValue())) {
            cMsg.arCoaAcctCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_ACCOUNT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProdAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaProdCd.getValue())) {
            cMsg.arCoaProdCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_PRODUCT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaChAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaChCd.getValue())) {
            cMsg.arCoaChCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_CHANNEL });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAfflAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaAfflCd.getValue())) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // cMsg.arCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_AFFILIATE });
            cMsg.arCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_INTERCOMPANY });
            // END 2016/11/25 J.Kim [QC#16240,MOD]  
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProjAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaProjCd.getValue())) {
            cMsg.arCoaProjCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_MDISE_TYPE });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaExtnAndEligCoaSegPtrn(glblCmpyCd, cMsg.arCoaExtnCd.getValue())) {
            cMsg.arCoaExtnCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_RECEIVABLE_BUSINESS_UNIT });
            checkResult = false;
        }

        // [Revenue]
        if (!NFCL3140CommonLogic.checkCoaCmpyAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaCmpyCd.getValue())) {
            cMsg.slsCoaCmpyCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_COMPANY });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaBrAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaBrCd.getValue())) {
            cMsg.slsCoaBrCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_BRANCH });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaCcCdAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaCcCd.getValue())) {
            cMsg.slsCoaCcCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_COST_CENTER });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAcctAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaAcctCd.getValue())) {
            cMsg.slsCoaAcctCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_ACCOUNT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProdAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaProdCd.getValue())) {
            cMsg.slsCoaProdCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_PRODUCT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaChAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaChCd.getValue())) {
            cMsg.slsCoaChCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_CHANNEL });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAfflAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaAfflCd.getValue())) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // cMsg.slsCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_AFFILIATE });
            cMsg.slsCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_INTERCOMPANY });
            // END 2016/11/25 J.Kim [QC#16240,MOD]
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProjAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaProjCd.getValue())) {
            cMsg.slsCoaProjCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_MDISE_TYPE });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaExtnAndEligCoaSegPtrn(glblCmpyCd, cMsg.slsCoaExtnCd.getValue())) {
            cMsg.slsCoaExtnCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_REVENUE_BUSINESS_UNIT });
            checkResult = false;
        }

        // [Tax]
        if (!NFCL3140CommonLogic.checkCoaCmpyAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaCmpyCd.getValue())) {
            cMsg.taxCoaCmpyCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_COMPANY });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaBrAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaBrCd.getValue())) {
            cMsg.taxCoaBrCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_BRANCH });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaCcCdAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaCcCd.getValue())) {
            cMsg.taxCoaCcCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_COST_CENTER });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAcctAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaAcctCd.getValue())) {
            cMsg.taxCoaAcctCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_ACCOUNT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProdAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaProdCd.getValue())) {
            cMsg.taxCoaProdCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_PRODUCT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaChAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaChCd.getValue())) {
            cMsg.taxCoaChCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_CHANNEL });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAfflAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaAfflCd.getValue())) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // cMsg.taxCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_AFFILIATE });
            cMsg.taxCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_INTERCOMPANY });
            // END 2016/11/25 J.Kim [QC#16240,MOD]
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProjAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaProjCd.getValue())) {
            cMsg.taxCoaProjCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_MDISE_TYPE });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaExtnAndEligCoaSegPtrn(glblCmpyCd, cMsg.taxCoaExtnCd.getValue())) {
            cMsg.taxCoaExtnCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_TAX_BUSINESS_UNIT });
            checkResult = false;
        }

        // [Unearned Revenue]
        if (!NFCL3140CommonLogic.checkCoaCmpyAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaCmpyCd.getValue())) {
            cMsg.unEarnCoaCmpyCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_COMPANY });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaBrAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaBrCd.getValue())) {
            cMsg.unEarnCoaBrCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_BRANCH });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaCcCdAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaCcCd.getValue())) {
            cMsg.unEarnCoaCcCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_COST_CENTER });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAcctAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaAcctCd.getValue())) {
            cMsg.unEarnCoaAcctCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_ACCOUNT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProdAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaProdCd.getValue())) {
            cMsg.unEarnCoaProdCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_PRODUCT });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaChAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaChCd.getValue())) {
            cMsg.unEarnCoaChCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_CHANNEL });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaAfflAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaAfflCd.getValue())) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // cMsg.unEarnCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_AFFILIATE });
            cMsg.unEarnCoaAfflCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_INTERCOMPANY });
            // END 2016/11/25 J.Kim [QC#16240,MOD]
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaProjAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaProjCd.getValue())) {
            cMsg.unEarnCoaProjCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_MDISE_TYPE });
            checkResult = false;
        }
        if (!NFCL3140CommonLogic.checkCoaExtnAndEligCoaSegPtrn(glblCmpyCd, cMsg.unEarnCoaExtnCd.getValue())) {
            cMsg.unEarnCoaExtnCd.setErrorInfo(1, NZZM0010E, new String[] {SCREEN_UNEARNED_REVENUE_BUSINESS_UNIT });
            checkResult = false;
        }

        if (checkResult == false) {
            return;
        }

        String dsInvTpCd = cMsg.dsInvTpCd.getValue();

        // insert/update DS_INV_TP
        if (ZYPCommonFunc.hasValue(cMsg.dsInvTpCd)) {
            Map<String, Object> beforeData = NFCL3140CommonLogic.getBeforeData(cMsg);
            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), (String) beforeData.get("EZUPTIME"), (String) beforeData.get("EZUPTIMEZONE"))) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }
            boolean result = true; // ADD 2016/11/30 [QC#15823]
            if (ZYPConstant.FLG_ON_1.equals((String) beforeData.get("EZCANCELFLAG"))) {
                // MOD 2016/11/30 [QC#15823]
                // NFCL3140CommonLogic.insertDsInvTp(cMsg, cMsg.dsInvTpCd.getValue(), (BigDecimal) beforeData.get("DS_INV_TP_SORT_NUM"));
                result = NFCL3140CommonLogic.insertDsInvTp(cMsg, cMsg.dsInvTpCd.getValue(), (BigDecimal) beforeData.get("DS_INV_TP_SORT_NUM"));
            } else {
                // MOD 2016/11/30 [QC#15823]
                // NFCL3140CommonLogic.updaetDsInvTp(cMsg, (BigDecimal) beforeData.get("DS_INV_TP_SORT_NUM"));
                result = NFCL3140CommonLogic.updaetDsInvTp(cMsg, (BigDecimal) beforeData.get("DS_INV_TP_SORT_NUM"));
            }
            // START 2016/11/30 [QC#15823, ADD]
            if (!result) {
                return;
            }
            // END 2016/11/30 [QC#15823, ADD]
            
        } else {
            Map<String, Object> maxData = NFCL3140CommonLogic.getMaxData(cMsg);
            String maxDsInvTpCd = (String) maxData.get("MAX_DS_INV_TP_CD");
            BigDecimal maxDsInvTpSortNum = (BigDecimal) maxData.get("MAX_DS_INV_TP_SORT_NUM");
            if ("9999".equals(maxDsInvTpCd)) {
                cMsg.setMessageInfo(NFCM0573E);
                return;
            }
            int intValue = new Integer(maxDsInvTpCd).intValue();
            intValue++;
            String newDsInvTpCd = ZYPCommonFunc.leftPad(String.valueOf(intValue), 4, "0");
            // MOD 2016/11/30 [QC#15823]
            // NFCL3140CommonLogic.insertDsInvTp(cMsg, newDsInvTpCd, maxDsInvTpSortNum.add(BigDecimal.ONE));
            boolean result = NFCL3140CommonLogic.insertDsInvTp(cMsg, newDsInvTpCd, maxDsInvTpSortNum.add(BigDecimal.ONE));

            // START 2016/11/30 [QC#15823, ADD]
            if (!result) {
                return;
            }
            // END 2016/11/30 [QC#15823, ADD]
            dsInvTpCd = newDsInvTpCd;
        }

        // delete/insert DS_INV_GRP_ATTRB
        if (ZYPCommonFunc.hasValue(cMsg.dsInvTpCd)) {
            NFCL3140CommonLogic.deleteDsInvGrpAttrb(cMsg);
        }
        // START 2016/11/30[QC#15823, MOD]
        //NFCL3140CommonLogic.insertDsInvGrpAttrb(cMsg, dsInvTpCd);
        boolean result = NFCL3140CommonLogic.insertDsInvGrpAttrb(cMsg, dsInvTpCd);
        if (!result) {
            return;
        }
        // END 2016/11/30[QC#15823, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsInvTpCd, dsInvTpCd);

    }
}
