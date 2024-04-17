package com.canon.cusa.s21.framework.codetable.ap;

import java.util.List;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * コードメンテナンス画面 レコード削除選択イベント。<br>
 * @author Administrator
 */
public class S21CMDeleteSeletctControl extends S21CMSelectTableControl {

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void control(S21NEContainerDataBean bean) {

        // 業務処理起動
        fireBiz(bean);

        // 業務処理結果をコンテナのレスポンスから取得
        S21CMSearchListBean result = (S21CMSearchListBean) bean.getResponse();

        // 表示用Beanに詰め替え
        S21CMSearchScreenBean view = new S21CMSearchScreenBean();
        view.setTableNames(result.getTableNames());
        view.setTableLongNames(result.getTableLongNames());

        // 選択情報
        view.setTableName(result.getTableName());
        view.setTableLongName(result.getTableLongName());

        // 非EZDカラム情報を設定
        List<S21CodeTableColumnInfo> columns = chooseNoEzdColumns(result.getColInfo());
        view.setColInfos(columns);

        // EZDTmsgの内容を設定
        view.setValues(convertMsgArray2List(result.getMsgs(), columns));

        // 編集可能として表示しない
        view.setUpdatable(false);

        // レスポンスとして設定
        bean.setResponse(view);
    }

    /**
     * 業務ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 業務ID
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getEzBizID(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getEzBizID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_BIZ_ID;
    }

    /**
     * アプリケーションID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return アプリケーションID
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getEzAplID(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getEzAplID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_APL_ID_MAINTENANCE;
    }

    /**
     * 画面ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面ID
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getEzScreenID(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getEzScreenID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_ID_DELETE;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_DELETE;
    }

}
