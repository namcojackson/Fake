/**
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC182001.constant;

/**
 * <pre>
 * Auto Add RMA API Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/06/07   Fujitsu         T.ishii         Update          S21_NA#5340
 * 2019/07/25   Fujitsu         S.Kosaka        Update          QC#51941
 *</pre>
 */
public class NWZC182001Constant {

    /** "Global Company Code" is not set. */
    public static final String NWZM0473E = "NWZM0473E";

    /** An input parameter "DS Order Category Code" has not been set. */
    public static final String NWZM1568E = "NWZM1568E";

    /** An input parameter "DS Order Type Code" has not been set. */
    public static final String NWZM1253E = "NWZM1253E";

    /** "DS Order Category Code" does not exist in the Master. */
    public static final String NWZM1415E = "NWZM1415E";

    /** The data does not exist in DS Order Type. */
    public static final String NWZM1286E = "NWZM1286E";

    /**
     * "Service Configuration Master PK" does not exist in the Master.
     */
    public static final String NWZM1442E = "NWZM1442E";

    /**
     * Order Category of search results mismatched with input
     * parameter.
     */
    public static final String NWZM1575E = "NWZM1575E";

    /** Order Type of search results mismatched with input parameter.. */
    public static final String NWZM1576E = "NWZM1576E";

    /** Service Machine Master Status is not 'Installed'. */
    public static final String NWZM1577E = "NWZM1577E";

    /** Input Parameter "Config Id" or "Serial#", has not been set. */
    public static final String NWZM1578E = "NWZM1578E";

    /** COLUMN_NAME (SVC_MACH_MSTR_STS_CD) */
    public static final String COLUMN_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";

    /** COLUMN_NAME (DS_ORD_CATG_CD) */
    public static final String COLUMN_DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** COLUMN_NAME (DS_ORD_TP_CD) */
    public static final String COLUMN_DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** COLUMN_NAME (MDSE_CD) */
    public static final String COLUMN_MDSE_CD = "MDSE_CD";

    /** COLUMN_NAME (SER_NUM) */
    public static final String COLUMN_SER_NUM = "SER_NUM";

    /** COLUMN_NAME (SVC_CONFIG_MSTR_PK) */
    public static final String COLUMN_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** COLUMN_NAME (SVC_MACH_MSTR_PK) */
    public static final String COLUMN_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK"; // S21_NA#5340

    // 2019/07/25 QC#51941 Add Start
    /** Not exist main machine in Line (N) */
    public static final String EXIST_MAIN_MACHINE_IN_LINE_N = "N";

    /** Not exist main machine in Line (Y) */
    public static final String EXIST_MAIN_MACHINE_IN_LINE_Y = "Y";
    // 2019/07/25 QC#51941 Add Start

}
