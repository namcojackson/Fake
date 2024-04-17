/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC058001;

import static com.canon.cusa.s21.api.NSZ.NSZC058001.constant.NSZC058001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.S21_PSNTMsg;
import business.db.SVC_SUPPL_TASKTMsg;
import business.db.SVC_SUPPL_TASK_TPTMsg;
import business.parts.NSZC058001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Supplemental Task Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/09   Hitachi         A.Kohinata      Create          N/A
 * 2015/12/10   Hitachi         T.Iwamoto       Update          QC#1796
 * 2015/12/11   Hitachi         T.Iwamoto       Update          QC#1806
 * 2016/05/11   Hitachi         T.Iwamoto       Update          QC#8109
 * 2016/11/02   Fujitsu         S.Nakai         Update          QC#15754
 * 2016/12/13   Hitachi         T.Mizuki        Update          QC#16288
 * 2018/06/15   Fujitsu         M.Ohno          Update          QC#25689
 *</pre>
 */
public class NSZC058001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Service Interrupt Flag */
    private String svcItrptFlg = null;

    /** Technician Code */
    private String techCd = null;

    /** FSR Number */
    private String fsrNum = null;

    /** HasValue FromDtTm */
    private boolean hasFromDtTm = false;

    /** HasValue ToDtTm */
    private boolean hasToDtTm = false;

    /**
     * Constructor
     */
    public NSZC058001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC058001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC058001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC058001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute Supplemental Task Update API.
     * @param param NSZC058001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC058001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        ZYPTableUtil.clear(param.xxMsgIdList);

        if (!checkMandatory(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkRelation(msgMap)) {
            msgMap.flush();
            return;
        }
        // del start 2016/12/13 CSA QC#16288
        if (!checkTaskRelation(msgMap)) {
//            msgMap.flush();
//            return;
        }
        // del end 2016/12/13 CSA QC#16288
        updateSvcSupplTask(msgMap);
        msgMap.flush();
    }

    /**
     * Check Mandatory.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkMandatory(S21ApiMessageMap msgMap) {
        NSZC058001PMsg pMsg = (NSZC058001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxProcMd) || (!MODE_NEW.equals(pMsg.xxProcMd.getValue()) && !MODE_UPDATE.equals(pMsg.xxProcMd.getValue()))) {
            msgMap.addXxMsgId(NSZM0039E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcSupplTaskStsCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental Task Status Code" });
            return false;
        }
        return true;
    }

    /**
     * Check Relation.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkRelation(S21ApiMessageMap msgMap) {
        NSZC058001PMsg pMsg = (NSZC058001PMsg) msgMap.getPmsg();

        if (MODE_NEW.equals(pMsg.xxProcMd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcSupplTaskTpCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental Task Type Code" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.techCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Technician Code" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcSupplFromDt)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental From Date" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcSupplFromTm)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental From Time" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.cratPsnCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Creation User ID" });
                return false;
            }
        }

        if (MODE_UPDATE.equals(pMsg.xxProcMd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcSupplTaskNum)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental Task#" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.updPsnCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Update User ID" });
                return false;
            }
        }

        if (SVC_TASK_STS.CLOSED.equals(pMsg.svcSupplTaskStsCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcSupplToDt)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental Thru Date" });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcSupplToTm)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Supplemental Thru Time" });
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcSupplFromDt) && ZYPCommonFunc.hasValue(pMsg.svcSupplFromTm)) {
            hasFromDtTm = true;
        }
        if (ZYPCommonFunc.hasValue(pMsg.svcSupplToDt) && ZYPCommonFunc.hasValue(pMsg.svcSupplToTm)) {
            hasToDtTm = true;
        }
        if (hasFromDtTm && hasToDtTm) {
            if (pMsg.svcSupplToDt.getValue().compareTo(pMsg.svcSupplFromDt.getValue()) < 0) {
                msgMap.addXxMsgIdWithPrm(NSZM0620E, new String[] {"Supplemental From/Thru Date" });
                return false;
            }
            if ((pMsg.svcSupplToDt.getValue().compareTo(pMsg.svcSupplFromDt.getValue()) == 0) && (pMsg.svcSupplToTm.getValue().compareTo(pMsg.svcSupplFromTm.getValue()) < 0)) {
                msgMap.addXxMsgIdWithPrm(NSZM0620E, new String[] {"Supplemental From/Thru Time" });
                return false;
            }
        }

        // Get Technician Code, Service Interrupt Flag
        if (MODE_UPDATE.equals(pMsg.xxProcMd.getValue())) {
            // Get SVC_SUPPL_TASK
            SVC_SUPPL_TASKTMsg svcSupplTaskTMsg = getSvcSupplTask(pMsg.glblCmpyCd.getValue(), pMsg.svcSupplTaskNum.getValue());
            if (svcSupplTaskTMsg == null || !ZYPCommonFunc.hasValue(svcSupplTaskTMsg.techCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0610E, new String[] {"SVC_SUPPL_TASK" });
                return false;
            }
            this.techCd = (String) svcSupplTaskTMsg.techCd.getValue();
            if (ZYPCommonFunc.hasValue(svcSupplTaskTMsg.svcSupplTaskTpCd)) {
                this.svcItrptFlg = getSvcItrptFlg(pMsg.glblCmpyCd.getValue(), (String) svcSupplTaskTMsg.svcSupplTaskTpCd.getValue());
            }
        } else {
            this.techCd = pMsg.techCd.getValue();
            this.svcItrptFlg = getSvcItrptFlg(pMsg.glblCmpyCd.getValue(), pMsg.svcSupplTaskTpCd.getValue());
        }

        if (MODE_NEW.equals(pMsg.xxProcMd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(svcItrptFlg) && !ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
                msgMap.addXxMsgIdWithPrm(NSZM0609E, new String[] {"Souce Task#" });
                return false;
            }
        }
        return true;
    }

// del start 2016/12/13 CSA QC#16288
    /**
     * Check Task Relation.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkTaskRelation(S21ApiMessageMap msgMap) {
        NSZC058001PMsg pMsg = (NSZC058001PMsg) msgMap.getPmsg();
//
//        String fromDtTm = null;
//        String toDtTm = null;
//        if (hasFromDtTm && hasToDtTm) {
//            fromDtTm = pMsg.svcSupplFromDt.getValue().concat(pMsg.svcSupplFromTm.getValue());
//            toDtTm = pMsg.svcSupplToDt.getValue().concat(pMsg.svcSupplToTm.getValue());
//        }
//
//        // Common Check
//        if (hasFromDtTm && hasToDtTm) {
//            if (existsDuplPerSupplTask(pMsg, fromDtTm, toDtTm)) {
//                msgMap.addXxMsgIdWithPrm(NSZM0611E, new String[] {"supplemental", fromDtTm, toDtTm });
//                return false;
//            }
//        }
//        // [QC#1806,MOD]START
//        if (!SVC_TASK_STS.CLOSED.equals(pMsg.svcSupplTaskStsCd.getValue())) {
//            String svcSupplTaskNum = getActSupplTask(pMsg);
//            if (ZYPCommonFunc.hasValue(svcSupplTaskNum)) {
////                if (!ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
//                    msgMap.addXxMsgIdWithPrm(NSZM0612E, new String[] {"supplemental", svcSupplTaskNum });
//                    return false;
////                }
//            }
//        }
//        // [QC#1806,MOD]END
//
//        // Supplemental Task Check
//        if (ZYPConstant.FLG_OFF_N.equals(this.svcItrptFlg)) {
//            // [QC#1806,MOD]START
//            if (!SVC_TASK_STS.CLOSED.equals(pMsg.svcSupplTaskStsCd.getValue())) {
//                String svcTaskNum = getActSvcTask(pMsg);
//                if (ZYPCommonFunc.hasValue(svcTaskNum)) {
//                    if (!ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
//                        msgMap.addXxMsgIdWithPrm(NSZM0612E, new String[] {"service", svcTaskNum });
//                        return false;
//                    }
//                }
//            }
//            // [QC#1806,MOD]END
////QC#15754 Start
////            if (hasFromDtTm && hasToDtTm) {
////                if (existsDuplPerSvcTask(pMsg, fromDtTm, toDtTm)) {
////                    msgMap.addXxMsgIdWithPrm(NSZM0611E, new String[] {"service", fromDtTm, toDtTm });
////                    return false;
////                }
////            }
////QC#15754 End
//        }
//
        // Interrupt Task Check
        if (ZYPConstant.FLG_ON_Y.equals(this.svcItrptFlg)) {
            // [QC#1806,MOD]START
            if (!SVC_TASK_STS.CLOSED.equals(pMsg.svcSupplTaskStsCd.getValue())) {
                if (notExistsActSrcSvcTask(pMsg)) {
//                    msgMap.addXxMsgIdWithPrm(NSZM0613E, new String[] {pMsg.svcTaskNum.getValue() });
//                    return false;
                }
            }
            // [QC#1806,MOD]END
//
//            if (hasFromDtTm && hasToDtTm) {
//                if (notExistsDuplPerSrcSvcTask(pMsg, fromDtTm, toDtTm)) {
//                    // [QC#1796,MOD]START
//                    msgMap.addXxMsgIdWithPrm(NSZM0614E, new String[] {fromDtTm, toDtTm });
//                    // [QC#1796,MOD]END
//                    return false;
//                }
//            }
        }
        return true;
    }
// del end 2016/12/13 CSA QC#16288

    /**
     * Update SVC_SUPPL_TASK.
     * @param msgMap S21ApiMessageMap
     */
    private void updateSvcSupplTask(S21ApiMessageMap msgMap) {
        NSZC058001PMsg pMsg = (NSZC058001PMsg) msgMap.getPmsg();
        SVC_SUPPL_TASKTMsg svcSupplTaskTMsg = new SVC_SUPPL_TASKTMsg();

        // Get CurrentSystemTime
        String procDt = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        String procTm = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);

        // Get Human Resources Supervisor Id
        String hrSupvId = null;
        if (ZYPCommonFunc.hasValue(this.techCd)) {
            hrSupvId = getHrSupvId(pMsg.glblCmpyCd.getValue(), this.techCd);
        }

        if (MODE_NEW.equals(pMsg.xxProcMd.getValue())) {
            // Get Supplemental Time
            BigDecimal svcSupplTmNum = BigDecimal.ZERO;
            if (ZYPCommonFunc.hasValue(pMsg.svcSupplTmNum)) {
                svcSupplTmNum = pMsg.svcSupplTmNum.getValue();
            } else if (hasFromDtTm && hasToDtTm) {
                String fromDtTm = pMsg.svcSupplFromDt.getValue().concat(pMsg.svcSupplFromTm.getValue());
                String toDtTm = pMsg.svcSupplToDt.getValue().concat(pMsg.svcSupplToTm.getValue());
                svcSupplTmNum = getSvcSupplTmNum(fromDtTm, toDtTm);
                if (svcSupplTmNum == null) {
                    msgMap.addXxMsgId(NSZM0046E);
                    return;
                }
            }

            // Get svcSupplTaskNum
            String svcTaskNum = ZYPExtnNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), "SVC_TS_NUM");
            ZYPEZDItemValueSetter.setValue(pMsg.svcSupplTaskNum, svcTaskNum);

            // Insert
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplTaskNum, pMsg.svcSupplTaskNum);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplTaskTpCd, pMsg.svcSupplTaskTpCd);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplTaskStsCd, pMsg.svcSupplTaskStsCd);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.techCd, pMsg.techCd);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.techMgrCd, hrSupvId);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.fsrNum, this.fsrNum);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcTaskNum, pMsg.svcTaskNum);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplFromDt, pMsg.svcSupplFromDt);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplFromTm, pMsg.svcSupplFromTm);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplToDt, pMsg.svcSupplToDt);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplToTm, pMsg.svcSupplToTm);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplTmNum, svcSupplTmNum);
            if (ZYPCommonFunc.hasValue(pMsg.svcTrvlTmNum)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcTrvlTmNum, pMsg.svcTrvlTmNum);
            } else {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcTrvlTmNum, BigDecimal.ZERO);
            }
            if (ZYPCommonFunc.hasValue(pMsg.durnTmNum)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.durnTmNum, pMsg.durnTmNum);
            } else {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.durnTmNum, BigDecimal.ZERO);
            }
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.cratPsnCd, pMsg.cratPsnCd);
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updPsnCd, pMsg.cratPsnCd);
            if (ZYPCommonFunc.hasValue(pMsg.cratDt)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.cratDt, pMsg.cratDt);
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updDt, pMsg.cratDt);
            } else {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.cratDt, procDt);
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updDt, procDt);
            }
            if (ZYPCommonFunc.hasValue(pMsg.cratTm)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.cratTm, pMsg.cratTm);
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updTm, pMsg.cratTm);
            } else {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.cratTm, procTm);
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updTm, procTm);
            }
            S21ApiTBLAccessor.create(svcSupplTaskTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcSupplTaskTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0398E, new String[] {"SVC_SUPPL_TASK" });
            }
        } else {
            // Get SVC_SUPPL_TASK For Update
            svcSupplTaskTMsg = getSvcSupplTaskForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcSupplTaskNum.getValue());
            if (svcSupplTaskTMsg == null) {
                msgMap.addXxMsgIdWithPrm(NSZM0610E, new String[] {"SVC_SUPPL_TASK" });
                return;
            }

            // Get Supplemental Time
            BigDecimal svcSupplTmNum = null;
            if (ZYPCommonFunc.hasValue(pMsg.svcSupplTmNum)) {
                svcSupplTmNum = pMsg.svcSupplTmNum.getValue();
            } else if (hasFromDtTm && hasToDtTm) {
                String fromDtTm = pMsg.svcSupplFromDt.getValue().concat(pMsg.svcSupplFromTm.getValue());
                String toDtTm = pMsg.svcSupplToDt.getValue().concat(pMsg.svcSupplToTm.getValue());
                svcSupplTmNum = getSvcSupplTmNum(fromDtTm, toDtTm);
                if (svcSupplTmNum == null) {
                    msgMap.addXxMsgId(NSZM0046E);
                    return;
                }
            } else if (!hasFromDtTm && hasToDtTm) {
                String fromDtTm = svcSupplTaskTMsg.svcSupplFromDt.getValue().concat(svcSupplTaskTMsg.svcSupplFromTm.getValue());
                String toDtTm = pMsg.svcSupplToDt.getValue().concat(pMsg.svcSupplToTm.getValue());
                svcSupplTmNum = getSvcSupplTmNum(fromDtTm, toDtTm);
                if (svcSupplTmNum == null) {
                    msgMap.addXxMsgId(NSZM0046E);
                    return;
                }
            } else {
                svcSupplTmNum = svcSupplTaskTMsg.svcSupplTmNum.getValue();
            }

            // Update
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplTaskStsCd, pMsg.svcSupplTaskStsCd);
            if (ZYPCommonFunc.hasValue(pMsg.svcSupplFromDt)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplFromDt, pMsg.svcSupplFromDt);
            }
            if (ZYPCommonFunc.hasValue(pMsg.svcSupplFromTm)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplFromTm, pMsg.svcSupplFromTm);
            }
            if (ZYPCommonFunc.hasValue(pMsg.svcSupplToDt)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplToDt, pMsg.svcSupplToDt);
            }
            if (ZYPCommonFunc.hasValue(pMsg.svcSupplToTm)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplToTm, pMsg.svcSupplToTm);
            }
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcSupplTmNum, svcSupplTmNum);
            if (ZYPCommonFunc.hasValue(pMsg.svcTrvlTmNum)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.svcTrvlTmNum, pMsg.svcTrvlTmNum);
            }
            if (ZYPCommonFunc.hasValue(pMsg.durnTmNum)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.durnTmNum, pMsg.durnTmNum);
            }
            ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updPsnCd, pMsg.updPsnCd);
            if (ZYPCommonFunc.hasValue(pMsg.updDt)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updDt, pMsg.updDt);
            } else {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updDt, procDt);
            }
            if (ZYPCommonFunc.hasValue(pMsg.updTm)) {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updTm, pMsg.updTm);
            } else {
                ZYPEZDItemValueSetter.setValue(svcSupplTaskTMsg.updTm, procTm);
            }
            S21ApiTBLAccessor.update(svcSupplTaskTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcSupplTaskTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0399E, new String[] {"SVC_SUPPL_TASK", "Supplemental Task# : " + pMsg.svcSupplTaskNum.getValue() });
            }
        }
    }
