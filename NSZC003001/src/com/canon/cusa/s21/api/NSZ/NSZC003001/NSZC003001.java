/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CTRYTMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CUST_TRX_RULETMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_ISTL_CHK_LISTTMsg;
import business.db.FSR_ISTL_CHK_LISTTMsgArray;
import business.db.FSR_TPTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.POSTTMsg;
import business.db.POSTTMsgArray;
import business.db.S21_PSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.SVC_ASG_TPTMsg;
import business.db.SVC_BILL_TPTMsg;
import business.db.SVC_CALL_AVOIDTMsg;
import business.db.SVC_CALL_SRC_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_TPTMsg;
import business.db.SVC_PBLM_TPTMsg;
import business.db.SVC_PBLM_TPTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.db.TECH_MSTRTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC003001PMsg;
import business.parts.NSZC003001_svcMemoListPMsg;
import business.parts.NSZC003001_xxSvcTaskListPMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC005001_xxFsrIstlChkListPMsg;
import business.parts.NSZC037001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC003001.constant.NSZC003001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.api.NSZ.NSZC005001.constant.NSZC005001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC037001.NSZC037001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ST;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_AVOID;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21CommonException;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Service Dispatch Schedule/Dispatch Screen API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/12   SRA             E.Inada         Create          N/A
 * 2013/09/09   SRA             E.Inada         Update          QC#2191
 * 2013/10/24   SRA             E.Inada         Update          QC#2910
 * 2014/01/17   Fujitsu         Y.Kamide        Update          Mobile IF
 * 2014/03/18   Fujitsu         M.Nakamura      Update          S21 NA Def#169
 * 2014/03/27   Fujitsu         M.Nakamura      Update          S21 NA Def#42
 * 08/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 08/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 10/09/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 10/23/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 11/18/2015   Hitachi         T.Iwamoto       Update          NA#395  CLICK BugFix
 * 12/21/2015   Hitachi         J.Kim           Update          NA#1112
 * 01/07/2016   Fujitsu         S.Nakai         Update          QC#2863
 * 2016/01/08   Hitachi         K.Yamada        Update          CSA QC#2556
 * 01/13/2015   Fujitsu         T.Nakamura      Update          NA#1107
 * 01/15/2015   Hitachi         J.Kim           Update          CSA QC#3202
 * 02/12/2016   Hitachi         T.Iwamoto       Update          CSA QC#2863,2561,2562
 * 02/26/2016   Hitachi         T.Iwamoto       Update          CSA QC#4113,4645,4301
 * 03/07/2016   Hitachi         T.Iwamoto       Update          CSA QC#2697
 * 04/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#6261
 * 04/06/2016   Hitachi         T.Iwamoto       Update          CSA QC#4982
 * 04/13/2016   Hitachi         A.Kohinata      Update          CSA QC#6866
 * 04/13/2016   Hitachi         T.Iwamoto       Update          CSA QC#5486
 * 04/20/2016   Hitachi         T.Iwamoto       Update          CSA QC#7326
 * 04/21/2016   Hitachi         T.Iwamoto       Update          CSA QC#7320
 * 05/24/2016   Hitachi         T.Iwamoto       Update          CSA QC#8809
 * 05/25/2016   Hitachi         T.Iwamoto       Update          CSA QC#8605
 * 07/14/2016   Hitachi         T.Iwamoto       Update          CSA QC#11185,SADP
 * 08/10/2016   Fujitsu         S.Nakai         Update          CSA QC#8421
 * 09/07/2016   Fujitsu         S.Nakai         Update          CSA QC#8421
 * 09/09/2016   Hitachi         T.Kanasaka      Update          CSA QC#14206
 * 11/16/2016   Hitachi         N.Arai          Update          QC#15860
 * 11/25/2016   Hitachi         T.Mizuki        Update          CSA QC#16025
 * 11/28/2016   Hitachi         N.Arai          Update          QC#13901
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/01/04   Hitachi         N.Arai          Update          QC#16449
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 * 2017/09/04   Hitachi         K.Kitachi       Update          QC#20053
 * 2017/09/08   Hitachi         T.Tomita        Update          QC#19242
 * 2017/09/27   Hitachi         T.Tomita        Update          QC#19242
 * 2018/01/10   Hitachi         K.Kojima        Update          QC#22975
 * 2018/01/15   Hitachi         U.Kim           Update          QC#19702
 * 2018/01/17   CITS            M.Naito         Update          QC#17469
 * 2018/01/24   CITS            M.Naito         Update          QC#23021
 * 2018/04/05   Hitachi         K.Kitachi       Update          QC#23988
 * 2018/05/16   CITS            M.Naito         Update          QC#25892
 * 2018/07/11   Hitachi         K.Kitachi       Update          QC#26045
 * 2018/07/12   CITS            M.Naito         Update          QC#13309
 * 2018/08/30   Hitachi         K.Kitachi       Update          QC#22665
 * 2018/09/06   Hitachi         K.Kojima        Update          QC#28064
 * 2018/09/26   Fujitsu         W.Honda         Update          QC#28381
 * 2018/10/03   Hitachi         K.Fujimoto      Update          QC#28514
 * 2019/01/21   Fujitsu         W.Honda         Update          QC#28650
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#30165
 * 2019/03/27   Hitachi         S.Kitamura      Update          QC#30906
 * 2019/05/07   Hitachi         K.Kitachi       Update          QC#31303
 * 2019/07/31   Hitachi         K.Kitachi       Update          QC#52257
 * 2019/08/23   Hitachi         K.Fujimoto      Update          QC#53116
 * 2021/10/06   CITS            R.Jabal         Update          QC#59183
 * 2022/04/11   Hitachi         K.Kitachi       Update          QC#59899
 * 2022/04/25   Hitachi         K.Kitachi       Update          QC#59913
 * 2022/07/20   CITS            L.Mandanas      Update          QC#60316
 * 2022/09/28   Hitachi         K.Kitachi       Update          QC#60633
 * 2022/10/26   Hitachi         K.Kitachi       Update          QC#60726
 * 2022/12/12   Hitachi         K.Kitachi       Update          QC#60911
 *</pre>
 */
public class NSZC003001 extends S21ApiCommonBase implements NSZC003001Constant {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** System Time stamp */
    private String sysTs = null;

    /** System Date */
    private String sysDt = null;

    /** System Time */
    private String sysTm = null;

    /** TECH_MSTRTMsg */
    private TECH_MSTRTMsg techMstrTMsg = null;

    /** onBatType */
    private ONBATCH_TYPE onBatType = null;

    // Add Start 2017/09/08 QC#19242
    /** Out of Territory Service Branch Code */
    private String outTrtySvcBrCd = null;
    // Add End 2017/09/08 QC#19242

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    /** Waiting Second for find by key*/
    private int waitSecUpdTaskOther;
    // END 2018/01/15 U.Kim [QC#19702, ADD]

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    /** information message list */
    private List<String> infoMsgList = null;
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** Click send exclusion call type list */
    private String[] clickSendExclCallTpList = null;
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    /**
     * NSZC003001
     */
    public NSZC003001() {
        super();

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC003001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC003001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        for (NSZC003001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC003001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC003001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        init(pMsg, onBatchType);

        // START 2018/01/09 U.Kim [QC#19702, ADD]
        setWaitSecUpdTaskOther(pMsg.glblCmpyCd.getValue());
        // END 2018/01/09 U.Kim [QC#19702, ADD]

        if (!inputCheck(pMsg)) {
            return;
        }
        // Add Start 2017/09/08 QC#19242
        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD, pMsg.glblCmpyCd.getValue());
        // Add End 2017/09/08 QC#19242

        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, pMsg.glblCmpyCd.getValue());
        this.clickSendExclCallTpList = clickSendExclCallTp.split(STR_COMMA);
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        try {

            String mode = pMsg.xxBizProcTp.getValue();
            String fsrNum = pMsg.fsrNum.getValue();
            boolean updFlg = false;
            FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
            List<Map<String, Object>> svcTaskList = null;

            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode) || MODE_POST_DISPT.equals(mode)) {

                svcTaskList = getSvcTaskList(pMsg, true);
                if (!combinationCheck(pMsg, svcTaskList)) {
                    return;
                }

                if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
                    fsrNum = ZYPExtnNumbering.getUniqueID(FSR_NUM_SEQ_CD);
                }

                // SVC_TASK update
                updateSvcTask(pMsg, svcTaskList, fsrNum);

                if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
                    // FSR insert
                    // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                    if(!insertFsr(pMsg, svcTaskList, fsrNum)) {
                        return;
                    }
                    // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
                    // FSR_VISIT insert
                    fsrVisitTMsg = insertFsrVisit(pMsg, fsrNum, fsrVisitTMsg, null, null, null, null);
                } else {
                    // FSR update
                    updateFsr(pMsg);
                    // FSR_VISIT update
                    fsrVisitTMsg = updateFsrVisit(pMsg, getVisitNum(pMsg, svcTaskList), null, null, null, null);

                    updFlg = checkUpdateSchedule(svcTaskList, mode);
                }

                // FSR_EVENT insert
                insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, null);

            } else if (MODE_CANCEL.equals(mode)) {

                int visitCnt = getFsrVisitCnt(pMsg);

                if (visitCnt <= 1) {

                    svcTaskList = getSvcTaskList(pMsg, false);

                    // SVC_TASK update
                    updateSvcTask(pMsg, svcTaskList, null);

                    if (getSvcTaskCnt(pMsg) == 0) {
                        // FSR delete
                        deleteFsr(pMsg);
                        // FSR_VISIT delete
                        deleteFsrVisit(pMsg, getVisitNum(pMsg, svcTaskList));
                    }

                } else {
                    svcTaskList = getSvcTaskList(pMsg, true);

                    // FSR_VISIT update
                    updateFsrVisit(pMsg, getVisitNum(pMsg, svcTaskList), null, null, null, null);
                }

                // FSR_EVENT insert
                insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, null);

            } else if (MODE_ACCEPT.equals(mode)) {
                svcTaskList = getSvcTaskList(pMsg, true);

                // FSR_VISIT update
                updateFsrVisit(pMsg, getVisitNum(pMsg, svcTaskList), null, null, null, null);

                // FSR_EVENT insert
                insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, null);

            } else if (MODE_CREATE_CALL.equals(mode)) {
                svcTaskList = searchSvcTaskList(pMsg, true);

                if (!masterCheck(pMsg)) {
                    return;
                }

                fsrNum = ZYPExtnNumbering.getUniqueID(FSR_NUM_SEQ_CD);

                // SVC_TASK update
                updateSvcTask(pMsg, svcTaskList, fsrNum);
                // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                // FSR insert
                if(!insertFsr(pMsg, svcTaskList, fsrNum)) {
                    return;
                }
                // END   2018/10/03 K.Fujimoto [QC#28514, MOD]

                Map<String, String> isPoCrChkRsltmap = isPoCrHldReq(pMsg, svcTaskList, fsrNum);
                if (isPoCrChkRsltmap == null) {
                    return;
                }

                String isPOPendCust = isPoCrChkRsltmap.get("PO_CHK_RSLT");
                String isCrHld = isPoCrChkRsltmap.get("CR_CHK_RSLT");

                /*
                 * Scheduling is required?
                 */
                String schedulabelFlag = ZYPConstant.FLG_OFF_N;

                if (ZYPConstant.FLG_OFF_N.equals(isPOPendCust)
                        && ZYPConstant.FLG_OFF_N.equals(isCrHld)) {

                    schedulabelFlag = checkDefTechSts(pMsg, pMsg.xxSvcTaskList.no(0));
                }

                // FSR_VISIT insert
                fsrVisitTMsg = insertFsrVisit(pMsg, fsrNum, fsrVisitTMsg, schedulabelFlag, isPOPendCust, isCrHld, isPndIstl(pMsg));

            // mod start 2016/07/14 CSA Defect#11185
//                String mblIntfcProdCd = getMblIntfcProcCd(pMsg, pMsg.xxSvcTaskList.no(0), fsrVisitTMsg, schedulabelFlag);
            // mod end 2016/07/14 CSA Defect#11185

                // FSR_EVENT insert
                insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag);
                if (SVC_TASK_STS.TBO.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag, SVC_DISPT_EVENT.TBO);
                } else if (SVC_TASK_STS.PENDING_INSTALL.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag, SVC_DISPT_EVENT.PENDING_INSTALL);
                }

                // START 2021/10/06 R.Jabal [QC#59183, ADD]
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                paramMap.put("svcTaskNum", pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
                List<Map<String, Object>> accessPermitsList = ssmBatchClient.queryObjectList("getAccessPermits", paramMap);

                if (accessPermitsList != null && accessPermitsList.size() > 0) {
                    StringBuilder svcCmntTxt = new StringBuilder();
                    svcCmntTxt.append("Task: ").append(pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue()).append("</p>");
                    svcCmntTxt.append("Required Access Permits:</p>");

                    for (Map<String, Object> accessPermitsMap : accessPermitsList) {
                        String svcAccsPmitNum = (String) accessPermitsMap.get(SVC_ACCS_PMIT_NUM);
                        String svcAccsPmitDescTxt = (String) accessPermitsMap.get(SVC_ACCS_PMIT_DESC_TXT);
                        if (ZYPCommonFunc.hasValue(svcAccsPmitNum) || ZYPCommonFunc.hasValue(svcAccsPmitDescTxt)) {
                            svcCmntTxt.append(svcAccsPmitNum).append("-").append(svcAccsPmitDescTxt).append("</p>");
                        }
                    }

                    int svcMemoListIndex = pMsg.svcMemoList.getValidCount();
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMemoList.no(svcMemoListIndex).svcMemoTpCd, SVC_MEMO_TP.DISPATCH);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMemoList.no(svcMemoListIndex).svcCmntTxt, svcCmntTxt.toString());
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMemoList.no(svcMemoListIndex).svcMemoRsnCd, "");
                    pMsg.svcMemoList.setValidCount(svcMemoListIndex + 1);
                }
                // END 2021/10/06 R.Jabal [QC#59183, ADD]

                // SVC_MEMO insert
                insertOrUpdateSvcMemo(pMsg, fsrVisitTMsg);

                ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, fsrNum);

                updateCcyAndRateOfTask(pMsg, svcTaskList, fsrNum);

                //TODO Taizo Need to add for Attachment registration logic.

            } else if (MODE_UPDATE_CALL.equals(mode)) {
                svcTaskList = searchSvcTaskList(pMsg, true);
                FSR_VISITTMsg beforeFsrVisitTmsg = getFsrVisitForUpdate(pMsg, getVisitNum(pMsg, svcTaskList));

                if (!masterCheck(pMsg)) {
                    return;
                }

                Map<String, String> isPoCrChkRsltmap = isPoCrHldReq(pMsg, svcTaskList, fsrNum);

                if (isPoCrChkRsltmap == null) {
                    return;
                }

                String isPOPendCust = isPoCrChkRsltmap.get("PO_CHK_RSLT");
                String isCrHld = isPoCrChkRsltmap.get("CR_CHK_RSLT");

                for (Map<String, Object> svcTaskMap : svcTaskList) {

                    SVC_TASKTMsg svcTaskTMsg = getSvcTaskTMsg(pMsg, svcTaskMap, null);

                    FSR_VISITTMsg srchFsrVisitTMsg = new FSR_VISITTMsg();
                    srchFsrVisitTMsg.setSQLID("002");
                    srchFsrVisitTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                    srchFsrVisitTMsg.setConditionValue("svcTaskNum01", svcTaskTMsg.svcTaskNum.getValue());
                    FSR_VISITTMsgArray fsrVisitTMsgArry = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(srchFsrVisitTMsg);

                    if (fsrVisitTMsgArry == null) {

                        setErrMsg(pMsg, NSZM0182E);
                        return;
                    }

                    if (SVC_TASK_STS.PENDING_PO.equals(fsrVisitTMsgArry.no(0).fsrVisitStsCd.getValue())
                            || SVC_TASK_STS.CREDIT_HOLD.equals(fsrVisitTMsgArry.no(0).fsrVisitStsCd.getValue())) {

                        if (ZYPConstant.FLG_OFF_N.equals(isPOPendCust) && ZYPConstant.FLG_OFF_N.equals(isCrHld)) {

                            // SVC_TASK update
                            updateSvcTask(pMsg, svcTaskList, fsrNum);
                        } else if (SVC_TASK_STS.PENDING_PO.equals(fsrVisitTMsgArry.no(0).fsrVisitStsCd.getValue())
                                && ZYPConstant.FLG_ON_Y.equals(isCrHld)) {

                            pMsg.svcTaskStsCd.setValue(SVC_TASK_STS.CREDIT_HOLD);
                        }
                    }
                }

// START 2016/11/28 N.Arai [QC#13901, MOD]
                // SVC_TASK update
                updateSvcTasComm(pMsg, svcTaskList);
// END 2016/11/28 N.Arai [QC#13901, MOD]

                // FSR update
                // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                if(!updateFsrForCall(pMsg, svcTaskList.get(0))) {
                    return;
                }
                // END   2018/10/03 K.Fujimoto [QC#28514, MOD]

                String schedulabelFlag = checkDefTechSts(pMsg, pMsg.xxSvcTaskList.no(0));
                fsrVisitTMsg = updateFsrVisit(pMsg, getVisitNum(pMsg, svcTaskList), schedulabelFlag, isPOPendCust, isCrHld, isPndIstl(pMsg));

                // mod start 2016/07/14 CSA Defect#11185
