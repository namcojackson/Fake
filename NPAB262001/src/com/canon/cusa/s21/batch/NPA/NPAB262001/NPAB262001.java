/*
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB262001;

import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.ASN_EDI_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.ASN_SO_NUM_BR_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.ASTERISK;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.CARET;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.INTFC_ID_DIETZGEN;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.INTFC_ID_TST_IMPRESO;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.INTFC_ID_HYTEC;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.INTFC_ID_ATRIX;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_ASN_SO_NUM_HYPHN;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_BASE_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_DEST_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_PKG_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_SEGMENT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_TRD_PTNR_CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_UNIT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_VND_SHIP_METH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_VND_SHIP_METH_CD_FROM_PO;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_VND_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.KEY_VND_UOM_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.LEN_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.MSG_ITEM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.NPAM1328E;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.NPAM1537E;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.NPAM1557E;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.NPAM1651E;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.NPGM0009E;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_ASN_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_CARR_CD_FROM_XREF;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_MDSE_STORE_PKG;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_PO_ASN_INTFC_DTL;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_PO_ASN_INTFC_HDR;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_PO_ASN_LEAD_TIME;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_PO_ASN_SO_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_PO_INFO;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_SO_NUM_FROM_CMBN_XREF;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_VND_UOM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.SQL_STMT_ID_GET_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.VAL_001;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.VAL_01;
import static com.canon.cusa.s21.batch.NPA.NPAB262001.constant.NPAB262001Constant.VAL_ZERO;

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
import business.db.EDI_ASN_DTL_WRKTMsg;
import business.db.EDI_ASN_HDR_WRKTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.PO_DTLTMsg;

import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ELAN_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Receiving ASN WRK from EDI Vendor<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 09/04/2018   CITS            M.Naito         Create          QC#26964,26965
 * 11/12/2018   CITS            M.Naito         Update          QC#29050
 * 09/02/2020   CITS            JR.Mercado      Update          QC#56991
 * 03/13/2023   Hitachi         E.Watabe        Update          QC#61128
 * 09/25/2023   Hitachi         TZ.Win          Update          QC#61608
 * </pre>
 */
public class NPAB262001 extends S21BatchMain {

    /** termination code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId = null;

    /** Commit Number */
    private int commitCount;

    /** success record count */
    private int successCount;

    /** Vendor Code */
    private String vndCd;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * main process
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB262001().executeBatch(NPAB262001.class.getSimpleName());
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

        // Get Interface ID
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
        this.successCount = 0;

        // Get vendor code from interfaceID
        String vndSysTpCd = null;
        if (INTFC_ID_TST_IMPRESO.equals(this.interfaceId)) {
            vndSysTpCd = VND_SYS_TP.TST_IMPRESO;
        } else if ((INTFC_ID_DIETZGEN.equals(this.interfaceId))) {
            vndSysTpCd = VND_SYS_TP.DIETZGEN;
        // START 2023/03/13 E.Watabe [QC#61128,ADD]
        } else if (INTFC_ID_HYTEC.equals(this.interfaceId)) {
            vndSysTpCd = VND_SYS_TP.HYTEC;
        // END 2023/03/13 E.Watabe [QC#61128,ADD]
        // START 2023/09/25 TZ.Win [QC#61608,ADD]
        } else if (INTFC_ID_ATRIX.equals(this.interfaceId)) {
            vndSysTpCd = VND_SYS_TP.ATRIX;
        }
        // END 2023/09/25 TZ.Win [QC#61608,ADD]