// del start 2016/12/13 CSA QC#16288
//    /**
//     * existsDuplPerSupplTask
//     * @param pMsg NSZC058001PMsg
//     * @return true:Exists, false:Not Exists
//     */
//    private boolean existsDuplPerSupplTask(NSZC058001PMsg pMsg, String fromDtTm, String toDtTm) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("techCd", this.techCd);
//        if (ZYPCommonFunc.hasValue(pMsg.svcSupplTaskNum)) {
//            param.put("svcSupplTaskNum", pMsg.svcSupplTaskNum.getValue());
//        }
//        param.put("fromDtTm", fromDtTm);
//        param.put("toDtTm", toDtTm);
//        List<Map<String, Object>> rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplPerSupplTask", param);
//        if (rsList == null || rsList.size() == 0) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * getActSupplTask
//     * @param pMsg NSZC058001PMsg
//     * @return String
//     */
//    private String getActSupplTask(NSZC058001PMsg pMsg) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("techCd", this.techCd);
//        if (ZYPCommonFunc.hasValue(pMsg.svcSupplTaskNum)) {
//            param.put("svcSupplTaskNum", pMsg.svcSupplTaskNum.getValue());
//        }
//        param.put("svcSupplTaskStsOpen", SVC_TASK_STS.OPEN);
//        List<Map<String, Object>> rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getActSupplTask", param);
//        if (rsList == null || rsList.size() == 0) {
//            return null;
//        }
//        return (String) rsList.get(0).get("SVC_SUPPL_TASK_NUM");
//    }
//
//    /**
//     * getActSvcTask
//     * @param pMsg NSZC058001PMsg
//     * @return String
//     */
//    private String getActSvcTask(NSZC058001PMsg pMsg) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("techCd", this.techCd);
//        param.put("fsrVisitStsList", new String[] {SVC_TASK_STS.IN_ROUTE, SVC_TASK_STS.ARRIVED });
//        List<Map<String, Object>> rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getActSvcTask", param);
//        if (rsList == null || rsList.size() == 0) {
//            return null;
//        }
//        return (String) rsList.get(0).get("SVC_TASK_NUM");
//    }
//
//    /**
//     * existsDuplPerSvcTask
//     * @param pMsg NSZC058001PMsg
//     * @return true:Exists, false:Not Exists
//     */
//    private boolean existsDuplPerSvcTask(NSZC058001PMsg pMsg, String fromDtTm, String toDtTm) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("techCd", this.techCd);
//        param.put("fromDtTm", fromDtTm);
//        param.put("toDtTm", toDtTm);
//        List<Map<String, Object>> rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplPerSvcTask", param);
//        if (rsList == null || rsList.size() == 0) {
//            return false;
//        }
//        return true;
//    }
//
    /**
     * notExistsActSrcSvcTask
     * @param pMsg NSZC058001PMsg
     * @return true:Not Exists, false:Exists
     */
    private boolean notExistsActSrcSvcTask(NSZC058001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        param.put("fsrVisitStsCdArrived", SVC_TASK_STS.ARRIVED);
        List<Map<String, Object>> rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getActSrcSvcTask", param);
        if (rsList == null || rsList.size() == 0) {
            return true;
        } else {
            this.fsrNum = (String) rsList.get(0).get("FSR_NUM");
        }
        return false;
    }
