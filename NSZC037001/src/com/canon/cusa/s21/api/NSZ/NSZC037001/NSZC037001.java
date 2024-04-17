/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC037001;

import static com.canon.cusa.s21.api.NSZ.NSZC037001.constant.NSZC037001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.common.EZDPItem;
import parts.common.EZDPMsgArray;
import parts.common.EZDValidatorException;

import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.FSRTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.db.SVC_TASK_HLDTMsg;
import business.db.SVC_TASK_HLDTMsgArray;
import business.parts.NSZC037001PMsg;
import business.parts.NSZC037001_APMsg;
import business.parts.NSZC061001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC037002.NSZC037002;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.constant.NSZC061001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;

/**
 * <pre>
 * Service Credit Check API ( <_ FSR Update API ).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/09/2015   Hitachi         Y.Tsuchimoto    Create          NA
 * 03/07/2016   Hitachi         T.Iwamoto       Update          CSA QC#2697
 * 03/22/2016   Hitachi         T.Tomita        Update          CSA QC#895
 * 04/13/2016   Hitachi         A.Kohinata      Update          CSA QC#6866
 * 05/09/2016   Hitachi         T.Tomita        Update          CSA QC#895
 * 05/23/2018   Fujitsu         S.Ohki          Update          CSA QC#26177
 * 06/04/2018   Fujitsu         S.Ohki          Update          CSA QC#26177-2
 * 07/31/2018   CITS            M.Naito         Update          CSA QC#27463
 * 2018/08/30   Hitachi         K.Kitachi       Update          CSA QC#22665
 * 2018/10/30   Hitachi         K.Kitachi       Update          CSA QC#28879
 * 2021/08/03   CITS            L.Mandanas      Update          CSA QC#59066
 * 2022/01/27   Hitachi         R.Onozuka       Update          CSA QC#56182
 * 2022/07/20   CITS            L.Mandanas      Update          CSA QC#60316
 * 2022/12/27   Hitachi         S.Fujita        Update          CSA QC#60744
 * 2023/06/07   Hitachi         S.Fujita        Update          CSA QC#60923
 * </pre>
 */
public class NSZC037001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Process Time Stamp
     */
    private String procTs = null;

    /**
     * Mail Time Stamp
     */
    private String mailTs = null;

    /**
     * Constructor
     */
    public NSZC037001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC037001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC037001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        // add start 2016/03/22 CSA Defect#895
        this.procTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        // add end 2016/03/22 CSA Defect#895
        // add start 2016/05/09 CSA Defect#895
        this.mailTs = ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm");
        // add end 2016/05/09 CSA Defect#895

        if (!checkParameter(msgMap)) {
            msgMap.flush();
            return;
        }

        if (!checkExistFSR(msgMap)) {
            msgMap.flush();
            return;
        }

        checkHold(msgMap);
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.slsDt, NSZM0002E);
        mandatoryCheck(msgMap, pMsg.usrId, NSZM0708E);
        mandatoryCheck(msgMap, pMsg.fsrNum, NSZM0124E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean checkExistFSR(S21ApiMessageMap msgMap) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();

        FSRTMsg fsrTMsg = getFSR(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg == null) {
            msgMap.addXxMsgId(NSZM0498E);
            return false;
        } else {
            if (!ZYPCommonFunc.hasValue(fsrTMsg.fsrStsCd) || (!SVC_TASK_STS.OPEN.equals(fsrTMsg.fsrStsCd.getValue())) && !SVC_TASK_STS.TBO.equals(fsrTMsg.fsrStsCd.getValue())) {
                msgMap.addXxMsgIdWithPrm(NSZM0709E, new String[] {MSG_PARAM_FSR, SVC_TASK_STS.OPEN + " OR " + SVC_TASK_STS.TBO });
                return false;
            }
        }

        return true;
    }

    private SVC_TASKTMsgArray getSvcTaskList(String glblCmpyCd, String fsrNum) {
        SVC_TASKTMsg param = new SVC_TASKTMsg();
        param.setSQLID("005");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("fsrNum01", fsrNum);

        SVC_TASKTMsgArray result = (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return result;
        }
        return null;
    }

    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        SVC_TASKTMsg param = new SVC_TASKTMsg();
        param.setSQLID("006");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("fsrNum01", fsrNum);
        param.setConditionValue("svcTaskNum01", svcTaskNum);

        SVC_TASKTMsgArray result = (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_TASKTMsg) result.get(0);
        }
        return null;
    }

    private FSRTMsg getFSR(String glblCmpyCd, String fsrNum) {
        FSRTMsg param = new FSRTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("fsrNum01", fsrNum);

        FSRTMsgArray result = (FSRTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (FSRTMsg) result.get(0);
        }
        return null;
    }

    private void checkHold(S21ApiMessageMap msgMap) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();

        // Get FSR
        FSRTMsg fsrTMsg = getFSR(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());

        // del start 2016/03/07 CSA Defect#2697
        // Get Service Task
