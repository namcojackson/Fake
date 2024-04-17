/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC184001.constant;

/**
 * <pre>
 * IS Web Store Pricing API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   Fujitsu         Kamide          Create
 * 02/26/2018   Fujitsu         Hd.Sugawara     Update          QC#22967
 * </pre>
 */
public final class NWZC184001Constant {

    /**
     * MODE 01 : Only Net Sell Price
     */
    public static final String MODE_ONLY_NET_PRC = "1";

    /**
     * MODE 02 : Net Sell+Freight+Tax
     */
    public static final String MODE_NET_PRC_FRT_TAX = "2";

    /**
     * Max Message Length
     */
    public static final int MAX_MSG_LEN = 300;

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
     * The entered Merchandise Code does not exist in Master.
     */
    public static final String NWZM0036E = "NWZM0036E";

    /**
     * "Order Quantity" is required.
     */
    public static final String NWZM0046E = "NWZM0046E";

    /**
     * Shipping Service Level Code is required.
     */
    public static final String NWZM0049E = "NWZM0049E";

    /**
     * "Global Company Code" is required.
     */
    public static final String NWZM0188E = "NWZM0188E";

    /**
     * "Sales Rep" could not be obtained.
     */
    public static final String NWZM0642E = "NWZM0642E";

    /**
     * Freight Condition Code is required.
     */
    public static final String NWZM1266E = "NWZM1266E";

    /**
     * The entered "Freight Condition Code" does not exist in the
     * Master.
     */
    public static final String NWZM1426E = "NWZM1426E";

    /**
     * The entered "Special Handling Type Code" does not exist in the
     * Master.
     */
    public static final String NWZM1428E = "NWZM1428E";

    /**
     * The entered "Ship to State Code" does not exist in the Master.
     */
    public static final String NWZM1446E = "NWZM1446E";

    /**
     * The entered "Ship to Country Code" does not exist in the
     * Master.
     */
    public static final String NWZM1448E = "NWZM1448E";

    // Del Start 2018/02/26 QC#22967
    ///**
    // * The relationship between 'Bill to', 'Ship to' and 'Sold to' is
    // * incorrect.
    // */
    //public static final String NWZM1455E = "NWZM1455E";
    // Del End 2018/02/26 QC#22967

    /**
     * The corresponding data does not exist in "DS_ORD_TP_PROC_DFN".
     */
    public static final String NWZM1514E = "NWZM1514E";

    /**
     * "Legacy Order Type Code" is required.
     */
    public static final String NWZM1900E = "NWZM1900E";

    /**
     * "Legacy Order Reason Code" is required.
     */
    public static final String NWZM1901E = "NWZM1901E";

    /**
     * "Bill To Location Number" is required.
     */
    public static final String NWZM1902E = "NWZM1902E";

    /**
     * "Sold To Location Number" is required.
     */
    public static final String NWZM1903E = "NWZM1903E";

    /**
     * "Ship To Location Number" is required.
     */
    public static final String NWZM1904E = "NWZM1904E";

    /**
     * "Order Source Reference Number" is required.
     */
    public static final String NWZM1905E = "NWZM1905E";

    /**
     * The corresponding data does not exist in "XTRNL_INTFC_XREF".
     */
    public static final String NWZM1906E = "NWZM1906E";

    /**
     * The entered "Sold To Location Number" does not exist in the
     * Master.
     */
    public static final String NWZM1907E = "NWZM1907E";

    /**
     * The entered "Bill To Location Number" does not exist in the
     * Master.
     */
    public static final String NWZM1908E = "NWZM1908E";

    /**
     * 'The entered "Ship To Location Number" does not exist in the
     * Master.
     */
    public static final String NWZM1909E = "NWZM1909E";

    /**
     * The corresponding data does not exist in
     * "DS_ORD_LINE_PROC_DFN".
     */
    public static final String NWZM1910E = "NWZM1910E";

    /**
     * The relationship between 'Carrier Code' and 'Shipping Service
     * Level Code' is incorrect.
     */
    public static final String NWZM1911E = "NWZM1911E";

    /**
     * The relationship between 'Freight Condition Code', 'LOB' and
     * 'Shipping Service Level Code' is incorrect.
     */
    public static final String NWZM1912E = "NWZM1912E";

    /**
     * There are any message in xxMsgIdDetailList param.
     */
    public static final String NWZM1927E = "NWZM1927E";

    // Add Start 2018/02/26 QC#22967
    /** The relationship between 'Ship To' and 'Sold To' is incorrect. */
    public static final String NWZM2254E = "NWZM2254E";

    /** The relationship between 'Sold To' and 'Bill To' is incorrect. */
    public static final String NWZM2255E = "NWZM2255E";
    // Add End 2018/02/26 QC#22967
}
