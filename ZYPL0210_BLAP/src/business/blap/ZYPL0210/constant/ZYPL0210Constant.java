package business.blap.ZYPL0210.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
public interface ZYPL0210Constant {
    // --------------------------------------------------------------
    // Screen Name
    // --------------------------------------------------------------
    /** Screen Name. ZYPL0210_INIT */
    String SCRN_INIT = "ZYPL0210_INIT";

    /** Screen Name. ZYPL0210_Search */
    String SCRN_SEARCH = "ZYPL0210Scrn00_Search";

    /** Screen Name. ZYPL0210_TemplateDownload */
    String SCRN_TEMPLATEDOWNLOAD = "ZYPL0210Scrn00_TemplateDownload";

    /** Screen Name. ZYPL0210Scrn00_Upload */
    String SCRN_UPLOAD = "ZYPL0210Scrn00_Upload";

    /** Screen Name. ZYPL0210Scrn00_PageNext */
    String SCRN_PAGENEXT = "ZYPL0210Scrn00_PageNext";

    /** Screen Name. ZYPL0210Scrn00_PagePrev */
    String SCRN_PAGEPREV = "ZYPL0210Scrn00_PagePrev";

    /** Screen Name. ZYPL0210Scrn00_Refresh */
    String SCRN_REFRESH = "ZYPL0210Scrn00_Refresh";

    /** Screen Name. ZYPL0210Scrn00_Download_Row */
    String SCRN_DOWNLOAD_ROW = "ZYPL0210Scrn00_Download_Row";

    /** Screen Name. ZYPL0210Scrn00_Download_Error_Row */
    String SCRN_DOWNLOAD_ERROR_ROW = "ZYPL0210Scrn00_Download_Error_Row";

    // --------------------------------------------------------------
    // Download Template file info.
    // --------------------------------------------------------------
    /** */
    String TMPL_FILE_BASE = "UploadTemplate_(Upload_CSV_ID)";

    /** */
    String TMPL_FILE_EXTENSION = ".csv";

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

    /** */
    String DNLD_UPLD_FILE_EXTENSION = ".csv";

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
    // Message Code
    // --------------------------------------------------------------
    /** search normal end */
    String MSG_CD_SEARCH_NORMAL_END = "ZZM8002I";

    /** upload normal end */
    String MSG_CD_UPLOAD_NORMAL_END = "ZZM8100I";

    /** Not found record in UPLD_CSV_RQST */
    String MSG_CD_NOT_FOUND_UPLD_CSV_RQST = "ZYPM0141W";
    
    // --------------------------------------------------------------
    // Control CSV Upload
    // --------------------------------------------------------------
    /** Write ART10 with format error */
    List<String> CSV_ID_LIST_UPLOAD_WITH_FORMAT_ERROR = Arrays.asList("NMA7010001", "NMA7010002", "NMA7010003", "NMA7010004", "NMA7010005", "NMA7010006", "NMA7010007", "NMA7010008", "NMA7020001");

    /** UPLOAD EXCEL WITH COMMENT COLUMN */
    List<String> CSV_ID_LIST_UPLOAD_TO_N_FLG = Arrays.asList("NMA7010001", "NMA7010002", "NMA7010003", "NMA7010004", "NMA7010005", "NMA7010006", "NMA7010007", "NMA7010008", "NMA7020001");
}