//        SVC_TASKTMsgArray svcTaskTMsgList = getSvcTaskList(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
//        if (svcTaskTMsgList == null) {
//            msgMap.addXxMsgId(NSZM0710E);
//            return;
//        }
        // del start 2016/03/07 CSA Defect#2697

        // Call API
        String targetClassName = null;
        try {
            int cnt = 0;
            // add start 2016/03/22 CSA Defect#895
            boolean callWfFlg = false;
            // add end 2016/03/22 CSA Defect#895
// mod start 2016/03/07 CSA Defect#2697
//            for (int i = 0; i < svcTaskTMsgList.getValidCount(); i++) {
//                SVC_TASKTMsg svcTaskTMsg = (SVC_TASKTMsg) svcTaskTMsgList.get(i);
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                  SVC_TASKTMsg svcTaskTMsg = getSvcTask(pMsg.glblCmpyCd.getValue(), pMsg.A.no(i).svcTaskNum.getValue());
                if (svcTaskTMsg == null) {
                    msgMap.addXxMsgId(NSZM0710E);
                    return;
                }
// mod end 2016/03/07 CSA Defect#2697

                if (!ZYPCommonFunc.hasValue(svcTaskTMsg.svcTaskStsCd) || (!SVC_TASK_STS.OPEN.equals(svcTaskTMsg.svcTaskStsCd.getValue()) && !SVC_TASK_STS.TBO.equals(svcTaskTMsg.svcTaskStsCd.getValue()))) {
                    msgMap.addXxMsgIdWithPrm(NSZM0709E, new String[] {MSG_PARAM_SERVICE, SVC_TASK_STS.OPEN + " OR " + SVC_TASK_STS.TBO });
                    continue;
                }

                // Get API Class Name
                List<Map<String, String>> targetClassNameList = getExeApiClassName(msgMap, svcTaskTMsg.dsSvcCallTpCd.getValue(), svcTaskTMsg.svcBillTpCd.getValue());
                // add start 2016/04/13 CSA Defect#6866
                if (targetClassNameList == null || targetClassNameList.size() == 0) {
                    msgMap.addXxMsgId(NSZM0963E);
                    return;
                }
                // add end 2016/04/13 CSA Defect#6866
                for (int j = 0; j < targetClassNameList.size(); j++) {
                    targetClassName = targetClassNameList.get(j).get(COLUMN_BIZ_APP_NM);
                    if (!hasValue(targetClassName)) {
                        msgMap.addXxMsgId(NSZM0711E);
                        return;
                    }
                    Class<NSZC037002> targetApi = (Class<NSZC037002>) Class.forName("com.canon.cusa.s21.api.NSZ." + targetClassName + "." + targetClassName);
                    Class<EZDPMsg> targetTMsg = (Class<EZDPMsg>) Class.forName("business.parts." + targetClassName + "PMsg");
                    Class<EZDPMsg> targetEzTMsg = (Class<EZDPMsg>) Class.forName("parts.common.EZDPMsg");

                    // set parameter
                    EZDPMsg subPMsg = targetTMsg.newInstance();
                    EZDItemAttribute attr = subPMsg.getAttr(PARAM_GLBL_CMPY_CD);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_GLBL_CMPY_CD, -1, pMsg.glblCmpyCd.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_BIZ_APP_ID);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_BIZ_APP_ID, -1, targetClassNameList.get(j).get(COLUMN_BIZ_APP_ID));
                    }
                    attr = subPMsg.getAttr(PARAM_SLS_DT);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_SLS_DT, -1, pMsg.slsDt.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_USR_ID);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_USR_ID, -1, pMsg.usrId.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_FSR_NUM);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_FSR_NUM, -1, pMsg.fsrNum.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_SVC_TASK_NUM);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_SVC_TASK_NUM, -1, svcTaskTMsg.svcTaskNum.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_DS_SVC_CALL_TP_CD);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_DS_SVC_CALL_TP_CD, -1, svcTaskTMsg.dsSvcCallTpCd.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_SVC_BILL_TP_CD);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_SVC_BILL_TP_CD, -1, svcTaskTMsg.svcBillTpCd.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_BILL_TO_ACCT_NUM);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_BILL_TO_ACCT_NUM, -1, fsrTMsg.billToCustAcctCd.getValue());
                    }
                    attr = subPMsg.getAttr(PARAM_BILL_TO_CUST_CD);
                    if (attr != null) {
                        subPMsg.setValueString(PARAM_BILL_TO_CUST_CD, -1, getBillToCustCd(fsrTMsg));
                    }

                    Method method = targetApi.getMethod("execute", new Class[] {targetEzTMsg, ONBATCH_TYPE.class });
                    method.invoke(targetApi.newInstance(), new Object[] {subPMsg, ONBATCH_TYPE.BATCH });

                    EZDPMsgArray aList = (EZDPMsgArray) subPMsg.getMsgData("A");
                    EZDPMsgArray messageList = (EZDPMsgArray) subPMsg.getMsgData("xxMsgIdList");
                    if (messageList != null && messageList.getValidCount() > 0) {
                        for (int k = 0; k < messageList.getValidCount(); k++) {
                            String xxMsgId = messageList.get(k).getValueString("xxMsgId", -1);
                            String xxMsgPrmTxt0 = messageList.get(k).getValueString("xxMsgPrmTxt_0", -1);
                            String xxMsgPrmTxt1 = messageList.get(k).getValueString("xxMsgPrmTxt_1", -1);
                            String xxMsgPrmTxt2 = messageList.get(k).getValueString("xxMsgPrmTxt_2", -1);
                            String xxMsgPrmTxt3 = messageList.get(k).getValueString("xxMsgPrmTxt_3", -1);
                            String xxMsgPrmTxt4 = messageList.get(k).getValueString("xxMsgPrmTxt_4", -1);
                            msgMap.addXxMsgIdWithPrm(xxMsgId, new String[] {xxMsgPrmTxt0, xxMsgPrmTxt1, xxMsgPrmTxt2, xxMsgPrmTxt3, xxMsgPrmTxt4 });
                        }
                    } else {
                        // mod start 2016/03/22 CSA Defect#895
                        String svcTaskHldRsnCd = aList.get(0).getValueString("svcTaskHldRsnCd", -1);
                        setValue(pMsg.A.no(i).svcTaskHldRsnCd, svcTaskHldRsnCd);

                        // START 2018/08/30 K.Kitachi [QC#22665, ADD]
                        // START 2022/07/20 L.Mandanas [QC#60316, MOD]
                        //if (isAftHrs(pMsg.glblCmpyCd.getValue(), svcTaskTMsg.dsSvcCallTpCd.getValue()) && SVC_TASK_HLD_RSN.OVER_DUE.equals(svcTaskHldRsnCd)) {
                        if (isAftHrs(pMsg.glblCmpyCd.getValue(), svcTaskTMsg.dsSvcCallTpCd.getValue())
                                && (SVC_TASK_HLD_RSN.OVER_DUE.equals(pMsg.A.no(i).svcTaskHldRsnCd.getValue())
                                    || SVC_TASK_HLD_RSN.CREDIT_LIMIT.equals(pMsg.A.no(i).svcTaskHldRsnCd.getValue()))) {
                        // END 2022/07/20 L.Mandanas [QC#60316, MOD]
                            sendMail(fsrTMsg);
                            msgMap.addXxMsgId(NSZM1347I);
                            continue;
                        }
                        // END 2018/08/30 K.Kitachi [QC#22665, ADD]

                        // add start 2016/03/07 CSA Defect#2697
                        if (hasValue(svcTaskHldRsnCd)) {
                            if (!updateSvcTask(msgMap, i)) {
                                return;
                            }

                            if (!insertSvcTaskHld(msgMap, i)) {
                                return;
                            }
                            callWfFlg = true;
                        }
                        // add end 2016/03/07 CSA Defect#2697
                        // mod end 2016/03/22 CSA Defect#895
                    }
                }
                // del start 2016/03/22 CSA Defect#895
