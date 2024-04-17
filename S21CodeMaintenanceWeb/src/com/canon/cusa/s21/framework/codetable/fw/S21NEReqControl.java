package com.canon.cusa.s21.framework.codetable.fw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * コードメンテナンス用プレゼンテーションコントロールインタフェース。<br>
 * @author Administrator
 */
public interface S21NEReqControl {

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException exception
     * @throws IOException exception
     */
    public void doProc(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException;

    /**
     * 業務IDを取得する。<br>
     * @param bean リクエスト情報コンテナ
     * @return 業務ID
     */
    public String getEzBizID(S21NEContainerDataBean bean);

    /**
     * アプリケーションIDを取得する。<br>
     * @param bean アプリケーション情報コンテナ
     * @return アプリケーションID
     */
    public String getEzAplID(S21NEContainerDataBean bean);

    /**
     * 画面IDを取得する。<br>
     * @param bean リクエスト情報コンテナ
     * @return 画面ID
     */
    public String getEzScreenID(S21NEContainerDataBean bean);

    /**
     * 画面名称を取得する。<br>
     * @param bean リクエスト情報コンテナ
     * @return 画面名称
     */
    public String getScreenName(S21NEContainerDataBean bean);
}
