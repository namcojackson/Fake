/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB003001;

import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.DELIM;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.KEY_CLT_STRGY_MAIL_ROW_CNT;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_CREDIT_INV_LIST;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_CREDIT_MEMO_LIST;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_CREDIT_PYMT_LIST;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_ID;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_KEY_ACCT_NUM;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_KEY_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAIL_TMPL_KEY_CUST_TP;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MAX_ROW;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.MORE_DATA_EXISTS_MSG;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.NFBM0184E;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.NFDM0004E;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.NOTIFY_COLLECTOR_ITEM_CD;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.UPDATE_CNT;
import static com.canon.cusa.s21.batch.NFD.NFDB003001.NFDB003001Constant.ZZZM9025E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/20/2015   Fujitsu         Y.Kamide        Create          N/A
 * 01/11/2016   CSAI            K.Uramori       Update          Apply fixed specification
 * 03/18/2016   CSAI            K.Uramori       Update          QC#5525
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2017/01/05   Hitachi         E.Kameishi      Update          QC#16817
 * 2017/06/26   Hitachi         E.Kameishi      Update          QC#18754
 * 2021/07/01   CITS            G.Delgado       Update          QC#58909
 *</pre>
 */
public class NFDB003001 extends S21BatchMain implements ZYPConstant {

    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    /** maxRowCnt */
    private int maxRowCnt = 0;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    
    /** Default email address */
    private String defEmlAdd = "";
    
    /** Default email address list */
    private List<String> defEmlAddList = new ArrayList<String> ();

    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        BigDecimal numConstVal = ZYPCodeDataUtil.getNumConstValue(KEY_CLT_STRGY_MAIL_ROW_CNT, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(numConstVal)) {
            this.maxRowCnt = numConstVal.intValue();
        } else {
            this.maxRowCnt = MAX_ROW;
        }
        