//                String mblIntfcProdCd = getMblIntfcProcCd(pMsg, pMsg.xxSvcTaskList.no(0), fsrVisitTMsg, schedulabelFlag);
                // mod end 2016/07/14 CSA Defect#11185

                // FSR_EVENT insert
                insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag);
                if (isChangeStatus(beforeFsrVisitTmsg.fsrVisitStsCd.getValue(), fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    if (SVC_TASK_STS.TBO.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                        insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag, SVC_DISPT_EVENT.TBO);
                    } else if (SVC_TASK_STS.PENDING_INSTALL.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                        insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag, SVC_DISPT_EVENT.PENDING_INSTALL);
                    }
                }

                // SVC_MEMO insert
                insertOrUpdateSvcMemo(pMsg, fsrVisitTMsg);

                updateCcyAndRateOfTask(pMsg, svcTaskList, fsrNum);

                //TODO Taizo Need to add for Attachment registration logic.

            } else if (MODE_CANCEL_CALL.equals(mode)) {

                svcTaskList = searchSvcTaskList(pMsg, true);


                String cancelMode = getRqstTpCdMode(pMsg);
                if (MODE_ALL_CANCEL.equals(cancelMode)) {
                    // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                    if(!updateFsrForCall(pMsg, svcTaskList.get(0))) {
                        return;
                    }
                    // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
                }

                // Update FSR_VISIT
                for (int j = 0; j < pMsg.xxSvcTaskList.getValidCount(); j++) {

                    String svcTaskNum = pMsg.xxSvcTaskList.no(j).svcTaskNum.getValue();

                    FSR_VISITTMsg befUpdFsrVisitTMsg =  getFsrVisitBySvcTaskNum(pMsg.glblCmpyCd.getValue(), svcTaskNum);
                    String fsrVisitNum = befUpdFsrVisitTMsg.fsrVisitNum.getValue();
                    fsrVisitTMsg = updateFsrVisit(pMsg, fsrVisitNum, null, null, null, null);

            // mod start 2016/07/14 CSA Defect#11185
//                    String mblIntfcProdCd = null;

//                    if (SVC_TASK_STS.OPEN.equals(befUpdFsrVisitTMsg.fsrVisitStsCd.getValue()) || SVC_TASK_STS.NOTIFY_VENDOR.equals(befUpdFsrVisitTMsg.fsrVisitStsCd.getValue())) {
//                        mblIntfcProdCd = MBL_INTFC_PROC.NO_NEED;
//                    } else {
//                        mblIntfcProdCd = getMblIntfcProcCd(pMsg, pMsg.xxSvcTaskList.no(j), fsrVisitTMsg, ZYPConstant.FLG_ON_Y);
//                    }
            // mod end 2016/07/14 CSA Defect#11185

                    // Insert FSR_EVENT
                    insertFsrEventModeCancel(pMsg, fsrVisitTMsg, befUpdFsrVisitTMsg.fsrVisitStsCd.getValue(), j);
                }

                // Insert SVC_MEMO
                insertOrUpdateSvcMemo(pMsg, fsrVisitTMsg);

                // Call NSZC0050 Service Dispatch Completion API
                if (MODE_ALL_CLOSE.equals(cancelMode)) {
                    callServiceDispatchCompletionApi(pMsg);
                }
            }

            // set Email
            if (MODE_SCHEDULE.equals(mode) || MODE_CANCEL.equals(mode)) {
                if (ZYPConstant.FLG_ON_Y.equals(getEmailFlg(pMsg))) {
                    sendScheduleMail(pMsg, fsrNum, svcTaskList);
                }
            }

        } catch (S21CommonException e) {
            S21InfoLogOutput.println(e.getMessage());
        }

        // START 2018/08/30 K.Kitachi [QC#22665, ADD]
        for (String infoMsg : this.infoMsgList) {
            setErrMsg(pMsg, infoMsg);
        }
        // END 2018/08/30 K.Kitachi [QC#22665, ADD]
    }

    /**
     * updateSvcTasComm
     * @param pMsg NSZC003001PMsg
     * @param svcTaskList List<Map<String, Object>>
     */
    private void updateSvcTasComm(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList) {

        for (Map<String, Object> svcTaskMap : svcTaskList) {
            SVC_TASKTMsg svcTaskTMsg = getSvcTaskTMsg(pMsg, svcTaskMap, null);

            String prtyCd = getPrtyCdFromPMsg(pMsg, (String) svcTaskMap.get(SVC_TASK_NUM));
            if (!ZYPCommonFunc.hasValue(prtyCd)) {
                continue;
            }

            if (prtyCd.equals(svcTaskTMsg.svcCallPrtyCd.getValue())) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcCallPrtyCd, prtyCd);
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcCallPrtyChngDt, sysDt);

            S21ApiTBLAccessor.update(svcTaskTMsg);
            checkRtnCodeForUpdate(pMsg, svcTaskTMsg);
        }
    }

    private Map<String, String> isPoCrHldReq(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, String fsrNum) {

        Map<String, String> isPoCrChkRsltmap = new HashMap<String, String>();

        String isCrHld = ZYPConstant.FLG_OFF_N;
        String isPOPendCust = ZYPConstant.FLG_OFF_N;

        boolean chkReq = false;

        for (Map<String, Object> svcTaskMap : svcTaskList) {

            SVC_TASKTMsg svcTaskTMsg = getSvcTaskTMsg(pMsg, svcTaskMap, null);

            if (!SVC_TASK_STS.OPEN.equals(svcTaskTMsg.svcTaskStsCd.getValue())) {
                continue;
            }

            // START 2018/04/05 K.Kitachi [QC#23988, MOD]
            SVC_BILL_TPTMsg srchSvcBilTpTMsg = new SVC_BILL_TPTMsg();
            srchSvcBilTpTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            srchSvcBilTpTMsg.svcBillTpCd.setValue(svcTaskTMsg.svcBillTpCd.getValue());

            SVC_BILL_TPTMsg svcBilTpTMsg = (SVC_BILL_TPTMsg) S21ApiTBLAccessor.findByKey(srchSvcBilTpTMsg);

            if (ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.lborChrgFlg.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.prtChrgFlg.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.drumChrgFlg.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.expChrgFlg.getValue())
            ) {

                chkReq = true;
                break;
            }
            // END 2018/04/05 K.Kitachi [QC#23988, MOD]
        }

        // START 2018/04/05 K.Kitachi [QC#23988, MOD]
        if (chkReq) {

            /*
             * PO Check
             */
            isPOPendCust = checkCustIssPO(pMsg);
        }

        if (ZYPConstant.FLG_OFF_N.equals(isPOPendCust)) {

            /*
             * Credit Check
             */
            isCrHld = checkCustCrPrfl(pMsg, svcTaskList, fsrNum);

            if (!ZYPCommonFunc.hasValue(isCrHld)) {

                return null;
            }
        }
        // END 2018/04/05 K.Kitachi [QC#23988, MOD]

        isPoCrChkRsltmap.put("PO_CHK_RSLT", isPOPendCust);
        isPoCrChkRsltmap.put("CR_CHK_RSLT", isCrHld);
        return isPoCrChkRsltmap;
    }


    private void init(NSZC003001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        msgMap = new S21ApiMessageMap(pMsg);

        sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS);

        String sysDtTm = sysTs.substring(0, LEN_DT_TM);

        sysDt = sysDtTm.substring(0, LEN_DT);

        sysTm = sysDtTm.substring(LEN_DT, sysDtTm.length());

        techMstrTMsg = null;

        onBatType = onBatchType;

        // START 2018/08/30 K.Kitachi [QC#22665, ADD]
        this.infoMsgList = new ArrayList<String>();
        // END 2018/08/30 K.Kitachi [QC#22665, ADD]
    }

    private boolean inputCheck(NSZC003001PMsg pMsg) {
        boolean retFlg = true;

        if (!mondatoryCheck(pMsg)) {
            return false;
        }

        String mode = pMsg.xxBizProcTp.getValue();
        int size = pMsg.xxSvcTaskList.getValidCount();
        for (int i = 0; i < size; i++) {
            if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).svcCallPrtyCd)) {
                if (!ZYPCommonFunc.isNumberCheck(pMsg.xxSvcTaskList.no(i).svcCallPrtyCd.getValue())) {
                    setErrMsg(pMsg, NSZM0147E, i);
                    retFlg = false;
                }
            }
            if (MODE_CREATE_CALL.equals(mode) || MODE_UPDATE_CALL.equals(mode)) {

                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techCd)
                        && !checkTechMstr(pMsg)) {
                    setErrMsg(pMsg, NSZM0076E);
                    return false;
                }

                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).techSchdFromDt)) {
                    if (!ZYPDateUtil.isValidDate(pMsg.xxSvcTaskList.no(i).techSchdFromDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                        setErrMsg(pMsg, ZZBM0083E, i);
                        retFlg = false;
                    }
                }
            }

            if (MODE_UPDATE_CALL.equals(mode)) {
                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).techSchdToDt)) {
                    if (!ZYPDateUtil.isValidDate(pMsg.xxSvcTaskList.no(i).techSchdToDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                        setErrMsg(pMsg, ZZBM0083E, i);
                        retFlg = false;
                    }
                }
                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).techAcptDt)) {
                    if (!ZYPDateUtil.isValidDate(pMsg.xxSvcTaskList.no(i).techAcptDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                        setErrMsg(pMsg, ZZBM0083E, i);
                        retFlg = false;
                    }
                }
                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).fsrVisitDisptDt)) {
                    if (!ZYPDateUtil.isValidDate(pMsg.xxSvcTaskList.no(i).fsrVisitDisptDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                        setErrMsg(pMsg, ZZBM0083E, i);
                        retFlg = false;
                    }
                }
            }
        }

        // techCd check
        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode) || MODE_POST_DISPT.equals(mode)) {
            if (!checkTechMstr(pMsg)) {
                setErrMsg(pMsg, NSZM0076E);
                return false;
            }
        }
        // cancel check
        if (MODE_CANCEL_CALL.equals(mode)) {
            if (!cancelCheck(pMsg)) {
                return false;
            }
        }

        return retFlg;
    }

    private boolean cancelCheck(NSZC003001PMsg pMsg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String rqstTpCd = pMsg.xxRqstTpCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        boolean returnFlg = true;
        boolean allCanceModel = true;
        boolean partialCanceModel = false;

        // PARTIAL CANCEL
        if (PARTIAL_CANCEL.equals(rqstTpCd)) {
            for (int j = 0; j < pMsg.xxSvcTaskList.getValidCount(); j++) {
                String svcTaskNum = pMsg.xxSvcTaskList.no(j).svcTaskNum.getValue();
                SVC_TASKTMsg  svcTaskTMsg = getSvcTaskFindByKey(glblCmpyCd, svcTaskNum);
                FSR_VISITTMsg fsrVisitTMsg = getFsrVisitBySvcTaskNum(glblCmpyCd, svcTaskNum);
                if (svcTaskTMsg != null && fsrVisitTMsg != null && !checkFsrVisitStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    setErrMsg(pMsg, NSZM0814E);
                    return false;
                }
            }
        }

        SVC_TASKTMsgArray svcTaskStsInfo = getSvcTaskByFsrNum(glblCmpyCd, fsrNum);
        for (int i = 0; i < svcTaskStsInfo.getValidCount(); i++) {
            String svcTaskNum = svcTaskStsInfo.no(i).svcTaskNum.getValue();

            if (PARTIAL_CANCEL.equals(rqstTpCd)) {
                // PARTIAL CANCEL
                boolean partialCanceFlg = false;
                for (int j = 0; j < pMsg.xxSvcTaskList.getValidCount(); j++) {
                    String paramSvcTaskNum = pMsg.xxSvcTaskList.no(j).svcTaskNum.getValue();
                    if (svcTaskNum.equals(paramSvcTaskNum)) {
                        partialCanceFlg = true;
                        break;
                    }
                }
                if (!partialCanceFlg) {
                    partialCanceModel = true;
                }
            } else {
                FSR_VISITTMsg fsrVisitTMsg = getFsrVisitBySvcTaskNum(glblCmpyCd, svcTaskNum);
                if (!checkFsrVisitStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    setErrMsg(pMsg, NSZM0814E);
                    return false;
                }

                // ALL CANCEL
                boolean allCanceFlg = false;
                for (int j = 0; j < pMsg.xxSvcTaskList.getValidCount(); j++) {
                    String paramSvcTaskNum = pMsg.xxSvcTaskList.no(j).svcTaskNum.getValue();
                    if (svcTaskNum.equals(paramSvcTaskNum)) {
                        allCanceFlg = true;
                        break;
                    }
                }
                if (!allCanceFlg) {
                    allCanceModel = false;
                }
            }
        }

        if (PARTIAL_CANCEL.equals(rqstTpCd) && !partialCanceModel) {
            setErrMsg(pMsg, NSZM0816E);
            returnFlg =  false;
        }

        if (!PARTIAL_CANCEL.equals(rqstTpCd) && !allCanceModel) {
            setErrMsg(pMsg, NSZM0815E);
            returnFlg =  false;
        }
        return returnFlg;
    }

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

    private boolean mondatoryCheck(NSZC003001PMsg pMsg) {
        boolean retFlg = true;
        NSZC003001_xxSvcTaskListPMsg dtlPMsg = null;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0001E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.xxBizProcTp)) {
            setErrMsg(pMsg, NSZM0003E);
            retFlg = false;
        }

        if (MODE_CANCEL.equals(pMsg.xxBizProcTp.getValue()) || MODE_ACCEPT.equals(pMsg.xxBizProcTp.getValue()) || MODE_UPDATE_CALL.equals(pMsg.xxBizProcTp.getValue()) || MODE_CANCEL_CALL.equals(pMsg.xxBizProcTp.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
                setErrMsg(pMsg, NSZM0180E);
                retFlg = false;
            }
        }

        int size = pMsg.xxSvcTaskList.getValidCount();
        if (size < 1) {
            setErrMsg(pMsg, NSZM0164E);
            return false;
        }

        if (MODE_SCHEDULE.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.techCd)) {
                    setErrMsg(pMsg, NSZM0052E, i);
                    retFlg = false;
                }
                if (ZYPCommonFunc.hasValue(dtlPMsg.techSchdFromTm)) {
                    if (!ZYPCommonFunc.hasValue(dtlPMsg.techSchdFromDt)) {
                        setErrMsg(pMsg, NSZM0158E, i);
                        retFlg = false;
                    }
                }
                if (ZYPCommonFunc.hasValue(dtlPMsg.techSchdToTm)) {
                    if (!ZYPCommonFunc.hasValue(dtlPMsg.techSchdToDt)) {
                        setErrMsg(pMsg, NSZM0160E, i);
                        retFlg = false;
                    }
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        } else if (MODE_DISPATCH.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.techCd)) {
                    setErrMsg(pMsg, NSZM0052E, i);
                    retFlg = false;
                }
                if (ZYPCommonFunc.hasValue(dtlPMsg.techSchdFromTm)) {
                    if (!ZYPCommonFunc.hasValue(dtlPMsg.techSchdFromDt)) {
                        setErrMsg(pMsg, NSZM0158E, i);
                        retFlg = false;
                    }
                }
                if (ZYPCommonFunc.hasValue(dtlPMsg.techSchdToTm)) {
                    if (!ZYPCommonFunc.hasValue(dtlPMsg.techSchdToDt)) {
                        setErrMsg(pMsg, NSZM0160E, i);
                        retFlg = false;
                    }
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        } else if (MODE_CANCEL.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        } else if (MODE_ACCEPT.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        } else if (MODE_POST_DISPT.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.techCd)) {
                    setErrMsg(pMsg, NSZM0052E, i);
                    retFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        } else if (MODE_CREATE_CALL.equals(pMsg.xxBizProcTp.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.svcPblmTpCd)) {
                setErrMsg(pMsg, NSZM0243E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcCallSrcTpCd)) {
                setErrMsg(pMsg, NSZM0544E);
                retFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
                setErrMsg(pMsg, NSZM0015E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                setErrMsg(pMsg, NSZM0011E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
                setErrMsg(pMsg, NSZM0012E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
                setErrMsg(pMsg, NSZM0017E);
                retFlg = false;
            }

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }

                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        } else if (MODE_UPDATE_CALL.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
                // START 2019/03/27 S.Kitamura [QC#30906, DEL]
//                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
//                    setErrMsg(pMsg, NSZM0163E, i);
//                    retFlg = false;
//                }
                // END 2019/03/27 S.Kitamura [QC#30906, DEL]
            }
        } else if (MODE_CANCEL_CALL.equals(pMsg.xxBizProcTp.getValue())) {

            for (int i = 0; i < size; i++) {
                dtlPMsg = pMsg.xxSvcTaskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(dtlPMsg.schdDisptEmlFlg)) {
                    setErrMsg(pMsg, NSZM0157E, i);
                    retFlg = false;
                }

                if (!ZYPCommonFunc.hasValue(dtlPMsg.userId)) {
                    setErrMsg(pMsg, NSZM0163E, i);
                    retFlg = false;
                }
            }
        }
        return retFlg;
    }

    private boolean combinationCheck(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList) {
        int size = pMsg.xxSvcTaskList.getValidCount();
        int listSize = svcTaskList.size();

        for (int i = 0; i < listSize; i++) {
            if (!ZYPCommonFunc.hasValue((BigDecimal) svcTaskList.get(i).get(ORG_LAYER_NUM))) {
                setErrMsg(pMsg, NSZM0384E);
                return false;
            }
            if (!ZYPCommonFunc.hasValue((String) svcTaskList.get(i).get(ORG_CD))) {
                setErrMsg(pMsg, NSZM0385E);
                return false;
            }
        }

        if (size <= 1) {
            return true;
        }

        for (int i = 1; i < size; i++) {
            if (!combinationCheck(pMsg, pMsg.xxSvcTaskList.no(0), pMsg.xxSvcTaskList.no(i))) {
                return false;
            }
        }

        if (listSize <= 1) {
            return true;
        }

        for (int i = 1; i < listSize; i++) {
            if (!combinationCheck(pMsg, svcTaskList.get(0), svcTaskList.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean masterCheck(NSZC003001PMsg pMsg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        if (ZYPCommonFunc.hasValue(pMsg.svcPblmTpCd)) {
            SVC_PBLM_TPTMsgArray svcPblmTpArray = getSvcPblmTp(glblCmpyCd, pMsg.svcPblmTpCd.getValue());
            if (svcPblmTpArray == null || svcPblmTpArray.getValidCount() < 1) {
                setErrMsg(pMsg, NSZM0550E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcCallAvoidCd)) {
            SVC_CALL_AVOIDTMsg svcCallAvoidTMsg = (SVC_CALL_AVOIDTMsg) ZYPCodeDataUtil.findByCode(SVC_CALL_AVOID.class, glblCmpyCd, pMsg.svcCallAvoidCd.getValue());
            if (svcCallAvoidTMsg == null) {
                setErrMsg(pMsg, NSZM0551E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcCallSrcTpCd)) {
            SVC_CALL_SRC_TPTMsg svcCallSrcTpTMsg = (SVC_CALL_SRC_TPTMsg) ZYPCodeDataUtil.findByCode(SVC_CALL_SRC_TP.class, glblCmpyCd, pMsg.svcCallSrcTpCd.getValue());
            if (svcCallSrcTpTMsg == null) {
                setErrMsg(pMsg, NSZM0552E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcCallRqstOwnrTocCd)) {

            S21_PSNTMsg s21PsnTMsg = getS21Psn(glblCmpyCd, pMsg.svcCallRqstOwnrTocCd.getValue());

            if (s21PsnTMsg == null || ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {
                setErrMsg(pMsg, NSZM0554E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.stCd)) {
            STTMsg stTMsg = (STTMsg) ZYPCodeDataUtil.findByCode(ST.class, glblCmpyCd, pMsg.stCd.getValue());
            if (stTMsg == null) {
                setErrMsg(pMsg, NSZM0555E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.postCd)) {
            POSTTMsgArray postArray = getPost(glblCmpyCd, pMsg.postCd.getValue());
            if (postArray == null || postArray.getValidCount() < 1) {
                setErrMsg(pMsg, NSZM0556E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.ctryCd)) {
            CTRYTMsg ctryTMsg = (CTRYTMsg) ZYPCodeDataUtil.findByCode(CTRY.class, glblCmpyCd, pMsg.ctryCd.getValue());
            if (ctryTMsg == null) {
                setErrMsg(pMsg, NSZM0557E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
            BILL_TO_CUSTTMsg billToCustTMsg = getBillToCustTMsg(glblCmpyCd, pMsg.billToCustCd.getValue());
            if (billToCustTMsg == null) {
                setErrMsg(pMsg, NSZM0558E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) ZYPCodeDataUtil.findByCode(PMT_TERM_CASH_DISC.class, glblCmpyCd, pMsg.pmtTermCashDiscCd.getValue());
            if (pmtTermCashDiscTMsg == null) {
                setErrMsg(pMsg, NSZM0559E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCustTMsg(glblCmpyCd, pMsg.shipToCustCd.getValue());
            if (shipToCustTMsg == null) {
                setErrMsg(pMsg, NSZM0560E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.billToUpdCustCd)) {

            if (getBillToCustTMsg(glblCmpyCd, pMsg.billToUpdCustCd.getValue()) == null) {

                setErrMsg(pMsg, NSZM0593E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.shipToUpdCustCd)) {

            if (getShipToCustTMsg(glblCmpyCd, pMsg.shipToUpdCustCd.getValue()) == null) {

                setErrMsg(pMsg, NSZM0594E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.fsrTpCd)) {

            if (getFsrTpTMsg(glblCmpyCd, pMsg.fsrTpCd.getValue()) == null) {

                setErrMsg(pMsg, NSZM0597E);
                return false;
            }
        }
        for (int i = 0; i < pMsg.xxSvcTaskList.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).svcAsgTpCd)) {

                if (getSvcAsgTpTMsg(glblCmpyCd, pMsg.xxSvcTaskList.no(i).svcAsgTpCd.getValue()) == null) {

                    setErrMsg(pMsg, NSZM0606E);
                    return false;
                }
            }
        }

        int size = pMsg.svcMemoList.getValidCount();
        for (int i = 0; i < size; i++) {
            if (ZYPCommonFunc.hasValue(pMsg.svcMemoList.no(i).svcMemoTpCd)) {
                SVC_MEMO_TPTMsg svcMemoTpTMsg = (SVC_MEMO_TPTMsg) ZYPCodeDataUtil.findByCode(SVC_MEMO_TP.class, glblCmpyCd, pMsg.svcMemoList.no(i).svcMemoTpCd.getValue());
                if (svcMemoTpTMsg == null) {
                    setErrMsg(pMsg, NSZM0547E);
                    return false;
                }
            }

        }

        return true;
    }

    /**
     * combinationCheck for PMsg
     * @param pMsg NSZC003001PMsg
     * @param srcMsg NSZC003001_xxSvcTaskListPMsg
     * @param destMsg NSZC003001_xxSvcTaskListPMsg
     * @return boolean
     */
    private boolean combinationCheck(NSZC003001PMsg pMsg, NSZC003001_xxSvcTaskListPMsg srcMsg, NSZC003001_xxSvcTaskListPMsg destMsg) {

        if (!isEquealStr(srcMsg.techCd.getValue(), destMsg.techCd.getValue())) {
            setErrMsg(pMsg, NSZM0150E);
            return false;
        }
        if (!isEquealStr(srcMsg.techSchdFromDt.getValue(), destMsg.techSchdFromDt.getValue())) {
            setErrMsg(pMsg, NSZM0165E);
            return false;
        }
        if (!isEquealStr(srcMsg.techSchdFromTm.getValue(), destMsg.techSchdFromTm.getValue())) {
            setErrMsg(pMsg, NSZM0165E);
            return false;
        }
        if (!isEquealStr(srcMsg.techSchdToDt.getValue(), destMsg.techSchdToDt.getValue())) {
            setErrMsg(pMsg, NSZM0165E);
            return false;
        }
        if (!isEquealStr(srcMsg.techSchdToTm.getValue(), destMsg.techSchdToTm.getValue())) {
            setErrMsg(pMsg, NSZM0165E);
            return false;
        }
        return true;
    }

    /**
     * combinationCheck for DB data
     * @param pMsg NSZC003001PMsg
     * @param srcMap Map<String, Object>
     * @param destMap Map<String, Object>
     * @return boolean
     */
    private boolean combinationCheck(NSZC003001PMsg pMsg, Map<String, Object> srcMap, Map<String, Object> destMap) {

        if (!isEquealStr(srcMap.get(SO_NUM), destMap.get(SO_NUM))) {
            setErrMsg(pMsg, NSZM0155E);
            return false;
        }
        if (!isEquealNum((BigDecimal) srcMap.get(ORG_LAYER_NUM), (BigDecimal) destMap.get(ORG_LAYER_NUM))) {
            setErrMsg(pMsg, NSZM0386E);
            return false;
        }
        if (!isEquealStr(srcMap.get(ORG_CD), destMap.get(ORG_CD))) {
            setErrMsg(pMsg, NSZM0387E);
            return false;
        }
        if (!isEquealStr(srcMap.get(MDL_NM), destMap.get(MDL_NM))) {
            setErrMsg(pMsg, NSZM0149E);
            return false;
        }
        if (!isEquealStr(srcMap.get(ORIG_INV_CCY_CD), destMap.get(ORIG_INV_CCY_CD))) {
            setErrMsg(pMsg, NSZM0152E);
            return false;
        }
        if (!isEquealStr(srcMap.get(INV_CCY_CD), destMap.get(INV_CCY_CD))) {
            setErrMsg(pMsg, NSZM0153E);
            return false;
        }
        if (!isEquealNum((BigDecimal) srcMap.get(SVC_MACH_MSTR_PK), (BigDecimal) destMap.get(SVC_MACH_MSTR_PK))) {
            setErrMsg(pMsg, NSZM0148E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue((String) srcMap.get(SO_NUM)) && !ZYPCommonFunc.hasValue((BigDecimal) srcMap.get(SVC_MACH_MSTR_PK))) {
            if (!isEquealStr(srcMap.get(SHIP_TO_CUST_CD), destMap.get(SHIP_TO_CUST_CD))) {
                setErrMsg(pMsg, NSZM0166E);
                return false;
            }
        }
        if (!isEquealStr(srcMap.get(PMT_TERM_CASH_DISC_CD), destMap.get(PMT_TERM_CASH_DISC_CD))) {
            setErrMsg(pMsg, NSZM0352E);
            return false;
        }

        return true;
    }

    private boolean isEquealStr(Object str1, Object str2) {
        if (str1 != null) {
            return ((String) str1).equals((String) str2);
        } else if (str2 == null) {
            return true;
        }
        return false;
    }

    private boolean isEquealNum(BigDecimal num1, BigDecimal num2) {
        if (num1 != null) {
            return (num1.compareTo(num2) == 0);
        } else if (num2 == null) {
            return true;
        }
        return false;
    }

    private void svcTaskStsCheck(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList) {
        String mode = pMsg.xxBizProcTp.getValue();
        String svcTaskStsCd = null;
        String fsrVisitStsCd = null;
        String errCd = null;

        // Task Status Check
        for (Map<String, Object> svcTaskMap : svcTaskList) {

            svcTaskStsCd = (String) svcTaskMap.get(SVC_TASK_STS_CD);

            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {
                // Status check
                if (!SVC_TASK_STS.APPROVED.equals(svcTaskStsCd) && !SVC_TASK_STS.IN_PROCESS.equals(svcTaskStsCd)) {
                    errCd = NSZM0146E;
                    setErrMsg(pMsg, errCd, (String) svcTaskMap.get(SVC_TASK_NUM));
                }

                // Hold check
                if (!ZYPCommonFunc.hasValue((String) svcTaskMap.get(FSR_NUM))) {
                    if (ZYPConstant.FLG_ON_Y.equals(svcTaskMap.get(SVC_CR_HLD_FLG))) {
                        errCd = NSZM0151E;
                        setErrMsg(pMsg, errCd, (String) svcTaskMap.get(SVC_TASK_NUM));
                    }
                }
            } else if (MODE_CANCEL.equals(mode) || MODE_ACCEPT.equals(mode)) {
                // Status check
                if (!SVC_TASK_STS.IN_PROCESS.equals(svcTaskStsCd)) {
                    errCd = NSZM0146E;
                    setErrMsg(pMsg, errCd, (String) svcTaskMap.get(SVC_TASK_NUM));
                }
            } else if (MODE_POST_DISPT.equals(mode)) {
                // Status check
                if (!ZYPCommonFunc.hasValue((String) svcTaskMap.get(FSR_NUM))) {
                    if (!SVC_TASK_STS.OPEN.equals(svcTaskStsCd)) {
                        errCd = NSZM0146E;
                        setErrMsg(pMsg, errCd, (String) svcTaskMap.get(SVC_TASK_NUM));
                    }
                }
            }
        }

        // FSR Visit Status Check
        boolean isFsrCheck = false;
        for (Map<String, Object> svcTaskMap : svcTaskList) {
            if (ZYPCommonFunc.hasValue((String) svcTaskMap.get(FSR_NUM))) {
                fsrVisitStsCd = (String) svcTaskMap.get(FSR_VISIT_STS_CD);
                isFsrCheck = true;
                break;
            }
        }

        if (isFsrCheck) {
            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {
                if (!SVC_TASK_STS.CONTINUOUS_OPEN.equals(fsrVisitStsCd) && !SVC_TASK_STS.SCHEDULED.equals(fsrVisitStsCd)) {
                    errCd = NSZM0154E;
                    setErrMsg(pMsg, errCd);
                }
            } else if (MODE_CANCEL.equals(mode)) {
                if (!SVC_TASK_STS.CONTINUOUS_OPEN.equals(fsrVisitStsCd) && !SVC_TASK_STS.SCHEDULED.equals(fsrVisitStsCd) && !SVC_TASK_STS.DISPATCHED.equals(fsrVisitStsCd)) {
                    errCd = NSZM0154E;
                    setErrMsg(pMsg, errCd);
                }
            } else if (MODE_ACCEPT.equals(mode)) {
                if (!SVC_TASK_STS.SCHEDULED.equals(fsrVisitStsCd)) {
                    errCd = NSZM0154E;
                    setErrMsg(pMsg, errCd);
                }
            }
        }

        if (errCd != null) {
            throw new S21CommonException(errCd);
        }
    }

    private List<Map<String, Object>> getSvcTaskList(NSZC003001PMsg pMsg, boolean isAllFlg) {
        List<String> svcTaskNumList = new ArrayList<String>();
        List<Map<String, Object>> svcTaskList = new ArrayList<Map<String, Object>>();
        int size = pMsg.xxSvcTaskList.getValidCount();

        for (int i = 0; i < size; i++) {
            svcTaskNumList.add(pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue());
        }

        if (isAllFlg) {
            svcTaskList = getSvcTaskList(pMsg, pMsg.fsrNum.getValue(), svcTaskNumList);
        } else {
            svcTaskList = getSvcTaskList(pMsg, null, svcTaskNumList);
        }
        if (svcTaskList == null || svcTaskList.size() < 1) {
            setErrMsg(pMsg, NSZM0079E);
            throw new S21CommonException(NSZM0079E);
        }

        svcTaskStsCheck(pMsg, svcTaskList);

        return svcTaskList;
    }

    private List<Map<String, Object>> searchSvcTaskList(NSZC003001PMsg pMsg, boolean isAllFlg) {
        List<String> svcTaskNumList = new ArrayList<String>();
        List<Map<String, Object>> svcTaskList = new ArrayList<Map<String, Object>>();
        int size = pMsg.xxSvcTaskList.getValidCount();

        for (int i = 0; i < size; i++) {
            svcTaskNumList.add(pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue());
        }

        if (isAllFlg) {
            svcTaskList = searchSvcTaskList(pMsg, pMsg.fsrNum.getValue(), svcTaskNumList);
        } else {
            svcTaskList = searchSvcTaskList(pMsg, null, svcTaskNumList);
        }
        if (svcTaskList == null || svcTaskList.size() < 1) {
            setErrMsg(pMsg, NSZM0079E);
            throw new S21CommonException(NSZM0079E);
        }

        svcTaskStsCheck(pMsg, svcTaskList);

        return svcTaskList;
    }

    private boolean checkUpdateSchedule(List<Map<String, Object>> svcTaskList, String mode) {
        for (Map<String, Object> svcTaskMap : svcTaskList) {
            if (MODE_SCHEDULE.equals(mode)) {
                if (SVC_TASK_STS.SCHEDULED.equals(svcTaskMap.get(FSR_VISIT_STS_CD))) {
                    return true;
                }
            } else if (MODE_DISPATCH.equals(mode)) {
                if (SVC_TASK_STS.DISPATCHED.equals(svcTaskMap.get(FSR_VISIT_STS_CD))) {
                    return true;
                }
            }
        }
        return false;
    }

    private SVC_TASKTMsg getSvcTaskTMsg(NSZC003001PMsg pMsg, Map<String, Object> svcTaskMap, String fsrNum) {
        SVC_TASKTMsg paramTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(paramTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramTMsg.svcTaskNum, (String) svcTaskMap.get(SVC_TASK_NUM));
        // START 2018/01/15 U.Kim [QC#19702, MOD]
        // SVC_TASKTMsg svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
        SVC_TASKTMsg svcTaskTMsg = null;
        try {
            svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
        } catch (EZDDBRecordLockedException e) {
            svcTaskTMsg = getSvcTaskTMsgForUpdateWait(paramTMsg);
        }
        // END 2018/01/15 U.Kim [QC#19702, MOD]
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0079E);
            throw new S21CommonException(NSZM0079E);
        }

        return svcTaskTMsg;
    }

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    private SVC_TASKTMsg getSvcTaskTMsgForUpdateWait(SVC_TASKTMsg svcTaskTMsg) {
        try {
            return (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTaskTMsg, this.waitSecUpdTaskOther);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }

    // END 2018/01/15 U.Kim [QC#19702, ADD]

    private void updateSvcTask(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, String fsrNum) {
        String mode = pMsg.xxBizProcTp.getValue();

        for (Map<String, Object> svcTaskMap : svcTaskList) {
            SVC_TASKTMsg svcTaskTMsg = getSvcTaskTMsg(pMsg, svcTaskMap, fsrNum);

            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {

                String prtyCd = getPrtyCdFromPMsg(pMsg, (String) svcTaskMap.get(SVC_TASK_NUM));
                if (ZYPCommonFunc.hasValue(prtyCd)) {
                    ZYPEZDItemValueSetter.setValue(svcTaskTMsg.origSvcCallPrtyCd, svcTaskTMsg.svcCallPrtyCd.getValue());
                    ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcCallPrtyCd, prtyCd);
                    ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcCallPrtyChngDt, sysDt);
                }

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.fsrNum, fsrNum);
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskStsCd, SVC_TASK_STS.IN_PROCESS);

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskSchdDt, sysDt);
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskSchdTm, sysTm);
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskSchdByUsrId, pMsg.xxSvcTaskList.no(0).userId.getValue());

            } else if (MODE_CANCEL.equals(mode)) {

                svcTaskTMsg.fsrNum.clear();
                svcTaskTMsg.svcTaskSchdDt.clear();
                svcTaskTMsg.svcTaskSchdTm.clear();
                svcTaskTMsg.svcTaskSchdByUsrId.clear();

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskStsCd, SVC_TASK_STS.APPROVED);

            } else if (MODE_ACCEPT.equals(mode)) {

                return;

            } else if (MODE_POST_DISPT.equals(mode)) {

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.fsrNum, fsrNum);
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskStsCd, SVC_TASK_STS.IN_PROCESS);
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.postDisptFlg, ZYPConstant.FLG_ON_Y);

            } else if (MODE_CREATE_CALL.equals(mode)) {

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.fsrNum, fsrNum);

            } else if (MODE_UPDATE_CALL.equals(mode)) {

                BigDecimal rspMnAot = BigDecimal.ZERO;

                String erlStartDt = sysDt;
                String erlStartTm = sysTm;
                String erlStartTs = sysTs;

                if ((pMsg.svcCallIncdtDt.getValue() + pMsg.svcCallIncdtTm.getValue()).compareTo(sysDt + sysTm) > 0) {

                    erlStartDt = pMsg.svcCallIncdtDt.getValue();
                    erlStartTm = pMsg.svcCallIncdtTm.getValue();

                    StringBuilder sb = new StringBuilder();
                    sb.append(pMsg.svcCallIncdtDt.getValue());
                    sb.append(pMsg.svcCallIncdtTm.getValue());
                    sb.append(TS_POSTFIX);
                    erlStartTs = sb.toString();
                }

                erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                        pMsg.glblCmpyCd.getValue()
                      , BigDecimal.ZERO
                      , pMsg.svcMachMstrPk.getValue()
                      , erlStartTs.substring(0, 8)
                      , erlStartTs.substring(8, 14)
                      , false
                      // START 2019/07/31 K.Kitachi [QC#52257, ADD]
                      , svcTaskTMsg.dsSvcCallTpCd.getValue()
                      // END 2019/07/31 K.Kitachi [QC#52257, ADD]
                      // START 2022/04/11 K.Kitachi [QC#59899, ADD]
                      , pMsg.shipToUpdCustCd.getValue()
                      // END 2022/04/11 K.Kitachi [QC#59899, ADD]
                );

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.erlStartTs, erlStartTs);

                erlStartDt = erlStartTs.substring(0, 8);
                erlStartTm = erlStartTs.substring(8, 14);

                if (ZYPCommonFunc.hasValue(svcTaskTMsg.svcRspTmMnAot)) {

                    rspMnAot = svcTaskTMsg.svcRspTmMnAot.getValue();
                }

                String lateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                        pMsg.glblCmpyCd.getValue()
                      , rspMnAot
                      , pMsg.svcMachMstrPk.getValue()
                      , erlStartDt
                      , erlStartTm
                      , false
                      // START 2019/07/31 K.Kitachi [QC#52257, ADD]
                      , svcTaskTMsg.dsSvcCallTpCd.getValue()
                      // END 2019/07/31 K.Kitachi [QC#52257, ADD]
                      // START 2022/04/11 K.Kitachi [QC#59899, ADD]
                      , pMsg.shipToUpdCustCd.getValue()
                      // END 2022/04/11 K.Kitachi [QC#59899, ADD]
                );

                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.lateStartTs, lateStartTs);
        }

            S21ApiTBLAccessor.update(svcTaskTMsg);

            checkRtnCodeForUpdate(pMsg, svcTaskTMsg);
        }
    }

    private String getPrtyCdFromPMsg(NSZC003001PMsg pMsg, String svcTaskNum) {
        int size = pMsg.xxSvcTaskList.getValidCount();
        for (int i = 0; i < size; i++) {
            if (svcTaskNum.equals(pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue())) {
                return pMsg.xxSvcTaskList.no(i).svcCallPrtyCd.getValue();
            }
        }
        return null;
    }

    private String getVisitNum(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList) {

        String prmSvcTaskNum = pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue();

        for (Map<String, Object> svcTaskMap : svcTaskList) {
            String svcTaskNum = (String) svcTaskMap.get(SVC_TASK_NUM);
            if (ZYPCommonFunc.hasValue(svcTaskNum) && svcTaskNum.equals(prmSvcTaskNum)) {
                return (String) svcTaskMap.get(FSR_VISIT_NUM);
            }
        }
        return FSR_VISIT_NUM_01;
    }

    private boolean insertFsr(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, String fsrNum) {
        String mode = pMsg.xxBizProcTp.getValue();
        Map<String, Object> svcTaskMap = svcTaskList.get(0);

        FSRTMsg fsrTMsg = new FSRTMsg();

        SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCustTMsg(pMsg, (String) svcTaskMap.get(SHIP_TO_CUST_CD));

        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode) || MODE_POST_DISPT.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(fsrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrStsCd, SVC_TASK_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.svcMachMstrPk, (BigDecimal) svcTaskMap.get(SVC_MACH_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(fsrTMsg.serNum, (String) svcTaskMap.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(fsrTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrCratDt, sysDt);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrCratTm, sysTm);
            //ZYPEZDItemValueSetter.setValue(fsrTMsg.billToCustCd, shipToCustTMsg.billToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.sellToCustCd, shipToCustTMsg.sellToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.shipToCustCd, (String) svcTaskMap.get(SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(fsrTMsg.origInvCcyCd, (String) svcTaskMap.get(ORIG_INV_CCY_CD));
            ZYPEZDItemValueSetter.setValue(fsrTMsg.invCcyCd, (String) svcTaskMap.get(INV_CCY_CD));
            ZYPEZDItemValueSetter.setValue(fsrTMsg.ccyExchRate, (BigDecimal) svcTaskMap.get(CCY_EXCH_RATE));
            ZYPEZDItemValueSetter.setValue(fsrTMsg.istlStsUpdCpltFlg, ZYPConstant.FLG_OFF_N);
            // QC#2191 Add start
            ZYPEZDItemValueSetter.setValue(fsrTMsg.pmtTermCashDiscCd, (String) svcTaskMap.get(PMT_TERM_CASH_DISC_CD));
            // QC#2191 end

        } else if (MODE_CANCEL.equals(mode)) {
            // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
            return true;
            // END 2018/10/03 K.Fujimoto [QC#28514, ADD]
        } else if (MODE_ACCEPT.equals(mode)) {
            // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
            return true;
            // END 2018/10/03 K.Fujimoto [QC#28514, ADD]
        } else if (MODE_CREATE_CALL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(fsrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrStsCd, SVC_TASK_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.serNum, pMsg.serNum);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrCratDt, sysDt);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrCratTm, sysTm);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.billToCustCd, pMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.sellToCustCd, shipToCustTMsg.sellToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrTMsg.shipToCustCd, pMsg.shipToCustCd);

            ZYPEZDItemValueSetter.setValue(fsrTMsg.istlStsUpdCpltFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(fsrTMsg.svcCallAvoidCd, pMsg.svcCallAvoidCd);

            // START 2018/07/12 M.Naito [QC#13309, ADD]
            ZYPEZDItemValueSetter.setValue(fsrTMsg.tempEttlNum, pMsg.tempEttlNum);
            // END 2018/07/12 M.Naito [QC#13309, ADD]

            // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
            //If setFsrForCall is Error, not create FSR.
            if(!setFsrForCall(pMsg, svcTaskMap, fsrTMsg)){
                return false;
            }
            // END 2018/10/03 K.Fujimoto [QC#28514, ADD]

        } else if (MODE_UPDATE_CALL.equals(mode)) {
            // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
            return true;
            // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
        } else if (MODE_CANCEL_CALL.equals(mode)) {
            // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
            return true;
            // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
        }
        S21ApiTBLAccessor.insert(fsrTMsg);

        checkRtnCodeForInsert(pMsg, fsrTMsg);

        // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
        return true;
        // END 2018/10/03 K.Fujimoto [QC#28514, ADD]
    }

    private void updateFsr(NSZC003001PMsg pMsg) {

        FSRTMsg fsrTMsg = getFsrForUpdate(pMsg);

        ZYPEZDItemValueSetter.setValue(fsrTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());

        S21ApiTBLAccessor.update(fsrTMsg);

        checkRtnCodeForUpdate(pMsg, fsrTMsg);
    }

    private boolean updateFsrForCall(NSZC003001PMsg pMsg, Map<String, Object> svcTaskMap) {

        FSRTMsg fsrTMsg = getFsrForUpdate(pMsg);
        // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
        //If setFsrForCall is Error, not create FSR.
        if(!setFsrForCall(pMsg, svcTaskMap, fsrTMsg)){
            return false;
        }
        // END 2018/10/03 K.Fujimoto [QC#28514, ADD]
        

        S21ApiTBLAccessor.update(fsrTMsg);

        checkRtnCodeForUpdate(pMsg, fsrTMsg);

        // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
        return true;
        // END 2018/10/03 K.Fujimoto [QC#28514, ADD]
    }
    // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
    
    /**
     * setFsrForCall
     * It set pMsg and DB records as FSRTMsg For Service Call.
     * If there is error(s), it returns false.
     * @param pMsg NSZC003001PMsg
     * @param svcTaskMap Map<String, Object>
     * @param fsrTMsg FSRTMsg
     * @return boolean
     */
    private boolean setFsrForCall(NSZC003001PMsg pMsg, Map<String, Object> svcTaskMap, FSRTMsg fsrTMsg) {
    // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
        String mode = pMsg.xxBizProcTp.getValue();
        if (MODE_CANCEL_CALL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrStsCd, SVC_TASK_STS.CANCELLED);
            // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
            return true;
            // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
        }
        setValueCompletion(fsrTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd);

        if (ZYPCommonFunc.hasValue(pMsg.pmtTermCashDiscCd)) {
            ZYPEZDItemValueSetter.setValue(fsrTMsg.pmtTermCashDiscCd, pMsg.pmtTermCashDiscCd);
        // START 2022/04/25 K.Kitachi [QC#59913, ADD]
        } else if (ZYPCommonFunc.hasValue(fsrTMsg.pmtTermCashDiscCd)) {
            // Do nothing
        // END 2022/04/25 K.Kitachi [QC#59913, ADD]
        } else {
            String pmtTermCashDiscCd = null;

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            paramMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            paramMap.put("billToCustCd", pMsg.billToCustCd.getValue());

            Map<String, Object> acctMap = (Map<String, Object>) ssmBatchClient.queryObject("getAccount", paramMap);
            if (acctMap == null) {
                // START 2018/09/06 K.Kojima [QC#28064,MOD]
                // setErrMsg(pMsg, NSZM0079E);
                setErrMsg(pMsg, NSZM1348E);
                // END 2018/09/06 K.Kojima [QC#28064,MOD]
                // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                return false;
                // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
            }

            CUST_CR_PRFLTMsg srchCustCrPrflTmsg = new CUST_CR_PRFLTMsg();
            srchCustCrPrflTmsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(srchCustCrPrflTmsg.billToCustPk, (BigDecimal) acctMap.get("BILL_TO_CUST_PK"));

            CUST_CR_PRFLTMsg custCrPrflTmsg = (CUST_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(srchCustCrPrflTmsg);

            if (custCrPrflTmsg == null
                    || !ZYPCommonFunc.hasValue(custCrPrflTmsg.pmtTermCashDiscCd)) {

                DS_ACCT_CR_PRFLTMsg srchDsAcctCrPrflTmsg = new DS_ACCT_CR_PRFLTMsg();
                srchDsAcctCrPrflTmsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                srchDsAcctCrPrflTmsg.dsAcctNum.setValue((String) acctMap.get("SELL_TO_CUST_CD"));
                DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTmsg = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(srchDsAcctCrPrflTmsg);

                if (dsAcctCrPrflTmsg == null
                        || !ZYPCommonFunc.hasValue(dsAcctCrPrflTmsg.pmtTermCashDiscCd)) {
                    // START 2018/09/06 K.Kojima [QC#28064,MOD]
                    // setErrMsg(pMsg, NSZM0079E);
                    setErrMsg(pMsg, NSZM1349E);
                    // END 2018/09/06 K.Kojima [QC#28064,MOD]
                    // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                    return false;
                    // END   2018/10/03 K.Fujimoto [QC#28514, MOD]

                } else {

                    pmtTermCashDiscCd = dsAcctCrPrflTmsg.pmtTermCashDiscCd.getValue();
                }
            } else {

                pmtTermCashDiscCd = custCrPrflTmsg.pmtTermCashDiscCd.getValue();
            }

            ZYPEZDItemValueSetter.setValue(fsrTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        }

        GLBL_CMPYTMsg glblCmpyTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, pMsg.glblCmpyCd.getValue(), pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(fsrTMsg.origInvCcyCd, glblCmpyTMsg.stdCcyCd);
        CUST_CR_PRFLTMsg custCrPrflTMsg = getCustCrPrflTMsg(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue());

        String ccyCd = null;
        if (custCrPrflTMsg != null) {

            ZYPEZDItemValueSetter.setValue(fsrTMsg.invCcyCd, custCrPrflTMsg.ccyCd);
            ccyCd = custCrPrflTMsg.ccyCd.getValue();

        } else {

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            paramMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

            String apiBillToCustCd = null;
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.billToCustUpdFlg.getValue()) && ZYPCommonFunc.hasValue(pMsg.billToUpdCustCd)) {

                apiBillToCustCd = pMsg.billToUpdCustCd.getValue();

            } else {

                apiBillToCustCd = pMsg.billToCustCd.getValue();
            }
            paramMap.put("billToCustCd", apiBillToCustCd);

            Map<String, Object> acctMap = (Map<String, Object>) ssmBatchClient.queryObject("getAccount", paramMap);
            if (acctMap == null) {

                // START 2018/10/03 K.Fujimoto [QC#28514, MOD]
                setErrMsg(pMsg, NSZM1348E);
                return false;
                // END   2018/10/03 K.Fujimoto [QC#28514, MOD]
            }

            DS_ACCT_CR_PRFLTMsg srchDsAcctCrPrflTMsg = new DS_ACCT_CR_PRFLTMsg();
            srchDsAcctCrPrflTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(srchDsAcctCrPrflTMsg.dsAcctNum, (String) acctMap.get("SELL_TO_CUST_CD"));

            DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTMsg = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(srchDsAcctCrPrflTMsg);
            if (dsAcctCrPrflTMsg != null) {

                ZYPEZDItemValueSetter.setValue(fsrTMsg.invCcyCd, dsAcctCrPrflTMsg.ccyCd);
                ccyCd = dsAcctCrPrflTMsg.ccyCd.getValue();
            }
        }

        BigDecimal exchRate = getExchRate(pMsg.glblCmpyCd.getValue(), ccyCd, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(fsrTMsg.ccyExchRate, exchRate);

        setValueCompletion(fsrTMsg.svcCallSrcTpCd, pMsg.svcCallSrcTpCd);
        setValueCompletion(fsrTMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        setValueCompletion(fsrTMsg.svcCallRqstOwnrTocCd, pMsg.svcCallRqstOwnrTocCd);
        setValueCompletion(fsrTMsg.svcCallIncdtDt, pMsg.svcCallIncdtDt);
        setValueCompletion(fsrTMsg.svcCallIncdtTm, pMsg.svcCallIncdtTm);
        setValueCompletion(fsrTMsg.custCseNum, pMsg.custCseNum);
        setValueCompletion(fsrTMsg.ittNum, pMsg.ittNum);
        setValueCompletion(fsrTMsg.billToCustUpdFlg, pMsg.billToCustUpdFlg);
        if (!ZYPCommonFunc.hasValue(fsrTMsg.billToCustUpdFlg)) {
            ZYPEZDItemValueSetter.setValue(fsrTMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        }
        setValueCompletion(fsrTMsg.shipToCustUpdFlg, pMsg.shipToCustUpdFlg);
        if (!ZYPCommonFunc.hasValue(fsrTMsg.shipToCustUpdFlg)) {
            ZYPEZDItemValueSetter.setValue(fsrTMsg.shipToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        }

        String poVerFlg = ZYPConstant.FLG_OFF_N;
        int cnt = pMsg.attachedFileList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.attachedFileList.no(i).poVerFlg.getValue())) {
                poVerFlg = ZYPConstant.FLG_ON_Y;
                break;
            }
        }
        ZYPEZDItemValueSetter.setValue(fsrTMsg.poVerFlg, poVerFlg);

        setValueCompletion(fsrTMsg.billToUpdCustCd, pMsg.billToUpdCustCd);
        setValueCompletion(fsrTMsg.shipToUpdCustCd, pMsg.shipToUpdCustCd);
        setValueCompletion(fsrTMsg.billToCustAcctCd, pMsg.billToCustAcctCd);
        setValueCompletion(fsrTMsg.shipToCustAcctCd, pMsg.shipToCustAcctCd);
        setValueCompletion(fsrTMsg.fsrTpCd, pMsg.fsrTpCd);
        // START 2018/10/03 K.Fujimoto [QC#28514, ADD]
        return true;
        // END   2018/10/03 K.Fujimoto [QC#28514, ADD]
    }

    private void deleteFsr(NSZC003001PMsg pMsg) {
        FSRTMsg fsrTMsg = getFsrForUpdate(pMsg);
        S21ApiTBLAccessor.logicalRemove(fsrTMsg);
        checkRtnCodeForUpdate(pMsg, fsrTMsg);
    }

    private FSRTMsg getFsrForUpdate(NSZC003001PMsg pMsg) {
        FSRTMsg paramFsrTMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrNum, pMsg.fsrNum.getValue());

        FSRTMsg fsrTMsg = (FSRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramFsrTMsg);
        checkRtnCodeForSearch(pMsg, fsrTMsg, paramFsrTMsg);

        return fsrTMsg;
    }

    private void setFsrVisitTMsg(NSZC003001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg, String fsrNum, String fsrVisitNum, String scheduleFlg, String isPOPendCust, String isCrHld, String isPndIstl) {
        String mode = pMsg.xxBizProcTp.getValue();

// START 2016/11/16 N.Arai [QC#15860, MOD]
        // Get ASG_TECH from FSR_VISIT/SVC_MACH_MSTR/SVC_NON_PRF_TECH
        Map<String, String> assignTechMap = null;
        assignTechMap = setAssignTech(pMsg.glblCmpyCd.getValue(), pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
        String svcAsgTpCd = null;
        String svcAsgTechCd = null;
        if (assignTechMap != null) {
            svcAsgTpCd = (String) assignTechMap.get("SVC_ASG_TP_CD");
            svcAsgTechCd = (String) assignTechMap.get("SVC_ASG_TECH_CD");
        }
// END 2016/11/16 N.Arai [QC#15860, MOD]

        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitNum, fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.techAcptFlg)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromDt, pMsg.xxSvcTaskList.no(0).techSchdFromDt.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromTm, pMsg.xxSvcTaskList.no(0).techSchdFromTm.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdToDt, pMsg.xxSvcTaskList.no(0).techSchdToDt.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdToTm, pMsg.xxSvcTaskList.no(0).techSchdToTm.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdTz, pMsg.xxSvcTaskList.no(0).techSchdTz.getValue());

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitLtstFlg, ZYPConstant.FLG_ON_Y);

            if (MODE_SCHEDULE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.SCHEDULED);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitSchdDt, sysDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitSchdTm, sysTm);
            } else if (MODE_DISPATCH.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.DISPATCHED);
                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).fsrVisitDisptDt) && ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).fsrVisitDisptTm)) {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptDt, pMsg.xxSvcTaskList.no(0).fsrVisitDisptDt);
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptTm, pMsg.xxSvcTaskList.no(0).fsrVisitDisptTm);
                } else {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptDt, sysDt);
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptTm, sysTm);
                }
                if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitSchdDt)) {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitSchdDt, sysDt);
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitSchdTm, sysTm);
                }
            }

            if (MODE_SCHEDULE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.schdDisptEmlFlg, getEmailFlg(pMsg));
            } else {
                if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.schdDisptEmlFlg)) {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
                }
            }

        } else if (MODE_CANCEL.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.CONTINUOUS_OPEN);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
            fsrVisitTMsg.techAcptDt.clear();
            fsrVisitTMsg.techAcptTm.clear();
            fsrVisitTMsg.techSchdFromDt.clear();
            fsrVisitTMsg.techSchdFromTm.clear();
            fsrVisitTMsg.techSchdToDt.clear();
            fsrVisitTMsg.techSchdToTm.clear();
            fsrVisitTMsg.techSchdTz.clear();
            fsrVisitTMsg.fsrVisitSchdDt.clear();
            fsrVisitTMsg.fsrVisitSchdTm.clear();
            fsrVisitTMsg.fsrVisitDisptDt.clear();
            fsrVisitTMsg.fsrVisitDisptTm.clear();

        } else if (MODE_ACCEPT.equals(mode)) {

         // START 2017/01/05 N.Arai [QC#13901-2, MOD]
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.ASSIGNED);
         // END 2017/01/05 N.Arai [QC#13901-2, MOD]
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.techAcptDt)) {
                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptDt) && ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptTm)) {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, pMsg.xxSvcTaskList.no(0).techAcptDt);
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, pMsg.xxSvcTaskList.no(0).techAcptTm);
                } else {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, sysDt);
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, sysTm);
                }
            }

        } else if (MODE_POST_DISPT.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitNum, fsrVisitNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitLtstFlg, ZYPConstant.FLG_ON_Y);
            if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.schdDisptEmlFlg)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
            }

        } else if (MODE_CREATE_CALL.equals(mode)) {
            // Add Start 2017/09/08 QC#19242
            SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
            svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(svcTaskTMsg);
            // Add End 2017/09/08 QC#19242

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrNum, fsrNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitNum, fsrVisitNum);
            if (AD_MODE_NEED_MORE_TIME.equals(pMsg.xxModeCd_AD.getValue())) {

                fsrVisitTMsg.fsrVisitStsCd.setValue(SVC_TASK_STS.WAITING_FOR_CUSTOMER_ACTION);

            } else if (ZYPConstant.FLG_ON_Y.equals(isPOPendCust)) {

                fsrVisitTMsg.fsrVisitStsCd.setValue(SVC_TASK_STS.PENDING_PO);

            } else if (ZYPConstant.FLG_ON_Y.equals(isCrHld)) {

                fsrVisitTMsg.fsrVisitStsCd.setValue(SVC_TASK_STS.CREDIT_HOLD);

            } else if (ZYPConstant.FLG_ON_Y.equals(isPndIstl)) {

                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.PENDING_INSTALL);

            } else {
                // Mod Start 2017/09/08 QC#19242
                if (ZYPConstant.FLG_ON_Y.equals(scheduleFlg) && !isOutTrtySvcBr(svcTaskTMsg)) {
                // Mod End 2017/09/08 QC#19242
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.TBO);

                } else {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
                }
            }
            // START 2018/01/24 M.Naito [QC#23021, ADD]
