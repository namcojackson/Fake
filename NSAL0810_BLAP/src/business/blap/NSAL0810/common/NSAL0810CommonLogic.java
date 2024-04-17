/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0810.common;

import static business.blap.NSAL0810.constant.NSAL0810Constant.NACM0746E;
import static business.blap.NSAL0810.constant.NSAL0810Constant.NACM0747E;
import static business.blap.NSAL0810.constant.NSAL0810Constant.RTNCD_NORMAL;
import static business.blap.NSAL0810.constant.NSAL0810Constant.ZZM8100I;
import static business.blap.NSAL0810.constant.NSAL0810Constant.BLLG_MTR_LB_NM_MAX_LENGTH;
import static business.blap.NSAL0810.constant.NSAL0810Constant.PAD_STR;
import static business.blap.NSAL0810.constant.NSAL0810Constant.LPAD_LEN;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0810.NSAL0810CMsg;
import business.blap.NSAL0810.NSAL0810Query;
import business.blap.NSAL0810.NSAL0810SMsg;
import business.blap.NSAL0810.NSAL0810_ACMsg;
import business.blap.NSAL0810.NSAL0810_ACMsgArray;
import business.blap.NSAL0810.NSAL0810_ASMsg;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MTR_LBTMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_CONFIG_MSTRTMsgArray;
import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.ValidationReturnBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.START_READ_VLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_FROM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Ds Contract Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/01/28   Hitachi         Y.Tsuchimoto    Update          QC#3862
 * 2016/02/03   Hitachi         T.Iwamoto       Update          QC#3995
 * 2016/02/15   Hitachi         Y.Takeno        Update          QC#3314
 * 2016/03/29   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/04/01   Hitachi         T.Iwamoto       Update          QC#6334, 6335
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/06/16   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/07/14   Hitachi         Y.Takeno        Update          QC#11918
 * 2016/08/31   Hitachi         T.Mizuki        Update          QC#12566
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/09/15   Hitachi         A.Kohinata      Update          QC#19775
 * 2017/11/21   Hitachi         T.Tomita        Update          QC#21724
 * 2018/02/22   CITS            M.Naito         Update          QC#23179
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0810CommonLogic {

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {

        // NSAL0810_ACMsg -> NSAL0810_ASMsg
        NSAL0810_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0810_ACMsg acMsg = (NSAL0810_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;
            EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(targetIdxNumA), null);
        }
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }
    /**
     * Paginate to item
     * @param cMsg NSAL0100CMsg
     * @param sMsg NSAL0100SMsg
     * @param itemIndex int
     */
    public static void pagenation(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, int itemIndex) {

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
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));

    }

    // del start 2017/09/15 QC#19775
//    /**
//     * getDsContrIntfcDefRule
//     * @param glblCmpyCd String
//     * @param contrIntfcSrcTp String
//     * @return S21SsmEZDResult
//     */
//    public static DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRule(String glblCmpyCd, String contrIntfcSrcTp) {
//        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getDsContrIntfcDefRule(glblCmpyCd, contrIntfcSrcTp);
//        if (!ssmResult.isCodeNormal()) {
//            return new DS_CONTR_INTFC_DEF_RULETMsg();
//        }
//        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
//        if (map == null) {
//            return new DS_CONTR_INTFC_DEF_RULETMsg();
//        }
//        return getDsContrIntfcDefRuleTMsg(map);
//    }

//    private static DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRuleTMsg(Map<String, Object> map) {
//        DS_CONTR_INTFC_DEF_RULETMsg tMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
//
//        setValue(tMsg.glblCmpyCd, (String) map.get("GLBL_CMPY_CD"));
//        setValue(tMsg.contrIntfcSrcTpCd, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
//        setValue(tMsg.effFromDt, (String) map.get("EFF_FROM_DT"));
//        setValue(tMsg.enblFlg, (String) map.get("ENBL_FLG"));
//        setValue(tMsg.mtrEstTpCd, (String) map.get("MTR_EST_TP_CD"));
//        setValue(tMsg.baseBllgCycleCd, (String) map.get("BASE_BLLG_CYCLE_CD"));
//        setValue(tMsg.mtrBllgCycleCd, (String) map.get("MTR_BLLG_CYCLE_CD"));
//        setValue(tMsg.invSeptBaseUsgFlg, (String) map.get("INV_SEPT_BASE_USG_FLG"));
//        setValue(tMsg.prcAllocByMachQtyFlg, (String) map.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
//        setValue(tMsg.contrUplftTpCd, (String) map.get("CONTR_UPLFT_TP_CD"));
//        setValue(tMsg.uplftPrcMethCd, (String) map.get("UPLFT_PRC_METH_CD"));
//        setValue(tMsg.uplftBasePrcUpRatio, (BigDecimal) map.get("UPLFT_BASE_PRC_UP_RATIO"));
//        setValue(tMsg.uplftMtrPrcUpRatio, (BigDecimal) map.get("UPLFT_MTR_PRC_UP_RATIO"));
//        setValue(tMsg.bllgThruTpCd, (String) map.get("BLLG_THRU_TP_CD"));
//        setValue(tMsg.bllgThruDt, (String) map.get("BLLG_THRU_DT"));
//        setValue(tMsg.stubPrepFromTpCd, (String) map.get("STUB_PREP_FROM_TP_CD"));
//        setValue(tMsg.stubPrepThruTpCd, (String) map.get("STUB_PREP_THRU_TP_CD"));
//        setValue(tMsg.autoEffFleetFlg, (String) map.get("AUTO_EFF_FLEET_FLG"));
//        setValue(tMsg.autoEffAggrFlg, (String) map.get("AUTO_EFF_AGGR_FLG"));
//        setValue(tMsg.leaseCmpyCd, (String) map.get("LEASE_CMPY_CD"));
//        setValue(tMsg.mtrReadMethCd, (String) map.get("MTR_READ_METH_CD"));
//        setValue(tMsg.dsContrSlsCrTpCd, (String) map.get("DS_CONTR_SLS_CR_TP_CD"));
//        setValue(tMsg.contrAutoRnwTpCd, (String) map.get("CONTR_AUTO_RNW_TP_CD"));
//        setValue(tMsg.rnwPrcMethCd, (String) map.get("RNW_PRC_METH_CD"));
//        setValue(tMsg.befEndRnwDaysAot, (BigDecimal) map.get("BEF_END_RNW_DAYS_AOT"));
//        setValue(tMsg.basePrcUpRatio, (BigDecimal) map.get("BASE_PRC_UP_RATIO"));
//        setValue(tMsg.mtrPrcUpRatio, (BigDecimal) map.get("MTR_PRC_UP_RATIO"));
//        setValue(tMsg.startReadVldTpCd, (String) map.get("START_READ_VLD_TP_CD"));
//        setValue(tMsg.mtrReadWinDaysAot, (BigDecimal) map.get("MTR_READ_WIN_DAYS_AOT"));
//        setValue(tMsg.allwDataCrctFlg, (String) map.get("ALLW_DATA_CRCT_FLG"));
//        setValue(tMsg.dsContrClsCd, (String) map.get("DS_CONTR_CLS_CD"));
//        setValue(tMsg.contrCloDay, (String) map.get("CONTR_CLO_DAY"));
//        setValue(tMsg.contrBllgDay, (String) map.get("CONTR_BLLG_DAY"));
//        setValue(tMsg.bllgRollOverRatio, (BigDecimal) map.get("BLLG_ROLL_OVER_RATIO"));
//        setValue(tMsg.printDtlFlg, (String) map.get("PRINT_DTL_FLG"));
//
//        return tMsg;
//    }
    // del end 2017/09/15 QC#19775

    private static BigDecimal getStartMtrCnt(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getStartMtrCnt(glblCmpyCd, dsContrIntfcPk);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        if (map == null) {
            return null;
        }
        return (BigDecimal) map.get("START_MTR_CNT");
    }

    private static String getFirstMonth(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        Calendar firstCal = Calendar.getInstance();
        firstCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

        int firstDay = firstCal.getActualMinimum(Calendar.DATE);
        firstCal.set(Calendar.DATE, firstDay);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(firstCal.getTimeInMillis());
    }

    private static String getEndMonth(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

        int endDay = endCal.getActualMaximum(Calendar.DATE);
        endCal.set(Calendar.DATE, endDay);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(endCal.getTimeInMillis());
    }

    private static Map<String, String> getSameDsContrDtlList(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getSameDsContrDtlList(glblCmpyCd, dsContrIntfcPk);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
        Map<String, String> rtnMap = new HashMap<String, String>();
        boolean setContrFromDtFlag = true;
        boolean setContrThruDtFlag = true;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> targetMap = list.get(i);
            String contrFromDt = (String) rtnMap.get("CONTR_EFF_FROM_DT");
            String contrThruDt = (String) rtnMap.get("CONTR_EFF_THRU_DT");
            String getContrFromDt = (String) targetMap.get("CONTR_EFF_FROM_DT");
            String getContrThruDt = (String) targetMap.get("CONTR_EFF_THRU_DT");
            if (setContrFromDtFlag) {
                if (!ZYPCommonFunc.hasValue(contrFromDt)) {
                    rtnMap.put("CONTR_EFF_FROM_DT", getContrFromDt);
                } else {
                    if (!contrFromDt.equals(getContrFromDt)) {
                        setContrFromDtFlag = false;
                    }
                }
            }
            if (setContrThruDtFlag) {
                if (!ZYPCommonFunc.hasValue(contrThruDt)) {
                    rtnMap.put("CONTR_EFF_THRU_DT", getContrThruDt);
                } else {
                    if (!contrThruDt.equals(getContrThruDt)) {
                        setContrThruDtFlag = false;
                    }
                }
            }
        }
        if (!setContrFromDtFlag) {
            rtnMap.put("CONTR_EFF_FROM_DT", null);
        }
        if (!setContrThruDtFlag) {
            rtnMap.put("CONTR_EFF_THRU_DT", null);
        }
        return rtnMap;
    }

    /**
     * setDefaultRule
     * @param glblCmpyCd String
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param allCheckFlag boolean
     */
    public static void setDefaultRule(String glblCmpyCd, NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, boolean allCheckFlag) {
        // 1.Interface Default Rule
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg target = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (!allCheckFlag) {
                if (!ZYPConstant.FLG_ON_Y.equals(target.xxChkBox_A.getValue())) {
                    continue;
                }
            }
            if (DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_AS.getValue())) {
                continue;
            }
            // mod start 2017/09/15 QC#19775
            //S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getDsContrIntfcDefRule(glblCmpyCd, target.contrIntfcSrcTpCd_AS.getValue());
            S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getDsContrIntfcDefRule(glblCmpyCd, target);
            // mod end 2017/09/15 QC#19775
            Map<String, Object> defRuleMap = (Map<String, Object>) ssmResult.getResultObject();
            if (defRuleMap == null) {
                continue;
            }
            // 1.1.1.General Defaults
            if (hasValue(target.dsContrIntfcPk_A) && isLeaseOrder(glblCmpyCd, target.dsContrIntfcPk_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(target.leaseCmpyCd_A)) {
                    setValue(target.leaseCmpyCd_A, (String) defRuleMap.get("LEASE_CMPY_CD"));
                }
            }
            // 1.1.2.General Defaults
            if (!ZYPCommonFunc.hasValue(target.mtrReadMethCd_AS)) {
                setValue(target.mtrReadMethCd_AS, (String) defRuleMap.get("MTR_READ_METH_CD"));
            }
            // 1.1.3.General Defaults
            if (!ZYPCommonFunc.hasValue(target.dsContrClsCd_AS)) {
                setValue(target.dsContrClsCd_AS, (String) defRuleMap.get("DS_CONTR_CLS_CD"));
            }
            // 1.1.4.General Defaults
