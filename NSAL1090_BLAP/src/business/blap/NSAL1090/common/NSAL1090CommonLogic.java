/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1090.common;

import static business.blap.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_PROC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import business.blap.NSAL1090.NSAL1090CMsg;
import business.blap.NSAL1090.NSAL1090Query;
import business.blap.NSAL1090.NSAL1090SMsg;
import business.blap.NSAL1090.NSAL1090_ASMsg;
import business.blap.NSAL1090.constant.NSAL1090Constant;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2017/11/07   Hitachi         K.Kojima        Update          QC#22325
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 * 2023/07/06   CITS            T.Aizawa        Update          QC#59538
 *</pre>
 */
public class NSAL1090CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL1090CMsg
     */
    public static void createPullDown(NSAL1090CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(SVC_CR_REBIL_PROC.class, cMsg.svcCrRebilProcCd_L, cMsg.svcCrRebilDescTxt_L);
    }

    /**
     * Search Credit Rebill Info
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     */
    public static void searchCrRebilInfo(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        clearMsg(cMsg, sMsg);
        if (!hasValue(cMsg.custIncdtId_H)) {
            cMsg.setMessageInfo(NSAM0131E, new String[] {"CUST_INCDT_ID" });
            return;
        }
        getCrRebilInfo(cMsg, sMsg);
        setSvcCrRebilDtlPkExistsFlg(sMsg);
        // START 2017/11/07 K.Kojima [QC#22224,ADD]
        setSummary(cMsg, sMsg);
        // END 2017/11/07 K.Kojima [QC#22224,ADD]
        pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Set Approval List Parameter
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     */
    public static void setApprovalListParam(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.P);
        int cnt = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.A.no(i).xxExstFlg_A.getValue())) {
                setValue(cMsg.P.no(cnt).svcCrRebilDtlPk, sMsg.A.no(i).svcCrRebilDtlPk_A.getValue());
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                    setValue(cMsg.P.no(cnt).invPrintFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    setValue(cMsg.P.no(cnt).invPrintFlg, ZYPConstant.FLG_OFF_N);
                }
                cnt++;
            }
        }
        cMsg.P.setValidCount(cnt);
    }

    /**
     * Set Sequence Number
     * @param sMsg NSAL1090SMsg
     */
    public static void setSeqNum(NSAL1090SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
    }

    /**
     * Set SvcCrRebilDtlPk Exists Flg
     * @param sMsg NSAL1090SMsg
     */
    public static void setSvcCrRebilDtlPkExistsFlg(NSAL1090SMsg sMsg) {

        List<BigDecimal> svcCrRebilDtlPkList = new ArrayList<BigDecimal>();
        BigDecimal targetSvcCrRebilDtlPk = null;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            targetSvcCrRebilDtlPk = sMsg.A.no(i).svcCrRebilDtlPk_A.getValue();
            if (!hasValue(targetSvcCrRebilDtlPk)) {
                sMsg.A.no(i).xxExstFlg_A.clear();
                continue;
            }

            if (svcCrRebilDtlPkList.contains(targetSvcCrRebilDtlPk)) {
                setValue(sMsg.A.no(i).xxExstFlg_A, ZYPConstant.FLG_ON_Y);
            } else {
                setValue(sMsg.A.no(i).xxExstFlg_A, ZYPConstant.FLG_OFF_N);
                svcCrRebilDtlPkList.add(targetSvcCrRebilDtlPk);
            }
        }
    }

    /**
     * Set CheckBox
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     */
    public static void setCheckBox(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        BigDecimal targetSvcCrRebilDtlPk = cMsg.A.no(cMsg.xxRowNum_S.getValueInt()).svcCrRebilDtlPk_A.getValue();
        String targetChcekBox = cMsg.A.no(cMsg.xxRowNum_S.getValueInt()).xxChkBox_A.getValue();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (targetSvcCrRebilDtlPk.compareTo(sMsg.A.no(i).svcCrRebilDtlPk_A.getValue()) == 0) {
                setValue(sMsg.A.no(i).xxChkBox_A, targetChcekBox);
            }
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * Clear Message
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     */
    private static void clearMsg(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        cMsg.svcCrRebilStsDescTxt_H.clear();
        cMsg.svcCrRebilDescTxt_H.clear();
        cMsg.svcCrRebilProcCd_H.clear();
        cMsg.svcCrRebilPk_H.clear();
        cMsg.svcCrRebilStsCd_H.clear();
        cMsg.xxChkBox_AL.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Get Credit Rebill Info
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     */
    private static void getCrRebilInfo(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        NSAL1090Query.getInstance().getHeaderInfo(cMsg);
        S21SsmEZDResult ssmResult = NSAL1090Query.getInstance().getDetailInfo(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    // START 2017/11/07 K.Kojima [QC#22325,ADD]
    private static void setSummary(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {
        BigDecimal xxInvAmt_S1 = BigDecimal.ZERO;
        BigDecimal xxInvAmt_S2 = BigDecimal.ZERO;
        BigDecimal xxInvAmt_S3 = BigDecimal.ZERO;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1090_ASMsg asMsg = sMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(asMsg.xxInvAmt_A1)) {
                xxInvAmt_S1 = xxInvAmt_S1.add(asMsg.xxInvAmt_A1.getValue());
            }
            if (ZYPCommonFunc.hasValue(asMsg.xxInvAmt_A2)) {
                xxInvAmt_S2 = xxInvAmt_S2.add(asMsg.xxInvAmt_A2.getValue());
            }
            if (ZYPCommonFunc.hasValue(asMsg.xxInvAmt_A3)) {
                xxInvAmt_S3 = xxInvAmt_S3.add(asMsg.xxInvAmt_A3.getValue());
            }
        }
        String xxDplyCtrlFlg_S = ZYPConstant.FLG_OFF_N;
        if (xxInvAmt_S3.compareTo(BigDecimal.ZERO) < 0) {
            xxDplyCtrlFlg_S = ZYPConstant.FLG_ON_Y;
            xxInvAmt_S3 = xxInvAmt_S3.abs();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.xxInvAmt_S1, xxInvAmt_S1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxInvAmt_S2, xxInvAmt_S2);
        ZYPEZDItemValueSetter.setValue(cMsg.xxInvAmt_S3, xxInvAmt_S3);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_S, xxDplyCtrlFlg_S);
    }
    // END 2017/11/07 K.Kojima [QC#22325,ADD]

    // START 2021/01/12 R.Shimamoto [QC#58177,ADD]
    public static boolean checkFuturePE(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {

        List<Map<String, Object>> resultList = null;

        // check Usage
        S21SsmEZDResult trgtUsg = NSAL1090Query.getInstance().getMaxMtrBllgThruDtFromUsage(cMsg);
        resultList = (List<Map<String, Object>>) trgtUsg.getResultObject();

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

            BigDecimal chkCnt = NSAL1090Query.getInstance().checkFuturePrcEffFromUsage(cMsg, (BigDecimal) resultMap.get("DS_CONTR_BLLG_MTR_PK"), (String) resultMap.get("MAX_DT"));
            if (BigDecimal.ZERO.compareTo(chkCnt) == -1) {
                return true;
            }
        }

        // check Base 
        S21SsmEZDResult trgtBase = NSAL1090Query.getInstance().getMaxMtrBllgThruDtFromBase(cMsg);
        resultList = (List<Map<String, Object>>) trgtBase.getResultObject();

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

            BigDecimal chkCnt = NSAL1090Query.getInstance().checkFuturePrcEffFromBase(cMsg, (BigDecimal) resultMap.get("DS_CONTR_DTL_PK"), (String) resultMap.get("MAX_DT"));
            if (BigDecimal.ZERO.compareTo(chkCnt) == -1) {
                return true;
            }
        }

        return false;
    }
    // END 2021/01/12 R.Shimamoto [QC#58177,ADD]

    // START 2023/07/06 t.aizawa [QC#59538,ADD]
    /**
     * Check if has Invoice with same Invoice#, same Detail, same Charge Type, and more than one Billing Period.
     * @param cMsg NSAL1090CMsg
     * @param sMsg NSAL1090SMsg
     * @return boolean
     */
    public static boolean hasInvoiceWithMoreThanOneBillingPeriod(NSAL1090CMsg cMsg, NSAL1090SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL1090Query.getInstance().getInvoiceWithMoreThanOneBillingPeriod(cMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = NWXC412001.autoCast(ssmResult.getResultObject());
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
            cMsg.custIncdtId_H.setErrorInfo(1, NSAL1090Constant.NSAM0780E, new String[] {(String) resultMap.get("ORIG_SVC_INV_NUM"), (String) resultMap.get("SVC_INV_CHRG_TP_NM"), ZYPDateUtil.formatEzd8ToDisp((String) resultMap.get("BLLG_FROM_DT")),
                    ZYPDateUtil.formatEzd8ToDisp((String) resultMap.get("BLLG_THRU_DT")) });
            cMsg.setMessageInfo(NSAL1090Constant.ZZM9037E);
            return true;
        }

        return false;
    }
    // END   2023/07/06 t.aizawa [QC#59538,ADD]

}
