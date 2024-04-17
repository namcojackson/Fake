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
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MDSETMsg;
import business.db.MTR_LBTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_COPY_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Validation DS_XS_COPY_INTFC
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A(Template only)
 * 2016/01/20   Hitachi         Y.Takeno        Update          N/A
 * 2016/02/29   Hitachi         Y.Takeno        Update          QC#3314
 * 2016/03/11   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/24   Hitachi         Y.Tsuchimoto    Update          QC#5662
 * 2016/03/28   Hitachi         Y.Tsuchimoto    Update          QC#5822
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11853
 *</pre>
 */
public class NSXC001001ValidationDsXsCopyIntfc {

    /** This data has been updated by another user. */
    private static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "DS_XS_COPY_INTFC". */
    private static final String NSXM0098E = "NSXM0098E";

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

    /** [Billing Counter Code] field is mandatory. */
    private static final String NSXM0077E = "NSXM0077E";

    /** [Billing Counter Code] doesn't exist in the table [Meter Table]. */
    private static final String NSXM0079E = "NSXM0079E";

    /** [Interface record related to IB ID] doesn't exist in the table [DS Contract Interface]. */
    private static final String NSXM0080E = "NSXM0080E";

    /** [Allowance] field is mandatory. */
    private static final String NSXM0088E = "NSXM0088E";

    /** [Price] field is mandatory. */
    private static final String NSXM0089E = "NSXM0089E";

    /**
     * Billing Meter Numbers were duplicated. 
     * Please change the number so it does not duplicate the other one .
     */
    private static final String NSXM0090E = "NSXM0090E";

    /** SPCL_FLT_MDSE_CD */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";