//            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
            String techCd = pMsg.xxSvcTaskList.no(0).techCd.getValue();
            if (!ZYPCommonFunc.hasValue(techCd)) {
                // START 2018/05/16 M.Naito [QC#25892, MOD]
//                if (ZYPConstant.FLG_ON_Y.equals(scheduleFlg) && isOutTrtySvcBr(svcTaskTMsg)) {
                if (ZYPConstant.FLG_OFF_N.equals(scheduleFlg)) {
                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
                    ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, pMsg.svcMachMstrPk.getValue());
                    svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcMachMstrTMsg);
//                    techCd = svcMachMstrTMsg.prfTechCd.getValue();
                    techCd = svcMachMstrTMsg.reqTechCd.getValue();
                }
                // END 2018/05/16 M.Naito [QC#25892, MOD]
            }
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techCd, techCd);
            // END 2018/01/24 M.Naito [QC#23021, ADD]
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromDt, pMsg.xxSvcTaskList.no(0).techSchdFromDt);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromTm, pMsg.xxSvcTaskList.no(0).techSchdFromTm);

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
            if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).schdDisptEmlFlg)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.schdDisptEmlFlg, pMsg.xxSvcTaskList.no(0).schdDisptEmlFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitLtstFlg, ZYPConstant.FLG_ON_Y);
            // Del Start 2017/09/08 QC#19242
