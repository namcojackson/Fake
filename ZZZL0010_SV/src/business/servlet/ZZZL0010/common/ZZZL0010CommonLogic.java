package business.servlet.ZZZL0010.common;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0010.constant.ZZZL0010Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.router.S21RouteInfo;
import com.canon.cusa.s21.framework.online.router.S21RouteManager;
import com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.userprofile.impl.mock.UserProfileServiceLoginUserMockImpl;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 * ZZZL0010共通ロジック。
 * @author q02673
 */
public class ZZZL0010CommonLogic implements ZZZL0010Constant {

    /** ZZZL0010プロパティファイル */
    private static Properties property = new Properties();

    static {
        ResourceBundle rb = ResourceBundle.getBundle("ZZZL0010");

        if (rb != null) {
            Enumeration<String> en = rb.getKeys();
            while (en.hasMoreElements()) {
                String key = en.nextElement();
                property.setProperty(key, rb.getString(key));
            }
        }
    };

    /**
     * プロパティから指定されたキーの値を返します。
     *
     * @param key プロパティキー名
     * @return プロパティキー値
     */
    public static String getProperty(String key) {
        return (String) property.get(key);
    }

    /**
     * プロパティから指定されたキーの値を返します。
     *
     * @param key プロパティキー名
     * @return プロパティキー値
     */
    public static int getPropertyInt(String key) {
        String val = (String) property.get(key);
        if (val == null) {
            return 0;
        } else {
            return Integer.parseInt(val);
        }
    }

    /**
     * Stubファイル配置ディレクトリ名を返します。
     * @param ctx EZDApplicationContext
     * @return Stubファイル配置ディレクトリ名
     */
    public static String getStubDir(EZDApplicationContext ctx) {
        String dir = ctx.getSettingStr("EZD_DENBUN_DIR");
        if (dir.endsWith(File.separator)) {
            return dir;
        } else {
            return dir + File.separator;
        }
    }

