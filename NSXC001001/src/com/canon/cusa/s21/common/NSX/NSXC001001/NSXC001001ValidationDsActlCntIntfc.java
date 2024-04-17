/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.MDSETMsg;
import business.db.MTR_LBTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSXC001001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACTL_CNT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Validation DS_ACTL_CNT_INTFC
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A(Template only)
 * 2015/01/07   Hitachi         Y.Takeno        Update          N/A
 * 2016/01/21   Hitachi         Y.Tsuchimoto    Update          QC#3402
 * 2016/01/21   Hitachi         Y.Tsuchimoto    Update          QC#3403
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/28   Hitachi         Y.Tsuchimoto    Update          QC#5822
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11853
 *</pre>
 */
public class NSXC001001ValidationDsActlCntIntfc {

    /** This data has been updated by another user. */
    private static final String ZZZM9004E = "ZZZM9004E";

    /** [Source Type] field is mandatory. */
    private static final String NSXM0012E = "NSXM0012E";

    /** [Source Ref#] field is mandatory. */
    private static final String NSXM0013E = "NSXM0013E";

    /** [Serial#] field is mandatory. */
    private static final String NSXM0016E = "NSXM0016E";

    /** [IB ID] doesn't exist in the table [Machine Master]. */
    private static final String NSXM0042E = "NSXM0042E";

    /** [Mdse Code] doesn't exist in the table [Merchandise Master]. */
    private static final String NSXM0047E = "NSXM0047E";

    /** [Contract#] should be match each other in the same [Source Ref#]. */
    private static final String NSXM0055E = "NSXM0055E";

    /** [Mdse Code] should be match each other in the same [IB ID]. */
    private static final String NSXM0060E = "NSXM0060E";

    /** [Serial#] should be match each other in the same [IB ID]. */
    private static final String NSXM0061E = "NSXM0061E";

    /** [Actual Counter Code] field is mandatory. */
    private static final String NSXM0075E = "NSXM0075E";

    /** [Multiplier] field is mandatory. */
    private static final String NSXM0076E = "NSXM0076E";

    /** [Billing Counter Code] field is mandatory. */
    private static final String NSXM0077E = "NSXM0077E";

    /** [Actual Counter Code] doesn't exist in the table [Meter Lable]. */
    private static final String NSXM0078E = "NSXM0078E";

    /** [Billing Counter Code] doesn't exist in the table [Meter Lable]. */
    private static final String NSXM0079E = "NSXM0079E";

    /** [Interface record related to IB ID] doesn't exist in the table [DS Contract Interface]. */
    private static final String NSXM0080E = "NSXM0080E";

    /** Failed to update "DS_ACTL_CNT_INTFC". */
    private static final String NSXM0081E = "NSXM0081E";

    /** The multiplier must be between 0.1 and 4. */
    private static final String NSXM0082E = "NSXM0082E";

    /** The multiplier must be incremented by 0.1. */
    private static final String NSXM0083E = "NSXM0083E";

    /** Minimum Meter Multiplier */
    private static final String MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";

    /** Maximum Meter Multiplier */
    private static final String MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";

