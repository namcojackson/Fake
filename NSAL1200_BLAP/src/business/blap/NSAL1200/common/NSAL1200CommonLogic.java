/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1200.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_IDX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import static business.blap.NSAL1200.constant.NSAL1200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.blap.NSAL1200.NSAL1200CMsg;
import business.blap.NSAL1200.NSAL1200Query;
import business.blap.NSAL1200.NSAL1200SMsg;
import business.blap.NSAL1200.NSAL1200_ACMsg;
import business.blap.NSAL1200.NSAL1200_ACMsgArray;
import business.blap.NSAL1200.NSAL1200_ASMsg;
import business.db.MTR_GRPTMsg;
import business.db.MTR_GRPTMsgArray;
import business.db.MTR_LBTMsg;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          QC#6700
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#7648
 * 2016/11/15   Hitachi         T.Mizuki        Update          QC#15894
 * 2016/12/08   Hitachi         A.Kohinata      Update          QC#16418
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 * 2017/09/13   Hitachi         T.Kanasaka      Update          QC#21070
 * 2017/09/04   Hitachi         T.Kanasaka      Update          QC#15134
 * 2018/12/05   Hitachi         K.Morita        Update          QC#28644
 *</pre>
 */
public class NSAL1200CommonLogic {

    /**
     * Paginate to item
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @param itemIndex int
     */
    public static void pagenation(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {

        // NSAL1200CMsg -> NSAL1200SMsg
        setValue(sMsg.mtrGrpNm_H, cMsg.mtrGrpNm_H);
        setValue(sMsg.mtrGrpDescTxt_H, cMsg.mtrGrpDescTxt_H);
        setValue(sMsg.mtrGrpTpCd_H, cMsg.mtrGrpTpCd_H);
        setValue(sMsg.mtrGrpPk_H, cMsg.mtrGrpPk_H);
        // add start 2016/12/08 QC#16418
        setValue(sMsg.prcVldFlg_H, cMsg.prcVldFlg_H);
        // add end 2016/12/08 QC#16418

        // NSAL1200_ACMsg -> NSAL1200_ASMsg
        NSAL1200_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL1200_ACMsg acMsg = (NSAL1200_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;

            NSAL1200_ASMsg asMsg = (NSAL1200_ASMsg) sMsg.A.get(targetIdxNumA);
            if (isDetailLineModified(acMsg, asMsg)) {
                setValue(acMsg.xxValUpdFlg_A, ZYPConstant.FLG_ON_Y);
            }

            // copy CMsg to SMsg
            EZDMsg.copy(acMsg, null, asMsg, null);
        }
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage rowsPerPage
     * @param page page
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * getLastPageNum
     * 
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @return int
     */
    public static int getLastPageNum(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            return BigDecimal.ONE.intValue();
        }
        return BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP).intValue();
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NSAL1200CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NSAL1200CMsg cMsg) {
        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        } else if (cMsg.xxPageShowTotNum.getValueInt() < cMsg.xxPageShowCurNum.getValueInt()) {
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
    }

    /**
     * Set empty record.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @param index index
     */
    public static final void setEmptyRecord(String glblCmpyCd, NSAL1200CMsg cMsg, NSAL1200SMsg sMsg, int index) {
        NSAL1200_ASMsg asMsg = sMsg.A.no(index);
        setValue(asMsg.mtrMultRate_A, BigDecimal.ONE);
        setValue(asMsg.actvFlg_A, ZYPConstant.CHKBOX_ON_Y);
        setValue(asMsg.effFromDt_A, cMsg.slsDt);
        setValue(asMsg.xxRowNum_A, BigDecimal.valueOf(index + 1));
        sMsg.A.setValidCount(index + 1);
    }


