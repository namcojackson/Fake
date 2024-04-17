package com.canon.cusa.s21.framework.generictable.fw;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canon.cusa.s21.framework.security.S21AuthenticationException;
import com.canon.cusa.s21.framework.security.authentication.details.S21IdentityDetails;
import com.canon.cusa.s21.framework.security.context.S21SecurityContext;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;

/**
 * コードメンテナンスFW用Servletクラス。<br>
 * @author Administrator
 */
public class S21NEServlet extends HttpServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 文字コード */
    private static final String CHAR_ENC = "UTF-8";

    /**
     * GETリクエストに対する処理を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @throws ServletException exception
     * @throws IOException exception
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProc(req, resp);
    }

    /**
     * POSTリクエストに対する処理を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @throws ServletException exception
     * @throws IOException exception
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProc(req, resp);
    }

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @throws ServletException exception
     * @throws IOException exception
     */
    protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Context作成
        S21NEContextHolder.setContext(new S21NEContext(req.getSession()));

        // 文字コード指定
        req.setCharacterEncoding(CHAR_ENC);

        // 画面共通情報を取得
        S21NEContainerDataBean bean = createContainer(req);

        if (bean.getScreenID() == null || bean.getReqID() == null) {
            // 不正リクエスト
            return;
        }

        // コントロールクラスのインスタンス生成
        S21NEReqControl control = S21NEClassFactory.getInstance().createReqControl(bean.getScreenID(), bean.getReqID());

        // リクエスト固有情報設定
        setApInfo(bean, control);

        // コントロール処理を起動
        control.doProc(req, resp, bean);

        // Context消去
        S21NEContextHolder.clearContext();
    }

    /**
     * リクエスト情報、処理結果を保持するコンテナクラスのインスタンス作成。<br>
     * コンテナクラスにインスタンスを生成し、全画面共通情報を設定する。<br>
     * @param req HttpServletRequest
     * @return コンテナクラスのインスタンス
     */
    protected S21NEContainerDataBean createContainer(HttpServletRequest req) {

        S21NEContainerDataBean bean = createContainerInstance(req);

        // ユーザ情報を設定する
        try {
            // セキュリティ情報を取得
            S21SecurityContext context = S21SecurityContextHolder.getContext();
            S21IdentityDetails details = context.getAuthentication().getIdentityDetails();

            // ユーザ情報を設定
            bean.setQid(details.getUID());
            bean.setUser(details.getUserName());
            bean.setCompanyCode(details.getCompanyCode());

            // リクエストに関する情報を設定
            Date date = new Date();

            // timeZone
            SimpleDateFormat timeZoneFmt = new SimpleDateFormat("z");
            bean.setReqTimeZone(timeZoneFmt.format(date));

            // serverCallTime
            SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String str = dateFmt.format(date);
            bean.setReqTime(str);

        } catch (S21AuthenticationException e) {
            // セキュリティ情報が取得できない
            bean.setQid("dummy");
            bean.setUser("dummyUser");
            bean.setCompanyCode("dummyCompany");
        }

        return bean;
    }

    /**
     * 各画面固有の情報をリクエストに設定する。<br>
     * @param bean コンテナクラス
     * @param control 画面制御処理
     */
    private void setApInfo(S21NEContainerDataBean bean, S21NEReqControl control) {
        bean.setEzBizID(control.getEzAplID(bean));
        bean.setEzScreenID(control.getEzScreenID(bean));
        bean.setEzAplID(control.getEzAplID(bean));
        bean.setScreenName(control.getScreenName(bean));
    }

    /**
     * S21NEContainerDataBeanを継承するコンテナクラスのインスタンスを作成する。<br>
     * @param req HttpServletRequest
     * @return S21NEContainerDataBean継承クラスのインスタンス
     */
    protected S21NEContainerDataBean createContainerInstance(HttpServletRequest req) {
        S21NEContainerDataBean bean = new S21NEContainerDataBean(req);
        return bean;
    }
}
