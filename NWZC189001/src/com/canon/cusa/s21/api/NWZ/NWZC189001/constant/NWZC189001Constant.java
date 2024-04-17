/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC189001.constant;

/**
 * <pre>
 * My CSA Supply Order Pricing API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/31   Fujitsu         K.Ishizuka      Create          QC#18426(Sol#123)
 * 2018/02/19   Fujitsu         K.Ishizuka      Create          QC#23811
 * </pre>
 */
public final class NWZC189001Constant {

    /**
     * MODE 1 : Only Net Sell Price
     */
    public static final String MODE_ONLY_NET_PRC = "1";

    /**
     * MODE 2 : Net Sell+Freight+Tax
     */
    public static final String MODE_NET_PRC_FRT_TAX = "2";

    /**
     * Max Message Length
     */
    public static final int MAX_MSG_LEN = 100;

    /**
     * Mode is required.
     */
    public static final String NWZM0012E = "NWZM0012E";

    /**
     * Specified value for Mode is invalid.
     */
    public static final String NWZM0013E = "NWZM0013E";

    /**
     * Merchandise Code is required.
     */
    public static final String NWZM0021E = "NWZM0021E";

    /**
     * "Global Company Code" is required.
     */
    public static final String NWZM0188E = "NWZM0188E";

    /**
     * DS Contract PK is required.
     */
    public static final String NWZM2239E = "NWZM2239E";

    /**
     * Serial # is required.
     */
    public static final String NWZM2240E = "NWZM2240E";

    /**
     * Merchandise Code for Machine is required.
     */
    public static final String NWZM2241E = "NWZM2241E";

    /**
     * Ordered Customer UOM Quantity is required.
     */
    public static final String NWZM2242E = "NWZM2242E";

    /**
     * Customer UOM Code is required.
     */
    public static final String NWZM2243E = "NWZM2243E";

    /**
     * The entered "Freight Condition Code" does not exist in the Master.
     */
    public static final String NWZM1426E = "NWZM1426E";

    /**
     * The entered "Special Handling Type Code" does not exist in the Master.
     */
    public static final String NWZM1428E = "NWZM1428E";

    /**
     * The entered "Ship to State Code" does not exist in the Master.
     */
    public static final String NWZM1446E = "NWZM1446E";

    /**
     * The entered "Ship to Country Code" does not exist in the Master.
     */
    public static final String NWZM1448E = "NWZM1448E";

    /**
     * The corresponding data does not exist in "DS_ORD_TP_PROC_DFN".
     */
    public static final String NWZM1514E = "NWZM1514E";

    /**
     * 'The entered "Ship To Location Number" does not exist in the Master.
     */
    public static final String NWZM1909E = "NWZM1909E";

    /**
     * There are any message in xxMsgIdDetailList parameter.
     */
    public static final String NWZM1927E = "NWZM1927E";

    /**
     * The relationship between 'Serial Number' and 'Service Machine Master' is incorrect.
     */
    public static final String NWZM1472E = "NWZM1472E";

    /**
     * Parameter "Contract PK" does not exists in the DS_CONTR.
     */
    public static final String NWZM1679E = "NWZM1679E";
    
    /**
     * Customer information linked to the "Ship To Customer Code" does not exist.  
     */
    public static final String NWZM0625E = "NWZM0625E";
    
    /**
     * Var Char Const:TERM_COND_ATTRB_NM_STAPLE_INCL
     */ // 2018/02/19 S21_NA#23811 Add
    public static final String VAR_CHAR_STAPLE_INCL = "TERM_COND_ATTRB_NM_STAPLE_INCL";
}
