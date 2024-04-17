package com.canon.cusa.s21.framework.codetable.ap;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 検索画面初期化イベント。<br>
 * @author Administrator
 */
public class S21CMSearchInitBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 共通情報のクリア
        S21CMSearchCommon.resetList();

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }
}
