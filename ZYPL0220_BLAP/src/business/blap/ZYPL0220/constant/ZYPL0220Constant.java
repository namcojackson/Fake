package business.blap.ZYPL0220.constant;

/**
 * @author Administrator
 */
public interface ZYPL0220Constant {
    // --------------------------------------------------------------
    // Screen Name
    // --------------------------------------------------------------
    /** Screen Name. ZYPL0220_INIT */
    String SCRN_INIT = "ZYPL0220_INIT";

    /** Screen Name. ZYPL0211_Search */
    String SCRN_SEARCH = "ZYPL0220Scrn00_Search";

    /** Screen Name. ZYPL0220Scrn00_LoadNames */
    String SCRN_LOAD_NAMES = "ZYPL0220Scrn00_LoadNames";

    /** Screen Name. ZYPL0220Scrn00_Add */
    String SCRN_ADD = "ZYPL0220Scrn00_Add";

    /** Screen Name. ZYPL0220Scrn00_Update */
    String SCRN_UPDATE = "ZYPL0220Scrn00_Update";

    // --------------------------------------------------------------
    // Message Code
    // --------------------------------------------------------------
    /** 指定されたUploadCSVIDがUPLD_CSV_MSTRに登録されていない */
    String MSG_CD_NOT_FOUND_UPLD_CSV_MSTR = "ZYPM0101W";

    /**
     * Data of the record was updated by other process. Would you
     * clear screen data and search UPLD_CSV_MSTR again.
     */
    String MSG_CD_NOT_SAME_TIMESTAMP = "ZYPM0103E";

    /** 指定されたFileIDのEZDFMsgクラスが見つからない */
    String MSG_CD_NOT_FOUND_FMSG_UPLD = "ZYPM0104E";
    
    /** 指定されたFileIDのEZDFMsgクラスから項目列フィールドが見つからない */
    String MSG_CD_FAILED_REFRECT_FMSG = "ZYPM0105E";

    /** 指定されたTableIDのEZDFMsgクラスが見つからない */
    String MSG_CD_NOT_FOUND_DNLD_FMSG = "ZYPM0106E";

    /** 指定されたTableIDのEZDTMsgクラスが見つからない */
    String MSG_CD_NOT_FOUND_TMSG = "ZYPM0107E";

    /** UPLD_CSV_MSTR重複エラー  */
    String MSG_CD_ALREADY_EXIST_UPLD_CSV_MSTR = "ZYPM0108E";

    /** UPLD_CSV_HDR重複エラー */
    String MSG_CD_ALREADY_EXIST_UPLD_CSV_HDR = "ZYPM0109E";

    /** UPLD_CSV_RST_PROC重複エラー */
    String MSG_CD_ALREADY_EXIST_UPLD_CSV_RST_PROC = "ZYPM0110E";
    
    /** UPLD_CSV_RST_BIZ_APP_ID重複エラー  */
    String MSG_CD_ALREADY_EXIST_UPLD_CSV_RST_BIZ_APP_ID = "ZYPM0111E";

    /** FAILED UPDATE TO UPLD_CSV_HDR */
    String MSG_CD_NOT_FOUND_UPLD_CSV_HDR = "ZYPM0115E";

    /** FAILED UPDATE TO UPLD_CSV_RST_PROC */
    String MSG_CD_NOT_FOUND_UPLD_CSV_RST_PROC = "ZYPM0116E";

    /** FAILED UPDATE TO UPLD_CSV_RST_BIZ_APP_ID */
    String MSG_CD_NOT_FOUND_UPLD_CSV_RST_BIZ_APP_ID ="ZYPM0117E";



    // --------------------------------------------------------------
    // SQL ID
    // --------------------------------------------------------------
    /** UPLD_CSV_HDRテーブルからヘッダ項目を取得するためのSQLID */
    String SQL_ID_UPLD_CSV_HDR_SET_UPLDCSVID = "050";

    /** UPLD_CSV_HDRテーブルからヘッダ項目を取得するためのSQLID */
    String SQL_ID_GET_PROCESS_NAME = "052";

    /** UPLD_CSV_HDRテーブルからヘッダ項目を取得するためのSQLID */
    String SQL_ID_GET_BIZ_APP_ID = "052";

    // --------------------------------------------------------------
    // replace string
    // --------------------------------------------------------------
    /** upldCsvFileClsNm に設定する文字列の雛型 */
    String UPLD_CSV_FILE_CLS_NM_TEMP = "business.file.FILE_IDFMsg";

    /** upldCsvFileClsNm に設定する文字列の可変文字 */
    String UPLD_CSV_FILE_CLS_NM_ARG = "FILE_ID";

    /** dnldCsvFileClsNm に設定する文字列の雛型 */
    String DNLD_CSV_FILE_CLS_NM_TEMP = "business.db.TEMP_IDFMsg";

    /** dnldCsvFileClsNm に設定する文字列の可変文字 */
    String DNLD_CSV_FILE_CLS_NM_ARG = "TEMP_ID";

    /** upldCsvTempTblClsNm に設定する文字列の雛型 */
    String DNLD_CSV_TEMP_TBL_CLS_NM_TEMP = "business.db.TEMP_IDTMsg";

    /** upldCsvTempTblClsNm に設定する文字列の可変文字 */
    String DNLD_CSV_TEMP_TBL_CLS_NM_ARG = "TEMP_ID";
}
