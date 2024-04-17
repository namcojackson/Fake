/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0910.constant;

/**
 *<pre>
 * CFS Invoice Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public final class NSAL0910Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0910";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Contract# and CSA Invoice Combination Invalid.
     */
    public static final String NSAM0533E = "NSAM0533E";

    /**
     * Contract and Serial Number Combination is not valid.
     */
    public static final String NSAM0534E = "NSAM0534E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Serial# "@" is not found.
     */
    public static final String NSZM0703E = "NSZM0703E";

    /**
     * The entered Serial Number does not be specified contract.
     */
    public static final String NSAM0538E = "NSAM0538E";

    /**
     * The entered Invoice Number does not be specified contract.
     */
    public static final String NSAM0539E = "NSAM0539E";

    /**
     * Invoice Number and Serial Number Combination is not valid.
     */
    public static final String NSAM0540E = "NSAM0540E";

    /**
     * VAR_CHAR_CONST:NSAL0910_DEALER_CODE
     */
    public static final String NSAL0910_DEALER_CODE = "NSAL0910_DEALER_CODE";

    /**
     * Table name:CFS_INV_INTFC
     */
    public static final String TBL_NM_CFS_INV_INTFC = "CFS_INV_INTFC";

    /**
     * Column Name:Contract Number
     */
    public static final String COLUMN_NAME_CONTR_NUM = "Contract Number";

    /**
     * Column Name:Contract Number
     */
    public static final String COLUMN_NAME_INV_NUM = "Invoice Number";

    /**
     * Column Name:Contract Number
     */
    public static final String COLUMN_NAME_SER_NUM = "Serial Number";


}
