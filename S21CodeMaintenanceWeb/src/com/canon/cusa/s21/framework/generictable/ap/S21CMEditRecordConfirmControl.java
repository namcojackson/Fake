package com.canon.cusa.s21.framework.generictable.ap;

import java.util.Arrays;

import parts.common.EZDAbendException;
import parts.common.EZDMessageInfo;
import parts.common.EZDValidatorException;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEErrorUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 入力画面確認画面遷移イベント。<br>
 * @author Administrator
 */
public class S21CMEditRecordConfirmControl extends S21CMBaseControl {

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

        // 設定値の表示
        ersb.setMsgMap(S21CMEditCommon.convertMsg2Map(erb.getMsg()));

        // 編集種別
        ersb.setType(erb.getType());

        // 確認画面
        ersb.setConfirm(true);

        // カラム情報の詰め替え
        ersb.setColInfos(erb.getColInfos());

        // 処理結果の詰め替え
        bean.setResponse(ersb);
    }

    /* (non-Javadoc)
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#error(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void error(S21NEContainerDataBean bean) {

        // Exception解析
        EZDAbendException exp = (EZDAbendException) bean.getExp();

        if (exp.getCause() instanceof EZDValidatorException) {

            // EZDでのチェックエラーを通知するExceptionを抜き出す
            EZDValidatorException ezdve = (EZDValidatorException) exp.getCause();

            // メッセージIDを取得
            String msgId = S21NEErrorUtil.convMessageCode(ezdve.getErrCode());

            String column = bean.getField();

            // エラーメッセージ作成
            EZDMessageInfo msgInfo = new EZDMessageInfo(msgId, new String[] {column}, 9);

            // コンテナに設定
            bean.setMsgInfo(msgInfo);
        }

        // 入力情報の再現
        S21CMEditRecordScreenBean ersb = new S21CMEditRecordScreenBean();
        S21CMEditRecordInputBean eib = (S21CMEditRecordInputBean) bean.getRequest();

        // 入力値の詰め替え
        ersb.setTableName(eib.getTableName());
        ersb.setTableLongName(eib.getTableLongName());
        ersb.setMsgMap(eib.getMsgMap());

        // 編集種別
        ersb.setType(eib.getType());

        // カラム情報の詰め替え
        ersb.setColInfos(Arrays.asList(S21MsgAccessor.getInstance().getNonEzdColumnInfos(eib.getTableName())));

        // 正常系と同じ画面へ遷移
        bean.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);

        // 処理結果の詰め替え
        bean.setResponse(ersb);
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
