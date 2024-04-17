/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC171001.constant;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         T.Yoshida      Create          N/A
 * 2016/11/07   Fujitsu         H.Ikeda        Update          S21_NA#15763
 * 2017/08/04   Fujitsu         W.Honda        Update          S21_NA#20228
 * 2017/12/26   Fujitsu         K.Ishizuka     Update          S21_NA#20164(Sol#356)
 * </pre>
 */
public class NWZC171001Constant {

    /** MODE : SAVE */
    public static final String MODE_SAVE = "01";

    /** MODE : SUBMIT */
    public static final String MODE_SUBMIT = "02";

    // Add Start 2017/08/04 S21_NA#20228
    /** MODE : ERROR */
    public static final String MODE_ERROR = "03";
    // Add End 2017/08/04 S21_NA#20228

    /** REQUEST TYPE : NEW */
    public static final String REQUEST_TYPE_NEW = "1";

    /** REQUEST TYPE : MOD */
    public static final String REQUEST_TYPE_MOD = "2";

    /** REQUEST TYPE : DELETE */
    public static final String REQUEST_TYPE_DEL = "3";

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Process ID */
    public static final String PROCESS_ID = "OM";

    /** Document Type */
    public static final String DOCUMENT_TYPE = "SA";

    /** Event ID (Save) */
    public static final String EVENT_ID_SAVE = "Schedule Save";

    /** Event ID (Submit) */
    public static final String EVENT_ID_SUBMIT = "Schedule Submit";

    /** Event ID (Create) */
    public static final String EVENT_ID_CREATE = "Schedule Create";

    /** Event ID (Change) */
    public static final String EVENT_ID_CHANGE = "Schedule Change";

    /** Event ID (Delete) */
    public static final String EVENT_ID_DELETE = "Schedule Cancel";

    /** Blank */
    public static final String BLANK = "";

    // /////////////////
    // Message ID //////
    // /////////////////
    /** NWZM0011E : "Data Global Company Code" is required. */
    public static final String NWZM0011E = "NWZM0011E";

    /** NWZM0346E : Sales Date is required. */
    public static final String NWZM0346E = "NWZM0346E";

    /** NWZM0977E : Invalid value is set for process mode. */
    public static final String NWZM0977E = "NWZM0977E";

    /** NWZM1401E : "Direct Sales Order Category Code" is not entered. */
    public static final String NWZM1401E = "NWZM1401E";

    /** NWZM1253E : An input parameter "DS Order Type Code" has not been set. */
    public static final String NWZM1253E = "NWZM1253E";

    /** "Scheduling Agreement Category Code" is required. */
    public static final String NWZM1845E = "NWZM1845E";

    /** CPO Order Type Code is not entered. */
    public static final String NWZM0097E = "NWZM0097E";

    /** "Price Category Code" is required. */
    public static final String NWZM1405E = "NWZM1405E";

    /** "Configuration ID" is required. */
    public static final String NWZM1846E = "NWZM1846E";

    /** Valid From Date of the entered parameter is not set. */
    public static final String NWZM1764E = "NWZM1764E";

    /** Valid Through Date of the entered parameter is not set. */
    public static final String NWZM1765E = "NWZM1765E";

    /** "Sell to customer Account Code" is required. */
    public static final String NWZM1402E = "NWZM1402E";

    /** "Sold To Customer Code" is required. */
    public static final String NWZM1403E = "NWZM1403E";

    /** Bill To Customer Account Code is required. */
    public static final String NWZM1377E = "NWZM1377E";

    /** Bill To Customer Location Code is required. */
    public static final String NWZM1378E = "NWZM1378E";

    /** "Pricing Base Date" is required. */
    public static final String NWZM1799E = "NWZM1799E";

    /** Freight Condition Code of the parameter is not set. */
    public static final String NWZM1158E = "NWZM1158E";

    /** An input parameter "Shipping Service Level Code" has not been set. */
    public static final String NWZM1255E = "NWZM1255E";

    /** "Scheduling Agreement Line Number" is required. */
    public static final String NWZM1847E = "NWZM1847E";

