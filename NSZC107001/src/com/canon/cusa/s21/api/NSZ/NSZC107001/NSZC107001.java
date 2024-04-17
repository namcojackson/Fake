/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC107001;


import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_TASK_SEND_RSLTTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MOD_SEND_CLICKTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASK_STSTMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC107001.constant.NSZC107001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SerialRangeCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MOD_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSACheckMaxReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAInstallCheckList;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAMeter;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAMeterInfo;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAMeterReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAModification;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSANoteSourceReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSANoteTypeReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSATaskNote;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.OperationResult;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAMeterInfo.Meters;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSAAccessPermitReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSABusinessReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSAInstallCheckListReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSAMeterInfoReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSAModificationReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSAProblemCodeReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CSATaskNoteReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CalendarReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.CountryReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.DistrictReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.EngineerReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.type.ObjectReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.ProcessTaskEx;
import com.canon.usa.s21.integration.service.clicksoftware.type.SkillReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task;
import com.canon.usa.s21.integration.service.clicksoftware.type.TaskStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.TaskTypeReference;
import com.canon.usa.s21.integration.service.clicksoftware.type.UpdateTask;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.CSAAccessPermits;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.CSAModifications;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.CSANotes;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.ExcludedEngineers;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.PreferredEngineers;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.RequiredEngineers;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.RequiredSkills1;
import com.canon.usa.s21.integration.service.clicksoftware.type.Task.RequiredSkills1.TaskRequiredSkill1;
import com.canon.usa.s21.integration.service.clicksoftware.wrapper.ClickSoftwareService;
/**
 *<pre>
 *  Send Task Info to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2016   Fujitsu         S.Nakai         Create          CSA QC#9677
 * 06/16/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 06/21/2016   Hitachi         T.Iwamoto       Update          CSA QC#5528
 * 06/23/2016   Hitachi         T.Iwamoto       Update          CSA QC#10468
 * 06/28/2016   Hitachi         T.Iwamoto       Update          CSA QC#4467,6676
 * 06/29/2016   Hitachi         T.Iwamoto       Update          CSA QC#11006,6676
 * 07/06/2016   Hitachi         T.Iwamoto       Update          CSA QC#11338
 * 08/03/2016   Hitachi         T.Iwamoto       Update          CSA QC#11134
 * 09/07/2016   Hitachi         K.Yamada        Update          CSA QC#14199
 * 09/12/2016   Hitachi         K.Yamada        Update          CSA QC#14357
 * 10/04/2016   Hitachi         Y.Zhang         Update          CSA QC#14918
 * 11/01/2016   Hitachi         N.Arai          Update          QC#15678
 * 11/02/2016   Hitachi         N.Arai          Update          QC#15654
 * 11/04/2016   Hitachi         N.Arai          Update          QC#15765
 * 11/07/2016   Hitachi         N.Arai          Update          QC#15784
 * 11/15/2016   Hitachi         T.Mizuki        Update          QC#15891
 * 11/15/2016   Hitachi         N.Arai          Update          QC#15784
 * 11/15/2016   Hitachi         N.Arai          Update          QC#15654
 * 11/16/2016   Hitachi         N.Arai          Update          QC#15860
 * 11/17/2016   Hitachi         N.Arai          Update          QC#16020
 * 12/12/2016   Hitachi         N.Arai          Update          QC#16511
 * 12/20/2016   Hitachi         K.Yamada        Update          QC#16514
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 03/07/2017   Hitachi         K.Kojima        Update          QC#17943
 * 06/21/2017   Hitachi         Y.Osawa         Update          QC#19054
 * 2017/07/25   Hitachi         K.Kojima        Update          QC#19963
 * 2017/08/25   Hitachi         K.Kojima        Update          QC#19911
 * 2017/08/25   Hitachi         K.Kojima        Update          QC#20667
 * 2017/09/04   Hitachi         M.Kidokoro      Update          QC#20862
 * 2017/09/08   Hitachi         M.Kidokoro      Update          QC#20862
 * 2017/10/03   Hitachi         T.Tomita        Update          QC#21515
 * 2017/10/20   Hitachi         K.Kojima        Update          QC#19911-1
 * 2017/12/11   CITS            M.Naito         Update          QC#10040
 * 2018/01/31   Hitachi         K.Kojima        Update          QC#21112
 * 2018/02/08   Hitachi         K.Kojima        Update          QC#21112
 * 2018/02/19   CITS            M.Naito         Update          QC#21112
 * 2018/03/22   Hitachi         T.Tomita        Update          QC#18713
 * 2018/05/30   Hitachi         T.Tomita        Update          QC#26408
 * 2018/08/06   Hitachi         K.Kitachi       Update          QC#26049
 * 2018/08/08   Hitachi         K.Kitachi       Update          QC#27460
 * 2018/10/09   Hitachi         K.Fujimoto      Update          QC#28612
 * 2018/10/22   Hitachi         T.Tomita        Update          QC#28565
 * 2018/11/15   Hitachi         T.Tomita        Update          QC#29281
 * 2018/12/05   Hitachi         K.Morita        Update          QC#28644
 * 2019/04/02   Hitachi         A.Kohinata      Update          QC#31027
 * 2019/07/12   Hitachi         A.Kohinata      Update          QC#51427
 * 2019/08/22   Hitachi         K.Fujimoto      Update          QC#51206
 * 2019/10/02   Hitachi         K.Fujimoto      Update          QC#53507
 * 2019/10/02   Hitachi         K.Fujimoto      Update          QC#53864
 * 2019/10/24   Hitachi         K.Fujimoto      Update          QC#53870
 * 2019/10/24   Hitachi         K.Fujimoto      Update          QC#53441
 * 2019/11/18   Hitachi         K.Fujimoto      Update          QC#54391
 * 2019/12/19   Hitachi         K.Fujimoto      Update          QC#55106
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 * 2020/04/03   Hitachi         K.Kim           Update          QC#56141
 * 2020/04/08   Hitachi         A.Kohinata      Update          QC#56328
 * 2020/04/21   Hitachi         A.Kohinata      Update          QC#56328-1
 * 2020/05/18   Hitachi         K.Kitachi       Update          QC#54615
 * 2020/08/03   Hitachi         K.Yamada        Update          QC#57483
 * 2020/12/22   CITS            T.Wada          Update          QC#58154
 * 2020/12/22   CITS            T.Wada          Update          QC#58152-1
 * 2021/09/02   CITS            T.Wada          Update          QC#59169
 * 2021/10/04   CITS            L.Mandanas      Update          QC#59114
 * 2021/10/14   CITS            L.Mandanas      Update          QC#59114
 * 2022/01/07   CITS            L.Mandanas      Update          QC#59114
 * 2022/01/11   CITS            L.Mandanas      Update          QC#59114-1
 * 2022/03/03   Hitachi         K.Kitachi       Update          QC#59700
 * 2022/03/07   Hitachi         D.Yoshida       Update          QC#59701
 * 2022/05/23   Hitachi         D.Yoshida       Update          QC#60058
 * 2022/07/25   Hitachi         K.Kim           Update          QC#60282
 * 2022/09/02   Hitachi         K.Kitachi       Update          QC#60536
 * 2022/10/19   Hitachi         K.Kishimoto     Update          QC#60712
 * 2022/11/16   Hitachi         K.Kitachi       Update          QC#60821
 * 2023/03/03   Hitachi         K.Watanabe      Update          QC#60926
 * 2023/05/16   Hitachi         K.Kitachi       Update          QC#61085
 * 2023/06/16   Hitachi         K.Watanabe      Update          QC#61552
 * 2023/06/21   Hitachi         K.Kitachi       Update          QC#61085
 * 2023/07/14   Hitachi         K.Watanabe      Update          QC#61310
 *</pre>
 */
public class NSZC107001 extends S21ApiCommonBase implements NSZC107001Constant {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /** varConstmap */
    private Map<String, String> varConstMap = null;

    /** numConstmap */
    private Map<String, BigDecimal> numConstMap = null;

    // START 2019/10/21 K.Fujimoto [QC#53441, ADD]
    /** forceSendTaskFlg **/
    private boolean forceSendTaskFlg = false;
    // END 2019/10/21 K.Fujimoto [QC#53441, ADD]
    // START 2022/07/25 [QC#60282, ADD]
    /** sendTaskBatchFlg **/
    private boolean sendTaskBatchFlg = false;
    // END 2022/07/25 [QC#60282, ADD]
    // START 2023/05/16 K.Kitachi [QC#61085, ADD]
    /** forceSendMultOpFlg **/
    private boolean forceSendMultOpFlg = false;
    // END 2023/05/16 K.Kitachi [QC#61085, ADD]

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private  boolean ofsMultiByteConvertFlg = false;
    // END   2022/10/19 [QC#60712, ADD]

    /**
     * Constructor
     */
    public NSZC107001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC107001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC107001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC107001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC107001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC107001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        init(pMsg, onBatchType);

