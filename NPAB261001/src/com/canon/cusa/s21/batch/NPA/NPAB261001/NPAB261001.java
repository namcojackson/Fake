/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB261001;

import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.ATT_FILE_NAME;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_DTL_PO_ACK_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_CONF_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_DTL_PO_ACK_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_DTL_RDD_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_DTL_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_HDR_CRAT_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_HDR_CRAT_TM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_HDR_VND_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_LINE_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_LINE_STS_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_PO_ACK_ORD_PRC_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_SHIP_TO_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COLUMN_VND_PO_ACK_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.CRLF;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.CSV_HEADER_EDI_ACK_PO_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.CSV_HEADER_EDI_ACK_PO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.CSV_HEADER_EDI_ACK_VND_ITEM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.CSV_HEADER_ERR_MSG;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.EXT_CSV;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.IDX_3;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.INTFC_ID_DIETZGEN;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.INTFC_ID_HYTEC;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.INTFC_ID_TST_IMPRESO;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.INTFC_ID_ATRIX;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_BASE_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_BAT_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_EDI_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_PO_ACK_DTL_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_VND_SHIP_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.KEY_VND_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_GROUP_ID_TO_NPAB261001;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_GROUP_ID_TO_NPAB261002;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_ITEM_BATCHID;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_ITEM_ERR_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_ITEM_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_TEMPLATE_ID_NPAB2610M001;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_TEMPLATE_ID_NPAB2610M002;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_TO_KEY_1;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAIL_TO_KEY_2;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.NPAM1328E;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.NPAM1537E;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.NPAM1538E;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.NPAM1539E;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.NPAM1557E;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.NPAM1642E;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_DELY_LEAD_AOT;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_MDSE_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_MDSE_STORE_PKG;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_PO_ACK_INTFC_LST;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_PO_INFO;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_SPLY_ITEM_NUM_FROM_ASL;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.SQL_STMT_ID_GET_VND_UOM;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.TBL_NPAI0410_01;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.TBL_NPAI0410_04;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.TBL_PO_ACK_DTL_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.TBL_PO_ACK_HDR_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.VAL_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.VAL_DEF_CCY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.VAL_EMPTY;
import static com.canon.cusa.s21.batch.NPA.NPAB261001.constant.NPAB261001Constant.VAL_ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.PO_ACK_DTL_WRKTMsg;
import business.db.PO_ACK_HDR_WRKTMsg;
import business.db.PO_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PO_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Receiving PO ACK WRK from EDI Vendor<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/07/2018   CITS            M.Naito         Create          QC#26964,26965
 * 11/12/2018   CITS            M.Naito         Update          QC#29050
 * 02/25/2019   Fujitsu         S.Takami        Update          QC#30453
 * 09/20/2020   CITS            A.Marte         Update          QC#56898
 * 03/08/2023   Hitachi         E.Watabe        Update          QC#61128
 * 09/25/2023   Hitachi         G.Quan          Update          QC#61608
 * </pre>
 */
