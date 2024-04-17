package com.canon.cusa.s21.framework.generictable.ap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContextHolder;

/**
 * コードメンテナンス画面検索共通処理。<br>
 * @author Administrator
 */
public class S21CMSearchCommon {

    /** Session Key */
    public static final String TABLE_LIST = "S21GM_TABLE_LIST";

    /** Session Key */
    public static final String TABLE_LONG_LIST = "S21GM_TABLE_LONG_LIST";

    /** Session Key */
    public static final String TABLE_VISIBLE_LIST = "S21GM_TABLE_VISIBLE_LIST";

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
    public static List<String> createTableList() {

    	List<String> tableShortName = null;
    	List tableInfoObj = S21GMDBAccessQuery.getInstance().getDefinedGmTableList();
    	
    	if (tableInfoObj != null) {
    		tableShortName = new ArrayList<String>();
    		for (Iterator iterator = tableInfoObj.iterator(); iterator.hasNext();) {
    			Map resultSet = (Map) iterator.next();
    			String tableName = (String)resultSet.get("GENL_TBL_NM");
    			tableShortName.add(tableName);
    		}
    	}
        // GENL_TBL_DFN テーブルに GENL_TBL_DFN テーブルの更新権限が設定されていない
        // 場合でも本機能が使用できるようにする。
        if (!tableShortName.contains("GENL_TBL_DFN")) {
            tableShortName.add("GENL_TBL_DFN");
        }
        return tableShortName;
    }

    /**
     * テーブル一覧に対応するロングネーム一覧を作成する。<br>
     * @param tables テーブル一覧
     * @return ロングネーム一覧
     */
    protected static List<String> createLongNameList(List<String> tables) {
    	List<String> tableLongName = new ArrayList<String>(tables.size());

        for (int i = 0; i < tables.size(); i++) {
            String longName = S21GMDBAccessQuery.getInstance().getGmTableLongName(tables.get(i));
            if (longName == null) {
                longName = "";
            }
            tableLongName.add(longName);
        }
        return tableLongName;
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
    
    /**
     * テーブルに設定されているコメント一覧を取得する
     * @param tables テーブル名
     * @return コメント一覧
     */
    protected static List<String> createCommentList(List<String> tables) {
    	List<String> commentList = new ArrayList<String>();
    	Map<String, String> commentMap = S21GMDBAccessQuery.getInstance().getTableCommentList();
    	
    	String comment;
    	for (int i=0; i<tables.size(); i++) {
    		comment = commentMap.get(tables.get(i));
    		if(comment == null) {
    			comment = "";
    		}
    		commentList.add(comment);
    	}
    	return commentList;
    	
    }
}
