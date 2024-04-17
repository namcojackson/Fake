/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC012001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_VISITTMsg;
import business.parts.NSZC012001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC012001.constant.NSZC012001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * Technician Arrived Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/20   Fujitsu         M.Nakamura      Create          N/A
 * 2014/03/13   Fujitsu         T.Yoshida       Update          Defect#127
 * 08/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 02/22/2016   Hitachi         T.Iwamoto       Update          QC#4283
 * 07/01/2016   Hitachi         T.Iwamoto       Update          QC#11185
 * 01/15/2018   Hitachi         U.Kim           Update          QC#19702
 * 01/24/2018   Hitachi         T.Tomita        Update          QC#22053
 * 2020/06/01   Hitachi         K.Kitachi       Update          QC#56271
 * </pre>
 */
public class NSZC012001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    /** Waiting Second for find by key*/
    private int waitSecUpdTaskOther;
    // END 2018/01/15 U.Kim [QC#19702, ADD]

    /** ONBATCH_TYPE */
    // private ONBATCH_TYPE onBatchType = null;
    /**
     * Constructor
     */
    public NSZC012001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Technician Arrived Update API.
     * @param pMsg NSZC012001PMsg
     * @param type ONBATCH_TYPE
     */
    public void execute(NSZC012001PMsg pMsg, ONBATCH_TYPE type) {
        // this.onBatchType = type;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        // START 2018/01/15 U.Kim [QC#19702, ADD]
        setWaitSecUpdTaskOther(pMsg.glblCmpyCd.getValue());
        // END 2018/01/15 U.Kim [QC#19702, ADD]
        main(msgMap, pMsg);
        msgMap.flush();
    }

    /**
     * main Method.
     * @param pMsg API Parametor
     * @param msgMap S21ApiMessageMap
     */
    private void main(S21ApiMessageMap msgMap, NSZC012001PMsg pMsg) {

        if (!checkInputParam(pMsg, msgMap)) {
            return;
        }

        if (!executeProcess(pMsg, msgMap)) {
            return;
        }

    }

    private boolean checkInputParam(NSZC012001PMsg pMsg, S21ApiMessageMap msgMap) {

        boolean isError = false;
        // **************************************************************
        // Mandatory Parameter Check
        // **************************************************************
        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZC012001Constant.NSZM0001E);
            isError = true;
        }
        if (!hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NSZC012001Constant.NSZM0002E);
            isError = true;
        }
        if (!hasValue(pMsg.xxModeCd)) {
            msgMap.addXxMsgId(NSZC012001Constant.NSZM0003E);
            isError = true;
        } else {
            if (!NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue()) && !NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0003E);
                isError = true;
            }
        }

        String mapKey = "";
        String mapValue = "";
        Map<String, String> fsrDtTmMap = new HashMap<String, String>();
        for (int i = 0; i < pMsg.FSRVisitList.getValidCount(); i++) {
            if (!hasValue(pMsg.FSRVisitList.no(i).fsrNum)) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0124E);
                isError = true;
            }
            if (!hasValue(pMsg.FSRVisitList.no(i).fsrVisitNum)) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0125E);
                isError = true;
            }
            if (!hasValue(pMsg.FSRVisitList.no(i).svcTaskNum)) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0156E);
                isError = true;
            }
            if (!hasValue(pMsg.FSRVisitList.no(i).userId)) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0163E);
                isError = true;
            }

            if (!isError) {
                mapKey = pMsg.FSRVisitList.no(i).fsrNum.getValue() + pMsg.FSRVisitList.no(i).fsrVisitNum.getValue();
            }
            if (NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue())) {
                if (!hasValue(pMsg.FSRVisitList.no(i).fsrVisitEtaDt)) {
                    msgMap.addXxMsgId(NSZC012001Constant.NSZM0424E);
                    isError = true;
                }
                if (!hasValue(pMsg.FSRVisitList.no(i).fsrVisitEtaTm)) {
                    msgMap.addXxMsgId(NSZC012001Constant.NSZM0425E);
                    isError = true;
                }
                mapValue = pMsg.FSRVisitList.no(i).fsrVisitEtaDt.getValue() + pMsg.FSRVisitList.no(i).fsrVisitEtaTm.getValue();
            } else if (NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
                if (!hasValue(pMsg.FSRVisitList.no(i).fsrVisitArvDt)) {
                    msgMap.addXxMsgId(NSZC012001Constant.NSZM0426E);
                    isError = true;
                }
                if (!hasValue(pMsg.FSRVisitList.no(i).fsrVisitArvTm)) {
                    msgMap.addXxMsgId(NSZC012001Constant.NSZM0427E);
                    isError = true;
                }
                mapValue = pMsg.FSRVisitList.no(i).fsrVisitArvDt.getValue() + pMsg.FSRVisitList.no(i).fsrVisitArvTm.getValue();
            }
            if (!isError) {
                if (fsrDtTmMap.containsKey(mapKey)) {
                    if (!mapValue.equals(fsrDtTmMap.get(mapKey))) {
                        if (NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue())) {
                            msgMap.addXxMsgId(NSZC012001Constant.NSZM0453E);
                            isError = true;
                        } else if (NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
                            msgMap.addXxMsgId(NSZC012001Constant.NSZM0454E);
                            isError = true;
                        }
                    }
                } else {
                    fsrDtTmMap.put(mapKey, mapValue);
                }
            }
            if (isError) {
                return false;
            }
        }

        if (isError) {
            return false;
        }

        return true;
    }

    private boolean executeProcess(NSZC012001PMsg pMsg, S21ApiMessageMap msgMap) {

        Map<String, Map<String, Object>> fsrAccessMap = new HashMap<String, Map<String, Object>>();

        // Add Defect#127 Start 2014/03/13 T.Yoshida
        String sysTs = ZYPDateUtil.getCurrentSystemTime(NSZC012001Constant.FORMAT_SYS_TS);
        // Add Defect#127 End 2014/03/13 T.Yoshida

        for (int i = 0; i < pMsg.FSRVisitList.getValidCount(); i++) {
            // String machDownFlg = ZYPConstant.FLG_OFF_N;

            Map<String, Object> fsrVisitMap = new HashMap<String, Object>();
            // Get FSR_VISIT
            if (fsrAccessMap.containsKey(pMsg.FSRVisitList.no(i).fsrNum.getValue())) {
                fsrVisitMap = fsrAccessMap.get(pMsg.FSRVisitList.no(i).fsrNum.getValue());
            } else {
                fsrVisitMap = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.FSRVisitList.no(i).fsrNum.getValue(), pMsg.FSRVisitList.no(i).fsrVisitNum.getValue());

                if (fsrVisitMap == null || fsrVisitMap.isEmpty()) {
                    msgMap.addXxMsgId(NSZC012001Constant.NSZM0295E);
                    return false;
                }

                fsrAccessMap.put(pMsg.FSRVisitList.no(i).fsrNum.getValue() + pMsg.FSRVisitList.no(i).fsrVisitNum.getValue(), fsrVisitMap);
            }

            if (NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals((String) fsrVisitMap.get("FSR_UPD_ENBL_FLG"))) {
                    continue;
                }
            // START 2020/06/01 K.Kitachi [QC#56271, DEL]
//            } else if (NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
//                // Mod Start 2018/01/24 QC#22053
////                if (!SVC_TASK_STS.DISPATCHED.equals((String) fsrVisitMap.get("FSR_VISIT_STS_CD")) || !ZYPConstant.FLG_ON_Y.equals((String) fsrVisitMap.get("FSR_VISIT_LTST_FLG"))) {
////                    continue;
////                }
//                if (!ZYPConstant.FLG_ON_Y.equals((String) fsrVisitMap.get("FSR_VISIT_LTST_FLG"))) {
//                    continue;
//                }
//                // Mod End 2018/01/24 QC#22053
            // END 2020/06/01 K.Kitachi [QC#56271, DEL]
            }

            // Get SVC_TASK
            List<Map<String, Object>> svcTaskList = getSvcTask(pMsg.glblCmpyCd.getValue(), pMsg.FSRVisitList.no(i).fsrNum.getValue(), pMsg.FSRVisitList.no(i).svcTaskNum.getValue());
            if (svcTaskList == null || svcTaskList.isEmpty()) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0302E);
                return false;
            }

            // Update FSR_VISIT
            FSR_VISITTMsg inFsrVisitTMsg = new FSR_VISITTMsg();
            inFsrVisitTMsg.setConditionValue("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            inFsrVisitTMsg.setConditionValue("fsrNum", pMsg.FSRVisitList.no(i).fsrNum.getValue());
            inFsrVisitTMsg.setConditionValue("fsrVisitNum", pMsg.FSRVisitList.no(i).fsrVisitNum.getValue());

            // START 2018/01/15 U.Kim [QC#19702, MOD]
            // FSR_VISITTMsg updFsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inFsrVisitTMsg);
            FSR_VISITTMsg updFsrVisitTMsg = null;
            try {
                updFsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inFsrVisitTMsg);
            } catch (EZDDBRecordLockedException e) {
                updFsrVisitTMsg = getFsrVisitForUpdateWait(inFsrVisitTMsg);
            }
            // END 2018/01/15 U.Kim [QC#19702, MOD]
            if (updFsrVisitTMsg == null) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0296E);
                return false;
            }

            if (NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.fsrVisitEtaDt, pMsg.FSRVisitList.no(i).fsrVisitEtaDt);
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.fsrVisitEtaTm, pMsg.FSRVisitList.no(i).fsrVisitEtaTm);
            } else if (NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
                // Mod Start 2018/01/24 QC#22053
                if (hasValue(updFsrVisitTMsg.fsrVisitDisptDt) && hasValue(updFsrVisitTMsg.fsrVisitDisptTm)) {
                    String disptTsStr = updFsrVisitTMsg.fsrVisitDisptDt.getValue() + updFsrVisitTMsg.fsrVisitDisptTm.getValue();
                    BigDecimal dispTsBd = new BigDecimal(disptTsStr);
                    if (dispTsBd.compareTo(new BigDecimal(pMsg.FSRVisitList.no(i).fsrVisitArvDt.getValue() + pMsg.FSRVisitList.no(i).fsrVisitArvTm.getValue())) > 0) {
                        msgMap.addXxMsgId(NSZC012001Constant.NSZM0444E);
                        return false;
                    }
                }
                // Mod End 2018/01/24 QC#22053
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.fsrVisitArvDt, pMsg.FSRVisitList.no(i).fsrVisitArvDt);
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.fsrVisitArvTm, pMsg.FSRVisitList.no(i).fsrVisitArvTm);
//NA#ASCC CLICK ADD Start
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.svcActlLttdNum, pMsg.FSRVisitList.no(i).svcActlLttdNum);
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.svcActlLgtdNum, pMsg.FSRVisitList.no(i).svcActlLgtdNum);
                ZYPEZDItemValueSetter.setValue(updFsrVisitTMsg.svcActlLocGapDstNum, pMsg.FSRVisitList.no(i).svcActlLocGapDstNum);
