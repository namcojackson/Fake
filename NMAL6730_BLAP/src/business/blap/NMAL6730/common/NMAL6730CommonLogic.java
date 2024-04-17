/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6730.common;

import static business.blap.NMAL6730.constant.NMAL6730Constant.BUSINESS_ID;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0072E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0075E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM0289E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8075E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.NMAM8121E;
import static business.blap.NMAL6730.constant.NMAL6730Constant.ZZM9001E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NMAL6730.NMAL6730CMsg;
import business.blap.NMAL6730.NMAL6730Query;
import business.blap.NMAL6730.NMAL6730SMsg;
import business.blap.NMAL6730.NMAL6730_ACMsg;
import business.blap.NMAL6730.NMAL6730_KCMsg;
import business.blap.NMAL6730.NMAL6730_KCMsgArray;
import business.blap.NMAL6730.constant.NMAL6730Constant;
import business.db.AR_STMT_ISS_CYCLETMsg;
import business.db.CLT_CUST_TPTMsg;
import business.db.COA_CHTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.CUST_BLLG_VCLETMsg;
import business.db.DS_ACCT_REF_ATTRBTMsg;
import business.db.DS_CUST_INV_RULETMsg;
import business.db.S21_PSNTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2016/02/18   Fujitsu         C.Tanaka        Update          QC#2440
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 *</pre>
 */
public class NMAL6730CommonLogic {

    public static COA_CHTMsg findCoaCh(String glblCmpyCd, String coaChCd) {
        COA_CHTMsg prmTMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.coaChCd, coaChCd);
        return (COA_CHTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public static CLT_CUST_TPTMsg findCltCustTp(String glblCmpyCd, String cltCustTp) {
        CLT_CUST_TPTMsg prmTMsg = new CLT_CUST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cltCustTpCd, cltCustTp);
        return (CLT_CUST_TPTMsg) S21CacheTBLAccessor.findByKey(prmTMsg);
    }

