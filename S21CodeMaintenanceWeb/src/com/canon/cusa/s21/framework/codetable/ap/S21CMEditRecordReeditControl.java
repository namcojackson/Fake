package com.canon.cusa.s21.framework.codetable.ap;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 レコード再編集イベント。<br>
 * @author Administrator
 */
public class S21CMEditRecordReeditControl extends S21CMBaseControl {

    @Override
    protected void control(S21NEContainerDataBean bean) {

//        // 業務処理起動
//        fireBiz(bean);

        // 入力情報取得
        S21CMEditRecordInputBean eib = (S21CMEditRecordInputBean) bean.getRequest();

        S21CMEditRecordScreenBean ersb = new S21CMEditRecordScreenBean();

        // 処理結果の詰め替え
        ersb.setTableName(eib.getTableName());
        ersb.setTableLongName(S21CodeTableAccessor.getLongName(eib.getTableName()));
        ersb.setMsg(eib.getMsg());

        // 設定値の表示
        ersb.setMsgMap(S21CMEditCommon.convertMsg2Map(eib.getMsg()));

        // 編集種別
        ersb.setType(eib.getType());

        // 確認画面
        ersb.setConfirm(false);

        // カラム情報の詰め替え
        ersb.setColInfos(S21CodeTableAccessor.getColumnInfoAll(eib.getTableName()));

        bean.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);

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
        return S21CMBizScreenIdDef.CM_SCREEN_ID_EDIT;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_EDIT;
    }

}
