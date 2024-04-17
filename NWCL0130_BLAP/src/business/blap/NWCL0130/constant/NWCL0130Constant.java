package business.blap.NWCL0130.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3006,3008
 * 2016/11/22   Fujitsu         M.Ohno          Update          S21_NA#16082
 *</pre>
 */
public interface NWCL0130Constant {
	
    /**
     * RETURN_CD_NORMAL -- 0000
     */
    String RETURN_CD_NORMAL = "0000";
	
    /**
     * Maximum Amount Value 
     */
	BigDecimal MAXIMUM_AMOUNT = new BigDecimal("999999999999999.9999");
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NWCL0130_SBRgneSearch";

    /** CSV File Extension */
    public static final String CSV_FILE_EXT = ".csv";

    /** CSV Fetch size */
    public static final int FETCH_SIZE_MAX = 1000;
    /** CSV: Max row */
    public static final int CSV_MAX_ROW = 65000;

    // 02/08/2016 #3008 Add Start
    /** Search Status "E" :Expired */
    public static final String EXPIERD = "E";

    /** Search Status "YES": Expired */
    public static final String EXPIERD_TXT = "Yes";
    // 02/08/2016 #3008 Add End

    // 02/08/2016 #3006 Add Start
    /** Const Name : Search Fetch size */
    public static final String NWCL0130_MAX_SRCH_ROW_CNT = "NWCL0130_MAX_SRCH_ROW_CNT";

    /** Const Name : Display Fetch size */
    public static final String NWCL0130_MAX_DPLY_ROW_CNT = "NWCL0130_MAX_DPLY_ROW_CNT";
    // 02/08/2016 #3006 Add End

    // Add  2016/11/22 M.Ohno S21_NA#16082
    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** CSV HEADER DOWNLOAD*/
    public static final String[] CSV_HDR_DOWNLOAD = new String[] {
          "Bill To"
        , "Bill To Account#"
        , "Customer Name"
        , "Bill#"
        , "Invoice#"
        , "Ticket#"
        , "URN#"
        , "Invoice Date"
        , "Amount"
        , "Action"
        };

    /** CSV file name */
    public static final String CSV_FILE_NAME = "NWCL0130_Download";

    /**
     * CSV file extension
     */
    public static final String CSV_FILE_EXTENSION = ".csv";

    public static final String NWCL0130_PULLDOEN_ACT_RGRN_TP = "NWCL0130_PULLDOEN_ACT_RGRN_TP";

    /** Comma */
    public static final String COMMA = ",";
}
