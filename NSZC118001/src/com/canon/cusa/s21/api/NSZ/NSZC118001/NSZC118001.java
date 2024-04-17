/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC118001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.WMS_MTR_READ_UPDTMsg;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC109001PMsg;
import business.parts.NSZC118001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC109001.NSZC109001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Meter Update for WMS API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/06   CITS            T.Tokutomi      Create          QC#22198_Sol#171
 * </pre>
 */
public class NSZC118001 extends S21ApiCommonBase {

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
     * NSZM0006E: Service machine master is not found.
     */
    private static final String NSZM0006E = "NSZM0006E";

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
     * NSZM1316E: Input parameter "Meter Label Code" is a mandatory
     * field.
     */
    private static final String NSZM1316E = "NSZM1316E";

    /**
     * NSZM1317E: The corresponding data does not exist Meter Label
     * Code.
     */
    private static final String NSZM1317E = "NSZM1317E";

    /**
     * NSZM1318W: Meter Read Count has already been registered.
     */
    private static final String NSZM1318W = "NSZM1318W";

    /**
     * NSZM1319E: Failed to insert WMS Meter Reading Update table.
     */
    private static final String NSZM1319E = "NSZM1319E";

    /**
     * NSZM1320E: Input parameter "Service Physical Meter List" is not
     * set.
     */
    private static final String NSZM1320E = "NSZM1320E";

    /**
     * NSZM0268E: Input parameter "Meter Read Date" is a mandatory
     * field.
     */
    private static final String NSZM0268E = "NSZM0268E";

    /**
     * NSZM0266E: Input parameter "Reading Meter Count" is a mandatory
     * field.
     */
    private static final String NSZM0266E = "NSZM0266E";

    // Const valiable

    /**
     * PROGRAM_ID
     */
    private static final String PROGRAM_ID = "NSZC118001";

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
    public NSZC118001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC118001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NSZC118001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC118001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC118001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC118001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        // initialize
        this.onBatchType = onBatchType;
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

        try {
            // write WMS_MTR_READ_UPD table.
            writeResultData(msgMap);

            // write log.
            EZDConnectionMgr.getInstance().commit();
        } catch (Exception e) {
            // insert Error.
            EZDConnectionMgr.getInstance().rollback();
            setErrorMessage(msgMap, NSZM1319E);
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
        }

