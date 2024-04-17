package com.canon.cusa.s21.batch.NFC.NFCB053001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.AR_RCPT_RCV_INFO_WRKTMsg;
import business.db.DS_BANK_ACCTTMsg;
import business.db.DS_BANK_BRTMsg;
import business.db.DS_CUST_BANK_ACCT_RELNTMsg;

/**
 * <pre>
 * Result Data Bean 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/03/2015   Fujitsu         H.Nakajima      Create          N/A
 * 2018/05/08   Fujitsu         H.ikeda         Update          QC#25703
 *</pre>
 */
public class ResultDataBean {

    /** AR_RCPT_RCV_INTFC_PK of error Data */
    private BigDecimal errorIntfcPk = null;

    /** VLD_CUST_RCPT_NUM */
    private String vldCustRcptNum = null;

    /** Account Information */
    private AccountInfoBean accountInfo = null;

    /**
     * Key : AR_RCPT_RCV_INTFC_PK Value :
     * List<AR_RCPT_RCV_INFO_WRKTMsg>
     */
    private Map<BigDecimal, List<AR_RCPT_RCV_INFO_WRKTMsg>> arRcptRcvInfoWrkList = new HashMap<BigDecimal, List<AR_RCPT_RCV_INFO_WRKTMsg>>();

    /** DS_BANK_BRTmsg */
    private DS_BANK_BRTMsg dsBankBr = null;

    /** DS_BANK_ACCTTmsg */
    private DS_BANK_ACCTTMsg dsBankAcct = null;

    /** DS_CUST_BANK_ACCT_RELNTMsg */
    private DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctReln = null;

    /** Data Type */
    private String dataType = null;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * return VLD_CUST_RCPT_NUM.
     * @return VLD_CUST_RCPT_NUM
     */
    public String getVldCustRcptNum() {
        return vldCustRcptNum;
    }

    /**
     * set VLD_CUST_RCPT_NUM.
     * @param vldCustRcptNum VLD_CUST_RCPT_NUM
     */
    public void setVldCustRcptNum(String vldCustRcptNum) {
        this.vldCustRcptNum = vldCustRcptNum;
    }

    /**
     * return List<AR_RCPT_RCV_INFO_WRKTMsg>.
     * @param arRcptRcvIntfcPk AR_RCPT_RCV_INTFC_PK
     * @return List<AR_RCPT_RCV_INFO_WRKTMsg>
     */
    public List<AR_RCPT_RCV_INFO_WRKTMsg> getArRcptRcvInfoWrkList(BigDecimal arRcptRcvIntfcPk) {
        return this.arRcptRcvInfoWrkList.get(arRcptRcvIntfcPk);
    }

    /**
     * return List<AR_RCPT_RCV_INFO_WRKTMsg> for all
     * AR_RCPT_RCV_INTFC_PK.
     * @return List<AR_RCPT_RCV_INFO_WRKTMsg>
     */
    public List<AR_RCPT_RCV_INFO_WRKTMsg> getArRcptRcvInfoWrkList() {
        List<AR_RCPT_RCV_INFO_WRKTMsg> allList = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();

        for (List<AR_RCPT_RCV_INFO_WRKTMsg> list : this.arRcptRcvInfoWrkList.values()) {
            allList.addAll(list);
        }

        return allList;
    }

    /**
     * Add AR_RCPT_RCV_INFO_WRKTMsg.
     * @param arRcptRcvInfoWrk AR_RCPT_RCV_INTFC_PK
     */
    public void addArRcptRcvInfoWrk(AR_RCPT_RCV_INFO_WRKTMsg arRcptRcvInfoWrk) {
        BigDecimal arRcptRcvIntfcPk = arRcptRcvInfoWrk.arRcptRcvIntfcPk.getValue();

        List<AR_RCPT_RCV_INFO_WRKTMsg> list = this.arRcptRcvInfoWrkList.get(arRcptRcvIntfcPk);

        if (list == null) {
            list = new ArrayList<AR_RCPT_RCV_INFO_WRKTMsg>();

            this.arRcptRcvInfoWrkList.put(arRcptRcvIntfcPk, list);
        }

        list.add(arRcptRcvInfoWrk);
    }

    /**
     * 
     * @return
     */
    public DS_BANK_BRTMsg getDsBankBr() {
        return dsBankBr;
    }

    /**
     * 
     * @param dsBankBr
     */
    public void setDsBankBr(DS_BANK_BRTMsg dsBankBr) {
        this.dsBankBr = dsBankBr;
    }


    /**
     * return DS_BANK_ACCTTMsg.
     * @return DS_BANK_ACCTTMsg
     */
    public DS_BANK_ACCTTMsg getDsBankAcct() {
        return dsBankAcct;
    }

    /**
     * set DS_BANK_ACCTTMsg.
     * @param dsBankAcct DS_BANK_ACCTTMsg
     */
    public void setDsBankAcct(DS_BANK_ACCTTMsg dsBankAcct) {
        this.dsBankAcct = dsBankAcct;
    }

    /**
     * return DS_CUST_BANK_ACCT_RELNTMsg.
     * @return DS_CUST_BANK_ACCT_RELNTMsg
     */
    public DS_CUST_BANK_ACCT_RELNTMsg getDsCustBankAcctReln() {
        return dsCustBankAcctReln;
    }

    /**
     * set DS_CUST_BANK_ACCT_RELNTMsg.
     * @param dsCustBankAcctReln DS_CUST_BANK_ACCT_RELNTMsg
     */
    public void setDsCustBankAcctReln(DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctReln) {
        this.dsCustBankAcctReln = dsCustBankAcctReln;
    }

    /**
     * Whether are AR_RCPT_RCV_INFO_WRKTMsg created for
     * AR_RCPT_RCV_INTFC_PK?
     * @param arRcptRcvIntfcPk AR_RCPT_RCV_INTFC_PK
     * @return boolean
     */
    public boolean isWorkCreated(BigDecimal arRcptRcvIntfcPk) {
        List<AR_RCPT_RCV_INFO_WRKTMsg> list = this.arRcptRcvInfoWrkList.get(arRcptRcvIntfcPk);

        if (list != null && list.size() > 0) {
            return true;
        }

        return false;
    }

    /**
     * return Account Information.
     * @return Account Information
     */
    public AccountInfoBean getAccountInfo() {
        return accountInfo;
    }

    /**
     * set Account Information.
     * @param accountInfo Account Information
     */
    public void setAccountInfo(AccountInfoBean accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * return AR_RCPT_RCV_INTFC_PK of error Data.
     * @return AR_RCPT_RCV_INTFC_PK of error Data
     */
    public BigDecimal getErrorIntfcPk() {
        return errorIntfcPk;
    }

    /**
     * set AR_RCPT_RCV_INTFC_PK of error Data.
     * @param errorIntfcPk AR_RCPT_RCV_INTFC_PK of error Data
     */
    public void setErrorIntfcPk(BigDecimal errorIntfcPk) {
        this.errorIntfcPk = errorIntfcPk;
    }

}