//                pMsg.A.setValidCount(cnt);
                // del end 2016/03/22 CSA Defect#895
            }
            // add start 2016/03/23 CSA Defect#895
            if (callWfFlg) {
                execWf(msgMap, fsrTMsg);
            }
            // add end 2016/03/23 CSA Defect#895
        } catch (InvocationTargetException ex) {
            msgMap.addXxMsgIdWithPrm(NSZM0712E, new String[] {targetClassName });
            return;
        } catch (InstantiationException ex) {
            msgMap.addXxMsgIdWithPrm(NSZM0712E, new String[] {targetClassName });
            return;
        } catch (ClassNotFoundException ex) {
            msgMap.addXxMsgIdWithPrm(NSZM0712E, new String[] {targetClassName });
            return;
        } catch (IllegalAccessException ex) {
            msgMap.addXxMsgIdWithPrm(NSZM0712E, new String[] {targetClassName });
            return;
        } catch (EZDValidatorException ex) {
            msgMap.addXxMsgIdWithPrm(NSZM0712E, new String[] {targetClassName });
            return;
        } catch (NoSuchMethodException ex) {
            msgMap.addXxMsgIdWithPrm(NSZM0712E, new String[] {targetClassName });
            return;
        }

     // del end 2016/03/07 CSA Defect#2697
