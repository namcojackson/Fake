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
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.MDSETMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADD_CHRG_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Validation DS_ADDL_CHRG_INTFC
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A(Template only)
 * 2016/01/07   Hitachi         Y.Takeno        Update          N/A
 * 2016/02/29   Hitachi         Y.Takeno        Update          QC#3314
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/16   Hitachi         T.Iwamoto       Update          QC#5544
 * 2016/03/24   Hitachi         Y.Tsuchimoto    Update          QC#5662
 * 2016/03/28   Hitachi         Y.Tsuchimoto    Update          QC#5822
 * 2016/04/08   Hitachi         Y.Tsuchimoto    Update          QC#6701
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11853
 *</pre>
 */
public class NSXC001001ValidationDsAddlChrgIntfc {

    /** This data has been updated by another user. */
    private static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "DS_ADDL_CHRG_INTFC". */
    private static final String NSXM0097E = "NSXM0097E";

    /** [Source Type] field is mandatory. */
    private static final String NSXM0012E = "NSXM0012E";

    /** [Source Ref#] field is mandatory. */
    private static final String NSXM0013E = "NSXM0013E";

    /** [Serial#] field is mandatory. */
    private static final String NSXM0016E = "NSXM0016E";

    /** [Start Date] field is mandatory. */
    private static final String NSXM0029E = "NSXM0029E";

    /** [End Date] field is mandatory. */
    private static final String NSXM0030E = "NSXM0030E";

    /** [Frequency] field is mandatory. */
    private static final String NSXM0031E = "NSXM0031E";

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

    /** [Interface record related to IB ID] doesn't exist in the table [DS Contract Interface]. */
    private static final String NSXM0080E = "NSXM0080E";

    /** [Charge Level] field is mandatory. */
    private static final String NSXM0084E = "NSXM0084E";

    /** [Charge Type] field is mandatory. */
    private static final String NSXM0085E = "NSXM0085E";

    /** [Flat Rate] or [Applicable%] must be entered. */
    private static final String NSXM0086E = "NSXM0086E";

    /** [Invoice Type] field is mandatory. */
    private static final String NSXM0087E = "NSXM0087E";

