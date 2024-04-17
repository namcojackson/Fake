/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB119001;

import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ACTL_SHIP_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.BASE_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.BOL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.CREATE;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.CUST_ISS_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_BOL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_CRAT_RWS_PROC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_CSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_IMPT_PROC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_PRIM_PKG_UOM_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_PRIM_SPLY_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_ROSS_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_SHIP_TO_ADDR_TP_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_VND_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DB_PARAM_VND_XREF_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DEAL_CCY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.DLR_WH_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ETA_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.INSTL_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_ADDR_TO_GRP;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_FROM_ADDR_GRP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_TEMPLATE_BATCH_ID_KEY;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_TEMPLATE_ERR_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_TEMPLATE_ERR_MESSAGE_KEY;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MAIL_TEMP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MDL_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MDSE_CD_RSI;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MSG_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MSG_ROSS_SHIP_HDR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.MSG_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.NPAM1003E;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.NPAM1007E;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.NPAM1010E;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.NPAM1341E;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.NPZM0043E;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.NPZM0045E;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.PDD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.PRCH_GRP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.PROD_MDL_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.PRO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.RCPO_POSN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ROSS_SHIP_HDR;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ROSS_SHIP_HDR_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ROSS_SHIP_ITEM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ROSS_SHIP_ITEM_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ROSS_SHIP_SER;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.RTL_DIV_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_TO_ADDR_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_TO_CTY_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_TO_POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_TO_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHIP_UNIT_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SO_CRAT_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SO_NUM_RSI;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SO_SLP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SO_SLP_NUM_RSI;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SUBMIT;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.SVC_DLR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.VAR_CHAR_CONST_PR_CRAT_SYSTEM_USER;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.VAR_CHAR_CONST_ROSS_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.VND_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.VND_SHIP_TO_XREF;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.V_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB119001.constant.NPAB119001Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ROSS_SHIP_HDRTMsg;
import business.db.ROSS_SHIP_ITEMTMsg;
import business.db.ROSS_SHIP_SERTMsg;
import business.parts.NPZC103001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHIP_TO_ADDR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_XREF_TP;
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

/**
 *<pre>
 * TPC09 PR Interface Import Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2016   CITS            K.Ogino         Create          N/A
 * 06/20/2016   CITS            K.Ogino         Update          QC#10433
 * 01/25/2017   CITS            K.Ogino         Update          QC#11314
 * 12/13/2017   CITS            K.Ogino         Update          QC#22275
 *</pre>
 */
