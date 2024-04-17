package com.canon.cusa.s21.batch.NPA.NPAB136001;

import java.math.BigDecimal;

/**
 * <pre>
* Business ID : NPAB1360 Insourcing Batch Error Message Bean
* </pre>
 * 
 *<pre>
* Date         Company         Name            Create/Update   Defect No
* ----------------------------------------------------------------------
* 08/30/2016   CITS          T.Tokutomi        Create          N/A
*</pre>
 */
public class ErrorMessageBean {

    /** PRCH_REQ_NUM */
    private String prchReqNum;

    /** PRCH_REQ_LINE_NUM */
    private String prchReqLineNum;

    /** MDSE_CD */
    private String mdseCd;

    /** PRCH_REQ_QTY */
    private BigDecimal prchReqQty;

    /** ERR_MSG */
    private String errMsg;

    /**
     * @return prchReqNum
     */
    public String getPrchReqNum() {
        return prchReqNum;
    }

    /**
     * @param prchReqNum Set prchReqNum
     */
    public void setPrchReqNum(String prchReqNum) {
        this.prchReqNum = prchReqNum;
    }

    /**
     * @return prchReqLineNum
     */
    public String getPrchReqLineNum() {
        return prchReqLineNum;
    }

    /**
     * @param prchReqLineNum Set prchReqLineNum
     */
    public void setPrchReqLineNum(String prchReqLineNum) {
        this.prchReqLineNum = prchReqLineNum;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd Set mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return prchReqQty
     */
    public BigDecimal getPrchReqQty() {
        return prchReqQty;
    }

    /**
     * @param prchReqQty Set prchReqQty
     */
    public void setPrchReqQty(BigDecimal prchReqQty) {
        this.prchReqQty = prchReqQty;
    }

    /**
     * @return errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param errMsg Set errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
