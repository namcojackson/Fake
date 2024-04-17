package com.canon.cusa.s21.framework.generictable.fw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDAccountInfoWriter;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;

/**
 * リクエスト情報を保持するValueObject。<br>
 * 共通的な画面情報を保持する。<br>
 * 特別な理由がなければ業務処理の個別ValueObjectは本クラスを継承する。<br>
 * @author Administrator
 */
public class S21NEContainerDataBean implements S21NEDataBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** リクエストID */
    private String reqID;

    /** 画面ID */
    private String screenID;

    /** 画面画面名 */
    private String screenName;

    /** リクエスト時間 */
    private String reqTime;

    /** リクエスト時間帯 */
    private String reqTimeZone;

    /** ログインユーザ名 */
    private String user;

    /** ログインユーザID */
    private String qid;

    /** ログインユーザ カンパニーコード */
    private String companyCode;

    /** EZD業務ID */
    private String ezBizID;

    /** EZDアプリケーションID */
    private String ezAplID;

    /** EZD画面ID */
    private String ezScreenID;

    /** EZD画面アプリケーションID */
    private String ezScreenAplID;

    /** 処理ステータス */
    private String status;

    /** EZDダミーリクエスト */
    private EZDCMsg cmsg;

    /** 課金情報 */
    private EZDAccountInfoWriter accountInfo;

    /** 画面遷移先 */
    private String transition;

    /** サーバ時刻 */
    private String serverTime;

    /** サーバタイムゾーン */
    private String serverTimeZone;

    /** サーバ日付 */
    private String serverDate;

    /** S21NEDataBean形式のリクエスト情報 */
    private S21NEDataBean request;

    /** S21NEDataBean形式のレスポンス情報 */
    private S21NEDataBean response;

    /** エラー情報 */
    private Exception exp;

    /** エラーメッセージコード */
    private String msgCode;

    /** エラーフィールド */
    private String field;

    /** リクエスト情報をすべて編集なしの状態で保持する */
    private Map mapReq;

    /** メッセージ情報 */
    private EZDMessageInfo msgInfo;

    /** ステータス:成功 */
    public static final String STATUS_SUCCESS = "success";

    /** ステータス:エラー */
    public static final String STATUS_ERROR = "error";

    /** ステータス:再入力エラー */
    public static final String STATUS_RECONFIRM = "reconfirm";

    /** ステータス:システムエラー */
    public static final String STATUS_SYS_ERROR = "syserror";

    /**
     * コンストラクタ。<br>
     * 何も初期化しない。<br>
     */
    public S21NEContainerDataBean() {
    }

    /**
     * コンストラクタ。<br>
     * リクエストから共通的な情報を抜き出し設定する。<br>
     * @param req condition
     */
    public S21NEContainerDataBean(HttpServletRequest req) {

        // マップに保持
        setReq(req.getParameterMap());

        // リクエストIDを設定
        reqID = getReq(S21NERequestDef.REQ_REQ_ID);

        // 画面IDを設定
        screenID = getReq(S21NERequestDef.REQ_SCREEN);
    }

    /**
     * リクエスト情報を取得する。<br>
     * @param key キー情報
     * @return 設定値
     */
    public String getReq(String key) {
        String[] vals = (String[]) mapReq.get(key);
        if (vals == null) {
            return null;
        }
        return vals[0];
    }

    /**
     * リクエスト情報を取得する。<br>
     * @param key キー情報
     * @return 設定値
     */
    public Object getReqObject(String key) {
        Object[] vals = (Object[]) mapReq.get(key);
        if (vals == null) {
            return null;
        }
        return vals[0];
    }

    /**
     * リクエスト情報から消去する。<br>
     * @param key キー情報
     */
    public void removeReq(String key) {
        mapReq.remove(key);
    }

    /**
     * リクエスト情報を設定する。<br>
     * @param map リクエスト情報
     */
    protected void setReq(Map map) {
        // Interstageの場合はMapの書き換えが不可のためコピーを作成する
        this.mapReq = new HashMap();
        this.mapReq.putAll(map);
    }

    /**
     * @param reqID 設定する reqID
     */
    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    /**
     * @param screenID 設定する screenID
     */
    public void setScreenID(String screenID) {
        this.screenID = screenID;
    }

    /**
     * @return reqID
     */
    public String getReqID() {
        return reqID;
    }

    /**
     * @return screenID
     */
    public String getScreenID() {
        return screenID;
    }

    /**
     * @return time
     */
    public String getReqTime() {
        return reqTime;
    }

    /**
     * @param time 設定する time
     */
    public void setReqTime(String time) {
        this.reqTime = time;
    }

    /**
     * @return timeZone
     */
    public String getReqTimeZone() {
        return reqTimeZone;
    }

    /**
     * @param timeZone 設定する timeZone
     */
    public void setReqTimeZone(String timeZone) {
        this.reqTimeZone = timeZone;
    }

    /**
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user 設定する user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return companyCode
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * @param companyCode 設定する companyCode
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * @return ezBizID
     */
    public String getEzBizID() {
        return ezBizID;
    }

    /**
     * @param progID 設定する progID
     */
    public void setEzBizID(String progID) {
        this.ezBizID = progID;
    }

    /**
     * @return cmsg
     */
    public EZDCMsg getCmsg() {
        return cmsg;
    }

    /**
     * @param cmsg 設定する cmsg
     */
    public void setCmsg(EZDCMsg cmsg) {
        this.cmsg = cmsg;
    }

    /**
     * @return accountInfo
     */
    public EZDAccountInfoWriter getAccountInfo() {
        return accountInfo;
    }

    /**
     * @param accountInfo 設定する accountInfo
     */
    public void setAccountInfo(EZDAccountInfoWriter accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 設定する status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return request
     */
    public S21NEDataBean getRequest() {
        return request;
    }

    /**
     * @param request 設定する request
     */
    public void setRequest(S21NEDataBean request) {
        this.request = request;
    }

    /**
     * @return response
     */
    public S21NEDataBean getResponse() {
        return response;
    }

    /**
     * @param response 設定する response
     */
    public void setResponse(S21NEDataBean response) {
        this.response = response;
    }

    /**
     * @return qid
     */
    public String getQid() {
        return qid;
    }

    /**
     * @param qid 設定する qid
     */
    public void setQid(String qid) {
        this.qid = qid;
    }

    /**
     * @return serverDate
     */
    public String getServerDate() {
        return serverDate;
    }

    /**
     * @param serverDate 設定する serverDate
     */
    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    /**
     * @return serverTime
     */
    public String getServerTime() {
        return serverTime;
    }

    /**
     * @param serverTime 設定する serverTime
     */
    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    /**
     * @return serverTimeZone
     */
    public String getServerTimeZone() {
        return serverTimeZone;
    }

    /**
     * @param serverTimeZone 設定する serverTimeZone
     */
    public void setServerTimeZone(String serverTimeZone) {
        this.serverTimeZone = serverTimeZone;
    }

    /**
     * @return transition
     */
    public String getTransition() {
        return transition;
    }

    /**
     * @param transition 設定する transition
     */
    public void setTransition(String transition) {
        this.transition = transition;
    }

    /**
     * @return exp
     */
    public Exception getExp() {
        return exp;
    }

    /**
     * @param exp 設定する exp
     */
    public void setExp(Exception exp) {
        this.exp = exp;
    }

    /**
     * @return msgCode
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode 設定する msgCode
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field 設定する field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return msgInfo
     */
    public EZDMessageInfo getMsgInfo() {
        return msgInfo;
    }

    /**
     * @param msgInfo 設定する msgInfo
     */
    public void setMsgInfo(EZDMessageInfo msgInfo) {
        this.msgInfo = msgInfo;
    }

    /**
     * @return screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * @param screenName 設定する screenName
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * @return ezAplID
     */
    public String getEzAplID() {
        return ezAplID;
    }

    /**
     * @param ezAplID 設定する ezAplID
     */
    public void setEzAplID(String ezAplID) {
        this.ezAplID = ezAplID;
    }

    /**
     * @return ezScreenAplID
     */
    public String getEzScreenAplID() {
        return ezScreenAplID;
    }

    /**
     * @param ezScreenAplID 設定する ezScreenAplID
     */
    public void setEzScreenAplID(String ezScreenAplID) {
        this.ezScreenAplID = ezScreenAplID;
    }

    /**
     * @return ezScreenID
     */
    public String getEzScreenID() {
        return ezScreenID;
    }

    /**
     * @param ezScreenID 設定する ezScreenID
     */
    public void setEzScreenID(String ezScreenID) {
        this.ezScreenID = ezScreenID;
    }

    /**
     * @return tableName
     */
    public String getTableName() {
        return "";
    }

    /**
     * @param tableName 設定する tableName
     */
    public void setTableName(String tableName) {
        return;
    }
}