//        if (!updateSvcTask(msgMap)) {
//            return;
//        }
//
//        if (!insertSvcTaskHld(msgMap)) {
//            return;
//        }
     // del end 2016/03/07 CSA Defect#2697
    }

    // mod start 2016/03/22 CSA Defect#895
    private boolean updateSvcTask(S21ApiMessageMap msgMap, int i) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();

        NSZC037001_APMsg outAPmsg = (NSZC037001_APMsg) pMsg.A.get(i);

        SVC_TASKTMsg svcTaskTMsg = getSvcTask(pMsg.glblCmpyCd.getValue(), outAPmsg.svcTaskNum.getValue());
        setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(svcTaskTMsg.svcTaskNum, outAPmsg.svcTaskNum.getValue());
        setValue(svcTaskTMsg.svcCrHldFlg, ZYPConstant.FLG_ON_Y);

        S21ApiTBLAccessor.update(svcTaskTMsg);
        if (!RETURN_CD_NORMAL.equals(svcTaskTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0625E, new String[] {svcTaskTMsg.getTableName() });
            return false;
        }
        return true;
    }
    // mod end 2016/03/22 CSA Defect#895

    // mod start 2016/03/22 CSA Defect#895
    private boolean insertSvcTaskHld(S21ApiMessageMap msgMap, int i) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();

        NSZC037001_APMsg outAPmsg = (NSZC037001_APMsg) pMsg.A.get(i);
        SVC_TASK_HLDTMsg svcTaskHldTMsg = new SVC_TASK_HLDTMsg();
        if (getSvcTaskHld(pMsg.glblCmpyCd.getValue(), outAPmsg.svcTaskNum.getValue(), outAPmsg.svcTaskHldRsnCd.getValue()) != null) {
            return true;
        }

        BigDecimal newSvcTaskHldPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_HLD_SQ);

        setValue(svcTaskHldTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(svcTaskHldTMsg.svcTaskHldPk, newSvcTaskHldPk);
        setValue(svcTaskHldTMsg.svcTaskNum, outAPmsg.svcTaskNum.getValue());
        setValue(svcTaskHldTMsg.svcTaskHldRsnCd, outAPmsg.svcTaskHldRsnCd.getValue());
        setValue(svcTaskHldTMsg.svcTaskHldDt, pMsg.slsDt.getValue());
        setValue(svcTaskHldTMsg.svcTaskHldUsrId, pMsg.usrId.getValue());
        setValue(svcTaskHldTMsg.svcTaskRelFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.create(svcTaskHldTMsg);
        if (!RETURN_CD_NORMAL.equals(svcTaskHldTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {svcTaskHldTMsg.getTableName() });
            return false;
        }
        return true;
    }
    // mod end 2016/03/22 CSA Defect#895

    private SVC_TASK_HLDTMsg getSvcTaskHld(String glblCmpyCd, String svcTaskNum, String svcTaskHldRsnCd) {
        SVC_TASK_HLDTMsg param = new SVC_TASK_HLDTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcTaskNum01", svcTaskNum);
        param.setConditionValue("svcTaskHldRsnCd01", svcTaskHldRsnCd);
        param.setConditionValue("svcTaskRelFlg01", ZYPConstant.FLG_OFF_N);

        SVC_TASK_HLDTMsgArray result = (SVC_TASK_HLDTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_TASK_HLDTMsg) result.get(0);
        }
        return null;
    }

    private String getBillToCustCd(FSRTMsg fsrTMsg) {
        String rtnBillToCustCd = null;
        if (fsrTMsg == null) {
            return rtnBillToCustCd;
        }

        if (ZYPConstant.FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
            rtnBillToCustCd = fsrTMsg.billToUpdCustCd.getValue();
        } else {
            rtnBillToCustCd = fsrTMsg.billToCustCd.getValue();
        }
        return rtnBillToCustCd;
    }

    private List<Map<String, String>> getExeApiClassName(S21ApiMessageMap msgMap, String dsSvcCallTpCd, String svcBillTpCd) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();

        return getSvcTaskHldCtrl(pMsg.glblCmpyCd.getValue(), dsSvcCallTpCd, svcBillTpCd);
    }

    private List<Map<String, String>> getSvcTaskHldCtrl(String glblCmpyCd, String dsSvcCallTpCd, String svcBillTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsSvcCallTpCd", dsSvcCallTpCd);
        param.put("svcBillTpCd", svcBillTpCd);

        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getSvcTaskHldCtrl", param);
    }

    // add start 2016/03/07 CSA Defect#2697
    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // add end 2016/03/07 CSA Defect#2697

    // add start 2016/03/22 CSA Defect#895
    private void execWf(S21ApiMessageMap msgMap, FSRTMsg fsrTMsg) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();
        // Insert Service Memo
        if (!insertSvcMemo(pMsg)) {
            msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {"SVC_MEMO" });
            return;
        }

        // add start 2016/05/09 CSA Defect#895
        // set Token Object
        NSZC037001TokenObject tokenBiz = setTokenBiz(fsrTMsg, pMsg);
        // add end 2016/05/09 CSA Defect#895

        // Start Work Flow
        startWf(msgMap, tokenBiz);
    }

    private boolean insertSvcMemo(NSZC037001PMsg pMsg) {
        Map<String, Object> param;
        boolean memoFlg = false;
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            param = new HashMap<String, Object>();
            param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            param.put("svcTaskNum", pMsg.A.no(i).svcTaskNum.getValue());

            String bllblFlg = (String) this.ssmBatchClient.queryObject("getBllblFlg", param);
            if (hasValue(bllblFlg) && ZYPConstant.FLG_ON_Y.equals(bllblFlg)) {
                memoFlg = true;
                break;
            }
        }

        if (memoFlg) {
            SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
            setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
            setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.CREDIT);
            setValue(svcMemoTMsg.svcCmntTxt, NSZC0370_WF_MEMO_TXT);
            setValue(svcMemoTMsg.fsrNum, pMsg.fsrNum);
            setValue(svcMemoTMsg.lastUpdUsrId, pMsg.usrId);
            setValue(svcMemoTMsg.lastUpdTs, this.procTs);
            S21ApiTBLAccessor.create(svcMemoTMsg);
            if (!RETURN_CD_NORMAL.equals(svcMemoTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    // mod start 2016/05/09 CSA Defect#895
    private NSZC037001TokenObject setTokenBiz(FSRTMsg fsrTMsg, NSZC037001PMsg pMsg) {
        // Business Token Object
        NSZC037001TokenObject tokenBiz = new NSZC037001TokenObject();

        // Line Data
        NSZC037001TokenObjectLine line = new NSZC037001TokenObjectLine();
        line.setDtlAttrb1(fsrTMsg.glblCmpyCd.getValue());
        line.setDtlAttrb2(fsrTMsg.fsrNum.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
            line.setDtlAttrb3(fsrTMsg.billToUpdCustCd.getValue());
        } else {
            line.setDtlAttrb3(fsrTMsg.billToCustCd.getValue());
        }
        line.setDtlAttrb4(fsrTMsg.billToCustAcctCd.getValue());
        tokenBiz.addLineData(line);

        // Set Condition Data
        tokenBiz.setBizId(WF_PROCESS_NM);
        if (ZYPConstant.FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
            tokenBiz.setCondStr1(fsrTMsg.billToUpdCustCd.getValue());
        } else {
            tokenBiz.setCondStr1(fsrTMsg.billToCustCd.getValue());
        }
        tokenBiz.setCondStr2(fsrTMsg.billToCustAcctCd.getValue());

        // Set Screen Data
        tokenBiz.setAttribute1Lbl("FSR#");
        tokenBiz.setAttribute1(fsrTMsg.fsrNum.getValue());
        tokenBiz.setAttribute2Lbl("Bill To Cust Code");
        if (ZYPConstant.FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
            tokenBiz.setAttribute2(fsrTMsg.billToUpdCustCd.getValue());
        } else {
            tokenBiz.setAttribute2(fsrTMsg.billToCustCd.getValue());
        }
        tokenBiz.setAttribute3Lbl("Bill To Cust Acct Code");
        tokenBiz.setAttribute3(fsrTMsg.billToCustAcctCd.getValue());

        // add start 2018/05/23 CSA Defect#26177
        tokenBiz.setBizScreenId(FUNCTION_ID_EXTNE307T020); // Mod 2018/06/04 CSA Defect#26177-2
        // add end 2018/05/23 CSA Defect#26177

        // Set Mail Item
        // START 2022/1/27 R.Onozuka [QC#56182, MOD]
        //setMailItem(tokenBiz, pMsg);
        setMailItem(tokenBiz, pMsg, fsrTMsg);
        // END   2022/1/27 R.Onozuka [QC#56182, MOD]

        return tokenBiz;
    }
    // mod end 2016/05/09 CSA Defect#895

    private void startWf(S21ApiMessageMap msgMap, NSZC037001TokenObject tokenBiz) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();
        // Create New Work Flow Process
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context;
        S21NwfProcess process;

        try {
            context = factory.getContex();
            process = context.newProcess(WF_PROCESS_NM);
            process.setDocumentId(pMsg.fsrNum.getValue());
        } catch (S21NwfSystemException e) {
            // System Error Logic
            return;
        }

        S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
        token.setTokenObj(tokenBiz);

        try {
            // Start Workflow
            token.start();
            // START 2023/06/07 S.Fujita [QC#60923, ADD]
            regisSvcMemo(msgMap, pMsg);
            // END 2023/06/07 S.Fujita [QC#60923, ADD]
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0921E);
            return;
        } catch (S21NwfBizException e) {
            // Business Error Logic
            // Auto Approve Process Call APIs
            // Approve API / Reject API / Process End API Error
            msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            return;
        } catch (S21NwfException e) {
            // System Error Logic
            msgMap.addXxMsgId(NFDM0008E);
            return;
        }
    }
    // add end 2016/03/22 CSA Defect#895

    // add start 2016/05/09 CSA Defect#895
    // START 2022/1/27 R.Onozuka [QC#56182, MOD]
    //private void setMailItem(NSZC037001TokenObject tokenBiz, NSZC037001PMsg pMsg) {
    private void setMailItem(NSZC037001TokenObject tokenBiz, NSZC037001PMsg pMsg, FSRTMsg fsrTMsg) {
    // END   2022/1/27 R.Onozuka [QC#56182, MOD]
        // START 2018/10/30 K.Kitachi [QC#28879, ADD]
        boolean isBllbl = false;
        BigDecimal count = countBllblTask(pMsg);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            isBllbl = true;
        }
        // END 2018/10/30 K.Kitachi [QC#28879, ADD]

        // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//        Map<String, Object> data = getMailData(pMsg);
        Map<String, Object> data = getMailData(pMsg, isBllbl);
        // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        if (data == null) {
            return;
        }
        // set Items
        tokenBiz.setSysTs(this.mailTs);
        tokenBiz.setFsrNum((String) data.get("FSR_NUM"));
        tokenBiz.setSvcBrCd((String) data.get("SVC_BR_CD"));
        tokenBiz.setDsAcctNum((String) data.get("DS_ACCT_NUM"));
        tokenBiz.setDsAcctNm((String) data.get("DS_ACCT_NM"));
        tokenBiz.setDsCltAcctStsDescTxt((String) data.get("DS_CLT_ACCT_STS_DESC_TXT"));
        // START 2018/07/31 M.Naito [QC#27463, MOD]