// START 2016/02/02 T.Iwamoto [QC#3995 DEL]
//            if (!ZYPCommonFunc.hasValue(target.printDtlFlg_A)) {
//                setValue(target.printDtlFlg_A, (String) defRuleMap.get("PRINT_DTL_FLG"));
//            }
// END 2016/02/02 T.Iwamoto [QC#3995 DEL]

            // 1.3.Renewal Defaults
            if (!ZYPCommonFunc.hasValue(target.contrAutoRnwTpCd_AS)) {
                if (ZYPCommonFunc.hasValue((String) defRuleMap.get("CONTR_AUTO_RNW_TP_CD"))) {
                    setValue(target.contrAutoRnwTpCd_AS, (String) defRuleMap.get("CONTR_AUTO_RNW_TP_CD"));
                } else {
                    setValue(target.contrAutoRnwTpCd_AS, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
                }
            }
            // 1.4.Overage Defaults
            // mod start 2017/09/15 QC#19775
            //if (START_READ_VLD_TP.INSERT_READING.equals((String) defRuleMap.get("START_READ_VLD_TP_CD")) && ((BigDecimal) defRuleMap.get("MTR_READ_WIN_DAYS_AOT")) != null) {
            if (START_READ_VLD_TP.INSERT_READING.equals((String) defRuleMap.get("START_READ_VLD_TP_CD")) && CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd_A.getValue()) && !hasValue(target.startMtrCnt_A)) {
            // mod end 2017/09/15 QC#19775
                if (hasValue(target.dsContrIntfcPk_A)) {
                    BigDecimal startMtrCnt = getStartMtrCnt(glblCmpyCd, target.dsContrIntfcPk_A.getValue());
                    setValue(target.startMtrCnt_A, startMtrCnt);
                }
            }
            // 1.6.Entitlement Default
            if (!ZYPCommonFunc.hasValue(target.mtrEstTpCd_AS)) {
                setValue(target.mtrEstTpCd_AS, (String) defRuleMap.get("MTR_EST_TP_CD"));
            }
            if (!ZYPCommonFunc.hasValue(target.bllgCycleCd_AS)) {
                if (CONTR_INTFC_DTL_TP.BASE.equals(target.contrIntfcDtlTpCd_A.getValue())) {
                    setValue(target.bllgCycleCd_AS, (String) defRuleMap.get("BASE_BLLG_CYCLE_CD"));
                } else if (CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd_A.getValue())) {
                    setValue(target.bllgCycleCd_AS, (String) defRuleMap.get("MTR_BLLG_CYCLE_CD"));
                }
            }
