/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC109001;

import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.CONST_WMS_MTR_READ_PAST_DAY_AOT;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.CONST_RSLT_STS_CD_1;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.CONST_RSLT_STS_CD_0;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.CONST_RSLT_STS_CD_9;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM0001E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM0006E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM1055E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM0012E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM1056E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM1057E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM1058E;
import static com.canon.cusa.s21.api.NSZ.NSZC109001.constant.NSZC109001Constant.NSZM1059E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.db.WMS_MTR_READ_RQSTTMsg;
import business.parts.NSZC109001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Check Meter Read For WMS API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/23   Hitachi         Y.Zhang         Create          N/A
 * 2016/09/09   Hitachi         Y.Zhang         Update          QC#14017
 * 2017/07/12   Hitachi         T.Tomita        Update          QC#19877
 * 2017/12/06   CITS            T.Tokutomi      Update          QC#22198 Sol#171
 * </pre>
 */
public class NSZC109001 extends S21ApiCommonBase {

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * Constructor
     */
    public NSZC109001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC109001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC109001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC109001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param param NSZC109001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC109001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        // if slsDt is not exist,systemDate set
        if (!hasValue(param.slsDt)) {
            ZYPEZDItemValueSetter.setValue(param.slsDt, ZYPDateUtil.getSalesDate());
        }

        WMS_MTR_READ_RQSTTMsg inTmsg = new WMS_MTR_READ_RQSTTMsg();

        // if input Parameter check successfully,process execute
        if (checkParameter(msgMap)) {
            // START 2016/09/09 Y.Zhang [QC#14017, MOD]
            // get new WMS_MTR_READ_RQST_PK
            BigDecimal sqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_MTR_READ_RQST_SQ);
            // END 2016/09/09 Y.Zhang [QC#14017, MOD]
            
            // get WMS_MTR_READ_PAST_DAY_AOT
            BigDecimal aotDays = ZYPCodeDataUtil.getNumConstValue(CONST_WMS_MTR_READ_PAST_DAY_AOT, param.glblCmpyCd.getValue());
            int days = 0;
            if (aotDays != null) {
                days = aotDays.intValue();
            }
            // history info is registered into table CHK_MTR_READ_RQST
            if (!insertWmsMtrReadRqst(param, msgMap, inTmsg, sqNum)) {
                return;
            }
            // if registered successful, do commit
            EZDConnectionMgr.getInstance().commit();
            // Meter check
            checkMtrInfo(msgMap, param, inTmsg, days);

