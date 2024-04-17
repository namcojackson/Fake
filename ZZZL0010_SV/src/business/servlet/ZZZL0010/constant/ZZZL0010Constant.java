package business.servlet.ZZZL0010.constant;

import parts.common.EZDApplicationContextConst;

/**
 * ZZZL0010定数クラス。
 * @author q02673
 */
public interface ZZZL0010Constant extends EZDApplicationContextConst {

    // ----------------------------------------------------------------
    // EZDApplicationContextConst未定義のEZパラメータ名

    /** 画面ID */
    static final String PAGE_ID = "pageID";

    /** 業務ID */
    static final String BUSINESS_ID = "ezBusinessID";

    /** 開始業務ID */
    static final String START_BUSINESS_ID = "ezd.startBusinessId";

    /** SUBMITフィールド名 */
    static final String S21_SUBMITED_FIELD_NM = "S21SubmitedFieldNm";

    // ----------------------------------------------------------------
    // リクエストパラメータ名

    /** リクエストパラメータ：グローバルカンパニーコード */
    static final String PARAM_GLOBAL_COMPANY_CODE = "glblCmpyCd";

    /** リクエストパラメータ：EAR間引継ぎ情報 */
    static final String PARAM_TRANSFER_OBJECT = "trnsfObj";

//    /** リクエストパラメータ：メールリンクパラメータ */
//    static final String PARAM_MAIL_ID = "mailId";
//
//    /** リクエストパラメータ：メールリンクパラメータ */
//    static final String PARAM_MAIL_TOKEN = "mailToken";

    // ----------------------------------------------------------------
    // セッションキー名（業務公開）

    /** セッション属性キー：リダイレクト先EARのアプリケーションID */
    static final String ATTRB_FORWARD_WEB_APP_ID = "ZZZL0010.FORWARD_WEB_APP_ID";

    /** セッション属性キー：引継用パラメータマップ */
    static final String ATTRB_REQUEST_PARAM_MAP = "ZZZL0010.REQUEST_PARAM_MAP";

    // ----------------------------------------------------------------
    // セッションキー名（Router内部利用）

    /** リダイレクトURL */
    static final String ATTRB_REDIRECT_URL = "redirectUrl";

    /** リクエストパラメータ：引継情報 */
    static final String ATTRB_TRANSFER_INFO = "transfInfo";

    // ----------------------------------------------------------------
    // イベント名

    /** イベント名：jumpToInternalBusiness */
    static final String EVENT_JUMP_INTERNAL = "jumpToInternalBusiness";

    /** イベント名：jumpToExternalBusiness */
    static final String EVENT_JUMP_EXTERNAL = "jumpToExternalBusiness";

    /** イベント名：Logout */
    static final String EVENT_LOGOUT = "Logout";

    // ----------------------------------------------------------------
    // 動作モード

    /** 動作モード：production */
    static final String RUN_MODE_PRODUCTION = "production";

    /** 動作モード：test */
    static final String RUN_MODE_TEST = "test";

    // ----------------------------------------------------------------
    // 認証方法方法

    /** 認証方法方法：FORM認証 */
    static final String AUTH_METHOD_FORM = "form";

    /** 認証方法方法：SSO認証 */
    static final String AUTH_METHOD_SSO = "sso";

    // ----------------------------------------------------------------
    // ログアウト方法

    /** ログアウト方法：通常 */
    static final String EVENT_LOGOUT_TYPE_NORMAL = "normal";

    /** ログアウト方法：SSOログアウト */
    static final String EVENT_LOGOUT_TYPE_SSO = "sso";

    // ----------------------------------------------------------------
    // Router制御情報

    /** Router制御情報：DataBase */
    static final String ROUTE_INFO_DATASOURCE_DATABASE = "database";

    /** Router制御情報：XML */
    static final String ROUTE_INFO_DATASOURCE_XML = "xml";

    // ----------------------------------------------------------------
    // その他

    /** 有効 */
    static final String ENABLE = "enable";

    /** 無効 */
    static final String DISABLE = "disable";

    // Multi screen start Shinobu Tsunaki
    /** Javascript Winow.name */
    static final String JS_WINDOW_NAME = "jswindowname";
    // Multi screen end Shinobu Tsunaki

    // ----------------------------------------------------------------
    // プロパティファイルキー

    /** プロパティキー：デフォルト表示業務ID */
    static final String PROPKEY_RUN_MODE = "ZZZL0010.runmode";

    /** プロパティキー：HTTPリダイレクト最大URL長 */
    static final String PROPKEY_REDIRECT_URL_MAX_LENGTH = "ZZZL0010.redirect.url_max_length";

    /** プロパティキー：経路情報データソースタイプ */
    static final String PROPKEY_ROUTE_INFO_DATASOURCE = "ZZZL0010.route_info_datasource";

    /** プロパティキー：認証方法 */
    static final String PROPKEY_AUTH_METHOD = "ZZZL0010.authentication_method";

    /** プロパティキー：FORM認証画面業務ID */
    static final String PROPKEY_FORM_AUTH_BIZID = "ZZZL0010.form_authentication.business_id";

    /** プロパティキー：デフォルト表示業務ID */
    static final String PROPKEY_DEFAULT_BIZID = "ZZZL0010.default_business_id";

    /** プロパティキー：ログアウト処理実行イベント名 */
    static final String PROPKEY_LOGOUT_EVENT_NAMES = "ZZZL0010.logout.event_names";

    /** プロパティキー：ログアウト方法 */
    static final String PROPKEY_LOGOUT_METHOD = "ZZZL0010.logout.method";

    /** プロパティキー：SSOログアウトWEB_APP_ID */
    static final String PROPKEY_LOGOUT_WEB_APP_ID = "ZZZL0010.logout.web_app_id";

    /** プロパティキー：アプリケーションドメインID */
    static final String PROPKEY_APP_DOMAIN_ID = "ZZZL0010.app_domain_id";

    /** プロパティキー：初回遷移時遷移先EAR判定 */
    static final String PROPKEY_APP_INIT_TRANS_ROUTING = "ZZZL0010.initial_transition_routing";
}