        if (!inputCheck(pMsg)) {
            msgMap.flush();
            return;
        }
        if (!getVarConstMap(pMsg.glblCmpyCd.getValue())) {
            msgMap.flush();
            return;
        }
        if (!getNumConstMap(pMsg.glblCmpyCd.getValue())) {
            msgMap.flush();
            return;
        }
        // START 2019/10/29 K.Fujimoto [QC#53441, ADD]
        // Insert FSR for Force Send.
        if (!insertFsrEventForForceSend(pMsg)) {
            msgMap.flush();
            return;
        }
        // END   2019/10/29 K.Fujimoto [QC#53441, ADD]
        // Send Task Information.
        sendTaskInfo(pMsg);
        msgMap.flush();
    }

    private void init(NSZC107001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
    }
    private void sendTaskInfo(NSZC107001PMsg pMsg) {
        // get Task Information.
        Map<String, Object> taskInfoMap = getTaskInfo(pMsg);
        if (taskInfoMap == null) {
            setInfoMsg(NSZM1019I, pMsg.svcTaskNum.getValue());
            // START 2019/09/20 K.Fujimoto [QC#53507,ADD]
            SVC_TASKTMsg tMsg = getSvcTaskTMsg(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
            if (tMsg != null) {
                CLICK_TASK_SEND_RSLTTMsg clickTMsg = getClickTaskSendRsltTMsg(pMsg.glblCmpyCd.getValue(), tMsg.fsrNum.getValue(), pMsg.svcTaskNum.getValue());
                if (clickTMsg != null) {
                    clickTMsg.multOpProcStsCd.clear();
                    clickTMsg.procTaskExProcStsCd.clear();
                    clickTMsg.relnUpdTaskProcStsCd.clear();
                    clickTMsg.updTaskProcStsCd.clear();
                    setValue(clickTMsg.rtrySendProcStsCd, PROC_STS.ERROR);
                    setValue(clickTMsg.appMsgId, NSZM1019I);
                    EZDTBLAccessor.update(clickTMsg);
                }
            }
            // END   2019/09/20 K.Fujimoto [QC#53507,ADD]
            return;
        }
        // START 2017/07/25 K.Kojima [QC#19963,ADD]
        replaceStatus(pMsg.glblCmpyCd.getValue(), taskInfoMap);
        // END 2017/07/25 K.Kojima [QC#19963,ADD]
        // mod start 2020/04/08 QC#56328
//        if (sendTaskInfoApi(pMsg, taskInfoMap)) {
//            updFsrEvent(pMsg, taskInfoMap, false);
        List<Map<String, Object>> svcModList = new ArrayList<Map<String, Object>>();
        if (sendTaskInfoApi(pMsg, taskInfoMap, svcModList)) {
            updFsrEvent(pMsg, taskInfoMap, false);
            insertSvcModSendClick(pMsg, svcModList);
            // add end 2020/04/08 QC#56328
        } else {
            updFsrEvent(pMsg, taskInfoMap, true);
        }
        // mod end 2020/04/08 QC#56328
        return;
    }
    /**
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    // mod start 2020/04/08 QC#56328
    //private boolean sendTaskInfoApi(NSZC107001PMsg pMsg, Map<String, Object> taskInfoMap) {
    private boolean sendTaskInfoApi(NSZC107001PMsg pMsg, Map<String, Object> taskInfoMap, List<Map<String, Object>> svcModList) {
    // mod end 2020/04/08 QC#56328

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        paramMap.put("svcMachMstrPk", getBigDecimal(taskInfoMap, SVC_MACH_MSTR_PK));
        // mod start 2016/11/15 CSA QC#15891
        paramMap.put("bizAreaOrgCd", BIZ_AREA_ORG.SERVICE);
        // mod end 2016/11/15 CSA QC#1589
        // START 2018/12/05 [QC#28644,MOD]
        paramMap.put("techReadMndFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/12/05  [QC#28644,MOD]

        List<Map<String, Object>> svcNonPrfTechList = ssmClient.queryObjectList("getSvcNonPrfTech", paramMap);

        List<Map<String, Object>> accessPermitsList = ssmClient.queryObjectList("getAccessPermits", paramMap);

        // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
        // START 2019/11/18 K.Fujimoto [QC#54391, MOD]
        // START 2019/10/21 K.Fujimoto [QC#53441, MOD]
        // START 2018/12/05 [QC#28644,MOD]
//        boolean isOnsSprtCall = getString(taskInfoMap, ONS_SPRT_CALL_FLG).equals(ZYPConstant.FLG_ON_Y);
        // List<Map<String, Object>> mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap));
//        List<Map<String, Object>> mtrInfoList = null;
//        if (forceSendTaskFlg && varConstMap.get(NSZC1070_MTR_SEND).equals(ZYPConstant.FLG_OFF_N)) {
//            mtrInfoList = new ArrayList<Map<String,Object>>();
//        } else if(isOnsSprtCall && varConstMap.get(NSZC1070_L2_MTR_SEND).equals(ZYPConstant.FLG_OFF_N)) {
//            mtrInfoList = new ArrayList<Map<String,Object>>();
//        } else {
//            mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap));
//            if(mtrInfoList.isEmpty()) {
//                paramMap.put("techReadMndFlg", ZYPConstant.FLG_OFF_N);
//                mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap));
//            }
//        }
        // END 2018/12/05  [QC#28644,MOD]

        //List<Map<String, Object>> svcModPlnList = ssmClient.queryObjectList("getSvcModPln", getSvcModPlnParam(paramMap));
//        List<Map<String, Object>> svcModPlnList = null;
//        if (forceSendTaskFlg && varConstMap.get(NSZC1070_MODS_SEND).equals(ZYPConstant.FLG_OFF_N)) {
//            svcModPlnList = new ArrayList<Map<String,Object>>();
//        } else if(isOnsSprtCall && varConstMap.get(NSZC1070_L2_MODS_SEND).equals(ZYPConstant.FLG_OFF_N)) {
//            svcModPlnList = new ArrayList<Map<String,Object>>();
//        } else {
//            svcModPlnList = ssmClient.queryObjectList("getSvcModPln", getSvcModPlnParam(paramMap));
            // START 2019/10/24 K.Fujimoto [QC#53870,ADD]
//            removeOutOfRngSvcModPln(pMsg.glblCmpyCd.getValue(), svcModPlnList);
            // END   2019/10/24 K.Fujimoto [QC#53870,ADD]
//        }
        // Mod Start 2017/10/03 QC#21515
        //List<Map<String, Object>> installCheckList = new ArrayList<Map<String,Object>>();
//        List<Map<String, Object>> installCheckList = null;
//        if (forceSendTaskFlg && varConstMap.get(NSZC1070_INSTL_CHK_LIST_SEND).equals(ZYPConstant.FLG_OFF_N)) {
//            installCheckList = new ArrayList<Map<String,Object>>();
//        } else if(isOnsSprtCall && varConstMap.get(NSZC1070_L2_ISTL_CHK_LST_SEND).equals(ZYPConstant.FLG_OFF_N)) {
//            installCheckList = new ArrayList<Map<String,Object>>();
//        } else {
//            if (isSvcExchOrd(pMsg.glblCmpyCd.getValue(), getBigDecimal(taskInfoMap, SVC_MACH_MSTR_PK))) {
//                installCheckList = getInstallCheckListForSvcExch(pMsg.glblCmpyCd.getValue(), getBigDecimal(taskInfoMap, SVC_MACH_MSTR_PK));
            // START 2018/01/31 K.Kojima [QC#21112,ADD]
            // del start 2019/07/12 QC#51427
            //} else if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, SVC_DEINS_REQ_FLG))) {
            //    installCheckList = ssmClient.queryObjectList("getInstallCheckList", getInstallCheckListParamForRemoval(paramMap));
            // del end 2019/07/12 QC#51427
            // END 2018/01/31 K.Kojima [QC#21112,ADD]
//            } else {
//                installCheckList = ssmClient.queryObjectList("getInstallCheckList", getInstallCheckListParam(paramMap));
//            }
//        }
        // Mod End 2017/10/03 QC#21515
        // END   2019/10/21 K.Fujimoto [QC#53441, MOD]
        // END   2019/11/18 K.Fujimoto [QC#54391, MOD]
        // END   2019/12/19 K.Fujimoto [QC#55106, DEL]
        // START 2019/12/19 K.Fujimoto [QC#55106, ADD]
        boolean isOnsSprtCall = getString(taskInfoMap, ONS_SPRT_CALL_FLG).equals(ZYPConstant.FLG_ON_Y);
        String fsrNum = getString(taskInfoMap, FSR_NUM);
        String svcTaskNum = getString(taskInfoMap, SVC_TASK_NUM);
        CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg = getClickTaskSendRsltTMsg(pMsg.glblCmpyCd.getValue(), fsrNum, svcTaskNum);
        String fsrVisitStsCd = getString(taskInfoMap, FSR_VISIT_STS_CD);
        List<Map<String, Object>> mtrInfoList =  new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> installCheckList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> svcModPlnList = new ArrayList<Map<String, Object>>();
        if (isMultOp(clickTaskSendRsltTMsg, fsrVisitStsCd)) {
            mtrInfoList = getMtr(pMsg, isOnsSprtCall, paramMap, taskInfoMap);
            installCheckList = getInstlChkList(pMsg, isOnsSprtCall, paramMap, taskInfoMap);
            boolean isSentMultOps = isSentMultOps(pMsg.glblCmpyCd.getValue(), svcTaskNum);
            if (!isSentMultOps) {
                svcModPlnList = getMods(pMsg, isOnsSprtCall, paramMap, taskInfoMap);
                // add start 2020/04/08 QC#56328
                for (Map<String, Object> svcModPlnMap : svcModPlnList) {
                    svcModList.add(svcModPlnMap);
                }
                // add end 2020/04/08 QC#56328
            }
        }
        // END   2019/12/19 K.Fujimoto [QC#55106, ADD]


// START 2016/11/02 N.Arai [QC#15654, MOD]
        List<Map<String, Object>> csaTaskNoteList = ssmClient.queryObjectList("getTaskNoteList", getTaskNoteListParam(paramMap));
// END 2016/11/02 N.Arai [QC#15654, MOD]

        // Add Start 2018/10/22 QC#28565
        // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
//        String fsrNum = getString(taskInfoMap, FSR_NUM);
//        String svcTaskNum = getString(taskInfoMap, SVC_TASK_NUM);
//        CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg = getClickTaskSendRsltTMsg(pMsg.glblCmpyCd.getValue(), fsrNum, svcTaskNum);
        // END   2019/12/19 K.Fujimoto [QC#55106, DEL]
        // Add End 2018/10/22 QC#28565

        // set TaskInfo
        Task task = new Task();
        ObjectFactory of = new ObjectFactory();

        // START 2019/10/02 K.Fujimoto [QC#53864, MOD]
        //if (!setTaskInfo(task, of, taskInfoMap, svcNonPrfTechList, accessPermitsList, mtrInfoList, svcModPlnList, installCheckList)) {
        if (!setTaskInfo(task, of, taskInfoMap, svcNonPrfTechList, accessPermitsList, mtrInfoList, svcModPlnList, installCheckList, pMsg.glblCmpyCd.getValue())) {
            // add start 2020/08/03 QC#57483
            updRsltForProcTaskExErr(null, pMsg.glblCmpyCd.getValue(), fsrNum, svcTaskNum, fsrVisitStsCd, null, true, true);
            // add end 2020/08/03 QC#57483
            return false;
        }
        // END   2019/10/02 K.Fujimoto [QC#53864, MOD]
        // START 2017/09/04 M.Kidokoro [QC#20862,ADD]
        replaceLocation(task, of, pMsg.glblCmpyCd.getValue(), taskInfoMap);
        // END 2017/09/04 M.Kidokoro [QC#20862,ADD]
        // mod start 2017/01/19
        // Temporary solution for resource manager not being populate
        // call TaskInfo
        //boolean taskRtnFlg = callTaskInfo(task, of, taskInfoMap);
        // Call Multiple Operations(Meter, Modifications, InstallCheckList)
        //boolean multiRtnFlg = true;
        //boolean updateRtnFlg = true;
        StandardOperations multipleOperations = null;
        // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//        UpdateTask request = null;
        // END 2023/05/16 K.Kitachi [QC#61085, DEL]
// START 2016/11/02 N.Arai [QC#15654, MOD]
//        if (isMultipleOperations(taskInfoMap, mtrInfoList, svcModPlnList, installCheckList)) {
//            multiRtnFlg = callMultipleOperations(mtrInfoList, svcModPlnList, installCheckList, getString(taskInfoMap, SVC_ISTL_REQ_FLG), getString(taskInfoMap, SVC_TASK_NUM));
// START 2016/11/15 N.Arai [QC#15654, MOD]
        // Mod Start 2018/10/22 QC#28565
        // if (isMultipleOperations(taskInfoMap, mtrInfoList, svcModPlnList, installCheckList, csaTaskNoteList)) {
        if (isMultipleOperations(taskInfoMap, mtrInfoList, svcModPlnList, installCheckList, csaTaskNoteList, clickTaskSendRsltTMsg)) {
        // Mod End 2018/10/22 QC#28565
//            multiRtnFlg = callMultipleOperations(mtrInfoList, svcModPlnList, installCheckList, getString(taskInfoMap, SVC_ISTL_REQ_FLG), getString(taskInfoMap, SVC_TASK_NUM),
//                    csaTaskNoteList, pMsg.glblCmpyCd.getValue(), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD));
// END 2016/11/02 N.Arai [QC#15654, MOD]
            // START 2018/01/31 K.Kojima [QC#21112,MOD]
            // multipleOperations = callMultipleOperations(mtrInfoList, svcModPlnList, installCheckList, getString(taskInfoMap, SVC_ISTL_REQ_FLG), getString(taskInfoMap, SVC_TASK_NUM),
            //         csaTaskNoteList, pMsg.glblCmpyCd.getValue(), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD), getString(taskInfoMap, FSR_VISIT_STS_CD));
            // Mod Start 2018/10/22 QC#28565
            // multipleOperations = callMultipleOperations(mtrInfoList, svcModPlnList, installCheckList, getString(taskInfoMap, SVC_ISTL_REQ_FLG), getString(taskInfoMap, SVC_DEINS_REQ_FLG), getString(taskInfoMap, SVC_TASK_NUM),
            //         csaTaskNoteList, pMsg.glblCmpyCd.getValue(), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD), getString(taskInfoMap, FSR_VISIT_STS_CD));
            // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
            // String fsrVisitStsCd = getString(taskInfoMap, FSR_VISIT_STS_CD);
            // END   2019/12/19 K.Fujimoto [QC#55106, DEL]
            // START 2023/07/14 K.Watanabe [QC#61310, MOD]
            // multipleOperations = callMultipleOperations(mtrInfoList, svcModPlnList, installCheckList, getString(taskInfoMap, SVC_ISTL_REQ_FLG), getString(taskInfoMap, SVC_DEINS_REQ_FLG), getString(taskInfoMap, SVC_TASK_NUM),
            //         csaTaskNoteList, pMsg.glblCmpyCd.getValue(), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD), fsrVisitStsCd, clickTaskSendRsltTMsg);
            multipleOperations = callMultipleOperations(mtrInfoList, svcModPlnList, installCheckList, getString(taskInfoMap, SVC_ISTL_REQ_FLG), getString(taskInfoMap, SVC_DEINS_REQ_FLG), getString(taskInfoMap, SVC_TASK_NUM),
                    csaTaskNoteList, pMsg.glblCmpyCd.getValue(), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD), fsrVisitStsCd, clickTaskSendRsltTMsg, pMsg.slsDt.getValue());
            // END 2023/07/14 K.Watanabe [QC#61310, MOD]
            // Mod End 2018/10/22 QC#28565
            // END 2018/01/31 K.Kojima [QC#21112,MOD]
            // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//            // START 2019/12/20 K.Fujimoto [QC#55106, MOD]
//            // Mod Start 2018/10/22 QC#28565
//            // if (SVC_TASK_STS.TBO.equals(getString(taskInfoMap, FSR_VISIT_STS_CD))) {
//            // END   2019/12/20 K.Fujimoto [QC#55106, MOD]
//            //if (isRelnUpdTask(clickTaskSendRsltTMsg, fsrVisitStsCd)) {
//            // START 2022/03/03 K.Kitachi [QC#59700, MOD]
////            if (isRelnUpdTask(taskInfoMap, mtrInfoList, svcModPlnList, installCheckList, clickTaskSendRsltTMsg, fsrVisitStsCd)) {
//            if (isRelnUpdTask(taskInfoMap, mtrInfoList, svcModPlnList, csaTaskNoteList, installCheckList, clickTaskSendRsltTMsg, fsrVisitStsCd)) {
//            // END 2022/03/03 K.Kitachi [QC#59700, MOD]
//            // Mod End 2018/10/22 QC#28565
//            // END   2019/12/20 K.Fujimoto [QC#55106, MOD]
//                // START 2022/03/03 K.Kitachi [QC#59700, MOD]
////                request = callUpdateTask(mtrInfoList, svcModPlnList, installCheckList, taskInfoMap);
//                request = callUpdateTask(mtrInfoList, svcModPlnList, csaTaskNoteList, installCheckList, taskInfoMap);
//                // END 2022/03/03 K.Kitachi [QC#59700, MOD]
//            }
            // END 2023/05/16 K.Kitachi [QC#61085, DEL]
        }
// END 2016/11/15 N.Arai [QC#15654, MOD]
        // Mod Start 2018/10/22 QC#28565
        // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//        return sendTaskInfoToClick(pMsg.glblCmpyCd.getValue(), task, multipleOperations, request, taskInfoMap, csaTaskNoteList, clickTaskSendRsltTMsg);
        return sendTaskInfoToClick(pMsg.glblCmpyCd.getValue(), task, multipleOperations, taskInfoMap, csaTaskNoteList, clickTaskSendRsltTMsg);
        // END 2023/05/16 K.Kitachi [QC#61085, MOD]
        // Mod End 2018/10/22 QC#28565
        //return taskRtnFlg && multiRtnFlg && updateRtnFlg;
    }
    private boolean inputCheck(NSZC107001PMsg pMsg) {
        boolean noErrFlg = true;
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(NSZM0001E);
            noErrFlg = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            setErrMsg(NSZM0002E);
            noErrFlg = false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
            setErrMsg(NSZM0082E);
            noErrFlg = false;
        }
        // START 2019/10/21 K.Fujimoto [QC#53441, ADD]
        if (ZYPCommonFunc.hasValue(pMsg.forceSendTaskFlg) && pMsg.forceSendTaskFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            forceSendTaskFlg = true;
        }
        // START 2019/10/21 K.Fujimoto [QC#53441, ADD]
        // START 2022/07/25 [QC#60282, ADD]
        if (ZYPCommonFunc.hasValue(pMsg.sendTaskBatchFlg) && pMsg.sendTaskBatchFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            sendTaskBatchFlg = true;
        }
        // END 2022/07/25 [QC#60282, ADD]
        // START 2023/05/16 K.Kitachi [QC#61085, ADD]
        if (ZYPCommonFunc.hasValue(pMsg.forceSendMultOpFlg) && pMsg.forceSendMultOpFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            forceSendMultOpFlg = true;
        }
        // END 2023/05/16 K.Kitachi [QC#61085, ADD]
        return noErrFlg;
    }
    private Map<String, Object> getTaskInfo(NSZC107001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        paramMap.put("iwrCondCd", IWR_COND.ACTIVE);
        paramMap.put("svcDisptEvent", SVC_DISPT_EVENT.CANCEL);
        //DEL start 2018/10/05 K.fujimoto QC#28612
        //paramMap.put("svcMemoTpCd", SVC_MEMO_TP.MEMO);
        //DEL end 2018/10/05 K.fujimoto QC#28612
        paramMap.put("mdlDurnTm", numConstMap.get(NSZC1070_MDL_DURN_TM_SEC));
        paramMap.put("fsrStsCd", SVC_TASK_STS.CLOSED);
        paramMap.put("procStsProcessed", MBL_INTFC_PROC.PROCESSED);
        paramMap.put("procStsError", MBL_INTFC_PROC.ERROR);
        paramMap.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        // mod start 2016/11/15 CSA QC#15891
        paramMap.put("bizAreaOrdCd", BIZ_AREA_ORG.SERVICE);
        // mod end 2016/11/15 CSA QC#15891
        return (Map<String, Object>) ssmClient.queryObject("getTaskInfo", paramMap);
    }
    // START 2019/10/02 K.Fujimoto [QC#53864, MOD]
    //private boolean setTaskInfo(Task task, ObjectFactory of, Map<String, Object> taskInfoMap, List<Map<String, Object>> svcNonPrfTechList, List<Map<String, Object>> accessPermitsList, List<Map<String, Object>> mtrInfoList,
    //        List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList) {
    private boolean setTaskInfo(Task task, ObjectFactory of, Map<String, Object> taskInfoMap, List<Map<String, Object>> svcNonPrfTechList, List<Map<String, Object>> accessPermitsList, List<Map<String, Object>> mtrInfoList,
            List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, String glblCmpyCd) {
    // END   2019/10/02 K.Fujimoto [QC#53864, MOD]
        // CallID
        task.setCallID(getString(taskInfoMap, SVC_TASK_NUM));

        // External Ref ID
        String fsrNum = getString(taskInfoMap, FSR_NUM);
        if (ZYPCommonFunc.hasValue(fsrNum)) {

            task.setExternalRefID(fsrNum);
        }
        // Call Type
        String dsSvcCallTp = getString(taskInfoMap, XTRNL_CALL_TP_REF_TXT);

        if (ZYPCommonFunc.hasValue(dsSvcCallTp)) {
            TaskTypeReference taskTypeRef = new TaskTypeReference();
            taskTypeRef.getContent().add(of.createTaskTypeReferenceName(dsSvcCallTp));
            task.setTaskType(taskTypeRef);
        }
        // Model Duration
        BigDecimal mdlDurnTmNum = getBigDecimal(taskInfoMap, MDL_DURN_TM_NUM);
        if (ZYPCommonFunc.hasValue(mdlDurnTmNum)) {
            task.setDuration(mdlDurnTmNum.intValue());
        }
        // Status
        String svcTaskStsNm = getString(taskInfoMap, SVC_TASK_STS_NM);
        if (ZYPCommonFunc.hasValue(svcTaskStsNm)) {
            TaskStatusReference taskStsRef = new TaskStatusReference();
            taskStsRef.getContent().add(svcTaskStsNm);
            task.setStatus(taskStsRef);
        }
        // Calendar
        CalendarReference calRef = new CalendarReference();
        calRef.getContent().add(of.createCalendarReferenceName(varConstMap.get(NSZC1070_CALENDAR)));
        task.setCalendar(calRef);
        // START 2019/10/02 K.Fujimoto [QC#53864, MOD]
        // District
        String fldSvcBr = getString(taskInfoMap, FLD_SVC_BR);
        String svcByLineBizTpCd = getString(taskInfoMap, SVC_BY_LINE_BIZ_TP_CD);
//        if (ZYPCommonFunc.hasValue(fldSvcBr)) {
//            DistrictReference distRef = new DistrictReference();
//            distRef.getContent().add(of.createDistrictReferenceName(fldSvcBr));
//            task.setDistrict(distRef);
//        }
        String postCd = getString(taskInfoMap, POST_CD);
        String ctryCd = getString(taskInfoMap, CTRY_CD);
        String firstLineAddr = getString(taskInfoMap, FIRST_LINE_ADDR);
        String scdLineAddr = getString(taskInfoMap, SCD_LINE_ADDR);
        String ctyAddr = getString(taskInfoMap, CTY_ADDR);
        String stCd = getString(taskInfoMap, ST_CD);


        if (SVC_TASK_STS.CANCELLED.equals(getString(taskInfoMap, FSR_VISIT_STS_CD))
                && (!ZYPCommonFunc.hasValue(postCd) || !ZYPCommonFunc.hasValue(ctryCd))) {
            SHIP_TO_CUSTTMsg shipToCustTmsg = getShipToCustTMsg(glblCmpyCd, getString(taskInfoMap, SHIP_TO_CUST_CD));
            if (shipToCustTmsg != null) {
                postCd = shipToCustTmsg.postCd.getValue();
                ctryCd = shipToCustTmsg.ctryCd.getValue();
                firstLineAddr = shipToCustTmsg.firstLineAddr.getValue();
                scdLineAddr = shipToCustTmsg.scdLineAddr.getValue();
                ctyAddr = shipToCustTmsg.ctyAddr.getValue();
                stCd = shipToCustTmsg.stCd.getValue();
                String svcBrCd =  getString(taskInfoMap, FLD_SVC_BR_CD);
                fldSvcBr = getFldSvcBr(glblCmpyCd, postCd, svcByLineBizTpCd, svcBrCd);
                if (!ZYPCommonFunc.hasValue(fldSvcBr) && postCd.length() > LEN_5) {
                    fldSvcBr = getFldSvcBr(glblCmpyCd, postCd.substring(NUM_0, LEN_5), svcByLineBizTpCd, svcBrCd);
                }
            }
        }
        if (ZYPCommonFunc.hasValue(fldSvcBr)) {
            DistrictReference distRef = new DistrictReference();
            distRef.getContent().add(of.createDistrictReferenceName(fldSvcBr));
            task.setDistrict(distRef);
        }
        // Address
//        if (ZYPCommonFunc.hasValue(getString(taskInfoMap, FIRST_LINE_ADDR))) {
//            task.setStreet(getString(taskInfoMap, FIRST_LINE_ADDR).replace("&", ""));
//        }
//        if (ZYPCommonFunc.hasValue(getString(taskInfoMap, SCD_LINE_ADDR))) {
//            task.setCSAStreet1(getString(taskInfoMap, SCD_LINE_ADDR).replace("&", ""));
//        }
//        task.setCity(getString(taskInfoMap, CTY_ADDR));
//        task.setState(getString(taskInfoMap, ST_CD));
//        task.setPostcode(getString(taskInfoMap, POST_CD));
        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            task.setStreet(firstLineAddr.replace("&", ""));
        }
        if (ZYPCommonFunc.hasValue(scdLineAddr)) {
            task.setCSAStreet1(scdLineAddr.replace("&", ""));
        }
        task.setCity(ctyAddr);
        task.setState(stCd);
        task.setPostcode(postCd);

        // Country
        //String ctryCd = getString(taskInfoMap, CTRY_CD);

        // END   2019/10/02 K.Fujimoto [QC#53864, MOD]
        if (ZYPCommonFunc.hasValue(ctryCd)) {

            CountryReference ctryRef = new CountryReference();
            ctryRef.getContent().add(of.createCountryReferenceName(ctryCd));
            task.setCountryID(ctryRef);
        }

        if (ZYPCommonFunc.hasValue(getString(taskInfoMap, DS_ACCT_NM))) {
            task.setCSACustomerName(getString(taskInfoMap, DS_ACCT_NM).replace("&", ""));
        }
        task.setContactPhoneNumber(ZYPCommonFunc.concatString(getString(taskInfoMap, CUST_TEL_NUM), " ", getString(taskInfoMap, CUST_TEL_EXTN_NUM)));
        if (ZYPCommonFunc.hasValue(getString(taskInfoMap, SVC_CUST_ATTN_TXT))) {
            task.setContactName(getString(taskInfoMap, SVC_CUST_ATTN_TXT).replace("&", ""));
        }
        // START 2017/03/07 K.Kojima [QC#17943,MOD]
        // String svcBillTpCd = getString(taskInfoMap, SVC_BILL_TP_CD);
        // if (ZYPCommonFunc.hasValue(svcBillTpCd)) {
        //     CSABillCodeReference billCdRef = new CSABillCodeReference();
        //     billCdRef.getContent().add(svcBillTpCd);
        //     task.setCSABillCode(billCdRef);
        // }
        String svcBillTpDescTxt = getString(taskInfoMap, SVC_BILL_TP_DESC_TXT);
        if (ZYPCommonFunc.hasValue(svcBillTpDescTxt)) {
        //    CSABillCodeReference billCdRef = new CSABillCodeReference();
        //    billCdRef.getContent().add(svcBillTpDescTxt);
            task.setCSABillCode(svcBillTpDescTxt);
        }
        // END 2017/03/07 K.Kojima [QC#17943,MOD]
        if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, DS_KEY_ACCT_FLG))) {
            task.setCSAVIP(true);
        } else {
            // START 2017/06/21 Y.Osawa [QC#19054,MOD]
            // task.setCSAVIP(true);
            task.setCSAVIP(false);
            // END   2017/06/21 Y.Osawa [QC#19054,MOD]
        }

        SvcTimeZoneInfo ziErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, getString(taskInfoMap, ERL_START_TS), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD));
        if (ziErlStartTs != null) {
            task.setEarlyStart(changeDateFormat(ziErlStartTs.getDateTime(), ziErlStartTs.getTimeZone()));
        }
        SvcTimeZoneInfo ziErlLateTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, getString(taskInfoMap, LATE_START_TS), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD));
        if (ziErlLateTs != null) {
            task.setLateStart(changeDateFormat(ziErlLateTs.getDateTime(), ziErlLateTs.getTimeZone()));
        }

        if (SVC_TASK_STS.CANCELLED.equals(getString(taskInfoMap, FSR_VISIT_STS_CD))) {
            // START 2019/10/02 K.Fujimoto [QC#53864, MOD]
            //SvcTimeZoneInfo ziCancelTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, getString(taskInfoMap, EZUPTIME), getString(taskInfoMap, CTRY_CD), getString(taskInfoMap, POST_CD));
            SvcTimeZoneInfo ziCancelTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, getString(taskInfoMap, EZUPTIME), ctryCd, postCd);
            // END   2019/10/02 K.Fujimoto [QC#53864, MOD]
            task.setCancellationDate(changeDateFormat(ziCancelTs.getDateTime(), ziCancelTs.getTimeZone()));
        }

        // Problem
        String svcPblmTpCd = getString(taskInfoMap, SVC_PBLM_TP_CD);
        if (ZYPCommonFunc.hasValue(svcPblmTpCd)) {

            CSAProblemCodeReference probCdRef = new CSAProblemCodeReference();
            probCdRef.getContent().add(svcPblmTpCd);
            task.setCSAProblemCode(probCdRef);
        }
        task.setCSAMachineSerialNo(getString(taskInfoMap, SER_NUM));
        task.setCustomerEmail(getString(taskInfoMap, CUST_EML_ADDR));
        if (getString(taskInfoMap, PHONE_FIX).equals(ZYPConstant.FLG_ON_Y)) {
            task.setCSAPhoneFix(true);
        } else {
            task.setCSAPhoneFix(false);
        }

        if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, MAN_LBOR_ALLW))) {
            task.setCSAOfflineSite(true);
        } else {
            task.setCSAOfflineSite(false);
        }
        task.setGISDataSource(Integer.parseInt(varConstMap.get(NSZC1070_GIS_DATA_SRC)));

        //DEL start 2018/10/05 K.fujimoto QC#28612
//        if (ZYPCommonFunc.hasValue(getString(taskInfoMap, SVC_CMNT_TXT))) {
//            task.setComment(getString(taskInfoMap, SVC_CMNT_TXT).replace("&", ""));
//        }
        //DEL end   2018/10/05 K.fujimoto QC#28612
        task.setCSAMachineTagNumber(getString(taskInfoMap, CUST_MACH_CTRL_NUM));
        task.setCSAMachineModel(getString(taskInfoMap, T_MDL_NM));

        // START 2023/06/16 K.Watanabe [QC#61552, MOD]
//        if (svcModPlnList.size() > 0) {
        if (svcModPlnList.size() > 0 || isSendModMessage(glblCmpyCd, getString(taskInfoMap, SVC_TASK_NUM))) {
        // END 2023/06/16 K.Watanabe [QC#61552, MOD]
            task.setCSAHasMOD(true);
        } else {
            task.setCSAHasMOD(false);
        }
        if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, IWR_COND_FLG))) {
            task.setCSAIWR(true);
        } else {
            task.setCSAIWR(false);
        }

        // Skills
        String svcSkillNm = getString(taskInfoMap, SVC_SKILL_NM);
        if (ZYPCommonFunc.hasValue(svcSkillNm)) {

            SkillReference skillRef = new SkillReference();
            skillRef.getContent().add(svcSkillNm);

            TaskRequiredSkill1 taskReqSkill = new TaskRequiredSkill1();
            taskReqSkill.setKey(skillRef);
            taskReqSkill.setSkillLevel(1);

            RequiredSkills1 reqSkill = new RequiredSkills1();
            reqSkill.getTaskRequiredSkill1().add(taskReqSkill);
            task.setRequiredSkills1(reqSkill);
        }

        // CSALineOfBusiness
        // START 2019/10/02 K.Fujimoto [QC#53864, DEL]
        // String svcByLineBizTpCd = getString(taskInfoMap, SVC_BY_LINE_BIZ_TP_CD);
        // END   2019/10/02 K.Fujimoto [QC#53864, DEL]
        if (ZYPCommonFunc.hasValue(svcByLineBizTpCd)) {

            CSABusinessReference br = new CSABusinessReference();
            br.getContent().add(svcByLineBizTpCd);

            // del start 2016/06/28 CSA Defect#6676
            // CSATerritoryMapperReference ctmr = new CSATerritoryMapperReference();
            // ctmr.getContent().add(of.createCSATerritoryMapperReferenceLineOfBusiness(br));
            // ctmr.getContent().add(of.createCSATerritoryMapperReferencePostcode(getString(taskInfoMap, POST_CD)));
            // task.setCSATerritoryMapper(ctmr);
            // del end 2016/06/28 CSA Defect#6676

            // del start 2016/06/23 CSA Defect#10468
            // CSALineOfBusiness
            // CSALineOfBusinessReference linebr = new CSALineOfBusinessReference();
            // linebr.getContent().add(of.createCSALineOfBusinessReferenceName(svcByLineBizTpCd));
            // task.setCSALineOfBusiness(linebr);
            // del end 2016/06/23 CSA Defect#10468

            // CSABusiness
            // add start 2016/06/23 CSA Defect#10468
            task.setCSABusiness(br);
            // add end 2016/06/23 CSA Defect#10468
        }

        // Engineers
        String svcAsgTpCd = getString(taskInfoMap, SVC_ASG_TP_CD);
        String svcAsgTechCd = getString(taskInfoMap, SVC_ASG_TECH_CD);

        String prfTechCd = getString(taskInfoMap, PRF_TECH_CD);
        String reqTechCd = getString(taskInfoMap, REQ_TECH_CD);

        boolean defNotAllowedTech = false;

        EngineerReference svcAsgEngRef = new EngineerReference();

        if (ZYPCommonFunc.hasValue(svcAsgTpCd)) {

            svcAsgEngRef.getContent().add(of.createEngineerReferenceID(svcAsgTechCd));

            DistrictReference distRef = new DistrictReference();

            // mod start 2016/11/15 CSA QC#15891
//            StringBuilder sb = new StringBuilder();
//            sb.append(getString(taskInfoMap, SVC_ASG_COA_BR_CD));
//            sb.append(HYPHEN);
//            sb.append(getString(taskInfoMap, SVC_ASG_COA_BR_NM));
//            distRef.getContent().add(sb.toString());
            distRef.getContent().add(getString(taskInfoMap, SVC_ASG_DISTRICT));
            // mod end 2016/11/15 CSA QC#15891

            svcAsgEngRef.getContent().add(of.createEngineerReferenceDistrict(distRef));

            if (SVC_ASG_TP.PREFERRED.equals(svcAsgTpCd)) {
                prfTechCd = svcAsgTechCd;
            } else if (SVC_ASG_TP.REQUIRED.equals(svcAsgTpCd)) {
                reqTechCd = svcAsgTechCd;
            } else if (SVC_ASG_TP.NOT_ALLOWED.equals(svcAsgTpCd)) {

                ExcludedEngineers excEng = new ExcludedEngineers();
                excEng.getExcludedEngineer().add(svcAsgEngRef);
                task.setExcludedEngineers(excEng);
// START 2016/11/10 N.Arai [QC#15860, MOD]
                // ExcludedEngineers
                for (Map<String, Object> svcNonPrfTechMap : svcNonPrfTechList) {

                    if (svcAsgTechCd.equals(getString(svcNonPrfTechMap, (NON_PRF_TECH_CD)))) {
                        continue;
                    }
                    EngineerReference engRef = new EngineerReference();
                    engRef.getContent().add(of.createEngineerReferenceID(getString(svcNonPrfTechMap, (NON_PRF_TECH_CD))));
                    distRef = new DistrictReference();
                    distRef.getContent().add(getString(svcNonPrfTechMap, (NON_PRF_DISTRICT)));
                    engRef.getContent().add(of.createEngineerReferenceDistrict(distRef));

                    excEng.getExcludedEngineer().add(engRef);
                    task.setExcludedEngineers(excEng);
                }
// END 2016/11/10 N.Arai [QC#15860, MOD]
                defNotAllowedTech = true;
            }
        }
        // PreferredEngineers
        if (ZYPCommonFunc.hasValue(prfTechCd)) {

            EngineerReference engRef = new EngineerReference();

            if (SVC_ASG_TP.PREFERRED.equals(svcAsgTpCd)) {
                engRef = svcAsgEngRef;
            } else {
                engRef.getContent().add(of.createEngineerReferenceID(getString(taskInfoMap, PRF_TECH_CD)));

                DistrictReference distRef = new DistrictReference();

                // mod start 2016/11/15 CSA QC#15891
//                StringBuilder sb = new StringBuilder();
//                sb.append(getString(taskInfoMap, PRF_COA_BR_CD));
//                sb.append(HYPHEN);
//                sb.append(getString(taskInfoMap, PRF_COA_BR_NM));
//                distRef.getContent().add(sb.toString());
                distRef.getContent().add(getString(taskInfoMap, PRF_DISTRICT));
                // mod end 2016/11/15 CSA QC#15891

                engRef.getContent().add(of.createEngineerReferenceDistrict(distRef));
            }
            PreferredEngineers prfEng = new PreferredEngineers();
            prfEng.getPreferredEngineer().add(engRef);
            task.setPreferredEngineers(prfEng);
        }
        // RequiredEngineers
        if (ZYPCommonFunc.hasValue(reqTechCd)) {

            EngineerReference engRef = new EngineerReference();

            if (SVC_ASG_TP.REQUIRED.equals(svcAsgTpCd)) {
                engRef = svcAsgEngRef;
            } else {
                engRef.getContent().add(of.createEngineerReferenceID(getString(taskInfoMap, REQ_TECH_CD)));
                DistrictReference distRef = new DistrictReference();
                // mod start 2016/11/15 CSA QC#15891
//                StringBuilder sb = new StringBuilder();
//                sb.append(getString(taskInfoMap, REQ_COA_BR_CD));
//                sb.append(HYPHEN);
//                sb.append(getString(taskInfoMap, REQ_COA_BR_NM));
                distRef.getContent().add(getString(taskInfoMap, REQ_DISTRICT));
                // mod end 2016/11/15 CSA QC#15891
                engRef.getContent().add(of.createEngineerReferenceDistrict(distRef));
            }
            RequiredEngineers reqEng = new RequiredEngineers();
            reqEng.getRequiredEngineer().add(engRef);
            task.setRequiredEngineers(reqEng);
        }
        if (!defNotAllowedTech) {
            // ExcludedEngineers
// START 2016/11/10 N.Arai [QC#15860, MOD]
            if (svcNonPrfTechList != null && svcNonPrfTechList.size() > 0) {
                ExcludedEngineers excEng = new ExcludedEngineers();
// END 2016/11/10 N.Arai [QC#15860, MOD]
                for (Map<String, Object> svcNonPrfTechMap : svcNonPrfTechList) {

                    EngineerReference engRef = new EngineerReference();
                    engRef.getContent().add(of.createEngineerReferenceID(getString(svcNonPrfTechMap, (NON_PRF_TECH_CD))));

                    DistrictReference distRef = new DistrictReference();

                // mod start 2016/11/15 CSA QC#15891
//                StringBuilder sb = new StringBuilder();
//                sb.append(getString(svcNonPrfTechMap, (NON_PRF_COA_BR_CD)));
//                sb.append(HYPHEN);
//                sb.append(getString(svcNonPrfTechMap, (NON_PRF_COA_BR_NM)));
//                distRef.getContent().add(sb.toString());
                    distRef.getContent().add(getString(svcNonPrfTechMap, NON_PRF_DISTRICT));
                    // mod end 2016/11/15 CSA QC#15891

                    engRef.getContent().add(of.createEngineerReferenceDistrict(distRef));

                    excEng.getExcludedEngineer().add(engRef);
                    task.setExcludedEngineers(excEng);
                }
            }
        }
        // CSA_AccessPermits

        // QC#59169 Mod Start
        CSAAccessPermits accessPmt = new CSAAccessPermits();
        int accessPmtCnt = 0;
        for (Map<String, Object> accessPermitsMap : accessPermitsList) {

            CSAAccessPermitReference accessPermitsRef = new CSAAccessPermitReference();

//            accessPermitsRef.setKey(Integer.parseInt(getString(accessPermitsMap, (SVC_ACCS_PMIT_NUM))));
//            String svcAccsPmitDescTxt = getString(accessPermitsMap, (SVC_ACCS_PMIT_DESC_TXT));
//            accessPermitsRef.getContent().add(svcAccsPmitDescTxt);

//            CSAAccessPermits accessPmt = new CSAAccessPermits();
//            accessPmt.getCSAAccessPermit().add(accessPermitsRef);
//            task.setCSAAccessPermits(accessPmt);

            String svcAccsPmitNum = getString(accessPermitsMap, (SVC_ACCS_PMIT_NUM));
            String svcAccsPmitDescTxt = getString(accessPermitsMap, (SVC_ACCS_PMIT_DESC_TXT));
            if (!ZYPCommonFunc.hasValue(svcAccsPmitNum) || !ZYPCommonFunc.hasValue(svcAccsPmitDescTxt)) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(svcAccsPmitNum);
            sb.append("-");
            sb.append(svcAccsPmitDescTxt);
            accessPermitsRef.getContent().add(sb.toString());
            
            accessPmt.getCSAAccessPermit().add(accessPermitsRef);
            accessPmtCnt++;
        }
        if (accessPmtCnt > 0) {
            task.setCSAAccessPermits(accessPmt);
        }
       // QC#59169 Mod End

        // CSAInstallCheck
        if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, SVC_ISTL_REQ_FLG))) {
            task.setCSAInstallChecklistRequired(true);
        // START 2018/02/08 K.Kojima [QC#21112,ADD]
        } else if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, SVC_DEINS_REQ_FLG))) {
            task.setCSAInstallChecklistRequired(true);
        // END 2018/02/08 K.Kojima [QC#21112,ADD]
        } else {
            task.setCSAInstallChecklistRequired(false);
        }
        // START 2017/12/11 M.Naito [QC#10040, ADD]
        // CSAInstallCheck
        if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, MACH_DOWN_FLG))) {
            task.setCSACustomerUpDown(true);
        } else {
            task.setCSACustomerUpDown(false);
        }
        // END 2017/12/11 M.Naito [QC#10040, ADD]
        // Add Start 2018/03/22 QC#18713
        String cellPhoNum = getString(taskInfoMap, CELL_PHO_NUM);
        if (ZYPCommonFunc.hasValue(cellPhoNum)) {
            task.setCSAMobilePhone(cellPhoNum);
        }
        // Add End 2018/03/22 QC#18713
        // START 2022/09/02 K.Kitachi [QC#60536, ADD]
        boolean isOfsSend = varConstMap.get(NSZC1070_OFS_SEND).equals(ZYPConstant.FLG_ON_Y);
        if (isOfsSend) {
            setTimeZone(task, taskInfoMap);
        }
        // END 2022/09/02 K.Kitachi [QC#60536, ADD]

        // Check Mandatory for Send Data
        if (!chkMandatoryForSendData(task, getString(taskInfoMap, SVC_TASK_NUM))) {
            return false;
        }
        return true;
    }

// START 2016/11/02 N.Arai [QC#15654, MOD]
// START 2016/11/15 N.Arai [QC#15654, MOD]
//    private boolean callMultipleOperations(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, String istlReqFlg, String svcTaskNum) {
//    private boolean callMultipleOperations(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, String istlReqFlg, String svcTaskNum, List<Map<String, Object>> taskNoteList, String glblCmpyCd, String CtryCd, String PostCd) {
// END 2016/11/02 N.Arai [QC#15654, MOD]
    // START 2018/01/31 K.Kojima [QC#21112,MOD]
    // private StandardOperations callMultipleOperations(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList,
    //         String istlReqFlg, String svcTaskNum, List<Map<String, Object>> taskNoteList, String glblCmpyCd, String CtryCd, String PostCd, String fsrVisitStsCd) {
    // Mod Start 2018/10/22 QC#28565
    // private StandardOperations callMultipleOperations(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList,
    //         String istlReqFlg, String deinsReqFlg, String svcTaskNum, List<Map<String, Object>> taskNoteList, String glblCmpyCd, String CtryCd, String PostCd, String fsrVisitStsCd) {
    // START 2023/07/14 K.Watanabe [QC#61310, MOD]
    // private StandardOperations callMultipleOperations(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList,
    //        String istlReqFlg, String deinsReqFlg, String svcTaskNum, List<Map<String, Object>> taskNoteList, String glblCmpyCd, String CtryCd, String PostCd, String fsrVisitStsCd, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg) {
    private StandardOperations callMultipleOperations(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList,
            String istlReqFlg, String deinsReqFlg, String svcTaskNum, List<Map<String, Object>> taskNoteList, String glblCmpyCd, String CtryCd, String PostCd, String fsrVisitStsCd, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg, String slsDt) {
    // END 2023/07/14 K.Watanabe [QC#61310, MOD]
    // Mod End 2018/10/22 QC#28565
    // END 2018/01/31 K.Kojima [QC#21112,MOD]
// END 2016/11/15 N.Arai [QC#15654, MOD]

        com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory objFactory = new com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory();

        StandardOperations standardOperations = objFactory.createStandardOperations();
        W6RequestedProperties props = objFactory.createW6RequestedProperties();

// START 2016/11/15 N.Arai [QC#15654, MOD]
        // Mod Start 2018/10/22 QC#28565
        // if (SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
        // START 2019/12/20 K.Fujimoto [QC#55106, DELL]
        // if (isMultOp(clickTaskSendRsltTMsg, fsrVisitStsCd)) {
        // END   2019/12/20 K.Fujimoto [QC#55106, MOD]
        // Mod End 2018/10/22 QC#28565
        // CSA_Modifications
        for (Map<String, Object> svcModPlnMap : svcModPlnList) {

            StandardOperation standardOperationMod = objFactory.createStandardOperation();
            standardOperationMod.setAction(ACTION);
            standardOperationMod.setRequestedProperties(props);

            CSAModification csaMod = new CSAModification();
            csaMod.setName(getString(svcModPlnMap, SVC_MOD_PLN_ID));
            // STRART 2016/10/04 Y.Zhang [QC#14918, MOD]
            BigDecimal timeSpentB;
            if (ZYPCommonFunc.hasValue(getString(svcModPlnMap, EST_SVC_LBOR_HOUR_MN))) {
                // get hours from the value of the EST_SVC_LBOR_HOUR_MN
                String timeSpentB1 = getString(svcModPlnMap, EST_SVC_LBOR_HOUR_MN).substring(0, 2);
                // get minutes from the value of the EST_SVC_LBOR_HOUR_MN
                String timeSpentB2 = getString(svcModPlnMap, EST_SVC_LBOR_HOUR_MN).substring(2);
                BigDecimal timeSpent1 = new BigDecimal(timeSpentB1);
                BigDecimal timeSpent2 = new BigDecimal(timeSpentB2);
                // minutes is changed hours
//START 2016/11/04 N.Arai [QC#15765, MOD]
//              timeSpent2 = timeSpent2.divide(new BigDecimal("60"), 2, BigDecimal.ROUND_HALF_UP);
                // hours is changed minutes
                timeSpent1 = timeSpent1.multiply(new BigDecimal("60"));
//END 2016/11/04 N.Arai [QC#15765, MOD]
                // the value of the EST_SVC_LBOR_HOUR_MN is changed to hours
                timeSpentB = timeSpent2.add(timeSpent1);
                // BigDecimal timeSpentB = getBigDecimal(svcModPlnMap,EST_SVC_LBOR_HOUR_MN);
            } else {
                timeSpentB = getBigDecimal(svcModPlnMap, EST_SVC_LBOR_HOUR_MN);
            }
            // END 2016/10/04 Y.Zhang [QC#14918, MOD]
            float timeSpentF = timeSpentB.floatValue();
            csaMod.setTimeSpent(timeSpentF);
            csaMod.setTaskCallID(getString(svcModPlnMap, SVC_TASK_NUM));
            csaMod.setItemNumber(getString(svcModPlnMap, MDSE_CD));
            csaMod.setSerialNumber(getString(svcModPlnMap, SER_NUM));

            BaseObjectWrapper bowMod = objFactory.createBaseObjectWrapper();
            bowMod.setObject(csaMod);
            standardOperationMod.setObject(bowMod);
            standardOperations.getOperation().add(standardOperationMod);
        }
        // CSA_Meter
        for (Map<String, Object> mtrInfoMap : mtrInfoList) {

            StandardOperation standardOperationMeter = objFactory.createStandardOperation();
            standardOperationMeter.setAction(ACTION);
            standardOperationMeter.setRequestedProperties(props);

            CSAMeter meter = new CSAMeter();
            // mod start 2016/09/12 CSA Defect#14357
            meter.setName(getString(mtrInfoMap, MTR_LB_NM));
            // mod end 2016/09/12 CSA Defect#14357
            meter.setLastReading(getBigDecimal(mtrInfoMap, READ_MTR_CNT).intValue());

            // QC#58154 Mod Start
            //meter.setLastReadingDate(changeDateFormat(getString(mtrInfoMap, MTR_READ_DT), null));
            if (ZYPCommonFunc.hasValue(getString(mtrInfoMap, MTR_READ_DT))) {
                meter.setLastReadingDate(changeDateFormat(getString(mtrInfoMap, MTR_READ_DT), null));
            } else {
            	meter.setLastReadingDate(changeDateFormat(getString(mtrInfoMap, SVC_TASK_RCV_DT), null));
            }
            // QC#58154 Mod End

            meter.setCurrentRead(getBigDecimal(mtrInfoMap, READ_MTR_CNT).intValue());
            // mod start 2016/07/06 CSA Defect#11338
            if (ZYPConstant.FLG_ON_Y.equals(getString(mtrInfoMap, TOT_MTR_FLG))) {
                // mod end 2016/07/06 CSA Defect#11338
                meter.setIsTotalMeter(true);
            } else {
                meter.setIsTotalMeter(false);
            }
//START 2016/11/17 N.Arai [QC#16020, MOD]
//            meter.setDailyMax(getBigDecimal(mtrInfoMap, MAX_COPY_PER_DAY_TOT_CNT).intValue());
            // mod start 2020/06/18 QC#57175
            // START 2019/10/21 K.Fujimoto [QC#53441, MOD]
            //if (!ZYPCommonFunc.hasValue((BigDecimal) mtrInfoMap.get(EXPECTED_MAX_COPIES))) {
            //if (!ZYPCommonFunc.hasValue((BigDecimal)mtrInfoMap.get(MAX_COPY_PER_DAY))){
                //meter.setDailyMax(ZYPCodeDataUtil.getNumConstValue(NSZC1070_MAX_DAILY_COPY, glblCmpyCd).intValue());
            //    meter.setDailyMax(1);
//            } else if (BigDecimal.ZERO.compareTo((BigDecimal)mtrInfoMap.get(MAX_COPY_PER_DAY)) == 0) {
//                meter.setDailyMax(ZYPCodeDataUtil.getNumConstValue(NSZC1070_MAX_DAILY_COPY, glblCmpyCd).intValue());
            //} else {
                //meter.setDailyMax(getBigDecimal(mtrInfoMap, MAX_COPY_PER_DAY).intValue());
            //    meter.setDailyMax(getBigDecimal(mtrInfoMap, EXPECTED_MAX_COPIES).intValue());
            //}
            // END   2019/10/21 K.Fujimoto [QC#53441, MOD]
            if (ZYPCommonFunc.hasValue((BigDecimal)mtrInfoMap.get(MAX_COPY_PER_DAY))) {
                meter.setDailyMax(getBigDecimal(mtrInfoMap, MAX_COPY_PER_DAY).intValue());
            } else {
                BigDecimal defMaxDailyCopy = ZYPCodeDataUtil.getNumConstValue(NSZC1070_MAX_DAILY_COPY, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defMaxDailyCopy)) {
                    meter.setDailyMax(defMaxDailyCopy.intValue());
                }
            }
            if (ZYPCommonFunc.hasValue((BigDecimal)mtrInfoMap.get(MAX_COPY_TEST_CNT))) {
                meter.setDailyMaxTest(getBigDecimal(mtrInfoMap, MAX_COPY_TEST_CNT).intValue());
            } else {
                BigDecimal defDailyMaxTest = ZYPCodeDataUtil.getNumConstValue(NSZC1070_MAX_TEST_COPY, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defDailyMaxTest)) {
                    meter.setDailyMaxTest(defDailyMaxTest.intValue());
                }
            }

            if (ZYPCommonFunc.hasValue((BigDecimal)mtrInfoMap.get(ADCV_HIGH))) {
                meter.setADCVHighDaily(getBigDecimal(mtrInfoMap, ADCV_HIGH).intValue());
            } else {
                BigDecimal defADCVHigh = ZYPCodeDataUtil.getNumConstValue(NSZC1070_ADCV_HIGH, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defADCVHigh)) {
                    meter.setADCVHighDaily(defADCVHigh.intValue());
                }
            }
            if (ZYPCommonFunc.hasValue((BigDecimal)mtrInfoMap.get(ADCV_LOW))) {
                meter.setADCVLowDaily(getBigDecimal(mtrInfoMap, ADCV_LOW).intValue());
            } else {
                BigDecimal defADCVLow = ZYPCodeDataUtil.getNumConstValue(NSZC1070_ADCV_LOW, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(defADCVLow)) {
                    meter.setADCVLowDaily(defADCVLow.intValue());
                }
            }
            // mod end 2020/06/18 QC#57175
//END 2016/11/17 N.Arai [QC#16020, MOD]
            meter.setTaskCallID(getString(mtrInfoMap, SVC_TASK_NUM));
            // del start 2016/06/21 CSA Defect#5528
            // meter.setCSAEngineerID(getString(mtrInfoMap, TECH_NM));
            // del end 2016/06/21 CSA Defect#5528

            BaseObjectWrapper bowMeter = objFactory.createBaseObjectWrapper();
            bowMeter.setObject(meter);
            standardOperationMeter.setObject(bowMeter);
            standardOperations.getOperation().add(standardOperationMeter);
        }

        // CSA_MeterInfo
        if (mtrInfoList != null && mtrInfoList.size() > 0) {

            StandardOperation standardOperationMeterInfo = objFactory.createStandardOperation();
            standardOperationMeterInfo.setAction(ACTION);
            standardOperationMeterInfo.setRequestedProperties(props);

            CSAMeterInfo csaMeterInfo = new CSAMeterInfo();
            csaMeterInfo.setTaskCallID(getString(mtrInfoList.get(0), SVC_TASK_NUM));
            csaMeterInfo.setTaskNumber(Integer.parseInt(getString(mtrInfoList.get(0), SVC_TASK_NUM)));
            csaMeterInfo.setSerialNumber(getString(mtrInfoList.get(0), SER_NUM));
//START 2016/11/01 N.Arai [QC#15678, MOD]
            if (!ZYPCommonFunc.hasValue((BigDecimal) mtrInfoList.get(0).get(EXPECTED_MAX_COPY_PER_DAY))) {
                csaMeterInfo.setExpectedMaxCopies(1);
            } else if ((BigDecimal.ZERO.compareTo((BigDecimal) mtrInfoList.get(0).get(EXPECTED_MAX_COPY_PER_DAY)) == 0)) {
                csaMeterInfo.setExpectedMaxCopies(1);
            } else {
                csaMeterInfo.setExpectedMaxCopies(getBigDecimal(mtrInfoList.get(0), EXPECTED_MAX_COPY_PER_DAY).intValue());
            }
//END 2016/11/01 N.Arai [QC#15678, MOD]
            CSACheckMaxReference csaCheckMaxRef = new CSACheckMaxReference();
            csaCheckMaxRef.getContent().add(getString(mtrInfoList.get(0), CHECK_MAX));
            csaMeterInfo.setCSACheckMax(csaCheckMaxRef);

            // mod start 2016/06/29 CSA Defect#11006
            Meters meterts = new Meters();
            for (Map<String, Object> mtrInfoMap : mtrInfoList) {
                CSAMeterReference csaMeterRef = new CSAMeterReference();
                csaMeterRef.getContent().add(objFactory.createCSAMeterReferenceTaskCallID(getString(mtrInfoMap, SVC_TASK_NUM)));
                // mod start 2016/09/12 CSA Defect#14357
                csaMeterRef.getContent().add(objFactory.createCSAMeterReferenceName(getString(mtrInfoMap, MTR_LB_NM)));
                // mod end 2016/09/12 CSA Defect#14357
                meterts.getCSAMeter().add(csaMeterRef);
            }
            csaMeterInfo.setMeters(meterts);
            // mod end 2016/06/29 CSA Defect#11006

            BaseObjectWrapper bowMeterInfo = objFactory.createBaseObjectWrapper();
            bowMeterInfo.setObject(csaMeterInfo);
            standardOperationMeterInfo.setObject(bowMeterInfo);
            standardOperations.getOperation().add(standardOperationMeterInfo);
        }

        // InstallCheckList
        // START 2018/01/31 K.Kojima [QC#21112,MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(istlReqFlg) && installCheckList != null && installCheckList.size() > 0) {
        if ((ZYPConstant.FLG_ON_Y.equals(istlReqFlg) || ZYPConstant.FLG_ON_Y.equals(deinsReqFlg)) && installCheckList != null && installCheckList.size() > 0) {
        // END 2018/01/31 K.Kojima [QC#21112,MOD]
            for (Map<String, Object> installCheckMap : installCheckList) {
                StandardOperation standardOperationInstallCheck = objFactory.createStandardOperation();
                standardOperationInstallCheck.setAction(ACTION);
                standardOperationInstallCheck.setRequestedProperties(props);

                CSAInstallCheckList csaInstallCheckList = new CSAInstallCheckList();

                String config = null;
                if (ZYPCommonFunc.hasValue(getBigDecimal(installCheckMap, SVC_CONFIG_MSTR_PK))) {
                    config = (getBigDecimal(installCheckMap, SVC_CONFIG_MSTR_PK)).toString();
                }
                csaInstallCheckList.setConfiguration(config);
                csaInstallCheckList.setItem(getString(installCheckMap, MDSE_CD));
                csaInstallCheckList.setSerialNumber(getString(installCheckMap, SER_NUM));
                // add start 2016/09/07 CSA Defect#14199
                csaInstallCheckList.setTaskCallID(svcTaskNum);
                // add end 2016/09/07 CSA Defect#14199
                // START 2016/12/12 N.Arai [QC#16511, MOD]
                if (ZYPCommonFunc.hasValue(getString(installCheckMap, T_MDL_NM))) {
                    csaInstallCheckList.setModelNumber(getString(installCheckMap, T_MDL_NM));
                }
                if (ZYPCommonFunc.hasValue(getString(installCheckMap, MDSE_DESC_SHORT_TXT))) {
                    // START 2022/10/19 [QC#60712, MOD]
//                    csaInstallCheckList.setDescription(getString(installCheckMap, MDSE_DESC_SHORT_TXT));
                    csaInstallCheckList.setDescription(ofsMultiByteConvert(getString(installCheckMap, MDSE_DESC_SHORT_TXT)));
                    // END   2022/10/19 [QC#60712, MOD]
                }
                // END 2016/12/12 N.Arai [QC#16511, MOD]
                // START 2017/08/25 K.Kojima [QC#20667,ADD]
                String istlChkVerFlg = getIstlChkVerFlg(glblCmpyCd, svcTaskNum, csaInstallCheckList.getItem(), csaInstallCheckList.getSerialNumber());
                if (istlChkVerFlg != null && ZYPConstant.FLG_ON_Y.equals(istlChkVerFlg)) {
                    csaInstallCheckList.setVerified(true);
                }
                // END 2017/08/25 K.Kojima [QC#20667,ADD]
                // START 2018/02/19 M.Naito [QC#21112, MOD]
                // START 2018/01/31 K.Kojima [QC#21112,ADD]
                // START 2023/07/14 K.Watanabe [QC#61310, MOD]
                // if (!SVC_MACH_MSTR_STS.INSTALLED.equals(getString(installCheckMap, SVC_MACH_MSTR_STS_CD))) {
                if (!SVC_MACH_MSTR_STS.INSTALLED.equals(getString(installCheckMap, SVC_MACH_MSTR_STS_CD)) && !isShowRoomMachine(glblCmpyCd, svcTaskNum, slsDt)) {
                // END 2023/07/14 K.Watanabe [QC#61310, MOD]
                    if (ZYPConstant.FLG_ON_Y.equals(istlReqFlg)) {
                        csaInstallCheckList.setNewItem(true);
                    } else if (ZYPConstant.FLG_ON_Y.equals(deinsReqFlg)) {
                        csaInstallCheckList.setRemoveItem(true);
                    }
                }
                // END 2018/01/31 K.Kojima [QC#21112,ADD]
                // END 2018/02/19 M.Naito [QC#21112, MOD]

                BaseObjectWrapper bowInstallCheck = objFactory.createBaseObjectWrapper();
                bowInstallCheck.setObject(csaInstallCheckList);
                standardOperationInstallCheck.setObject(bowInstallCheck);
                standardOperations.getOperation().add(standardOperationInstallCheck);
            }
        }
// END 2016/11/15 N.Arai [QC#15654, MOD]

// START 2016/11/02 N.Arai [QC#15654, MOD]
        // CSA_Notes
        if (taskNoteList != null && taskNoteList.size() > 0) {
            for (Map<String, Object> taskNoteMap : taskNoteList) {
                // CSA_Notes
                StandardOperation standardOperationTaskNote = objFactory.createStandardOperation();
                standardOperationTaskNote.setAction(ACTION);
                standardOperationTaskNote.setRequestedProperties(props);

                CSATaskNote csaTaskNote = new CSATaskNote();
                // NoteType
                CSANoteTypeReference csaNoteTypeRef = new CSANoteTypeReference();
                csaNoteTypeRef.getContent().add(getString(taskNoteMap, SVC_MEMO_TP_DESC_TXT));
                csaTaskNote.setNoteType(csaNoteTypeRef);
                // CreatedAt
                SvcTimeZoneInfo createdAtTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, getString(taskNoteMap, LAST_UPD_TS), CtryCd, PostCd);
                if (createdAtTs != null) {
                    csaTaskNote.setCreatedAt(changeDateFormat(createdAtTs.getDateTime(), createdAtTs.getTimeZone()));
                }
                // Message
                csaTaskNote.setMessage(getString(taskNoteMap, SVC_CMNT_TXT));
                // SubmittedBy
                csaTaskNote.setSubmittedBy(getString(taskNoteMap, LAST_UPD_USR_ID));
                // TaskCallID
                csaTaskNote.setTaskCallID(getString(taskNoteMap, SVC_TASK_NUM));
                // IsCreatedFromIntegration
                csaTaskNote.setIsCreatedFromIntegration(true);
                // NoteSource
                CSANoteSourceReference csaNoteSrcRef = new CSANoteSourceReference();
                csaNoteSrcRef.getContent().add(getString(taskNoteMap, SVC_CALL_SRC_TP_DESC_TXT));
                csaTaskNote.setNoteSource(csaNoteSrcRef);

                BaseObjectWrapper bowTaskNote = objFactory.createBaseObjectWrapper();
                bowTaskNote.setObject(csaTaskNote);
                standardOperationTaskNote.setObject(bowTaskNote);
                standardOperations.getOperation().add(standardOperationTaskNote);
            }
        }
// END 2016/11/02 N.Arai [QC#15654, MOD]

        return standardOperations;
/*
        // mod start 2016/12/20 CSA Defect#16514
        boolean hasClickError = false;
        try {
            // Execute WMB
            ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();
            ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
            emo.setOneTransaction(true);
            emo.setContinueOnError(true);
            emo.setOperations(standardOperations);

            ExecuteMultipleOperationsResponse response = null;
            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            response = proxy.executeMultipleOperations(emo);
            response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]

            //This is a temporary solution for QC#16514
            //if (response == null) {
            //    return false;
            //}
            if (response != null) {
                List<OperationResult> opeResults = response.getOperations().getSucceededGetObjectOperationOrOperationOrFailedOperation();
                for (OperationResult opeResult : opeResults) {
                    S21InfoLogOutput.println("NSZC1070 : multiple operation result:" + opeResult.getAction());
                }
            } else {
                S21InfoLogOutput.println("NSZC1070 : ExecuteMultipleOperationsResponse is null.");
                hasClickError= true;
            }
            //This is a temporary solution for QC#16514

        } catch (RemoteException re) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {svcTaskNum});
            S21InfoLogOutput.println(errMsg);
            re.printStackTrace();
            hasClickError= true;
        } catch (Exception e) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {svcTaskNum});
            S21InfoLogOutput.println(errMsg);
            e.printStackTrace();
            hasClickError= true;
        } catch (Throwable t) {
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {svcTaskNum});
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            hasClickError= true;
        }

// START 2016/11/07 N.Arai [QC#15784, MOD]
        if (taskNoteList != null && taskNoteList.size() > 0) {
            for (Map<String, Object> taskNoteMap : taskNoteList) {
                SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
                setValue(inMsg.glblCmpyCd, getString(taskNoteMap, "GLBL_CMPY_CD"));
                setValue(inMsg.svcMemoPk, getBigDecimal(taskNoteMap, "SVC_MEMO_PK"));
                inMsg = (SVC_MEMOTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    throw new S21AbendException(NSAM0045E, new String[] {"SVC_MEMO"});
                }

                if (hasClickError) {
                    setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.ERROR);
                } else {
                    setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.PROCESSED);
                }
                EZDTBLAccessor.update(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0031E, new String[] {"SVC_MEMO"});
                }
            }
        }
// END 2016/11/07 N.Arai [QC#15784, MOD]
        if (hasClickError) {
            return false;
        }
        return true;
        // mod end 2016/12/20 CSA Defect#16514
*/
    }