// START 2016/02/02 T.Iwamoto [QC#3995 DEL]
//            if (!ZYPCommonFunc.hasValue(target.invSeptBaseUsgFlg_A)) {
//                setValue(target.invSeptBaseUsgFlg_A, (String) defRuleMap.get("INV_SEPT_BASE_USG_FLG"));
//            }
//            if (!ZYPCommonFunc.hasValue(target.prcAllocByMachQtyFlg_A)) {
//                setValue(target.prcAllocByMachQtyFlg_A, (String) defRuleMap.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
//            }
// END 2016/02/02 T.Iwamoto [QC#3995 DEL]

            // 1.7.
            if (!ZYPCommonFunc.hasValue(target.contrUplftTpCd_AS)) {
                setValue(target.contrUplftTpCd_AS, (String) defRuleMap.get("CONTR_UPLFT_TP_CD"));
            }
            if (!ZYPCommonFunc.hasValue(target.uplftPrcMethCd_AS)) {
                setValue(target.uplftPrcMethCd_AS, (String) defRuleMap.get("UPLFT_PRC_METH_CD"));
            }
            if (!ZYPCommonFunc.hasValue(target.uplftPrcUpRatio_A)) {
                if (CONTR_INTFC_DTL_TP.BASE.equals(target.contrIntfcDtlTpCd_A.getValue())) {
                    setValue(target.uplftPrcUpRatio_A, (BigDecimal) defRuleMap.get("UPLFT_BASE_PRC_UP_RATIO"));
                } else if (CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd_A.getValue())) {
                    setValue(target.uplftPrcUpRatio_A, (BigDecimal) defRuleMap.get("UPLFT_MTR_PRC_UP_RATIO"));
                }
            }
            // 1.8.
            if (!ZYPCommonFunc.hasValue(target.bllgThruDt_A)) {
                if (ZYPCommonFunc.hasValue((String) defRuleMap.get("BLLG_THRU_DT"))) {
                    setValue(target.bllgThruDt_A, (String) defRuleMap.get("BLLG_THRU_DT"));
                } else {
                    if (BLLG_THRU_TP.SYSDATE.equals((String) defRuleMap.get("BLLG_THRU_TP_CD"))) {
                        setValue(target.bllgThruDt_A, ZYPDateUtil.getSalesDate(glblCmpyCd));
                    }
                }
            }
            // 1.9.Effectivity Defaults
            if (STUB_PREP_FROM_TP.EXTEND_START_MONTH.equals((String) defRuleMap.get("STUB_PREP_FROM_TP_CD"))) {
                if (ZYPCommonFunc.hasValue(target.contrFromDt_A)) {
                    String monthEnd = getFirstMonth(target.contrFromDt_A.getValue());
                    setValue(target.contrFromDt_A, monthEnd);
                }
            }
            if (STUB_PREP_THRU_TP.EXTEND_END_MONTH.equals((String) defRuleMap.get("STUB_PREP_THRU_TP_CD"))) {
                if (ZYPCommonFunc.hasValue(target.contrThruDt_A)) {
                    String monthEnd = getEndMonth(target.contrThruDt_A.getValue());
                    setValue(target.contrThruDt_A, monthEnd);
                }
            }
            if (hasValue(target.dsContrIntfcPk_A)) {
                Map<String, String> defMap = getSameDsContrDtlList(glblCmpyCd, target.dsContrIntfcPk_A.getValue());
                if (ZYPConstant.FLG_ON_Y.equals((String) defRuleMap.get("AUTO_EFF_FLEET_FLG")) && !ZYPCommonFunc.hasValue(target.contrFromDt_A) && !ZYPCommonFunc.hasValue(target.contrThruDt_A)) {
                    if (DS_CONTR_CATG.FLEET.equals(target.dsContrCatgCd_AS.getValue()) && DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(target.dsContrIntfcActCd_AS.getValue())) {
                        setValue(target.contrFromDt_A, (String) defMap.get("CONTR_EFF_FROM_DT"));
                        setValue(target.contrThruDt_A, (String) defMap.get("CONTR_EFF_THRU_DT"));
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals((String) defRuleMap.get("AUTO_EFF_AGGR_FLG")) && !ZYPCommonFunc.hasValue(target.contrFromDt_A) && !ZYPCommonFunc.hasValue(target.contrThruDt_A)) {
                    if (DS_CONTR_CATG.AGGREGATE.equals(target.dsContrCatgCd_AS.getValue()) && DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(target.dsContrIntfcActCd_AS.getValue())) {
                        setValue(target.contrFromDt_A, (String) defMap.get("CONTR_EFF_FROM_DT"));
                        setValue(target.contrThruDt_A, (String) defMap.get("CONTR_EFF_THRU_DT"));
                    }
                }
            }
            // Set MDL_ID
            if (ZYPCommonFunc.hasValue(target.svcMachMstrPk_A)) {
                setValue(target.mdlId_A, getMdlID(glblCmpyCd, target.svcMachMstrPk_A.getValue()));
            }
        }
    }

    private static BigDecimal getMdlID(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_CONFIG_MSTRTMsgArray list = (SVC_CONFIG_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list != null && list.getValidCount() > 0) {
            SVC_CONFIG_MSTRTMsg tMsg = (SVC_CONFIG_MSTRTMsg) list.get(0);
            return tMsg.mdlId.getValue();
        }
        return null;
    }

    /**
     * createAndUpdateDsContrIntfc
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param glblCmpyCd String
     * @param allFlag boolean
     * @param isSave boolean
     */
    public static void createAndUpdateDsContrIntfc(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, String glblCmpyCd, boolean allFlag, boolean isSave) {
        // ADD start 2016/04/01 CSA Defect#6335
        BigDecimal dsContrIntfcBatSq = NSAL0810Query.getInstance().getMaxPlusOneSequence(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd));
        String dsContrIntfcBatNum = getDsContrIntfcBatNum(dsContrIntfcBatSq, ZYPDateUtil.getSalesDate(glblCmpyCd));
        // ADD end 2016/04/01 CSA Defect#6335

        // MOD start 2016/04/07 CSA Defect#5662
        List<BigDecimal> targetPkList = new ArrayList<BigDecimal>();
        // MOD end 2016/04/07 CSA Defect#5662

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0810_ASMsg target = (NSAL0810_ASMsg) sMsg.A.get(i);
            if (!allFlag) {
                if (!ZYPConstant.FLG_ON_Y.equals(target.xxChkBox_A.getValue())) {
                    continue;
                }
            }
            if (DS_CONTR_PROC_STS.COMPLEATED.equals(target.dsContrProcStsCd_AS.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(target.dsContrIntfcPk_A)) {
                // Update
                DS_CONTR_INTFCTMsg tMsg = findDsContrIntfcForUpdate(glblCmpyCd, target.dsContrIntfcPk_A.getValue());
                if (tMsg == null) {

                    cMsg.setMessageInfo(NACM0747E, new String[] {"DS Contract Interface", null });
                    return;
                }
                // MOD start 2016/04/01 CSA Defect#6335
                setDsContrIntfc(tMsg, target, glblCmpyCd, false, isSave, dsContrIntfcBatNum, dsContrIntfcBatSq);
                // MOD end 2016/04/01 CSA Defect#6335
                S21FastTBLAccessor.update(tMsg);
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    BigDecimal dsContrIntfcPk = tMsg.dsContrIntfcPk.getValue();
                    String errerDsContrIntfcPk = null;
                    if (ZYPCommonFunc.hasValue(dsContrIntfcPk)) {
                        errerDsContrIntfcPk = dsContrIntfcPk.toString();
                    }
                    cMsg.setMessageInfo(NACM0747E, new String[] {"DS Contract Interface", errerDsContrIntfcPk });
                    return;
                }
                // MOD start 2016/04/07 CSA Defect#5662
                setValue(target.dsContrProcStsCd_AS, tMsg.dsContrProcStsCd);
                targetPkList.add(tMsg.dsContrIntfcPk.getValue());
                // MOD end 2016/04/07 CSA Defect#5662
            } else {
                // Insert
                DS_CONTR_INTFCTMsg tMsg = new DS_CONTR_INTFCTMsg();
                // MOD start 2016/04/01 CSA Defect#6335
                setDsContrIntfc(tMsg, target, glblCmpyCd, true, isSave, dsContrIntfcBatNum, dsContrIntfcBatSq);
                // MOD end 2016/04/01 CSA Defect#6335
                S21FastTBLAccessor.create(tMsg);
                if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    BigDecimal dsContrIntfcPk = tMsg.dsContrIntfcPk.getValue();
                    String errerDsContrIntfcPk = null;
                    if (ZYPCommonFunc.hasValue(dsContrIntfcPk)) {
                        errerDsContrIntfcPk = dsContrIntfcPk.toString();
                    }
                    cMsg.setMessageInfo(NACM0746E, new String[] {"DS Contract Interface", errerDsContrIntfcPk });
                    return;
                }
                // MOD start 2016/04/07 CSA Defect#5662
                setValue(target.dsContrProcStsCd_AS, tMsg.dsContrProcStsCd);
                targetPkList.add(tMsg.dsContrIntfcPk.getValue());
                // MOD end 2016/04/07 CSA Defect#5662
                // START 2016/06/16 [QC#4061, ADD]
                setValue(target.dsContrIntfcPk_A, tMsg.dsContrIntfcPk);
                setValue(target.dsContrIntfcBatNum_A, tMsg.dsContrIntfcBatNum);
                // END   2016/06/16 [QC#4061, ADD]
            }
        }

        // MOD start 2016/04/07 CSA Defect#5662
        // update ProcStatusCode
        if (targetPkList.size() > 0) {
            updateProcStatus(cMsg, sMsg, targetPkList);
        }
        // MOD start 2016/04/07 CSA Defect#5662

        cMsg.setMessageInfo(ZZM8100I);
    }

    // ADD start 2016/04/01 CSA Defect#6335
    private static String getDsContrIntfcBatNum(BigDecimal dsContrIntfcSqNum, String salesDate) {
        return salesDate + ZYPCommonFunc.leftPad(String.valueOf(dsContrIntfcSqNum), LPAD_LEN, PAD_STR);
    }
    // ADD end 2016/04/01 CSA Defect#6335

    // ADD 2016/04/07 CSA Defect#5662
    private static void updateProcStatus(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, List<BigDecimal> targetPkList) {

        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getStatusUpdateTarget(cMsg.glblCmpyCd.getValue(), targetPkList);
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (Map<String, Object> target : list) {
            DS_CONTR_INTFCTMsg tMsg = findDsContrIntfcForUpdate((String) target.get("GLBL_CMPY_CD"), (BigDecimal) target.get("DS_CONTR_INTFC_PK"));
            if (tMsg == null) {
                cMsg.setMessageInfo(NACM0747E, new String[] {"DS Contract Interface", ((BigDecimal) target.get("DS_CONTR_INTFC_PK")).toString() });
                return;
            }
            setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
            S21FastTBLAccessor.update(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NACM0747E, new String[] {"DS Contract Interface", ((BigDecimal) target.get("DS_CONTR_INTFC_PK")).toString() });
                return;
            }
            setProcStsForSMsg(sMsg, (BigDecimal) target.get("DS_CONTR_INTFC_PK"));
        }
        return;
    }

    // ADD 2016/04/07 CSA Defect#5662
    private static void setProcStsForSMsg(NSAL0810SMsg sMsg, BigDecimal targetPk) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (targetPk.compareTo(sMsg.A.no(i).dsContrIntfcPk_A.getValue()) == 0) {
                setValue(sMsg.A.no(i).dsContrProcStsCd_AS, DS_CONTR_PROC_STS.ERROR);
                break;
            }
        }
    }

    private static void setDsContrIntfc(DS_CONTR_INTFCTMsg tMsg, NSAL0810_ASMsg target, String glblCmpyCd, boolean isCreate, boolean isSave, String dsContrIntfcBatNum, BigDecimal dsContrIntfcBatSq) {
        if (isCreate) {
            setValue(tMsg.glblCmpyCd, glblCmpyCd);
            BigDecimal dsContrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ);
            setValue(tMsg.dsContrIntfcPk, dsContrIntfcPk);
        }
        setValue(tMsg.contrIntfcSrcTpCd, target.contrIntfcSrcTpCd_AS);
        setValue(tMsg.dsContrSrcRefNum, target.dsContrSrcRefNum_A);
        if (!hasValue(target.dsContrIntfcBatNum_A)) {
            // MOD start 2016/04/01 CSA Defect#6335
            setValue(tMsg.dsContrIntfcBatNum, dsContrIntfcBatNum);
            // MOD end 2016/04/01 CSA Defect#6335
        } else {
            setValue(tMsg.dsContrIntfcBatNum, target.dsContrIntfcBatNum_A);
        }
        // MOD start 2016/04/01 CSA Defect#6334
//        if (isCreate) {
            setValue(tMsg.dsContrNum, target.dsContrNum_A);
