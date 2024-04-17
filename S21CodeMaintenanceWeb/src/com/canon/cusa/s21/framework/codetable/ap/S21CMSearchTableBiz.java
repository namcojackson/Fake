package com.canon.cusa.s21.framework.codetable.ap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 テーブル検索イベント業務処理。<br>
 * @author Administrator
 */
public class S21CMSearchTableBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 対象テーブル取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();
        List<String> list = searchTable(sib.getTableSearchKey(), sib.isSearchLongName());

        slb.setSearchTableNames(list);
        slb.setSearchTableLongNames(S21CMSearchCommon.createLongNameList(list));
        slb.setSearchTableVisible(S21CMSearchCommon.createVisibleTableList(list));
        slb.setViewPage(sib.getViewPage());

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * テーブル名称で検索<br>
     * @param cond 検索条件
     * @param findLongName 検索対象
     * @return
     */
    private List<String> searchTable(String cond, boolean findLongName) {

        List<String> listResult = new ArrayList<String>();
        List<String> listSubj;

        // 検索実行クラス
        TableNameCompare comp = new TableNameCompare();
        comp.setCondition(cond);

        // 全テーブル取得
        List<String> listAll = S21CodeTableAccessor.getCodeTableList();

        // 検索対象
        if (findLongName) {
            // 対象をLongNameとする
            listSubj = S21CMSearchCommon.createLongNameList(listAll);
        } else {
            // 対象を通常のテーブル名とする
            listSubj = listAll;
        }

        Iterator<String> it = listSubj.iterator();
        for (int i = 0; it.hasNext(); i++) {
            String name = it.next();

            // チェック
            if (comp.compare(name)) {
                listResult.add(listAll.get(i));
            }
        }

        return listResult;
    }

    /**
     * 比較用処理クラス。<br>
     * @author Administrator
     */
    private class TableNameCompare {

        /** 検索条件 */
        private String cond = null;

        /** 検索種別 */
        private int type;

        /** 前方一致 */
        private static final int TOP = -1;

        /** 部分一致 */
        private static final int MID = 0;

        /** 後方一致 */
        private static final int LAST = 1;

        /** ワイルドカード */
        private static final String WC = "%";

        /**
         * 検索条件設定。<br>
         * @param condition 検索条件
         */
        public void setCondition(String condition) {

            int idxStart = condition.indexOf(WC);
            int idxEnd = condition.lastIndexOf(WC);

            if (idxStart != idxEnd) {
                // *が複数ある場合は文字列を含んでいるかチェック
                type = MID;
            } else if (idxStart == 0) {
                // 後方一致
                type = LAST;
            } else if (idxStart == -1) {
                // 前方一致
                type = TOP;
            } else if (idxStart == condition.length() - 1) {
                // 前方一致
                type = TOP;
            } else {
                // 部分一致
                type = MID;
            }

            cond = condition.replace(WC, "");
        }

        /**
         * 比較実行。<br>
         * @param val 比較対象
         * @return 結果
         */
        public boolean compare(String val) {
            switch (type) {
                case TOP:
                    // 前方一致
                    return val.startsWith(cond);
                case LAST:
                    // 後方一致
                    return val.endsWith(cond);
                default:
                    // 部分一致
                    return (val.indexOf(cond) != -1);
            }
        }
    }
}