// START 2016/11/02 N.Arai [QC#15654, MOD]
    private Object getTaskNoteListParam(Map<String, Object> paramMap) {
        paramMap.put("system", SUBMITTED_BY_SYSTEM);
// START 2016/11/15 N.Arai [QC#15784, MOD]
        paramMap.put("procCdNotProcessed", MBL_INTFC_PROC.NOT_PROCESSED);
        // START 2022/05/23 [QC#60058, ADD]
        paramMap.put("procCdError", MBL_INTFC_PROC.ERROR);
        // END   2022/05/23 [QC#60058, ADD]
        paramMap.put("mblIntfcFlgY", ZYPConstant.FLG_ON_Y);
// END 2016/11/15 N.Arai [QC#15784, MOD]
        return paramMap;
    }
// END 2016/11/02 N.Arai [QC#15654, MOD]

    private void setErrMsg(String msgId) {
        setErrMsg(msgId, null);
    }
    private void setErrMsg(String msgId, String svcTaskNum) {
        this.msgMap.addXxMsgId(msgId);
        if (varConstMap == null || ZYPConstant.FLG_ON_Y.equals((String) varConstMap.get(NSZC1070_ERR_LOG))) {
            String errMsg = S21MessageFunc.clspGetMessage(msgId);
            String msg = S21MessageFunc.clspGetMessage(NSZM1017E, new String[]{errMsg, svcTaskNum});
            S21InfoLogOutput.println(msg);
        }
    }
    private void setInfoMsg(String msgId, String svcTaskNum) {
        if (varConstMap == null || ZYPConstant.FLG_ON_Y.equals((String) varConstMap.get(NSZC1070_INFO_LOG))) {
            String errMsg = S21MessageFunc.clspGetMessage(msgId);
            String msg = S21MessageFunc.clspGetMessage(NSZM1018W, new String[]{errMsg, svcTaskNum});
            S21InfoLogOutput.println(msg);
        }
    }

    private boolean getVarConstMap(String glblCmpyCd) {
        boolean noErrFlg = true;
        varConstMap = new HashMap<String, String>();
        String timeFormat = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_TIME_FORMAT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(timeFormat)) {
            setErrMsg(NSZM0102E);
            noErrFlg = false;
        }
        varConstMap.put(NSZC1070_TIME_FORMAT, timeFormat);

        String calendar = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_CALENDAR, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(calendar)) {
            setErrMsg(NSZM0102E);
            noErrFlg = false;
        }
        varConstMap.put(NSZC1070_CALENDAR, calendar);

        String gisDataSource = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_GIS_DATA_SRC, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(gisDataSource)) {
            setErrMsg(NSZM0102E);
            noErrFlg = false;
        }
        varConstMap.put(NSZC1070_GIS_DATA_SRC, gisDataSource);
        String infoLogFlg = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_INFO_LOG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(infoLogFlg)) {
            infoLogFlg = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_INFO_LOG, infoLogFlg);
        String errLogFlg = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_ERR_LOG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(errLogFlg)) {
            errLogFlg = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_ERR_LOG, errLogFlg);
        // Add Start 2019/08/22 QC#51206 K.Fujimoto
        String sendMailExcld = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_SEND_MAIL_EXCLD_COND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(sendMailExcld)) {
            sendMailExcld = "";
        }
        varConstMap.put(NSZC1070_SEND_MAIL_EXCLD_COND, sendMailExcld);

        String rtryExcldCond = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_RTRY_EXCLD_COND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(rtryExcldCond)) {
            rtryExcldCond = "";
        }
        varConstMap.put(NSZC1070_RTRY_EXCLD_COND, rtryExcldCond);
        // Add End   2019/08/22 QC#51206 K.Fujimoto
        // START 2019/10/21 K.Fujimoto [QC#53441, ADD]
        String modsSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_MODS_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(modsSend)) {
            modsSend = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_MODS_SEND, modsSend);

        String mtrSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_MTR_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(mtrSend)) {
            mtrSend = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_MTR_SEND, mtrSend);

        String instlChkListSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_INSTL_CHK_LIST_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(instlChkListSend)) {
            instlChkListSend = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_INSTL_CHK_LIST_SEND, instlChkListSend);
        // END   2019/10/21 K.Fujimoto [QC#53441, ADD]
        // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
        String l2ModsSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_L2_MODS_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(l2ModsSend)) {
            l2ModsSend = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_L2_MODS_SEND, l2ModsSend);

        String l2MtrSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_L2_MTR_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(l2MtrSend)) {
            l2MtrSend = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_L2_MTR_SEND, l2MtrSend);

        String l2InstlChkListSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_L2_ISTL_CHK_LST_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(l2InstlChkListSend)) {
            l2InstlChkListSend = ZYPConstant.FLG_ON_Y;
        }
        varConstMap.put(NSZC1070_L2_ISTL_CHK_LST_SEND, l2InstlChkListSend);
        // END   2019/11/18 K.Fujimoto [QC#54391, ADD]

        // START 2022/09/02 K.Kitachi [QC#60536, ADD]
        String ofsSend = ZYPCodeDataUtil.getVarCharConstValue(NSZC1070_OFS_SEND, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ofsSend)) {
            ofsSend = ZYPConstant.FLG_OFF_N;
        }
        varConstMap.put(NSZC1070_OFS_SEND, ofsSend);
        // END 2022/09/02 K.Kitachi [QC#60536, ADD]

        // START 2022/10/19 [QC#60712, ADD]
        String strOFSMultiByteConvertFlg = ZYPCodeDataUtil.getVarCharConstValue("OFS_MULTI_BYTE_CONVERT_FLG", glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(strOFSMultiByteConvertFlg)) {
            ofsMultiByteConvertFlg = true;
        }
        // END   2022/10/19 [QC#60712, ADD]
        return noErrFlg;
    }

    private boolean getNumConstMap(String glblCmpyCd) {
        boolean noErrFlg = true;
        numConstMap = new HashMap<String, BigDecimal>();
        BigDecimal maxReadrate = ZYPCodeDataUtil.getNumConstValue(NSZC1070_MAX_READ_RATE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(maxReadrate)) {
            setErrMsg(NSZM1015E);
            noErrFlg = false;
        }
        numConstMap.put(NSZC1070_MAX_READ_RATE, maxReadrate);

        BigDecimal mdlDurnTmSec = ZYPCodeDataUtil.getNumConstValue(NSZC1070_MDL_DURN_TM_SEC, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(mdlDurnTmSec)) {
            setErrMsg(NSZM1015E);
            noErrFlg = false;
        }
        numConstMap.put(NSZC1070_MDL_DURN_TM_SEC, mdlDurnTmSec);

        // add start 2020/03/03 QC#56123
        BigDecimal mtrUpThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_UP_THRHD_FCT_NUM, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(mtrUpThrhdFctNum)) {
            mtrUpThrhdFctNum = BigDecimal.ONE;
        }
        numConstMap.put(NSXC0030_MTR_UP_THRHD_FCT_NUM, mtrUpThrhdFctNum);
        // add end 2020/03/03 QC#56123
        // add start 2020/06/18 QC#57175
        BigDecimal mtrLowThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_LOW_THRHD_FCT_NUM, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(mtrLowThrhdFctNum)) {
            mtrLowThrhdFctNum = BigDecimal.ONE;
        }
        numConstMap.put(NSXC0030_MTR_LOW_THRHD_FCT_NUM, mtrLowThrhdFctNum);
        // add end 2020/06/18 QC#57175

        return noErrFlg;
    }
    /**
     * updFsrEvent.
     * @param rs ResultSet
     * @return boolean
     */
    private boolean updFsrEvent(NSZC107001PMsg pMsg, Map<String, Object> taskInfoMap, boolean isErr) {

        // get FSR Event
        Map<String, Object> params = new HashMap<String, Object>();
        String svcTaskNum = pMsg.svcTaskNum.getValue();

        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("svcTaskNum", svcTaskNum);
        // Add Start 2018/10/24 QC#28565
        params.put("notProc", MBL_INTFC_PROC.NOT_PROCESSED);
        params.put("error", MBL_INTFC_PROC.ERROR);
        // Add End 2018/10/24 QC#28565
        List<Map<String, Object>> fsrEventList = ssmClient.queryObjectList("getFsrEvent", params);

        // START 2022/05/23 [QC#60058, ADD]
        String fsrNum = getString(taskInfoMap, FSR_NUM);
        CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg = getClickTaskSendRsltTMsg(pMsg.glblCmpyCd.getValue(), fsrNum, svcTaskNum);
        if (clickTaskSendRsltTMsg != null && !PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.rtrySendProcStsCd.getValue())) {
            isErr = true;
        }
        // END   2022/05/23 [QC#60058, ADD]

        List<FSR_EVENTTMsg> updateList = new ArrayList<FSR_EVENTTMsg>();
        for (Map<String, Object> fsrEventMap : fsrEventList) {
            FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventPk, getBigDecimal(fsrEventMap, FSR_EVENT_PK));
            fsrEventTMsg = (FSR_EVENTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(fsrEventTMsg);

            if (isErr) {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, MBL_INTFC_PROC.ERROR);
            } else {
                ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, MBL_INTFC_PROC.PROCESSED);
            }

            updateList.add(fsrEventTMsg);
        }
        for (FSR_EVENTTMsg fsrEventTMsg : updateList) {
            S21ApiTBLAccessor.update(fsrEventTMsg);
            if (!checkRtnCodeForUpdate(pMsg, fsrEventTMsg)) {
                return false;
            }
        }

        if (getString(taskInfoMap, SVC_TASK_STS_CD).equals(SVC_TASK_STS.OPEN)) {
            SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
            svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcTaskTMsg);

            ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskStsCd, SVC_TASK_STS.IN_PROCESS);
            S21ApiTBLAccessor.update(svcTaskTMsg);
            if (!checkRtnCodeForUpdate(pMsg, svcTaskTMsg)) {
                return false;
            }
        }

        return true;
    }
    /**
     * Get Parameter to Query Meter Information.
     * @return Query Parameter
     */
    // mod start 2020/03/03 QC#56123
    //private Map<String, Object> getMtrInfoParam(Map<String, Object> paramMap) {
    private Map<String, Object> getMtrInfoParam(Map<String, Object> paramMap, Map<String, Object> taskInfoMap, BigDecimal mtrUpThrhdFctNum, BigDecimal mtrLowThrhdFctNum) {
    // mod end 2020/03/03 QC#56123

        paramMap.put("all", CHECK_MAX_ALL);
        paramMap.put("total", CHECK_MAX_TOTAL);
        paramMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        // add start 2020/03/03 QC#56123
        if (ZYPConstant.FLG_OFF_N.equals(getString(taskInfoMap, SVC_ISTL_REQ_FLG))) {
            List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
            paramMap.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        }
        paramMap.put("mtrUpThrhdFctNum", mtrUpThrhdFctNum);
        // add end 2020/03/03 QC#56123
        // add start 2020/06/18 QC#57175
        paramMap.put("mtrLowThrhdFctNum", mtrLowThrhdFctNum);
        // add end 2020/06/18 QC#57175
        return paramMap;
    }
    /**
     * Get Parameter to Query Service Mod Plan.
     * @return Query Parameter
     */
    private Map<String, Object> getSvcModPlnParam(Map<String, Object> paramMap) {

        paramMap.put("stsClosed", SVC_MOD_PROC_STS.CLOSED);
        paramMap.put("stsCancelled", SVC_MOD_PROC_STS.CANCELLED);
        // START 2018/08/08 K.Kitachi [QC#27460, ADD]
        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
        paramMap.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);
        // END 2018/08/08 K.Kitachi [QC#27460, ADD]
        // add start 2019/04/02 QC#31027
        paramMap.put("maxDate", MAX_DATE);
        // add end 2019/04/02 QC#31027
        // add start 2020/04/21 QC#56328-1
        paramMap.put("completed", SVC_TASK_STS.COMPLETED);
        paramMap.put("closed", SVC_TASK_STS.CLOSED);
        paramMap.put("cancelled", SVC_TASK_STS.CANCELLED);
        paramMap.put("modification", SVC_TM_EVENT.MODIFICATION);
        // add end 2020/04/21 QC#56328-1

        return paramMap;
    }
    /**
     * Get Parameter to Query Install CheckList.
     * @return Query Parameter
     */
    private Map<String, Object> getInstallCheckListParam(Map<String, Object> paramMap) {

        // mod start 2019/07/12 QC#51427
        //paramMap.put("machStsCd", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        //// START 2018/02/19 M.Naito [QC#21112, ADD]
        //paramMap.put("machStsCdInstl", SVC_MACH_MSTR_STS.INSTALLED);
        //// END 2018/02/19 M.Naito [QC#21112, ADD]
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.INSTALLED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        // mod end 2019/07/12 QC#51427
        // START 2018/08/06 K.Kitachi [QC#26049, ADD]
        paramMap.put("svcMachTpCd", SVC_MACH_TP.ACCESSORY);
        // END 2018/08/06 K.Kitachi [QC#26049, ADD]
        // START 2023/07/14 K.Watanabe [QC#61310, ADD]
        List<String> svcMachMstrStsCdSearchShowRoomList = new ArrayList<String>();
        svcMachMstrStsCdSearchShowRoomList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdSearchShowRoomList.add(SVC_MACH_MSTR_STS.REMOVED);
        // START 2023/09/13 K.Watanabe [QC#61310, MOD]
        //paramMap.put("svcMachMstrStsCdSearchShowRoom", svcMachMstrStsCdSearchShowRoomList);
        paramMap.put("svcMachMstrStsCdSearchShowRoomList", svcMachMstrStsCdSearchShowRoomList);
        // END 2023/09/13 K.Watanabe [QC#61310, MOD]
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        // END 2023/07/14 K.Watanabe [QC#61310, ADD]

        return paramMap;
    }

    // START 2023/06/16 K.Watanabe [QC#61552, ADD]
    private boolean isSendModMessage(String glblCmpyCd, String svcTaskNum) {
        boolean isSendModMessage = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        BigDecimal svcModSendClickCnt = (BigDecimal) ssmClient.queryObject("getSvcModSendClickCnt", paramMap);
        if (svcModSendClickCnt != null && svcModSendClickCnt.compareTo(BigDecimal.ZERO) > 0) {
            isSendModMessage = true;
        }
        return isSendModMessage;
    }
    // END 2023/06/16 K.Watanabe [QC#61552, ADD]

    // START 2018/01/31 K.Kojima [QC#21112,ADD]
    // del start 2019/07/12 QC#51427
