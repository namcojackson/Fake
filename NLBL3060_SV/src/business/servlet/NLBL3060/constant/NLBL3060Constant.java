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
package business.servlet.NLBL3060.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          N/A
 * 2023/04/13   Hitachi         T.Kuroda        Update          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public interface NLBL3060Constant {

    /** Function Button */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    // [0]:Button Name [1]:Event Name
    /** Another Button */
    String[] BTN_INSERT_ROW = {"Insert_Row", "Insert_Row" };

    String[] BTN_DELETE_ROW = {"Delete_Row", "Delete_Row" };

    String[] BTN_RWH_NM = {"Click_RtlWhName", "Click_RtlWhName" };

    String[] BTN_PSN_NM = {"Click_PersonName", "Click_PersonName" };

    String[] BTN_DTL_RWH_NM = {"Click_DtlRtlWhName", "Click_DtlRtlWhName" };

    String[] BTN_DTL_PSN_NM = {"Click_DtlPersonName", "Click_DtlPersonName" };

    String[] BTN_DTL_RWH = {"Btn_DtlRtlWh", "Btn_DtlRtlWh" };

    String[] BTN_DTL_PSN = {"Btn_DtlPerson", "Btn_DtlPerson" };

    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
    String[] BTN_DTL_GRP_NM = {"Btn_DtlGrpNm", "Btn_DtlGrpNm" };
    // END 2023/10/18 Y.Ogura [QC#61793, ADD]

    // START 2023/04/13 T.Kuroda [QC#61166, ADD]
    String[] BTN_CHK_ALL = {"OnClick_CheckAll", "OnClick_CheckAll" };
    String[] BTN_UNCHK_ALL = {"OnClick_UnCheck", "OnClick_UnCheck" };
    String[] BTN_UP_CSV = {"OnClick_Upload", "OnClick_Upload" };
    // END   2023/04/13 T.Kuroda [QC#61166, ADD]

    /** Screen ID */
    String SCREEN_ID = "NLBL3060Scrn00";

    String APPLICATION_ID = "NLBL3060";

    String TABLE_ID_A = "A";

    // Function ID
    String FUNC_REFER = "NLBL3060T010";

    String FUNC_UPDATE = "NLBL3060T020";

    // START 2023/04/13 T.Kuroda [QC#61166, ADD]
    // Function Code
    String FUNCTION_CD_SEARCH = "20";

    String FUNCTION_CD_UPDATE = "40";

    // CSV file extension
    String FILE_EXTENSION_CSV = ".csv";

    // CSV template file name
    String TEMPLATE_FILE_NAME = "UploadTemplate";

    // Check box name
    String CHECK_BOX_NAME = "xxChkBox_A1";
    // END   2023/04/13 T.Kuroda [QC#61166, ADD]

    int MAX_RECORD_COUNT = 200;

    enum MESSAGE_ID {
        NZZM0002I, NMAM0072E, NMAM0180E, NMAM0181E, ZZM9008E
        // START 2023/04/13 T.Kuroda [QC#61166, ADD]
        , ZZM9000E
        // END   2023/04/13 T.Kuroda [QC#61166, ADD]
        ;
    }
}