    public static CUST_BLLG_VCLETMsg findCustBllgVcle(String glblCmpyCd, String custBllgVcleCd) {
        CUST_BLLG_VCLETMsg prmTMsg = new CUST_BLLG_VCLETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.custBllgVcleCd, custBllgVcleCd);
        return (CUST_BLLG_VCLETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public static DS_CUST_INV_RULETMsg findDsCustInvRuleForUpdate(String glblCmpyCd, BigDecimal dsCustInvRulePk) {
        DS_CUST_INV_RULETMsg prmTMsg = new DS_CUST_INV_RULETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCustInvRulePk, dsCustInvRulePk);
        return (DS_CUST_INV_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public static S21_PSNTMsg findS21Psn(String glblCmpyCd, String psnCd) {
        S21_PSNTMsg prmTMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.psnCd, psnCd);
        return (S21_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public static DS_ACCT_REF_ATTRBTMsg findDsAcctRefAttrbForUpdate(String glblCmpyCd, BigDecimal dsAcctRefAttrbPk) {
        DS_ACCT_REF_ATTRBTMsg prmTMsg = new DS_ACCT_REF_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAcctRefAttrbPk, dsAcctRefAttrbPk);
        return (DS_ACCT_REF_ATTRBTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public static String getPsnNm(String glblCmpyCd, String psnCd) {

        S21_PSNTMsg psnMsg = (S21_PSNTMsg) findS21Psn(glblCmpyCd, psnCd);
        if (psnMsg != null) {
            String firstNm = psnMsg.psnFirstNm.getValue();
            String lastNm = psnMsg.psnLastNm.getValue();

            if (ZYPCommonFunc.hasValue(firstNm)) {
                return firstNm.concat(" ").concat(lastNm).trim();
            }
        }
        return "";
    }

    // QC#7781
    public static String getCtacPsnNm(String glblCmpyCd, String ctacPsnPk) {
        CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, new BigDecimal(ctacPsnPk));
        tMsg = (CTAC_PSNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            String firstNm = tMsg.ctacPsnFirstNm.getValue();
            String lastNm = tMsg.ctacPsnLastNm.getValue();
            if (ZYPCommonFunc.hasValue(firstNm)) {
                return firstNm.concat(" ").concat(lastNm).trim();
            }
        }
        return "";
    }

    /**
     * clearCMsg
     * @param cMsg NMAL6730CMsg
     */
    public static void clearCMsg(NMAL6730CMsg cMsg) {
        ZYPTableUtil.clear(cMsg.A);
    }

    public static String getArStmtIssDay(String glblCmpyCd, String getArStmtIssCd) {
        String arStmtIssDay = "";
        if (ZYPCommonFunc.hasValue(getArStmtIssCd)) {
            AR_STMT_ISS_CYCLETMsg arStmtIssCycleTMsg = new AR_STMT_ISS_CYCLETMsg();
            arStmtIssCycleTMsg = (AR_STMT_ISS_CYCLETMsg) ZYPCodeDataUtil.findByCode(AR_STMT_ISS_CYCLE.class, glblCmpyCd, getArStmtIssCd);
            if (arStmtIssCycleTMsg != null) {
                arStmtIssDay = arStmtIssCycleTMsg.arStmtIssDay.getValue();
            }
        }

        return arStmtIssDay;
    }

    public static boolean getCltCustTpNm(NMAL6730CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cltCustTpCd_F1)) {
            CLT_CUST_TPTMsg cltCustTpTMsg = new CLT_CUST_TPTMsg();
            cltCustTpTMsg = NMAL6730CommonLogic.findCltCustTp(cMsg.glblCmpyCd_H1.getValue(), cMsg.cltCustTpCd_F1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.cltCustTpCd_F1) && NMAL6730CommonLogic.findCltCustTp(cMsg.glblCmpyCd_H1.getValue(), cMsg.cltCustTpCd_F1.getValue()) == null) {
                cMsg.cltCustTpCd_F1.setErrorInfo(1, NMAM8121E, new String[] {"Collection Customer Type" });
                return false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpNm_F1, cltCustTpTMsg.cltCustTpNm);
                return true;
            }
        }
        return true;
    }

    public static boolean getCltPtfoNm(NMAL6730CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cltPtfoCd_F1)) {
            S21SsmEZDResult result = NMAL6730Query.getInstance().getCltPtfoNm(cMsg);

            if (result.isCodeNormal()) {
                String cltPtfoNm = (String) ((Map) result.getResultObject()).get("CLT_PTFO_NM");
                BigDecimal cltPtfoPk = (BigDecimal) ((Map) result.getResultObject()).get("CLT_PTFO_PK");
                ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoNm_F1, cltPtfoNm);
                ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoPk_F1, cltPtfoPk);
                return true;
            } else {
                cMsg.cltPtfoCd_F1.setErrorInfo(1, NMAM8121E, new String[] {"Default  Collector" });
                return false;
            }
        }
        return true;
    }

    public static boolean chkRemId(NMAL6730CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.remId_F1)) {
            S21SsmEZDResult result = NMAL6730Query.getInstance().countRemId(cMsg);
            BigDecimal count = BigDecimal.ZERO;
            if (!result.isCodeNotFound()) {
                count = (BigDecimal) result.getResultObject();
            }

            if (BigDecimal.ZERO.compareTo(count) == 0) {
                cMsg.remId_F1.setErrorInfo(1, NMAM8121E, new String[] {"Rem ID" });
                return false;
            }
        }

        return true;
    }

    // QC#7781
    public static boolean chkContact(NMAL6730CMsg cMsg, NMAL6730_ACMsg acMsg) {
        if (ZYPCommonFunc.hasValue(acMsg.xxGenlFldAreaTxt_A2)) {
            String ctacPsnPkList = acMsg.xxGenlFldAreaTxt_A2.getValue();
            String[] ctacPsnPkArray = NMAL6730CommonLogic.splitByComma(ctacPsnPkList);

            // check max count
            if (ctacPsnPkArray.length > NMAL6730Constant.MAX_CUST_INV_RULE_RCPNT_CNT) {
                acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM0289E);
                return false;
            }

            // check format
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (!isNumber(ctacPsnPk)) {
                    acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM8075E, new String[] {"single byte numeric character, and can be separated by comma." });
                    return false;
                }
            }

            // check max length
            CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (ctacPsnPk.length() > tMsg.getAttr("ctacPsnPk").getDigit()) {
                    acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, ZZM9001E, new String[] {"External Email Contact" });
                    return false;
                }
            }

            // check duplicate
            List<String> list = new ArrayList<String>();
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (list.contains(ctacPsnPk)) {
                    acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM0072E, new String[] {"External Email Contact" });
                    return false;
                } else {
                    list.add(ctacPsnPk);
                }
            }

            // check master
            for (String ctacPsnPk : ctacPsnPkArray) {
                if (ZYPCommonFunc.hasValue(ctacPsnPk)) {
                    if (!chkContact(cMsg, acMsg, new BigDecimal(ctacPsnPk))) {
                        acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM8121E, new String[] {"External Email Contact" });
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isNumber(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private static boolean chkContact(NMAL6730CMsg cMsg, NMAL6730_ACMsg acMsg, BigDecimal ctacPsnPk) {
        S21SsmEZDResult result = NMAL6730Query.getInstance().countContact(cMsg, acMsg, ctacPsnPk);
        BigDecimal count = BigDecimal.ZERO;
        if (!result.isCodeNotFound()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return false;
        }
        return true;
    }

    public static boolean chkBillVehicle(NMAL6730CMsg cMsg, NMAL6730_ACMsg acMsg) {

        if (ZYPCommonFunc.hasValue(acMsg.custBllgVcleCd_P1)) {
            CUST_BLLG_VCLETMsg custBllgVcleTMsg = new CUST_BLLG_VCLETMsg();
            custBllgVcleTMsg = NMAL6730CommonLogic.findCustBllgVcle(cMsg.glblCmpyCd_H1.getValue(), acMsg.custBllgVcleCd_P1.getValue());
            if (custBllgVcleTMsg == null) {
                acMsg.custBllgVcleCd_P1.setErrorInfo(1, NMAM8121E, new String[] {"Bill Vehicle" });
                return false;
            } else {

                if (ZYPConstant.FLG_ON_Y.equals(custBllgVcleTMsg.invSpclBillFlg.getValue()) && !CUST_BLLG_TP.CONSOLIDATED.equals(acMsg.custBllgTpCd_P1.getValue())) {
                    String[] args = {"Invoice Special Billing Flag is 'Y'", "Consolidated" };
                    acMsg.custBllgTpCd_P1.setErrorInfo(1, NMAM0075E, args);
                    acMsg.custBllgVcleCd_P1.setErrorInfo(1, NMAM0075E, args);
                    return false;
                }
                // QC#7781
                if (ZYPConstant.FLG_ON_Y.equals(custBllgVcleTMsg.itrlRvwFlg.getValue()) && splitByComma(acMsg.xxGenlFldAreaTxt_A1.getValue()).length == 0) {
                    String[] args = {"Internal Review Flag is 'Y'", "Internal Email Review" };
                    acMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, NMAM0075E, args);
                    return false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(custBllgVcleTMsg.emlEligFlg.getValue()) && splitByComma(acMsg.xxGenlFldAreaTxt_A2.getValue()).length == 0) {
                    String[] args = {"Email Eligible Flag is 'Y'", "External Email Contact" };
                    acMsg.xxGenlFldAreaTxt_A2.setErrorInfo(1, NMAM0075E, args);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * GL Code Combination Check
     * @param cMsg NMAL6730CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean coaCmbnCheck(NMAL6730CMsg cMsg, String glblCmpyCd) {

        NFZC102001 afzc102001 = new NFZC102001();
        NFZC102001PMsg paramMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(paramMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(paramMsg.xxArcsApiChkFlg, "");
        ZYPEZDItemValueSetter.setValue(paramMsg.coaCmpyCd, cMsg.coaCmpyCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaAfflCd, cMsg.coaAfflCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaBrCd, cMsg.coaBrCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaCcCd, cMsg.coaCcCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaAcctCd, cMsg.coaAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaProdCd, cMsg.coaProdCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaChCd, cMsg.coaChCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaProjCd, cMsg.coaProjCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.coaExtnCd, cMsg.coaExtnCd_H1);
        ZYPEZDItemValueSetter.setValue(paramMsg.resrcObjNm, BUSINESS_ID);

        afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

        List msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
        if (msgIdList != null && msgIdList.size() > 0) {
            cMsg.setMessageInfo((String) msgIdList.get(0));
            return false;
        }
        return true;
    }

    public static boolean callCreditProfileUpdateApi(NMAL6730CMsg bizMsg, String SlsDt) {
        // Credit Profile Update (Balance)

        boolean rtnFlg = true;
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();

        paramMsg.xxModeCd.setValue(NFZC202001.MODE_BILL_TO_CUST);
        paramMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd_H1.getValue());
        paramMsg.billToCustCd.setValue(bizMsg.billToCustCd_P1.getValue());
        paramMsg.procDt.setValue(SlsDt);

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                bizMsg.crLimitAmt_F1.setErrorInfo(1, msgId);
                bizMsg.setMessageInfo(msgId);
                rtnFlg = false;
            }
        }
        return rtnFlg;

    }

    // QC#7781
    public static boolean chkResource(NMAL6730_ACMsg acMsg) {
        if (ZYPCommonFunc.hasValue(acMsg.xxGenlFldAreaTxt_A1)) {
            String psnCdList = acMsg.xxGenlFldAreaTxt_A1.getValue();
            String[] psnCdArray = NMAL6730CommonLogic.splitByComma(psnCdList);

            // check max count
            if (psnCdArray.length > NMAL6730Constant.MAX_CUST_INV_RULE_RCPNT_CNT) {
                acMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, NMAM0289E);
                return false;
            }

            // check max length
            S21_PSNTMsg tMsg = new S21_PSNTMsg();
            for (String psnCd : psnCdArray) {
                if (psnCd.length() > tMsg.getAttr("psnCd").getDigit()) {
                    acMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, ZZM9001E, new String[] {"Internal Email Review" });
                    return false;
                }
            }

            // check duplicate
            List<String> list = new ArrayList<String>();
            for (String psnCd : psnCdArray) {
                if (list.contains(psnCd)) {
                    acMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, NMAM0072E, new String[] {"Internal Email Review" });
                    return false;
                } else {
                    list.add(psnCd);
                }
            }

            // check master
            for (String psnCd : psnCdArray) {
                if (ZYPCommonFunc.hasValue(psnCd)) {
                    if (!chkResource(acMsg, psnCd)) {
                        acMsg.xxGenlFldAreaTxt_A1.setErrorInfo(1, NMAM8121E, new String[] {"Internal Email Review" });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean chkResource(NMAL6730_ACMsg acMsg, String psnCd) {
        S21SsmEZDResult result = NMAL6730Query.getInstance().countResource(acMsg, psnCd);
        BigDecimal count = BigDecimal.ZERO;
        if (!result.isCodeNotFound()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return false;
        }
        return true;
    }

    public static int getAddAttributeNum(NMAL6730_KCMsgArray kcMsgArray) {

        for (int i = 0; i < kcMsgArray.getValidCount(); i++) {
            if (Integer.valueOf(kcMsgArray.no(i).dsAcctRefAttrbNum_K1.getValue()) == i + 1) {
                continue;
            }
            return i + 1;
        }
        return kcMsgArray.getValidCount() + 1;
    }

    public static void setNewAttribute(NMAL6730_KCMsgArray kcMsgArray, NMAL6730_KCMsg newMsg, int addNum) {

        int i = kcMsgArray.getValidCount();
        for (; i >= addNum; i--) {
            EZDMsg.copy(kcMsgArray.no(i - 1), null, kcMsgArray.no(i), null);
        }
        EZDMsg.copy(newMsg, null, kcMsgArray.no(i), null);
        kcMsgArray.setValidCount(kcMsgArray.getValidCount() + 1);
    }

    // QC#7781
    public static void getInvoiceSourceListInternalReview(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg, String glblCmpyCd) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            List<String> listPsnCd = new ArrayList<String>();
            List<String> listPsnNm = new ArrayList<String>();
            S21SsmEZDResult result = NMAL6730Query.getInstance().getInvoiceSourceListInternalReview(sMsg.A.no(i).dsCustInvRulePk_A1.getValue());
            if (result.isCodeNormal()) {
                List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
                for (Map<String, Object> map : listMap) {
                    String psnCd = (String) map.get("PSN_CD");
                    String psnNm = NMAL6730CommonLogic.getPsnNm(glblCmpyCd, psnCd);
                    listPsnCd.add(psnCd);
                    listPsnNm.add(psnNm);
                }
            }
            String psnCdList = NMAL6730CommonLogic.combineByComma(listPsnCd);
            String psnNmList = NMAL6730CommonLogic.combineByComma(listPsnNm);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxGenlFldAreaTxt_A1, psnCdList);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxCustInvRuleRcpntTxt_A1, psnNmList);
        }
    }

    public static void getInvoiceSourceListExternalContact(NMAL6730CMsg cMsg, NMAL6730SMsg sMsg, String glblCmpyCd) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            List<String> listCtacPsnPk = new ArrayList<String>();
            List<String> listCtacPsnNm = new ArrayList<String>();
            S21SsmEZDResult result = NMAL6730Query.getInstance().getInvoiceSourceListExternalContact(sMsg.A.no(i).dsCustInvRulePk_A1.getValue());
            if (result.isCodeNormal()) {
                List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
                for (Map<String, Object> map : listMap) {
                    String ctacPsnPk = ((BigDecimal) map.get("CTAC_PSN_PK")).toPlainString();
                    String ctacPsnNm = NMAL6730CommonLogic.getCtacPsnNm(glblCmpyCd, ctacPsnPk);
                    listCtacPsnPk.add(ctacPsnPk);
                    listCtacPsnNm.add(ctacPsnNm);
                }
            }
            String ctacPsnPkList = NMAL6730CommonLogic.combineByComma(listCtacPsnPk);
            String ctacPsnNmList = NMAL6730CommonLogic.combineByComma(listCtacPsnNm);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxGenlFldAreaTxt_A2, ctacPsnPkList);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxCustInvRuleRcpntTxt_A2, ctacPsnNmList);
        }
    }

    private static String combineByComma(List<String> list) {
        StringBuffer sb = new StringBuffer();
        for (String str : list) {
            if (sb.length() > 0) {
                sb.append(NMAL6730Constant.CHAR_COMMA);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * @param str String
     * @return String[]
     */
    public static String[] splitByComma(String str) {
        String[] array = str.split(NMAL6730Constant.CHAR_COMMA);
        List<String> list = new ArrayList<String>();
        for (String s : array) {
            if (ZYPCommonFunc.hasValue(s)) {
                list.add(s);
            }
        }
        return list.toArray(new String[] {});
    }
}