//    /**
//     * Get Parameter to Query Install CheckList.
//     * @return Query Parameter
//     */
//    private Map<String, Object> getInstallCheckListParamForRemoval(Map<String, Object> paramMap) {
//
//        paramMap.put("machStsCd", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
//        // START 2018/02/19 M.Naito [QC#21112, ADD]
//        paramMap.put("machStsCdInstl", SVC_MACH_MSTR_STS.INSTALLED);
//        // END 2018/02/19 M.Naito [QC#21112, ADD]
//        // START 2018/08/06 K.Kitachi [QC#26049, ADD]
//        paramMap.put("svcMachTpCd", SVC_MACH_TP.ACCESSORY);
//        // END 2018/08/06 K.Kitachi [QC#26049, ADD]
//
//        return paramMap;
//    }
    // del end 2019/07/12 QC#51427
    // END 2018/01/31 K.Kojima [QC#21112,ADD]

    /**
     * changeDateFormat
     * @param dateStr String
     * @param ctryCd String
     * @param postCd String
     * @return BigDecimal
     */
//    private XMLGregorianCalendar changeDateFormat(String dateStr) {
  private XMLGregorianCalendar changeDateFormat(String dateStr, String tzId) {
        XMLGregorianCalendar xmlgc = null;
        try {

            if (!ZYPCommonFunc.hasValue(dateStr)) {
                return xmlgc;
            }

            if (dateStr.length() == LEN_8) {
                dateStr = dateStr.concat(DEF_TS);
            }

            // Date --> GregorianCalendar
            SimpleDateFormat format = new SimpleDateFormat(varConstMap.get(NSZC1070_TIME_FORMAT));
            ParsePosition pos = new ParsePosition(0);
            Date formatDate = format.parse(dateStr, pos);
            if (formatDate == null) {
                return xmlgc;
            } else {
                GregorianCalendar calendar = new GregorianCalendar();
                if (ZYPCommonFunc.hasValue(tzId)) {
                    calendar.setTimeZone(TimeZone.getTimeZone(tzId));
                }
                DatatypeFactory factory = DatatypeFactory.newInstance();

                Calendar parsedCalendar = Calendar.getInstance();
                parsedCalendar.setTime(formatDate);

                // set date and time from time parameter and
                calendar.set(parsedCalendar.get(Calendar.YEAR),
                                parsedCalendar.get(Calendar.MONTH),
                                parsedCalendar.get(Calendar.DATE),
                                parsedCalendar.get(Calendar.HOUR_OF_DAY),
                                parsedCalendar.get(Calendar.MINUTE),
                                parsedCalendar.get(Calendar.SECOND));
                calendar.set(Calendar.MILLISECOND, parsedCalendar.get(Calendar.MILLISECOND));

                xmlgc = factory.newXMLGregorianCalendar(calendar);
            }
            return xmlgc;
        } catch (DatatypeConfigurationException re) {
            return xmlgc;
        }
    }

  // START 2019/08/15 K.Fujimoto [QC#51206,DEL]
  // Dead Logic.
