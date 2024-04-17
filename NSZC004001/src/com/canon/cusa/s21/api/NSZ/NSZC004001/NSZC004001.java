/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC004001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.FSRTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC004001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC004001.constant.NSZC004001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Task Creation/Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/31/2013   Fujitsu         J.Sakamoto      Create
 * 09/15/2015   Hitachi         A.Kohinata      Update          NA#Add Param FSR_VISIT_STS_CD
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 08/10/2016   Fujitsu         S.Nakai         Update          CSA QC#8421
 * 09/04/2017   Hitachi         K.Kitachi       Update          CSA QC#20053
 * 01/09/2018   Hitachi         U.Kim           Update          QC#19702
 * 2018/08/09   Hitachi         K.Kojima        Update          QC#27399
 * </pre>
 */
public class NSZC004001 extends S21ApiCommonBase implements NSZC004001Constant {

    /*****************************************************************
     * Variable
     ****************************************************************/
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType = null;

    // START 2018/01/09 U.Kim [QC#19702, ADD]
    /** Waiting Second for find by key */
    private int waitSecUpdTaskOther;

    // END 2018/01/09 U.Kim [QC#19702, ADD]
    /**
     * Constructor
     */
    public NSZC004001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute API.
     * @param pMsg API Parametor
     * @param aOnBatchType ONLINE/BATCH
     */
    public void execute(NSZC004001PMsg pMsg, ONBATCH_TYPE aOnBatchType) {
        onBatchType = aOnBatchType;

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        // START 2018/01/09 U.Kim [QC#19702, ADD]
        setWaitSecUpdTaskOther(pMsg.glblCmpyCd.getValue());
        // END 2018/01/09 U.Kim [QC#19702, ADD]

        if (!checkInputParam(pMsg, msgMap)) {
            msgMap.flush();
            return;
        }
        if (!executeProcess(pMsg, msgMap)) {
            msgMap.flush();
            return;
        }
        msgMap.flush();
    }

