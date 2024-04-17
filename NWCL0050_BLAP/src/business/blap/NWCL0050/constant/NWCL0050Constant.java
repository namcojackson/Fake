/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0050.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2017/03/07   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2017/12/28   Hitachi         E.Kameishi      Update          QC#20312
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 * 2019/02/26   Fujitsu         Y.Kanefusa      Update          S21_NA#30519
 */
public class NWCL0050Constant {

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Please select at least one invoice. */
    public static final String NWCM0096E = "NWCM0096E";

    /** This invoice is part of Bill. Do you want to continue selecting for emailing? */
    public static final String NWCM0097W = "NWCM0097W";

    /** Account is multiple. Please select one of the account. */
    public static final String NWCM0098E = "NWCM0098E";

    /** Please select within @ invoices. */
    public static final String NWCM0099E = "NWCM0099E";

    /** Search results is over @. Please narrow your search criteria and retry. */
    public static final String NWCM0101W = "NWCM0101W";

    /** Consolidated Invoice cannot be selected. */
    public static final String NWCM0143E = "NWCM0143E";

    /** Selected Invoice does not have  "Invoice File URL". */
    public static final String NWCM0160E = "NWCM0160E";

    // QC#30519 2019/02/26 Add Start
    /** You cannot select more than [@] invoices to process Credit Card Payment */
    public static final String NWCM0161E = "NWCM0161E";
    // QC#30519 2019/02/26 Add End

    /** VarChar Constant Name */
    public static final String CONST_RECORD_PER_PAGE = "NWCL0050_RECORD_PER_PAGE";

    /** Number Constant Name */
    public static final String CONST_MAX_ROW_CNT = "NWCL0050_MAX_ROW_CNT";

    /** Base */
    public static final String BASE = "%Base:";

    /** Comma */
    public static final String COMMA = ",";

    /** Percent */
    public static final String PERCENT = "%";
    
    // ADD START S21_NA QC#13856
    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;

    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;
    
    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** Too many search results. Please narrow your search criteria and retry */
    public static final String ZZZM9002W = "ZZZM9002W";
    
    /** DownLoad File Name */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NWCL0050F00";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";
    // ADD END S21_NA QC#13856
    // START 2017/12/28 E.Kameishi [QC#20312,ADD]
    /** CSV File Extension */
    public static final String KEY_INFO_CD_10 = "10";
    // END 2017/12/28 E.Kameishi [QC#20312,ADD]
    // QC#30519 2019/02/26 Add Start
    public static final int PAYMENT_MAX_CNT = 90;
    // QC#30519 2019/02/26 Add End
    // QC#53014 2019/09/17 Add Start
    public static final String NO = "NO";
    // QC#53014 2019/09/17 Add End

}
