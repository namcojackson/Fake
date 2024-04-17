/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB103001;

import static com.canon.cusa.s21.batch.NSB.NSBB103001.constant.NSBB103001Constant.*;

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
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.SVC_DEINS_CALL_MACH_INFOTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
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
 * NSBB103001
 * Deinstall Call Creation Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/12/13   Hitachi         S.Naya          Create          QC#61300
 * </pre>
 */
public class NSBB103001 extends S21BatchMain {

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
    
    /** System TimeStamp */
    private String sysTs = null;
    
    /** bizProcLogPk. */
    private BigDecimal dsBizProcLogPk = null;

    /** dsBizLastUpdTs. */
    private String dsBizLastUpdTs = null;

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

    /** customer email address (not exists) */
    private String istlNotExistEmlAddr = null;

    /** VAR_CHAR_CONST : ctacPsnTpCd */
    private String ctacPsnTpCd = null;

    /** Out of Territory Service Branch Code */
    private String outTrtySvcBrCd = null;

    private static final int svcCustAttnTxtLen = new SVC_TASKTMsg().getAttr("svcCustAttnTxt").getDigit();
    private static final int svcCustCllrTxtLen = new SVC_TASKTMsg().getAttr("svcCustCllrTxt").getDigit();

    /** Click send exclusion call type list */
    private String[] clickSendExclCallTpList = null;

    /** Accessory Call Override call type list */
    private String[] accCallOvrdCallTpList = null;

