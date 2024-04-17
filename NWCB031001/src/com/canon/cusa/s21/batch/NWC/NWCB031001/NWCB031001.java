/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB031001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.INV_PRT_CTRLTMsg;
import business.db.NEAI0970_01TMsg;
import business.db.NEAI0970_02TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
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
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

import static com.canon.cusa.s21.batch.NWC.NWCB031001.constant.NWCB031001Constant.*;

/**
 *<pre>
 * Invoice EDI Translation for Outsourcer
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   Fujitsu         M.Nakamura      Create
 * 2016/08/17   SRAA            K.Aratani       Update          QC#12449
 * 2018/01/24   Fujitsu         A.Kosai         Update          S21_NA#22824
 * 2018/05/24   Fujitsu         T.Aoi           Update          S21_NA#21841
 *</pre>
 */
public class NWCB031001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Mail List */
    private List<Map<String, String>> mailErrorList = new ArrayList<Map<String, String>>(20);

    /** Interface ID */
    private String intfcId = null;

    /** Sales Date */
    private String slsDt = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM-LowLevel-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**  Message IDs */
    private static enum MSG_ID {
        /** Data insert failure.(ReturnCode = [@]) */
        ZZZM9012E,
        /** Data update failure.(ReturnCode = [@]) */
        ZZZM9013E,
        /** [@] is mandatory. */
        ZZZM9025E,
        /** It failed to register Mail. */
        NWCM0060E,
        /** It failed to get [@].(@) */
        NWCM0112E,
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NWCB031001().executeBatch(NWCB031001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        // Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Interface ID"));
        }

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate();
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Sales Date"));
        }

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String invNum = null;
        String creditCardInfo = "";
        String isEasyPackFlg = ZYPConstant.FLG_OFF_N;
        BigDecimal unitId = BigDecimal.ZERO;
        BigDecimal sqNum = BigDecimal.ZERO;
        try {

            // Get EDI Invoice target data
            ps = getInvoiceTargetData();
            rs = ps.executeQuery();

            S21TransactionTableAccessor trxTblAccessor = new S21TransactionTableAccessor();
            BigDecimal trxId = null;
            int counter = 0;
            while (rs.next()) {
                if (counter == 0) {
                    trxId = trxTblAccessor.getNextTransactionId();
                }
                
                invNum = rs.getString("INV_NUM");

                // Get header data
                Map<String, Object> headerDataMap = getHeaderData(invNum, rs.getString("DS_EDI_TRD_PTNR_CD"));

                // Get Ship To Information
                Map<String, Object> shipToInfoMap = null;
                if (TYPE_OM.equals(rs.getString("TYPE"))) {
                    shipToInfoMap = getShipToInfoOM(invNum);
                } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && SVC_INV_SRC_TP.CONTRACT.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
                    shipToInfoMap = getShipToInfoSvcContr(invNum, (String) headerDataMap.get("INV_DT"));
                } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && SVC_INV_SRC_TP.DISPATCH.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
                    shipToInfoMap = getShipToInfoSvcDspt(invNum, (String) headerDataMap.get("INV_DT"));
                } else {
                    S21InfoLogOutput.println("Get Error: TYPE=" + rs.getString("TYPE") + ", SVC_INV_SRC_TP=" + rs.getString("SVC_INV_SRC_TP_CD"));
                    setMailMessage(invNum, MSG_ID.NWCM0112E.toString(), new String[] {"Ship To Information", "Inv#=" + invNum});
                    this.totalErrCount = this.totalErrCount + 1;
                    rollback();
                    continue;
                }

                // Get Credit Card Information
                Map<String, Object> crCardInfoMap = null;
                if (ZYPConstant.FLG_ON_Y.equals(headerDataMap.get("PMT_CC_FLG"))) {
                    if (!SYS_SRC.S21_ACCOUNTING_AR.equals(headerDataMap.get("SYS_SRC_CD")) && ZYPCommonFunc.hasValue((String) headerDataMap.get("CPO_ORD_NUM"))) {
                        crCardInfoMap = getCrCardInfo(createCrCardInfoParam(CR_CARD_TRX_TP.CPO, (String) headerDataMap.get("CPO_ORD_NUM"), CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED, null, ZYPConstant.FLG_OFF_N));
                        if (crCardInfoMap == null) {
                            crCardInfoMap = getCrCardInfo(createCrCardInfoParam(CR_CARD_TRX_TP.CPO, (String) headerDataMap.get("CPO_ORD_NUM"), CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED, null, ZYPConstant.FLG_ON_Y));
                        }
                    } else if (!SYS_SRC.S21_ACCOUNTING_AR.equals((String) headerDataMap.get("SYS_SRC_CD")) && SVC_INV_SRC_TP.DISPATCH.equals(rs.getString("SVC_INV_SRC_TP_CD"))
                            && ZYPCommonFunc.hasValue((String) headerDataMap.get("FSR_NUM"))) {
                        crCardInfoMap = getCrCardInfo(createCrCardInfoParam(CR_CARD_TRX_TP.SERVICE_REQUEST, (String) headerDataMap.get("FSR_NUM"), CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED, (String) headerDataMap.get("CR_CARD_CUST_REF_NUM"), null));
                        if (crCardInfoMap == null) {
                            crCardInfoMap = getCrCardInfo(createCrCardInfoParam(CR_CARD_TRX_TP.SERVICE_REQUEST, (String) headerDataMap.get("FSR_NUM"), CR_CARD_AUTH_STS.SAVED, (String) headerDataMap.get("CR_CARD_CUST_REF_NUM"), null));
                        }
                    } else if (!SYS_SRC.S21_ACCOUNTING_AR.equals((String) headerDataMap.get("SYS_SRC_CD")) && SVC_INV_SRC_TP.CONTRACT.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
                        crCardInfoMap = getCrCardInfoCntr(createCrCardInfoParam(null, null, null, (String) headerDataMap.get("CR_CARD_CUST_REF_NUM"), null));
                    } else {
                        crCardInfoMap = getCrCardInfo(createCrCardInfoParam(CR_CARD_TRX_TP.INVOICE, invNum, CR_CARD_AUTH_STS.AUTHORIZED_COMPLETED, null, null));
                        if (crCardInfoMap == null) {
                            crCardInfoMap = getCrCardInfo(createCrCardInfoParam(CR_CARD_TRX_TP.INVOICE, invNum, CR_CARD_AUTH_STS.SAVED, null, null));
                        }
                    }
                }

                creditCardInfo = "";
                if (crCardInfoMap != null) {
                    creditCardInfo = "PAID BY CREDIT CARD:  CREDIT CARD TYPE "
                        + (String) crCardInfoMap.get("CR_CARD_TP_CD")
                        + ", CREDIT CARD NUMBER XXXX-XXXX-XXXX-"
                        + (String) crCardInfoMap.get("CR_CARD_LAST_DIGIT_NUM")
                        + ", EXP. DATE "
                        + (String) crCardInfoMap.get("CR_CARD_EXPR_YR_MTH");
                }

                // Check Easy Pack
                isEasyPackFlg = isEasyPack(headerDataMap);

                unitId = unitId.add(BigDecimal.ONE);
                sqNum = BigDecimal.ONE;

                // Insert NEAI0970_01
                if (!insertNEAI0970_01(rs, headerDataMap, shipToInfoMap, crCardInfoMap, trxId, unitId, sqNum, creditCardInfo)) {
                    // Error Message.
                    this.totalErrCount = this.totalErrCount + 1;
                    rollback();
                    continue;
                }

                // Create NEAI0970_02
                if (!createNEAI0970_02(invNum, rs, headerDataMap, trxId, unitId, sqNum, isEasyPackFlg)) {
                    this.totalErrCount = this.totalErrCount + 1;
                    rollback();
                    continue;
                }

                // Get target INV_PRT_CTRL
                List<BigDecimal> invPrtCtrlList = getInvPrtCtrl(invNum);

                for (BigDecimal invPrtCtrlPk  : invPrtCtrlList) {
                    INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
                    ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, invPrtCtrlPk);

                    INV_PRT_CTRLTMsg updTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(invPrtCtrlTMsg);

                    ZYPEZDItemValueSetter.setValue(updTMsg.invEdiProcStsCd, INV_PROC_STS.PROCESSED);
                    ZYPEZDItemValueSetter.setValue(updTMsg.invEdiProcDt, this.slsDt);
                    EZDTBLAccessor.update(updTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                        setMailMessage(invNum, MSG_ID.ZZZM9013E.toString(), new String[] {updTMsg.getReturnCode()});
                        this.totalErrCount = this.totalErrCount + 1;
                        rollback();
                        break;
                    }
                }

                counter++;
                // Every Invoice.
                commit();
            }

            if (ZYPCommonFunc.hasValue(trxId)) {
                // Create interface transaction record.
                trxTblAccessor.createIntegrationRecordForBatch(this.intfcId, trxId);
            }
            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        this.totalCount = this.totalNmlCount + this.totalErrCount;
        if (this.totalErrCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
            postErrMail();
        }
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * execParam.
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * getInvoiceTargetData.
     * @return PreparedStatement
     * @throws SQLException SQLException
     */
    private PreparedStatement getInvoiceTargetData() throws SQLException {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invEdiProcStsCd", INV_PROC_STS.NOT_PROCESSED);
        stesParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.TRADING_PARTNER_CODE_MAPPING_FOR_ORACLE);
        stesParam.put("intfcId", this.intfcId);
        stesParam.put("flgY", ZYPConstant.FLG_ON_Y);
        stesParam.put("flgN", ZYPConstant.FLG_OFF_N);
        stesParam.put("invTpCreditMemo", INV_TP.CREDIT_MEMO);
        stesParam.put("amountZero", BigDecimal.ZERO);
        stesParam.put("svcInvSrcTpContract", SVC_INV_SRC_TP.CONTRACT);
        stesParam.put("svcInvSrcTpDispatch", SVC_INV_SRC_TP.DISPATCH);
        stesParam.put("typeOM", TYPE_OM);
        stesParam.put("typeSVC", TYPE_SERVICE);
        return this.ssmLLClient.createPreparedStatement("getInvoiceTargetData", stesParam, execParam());
    }

    /**
     * getHeaderData.
     * @param invNum String
     * @param dsEdiTrdPtnrCd String
     * @return Map<String, Object>
     */
    private Map<String, Object> getHeaderData(String invNum, String dsEdiTrdPtnrCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("flgY", ZYPConstant.FLG_ON_Y);
        queryParam.put("flgN", ZYPConstant.FLG_OFF_N);
        queryParam.put("phoneWrk", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("taxTpState", DS_SLS_TAX_TP.STATE_TAX);
        queryParam.put("taxTpCounty", DS_SLS_TAX_TP.COUNTY_TAX);
        queryParam.put("taxTpCity", DS_SLS_TAX_TP.CITY_TAX);
        queryParam.put("taxTpFrtState", DS_SLS_TAX_TP.FREIGHT_STATE_TAX);
        queryParam.put("taxTpFrtCounty", DS_SLS_TAX_TP.FREIGHT_COUNTY_TAX);
        queryParam.put("taxTpFrtCity", DS_SLS_TAX_TP.FREIGHT_CITY_TAX);
        queryParam.put("xrefAcctTpOracle", DS_XREF_ACCT_TP.ORACLE);
        queryParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.PAYMENT_TERM_CASH_DESC_MAPPING_FOR_ORACLE);
        queryParam.put("intfcId", this.intfcId);
        queryParam.put("dsEdiTrdPtnrCd", dsEdiTrdPtnrCd);
        queryParam.put("dateFmt", ZYPDateUtil.TYPE_YYYYMMDD);
        queryParam.put("dateFmtDD", ZYPDateUtil.TYPE_DD);
        queryParam.put("invTpCdCr", INV_TP.CREDIT_MEMO);
        queryParam.put("invTpNmCr", CREDIT_CD);
        queryParam.put("invTpNmDr", DEBIT_CD);
        queryParam.put("lpadInvTotDealNetAmt", createLPadSqlFmt("I.INV_TOT_DEAL_NET_AMT", DEC_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));
        queryParam.put("lpadINvTotDealTaxAmt", createLPadSqlFmt("I.INV_TOT_DEAL_TAX_AMT", DEC_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));
        queryParam.put("lpadStTaxAmt", createLPadSqlFmt("TAX_ST.DEAL_SLS_TAX_AMT", DEC_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));
        queryParam.put("lpadCntyTaxAmt", createLPadSqlFmt("TAX_CNTY.DEAL_SLS_TAX_AMT", DEC_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));
        queryParam.put("lpadCityTaxAmt", createLPadSqlFmt("TAX_CITY.DEAL_SLS_TAX_AMT", DEC_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        queryParam.put("lpadRightPrm", "'" + DEC_FORMAT_35 + "'), " + LEN_35.toString() + ", '" + BigDecimal.ZERO.toString() + "')");

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getHeaderData", queryParam, execParam());
        return result;
    }

    private String createLPadSqlFmt(String columnNm, String fmt, String length, String str) {
        StringBuilder sb = new StringBuilder();

        // LPAD(TO_CHAR(NVL(columnNm, 0), 'format'), length, 'string')
        sb = sb.append("LPAD(TO_CHAR(NVL(");
        sb = sb.append(columnNm);
        sb = sb.append(", 0), '").append(fmt);
        sb = sb.append("'), ").append(length);
        sb = sb.append(", '").append(str).append("')");

        return sb.toString();
    }

    /**
     * getShipToInfoOM.
     * @param invNum String
     * @return Map<String, Object>
     */
    private Map<String, Object> getShipToInfoOM(String invNum) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("xrefAcctTpOracle", DS_XREF_ACCT_TP.ORACLE);
        queryParam.put("numOne", BigDecimal.ONE);

        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getShipToInfoOM", queryParam, execParam());
        return result;
    }

    /**
     * getShipToInfoSvcContr.
     * @param invNum String
     * @param invDt  String
     * @return Map<String, Object>
     */
    private Map<String, Object> getShipToInfoSvcContr(String invNum, String invDt) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invDt", invDt);
        queryParam.put("xrefAcctTpOracle", DS_XREF_ACCT_TP.ORACLE);
        queryParam.put("numOne", BigDecimal.ONE);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getShipToInfoSvcContr", queryParam, execParam());
        return result;
    }

    /**
     * getShipToInfoSvcDspt.
     * @param invNum String
     * @param invDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> getShipToInfoSvcDspt(String invNum, String invDt) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invDt", invDt);
        queryParam.put("xrefAcctTpOracle", DS_XREF_ACCT_TP.ORACLE);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getShipToInfoSvcDspt", queryParam, execParam());
        return result;
    }

    /**
     * isEasyPack
     * @param headerData Map<String, Object>
     * @return String
     */
    private String isEasyPack(Map<String, Object> headerData) {

        if (!ZYPCommonFunc.hasValue((String) headerData.get("DS_ORD_CATG_CD"))) {
            return ZYPConstant.FLG_OFF_N;
        }

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dsOrdCatgCd", (String) headerData.get("DS_ORD_CATG_CD"));
        queryParam.put("dsOrdTpCd", (String) headerData.get("DS_ORD_TP_CD"));
        queryParam.put("dsOrdRsnCd", (String) headerData.get("DS_ORD_RSN_CD"));
        queryParam.put("easyPack1", ORD_CATG_CTX_TP.EASY_PAC1);
        queryParam.put("easyPack1Return", ORD_CATG_CTX_TP.EASY_PAC1_RETURN);
        queryParam.put("billtoCustAcctCd", (String) headerData.get("BILL_TO_CUST_ACCT_CD"));
        String ordDt = (String) headerData.get("ORD_DT");
        String invDt = (String) headerData.get("INV_DT");
        if (ordDt != null && ordDt.length() != 0) {
            queryParam.put("effDt", ordDt);
        } else {
            queryParam.put("effDt", invDt);
        }
        queryParam.put("limitDate", LIMIT_DATE);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getEasyPack", queryParam, execParam());
        if (result == null) {
            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    private Map<String, Object> createCrCardInfoParam(String crCardTrxTpCd, String firstTrxInfoTxt, String crCardAuthStsCd, String crCardCustRefNum, String setlCpltFlg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("crCardTrxTpCd", crCardTrxTpCd);
        queryParam.put("firstTrxInfoTxt", firstTrxInfoTxt);
        queryParam.put("crCardCustRefNum", crCardCustRefNum);
        queryParam.put("crCardAuthStsCd", crCardAuthStsCd);
        queryParam.put("setlCpltFlg", setlCpltFlg);
        queryParam.put("numOne", BigDecimal.ONE);

        return queryParam;
    }

    private Map<String, Object> getCrCardInfo(Map<String, Object> queryParam) {
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCrCardInfo", queryParam, execParam());
        return result;
    }

    private Map<String, Object> getCrCardInfoCntr(Map<String, Object> queryParam) {
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCrCardInfoCntr", queryParam, execParam());
        return result;
    }
    /**
     * getInvoiceDetailTargetDataOM.
     * @return PreparedStatement
     * @throws SQLException SQLException
     */
    private PreparedStatement getInvoiceDetailTargetDataOM(String invNum, String sellToCustCd, String invDt, String isEasyPackFlg) throws SQLException {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invNum", invNum);
        stesParam.put("invTpCdCr", INV_TP.CREDIT_MEMO);
        stesParam.put("flgY", ZYPConstant.FLG_ON_Y);
        stesParam.put("flgN", ZYPConstant.FLG_OFF_N);
        stesParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        stesParam.put("isEasyPackFlg", isEasyPackFlg);
        stesParam.put("mdseShipping", MDSE_SHIPPING);

        stesParam.put("itemTypeMShipping", ITEM_TYPE_M_SHIPPING);
        stesParam.put("itemTypeSToner", ITEM_TYPE_S_TONER);
        stesParam.put("itemTypeEBaseunit", ITEM_TYPE_E_BASEUNIT);

        stesParam.put("lpadShipQty", createLPadSqlFmt("T.SHIP_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod Start
        //stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod End
        stesParam.put("lpadInvLineDealNetAmt", createLPadSqlFmt("T.INV_LINE_DEAL_NET_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadInvLineDealTaxAmt", createLPadSqlFmt("T.INV_LINE_DEAL_TAX_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadTaxPct", createLPadSqlFmt("T.TAX_PCT", PCT_FORMAT_10, BigDecimal.TEN.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadOrdQty", createLPadSqlFmt("T.ORD_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDsContrNum", createLPadSqlFmt("T.DS_CONTR_NUM", INT_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        stesParam.put("strIntFmt20", STR_INT_FORMAT_20);
        stesParam.put("strIntFmt22", STR_INT_FORMAT_22);
        stesParam.put("strDecFmt22", STR_DEC_FORMAT_22);

        stesParam.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT); // 2018/05/24 QC#21841 Add

        return this.ssmLLClient.createPreparedStatement("getInvoiceDetailTargetDataOM", stesParam, execParam());
    }

    /**
     * getInvoiceDetailTargetDataFlt.
     * @return PreparedStatement
     * @throws SQLException SQLException
     */
    private PreparedStatement getInvoiceDetailTargetDataSvcFlt(String invNum) throws SQLException {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invNum", invNum);
        stesParam.put("invTpCdCr", INV_TP.CREDIT_MEMO);
        stesParam.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        stesParam.put("svcInvChrgTpMC", SVC_INV_CHRG_TP.METER_CHARGE);
        stesParam.put("srcNmRental", SRC_NM_RENTAL);
        stesParam.put("itemTypeCRental", ITEM_TYPE_C_RENTAL);
        stesParam.put("itemTypeEBaseunit", ITEM_TYPE_E_BASEUNIT);
        stesParam.put("itemTypeU", ITEM_TYPE_U);
        stesParam.put("itemTypeBW", ITEM_TYPE_BW);
        stesParam.put("itemTypeColor", ITEM_TYPE_COLOR);
        stesParam.put("flgY", ZYPConstant.FLG_ON_Y);
        stesParam.put("invPer", INV_PER);
        stesParam.put("usgPer", USG_PER);
        stesParam.put("decFmt22", DEC_FORMAT_22);
        stesParam.put("lpadSvcContrBllgPk", createLPadSqlFmt("T.SVC_CONTR_BLLG_PK", INT_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        stesParam.put("lpadShipQty", createLPadSqlFmt("T.SHIP_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod Start
        //stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod End
        stesParam.put("lpadInvLineDealNetAmt", createLPadSqlFmt("T.INV_LINE_DEAL_NET_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadInvLineDealTaxAmt", createLPadSqlFmt("T.INV_LINE_DEAL_TAX_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadTaxPct", createLPadSqlFmt("T.TAX_PCT", PCT_FORMAT_10, BigDecimal.TEN.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadOrdQty", createLPadSqlFmt("T.ORD_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDsContrNum", createLPadSqlFmt("T.DS_CONTR_NUM", INT_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        stesParam.put("strIntFmt20", STR_INT_FORMAT_20);
        stesParam.put("strIntFmt22", STR_INT_FORMAT_22);
        stesParam.put("strIntFmt35", STR_INT_FORMAT_35);
        stesParam.put("strDecFmt22", STR_DEC_FORMAT_22);

        stesParam.put("lpadSumPrevTotCopyQty", createLPadSqlFmt("SILM.PREV_TOT_COPY_QTY", INT_FORMAT_22, LEN_22.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadSumTotCopyQty", createLPadSqlFmt("SILM.TOT_COPY_QTY", INT_FORMAT_22, LEN_22.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadSumTotAlwnc", createLPadSqlFmt("SILM.TEST_COPY_QTY + SILM.COPY_INCL_QTY", INT_FORMAT_20, LEN_20.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadSumBllgCopyQty", createLPadSqlFmt("SILM.BLLG_COPY_QTY", INT_FORMAT_22, LEN_22.toString(), BigDecimal.ZERO.toString()));


        return this.ssmLLClient.createPreparedStatement("getInvoiceDetailTargetDataSvcFlt", stesParam, execParam());
    }

    /**
     * getInvoiceDetailTargetDataRegAgg.
     * @return PreparedStatement
     * @throws SQLException SQLException
     */
    private PreparedStatement getInvoiceDetailTargetDataRegAgg(String invNum) throws SQLException {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invNum", invNum);
        stesParam.put("invTpCdCr", INV_TP.CREDIT_MEMO);
        stesParam.put("svcInvLineTpAcsy", SVC_INV_LINE_TP.ACCESSORY);
        stesParam.put("srcNmRental", SRC_NM_RENTAL);
        stesParam.put("itemTypeCPeri", ITEM_TYPE_C_PERI);
        stesParam.put("itemTypeCRental", ITEM_TYPE_C_RENTAL);
        stesParam.put("itemTypeEBaseunit", ITEM_TYPE_E_BASEUNIT);
        stesParam.put("itemTypeU", ITEM_TYPE_U);
        stesParam.put("itemTypeBW", ITEM_TYPE_BW);
        stesParam.put("itemTypeColor", ITEM_TYPE_COLOR);
        stesParam.put("invPer", INV_PER);
        stesParam.put("usgPer", USG_PER);
        stesParam.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        stesParam.put("svcInvChrgTpMC", SVC_INV_CHRG_TP.METER_CHARGE);
        stesParam.put("flgY", ZYPConstant.FLG_ON_Y);
        stesParam.put("sepStr", SEP_STR);
        stesParam.put("decFmt22", DEC_FORMAT_22);
        stesParam.put("lpadSvcContrBllgPk", createLPadSqlFmt("T.SVC_CONTR_BLLG_PK", INT_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        stesParam.put("lpadShipQty", createLPadSqlFmt("T.SHIP_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod Start
        //stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod End
        stesParam.put("lpadInvLineDealNetAmt", createLPadSqlFmt("T.INV_LINE_DEAL_NET_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadInvLineDealTaxAmt", createLPadSqlFmt("T.INV_LINE_DEAL_TAX_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadTaxPct", createLPadSqlFmt("T.TAX_PCT", PCT_FORMAT_10, BigDecimal.TEN.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadOrdQty", createLPadSqlFmt("T.ORD_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDsContrNum", createLPadSqlFmt("T.DS_CONTR_NUM", INT_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        stesParam.put("strIntFmt20", STR_INT_FORMAT_20);
        stesParam.put("strIntFmt22", STR_INT_FORMAT_22);
        stesParam.put("strDecFmt22", STR_DEC_FORMAT_22);

        stesParam.put("lpadSumPrevTotCopyQty", createLPadSqlFmt("SUM_SILM.SUM_PREV_TOT_COPY_QTY", INT_FORMAT_22, LEN_22.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadSumTotCopyQty", createLPadSqlFmt("SUM_SILM.SUM_TOT_COPY_QTY", INT_FORMAT_22, LEN_22.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadSumTotAlwnc", createLPadSqlFmt("SUM_SILM.SUM_TOT_ALWNC", INT_FORMAT_20, LEN_20.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadSumBllgCopyQty", createLPadSqlFmt("SUM_SILM.SUM_BLLG_COPY_QTY", INT_FORMAT_22, LEN_22.toString(), BigDecimal.ZERO.toString()));

        return this.ssmLLClient.createPreparedStatement("getInvoiceDetailTargetDataRegAgg", stesParam, execParam());
    }

    /**
     * getInvoiceDetailTargetDataDspt.
     * @return PreparedStatement
     * @throws SQLException SQLException
     */
    private PreparedStatement getInvoiceDetailTargetDataDspt(String invNum) throws SQLException {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invNum", invNum);
        stesParam.put("invTpCdCr", INV_TP.CREDIT_MEMO);
        stesParam.put("itemTypeMLabor", ITEM_TYPE_M_LABOR);
        stesParam.put("sepStr", SEP_STR);

        stesParam.put("lpadShipQty", createLPadSqlFmt("T.SHIP_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod Start
        //stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDealNetUnitPrcAmt", createLPadSqlFmt("T.DEAL_NET_UNIT_PRC_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        // 2018/01/24 S21_NA#22824 Mod End
        stesParam.put("lpadInvLineDealNetAmt", createLPadSqlFmt("T.INV_LINE_DEAL_NET_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadInvLineDealTaxAmt", createLPadSqlFmt("T.INV_LINE_DEAL_TAX_AMT", DEC_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadTaxPct", createLPadSqlFmt("T.TAX_PCT", PCT_FORMAT_10, BigDecimal.TEN.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadOrdQty", createLPadSqlFmt("T.ORD_QTY", INT_FORMAT_34, LEN_35.toString(), BigDecimal.ZERO.toString()));
        stesParam.put("lpadDsContrNum", createLPadSqlFmt("T.DS_CONTR_NUM", INT_FORMAT_35, LEN_35.toString(), BigDecimal.ZERO.toString()));

        stesParam.put("strIntFmt20", STR_INT_FORMAT_20);
        stesParam.put("strIntFmt22", STR_INT_FORMAT_22);
        stesParam.put("strDecFmt22", STR_DEC_FORMAT_22);

        return this.ssmLLClient.createPreparedStatement("getInvoiceDetailTargetDataDspt", stesParam, execParam());
    }

    private boolean insertNEAI0970_01(ResultSet rs, Map<String, Object> headerDataMap, Map<String, Object> shipToInfoMap, Map<String, Object> crCardInfoMap, BigDecimal trxId, BigDecimal unitId, BigDecimal sqNum, String creditCardInfo) throws SQLException {
        NEAI0970_01TMsg tMsg = new NEAI0970_01TMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, sqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRecTpCd, NEAI0970_01_REC_TP);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrxNum, (String) headerDataMap.get("INV_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrxDt, (String) headerDataMap.get("INV_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrdPtnrCd, rs.getString("DS_EDI_TRD_PTNR_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrdPtnrXtrnlCd, rs.getString("DS_EDI_TRD_PTNR_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrdPtnrNm, rs.getString("DS_EDI_TRD_PTNR_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvPoNum, (String) headerDataMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvPoDt, (String) headerDataMap.get("CUST_ISS_PO_DT"));
        tMsg.ediInvPoRevnNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvOrdNum, (String) headerDataMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvOrdDt, (String) headerDataMap.get("ORD_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvDrCrCd, (String) headerDataMap.get("INV_TYPE"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToCustNum, (String) headerDataMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToCustNm, (String) headerDataMap.get("BILL_TO_CUST_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToAddr_01, (String) headerDataMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToAddr_02, (String) headerDataMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToAddr_03, (String) headerDataMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToAddr_04, (String) headerDataMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToCityAddr, (String) headerDataMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToStCd, (String) headerDataMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToPostCd, (String) headerDataMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToCntyNm, (String) headerDataMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToCtryCd, (String) headerDataMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillCtacLastNm, (String) headerDataMap.get("BILL_TO_CTAC_PSN_LAST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillCtacFirstNm, (String) headerDataMap.get("BILL_TO_CTAC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillCtacTelNum, (String) headerDataMap.get("DS_CTAC_PNT_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillToSicCd, (String) headerDataMap.get("DS_CUST_SIC_CD"));
        tMsg.ediInvBillCustLocTxt.clear();
        tMsg.ediInvBillCustItrlCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillCustXtrnlCd, (String) headerDataMap.get("DS_XREF_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToCustNum, (String) shipToInfoMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToCustNm, (String) shipToInfoMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToAddr_01, (String) shipToInfoMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToAddr_02, (String) shipToInfoMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToAddr_03, (String) shipToInfoMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToAddr_04, (String) shipToInfoMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToCityAddr, (String) shipToInfoMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToStCd, (String) shipToInfoMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToCtryCd, (String) shipToInfoMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToPostCd, (String) shipToInfoMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipToSicCd, (String) shipToInfoMap.get("DS_CUST_SIC_CD"));
        tMsg.ediInvShipCustLocTxt.clear();
        tMsg.ediInvShipCustItrlCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvShipCustXtrnlCd, (String) shipToInfoMap.get("DS_XREF_ACCT_CD"));
        tMsg.ediInvRemCustNm.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemAddr_01, (String) headerDataMap.get("REM_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemAddr_02, (String) headerDataMap.get("REM_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemAddr_03, (String) headerDataMap.get("REM_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemAddr_04, (String) headerDataMap.get("REM_FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemCityAddr, (String) headerDataMap.get("REM_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemStCd, (String) headerDataMap.get("REM_ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRemPostCd, (String) headerDataMap.get("REM_POST_CD"));
        tMsg.ediInvCrInvQlfyCd.clear();
        tMsg.ediInvCrInvNum.clear();
        tMsg.ediInvCrInvDt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTermTpNm, (String) headerDataMap.get("SRC_ATTRB_TXT_01"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTermPctDiscTxt, STR_INT_FORMAT_35);
        tMsg.ediInvTermDiscDueDt.clear();
        tMsg.ediInvTermAotDiscTxt.clear();
        tMsg.ediInvRteTxt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtTotTxt, ((String) headerDataMap.get("INV_TOT_DEAL_NET_AMT")));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtTaxTxt, ((String) headerDataMap.get("INV_TOT_DEAL_TAX_AMT")));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtShipTxt, STR_INT_FORMAT_35);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtIstlTxt, STR_INT_FORMAT_35);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvOrdLocTxt, (String) headerDataMap.get("AR_COA_ACCT_CD"));
        tMsg.ediInvSplyRefCd.clear();
        tMsg.ediInvCityFipsCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtCityTaxTxt, ((String) headerDataMap.get("CITY_TAX_AMT")));
        tMsg.ediInvCntyFipsCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtCntyTaxTxt, ((String) headerDataMap.get("CNTY_TAX_AMT")));
        tMsg.ediInvStFipsCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtStTaxTxt, ((String) headerDataMap.get("ST_TAX_AMT")));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTermDueDt, (String) headerDataMap.get("NET_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTermAotNetTxt, (String) headerDataMap.get("NET_DUE_DAYS"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTermDescTxt, (String) headerDataMap.get("PMT_TERM_NM"));
        tMsg.ediInvShipDt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvWtGrsTxt, STR_WT_GRS_22_100);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvBaseCcyCd, (String) headerDataMap.get("DEAL_CCY_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRunId, String.format("%022d", trxId.toBigInteger()));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvCrCardInfoTxt, creditCardInfo);

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println("NEAI0970_01 Insert Error: TrxId=" + trxId.toString() + ", unitId=" + unitId.toString() + ", sqNum=" + sqNum.toString());
            setMailMessage((String) headerDataMap.get("INV_NUM"), MSG_ID.ZZZM9012E.toString(), new String[] {tMsg.getReturnCode() });
            return false;
        }

        return true;
    }

    private boolean createNEAI0970_02(String invNum, ResultSet rs, Map<String, Object> headerDataMap, BigDecimal trxId, BigDecimal unitId, BigDecimal sqNum, String isEasyPackFlg) throws SQLException {

        PreparedStatement psDtl = null;
        ResultSet rsDtl = null;
        try {
            // Get EDI Invoice Detail target data
            if (TYPE_OM.equals(rs.getString("TYPE"))) {
                psDtl = getInvoiceDetailTargetDataOM(invNum, (String) headerDataMap.get("SELL_TO_CUST_CD"), (String) headerDataMap.get("INV_DT"), isEasyPackFlg);
            } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && DS_CONTR_CATG.FLEET.equals((String) headerDataMap.get("DS_CONTR_CATG_CD"))) {
                psDtl = getInvoiceDetailTargetDataSvcFlt(invNum);
            } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && !DS_CONTR_CATG.FLEET.equals((String) headerDataMap.get("DS_CONTR_CATG_CD")) && SVC_INV_SRC_TP.CONTRACT.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
                psDtl = getInvoiceDetailTargetDataRegAgg(invNum);
            } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && !DS_CONTR_CATG.FLEET.equals((String) headerDataMap.get("DS_CONTR_CATG_CD")) && SVC_INV_SRC_TP.DISPATCH.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
                psDtl = getInvoiceDetailTargetDataDspt(invNum);
            } else {
                S21InfoLogOutput.println("Get Error: TYPE=" + rs.getString("TYPE") + ", DS_CONTR_CATG_CD=" + (String) headerDataMap.get("DS_CONTR_CATG_CD") + ", SVC_INV_SRC_TP=" + rs.getString("SVC_INV_SRC_TP_CD"));
                setMailMessage(invNum, MSG_ID.NWCM0112E.toString(), new String[] {"Detail Information", "Inv#=" + invNum});
                return false;
            }

            rsDtl = psDtl.executeQuery();
            BigDecimal lineNum = BigDecimal.ZERO;
            BigDecimal calcTotTaxAmt = BigDecimal.ZERO;
            BigDecimal lineTaxAmt = BigDecimal.ZERO;
            BigDecimal headTotTaxAmt = new BigDecimal((String) headerDataMap.get("INV_TOT_DEAL_TAX_AMT"));
            BigDecimal firstSqNum = BigDecimal.ZERO;
            boolean isFirstRec = true;
            boolean isExistUsgChrg = false;
            int dtlCnt = 0;

            while (rsDtl.next()) {
                sqNum = sqNum.add(BigDecimal.ONE);
                lineNum = lineNum.add(BigDecimal.ONE);

                if (!TYPE_OM.equals(rs.getString("TYPE"))) {
                    if (isFirstRec) {
                        isFirstRec = false;
                        firstSqNum = sqNum;
                    }
                    if (!isExistUsgChrg && USG_PER.equals(rsDtl.getString("USG_PER"))) {
                        isExistUsgChrg = true;
                        firstSqNum = sqNum;
                    }
                }

                if (!insertNEAI0970_02(rs, headerDataMap, rsDtl, trxId, unitId, sqNum, lineNum)) {
                    return false;
                }

                if (!TYPE_OM.equals(rs.getString("TYPE"))) {
                    lineTaxAmt = new BigDecimal(rsDtl.getString("INV_LINE_DEAL_TAX_AMT"));
                    calcTotTaxAmt = calcTotTaxAmt.add(lineTaxAmt.abs());
                }

                dtlCnt = dtlCnt + 1;
            }

            if (calcTotTaxAmt.compareTo(headTotTaxAmt) != 0
                    && !TYPE_OM.equals(rs.getString("TYPE"))) {

                if (!updateTaxAmount(trxId, unitId, firstSqNum, headTotTaxAmt.subtract(calcTotTaxAmt), invNum)) {
                    return false;
                }
            }

            this.totalNmlCount = this.totalNmlCount + dtlCnt;

        } finally {
            S21SsmLowLevelCodingClient.closeResource(psDtl, rsDtl);
        }

        return true;
    }
    private boolean insertNEAI0970_02(ResultSet rs, Map<String, Object> headerDataMap, ResultSet rsDtl, BigDecimal trxId, BigDecimal unitId, BigDecimal sqNum, BigDecimal lineNum) throws SQLException {

        NEAI0970_02TMsg tMsg = new NEAI0970_02TMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, sqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRecTpCd, NEAI0970_02_REC_TP);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrxNum, (String) headerDataMap.get("INV_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvTrxSrcCd, (String) headerDataMap.get("INV_SRC_TXT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvInvLineNum, lineNum.toString());
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvPoNum_01, rsDtl.getString("CUST_ISS_PO_NUM"));
        tMsg.ediInvPoLineNum.clear();
        tMsg.ediInvPoRevnNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvUomCd, rsDtl.getString("UOM_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvQtyInvTxt, rsDtl.getString("SHIP_QTY"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtUnitPrcTxt, rsDtl.getString("DEAL_NET_UNIT_PRC_AMT"));
        tMsg.ediInvCustItemNum.clear();
        tMsg.ediInvItemDescTxt.clear();
        tMsg.ediInvVndItemNum.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvUpcCd, rsDtl.getString("UPC_CD"));
        tMsg.ediInvSerNumEdiTxt_01.clear();
        tMsg.ediInvSerNumEdiTxt_02.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtLineTxt, rsDtl.getString("INV_LINE_DEAL_NET_AMT"));
        tMsg.ediInvAlwncChrgCd.clear();
        tMsg.ediInvAlwncChrgDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtAlwncChrgTxt, STR_INT_FORMAT_35);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtSlsTaxTxt, rsDtl.getString("INV_LINE_DEAL_TAX_AMT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvRateTaxTxt, rsDtl.getString("TAX_PCT"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtFrtChrgTxt, STR_INT_FORMAT_35);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvPctDiscTxt, STR_INT_FORMAT_5);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtDiscUnitTxt, STR_INT_FORMAT_25);
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvAmtTotDiscTxt, STR_INT_FORMAT_35);
        tMsg.ediInvShipPmtCd.clear();
        tMsg.ediInvCarrCd.clear();
        tMsg.ediInvCarrNm.clear();
        tMsg.ediInvBolNum.clear();
        tMsg.ediInvWtShipTxt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvLineItemNum, rsDtl.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvQtyOrdTxt, rsDtl.getString("ORD_QTY"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvContrEdiTxt, rsDtl.getString("DS_CONTR_NUM_35"));
        // ediInvInstNum is not same value.
        tMsg.ediInvIntfcAttrbCatgNm.clear();
        // ediInvBillFromDt is not same value.
        // ediInvBillThruDt is not same value.
        tMsg.ediInvCntCopyUsedTxt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvPoNum_02, (String) headerDataMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvItemTypeTxt, rsDtl.getString("ITEM_TYPE"));
        tMsg.ediInvConvBillThruDt.clear();
        tMsg.ediInvOrigStartDt.clear();
        tMsg.ediInvOrigEndDt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvSvcRefTpTxt, STR_SVC_REF_TP_TXT);
        // ediInvSerNum is not same value.
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvCustCtrlFldTxt, subStr(rsDtl.getString("EDI_INV_CUST_CTRL_FLD_TXT"), tMsg.getAttr("ediInvCustCtrlFldTxt").getDigit()));
        // ediInvBaseSerNum is not same value.
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvContrNum, rsDtl.getString("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvCtacModTxt, rsDtl.getString("CRAT_DRAFT_PSN_CD"));
        // ediInvCntFromReadTxt is not same value.
        // ediInvCntThruReadTxt is not same value.
        // ediInvCntAlwncTxt is not same value.
        // ediInvQtyBllgCopyTxt is not same value.
        ZYPEZDItemValueSetter.setValue(tMsg.ediInvDbmtCd, DISBURSEMENT_CODE);
        tMsg.ediInvMthRntlTxt.clear();
        // ediInvInvPerTxt is not same value.
        // ediInvRateXsMtrTxt is not same value.
        // ediInvUsgPerTxt is not same value.
        tMsg.ediInvUsgMtrQlfyTxt.clear();
        tMsg.ediInvMtrId.clear();
        tMsg.ediInvMeasEdiCd.clear();
        tMsg.ediInvMeasMultTxt.clear();
        tMsg.ediInvAttDescTxt.clear();
        if (TYPE_OM.equals(rs.getString("TYPE"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvInstNum, STR_INT_FORMAT_35);
            tMsg.ediInvBillFromDt.clear();
            tMsg.ediInvBillThruDt.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvSerNum, (String) headerDataMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBaseSerNum, (String) headerDataMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntFromReadTxt, STR_INT_FORMAT_22);
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntThruReadTxt, STR_INT_FORMAT_22);
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntAlwncTxt, STR_INT_FORMAT_20);
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvQtyBllgCopyTxt, STR_INT_FORMAT_22);
            tMsg.ediInvInvPerTxt.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvRateXsMtrTxt, STR_DEC_FORMAT_22);
            tMsg.ediInvUsgPerTxt.clear();
        } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && DS_CONTR_CATG.FLEET.equals((String) headerDataMap.get("DS_CONTR_CATG_CD"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvInstNum, rsDtl.getString("SVC_CONTR_BLLG_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillFromDt, rsDtl.getString("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillThruDt, rsDtl.getString("BLLG_PER_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvSerNum, rsDtl.getString("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBaseSerNum, rsDtl.getString("BASE_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntFromReadTxt, rsDtl.getString("SUM_PREV_TOT_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntThruReadTxt, rsDtl.getString("SUM_TOT_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntAlwncTxt, rsDtl.getString("SUM_TOT_ALWNC"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvQtyBllgCopyTxt, rsDtl.getString("SUM_BLLG_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvInvPerTxt, rsDtl.getString("INV_PER"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvRateXsMtrTxt, rsDtl.getString("USG_COST_PER_COPY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvUsgPerTxt, rsDtl.getString("USG_PER"));
        } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && !DS_CONTR_CATG.FLEET.equals((String) headerDataMap.get("DS_CONTR_CATG_CD")) && SVC_INV_SRC_TP.CONTRACT.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvInstNum, rsDtl.getString("SVC_CONTR_BLLG_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillFromDt, rsDtl.getString("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBillThruDt, rsDtl.getString("BLLG_PER_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvSerNum, rsDtl.getString("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBaseSerNum, rsDtl.getString("BASE_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntFromReadTxt, rsDtl.getString("SUM_PREV_TOT_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntThruReadTxt, rsDtl.getString("SUM_TOT_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntAlwncTxt, rsDtl.getString("SUM_TOT_ALWNC"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvQtyBllgCopyTxt, rsDtl.getString("SUM_BLLG_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvInvPerTxt, rsDtl.getString("INV_PER"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvRateXsMtrTxt, rsDtl.getString("USG_COST_PER_COPY"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvUsgPerTxt, rsDtl.getString("USG_PER"));
        } else if (TYPE_SERVICE.equals(rs.getString("TYPE")) && !DS_CONTR_CATG.FLEET.equals((String) headerDataMap.get("DS_CONTR_CATG_CD")) && SVC_INV_SRC_TP.DISPATCH.equals(rs.getString("SVC_INV_SRC_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvInstNum, STR_INT_FORMAT_35);
            tMsg.ediInvBillFromDt.clear();
            tMsg.ediInvBillThruDt.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvSerNum, (String) headerDataMap.get("CPO_ORD_NUM"));
            // 2018/01/24 S21_NA#22824 Mod Start
            //ZYPEZDItemValueSetter.setValue(tMsg.ediInvBaseSerNum, (String) headerDataMap.get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvBaseSerNum, rsDtl.getString("BASE_SER_NUM"));
            // 2018/01/24 S21_NA#22824 Mod End
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntFromReadTxt, STR_INT_FORMAT_22);
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntThruReadTxt, STR_INT_FORMAT_22);
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvCntAlwncTxt, STR_INT_FORMAT_20);
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvQtyBllgCopyTxt, STR_INT_FORMAT_22);
            tMsg.ediInvInvPerTxt.clear();
            ZYPEZDItemValueSetter.setValue(tMsg.ediInvRateXsMtrTxt, STR_DEC_FORMAT_22);
            tMsg.ediInvUsgPerTxt.clear();
        }

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println("NEAI0970_02 Insert Error: TrxId=" + trxId.toString() + ", unitId=" + unitId.toString() + ", sqNum=" + sqNum.toString());
            setMailMessage((String) headerDataMap.get("INV_NUM"), MSG_ID.ZZZM9012E.toString(), new String[] {tMsg.getReturnCode() });
            return false;
        }

        return true;
    }

    private boolean updateTaxAmount(BigDecimal trxId, BigDecimal unitId, BigDecimal firstSqNum, BigDecimal diffTaxAmt, String invNum) {
        NEAI0970_02TMsg inTMsg = new NEAI0970_02TMsg();

        ZYPEZDItemValueSetter.setValue(inTMsg.interfaceId, this.intfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(inTMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inTMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(inTMsg.seqNumber, firstSqNum);

        NEAI0970_02TMsg updTMsg = (NEAI0970_02TMsg) EZDTBLAccessor.findByKeyForUpdate(inTMsg);

        BigDecimal ediInvAmtSlsTax = new BigDecimal(updTMsg.ediInvAmtSlsTaxTxt.getValue());
        BigDecimal calcTaxAmt = BigDecimal.ZERO;

        if (BigDecimal.ZERO.compareTo(ediInvAmtSlsTax) > 0) {
            calcTaxAmt = ediInvAmtSlsTax.subtract(diffTaxAmt);
        } else {
            calcTaxAmt = ediInvAmtSlsTax.add(diffTaxAmt);
        }

        ZYPEZDItemValueSetter.setValue(updTMsg.ediInvAmtSlsTaxTxt, String.format("%035.2f", calcTaxAmt.doubleValue()));

        EZDTBLAccessor.update(updTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            S21InfoLogOutput.println("NEAI0970_02 Update Error: TrxId=" + trxId.toString() + ", unitId=" + unitId.toString() + ", sqNum=" + firstSqNum.toString());
            setMailMessage(invNum, MSG_ID.ZZZM9013E.toString(), new String[] {updTMsg.getReturnCode() });
            return false;
        }


        return true;
    }

    private List<BigDecimal> getInvPrtCtrl(String invNum) {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invNum", invNum);
        stesParam.put("notProcessed", INV_PROC_STS.NOT_PROCESSED);

        return this.ssmBatchClient.queryObjectList("getInvPrtCtrl", stesParam);
    }

    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    private void postErrMail() {

        // get mail information : address
        S21Mail mail = new S21Mail(glblCmpyCd);
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, GROUP_FROM);

        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList.isEmpty()) {
            throw new S21AbendException(MSG_ID.NWCM0060E.toString());
        }
        mail.setFromAddress(fromAddrList.get(0));

        S21MailGroup group = new S21MailGroup(glblCmpyCd, BUSINESS_ID);
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {
            throw new S21AbendException(MSG_ID.NWCM0060E.toString());
        }
        mail.setToAddressList(toAddrList);

        // get mail template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        template.setTemplateParameter("batchId", BUSINESS_ID);
        template.setTemplateParameter("batchNm", BATCH_NM);
        template.setTemplateParameter("batchProcLogId", super.getBatchProcessLogID());

        // set Message
        StringBuilder msg = new StringBuilder();
        msg.append(TITLE);
        for (Map<String, String> map : this.mailErrorList) {
            msg.append(LINE_FEED_CODE);
            msg.append(MSG_INV_NUM);
            msg.append(map.get(NUMBER));
            msg.append(SEPARATOR);
            msg.append(map.get(MESSAGE));
        }

        template.setTemplateParameter("ErrorInfo", msg.toString());
        mail.setMailTemplate(template);

        // post mail
        mail.postMail();
    }

    private String subStr(String str, int length) {
        if (!ZYPCommonFunc.hasValue(str)) {
            return "";
        }

        if (str.length() > length) {
            return str.substring(0, length);
        }

        return str;
    }

    /**
     * setMailMessage
     * @param number String
     * @param msgId String
     * @param msgParam String[]
     */
    private void setMailMessage(String number, String msgId, String[] msgParam) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(NUMBER, number);
        map.put(MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        mailErrorList.add(map);
    }
}