//        tokenBiz.setCrChkReqTpDefFlg((String) data.get("CR_CHK_REQ_TP_DEF_FLG"));
        tokenBiz.setCustHardHldFlg((String) data.get("CUST_HARD_HLD_FLG"));
        // END 2018/07/31 M.Naito [QC#27463, MOD]
        tokenBiz.setCrLimitAmt((BigDecimal) data.get("CR_LIMIT_AMT"));
        tokenBiz.setOpenAr((BigDecimal) data.get("OPEN_AR"));
        tokenBiz.setCallAmt((BigDecimal) data.get("CALL_AMT"));
        // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//        tokenBiz.setDealRmngBalGrsAmt((BigDecimal) data.get("DEAL_RMNG_BAL_GRS_AMT"));
        tokenBiz.setInProcAmt((BigDecimal) data.get("IN_PROC_AMT"));
        // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        tokenBiz.setCallTaxAmt((BigDecimal) data.get("CALL_TAX_AMT"));
        // START 2022/12/27 S.Fujita [QC#60744, DEL]
        //tokenBiz.setGracePerDaysAot((BigDecimal) data.get("GRACE_PER_DAYS_AOT"));
        // END 2022/12/27 S.Fujita [QC#60744, DEL]
        tokenBiz.setPastDueBy((BigDecimal) data.get("PAST_DUE_BY"));
        tokenBiz.setPastDueAmt((BigDecimal) data.get("PAST_DUE_AMT"));
        tokenBiz.setDsPoReqFlg((String) data.get("DS_PO_REQ_FLG"));
        tokenBiz.setCustPoNum((String) data.get("CUST_PO_NUM"));
        // START 2022/1/27 R.Onozuka [QC#56182, ADD]
        String dsSvcCallTpCd = getDsSvcCallTpCd(pMsg.glblCmpyCd.getValue(), (String) data.get("FSR_NUM"));
        String dsContrNum = identifyDsContrNum(
                pMsg.glblCmpyCd.getValue(), NSZC061001Constant.PROCESS_MODE_CALL_CREATION, pMsg.slsDt.getValue(),
                fsrTMsg.svcMachMstrPk.getValue(), dsSvcCallTpCd);
        tokenBiz.setDsContrNum(dsContrNum);
        // END   2022/1/27 R.Onozuka [QC#56182, ADD]
        tokenBiz.setPmtTermCashDiscDescTxt((String) data.get("PMT_TERM_CASH_DISC_DESC_TXT"));
        tokenBiz.setSvcTaskHldRsnDescTxt((String) data.get("SVC_TASK_HLD_RSN_DESC_TXT"));
        // START 2022/12/27 S.Fujita [QC#60744, DEL]
        // START 2018/10/30 K.Kitachi [QC#28879, ADD]
        //if (isBllbl) {
        //    data = getPastDueInfo(pMsg);
        //    if (data != null) {
        //        tokenBiz.setPastDueBy((BigDecimal) data.get("PAST_DUE_BY"));
        //        tokenBiz.setPastDueAmt((BigDecimal) data.get("PAST_DUE_AMT"));
        //    }
        //}
        // END 2018/10/30 K.Kitachi [QC#28879, ADD]
        // END 2022/12/27 S.Fujita [QC#60744, DEL]
        // START 2022/12/27 S.Fujita [QC#60744, ADD]
        if (isBllbl) {
            tokenBiz.setCallTpTxt("Billable");
            tokenBiz.setGracePerDaysAot((BigDecimal) data.get("GRACE_PER_DAYS_AOT"));
        } else {
            tokenBiz.setCallTpTxt("Contract");
            tokenBiz.setGracePerDaysAot((BigDecimal) data.get("CONTR_GRACE_PER_DAYS_AOT"));
        }
        if (ZYPCommonFunc.hasValue(dsContrNum)) {
        	
            data = getPastDueInfo(pMsg);
            if (data != null) {
                tokenBiz.setContrOpenAr((BigDecimal) data.get("CONTR_OPEN_AR"));
                tokenBiz.setContrPastDueBy((BigDecimal) data.get("CONTR_PAST_DUE_BY"));
                tokenBiz.setContrPastDueAmt((BigDecimal) data.get("CONTR_PAST_DUE_AMT"));
            }
        }
        // END 2022/12/27 S.Fujita [QC#60744, ADD]
    }

    // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//    private Map<String, Object> getMailData(NSZC037001PMsg pMsg) {
    private Map<String, Object> getMailData(NSZC037001PMsg pMsg, boolean isBllbl) {
    // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("fsrNum", pMsg.fsrNum.getValue());
        param.put("arCashApplyStsCd", new String[] {AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED });
        param.put("sysTs", this.procTs);
        param.put("slsDt", pMsg.slsDt.getValue());
        // START 2018/10/30 K.Kitachi [QC#28879, ADD]
        param.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
        // START 2022/12/27 S.Fujita [QC#60744, DEL]
        //if (isBllbl) {
        //    return (Map<String, Object>) this.ssmBatchClient.queryObject("getMailDataForBllbl", param);
        //}
        // END 2022/12/27 S.Fujita [QC#60744, DEL]
        // END 2018/10/30 K.Kitachi [QC#28879, ADD]
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMailData", param);
    }
    // add end 2016/05/09 CSA Defect#895

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    private boolean isAftHrs(String glblCmpyCd, String dsSvcCallTpCd) {
        if (!hasValue(dsSvcCallTpCd)) {
            return false;
        }
        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, glblCmpyCd, dsSvcCallTpCd);
        if (dsSvcCallTpTMsg == null) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.aftHrsFlg.getValue())) {
            return true;
        }
        return false;
    }

    private void sendMail(FSRTMsg fsrTMsg) {
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(fsrTMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_FROM);
        // START 2021/08/03 L.Mandanas [QC#59066, ADD]
        groupFrom.setMailKey1(MAIL_GROUP_MAIL_KEY_FROM);
        // END 2021/08/03 L.Mandanas [QC#59066, ADD]
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            return;
        }

        // Get To Address
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
        List<String> emlAddrList = getEmlAddrList(fsrTMsg);
        for (String emlAddr : emlAddrList) {
            toAddrList.add(new S21MailAddress(fsrTMsg.glblCmpyCd.getValue(), emlAddr));
        }

        if (toAddrList == null || toAddrList.isEmpty()) {
            return;
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(fsrTMsg.glblCmpyCd.getValue(), MAIL_TEMPLATE_ID);
        if (template == null || !hasValue(template.getBody())) {
            throw new S21AbendException(NSZM0185E);
        }

        // Mail Body Edit Start
        Map<String, Object> mailInfo = getMailInfo(fsrTMsg);
        if (mailInfo == null) {
            return;
        }
        template.setTemplateParameter("fsrNum", fsrTMsg.fsrNum.getValue());
        template.setTemplateParameter("custNm", (String) mailInfo.get("DS_ACCT_NM"));
        template.setTemplateParameter("address", (String) mailInfo.get("LINE_ADDR"));
        template.setTemplateParameter("ctacNm", (String) mailInfo.get("SVC_CUST_ATTN_TXT"));
        template.setTemplateParameter("ctacNum", (String) mailInfo.get("CUST_TEL_NUM"));
        template.setTemplateParameter("mdlNm", (String) mailInfo.get("T_MDL_NM"));
        template.setTemplateParameter("serNum", (String) mailInfo.get("SER_NUM"));
        Map<String, Object> custInfo = getCustInfo(fsrTMsg);
        if (custInfo != null) {
            template.setTemplateParameter("custNm", (String) custInfo.get("DS_ACCT_NM"));
            template.setTemplateParameter("address", (String) custInfo.get("LINE_ADDR"));
        }
        // Mail Body Edit End

        // Call Mail API
        S21Mail mail = new S21Mail(fsrTMsg.glblCmpyCd.getValue());
        mail.setFromAddress(fromAddrList.get(0));
        mail.setToAddressList(toAddrList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    private List<String> getEmlAddrList(FSRTMsg fsrTMsg) {
        List<String> emlAddrList = new ArrayList<String>();
        List<String> cltEmlAddrList = getCltEmlAddrList(fsrTMsg);
        if (cltEmlAddrList.size() == 0) {
            String defCltEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(AR_CLT_DEF_EML_ADDR, fsrTMsg.glblCmpyCd.getValue());
            emlAddrList.add(defCltEmlAddr);
            return emlAddrList;
        }
        for (String cltEmlAddr : cltEmlAddrList) {
            if (!emlAddrList.contains(cltEmlAddr)) {
                emlAddrList.add(cltEmlAddr);
            }
        }
        return emlAddrList;
    }

    private List<String> getCltEmlAddrList(FSRTMsg fsrTMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", fsrTMsg.glblCmpyCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue()) && hasValue(fsrTMsg.billToUpdCustCd)) {
            BILL_TO_CUSTTMsg billToCustTMsg = getBillToCust(fsrTMsg.glblCmpyCd.getValue(), fsrTMsg.billToUpdCustCd.getValue());
            if (billToCustTMsg != null) {
                ssmPrm.put("sellToCustCd", billToCustTMsg.sellToCustCd.getValue());
                ssmPrm.put("billToCustCd", billToCustTMsg.billToCustCd.getValue());
                return (List<String>) this.ssmBatchClient.queryObjectList("getCltEmlAddrList", ssmPrm);
            }
        }
        if (hasValue(fsrTMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(fsrTMsg.glblCmpyCd.getValue(), fsrTMsg.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg != null) {
                ssmPrm.put("sellToCustCd", svcMachMstrTMsg.billToAcctNum.getValue());
                ssmPrm.put("billToCustCd", svcMachMstrTMsg.billToLocNum.getValue());
                return (List<String>) this.ssmBatchClient.queryObjectList("getCltEmlAddrList", ssmPrm);
            }
        }
        return new ArrayList<String>();
    }

    private BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("990");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray outArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (outArray.getValidCount() > 0) {
            return outArray.no(0);
        }
        return null;
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private Map<String, Object> getMailInfo(FSRTMsg fsrTMsg) {
        if (fsrTMsg == null) {
            return null;
        }
        if (!hasValue(fsrTMsg.svcMachMstrPk)) {
            return null;
        }
        if (!hasValue(fsrTMsg.fsrNum)) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", fsrTMsg.glblCmpyCd.getValue());
        ssmPrm.put("svcMachMstrPk", fsrTMsg.svcMachMstrPk.getValue());
        ssmPrm.put("fsrNum", fsrTMsg.fsrNum.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMailInfo", ssmPrm);
    }

    private Map<String, Object> getCustInfo(FSRTMsg fsrTMsg) {
        if (fsrTMsg == null) {
            return null;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(fsrTMsg.shipToCustUpdFlg.getValue())) {
            return null;
        }
        if (!hasValue(fsrTMsg.shipToUpdCustCd)) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", fsrTMsg.glblCmpyCd.getValue());
        ssmPrm.put("shipToCustCd", fsrTMsg.shipToUpdCustCd.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getCustInfo", ssmPrm);
    }
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    // START 2018/10/30 K.Kitachi [QC#28879, ADD]
    private BigDecimal countBllblTask(NSZC037001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        return (BigDecimal) this.ssmBatchClient.queryObject("countBllblTask", paramMap);
    }

    private Map<String, Object> getPastDueInfo(NSZC037001PMsg pMsg) {
        List<String> dsContrNumList = getDsContrNumList(pMsg);
        if (dsContrNumList == null || dsContrNumList.isEmpty()) {
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("arTrxTpInvoice", AR_TRX_TP.INVOICE);
        paramMap.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        paramMap.put("arCashApplyStsPartial", AR_CASH_APPLY_STS.PARTIAL);
        paramMap.put("dsContrNumList", dsContrNumList);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getPastDueInfo", paramMap);
    }

    private List<String> getDsContrNumList(NSZC037001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        return (List<String>) this.ssmBatchClient.queryObjectList("getContrNum", paramMap);
    }
    // END 2018/10/30 K.Kitachi [QC#28879, ADD]

    // START 2022/1/27 R.Onozuka [QC#56182, ADD]
    private String identifyDsContrNum (String glblCmpyCd, String modeCd, String slsDate, BigDecimal svcMachMstrPk, String dsSvcCallTpCd){

        String dsContrNum = null;
        String sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_TS);
        String sysTm = sysTs.substring(LEN_DT, sysTs.length());

        NSZC061001 billCodeApi = new NSZC061001();
        NSZC061001PMsg pmsg = new NSZC061001PMsg();
        pmsg.glblCmpyCd.setValue(glblCmpyCd);
        pmsg.xxModeCd.setValue(modeCd);
        pmsg.startDt.setValue(slsDate);
        pmsg.startTm.setValue(sysTm);
        pmsg.svcMachMstrPk.setValue(svcMachMstrPk);
        pmsg.dsSvcCallTpCd.setValue(dsSvcCallTpCd);

        // execute NSZC061001
        billCodeApi.execute(pmsg, ONBATCH_TYPE.ONLINE);
        if (!S21ApiUtil.isXxMsgId(pmsg)) {
            BigDecimal dsContrPk = pmsg.dsContrPk.getValue();
            if (hasValue(dsContrPk)) {
                DS_CONTRTMsg tMsg = getDsContr(glblCmpyCd, dsContrPk);
                dsContrNum = tMsg.dsContrNum.getValue();
            }
        }

        return dsContrNum;
    }

    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private String getDsSvcCallTpCd(String glblCmpyCd, String fsrNum){

        SVC_TASKTMsg condSvcTaskTMsg = new SVC_TASKTMsg();
        condSvcTaskTMsg.setSQLID("005");
        condSvcTaskTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condSvcTaskTMsg.setConditionValue("fsrNum01", fsrNum);
        SVC_TASKTMsgArray svcTaskTMsgArray = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(condSvcTaskTMsg);

        String svcTaskNum = null;
        if (svcTaskTMsgArray != null && svcTaskTMsgArray.getValidCount() > 0) {
            svcTaskNum = svcTaskTMsgArray.no(0).svcTaskNum.getValue();
        }

        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcTaskNum, svcTaskNum);
        tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);

        return tMsg.dsSvcCallTpCd.getValue();
    }
    // END   2022/1/27 R.Onozuka [QC#56182, ADD]

    // START 2023/06/07 S.Fujita [QC#60923, ADD]
    private void regisSvcMemo(S21ApiMessageMap msgMap, NSZC037001PMsg pMsg) {
        String psnCd = getApvrUsrId(msgMap);

        if (ZYPCommonFunc.hasValue(psnCd)) {
            S21UserInfo userInfo = S21UserProfileServiceFactory.getInstance().getService().getUserInfoFor(psnCd);
            String managerID = userInfo.getUserDetails().getManagerId();

            Map<String, Object> usrParam = new HashMap<String, Object>();
            usrParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            usrParam.put("psnCd", psnCd);

            String aprvUsrNm = null;
            String aprvUserTelNum = null;
            Map<String, String> usrData = (Map<String, String>) this.ssmBatchClient.queryObject("getAprvUsrInfo", usrParam);
            if (usrData != null) {
                aprvUsrNm = usrData.get("PSN_NM");
                aprvUserTelNum = usrData.get("WRK_TEL_NUM");
            }

            String managerNm = null;
            String managerTelNum = null;
            if (ZYPCommonFunc.hasValue(managerID)) {
                Map<String, Object> managerParam = new HashMap<String, Object>();
                managerParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                managerParam.put("managerID", managerID);

                Map<String, String> managerData = (Map<String, String>) this.ssmBatchClient.queryObject("getManagerInfo", managerParam);
                if (managerData != null) {
                    managerNm = managerData.get("PSN_NM");
                    managerTelNum = managerData.get("WRK_TEL_NUM");
                }
            }

            String cmntTxt = null;
            String creRevBy = ZYPCodeDataUtil.getVarCharConstValue(CMNT_TXT_1, pMsg.glblCmpyCd.getValue());
            String isReq = ZYPCodeDataUtil.getVarCharConstValue(CMNT_TXT_2, pMsg.glblCmpyCd.getValue());
            String manIs = ZYPCodeDataUtil.getVarCharConstValue(CMNT_TXT_3, pMsg.glblCmpyCd.getValue());
            String period = ZYPCodeDataUtil.getVarCharConstValue(CMNT_TXT_4, pMsg.glblCmpyCd.getValue());
            StringBuilder sb = new StringBuilder();

            sb.append(creRevBy);
            sb.append(SPACE);
            sb.append(psnCd);
            if (ZYPCommonFunc.hasValue(aprvUsrNm)) {
                sb.append(SPACE);
                sb.append(aprvUsrNm);
            }
            if (ZYPCommonFunc.hasValue(aprvUserTelNum)) {
                sb.append(SPACE);
                sb.append(aprvUserTelNum);
            }
            sb.append(isReq);
            if (ZYPCommonFunc.hasValue(managerID)) {
                sb.append(manIs);
                sb.append(SPACE);
                sb.append(managerID);
                if (ZYPCommonFunc.hasValue(managerNm)) {
                    sb.append(SPACE);
                    sb.append(managerNm);
                }
                if (ZYPCommonFunc.hasValue(managerTelNum)) {
                    sb.append(SPACE);
                    sb.append(managerTelNum);
                }
            }
            sb.append(period);
            cmntTxt = sb.toString();

            SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.CREDIT);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, cmntTxt);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.fsrNum, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, pMsg.usrId);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, this.procTs);

            EZDTBLAccessor.insert(svcMemoTMsg);
        }
    }

    private String getApvrUsrId(S21ApiMessageMap msgMap) {
        NSZC037001PMsg pMsg = (NSZC037001PMsg) msgMap.getPmsg();
        String apvrUsrId = null;

        try {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            S21NwfContext context = factory.getContex();
            List<S21NwfProcess> procs = context.getProcessForBiz(WF_PROCESS_NM, pMsg.fsrNum.getValue());

            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (hasValue(apvrUsrId)) {
                        break;
                    }
                    if (wi.isApprovable() && !wi.isComplete()) {
                        List<S21NwfUserRole> users = wi.getToUsers();
                        for (S21NwfUserRole user : users) {
                            String group = user.getGroup();
                            if (!hasValue(group)) {
                                apvrUsrId = user.getUserRole();
                                break;
                            }
                        }
                    }
                }
            }
            return apvrUsrId;
        } catch (S21NwfException e) {
            return null;
        }
    }

    // END 2023/06/07 S.Fujita [QC#60923, ADD]
}
