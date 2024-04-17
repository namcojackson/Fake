/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB039001;

import static com.canon.cusa.s21.batch.NSB.NSBB039001.constant.NSBB039001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_ISTL_CALL_MACH_INFOTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * NSBB039001
 * Install Call Creation Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2015   Hitachi         J.Kim           Create          N/A
 * 01/14/2015   Hitachi         J.Kim           Create          QC#3075
 * 01/20/2015   Hitachi         J.Kim           Create          QC#3373
 * 04/21/2016   Hitachi         K.Yamada        Update          QC#5930
 * 05/12/2016   Fujitsu         S.Nakai         Update          QC#8220
 * 05/24/2016   Hitachi         T.Iwamoto       Update          QC#8809
 * 06/29/2016   Hitachi         O.Okuma         Update          QC#10950
 * 06/30/2016   Hitachi         Y.Tsuchimoto    Update          QC#10692
 * 07/12/2016   Hitachi         Y.Takeno        Update          QC#8423
 * 08/10/2016   Fujitsu         S.Nakai         Update          QC#8421
 * 08/24/2016   Hitachi         K.Kasai         Update          QC#13662,13678
 * 09/07/2016   Fujitsu         S.Nakai         Update          QC#8421
 * 09/30/2016   Hitachi         T.Tomita        Update          QC#14145
 * 10/04/2016   Hitachi         T.Tomita        Update          QC#11896
 * 11/14/2016   Hitachi         T.Tomita        Update          QC#15957
 * 11/15/2016   Hitachi         T.Tomita        Update          QC#15957
 * 11/22/2016   Hitachi         T.Tomita        Update          QC#16124
 * 12/09/2016   Hitachi         T.Tomita        Update          QC#16490
 * 12/14/2016   Hitachi         T.Tomita        Update          QC#16490
 * 12/15/2016   Hitachi         N.Arai          Update          QC#16570
 * 2017/07/04   Hitachi         K.Kitachi       Update          QC#19698
 * 2017/07/19   Hitachi         K.Kojima        Update          QC#19987
 * 2017/09/04   Hitachi         U.Kim           Update          QC#20903
 * 2018/01/09   Hitachi         K.Kojima        Update          QC#19987
 * 2018/01/10   CITS            M.Naito         Update          QC#18889
 * 2018/01/19   Hitachi         K.Ochiai        Update          QC#18889
 * 2018/02/19   CITS            M.Naito         Update          QC#24203
 * 2018/02/28   Hitachi         T.Tomita        Update          QC#24359
 * 2018/03/12   Hitachi         T.Tomita        Update          QC#23424
 * 2018/08/07   Hitachi         A.Kohinata      Update          QC#27566
 * 2018/08/08   Hitachi         T.Tomita        Update          QC#27640
 * 2018/08/22   CITS            T.Wada          Update          QC#27857
 * 2018/09/18   CITS            T.Wada          Update          QC#19598
 * 2019/01/18   Hitachi         Y.Takeno        Update          QC#29081
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#30165
 * 2019/08/13   Hitachi         A.Kohinata      Update          QC#52475
 * 2019/08/23   Hitachi         K.Kitachi       Update          QC#52626
 * 2022/07/15   Hitachi         K.Kitachi       Update          QC#60311
 * 2022/12/12   Hitachi         K.Kitachi       Update          QC#60911
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * </pre>
 */
public class NSBB039001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** total Count */
    private int totalCount = 0;

    /** Error Count */
    private int errorCont = 0;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /** API Error message list */
    private List<String> apiErrMsgList = new ArrayList<String>();

    /** S21UserProfileService */
    private S21UserProfileService profileService;

// START 2016/07/12 [QC#8423, ADD]
    /** customer email address (not exists) */
    private String istlNotExistEmlAddr = null;
