package com.canon.cusa.s21.framework.codetable.ap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContextHolder;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面検索共通処理。<br>
 * @author Administrator
 */
public class S21CMSearchCommon {

    /** Session Key */
    public static final String TABLE_LIST = "S21CM_TABLE_LIST";

    /** Session Key */
    public static final String TABLE_LONG_LIST = "S21CM_TABLE_LONG_LIST";

    /** Session Key */
    public static final String TABLE_VISIBLE_LIST = "S21CM_TABLE_VISIBLE_LIST";

    /**
     * リスト情報消去。<br>
     */
    protected static void resetList() {
        S21NEContextHolder.getContext().setAttribute(TABLE_LIST, null);
        S21NEContextHolder.getContext().setAttribute(TABLE_LONG_LIST, null);
        S21NEContextHolder.getContext().setAttribute(TABLE_VISIBLE_LIST, null);
        S21CMSecurityAccessor.getInstance().clear();
    }

    /**
     * 検索画面業務処理結果保持クラスのインスタンスを作成する。<br>
     * インスタンスを生成し各画面間で共通の情報を設定する。<br>
     * @param req リクエスト情報
     * @return S21CMSearchListBean
     */
    protected static S21CMSearchListBean createSearchListBean() {
        return createSearchListBean(true);
    }

    /**
     * 検索画面業務処理結果保持クラスのインスタンスを作成する。<br>
     * インスタンスを生成し各画面間で共通の情報を設定する。<br>
     * @param req リクエスト情報
     * @param makeList リスト作成
     * @return S21CMSearchListBean
     */
    protected static S21CMSearchListBean createSearchListBean(boolean makeList) {

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = new S21CMSearchListBean();

        if (makeList) {
            // テーブル一覧を作成
            slb.setTableNames(S21CMSearchCommon.createTableList());

            // ロングネームを作成
            slb.setTableLongNames(S21CMSearchCommon.createLongNameList(slb.getTableNames()));
        }

        return slb;
    }

    /**
     * ログインユーザの参照可能なテーブル一覧を作成する。<br>
     * @param req S21NEContainerDataBean
     * @return 参照可能なテーブル一覧
     */
    private static List<String> createTableList() {

        // Auth処理改善のためコメントアウト
//        // コンテキストからテーブル一覧取得
//        List<String> list = (List<String>) S21NEContextHolder.getContext().getAttribute(TABLE_LIST);
//        if (list != null) {
//            return list;
//        }

        // 登録されているテーブル一覧を取得
        List<String> listBase = S21CodeTableAccessor.getCodeTableList();

        List<String> listTable = new ArrayList<String>();

        Iterator<String> it = listBase.iterator();

        // ユーザが使用可能であるかチェック
        while (it.hasNext()) {

            // テーブル名取得
            String tableName = it.next();

            // セキュリティを使用してテーブル読み書きフラグをチェックする
            // 書き込み可は読み込み可として扱われるため個別にチェックしない
            if (S21CMSecurityAccessor.getInstance().isTableRead(tableName)) {
                listTable.add(tableName);
            }
        }

        // Auth処理改善のためコメントアウト
//        // コンテキストにテーブル一覧設定
//        S21NEContextHolder.getContext().setAttribute(TABLE_LIST, listTable);

        return listTable;
    }

    /**
     * テーブル一覧に対応するロングネーム一覧を作成する。<br>
     * @param tables テーブル一覧
     * @return ロングネーム一覧
     */
    protected static List<String> createLongNameList(List<String> tables) {

        // Auth処理改善のためコメントアウト
//        // コンテキストからテーブル一覧取得
//        List<String> list = (List<String>) S21NEContextHolder.getContext().getAttribute(TABLE_LONG_LIST);
//        if (list != null) {
//            return list;
//        }

        List<String> lnames = new ArrayList<String>(tables.size());

        for (int i = 0; i < tables.size(); i++) {
            // ロングネーム取得
            String longName = S21CodeTableAccessor.getLongName(tables.get(i));

            if (longName == null) {
                longName = "";
            }

            lnames.add(longName);
        }

        // Auth処理改善のためコメントアウト
//        // コンテキストにテーブル一覧設定
//        S21NEContextHolder.getContext().setAttribute(TABLE_LONG_LIST, lnames);

        return lnames;
    }

    /**
     * テーブル一覧に対応する参照可否一覧を作成する。<br>
     * @param tables テーブル一覧
     * @return 参照可否一覧
     */
    protected static List<Boolean> createVisibleTableList(List<String> tables) {

        // Auth処理改善のためコメントアウト
//        // コンテキストからテーブル一覧取得
//        List<Boolean> list = (List<Boolean>) S21NEContextHolder.getContext().getAttribute(TABLE_VISIBLE_LIST);
//        if (list != null) {
//            return list;
//        }

        List<Boolean> visibles = new ArrayList<Boolean>(tables.size());

        for (int i = 0; i < tables.size(); i++) {
            String tableName = tables.get(i);

            // セキュリティを使用してテーブル読み書きフラグをチェックする
            // 書き込み可は読み込み可として扱われるため個別にチェックしない
            if (S21CMSecurityAccessor.getInstance().isTableRead(tableName)) {
                visibles.add(new Boolean(true));
            } else {
                visibles.add(new Boolean(false));
            }
        }

        // Auth処理改善のためコメントアウト
//        // コンテキストにテーブル一覧設定
//        S21NEContextHolder.getContext().setAttribute(TABLE_VISIBLE_LIST, visibles);

        return visibles;
    }
}
