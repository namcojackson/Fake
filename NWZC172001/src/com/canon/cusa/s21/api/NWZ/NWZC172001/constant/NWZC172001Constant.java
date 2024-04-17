/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC172001.constant;

/**
 * <pre>
 * Creation of Schedule via the Deal Configurator API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 * 2016/11/07   Fujitsu         H.Ikeda         UpDate          S21_NA#15763
 * 2017/03/07   Fujitsu         S.Iidaka        UpDate          S21_NA#15763
 * 2017/05/09   Hitachi         T.Tomita        Update          S21_NA:RS#8416
 * 2017/12/22   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/02/08   Fujitsu         K.Ishizuka      Update          S21_NA#20297(Sol#379)
 *</pre>
 */
public class NWZC172001Constant {
    /*****************************************************************
     * Message ID
     ****************************************************************/
    /** Data Global Company Code is not entered. */
    public static final String NWZM0163E = "NWZM0163E";

    /** Request Type is not entered. */
    public static final String NWZM0157E = "NWZM0157E";

    /** Sales Date is required. */
    public static final String NWZM0346E = "NWZM0346E";

    /** Reference CPO Order Number of the parameter is not set. */
    public static final String NWZM1597E = "NWZM1597E";

    /** DS Contract PK of the parameter is not set. */
    public static final String NWZM2227E = "NWZM2227E";

    /** Length of the contract Days Amount Of Time of the parameter is not set. */
    public static final String NWZM1760E = "NWZM1760E";

    /** An input parameter "DS Order Type Code"  has not been set. */
    public static final String NWZM1253E = "NWZM1253E";

    /** An input parameter "Model ID"  has not been set. */
    public static final String NWZM1567E = "NWZM1567E";

    /** Serial Number of the parameter is not set. */
    public static final String NWZM1761E = "NWZM1761E";

    /** Service Machine Master Pk of the parameter is not set. */
    public static final String NWZM1762E = "NWZM1762E";

    /** Service Config Master PK of the parameter is not set. */
    public static final String NWZM1303E = "NWZM1303E";

    /** Contract Number of the parameter is not set. */
    public static final String NWZM1143E = "NWZM1143E";

    /** Contract Sequence Number of the parameter is not set. */
    public static final String NWZM1763E = "NWZM1763E";

    /** Valid From Date of the entered parameter is not set. */
    public static final String NWZM1764E = "NWZM1764E";

    /** Valid Through Date of the entered parameter is not set. */
    public static final String NWZM1765E = "NWZM1765E";

    /** An invalid date error occurred to the  "Valid From Date field ". */
    public static final String NWZM1766E = "NWZM1766E";

    /** An invalid date error occurred to the  "Valid Through Date field ". */
    public static final String NWZM1767E = "NWZM1767E";

    /** DS Contract Detail PK of the parameter is not set. */
    public static final String NWZM2228E = "NWZM2228E";

    /** "Merchandise Code" for the entered parameter is not set. */
    public static final String NWZM0996E = "NWZM0996E";

    /** Detail List of the entered parameter is not set. */
    public static final String NWZM1768E = "NWZM1768E";

    /** Scheduling Line Number of the entered parameter is not set. */
    public static final String NWZM1769E = "NWZM1769E";

    /** Scheduling Allow Qty of the entered parameter is not set. */
    public static final String NWZM1770E = "NWZM1770E";

    /** Shipping Interval Code of the entered parameter is not set. */
    public static final String NWZM1771E = "NWZM1771E";

    /** Onetime Ship Quantity of the entered parameter is not set. */
    public static final String NWZM1772E = "NWZM1772E";

    /** Failed to insert the SCHD_CRAT_TMPL. */
    public static final String NWZM1773E = "NWZM1773E";

    /** Failed to insert the SCHD_CRAT_TMPL_LINE. */
    public static final String NWZM1774E = "NWZM1774E";

    /** "Request Type Code" has an invalid value. */
    public static final String NWZM0209E = "NWZM0209E";

    /** The target "CPO Service(Shell) Pkey" cannot be found. */
    public static final String NWZM1778E = "NWZM1778E";

    /** The target "Service Config Master PKey" cannot be found. */
    public static final String NWZM1779E = "NWZM1779E";

    /** "Valid From Date" cannot be later than "Valid Through Date". */
    public static final String NWZM1786E = "NWZM1786E";

    /** First Shipment Quantity of the entered parameter is not set. */
    public static final String NWZM2222E = "NWZM2222E";

    // Add Start 2016/11/07 S21_NA#15763
    /** Default Freight Term could not be obtained. Scheduling Agreement has been created without Freight Term as Saved status.Scheduling Agreement # : @. */
    public static final String NWZM2051W = "NWZM2051W";
    // Add End 2016/11/07 S21_NA#15763

    /** Data delete failure. (table name is [SCHD_CRAT_TMPL]) */
    public static final String NWZM2055E = "NWZM2055E";

    /** Data delete failure. (table name is [SCHD_CRAT_TMPL_LINE]) */
    public static final String NWZM2056E = "NWZM2056E";
    
    // Add Start 2017/12/22 S21_NA#20164
    public static final String SPACE = " ";
    // Add End   2017/12/22 S21_NA#20164
    
    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    /** Line */ 
    public static final String LINE = "\r\n";
    
    /** Blank */
    public static final String BLANK = "";
    
    /** Order Category Context Type Code (EQUIPMENT_ORDER) */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /** Order Category Context Type Code (SUPPLIES_ORDER) */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";
    
    /** Shipping Comment Limit Size*/
    public static final int SHPG_CMT_TXT_LIMIT_SIZE = 260;
    // 2018/02/08 S21_NA#20297(Sol#379) Add End

    /*****************************************************************
     * Variable
     ****************************************************************/
    /** Request Type : Template */
    public static final String RQST_TP_TMPL = "1";

    /** Request Type : Schedule */
    public static final String RQST_TP_SCHD = "2";

    /*****************************************************************
     * Variable for Scheduling Agreement Update API[NWZC1710]
     ****************************************************************/
    /** Request Type Sales Credit : New */
    public static final String RQST_TP_SLS_CR_NEW = "1";

    /** Request Type Contact Person : New */
    public static final String RQST_TP_CTAC_PSN_NEW = "1";

}
