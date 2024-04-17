package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 テーブル検索イベントコントロール処理。<br>
 * @author Administrator
 */
public class S21CMSearchTableControl extends S21CMBaseControl {

    @Override
    protected void control(S21NEContainerDataBean bean) {

        // 業務処理起動
        fireBiz(bean);

        // 業務処理結果をコンテナのレスポンスから取得
        S21CMSearchInputBean req = (S21CMSearchInputBean) bean.getRequest();

        // 業務処理結果をコンテナのレスポンスから取得
        S21CMSearchListBean result = (S21CMSearchListBean) bean.getResponse();

        // 表示用Beanに詰め替え
        S21CMSearchScreenBean view = new S21CMSearchScreenBean();
        view.setTableNames(result.getTableNames());
        view.setTableLongNames(result.getTableLongNames());

        // テーブル一覧情報を設定
        view.setSearchTableNames(result.getSearchTableNames());
        view.setSearchTableLongNames(result.getSearchTableLongNames());
        view.setSearchTableComments(result.getSearchTableComments());
        view.setSearchTableVisible(result.getSearchTableVisible());

        // 入力された検索条件を設定
        view.setTableSearchKey(req.getTableSearchKey());
        view.setSearchLongName(req.isSearchLongName());
        //view.setSearchType("1");
        view.setSearchType(req.getSearchType());

        // テーブルリストを表示する
        view.setTableSearchList(true);

        // 検索条件を表示しない
        view.setShowCondition(false);

        // 検索結果を表示しない
        view.setShowResult(false);

        // 編集可能として表示しない
        view.setUpdatable(false);

        // レスポンスとして設定
        bean.setResponse(view);
    }

    @Override
    protected void error(S21NEContainerDataBean bean) {
        // エラーは発生しない
    }

    /**
     * 業務ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 業務ID
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getEzBizID(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getEzBizID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_BIZ_ID;
    }

    /**
     * アプリケーションID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return アプリケーションID
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getEzAplID(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getEzAplID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_APL_ID_MAINTENANCE;
    }

    /**
     * 画面ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面ID
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getEzScreenID(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getEzScreenID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_ID_SEARCH;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_SEARCH;
    }

}