//NA#ASCC CLICK ADD End
            }
            S21ApiTBLAccessor.update(updFsrVisitTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updFsrVisitTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0299E);
                return false;
            }

            // Insert FSR_EVENT
            FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.FSR_EVENT_SQ));
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, pMsg.FSRVisitList.no(i).fsrNum);
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, pMsg.FSRVisitList.no(i).fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcTaskNum, pMsg.FSRVisitList.no(i).svcTaskNum);

            // Mod Defect#127 Start 2014/03/13 T.Yoshida
            if (NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_ETA);
            } else if (NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_VISIT);
            }
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, (String) fsrVisitMap.get("TECH_CD"));
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeTs, sysTs);
            // Mod Defect#127 End 2014/03/13 T.Yoshida

            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeUsrId, pMsg.FSRVisitList.no(i).userId);
            // mod start 2016/02/22 CSA Defect#4283, 2016/07/01 CSA Defect#11185
            NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
            mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            mblIntfcInfoBean.setMblIntfcFlg(pMsg.FSRVisitList.no(i).mblIntfcFlg.getValue());
            if (NSZC012001Constant.PROCESS_MODE_ETA.equals(pMsg.xxModeCd.getValue())) {
                mblIntfcInfoBean.setSvcDisptEventCd(SVC_DISPT_EVENT.UPDATE_ETA);
                mblIntfcInfoBean.setSvcTaskStsCd(SVC_TASK_STS.ASSIGNED);
            } else if (NSZC012001Constant.PROCESS_MODE_ARRIVED.equals(pMsg.xxModeCd.getValue())) {
                mblIntfcInfoBean.setSvcDisptEventCd(SVC_DISPT_EVENT.UPDATE_VISIT);
                mblIntfcInfoBean.setSvcTaskStsCd(SVC_TASK_STS.ARRIVED);
            }
            mblIntfcInfoBean.setTechCd((String) fsrVisitMap.get("TECH_CD"));
            NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);

            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
            // mod end 2016/02/22 CSA Defect#4283, 2016/07/01 CSA Defect#11185

            S21ApiTBLAccessor.insert(fsrEventTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrEventTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZC012001Constant.NSZM0083E);
                return false;
            }
        }

        return true;
    }

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    private void setWaitSecUpdTaskOther(String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return;
        }
        BigDecimal numConst = ZYPCodeDataUtil.getNumConstValue(NSZC012001Constant.WAIT_SEC_UPD_TASK_OTHER, glblCmpyCd);
        if (numConst != null) {
            this.waitSecUpdTaskOther = numConst.intValue();
        }
    }

    private FSR_VISITTMsg getFsrVisitForUpdateWait(FSR_VISITTMsg paramFsrTMsg) {
        try {
            return (FSR_VISITTMsg) EZDTBLAccessor.findByKeyForUpdateWait(paramFsrTMsg, this.waitSecUpdTaskOther);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }

    // END 2018/01/15 U.Kim [QC#19702, ADD]


    /**
     * getFsrVisit
     * @param glblCmpyCd String
     * @param fsrNum String
     * @param fsrVisitNum String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getFsrVisit(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("fsrNum", fsrNum);
        param.put("fsrVisitNum", fsrVisitNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFsrVisit", param);
    }

    /**
     * getSvcTask
     * @param glblCmpyCd String
     * @param fsrNum String
     * @param svcTaskNum String
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcTask(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("fsrNum", fsrNum);
        param.put("svcTaskNum", svcTaskNum);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcTask", param);
    }

}
