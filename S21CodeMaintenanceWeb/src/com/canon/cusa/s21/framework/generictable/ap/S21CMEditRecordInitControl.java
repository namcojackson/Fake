package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.ap.S21CMEditRecordBean.S21CMEditType;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 入力画面初期化イベント。<br>
 * @author Administrator
 */
public class S21CMEditRecordInitControl extends S21CMBaseControl {

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void control(S21NEContainerDataBean bean) {

        // 業務処理起動
        fireBiz(bean);

        // 業務処理結果取得
        S21CMEditRecordBean erb = (S21CMEditRecordBean) bean.getResponse();

        S21CMEditRecordScreenBean ersb = new S21CMEditRecordScreenBean();

        // 処理結果の詰め替え
        ersb.setTableName(erb.getTableName());
        ersb.setTableLongName(erb.getTableLongName());
        ersb.setMsg(erb.getMsg());

        if (ersb.getMsg() != null) {
            // 編集画面として出力
            ersb.setType(S21CMEditType.EDIT);
            ersb.setMsgMap(S21CMEditCommon.convertMsg2Map(erb.getMsg()));
        } else {
            // 追加画面として出力
            ersb.setType(S21CMEditType.ADD);
        }

        // カラム情報の詰め替え
        ersb.setColInfos(erb.getColInfos());

        // 処理結果の詰め替え
        bean.setResponse(ersb);
    }

    @Override
    protected void error(S21NEContainerDataBean bean) {
        // 処理なし
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
        return S21CMBizScreenIdDef.CM_SCREEN_ID_EDIT;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_EDIT;
    }
}
