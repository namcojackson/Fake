package business.blap.ZYPL0310.constant;

import java.math.BigDecimal;

public class ZYPL0310Constant {
    /**
     * Message ID : Download file not found.
     */
    public static String MSG_ID_DOWNLOADFILE_NOT_FOUND = "ZYPM0007E"; 
    
    /**
     * Message ID : Attachment record duplicate.
     */
    public static String MSG_ID_ATTACHMENT_DUPLICATE = "ZYPM0006E";
    
    /**
     * Message ID : Unknown error.
     */
    public static String MSG_ID_UNKNOWN_ERROR = "ZZM9501E";
    
    /**
     * Database table column : BLOB column
     */
    public static String DB_COL_BLOB = "ATT_DATA_BLOD";
    
    /**
     * Byte Size Unit : 1KB(kilobyte)
     */
    public static BigDecimal BYTE_KB = new BigDecimal( 1*1024 );
    
    /**
     * Byte Size Unit : 1MB(megabyte)
     */
    public static BigDecimal BYTE_MB = new BigDecimal( 1*1024*1024 );
    
    /**
     * Byte Size Unit : 1GB(gigabyte)
     */
    public static BigDecimal BYTE_GB = new BigDecimal( 1*1024*1024*1024 );

    /**
     * attachment data type code : File
     */
    public static String ATT_DATA_TYPE_CODE_FILE = "FL";

    /**
     * attachment data type code : WEBPAGE(URL)
     */
    public static String ATT_DATA_TYPE_CODE_URL = "WP";

    /**
     * attachment data type code : Therefore
     */
    public static String ATT_DATA_TYPE_CODE_THEREFORE = "TF";

    /**
     * SSM Parameter : global company code
     */
    public static String PRM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * SSM Parameter : attachment data pk
     */
    public static String PRM_ATT_DATA_PK = "attDataPk";

    /**
     * SSM Parameter : attachment data comment
     */
    public static String PRM_ATT_DATA_CMNT = "attDataCmnt";

    /**
     * SSM Parameter : ezBusinessID
     */
    public static String PRM_EZ_BUSINESS_ID = "ezBusinessID";

    /**
     * SSM Parameter : Attachment Data Group
     */
    public static String PRM_ATT_DATA_GRP = "attDataGrpTxt";

    /**
     * SSM Query name : updAttCmnt
     */
    public static String QUERY_UPDATE = "updAttCmnt";

    /**
     * SSM Query name : getAttCmnt
     */
    public static String QUERY_SELECT = "getAttCmnt";

    /**
     * SSM Result name : ATT_DATA_CMNT_CLOD
     */
    public static String RSLT_ATT_DATA_CMNT_CLOD = "ATT_DATA_CMNT_CLOD";

    /**
     * SSM Result name : CHUNK_SIZE
     */
    public static String RSLT_CHUNK_SIZE = "CHUNK_SIZE";

    /**
     * SSM Result name : ATT_DATA_PK
     */
    public static String RSLT_ATT_DATA_PK = "ATT_DATA_PK";
}