    /**
     * validate header.
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @return true/false
     */
    public static final boolean validateHeader(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        // Counter Name Duplication
        MTR_GRPTMsgArray tMtrGrpArray = NSAL1200CommonLogic.findMtrGrpByName(cMsg.glblCmpyCd.getValue(), sMsg.mtrGrpNm_H.getValue());
        for (int i = 0; i < tMtrGrpArray.getValidCount(); i++) {
            String mtrGrpNm = sMsg.mtrGrpNm_H.getValue();
            if (mtrGrpNm.equals(tMtrGrpArray.no(i).mtrGrpNm.getValue())) {
                if (hasValue(sMsg.mtrGrpPk_H) && sMsg.mtrGrpPk_H.getValue().equals(tMtrGrpArray.no(i).mtrGrpPk.getValue())) {
                    return true;
                } else {
                    cMsg.mtrGrpNm_H.setErrorInfo(1, NSAM0035E, new String[] {"Counter Group" });
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * validateDetailLines
     * 
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @return true/false
     */
    public static final boolean validateDetailLines(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {

        boolean result = true;
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String slsDt = cMsg.slsDt.getValue();
        BigDecimal minRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_MIN_NUM, glblCmpyCd);
        BigDecimal maxRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_MAX_NUM, glblCmpyCd);
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, glblCmpyCd);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            S21SsmEZDResult ssmResult = null;
            // mod start 2016/04/27 CSA Defect#7648
            // Counter
            if (hasValue(sMsg.A.no(i).mtrLbDescTxt_CN)) {
                ssmResult = findMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mtrLbDescTxt_CN.getValue(), MTR_LB_TP.REGULAR_METER, MTR_IDX.NON_FLEET);
                if (ssmResult.getQueryResultCount() > 0) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                    setValue(sMsg.A.no(i).mdlMtrLbCd_A, (String) resultMap.get("MTR_LB_CD"));
                    setValue(sMsg.A.no(i).mtrLbDescTxt_CN, (String) resultMap.get("MTR_LB_DESC_TXT"));
                } else {
                    sMsg.A.no(i).mtrLbDescTxt_CN.setErrorInfo(1, NZZM0010E, new String[] {"Counter" });
                    result = false;
                }
            }

            // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//            // Billing Counter
//            if (hasValue(sMsg.A.no(i).mdlMtrLbCd_A) && hasValue(sMsg.A.no(i).mtrLbDescTxt_DB)) {
//                ssmResult = findBllgMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mdlMtrLbCd_A.getValue(), sMsg.A.no(i).mtrLbDescTxt_DB.getValue());
//                if (ssmResult.getQueryResultCount() > 0) {
//                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
//                    setValue(sMsg.A.no(i).bllgMtrLbCd_A, (String) resultMap.get("MTR_LB_CD"));
//                    setValue(sMsg.A.no(i).mtrLbDescTxt_DB, (String) resultMap.get("MTR_LB_DESC_TXT"));
//                } else {
//                    sMsg.A.no(i).mtrLbDescTxt_DB.setErrorInfo(1, NZZM0010E, new String[] {"Billing Counter" });
//                    result = false;
//                }
//            }
            // END 2017/08/03 T.Kanasaka [QC#18193,DEL]

            // Multiplier
            if (!validateMultiplier(sMsg.A.no(i), minRate, maxRate, fctNum)) {
                result = false;
            }

            // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//            // FLT Counter
//            if (hasValue(sMsg.A.no(i).mtrLbDescTxt_FL)) {
//                ssmResult = findMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mtrLbDescTxt_FL.getValue(), MTR_LB_TP.REGULAR_METER, MTR_IDX.FLEET);
//                if (ssmResult.getQueryResultCount() > 0) {
//                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
//                    setValue(sMsg.A.no(i).fleetMtrLbCd_A, (String) resultMap.get("MTR_LB_CD"));
//                    setValue(sMsg.A.no(i).mtrLbDescTxt_FL, (String) resultMap.get("MTR_LB_DESC_TXT"));
//                } else {
//                    sMsg.A.no(i).mtrLbDescTxt_FL.setErrorInfo(1, NZZM0010E, new String[] {"FLT Counter" });
//                    result = false;
//                }
//            // mod start 2016/11/15 CSA QC#15894
//            } else {
//                sMsg.A.no(i).fleetMtrLbCd_A.clear();
//            // mod end 2016/11/15 CSA QC#15894
//            }
//
//            // AGG Counter
//            if (hasValue(sMsg.A.no(i).mtrLbDescTxt_AG)) {
//                ssmResult = findMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mtrLbDescTxt_AG.getValue(), MTR_LB_TP.REGULAR_METER, MTR_IDX.AGGREGATE);
//                if (ssmResult.getQueryResultCount() > 0) {
//                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
//                    setValue(sMsg.A.no(i).aggrMtrLbCd_A, (String) resultMap.get("MTR_LB_CD"));
//                    setValue(sMsg.A.no(i).mtrLbDescTxt_AG, (String) resultMap.get("MTR_LB_DESC_TXT"));
//                } else {
//                    sMsg.A.no(i).mtrLbDescTxt_AG.setErrorInfo(1, NZZM0010E, new String[] {"AGG Counter" });
//                    result = false;
//                }
//            // mod start 2016/11/15 CSA QC#15894
//            } else {
//                sMsg.A.no(i).aggrMtrLbCd_A.clear();
//            // mod end 2016/11/15 CSA QC#15894
//            }
//            // mod end 2016/04/27 CSA Defect#7648
            // END 2017/08/03 T.Kanasaka [QC#18193,DEL]

            // START 2017/08/03 T.Kanasaka [QC#18193,ADD]
            // Billing Counter LVL1
            if (hasValue(sMsg.A.no(i).mdlMtrLbCd_A) && hasValue(sMsg.A.no(i).mtrLbDescTxt_L1)) {
                ssmResult = findBllgMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mdlMtrLbCd_A.getValue(), sMsg.A.no(i).mtrLbDescTxt_L1.getValue());
                if (ssmResult.getQueryResultCount() > 0) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                    setValue(sMsg.A.no(i).bllgMtrLbCd_L1, (String) resultMap.get("MTR_LB_CD"));
                    setValue(sMsg.A.no(i).mtrLbDescTxt_L1, (String) resultMap.get("MTR_LB_DESC_TXT"));
                } else {
                    sMsg.A.no(i).mtrLbDescTxt_L1.setErrorInfo(1, NZZM0010E, new String[] {"Billing Counter LVL1" });
                    result = false;
                }
            }

            // Billing Counter LVL2
            if (hasValue(sMsg.A.no(i).mdlMtrLbCd_A) && hasValue(sMsg.A.no(i).mtrLbDescTxt_L2)) {
                ssmResult = findBllgMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mdlMtrLbCd_A.getValue(), sMsg.A.no(i).mtrLbDescTxt_L2.getValue());
                if (ssmResult.getQueryResultCount() > 0) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                    setValue(sMsg.A.no(i).bllgMtrLbCd_L2, (String) resultMap.get("MTR_LB_CD"));
                    setValue(sMsg.A.no(i).mtrLbDescTxt_L2, (String) resultMap.get("MTR_LB_DESC_TXT"));
                } else {
                    sMsg.A.no(i).mtrLbDescTxt_L2.setErrorInfo(1, NZZM0010E, new String[] {"Billing Counter LVL2" });
                    result = false;
                }
            }

            // Billing Counter LVL3
            if (hasValue(sMsg.A.no(i).mdlMtrLbCd_A) && hasValue(sMsg.A.no(i).mtrLbDescTxt_L3)) {
                ssmResult = findBllgMtrLbByDescTxt(glblCmpyCd, slsDt, sMsg.A.no(i).mdlMtrLbCd_A.getValue(), sMsg.A.no(i).mtrLbDescTxt_L3.getValue());
                if (ssmResult.getQueryResultCount() > 0) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
                    setValue(sMsg.A.no(i).bllgMtrLbCd_L3, (String) resultMap.get("MTR_LB_CD"));
                    setValue(sMsg.A.no(i).mtrLbDescTxt_L3, (String) resultMap.get("MTR_LB_DESC_TXT"));
                } else {
                    sMsg.A.no(i).mtrLbDescTxt_L3.setErrorInfo(1, NZZM0010E, new String[] {"Billing Counter LVL3" });
                    result = false;
                }
            }
            // END 2017/08/03 T.Kanasaka [QC#18193,ADD]

            // START 2017/09/13 T.Kanasaka [QC#21070,ADD]
            // Level Duplicate Check
            List<String> bllgMtrLbCdGreaterThanLv3 = new ArrayList<String>();
            if (hasValue(sMsg.mtrGrpPk_H.getValue())) {
                bllgMtrLbCdGreaterThanLv3 = getBllgMtrLbCdGreaterThanLv3(glblCmpyCd, slsDt, sMsg.mtrGrpPk_H.getValue(), sMsg.A.no(i).mdlMtrLbCd_A.getValue());
            }

            if (!sMsg.A.no(i).mtrLbDescTxt_L1.isError() && hasValue(sMsg.A.no(i).mtrLbDescTxt_L1)) {
                if (!sMsg.A.no(i).mtrLbDescTxt_L2.isError() & hasValue(sMsg.A.no(i).mtrLbDescTxt_L2) && sMsg.A.no(i).bllgMtrLbCd_L1.getValue().equals(sMsg.A.no(i).bllgMtrLbCd_L2.getValue())) {
                    sMsg.A.no(i).mtrLbDescTxt_L1.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter" });
                    sMsg.A.no(i).mtrLbDescTxt_L2.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter" });
                    result = false;
                }
                if (!sMsg.A.no(i).mtrLbDescTxt_L3.isError() & hasValue(sMsg.A.no(i).mtrLbDescTxt_L3) && sMsg.A.no(i).bllgMtrLbCd_L1.getValue().equals(sMsg.A.no(i).bllgMtrLbCd_L3.getValue())) {
                    sMsg.A.no(i).mtrLbDescTxt_L1.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter" });
                    sMsg.A.no(i).mtrLbDescTxt_L3.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter" });
                    result = false;
                }
                if (!sMsg.A.no(i).mtrLbDescTxt_L1.isError() && bllgMtrLbCdGreaterThanLv3.contains(sMsg.A.no(i).bllgMtrLbCd_L1.getValue())) {
                    sMsg.A.no(i).mtrLbDescTxt_L1.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter (greater than Lv3)" });
                    result = false;
                }
            }

            if (!sMsg.A.no(i).mtrLbDescTxt_L2.isError() && hasValue(sMsg.A.no(i).mtrLbDescTxt_L2)) {
                if (!sMsg.A.no(i).mtrLbDescTxt_L3.isError() && hasValue(sMsg.A.no(i).mtrLbDescTxt_L3) && sMsg.A.no(i).bllgMtrLbCd_L2.getValue().equals(sMsg.A.no(i).bllgMtrLbCd_L3.getValue())) {
                    sMsg.A.no(i).mtrLbDescTxt_L2.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter" });
                    sMsg.A.no(i).mtrLbDescTxt_L3.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter" });
                    result = false;
                } else if (bllgMtrLbCdGreaterThanLv3.contains(sMsg.A.no(i).bllgMtrLbCd_L2.getValue())) {
                    sMsg.A.no(i).mtrLbDescTxt_L2.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter (greater than Lv3)" });
                    result = false;
                }
            }

            if (!sMsg.A.no(i).mtrLbDescTxt_L3.isError() && hasValue(sMsg.A.no(i).mtrLbDescTxt_L3)) {
                if (bllgMtrLbCdGreaterThanLv3.contains(sMsg.A.no(i).bllgMtrLbCd_L3.getValue())) {
                    sMsg.A.no(i).mtrLbDescTxt_L3.setErrorInfo(1, NSAM0035E, new String[] {"Billing Counter (greater than Lv3)" });
                    result = false;
                }
            }
            // END 2017/09/13 T.Kanasaka [QC#21070,ADD]

            // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
            if (hasValue(sMsg.A.no(i).cntrDigitNum_A)) {
                if (MIN_CNTR_DIGIT_NUM.compareTo(sMsg.A.no(i).cntrDigitNum_A.getValue()) > 0 || MAX_CNTR_DIGIT_NUM.compareTo(sMsg.A.no(i).cntrDigitNum_A.getValue()) < 0) {
                    sMsg.A.no(i).cntrDigitNum_A.setErrorInfo(1, NSAM0194E, new String[] {MIN_CNTR_DIGIT_NUM.toString(), MAX_CNTR_DIGIT_NUM.toString() });
                    result = false;
                }
            }
            // END 2017/09/04 T.Kanasaka [QC#15134,ADD]

            // Start Date > End Date
            String effFromDt = sMsg.A.no(i).effFromDt_A.getValue();
            String effThruDt = sMsg.A.no(i).effThruDt_A.getValue();
            if (ZYPDateUtil.compare(effFromDt, effThruDt) == 1) {
                sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NSAM0374E, new String[] {"Start Date" });
                result = false;
            }

            // Sale Date > End Date (Active Flag = On)
            String actvFlg = sMsg.A.no(i).actvFlg_A.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(actvFlg)) {
                if (ZYPDateUtil.compare(slsDt, effThruDt) == 1) {
                    sMsg.A.no(i).effThruDt_A.setErrorInfo(1, NSAM0447E);
                    result = false;
                }
            }
        }

        if (!result) {
            return false;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // compare MTR_LB_CD and period.
            if (isExistDuplicateLine(sMsg, i)) {
                sMsg.A.no(i).mtrLbDescTxt_CN.setErrorInfo(1, NSAM0446E);
                return false;
            }
        }

        return true;
    }

    private static final boolean validateMultiplier(NSAL1200_ASMsg asMsg, BigDecimal minRate, BigDecimal maxRate, BigDecimal fctNum) {
        BigDecimal spcRate = BigDecimal.ONE.negate();

        if (ZYPCommonFunc.hasValue(asMsg.mtrMultRate_A)) {
            BigDecimal multRate = asMsg.mtrMultRate_A.getValue();
            if (multRate.compareTo(spcRate) != 0) {
                if (multRate.compareTo(minRate) < 0 || multRate.compareTo(maxRate) > 0) {
                    asMsg.mtrMultRate_A.setErrorInfo(1, NSAM0316E, new String[] {minRate.toPlainString(), maxRate.toPlainString() });
                    return false;
                }
                if (multRate.remainder(fctNum).compareTo(BigDecimal.ZERO) != 0) {
                    asMsg.mtrMultRate_A.setErrorInfo(1, NSAM0317E, new String[] {fctNum.toPlainString() });
                    return false;
                }
            }
        }

        return true;
    }

    private static final boolean isExistDuplicateLine(NSAL1200SMsg sMsg, int selIdx) {
        String selMdlMtrLbCd = sMsg.A.no(selIdx).mdlMtrLbCd_A.getValue();
        String selEffFromDt = sMsg.A.no(selIdx).effFromDt_A.getValue();
        String selEffThruDt = sMsg.A.no(selIdx).effThruDt_A.getValue();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (selIdx == i || !ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(selIdx).actvFlg_A.getValue())) {
                continue;
            }

            if (selMdlMtrLbCd.equals(sMsg.A.no(i).mdlMtrLbCd_A.getValue())) {
                String effFromDt = sMsg.A.no(i).effFromDt_A.getValue();
                String effThruDt = sMsg.A.no(i).effThruDt_A.getValue();

                if (ZYPDateUtil.compare(replaceEmptyDt(effThruDt), selEffFromDt) >= 0
                        && ZYPDateUtil.compare(replaceEmptyDt(selEffThruDt), effFromDt) >= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private static final String replaceEmptyDt(String dt) {
        if (!hasValue(dt)) {
            return MAX_DT_VAL;
        }
        return dt;
    }


    /**
     * findMtrLbByDescTxt
     *  
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrLbDescTxt mtrLbDescTxt
     * @param mtrLbTpCd mtrLbTpCd
     * @param mtrIdxCd mtrIdxCd
     * @return S21SsmEZDResult
     */
    public static final S21SsmEZDResult findMtrLbByDescTxt(String glblCmpyCd, String slsDate, String mtrLbDescTxt, String mtrLbTpCd, String mtrIdxCd) {
        return NSAL1200Query.getInstance().getMtrLbMapByDescTxt(glblCmpyCd, slsDate, mtrLbDescTxt, mtrLbTpCd, mtrIdxCd);
    }

    /**
     * findBllgMtrLbByDescTxt
     *  
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrLbCd mtrLbCd
     * @param bllgMtrLbDescTxt bllgMtrLbDescTxt
     * @return S21SsmEZDResult
     */
    public static final S21SsmEZDResult findBllgMtrLbByDescTxt(String glblCmpyCd, String slsDate, String mtrLbCd, String bllgMtrLbDescTxt) {
        return NSAL1200Query.getInstance().getBllgMtrMapByDescTxt(glblCmpyCd, slsDate, mtrLbCd, bllgMtrLbDescTxt);
    }

    // START 2017/09/13 T.Kanasaka [QC#21070,ADD]
    /**
     * getBllgMtrLbCdGreaterThanLv3
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrGrpPk mtrGrpPk
     * @param mdlMtrLbCd mdlMtrLbCd
     * @return List<String>
     */
    public static final List<String> getBllgMtrLbCdGreaterThanLv3(String glblCmpyCd, String slsDate, BigDecimal mtrGrpPk, String mdlMtrLbCd) {
        S21SsmEZDResult ssmResult = NSAL1200Query.getInstance().getBllgMtrLbCdGreaterThanLv3(glblCmpyCd, slsDate, mtrGrpPk, mdlMtrLbCd);
        return (List<String>) ssmResult.getResultObject();
    }
    // END 2017/09/13 T.Kanasaka [QC#21070,ADD]

    /**
     * checkMtrLbCd
     *  
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrLbCd mtrLbCd
     * @param mtrLbTpCd mtrLbTpCd
     * @param mtrIdxCd mtrIdxCd
     * @return boolean
     */
    public static final boolean checkMtrLbCd(String glblCmpyCd, String slsDate, String mtrLbCd, String mtrLbTpCd, String mtrIdxCd) {
        S21SsmEZDResult ssmResult = NSAL1200Query.getInstance().getMtrLbMap(glblCmpyCd, slsDate, mtrLbCd, mtrLbTpCd, mtrIdxCd);
        if (ssmResult.getQueryResultCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * checkBllgMtrLbCd
     *  
     * @param glblCmpyCd glblCmpyCd
     * @param slsDate slsDate
     * @param mtrLbCd mtrLbCd
     * @param bllgMtrLbCd bllgMtrLbCd
     * @return boolean
     */
    public static final boolean checkBllgMtrLbCd(String glblCmpyCd, String slsDate, String mtrLbCd, String bllgMtrLbCd) {
        S21SsmEZDResult ssmResult = NSAL1200Query.getInstance().getBllgMtrMap(glblCmpyCd, slsDate, mtrLbCd, bllgMtrLbCd);
        if (ssmResult.getQueryResultCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * find MTR_GRPTMsg by MTR_GRP_NM.
     * @param glblCmpyCd glblCmpyCd
     * @param mtrGrpNm mtrGrpNm
     * @return MTR_GRPTMsgArray
     */
    public static final MTR_GRPTMsgArray findMtrGrpByName(String glblCmpyCd, String mtrGrpNm) {
        MTR_GRPTMsg inMsg = new MTR_GRPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrGrpNm01", mtrGrpNm);
        inMsg.setConditionValue("ezCancelFlag01", ZYPConstant.FLG_OFF_0);
        return (MTR_GRPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * check MTR_IDX_CD is Non-Fleet.
     * @param glblCmpyCd glblCmpyCd
     * @param mtrLbCd mtrLbCd
     * @return true/false
     */
    public static final boolean isMtrIdxNonFleet(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mtrLbCd, mtrLbCd);
        inMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (MTR_IDX.NON_FLEET.equals(inMsg.mtrIdxCd.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * getFirstErrorIndex
     * 
     * @param cMsg NSAL1200CMsg
     * @param sMsg NSAL1200SMsg
     * @return error page number
     */
    public static final int getFirstErrorIndex(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        // START 2016/04/26 [QC#6700, MOD]
        int errIndex = convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        // END   2016/04/26 [QC#6700, MOD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isError(sMsg.A.no(i))) {
                errIndex = i;
                break;
            }
        }
        return errIndex;
    }

    // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
    private static final boolean isDetailLineModified(NSAL1200_ACMsg acMsg, NSAL1200_ASMsg asMsg) {

        if (!compareObject(acMsg.mdlMtrLbCd_A, asMsg.mdlMtrLbCd_A)) {
            return true;
        }
//        if (!compareObject(acMsg.bllgMtrLbCd_A, asMsg.bllgMtrLbCd_A)) {
//            return true;
//        }
        if (!compareFlag(acMsg.mtrEntryMndFlg_A, asMsg.mtrEntryMndFlg_A)) {
            return true;
        }
        if (!compareObject(acMsg.mtrMultRate_A, asMsg.mtrMultRate_A)) {
            return true;
        }
//        if (!compareObject(acMsg.fleetMtrLbCd_A, asMsg.fleetMtrLbCd_A)) {
//            return true;
//        }
//        if (!compareObject(acMsg.aggrMtrLbCd_A, asMsg.aggrMtrLbCd_A)) {
//            return true;
//        }
        // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
        if (!compareObject(acMsg.cntrDigitNum_A, asMsg.cntrDigitNum_A)) {
            return true;
        }
        // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
        
        // START 2018/12/5 [QC#,28644,MOD]
        if (!compareFlag(acMsg.techReadMndFlg_A, asMsg.techReadMndFlg_A)) {
            return true;
        }
        // END 2018/12/5 [QC#28644,ADD]
     
        if (!compareFlag(acMsg.actvFlg_A, asMsg.actvFlg_A)) {
            return true;
        }
        if (!compareObject(acMsg.effFromDt_A, asMsg.effFromDt_A)) {
            return true;
        }
        if (!compareObject(acMsg.effThruDt_A, asMsg.effThruDt_A)) {
            return true;
        }
//        // mod start 2016/11/15 CSA QC#15894
//        if (!compareObject(acMsg.mtrLbDescTxt_FL, asMsg.mtrLbDescTxt_FL)) {
//            return true;
//        }
//        if (!compareObject(acMsg.mtrLbDescTxt_AG, asMsg.mtrLbDescTxt_AG)) {
//            return true;
//        }
//        // mod end 2016/11/15 CSA QC#15894

        if (!compareObject(acMsg.bllgMtrLbCd_L1, asMsg.bllgMtrLbCd_L1)) {
            return true;
        }
        if (!compareObject(acMsg.bllgMtrLbCd_L2, asMsg.bllgMtrLbCd_L2)) {
            return true;
        }
        if (!compareObject(acMsg.bllgMtrLbCd_L3, asMsg.bllgMtrLbCd_L3)) {
            return true;
        }
        if (!compareObject(acMsg.mtrLbDescTxt_L1, asMsg.mtrLbDescTxt_L1)) {
            return true;
        }
        if (!compareObject(acMsg.mtrLbDescTxt_L2, asMsg.mtrLbDescTxt_L2)) {
            return true;
        }
        if (!compareObject(acMsg.mtrLbDescTxt_L3, asMsg.mtrLbDescTxt_L3)) {
            return true;
        }

        return false;
    }
    // END 2017/08/03 T.Kanasaka [QC#18193,MOD]

    private static final boolean compareFlag(EZDCStringItem cItem, EZDSStringItem sItem) {
        String sItemValue = sItem.getValue();
        String cItemValue = cItem.getValue();

        if (!hasValue(sItemValue)) {
            return false;
        }

        if (!hasValue(cItemValue)) {
            cItemValue = ZYPConstant.FLG_OFF_N;
        }

        return sItemValue.equals(cItemValue);
    }

    private static final boolean compareObject(EZDCStringItem cItem, EZDSStringItem sItem) {
        if (hasValue(cItem) && !hasValue(sItem)) {
            return false;
        }
        if (!hasValue(cItem) && hasValue(sItem)) {
            return false;
        }
        if (hasValue(cItem) && hasValue(sItem)) {
            if (!cItem.getValue().equals(sItem.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static final boolean compareObject(EZDCDateItem cItem, EZDSDateItem sItem) {
        if (hasValue(cItem) && !hasValue(sItem)) {
            return false;
        }
        if (!hasValue(cItem) && hasValue(sItem)) {
            return false;
        }
        if (hasValue(cItem) && hasValue(sItem)) {
            if (!cItem.getValue().equals(sItem.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static final boolean compareObject(EZDCBigDecimalItem cItem, EZDSBigDecimalItem sItem) {
        if (hasValue(cItem) && !hasValue(sItem)) {
            return false;
        }
        if (!hasValue(cItem) && hasValue(sItem)) {
            return false;
        }
        if (hasValue(cItem) && hasValue(sItem)) {
            if (cItem.getValue().compareTo(sItem.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * check validation 
     * 
     * @param aSMsg NSAL1200_ASMsg
     * @return true/false
     */
    public static final boolean isError(NSAL1200_ASMsg aSMsg) {

        if (aSMsg.mtrLbDescTxt_CN.isError()) {
            return true;
        }
        // START 2017/08/03 T.Kanasaka [QC#18193,MOD]
//        if (aSMsg.mtrLbDescTxt_DB.isError()) {
//            return true;
//        }
        if (aSMsg.mtrEntryMndFlg_A.isError()) {
            return true;
        }
        if (aSMsg.mtrMultRate_A.isError()) {
            return true;
        }
//        if (aSMsg.mtrLbDescTxt_FL.isError()) {
//            return true;
//        }
//        if (aSMsg.mtrLbDescTxt_AG.isError()) {
//            return true;
//        }
        if (aSMsg.mtrLbDescTxt_L1.isError()) {
            return true;
        }
        if (aSMsg.mtrLbDescTxt_L2.isError()) {
            return true;
        }
        if (aSMsg.mtrLbDescTxt_L3.isError()) {
            return true;
        }
        // END 2017/08/03 T.Kanasaka [QC#18193,MOD]
        // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
        if (aSMsg.cntrDigitNum_A.isError()) {
            return true;
        }
        // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
        if (aSMsg.actvFlg_A.isError()) {
            return true;
        }
        if (aSMsg.effFromDt_A.isError()) {
            return true;
        }
        if (aSMsg.effThruDt_A.isError()) {
            return true;
        }
        return false;
    }
}