    /** Merchandise Code is required. */
    public static final String NWZM0021E = "NWZM0021E";

    /** Only "0" or a larger numerical value can be set to "Quantity Allowed". */
    public static final String NWZM1849E = "NWZM1849E";

    /** "Scheduling Agreement Scheduled Number" is required. */
    public static final String NWZM1848E = "NWZM1848E";

    /** The parameter's "Requested Delivery Date" is not set. */
    public static final String NWZM0794E = "NWZM0794E";

    /** Only "0" or a larger numerical value can be set to "Order Quantity". */
    public static final String NWZM1850E = "NWZM1850E";

    /** "Request Type Code" has an invalid value. */
    public static final String NWZM0209E = "NWZM0209E";

    /** "Sales Credit Primary Key" is required. */
    public static final String NWZM1851E = "NWZM1851E";

    /** Contact Person Primary Key" is required if Request Type is not New. */
    public static final String NWZM1805E = "NWZM1805E";

    /** "Contact Person Type Code" is required. */
    public static final String NWZM1806E = "NWZM1806E";

    /** "Contact Person First Name" is required. */
    public static final String NWZM1807E = "NWZM1807E";

    /** Failed to insert the SCHD_AGMT. */
    public static final String NWZM1852E = "NWZM1852E";

    /** Failed to update the SCHD_AGMT. */
    public static final String NWZM1853E = "NWZM1853E";

    /** Failed to delete the SCHD_AGMT. */
    public static final String NWZM1854E = "NWZM1854E";

    /** Failed to insert the SCHD_AGMT_LINE. */
    public static final String NWZM1855E = "NWZM1855E";

    /** Failed to update the SCHD_AGMT_LINE. */
    public static final String NWZM1856E = "NWZM1856E";

    /** Failed to delete the SCHD_AGMT_LINE. */
    public static final String NWZM1857E = "NWZM1857E";

    /** Failed to insert the SCHD_AGMT_PLN. */
    public static final String NWZM1858E = "NWZM1858E";

    /** Failed to update the SCHD_AGMT_PLN. */
    public static final String NWZM1859E = "NWZM1859E";

    /** Failed to delete the SCHD_AGMT_PLN. */
    public static final String NWZM1860E = "NWZM1860E";

    /** Failed to insert the SCHD_AGMT_SLS_CR. */
    public static final String NWZM1861E = "NWZM1861E";

    /** Failed to update the SCHD_AGMT_SLS_CR. */
    public static final String NWZM1862E = "NWZM1862E";

    /** Failed to delete the SCHD_AGMT_SLS_CR. */
    public static final String NWZM1863E = "NWZM1863E";

    /** Failed to insert the SCHD_AGMT_CTAC_PSN. */
    public static final String NWZM1864E = "NWZM1864E";

    /** Failed to update the SCHD_AGMT_CTAC_PSN. */
    public static final String NWZM1865E = "NWZM1865E";

    /** Failed to delete the SCHD_AGMT_CTAC_PSN. */
    public static final String NWZM1866E = "NWZM1866E";

    /** Failed to insert the SCHD_AGMT_PRC_CALC_BASE. */
    public static final String NWZM1867E = "NWZM1867E";

    /** Failed to update the SCHD_AGMT_PRC_CALC_BASE. */
    public static final String NWZM1868E = "NWZM1868E";

    /** Failed to delete the SCHD_AGMT_PRC_CALC_BASE. */
    public static final String NWZM1869E = "NWZM1869E";

    /** DS Contract Number is required. */
    public static final String NWZM2031E = "NWZM2031E"; // Add 2016/10/06 S21_NA#11883

    // Add Start 2016/11/07 S21_NA#15763
    /** Default Freight Term could not be obtained. Scheduling Agreement has been created without Freight Term as Saved status.Scheduling Agreement # : @. */
    public static final String NWZM2051W = "NWZM2051W";
    // Add End 2016/11/07 S21_NA#15763
    
    /** Attention is required. */
    public static final String NWZM2251E = "NWZM2251E"; // Add 2017/12/26 S21_NA#20164
}