        //----- start add 2016/01/11 get default email address from VAR_CHAR_CONST
        defEmlAdd = ZYPCodeDataUtil.getVarCharConstValue(NFDB003001Constant.AR_CLT_DEF_EML_ADDR, this.glblCmpyCd);
        if (!hasValue(defEmlAdd)) {
            // error
            throw new S21AbendException(ZZZM9025E, new String[] {"Default Collector's Email Address" });
        }
        // split the email addresses
        divDefEmlAdd();
        //----- end 2016/01/11
    }
    
    private void divDefEmlAdd() {
        StringTokenizer st = new StringTokenizer(defEmlAdd, ",");
        
        while (st.hasMoreTokens()) {
            defEmlAddList.add(st.nextToken());
        }

    }

    @Override
    protected void mainRoutine() {
        notifyCollector();
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        new NFDB003001().executeBatch(NFDB003001.class.getSimpleName());
    }

    /**
     * notifyCollector
     */
    private void notifyCollector() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getWorkItemTran();
            rs = ps.executeQuery();
            Map<String, MailData> mailDataMap = new HashMap<String, MailData>();
            while (rs.next()) {
                String billToCustCd = rs.getString("BILL_TO_CUST_CD");
                if (billToCustCd != null && !mailDataMap.containsKey(billToCustCd)) {
                    mailDataMap.put(billToCustCd, createMailData(rs));
                }
            }

            notifyCollector(mailDataMap);

            rs.beforeFirst();
            updateCltStrtgyWrkItemTrx(rs);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @throws SQLException
     */
    private void updateCltStrtgyWrkItemTrx(ResultSet rs) throws SQLException {

        List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList = new ArrayList<CLT_STRGY_WRK_ITEM_TRXTMsg>();
        while (rs.next()) {

            BigDecimal cltStrgyWorkItemTrxPk = rs.getBigDecimal("CLT_STRGY_WRK_ITEM_TRX_PK");
            CLT_STRGY_WRK_ITEM_TRXTMsg updateMsg = getCltStrtgyWrkItemTrxForUpdate(cltStrgyWorkItemTrxPk);
            if (updateMsg == null) {
                continue;
            }

            setValue(updateMsg.cltWrkItemWsrdDt, this.batProcDt);
            
            //---- start mod 2016/01/11 update to "Close"
            // START 2016/09/26 K.Kojima [QC#13004,MOD]
            // setValue(updateMsg.cltWrkItemStsCd,
            // CLT_WRK_ITEM_STS.CLOSE);
            setValue(updateMsg.cltWrkItemStsCd, CLT_WRK_ITEM_STS.COMPLETED);
            // END 2016/09/26 K.Kojima [QC#13004,MOD]
            //---- end 2016/01/11
            // START 2017/01/05 E.Kameishi [QC#16817,ADD]
            setValue(updateMsg.cltWrkItemWerdDt, this.batProcDt);
            // END 2017/01/05 E.Kameishi [QC#16817,ADD]

            updateList.add(updateMsg);
            if (updateList.size() >= UPDATE_CNT) {
                updateCltStrtgyWrkItemTrx(updateList);
                updateList.clear();
            }

        }
        if (updateList.size() > 0) {
            updateCltStrtgyWrkItemTrx(updateList);
        }
    }

    /**
     * updateCltStrtgyWrkItemTrx
     * @param updateList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    private void updateCltStrtgyWrkItemTrx(List<CLT_STRGY_WRK_ITEM_TRXTMsg> updateList) {
        int cnt = S21FastTBLAccessor.update(updateList.toArray(new CLT_STRGY_WRK_ITEM_TRXTMsg[0]));
        if (cnt != updateList.size()) {
            String[] args = {"CLT_STRGY_WRK_ITEM_TRX" };
            throw new S21AbendException(NFDM0004E, args);
        }
        commit();
    }

    /**
     * createMailData
     * @param rs ResultSet
     * @return MailData
     * @throws SQLException
     */
    private MailData createMailData(ResultSet rs) throws SQLException {
        MailData data = new MailData();
        data.setBillToCustCd(rs.getString("BILL_TO_CUST_CD"));
        data.setDsAcctNum(rs.getString("CLT_ACCT_CD"));
        data.setLocNum(rs.getString("LOC_NUM"));
        //---- start mod 2016/01/11
        // data.setCltStrgyNm(rs.getString("CLT_STRGY_NM"));
        data.setCltStrgyNm(rs.getString("CLT_CUST_TP_NM"));  // QC#5525. This needs to be retrieved from DS_ACCT_CUST if it cannot be obtained from bill to.
        //---- end 2016/01/11
        data.setFirstEmlAddr(rs.getString("EML_ADDR_BILL"));
        data.setScdEmlAddr(rs.getString("EML_ADDR_ACCT"));
        return data;
    }

    /**
     * notifyCollector
     * @param mailDataMap Map<String, MailData>
     */
    private void notifyCollector(Map<String, MailData> mailDataMap) {
        for (String bllgToCustCd : mailDataMap.keySet()) {
            notifyCollector(mailDataMap.get(bllgToCustCd));
        }
    }

    /**
     * notifyCollector
     * @param mailData MailData
     */
    private void notifyCollector(MailData mailData) {
        List<String> creditMemoList = new ArrayList<String>();
        List<String> paymentList = new ArrayList<String>();
        List<String> invoiceList = new ArrayList<String>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getArTrxBalList(mailData.getBillToCustCd());
            rs = ps.executeQuery();
            while (rs.next()) {
                String arTrxTpCd = rs.getString("AR_TRX_TP_CD");
                if (AR_TRX_TP.CREDIT_MEMO.equals(arTrxTpCd)) {
                    if (creditMemoList.size() < this.maxRowCnt) {
                        creditMemoList.add(createCreditMemoRow(rs));
                    } else if (creditMemoList.size() == this.maxRowCnt) {
                        creditMemoList.add(MORE_DATA_EXISTS_MSG);
                    }
                } else if (AR_TRX_TP.RECEIPT.equals(arTrxTpCd) || AR_TRX_TP.ON_ACCOUNT.equals(arTrxTpCd)) {
                    if (paymentList.size() < this.maxRowCnt) {
                        paymentList.add(createPaymentRow(rs));
                    } else if (paymentList.size() == this.maxRowCnt) {
                        paymentList.add(MORE_DATA_EXISTS_MSG);
                    }
                } else if (AR_TRX_TP.INVOICE.equals(arTrxTpCd) || AR_TRX_TP.DEBIT_MEMO.equals(arTrxTpCd) || AR_TRX_TP.DEDUCTION.equals(arTrxTpCd)) {
                    if (invoiceList.size() < this.maxRowCnt) {
                        invoiceList.add(createInvoiceRow(rs));
                    } else if (invoiceList.size() == this.maxRowCnt) {
                        invoiceList.add(MORE_DATA_EXISTS_MSG);
                    }
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        sendMail(mailData, creditMemoList, paymentList, invoiceList);
    }

    /**
     *<pre>
     * Send Mail
     * @param mailData MailData
     * @param creditMemoList List<String>
     * @param paymentList List<String>
     * @param invoiceList List<String>
     *</pre>
     */
    public void sendMail(MailData mailData, List<String> creditMemoList, List<String> paymentList, List<String> invoiceList) {

        if (creditMemoList.size() == 0 && paymentList.size() == 0 && invoiceList.size() == 0) {
            return;
        }

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1("NFD");
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To Address
        //---- start mod 2016/01/11
        //S21MailAddress addrTo = getAddrTo(mailData);
        List<S21MailAddress> addrToList = getAddrTo(mailData);
        //---- end 2016/01/11

        // 4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TMPL_ID);
        if (template == null) {
            throw new S21AbendException(NFBM0184E, new String[] {MAIL_TMPL_ID });
        }

        template.setTemplateParameter(MAIL_TMPL_KEY_ACCT_NUM, nullToEmpty(mailData.getDsAcctNum()));
        // START 2021/07/01 G.Delgado [QC#58909,MOD]
        // template.setTemplateParameter(MAIL_TMPL_KEY_LOC_NUM, nullToEmpty(mailData.getLocNum()));
        template.setTemplateParameter(MAIL_TMPL_KEY_BILL_TO_CUST_CD, nullToEmpty(mailData.getBillToCustCd()));
        // END 2021/07/01 G.Delgado [QC#58909,MOD]
        template.setTemplateParameter(MAIL_TMPL_KEY_CUST_TP, nullToEmpty(mailData.getCltStrgyNm()));
        template.setTemplateParameter(MAIL_TMPL_CREDIT_MEMO_LIST, toStringForList(creditMemoList));
        template.setTemplateParameter(MAIL_TMPL_CREDIT_PYMT_LIST, toStringForList(paymentList));
        template.setTemplateParameter(MAIL_TMPL_CREDIT_INV_LIST, toStringForList(invoiceList));

        // 5. Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        //---- start mod 2016/01/11
        //mail.setToAddress(addrTo);
        mail.setToAddressList(addrToList);
        //---- end 2016/01/11
        mail.setMailTemplate(template);
        
        mail.postMail();

        this.normCnt++;
    }

    private String toStringForList(List<String> list) {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(lineSeparator);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private List<S21MailAddress> getAddrTo(MailData mailData) {
        // mod 2016/01/11 default email address
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress> ();
        
        String toStr = null;
        if (hasValue(mailData.getFirstEmlAddr())) {
            toStr = mailData.getFirstEmlAddr();
            toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
        } else if (hasValue(mailData.getScdEmlAddr())) {
            toStr = mailData.getScdEmlAddr();
            toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
        } else {
            for (String add : defEmlAddList) {
                toStr = add;
                toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
            }
        }
        
        return toAddrList;
    }

    private String createCreditMemoRow(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(nullToEmpty(rs.getString("AR_TRX_NUM")));
        sb.append(DELIM);
        sb.append(nullToEmpty(rs.getString("DEAL_ORIG_GRS_AMT")));
        sb.append(DELIM);
        sb.append(nullToEmpty(rs.getString("DEAL_TAX_AMT")));
        sb.append(DELIM);
        sb.append(nullToEmpty(rs.getString("DEAL_APPLY_GRS_AMT")));
        sb.append(DELIM);
        sb.append(nullToEmpty(rs.getString("DEAL_RMNG_BAL_GRS_AMT")));
        return sb.toString();
    }

    private String createPaymentRow(ResultSet rs) throws SQLException {

        BigDecimal dealOrigGrsAmt = rs.getBigDecimal("DEAL_ORIG_GRS_AMT");
        BigDecimal dealApplyGrsAmt = rs.getBigDecimal("DEAL_APPLY_GRS_AMT");
        BigDecimal dealRmngBalGrsAmt = rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT");
        if (AR_TRX_TP.RECEIPT.equals(rs.getString("AR_TRX_TP_CD"))) {
            if (hasValue(dealOrigGrsAmt)) {
                dealOrigGrsAmt = dealOrigGrsAmt.negate();
            }
            if (hasValue(dealApplyGrsAmt)) {
                dealApplyGrsAmt = dealApplyGrsAmt.negate();
            }
            if (hasValue(dealRmngBalGrsAmt)) {
                dealRmngBalGrsAmt = dealRmngBalGrsAmt.negate();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(nullToEmpty(rs.getString("AR_TRX_NUM")));
        sb.append(DELIM);

        sb.append(nullToEmpty(dealOrigGrsAmt));
        sb.append(DELIM);

        sb.append(nullToEmpty(dealApplyGrsAmt));
        sb.append(DELIM);

        sb.append(nullToEmpty(dealRmngBalGrsAmt));

        return sb.toString();
    }

    private String createInvoiceRow(ResultSet rs) throws SQLException {
        return createCreditMemoRow(rs);
    }

    /**
     * getWorkItemTran
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getWorkItemTran() throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        queryParam.put("batProcDt", batProcDt);
        queryParam.put("cltWrkItemCd", NOTIFY_COLLECTOR_ITEM_CD);
        queryParam.put("cltWrkItemStsCd", CLT_WRK_ITEM_STS.PENDING);
        // START 2017/06/26 E.Kameishi [QC#18754, ADD]
        queryParam.put("wrkItmStsOpen", CLT_WRK_ITEM_STS.OPEN);
        // END 2017/06/26 E.Kameishi [QC#18754, ADD]
        //------ start add 2016/01/11 
        queryParam.put("flgY", FLG_ON_Y);
        //------ end 2016/01/11
        // START 2017/08/03 J.Kim [QC#18754, ADD]
        queryParam.put("wrkTpCd", CLT_WRK_TP.MANUAL);
        // END 2017/08/03 J.Kim [QC#18754, ADD]

        return this.ssmLLClient.createPreparedStatement("getWorkItemTran", queryParam, getExecPrm());
    }

    /**
     * getArTrxBalList
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getArTrxBalList(String billToCustCd) throws SQLException {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("billToCustCd", billToCustCd);
        List<String> arCashApplyStsCdList = new ArrayList<String>();
        arCashApplyStsCdList.add(AR_CASH_APPLY_STS.UNAPPLIED);
        arCashApplyStsCdList.add(AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arCashApplyStsCdList", arCashApplyStsCdList);

        return this.ssmLLClient.createPreparedStatement("getArTrxBalList", queryParam, getExecPrm());
    }

    /**
     * getCltStrtgyWrkItemTrxForUpdate
     * @param cltStrgyWorkItemTrxPk BigDecimal
     * @return CLT_STRGY_WRK_ITEM_TRXTMsg
     */
    private CLT_STRGY_WRK_ITEM_TRXTMsg getCltStrtgyWrkItemTrxForUpdate(BigDecimal cltStrgyWorkItemTrxPk) {
        CLT_STRGY_WRK_ITEM_TRXTMsg inMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.cltStrgyWrkItemTrxPk, cltStrgyWorkItemTrxPk);

        return (CLT_STRGY_WRK_ITEM_TRXTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    private static String nullToEmpty(BigDecimal value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