    /**
     * SSM Batch Client
     */
// 2015/02/29 START [QC#3314, MOD]
//    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ValidationDsAddlChrgIntfc.class);
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001ValidationDsXsCopyIntfc.class);
// 2015/02/29 END   [QC#3314, MOD]

    /**
     * <pre>
     * validationDsXsCopyIntfc
     * </pre>
     * @param tMsgList List<DS_XS_COPY_INTFCTMsg>
     * @return rtnBean ValidationReturnBean
     */
    public static ValidationReturnBean validationDsXsCopyIntfc(List<DS_XS_COPY_INTFCTMsg> tMsgList) {
        ValidationReturnBean resultPMsg = new ValidationReturnBean();

        int errorCount = 0;
        int errorTotalCount = 0;

        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());

        for (DS_XS_COPY_INTFCTMsg tMsg : tMsgList) {

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
        }

        if (errorTotalCount == 0) {
            // Contract Header validation
            errorCount = headerCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
            errorTotalCount += errorCount;
            if (errorCount == 0) {

                // Contract Detail validation
                errorCount = detailCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
                errorTotalCount += errorCount;
                if (errorCount == 0) {

                    // Billing Counter validation
                    errorCount = detailBillingCounterCheck(resultPMsg, tMsgList, errorTotalCount + errorCount);
                    errorTotalCount += errorCount;
                }
            }
        }


        resultPMsg.xxMsgIdList.setValidCount(errorTotalCount);

        return resultPMsg;
    }

    private static int checkInput(ValidationReturnBean prmPmsg, DS_XS_COPY_INTFCTMsg tMsg, int errorTotalCount) {
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
        // START 2016/08/09 Y.Takeno [QC#11853, DEL]
// 2016/03/11 START [QC#5298, DEL]
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
// 2016/03/11 END [QC#5298, DEL]
        if (!hasValue(tMsg.bllgMtrLbCd)) {
            setErrorMessage(prmPmsg, NSXM0077E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0077E);
            errorCount++;
        }
        if (!hasValue(tMsg.xsMtrCopyQty)) {
            setErrorMessage(prmPmsg, NSXM0088E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0088E);
            errorCount++;
        }
        if (!hasValue(tMsg.xsMtrAmtRate)) {
            setErrorMessage(prmPmsg, NSXM0089E, errorTotalCount + errorCount);
            setErrorStatusAndMessage(tMsg, NSXM0089E);
            errorCount++;
        }

        return errorCount;
    }

    private static int masterCheck(ValidationReturnBean prmPmsg, DS_XS_COPY_INTFCTMsg tMsg, int errorTotalCount) {
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

        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("mdseCd", mdseCd);
        MDSETMsg tMsg = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);

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

    private static boolean checkDsContrIntf(DS_XS_COPY_INTFCTMsg actlMsg) {
        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
        // 2016/03/28 START [QC#5822, MOD]
        if (hasValue(actlMsg.svcMachMstrPk)) {
            inMsg.setSQLID("003");
            inMsg.setConditionValue("svcMachMstrPk01", actlMsg.svcMachMstrPk.getValue());
        } else {
            inMsg.setSQLID("004");
        }
        inMsg.setConditionValue("glblCmpyCd01", actlMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("contrIntfcSrcTpCd01", actlMsg.contrIntfcSrcTpCd.getValue());
        inMsg.setConditionValue("dsContrSrcRefNum01", actlMsg.dsContrSrcRefNum.getValue());
        inMsg.setConditionValue("bllgMtrLbCd01", actlMsg.bllgMtrLbCd.getValue());
        // 2016/03/28 END [QC#5822, MOD]
        if ((int) EZDTBLAccessor.count(inMsg) > 0) {
            return true;
        }
        return false;
    }

    private static int headerCheck(ValidationReturnBean prmPmsg, List<DS_XS_COPY_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, List<DS_XS_COPY_INTFCTMsg>> groupDataMap = new HashMap<String, List<DS_XS_COPY_INTFCTMsg>>();
        for (DS_XS_COPY_INTFCTMsg tMsg : tMsgList) {

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
                groupDataMap.put(groupKey, new ArrayList<DS_XS_COPY_INTFCTMsg>());
            }
            List<DS_XS_COPY_INTFCTMsg> tMsgGroupList = groupDataMap.get(groupKey);
            tMsgGroupList.add(tMsg);
        }

        for (List<DS_XS_COPY_INTFCTMsg> tMsgGroupList : groupDataMap.values()) {

            String dsContrNumBase = null;
            for (DS_XS_COPY_INTFCTMsg tMsg : tMsgGroupList) {

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

    private static List<Map<String, Object>> getDsContrNumForHeaderConsistencyCheck(DS_XS_COPY_INTFCTMsg tMsg) {
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

    private static int detailCheck(ValidationReturnBean prmPmsg, List<DS_XS_COPY_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        Map<String, DS_XS_COPY_INTFCTMsg> groupDataMap = new HashMap<String, DS_XS_COPY_INTFCTMsg>();
        for (DS_XS_COPY_INTFCTMsg tMsg : tMsgList) {
            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            String groupKey = builder.toString();

            if (groupDataMap.containsKey(groupKey)) {
                DS_XS_COPY_INTFCTMsg tMsgMap = groupDataMap.get(groupKey);
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

    private static int detailBillingCounterCheck(ValidationReturnBean prmPmsg, List<DS_XS_COPY_INTFCTMsg> tMsgList, int errorTotalCount) {
        int errorCount = 0;

        for (DS_XS_COPY_INTFCTMsg tMsg : tMsgList) {
            StringBuilder builder = new StringBuilder();
            builder.append(tMsg.contrIntfcSrcTpCd.getValue());
            builder.append(",");
            builder.append(tMsg.dsContrSrcRefNum.getValue());
            builder.append(",");
            builder.append(tMsg.svcMachMstrPk.getValue());
            builder.append(",");
            builder.append(tMsg.bllgMtrLbCd.getValue());
            // 2016/03/28 START [QC#5822, ADD]
            builder.append(",");
            BigDecimal xsMtrCopyQty = tMsg.xsMtrCopyQty.getValue();
            if (xsMtrCopyQty != null) {
                builder.append(xsMtrCopyQty.toString());
            } else {
                builder.append((String) null);
            }
            // 2016/03/28 END   [QC#5822, ADD]
            String groupKey = builder.toString();

            // 2016/03/28 START [QC#5822, MOD]
            int sameCount = 0;
            for (DS_XS_COPY_INTFCTMsg targetTMsg : tMsgList) {
                StringBuilder targetBuilder = new StringBuilder();
                targetBuilder.append(targetTMsg.contrIntfcSrcTpCd.getValue());
                targetBuilder.append(",");
                targetBuilder.append(targetTMsg.dsContrSrcRefNum.getValue());
                targetBuilder.append(",");
                targetBuilder.append(targetTMsg.svcMachMstrPk.getValue());
                targetBuilder.append(",");
                targetBuilder.append(targetTMsg.bllgMtrLbCd.getValue());
                targetBuilder.append(",");
                BigDecimal targetXsMtrCopyQty = targetTMsg.xsMtrCopyQty.getValue();
                if (targetXsMtrCopyQty != null) {
                    targetBuilder.append(targetXsMtrCopyQty.toString());
                } else {
                    targetBuilder.append((String) null);
                }
                String targetGroupKey = targetBuilder.toString();
                if (hasValue(groupKey) && groupKey.equals(targetGroupKey)) {
                    sameCount++;
                }
            }
            if (sameCount > 1) {
                setErrorMessage(prmPmsg, NSXM0090E, errorTotalCount + errorCount);
                setErrorStatusAndMessage(tMsg, NSXM0090E);
                errorCount++;
            }
            // 2016/03/28 END   [QC#5822, MOD]
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

    private static void setErrorStatusAndMessage(DS_XS_COPY_INTFCTMsg tMsg, String errorMsgId) {
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
     * @param rstMsgList List<DS_XS_COPY_INTFCTMsg>
     */
    public static void updateValidationResult(ValidationReturnBean resultPMsg, List<DS_XS_COPY_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        if (resultPMsg == null) {
            resultPMsg = new ValidationReturnBean();
        }
        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_XS_COPY_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.dsXsCopyIntfcPk)) {
                // for update
                DS_XS_COPY_INTFCTMsg tMsg = getDsXsCopyIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsXsCopyIntfcPk.getValue());
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
                    setValue(tMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(resultPMsg, NSXM0098E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0098E);

                    return;
                }
            }
        }
    }

    private static DS_XS_COPY_INTFCTMsg getDsXsCopyIntfc(String glblCmpyCd, BigDecimal dsXsCopyIntfcPk) {
        DS_XS_COPY_INTFCTMsg prmTMsg = new DS_XS_COPY_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsXsCopyIntfcPk, dsXsCopyIntfcPk);
        return (DS_XS_COPY_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
    // END   2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
}
