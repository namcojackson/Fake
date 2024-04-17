/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC117001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.WMS_MTR_READ_INFOTMsg;
import business.parts.NSZC117001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Meter Info for WMS API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/04   CITS            T.Tokutomi      Create          QC#22198_Sol#171
 * 2018/02/05   CITS            T.Tokutomi      Update          QC#23918
 * </pre>
 */
public class NSZC117001 extends S21ApiCommonBase {

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    // Error Message List
    /**
     * NSZM0001E: Input parameter "Global Company Code" is a mandatory
     * field.
     */
    private static final String NSZM0001E = "NSZM0001E";

    /**
     * NSZM0013E: Input parameter "Merchandise Code" is a mandatory
     * field.
     */
    private static final String NSZM0013E = "NSZM0013E";

    /**
     * NSZM0012E: Input parameter "Serial Number" is a mandatory
     * field.
     */
    private static final String NSZM0012E = "NSZM0012E";

    /**
     * NSZM1314E: The corresponding data does not exist Service
     * Physical Meter.
     */
    private static final String NSZM1314E = "NSZM1314E";

    /**
     * NSZM1315E: Failed to insert WMS Meter Reading Information.
     */
    private static final String NSZM1315E = "NSZM1315E";

    // Const valiable
    /**
     * Const Value:WMS_MTR_READ_PAST_DAY_AOT
     */
    private static final String CONST_WMS_MTR_READ_PAST_DAY_AOT = "WMS_MTR_READ_PAST_DAY_AOT";

    /**
     * Result Status Code:SUCCESS
     */
    private static final String RETURN_CODE_SUCCESS = "1";

    /**
     * Result Status Code:ERROR
     */
    private static final String RETURN_CODE_ERROR = "9";

