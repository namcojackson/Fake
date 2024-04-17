package business.servlet.ZZZL0010.common;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0010.constant.ZZZL0010Constant;

import com.canon.cusa.s21.framework.online.router.S21RouteInfo;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;

/**
 * 引継情報操作クラス。
 * @author q02673
 */
public class TakeoverManager implements ZZZL0010Constant {

    // performance tuning (sync)
    private static final DateFormat df = new SimpleDateFormat("HHmmssSSS");

    /**
     * 引継情報を構築します。
     * @param ctx EZDApplicationContext
     * @param routeInfo 遷移情報
     * @param businessId 遷移先業務ID
     * @param businessArgs 業務引継情報
     * @return 引継情報
     */
    public static TakeoverInfo createTakeoverInfoForSend(EZDApplicationContext ctx, S21RouteInfo routeInfo, String businessId, Serializable businessArgs) {
        TakeoverInfo takeoverInfo = new TakeoverInfo();

        // EZD FW用パラメータ
        String dateStamp;
        synchronized (df) {
            dateStamp = df.format(new Date());
        }
        takeoverInfo.setRandomKey(dateStamp);

        // 遷移元画面ID
        takeoverInfo.setPageID(ZZZL0010CommonLogic.getPreviousPageID(ctx));

        // 開始業務ID
        takeoverInfo.setStartBusinessId(routeInfo.getEzdStartBusinessId());

        // EZD項目
        takeoverInfo.setEzBusinessID(businessId);
        takeoverInfo.setEzWindowName((String) ctx.getAttribute(WINDOWNAME));
        takeoverInfo.setEzLoginTime((String) ctx.getAttribute(LOGINTIME));
        takeoverInfo.setEzLoginTimeZone((String) ctx.getAttribute(LOGINTIMEZONE));

        // Multi screen start Shinobu Tsunaki
        takeoverInfo.setWindowNames((String) createWinNames(ctx.getSessionProfile().getTransitionContext().enumChildWindowNames()));
        // Multi screen end Shinobu Tsunaki

        // NOTE:テストモードかつFORM認証の場合はユーザID、パスワード引継ぎ
        // NOTE:複数WARでセッション共有時はコメントアウト
        if (RUN_MODE_TEST.equalsIgnoreCase(ZZZL0010CommonLogic.getProperty(PROPKEY_RUN_MODE)) && AUTH_METHOD_FORM.equalsIgnoreCase(ZZZL0010CommonLogic.getProperty(PROPKEY_AUTH_METHOD))) {
            takeoverInfo.setEzUserID((String) ctx.getAttribute(USER_ID));
            takeoverInfo.setEzPassword((String) ctx.getAttribute(PASSWORD));
            takeoverInfo.setEzUserName((String) ctx.getAttribute(USER_NAME));
        }

        // リクエストパラメータに付与するパラメータをSession情報から生成
        Map<String, String> paramMap = (Map<String, String>) ctx.getAttribute(ATTRB_REQUEST_PARAM_MAP);

        // if (paramMap != null) {
        // for (String name : paramMap.keySet()) {
        // takeoverInfo.setParameter(name, paramMap.get(name));
        // }
        // }
        // Performance Tuning by miyamoto
        if (paramMap != null) {
            for (Entry entry : paramMap.entrySet()) {
                takeoverInfo.setParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        // シリアライズして引継ぐSession情報
        List<String> sessionName = routeInfo.getReplicationSessionName();
        for (String name : sessionName) {
            if (name != null && name.trim().length() > 0) {
                Serializable value = (Serializable) ctx.getAttribute(name);
                if (value != null) {
                    takeoverInfo.setSession(name, value);
                }
            }
        }

        // グローバルカンパニーコードをリクエストパラメータに付与
        takeoverInfo.setGlobalCompanyCode(S21SecurityContextHolder.getContext().getSystemAttributes().getGlobalCompanyCode());

        // 業務引継情報
        takeoverInfo.setBusinesArgs(businessArgs);

        return takeoverInfo;
    }

    /**
     * 引継情報を構築します。
     * @param ctx EZDApplicationContext
     * @param routeInfo 遷移情報
     * @return 引継情報
     */
    public static TakeoverInfo createTakeoverInfoForNonEzdAppSend(EZDApplicationContext ctx, S21RouteInfo routeInfo) {
        TakeoverInfo takeoverInfo = new TakeoverInfo();

        // リクエストパラメータに付与するパラメータをSession情報から生成
        List<String> attributeName = routeInfo.getReplicationParameterName();
        for (String name : attributeName) {
            if (name != null && name.trim().length() > 0) {
                Serializable value = ctx.getAttribute(name);
                if (value != null) {
                    takeoverInfo.setParameter(name, value);
                }
            }
        }

        return takeoverInfo;
    }

    /**
     * リクエストパラメータから引継情報を復元します。
     * @param ctx EZDApplicationContext
     * @return 引継情報
     */
    public static TakeoverInfo createTakeoverInfoForReceive(EZDApplicationContext ctx) {
        HttpServletRequest request = ctx.getHttpServletRequest();
        String transferObject = request.getParameter(PARAM_TRANSFER_OBJECT);

        // 業務引継情報
        TakeoverInfo takeoverInfo = TakeoverInfo.decode(transferObject, ctx.getHttpServletRequest().getParameterMap());

        // FW情報を格納
        takeoverInfo.setEzBusinessID(request.getParameter(BUSINESS_ID));
        takeoverInfo.setEzLoginTime(request.getParameter(LOGINTIME));
        takeoverInfo.setEzLoginTimeZone(request.getParameter(LOGINTIMEZONE));
        takeoverInfo.setEzWindowName(request.getParameter(WINDOWNAME));

        return takeoverInfo;
    }

    /**
     * セッション情報に引継情報に含まれるFW情報を設定します。
     * @param ctx EZDApplicationContext
     * @param takeoverInfo 引継情報
     */
    public static void setupFwInfo(EZDApplicationContext ctx, TakeoverInfo takeoverInfo) {
        Date date = new Date();
        ctx.setAttribute(BUSINESS_ID, takeoverInfo.getEzBusinessID());
        ctx.setAttribute(LOGINTIME, nvl(takeoverInfo.getEzLoginTime(), new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date)));
        ctx.setAttribute(LOGINTIMEZONE, nvl(takeoverInfo.getEzLoginTimeZone(), new SimpleDateFormat("Z").format(date)));
        ctx.setAttribute(WINDOWNAME, takeoverInfo.getEzWindowName());

        // NOTE:Session情報に内包されているので、そちらで設定する
        // ctx.setAttribute(USER_ID, takeoverInfo.getEzUserID());
        // ctx.setAttribute(USER_NAME, takeoverInfo.getEzUserName());
        // ctx.setAttribute(PASSWORD, takeoverInfo.getEzPassword());
    }

    /**
     * セッション情報へ引継情報の内容を設定します。
     * @param ctx EZDApplicationContext
     * @param takeoverInfo 引継情報
     */
    public static void setupSession(EZDApplicationContext ctx, TakeoverInfo takeoverInfo) {
        Map<String, Serializable> map = takeoverInfo.getSession();

        // for (Object key : map.keySet()) {
        // ctx.setAttribute((String) key, (Serializable)
        // map.get(key));
        // }

        // Performance Tuning by Miyamoto
        for (Entry entry : map.entrySet()) {
            ctx.setAttribute((String) entry.getKey(), (Serializable) entry.getValue());
        }

    }

    /**
     * 指定された値がnullの場合にdefaultValueを返します。
     * @param value 値
     * @param defaultValue valueがnullの場合に返す値
     * @return
     */
    private static String nvl(String value, String defaultValue) {
        if ((value != null) && (value.length() > 0)) {
            return value;
        } else {
            return defaultValue;
        }
    }

    // Multi screen start Shinobu Tsunaki
    private static String createWinNames(String[] vals) {
        if (vals == null || vals.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String val : vals) {
            sb.append(val).append(";");
        }
        return sb.toString();
    }
    // Multi screen end Shinobu Tsunaki
}
