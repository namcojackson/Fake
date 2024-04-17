/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB204001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import static com.canon.cusa.s21.batch.NWA.NWAB204001.constant.NWAB204001Constant.*;

/**
 * <pre>
 * Credit Card Capture Batch..
 * This class defines the available used in the batch application
 * program of BusinessID NWAB204001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   Fujitsu         S.Ohki          Create          N/A
 * 01/24/2022   Hitachi         R.Onozuka       Update          QC#56129
 * </pre>
 */
public class NWAB204001MailBean {

    /** Sales Date */
    private String slsDt = null;

    /** Sell To Customer Code */
    private String sellToCustCd = null;
    
    // Add Start 2022/01/24 QC#56129
    /** Sell To Location Name */
    private String sellToLocNm = null;
    
    /** Collection Position Code */
    private String cltPsnCd = null;
    
    /** Collection Position Name */
    private String cltPsnNm = null;
    // Add End   2022/01/24 QC#56129

    /** PreAuthorization Hold Count */
    private int preAuthHoldCnt = 0;

    /** Authorization Error Message List */
    private List<String> authErrRepMsgList;

    /** email contents list */
    private List<NWAB204001MailContentsLineBean> contentsList;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NWAB204001MailBean() {
    }

    /**
     * Set Sell To Customer Code Type
     * @param sellToCustCd Sell To Customer Code
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * Get Sell To Customer Code
     * @return sellToCustCd Sell To Customer Code
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    // Add Start 2022/01/24 QC#56129

    /**
     * @param sellToLocNm String
     */
    public void setSellToLocNm(String sellToLocNm) {
        this.sellToLocNm = sellToLocNm;
    }

    /**
     * @return sellToLocNm
     */
    public String getSellToLocNm() {
        return sellToLocNm;
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
    
    // Add End   2022/01/24 QC#56129

    /**
     * @return the slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @param slsDt the slsDt to set
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @return preAuthHoldCnt
     */
    public int getPreAuthHoldCnt() {
        return preAuthHoldCnt;
    }

    /**
     * @param preAuthHoldCnt preAuthorization Hold Count
     */
    public void setPreAuthHoldCnt(int preAuthHoldCnt) {
        this.preAuthHoldCnt = preAuthHoldCnt;
    }

    /**
     * @return Authorization Error Msg List
     */
    public List<String> getAuthErrRepMsgList() {
        if (authErrRepMsgList == null) {
            authErrRepMsgList = new ArrayList<String>();
        }
        return authErrRepMsgList;
    }

    /**
     * @param authErrRepMsgList List
     */
    public void setAuthErrRepMsgList(List<String> authErrRepMsgList) {
        this.authErrRepMsgList = authErrRepMsgList;
    }

    /**
     * @param msg String
     */
    public void setMsgToAuthErrRepMsgList(String msg) {
        if (authErrRepMsgList == null) {
            authErrRepMsgList = new ArrayList<String>();
        }
        authErrRepMsgList.add(msg);
    }

    /**
     * @return the contentsList
     */
    public List<NWAB204001MailContentsLineBean> getContentsList() {
        return contentsList;
    }

    /**
     * @param contentsList the contentsList to set
     */
    public void setContentsList(List<NWAB204001MailContentsLineBean> contentsList) {
        this.contentsList = contentsList;
    }

    /**
     * @param lineBean lineBean add the contentsList
     */
    public void setContentsLine(NWAB204001MailContentsLineBean lineBean) {
        if (contentsList == null) {
            contentsList = new ArrayList<NWAB204001MailContentsLineBean>();
        }
        contentsList.add(lineBean);
    }

    /**
     * * Get Mail Text
     * @return mailTxt Mail Text
     */
    public String getMailTxt() {
        StringBuilder mailTxt = new StringBuilder();
        // Mod Start 2022/1/24 QC#56129
        //mailTxt.append("Sell To Customer Code  :  " + sellToCustCd).append(CRLF);
        mailTxt.append("Sell To Customer Code  :  " + sellToCustCd + ONE_SPACE + sellToLocNm).append(CRLF);
        mailTxt.append("Collector  :  " + cltPsnCd + ONE_SPACE + cltPsnNm).append(CRLF);
        // Mod End   2022/1/24 QC#56129

        if (contentsList.size() != 0) {
            mailTxt.append(CONTENT_HDR_LBL).append(CRLF);
            mailTxt.append(CONTENT_HDR_DIV_LINE).append(CRLF);
        }

        for (NWAB204001MailContentsLineBean line : contentsList) {
            mailTxt.append(ZYPCommonFunc.rightPad(line.getInvNum(), INV_NUM_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.rightPad(formateDate(line.getInvDt()), INV_DT_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.rightPad(line.getCustRefNum(), CUST_REF_NUM_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.rightPad(line.getTrxRefNum(), TRX_REF_NUM_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.leftPad(formatBigDecimal(line.getInvTotDealNetAmt()), INV_TOT_DEAL_NET_AMT_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.leftPad(formatBigDecimal(line.getInvTotFuncTaxAmt()), INV_TOT_FUNC_TAX_AMT_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(ZYPCommonFunc.leftPad(formatBigDecimal(line.getInvTotFuncNetAmt()), INV_TOT_FUNC_NET_AMT_LEN, ONE_SPACE)).append(SEPT_SPACE);
            mailTxt.append(CRLF);
        }

        mailTxt.append(CONTENT_HDR_DIV_LINE);
        mailTxt.append(CRLF);

        if (this.preAuthHoldCnt > 0) {
            mailTxt.append("Please confirm your credit card information.");
            mailTxt.append(CRLF);
            mailTxt.append("[Authorization Error Report]");
            mailTxt.append(CRLF);
            mailTxt.append("Credit Card Authorization Error Count :  " + this.preAuthHoldCnt);
            mailTxt.append(CRLF);
            mailTxt.append("Order#");
            mailTxt.append(CRLF);
            mailTxt.append("----------");
            mailTxt.append(CRLF);
            for (String errorMsg : authErrRepMsgList) {
                mailTxt.append("  " + errorMsg);
                mailTxt.append(CRLF);
            }
            mailTxt.append("----------");
            mailTxt.append(CRLF);
        }
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