//            SVC_TASKTMsg srchSvcTaskTMsg = new SVC_TASKTMsg();
//            srchSvcTaskTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
//            srchSvcTaskTMsg.svcTaskNum.setValue(pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
//            SVC_TASKTMsg svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(srchSvcTaskTMsg);
            // Del End 2017/09/08 QC#19242
            String cpltByTelFixFlg = null;
            // Mod Start 2018/09/26 QC#28381
//            if (DS_SVC_CALL_TP.PHONE.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())
//                    || DS_SVC_CALL_TP.PHONE_2.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())
//            ) {
            if (DS_SVC_CALL_TP.PHONE_FIX_DISPATCHER.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())
                    || DS_SVC_CALL_TP.PHONE_FIX_TECHNICIAN.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())
                    || DS_SVC_CALL_TP.AHS_PHONE_FIX_DISPATCHER.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())
                    || DS_SVC_CALL_TP.AHS_PHONE_FIX_TECHNICIAN.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())
              ) {
            // Mod End 2018/09/26 QC#28381
                cpltByTelFixFlg = ZYPConstant.FLG_ON_Y;
            } else {
                cpltByTelFixFlg = ZYPConstant.FLG_OFF_N;
            }
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.cpltByTelFixFlg, cpltByTelFixFlg);

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcLttdNum, pMsg.xxSvcTaskList.no(0).svcLttdNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcLgtdNum, pMsg.xxSvcTaskList.no(0).svcLgtdNum);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcUnAsgRsnTxt, pMsg.xxSvcTaskList.no(0).svcUnAsgRsnTxt);
// START 2016/11/16 N.Arai [QC#15860, MOD]
//            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, pMsg.xxSvcTaskList.no(0).svcAsgTpCd);
            // START 2022/12/12 K.Kitachi [QC#60911, MOD]
//            if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techCd)) {
            if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techCd) && ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).svcAsgTpCd)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTechCd, pMsg.xxSvcTaskList.no(0).techCd);