//  private boolean callTaskInfo(Task task, ObjectFactory of, Map<String, Object> taskInfoMap) {
//      try {
//          // Invoke 
//          ClickSoftwareService clickSoftwareServiceProxy = new ClickSoftwareService();
//
//          task.setNumber(1);
//
//          String fsrVisitStsCd = getString(taskInfoMap, FSR_VISIT_STS_CD);
//          if (SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
//
//              ProcessTaskEx request = new ProcessTaskEx();
//              request.setTask(task);
//              request.setReturnAssignment(true);
//              request.setReturnSchedulingError(false);
//              clickSoftwareServiceProxy.processTaskEx(request);
//
//// mod start 2016/08/03 CSA Defect#11134
////          } else if (SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
////
////              DeleteTask request = new DeleteTask();
////              TaskReference taskRef = new TaskReference();
////              taskRef.getContent().add(of.createTaskReferenceCallID(getString(taskInfoMap, SVC_TASK_NUM)));
////              request.setTask(taskRef);
////              clickSoftwareServiceProxy.deleteTask(request);
//// mod end 2016/08/03 CSA Defect#11134
//          } else {
//
//              UpdateTask request = new UpdateTask();
//              request.setTask(task);
//              clickSoftwareServiceProxy.updateTask(request);
//          }
//          return true;
//      } catch (RemoteException re) {
//          String errMsg = S21MessageFunc.clspGetMessage(NSZM1016E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
//          S21InfoLogOutput.println(errMsg);
//          re.printStackTrace();
//          return false;
//      } catch (Exception e) {
//          String errMsg = S21MessageFunc.clspGetMessage(NSZM1016E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
//          S21InfoLogOutput.println(errMsg);
//          e.printStackTrace();
//          return false;
//      } catch (Throwable t) {
//          String errMsg = S21MessageFunc.clspGetMessage(NSZM1016E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
//          S21InfoLogOutput.println(errMsg);
//          t.printStackTrace();
//          return false;
//      }
//  }
  // END   2019/08/15 K.Fujimoto [QC#51206,DEL]

  // START 2022/03/03 K.Kitachi [QC#59700, MOD]
//  private UpdateTask callUpdateTask(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, Map<String, Object> taskInfoMap) {
  private UpdateTask callUpdateTask(List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> csaTaskNoteList, List<Map<String, Object>> installCheckList, Map<String, Object> taskInfoMap) {
  // END 2022/03/03 K.Kitachi [QC#59700, MOD]
      //try {
          Task task = new Task();

          // START 2019/12/20 K.Fujimoto [QC#55106, ADD]
          boolean isUpdateTask = false;
          // END   2019/12/20 K.Fujimoto [QC#55106, ADD]
          task.setCallID(getString(taskInfoMap, SVC_TASK_NUM));
          task.setNumber(1);

          // CSA_Meter
          if (mtrInfoList != null && mtrInfoList.size() > 0) {
              CSAMeterInfoReference csaMeterInfoRef = new CSAMeterInfoReference();
              //csaMeterInfoRef.setKey(Integer.parseInt(getString(mtrInfoList.get(0), SVC_TASK_NUM)));
              csaMeterInfoRef.getContent().add(getString(mtrInfoList.get(0), SVC_TASK_NUM));
              task.setCSAMeterInfo(csaMeterInfoRef);
              // START 2019/12/20 K.Fujimoto [QC#55106, ADD]
              isUpdateTask = true;
              // END   2019/12/20 K.Fujimoto [QC#55106, ADD]
          }

          // CSA_Modifications
          if (svcModPlnList != null && svcModPlnList.size() > 0) {
              CSAModificationReference modRef = new CSAModificationReference();
              //modRef.setKey(Integer.parseInt(getString(svcModPlnList.get(0), (SVC_TASK_NUM))));
              modRef.getContent().add(getString(svcModPlnList.get(0), (SVC_TASK_NUM)));

              CSAModifications mod = new CSAModifications();
              mod.getCSAModification().add(modRef);
              task.setCSAModifications(mod);
              // START 2019/12/20 K.Fujimoto [QC#55106, ADD]
              isUpdateTask = true;
              // END   2019/12/20 K.Fujimoto [QC#55106, ADD]
          }

          // START 2022/03/03 K.Kitachi [QC#59700, ADD]
          // CSA_Notes
          if (csaTaskNoteList != null && csaTaskNoteList.size() > 0) {
              CSATaskNoteReference noteRef = new CSATaskNoteReference();
              noteRef.getContent().add(getString(csaTaskNoteList.get(0), (SVC_TASK_NUM)));

              CSANotes note = new CSANotes();
              note.getCSANote().add(noteRef);
              task.setCSANotes(note);
              isUpdateTask = true;
          }
          // END 2022/03/03 K.Kitachi [QC#59700, ADD]

          // CSAInstallCheckList
          // START 2018/01/31 K.Kojima [QC#21112,MOD]
          // if (ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, SVC_ISTL_REQ_FLG)) && installCheckList != null && installCheckList.size() > 0) {
          if ((ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, SVC_ISTL_REQ_FLG)) || ZYPConstant.FLG_ON_Y.equals(getString(taskInfoMap, SVC_DEINS_REQ_FLG))) && installCheckList != null && installCheckList.size() > 0) {
          // END 2018/01/31 K.Kojima [QC#21112,MOD]
              CSAInstallCheckListReference installCheckListReference = new CSAInstallCheckListReference();
              //installCheckListReference.setKey(Integer.parseInt(getString(taskInfoMap, SVC_TASK_NUM)));
              installCheckListReference.getContent().add(getString(taskInfoMap, SVC_TASK_NUM));

              com.canon.usa.s21.integration.service.clicksoftware.type.Task.CSAInstallCheckList csaInstallCheckList = new com.canon.usa.s21.integration.service.clicksoftware.type.Task.CSAInstallCheckList();
              csaInstallCheckList.getInstallCheckList().add(installCheckListReference);
              task.setCSAInstallCheckList(csaInstallCheckList);
              // START 2019/12/20 K.Fujimoto [QC#55106, ADD]
              isUpdateTask = true;
              // END   2019/12/20 K.Fujimoto [QC#55106, ADD]
          }

          // START 2019/12/20 K.Fujimoto [QC#55106, ADD]
          if (!isUpdateTask) {
              return null;
          }
          // END   2019/12/20 K.Fujimoto [QC#55106, ADD]
          // Invoke
          //ClickSoftwareService clickSoftwareServiceProxy = new ClickSoftwareService();

          UpdateTask request = new UpdateTask();
          request.setTask(task);
          //clickSoftwareServiceProxy.updateTask(request);

          return request;
/*
      } catch (RemoteException re) {
          String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
          S21InfoLogOutput.println(errMsg);
          re.printStackTrace();
          return false;

      } catch (Exception e) {
          String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
          S21InfoLogOutput.println(errMsg);
          e.printStackTrace();
          return false;

      } catch (Throwable t) {
          String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
          S21InfoLogOutput.println(errMsg);
          t.printStackTrace();
          return false;
      }
*/
  }
  /**
   * <pre>
   * Check Mandatory For Send Data
   * </pre>
   * @param msgMap S21ApiMessageMap
   * @param invHdrMap Map<Object, Object>
   */
  private boolean chkMandatoryForSendData(Task task, String svcTaskNum) {

      boolean returnValue = true;

      if (!ZYPCommonFunc.hasValue(task.getCallID())) {
          setErrMsg(NSZM1001W, svcTaskNum);
          returnValue = false;
      }
      TaskStatusReference taskStsRef = task.getStatus();
      if (!chkParamObject(taskStsRef)) {
          setErrMsg(NSZM1002W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getCSAMachineSerialNo())) {
          setErrMsg(NSZM1003W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getCustomerEmail())) {
          setErrMsg(NSZM1004W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getExternalRefID())) {
          setErrMsg(NSZM1005W, svcTaskNum);
          returnValue = false;
      }
      TaskTypeReference taskTypeRef = task.getTaskType();
      if (!chkParamObject(taskTypeRef)) {
          setErrMsg(NSZM1006W, svcTaskNum);
          returnValue = false;
      }
      DistrictReference distRef = task.getDistrict();
      if (!chkParamObject(distRef)) {
          setErrMsg(NSZM1007W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getStreet())) {
          setErrMsg(NSZM1008W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getCity())) {
          setErrMsg(NSZM1009W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getState())) {
          setErrMsg(NSZM1010W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getPostcode())) {
          setErrMsg(NSZM1011W, svcTaskNum);
          returnValue = false;
      }
      CountryReference ctryRef = task.getCountryID();
      if (!chkParamObject(ctryRef)) {
          setErrMsg(NSZM1012W, svcTaskNum);
          returnValue = false;
      }
      if (!ZYPCommonFunc.hasValue(task.getCSACustomerName())) {
          setErrMsg(NSZM1013W, svcTaskNum);
          returnValue = false;
      }
      // del start 2016/06/29 CSA Defect#6676
      // CSATerritoryMapperReference ctmr = task.getCSATerritoryMapper();
      // if (!chkParamObject(ctmr)) {
      //    setErrMsg(NSZM1014W, svcTaskNum);
      //    returnValue = false;
      // }
      // del end 2016/06/29 CSA Defect#6676

      return returnValue;
  }