    /** Meter Multiplier Factor */
    private static final String MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    /** SPCL_FLT_MDSE_CD */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ValidationDsActlCntIntfc.class);

    /**
     * <pre>
     * validationDsActlCntIntfc
     * </pre>
     * @param prmPmsg NSXC001001PMsg
     * @param tMsgList List<DS_ACTL_CNT_INTFCTMsg>
     */
    public static void validationDsActlCntIntfc(NSXC001001PMsg prmPmsg, List<DS_ACTL_CNT_INTFCTMsg> tMsgList) {

        int errorCount = 0;
        int errorTotalCount = 0;


        prmPmsg.xxMsgIdList.setValidCount(prmPmsg.xxMsgIdList.length());

        for (DS_ACTL_CNT_INTFCTMsg tMsg : tMsgList) {

            // Input check
            errorCount = checkInput(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }

            // Master check
            errorCount = masterCheck(prmPmsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }
        }

        // Contract Header validation
        errorCount = headerCheck(prmPmsg, tMsgList, errorTotalCount + errorCount);
        errorTotalCount += errorCount;
        if (errorCount == 0) {

            // Contract Detail validation
            errorCount = detailCheck(prmPmsg, tMsgList, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
        }

        prmPmsg.xxMsgIdList.setValidCount(errorTotalCount);
    }

    private static int checkInput(NSXC001001PMsg prmPmsg, DS_ACTL_CNT_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        if (!hasValue(tMsg.dsContrSrcRefNum)) {
            setErrorMessage(prmPmsg, NSXM0013E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0013E);
            errorCount++;
        }
        if (!hasValue(tMsg.contrIntfcSrcTpCd)) {
            setErrorMessage(prmPmsg, NSXM0012E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0012E);
            errorCount++;
        }
        // START 2016/08/09 Y.Takeno [QC#11853, DEL]
//        if (isInstlBaseCtrlFlg(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue()) && !hasValue(tMsg.serNum)) {
//            setErrorMessage(prmPmsg, NSXM0016E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0016E);
//            errorCount++;
//        }
        // END 2016/08/09 Y.Takeno [QC#11853, DEL]

// 2016/03/11 START [QC#5298, MOD]
//        if (!hasValue(tMsg.svcMachMstrPk)) {
//            setErrorMessage(prmPmsg, NSXM0017E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0017E);
//            errorCount++;
//        }
//        if (!hasValue(tMsg.mdseCd)) {
//            setErrorMessage(prmPmsg, NSXM0028E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0028E);
//            errorCount++;
//        }
// 2016/03/11 END [QC#5298, MOD]
        if (!hasValue(tMsg.physMtrLbCd)) {
            setErrorMessage(prmPmsg, NSXM0075E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0075E);
            errorCount++;
        }
        if (!hasValue(tMsg.contrMtrMultRate)) {
            setErrorMessage(prmPmsg, NSXM0076E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0076E);
            errorCount++;
        }
        if (!hasValue(tMsg.bllgMtrLbCd)) {
            setErrorMessage(prmPmsg, NSXM0077E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0077E);
            errorCount++;
        }

        if (!checkMultiplier(prmPmsg, tMsg, errorTotalCount + errorCount)) {
            errorCount++;
        }

        return errorCount;
    }

    private static boolean checkMultiplier(NSXC001001PMsg prmPmsg, DS_ACTL_CNT_INTFCTMsg tMsg, int errorTotalCount) {

        String glblCmpyCd = tMsg.glblCmpyCd.getValue();
        BigDecimal spcRate = BigDecimal.ONE.negate();
        BigDecimal minRate = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_MIN_NUM, glblCmpyCd);
        BigDecimal maxRate = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_MAX_NUM, glblCmpyCd);
        BigDecimal fctNum = ZYPCodeDataUtil.getNumConstValue(MTR_MULT_RATE_FCT_NUM, glblCmpyCd);

        if (hasValue(tMsg.contrMtrMultRate)) {
            BigDecimal multRate = tMsg.contrMtrMultRate.getValue();
            if (multRate.compareTo(spcRate) != 0) {
                if (multRate.compareTo(minRate) < 0 || multRate.compareTo(maxRate) > 0) {
                    setErrorMessage(prmPmsg, NSXM0082E, errorTotalCount);
                    setErrorStatusAndMessage(tMsg, NSXM0082E);
                    return false;
                }
                if (multRate.remainder(fctNum).compareTo(BigDecimal.ZERO) != 0) {
                    setErrorMessage(prmPmsg, NSXM0083E, errorTotalCount);
                    setErrorStatusAndMessage(tMsg, NSXM0083E);
                    return false;
                }
            }
        }
        // 2016/01/21 QC#3402 Y.Tsuchimoto Mod Start
        //return false;
        return true;
        // 2016/01/21 QC#3402 Y.Tsuchimoto Mod End
    }

    private static int masterCheck(NSXC001001PMsg prmPmsg, DS_ACTL_CNT_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

        // 2016/03/11 START [QC#5298, MOD]
        if (hasValue(tMsg.svcMachMstrPk)) {
            if (!checkIbId(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue())) {
                setErrorMessage(prmPmsg, NSXM0042E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0042E);
                errorCount++;
            }
        }
        if (hasValue(tMsg.mdseCd)) {
            if (!checkMdse(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue())) {
                setErrorMessage(prmPmsg, NSXM0047E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0047E);
                errorCount++;
            }
        }
        // 2016/03/11 END [QC#5298, MOD]
        if (!checkCounter(tMsg.glblCmpyCd.getValue(), tMsg.physMtrLbCd.getValue(), MTR_LB_TP.REGULAR_METER)) {
            setErrorMessage(prmPmsg, NSXM0078E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0078E);
            errorCount++;
        }
        if (!checkCounter(tMsg.glblCmpyCd.getValue(), tMsg.bllgMtrLbCd.getValue(), MTR_LB_TP.BILLING_METER)) {
            setErrorMessage(prmPmsg, NSXM0079E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0079E);
            errorCount++;
        }
        if (!checkDsContrIntf(tMsg)) {
            setErrorMessage(prmPmsg, NSXM0080E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0080E);
            errorCount++;
        }

        return errorCount;
    }

    private static boolean checkIbId(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk", svcMachMstrPk);
        SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private static boolean checkMdse(String glblCmpyCd, String mdseCd) {
        // 2016/01/21 QC#3403 Y.Tsuchimoto Mod Start
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdseCd", mdseCd);
        MDSETMsg tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
        // 2016/01/21 QC#3403 Y.Tsuchimoto Mod End
        if (tMsg != null) {
            return true;
        }
        // 2016/03/11 START [QC#5298, MOD]
        String fltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, glblCmpyCd);
        if (mdseCd.equals(fltMdseCd)) {
            return true;
        }
        // 2016/03/11 END [QC#5298, MOD]
        return false;
    }

    private static boolean checkCounter(String glblCmpyCd, String mtrLbCd, String mtrLbTpCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mtrLbCd", mtrLbCd);
        MTR_LBTMsg tMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tMsg != null && mtrLbTpCd.equals(tMsg.mtrLbTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean checkDsContrIntf(DS_ACTL_CNT_INTFCTMsg actlMsg) {
        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
        // 2016/03/11 START [QC#5298, MOD]
        if (hasValue(actlMsg.svcMachMstrPk)) {
            inMsg.setSQLID("003");
            inMsg.setConditionValue("svcMachMstrPk01", actlMsg.svcMachMstrPk.getValue());
        } else {
            inMsg.setSQLID("004");
        }
        // 2016/03/11 END [QC#5298, MOD]
        inMsg.setConditionValue("glblCmpyCd01", actlMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("contrIntfcSrcTpCd01", actlMsg.contrIntfcSrcTpCd.getValue());
        inMsg.setConditionValue("dsContrSrcRefNum01", actlMsg.dsContrSrcRefNum.getValue());
        inMsg.setConditionValue("bllgMtrLbCd01", actlMsg.bllgMtrLbCd.getValue());
        if ((int) EZDTBLAccessor.count(inMsg) > 0) {
            return true;
        }
        return false;
    }

    private static int headerCheck(NSXC001001PMsg prmPmsg, List<DS_ACTL_CNT_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, List<DS_ACTL_CNT_INTFCTMsg>> groupDataMap = new HashMap<String, List<DS_ACTL_CNT_INTFCTMsg>>();
        for (DS_ACTL_CNT_INTFCTMsg tMsg : tMsgList) {

            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.glblCmpyCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrIntfcBatNum.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            String groupKey = builder.toString();

            if (!groupDataMap.containsKey(groupKey)) {
                groupDataMap.put(groupKey, new ArrayList<DS_ACTL_CNT_INTFCTMsg>());
            }
            List<DS_ACTL_CNT_INTFCTMsg> tMsgGroupList = groupDataMap.get(groupKey);
            tMsgGroupList.add(tMsg);
        }

        for (List<DS_ACTL_CNT_INTFCTMsg> tMsgGroupList : groupDataMap.values()) {

            String dsContrNumBase = null;
            for (DS_ACTL_CNT_INTFCTMsg tMsg : tMsgGroupList) {

                List<Map<String, Object>> result = getDsContrNumForHeaderConsistencyCheck(tMsg);

                if (result != null && result.size() == 1) {
                    if (dsContrNumBase == null) {
                        dsContrNumBase = (String) result.get(0).get("DS_CONTR_NUM");

                    } else {
                        String dsContrNum = (String) result.get(0).get("DS_CONTR_NUM");
                        if (!dsContrNumBase.equals(dsContrNum)) {
                            setErrorMessage(prmPmsg, NSXM0055E, errorTotalCount + errorCount);
                            setErrorStatusAndMessage(tMsg, NSXM0055E);
                            errorCount++;
                        }
                    }

                } else {
                    setErrorMessage(prmPmsg, NSXM0055E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0055E);
                    errorCount++;
                }
            }
        }

        return errorCount;
    }

    private static List<Map<String, Object>> getDsContrNumForHeaderConsistencyCheck(DS_ACTL_CNT_INTFCTMsg tMsg) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        prmMap.put("dsContrIntfcBatNum", tMsg.dsContrIntfcBatNum.getValue());
        prmMap.put("dsContrSrcRefNum", tMsg.dsContrSrcRefNum.getValue());
        prmMap.put("contrIntfcSrcTpCd", tMsg.contrIntfcSrcTpCd.getValue());
        // 2016/03/28 START [QC#5822, ADD]
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tMsg.contrIntfcSrcTpCd.getValue())) {
            prmMap.put("cpoSvcDtlPk", tMsg.cpoSvcDtlPk.getValue());
        }
        // 2016/03/28 END   [QC#5822, ADD]

        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getDsContrNumForHeaderConsistencyCheck", prmMap);
    }

    private static int detailCheck(NSXC001001PMsg prmPmsg, List<DS_ACTL_CNT_INTFCTMsg> tMsgList, int errorTotalCount) {

        int errorCount = 0;

        Map<String, DS_ACTL_CNT_INTFCTMsg> groupDataMap = new HashMap<String, DS_ACTL_CNT_INTFCTMsg>();
        for (DS_ACTL_CNT_INTFCTMsg tMsg : tMsgList) {
            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            String groupKey = builder.toString();

            if (groupDataMap.containsKey(groupKey)) {
                DS_ACTL_CNT_INTFCTMsg tMsgMap = groupDataMap.get(groupKey);
                if (!tMsgMap.mdseCd.getValue().equals(tMsg.mdseCd.getValue())) {
                    setErrorMessage(prmPmsg, NSXM0060E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0060E);
                    errorCount++;
                }

                if (!tMsgMap.serNum.getValue().equals(tMsg.serNum.getValue())) {
                    setErrorMessage(prmPmsg, NSXM0061E, errorTotalCount + errorCount);
                    setErrorStatusAndMessage(tMsg, NSXM0061E);
                    errorCount++;
                }

            } else {
                groupDataMap.put(groupKey, tMsg);
            }
        }

        return errorCount;
    }

    private static void setErrorMessage(NSXC001001PMsg prmPmsg, String errorMsgId, int errCnt) {
        if (prmPmsg == null || errorMsgId == null) {
            return;
        }
        if (errCnt >= prmPmsg.xxMsgIdList.length()) {
            return;
        }
        setValue(prmPmsg.xxMsgIdList.no(errCnt).xxMsgId, errorMsgId);
    }

    private static void setErrorStatusAndMessage(DS_ACTL_CNT_INTFCTMsg tMsg, String errorMsgId) {
        if (tMsg == null || errorMsgId == null) {
            return;
        }
        setValue(tMsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
    }

    /**
     * <pre>
     * updateValidationResult
     * </pre>
     * @param prmPmsg NSXC001001PMsg
     * @param rstMsgList List<DS_CONTR_INTFCTMsg>
     */
    public static void updateValidationResult(NSXC001001PMsg prmPmsg, List<DS_ACTL_CNT_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_ACTL_CNT_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.dsActlCntIntfcPk)) {
                // for update
                DS_ACTL_CNT_INTFCTMsg tMsg = getContrIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsActlCntIntfcPk.getValue());
                if (tMsg == null) {
                    setErrorMessage(prmPmsg, ZZZM9004E, errCnt);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    setErrorMessage(prmPmsg, ZZZM9004E, errCnt);
                    return;
                }
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                    setValue(tMsg.actlCntIntfcStsCd, ACTL_CNT_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.actlCntIntfcStsCd, ACTL_CNT_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(prmPmsg, NSXM0081E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0081E);

                    return;
                }
            }
        }
    }

    private static DS_ACTL_CNT_INTFCTMsg getContrIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_ACTL_CNT_INTFCTMsg prmTMsg = new DS_ACTL_CNT_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsActlCntIntfcPk, dsContrIntfcPk);
        return (DS_ACTL_CNT_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
}
