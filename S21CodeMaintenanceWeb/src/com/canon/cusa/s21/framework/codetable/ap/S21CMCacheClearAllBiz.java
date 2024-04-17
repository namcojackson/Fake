package com.canon.cusa.s21.framework.codetable.ap;

/**
 * コードメンテナンス画面 全テーブルキャッシュ削除業務処理。<br>
 * リクエストとレスポンスのBeanは検索と同じものを使用。<br>
 * @author Administrator
 */
public class S21CMCacheClearAllBiz extends S21CMCacheClearBaseBiz {

    /* (non-Javadoc)
     * @see com.canon.cusa.s21.framework.codetable.ap.S21CMCacheClearBaseBiz#cacheClear(com.canon.cusa.s21.framework.codetable.ap.S21CMSearchInputBean)
     */
    @Override
    protected void cacheClear(S21CMSearchInputBean sib) {
        // キャッシュ削除
        S21CMCodetableReflectionInvoker.invokeClearCacheAll();
    }

}
