/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC183001;

/**
 * <pre>
 * NWZC1830 D&I / Site Survey / Contact API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/25   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */

public class OrderDtlInfoBean {
    /** ORD_NUM */
    private String ordNum = "";

    /** ORD_LINE_NUM */
    private String ordLineNum = "";

    /** ORD_LINE_SUB_NUM */
    private String ordLineSubNum = "";

    /**
     * getOrdNum
     * @return String
     */
    public String getOrdNum() {
        return ordNum;
    }

    /**
     * setOrdNum
     * @param ordNum String
     */
    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    /**
     * getOrdLineNum
     * @return String
     */
    public String getOrdLineNum() {
        return ordLineNum;
    }

    /**
     * setOrdLineNum
     * @param ordLineNum String
     */
    public void setOrdLineNum(String ordLineNum) {
        this.ordLineNum = ordLineNum;
    }

    /**
     * getOrdLineSubNum
     * @return String
     */
    public String getOrdLineSubNum() {
        return ordLineSubNum;
    }

    /**
     * setOrdLineSubNum
     * @param ordLineSubNum String
     */
    public void setOrdLineSubNum(String ordLineSubNum) {
        this.ordLineSubNum = ordLineSubNum;
    }

}