public class NPAB261001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId = null;

    /** Batch Process Date */
    private String batDt;

    /** Commit Number */
    private int commitCount;

    /** total commit count */
    private int totalCommitCount;

    /** total error count */
    private int totalErrorCount;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Csv Info */
    private StringBuilder errCsvInfo = new StringBuilder();

    /** Vendor Code */
    private String vndCd;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NPAM1539E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NPAM1539E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Get Batch Process Date
        this.batDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {
            this.commitCount = MAX_COMMIT_NUMBER;
        }
        this.totalCommitCount = 0;

        // Get vendor code from interfaceID
        String vndSysTpCd = null;
        if (INTFC_ID_TST_IMPRESO.equals(this.interfaceId)) {
            vndSysTpCd = VND_SYS_TP.TST_IMPRESO;
        } else if ((INTFC_ID_DIETZGEN.equals(this.interfaceId))) {
            vndSysTpCd = VND_SYS_TP.DIETZGEN;
        // START 2023/03/08 E.Watabe [QC#61128,ADD]
        } else if (INTFC_ID_HYTEC.equals(this.interfaceId)) {
            vndSysTpCd = VND_SYS_TP.HYTEC;
        // END 2023/03/08 E.Watabe [QC#61128,ADD]
        // START 2023/09/25 G.Quan [QC#61608, ADD]
        } else if (INTFC_ID_ATRIX.equals(this.interfaceId)) {
            vndSysTpCd = VND_SYS_TP.ATRIX;
        }
        // END 2023/09/25 G.Quan [QC#61608, ADD]

        if (!ZYPCommonFunc.hasValue(vndSysTpCd)) {
            throw new S21AbendException(NPAM1328E, new String[] {"VND_SYS_TP_CD" });
        }
        Map<String, Object> ssmParamVndCd = new HashMap<String, Object>();
        ssmParamVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamVndCd.put(KEY_VND_SYS_TP_CD, vndSysTpCd);
        this.vndCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_VND_CD, ssmParamVndCd);
        if (!ZYPCommonFunc.hasValue(this.vndCd)) {
            throw new S21AbendException(NPAM1537E, new String[] {"VND_CD", "RCV_ASN_VND", "GLBL_CMPY_CD = " + this.glblCmpyCd + COMMA + " VND_SYS_TP_CD = " + vndSysTpCd });
        }

    }

    /**
     * Main process
     */
    @Override
    protected void mainRoutine() {

        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (BigDecimal tranId : tranIds) {

            PreparedStatement prdStmtPoAckIf = null;
            ResultSet rsPoAckIf = null;

            int insertCount = 0;
            try {
                // Search for data to be processed from
                // NPAI0410_01(Header), NPAI0410_04(Detail)
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
                queryParam.put(KEY_INTERFACE_ID, interfaceId);
                queryParam.put(KEY_TRANSACTION_ID, tranId);
                prdStmtPoAckIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_PO_ACK_INTFC_LST, queryParam, execParam);
                rsPoAckIf = prdStmtPoAckIf.executeQuery();

                List<PO_ACK_HDR_WRKTMsg> poAckHdrLst = new ArrayList<PO_ACK_HDR_WRKTMsg>();
                List<PO_ACK_DTL_WRKTMsg> poAckDtlLst = new ArrayList<PO_ACK_DTL_WRKTMsg>();

                String curPoNum = VAL_EMPTY;
                String lastPoNum = VAL_EMPTY;
                BigDecimal poAckHdrWrkPk = null;
                BigDecimal poAckHdrPk = null;
                while (rsPoAckIf.next()) {

                    curPoNum = rsPoAckIf.getString(COLUMN_PO_ACK_ORD_NUM);

                    // Insert HDR/DTL when PO_NUM breaks.
                    if (!curPoNum.equals(lastPoNum)) {
                        // Insert HDR/DTL
                        if (!poAckHdrLst.isEmpty()) {
                            insertPoAckHdrWrkData(poAckHdrLst);
                            insertCount += insertPoAckDtlWrkData(poAckDtlLst);
                        }
                        // Clear values
                        poAckHdrWrkPk = null;
                        poAckHdrPk = null;
                        poAckHdrLst.clear();
                        poAckDtlLst.clear();
                    }
                    if (!ZYPCommonFunc.hasValue(rsPoAckIf.getString(COLUMN_DTL_PO_ACK_ORD_NUM))) {
                        addErrCsvInfo(rsPoAckIf, S21MessageFunc.clspGetMessage(NPAM1537E, new String[] {rsPoAckIf.getString(COLUMN_PO_ACK_ORD_NUM), TBL_NPAI0410_04, COLUMN_PO_ACK_ORD_NUM }));
                        this.totalErrorCount++;
                        continue;
                    }

                    // mandatory and format check
                    if (!validateEdiPoAckData(rsPoAckIf)) {
                        this.totalErrorCount++;
                        continue;
                    }

                    // Initialize header values when new PO_NUM.
                    if (poAckHdrWrkPk == null) {
                        poAckHdrWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_ACK_HDR_WRK_SQ);
                        poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_ACK_HDR_SQ);
                        poAckHdrLst.add(setPoAckHdrWrkData(rsPoAckIf, poAckHdrWrkPk, poAckHdrPk));
                    }

                    // Add details
                    poAckDtlLst.add(setPoAckDtlWrkData(rsPoAckIf, poAckHdrWrkPk));

                    // Store current PO_NUM as last PO_NUM
                    lastPoNum = curPoNum;
                }

                // Insert HDR/DTL at last if still have data.
                if (!poAckHdrLst.isEmpty()) {
                    insertPoAckHdrWrkData(poAckHdrLst);
                    insertCount += insertPoAckDtlWrkData(poAckDtlLst);
                }

            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(prdStmtPoAckIf, rsPoAckIf);
            }

            trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();
            this.totalCommitCount += insertCount;
        }

        if (errCsvInfo.toString().getBytes().length > 0) {
            StringBuilder csvHeader = new StringBuilder();
            csvHeader.append(CSV_HEADER_EDI_ACK_PO_NUM).append(COMMA).append(CSV_HEADER_EDI_ACK_PO_LINE_NUM).append(COMMA).append(CSV_HEADER_EDI_ACK_VND_ITEM_CD).append(COMMA).append(CSV_HEADER_ERR_MSG).append(CRLF);
            errCsvInfo.insert(0, csvHeader.toString());
            sendMailForAppError();
            commit();
        }

    }

    private boolean validateEdiPoAckData(ResultSet rs) throws SQLException {

        // PO_ACK_HDR_WRK
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_ORD_NUM))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_HDR_WRK, TBL_NPAI0410_01, COLUMN_PO_ACK_ORD_NUM, null }));
            return false;
        }
        // PO_ACK_DTL_WRK
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI0410_04, COLUMN_PO_ACK_ORD_DTL_LINE_NUM, null }));
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_DTL_PO_ACK_CD))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI0410_04, COLUMN_PO_ACK_DTL_PO_ACK_CD, null }));
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_CONF_QTY))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI0410_04, COLUMN_PO_ACK_CONF_QTY, null }));
            return false;
        }
        // START 2019/02/25 S.Takami [QC#30453,ADD]
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_MDSE_CD))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1642E, new String[] {INTFC_ID_TST_IMPRESO}));
            return false;
        }
        // END 2019/02/25 S.Takami [QC#30453,ADD]
        return true;
    }

    private void addErrCsvInfo(ResultSet rs, String message) throws SQLException {
        // START 2019/02/25 S.Takami [QC#30453,ADD]
        boolean hasIfMdseCd = ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_MDSE_CD));
        // End 2019/02/25 S.Takami [QC#30453,ADD]
        if (hasIfMdseCd) {
            String vndMdseCd = getSplyItemNumFromAsl(rs.getString(COLUMN_PO_ACK_MDSE_CD));
            errCsvInfo.append(rs.getString(COLUMN_PO_ACK_ORD_NUM)).append(COMMA).append(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM)).append(COMMA).append(vndMdseCd).append(COMMA).append(message).append(CRLF);
        } else {
            errCsvInfo.append(rs.getString(COLUMN_PO_ACK_ORD_NUM)).append(COMMA).append("").append(COMMA).append("").append(COMMA).append(message).append(CRLF);
        }
        S21InfoLogOutput.println(message);
    }

    /**
     * setPoAckHdrWrkData
     * @param rs ResultSet
     * @return PO_ACK_HDR_WRKTMsg
     */
    private PO_ACK_HDR_WRKTMsg setPoAckHdrWrkData(ResultSet rs, BigDecimal poAckHdrWrkPk, BigDecimal poAckHdrPk) throws SQLException {
        PO_ACK_HDR_WRKTMsg poAckHdrWrk = new PO_ACK_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.poAckHdrWrkPk, poAckHdrWrkPk);
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ediPoOrdNum, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ackEdiProcStsCd, ACK_EDI_PROC_STS.SAVED);

        String ackCratDate = rs.getString(COLUMN_PO_ACK_HDR_CRAT_DT) + rs.getString(COLUMN_PO_ACK_HDR_CRAT_TM);
        if (ZYPCommonFunc.hasValue(ackCratDate)) {
            int degit = poAckHdrWrk.getAttr("ediRcvDateTs").getDigit();
            if (degit == ackCratDate.length()) {
                ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ediRcvDateTs, ackCratDate);
            } else if (degit > ackCratDate.length()) {
                ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ediRcvDateTs, ZYPCommonFunc.rightPad(ackCratDate, degit, "0"));
            } else {
                ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ediRcvDateTs, ackCratDate.substring(0,degit));
            }
        }
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.itrlIntfcId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.vndCpoOrdNum, rs.getString(COLUMN_PO_ACK_HDR_VND_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.poAckHdrPk, poAckHdrPk);
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.poAckNum, getPoAckNum(rs));

        return poAckHdrWrk;
    }

    /**
     * setPoAckDtlWrkData
     * @param rs ResultSet
     * @return PO_ACK_DTL_WRKTMsg
     */
    private PO_ACK_DTL_WRKTMsg setPoAckDtlWrkData(ResultSet rs, BigDecimal poAckHdrWrkPk) throws SQLException {
        PO_ACK_DTL_WRKTMsg poAckDtlWrk = new PO_ACK_DTL_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.glblCmpyCd, getGlobalCompanyCode());

        BigDecimal poAckDtlWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_ACK_DTL_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poAckDtlWrkPk, poAckDtlWrkPk);

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poOrdDtlSubLineNum, ZYPCommonFunc.leftPad(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM), IDX_3, VAL_ZERO));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.shpgPlnDplyLineNum, ZYPCommonFunc.leftPad(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM), IDX_3, VAL_ZERO));
        // START 2019/02/25 S.Takami [QC#30453,DEL]
