package com.canon.cusa.s21.batch.NLB.NLBB021001;

import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_BILL_TO_PTY_ADDR;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_BILL_TO_PTY_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_BILL_TO_PTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_BILL_TO_PTY_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_BILL_TO_PTY_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CENT_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CNSGN_ADDR;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CNSGN_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CNSGN_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CNSGN_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CNSGN_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CNTNR_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_CTAC_TXT;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_EX_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_EX_LOAD_QTY;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_EX_PKG_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_GS_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_LOAD_QTY;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_NET_AMT;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_PICK_UP_DT;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_PICK_UP_TM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_PKG_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_PRO_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SGN_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SHPPR_ADDR;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SHPPR_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SHPPR_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SHPPR_POST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SHPPR_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_SQ_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_STS_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_STS_CTY_NM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_STS_DT;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_STS_RSN_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_STS_ST_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_STS_TM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_ST_CTRL_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_TM_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_TP_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_TP_CD_LENGTH;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_TRX_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_UCC_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_WT;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.EDI_WT_UNIT_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.LEN_TM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.LINE_FEED_CODE;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_KEY_TO;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_MSG_HEADER;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_TEMPLATE_KEY_DATE;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_TEMPLATE_KEY_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAIL_TEMPLATE_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAX_NUM_HH;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAX_NUM_MM;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.MAX_NUM_SS;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1064E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1065E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1089E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1090E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1091E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1092E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1093E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1094E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1095E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1101E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLBM1102E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.NLGM0049E;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POD_SRC_TP_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POS_HH_ED;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POS_HH_ST;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POS_MM_ED;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POS_MM_ST;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POS_SS_ED;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.POS_SS_ST;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.PRAM_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLB.NLBB021001.constant.NLBB021001Constant.PRO_NUM_LENGTH;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.POD_ADDR_WRKTMsg;
import business.db.POD_STS_WRKTMsg;
import business.parts.NLZC402001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC402001.NLZC402001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
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
 * Batch Program Class for Delivery Confirmation from EDI
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   CITS            T.Wada          Create          
 * 02/03/2017   CITS            T.Kikuhara      Update          QC#15161
 * 03/13/2019   CITS            K.Ogino         Update          QC#30750
 *</pre>
 */
public class NLBB021001 extends S21BatchMain {

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** correct count */
    private int correctCount = 0;

    /** error count */
    private int errorCount = 0;

    /** error message code list */
    private List<String> errMsgCdList;

    /** pro number list */
    private List<String> proNoList;

    /** ediTpCd list */
    private List<String> ediTpCdList;

    /** commit count */
    private int commitCount = 0;

    /** error message code for POD_ADDR_WRK */
    private String errMsgCdForAddr = "";

    // *********************************************************
    // Methods
    // *********************************************************
    /**
     * Startups.
     * @param args arcuments
     */
    public static void main(String[] args) {
        new NLBB021001().executeBatch(NLBB021001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialize Variables
        termCd = TERM_CD.NORMAL_END;

        // Getting Global Company Code
        S21UserProfileServiceFactory profFactory = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService prof = profFactory.getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {PRAM_NM_GLBL_CMPY_CD });
        }

