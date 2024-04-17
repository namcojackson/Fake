package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 削除実行イベント。<br>
 * エラー時は検索画面へ戻り、メッセージ出力。<br>
 * @author Administrator
 */
public class S21CMDeleteExecControl extends S21CMCommonUpdateControl {

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void control(S21NEContainerDataBean bean) {
        try {
            // 親クラスのメソッド呼び出し
            super.control(bean);
        } finally {
            // 処理後スクリーンIDを変更
            bean.setEzScreenID(S21CMBizScreenIdDef.CM_SCREEN_ID_SEARCH);
            bean.setScreenName(S21CMBizScreenIdDef.CM_SCREEN_NAME_SEARCH);
        }
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
        return S21CMBizScreenIdDef.CM_SCREEN_ID_DELETE;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_DELETE;
    }

}