//        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poOrdNum, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        // End 2019/02/25 S.Takami [QC#30453,DEL]
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poAckLineStsCd, rs.getString(COLUMN_PO_ACK_LINE_STS_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.vndPoAckLineStsTxt, rs.getString(COLUMN_PO_ACK_LINE_STS_NM));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.mdseCd, rs.getString(COLUMN_PO_ACK_MDSE_CD));
        String mdseNm = getMdseNm(rs);
        if (ZYPCommonFunc.hasValue(mdseNm)) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.mdseNm, mdseNm);
        }
        String vndMdseCd = getSplyItemNumFromAsl(rs.getString(COLUMN_PO_ACK_MDSE_CD));
        if (ZYPCommonFunc.hasValue(vndMdseCd)) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.trdPtnrSkuCd, vndMdseCd);
        }
        // START 2018/11/12 M.Naito [QC#29050,MOD]
//        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ordQty, new BigDecimal(rs.getString(COLUMN_PO_ACK_CONF_QTY)));
        // END 2018/11/12 M.Naito [QC#29050,MOD]
        if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_ORD_PRC_NUM))) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.thisMthFobCostAmt, new BigDecimal(rs.getString(COLUMN_PO_ACK_ORD_PRC_NUM)));
        }
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ccyCd, VAL_DEF_CCY_CD);

        // START 2018/11/12 M.Naito [QC#29050,MOD]