// START 2016/11/02 N.Arai [QC#15654, MOD]
//  private boolean isMultipleOperations(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList) {
// Mod Start 2018/10/22 QC#28565
//  private boolean isMultipleOperations(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, List<Map<String, Object>> csaTaskNoteList) {
  private boolean isMultipleOperations(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, List<Map<String, Object>> csaTaskNoteList, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg) {
// Mod End 2018/10/22 QC#28565
// START 2016/11/15 N.Arai [QC#15654, MOD]
//      if (!SVC_TASK_STS.TBO.equals(getString(svcTaskInfoMap, FSR_VISIT_STS_CD))) {
//          return false;
//      }
      // Mod Start 2018/10/22 QC#28565
      // if (SVC_TASK_STS.TBO.equals(getString(svcTaskInfoMap, FSR_VISIT_STS_CD))) {
      String fsrVisitStsCd = getString(svcTaskInfoMap, FSR_VISIT_STS_CD);
      // START 2019/12/20 K.Fujimoto [QC#55106, DEL]
      // if (isMultOp(clickTaskSendRsltTMsg, fsrVisitStsCd)) {
      // Mod End 2018/10/22 QC#28565
//      if (svcModPlnList != null && svcModPlnList.size() > 0) {
//          return true;
//      }
//      if (mtrInfoList != null && mtrInfoList.size() > 0) {
//          return true;
//      }
//      if (ZYPConstant.FLG_ON_Y.equals(getString(svcTaskInfoMap, SVC_ISTL_REQ_FLG)) && installCheckList != null && installCheckList.size() > 0) {
//          return true;
//      }
      // START 2018/01/31 K.Kojima [QC#21112,ADD]
//      if (ZYPConstant.FLG_ON_Y.equals(getString(svcTaskInfoMap, SVC_DEINS_REQ_FLG)) && installCheckList != null && installCheckList.size() > 0) {
//          return true;
//      }
      // END 2018/01/31 K.Kojima [QC#21112,ADD]
      // }
      // END   2019/12/20 K.Fujimoto [QC#55106, DEL]

      // START 2019/12/20 K.Fujimoto [QC#55106, MOD]
      // START 2022/03/03 K.Kitachi [QC#59700, MOD]
//      if (isSendList(svcTaskInfoMap, mtrInfoList, svcModPlnList, installCheckList)) {
      if (isSendList(svcTaskInfoMap, mtrInfoList, svcModPlnList, csaTaskNoteList, installCheckList)) {
      // END 2022/03/03 K.Kitachi [QC#59700, MOD]
          return true;
      }
      if (SVC_TASK_STS.TBO.equals(fsrVisitStsCd)
               || SVC_TASK_STS.SCHEDULED.equals(fsrVisitStsCd)
               || SVC_TASK_STS.ASSIGNED.equals(fsrVisitStsCd)
               || SVC_TASK_STS.IN_ROUTE.equals(fsrVisitStsCd)
               || SVC_TASK_STS.ARRIVED.equals(fsrVisitStsCd)) {
      // END   2019/12/20 K.Fujimoto [QC#55106, MOD]
          if (csaTaskNoteList != null && csaTaskNoteList.size() > 0) {
              return true;
          }
      }
// END 2016/11/02 N.Arai [QC#15654, MOD]
// END 2016/11/15 N.Arai [QC#15654, MOD]
      return false;
  }

  /**
     * Get String Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return String
     */
    private String getString(Map<String, Object> map, String key) {
        String ret = (String) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }
    /**
     * Get BigDecimal Value from Map. (With Conversion from Null to BigDecimal.ZERO)
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return BigDecimal
     */
    private BigDecimal getBigDecimal(Map<String, Object> map, String key) {
        BigDecimal ret = (BigDecimal) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return BigDecimal.ZERO;
    }
    /**
     * checkRtnCodeForUpdate
     * @param ezdTMsg EZDTMsg
     * @return boolean
     */
    private boolean checkRtnCodeForUpdate(NSZC107001PMsg pMsg, EZDTMsg ezdTMsg) {
        // check error code
        if (ezdTMsg == null) {
            String errCd = null;
            if (ezdTMsg instanceof SVC_TASKTMsg) {
                errCd = NSZM0167E;
            } else if (ezdTMsg instanceof FSR_EVENTTMsg) {
                errCd = NSZM0170E;
            } else {
                errCd = "";
            }
            setErrMsg(errCd, pMsg.svcTaskNum.getValue());
            return false;
        }
        return true;
    }
    private boolean chkParamObject(ObjectReference obj) {

        if (obj == null) {
            return false;
        }
        for (int i = 0; i < obj.getContent().size(); i++) {

            Class clsObj = obj.getContent().get(i).getClass();

            if (String.class == clsObj
                    || Integer.class == clsObj
                    || Boolean.class == clsObj
            ) {

                if (!ZYPCommonFunc.hasValue((String) obj.getContent().get(i))) {
                    return false;
                }
            } else {
                JAXBElement jbe = (JAXBElement) obj.getContent().get(i);

                if (jbe.isNil()) {
                    return false;
                }
                Class clsJBE = jbe.getDeclaredType();
                if (String.class == clsJBE
                        || Integer.class == clsJBE
                        || Boolean.class == clsJBE
                ) {
                    if (!ZYPCommonFunc.hasValue((String) jbe.getValue())) {
                        return false;
                    }
                } else if (!chkParamObject((ObjectReference) jbe.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    // Mod Start 2018/10/22 QC#28565
    // private boolean sendTaskInfoToClick(Task task, StandardOperations multipleOperations, UpdateTask updateTask, Map<String, Object> taskInfoMap, List<Map<String, Object>> csaTaskNoteList) {
    // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//    private boolean sendTaskInfoToClick(String glblCmpyCd, Task task, StandardOperations multipleOperations, UpdateTask updateTask, Map<String, Object> taskInfoMap, List<Map<String, Object>> csaTaskNoteList, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg) {
    private boolean sendTaskInfoToClick(String glblCmpyCd, Task task, StandardOperations multipleOperations, Map<String, Object> taskInfoMap, List<Map<String, Object>> csaTaskNoteList, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg) {
    // END 2023/05/16 K.Kitachi [QC#61085, MOD]
    // Mod End 2018/10/22 QC#28565
        boolean taskRtnFlg = true;
        boolean multiRtnFlg = true;
        boolean updateRtnFlg = true;
        // START 2019/08/22 K.Fujimoto [QC#51206,ADD]
        boolean taskRtryFlg = true;
        boolean multiRtryFlg = true;
        // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//        boolean updateRtryFlg = true;
        // END 2023/05/16 K.Kitachi [QC#61085, DEL]
        boolean taskMailSendFlg = true;
        boolean multiMailSendFlg = true;
        // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//        boolean updateMailSendFlg = true;
        // END 2023/05/16 K.Kitachi [QC#61085, DEL]
        // END   2019/08/22 K.Fujimoto [QC#51206,ADD]

        // Invoke 
        ClickSoftwareService clickSoftwareServiceProxy = new ClickSoftwareService();
        ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();
        com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory objFactory = new com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory();

        // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
        boolean processTaskExLogFlag = false;
        boolean updateTaskLogFlag = false;
        boolean executeMultipleOperationsLogFlag = false;
        // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
        // Add Start 2018/10/22 QC#28565
        String fsrNum = task.getExternalRefID();
        String svcTaskNum = task.getCallID();
        String fsrVisitStsCd = getString(taskInfoMap, FSR_VISIT_STS_CD);
        // Add End 2018/10/22 QC#28565

        // Add QC#58152-1 Start
        boolean nsbi1020ExecFlg = false;
        // Add QC#58152-1 End

        // START 2022/05/23 [QC#60058, ADD]
        boolean taskExecFlg = false;
        boolean updateExecFlg = false;
        // END   2022/05/23 [QC#60058, ADD]

        // START 2020/05/18 K.Kitachi [QC#54615, ADD]
        // START 2022/07/25 [QC#60282, MOD]
        // if (isSendRelnUpdTask(clickTaskSendRsltTMsg)) {
        // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//        if (this.sendTaskBatchFlg && isSendRelnUpdTask(clickTaskSendRsltTMsg)) {
        if (this.sendTaskBatchFlg && isSendMultOp(clickTaskSendRsltTMsg)) {
        // END 2023/05/16 K.Kitachi [QC#61085, MOD]
        // END 2022/07/25 [QC#60282, MOD]
            // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//            if (updateTask != null) {
            if (multipleOperations != null) {
            // END 2023/05/16 K.Kitachi [QC#61085, MOD]
                // QC#58152-1 Add Start
                ///////////////////////////////////////////
                // NSBI1020
                ///////////////////////////////////////////
                try {
                    ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
                    emo.setOneTransaction(true);
                    emo.setContinueOnError(true);
                    emo.setOperations(multipleOperations);

                    ExecuteMultipleOperationsResponse response = null;
                    S21StopWatch sw = new S21StopWatch();
                    S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations Start." + " Task#" + svcTaskNum);
                    sw.start();
                    executeMultipleOperationsLogFlag = true;
                    response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
                    sw.stop();
                    S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations End." + " Task#"+ svcTaskNum);
                    S21InfoLogOutput.println(String.format("NSZC1070 : MultipleOperations Elapsed Time [%s]", sw.getElapsedMilliSec()));
                    executeMultipleOperationsLogFlag = false;
                    if (response != null) {
                        List<OperationResult> opeResults = response.getOperations().getSucceededGetObjectOperationOrOperationOrFailedOperation();
                        for (OperationResult opeResult : opeResults) {
                            S21InfoLogOutput.println("NSZC1070 : multiple operation result:" + opeResult.getAction() + " Task#" + svcTaskNum);
                        }
                    } else {
                        S21InfoLogOutput.println("NSZC1070 : ExecuteMultipleOperationsResponse is null.");
                        multiRtnFlg = false;
                    }
                    nsbi1020ExecFlg = true;
                } catch (SOAPFaultException e) {
                        String rtryExcldCond = varConstMap.get(NSZC1070_RTRY_EXCLD_COND);
                        if (!rtryExcldCond.equals("")) {
                            // START 2022/05/23 [QC#60058, MOD]
//                            multiRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(COMMA));
                            multiRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(PIPE));
                            // END   2022/05/23 [QC#60058, MOD]
                        }
                        String sendMailExcldCnd = varConstMap.get(NSZC1070_SEND_MAIL_EXCLD_COND);
                        if (!sendMailExcldCnd.equals("")) {
                            // START 2022/05/23 [QC#60058, MOD]
//                            multiMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(COMMA));
                            multiMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(PIPE));
                            // END   2022/05/23 [QC#60058, MOD]
                        }
                        if (executeMultipleOperationsLogFlag) {
                            S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations FaultString : " + e.getFault().getFaultString());
                            S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations End.[Exception]");
                        }
                        String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
                        S21InfoLogOutput.println(errMsg);
                        e.printStackTrace();
                        multiRtnFlg = false;
                    // END   2019/08/15 K.Fujimoto [QC#51206,ADD]
                } catch (Throwable t) {
                        if (executeMultipleOperationsLogFlag) {
                            S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations End.[Exception]");
                        }
                        String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
                        S21InfoLogOutput.println(errMsg);
                        t.printStackTrace();
                        multiRtnFlg = false;
                }
                // QC#58152-1 Add End
                // START 2023/05/16 K.Kitachi [QC#61085, ADD]
                if (!multiRtnFlg) {
                    updRsltForMultOpErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, fsrVisitStsCd, multiRtryFlg, multiMailSendFlg);
                }
                // END 2023/05/16 K.Kitachi [QC#61085, ADD]

                // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//                ///////////////////////////////////////////
//                // updateTask(Reln)
//                ///////////////////////////////////////////
//                updateTaskLogFlag = false;
//                try {
//                    // reln update task
//                    S21StopWatch sw = new S21StopWatch();
//                    S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask(Reln) Start." + " Task#" + svcTaskNum);
//                    sw.start();
//                    updateTaskLogFlag = true;
//                    clickSoftwareServiceProxy.updateTask(updateTask);
//                    sw.stop();
//                    S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask(Reln) End." + " Task#" + svcTaskNum);
//                    S21InfoLogOutput.println(String.format("NSZC1070 : updateTask(Reln) Elapsed Time [%s]", sw.getElapsedMilliSec()));
//                    updateTaskLogFlag = false;
//                    // START 2022/05/23 [QC#60058, ADD]
//                    updateExecFlg = true;
//                    // END   2022/05/23 [QC#60058, ADD]
//                } catch (SOAPFaultException e) {
//                    String rtryExcldCond = varConstMap.get(NSZC1070_RTRY_EXCLD_COND);
//                    if (!rtryExcldCond.equals("")) {
//                        // START 2022/05/23 [QC#60058, MOD]
////                        updateRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(COMMA));
//                        updateRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(PIPE));
//                        // END   2022/05/23 [QC#60058, MOD]
//                    }
//                    String sendMailExcldCnd = varConstMap.get(NSZC1070_SEND_MAIL_EXCLD_COND);
//                    if (!sendMailExcldCnd.equals("")) {
//                        // START 2022/05/23 [QC#60058, MOD]
////                        updateMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(COMMA));
//                        updateMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(PIPE));
//                        // END   2022/05/23 [QC#60058, MOD]
//                    }
//                    if (updateTaskLogFlag) {
//                        S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask(Reln) FaultString : " + e.getFault().getFaultString());
//                        S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask(Reln) End.[Exception]");
//                        String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM) });
//                        S21InfoLogOutput.println(errMsg);
//                        e.printStackTrace();
//                        updateRtnFlg = false;
//                    }
//                } catch (Throwable t) {
//                    if (updateTaskLogFlag) {
//                        S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask(Reln) End.[Exception]");
//                    }
//                    String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM) });
//                    S21InfoLogOutput.println(errMsg);
//                    t.printStackTrace();
//                    updateRtnFlg = false;
//                }
//                if (!updateRtnFlg) {
//                    updRsltForRelnUpdTaskErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, updateRtryFlg, updateMailSendFlg);
//                }
                // END 2023/05/16 K.Kitachi [QC#61085, DEL]
                // START 2022/07/25 [QC#60282, ADD]
                if (multipleOperations != null && csaTaskNoteList != null && csaTaskNoteList.size() > 0) {
                    for (Map<String, Object> taskNoteMap : csaTaskNoteList) {
                        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
                        setValue(inMsg.glblCmpyCd, getString(taskNoteMap, "GLBL_CMPY_CD"));
                        setValue(inMsg.svcMemoPk, getBigDecimal(taskNoteMap, "SVC_MEMO_PK"));
                        inMsg = (SVC_MEMOTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                        if (inMsg == null) {
                            throw new S21AbendException(NSAM0045E, new String[] {"SVC_MEMO"});
                        }
                        if (!multiRtnFlg || (!taskRtnFlg && multiRtnFlg)) {
                            setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.ERROR);
                        } else {
                            if (nsbi1020ExecFlg) {
                                setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.PROCESSED);
                            }
                        }
                        EZDTBLAccessor.update(inMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                            throw new S21AbendException(NSAM0031E, new String[] {"SVC_MEMO"});
                        }
                    }
                }
                if (taskRtnFlg & multiRtnFlg & updateRtnFlg) {
                    updRsltForSuccess(clickTaskSendRsltTMsg, taskExecFlg, nsbi1020ExecFlg, updateExecFlg);
                }
                return taskRtnFlg & multiRtnFlg & updateRtnFlg;
                // END 2022/07/25 [QC#60282, ADD]
            }
        }
        // END 2020/05/18 K.Kitachi [QC#54615, ADD]

        // send task
        try {

            task.setNumber(1);
            // Del Start 2018/10/22 QC#28565
            // String fsrVisitStsCd = getString(taskInfoMap, FSR_VISIT_STS_CD);
            // Del End 2018/10/21 QC#28565
            // Mod Start 2018/10/22 QC#28565
            // if (SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
            if (isProcTaskEx(clickTaskSendRsltTMsg, fsrVisitStsCd)) {
            // Mod End 2018/10/22 QC#28565
                ///////////////////////////////////////////
                // Process Task Ex
                ///////////////////////////////////////////
                ProcessTaskEx request = new ProcessTaskEx();
                request.setTask(task);
                request.setReturnAssignment(true);
                request.setReturnSchedulingError(false);
                // START 2017/08/25 K.Kojima [QC#19911,ADD]
                // START 2020/04/03 K.Kim [QC#56141,MOD]
                S21StopWatch sw = new S21StopWatch();
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.processTaskEx Start." + " Task#" + svcTaskNum);
                sw.start();
                // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
                processTaskExLogFlag = true;
                // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
                // END 2017/08/25 K.Kojima [QC#19911,ADD]
                clickSoftwareServiceProxy.processTaskEx(request);
                // START 2017/08/25 K.Kojima [QC#19911,ADD]
                sw.stop();
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.processTaskEx End." + " Task#" + svcTaskNum);
                S21InfoLogOutput.println(String.format("NSZC1070 : processTaskEx Elapsed Time [%s]", sw.getElapsedMilliSec()));
                // END 2020/04/03 K.Kim [QC#56141,MOD]
                // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
                processTaskExLogFlag = false;
                // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
                // END 2017/08/25 K.Kojima [QC#19911,ADD]
                // START 2022/05/23 [QC#60058, ADD]
                taskExecFlg = true;
                // END   2022/05/23 [QC#60058, ADD]

            } else {
                ///////////////////////////////////////////
                // UpdateTask
                ///////////////////////////////////////////
                UpdateTask request = new UpdateTask();
                request.setTask(task);
                // START 2017/08/25 K.Kojima [QC#19911,ADD]
                // START 2020/04/03 K.Kim [QC#56141,MOD]
                S21StopWatch sw = new S21StopWatch();
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask Start." + " Task#" + svcTaskNum);
                sw.start();
                // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
                updateTaskLogFlag = true;
                // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
                // END 2017/08/25 K.Kojima [QC#19911,ADD]
                clickSoftwareServiceProxy.updateTask(request);
                // START 2017/08/25 K.Kojima [QC#19911,ADD]
                sw.stop();
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask End." + " Task#" + svcTaskNum);
                S21InfoLogOutput.println(String.format("NSZC1070 : updateTask Elapsed Time [%s]", sw.getElapsedMilliSec()));
                // END 2020/04/03 K.Kim [QC#56141,MOD]
                // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
                updateTaskLogFlag = false;
                // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
                // END 2017/08/25 K.Kojima [QC#19911,ADD]
                // START 2022/05/23 [QC#60058, ADD]
                updateExecFlg = true;
                // END   2022/05/23 [QC#60058, ADD]
            }
            // START 2019/08/15 K.Fujimoto [QC#51206,ADD]
        } catch (SOAPFaultException e) {
            String rtryExcldCond = varConstMap.get(NSZC1070_RTRY_EXCLD_COND);
            if (!rtryExcldCond.equals("")) {
                // START 2022/05/23 [QC#60058, MOD]
//                taskRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(COMMA));
                taskRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(PIPE));
                // END   2022/05/23 [QC#60058, MOD]
            }
            String sendMailExcldCnd = varConstMap.get(NSZC1070_SEND_MAIL_EXCLD_COND);
            if (!sendMailExcldCnd.equals("")) {
                // START 2022/05/23 [QC#60058, MOD]
//                taskMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(COMMA));
                taskMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(PIPE));
                // END   2022/05/23 [QC#60058, MOD]
            }
            if (processTaskExLogFlag) {
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTaskEx FaultString : " + e.getFault().getFaultString());
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTaskEx End.[Exception]");
            }
            if (updateTaskLogFlag) {
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask FaultString : " + e.getFault().getFaultString());
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask End.[Exception]");
            }
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1016E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
            S21InfoLogOutput.println(errMsg);
            e.printStackTrace();
            taskRtnFlg = false;
        // END   2019/08/15 K.Fujimoto [QC#51206,ADD]
        } catch (Throwable t) {
            // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
            if (processTaskExLogFlag) {
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.processTaskEx End.[Exception]");
            }
            if (updateTaskLogFlag) {
                S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask End.[Exception]");
            }
            // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
            String errMsg = S21MessageFunc.clspGetMessage(NSZM1016E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
            S21InfoLogOutput.println(errMsg);
            t.printStackTrace();
            taskRtnFlg = false;
        }
        // Add Start 2018/10/22 QC#28565
        if (!taskRtnFlg) {
            // START 2019/08/22 K.Fujimoto [QC#51206,MOD]
            updRsltForProcTaskExErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, fsrVisitStsCd, multipleOperations, taskRtryFlg, taskMailSendFlg);
            //updRsltForProcTaskExErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, fsrVisitStsCd, multipleOperations);
            // END   2019/08/22 K.Fujimoto [QC#51206,MOD]
        }
        // Add End 2018/10/22 QC#28565

        // multiple operations
        // Mod Start 2018/10/22 QC#28565
        // Mod Start 2020/12/19 QC#58152-1
        //if (taskRtnFlg && multipleOperations != null) {
        // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//        if (!isProcTaskEx(clickTaskSendRsltTMsg, fsrVisitStsCd) && multipleOperations != null && !nsbi1020ExecFlg) {
        if ((this.forceSendMultOpFlg || !isProcTaskEx(clickTaskSendRsltTMsg, fsrVisitStsCd)) && multipleOperations != null && !nsbi1020ExecFlg) {
        // END 2023/05/16 K.Kitachi [QC#61085, MOD]
        // Mod End 2020/12/19 QC#58152-1
        // Mod End 2018/10/21 QC#28565
            try {
                ///////////////////////////////////////////
                // NSBI1020
                ///////////////////////////////////////////

                // Execute WMB
                // START 2017/08/25 K.Kojima [QC#19911,ADD]
                // START 2017/10/20 K.Kojima [QC#19911-1,DEL]
                // S21InfoLogOutput.println("NSZC1070 : objFactory.createExecuteMultipleOperations Start.");
                // END 2017/10/20 K.Kojima [QC#19911-1,DEL]
                // END 2017/08/25 K.Kojima [QC#19911,ADD]
                ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
                // START 2017/08/25 K.Kojima [QC#19911,ADD]
                // START 2017/10/20 K.Kojima [QC#19911-1,DEL]
                // S21InfoLogOutput.println("NSZC1070 : objFactory.createExecuteMultipleOperations End.");
                // END 2017/10/20 K.Kojima [QC#19911-1,DEL]
                // END 2017/08/25 K.Kojima [QC#19911,ADD]
                emo.setOneTransaction(true);
                emo.setContinueOnError(true);
                emo.setOperations(multipleOperations);

                ExecuteMultipleOperationsResponse response = null;
                // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//                response = proxy.executeMultipleOperations(emo);
                // START 2017/08/25 K.Kojima [QC#19911-1,ADD]
                // START 2020/04/03 K.Kim [QC#56141,MOD]
                S21StopWatch sw = new S21StopWatch();
                S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations Start." + " Task#" + svcTaskNum);
                sw.start();
                executeMultipleOperationsLogFlag = true;
                // END 2017/08/25 K.Kojima [QC#19911-1,ADD]
                response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
                // START 2017/08/25 K.Kojima [QC#19911-1,ADD]
                sw.stop();
                S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations End." + " Task#"+ svcTaskNum);
                S21InfoLogOutput.println(String.format("NSZC1070 : MultipleOperations Elapsed Time [%s]", sw.getElapsedMilliSec()));
                // END 2020/04/03 K.Kim [QC#56141,MOD]
                executeMultipleOperationsLogFlag = false;
                // END 2017/08/25 K.Kojima [QC#19911-1,ADD]
                // END 2017/01/04 K.Kitachi [QC#16316, MOD]

                //This is a temporary solution for QC#16514
                //if (response == null) {
                //    return false;
                //}
                if (response != null) {
                    List<OperationResult> opeResults = response.getOperations().getSucceededGetObjectOperationOrOperationOrFailedOperation();
                    for (OperationResult opeResult : opeResults) {
                        // START 2020/04/03 K.Kim [QC#56141,MOD]
                        S21InfoLogOutput.println("NSZC1070 : multiple operation result:" + opeResult.getAction() + " Task#" + svcTaskNum);
                        // END 2020/04/03 K.Kim [QC#56141,MOD]
                    }
                } else {
                    S21InfoLogOutput.println("NSZC1070 : ExecuteMultipleOperationsResponse is null.");
                    multiRtnFlg = false;
                }
                //This is a temporary solution for QC#16514

                // QC#58152-1 Add Start
                nsbi1020ExecFlg = true;
                // QC#58152-1 Add End

                // START 2019/08/15 K.Fujimoto [QC#51206,ADD]
                } catch (SOAPFaultException e) {
                    String rtryExcldCond = varConstMap.get(NSZC1070_RTRY_EXCLD_COND);
                    if (!rtryExcldCond.equals("")) {
                        // START 2022/05/23 [QC#60058, MOD]
//                        multiRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(COMMA));
                        multiRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(PIPE));
                        // END   2022/05/23 [QC#60058, MOD]
                    }
                    String sendMailExcldCnd = varConstMap.get(NSZC1070_SEND_MAIL_EXCLD_COND);
                    if (!sendMailExcldCnd.equals("")) {
                        // START 2022/05/23 [QC#60058, MOD]
//                        multiMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(COMMA));
                        multiMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(PIPE));
                        // END   2022/05/23 [QC#60058, MOD]
                    }
                    if (executeMultipleOperationsLogFlag) {
                        S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations FaultString : " + e.getFault().getFaultString());
                        S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations End.[Exception]");
                    }
                    String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
                    S21InfoLogOutput.println(errMsg);
                    e.printStackTrace();
                    multiRtnFlg = false;
                // END   2019/08/15 K.Fujimoto [QC#51206,ADD]
                } catch (Throwable t) {
                    // START 2017/08/25 K.Kojima [QC#19911-1,ADD]
                    if (executeMultipleOperationsLogFlag) {
                        S21InfoLogOutput.println("NSZC1070 : proxy.executeMultipleOperations End.[Exception]");
                    }
                    // END 2017/08/25 K.Kojima [QC#19911-1,ADD]
                    String errMsg = S21MessageFunc.clspGetMessage(NSZM1020E, new String[] {getString(taskInfoMap, SVC_TASK_NUM)});
                    S21InfoLogOutput.println(errMsg);
                    t.printStackTrace();
                    multiRtnFlg = false;
                }
                // mod end 2016/12/20 CSA Defect#16514
        // START 2023/05/16 K.Kitachi [QC#61085, ADD]
        // START 2023/06/21 K.Kitachi [QC#61085, MOD]
//        } else if (!this.forceSendMultOpFlg && isProcTaskEx(clickTaskSendRsltTMsg, fsrVisitStsCd) && multipleOperations != null && !nsbi1020ExecFlg) {
        } else if (!this.forceSendMultOpFlg && isProcTaskEx(clickTaskSendRsltTMsg, fsrVisitStsCd) && multipleOperations != null && !nsbi1020ExecFlg && taskRtnFlg) {
        // END 2023/06/21 K.Kitachi [QC#61085, MOD]
            multiRtnFlg = false;
            multiMailSendFlg = false;
        // END 2023/05/16 K.Kitachi [QC#61085, ADD]
        }
        // Add Start 2018/10/22 QC#28565
        if (!multiRtnFlg) {
            // START 2019/08/22 K.Fujimoto [QC#51206,MOD]
            // updRsltForMultOpErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, fsrVisitStsCd);
            updRsltForMultOpErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, fsrVisitStsCd, multiRtryFlg, multiMailSendFlg);
            // END   2019/08/22 K.Fujimoto [QC#51206,MOD]
        }
        // Add End 2018/10/22 QC#28565

        // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//        // Add Start 2018/10/22 QC#28565
