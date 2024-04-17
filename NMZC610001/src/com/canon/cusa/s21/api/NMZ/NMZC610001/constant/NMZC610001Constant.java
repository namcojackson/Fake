/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC610001.constant;

/**
 *<pre>
 * Customer Information Get API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         N.Sugiura       Create          N/A
 * 2015/05/27   Fujitsu         M.suzuki        Update          QC#9073
 * 2018/02/07   Fujitsu         Hd.Sugawara     Update          QC#20297(Sol#379)
 * 2018/03/20   Fujitsu         Hd.Sugawara     Update          QC#25000
 *</pre>
 */
public class NMZC610001Constant {

    /** PROCESS MODE 01 Transaction. */
    public static final String PROCESS_MODE_TRANSACTION = "01";

    /** PROCESS MODE 02 Invoice. */
    public static final String PROCESS_MODE_INVOICE = "02";

    /** PROCESS MODE 03 Instruction. */
    public static final String PROCESS_MODE_INSTRUCTION = "03";

    /** PROCESS MODE 04  Default Bill To Ship To. */
    public static final String PROCESS_DEFAULT_BILL_SHIP = "04";

    /** PROCESS MODE 05  Special Handling. */
    public static final String PROCESS_MODE_SPECIAL_HANDLING = "05";

    /** PROCESS MODE 06  Eligible Check. */
    public static final String PROCESS_MODE_ELIGIBLE_CHECK = "06";

    /** PROCESS MODE 07  Related Bill or Ship. */
    public static final String PROCESS_MODE_RELATED_BILL_SHIP = "07";

    /** PROCESS MODE 08 Instruction for Invoice Print. */
    public static final String PROCESS_MODE_INSTRUCTION_FOR_INV_PRINT = "08";

    // Add Start 2018/02/07 QC#20297(Sol#379)
    /** PROCESS MODE 09 Shipping Default Information. */
    public static final String PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION = "09";
    // Add End 2018/02/07 QC#20297(Sol#379)

    // Add Start 2018/03/20 QC#25000
    /** PROCESS MODE 07 - SUB MODE 01 : All RGTN_STS_CD. */
    public static final String RELATED_BILL_SHIP_ALL_RGTN_STS_CD = "01";
    // Add End 2018/03/20 QC#25000

    /** Search Level : Account Level*/
    public static final String SEARCH_LVL_ACCT = "1";

    /** Search Level : Location Level*/
    public static final String SEARCH_LVL_LOC = "2";

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NMZM0009E = "NMZM0009E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NMZM0011E = "NMZM0011E";

    // Add Start 2018/03/20 QC#25000
    /**
     * Mode is invalid.
     */
    public static final String NMZM0024E = "NMZM0024E";
    // Add End 2018/03/20 QC#25000

    /**
     * Process mode is not set.
     */
    public static final String NMZM0054E = "NMZM0054E";

    /**
     * Invalid value is set for process mode.
     */
    public static final String NMZM0055E = "NMZM0055E";

    /**
     * Parameter for @ is not registered yet.
     */
    public static final String NMZM0056E = "NMZM0056E";

    /**
     * It failed to register to the [@] table.
     */
    public static final String NMZM0057E = "NMZM0057E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name: [@], Key Value: [@]
     */
    public static final String NMZM0058E = "NMZM0058E";

    /**
     * The entered [@] does not exist in [@].
     */
    public static final String NMZM0059E = "NMZM0059E";

    /**
     * The effective date of the entered data is incorrect.
     */
    public static final String NMZM0060E = "NMZM0060E";

    /**
     * [@] for update target is not found.
     */
    public static final String NMZM0061E = "NMZM0061E";

    /**
     * Credit limit has not been set. Regstration status can not be changed to P20.
     */
    public static final String NMZM0062E = "NMZM0062E";

    /**
     * [@] and [@] and [@] of the input parameter is a mandatory field either.
     */
    public static final String NMZM0063E = "NMZM0063E";

    /**
     * @ is duplicated.
     */
    public static final String NMAM0072E = "NMAM0072E";

    // Add Start 2018/02/07 QC#20297(Sol#379)
    /**
     * Ship to Account is not set.
     */
    public static final String NMZM0350E = "NMZM0350E";

    /**
     * DS Order Category Code is mandatory.
     */
    public static final String NMZM0363E = "NMZM0363E";

    /**
     * DS Order Type Code is mandatory.
     */
    public static final String NMZM0364E = "NMZM0364E";

    /**
     * Ship To Cust Code is mandatory.
     */
    public static final String NMZM0365E = "NMZM0365E";

    /**
     * [Line of Business Type Code] does not exist.
     */
    public static final String NMZM0366E = "NMZM0366E";

    /**
     * [Direct Sales Business Area Code] does not exist.
     */
    public static final String NMZM0367E = "NMZM0367E";

    /**
     * Order Category Context Type Code : EQUIPMENT_ORDER
     */
    public static final String ORD_CATG_CTX_TP_CD_EQUIPMENT = "EQUIPMENT_ORDER";

    /**
     * Order Category Context Type Code : SUPPLIES_ORDER
     */
    public static final String ORD_CATG_CTX_TP_CD_SUPPLIES = "SUPPLIES_ORDER";
    // Add End 2018/02/07 QC#20297(Sol#379)

    /** DS_ACCT_NUM degit:28 */
    public static final int DS_ACCT_NUM = 28;

    /** LOC_NUM degit:30 */
    public static final int LOC_NUM = 30;

    /** CMPY_NM degit:60 */
    public static final int LEN_CMPY_NM = 60;

    /** Terminated Effective Date */
    public static final String TERMINATED_EFF_DT = "99991231";

    /** SEQ_DS_ACCT_NUM */
    public static final String SEQ_DS_ACCT_NUM = "DS_ACCT_NUM";

    /** SEQ_LOC_NUM */
    public static final String SEQ_LOC_NUM = "LOC_NUM";

    /** SEQ_BILL_TO_CUST_CD */
    public static final String SEQ_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** SEQ_SHIP_TO_CUST_CD */
    public static final String SEQ_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";
    //-- Add Start QC#9073 2016/05/27 -----
    /** MAX_DATE_VALUE */
    public static final String MAX_DATE_VALUE = "99991231";
    //-- Add Start QC#9073 2016/05/27 -----
    public static final String CONST_NMZC6100_RELATED_BILL_SHIP_CNT = "NMZC6100_RELATED_BILL_SHIP_CNT";
    
}