//      ZYPEZDItemValueSetter.setValue(poAckDtlWrk.uomCd, getVndUom(rs.getString(COLUMN_PO_ACK_DTL_UOM_CD)));
        BigDecimal ordQty = new BigDecimal(rs.getString(COLUMN_PO_ACK_CONF_QTY));
        if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_DTL_UOM_CD))) {
            String uomCd = getUomCdFromPo(rs);
            String vndUomCd = rs.getString(COLUMN_PO_ACK_DTL_UOM_CD);
            if (INTFC_ID_TST_IMPRESO.equals(this.interfaceId)) {
                vndUomCd = getVndUomCd(vndUomCd, uomCd);
                if (ZYPCommonFunc.hasValue(uomCd) && !uomCd.equals(vndUomCd)) {
                    BigDecimal inEachQty = getInEachQty(uomCd, vndUomCd, rs.getString(COLUMN_PO_ACK_MDSE_CD));
                    if (ZYPCommonFunc.hasValue(ordQty) && ZYPCommonFunc.hasValue(inEachQty) && !BigDecimal.ZERO.equals(inEachQty)) {
                        ordQty = inEachQty.multiply(ordQty);
                        vndUomCd = uomCd;
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.uomCd, vndUomCd);
        }
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ordQty, ordQty);
        // END 2018/11/12 M.Naito [QC#29050,MOD]
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etdDt, rs.getString(COLUMN_PO_ACK_DTL_RDD_DT));

        // Lead Time
        if (VND_PO_ACK_STS.SHIPPED.equals(rs.getString(COLUMN_VND_PO_ACK_STS_CD))) {
            BigDecimal leadTimeAot = getVndShipLt(rs);
            if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_PO_ACK_DTL_RDD_DT))) {
                ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etaDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, rs.getString(COLUMN_PO_ACK_DTL_RDD_DT), leadTimeAot.intValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etaDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, this.batDt, leadTimeAot.intValue()));
            }
        }
        if (ZYPCommonFunc.hasValue(vndMdseCd)) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.origVndMdseCd, vndMdseCd);
        }
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poAckHdrWrkPk, poAckHdrWrkPk);
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ediPoOrdNum, rs.getString(COLUMN_DTL_PO_ACK_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ediPoOrdDtlLineNum, rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM));

        return poAckDtlWrk;
    }

    /**
     * getMdseNm
     * @param rs ResultSet
     * @return String
     */
    private String getMdseNm(ResultSet rs) throws SQLException {
        String mdseNm = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_PO_ORD_NUM, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        ssmParam.put(KEY_PO_ORD_DTL_LINE_NUM, ZYPCommonFunc.leftPad(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM), IDX_3, VAL_ZERO));
        ssmParam.put(KEY_MDSE_CD, rs.getString(COLUMN_PO_ACK_MDSE_CD));
        mdseNm = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_MDSE_NM, ssmParam);
        return mdseNm;
    }

    /**
     * getVndShipLt
     * @param rs ResultSet
     * @return BigDecimal
     */
    private BigDecimal getVndShipLt(ResultSet rs) throws SQLException {

        BigDecimal ret = BigDecimal.ZERO;

        // get SHIP_TO_ST_CD,SHPG_SVC_LVL_CD from PO
        Map<String, Object> ssmParamPoInfo = new HashMap<String, Object>();
        ssmParamPoInfo.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoInfo.put(KEY_PO_ORD_NUM, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        ssmParamPoInfo.put(KEY_PO_ORD_DTL_LINE_NUM, ZYPCommonFunc.leftPad(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM), IDX_3, VAL_ZERO));
        List<Map<String, Object>> poInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_PO_INFO, ssmParamPoInfo);

        String stCd = null;
        String vndShipMethCd = null;
        if (poInfo != null && !poInfo.isEmpty()) {
            stCd = (String) poInfo.get(0).get(COLUMN_SHIP_TO_ST_CD);
            vndShipMethCd = (String) poInfo.get(0).get(COLUMN_SHPG_SVC_LVL_CD);
        }
        if (!ZYPCommonFunc.hasValue(stCd)) {
            stCd = VAL_AST;
        }
        if (!ZYPCommonFunc.hasValue(vndShipMethCd)) {
            vndShipMethCd = VAL_AST;
        }

        Map<String, Object> ssmParamLtInfo = new HashMap<String, Object>();
        ssmParamLtInfo.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamLtInfo.put(KEY_VND_CD, this.vndCd);
        ssmParamLtInfo.put(KEY_ST_CD, stCd);
        ssmParamLtInfo.put(KEY_VND_SHIP_METH_CD, vndShipMethCd);
        ssmParamLtInfo.put(KEY_AST, VAL_AST);
        BigDecimal delyLeadAot = (BigDecimal) ssmBatchClient.queryObject(SQL_STMT_ID_GET_DELY_LEAD_AOT, ssmParamLtInfo);
        if (ZYPCommonFunc.hasValue(delyLeadAot)) {
            ret = delyLeadAot;
        }
        return ret;
    }

    /**
     * getPoAckNum
     * @param rs ResultSet
     * @return String
     */
    private String getPoAckNum(ResultSet rs) throws SQLException {

        Map<String, Object> ssmParamPoAckHdrWrk = new HashMap<String, Object>();
        ssmParamPoAckHdrWrk.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoAckHdrWrk.put(KEY_EDI_PO_ORD_NUM, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        String poAckNumFroPoAckHdrWrk = (String)ssmBatchClient.queryObject(SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR_WRK, ssmParamPoAckHdrWrk);

        Map<String, Object> ssmParamPoAckHdr = new HashMap<String, Object>();
        ssmParamPoAckHdr.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoAckHdr.put(KEY_PO_ORD_NUM, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        String poAckNumFroPoAckHdr = (String)ssmBatchClient.queryObject(SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR, ssmParamPoAckHdr);

        if (!ZYPCommonFunc.hasValue(poAckNumFroPoAckHdrWrk)) {
            poAckNumFroPoAckHdrWrk = VAL_ZERO;
        }

        if (!ZYPCommonFunc.hasValue(poAckNumFroPoAckHdr)) {
            poAckNumFroPoAckHdr = VAL_ZERO;
        }
        // START 2020/09/02 A.Marte [QC#56898, MOD]
//        if (poAckNumFroPoAckHdrWrk.compareTo(poAckNumFroPoAckHdr) > 0) {
        if ((new BigDecimal(poAckNumFroPoAckHdrWrk)).compareTo(new BigDecimal(poAckNumFroPoAckHdr)) > 0){
        // END 2020/09/02 A.Marte [QC#56898, MOD]
            return String.valueOf(Integer.parseInt(poAckNumFroPoAckHdrWrk) + 1);
        } else {
            return String.valueOf(Integer.parseInt(poAckNumFroPoAckHdr) + 1);
        }
    }


    // START 2018/11/12 M.Naito [QC#29050,MOD]
    /**
     * getVndUomCd
     * @param poAckDtlUomCd String
     * @param uomCd String
     * @return String
     */
    private String getVndUomCd(String poAckDtlUomCd, String uomCd) {

        if (!ZYPCommonFunc.hasValue(uomCd)) {
            return poAckDtlUomCd;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_VND_CD, this.vndCd);
        ssmParam.put(KEY_PO_ACK_DTL_UOM_CD, poAckDtlUomCd);
        ssmParam.put(KEY_PKG_UOM_CD, uomCd);
        String vndUomCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_VND_UOM, ssmParam);
        if (ZYPCommonFunc.hasValue(vndUomCd)) {
            return vndUomCd;
        }
        return poAckDtlUomCd;
    }

    /**
     * getUomCdFromPo
     * @param rs ResultSet
     * @return String
     * @throws SQLException 
     */
    private String getUomCdFromPo(ResultSet rs) throws SQLException {

        PO_DTLTMsg poDtlTmsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        // START 2019/02/25 S.Takami [QC#30453,MOD]
//        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, rs.getString(COLUMN_PO_ACK_ORD_NUM));
        String poAckOrdNum = rs.getString(COLUMN_PO_ACK_ORD_NUM);
        int maxPoOrdNumLen = poDtlTmsg.getAttr("poOrdNum").getDigit();
        if (poAckOrdNum.length() > maxPoOrdNumLen) {
            return "";
        }
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, poAckOrdNum);
        // START 2019/02/25 S.Takami [QC#30453,MOD]
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdDtlLineNum, ZYPCommonFunc.leftPad(rs.getString(COLUMN_PO_ACK_ORD_DTL_LINE_NUM), IDX_3, VAL_ZERO));
        poDtlTmsg = (PO_DTLTMsg) EZDFastTBLAccessor.findByKey(poDtlTmsg);

        String poUomCd = null;
        if (poDtlTmsg != null) {
            poUomCd = poDtlTmsg.poDispUomCd.getValue();
        }
        return poUomCd;
    }

    /**
     * getInEachQty
     * @param uomCd String
     * @param vndUomCd String
     * @param mdseCd String
     * @return BigDecimal
     */
    private BigDecimal getInEachQty(String uomCd, String vndUomCd, String mdseCd) {

        BigDecimal inEachQty = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_MDSE_CD, mdseCd);
        ssmParam.put(KEY_BASE_PKG_UOM_CD, uomCd);
        ssmParam.put(KEY_PKG_UOM_CD, PKG_UOM.EACH);
        BigDecimal targetCnt = (BigDecimal) ssmBatchClient.queryObject(SQL_STMT_ID_GET_MDSE_STORE_PKG, ssmParam);
        if (BigDecimal.ZERO.equals(targetCnt)) {
            return inEachQty;
        }

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("pkgUomCd01", vndUomCd);
        tMsg.setConditionValue("mdseCd01", mdseCd);
        MDSE_STORE_PKGTMsgArray tMsgArray = (MDSE_STORE_PKGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() > 0 && ZYPCommonFunc.hasValue(tMsgArray.no(0).inEachQty.getValue())) {
            inEachQty = tMsgArray.no(0).inEachQty.getValue();
        }
        return inEachQty;
    }
    // END 2018/11/12 M.Naito [QC#29050,MOD]

    /**
     * getSplyItemNumFromAsl
     * @param mdseCd String
     * @return String
     */
    private String getSplyItemNumFromAsl(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_VND_CD, this.vndCd);
        ssmParam.put(KEY_MDSE_CD, mdseCd);
        ssmParam.put(KEY_BAT_DT, this.batDt);
        return (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_SPLY_ITEM_NUM_FROM_ASL, ssmParam);
    }

    /**
     * insertPoAckHdrWrkData
     * @param inMsgLst List<PO_ACK_HDR_WRKTMsg>
     * @return int
     */
    private int insertPoAckHdrWrkData(List<PO_ACK_HDR_WRKTMsg> inMsgLst) {
        PO_ACK_HDR_WRKTMsg[] inMsgArray;
        inMsgArray = new PO_ACK_HDR_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            rollback();
            String errMsg = S21MessageFunc.clspGetMessage(NPAM1557E, new String[] {TBL_PO_ACK_HDR_WRK });
            sendMailForError(errMsg);
            commit();
            throw new S21AbendException(errMsg);
        }

        return insertCount;
    }

    /**
     * insertPoAckDtlWrkData
     * @param inMsgLst List<PO_ACK_DTL_WRKTMsg>
     * @return int
     */
    private int insertPoAckDtlWrkData(List<PO_ACK_DTL_WRKTMsg> inMsgLst) {
        PO_ACK_DTL_WRKTMsg[] inMsgArray;
        inMsgArray = new PO_ACK_DTL_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            rollback();
            String errMsg = S21MessageFunc.clspGetMessage(NPAM1557E, new String[] {TBL_PO_ACK_DTL_WRK });
            sendMailForError(errMsg);
            commit();
            throw new S21AbendException(errMsg);
        }

        return insertCount;
    }

    /**
     * sendMailForError
     * @param message String
     */
    private void sendMailForError(String message) {

        S21MailGroup mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrListFrom = mailGroupFrom.getMailAddress();

        S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO_NPAB261001);
        mailGroupTo.setMailKey1(MAIL_TO_KEY_1);
        List<S21MailAddress> addrListTo = mailGroupTo.getMailAddress();

        // get mail template
        S21MailTemplate mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_NPAB2610M001);
        mailTemplate.setTemplateParameter(MAIL_ITEM_BATCHID, BUSINESS_ID);
        mailTemplate.setTemplateParameter(MAIL_ITEM_ERR_DATE, this.batDt);
        mailTemplate.setTemplateParameter(MAIL_ITEM_MESSAGE, message);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(addrListFrom.get(0));
        mail.setToAddressList(addrListTo);
        mail.setMailTemplate(mailTemplate);

        mail.postMail();
    }

    /**
     * sendMailForAppError
     */
    private void sendMailForAppError() {

        S21MailGroup mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrListFrom = mailGroupFrom.getMailAddress();

        S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO_NPAB261002);
        mailGroupTo.setMailKey1(MAIL_TO_KEY_2);
        List<S21MailAddress> addrListTo = mailGroupTo.getMailAddress();

        // get mail template
        S21MailTemplate mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_NPAB2610M002);
        mailTemplate.setTemplateParameter(MAIL_ITEM_BATCHID, BUSINESS_ID);

        // attachment CSV File
        S21MailAttachment attachment = new S21MailAttachment(this.glblCmpyCd);
        String csvInfoStr = this.errCsvInfo.toString();
        byte[] attachData = csvInfoStr.getBytes();
        int attachId = attachment.setAttachment(attachData);
        attachment.setFileName(ATT_FILE_NAME + EXT_CSV);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(addrListFrom.get(0));
        mail.setToAddressList(addrListTo);
        mail.setMailTemplate(mailTemplate);
        mail.setAttachmentId(attachId);

        mail.postMail();
    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, this.totalErrorCount);
    }

    /**
     * main process
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB261001().executeBatch(NPAB261001.class.getSimpleName());
    }
}
