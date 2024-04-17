/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC045001;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.MDSETMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.db.SVC_TASK_STSTMsg;
import business.parts.NSZC002001PMsg;
import business.parts.NSZC003001PMsg;
import business.parts.NSZC004001PMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC045001_xxSvcMemoListPMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC002001.NSZC002001;
import com.canon.cusa.s21.api.NSZ.NSZC002001.constant.NSZC002001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC003001.NSZC003001;
import com.canon.cusa.s21.api.NSZ.NSZC003001.constant.NSZC003001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC004001.NSZC004001;
import com.canon.cusa.s21.api.NSZ.NSZC004001.constant.NSZC004001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetFollUpInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetFollUpInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NSX.NSXC002001.CovInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetCovInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 * Add Task API
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 08/10/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 09/09/2015   Hitachi         T.Tsuchida      Update          NA#Follow up Call
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 12/18/2015   Hitachi         J.Kim           Update          CSA QC#1112
 * 02/10/2016   Hitachi         T.Iwamoto       Update          CSA QC#3727
 * 02/26/2016   Hitachi         T.Iwamoto       Update          CSA QC#4113
 * 03/02/2016   Hitachi         Y.Tsuchimoto    Update          CSA QC#4753
 * 03/04/2016   Hitachi         T.Iwamoto       Update          CSA QC#2697
 * 04/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#5083,6020
 * 04/12/2016   Hitachi         T.Iwamoto       Update          CSA QC#6766
 * 04/13/2016   Hitachi         T.Iwamoto       Update          CSA QC#1945
 * 04/21/2016   Hitachi         T.Iwamoto       Update          CSA QC#7320
 * 04/28/2016   Hitachi         T.Iwamoto       Update          CSA QC#2696
 * 05/25/2016   Hitachi         T.Iwamoto       Update          CSA QC#8605
 * 06/07/2016   Hitachi         T.Iwamoto       Update          CSA QC#3727,9218
 * 06/09/2016   Hitachi         T.Iwamoto       Update          CSA QC#9177
 * 06/15/2016   Hitachi         T.Iwamoto       Update          CSA QC#8899
 * 06/16/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 06/22/2016   Hitachi         T.Iwamoto       Update          CSA QC#8899,9677
 * 07/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 08/10/2016   Fujitsu         S.Nakai         Update          CSA QC#8421
 * 09/07/2016   Fujitsu         S.Nakai         Update          CSA QC#8421
 * 09/09/2016   Hitachi         T.Kanasaka      Update          CSA QC#14206
 * 10/05/2016   Hitachi         T.Tomita        Update          CSA QC#14145
 * 01/05/2017   Hitachi         K.Yamada        Update          CSA QC#16942
 * 06/23/2017   Hitachi         K.Ochiai        Update          CSA QC#19493
 * 08/28/2017   Hitachi         K.Kojima        Update          CSA QC#20643
 * 09/08/2017   Hitachi         T.Tomita        Update          CSA QC#19242
 * 09/27/2017   Hitachi         T.Tomita        Update          CSA QC#19242
 * 10/02/2017   Hitachi         T.Tomita        Update          CSA QC#21273
 * 03/22/2018   Hitachi         T.Tomita        Update          CSA QC#18713
 * 06/25/2018   CITS            M.Naito         Update          CSA QC#25608
 * 08/09/2018   Hitachi         K.Kojima        Update          CSA QC#27399
 * 09/26/2018   CITS            T.Wada        Update            CSA QC#28262
 * 2018/10/24   Hitachi         K.Kojima        Update          CSA QC#28545
 * 2018/11/08   Hitachi         K.Kitamura      Update          CSA QC#28930
 * 2018/12/21   Hitachi         K.Fujimoto      Update          CSA QC#29079
 * 2019/02/15   Hitachi         K.Kitachi       Update          CSA QC#30165
 * 2019/04/19   Hitachi         A.Kohinata      Update          CSA QC#31238
 * 2019/04/26   Hitachi         A.Kohinata      Update          CSA QC#31213
 * 2021/10/06   CITS            R.Jabal         Update          CSA QC#59183
 * 2021/10/21   CITS            R.Jabal         Update          CSA QC#59327
 * 2022/04/11   Hitachi         K.Kitachi       Update          CSA QC#59899
 * 2022/06/01   Hitachi         K.Kim           Update          CSA QC#59899-1
 * </pre>
 */
public class NSZC045001 extends S21ApiCommonBase {

    /**
     * NSZC045001ApiMsgIdMgr
     */
    private static final class NSZC045001ApiMsgIdMgr extends S21ApiMessageIdMgr {

