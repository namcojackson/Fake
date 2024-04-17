/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB161001;

import static com.canon.cusa.s21.batch.NPA.NPAB161001.constant.NPAB161001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.PO_ACK_DTL_WRKTMsg;
import business.db.PO_ACK_HDR_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
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
 * Receiving PO ACK from Azerty<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 07/01/2016   CITS            N.Akaishi       Create          V1.0
 * 12/12/2016   CITS            Y.IWASAKI       Update          QC#16555
 * 12/21/2016   CITS            T.Wada          Update          QC#16823
 * </pre>
 */
public class NPAB161001 extends S21BatchMain {

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

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Lead Time for */
    private BigDecimal leadTimeAot;

    /** Error Csv Info */
    private StringBuilder errCsvInfo = new StringBuilder();

    /** Vendor Code */
    private String azertyVndCd;

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

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NPAM1539E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // get Batch Process Date
        this.batDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {
            this.commitCount = MAX_COMMIT_NUMBER;
        }
        this.totalCommitCount = 0;

        // get Azerty Vendor Cd
        Map<String, Object> ssmParamVndCd = new HashMap<String, Object>();
        ssmParamVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamVndCd.put(KEY_VND_SYS_TP_CD_A, VND_SYS_TP.AZERTY);
        this.azertyVndCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_AZERTY_VND_CD, ssmParamVndCd);
        if (!ZYPCommonFunc.hasValue(this.azertyVndCd)) {
            throw new S21AbendException(NPAM1537E, new String[] {COLUMN_VND_CD, TBL_RCV_ASN_VND, "GLBL_CMPY_CD = " + this.glblCmpyCd + COMMA + "VND_SYS_TP_CD = " + VND_SYS_TP.AZERTY});
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
                // NPAI2100_01(Header), NPAI2100_02(Detail)
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

                    curPoNum = rsPoAckIf.getString(COLUMN_EDI_ACK_PO_NUM);

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

                    // no po ack detail
                    if (!ZYPCommonFunc.hasValue(rsPoAckIf.getString(COLUMN_DTL_EDI_ACK_PO_NUM))) {
                        addErrCsvInfo(rsPoAckIf, S21MessageFunc.clspGetMessage(NPAM1537E, new String[] {rsPoAckIf.getString(COLUMN_EDI_ACK_PO_NUM), TBL_NPAI2100_02, COLUMN_EDI_ACK_PO_NUM }));
                        continue;
                    }

                    // mandatory and format check
                    if (!validateEdiPoAckData(rsPoAckIf)) {
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
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_PO_NUM))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_HDR_WRK, TBL_NPAI2100_01, COLUMN_EDI_ACK_PO_NUM, null }));
            return false;
        }
        // PO_ACK_DTL_WRK
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_PO_LINE_NUM))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI2100_02, COLUMN_EDI_ACK_PO_LINE_NUM, null }));
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_ACK_CD))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI2100_02, COLUMN_EDI_ACK_ACK_CD, null }));
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_VND_ITEM_CD))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI2100_02, COLUMN_EDI_ACK_VND_ITEM_CD, null }));
            return false;
        }
        if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_ACK_QTY_EDI_TXT))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(NPAM1538E, new String[] {TBL_PO_ACK_DTL_WRK, TBL_NPAI2100_02, COLUMN_EDI_ACK_ACK_QTY_EDI_TXT, null }));
            return false;
        }

        return true;
    }

    private void addErrCsvInfo(ResultSet rs, String message) throws SQLException {
        errCsvInfo.append(rs.getString(COLUMN_EDI_ACK_PO_NUM)).append(COMMA).append(rs.getString(COLUMN_EDI_ACK_PO_LINE_NUM)).append(COMMA).append(rs.getString(COLUMN_EDI_ACK_VND_ITEM_CD)).append(COMMA).append(message).append(CRLF);
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

        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ediPoOrdNum, rs.getString(COLUMN_EDI_ACK_PO_NUM));
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ackEdiProcStsCd, ACK_EDI_PROC_STS.SAVED);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.ediRcvDateTs, currentTime);
        ZYPEZDItemValueSetter.setValue(poAckHdrWrk.itrlIntfcId, this.interfaceId);

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
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ediPoOrdDtlLineNum, ZYPCommonFunc.leftPad(rs.getString(COLUMN_EDI_ACK_PO_LINE_NUM), IDX_3, VAL_ZERO));

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poOrdDtlSubLineNum, VAL_DEF_DTL_SUB_LINE_NUM);

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.shpgPlnDplyLineNum, ZYPCommonFunc.leftPad(rs.getString(COLUMN_EDI_ACK_PO_LINE_NUM), IDX_3, VAL_ZERO));

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ediPoOrdNum, rs.getString(COLUMN_EDI_ACK_PO_NUM));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poAckLineStsCd, rs.getString(COLUMN_PO_ACK_LINE_STS_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.vndPoAckLineStsTxt, rs.getString(COLUMN_PO_ACK_LINE_STS_NM));

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.mdseCd, rs.getString(COLUMN_EDI_ACK_VND_ITEM_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.trdPtnrSkuCd, rs.getString(COLUMN_EDI_ACK_VND_ITEM_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ordQty, new BigDecimal(rs.getString(COLUMN_EDI_ACK_ACK_QTY_EDI_TXT)));
        if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_FOB_TXT))) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.thisMthFobCostAmt, new BigDecimal(rs.getString(COLUMN_EDI_ACK_FOB_TXT)));
        }

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.ccyCd, VAL_DEF_CCY_CD);

        if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_UOM_TXT_02))) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.uomCd, getVndUom(rs.getString(COLUMN_EDI_ACK_UOM_TXT_02)));
        }

        if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_TRSMT_DT))) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etdDt, rs.getString(COLUMN_EDI_ACK_TRSMT_DT));
        } else if (!ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_TRSMT_DT)) && EDI_ACK_CD_AC.equals(rs.getString(COLUMN_EDI_ACK_ACK_CD))) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etdDt, this.batDt);
        }

        // Lead Time
        if (EDI_ACK_CD_AC.equals(rs.getString(COLUMN_EDI_ACK_ACK_CD))) {

            leadTimeAot = getVndShipLt(rs);

            if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_TRSMT_DT))) {
                ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etaDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, rs.getString(COLUMN_EDI_ACK_TRSMT_DT), leadTimeAot.intValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(poAckDtlWrk.etaDt, ZYPDateUtil.addBusinessDay(glblCmpyCd, this.batDt, leadTimeAot.intValue()));
            }
        }

        if (ZYPCommonFunc.hasValue(rs.getString(COLUMN_EDI_ACK_QTY_EDI_TXT))) {
            ZYPEZDItemValueSetter.setValue(poAckDtlWrk.origOrdQty, new BigDecimal(ZYPCommonFunc.trimHead(rs.getString(COLUMN_EDI_ACK_QTY_EDI_TXT))));
        }

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.origVndMdseCd, rs.getString(COLUMN_EDI_ACK_VND_ITEM_CD));

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.poAckHdrWrkPk, poAckHdrWrkPk);

        ZYPEZDItemValueSetter.setValue(poAckDtlWrk.vndPoAckStsCd, rs.getString(COLUMN_EDI_ACK_ACK_CD));

        return poAckDtlWrk;
    }

    // get Vendor Ship Lead Time
    private BigDecimal getVndShipLt(ResultSet rs) throws SQLException {

        BigDecimal ret = BigDecimal.ZERO;

        // get SHIP_TO_ST_CD,SHPG_SVC_LVL_CD from PO
        Map<String, Object> ssmParamPoInfo = new HashMap<String, Object>();
        ssmParamPoInfo.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoInfo.put(KEY_PO_ORD_NUM, rs.getString(COLUMN_EDI_ACK_PO_NUM));
        ssmParamPoInfo.put(KEY_PO_ORD_DTL_LINE_NUM, ZYPCommonFunc.leftPad(rs.getString(COLUMN_EDI_ACK_PO_LINE_NUM), LEN_PO_ORD_DTL_LINE_NUM, VAL_ZERO));
        List<Map<String, Object>> poInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_PO_INFO, ssmParamPoInfo);

        String stCd = null;
        String vndShipMethCd = null;
        if (poInfo == null || poInfo.isEmpty()) {
            throw new S21AbendException(NPAM1537E, new String[] {COLUMN_SHIP_TO_ST_CD + COMMA + COLUMN_SHPG_SVC_LVL_CD, TBL_PO_DTL,
                    "PO_ORD_NUM = " + rs.getString(COLUMN_EDI_ACK_PO_NUM) + COMMA + "PO_ORD_DTL_LINE_NUM = " + rs.getString(COLUMN_EDI_ACK_PO_LINE_NUM) });
        } else {
            stCd = (String) poInfo.get(0).get(COLUMN_SHIP_TO_ST_CD);
            vndShipMethCd = (String) poInfo.get(0).get(COLUMN_SHPG_SVC_LVL_CD);
        }
        if (!ZYPCommonFunc.hasValue(stCd)) {
            stCd = VAL_AST;
        }
        if (!ZYPCommonFunc.hasValue(vndShipMethCd)) {
            vndShipMethCd = VAL_AST;
        }

        Map<String, Object> ssmParamAzertyLtInfo = new HashMap<String, Object>();
        ssmParamAzertyLtInfo.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamAzertyLtInfo.put(KEY_VND_CD_AZERTY, this.azertyVndCd);
        ssmParamAzertyLtInfo.put(KEY_ST_CD, stCd);
        ssmParamAzertyLtInfo.put(KEY_VND_SHIP_METH_CD, vndShipMethCd);
        ssmParamAzertyLtInfo.put(KEY_AST, VAL_AST);
        BigDecimal azertyLtInfo = (BigDecimal) ssmBatchClient.queryObject(SQL_STMT_ID_GET_AZERTY_LT, ssmParamAzertyLtInfo);

        // QC#16823
        if (azertyLtInfo != null) {
            ret = azertyLtInfo;
        }

        return ret;
    }

    private String getPoAckNum(ResultSet rs) throws SQLException {

        Map<String, Object> ssmParamPoAckHdrWrk = new HashMap<String, Object>();
        ssmParamPoAckHdrWrk.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoAckHdrWrk.put(KEY_EDI_PO_ORD_NUM, rs.getString(COLUMN_EDI_ACK_PO_NUM));
        String poAckNumFroPoAckHdrWrk = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR_WRK, ssmParamPoAckHdrWrk);

        Map<String, Object> ssmParamPoAckHdr = new HashMap<String, Object>();
        ssmParamPoAckHdr.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoAckHdr.put(KEY_PO_ORD_NUM, rs.getString(COLUMN_EDI_ACK_PO_NUM));
        String poAckNumFroPoAckHdr = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_MAX_PO_ACK_NUM_FROM_PO_HDR, ssmParamPoAckHdr);

        if (!ZYPCommonFunc.hasValue(poAckNumFroPoAckHdrWrk)) {
            poAckNumFroPoAckHdrWrk = VAL_ZERO;
        }

        if (!ZYPCommonFunc.hasValue(poAckNumFroPoAckHdr)) {
            poAckNumFroPoAckHdr = VAL_ZERO;
        }

        if (poAckNumFroPoAckHdrWrk.compareTo(poAckNumFroPoAckHdr) > 0) {
            return String.valueOf(Integer.parseInt(poAckNumFroPoAckHdrWrk) + 1);
        } else {
            return String.valueOf(Integer.parseInt(poAckNumFroPoAckHdr) + 1);
        }

    }

    private String getVndUom(String ediAckUomTxt02) {
        String vndUom;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_GRP_ID_VND_UOM_XREF, DS_COND_CONST_KEY_VND_UOM_XREF);
        ssmParam.put(KEY_EDI_ACK_UOM_TXT_02, ediAckUomTxt02);
        vndUom = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_VND_UOM, ssmParam);

        return vndUom;
    }

    /**
     * insertPoAckHdrWrkData
     * @param inMsgLst List<PO_ACK_HDR_WRKTMsg>
     */
    private int insertPoAckHdrWrkData(List<PO_ACK_HDR_WRKTMsg> inMsgLst) {
        PO_ACK_HDR_WRKTMsg[] inMsgArray;
        inMsgArray = new PO_ACK_HDR_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NMAM0176E, new String[] {TBL_PO_ACK_HDR_WRK});
        }

        return insertCount;
    }

    /**
     * insertPoAckDtlWrkData
     * @param inMsgLst List<PO_ACK_DTL_WRKTMsg>
     */
    private int insertPoAckDtlWrkData(List<PO_ACK_DTL_WRKTMsg> inMsgLst) {
        PO_ACK_DTL_WRKTMsg[] inMsgArray;
        inMsgArray = new PO_ACK_DTL_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NMAM0176E, new String[] {TBL_PO_ACK_DTL_WRK});
        }

        return insertCount;
    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * main process
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB161001().executeBatch(NPAB161001.class.getSimpleName());
    }

    // send E-mail for Application Exception
    private void sendMailForAppError() {

        S21MailGroup mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrListFrom = mailGroupFrom.getMailAddress();

        S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO_NPAB161002);
        mailGroupTo.setMailKey1(MAIL_TO_KEY_2);
        List<S21MailAddress> addrListTo = mailGroupTo.getMailAddress();

        // get mail template
        S21MailTemplate mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_NPAB1610M002);
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
}
