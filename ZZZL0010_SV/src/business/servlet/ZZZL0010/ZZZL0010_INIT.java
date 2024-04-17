package business.servlet.ZZZL0010;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZZL0010.common.TakeoverManager;
import business.servlet.ZZZL0010.common.TakeoverInfo;
import business.servlet.ZZZL0010.common.ZZZL0010CommonLogic;
import business.servlet.ZZZL0010.constant.ZZZL0010Constant;

import com.canon.cusa.s21.framework.common.util.S21Text;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;
import com.canon.cusa.s21.framework.security.S21AuthenticationException;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;

/**
 * Router業務：初期表示
 * @author q02673
 */
public class ZZZL0010_INIT extends S21CommonHandlerEx implements ZZZL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ctx.setAttribute("stub_dir", ZZZL0010CommonLogic.getStubDir(ctx));

        // 業務用引継パラメータ格納領域初期化
        ctx.setAttribute(ATTRB_REQUEST_PARAM_MAP, null);

        // FW業務引継情報取得
        TakeoverInfo takeoverInfo = TakeoverManager.createTakeoverInfoForReceive(ctx);
        TakeoverManager.setupFwInfo(ctx, takeoverInfo);
        TakeoverManager.setupSession(ctx, takeoverInfo);

        try {
            // 認証済の場合
            if (S21SecurityContextHolder.getContext() != null && S21SecurityContextHolder.getContext().getAuthentication() != null && S21SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

                invoke(ctx, takeoverInfo);
                return;
            }
        } catch (S21AuthenticationException e) {
            throw new S21AbendException(e);
        }

        // 認証未済の場合
        if (RUN_MODE_PRODUCTION.equalsIgnoreCase(ZZZL0010CommonLogic.getProperty(PROPKEY_RUN_MODE))) {
            // productionモード（SSO認証利用時）
            throw new S21AbendException("ZZSM1061E"); // 認証未済
        } else if (AUTH_METHOD_SSO.equalsIgnoreCase(ZZZL0010CommonLogic.getProperty(PROPKEY_AUTH_METHOD))) {
            // testモードでSSO認証利用時
            throw new S21AbendException("ZZSM1061E"); // 認証未済
        } else {
            // testモードでSSO認証未使用（FORM認証）時

            // ezUserID,ezPasswordがセッションから取得できる場合は他EARからの遷移
            // この場合、自動認証を試みる
            String ezUserID = (String) ctx.getAttribute(USER_ID);
            String ezPassword = (String) ctx.getAttribute(PASSWORD);

            if (ZZZL0010CommonLogic.autoAuthentication(ezUserID, ezPassword)) {
                // 認証成功の場合
                invoke(ctx, takeoverInfo);
            } else {
                // FORM認証表示
                invokeBusiness(ctx, takeoverInfo, ZZZL0010CommonLogic.getProperty(PROPKEY_FORM_AUTH_BIZID));
            }
        }
    }

    /**
     * 起動すべき業務を判定し起動します。
     * @param ctx EZDApplicationContext
     * @param takeoverInfo 引継情報
     */
    private void invoke(EZDApplicationContext ctx, TakeoverInfo takeoverInfo) {
        // 遷移先業務ID
        HttpServletRequest request = ctx.getHttpServletRequest();
        String businessId = request.getParameter(BUSINESS_ID);

        if (S21Text.isNullOrEmpty(businessId)) {
            // businessId == null || businessId.trim().length() == 0)
            // {
            // 遷移先業務指定なし
            businessId = ZZZL0010CommonLogic.getProperty(PROPKEY_DEFAULT_BIZID);
        }

        // グローバルカンパニーコード復元
        String globalCompanyCode = takeoverInfo.getGlobalCompanyCode();
        if (globalCompanyCode != null && globalCompanyCode.length() > 0) {
            S21SecurityContextHolder.getContext().getSystemAttributes().setGlobalCompanyCode(globalCompanyCode);
        }

        invokeBusiness(ctx, takeoverInfo, businessId);
    }

    /**
     * 指定された業務IDの業務を起動します。
     * @param ctx EZDApplicationContext
     * @param takeoverInfo 引継情報
     * @param businessId 起動する業務ID
     */
    private void invokeBusiness(EZDApplicationContext ctx, TakeoverInfo takeoverInfo, String businessId) {
        // 業務引継情報をセット
        Serializable bizObject = takeoverInfo.getBusinesObject();

        if (DISABLE.equals(ZZZL0010CommonLogic.getProperty(PROPKEY_APP_INIT_TRANS_ROUTING))) {
            // 同一EAR内業務起動
            overrideTransition(businessId);
            setArgForSubScreen(bizObject);
            setResult(EVENT_JUMP_INTERNAL);
            return;
        }

        String event = ZZZL0010CommonLogic.jump(ctx, businessId, bizObject);
        if (EVENT_JUMP_INTERNAL.equals(event)) {
            // 同一EAR内業務起動
            overrideTransition(businessId);
            setArgForSubScreen(bizObject);
            setResult(event);
        } else {
            setResult(event);
        }
    }
}
