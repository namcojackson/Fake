package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 個別テーブルキャッシュ削除業務処理。<br>
 * リクエストとレスポンスのBeanは検索と同じものを使用。<br>
 * @author Administrator
 */
public abstract class S21CMCacheClearBaseBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {
        // 入力情報を取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();

        // キャッシュ削除
        cacheClear(sib);

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

        // 検索テーブル情報
        slb.setTableName(sib.getTableName());
        slb.setTableLongName(S21CodeTableAccessor.getLongName(sib.getTableName()));

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);

    }

    protected abstract void cacheClear(S21CMSearchInputBean sib);
}
