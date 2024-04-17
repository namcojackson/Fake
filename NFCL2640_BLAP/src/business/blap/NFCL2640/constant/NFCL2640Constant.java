/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2640.constant;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NFCL2640Constant {

    /** Add Month Size */
    public static final int ADD_MONTH_SIZE = 12;

    /** Online batch type code */
    public static final String ONL_BAT_TP_CD = "1";

    /** VAR_CHAR_CONST Key */
    public static final String CONST_AR_SUB_SYS_ID = "AR_SUB_SYS_ID";

    // --------------------------------
    // Message
    // --------------------------------
    /** @ has a duplicate of data */
    public static final String NFCM0580E = "NFCM0580E";

    /** Search results exceeded @. Please modify search criteria. */
    public static final String NFCM0798E = "NFCM0798E";

    /** Statement Date which you added is future over two years. */
    public static final String NFCM0802W = "NFCM0802W";

    /** Cannot change Print Status into Printed. */
    public static final String NFCM0799E = "NFCM0799E";

    /**
     * No search results found. Please modify the search criteria.
     */
    public static final String NFCM0803E = "NFCM0803E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Data update failure.(ReturnCode = [@]) */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** Data insert failure.(ReturnCode = [@]) */
    public static final String ZZZM9012E = "ZZZM9012E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";
 
    /** @ cannot be the past date. */
    public static final String NFCM0801E = "NFCM0801E";

    /** Entry Parameter Error [@] */
    public static final String NFCM0501E = "NFCM0501E";

    /** Statement Date */
    public static final String STMT_DT = "Statement Date";
}