        if (!ZYPCommonFunc.hasValue(vndSysTpCd)) {
            throw new S21AbendException(NPAM1328E, new String[] {"VND_SYS_TP_CD" });
        }
        Map<String, Object> ssmParamVndCd = new HashMap<String, Object>();
        ssmParamVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamVndCd.put(KEY_VND_SYS_TP_CD, vndSysTpCd);
        this.vndCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_ASN_VND_CD, ssmParamVndCd);
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

            PreparedStatement prdStmtPoAsnIf = null;
            ResultSet poAsnHdr = null;

            try {
                // Search for data to be processed from
                // NPAI0420_01(Header)
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(KEY_INTERFACE_ID, interfaceId);
                queryParam.put(KEY_TRANSACTION_ID, tranId);
                prdStmtPoAsnIf = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_PO_ASN_INTFC_HDR, queryParam, execParam);
                poAsnHdr = prdStmtPoAsnIf.executeQuery();

                while (poAsnHdr.next()) {

                    // Search for data to be processed from
                    // NPAI0420_02(Detail)
                    List<Map<String, Object>> poAsnDtlLst = getPoAsnIntfcDtl(poAsnHdr);

                    String lastPoOrdNum = null;
                    String asnSoNum = null;
                    int asnLineNum = 0;
                    for (int i = 0; i < poAsnDtlLst.size(); i++) {

                        Map<String, Object> poAsnDtl = poAsnDtlLst.get(i);

                        // Get LeadTime(Days) from VND_SHIP_LT
                        String rcvOtbdCarrCd = (String) poAsnHdr.getString("ASN_RCV_OTBD_CARR_CD");
                        String asnRcvShipDt = (String) poAsnHdr.getString("ASN_RCV_SHIP_DT");
                        String poOrdNum = (String) poAsnDtl.get("ASN_RCV_PO_ORD_NUM");
                        String poOrdDtlLineNum = (String) poAsnDtl.get("ASN_RCV_PO_ORD_DTL_LINE_NUM");
                        poOrdDtlLineNum = ZYPCommonFunc.leftPad(poOrdDtlLineNum, LEN_PO_ORD_DTL_LINE_NUM, VAL_ZERO);

                        // QC#56991 Add - Start
                        if (!ZYPCommonFunc.hasValue(poOrdNum)) {
                            continue;
                        }

                        if (!isPoNumExist(poOrdNum)) {
                            ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

                            String[] msgParam = new String[] {poOrdNum};

                            Map<String, String> mailParam = new HashMap<String, String>();
                            mailParam.put(NLXMailSend.KEY_MESSAGE_ID, NPAM1651E);
                            mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(NPAM1651E, msgParam));

                            errMsgList.add(mailParam);

                            NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                            mail.send("NPAB262001", errMsgList);
                            commit();

                            continue;
                        }
                        // QC#56991 Add - End

                        String asnPlnDelyDt = getLeadTime(poOrdNum, poOrdDtlLineNum, rcvOtbdCarrCd, asnRcvShipDt);

                        if (!poOrdNum.equals(lastPoOrdNum)) {
                            // get Shipping Number from
                            // CMBN_PO_SO_XREF
                            String soNum = getSoNumFromCmbnXref(poOrdNum, poOrdDtlLineNum);
                            if (ZYPCommonFunc.hasValue(soNum)) {
                                asnSoNum = soNum;
                            } else {
                                asnSoNum = poOrdNum;
                            }
                            // Get data of ASN_SO_NUM from
                            // EDI_ASN_HDR_WRK
                            asnSoNum = getAsnSoNum(asnSoNum);

                            // insert EDI_ASN_HDR_WRK(Header)
                            insertEdiAsnHdrWrkData(poAsnHdr, poAsnDtl, asnSoNum);

                            asnLineNum = 0;
                            lastPoOrdNum = poOrdNum;
                        }

                        // Add ASN_LINE_NUM
                        ++asnLineNum;
                        // insert EDI_ASN_DTL_WRK(Detail)
                        insertEdiAsnDtlWrkData(poAsnHdr, poAsnDtl, asnSoNum, asnPlnDelyDt, asnLineNum);
                    }
                }
                trxAccessor.endIntegrationProcess(this.interfaceId, tranId);
                commit();

            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(prdStmtPoAsnIf, poAsnHdr);
            }
        }
    }

    /**
     * getAsnSoNum
     * @param soNum String
     * @return String
     * @throws SQLException
     */
    private String getAsnSoNum(String asnSoNum) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(KEY_ASN_SO_NUM, asnSoNum);
        queryParam.put(KEY_ASN_SO_NUM_HYPHN, CARET + asnSoNum + ASN_SO_NUM_BR_NUM);
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
     * isPoNumExist
     * QC#56991 add method.
     * @param poOrdNum String
     * @return boolean
     * @throws SQLException
     */
    private boolean isPoNumExist(String poOrdNum) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(KEY_PO_ORD_NUM, poOrdNum);
        BigDecimal resultCnt = (BigDecimal) this.ssmBatchClient.queryObject(SQL_STMT_ID_GET_PO_ORD_NUM, queryParam);

        if (BigDecimal.ZERO.equals(resultCnt)) {
            return false;
        }

        return true;
    }

    /**
     * getLeadTime
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param vndShipMethCd String
     * @param asnRcvShipDt String
     * @return String
     * @throws SQLException
     */
    private String getLeadTime(String poOrdNum, String poOrdDtlLineNum, String vndShipMethCd, String asnRcvShipDt) throws SQLException {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        String asnPlnDelyDt = asnRcvShipDt;

        // get SHIP_TO_ST_CD,SHPG_SVC_LVL_CD from PO
        Map<String, Object> ssmParamPoInfo = new HashMap<String, Object>();
        ssmParamPoInfo.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParamPoInfo.put(KEY_PO_ORD_NUM, poOrdNum);
        ssmParamPoInfo.put(KEY_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        List<Map<String, Object>> poInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SQL_STMT_ID_GET_PO_INFO, ssmParamPoInfo);

        String stCd = null;
        String vndShipMethCdFromPo = null;
        if (poInfo != null && !poInfo.isEmpty()) {
            stCd = (String) poInfo.get(0).get("SHIP_TO_ST_CD");
            vndShipMethCdFromPo = (String) poInfo.get(0).get("SHPG_SVC_LVL_CD");
        }
        if (!ZYPCommonFunc.hasValue(stCd)) {
            stCd = ASTERISK;
        }
        if (!ZYPCommonFunc.hasValue(vndShipMethCdFromPo)) {
            vndShipMethCdFromPo = ASTERISK;
        }

        queryParam.put(KEY_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(KEY_VND_CD, this.vndCd);
        queryParam.put(KEY_DEST_ST_CD, stCd);
        queryParam.put(KEY_VND_SHIP_METH_CD, vndShipMethCd);
        queryParam.put(KEY_VND_SHIP_METH_CD_FROM_PO, vndShipMethCdFromPo);
        queryParam.put(KEY_AST, ASTERISK);
        BigDecimal delyLeadAot = (BigDecimal) this.ssmBatchClient.queryObject(SQL_STMT_ID_GET_PO_ASN_LEAD_TIME, queryParam);
        if (ZYPCommonFunc.hasValue(delyLeadAot)) {
            // Add delyLeadAot
            asnPlnDelyDt = ZYPDateUtil.addDays(asnRcvShipDt, delyLeadAot.intValue());
        }
        return asnPlnDelyDt;
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
     * getCarrCdFromXref
     * @param trdPtnrCarrCd String
     * @return String
     * @throws SQLException
     */
    private String getCarrCdFromXref(String trdPtnrCarrCd) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_VND_CD, this.vndCd);
        ssmParam.put(KEY_TRD_PTNR_CARR_CD, trdPtnrCarrCd);
        String carrCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_CARR_CD_FROM_XREF, ssmParam);
        if (ZYPCommonFunc.hasValue(carrCd) && !ASTERISK.equals(carrCd)) {
            return carrCd;
        }
        return trdPtnrCarrCd;
    }

    /**
     * getSoNumFromCmbnXref
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private String getSoNumFromCmbnXref(String poOrdNum, String poOrdDtlLineNum) throws SQLException {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_PO_ORD_NUM, poOrdNum);
        ssmParam.put(KEY_PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
        return (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_SO_NUM_FROM_CMBN_XREF, ssmParam);
    }

    // START 2018/11/12 M.Naito [QC#29050,ADD]
    /**
     * getVndUomCd
     * @param asnRcvPkgUomCd String
     * @param uomCd String
     * @return String
     */
    private String getVndUomCd(String asnRcvPkgUomCd, String uomCd) {

        if (!ZYPCommonFunc.hasValue(uomCd)) {
            return asnRcvPkgUomCd;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(KEY_VND_CD, this.vndCd);
        ssmParam.put(KEY_VND_UOM_CD, asnRcvPkgUomCd);
        ssmParam.put(KEY_PKG_UOM_CD, uomCd);
        String vndUomCd = (String) ssmBatchClient.queryObject(SQL_STMT_ID_GET_VND_UOM, ssmParam);
        if (ZYPCommonFunc.hasValue(vndUomCd)) {
            return vndUomCd;
        }
        return asnRcvPkgUomCd;
    }

    /**
     * getUomCdFromPo
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return String
     * @throws SQLException 
     */
    private String getUomCdFromPo(String poOrdNum, String poOrdDtlLineNum) throws SQLException {

        PO_DTLTMsg poDtlTmsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdDtlLineNum, poOrdDtlLineNum);
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
    // END 2018/11/12 M.Naito [QC#29050,ADD]

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
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.asnShipDtTmTs, hdr.getString("ASN_RCV_SHIP_DT"));
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.shipFromSoNum, asnSoNum);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.brFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.procFlg, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.asnEdiProcStsCd, ASN_EDI_PROC_STS_CD);
        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.ediRcvTs, currentTime);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.itrlIntfcId, interfaceId);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.ediPoOrdNum, hdr.getString("ASN_RCV_PO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.cmProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ediAsnHdrWrk.elanPrintStsCd, ELAN_PRINT_STS.INITIAL);

        S21FastTBLAccessor.insert(ediAsnHdrWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ediAsnHdrWrk.getReturnCode())) {
            throw new S21AbendException(NPAM1557E, new String[] {"EDI_ASN_HDR_WRK" });
        }
        this.successCount++;
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
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnProNum, hdr.getString("ASN_RCV_BOL_NUM"));
        // Convert Carrier Code
        String asnCarrCd = getCarrCdFromXref(hdr.getString("ASN_RCV_OTBD_CARR_CD"));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnCarrCd, asnCarrCd);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnBolNum, (String) dtl.get("ASN_RCV_BOL_NUM"));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.ediPoOrdDtlLineNum, String.format("%3s", ((String) dtl.get("ASN_RCV_PO_ORD_DTL_LINE_NUM"))).replace(' ', '0'));
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.ediSubNum, VAL_001);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnPlnDelyDt, asnPlnDelyDt);
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnMdseCd, (String) dtl.get("ASN_RCV_MDSE_CD"));
        // START 2018/11/12 M.Naito [QC#29050,MOD]