//                if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).svcAsgTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, pMsg.xxSvcTaskList.no(0).svcAsgTpCd);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, SVC_ASG_TP.REQUIRED);
//                }
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, pMsg.xxSvcTaskList.no(0).svcAsgTpCd);
            // END 2022/12/12 K.Kitachi [QC#60911, MOD]
            // START 2022/10/26 K.Kitachi [QC#60726, MOD]
//            } else {
            } else if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.svcAsgTechCd)) {
            // END 2022/10/26 K.Kitachi [QC#60726, MOD]
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, svcAsgTpCd);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTechCd, svcAsgTechCd);
            }
// END 2016/11/16 N.Arai [QC#15860, MOD]
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.futSvcDt, pMsg.xxSvcTaskList.no(0).futSvcDt);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.futSvcTm, pMsg.xxSvcTaskList.no(0).futSvcTm);
            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcTaskNum, pMsg.xxSvcTaskList.no(0).svcTaskNum);

        } else if (MODE_UPDATE_CALL.equals(mode)) {

            setValueCompletion(fsrVisitTMsg.fsrVisitStsCd, pMsg.svcTaskStsCd);
            setValueCompletion(fsrVisitTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd);

            if (SVC_TASK_STS.SCHEDULED.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.techSchdFromDt) && !ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techSchdFromDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromDt, sysDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromTm, sysTm);
            } else if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techSchdFromDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromDt, pMsg.xxSvcTaskList.no(0).techSchdFromDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdFromTm, pMsg.xxSvcTaskList.no(0).techSchdFromTm);
            }
            if (SVC_TASK_STS.SCHEDULED.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.techSchdToDt) && !ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techSchdToDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdToDt, sysDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdToTm, sysTm);
            } else if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techSchdToDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdToDt, pMsg.xxSvcTaskList.no(0).techSchdToDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techSchdToTm, pMsg.xxSvcTaskList.no(0).techSchdToTm);
            }
            // START 2019/01/21 W.Honda [QC#28650, ADD]
            // START 2022/09/28 K.Kitachi [QC#60633, MOD]
//            if (!isAhsCall(pMsg.glblCmpyCd.getValue(), pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue())) {
//                if (SVC_TASK_STS.ASSIGNED.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.techAcptDt) && !ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptDt)) {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, sysDt);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, sysTm);
//                } else if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptDt)) {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, pMsg.xxSvcTaskList.no(0).techAcptDt);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, pMsg.xxSvcTaskList.no(0).techAcptTm);
//                }
//            } else {
//                if (SVC_TASK_STS.ASSIGNED.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcTaskList.no(0).techAcptFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
//                    fsrVisitTMsg.techAcptDt.clear();
//                    fsrVisitTMsg.techAcptTm.clear();
//                } else if (SVC_TASK_STS.ASSIGNED.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.techAcptDt) && !ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptDt) && ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcTaskList.no(0).techAcptFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, sysDt);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, sysTm);
//                } else if (SVC_TASK_STS.ASSIGNED.equals(pMsg.svcTaskStsCd.getValue()) && ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptDt) && ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcTaskList.no(0).techAcptFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, pMsg.xxSvcTaskList.no(0).techAcptDt);
//                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, pMsg.xxSvcTaskList.no(0).techAcptTm);
//                }
//            }
            if (SVC_TASK_STS.SCHEDULED.equals(pMsg.svcTaskStsCd.getValue()) || SVC_TASK_STS.ASSIGNED.equals(pMsg.svcTaskStsCd.getValue())) {
                if (!isAhsCall(pMsg.glblCmpyCd.getValue(), pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue())) {
                    // Regular Hour Call
                    if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.techAcptDt)) {
                        ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, sysDt);
                        ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, sysTm);
                    }
                } else {
                    // After Hour Call
                    if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcTaskList.no(0).techAcptFlg.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.techAcptDt)) {
                        if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techAcptDt)) {
                            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
                            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, pMsg.xxSvcTaskList.no(0).techAcptDt);
                            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, pMsg.xxSvcTaskList.no(0).techAcptTm);
                        } else {
                            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_ON_Y);
                            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptDt, sysDt);
                            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptTm, sysTm);
                        }
                    } else if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxSvcTaskList.no(0).techAcptFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
                        fsrVisitTMsg.techAcptDt.clear();
                        fsrVisitTMsg.techAcptTm.clear();
                    }
                }
            }
            // END 2022/09/28 K.Kitachi [QC#60633, MOD]
            // END 2019/01/21 W.Honda [QC#28650, ADD]
            if ((ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techSchdFromDt) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitSchdDt)) || (SVC_TASK_STS.SCHEDULED.equals(pMsg.svcTaskStsCd.getValue()))) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitSchdDt, sysDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitSchdTm, sysTm);
            }
            if (SVC_TASK_STS.IN_ROUTE.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitDisptDt) && !ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).fsrVisitDisptDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptDt, sysDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptTm, sysTm);
            } else if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).fsrVisitDisptDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptDt, pMsg.xxSvcTaskList.no(0).fsrVisitDisptDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitDisptTm, pMsg.xxSvcTaskList.no(0).fsrVisitDisptTm);
            }
            if (SVC_TASK_STS.ARRIVED.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPCommonFunc.hasValue(fsrVisitTMsg.fsrVisitArvDt)) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitArvDt, sysDt);
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitArvTm, sysTm);
            }
            setValueCompletion(fsrVisitTMsg.svcLttdNum, pMsg.xxSvcTaskList.no(0).svcLttdNum);
            setValueCompletion(fsrVisitTMsg.svcLgtdNum, pMsg.xxSvcTaskList.no(0).svcLgtdNum);
            setValueCompletion(fsrVisitTMsg.svcUnAsgRsnTxt, pMsg.xxSvcTaskList.no(0).svcUnAsgRsnTxt);
// START 2016/11/16 N.Arai [QC#15860, MOD]
//            setValueCompletion(fsrVisitTMsg.svcAsgTpCd, pMsg.xxSvcTaskList.no(0).svcAsgTpCd);
          // START 2022/12/12 K.Kitachi [QC#60911, MOD]
//          if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techCd)) {
          if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).techCd) && ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).svcAsgTpCd)) {
              ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTechCd, pMsg.xxSvcTaskList.no(0).techCd);
//              if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).svcAsgTpCd)) {
//                  ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, pMsg.xxSvcTaskList.no(0).svcAsgTpCd);
//              } else {
//                  ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, SVC_ASG_TP.REQUIRED);
//              }
              ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, pMsg.xxSvcTaskList.no(0).svcAsgTpCd);
          // END 2022/12/12 K.Kitachi [QC#60911, MOD]
          // START 2022/10/26 K.Kitachi [QC#60726, MOD]
//          } else {
          } else if (!ZYPCommonFunc.hasValue(fsrVisitTMsg.svcAsgTechCd)) {
          // END 2022/10/26 K.Kitachi [QC#60726, MOD]
              ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTpCd, svcAsgTpCd);
              ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.svcAsgTechCd, svcAsgTechCd);
          }
//END 2016/11/16 N.Arai [QC#15860, MOD]
            setValueCompletion(fsrVisitTMsg.futSvcDt, pMsg.xxSvcTaskList.no(0).futSvcDt);
            setValueCompletion(fsrVisitTMsg.futSvcTm, pMsg.xxSvcTaskList.no(0).futSvcTm);

// START 2017/01/04 N.Arai [QC#16449, MOD]
            if (SVC_TASK_STS.TBO.equals(pMsg.svcTaskStsCd.getValue()) || SVC_TASK_STS.OPEN.equals(pMsg.svcTaskStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.techAcptFlg, ZYPConstant.FLG_OFF_N);
                fsrVisitTMsg.techAcptDt.clear();
                fsrVisitTMsg.techAcptTm.clear();
                fsrVisitTMsg.techSchdFromDt.clear();
                fsrVisitTMsg.techSchdFromTm.clear();
                fsrVisitTMsg.techSchdToDt.clear();
                fsrVisitTMsg.techSchdToTm.clear();
                fsrVisitTMsg.techSchdTz.clear();
                fsrVisitTMsg.fsrVisitSchdDt.clear();
                fsrVisitTMsg.fsrVisitSchdTm.clear();
                fsrVisitTMsg.fsrVisitDisptDt.clear();
                fsrVisitTMsg.fsrVisitDisptTm.clear();
                fsrVisitTMsg.fsrVisitEtaDt.clear();
                fsrVisitTMsg.fsrVisitEtaTm.clear();
           }
// START 2018/01/19 M.Naito [QC#17469, ADD]
            if (SVC_TASK_STS.PENDING_PO.equals(pMsg.svcTaskStsCd.getValue()) && !ZYPConstant.FLG_ON_Y.equals(isPOPendCust)) {
                SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
                svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(svcTaskTMsg);
                if (ZYPConstant.FLG_ON_Y.equals(scheduleFlg) && !isOutTrtySvcBr(svcTaskTMsg)) {
                    // Mod End 2017/09/08 QC#19242
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.TBO);

                } else {
                    ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
                }
            }
// END 2018/01/19 M.Naito [QC#17469, ADD]
// END 2017/01/04 N.Arai [QC#16449, MOD]
        } else if (MODE_CANCEL_CALL.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(fsrVisitTMsg.fsrVisitStsCd, SVC_TASK_STS.CANCELLED);
        }
    }

    private FSR_VISITTMsg insertFsrVisit(NSZC003001PMsg pMsg, String fsrNum, FSR_VISITTMsg fsrVisitTMsg, String scheduleFlg, String isPOPendCust, String isCrHld, String isPndIstl) {

        setFsrVisitTMsg(pMsg, fsrVisitTMsg, fsrNum, FSR_VISIT_NUM_01, scheduleFlg, isPOPendCust, isCrHld, isPndIstl);

        S21ApiTBLAccessor.insert(fsrVisitTMsg);

        checkRtnCodeForInsert(pMsg, fsrVisitTMsg);

        return fsrVisitTMsg;
    }

    private FSR_VISITTMsg updateFsrVisit(NSZC003001PMsg pMsg, String fsrVisitNum, String scheduleFlg, String isPOPendCust, String isCrHld, String isPendIstl) {

        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitForUpdate(pMsg, fsrVisitNum);

        setFsrVisitTMsg(pMsg, fsrVisitTMsg, fsrVisitTMsg.fsrNum.getValue(), fsrVisitNum, scheduleFlg, isPOPendCust, isCrHld, isPendIstl);

        S21ApiTBLAccessor.update(fsrVisitTMsg);

        checkRtnCodeForUpdate(pMsg, fsrVisitTMsg);

        return fsrVisitTMsg;
    }

    private void deleteFsrVisit(NSZC003001PMsg pMsg, String fsrVisitNum) {
        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitForUpdate(pMsg, fsrVisitNum);

        S21ApiTBLAccessor.logicalRemove(fsrVisitTMsg);

        checkRtnCodeForUpdate(pMsg, fsrVisitTMsg);
    }

    private FSR_VISITTMsg getFsrVisitForUpdate(NSZC003001PMsg pMsg, String fsrVisitNum) {
        FSR_VISITTMsg paramFsrTMsg = new FSR_VISITTMsg();
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrNum, pMsg.fsrNum.getValue());
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrVisitNum, fsrVisitNum);

        // START 2018/01/15 U.Kim [QC#19702, MOD]
        // FSR_VISITTMsg fsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramFsrTMsg);
        FSR_VISITTMsg fsrVisitTMsg = null;
        try {
            fsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramFsrTMsg);
        } catch (EZDDBRecordLockedException e) {
            fsrVisitTMsg = getFsrVisitForUpdateWait(paramFsrTMsg);
        }
        // END 2018/01/15 U.Kim [QC#19702, MOD]
        checkRtnCodeForSearch(pMsg, fsrVisitTMsg, paramFsrTMsg);

        return fsrVisitTMsg;
    }

    // START 2018/01/15 U.Kim [QC#19702, ADD]
    private FSR_VISITTMsg getFsrVisitForUpdateWait(FSR_VISITTMsg paramFsrTMsg){
        try{
            return (FSR_VISITTMsg) EZDTBLAccessor.findByKeyForUpdateWait(paramFsrTMsg, this.waitSecUpdTaskOther);
        }catch(EZDDBRecordLockedException e){
            return null;
        }
    }
    // END 2018/01/15 U.Kim [QC#19702, ADD]

    private void insertFsrEvent(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, FSR_VISITTMsg fsrVisitTMsg, boolean updFlg, String schedulabelFlag) {
        insertFsrEvent(pMsg, svcTaskList, fsrVisitTMsg, updFlg, schedulabelFlag, null);
    }
    private void insertFsrEvent(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, FSR_VISITTMsg fsrVisitTMsg, boolean updFlg, String schedulabelFlag, String svcDisptEventCd) {
        String mode = pMsg.xxBizProcTp.getValue();
        FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();

        for (Map<String, Object> svcTaskMap : svcTaskList) {

            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcTaskNum, (String) svcTaskMap.get(SVC_TASK_NUM));
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeUsrId, pMsg.xxSvcTaskList.no(0).userId.getValue());
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeTs, sysTs);
//            if (!ZYPCommonFunc.hasValue(fsrEventTMsg.mblIntfcFlg)) {
//
//                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, ZYPConstant.FLG_ON_Y);
//            }

            if (MODE_SCHEDULE.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, fsrVisitTMsg.techCd.getValue());
                if (updFlg) {
                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_SCHEDULE);
                } else {
                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.SCHEDULE);
                }

            } else if (MODE_DISPATCH.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, fsrVisitTMsg.techCd.getValue());
                if (updFlg) {
                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_DISPATCH);
                } else {
                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.IN_ROUTE);
                }

            } else if (MODE_CANCEL.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, (String) svcTaskMap.get(FSR_NUM));
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, (String) svcTaskMap.get(FSR_VISIT_NUM));
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, (String) svcTaskMap.get(TECH_CD));

                if (SVC_TASK_STS.SCHEDULED.equals((String) svcTaskMap.get(FSR_VISIT_STS_CD))) {

                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UNSCHEDULE);

                } else if (SVC_TASK_STS.DISPATCHED.equals((String) svcTaskMap.get(FSR_VISIT_STS_CD))) {

                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UN_IN_ROUTE);
                }

            } else if (MODE_ACCEPT.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, (String) svcTaskMap.get(FSR_NUM));
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, (String) svcTaskMap.get(FSR_VISIT_NUM));
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, (String) svcTaskMap.get(TECH_CD));
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.ASSIGN);

            } else if (MODE_POST_DISPT.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, fsrVisitTMsg.techCd.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.POST_DISPATCH_FSR);

            } else if (MODE_CREATE_CALL.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());

                String eventCd = null;
                if (ZYPCommonFunc.hasValue(svcDisptEventCd)) {

                    eventCd = svcDisptEventCd;

                } else if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd_AD.getValue())) {

                    eventCd = SVC_DISPT_EVENT.OPEN;

                } else {

                    String adModeCd = pMsg.xxModeCd_AD.getValue();

                    if (AD_MODE_CALL_RESOLVE.equals(adModeCd)) {

                        eventCd = SVC_DISPT_EVENT.AD_RESOLVE_CALL;

                    } else if (AD_MODE_DISPATCH_TECH.equals(adModeCd)) {

                        eventCd = SVC_DISPT_EVENT.AD_NEED_TECHNICIAN;

                    } else if (AD_MODE_NEED_MORE_TIME.equals(adModeCd)) {

                        eventCd = SVC_DISPT_EVENT.AD_NEED_MORE_TIME;
                    }
                }
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, eventCd);
//                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcProcCd);
//                fsrEventTMsg.mblIntfcFlg.setValue(pMsg.xxSvcTaskList.no(0).mblIntfcFlg.getValue());

            } else if (MODE_UPDATE_CALL.equals(mode)) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, pMsg.xxSvcTaskList.no(0).techCd.getValue());
                if (ZYPCommonFunc.hasValue(svcDisptEventCd)) {
                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, svcDisptEventCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_VISIT);
                    
                }
//                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcProcCd);
//                fsrEventTMsg.mblIntfcFlg.setValue(pMsg.xxSvcTaskList.no(0).mblIntfcFlg.getValue());

            } else if (MODE_CANCEL_CALL.equals(mode)) {
                fsrEventTMsg.svcTaskNum.clear();
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum.getValue());
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.CANCEL);
//                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcProcCd);
//                fsrEventTMsg.mblIntfcFlg.setValue(pMsg.xxSvcTaskList.no(0).mblIntfcFlg.getValue());

            } else {
                return;
            }
            if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode)) {
                FSR_EVENTTMsg latestFsrEventTMsg = getLatestFsrEvent(pMsg, svcTaskMap, fsrVisitTMsg);
                if (latestFsrEventTMsg != null) {
                    if (!fsrEventTMsg.techCd.getValue().equals(latestFsrEventTMsg.techCd.getValue())) {
                        insertUnschedule(pMsg, latestFsrEventTMsg, fsrVisitTMsg);
                        if (MODE_SCHEDULE.equals(mode)) {
                            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.SCHEDULE);
                        } else if (MODE_DISPATCH.equals(mode)) {
                            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.IN_ROUTE);
                        }
                    }
                }
            }
            // START 2017/09/04 K.Kitachi [QC#20053, ADD]
            if (!isVisitLtst(fsrEventTMsg)) {
                continue;
            }
            // END 2017/09/04 K.Kitachi [QC#20053, ADD]

            // mod start 2016/07/14 CSA Defect#11185
//          if (!ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).mblIntfcFlg)) {
//              ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, getMblIntfcFlg(pMsg.glblCmpyCd.getValue(), fsrEventTMsg.svcDisptEventCd.getValue()));
//          }
            NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
            mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            if (ZYPConstant.FLG_OFF_N.equals(schedulabelFlag)) {
                mblIntfcInfoBean.setMblIntfcFlg(ZYPConstant.FLG_OFF_N);
            } else {
                mblIntfcInfoBean.setMblIntfcFlg(pMsg.xxSvcTaskList.no(0).mblIntfcFlg.getValue());
            }
            mblIntfcInfoBean.setSvcDisptEventCd(fsrEventTMsg.svcDisptEventCd.getValue());
            mblIntfcInfoBean.setSvcTaskStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue());
            mblIntfcInfoBean.setTechCd(fsrVisitTMsg.techCd.getValue());
            NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
            // mod end 2016/07/14 CSA Defect#11185

            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(FSR_EVENT_SQ));
            S21ApiTBLAccessor.insert(fsrEventTMsg);

            checkRtnCodeForInsert(pMsg, fsrEventTMsg);

            // add start 2016/07/14 For SADP
            if (isCreateStatusEvent(fsrVisitTMsg)) {
                FSR_EVENTTMsg stsFsrEventTMsg = new FSR_EVENTTMsg();
                EZDMsg.copy(fsrEventTMsg, null, stsFsrEventTMsg, null);
                if (SVC_TASK_STS.ASSIGNED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(stsFsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.ASSIGN);
                } else if (SVC_TASK_STS.SCHEDULED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(stsFsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.SCHEDULE);
                } else if (SVC_TASK_STS.IN_ROUTE.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(stsFsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.IN_ROUTE);
                }
                ZYPEZDItemValueSetter.setValue(stsFsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(FSR_EVENT_SQ));
                S21ApiTBLAccessor.insert(stsFsrEventTMsg);

                checkRtnCodeForInsert(pMsg, stsFsrEventTMsg);
            }
            // add end 2016/07/14 For SADP
        }
    }

    private boolean isCreateStatusEvent(FSR_VISITTMsg fsrVisitTMsg) {
        if (SVC_TASK_STS.ASSIGNED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
            return true;
        }
        if (SVC_TASK_STS.SCHEDULED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
            return true;
        }
        if (SVC_TASK_STS.IN_ROUTE.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
            return true;
        }
        return false;
    }

    private void insertFsrEventModeCancel(NSZC003001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg, String befFsrVisitStsCd, int index) {

        FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeUsrId, pMsg.xxSvcTaskList.no(index).userId);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeTs, sysTs);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcTaskNum, fsrVisitTMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.CANCEL);
        // mod start 2016/07/14 CSA Defect#11185
        NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
        mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        if (SVC_TASK_STS.OPEN.equals(befFsrVisitStsCd) || SVC_TASK_STS.NOTIFY_VENDOR.equals(befFsrVisitStsCd)) {
            mblIntfcInfoBean.setMblIntfcFlg(ZYPConstant.FLG_OFF_N);
        } else {
            mblIntfcInfoBean.setMblIntfcFlg(pMsg.xxSvcTaskList.no(index).mblIntfcFlg.getValue());
        }
        mblIntfcInfoBean.setSvcDisptEventCd(fsrEventTMsg.svcDisptEventCd.getValue());
        mblIntfcInfoBean.setSvcTaskStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue());
        mblIntfcInfoBean.setTechCd(fsrVisitTMsg.techCd.getValue());
        NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, fsrVisitTMsg.techCd);
