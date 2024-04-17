/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NLCL1020.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 03/11/2019   Fujitsu         T.Ogura         Update          QC#30705
 * 03/26/2019   Fujitsu         T.Ogura         Update          QC#30124
 *</pre>
 */
public class NLCL1020Constant {

    /**
     */
    public static final String ERRFLAG_RG0 = "0";

    /**
     */
    public static final String ERRFLAG_RG1 = "1";

    /** Field Name Transaction Number*/
    public static final String LOC_FR = "Location From";

    /** Field Name Transaction Number*/
    public static final String LOC_TO = "Location To";

    /** Field Name Transaction Number*/
    public static final String TRANSACTION_NUMBER = "Transaction Number";

    /** Field Name MDSE_CD*/
    // 10/20/2015 mod start
    // public static final String MDSE_CD = "Mdse Cd";
    public static final String MDSE_CD = "Item Number";
    // 10/20/2015 mod end

    /** Business ID:NLCL1020 */
    public static final String BUSINESS_ID = "NLCL1020";

    /** Screen ID:NLCL1020Scrn00 */
    public static final String SCREEN_ID = "NLCL1020Scrn00";

    /** The functions for Entry */
    public static final String ENTRY = "ENTRY";

    /** The functions for inquiry only mode */
    public static final String INQUIRY_ONLY = "INQUIRY_ONLY";

    /** Download button */
    public static final String CMN_DOWNLOAD = "btn6";

    /** Search button */
    // START 2019/03/11 T.Ogura [QC#30705,MOD]
//    public static final String SEARCH_BTN = "SearchTrx";
    public static final String SEARCH_BTN = "Search_Trx";
    // END   2019/03/11 T.Ogura [QC#30705,MOD]

    /** Delete Button */
    public static final String BTN_DELETE_LINE = "Delete_Dtaill_Line";    // 2019/03/26 T.Ogura [QC#30124,ADD]

    /** Function ID for inquiry */
    public static final String FUNC_NLCL1020T010 = "NLCL1020T010";

    /** Function ID for Update */
    public static final String FUNC_NLCL1020T020 = "NLCL1020T020";

    //Event
    /** Event  OpenWin_NPAL1010_LocFrom */
    public static final String EVENT_OPENWIN_LOCFROM = "OpenWin_NPAL1010_LocFrom";

    /** Event  OpenWin_NPAL1010_LocTo */
    public static final String EVENT_OPENWIN_LOCTO = "OpenWin_NPAL1010_LocTo";

    //Message
    /** [@] is [@]. */
    public static final String NLCM0111E = "NLCM0111E";

    /** Due to an error, process cannot be completed.  Please contact IT Department. */
    public static final String NLCM0001E = "NLCM0001E";

    /** The number of Detail rows reached to the maximum.  No more Details can be registered. */
    public static final String NLCM0025E = "NLCM0025E";

    /** Entered data is already registered.. */
    public static final String NLCM0019E = "NLCM0019E";

    /** [@] is [@]. */
//    public static final String NLCM0112W = "NLCM0112W";    // 2019/03/26 T.Ogura [QC#30124,DEL]

    /** You can click 'Submit' button. */
    public static final String NLCM0008I = "NLCM0008I";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Detail requires at least one line.  Please enter. */
    public static final String NLCM0010E = "NLCM0010E";

    /** [@] field is mandatory  Please enter. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Values remains in the detail line entry field.  Add detail lines. */
    public static final String NLCM0015E = "NLCM0015E";

    /** This cannot be processed on this date.  Please confirm the date. */
    public static final String NLCM0041E = "NLCM0041E";

    /** Please check at least 1 checkbox. */
    public static final String NLCM0233E = "NLCM0233E";    // 2019/03/26 T.Ogura [QC#30124,ADD]


    /**
     * value for message kind error
     */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Function Button 11 */
    public static final String[] BTN_ADD_LINE = {"btn11", "Add_Dtaill_Line", "Add Line" };

    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 11 */
    public static final int IDX_11 = 11;
}