// END   2016/07/12 [QC#8423, ADD]

    // add start 2016/11/15 CSA Defect#15957
    /** VAR_CHAR_CONST : ctacPsnTpCd */
    private String ctacPsnTpCd = null;
    // add end 2016/11/15 CSA Defect#15957

    // START 2018/01/10 M.Naito [QC#18889, ADD]
    /** NUM_CONST : meet track early start days(ESS) */
    private BigDecimal meetTruckErlStrtDaysEss;

    /** NUM_CONST : meet track early start days(LFS) */
    private BigDecimal meetTruckErlStrtDaysLfs;

    /** NUM_CONST : meet track early start days(PPS) */
    private BigDecimal meetTruckErlStrtDaysPps;

    /** VAR_CHAR_CONST : meet track early start time(ESS) */
    private String meetTruckErlStrtTmEss;

    /** VAR_CHAR_CONST : meet track early start time(LFS) */
    private String meetTruckErlStrtTmLfs;

    /** VAR_CHAR_CONST : meet track early start time(PPS) */
    private String meetTruckErlStrtTmPps;

    /** NUM_CONST : meet track response time minute AOT */
    private BigDecimal meetTruckRspTmMnAot;
    // END 2018/01/10 M.Naito [QC#18889, ADD]

    // START 2018/02/19 M.Naito [QC#24203, ADD]
    /** Out of Territory Service Branch Code */
    private String outTrtySvcBrCd = null;
    // END 2018/02/19 M.Naito [QC#24203, ADD]

    // START 2018/08/23 T.Wada [QC#27857, ADD]
    private static final int svcCustAttnTxtLen = new SVC_TASKTMsg().getAttr("svcCustAttnTxt").getDigit();
    private static final int svcCustCllrTxtLen = new SVC_TASKTMsg().getAttr("svcCustCllrTxt").getDigit();
    // END 2018/08/23 T.Wada [QC#27857, ADD]

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** Click send exclusion call type list */
    private String[] clickSendExclCallTpList = null;
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /** Accessory Call Override call type list */
    private String[] accCallOvrdCallTpList = null;

    /** Service Bill Type Code */
    private String svcBillTpCd = null;
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NASM0010E);
        }

        // "Sales Date"
        // mod start 2016/01/20 CSA Defect#3373
        // this.slsDt = ZYPDateUtil.getBatProcDate(glblCmpyCd, PROGRAM_ID);
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        // mod end 2016/01/20 CSA Defect#3373
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // SVC_ISTL_NOT_EXIST_EML_ADDR
        this.istlNotExistEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_ISTL_NOT_EXIST_EML_ADDR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.istlNotExistEmlAddr)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_ISTL_NOT_EXIST_EML_ADDR });
        }

        // add start 2016/11/15 CSA Defect#15957
        this.ctacPsnTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NSBB0390_CTAC_PSN_TP_CD, this.glblCmpyCd);
        // add end 2016/11/15 CSA Defect#15957

        // START 2018/01/10 M.Naito [QC#18889, ADD]
        this.meetTruckErlStrtDaysEss = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_MEET_TRUCK_ERL_START_DAYS_ESS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckErlStrtDaysEss)) {
            throw new S21AbendException(NSBM0069E, new String[] {NUM_CONST_MEET_TRUCK_ERL_START_DAYS_ESS });
        }
        this.meetTruckErlStrtDaysLfs = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_MEET_TRUCK_ERL_START_DAYS_LFS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckErlStrtDaysLfs)) {
            throw new S21AbendException(NSBM0069E, new String[] {NUM_CONST_MEET_TRUCK_ERL_START_DAYS_LFS });
        }
        this.meetTruckErlStrtDaysPps = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_MEET_TRUCK_ERL_START_DAYS_PPS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckErlStrtDaysPps)) {
            throw new S21AbendException(NSBM0069E, new String[] {NUM_CONST_MEET_TRUCK_ERL_START_DAYS_PPS });
        }
        this.meetTruckErlStrtTmEss = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckErlStrtTmEss)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS });
        }
        this.meetTruckErlStrtTmLfs = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckErlStrtTmLfs)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS });
        }
        this.meetTruckErlStrtTmPps = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckErlStrtTmPps)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS });
        }
        this.meetTruckRspTmMnAot = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.meetTruckRspTmMnAot)) {
            throw new S21AbendException(NSBM0069E, new String[] {NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT });
        }
        // END 2018/01/10 M.Naito [QC#18889, ADD]
        // START 2018/02/19 M.Naito [QC#24203, ADD]
        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD,this.glblCmpyCd);
        // END 2018/02/19 M.Naito [QC#24203, MOD]

        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, this.glblCmpyCd);
        this.clickSendExclCallTpList = clickSendExclCallTp.split(STR_COMMA);
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profileService = getUserProfileService();

        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        String accCallOvrdCallTp = ZYPCodeDataUtil.getVarCharConstValue(ACC_CALL_OVRD_CALL_TP, this.glblCmpyCd);
        this.accCallOvrdCallTpList = accCallOvrdCallTp.split(STR_COMMA);

        DS_SVC_CALL_TPTMsg dsSvcCallTp = getDsSvcCallTp(DS_SVC_CALL_TP_CD_ACC_INSTALL, this.glblCmpyCd);
        this.svcBillTpCd = dsSvcCallTp.svcBillTpCd.getValue();
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
    }

    @Override
    protected void mainRoutine() {
        // QC#19598 Add Start
        regSvcIstlCallMachInfo();
        // QC#19598 Add End

        doProcess();
        // add start 2016/10/04 CSA Defect#11896
        // Create Remove Call (Stand alone)
        createRmvCallForStandAlone();
        // add end 2016/10/04 CSA Defect#11896
        // START 2017/07/04 K.Kitachi [QC#19698, ADD]
        updateFsrVisitTaskSts();
        // END 2017/07/04 K.Kitachi [QC#19698, ADD]
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB039001().executeBatch(NSBB039001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {

        sendEmail();
        if (this.errorCont > 0) {
            // START 2016/06/30 [QC#10692,MOD]
            this.termCd = TERM_CD.WARNING_END;
            // END   2016/06/30 [QC#10692,MOD]
        }
        setTermState(this.termCd, this.totalCount - this.errorCont, this.errorCont, this.totalCount);
    }

    // QC#19598 Add Start
    private void regSvcIstlCallMachInfo() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getTargetData();
            rs = ps.executeQuery();

            while (rs.next()) {
                insertSvcIstlCallMachInfo(rs);
            }
            commit();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    private void insertSvcIstlCallMachInfo(ResultSet rs) throws SQLException {

        SVC_ISTL_CALL_MACH_INFOTMsg inParam = new SVC_ISTL_CALL_MACH_INFOTMsg();
        String cpoOrdNum = null ;
        BigDecimal svcMachMstrPk = null ;

        cpoOrdNum = rs.getString("CPO_ORD_NUM") ;
        svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK") ;
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, svcMachMstrPk);

        EZDTBLAccessor.insert(inParam);
    }
    private void removeSvcIstlCallMachInfo(ResultSet rs) throws SQLException {

        SVC_ISTL_CALL_MACH_INFOTMsg inParam = new SVC_ISTL_CALL_MACH_INFOTMsg();
        String cpoOrdNum = null ;
        BigDecimal svcMachMstrPk = null ;

        cpoOrdNum = rs.getString("CPO_CTAC_ORD_NUM") ;
        svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK") ;
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, svcMachMstrPk);
        inParam = (SVC_ISTL_CALL_MACH_INFOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        S21ApiTBLAccessor.remove(inParam);
    }
    // QC#19598 Add End

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    private DS_SVC_CALL_TPTMsg getDsSvcCallTp(String dsSvcCallTpCd ,String glblCmpyCd) {

        DS_SVC_CALL_TPTMsg inParam = new DS_SVC_CALL_TPTMsg();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsSvcCallTpCd, dsSvcCallTpCd);

        return (DS_SVC_CALL_TPTMsg) EZDTBLAccessor.findByKey(inParam);
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getSvcInstallCallMachineInfo();
            rs = ps.executeQuery();

            // add start 2016/10/26 CSA Defect#11896
            boolean errFlg;
            // add end 2016/10/26 CSA Defect#11896
            while (rs.next()) {

                InstallCallMachineInfoBean bean = setInstallCallMachineInfoBean(rs);
                // add start 2016/09/30 CSA Defect#14145
                if (!isCreateSvcExchCall(bean)) {
                    continue;
                }
                // add end 2016/09/30 CSA Defect#14145
                InstallCallMachineInfoBean cpoCtacPsnbean = setCpoCtacPsnInfo(bean);
                List<Map<String, Object>> dsSvcCallTpInfoList = getDsSvcCallTpInfo(bean.getSvcIstlCalGrpNum());

                NSZC043001PMsg pMsg = new NSZC043001PMsg();
                // START 2016/08/24 [QC#13662,13678, ADD]
                NSZC045001PMsg addTaskPMsg = new NSZC045001PMsg();
                // END 2016/08/24 [QC#13662,13678, ADD]
                // mod start 2016/10/26 CSA Defect#11896
                errFlg = false;
                this.totalCount++;
                for (int index = 0; index < dsSvcCallTpInfoList.size(); index++) {
                    dsSvcCallTpInfoMap(bean, dsSvcCallTpInfoList.get(index));
                    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
                    String fsrNum;
                    String fsrVisitNum;
                    String dsSvcCallTpCd;
                    // END 2019/02/15 K.Kitachi [QC#30165, ADD]
                    // NSZC0430 FSR Update API
                    if (index == 0) {
                        pMsg = callFsrUpdateApi(bean, cpoCtacPsnbean);
                        if (this.apiErrMsgList.size() > 0) {
                            addMsgList(pMsg);
                            this.apiErrMsgList.clear();
                            rollback();
                            errFlg = true;
                            continue;
                        }
                        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
                        fsrNum = pMsg.fsrNum.getValue();
                        fsrVisitNum = pMsg.fsrVisitNum.getValue();
                        dsSvcCallTpCd = pMsg.dsSvcCallTpCd.getValue();
                        // END 2019/02/15 K.Kitachi [QC#30165, ADD]
                    // NSZC0450 Add Task API
                    } else {
                        // START 2016/08/24 [QC#13662,13678, MOD]
                        //NSZC045001PMsg addTaskPMsg = callAddTaskApi(bean, pMsg, cpoCtacPsnbean);
                        addTaskPMsg = callAddTaskApi(bean, pMsg, cpoCtacPsnbean);
                        // END 2016/08/24 [QC#13662,13678, MOD]
                        if (this.apiErrMsgList.size() > 0) {
                            addMsgList(addTaskPMsg);
                            this.apiErrMsgList.clear();
                            rollback();
                            errFlg = true;
                            continue;
                        }
                        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
                        fsrNum = addTaskPMsg.fsrNum.getValue();
                        fsrVisitNum = addTaskPMsg.fsrVisitNum.getValue();
                        dsSvcCallTpCd = addTaskPMsg.dsSvcCallTpCd.getValue();
                        // END 2019/02/15 K.Kitachi [QC#30165, ADD]
                    }
                    // START 2018/02/19 M.Naito [QC#24203, MOD]
                    // START 2016/08/24 [QC#13662,13678, ADD]
                    // START 2018/01/10 M.Naito [QC#18889, ADD]
//                    if (SHPG_STS.ARRIVED.equals(bean.getShpgStsCd())) {
//                    if (SHPG_STS.ARRIVED.equals(bean.getShpgStsCd()) || ZYPConstant.FLG_ON_Y.equals(bean.getTechMeetTruckFlg())) {
                    // mod start 2018/08/07 QC#27566
                    //if (!isOutTrtySvcBr(bean.getSvcMachMstrPk())) {
                    // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//                    if (!isThirdPartyCall(bean.getSvcMachMstrPk(), pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue())) {
                    // mod start 2019/08/13 QC#52475
                    //if (!isThirdPartyCall(bean.getSvcMachMstrPk(), fsrNum, fsrVisitNum, dsSvcCallTpCd)) {
                    // END 2019/02/15 K.Kitachi [QC#30165, ADD]
                    // mod end 2018/08/07 QC#27566
                    // END 2018/01/10 M.Naito [QC#18889, ADD]
                    // NSZC0430 FSR Update API(MODE:Update FSR)
                    pMsg = callFsrUdateApiForUpdateFsrMode(bean, pMsg, addTaskPMsg, index, dsSvcCallTpCd);
                    if (this.apiErrMsgList.size() > 0) {
                        addMsgList(pMsg);
                        this.apiErrMsgList.clear();
                        rollback();
                        errFlg = true;
                        continue;
                    }
                    //}
                    // mod end 2019/08/13 QC#52475
                    // END 2016/08/24 [QC#13662,13678, ADD]
                    // END 2018/02/19 M.Naito [QC#24203, MOD]
                }
                if (errFlg) {
                    this.errorCont++;
                    continue;
                }
                // Create Remove Call (Remove with install)
                if (!createRmvCallForSvcExch(bean)) {
                    rollback();
                    continue;
                }
                // QC#19598 Add Start
                removeSvcIstlCallMachInfo(rs);
                // QC#19598 Add End

                commit();
                // mod end 2016/10/26 CSA Defect#11896
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private InstallCallMachineInfoBean setInstallCallMachineInfoBean(ResultSet rs) throws SQLException {

        InstallCallMachineInfoBean bean = new InstallCallMachineInfoBean();
        bean.setSoNum(rs.getString("SO_NUM"));
        bean.setCpoGlblCmpyCd(rs.getString("CPO_CTAC_GLBL_CMPY_CD"));
        bean.setCpoOrdNum(rs.getString("CPO_CTAC_ORD_NUM"));
        bean.setCpoConfigPk(rs.getBigDecimal("CPO_CTAC_CONFIG_PK"));
        bean.setCpoDtlLineNum(rs.getString("CPO_CTAC_DTL_LINE_NUM"));
        bean.setCpoDtlLineSubNum(rs.getString("CPO_CTAC_DTL_LINE_SUB_NUM"));
        bean.setFirstProdCtrlCd(rs.getString("FIRST_PROD_CTRL_CD"));
        bean.setCustPoNum(rs.getString("CUST_ISS_PO_NUM"));
        bean.setCustPoDt(rs.getString("CUST_ISS_PO_DT"));
        bean.setActlDelyDt(rs.getString("ACTL_DELY_DT"));
        bean.setActlDelyTm(rs.getString("ACTL_DELY_TM"));
        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        bean.setAddAsryFlg(rs.getString("ADD_ASRY_FLG"));
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
        // START 2016/08/24 [QC#13662,13678, ADD]
        if (rs.getString("ACTL_DELY_DT") != null && rs.getString("ACTL_DELY_TM") == null) {
            bean.setActlDelyTm(DEF_HHMMSS);
        }
        // END 2016/08/24 [QC#13662,13678, ADD]
        // START 2016/12/15 N.Arai [QC#16570, MOD]
        if (ZYPCommonFunc.hasValue(bean.getActlDelyTm()) && bean.getActlDelyTm().length() != STRING_LEN6) {
            bean.setActlDelyTm(ZYPCommonFunc.rightPad(bean.getActlDelyTm(), STRING_LEN6, "0"));
        }
        // END 2016/12/15 N.Arai [QC#16570, MOD]
        bean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        bean.setCustMachCtrlNum(rs.getString("CUST_MACH_CTRL_NUM"));
        // mod start 2016/10/04 CSA Defect#11896
        bean.setBillToAcctNum(rs.getString("BILL_TO_ACCT_NUM"));
        // mod end 2016/10/04 CSA Defect#11896
        bean.setSerNum(rs.getString("SER_NUM"));
        bean.setSvcIstlCalGrpNum(rs.getString("SVC_ISTL_CALL_GRP_NUM"));
        // START 2016/08/24 [QC#13662,13678, DEL]
//        bean.setActlDelyDt(rs.getString("DSOD_ACTL_DELY_DT"));
//        bean.setActlDelyTm(rs.getString("DSOD_ACTL_DELY_TM"));
        // END 2016/08/24 [QC#13662,13678, DEL]
        bean.setSvcCustAttnTxt1(rs.getString("ISTL_ADDL_CMNT_TXT1"));
        bean.setRqstIstlDt1(rs.getString("RQST_ISTL_DT1"));
        bean.setRqstIstlTm1(rs.getString("RQST_ISTL_TM1"));
        // START 2016/12/15 N.Arai [QC#16570, MOD]
        if (ZYPCommonFunc.hasValue(bean.getRqstIstlTm1()) && bean.getRqstIstlTm1().length() != STRING_LEN6) {
            bean.setRqstIstlTm1(ZYPCommonFunc.rightPad(bean.getRqstIstlTm1(), STRING_LEN6, "0"));
        }
        // END 2016/12/15 N.Arai [QC#16570, MOD]
        bean.setTechCd1(rs.getString("ISTL_TECH_CD1"));
        bean.setDciiEzUpUserId1(rs.getString("EZUPUSERID1"));
        bean.setSvcCustAttnTxt2(rs.getString("ISTL_ADDL_CMNT_TXT2"));
        bean.setRqstIstlDt2(rs.getString("RQST_ISTL_DT2"));
        bean.setRqstIstlTm2(rs.getString("RQST_ISTL_TM2"));
        // START 2016/12/15 N.Arai [QC#16570, MOD]
        if (ZYPCommonFunc.hasValue(bean.getRqstIstlTm2()) && bean.getRqstIstlTm2().length() != STRING_LEN6) {
            bean.setRqstIstlTm2(ZYPCommonFunc.rightPad(bean.getRqstIstlTm2(), STRING_LEN6, "0"));
        }
        // END 2016/12/15 N.Arai [QC#16570, MOD]
        bean.setTechCd2(rs.getString("ISTL_TECH_CD2"));
        bean.setDciiEzUpUserId2(rs.getString("EZUPUSERID2"));
        bean.setSosEzUpUserId(rs.getString("SOS_EZUPUSERID"));
        // START 2016/08/24 [QC#13662,13678, ADD]
        bean.setShpgStsCd(rs.getString("SHPG_STS_CD"));
        // END 2016/08/24 [QC#13662,13678, ADD]
        // add start 2016/09/30 CSA Defect#14145
        bean.setSvcExchFlg(rs.getString("SVC_EXCH_FLG"));
        bean.setPreSvcConfigMstrPk(rs.getBigDecimal("PRE_SVC_CONFIG_MSTR_PK"));
        bean.setMachMdseCd(rs.getString("MACH_MDSE_CD"));
        bean.setDsCpoIstlInfoPk1(rs.getBigDecimal("DS_CPO_ISTL_INFO_PK1"));
        bean.setDsCpoIstlInfoPk2(rs.getBigDecimal("DS_CPO_ISTL_INFO_PK2"));
        // add end 2016/09/30 CSA Defect#14145
        // START 2018/01/10 M.Naito [QC#18889, ADD]
        bean.setTechMeetTruckFlg(rs.getString("TECH_MEET_TRUCK_FLG"));
        bean.setSvcByLineBizTpCd(rs.getString("SVC_BY_LINE_BIZ_TP_CD"));
        bean.setSchdDelyDt(rs.getString("SCHD_DELY_DT"));
        bean.setSchdDelyTm(rs.getString("SCHD_DELY_TM"));
        // END 2018/01/10 M.Naito [QC#18889, ADD]
        // START 2018/01/19 K.Ochiai [QC#18889, ADD]
        bean.setCtryCd(rs.getString("CTRY_CD"));
        bean.setPostCd(rs.getString("POST_CD"));
        // END 2018/01/19 K.Ochiai [QC#18889, ADD]
        return bean;
    }

    private void dsSvcCallTpInfoMap(InstallCallMachineInfoBean bean, Map<String, Object> dsSvcCallTpInfoMap) {

        // START 2023/09/05 K.Watanabe [QC#53408, MOD]
        // bean.setBypsPrfTechFlg((String) dsSvcCallTpInfoMap.get("BYPS_PRF_TECH_FLG"));
        // bean.setDsSvcCallTpCd((String) dsSvcCallTpInfoMap.get("DS_SVC_CALL_TP_CD_DTL"));
        // bean.setPrtChrgFlg((String) dsSvcCallTpInfoMap.get("PRT_CHRG_FLG"));
        // bean.setSvcLborChrgFlg((String) dsSvcCallTpInfoMap.get("PRT_CHRG_FLG"));
        // bean.setSvcCallPrtyCd((String) dsSvcCallTpInfoMap.get("SVC_CALL_PRTY_CD"));
        if (ZYPConstant.FLG_ON_Y.equals(bean.getAddAsryFlg()) && Arrays.asList(this.accCallOvrdCallTpList).contains(dsSvcCallTpInfoMap.get("DS_SVC_CALL_TP_CD_DTL"))) {
            bean.setDsSvcCallTpCd(DS_SVC_CALL_TP_CD_ACC_INSTALL);
            bean.setSvcBillTpCd(this.svcBillTpCd);
        } else {
            bean.setBypsPrfTechFlg((String) dsSvcCallTpInfoMap.get("BYPS_PRF_TECH_FLG"));
            bean.setDsSvcCallTpCd((String) dsSvcCallTpInfoMap.get("DS_SVC_CALL_TP_CD_DTL"));
            bean.setPrtChrgFlg((String) dsSvcCallTpInfoMap.get("PRT_CHRG_FLG"));
            bean.setSvcLborChrgFlg((String) dsSvcCallTpInfoMap.get("PRT_CHRG_FLG"));
            bean.setSvcCallPrtyCd((String) dsSvcCallTpInfoMap.get("SVC_CALL_PRTY_CD"));
        }
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
    }

    private NSZC043001PMsg callFsrUpdateApi(InstallCallMachineInfoBean bean, InstallCallMachineInfoBean cpoBean) {

        // NSZC0430 FSR Update API
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_CREATE_FSR);
        // mod start 2016/05/24 CSA Defect#8809
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, bean.getBypsPrfTechFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.soNum, bean.getSoNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstProdCtrlCd, bean.getFirstProdCtrlCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, bean.getCustMachCtrlNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, bean.getSerNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, cpoBean.getCustTelNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, cpoBean.getCustTelExtnNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, cpoBean.getCustEmlAddr());
        // START 2016/07/12 [QC#8423, MOD]
        //ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, getSvcCustAttnTxt(bean));

        // START 2018/08/22 [QC#27857, MOD]
        //ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, cpoBean.getSvcCustAttnTxt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, getSvcCustAttnTxt(cpoBean.getSvcCustAttnTxt()));
        // END 2018/08/22 [QC#27857, MOD]

        // END   2016/07/12 [QC#8423, MOD]
        // START 2017/09/04 U.Kim [QC#20903,MOD]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, bean.getCustPoNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, substringPoNum(bean.getCustPoNum()));
        // END 2017/09/04 U.Kim [QC#20903,MOD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, bean.getCustPoDt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, bean.getDsSvcCallTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        String sysDateTime = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, sysDateTime.substring(0, SUBSTRING_LEN8));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, sysDateTime.substring(SUBSTRING_LEN8, SUBSTRING_LEN14));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdDt, getSvcTaskSchdDt(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdTm, getSvcTaskSchdTm(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdByUsrId, getSvcTaskSchdByUsrId(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, bean.getPrtChrgFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, bean.getSvcLborChrgFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, getPmtTermCashDiscCd(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcPblmTpCd, "9000"); // INSTALLATION)
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.S21_SYSTEM);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);

        // START 2016/07/12 [QC#8423, ADD]
        // START 2018/08/22 [QC#27857, MOD]
        //ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, cpoBean.getSvcCustCllrTxt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, getSvcCustCllrTxt(cpoBean.getSvcCustCllrTxt()));
        // END   2018/08/22 [QC#27857, MOD]
        // END   2016/07/12 [QC#8423, ADD]
        // START 2022/07/15 K.Kitachi [QC#60311, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2022/07/15 K.Kitachi [QC#60311, ADD]

        // Service Task List
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCallPrtyCd, bean.getSvcCallPrtyCd());
        // START 2016/08/24 [QC#13662,13678, MOD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).techCd, bean.getTechCd2());
        // END 2016/08/24 [QC#13662,13678, MOD]
        // START 2022/12/12 K.Kitachi [QC#60911, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        // END 2022/12/12 K.Kitachi [QC#60911, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).techSchdFromDt, getSvcTaskSchdDt(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).techSchdFromTm, getSvcTaskSchdTm(bean));

        apiPMsg.taskList.no(0).erlStartTs.clear();
        apiPMsg.taskList.no(0).lateStartTs.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // mod start 2016/12/09 CSA Defect#16490
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/12/09 CSA Defect#16490
        // START 2016/07/12 [QC#8423, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelNum, cpoBean.getSvcCustCllrTelNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelExtnNum, cpoBean.getSvcCustCllrTelExtnNum());
        // END   2016/07/12 [QC#8423, ADD]
        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        setApiErrMsgList(apiPMsg);

        // START 2018/01/10 M.Naito [QC#18889, ADD]
        // Update SVC_TASK for Tech Meet The Truck
        // START 2018/01/25 K.Ochiai [QC#18889, MOD]
        String erlStartTs = getMeetTruckErlStrtTs(bean, BigDecimal.ZERO);
        String lateStartTs = getMeetTruckErlStrtTs(bean, meetTruckRspTmMnAot);
        SVC_TASKTMsg tMsg = getSvcTask(apiPMsg.fsrNum.getValue());
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.erlStartTs, erlStartTs);
            ZYPEZDItemValueSetter.setValue(tMsg.lateStartTs, lateStartTs);
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK, tMsg.svcTaskNum.getValue() });
            }
        }
        // END 2018/01/25 K.Ochiai [QC#18889, MOD]
        // END 2018/01/10 M.Naito [QC#18889, ADD]
        return apiPMsg;
    }

    // START 2016/08/24 [QC#13662,13678, ADD]
    // mod start 2019/08/13 QC#52475
    //private NSZC043001PMsg callFsrUdateApiForUpdateFsrMode(InstallCallMachineInfoBean bean, NSZC043001PMsg pMsg, NSZC045001PMsg addTaskPMsg, int index) {
    private NSZC043001PMsg callFsrUdateApiForUpdateFsrMode(InstallCallMachineInfoBean bean, NSZC043001PMsg pMsg, NSZC045001PMsg addTaskPMsg, int index, String dsSvcCallTpCd) {
    // mod end 2019/08/13 QC#52475

        // NSZC0430 FSR Update API
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        // mod start 2019/08/13 QC#52475
        //ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
        boolean isThirdPartyCall = false;
        if (index == 0) {
            isThirdPartyCall = isThirdPartyCall(bean.getSvcMachMstrPk(), pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue(), dsSvcCallTpCd);
        } else {
            isThirdPartyCall = isThirdPartyCall(bean.getSvcMachMstrPk(), addTaskPMsg.fsrNum.getValue(), addTaskPMsg.fsrVisitNum.getValue(), dsSvcCallTpCd);
        }
        if (isThirdPartyCall) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
        }
        // mod end 2019/08/13 QC#52475
        // Add Start 2018/08/08 QC#27640
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // Add End 2018/08/08 QC#27640
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());

        // Service Task List
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcTaskNum, addTaskPMsg.xxSvcTaskList.no(0).svcTaskNum);
        }
        // START 2018/01/09 K.Kojima [QC#19987,DEL]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).erlStartTs, ZYPCommonFunc.rightPad(bean.getActlDelyDt(), 17, "0"));
        // END 2018/01/09 K.Kojima [QC#19987,DEL]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).techCd, bean.getTechCd2());
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // START 2018/01/09 K.Kojima [QC#19987,DEL]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
        // END 2018/01/09 K.Kojima [QC#19987,DEL]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);

        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        setApiErrMsgList(apiPMsg);

        // START 2018/01/10 M.Naito [QC#18889, ADD]
        // Update SVC_TASK for Tech Meet The Truck
        // START 2018/01/25 K.Ochiai [QC#18889, MOD]
        String erlStartTs = getMeetTruckErlStrtTs(bean, BigDecimal.ZERO);
        String lateStartTs = getMeetTruckErlStrtTs(bean, meetTruckRspTmMnAot);
        SVC_TASKTMsg tMsg = getSvcTask(apiPMsg.fsrNum.getValue());
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.erlStartTs, erlStartTs);
            ZYPEZDItemValueSetter.setValue(tMsg.lateStartTs, lateStartTs);
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK, tMsg.svcTaskNum.getValue() });
            }
        }
        // END 2018/01/25 K.Ochiai [QC#18889, MOD]
        // END 2018/01/10 M.Naito [QC#18889, ADD]
        return apiPMsg;
    }
    // END 2016/08/24 [QC#13662,13678, ADD]

    private NSZC045001PMsg callAddTaskApi(InstallCallMachineInfoBean bean, NSZC043001PMsg pMsg, InstallCallMachineInfoBean cpoBean) {

        // NSZC0450 Add Task API
        NSZC045001PMsg apiPMsg = new NSZC045001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_ADD_TASK);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrVisitNum, pMsg.fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, bean.getBypsPrfTechFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.soNum, bean.getSoNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstProdCtrlCd, bean.getFirstProdCtrlCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, bean.getCustMachCtrlNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, bean.getSerNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, cpoBean.getCustTelNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, cpoBean.getCustTelExtnNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, cpoBean.getCustEmlAddr());
        // START 2016/07/12 [QC#8423, MOD]
        //ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, getSvcCustAttnTxt(bean));

        // START 2018/08/22 [QC#27857, MOD]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, cpoBean.getSvcCustAttnTxt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, getSvcCustAttnTxt(cpoBean.getSvcCustAttnTxt()));
        // END 2018/08/22 [QC#27857, MOD]

        // START 2016/07/12 [QC#8423, MOD]
        // START 2017/09/04 U.Kim [QC#20903,MOD]
        // ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, bean.getCustPoNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, substringPoNum(bean.getCustPoNum()));
        // END 2017/09/04 U.Kim [QC#20903,MOD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, bean.getCustPoDt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, bean.getDsSvcCallTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        String sysDateTime = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, sysDateTime.substring(0, SUBSTRING_LEN8));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, sysDateTime.substring(SUBSTRING_LEN8, SUBSTRING_LEN14));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdDt, getSvcTaskSchdDt(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdTm, getSvcTaskSchdTm(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdByUsrId, getSvcTaskSchdByUsrId(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, bean.getPrtChrgFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, bean.getSvcLborChrgFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, getPmtTermCashDiscCd(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcPblmTpCd, "9000"); // INSTALLATION
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.S21_SYSTEM);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);

        // START 2016/07/12 [QC#8423, ADD]
        // START 2018/08/22 [QC#27857, MOD]
        //ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, cpoBean.getSvcCustCllrTxt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, getSvcCustCllrTxt(cpoBean.getSvcCustCllrTxt()));
        // END   2018/08/22 [QC#27857, MOD]
        // END   2016/07/12 [QC#8423, ADD]

        // Service Task List
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(0).svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(0).svcCallPrtyCd, bean.getSvcCallPrtyCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(0).techSchdFromDt, getSvcTaskSchdDt(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxSvcTaskList.no(0).techSchdFromTm, getSvcTaskSchdTm(bean));
        apiPMsg.xxSvcTaskList.no(0).erlStartTs.clear();
        apiPMsg.xxSvcTaskList.no(0).lateStartTs.clear();
        // mod start 2016/12/09 CSA Defect#16490
        apiPMsg.xxSvcTaskList.no(0).ovrdFlg.setValue(ZYPConstant.FLG_OFF_N);
        // mod end 2016/12/09 CSA Defect#16490
        apiPMsg.xxSvcTaskList.setValidCount(1);

        new NSZC045001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        setApiErrMsgList(apiPMsg);
        return apiPMsg;
    }
 // START 2016/07/12 [QC#8423, DEL]
    /*
        private String getSvcCustAttnTxt(InstallCallMachineInfoBean bean) {

            String svcCustAttnTxt = null;
            String svcCustAttnTx1 = bean.getSvcCustAttnTxt1();
            String svcCustAttnTx2 = bean.getSvcCustAttnTxt2();
            if (svcCustAttnTx1 != null) {
                svcCustAttnTxt = getSubStringTxt(svcCustAttnTx1);
            } else if (svcCustAttnTx2 != null) {
                svcCustAttnTxt = getSubStringTxt(svcCustAttnTx2);
            }
            return svcCustAttnTxt;
        }

        private String getSubStringTxt(String svcCustAttnTxt) {

            if (svcCustAttnTxt.length() > ISTL_ADDL_CMNT_TXT_LEN) {
                svcCustAttnTxt = svcCustAttnTxt.substring(0, ISTL_ADDL_CMNT_TXT_LEN);
            }
            return svcCustAttnTxt;
        }
    */
    // END   2016/07/12 [QC#8423, DEL]

    // START 2018/08/22 [QC#27857, ADD]
    private String getSvcCustAttnTxt(String inTxt) {
      if (inTxt != null) {
          if (inTxt.length() > svcCustAttnTxtLen) {
              inTxt = inTxt.substring(0, svcCustAttnTxtLen);
          }
      } 
      return inTxt;
    }
    private String getSvcCustCllrTxt(String inTxt) {
        if (inTxt != null) {
            if (inTxt.length() > svcCustCllrTxtLen) {
                inTxt = inTxt.substring(0, svcCustCllrTxtLen);
            }
        } 
        return inTxt;
    }
    // END 2018/08/22 [QC#27857, ADD]

    private String getPmtTermCashDiscCd(InstallCallMachineInfoBean bean) {

        String pmtTermCashDiscCd = null;
        DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, bean.getBillToAcctNum());
        DS_ACCT_CR_PRFLTMsg outTMsg = (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (outTMsg != null) {
            pmtTermCashDiscCd = outTMsg.pmtTermCashDiscCd.getValue();
        }
        return pmtTermCashDiscCd;
    }

    private String getSvcTaskSchdByUsrId(InstallCallMachineInfoBean bean) {

        String userId = null;
        // Mod Start 2018/02/28 QC#24359
        if (ZYPCommonFunc.hasValue(bean.getSosEzUpUserId())) {
            userId = bean.getSosEzUpUserId();
        } else {
            if (ZYPCommonFunc.hasValue(bean.getDciiEzUpUserId1())) {
                userId = bean.getDciiEzUpUserId1();
            } else if (ZYPCommonFunc.hasValue(bean.getDciiEzUpUserId2())) {
                userId = bean.getDciiEzUpUserId2();
            }
        }
        // Mod End 2018/02/28 QC#24359
        return userId;
    }

    private String getSvcTaskSchdDt(InstallCallMachineInfoBean bean) {
        String svcTaskSchdDtTm = getSvcTaskSchdDtTm(bean);
        if (svcTaskSchdDtTm != null) {
            return svcTaskSchdDtTm.substring(0, SUBSTRING_LEN8);
        }
        return null;
    }

    private String getSvcTaskSchdTm(InstallCallMachineInfoBean bean) {
        String svcTaskSchdDtTm = getSvcTaskSchdDtTm(bean);
        if (svcTaskSchdDtTm != null) {
            return svcTaskSchdDtTm.substring(SUBSTRING_LEN8, SUBSTRING_LEN14);
        }
        return null;
    }

    private String getSvcTaskSchdDtTm(InstallCallMachineInfoBean bean) {

        String rtnDtTm = null;
        StringBuffer actlDely = new StringBuffer();
        StringBuffer rqstIstl = new StringBuffer();

        if (bean.getRqstIstlDt1() != null) {
            bean.setRqstIstlDt(bean.getRqstIstlDt1());
            bean.setRqstIstlTm(bean.getRqstIstlTm1());
        } else if (bean.getRqstIstlDt2() != null) {
            bean.setRqstIstlDt(bean.getRqstIstlDt2());
            bean.setRqstIstlTm(bean.getRqstIstlTm2());
        }

        String actlDelyDt = bean.getActlDelyDt();
        String actlDelyTm = bean.getActlDelyTm();
        String rqstIstlDt = bean.getRqstIstlDt();
        String rqstIstlTm = bean.getRqstIstlTm();

        if (actlDelyDt != null && actlDelyTm != null) {
            actlDely.append(bean.getActlDelyDt());
            actlDely.append(bean.getActlDelyTm());
        }

        if (rqstIstlDt != null && rqstIstlTm != null) {
            rqstIstl.append(rqstIstlDt);
            rqstIstl.append(rqstIstlTm);
        }

        if (actlDely.length() != 0 && rqstIstl.length() != 0) {
            if (actlDely.toString().compareTo(rqstIstl.toString()) >= 0) {
                rtnDtTm = actlDely.toString();
            } else {
                rtnDtTm = rqstIstl.toString();
            }
        } else {
            if (actlDely.length() != 0) {
                rtnDtTm = actlDely.toString();
            } else if (rqstIstl.length() != 0) {
                rtnDtTm = rqstIstl.toString();
            }
        }
        return rtnDtTm;
    }

    // del start 2016/09/30 CSA Defect#14145
