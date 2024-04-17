/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB581001;

import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.API_NMZC001001;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.API_NMZC002001;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.API_NMZC201001;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.BAT_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_CTAC_PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_CTAC_PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_CTAC_PSN_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_CTAC_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_DS_CTAC_PNT_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_DS_CTAC_PNT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_EML_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_LOC_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_RTL_DLR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.COL_TEL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.C_XTRNL_SYS_VND_TP_LKUP_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.KEY_COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.KEY_COA_CH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.KEY_LGCY_CUST_CLS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAIL_ADDR_TO_GRP;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAIL_FROM_ADDR_GRP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAIL_TEMPLATE_BATCH_ID_KEY;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAIL_TEMPLATE_BATCH_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAIL_TEMPLATE_ERROR_INFO_KEY;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAIL_TEMP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MAX_LEN_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_ID_NMAM8186E;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_ID_NPAM1323E;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_ID_ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_STR_CC;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_STR_CNTCT_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_STR_COMP_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_STR_GCC;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_STR_GIC;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.MSG_STR_PRNT_VND_TP;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.VAL_CTAC_PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB581001.constant.NMAB581001Constant.VAL_EXTERNAL;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CTAC_PNTTMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC201001PMsg;
import business.parts.NMZC201002PMsg;
import business.parts.NMZC201003PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC001001.NMZC001001;
import com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC201001.NMZC201001;
import com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Receive Supplier Info from CUSA ROSS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   CITS            T.Wada          Create
 * 08/31/2016   CITS            T.Gotoda        Update          QC#12218
 * 09/06/2016   CITS            T.Gotoda        Update          QC#14133
 * 
 *</pre>
 */
