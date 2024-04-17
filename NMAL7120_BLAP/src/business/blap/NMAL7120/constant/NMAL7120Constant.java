package business.blap.NMAL7120.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 02/08/2016   FUJITSU         T.Murai         Update          #3006,3008
 * 09/12/2017   Fujitsu         K.Ishizuka      Update          QC#20206(Sol#269)
 * 07/11/2018   Fujitsu         M.Ishii         Update          QC#25557
 *</pre>
 */
public interface NMAL7120Constant {
	
    /**
     * RETURN_CD_NORMAL -- 0000
     */
    String RETURN_CD_NORMAL = "0000";
	
    /**
     * Maximum Amount Value 
     */
	BigDecimal MAXIMUM_AMOUNT = new BigDecimal("999999999999999.9999");
    /** CSV File Name Download */
    public static final String CSV_FILE_NAME_DOWNLOAD = "NMAL7120_CSMPSearch";

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
    public static final String NMAL7120_MAX_SRCH_ROW_CNT = "NMAL7120_MAX_SRCH_ROW_CNT";

    /** Const Name : Display Fetch size */
    public static final String NMAL7120_MAX_DPLY_ROW_CNT = "NMAL7120_MAX_DPLY_ROW_CNT";
    // 02/08/2016 #3006 Add End
    
    /** Price List Type Code : CSMP */ //S21_NA ADD QC#20206(L3#269)
    public static final String PRC_LIST_TP_CD_CSMP = "15";
    
    /** Price List Type Code : CSMP OFFER */ //S21_NA ADD QC#20206(L3#269)
    public static final String PRC_LIST_TP_CD_CSMP_OFFER = "16";

    // 2018/07/11 QC#25557 Add Start
    /** convert format : 0.regex, 1.replacement */
    public static final String[] DT_CONV_FORMAT = {"^(....)(..)(..)$", "$2/$3/$1" };
    // 2018/07/11 QC#25557 Add End

}
