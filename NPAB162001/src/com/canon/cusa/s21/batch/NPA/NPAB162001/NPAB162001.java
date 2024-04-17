/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB162001;

import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.ATT_FILE_NAME;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CHECK_DIGITS_TP_REAL_NUMERIC;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_ASL_QLFY_REF_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_DCC_VND_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EDI_PRC_CAT_BIG_DEAL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EDI_PRC_CAT_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EDI_PRC_CAT_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EDI_PRC_CAT_UNIT_PRC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EDI_PRC_CAT_UOM_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EDI_PRC_CAT_VND_PROD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_SPLY_ITEM_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_UNIT_PRC_AMT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COL_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CRLF;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CSV_HEADER_EDI_PRC_CAT_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CSV_HEADER_EDI_PRC_CAT_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CSV_HEADER_EDI_PRC_CAT_UOM_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.CSV_HEADER_EDI_PRC_CAT_VND_PROD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.DS_COND_CONST_KEY_VND_UOM_XREF;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.EXT_CSV;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_ASL_QLFY_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_EFF_THRU_DT_DEFAULT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_GRP_ID_VND_UOM_XREF;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.KEY_VND_SYS_TP_CD_A;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MAIL_GROUP_ID_TO_NPAB162002;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MAIL_ITEM_BATCHID;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MAIL_TEMPLATE_ID_NPAB1620M002;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MAIL_TO_KEY_2;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MAX_ASL_DTL_CNT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.NPAM1178E;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.NPAM1537E;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.NPAM1539E;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.NUM_EDI_PRC_CAT_UNIT_PRC_TXT_DIGITS;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.NUM_EDI_PRC_CAT_UNIT_PRC_TXT_FRAC_DIGITS;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.STMT_GET_AZERTY_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.STMT_GET_AZERTY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.STMT_GET_EDI_PRC_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.TBL_PRNT_VND;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.TBL_RCV_ASN_VND;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.VAL_EFF_THRU_DT_DEFAULT;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.VAL_EMPTY;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.VAL_VND_SYS_TP_CD_A;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NPA.NPAB162001.constant.NPAB162001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDValidatorException;
import business.parts.NPZC115001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC115001.NPZC115001;
import com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASL_QLFY_TP;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 * Receiving Price from Azerty<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 07/29/2016   CITS            N.Akaishi       Create          N/A
 * 12/27/2016   CITS            R.Shimamoto     Update          QC#16827
 * </pre>
 */