        @SuppressWarnings("unchecked")
        public boolean isXxErrMsgId() throws S21AbendException {
            try {
                Field fld = S21ApiMessageIdMgr.class.getDeclaredField("messageList");
                fld.setAccessible(true);
                Object obj = fld.get(this);
                if (obj == null) {
                    return false;
                } else if (List.class.isAssignableFrom(obj.getClass())) {
                    List<String> msgIdList = (List<String>) obj;
                    for (String msgId : msgIdList) {
                        if (msgId != null && msgId.endsWith("E")) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                throw new S21AbendException(ex);
            }
        }

    }

    /**
     * Process Mode: Add Task
     */
    public static final String PROCESS_MODE_ADD_TASK = "01";

    /**
     * Process Mode: Cancel Task
     */
    public static final String PROCESS_MODE_CANCEL_TASK = "02";

    // START 09/09/2015 T.Tsuchida [NA#Follow up Call,ADD]
    /**
     * Process Mode: Follow Up Task
     */
    public static final String PROCESS_MODE_FOLLOWUP_TASK = "03";

    // END 09/09/2015 T.Tsuchida [NA#Follow up Call,ADD]

    /**
     * Process Mode: Call Avoidance Dispatch Technician
     */
    public static final String PROCESS_MODE_CALLAVOID_DISPTTECH = "04";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    private static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Process Mode" is a mandatory field.
     */
    private static final String NSZM0003E = "NSZM0003E";

    /**
     * Service machine master is not found.
     */
    private static final String NSZM0006E = "NSZM0006E";

    /**
     * Input parameter "Shipping Order Number" is a mandatory field.
     */
    public static final String NSZM0027E = "NSZM0027E";

    /**
     * Process Mode is not valid.
     */
    private static final String NSZM0039E = "NSZM0039E";

    /**
     * Input parameter "DS Service Call Type Code" is a mandatory
     * field.
     */
    private static final String NSZM0049E = "NSZM0049E";

    /**
     * Input parameter "Service Task Received Date" is a mandatory
     * field.
     */
    private static final String NSZM0053E = "NSZM0053E";

    /**
     * Input parameter "Service Task Received Time" is a mandatory
     * field.
     */
    private static final String NSZM0054E = "NSZM0054E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    private static final String NSZM0074E = "NSZM0074E";

    /**
     * The entered 'Model Name' does not exist.
     */
    private static final String NSZM0090E = "NSZM0090";

    /**
     * The process cannot be executed because the "FSR Status" is
     * incorrect.
     */
    private static final String NSZM0154E = "NSZM0154E";

    /**
     * Failed to search a FSR Visit record.
     */
    private static final String NSZM0182E = "NSZM0182E";

    /**
     * Input parameter "FSR Number" is a mandatory field.
     */
    private static final String NSZM0291E = "NSZM0291E";

    /**
     * Input parameter "User ID" is a mandatory field.
     */
    private static final String NSZM0293E = "NSZM0293E";

    /**
     * The entered 'Svc Task Number' does not exist.
     */
    private static final String NSZM0302E = "NSZM0302E";

    /**
     * The entered 'DS Service Call Type Code' does not exist.
     */
    private static final String NSZM0549E = "NSZM0549E";

    /**
     * Input parameter "Bypass Preferred Tech Flag" is a mandatory
     * field.
     */
    private static final String NSZM0582E = "NSZM0582E";

    /**
     * The entered 'Merchandise Code' does not exist.
     */
    private static final String NSZM0583E = "NSZM0583E";

    /**
     * The entered 'FSR Status Code' does not exist.
     */
    private static final String NSZM0584E = "NSZM0584E";

    /**
     * The entered 'Model Group Name' does not exist.
     */
    private static final String NSZM0585E = "NSZM0585E";

    // START 09/09/2015 T.Tsuchida [NA#Follow up Call,ADD]
    /**
     * The entered 'Ship To Cust Cd' does not exist.
     */
    private static final String NSZM0084E = "NSZM0084E";

    /**
     * Failed to search a FSR Visit Task record.
     */
    // private static final String NSZM0423E = "NSZM0423E";
    /**
     * The entered Resource as 'Technician' does not exist.
     */
    private static final String NSZM0602E = "NSZM0602E";

    /**
     * The entered Resource as 'Technician' is not active.
     */
    private static final String NSZM0603E = "NSZM0603E";

    /**
     * The entered 'Service Following Upper Start Code' does not
     * exist.
     */
    // private static final String NSZM0621E = "NSZM0621E";
    // /**
    // * Input parameter "Service Problem Reason Type Code" is a
    // * mandatory field.
    // */
    // private static final String NSZM0245E = "NSZM0245E";
    // del start 2015/12/18 CSA Defect#1112
    // /** SVC_TASK is not found. */
    // private static final String NSZM0486E = "NSZM0486E";
    // del end 2015/12/18 CSA Defect#1112
    // add 2016/04/21 CSA Defect#7320
    /**
     * Unable to proceed due to existing status tasks can not be
     * cancelled.
     */
    private static final String NSZM0814E = "NSZM0814E";

    // add 2016/04/21 CSA Defect#7320
    /** Please execute the "FSR Cancel". */
    private static final String NSZM0966E = "NSZM0966E";

    // START 2017/08/28 K.Kojima [QC#20643,ADD]
    /** Failed to insert the SVC_MEMO. (System Error) */
    private static final String NSZM0475E = "NSZM0475E";

    // END 2017/08/28 K.Kojima [QC#20643,ADD]

    // Add Start 2018/06/25 QC#25608
    /** It failed to cancel workflow. */
    String NSZM0866E = "NSZM0866E";

    /** Process def name : NSWP0004 */
    String PROCESS_DEF_NSWP0004 = "NSWP0004";

    // Add End 2018/06/25 QC#25608

    /** TS_POSTFIX : 000 */
    private static final String TS_POSTFIX = "000";

    /** 000000 */
    private static final String TM_START_OFTHEDAY = "000000";

    /** Time Stamp Format */
    // private static final String TIME_STAMP_FORMAT =
    // "yyyyMMddHHmmssSSS";
    // END 09/09/2015 T.Tsuchida [NA#Follow up Call,ADD]
    // add start 2015/12/18 CSA Defect#1112
    /** MODE : PARTIAL_CANCEL */
    private static final String PARTIAL_CANCEL = "2";

    // add end 2015/12/18 CSA Defect#1112

    // START 2017/08/28 K.Kojima [QC#20643,ADD]
    /** Format System Time stamp **/
    String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    // END 2017/08/28 K.Kojima [QC#20643,ADD]

    // Add Start 2017/09/08 QC#19242
    /** VAR_CHAR_CONST_NM: OUT_TRTY_SVC_BR_CD */
    public static final String OUT_TRTY_SVC_BR_CD = "OUT_TRTY_SVC_BR_CD";

    /** Out of Territory Service Branch Code */
    private String outTrtySvcBrCd = null;

    // Add End 2017/09/08 QC#19242

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** String : "," */
    private static final String STR_COMMA = ",";

    /** VAR_CHAR_CONST_NM: CLICK_SEND_EXCL_CALL_TP */
    private static final String CLICK_SEND_EXCL_CALL_TP = "CLICK_SEND_EXCL_CALL_TP";

    /** Click send exclusion call type list */
    private String[] clickSendExclCallTpList = null;
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    /** ssmBatClnt */
    private S21SsmBatchClient ssmBatClnt;

    /**
     * NSZC045001
     */
    public NSZC045001() {
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC045001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(List<NSZC045001PMsg> pMsgList, ONBATCH_TYPE onBatTp) {
        for (NSZC045001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC045001PMsg
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(NSZC045001PMsg pMsg, ONBATCH_TYPE onBatTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg, new NSZC045001ApiMsgIdMgr());
        execute(msgMap, onBatTp);
        msgMap.flush();
    }

    private void execute(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        if (ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {

            String xxModeCd = pMsg.xxModeCd.getValue();

            if (PROCESS_MODE_ADD_TASK.equals(xxModeCd) || PROCESS_MODE_CALLAVOID_DISPTTECH.equals(xxModeCd)) {

                addTask(msgMap, onBatTp);

            } else if (PROCESS_MODE_CANCEL_TASK.equals(xxModeCd)) {

                cancTask(msgMap, onBatTp);

            } else if (PROCESS_MODE_FOLLOWUP_TASK.equals(xxModeCd)) {

                followUpTask(msgMap, onBatTp);

            } else {

                msgMap.addXxMsgId(NSZM0039E);
            }
        } else {

            msgMap.addXxMsgId(NSZM0003E);
        }

        // add start 2016/06/16 CSA Defect#9677
        if (hasErrMsg(msgMap)) {
            return;
        }
        // Call Send Task Info API To Click
        callSendTaskInfoApi(msgMap, pMsg, onBatTp);
        // add end 2016/06/16 CSA Defect#9677
    }

    /**
     * Add Task
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void addTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        vldAddTask(msgMap, onBatTp);

        if (hasErrMsg(msgMap)) {
            return;
        }

        procAddTask(msgMap, onBatTp);

    }

    /**
     * Validate "Add Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void vldAddTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
            msgMap.addXxMsgId(NSZM0291E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.userId)) {
            msgMap.addXxMsgId(NSZM0293E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.bypsPrfTechFlg)) {
            msgMap.addXxMsgId(NSZM0582E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
            msgMap.addXxMsgId(NSZM0049E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
            msgMap.addXxMsgId(NSZM0053E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
            msgMap.addXxMsgId(NSZM0054E);
        }

        if (pMsg.xxSvcTaskList.getValidCount() <= 0) {
            msgMap.addXxMsgId(NSZM0302E);
        }

        if (hasErrMsg(msgMap)) {
            return;
        }

        SVC_TASK_STSTMsg fsrStsTMsg = getFsrSts(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrStsTMsg == null) {
            msgMap.addXxMsgId(NSZM0584E);
        } else if (!ZYPConstant.FLG_ON_Y.equals(fsrStsTMsg.taskAddEnblFlg.getValue())) {
            msgMap.addXxMsgId(NSZM0154E);
        }
    }

    private SVC_TASK_STSTMsg getFsrSts(String glblCmpyCd, String fsrNum) {
        FSRTMsg tMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, fsrNum);
        FSRTMsg fsrTMsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (fsrTMsg == null) {
            return null;
        }
        return (SVC_TASK_STSTMsg) ZYPCodeDataUtil.findByCode("SVC_TASK_STS", glblCmpyCd, fsrTMsg.fsrStsCd.getValue());
    }

    /**
     * Process "Add Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void procAddTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC002001PMsg nszc002001PMsg = buildNSZC002001PMsgForAddTask(msgMap);

        if (hasErrMsg(msgMap)) {
            return;
        }

        new NSZC002001().execute(nszc002001PMsg, onBatTp);

        rcvMsgFromApi(msgMap, nszc002001PMsg);

        if (hasErrMsg(msgMap)) {
            return;
        }

        rcvPrmFromApi(msgMap, nszc002001PMsg);

        // add start 2016/03/04 CSA Defect#2697
        boolean isCrHld = false;

        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();
        if (PROCESS_MODE_ADD_TASK.equals(pMsg.xxModeCd.getValue())) {
            // START 2018/11/08 S.Kitamura [QC#28930,MOD]
            // if (isCreditCheckTarget(pMsg)) {
            // NSZC037001PMsg nszc037001PMsg =
            // buildNSZC037001PMsgForAddTask(msgMap);
            //
            // if (hasErrMsg(msgMap)) {
            // return;
            // }
            //
            // new NSZC037001().execute(nszc037001PMsg, onBatTp);
            // rcvMsgFromApi(msgMap, nszc037001PMsg);
            //
            // if (hasErrMsg(msgMap)) {
            // return;
            // }
            // isCrHld = checkCreditHold(nszc037001PMsg);
            // }
            String fstTskVisitStsCd = getFirstTaskVisitStatusCode(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
            if (ZYPCommonFunc.hasValue(fstTskVisitStsCd) && SVC_TASK_STS.CREDIT_HOLD.equals(fstTskVisitStsCd)) {
                isCrHld = true;
            }
            // END 2018/11/08 S.Kitamura [QC#28930,MOD]
        }
        // add end 2016/03/04 CSA Defect#2697

        // mod start 2016/03/04 CSA Defect#2697
        NSZC004001PMsg nszc004001PMsg = buildNSZC004001PMsgForAddTask(msgMap, isCrHld, isPndIstl(pMsg));
        // mod end 2016/03/04 CSA Defect#2697

        if (hasErrMsg(msgMap)) {
            return;
        }

        new NSZC004001().execute(nszc004001PMsg, onBatTp);

        rcvMsgFromApi(msgMap, nszc004001PMsg);

        if (hasErrMsg(msgMap)) {
            return;
        }

        rcvPrmFromApi(msgMap, nszc004001PMsg);

        //moved to getAccessPermit method
        // START 2021/10/25 R.Jabal [QC#59327, DEL]
//        // START 2021/10/06 R.Jabal [QC#59183, ADD]
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        paramMap.put("svcTaskNum", pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
//        List<Map<String, Object>> accessPermitsList = ssmBatClnt.queryObjectList("getAccessPermits", paramMap);
//
//        if (accessPermitsList != null && accessPermitsList.size() > 0) {
//            StringBuilder svcCmntTxt = new StringBuilder();
//            svcCmntTxt.append("Task: ").append(pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue()).append("</p>");
//            svcCmntTxt.append("Required Access Permits:</p>");
//
//            for (Map<String, Object> accessPermitsMap : accessPermitsList) {
//                String svcAccsPmitNum = (String) accessPermitsMap.get("SVC_ACCS_PMIT_NUM");
//                String svcAccsPmitDescTxt = (String) accessPermitsMap.get("SVC_ACCS_PMIT_DESC_TXT");
//                if (ZYPCommonFunc.hasValue(svcAccsPmitNum) || ZYPCommonFunc.hasValue(svcAccsPmitDescTxt)) {
//                    svcCmntTxt.append(svcAccsPmitNum).append("-").append(svcAccsPmitDescTxt).append("</p>");
//                }
//            }
//
//            int svcMemoListIndex = pMsg.xxSvcMemoList.getValidCount();
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcMemoList.no(svcMemoListIndex).svcMemoTpCd, SVC_MEMO_TP.DISPATCH);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcMemoList.no(svcMemoListIndex).svcCmntTxt, svcCmntTxt.toString());
//            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcMemoList.no(svcMemoListIndex).svcMemoRsnCd, "");
//            pMsg.xxSvcMemoList.setValidCount(svcMemoListIndex + 1);
//
//            msgMap.setPmsg(pMsg);
//        }
//        // END 2021/10/06 R.Jabal [QC#59183, ADD]
        // END 2021/10/25 R.Jabal [QC#59327, DEL]

        // START 2021/10/21 R.Jabal [QC#59327, ADD]
        getAccessPermit(msgMap);
        // END 2021/10/21 R.Jabal [QC#59327, ADD]

        // START 2017/08/28 K.Kojima [QC#20643,ADD]
        createSvcMemo(msgMap);

        if (hasErrMsg(msgMap)) {
            return;
        }
        // END 2017/08/28 K.Kojima [QC#20643,ADD]

    }

    // START 2021/10/21 R.Jabal [QC#59327, ADD]
    private void getAccessPermit(S21ApiMessageMap msgMap) {
        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
        List<Map<String, Object>> accessPermitsList = ssmBatClnt.queryObjectList("getAccessPermits", paramMap);

        if (accessPermitsList != null && accessPermitsList.size() > 0) {
            StringBuilder svcCmntTxt = new StringBuilder();
            svcCmntTxt.append("Task: ").append(pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue()).append("</p>");
            svcCmntTxt.append("Required Access Permits:</p>");

            for (Map<String, Object> accessPermitsMap : accessPermitsList) {
                String svcAccsPmitNum = (String) accessPermitsMap.get("SVC_ACCS_PMIT_NUM");
                String svcAccsPmitDescTxt = (String) accessPermitsMap.get("SVC_ACCS_PMIT_DESC_TXT");
                if (ZYPCommonFunc.hasValue(svcAccsPmitNum) || ZYPCommonFunc.hasValue(svcAccsPmitDescTxt)) {
                    svcCmntTxt.append(svcAccsPmitNum).append("-").append(svcAccsPmitDescTxt).append("</p>");
                }
            }

            int svcMemoListIndex = pMsg.xxSvcMemoList.getValidCount();
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcMemoList.no(svcMemoListIndex).svcMemoTpCd, SVC_MEMO_TP.DISPATCH);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcMemoList.no(svcMemoListIndex).svcCmntTxt, svcCmntTxt.toString());
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcMemoList.no(svcMemoListIndex).svcMemoRsnCd, "");
            pMsg.xxSvcMemoList.setValidCount(svcMemoListIndex + 1);

            msgMap.setPmsg(pMsg);
        }
    }
    // END 2021/10/21 R.Jabal [QC#59327, ADD]

    /**
     * Get Creation / Update API parameter for "Add Task"
     * @param msgMap S21ApiMessageMap
     * @return NSZC002001PMsg
     */
    private NSZC002001PMsg buildNSZC002001PMsgForAddTask(S21ApiMessageMap msgMap) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        NSZC002001PMsg apiPMsg = new NSZC002001PMsg();

        String firstProdCtrlCd = prmPMsg.firstProdCtrlCd.getValue();
        if (!ZYPCommonFunc.hasValue(firstProdCtrlCd)) {

            if (!ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0074E);
                return null;
            }

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return null;
            }

            MDSETMsg mdseTMsg = getMdse(prmPMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.mdseCd.getValue());
            if (mdseTMsg == null) {
                msgMap.addXxMsgId(NSZM0583E);
                return null;
            } else {
                firstProdCtrlCd = mdseTMsg.firstProdCtrlCd.getValue();
            }
        }

        String custMachCtrlNum = prmPMsg.custMachCtrlNum.getValue();
        if (!ZYPCommonFunc.hasValue(custMachCtrlNum)) {

            if (ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {

                SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());

                if (svcMachMstrTMsg == null) {
                    msgMap.addXxMsgId(NSZM0006E);
                    return null;
                }
                custMachCtrlNum = svcMachMstrTMsg.custMachCtrlNum.getValue();
            }
        }

        String serNum = prmPMsg.serNum.getValue();
        String mdseCd = prmPMsg.mdseCd.getValue();
        if (!ZYPCommonFunc.hasValue(serNum) || !ZYPCommonFunc.hasValue(mdseCd)) {

            if (ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {

                SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());

                if (svcMachMstrTMsg == null) {
                    msgMap.addXxMsgId(NSZM0006E);
                    return null;
                }
                serNum = svcMachMstrTMsg.serNum.getValue();
                mdseCd = svcMachMstrTMsg.mdseCd.getValue();
            }
        }

        String mdlNm = prmPMsg.mdlNm.getValue();
        BigDecimal mdlId = null;

        if (ZYPCommonFunc.hasValue(mdlNm)) {
            MDL_NMTMsgArray mdlNmTMsgArray = getMdlNmByMdlNm(prmPMsg.glblCmpyCd.getValue(), mdlNm);
            if (mdlNmTMsgArray.length() == 0) {
                msgMap.addXxMsgId(NSZM0090E);
                return null;
            } else {
                mdlId = mdlNmTMsgArray.no(0).t_MdlId.getValue();
            }
        } else {
            DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, prmPMsg.glblCmpyCd.getValue(), prmPMsg.dsSvcCallTpCd.getValue());
            if (dsSvcCallTpTMsg == null) {
                msgMap.addXxMsgId(NSZM0549E);
                return null;
            }
            if (!ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0074E);
                return null;
            }
            // mod start 2016/06/15 CSA Defect#8899
            // if
            // (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.svcIstlReqFlg.getValue()))
            // {
            // BigDecimal svcConfigMstrCnt =
            // cntSvcConfigMstrBySvcMachMstrPk(prmPMsg.glblCmpyCd.getValue(),
            // prmPMsg.svcMachMstrPk.getValue());
            // if (svcConfigMstrCnt.compareTo(BigDecimal.ONE) >= 0) {
            // Map<String, Object> mdlNmMap =
            // getMdlNmMapFromSvcConfigMstr(prmPMsg.glblCmpyCd.getValue(),
            // prmPMsg.svcMachMstrPk.getValue());
            // if (mdlNmMap == null) {
            // msgMap.addXxMsgId(NSZM0090E);
            // return null;
            // }
            // mdlNm = (String) mdlNmMap.get("T_MDL_NM");
            // mdlId = (BigDecimal) mdlNmMap.get("T_MDL_ID");
            //
            // } else {
            //
            // if (!ZYPCommonFunc.hasValue(prmPMsg.soNum)) {
            // msgMap.addXxMsgId(NSZM0027E);
            // return null;
            // }
            //
            // Map<String, Object> mdlNmMap =
            // getMdlNmMapFromDsMdlConfig(prmPMsg.glblCmpyCd.getValue(),
            // prmPMsg.soNum.getValue());
            //
            // if (mdlNmMap == null) {
            // msgMap.addXxMsgId(NSZM0090E);
            // return null;
            // }
            // mdlNm = (String) mdlNmMap.get("T_MDL_NM");
            // mdlId = (BigDecimal) mdlNmMap.get("T_MDL_ID");
            // }
            //
            // } else {

            Map<String, Object> mdlNmMap = getMdlNmMapFromSvcConfigMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
            if (mdlNmMap == null) {
                msgMap.addXxMsgId(NSZM0090E);
                return null;
            }
            mdlNm = (String) mdlNmMap.get("T_MDL_NM");
            mdlId = (BigDecimal) mdlNmMap.get("T_MDL_ID");
            // }
            // mod end 2016/06/15 CSA Defect#8899
        }