//
//    /**
//     * notExistsDuplPerSrcSvcTask
//     * @param pMsg NSZC058001PMsg
//     * @return true:Not Exists, false:Exists
//     */
//    private boolean notExistsDuplPerSrcSvcTask(NSZC058001PMsg pMsg, String fromDtTm, String toDtTm) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());
//        param.put("fromDtTm", fromDtTm);
//        param.put("toDtTm", toDtTm);
//        // mod Start 2016/05/11 CSA Defect#8109
//        param.put("maxDt", MAX_DT);
//        param.put("maxTm", MAX_TM);
//        // mod end 2016/05/11 CSA Defect#8109
//        List<Map<String, Object>> rsList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplPerSrcSvcTask", param);
//        if (rsList == null || rsList.size() == 0) {
//            return true;
//        }
//        return false;
//    }
// del end 2016/12/13 CSA QC#16288
    /**
     * Get Service Interrupt Flag
     * @param glblCmpyCd
     * @param svcSupplTaskTpCd
     * @return String
     */
    private static String getSvcItrptFlg(String glblCmpyCd, String svcSupplTaskTpCd) {
        SVC_SUPPL_TASK_TPTMsg svcSupplTaskTpTMsg = new SVC_SUPPL_TASK_TPTMsg();
        setValue(svcSupplTaskTpTMsg.glblCmpyCd, glblCmpyCd);
        setValue(svcSupplTaskTpTMsg.svcSupplTaskTpCd, svcSupplTaskTpCd);
        svcSupplTaskTpTMsg = (SVC_SUPPL_TASK_TPTMsg) S21CodeTableAccessor.findByKey(svcSupplTaskTpTMsg);
        if (svcSupplTaskTpTMsg == null) {
            return null;
        }
        return (String) svcSupplTaskTpTMsg.svcItrptFlg.getValue();
    }

    /**
     * Get Human Resources Supervisor Id
     * @param glblCmpyCd
     * @param techCd
     * @return String
     */
    private static String getHrSupvId(String glblCmpyCd, String techCd) {
        S21_PSNTMsg s21PsnTmsg = new S21_PSNTMsg();
        setValue(s21PsnTmsg.glblCmpyCd, glblCmpyCd);
        setValue(s21PsnTmsg.psnCd, techCd);
        s21PsnTmsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(s21PsnTmsg);
        if (s21PsnTmsg == null) {
            return null;
        }
        return (String) s21PsnTmsg.hrSupvId.getValue();
    }

    /**
     * Get SVC_SUPPL_TASK
     * @param glblCmpyCd
     * @param svcSupplTaskNum
     * @return SVC_SUPPL_TASKTMsg
     */
    private static SVC_SUPPL_TASKTMsg getSvcSupplTask(String glblCmpyCd, String svcSupplTaskNum) {
        SVC_SUPPL_TASKTMsg svcSupplTaskTMsg = new SVC_SUPPL_TASKTMsg();
        setValue(svcSupplTaskTMsg.glblCmpyCd, glblCmpyCd);
        setValue(svcSupplTaskTMsg.svcSupplTaskNum, svcSupplTaskNum);
        return (SVC_SUPPL_TASKTMsg) S21ApiTBLAccessor.findByKey(svcSupplTaskTMsg);
    }

    /**
     * Get SVC_SUPPL_TASK For Update
     * @param glblCmpyCd
     * @param svcSupplTaskNum
     * @return SVC_SUPPL_TASKTMsg
     */
    private static SVC_SUPPL_TASKTMsg getSvcSupplTaskForUpdate(String glblCmpyCd, String svcSupplTaskNum) {
        SVC_SUPPL_TASKTMsg svcSupplTaskTMsg = new SVC_SUPPL_TASKTMsg();
        setValue(svcSupplTaskTMsg.glblCmpyCd, glblCmpyCd);
        setValue(svcSupplTaskTMsg.svcSupplTaskNum, svcSupplTaskNum);
        return (SVC_SUPPL_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcSupplTaskTMsg);
    }

    /**
     * Get Supplemental Time
     * @param strFromDtTm
     * @param strToDtTm
     * @return BigDecimal
     */
    private static BigDecimal getSvcSupplTmNum(String strFromDtTm, String strToDtTm) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date fromDtTm = null;
        Date toDtTm = null;
        try {
            fromDtTm = sdf.parse(strFromDtTm);
            toDtTm = sdf.parse(strToDtTm);
        } catch (ParseException e) {
            return null;
        }
        long timeDiff = (toDtTm.getTime() - fromDtTm.getTime()) / (1000 * 60);

        // 2018/06/05 QC#25689 add start
        if (timeDiff > MAX_TM_NUM) {
            timeDiff = MAX_TM_NUM;
        }
        // 2018/06/05 QC#25689 add end

        return BigDecimal.valueOf(timeDiff);
    }
}
