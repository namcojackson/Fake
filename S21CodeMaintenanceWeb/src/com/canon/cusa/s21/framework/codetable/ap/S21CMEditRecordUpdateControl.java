package com.canon.cusa.s21.framework.codetable.ap;

import parts.common.EZDAbendException;
import parts.common.EZDMessageInfo;
import parts.common.EZDValidatorException;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEErrorUtil;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidatorException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 レコード更新イベント。<br>
 * エラー時は検索画面へ戻り、メッセージ出力。<br>
 * @author Administrator
 */
public class S21CMEditRecordUpdateControl extends S21CMCommonUpdateControl {

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
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

    @Override
    protected void error(S21NEContainerDataBean bean) {

        if(bean.getExp() instanceof S21NEValidatorException){
            
            S21NEValidatorException exp = (S21NEValidatorException) bean.getExp();
            String msgId = exp.getErrorCd();
            String column = bean.getField();
            EZDMessageInfo msgInfo = new EZDMessageInfo(msgId, new String[] {column}, 9);
            reConfirm(bean, msgInfo);
            
        } else {
            // Exception退避
            EZDAbendException exp = (EZDAbendException) bean.getExp();
    
            // Exception解析
            if (exp.getCause() instanceof S21NEValidatorException) {
    
                // S21でのチェックエラーを通知するExceptionを抜き出す
                S21NEValidatorException neve = (S21NEValidatorException) exp.getCause();
    
                // メッセージIDを取得
                String msgId = neve.getErrorCd();
    
                String column = bean.getField();
    
                // エラーメッセージ作成
                EZDMessageInfo msgInfo = new EZDMessageInfo(msgId, new String[] {column}, 9);
    
                // 確認画面へ遷移
                reConfirm(bean, msgInfo);
    
            } else if (exp.getCause() instanceof EZDValidatorException) {
    
                // EZDでのチェックエラーを通知するExceptionを抜き出す
                EZDValidatorException ezdve = (EZDValidatorException) exp.getCause();
    
                // メッセージIDを取得
                String msgId = S21NEErrorUtil.convMessageCode(ezdve.getErrCode());
    
                String column = bean.getField();
    
                // エラーメッセージ作成
                EZDMessageInfo msgInfo = new EZDMessageInfo(msgId, new String[] {column}, 9);
    
                // 確認画面へ遷移
                reConfirm(bean, msgInfo);
    
            } else {
                // 共通エラー処理
                super.error(bean);
            }
        }
    }

    /**
     * 確認画面へ遷移。<br>
     * 編集時のエラーは編集確認画面へ戻る。<br>
     * @param bean S21NEContainerDataBean
     * @param msgInfo エラーメッセージ
     */
    protected void reConfirm(S21NEContainerDataBean bean, EZDMessageInfo msgInfo) {

      // 入力情報取得
      S21CMEditRecordInputBean eib = (S21CMEditRecordInputBean) bean.getRequest();

      // 編集画面用Bean作成
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
      ersb.setConfirm(true);

      // カラム情報の詰め替え
      ersb.setColInfos(S21CodeTableAccessor.getColumnInfoAll(eib.getTableName()));

      bean.setStatus(S21NEContainerDataBean.STATUS_RECONFIRM);

      // コンテナにエラーメッセージを設定
      bean.setMsgInfo(msgInfo);

      // 処理結果の詰め替え
      bean.setResponse(ersb);
  }
}