        String mdlGrpNm = prmPMsg.mdlGrpNm.getValue();
        if (!ZYPCommonFunc.hasValue(mdlGrpNm)) {
            if (!ZYPCommonFunc.hasValue(mdlId)) {
                msgMap.addXxMsgId(NSZM0090E);
                return null;
            }
            Map<String, Object> mdlGrpMap = getDsMdlGrpMapByMdlId(prmPMsg.glblCmpyCd.getValue(), mdlId);
            if (mdlGrpMap == null) {
                msgMap.addXxMsgId(NSZM0585E);
                return null;
            }
            mdlGrpNm = (String) mdlGrpMap.get("MDL_GRP_NM");
        }

        String firstSvcTaskFlg = prmPMsg.firstSvcTaskFlg.getValue();
        if (!ZYPCommonFunc.hasValue(firstSvcTaskFlg)) {
            if (firstSvcTask(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue())) {
                firstSvcTaskFlg = ZYPConstant.FLG_ON_Y;
            } else {
                firstSvcTaskFlg = ZYPConstant.FLG_OFF_N;
            }
        }

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, prmPMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(apiPMsg.soNum, prmPMsg.soNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstProdCtrlCd, firstProdCtrlCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallPrtyCd, prmPMsg.xxSvcTaskList.no(0).svcCallPrtyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, prmPMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, custMachCtrlNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdlNm, mdlNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdlGrpNm, mdlGrpNm);
        // START 09/09/2015 T.Tsuchida [NA#Follow up Call,MOD]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustCd,prmPMsg.shipToCustCd);
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
        if (svcMachMstrTMsg != null) {

            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustCd, svcMachMstrTMsg.curLocNum);
        }
        // END 09/09/2015 T.Tsuchida [NA#Follow up Call,MOD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, prmPMsg.custTelNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, prmPMsg.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, prmPMsg.svcCustAttnTxt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, prmPMsg.custEmlAddr);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, prmPMsg.custPoNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, prmPMsg.custPoDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, prmPMsg.dsSvcCallTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdDt, prmPMsg.svcTaskSchdDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdTm, prmPMsg.svcTaskSchdTm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdByUsrId, prmPMsg.svcTaskSchdByUsrId);

        String svcBillTpCd = null;