        // Process end.
    }

    /**
     * doProcess
     * @param msgMap S21ApiMessageMap
     */
    private void doProcess(S21ApiMessageMap msgMap) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

        // check parameter
        boolean isError = false;

        isError = !isMandatoryParam(msgMap);

        // check master data.

        // check Service Machine Master Pk
        BigDecimal svcMachMstrPk = null;
        if (!isError) {
            svcMachMstrPk = getSvcMachMstrPk(msgMap);

            if (svcMachMstrPk == null) {
                // Error
                isError = true;
                setErrorMessage(msgMap, NSZM0006E);
            }
        }

        // check Service Physical Meter Pk & Meter Label Code.
        HashMap<String, BigDecimal> svcPhysMtrPkList = new HashMap<String, BigDecimal>();
        if (!isError) {
            for (int i = 0; i < pMsg.SvcPhysMtrList.getValidCount(); i++) {
                String glblCmpyCd = pMsg.glblCmpyCd.getValue();
                String mtrLbCd = pMsg.SvcPhysMtrList.no(i).mtrLbCd.getValue();

                if (!existMtrLbCd(glblCmpyCd, mtrLbCd)) {
                    // Error
                    isError = true;
                    setErrorMessageForMtrList(msgMap, NSZM1317E, i);
                    continue;
                }

                BigDecimal svcPhysMtrPk = getSvcPhsyMtrPk(glblCmpyCd, svcMachMstrPk, mtrLbCd);

                if (svcPhysMtrPk == null) {
                    // Error
                    isError = true;
                    setErrorMessageForMtrList(msgMap, NSZM1314E, i);
                } else {
                    svcPhysMtrPkList.put(mtrLbCd, svcPhysMtrPk);
                }
            }
        }

        // has Error
        if (isError) {
            return;
        }

        // check sales Date.
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }

        // call Meter Read Validation.
        if (!callMeterReadValidAPI(msgMap)) {
            // has Error.
            return;
        }

        // call Meter Update API
        callMeterUpdateAPI(msgMap, svcMachMstrPk, svcPhysMtrPkList);
    }

    /**
     * isMandatoryParam
     * @param msgMap
     * @return true:Ok / false:NG
     */
    private boolean isMandatoryParam(S21ApiMessageMap msgMap) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

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

        if (pMsg.SvcPhysMtrList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.SvcPhysMtrList.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(pMsg.SvcPhysMtrList.no(i).mtrLbCd)) {
                    result = false;
                    setErrorMessageForMtrList(msgMap, NSZM1316E, i);
                }
                if (!ZYPCommonFunc.hasValue(pMsg.SvcPhysMtrList.no(i).mtrReadDt)) {
                    result = false;
                    setErrorMessageForMtrList(msgMap, NSZM0268E, i);
                }
                if (!ZYPCommonFunc.hasValue(pMsg.SvcPhysMtrList.no(i).readMtrCnt)) {
                    result = false;
                    setErrorMessageForMtrList(msgMap, NSZM0266E, i);
                }
            }
        } else {
            // Error
            result = false;
            setErrorMessage(msgMap, NSZM1320E);
        }

        return result;

    }

    /**
     * getSvcMachMstrPk
     * @param msgMap S21ApiMessageMap
     * @return svcMachMstrPk BigDecimal
     */
    private BigDecimal getSvcMachMstrPk(S21ApiMessageMap msgMap) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("serNum", pMsg.serNum.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        return (BigDecimal) this.ssmBatchClient.queryObject("getSvcMachMstrPk", ssmParam);
    }

    /**
     * existMtrLbCd
     * @param glblCmpyCd String
     * @param mtrLbCd String
     * @return true:Exist / false:Not Exist
     */
    private boolean existMtrLbCd(String glblCmpyCd, String mtrLbCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mtrLbCd", mtrLbCd);

        String mtrLb = (String) this.ssmBatchClient.queryObject("getMtrLbCd", ssmParam);

        if (ZYPCommonFunc.hasValue(mtrLb)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getSvcPhsyMtrPk
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param mtrLbCd String
     * @return svcPhysMtrPk BigDecimal
     */
    private BigDecimal getSvcPhsyMtrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String mtrLbCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("mdlMtrLbCd", mtrLbCd);

        return (BigDecimal) this.ssmBatchClient.queryObject("getSvcPhysMtrPk", ssmParam);
    }

    /**
     * callMeterReadValidAPI
     * @param msgMap
     * @return true:OK / false:Error or Warning
     */
    private boolean callMeterReadValidAPI(S21ApiMessageMap msgMap) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

        NSZC109001PMsg param = new NSZC109001PMsg();

        // mapping
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(param.wmsWhCd, pMsg.wmsWhCd);
        ZYPEZDItemValueSetter.setValue(param.psnCd, pMsg.psnCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(param.serNum, pMsg.serNum);

        // Call API
        NSZC109001 api = new NSZC109001();
        api.execute(param, onBatchType);

        // check result
        if (RETURN_CODE_ERROR.equals(param.rsltStsCd.getValue())) {
            // Error
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                setErrorMessage(msgMap, param.xxMsgIdList.no(i).xxMsgId.getValue());
            }

            return false;
        } else if (RETURN_CODE_SUCCESS.equals(param.rsltStsCd.getValue())) {
            // Warning
            setErrorMessage(msgMap, NSZM1318W);
            return false;
        }

        return true;
    }

    /**
     * callMeterUpdateAPI
     * @param msgMap S21ApiMessageMap
     * @param svcMachMstrPk BigDecimal
     * @param svcPhysMtrPkList HashMap<String, BigDecimal>
     */
    private void callMeterUpdateAPI(S21ApiMessageMap msgMap, BigDecimal svcMachMstrPk, HashMap<String, BigDecimal> svcPhysMtrPkList) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

        NSZC010001PMsg param = new NSZC010001PMsg();

        // mapping
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
        ZYPEZDItemValueSetter.setValue(param.dsMtrReadTpCd, DS_MTR_READ_TP.WMS);
        ZYPEZDItemValueSetter.setValue(param.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(param.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(param.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(param.xxStartReadFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(param.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.SUPPLY_READS);

        int i = 0;
        for (; i < pMsg.SvcPhysMtrList.getValidCount(); i++) {
            String mtrLbCd = pMsg.SvcPhysMtrList.no(i).mtrLbCd.getValue();
            BigDecimal svcPhysMtrPk = svcPhysMtrPkList.get(mtrLbCd);

            ZYPEZDItemValueSetter.setValue(param.A.no(i).mtrReadDt, pMsg.SvcPhysMtrList.no(i).mtrReadDt);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).rgtnMtrDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).readMtrCnt, pMsg.SvcPhysMtrList.no(i).readMtrCnt);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).rgtnUsrId, PROGRAM_ID);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).estFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).svcPhysMtrPk, svcPhysMtrPk);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).vldMtrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(param.A.no(i).mdlMtrLbCd, mtrLbCd);
        }
        param.A.setValidCount(i);

        // Call API
        NSZC010001 api = new NSZC010001();
        api.execute(param, onBatchType);

        if (RETURN_CODE_ERROR.equals(param.xxRsltStsCd.getValue())) {
            // Line Error Message
            for (int j = 0; j < param.A.getValidCount(); j++) {
                if (RETURN_CODE_ERROR.equals(param.A.no(j).xxRsltStsCd.getValue())) {
                    setErrorMessageForMtrList(msgMap, param.A.no(j).xxMsgId.getValue(), j);
                }
            }

            for (int k = 0; k < param.xxMsgIdList.getValidCount(); k++) {
                setErrorMessage(msgMap, param.xxMsgIdList.no(k).xxMsgId.getValue());
            }
        }

    }

    /**
     * setResultStatusCode
     * @param msgMap S21ApiMessageMap
     */
    private void setResultStatusCode(S21ApiMessageMap msgMap) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();
        boolean isError = false;

        // check line.
        for (int i = 0; i < pMsg.SvcPhysMtrList.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(pMsg.SvcPhysMtrList.no(i).wmsErrMsgCd)) {
                isError = true;
                break;
            }
        }

        // check set Message.
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            if (isErrorMessage(pMsg.xxMsgIdList.no(i).xxMsgId.getValue())) {
                isError = true;
                break;
            }
        }

        if (isError) {
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_SUCCESS);
        }
    }

    /**
     * isErrorMessage
     * @param msgId
     * @return true:Yes/false:No
     */
    private boolean isErrorMessage(String msgId) {
        if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith("E")) {
            return true;
        }
        return false;
    }

    /**
     * writeResultData
     * @param msgMap S21ApiMessageMap
     */
    private void writeResultData(S21ApiMessageMap msgMap) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();
        boolean dbError = false;

        for (int i = 0; i < pMsg.SvcPhysMtrList.getValidCount(); i++) {
            WMS_MTR_READ_UPDTMsg wmruTMsg = new WMS_MTR_READ_UPDTMsg();

            ZYPEZDItemValueSetter.setValue(wmruTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsMtrReadUpdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_MTR_READ_UPD_SQ));
            ZYPEZDItemValueSetter.setValue(wmruTMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsWhCd, pMsg.wmsWhCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.psnCd, pMsg.psnCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.mdseCd, pMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.serNum, pMsg.serNum);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.SUPPLY_READS);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.mtrLbCd, pMsg.SvcPhysMtrList.no(i).mtrLbCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.dsMtrReadTpCd, DS_MTR_READ_TP.WMS);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.mtrReadDt, pMsg.SvcPhysMtrList.no(i).mtrReadDt);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.readMtrCnt, pMsg.SvcPhysMtrList.no(i).readMtrCnt);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.rsltStsCd, pMsg.rsltStsCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsErrMsgCd, pMsg.SvcPhysMtrList.no(i).wmsErrMsgCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsErrMsgTxt, pMsg.SvcPhysMtrList.no(i).wmsErrMsgTxt);

            S21ApiTBLAccessor.insert(wmruTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmruTMsg.getReturnCode())) {
                // insert Error.
                setErrorMessage(msgMap, NSZM1319E);
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
                dbError = true;
                break;
            }
        }

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            WMS_MTR_READ_UPDTMsg wmruTMsg = new WMS_MTR_READ_UPDTMsg();

            ZYPEZDItemValueSetter.setValue(wmruTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsMtrReadUpdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_MTR_READ_UPD_SQ));
            ZYPEZDItemValueSetter.setValue(wmruTMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsWhCd, pMsg.wmsWhCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.psnCd, pMsg.psnCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.mdseCd, pMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.serNum, pMsg.serNum);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.rsltStsCd, pMsg.rsltStsCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsErrMsgCd, pMsg.xxMsgIdList.no(i).wmsErrMsgCd);
            ZYPEZDItemValueSetter.setValue(wmruTMsg.wmsErrMsgTxt, pMsg.xxMsgIdList.no(i).wmsErrMsgTxt);

            S21ApiTBLAccessor.insert(wmruTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmruTMsg.getReturnCode())) {
                // insert Error.
                setErrorMessage(msgMap, NSZM1319E);
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, RETURN_CODE_ERROR);
                dbError = true;
                break;
            }
        }
    }

    /**
     * setErrorMessage
     * @param msgMap S21ApiMessageMap
     * @param msgId String
     */
    private void setErrorMessage(S21ApiMessageMap msgMap, String msgId) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

        int index = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(index).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(index).wmsErrMsgCd, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(index).wmsErrMsgTxt, S21MessageFunc.clspGetMessage(msgId));
        pMsg.xxMsgIdList.setValidCount(++index);
    }

    private void setErrorMessageForMtrList(S21ApiMessageMap msgMap, String msgId, int index) {
        NSZC118001PMsg pMsg = (NSZC118001PMsg) msgMap.getPmsg();

        ZYPEZDItemValueSetter.setValue(pMsg.SvcPhysMtrList.no(index).wmsErrMsgCd, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.SvcPhysMtrList.no(index).wmsErrMsgTxt, S21MessageFunc.clspGetMessage(msgId));
    }
}
