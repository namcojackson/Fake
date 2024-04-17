/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC068001;

import static com.canon.cusa.s21.api.NSZ.NSZC068001.cnstant.NSZC068001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import parts.common.EZDMsg;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CLICK_TECH_ORD_PRTTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_CONTRTMsg;
import business.db.FSR_ISTL_CHK_LISTTMsg;
import business.db.FSR_ISTL_CHK_LISTTMsgArray;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.DS_SVC_CALL_TPTMsgArray;
import business.db.FSRTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.PRCH_REQTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_CALL_PRTYTMsg;
import business.db.SVC_CALL_PRTYTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MBL_INTFC_XREFTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_RGTMsg;
import business.db.SVC_RGTMsgArray;
import business.db.SVC_SUPPL_TASKTMsg;
import business.db.SVC_SUPPL_TASK_TPTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASK_STSTMsg;
import business.db.SVC_TASK_STSTMsgArray;
import business.db.SVC_TM_EVENTTMsg;
import business.db.SVC_TM_EVENTTMsgArray;
import business.db.TECH_MSTRTMsg;
import business.parts.NPZC117001PMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC005001_xxExpenseListPMsg;
import business.parts.NSZC005001_xxFsrIstlChkListPMsg;
import business.parts.NSZC005001_xxFsrUsgListPMsg;
import business.parts.NSZC005001_xxMtrReadListPMsg;
import business.parts.NSZC005001_xxPartSurveyListPMsg;
import business.parts.NSZC005001_xxTmEventListPMsg;
import business.parts.NSZC005001_xxVisitTaskListPMsg;
import business.parts.NSZC012001PMsg;
import business.parts.NSZC012001_FSRVisitListPMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC043001_svcMemoListPMsg;
import business.parts.NSZC043001_taskListPMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC045001_xxSvcTaskListPMsg;
import business.parts.NSZC058001PMsg;
import business.parts.NSZC068001PMsg;
import business.parts.NSZC068001_AssignmentListPMsg;
import business.parts.NSZC068001_CsaInstallCheckListPMsg;
import business.parts.NSZC068001_CsaPartsOrderListPMsg;
import business.parts.NSZC068001_CsaSupplementalTimeListPMsg;
import business.parts.NSZC068001_PartsLookupListPMsg;
import business.parts.NSZC068001_svcMemoListPMsg;
import business.parts.NSZC073001PMsg;
import business.parts.NSZC075001PMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC117001.NPZC117001;
import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.api.NSZ.NSZC005001.constant.NSZC005001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC012001.NSZC012001;
import com.canon.cusa.s21.api.NSZ.NSZC012001.constant.NSZC012001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.api.NSZ.NSZC073001.NSZC073001;
import com.canon.cusa.s21.api.NSZ.NSZC058001.NSZC058001;
import com.canon.cusa.s21.api.NSZ.NSZC058001.constant.NSZC058001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC075001.NSZC075001;
import com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_PRTY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Receive Task Info from Click Software API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/13   Fujitsu         N.Sugiura       Create          N/A
 * 2015/11/18   Hitachi         T.Iwamoto       Update          N/A
 * 2015/12/24   Hitachi         T.Iwamoto       Update          QC#1404
 * 2016/01/07   Hitachi         K.Yamada        Update          CSA QC#2558,2563
 * 2016/02/09   Hitachi         Y.Takeno        Update          QC#3727
 * 2016/02/17   Hitachi         T.Iwamoto       Update          QC#3727
 * 2016/02/19   Hitachi         T.Iwamoto       Update          QC#4108
 * 2016/02/22   Hitachi         T.Iwamoto       Update          QC#2563
 * 2016/03/03   Hitachi         T.Iwamoto       Update          QC#4766
 * 2016/03/03   Hitachi         T.Iwamoto       Update          QC#5468
 * 2016/03/17   Hitachi         T.Tomita        Update          QC#5583
 * 2016/03/31   Hitachi         K.Yamada        Update          CSA QC#6270
 * 2016/04/01   Hitachi         T.Iwamoto       Update          CSA QC#6020
 * 2016/04/20   Hitachi         T.Iwamoto       Update          CSA QC#4467,4469
 * 2016/05/24   Hitachi         T.Iwamoto       Update          CSA QC#8809
 * 2016/06/02   Hitachi         T.Iwamoto       Update          CSA QC#8686,9320
 * 2016/06/17   Hitach          A.Kohinata      Update          CSA QC#9677
 * 2016/07/01   Hitachi         T.Iwamoto       Update          CSA QC#11185,9677
 * 2016/07/06   Hitachi         T.Iwamoto       Update          CSA QC#11284
 * 2016/07/29   Hitachi         O.Okuma         Update          CSA QC#10483
 * 2016/09/02   Hitachi         T.Mizuki        Update          CSA QC#11064
 * 2016/09/09   Hitachi         T.Kanasaka      Update          CSA QC#14206
 * 2016/09/14   Hitachi         T.Tomita        Update          CSA QC#7202
 * 2016/10/05   Hitachi         K.Yamada        Update          CSA QC#14803
 * 2016/10/13   Hitachi         Y.Takeno        Update          CSA QC#14876
 * 2016/11/08   Hitachi         A.Kohinata      Update          CSA QC#15780
 * 2016/11/09   Hitachi         T.Tomita        Update          CSA QC#15872
 * 2016/11/10   Hitachi         Y.Takeno        Update          CSA QC#15732
 * 2016/11/17   Hitachi         Y.Takeno        Update          CSA QC#16060
 * 2016/11/18   Hitachi         N.Arai          Update          CSA QC#16061
 * 2016/11/18   Hitachi         Y.Takeno        Update          CSA QC#16060-1
 * 2016/11/21   Hitachi         K.Ochiai        Update          CSA QC#15782
 * 2016/11/22   Hitachi         T.Mizuki        Update          CSA QC#16145
 * 2016/11/25   Hitachi         T.Tomita        Update          CSA QC#16051
 * 2016/11/29   Hitachi         T.Tomita        Update          CSA QC#16145
 * 2016/12/07   Hitachi         K.Yamada        Update          CSA QC#16436 (Temporary solution)
 * 2016/12/15   Hitachi         K.Kojima        Update          CSA QC#16436
 * 2016/12/20   Hitachi         K.Kojima        Update          CSA QC#16436
 * 2016/12/21   Hitachi         T.Tomita        Update          CSA QC#16514
 * 2017/01/30   Hitachi         Y.Takeno        Update          CSA QC#17291
 * 2017/02/22   Hitachi         K.Kojima        Update          CSA QC#16301
 * 2017/08/09   Hitachi         K.Kojima        Update          CSA QC#19796
 * 2017/09/01   Hitachi         K.Kim           Update          CSA QC#20672
 * 2017/09/06   Hitachi         A.Kohinata      Update          CSA QC#15134
 * 2017/10/15   Hitachi         T.Tomita        Update          CSA QC#21648
 * 2017/10/25   CITS            M.Naito         Update          CSA QC#22061
 * 2017/12/14   Hitachi         M.Kidokoro      Update          CSA QC#22223
 * 2018/01/29   Hitachi         U.Kim           Update          CSA QC#19702
 * 2018/03/22   CITS            M.Naito         Update          CSA QC#18713
 * 2018/06/01   CITS            M.Naito         Update          CSA QC#26272
 * 2018/07/05   Hitachi         T.Tomita        Update          CSA QC#27015
 * 2018/07/10   Hitachi         A.Kohinata      Update          CSA QC#27089
 * 2018/07/30   Hitachi         A.Kohinata      Update          CSA QC#20956
 * 2018/08/03   Hitachi         T.Tomita        Update          CSA QC#27605
 * 2018/08/20   CITS            T.Wada          Update          CSA QC#27661
 * 2018/08/20   CITS            T.Kikuhara      Update          CSA QC#12323
 * 2018/08/27   CITS            T.Wada          Update          CSA QC#27882
 * 2018/08/27   CITS            T.Wada          Update          CSA QC#23385
 * 2019/01/21   Fujitsu         W.Honda         Update          CSA QC#28650
 * 2019/03/13   Hitachi         A.Kohinata      Update          CSA QC#30697
 * 2019/03/29   Hitachi         A.Kohinata      Update          CSA QC#30697-2
 * 2019/05/31   Hitachi         K.Kim           Update          CSA QC#50574
 * 2019/06/19   Hitachi         K.Fujimoto      Update          CSA QC#50880
 * 2019/06/25   Hitachi         K.Kitachi       Update          CSA QC#50938
 * 2019/07/22   Hitachi         S.Kitamura      Update          CSA QC#51521
 * 2019/10/10   Hitachi         K.Fujimoto      Update          CSA QC#54070
 * 2019/11/08   Hitachi         K.Fujimoto      Update          CSA QC#54400
 * 2019/11/14   Hitachi         K.Fujimoto      Update          CSA QC#52262
 * 2019/11/29   Hitachi         A.Kohinata      Update          CSA QC#54113
 * 2020/06/01   Hitachi         K.Kitachi       Update          CSA QC#56271
 * 2020/06/29   Hitachi         K.Kitachi       Update          CSA QC#57231
 * 2020/07/17   Hitachi         K.Yamada        Update          CSA QC#57385 (Revert 57261)
 * 2020/07/29   Hitachi         K.Kitachi       Update          CSA QC#57410
 * 2021/01/14   CITS            K.Ogino         Update          CSA QC#58229
 * 2021/09/10   CITS            R.Cabral        Update          CSA QC#58979
 * 2021/10/04   CITS            R.Cabral        Update          CSA QC#59192
 * 2022/04/27   Hitachi         K.Kitachi       Update          CSA QC#59895
 * 2022/10/31   Hitachi         K.Kitachi       Update          CSA QC#60750
 * 2022/12/01   Hitachi         K.Kitachi       Update          CSA QC#60882
 * 2023/04/03   Hitachi         K.Kitachi       Update          CSA QC#61263
 * 2023/09/14   Hitachi         N.Takatsu       Update          CSA QC#61780
 * 2023/09/25   Hitachi         N.Takatsu       Update          CSA QC#61780
 * 2023/11/07   Hitachi         K.Kitachi       Update          CSA QC#61780
 * 2023/11/07   Hitachi         K.Kitachi       Update          CSA QC#61648
 *</pre>
 */
