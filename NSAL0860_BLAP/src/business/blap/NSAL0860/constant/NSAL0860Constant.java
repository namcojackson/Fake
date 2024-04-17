/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0860.constant;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public final class NSAL0860Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BIZ_ID = "NSAL0860";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BIZ_ID + "Scrn00";

    /**
     * TEMPLATE_TITLE
     */
    public static final String SCREEN_TITLE = "IWR Registration & De-Registration Upload Process";

    /**
     * MEMO
     */
    public static final String MEMO = "**Please do not change the format";

    /**
     * The Upload File is empty or only has a header line, therefore it could not be processed. Please confirm the content of the Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * Serial# "@" is not found.
     */
    public static final String NSZM0703E = "NSZM0703E";

    /**
     * The entered Action value is incorrect.
     */
    public static final String NSAM0522E = "NSAM0522E";

    /**
     * The entered Serial# does not be specified model.Please execute on the screen.
     */
    public static final String NSAM0523E = "NSAM0523E";

    /**
     * This Serial# cannot be processed because it exists shared by dealer.
     */
    public static final String NSAM0536E = "NSAM0536E";

    /**
     * Reading the file layout has failed.  Please contact the system administrator.
     */
    public static final String ZYEM0012E = "ZYEM0012E";

    /**
     * This record exceeded the upper limit [@].This and bellow records were not able to be uploaded. 
     */
    public static final String ZYEM0013E = "ZYEM0013E";

    /**
     * No search results found.
     */
    public static final String ZZOM0011E = "ZZOM0011E";

    /**
     * Please input at least one search condition..
     */
    public static final String NSAM0005E = "NSAM0005E";

    /** 
     * Please select at least 1 checkbox. 
     * */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     *  [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /** FMSG_MAX_COUNT */
    public static final int FMSG_MAX_COUNT = 1000;;

    /** MAX_COUNT */
    public static final String MAX_COUNT_STR = "1000";

    /** FMSG_COL_LENGTH */
    public static final int FMSG_COL_LENGTH = 5;

    /** Header Line Contst :3 */
    public static final int HEADER_LINE_CONST = 3;

    /**
     * SET VALUE: Register
     */
    public static final String REGISTER = "Register";

    /**
     * SET VALUE: De-Register
     */
    public static final String DE_REGISTER = "De-Register";

    /**
     * SET VALUE: SUCCESS
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * SET VALUE: WARNING
     */
    public static final String WARNING = "WARNING";

    /**
     * SET VALUE: ERROR
     */
    public static final String ERROR = "ERROR";
}
