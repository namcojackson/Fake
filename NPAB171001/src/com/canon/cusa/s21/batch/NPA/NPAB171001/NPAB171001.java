/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB171001;

import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.ASN_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.ASN_SO_NUM_BR_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.ASTERISK;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.CARET;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_ASN_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_DEST_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_EDI_ASN_HDR_BOL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_EDI_ASN_HDR_BOL_NUM_HYPHN;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_EDI_ASN_PO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_EDI_ASN_SHIP_METH_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_EDI_PO_ORD_NUM_FOR_SER;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_SEGMENT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_UNIT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_VND_CD_AZERTY;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_VND_SHIP_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.KEY_VND_SYS_TP_CD_A;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.LEN_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.MAX_EDI_ASN_SHIP_METH_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.NPAM1537E;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.NPGM0009E;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.SQL_STMT_ID_GET_AZERTY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.SQL_STMT_ID_GET_PO_ASN_INTFC_DTL;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.SQL_STMT_ID_GET_PO_ASN_INTFC_HDR;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.SQL_STMT_ID_GET_PO_ASN_LEAD_TIME;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.SQL_STMT_ID_GET_PO_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.SQL_STMT_ID_GET_PO_INFO;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.VAL_001;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.VAL_01;
import static com.canon.cusa.s21.batch.NPA.NPAB171001.constant.NPAB171001Constant.VAL_ZERO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.EDI_ASN_DTL_WRKTMsg;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.EDI_ASN_SER_NUM_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ELAN_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Receiving PO ASN from Azerty<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 07/05/2016   CITS            M.Naito         Create          V1.0
 * 11/11/2016   CITS            M.Naito         Update          CSA
 * 12/14/2016   CITS            Y.IWASAKI       Update          QC#16624
 * 02/08/2018   CITS            T.Tokutomi      Update          QC#23339
 * 09/05/2018   CITS            K.Ogino         Update          QC#28100
 * </pre>
 */
