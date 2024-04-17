/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

/**
 * <pre>
 * Get Coverage Information
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/17/2015   Hitachi         T.Tsuchida      Create          NA#Service Pricing API
 * </pre>
 */
public class CovInfoBean {

    /** Service Bill Type Code */
    private String svcBillTpCd;

    /** Labor Charge Flag */
    private String lborChrgFlg;

    /** Parts Charge Flag */
    private String prtChrgFlg;

    /** Expense Charge Flag */
    private String expChrgFlg;

    /** Drum Charge Flag */
    private String drumChrgFlg;

//    public void clear() {
//        this.svcBillTpCd = null;
//        this.lborChrgFlg = null;
//        this.prtChrgFlg = null;
//        this.expChrgFlg = null;
//        this.drumChrgFlg = null;
//    }

    /** 
     * Get Service Bill Type Code
     * @return String
     */
    public String getSvcBillTpCd() {
        return svcBillTpCd;
    }

    /** 
     * Set Service Bill Type Code
     * @param svcBillTpCd String
     */
    public void setSvcBillTpCd(String svcBillTpCd) {
        this.svcBillTpCd = svcBillTpCd;
    }

    /** 
     * Get Labor Charge Flag
     * @return String
     */
    public String getLborChrgFlg() {
        return lborChrgFlg;
    }

    /** 
     * Set Labor Charge Flag
     * @param lborChrgFlg String
     */
    public void setLborChrgFlg(String lborChrgFlg) {
        this.lborChrgFlg = lborChrgFlg;
    }

    /** 
     * Get Parts Charge Flag
     * @return String
     */
    public String getPrtChrgFlg() {
        return prtChrgFlg;
    }

    /** 
     * Set Parts Charge Flag
     * @param prtChrgFlg String
     */
    public void setPrtChrgFlg(String prtChrgFlg) {
        this.prtChrgFlg = prtChrgFlg;
    }

    /** 
     * Get Expense Charge Flag
     * @return String
     */
    public String getExpChrgFlg() {
        return expChrgFlg;
    }

    /** 
     * Set Expense Charge Flag
     * @param expChrgFlg String
     */
    public void setExpChrgFlg(String expChrgFlg) {
        this.expChrgFlg = expChrgFlg;
    }

    /** 
     * Get Drum Charge Flag
     * @return String
     */
    public String getDrumChrgFlg() {
        return drumChrgFlg;
    }

    /** 
     * Set Drum Charge Flag
     * @param drumChrgFlg String
     */
    public void setDrumChrgFlg(String drumChrgFlg) {
        this.drumChrgFlg = drumChrgFlg;
    }

}