//        if (taskRtnFlg && multiRtnFlg && updateTask != null) {
//        // Add End 2018/10/22 QC#28565
//            // START 2020/05/18 K.Kitachi [QC#54615, DEL]
////            // START 2018/04/19 QC#24220
////            // This is a temporary solution. To avoid update task failing on clicksoft, wait for a few milliseconds.
////            BigDecimal waitMsec = ZYPCodeDataUtil.getNumConstValue("NSZC1070_WAIT_MSEC", "ADB");
////            if (!ZYPCommonFunc.hasValue(waitMsec)) {
////                waitMsec = BigDecimal.valueOf(100); //Default 100 msec
////            }
////            try {
////                TimeUnit.MILLISECONDS.sleep(waitMsec.longValue());
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            // END 2018/04/19 QC#24220
//            // END 2020/05/18 K.Kitachi [QC#54615, DEL]
//
//        // Del Start 2018/10/22 QC#28565
//        // if (updateTask != null) {
//        // Del End 2018/10/22 QC#28565
//            // START 2020/05/18 K.Kitachi [QC#54615, ADD]
//            if (isProcTaskEx(clickTaskSendRsltTMsg, fsrVisitStsCd)) {
//                updateRtnFlg = false;
//                updateMailSendFlg = false;
//            } else {
//            // END 2020/05/18 K.Kitachi [QC#54615, ADD]
//                // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                updateTaskLogFlag = false;
//                // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                try {
//                    // update task
//                    // START 2017/08/25 K.Kojima [QC#19911,ADD]
//                    // START 2020/04/03 K.Kim [QC#56141,MOD]
//                    S21StopWatch sw = new S21StopWatch();
//                    S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask Start." + " Task#" + svcTaskNum);
//                    sw.start();
//                    // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                    updateTaskLogFlag = true;
//                    // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                    // END 2017/08/25 K.Kojima [QC#19911,ADD]
//                    clickSoftwareServiceProxy.updateTask(updateTask);
//                    // START 2017/08/25 K.Kojima [QC#19911,ADD]
//                    sw.stop();
//                    S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask End." + " Task#" + svcTaskNum);
//                    S21InfoLogOutput.println(String.format("NSZC1070 : updateTask Elapsed Time [%s]", sw.getElapsedMilliSec()));
//                    // END 2020/04/03 K.Kim [QC#56141,MOD]
//                    // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                    updateTaskLogFlag = false;
//                    // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                    // END 2017/08/25 K.Kojima [QC#19911,ADD]
//                    // START 2019/08/15 K.Fujimoto [QC#51206,ADD]
//                    // START 2022/05/23 [QC#60058, ADD]
//                    updateExecFlg = true;
//                    // END   2022/05/23 [QC#60058, ADD]
//                } catch (SOAPFaultException e) {
//                    String rtryExcldCond = varConstMap.get(NSZC1070_RTRY_EXCLD_COND);
//                    if (!rtryExcldCond.equals("")) {
//                        // START 2022/05/23 [QC#60058, MOD]
////                        updateRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(COMMA));
//                        updateRtryFlg = !matchPetterns(e.getFault().getFaultString(), rtryExcldCond.split(PIPE));
//                        // END   2022/05/23 [QC#60058, MOD]
//                    }
//                    String sendMailExcldCnd = varConstMap.get(NSZC1070_SEND_MAIL_EXCLD_COND);
//                    if (!sendMailExcldCnd.equals("")) {
//                        // START 2022/05/23 [QC#60058, MOD]
////                        updateMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(COMMA));
//                        updateMailSendFlg = !matchPetterns(e.getFault().getFaultString(), sendMailExcldCnd.split(PIPE));
//                        // END   2022/05/23 [QC#60058, MOD]
//                    }
//                    if (updateTaskLogFlag) {
//                        S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask FaultString : " + e.getFault().getFaultString());
//                        S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask End.[Exception]");
//                        String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM) });
//                        S21InfoLogOutput.println(errMsg);
//                        e.printStackTrace();
//                        updateRtnFlg = false;
//                    }
//                    // END 2019/08/15 K.Fujimoto [QC#51206,ADD]
//                } catch (Throwable t) {
//                    // START 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                    if (updateTaskLogFlag) {
//                        S21InfoLogOutput.println("NSZC1070 : clickSoftwareServiceProxy.updateTask End.[Exception]");
//                    }
//                    // END 2017/10/20 K.Kojima [QC#19911-1,ADD]
//                    String errMsg = S21MessageFunc.clspGetMessage(NSZM1021E, new String[] {getString(taskInfoMap, SVC_TASK_NUM) });
//                    S21InfoLogOutput.println(errMsg);
//                    t.printStackTrace();
//                    updateRtnFlg = false;
//                }
//            }
//            // Add Start 2018/10/22 QC#28565
//            if (!updateRtnFlg) {
//                // START 2019/08/22 K.Fujimoto [QC#51206,MOD]
//                // updRsltForRelnUpdTaskErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum);
//                updRsltForRelnUpdTaskErr(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum, updateRtryFlg, updateMailSendFlg);
//                // END 2019/08/22 K.Fujimoto [QC#51206,MOD]
//            }
//            // Add End 2018/10/22 QC#28565
//        // START 2020/05/18 K.Kitachi [QC#54615, ADD]
//        }
//        // END 2020/05/18 K.Kitachi [QC#54615, ADD]
        // END 2023/05/16 K.Kitachi [QC#61085, DEL]

        // START 2016/11/07 N.Arai [QC#15784, MOD]
        // Mod Start 2018/10/24 QC#28565
        // if (csaTaskNoteList != null && csaTaskNoteList.size() > 0) {
        if (multipleOperations != null && csaTaskNoteList != null && csaTaskNoteList.size() > 0) {
        // Mod End 2018/10/24 QC#28565
            for (Map<String, Object> taskNoteMap : csaTaskNoteList) {
                SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
                setValue(inMsg.glblCmpyCd, getString(taskNoteMap, "GLBL_CMPY_CD"));
                setValue(inMsg.svcMemoPk, getBigDecimal(taskNoteMap, "SVC_MEMO_PK"));
                inMsg = (SVC_MEMOTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
                if (inMsg == null) {
                    throw new S21AbendException(NSAM0045E, new String[] {"SVC_MEMO"});
                }

                // Mod Start 2018/10/24 QC#28565
                // if (!multiRtnFlg) {
                if (!multiRtnFlg || (!taskRtnFlg && multiRtnFlg)) {
                // Mod End 2018/10/24 QC#28565
                    setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.ERROR);
                } else {
                    // QC#58152-1 Add Start
                    if (nsbi1020ExecFlg) {
                        setValue(inMsg.mblIntfcProcCd, MBL_INTFC_PROC.PROCESSED);
                    }
                    // QC#58152-1 Add End
                }
                EZDTBLAccessor.update(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0031E, new String[] {"SVC_MEMO"});
                }
            }
        }
        // END 2016/11/07 N.Arai [QC#15784, MOD]

        // Add Start 2018/10/22 QC#28565
        if (taskRtnFlg & multiRtnFlg & updateRtnFlg) {
            // START 2022/05/23 [QC#60058, MOD]
//            updRsltForScusess(clickTaskSendRsltTMsg);
            // START 2022/11/16 K.Kitachi [QC#60821, MOD]
//            updRsltForSuccess(clickTaskSendRsltTMsg, taskExecFlg, nsbi1020ExecFlg, updateExecFlg);
            if (clickTaskSendRsltTMsg != null) {
                updRsltForSuccess(clickTaskSendRsltTMsg, taskExecFlg, nsbi1020ExecFlg, updateExecFlg);
            } else {
                // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//                insRsltForSuccess(clickTaskSendRsltTMsg, glblCmpyCd, fsrNum, svcTaskNum);
                insRsltForSuccess(glblCmpyCd, fsrNum, svcTaskNum, taskExecFlg, nsbi1020ExecFlg, updateExecFlg);
                // END 2023/05/16 K.Kitachi [QC#61085, MOD]
            }
            // END 2022/11/16 K.Kitachi [QC#60821, MOD]
            // END   2022/05/23 [QC#60058, MOD]
        }
        // Add End 2018/10/22 QC#28565
        return taskRtnFlg & multiRtnFlg & updateRtnFlg;
    }

    // START 2017/07/25 K.Kojima [QC#19963,ADD]
    private static void replaceStatus(String glblCmpyCd, Map<String, Object> taskInfo) {
        String fsrVisitStsCd = (String) taskInfo.get(FSR_VISIT_STS_CD);
        if (fsrVisitStsCd == null) {
            return;
        }
        if (fsrVisitStsCd.equals(SVC_TASK_STS.CLOSED)) {
            SVC_TASK_STSTMsg tMsg = new SVC_TASK_STSTMsg();
            setValue(tMsg.glblCmpyCd, glblCmpyCd);
            setValue(tMsg.svcTaskStsCd, SVC_TASK_STS.COMPLETED);
            tMsg = (SVC_TASK_STSTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            taskInfo.put(FSR_VISIT_STS_CD, tMsg.svcTaskStsCd.getValue());
            taskInfo.put(SVC_TASK_STS_NM, tMsg.xtrnlStsRefTxt.getValue());
        }
    }
    // END 2017/07/25 K.Kojima [QC#19963,ADD]

    // START 2017/09/04 M.Kidokoro [QC#20862,ADD]
    private static void replaceLocation(Task task, ObjectFactory of, String glblCmpyCd, Map<String, Object> taskInfo) {
        String shipToUpdCustCd = (String) taskInfo.get(SHIP_TO_UPD_CUST_CD);
        if (!ZYPCommonFunc.hasValue(shipToUpdCustCd)) {
            return;
        }
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToUpdCustCd);
        tMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() == 0) {
            return;
        }
        tMsg = tMsgArray.no(0);

        // START 2017/09/08 M.Kidokoro [QC#20862,ADD]
        if (ZYPCommonFunc.hasValue(tMsg.sellToCustCd)) {
            SELL_TO_CUSTTMsg sellTMsg = new SELL_TO_CUSTTMsg();
            sellTMsg.setSQLID("001");
            sellTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            sellTMsg.setConditionValue("sellToCustCd01", tMsg.sellToCustCd.getValue());
            SELL_TO_CUSTTMsgArray sellTMsgArray = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(sellTMsg);
            if (sellTMsgArray.getValidCount() == 0) {
                return;
            }
            sellTMsg = sellTMsgArray.no(0);
            task.setCSACustomerName(sellTMsg.dsAcctNm.getValue().replace("&", ""));
        }
        // END 2017/09/08 M.Kidokoro [QC#20862,ADD]

        // Address
        if (ZYPCommonFunc.hasValue(tMsg.firstLineAddr)) {
            task.setStreet(tMsg.firstLineAddr.getValue().replace("&", ""));
        }
        if (ZYPCommonFunc.hasValue(tMsg.scdLineAddr)) {
            task.setCSAStreet1(tMsg.scdLineAddr.getValue().replace("&", ""));
        }
        task.setCity(tMsg.ctyAddr.getValue());
        task.setState(tMsg.stCd.getValue());
        task.setPostcode(tMsg.postCd.getValue());

        // Country
        String ctryCd = tMsg.ctryCd.getValue();
        if (ZYPCommonFunc.hasValue(ctryCd)) {

            CountryReference ctryRef = new CountryReference();
            ctryRef.getContent().add(of.createCountryReferenceName(ctryCd));
            task.setCountryID(ctryRef);
        }
    }
    // END 2017/09/04 M.Kidokoro [QC#20862,ADD]

    // START 2017/08/25 K.Kojima [QC#20527,ADD]
    private String getIstlChkVerFlg(String glblCmpyCd, String svcTaskNum, String mdseCd, String serNum) {
        String istlChkVerFlg = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("mdseCd", mdseCd);
        if (ZYPCommonFunc.hasValue(serNum)) {
            paramMap.put("serNum", serNum);
        }
        istlChkVerFlg = (String) ssmClient.queryObject("getIstlChkVerFlg", paramMap);

        return istlChkVerFlg;
    }
    // END 2017/08/25 K.Kojima [QC#20527,ADD]
    // Add Start 2017/10/03 QC#21515
    private boolean isSvcExchOrd(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        // Add Start 2018/05/30 QC#26408
        paramMap.put("svcMachMstrStsCd", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        List<String> ordHdrStsCdList = new ArrayList<String>();
        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
        paramMap.put("ordHdrStsCdList", ordHdrStsCdList);
        // Add End 2018/05/30 QC#26408
        paramMap.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET);
        BigDecimal count = (BigDecimal) ssmClient.queryObject("countSvcExchOrd", paramMap);
        if (BigDecimal.ZERO.compareTo(count) >= 0) {
            return false;
        }
        return true;
    }

    private List<Map<String, Object>> getInstallCheckListForSvcExch(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        List<String> rtnLineStsCdList = new ArrayList<String>();
        rtnLineStsCdList.add(RTRN_LINE_STS.ENTERED);
        rtnLineStsCdList.add(RTRN_LINE_STS.CANCELLED);
        paramMap.put("rtnLineStsCdList", rtnLineStsCdList);
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getInstallCheckListForSvcExch", paramMap);
    }
    // Add End 2017/10/03 QC#21515
    // Add Start 2018/10/22 QC#28565
    private CLICK_TASK_SEND_RSLTTMsg getClickTaskSendRsltTMsg(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        CLICK_TASK_SEND_RSLTTMsg inMsg = new CLICK_TASK_SEND_RSLTTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.fsrNum, fsrNum);
        setValue(inMsg.svcTaskNum, svcTaskNum);
        return (CLICK_TASK_SEND_RSLTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    // Mod Start 2019/08/22 QC#51206 K.Fujimoto
    // private void updRsltForProcTaskExErr(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum, String fsrVisitStsCd, StandardOperations multipleOperations) {
    private void updRsltForProcTaskExErr(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum, String fsrVisitStsCd, StandardOperations multipleOperations, boolean rtryFlg, boolean mailSendFlg) {
    // Mod End   2019/08/22 QC#51206 K.Fujimoto
        boolean createFlg = false;
        if (isProcTaskEx(inMsg, fsrVisitStsCd)) {
            if (inMsg == null) {
                inMsg = new CLICK_TASK_SEND_RSLTTMsg();
                setValue(inMsg.glblCmpyCd, glblCmpyCd);
                setValue(inMsg.fsrNum, fsrNum);
                setValue(inMsg.svcTaskNum, svcTaskNum);
                createFlg = true;
            }
            setValue(inMsg.procTaskExProcStsCd, PROC_STS.ERROR);
            // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//            setValue(inMsg.relnUpdTaskProcStsCd, PROC_STS.ERROR);
            inMsg.relnUpdTaskProcStsCd.clear();
            // END 2023/05/16 K.Kitachi [QC#61085, MOD]
            inMsg.updTaskProcStsCd.clear();
            // Mod Start 2019/08/22 QC#51206 K.Fujimoto
            if (mailSendFlg) {
                setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1350E);
            } else {
                setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1362E);
            }
            // Mod End   2019/08/22 QC#51206 K.Fujimoto
        } else {
            if (inMsg == null) {
                inMsg = new CLICK_TASK_SEND_RSLTTMsg();
                setValue(inMsg.glblCmpyCd, glblCmpyCd);
                setValue(inMsg.fsrNum, fsrNum);
                setValue(inMsg.svcTaskNum, svcTaskNum);
                createFlg = true;
            }
            inMsg.procTaskExProcStsCd.clear();
            inMsg.relnUpdTaskProcStsCd.clear();
            setValue(inMsg.updTaskProcStsCd, PROC_STS.ERROR);
            // Mod Start 2019/08/22 QC#51206 K.Fujimoto
            if (mailSendFlg) {
                setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1353E);
            } else {
                setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1365E);
            }
        }
        if (multipleOperations != null) {
            setValue(inMsg.multOpProcStsCd, PROC_STS.ERROR);
        } else {
            inMsg.multOpProcStsCd.clear();
        }

        // Mod Start 2019/08/22 QC#51206 K.Fujimoto
//        if (createFlg) {
//            setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
//            S21ApiTBLAccessor.create(inMsg);
//        } else {
//            if (PROC_STS.COMPLEATED.equals(inMsg.rtrySendProcStsCd.getValue())) {
//                setValue(inMsg.rtrySendProcStsCd, PROC_STS.IN_COMPLETED);
//            } else {
//                setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
//            }
//            S21ApiTBLAccessor.update(inMsg);
//        }
        updRslt(inMsg, createFlg, rtryFlg);
        // Mod End 2019/08/22 QC#51206 K.Fujimoto
    }

    // Mod Start 2019/08/22 QC#51206 K.Fujimoto
    // private void updRsltForMultOpErr(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum, String fsrVisitStsCd) {
    private void updRsltForMultOpErr(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum, String fsrVisitStsCd, Boolean rtryFlg, boolean mailSendFlg) {
    // Mod End   2019/08/22 QC#51206 K.Fujimoto
        boolean createFlg = false;

        if (isProcTaskEx(inMsg, fsrVisitStsCd)) {
            if (inMsg == null) {
                inMsg = new CLICK_TASK_SEND_RSLTTMsg();
                setValue(inMsg.glblCmpyCd, glblCmpyCd);
                setValue(inMsg.fsrNum, fsrNum);
                setValue(inMsg.svcTaskNum, svcTaskNum);
                createFlg = true;
            }
            setValue(inMsg.procTaskExProcStsCd, PROC_STS.COMPLEATED);
            setValue(inMsg.multOpProcStsCd, PROC_STS.ERROR);
            // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//            setValue(inMsg.relnUpdTaskProcStsCd, PROC_STS.ERROR);
            inMsg.relnUpdTaskProcStsCd.clear();
            // END 2023/05/16 K.Kitachi [QC#61085, MOD]
            inMsg.updTaskProcStsCd.clear();
        } else {
            if (inMsg == null) {
                inMsg = new CLICK_TASK_SEND_RSLTTMsg();
                setValue(inMsg.glblCmpyCd, glblCmpyCd);
                setValue(inMsg.fsrNum, fsrNum);
                setValue(inMsg.svcTaskNum, svcTaskNum);
                createFlg = true;
            }
            inMsg.procTaskExProcStsCd.clear();
            setValue(inMsg.multOpProcStsCd, PROC_STS.ERROR);
            inMsg.relnUpdTaskProcStsCd.clear();
            setValue(inMsg.updTaskProcStsCd, PROC_STS.COMPLEATED);
        }
        // Mod Start 2019/08/22 QC#51206 K.Fujimoto
        if (mailSendFlg) {
            setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1351E);
        } else {
            setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1363E);
        }

        // Mod Start 2019/08/22 QC#51206 K.Fujimoto
//        if (createFlg) {
//            setValue(inMsg.rtrySendProcStsCd, PROC_STS.IN_COMPLETED);
//            S21ApiTBLAccessor.create(inMsg);
//        } else {
//            if (PROC_STS.COMPLEATED.equals(inMsg.rtrySendProcStsCd.getValue())) {
//                setValue(inMsg.rtrySendProcStsCd, PROC_STS.IN_COMPLETED);
//            } else {
//                setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
//            }
//            S21ApiTBLAccessor.update(inMsg);
//        }
        updRslt(inMsg, createFlg, rtryFlg);
        // Mod End   2019/08/22 QC#51206 K.Fujimoto
    }

    // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//    // Mod Start 2019/08/22 QC#51206 K.Fujimoto
//    // private void updRsltForRelnUpdTaskErr(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum) {
//    private void updRsltForRelnUpdTaskErr(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum, boolean rtryFlg, boolean mailSendFlg) {
//    // Mod End   2019/08/22 QC#51206 K.Fujimoto
//        boolean createFlg = false;
//        if (inMsg == null) {
//            inMsg = new CLICK_TASK_SEND_RSLTTMsg();
//            setValue(inMsg.glblCmpyCd, glblCmpyCd);
//            setValue(inMsg.fsrNum, fsrNum);
//            setValue(inMsg.svcTaskNum, svcTaskNum);
//            createFlg = true;
//        }
//        setValue(inMsg.procTaskExProcStsCd, PROC_STS.COMPLEATED);
//        setValue(inMsg.multOpProcStsCd, PROC_STS.COMPLEATED);
//        setValue(inMsg.relnUpdTaskProcStsCd, PROC_STS.ERROR);
//        inMsg.updTaskProcStsCd.clear();
//        // Mod Start 2019/08/22 QC#51206 K.Fujimoto
//        if (mailSendFlg) {
//            setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1352E);
//        } else {
//            setValue(inMsg.appMsgId, NSZC107001Constant.NSZM1364E);
//        }
//        // Mod Start 2019/08/22 QC#51206 K.Fujimoto
////        if (createFlg) {
////            setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
////            S21ApiTBLAccessor.create(inMsg);
////        } else {
////            if (PROC_STS.COMPLEATED.equals(inMsg.rtrySendProcStsCd.getValue())) {
////                setValue(inMsg.rtrySendProcStsCd, PROC_STS.IN_COMPLETED);
////            } else {
////                setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
////            }
////            S21ApiTBLAccessor.update(inMsg);
////        }
//        updRslt(inMsg, createFlg, rtryFlg);
//        // Mod End 2019/08/22 QC#51206 K.Fujimoto
//    }
    // END 2023/05/16 K.Kitachi [QC#61085, DEL]
    // Add Start 2019/08/22 QC#51206 K.Fujimoto
    private void updRslt(CLICK_TASK_SEND_RSLTTMsg inMsg, boolean createFlg, boolean rtryFlg) {
        if (createFlg) {
            if (rtryFlg) {
                setValue(inMsg.rtrySendProcStsCd, PROC_STS.IN_COMPLETED);
            } else {
                setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
            }
            S21ApiTBLAccessor.create(inMsg);
        } else {
            // START 2022/05/23 [QC#60058, MOD]
//            if (PROC_STS.COMPLEATED.equals(inMsg.rtrySendProcStsCd.getValue()) && rtryFlg) {
            if (rtryFlg) {
            // END   2022/05/23 [QC#60058, MOD]
                setValue(inMsg.rtrySendProcStsCd, PROC_STS.IN_COMPLETED);
            } else {
                setValue(inMsg.rtrySendProcStsCd, PROC_STS.ERROR);
            }
            S21ApiTBLAccessor.update(inMsg);
        }
    }
    // Add End   2019/08/22 QC#51206 K.Fujimoto    

    // START 2022/05/23 [QC#60058, MOD]
//    private void updRsltForScusess(CLICK_TASK_SEND_RSLTTMsg inMsg) {
    private void updRsltForSuccess(CLICK_TASK_SEND_RSLTTMsg inMsg, boolean taskExecFlg, boolean nsbi1020ExecFlg, boolean updateExecFlg) {
    // END   2022/05/23 [QC#60058, MOD]
        if (inMsg == null) {
            return;
        }
        // START 2022/05/23 [QC#60058, MOD]
//        setValue(inMsg.procTaskExProcStsCd, changeCompSts(inMsg.procTaskExProcStsCd.getValue()));
//        setValue(inMsg.multOpProcStsCd, changeCompSts(inMsg.multOpProcStsCd.getValue()));
//        setValue(inMsg.relnUpdTaskProcStsCd, changeCompSts(inMsg.relnUpdTaskProcStsCd.getValue()));
//        setValue(inMsg.updTaskProcStsCd, changeCompSts(inMsg.updTaskProcStsCd.getValue()));
//        setValue(inMsg.rtrySendProcStsCd, PROC_STS.COMPLEATED);
        if (taskExecFlg) {
            setValue(inMsg.procTaskExProcStsCd, changeCompSts(inMsg.procTaskExProcStsCd.getValue()));
        }
        if (nsbi1020ExecFlg) {
            setValue(inMsg.multOpProcStsCd, changeCompSts(inMsg.multOpProcStsCd.getValue()));
        }
        if (updateExecFlg) {
            // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//            setValue(inMsg.relnUpdTaskProcStsCd, changeCompSts(inMsg.relnUpdTaskProcStsCd.getValue()));
            // END 2023/05/16 K.Kitachi [QC#61085, DEL]
            setValue(inMsg.updTaskProcStsCd, changeCompSts(inMsg.updTaskProcStsCd.getValue()));
        }
        // START 2023/05/16 K.Kitachi [QC#61085, MOD]
//        if (!PROC_STS.ERROR.equals(inMsg.procTaskExProcStsCd.getValue()) && !PROC_STS.ERROR.equals(inMsg.multOpProcStsCd.getValue()) && !PROC_STS.ERROR.equals(inMsg.relnUpdTaskProcStsCd.getValue())
//                && !PROC_STS.ERROR.equals(inMsg.updTaskProcStsCd.getValue())) {
        if (!PROC_STS.ERROR.equals(inMsg.procTaskExProcStsCd.getValue()) && !PROC_STS.ERROR.equals(inMsg.multOpProcStsCd.getValue()) && !PROC_STS.ERROR.equals(inMsg.updTaskProcStsCd.getValue())) {
        // END 2023/05/16 K.Kitachi [QC#61085, MOD]
            setValue(inMsg.rtrySendProcStsCd, PROC_STS.COMPLEATED);
        }
        // END   2022/05/23 [QC#60058, MOD]
        inMsg.appMsgId.clear();
        S21ApiTBLAccessor.update(inMsg);
    }

    private String changeCompSts(String procStsCd) {
        if (!ZYPCommonFunc.hasValue(procStsCd)) {
            return null;
        }
        if (PROC_STS.ERROR.equals(procStsCd)) {
            return PROC_STS.COMPLEATED;
        }
        return procStsCd;
    }

    private boolean isProcTaskEx(CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg, String fsrVisitStsCd) {
        // Mod Start 2018/11/15 QC#29281
//        if (clickTaskSendRsltTMsg == null && SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
//            return true;
//        }
//        if (PROC_STS.ERROR.equals(clickTaskSendRsltTMsg.procTaskExProcStsCd.getValue())) {
//            return true;
//        }
        if (clickTaskSendRsltTMsg == null) {
            if (SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
                return true;
            }
            return false;
        }
        if (PROC_STS.ERROR.equals(clickTaskSendRsltTMsg.procTaskExProcStsCd.getValue()) && SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
            return true;
        }
        // Mod End 2018/11/15 QC#29281
        return false;
    }

    private boolean isMultOp(CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg, String fsrVisitStsCd) {
        // Mod Start 2018/11/15 QC#29281
//        if (clickTaskSendRsltTMsg == null && SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
//            return true;
//        }
//        if (PROC_STS.ERROR.equals(clickTaskSendRsltTMsg.multOpProcStsCd.getValue())) {
//            return true;
//        }
//        if (PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.multOpProcStsCd.getValue())) {
//            return false;
//        }
//        if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd) || SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
//            return false;
//        }

        // START 2019/10/29 K.Fujimoto [QC#53441, ADD]
        if (forceSendTaskFlg) {
            return true;
        }
        // END   2019/10/29 K.Fujimoto [QC#53441, ADD]
        // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
//        if (clickTaskSendRsltTMsg == null) {
//            if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd) || SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
//                return false;
//            }
//            return true;
//        }
//        if (PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.multOpProcStsCd.getValue())) {
//            return false;
//        }
        // END   2019/12/19 K.Fujimoto [QC#55106, DEL]
        if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd) || SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
            return false;
        }
        // Mod End 2018/11/15 QC#29281
        return true;
    }

    // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//    // START 2019/12/19 K.Fujimoto [QC#55106, MOD]