//        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcProcCd);
//        if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(index).mblIntfcFlg)) {
//            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, pMsg.xxSvcTaskList.no(index).mblIntfcFlg);
//        } else {
//            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, getMblIntfcFlg(pMsg.glblCmpyCd.getValue(), SVC_DISPT_EVENT.CANCEL));
//        }
        // mod end 2016/07/14 CSA Defect#11185
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(FSR_EVENT_SQ));
        S21ApiTBLAccessor.insert(fsrEventTMsg);
        checkRtnCodeForInsert(pMsg, fsrEventTMsg);
    }

    private void insertSvcMemo(NSZC003001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg, NSZC003001_svcMemoListPMsg svcMemoListPMsg, String svcTaskNum, String userId) {

        SVC_MEMOTMsg svcMemoTMsg = createSvcMemoTMsg(pMsg, fsrVisitTMsg, svcMemoListPMsg, svcTaskNum, userId);
        S21ApiTBLAccessor.insert(svcMemoTMsg);
        checkRtnCodeForInsert(pMsg, svcMemoTMsg);
    }

    private void insertOrUpdateSvcMemo(NSZC003001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg) {

        String mode = pMsg.xxBizProcTp.getValue();
        int cnt = pMsg.svcMemoList.getValidCount();
        for (int idx = 0; idx < pMsg.xxSvcTaskList.getValidCount(); idx++) {
            String svcTaskNum = pMsg.xxSvcTaskList.no(idx).svcTaskNum.getValue();
            String userId = pMsg.xxSvcTaskList.no(idx).userId.getValue();

            for (int i = 0; i < cnt; i++) {
                NSZC003001_svcMemoListPMsg svcMemoListPMsg = pMsg.svcMemoList.no(i);

                // mod start 2016/07/14 For SADP
                // List<Map<String, Object>> data = getSvcMemoData(pMsg.glblCmpyCd.getValue(), fsrVisitTMsg.fsrNum.getValue(), svcMemoListPMsg);
                // if (data == null || data.size() == 0) {
                    insertSvcMemo(pMsg, fsrVisitTMsg, svcMemoListPMsg, svcTaskNum, userId);
                // } else {
                //     SVC_MEMOTMsg svcMemoTMsg = getSvcMemoForUpdate(pMsg.glblCmpyCd.getValue(), (BigDecimal) data.get(0).get("SVC_MEMO_PK"));
                //     if (MODE_CANCEL_CALL.equals(mode)) {
                //         ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.MEMO);
                //     }
                //     ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, svcMemoListPMsg.svcCmntTxt);
                //     ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, userId);
                //     ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, sysTs);

                //     S21ApiTBLAccessor.update(svcMemoTMsg);

                //     checkRtnCodeForInsert(pMsg, svcMemoTMsg);
                // }
                // mod End 2016/07/14 For SADP

            }
        }
        // add start 2016/07/14 For SADP
        if (cnt > 0 && !SVC_TASK_STS.CANCELLED.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) {
            insertFsrEventForMemo(pMsg, fsrVisitTMsg);
        }
        // add end 2016/07/14 For SADP
    }

    // add start 2016/07/14 For SADP
    private void insertFsrEventForMemo(NSZC003001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg) {
        FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeUsrId, pMsg.xxSvcTaskList.no(0).userId);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeTs, sysTs);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcTaskNum, fsrVisitTMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.techCd, fsrVisitTMsg.techCd);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UPDATE_NOTES);

        NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
        mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        mblIntfcInfoBean.setMblIntfcFlg(pMsg.xxSvcTaskList.no(0).mblIntfcFlg.getValue());
        mblIntfcInfoBean.setSvcDisptEventCd(fsrEventTMsg.svcDisptEventCd.getValue());
        mblIntfcInfoBean.setSvcTaskStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue());
        mblIntfcInfoBean.setTechCd(fsrVisitTMsg.techCd.getValue());
        NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
        ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(FSR_EVENT_SQ));

        S21ApiTBLAccessor.insert(fsrEventTMsg);
        checkRtnCodeForInsert(pMsg, fsrEventTMsg);
    }
    // add end 2016/07/14 For SADP

    private SVC_MEMOTMsg createSvcMemoTMsg(NSZC003001PMsg pMsg, FSR_VISITTMsg fsrVisitTMsg, NSZC003001_svcMemoListPMsg svcMemoListPMsg, String svcTaskNum, String userId) {
        String mode = pMsg.xxBizProcTp.getValue();
        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        if (MODE_CANCEL_CALL.equals(mode) && !ZYPCommonFunc.hasValue(svcMemoListPMsg.svcMemoTpCd)) {
            // mod start 2016/07/14 For SADP
            // ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.MEMO);
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, SVC_MEMO_TP.TASK_CANCEL_REASON);
            // mod end 2016/07/14 For SADP
        } else {
            ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoTpCd, svcMemoListPMsg.svcMemoTpCd);
        }
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcCmntTxt, svcMemoListPMsg.svcCmntTxt);
        // mod start 2016/11/25 CSA QC#16025
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcMemoRsnCd, svcMemoListPMsg.svcMemoRsnCd);
        // mod end 2016/11/25 CSA QC#16025
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.svcTaskNum, svcTaskNum);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.fsrNum, fsrVisitTMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdUsrId, userId);
        ZYPEZDItemValueSetter.setValue(svcMemoTMsg.lastUpdTs, sysTs);
        return svcMemoTMsg;
    }

    /**
     * <pre>
     * Get Service Memo data.
     * </pre>
     * @param pMsg NSZC015001PMsg
     * @return Service Memo data
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcMemoData(String glblCmpyCd, String fsrNum, NSZC003001_svcMemoListPMsg svcMemoListPMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG.DISPATCH_MEMO);
        param.put("svcMemoTypeCd", svcMemoListPMsg.svcMemoTpCd.getValue());
        param.put("fsrNum", fsrNum);
        param.put("rowNum", 1);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcMemo", param);
    }

    // mod start 2016/05/24 CSA Defect#8809
    private BigDecimal getExchRate(String glblCmpyCd, String ccyCd, String slsDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("ccyCd", ccyCd);
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, "NSZC003001");
        }
        paramMap.put("slsDt", slsDt);
        // mod end 2016/05/24 CSA Defect#8809

        return (BigDecimal) ssmBatchClient.queryObject("getExchRate", paramMap);
    }

    /**
     * <pre>
     * get Service Memo For Update
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param svcMemoPk Service Memo PK
     * @return SVC_MEMOTMsg
     */
    private SVC_MEMOTMsg getSvcMemoForUpdate(String glblCmpyCd, BigDecimal svcMemoPk) {
        SVC_MEMOTMsg tMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMemoPk, svcMemoPk);
        return (SVC_MEMOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

    private SVC_PBLM_TPTMsgArray getSvcPblmTp(String glblCmpyCd, String svcPblmTpCd) {
        SVC_PBLM_TPTMsg tMsg = new SVC_PBLM_TPTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcPblmTpCd01", svcPblmTpCd);
        return (SVC_PBLM_TPTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private POSTTMsgArray getPost(String glblCmpyCd, String postCd) {
        POSTTMsg tMsg = new POSTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("postCd01", postCd);
        return (POSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private void insertUnschedule(NSZC003001PMsg pMsg, FSR_EVENTTMsg latestFsrEventTMsg, FSR_VISITTMsg fsrVisitTMsg) {
        FSR_EVENTTMsg unschedulefsrEventTMsg = new FSR_EVENTTMsg();
        EZDMsg.copy(latestFsrEventTMsg, null, unschedulefsrEventTMsg, null);
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(FSR_EVENT_SQ));
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.fsrEventExeUsrId, pMsg.xxSvcTaskList.no(0).userId.getValue());
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.fsrEventExeTs, sysTs);
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.UNSCHEDULE);

        // mod start 2016/07/14 CSA Defect#11185
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.svcTaskNum, fsrVisitTMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.techCd, fsrVisitTMsg.techCd);
        NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
        mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        mblIntfcInfoBean.setMblIntfcFlg(pMsg.xxSvcTaskList.no(0).mblIntfcFlg.getValue());
        mblIntfcInfoBean.setSvcDisptEventCd(SVC_DISPT_EVENT.UNSCHEDULE);
        mblIntfcInfoBean.setSvcTaskStsCd(fsrVisitTMsg.fsrVisitStsCd.getValue());
        mblIntfcInfoBean.setTechCd(fsrVisitTMsg.techCd.getValue());
        NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
        ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
//      ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.mblIntfcProcCd, MBL_INTFC_PROC.NOT_PROCESSED);
//      if (ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(0).mblIntfcFlg)) {
//          ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.mblIntfcFlg, pMsg.xxSvcTaskList.no(0).mblIntfcFlg);
//      } else {
//          ZYPEZDItemValueSetter.setValue(unschedulefsrEventTMsg.mblIntfcFlg, getMblIntfcFlg(pMsg.glblCmpyCd.getValue(), SVC_DISPT_EVENT.CANCEL));
//      }
        // mod end 2016/07/14 CSA Defect#11185
        
        
        

        S21ApiTBLAccessor.insert(unschedulefsrEventTMsg);
        checkRtnCodeForInsert(pMsg, unschedulefsrEventTMsg);
    }

    private FSR_EVENTTMsg getLatestFsrEvent(NSZC003001PMsg pMsg, Map<String, Object> svcTaskMap, FSR_VISITTMsg fsrVisitTMsg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", (String) svcTaskMap.get(SVC_TASK_NUM));
        paramMap.put("fsrNum", fsrVisitTMsg.fsrNum.getValue());
        paramMap.put("fsrVisitNum", fsrVisitTMsg.fsrVisitNum.getValue());

        BigDecimal fsrEventPk = (BigDecimal) ssmBatchClient.queryObject("getLatestFsrEventPk", paramMap);

        FSR_EVENTTMsg prmFsrEventTMsg = new FSR_EVENTTMsg();
        ZYPEZDItemValueSetter.setValue(prmFsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prmFsrEventTMsg.fsrEventPk, fsrEventPk);
        return (FSR_EVENTTMsg) S21ApiTBLAccessor.findByKey(prmFsrEventTMsg);
    }

    private String getEmailFlg(NSZC003001PMsg pMsg) {
        int size = pMsg.xxSvcTaskList.getValidCount();
        for (int i = 0; i < size; i++) {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcTaskList.no(i).schdDisptEmlFlg.getValue())) {
                return ZYPConstant.FLG_ON_Y;
            }
        }
        return ZYPConstant.FLG_OFF_N;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcTaskList(NSZC003001PMsg pMsg, String fsrNum, List<String> svcTaskNumList) {
        String mode = pMsg.xxBizProcTp.getValue();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("svcTaskNumList", svcTaskNumList);
        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode) || MODE_CANCEL.equals(mode) || MODE_ACCEPT.equals(mode)) {
            paramMap.put("svcTaskStsCd", SVC_TASK_STS.COMPLETED);
        }
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcTaskList", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> searchSvcTaskList(NSZC003001PMsg pMsg, String fsrNum, List<String> svcTaskNumList) {
        String mode = pMsg.xxBizProcTp.getValue();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", fsrNum);
        paramMap.put("svcTaskNumList", svcTaskNumList);
        if (MODE_SCHEDULE.equals(mode) || MODE_DISPATCH.equals(mode) || MODE_CANCEL.equals(mode) || MODE_ACCEPT.equals(mode)) {
            paramMap.put("svcTaskStsCd", SVC_TASK_STS.COMPLETED);
        }
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("searchSvcTaskList", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getMachMstrAddr(NSZC003001PMsg pMsg, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        return (Map<String, String>) ssmBatchClient.queryObject("getMachMstrAddr", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getShpgPlnAddr(NSZC003001PMsg pMsg, String soNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("soNum", soNum);
        return (Map<String, String>) ssmBatchClient.queryObject("getShpgPlnAddr", paramMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getShipToAddr(NSZC003001PMsg pMsg, String shipToCustCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("shipToCustCd", shipToCustCd);
        return (Map<String, String>) ssmBatchClient.queryObject("getShipToAddr", paramMap);
    }

    private int getSvcTaskCnt(NSZC003001PMsg pMsg) {
        return (Integer) ssmBatchClient.queryObject("getSvcTaskCnt", getFsrParam(pMsg));
    }

    private int getFsrVisitCnt(NSZC003001PMsg pMsg) {
        return (Integer) ssmBatchClient.queryObject("getFsrVisitCnt", getFsrParam(pMsg));
    }

    private boolean checkTechMstr(NSZC003001PMsg pMsg) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("techCd", pMsg.xxSvcTaskList.no(0).techCd.getValue());
        // START 2016/12/19 K.Kojima [QC#15653,ADD]
        String slsDt = pMsg.slsDt.getValue();
        if (!ZYPCommonFunc.hasValue(slsDt)) {
            slsDt = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
        }
        paramMap.put("effDt", slsDt);
        // END 2016/12/19 K.Kojima [QC#15653,ADD]
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]

        techMstrTMsg = (TECH_MSTRTMsg) ssmBatchClient.queryObject("checkTechMstr", paramMap);
        if (techMstrTMsg == null) {
            return false;
        }
        return true;
    }

    private Map<String, String> getFsrParam(NSZC003001PMsg pMsg) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        return paramMap;
    }

    private CUST_CR_PRFLTMsg getCustCrPrflTMsg(String glblCmpyCd, String billToCustCd) {

        CUST_CR_PRFLTMsg custCrPrflTMsg = new CUST_CR_PRFLTMsg();
        custCrPrflTMsg.setSQLID("001");
        custCrPrflTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        custCrPrflTMsg.setConditionValue("billToCustCd01", billToCustCd);
        CUST_CR_PRFLTMsgArray tMsgArray = (CUST_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByCondition(custCrPrflTMsg);

        if (tMsgArray.getValidCount() <= 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private BILL_TO_CUSTTMsg getBillToCustTMsg(String glblCmpyCd, String billToCustCd) {

        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("004");
        billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        billToCustTMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(billToCustTMsg);

        if (tMsgArray.getValidCount() <= 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private SHIP_TO_CUSTTMsg getShipToCustTMsg(String glblCmpyCd, String shipToCustCd) {

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(shipToCustTMsg);

        if (tMsgArray.getValidCount() <= 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private SHIP_TO_CUSTTMsg getShipToCustTMsg(NSZC003001PMsg pMsg, String shipToCustCd) {

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(shipToCustTMsg);

        if (tMsgArray.getValidCount() <= 0) {
            setErrMsg(pMsg, NSZM0084E);
            throw new S21CommonException(NSZM0084E);
        }
        return tMsgArray.no(0);
    }

    /**
     * checkRtnCodeForSearch
     * @param ezdTMsg EZDTMsg
     * @param tblTMsg EZDTMsg
     * @return boolean
     */
    private void checkRtnCodeForSearch(NSZC003001PMsg pMsg, EZDTMsg ezdTMsg, EZDTMsg tblTMsg) {
        // check error code
        if (ezdTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(ezdTMsg.getReturnCode())) {
            String errCd = null;

            if (tblTMsg instanceof SVC_TASKTMsg) {
                errCd = NSZM0079E;
            } else if (tblTMsg instanceof FSRTMsg) {
                errCd = NSZM0181E;
            } else if (tblTMsg instanceof FSR_VISITTMsg) {
                errCd = NSZM0182E;
            } else {
                errCd = "";
            }
            setErrMsg(pMsg, errCd);
            throw new S21CommonException(errCd);
        }
    }

    /**
     * checkRtnCodeForInsert
     * @param ezdTMsg EZDTMsg
     * @return boolean
     */
    private void checkRtnCodeForInsert(NSZC003001PMsg pMsg, EZDTMsg ezdTMsg) {
        // check error code
        if (ezdTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(ezdTMsg.getReturnCode())) {
            String errCd = null;

            if (ezdTMsg instanceof FSRTMsg) {
                errCd = NSZM0171E;
            } else if (ezdTMsg instanceof FSR_VISITTMsg) {
                errCd = NSZM0172E;
            } else if (ezdTMsg instanceof FSR_EVENTTMsg) {
                errCd = NSZM0173E;
            } else {
                errCd = "";
            }
            setErrMsg(pMsg, errCd);
            throw new S21CommonException(errCd);
        }
    }

    /**
     * checkRtnCodeForUpdate
     * @param ezdTMsg EZDTMsg
     * @return boolean
     */
    private void checkRtnCodeForUpdate(NSZC003001PMsg pMsg, EZDTMsg ezdTMsg) {
        // check error code
        if (ezdTMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(ezdTMsg.getReturnCode())) {
            String errCd = null;

            if (ezdTMsg instanceof SVC_TASKTMsg) {
                errCd = NSZM0167E;
            } else if (ezdTMsg instanceof FSRTMsg) {
                errCd = NSZM0168E;
            } else if (ezdTMsg instanceof FSR_VISITTMsg) {
                errCd = NSZM0169E;
            }
            setErrMsg(pMsg, errCd);
            throw new S21CommonException(errCd);
        }
    }

    private void sendScheduleMail(NSZC003001PMsg pMsg, String fsrNum, List<Map<String, Object>> svcTaskList) {
        String mode = pMsg.xxBizProcTp.getValue();

        Map<String, Object> taskMap = svcTaskList.get(0);

        String templateId = null;
        String emlAddr = null;
        if (MODE_SCHEDULE.equals(mode)) {
            templateId = MAIL_TEMPLATE_ID_SCHEDULE;
            if (techMstrTMsg != null && ZYPCommonFunc.hasValue(techMstrTMsg.emlAddr)) {
                emlAddr = techMstrTMsg.emlAddr.getValue();
            }
        } else if (MODE_CANCEL.equals(mode)) {
            templateId = MAIL_TEMPLATE_ID_CANCEL;
            if (ZYPCommonFunc.hasValue((String) taskMap.get(EML_ADDR))) {
                emlAddr = (String) taskMap.get(EML_ADDR);
            }
        } else {
            return;
        }

        if (!ZYPCommonFunc.hasValue(emlAddr)) {
            return;
        }

        // Get Technician Mail-Address
        S21MailAddress toAddr = new S21MailAddress(pMsg.glblCmpyCd.getValue(), emlAddr);

        // Get From Mail Address
        S21MailGroup groupFrom = new S21MailGroup(pMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            setErrMsg(pMsg, NSZM0184E);
            throw new S21CommonException(NSZM0184E);
        }
        S21MailAddress fromAddr = addrFromList.get(0);

        // Set Mail Template
        S21MailTemplate template = new S21MailTemplate(pMsg.glblCmpyCd.getValue(), templateId);
        if (template == null || !ZYPCommonFunc.hasValue(template.getSubject())) {
            setErrMsg(pMsg, NSZM0185E);
            throw new S21CommonException(NSZM0185E);
        }

        // Get Address from MachMstr/ShpgPln/SvcTask
        Map<String, String> addrMap = null;
        if (ZYPCommonFunc.hasValue((BigDecimal) taskMap.get(SVC_MACH_MSTR_PK))) {
            addrMap = getMachMstrAddr(pMsg, (BigDecimal) taskMap.get(SVC_MACH_MSTR_PK));
        }
        if (addrMap == null && ZYPCommonFunc.hasValue((String) taskMap.get(SO_NUM))) {
            addrMap = getShpgPlnAddr(pMsg, (String) taskMap.get(SO_NUM));
        }
        if (addrMap == null) {
            addrMap = getShipToAddr(pMsg, (String) taskMap.get(SHIP_TO_CUST_CD));
        }

        String locNum = addrMap.get(LOC_NUM);
        String firstAddr = addrMap.get(FIRST_LINE_ADDR);
        String scdAddr = addrMap.get(SCD_LINE_ADDR);
        String thirdAddr = addrMap.get(THIRD_LINE_ADDR);
        String frthAddr = addrMap.get(FRTH_LINE_ADDR);
        String ctyAddr = addrMap.get(CTY_ADDR);
        String cntyNm = addrMap.get(CNTY_NM);
        String stCd = addrMap.get(ST_CD);
        String postCd = addrMap.get(POST_CD);

        // Get Schedule Date/Time
        String techSchdFromDt = pMsg.xxSvcTaskList.no(0).techSchdFromDt.getValue();
        String techSchdFromTm = pMsg.xxSvcTaskList.no(0).techSchdFromTm.getValue();
        String techSchdToDt = pMsg.xxSvcTaskList.no(0).techSchdToDt.getValue();
        String techSchdToTm = pMsg.xxSvcTaskList.no(0).techSchdToTm.getValue();

        if (MODE_CANCEL.equals(mode)) {
            techSchdFromDt = (String) taskMap.get(TECH_SCHD_FROM_DT);
            techSchdFromTm = (String) taskMap.get(TECH_SCHD_FROM_TM);
            techSchdToDt = (String) taskMap.get(TECH_SCHD_TO_DT);
            techSchdToTm = (String) taskMap.get(TECH_SCHD_TO_TM);
        }

        String svcTaskRcvDtTm = null;
        String techSchdFromDtTm = null;
        String techSchdToDtTm = null;
        String techSchdFromDt2 = null;
        String techSchdFromTm2 = null;
        String techSchdToDt2 = null;
        String techSchdToTm2 = null;

        // convert Date format
        try {
            techSchdFromDt2 = techSchdFromDt.replaceAll(FORMAT_DT_FROM, FORMAT_DT_TO);
            techSchdToDt2 = techSchdToDt.replaceAll(FORMAT_DT_FROM, FORMAT_DT_TO);

            techSchdFromTm2 = techSchdFromTm.replaceAll(FORMAT_TM_FROM, FORMAT_TM_TO);
            techSchdToTm2 = techSchdToTm.replaceAll(FORMAT_TM_FROM, FORMAT_TM_TO);

            techSchdFromDtTm = techSchdFromDt2 + STR_SP + techSchdFromTm2;
            if (techSchdFromDt2.equals(techSchdToDt2)) {
                techSchdToDtTm = techSchdToTm2;
            } else {
                techSchdToDtTm = techSchdToDt2 + STR_SP + techSchdToTm2;
            }
        } catch (Exception e) {
            techSchdFromDtTm = techSchdFromDt + techSchdFromTm;
            techSchdToDtTm = techSchdToDt + techSchdToTm;
        }

        String svcTaskRcvDt = null;
        String svcTaskRcvTm = null;
        String svcTaskRcvDt2 = null;
        String svcTaskRcvTm2 = null;
        String taskNum = "";
        String callTpNm = "";
        String custTelNum = null;
        String custTelExtnNum = null;

        Set<String> callTpNmSet = new HashSet<String>();

        // Get Rcv Date/Time and TelNum
        for (int i = 0; i < svcTaskList.size(); i++) {
            Map<String, Object> mapLine = svcTaskList.get(i);
            String tmpTaskNum = (String) mapLine.get(SVC_TASK_NUM);
            String tmpCallTpNm = (String) mapLine.get(DS_SVC_CALL_TP_NM);

            if (ZYPCommonFunc.hasValue(tmpCallTpNm)) {
                if (i > 0) {
                    taskNum += STR_COMMA;
                }
                taskNum += tmpTaskNum;

                if (!callTpNmSet.contains(tmpCallTpNm)) {
                    if (i > 0) {
                        callTpNm += STR_COMMA;
                    }
                    callTpNm += tmpCallTpNm;
                    callTpNmSet.add(tmpCallTpNm);
                }
            }

            svcTaskRcvDt = (String) mapLine.get(SVC_TASK_RCV_DT);
            svcTaskRcvTm = (String) mapLine.get(SVC_TASK_RCV_TM);

            try {
                svcTaskRcvDt2 = svcTaskRcvDt.replaceAll(FORMAT_DT_FROM, FORMAT_DT_TO);
                svcTaskRcvTm2 = svcTaskRcvTm.replaceAll(FORMAT_TM_FROM, FORMAT_TM_TO);
                // get the oldest RcvDt.
                if (svcTaskRcvDtTm == null || svcTaskRcvDtTm.compareTo(svcTaskRcvDt2 + STR_SP + svcTaskRcvTm2) > 0) {
                    svcTaskRcvDtTm = svcTaskRcvDt2 + STR_SP + svcTaskRcvTm2;
                }
            } catch (Exception e) {
                svcTaskRcvDtTm = svcTaskRcvDt + svcTaskRcvTm;
            }

            if (custTelNum == null && custTelExtnNum == null) {
                custTelNum = (String) mapLine.get(CUST_TEL_NUM);
                custTelExtnNum = (String) mapLine.get(CUST_TEL_EXTN_NUM);
            }
        }

        // Response Time AOT
        String svcRspTmMnAot = null;
        BigDecimal rspTm = (BigDecimal) taskMap.get(SVC_RSP_TM_MN_AOT);
        if (ZYPCommonFunc.hasValue(rspTm)) {
            svcRspTmMnAot = String.format(FORMAT_RSP_TM, rspTm.intValue() / HOUR_MIN, rspTm.intValue() % HOUR_MIN);
        }

        // make Address strings
        String shipToAddr = locNum;
        if (firstAddr != null) {
            shipToAddr += CRLF + STR_SP + firstAddr;
        }
        if (scdAddr != null) {
            shipToAddr += CRLF + STR_SP + scdAddr;
        }
        if (thirdAddr != null) {
            shipToAddr += CRLF + STR_SP + thirdAddr;
        }
        if (frthAddr != null) {
            shipToAddr += CRLF + STR_SP + frthAddr;
        }
        shipToAddr += CRLF + STR_SP + ctyAddr + STR_COMMA;
        if (ZYPCommonFunc.hasValue(cntyNm)) {
            shipToAddr += cntyNm + STR_COMMA;
        }
        shipToAddr += stCd;
        shipToAddr += CRLF + STR_SP + postCd;
        if (custTelNum != null) {
            shipToAddr += CRLF + STR_SP + custTelNum;
            if (custTelExtnNum != null) {
                shipToAddr += STR_START + custTelExtnNum + STR_END;
            }
        }

        template.setTemplateParameter(ML_RCV_DT, svcTaskRcvDtTm);
        template.setTemplateParameter(ML_FROM_DT, techSchdFromDtTm);
        template.setTemplateParameter(ML_TO_DT, techSchdToDtTm);
        template.setTemplateParameter(ML_SHIP_TO_ADDR, shipToAddr);
        template.setTemplateParameter(ML_MACH_DOWN_FLG, (String) taskMap.get(MACH_DOWN_FLG));
        template.setTemplateParameter(ML_CALL_TP_NM, callTpNm);
        template.setTemplateParameter(ML_SVC_RSP_GRP_CD, svcRspTmMnAot);
        template.setTemplateParameter(ML_TASK_NUM, taskNum);
        template.setTemplateParameter(ML_FSR_NUM, fsrNum);

        // Set e-Mail
        S21Mail mail = new S21Mail(pMsg.glblCmpyCd.getValue());
        mail.setFromAddress(fromAddr);
        mail.setToAddress(toAddr);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    private void setErrMsg(NSZC003001PMsg pMsg, String msgId, int idx) {
        if (!ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(idx).xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskList.no(idx).xxMsgId, msgId);
        }
        setErrMsg(pMsg, msgId);
    }

    private void setErrMsg(NSZC003001PMsg pMsg, String msgId, String svcTaskNum) {
        for (int i = 0; i < pMsg.xxSvcTaskList.getValidCount(); i++) {
            if (pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue().equals(svcTaskNum)) {
                if (!ZYPCommonFunc.hasValue(pMsg.xxSvcTaskList.no(i).xxMsgId)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxSvcTaskList.no(i).xxMsgId, msgId);
                    break;
                }
            }
        }
        setErrMsg(pMsg, msgId);
    }

    private void setErrMsg(NSZC003001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        msgMap.flush();
    }

    // START 2018/05/16 M.Naito [QC#25892, MOD]
    private String checkDefTechSts(NSZC003001PMsg pMsg, NSZC003001_xxSvcTaskListPMsg taskPMsg) {

        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), taskPMsg.svcTaskNum.getValue());
        if (isClickSendExclCall(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return ZYPConstant.FLG_OFF_N;
        }
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        if (ZYPCommonFunc.hasValue(taskPMsg.techCd)) {

            S21_PSNTMsg srchS21PsnTMsg = new S21_PSNTMsg();
            srchS21PsnTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            srchS21PsnTMsg.psnCd.setValue(taskPMsg.techCd.getValue());

            S21_PSNTMsg s21PsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(srchS21PsnTMsg);
            if (s21PsnTMsg == null) {

                setErrMsg(pMsg, NSZM0602E);
                throw new S21CommonException(NSZM0602E);

            } else if (ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {

                setErrMsg(pMsg, NSZM0603E);
                throw new S21CommonException(NSZM0603E);
            }

            if (!PSN_TP.EMPLOYEE.equals(s21PsnTMsg.psnTpCd.getValue())) {

                return ZYPConstant.FLG_OFF_N;
            }

        } else {

            // START 2019/02/15 K.Kitachi [QC#30165, DEL]
//            SVC_TASKTMsg svcTaskTMsg = getSvcTaskFindByKey(pMsg.glblCmpyCd.getValue(), taskPMsg.svcTaskNum.getValue());
            // END 2019/02/15 K.Kitachi [QC#30165, DEL]

            SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcTaskTMsg.svcMachMstrPk.getValue());
            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcMachMstrTMsg);

            if (ZYPCommonFunc.hasValue(svcMachMstrTMsg.reqTechCd)) {

                S21_PSNTMsg reqTechS21PsnTMsg = new S21_PSNTMsg();
                reqTechS21PsnTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                reqTechS21PsnTMsg.psnCd.setValue(svcMachMstrTMsg.reqTechCd.getValue());
                reqTechS21PsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(reqTechS21PsnTMsg);

                if (reqTechS21PsnTMsg == null) {

                    setErrMsg(pMsg, NSZM0602E);
                    throw new S21CommonException(NSZM0602E);

                } else if (!PSN_TP.EMPLOYEE.equals(reqTechS21PsnTMsg.psnTpCd.getValue())) {

                    return ZYPConstant.FLG_OFF_N;
                }
            }
        }

        return ZYPConstant.FLG_ON_Y;

    }
    // END 2018/05/16 M.Naito [QC#25892, MOD]

//    private String getMblIntfcProcCd(NSZC003001PMsg pMsg, NSZC003001_xxSvcTaskListPMsg taskPMsg, FSR_VISITTMsg fsrVisitTMsg, String schdFlg) {
//
//        if (ZYPConstant.FLG_OFF_N.equals(schdFlg)) {
//
//            return MBL_INTFC_PROC.NO_NEED;
//
//        }
//
//        SVC_TASK_STSTMsg srchSvcTaskStsTMsg = new SVC_TASK_STSTMsg();
//        srchSvcTaskStsTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
//        srchSvcTaskStsTMsg.svcTaskStsCd.setValue(fsrVisitTMsg.fsrVisitStsCd.getValue());
//
//        SVC_TASK_STSTMsg svcTaskStsTMsg = (SVC_TASK_STSTMsg) S21ApiTBLAccessor.findByKey(srchSvcTaskStsTMsg);
//
//        if (svcTaskStsTMsg == null) {
//
//            setErrMsg(pMsg, NSZM0728E);
//            throw new S21CommonException(NSZM0728E);
//        }
//
//        if (ZYPConstant.FLG_OFF_N.equals(svcTaskStsTMsg.xtrnlSendReqFlg.getValue())) {
//
//            return MBL_INTFC_PROC.NO_NEED;
//
//        }
//
//        if (ZYPConstant.FLG_OFF_N.equals(taskPMsg.mblIntfcFlg.getValue())) {
//
//            return MBL_INTFC_PROC.NO_NEED;
//
//        }
//
//        return MBL_INTFC_PROC.NOT_PROCESSED;
//
//    }

    private String checkCustIssPO(NSZC003001PMsg pMsg) {

        DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg = getCustTrxRule(pMsg);
        if (dsCustTrxRuleTMsg == null) {

            return ZYPConstant.FLG_OFF_N;
        }

        if (ZYPConstant.FLG_OFF_N.equals(dsCustTrxRuleTMsg.dsPoReqFlg.getValue())) {

            return ZYPConstant.FLG_OFF_N;

        } else {

            /*
             * PO Release Check
             */
            String poVerFlg = ZYPConstant.FLG_OFF_N;
            int cnt = pMsg.attachedFileList.getValidCount();
            for (int i = 0; i < cnt; i++) {
                if (ZYPConstant.FLG_ON_Y.equals(pMsg.attachedFileList.no(i).poVerFlg.getValue())) {
                    poVerFlg = ZYPConstant.FLG_ON_Y;
                    break;
                }
            }
//TODO Taizo Change Check Logic
            if (ZYPConstant.FLG_ON_Y.equals(poVerFlg)) {

                return ZYPConstant.FLG_OFF_N;

            } else {

                return ZYPConstant.FLG_ON_Y;
            }
        }
    }

    private DS_CUST_TRX_RULETMsg getCustTrxRule(NSZC003001PMsg pMsg) {

        NMZC610001PMsg nmzc610001PMsg = new NMZC610001PMsg();
        nmzc610001PMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        nmzc610001PMsg.xxModeCd.setValue(NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        nmzc610001PMsg.dsTrxRuleTpCd.setValue(DS_TRX_RULE_TP.SERVICE);

        String apiBillToCustCd = null;
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.billToCustUpdFlg.getValue()) && ZYPCommonFunc.hasValue(pMsg.billToUpdCustCd)) {

            apiBillToCustCd = pMsg.billToUpdCustCd.getValue();

        } else {

            apiBillToCustCd = pMsg.billToCustCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(nmzc610001PMsg.billToCustCd, apiBillToCustCd);

        new NMZC610001().execute(nmzc610001PMsg, onBatType);

        List<String> errList = S21ApiUtil.getXxMsgIdList(nmzc610001PMsg);
        if (errList.size() > 0) {

            for (String msgId : errList) {
                msgMap.addXxMsgId(msgId);
                msgMap.flush();
            }

            return null;
        }

        DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg = null;

        for (int i = 0; i < nmzc610001PMsg.TransactionRuleList.getValidCount(); i++) {
            dsCustTrxRuleTMsg = new DS_CUST_TRX_RULETMsg();
            if (nmzc610001PMsg.TransactionRuleList.no(i) != null) {

                EZDMsg.copy(nmzc610001PMsg.TransactionRuleList.no(i), null, dsCustTrxRuleTMsg, null);
                break;
            }
        }

        return dsCustTrxRuleTMsg;
    }

    private String checkCustCrPrfl(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, String fsrNum) {

        boolean crChkReq = false;
        // START 2019/05/07 K.Kitachi [QC#31303, ADD]
        List<String> crChkExclFsrVisitStsList = new ArrayList<String>();
        crChkExclFsrVisitStsList.add(SVC_TASK_STS.PENDING_PARTS);
        crChkExclFsrVisitStsList.add(SVC_TASK_STS.CREDIT_HOLD);
        crChkExclFsrVisitStsList.add(SVC_TASK_STS.NOTIFY_VENDOR);
        crChkExclFsrVisitStsList.add(SVC_TASK_STS.VENDOR_ACKNOWLEDGED);
        // END 2019/05/07 K.Kitachi [QC#31303, ADD]

        for (Map<String, Object> svcTaskMap : svcTaskList) {

            SVC_TASKTMsg svcTaskTMsg = getSvcTaskTMsg(pMsg, svcTaskMap, null);
            FSR_VISITTMsg fsrVisitTMsg = getFsrVisitBySvcTaskNum(svcTaskTMsg.glblCmpyCd.getValue(), svcTaskTMsg.svcTaskNum.getValue());
            // START 2018/07/11 K.Kitachi [QC#26045, MOD]
//            if (!SVC_TASK_STS.OPEN.equals(svcTaskTMsg.svcTaskStsCd.getValue()) || (fsrVisitTMsg != null && SVC_TASK_STS.PENDING_PARTS.equals(fsrVisitTMsg.fsrVisitStsCd.getValue()))) {
            // START 2019/05/07 K.Kitachi [QC#31303, MOD]
//            if (!SVC_TASK_STS.OPEN.equals(svcTaskTMsg.svcTaskStsCd.getValue()) || (fsrVisitTMsg != null && SVC_TASK_STS.PENDING_PARTS.equals(fsrVisitTMsg.fsrVisitStsCd.getValue())) || (fsrVisitTMsg != null && SVC_TASK_STS.CREDIT_HOLD.equals(fsrVisitTMsg.fsrVisitStsCd.getValue()))) {
            if (!SVC_TASK_STS.OPEN.equals(svcTaskTMsg.svcTaskStsCd.getValue()) || (fsrVisitTMsg != null && crChkExclFsrVisitStsList.contains(fsrVisitTMsg.fsrVisitStsCd.getValue()))) {
            // END 2019/05/07 K.Kitachi [QC#31303, MOD]
            // END 2018/07/11 K.Kitachi [QC#26045, MOD]
                continue;
            }

            // START 2018/01/10 K.Kojima [QC#22975,ADD]
            crChkReq = true;
            break;
            // END 2018/01/10 K.Kojima [QC#22975,ADD]

            // START 2018/01/10 K.Kojima [QC#22975,DEL]
            // DS_SVC_CALL_TPTMsg srchDsSvcCallTpTMsg = new DS_SVC_CALL_TPTMsg();
            // srchDsSvcCallTpTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            // srchDsSvcCallTpTMsg.dsSvcCallTpCd.setValue(svcTaskTMsg.dsSvcCallTpCd.getValue());
            // 
            // DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(srchDsSvcCallTpTMsg);
            // 
            // if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.crChkFlg.getValue())) {
            // 
            //     crChkReq = true;
            //     break;
            // }
            // 
            // // mod start 2016/05/25 CSA Defect#8605
            // SVC_BILL_TPTMsg srchSvcBilTpTMsg = new SVC_BILL_TPTMsg();
            // srchSvcBilTpTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            // srchSvcBilTpTMsg.svcBillTpCd.setValue(svcTaskTMsg.svcBillTpCd.getValue());
            // 
            // SVC_BILL_TPTMsg svcBilTpTMsg = (SVC_BILL_TPTMsg) S21ApiTBLAccessor.findByKey(srchSvcBilTpTMsg);
            // 
            // if (ZYPConstant.FLG_ON_Y.equals(svcBilTpTMsg.crChkFlg.getValue())) {
            //     crChkReq = true;
            //     break;
            // }
            // // mod end 2016/05/25 CSA Defect#8605
            // END 2018/01/10 K.Kojima [QC#22975,DEL]
        }

        if (crChkReq) {

            NSZC037001PMsg nszc0370PMsg = new NSZC037001PMsg();

            ZYPEZDItemValueSetter.setValue(nszc0370PMsg.glblCmpyCd, pMsg.glblCmpyCd);

            String slsDtPrm = pMsg.slsDt.getValue();
            if (!ZYPCommonFunc.hasValue(slsDtPrm)) {

                slsDtPrm = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue(), "NSZC003001");
            }

            ZYPEZDItemValueSetter.setValue(nszc0370PMsg.slsDt, slsDtPrm);
            ZYPEZDItemValueSetter.setValue(nszc0370PMsg.usrId, "NSZC003001");
            ZYPEZDItemValueSetter.setValue(nszc0370PMsg.fsrNum, fsrNum);

            int cnt = 0;
            for (Map<String, Object> svcTaskMap : svcTaskList) {
                ZYPEZDItemValueSetter.setValue(nszc0370PMsg.A.no(cnt).svcTaskNum, (String) svcTaskMap.get(SVC_TASK_NUM));
                cnt++;
            }
            nszc0370PMsg.A.setValidCount(cnt);

            new NSZC037001().execute(nszc0370PMsg, onBatType);

            List<String> errList = S21ApiUtil.getXxMsgIdList(nszc0370PMsg);
            if (errList.size() > 0) {

                // START 2018/08/30 K.Kitachi [QC#22665, MOD]
//                for (String msgId : errList) {
//                    msgMap.addXxMsgId(msgId);
//                    msgMap.flush();
//                }
//
//                return null;
                boolean isErr = false;
                for (String msgId : errList) {
                    if (msgId.endsWith("I")) {
                        this.infoMsgList.add(msgId);
                        continue;
                    }
                    msgMap.addXxMsgId(msgId);
                    msgMap.flush();
                    isErr = true;
                }
                if (isErr) {
                    return null;
                }
                // END 2018/08/30 K.Kitachi [QC#22665, MOD]
            }

            for (int i = 0; i < nszc0370PMsg.A.getValidCount(); i++) {

                // START 2018/08/30 K.Kitachi [QC#22665, MOD]
//                if (ZYPCommonFunc.hasValue(nszc0370PMsg.A.no(i).svcTaskHldRsnCd)) {
//
//                    return ZYPConstant.FLG_ON_Y;
//                }
                if (ZYPCommonFunc.hasValue(nszc0370PMsg.A.no(i).svcTaskHldRsnCd)) {
                    if (isAhsCall(nszc0370PMsg.glblCmpyCd.getValue(), nszc0370PMsg.A.no(i).svcTaskNum.getValue())) {
                        // START 2022/07/20 L.Mandanas [QC#60316, MOD]
                        //if (!SVC_TASK_HLD_RSN.OVER_DUE.equals(nszc0370PMsg.A.no(i).svcTaskHldRsnCd.getValue())) {
                        if (!SVC_TASK_HLD_RSN.OVER_DUE.equals(nszc0370PMsg.A.no(i).svcTaskHldRsnCd.getValue())
                              && !SVC_TASK_HLD_RSN.CREDIT_LIMIT.equals(nszc0370PMsg.A.no(i).svcTaskHldRsnCd.getValue())) {
                        // END 2022/07/20 L.Mandanas [QC#60316, MOD]
                            return ZYPConstant.FLG_ON_Y;
                        }
                    } else {
                        return ZYPConstant.FLG_ON_Y;
                    }
                }
                // END 2018/08/30 K.Kitachi [QC#22665, MOD]
            }
            return ZYPConstant.FLG_OFF_N;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    private FSR_TPTMsg getFsrTpTMsg(String glblCmpyCd, String fsrTpCd) {

        FSR_TPTMsg tMsg = new FSR_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTpCd, fsrTpCd);
        return (FSR_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private S21_PSNTMsg getS21Psn(String glblCmpyCd, String psnCd) {

        S21_PSNTMsg tMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, psnCd);
        return (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private SVC_ASG_TPTMsg getSvcAsgTpTMsg(String glblCmpyCd, String svcAsgTpCd) {

        SVC_ASG_TPTMsg tMsg = new SVC_ASG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcAsgTpCd, svcAsgTpCd);
        return (SVC_ASG_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private SVC_TASKTMsg getSvcTaskFindByKey(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg paramTMsg = new SVC_TASKTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(paramTMsg);
    }

    private SVC_TASKTMsgArray getSvcTaskByFsrNum(String glblCmpyCd, String fsrNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        return (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getSvcTaskStsCdList(NSZC003001PMsg pMsg) {
        List<String> svcTaskNumList = new ArrayList<String>();
        for (int i = 0; i < pMsg.xxSvcTaskList.getValidCount(); i++) {
            svcTaskNumList.add(pMsg.xxSvcTaskList.no(i).svcTaskNum.getValue());
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNumList", svcTaskNumList);
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcTaskStsCdList", paramMap);
    }

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

    @SuppressWarnings("unchecked")
    private Map<String, Object> getFsrVisitBySvcTaskNumFsrNum(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFsrVisitBySvcTaskNumFsrNum", paramMap);
    }

    private BigDecimal getAcctDlyActlExchRatesByCcyCd(String glblCmpyCd, String slsDt, Map<String, Object> fsrVisitMap) {

        BigDecimal exchRate = BigDecimal.ZERO;
        String ccyCd = (String) fsrVisitMap.get("INV_CCY_CD");
        if (ccyCd != null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("ccyCd", ccyCd);
            paramMap.put("slsDt", slsDt);
            BigDecimal ccyExchRate = (BigDecimal) ssmBatchClient.queryObject("getExchRateData", paramMap);
            if (ccyExchRate == null) {
                return exchRate;
            }
        }
        return exchRate;
    }

    private String getRqstTpCdMode(NSZC003001PMsg pMsg) {

        String rqstTpCd = pMsg.xxRqstTpCd.getValue();
        boolean allCancel = true;
        boolean allClose = true;

        String mode = null;
        if (PARTIAL_CANCEL.equals(rqstTpCd)) {
            List<Map<String, Object>> svcTaskTMsgList = getSvcTaskStsCdList(pMsg);
            if (svcTaskTMsgList.size() == 0) {
                mode = MODE_ALL_CANCEL;
            } else {
                for (Map<String, Object> svcTaskTMsg : svcTaskTMsgList) {
                    String svcTaskStsCd = (String) svcTaskTMsg.get("SVC_TASK_STS_CD");
                    if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
                        //do noting
                    } else if (SVC_TASK_STS.CLOSED.equals(svcTaskStsCd) || SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
                        allCancel = false;
                    } else {
                        allCancel = false;
                        allClose = false;
                    }
                }
            }

            if (allCancel) {
                mode = MODE_ALL_CANCEL;
            } else if (allClose) {
                mode = MODE_ALL_CLOSE;
            } else {
                mode = MODE_ALL_NORMAL;
            }
        } else {
            mode = MODE_ALL_CANCEL;
        }
        return mode;
    }

    private void callServiceDispatchCompletionApi(NSZC003001PMsg pMsg) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        String fsrNum = pMsg.fsrNum.getValue();

        // Service Task Info
        SVC_TASKTMsgArray svcTaskTMsgList = getSvcTaskByFsrNum(glblCmpyCd, fsrNum);
        for (int i = 0; i < svcTaskTMsgList.getValidCount(); i++) {
            String svcTaskNum = svcTaskTMsgList.no(i).svcTaskNum.getValue();
            String svcTaskStsCd = svcTaskTMsgList.no(i).svcTaskStsCd.getValue();
            if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
                Map<String, Object> fsrVisitMap = getFsrVisitBySvcTaskNumFsrNum(glblCmpyCd, svcTaskNum);
                if (fsrVisitMap != null) {
                    //START 2019/08/23 QC#53116[ADD]
                    DS_SVC_CALL_TPTMsg srchDsSvcCallTpTMsg = new DS_SVC_CALL_TPTMsg();
                    srchDsSvcCallTpTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                    srchDsSvcCallTpTMsg.dsSvcCallTpCd.setValue(svcTaskTMsgList.no(i).dsSvcCallTpCd.getValue());
                    DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(srchDsSvcCallTpTMsg);
                    //END   2019/08/23 QC#53116[ADD]
                    BigDecimal exchRate = getAcctDlyActlExchRatesByCcyCd(glblCmpyCd, slsDt, fsrVisitMap);
                    //START 2019/08/23 QC#53116[MOD]
                    //callSdCompletionApi(pMsg, fsrVisitMap, exchRate);
                    callSdCompletionApi(pMsg, fsrVisitMap, exchRate, dsSvcCallTpTMsg.svcIstlReqFlg.getValue());
                    //END   2019/08/23 QC#53116[MOD]
                    // del start 2016/09/09 CSA Defect#14206
//                    break;
                    // del end 2016/09/09 CSA Defect#14206
                }
            }
        }
    }

    //START 2019/08/23 QC#53116[MOD]
    //private void callSdCompletionApi(NSZC003001PMsg pMsg, Map<String, Object> fsrVisitMap, BigDecimal exchRate) {
    private void callSdCompletionApi(NSZC003001PMsg pMsg, Map<String, Object> fsrVisitMap, BigDecimal exchRate, String svcIstlReqFlg) {
    //END   2019/08/23 QC#53116[MOD]

       //START 2019/08/23 QC#53116[MOD]
       //NSZC005001PMsg apiPMsg = setNSZC005001PMsgApiPMsgCompletion(pMsg, fsrVisitMap, exchRate);
        NSZC005001PMsg apiPMsg = setNSZC005001PMsgApiPMsgCompletion(pMsg, fsrVisitMap, exchRate, svcIstlReqFlg);
        //END   2019/08/23 QC#53116[MOD]

        new NSZC005001().execute(apiPMsg, onBatType);
        List<String> errList = S21ApiUtil.getXxMsgIdList(apiPMsg);
        if (errList.size() > 0) {
            for (String msgId : errList) {
                msgMap.addXxMsgId(msgId);
                msgMap.flush();
            }
        }
    }
    //START 2019/08/23 QC#53116[MOD]
    //private NSZC005001PMsg setNSZC005001PMsgApiPMsgCompletion(NSZC003001PMsg pMsg, Map<String, Object> fsrVisitMap, BigDecimal exchRate) {
    private NSZC005001PMsg setNSZC005001PMsgApiPMsgCompletion(NSZC003001PMsg pMsg, Map<String, Object> fsrVisitMap, BigDecimal exchRate, String svcIstlReqFlg) {
    //END   2019/08/23 QC#53116[MOD]
        //START 2019/08/23 QC#53116[MOD]
        NSZC005001PMsg apiPMsg = new NSZC005001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_DEBRIEF);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, pMsg.xxSvcTaskList.no(0).userId);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, (String) fsrVisitMap.get("FSR_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitNum, (String) fsrVisitMap.get("FSR_VISIT_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, (BigDecimal) fsrVisitMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitArvDt, (String) fsrVisitMap.get("FSR_VISIT_ARV_DT"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitArvTm, (String) fsrVisitMap.get("FSR_VISIT_ARV_TM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitCpltDt, (String) fsrVisitMap.get("FSR_VISIT_CPLT_DT"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitCpltTm, (String) fsrVisitMap.get("FSR_VISIT_CPLT_TM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTrvlTmNum, (BigDecimal) fsrVisitMap.get("SVC_TRVL_TM_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.invCcyCd, (String) fsrVisitMap.get("INV_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.ccyExchRate, exchRate);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcChrgContFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, (String) fsrVisitMap.get("SVC_BILL_TP_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, (String) fsrVisitMap.get("PMT_TERM_CASH_DISC_CD"));
        if (svcIstlReqFlg.equals(ZYPConstant.FLG_ON_Y)) {
            String istlCpltFlg = ZYPConstant.FLG_ON_Y;
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC005001Constant.MODE_INSTALL_CHECK);
            FSR_ISTL_CHK_LISTTMsgArray istlChkListTmsgArray = getIstlChkListBySvcTaskNum(pMsg.glblCmpyCd.getValue(), (String) fsrVisitMap.get("FSR_NUM"), (String) fsrVisitMap.get("SVC_TASK_NUM"));
            for(int k = 0; k < istlChkListTmsgArray.getValidCount(); k++){
                FSR_ISTL_CHK_LISTTMsg istlChkListTMsg = istlChkListTmsgArray.no(k);
                NSZC005001_xxFsrIstlChkListPMsg stlChkList = apiPMsg.xxFsrIstlChkList.no(k);
                setValue(stlChkList.fsrIstlChkListPk, istlChkListTMsg.fsrIstlChkListPk);
                setValue(stlChkList.fsrNum, istlChkListTMsg.fsrNum);
                setValue(stlChkList.svcTaskNum, istlChkListTMsg.svcTaskNum);
                setValue(stlChkList.svcConfigMstrPk, istlChkListTMsg.svcConfigMstrPk);
                setValue(stlChkList.mdseCd, istlChkListTMsg.mdseCd);
                setValue(stlChkList.mdlId, istlChkListTMsg.mdlId);
                setValue(stlChkList.mdlNm, istlChkListTMsg.mdlNm);
                setValue(stlChkList.serNum, istlChkListTMsg.serNum);
                setValue(stlChkList.istlChkVerFlg, istlChkListTMsg.istlChkVerFlg);
                setValue(stlChkList.crctSerNum, istlChkListTMsg.crctSerNum);
                if (istlChkListTMsg.istlCpltFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                    istlCpltFlg = ZYPConstant.FLG_OFF_N;
                }
            }
            ZYPEZDItemValueSetter.setValue(apiPMsg.istlCpltFlg, istlCpltFlg);
            apiPMsg.xxFsrIstlChkList.setValidCount(istlChkListTmsgArray.getValidCount());
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC005001Constant.MODE_DEBRIEF);
        }
        //END   2019/08/23 QC#53116[MOD]
        return apiPMsg;
    }

    private void updateCcyAndRateOfTask(NSZC003001PMsg pMsg, List<Map<String, Object>> svcTaskList, String fsrNum) {

        FSRTMsg fsrTMsg = getFsr(pMsg, fsrNum);
        if (fsrTMsg == null) {
            return;
        }
        for (Map<String, Object> svcTaskMap : svcTaskList) {
            SVC_TASKTMsg svcTaskTMsg = getSvcTaskTMsg(pMsg, svcTaskMap, fsrNum);

            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.origInvCcyCd, fsrTMsg.origInvCcyCd);
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.invCcyCd, fsrTMsg.invCcyCd);
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.ccyExchRate, fsrTMsg.ccyExchRate);
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.pmtTermCashDiscCd, fsrTMsg.pmtTermCashDiscCd);

            S21ApiTBLAccessor.update(svcTaskTMsg);

            checkRtnCodeForUpdate(pMsg, svcTaskTMsg);
        }

    }

    private FSRTMsg getFsr(NSZC003001PMsg pMsg, String fsrNum) {
        FSRTMsg paramFsrTMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrNum, fsrNum);

        FSRTMsg fsrTMsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(paramFsrTMsg);
        checkRtnCodeForSearch(pMsg, fsrTMsg, paramFsrTMsg);

        return fsrTMsg;
    }

//    private String getMblIntfcFlg(String glblCmpyCd, String svcDisptEventCd) {
//        SVC_DISPT_EVENTTMsg tMsg = new SVC_DISPT_EVENTTMsg();
//        setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        setValue(tMsg.svcDisptEventCd, svcDisptEventCd);
//        SVC_DISPT_EVENTTMsg outTMsg = (SVC_DISPT_EVENTTMsg) S21ApiTBLAccessor.findByKey(tMsg);
//        if (outTMsg == null) {
//            return ZYPConstant.FLG_ON_Y;
//        }
//        return outTMsg.mblIntfcFlg.getValue();
//    }

    private void setValueCompletion(EZDTStringItem dbValue, EZDPStringItem paramValue) {
        if (ZYPCommonFunc.hasValue(paramValue)) {
            ZYPEZDItemValueSetter.setValue(dbValue, paramValue);
        }
        return;
    }

    private void setValueCompletion(EZDTDateItem dbValue, EZDPDateItem paramValue) {
        if (ZYPCommonFunc.hasValue(paramValue)) {
            ZYPEZDItemValueSetter.setValue(dbValue, paramValue);
        }
        return;
    }
    private String isPndIstl(NSZC003001PMsg pMsg) {
        Map<String, Object> svcTaskMap = new HashMap<String, Object>();
        svcTaskMap.put(SVC_TASK_NUM, pMsg.xxSvcTaskList.no(0).svcTaskNum.getValue());
        SVC_TASKTMsg svcTaskTmsg = getSvcTaskTMsg(pMsg, svcTaskMap, null);
        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        if (isClickSendExclCall(svcTaskTmsg.dsSvcCallTpCd.getValue())) {
            return ZYPConstant.FLG_OFF_N;
        }
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]
        if (ZYPCommonFunc.hasValue(svcTaskTmsg.soNum)) {
            return ZYPConstant.FLG_ON_Y;
        }
        return ZYPConstant.FLG_OFF_N;
    }
    boolean isChangeStatus(String beforeFsrVisitStsCd, String afterFsrVisitStsCd) {
        if (!ZYPCommonFunc.hasValue(beforeFsrVisitStsCd) || !ZYPCommonFunc.hasValue(afterFsrVisitStsCd)) {
            return false;
        }
        if (beforeFsrVisitStsCd.equals(afterFsrVisitStsCd)) {
            return false;
        }
        return true;
    }

// START 2016/11/16 N.Arai [QC#15860, MOD]
    private Map<String, String> setAssignTech(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("required", SVC_ASG_TP.REQUIRED);
        paramMap.put("preferred", SVC_ASG_TP.PREFERRED);

        return (Map<String, String>) ssmBatchClient.queryObject("getAssignTech", paramMap);
    }
// END 2016/11/16 N.Arai [QC#15860, MOD]

    // START 2017/09/04 K.Kitachi [QC#20053, ADD]
    boolean isVisitLtst(FSR_EVENTTMsg fsrEventTMsg) {
        if (!ZYPCommonFunc.hasValue(fsrEventTMsg.svcTaskNum)) {
            return true;
        }
        FSR_VISITTMsg fsrVisitTMsg = getFsrVisitBySvcTaskNum(fsrEventTMsg.glblCmpyCd.getValue(), fsrEventTMsg.svcTaskNum.getValue());
        if (fsrVisitTMsg == null) {
            return true;
        }
        if (fsrVisitTMsg.fsrVisitLtstFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }
    // END 2017/09/04 K.Kitachi [QC#20053, ADD]
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
    // END 2018/01/09 U.Kim [QC#19702, ADD]
    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    private boolean isAhsCall(String glblCmpyCd, String svcTaskNum) {
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            return false;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        String aftHrsFlg = (String) ssmBatchClient.queryObject("getAftHrsFlg", paramMap);
        if (!ZYPCommonFunc.hasValue(aftHrsFlg)) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(aftHrsFlg)) {
            return true;
        }
        return false;
    }
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

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
    //START 2019/08/23 QC#53116[ADD]
    private FSR_ISTL_CHK_LISTTMsgArray getIstlChkListBySvcTaskNum(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_ISTL_CHK_LISTTMsg tMsg = new FSR_ISTL_CHK_LISTTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_ISTL_CHK_LISTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    //END   2019/08/23 QC#53116[ADD]
}