//        }
        // MOD end 2016/04/01 CSA Defect#6334
        setValue(tMsg.dsContrIntfcActCd, target.dsContrIntfcActCd_AS);
        setValue(tMsg.serNum, target.serNum_A);
        setValue(tMsg.svcMachMstrPk, target.svcMachMstrPk_A);
        setValue(tMsg.contrIntfcDtlTpCd, target.contrIntfcDtlTpCd_AS);
        if (isCreate) {
            setValue(tMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
        }
        if (!isSave) {
            if (isCreate) {
                setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
            } else {
                if (DS_CONTR_PROC_STS.CREATE.equals(target.dsContrProcStsCd_AS.getValue())) {
                    setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
                } else if (DS_CONTR_PROC_STS.ERROR.equals(target.dsContrProcStsCd_AS.getValue())) {
                    setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.REPROCESS);
                }
            }
        } else {
            setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.CREATE);
        }
        setValue(tMsg.dsAcctNum, target.dsAcctNum_A);
        setValue(tMsg.billToCustCd, target.billToCustCd_A);
        setValue(tMsg.billToLocNum, (String) null);
        setValue(tMsg.leaseCmpyCd, target.leaseCmpyCd_A);
        setValue(tMsg.svcContrBrCd, target.svcContrBrCd_A);
        // add start 2016/06/30 CSA Defect#10661
        setValue(tMsg.contrAdminPsnCd, target.contrAdminPsnCd_A);
        // add end 2016/06/30 CSA Defect#10661
        setValue(tMsg.tocCd, target.tocCd_A);
        setValue(tMsg.custPoNum, target.custPoNum_A);
        setValue(tMsg.poDt, target.poDt_A);
        setValue(tMsg.crCardCustRefNum, target.crCardCustRefNum_A);
        setValue(tMsg.crCardExprYrMth, target.crCardExprYrMth_A);
        setValue(tMsg.mtrEstTpCd, target.mtrEstTpCd_AS);
        setValue(tMsg.svcPgmMdseCd, target.svcPgmMdseCd_A);
        setValue(tMsg.mdseCd, target.mdseCd_A);
        setValue(tMsg.mdlId, target.mdlId_A);
        setValue(tMsg.mdlNm, target.mdlNm_A);
        setValue(tMsg.contrFromDt, target.contrFromDt_A);
        setValue(tMsg.contrThruDt, target.contrThruDt_A);
        setValue(tMsg.bllgCycleCd, target.bllgCycleCd_AS);
        if (ZYPConstant.FLG_ON_Y.equals(target.prcAllocByMachQtyFlg_A.getValue())) {
            setValue(tMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(tMsg.contrAutoRnwTpCd, target.contrAutoRnwTpCd_AS);
        setValue(tMsg.rnwPrcMethCd, target.rnwPrcMethCd_AS);
        setValue(tMsg.befEndRnwDaysAot, target.befEndRnwDaysAot_A);
        setValue(tMsg.rnwPrcUpRatio, target.rnwPrcUpRatio_A);
        setValue(tMsg.contrUplftTpCd, target.contrUplftTpCd_AS);
        setValue(tMsg.uplftPrcMethCd, target.uplftPrcMethCd_AS);
        setValue(tMsg.uplftPrcUpRatio, target.uplftPrcUpRatio_A);
        setValue(tMsg.mtrReadMethCd, target.mtrReadMethCd_AS);
        setValue(tMsg.basePrcDealAmt, target.basePrcDealAmt_A);
        setValue(tMsg.contrCloDay, target.contrCloDay_A);
        setValue(tMsg.contrBllgDay, target.contrBllgDay_A);
        setValue(tMsg.bllgThruDt, target.bllgThruDt_A);
        setValue(tMsg.bllgMtrLbCd, target.bllgMtrLbCd_A);
        if (ZYPCommonFunc.hasValue(target.bllgMtrLbCd_A)) {
            String bllgMtrLbNm = getBllgMtrLbNm(glblCmpyCd, target.bllgMtrLbCd_A.getValue());
            if (hasValue(bllgMtrLbNm)) {
                setValue(tMsg.bllgMtrLbNm, bllgMtrLbNm);
            }
        }
        setValue(tMsg.startMtrCnt, target.startMtrCnt_A);
        setValue(tMsg.bllgRollOverRatio, target.bllgRollOverRatio_A);
        setValue(tMsg.dsContrCatgCd, target.dsContrCatgCd_AS);
        setValue(tMsg.dsContrStsCd, target.dsContrStsCd_AS);
        setValue(tMsg.xsChrgTpCd, target.xsChrgTpCd_AS);
        setValue(tMsg.xsMtrCopyQty, target.xsMtrCopyQty_A);
        setValue(tMsg.xsMtrAmtRate, target.xsMtrAmtRate_A);
        setValue(tMsg.addlChrgTpCd, target.addlChrgTpCd_AS);
        setValue(tMsg.addlChrgFlatDealPrcAmt, target.addlChrgFlatDealPrcAmt_A);
        setValue(tMsg.addlChrgAplcPct, target.addlChrgAplcPct_A);
        setValue(tMsg.basePrcTermDealAmtRate, target.basePrcTermDealAmtRate_A);
        setValue(tMsg.dsContrClsCd, target.dsContrClsCd_AS);
        setValue(tMsg.ctacPsnPk, target.ctacPsnPk_A);
        setValue(tMsg.ctacPsnNm, target.ctacPsnNm_A);
        // START 2022/03/22 [QC#59683, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(target.invSeptBaseUsgFlg_A.getValue())) {
//            setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_ON_Y);
//        } else {
//            setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
//        }
        setValue(tMsg.dsInvTgtrTpCd, target.dsInvTgtrTpCd_AS);
        if (DS_INV_TGTR_TP.BILL_ALL_BASE_AND_USAGE_TOGETHER.equals(tMsg.dsInvTgtrTpCd.getValue())) {
            setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
        } else {
            setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_ON_Y);
        }
        // END   2022/03/22 [QC#59683, MOD]
        // START 2018/02/22 M.Naito [QC23179, MOD]
        // Mod Start 2017/11/20 QC#21724
//        setValue(tMsg.contrCloDt, addCloDt(target.contrCloDt_A.getValue(), -1));
        // Mod End 2017/11/20 QC#21724
        setValue(tMsg.contrCloDt, target.contrCloDt_A);
        // START 2018/02/22 M.Naito [QC23179, MOD]
        setValue(tMsg.contrDurnAot, target.contrDurnAot_A);
        setValue(tMsg.pmtTermCashDiscCd, target.pmtTermCashDiscCd_A);
        setValue(tMsg.svcLineBizCd, target.svcLineBizCd_AS);
        setValue(tMsg.bllgTmgTpCd, target.bllgTmgTpCd_AS);
        setValue(tMsg.dsContrEdiCd, target.dsContrEdiCd_A);
        if (isCreate) {
            setValue(tMsg.dsContrIntfcDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        }
        setValue(tMsg.dsContrDescTxt, target.dsContrDescTxt_A);
        if (ZYPConstant.FLG_ON_Y.equals(target.baseChrgToLeaseCmpyFlg_A.getValue())) {
            setValue(tMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(target.usgChrgToLeaseCmpyFlg_A.getValue())) {
            setValue(tMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        }
        setValue(tMsg.intgMdseCd, target.intgMdseCd_A);
        setValue(tMsg.capBwOrigQty, target.capBwOrigQty_A);
        setValue(tMsg.capColorOrigQty, target.capColorOrigQty_A);
        setValue(tMsg.capTotOrigQty, target.capTotOrigQty_A);
        setValue(tMsg.capBwRunQty, target.capBwRunQty_A);
        setValue(tMsg.capColorRunQty, target.capColorRunQty_A);
        setValue(tMsg.capTotRunQty, target.capTotRunQty_A);
        setValue(tMsg.chrgLvlTpCd, target.chrgLvlTpCd_AS);
        setValue(tMsg.addlChrgInvTpCd, target.addlChrgInvTpCd_AS);
        // ADD start 2016/04/01 CSA Defect#6335
        setValue(tMsg.dsContrIntfcBatSq, dsContrIntfcBatSq);
        // ADD end 2016/04/01 CSA Defect#6335
        if (ZYPConstant.FLG_ON_Y.equals(target.printDtlFlg_A.getValue())) {
            setValue(tMsg.printDtlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2016/05/20 [QC#4061, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(target.manContrOvrdFlg_A.getValue())) {
            setValue(tMsg.manContrOvrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(tMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        }
        // END   2016/05/20 [QC#4061, ADD]
    }

    // DELETE start 2016/04/01 CSA Defect#6335
//    private static String getMaxPlusOneDsContrIntfcBatNum(String glblCmpyCd) {
//        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getMaxPlusOneSequence(glblCmpyCd, ZYPDateUtil.getSalesDate(glblCmpyCd));
//        if (!ssmResult.isCodeNormal()) {
//            return null;
//        }
//        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
//        if (map == null) {
//            return null;
//        }
//
//        String maxDsContrIntfcBatNum = (String) map.get("DS_CONTR_INTFC_BAT_NUM");
//        if (!hasValue(maxDsContrIntfcBatNum)) {
//            return null;
//        }
//        long maxValue = Long.valueOf(maxDsContrIntfcBatNum);
//        maxValue = maxValue + 1;
//        maxDsContrIntfcBatNum = String.valueOf(maxValue);
//        return maxDsContrIntfcBatNum;
//    }
    // DELETE end 2016/04/01 CSA Defect#6335

    private static DS_CONTR_INTFCTMsg findDsContrIntfcForUpdate(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    /**
     * setDsActlCntIntfc
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param allFlag boolean
     * @return tMsgList
     */
    public static List<DS_CONTR_INTFCTMsg> setDsContrIntfc(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, boolean allFlag) {
        List<DS_CONTR_INTFCTMsg> tMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!allFlag) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                    continue;
                }
            }
            if (DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_AS.getValue())) {
                continue;
            }

            DS_CONTR_INTFCTMsg tMsg = new DS_CONTR_INTFCTMsg();
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.dsContrIntfcPk, sMsg.A.no(i).dsContrIntfcPk_A);
            setValue(tMsg.dsContrIntfcBatNum, sMsg.A.no(i).dsContrIntfcBatNum_A);
            setValue(tMsg.dsContrSrcRefNum, sMsg.A.no(i).dsContrSrcRefNum_A);
            setValue(tMsg.contrIntfcSrcTpCd, sMsg.A.no(i).contrIntfcSrcTpCd_AS);
            setValue(tMsg.dsContrIntfcActCd, sMsg.A.no(i).dsContrIntfcActCd_AS);
            setValue(tMsg.dsContrProcStsCd, sMsg.A.no(i).dsContrProcStsCd_AS);
            setValue(tMsg.dsContrIntfcStsCd, sMsg.A.no(i).dsContrIntfcStsCd_A);
            setValue(tMsg.intfcErrMsgTxt, sMsg.A.no(i).intfcErrMsgTxt_A);
            setValue(tMsg.dsContrNum, sMsg.A.no(i).dsContrNum_A);
            setValue(tMsg.serNum, sMsg.A.no(i).serNum_A);
            setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
            setValue(tMsg.contrIntfcDtlTpCd, sMsg.A.no(i).contrIntfcDtlTpCd_AS);
            setValue(tMsg.dsAcctNum, sMsg.A.no(i).dsAcctNum_A);
            setValue(tMsg.billToCustCd, sMsg.A.no(i).billToCustCd_A);
            setValue(tMsg.billToLocNum, sMsg.A.no(i).billToLocNum_A);
            setValue(tMsg.leaseCmpyCd, sMsg.A.no(i).leaseCmpyCd_A);
            setValue(tMsg.svcContrBrCd, sMsg.A.no(i).svcContrBrCd_A);
            setValue(tMsg.tocCd, sMsg.A.no(i).tocCd_A);
            setValue(tMsg.custPoNum, sMsg.A.no(i).custPoNum_A);
            setValue(tMsg.poDt, sMsg.A.no(i).poDt_A);
            setValue(tMsg.crCardCustRefNum, sMsg.A.no(i).crCardCustRefNum_A);
            setValue(tMsg.crCardExprYrMth, sMsg.A.no(i).crCardExprYrMth_A);
            setValue(tMsg.mtrEstTpCd, sMsg.A.no(i).mtrEstTpCd_AS);
            setValue(tMsg.svcPgmMdseCd, sMsg.A.no(i).svcPgmMdseCd_A);
            setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A);
            setValue(tMsg.mdlId, sMsg.A.no(i).mdlId_A);
            setValue(tMsg.mdlNm, sMsg.A.no(i).mdlNm_A);
            setValue(tMsg.contrFromDt, sMsg.A.no(i).contrFromDt_A);
            setValue(tMsg.contrThruDt, sMsg.A.no(i).contrThruDt_A);
            setValue(tMsg.bllgCycleCd, sMsg.A.no(i).bllgCycleCd_AS);
            setValue(tMsg.prcAllocByMachQtyFlg, sMsg.A.no(i).prcAllocByMachQtyFlg_A);
            setValue(tMsg.contrAutoRnwTpCd, sMsg.A.no(i).contrAutoRnwTpCd_AS);
            setValue(tMsg.rnwPrcMethCd, sMsg.A.no(i).rnwPrcMethCd_AS);
            setValue(tMsg.befEndRnwDaysAot, sMsg.A.no(i).befEndRnwDaysAot_A);
            setValue(tMsg.rnwPrcUpRatio, sMsg.A.no(i).rnwPrcUpRatio_A);
            setValue(tMsg.contrUplftTpCd, sMsg.A.no(i).contrUplftTpCd_AS);
            setValue(tMsg.uplftPrcMethCd, sMsg.A.no(i).uplftPrcMethCd_AS);
            setValue(tMsg.uplftPrcUpRatio, sMsg.A.no(i).uplftPrcUpRatio_A);
            setValue(tMsg.mtrReadMethCd, sMsg.A.no(i).mtrReadMethCd_AS);
            setValue(tMsg.basePrcDealAmt, sMsg.A.no(i).basePrcDealAmt_A);
            setValue(tMsg.contrCloDay, sMsg.A.no(i).contrCloDay_A);
            setValue(tMsg.contrBllgDay, sMsg.A.no(i).contrBllgDay_A);
            setValue(tMsg.bllgThruDt, sMsg.A.no(i).bllgThruDt_A);
            // START 2016/05/25 T.Tomita [QC#8898, MOD]
//            setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_AS);
            setValue(tMsg.bllgMtrLbCd, sMsg.A.no(i).bllgMtrLbCd_A);
//            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).bllgMtrLbCd_AS)) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mtrLbDescTxt_A)) {
//                String bllgMtrLbNm = getBllgMtrLbNm(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).bllgMtrLbCd_AS.getValue());
                String bllgMtrLbNm = getBllgMtrLbNm(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).bllgMtrLbCd_A.getValue());
                if (hasValue(bllgMtrLbNm)) {
                    setValue(tMsg.bllgMtrLbNm, bllgMtrLbNm);
                }
            }
            // END 2016/05/25 T.Tomita [QC#8898, MOD]
            setValue(tMsg.startMtrCnt, sMsg.A.no(i).startMtrCnt_A);
            setValue(tMsg.bllgRollOverRatio, sMsg.A.no(i).bllgRollOverRatio_A);
            setValue(tMsg.dsContrCatgCd, sMsg.A.no(i).dsContrCatgCd_AS);
            setValue(tMsg.dsContrStsCd, sMsg.A.no(i).dsContrStsCd_AS);
            setValue(tMsg.xsChrgTpCd, sMsg.A.no(i).xsChrgTpCd_AS);
            setValue(tMsg.xsMtrCopyQty, sMsg.A.no(i).xsMtrCopyQty_A);
            setValue(tMsg.xsMtrAmtRate, sMsg.A.no(i).xsMtrAmtRate_A);
            setValue(tMsg.addlChrgTpCd, sMsg.A.no(i).addlChrgTpCd_AS);
            setValue(tMsg.addlChrgFlatDealPrcAmt, sMsg.A.no(i).addlChrgFlatDealPrcAmt_A);
            setValue(tMsg.addlChrgAplcPct, sMsg.A.no(i).addlChrgAplcPct_A);
            setValue(tMsg.chrgLvlTpCd, sMsg.A.no(i).chrgLvlTpCd_AS);
            setValue(tMsg.addlChrgInvTpCd, sMsg.A.no(i).addlChrgInvTpCd_AS);
            setValue(tMsg.printDtlFlg, sMsg.A.no(i).printDtlFlg_A);
            setValue(tMsg.basePrcTermDealAmtRate, sMsg.A.no(i).basePrcTermDealAmtRate_A);
            setValue(tMsg.dsContrClsCd, sMsg.A.no(i).dsContrClsCd_AS);
            setValue(tMsg.ctacPsnPk, sMsg.A.no(i).ctacPsnPk_A);
            setValue(tMsg.ctacPsnNm, sMsg.A.no(i).ctacPsnNm_A);
            // START 2022/03/22 [QC#59683, MOD]
//            setValue(tMsg.invSeptBaseUsgFlg, sMsg.A.no(i).invSeptBaseUsgFlg_A);
            setValue(tMsg.dsInvTgtrTpCd, sMsg.A.no(i).dsInvTgtrTpCd_AS);
            if (DS_INV_TGTR_TP.BILL_ALL_BASE_AND_USAGE_TOGETHER.equals(tMsg.dsInvTgtrTpCd.getValue())) {
                setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_OFF_N);
            } else {
                setValue(tMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2022/03/22 [QC#59683, MOD]
            setValue(tMsg.contrCloDt, sMsg.A.no(i).contrCloDt_A);
            setValue(tMsg.contrDurnAot, sMsg.A.no(i).contrDurnAot_A);
            setValue(tMsg.pmtTermCashDiscCd, sMsg.A.no(i).pmtTermCashDiscCd_A);
            setValue(tMsg.svcLineBizCd, sMsg.A.no(i).svcLineBizCd_AS);
            setValue(tMsg.bllgTmgTpCd, sMsg.A.no(i).bllgTmgTpCd_AS);
            setValue(tMsg.dsContrEdiCd, sMsg.A.no(i).dsContrEdiCd_A);
            setValue(tMsg.dsContrIntfcDt, sMsg.A.no(i).dsContrIntfcDt_A);
            setValue(tMsg.dsContrDescTxt, sMsg.A.no(i).dsContrDescTxt_A);
            setValue(tMsg.baseChrgToLeaseCmpyFlg, sMsg.A.no(i).baseChrgToLeaseCmpyFlg_A);
            setValue(tMsg.usgChrgToLeaseCmpyFlg, sMsg.A.no(i).usgChrgToLeaseCmpyFlg_A);
            setValue(tMsg.intgMdseCd, sMsg.A.no(i).intgMdseCd_A);
            setValue(tMsg.cpoSvcDtlPk, sMsg.A.no(i).cpoSvcDtlPk_A);
            setValue(tMsg.capBwOrigQty, sMsg.A.no(i).capBwOrigQty_A);
            setValue(tMsg.capColorOrigQty, sMsg.A.no(i).capColorOrigQty_A);
            setValue(tMsg.capTotOrigQty, sMsg.A.no(i).capTotOrigQty_A);
            setValue(tMsg.capBwRunQty, sMsg.A.no(i).capBwRunQty_A);
            setValue(tMsg.capColorRunQty, sMsg.A.no(i).capColorRunQty_A);
            setValue(tMsg.capTotRunQty, sMsg.A.no(i).capTotRunQty_A);
            // START 2016/05/20 [QC#4061, ADD]
            setValue(tMsg.manContrOvrdFlg, sMsg.A.no(i).manContrOvrdFlg_A);
            // END   2016/05/20 [QC#4061, ADD]
            setValue(tMsg.ezUpTime, sMsg.A.no(i).ezUpTime_A);
            setValue(tMsg.ezUpTimeZone, sMsg.A.no(i).ezUpTimeZone_A);
            tMsgList.add(tMsg);
        }
        // mod start 2016/08/31 CSA QC#12566
        List<DS_CONTR_INTFCTMsg> tMsgSortList = new ArrayList<DS_CONTR_INTFCTMsg>();
        tMsgSortList = getSortListByBatNum(tMsgList);
        return tMsgSortList;
        // mod end 2016/08/31 CSA QC#12566
    }

    /**
     * updateValidationResult
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param rstMsgList List<DS_CONTR_INTFCTMsg>
     * @param validFlg boolean
     * @param allFlg boolean
     */
    public static void updateValidationResult(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, List<DS_CONTR_INTFCTMsg> rstMsgList, boolean validFlg, boolean allFlg) {
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_CONTR_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (validFlg && hasValue(rstMsg.dsContrIntfcPk)) {
                DS_CONTR_INTFCTMsg updatedTMsg = getContrIntfc(cMsg.glblCmpyCd.getValue(), rstMsg.dsContrIntfcPk.getValue());
                for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                    if ((!allFlg && ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(j).xxChkBox_A.getValue())) || allFlg) {
                        if (hasValue(sMsg.A.no(j).dsContrIntfcPk_A) && sMsg.A.no(j).dsContrIntfcPk_A.getValue().equals(rstMsg.dsContrIntfcPk.getValue())) {
                            if (updatedTMsg != null) {
                                setValue(sMsg.A.no(j).intfcErrMsgTxt_A, updatedTMsg.intfcErrMsgTxt);
                                setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, updatedTMsg.dsContrIntfcStsCd);
                                setValue(sMsg.A.no(j).ezUpTime_A, updatedTMsg.ezUpTime);
                                setValue(sMsg.A.no(j).ezUpTimeZone_A, updatedTMsg.ezUpTimeZone);
                            } else {
                                setValue(sMsg.A.no(j).intfcErrMsgTxt_A, rstMsg.intfcErrMsgTxt);
                                setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, rstMsg.dsContrIntfcStsCd);
                            }
                        }
                    }
                }
            } else {
                for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                    if ((!allFlg && ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(j).xxChkBox_A.getValue())) || allFlg) {
                        if (matchLine(sMsg.A.no(j), rstMsg)) {
                            setValue(sMsg.A.no(j).intfcErrMsgTxt_A, rstMsg.intfcErrMsgTxt);
                            setValue(sMsg.A.no(j).dsContrIntfcStsCd_A, rstMsg.dsContrIntfcStsCd);
                        }
                    }
                }
            }
        }
    }

 // START 2016/02/15 [QC#3314, ADD]
    /**
     * updateCommonValidationResult
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param vrBean ValidationReturnBean
     * @param tMsg DS_CONTR_INTFCTMsg
     * @param validFlg boolean
     * @param allFlg boolean
     */
    public static void updateCommonValidationResult(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, ValidationReturnBean vrBean, DS_CONTR_INTFCTMsg tMsg, boolean validFlg, boolean allFlg) {
        if (vrBean.xxMsgIdList.getValidCount() == 0 || hasValue(tMsg.intfcErrMsgTxt)) {
            return;
        }

        if (!validFlg || !hasValue(tMsg.dsContrIntfcPk)) {
            return;
        }

        DS_CONTR_INTFCTMsg updatedTMsg = getContrIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if ((!allFlg && ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) || allFlg) {
                if (hasValue(sMsg.A.no(i).dsContrIntfcPk_A) && (sMsg.A.no(i).dsContrIntfcPk_A.getValue().compareTo(tMsg.dsContrIntfcPk.getValue()) == 0)) {
                    setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(vrBean.xxMsgIdList.no(0).xxMsgId.getValue()));
                    setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    setValue(sMsg.A.no(i).ezUpTime_A, updatedTMsg.ezUpTime);
                    setValue(sMsg.A.no(i).ezUpTimeZone_A, updatedTMsg.ezUpTimeZone);
                }
            }
        }
    }

    /**
     * updateCommonValidationResult
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     * @param prmPmsg NSXC001001PMsg
     * @param tMsg DS_CONTR_INTFCTMsg
     * @param validFlg boolean
     * @param allFlg boolean
     */
    public static void updateCommonValidationResult(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg, NSXC001001PMsg prmPmsg, DS_CONTR_INTFCTMsg tMsg, boolean validFlg, boolean allFlg) {
        if (prmPmsg.xxMsgIdList.getValidCount() == 0 || hasValue(tMsg.intfcErrMsgTxt)) {
            return;
        }

        if (!validFlg || !hasValue(tMsg.dsContrIntfcPk)) {
            return;
        }

        DS_CONTR_INTFCTMsg updatedTMsg = getContrIntfc(cMsg.glblCmpyCd.getValue(), tMsg.dsContrIntfcPk.getValue());
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if ((!allFlg && ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) || allFlg) {
                if (hasValue(sMsg.A.no(i).dsContrIntfcPk_A) && sMsg.A.no(i).dsContrIntfcPk_A.getValue().equals(tMsg.dsContrIntfcPk.getValue())) {
                    setValue(sMsg.A.no(i).intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(prmPmsg.xxMsgIdList.no(0).xxMsgId.getValue()));
                    setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    setValue(sMsg.A.no(i).ezUpTime_A, updatedTMsg.ezUpTime);
                    setValue(sMsg.A.no(i).ezUpTimeZone_A, updatedTMsg.ezUpTimeZone);
                }
            }
        }
    }