    /**
     * Constructor
     */
    public NSZC117001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC117001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final List<NSZC117001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC117001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC117001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC117001PMsg pMsg, final ONBATCH_TYPE onBatchTp) {
        // initialize
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        // main process.
        doProcess(msgMap);

        // set Result Status.
        setResultStatusCode(msgMap);

        // control transaction.
        if (RETURN_CODE_ERROR.equals(pMsg.rsltStsCd.getValue())) {
            EZDConnectionMgr.getInstance().rollback();
        } else {
            EZDConnectionMgr.getInstance().commit();
        }

        // write WMS_MTR_READ_INFO table.
        try {
            writeResultData(msgMap);

            // write log.
            EZDConnectionMgr.getInstance().commit();
        } catch (Exception e) {
            // insert Error.
            EZDConnectionMgr.getInstance().rollback();
            setErrorMessage(msgMap, NSZM1315E);
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
        }

        // process end.
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     */
    private void doProcess(S21ApiMessageMap msgMap) {
        // check parameter
        if (!isMandatoryParam(msgMap)) {
            // has Error
            return;
        }

        // get Meter Read count
        List<Map<String, Object>> mtrRdCnt = getMeterReadCount(msgMap);

        if (mtrRdCnt == null || mtrRdCnt.isEmpty()) {
            // no data.
            setErrorMessage(msgMap, NSZM1314E);
            return;
        }

        // set paramater
        setOutputParam(msgMap, mtrRdCnt);
    }

    /**
     * isMandatoryParam
     * @param msgMap
     * @return true:Ok / false:NG
     */
    private boolean isMandatoryParam(S21ApiMessageMap msgMap) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();
        boolean result = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            result = false;
            setErrorMessage(msgMap, NSZM0001E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.mdseCd)) {
            result = false;
            setErrorMessage(msgMap, NSZM0013E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
            result = false;
            setErrorMessage(msgMap, NSZM0012E);
        }

        return result;
    }

    /**
     * getMeterReadCount
     * @param msgMap
     * @return (List<Map<String, Object>>) Meter Read Count List or
     * null.
     */
    private List<Map<String, Object>> getMeterReadCount(S21ApiMessageMap msgMap) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();

        BigDecimal mrgSq = getMeterReadCountGrpSq(msgMap);

        // QC#23918 modify start.
        if (!ZYPCommonFunc.hasValue(mrgSq)) {
            // Meter_Read does not exist. Check SVC_PYSY_MTR exists.

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("serNum", pMsg.serNum.getValue());
            ssmParam.put("mdseCd", pMsg.mdseCd.getValue());
            ssmParam.put("flgOn_Y", ZYPConstant.FLG_ON_Y);
            ssmParam.put("slsDt", pMsg.slsDt.getValue());

            return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMeterInfo", ssmParam);
        } else {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("serNum", pMsg.serNum.getValue());
            ssmParam.put("mdseCd", pMsg.mdseCd.getValue());
            ssmParam.put("svcPhysMtrRdGrpSq", mrgSq);

            return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMeterReadCount", ssmParam);
        }
        // QC#23918 modify end.
    }

    /**
     * getMeterReadCountGrpSq
     * @param msgMap S21ApiMessageMap
     * @return (BigDecimal) service physical Meter reading group
     * sequece
     */
    private BigDecimal getMeterReadCountGrpSq(S21ApiMessageMap msgMap) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();

        // check sales date.
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }

        // get WMS_MTR_READ_PAST_DAY_AOT
        BigDecimal aotDays = ZYPCodeDataUtil.getNumConstValue(CONST_WMS_MTR_READ_PAST_DAY_AOT, pMsg.glblCmpyCd.getValue());
        String rsDt = ZYPDateUtil.addDays(pMsg.slsDt.getValue(), -aotDays.intValue());

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("serNum", pMsg.serNum.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());
        ssmParam.put("flgOn_Y", ZYPConstant.FLG_ON_Y);
        ssmParam.put("mtrRdPstDay", rsDt);
        ssmParam.put("slsDt", pMsg.slsDt.getValue());

        List<Map<String, Object>> svcPhyMtrRdGrpSqList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcPhysclMtrReadGrpSq", ssmParam);

        // Result.
        if (svcPhyMtrRdGrpSqList != null && !svcPhyMtrRdGrpSqList.isEmpty()) {
            // get first record.
            return (BigDecimal) svcPhyMtrRdGrpSqList.get(0).get("SVC_PHYS_MTR_READ_GRP_SQ");
        } else {
            return null;
        }
    }

    /**
     * setOutputParam
     * @param msgMap S21ApiMessageMap
     * @param mtrRdCnt List<Map<String, Object>>
     */
    private void setOutputParam(S21ApiMessageMap msgMap, List<Map<String, Object>> mtrRdCnt) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();

        int index = 0;
        for (Map<String, Object> record : mtrRdCnt) {
            ZYPEZDItemValueSetter.setValue(pMsg.SvcPhysMtrList.no(index).mtrLbCd, (String) record.get("MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.SvcPhysMtrList.no(index).mdlMtrLbNoteTxt, (String) record.get("MDL_MTR_LB_NOTE_TXT"));
            ZYPEZDItemValueSetter.setValue(pMsg.SvcPhysMtrList.no(index).readMtrCnt, (BigDecimal) record.get("READ_MTR_CNT"));
            ++index;
        }
        pMsg.SvcPhysMtrList.setValidCount(index);
    }

    /**
     * setResultStatusCode
     * @param msgMap S21ApiMessageMap
     */
    private void setResultStatusCode(S21ApiMessageMap msgMap) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_SUCCESS);
        }
    }

    /**
     * writeResultData
     * @param msgMap
     */
    private void writeResultData(S21ApiMessageMap msgMap) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();
        boolean dbError = false;

        for (int i = 0; i < pMsg.SvcPhysMtrList.getValidCount(); i++) {
            WMS_MTR_READ_INFOTMsg wmriTMsg = new WMS_MTR_READ_INFOTMsg();

            ZYPEZDItemValueSetter.setValue(wmriTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.wmsMtrReadInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_MTR_READ_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(wmriTMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.wmsWhCd, pMsg.wmsWhCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.psnCd, pMsg.psnCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.mdseCd, pMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.serNum, pMsg.serNum);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.rsltStsCd, pMsg.rsltStsCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.mtrLbCd, pMsg.SvcPhysMtrList.no(i).mtrLbCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.mdlMtrLbNoteTxt, pMsg.SvcPhysMtrList.no(i).mdlMtrLbNoteTxt);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.readMtrCnt, pMsg.SvcPhysMtrList.no(i).readMtrCnt);

            S21ApiTBLAccessor.insert(wmriTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmriTMsg.getReturnCode())) {
                // insert Error.
                setErrorMessage(msgMap, NSZM1315E);
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
                dbError = true;
                break;
            }
        }

        if (dbError) {
            // Process stop. Occurred DB insert error.
            return;
        }

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            WMS_MTR_READ_INFOTMsg wmriTMsg = new WMS_MTR_READ_INFOTMsg();

            ZYPEZDItemValueSetter.setValue(wmriTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.wmsMtrReadInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_MTR_READ_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(wmriTMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.wmsWhCd, pMsg.wmsWhCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.psnCd, pMsg.psnCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.mdseCd, pMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.serNum, pMsg.serNum);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.rsltStsCd, pMsg.rsltStsCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.wmsErrMsgCd, pMsg.xxMsgIdList.no(i).wmsErrMsgCd);
            ZYPEZDItemValueSetter.setValue(wmriTMsg.wmsErrMsgTxt, pMsg.xxMsgIdList.no(i).wmsErrMsgTxt);

            S21ApiTBLAccessor.insert(wmriTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmriTMsg.getReturnCode())) {
                // insert Error.
                setErrorMessage(msgMap, NSZM1315E);
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
                dbError = true;
                break;
            }
        }
    }

    /**
     * setErrorMessage
     * @param msgMap S21ApiMessageMap
     * @param msgId msgId
     */
    private void setErrorMessage(S21ApiMessageMap msgMap, String msgId) {
        NSZC117001PMsg pMsg = (NSZC117001PMsg) msgMap.getPmsg();

        int index = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(index).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(index).wmsErrMsgCd, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(index).wmsErrMsgTxt, S21MessageFunc.clspGetMessage(msgId));
        pMsg.xxMsgIdList.setValidCount(++index);

    }
}