        CovInfoBean covInfo = NSXC002001GetCovInfo.getCovInfo(prmPMsg.glblCmpyCd.getValue(), null, prmPMsg.svcMachMstrPk.getValue());
        if (covInfo != null) {

            svcBillTpCd = covInfo.getSvcBillTpCd();

        } else {

            DS_SVC_CALL_TPTMsg svcCallTpTMsg = new DS_SVC_CALL_TPTMsg();
            svcCallTpTMsg.glblCmpyCd.setValue(apiPMsg.glblCmpyCd.getValue());
            svcCallTpTMsg.dsSvcCallTpCd.setValue(apiPMsg.dsSvcCallTpCd.getValue());
            svcCallTpTMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(svcCallTpTMsg);

            if (svcCallTpTMsg != null) {

                svcBillTpCd = svcCallTpTMsg.svcBillTpCd.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, svcBillTpCd);

        ZYPEZDItemValueSetter.setValue(apiPMsg.techCd, prmPMsg.xxSvcTaskList.no(0).techCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, prmPMsg.svcTaskRcvDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, prmPMsg.svcTaskRcvTm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, firstSvcTaskFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrEventExeUsrId, prmPMsg.userId);

        StringBuilder sb = new StringBuilder();
        sb.append(prmPMsg.svcTaskRcvDt.getValue());
        sb.append(prmPMsg.svcTaskRcvTm.getValue());
        sb.append(TS_POSTFIX);

        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        Map<String, String> shipInfoMap = getShipToInfo(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue());
        String shipToUpdCustCd = null;
        if (shipInfoMap != null) {
            shipToUpdCustCd = shipInfoMap.get("SHIP_TO_UPD_CUST_CD");
            // START 2022/06/01 [QC#59899-1, ADD]
            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToUpdCustCd, shipToUpdCustCd);
            // END   2022/06/01 [QC#59899-1, ADD]
        }
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]

        if (ZYPCommonFunc.hasValue(prmPMsg.xxSvcTaskList.no(0).futSvcDt)) {

            sb = new StringBuilder();
            sb.append(prmPMsg.xxSvcTaskList.no(0).futSvcDt.getValue());
            if (ZYPCommonFunc.hasValue(prmPMsg.xxSvcTaskList.no(0).futSvcTm)) {
                sb.append(prmPMsg.xxSvcTaskList.no(0).futSvcTm.getValue());
            } else {
                sb.append(TM_START_OFTHEDAY);
            }
            sb.append(TS_POSTFIX);

            // mod Start 2016/06/07 CSA Defect#3727
            if (ZYPConstant.FLG_ON_Y.equals(prmPMsg.xxTmZnConvtFlg.getValue())) {
                // START 2022/04/11 K.Kitachi [QC#59899, DEL]
//                Map<String, String> shipInfoMap = getShipToInfo(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue());
                // END 2022/04/11 K.Kitachi [QC#59899, DEL]
                if (shipInfoMap != null) {
                    SvcTimeZoneInfo tzFutSvcTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, sb.toString(), shipInfoMap.get("CTRY_CD"), shipInfoMap.get("POST_CD"));
                    if (tzFutSvcTs != null) {
                        sb = new StringBuilder();
                        sb.append(tzFutSvcTs.getDateTime());
                    }
                }
            }
        }
        // mod End 2016/06/07 CSA Defect#3727

        // add start 2016/02/10 CSA Defect#3727
        if (!ZYPCommonFunc.hasValue(prmPMsg.xxSvcTaskList.no(0).ovrdFlg) || ZYPConstant.FLG_OFF_N.equals(prmPMsg.xxSvcTaskList.no(0).ovrdFlg.getValue())) {
            String cvtErlStartTs = sb.toString();
            // Mod Start 2017/10/02 QC#21273
            // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//            cvtErlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(prmPMsg.glblCmpyCd.getValue(), BigDecimal.ZERO, prmPMsg.svcMachMstrPk.getValue(), sb.substring(0, 8), sb.substring(8, 14), false, prmPMsg.dsSvcCallTpCd
//                    .getValue());
            cvtErlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(prmPMsg.glblCmpyCd.getValue(), BigDecimal.ZERO, prmPMsg.svcMachMstrPk.getValue(), sb.substring(0, 8), sb.substring(8, 14), false, prmPMsg.dsSvcCallTpCd
                    .getValue(), shipToUpdCustCd);
            // END 2022/04/11 K.Kitachi [QC#59899, MOD]
            // Mod End 2017/10/02 QC#21273
            ZYPEZDItemValueSetter.setValue(apiPMsg.erlStartTs, cvtErlStartTs);
            // add end 2016/02/10 CSA Defect#3727
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.erlStartTs, prmPMsg.xxSvcTaskList.no(0).erlStartTs);
            ZYPEZDItemValueSetter.setValue(apiPMsg.lateStartTs, prmPMsg.xxSvcTaskList.no(0).lateStartTs);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
        }

        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, prmPMsg.svcCustCllrTxt);
        // add Start 2016/02/10 CSA Defect#6020
        ZYPEZDItemValueSetter.setValue(apiPMsg.mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // add end 2016/02/10 CSA Defect#6020

        // add Start 2016/04/12 CSA Defect#6766
        // Mod Start 2018/09/26 QC#28262
        nszc0020DefaultSet(apiPMsg, prmPMsg.fsrNum.getValue(), prmPMsg);
        // nszc0020DefaultSet(apiPMsg, prmPMsg.fsrNum.getValue());
        // Mod End 2018/09/26 QC#28262
        // add end 2016/04/12 CSA Defect#6766

        return apiPMsg;
    }

    // add 2016/06/07 CSA Defect#3727
    private Map<String, String> getShipToInfo(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        return (Map<String, String>) ssmBatClnt.queryObject("getShipToInfo", paramMap);
    }

    /**
     * Get MDSE
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise Code
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdsePrmTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdsePrmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdsePrmTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21ApiTBLAccessor.findByKey(mdsePrmTMsg);
    }

    /**
     * Get SVC_MACH_MSTR
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Pk
     * @return MDSETMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg machPrmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(machPrmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machPrmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(machPrmTMsg);
    }

    /**
     * Get MDL_NM
     * @param glblCmpyCd Global Company Code
     * @param mdlNm Model Name
     * @return MDL_NMTMsgArray
     */
    private MDL_NMTMsgArray getMdlNmByMdlNm(String glblCmpyCd, String mdlNm) {
        MDL_NMTMsg tMsg = new MDL_NMTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("t_MdlNm01", mdlNm);
        return (MDL_NMTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * Count SVC_CONFIG_MSTR
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Pk
     * @return BigDecimal
     */
    private BigDecimal cntSvcConfigMstrBySvcMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return (BigDecimal) ssmBatClnt.queryObject("cntSvcConfigMstrBySvcMachMstrPk", ssmPrm);
    }

    /**
     * Get Model information
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master Pk
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getMdlNmMapFromSvcConfigMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        Map<String, Object> mapMdlNm = (Map<String, Object>) ssmBatClnt.queryObject("getMdlNmMapFromSvcConfigMstr", ssmPrm);
        if (mapMdlNm != null) {
            return mapMdlNm;
        }
        // add start 2016/10/05 CSA Defect#14145
        ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        mapMdlNm = (Map<String, Object>) ssmBatClnt.queryObject("getMdlNmMapFromSvcConfigMstrDtl", ssmPrm);
        if (mapMdlNm != null) {
            return mapMdlNm;
        }
        // add end 2016/10/05 CSA Defect#14145
        return (Map<String, Object>) ssmBatClnt.queryObject("getMdlNmMapFromCpo", ssmPrm);
    }

    /**
     * Get Model information
     * @param glblCmpyCd Global Company Code
     * @param soNum Shipping Order Number
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getMdlNmMapFromDsMdlConfig(String glblCmpyCd, String soNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("soNum", soNum);
        return (Map<String, Object>) ssmBatClnt.queryObject("getMdlNmMapFromDsMdlConfig", ssmPrm);
    }

    /**
     * Get DS Model Group information
     * @param glblCmpyCd
     * @param mdlId
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getDsMdlGrpMapByMdlId(String glblCmpyCd, BigDecimal mdlId) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlId", mdlId);
        return (Map<String, Object>) ssmBatClnt.queryObject("getDsMdlGrpMapByMdlId", ssmPrm);
    }

    /**
     * Determine if there exists any tasks for the FSR
     * @param glblCmpyCd Global Company Code
     * @param fsrNum FSR Number
     * @return true if exists, otherwise false
     */
    private boolean firstSvcTask(String glblCmpyCd, String fsrNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("fsrNum", fsrNum);
        BigDecimal svcTaskCnt = (BigDecimal) ssmBatClnt.queryObject("cntSvcTaskByFsrNum", ssmPrm);
        // Mod Start 2018/12/21 K.Fujimoto QC#29724
        // return svcTaskCnt.compareTo(BigDecimal.ONE) <= 0;
        return svcTaskCnt.compareTo(BigDecimal.ONE) < 0;
        // Mod End 2018/12/21 K.Fujimoto QC#29724
    }

    /**
     * Receive generated keys from API
     * @param msgMap
     * @param apiPMsg
     */
    private void rcvPrmFromApi(S21ApiMessageMap msgMap, NSZC002001PMsg apiPMsg) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();

        prmPMsg.xxSvcTaskList.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(prmPMsg.xxSvcTaskList.no(0).svcTaskNum, apiPMsg.svcTaskNum);
        // add start 2016/03/04 CSA Defect#2697
        ZYPEZDItemValueSetter.setValue(prmPMsg.svcBillTpCd, apiPMsg.svcBillTpCd);
        // add end 2016/03/04 CSA Defect#2697

    }

    /**
     * Get Continuous Call API parameter for "Add Task"
     * @param msgMap S21ApiMessageMap
     * @return NSZC004001PMsg
     */
    private NSZC004001PMsg buildNSZC004001PMsgForAddTask(S21ApiMessageMap msgMap, boolean isCrHld, boolean isPndIstl) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        NSZC004001PMsg apiPMsg = new NSZC004001PMsg();
        // Add Start 2017/09/08 QC#19242
        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD, prmPMsg.glblCmpyCd.getValue());
        // Add End 2017/09/08 QC#19242
        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, prmPMsg.glblCmpyCd.getValue());
        this.clickSendExclCallTpList = clickSendExclCallTp.split(STR_COMMA);
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        String fsrVisitNum = prmPMsg.fsrVisitNum.getValue();
        if (!ZYPCommonFunc.hasValue(fsrVisitNum)) {
            FSR_VISITTMsgArray fsrVisitTMsgArray = getLtstFsrVisit(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue());
            if (fsrVisitTMsgArray.length() == 1) {
                fsrVisitNum = fsrVisitTMsgArray.no(0).fsrVisitNum.getValue();
            } else {
                msgMap.addXxMsgId(NSZM0182E);
                return null;
            }
        }

        // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//        String fsrVisitStsCd = getFsrVisitStsCd(msgMap, SVC_TASK_STS.TBO);
        String fsrVisitStsCd;
        // END 2019/02/15 K.Kitachi [QC#30165, MOD]

        // add start 2016/03/04 CSA Defect#2697
        if (isCrHld) {
            fsrVisitStsCd = SVC_TASK_STS.CREDIT_HOLD;
        } else if (isPndIstl) {
            // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//            fsrVisitStsCd = SVC_TASK_STS.PENDING_INSTALL;
            fsrVisitStsCd = getFsrVisitStsCd(msgMap, SVC_TASK_STS.PENDING_INSTALL);
            // END 2019/02/15 K.Kitachi [QC#30165, MOD]
        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        } else {
            fsrVisitStsCd = getFsrVisitStsCd(msgMap, SVC_TASK_STS.TBO);
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]
        }
        // add end 2016/03/04 CSA Defect#2697

        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitStsCd, fsrVisitStsCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, prmPMsg.glblCmpyCd);

        if (PROCESS_MODE_ADD_TASK.equals(prmPMsg.xxModeCd.getValue())) {

            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC004001Constant.PROCESS_MODE_ADD_TASK);

        } else if (PROCESS_MODE_CALLAVOID_DISPTTECH.equals(prmPMsg.xxModeCd.getValue())) {

            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC004001Constant.PROCESS_MODE_CALLAVOID_DISPTTECH);

        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, prmPMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitNum, fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrEventExeUsrId, prmPMsg.userId);
        // NA#ASCC CLICK BugFix MOD Start
        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcChrgContFlg,
        // prmPMsg.svcChrgContFlg);
        apiPMsg.svcChrgContFlg.setValue(ZYPConstant.FLG_ON_Y);
        // NA#ASCC CLICK BugFix MOD End
        ZYPEZDItemValueSetter.setValue(apiPMsg.techCd, prmPMsg.xxSvcTaskList.no(0).techCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, prmPMsg.bypsPrfTechFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.techSchdFromDt, prmPMsg.xxSvcTaskList.no(0).techSchdFromDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.techSchdFromTm, prmPMsg.xxSvcTaskList.no(0).techSchdFromTm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.schdDisptEmlFlg, prmPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg);

        apiPMsg.xxSvcTaskNumList.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskNumList.no(0).svcTaskNum, prmPMsg.xxSvcTaskList.no(0).svcTaskNum);

        // NA#ASCC CLICK BugFix ADD Start
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcAsgTpCd, prmPMsg.xxSvcTaskList.no(0).svcAsgTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcAsgTechCd, prmPMsg.xxSvcTaskList.no(0).svcAsgTechCd);

        // mod Start 2016/06/07 CSA Defect#3727
        String futSvcDt = prmPMsg.xxSvcTaskList.no(0).futSvcDt.getValue();
        String futSvcTm = prmPMsg.xxSvcTaskList.no(0).futSvcTm.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(prmPMsg.xxTmZnConvtFlg.getValue()) && ZYPCommonFunc.hasValue(futSvcDt)) {

            StringBuilder sb = new StringBuilder();
            sb.append(futSvcDt);
            if (ZYPCommonFunc.hasValue(futSvcTm)) {
                sb.append(futSvcTm);
            } else {
                sb.append(TM_START_OFTHEDAY);
            }
            Map<String, String> shipInfoMap = getShipToInfo(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue());
            if (shipInfoMap != null) {
                SvcTimeZoneInfo tzFutSvcTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, sb.toString(), shipInfoMap.get("CTRY_CD"), shipInfoMap.get("POST_CD"));
                if (tzFutSvcTs != null) {
                    futSvcDt = tzFutSvcTs.getDateTime().substring(0, 8);
                    futSvcTm = tzFutSvcTs.getDateTime().substring(8, 14);
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.futSvcDt, futSvcDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.futSvcTm, futSvcTm);
        // mod End 2016/06/07 CSA Defect#3727
        // NA#ASCC CLICK BugFix ADD End

        // START 2018/08/09 K.Kojima [QC#27399,ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.mblIntfcFlg, prmPMsg.xxSvcTaskList.no(0).mblIntfcFlg);
        // END 2018/08/09 K.Kojima [QC#27399,ADD]

        return apiPMsg;
    }

    private String getFsrVisitStsCd(S21ApiMessageMap msgMap, String defVisitStsCd) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        // Add Start 2017/09/08 QC#19242
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, prmPMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, prmPMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
        svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(svcTaskTMsg);
        // Add End 2017/09/08 QC#19242

        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        if (svcTaskTMsg != null && isClickSendExclCall(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return SVC_TASK_STS.NOTIFY_VENDOR;
        }
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        if (ZYPCommonFunc.hasValue(prmPMsg.xxSvcTaskList.no(0).techCd)) {

            S21_PSNTMsg srchS21PsnTMsg = new S21_PSNTMsg();
            srchS21PsnTMsg.glblCmpyCd.setValue(prmPMsg.glblCmpyCd.getValue());
            srchS21PsnTMsg.psnCd.setValue(prmPMsg.xxSvcTaskList.no(0).techCd.getValue());

            S21_PSNTMsg s21PsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(srchS21PsnTMsg);
            if (s21PsnTMsg == null) {

                msgMap.addXxMsgId(NSZM0602E);
                return null;

            } else if (ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {

                msgMap.addXxMsgId(NSZM0603E);
                return null;
            }

            if (!PSN_TP.EMPLOYEE.equals((String) s21PsnTMsg.psnTpCd.getValue())) {
                // mod start 2016/02/26 CSA Defect#4113
                // return SVC_TASK_STS.OPEN;
                return SVC_TASK_STS.NOTIFY_VENDOR;
                // mod start 2016/02/26 CSA Defect#4113
            }
        }
        // Add Start 2017/09/08 QC#19242
        if (isOutTrtySvcBr(svcTaskTMsg)) {
            return SVC_TASK_STS.NOTIFY_VENDOR;
        }
        // Add End 2017/09/08 QC#19242
        return defVisitStsCd;
    }

    /**
     * Get latest FSR_VISIT
     * @param glblCmpyCd Global Company Code
     * @param fsrNum FSR Number
     * @return FSR_VISITTMsgArray
     */
    private FSR_VISITTMsgArray getLtstFsrVisit(String glblCmpyCd, String fsrNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("fsrVisitLtstFlg01", ZYPConstant.FLG_ON_Y);
        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private FSR_VISITTMsg getFsrVisitBySvcTaskNum(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        FSR_VISITTMsgArray outArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (outArray.getValidCount() > 0) {
            return outArray.no(0);
        }
        return null;
    }

    /**
     * Receive generated keys from API.
     * @param msgMap
     * @param apiPMsg
     */
    private void rcvPrmFromApi(S21ApiMessageMap msgMap, NSZC004001PMsg apiPMsg) {

    }

    /**
     * "Cancel Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void cancTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        vldCancTask(msgMap, onBatTp);

        if (hasErrMsg(msgMap)) {
            return;
        }

        procCancTask(msgMap, onBatTp);

    }

    /**
     * Validate "Cancel Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void vldCancTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
            msgMap.addXxMsgId(NSZM0291E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.userId)) {
            msgMap.addXxMsgId(NSZM0293E);
        }

        if (pMsg.xxSvcTaskList.getValidCount() <= 0) {
            msgMap.addXxMsgId(NSZM0302E);
        }

        for (int i = 0; i < pMsg.xxSvcTaskList.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).svcTaskNum)) {
                msgMap.addXxMsgId(NSZM0302E);

                // add start2016/04/21 CSA Defect#7320
            } else if (ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
                FSR_VISITTMsg fsrVisitTMsg = getFsrVisitBySvcTaskNum(pMsg.glblCmpyCd.getValue(), pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue());
                if (fsrVisitTMsg != null && !checkFsrVisitStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    msgMap.addXxMsgId(NSZM0814E);
                }
            }
            // add end 2016/04/21 CSA Defect#7320
        }
    }

    // add 2016/04/21 CSA Defect#7320
    private FSR_VISITTMsg getFsrVisitBySvcTaskNum(String glblCmpyCd, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        FSR_VISITTMsgArray fsrVisitTMsgList = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (fsrVisitTMsgList.getValidCount() <= 0) {
            return null;
        }
        return fsrVisitTMsgList.no(0);
    }

    // add 2016/04/21 CSA Defect#7320
    private boolean checkFsrVisitStsCd(String fsrVisitStsCd) {
        if (SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.ARRIVED.equals(fsrVisitStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.IN_ROUTE.equals(fsrVisitStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.DEBRIEF_ERROR.equals(fsrVisitStsCd)) {
            return false;
        }
        return true;
    }

    /**
     * Process "Cancel Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void procCancTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        // del start 2017/01/05 CSA Defect#16942
        // mod 2016/04/21 CSA Defect#7320
        // if (needCancFsr(pMsg)) {
        // msgMap.addXxMsgId(NSZM0966E);
        // // mod 2016/04/21 CSA Defect#7320
        // return;
        // }
        // del end 2017/01/05 CSA Defect#16942

        // mod start 2016/09/09 CSA Defect#14206
        // NSZC003001PMsg nszc003001PMsg =
        // buildNSZC003001PMsgForCancTask(msgMap);
        //
        // if (hasErrMsg(msgMap)) {
        // return;
        // }
        //
        // new NSZC003001().execute(nszc003001PMsg, onBatTp);
        //
        // rcvMsgFromApi(msgMap, nszc003001PMsg);
        //
        // if (hasErrMsg(msgMap)) {
        // return;
        // }

        List<NSZC002001PMsg> nszc002001PMsgList = buildNSZC002001PMsgForCancTask(msgMap);

        if (hasErrMsg(msgMap)) {
            return;
        }

        for (NSZC002001PMsg nszc002001PMsg : nszc002001PMsgList) {

            new NSZC002001().execute(nszc002001PMsg, onBatTp);

            rcvMsgFromApi(msgMap, nszc002001PMsg);

            if (hasErrMsg(msgMap)) {
                return;
            }
        }

        NSZC003001PMsg nszc003001PMsg = buildNSZC003001PMsgForCancTask(msgMap);

        if (hasErrMsg(msgMap)) {
            return;
        }

        new NSZC003001().execute(nszc003001PMsg, onBatTp);

        rcvMsgFromApi(msgMap, nszc003001PMsg);

        if (hasErrMsg(msgMap)) {
            return;
        }
        // mod end 2016/09/09 CSA Defect#14206
        // Add Start 2018/06/25 QC#25608
        cancelWF(msgMap, PROCESS_DEF_NSWP0004, pMsg.fsrNum.getValue());
        // Add END 2018/06/25 QC#25608

    }

    /**
     * Returns true if this FSR contains tasks except parameter's
     * tasks.
     * @param msgMap S21ApiMessageMap
     * @return true if FSR contains other tasks
     */
    // mod start 2017/01/05 CSA Defect#16942
    // private boolean needCancFsr(NSZC045001PMsg pMsg) {
    private boolean existsOtherTasks(NSZC045001PMsg pMsg) {
        // mod end 2017/01/05 CSA Defect#16942
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmPrm.put("fsrNum", pMsg.fsrNum.getValue());
        // mod start 2016/04/21 CSA Defect#7320
        // ssmPrm.put("fsrVisitStsCdList",
        // Arrays.asList(SVC_TASK_STS.COMPLETED,
        // SVC_TASK_STS.CANCELLED, SVC_TASK_STS.CLOSED));
        List<String> svcTaskNumList = new ArrayList<String>();
        for (int i = 0; i < pMsg.xxSvcTaskList.getValidCount(); i++) {
            svcTaskNumList.add(pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue());
        }
        ssmPrm.put("svcTaskNumList", svcTaskNumList);
        int fsrVisitCnt = (Integer) ssmBatClnt.queryObject("cntFsrVisitByFsrNum", ssmPrm);
        return fsrVisitCnt > 0;
        // mod end 2016/04/21 CSA Defect#7320
    }

    /**
     * Get Schedule / Accept / Dispatch API parameter for
     * "Cancel Task"
     * @param msgMap S21ApiMessageMap
     * @return NSZC003001PMsg
     */
    private NSZC003001PMsg buildNSZC003001PMsgForCancTask(S21ApiMessageMap msgMap) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        NSZC003001PMsg apiPMsg = new NSZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, prmPMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxBizProcTp, NSZC003001Constant.MODE_CANCEL_CALL);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, prmPMsg.fsrNum);

        // add start 2015/12/18 CSA Defect#1112
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, prmPMsg.slsDt);
        // mod start 2017/01/05 CSA Defect#16942
        // ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstTpCd,
        // PARTIAL_CANCEL);
        if (existsOtherTasks(prmPMsg)) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstTpCd, PARTIAL_CANCEL);
        }
        // mod end 2017/01/05 CSA Defect#16942

        for (int i = 0; i < prmPMsg.xxSvcTaskList.getValidCount(); i++) {
            String svcTaskNum = prmPMsg.xxSvcTaskList.no(i).svcTaskNum.getValue();
            String schdDisptEmlFlg = prmPMsg.xxSvcTaskList.no(i).schdDisptEmlFlg.getValue();
            String mblIntfcFlg = prmPMsg.xxSvcTaskList.no(i).mblIntfcFlg.getValue();
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(i).svcTaskNum, svcTaskNum);
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(i).schdDisptEmlFlg, schdDisptEmlFlg);
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(i).mblIntfcFlg, mblIntfcFlg);
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(i).userId, prmPMsg.userId);
        }
        apiPMsg.xxSvcTaskList.setValidCount(prmPMsg.xxSvcTaskList.getValidCount());

        // add end 2015/12/18 CSA Defect#1112

        apiPMsg.svcTaskStsCd.setValue(SVC_TASK_STS.CANCELLED);

        EZDMsg.copy(prmPMsg.xxSvcMemoList, null, apiPMsg.svcMemoList, null);

        return apiPMsg;
    }

    /**
     * Get Creation / Update API parameter for "Cancel Task"
     * @param msgMap S21ApiMessageMap
     * @return NSZC002001PMsg
     */
    private List<NSZC002001PMsg> buildNSZC002001PMsgForCancTask(S21ApiMessageMap msgMap) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();

        List<NSZC002001PMsg> apiPMsgList = new ArrayList<NSZC002001PMsg>();

        for (int i = 0; i < prmPMsg.xxSvcTaskList.getValidCount(); i++) {

            NSZC002001PMsg apiPMsg = new NSZC002001PMsg();
            apiPMsgList.add(apiPMsg);

            SVC_TASKTMsg defTMsg = getSvcTask(prmPMsg.glblCmpyCd.getValue(), prmPMsg.xxSvcTaskList.no(i).svcTaskNum.getValue());

            EZDMsg.copy(defTMsg, null, apiPMsg, null);

            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_CANCEL);
            ZYPEZDItemValueSetter.setValue(apiPMsg.fsrEventExeUsrId, prmPMsg.userId);
            // add Start 2016/04/01 CSA Defect#6020
            ZYPEZDItemValueSetter.setValue(apiPMsg.mblIntfcFlg, prmPMsg.xxSvcTaskList.no(i).mblIntfcFlg);
            // add End 2016/04/01 CSA Defect#6020
        }

        return apiPMsgList;
    }

    /**
     * Get SVC_TASK
     * @param glblCmpyCd Global Company Code
     * @param svcTaskNum Service Task Number
     * @return SVC_TASKTMsg
     */
    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    /**
     * Receive messages from API.
     * @param msgMap S21ApiMessageMap
     * @param apiPMsg API parameter
     */
    private void rcvMsgFromApi(S21ApiMessageMap msgMap, EZDPMsg apiPMsg) {
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
        for (S21ApiMessage msg : msgList) {
            String xxMsgId = msg.getXxMsgid();
            String[] xxMsgPrm = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(xxMsgId, xxMsgPrm);
        }
    }

    /**
     * Determine if the S21ApiMessageMap contains an error message.
     * @param msgMap S21ApiMessageMap
     * @return true if S21ApiMessageMap contains an error message.
     */
    private boolean hasErrMsg(S21ApiMessageMap msgMap) {
        NSZC045001ApiMsgIdMgr msgMgr = (NSZC045001ApiMsgIdMgr) msgMap.getMsgMgr();
        return msgMgr.isXxErrMsgId();
    }

    // START 09/09/2015 T.Tsuchida [NA#Follow up Call,ADD]
    /**
     * Follow Up Task
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void followUpTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        vldFollowUpTask(msgMap, onBatTp);

        if (hasErrMsg(msgMap)) {
            return;
        }

        procFollowUpTask(msgMap, onBatTp);

    }

    /**
     * Validate "Follow Up Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void vldFollowUpTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
            msgMap.addXxMsgId(NSZM0291E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.userId)) {
            msgMap.addXxMsgId(NSZM0293E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.bypsPrfTechFlg)) {
            msgMap.addXxMsgId(NSZM0582E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
            msgMap.addXxMsgId(NSZM0049E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
            msgMap.addXxMsgId(NSZM0053E);
        }

        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
            msgMap.addXxMsgId(NSZM0054E);
        }

        if (pMsg.xxSvcTaskList.getValidCount() <= 0) {
            msgMap.addXxMsgId(NSZM0302E);
        }

        // for (int i = 0; i < pMsg.xxSvcTaskList.getValidCount();
        // i++) {
        //
        // if
        // (!ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).svcCallPrtyCd))
        // {
        // msgMap.addXxMsgId(NSZM0043E);
        // }
        //
        // }

        if (hasErrMsg(msgMap)) {
            return;
        }

        SVC_TASK_STSTMsg fsrStsTMsg = getFsrSts(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrStsTMsg == null) {
            msgMap.addXxMsgId(NSZM0584E);
        } else if (!ZYPConstant.FLG_ON_Y.equals(fsrStsTMsg.taskAddEnblFlg.getValue())) {
            msgMap.addXxMsgId(NSZM0154E);
        }
    }

    /**
     * Process "Follow Up Task"
     * @param msgMap S21ApiMessageMap
     * @param onBatTp ONBATCH_TYPE
     */
    private void procFollowUpTask(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatTp) {

        NSXC001001GetFollUpInfoBean follUpBean = new NSXC001001GetFollUpInfoBean();

        NSZC002001PMsg nszc002001PMsg = buildNSZC002001PMsgForFollowUpTask(msgMap, follUpBean);

        if (hasErrMsg(msgMap)) {
            return;
        }

        new NSZC002001().execute(nszc002001PMsg, onBatTp);

        rcvMsgFromApi(msgMap, nszc002001PMsg);

        if (hasErrMsg(msgMap)) {
            return;
        }

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        String preSvcTaskNum = prmPMsg.xxSvcTaskList.no(0).svcTaskNum.getValue();

        rcvPrmFromApi(msgMap, nszc002001PMsg);

        NSZC004001PMsg nszc004001PMsg = buildNSZC004001PMsgForFollowUpTask(msgMap, preSvcTaskNum, follUpBean);

        if (hasErrMsg(msgMap)) {
            return;
        }

        new NSZC004001().execute(nszc004001PMsg, onBatTp);

        rcvMsgFromApi(msgMap, nszc004001PMsg);

        if (hasErrMsg(msgMap)) {
            return;
        }

        rcvPrmFromApi(msgMap, nszc004001PMsg);

        // START 2021/10/21 R.Jabal [QC#59327, ADD]
        getAccessPermit(msgMap);

        createSvcMemo(msgMap);

        if (hasErrMsg(msgMap)) {
            return;
        }
        // END 2021/10/21 R.Jabal [QC#59327, ADD]

    }

    /**
     * Get Creation / Update API parameter for "Follow Up Task"
     * @param msgMap S21ApiMessageMap
     * @param follUpBean
     * @return NSZC002001PMsg
     */
    private NSZC002001PMsg buildNSZC002001PMsgForFollowUpTask(S21ApiMessageMap msgMap, NSXC001001GetFollUpInfoBean follUpBean) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        NSZC002001PMsg apiPMsg = new NSZC002001PMsg();

        String firstProdCtrlCd = prmPMsg.firstProdCtrlCd.getValue();
        if (!ZYPCommonFunc.hasValue(firstProdCtrlCd)) {
            if (!ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0074E);
                return null;
            }
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return null;
            }
            MDSETMsg mdseTMsg = getMdse(prmPMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.mdseCd.getValue());
            if (mdseTMsg == null) {
                msgMap.addXxMsgId(NSZM0583E);
                return null;
            } else {
                firstProdCtrlCd = mdseTMsg.firstProdCtrlCd.getValue();
            }
        }

        String custMachCtrlNum = prmPMsg.custMachCtrlNum.getValue();
        if (!ZYPCommonFunc.hasValue(custMachCtrlNum)) {
            if (ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {
                SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
                if (svcMachMstrTMsg == null) {
                    msgMap.addXxMsgId(NSZM0006E);
                    return null;
                }
                custMachCtrlNum = svcMachMstrTMsg.custMachCtrlNum.getValue();
            }
        }

        String serNum = prmPMsg.serNum.getValue();
        String mdseCd = prmPMsg.mdseCd.getValue();
        if (!ZYPCommonFunc.hasValue(serNum) || !ZYPCommonFunc.hasValue(mdseCd)) {
            if (ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {
                SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
                if (svcMachMstrTMsg == null) {
                    msgMap.addXxMsgId(NSZM0006E);
                    return null;
                }
                serNum = svcMachMstrTMsg.serNum.getValue();
                mdseCd = svcMachMstrTMsg.mdseCd.getValue();
            }
        }

        String mdlNm = prmPMsg.mdlNm.getValue();
        BigDecimal mdlId = null;

        if (ZYPCommonFunc.hasValue(mdlNm)) {
            MDL_NMTMsgArray mdlNmTMsgArray = getMdlNmByMdlNm(prmPMsg.glblCmpyCd.getValue(), mdlNm);
            if (mdlNmTMsgArray.length() == 0) {
                msgMap.addXxMsgId(NSZM0090E);
                return null;
            } else {
                mdlId = mdlNmTMsgArray.no(0).t_MdlId.getValue();
            }
        } else {
            DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, prmPMsg.glblCmpyCd.getValue(), prmPMsg.dsSvcCallTpCd.getValue());
            if (dsSvcCallTpTMsg == null) {
                msgMap.addXxMsgId(NSZM0549E);
                return null;
            }
            if (!ZYPCommonFunc.hasValue(prmPMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0074E);
                return null;
            }
            // mod start 2016/06/15 CSA Defect#8899
            // if
            // (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.svcIstlReqFlg.getValue()))
            // {
            // BigDecimal svcConfigMstrCnt =
            // cntSvcConfigMstrBySvcMachMstrPk(prmPMsg.glblCmpyCd.getValue(),
            // prmPMsg.svcMachMstrPk.getValue());
            // if (svcConfigMstrCnt.compareTo(BigDecimal.ONE) >= 0) {
            // Map<String, Object> mdlNmMap =
            // getMdlNmMapFromSvcConfigMstr(prmPMsg.glblCmpyCd.getValue(),
            // prmPMsg.svcMachMstrPk.getValue());
            // if (mdlNmMap == null) {
            // msgMap.addXxMsgId(NSZM0090E);
            // return null;
            // }
            // mdlNm = (String) mdlNmMap.get("T_MDL_NM");
            // mdlId = (BigDecimal) mdlNmMap.get("T_MDL_ID");
            // } else {
            // if (!ZYPCommonFunc.hasValue(prmPMsg.soNum)) {
            // msgMap.addXxMsgId(NSZM0027E);
            // return null;
            // }
            // Map<String, Object> mdlNmMap =
            // getMdlNmMapFromDsMdlConfig(prmPMsg.glblCmpyCd.getValue(),
            // prmPMsg.soNum.getValue());
            // if (mdlNmMap == null) {
            // msgMap.addXxMsgId(NSZM0090E);
            // return null;
            // }
            // mdlNm = (String) mdlNmMap.get("T_MDL_NM");
            // mdlId = (BigDecimal) mdlNmMap.get("T_MDL_ID");
            // }
            // } else {
            Map<String, Object> mdlNmMap = getMdlNmMapFromSvcConfigMstr(prmPMsg.glblCmpyCd.getValue(), prmPMsg.svcMachMstrPk.getValue());
            if (mdlNmMap == null) {
                msgMap.addXxMsgId(NSZM0090E);
                return null;
            }
            mdlNm = (String) mdlNmMap.get("T_MDL_NM");
            mdlId = (BigDecimal) mdlNmMap.get("T_MDL_ID");
            // }
            // mod end 2016/06/15 CSA Defect#8899
        }

        String mdlGrpNm = prmPMsg.mdlGrpNm.getValue();
        if (!ZYPCommonFunc.hasValue(mdlGrpNm)) {
            if (!ZYPCommonFunc.hasValue(mdlId)) {
                msgMap.addXxMsgId(NSZM0090E);
                return null;
            }
            Map<String, Object> mdlGrpMap = getDsMdlGrpMapByMdlId(prmPMsg.glblCmpyCd.getValue(), mdlId);
            if (mdlGrpMap == null) {
                msgMap.addXxMsgId(NSZM0585E);
                return null;
            }
            mdlGrpNm = (String) mdlGrpMap.get("MDL_GRP_NM");
        }

        String firstSvcTaskFlg = prmPMsg.firstSvcTaskFlg.getValue();
        if (!ZYPCommonFunc.hasValue(firstSvcTaskFlg)) {
            if (firstSvcTask(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue())) {
                firstSvcTaskFlg = ZYPConstant.FLG_ON_Y;
            } else {
                firstSvcTaskFlg = ZYPConstant.FLG_OFF_N;
            }
        }

        String shipToCustCd = null;
        String svcBillTpCd = null;
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(prmPMsg.glblCmpyCd.getValue(), prmPMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
        if (svcTaskTMsg == null) {
            msgMap.addXxMsgId(NSZM0084E);
            return null;
        } else {
            shipToCustCd = svcTaskTMsg.shipToCustCd.getValue();
            svcBillTpCd = svcTaskTMsg.svcBillTpCd.getValue();
        }

        // mod Start 2016/04/01 CSA Defect#5083, 2016/04/28 CSA
        // Defect#2696
        // SVC_PBLM_RSN_FOLL_UPTMsg svcPblmRsnFollUpTMsg =
        // getSvcPblmRsnFollUp(prmPMsg.glblCmpyCd.getValue(),
        // prmPMsg.svcPblmRsnTpCd.getValue());
        // Map<String, Object> fSRVisitTaskMap =
        // getFSRVisitTaskBySvcTaskNum(prmPMsg.glblCmpyCd.getValue(),
        // prmPMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
        // if (fSRVisitTaskMap == null) {
        follUpBean.setGlblCmpyCd(prmPMsg.glblCmpyCd.getValue());
        // mod Start 2016/06/07 CSA Defect#9218
        follUpBean.setSvcPblmCrctTpCd(prmPMsg.svcPblmCrctTpCd.getValue());
        // mod End 2016/06/07 CSA Defect#9218
        follUpBean.setSvcMachMstrPk(prmPMsg.svcMachMstrPk.getValue());
        follUpBean.setMdlNm(mdlNm);
        follUpBean.setSvcTaskRcvDt(prmPMsg.svcTaskRcvDt.getValue());
        follUpBean.setMachDownFlg(prmPMsg.machDownFlg.getValue());
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        Map<String, String> shipInfoMap = getShipToInfo(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue());
        String shipToUpdCustCd = null;
        if (shipInfoMap != null) {
            shipToUpdCustCd = shipInfoMap.get("SHIP_TO_UPD_CUST_CD");
        }
        follUpBean.setShipToUpdCustCd(shipToUpdCustCd);
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]
        if (!NSXC001001GetFollUpInfo.getFollUpInfo(follUpBean)) {
            msgMap.addXxMsgId(follUpBean.getXxMsgId());
            return null;
        }
        // mod End 2016/04/01 CSA Defect#5083, 2016/04/28 CSA
        // Defect#2696

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, prmPMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(apiPMsg.soNum, prmPMsg.soNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstProdCtrlCd, firstProdCtrlCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallPrtyCd, prmPMsg.xxSvcTaskList.no(0).svcCallPrtyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, prmPMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, custMachCtrlNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdlNm, mdlNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdlGrpNm, mdlGrpNm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, prmPMsg.custTelNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, prmPMsg.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, prmPMsg.svcCustAttnTxt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, prmPMsg.custEmlAddr);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, prmPMsg.custPoNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, prmPMsg.custPoDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, prmPMsg.dsSvcCallTpCd);
        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd,
        // prmPMsg.svcBillTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, svcBillTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.techCd, prmPMsg.xxSvcTaskList.no(0).techCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, prmPMsg.svcTaskRcvDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, prmPMsg.svcTaskRcvTm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, firstSvcTaskFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
        // mod Start 2016/04/01 CSA Defect#5083, 2016/04/13 CSA
        // Defect#1945, 2016/04/28 CSA Defect#2696
        ZYPEZDItemValueSetter.setValue(apiPMsg.erlStartTs, follUpBean.getErlStartTs());
        ZYPEZDItemValueSetter.setValue(apiPMsg.lateStartTs, follUpBean.getLateStartTs());
        // mod End 2016/04/01 CSA Defect#5083, 2016/04/13 CSA
        // Defect#1945, 2016/04/28 CSA Defect#2696
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrEventExeUsrId, prmPMsg.userId);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, prmPMsg.svcCustCllrTxt);
        // mod Start 2016/04/01 CSA Defect#6020
        ZYPEZDItemValueSetter.setValue(apiPMsg.mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // mod End 2016/04/01 CSA Defect#6020
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.shipToUpdCustCd, shipToUpdCustCd);
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]

        // add Start 2016/04/12 CSA Defect#6766
        // Mod Start 2018/09/26 QC#28262
        // nszc0020DefaultSet(apiPMsg, prmPMsg.fsrNum.getValue());
        nszc0020DefaultSet(apiPMsg, prmPMsg.fsrNum.getValue(), prmPMsg);
        // Mod End 2018/09/26 QC#28262
        // add end 2016/04/12 CSA Defect#6766

        return apiPMsg;
    }

    /**
     * Get Continuous Call API parameter for "Follow Up Task"
     * @param msgMap S21ApiMessageMap
     * @param preSvcTaskNum
     * @param follUpBean
     * @return NSZC004001PMsg
     */
    private NSZC004001PMsg buildNSZC004001PMsgForFollowUpTask(S21ApiMessageMap msgMap, String preSvcTaskNum, NSXC001001GetFollUpInfoBean follUpBean) {

        NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
        NSZC004001PMsg apiPMsg = new NSZC004001PMsg();
        // Add Start 2017/09/08 QC#19242
        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD, prmPMsg.glblCmpyCd.getValue());
        // Add End 2017/09/08 QC#19242
        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, prmPMsg.glblCmpyCd.getValue());
        this.clickSendExclCallTpList = clickSendExclCallTp.split(STR_COMMA);
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        String fsrVisitNum = prmPMsg.fsrVisitNum.getValue();
        // del start 2016/02/26 CSA Defect#4753, mod 2016/04/28 CSA
        // Defect#2696
        if (!ZYPCommonFunc.hasValue(fsrVisitNum)) {
            FSR_VISITTMsg fsrVisitTMsg = getFsrVisitBySvcTaskNum(prmPMsg.glblCmpyCd.getValue(), prmPMsg.fsrNum.getValue(), preSvcTaskNum);
            if (fsrVisitTMsg != null) {
                fsrVisitNum = fsrVisitTMsg.fsrVisitNum.getValue();
            } else {
                msgMap.addXxMsgId(NSZM0182E);
                return null;
            }
        }
        // del end 2016/02/26 CSA Defect#4753, mod 2016/04/28 CSA
        // Defect#2696

        FSR_VISITTMsg srchFsrVisitTMsg = new FSR_VISITTMsg();
        srchFsrVisitTMsg.glblCmpyCd.setValue(prmPMsg.glblCmpyCd.getValue());
        srchFsrVisitTMsg.fsrNum.setValue(prmPMsg.fsrNum.getValue());
        srchFsrVisitTMsg.fsrVisitNum.setValue(fsrVisitNum);
        FSR_VISITTMsg fsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKey(srchFsrVisitTMsg);
        if (fsrVisitTMsg == null) {
            msgMap.addXxMsgId(NSZM0182E);
            return null;
        }

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, prmPMsg.glblCmpyCd);
        // mod start 2016/04/01 CSA Defect#5083
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC004001Constant.PROCESS_MODE_FOLLOWUP_TASK);
        // mod end 2016/04/01 CSA Defect#5083
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, prmPMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitNum, fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrEventExeUsrId, prmPMsg.userId);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcAsgTpCd, follUpBean.getSvcAsgTpCd());
        // NA#ASCC CLICK BugFix MOD Start
        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcChrgContFlg,
        // prmPMsg.svcChrgContFlg);
        apiPMsg.svcChrgContFlg.setValue(ZYPConstant.FLG_ON_Y);
        // NA#ASCC CLICK BugFix MOD End

        // mod Start 2016/04/01 CSA Defect#5083, 2016/04/28 CSA
        // Defect#2696
        // SVC_PBLM_RSN_FOLL_UPTMsg svcPblmRsnFollUpTMsg =
        // getSvcPblmRsnFollUp(prmPMsg.glblCmpyCd.getValue(),
        // prmPMsg.svcPblmRsnTpCd.getValue());
        // Map<String, Object> fSRVisitTaskMap =
        // getFSRVisitTaskBySvcTaskNum(prmPMsg.glblCmpyCd.getValue(),
        // fsrVisitTMsg.svcTaskNum.getValue());
        // if (fSRVisitTaskMap == null) {
        // if (svcPblmRsnFollUpTMsg == null) {
        // msgMap.addXxMsgId(NSZM0423E);
        // return null;
        // }
        // String reqTechFlg = (String)
        // fSRVisitTaskMap.get("REQ_TECH_FLG");
        // mod End 2016/04/01 CSA Defect#5083
        String reqTechFlg = follUpBean.getReqTechFlg();
        // START 2017/06/23 K.Ochiai [QC#19493, ADD]
        String techCd = null;
        String svcAsgTechCd = null;
        String svcAsgTpCd = null;
        if (ZYPConstant.FLG_ON_Y.equals(reqTechFlg)) {
            techCd = prmPMsg.xxSvcTaskList.no(0).techCd.getValue();
            svcAsgTechCd = prmPMsg.xxSvcTaskList.no(0).svcAsgTechCd.getValue();
            svcAsgTpCd = prmPMsg.xxSvcTaskList.no(0).svcAsgTpCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.techCd, techCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcAsgTechCd, svcAsgTechCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcAsgTpCd, svcAsgTpCd);
        // END 2017/06/23 K.Ochiai [QC#19493, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, prmPMsg.bypsPrfTechFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.techSchdFromDt, prmPMsg.xxSvcTaskList.no(0).techSchdFromDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.techSchdFromTm, prmPMsg.xxSvcTaskList.no(0).techSchdFromTm);
        ZYPEZDItemValueSetter.setValue(apiPMsg.schdDisptEmlFlg, prmPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg);

        // mod Start 2016/04/01 CSA Defect#5083
        // if (!ZYPCommonFunc.hasValue((String)
        // fSRVisitTaskMap.get("SVC_PBLM_RSN_TP_CD"))) {
        // msgMap.addXxMsgId(NSZM0245E);
        // return null;
        // }

        // String reqPrtFlg = (String)
        // fSRVisitTaskMap.get("REQ_PRT_FLG");
        // String fsrVisitStsCd = getFsrVisitStsCd(msgMap, (String)
        // fSRVisitTaskMap.get("SVC_TASK_STS_CD"));
        String reqPrtFlg = follUpBean.getReqPrtFlg();
        String fsrVisitStsCd = getFsrVisitStsCd(msgMap, follUpBean.getFsrVisitStsCd());
        // mod End 2016/04/01 CSA Defect#5083, 2016/04/28 CSA
        // Defect#2696

        if (ZYPConstant.FLG_ON_Y.equals(reqPrtFlg)) {

            fsrVisitStsCd = SVC_TASK_STS.PENDING_PARTS;
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitStsCd, fsrVisitStsCd);
        apiPMsg.xxSvcTaskNumList.setValidCount(1);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskNumList.no(0).svcTaskNum, prmPMsg.xxSvcTaskList.no(0).svcTaskNum);

        ZYPEZDItemValueSetter.setValue(apiPMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);

        return apiPMsg;
    }

    // /**
    // * Get FSR Visit Task By Service Task Number information
    // * @param glblCmpyCd
    // * @param svcTaskNum
    // * @return
    // */
    // @SuppressWarnings("unchecked")
    // private Map<String, Object> getFSRVisitTaskBySvcTaskNum(String
    // glblCmpyCd, String svcTaskNum) {
    // Map<String, Object> ssmPrm = new HashMap<String, Object>();
    // ssmPrm.put("glblCmpyCd", glblCmpyCd);
    // ssmPrm.put("svcTaskNum", svcTaskNum);
    // return (Map<String, Object>)
    // ssmBatClnt.queryObject("getFSRVisitTaskBySvcTaskNum", ssmPrm);
    // }

    // /**
    // * Get SVC_PBLM_RSN_FOLL_UP
    // * @param glblCmpyCd Global Company Code
    // * @param svcPblmRsnTpCd Service Problem Reason Type Code
    // * @return SVC_PBLM_RSN_FOLL_UPTMsg
    // */
    // private SVC_PBLM_RSN_FOLL_UPTMsg getSvcPblmRsnFollUp(String
    // glblCmpyCd, String svcPblmRsnTpCd) {
    // if (!ZYPCommonFunc.hasValue(svcPblmRsnTpCd)) {
    // svcPblmRsnTpCd =
    // ZYPCodeDataUtil.getVarCharConstValue(SVC_PBLM_RSN_TP,
    // glblCmpyCd);
    // }
    // SVC_PBLM_RSN_FOLL_UPTMsg tMsg = new SVC_PBLM_RSN_FOLL_UPTMsg();
    // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(tMsg.svcPblmRsnTpCd,
    // svcPblmRsnTpCd);
    // return (SVC_PBLM_RSN_FOLL_UPTMsg)
    // S21ApiTBLAccessor.findByKey(tMsg);
    // }

    // /**
    // * Get SVC_FOLL_UP_START
    // * @param glblCmpyCd Global Company Code
    // * @param svcFollUpStartCd Service Foll Up Start Code
    // * @return SVC_FOLL_UP_STARTTMsg
    // */
    // private SVC_FOLL_UP_STARTTMsg getSvcFollUpStart(String
    // glblCmpyCd, String svcFollUpStartCd) {
    // SVC_FOLL_UP_STARTTMsg svcFollUpStartPrmTMsg = new
    // SVC_FOLL_UP_STARTTMsg();
    // ZYPEZDItemValueSetter.setValue(svcFollUpStartPrmTMsg.glblCmpyCd,
    // glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(svcFollUpStartPrmTMsg.svcFollUpStartCd,
    // svcFollUpStartCd);
    // return (SVC_FOLL_UP_STARTTMsg)
    // S21ApiTBLAccessor.findByKey(svcFollUpStartPrmTMsg);
    // }

    // del start 2015/12/18 CSA Defect#1112
    // private List<Map<String, Object>> getFsrVisit(String
    // glblCmpyCd, String svcTaskNum) {
    // Map<String, Object> paramMap = new HashMap<String, Object>();
    // paramMap.put("glblCmpyCd", glblCmpyCd);
    // paramMap.put("svcTaskNum", svcTaskNum);
    // return (List<Map<String, Object>>)
    // ssmBatClnt.queryObjectList("getFsrVisit", paramMap);
    // }
    // del end 2015/12/18 CSA Defect#1112

    // START 2018/11/08 S.Kitamura [QC#28930,DEL]
    // add start 2016/03/04 CSA Defect#2697
    // /**
    // * buildNSZC037001PMsgForAddTask
    // * @param msgMap
    // * @return
    // */
    // private NSZC037001PMsg
    // buildNSZC037001PMsgForAddTask(S21ApiMessageMap msgMap) {
    //
    // NSZC045001PMsg prmPMsg = (NSZC045001PMsg) msgMap.getPmsg();
    // NSZC037001PMsg apiPMsg = new NSZC037001PMsg();
    //
    // ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd,
    // prmPMsg.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, prmPMsg.slsDt);
    // ZYPEZDItemValueSetter.setValue(apiPMsg.usrId, prmPMsg.userId);
    // ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, prmPMsg.fsrNum);
    // ZYPEZDItemValueSetter.setValue(apiPMsg.A.no(0).svcTaskNum,
    // prmPMsg.xxSvcTaskList.no(0).svcTaskNum);
    // apiPMsg.A.setValidCount(1);
    //
    // return apiPMsg;
    // }
    //
    // // add start 2016/03/04 CSA Defect#2697
    // /**
    // * isCreditCheckTarget
    // * @param pMsg
    // * @return
    // */
    // private boolean isCreditCheckTarget(NSZC045001PMsg pMsg) {
    // SVC_BILL_TPTMsg srchSvcBilTpTMsg = new SVC_BILL_TPTMsg();
    // srchSvcBilTpTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
    // srchSvcBilTpTMsg.svcBillTpCd.setValue(pMsg.svcBillTpCd.getValue());
    //
    // SVC_BILL_TPTMsg svcBilTpTMsg = (SVC_BILL_TPTMsg)
    // S21ApiTBLAccessor.findByKey(srchSvcBilTpTMsg);
    //
    // if
    // (ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.lborChrgFlg.getValue())
    // ||
    // ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.prtChrgFlg.getValue())
    // ||
    // ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.drumChrgFlg.getValue())
    // ||
    // ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.expChrgFlg.getValue()))
    // {
    //
    // // mod start 2016/05/25 CSA Defect#8605
    // if
    // (ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.crChkFlg.getValue()))
    // {
    // return true;
    // }
    // // mod end 2016/05/25 CSA Defect#8605
    //
    // DS_SVC_CALL_TPTMsg srchDsSvcCallTpTMsg = new
    // DS_SVC_CALL_TPTMsg();
    // srchDsSvcCallTpTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
    // srchDsSvcCallTpTMsg.dsSvcCallTpCd.setValue(pMsg.dsSvcCallTpCd.getValue());
    //
    // DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg)
    // S21ApiTBLAccessor.findByKey(srchDsSvcCallTpTMsg);
    //
    // if
    // (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.crChkFlg.getValue()))
    // {
    //
    // return true;
    // }
    // }
    // return false;
    // }
    //
    // // add start 2016/03/04 CSA Defect#2697
    // /**
    // * checkCreditHold
    // * @param nszc037001PMsg
    // * @return
    // */
    // private boolean checkCreditHold(NSZC037001PMsg nszc037001PMsg)
    // {
    // for (int i = 0; i < nszc037001PMsg.A.getValidCount(); i++) {
    // if
    // (ZYPCommonFunc.hasValue(nszc037001PMsg.A.no(i).svcTaskHldRsnCd))
    // {
    // return true;
    // }
    // }
    // return false;
    // }

    // END 2018/11/08 S.Kitamura [QC#28930,DEL]

    // add 2016/04/12 CSA Defect#6766
    /**
     * nszc0020DefaultSet
     * @param apiPMsg NSZC002001PMsg
     * @param fsrNum String
     */
    private void nszc0020DefaultSet(NSZC002001PMsg apiPMsg, String fsrNum, NSZC045001PMsg prmPMsg) {

        SVC_TASKTMsg firstTask = getFirstSvcTask(apiPMsg.glblCmpyCd.getValue(), fsrNum);
        if (firstTask == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, firstTask.custTelNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, firstTask.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, firstTask.svcCustAttnTxt);
        // mod start 2019/04/19 QC#31238
        //ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, firstTask.custEmlAddr);
        if (prmPMsg.xxModeCd.getValue().equals(PROCESS_MODE_FOLLOWUP_TASK) && ZYPCommonFunc.hasValue(prmPMsg.custEmlAddr)) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, prmPMsg.custEmlAddr);
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, firstTask.custEmlAddr);
        }
        // mod end 2019/04/19 QC#31238
        // START 2018/10/24 K.Kojima [QC#28545,MOD]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd,
        // firstTask.svcBillTpCd);
        if (prmPMsg.xxModeCd.getValue().equals(PROCESS_MODE_CALLAVOID_DISPTTECH) && ZYPCommonFunc.hasValue(prmPMsg.svcBillTpCd)) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, prmPMsg.svcBillTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, firstTask.svcBillTpCd);
        }
        // END 2018/10/24 K.Kojima [QC#28545,MOD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, firstTask.machDownFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, firstTask.prtChrgFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, firstTask.svcLborChrgFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.origInvCcyCd, firstTask.origInvCcyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.invCcyCd, firstTask.invCcyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ccyExchRate, firstTask.ccyExchRate);
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, firstTask.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, firstTask.svcCustCllrTxt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ccyExchRate, firstTask.ccyExchRate);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTelNum, firstTask.svcCustCllrTelNum);
        // mod start 2016/06/09 CSA Defect#9177
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTelExtnNum, firstTask.svcCustCllrTelExtnNum);
        // mod end 2016/06/09 CSA Defect#9177
        // Add Start 2018/03/22 QC#18713
        // Mod Start 2018/09/26 QC#28262
        // ZYPEZDItemValueSetter.setValue(apiPMsg.cellPhoNum,
        // firstTask.cellPhoNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.cellPhoNum, prmPMsg.xxSvcTaskList.no(0).cellPhoNum);
        // Mod End 2018/09/26 QC#28262
        // Add End 2018/03/22 QC#18713
        // add start 2019/04/26 QC#31213
        ZYPEZDItemValueSetter.setValue(apiPMsg.crsSvcSrNum, firstTask.crsSvcSrNum);
        // add end 2019/04/26 QC#31213
    }

    // add 2016/04/12 CSA Defect#6766
    /**
     * getFirstSvcTask
     * @param value
     * @param fsrNum
     * @return
     */
    private SVC_TASKTMsg getFirstSvcTask(String glblCmpyCd, String fsrNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("011");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("firstSvcTaskFlg01", ZYPConstant.FLG_ON_Y);
        return ((SVC_TASKTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg)).no(0);
    }

    // add start 2016/06/16 CSA Defect#9677
    private void callSendTaskInfoApi(S21ApiMessageMap msgMap, NSZC045001PMsg pMsg, ONBATCH_TYPE onBatTp) {
        if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxRqstSendFlg.getValue())) {
            return;
        }

        NSZC107001PMsg inPMsg = new NSZC107001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(inPMsg.svcTaskNum, pMsg.xxSvcTaskList.no(0).svcTaskNum);

        NSZC107001 nszc107001API = new NSZC107001();
        nszc107001API.execute(inPMsg, onBatTp);

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(inPMsg);
        for (S21ApiMessage msg : msgList) {
            String xxMsgId = msg.getXxMsgid();
            String[] xxMsgPrm = msg.getXxMsgPrmArray();
            if (ZYPCommonFunc.hasValue(xxMsgId) && xxMsgId.endsWith("E")) {
                msgMap.addXxMsgIdWithPrm(xxMsgId, xxMsgPrm);
            }
        }
    }

    // add end 2016/06/16 CSA Defect#9677
    private boolean isPndIstl(NSZC045001PMsg pMsg) {
        return ZYPCommonFunc.hasValue(pMsg.soNum);
    }

    // START 2017/08/28 K.Kojima [QC#20643,ADD]
    private void createSvcMemo(S21ApiMessageMap msgMap) {
        NSZC045001PMsg pMsg = (NSZC045001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxSvcMemoList.getValidCount(); i++) {
            NSZC045001_xxSvcMemoListPMsg xxSvcMemo = pMsg.xxSvcMemoList.no(i);

            SVC_MEMOTMsg tMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMemoTpCd, xxSvcMemo.svcMemoTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcCmntTxt, xxSvcMemo.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMemoRsnCd, xxSvcMemo.svcMemoRsnCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.xxSvcTaskList.no(0).svcTaskNum);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(tMsg.lastUpdUsrId, pMsg.userId);
            ZYPEZDItemValueSetter.setValue(tMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS));
            S21ApiTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0475E);
                return;
            }

        }
    }

    // END 2017/08/28 K.Kojima [QC#20643,ADD]
    // Add Start 2017/09/08 QC#19242
    private boolean isOutTrtySvcBr(SVC_TASKTMsg svcTaskTMsg) {
        // Mod Start 2017/09/27 QC#19242
        if (svcTaskTMsg == null || !ZYPCommonFunc.hasValue(this.outTrtySvcBrCd)) {
            return false;
        }

        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, svcTaskTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, svcTaskTMsg.svcMachMstrPk);
        SVC_MACH_MSTRTMsg machMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (machMstrTMsg == null || !ZYPCommonFunc.hasValue(machMstrTMsg.fldSvcBrCd)) {
            return false;
        }

        if (this.outTrtySvcBrCd.equals(machMstrTMsg.fldSvcBrCd.getValue())) {
            return true;
        }
        return false;
        // Mod End 2017/09/27 QC#19242
    }

    // Add End 2017/09/08 QC#19242

    // Add Start 2018/06/25 QC#25608
    private void cancelWF(S21ApiMessageMap msgMap, String processNm, String documentId) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        List<S21NwfProcess> processes = null;

        try {

            context = factory.getContex();
            processes = context.getProcess(processNm, documentId);

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().cancel();
                }
            }
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfBizException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfException e) {
            msgMap.addXxMsgId(NSZM0866E);
        }
    }

    // Add End 2018/06/25 QC#25608

    // START 2018/11/08 S.Kitamura [QC#28930,ADD]
    private String getFirstTaskVisitStatusCode(String glblCmpyCd, String fsrNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("fsrNum", fsrNum);
        return (String) ssmBatClnt.queryObject("getFstTskVstStsCd", paramMap);
    }
    // END 2018/11/08 S.Kitamura [QC#28930,ADD]

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    private boolean isClickSendExclCall(String dsSvcCallTpCd) {
        for (int i = 0; i < this.clickSendExclCallTpList.length; i++) {
            if (this.clickSendExclCallTpList[i].equals(dsSvcCallTpCd)) {
                return true;
            }
        }
        return false;
    }
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]
}