public class NPAB162001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId = null;

    /** Sales Date */
    private String slsDt;

    /** Error Number */
    private int errorCount;

    /** Vendor Code(Azerty) */
    private String vndCd;

    /** Parent Vendor Code(Azerty) */
    private String prntVndCd;

    /** total commit count */
    private int totalCommitCount;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Csv Info */
    private StringBuilder errCsvInfo = new StringBuilder();

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

        // get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NPAM1539E, new String[] {MSG_ITEM_SALES_DATE });
        }

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // get Azerty Vendor Cd
        Map<String, Object> ssmParamVndCd = new HashMap<String, Object>();
        ssmParamVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamVndCd.put(KEY_VND_SYS_TP_CD_A, VAL_VND_SYS_TP_CD_A);
        this.vndCd = (String) ssmBatchClient.queryObject(STMT_GET_AZERTY_VND_CD, ssmParamVndCd);

        if (!ZYPCommonFunc.hasValue(this.vndCd)) {
            throw new S21AbendException(NPAM1537E, new String[] {COL_VND_CD, TBL_RCV_ASN_VND, "GLBL_CMPY_CD = " + this.glblCmpyCd + COMMA + "VND_SYS_TP_CD = " + VAL_VND_SYS_TP_CD_A});
        }

        // get Azerty Parent Vendor Cd
        Map<String, Object> ssmParamPrntVndCd = new HashMap<String, Object>();
        ssmParamPrntVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPrntVndCd.put(KEY_VND_CD, this.vndCd);
        this.prntVndCd = (String) ssmBatchClient.queryObject(STMT_GET_AZERTY_PRNT_VND_CD, ssmParamPrntVndCd);
        if (!ZYPCommonFunc.hasValue(this.prntVndCd)) {
            throw new S21AbendException(NPAM1537E, new String[] {COL_PRNT_VND_CD, TBL_PRNT_VND, "GLBL_CMPY_CD = " + this.glblCmpyCd + COMMA + "VND_CD = " + this.vndCd});
        }

        // initialize
        this.totalCommitCount = 0;
        this.errorCount = 0;

    }

    /**
     * Main process
     */
    @Override
    protected void mainRoutine() {

        try {
            doProcess();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

    }

    // doProcess
    protected void doProcess() throws SQLException {

        S21TransactionTableAccessor trxAccessor = new S21TransactionTableAccessor();
        BigDecimal[] tranIds = trxAccessor.getIntegrationRecord(this.interfaceId);

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_ASL_DTL_CNT);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        for (BigDecimal tranId : tranIds) {

            // Search for data to be processed from NPAI2110_01(EDI Price Header), NPAI2110_02(EDI Price Detail)
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_INTERFACE_ID, interfaceId);
            queryParam.put(KEY_TRANSACTION_ID, tranId);
            queryParam.put(KEY_SALES_DATE, slsDt);
            queryParam.put(KEY_ASL_QLFY_TP_CD, ASL_QLFY_TP.BIG_DEAL_SPECIFIC);
            queryParam.put(KEY_VND_CD, this.vndCd);
            queryParam.put(KEY_PRNT_VND_CD, this.prntVndCd);
            queryParam.put(KEY_GRP_ID_VND_UOM_XREF, DS_COND_CONST_KEY_VND_UOM_XREF);
            queryParam.put(KEY_EFF_THRU_DT_DEFAULT, VAL_EFF_THRU_DT_DEFAULT);

            PreparedStatement prdStmtEdiPrcIf = null;
            ResultSet rsEdiPrcIf = null;

            prdStmtEdiPrcIf = ssmLLClient.createPreparedStatement(STMT_GET_EDI_PRC_LIST, queryParam, execParam);
            rsEdiPrcIf = prdStmtEdiPrcIf.executeQuery();

            String currentBigDealNum = VAL_EMPTY;
            String beforeBigDealNum = VAL_EMPTY;

            int aslCnt = 0;
            int aslQlfyCnt = 0;

            // set API parameter
            NPZC115001PMsg pMsg = new NPZC115001PMsg();
            pMsg.xxAslDtlList.setValidCount(aslCnt);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.COST_UPDATE_MODE);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, this.prntVndCd);

            while (rsEdiPrcIf.next()) {

                currentBigDealNum = rsEdiPrcIf.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM);

                // call ASL Update API
                // pMsg(xxAslDtlList)'s count = 1000 or BigDealNum Change
                if (aslCnt == MAX_ASL_DTL_CNT || (aslCnt > 0 && !currentBigDealNum.equals(beforeBigDealNum))) {

                    callAslUpdateApi(pMsg);

                    aslCnt = 0;
                    aslQlfyCnt = 0;
                    pMsg = new NPZC115001PMsg();
                }

                NPAB1620EdiPriceBean bean = new NPAB1620EdiPriceBean();

                if (!validateEdiPrc(rsEdiPrcIf, bean)) {
                    continue;
                }

                // set API parameter
                aslCnt++;
                pMsg.xxAslDtlList.setValidCount(aslCnt);
                int idxAslDtl = aslCnt - 1;
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC115001Constant.COST_UPDATE_MODE);
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.procDt, this.slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, this.prntVndCd);

                // asl dtl
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(idxAslDtl).vndUomCd, bean.getVndUomCd());
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(idxAslDtl).splyItemNum, bean.getEdiPrcCatVndProdNum());
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(idxAslDtl).vndCd, this.vndCd);
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(idxAslDtl).unitPrcAmt, bean.getUnitPrcAmt());
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(idxAslDtl).effFromDt, bean.getEdiPrcCatEffFromDt());
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(idxAslDtl).effThruDt, bean.getEdiPrcCatEffThruDt());

                // set EDI price info 's  big deal num if asl qlfy exists
                if (ZYPCommonFunc.hasValue(bean.getEdiPrcCatBigDealNum())) {
                    aslQlfyCnt++;
                    pMsg.xxAslQlfyRelnList.setValidCount(aslQlfyCnt);
                    int idxAslQlfyReln = aslQlfyCnt - 1;
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslQlfyRelnList.no(idxAslQlfyReln).aslQlfyTpCd, ASL_QLFY_TP.BIG_DEAL_SPECIFIC);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslQlfyRelnList.no(idxAslQlfyReln).aslQlfyRefCd, bean.getEdiPrcCatBigDealNum());
                }

                beforeBigDealNum = rsEdiPrcIf.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM);
            }

            if (pMsg.xxAslDtlList.getValidCount() > 0) {
                callAslUpdateApi(pMsg);
            }

            S21SsmLowLevelCodingClient.closeResource(prdStmtEdiPrcIf, rsEdiPrcIf);

            // update interface_transaction
            trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();

        }

        if (errCsvInfo.toString().getBytes().length > 0) {
            StringBuilder csvHeader = new StringBuilder();
            csvHeader.append(CSV_HEADER_EDI_PRC_CAT_VND_PROD_NUM).append(COMMA)
                     .append(CSV_HEADER_EDI_PRC_CAT_EFF_FROM_DT).append(COMMA)
                     .append(CSV_HEADER_EDI_PRC_CAT_EFF_THRU_DT).append(COMMA)
                     .append(CSV_HEADER_EDI_PRC_CAT_UOM_TXT)
                     .append(COMMA).append(CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT).append(CRLF);
            errCsvInfo.insert(0, csvHeader.toString());
            sendMailForAppError();

            commit();
        }

    }

    // check
    private boolean validateEdiPrc(ResultSet rs, NPAB1620EdiPriceBean bean) throws SQLException {

        boolean ret = true;

        // check mandatory
        if (!ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_VND_PROD_NUM))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9025E, new String[] {CSV_HEADER_EDI_PRC_CAT_VND_PROD_NUM }));
            return false;
        }

        if (!ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_UOM_TXT))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9025E, new String[] {CSV_HEADER_EDI_PRC_CAT_UOM_TXT }));
            return false;
        }

        if (!ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_UNIT_PRC_TXT))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9025E, new String[] {CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT }));
            return false;
        }

        // check Date Format
        if (ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_EFF_FROM_DT))) {
            if (!ZYPDateUtil.checkDate(rs.getString(COL_EDI_PRC_CAT_EFF_FROM_DT))) {
                addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {CSV_HEADER_EDI_PRC_CAT_EFF_FROM_DT }));
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_EFF_THRU_DT))) {
            if (!ZYPDateUtil.checkDate(rs.getString(COL_EDI_PRC_CAT_EFF_THRU_DT))) {
                addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {CSV_HEADER_EDI_PRC_CAT_EFF_THRU_DT }));
                return false;
            }
        }

        // check Vnd Uom
        if (!ZYPCommonFunc.hasValue(rs.getString(COL_DCC_VND_UOM_CD))) {
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {CSV_HEADER_EDI_PRC_CAT_UOM_TXT }));
            return false;
        }

        // check Unit Price Format Number(19.4)
        try {
            if (!ZYPCommonFunc.isCheckDigits(rs.getString(COL_EDI_PRC_CAT_UNIT_PRC_TXT), NUM_EDI_PRC_CAT_UNIT_PRC_TXT_DIGITS, NUM_EDI_PRC_CAT_UNIT_PRC_TXT_FRAC_DIGITS, CHECK_DIGITS_TP_REAL_NUMERIC)) {
                addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT }));
                return false;
            }
        } catch (EZDValidatorException e) { // frac digits is invalid
            S21InfoLogOutput.println(ZZZM9026E, new String[] {"EDI_PRC_CAT_UNIT_PRC_TXT = " + rs.getString(COL_EDI_PRC_CAT_UNIT_PRC_TXT)});
            addErrCsvInfo(rs, S21MessageFunc.clspGetMessage(ZZZM9026E, new String[] {CSV_HEADER_EDI_PRC_CAT_UNIT_PRC_TXT }));
            return false;
        }

        // Processing ASL of information to check whether the
        // registered
        if (ZYPCommonFunc.hasValue(rs.getString(COL_SPLY_ITEM_NUM))) {
            if (!rs.getString(COL_EDI_PRC_CAT_VND_PROD_NUM).equals(rs.getString(COL_SPLY_ITEM_NUM))) {
                ret = true;
            } else {
                ret = false;
            }
        }
        if (!ret && ZYPCommonFunc.hasValue(rs.getString(COL_UNIT_PRC_AMT))) {
            if (!rs.getString(COL_EDI_PRC_CAT_UNIT_PRC_TXT).equals(rs.getString(COL_UNIT_PRC_AMT))) {
                ret = true;
            } else {
                ret = false;
            }
        }
        if (!ret && ZYPCommonFunc.hasValue(rs.getString(COL_ASL_QLFY_REF_CD))) {
            if (!rs.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM).equals(rs.getString(COL_ASL_QLFY_REF_CD))) {
                ret = true;
            } else {
                ret = false;
            }
        }
        if (!ret && ZYPCommonFunc.hasValue(rs.getString(COL_EFF_FROM_DT))) {
            if (!rs.getString(COL_EDI_PRC_CAT_EFF_FROM_DT).equals(rs.getString(COL_EFF_FROM_DT))) {
                ret = true;
            } else {
                ret = false;
            }
        }
        if (!ret && ZYPCommonFunc.hasValue(rs.getString(COL_EFF_THRU_DT))) {
            if (!rs.getString(COL_EDI_PRC_CAT_EFF_THRU_DT).equals(rs.getString(COL_EFF_THRU_DT))) {
                ret = true;
            } else {
                ret = false;
            }
        }

        bean.setEdiPrcCatVndProdNum(rs.getString(COL_EDI_PRC_CAT_VND_PROD_NUM));
        bean.setVndUomCd(rs.getString(COL_DCC_VND_UOM_CD));
        bean.setUnitPrcAmt(new BigDecimal(rs.getString(COL_EDI_PRC_CAT_UNIT_PRC_TXT)));
        bean.setEdiPrcCatEffFromDt(rs.getString(COL_EDI_PRC_CAT_EFF_FROM_DT));
        bean.setEdiPrcCatEffThruDt(rs.getString(COL_EDI_PRC_CAT_EFF_THRU_DT));

        // QC#16827 Mod.
