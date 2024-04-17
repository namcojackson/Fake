package com.canon.cusa.s21.framework.generictable.fw;

import javax.servlet.http.HttpServletRequest;

/**
 * コードメンテナンスFW用 ファイルアップロード用Servletクラス。<br>
 * マルチパートリクエスト解析用のコンテナクラスを作成する。<br>
 * コンテナクラス内でマルチパートリクエストを解析し、通常のリクエストと
 * 同じようにリクエスト情報を処理できるよう変換を行う。<br>
 * @author Administrator
 */
public class S21NEUploadServlet extends S21NEServlet {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * S21NEContainerDataBeanを継承するコンテナクラスのインスタンスを作成する。<br>
     * @param req HttpServletRequest
     * @return S21NEContainerDataBean継承クラスのインスタンス
     */
    protected S21NEContainerDataBean createContainerInstance(HttpServletRequest req) {
        S21NEContainerUploadDataBean bean = new S21NEContainerUploadDataBean(req);
        return bean;
    }
}