public class NMAB581001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    // -- Count of processing -------------------
    /** The number of cases : Insert */
    private int normalRecCnt = 0;

    /** The number of case : Skip */
    private int errRecCnt = 0;

    /** error message List */
    private List<String> errorInfoList = new ArrayList<String>();

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Contact Type */
    private String ctacTp = null;

    /** classificationCd */
    private String classificationCd = null;

    /** glClassificationCd */
    private String glClassificationCd = null;

    /** glIntercompanyCd */
    private String glIntercompanyCd = null;

    /** prntVndTpCd */
    private String prntVndTpCd = null;

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NMAB581001().executeBatch(NMAB581001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_COMP_CODE });
        }

        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Contact Type
        ctacTp = getContactType();
        if (!ZYPCommonFunc.hasValue(ctacTp)) {
            throw new S21AbendException(MSG_ID_NMAM8186E, new String[] {MSG_STR_CNTCT_TP });
        }

        // Classification Code,GL Classification code,GL Intercompany
        // code
        classificationCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_LGCY_CUST_CLS_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(classificationCd)) {
            throw new S21AbendException(MSG_ID_NMAM8186E, new String[] {MSG_STR_CC });
        }
        glClassificationCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_COA_CH_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(glClassificationCd)) {
            throw new S21AbendException(MSG_ID_NMAM8186E, new String[] {MSG_STR_GCC });
        }
        glIntercompanyCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_COA_AFFL_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(glIntercompanyCd)) {
            throw new S21AbendException(MSG_ID_NMAM8186E, new String[] {MSG_STR_GIC });
        }

        // Parent Vendor Type
        prntVndTpCd = getPrntVndTpCd(C_XTRNL_SYS_VND_TP_LKUP_TXT);
        if (!ZYPCommonFunc.hasValue(prntVndTpCd)) {
            throw new S21AbendException(MSG_ID_NMAM8186E, new String[] {MSG_STR_PRNT_VND_TP });
        }
    }

    @Override
    protected void termRoutine() {

        if (hasValueList(this.errorInfoList)) {
            // send error mail
            postErrorMail();
            termCd = TERM_CD.WARNING_END;
        }

        // Setting of termination code
        setTermState(termCd, normalRecCnt, errRecCnt);
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            // Get CBS_FEDEX_SVC_DLR INFO
            Map<String, Object> param = new HashMap<String, Object>();
            prdStmt = ssmLLClient.createPreparedStatement("getCbsFedexSvcDlr", param, execParam);
            rs = prdStmt.executeQuery();

            while (rs.next()) {

                // Main Process
                if (!mainProcess(rs)) {
                    rollback();
                    errRecCnt++;
                } else {
                    commit();
                    normalRecCnt++;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    // ****************************************************************
    // Main Process
    // ****************************************************************
    private boolean mainProcess(ResultSet rs) throws SQLException {

        // Get Vnd INFO By CUSA_RTL_DLR_CD
        Map<String, Object> vndInfo = getVendInfo(rs.getString(COL_RTL_DLR_CD));

        if (vndInfo == null) {
            // Regist Supplier & CustomerAccount
            if (!regSplyAndCustAcct(rs)) {
                return false;
            }
        } else {

            // Update Supplier Contact
            List<Map<String, Object>> splyCntctInfoList = getSplyContact(rs.getString(COL_RTL_DLR_CD));
            if (splyCntctInfoList.size() != 0) {
                // Obtain an exclusive
                for (Map<String, Object> splyCntctInfo : splyCntctInfoList) {
                    lockCtactPsnInfo((BigDecimal) splyCntctInfo.get(COL_DS_CTAC_PNT_PK));
                }

                // Update Mode
                if (!updSplyCntct(rs, vndInfo, splyCntctInfoList)) {
                    return false;
                }
            } else {
                // New Regist Mode
                if (!regSplyCntct(rs, vndInfo)) {
                    return false;
                }
            }
        }

        return true;
    }
    /**
     * regSplyAndCustAcct
     * @param rs
     * @param vndInfo
     * @throws SQLException
     */
    private boolean regSplyAndCustAcct(ResultSet rs) throws SQLException {
        // ///////////////////////////////////
        // Regist Customer Account
        // ///////////////////////////////////
        NMZC001001PMsg nmzc001001PMsg = new NMZC001001PMsg();

        // XX_MODE_CD
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.xxModeCd, NMZC001001Constant.PROCESS_MODE_CUST_CRAT);

        // Global Company Code
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.glblCmpyCd, this.glblCmpyCd);

        // Sales Date
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.slsDt, this.slsDt);

        // Account Name
        String acctNm = rs.getString(COL_LOC_NM) + "-" + rs.getString(COL_RTL_DLR_CD);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.dsAcctNm, acctNm);

        // Internal / External
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.dsAcctItrlFlg, VAL_EXTERNAL);

        // Classification Code
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.dsAcctClsCd, this.classificationCd);

        // GL Classification code
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.coaChCd, this.glClassificationCd);

        // GL Intercompany code
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.coaAfflCd, this.glIntercompanyCd);

        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.xxAcctCrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.xxAcctCltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.xxAcctDunsInfoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.xxAcctInacRsnFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.xxAcctTaxFlg, ZYPConstant.FLG_OFF_N);


        // NMZC001002PMsg Info
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).firstLineAddr, "CUSA DEALER ONLY");
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).scdLineAddr, rs.getString(COL_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).ctyAddr, rs.getString(COL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).stCd, rs.getString(COL_ST_CD));
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).postCd, rs.getString(COL_POST_CD));
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).locNm, acctNm);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).ctryCd, CTRY.UNITED_STATES_OF_AMERICA);
        //QC#12218 Start
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).xxCntyVldErrExclFlg, ZYPConstant.FLG_ON_Y);
        //QC#12218 End

        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).xxPrinFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).billToCustFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc001001PMsg.NMZC001002PMsg.no(0).shipToCustFlg, ZYPConstant.FLG_OFF_N);

        nmzc001001PMsg.NMZC001002PMsg.setValidCount(1);

        // execute CustomerUpdateAPI(NMZC001001)
        NMZC001001 apiCustomer = new NMZC001001();
        apiCustomer.execute(nmzc001001PMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(nmzc001001PMsg)) {
            List<String> errIdList = S21ApiUtil.getXxMsgIdList(nmzc001001PMsg);
            for (String msgId : errIdList) {
                if (msgId.endsWith("E")) {
                    outputErr(MSG_ID_NPAM1323E, new String[] {API_NMZC001001, msgId});

                    String errMsgText = S21MessageFunc.clspGetMessage(MSG_ID_NPAM1323E, new String[] {API_NMZC001001, msgId});
                    //Add Error Mail Message Text
                    this.errorInfoList.add(errMsgText);
                }
            }

            return false;
        }

        // ///////////////////////////////////
        // Regist Supplier
        // ///////////////////////////////////
        NMZC201001PMsg nmzc201001PMsg = new NMZC201001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.xxModeCd, NMZC201001Constant.CREATE_MODE);
        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.procDt, this.slsDt);

        String prntVndCd = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRNT_VND_SQ).toPlainString();
        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.prntVndCd, prntVndCd);

        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.prntVndNm, acctNm);
        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.prntVndTpCd, this.prntVndTpCd);
        String dsAcctNm = nmzc001001PMsg.dsAcctNum.getValue();
        ZYPEZDItemValueSetter.setValue(nmzc201001PMsg.dsAcctNum, dsAcctNm);

        // Spply Site
        NMZC201002PMsg nmzc201002PMsg = new NMZC201002PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.xxModeCd, NMZC201001Constant.CREATE_MODE);
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.prntVndCd, prntVndCd);

        String vndCd = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.VND_SQ).toPlainString();
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.firstLineAddr, rs.getString(COL_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.ctyAddr, rs.getString(COL_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.stCd, rs.getString(COL_ST_CD));
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.postCd, rs.getString(COL_POST_CD));
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.ctryCd, CTRY.UNITED_STATES_OF_AMERICA);
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.locNm, rs.getString(COL_RTL_DLR_CD));
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.splySiteDealCd, rs.getString(COL_RTL_DLR_CD));

        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.splyPmtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nmzc201002PMsg.splyPoFlg, ZYPConstant.FLG_OFF_N);

        List<NMZC201002PMsg> nmzc201002PMsgList = new ArrayList<NMZC201002PMsg>();
        nmzc201002PMsgList.add(nmzc201002PMsg);
        List<NMZC201003PMsg> nmzc201003PMsgList = new ArrayList<NMZC201003PMsg>();

        // execute SupplierUpdateAPI(NMZC201001)
        NMZC201001 updateSupplierApi = new NMZC201001();
        updateSupplierApi.execute(nmzc201001PMsg, nmzc201002PMsgList, nmzc201003PMsgList, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(nmzc201001PMsg)) {
            List<String> errIdList = S21ApiUtil.getXxMsgIdList(nmzc201001PMsg);
            for (String msgId : errIdList) {
                if (msgId.endsWith("E")) {
                    outputErr(MSG_ID_NPAM1323E, new String[] {API_NMZC201001, msgId});

                    String errMsgText = S21MessageFunc.clspGetMessage(MSG_ID_NPAM1323E, new String[] {API_NMZC001001, msgId});
                    //Add Error Mail Message Text
                    this.errorInfoList.add(errMsgText);
                }
            }

            return false;
        }

        // ///////////////////////////////////
        // Regist Supplier Contact
        // ///////////////////////////////////
        Map<String, Object> vndInfo = getVendInfo(rs.getString(COL_RTL_DLR_CD));
        if (!regSplyCntct(rs, vndInfo)) {
            return false;
        }

        return true;
    }

    /***
     * regSplyCntct
     * @param rs
     * @param splyCntacInfo
     * @throws SQLException
     */
    private boolean regSplyCntct(ResultSet rs, Map<String, Object> vndInfo) throws SQLException {

        NMZC002001PMsg nmzc002001PMsg = new NMZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.locNum, (String) vndInfo.get(COL_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.effFromDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnFirstNm, VAL_CTAC_PSN_FIRST_NM);

        // CTAC_PSN_LAST_NM
        if (ZYPCommonFunc.hasValue(rs.getString(COL_RTL_DLR_CD)) && ZYPCommonFunc.hasValue(rs.getString(COL_LOC_NM))) {
            String ctacPsnLastNm = null;
            if (rs.getString(COL_LOC_NM).length() > MAX_LEN_LOC_NM) {
                ctacPsnLastNm = rs.getString(COL_RTL_DLR_CD) + " " + rs.getString(COL_LOC_NM).substring(0, MAX_LEN_LOC_NM);
            } else {
                ctacPsnLastNm = rs.getString(COL_RTL_DLR_CD) + " " + rs.getString(COL_LOC_NM);
            }
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnLastNm, ctacPsnLastNm);
        }
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacTpCd, this.ctacTp);
        ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

        int i = 0;
        if (ZYPCommonFunc.hasValue(rs.getString(COL_TEL_NUM))) {
            // MODE
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);

            // DS_CTAC_PNT_TP_CD
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);

            // DS_CTAC_PNT_VAL_TXT
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_TEL_NUM));
            i++;
        }

        if (ZYPCommonFunc.hasValue(rs.getString(COL_EML_ADDR))) {
            // MODE
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);

            // DS_CTAC_PNT_TP_CD
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);

            // DS_CTAC_PNT_VAL_TXT
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_EML_ADDR));
            i++;
        }

        if (ZYPCommonFunc.hasValue(rs.getString(COL_FAX_NUM))) {
            // MODE
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);

            // DS_CTAC_PNT_TP_CD
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);

            // DS_CTAC_PNT_VAL_TXT
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_FAX_NUM));
            i++;
        }

        if (i > 0) {
            // execute SupplierContactAPI(NMZC002001)
            nmzc002001PMsg.ContactPointInfoList.setValidCount(i);
            NMZC002001 updateContactApi = new NMZC002001();
            updateContactApi.execute(nmzc002001PMsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(nmzc002001PMsg)) {
                List<String> errIdList = S21ApiUtil.getXxMsgIdList(nmzc002001PMsg);
                errRecCnt++;
                this.setTermState(TERM_CD.ABNORMAL_END);
                for (String msgId : errIdList) {
                    if (msgId.endsWith("E")) {
                        outputErr(MSG_ID_NPAM1323E, new String[] {API_NMZC002001, msgId});

                        String errMsgText = S21MessageFunc.clspGetMessage(MSG_ID_NPAM1323E, new String[] {API_NMZC001001, msgId});
                        //Add Error Mail Message Text
                        this.errorInfoList.add(errMsgText);
                    }
                }

                return false;
            }
        }

        return true;
    }

    /**
     * updSplyCntct
     * @param rs
     * @param vndInfo
     * @param splyCntctInfoList
     * @throws SQLException
     */
    private boolean updSplyCntct(ResultSet rs, Map<String, Object> vndInfo, List<Map<String, Object>> splyCntctInfoList) throws SQLException {

        NMZC002001PMsg nmzc002001PMsg = new NMZC002001PMsg();

        boolean phoneExist = false;
        boolean emaiExist = false;
        boolean faxExist = false;
        int i = 0;
        for (Map<String, Object> splyCntctInfo : splyCntctInfoList) {
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_UPD);
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.slsDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnPk, (BigDecimal) splyCntctInfo.get(COL_CTAC_PSN_PK));
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.locNum, (String) vndInfo.get(COL_LOC_NUM));
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.effFromDt, (String) splyCntctInfo.get(COL_EFF_FROM_DT));

            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnFirstNm, (String) splyCntctInfo.get(COL_CTAC_PSN_FIRST_NM));
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnLastNm, (String) splyCntctInfo.get(COL_CTAC_PSN_LAST_NM));
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacTpCd, (String) splyCntctInfo.get(COL_CTAC_TP_CD));
            ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

            String dsCtacPntTpCd = (String) splyCntctInfo.get(COL_DS_CTAC_PNT_TP_CD);

            if (DS_CTAC_PNT_TP.PHONE_WORK.equals(dsCtacPntTpCd)) {
                phoneExist = true;
                if (ZYPCommonFunc.hasValue(rs.getString(COL_TEL_NUM))) {
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntPk, (BigDecimal) splyCntctInfo.get(COL_DS_CTAC_PNT_PK));
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_TEL_NUM));
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                    i++;
                }
            } else if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(dsCtacPntTpCd)) {
                emaiExist = true;
                if (ZYPCommonFunc.hasValue(rs.getString(COL_EML_ADDR))) {
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntPk, (BigDecimal) splyCntctInfo.get(COL_DS_CTAC_PNT_PK));
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_EML_ADDR));
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                    i++;
                }
            } else if (DS_CTAC_PNT_TP.FAX_WORK.equals(dsCtacPntTpCd)) {
                faxExist = true;
                if (ZYPCommonFunc.hasValue(rs.getString(COL_FAX_NUM))) {
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntPk, (BigDecimal) splyCntctInfo.get(COL_DS_CTAC_PNT_PK));
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_FAX_NUM));
                    ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                    i++;
                }
            }
        }

        if (!phoneExist) {
            if (ZYPCommonFunc.hasValue(rs.getString(COL_TEL_NUM))) {
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_TEL_NUM));
                i++;
            }
        }
        if (!emaiExist) {
            if (ZYPCommonFunc.hasValue(rs.getString(COL_EML_ADDR))) {
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_EML_ADDR));
                i++;
            }
        }
        if (!faxExist) {
            if (ZYPCommonFunc.hasValue(rs.getString(COL_FAX_NUM))) {
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                ZYPEZDItemValueSetter.setValue(nmzc002001PMsg.ContactPointInfoList.no(i).dsCtacPntValTxt, rs.getString(COL_FAX_NUM));
                i++;
            }
        }

        if (i != 0) {
            nmzc002001PMsg.ContactPointInfoList.setValidCount(i);
        }

        NMZC002001 updateContactApi = new NMZC002001();
        updateContactApi.execute(nmzc002001PMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(nmzc002001PMsg)) {
            List<String> errIdList = S21ApiUtil.getXxMsgIdList(nmzc002001PMsg);
            errRecCnt++;
            this.setTermState(TERM_CD.ABNORMAL_END);
            for (String msgId : errIdList) {
                if (msgId.endsWith("E")) {
                    outputErr(MSG_ID_NPAM1323E, new String[] {API_NMZC002001, msgId});

                    String errMsgText = S21MessageFunc.clspGetMessage(MSG_ID_NPAM1323E, new String[] {API_NMZC001001, msgId});
                    //Add Error Mail Message Text
                    this.errorInfoList.add(errMsgText);
                }
            }

            return false;
        }

        return true;
    }

    /**
     * getVendInfo
     * @param splySiteDealCd
     * @return
     */
    private Map<String, Object> getVendInfo(String splySiteDealCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SPLY_SITE_DEAL_CD", splySiteDealCd);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getVendInfo", queryParam);
        return ret;
    }

    /**
     * getContactType
     * @return
     */
    private String getContactType() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        String ret = (String) this.ssmBatchClient.queryObject("getContactType", queryParam);
        return ret;
    }

    /**
     * getPrntVndTpCd
     * @param xtrnlSysVndTpLkupTxt
     * @return
     */
    private String getPrntVndTpCd(String xtrnlSysVndTpLkupTxt) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("XTRNL_SYS_VND_TP_LKUP_TXT", xtrnlSysVndTpLkupTxt);
        String ret = (String) this.ssmBatchClient.queryObject("getPrntVndTpCd", queryParam);
        return ret;
    }

    /**
     * getSplyContact
     * @param splySiteDealCd
     * @return
     */
    private List<Map<String, Object>> getSplyContact(String splySiteDealCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SPLY_SITE_DEAL_CD", splySiteDealCd);
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSplyContact", queryParam);
        return ret;
    }

    /**
     * lockCtactPsnInfo
     * @param ctacPsnPk
     */
    private void lockCtactPsnInfo(BigDecimal dsCtacPntPk) {
        DS_CTAC_PNTTMsg inMsg = new DS_CTAC_PNTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsCtacPntPk, dsCtacPntPk);
        EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
    }
    private void outputErr(String msgId, String[] msgParam) {
        S21InfoLogOutput.println(msgId, msgParam);
    }

    // ****************************************************************
    // Post Error Mail Method
    // ****************************************************************
    private void postErrorMail() {
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_FROM_ADDR_GRP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (!hasValueList(addrFromList)) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_ADDR_TO_GRP);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (!hasValueList(addrToList)) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMP_ID);
        if (template == null) {
            return;
        }

        template.setTemplateParameter(MAIL_TEMPLATE_BATCH_ID_KEY, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_BATCH_NM, BAT_NM);
        String errMsg = getMailBodyErrMsg();
        template.setTemplateParameter(MAIL_TEMPLATE_ERROR_INFO_KEY, errMsg);

        // *****************************************************************
        // Post mail
        // *****************************************************************
        S21Mail mail;
        for (S21MailAddress addr : addrToList) {
            mail = new S21Mail(this.glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddress(addr);
            mail.setMailTemplate(template);
            mail.postMail();
        }
    }

    private String getMailBodyErrMsg() {
        StringBuilder sb = new StringBuilder();

        for (String message : this.errorInfoList) {
            sb.append(message);
            sb.append("\n");
            sb.append("    ");
        }

        return sb.toString();
    }

    // ****************************************************************
    // Data Check Method
    // ****************************************************************
    private static <T extends List< ? >> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }
}
