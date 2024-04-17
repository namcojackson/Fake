package com.canon.cusa.s21.batch.NWA.NWAB244001;

import java.math.BigDecimal;


/** <pre>
 * Scheduling Agreement Create Sales Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   Fujitsu         Y.Taoka          Create          N/A
 * </pre>
 */
public class SendMailInfoBean {
    /** schdAgmtNum */
    private String schdAgmtNum;
    /** schdAgmtLineNum */
    private BigDecimal schdAgmtLineNum;
    /** adminPsnCd */
    private String adminPsnCd;
    /** xxMsgId */
    private String xxMsgId;
    /** xxMsgPrmArray */
    private String[] xxMsgPrmArray;

    /**
     * getSchdAgmtNum
     * @return String 
     */
    public String getSchdAgmtNum() {
        return schdAgmtNum;
    }

    /**
     * setSchdAgmtNum
     * @param schdAgmtNum String
     */
    public void setSchdAgmtNum(String schdAgmtNum) {
        this.schdAgmtNum = schdAgmtNum;
    }

    /**
     * getSchdAgmtLineNum
     * @return BigDecimal
     */
    public BigDecimal getSchdAgmtLineNum() {
        return schdAgmtLineNum;
    }

    /**
     * setSchdAgmtLineNum
     * @param schdAgmtLineNum BigDecimal
     */
    public void setSchdAgmtLineNum(BigDecimal schdAgmtLineNum) {
        this.schdAgmtLineNum = schdAgmtLineNum;
    }

    /**
     * getAdminPsnCd
     * @return String
     */
    public String getAdminPsnCd() {
        return adminPsnCd;
    }

    /**
     * setAdminPsnCd
     * @param adminPsnCd String
     */
    public void setAdminPsnCd(String adminPsnCd) {
        this.adminPsnCd = adminPsnCd;
    }

    /**
     * getXxMsgId
     * @return String
     */
    public String getXxMsgId() {
        return xxMsgId;
    }

    /**
     * setXxMsgId
     * @param xxMsgId String
     */
    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }

    /**
     * getXxMsgPrmArray
     * @return String[]
     */
    public String[] getXxMsgPrmArray() {
        return xxMsgPrmArray;
    }

    /**
     * setXxMsgPrmArra
     * @param xxMsgPrmArray String[]
     */
    public void setXxMsgPrmArra(String[] xxMsgPrmArray) {
        this.xxMsgPrmArray = xxMsgPrmArray;
    }
}