//        if (ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM))
//                && ZYPCommonFunc.hasValue(rs.getString(COL_ASL_QLFY_REF_CD))) {
//            if (rs.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM).equals(rs.getString(COL_ASL_QLFY_REF_CD))) {
//                bean.setEdiPrcCatBigDealNum(rs.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM));
//            }
//        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM))) {
            bean.setEdiPrcCatBigDealNum(rs.getString(COL_EDI_PRC_CAT_BIG_DEAL_NUM));
        }

        return ret;
    }

    // call ASL Update API
    private void callAslUpdateApi(NPZC115001PMsg pMsg) {

        // Call ASL Update API
        new NPZC115001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {

            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);

            for (String xxMsgId : xxMsgIdList) {

                S21InfoLogOutput.println(xxMsgId);

            }

            StringBuilder subMsg = new StringBuilder();
            for (int i = 0; i < pMsg.xxAslDtlList.getValidCount(); i++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).xxMsgId)) {

                    subMsg.setLength(0);
                    subMsg.append("API:ASL Update: ");
                    subMsg.append(" VND_UOM_CD=").append(pMsg.xxAslDtlList.no(i).vndUomCd.getValue());
                    subMsg.append(" EDI_PRC_CAT_VND_PROD_NUM = ").append(pMsg.xxAslDtlList.no(i).splyItemNum.getValue());
                    subMsg.append(" VND_CD = ").append(pMsg.xxAslDtlList.no(i).vndCd.getValue());
                    subMsg.append(" UNIT_PRC_AMT = ").append(pMsg.xxAslDtlList.no(i).unitPrcAmt.getValue().toPlainString());
                    subMsg.append(" EDI_PRC_CAT_EFF_FROM_DT = ").append(pMsg.xxAslDtlList.no(i).effFromDt.getValue());
                    subMsg.append(" EDI_PRC_CAT_EFF_THRU_DT = ").append(pMsg.xxAslDtlList.no(i).effThruDt.getValue());

                    S21InfoLogOutput.println(NPAM1178E, new String[] {subMsg.toString()});

                    errCsvInfo.append(pMsg.xxAslDtlList.no(i).splyItemNum.getValue()).append(COMMA)
                              .append(pMsg.xxAslDtlList.no(i).effFromDt.getValue()).append(COMMA)
                              .append(pMsg.xxAslDtlList.no(i).effThruDt.getValue()).append(COMMA)
                              .append(pMsg.xxAslDtlList.no(i).vndUomCd.getValue()).append(COMMA)
                              .append(pMsg.xxAslDtlList.no(i).unitPrcAmt.getValue().toPlainString()).append(COMMA)
                              .append(COMMA).append(S21MessageFunc.clspGetMessage(pMsg.xxAslDtlList.no(i).xxMsgId.getValue())).append(CRLF);

                    this.errorCount++;

                } else {
                    this.totalCommitCount++;
                }
            }

        } else {
            this.totalCommitCount += pMsg.xxAslDtlList.getValidCount();
        }
    }

    // add csv error info
    private void addErrCsvInfo(ResultSet rs, String message) throws SQLException {
        errCsvInfo.append(rs.getString(COL_EDI_PRC_CAT_VND_PROD_NUM)).append(COMMA)
                  .append(rs.getString(COL_EDI_PRC_CAT_EFF_FROM_DT)).append(COMMA)
                  .append(rs.getString(COL_EDI_PRC_CAT_EFF_THRU_DT)).append(COMMA)
                  .append(rs.getString(COL_EDI_PRC_CAT_UOM_TXT)).append(COMMA)
                  .append(rs.getString(COL_EDI_PRC_CAT_UNIT_PRC_TXT))
                  .append(COMMA).append(message).append(CRLF);
        S21InfoLogOutput.println(message);
    }

    // send E-mail for Application Exception
    private void sendMailForAppError() {

        S21MailGroup mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrListFrom = mailGroupFrom.getMailAddress();

        S21MailGroup mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO_NPAB162002);
        mailGroupTo.setMailKey1(MAIL_TO_KEY_2);
        List<S21MailAddress> addrListTo = mailGroupTo.getMailAddress();

        // get mail template
        S21MailTemplate mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_NPAB1620M002);
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
        setTermState(this.termCd, this.totalCommitCount, this.errorCount);
    }

    /**
     * main process
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB162001().executeBatch(NPAB162001.class.getSimpleName());
    }

}