    /**
     * 非SSO環境のテストモード動作時の認証を行います。
     * 本メソッドは他EARからの遷移時に実行します。
     *
     * @param ezUserID   ユーザID
     * @param ezPassword パスワード
     * @return 認証成功時にtrue
     */
    public static boolean autoAuthentication(String ezUserID, String ezPassword) {

        if (ezUserID == null || ezUserID.length() == 0)  {
            return false;
        }
        if (ezPassword == null || ezPassword.length() == 0)  {
            return false;
        }

        if (S21UserProfileServiceFactory.getInstance().isMock()) {
            S21UserProfileService upService;
            try {
                upService = S21UserProfileServiceFactory.getInstance().getService();
            } catch (Exception th) {
                throw new S21AbendException(th, "S21UserProfileServiceFactory initialization error");
            }

            try {
                ((UserProfileServiceLoginUserMockImpl) upService).setCurrentUser(ezUserID);
            } catch (Exception th) {
                throw new S21AbendException("User, '" + ezUserID + "', is not defined in S21UserProfileMock.xml");
            }
        } else {
            try {
                if (!S21AuthenticationHelper.loginUser(null, ezUserID, ezPassword)) {
                    new S21AbendException("authentication failure:" + S21AuthenticationHelper.getLastErrorMessage()).printStackTrace();
                    return false;
                }
            } catch (Exception th) {
                th.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * 指定した業務IDへ画面遷移します。
     *
     * @param ctx          EZDApplicationContext
     * @param businessId   遷移先業務ID
     * @param bizObject    業務引継情報
     * @return イベント名
     */
    public static String jump(EZDApplicationContext ctx, String businessId, Serializable bizObject) {

        String contextRoot = ctx.getHttpServletRequest().getContextPath();
        S21RouteManager routeManager = createRouteManager(contextRoot);
        S21RouteInfo routeInfo = null;

        // 遷移先EAR指定がある場合
        String redirectEarId = (String) ctx.getAttribute(ATTRB_FORWARD_WEB_APP_ID);
        if (redirectEarId != null && redirectEarId.length() > 0) {
            routeInfo = routeManager.getTransitionInfoForApplicationId(redirectEarId);
        }

        // 遷移先EAR指定がない、または遷移先EARが定義情報に存在しない場合
        if (routeInfo == null) {
            routeInfo = routeManager.getTransitionInfoForBusinessId(businessId);
        }

        if (businessId != null && (routeInfo.isInternal() || businessId.equals(ZZZL0010CommonLogic.getProperty(PROPKEY_FORM_AUTH_BIZID)))) {
            // 同一EAR内業務ジャンプ
            // NOTE:非SSO環境用認証画面は同一EAR内で表示する
            return EVENT_JUMP_INTERNAL;
        } else if (businessId != null && routeInfo.isEzdApplication()) {
            // 他EARへの遷移

            // 遷移先URL(パス名)
            String url = routeInfo.getUrl();
            // 引継情報生成
            TakeoverInfo takeoverInfo = TakeoverManager.createTakeoverInfoForSend(ctx, routeInfo, businessId, bizObject);
            // クエリパラメータ
            // Multi screen start Shinobu Tsunaki
            //String fwParam = takeoverInfo.getQueryString();
            String fwParam = takeoverInfo.getQueryString(ctx);
            // Multi screen end Shinobu Tsunaki
            // 業務情報
            String takeoverObject = null;
            if (routeInfo.isRouterAvailable()) {
                // Router業務が有効な場合セッション情報を引継
                takeoverObject = takeoverInfo.encode();
            }

            int urlLength = url.length() + fwParam.length()
                + ("&" + PARAM_TRANSFER_OBJECT + "=").length();
            if (takeoverObject != null) {
                urlLength += takeoverObject.length();
            }

            try {
                if (urlLength <= getPropertyInt(PROPKEY_REDIRECT_URL_MAX_LENGTH)) {
                    // 他のURLへHTTP Redirect(302 Moved Temporarily)
                    url = url + fwParam;
                    if (takeoverObject != null) {
                        url = url + "&" + PARAM_TRANSFER_OBJECT + "=" + takeoverObject;
                    }
                    if (url.startsWith("http")) {
                        ctx.getHttpServletResponse().sendRedirect(new URL(url).toString());
                    } else {
                        ctx.getHttpServletResponse().sendRedirect(url);
                    }
                } else {
                    // Redirect画面経由の遷移
                    ctx.setAttribute(ATTRB_REDIRECT_URL, url);
                    ctx.setAttribute(ATTRB_TRANSFER_INFO, (Serializable) takeoverInfo);
                    ctx.setAttribute(PARAM_TRANSFER_OBJECT, takeoverObject);
                    ctx.getHttpServletRequest().getRequestDispatcher("/jsp/business/ZZZL0010Scrn00.jsp").forward(ctx.getHttpServletRequest(), ctx.getHttpServletResponse());
                }
            } catch (ServletException e) {
                throw new S21AbendException(e);
            } catch (IOException e) {
                throw new S21AbendException(e);
            }

            // NOTE:ここでSessionを破棄すると後続処理で例外が出るので破棄はLogout処理で行わせる
            //ctx.getHttpServletRequest().getSession().invalidate();
            // NOTE:複数WARでセッション共有時はEVENT_JUMP_INTERNALをreturn
            return EVENT_LOGOUT;
//            return EVENT_JUMP_INTERNAL;
        } else {
            // EZDアプリケーション以外のサイトへの遷移

            // 遷移先URL(パス名)
            String url = routeInfo.getUrl();
            // 引継情報生成
            TakeoverInfo takeoverInfo = TakeoverManager.createTakeoverInfoForNonEzdAppSend(ctx, routeInfo);
            // クエリパラメータ
            String param = takeoverInfo.getOptionalParameterString();

            try {
                // 他のURLへHTTP Redirect(302 Moved Temporarily)
                if (param != null && param.length() > 0) {
                    if (!url.contains("?")) {
                        url = url + param;
                    } else if (!url.contains("&")) {
                        url = url + param;
                    } else {
                        url = url + param.substring(1);
                    }
                }
                if (url.startsWith("http")) {
                    ctx.getHttpServletResponse().sendRedirect(new URL(url).toString());
                } else {
                    ctx.getHttpServletResponse().sendRedirect(url);
                }
            } catch (IOException e) {
                throw new S21AbendException(e);
            }
            return EVENT_LOGOUT;
        }
    }
    
    /**
     * 指定した業務IDへ画面遷移します。
     *
     * @param ctx          EZDApplicationContext
     * @param businessId   遷移先業務ID
     * @param bizObject    業務引継情報
     * @return EventName
     */
    public static String jumpLogout(EZDApplicationContext ctx, String businessId, Serializable bizObject) {

        String contextRoot = ctx.getHttpServletRequest().getContextPath();
        S21RouteManager routeManager = createRouteManager(contextRoot);
        S21RouteInfo routeInfo = null;

        String redirectEarId = (String) ctx.getAttribute(ATTRB_FORWARD_WEB_APP_ID);
        if (redirectEarId != null && redirectEarId.length() > 0) {
            routeInfo = routeManager.getTransitionInfoForApplicationId(redirectEarId);
        }

        if (routeInfo == null) {
            routeInfo = routeManager.getTransitionInfoForBusinessId(businessId);
        }

        String url = routeInfo.getUrl();
                
        try {
            ctx.setAttribute(ATTRB_REDIRECT_URL, url);
            ctx.getHttpServletRequest().getRequestDispatcher("/jsp/business/ZZZL0010Scrn01.jsp").forward(ctx.getHttpServletRequest(), ctx.getHttpServletResponse());
        } catch (ServletException e) {
            throw new S21AbendException(e);
        } catch (IOException e) {
            throw new S21AbendException(e);
        }

        return EVENT_LOGOUT;
    }

    /**
     * 経路情報管理クラスのインスタンスを生成します。
     *
     * @param contextRoot コンテキストルート
     * @return 経路情報管理クラス
     */
    private static S21RouteManager createRouteManager(String contextRoot) {
        if (ROUTE_INFO_DATASOURCE_DATABASE.equalsIgnoreCase(getProperty(PROPKEY_ROUTE_INFO_DATASOURCE))) {
            return new S21RouteManager(contextRoot);
        } else if (ROUTE_INFO_DATASOURCE_XML.equalsIgnoreCase(getProperty(PROPKEY_ROUTE_INFO_DATASOURCE))) {
            return new XmlRouteManager(contextRoot);
        } else {
            throw new S21AbendException("PropertyKey:" + PROPKEY_ROUTE_INFO_DATASOURCE + " is not defined.");
        }
    }

    /**
     * リクエストパラメータの内容をAttributeへ設定します。
     *
     * @param ctx           EZDApplicationContext
     * @param key           取得・設定キー名
     * @param defaultValue  値が取得できなかった場合に設定する値。nullの場合は設定をスキップ
     */
    public static void setPramToAttribute(EZDApplicationContext ctx, String key, String defaultValue) {
        HttpServletRequest request = ctx.getHttpServletRequest();
        String value = request.getParameter(key);

        //System.out.println("**ZZZL0010_INIT** takeover [" + key + " : " + value + "]");

        if (value != null && value.length() > 0) {
            ctx.setAttribute(key, value);
        } else if (defaultValue != null) {
            ctx.setAttribute(key, defaultValue);
        }
    }

    /**
     * リクエストパラメータを取得します。
     * @param ctx EZDApplicationContext
     */
    public static Parameters getParameters(EZDApplicationContext ctx) {
        return ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
    }

    /**
     * 遷移元画面IDを取得します。
     * @param ctx EZDApplicationContext
     */
    public static String getPreviousPageID(EZDApplicationContext ctx) {
        String scrnId = ZZZL0010CommonLogic.getParameters(ctx).getSingleValue(PAGE_ID);
        if (scrnId == null) {
            return "";
        } else {
            return scrnId;
        }
    }

    /**
     * 遷移元業務IDを取得します。
     * @param ctx EZDApplicationContext
     */
    public static String getPreviousBusinessId(EZDApplicationContext ctx) {
        return getPreviousPageID(ctx).replaceFirst("Scrn.*$", "");
    }

    /**
     * submitフィールド名を取得します。
     * @param ctx EZDApplicationContext
     */
    public static String getSubmitedFieldNm(EZDApplicationContext ctx) {
        String fieldNm = ZZZL0010CommonLogic.getParameters(ctx).getSingleValue(S21_SUBMITED_FIELD_NM);
        if (fieldNm == null) {
            return "";
        } else {
            return fieldNm;
        }
    }
}
