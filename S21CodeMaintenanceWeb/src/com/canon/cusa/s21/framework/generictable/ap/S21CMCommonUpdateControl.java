package com.canon.cusa.s21.framework.generictable.ap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 更新系イベント共通処理。<br>
 * 更新系業務処理を行い、成功時は検索画面へリダイレクトする。<br>
 * 障害時は検索画面の表示へ戻りエラーメッセージをステータス部へ出力する。<br>
 * @author Administrator
 */
public abstract class S21CMCommonUpdateControl extends S21CMSearchtScreenCommonControl {

    // Start 2012.10.9 M.Yaguchi - For SSL
    /** リダイレクトする画面のURL */
    private static final String DEF_REDIRECT_URL_AND_REQ = "?S21NE_SCREEN_ID=cm_search&S21NE_REQ_ID=selectTable&selectTable=";
    // End   2012.10.9 M.Yaguchi - For SSL
    
    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void control(S21NEContainerDataBean bean) {

        // 業務処理起動
        fireBiz(bean);

        // 正常時は検索画面(更新テーブルの参照)へリダイレクト
    }

    /**
     * 業務処理成功後のforward処理。<br>
     * 標準のRequestDispatcher#forward処理ではなくsendRedirect処理を行う。<br>
     * リダイレクト先は検索画面の更新対象テーブル照会とする。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException
     * @throws IOException
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#succeedFwd(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    protected void succeedFwd(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {
        String tableName = ((S21CMTableBean) bean.getRequest()).getTableName();
        // Start 2012.10.12 M.Yaguchi - For SSL
        String url = req.getRequestURL().toString();
        String referer = req.getHeader("Referer");
        if(referer == null || !referer.startsWith("http://")){
            url = url.replaceFirst("http://", "https://");
        }
        resp.sendRedirect(url + DEF_REDIRECT_URL_AND_REQ + tableName);
        // End 2012.10.12 M.Yaguchi - For SSL
    }
}
