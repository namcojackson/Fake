package business.servlet.ZYPL0210.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;


/**
 * @author Administrator
 */
public interface ZYPL0210Constant {

    // --------------------------------------------------------------
    // Biz App ID Type
    // --------------------------------------------------------------
    /** current business application id */
    String UPLD_CSV_BIZ_APP = "ZYPL0210";

    // --------------------------------------------------------------
    // Biz App ID Type
    // --------------------------------------------------------------
    /** business application id for tab screen move*/
    String SOURCE_EVENT_NAME_UPPER_TAB = "OnClickS21BusinessProcessTAB";
    /** business application id for multi screen move*/
    String SOURCE_EVENT_NAME_MULTI_SCREEN = "OnClickMultiScrennButton";


    // --------------------------------------------------------------
    // CONTEXTKEY_BIZ_PROC_NM
    // --------------------------------------------------------------
    /** key of businessprocess info */
    String CONTEXTKEY_PROCESS_INFO = "SelectedS21ProcessInfo";

    // --------------------------------------------------------------
    // Button Control Info
    // --------------------------------------------------------------
    /** Button Control Info(buttion1) */
    String[] BTN_CMN_BTN1 = {"btn1", "", "" };

    /** Button Control Info(buttion2) */
    String[] BTN_CMN_BTN2 = {"btn2", "", "" };

    /** Button Control Info(buttion3) */
    String[] BTN_CMN_BTN3 = {"btn3", "", "" };

    /** Button Control Info(buttion4) */
    String[] BTN_CMN_BTN4 = {"btn4", "", "" };

    /** Button Control Info(buttion5) */
    String[] BTN_CMN_BTN5 = {"btn5", "", "" };

    /** Button Control Info(buttion6) */
    String[] BTN_CMN_BTN6 = {"btn6", "", "" };

    /** Button Control Info(buttion7) */
    String[] BTN_CMN_BTN7 = {"btn7", "", "" };

    /** Button Control Info(buttion8) */
    String[] BTN_CMN_BTN8 = {"btn8", "", "" };

    /** Button Control Info(buttion9) */
    String[] BTN_CMN_BTN9 = {"btn9", "", "" };

    /** Button Control Info(buttion10) */
    String[] BTN_CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    // --------------------------------------------------------------
    // Button On Screen
    // --------------------------------------------------------------
    /** Button Search(Search) */
    String BTN_SEARCH = "Search";

    /** Button Search(Search) */
    String BTN_UPLOAD = "Upload";

    /** Button Search(Search) */
    String BTN_REFRESH = "Refresh";

    // --------------------------------------------------------------
    // Download Template file info.
    // --------------------------------------------------------------
    /** */
    String TMPL_FILE_BASE = "UploadTemplate_(Upload_CSV_ID)";
	//[MOD] FWDEF273 - C.Kim EXCEL Convert
    /** */
//    String TMPL_FILE_EXTENSION = ".csv";
    String TMPL_FILE_EXTENSION = ZYPExcelUtil.EXCEL_EXTENSION_XLSX;
	//[MOD] FWDEF273 - C.Kim EXCEL Convert
	
    /** */
    String TMPL_FILE_VALIABLE = "Upload_CSV_ID";

    /** */
    String TMPL_FILE_NAME = TMPL_FILE_BASE + TMPL_FILE_EXTENSION;

    /** */
    String TMPL_FILE_OUT_DIR = null;

    // --------------------------------------------------------------
    // Download Uploaded file info.
    // --------------------------------------------------------------
    /** */
    String DNLD_UPLD_FILE_BASE = "UploadFile_(Upload_ID)_(Upload_Request_ID)";
	//[MOD] FWDEF273 - C.Kim EXCEL Convert
    /** */
//    String DNLD_UPLD_FILE_EXTENSION = ".csv";
    String DNLD_UPLD_FILE_EXTENSION = ZYPExcelUtil.EXCEL_EXTENSION_XLSX;
    //[MOD] FWDEF273 - C.Kim EXCEL Convert
    /** */
    String DNLD_UPLD_FILE_VALIABLE_UPLD_ID = "Upload_ID";

    /** */
    String DNLD_UPLD_FILE_VALIABLE_RQST_ID = "Upload_Request_ID";

    /** */
    String DNLD_UPLD_FILE_NAME = DNLD_UPLD_FILE_BASE + DNLD_UPLD_FILE_EXTENSION;

    /** */
    String DNLD_UPLD_FILE_OUT_DIR = null;

    // --------------------------------------------------------------
    // Upload file info.
    // --------------------------------------------------------------
    /** */
    String UPLD_FILE_BASE = "UploadTemplate_(Upload_CSV_ID)";

    /** */
    String UPLD_FILE_EXTENSION = ".csv";

    /** */
    String UPLD_FILE_VALIABLE = "Upload_CSV_ID";

    /** */
    String UPLD_FILE_NAME = TMPL_FILE_BASE + TMPL_FILE_EXTENSION;

    /** */
    String UPLD_FILE_OUT_DIR = null;

    // --------------------------------------------------------------
    // Upload Restriction Code
    // --------------------------------------------------------------
    /** restriction of upldCSvID by process name */
    static String UPLD_RST_PROC_NM = "PROC_NM";

    /** restriction of upldCSvID by business application ID */
    static String UPLD_RST_BIZ_APP_ID = "BIZ_APP_ID";
    
    /** */
    String CSV_FILE_EXTENSION = "csv";
    
    /** */
    String TXT_FILE_EXTENSION = "txt";
    //[MOD] FWDEF273 - C.Kim EXCEL Convert
    /** */
    String EXCEL_FILE_EXTENSION = "xlsx";
    //[MOD] FWDEF273 - C.Kim EXCEL Convert
    
    /** */
    int UP_FILE_SIZE_DEF = 16;

    // --------------------------------------------------------------
    // CSV IDs to download number format.
    // --------------------------------------------------------------

    /** Only Pricing Upload */
    public static List<String> CSV_ID_LIST_DOWNLOAD_NUMBER_FORMAT = Arrays.asList("NMA7010001", "NMA7010002", "NMA7010003", "NMA7010004", "NMA7010005", "NMA7010006", "NMA7010007", "NMA7010008", "NMA7020001");
}
