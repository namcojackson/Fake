/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC185001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * IS Web Store Pricing API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   Fujitsu         Kamide          Create
 * 12/22/2016   Fujitsu         S.Ohki          Update          S21_NA#16617
 * 02/08/2018   Fujitsu         K.Ishizuka      Update          S21_NA#20297(Sol#379)
 * 02/26/2018   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 06/08/2018   Fujitsu         A.Kosai         Update          QC#26123
 * 09/20/2023   Hitachi         H.Watanabe      Update          QC#61782
 * </pre>
 */
public final class NWZC185001Constant {

    /**
     * BIZ_ID
     */
    public static final String BIZ_ID = "NWZC1850";

    // 2018/06/08 QC#26123 Add Start
    /**
     * PRNT_SUB_NUM
     */
    public static final String PRNT_SUB_NUM = "000";
    // 2018/06/08 QC#26123 Add End

    /**
     * SUB_NUM
     */
    public static final String SUB_NUM = "001";

    /**
     * POSN_NUM
     */
    public static final String POSN_NUM = "1";

    /**
     * Max Message Length
     */
    public static final int MAX_MSG_LEN = 300;

    // 2016/12/22 S21_NA#16617 Add Start
    /** Delivery Comment Text Max Size */
    public static final int DELY_ADDL_CMNT_TXT_MAX_SIZE = 240;
    // 2016/12/22 S21_NA#16617 Add End

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
     * "Drop Ship Flag" is required.
     */
    public static final String NWZM0347E = "NWZM0347E";

    /**
     * "Sales Rep" could not be obtained.
     */
    public static final String NWZM0642E = "NWZM0642E";


    /**
     * Contact Person does not exist in master.
     */
    public static final String NWZM1138E = "NWZM1138E";
    
    
    /**
     * Freight Condition Code is required.
     */
    public static final String NWZM1266E = "NWZM1266E";

    /**
     * The data does not exist in DS Order Type.
     */
    public static final String NWZM1286E = "NWZM1286E";

    /**
     * "Contact Person PK" is required.
     */
    public static final String NWZM1346E = "NWZM1346E";

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
    
    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    /** Line */ 
    public static final String LINE = "\r\n";
    
    /** Blank */
    public static final String BLANK = "";
    
    /** Order Category Context Type Code (EQUIPMENT_ORDER) */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /** Order Category Context Type Code (SUPPLIES_ORDER) */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";
    // 2018/02/08 S21_NA#20297(Sol#379) Add End

    // 2023/09/20 QC#61782 Add Start
    /** Email address from myCSA Shop is invalid. (myCSA Shop Order Source Ref#[@], CPO#[@]) */
    public static final String NWZM2323E = "NWZM2323E";

    /** Mail Template ID NWZC0010M001 */
    public static final String ML_TMPL_ID_01 = "NWZC0010M001";

    /** Mail Group ID NWZC0010M001 */
    public static final String ML_GRP_ID_01 = "NWZC0010M001";

    /** Direct Sales Contact Person Sort Num */
    public static final BigDecimal SORT_NUM_MYCSAUSER = new BigDecimal(2);

    /** Var Char Const MyCSAUser First Name */
    public static final String MYCSAUSER_FIRST_NM = "MYCSAUSER_FIRST_NM";

    /** Var Char Const MyCSAUser Last Name */
    public static final String MYCSAUSER_LAST_NM = "MYCSAUSER_LAST_NM";

    /** Var Char Const MyCSAUser Tell Num */
    public static final String MYCSAUSER_TEL_NUM = "MYCSAUSER_TEL_NUM";
    // 2023/09/20 QC#61782 Add End
}