// END   2016/02/15 [QC#3314, ADD]

    private static boolean matchLine(NSAL0810_ASMsg asMsg, DS_CONTR_INTFCTMsg rstMsg) {
        if (!isMatchObject(asMsg.dsContrIntfcPk_A, rstMsg.dsContrIntfcPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrIntfcBatNum_A, rstMsg.dsContrIntfcBatNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrSrcRefNum_A, rstMsg.dsContrSrcRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrIntfcSrcTpCd_AS, rstMsg.contrIntfcSrcTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrIntfcActCd_AS, rstMsg.dsContrIntfcActCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrProcStsCd_AS, rstMsg.dsContrProcStsCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrNum_A, rstMsg.dsContrNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.serNum_A, rstMsg.serNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcMachMstrPk_A, rstMsg.svcMachMstrPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrIntfcDtlTpCd_AS, rstMsg.contrIntfcDtlTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsAcctNum_A, rstMsg.dsAcctNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.billToCustCd_A, rstMsg.billToCustCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.billToLocNum_A, rstMsg.billToLocNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.leaseCmpyCd_A, rstMsg.leaseCmpyCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcContrBrCd_A, rstMsg.svcContrBrCd)) {
            return false;
        }
        // add start 2016/06/30 CSA Defect#10661
        if (!isMatchObject(asMsg.contrAdminPsnCd_A, rstMsg.contrAdminPsnCd)) {
            return false;
        }
        // add end 2016/06/30 CSA Defect#10661
        if (!isMatchObject(asMsg.tocCd_A, rstMsg.tocCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.custPoNum_A, rstMsg.custPoNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.poDt_A, rstMsg.poDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.crCardCustRefNum_A, rstMsg.crCardCustRefNum)) {
            return false;
        }
        if (!isMatchObject(asMsg.crCardExprYrMth_A, rstMsg.crCardExprYrMth)) {
            return false;
        }
        if (!isMatchObject(asMsg.mtrEstTpCd_AS, rstMsg.mtrEstTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcPgmMdseCd_A, rstMsg.svcPgmMdseCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.mdseCd_A, rstMsg.mdseCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.mdlId_A, rstMsg.mdlId)) {
            return false;
        }
        if (!isMatchObject(asMsg.mdlNm_A, rstMsg.mdlNm)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrFromDt_A, rstMsg.contrFromDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrThruDt_A, rstMsg.contrThruDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllgCycleCd_AS, rstMsg.bllgCycleCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.prcAllocByMachQtyFlg_A, rstMsg.prcAllocByMachQtyFlg)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrAutoRnwTpCd_AS, rstMsg.contrAutoRnwTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.rnwPrcMethCd_AS, rstMsg.rnwPrcMethCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.befEndRnwDaysAot_A, rstMsg.befEndRnwDaysAot)) {
            return false;
        }
        if (!isMatchObject(asMsg.rnwPrcUpRatio_A, rstMsg.rnwPrcUpRatio)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrUplftTpCd_AS, rstMsg.contrUplftTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.uplftPrcMethCd_AS, rstMsg.uplftPrcMethCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.uplftPrcUpRatio_A, rstMsg.uplftPrcUpRatio)) {
            return false;
        }
        if (!isMatchObject(asMsg.mtrReadMethCd_AS, rstMsg.mtrReadMethCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.basePrcDealAmt_A, rstMsg.basePrcDealAmt)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrCloDay_A, rstMsg.contrCloDay)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrBllgDay_A, rstMsg.contrBllgDay)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllgThruDt_A, rstMsg.bllgThruDt)) {
            return false;
        }
        // START 2016/05/25 T.Tomita [QC#8898, MOD]