public class NPAB119001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** normal count */
    private int normalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** error message List */
    private List<String> errorInfoList = new ArrayList<String>();

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** prCrtSystemUser */
    private String prCrtSystemUser;

    /** rossVndCds */
    private String[] rossVndCds = null;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NPAB119001().executeBatch(NPAB119001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // S21SsmBatchClient
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_GLOBAL_COMPANY_CODE });
        }

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_SALES_DATE });
        }

        // prCrtSystemUser
        this.prCrtSystemUser = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_PR_CRAT_SYSTEM_USER, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.prCrtSystemUser)) {
            throw new S21AbendException(NPAM1010E, new String[] {VAR_CHAR_CONST_PR_CRAT_SYSTEM_USER });
        }

        // rossVndCds
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_ROSS_VND_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constValue)) {
               rossVndCds = constValue.split(",");
        } else {
            throw new S21AbendException(NPAM1010E, new String[] {VAR_CHAR_CONST_ROSS_VND_CD });
        }
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            // Create ROSS Data
            result = createRossShipItem();
            if (result == false) {
                return;
            }
            result = createRossShipHdr();
            if (result == false) {
                return;
            }
            result = createRossShipSer();
            if (result == false) {
                return;
            }
            // Update ROSS_SHIP_ITEM
            result = updateRsiImptProcCd();
            if (result == false) {
                return;
            }
            commit();

            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_CRAT_RWS_PROC_CD, ZYPConstant.FLG_OFF_0);
            ps = ssmLlcClient.createPreparedStatement("findRossShipHdr", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                BigDecimal rossShipHdrPk = rs.getBigDecimal(ROSS_SHIP_HDR_PK);
                String dlrWhLocCd = rs.getString(DLR_WH_LOC_CD);
                String soNum = rs.getString(SO_NUM);
                String etaDt = rs.getString(ETA_DT);
                String carrCd = rs.getString(CARR_CD);
                // QC#22275
                String bolNum = rs.getString(BOL_NUM);
                String proNum = rs.getString(PRO_NUM);

                // Create PR Data
                result = mainProcess(rossShipHdrPk, dlrWhLocCd, soNum, etaDt, carrCd, bolNum, proNum);
                if (result) {
                    normalCount++;
                    commit();
                } else {
                    errorCount++;
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        if (hasValueList(this.errorInfoList)) {
            // send error mail
            postErrorMail();
        }

        if (0 < errorCount) {
            termCd = TERM_CD.WARNING_END;
        }
        setTermState(termCd, normalCount, errorCount);
    }

    // ****************************************************************
    // S21SsmBatchClient DB Access Method
    // ****************************************************************
    private Map<String, String> queryObject(String statementId, Map<String, Object> ssmParam) {
        return (Map<String, String>) ssmBatchClient.queryObject(statementId, ssmParam);
    }

    // ****************************************************************
    // Main Process
    // ****************************************************************
    private boolean mainProcess(BigDecimal rossShipHdrPk, String dlrWhLocCd, String soNum, String etaDt, String carrCd, String bolNum, String proNum) {
        writeStartLogLn("mainProcess", String.format(MSG_ROSS_SHIP_HDR_PK, rossShipHdrPk));

        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            // Validation VND_SHIP_TO_XREF
            Map<String, Object> xrefSsmParam = new HashMap<String, Object>();
            xrefSsmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            xrefSsmParam.put(DB_PARAM_VND_XREF_TP_CD, VND_XREF_TP.CUSA_TO_CSA);
            xrefSsmParam.put(DB_PARAM_VND_SHIP_TO_CUST_CD, dlrWhLocCd);
            xrefSsmParam.put(DB_PARAM_SALES_DATE, this.slsDt);

            Map<String, String> xrefMap = queryObject("findVndShipToXref", xrefSsmParam);
            if (xrefMap == null) {
                String errMsgText = S21MessageFunc.clspGetMessage(NPAM1007E, new String[] {VND_SHIP_TO_XREF, VND_SHIP_TO_CUST_CD, dlrWhLocCd });
                writeLogLn(errMsgText);
                this.errorInfoList.add(errMsgText);
                return false;
            }

            // findRossShipItemSer
            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_SO_NUM, soNum);
            ssmParam.put(DB_PARAM_PRIM_SPLY_FLG, ZYPConstant.FLG_ON_Y);
            ssmParam.put(DB_PARAM_PRIM_PKG_UOM_FLG, ZYPConstant.FLG_ON_Y);
            ssmParam.put(DB_PARAM_SALES_DATE, this.slsDt);
            ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put(DB_PARAM_ROSS_VND_CD, rossVndCds[0]);
            // QC#22275
            ssmParam.put(DB_PARAM_BOL_NUM, bolNum);
            ssmParam.put(DB_PARAM_PRO_NUM, proNum);
            ps = ssmLlcClient.createPreparedStatement("findRossShipItemSer", ssmParam, execParam);
            rs = ps.executeQuery();

            int prchLineNum = 1;
            int prchReqInfoCnt = 0;
            int serNumCnt = 0;
            String nonSerItemCd = "";

            // PR Update API
            NPZC103001PMsg pMsg = new NPZC103001PMsg();

            // set PR Update API Parameter
            if (rs.next()) {
                // Header
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, CREATE);
                ZYPEZDItemValueSetter.setValue(pMsg.eventId, SUBMIT);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqRecTpCd, PRCH_REQ_REC_TP.PO_REQUISITION);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqTpCd, PRCH_REQ_TP.ROSS);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqCratByPsnCd, this.prCrtSystemUser);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqRqstByPsnCd, this.prCrtSystemUser);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.ROSS);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlDt, this.slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.prchReqApvlByPsnCd, this.prCrtSystemUser);
                if (!ZYPCommonFunc.hasValue(pMsg.prchGrpCd)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, rs.getString(PRCH_GRP_CD));
                }
                if (!ZYPCommonFunc.hasValue(pMsg.lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, rs.getString(LINE_BIZ_TP_CD));
                }
                do {
                    // Vendor Code Check
                    if (!ZYPCommonFunc.hasValue(rs.getString(V_VND_CD))) {
                        String errMsgText = S21MessageFunc.clspGetMessage(NPZM0043E, null);
                        writeLogLn(errMsgText);
                        this.errorInfoList.add(errMsgText);
                        return false;
                    }
                    // Merchandise Code Check
                    if (!ZYPCommonFunc.hasValue(rs.getString(MDSE_CD))) {
                        String errMsgText = S21MessageFunc.clspGetMessage(NPZM0045E, null);
                        writeLogLn(errMsgText);
                        this.errorInfoList.add(errMsgText);
                        return false;
                    }
                    // Non Serial IB Item Duplicate Line Check
                    if (nonSerItemCd.equals(rs.getString(MDSE_CD))) {
                        continue;
                    }

                    // prchReqInfo
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqLineNum, String.format("%03d", prchLineNum));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqLineSubNum, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).xxDtlLineNum, String.format("%03d", prchLineNum));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqLineTpCd, PRCH_REQ_LINE_TP.GOODS);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).rqstRcvDt, etaDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).soNum, rs.getString(SO_NUM_RSI));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).poSchdRelDt, this.slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).carrCd, carrCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqFrzFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).procrTpCd, PROCR_TP.SUPPLIER);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).destInvtyLocCd, (String) xrefMap.get(INVTY_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prntVndCd, rs.getString(PRNT_VND_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).vndCd, rossVndCds[0]);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).poMdseCmpsnTpCd, PO_MDSE_CMPSN_TP.REGULAR);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).mdseCd, rs.getString(MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).aslMdseCd, rs.getString(MDSE_CD_RSI));
                    if (ZYPCommonFunc.hasValue(rs.getString(SER_NUM))) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqQty, BigDecimal.ONE);
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqDispQty, BigDecimal.ONE);
                    } else {
                        nonSerItemCd = rs.getString(MDSE_CD);
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqQty, rs.getBigDecimal(SHIP_QTY));
                        ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqDispQty, rs.getBigDecimal(SHIP_QTY));
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).ordQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqDsplUomCd, rs.getString(BASE_PKG_UOM_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqRelQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).ropQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).minOrdQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).incrOrdQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).maxInvtyQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).curInvtyQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).curInvtyAvalQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).schdInbdQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).schdOtbdQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).proNum, rs.getString(PRO_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).shipDtTmTs, rs.getString(ACTL_SHIP_DT));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).shipQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).rwsPutAwayQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).backToTechQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).aslUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).entDealNetUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).entFuncNetUnitPrcAmt, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).ccyCd, rs.getString(DEAL_CCY_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).exchRate, BigDecimal.ONE);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).trxRefNum, rs.getString(SO_NUM_RSI));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).trxRefLineNum, rs.getString(SO_SLP_NUM_RSI));
                    ZYPEZDItemValueSetter.setValue(pMsg.prchReqInfo.no(prchReqInfoCnt).prchReqLineCmntTxt, rs.getString(SER_NUM));
                    // serNumInfo
                    ZYPEZDItemValueSetter.setValue(pMsg.serNumInfo.no(serNumCnt).serNum, rs.getString(SER_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.serNumInfo.no(prchReqInfoCnt).xxDtlLineNum, String.format("%03d", prchLineNum));
                    prchLineNum++;
                    prchReqInfoCnt++;
                    serNumCnt++;
                } while (rs.next());

                // set Valid Count
                pMsg.prchReqInfo.setValidCount(prchReqInfoCnt);
                pMsg.serNumInfo.setValidCount(serNumCnt);

                // Execute PR Update API
                NPZC103001 api = new NPZC103001();
                api.execute(pMsg, ONBATCH_TYPE.BATCH);
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                    for (String xxMsgId : xxMsgIdList) {
                        String errMsgText = S21MessageFunc.clspGetMessage(xxMsgId, null);
                        writeLogLn(errMsgText);
                        this.errorInfoList.add(errMsgText);
                        result = false;
                    }
                } else {
                    result = true;
                }

                // update ROSS_SHIP_HDR
                ROSS_SHIP_HDRTMsg tMsg = new ROSS_SHIP_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossShipHdrPk, rossShipHdrPk);

                tMsg = (ROSS_SHIP_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
                if (tMsg == null) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {ROSS_SHIP_HDR, ROSS_SHIP_HDR_PK, String.valueOf(rossShipHdrPk) });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(tMsg.cratRwsProcCd, ZYPConstant.FLG_ON_1);

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {ROSS_SHIP_HDR, ROSS_SHIP_HDR_PK, String.valueOf(rossShipHdrPk) });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
            writeEndLogLn("mainProcess", String.format(MSG_ROSS_SHIP_HDR_PK, rossShipHdrPk));
        }
        return result;
    }

    // ****************************************************************
    // Create ROSS_SHIP_ITEM
    // ****************************************************************
    private boolean createRossShipItem() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_CUSA_GLBL_CMPY_CD, GLBL_CMPY.CANON_USA_INC);
            ssmParam.put(DB_PARAM_SALES_DATE, this.slsDt);
            ssmParam.put(DB_PARAM_SHIP_TO_ADDR_TP_CD_LIST, new String[] {SHIP_TO_ADDR_TP.INSTALL_LOCATION, SHIP_TO_ADDR_TP.OTHER });
            ps = ssmLlcClient.createPreparedStatement("findCusaShipItem", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                ROSS_SHIP_ITEMTMsg tMsg = new ROSS_SHIP_ITEMTMsg();
                BigDecimal rossShipItemPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_SHIP_ITEM_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossShipItemPk, rossShipItemPk);
                ZYPEZDItemValueSetter.setValue(tMsg.shpgPlnNum, rs.getString(SHPG_PLN_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.soNum, rs.getString(SO_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.soSlpNum, rs.getString(SO_SLP_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.soCratDt, rs.getString(SO_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, rs.getString(MDL_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.shipQty, rs.getBigDecimal(SHIP_QTY));
                ZYPEZDItemValueSetter.setValue(tMsg.bolNum, rs.getString(BOL_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.proNum, rs.getString(PRO_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.shipToAddrTpCd, rs.getString(SHIP_TO_ADDR_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.actlShipDt, rs.getString(ACTL_SHIP_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.rcpoPosnNum, rs.getString(RCPO_POSN_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.prodMdlTpCd, rs.getString(PROD_MDL_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.imptProcCd, ZYPConstant.FLG_OFF_0);

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1341E, new String[] {ROSS_SHIP_ITEM });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return true;
    }

    // ****************************************************************
    // Create ROSS_SHIP_HDR
    // ****************************************************************
    private boolean createRossShipHdr() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CUSA_GLBL_CMPY_CD, GLBL_CMPY.CANON_USA_INC);
            ssmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_IMPT_PROC_CD, ZYPConstant.FLG_OFF_0);
            ps = ssmLlcClient.createPreparedStatement("findCusaShipHdr", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                ROSS_SHIP_HDRTMsg tMsg = new ROSS_SHIP_HDRTMsg();
                BigDecimal rossShipHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_SHIP_HDR_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossShipHdrPk, rossShipHdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.soNum, rs.getString(SO_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.soCratDt, rs.getString(SO_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, rs.getString(CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, rs.getString(CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCustCd, rs.getString(SVC_DLR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.istlLocNm, rs.getString(INSTL_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlDivCd, rs.getString(RTL_DIV_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.dlrWhLocCd, rs.getString(DLR_WH_LOC_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.etaDt, rs.getString(PDD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.carrCd, rs.getString(VND_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, rs.getString(SHIP_TO_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, rs.getString(SHIP_TO_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, rs.getString(SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, rs.getString(SHIP_TO_POST_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.cratRwsProcCd, ZYPConstant.FLG_OFF_0);
                // QC#22275
                ZYPEZDItemValueSetter.setValue(tMsg.bolNum, rs.getString(BOL_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.proNum, rs.getString(PRO_NUM));

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1341E, new String[] {ROSS_SHIP_HDR });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return true;
    }

    // ****************************************************************
    // Create ROSS_SHIP_SER
    // ****************************************************************
    private boolean createRossShipSer() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CUSA_GLBL_CMPY_CD, GLBL_CMPY.CANON_USA_INC);
            ssmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_IMPT_PROC_CD, ZYPConstant.FLG_OFF_0);
            ps = ssmLlcClient.createPreparedStatement("findCusaShipSer", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                ROSS_SHIP_SERTMsg tMsg = new ROSS_SHIP_SERTMsg();
                BigDecimal rossShipSerPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_SHIP_SER_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossShipSerPk, rossShipSerPk);
                ZYPEZDItemValueSetter.setValue(tMsg.shipUnitPk, rs.getBigDecimal(SHIP_UNIT_PK));
                ZYPEZDItemValueSetter.setValue(tMsg.shpgPlnNum, rs.getString(SHPG_PLN_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.soNum, rs.getString(SO_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.soSlpNum, rs.getString(SO_SLP_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
                // QC#22275
                ZYPEZDItemValueSetter.setValue(tMsg.bolNum, rs.getString(BOL_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.proNum, rs.getString(PRO_NUM));

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1341E, new String[] {ROSS_SHIP_SER });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return true;
    }

    // ****************************************************************
    // UPDATE ROSS_SHIP_ITEM.IMPT_PROC_CD
    // ****************************************************************
    private boolean updateRsiImptProcCd() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // S21SsmLowLevelCodintClient Setup
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            execParam.setMaxRows(0);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CSA_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(DB_PARAM_IMPT_PROC_CD, ZYPConstant.FLG_OFF_0);
            ps = ssmLlcClient.createPreparedStatement("findRossShipItem", ssmParam, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                BigDecimal rossShipItemPk = rs.getBigDecimal(ROSS_SHIP_ITEM_PK);
                // update ROSS_SHIP_ITEM
                ROSS_SHIP_ITEMTMsg tMsg = new ROSS_SHIP_ITEMTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossShipItemPk, rossShipItemPk);

                tMsg = (ROSS_SHIP_ITEMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
                if (tMsg == null) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {ROSS_SHIP_ITEM, ROSS_SHIP_ITEM_PK, String.valueOf(rossShipItemPk) });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(tMsg.imptProcCd, ZYPConstant.FLG_ON_1);

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsgText = S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {ROSS_SHIP_ITEM, ROSS_SHIP_ITEM_PK, String.valueOf(rossShipItemPk) });
                    writeLogLn(errMsgText);
                    this.errorInfoList.add(errMsgText);
                    return false;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        return true;
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
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);
        template.setTemplateParameter(MAIL_TEMPLATE_BATCH_ID_KEY, this.getClass().getSimpleName());
        template.setTemplateParameter(MAIL_TEMPLATE_ERR_DATE, errDate);
        String errMsg = getMailBodyErrMsg();
        template.setTemplateParameter(MAIL_TEMPLATE_ERR_MESSAGE_KEY, errMsg);

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
            sb.append("     ");
        }

        return sb.toString();
    }

    // ****************************************************************
    // Data Check Method
    // ****************************************************************
    private static <T extends List< ? >> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    // ****************************************************************
    // Output Log Method
    // ****************************************************************
    private static void writeStartLogLn(String methodNm, String msg) {
        writeLogLn("[START] %s(%s)", methodNm, msg);
    }

    private static void writeEndLogLn(String methodNm, String msg) {
        writeLogLn("[END] %s(%s)\r\n", methodNm, msg);
    }

    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s]", BIZ_APP_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }
}
