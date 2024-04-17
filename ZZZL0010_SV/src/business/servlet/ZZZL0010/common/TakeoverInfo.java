package business.servlet.ZZZL0010.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.router.S21TransferObjectSerializer;
import com.canon.cusa.s21.framework.online.router.S21URLEncoder;

import business.servlet.ZZZL0010.constant.ZZZL0010Constant;

/**
 * EAR間遷移時に連携する引継情報のコンテナクラス。
 * @author q02673
 */
public class TakeoverInfo implements Serializable {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** 業務引継情報 */
    private Serializable biz;

    /** Session情報 */
    private Map<String, Serializable> session;

    /** Parameter情報 */
    private transient Map<String, Serializable> parameter;

    /** パラメータ：randomKey */
    private transient String randomKey;

    /** パラメータ：開始業務ID */
    private transient String startBusinessId;

    /** パラメータ：業務ID */
    private transient String ezBusinessID;

    /** パラメータ：ウィンドウ名 */
    private transient String ezWindowName;

    /** パラメータ：ログイン時刻 */
    private transient String ezLoginTime;

    /** パラメータ：ログインタイムゾーン */
    private transient String ezLoginTimeZone;

    /** パラメータ：遷移元画面ID */
    private transient String pageID;

    // Multi screen start Shinobu Tsunaki
    /** パラメータ：表示中画面名 */
    private transient String windowNames;

    // Multi screen end Shinobu Tsunaki

    // /** パラメータ：ユーザID */
    // private transient String ezUserID;
    //
    // /** パラメータ：ユーザ名 */
    // private transient String ezUserName;
    //
    // /** パラメータ：パスワード */
    // private transient String ezPassword;

    /**
     * 自己インスタンスをシリアライズ後、特殊Base64エンコードした文字列を返します。
     * @return 特殊Base64エンコード後の文字列
     */
    public String encode() {
        return S21TransferObjectSerializer.encode(this, true);
    }

    /**
     * 指定された特殊Base64エンコード後の文字列からインスタンスを生成して返します。
     * @param base64String 特殊Base64エンコード後の文字列
     * @return デシリアライズされたオブジェクト
     */
    public static TakeoverInfo decode(String base64String) {
        Serializable result = S21TransferObjectSerializer.decode(base64String, true);
        if (result == null) {
            return new TakeoverInfo();
        } else {
            return (TakeoverInfo) result;
        }
    }

    /**
     * 指定された特殊Base64エンコード後の文字列からインスタンスを生成して返します。
     * @param base64String 特殊Base64エンコード後の文字列
     * @param parameter Parameter情報
     * @return デシリアライズされたオブジェクト
     */
    public static TakeoverInfo decode(String base64String, Map<String, Serializable> parameter) {
        Serializable result = S21TransferObjectSerializer.decode(base64String, true);
        TakeoverInfo info = null;
        if (result == null) {
            info = new TakeoverInfo();
        } else {
            info = (TakeoverInfo) result;
        }

        info.parameter = parameter;

        return info;
    }

    /**
     * GETリクエスト時のクエリストリングを生成します。
     * @return &から開始するクエリストリング
     */
    public String getSimpleFwQueryString() {
        StringBuilder params = new StringBuilder(256);
        // NOTE:履歴キャッシュ対策
        params.append("?").append(getRandomKey());

        // NOTE:開始業務IDがあれば連携
        if ((getStartBusinessId() != null) && (getStartBusinessId().length() > 0)) {
            params.append("&").append(ZZZL0010Constant.START_BUSINESS_ID).append("=").append(S21URLEncoder.encode(getStartBusinessId()));
        }

        return params.toString();
    }

