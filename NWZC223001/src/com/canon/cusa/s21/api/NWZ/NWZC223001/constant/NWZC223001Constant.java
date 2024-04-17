/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC223001.constant;

/**
 * <pre>
 * Auto Document Attachment API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2016   Fujitsu         Kamide          Create
 * 08/25/2016   Fujitsu         S.Iidaka        Update          Unit Test#201
 * </pre>
 */
public final class NWZC223001Constant {

    /** Order Entry Business ID */
    public static final String ORD_ENTRY_BIZ_ID = "NWAL1500";

    /** Order Entry Business Name */
    public static final String ORD_ENTRY_BIZ_NM = "Order Entry";

    /** Business App ID */
    public static final String BIZ_APP_ID = "NWZC2230";

    /** Business App Name */
    public static final String BIZ_APP_NM = "Auto Document Attachment API";

    /** Order Number Name */
    public static final String ORD_NUM_KEY_NM = "Order Number";

    /**
     * Data Type URL. see ZYPL0310Constant
     */
    public static final String ATT_DATA_TYPE_CODE_URL = "WP";

    /**
     * Doc Type Order. see ZYPL0310Constant
     */
    public static final String DOC_TYPE_ORDER = "10";

    /** CtgryNo */
    public static final String PRM_CTGRY_NO = "CtgryNo";

    /** DocID */
    public static final String PRM_DOC_ID = "DocID";

    /** API Mode Create */
    public static final String API_MODE_CREATE = "10";

    /** Max Message Length */
    public static final int MAX_MSG_LEN = 300;

    /** Date Time Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** VAR_CHAR_CONST_NWZC2230_THEREFORE_ASYNC_FLG */
    public static final String VAR_CHAR_CONST_NWZC2230_THEREFORE_ASYNC_FLG = "NWZC2230_THEREFORE_ASYNC_FLG";

    /**
     * "Global Company Code" is required.
     */
    public static final String NWZM0188E = "NWZM0188E";

    /**
     * "Document ID" is required.
     */
    public static final String NWZM1938E = "NWZM1938E";

    /**
     * "Category Number" is required.
     */
    public static final String NWZM1939E = "NWZM1939E";

    /**
     * "Category Name" is required.
     */
    public static final String NWZM1940E = "NWZM1940E";

    /**
     * "Business Document Number" is required.
     */
    public static final String NWZM1941E = "NWZM1941E";

    /**
     * Failed to register data. Table: [@]
     */
    public static final String NWAM0546E = "NWAM0546E";

    /**
     * "Parent Business Document Number" is required.
     */
    public static final String NWZM1981E = "NWZM1981E";

    /**
     * "Parent Business Document Number" does not exist in S21.
     */
    public static final String NWZM1999E = "NWZM1999E";

    /**
     * "Category Code" is required.
     */
    public static final String NWZM2000E = "NWZM2000E";

    /**
     * Cannot register Therefore data to S21. Please contact IT Support.
     */
    public static final String NWZM2002E = "NWZM2002E";

    /**
     * Specified SO Number is invalid or does not exist.
     */
    public static final String NWZM2006E = "NWZM2006E";

    /**
     * Specified CFS Lease Package Number is invalid or does not exist.
     */
    public static final String NWZM2038E = "NWZM2038E";
}