    private boolean checkInputParam(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {

        // **************************************************************
        // Mandatory Parameter Check
        // **************************************************************
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }
        if (ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
//NA#ASCC CLICK BugFix MOD Start
//            if (!find(pMsg.xxModeCd.getValue(), PROCESS_MODE_CONTINUOUS_CALL, PROCESS_MODE_ADD_TASK)) {
            if (!find(pMsg.xxModeCd.getValue(), PROCESS_MODE_CONTINUOUS_CALL, PROCESS_MODE_ADD_TASK, PROCESS_MODE_FOLLOWUP_TASK, PROCESS_MODE_CALLAVOID_DISPTTECH)) {
//NA#ASCC CLICK BugFix MOD End
                msgMap.addXxMsgId(NSZM0003E);
            }
        } else {
            msgMap.addXxMsgId(NSZM0003E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
            msgMap.addXxMsgId(NSZM0291E);
        }

        if (PROCESS_MODE_FOLLOWUP_TASK.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.fsrVisitNum)) {
                msgMap.addXxMsgId(NSZM0292E);
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.fsrEventExeUsrId)) {
            msgMap.addXxMsgId(NSZM0293E);
        }
//NA#ASCC CLICK BugFix DEL Start
//        if (!ZYPCommonFunc.hasValue(pMsg.svcChrgContFlg)) {
//            msgMap.addXxMsgId(NSZM0294E);
//        }
//        if (!ZYPCommonFunc.hasValue(pMsg.techCd)) {
//            msgMap.addXxMsgId(NSZM0052E);
//        }
//NA#ASCC CLICK BugFix DEL End
        if (pMsg.xxSvcTaskNumList.getValidCount() <= 0) {
            msgMap.addXxMsgId(NSZM0082E);
        }
        for (int i = 0; i < pMsg.xxSvcTaskNumList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.xxSvcTaskNumList.no(i).svcTaskNum)) {
                msgMap.addXxMsgId(NSZM0082E);
                break;
            }
        }

        if (!PROCESS_MODE_CONTINUOUS_CALL.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.bypsPrfTechFlg)) {
                msgMap.addXxMsgId(NSZM0582E);
            }
            // Add Start 09/15/2015 [NA#Add Param FSR_VISIT_STS_CD]
            if (!ZYPCommonFunc.hasValue(pMsg.fsrVisitStsCd)) {
                msgMap.addXxMsgId(NSZM0624E);
            }
            // Add End 09/15/2015 [NA#Add Param FSR_VISIT_STS_CD]
        }

        msgMap.flush();
        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }
        return false;
    }

    private boolean executeProcess(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {

        // **************************************************************
        // Get Parameters
        // **************************************************************
        FSRTMsg fsrMsg = getFsr(pMsg, msgMap);
        if (fsrMsg == null) {
            return false;
        }

        if (PROCESS_MODE_FOLLOWUP_TASK.equals(pMsg.xxModeCd.getValue())
                || PROCESS_MODE_CALLAVOID_DISPTTECH.equals(pMsg.xxModeCd.getValue())) {

            FSR_VISITTMsg previousFsrVisitMsg = getFsrVisit(pMsg, msgMap);

            if (previousFsrVisitMsg == null) {

                return false;
            }
            // **************************************************************
            // Check Parameter
            // **************************************************************
            existsSvcTask(pMsg, msgMap);
//NA#ASCC CLICK BugFix DEL Start
//        existsTechCd(pMsg, msgMap);
//        if (!SVC_TASK_STS.COMPLETED.equals(previousFsrVisitMsg.fsrVisitStsCd.getValue())) {
//            msgMap.addXxMsgId(NSZM0297E);
//        }
//NA#ASCC CLICK BugFix DEL End
            if (ZYPCommonFunc.hasValue(previousFsrVisitMsg.nextFsrVisitNum)) {
                msgMap.addXxMsgId(NSZM0301E);
            }

            // **************************************************************
            // Check Error.
            // **************************************************************
            msgMap.flush();
            if (msgMap.getMsgMgr().getXxMsgIdListSize() != 0) {
                return false;
            }

            // **************************************************************
            // Execute Main Process.
            // **************************************************************
            if (createFsrVisitRecord(pMsg, msgMap, previousFsrVisitMsg)) {
                if (completeFsrVisitRecord(pMsg, msgMap, previousFsrVisitMsg)) {
                    createFsrEventRecord(pMsg, msgMap);
                    if (ZYPCommonFunc.hasValue(pMsg.fsrVisitStsCd) && SVC_TASK_STS.TBO.equals(pMsg.fsrVisitStsCd.getValue())) {
                        createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.TBO);
                    } else if (ZYPCommonFunc.hasValue(pMsg.fsrVisitStsCd) && SVC_TASK_STS.PENDING_INSTALL.equals(pMsg.fsrVisitStsCd.getValue())) {
                        createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.PENDING_INSTALL);
                    }
                }
            }

        } else if (PROCESS_MODE_ADD_TASK.equals(pMsg.xxModeCd.getValue())) {

            // **************************************************************
            // Execute Main Process.
            // **************************************************************
            if (createFsrVisitRecord(pMsg, msgMap, null)) {

                createFsrEventRecord(pMsg, msgMap);
                if (ZYPCommonFunc.hasValue(pMsg.fsrVisitStsCd) && SVC_TASK_STS.TBO.equals(pMsg.fsrVisitStsCd.getValue())) {
                    createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.TBO);
                } else if (ZYPCommonFunc.hasValue(pMsg.fsrVisitStsCd) && SVC_TASK_STS.PENDING_INSTALL.equals(pMsg.fsrVisitStsCd.getValue())) {
                    createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.PENDING_INSTALL);
                }
            }

        } else {

            msgMap.addXxMsgId(NSZM0039E);
            return false;
        }

        if (!PROCESS_MODE_CONTINUOUS_CALL.equals(pMsg.xxModeCd.getValue())) {
            updateSvcTaskRecord(pMsg, msgMap);
        }

        msgMap.flush();
        if (msgMap.getMsgMgr().getXxMsgIdListSize() != 0) {
            return false;
        }
        return true;
    }

    private FSRTMsg getFsr(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {
        FSRTMsg fsrMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(fsrMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fsrMsg.fsrNum, pMsg.fsrNum);

        fsrMsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(fsrMsg);
        if (fsrMsg == null) {
            msgMap.addXxMsgId(NSZM0295E);
            return null;
        }
        return fsrMsg;
    }

    private FSR_VISITTMsg getFsrVisit(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {
        FSR_VISITTMsg fsrVisitMsg = new FSR_VISITTMsg();
        ZYPEZDItemValueSetter.setValue(fsrVisitMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fsrVisitMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(fsrVisitMsg.fsrVisitNum, pMsg.fsrVisitNum);

        fsrVisitMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKey(fsrVisitMsg);
        if (fsrVisitMsg == null) {
            msgMap.addXxMsgId(NSZM0296E);
            return null;
        }

        return fsrVisitMsg;
    }

    private boolean existsSvcTask(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {
        for (int i = 0; i < pMsg.xxSvcTaskNumList.getValidCount(); i++) {
            SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskNumList.no(i).svcTaskNum);
            tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                msgMap.addXxMsgId(NSZM0302E);
                return false;
            }
        }
        return true;
    }

//    private boolean existsTechCd(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {
//        Map<String, String> param = new HashMap<String, String>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("techCd", pMsg.techCd.getValue());
//        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//        Object result = ssmBatchClient.queryObject("getTechCd", param);
//
//        if (result == null) {
//            msgMap.addXxMsgId(NSZM0287E);
//            return false;
//        }
//        return true;
//    }

    private boolean createFsrVisitRecord(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap, FSR_VISITTMsg previousFsrVisitMsg) {

        // **************************************************************
        // Get New FSR_VISIT_NUM.
        // **************************************************************
        String newFsrVisitNum = getLatestFsrVisitNum(pMsg, msgMap);

        if (previousFsrVisitMsg != null) {

            ZYPEZDItemValueSetter.setValue(previousFsrVisitMsg.nextFsrVisitNum, newFsrVisitNum);
        }

        // **************************************************************
        // Create FSR_VISIT
        // **************************************************************
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        if (PROCESS_MODE_CONTINUOUS_CALL.equals(pMsg.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, newFsrVisitNum);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitStsCd, SVC_TASK_STS.CONTINUOUS_OPEN);
            ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
            ZYPEZDItemValueSetter.setValue(tMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.prevFsrVisitNum, pMsg.fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(tMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitLtstFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.cpltByTelFixFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskNumList.no(0).svcTaskNum);

        } else if (PROCESS_MODE_FOLLOWUP_TASK.equals(pMsg.xxModeCd.getValue())
                || PROCESS_MODE_CALLAVOID_DISPTTECH.equals(pMsg.xxModeCd.getValue())
        ) {

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, newFsrVisitNum);
            // Mod Start 09/15/2015 [NA#Add Param FSR_VISIT_STS_CD]
            // ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitStsCd, SVC_TASK_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitStsCd, pMsg.fsrVisitStsCd);
            // ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.bypsPrfTechFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
            }
            // Mod End 09/15/2015 [NA#Add Param FSR_VISIT_STS_CD]
            ZYPEZDItemValueSetter.setValue(tMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.prevFsrVisitNum, pMsg.fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(tMsg.techSchdFromDt, pMsg.techSchdFromDt);
            ZYPEZDItemValueSetter.setValue(tMsg.techSchdFromTm, pMsg.techSchdFromTm);
            ZYPEZDItemValueSetter.setValue(tMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
            if (ZYPCommonFunc.hasValue(pMsg.schdDisptEmlFlg)) {
                ZYPEZDItemValueSetter.setValue(tMsg.schdDisptEmlFlg, pMsg.schdDisptEmlFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitLtstFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.cpltByTelFixFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskNumList.no(0).svcTaskNum);
//NA#ASCC CLICK BugFix ADD Start
            ZYPEZDItemValueSetter.setValue(tMsg.svcAsgTpCd, pMsg.svcAsgTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcAsgTechCd, pMsg.svcAsgTechCd);
            ZYPEZDItemValueSetter.setValue(tMsg.futSvcDt, pMsg.futSvcDt);
            ZYPEZDItemValueSetter.setValue(tMsg.futSvcTm, pMsg.futSvcTm);
//NA#ASCC CLICK BugFix ADD End

        } else if (PROCESS_MODE_ADD_TASK.equals(pMsg.xxModeCd.getValue())) {

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, newFsrVisitNum);

            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitStsCd, pMsg.fsrVisitStsCd);
            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.bypsPrfTechFlg.getValue())) {

                ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(tMsg.techSchdFromDt, pMsg.techSchdFromDt);
            ZYPEZDItemValueSetter.setValue(tMsg.techSchdFromTm, pMsg.techSchdFromTm);
            ZYPEZDItemValueSetter.setValue(tMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
            if (ZYPCommonFunc.hasValue(pMsg.schdDisptEmlFlg)) {
                ZYPEZDItemValueSetter.setValue(tMsg.schdDisptEmlFlg, pMsg.schdDisptEmlFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitLtstFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.cpltByTelFixFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskNumList.no(0).svcTaskNum);

            ZYPEZDItemValueSetter.setValue(tMsg.svcAsgTpCd, pMsg.svcAsgTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcAsgTechCd, pMsg.svcAsgTechCd);
            ZYPEZDItemValueSetter.setValue(tMsg.futSvcDt, pMsg.futSvcDt);
            ZYPEZDItemValueSetter.setValue(tMsg.futSvcTm, pMsg.futSvcTm);
        }

        S21ApiTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0298E);
            return false;
        }
        return true;
    }

    private String getLatestFsrVisitNum(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("fsrNum", pMsg.fsrNum.getValue());
        String result = (String) ssmBatchClient.queryObject("getLatestFsrVisitNum", param);
        BigDecimal latestFsrVisitNum = new BigDecimal(result);
        latestFsrVisitNum = latestFsrVisitNum.add(BigDecimal.ONE);

        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, result);
        // START 2018/01/09 U.Kim [QC#19702, MOD]
        try {
            tMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
        } catch (EZDDBRecordLockedException e) {
            tMsg = getFsrVisitForUpdateWait(tMsg);
        }
        // END 2018/01/09 U.Kim [QC#19702, MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitLtstFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0298E);
            return null;
        }

        return String.format("%02d", latestFsrVisitNum.intValue());
    }

    private boolean completeFsrVisitRecord(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap, FSR_VISITTMsg previousFsrVisitMsg) {
        ZYPEZDItemValueSetter.setValue(previousFsrVisitMsg.svcChrgContFlg, pMsg.svcChrgContFlg);
        ZYPEZDItemValueSetter.setValue(previousFsrVisitMsg.fsrVisitLtstFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.update(previousFsrVisitMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(previousFsrVisitMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0299E);
            return false;
        }
        return true;
    }

    private boolean createFsrEventRecord(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {
        return createFsrEventRecord(pMsg, msgMap, null);
    }

    private boolean createFsrEventRecord(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap, String svcDisptEventCd) {

        String currentTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        // START 2018/08/09 K.Kojima [QC#27399,ADD]
        String mblIntfcFlg = null;
        // END 2018/08/09 K.Kojima [QC#27399,ADD]
        String mblIntfcProcCd = null;
        if (PROCESS_MODE_CONTINUOUS_CALL.equals(pMsg.xxModeCd.getValue())) {
            svcDisptEventCd = SVC_DISPT_EVENT.CONTINUOUS_FSR_CREATE;

        } else {
            if (ZYPCommonFunc.hasValue(svcDisptEventCd)) {
                // do nothing
            } else if (PROCESS_MODE_FOLLOWUP_TASK.equals(pMsg.xxModeCd.getValue())) {

                svcDisptEventCd = SVC_DISPT_EVENT.CREATE_FOLLOW_UP_TASK;

            } else if (PROCESS_MODE_ADD_TASK.equals(pMsg.xxModeCd.getValue())) {

                svcDisptEventCd = SVC_DISPT_EVENT.ADD_TASK;

            } else if (PROCESS_MODE_CALLAVOID_DISPTTECH.equals(pMsg.xxModeCd.getValue())) {

                svcDisptEventCd = SVC_DISPT_EVENT.AD_NEED_TECHNICIAN;
            }

            String techCd = pMsg.techCd.getValue();

            if (ZYPCommonFunc.hasValue(pMsg.svcAsgTpCd) && ZYPCommonFunc.hasValue(pMsg.svcAsgTechCd)) {

                    if (SVC_ASG_TP.PREFERRED.equals(pMsg.svcAsgTpCd.getValue())
                            || SVC_ASG_TP.REQUIRED.equals(pMsg.svcAsgTpCd.getValue())) {

                        techCd = pMsg.svcAsgTechCd.getValue();
                    }
            }

            // START 2018/08/09 K.Kojima [QC#27399,DEL]
            // mblIntfcProcCd = MBL_INTFC_PROC.NOT_PROCESSED;
            // if (ZYPCommonFunc.hasValue(techCd)) {
            // 
            //     S21_PSNTMsg srchS21PsnTMsg = new S21_PSNTMsg();
            //     srchS21PsnTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            //     srchS21PsnTMsg.psnCd.setValue(techCd);
            // 
            //     S21_PSNTMsg s21PsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(srchS21PsnTMsg);
            //     if (s21PsnTMsg == null) {
            // 
            //         msgMap.addXxMsgId(NSZM0602E);
            //         return false;
            // 
            //     } else if (ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {
            // 
            //         msgMap.addXxMsgId(NSZM0603E);
            //         return false;
            //     }
            // 
            //     if (!PSN_TP.EMPLOYEE.equals(s21PsnTMsg.psnTpCd.getValue())) {
            // 
            //         mblIntfcProcCd = MBL_INTFC_PROC.NO_NEED;
            //     }
            // }
            // 
            // SVC_TASK_STSTMsg srchSvcTaskStsTMsg = new SVC_TASK_STSTMsg();
            // srchSvcTaskStsTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            // srchSvcTaskStsTMsg.svcTaskStsCd.setValue(pMsg.fsrVisitStsCd.getValue());
            // 
            // SVC_TASK_STSTMsg svcTaskStsTMsg = (SVC_TASK_STSTMsg) S21ApiTBLAccessor.findByKey(srchSvcTaskStsTMsg);
            // if (svcTaskStsTMsg == null) {
            // 
            //     msgMap.addXxMsgId(NSZM0728E);
            //     return false;
            // }
            // 
            // if (ZYPConstant.FLG_OFF_N.equals(svcTaskStsTMsg.xtrnlSendReqFlg.getValue())) {
            // 
            //     mblIntfcProcCd = MBL_INTFC_PROC.NO_NEED;
            // }
            // END 2018/08/09 K.Kojima [QC#27399,DEL]

            // START 2018/08/09 K.Kojima [QC#27399,ADD]
            if (ZYPCommonFunc.hasValue(pMsg.mblIntfcFlg)) {
                mblIntfcFlg = pMsg.mblIntfcFlg.getValue();
            } else {
                mblIntfcFlg = ZYPConstant.FLG_ON_Y;
            }
            NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
            mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            mblIntfcInfoBean.setMblIntfcFlg(mblIntfcFlg);
            mblIntfcInfoBean.setSvcDisptEventCd(svcDisptEventCd);
            mblIntfcInfoBean.setSvcTaskStsCd(pMsg.fsrVisitStsCd.getValue());
            mblIntfcInfoBean.setTechCd(techCd);
            NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
            mblIntfcProcCd = mblIntfcInfoBean.getMblIntfcProcCd();
            // END 2018/08/09 K.Kojima [QC#27399,ADD]
        }

        for (int i = 0; i < pMsg.xxSvcTaskNumList.getValidCount(); i++) {
            BigDecimal fsrEventPk = ZYPOracleSeqAccessor.getNumberBigDecimal("FSR_EVENT_SQ");

            FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrEventPk, fsrEventPk);
            ZYPEZDItemValueSetter.setValue(tMsg.svcDisptEventCd, svcDisptEventCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskNumList.no(i).svcTaskNum.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);
            // START 2017/09/04 K.Kitachi [QC#20053, MOD]
//            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, pMsg.fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, getVisitNum(pMsg, pMsg.xxSvcTaskNumList.no(i).svcTaskNum.getValue()));
            // END 2017/09/04 K.Kitachi [QC#20053, MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrEventExeTs, currentTime);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrEventExeUsrId, pMsg.fsrEventExeUsrId);
            ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcProcCd, mblIntfcProcCd);
            // START 2018/08/09 K.Kojima [QC#27399,MOD]
            // ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcFlg, mblIntfcFlg);
            // END 2018/08/09 K.Kojima [QC#27399,MOD]

            S21ApiTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0300E);
                return false;
            }
        }
        return true;
    }

    private void updateSvcTaskRecord(NSZC004001PMsg pMsg, S21ApiMessageMap msgMap) {

        for (int i = 0; i < pMsg.xxSvcTaskNumList.getValidCount(); i++) {

            SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskNumList.no(i).svcTaskNum);

            // START 2018/01/09 U.Kim [QC#19702, MOD]
            // svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcTaskTMsg);
            try {
                tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
            } catch (EZDDBRecordLockedException e) {
                tMsg = getSvcTaskForUpdateWait(tMsg);
            }
            // END 2018/01/09 U.Kim [QC#19702, MOD]

            if (tMsg == null) {
                msgMap.addXxMsgId(NSZM0302E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);

            S21ApiTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0167E);
                return;
            }
        }

    }

    private static boolean find(String str, String... in) {
        for (String value : in) {
            if (value.equals(str)) {
                return true;
            }
        }
        return false;
    }

    // START 2017/09/04 K.Kitachi [QC#20053, ADD]
    private String getVisitNum(NSZC004001PMsg pMsg, String svcTaskNum) {
        FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", pMsg.fsrNum.getValue());
        inMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        FSR_VISITTMsgArray outMsgArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() > 0) {
            return outMsgArray.no(0).fsrVisitNum.getValue();
        }
        return pMsg.fsrVisitNum.getValue();
    }
    // END 2017/09/04 K.Kitachi [QC#20053, ADD]
    // START 2018/01/09 U.Kim [QC#19702, ADD]
    private void setWaitSecUpdTaskOther(String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return;
        }
        BigDecimal numConst = ZYPCodeDataUtil.getNumConstValue(WAIT_SEC_UPD_TASK_OTHER, glblCmpyCd);
        if (numConst != null) {
            this.waitSecUpdTaskOther = numConst.intValue();
        }
    }

    private SVC_TASKTMsg getSvcTaskForUpdateWait(SVC_TASKTMsg svcTaskTMsg){
        try {
            return (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTaskTMsg, this.waitSecUpdTaskOther);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }

    private FSR_VISITTMsg getFsrVisitForUpdateWait(FSR_VISITTMsg fsrVisitTMsg){
        try {
            return (FSR_VISITTMsg) EZDTBLAccessor.findByKeyForUpdateWait(fsrVisitTMsg, this.waitSecUpdTaskOther);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }
    // END 2018/01/09 U.Kim [QC#19702, ADD]
}
