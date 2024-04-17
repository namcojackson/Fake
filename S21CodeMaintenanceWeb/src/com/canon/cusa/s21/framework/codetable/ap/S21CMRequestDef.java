package com.canon.cusa.s21.framework.codetable.ap;

/**
 * 業務処理固有のリクエスト/アトリビュート定数。<br>
 * リクエストはREQ_<i>XX</i>、アトリビュートはATTR_<i>XX</i>とする。<br>
 * @author Administrator
 */
public class S21CMRequestDef {

    /** HTTP Request:EZDMsg系の入力情報のPrefix */
    public static final String REQ_EZMSG = "NE_EZM_";

    /** HTTP Request:EZDMsg系の過去の入力情報のPrefix */
    public static final String REQ_EZMSG_OLD = "NE_EZM_OLD_";

    /** HTTP Request:EZDMsg系のキー情報のPrefix */
    public static final String REQ_EZMSG_KEY = "NE_EZM_KEY_";

    /** HTTP Request:削除チェック情報 */
    public static final String REQ_DEL_INDEX = "deleteIndex_";

    /** HTTP Request:削除チェック情報 */
    public static final String REQ_EDIT_INDEX = "editIndex";

    /** HTTP Request:表示中テーブル */
    public static final String REQ_SHOW_TABLE = "showTable";

    /** HTTP Request:テーブル選択 */
    public static final String REQ_SELECT_TABLE = "selectTable";

    /** HTTP Request:表示中テーブル */
    public static final String REQ_VIEW_TABLE = "viewTable";

    /** HTTP Request:テーブル検索条件 */
    public static final String REQ_SEARCH_TABLE = "searchTable";

    /** HTTP Request:ロングネーム検索 */
    public static final String REQ_SEARCH_LONGN_NAME = "searchLongName";

    /** HTTP Request:編集種別 */
    public static final String REQ_EDIT_TYPE = "editType";

    /** HTTP Request:編集種別 ADD */
    public static final String REQ_EDIT_TYPE_ADD = "ADD";

    /** HTTP Request:編集種別 EDIT */
    public static final String REQ_EDIT_TYPE_EDIT = "EDIT";

    /** HTTP Request:表示件数 */
    public static final String REQ_REC_COUNT = "recordCount";

    /** HTTP Request:条件検索での表示 */
    public static final String REQ_COND_SEARCH = "condSearch";

    /** HTTP Request:現在表示中ページ */
    public static final String REQ_VIEW_PAGE = "viewPage";

    /** HTTP Request:遷移先ページ */
    public static final String REQ_NEXT_PAGE = "nextPage";

    /** HTTP Request:Uploadファイル */
    public static final String REQ_UPLOAD_FILE = "uploadFile";

}