//        if (!isMatchObject(asMsg.bllgMtrLbCd_AS, rstMsg.bllgMtrLbCd)) {
//            return false;
//        }
        if (!isMatchObject(asMsg.bllgMtrLbCd_A, rstMsg.bllgMtrLbCd)) {
            return false;
        }
        // END 2016/05/25 T.Tomita [QC#8898, MOD]
        if (!isMatchObject(asMsg.startMtrCnt_A, rstMsg.startMtrCnt)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllgRollOverRatio_A, rstMsg.bllgRollOverRatio)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrCatgCd_AS, rstMsg.dsContrCatgCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrStsCd_AS, rstMsg.dsContrStsCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.xsChrgTpCd_AS, rstMsg.xsChrgTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.xsMtrCopyQty_A, rstMsg.xsMtrCopyQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.xsMtrAmtRate_A, rstMsg.xsMtrAmtRate)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgTpCd_AS, rstMsg.addlChrgTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgFlatDealPrcAmt_A, rstMsg.addlChrgFlatDealPrcAmt)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgAplcPct_A, rstMsg.addlChrgAplcPct)) {
            return false;
        }
        if (!isMatchObject(asMsg.chrgLvlTpCd_AS, rstMsg.chrgLvlTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.addlChrgInvTpCd_AS, rstMsg.addlChrgInvTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.printDtlFlg_A, rstMsg.printDtlFlg)) {
            return false;
        }
        if (!isMatchObject(asMsg.basePrcTermDealAmtRate_A, rstMsg.basePrcTermDealAmtRate)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrClsCd_AS, rstMsg.dsContrClsCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.ctacPsnPk_A, rstMsg.ctacPsnPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.ctacPsnNm_A, rstMsg.ctacPsnNm)) {
            return false;
        }
        // START 2022/03/22 [QC#59683, ADD]
