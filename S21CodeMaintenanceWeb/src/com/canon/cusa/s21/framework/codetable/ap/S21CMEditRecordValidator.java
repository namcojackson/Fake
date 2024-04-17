package com.canon.cusa.s21.framework.codetable.ap;

import com.canon.cusa.s21.framework.codetable.ap.S21CMEditRecordBean.S21CMEditType;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 編集画面入力情報チェック。<br>
 * 検索条件ありでの検索用。<br>
 * @author Administrator
 */
public class S21CMEditRecordValidator extends S21CMBaseValidator {

    /**
     * 検索画面の入力内容をチェックする。<br>
     * @param bean リクエスト情報を保持するコンテナBean
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEValidator#valid(javax.servlet.http.HttpServletRequest, com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public void valid(S21NEContainerDataBean bean) {

        // 検索画面の入力情報を保持するBeanを作成
        S21CMEditRecordInputBean eib = new S21CMEditRecordInputBean();

        // コンテナに入力情報を保持するBeanを設定
        bean.setRequest(eib);

        // 共通リクエストを解析
        // 表示中テーブル名

        // 入力内容のチェックと設定
        checkInputMsg(bean);
    }

    /**
     * 個別の入力情報設定。<br>
     * @param bean S21NEContainerDataBean
     */
    protected void checkInputMsg(S21NEContainerDataBean bean) {

        String prefix;
        String suffix = "";

        S21CMEditRecordInputBean eib = (S21CMEditRecordInputBean) bean.getRequest();

        // 編集種別取得
        String type = S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_EDIT_TYPE);

        if (!type.equals("")) {
            eib.setTableName(S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_SELECT_TABLE));
            eib.setTableLongName(S21CodeTableAccessor.getLongName(eib.getTableName()));

            // 種別の設定
            eib.setType(S21CMEditType.valueOf(type));

            // EZDTMsg設定
            prefix = S21CMRequestDef.REQ_EZMSG;

            // 持ち回り表示用に入力内容をそのまま保持するMapへ情報設定
            eib.setMsgMap(S21CMCommonRequestFunc.createMsgMap(eib.getTableName(), bean, prefix, suffix));

            // 追加の場合はソートカラムを指定しないことが許される
            if (type.equals(S21CMRequestDef.REQ_EDIT_TYPE_ADD)) {
                // 入力必須チェック(ソートカラム除外)
                neccesaryCheckExcludeSortCol(eib.getTableName(), bean, eib.getMsgMap());
            } else {
                // 入力必須チェック
                neccesaryCheck(eib.getTableName(), bean, eib.getMsgMap());
            }

        } else {
            // 初期化

            eib.setTableName(S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_VIEW_TABLE));
            eib.setTableLongName(S21CodeTableAccessor.getLongName(eib.getTableName()));

            // 編集対象インデックス
            eib.setIndex(S21CMCommonRequestFunc.getReqInt(bean, S21CMRequestDef.REQ_EDIT_INDEX));

            // インデックスが負値の場合は追加処理
            if (eib.getIndex() < 0) {
                return;
            }

            // EZDTMsg設定
            prefix = S21CMRequestDef.REQ_EZMSG_KEY;
            suffix = "_" + eib.getIndex();
        }

        // EZDTMsg設定
        // 入力値チェックを行う
        eib.setMsg(S21CMCommonRequestFunc.createEZDTMsg(eib.getTableName(), bean, prefix, suffix));
    }

}
