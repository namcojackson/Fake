package com.canon.cusa.s21.framework.generictable.ap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEValidatorException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 レコード削除業務処理<br>
 * トランザクション後処理でキャッシュ削除を行うS21CMCacheRemoveBizを親クラスとする。<br>
 * @author Administrator
 */
public class S21CMDeleteExecBiz extends S21CMCacheRemoveBiz {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 入力情報を取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = execDeleteList(sib);

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * 削除対象テーブル検索処理。<br>
     * @param sib 検索条件Bean
     * @return 検索結果Bean
     */
    private S21CMSearchListBean execDeleteList(S21CMSearchInputBean sib) {

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

        // 削除対象レコードのキー情報を保持するEZDTMsgを取得
        List<EZDTMsg> delMsgs = sib.getDelRecs();

        // ソートカラム取得
        String sortColName = S21MsgAccessor.getInstance().getContainSortNum(sib.getTableName());

        // ソートカラムが存在している場合は、削除をソート値の降順に行う
        if (sortColName != null) {
            // キー項目以外も保持するEZDTMsgを使用してソートする
            delMsgs = sortDelMsgs(sib.getTableName(), sortColName, findMsgs(delMsgs));
        }

        // 削除対象レコードを削除する
        for (int i = 0; i < delMsgs.size(); i++) {

            EZDTMsg msg = delMsgs.get(i);

            // ソートカラムが存在するのであれば、ソート値の更新を行う
            if (sortColName != null) {
                updateSortNum(sortColName, msg);
            }

            // レコード削除
//            S21CMCodetableReflectionInvoker.invokeDelete(msg);
            S21GenericTableAccessor.delete(msg);

            // リターンコードチェック
            checkReturnCode(msg);
        }

        // 検索テーブル情報
        slb.setTableName(sib.getTableName());
        slb.setTableLongName(S21CodeTableAccessor.getLongName(sib.getTableName()));

        return slb;
    }

    /**
     * 新旧のEZDTMsgのソートカラム設定値を比較したうえでソートカラムを
     * 考慮した更新処理を行う。<br>
     * @param columnNmae カラム名
     * @param newMsg 新EZDTMsg
     */
    private void updateSortNum(String columnNmae, EZDTMsg msg) {

        BigDecimal newVal = null;

        //S21CodeTableColumnInfo info = S21CodeTableAccessor.getColumnInfo(msg.getTableName(), columnNmae);
        S21CodeTableColumnInfo info = S21GenericTableAccessor.getColumnInfo(msg.getTableName(), columnNmae);
        

        // ソートキーは数値型のみ
        if (msg != null && info.getItemType() == S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
            newVal = msg.getValueBigDecimal(info.getKeyName(), -1);
        }

        // 数値以外であればソート値の更新は行わない
        if (newVal == null) {
            return;
        }

        // ソート値が使用されているかどうか確認
        Map<String, String> map = new HashMap<String, String>();
        map.put(columnNmae, newVal.toPlainString());
        //EZDTMsgArray finds = S21CodeTableAccessor.findByCondition(msg.getTableName(), map);
        EZDTMsgArray finds = S21GenericTableAccessor.findByCondition(msg.getTableName(), map);

        // 見つかる(重複するソートキーがある)場合は何もしない
        if (finds.length() != 1) {
            return;
        }

        // 移動
        S21CMCodetableReflectionInvoker.invokeDecSortNum(msg.getTableName(),
                                                        newVal.intValue());
    }

    /**
     * 削除するテーブル名称を取得。<br>
     * @param req S21NEContainerDataBean
     * @return テーブル名称
     * @see com.canon.cusa.s21.framework.generictable.ap.S21CMCacheRemoveBiz#getTableName(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected String getTableName(S21NEContainerDataBean req) {
        // 対象テーブル取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();
        return sib.getTableName();
    }

    /**
     * キーのみを保持するEZDTMsgからすべての値を保持するEZDTMsgを作成する。<br>
     * @param delMsgs 削除対象List
     * @return すべての値を保持するEZDTMsgのList
     */
    private List<EZDTMsg> findMsgs(List<EZDTMsg> delMsgs) {

        List<EZDTMsg> newMsgs = new ArrayList<EZDTMsg>();

        Iterator<EZDTMsg> it = delMsgs.iterator();

        while (it.hasNext()) {
            EZDTMsg msg = it.next();

            // 検索
            msg = S21GenericTableAccessor.findByKey(msg);

            // 実行前に削除されている
            if (msg == null) {
                throw new EZDAbendException(new S21NEValidatorException("ZZXL0005E", ""));
            }

            // リターンコードチェック
            checkReturnCode(msg);

            // リストへ追加
            newMsgs.add(msg);
        }

        return newMsgs;
    }

    /**
     * 削除対象EZDTMsgリストをソート値の降順にソートする。<br>
     * @param delMsgs 削除対象List
     * @return ソート結果List
     */
    private List<EZDTMsg> sortDelMsgs(String tableName, String colName, List<EZDTMsg> delMsgs) {
        TreeSet<EZDTMsg> ts = new TreeSet<EZDTMsg>(new CompDescSortVal(tableName, colName));
        ts.addAll(delMsgs);
        return new ArrayList<EZDTMsg>(ts);
    }

    /**
     * ソート値の降順にソートする。<br>
     * @author Administrator
     */
    private class CompDescSortVal implements Comparator<EZDTMsg> {

        /** ソートカラム名 */
        S21CodeTableColumnInfo info;

        /**
         * コンストラクタ。<br>
         * @param tableName テーブル名
         * @param sortColName ソートカラム名
         */
        public CompDescSortVal(String tableName, String sortColName) {

            info = S21GenericTableAccessor.getColumnInfo(tableName, sortColName);

            // ソートキーは数値型のみ
            if (info.getItemType() != S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
                info = null;
            }
        }

        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(EZDTMsg arg0, EZDTMsg arg1) {

            if (info == null) {
                return 0;
            }

            BigDecimal bd1 = arg0.getValueBigDecimal(info.getKeyName(), -1);
            BigDecimal bd2 = arg1.getValueBigDecimal(info.getKeyName(), -1);

            if (bd1 == null) { bd1 = new BigDecimal(-1); }
            if (bd2 == null) { bd2 = new BigDecimal(-1); }

            // 同値(0)の場合はオブジェクトが追加されない
            // ソート番号は必ずしもユニークではない可能性がある
            int comp = bd1.compareTo(bd2);
            if (comp == 0) {
                return -1;
            } else {
                return comp;
            }
        }
    }
}
