package com.canon.cusa.s21.framework.codetable.fw;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * コードメンテナンスFW設定用propertiesファイルの管理を行う。<br>
 * @author Administrator
 */
public final class S21NEConfig {

    /** true */
    private static final String DEF_TRUE = "true";

    /** S21NEConfigのインスタンス */
    private static S21NEConfig instance;

    /** プロパティファイルの内容を保持する */
    private HashMap<String, String> map = new HashMap<String, String>();

    /** プロパティファイル */
    private static final String PROP_FILE = "codemaintenancefw";

    /** プロパティファイル */
    private static final String PROP_EZD_SYS_FILE = "EZDSystem";

    /** キー:共通部 */
    private static final String PROP_PREFIX_DEF = "codemaintenance.";

    /** キー:クラス定義 */
    private static final String PROP_CLASS_DEF = PROP_PREFIX_DEF + "class.";

    /** キー:画面定義 */
    private static final String PROP_SCREEN_DEF = PROP_PREFIX_DEF + "screen.";

    /** キー:認証定義 */
    private static final String PROP_AUTH_DEF = PROP_PREFIX_DEF + "auth.";

    /** キー:認証クラス定義 */
    private static final String PROP_AUTH_CLASS_DEF = PROP_AUTH_DEF + "class";

    /** キー:画面定義 */
    private static final String PROP_DISP_DEF = PROP_PREFIX_DEF + "disp.";

    /** キー:画面最大表示定義 */
    private static final String PROP_DISP_LINE_DEF = PROP_DISP_DEF + "line.";

    /** キー:アプリケーション動作定義 */
    private static final String PROP_AP_DEF = PROP_PREFIX_DEF + "ap.";

    /** キー:課金情報 */
    private static final String PROP_AP_ACC_INFO_DEF = PROP_AP_DEF + "accountinfo.";

    /** デフォルト最大表示 */
    private static final int DEF_MAX_LINE = 100;

    /** EZDテンポラリディレクトリ */
    private static final String PROP_EZD_UP_DOWN_DIR = "up_down_file_dir";
    
    /** Key : Application name for Security Cache*/
    private static final String PROP_APP_NM_KEY = "codemaintenance.class.cm_manager.clearSecurityCache.appname";

    // staticイニシャライザ
    static {

        // プロパティファイル読み込み
        ResourceBundle rb = ResourceBundle.getBundle(PROP_FILE);

        if (rb == null) {
            throw new ExceptionInInitializerError();
        }

        // インスタンス作成
        instance = new S21NEConfig();

        Enumeration<String> keys = rb.getKeys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            instance.map.put(key, rb.getString(key));
        }