        commitCount = getCommitCount();
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // POD (Proof of Delivery)
        deliveryConfFromEdi(execParam);

    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.correctCount, this.errorCount, this.correctCount + this.errorCount );

    }

    /**
     * deliveryConfFromEdi
     * @param execParam
     */

    private void deliveryConfFromEdi(S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmt = null;
        ResultSet rs = null;
        this.proNoList = new ArrayList<String>();
        this.ediTpCdList = new ArrayList<String>();
        this.errMsgCdList = new ArrayList<String>();

        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);

            prdStmt = ssmLLClient.createPreparedStatement("getTargetAddrData", queryParam, execParam);
            rs = prdStmt.executeQuery();

            while (rs.next()) {

                boolean errFlg = false;
                this.errMsgCdForAddr = "";

                List<Map<String, Object>> stsInfoList = getTargetStsData(rs.getString(EDI_GS_CTRL_CD), rs.getString(EDI_ST_CTRL_CD));

                // QC#15161 update start
                for (Map<String, Object> stsInfo : stsInfoList) {
                    // Check Before call API
                    errFlg = bizErrChk(stsInfo);
                    if (!errFlg) {
                        // Call Delivery Confirmation API
                        errFlg = execDelivConfApi(rs, stsInfo);
                    }
                    if (errFlg) {
                        errorCount++;
                    } else {
                        correctCount++;
                    }
                    // Update Detail Info
                    updateStsWrk(stsInfo, errFlg);
                }
                // QC#15161 update end

                // Update Header Info
                updateAddrWrk(rs, errFlg);

                // Commit
                if (commitCount >= getCommitCount()) {
                    commit();
                    commitCount = 0;
                }
            }
            if (!this.proNoList.isEmpty()) {
                registerMail();
            }

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    /**
     * execDelivConfApi
     * @param rs
     * @param stsInfo
     * @throws SQLException
     */
    private boolean execDelivConfApi(ResultSet rs, Map<String, Object> stsInfo) throws SQLException {

        NLZC402001PMsg pMsg = new NLZC402001PMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        // EDI_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediTpCd, rs.getString(EDI_TP_CD));
        // EDI_SHPPR_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediShpprNm, rs.getString(EDI_SHPPR_NM));
        // EDI_SHPPR_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg.ediShpprAddr, rs.getString(EDI_SHPPR_ADDR));
        // EDI_SHPPR_CTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediShpprCtyNm, rs.getString(EDI_SHPPR_CTY_NM));
        // EDI_SHPPR_ST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediShpprStCd, rs.getString(EDI_SHPPR_ST_CD));
        // EDI_SHPPR_POST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediShpprPostCd, rs.getString(EDI_SHPPR_POST_CD));
        // EDI_BILL_TO_PTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediBillToPtyNm, rs.getString(EDI_BILL_TO_PTY_NM));
        // EDI_BILL_TO_PTY_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg.ediBillToPtyAddr, rs.getString(EDI_BILL_TO_PTY_ADDR));
        // EDI_BILL_TO_PTY_CTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediBillToPtyCtyNm, rs.getString(EDI_BILL_TO_PTY_CTY_NM));
        // EDI_BILL_TO_PTY_ST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediBillToPtyStCd, rs.getString(EDI_BILL_TO_PTY_ST_CD));
        // EDI_BILL_TO_PTY_POST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediBillToPtyPostCd, rs.getString(EDI_BILL_TO_PTY_POST_CD));
        // EDI_CNSGN_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediCnsgnNm, rs.getString(EDI_CNSGN_NM));
        // EDI_CNSGN_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg.ediCnsgnAddr, rs.getString(EDI_CNSGN_ADDR));
        // EDI_CNSGN_CTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediCnsgnCtyNm, rs.getString(EDI_CNSGN_CTY_NM));
        // EDI_CNSGN_ST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediCnsgnStCd, rs.getString(EDI_CNSGN_ST_CD));
        // EDI_CNSGN_POST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediCnsgnPostCd, rs.getString(EDI_CNSGN_POST_CD));
        // POD_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg.podSrcTpCd, rs.getString(POD_SRC_TP_CD));

        // EDI_PRO_NUM
        ZYPEZDItemValueSetter.setValue(pMsg.ediProNum, (String) stsInfo.get(EDI_PRO_NUM));
        // EDI_STS_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediStsCd, (String) stsInfo.get(EDI_STS_CD));
        // EDI_STS_DT
        ZYPEZDItemValueSetter.setValue(pMsg.ediStsDt, (String) stsInfo.get(EDI_STS_DT));
        // EDI_STS_TM
        ZYPEZDItemValueSetter.setValue(pMsg.ediStsTm, (String) stsInfo.get(EDI_STS_TM));
        // EDI_TM_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediTmCd, (String) stsInfo.get(EDI_TM_CD));
        // EDI_STS_RSN_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediStsRsnCd, (String) stsInfo.get(EDI_STS_RSN_CD));
        // EDI_STS_CTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg.ediStsCtyNm, (String) stsInfo.get(EDI_STS_CTY_NM));
        // EDI_STS_ST_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediStsStCd, (String) stsInfo.get(EDI_STS_ST_CD));
        // EDI_CNTNR_NUM
        ZYPEZDItemValueSetter.setValue(pMsg.ediCntnrNum, (String) stsInfo.get(EDI_CNTNR_NUM));
        // EDI_WT
        ZYPEZDItemValueSetter.setValue(pMsg.ediWt, (BigDecimal) stsInfo.get(EDI_WT));
        // EDI_WT_UNIT_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediWtUnitCd, (String) stsInfo.get(EDI_WT_UNIT_CD));
        // EDI_LOAD_QTY
        ZYPEZDItemValueSetter.setValue(pMsg.ediLoadQty, (BigDecimal) stsInfo.get(EDI_LOAD_QTY));
        // EDI_PKG_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediPkgCd, (String) stsInfo.get(EDI_PKG_CD));
        // EDI_NET_AMT
        ZYPEZDItemValueSetter.setValue(pMsg.ediNetAmt, (BigDecimal) stsInfo.get(EDI_NET_AMT));
        // EDI_EX_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediExCd, (String) stsInfo.get(EDI_EX_CD));
        // EDI_EX_PKG_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediExPkgCd, (String) stsInfo.get(EDI_EX_PKG_CD));
        // EDI_EX_LOAD_QTY
        ZYPEZDItemValueSetter.setValue(pMsg.ediExLoadQty, (BigDecimal) stsInfo.get(EDI_EX_LOAD_QTY));
        // EDI_SGN_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediSgnCd, (String) stsInfo.get(EDI_SGN_CD));
        // EDI_CTAC_TXT
        ZYPEZDItemValueSetter.setValue(pMsg.ediCtacTxt, (String) stsInfo.get(EDI_CTAC_TXT));
        // EDI_PICK_UP_DT
        ZYPEZDItemValueSetter.setValue(pMsg.ediPickUpDt, (String) stsInfo.get(EDI_PICK_UP_DT));
        // EDI_PICK_UP_TM
        ZYPEZDItemValueSetter.setValue(pMsg.ediPickUpTm, (String) stsInfo.get(EDI_PICK_UP_TM));
        // EDI_CENT_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediCentCd, (String) stsInfo.get(EDI_CENT_CD));
        // EDI_UCC_CD
        ZYPEZDItemValueSetter.setValue(pMsg.ediUccCd, (String) stsInfo.get(EDI_UCC_CD));

        NLZC402001 api = new NLZC402001();

        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> errIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            if(NLBM1094E.equals((String) errIdList.get(0)) || NLBM1095E.equals((String) errIdList.get(0))){
                // QC#30750
                setTermState(TERM_CD.WARNING_END);
                errMsgCdForAddr = (String) errIdList.get(0);
//                bizErrorProcess((String) stsInfo.get(EDI_PRO_NUM), rs.getString(EDI_TP_CD), (String) errIdList.get(0));
                // when API error return true.
                return true;
            } else {
                rollback();
                this.setTermState(TERM_CD.ABNORMAL_END);
                throw new S21AbendException(errIdList.get(0));
            }
        }
        // no error return false.
        return false;
    }

    /**
     * updateAddrWrk
     * @param rs
     * @param errFlg
     * @throws SQLException
     */
    private void updateAddrWrk(ResultSet rs, boolean errFlg) throws SQLException {

        // Update POD_ADDR_WRK
        POD_ADDR_WRKTMsg tMsg = new POD_ADDR_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ediTrxId, rs.getString(EDI_TRX_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.ediSqId, rs.getString(EDI_SQ_ID));

        tMsg = (POD_ADDR_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLBM1064E);
        }

        if (errFlg) {
            ZYPEZDItemValueSetter.setValue(tMsg.errMsgCd, this.errMsgCdForAddr);
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, PROC_STS.ERROR);
            termCd = TERM_CD.WARNING_END;
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, PROC_STS.COMPLEATED);
        }

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLBM1064E);
        }


    }

    /**
     * updateStsWrk
     * @param stsInfo
     * @param errFlg
     * @throws SQLException
     */
    private void updateStsWrk(Map<String, Object> stsInfo, boolean errFlg) throws SQLException {

        POD_STS_WRKTMsg tMsg = new POD_STS_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ediTrxId, (String) stsInfo.get(EDI_TRX_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.ediSqId, (String) stsInfo.get(EDI_SQ_ID));
        tMsg = (POD_STS_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLBM1064E);
        }

        if (errFlg) {
            ZYPEZDItemValueSetter.setValue(tMsg.errMsgCd, this.errMsgCdForAddr);
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, PROC_STS.ERROR);
            termCd = TERM_CD.WARNING_END;
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, PROC_STS.COMPLEATED);
        }

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NLBM1064E);
        }

        commitCount++;
    }

    /**
     * bizErrChk
     * @param stsInfoList
     * @return
     */
    // QC#15161 update start
    private boolean bizErrChk(Map<String, Object> stsInfo) {

        String proNum = (String) stsInfo.get(EDI_PRO_NUM);
        String ediTpCd = (String) stsInfo.get(EDI_TP_CD);

        // EDI_PRO_NUM IS NULL
        if (!ZYPCommonFunc.hasValue(proNum)) {
            bizErrorProcess(proNum, ediTpCd, NLBM1089E);
            return true;
        }
        // EDI_TP_CD IS NULL
        if (!ZYPCommonFunc.hasValue(ediTpCd)) {
            bizErrorProcess(proNum, ediTpCd, NLBM1090E);
            return true;
        } else {
            // Get CARR_CD By EDI_TP_CD
            String carrCd = checkCarrCd(ediTpCd);
            if (!ZYPCommonFunc.hasValue(carrCd)) {
                bizErrorProcess(proNum, ediTpCd, NLBM1090E);
                return true;
            }
        }

        // EDI_STS_DT Check Date FMT
        String stsDt = (String) stsInfo.get(EDI_STS_DT);
        if (ZYPCommonFunc.hasValue(stsDt)) {
            if (!ZYPDateUtil.checkDate(stsDt)) {
                bizErrorProcess(proNum, ediTpCd, NLBM1091E);
                return true;
            }
        }

        // EDI_STS_TM Check Time FMT
        String stsTm = (String) stsInfo.get(EDI_STS_TM);
        if (ZYPCommonFunc.hasValue(stsTm)) {
            if (!checkHHMISS(stsTm)) {
                bizErrorProcess(proNum, ediTpCd, NLBM1092E);
                return true;
            }
        }

        // EDI_STS_CD
        String ediStsCd = (String) stsInfo.get(EDI_STS_CD);
        if (!ZYPCommonFunc.hasValue(ediStsCd)) {
            bizErrorProcess(proNum, ediTpCd, NLBM1093E);
            return true;
        }
        if (!checkPodStsCd(ediStsCd)) {
            bizErrorProcess(proNum, ediTpCd, NLBM1093E);
            return true;
        }

        // EDI_PICK_UP_DT Check Date FMT
        String pickUpDt = (String) stsInfo.get(EDI_PICK_UP_DT);
        if (ZYPCommonFunc.hasValue(pickUpDt)) {
            if (!ZYPDateUtil.checkDate(pickUpDt)) {
                bizErrorProcess(proNum, ediTpCd, NLBM1101E);
                return true;
            }
        }

        // EDI_PICK_UP_TM Check Time FMT
        String pickUpTm = (String) stsInfo.get(EDI_PICK_UP_TM);
        if (ZYPCommonFunc.hasValue(pickUpTm)) {
            if (!checkHHMISS(pickUpTm)) {
                bizErrorProcess(proNum, ediTpCd, NLBM1102E);
                return true;
            }
        }

        return false;
    }
    // QC#15161 update end

    /**
     * checkCarrCd
     * @param ediTpCd
     * @return
     */
    private String checkCarrCd(String ediTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("ediTpCd", ediTpCd);
        String ret = (String) this.ssmBatchClient.queryObject("checkCarrCd", queryParam);
        return ret;
    }

    /**
     * countPodStsCd
     * @param ediStsCd
     * @return
     */
    private boolean checkPodStsCd(String ediStsCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("podStsCd", ediStsCd);
        BigDecimal ret = (BigDecimal) this.ssmBatchClient.queryObject("checkPodStsCd", queryParam);
        if(ret.intValue() > 0) {
            return true;
        }
        return false;
    }

    /**
     * getTargetStsData
     * @param ediGsCtrlCd
     * @param ediStCtrlCd
     * @return
     */
    private List<Map<String, Object>> getTargetStsData(String ediGsCtrlCd, String ediStCtrlCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("procStsCd", PROC_STS.IN_COMPLETED);
        queryParam.put("ediGsCtrlCd", ediGsCtrlCd);
        queryParam.put("ediStCtrlCd", ediStCtrlCd);
        List<Map<String, Object>> ret = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTargetStsData", queryParam);
        return ret;
    }


    /**
     * registerMail
     */
    private void registerMail() {

        // 10.1. Get From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 10.2. Get To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        groupTo.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NLBM1065E);
        }

        // 10.3. Create message for Body
        StringBuilder msgBuf = new StringBuilder();
        msgBuf.append(MAIL_MSG_HEADER);
        msgBuf.append(LINE_FEED_CODE);
        for (int i = 0; i < this.proNoList.size(); i++) {
            String errorMessage = S21MessageFunc.clspGetMessage((String) this.errMsgCdList.get(i));

            msgBuf.append(" ");
            msgBuf.append(ZYPCommonFunc.paddingSpace((String) this.ediTpCdList.get(i), false, EDI_TP_CD_LENGTH));
            msgBuf.append(" ");
            msgBuf.append(ZYPCommonFunc.paddingSpace((String) this.proNoList.get(i), false, PRO_NUM_LENGTH));
            msgBuf.append(" ");
            msgBuf.append(errorMessage);
            msgBuf.append(LINE_FEED_CODE);
        }
        String message = msgBuf.toString();

        // 10.4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (template == null) {
            throw new S21AbendException(NLBM1065E);
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);
        if (!ZYPCommonFunc.hasValue(currentTime)) {
            throw new S21AbendException(NLBM1065E);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BUSINESS_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        // 10.5 Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        this.ediTpCdList.clear();
        this.proNoList.clear();
        this.errMsgCdList.clear();
    }

    /**
     * bizErrorProcess
     * @param proNum
     * @param ediTpCd
     * @param messageCd
     */
    private void bizErrorProcess(String proNum, String ediTpCd, String messageCd) {

        S21InfoLogOutput.println(messageCd);
        this.proNoList.add(nullToEmpty(proNum));
        this.ediTpCdList.add(nullToEmpty(ediTpCd));
        this.errMsgCdList.add(messageCd);
        errMsgCdForAddr = messageCd;
    }

    /**
     * checkHHMISS
     * @param str
     * @return
     */
    private boolean checkHHMISS(String str) {
        if (str.length() != LEN_TM) {
            return false;
        }
        try {
            int hh = Integer.parseInt(str.substring(POS_HH_ST, POS_HH_ED));
            int mm = Integer.parseInt(str.substring(POS_MM_ST, POS_MM_ED));
            int ss = Integer.parseInt(str.substring(POS_SS_ST, POS_SS_ED));

            if (hh < 0 || hh > MAX_NUM_HH) {
                return false;
            }
            if (mm < 0 || mm > MAX_NUM_MM) {
                return false;
            }
            if (ss < 0 || ss > MAX_NUM_SS) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * nullToEmpty
     * @param str
     * @return
     */
    private String nullToEmpty(String str) {

        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

}