    /** Service Bill Type Code */
    private String svcBillTpCd = null;

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NASM0010E);
        }

        // "Sales Date"
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // "System TimeStamp"
        this.sysTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);

        // SVC_ISTL_NOT_EXIST_EML_ADDR
        this.istlNotExistEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_ISTL_NOT_EXIST_EML_ADDR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.istlNotExistEmlAddr)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_ISTL_NOT_EXIST_EML_ADDR });
        }

        this.ctacPsnTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NSBB1030_CTAC_PSN_TP_CD, this.glblCmpyCd);

        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD,this.glblCmpyCd);

        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, this.glblCmpyCd);
        this.clickSendExclCallTpList = clickSendExclCallTp.split(STR_COMMA);

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profileService = getUserProfileService();

        String accCallOvrdCallTp = ZYPCodeDataUtil.getVarCharConstValue(ACC_CALL_OVRD_CALL_TP, this.glblCmpyCd);
        this.accCallOvrdCallTpList = accCallOvrdCallTp.split(STR_COMMA);

        DS_SVC_CALL_TPTMsg dsSvcCallTp = getDsSvcCallTp(DS_SVC_CALL_TP_CD_ACC_INSTALL, this.glblCmpyCd);
        this.svcBillTpCd = dsSvcCallTp.svcBillTpCd.getValue();
    }

    @Override
    protected void mainRoutine() {
        // Get LastUpdateTime from DS_BIZ_PROC_LOG.
        getLastUpdateTs();
        // Create SVC_DEINS_CALL_MACH_INFO
        regSvcDeinsCallMachInfo();
        // Regist LastUpdateTime to DS_BIZ_PROC_LOG.
        updateDsBizProcLog();

        // Create De-Install Call from SVC_DEINS_CALL_MACH_INFO.
        doProcess();
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB103001().executeBatch(NSBB103001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {

        sendEmail();
        if (this.errorCont > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCont, this.errorCont, this.totalCount);
    }

    /**
     * getLastUpdateTs
     */
    private void getLastUpdateTs() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("procPgmId", PROGRAM_ID);

        List<Map<String, Object>> lastUpdateTsList = ssmBatchClient.queryObjectList("getLastUpdateTs", ssmParam);

        if (lastUpdateTsList == null || lastUpdateTsList.isEmpty()) {
            this.dsBizProcLogPk = null;
            this.dsBizLastUpdTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        } else {
            Map<String, Object> lastUpdateTsMap = lastUpdateTsList.get(0);
            this.dsBizProcLogPk = (BigDecimal) lastUpdateTsMap.get("DS_BIZ_PROC_LOG_PK");
            this.dsBizLastUpdTs = (String) lastUpdateTsMap.get("DS_BIZ_LAST_UPD_TS");
        }

    }

    /**
     * updateDsBizProcLog
     */
    private void updateDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg outMsg = new DS_BIZ_PROC_LOGTMsg();

        if (!ZYPCommonFunc.hasValue(this.dsBizProcLogPk)) {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, PROGRAM_ID);
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, this.sysTs);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_BIZ_PROC_LOG_SQ"));

            EZDTBLAccessor.insert(outMsg);
        } else {
            
            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, dsBizProcLogPk);
            outMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, PROGRAM_ID);
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, this.sysTs);

            EZDTBLAccessor.update(outMsg);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(outMsg.getTableName());
            rollback();
            termCd = TERM_CD.ABNORMAL_END;

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {sb.toString() }));
        } else {
            commit();
        }

    }

    private void regSvcDeinsCallMachInfo() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Get Target Data.
            ps = getTargetData();
            rs = ps.executeQuery();

            while (rs.next()) {
                // Insert SVC_DEINS_CALL_MACH_INFO.
                insertSvcDeinsCallMachInfo(rs);
            }
            commit();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void insertSvcDeinsCallMachInfo(ResultSet rs) throws SQLException {

        SVC_DEINS_CALL_MACH_INFOTMsg inParam = new SVC_DEINS_CALL_MACH_INFOTMsg();
        String rmaNum = null ;
        BigDecimal svcMachMstrPk = null ;

        rmaNum = rs.getString("RMA_NUM") ;
        svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK") ;
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.rmaNum, rmaNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, svcMachMstrPk);

        EZDTBLAccessor.insert(inParam);
    }
    
    private void removeSvcDeinsCallMachInfo(ResultSet rs) throws SQLException {

        SVC_DEINS_CALL_MACH_INFOTMsg inParam = new SVC_DEINS_CALL_MACH_INFOTMsg();
        String rmaNum = null ;
        BigDecimal svcMachMstrPk = null ;

        rmaNum = rs.getString("CPO_CTAC_ORD_NUM") ;
        svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK") ;
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.rmaNum, rmaNum);
        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, svcMachMstrPk);
        inParam = (SVC_DEINS_CALL_MACH_INFOTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inParam);

        S21ApiTBLAccessor.remove(inParam);
    }

    private DS_SVC_CALL_TPTMsg getDsSvcCallTp(String dsSvcCallTpCd ,String glblCmpyCd) {

        DS_SVC_CALL_TPTMsg inParam = new DS_SVC_CALL_TPTMsg();

        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.dsSvcCallTpCd, dsSvcCallTpCd);

        return (DS_SVC_CALL_TPTMsg) EZDTBLAccessor.findByKey(inParam);
    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Get Target Data from SVC_DEINS_CALL_MACH_INFO.
            ps = getSvcDeinstallCallMachineInfo();
            rs = ps.executeQuery();

            boolean errFlg;
            while (rs.next()) {

                DeinstallCallMachineInfoBean bean = setInstallCallMachineInfoBean(rs);
                DeinstallCallMachineInfoBean cpoCtacPsnbean = setCpoCtacPsnInfo(bean);
                List<Map<String, Object>> dsSvcCallTpInfoList = getDsSvcCallTpInfo(bean.getSvcDeinsCalGrpNum());

                NSZC043001PMsg pMsg = new NSZC043001PMsg();
                NSZC045001PMsg addTaskPMsg = new NSZC045001PMsg();
                errFlg = false;
                this.totalCount++;
                for (int index = 0; index < dsSvcCallTpInfoList.size(); index++) {
                    dsSvcCallTpInfoMap(bean, dsSvcCallTpInfoList.get(index));
                    String dsSvcCallTpCd;

                    // NSZC0430 FSR Update API(FSR Create Mode)
                    pMsg = callFsrUpdateApi(bean, cpoCtacPsnbean);
                    if (this.apiErrMsgList.size() > 0) {
                        addMsgList(pMsg);
                        this.apiErrMsgList.clear();
                        rollback();
                        errFlg = true;
                        continue;
                    }
                    dsSvcCallTpCd = pMsg.dsSvcCallTpCd.getValue();
                    
                    // NSZC0430 FSR Update API(FSR Update Mode)
                    pMsg = callFsrUpdateApiForUpdateFsrMode(bean, pMsg, addTaskPMsg, index, dsSvcCallTpCd);
                    if (this.apiErrMsgList.size() > 0) {
                        addMsgList(pMsg);
                        this.apiErrMsgList.clear();
                        rollback();
                        errFlg = true;
                        continue;
                    }
                }
                if (errFlg) {
                    this.errorCont++;
                    continue;
                }
                // Remove SVC_DEINS_CALL_MACH_INFO.
                removeSvcDeinsCallMachInfo(rs);

                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private DeinstallCallMachineInfoBean setInstallCallMachineInfoBean(ResultSet rs) throws SQLException {

        DeinstallCallMachineInfoBean bean = new DeinstallCallMachineInfoBean();
        bean.setRmaNum(rs.getString("RMA_NUM"));
        bean.setCpoGlblCmpyCd(rs.getString("CPO_CTAC_GLBL_CMPY_CD"));
        bean.setCpoOrdNum(rs.getString("CPO_CTAC_ORD_NUM"));
        bean.setCpoConfigPk(rs.getBigDecimal("CPO_CTAC_CONFIG_PK"));
        bean.setCustPoNum(rs.getString("CUST_ISS_PO_NUM"));
        bean.setCustPoDt(rs.getString("CUST_PO_DT"));
        bean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        bean.setCustMachCtrlNum(rs.getString("CUST_MACH_CTRL_NUM"));
        bean.setSerNum(rs.getString("SER_NUM"));
        bean.setBillToAcctNum(rs.getString("BILL_TO_ACCT_NUM"));
        bean.setSvcDeinsCalGrpNum(rs.getString("SVC_DEINS_CALL_GRP_NUM"));
        bean.setSvcCustAttnTxt1(rs.getString("ISTL_ADDL_CMNT_TXT1"));
        bean.setTechCd1(rs.getString("ISTL_TECH_CD1"));
        bean.setDciiEzUpUserId1(rs.getString("EZUPUSERID1"));
        bean.setSvcCustAttnTxt2(rs.getString("ISTL_ADDL_CMNT_TXT2"));
        bean.setTechCd2(rs.getString("ISTL_TECH_CD2"));
        bean.setDciiEzUpUserId2(rs.getString("EZUPUSERID2"));
        bean.setDcrdEzUpUserId(rs.getString("DCRD_EZUPUSERID"));
        bean.setMachMdseCd(rs.getString("MACH_MDSE_CD"));
        bean.setAddAsryFlg(rs.getString("ADD_ASRY_FLG"));

        return bean;
    }

    private void dsSvcCallTpInfoMap(DeinstallCallMachineInfoBean bean, Map<String, Object> dsSvcCallTpInfoMap) {

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
    }

    private NSZC043001PMsg callFsrUpdateApi(DeinstallCallMachineInfoBean bean, DeinstallCallMachineInfoBean cpoBean) {

        // NSZC0430 FSR Update API
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, MODE_CREATE_FSR);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(apiPMsg.bypsPrfTechFlg, bean.getBypsPrfTechFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custMachCtrlNum, bean.getCustMachCtrlNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.serNum, bean.getSerNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelNum, cpoBean.getCustTelNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custTelExtnNum, cpoBean.getCustTelExtnNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.custEmlAddr, cpoBean.getCustEmlAddr());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustAttnTxt, getSvcCustAttnTxt(cpoBean.getSvcCustAttnTxt()));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoNum, substringPoNum(bean.getCustPoNum()));
        ZYPEZDItemValueSetter.setValue(apiPMsg.custPoDt, bean.getCustPoDt());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsSvcCallTpCd, bean.getDsSvcCallTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        String sysDateTime = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvDt, sysDateTime.substring(0, SUBSTRING_LEN8));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskRcvTm, sysDateTime.substring(SUBSTRING_LEN8, SUBSTRING_LEN14));
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcTaskSchdByUsrId, getSvcTaskSchdByUsrId(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prtChrgFlg, bean.getPrtChrgFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcLborChrgFlg, bean.getSvcLborChrgFlg());
        ZYPEZDItemValueSetter.setValue(apiPMsg.pmtTermCashDiscCd, getPmtTermCashDiscCd(bean));
        ZYPEZDItemValueSetter.setValue(apiPMsg.firstSvcTaskFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcPblmTpCd, "9000"); // INSTALLATION)
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.S21_SYSTEM);
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcCustCllrTxt, getSvcCustCllrTxt(cpoBean.getSvcCustCllrTxt()));
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);

        // Service Task List
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCallPrtyCd, bean.getSvcCallPrtyCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).techCd, bean.getTechCd2());
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);

        apiPMsg.taskList.no(0).erlStartTs.clear();
        apiPMsg.taskList.no(0).lateStartTs.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelNum, cpoBean.getSvcCustCllrTelNum());
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcCustCllrTelExtnNum, cpoBean.getSvcCustCllrTelExtnNum());
        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        setApiErrMsgList(apiPMsg);

        SVC_TASKTMsg tMsg = getSvcTask(apiPMsg.fsrNum.getValue());
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.rmaNum, bean.getRmaNum());
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK, tMsg.svcTaskNum.getValue() });
            }
        }

        return apiPMsg;
    }

    private NSZC043001PMsg callFsrUpdateApiForUpdateFsrMode(DeinstallCallMachineInfoBean bean, NSZC043001PMsg pMsg, NSZC045001PMsg addTaskPMsg, int index, String dsSvcCallTpCd) {

        // NSZC0430 FSR Update API
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.userId, profileService.getContextUserInfo().getUserId());
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
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());

        // Service Task List
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcTaskNum, addTaskPMsg.xxSvcTaskList.no(0).svcTaskNum);
        }
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).techCd, bean.getTechCd2());
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);

        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        setApiErrMsgList(apiPMsg);

        SVC_TASKTMsg tMsg = getSvcTask(apiPMsg.fsrNum.getValue());
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.rmaNum, bean.getRmaNum());
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK, tMsg.svcTaskNum.getValue() });
            }
        }

        return apiPMsg;
    }

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

    private String getPmtTermCashDiscCd(DeinstallCallMachineInfoBean bean) {

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

    private String getSvcTaskSchdByUsrId(DeinstallCallMachineInfoBean bean) {

        String userId = null;
        if (ZYPCommonFunc.hasValue(bean.getDcrdEzUpUserId())) {
            userId = bean.getDcrdEzUpUserId();
        } else {
            if (ZYPCommonFunc.hasValue(bean.getDciiEzUpUserId1())) {
                userId = bean.getDciiEzUpUserId1();
            } else if (ZYPCommonFunc.hasValue(bean.getDciiEzUpUserId2())) {
                userId = bean.getDciiEzUpUserId2();
            }
        }
        return userId;
    }

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

    private void addMsgList(NSZC043001PMsg apiPMsg) {
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

    private DeinstallCallMachineInfoBean setCpoCtacPsnInfo(DeinstallCallMachineInfoBean bean) {

        DeinstallCallMachineInfoBean cpoCtacPsnBean = new DeinstallCallMachineInfoBean();
        Map<String, Object> cpoCtacPsnInfo = getCpoCtacPsnInfo(bean.getCpoOrdNum(), bean.getCpoConfigPk());
        if (cpoCtacPsnInfo != null) {
            cpoCtacPsnBean.setCustTelNum((String) cpoCtacPsnInfo.get("CTAC_PSN_TEL_NUM"));
            cpoCtacPsnBean.setCustTelExtnNum((String) cpoCtacPsnInfo.get("CTAC_PSN_EXTN_NUM"));
            cpoCtacPsnBean.setCustEmlAddr((String) cpoCtacPsnInfo.get("CTAC_PSN_EML_ADDR"));
            cpoCtacPsnBean.setSvcCustAttnTxt((String) cpoCtacPsnInfo.get("SVC_CUST_ATTN_TXT"));
            cpoCtacPsnBean.setSvcCustCllrTxt((String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TXT"));
            cpoCtacPsnBean.setSvcCustCllrTelNum((String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_NUM"));
            cpoCtacPsnBean.setSvcCustCllrTelExtnNum((String) cpoCtacPsnInfo.get("SVC_CUST_CLLR_TEL_EXTN_NUM"));
        }
        if (!ZYPCommonFunc.hasValue(cpoCtacPsnBean.getCustEmlAddr())) {
            cpoCtacPsnBean.setCustEmlAddr(this.istlNotExistEmlAddr);
        }
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

    private PreparedStatement getTargetData() throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dsBizLastUpdTs", this.dsBizLastUpdTs);
        queryParam.put("sysTimeStamp", this.sysTs);
        queryParam.put("svcMachMstrSts", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        queryParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        queryParam.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        queryParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        
        return this.ssmLLClient.createPreparedStatement("getTargetData", queryParam, getExecPrm());
    }

    private PreparedStatement getSvcDeinstallCallMachineInfo() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcMachMstrSts", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        queryParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        queryParam.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        queryParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);

        return this.ssmLLClient.createPreparedStatement("getSvcDeinstallCallMachineInfo", queryParam, getExecPrm());
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsSvcCallTpInfo(String svcIstlCallGrpNum) {
        if (svcIstlCallGrpNum == null) {
            return (new ArrayList<Map<String, Object>>());
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcIstlCallGrpNum", svcIstlCallGrpNum);
        queryParam.put("svcIstlCallGrpFlg", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsSvcCallTpInfo", queryParam);
    }

    private Map<String, Object> getCpoCtacPsnInfo(String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        if (!ZYPCommonFunc.hasValue(this.ctacPsnTpCd)) {
            return null;
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);
        queryParam.put("ctacPsnTpCd", this.ctacPsnTpCd);
        queryParam.put("len60", SUBSTRING_LEN60);

        return (Map<String, Object>) ssmBatchClient.queryObject("getCpoCtacPsnInfo", queryParam);
    }

    private String substringPoNum(String custPoNum) {
        return custPoNum;
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

    private boolean isThirdPartyCall(BigDecimal svcMachMstrPk, String fsrNum, String fsrVisitNum, String dsSvcCallTpCd) {

        if (isClickSendExclCall(dsSvcCallTpCd)) {
            return true;
        }

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

    private boolean isClickSendExclCall(String dsSvcCallTpCd) {
        for (int i = 0; i < this.clickSendExclCallTpList.length; i++) {
            if (this.clickSendExclCallTpList[i].equals(dsSvcCallTpCd)) {
                return true;
            }
        }
        return false;
    }
}
