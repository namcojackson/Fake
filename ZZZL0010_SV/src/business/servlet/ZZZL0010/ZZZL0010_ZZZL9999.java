package business.servlet.ZZZL0010;


import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0010.common.ZZZL0010CommonLogic;
import business.servlet.ZZZL0010.constant.ZZZL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;

/**
 * Router業務：戻りアプリ
 * @author q02673
 */
public class ZZZL0010_ZZZL9999 extends S21CommonHandlerEx implements ZZZL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
         return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (isLogout(getLastGuard())) {
            // 直前に押されたボタン名がLogoutまたはExitの場合
            logout(ctx);
        } else if (isJumpRet()) {
            // 業務JUMP呼出し
            invoke(ctx, getJumpBusinessID());
        } else {
            // 業務JUMP呼出し後の戻り遷移など
            String defaultBusinessId = ZZZL0010CommonLogic.getProperty(PROPKEY_DEFAULT_BIZID);
            String formAutheBusinessId = ZZZL0010CommonLogic.getProperty(PROPKEY_FORM_AUTH_BIZID);
            String ezdEvent = ctx.getEventName();
            String pageId = getSubmitedScrnId(ctx);

            if (isLogout(ezdEvent)) {
                logout(ctx);
            } else if (pageId != null && pageId.startsWith(formAutheBusinessId)) {
                // フォーム認証業務から前に戻った場合
                logout(ctx);
            } else if (pageId != null && pageId.startsWith(defaultBusinessId)) {
                // デフォルト業務から前に戻った場合
                logout(ctx);
            } else {
                // デフォルト業務（または非EZDのWebアプリケーションID）への遷移
                invoke(ctx, defaultBusinessId);
            }
        }
    }

    /**
     * 指定された業務IDの業務を起動します。
     * @param ctx        EZDApplicationContext
     * @param businessId 起動する業務ID
     */
    private void invoke(EZDApplicationContext ctx, String businessId) {
        // 前業務からの引継情報取得
        Serializable bizObject = getArgForJump();
        if (bizObject == null && businessId != null) {
            // NOTE:業務遷移用パラメータ設定で設定されていた場合についてもフォロー
            bizObject = getArgForSubScreen();
        }

        String event = ZZZL0010CommonLogic.jump(ctx, businessId, bizObject);
        if (EVENT_JUMP_INTERNAL.equals(event)) {
            // 同一EAR内業務ジャンプ
            overrideTransition(businessId);
            setArgForSubScreen(bizObject);
            setResult(event);
        } else {
            setResult(event);
        }
    }

    
    /**
     * ログアウト処理を起動します。
     * @param ctx EZDApplicationContext
     */
    private void logout(EZDApplicationContext ctx) {
        if (EVENT_LOGOUT_TYPE_SSO.equals(ZZZL0010CommonLogic.getProperty(PROPKEY_LOGOUT_METHOD))) {
            // SSO logout用ページへ遷移
            ctx.setAttribute(ATTRB_FORWARD_WEB_APP_ID, ZZZL0010CommonLogic.getProperty(PROPKEY_LOGOUT_WEB_APP_ID));
            // 前業務からの引継情報取得
            Serializable bizObject = getArgForJump();
            String event = ZZZL0010CommonLogic.jumpLogout(ctx, null, bizObject);
            setResult(event);            
        } else {
            // NOTE:セッション情報はmain.jspでinvalidateされる
            setResult(EVENT_LOGOUT);
        }
    }

    /**
     * イベント名がログアウト対象であるか判別します。
     * @param eventName イベント名またはボタン名
     */
    private boolean isLogout(String eventName) {
        String[] eventList = ZZZL0010CommonLogic.getProperty(PROPKEY_LOGOUT_EVENT_NAMES).split(",");
        for (int i = 0; i < eventList.length; i++) {
            if (eventList[i].equalsIgnoreCase(eventName)) {
                return true;
            }
        }
        return false;
    }
}