    /**
     * GETリクエスト時のクエリストリングを生成します。
     * @return &から開始するクエリストリング
     */
    // Multi screen start Shinobu Tsunaki
    // public String getQueryString() {
    public String getQueryString(EZDApplicationContext ctx) {
        // Multi screen end Shinobu Tsunaki
        StringBuilder params = new StringBuilder(256);
        params.append(getSimpleFwQueryString());
        params.append("&").append(ZZZL0010Constant.BUSINESS_ID).append("=").append(S21URLEncoder.encode(getEzBusinessID()));
        params.append("&").append(ZZZL0010Constant.WINDOWNAME).append("=").append(S21URLEncoder.encode(getEzWindowName()));

        // Multi screen start Shinobu Tsunaki
        params.append("&").append(ZZZL0010Constant.JS_WINDOW_NAME).append("=").append(S21URLEncoder.encode(getWindowNames()));
        // Multi screen end Shinobu Tsunaki

        params.append("&").append(ZZZL0010Constant.LOGINTIME).append("=").append(S21URLEncoder.encode(getEzLoginTime()));
        params.append("&").append(ZZZL0010Constant.LOGINTIMEZONE).append("=").append(S21URLEncoder.encode(getEzLoginTimeZone()));
        if (getPageID() != null && getPageID().length() > 0) {
            params.append("&").append(ZZZL0010Constant.PAGE_ID).append("=").append(S21URLEncoder.encode(getPageID()));
        }

        // 追加パラメータ
        for (String key : getParameter().keySet()) {
            params.append("&").append(key).append("=").append(S21URLEncoder.encode((String) getParameter().get(key)));
        }

        return params.toString();
    }

    /**
     * GETリクエスト時のクエリストリングを生成します。
     * @return ?から開始するクエリストリング
     */
    public String getOptionalParameterString() {
        StringBuilder params = new StringBuilder();
        boolean first = true;
        for (String key : getParameter().keySet()) {
            if (first) {
                first = false;
                params.append("?").append(key).append("=");
            } else {
                params.append("&").append(key).append("=");
            }
            params.append(S21URLEncoder.encode((String) getParameter().get(key)));
        }

        return params.toString();
    }

    /**
     * Parameter情報を返します。
     * @return Parameter情報が格納されたMap
     */
    public Map<String, Serializable> getParameter() {
        if (parameter == null) {
            return Collections.EMPTY_MAP;
        } else {
            return parameter;
        }
    }

    /**
     * Parameter情報を設定します。
     * @param key Parameterのキー名
     * @param value Parameterの値
     */
    public void setParameter(String key, Serializable value) {
        if (parameter == null) {
            parameter = new HashMap<String, Serializable>();
        }

        parameter.put(key, value);
    }

    /**
     * Session情報を返します。
     * @return Session情報が格納されたMap
     */
    public Map<String, Serializable> getSession() {
        if (session == null) {
            return Collections.EMPTY_MAP;
        } else {
            return session;
        }
    }

    /**
     * Session情報を設定します。
     * @param key Sessionのキー名
     * @param value Sessionの値
     */
    public void setSession(String key, Serializable value) {
        if (session == null) {
            session = new HashMap<String, Serializable>();
        }

        session.put(key, value);
    }

    /**
     * 業務引継情報を返します。
     * @return 業務引継情報
     */
    public Serializable getBusinesObject() {
        return biz;
    }

    /**
     * 業務引継情報を設定します。
     * @param businesArgs 業務引継情報
     */
    public void setBusinesArgs(Serializable businesArgs) {
        this.biz = businesArgs;
    }

    /**
     * 業務IDを返します。
     * @return 業務ID
     */
    public String getEzBusinessID() {
        return ezBusinessID;
    }

    /**
     * 業務IDを設定します。
     * @param ezBusinessID 設定する業務ID
     */
    public void setEzBusinessID(String ezBusinessID) {
        this.ezBusinessID = ezBusinessID;
    }

    /**
     * ログイン時刻を返します。
     * @return ログイン時刻
     */
    public String getEzLoginTime() {
        return ezLoginTime;
    }

    /**
     * ログイン時刻を設定します。
     * @param ezLoginTime 設定するログイン時刻
     */
    public void setEzLoginTime(String ezLoginTime) {
        this.ezLoginTime = ezLoginTime;
    }

    /**
     * ログインタイムゾーンを返します。
     * @return ログインタイムゾーン
     */
    public String getEzLoginTimeZone() {
        return ezLoginTimeZone;
    }