//    private String getSvcBillTpCd(BigDecimal svcMachMstrPk) {
//
//        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSXC001001GetContr.getContrDtlByMachMstrPk(this.glblCmpyCd, svcMachMstrPk, this.slsDt);
//        CovTmplInfo covTmplInfo = new CovTmplInfo();
//        if (dsContrDtlTMsg != null) {
//            covTmplInfo.setGlblCmpyCd(this.glblCmpyCd);
//            covTmplInfo.setSvcPgmMdseCd(dsContrDtlTMsg.svcPgmMdseCd.getValue());
//            covTmplInfo.setSlsDt(this.slsDt);
//            NSXC001001GetCovTmpl nsxc001001GetCovTmpl = new NSXC001001GetCovTmpl();
//            nsxc001001GetCovTmpl.getCovTmpl(covTmplInfo);
//        }
//        return setBillTpCd(covTmplInfo);
//    }
    // del end 2016/09/30 CSA Defect#14145

    // del start 2016/09/30 CSA Defect#14145
//    /**
//     * Set Bill Type Code
//     * @param covTmplInfo CovTmplInfo
//     * @return Bill Type Code
//     */
//    private String setBillTpCd(CovTmplInfo covTmplInfo) {
//        String svcBillTpCd = SVC_BILL_TP.FULL_CHARGE;
//        List<OutputCovTmplInfo> outputCovTmplInfoList = covTmplInfo.getOutputCovTmplInfoList();
//        if (outputCovTmplInfoList != null) {
//            for (int i = 0; i < outputCovTmplInfoList.size(); i++) {
//                if (SVC_COV_FEAT.BILL_CD.equals(outputCovTmplInfoList.get(i).getSvcCovFeatCd())) {
//                    svcBillTpCd = outputCovTmplInfoList.get(i).getSvcCovDtlValTxt();
//                    break;
//                }
//            }
//        }
//        return svcBillTpCd;
//    }
    // del end 2016/09/30 CSA Defect#14145

    private boolean setApiErrMsgList(EZDPMsg apiPMsg) {

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                this.apiErrMsgList.add(xxMsgId);
            }
            return false;
        }
        return true;
    }

    // add start 2016/04/21 CSA Defect#5930
    private void addMsgList(NSZC043001PMsg apiPMsg) {
        addMsgList(apiPMsg.serNum.getValue());
    }

    private void addMsgList(NSZC045001PMsg apiPMsg) {
        addMsgList(apiPMsg.serNum.getValue());
    }

    private void addMsgList(String serNum) {
        for (String apiMsgId : this.apiErrMsgList) {
            String errMsg = S21MessageFunc.clspGetMessage(apiMsgId);
            StringBuilder sb = new StringBuilder();
            sb.append("Serial#:");
            sb.append(serNum);
            sb.append(" ");
            sb.append("Error Message:");
            sb.append(errMsg);
            this.msgList.add(sb.toString());
        }
    }
    // add end 2016/04/21 CSA Defect#5930

    private void sendEmail() {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    tmpl.setTemplateParameter(MAIL_ITEM_BATCH_ID, BUSINESS_ID);
                    tmpl.setTemplateParameter(MAIL_ITEM_ERR_DATE, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL));
                    tmpl.setTemplateParameter(MAIL_ITEM_ERR_MSG, msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();
                }
            }
        }
    }

    private InstallCallMachineInfoBean setCpoCtacPsnInfo(InstallCallMachineInfoBean bean) {

        InstallCallMachineInfoBean cpoCtacPsnBean = new InstallCallMachineInfoBean();
        // mod start 2016/10/04 CSA Defect#11896
        Map<String, Object> cpoCtacPsnInfo = getCpoCtacPsnInfo(bean.getCpoOrdNum(), bean.getCpoConfigPk());
        // mod end 2016/10/04 CSA Defect#11896
        if (cpoCtacPsnInfo != null) {
            cpoCtacPsnBean.setCustTelNum((String) cpoCtacPsnInfo.get("CTAC_PSN_TEL_NUM"));
            cpoCtacPsnBean.setCustTelExtnNum((String) cpoCtacPsnInfo.get("CTAC_PSN_EXTN_NUM"));
            cpoCtacPsnBean.setCustEmlAddr((String) cpoCtacPsnInfo.get("CTAC_PSN_EML_ADDR"));
            // START 2016/07/12 [QC#8423, ADD]
            cpoCtacPsnBean.setSvcCustAttnTxt((String) cpoCtacPsnInfo.get("SVC_CUST_ATTN_TXT"));
            cpoCtacPsnBean.setSvcCustCllrTxt((String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TXT"));
            cpoCtacPsnBean.setSvcCustCllrTelNum((String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_NUM"));
            cpoCtacPsnBean.setSvcCustCllrTelExtnNum((String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_EXTN_NUM"));
            // END   2016/07/12 [QC#8423, ADD]
        }
        // START 2016/07/12 [QC#8423, ADD]
        if (!ZYPCommonFunc.hasValue(cpoCtacPsnBean.getCustEmlAddr())) {
            cpoCtacPsnBean.setCustEmlAddr(istlNotExistEmlAddr);
        }
        // END   2016/07/12 [QC#8423, ADD]
        return cpoCtacPsnBean;
    }

    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    // QC#19598 Add Start
    private PreparedStatement getTargetData() throws SQLException {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", this.glblCmpyCd);
//        queryParam.put("shpgStsCdArv", SHPG_STS.ARRIVED);
//        queryParam.put("actlArvDt", this.slsDt);
//        queryParam.put("shpgStsCdShip", SHPG_STS.SHIPPED);
//        queryParam.put("actlShipDt", this.slsDt);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shpgStsCdArv", SHPG_STS.ARRIVED);
        queryParam.put("actlArvDt", this.slsDt);
        queryParam.put("shpgStsCdShip", SHPG_STS.SHIPPED);
        queryParam.put("actlShipDt", this.slsDt);
        queryParam.put("svcMachMstrSts", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        queryParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        queryParam.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        queryParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        List<String> configTpCdList = new ArrayList<String>();
        configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        configTpCdList.add(CONFIG_TP.SERVICE_EXCHANGE);
        queryParam.put("configTpCdList", configTpCdList);
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("ordCatgCtxTp", ISTL_CALL_EXCL_ORD_TP);
        queryParam.put("ordCatgCtxTpSvcExch", SERVICE_EXCHANGE);
        
        return this.ssmLLClient.createPreparedStatement("getTargetData", queryParam, getExecPrm());
    }
    // QC#19598 Add End

    private PreparedStatement getSvcInstallCallMachineInfo() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // mod start 2016/01/14 CSA Defect#3075
        // queryParam.put("shpgStsCd", SHPG_STS.ARRIVED);
        // queryParam.put("actlArvDt", this.slsDt);
        queryParam.put("shpgStsCdArv", SHPG_STS.ARRIVED);
        queryParam.put("actlArvDt", this.slsDt);
        queryParam.put("shpgStsCdShip", SHPG_STS.SHIPPED);
        queryParam.put("actlShipDt", this.slsDt);
        // mod end 2016/01/14 CSA Defect#3075
        queryParam.put("svcMachMstrSts", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        queryParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        // del start 2016/09/30 CSA Defect#14145
        // queryParam.put("autoCallFlg", ZYPConstant.FLG_ON_Y);
        // del end 2016/09/30 CSA Defect#14145
        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        queryParam.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);List<String> configTpCdList = new ArrayList<String>();
        queryParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        configTpCdList.add(CONFIG_TP.SERVICE_EXCHANGE);
        queryParam.put("configTpCdList", configTpCdList);
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("ordCatgCtxTp", ISTL_CALL_EXCL_ORD_TP);
        // add start 2016/09/30 CSA Defect#14145
        queryParam.put("ordCatgCtxTpSvcExch", SERVICE_EXCHANGE);
        // add end 2016/09/30 CSA Defect#14145
        return this.ssmLLClient.createPreparedStatement("getSvcInstallCallMachineInfo", queryParam, getExecPrm());
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsSvcCallTpInfo(String svcIstlCallGrpNum) {
        if (svcIstlCallGrpNum == null) {
            return (new ArrayList<Map<String, Object>>());
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcIstlCallGrpNum", svcIstlCallGrpNum);
        queryParam.put("svcIstlCallGrpFlg", ZYPConstant.FLG_ON_Y);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsSvcCallTpInfo", queryParam);
    }

    // mod start 2016/10/04 CSA Defect#11896
    private Map<String, Object> getCpoCtacPsnInfo(String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        // mod start 2016/11/15 CSA Defect#15957
        if (!ZYPCommonFunc.hasValue(this.ctacPsnTpCd)) {
            return null;
        }
        // mod end 2016/11/15 CSA Defect#15957
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);
        // mod start 2016/11/15 CSA Defect#15957
        queryParam.put("ctacPsnTpCd", this.ctacPsnTpCd);
        // mod end 2016/11/15 CSA Defect#15957
        // START 2019/08/23 K.Kitachi [QC#52626, ADD]
        queryParam.put("len60", SUBSTRING_LEN60);
        // END 2019/08/23 K.Kitachi [QC#52626, ADD]
        return (Map<String, Object>) ssmBatchClient.queryObject("getCpoCtacPsnInfo", queryParam);
    }
    // mod end 2016/10/04 CSA Defect#11896

    // add start 2016/09/30 CSA Defect#14145
    private boolean isCreateSvcExchCall(InstallCallMachineInfoBean bean) {
        if (ZYPConstant.FLG_OFF_N.equals(bean.getSvcExchFlg())) {
            return true;
        }

        if (!setMdlForSvcExch(bean)) {
            return false;
        }

        if (ZYPCommonFunc.hasValue(bean.getDsCpoIstlInfoPk1()) || ZYPCommonFunc.hasValue(bean.getDsCpoIstlInfoPk2())) {
            return true;
        }

        if (!isAutoIstlCallForMdl(bean.getSvcExchMdlId())) {
            return false;
        }
        return true;
    }

    private boolean setMdlForSvcExch(InstallCallMachineInfoBean bean) {
        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, bean.getPreSvcConfigMstrPk());
        inMsg = (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            String errMsg = S21MessageFunc.clspGetMessage(NSBM0025E, new String[] {"Configuration", "Config Master"});
            StringBuilder sb = new StringBuilder();
            sb.append("Serial#:");
            sb.append(bean.getSerNum());
            sb.append(" ");
            sb.append("Error Message:");
            sb.append(errMsg);
            this.msgList.add(sb.toString());
            this.errorCont++;
            return false;
        }
        bean.setSvcExchMdlId(inMsg.mdlId.getValue());
        bean.setSvcExchMdlNm(getMdlNm(inMsg.mdlId.getValue()));
        // START 2023/09/05 K.Watanabe [QC#53408, MOD]
        //bean.setSvcIstlCalGrpNum(getSvcIstlCalGrpNum(inMsg.mdlId.getValue()));
        if (ZYPConstant.FLG_OFF_N.equals(bean.getAddAsryFlg())) {
            bean.setSvcIstlCalGrpNum(getSvcIstlCalGrpNum(inMsg.mdlId.getValue()));
        }
        // END 2023/09/05 K.Watanabe [QC#53408, MOD]
        return true;
    }

    private String getMdlNm(BigDecimal mdlId) {
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return null;
        }
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.t_GlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.t_MdlId, mdlId);
        MDL_NMTMsg outMsg = (MDL_NMTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return null;
        }
        return outMsg.t_MdlNm.getValue();
    }

    private String getSvcIstlCalGrpNum(BigDecimal mdlId) {
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return null;
        }
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdlId, mdlId);
        DS_MDLTMsg outMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return null;
        }
        return outMsg.svcIstlCallGrpNum.getValue();
    }

    private boolean isAutoIstlCallForMdl(BigDecimal mdlId) {
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return false;
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdlId", mdlId);
        BigDecimal cnt = (BigDecimal) ssmBatchClient.queryObject("cntAutoIstlCallForMdl", queryParam);
        if (BigDecimal.ZERO.compareTo(cnt) < 0) {
            return true;
        }
        return false;
    }
    // add end 2016/09/30 CSA Defect#14145
    // add start 2016/10/04 CSA Defect#11896
    private boolean createRmvCallForSvcExch(InstallCallMachineInfoBean bean) {
        if (ZYPConstant.FLG_OFF_N.equals(bean.getSvcExchFlg())) {
            return true;
        }

        // START 2016/11/22 T.Tomita [QC#16124, MOD]
        Map<String, Object> rmvCallData = getRmvCallForSvcExch(bean.getCpoOrdNum(), bean.getPreSvcConfigMstrPk());
        // END 2016/11/22 T.Tomita [QC#16124, MOD]
        if (rmvCallData == null) {
            return true;
        }

        // Mod Start 2018/03/09 QC#23424
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        if (!callFsrUpdApiForSvcExchRmvCall(apiPMsg, rmvCallData, bean)) {
            return false;
        }

        // Status Update TBO
        // mod start 2018/08/07 QC#27566
        //if (!isOutTrtySvcBr(bean.getSvcMachMstrPk())) {
        // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//        if (!isThirdPartyCall(bean.getSvcMachMstrPk(), apiPMsg.fsrNum.getValue(), apiPMsg.fsrVisitNum.getValue())) {
        if (!isThirdPartyCall(bean.getSvcMachMstrPk(), apiPMsg.fsrNum.getValue(), apiPMsg.fsrVisitNum.getValue(), apiPMsg.dsSvcCallTpCd.getValue())) {
        // END 2019/02/15 K.Kitachi [QC#30165, MOD]
        // mod end 2018/08/07 QC#27566
            apiPMsg = updateStatusByTBO(apiPMsg);
            if (this.apiErrMsgList.size() > 0) {
                addMsgList(apiPMsg);
                return false;
            }
        }
        // Mod End 2018/03/09 QC#23424
        return true;
    }

    // mod start 2016/12/14 CSA Defect#16490
    // Mod Start 2018/03/09 QC#23424
    private boolean callFsrUpdApiForSvcExchRmvCall(NSZC043001PMsg apiPMsg, Map<String, Object> rmvCallData, InstallCallMachineInfoBean bean) {
    // Mod End 2018/03/09 QC#23424
        // START 2016/11/22 T.Tomita [QC#16124, ADD]
        this.apiErrMsgList.clear();
        // END 2016/11/22 T.Tomita [QC#16124, ADD]

        Map<String, Object> cpoCtacPsnInfo = getCpoCtacPsnInfo((String) rmvCallData.get("CPO_ORD_NUM"), (BigDecimal) rmvCallData.get("DS_CPO_CONFIG_PK"));
        if (cpoCtacPsnInfo == null) {
            cpoCtacPsnInfo = new HashMap<String, Object>();
        }

        // NSZC0430 FSR Update API
        // Del Start 2018/03/09 QC#23424
//        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        // Del End 2018/03/09 QC#23424
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_CREATE_FSR);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, (String) rmvCallData.get("BYPS_PRF_TECH_FLG"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.soNum, bean.getSoNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstProdCtrlCd, (String) rmvCallData.get("FIRST_PROD_CTRL_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, (BigDecimal) rmvCallData.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, (String) rmvCallData.get("CUST_MACH_CTRL_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, (String) rmvCallData.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, (String) cpoCtacPsnInfo.get("CTAC_PSN_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, (String) cpoCtacPsnInfo.get("CTAC_PSN_EXTN_NUM"));
        // mod start 2016/11/14 CSA Defect#15957
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, (String) cpoCtacPsnInfo.get("CTAC_PSN_EML_ADDR"));
        if (!ZYPCommonFunc.hasValue(apiPMsg.custEmlAddr)) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, this.istlNotExistEmlAddr);
        }
        // mod end 2016/11/14 CSA Defect#15957
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, (String) cpoCtacPsnInfo.get("SVC_CUST_ATTN_TXT"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, (String) rmvCallData.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, (String) rmvCallData.get("CUST_ISS_PO_DT"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, (String) rmvCallData.get("DS_SVC_CALL_TP_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        String sysDateTime = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, sysDateTime.substring(0, SUBSTRING_LEN8));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, sysDateTime.substring(SUBSTRING_LEN8, SUBSTRING_LEN14));
        // add start 2016/12/14 CSA Defect#16490
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallIncdtDt, sysDateTime.substring(0, SUBSTRING_LEN8));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallIncdtTm, sysDateTime.substring(SUBSTRING_LEN8, SUBSTRING_LEN14));
        // add end 2016/12/14 CSA Defect#16490
        apiPMsg.svcTaskSchdDt.clear();
        apiPMsg.svcTaskSchdTm.clear();
        apiPMsg.svcTaskSchdByUsrId.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, (String) rmvCallData.get("PRT_CHRG_FLG"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, (String) rmvCallData.get("LBOR_CHRG_FLG"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, (String) rmvCallData.get("PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcPblmTpCd, "9000");
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.S21_SYSTEM);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, (String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TXT"));
        // START 2022/07/15 K.Kitachi [QC#60311, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2022/07/15 K.Kitachi [QC#60311, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCallPrtyCd, (String) rmvCallData.get("SVC_CALL_PRTY_CD"));
        apiPMsg.taskList.no(0).techCd.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        apiPMsg.taskList.no(0).techSchdFromDt.clear();
        apiPMsg.taskList.no(0).techSchdFromTm.clear();
        apiPMsg.taskList.no(0).erlStartTs.clear();
        apiPMsg.taskList.no(0).lateStartTs.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // mod start 2016/12/14 CSA Defect#16490
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
        // mod end 2016/12/14 CSA Defect#16490
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelNum, (String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelExtnNum, (String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_EXTN_NUM"));
        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (!setApiErrMsgList(apiPMsg)) {
            // START 2016/11/22 T.Tomita [QC#16124, ADD]
            addMsgList(apiPMsg);
            // END 2016/11/22 T.Tomita [QC#16124, ADD]
            return false;
        }

        return true;
    }
    // mod end 2016/12/14 CSA Defect#16490

    // START 2016/11/22 T.Tomita [QC#16124, MOD]
    private Map<String, Object> getRmvCallForSvcExch(String cpoOrdNum, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("configCatgCdIn", CONFIG_CATG.INBOUND);
        queryParam.put("configCatgCdOut", CONFIG_CATG.OUTBOUND);
        Map<String, Object> reslt = (Map<String, Object>) ssmBatchClient.queryObject("getRmvCallForSvcExch", queryParam);
        return reslt;
    }
    // END 2016/11/22 T.Tomita [QC#16124, MOD]

    private void createRmvCallForStandAlone() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getRmvCallForStandAlone();
            rs = ps.executeQuery();
            while (rs.next()) {
                this.totalCount++;
                if (!callFsrUpdApiForStandAloneRmvCall(rs)) {
                    this.errorCont++;
                    rollback();
                    continue;
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private PreparedStatement getRmvCallForStandAlone() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("slsDt", this.slsDt);
        queryParam.put("configCatgCdIn", CONFIG_CATG.INBOUND);
        queryParam.put("configCatgCdOut", CONFIG_CATG.OUTBOUND);
        queryParam.put("rtrnLineStsCd", RTRN_LINE_STS.BOOKED);
        String[] dsSvcCallTpCdList = new String[] {DS_SVC_CALL_TP.HARD_DRIVE_REMOVAL_INSTALL, DS_SVC_CALL_TP.HARD_DRIVE_REMOVAL_STANDALONE };
        queryParam.put("dsSvcCallTpCdList", dsSvcCallTpCdList);
        String[] svcTaskStsCdList = new String[] {SVC_TASK_STS.COMPLETED, SVC_TASK_STS.PENDING_CHARGE, SVC_TASK_STS.CLOSED, SVC_TASK_STS.CANCELLED};
        queryParam.put("svcTaskStsCdList", svcTaskStsCdList);

        return this.ssmLLClient.createPreparedStatement("getRmvCallForStandAlone", queryParam, getExecPrm());
    }

    // Mod Start 2018/03/09 QC#23424
    private boolean callFsrUpdApiForStandAloneRmvCall(ResultSet rs) throws SQLException {
        this.apiErrMsgList.clear();

        // NSZC0430 FSR Update API
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        setFsrUpdateApiForStandAloneRmvCall(apiPMsg, rs);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (!setApiErrMsgList(apiPMsg)) {
            addMsgList(apiPMsg);
            return false;
        }

        // Status Update TBO
        // mod start 2018/08/07 QC#27566
        //if (!isOutTrtySvcBr(apiPMsg.svcMachMstrPk.getValue())) {
        // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//        if (!isThirdPartyCall(apiPMsg.svcMachMstrPk.getValue(), apiPMsg.fsrNum.getValue(), apiPMsg.fsrVisitNum.getValue())) {
        if (!isThirdPartyCall(apiPMsg.svcMachMstrPk.getValue(), apiPMsg.fsrNum.getValue(), apiPMsg.fsrVisitNum.getValue(), apiPMsg.dsSvcCallTpCd.getValue())) {
        // END 2019/02/15 K.Kitachi [QC#30165, MOD]
        // mod end 2018/08/07 QC#27566
            apiPMsg = updateStatusByTBO(apiPMsg);
            if (this.apiErrMsgList.size() > 0) {
                addMsgList(apiPMsg);
                return false;
            }
        }

        return true;
    }
    // Mod End 2018/03/09 QC#23424
    // add end 2016/10/04 CSA Defect#
    // Add Start 2018/03/09 QC#23424
    private void setFsrUpdateApiForStandAloneRmvCall(NSZC043001PMsg apiPMsg, ResultSet rs) throws SQLException {
        String cpoOrdNum = rs.getString("CPO_ORD_NUM");
        BigDecimal dsCpoConfigPk = rs.getBigDecimal("DS_CPO_CONFIG_PK");

        Map<String, Object> cpoCtacPsnInfo = getCpoCtacPsnInfo(cpoOrdNum, dsCpoConfigPk);
        if (cpoCtacPsnInfo == null) {
            cpoCtacPsnInfo = new HashMap<String, Object>();
        }

        // NSZC0430 FSR Update API
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_CREATE_FSR);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, rs.getString("BYPS_PRF_TECH_FLG"));
        apiPMsg.soNum.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstProdCtrlCd, rs.getString("FIRST_PROD_CTRL_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, rs.getString("CUST_MACH_CTRL_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, (String) cpoCtacPsnInfo.get("CTAC_PSN_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, (String) cpoCtacPsnInfo.get("CTAC_PSN_EXTN_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, (String) cpoCtacPsnInfo.get("CTAC_PSN_EML_ADDR"));
        if (!ZYPCommonFunc.hasValue(apiPMsg.custEmlAddr)) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, this.istlNotExistEmlAddr);
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, (String) cpoCtacPsnInfo.get("SVC_CUST_ATTN_TXT"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, rs.getString("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, rs.getString("CUST_ISS_PO_DT"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, rs.getString("DS_SVC_CALL_TP_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        String sysDateTime = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, sysDateTime.substring(0, SUBSTRING_LEN8));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, sysDateTime.substring(SUBSTRING_LEN8, SUBSTRING_LEN14));
        apiPMsg.svcTaskSchdDt.clear();
        apiPMsg.svcTaskSchdTm.clear();
        apiPMsg.svcTaskSchdByUsrId.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, rs.getString("PRT_CHRG_FLG"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, rs.getString("LBOR_CHRG_FLG"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, rs.getString("PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcPblmTpCd, "9000");
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.S21_SYSTEM);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, (String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TXT"));
        // START 2022/07/15 K.Kitachi [QC#60311, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2022/07/15 K.Kitachi [QC#60311, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCallPrtyCd, rs.getString("SVC_CALL_PRTY_CD"));
        apiPMsg.taskList.no(0).techCd.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        apiPMsg.taskList.no(0).techSchdFromDt.clear();
        apiPMsg.taskList.no(0).techSchdFromTm.clear();
        apiPMsg.taskList.no(0).erlStartTs.clear();
        apiPMsg.taskList.no(0).lateStartTs.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelNum, (String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelExtnNum, (String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_EXTN_NUM"));
        apiPMsg.taskList.setValidCount(1);
    }
    // Add End 2018/03/09 QC#23424

    // START 2017/07/04 K.Kitachi [QC#19698, ADD]
    private void updateFsrVisitTaskSts() {

        this.apiErrMsgList.clear();

        List<Map<String, Object>> fsrTaskMapList = getFsrTaskList();
        if (fsrTaskMapList == null || fsrTaskMapList.isEmpty()) {
            return;
        }

        for (Map<String, Object> fsrTaskMap : fsrTaskMapList) {
            // START 2017/07/19 K.Kojima [QC#19987,ADD]
            String erlStartTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
            erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                    this.glblCmpyCd
                  , BigDecimal.ZERO
                  , (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK")
                  , erlStartTs.substring(0, 8)
                  , erlStartTs.substring(8, 14)
                  , false
            );
            // END 2017/07/19 K.Kojima [QC#19987,ADD]

            NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, (String) fsrTaskMap.get("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, this.profileService.getContextUserInfo().getUserId());
            // mod start 2018/08/07 QC#27566
            //ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
            // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//            if (isThirdPartyCall((BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"), (String) fsrTaskMap.get("FSR_NUM"), (String) fsrTaskMap.get("FSR_VISIT_NUM"))) {
            if (isThirdPartyCall((BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"), (String) fsrTaskMap.get("FSR_NUM"), (String) fsrTaskMap.get("FSR_VISIT_NUM"), (String) fsrTaskMap.get("DS_SVC_CALL_TP_CD"))) {
            // END 2019/02/15 K.Kitachi [QC#30165, MOD]
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
            } else {
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
                // Add Start 2018/08/08 QC#27640
                ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
                // Add End 2018/08/08 QC#27640
            }
            // mod end 2018/08/07 QC#27566
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, (BigDecimal) fsrTaskMap.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.custMachCtrlNum, (String) fsrTaskMap.get("CUST_MACH_CTRL_NUM"));
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.serNum, (String) fsrTaskMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, (String) fsrTaskMap.get("SVC_TASK_NUM"));
            // START 2017/07/19 K.Kojima [QC#19987,MOD]
            // ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, (String) fsrTaskMap.get("ACTL_DELY_TS"));
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, erlStartTs);
            // END 2017/07/19 K.Kojima [QC#19987,MOD]
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).techCd, (String) fsrTaskMap.get("ISTL_TECH_CD"));
            ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
            fsrUpdPMsg.taskList.setValidCount(1);

            new NSZC043001().execute(fsrUpdPMsg, ONBATCH_TYPE.BATCH);
            if (!setApiErrMsgList(fsrUpdPMsg)) {
                addMsgList(fsrUpdPMsg);
                this.errorCont++;
            }
            this.totalCount++;
        }
    }

    private List<Map<String, Object>> getFsrTaskList() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcTaskStsPendIstl", SVC_TASK_STS.PENDING_INSTALL);
        queryParam.put("shpgStsArv", SHPG_STS.ARRIVED);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getFsrTaskList", queryParam);
    }
    // END 2017/07/04 K.Kitachi [QC#19698, ADD]

    // START 2017/09/04 U.Kim [QC#20903,ADD]
    private String substringPoNum(String custPoNum) {
        // START 2019/01/18 [QC#29081, MOD]
        // if (custPoNum == null) {
        //     return null;
        // }
        // if (custPoNum.length() <= 25) {
        //     return custPoNum;
        // }
        // return custPoNum.substring(0, 25);
        return custPoNum;
        // END   2019/01/18 [QC#29081, MOD]
    }
    // END 2017/09/04 U.Kim [QC#20903,ADD]

    // START 2018/01/10 M.Naito [QC#18889, ADD]
    private String getMeetTruckErlStrtTs(InstallCallMachineInfoBean bean, BigDecimal rspTmMnAot) {
        String erlStartDt = null;
        String erlStartTm = null;

        if (ZYPCommonFunc.hasValue(bean.getSchdDelyDt()) && ZYPConstant.FLG_ON_Y.equals(bean.getTechMeetTruckFlg())) {
            erlStartDt = bean.getSchdDelyDt();
        } else {
            String systemTime = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);
            // Get Constant Value of Early Start Date
            BigDecimal meetTruckErlStrtDays = getMeetTruckErlStrtDays(bean.getSvcByLineBizTpCd());
            erlStartDt = ZYPDateUtil.addDays(systemTime, meetTruckErlStrtDays.intValue());
        }
        if (ZYPCommonFunc.hasValue(bean.getSchdDelyTm()) && ZYPConstant.FLG_ON_Y.equals(bean.getTechMeetTruckFlg())) {
            erlStartTm = bean.getSchdDelyTm();
        } else {
            // Get Constant Value of Early Start Time
            erlStartTm = getMeetTruckErlStrtTm(bean.getSvcByLineBizTpCd());
            // START 2018/01/19 K.Ochiai [QC#18889, ADD]
            String systemTimeZoneTs = erlStartDt + erlStartTm;
            SvcTimeZoneInfo convertErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, systemTimeZoneTs, (String) bean.getCtryCd(), (String) bean.getPostCd());
            if (convertErlStartTs != null) {
                erlStartDt = convertErlStartTs.getDateTime().substring(0, 8);
                erlStartTm = convertErlStartTs.getDateTime().substring(8, 12);
            }
            // END 2018/01/19 K.Ochiai [QC#18889, ADD]
        }
        return NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(this.glblCmpyCd, rspTmMnAot, (BigDecimal) bean.getSvcMachMstrPk(), erlStartDt, convertTimeForHHmmss(erlStartTm), false);
    }

    private BigDecimal getMeetTruckErlStrtDays(String svcByLineBizTp) {
        BigDecimal meetTruckErlStrtDays = null;

        if (LINE_BIZ_TP.LFS.equals(svcByLineBizTp)) {
            meetTruckErlStrtDays = meetTruckErlStrtDaysLfs;
        } else if (LINE_BIZ_TP.PPS.equals(svcByLineBizTp)) {
            meetTruckErlStrtDays = meetTruckErlStrtDaysPps;
        } else {
            meetTruckErlStrtDays = meetTruckErlStrtDaysEss;
        }
        return meetTruckErlStrtDays;
    }

    private String getMeetTruckErlStrtTm(String svcByLineBizTp) {
        String meetTruckErlStrtTm = null;

        if (LINE_BIZ_TP.LFS.equals(svcByLineBizTp)) {
            meetTruckErlStrtTm = meetTruckErlStrtTmLfs;
        } else if (LINE_BIZ_TP.PPS.equals(svcByLineBizTp)) {
            meetTruckErlStrtTm = meetTruckErlStrtTmPps;
        } else {
            meetTruckErlStrtTm = meetTruckErlStrtTmEss;
        }
        return meetTruckErlStrtTm;
    }

    private String convertTimeForHHmmss(String time) {
        if (!ZYPCommonFunc.hasValue(time) || time.length() != HOUR_MINUTE_LEN) {
            time = DEF_HHMMSS;
        } else {
            time = time + "00";
        }
        return time;
    }

    private SVC_TASKTMsg getSvcTask(String fsrNum) {
        if (!ZYPCommonFunc.hasValue(fsrNum)) {
            return null;
        }
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        SVC_TASKTMsgArray list = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (list == null || list.getValidCount() != 1) {
            return null;
        }
        return list.no(0);
    }
    // END 2018/01/10 M.Naito [QC#18889, ADD]

    // del start 2018/08/07 QC#27566
    // START 2018/02/19 M.Naito [QC#24203, ADD]
//    private boolean isOutTrtySvcBr(BigDecimal svcMachMstrPk) {
//        if (!ZYPCommonFunc.hasValue(svcMachMstrPk) || !ZYPCommonFunc.hasValue(this.outTrtySvcBrCd)) {
//            return false;
//        }
//
//        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
//        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
//        SVC_MACH_MSTRTMsg machMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
//        if (machMstrTMsg == null || !ZYPCommonFunc.hasValue(machMstrTMsg.fldSvcBrCd)) {
//            return false;
//        }
//
//        if (this.outTrtySvcBrCd.equals(machMstrTMsg.fldSvcBrCd.getValue())) {
//            return true;
//        }
//        return false;
//    }
    // END 2018/02/19 M.Naito [QC#24203, ADD]
    // del end 2018/08/07 QC#27566

    // Add Start 2018/03/12 QC#23424
    private NSZC043001PMsg updateStatusByTBO(NSZC043001PMsg pMsg) {

        // NSZC0430 FSR Update API
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskStsCd, SVC_TASK_STS.TBO);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        // START 2022/07/15 K.Kitachi [QC#60311, ADD]
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2022/07/15 K.Kitachi [QC#60311, ADD]

        // Service Task List
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);

        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if(!setApiErrMsgList(apiPMsg)) {
            return apiPMsg;
        }

        if (ZYPCommonFunc.hasValue(pMsg.soNum)) {
            Map<String, Object> startTsMap = getStartTsForSvcExchng(pMsg.soNum.getValue(), pMsg.taskList.no(0).svcTaskNum.getValue());
            if (startTsMap == null) {
                return apiPMsg;
            }

            SVC_TASKTMsg tMsg = getSvcTaskByKey(pMsg.taskList.no(0).svcTaskNum.getValue());
            if (tMsg == null) {
                return apiPMsg;
            }

            // Update ERL_START_TS and LATE_START_TS
            ZYPEZDItemValueSetter.setValue(tMsg.erlStartTs, (String) startTsMap.get("ERL_START_TS"));
            ZYPEZDItemValueSetter.setValue(tMsg.lateStartTs, (String) startTsMap.get("LATE_START_TS"));
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK, tMsg.svcTaskNum.getValue() });
            }
        }

        return apiPMsg;
    }

    private Map<String, Object> getStartTsForSvcExchng(String soNum, String svcTaskNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("svcTaskNum", svcTaskNum);
        queryParam.put("fsrVisitStsCd", SVC_TASK_STS.TBO);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getStartTsForSvcExchng", queryParam);
        if (resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    private SVC_TASKTMsg getSvcTaskByKey(String svcTaskNum) {
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            return null;
        }

        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // Add End 2018/03/12 QC#23424

    // add start 2018/08/07 QC#27566
    // START 2019/02/15 K.Kitachi [QC#30165, MOD]
//    private boolean isThirdPartyCall(BigDecimal svcMachMstrPk, String fsrNum, String fsrVisitNum) {
    private boolean isThirdPartyCall(BigDecimal svcMachMstrPk, String fsrNum, String fsrVisitNum, String dsSvcCallTpCd) {
    // END 2019/02/15 K.Kitachi [QC#30165, MOD]

        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        if (isClickSendExclCall(dsSvcCallTpCd)) {
            return true;
        }
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        if (ZYPCommonFunc.hasValue(svcMachMstrPk) && ZYPCommonFunc.hasValue(this.outTrtySvcBrCd)) {
            SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
            tMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg != null && this.outTrtySvcBrCd.equals(tMsg.fldSvcBrCd.getValue())) {
                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(fsrNum) && ZYPCommonFunc.hasValue(fsrVisitNum)) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("fsrNum", fsrNum);
            queryParam.put("fsrVisitNum", fsrVisitNum);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getTechInfo", queryParam);
            if (result != null && ZYPCommonFunc.hasValue((String) result.get("PSN_TP_CD")) && !PSN_TP.EMPLOYEE.equals((String) result.get("PSN_TP_CD"))) {
                return true;
            }
        }
        return false;
    }
    // add end 2018/08/07 QC#27566

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
