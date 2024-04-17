package com.canon.cusa.s21.api.NSZ.NSZC076001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.api.NSZ.NSZC076001.constant.NSZC076001Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASK_STSTMsg;
import business.db.SVC_TASK_STSTMsgArray;
import business.parts.NSZC058001PMsg;
import business.parts.NSZC076001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC058001.NSZC058001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Receive Supplemental Time from Click Software API                                                                                                                                                          
 *
 * Date         Company         Name            Create/Update   Defect No                                                                                                                                                           
 * ----------------------------------------------------------------------                                                                                                                                                           
 * 10/06/2015   Hitachi         J.Kim           Create          N/A
 * 12/09/2015   Hitachi         J.Kim           Update          QC#1403                                                                                                                                                      
 * 02/09/2016   Hitachi         Y.Takeno        Update          QC#3727                                                                                                                                                     
 * 02/17/2016   Hitachi         T.Iwamoto       Update          QC#3727
 * 03/25/2016   Hitachi         T.Iwamoto       Update          QC#5965
 *</pre>
 */

public class NSZC076001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** endDayTaskFlg */
    private String endDayTaskFlg = null;

    /** svcTaskStsNm */
    private String svcTaskStsNm = null;

    // START 03/25/2016 [QC#5965, MOD]
    /** starDate */
    private String svcSupplFromDt = null;

    /** startTime */
    private String svcSupplFromTm = null;

    /** endDate */
    private String svcSupplToDt = null;

    /** endTime */
    private String svcSupplToTm = null;

    // END 03/25/2016 [QC#5965, MOD]

    /**
     * Constructor
     */
    public NSZC076001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute API.
     * @param pMsg NSZC076001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(NSZC076001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        this.onBatchType = onBatTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap)) {
            doProcess(msgMap, pMsg);
        }
        msgMap.flush();
    }

    /**
     * checkParameter
     * @param msgMap S21ApiMessageMap
     * @return
     */
    private boolean checkParameter(S21ApiMessageMap msgMap) {

        NSZC076001PMsg pMsg = (NSZC076001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.svcSupplFromDt, NSZM0699E);
        mandatoryCheck(msgMap, pMsg.svcSupplFromTm, NSZM0700E);
        mandatoryCheck(msgMap, pMsg.svcSupplTaskTpDescTxt, NSZM0701E);
        mandatoryCheck(msgMap, pMsg.psnCd, NSZM0293E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    /**
     * mandatoryCheck
     * @param msgMap S21ApiMessageMap
     * @param targetItem EZDPItem
     * @param messageId String
     */
    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC076001PMsg
     */
    private void doProcess(S21ApiMessageMap msgMap, NSZC076001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("tpCdKey", SVC_SUPPL_TASK_TP_CD);
        param.put("svcSupplTaskTpDescTxt", pMsg.svcSupplTaskTpDescTxt.getValue());

        Map<String, String> result = (Map<String, String>) ssmBatchClient.queryObject("getS21SupplTpInfo", param);
        if (result == null) {
            msgMap.addXxMsgId(NSZM0505E);
        } else {

            // START 03/25/2016 [QC#5965, MOD]
            setSupplementalDateTime(pMsg);
            // END 03/25/2016 [QC#5965, MOD]

            String svcMblS21ValTxt = (String) result.get(SVC_MBL_S21_VAL_TXT);
            this.endDayTaskFlg = (String) result.get(END_DAY_TASK_FLG);

            // START 03/25/2016 [QC#5965, MOD]
            String svcSupplTaskNum = null;
            if (hasValue(svcSupplFromDt) && hasValue(svcSupplFromTm)) {
                Map<String, Object> sstParam = new HashMap<String, Object>();
                sstParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                sstParam.put("psnCd", pMsg.psnCd.getValue());
                sstParam.put("stsCd", SVC_TASK_STS.IN_PROCESS);
                sstParam.put("valTxt", svcMblS21ValTxt);
                sstParam.put("startDate", this.svcSupplFromDt);
                sstParam.put("startTime", this.svcSupplFromTm);
                svcSupplTaskNum = (String) ssmBatchClient.queryObject("getSvcSupplTaskNum", sstParam);
            }
            // END 03/25/2016 [QC#5965, MOD]

            NSZC058001PMsg stuApiPMsg = new NSZC058001PMsg();
            if (!hasValue(svcSupplTaskNum)) {
                // NEW CREATE
                setNewCreateData(pMsg, stuApiPMsg, result);
            } else {
                // UPDATE
                setUpdateData(pMsg, stuApiPMsg, result, svcSupplTaskNum);
            }

            // Call NSZC0580 Supplemental Task Update API
            callSupplTaskUpdApi(msgMap, pMsg, stuApiPMsg);

            // Insert Service Memo
            if (stuApiPMsg.xxMsgIdList.getValidCount() == 0) {
                insertServiceMemo(msgMap, pMsg, stuApiPMsg);
            }
        }
    }

    private void insertServiceMemo(S21ApiMessageMap msgMap, NSZC076001PMsg pMsg, NSZC058001PMsg stuApiPMsg) {

        SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
        BigDecimal svcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);
        ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, svcMemoPk);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, getSvcMemoTpCd(pMsg));
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, pMsg.svcCmntTxt);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTrxNum, stuApiPMsg.svcSupplTaskNum);
        ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTrxNm, SVC_SUPPL_TASK_NUM);
        S21FastTBLAccessor.insert(insertTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_MEMO", svcMemoPk.toString() });
        }
    }

    private String getSvcMemoTpCd(NSZC076001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("keyTxt", SVC_MEMO_TP_FOR_SUPPLEMENTAL_TIME);
        param.put("valTxt", SVC_MEMO_TP_CD);

        String svcMemoTpCd = (String) ssmBatchClient.queryObject("getSvcMemoTpCd", param);

        return svcMemoTpCd;
    }

    private String setSvcTaskStsCd(NSZC076001PMsg pMsg) {

        String svcTaskStsCd = null;
        if (ZYPConstant.FLG_ON_Y.equals(this.endDayTaskFlg)) {
            svcTaskStsCd = SVC_TASK_STS.CLOSED;
            this.svcTaskStsNm = CLOSED_NM;
        } else {
            SVC_TASK_STSTMsg inTMsg = new SVC_TASK_STSTMsg();
            inTMsg.setSQLID("001");
            inTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inTMsg.setConditionValue("xtrnlStsRefTxt01", pMsg.xtrnlStsRefTxt.getValue());
            inTMsg.setMaxCount(1);

            SVC_TASK_STSTMsgArray outTMsgArray = (SVC_TASK_STSTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

            if (outTMsgArray == null || outTMsgArray.getValidCount() == 0) {
                return null;
            }

            SVC_TASK_STSTMsg outTMsg = (SVC_TASK_STSTMsg) outTMsgArray.get(0);
            this.svcTaskStsNm = outTMsg.svcTaskStsNm.getValue();
            svcTaskStsCd = outTMsg.svcTaskStsCd.getValue();
        }

        return svcTaskStsCd;
    }

    private void setNewCreateData(NSZC076001PMsg pMsg, NSZC058001PMsg stuApiPMsg, Map<String, String> result) {

        // Global Company Code
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // Mode
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.xxProcMd, MODE_NEW);
        // Supplemental Task Type Code
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplTaskTpCd, (String) result.get(SVC_MBL_S21_VAL_TXT));
        // Supplemental Task Status Code
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplTaskStsCd, setSvcTaskStsCd(pMsg));
        // Technician Code
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.techCd, pMsg.psnCd);

        // START 03/25/2016 [QC#5965, MOD]
        // String svcSupplFromTm =
        // tzSupplFrom.getDateTime().substring(DATE_LEN,
        // svcSupplFromTmstmp.length());
        // set Supplemental From Date/Time, Supplemental Thru
        // Date/Time
        // setSupplementalDateTime(pMsg);
        // Supplemental From Date
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplFromDt, this.svcSupplFromDt);
        // Supplemental From Time
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplFromTm, this.svcSupplFromTm);
        // Supplemental Thru Date
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplToDt, this.svcSupplToDt);
        // Supplemental Thru Time
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplToTm, this.svcSupplToTm);
        // END 03/25/2016 [QC#5965, MOD]
        // Travel Time
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcTrvlTmNum, pMsg.svcTrvlTmNum);
        // Creation User ID
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.cratPsnCd, pMsg.psnCd);

    }

    private void setUpdateData(NSZC076001PMsg pMsg, NSZC058001PMsg stuApiPMsg, Map<String, String> result, String svcSupplTaskNum) {

        // Global Company Code
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        // Mode
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.xxProcMd, MODE_UPDATE);
        // Supplemental Task#
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplTaskNum, (String) svcSupplTaskNum);
        // Supplemental Task Status Code
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplTaskStsCd, setSvcTaskStsCd(pMsg));

        // START 03/25/2016 [QC#5965, MOD]
        // set Supplemental From Date/Time, Supplemental Thru
        // Date/Time
        // setSupplementalDateTime(pMsg);
        // Supplemental From Date
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplFromDt, this.svcSupplFromDt);
        // Supplemental From Time
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplFromTm, this.svcSupplFromTm);
        // Supplemental Thru Date
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplToDt, this.svcSupplToDt);
        // Supplemental Thru Time
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcSupplToTm, this.svcSupplToTm);
        // END 03/25/2016 [QC#5965, MOD]

        // Travel Time
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.svcTrvlTmNum, pMsg.svcTrvlTmNum);
        // Update User ID
        ZYPEZDItemValueSetter.setValue(stuApiPMsg.updPsnCd, pMsg.psnCd);

    }

    private void callSupplTaskUpdApi(S21ApiMessageMap msgMap, NSZC076001PMsg rStApipMsg, NSZC058001PMsg pMsg) {

        // Call Supplemental Task Update API
        new NSZC058001().execute(pMsg, this.onBatchType);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            msgMap.addXxMsgId(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            rStApipMsg.xtrnlStsRefTxt.setValue(ZYPCodeDataUtil.getVarCharConstValue(SUPPL_TASK_ERR_STS, rStApipMsg.glblCmpyCd.getValue()));
        } else {
            rStApipMsg.xtrnlStsRefTxt.setValue(this.svcTaskStsNm);
        }
    }

    // START 03/25/2016 [QC#5965, MOD]
    private void setSupplementalDateTime(NSZC076001PMsg pMsg) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(pMsg.svcSupplFromDt.getValue());
        buffer.append(pMsg.svcSupplFromTm.getValue());
        String svcSupplFromTmstmp = buffer.toString();

        buffer = new StringBuffer();
        buffer.append(pMsg.svcSupplToDt.getValue());
        buffer.append(pMsg.svcSupplToTm.getValue());
        String svcSupplToTmstmp = buffer.toString();

        SvcTimeZoneInfo tzSupplFrom = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, svcSupplFromTmstmp, pMsg.psnCd.getValue());
        SvcTimeZoneInfo tzSupplTo = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, svcSupplToTmstmp, pMsg.psnCd.getValue());

        if (tzSupplFrom != null) {
            this.svcSupplFromDt = tzSupplFrom.getDateTime().substring(0, DATE_LEN);
            this.svcSupplFromTm = tzSupplFrom.getDateTime().substring(DATE_LEN, svcSupplFromTmstmp.length());
        }
        if (tzSupplTo != null) {
            this.svcSupplToDt = tzSupplTo.getDateTime().substring(0, DATE_LEN);
            this.svcSupplToTm = tzSupplTo.getDateTime().substring(DATE_LEN, svcSupplFromTmstmp.length());
        }
    }
    // END 03/25/2016 [QC#5965, MOD]
}