    /** SPCL_FLT_MDSE_CD */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ValidationDsAddlChrgIntfc.class);

    /**
     * <pre>
     * validationDsActlCntIntfc
     * </pre>
     * @param tMsgList List<DS_ADDL_CHRG_INTFCTMsg>
     * @return rtnBean ValidationReturnBean
     */
    public static ValidationReturnBean validationDsAddlChrgIntfc(List<DS_ADDL_CHRG_INTFCTMsg> tMsgList) {
        ValidationReturnBean resultPMsg = new ValidationReturnBean();

        int errorCount = 0;
        int errorTotalCount = 0;

        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());

        for (DS_ADDL_CHRG_INTFCTMsg tMsg : tMsgList) {

            // Input check
            errorCount = checkInput(resultPMsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }

            // Master check
            errorCount = masterCheck(resultPMsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }

            // Consistency check
            errorCount = consistencyCheck(resultPMsg, tMsg, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount > 0) {
                continue;
            }
        }

        // Contract Header validation
        errorCount = headerCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
        errorTotalCount += errorCount;
        if (errorCount == 0) {

            // Contract Detail validation
            errorCount = detailCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
        }

        resultPMsg.xxMsgIdList.setValidCount(errorTotalCount);

        return resultPMsg;
    }

    private static int checkInput(ValidationReturnBean prmPmsg, DS_ADDL_CHRG_INTFCTMsg tMsg, int errorTotalCount) {
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
// 2015/02/29 START [QC#3314, MOD]
        // START 2016/08/09 Y.Takeno [QC#11853, DEL]
//        if (isInstlBaseCtrlFlg(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue()) && !hasValue(tMsg.serNum)) {
//            setErrorMessage(prmPmsg, NSXM0016E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0016E);
//            errorCount++;
//        }
        // END 2016/08/09 Y.Takeno [QC#11853, DEL]
//        if (!hasValue(tMsg.svcMachMstrPk)) {
//            setErrorMessage(prmPmsg, NSXM0016E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0016E);
//            errorCount++;
//        }
// 2015/02/29 END   [QC#3314, MOD]
        if (!hasValue(tMsg.chrgLvlTpCd)) {
            setErrorMessage(prmPmsg, NSXM0084E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0084E);
            errorCount++;
        }
        if (!hasValue(tMsg.addlChrgTpCd)) {
            setErrorMessage(prmPmsg, NSXM0085E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0085E);
            errorCount++;
        }
        if (!hasValue(tMsg.effFromDt)) {
            setErrorMessage(prmPmsg, NSXM0029E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0029E);
            errorCount++;
        }
        if (!hasValue(tMsg.effThruDt)) {
            setErrorMessage(prmPmsg, NSXM0030E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0030E);
            errorCount++;
        }
        if (!hasValue(tMsg.bllgCycleCd)) {
            setErrorMessage(prmPmsg, NSXM0031E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0031E);
            errorCount++;
        }
        // 2016/03/15 START [QC#5544, MOD]
        if (!hasValue(tMsg.addlChrgFlatDealPrcAmt) && !hasValue(tMsg.addlChrgAplcPct)) {
            setErrorMessage(prmPmsg, NSXM0086E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0086E);
            errorCount++;
        }
        // 2016/03/15 END [QC#5544, MOD]
        if (!hasValue(tMsg.addlChrgInvTpCd)) {
            setErrorMessage(prmPmsg, NSXM0087E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0087E);
            errorCount++;
        }

        return errorCount;
    }

    private static int masterCheck(ValidationReturnBean prmPmsg, DS_ADDL_CHRG_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

// 2015/02/29 START [QC#3314, MOD]
//        if (!checkIbId(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue())) {
// 2015/02/29 END   [QC#3314, MOD]
        if (hasValue(tMsg.svcMachMstrPk.getValue()) && !checkIbId(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue())) {
            setErrorMessage(prmPmsg, NSXM0042E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0042E);
            errorCount++;
        }
// 2015/02/29 START [QC#3314, MOD]
//        if (!checkMdse(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue())) {
// 2015/02/29 END   [QC#3314, MOD]
        if (hasValue(tMsg.mdseCd.getValue()) && !checkMdse(tMsg.glblCmpyCd.getValue(), tMsg.mdseCd.getValue())) {
            setErrorMessage(prmPmsg, NSXM0047E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0047E);
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
// 2015/02/29 START [QC#3314, MOD]
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.mdseCd.setValue(mdseCd);
        MDSETMsg tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
// 2015/02/29 END   [QC#3314, MOD]

        // 2016/03/11 END [QC#5298, MOD]
        if (tMsg != null) {
            return true;
        }
        // 2016/03/11 START [QC#5298, MOD]
        String fltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, glblCmpyCd);
        if (mdseCd.equals(fltMdseCd)) {
            return true;
        }
        return false;
    }

    private static boolean checkDsContrIntf(DS_ADDL_CHRG_INTFCTMsg tMsg) {
        List<Map<String, Object>> resultList = findDsContrIntfc(tMsg);
        if (resultList != null && resultList.size() > 0) {
            return true;
        }
        return false;
    }

    private static List<Map<String, Object>> findDsContrIntfc(DS_ADDL_CHRG_INTFCTMsg tMsg) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", tMsg.glblCmpyCd.getValue());
        prmMap.put("dsContrIntfcBatNum", tMsg.dsContrIntfcBatNum.getValue());
        prmMap.put("contrIntfcSrcTpCd", tMsg.contrIntfcSrcTpCd.getValue());
        prmMap.put("dsContrSrcRefNum", tMsg.dsContrSrcRefNum.getValue());

        // START 2016/04/08 [QC#6701, ADD]
        if (CONTR_INTFC_SRC_TP.ORDER.equals(tMsg.contrIntfcSrcTpCd.getValue())) {
            prmMap.put("cpoSvcDtlPk", tMsg.cpoSvcDtlPk.getValue());
        }
        // END   2016/04/08 [QC#6701, ADD]

        return (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("findDsContrIntfc", prmMap);
    }

    private static int consistencyCheck(ValidationReturnBean prmPmsg, DS_ADDL_CHRG_INTFCTMsg tMsg, int errorTotalCount) {
        int errorCount = 0;

//        if (!(CHRG_LVL_TP.CONTRACT.equals(tMsg.chrgLvlTpCd.getValue())) && !hasValue(tMsg.svcMachMstrPk)) {
//            setErrorMessage(prmPmsg, NSXM0017E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0017E);
//            errorCount++;
//        }
//
//        if (!(CHRG_LVL_TP.CONTRACT.equals(tMsg.chrgLvlTpCd.getValue())) && !hasValue(tMsg.mdseCd)) {
//            setErrorMessage(prmPmsg, NSXM0028E, errorTotalCount + errorCount);
//            setErrorStatusAndMessage(tMsg, NSXM0028E);
//            errorCount++;
//        }

        return errorCount;
    }

    private static int headerCheck(ValidationReturnBean prmPmsg, List<DS_ADDL_CHRG_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, List<DS_ADDL_CHRG_INTFCTMsg>> groupDataMap = new HashMap<String, List<DS_ADDL_CHRG_INTFCTMsg>>();
        for (DS_ADDL_CHRG_INTFCTMsg tMsg : tMsgList) {

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
                groupDataMap.put(groupKey, new ArrayList<DS_ADDL_CHRG_INTFCTMsg>());
            }
            List<DS_ADDL_CHRG_INTFCTMsg> tMsgGroupList = groupDataMap.get(groupKey);
            tMsgGroupList.add(tMsg);
        }

        for (List<DS_ADDL_CHRG_INTFCTMsg> tMsgGroupList : groupDataMap.values()) {

            String dsContrNumBase = null;
            for (DS_ADDL_CHRG_INTFCTMsg tMsg : tMsgGroupList) {

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

    private static List<Map<String, Object>> getDsContrNumForHeaderConsistencyCheck(DS_ADDL_CHRG_INTFCTMsg tMsg) {
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

    private static int detailCheck(ValidationReturnBean prmPmsg, List<DS_ADDL_CHRG_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, DS_ADDL_CHRG_INTFCTMsg> groupDataMap = new HashMap<String, DS_ADDL_CHRG_INTFCTMsg>();
        for (DS_ADDL_CHRG_INTFCTMsg tMsg : tMsgList) {
            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            String groupKey = builder.toString();

            if (groupDataMap.containsKey(groupKey)) {
                DS_ADDL_CHRG_INTFCTMsg tMsgMap = groupDataMap.get(groupKey);
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

    private static void setErrorMessage(ValidationReturnBean prmPmsg, String errorMsgId, int errCnt) {
        if (prmPmsg == null || errorMsgId == null) {
            return;
        }
        if (errCnt >= prmPmsg.xxMsgIdList.length()) {
            return;
        }
        setValue(prmPmsg.xxMsgIdList.no(errCnt).xxMsgId, errorMsgId);
    }

    private static void setErrorStatusAndMessage(DS_ADDL_CHRG_INTFCTMsg tMsg, String errorMsgId) {
        if (tMsg == null || errorMsgId == null) {
            return;
        }
        setValue(tMsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
    }

    // START 2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
    /**
     * <pre>
     * updateValidationResult
     * </pre>
     * @param resultPMsg ValidationReturnBean
     * @param rstMsgList List<DS_ADDL_CHRG_INTFCTMsg>
     */
    public static void updateValidationResult(ValidationReturnBean resultPMsg, List<DS_ADDL_CHRG_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        if (resultPMsg == null) {
            resultPMsg = new ValidationReturnBean();
        }
        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_ADDL_CHRG_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.dsAddlChrgIntfcPk)) {
                // for update
                DS_ADDL_CHRG_INTFCTMsg tMsg = getDsAddlChrgIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsAddlChrgIntfcPk.getValue());
                if (tMsg == null) {
                    setErrorMessage(resultPMsg, ZZZM9004E, errCnt);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(rstMsg.ezUpTime.getValue(), rstMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    setErrorMessage(resultPMsg, ZZZM9004E, errCnt);
                    return;
                }
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                    setValue(tMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(resultPMsg, NSXM0097E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0097E);

                    return;
                }
            }
        }
    }

    private static DS_ADDL_CHRG_INTFCTMsg getDsAddlChrgIntfc(String glblCmpyCd, BigDecimal dsAddlChrgIntfcPk) {
        DS_ADDL_CHRG_INTFCTMsg prmTMsg = new DS_ADDL_CHRG_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAddlChrgIntfcPk, dsAddlChrgIntfcPk);
        return (DS_ADDL_CHRG_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
    // END   2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
}