//        if (!isMatchObject(asMsg.invSeptBaseUsgFlg_A, rstMsg.invSeptBaseUsgFlg)) {
        if (!isMatchObject(asMsg.dsInvTgtrTpCd_AS, rstMsg.dsInvTgtrTpCd)) {
        // END   2022/03/22 [QC#59683, ADD]
            return false;
        }
        if (!isMatchObject(asMsg.contrCloDt_A, rstMsg.contrCloDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.contrDurnAot_A, rstMsg.contrDurnAot)) {
            return false;
        }
        if (!isMatchObject(asMsg.pmtTermCashDiscCd_A, rstMsg.pmtTermCashDiscCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.svcLineBizCd_AS, rstMsg.svcLineBizCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.bllgTmgTpCd_AS, rstMsg.bllgTmgTpCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrEdiCd_A, rstMsg.dsContrEdiCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrIntfcDt_A, rstMsg.dsContrIntfcDt)) {
            return false;
        }
        if (!isMatchObject(asMsg.dsContrDescTxt_A, rstMsg.dsContrDescTxt)) {
            return false;
        }
        if (!isMatchObject(asMsg.baseChrgToLeaseCmpyFlg_A, rstMsg.baseChrgToLeaseCmpyFlg)) {
            return false;
        }
        if (!isMatchObject(asMsg.usgChrgToLeaseCmpyFlg_A, rstMsg.usgChrgToLeaseCmpyFlg)) {
            return false;
        }
        if (!isMatchObject(asMsg.intgMdseCd_A, rstMsg.intgMdseCd)) {
            return false;
        }
        if (!isMatchObject(asMsg.cpoSvcDtlPk_A, rstMsg.cpoSvcDtlPk)) {
            return false;
        }
        if (!isMatchObject(asMsg.capBwOrigQty_A, rstMsg.capBwOrigQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.capColorOrigQty_A, rstMsg.capColorOrigQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.capTotOrigQty_A, rstMsg.capTotOrigQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.capBwRunQty_A, rstMsg.capBwRunQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.capColorRunQty_A, rstMsg.capColorRunQty)) {
            return false;
        }
        if (!isMatchObject(asMsg.capTotRunQty_A, rstMsg.capTotRunQty)) {
            return false;
        }
        // START 2016/05/20 [QC#4061, ADD]
        if (!isMatchObject(asMsg.manContrOvrdFlg_A, rstMsg.manContrOvrdFlg)) {
            return false;
        }
        // END   2016/05/20 [QC#4061, ADD]

        return true;
    }

    private static boolean isMatchObject(EZDSStringItem asMsgObj, EZDTStringItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchObject(EZDSBigDecimalItem asMsgObj, EZDTBigDecimalItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMatchObject(EZDSDateItem asMsgObj, EZDTDateItem rstMsgObj) {
        if (hasValue(asMsgObj) && !hasValue(rstMsgObj)) {
            return false;
        }
        if (!hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            return false;
        }
        if (hasValue(asMsgObj) && hasValue(rstMsgObj)) {
            if (!asMsgObj.getValue().equals(rstMsgObj.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * get Ds ContractInterfac By Primary Key
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return DS_CONTR_INTFCTMsg
     */
    public static DS_CONTR_INTFCTMsg getContrIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * getDsActlCntrIntfcList
     * @param ssmResult S21SsmEZDResult
     * @return List<DS_ACTL_CNT_INTFCTMsg>
     */
    public static List<DS_ACTL_CNT_INTFCTMsg> getDsActlCntrIntfcList(S21SsmEZDResult ssmResult) {

        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<DS_ACTL_CNT_INTFCTMsg> tMsgList = new ArrayList<DS_ACTL_CNT_INTFCTMsg>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            DS_ACTL_CNT_INTFCTMsg tMsg = new DS_ACTL_CNT_INTFCTMsg();

            setValue(tMsg.glblCmpyCd, (String) map.get("GLBL_CMPY_CD"));
            setValue(tMsg.dsActlCntIntfcPk, (BigDecimal) map.get("DS_ACTL_CNT_INTFC_PK"));
            setValue(tMsg.dsContrIntfcBatNum, (String) map.get("DS_CONTR_INTFC_BAT_NUM"));
            setValue(tMsg.dsContrSrcRefNum, (String) map.get("DS_CONTR_SRC_REF_NUM"));
            setValue(tMsg.contrIntfcSrcTpCd, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
            setValue(tMsg.actlCntIntfcStsCd, (String) map.get("ACTL_CNT_INTFC_STS_CD"));
            setValue(tMsg.intfcErrMsgTxt, (String) map.get("INTFC_ERR_MSG_TXT"));
            setValue(tMsg.serNum, (String) map.get("SER_NUM"));
            setValue(tMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            setValue(tMsg.mdseCd, (String) map.get("MDSE_CD"));
            setValue(tMsg.physMtrLbCd, (String) map.get("PHYS_MTR_LB_CD"));
            setValue(tMsg.physMtrLbNm, (String) map.get("PHYS_MTR_LB_NM"));
            setValue(tMsg.bllblFlg, (String) map.get("BLLBL_FLG"));
            setValue(tMsg.contrMtrMultRate, (BigDecimal) map.get("CONTR_MTR_MULT_RATE"));
            setValue(tMsg.bllgMtrLbCd, (String) map.get("BLLG_MTR_LB_CD"));
            setValue(tMsg.bllgMtrLbNm, (String) map.get("BLLG_MTR_LB_NM"));
            setValue(tMsg.intgMdseCd, (String) map.get("INTG_MDSE_CD"));
            setValue(tMsg.cpoSvcDtlPk, (BigDecimal) map.get("CPO_SVC_DTL_PK"));

            tMsgList.add(tMsg);
        }
        return tMsgList;
    }

    /**
     * getDsXsCopyIntfcList
     * @param ssmResult S21SsmEZDResult
     * @return List<DS_XS_COPY_INTFCTMsg>
     */
    public static List<DS_XS_COPY_INTFCTMsg> getDsXsCopyIntfcList(S21SsmEZDResult ssmResult) {

        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<DS_XS_COPY_INTFCTMsg> tMsgList = new ArrayList<DS_XS_COPY_INTFCTMsg>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            DS_XS_COPY_INTFCTMsg tMsg = new DS_XS_COPY_INTFCTMsg();

            setValue(tMsg.glblCmpyCd, (String) map.get("GLBL_CMPY_CD"));
            setValue(tMsg.dsXsCopyIntfcPk, (BigDecimal) map.get("DS_XS_COPY_INTFC_PK"));
            setValue(tMsg.dsContrIntfcBatNum, (String) map.get("DS_CONTR_INTFC_BAT_NUM"));
            setValue(tMsg.dsContrSrcRefNum, (String) map.get("DS_CONTR_SRC_REF_NUM"));
            setValue(tMsg.contrIntfcSrcTpCd, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
            setValue(tMsg.xsCopyIntfcStsCd, (String) map.get("XS_COPY_INTFC_STS_CD"));
            setValue(tMsg.intfcErrMsgTxt, (String) map.get("INTFC_ERR_MSG_TXT"));
            setValue(tMsg.serNum, (String) map.get("SER_NUM"));
            setValue(tMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            setValue(tMsg.mdseCd, (String) map.get("MDSE_CD"));
            setValue(tMsg.bllgMtrLbCd, (String) map.get("BLLG_MTR_LB_CD"));
            setValue(tMsg.bllgMtrLbNm, (String) map.get("BLLG_MTR_LB_NM"));
            setValue(tMsg.xsMtrCopyQty, (BigDecimal) map.get("XS_MTR_COPY_QTY"));
            setValue(tMsg.xsMtrAmtRate, (BigDecimal) map.get("XS_MTR_AMT_RATE"));
            setValue(tMsg.xsMtrLvlNum, (String) map.get("XS_MTR_LVL_NUM"));
            setValue(tMsg.cpoSvcDtlPk, (BigDecimal) map.get("CPO_SVC_DTL_PK"));

            tMsgList.add(tMsg);
        }
        return tMsgList;
    }

    /**
     * getDsAddlChrgIntfcList
     * @param ssmResult S21SsmEZDResult
     * @return List<DS_ADDL_CHRG_INTFCTMsg>
     */
    public static List<DS_ADDL_CHRG_INTFCTMsg> getDsAddlChrgIntfcList(S21SsmEZDResult ssmResult) {

        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<DS_ADDL_CHRG_INTFCTMsg> tMsgList = new ArrayList<DS_ADDL_CHRG_INTFCTMsg>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            DS_ADDL_CHRG_INTFCTMsg tMsg = new DS_ADDL_CHRG_INTFCTMsg();

            setValue(tMsg.glblCmpyCd, (String) map.get("GLBL_CMPY_CD"));
            setValue(tMsg.dsAddlChrgIntfcPk, (BigDecimal) map.get("DS_ADDL_CHRG_INTFC_PK"));
            setValue(tMsg.dsContrIntfcBatNum, (String) map.get("DS_CONTR_INTFC_BAT_NUM"));
            setValue(tMsg.dsContrSrcRefNum, (String) map.get("DS_CONTR_SRC_REF_NUM"));
            setValue(tMsg.contrIntfcSrcTpCd, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
            setValue(tMsg.addChrgIntfcStsCd, (String) map.get("ADD_CHRG_INTFC_STS_CD"));
            setValue(tMsg.intfcErrMsgTxt, (String) map.get("INTFC_ERR_MSG_TXT"));
            setValue(tMsg.serNum, (String) map.get("SER_NUM"));
            setValue(tMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            setValue(tMsg.mdseCd, (String) map.get("MDSE_CD"));
            setValue(tMsg.chrgLvlTpCd, (String) map.get("CHRG_LVL_TP_CD"));
            setValue(tMsg.addlChrgTpCd, (String) map.get("ADDL_CHRG_TP_CD"));
            setValue(tMsg.effFromDt, (String) map.get("EFF_FROM_DT"));
            setValue(tMsg.effThruDt, (String) map.get("EFF_THRU_DT"));
            setValue(tMsg.bllgCycleCd, (String) map.get("BLLG_CYCLE_CD"));
            setValue(tMsg.addlChrgFlatDealPrcAmt, (BigDecimal) map.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            setValue(tMsg.addlChrgAplcPct, (BigDecimal) map.get("ADDL_CHRG_APLC_PCT"));
            setValue(tMsg.addlChrgInvTpCd, (String) map.get("ADDL_CHRG_INV_TP_CD"));
            setValue(tMsg.printDtlFlg, (String) map.get("PRINT_DTL_FLG"));
            setValue(tMsg.cpoSvcDtlPk, (BigDecimal) map.get("CPO_SVC_DTL_PK"));

            tMsgList.add(tMsg);
        }
        return tMsgList;
    }

    /**
     * getPrcAllocIntfcList
     * @param ssmResult S21SsmEZDResult
     * @return List<PRC_ALLOC_INTFCTMsg>
     */
    public static List<PRC_ALLOC_INTFCTMsg> getPrcAllocIntfcList(S21SsmEZDResult ssmResult) {

        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<PRC_ALLOC_INTFCTMsg> tMsgList = new ArrayList<PRC_ALLOC_INTFCTMsg>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            PRC_ALLOC_INTFCTMsg tMsg = new PRC_ALLOC_INTFCTMsg();

            setValue(tMsg.glblCmpyCd, (String) map.get("GLBL_CMPY_CD"));
            setValue(tMsg.prcAllocIntfcPk, (BigDecimal) map.get("PRC_ALLOC_INTFC_PK"));
            setValue(tMsg.dsContrIntfcBatNum, (String) map.get("DS_CONTR_INTFC_BAT_NUM"));
            setValue(tMsg.dsContrSrcRefNum, (String) map.get("DS_CONTR_SRC_REF_NUM"));
            setValue(tMsg.contrIntfcSrcTpCd, (String) map.get("CONTR_INTFC_SRC_TP_CD"));
            setValue(tMsg.allocIntfcStsCd, (String) map.get("ALLOC_INTFC_STS_CD"));
            setValue(tMsg.intfcErrMsgTxt, (String) map.get("INTFC_ERR_MSG_TXT"));
            setValue(tMsg.prcAllocIntfcTpCd, (String) map.get("PRC_ALLOC_INTFC_TP_CD"));
            setValue(tMsg.serNum, (String) map.get("SER_NUM"));
            setValue(tMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            setValue(tMsg.mdseCd, (String) map.get("MDSE_CD"));
            setValue(tMsg.tocCd, (String) map.get("TOC_CD"));
            setValue(tMsg.tocNm, (String) map.get("TOC_NM"));
            setValue(tMsg.prcAllocRate, (BigDecimal) map.get("PRC_ALLOC_RATE"));
            setValue(tMsg.cpoSvcDtlPk, (BigDecimal) map.get("CPO_SVC_DTL_PK"));

            tMsgList.add(tMsg);
        }
        return tMsgList;
    }

    /**
     * getCtacPsnPk
     * @param glblCmpyCd String
     * @param fullName String
     * @return BigDecimal
     */
    public static BigDecimal getCtacPsnPk(String glblCmpyCd, String fullName) {
        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getCtacPsnPk(glblCmpyCd, fullName);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        if (map == null) {
            return null;
        }
        return (BigDecimal) map.get("CTAC_PSN_PK");
    }

    /**
     * getDsContrIntfcBatNumSet
     * @param sMsg NSAL0810SMsg
     * @return selected DsContrSrcRefNum set
     */
    public static Set<String> getDsContrIntfcBatNumSet(NSAL0810SMsg sMsg) {
        Set<String> selectedDsContrIntfcBatNumSet = new HashSet<String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_A) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                // MOD start 2016/03/29 CSA Defect#5541
                if (!selectedDsContrIntfcBatNumSet.contains(sMsg.A.no(i).dsContrIntfcBatNum_A.getValue()) || !selectedDsContrIntfcBatNumSet.contains(sMsg.A.no(i).dsContrSrcRefNum_A.getValue())) {
                    selectedDsContrIntfcBatNumSet.add(sMsg.A.no(i).dsContrIntfcBatNum_A.getValue());
                    selectedDsContrIntfcBatNumSet.add(sMsg.A.no(i).dsContrSrcRefNum_A.getValue());
                }
                // MOD end 2016/03/29 CSA Defect#5541
            }
        }
        return selectedDsContrIntfcBatNumSet;
    }

    /**
     * isLeaseOrder
     * @param glblCmpyCd String
     * @param dsContrIntfcPk BigDecimal
     * @return boolean
     */
    public static boolean isLeaseOrder(String glblCmpyCd, BigDecimal dsContrIntfcPk) {

        S21SsmEZDResult ssmResult = NSAL0810Query.getInstance().getCpoOrdTpCd(glblCmpyCd, dsContrIntfcPk);
        if (!ssmResult.isCodeNormal()) {
            return false;
        }
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();
        if (map != null) {
            // 2016/01/28 QC#3862 Y.Tsuchimoto Mod Start
            //if (CPO_ORD_TP.LEASE.equals((String) map.get("CPO_ORD_TP_CD"))) {
            //    return true;
            //}
            if (DS_ORD_CATG.LEASE_CSA.equals((String) map.get("DS_ORD_CATG_CD"))) {
                return true;
            }
            // 2016/01/28 QC#3862 Y.Tsuchimoto Mod End
        }
        return false;
    }

    /**
     * isAllowDataCrctFlg
     * @param glblCmpyCd String
     * @param contrIntfcSrcTpCd String
     * @return true:ALLOW_DATA_CRCT_FLG = Y, false:ALLOW_DATA_CRCT_FLG = N
     */
    public static boolean isAllowDataCrctFlg(String glblCmpyCd, String contrIntfcSrcTpCd) {
        // mod start 2017/09/15 QC#18799
//        DS_CONTR_INTFC_DEF_RULETMsg prmTMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
//        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(prmTMsg.contrIntfcSrcTpCd, contrIntfcSrcTpCd);
//        DS_CONTR_INTFC_DEF_RULETMsg tMsg = (DS_CONTR_INTFC_DEF_RULETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
//        if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.allwDataCrctFlg.getValue())) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
        // mod end 2017/09/15 QC#18799
    }

    private static String getBllgMtrLbNm(String glblCmpyCd, String bllgMtrLbCd) {
        MTR_LBTMsg mtrLbTMsg = (MTR_LBTMsg) ZYPCodeDataUtil.findByCode(MTR_LB.class, glblCmpyCd, bllgMtrLbCd);
        if (mtrLbTMsg != null) {
            String mtrLbDescTxt = mtrLbTMsg.mtrLbDescTxt.getValue();
            if (hasValue(mtrLbDescTxt) && mtrLbDescTxt.length() > BLLG_MTR_LB_NM_MAX_LENGTH) {
                return mtrLbDescTxt.substring(0, BLLG_MTR_LB_NM_MAX_LENGTH);
            } else {
                return mtrLbDescTxt;
            }
        }
        return null;
    }

// START 2016/07/14 [QC#11918, ADD]
    /**
     * countupSMsgProcStatus
     * 
     * @param cMsg NSAL0810CMsg
     * @param sMsg NSAL0810SMsg
     */
     public static void countupSMsgProcStatus(NSAL0810CMsg cMsg, NSAL0810SMsg sMsg) {
         // do basic check and load to screen(for all csv data)
         int cntComplete = 0;
         int cntErrorOrReprocess = 0;
         int cntInProcess = 0;
         int cntError = 0;

         for (int i = 0 ; i < sMsg.A.getValidCount() ; i++) {
             NSAL0810_ASMsg asMsg = sMsg.A.no(i);

             if (hasValue(asMsg.dsContrProcStsCd_AS)) {
                 if (DS_CONTR_PROC_STS.COMPLEATED.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                     cntComplete++;
                 } else if (DS_CONTR_PROC_STS.ERROR.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                     cntErrorOrReprocess++;
                     cntError++;
                 } else if (DS_CONTR_PROC_STS.REPROCESS.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                     cntErrorOrReprocess++;
                 } else if (DS_CONTR_PROC_STS.IN_PROCESS.equals(asMsg.dsContrProcStsCd_AS.getValue())) {
                     cntInProcess++;
                 }
             }
         }

         setValue(cMsg.xxSrchCnt_SP, BigDecimal.valueOf(cntComplete));
         setValue(cMsg.xxSrchCnt_SR, BigDecimal.valueOf(cntErrorOrReprocess));
         setValue(cMsg.xxSrchCnt_SU, BigDecimal.valueOf(cntInProcess));
         setValue(cMsg.xxSrchCnt_SI, BigDecimal.valueOf(cntError));
     }

     // END   2016/07/14 [QC#11918, ADD]
     // mod start 2016/08/31 CSA QC#12566
     public static List<DS_CONTR_INTFCTMsg> getSortListByBatNum(List<DS_CONTR_INTFCTMsg> tMsgList) {

         List<DS_CONTR_INTFCTMsg> sortList = new ArrayList<DS_CONTR_INTFCTMsg>();
         for (int i = 0; i < tMsgList.size(); i++) {
             sortList.add(tMsgList.get(i));
         }

         Collections.sort(sortList, new Comparator<DS_CONTR_INTFCTMsg>() {

             public int compare(DS_CONTR_INTFCTMsg record0, DS_CONTR_INTFCTMsg record1) {

                 String dsContrIntfcBatNum0 = record0.dsContrIntfcBatNum.getValue();
                 String dsContrIntfcBatNum1 = record1.dsContrIntfcBatNum.getValue();
                 String dsContrIntfcActCd0 = record0.dsContrIntfcActCd.getValue();
                 String dsContrIntfcActCd1 = record1.dsContrIntfcActCd.getValue();
                 String key0 = dsContrIntfcBatNum0 + "," + dsContrIntfcActCd0;
                 String key1 = dsContrIntfcBatNum1 + "," + dsContrIntfcActCd1;

                 return (key0).compareTo(key1);
             }
         });

         return sortList;
     }
     // mod end 2016/08/31 CSA QC#12566

     // START 2017/05/26 Y.Osawa [QC#18560, ADD]
     /**
      * deletePulldownList
      * @param cdArray EZDCStringItemArray Code Array
      * @param txtArray EZDCStringItemArray Text Array
      * @param delCd delete Code
      */
     public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
         int index = -1;
         for (int i = 0; i < cdArray.length(); i++) {
             if (delCd.equals(cdArray.no(i).getValue())) {
                 index = i;
                 break;
             }
         }

         if (index >= 0) {
             int i = index;
             for (; i < cdArray.length() - 1; i++) {
                 ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                 ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
             }
             cdArray.no(i).clear();
             txtArray.no(i).clear();
         }
     }
     // END   2017/05/26 Y.Osawa [QC#18560, ADD]
     // START 2018/02/22 M.Naito [QC23179, DEL]
     // Add Start 2017/11/21 QC#21724
//     public static String addCloDt(String contrCloDt, int days) {
//         String rtnDt = contrCloDt;
//         if (!hasValue(rtnDt)) {
//             return rtnDt;
//         }
//         return ZYPDateUtil.addDays(rtnDt, days);
//     }
     // Add End 2017/11/21 QC#21724
     // END 2018/02/22 M.Naito [QC23179, DEL]
}