public class NSZC068001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** sysDate */
    private String sysDate = "";

    /** sysTime */
    private String sysTime = "";

    /** varConstmap */
    private Map<String, String> varCharConstMap = new HashMap<String, String>();
    
    // START 2018/01/29 U.Kim [QC#19702, ADD]
    /** numConstmap */
    private Map<String, BigDecimal> numConstMap = new HashMap<String, BigDecimal>();
    // END 2018/01/29 U.Kim [QC#19702, ADD]

    /** svcTaskStsCd */
    private String svcTaskStsCd = "";

    /** svcCallPrtyCd */
    private String svcCallPrtyCd = "";

    /** chkChngPriority */
    private boolean chkChngPriority = false;

    /** chkChngStatus */
    private boolean chkChngStatus = false;

    /** techCd */
    private String apiTechCd = "";

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** ReceiveTaskInfoMap */
    private Map<String, Object> rcvTaskInfoMap = null;

    /** Click Date Format */
    private String dateFormat = null;

    /**
     * Constructor
     */
    public NSZC068001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Receive Task Info from Click Software API
     * </pre>
     * @param param NSZC068001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC068001PMsg param, final ONBATCH_TYPE onBatchTp) {

        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        String svcTaskNum = param.svcTaskNum.getValue();
        S21StopWatch sw = new S21StopWatch();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0680 : execute Start." + " Task#" + svcTaskNum);
        sw.start();
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]

        this.onBatchType = onBatchTp;

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // Checking Input value
        checkInput(msgMap);

        // main
        doProcess(msgMap);

        // end
        msgMap.flush();

        // QC#12323 ADD START
        if (msgMap.getMsgMgr().isXxMsgId()) {
            // START 2019/11/14 K.Fujimoto [QC#52262, ADD]
            getVarCharConst(NSZC0680_EXCLD_THROW_ABEND, param.glblCmpyCd.getValue());
            // END   2019/11/14 K.Fujimoto [QC#52262, ADD]
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(param);
            if (msgList != null && !msgList.isEmpty()) {
                // START 2019/11/14 K.Fujimoto [QC#52262, ADD]
                List<String> excldMsgIdList = getExcldMsgId(param);
                // END   2019/11/14 K.Fujimoto [QC#52262, ADD]
                for (S21ApiMessage msg : msgList) {
                    String msgId = msg.getXxMsgid();
                    // START 2019/11/14 K.Fujimoto [QC#52262, ADD]
                    // If the error is included in the list, don't throw abend exception. 
                    if (excldMsgIdList.contains(msgId)) {
                        continue;
                    }
                    // END   2019/11/14 K.Fujimoto [QC#52262, ADD]
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith("E")) {
                        throw new S21AbendException("Task#" + param.svcTaskNum.getValue() + " " + S21MessageFunc.clspGetMessage(msgId, msgPrms));
                    }
                }
            }
        }
        // QC#12323 ADD END

        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        sw.stop();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0680 : execute End." + " Task#" + svcTaskNum);
        S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0680 : execute Elapsed Time [%s]", sw.getElapsedMilliSec()));
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
    }

    // START 2019/11/14 K.Fujimoto [QC#52262, ADD]
    private List<String> getExcldMsgId(NSZC068001PMsg param) {
       String excldMsgId = getVarCharConst(NSZC0680_EXCLD_THROW_ABEND, param.glblCmpyCd.getValue());
       if (!hasValue(excldMsgId)) {
           return new ArrayList<String>();
       }
       return Arrays.asList(excldMsgId.split(COMMA));
    }
    // END   2019/11/14 K.Fujimoto [QC#52262, ADD]

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     */
    private void checkInput(S21ApiMessageMap msgMap) {

        NSZC068001PMsg param = (NSZC068001PMsg) msgMap.getPmsg();
        if (!hasValue(param.glblCmpyCd)) {
            setValue(param.glblCmpyCd, GLBL_CMPY_CD);
        }

        if (!hasValue(param.slsDt)) {
            setValue(param.slsDt, (String) ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()));
        }
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        NSZC068001PMsg param = (NSZC068001PMsg) msgMap.getPmsg();
        // init
        if (!init(msgMap, param)) {
            return;
        }

        // Date Format check /Master check / Status mandatory check
        if (!chkInputParam(msgMap, param)) {
            return;
        }

        if (!callApi(msgMap, param)) {
            return;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return returnValue
     */
    private boolean init(S21ApiMessageMap msgMap, NSZC068001PMsg param) {
        // Set sysdate
        sysDate = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);
        sysTime = S21StringUtil.subStringByLength(ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS), TIME_START_POS, TIME_END_POS);

        // Set VAR_CHAR_CONST
        String glblCmpyCd = param.glblCmpyCd.getValue();
        if (!setVarCharConst(msgMap, glblCmpyCd)) {
            return false;
        }
        // START 2018/01/29 U.Kim [QC#19702, ADD] 
        // Set NUM_CONST
        if (!setNumConst(msgMap, glblCmpyCd)) {
            return false;
        }
        // END 2018/01/29 U.Kim [QC#19702, ADD]

        // Get SvcTaskInfo
        if (!getSvcTaskInfo(msgMap, param)) {
            return false;
        }
        // set apiTechCd
        if (hasValue(param.techCd)) {
            apiTechCd = (String) param.techCd.getValue();
        } else if (param.AssignmentList.getValidCount() > 0 && hasValue(param.AssignmentList.no(0).techCd)) {
            apiTechCd = (String) param.AssignmentList.no(0).techCd.getValue();
        } else {
            apiTechCd = (String) rcvTaskInfoMap.get(TECH_CD);
        }

        // Get SvcCallPrtyCd
        if (!getSvcCallPrtyCd(msgMap, param)) {
            return false;
        }

        // Get SvcTaskStsCd
        if (!getStatus(msgMap, param)) {
            return false;
        }

        // Check Difference >> true
        if (!svcCallPrtyCd.equals((String) rcvTaskInfoMap.get(SVC_CALL_PRTY_CD))) {
            chkChngPriority = true;
        }
        if (!svcTaskStsCd.equals((String) rcvTaskInfoMap.get(FSR_VISIT_STS_CD))) {
            chkChngStatus = true;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return returnValue
     */
    private boolean getSvcTaskInfo(S21ApiMessageMap msgMap, NSZC068001PMsg param) {
        boolean returnValue = true;
        // START 2018/01/29 U.Kim [QC#19702, ADD]
        int timesFindTask = getNumConst(SLEEP_TIMES_FIND_SVC_TASK, param.glblCmpyCd.getValue()).intValue();
        long millSecFindTask = getNumConst(SLEEP_MILL_SEC_FIND_SVC_TASK, param.glblCmpyCd.getValue()).longValue();
        // END 2018/01/29 U.Kim [QC#19702, ADD]
        // Add Start 2018/07/05 QC#27015
        int retryCount = getIntValueForNumConst(RETRY_COUNT_FOR_LOCK, param.glblCmpyCd.getValue(), DEF_VAL_RETRY_COUNT);
        int waitMsec = getIntValueForNumConst(NSZC1070_WAIT_MSEC, param.glblCmpyCd.getValue(), DEF_VAL_WAIT_MSEC);
        // Add End 2018/07/05 QC#27015

        if (!hasValue(param.svcTaskNum)) {
            msgMap.addXxMsgIdWithPrm(NSZM0586E, new String[] {ERR_VAR_CALL_ID });
            return false;
        }

        // Header
        // START 2018/01/29 U.Kim [QC#19702, MOD]
        // if (!getSvcTaskInfoList(param)) {
        //     returnValue = false;
        //     msgMap.addXxMsgIdWithPrm(NSZM0586E, new String[] {ERR_VAR_CALL_INFO });
        // }
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        String svcTaskNum = param.svcTaskNum.getValue();
        S21StopWatch sw = new S21StopWatch();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0680 : getSvcTaskInfo Start." + " Task#" + svcTaskNum);
        sw.start();
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        for (int i = 0; i < timesFindTask; i++) {
            if (!getSvcTaskInfoList(param)) {
                sleep(millSecFindTask);
                returnValue = false;
            }else{
                // Add Start 2018/07/05 QC#27015
                if (!lockSvcTask(param, retryCount, waitMsec)) {
                    returnValue = false;
                    continue;
                }
                // Add End 2018/07/05 QC#27015
                returnValue = true;
                break;
            }
        }
        // START 2022/12/01 K.Kitachi [QC#60882, ADD]
        sw.stop();
        S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0680 : getSvcTaskInfo End." + " Task#" + svcTaskNum);
        S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0680 : getSvcTaskInfo Elapsed Time [%s]", sw.getElapsedMilliSec()));
        // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        if (returnValue == false) {
            msgMap.addXxMsgIdWithPrm(NSZM0586E, new String[] {ERR_VAR_CALL_INFO });
        }
        // END 2018/01/29 U.Kim [QC#19702, MOD]
        return returnValue;
    }

    /**
     * getSvcTaskInfoList
     * @param pMsg NSZC068001PMsg
     * @return true/false
     */
    private boolean getSvcTaskInfoList(NSZC068001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rslt = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcTaskInfo", param);

        if (rslt.size() == 0) {
            return false;
        }
        rcvTaskInfoMap = rslt.get(0);
        return true;
    }

    /**
     * getSvcCallPrtyCd
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC068001PMsg
     * @return returnValue
     */
    private boolean getSvcCallPrtyCd(S21ApiMessageMap msgMap, NSZC068001PMsg pMsg) {
        // mod start 2016/03/15 CSA Defect#5468
        if (hasValue(pMsg.xtrnlCallPrtyRefTxt)) {
            SVC_CALL_PRTYTMsg inMsg = new SVC_CALL_PRTYTMsg();
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("xtrnlCallPrtyRefTxt01", pMsg.xtrnlCallPrtyRefTxt.getValue());
            SVC_CALL_PRTYTMsgArray outArray = (SVC_CALL_PRTYTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
            if (outArray.getValidCount() > 0) {
                svcCallPrtyCd = outArray.no(0).svcCallPrtyCd.getValue();
                return true;
            }
        }
        svcCallPrtyCd = SVC_CALL_PRTY.NO_PRIORITY;
        // mod end 2016/03/15 CSA Defect#5468
        return true;
    }

    /**
     * getStatus
     * @param msgMap S21ApiMessageMap
     * @param pMsg NSZC068001PMsg
     * @return returnValue
     */
    private boolean getStatus(S21ApiMessageMap msgMap, NSZC068001PMsg pMsg) {
        // add start 2016/03/17 CSA Defect#5583
        if (!hasValue(pMsg.xtrnlStsRefTxt)) {
            svcTaskStsCd = (String) rcvTaskInfoMap.get(FSR_VISIT_STS_CD);
            return true;
        }
        // add end 2016/03/17 CSA Defect#5583

        SVC_TASK_STSTMsg inMsg = new SVC_TASK_STSTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("xtrnlStsRefTxt01", pMsg.xtrnlStsRefTxt.getValue());
        SVC_TASK_STSTMsgArray outArray = (SVC_TASK_STSTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {ERR_VAR_STATUS });
            return false;
        }
        svcTaskStsCd = outArray.no(0).svcTaskStsCd.getValue();
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return returnValue
     */
    private boolean chkInputParam(S21ApiMessageMap msgMap, NSZC068001PMsg param) {
        boolean returnValue = true;

        // Date Format Check
        if (!chkDateFormat(msgMap, param)) {
            return false;
        }

        // Master Check
        if (!chkMaster(msgMap, param)) {
            return false;
        }

        // If change status check input parameter.
        if (chkChngStatus) {
            if (SVC_TASK_STS.IN_ROUTE.equals(svcTaskStsCd)) {
                // START 2019/06/25 K.Kitachi [QC#50938, ADD]
                setTimestamp(param.xxFsrVisitDisptTs);
                // END 2019/06/25 K.Kitachi [QC#50938, ADD]
                if (!chkStatusMandatory(msgMap, param.xxFsrVisitDisptTs.getValue(), ERR_VAR_INROUTE_TS)) {
                    returnValue = false;
                }
            }
            if (SVC_TASK_STS.ARRIVED.equals(svcTaskStsCd)) {
                // START 2019/06/25 K.Kitachi [QC#50938, ADD]
                setTimestamp(param.xxFsrVisitArvTs);
                // END 2019/06/25 K.Kitachi [QC#50938, ADD]
                if (!chkStatusMandatory(msgMap, param.xxFsrVisitArvTs.getValue(), ERR_VAR_ARRIVED_TS)) {
                    returnValue = false;
                }
            }
            if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
                // START 2019/06/25 K.Kitachi [QC#50938, ADD]
                setTimestamp(param.xxFsrVisitCpltTs);
                // END 2019/06/25 K.Kitachi [QC#50938, ADD]
                if (!chkStatusMandatory(msgMap, param.xxFsrVisitCpltTs.getValue(), ERR_VAR_COMP_TS)) {
                    returnValue = false;
                }
            }
            if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
                if (!chkStatusMandatory(msgMap, param.cancDt.getValue(), ERR_VAR_CANC_DT)) {
                    returnValue = false;
                }
            }
        }

        return returnValue;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return returnValue
     */
    private boolean chkDateFormat(S21ApiMessageMap msgMap, NSZC068001PMsg param) {

        dateFormat = getVarCharConst(CLICK_DATE_TIME_FORMAT, param.glblCmpyCd.getValue());
        boolean returnValue = true;
        if (!chkTypeDigit(param.fsrEventExeTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_STAMP });
            returnValue = false;
        }
        if (!chkTypeDigit(param.startTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_START });
            returnValue = false;
        }
        if (!chkTypeDigit(param.endTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_FINISH });
            returnValue = false;
        }
        if (!chkTypeDigit(param.xxFsrVisitDisptTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_INROUTE_TS });
            returnValue = false;
        }
        if (!chkTypeDigit(param.xxFsrVisitArvTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_ARRIVED_TS });
            returnValue = false;
        }
        if (!chkTypeDigit(param.xxFsrVisitCpltTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_COMP_TS });
            returnValue = false;
        }
        if (!chkTypeDigit(param.xxSvcTaskRcvTs.getValue(), dateFormat)) {
            msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_CLIENT_TS });
            returnValue = false;
        }
        for (int i = 0; i < param.CsaSupplementalTimeList.getValidCount(); i++) {
            if (!chkTypeDigit(param.CsaSupplementalTimeList.no(i).startTs.getValue(), dateFormat)) {
                msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_START_SPPL });
                returnValue = false;
            }
            if (!chkTypeDigit(param.CsaSupplementalTimeList.no(i).endTs.getValue(), dateFormat)) {
                msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_FINISH_SPPL });
                returnValue = false;
            }
        }
        for (int i = 0; i < param.AssignmentList.getValidCount(); i++) {
            if (!chkTypeDigit(param.AssignmentList.no(i).startTs.getValue(), dateFormat)) {
                msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_START_ASS });
                returnValue = false;
            }
            if (!chkTypeDigit(param.AssignmentList.no(i).endTs.getValue(), dateFormat)) {
                msgMap.addXxMsgIdWithPrm(NSZM0587E, new String[] {dateFormat, ERR_VAR_FINISH_ASS });
                returnValue = false;
            }
        }
        return returnValue;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return returnValue
     */
    private boolean chkTypeDigit(String date, String chkDateFormat) {
        if (!hasValue(date)) {
            return true;
        }
        if (date.length() != chkDateFormat.length()) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat(chkDateFormat);
        String changeDate = null;
        try {
            changeDate = format.format(format.parse(date));
        } catch (ParseException e) {
            return false;
        }
        if (date.equals(changeDate)) {
            return true;
        }
        return false;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return true / false
     */
    private boolean chkMaster(S21ApiMessageMap msgMap, NSZC068001PMsg param) {
        boolean returnValue = true;
        String glblCmpyCd = param.glblCmpyCd.getValue();

        if (hasValue(param.svcBrMgrPsnCd.getValue())) {
            if (!chkMasterManager(msgMap, glblCmpyCd, param.svcBrMgrPsnCd.getValue(), ERR_VAR_BR_MGR)) {
                returnValue = false;
            }
        }
        if (hasValue(param.svcTrtyMgrPsnCd.getValue())) {
            if (!chkMasterManager(msgMap, glblCmpyCd, param.svcTrtyMgrPsnCd.getValue(), ERR_VAR_TRTY_MGR)) {
                returnValue = false;
            }
        }
        if (hasValue(param.svcTeamMgrPsnCd.getValue())) {
            if (!chkMasterManager(msgMap, glblCmpyCd, param.svcTeamMgrPsnCd.getValue(), ERR_VAR_TEAM_MGR)) {
                returnValue = false;
            }
        }
        if (hasValue(param.techCd.getValue())) {
            if (!chkTechCd(msgMap, glblCmpyCd, param.techCd.getValue(), ERR_VAR_TECH)) {
                returnValue = false;
            }
        }
        if (hasValue(param.xxSvcResrcMgrPsnCd.getValue())) {
            if (!chkMasterManager(msgMap, glblCmpyCd, param.xxSvcResrcMgrPsnCd.getValue(), ERR_VAR_RESR_MGR)) {
                returnValue = false;
            }
        }
        // mod start 2016/01/07 CSA Defect#2558
        if (hasValue(param.xtrnlPblmLocTpRefTxt.getValue())) {
            if (!chkSvcPblmLocTp(msgMap, glblCmpyCd, param.xtrnlPblmLocTpRefTxt.getValue(), ERR_VAR_LOC)) {
                returnValue = false;
            }
        }
        if (hasValue(param.xtrnlPblmRsnTpRefTxt.getValue())) {
            if (!chkSvcPblmRsnTp(msgMap, glblCmpyCd, param.xtrnlPblmRsnTpRefTxt.getValue(), ERR_VAR_RSN)) {
                returnValue = false;
            }
        }
        if (hasValue(param.xtrnlPblmCrctTpRefTxt.getValue())) {
            if (!chkSvcPblmCrctTp(msgMap, glblCmpyCd, param.xtrnlPblmCrctTpRefTxt.getValue(), ERR_VAR_CRCT)) {
                returnValue = false;
            }
        }
        // mod end 2016/01/07 CSA Defect#2558
        for (int i = 0; i < param.CsaSupplementalTimeList.getValidCount(); i++) {
// START  02/19/2016 T.Iwamoto [QC#4108, DEL]
//            if (hasValue(param.CsaSupplementalTimeList.no(i).xtrnlStsRefTxt)) {
//                if (!chkSpplStatus(msgMap, glblCmpyCd, param.CsaSupplementalTimeList.no(i).xtrnlStsRefTxt.getValue(), ERR_VAR_STS_SPPL)) {
//                    returnValue = false;
//                }
//            }
// END  02/19/2016 T.Iwamoto [QC#4108, DEL]
            if (hasValue(param.CsaSupplementalTimeList.no(i).xtrnlCallTpRefTxt)) {
                if (!chkSupplTaskType(msgMap, glblCmpyCd, param.CsaSupplementalTimeList.no(i).xtrnlCallTpRefTxt.getValue(), ERR_VAR_TYPE_SPPL)) {
                    returnValue = false;
                }
            }
        }
        for (int i = 0; i < param.AssignmentList.getValidCount(); i++) {
            if (hasValue(param.AssignmentList.no(i).techCd)) {
                if (!chkTechCd(msgMap, glblCmpyCd, param.AssignmentList.no(i).techCd.getValue(), ERR_VAR_TECH_ASS)) {
                    returnValue = false;
                }
            }
        }
        for (int i = 0; i < param.CsaFieldRequestList.getValidCount(); i++) {
            if (hasValue(param.CsaFieldRequestList.no(i).serNum)) {
                if (!chkSerNum(msgMap, glblCmpyCd, param.CsaFieldRequestList.no(i).serNum.getValue(), ERR_VAR_SER_FRQ)) {
                    returnValue = false;
                }
            }
            if (hasValue(param.CsaFieldRequestList.no(i).xtrnlCallTpRefTxt)) {
                if (!chkDsSvcCallTpCd(msgMap, glblCmpyCd, param.CsaFieldRequestList.no(i).xtrnlCallTpRefTxt.getValue(), ERR_VAR_TYPE_FRQ)) {
                    returnValue = false;
                }
            }
            if (hasValue(param.CsaFieldRequestList.no(i).techCd)) {
                if (!chkTechCd(msgMap, glblCmpyCd, param.CsaFieldRequestList.no(i).techCd.getValue(), ERR_VAR_TECH_FRQ)) {
                    returnValue = false;
                }
            }
        }
        return returnValue;
    }

    /**
     * chkMasterManager
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkMasterManager(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {
        // Check Manager for Master
        S21_PSNTMsg inMsg = new S21_PSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("psnCd01", chkStr);
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt == 0) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
    }

    /**
     * chkSvcPblmLocTp
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkSvcPblmLocTp(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {

        // mod start 2016/01/07 CSA Defect#2558
        //SVC_PBLM_LOC_TPTMsg inMsg = new SVC_PBLM_LOC_TPTMsg();
        //inMsg.setSQLID("004");
        //inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        //inMsg.setConditionValue("svcPblmLocTpCd01", chkStr);
        //Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);

        String svcPblmLocTpCd = getSvcPblmLocTpCd(glblCmpyCd, chkStr);

        if (!hasValue(svcPblmLocTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
        // mod end 2016/01/07 CSA Defect#2558
    }

    /**
     * chkSvcPblmRsnTpCd
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkSvcPblmRsnTp(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {

        // mod start 2016/01/07 CSA Defect#2558
        //SVC_PBLM_RSN_TPTMsg inMsg = new SVC_PBLM_RSN_TPTMsg();
        //inMsg.setSQLID("004");
        //inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        //inMsg.setConditionValue("svcPblmRsnTpCd01", chkStr);
        //Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);

        String svcPblmRsnTpCd = getSvcPblmRsnTpCd(glblCmpyCd, chkStr);

        if (!hasValue(svcPblmRsnTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
        // mod end 2016/01/07 CSA Defect#2558
    }

    /**
     * chkSvcPblmCrctTpCd
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkSvcPblmCrctTp(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {

        // mod start 2016/01/07 CSA Defect#2558
        //SVC_PBLM_CRCT_TPTMsg inMsg = new SVC_PBLM_CRCT_TPTMsg();
        //inMsg.setSQLID("004");
        //inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        //inMsg.setConditionValue("svcPblmCrctTpCd01", chkStr);
        //Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);

        String svcPblmCrctTpCd = getSvcPblmCrctTpCd(glblCmpyCd, chkStr);

        if (!hasValue(svcPblmCrctTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
        // mod end 2016/01/07 CSA Defect#2558
    }

    /**
     * chkTechCd
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkTechCd(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {

        TECH_MSTRTMsg inMsg = new TECH_MSTRTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("techTocCd", chkStr);
        TECH_MSTRTMsg outMsg = (TECH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
    }

    /**
     * chkSerNum
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkSerNum(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", chkStr);
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt == 0) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
    }

    /**
     * chkDsSvcCallTpCd
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkDsSvcCallTpCd(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {
        String dsSvcCallTpCd = getDsSvcCallTpCd(glblCmpyCd, chkStr);
        if (!hasValue(dsSvcCallTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
    }

// START  02/19/2016 T.Iwamoto [QC#4108, DEL]
//    /**
//     * chkSpplStatus
//     * @param msgMap S21ApiMessageMap
//     * @param glblCmpyCd String
//     * @param chkStr String
//     * @param errVar String
//     * @return returnValue
//     */
//    private boolean chkSpplStatus(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {
//        String svcSupplStatusCd = getSupplementalStatus(glblCmpyCd, chkStr);
//        if (!hasValue(svcSupplStatusCd)) {
//            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
//            return false;
//        }
//        return true;
//    }
// END  02/19/2016 T.Iwamoto [QC#4108, DEL]

    /**
     * chkSupplTaskType
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param chkStr String
     * @param errVar String
     * @return returnValue
     */
    private boolean chkSupplTaskType(S21ApiMessageMap msgMap, String glblCmpyCd, String chkStr, String errVar) {
        String svcSupplTaskTpCd = getSvcMblS21ValTxt(glblCmpyCd, SVC_SUPPL_TASK_TP_CD, chkStr);
        if (!hasValue(svcSupplTaskTpCd)) {
            msgMap.addXxMsgIdWithPrm(NSZM0588E, new String[] {errVar });
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param chkStr String
     * @param errVar String
     * @return true / false
     */
    private boolean chkStatusMandatory(S21ApiMessageMap msgMap, String chkStr, String errVar) {

        if (!hasValue(chkStr)) {
            msgMap.addXxMsgIdWithPrm(NSZM0586E, new String[] {errVar });
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set VAR_CHAR_CONST
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     */
    private boolean setVarCharConst(S21ApiMessageMap msgMap, String glblCmpyCd) {

        boolean returnValue = true;

        String vatCharConstNm = "";

        vatCharConstNm = CLICK_DATE_TIME_FORMAT;
        if (!hasValue(getVarCharConst(vatCharConstNm, glblCmpyCd))) {
            msgMap.addXxMsgId(NSZM0102E);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * @param key String
     * @param glblCmpyCd String
     * @return String
     */
    private String getVarCharConst(String key, String glblCmpyCd) {

        if (this.varCharConstMap.containsKey(key)) {
            return this.varCharConstMap.get(key);
        } else {
            String value = ZYPCodeDataUtil.getVarCharConstValue(key, glblCmpyCd);
            if (hasValue(value)) {
                this.varCharConstMap.put(key, value);
            }
            return value;
        }
    }
    // START 2018/01/29 U.Kim [QC#19702, ADD]
    /**
     * <pre>
     * Set NUM_CONST
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     */
    private boolean setNumConst(S21ApiMessageMap msgMap, String glblCmpyCd) {

        boolean returnValue = true;

        String tmsNumConstNm = "";
        String milSecNumConstNm = "";

        tmsNumConstNm = SLEEP_TIMES_FIND_SVC_TASK;
        milSecNumConstNm = SLEEP_MILL_SEC_FIND_SVC_TASK;
        if (!hasValue(getNumConst(tmsNumConstNm, glblCmpyCd))) {
            msgMap.addXxMsgId(NSZM1015E);
            returnValue = false;
        }

        if (!hasValue(getNumConst(milSecNumConstNm, glblCmpyCd))) {
            msgMap.addXxMsgId(NSZM1015E);
            returnValue = false;
        }

        // Add Start 2018/07/04 QC#27015
        if (!hasValue(getNumConst(RETRY_COUNT_FOR_LOCK, glblCmpyCd))) {
            msgMap.addXxMsgId(NSZM1015E);
            returnValue = false;
        }

        if (!hasValue(getNumConst(NSZC1070_WAIT_MSEC, glblCmpyCd))) {
            msgMap.addXxMsgId(NSZM1015E);
            returnValue = false;
        }
        // Add End 2018/07/04 QC#27015
        return returnValue;
    }
    /**
     * @param key String
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    private BigDecimal getNumConst(String key, String glblCmpyCd) {

        if (this.numConstMap.containsKey(key)) {
            return this.numConstMap.get(key);
        } else {
            BigDecimal numConst = ZYPCodeDataUtil.getNumConstValue(key, glblCmpyCd);
            if (hasValue(numConst)) {
                this.numConstMap.put(key, numConst);
            }
            return numConst;
        }
    }
    // END 2018/01/29 U.Kim [QC#19702, ADD]

    /**
     * @param msgMap S21ApiMessageMap
     * @param param NSZC068001PMsg
     * @return boolean
     */
    private boolean callApi(S21ApiMessageMap msgMap, NSZC068001PMsg param) {

        // add start 2016/06/17 QC#9677
        List<String> svcTaskNumList = new ArrayList<String>();
        // add end 2016/06/17 QC#9677

        // START 2016/12/20 K.Kojima [QC#16436,ADD]
        boolean callNSZC0430Flag = false;
        // END 2016/12/20 K.Kojima [QC#16436,ADD]

        // Call Technician Arrived Update API
        if (checkArriveStatus(param)) {
            if (isTecArrivedUpdate(param)) {
                final NSZC012001 callTecArrivedUpdateApi = new NSZC012001();
                NSZC012001PMsg callTecArrivedUpdateParam = createTecArrivedUpdateParam(param);
                callTecArrivedUpdateApi.execute(callTecArrivedUpdateParam, this.onBatchType);

                if (callTecArrivedUpdateParam.xxMsgIdList.getValidCount() != 0) {
                    for (int i = 0; i < callTecArrivedUpdateParam.xxMsgIdList.getValidCount(); i++) {
                        msgMap.addXxMsgId(callTecArrivedUpdateParam.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return false;
                }
                // START 2016/12/20 K.Kojima [QC#16436,ADD]
                callNSZC0430Flag = true;
                // END 2016/12/20 K.Kojima [QC#16436,ADD]
            }
        }

        // START 2021/09/10 R.Cabral [QC#58979, ADD]
        // START 2023/11/07 K.Kitachi [QC#61780, DEL]
//        // Call Supplemental Update API
//        if (isSupplementalUpdate(param)) {
//            for (int i = 0; i < param.CsaSupplementalTimeList.getValidCount(); i++) {
//                final NSZC058001 callSupplementalUpdateApi = new NSZC058001();
//                NSZC058001PMsg callSupplementalUpdateParam = createSupplementalUpdateParam(param, i);
//                callSupplementalUpdateApi.execute(callSupplementalUpdateParam, this.onBatchType);
//                if (callSupplementalUpdateParam.xxMsgIdList.getValidCount() != 0) {
//                    for (int j = 0; j < callSupplementalUpdateParam.xxMsgIdList.getValidCount(); j++) {
//                        msgMap.addXxMsgIdWithPrm(callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgId.getValue(), new String[] {callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue(),
//                                callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_1.getValue(), callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_2.getValue() });
//                    }
//                    return false;
//                }
//                if (hasValue(callSupplementalUpdateParam.svcSupplTaskNum)) {
//                    setValue(param.CsaSupplementalTimeList.no(i).svcSupplTaskNum, callSupplementalUpdateParam.svcSupplTaskNum);
//                }
//            }
//        }
        // END 2023/11/07 K.Kitachi [QC#61780, DEL]
        // END   2021/09/10 R.Cabral [QC#58979, ADD]

        if (checkFsrUpdateStatus()) {
            // Call FSR Update API / 02(Update)
            // START 2016/12/20 K.Kojima [QC#16436,MOD]
            // if (isFsrUpdate(param)) {
            if (isFsrUpdate(param) || callNSZC0430Flag) {
                // END 2016/12/20 K.Kojima [QC#16436,MOD]
                final NSZC043001 callFsrUpdateUpdApi = new NSZC043001();
                NSZC043001PMsg callFsrUpdateApiParam = createFsrUpdateApiUpdParam(param);
                callFsrUpdateUpdApi.execute(callFsrUpdateApiParam, this.onBatchType);

                if (callFsrUpdateApiParam.xxMsgIdList.getValidCount() != 0) {
                    for (int i = 0; i < callFsrUpdateApiParam.xxMsgIdList.getValidCount(); i++) {
                        msgMap.addXxMsgId(callFsrUpdateApiParam.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return false;
                }
            }
            // START 2016/12/20 K.Kojima [QC#16436,MOD]
            // } else if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd)) {
        } else if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd) && ZYPCommonFunc.hasValue(param.xxFsrVisitCpltTs)) {
            // END 2016/12/20 K.Kojima [QC#16436,MOD]
            // START 2019/10/10 K.Fujimoto [QC#54070,ADD]
            String curSts = (String)rcvTaskInfoMap.get(FSR_VISIT_STS_CD);
            if (curSts.equals(SVC_TASK_STS.COMPLETED) || curSts.equals(SVC_TASK_STS.CLOSED) || curSts.equals(SVC_TASK_STS.DEBRIEF_ERROR)) {
                return true;
            }
            // END   2019/10/10 K.Fujimoto [QC#54070,ADD]

            // Add Start 2017/10/15 QC#21648
            // START 2023/09/14 N.Takatsu [QC#61780, MOD]
//            if (!callPartsRequest(msgMap, param)) {
//                return false;
//            }
            callPartsRequest(msgMap, param);
            // END   2023/09/14 N.Takatsu [QC#61780, MOD]
            // Add End 2017/10/15 QC#21648

            // START 2023/11/07 K.Kitachi [QC#61780, ADD]
            // Call Supplemental Update API
            if (isSupplementalUpdate(param)) {
                for (int i = 0; i < param.CsaSupplementalTimeList.getValidCount(); i++) {
                    final NSZC058001 callSupplementalUpdateApi = new NSZC058001();
                    NSZC058001PMsg callSupplementalUpdateParam = createSupplementalUpdateParam(param, i);
                    callSupplementalUpdateApi.execute(callSupplementalUpdateParam, this.onBatchType);
                    if (callSupplementalUpdateParam.xxMsgIdList.getValidCount() != 0) {
                        for (int j = 0; j < callSupplementalUpdateParam.xxMsgIdList.getValidCount(); j++) {
                            msgMap.addXxMsgIdWithPrm(callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgId.getValue(), new String[] {callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue(),
                                    callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_1.getValue(), callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_2.getValue() });
                        }
                        return false;
                    }
                    if (hasValue(callSupplementalUpdateParam.svcSupplTaskNum)) {
                        setValue(param.CsaSupplementalTimeList.no(i).svcSupplTaskNum, callSupplementalUpdateParam.svcSupplTaskNum);
                    }
                }
            }
            // END 2023/11/07 K.Kitachi [QC#61780, ADD]

            // Call Completion API
            final NSZC005001 callCompletionApi = new NSZC005001();
            // START 2016/11/21 CSA QC#15782
            NSZC005001PMsg callCompletionParam = null;
            if (!ZYPConstant.FLG_ON_Y.equals(param.phoneFixFlg.getValue())) {
                callCompletionParam = createCompletionParam(param);
            } else {
                callCompletionParam = createCompletionParamPhoneFix(param);
            }
            // END 2016/11/21 CSA QC#15782

            // START 2022/12/01 K.Kitachi [QC#60882, ADD]
            String svcTaskNum = param.svcTaskNum.getValue();
            S21StopWatch sw = new S21StopWatch();
            S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : execute Start." + " Task#" + svcTaskNum);
            sw.start();
            // END 2022/12/01 K.Kitachi [QC#60882, ADD]
            callCompletionApi.execute(callCompletionParam, this.onBatchType);
            // START 2022/12/01 K.Kitachi [QC#60882, ADD]
            sw.stop();
            S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : execute End." + " Task#" + svcTaskNum);
            S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0050 : execute Elapsed Time [%s]", sw.getElapsedMilliSec()));
            // END 2022/12/01 K.Kitachi [QC#60882, ADD]

            if (callCompletionParam.xxMsgIdList.getValidCount() != 0) {
                for (int i = 0; i < callCompletionParam.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(callCompletionParam.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }

            // START 2019/11/08 K.Fujimoto[QC#54400, ADD]
            String currentFsrVisitSts = getFsrVisitStatus(param);
            if (!hasValue(currentFsrVisitSts)) {
                return false;
            }
            // END   2019/11/08 K.Fujimoto[QC#54400, ADD]
            // add start 2016/04/20 CSA Defect#4467
            // InstallCheck
            // START 2019/11/08 K.Fujimoto[QC#54400, MOD]
            //if (param.CsaInstallCheckList.getValidCount() > 0) {
            if (param.CsaInstallCheckList.getValidCount() > 0 && !currentFsrVisitSts.equals(SVC_TASK_STS.DEBRIEF_ERROR)) {
            // END   2019/11/08 K.Fujimoto[QC#54400, MOD]
                final NSZC005001 callCompletionApiForInstallCheck = new NSZC005001();
                NSZC005001PMsg callCompletionParamForInstallCheck = createCompletionParamForInstallCheck(param);

                // START 2022/12/01 K.Kitachi [QC#60882, ADD]
                sw = new S21StopWatch();
                S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : execute Start." + " Task#" + svcTaskNum);
                sw.start();
                // END 2022/12/01 K.Kitachi [QC#60882, ADD]
                callCompletionApiForInstallCheck.execute(callCompletionParamForInstallCheck, this.onBatchType);
                // START 2022/12/01 K.Kitachi [QC#60882, ADD]
                sw.stop();
                S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0050 : execute End." + " Task#" + svcTaskNum);
                S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0050 : execute Elapsed Time [%s]", sw.getElapsedMilliSec()));
                // END 2022/12/01 K.Kitachi [QC#60882, ADD]

                if (callCompletionParamForInstallCheck.xxMsgIdList.getValidCount() != 0) {
                    for (int i = 0; i < callCompletionParamForInstallCheck.xxMsgIdList.getValidCount(); i++) {
                        msgMap.addXxMsgId(callCompletionParamForInstallCheck.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return false;
                }
            // START 2019/11/08 K.Fujimoto[QC#54400, ADD]
            } else {
                //if there is error by Debrief, don't call Install check mode.
                //Then, regist Install Check List.
                insertFsrInstallCheckList(msgMap, param);
            }
            // END   2019/11/08 K.Fujimoto[QC#54400, ADD]
            // add end 2016/04/20 CSA Defect#4467

        } else if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
            if (!existOtherTask(param)) {
                // Call FSR Update API / 03(cancel)
                if (isFsrUpdate(param)) {
                    final NSZC043001 callFsrUpdateCancelApi = new NSZC043001();
                    NSZC043001PMsg calFsrUpdateCancelApiParam = createFsrUpdateApiCancelParam(param);
                    callFsrUpdateCancelApi.execute(calFsrUpdateCancelApiParam, this.onBatchType);

                    if (calFsrUpdateCancelApiParam.xxMsgIdList.getValidCount() != 0) {
                        for (int i = 0; i < calFsrUpdateCancelApiParam.xxMsgIdList.getValidCount(); i++) {
                            msgMap.addXxMsgId(calFsrUpdateCancelApiParam.xxMsgIdList.no(i).xxMsgId.getValue());
                        }
                        return false;
                    }
                }
            } else {
                // Call Add Task API / 02(cancel)
                final NSZC045001 callAddTaskApi = new NSZC045001();
                NSZC045001PMsg callAddTaskApiParam = createAddTaskApiCancelParam(param);
                callAddTaskApi.execute(callAddTaskApiParam, this.onBatchType);

                if (callAddTaskApiParam.xxMsgIdList.getValidCount() != 0) {
                    for (int i = 0; i < callAddTaskApiParam.xxMsgIdList.getValidCount(); i++) {
                        msgMap.addXxMsgId(callAddTaskApiParam.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    return false;
                }
            }
        }

        // START 2021/09/10 R. Cabral [QC#58979, DEL]
//        // Call Supplemental Update API
//        if (isSupplementalUpdate(param)) {
//            for (int i = 0; i < param.CsaSupplementalTimeList.getValidCount(); i++) {
//                final NSZC058001 callSupplementalUpdateApi = new NSZC058001();
//                NSZC058001PMsg callSupplementalUpdateParam = createSupplementalUpdateParam(param, i);
//                callSupplementalUpdateApi.execute(callSupplementalUpdateParam, this.onBatchType);
//                if (callSupplementalUpdateParam.xxMsgIdList.getValidCount() != 0) {
//                    for (int j = 0; j < callSupplementalUpdateParam.xxMsgIdList.getValidCount(); j++) {
//                        msgMap.addXxMsgIdWithPrm(callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgId.getValue(), new String[] {callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue(),
//                                callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_1.getValue(), callSupplementalUpdateParam.xxMsgIdList.no(j).xxMsgPrmTxt_2.getValue() });
//                    }
//                    return false;
//                }
//                if (hasValue(callSupplementalUpdateParam.svcSupplTaskNum)) {
//                    setValue(param.CsaSupplementalTimeList.no(i).svcSupplTaskNum, callSupplementalUpdateParam.svcSupplTaskNum);
//                }
//            }
//        }
        // END 2021/09/10 R. Cabral [QC#58979, DEL]

        // Call TSS Escalation API
        if (isTtsEscalation(param)) {
            final NSZC073001 callTssEscalationApi = new NSZC073001();
            NSZC073001PMsg callTssEscalationParam = createTssEscalationParam(param);
            callTssEscalationApi.execute(callTssEscalationParam, this.onBatchType);

            if (callTssEscalationParam.xxMsgIdList.getValidCount() != 0) {
                for (int i = 0; i < callTssEscalationParam.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgIdWithPrm(callTssEscalationParam.xxMsgIdList.no(i).xxMsgId.getValue(), new String[] {callTssEscalationParam.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() });
                }
                return false;
            }
        }

        // Receive Field Request API
        if (isFidldRequest(param)) {
            for (int i = 0; i < param.CsaFieldRequestList.getValidCount(); i++) {
                final NSZC075001 callReceiveFieldRequestApi = new NSZC075001();
                NSZC075001PMsg callReceiveFieldRequestParam = createReceiveFieldRequestParam(param);
                callReceiveFieldRequestApi.execute(callReceiveFieldRequestParam, this.onBatchType);

                if (callReceiveFieldRequestParam.xxMsgIdList.getValidCount() != 0) {
                    for (int j = 0; j < callReceiveFieldRequestParam.xxMsgIdList.getValidCount(); j++) {
                        msgMap.addXxMsgIdWithPrm(callReceiveFieldRequestParam.xxMsgIdList.no(i).xxMsgId.getValue(), new String[] {callReceiveFieldRequestParam.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() });
                    }
                    return false;
                }

                // add start 2016/06/17 QC#9677
                String svcTaskNum = callReceiveFieldRequestParam.svcTaskNum.getValue();
                svcTaskNumList.add(svcTaskNum);
                // add end 2016/06/17 QC#9677
            }
        }

        // Mod Start 2017/10/15 QC#21648
        // Parts Search API
        // START 2023/11/07 K.Kitachi [QC#61780, DEL]
//        if (!SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd) || !ZYPCommonFunc.hasValue(param.xxFsrVisitCpltTs)) {
//            if (!callPartsRequest(msgMap, param)) {
//                return false;
//            }
//        }
        // END 2023/11/07 K.Kitachi [QC#61780, DEL]
//        // Parts Search API
//        if (isPartsRequest(param)) {
//            final NPZC117001 callPartsRequestApi = new NPZC117001();
//            // mod start 2016/11/22 CSA QC#16145
//            for (int i = 0; i < param.CsaPartsOrderList.getValidCount(); i++) {
//                List<NPZC117001PMsg> callPartsRequestParamList = createPartsRequestParam(param, i);
//                callPartsRequestApi.execute(callPartsRequestParamList, this.onBatchType);
//
//// del start 2016/11/18 CSA Defect#16060-1
////                int lookupNum = param.PartsLookupList.getValidCount();
//// del end   2016/11/18 CSA Defect#16060-1
//                for (NPZC117001PMsg callPartsRequestParam : callPartsRequestParamList) {
//                    if (callPartsRequestParam.xxMsgIdList.getValidCount() != 0) {
//                        for (int j = 0; j < callPartsRequestParam.xxMsgIdList.getValidCount(); j++) {
//                            msgMap.addXxMsgIdWithPrm(callPartsRequestParam.xxMsgIdList.no(j).xxMsgId.getValue(), new String[] {callPartsRequestParam.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue(),
//                                    callPartsRequestParam.xxMsgIdList.no(j).xxMsgPrmTxt_1.getValue() });
//                        }
//                        // START 2017/02/23 K.Kojima [QC#16301,ADD]
//                        insertClickTechOrdByOrderPartsDenied(param);
//                        // END 2017/02/23 K.Kojima [QC#16301,ADD]
//                        return false;
//                    }
//                    if (hasValue(callPartsRequestParam.prchReqNum)) {
//                        setValue(param.CsaPartsOrderList.no(i).prchReqNum, callPartsRequestParam.prchReqNum);
//                    }
//                    if (hasValue(callPartsRequestParam.prchReqStsNm)) {
//                        setValue(param.CsaPartsOrderList.no(i).prchReqStsNm, callPartsRequestParam.prchReqStsNm);
//                    }
//    // mod start 2016/11/18 CSA Defect#16060-1
////                    for (int k = 0; k < callPartsRequestParam.SearchPartsList.getValidCount(); k++) {
////                        setValue(param.PartsLookupList.no(lookupNum).mdseCd, callPartsRequestParam.SearchPartsList.no(k).mdseCd_02);
////                        String mdseNm = callPartsRequestParam.SearchPartsList.no(k).mdseDescShortTxt_02.getValue();
////                        if (hasValue(mdseNm) && mdseNm.length() > 30) {
////                            mdseNm = mdseNm.substring(0, 30);
////                        }
////                        setValue(param.PartsLookupList.no(lookupNum).mdseNm, mdseNm);//TODO (Change ShortTxt)
////                        lookupNum++;
////                    }
//                    int partsLookupCnt = param.PartsLookupList.getValidCount();
//                    for (int k = 0; k < callPartsRequestParam.SearchPartsList.getValidCount(); k++) {
//                        setValue(param.PartsLookupList.no(partsLookupCnt).mdseCd, callPartsRequestParam.SearchPartsList.no(k).mdseCd_02);
//                        setValue(param.PartsLookupList.no(partsLookupCnt).mdseDescShortTxt, callPartsRequestParam.SearchPartsList.no(k).mdseDescShortTxt_02);
//                        partsLookupCnt++;
////                        setValue(param.PartsLookupList.no(k).mdseCd, callPartsRequestParam.SearchPartsList.no(k).mdseCd_02);
////                        String mdseNm = callPartsRequestParam.SearchPartsList.no(k).mdseDescShortTxt_02.getValue();
////                        if (hasValue(mdseNm)) {
////                            if (mdseNm.length() > 30) {
////                                mdseNm = mdseNm.substring(0, 30);
////                            }
////                            setValue(param.PartsLookupList.no(k).mdseDescShortTxt, mdseNm);
////                        }
//                    }
//                    param.PartsLookupList.setValidCount(partsLookupCnt);
////                    param.PartsLookupList.setValidCount(callPartsRequestParam.SearchPartsList.getValidCount());
//                    // mod end   2016/11/18 CSA Defect#16060-1
//                }
//                // mod end 2016/11/22 CSA QC#16145
//            }
//// del start 2016/11/18 CSA Defect#16060-1
////            param.PartsLookupList.setValidCount(lookupNum);
//// del end   2016/11/18 CSA Defect#16060-1
//        }
        // Mod End 2017/10/15 QC#21648

        // START 2019/05/31 [QC#50574,ADD]
        if (!updateFollowUpPartsCallTaskNum(msgMap, param)) {
            return false;
        }
        // END 2019/05/31 [QC#50574,ADD]

        // add start 2016/11/08 CSA Defect#15780
        // Call FSR Update API For Note
        if (isFsrUpdateForNote(param)) {
            // START 2016/12/15 K.Kojima [QC#16436,DEL]
            // final NSZC043001 callFsrUpdateUpdApi = new NSZC043001();
            // NSZC043001PMsg callFsrUpdateApiParam = createFsrUpdateApiUpdParamForNote(param);
            // callFsrUpdateUpdApi.execute(callFsrUpdateApiParam, this.onBatchType);
            // 
            // if (callFsrUpdateApiParam.xxMsgIdList.getValidCount() != 0) {
            //     for (int i = 0; i < callFsrUpdateApiParam.xxMsgIdList.getValidCount(); i++) {
            //         msgMap.addXxMsgId(callFsrUpdateApiParam.xxMsgIdList.no(i).xxMsgId.getValue());
            //     }
            //     return false;
            // }
            // END 2016/12/15 K.Kojima [QC#16436,DEL]
            // START 2016/12/15 K.Kojima [QC#16436,ADD]
            insertServiceMemo(param);
            // END 2016/12/15 K.Kojima [QC#16436,ADD]
        }
        // add end 2016/11/08 CSA Defect#15780

        // add start 2016/06/17 QC#9677
        // Call Send Task Info to Click Software
        callSendTaskInfoApi(msgMap, param, svcTaskNumList);
        // add end 2016/06/17 QC#9677

        return true;
    }

    private boolean checkArriveStatus(NSZC068001PMsg param) {
        if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd) && hasValue(param.startTs)) {
            return true;
        }
        if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd) && hasValue(param.AssignmentList.no(0).startTs)) {
            return true;
        }
        if (SVC_TASK_STS.ARRIVED.equals(svcTaskStsCd) && hasValue(param.xxFsrVisitArvTs)) {
            return true;
        }
        return false;
    }

    private boolean checkFsrUpdateStatus() {
        if (SVC_TASK_STS.TBO.equals(svcTaskStsCd)) {
            return true;
        }
        if (SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
            return true;
        }
        if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
            return true;
        }
        if (SVC_TASK_STS.IN_ROUTE.equals(svcTaskStsCd)) {
            return true;
        }
        if (SVC_TASK_STS.ARRIVED.equals(svcTaskStsCd)) {
            return true;
        }
        return false;
    }

    private boolean existOtherTask(NSZC068001PMsg apiParam) {

        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("008");
        inMsg.setConditionValue("glblCmpyCd01", apiParam.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", (String) rcvTaskInfoMap.get(FSR_NUM));
        inMsg.setConditionValue("svcTaskNum01", apiParam.svcTaskNum.getValue());
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * isFsrUpdate
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean isFsrUpdate(NSZC068001PMsg param) {

        // START 2016/12/16 K.Kojima [QC#15653,MOD]
        // if (hasValue(param.xtrnlCallPrtyRefTxt)) {
        if (hasValue(param.xtrnlCallPrtyRefTxt) && this.chkChngPriority) {
            // END 2016/12/16 K.Kojima [QC#15653,MOD]
            return true;
        }
        if (hasValue(param.srcZnDescTxt)) {
            return true;
        }
        if (hasValue(param.svcRgDescTxt)) {
            return true;
        }
        if (hasValue(param.svcTeamTxt)) {
            return true;
        }
        if (hasValue(param.svcBrMgrPsnCd)) {
            return true;
        }
        if (hasValue(param.svcTrtyMgrPsnCd)) {
            return true;
        }
        if (hasValue(param.svcTeamMgrPsnCd)) {
            return true;
        }
        if (hasValue(param.svcLttdNum)) {
            return true;
        }
        if (hasValue(param.svcLgtdNum)) {
            return true;
        }
        if (hasValue(param.fsrEventExeTs)) {
            return true;
        }
        if (hasValue(param.startTs)) {
            return true;
        }
        if (hasValue(param.endTs)) {
            return true;
        }
        if (hasValue(param.xxSignaDataDescTxt)) {
            return true;
        }
        if (hasValue(param.svcCmntTxt)) {
            return true;
        }
        if (hasValue(param.svcUnAsgRsnTxt)) {
            return true;
        }
        if (hasValue(param.xxFsrVisitDisptTs)) {
            return true;
        }
        if (hasValue(param.xxSvcTaskRcvTs)) {
            return true;
        }
        // START 2016/12/15 K.Kojima [QC#16436,MOD]
        // if (param.AssignmentList.getValidCount() > 0) {
        if (isFsrUpdateAssignmentList(param) && isFsrUpdateStatus()) {
        // END 2016/12/15 K.Kojima [QC#16436,MOD]
            return true;
        }
        // START 2019/06/19 K.Fujimoto [QC#50880,ADD]
        if (isStsChanged()){
            return true;
        }
        // END   2019/06/19 K.Fujimoto [QC#50880,ADD]
        return false;
    }
    // START 2019/06/19 K.Fujimoto [QC#50880,ADD]
    private boolean isStsChanged() {
        if (svcTaskStsCd == null || rcvTaskInfoMap == null || rcvTaskInfoMap.get(FSR_VISIT_STS_CD) == null){
            return false;
        }
        String beforeSts = (String) rcvTaskInfoMap.get(FSR_VISIT_STS_CD);
        if ((beforeSts.equals(SVC_TASK_STS.SCHEDULED) || beforeSts.equals(SVC_TASK_STS.ASSIGNED)) && svcTaskStsCd.equals(SVC_TASK_STS.TBO)) {
            return true;
        }
        return false;
    }
    // END   2019/06/19 K.Fujimoto [QC#50880,ADD]
    // START 2016/12/15 K.Kojima [QC#16436,ADD]
    private boolean isFsrUpdateAssignmentList(NSZC068001PMsg param) {
        if (param.AssignmentList.getValidCount() == 0) {
            return false;
        }
        for (int i = 0; i < param.AssignmentList.getValidCount(); i++) {
            NSZC068001_AssignmentListPMsg apMsg = param.AssignmentList.no(i);
            if (hasValue(apMsg.techNm) || hasValue(apMsg.techCd) || hasValue(apMsg.endTs) || hasValue(apMsg.startTs) || hasValue(apMsg.svcAsgTechCd) || hasValue(apMsg.svcCmntTxt) || hasValue(apMsg.svcTrvlTmNum)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFsrUpdateStatus() {
        if (SVC_TASK_STS.IN_ROUTE.equals(this.svcTaskStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.ARRIVED.equals(this.svcTaskStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.COMPLETED.equals(this.svcTaskStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.CLOSED.equals(this.svcTaskStsCd)) {
            return false;
        }
        if (SVC_TASK_STS.DEBRIEF_ERROR.equals(this.svcTaskStsCd)) {
            return false;
        }
        return true;
    }

    // END 2016/12/15 K.Kojima [QC#16436,ADD]

    /**
     * <pre>
     * isTecArrivedUpdate
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean isTecArrivedUpdate(NSZC068001PMsg param) {

        if (hasValue(param.startTs)) {
            return true;
        }
        if (hasValue(param.xxFsrVisitArvTs)) {
            return true;
        }
        if (hasValue(param.svcActlLttdNum)) {
            return true;
        }
        if (hasValue(param.svcActlLgtdNum)) {
            return true;
        }
        if (hasValue(param.svcActlLocGapDstNum)) {
            return true;
        }
        if (param.AssignmentList.getValidCount() > 0 && hasValue(param.AssignmentList.no(0).startTs)) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * isSupplementalUpdate
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean isSupplementalUpdate(NSZC068001PMsg param) {

        if (param.CsaSupplementalTimeList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * isTtsEscalation
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean isTtsEscalation(NSZC068001PMsg param) {

        if (param.CsaTtsEscalationList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * isFidldRequest
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean isFidldRequest(NSZC068001PMsg param) {

        if (param.CsaFieldRequestList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * isPartsRequest
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private boolean isPartsRequest(NSZC068001PMsg param) {

        if (param.CsaPartsOrderList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Create Parameter for FSR Update API / Update.
     * @param param NSZC068001PMsg
     * @return NSZC043001PMsg
     */
    private NSZC043001PMsg createFsrUpdateApiUpdParam(NSZC068001PMsg param) {
        NSZC043001PMsg fsrUpdateApiPMsg = new NSZC043001PMsg();

        setValue(fsrUpdateApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(fsrUpdateApiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        setValue(fsrUpdateApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        // mod start 2016/05/24 CSA Defect#8809
        setValue(fsrUpdateApiPMsg.slsDt, param.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        // mod start 2016/07/29 CSA Defect#10483
        if (hasValue(apiTechCd)) {
            setValue(fsrUpdateApiPMsg.userId, apiTechCd);
        } else {
            setValue(fsrUpdateApiPMsg.userId, getUserId(param.glblCmpyCd.getValue(), param.svcTaskNum.getValue()));
        }
        // mod end 2016/07/29 CSA Defect#10483
        setValue(fsrUpdateApiPMsg.svcTaskStsCd, svcTaskStsCd);

        if (chkChngPriority) {
            setValue(fsrUpdateApiPMsg.origSvcCallPrtyCd, (String) rcvTaskInfoMap.get(SVC_CALL_PRTY_CD));
            setValue(fsrUpdateApiPMsg.svcCallPrtyChngDt, sysDate);
        }
        setValue(fsrUpdateApiPMsg.svcMachMstrPk, (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK));
        // setValue(fsrUpdateApiPMsg.custMachCtrlNum, (String) rcvTaskInfoMap.get(CUST_MACH_CTRL_NUM));
        // setValue(fsrUpdateApiPMsg.serNum, (String) rcvTaskInfoMap.get(SER_NUM));
// START  02/19/2016 T.Iwamoto [QC#4108, ADD]
        setValue(fsrUpdateApiPMsg.custTelNum, param.custTelNum);
     // START  03/02/2016 T.Iwamoto [QC#4766, DEL]
//        if (hasValue(param.xxCtacNm)) {
//            setValue(fsrUpdateApiPMsg.svcCustAttnTxt, param.xxCtacNm);
//        } else {
//            setValue(fsrUpdateApiPMsg.svcCustAttnTxt, param.dsAcctNm);
//        }
     // END  03/02/2016 T.Iwamoto [QC#4766, DEL]
// END  02/19/2016 T.Iwamoto [QC#4108, ADD]
        if (SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd)) {
            setValue(fsrUpdateApiPMsg.svcTaskSchdDt, sysDate);
            setValue(fsrUpdateApiPMsg.svcTaskSchdTm, sysTime);
        }
        setValue(fsrUpdateApiPMsg.svcCallRqstOwnrTocCd, param.xxSvcResrcMgrPsnCd);
        // add start 2016/07/01 CSA Defect#9677
        setValue(fsrUpdateApiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#9677
        // START 2017/12/14 M.Kidokoro [QC#22223,ADD]
        setCrCardCustRefNum(fsrUpdateApiPMsg);
        // END 2017/12/14 M.Kidokoro [QC#22223,ADD]

        NSZC043001_taskListPMsg taskListPMsg = fsrUpdateApiPMsg.taskList.no(0);
        setValue(taskListPMsg.svcTaskNum, param.svcTaskNum);
        setValue(taskListPMsg.svcCallPrtyCd, svcCallPrtyCd);
        setValue(taskListPMsg.techCd, apiTechCd);
        setValue(taskListPMsg.schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
// mod start 2016/03/03 CSA Defect#4766
        if (SVC_TASK_STS.SCHEDULED.equals(svcTaskStsCd) || SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
            if (hasValue(param.startTs) && param.startTs.getValue().length() >= TS_LEN) {
                // START  02/09/2016 Y.Takeno [QC#3727, MOD]
                SvcTimeZoneInfo tzStartTs = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.startTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                if (tzStartTs != null) {
                    String cnvtStartTs = tzStartTs.getDateTime();
                    setValue(taskListPMsg.techSchdFromDt, S21StringUtil.subStringByLength(cnvtStartTs, DATE_START_POS, DATE_END_POS));
                    setValue(taskListPMsg.techSchdFromTm, S21StringUtil.subStringByLength(cnvtStartTs, TIME_START_POS, TIME_END_POS));
                }
                // END    02/09/2016 Y.Takeno [QC#3727, MOD]
            } else if (param.AssignmentList.getValidCount() > 0 && hasValue(param.AssignmentList.no(0).startTs)) {
                // START  02/09/2016 Y.Takeno [QC#3727, MOD]
                SvcTimeZoneInfo tzStartTs = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.AssignmentList.no(0).startTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                if (tzStartTs != null) {
                    String cnvtStartTs = tzStartTs.getDateTime();
                    setValue(taskListPMsg.techSchdFromDt, S21StringUtil.subStringByLength(cnvtStartTs, DATE_START_POS, DATE_END_POS));
                    setValue(taskListPMsg.techSchdFromTm, S21StringUtil.subStringByLength(cnvtStartTs, TIME_START_POS, TIME_END_POS));
                }
                // END    02/09/2016 Y.Takeno [QC#3727, MOD]
            }
            if (hasValue(param.endTs) && param.endTs.getValue().length() >= TS_LEN) {
                // START  02/09/2016 Y.Takeno [QC#3727, MOD]
                SvcTimeZoneInfo tzEndTs = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.endTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                if (tzEndTs != null) {
                    String cnvtEndTs = tzEndTs.getDateTime();
                    setValue(taskListPMsg.techSchdToDt, S21StringUtil.subStringByLength(cnvtEndTs, DATE_START_POS, DATE_END_POS));
                    setValue(taskListPMsg.techSchdToTm, S21StringUtil.subStringByLength(cnvtEndTs, TIME_START_POS, TIME_END_POS));
                }
                // END    02/09/2016 Y.Takeno [QC#3727, MOD]
            } else if (param.AssignmentList.getValidCount() > 0 && hasValue(param.AssignmentList.no(0).endTs)) {
                // START  02/09/2016 Y.Takeno [QC#3727, MOD]
                SvcTimeZoneInfo tzEndTs = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.AssignmentList.no(0).endTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                if (tzEndTs != null) {
                    String cnvtEndTs = tzEndTs.getDateTime();
                    setValue(taskListPMsg.techSchdToDt, S21StringUtil.subStringByLength(cnvtEndTs, DATE_START_POS, DATE_END_POS));
                    setValue(taskListPMsg.techSchdToTm, S21StringUtil.subStringByLength(cnvtEndTs, TIME_START_POS, TIME_END_POS));
                }
                // END    02/09/2016 Y.Takeno [QC#3727, MOD]
            }
        }
// mod end 2016/03/03 CSA Defect#4766
        if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
            setValue(taskListPMsg.techAcptDt, sysDate);
            setValue(taskListPMsg.techAcptTm, sysTime);
        }
        if (hasValue(param.xxFsrVisitDisptTs) && param.xxFsrVisitDisptTs.getValue().length() >= TS_LEN) {
// START  02/09/2016 Y.Takeno [QC#3727, MOD]
            SvcTimeZoneInfo tzFsrVisitDisptTs = NSXC001001SvcTimeZone.convertTime(
                    NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitDisptTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitDisptTs != null) {
                String cnvtFsrVisitDisptTs = tzFsrVisitDisptTs.getDateTime();
                setValue(taskListPMsg.fsrVisitDisptDt, S21StringUtil.subStringByLength(cnvtFsrVisitDisptTs, DATE_START_POS, DATE_END_POS));
                setValue(taskListPMsg.fsrVisitDisptTm, S21StringUtil.subStringByLength(cnvtFsrVisitDisptTs, TIME_START_POS, TIME_END_POS));
            }
// END    02/09/2016 Y.Takeno [QC#3727, MOD]
        }
// START 02/19/2016 T.Iwamoto [QC#4108, ADD]
        String svcRgCd = getSvcRgCd(param.glblCmpyCd.getValue(), param.svcRgDescTxt.getValue());
        setValue(taskListPMsg.svcRgCd, svcRgCd);
// END  02/19/2016 T.Iwamoto [QC#4108, ADD]
        // mod start 2018/07/10 QC#27089
        //if (hasValue(param.trtyOrgNm) && param.trtyOrgNm.getValue().length() >= BR_LEN) {
        //    setValue(taskListPMsg.svcBrCd, S21StringUtil.subStringByLength(param.trtyOrgNm.getValue(), 0, BR_LEN));
        if (hasValue(param.svcTrtyDescTxt) && param.svcTrtyDescTxt.getValue().length() >= BR_LEN) {
            setValue(taskListPMsg.svcBrCd, S21StringUtil.subStringByLength(param.svcTrtyDescTxt.getValue(), 0, BR_LEN));
        }
        // mod end 2018/07/10 QC#27089
        // mod start 2016/03/03 CSA Defect#4766
        setValue(taskListPMsg.svcTeamTxt, param.svcTeamTxt);
        // mod end 2016/03/03 CSA Defect#4766
        setValue(taskListPMsg.svcBrMgrPsnCd, param.svcBrMgrPsnCd);
        setValue(taskListPMsg.svcTrtyMgrPsnCd, param.svcTrtyMgrPsnCd);
        setValue(taskListPMsg.svcTeamMgrPsnCd, param.svcTeamMgrPsnCd);
        setValue(taskListPMsg.svcZnCd, getSvcMblS21ValTxt(param.glblCmpyCd.getValue(), SVC_ZN_CD, param.srcZnDescTxt_T.getValue()));
        setValue(taskListPMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        setValue(taskListPMsg.svcLttdNum, param.svcLttdNum);
        setValue(taskListPMsg.svcLgtdNum, param.svcLgtdNum);
        // Add Start 2018/08/03 QC#27605
        setValue(taskListPMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
        setValue(taskListPMsg.erlStartTs, (String) rcvTaskInfoMap.get(ERL_START_TS));
        setValue(taskListPMsg.lateStartTs, (String) rcvTaskInfoMap.get(LATE_START_TS));
        if (hasValue(param.erlStartTs)) {
            SvcTimeZoneInfo erlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.erlStartTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (erlStartTs != null) {
                setValue(taskListPMsg.erlStartTs, erlStartTs.getDateTime());
            }
        }
        if (hasValue(param.lateStartTs)) {
            SvcTimeZoneInfo lateStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.lateStartTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (lateStartTs != null) {
                setValue(taskListPMsg.lateStartTs, lateStartTs.getDateTime());
            }
        }
        // Add End 2018/08/03 QC#27605

        setValue(taskListPMsg.svcUnAsgRsnTxt, param.svcUnAsgRsnTxt);
        // START 2016/09/14 T.Tomita [QC#7202, ADD]
        // START 2019/01/21 W.Honda [QC#28650, ADD]
        setValue(taskListPMsg.techAcptFlg, param.techAcptFlg);
        // END 2019/01/21 W.Honda [QC#28650, ADD]
        fsrUpdateApiPMsg.taskList.setValidCount(1);
        if (!hasValue(param.svcCmntTxt) && !(param.AssignmentList.getValidCount() > 0 && hasValue(param.AssignmentList.no(0).svcCmntTxt))) {
            return fsrUpdateApiPMsg;
        }
        // END 2016/09/14 T.Tomita [QC#7202, ADD]
        NSZC043001_svcMemoListPMsg svcMemoListPMsg = fsrUpdateApiPMsg.svcMemoList.no(0);
        setValue(svcMemoListPMsg.svcMemoTpCd, SVC_MEMO_TP.TECHNICIAN_PAGE_NOTES);
        if (hasValue(param.svcCmntTxt)) {
            setValue(svcMemoListPMsg.svcCmntTxt, param.svcCmntTxt);
        } else if (param.AssignmentList.getValidCount() > 0) {
            setValue(svcMemoListPMsg.svcCmntTxt, param.AssignmentList.no(0).svcCmntTxt);
        }

        // START 2016/09/14 T.Tomita [QC#7202, DEL]
//        fsrUpdateApiPMsg.taskList.setValidCount(1);
        // END 2016/09/14 T.Tomita [QC#7202, DEL]
        fsrUpdateApiPMsg.svcMemoList.setValidCount(1);

        return fsrUpdateApiPMsg;
    }

    /**
     * Create Parameter for FSR Update API / Cancel.
     * @param param NSZC068001PMsg
     * @return NSZC043001PMsg
     */
    private NSZC043001PMsg createFsrUpdateApiCancelParam(NSZC068001PMsg param) {
        NSZC043001PMsg fsrUpdateApiPMsg = new NSZC043001PMsg();

        setValue(fsrUpdateApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(fsrUpdateApiPMsg.xxModeCd, NSZC043001Constant.MODE_CANCEL_FSR);
        setValue(fsrUpdateApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        // mod start 2016/05/24 CSA Defect#8809
        setValue(fsrUpdateApiPMsg.slsDt, param.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        setValue(fsrUpdateApiPMsg.userId, apiTechCd);
        // add start 2016/07/01 CSA Defect#9677
        setValue(fsrUpdateApiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#9677
        // START 2017/12/14 M.Kidokoro [QC#22223,ADD]
        setCrCardCustRefNum(fsrUpdateApiPMsg);
        // END 2017/12/14 M.Kidokoro [QC#22223,ADD]

        NSZC043001_taskListPMsg taskListPMsg = fsrUpdateApiPMsg.taskList.no(0);
        setValue(taskListPMsg.svcTaskNum, param.svcTaskNum);
        setValue(taskListPMsg.schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        setValue(taskListPMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);

        fsrUpdateApiPMsg.taskList.setValidCount(1);

        return fsrUpdateApiPMsg;
    }

    /**
     * Create Parameter for Add Task API / Cancel.
     * @param param NSZC068001PMsg
     * @return NSZC045001PMsg
     */
    private NSZC045001PMsg createAddTaskApiCancelParam(NSZC068001PMsg param) {
        NSZC045001PMsg addTaskApiPMsg = new NSZC045001PMsg();

        setValue(addTaskApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(addTaskApiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(addTaskApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(addTaskApiPMsg.userId, apiTechCd);
        // add start 2016/07/01 CSA Defect#9677
        setValue(addTaskApiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#9677
        // add start 2016/09/09 CSA Defect#14206
        setValue(addTaskApiPMsg.slsDt, param.slsDt);
        // add end 2016/09/09 CSA Defect#14206

        NSZC045001_xxSvcTaskListPMsg taskListPMsg = addTaskApiPMsg.xxSvcTaskList.no(0);
        setValue(taskListPMsg.svcTaskNum, param.svcTaskNum);
        setValue(taskListPMsg.schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        setValue(taskListPMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);

        addTaskApiPMsg.xxSvcTaskList.setValidCount(1);

        return addTaskApiPMsg;
    }

    /**
     * Create Parameter for Technician Arrived Update API
     * @param param NSZC068001PMsg
     * @param processMode String
     * @return NSZC012001PMsg
     */
    private NSZC012001PMsg createTecArrivedUpdateParam(NSZC068001PMsg param) {
        NSZC012001PMsg fsrUpdateApiPMsg = new NSZC012001PMsg();

        setValue(fsrUpdateApiPMsg.glblCmpyCd, param.glblCmpyCd);
        if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
            setValue(fsrUpdateApiPMsg.xxModeCd, NSZC012001Constant.PROCESS_MODE_ETA);
        } else if (SVC_TASK_STS.ARRIVED.equals(svcTaskStsCd)) {
            setValue(fsrUpdateApiPMsg.xxModeCd, NSZC012001Constant.PROCESS_MODE_ARRIVED);
        }
        setValue(fsrUpdateApiPMsg.slsDt, param.slsDt);

        NSZC012001_FSRVisitListPMsg fsrVisitList = fsrUpdateApiPMsg.FSRVisitList.no(0);
        setValue(fsrVisitList.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(fsrVisitList.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(fsrVisitList.svcTaskNum, param.svcTaskNum);
        // mod start 2016/03/03 CSA Defect#4766
        if (SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd)) {
            if (hasValue(param.startTs) && param.startTs.getValue().length() >= TS_LEN) {
                // START  02/09/2016 Y.Takeno [QC#3727, MOD]
                SvcTimeZoneInfo tzStartTs = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.startTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                if (tzStartTs != null) {
                    String cnvtStartTs = tzStartTs.getDateTime();
                    setValue(fsrVisitList.fsrVisitEtaDt, S21StringUtil.subStringByLength(cnvtStartTs, DATE_START_POS, DATE_END_POS));
                    setValue(fsrVisitList.fsrVisitEtaTm, S21StringUtil.subStringByLength(cnvtStartTs, TIME_START_POS, TIME_END_POS));
                }
                // END    02/09/2016 Y.Takeno [QC#3727, MOD]
            } else if (param.AssignmentList.getValidCount() > 0 && hasValue(param.AssignmentList.no(0).startTs) && param.AssignmentList.no(0).startTs.getValue().length() >= TS_LEN) {
            // START  02/09/2016 Y.Takeno [QC#3727, MOD]
                SvcTimeZoneInfo tzStartTs = NSXC001001SvcTimeZone.convertTime(
                        NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.AssignmentList.no(0).startTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                if (tzStartTs != null) {
                    String cnvtStartTs = tzStartTs.getDateTime();
                    setValue(fsrVisitList.fsrVisitEtaDt, S21StringUtil.subStringByLength(cnvtStartTs, DATE_START_POS, DATE_END_POS));
                    setValue(fsrVisitList.fsrVisitEtaTm, S21StringUtil.subStringByLength(cnvtStartTs, TIME_START_POS, TIME_END_POS));
                }
                // END    02/09/2016 Y.Takeno [QC#3727, MOD]
            }
        }
        // mod end 2016/03/03 CSA Defect#4766
        if (hasValue(param.xxFsrVisitArvTs) && param.xxFsrVisitArvTs.getValue().length() >= TS_LEN) {
            // START  02/17/2016 T.Iwamoto [QC#3727, MOD]
            SvcTimeZoneInfo tzFsrVisitArvTs = NSXC001001SvcTimeZone.convertTime(
                    NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitArvTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitArvTs != null) {
                String cnvtFsrVisitArvTs = tzFsrVisitArvTs.getDateTime();
                setValue(fsrVisitList.fsrVisitArvDt, S21StringUtil.subStringByLength(cnvtFsrVisitArvTs, DATE_START_POS, DATE_END_POS));
                setValue(fsrVisitList.fsrVisitArvTm, S21StringUtil.subStringByLength(cnvtFsrVisitArvTs, TIME_START_POS, TIME_END_POS));
            }
            // END  02/17/2016 T.Iwamoto [QC#3727, MOD]
        }

        setValue(fsrVisitList.userId, apiTechCd);
        setValue(fsrVisitList.svcActlLttdNum, param.svcActlLttdNum);
        setValue(fsrVisitList.svcActlLgtdNum, param.svcActlLgtdNum);
        setValue(fsrVisitList.svcActlLocGapDstNum, param.svcActlLocGapDstNum);
        // add start 2016/07/01 CSA Defect#11185
        setValue(fsrVisitList.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#11185

        fsrUpdateApiPMsg.FSRVisitList.setValidCount(1);

        return fsrUpdateApiPMsg;
    }

    /**
     * Create Parameter for Completion API
     * @param param NSZC068001PMsg
     * @return NSZC005001PMsg
     */
    private NSZC005001PMsg createCompletionParam(NSZC068001PMsg param) {
        NSZC005001PMsg completionApiPMsg = new NSZC005001PMsg();
        String glblCmpyCd = (String) param.glblCmpyCd.getValue();
        // Visit Header
        setValue(completionApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(completionApiPMsg.slsDt, param.slsDt);
        setValue(completionApiPMsg.userId, apiTechCd);
        setValue(completionApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(completionApiPMsg.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(completionApiPMsg.svcMachMstrPk, (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK));
        setValue(completionApiPMsg.fsrVisitArvDt, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT));
        setValue(completionApiPMsg.fsrVisitArvTm, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_TM));
        SvcTimeZoneInfo tzFsrVisitCpltTs = null;
        String cnvtFsrVisitCpltTs = null;
        if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
            // START 02/09/2016 Y.Takeno [QC#3727, MOD]
            tzFsrVisitCpltTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitCpltTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitCpltTs != null) {
                cnvtFsrVisitCpltTs = tzFsrVisitCpltTs.getDateTime();
                setValue(completionApiPMsg.fsrVisitCpltDt, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, DATE_START_POS, DATE_END_POS));
                setValue(completionApiPMsg.fsrVisitCpltTm, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, TIME_START_POS, TIME_END_POS));
            }
            // END 02/09/2016 Y.Takeno [QC#3727, MOD]
        }
        // mod start 2016/11/30 CSA Defect#16145
        // START 02/22/2016 T.Iwamoto [QC#2563, MOD]
        String fsrVisitDisptTs = null;
        String fsrVisitArvTs = null;
        if (hasValue((String) rcvTaskInfoMap.get(FSR_VISIT_DISPT_DT))) {
            StringBuilder disptTsBuilder = new StringBuilder();
            disptTsBuilder.append((String) rcvTaskInfoMap.get(FSR_VISIT_DISPT_DT));
            disptTsBuilder.append((String) rcvTaskInfoMap.get(FSR_VISIT_DISPT_TM));
            fsrVisitDisptTs = disptTsBuilder.toString();
        }
        if (hasValue((String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT))) {
            StringBuilder arvTsBuilder = new StringBuilder();
            arvTsBuilder.append((String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT));
            arvTsBuilder.append((String) rcvTaskInfoMap.get(FSR_VISIT_ARV_TM));
            fsrVisitArvTs = arvTsBuilder.toString();

        }
        if (hasValue(fsrVisitDisptTs) && hasValue(fsrVisitArvTs)) {
            BigDecimal svcTrvlTmNum = BigDecimal.valueOf(getTimeDiff(fsrVisitDisptTs, fsrVisitArvTs));
            setValue(completionApiPMsg.svcTrvlTmNum, svcTrvlTmNum);
        } else {
            setValue(completionApiPMsg.svcTrvlTmNum, BigDecimal.ZERO);
        }
        // END 02/22/2016 T.Iwamoto [QC#2563, MOD]
        // mod end 2016/11/30 CSA Defect#16145

        setValue(completionApiPMsg.invCcyCd, (String) rcvTaskInfoMap.get(INV_CCY_CD));
        setValue(completionApiPMsg.ccyExchRate, (BigDecimal) rcvTaskInfoMap.get(CCY_EXCH_RATE));
        setValue(completionApiPMsg.svcBillTpCd, (String) rcvTaskInfoMap.get(SVC_BILL_TP_CD));
        setValue(completionApiPMsg.pmtTermCashDiscCd, (String) rcvTaskInfoMap.get(PMT_TERM_CASH_DISC_CD));
        setValue(completionApiPMsg.svcCpltLttdNum, param.svcCpltLttdNum);
        setValue(completionApiPMsg.svcCpltLgtdNum, param.svcCpltLgtdNum);
        setValue(completionApiPMsg.svcCpltLocGapDstNum, param.svcCpltLocGapDstNum);
        // mod start 2016/04/01 CSA Defect#6020
        setValue(completionApiPMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/04/01 CSA Defect#6020

        // START 2016/11/10 [QC#15732, ADD]
        setValue(completionApiPMsg.iwrStsCd, getIwrStsCd(param.glblCmpyCd.getValue(), param.xtrnlIwrStsRefTxt.getValue()));
        // END 2016/11/10 [QC#15732, ADD]

        // START 2022/04/27 K.Kitachi [QC#59895, ADD]
        setValue(completionApiPMsg.arvMachDownTpCd, getArvMachDownTpCd(param.arvMachDownTpCd.getValue()));
        // END 2022/04/27 K.Kitachi [QC#59895, ADD]

        // Visit for task
        NSZC005001_xxVisitTaskListPMsg visitTaskList = completionApiPMsg.xxVisitTaskList.no(0);
        setValue(visitTaskList.svcTaskNum, param.svcTaskNum);
        // mod start 2016/03/31 CSA Defect#6270
        if (hasValue(param.xtrnlPblmTpRefTxt)) {
            setValue(visitTaskList.svcPblmTpCd, getSvcPblmTpCd(param.glblCmpyCd.getValue(), param.xtrnlPblmTpRefTxt.getValue()));
        } else {
            setValue(visitTaskList.svcPblmTpCd, (String) rcvTaskInfoMap.get(SVC_PBLM_TP_CD));
        }
        // mod end 2016/03/31 CSA Defect#6270
        // mod start 2016/01/07 CSA Defect#2558
        setValue(visitTaskList.svcPblmLocTpCd, getSvcPblmLocTpCd(param.glblCmpyCd.getValue(), param.xtrnlPblmLocTpRefTxt.getValue()));
        setValue(visitTaskList.svcPblmRsnTpCd, getSvcPblmRsnTpCd(param.glblCmpyCd.getValue(), param.xtrnlPblmRsnTpRefTxt.getValue()));
        setValue(visitTaskList.svcPblmCrctTpCd, getSvcPblmCrctTpCd(param.glblCmpyCd.getValue(), param.xtrnlPblmCrctTpRefTxt.getValue()));
        // mod end 2016/01/07 CSA Defect#2558
        BigDecimal svcLborTmNum = BigDecimal.ZERO;
        // mod start 2016/03/03 CSA Defect#4766
        if (hasValue(param.xxFsrVisitCpltTs)) {
            svcLborTmNum = BigDecimal.valueOf(getTimeDiff(fsrVisitArvTs, cnvtFsrVisitCpltTs));
        }
        // mod end 2016/03/03 CSA Defect#4766
        setValue(visitTaskList.svcLborTmNum, svcLborTmNum);
        setValue(visitTaskList.xxVisitTaskCpltFlg, ZYPConstant.FLG_ON_Y);
        // Add Start 2017/10/25 QC#22061
        setValue(visitTaskList.phoneFixFlg, param.phoneFixFlg);
        // Add End 2017/10/25 QC#22061

        // START 2018/03/22 M.Naito [QC#18713, ADD]
        // Upload signature data to S21 file system.
        if (hasValue(param.xxSignaDataDescTxt)) {
            String signaFilePathTxt = uploadSignaFileToFS(param.xxSignaDataDescTxt.getValue(), param.svcTaskNum.getValue());
            setValue(visitTaskList.signaFilePathTxt, signaFilePathTxt);
        }
        // END 2018/03/22 M.Naito [QC#18713, ADD]
        // START 2018/06/01 M.Naito [QC#26272, ADD]
        if (hasValue(param.custEmlAddr)) {
            setValue(visitTaskList.sendRptEmlAddr, param.custEmlAddr);
        }
        // END 2018/06/01 M.Naito [QC#26272, ADD]

        completionApiPMsg.xxVisitTaskList.setValidCount(1);

        // Parts Usage
        if (param.CsaPartsUsedList.getValidCount() > 0) {
            for (int i = 0; i < param.CsaPartsUsedList.getValidCount(); i++) {
                NSZC005001_xxFsrUsgListPMsg partsUsageList = completionApiPMsg.xxFsrUsgList.no(i);
                setValue(partsUsageList.svcTaskNum, param.svcTaskNum);
                setValue(partsUsageList.dsSvcCallTpCd, (String) rcvTaskInfoMap.get(DS_SVC_CALL_TP_CD));
                setValue(partsUsageList.prtUsedByTechLocCd, apiTechCd.concat(TEC_LOC_SWH));
                setValue(partsUsageList.prtUsedByTechCd, apiTechCd);
                setValue(partsUsageList.mdseCd, param.CsaPartsUsedList.no(i).mdseCd);
                setValue(partsUsageList.mdseNm, param.CsaPartsUsedList.no(i).mdseDescLongTxt);
                setValue(partsUsageList.svcPrtQty, param.CsaPartsUsedList.no(i).svcPrtQty);
                String uomCd = getUomCd(glblCmpyCd, (String) param.CsaPartsUsedList.no(i).mdseCd.getValue());
                setValue(partsUsageList.uomCd, uomCd);
                if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
                    setValue(partsUsageList.svcExecDt, S21StringUtil.subStringByLength(param.xxFsrVisitCpltTs.getValue(), DATE_START_POS, DATE_END_POS));
                }
                setValue(partsUsageList.fsrUsgProcFlg, ZYPConstant.FLG_OFF_N);
                setValue(partsUsageList.svcInvtyExFlg, ZYPConstant.FLG_OFF_N);
                setValue(partsUsageList.svcModPlnId, param.CsaPartsUsedList.no(i).svcModPlnId);
            }
            completionApiPMsg.xxFsrUsgList.setValidCount(param.CsaPartsUsedList.getValidCount());

        }

        // Meter Reading
        if (param.CsaMeterList.getValidCount() > 0) {
            // START 2020/07/29 K.Kitachi [QC#57410, ADD]
            setValue(completionApiPMsg.arvMtrVisTpCd, param.CsaMeterList.no(0).arvMtrVisTpCd);
            setValue(completionApiPMsg.cpltMtrVisTpCd, param.CsaMeterList.no(0).cpltMtrVisTpCd);
            // END 2020/07/29 K.Kitachi [QC#57410, ADD]
            // mod start 2016/07/06 CSA Defect#11284
            // InMeter
            int idx = 0;
            for (int i = 0; i < param.CsaMeterList.getValidCount(); i++) {
                NSZC005001_xxMtrReadListPMsg meterReadingList = completionApiPMsg.xxMtrReadList.no(idx);
                MTR_LBTMsgArray meterArray = getMeterLabel(glblCmpyCd, (String) param.CsaMeterList.no(i).mtrLbNm.getValue());
                String mtrLbCd = null;
                if (meterArray.getValidCount() > 0) {
                    mtrLbCd = meterArray.no(0).mtrLbCd.getValue();
                }
                setValue(meterReadingList.mtrLbNm, param.CsaMeterList.no(i).mtrLbNm);
                setValue(meterReadingList.mdseCd, (String) rcvTaskInfoMap.get(MDSE_CD));
                setValue(meterReadingList.mtrDt, sysDate);
                setValue(meterReadingList.mtrCnt, param.CsaMeterList.no(i).startMtrCnt);
                setValue(meterReadingList.mtrTestCnt, BigDecimal.ZERO);
                // mod start 2016/09/02 CSA QC#11064
                if (hasValue(param.CsaMeterList.no(i).startMtrReadDt)) {
                    setValue(meterReadingList.mtrReadDt, param.CsaMeterList.no(i).startMtrReadDt);
                // START 2020/06/29 K.Kitachi [QC#57231, ADD]
                } else if (hasValue(completionApiPMsg.fsrVisitArvDt)) {
                    setValue(meterReadingList.mtrReadDt, completionApiPMsg.fsrVisitArvDt);
                // END 2020/06/29 K.Kitachi [QC#57231, ADD]
                } else {
                    setValue(meterReadingList.mtrReadDt, param.slsDt);
                }
                // mod end 2016/09/02 CSA QC#11064
                // mod start 2017/09/06 QC#15134
                //setValue(meterReadingList.mtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                setValue(meterReadingList.dsMtrReadTpCd, getDsMtrReadTpCd(param.CsaMeterList.no(i).dsMtrReadTpNm_IN.getValue()));
                // mod end 2017/09/06 QC#15134
                setValue(meterReadingList.mtrReadSrcTpCd, MTR_READ_SRC_TP.SERVICE);

                BigDecimal svcMachMstrPk = (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK);
                DS_CONTRTMsg dsContrDtlTMsg = NSXC001001GetContr.getContrByMachMstrPkInclWarranty(glblCmpyCd, svcMachMstrPk, param.slsDt.getValue());
                if (dsContrDtlTMsg != null) {
                    setValue(meterReadingList.dsContrNum, dsContrDtlTMsg.dsContrNum);
                }
                setValue(meterReadingList.mtrLbCd, mtrLbCd);
                setValue(meterReadingList.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.SERVICE_READS);
                // START 2020/07/29 K.Kitachi [QC#57410, ADD]
                setValue(meterReadingList.dsTestCopyClsCd, DS_TEST_COPY_CLS.TEST_COPY_IN);
                // END 2020/07/29 K.Kitachi [QC#57410, ADD]
                idx++;
            }
            // OutMeter
            for (int i = 0; i < param.CsaMeterList.getValidCount(); i++) {
                NSZC005001_xxMtrReadListPMsg meterReadingList = completionApiPMsg.xxMtrReadList.no(idx);
                MTR_LBTMsgArray meterArray = getMeterLabel(glblCmpyCd, (String) param.CsaMeterList.no(i).mtrLbNm.getValue());
                String mtrLbCd = null;
                if (meterArray.getValidCount() > 0) {
                    mtrLbCd = meterArray.no(0).mtrLbCd.getValue();
                }
                setValue(meterReadingList.mtrLbNm, param.CsaMeterList.no(i).mtrLbNm);
                setValue(meterReadingList.mdseCd, (String) rcvTaskInfoMap.get(MDSE_CD));
                setValue(meterReadingList.mtrDt, sysDate);
                setValue(meterReadingList.mtrCnt, param.CsaMeterList.no(i).endMtrCnt);

                int endMtrCnt = 0;
                int mtrCnt = 0;
                if (hasValue(param.CsaMeterList.no(i).endMtrCnt)) {
                    endMtrCnt = param.CsaMeterList.no(i).endMtrCnt.getValueInt();
                }
                if (hasValue(param.CsaMeterList.no(i).startMtrCnt)) {
                    mtrCnt = param.CsaMeterList.no(i).startMtrCnt.getValueInt();
                }
                setValue(meterReadingList.mtrTestCnt, BigDecimal.valueOf(endMtrCnt - mtrCnt));
                // mod start 2016/09/02 CSA QC#11064
                if (hasValue(param.CsaMeterList.no(i).endMtrReadDt)) {
                    setValue(meterReadingList.mtrReadDt, param.CsaMeterList.no(i).endMtrReadDt);
                // START 2020/06/29 K.Kitachi [QC#57231, ADD]
                } else if (hasValue(completionApiPMsg.fsrVisitCpltDt)) {
                    setValue(meterReadingList.mtrReadDt, completionApiPMsg.fsrVisitCpltDt);
                // END 2020/06/29 K.Kitachi [QC#57231, ADD]
                } else {
                    setValue(meterReadingList.mtrReadDt, param.slsDt);
                }
                // mod end 2016/09/02 CSA QC#11064
                // mod start 2017/09/06 QC#15134
                //setValue(meterReadingList.mtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                setValue(meterReadingList.dsMtrReadTpCd, getDsMtrReadTpCd(param.CsaMeterList.no(i).dsMtrReadTpNm_OT.getValue()));
                // mod end 2017/09/06 QC#15134
                setValue(meterReadingList.mtrReadSrcTpCd, MTR_READ_SRC_TP.SERVICE);

                BigDecimal svcMachMstrPk = (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK);
                DS_CONTRTMsg dsContrDtlTMsg = NSXC001001GetContr.getContrByMachMstrPkInclWarranty(glblCmpyCd, svcMachMstrPk, param.slsDt.getValue());
                if (dsContrDtlTMsg != null) {
                    setValue(meterReadingList.dsContrNum, dsContrDtlTMsg.dsContrNum);
                }
                setValue(meterReadingList.mtrLbCd, mtrLbCd);
                setValue(meterReadingList.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.SERVICE_READS);
                // START 2020/07/29 K.Kitachi [QC#57410, ADD]
                setValue(meterReadingList.dsTestCopyClsCd, DS_TEST_COPY_CLS.TEST_COPY_OUT);
                // END 2020/07/29 K.Kitachi [QC#57410, ADD]
                idx++;
            }
            completionApiPMsg.xxMtrReadList.setValidCount(idx);
            // mod end 2016/07/06 CSA Defect#11284
        }

        // Time Event
        int timeEventCnt = 0;
        // Labor
        NSZC005001_xxTmEventListPMsg timeEventLaborList = completionApiPMsg.xxTmEventList.no(timeEventCnt);
        setTimeEventLaborList(timeEventLaborList, param);
        timeEventCnt++;
        // Travel
        NSZC005001_xxTmEventListPMsg timeEventTravelList = completionApiPMsg.xxTmEventList.no(timeEventCnt);
        setTimeEventTravelList(timeEventTravelList, param);
        timeEventCnt++;
        // Modification
        if (param.CsaModificationList.getValidCount() > 0) {
            String svcTmEventCd = getSvcTmEventCdForModification(glblCmpyCd);
            // QC#27882 Add Start
            String wkSvcTmEventToDt = null;
            String wkSvcTmEventToTm = null;
            // QC#27882 Add End

            // QC#27882 Mod Start
            //for (int i = 0; i < param.CsaModificationList.getValidCount(); i++) {
            for (int i = param.CsaModificationList.getValidCount() - 1; i >= 0; i--) {
            // QC#27882 Mod End

                // QC#27882 Add End
                // START  10/13/2016 [QC#14876, MOD]
                // mod start 2016/11/25 CSA Defect#16051
                String svcTaskCpltFlg = param.CsaModificationList.no(i).svcTaskCpltFlg.getValue();
                if (hasValue(svcTaskCpltFlg) && (ZYPConstant.FLG_ON_1.equals(svcTaskCpltFlg) || ZYPConstant.FLG_ON_Y.equals(svcTaskCpltFlg))) {
                    NSZC005001_xxTmEventListPMsg timeEventModificationList = completionApiPMsg.xxTmEventList.no(timeEventCnt);

                    // QC#27882 Add Start
                    if (!ZYPCommonFunc.hasValue(wkSvcTmEventToTm)) {
                        if (ZYPCommonFunc.hasValue(timeEventLaborList.svcTmEventToDt) && ZYPCommonFunc.hasValue(timeEventLaborList.svcTmEventToTm)) {
                            wkSvcTmEventToDt = timeEventLaborList.svcTmEventToDt.getValue();
                            wkSvcTmEventToTm = timeEventLaborList.svcTmEventToTm.getValue();
                        }
                    }
                    // QC#27882 Add End

                    // QC#27882 Mod Start
                    //setTimeEventModificationList(timeEventModificationList, param, svcTmEventCd, i);
                    // START 2021/10/04 R. Cabral [QC#59192, MOD]
                    // setTimeEventModificationList(timeEventModificationList, param, svcTmEventCd, i, wkSvcTmEventToDt, wkSvcTmEventToTm, timeEventLaborList);
                    // QC#27882 Mod End
                    int addedMods = setTimeEventModificationList(timeEventModificationList, param, svcTmEventCd, i, wkSvcTmEventToDt, wkSvcTmEventToTm, timeEventLaborList, completionApiPMsg, timeEventCnt);
                    if (addedMods > 0) {
                        timeEventCnt += addedMods;
                    }
                    // END   2021/10/04 R. Cabral [QC#59192, MOD]

                    // QC#27882 Add Start
                    if (ZYPCommonFunc.hasValue(timeEventModificationList.svcTmEventFromDt) && ZYPCommonFunc.hasValue(timeEventModificationList.svcTmEventFromTm)) {
                        // START 2021/10/04 R. Cabral [QC#59192, MOD]
                        // wkSvcTmEventToDt = timeEventModificationList.svcTmEventFromDt.getValue();
                        // wkSvcTmEventToTm = timeEventModificationList.svcTmEventFromTm.getValue();
                        int timeIdx = 0;
                        String earliestFromDateTime = null;
                        for (int idx = timeEventCnt - addedMods; idx <= timeEventCnt; idx++) {
                            // search for earliest timestamp for mods
                            String currentFromDt = completionApiPMsg.xxTmEventList.no(idx).svcTmEventFromDt.getValue();
                            String currentFromTm = completionApiPMsg.xxTmEventList.no(idx).svcTmEventFromTm.getValue();
                            if (!completionApiPMsg.xxTmEventList.no(idx).svcTmEventCd.getValue().equals(svcTmEventCd)) {
                                continue;
                            } else if (earliestFromDateTime == null
                                    || (currentFromDt + currentFromTm).compareTo(earliestFromDateTime) < 0) {
                                earliestFromDateTime = currentFromDt + currentFromTm;
                                timeIdx = idx;
                            }
                        }
                        wkSvcTmEventToDt = completionApiPMsg.xxTmEventList.no(timeIdx).svcTmEventFromDt.getValue();
                        wkSvcTmEventToTm = completionApiPMsg.xxTmEventList.no(timeIdx).svcTmEventFromTm.getValue();
                        // END   2021/10/04 R. Cabral [QC#59192, MOD]
                    } else {
                        // START 2021/10/04 R. Cabral [QC#59192, MOD]
                        // Modification is invalid
//                        wkSvcTmEventToDt = null;
//                        wkSvcTmEventToTm = null;
                        timeEventModificationList.clear();
                        timeEventCnt--;
                        int timeIdx = -1;
                        String earliestFromDateTime = null;
                        for (int idx = timeEventCnt - addedMods; idx <= timeEventCnt; idx++) {
                            String currentFromDt = completionApiPMsg.xxTmEventList.no(idx).svcTmEventFromDt.getValue();
                            String currentFromTm = completionApiPMsg.xxTmEventList.no(idx).svcTmEventFromTm.getValue();
                            if (!completionApiPMsg.xxTmEventList.no(idx).svcTmEventCd.getValue().equals(svcTmEventCd)) {
                                continue;
                            } else if (earliestFromDateTime == null
                                    || (currentFromDt + currentFromTm).compareTo(earliestFromDateTime) < 0) {
                                earliestFromDateTime = currentFromDt + currentFromTm;
                                timeIdx = idx;
                            }
                        }
                        if (timeIdx != -1) {
                            wkSvcTmEventToDt = completionApiPMsg.xxTmEventList.no(timeIdx).svcTmEventFromDt.getValue();
                            wkSvcTmEventToTm = completionApiPMsg.xxTmEventList.no(timeIdx).svcTmEventFromTm.getValue();
                        }
                        // END   2021/10/04 R. Cabral [QC#59192, MOD]
                    }
                    // QC#27882 Add End

                    timeEventCnt++;
                }
                // mod end 2016/11/25 CSA Defect#16051
                // END    10/13/2016 [QC#14876, MOD]
            }
            // QC#27882 Add Start
            // Reconfigure EndTime of Labor Event
            if (ZYPCommonFunc.hasValue(wkSvcTmEventToDt) && ZYPCommonFunc.hasValue(wkSvcTmEventToTm)) {
                setValue(timeEventLaborList.svcTmEventToDt, wkSvcTmEventToDt);
                setValue(timeEventLaborList.svcTmEventToTm, wkSvcTmEventToTm);
            }
            // QC#27882 Add End
        }
        completionApiPMsg.xxTmEventList.setValidCount(timeEventCnt);

        // Expense
        if (param.CsaExpensesList.getValidCount() > 0) {
            for (int i = 0; i < param.CsaExpensesList.getValidCount(); i++) {
                NSZC005001_xxExpenseListPMsg expenseList = completionApiPMsg.xxExpenseList.no(i);
                String expMdseCd = null;
                expMdseCd = getSvcMblS21ValTxt(glblCmpyCd, SVC_EXP_MDSE_CD, (String) param.CsaExpensesList.no(i).xxSvcExpTpTxt.getValue());
                setValue(expenseList.mdseCd, expMdseCd);
                setValue(expenseList.svcExpQty, BigDecimal.valueOf(1));
                if (hasValue(expMdseCd)) {
                    setValue(expenseList.uomCd, getUomCd(glblCmpyCd, expMdseCd));
                }
                String svcExecDt = null;
                if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
                    svcExecDt = S21StringUtil.subStringByLength(param.xxFsrVisitCpltTs.getValue(), DATE_START_POS, DATE_END_POS);
                }
                setValue(expenseList.svcExecDt, svcExecDt);
                setValue(expenseList.techCd, apiTechCd);
                setValue(expenseList.svcExpUnitAmt, param.CsaExpensesList.no(i).svcExpDealAmt);
                setValue(expenseList.svcExpDealAmt, param.CsaExpensesList.no(i).svcExpDealAmt);
            }
            completionApiPMsg.xxExpenseList.setValidCount(param.CsaExpensesList.getValidCount());
        }

        // mod start 2016/04/20 CSA Defect#4469
        // Parts Survey
        if (param.CsaPartSurvey.getValidCount() > 0) {
            for (int i = 0; i < param.CsaPartSurvey.getValidCount(); i++) {
                NSZC005001_xxPartSurveyListPMsg partSurveyList = completionApiPMsg.xxPartSurveyList.no(i);
                setValue(partSurveyList.mdseCd, param.CsaPartSurvey.no(i).mdseCd);
                setValue(partSurveyList.prtSrvyMdlTxt, param.CsaPartSurvey.no(i).prtSrvyMdlTxt);
                setValue(partSurveyList.prtSrvyWhTxt, param.CsaPartSurvey.no(i).prtSrvyWhTxt);
                setValue(partSurveyList.prtSrvyQstTxt, param.CsaPartSurvey.no(i).prtSrvyQstTxt);
                setValue(partSurveyList.prtSrvyAnsTxt, param.CsaPartSurvey.no(i).prtSrvyAnsTxt);
            }
            completionApiPMsg.xxPartSurveyList.setValidCount(param.CsaPartSurvey.getValidCount());
        }
        // mod start 2016/04/20 CSA Defect#4467

        return completionApiPMsg;
    }

    // START 2016/11/21 CSA QC#15782
    private NSZC005001PMsg createCompletionParamPhoneFix(NSZC068001PMsg param) {
        NSZC005001PMsg completionApiPMsg = new NSZC005001PMsg();
        // Visit Header
        setValue(completionApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(completionApiPMsg.xxModeCd, NSZC005001Constant.MODE_DEBRIEF);
        setValue(completionApiPMsg.userId, apiTechCd);
        setValue(completionApiPMsg.slsDt, param.slsDt);
        setValue(completionApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(completionApiPMsg.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(completionApiPMsg.svcMachMstrPk, (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK));

        SvcTimeZoneInfo tzFsrVisitCpltTs = null;
        String cnvtFsrVisitCpltTs = null;
        if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
            tzFsrVisitCpltTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitCpltTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitCpltTs != null) {
                cnvtFsrVisitCpltTs = tzFsrVisitCpltTs.getDateTime();
                // START 2023/04/03 K.Kitachi [QC#61263, MOD]
//                setValue(completionApiPMsg.fsrVisitArvDt, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, DATE_START_POS, DATE_END_POS));
//                setValue(completionApiPMsg.fsrVisitArvTm, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, TIME_START_POS, TIME_END_POS));
                String fsrVisitArvTs = convertDt(cnvtFsrVisitCpltTs, param.svcLborTmNum);
                setValue(completionApiPMsg.fsrVisitArvDt, S21StringUtil.subStringByLength(fsrVisitArvTs, DATE_START_POS, DATE_END_POS));
                setValue(completionApiPMsg.fsrVisitArvTm, S21StringUtil.subStringByLength(fsrVisitArvTs, TIME_START_POS, TIME_END_POS));
                // END 2023/04/03 K.Kitachi [QC#61263, MOD]
                setValue(completionApiPMsg.fsrVisitCpltDt, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, DATE_START_POS, DATE_END_POS));
                setValue(completionApiPMsg.fsrVisitCpltTm, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, TIME_START_POS, TIME_END_POS));
            }
        }

        setValue(completionApiPMsg.svcTrvlTmNum, BigDecimal.ZERO);
        setValue(completionApiPMsg.invCcyCd, (String) rcvTaskInfoMap.get(INV_CCY_CD));
        setValue(completionApiPMsg.ccyExchRate, (BigDecimal) rcvTaskInfoMap.get(CCY_EXCH_RATE));
        setValue(completionApiPMsg.svcBillTpCd, (String) rcvTaskInfoMap.get(SVC_BILL_TP_CD));
        setValue(completionApiPMsg.pmtTermCashDiscCd, (String) rcvTaskInfoMap.get(PMT_TERM_CASH_DISC_CD));
        setValue(completionApiPMsg.svcCpltLttdNum, param.svcCpltLttdNum.getValue());
        setValue(completionApiPMsg.svcCpltLgtdNum, param.svcCpltLgtdNum.getValue());
        setValue(completionApiPMsg.svcCpltLocGapDstNum, param.svcCpltLocGapDstNum.getValue());
        // START 2022/10/31 K.Kitachi [QC#60750, ADD]
        setValue(completionApiPMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        // END 2022/10/31 K.Kitachi [QC#60750, ADD]
        // START 2022/04/27 K.Kitachi [QC#59895, ADD]
        setValue(completionApiPMsg.arvMachDownTpCd, getArvMachDownTpCd(param.arvMachDownTpCd.getValue()));
        // END 2022/04/27 K.Kitachi [QC#59895, ADD]

        // Visit for task
        NSZC005001_xxVisitTaskListPMsg visitTaskList = completionApiPMsg.xxVisitTaskList.no(0);
        setValue(visitTaskList.svcTaskNum, param.svcTaskNum);
        // mod start 2019/11/29 QC#54113
        //setValue(visitTaskList.svcPblmTpCd, param.xtrnlPblmTpRefTxt.getValue());
        //setValue(visitTaskList.svcPblmLocTpCd, param.xtrnlPblmLocTpRefTxt.getValue());
        //setValue(visitTaskList.svcPblmRsnTpCd, param.xtrnlPblmRsnTpRefTxt.getValue());
        //setValue(visitTaskList.svcPblmCrctTpCd, param.xtrnlPblmCrctTpRefTxt.getValue());
        setValue(visitTaskList.svcPblmTpCd, getCodeValueForPhoneFix(param.xtrnlPblmTpRefTxt.getValue(), PHONE_FIX_SVC_PBLM_TP_CD, param.glblCmpyCd.getValue()));
        setValue(visitTaskList.svcPblmLocTpCd, getCodeValueForPhoneFix(param.xtrnlPblmLocTpRefTxt.getValue(), PHONE_FIX_SVC_PBLM_LOC_TP_CD, param.glblCmpyCd.getValue()));
        setValue(visitTaskList.svcPblmRsnTpCd, getCodeValueForPhoneFix(param.xtrnlPblmRsnTpRefTxt.getValue(), PHONE_FIX_SVC_PBLM_RSN_TP_CD, param.glblCmpyCd.getValue()));
        setValue(visitTaskList.svcPblmCrctTpCd, getCodeValueForPhoneFix(param.xtrnlPblmCrctTpRefTxt.getValue(), PHONE_FIX_SVC_PBLM_CRCT_TP_CD, param.glblCmpyCd.getValue()));
        // mod end 2019/11/29 QC#54113
        if (hasValue(param.svcLborTmNum)) {
            setValue(visitTaskList.svcLborTmNum, param.svcLborTmNum.getValue());
        } else {
            setValue(visitTaskList.svcLborTmNum, BigDecimal.ZERO);
        }
        setValue(visitTaskList.xxVisitTaskCpltFlg, ZYPConstant.FLG_ON_Y);
        // Add Start 2017/10/25 QC#22061
        setValue(visitTaskList.phoneFixFlg, param.phoneFixFlg);
        // Add End 2017/10/25 QC#22061        
        completionApiPMsg.xxVisitTaskList.setValidCount(1);

        // Time Event
        int timeEventCnt = 0;
        // Labor
        NSZC005001_xxTmEventListPMsg timeEventLaborList = completionApiPMsg.xxTmEventList.no(timeEventCnt);
        setTimeEventLaborListPhoneFix(timeEventLaborList, param);
        timeEventCnt++;
        completionApiPMsg.xxTmEventList.setValidCount(timeEventCnt);
        return completionApiPMsg;
    }
    // END 2016/11/21 CSA QC#15782

    // add start 2019/11/29 QC#54113
    private String getCodeValueForPhoneFix(String paramValue, String codeName, String glblCmpyCd) {
        if (hasValue(paramValue)) {
            return paramValue;
        }
        String constValue = getVarCharConst(codeName, glblCmpyCd);
        return constValue;
    }
    // add end 2019/11/29 QC#54113

    // add 2016/04/20 CSA Defect#4467
    /**
     * createCompletionParamForInstallCheck
     * @param param NSZC068001PMsg
     * @return completionApiPMsg
     */
    private NSZC005001PMsg createCompletionParamForInstallCheck(NSZC068001PMsg param) {
        NSZC005001PMsg completionApiPMsg = new NSZC005001PMsg();

        // Header
        setValue(completionApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(completionApiPMsg.slsDt, param.slsDt);
        setValue(completionApiPMsg.xxModeCd, NSZC005001Constant.MODE_INSTALL_CHECK);
        setValue(completionApiPMsg.userId, apiTechCd);
        setValue(completionApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
//        setValue(completionApiPMsg.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(completionApiPMsg.svcMachMstrPk, (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK));
//        setValue(completionApiPMsg.fsrVisitArvDt, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT));
//        setValue(completionApiPMsg.fsrVisitArvTm, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_TM));
//        setValue(completionApiPMsg.invCcyCd, (String) rcvTaskInfoMap.get(INV_CCY_CD));
        // START 2017/09/01 K.Kim [QC#20672, ADD]
        setValue(completionApiPMsg.istlCpltFlg, param.istlCpltFlg);
        // END 2017/09/01 K.Kim [QC#20672, ADD]
        SvcTimeZoneInfo tzFsrVisitCpltTs = null;
        String cnvtFsrVisitCpltTs = null;
        if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
            tzFsrVisitCpltTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitCpltTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitCpltTs != null) {
                cnvtFsrVisitCpltTs = tzFsrVisitCpltTs.getDateTime();
                setValue(completionApiPMsg.fsrVisitCpltDt, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, DATE_START_POS, DATE_END_POS));
                setValue(completionApiPMsg.fsrVisitCpltTm, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, TIME_START_POS, TIME_END_POS));
            }
        }
        if (param.CsaInstallCheckList.getValidCount() > 0) {
            for (int i = 0; i < param.CsaInstallCheckList.getValidCount(); i++) {
                NSZC005001_xxFsrIstlChkListPMsg stlChkList = completionApiPMsg.xxFsrIstlChkList.no(i);
                setValue(stlChkList.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
                setValue(stlChkList.svcTaskNum, param.svcTaskNum);
                setValue(stlChkList.svcConfigMstrPk, param.CsaInstallCheckList.no(i).svcConfigMstrPk);
                setValue(stlChkList.mdseCd, param.CsaInstallCheckList.no(i).mdseCd);
                Map<String, Object> mdlInfo = getMdlInfo(param.glblCmpyCd.getValue(), param.CsaInstallCheckList.no(i).svcConfigMstrPk.getValue());
                if (mdlInfo != null) {
                    setValue(stlChkList.mdlId, (BigDecimal) mdlInfo.get(MDL_ID));
                    setValue(stlChkList.mdlNm, (String) mdlInfo.get(MDL_NM));
                }
                setValue(stlChkList.serNum, param.CsaInstallCheckList.no(i).serNum);
                // mod start 2016/11/09 CSA Defect#15872
                if (ZYPConstant.FLG_ON_1.equals(param.CsaInstallCheckList.no(i).istlChkVerFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(param.CsaInstallCheckList.no(i).istlChkVerFlg.getValue())) {
                    setValue(stlChkList.istlChkVerFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    setValue(stlChkList.istlChkVerFlg, ZYPConstant.FLG_OFF_N);
                }
                // mod end 2016/11/09 CSA Defect#15872
                setValue(stlChkList.crctSerNum, param.CsaInstallCheckList.no(i).crctSerNum);
            }
            completionApiPMsg.xxFsrIstlChkList.setValidCount(param.CsaInstallCheckList.getValidCount());
        }

        return completionApiPMsg;
    }

    /**
     * setTimeEventLaborList
     * @param timeEventList
     * @param param
     */
    private void setTimeEventLaborList(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param) {
        setValue(timeEventList.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(timeEventList.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(timeEventList.svcTmEventCd, getSvcTmEventCdForLabor(param.glblCmpyCd.getValue()));
        setValue(timeEventList.svcTaskNum, param.svcTaskNum);
        // mod start 2016/01/07 CSA Defect#2563
        //if (hasValue(param.xxFsrVisitArvTs) && param.xxFsrVisitArvTs.getValue().length() >= TS_LEN) {
        //    setValue(timeEventList.svcTmEventFromDt, S21StringUtil.subStringByLength(param.xxFsrVisitArvTs.getValue(), DATE_START_POS, DATE_END_POS));
        //    setValue(timeEventList.svcTmEventFromTm, S21StringUtil.subStringByLength(param.xxFsrVisitArvTs.getValue(), TIME_START_POS, TIME_END_POS));
        //}
        setValue(timeEventList.svcTmEventFromDt, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT));
        setValue(timeEventList.svcTmEventFromTm, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_TM));
        if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
            // START  02/17/2016 T.Iwamoto [QC#3727, MOD]
            SvcTimeZoneInfo tzFsrVisitCpltTs = NSXC001001SvcTimeZone.convertTime(
                    NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitCpltTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitCpltTs != null) {
                String cnvtFsrVisitCpltTs = tzFsrVisitCpltTs.getDateTime();
                setValue(timeEventList.svcTmEventToDt, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, DATE_START_POS, DATE_END_POS));
                setValue(timeEventList.svcTmEventToTm, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, TIME_START_POS, TIME_END_POS));
            }
            // END  02/17/2016 T.Iwamoto [QC#3727, MOD]
        }

        if (hasValue(timeEventList.svcTmEventFromDt) && hasValue(timeEventList.svcTmEventToDt)) {
            //BigDecimal minute = BigDecimal.valueOf(getTimeDiff((String) param.xxFsrVisitArvTs.getValue(), (String) param.xxFsrVisitCpltTs.getValue()));
            StringBuilder sb = new StringBuilder();
            sb.append(timeEventList.svcTmEventFromDt.getValue());
            sb.append(timeEventList.svcTmEventFromTm.getValue());
            String fsrVisitArvTs = sb.toString();
            BigDecimal minute = BigDecimal.valueOf(getTimeDiff(fsrVisitArvTs, (String) param.xxFsrVisitCpltTs.getValue()));
            setValue(timeEventList.durnTmNum, minute);
        }
        // mod end 2016/01/07 CSA Defect#2563
        // START 2022/04/27 K.Kitachi [QC#59895, MOD]
//        setValue(timeEventList.machDownFlg, getMachDownFlg(param.machDownFlg.getValue()));
        setValue(timeEventList.machDownFlg, getMachDownFlg(timeEventList, param));
        // END 2022/04/27 K.Kitachi [QC#59895, MOD]
    }

    // START 2016/11/21 CSA QC#15782
    /**
     * setTimeEventLaborListPhoneFix
     * @param timeEventList
     * @param param
     */
    private void setTimeEventLaborListPhoneFix(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param) {
        setValue(timeEventList.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(timeEventList.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(timeEventList.svcTmEventCd, getSvcTmEventCdForLaborPhoneFix(param.glblCmpyCd.getValue()));
        setValue(timeEventList.svcTaskNum, param.svcTaskNum);
        if (hasValue(param.xxFsrVisitCpltTs) && param.xxFsrVisitCpltTs.getValue().length() >= TS_LEN) {
            SvcTimeZoneInfo tzFsrVisitCpltTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.xxFsrVisitCpltTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
            if (tzFsrVisitCpltTs != null) {
                String cnvtFsrVisitCpltTs = tzFsrVisitCpltTs.getDateTime();
                String svcTmEventDt = convertDt(cnvtFsrVisitCpltTs, param.svcLborTmNum);
                setValue(timeEventList.svcTmEventFromDt, S21StringUtil.subStringByLength(svcTmEventDt, DATE_START_POS, DATE_END_POS));
                setValue(timeEventList.svcTmEventFromTm, S21StringUtil.subStringByLength(svcTmEventDt, TIME_START_POS, TIME_END_POS));
                setValue(timeEventList.svcTmEventToDt, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, DATE_START_POS, DATE_END_POS));
                setValue(timeEventList.svcTmEventToTm, S21StringUtil.subStringByLength(cnvtFsrVisitCpltTs, TIME_START_POS, TIME_END_POS));
            }
        }
        setValue(timeEventList.durnTmNum, param.svcLborTmNum.getValue());
        // START 2022/04/27 K.Kitachi [QC#59895, MOD]
//        setValue(timeEventList.machDownFlg, getMachDownFlg(param.machDownFlg.getValue()));
        setValue(timeEventList.machDownFlg, getMachDownFlg(timeEventList, param));
        // END 2022/04/27 K.Kitachi [QC#59895, MOD]
    }

    private String convertDt(String cnvtFsrVisitCpltTs, EZDPBigDecimalItem svcLborTmNum) {

        if (!hasValue(svcLborTmNum)) {
            setValue(svcLborTmNum, BigDecimal.ZERO);
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        try {
            cal.setTime(sdf.parse(cnvtFsrVisitCpltTs));
            cal.add(Calendar.MINUTE, svcLborTmNum.getValue().negate().intValue());
        } catch (ParseException e) {
            return null;
        }
        return sdf.format(cal.getTime());
    }
    // END 2016/11/21 CSA QC#15782

    /**
     * setTimeEventTravelList
     * @param timeEventList
     * @param param
     */
    private void setTimeEventTravelList(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param) {
        setValue(timeEventList.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(timeEventList.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(timeEventList.svcTmEventCd, getSvcTmEventCdForTravel(param.glblCmpyCd.getValue()));
        setValue(timeEventList.svcTaskNum, param.svcTaskNum);
        // mod start 2016/01/07 CSA Defect#2563
        //if (hasValue(param.xxFsrVisitDisptTs) && param.xxFsrVisitDisptTs.getValue().length() >= TS_LEN) {
        //    setValue(timeEventList.svcTmEventFromDt, S21StringUtil.subStringByLength(param.xxFsrVisitDisptTs.getValue(), DATE_START_POS, DATE_END_POS));
        //    setValue(timeEventList.svcTmEventFromTm, S21StringUtil.subStringByLength(param.xxFsrVisitDisptTs.getValue(), TIME_START_POS, TIME_END_POS));
        //}
        //if (hasValue(param.xxFsrVisitArvTs) && param.xxFsrVisitArvTs.getValue().length() >= TS_LEN) {
        //    setValue(timeEventList.svcTmEventToDt, S21StringUtil.subStringByLength(param.xxFsrVisitArvTs.getValue(), DATE_START_POS, DATE_END_POS));
        //    setValue(timeEventList.svcTmEventToTm, S21StringUtil.subStringByLength(param.xxFsrVisitArvTs.getValue(), TIME_START_POS, TIME_END_POS));
        //}
        // mod start 2016/12/01 CSA Defect#16145
        if (hasValue((String) rcvTaskInfoMap.get(FSR_VISIT_DISPT_DT))) {
            setValue(timeEventList.svcTmEventFromDt, (String) rcvTaskInfoMap.get(FSR_VISIT_DISPT_DT));
            setValue(timeEventList.svcTmEventFromTm, (String) rcvTaskInfoMap.get(FSR_VISIT_DISPT_TM));
        } else {
            setValue(timeEventList.svcTmEventFromDt, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT));
            setValue(timeEventList.svcTmEventFromTm, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_TM));
        }
        // mod end 2016/12/01 CSA Defect#16145
        setValue(timeEventList.svcTmEventToDt, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_DT));
        setValue(timeEventList.svcTmEventToTm, (String) rcvTaskInfoMap.get(FSR_VISIT_ARV_TM));
        if (hasValue(timeEventList.svcTmEventFromDt) && hasValue(timeEventList.svcTmEventToDt)) {
            //BigDecimal minute = BigDecimal.valueOf(getTimeDiff((String) param.xxFsrVisitDisptTs.getValue(), (String) param.xxFsrVisitArvTs.getValue()));
            StringBuilder disptTsBuilder = new StringBuilder();
            disptTsBuilder.append(timeEventList.svcTmEventFromDt.getValue());
            disptTsBuilder.append(timeEventList.svcTmEventFromTm.getValue());
            String fsrVisitDisptTs = disptTsBuilder.toString();

            StringBuilder arvTsBuilder = new StringBuilder();
            arvTsBuilder.append(timeEventList.svcTmEventToDt.getValue());
            arvTsBuilder.append(timeEventList.svcTmEventToTm.getValue());
            String fsrVisitArvTs = arvTsBuilder.toString();

            BigDecimal minute = BigDecimal.valueOf(getTimeDiff(fsrVisitDisptTs, fsrVisitArvTs));
            setValue(timeEventList.durnTmNum, minute);
        }
        // mod end 2016/01/07 CSA Defect#2563
        // START 2022/04/27 K.Kitachi [QC#59895, MOD]
//        setValue(timeEventList.machDownFlg, getMachDownFlg(param.machDownFlg.getValue()));
        setValue(timeEventList.machDownFlg, getMachDownFlg(timeEventList, param));
        // END 2022/04/27 K.Kitachi [QC#59895, MOD]
    }

    /**
     * setTimeEventLaborList
     * @param timeEventList
     * @param param
     * @param svcTmEventCd
     * @param row
     * @param lastSvcTmEventToDt
     * @param lastSvcTmEventToTm
     * @param timeEventTravelList
     * @param completionPMsg
     * @param timeEventCnt
     */
// QC#27882 Mod Start
    // START 2021/10/04 R. Cabral [QC#59192, MOD]
    // private void setTimeEventModificationList(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param, String svcTmEventCd, int row
    //        , String lastSvcTmEventToDt, String lastSvcTmEventToTm, NSZC005001_xxTmEventListPMsg timeEventTravelList) {
    private int setTimeEventModificationList(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param, String svcTmEventCd, int row
            , String lastSvcTmEventToDt, String lastSvcTmEventToTm, NSZC005001_xxTmEventListPMsg timeEventTravelList, NSZC005001PMsg completionPMsg, int timeEventCnt) {
    // private void setTimeEventModificationList(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param, String svcTmEventCd, int row) {
// QC#27882 Mod End
        int addedMods = 0;
    // END   2021/10/04 R. Cabral [QC#59192, MOD]
        setValue(timeEventList.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(timeEventList.fsrVisitNum, (String) rcvTaskInfoMap.get(FSR_VISIT_NUM));
        setValue(timeEventList.svcTmEventCd, svcTmEventCd);
        setValue(timeEventList.svcTaskNum, param.CsaModificationList.no(row).svcTaskNum_M2);
// START 2018/08/20 [QC#27661, MOD]
//        setValue(timeEventList.durnTmNum, param.CsaModificationList.no(row).svcLborTmNum_M1);
        setValue(timeEventList.durnTmNum, param.CsaModificationList.no(row).svcLborTmNum_M2);
// END 2018/08/20 [QC#27661, MOD]
        // START 2022/04/27 K.Kitachi [QC#59895, MOD]
//        setValue(timeEventList.machDownFlg, getMachDownFlg(param.machDownFlg.getValue()));
        setValue(timeEventList.machDownFlg, getMachDownFlg(timeEventList, param));
        // END 2022/04/27 K.Kitachi [QC#59895, MOD]
        setValue(timeEventList.svcModPlnId, param.CsaModificationList.no(row).svcModPlnId_M1);
// START  02/19/2016 T.Iwamoto [QC#4108, ADD]
        setValue(timeEventList.serNumTxt, param.CsaModificationList.no(row).serNumTxt);
        setValue(timeEventList.modItemTxt, param.CsaModificationList.no(row).modItemTxt);
// END  02/19/2016 T.Iwamoto [QC#4108, ADD]

        
// QC#27882 Add Start
        String modSvcTmEventFrom = null;
        if (ZYPCommonFunc.hasValue(lastSvcTmEventToDt) 
                && ZYPCommonFunc.hasValue(lastSvcTmEventToTm) 
                && ZYPCommonFunc.hasValue(timeEventList.durnTmNum)) {

            modSvcTmEventFrom = addTime(lastSvcTmEventToDt + lastSvcTmEventToTm, timeEventList.durnTmNum.getValue().multiply(new BigDecimal("-1")));

        } else {
            // START 2021/10/04 R. Cabral [QC#59192, MOD]
            // return;
            return addedMods;
            // END   2021/10/04 R. Cabral [QC#59192, MOD]
        }

        String labSvcTmEventFrom = null;
        if (ZYPCommonFunc.hasValue(timeEventTravelList.svcTmEventFromDt) 
                && ZYPCommonFunc.hasValue(timeEventTravelList.svcTmEventFromTm) ) {
            labSvcTmEventFrom = timeEventTravelList.svcTmEventFromDt.getValue() + timeEventTravelList.svcTmEventFromTm.getValue();
            
        } else {
            // START 2021/10/04 R. Cabral [QC#59192, MOD]
            // return;
            return addedMods;
            // END   2021/10/04 R. Cabral [QC#59192, MOD]
        }

        // It is checked whether the start time of Modification does not exceed the Labor start time
        long checkRslt = getTimeDiff(labSvcTmEventFrom, modSvcTmEventFrom);
        String svcTmEventFromDt = null;
        String svcTmEventFromTm = null;
        if (checkRslt >= 0) {
            svcTmEventFromDt = S21StringUtil.subStringByLength(modSvcTmEventFrom, DATE_START_POS, DATE_END_POS);
            svcTmEventFromTm = S21StringUtil.subStringByLength(modSvcTmEventFrom, TIME_START_POS, TIME_END_POS);
        } else {
            svcTmEventFromDt = S21StringUtil.subStringByLength(labSvcTmEventFrom, DATE_START_POS, DATE_END_POS);
            svcTmEventFromTm = S21StringUtil.subStringByLength(labSvcTmEventFrom, TIME_START_POS, TIME_END_POS);
        }
        // START 2021/10/04 R. Cabral [QC#59192, MOD]
//        setValue(timeEventList.svcTmEventFromDt, svcTmEventFromDt);
//        setValue(timeEventList.svcTmEventFromTm, svcTmEventFromTm);
//        setValue(timeEventList.svcTmEventToDt, lastSvcTmEventToDt);
//        setValue(timeEventList.svcTmEventToTm, lastSvcTmEventToTm);
// QC#27882 Add End

        String modFromDt = svcTmEventFromDt;
        String modFromTm = svcTmEventFromTm;
        String modToDt = lastSvcTmEventToDt;
        String modToTm = lastSvcTmEventToTm;

        List<SVC_SUPPL_TASKTMsg> supplTaskTMsgList = getSupplTask(completionPMsg, timeEventList);
        long totalModTime = getTimeDiff(modFromDt + modFromTm, modToDt + modToTm);
        long currentModsTime = 0;

        for (SVC_SUPPL_TASKTMsg supplTaskTMsg : supplTaskTMsgList) {
            String itrptFromDt = supplTaskTMsg.svcSupplFromDt.getValue();
            String itrptFromTm = supplTaskTMsg.svcSupplFromTm.getValue();
            String itrptToDt = supplTaskTMsg.svcSupplToDt.getValue();
            String itrptToTm = supplTaskTMsg.svcSupplToTm.getValue();
            String itrptFromDtTm = itrptFromDt + itrptFromTm;
            String itrptToDtTm = itrptToDt + itrptToTm;

            if ((modFromDt + modFromTm).compareTo(itrptFromDtTm) <= 0) {
                if ((modToDt + modToTm).compareTo(itrptToDtTm) <= 0
                        && (modToDt + modToTm).compareTo(itrptFromDtTm) > 0) {
                    // Mod From less than InterruptFrom and ModTo less than or equal to InterruptTo and ModTo is greater than InterruptFrom
                    // Explanation: Mod starts before interrupt and Mod end is after interrupt start, but before or equal to interrupt To
                    //              Mod end is within Interrupt
                    // ModTo should be equal to Interrupt From, maintain original mod time
                    modToDt = itrptFromDt;
                    modToTm = itrptFromTm;
                    // Shift ModFrom
                    String shiftedModFromTs = addTime(modToDt + modToTm, new BigDecimal((totalModTime - currentModsTime) * -1));
                    long timeDiff = getTimeDiff(labSvcTmEventFrom, shiftedModFromTs);
                    String shiftedModFromDt = null;
                    String shiftedModFromTm = null;
                    if (timeDiff >= 0) {
                        // Use shifted mod date 
                        shiftedModFromDt = S21StringUtil.subStringByLength(shiftedModFromTs, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(shiftedModFromTs, TIME_START_POS, TIME_END_POS);
                    } else {
                        // Shifted mod date earlier than labor start, use labor start instead 
                        shiftedModFromDt = S21StringUtil.subStringByLength(labSvcTmEventFrom, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(labSvcTmEventFrom, TIME_START_POS, TIME_END_POS);
                        totalModTime += timeDiff;
                    }
                    long mod2TimeDiff = getTimeDiff(shiftedModFromDt + shiftedModFromTm, itrptFromDt + itrptFromTm);
                    if (mod2TimeDiff > 0) {
                        modFromDt = shiftedModFromDt;
                        modFromTm = shiftedModFromTm;
                    } else {
                        // Current mod is invalid, break loop
                        modFromDt = null;
                        modFromTm = null;
                        break;
                    }                    
                } else if ((modToDt + modToTm).compareTo(itrptToDtTm) > 0) {
                    // Mod From less than Interrupt From and Mod To greater than Interrupt To
                    // Explanation: Mod starts before interrupt and ends after Interrupt
                    // mod1, modFrom = interruptTo, modTo = no change; get remaining mod duration
                    long addedModTime = getTimeDiff(itrptToDtTm, modToDt + modToTm);
                    String shiftedModFromTs = addTime(itrptFromDt + itrptFromTm, new BigDecimal((totalModTime - (currentModsTime + addedModTime)) * -1));
                    String shiftedModFromDt = null;
                    String shiftedModFromTm = null;
                    long timeDiff = getTimeDiff(labSvcTmEventFrom, shiftedModFromTs);
                    if (timeDiff >= 0) {
                        // Use shifted mod date 
                        shiftedModFromDt = S21StringUtil.subStringByLength(shiftedModFromTs, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(shiftedModFromTs, TIME_START_POS, TIME_END_POS);
                    } else {
                        // Shifted mod date earlier than labor start, use labor start instead 
                        shiftedModFromDt = S21StringUtil.subStringByLength(labSvcTmEventFrom, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(labSvcTmEventFrom, TIME_START_POS, TIME_END_POS);
                        totalModTime += timeDiff;
                    }
                    long mod2TimeDiff = getTimeDiff(shiftedModFromDt + shiftedModFromTm, itrptFromDt + itrptFromTm);
                    if (mod2TimeDiff > 0) {
                        // Add Mod1 if mod2 time is greater than 0
                        NSZC005001_xxTmEventListPMsg changeDtlPMsg = completionPMsg.xxTmEventList.no(timeEventCnt + addedMods + 1);
                        EZDMsg.copy(timeEventList, null, changeDtlPMsg, null);
                        setValue(changeDtlPMsg.svcTmEventFromDt, itrptToDt);
                        setValue(changeDtlPMsg.svcTmEventFromTm, itrptToTm);
                        setValue(changeDtlPMsg.svcTmEventToDt, modToDt);
                        setValue(changeDtlPMsg.svcTmEventToTm, modToTm);
                        addedMods++;
                        currentModsTime += addedModTime;
                        // mod2, modTo = interruptFrom, modFrom = subtract remaining mod duration from modTo timestamp
                        modToDt = itrptFromDt;
                        modToTm = itrptFromTm;
                        modFromDt = shiftedModFromDt;
                        modFromTm = shiftedModFromTm;
                    } else {
                        // Add previous half of mods, do not add first half (first half has 0 minutes)
                        modFromDt = itrptToDt;
                        modFromTm = itrptToTm;
                    }
                }
            } else {
               // Mod From Greater than InterruptFrom
                if ((modToDt + modToTm).compareTo(itrptToDtTm) <= 0
                        && (modToDt + modToTm).compareTo(itrptFromDtTm) > 0) {
                    // ModFrom greater than InterruptFrom and ModTo less than or equal to InterruptTo and ModTo is greater than InterruptFrom
                    // Explanation: Mod is inside an interrupt
                    // ModTo should be equal to InterruptFrom, maintain original mod time
                    modToDt = itrptFromDt;
                    modToTm = itrptFromTm;
                    // Shift ModFrom
                    String shiftedModFromTs = addTime(modToDt + modToTm, new BigDecimal((totalModTime - currentModsTime) * -1));

                    long timeDiff = getTimeDiff(labSvcTmEventFrom, shiftedModFromTs);
                    String shiftedModFromDt = null;
                    String shiftedModFromTm = null;
                    if (timeDiff >= 0) {
                        // Use shifted mod date 
                        shiftedModFromDt = S21StringUtil.subStringByLength(shiftedModFromTs, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(shiftedModFromTs, TIME_START_POS, TIME_END_POS);
                    } else {
                        // Shifted mod date earlier than labor start, use labor start instead 
                        shiftedModFromDt = S21StringUtil.subStringByLength(labSvcTmEventFrom, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(labSvcTmEventFrom, TIME_START_POS, TIME_END_POS);
                        totalModTime += timeDiff;
                    }
                    long mod2TimeDiff = getTimeDiff(shiftedModFromDt + shiftedModFromTm, itrptFromDt + itrptFromTm);
                    if (mod2TimeDiff > 0) {
                        modFromDt = shiftedModFromDt;
                        modFromTm = shiftedModFromTm;
                    } else {
                        // Current mod is invalid, break loop
                        modFromDt = null;
                        modFromTm = null;
                        break;
                    }
                } else if ((modToDt + modToTm).compareTo(itrptToDtTm) > 0
                        && (modFromDt + modFromTm).compareTo(itrptToDtTm) < 0) {
                    // ModFrom greater than InterruptFrom and ModTo greater than InterruptTo
                    // Explanation: ModFrom is inside interrupt, but ModTo is after InterruptTo
                    // mod1 ModFrom = interruptTo, modTo = no change; get remaining mod duration
                    long addedModTime = getTimeDiff(itrptToDtTm, modToDt + modToTm);
                    String shiftedModFromTs = addTime(itrptFromDt + itrptFromTm, new BigDecimal((totalModTime - (currentModsTime + addedModTime)) * -1));
                    String shiftedModFromDt = null;
                    String shiftedModFromTm = null;
                    long timeDiff = getTimeDiff(labSvcTmEventFrom, shiftedModFromTs);
                    if (timeDiff >= 0) {
                        // Use shifted mod date 
                        shiftedModFromDt = S21StringUtil.subStringByLength(shiftedModFromTs, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(shiftedModFromTs, TIME_START_POS, TIME_END_POS);
                    } else {
                        // Shifted mod date earlier than labor start, use labor start instead 
                        shiftedModFromDt = S21StringUtil.subStringByLength(labSvcTmEventFrom, DATE_START_POS, DATE_END_POS);
                        shiftedModFromTm = S21StringUtil.subStringByLength(labSvcTmEventFrom, TIME_START_POS, TIME_END_POS);
                        totalModTime += timeDiff;
                    }
                    long mod2TimeDiff = getTimeDiff(shiftedModFromDt + shiftedModFromTm, itrptFromDt + itrptFromTm);
                    if (mod2TimeDiff > 0) {
                        // Add Mod1 if mod2 time is greater than 0
                        NSZC005001_xxTmEventListPMsg changeDtlPMsg = completionPMsg.xxTmEventList.no(timeEventCnt + addedMods + 1);
                        EZDMsg.copy(timeEventList, null, changeDtlPMsg, null);
                        setValue(changeDtlPMsg.svcTmEventFromDt, itrptToDt);
                        setValue(changeDtlPMsg.svcTmEventFromTm, itrptToTm);
                        setValue(changeDtlPMsg.svcTmEventToDt, modToDt);
                        setValue(changeDtlPMsg.svcTmEventToTm, modToTm);
                        addedMods++;
                        currentModsTime += addedModTime;
                        // mod2, modTo = interruptFrom, modFrom = subtract remaining mod duration from modTo timestamp
                        modToDt = itrptFromDt;
                        modToTm = itrptFromTm;
                        modFromDt = shiftedModFromDt;
                        modFromTm = shiftedModFromTm;
                    } else {
                        // Add previous half of mods, do not add first half (first half has 0 minutes)
                        modFromDt = itrptToDt;
                        modFromTm = itrptToTm;
                    }
                }
            }
        }

        setValue(timeEventList.svcTmEventFromDt, modFromDt);
        setValue(timeEventList.svcTmEventFromTm, modFromTm);
        setValue(timeEventList.svcTmEventToDt, modToDt);
        setValue(timeEventList.svcTmEventToTm, modToTm);
        return addedMods;
        // END   2021/10/04 R. Cabral [QC#59192, MOD]

    }

    // START 2021/10/04 R. Cabral [QC#59192, ADD]
    @SuppressWarnings("unchecked")
    /**
     * getSupplTask
     * @param pMsg
     * @param dtlPMsg
     * @return List<SVC_SUPPL_TASKTMsg>
     */
    private List<SVC_SUPPL_TASKTMsg> getSupplTask(NSZC005001PMsg pMsg, NSZC005001_xxTmEventListPMsg dtlPMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", dtlPMsg.svcTaskNum.getValue());
        paramMap.put("svcItrptFlgY", ZYPConstant.FLG_ON_Y);
        paramMap.put("exclLborFlg", ZYPConstant.FLG_ON_Y);
        return (List<SVC_SUPPL_TASKTMsg>) ssmBatchClient.queryObjectList("getSupplTask", paramMap);
    }
    // END   2021/10/04 R. Cabral [QC#59192, ADD]

    /**
     * getMachDownFlg
     * @param machDownFlg
     * @return machDownFlg
     */
    private String getMachDownFlg(String machDownFlg) {
        if (!hasValue(machDownFlg)) {
            return ZYPConstant.FLG_OFF_N;
        }
        // START 2022/04/27 K.Kitachi [QC#59895, MOD]
//        if (ZYPConstant.FLG_ON_1.equals(machDownFlg)) {
        if (ZYPConstant.FLG_OFF_0.equals(machDownFlg)) {
        // END 2022/04/27 K.Kitachi [QC#59895, MOD]
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPConstant.FLG_ON_Y.equals(machDownFlg)) {
            return ZYPConstant.FLG_ON_Y;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    // START 2022/04/27 K.Kitachi [QC#59895, ADD]
    /**
     * getArvMachDownTpCd
     * @param arvMachDownTpCd String
     * @return arvMachDownTpCd
     */
    private String getArvMachDownTpCd(String arvMachDownTpCd) {
        if (!hasValue(arvMachDownTpCd)) {
            return null;
        }
        return getMachDownFlg(arvMachDownTpCd);
    }

    /**
     * getMachDownFlg
     * @param timeEventList NSZC005001_xxTmEventListPMsg
     * @param param NSZC068001PMsg
     * @return machDownFlg
     */
    private String getMachDownFlg(NSZC005001_xxTmEventListPMsg timeEventList, NSZC068001PMsg param) {
        if (hasValue(param.machDownFlg)) {
            return getMachDownFlg(param.machDownFlg.getValue());
        }
        if (hasValue(param.arvMachDownTpCd)) {
            return getMachDownFlg(param.arvMachDownTpCd.getValue());
        }
        SVC_TASKTMsg svcTask = getSvcTask(param.glblCmpyCd.getValue(), timeEventList.svcTaskNum.getValue());
        if (svcTask != null) {
            return svcTask.machDownFlg.getValue();
        }
        return ZYPConstant.FLG_OFF_N;
    }
    // END 2022/04/27 K.Kitachi [QC#59895, ADD]

    /**
     * getTimeDiff
     * @param startTime String
     * @param endTime String
     * @return diffMinutes
     */
    private long getTimeDiff(String startTime, String endTime) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        java.util.Date startDay = null;
        java.util.Date endDay = null;
        long diffMinutes = 0;
        long lngStartTime = 0;
        long lngEndTime = 0;

        // START 2017/08/09 K.Kojima [QC#19796,ADD]
        if (!ZYPCommonFunc.hasValue(startTime) || !ZYPCommonFunc.hasValue(endTime)) {
            return diffMinutes;
        }
        // END 2017/08/09 K.Kojima [QC#19796,ADD]

        try {
            startDay = formatter.parse(startTime);
            endDay = formatter.parse(endTime);
        } catch (ParseException e) {
            return diffMinutes;
        }

        lngStartTime = startDay.getTime();
        lngEndTime = endDay.getTime();
        diffMinutes = (lngEndTime - lngStartTime) / MINUTES;

        // START 2017/01/30 [QC#17291, ADD]
        if (diffMinutes > TIME_DIFF_MAX_VALUE) {
            diffMinutes = TIME_DIFF_MAX_VALUE;
        }
        // END   2017/01/30 [QC#17291, ADD]

        return diffMinutes;
    }

    /**
     * getUomCd
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return uomCd
     */
    private String getUomCd(String glblCmpyCd, String mdseCd) {
        String uomCd = null;
        MDSE_STORE_PKGTMsg inMsg = new MDSE_STORE_PKGTMsg();
        inMsg.setSQLID("501");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);
        inMsg.setConditionValue("primPkgUomFlg01", ZYPConstant.FLG_ON_Y);
        MDSE_STORE_PKGTMsgArray outArray = (MDSE_STORE_PKGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            uomCd = outArray.no(0).pkgUomCd.getValue();
        }
        return uomCd;
    }

    /**
     * getMeterLabel
     * @param glblCmpyCd String
     * @param mtrLbNm String
     * @return MTR_LBTMsgArray
     */
    private MTR_LBTMsgArray getMeterLabel(String glblCmpyCd, String mtrLbNm) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbNm01", mtrLbNm);
        inMsg.setConditionValue("mtrLbTpCd01", MTR_LB_TP.REGULAR_METER);
        MTR_LBTMsgArray outArray = (MTR_LBTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        return outArray;
    }

    /**
     * getSvcTmEventCdForLabor
     * @param glblCmpyCd String
     * @return svcTmEventCd
     */
    private String getSvcTmEventCdForLabor(String glblCmpyCd) {
        String svcTmEventCd = null;
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inclLborTmFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("phoneFixFlg01", ZYPConstant.FLG_OFF_N);
        SVC_TM_EVENTTMsgArray outArray = (SVC_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            svcTmEventCd = (String) outArray.no(0).svcTmEventCd.getValue();
        }
        return svcTmEventCd;
    }

    // START 2016/11/21 CSA QC#15782
    /**
     * getSvcTmEventCdForLaborPhoneFix
     * @param glblCmpyCd String
     * @return svcTmEventCd
     */
    private String getSvcTmEventCdForLaborPhoneFix(String glblCmpyCd) {
        String svcTmEventCd = null;
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("phoneFixFlg01", ZYPConstant.FLG_ON_Y);
        SVC_TM_EVENTTMsgArray outArray = (SVC_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            svcTmEventCd = (String) outArray.no(0).svcTmEventCd.getValue();
        }
        return svcTmEventCd;
    }
    // END 2016/11/21 CSA QC#15782

    /**
     * getSvcTmEventCdForTravel
     * @param glblCmpyCd String
     * @return svcTmEventCd
     */
    private String getSvcTmEventCdForTravel(String glblCmpyCd) {
        String svcTmEventCd = null;
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inclTrvlTmFlg01", ZYPConstant.FLG_ON_Y);
        SVC_TM_EVENTTMsgArray outArray = (SVC_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            svcTmEventCd = (String) outArray.no(0).svcTmEventCd.getValue();
        }

        return svcTmEventCd;
    }

    /**
     * getSvcTmEventCdForModification
     * @param glblCmpyCd String
     * @return svcTmEventCd
     */
    private String getSvcTmEventCdForModification(String glblCmpyCd) {
        String svcTmEventCd = null;
        SVC_TM_EVENTTMsg inMsg = new SVC_TM_EVENTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("inclLborTmFlg01", ZYPConstant.FLG_OFF_N);
        inMsg.setConditionValue("inclTrvlTmFlg01", ZYPConstant.FLG_OFF_N);
        SVC_TM_EVENTTMsgArray outArray = (SVC_TM_EVENTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            svcTmEventCd = (String) outArray.no(0).svcTmEventCd.getValue();
        }

        return svcTmEventCd;
    }

    /**
     * getSvcMblS21ValTxt
     * @param glblCmpyCd String
     * @param key String
     * @param type String
     * @return svcMblS21ValTxt
     */
    private String getSvcMblS21ValTxt(String glblCmpyCd, String key, String type) {
        String svcMblS21ValTxt = null;
        SVC_MBL_INTFC_XREFTMsg inMsg = new SVC_MBL_INTFC_XREFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMblIntfcKeyTxt, key);
        setValue(inMsg.svcMblIntfcValTxt, type);
        SVC_MBL_INTFC_XREFTMsg outList = (SVC_MBL_INTFC_XREFTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (outList != null) {
            svcMblS21ValTxt = (String) outList.svcMblS21ValTxt.getValue();
        }
        return svcMblS21ValTxt;
    }

    /**
     * Create Parameter for Supplemental API
     * @param param NSZC068001PMsg
     * @param num int
     * @return NSZC058001PMsg
     */
    private NSZC058001PMsg createSupplementalUpdateParam(NSZC068001PMsg param, int num) {
        NSZC058001PMsg apiPMsg = new NSZC058001PMsg();
        String svcSupplTaskNum = null;
        svcSupplTaskNum = getSupplementalTask((String) param.glblCmpyCd.getValue(), apiTechCd, (String) param.svcTaskNum.getValue());

        // mode = 1:create
        if (!hasValue(svcSupplTaskNum)) {
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.xxProcMd, NSZC058001Constant.MODE_NEW);

            String svcSupplTaskTpCd = null;
            svcSupplTaskTpCd = getSvcMblS21ValTxt((String) param.glblCmpyCd.getValue(), SVC_SUPPL_TASK_TP_CD, (String) param.CsaSupplementalTimeList.no(num).xtrnlCallTpRefTxt.getValue());
            setValue(apiPMsg.svcSupplTaskTpCd, svcSupplTaskTpCd);
            String supplementalStatus = getSupplementalStatus(param.CsaSupplementalTimeList.no(num));
            setValue(apiPMsg.svcSupplTaskStsCd, supplementalStatus);

            setValue(apiPMsg.techCd, apiTechCd);
            setValue(apiPMsg.svcTaskNum, param.svcTaskNum);

            // mod start 2016/06/02 CSA Defect#9320
            boolean itrptTaskFlg = isItrptTask(param.glblCmpyCd.getValue(), svcSupplTaskTpCd);
            // mod end 2016/06/02 CSA Defect#9320
            if (hasValue(param.CsaSupplementalTimeList.no(num).startTs) && param.CsaSupplementalTimeList.no(num).startTs.getValue().length() >= TS_LEN) {
                // mod start 2016/06/02 CSA Defect#9320
                SvcTimeZoneInfo tzSupplFrom = null;
                if (itrptTaskFlg) {
                    tzSupplFrom = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.CsaSupplementalTimeList.no(num).startTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap
                            .get(POST_CD));
                } else {
                    tzSupplFrom = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.CsaSupplementalTimeList.no(num).startTs.getValue(), apiTechCd);
                }
                // mod end 2016/06/02 CSA Defect#9320
                if (tzSupplFrom != null) {
                    String svcSupplFromDt = tzSupplFrom.getDateTime().substring(0, DATE_END_POS);
                    String svcSupplFromTm = tzSupplFrom.getDateTime().substring(DATE_END_POS, TS_LEN);
                    setValue(apiPMsg.svcSupplFromDt, svcSupplFromDt);
                    setValue(apiPMsg.svcSupplFromTm, svcSupplFromTm);
                }
            }
            if (hasValue(param.CsaSupplementalTimeList.no(num).endTs) && param.CsaSupplementalTimeList.no(num).endTs.getValue().length() >= TS_LEN) {
                // mod start 2016/06/02 CSA Defect#9320
                SvcTimeZoneInfo tzSupplTo = null;
                if (itrptTaskFlg) {
                    tzSupplTo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.CsaSupplementalTimeList.no(num).endTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
                } else {
                    tzSupplTo = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.CsaSupplementalTimeList.no(num).endTs.getValue(), apiTechCd);
                }
                // mod end 2016/06/02 CSA Defect#9320
                if (tzSupplTo != null) {
                    String svcSupplToDt = tzSupplTo.getDateTime().substring(0, DATE_END_POS);
                    String svcSupplToTm = tzSupplTo.getDateTime().substring(DATE_END_POS, TS_LEN);
                    setValue(apiPMsg.svcSupplToDt, svcSupplToDt);
                    setValue(apiPMsg.svcSupplToTm, svcSupplToTm);
                }
            }
            setValue(apiPMsg.svcTrvlTmNum, param.CsaSupplementalTimeList.no(num).svcTrvlTmNum);
            setValue(apiPMsg.cratPsnCd, apiTechCd);

            // mode = 2:update
        } else {
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.xxProcMd, NSZC058001Constant.MODE_UPDATE);
            setValue(apiPMsg.svcSupplTaskNum, svcSupplTaskNum);
            setValue(apiPMsg.svcTaskNum, param.svcTaskNum);
            String supplementalStatus = getSupplementalStatus(param.CsaSupplementalTimeList.no(num));
            setValue(apiPMsg.svcSupplTaskStsCd, supplementalStatus);
            if (hasValue(param.CsaSupplementalTimeList.no(num).endTs) && param.CsaSupplementalTimeList.no(num).endTs.getValue().length() >= TS_LEN) {
                Map<String, Object> shipToCustMap = getShipToCustBySvcSupplTaskNum(param.glblCmpyCd.getValue(), svcSupplTaskNum);
                if (shipToCustMap != null) {
                    SvcTimeZoneInfo tzEndTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.CsaSupplementalTimeList.no(num).endTs.getValue(), (String) shipToCustMap.get(CTRY_CD),
                            (String) shipToCustMap.get(POST_CD));
                    if (tzEndTs != null) {
                        String cnvtEndTs = tzEndTs.getDateTime();
                        setValue(apiPMsg.svcSupplToDt, S21StringUtil.subStringByLength(cnvtEndTs, DATE_START_POS, DATE_END_POS));
                        setValue(apiPMsg.svcSupplToTm, S21StringUtil.subStringByLength(cnvtEndTs, TIME_START_POS, TIME_END_POS));
                    }
                } else {
                    SvcTimeZoneInfo tzSupplTo = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, param.CsaSupplementalTimeList.no(num).endTs.getValue(), apiTechCd);
                    if (tzSupplTo != null) {
                        String svcSupplToDt = tzSupplTo.getDateTime().substring(0, DATE_END_POS);
                        String svcSupplToTm = tzSupplTo.getDateTime().substring(DATE_END_POS, TS_LEN);
                        setValue(apiPMsg.svcSupplToDt, svcSupplToDt);
                        setValue(apiPMsg.svcSupplToTm, svcSupplToTm);
                    }
                }
            }

            setValue(apiPMsg.svcTrvlTmNum, param.CsaSupplementalTimeList.no(num).svcTrvlTmNum);
            setValue(apiPMsg.updPsnCd, apiTechCd);
        }

        return apiPMsg;
    }
    private boolean isItrptTask(String glblCmpyCd, String svcSupplTaskTpCd) {
        SVC_SUPPL_TASK_TPTMsg svcSupplTaskTpTMsg = new SVC_SUPPL_TASK_TPTMsg();
        setValue(svcSupplTaskTpTMsg.glblCmpyCd, glblCmpyCd);
        setValue(svcSupplTaskTpTMsg.svcSupplTaskTpCd, svcSupplTaskTpCd);
        svcSupplTaskTpTMsg = (SVC_SUPPL_TASK_TPTMsg) S21CodeTableAccessor.findByKey(svcSupplTaskTpTMsg);
        if (svcSupplTaskTpTMsg == null) {
            return false;
        }
        return ZYPConstant.FLG_ON_Y.equals(svcSupplTaskTpTMsg.svcItrptFlg.getValue());
    }
    /**
     * getSupplementalTask
     * @param glblCmpyCd String
     * @param techCd String
     * @param svcTaskNum String
     * @return svcSupplTaskNum
     */
    private String getSupplementalTask(String glblCmpyCd, String techCd, String svcTaskNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);
        param.put("techCd", techCd);
        param.put("svcSupplTaskStsCd", SVC_TASK_STS.IN_PROCESS);

        String svcSupplTaskNum = (String) this.ssmBatchClient.queryObject("getSupplementalTask", param);
        return svcSupplTaskNum;
    }

// START  02/19/2016 T.Iwamoto [QC#4108, DEL]
//    /**
//     * getSupplementalStatus
//     * @param glblCmpyCd String
//     * @param svcTaskStsNm String
//     * @return spplmntlSts
//     */
//    private String getSupplementalStatus(String glblCmpyCd, String svcTaskStsNm) {
//
//        String spplmntlSts = null;
//        SVC_TASK_STSTMsg inMsg = new SVC_TASK_STSTMsg();
//        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("xtrnlStsRefTxt01", svcTaskStsNm);
//        SVC_TASK_STSTMsgArray outArray = (SVC_TASK_STSTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
//        if (outArray.getValidCount() > 0) {
//            spplmntlSts = (String) outArray.no(0).svcTaskStsCd.getValue();
//        }
//        return spplmntlSts;
//    }
// END  02/19/2016 T.Iwamoto [QC#4108, DEL]

    /**
     * getSupplementalStatus
     * @param spplPMsg
     * @return status
     */
    private String getSupplementalStatus(NSZC068001_CsaSupplementalTimeListPMsg spplPMsg) {
        if (hasValue(spplPMsg.startTs) && hasValue(spplPMsg.endTs)) {
            return SVC_TASK_STS.CLOSED;
        }
        return SVC_TASK_STS.IN_PROCESS;
    }

    /**
     * Create Parameter for TSS Escalation API
     * @param param NSZC068001PMsg
     * @return NSZC073001PMsg
     */
    private NSZC073001PMsg createTssEscalationParam(NSZC068001PMsg param) {
        NSZC073001PMsg apiPMsg = new NSZC073001PMsg();
        if (param.CsaTtsEscalationList.getValidCount() > 0) {
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.svcTaskNum, param.svcTaskNum);
            setValue(apiPMsg.esclTpTxt, param.CsaTtsEscalationList.no(0).esclTpTxt);
            setValue(apiPMsg.dsAcctNm, (String) rcvTaskInfoMap.get(DS_ACCT_NM));
            setValue(apiPMsg.firstLineAddr, (String) rcvTaskInfoMap.get(FIRST_LINE_ADDR));
            setValue(apiPMsg.mdlNm, (String) rcvTaskInfoMap.get(MDL_NM));
            setValue(apiPMsg.serNum, (String) rcvTaskInfoMap.get(SER_NUM));
            setValue(apiPMsg.techNm, param.CsaTtsEscalationList.no(0).techNm);
            setValue(apiPMsg.cellPhoNum, param.CsaTtsEscalationList.no(0).cellPhoNum);
            setValue(apiPMsg.xtrnlPblmTpRefTxt, param.CsaTtsEscalationList.no(0).xtrnlPblmTpRefTxt);
            setValue(apiPMsg.xtrnlPblmLocTpRefTxt, param.CsaTtsEscalationList.no(0).xtrnlPblmLocTpRefTxt);
            setValue(apiPMsg.xtrnlPblmCrctTpRefTxt, param.CsaTtsEscalationList.no(0).xtrnlPblmCrctTpRefTxt);
            setValue(apiPMsg.xtrnlPblmRsnTpRefTxt, param.CsaTtsEscalationList.no(0).xtrnlPblmRsnTpRefTxt);
            setValue(apiPMsg.helpDeskTktNum, param.CsaTtsEscalationList.no(0).helpDeskTktNum);
            setValue(apiPMsg.svcCmntTxt, param.CsaTtsEscalationList.no(0).svcCmntTxt);
            setValue(apiPMsg.attDataDescTxt, param.CsaTtsEscalationList.no(0).attDataDescTxt);
            setValue(apiPMsg.techSprtGrpEmlAddr, param.CsaTtsEscalationList.no(0).techSprtGrpEmlAddr);
// START  02/19/2016 T.Iwamoto [QC#4108, MOD]
            for (int i = 0; i < param.CsaPartsUsedList.getValidCount(); i++) {
                setValue(apiPMsg.xxUsedPartsList.no(i).mdseCd, param.CsaPartsUsedList.no(i).mdseCd);
            }
            apiPMsg.xxUsedPartsList.setValidCount(param.CsaPartsUsedList.getValidCount());
// END  02/19/2016 T.Iwamoto [QC#4108, MOD]
        }
        return apiPMsg;
    }

    /**
     * Create Parameter for Receive Field Request API
     * @param param NSZC068001PMsg
     * @return NSZC075001PMsg
     */
    private NSZC075001PMsg createReceiveFieldRequestParam(NSZC068001PMsg param) {
        NSZC075001PMsg apiPMsg = new NSZC075001PMsg();
        if (param.CsaFieldRequestList.getValidCount() > 0) {
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.fldRqstSrcPgmCd, NSZC075001Constant.FLD_RQST_SRC_PGM_CLICK);
            setValue(apiPMsg.serNum, param.CsaFieldRequestList.no(0).serNum);
            setValue(apiPMsg.dsSvcCallTpCd, getDsSvcCallTpCd(param.glblCmpyCd.getValue(), param.CsaFieldRequestList.no(0).xtrnlCallTpRefTxt.getValue()));
//            setValue(apiPMsg.svcPblmTpCd, (String) rcvTaskInfoMap.get(SVC_PBLM_TP_CD));
            setValue(apiPMsg.svcPblmTpCd, param.CsaFieldRequestList.no(0).xtrnlPblmTpRefTxt);//TODO
            setValue(apiPMsg.psnCd, param.CsaFieldRequestList.no(0).techCd);
            setValue(apiPMsg.xxAllPsnNm, param.CsaFieldRequestList.no(0).techNm);
            setValue(apiPMsg.svcCmntTxt, param.CsaFieldRequestList.no(0).svcCmntTxt);
            // add start 2016/06/17 QC#9677
            setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
            // add end 2016/06/17 QC#9677
            // START 2017/02/22 K.Kojima [QC#16301,ADD]
            setValue(apiPMsg.xxRqstFlg_NC, ZYPConstant.FLG_ON_Y);
            // END 2017/02/22 K.Kojima [QC#16301,ADD]
        }

        return apiPMsg;
    }

    /**
     * getDsSvcCallTpCd
     * @param glblCmpyCd
     * @param xtrnlCallTpRefTxt
     * @return dsSvcCallTpCd
     */
    private String getDsSvcCallTpCd(String glblCmpyCd, String xtrnlCallTpRefTxt) {
        String dsSvcCallTpCd = null;
        DS_SVC_CALL_TPTMsg inMsg = new DS_SVC_CALL_TPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("xtrnlCallTpRefTxt01", xtrnlCallTpRefTxt);
        DS_SVC_CALL_TPTMsgArray outArray = (DS_SVC_CALL_TPTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            dsSvcCallTpCd = outArray.no(0).dsSvcCallTpCd.getValue();
        }
        return dsSvcCallTpCd;
    }

    // mod start 2016/11/22 CSA QC#16145
    /**
     * Create Parameter for Parts Request API
     * @param param NSZC068001PMsg
     * @param num int
     * @return List<NPZC117001PMsg>
     */
    private List<NPZC117001PMsg> createPartsRequestParam(NSZC068001PMsg param, int num) {

        NPZC117001PMsg apiPMsg = new NPZC117001PMsg();
        List<NPZC117001PMsg> apiPMsgList = new ArrayList<NPZC117001PMsg>();
        // Validation = On
        // mod start 2016/06/02 CSA Defect#8686
// mod start 2016/11/18 CSA Defect#16061
//        if (ZYPConstant.FLG_ON_Y.equals(param.CsaPartsOrderList.no(0).xxItemVldFlg.getValue()) || param.CsaPartsOrderList.no(0).mdseNm.getValue().length() < MDSE_LEN) {
        if (ZYPConstant.FLG_ON_Y.equals(param.CsaPartsOrderList.no(num).xxItemVldFlg.getValue())) {
// mod end 2016/11/18 CSA Defect#16061
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.vldActCd, ZYPConstant.FLG_ON_Y);
            setValue(apiPMsg.mdseDescShortTxt_01, param.CsaPartsOrderList.no(num).mdseDescShortTxt); //TODO (Change ShortTxt)
            setValue(apiPMsg.clickKeyTxt, param.CsaPartsOrderList.no(num).clickKeyTxt_P1);
            apiPMsgList.add(apiPMsg);
        } else {
            // Validation = Off
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.vldActCd, ZYPConstant.FLG_OFF_N);
            setValue(apiPMsg.prchReqTpNm, param.CsaPartsOrderList.no(num).prchReqTpNm);
            // mod start 2016/10/05 CSA Defect#14803
            setValue(apiPMsg.lgscOrdRmkTxt, param.CsaPartsOrderList.no(num).lgscOrdRmkTxt);
            // mod end 2016/10/05 CSA Defect#14803
            setValue(apiPMsg.shipToCustNm, param.CsaPartsOrderList.no(num).shipToCustNm);
            setValue(apiPMsg.firstInvtyOrdCmntTxt, param.CsaPartsOrderList.no(num).firstInvtyOrdCmntTxt);
            setValue(apiPMsg.svcTaskNum, param.svcTaskNum);
            setValue(apiPMsg.techTocCd, apiTechCd);
            setValue(apiPMsg.destRtlWhCd, param.CsaPartsOrderList.no(num).destRtlWhCd);
// del start 2016/11/17 CSA Defect#16060
//            apiPMsgList.add(apiPMsg);
// del end   2016/11/17 CSA Defect#16060
            int partsNum = 0;
            for (int i = 0; i < param.PartsLookupList.getValidCount(); i++) {
                if (param.CsaPartsOrderList.no(num).clickKeyTxt_P1.getValue().equals(param.PartsLookupList.no(i).clickKeyTxt_P1.getValue())) {
                    setValue(apiPMsg.SearchPartsList.no(partsNum).mdseCd_02, param.PartsLookupList.no(i).mdseCd);
                    // START 2023/11/07 K.Kitachi [QC#61648, ADD]
                    if (hasValue(param.PartsLookupList.no(i).mdseCd) && !hasValue(param.PartsLookupList.no(i).mdseDescShortTxt)) {
                        String mdseDescShortTxt = getMdseDescShortTxt(apiPMsg.glblCmpyCd.getValue(), param.PartsLookupList.no(i).mdseCd.getValue());
                        if (hasValue(mdseDescShortTxt)) {
                            setValue(param.PartsLookupList.no(i).mdseDescShortTxt, mdseDescShortTxt);
                        }
                    }
                    setValue(apiPMsg.SearchPartsList.no(partsNum).mdseDescShortTxt_02, param.PartsLookupList.no(i).mdseDescShortTxt);
                    // END 2023/11/07 K.Kitachi [QC#61648, ADD]
                    setValue(apiPMsg.SearchPartsList.no(partsNum).prchReqQty, param.PartsLookupList.no(i).prchReqQty);
                    partsNum++;
                }
            }
//            apiPMsg.SearchPartsList.setValidCount(param.PartsLookupList.getValidCount());
            apiPMsg.SearchPartsList.setValidCount(partsNum);
            setValue(apiPMsg.clickKeyTxt, param.CsaPartsOrderList.no(num).clickKeyTxt_P1);
            // START 2017/02/22 K.Kojima [QC#16301,ADD]
            setValue(apiPMsg.xxRqstFlg_NC, ZYPConstant.FLG_ON_Y);
            // END 2017/02/22 K.Kojima [QC#16301,ADD]
            // QC#58229 Add
            setValue(apiPMsg.spclInstnCmntTxt, param.CsaPartsOrderList.no(num).spclInstnCmntTxt);
            apiPMsgList.add(apiPMsg);
            // mod end 2016/06/02 CSA Defect#8686
        }
        return apiPMsgList;
    }
    // mod end 2016/11/22 CSA QC#16145
    // add start 2016/01/07 CSA Defect#2558
    private String getSvcPblmLocTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmLocTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmLocTpCd", param);
    }

    private String getSvcPblmCrctTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmCrctTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmCrctTpCd", param);
    }

    private String getSvcPblmRsnTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmRsnTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmRsnTpCd", param);
    }
    // add end 2016/01/07 CSA Defect#2558

    // START 2016/11/10 [QC#15732,ADD]
    private String getIwrStsCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlIwrStsRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getIwrStsCd", param);
    }
    // END 2016/11/10 [QC#15732,ADD]

    // add start 2016/03/31 CSA Defect#6270
    private String getSvcPblmTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmTpRefTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmTpCd", param);
    }
    // add end 2016/03/31 CSA Defect#6270

    // START  02/09/2016 Y.Takeno [QC#3727, ADD]
    private Map<String, Object> getShipToCustBySvcSupplTaskNum(String glblCmpyCd, String svcSupplTaskNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcSupplTaskNum", svcSupplTaskNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getShipToCustBySvcSupplTaskNum", param);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }

        return null;
    }
    // END   02/09/2016 Y.Takeno [QC#3727, ADD]

    // START   02/19/2016 T.Iwamoto [QC#4108, ADD]
    private String getSvcRgCd(String glblCmpyCd, String svcRgDescTxt) {
        SVC_RGTMsg inMsg = new SVC_RGTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcRgDescTxt01", svcRgDescTxt);
        SVC_RGTMsgArray outArray = (SVC_RGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        String svcRgCd = outArray.no(0).svcRgPk.getValue().toString();
        if (svcRgCd.length() > RG_LEN) {
            return null;
        }
        return svcRgCd;
    }


    // add 2016/04/20 CSA Defect#4467
    private Map<String, Object> getMdlInfo(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        if (!hasValue(svcConfigMstrPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMdlInfo", param);
    }

    // add start 2016/06/17 QC#9677
    private void callSendTaskInfoApi(S21ApiMessageMap msgMap, NSZC068001PMsg param, List<String> svcTaskNumList) {
        String follwUpTaskNum = getFollowUpTask(param.glblCmpyCd.getValue(), param.svcTaskNum.getValue());
        if (hasValue(follwUpTaskNum)) {
            svcTaskNumList.add(follwUpTaskNum);
        }
        if (svcTaskNumList.size() == 0) {
            return;
        }

        List<NSZC107001PMsg> apiPMsgList = new ArrayList<NSZC107001PMsg>();
        for (String svcTaskNum : svcTaskNumList) {
            NSZC107001PMsg apiPMsg = new NSZC107001PMsg();
            setValue(apiPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(apiPMsg.slsDt, param.slsDt);
            setValue(apiPMsg.svcTaskNum, svcTaskNum);
            apiPMsgList.add(apiPMsg);
        }

        NSZC107001 api = new NSZC107001();
        api.execute(apiPMsgList, this.onBatchType);

        for (NSZC107001PMsg apiPMsg : apiPMsgList) {
            if (S21ApiUtil.isXxMsgId(apiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
                for (S21ApiMessage msg : msgList) {
                    if (hasValue(msg.getXxMsgid()) && msg.getXxMsgid().endsWith("E")) {
                        msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                    }
                }
            }
        }
    }
    // add end 2016/06/17 QC#9677

    // START 2019/05/31 [QC#50574,ADD]
    private String getFollowUpPartCall(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        String[] DS_SVC_CALL_TP_LIST_PART_CALL = new String[] {DS_SVC_CALL_TP.PART_CALL, DS_SVC_CALL_TP.HIGH_PART,
                DS_SVC_CALL_TP.HIGH_PART_LOCAL_SOURCE, DS_SVC_CALL_TP.PART, DS_SVC_CALL_TP.HIGH_PART_2  };
        paramMap.put("dsSvcCallTpList", DS_SVC_CALL_TP_LIST_PART_CALL);
        return (String) this.ssmBatchClient.queryObject("getFollUpPartCall", paramMap);
    }
    // END 2019/05/31 [QC#50574,ADD]
    // add start 2016/06/17 QC#9677
    private String getFollowUpTask(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (String) this.ssmBatchClient.queryObject("getFollUpTask", paramMap);
    }
    // add end 2016/06/17 QC#9677
    // add start 2016/07/29 CSA Defect#10483
    private String getUserId(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (String) this.ssmBatchClient.queryObject("getUserId", paramMap);
    }
    // add end 2016/07/29 CSA Defect#10483

    // add start 2016/11/08 CSA Defect#15780
    private boolean isFsrUpdateForNote(NSZC068001PMsg param) {
        if (param.svcMemoList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private NSZC043001PMsg createFsrUpdateApiUpdParamForNote(NSZC068001PMsg param) {
        NSZC043001PMsg fsrUpdateApiPMsg = new NSZC043001PMsg();

        setValue(fsrUpdateApiPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(fsrUpdateApiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        setValue(fsrUpdateApiPMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(fsrUpdateApiPMsg.slsDt, param.slsDt);
        setValue(fsrUpdateApiPMsg.userId, param.svcMemoList.no(0).lastUpdUsrId);

        setValue(fsrUpdateApiPMsg.svcMachMstrPk, (BigDecimal) rcvTaskInfoMap.get(SVC_MACH_MSTR_PK));
        FSRTMsg fsrTmsg = getFsr(param.glblCmpyCd.getValue(), (String) rcvTaskInfoMap.get(FSR_NUM));
        if (fsrTmsg != null) {
            setValue(fsrUpdateApiPMsg.svcCallIncdtDt, fsrTmsg.svcCallIncdtDt);
            setValue(fsrUpdateApiPMsg.svcCallIncdtTm, fsrTmsg.svcCallIncdtTm);
        }
        // START 2017/12/14 M.Kidokoro [QC#22223,ADD]
        setCrCardCustRefNum(fsrUpdateApiPMsg);
        // END 2017/12/14 M.Kidokoro [QC#22223,ADD]

        NSZC043001_taskListPMsg taskListPMsg = fsrUpdateApiPMsg.taskList.no(0);
        setValue(taskListPMsg.svcTaskNum, param.svcTaskNum);
        setValue(taskListPMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        SVC_TASKTMsg svcTaskTmsg = getSvcTask(param.glblCmpyCd.getValue(), param.svcTaskNum.getValue());
        if (svcTaskTmsg != null) {
            setValue(taskListPMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
            setValue(taskListPMsg.erlStartTs, svcTaskTmsg.erlStartTs);
            setValue(taskListPMsg.lateStartTs, svcTaskTmsg.lateStartTs);
        }
        fsrUpdateApiPMsg.taskList.setValidCount(1);

        for (int i = 0; i < param.svcMemoList.getValidCount(); i++) {
            NSZC043001_svcMemoListPMsg svcMemoListPMsg = fsrUpdateApiPMsg.svcMemoList.no(i);
            setValue(svcMemoListPMsg.svcMemoTpCd, getSvcMemoTpCd(param.glblCmpyCd.getValue(), param.svcMemoList.no(i).svcMemoTpDescTxt.getValue()));
            setValue(svcMemoListPMsg.svcCmntTxt, param.svcMemoList.no(i).svcCmntTxt);
        }
        fsrUpdateApiPMsg.svcMemoList.setValidCount(param.svcMemoList.getValidCount());

        return fsrUpdateApiPMsg;
    }

    private FSRTMsg getFsr(String glblCmpyCd, String fsrNum) {
        FSRTMsg inMsg = new FSRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.fsrNum, fsrNum);
        return (FSRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private String getSvcMemoTpCd(String glblCmpyCd, String value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMemoTpDescTxt", value);
        return (String) this.ssmBatchClient.queryObject("getSvcMemoTpCd", param);
    }
    // add end 2016/11/08 CSA Defect#15780

    // START 2016/12/15 K.Kojima [QC#16436,ADD]
    /**
     * insertServiceMemo
     * @param pMsg NSZC068001PMsg
     */
    private void insertServiceMemo(NSZC068001PMsg pMsg) {
        for (int i = 0; i < pMsg.svcMemoList.getValidCount(); i++) {
            insertServiceMemo(pMsg.svcMemoList.no(i), pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue(), (String) rcvTaskInfoMap.get(FSR_NUM));
        }
    }

    /**
     * insertServiceMemo
     * @param svcMemoListPMsg NSZC068001_svcMemoListPMsg
     * @param glblCmpyCd String
     * @param svcTaskNum String
     * @param fsrNum String
     */
    private void insertServiceMemo(NSZC068001_svcMemoListPMsg svcMemoListPMsg, String glblCmpyCd, String svcTaskNum, String fsrNum) {
        SVC_MEMOTMsg tMsg = new SVC_MEMOTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
        setValue(tMsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        setValue(tMsg.svcMemoTpCd, getSvcMemoTpCd(glblCmpyCd, svcMemoListPMsg.svcMemoTpDescTxt.getValue()));
        setValue(tMsg.svcCmntTxt, svcMemoListPMsg.svcCmntTxt.getValue());
        setValue(tMsg.svcTaskNum, svcTaskNum);
        setValue(tMsg.fsrNum, fsrNum);
        setValue(tMsg.lastUpdUsrId, svcMemoListPMsg.lastUpdUsrId.getValue());
        // mod start 2019/03/13 QC#30697
        // mod start 2019/03/29 QC#30697-2
        setValue(tMsg.lastUpdTs, svcMemoListPMsg.lastUpdTs.getValue());
        //SvcTimeZoneInfo tzLastUpdTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, svcMemoListPMsg.lastUpdTs.getValue(), (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
        //if (tzLastUpdTs != null) {
        //    setValue(tMsg.lastUpdTs, tzLastUpdTs.getDateTime());
        //}
        // mod end 2019/03/29 QC#30697-2
        // mod end 2019/03/13 QC#30697
        // add start 2016/12/21 CSA Defect#16514
        setValue(tMsg.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
        // add end 2016/12/21 CSA Defect#16514
        S21ApiTBLAccessor.insert(tMsg);
    }
    // END 2016/12/15 K.Kojima [QC#16436,ADD]

    // START 2017/02/22 K.Kojima [QC#16301,ADD]
    // START 2023/11/07 K.Kitachi [QC#61648, MOD]
//    private void insertClickTechOrdByOrderPartsDenied(NSZC068001PMsg param) {
    private void insertClickTechOrdByOrderPartsDenied(NSZC068001PMsg param, NPZC117001PMsg partsRequestApiParam) {
    // END 2023/11/07 K.Kitachi [QC#61648, MOD]
        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        EZDConnectionMgr.getInstance().rollback();

        DS_COND_CONSTTMsg reqStatusTMsg = getDsCondConstTMsg(param.glblCmpyCd.getValue(), GRP_ID_NPZC1170, REQ_STATUS);
        String prchReqStsNm = null;
        if (reqStatusTMsg != null) {
            prchReqStsNm = reqStatusTMsg.dsCondConstValTxt_02.getValue();
        }

        for (int orderCount = 0; orderCount < param.CsaPartsOrderList.getValidCount(); orderCount++) {

            NSZC068001_CsaPartsOrderListPMsg orderPMsg = param.CsaPartsOrderList.no(orderCount);
            String clickKeyTxt_Order = orderPMsg.clickKeyTxt_P1.getValue();

            for (int partsCount = 0; partsCount < param.PartsLookupList.getValidCount(); partsCount++) {

                NSZC068001_PartsLookupListPMsg partsPMsg = param.PartsLookupList.no(partsCount);
                String clickKeyTxt_Parts = partsPMsg.clickKeyTxt_P1.getValue();

                if (!clickKeyTxt_Order.equals(clickKeyTxt_Parts)) {
                    continue;
                }

                CLICK_TECH_ORD_PRTTMsg clickTechOrdPrtTMsg = new CLICK_TECH_ORD_PRTTMsg();
                setValue(clickTechOrdPrtTMsg.glblCmpyCd, param.glblCmpyCd);
                setValue(clickTechOrdPrtTMsg.clickTechOrdPrtPk, ZYPOracleSeqAccessor.getNumberBigDecimal(CLICK_TECH_ORD_PRT_SQ));
                setValue(clickTechOrdPrtTMsg.techOrdPrtSq, ZYPOracleSeqAccessor.getNumberBigDecimal(TECH_ORD_PRT_SQ));
                setValue(clickTechOrdPrtTMsg.vldActCd, orderPMsg.xxItemVldFlg);
                setValue(clickTechOrdPrtTMsg.prchReqTpNm, orderPMsg.prchReqTpNm);
                setValue(clickTechOrdPrtTMsg.lgscOrdRmkTxt, orderPMsg.lgscOrdRmkTxt);
                setValue(clickTechOrdPrtTMsg.shipToCustNm, orderPMsg.shipToCustNm);
                setValue(clickTechOrdPrtTMsg.firstInvtyOrdCmntTxt, orderPMsg.firstInvtyOrdCmntTxt);
                setValue(clickTechOrdPrtTMsg.svcTaskNum, orderPMsg.svcTaskNum);
                setValue(clickTechOrdPrtTMsg.rqstTechTocCd, orderPMsg.techCd);
                setValue(clickTechOrdPrtTMsg.prchReqStsNm, prchReqStsNm);
                setValue(clickTechOrdPrtTMsg.prchReqNum, orderPMsg.prchReqNum);
                setValue(clickTechOrdPrtTMsg.destRtlWhCd, orderPMsg.destRtlWhCd);
                setValue(clickTechOrdPrtTMsg.prchReqQty, partsPMsg.prchReqQty);
                setValue(clickTechOrdPrtTMsg.mdseCd, partsPMsg.mdseCd);
                setValue(clickTechOrdPrtTMsg.mdseDescShortTxt, partsPMsg.mdseDescShortTxt);
                setValue(clickTechOrdPrtTMsg.procStsCd, PROC_STS.IN_COMPLETED);
                setValue(clickTechOrdPrtTMsg.clickKeyTxt, clickKeyTxt_Parts);

                S21ApiTBLAccessor.insert(clickTechOrdPrtTMsg);
            }
        }

        // START 2023/11/07 K.Kitachi [QC#61648, ADD]
        if (isSendErrMail(partsRequestApiParam)) {
            // Send error mail
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("glblCmpyCd", partsRequestApiParam.glblCmpyCd.getValue());
            queryParams.put("rqstTechTocCd", partsRequestApiParam.techTocCd.getValue());
            String techEmlAddr = (String) this.ssmBatchClient.queryObject("getTechEmlAddr", queryParams);
            if (ZYPCommonFunc.hasValue(techEmlAddr)) {
                S21MailAddress toAddr = new S21MailAddress(param.glblCmpyCd.getValue(), techEmlAddr);
                List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
                toAddrList.add(toAddr);

                StringBuffer mailMessage = new StringBuffer();
                for (int i = 0; i < partsRequestApiParam.SearchPartsList.getValidCount(); i++) {
                    if (i != 0) {
                        mailMessage.append(MAIL_SPACE);
                        mailMessage.append(MAIL_SPACE);
                    }
                    mailMessage.append(String.format(MAIL_ITEM_CODE_FORMAT, partsRequestApiParam.SearchPartsList.no(i).mdseCd_02.getValue()));
                    mailMessage.append(MAIL_SPACE);
                    mailMessage.append(String.format(MAIL_ITEM_DESCRIPTIONE_FORMAT, partsRequestApiParam.SearchPartsList.no(i).mdseDescShortTxt_02.getValue()));
                    mailMessage.append(MAIL_SPACE);
                    mailMessage.append(partsRequestApiParam.SearchPartsList.no(i).prchReqQty.getValue());
                    mailMessage.append(MAIL_LINE_SEPARATOR);
                }

                sendErrMail(partsRequestApiParam.glblCmpyCd.getValue(), toAddrList, mailMessage.toString());
            }
        }
        // END 2023/11/07 K.Kitachi [QC#61648, ADD]

        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        EZDConnectionMgr.getInstance().commit();
    }

    private DS_COND_CONSTTMsg getDsCondConstTMsg(String gcc, String grpId, String constCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        setValue(dsCondConstTMsg.glblCmpyCd, gcc);
        setValue(dsCondConstTMsg.dsCondConstGrpId, grpId);
        setValue(dsCondConstTMsg.dsCondConstCd, constCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }

    // END 2017/02/22 K.Kojima [QC#16301,ADD]

    // add start 2017/09/06 QC#15134
    private String getDsMtrReadTpCd(String dsMtrReadTpNm) {
        if (!hasValue(dsMtrReadTpNm)) {
            return DS_MTR_READ_TP.ACTUAL;
        }
        if (CLICK_MTR_READ_TP_REPLACE.equals(dsMtrReadTpNm)) {
            return DS_MTR_READ_TP.EXCHANGE_METER_TO;
        } else if (CLICK_MTR_READ_TP_ROLLOVER.equals(dsMtrReadTpNm)) {
            return DS_MTR_READ_TP.ROLLOVER;
        }
        return DS_MTR_READ_TP.ACTUAL;
    }
    // add end 2017/09/06 QC#15134

    // Add Start 2017/10/15 QC#21648
    private boolean callPartsRequest(S21ApiMessageMap msgMap, NSZC068001PMsg param) {
        // Parts Search API
        if (isPartsRequest(param)) {
            final NPZC117001 callPartsRequestApi = new NPZC117001();
            // mod start 2016/11/22 CSA QC#16145
            for (int i = 0; i < param.CsaPartsOrderList.getValidCount(); i++) {
                List<NPZC117001PMsg> callPartsRequestParamList = createPartsRequestParam(param, i);
                callPartsRequestApi.execute(callPartsRequestParamList, this.onBatchType);

                for (NPZC117001PMsg callPartsRequestParam : callPartsRequestParamList) {
                    if (callPartsRequestParam.xxMsgIdList.getValidCount() != 0) {
                        // START 2023/09/25 N.Takatsu [QC#61780, MOD]
//                        for (int j = 0; j < callPartsRequestParam.xxMsgIdList.getValidCount(); j++) {
//                            msgMap.addXxMsgIdWithPrm(callPartsRequestParam.xxMsgIdList.no(j).xxMsgId.getValue(), new String[] {callPartsRequestParam.xxMsgIdList.no(j).xxMsgPrmTxt_0.getValue(),
//                                    callPartsRequestParam.xxMsgIdList.no(j).xxMsgPrmTxt_1.getValue() });
//                        }
                        msgMap.addXxMsgId(NSZM1407W);
                        // END   2023/09/25 N.Takatsu [QC#61780, MOD]
                        // START 2023/11/07 K.Kitachi [QC#61648, MOD]
//                        insertClickTechOrdByOrderPartsDenied(param);
                        insertClickTechOrdByOrderPartsDenied(param, callPartsRequestParam);
                        // END 2023/11/07 K.Kitachi [QC#61648, MOD]
                        return false;
                    }
                    if (hasValue(callPartsRequestParam.prchReqNum)) {
                        setValue(param.CsaPartsOrderList.no(i).prchReqNum, callPartsRequestParam.prchReqNum);
                    }
                    if (hasValue(callPartsRequestParam.prchReqStsNm)) {
                        setValue(param.CsaPartsOrderList.no(i).prchReqStsNm, callPartsRequestParam.prchReqStsNm);
                    }
                    // mod start 2018/07/30 QC#20956
                    //int partsLookupCnt = param.PartsLookupList.getValidCount();
                    //for (int k = 0; k < callPartsRequestParam.SearchPartsList.getValidCount(); k++) {
                    //    setValue(param.PartsLookupList.no(partsLookupCnt).mdseCd, callPartsRequestParam.SearchPartsList.no(k).mdseCd_02);
                    //    setValue(param.PartsLookupList.no(partsLookupCnt).mdseDescShortTxt, callPartsRequestParam.SearchPartsList.no(k).mdseDescShortTxt_02);
                    //    partsLookupCnt++;
                    //}
                    //param.PartsLookupList.setValidCount(partsLookupCnt);
                    int k = 0;
                    for (int j = 0; j < param.PartsLookupList.getValidCount(); j++) {
                        if (callPartsRequestParam.SearchPartsList.getValidCount() <= k) {
                            break;
                        }
                        if (param.CsaPartsOrderList.no(i).clickKeyTxt_P1.getValue().equals(param.PartsLookupList.no(j).clickKeyTxt_P1.getValue())) {
                            setValue(param.PartsLookupList.no(j).mdseCd, callPartsRequestParam.SearchPartsList.no(k).mdseCd_02);
                            setValue(param.PartsLookupList.no(j).mdseDescShortTxt, callPartsRequestParam.SearchPartsList.no(k).mdseDescShortTxt_02);
                            k++;
                        } else {
                            continue;
                        }
                    }
                    // mod end 2018/07/30 QC#20956
                }
            }
        }
        return true;
    }
    // Add End 2017/10/15 QC#21648
    // START 2019/05/31 [QC#50574,ADD]
    private boolean updateFollowUpPartsCallTaskNum(S21ApiMessageMap msgMap, NSZC068001PMsg pMsg) {
        String follwUpPartCallTaskNum = getFollowUpPartCall(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
        if (!hasValue(follwUpPartCallTaskNum)) {
            return true;
        }

        for (int i = 0; i < pMsg.CsaPartsOrderList.getValidCount(); i++) {
            String prchReqNum = pMsg.CsaPartsOrderList.no(i).prchReqNum.getValue();

            List<Map<String, BigDecimal>> techOrdPrtPkList = getTechOrdPrtPkList(pMsg.glblCmpyCd.getValue(), prchReqNum);
            CLICK_TECH_ORD_PRTTMsg clickTechOrdPrtTMsg = new CLICK_TECH_ORD_PRTTMsg();
            BigDecimal techOrdPrtPk = null;
            for (Map<String, BigDecimal> techOrdPrtPkMap : techOrdPrtPkList) {
                techOrdPrtPk = techOrdPrtPkMap.get("CLICK_TECH_ORD_PRT_PK");
                clickTechOrdPrtTMsg = getTechOrdPrtForUpdate(pMsg.glblCmpyCd.getValue(), techOrdPrtPk);
                if (clickTechOrdPrtTMsg != null) {
                    setValue(clickTechOrdPrtTMsg.svcTaskNum, follwUpPartCallTaskNum);
                    S21ApiTBLAccessor.update(clickTechOrdPrtTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(clickTechOrdPrtTMsg.getReturnCode())) {
                        msgMap.addXxMsgIdWithPrm(NSZM0625E, new String[] {clickTechOrdPrtTMsg.getTableName() });
                        return false;
                    }
                }
            }

            PRCH_REQTMsg prchReqTMsg = getPrchReqForUpdate(pMsg.glblCmpyCd.getValue(), prchReqNum);
            if (prchReqTMsg != null) {
                setValue(prchReqTMsg.svcTaskNum, follwUpPartCallTaskNum);
                S21ApiTBLAccessor.update(prchReqTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqTMsg.getReturnCode())) {
                    msgMap.addXxMsgIdWithPrm(NSZM0625E, new String[] {prchReqTMsg.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private List<Map<String, BigDecimal>> getTechOrdPrtPkList(String glblCmpyCd, String prchReqNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("inCompleted", PROC_STS.IN_COMPLETED);
        return (List<Map<String, BigDecimal>>) this.ssmBatchClient.queryObjectList("getTechOrdPrtPk", param);
    }

    private CLICK_TECH_ORD_PRTTMsg getTechOrdPrtForUpdate(String glblCmpyCd, BigDecimal clickTechOrdPrtPk) {
        CLICK_TECH_ORD_PRTTMsg paramTMsg = new CLICK_TECH_ORD_PRTTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.clickTechOrdPrtPk, clickTechOrdPrtPk);
        return (CLICK_TECH_ORD_PRTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private PRCH_REQTMsg getPrchReqForUpdate(String glblCmpyCd, String prchReqNum) {
        PRCH_REQTMsg paramTMsg = new PRCH_REQTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.prchReqNum, prchReqNum);
        return (PRCH_REQTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }
    // END 2019/05/31 [QC#50574,ADD]
    // START 2017/12/14 M.Kidokoro [QC#22223,ADD]
    /**
     * setCrCardCustRefNum
     * @param pMsg NSZC043001PMsg
     * @return void
     */
    private void setCrCardCustRefNum(NSZC043001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("crCardTrxTpCd", CR_CARD_TRX_TP.SERVICE_REQUEST);
        param.put("firstTrxInfoTxt", pMsg.fsrNum.getValue());

        String rslt = (String) this.ssmBatchClient.queryObject("getCrCardCustRefNum", param);
        if (rslt != null) {
            setValue(pMsg.crCardCustRefNum, rslt);
        }
    }
    // END 2017/12/14 M.Kidokoro [QC#22223,ADD]

    // START 2018/01/29 U.Kim [QC#19702, ADD]
    /**
     * Sleep
     * @param millSec
     */
    private static void sleep(long millSec) {
        try {
            Thread.sleep(millSec);
        } catch (InterruptedException ie) {
        }
    }
    // END 2018/01/29 U.Kim [QC#19702, ADD]

    // START 2018/03/22 M.Naito [QC#18713, ADD]
    private static String uploadSignaFileToFS(String encodeFile, String svcTaskNum) {

        String uploadedFilePath = null;

        if (hasValue(encodeFile)) {
            // START 2022/12/01 K.Kitachi [QC#60882, ADD]
            S21StopWatch sw = new S21StopWatch();
            S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0680 : uploadSignaFileToFS Start." + " Task#" + svcTaskNum);
            sw.start();
            // END 2022/12/01 K.Kitachi [QC#60882, ADD]

            // Decode the encodedStringOfAttachFile
            byte[] byteArrayOfFileAfterDecodedBase64 = DatatypeConverter.parseBase64Binary(encodeFile);

            String uniqueFileNm = ZYPFileNameUtil.createUniqueFileNm("", svcTaskNum);
            uniqueFileNm += "." + SIGNA_FILE_TYPE;
            // QC#23385 Mod Start
            //uploadedFilePath = ZYPFileWriter.uploadAttachmentFileToFS(byteArrayOfFileAfterDecodedBase64, uniqueFileNm, INTFC_ID);
            if (EZDDBCICarrier.isBatchMode()) {
                uploadedFilePath = ZYPFileWriter.uploadAttachmentFileToFS(byteArrayOfFileAfterDecodedBase64, uniqueFileNm, INTFC_ID + "_" + EXEC_MODE_BATCH);
            } else {
                uploadedFilePath = ZYPFileWriter.uploadAttachmentFileToFS(byteArrayOfFileAfterDecodedBase64, uniqueFileNm, INTFC_ID + "_" + EXEC_MODE_ONLINE);
            }
            // QC#23385 Mod End

            // START 2022/12/01 K.Kitachi [QC#60882, ADD]
            sw.stop();
            S21InfoLogOutput.println("CHK_CPLT_TM : NSZC0680 : uploadSignaFileToFS End." + " Task#" + svcTaskNum);
            S21InfoLogOutput.println(String.format("CHK_CPLT_TM : NSZC0680 : uploadSignaFileToFS Elapsed Time [%s]", sw.getElapsedMilliSec()));
            // END 2022/12/01 K.Kitachi [QC#60882, ADD]
        }

        return uploadedFilePath;
    }
    // END 2018/03/22 M.Naito [QC#18713, ADD]

    // Add Start 2018/07/05 QC#27015
    private int getIntValueForNumConst(String key, String glblCmpyCd, int defVal) {

        if (this.numConstMap.containsKey(key)) {
            return this.numConstMap.get(key).intValue();
        }

        BigDecimal numConst = ZYPCodeDataUtil.getNumConstValue(key, glblCmpyCd);
        if (!hasValue(numConst)) {
            this.numConstMap.put(key, new BigDecimal(defVal));
            return defVal;
        }
        this.numConstMap.put(key, numConst);
        return numConst.intValue();
    }

    private boolean lockSvcTask(NSZC068001PMsg pMsg, int retryCount, int waitMsec) {
        int waitTime = waitMsec / 1000;

        // Service Task
        SVC_TASKTMsg svcTaskTMsg = null;
        for (int i = 0; i < retryCount; i++) {
            svcTaskTMsg = new SVC_TASKTMsg();
            setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(svcTaskTMsg.svcTaskNum, pMsg.svcTaskNum);
            try {
                svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcTaskTMsg);
            } catch (EZDDBRecordLockedException e) {
                svcTaskTMsg = getSvcTaskForUpdateWait(svcTaskTMsg, waitTime);
            }

            if (svcTaskTMsg != null) {
                break;
            }
        }

        if (svcTaskTMsg == null) {
            return false;
        }

        // FSR Visit
        FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
        fsrVisitTMsg.setSQLID("002");
        fsrVisitTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        fsrVisitTMsg.setConditionValue("svcTaskNum01", pMsg.svcTaskNum.getValue());
        FSR_VISITTMsgArray fsrVisitTMsgArray = new FSR_VISITTMsgArray();
        for (int i = 0; i < retryCount; i++) {
            try {
                fsrVisitTMsgArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(fsrVisitTMsg);
            } catch (EZDDBRecordLockedException e) {
                fsrVisitTMsgArray = getFsrVisitForUpdateWait(fsrVisitTMsg, waitTime);
            }

            if (fsrVisitTMsgArray != null && fsrVisitTMsgArray.getValidCount() > 0) {
                break;
            }
        }

        if (fsrVisitTMsgArray == null || fsrVisitTMsgArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    private SVC_TASKTMsg getSvcTaskForUpdateWait(SVC_TASKTMsg svcTaskTMsg, int waitTime) {
        try {
            return (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTaskTMsg, waitTime);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }

    private FSR_VISITTMsgArray getFsrVisitForUpdateWait(FSR_VISITTMsg fsrVisitTMsg, int waitTime) {
        try {
            return (FSR_VISITTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(fsrVisitTMsg, waitTime);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }
    // Add End 2018/07/05 QC#27015
    // QC#27882 Add Start
    private static String addTime(String startTs, BigDecimal rspTmMnAot) {

        Long tempRspTm = rspTmMnAot.longValue() * 60000;
        Date inParamDate = toDate(startTs, "yyyyMMddHHmmss");
        if (inParamDate == null) {
            return null;
        }

        Calendar tmpCalendar = Calendar.getInstance();
        tmpCalendar.setTime(inParamDate);
        tmpCalendar.add(Calendar.MILLISECOND, tempRspTm.intValue());

        return getDtFmtString(tmpCalendar.getTime(), "yyyyMMddHHmmss");
    }
    private static String getDtFmtString(Date date, String dateFmt) {

        SimpleDateFormat format = new SimpleDateFormat(dateFmt);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return format.format(cal.getTime());
    }

    private static Date toDate(String fromTs, String formatFrom) {

        if (!hasValue(fromTs)) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(formatFrom);
        parser.setLenient(false);

        try {
            return parser.parse(fromTs);
        } catch (ParseException e) {
            return null;
        }
    }
    // QC#27882 Add End

    // START 2019/06/25 K.Kitachi [QC#50938, ADD]
    private void setTimestamp(EZDPStringItem item) {
        if (hasValue(item)) {
            return;
        }
        SvcTimeZoneInfo convSysToLoc = NSXC001001SvcTimeZone.convertTime(
                NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, sysDate + sysTime, (String) rcvTaskInfoMap.get(CTRY_CD), (String) rcvTaskInfoMap.get(POST_CD));
        // START 2019/07/22 S.Kitamura [QC#51521, MOD]
        //setValue(item, S21StringUtil.subStringByLength(convSysToLoc.getDateTime(), 0, dateFormat.length()));
        setValue(item, S21StringUtil.subStringByLength(convSysToLoc.getDateTime(), 0, dateFormat.length() - 2) + "00");
        // END 2019/07/22 S.Kitamura [QC#51521, MOD]
    }
    // END 2019/06/25 K.Kitachi [QC#50938, ADD]

    // START 2019/11/08 K.Fujimoto[QC#54400, ADD]
    private String getFsrVisitStatus(NSZC068001PMsg pMsg) {
        FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
        fsrVisitTMsg.setSQLID("002");
        fsrVisitTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        fsrVisitTMsg.setConditionValue("svcTaskNum01", pMsg.svcTaskNum.getValue());
        FSR_VISITTMsgArray fsrVisitTMsgArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(fsrVisitTMsg);
        if (fsrVisitTMsgArray != null) {
            return fsrVisitTMsgArray.no(0).fsrVisitStsCd.getValue();
        }
        return null;
    }

    private void insertFsrInstallCheckList(S21ApiMessageMap msgMap, NSZC068001PMsg pMsg) {
        if (pMsg.CsaInstallCheckList.getValidCount() < 1) {
            return;
        }

        if (!deleteFsrInstallCheckList(msgMap, pMsg)) {
            return;
        }

        for (int i = 0; i < pMsg.CsaInstallCheckList.getValidCount(); i++) {
            NSZC068001_CsaInstallCheckListPMsg dtlPMsg = pMsg.CsaInstallCheckList.no(i);
            FSR_ISTL_CHK_LISTTMsg fsrIstlChkTMsg = new FSR_ISTL_CHK_LISTTMsg();

            if (!setFsrIstlChkValue(msgMap, pMsg, dtlPMsg, fsrIstlChkTMsg)) {
                return;
            }
            S21ApiTBLAccessor.create(fsrIstlChkTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrIstlChkTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0625E, new String[] {fsrIstlChkTMsg.getTableName() });
                return;
            }
        }
        return;
    }

    private boolean deleteFsrInstallCheckList(S21ApiMessageMap msgMap, NSZC068001PMsg pMsg) {
        FSR_ISTL_CHK_LISTTMsg inMsg = new FSR_ISTL_CHK_LISTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcTaskNum01", pMsg.svcTaskNum.getValue());
        inMsg.setConditionValue("fsrNum01", (String) rcvTaskInfoMap.get(FSR_NUM));
        FSR_ISTL_CHK_LISTTMsgArray fsrIstlChkListArray = (FSR_ISTL_CHK_LISTTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

        for (int i = 0; i < fsrIstlChkListArray.getValidCount(); i++) {
            FSR_ISTL_CHK_LISTTMsg tMsg = fsrIstlChkListArray.no(i);
            if (tMsg != null) {
                S21ApiTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    msgMap.addXxMsgIdWithPrm(NSZM0625E, new String[] {tMsg.getTableName() });
                    return false;
                }
            }
        }
        return true;
    }

    private boolean setFsrIstlChkValue(S21ApiMessageMap msgMap, NSZC068001PMsg pMsg, NSZC068001_CsaInstallCheckListPMsg dtlPMsg, FSR_ISTL_CHK_LISTTMsg fsrIstlChkTMsg) {
        BigDecimal fsrIstlChkListPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.FSR_ISTL_CHK_LIST_SQ);

        setValue(fsrIstlChkTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrIstlChkTMsg.fsrIstlChkListPk, fsrIstlChkListPk);
        setValue(fsrIstlChkTMsg.fsrNum, (String) rcvTaskInfoMap.get(FSR_NUM));
        setValue(fsrIstlChkTMsg.svcTaskNum, pMsg.svcTaskNum);
        setValue(fsrIstlChkTMsg.svcConfigMstrPk, dtlPMsg.svcConfigMstrPk);
        setValue(fsrIstlChkTMsg.mdseCd, dtlPMsg.mdseCd);
        Map<String, Object> mdlInfo = getMdlInfo(pMsg.glblCmpyCd.getValue(), dtlPMsg.svcConfigMstrPk.getValue());
        if (mdlInfo != null) {
            setValue(fsrIstlChkTMsg.mdlId, (BigDecimal) mdlInfo.get(MDL_ID));
            setValue(fsrIstlChkTMsg.mdlNm, (String) mdlInfo.get(MDL_NM));
        }
        setValue(fsrIstlChkTMsg.serNum, dtlPMsg.serNum);
        setValue(fsrIstlChkTMsg.istlChkVerFlg, nullToN(dtlPMsg.istlChkVerFlg.getValue()));
        setValue(fsrIstlChkTMsg.crctSerNum, dtlPMsg.crctSerNum);
        setValue(fsrIstlChkTMsg.istlCpltFlg, nullToN(pMsg.istlCpltFlg.getValue()));
        return true;
    }

    private String nullToN(String flg) {
        if (hasValue(flg)) {
            return flg;
        }
        return ZYPConstant.FLG_OFF_N;
    }
    // END   2019/11/08 K.Fujimoto[QC#54400, ADD]

    // START 2023/11/07 K.Kitachi [QC#61648, ADD]
    private boolean isSendErrMail(NPZC117001PMsg partsRequestApiParam) {
        for (int i = 0; i < partsRequestApiParam.xxMsgIdList.getValidCount(); i++) {
            if (NPZM0318E.equals(partsRequestApiParam.xxMsgIdList.no(i).xxMsgId.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void sendErrMail(String glblCmpyCd, List<S21MailAddress> toAddrList, String message) {
        // Mail instance
        S21Mail mail = new S21Mail(glblCmpyCd);

        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);

        // Set address
        mail.setToAddressList(toAddrList);
        mail.setFromAddress(groupFrom.getMailAddress().get(0));

        // Mail Param Timestamp
        Date execDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        SimpleDateFormat timeFormat = new SimpleDateFormat();
        dateFormat.applyLocalizedPattern(MAIL_DATE_FORMAT);
        timeFormat.applyLocalizedPattern(MAIL_TIME_FORMAT);

        // Mail template instance
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        // Set template parameter
        template.setTemplateParameter(MAIL_PARAM_TODAY, dateFormat.format(execDate));
        template.setTemplateParameter(MAIL_PARAM_TIME, timeFormat.format(execDate));
        template.setTemplateParameter(MAIL_PARAM_MESSAGE, message);

        // Set mail subject
        mail.setSubject(template.getSubject(ML_LANG), ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }

    private String getMdseDescShortTxt(String glblCmpyCd, String mdseCd) {
        ALL_MDSE_VTMsg param = new ALL_MDSE_VTMsg();
        param.setSQLID("003");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("mdseCd01", mdseCd);
        ALL_MDSE_VTMsgArray result = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() == 0) {
            return null;
        }
        return result.no(0).mdseDescShortTxt.getValue();
    }
    // END 2023/11/07 K.Kitachi [QC#61648, ADD]
}
