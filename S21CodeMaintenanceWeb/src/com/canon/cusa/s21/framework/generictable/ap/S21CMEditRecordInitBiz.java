package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 レコード入力初期化処理。<br>
 * @author Administrator
 */
public class S21CMEditRecordInitBiz extends S21NEBusiness {

    /* (non-Javadoc)
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBusiness#execute(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 検索処理結果保持Beanの作成
        S21CMEditRecordBean erb = new S21CMEditRecordBean();

        // レスポンス情報としてS21CMEditRecordBeanを設定
        req.setResponse(erb);

        // 入力情報取得
        S21CMEditRecordInputBean inb = (S21CMEditRecordInputBean) req.getRequest();

        // テーブル名称設定
        erb.setTableName(inb.getTableName());
        erb.setTableLongName(S21GMDBAccessQuery.getInstance().getGmTableLongName(inb.getTableName()));

        // 指定レコードの変更であるか?
        // 追加(index=-1)の場合は何もしない
        if (inb.getIndex() >= 0) {
            // 検索条件を指定して対象レコード取得
//            erb.setMsg(S21CodeTableAccessor.findByKey(inb.getMsg()));
        	erb.setMsg(S21GenericTableAccessor.findByKey(inb.getMsg()));
        }

        // カラム情報を取得
//        erb.setColInfos(S21CodeTableAccessor.getColumnInfoAll(inb.getTableName()));
        erb.setColInfos(S21GenericTableAccessor.getColumnInfoAll(inb.getTableName()));

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

}