//    //private boolean isRelnUpdTask(CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg, String fsrVisitStsCd) {
//    // START 2022/03/03 K.Kitachi [QC#59700, MOD]
////    private boolean isRelnUpdTask(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg, String fsrVisitStsCd) {
//    private boolean isRelnUpdTask(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> csaTaskNoteList, List<Map<String, Object>> installCheckList, CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg, String fsrVisitStsCd) {
//    // END 2022/03/03 K.Kitachi [QC#59700, MOD]
//    // END   2019/12/19 K.Fujimoto [QC#55106, MOD]
//        // Mod Start 2018/11/15 QC#29281
////        if (clickTaskSendRsltTMsg == null && SVC_TASK_STS.TBO.equals(fsrVisitStsCd)) {
////            return true;
////        }
////        if (PROC_STS.ERROR.equals(clickTaskSendRsltTMsg.relnUpdTaskProcStsCd.getValue())) {
////            return true;
////        }
////        if (PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.relnUpdTaskProcStsCd.getValue())) {
////            return false;
////        }
////        if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd) || SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
////            return false;
////        }
//        // START 2019/12/19 K.Fujimoto [QC#55106, ADD]
//        // START 2022/03/03 K.Kitachi [QC#59700, MOD]
////        if (!isSendList(svcTaskInfoMap, mtrInfoList, svcModPlnList, installCheckList)) {
//        if (!isSendList(svcTaskInfoMap, mtrInfoList, svcModPlnList, csaTaskNoteList, installCheckList)) {
//        // END 2022/03/03 K.Kitachi [QC#59700, MOD]
//            return false;
//        }
//        // END   2019/12/19 K.Fujimoto [QC#55106, ADD]
//        // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
////        if (clickTaskSendRsltTMsg == null) {
////            if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd) || SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
////                return false;
////            }
//            // END   2019/12/19 K.Fujimoto [QC#55106, DEL]
////            return true;
////        }
////        if (PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.relnUpdTaskProcStsCd.getValue())) {
////            return false;
////        }
//        // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
////        if (SVC_TASK_STS.COMPLETED.equals(fsrVisitStsCd) || SVC_TASK_STS.CLOSED.equals(fsrVisitStsCd) || SVC_TASK_STS.CANCELLED.equals(fsrVisitStsCd)) {
////            return false;
////        }
//        // Mod End 2018/11/15 QC#29281
//        // START 2019/12/19 K.Fujimoto [QC#55106, DEL]
//        return true;
//    }
    // END 2023/05/16 K.Kitachi [QC#61085, DEL]
    // Add End 2018/10/22 QC#28565

   // Add Start 2019/08/22 QC#51206 K.Fujimoto
   private boolean matchPetterns(String errMsg, String[] regexes) {
       boolean isMatch = false;
       for (String regex : regexes) {
           isMatch = errMsg.matches(regex);
           if (isMatch) {
               return isMatch;
           }
       }
       return isMatch;
   }
   // Add End   2019/08/22 QC#51206 K.Fujimoto
   // START 2019/09/20 K.Fujimoto [QC#53507,ADD]
   private SVC_TASKTMsg getSvcTaskTMsg(String glblCmpyCd, String svcTaskNum) {
       SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
       setValue(inMsg.glblCmpyCd, glblCmpyCd);
       setValue(inMsg.svcTaskNum, svcTaskNum);
       return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(inMsg);
   }

   private SHIP_TO_CUSTTMsg getShipToCustTMsg(String glblCmpyCd, String shipToCustCd) {
       SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
       tMsg.setSQLID("004");
       tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
       tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
       SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
       if (tMsgArray.getValidCount() == 0) {
           return null;
       }
       return  tMsgArray.no(0);
   }
   private String getFldSvcBr(String glblCmpyCd, String postCd, String svcLineBizCd, String svcBrCd) {
       Map<String, Object> paramMap = new HashMap<String, Object>();
       paramMap.put("glblCmpyCd", glblCmpyCd);
       paramMap.put("postCd", postCd);
       paramMap.put("svcLineBizCd", svcLineBizCd);
       paramMap.put("svcBrCd", svcBrCd);
       return (String) ssmClient.queryObject("getFldSvcBr", paramMap);
   }
   // END   2019/09/20 K.Fujimoto [QC#53507,ADD]
   // START 2019/10/24 K.Fujimoto [QC#53870,ADD]
   private void removeOutOfRngSvcModPln(String glblCmpyCd, List<Map<String, Object>> svcModPlnList) {
     // START 2022/02/24 [QC#59701, MOD]
//     for (Map<String, Object> svcModPlnMap : svcModPlnList) {
       Iterator<Map<String, Object>>  svcModPlnItr = svcModPlnList.iterator();
       while (svcModPlnItr.hasNext()){
           Map<String,Object> svcModPlnMap = svcModPlnItr.next();
     // END   2022/02/24 [QC#59701, MOD]
           String serNum = getString(svcModPlnMap, SER_NUM);
           String fromSerNum = getString(svcModPlnMap, SVC_MOD_FROM_SER_NUM);
           String toSerNum = getString(svcModPlnMap, SVC_MOD_TO_SER_NUM);
           //if the serial number has Non-numeric, check modification serial range by NSXC002001SerialRangeCheck.
           if (!NSXC002001SerialRangeCheck.isCheckSerialNum(fromSerNum, toSerNum, serNum)) {
                // START 2022/02/24 [QC#59701, MOD]
//               svcModPlnList.remove(svcModPlnMap);
               svcModPlnItr.remove();
                // END   2022/02/24 [QC#59701, MOD]
           }
       }
   }
   // END   2019/10/24 K.Fujimoto [QC#53870,ADD]
   // START 2019/10/29 K.Fujimoto [QC#53441, ADD]
   private boolean insertFsrEventForForceSend(NSZC107001PMsg pMsg) {
       if (!forceSendTaskFlg) {
           return true;
       }
       BigDecimal fsrEventPk = ZYPOracleSeqAccessor.getNumberBigDecimal("FSR_EVENT_SQ");
       FSR_EVENTTMsg fsrEventTMsg = new FSR_EVENTTMsg();
       FSR_VISITTMsg fsrVisitTMsg = getFsrVisit(pMsg);
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.glblCmpyCd, pMsg.glblCmpyCd);
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventPk, fsrEventPk);
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcDisptEventCd, SVC_DISPT_EVENT.SEND_TASK);
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.svcTaskNum, pMsg.svcTaskNum);
       if (fsrVisitTMsg != null) {
          ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrNum, fsrVisitTMsg.fsrNum);
          ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
       }
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.fsrEventExeTs, ZYPDateUtil.getCurrentSystemTime(FORMAT_TS));
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcProcCd, MBL_INTFC_PROC.NOT_PROCESSED);
       ZYPEZDItemValueSetter.setValue(fsrEventTMsg.mblIntfcFlg,  ZYPConstant.FLG_OFF_N);
       S21ApiTBLAccessor.insert(fsrEventTMsg);
       if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrEventTMsg.getReturnCode())) {
           msgMap.addXxMsgId(NSZM0083E);
           return false;
       }
       return true;
   }
   private FSR_VISITTMsg getFsrVisit(NSZC107001PMsg pMsg) {
       FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
       tMsg.setSQLID("002");
       tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
       tMsg.setConditionValue("svcTaskNum01", pMsg.svcTaskNum.getValue());
       FSR_VISITTMsgArray fsrVisitArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
       if (fsrVisitArray == null) {
          return null;
       }
       return fsrVisitArray.no(0);
   }
   // END   2019/10/29 K.Fujimoto [QC#53441, ADD]
   // START 2019/12/20 K.Fujimoto [QC#55106, ADD]
   /**
    * If the multiple operations has been sent even once in the task, return true.
    */
   private boolean isSentMultOps(String glblCmpyCd, String svcTaskNum) {
       boolean isSentMultipleOperations = false;
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("glblCmpyCd", glblCmpyCd);
       params.put("svcTaskNum", svcTaskNum);
       params.put("svcDisptEventCd", SVC_DISPT_EVENT.TBO);
       params.put("mblIntfcProcCd", MBL_INTFC_PROC.PROCESSED);
       BigDecimal sentMultOpsCnt = (BigDecimal) ssmClient.queryObject("getFsrEventCnt", params);
       if (sentMultOpsCnt != null && sentMultOpsCnt.compareTo(BigDecimal.ZERO) > 0) {
           isSentMultipleOperations = true;
       }
       return isSentMultipleOperations;
   }

   private List<Map<String, Object>> getMtr(NSZC107001PMsg pMsg, boolean isOnsSprtCall, Map<String, Object> paramMap, Map<String, Object> taskInfoMap) {
       if (isNeedToGetMtr(pMsg.glblCmpyCd.getValue(), isOnsSprtCall, taskInfoMap)) {
           // mod start 2020/03/03 QC#56123
           //List<Map<String, Object>> mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap));
           BigDecimal mtrUpThrhdFctNum = numConstMap.get(NSXC0030_MTR_UP_THRHD_FCT_NUM);
           // add start 2020/06/18 QC#
           BigDecimal mtrLowThrhdFctNum = numConstMap.get(NSXC0030_MTR_LOW_THRHD_FCT_NUM);
           List<Map<String, Object>> mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap, taskInfoMap, mtrUpThrhdFctNum, mtrLowThrhdFctNum));
           if (mtrInfoList.isEmpty()) {
               paramMap.put("techReadMndFlg", ZYPConstant.FLG_OFF_N);
               //mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap));
               mtrInfoList = ssmClient.queryObjectList("getMtrInfo", getMtrInfoParam(paramMap, taskInfoMap, mtrUpThrhdFctNum, mtrLowThrhdFctNum));
           }
           // mod end 2020/03/03 QC#56123
           return mtrInfoList;
       }
       return new ArrayList<Map<String, Object>>();
   }

   private boolean isNeedToGetMtr(String glblCmpyCd, boolean isOnsSprtCall, Map<String, Object> taskInfoMap) {
       // START 2023/03/03 K.Watanabe [QC#60926, ADD]
       if (ZYPConstant.FLG_OFF_N.equals(taskInfoMap.get(MTR_READ_REQ_FLG))) {
           return false;
       }
       // END   2023/03/03 K.Watanabe [QC#60926, ADD]
       boolean isForceSend = varConstMap.get(NSZC1070_MTR_SEND).equals(ZYPConstant.FLG_ON_Y);
       boolean isOnsSprtCallSend = varConstMap.get(NSZC1070_L2_MTR_SEND).equals(ZYPConstant.FLG_ON_Y);
       if (!isNeedToGetData(isOnsSprtCall, isForceSend, isOnsSprtCallSend, glblCmpyCd, getString(taskInfoMap, SVC_TASK_NUM))) {
           return false;
       }
       return true;
   }

   private List<Map<String, Object>> getMods(NSZC107001PMsg pMsg, boolean isOnsSprtCall, Map<String, Object> paramMap, Map<String, Object> taskInfoMap) {
       if (isNeedToGetMods(pMsg.glblCmpyCd.getValue(), isOnsSprtCall, taskInfoMap)) {
           List<Map<String, Object>> svcModPlnList = ssmClient.queryObjectList("getSvcModPln", getSvcModPlnParam(paramMap));
           removeOutOfRngSvcModPln(pMsg.glblCmpyCd.getValue(), svcModPlnList);
           // START 2021/10/04 L.Mandanas [QC#59114, ADD]
           removeSvcModPlnDuplicate(svcModPlnList);
           // END 2021/10/04 L.Mandanas [QC#59114, ADD]
           return svcModPlnList;
       }
       return new ArrayList<Map<String, Object>>();
   }

   private boolean isNeedToGetMods(String glblCmpyCd, boolean isOnsSprtCall, Map<String, Object> taskInfoMap) {
       boolean isForceSend = varConstMap.get(NSZC1070_MODS_SEND).equals(ZYPConstant.FLG_ON_Y);
       boolean isOnsSprtCallSend = varConstMap.get(NSZC1070_L2_MODS_SEND).equals(ZYPConstant.FLG_ON_Y);
       if (!isNeedToGetData(isOnsSprtCall, isForceSend, isOnsSprtCallSend, glblCmpyCd, getString(taskInfoMap, SVC_TASK_NUM))) {
           return false;
       }
       return true;
   }

   private List<Map<String, Object>> getInstlChkList(NSZC107001PMsg pMsg, boolean isOnsSprtCall, Map<String, Object> paramMap, Map<String, Object> taskInfoMap) {
       BigDecimal svcMachMstrPk = getBigDecimal(taskInfoMap, SVC_MACH_MSTR_PK);
       if (isNeedToGetInstlChkList(pMsg.glblCmpyCd.getValue(), isOnsSprtCall, taskInfoMap)) {
           if (isSvcExchOrd(pMsg.glblCmpyCd.getValue(), svcMachMstrPk)) {
               return getInstallCheckListForSvcExch(pMsg.glblCmpyCd.getValue(), svcMachMstrPk);
           } else {
               return ssmClient.queryObjectList("getInstallCheckList", getInstallCheckListParam(paramMap));
           }
       }
       return new ArrayList<Map<String, Object>>();
   }

   private boolean isNeedToGetInstlChkList(String glblCmpyCd, boolean isOnsSprtCall, Map<String, Object> taskInfoMap) {
       //if the call doesn't require Install-Check-List, return false.
       boolean isForceSend = varConstMap.get(NSZC1070_INSTL_CHK_LIST_SEND).equals(ZYPConstant.FLG_ON_Y);
       boolean isOnsSprtCallSend = varConstMap.get(NSZC1070_L2_ISTL_CHK_LST_SEND).equals(ZYPConstant.FLG_ON_Y);
       if (ZYPConstant.FLG_OFF_N.equals(getString(taskInfoMap, SVC_ISTL_REQ_FLG)) && ZYPConstant.FLG_OFF_N.equals(getString(taskInfoMap, SVC_DEINS_REQ_FLG))) {
           return false;
       }
       if (!isNeedToGetData(isOnsSprtCall, isForceSend, isOnsSprtCallSend, glblCmpyCd, getString(taskInfoMap, SVC_TASK_NUM))) {
           return false;
       }
       return true;
   }

   private boolean isNeedToGetData(boolean isOnsSprtCall, boolean isForceSend, boolean isOnsSprtCallSend, String glblCmpyCd, String svcTaskNum) {
       if (forceSendTaskFlg) {
          if (isForceSend) {
            return true;
          }
          return false;
       }
       if (isOnsSprtCall && !isOnsSprtCallSend) {
          return false;
       }
       return true;
   }

   // START 2022/03/03 K.Kitachi [QC#59700, MOD]
//   private boolean isSendList(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> installCheckList) {
   private boolean isSendList(Map<String, Object> svcTaskInfoMap, List<Map<String, Object>> mtrInfoList, List<Map<String, Object>> svcModPlnList, List<Map<String, Object>> csaTaskNoteList, List<Map<String, Object>> installCheckList) {
   // END 2022/03/03 K.Kitachi [QC#59700, MOD]
          if (svcModPlnList != null && svcModPlnList.size() > 0) {
              return true;
          }
          if (mtrInfoList != null && mtrInfoList.size() > 0) {
              return true;
          }
          // START 2022/03/03 K.Kitachi [QC#59700, ADD]
          if (csaTaskNoteList != null && csaTaskNoteList.size() > 0) {
              return true;
          }
          // END 2022/03/03 K.Kitachi [QC#59700, ADD]
          if (ZYPConstant.FLG_ON_Y.equals(getString(svcTaskInfoMap, SVC_ISTL_REQ_FLG)) && installCheckList != null && installCheckList.size() > 0) {
              return true;
          }
          if (ZYPConstant.FLG_ON_Y.equals(getString(svcTaskInfoMap, SVC_DEINS_REQ_FLG)) && installCheckList != null && installCheckList.size() > 0) {
              return true;
          }
       return false;
   }
   // END   2019/10/29 K.Fujimoto [QC#55106, ADD]

   // add start 2020/04/08 QC#56328
    private void insertSvcModSendClick(NSZC107001PMsg pMsg, List<Map<String, Object>> svcModList) {
        for (Map<String, Object> svcModMap : svcModList) {
            SVC_MOD_SEND_CLICKTMsg svcModSendClickTMsg = new SVC_MOD_SEND_CLICKTMsg();
            setValue(svcModSendClickTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(svcModSendClickTMsg.svcModSendClickPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_MOD_SEND_CLICK_SQ"));
            setValue(svcModSendClickTMsg.svcModPk, getBigDecimal(svcModMap, SVC_MOD_PK));
            setValue(svcModSendClickTMsg.svcModDtlPk, getBigDecimal(svcModMap, SVC_MOD_DTL_PK));
            setValue(svcModSendClickTMsg.svcMachMstrPk, getBigDecimal(svcModMap, SVC_MACH_MSTR_PK));
            setValue(svcModSendClickTMsg.svcTaskNum, getString(svcModMap, SVC_TASK_NUM));
            S21ApiTBLAccessor.create(svcModSendClickTMsg);
        }
    }
   // add end 2020/04/08 QC#56328

    // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//    // START 2020/05/18 K.Kitachi [QC#54615, ADD]
//    private boolean isSendRelnUpdTask(CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg) {
//        if (clickTaskSendRsltTMsg == null) {
//            return false;
//        }
//        if (!PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.procTaskExProcStsCd.getValue())) {
//            return false;
//        }
//        if (!PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.multOpProcStsCd.getValue())) {
//            return false;
//        }
//        if (!PROC_STS.ERROR.equals(clickTaskSendRsltTMsg.relnUpdTaskProcStsCd.getValue())) {
//            return false;
//        }
//        return true;
//    }
//    // END 2020/05/18 K.Kitachi [QC#54615, ADD]
    // END 2023/05/16 K.Kitachi [QC#61085, DEL]
    // START 2023/05/16 K.Kitachi [QC#61085, ADD]
    private boolean isSendMultOp(CLICK_TASK_SEND_RSLTTMsg clickTaskSendRsltTMsg) {
        if (clickTaskSendRsltTMsg == null) {
            return false;
        }
        if (!PROC_STS.COMPLEATED.equals(clickTaskSendRsltTMsg.procTaskExProcStsCd.getValue())) {
            return false;
        }
        if (!PROC_STS.ERROR.equals(clickTaskSendRsltTMsg.multOpProcStsCd.getValue())) {
            return false;
        }
        return true;
    }
    // END 2023/05/16 K.Kitachi [QC#61085, ADD]

    // START 2021/10/04 L.Mandanas [QC#59114, ADD]
    /**
     * Remove duplicate SVC_MOD_DTL_PK from svcModPlnList.
     * @param svcModPlnList List < Map < String, Object > > svcModPlnList
     */
    private void removeSvcModPlnDuplicate(
            final List < Map < String, Object > > svcModPlnList) {
        // START 2021/10/14 L.Mandanas [QC#59114, MOD]
          //List < String > svcModDtlPkList = new ArrayList < String >();
          //String dtlPk = null;
        // START 2022/01/12 L.Mandanas [QC#59114, DEL]
        //List < BigDecimal > svcModDtlPkList = new ArrayList < BigDecimal >();
        // END 2022/01/12 L.Mandanas [QC#59114, DEL]
        BigDecimal dtlPk = null;
        // END 2021/10/14 L.Mandanas [QC#59114, MOD]

        // START 2022/01/12 L.Mandanas [QC#59114, ADD]
        List < Map < BigDecimal, BigDecimal > > svcModDtlPkList
            = new ArrayList < Map < BigDecimal, BigDecimal > >();
        Map < BigDecimal, BigDecimal > svcModDtlPkMap
            = new HashMap < BigDecimal, BigDecimal>();
        BigDecimal machMstrPk = null;
        // END 2022/01/12 L.Mandanas [QC#59114, ADD]

        // START 2022/01/07 L.Mandanas [QC#59114, DEL]
//        for (Map < String, Object > svcModPlnMap : svcModPlnList) {
//            // START 2021/10/04 L.Mandanas [QC#59114, MOD]
//              //dtlPk = getString(svcModPlnMap, SVC_MOD_DTL_PK);
//            dtlPk = getBigDecimal(svcModPlnMap, SVC_MOD_DTL_PK);
//            // END 2021/10/04 L.Mandanas [QC#59114, MOD]
//            if (svcModDtlPkList.contains(dtlPk)) {
//                svcModPlnList.remove(svcModPlnMap);
//            // START 2021/10/04 L.Mandanas [QC#59114, MOD]
//              //} else {
//            } else if (!dtlPk.equals(BigDecimal.ZERO)) {
//            // END 2021/10/04 L.Mandanas [QC#59114, MOD]
//                svcModDtlPkList.add(dtlPk);
//            }
//        }
        // END 2022/01/07 L.Mandanas [QC#59114, DEL]
        // START 2022/01/07 L.Mandanas [QC#59114, ADD]
        Iterator < Map < String, Object > > iterator = svcModPlnList.iterator();
        while (iterator.hasNext()) {
            Map < String, Object > svcModPlnMap = iterator.next();
            dtlPk = getBigDecimal(svcModPlnMap, SVC_MOD_DTL_PK);
            // START 2022/01/12 L.Mandanas [QC#59114, ADD]
            machMstrPk = getBigDecimal(svcModPlnMap, SVC_MACH_MSTR_PK);
            svcModDtlPkMap.put(dtlPk, machMstrPk);
            // END 2022/01/12 L.Mandanas [QC#59114, ADD]
            // START 2022/01/12 L.Mandanas [QC#59114, MOD]
            //if (svcModDtlPkList.contains(dtlPk)) {
            if (svcModDtlPkList.contains(svcModDtlPkMap)) {
            // END 2022/01/12 L.Mandanas [QC#59114, MOD]
                iterator.remove();
            } else if (!dtlPk.equals(BigDecimal.ZERO)) {
                // START 2022/01/12 L.Mandanas [QC#59114, MOD]
                //svcModDtlPkList.add(dtlPk);
                // END 2022/01/12 L.Mandanas [QC#59114, MOD]
                svcModDtlPkList.add(svcModDtlPkMap);
            }
            // START 2022/01/12 L.Mandanas [QC#59114, ADD]
            svcModDtlPkMap = new HashMap < BigDecimal, BigDecimal >();
            // END 2022/01/12 L.Mandanas [QC#59114, ADD]
        }
        // END 2022/01/07 L.Mandanas [QC#59114, ADD]
    }
    // END 2021/10/04 L.Mandanas [QC#59114, ADD]

    // START 2022/09/02 K.Kitachi [QC#60536, ADD]
    private void setTimeZone(Task task, Map<String, Object> taskInfoMap) {
        String ctryCd = getString(taskInfoMap, CTRY_CD);
        String postCd = getString(taskInfoMap, POST_CD);
        if (!ZYPCommonFunc.hasValue(ctryCd) || !ZYPCommonFunc.hasValue(postCd)) {
            return;
        }
        if (postCd.length() > LEN_5) {
            postCd = postCd.substring(NUM_0, LEN_5);
        }
        String tzId = null;
        try {
            tzId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
        } catch (Exception e) {
        }
        task.setComment(tzId);
    }
    // END 2022/09/02 K.Kitachi [QC#60536, ADD]

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private String ofsMultiByteConvert(String inStr) {
        if (this.ofsMultiByteConvertFlg == false) {
            return inStr;
        }
        String outStr = ZYPCharacterConversionUtil.convertCharacter(inStr);
        return outStr;
    }
    // END   2022/10/19 [QC#60712, ADD]

    // START 2023/05/16 K.Kitachi [QC#61085, DEL]
//    // START 2022/11/16 K.Kitachi [QC#60821, ADD]
//    private void insRsltForSuccess(CLICK_TASK_SEND_RSLTTMsg inMsg, String glblCmpyCd, String fsrNum, String svcTaskNum) {
//        inMsg = new CLICK_TASK_SEND_RSLTTMsg();
//        setValue(inMsg.glblCmpyCd, glblCmpyCd);
//        setValue(inMsg.fsrNum, fsrNum);
//        setValue(inMsg.svcTaskNum, svcTaskNum);
//        setValue(inMsg.procTaskExProcStsCd, PROC_STS.COMPLEATED);
//        setValue(inMsg.rtrySendProcStsCd, PROC_STS.COMPLEATED);
//        S21ApiTBLAccessor.create(inMsg);
//    }
//    // END 2022/11/16 K.Kitachi [QC#60821, ADD]
    // END 2023/05/16 K.Kitachi [QC#61085, DEL]
    // START 2023/05/16 K.Kitachi [QC#61085, ADD]
    private void insRsltForSuccess(String glblCmpyCd, String fsrNum, String svcTaskNum, boolean taskExecFlg, boolean nsbi1020ExecFlg, boolean updateExecFlg) {
        CLICK_TASK_SEND_RSLTTMsg inMsg = new CLICK_TASK_SEND_RSLTTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.fsrNum, fsrNum);
        setValue(inMsg.svcTaskNum, svcTaskNum);
        if (taskExecFlg) {
            setValue(inMsg.procTaskExProcStsCd, PROC_STS.COMPLEATED);
        }
        if (nsbi1020ExecFlg) {
            setValue(inMsg.multOpProcStsCd, PROC_STS.COMPLEATED);
        }
        if (updateExecFlg) {
            setValue(inMsg.updTaskProcStsCd, PROC_STS.COMPLEATED);
        }
        setValue(inMsg.rtrySendProcStsCd, PROC_STS.COMPLEATED);
        S21ApiTBLAccessor.create(inMsg);
    }
    // END 2023/05/16 K.Kitachi [QC#61085, ADD]

    // START 2023/07/14 K.Watanabe [QC#61310, ADD]
    private boolean isShowRoomMachine(String glblCmpyCd, String svcTaskNum, String startDt) {
        boolean isShowRoomMachine = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        paramMap.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        paramMap.put("startDt", startDt);
        BigDecimal showRoomMachineCnt = (BigDecimal) ssmClient.queryObject("getShowRoomMachineCnt", paramMap);
        if (showRoomMachineCnt != null && showRoomMachineCnt.compareTo(BigDecimal.ZERO) > 0) {
            isShowRoomMachine = true;
        }
        return isShowRoomMachine;
    }
    // END 2023/07/14 K.Watanabe [QC#61310, ADD]
}