package com.canon.cusa.s21.framework.codetable.ap;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.canon.cusa.s21.framework.security.S21Authentication;
import com.canon.cusa.s21.framework.security.S21AuthenticationException;
import com.canon.cusa.s21.framework.security.S21AuthorizationAction;
import com.canon.cusa.s21.framework.security.S21SecurityException;
import com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken;
import com.canon.cusa.s21.framework.security.config.S21ConfigurationException;
import com.canon.cusa.s21.framework.security.config.S21SecurityConfigHolder;
import com.canon.cusa.s21.framework.security.context.S21SecurityContext;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;
import com.canon.cusa.s21.framework.security.provider.S21AuthenticationProvider;

/**
 * ダミー認証を行うServlete Filter。<br>
 * S21Authのフィルターの前にいれること。<br>
 * (後ろでもよいが初回のリクエストが認証済みとならない)<br>
 * @author Administrator
 */
public class S21CMTestDBAuthFilter implements Filter {

    /**
     * 終了処理。<br>
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * フィルター処理を実装する。<br>
     * @param arg0 ServletRequest
     * @param arg1 ServletResponse
     * @param arg2 FilterChain
     * @throws IOException IOException
     * @throws ServletException ServletException
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

        S21SecurityConfigHolder.setConfiguration("ezd-security.xml");

        String userId = "S00001";
        String password = "canon";

        try {
            S21SecurityContext sCtx = S21SecurityContextHolder.getContext();

            if (sCtx == null) {

                try {
                    sCtx = S21SecurityContextFactory.getContextFactory().getSecurityContext(S21SecurityConfigHolder.DEFAULT_CONFIG);

                    S21SecurityContextHolder.setContext(sCtx);
                } catch (S21ConfigurationException ex) {
                    //logger.error("Configuration exception", ex);
                    ex.printStackTrace();
                }
            }

            S21Authentication authToken = new S21UserPasswordAuthenticationToken(userId, password);

            S21Authentication smAuth = (S21Authentication) sCtx.getConfig().getSecurityProviderManager().getAuthenticationProvider(sCtx).authenticate(authToken);

            S21AuthorizationAction[] actions = smAuth.getAuthorizationActions();
            System.out.println("S21AuthorizationAction:" + actions);

            sCtx.setAuthentication(smAuth);

            S21SecurityContextHolder.getContext().getSystemAttributes().setGlobalCompanyCode("ABR");

        } catch (S21AuthenticationException e) {
            e.printStackTrace();
        } catch (S21SecurityException e) {
            e.printStackTrace();
        } catch (S21ConfigurationException e) {
            e.printStackTrace();
        }

        // 次のFilterへ処理を委譲
        arg2.doFilter(arg0, arg1);
    }

    /**
     * 初期化処理。<br>
     * @param arg0 FilterConfig
     * @throws ServletException ServletException
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {
    }

}
