/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLBL3060.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2023/04/13   Hitachi         T.Kuroda        Update          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public interface NLBL3060Constant {

    enum MESSAGE_ID {
        NZZM0002I, NZZM0003E, NMAM0007I, NMAM0098I, NMAM0009E, NMAM0010E, NMAM0050E, NMAM0072E, NMAM0177E, 
        NMAM8054E, NLAM8140E, NWDM0099E, NWDM0134E, NWDM0223E, NLBM1295E, NFCM0063E, NFCM0504E, NFCM0764E, 
        NFAM0075E
        // START 2023/04/13 T.Kuroda [QC#61166, ADD]
        , NZZM0001W
        , ZYEM0004E
        , NLBM1379E
        , NLBM1380E
        , NMAM8191E
        // END   2023/04/13 T.Kuroda [QC#61166, ADD]
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        , NLBM1390E
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        ;
    }

    String LAST_DATE = "99991231";

    BigDecimal CONV_PARCENT = new BigDecimal(100);

    // START 2023/04/13 T.Kuroda [QC#61166, ADD]
    // status code : 1000
    int CSV_READ_STATUS_CODE_1000 = 1000;

    // status code : 2000 
    int CSV_READ_STATUS_CODE_2000 = 2000;

    // Max Fetch Size
    int MAX_FETCH_SIZE = 1000;

    // CSV Download file name
    String CSV_DL_FILE_NAME = "WarehousePermissions";

    // CSV Template file name
    String CSV_TEMP_FILE_NAME = "UploadTemplate";

    // CSV file extension
    String CSV_FILE_EXTENSION = ".csv";

    // Upload Data Format
    String UPLOAD_DATA_FORMAT = "CSV";

    // Limit Down load RowNumber
    int LIMIT_DL_ROWNUM = 65001;

    // CSV Template file Header
    String[] CSV_HDR_DOWNLOAD = new String[] {
         // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            "WH Type"
            ,"Group Name"
         // END 2023/10/18 Y.Ogura [QC#61793, ADD]
            , "Retail Warehouse Code"
            , "Retail Warehouse Name"
            , "Permitted User Code"
            , "Permitted User Name"
            , "Eff From Date"
            , "Eff Thru Date"
    };
    // END   2023/04/13 T.Kuroda [QC#61166, ADD]

    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
    // Upload 
    String UPLOAD_NAME = "upload";

    // download 
    String DOWNLOAD_NAME = "download";
    // END 2023/10/18 Y.Ogura [QC#61793, ADD]

}