    /**
     * ログインタイムゾーンを設定します。
     * @param ezLoginTimeZone 設定するログインタイムゾーン
     */
    public void setEzLoginTimeZone(String ezLoginTimeZone) {
        this.ezLoginTimeZone = ezLoginTimeZone;
    }

    /**
     * ユーザIDを返します。
     * @return ユーザID
     */
    public String getEzUserID() {
        return (String) getSession().get(ZZZL0010Constant.USER_ID);
    }

    /**
     * ユーザIDを設定します。
     * @param ezUserID 設定するユーザID
     */
    public void setEzUserID(String ezUserID) {
        setSession(ZZZL0010Constant.USER_ID, ezUserID);
    }

    /**
     * ユーザ名を返します。
     * @return ezUserName ユーザ名
     */
    public String getEzUserName() {
        return (String) getSession().get(ZZZL0010Constant.USER_NAME);
    }

    /**
     * ユーザ名を設定します。
     * @param ezUserName 設定するユーザ名
     */
    public void setEzUserName(String ezUserName) {
        setSession(ZZZL0010Constant.USER_NAME, ezUserName);
    }

    /**
     * パスワードを設定します。
     * @return パスワード
     */
    public String getEzPassword() {
        return (String) getSession().get(ZZZL0010Constant.PASSWORD);
    }

    /**
     * パスワードを設定します。
     * @param ezPassword 設定するパスワード
     */
    public void setEzPassword(String ezPassword) {
        setSession(ZZZL0010Constant.PASSWORD, ezPassword);
    }

    /**
     * ウィンドウ名を返します。
     * @return ウィンドウ名
     */
    public String getEzWindowName() {
        return ezWindowName;
    }

    /**
     * ウィンドウ名を設定します。
     * @param ezWindowName 設定するウィンドウ名
     */
    public void setEzWindowName(String ezWindowName) {
        this.ezWindowName = ezWindowName;
    }

    /**
     * randomKeyを返します。
     * @return randomKey
     */
    public String getRandomKey() {
        return randomKey;
    }

    /**
     * randomKeyを設定します。
     * @param randomKey 設定するrandomKey
     */
    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    /**
     * 開始業務IDを返します。
     * @return 開始業務ID
     */
    public String getStartBusinessId() {
        return startBusinessId;
    }

    /**
     * 開始業務IDを設定します。
     * @param startBusinessId 設定する開始業務ID
     */
    public void setStartBusinessId(String startBusinessId) {
        this.startBusinessId = startBusinessId;
    }

    /**
     * 遷移元画面IDを返します。
     * @return 遷移元画面ID
     */
    public String getPageID() {
        return pageID;
    }

    /**
     * 遷移元画面IDを設定します。
     * @param pageId 設定する遷移元画面ID
     */
    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    // Multi screen start Shinobu Tsunaki
    /**
     * 表示画面名を返します。
     * @return 表示画面名
     */
    public String getWindowNames() {
        return windowNames;
    }

    /**
     * 表示画面名を設定します。
     * @param pageId 設定する表示画面名
     */
    public void setWindowNames(String windowNames) {
        this.windowNames = windowNames;
    }

    // Multi screen end Shinobu Tsunaki

    /**
     * グローバルカンパニーコードを返します
     * @return グローバルカンパニーコード
     */
    public String getGlobalCompanyCode() {
        Serializable gcc = getParameter().get(ZZZL0010Constant.PARAM_GLOBAL_COMPANY_CODE);
        if (gcc == null) {
            return null;
        } else if (gcc instanceof Object[] && ((Object[]) gcc).length > 0) {
            return (String) ((Object[]) gcc)[0];
        } else {
            return null;
        }
    }

    /**
     * グローバルカンパニーコードを設定します。
     * @param globalCompanyCode 設定するグローバルカンパニーコード
     */
    public void setGlobalCompanyCode(String globalCompanyCode) {
        if (globalCompanyCode != null) {
            setParameter(ZZZL0010Constant.PARAM_GLOBAL_COMPANY_CODE, globalCompanyCode);
        }
    }
}
