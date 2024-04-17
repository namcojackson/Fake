/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NMAL2800Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/11/04   Fujitsu         C.Yokoi         Update          CSA-QC#15296
 * 2016/11/17   Fujitsu         R.Nakamura      Update          CSA-QC#16082
 *</pre>
 */
public class NMAL2800Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2800";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2800Scrn00";

    /** Privilege: Update */
    public static final String PRIV_UPD = "NMAL2800T020";

    /** Function Code: Search */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code: Update */
    public static final String FUNC_CD_UPD = "40";

    /** Mode: Search */
    public static final String MODE_CD_SRCH = "1";

    /** Mode: CSV Update */
    public static final String MODE_CD_UPL = "2";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    /** CMN_Close */
    public static final String BTN_CMN_CLS = "CMN_Close";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Please set at least one search criteria. */
    public static final String NMAM0288E = "NMAM0288E";

    /** Please set either @ or @. */
    public static final String NMAM8229E = "NMAM8229E";

    /** The format of [@] is incorrect. */
    public static final String NMAM0052E = "NMAM0052E";

    /** Please set @ mode. */
    public static final String NMAM8552E = "NMAM8552E";

    /** Please clear the search result before uploading CSV file. */
    public static final String NMAM8553E = "NMAM8553E";

    /**
     * Phone number format is incorrect. Minimum length is 7digit,
     * Maximum length is 20 digit.
     */
    public static final String NMAM8348E = "NMAM8348E";

    /**
     * The file size is too big for upload. Only up to[@]bytes are
     * permitted.
     */
    public static final String NZZM0006E = "NZZM0006E";

    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /** @ should be blank. */
    public static final String NMAM8608E = "NMAM8608E";

    /**
     * The download information could not be found. Go back to Search
     * and try again.
     */
    public static final String NMAM8298E = "NMAM8298E";
    ;
    // 2016/11/04 CSA-QC#15296 Add Start
    /** [@] must be entered. Please press Copy button to input address field. */
    public static final String NMAM8656E = "NMAM8656E";;
    // 2016/11/04 CSA-QC#15296 Add End

    /** Saved Search Option */
    public static final String SRCH_OPT_PK = "Saved Search Options";

    /** Search Option Name */
    public static final String SRCH_OPT_NM = "Search Option Name";

    /** Mode */
    public static final String MODE = "Mode";

    /** Mode: Search */
    public static final String MODE_SRCH = "Search";

    /** Mode: CSV Upload */
    public static final String MODE_UPL = "CSV Upload";

    /** Process Flag */
    public static final String PROS_RVW_STS_CD = "Process Flag";

    /** Assign To Territory */
    public static final String AFT_TRTY_ORG_CD = "Assign To Territory";

    /** New Address1 */
    public static final String AFT_LOC_FIRST_LINE_ADDR = "New Address1";

    /** New Address2 */
    public static final String AFT_LOC_SCD_LINE_ADDR = "New Address2";

    /** New Address3 */
    public static final String AFT_LOC_THIRD_LINE_ADDR = "New Address3";

    /** New Address4 */
    public static final String AFT_LOC_FRTH_LINE_ADDR = "New Address4";

    /** New City */
    public static final String AFT_LOC_CTY_ADDR = "New City";

    /** New State Code */
    public static final String AFT_LOC_ST_CD = "New State Code";

    /** New Post Code */
    public static final String AFT_LOC_POST_CD = "New Post Code";

    /** New County */
    public static final String CNTY_NM = "New County";

    /** New Country */
    public static final String AFT_CTRY_CD = "New Country";

    /** New Phone */
    public static final String AFT_TEL_NUM = "New Phone";

    /** New Fax */
    public static final String AFT_FAX_NUM = "New Fax";

    /** New DNB Name */
    public static final String AFT_DS_ACCT_DUNS_NM = "New DNB Name";

    /** New Annual US Sales */
    public static final String AFT_DS_LOC_REV_AMT = "New Annual US Sales";

    /** New DUNS Number */
    public static final String AFT_DUNS_NUM = "New DUNS Number";

    /** New Line Of Business */
    public static final String AFT_DS_CUST_SIC_DESC_TXT = "New Line Of Business";

    /** New Employees Total */
    public static final String AFT_DS_LOC_EMP_NUM = "New Employees Total";

    /** New SIC Code */
    public static final String AFT_DS_CUST_SIC_CD = "New SIC Code";

    /** New Ult DUNS Number */
    public static final String AFT_DS_ULT_DUNS_NUM = "New Ult DUNS Number";

    /** New GLN */
    public static final String AFT_GLN_NUM = "New GLN";

    /** New Parent DUNS Number */
    public static final String AFT_DS_PRNT_DUNS_NUM = "New Parent DUNS Number";

    /** New HQ DUNS Number */
    public static final String AFT_HQ_DUNS_NUM = "New HQ DUNS Number";

    /** New Company SIC */
    public static final String AFT_DS_CMPY_SIC_CD = "New Company SIC";

    /** New Company SIC Description */
    public static final String AFT_DS_CMPY_SIC_DESC_TXT = "New Company SIC Description";

    /** Merge To Location ID */
    public static final String AFT_LOC_NUM = "Merge To Location ID";

    /** Merge To Prospect# */
    public static final String XTRNL_PROS_RVW_CMNT_TXT = "Merge To Prospect#";


    // --------------------------------
    // Popup
    // --------------------------------
    /** OpenWin_EmpInfo */
    public static final String POPUP_EMP = "OpenWin_EmpInfo";

    /** OpenWin_OwnerInfo */
    public static final String POPUP_OWNER = "OpenWin_OwnerInfo";

    /** OpenWin_OwnerTrtyInfo */
    public static final String POPUP_OWNER_TRTY = "OpenWin_OwnerTrtyInfo";

    /** OpenWin_TrtyForRule */
    public static final String POPUP_TRTY_RULE = "OpenWin_TrtyForRule";

    /** OpenWin_TrtyForResrc */
    public static final String POPUP_TRTY_RESRC = "OpenWin_TrtyForResrc";

    /** OpenWin_TrtyToAssign */
    public static final String POPUP_TRTY_ASSIGN = "OpenWin_TrtyToAssign";

    /** OpenWin_LocInfo */
    public static final String POPUP_LOC = "OpenWin_LocInfo";

    /** OpenWin_DupLocInfo */
    public static final String POPUP_DUP_LOC = "OpenWin_DupLocInfo";

    /** NMA2690001 */
    public static final String UPLOAD_CSV_ID = "NMA2690001";

    /** Suffix */
    public static final String SUFFIX = "C";

    // --------------------------------
    // File
    // --------------------------------
    /** file extension */
    public static final String EXT = "csv";

    // Add Start 2016/11/17 QC#16082
    /** file extension */
    public static final String TXT = "txt";

    /** file extension */
    public static final String XLSX = "xlsx";

    /** file extension */
    public static final String XLS = "xls";
    // Add End 2016/11/17 QC#16082

    /** file size */
    public static final int FILE_SIZE = 16;

    // --------------------------------
    // Format pattern
    // --------------------------------
    /** Phone Pattern */
    public static final String PHONE_PATTERN = "(\\d{10}|\\d{3}-\\d{3}-\\d{4})";

    /** Phone Format */
    public static final String PHONE_FORMAT = "9999999999 or 999-999-9999";

    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";

    /** Mode Edit:01 */
    public static final String SCRN_EVENT_EDIT = "01";

    /** Mode : Set(For Merge to Location ID) */
    public static final String MODE_SET_LOC = "03";

    /** Mode : Set(For Merge to Prospect#(For SFDC# / OASIS)) */
    public static final String MODE_SET_PROS = "04";

    /** Radio : Prospect */
    public static final BigDecimal RADIO_PROS = BigDecimal.valueOf(0);

    /** Radio : Merge to */
    public static final BigDecimal RADIO_MRG_TO = BigDecimal.valueOf(1);
}