        rb = ResourceBundle.getBundle(PROP_EZD_SYS_FILE);
        instance.map.put(PROP_EZD_UP_DOWN_DIR, rb.getString(PROP_EZD_UP_DOWN_DIR));
    }

    /**
     * コンストラクタ。<br>
     * シングルトンクラスのためprivate。<br>
     */
    private S21NEConfig() {
    }

    /**
     * S21NEConfigのインスタンス取得。<br>
     * @return S21NEConfigのインスタンス
     */
    public static S21NEConfig getInstance() {
        return instance;
    }

    /**
     * クラス定義の取得。<br>
     * @param screen 画面名
     * @param reqID リクエストID
     * @param type 種別
     * @return 設定値
     */
    public String getClassName(String screen, String reqID, String type) {
        String key = appender(PROP_CLASS_DEF, screen, reqID, type);
        return map.get(key);
    }

    /**
     * クラス定義の取得。<br>
     * @param opt 画面名
     * @return 設定値
     */
    public String getClassName(String opt) {
        String key = appender(PROP_CLASS_DEF, opt);
        return map.get(key);
    }

    /**
     * 遷移先画面情報の取得。<br>
     * @param screen 画面名
     * @param reqID リクエストID
     * @param status 処理結果ステータス
     * @return 設定値
     */
    public String getNextScreen(String screen, String reqID, String status) {
        String key = appender(PROP_SCREEN_DEF, screen, reqID, status);
        return map.get(key);
    }

    /**
     * 課金情報書き込みフラグの取得。<br>
     * @param screen 画面名
     * @return 課金情報書き込みフラグ(省略時はtrueとする)
     */
    public boolean isWriteAccount(String screen) {
        String key = appender(PROP_AP_ACC_INFO_DEF, screen);
        String val = map.get(key);
        return (val == null || val.equalsIgnoreCase(DEF_TRUE));
    }

    /**
     * 画面表示最大レコード数の取得。<br>
     * 未設定、不正な設定値の場合はデフォルト値を返す。<br>
     * @param screen 画面名
     * @return 設定値(省略時はデフォルト値とする)
     */
    public int getDispLine(String screen) {
        String key = appender(PROP_DISP_LINE_DEF, screen);
        String val = map.get(key);
        if (val == null) {
            return DEF_MAX_LINE;
        }

        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException  e) {
            return DEF_MAX_LINE;
        }
    }

    /**
     * 画面オプション情報の取得。<br>
     * 結果はStringを戻す。<br>
     * 未設定、不正な設定値の場合はデフォルト値を返す。<br>
     * @param screen 画面名
     * @param option オプション
     * @param def デフォルト値
     * @return 設定値(省略時はデフォルト値とする)
     */
    public String getDispOption(String screen, String option, String def) {
        String key = appender(PROP_DISP_DEF, screen, option);
        String val = map.get(key);
        if (val == null) {
            return def;
        }

        return val;
    }

    /**
     * 画面オプション情報の取得。<br>
     * 結果はbooleanを戻す。<br>
     * 未設定、不正な設定値の場合はデフォルト値を返す。<br>
     * @param screen 画面名
     * @param option オプション
     * @param def デフォルト値
     * @return 設定値(省略時はデフォルト値とする)
     */
    public boolean isDispOption(String screen, String option, boolean def) {
        String key = appender(PROP_DISP_DEF, screen, option);
        String val = map.get(key);
        if (val == null) {
            return def;
        }

        return (val.equalsIgnoreCase(DEF_TRUE));
    }

    /**
     * 画面オプション情報の取得。<br>
     * 結果はbooleanを戻す。<br>
     * 未設定、不正な設定値の場合はデフォルト値を返す。<br>
     * @param screen 画面名
     * @param option オプション
     * @param def デフォルト値
     * @return 設定値(省略時はデフォルト値とする)
     */
    public long getDispOptionLong(String screen, String option, long def) {
        String key = appender(PROP_DISP_DEF, screen, option);
        String val = map.get(key);
        if (val == null) {
            return def;
        }

        try {
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    /**
     * テンポラリディレクトリ取得。<br>
     * @return テンポラリディレクトリ
     */
    public String getTmpDir() {
        return map.get(PROP_EZD_UP_DOWN_DIR);
    }

    /**
     * プロパティファイルのキー文字列を作成する。<br>
     * <code>str1 + str2</code>
     * @param str1 文字列1
     * @param str2 文字列2
     * @return キー文字列
     */
    private String appender(String str1, String str2) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
        return sb.toString();
    }

    /**
     * プロパティファイルのキー文字列を作成する。<br>
     * <code>str1 + str2 + "." + str3</code>
     * @param str1 文字列1
     * @param str2 文字列2
     * @param str3 文字列3
     * @return キー文字列
     */
    private String appender(String str1, String str2, String str3) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2).append(".").append(str3);
        return sb.toString();
    }

    /**
     * プロパティファイルのキー文字列を作成する。<br>
     * <code>str1 + str2 + "." + str3 + "." + str4</code>
     * @param str1 文字列1
     * @param str2 文字列2
     * @param str3 文字列3
     * @param str4 文字列4
     * @return キー文字列
     */
    private String appender(String str1, String str2, String str3, String str4) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2).append(".").append(str3).append(".").append(str4);
        return sb.toString();
    }
    
    /**
     * Get Application name for Security Cache.<br>
     * @return application name
     */
    public String getAppName() {
        return map.get(PROP_APP_NM_KEY);
    }
}
