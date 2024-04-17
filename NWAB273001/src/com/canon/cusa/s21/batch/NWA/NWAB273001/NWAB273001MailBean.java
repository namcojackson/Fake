/**
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB273001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import static com.canon.cusa.s21.batch.NWA.NWAB273001.constant.NWAB273001Constant.*;


/**
 * <pre>
 * eCheck Capture Batch.
 * This class defines the available used in the batch application
 * program of BusinessID NWAB273001. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/06/2023   Hitachi         M.Hashino       Create          QC#55645
 * 
 *</pre>
 */

public class NWAB273001MailBean {


    /** Sales Date */
    private String slsDt = null;

    /** Sell To Customer Code */
    private String sellToCustCd = null;

    /** Sell To Location Name */
    private String sellToLocNm = null;

    /** Error Message Text */
    private String errMsgTxt = null;

    /** Bank Routing Number */
    private String bankRteNum = null;

    /** Masked Bank Account Number */
    private String maskedBankAcctNum = null;

    /** Collection Position Code */
    private String cltPsnCd = "";

    /** Collection Position Name */
    private String cltPsnNm = "";

    /** email contents list */
    private List<NWAB273001MailContentsLineBean> contentsList;

    /** eCheck Payment Amount */
    private BigDecimal echkPmtAmt = null;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWAB273001MailBean() {
    }

    /**
     * Set Sell To Customer Code
     * @param sellToCustCd String
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * Get Sell To Customer Code
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param locNm String
     */
    public void setSellToLocNm(String locNm) {
        this.sellToLocNm = locNm;
    }

    /**
     * @return sellToLocNm
     */
    public String getSellToLocNm() {
        return sellToLocNm;
    }

    /**
     * @param errMsg String
     */
    public void setErrMsgTxt(String errMsg) {
        this.errMsgTxt = errMsg;
    }

    /**
     * @return errMsgTxt
     */
    public String getErrMsgTxt() {
        return errMsgTxt;
    }

    /**
     * @param bankRteNum String
     */
    public void setBankRteNum(String bankRteNum) {
        this.bankRteNum = bankRteNum;
    }

    /**
     * @return bankRteNum
     */
    public String getBankRteNum() {
        return bankRteNum;
    }

    /**
     * @param maskedValue String
     */
    public void setBankAcctNum(String maskedValue) {
        this.maskedBankAcctNum = maskedValue;
    }

    /**
     * @return maskedBankAcctNum
     */
    public String getBankAcctNum() {
        return maskedBankAcctNum;
    }

    /**
     * @param cltPsnCd String
     */
    public void setCltPsnCd(String cltPsnCd) {
        this.cltPsnCd = cltPsnCd;
    }

    /**
     * @return cltPsnCd
     */
    public String getCltPsnCd() {
        return cltPsnCd;
    }
    /**
     * @param cltPsnNm String
     */
    public void setCltPsnNm(String cltPsnNm) {
        this.cltPsnNm = cltPsnNm;
    }

    /**
     * @return cltPsnNm
     */
    public String getCltPsnNm() {
        return cltPsnNm;
    }

    /**
     * @param slsDt String
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @return slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @param echkPmtAmt BigDecimal
     */
    public void setEchkPmtAmt(BigDecimal echkPmtAmt) {
        this.echkPmtAmt = echkPmtAmt;
    }

    /**
     * @return echkPmtAmt
     */
    public BigDecimal getEchkPmtAmt() {
        return echkPmtAmt;
    }

    /**
     * @return the contentsList
     */
    public List<NWAB273001MailContentsLineBean> getContentsList() {
        return contentsList;
    }

    /**
     * @param contentsList the contentsList to set
     */
    public void setContentsList(List<NWAB273001MailContentsLineBean> contentsList) {
        this.contentsList = contentsList;
    }

    /**
     * @param lineBean lineBean add the contentsList
     */
    public void setContentsLine(NWAB273001MailContentsLineBean lineBean) {
        if (contentsList == null) {
            contentsList = new ArrayList<NWAB273001MailContentsLineBean>();
        }
        contentsList.add(lineBean);
    }

    /**
     * * Get Mail Text
     * @return mailTxt Mail Text
     */
    public String getMailTxt() {
        StringBuilder mailTxt = new StringBuilder();
        mailTxt.append("Sell To Customer Code :      {" + sellToCustCd + ONE_SPACE + sellToLocNm + "}").append(CRLF);
        mailTxt.append("ACH Payment request failed : {" + errMsgTxt + "}").append(CRLF);
        mailTxt.append("Routing Number :             {" + bankRteNum + "}").append(CRLF);
        mailTxt.append("Bank Account Number :        {" + maskedBankAcctNum + "}").append(CRLF);
        mailTxt.append("Collector :                  {" + cltPsnCd + ONE_SPACE + cltPsnNm + "}").append(CRLF);
        mailTxt.append(CRLF);
        mailTxt.append("Payment details : ").append(CRLF);
        mailTxt.append(CONTENT_HDR_LBL).append(CRLF);
        mailTxt.append(CONTENT_HDR_DIV_LINE).append(CRLF);
        for (NWAB273001MailContentsLineBean line : contentsList) {
            mailTxt.append(ZYPCommonFunc.rightPad(line.getInvNum(), INV_NUM_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.rightPad(formateDate(line.getInvDt()), INV_DT_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.leftPad(formatBigDecimal(line.getInvTotDealNetAmt()), INV_TOT_DEAL_NET_AMT_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(CRLF);
        }
        mailTxt.append(CONTENT_HDR_DIV_LINE).append(CRLF);
        mailTxt.append("Total Payment Amount : " + echkPmtAmt).append(CRLF);
        mailTxt.append(CRLF);

        return mailTxt.toString();
    }

    private String formatBigDecimal(BigDecimal source) {
        if (source == null) {
            return "";
        } else {
            return source.toPlainString();
        }
    }

    private String formateDate(String source) {
        if (source == null) {
            return "";
        } else {
            return ZYPDateUtil.convertFormat(source, S21CalendarUtil.TYPE_YYYYMMDD, S21CalendarUtil.TYPE_MMDDYYYY, S21CalendarUtil.SEPARATOR_SLASH);
        }
    }
}