public class NPAB171001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId = null;

    /** Commit Number */
    private int commitCount;

    /** normal record count */
    private int normalRecordCount;

    /** error record count */
    private int errorRecordCount;

    /** total record count */
    private int totalRecordCount;

    /** Vendor Code */
    private String azertyVndCd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SO_SER_ID HashMap */
    private HashMap<String, Integer> soSerIdMap;

    /**
     * main process
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB171001().executeBatch(NPAB171001.class.getSimpleName());
    }

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NPGM0009E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Getting Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NPGM0009E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        if (this.commitCount <= 0 || this.commitCount >= MAX_COMMIT_NUMBER) {
            this.commitCount = MAX_COMMIT_NUMBER;
        }
        this.normalRecordCount = 0;
        this.errorRecordCount = 0;
        this.totalRecordCount = 0;

        // get Azerty Vendor Cd
        Map<String, Object> ssmParamVndCd = new HashMap<String, Object>();
        ssmParamVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamVndCd.put(KEY_VND_SYS_TP_CD_A, VND_SYS_TP.AZERTY);
        this.azertyVndCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_AZERTY_VND_CD, ssmParamVndCd);
        if (!ZYPCommonFunc.hasValue(this.azertyVndCd)) {
            throw new S21AbendException(NPAM1537E, new String[] {"VND_CD", "RCV_ASN_VND", "GLBL_CMPY_CD = " + this.glblCmpyCd + COMMA + "VND_SYS_TP_CD = " + VND_SYS_TP.AZERTY });
        }

        // QC#23339 Add.
        soSerIdMap = new HashMap<String, Integer>();
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

            PreparedStatement prdStmtPoAsnIf = null;
            ResultSet rsPoAsnIf = null;

            try {
                // Search for data to be processed from
                // NPAI2120_01(Header)
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(KEY_INTERFACE_ID, interfaceId);
                queryParam.put(KEY_TRANSACTION_ID, tranId);
                prdStmtPoAsnIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_PO_ASN_INTFC_HDR, queryParam, execParam);
                rsPoAsnIf = prdStmtPoAsnIf.executeQuery();

                while (rsPoAsnIf.next()) {

                    // Search for data to be processed from
                    // NPAI2120_02(Detail)
                    List<Map<String, Object>> poAsnDtlLst = getPoAsnIntfcDtl(rsPoAsnIf);

                    String lastPoOrdNum = null;
                    String asnSoNum = null;
                    int asnLineNum = 0;
                    for (int i = 0; i < poAsnDtlLst.size(); i++) {

                        Map<String, Object> poAsnDtl = poAsnDtlLst.get(i);

                        // Get LeadTime(Days) from VND_SHIP_LT
                        String asnShipMethTxt = (String) rsPoAsnIf.getString("EDI_ASN_SHIP_METH_TXT");
                        // QC#28100
                        String asnShipDtTm = (String) rsPoAsnIf.getString("EDI_ASN_SHIP_DT_TM_TS");
                        String poOrdNum = (String) poAsnDtl.get("EDI_PO_ORD_NUM");
                        String poOrdDtlLineNum = (String) poAsnDtl.get("EDI_ASN_PO_DTL_LINE_NUM");
                        poOrdDtlLineNum = ZYPCommonFunc.leftPad(poOrdDtlLineNum, LEN_PO_ORD_DTL_LINE_NUM, VAL_ZERO);

                        String asnPlnDelyDt = getLeadTime(poOrdNum, poOrdDtlLineNum, asnShipMethTxt, asnShipDtTm);

                        // QC#16624
                        // Use same ASN_SO_NUM while BOL_NUM is same.
                        // ASN_SO_NUM should be divided when PO_NUM
                        // breaks.
                        if (!poOrdNum.equals(lastPoOrdNum)) {
                            // Get data of ASN_SO_NUM from
                            // EDI_ASN_HDR_WRK
                            asnSoNum = getAsnSoNum(rsPoAsnIf);

                            // insert EDI_ASN_HDR_WRK(Header)
                            insertEdiAsnHdrWrkData(rsPoAsnIf, poAsnDtl, asnSoNum);

                            asnLineNum = 0;
                            lastPoOrdNum = poOrdNum;
                        }

                        // Add ASN_LINE_NUM
                        ++asnLineNum;
                        // insert EDI_ASN_DTL_WRK(Detail)
                        insertEdiAsnDtlWrkData(rsPoAsnIf, poAsnDtl, asnSoNum, asnPlnDelyDt, asnLineNum);

                        // QC#23339 Add Serial process.
                        List<Map<String, Object>> ediSerialList = getPoAsnIntfcSerial(poAsnDtl);
                        insertEdiAsnSerNumWrkData(poAsnDtl, ediSerialList, asnSoNum);
                    }
                }

                trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
                commit();

            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(prdStmtPoAsnIf, rsPoAsnIf);
            }
        }
    }

    /**
     * getAsnSoNum
     * @param rs ResultSet
     * @return String
     * @throws SQLException
     */
    private String getAsnSoNum(ResultSet rs) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        String hdrbolNum = rs.getString("EDI_ASN_HDR_BOL_NUM");
        String asnSoNum = hdrbolNum;

        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(KEY_EDI_ASN_HDR_BOL_NUM, hdrbolNum);
        queryParam.put(KEY_EDI_ASN_HDR_BOL_NUM_HYPHN, CARET + hdrbolNum + ASN_SO_NUM_BR_NUM);
        String chkAsnSoNum = (String) this.ssmBatchClient.queryObject(SQL_STMT_ID_GET_PO_ASN_SO_NUM, queryParam);

        if (ZYPCommonFunc.hasValue(chkAsnSoNum)) {

            if (chkAsnSoNum.indexOf(HYPHEN) != -1) {
                // Split ASN_SO_NUM to ASN_SO_NUM and branch number
                String[] asnSoNumPrt = chkAsnSoNum.split(HYPHEN);
                // Increment branch number
                String asnSoNumBrNum = String.valueOf(Integer.parseInt(asnSoNumPrt[1]) + 1);
                asnSoNumBrNum = ZYPCommonFunc.leftPad(asnSoNumBrNum, 2, "0");
                asnSoNum = asnSoNumPrt[0] + HYPHEN + asnSoNumBrNum;
            } else {
                asnSoNum = chkAsnSoNum + HYPHEN + VAL_01;
            }
        }
        return asnSoNum;
    }

    /**
     * getLeadTime
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param asnShipMethTxt String
     * @param asnShipDtTm String
     * @return String
     * @throws SQLException
     */
    private String getLeadTime(String poOrdNum, String poOrdDtlLineNum, String asnShipMethTxt, String asnShipDtTm) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        BigDecimal delyLeadAot = BigDecimal.ZERO;

        // get SHIP_TO_ST_CD,SHPG_SVC_LVL_CD from PO
        Map<String, Object> ssmParamPoInfo = new HashMap<String, Object>();
        ssmParamPoInfo.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoInfo.put(KEY_PO_ORD_NUM, poOrdNum);
        ssmParamPoInfo.put(KEY_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        List<Map<String, Object>> poInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_PO_INFO, ssmParamPoInfo);

        String stCd = null;
        String vndShipMethCd = null;
        if (poInfo == null || poInfo.isEmpty()) {
            throw new S21AbendException(NPAM1537E, new String[] {"SHIP_TO_ST_CD" + COMMA + "SHPG_SVC_LVL_CD", "PO_DTL", "PO_ORD_NUM = " + poOrdNum + COMMA + "PO_ORD_DTL_LINE_NUM = " + poOrdDtlLineNum });
        } else {
            stCd = (String) poInfo.get(0).get("SHIP_TO_ST_CD");
            vndShipMethCd = (String) poInfo.get(0).get("SHPG_SVC_LVL_CD");
        }
        if (!ZYPCommonFunc.hasValue(stCd)) {
            stCd = ASTERISK;
        }
        if (!ZYPCommonFunc.hasValue(vndShipMethCd)) {
            vndShipMethCd = ASTERISK;
        }

        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(KEY_VND_CD_AZERTY, this.azertyVndCd);
        queryParam.put(KEY_DEST_ST_CD, stCd);
        queryParam.put(KEY_VND_SHIP_METH_CD, vndShipMethCd);
        queryParam.put(KEY_EDI_ASN_SHIP_METH_TXT, asnShipMethTxt);
        queryParam.put(KEY_AST, ASTERISK);
        BigDecimal azertyLtInfo = (BigDecimal) this.ssmBatchClient.queryObject(SQL_STMT_ID_GET_PO_ASN_LEAD_TIME, queryParam);
        if (azertyLtInfo != null) {
            delyLeadAot = azertyLtInfo;
        }

        // Convert format of EDI_ASN_PLN_DELY_DT_TM_TS from
        // NPAI2120_01
        asnShipDtTm = ZYPDateUtil.DateFormatter(asnShipDtTm, DATE_FORMAT, ZYPDateUtil.TYPE_YYYYMMDD);

        if (ZYPCommonFunc.hasValue(delyLeadAot)) {
            // Add delyLeadAot
            asnShipDtTm = ZYPDateUtil.addDays(asnShipDtTm, delyLeadAot.intValue());
        }
        return asnShipDtTm;
    }

    /**
     * getPoAsnIntfcDtl
     * @param rs ResultSet
     * @return List<Map<String, Object>>
     * @throws SQLException
     */
    private List<Map<String, Object>> getPoAsnIntfcDtl(ResultSet rs) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        List<Map<String, Object>> ediAsnDtlLst = null;

        queryParam.put(KEY_INTERFACE_ID, this.interfaceId);
        queryParam.put(KEY_TRANSACTION_ID, rs.getString("TRANSACTION_ID"));
        queryParam.put(KEY_SEGMENT_ID, rs.getString("SEGMENT_ID"));
        queryParam.put(KEY_UNIT_ID, rs.getString("UNIT_ID"));
        ediAsnDtlLst = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_PO_ASN_INTFC_DTL, queryParam);

        return ediAsnDtlLst;
    }

    /**
     * insertEdiAsnHdrWrkData
     * @param hdr ResultSet
     * @param dtl Map
     * @param asnSoNum String
     * @throws SQLException
     */
    private void insertEdiAsnHdrWrkData(ResultSet hdr, Map<String, Object> dtl, String asnSoNum) throws SQLException {

        EDI_ASN_HDR_WRKTMsg ediAsnHdrWrk = new EDI_ASN_HDR_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.asnSoNum, asnSoNum);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.asnShipDtTmTs, hdr.getString("EDI_ASN_SHIP_DT_TM_TS"));
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.shipFromSoNum, asnSoNum);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.brFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.procFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.asnEdiProcStsCd, ASN_EDI_PROC_STS_CD);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.ediRcvTs, currentTime);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.itrlIntfcId, interfaceId);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.ediPoOrdNum, (String) dtl.get("EDI_PO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.cmProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.elanPrintStsCd, ELAN_PRINT_STS.INITIAL);

        S21FastTBLAccessor.insert(ediAsnHdrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ediAsnHdrWrk.getReturnCode())) {
            this.errorRecordCount++;
            this.totalRecordCount++;
            throw new S21AbendException(NMAM0176E, new String[] {"EDI_ASN_HDR_WRKTMsg" });
        }
        this.normalRecordCount++;
        this.totalRecordCount++;
    }

    /**
     * insertEdiAsnDtlWrkData
     * @param hdr ResultSet
     * @param dtl Map
     * @param asnSoNum String
     * @param asnPlnDelyDt String
     * @throws SQLException
     */
    private void insertEdiAsnDtlWrkData(ResultSet hdr, Map<String, Object> dtl, String asnSoNum, String asnPlnDelyDt, int asnLineNum) throws SQLException {

        EDI_ASN_DTL_WRKTMsg ediAsnDtlWrk = new EDI_ASN_DTL_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnSoNum, asnSoNum);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnLineNum, String.format("%3s", asnLineNum).replace(' ', '0'));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnSoSlpNum, VAL_001);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnProNum, hdr.getString("EDI_ASN_PICK_SLP_NUM"));
        String ediAsnShipMethTxt = hdr.getString("EDI_ASN_SHIP_METH_TXT");
        if (ediAsnShipMethTxt.length() > MAX_EDI_ASN_SHIP_METH_TXT) {
            ediAsnShipMethTxt = ediAsnShipMethTxt.substring(0, MAX_EDI_ASN_SHIP_METH_TXT);
        }
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnCarrCd, ediAsnShipMethTxt);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnBolNum, (String) dtl.get("EDI_ASN_DTL_BOL_NUM"));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.ediPoOrdDtlLineNum, String.format("%3s", ((String) dtl.get("EDI_ASN_PO_DTL_LINE_NUM"))).replace(' ', '0'));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.ediSubNum, VAL_001);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnPlnDelyDt, asnPlnDelyDt);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnMdseCd, (String) dtl.get("ASN_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnQty, (BigDecimal) dtl.get("EDI_ASN_QTY"));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.mdseCdUpdFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(ediAsnDtlWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ediAsnDtlWrk.getReturnCode())) {
            this.errorRecordCount++;
            this.totalRecordCount++;
            throw new S21AbendException(NMAM0176E, new String[] {"EDI_ASN_DTL_WRKTMsg" });
        }
        this.normalRecordCount++;
        this.totalRecordCount++;
    }

    /**
     * getPoAsnIntfcSerial
     * QC#23339 Add method.
     * @param poAsnDtl
     * @return 
     */
    private List<Map<String, Object>> getPoAsnIntfcSerial(Map<String, Object> poAsnDtl) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(KEY_INTERFACE_ID, this.interfaceId);
        queryParam.put(KEY_TRANSACTION_ID, poAsnDtl.get("TRANSACTION_ID"));
        queryParam.put(KEY_SEGMENT_ID, poAsnDtl.get("SEGMENT_ID"));
        queryParam.put(KEY_UNIT_ID, poAsnDtl.get("UNIT_ID"));
        queryParam.put(KEY_EDI_PO_ORD_NUM_FOR_SER, poAsnDtl.get("EDI_PO_ORD_NUM"));
        queryParam.put(KEY_EDI_ASN_PO_DTL_LINE_NUM, poAsnDtl.get("EDI_ASN_PO_DTL_LINE_NUM"));
        List<Map<String, Object>> ediAsnSerLst = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPoAsnIntfcSerial", queryParam);

        if (ediAsnSerLst == null) {
            ediAsnSerLst = new ArrayList<Map<String, Object>>();
        }
        return ediAsnSerLst;
    }

    /**
     * insertEdiAsnSerNumWrkData
     * QC#23339 Add method.
     * @param dtl Map
     * @param serialList List<Map<String, Object>>
     * @param asnSoNum String
     */
    private void insertEdiAsnSerNumWrkData(Map<String, Object> dtl, List<Map<String, Object>> serialList, String asnSoNum) {

        for (Map<String, Object> serrial : serialList) {
            EDI_ASN_SER_NUM_WRKTMsg ediAsnSerNumWrk = new EDI_ASN_SER_NUM_WRKTMsg();

            // Primary Key
            ZYPEZDItemValueSetter.setValue(ediAsnSerNumWrk.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ediAsnSerNumWrk.asnSoNum, asnSoNum);
            ZYPEZDItemValueSetter.setValue(ediAsnSerNumWrk.asnSoSlpNum, VAL_001);
            ZYPEZDItemValueSetter.setValue(ediAsnSerNumWrk.asnMdseCd, (String) dtl.get("ASN_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(ediAsnSerNumWrk.soSerId, deriveSoSerId(asnSoNum, (String) dtl.get("ASN_MDSE_CD")));

            ZYPEZDItemValueSetter.setValue(ediAsnSerNumWrk.serNum, (String) serrial.get("EDI_SER_NUM"));

            S21FastTBLAccessor.insert(ediAsnSerNumWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ediAsnSerNumWrk.getReturnCode())) {
                this.errorRecordCount++;
                this.totalRecordCount++;
                throw new S21AbendException(NMAM0176E, new String[] {"EDI_ASN_SER_NUM_WRKTMsg" });
            }
            this.normalRecordCount++;
            this.totalRecordCount++;
        }
    }

    /**
     * deriveSoSerId
     * QC#23339 Add method.
     * @param asnSoNum
     * @param asnMdseCd
     * @return 
     */
    private String deriveSoSerId(String asnSoNum, String asnMdseCd) {

        String key = asnSoNum + asnMdseCd;
        Integer seq = new Integer(0);

        if (soSerIdMap.containsKey(key)) {

            seq = soSerIdMap.get(key);
            seq++;

        } else {
            // Check DB data. If there is no data, 0 is returned.
            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(KEY_ASN_SO_NUM, asnSoNum);
            queryParam.put(KEY_ASN_MDSE_CD, asnMdseCd);
            seq = (Integer) this.ssmBatchClient.queryObject("getMaxSoSerId", queryParam);
            seq++;
        }

        // Update or Insert sequence
        soSerIdMap.put(key, seq);

        return seq.toString();
    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecordCount, this.errorRecordCount, this.totalRecordCount);
    }

}
