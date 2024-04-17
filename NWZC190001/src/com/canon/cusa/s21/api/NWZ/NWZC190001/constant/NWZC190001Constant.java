/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC190001.constant;

/**
 * <pre>
 * My CSA Supply Order Creation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/02   Fujitsu         A.Kosai         Create          N/A
 * 2017/12/21   Fujitsu         A.Kosai         Update          S21_NA#20164(Sol#356)
 * 2018/02/23   Fujitsu         K.Ishizuka      Update          S21_NA#23810
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * </pre>
 */
public final class NWZC190001Constant {

    /**
     * BIZ_ID
     */
    public static final String BIZ_ID = "NWZC1900";

    /**
     * SUB_NUM
     */
    public static final String SUB_NUM = "001";

    /**
     * Max Message Length
     */
    public static final int MAX_MSG_LEN = 300;

    /**
     * Delivery Comment Text Max Size
     **/
    public static final int DELY_ADDL_CMNT_TXT_MAX_SIZE = 240;

    // 2017/12/21 S21_NA#20164(Sol#356) Add Start
    /**
     * Sell to First Reference Comment Text Max Size
     **/
    public static final int SELL_TO_FIRST_REF_CMNT_TXT_MAX_SIZE = 90;
    // 2017/12/21 S21_NA#20164(Sol#356) Add End

    /**
     * DS_ACPO_NUM_SEQ
     */
    public static final String DS_ACPO_NUM_SEQ = "QC#";

    /**
     * The entered Merchandise Code does not exist in Master.
     */
    public static final String NWZM0036E = "NWZM0036E";

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
     * "Bill To Customer CD" is required.
     */
    public static final String NWZM0617E = "NWZM0617E";

    /**
     * "Ship To Customer CD" is required.
     */
    public static final String NWZM0619E = "NWZM0619E";

    /**
     * It failed to insert the [@]. PK [@]
     */
    public static final String NWZM1023E = "NWZM1023E";

    /**
     * Ship To Customer Code does not exist in master.
     */
    public static final String NWZM1125E = "NWZM1125E";

    /**
     * Bill To Customer Code does not exist in master.
     */
    public static final String NWZM1132E = "NWZM1132E";

    /**
     * The data does not exist in DS Order Type.
     */
    public static final String NWZM1286E = "NWZM1286E";

    /**
     * Bill To Customer Account Code is required.
     */
    public static final String NWZM1377E = "NWZM1377E";

    /**
     * Ship To Customer Account Code is required.
     */
    public static final String NWZM1379E = "NWZM1379E";

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
     * Parameter "Contract PK" does not exists in the DS_CONTR.
     */
    public static final String NWZM1679E = "NWZM1679E";

    /**
     * The target "Service Config Master PKey" cannot be found.
     */
    public static final String NWZM1779E = "NWZM1779E";

    /**
     * The relationship between 'Freight Condition Code', 'LOB' and
     * 'Shipping Service Level Code' is incorrect.
     */
    public static final String NWZM1912E = "NWZM1912E";

    /**
     * There are any message in xxMsgIdDetailList param.
     */
    public static final String NWZM1927E = "NWZM1927E";

    /**
     * No corresponding Machine Master information.
     */
    public static final String NWZM2220E = "NWZM2220E";

    /**
     * Ds Contract PK is required.
     */
    public static final String NWZM2239E = "NWZM2239E";

    /**
     * Merchandise Code for Machine is required.
     */
    public static final String NWZM2241E = "NWZM2241E";

    /**
     * Person First Name is required.
     */
    public static final String NWZM2244E = "NWZM2244E";

    /**
     * Person Last Name is required.
     */
    public static final String NWZM2245E = "NWZM2245E";

    /**
     * Telephone Number is required.
     */
    public static final String NWZM2246E = "NWZM2246E";

    /**
     * Email Address is required.
     */
    public static final String NWZM2247E = "NWZM2247E";

    /**
     * Service Machine Master PK is required.
     */
    public static final String NWZM2248E = "NWZM2248E";
    
    /**
     * The relationship between 'Order Category' and 'Order Reason' is incorrect.
     */ // 2018/02/23 S21_NA#23810 Add
    public static final String NWZM1450E = "NWZM1450E";

    // Add Start 2018/02/26 QC#22967
    /** The relationship between 'Ship To' and 'Sold To' is incorrect. */
    public static final String NWZM2254E = "NWZM2254E";

    /** The relationship between 'Sold To' and 'Bill To' is incorrect. */
    public static final String NWZM2255E = "NWZM2255E";
    // Add End 2018/02/26 QC#22967
}