//        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnQty, (BigDecimal) dtl.get("ASN_RCV_SHIP_QTY"));
        BigDecimal shipQty = (BigDecimal) dtl.get("ASN_RCV_SHIP_QTY");
        if (ZYPCommonFunc.hasValue((String) dtl.get("ASN_RCV_PKG_UOM_CD"))) {
            if (INTFC_ID_TST_IMPRESO.equals(this.interfaceId)) {
                String poOrdNum = (String) dtl.get("ASN_RCV_PO_ORD_NUM");
                String poOrdDtlLineNum = (String) dtl.get("ASN_RCV_PO_ORD_DTL_LINE_NUM");
                poOrdDtlLineNum = ZYPCommonFunc.leftPad(poOrdDtlLineNum, LEN_PO_ORD_DTL_LINE_NUM, VAL_ZERO);
                String uomCd = getUomCdFromPo(poOrdNum, poOrdDtlLineNum);
                String asnRcvPkgUomCd = getVndUomCd((String) dtl.get("ASN_RCV_PKG_UOM_CD"), uomCd);
                if (ZYPCommonFunc.hasValue(uomCd) && !uomCd.equals(asnRcvPkgUomCd)) {
                    BigDecimal inEachQty = getInEachQty(uomCd, asnRcvPkgUomCd, (String) dtl.get("ASN_RCV_MDSE_CD"));
                    if (ZYPCommonFunc.hasValue(shipQty) && ZYPCommonFunc.hasValue(inEachQty) && !BigDecimal.ZERO.equals(inEachQty)) {
                        shipQty = inEachQty.multiply(shipQty);
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.asnQty, shipQty);
        // END 2018/11/12 M.Naito [QC#29050,MOD]
        ZYPEZDItemValueSetter.setValue(ediAsnDtlWrk.mdseCdUpdFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.insert(ediAsnDtlWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ediAsnDtlWrk.getReturnCode())) {
            throw new S21AbendException(NPAM1557E, new String[] {"EDI_ASN_DTL_WRK" });
        }
        this.successCount++;
    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.successCount, 0);
    }

}