            // history info update
            if (!updateWmsMtrReadRqs(msgMap, inTmsg)) {
                return;
            }
            // if update successful, do commit
            EZDConnectionMgr.getInstance().commit();
        }
        msgMap.flush();

        //Set Message Text
        for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
            String msgErrTxt = S21MessageFunc.clspGetMessage(param.xxMsgIdList.no(i).xxMsgId.getValue());
            ZYPEZDItemValueSetter.setValue(param.xxMsgIdList.no(i).wmsErrMsgTxt, msgErrTxt);
        }
    }

    /**
     * checkParameter
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC109001PMsg param = (NSZC109001PMsg) msgMap.getPmsg();

        // check common mandatory parameter
        mandatoryCheck(msgMap, param.glblCmpyCd, NSZM0001E);
        // QC#22198 Sol#171 Update. Delete rwsRefNum mandatory check.
        // mandatoryCheck(msgMap, param.rwsRefNum, NSZM1055E);
        mandatoryCheck(msgMap, param.mdseCd, NSZM1056E);
        mandatoryCheck(msgMap, param.serNum, NSZM0012E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            ZYPEZDItemValueSetter.setValue(param.rsltStsCd, CONST_RSLT_STS_CD_9);
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
     * checkMtrInfo
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC109001PMsg
     * @param inTmsg StriWMS_MTR_READ_RQSTTMsgng
     * @param days int
     */
    private void checkMtrInfo(S21ApiMessageMap msgMap, NSZC109001PMsg pMsg, WMS_MTR_READ_RQSTTMsg inTmsg, int days) {

        // get SVC_MACH_MSTR info
        Map<String, Object> machineMasterInfo = getMachineMasterInfo(pMsg);
        // if get info is not exist
        if (machineMasterInfo == null) {
            msgMap.addXxMsgId(NSZM0006E);
            setMsgInfo(inTmsg, NSZM0006E);
            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_9);
            return;
        } else {
            // if SVC_MACH_MSTR_PK is not exist
            if ((machineMasterInfo.get("SVC_MACH_MSTR_PK") == null)) {
                msgMap.addXxMsgId(NSZM0006E);
                setMsgInfo(inTmsg, NSZM0006E);
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_1);
                return;
            }

            // if MTR_REQ_MDL_FLG is n
            if (machineMasterInfo.get("MTR_REQ_MDL_FLG") != null) {
                if (ZYPConstant.FLG_OFF_N.equals(machineMasterInfo.get("MTR_REQ_MDL_FLG").toString())) {
                    msgMap.addXxMsgId(NSZM1058E);
                    setMsgInfo(inTmsg, NSZM1058E);
                    ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_9);
                    return;
                }
            }

            // get Meter info
            SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = getSvcPhysMtrRead(msgMap, pMsg, machineMasterInfo, days);

            // if Meter info is not exist
            if (svcPhysMtrReadTMsgArray.getValidCount() == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_0);
                return;
            }

            // get Mandatory MeterInfo
            List<Map<String, Object>> getMeterInfoList = getMeterInfo(pMsg, machineMasterInfo, svcPhysMtrReadTMsgArray);

            // if the value of TOT_MTR_FLG is Y, set RsltStsCd value
            for (Map<String, Object> meterInfo : getMeterInfoList) {
                if (meterInfo.get("TOT_MTR_FLG") != null) {
                    if (ZYPConstant.FLG_ON_Y.equals(meterInfo.get("TOT_MTR_FLG").toString())) {
                        if (meterInfo.get("READ_MTR_CNT") != null) {
                            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_1);
                            return;
                        } else {
                            ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_0);
                            return;
                        }
                    }
                }

            }

            // if the value of TOT_MTR_FLG is not Y, RsltStsCd value
            // set
            if (getMeterInfoList.get(0).get("READ_MTR_CNT") != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_1);
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.rsltStsCd, CONST_RSLT_STS_CD_0);
                return;
            }
        }
    }

    /**
     *setMsgInfo
     * @param inTmsg WMS_MTR_READ_RQSTTMsg
     * @param msgStr String
     */
    private void setMsgInfo(WMS_MTR_READ_RQSTTMsg inTmsg, String msgStr) {
        if (!hasValue(inTmsg.wmsErrMsgCd)) {
            ZYPEZDItemValueSetter.setValue(inTmsg.wmsErrMsgCd, msgStr);
            ZYPEZDItemValueSetter.setValue(inTmsg.wmsErrMsgTxt, S21MessageFunc.clspGetMessage(msgStr));
        }
    }

    /**
     *updateWmsMtrReadRqs
     * @param msgMap S21ApiMessageMap
     * @param inTmsg WMS_MTR_READ_RQSTTMsg
     */
    private boolean updateWmsMtrReadRqs(S21ApiMessageMap msgMap, WMS_MTR_READ_RQSTTMsg inTmsg) {
        NSZC109001PMsg param = (NSZC109001PMsg) msgMap.getPmsg();
        // update value set
        ZYPEZDItemValueSetter.setValue(inTmsg.rsltStsCd, param.rsltStsCd);
        // update executed
        S21ApiTBLAccessor.update(inTmsg);
        // if error occurred when update
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTmsg.getReturnCode())) {
            setMsgInfo(inTmsg, NSZM1059E);
            msgMap.addXxMsgId(NSZM1059E);
            return false;
        }
        return true;
    }

    /**
     *insertWmsMtrReadRqst
     * @param param NSZC109001PMsg
     * @param msgMap S21ApiMessageMap
     * @param inTmsg WMS_MTR_READ_RQSTTMsg
     */
    private boolean insertWmsMtrReadRqst(NSZC109001PMsg param, S21ApiMessageMap msgMap, WMS_MTR_READ_RQSTTMsg inTmsg, BigDecimal sqNum) {
        // registered value set
        ZYPEZDItemValueSetter.setValue(inTmsg.wmsMtrReadRqstPk, sqNum);
        ZYPEZDItemValueSetter.setValue(inTmsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.serNum, param.serNum);
        ZYPEZDItemValueSetter.setValue(inTmsg.mdseCd, param.mdseCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.rwsRefNum, param.rwsRefNum);
        ZYPEZDItemValueSetter.setValue(inTmsg.wmsWhCd, param.wmsWhCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.psnCd, param.psnCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.slsDt, param.slsDt);
        // register is executed
        S21ApiTBLAccessor.insert(inTmsg);
        // if failed insert to the CHK_MTR_READ_RQST
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTmsg.getReturnCode())) {
            setMsgInfo(inTmsg, NSZM1057E);
            msgMap.addXxMsgId(NSZM1057E);
            return false;
        }
        return true;

    }

    /**
     *getMachineMasterInfo
     * @param param NSZC109001PMsg
     */
    private Map<String, Object> getMachineMasterInfo(NSZC109001PMsg param) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("serNum", param.serNum.getValue());
        ssmParam.put("mdseCd", param.mdseCd.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getmachineMasterInfo", ssmParam);
    }

    /**
     *getSvcPhysMtrRead
     * @param param NSZC109001PMsg
     * @param machineMasterInfo Map<String, Object>
     * @param days int
     */
    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrRead(S21ApiMessageMap msgMap, NSZC109001PMsg param, Map<String, Object> machineMasterInfo, int days) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        String rsDt = ZYPDateUtil.addDays(param.slsDt.getValue(), -days);
        inMsg.setSQLID("010");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", machineMasterInfo.get("SVC_MACH_MSTR_PK"));
        inMsg.setConditionValue("vldMtrFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("mtrReadDt02", param.slsDt.getValue());
        inMsg.setConditionValue("mtrReadDt01", rsDt);
        // del start 2017/07/12 QC#19877
//        inMsg.setConditionValue("dsMtrReadTpGrpCd01", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        // del end   2017/07/12 QC#19877
        SVC_PHYS_MTR_READTMsgArray tMsgAry = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    /**
     *getMeterInfo
     * @param param NSZC109001PMsg
     * @param machineMasterInfo Map<String, Object>
     * @param svcPhysMtrReadTMsgArray SVC_PHYS_MTR_READTMsgArray
     */
    private List<Map<String, Object>> getMeterInfo(NSZC109001PMsg param, Map<String, Object> machineMasterInfo, SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", machineMasterInfo.get("SVC_MACH_MSTR_PK"));
        ssmParam.put("svcPhysMtrReadGrpSq", svcPhysMtrReadTMsgArray.no(0).svcPhysMtrReadGrpSq.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMeterInfo", ssmParam);
    }
}
