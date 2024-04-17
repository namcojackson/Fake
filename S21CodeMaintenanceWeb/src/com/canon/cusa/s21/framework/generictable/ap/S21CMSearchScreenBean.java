package com.canon.cusa.s21.framework.generictable.ap;

import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.generictable.fw.S21NEDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * 検索画面表示用Bean。<br>
 * @author Administrator
 */
public class S21CMSearchScreenBean implements S21NEDataBean, S21CMTableBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    // 共通
    /** 選択テーブル名 */
    private String tableName;

    /** 選択テーブルロングネーム */
    private String tableLongName;

    /** テーブル一覧(テーブル名) */
    private List<String> tableNames;

    /** テーブル一覧(ロングネーム) */
    private List<String> tableLongNames;

    // include制御
    /** 検索条件表示 */
    private boolean showCondition;

    /** 検索結果表示 */
    private boolean showResult;

    /** 更新可能表示 */
    private boolean updatable;

    /** テーブルリスト表示 */
    private boolean tableSearchList;

    // 検索用
    /** カラム名一覧 */
    private List<S21CodeTableColumnInfo> colInfos;

    /** レコード情報 */
    private List<List<String>> values;

    /** 削除フラグ */
    private List<Boolean> delList;

    /** 検索条件 */
    private Map<String, String> condition;

    /** 検索条件(エラーとなっていない検索条件) */
    private Map<String, String> conditionOld;

    /** 現在表示ページ */
    private int page;

    /** 現在全ページ */
    private int pageAll;

    // テーブル検索用
    /** テーブル検索キー */
    private String tableSearchKey;

    /** テーブル検索キー */
    private boolean searchLongName;

    /** 検索テーブル一覧(テーブル名) */
    private List<String> searchTableNames;

    /** 検索テーブル一覧(ロングネーム) */
    private List<String> searchTableLongNames;

    /** 検索テーブル一覧(変更可否フラグ) */
    private List<Boolean> searchTableVisible;

    /** 条件検索での表示フラグ */
    private boolean condSearch;

    /** 全件数 */
    private int allRecord;
    
    /** カラムコメント */
    private Map<String, String> columnComment;
    
    /** 1ページあたりの表示件数 */
    private int displayNumber;
    
    /** 検索テーブル一覧(コメント) */
    private List<String> searchTableComments;
    
    /** テーブル検索方式 */
    private String searchType;

    /**
     * @return condSearch
     */
    public boolean isCondSearch() {
        return condSearch;
    }

    /**
     * @param condSearch 設定する condSearch
     */
    public void setCondSearch(boolean condSearch) {
        this.condSearch = condSearch;
    }

    /**
     * @return showCondition
     */
    public boolean isShowCondition() {
        return showCondition;
    }

    /**
     * @param showCondition 設定する showCondition
     */
    public void setShowCondition(boolean showCondition) {
        this.showCondition = showCondition;
    }

    /**
     * @return showResult
     */
    public boolean isShowResult() {
        return showResult;
    }

    /**
     * @param showResult 設定する showResult
     */
    public void setShowResult(boolean showResult) {
        this.showResult = showResult;
    }

    /**
     * @return tableLongName
     */
    public String getTableLongName() {
        return tableLongName;
    }

    /**
     * @param tableLongName 設定する tableLongName
     */
    public void setTableLongName(String tableLongName) {
        this.tableLongName = tableLongName;
    }

    /**
     * @return tableLongNames
     */
    public List<String> getTableLongNames() {
        return tableLongNames;
    }

    /**
     * @param tableLongNames 設定する tableLongNames
     */
    public void setTableLongNames(List<String> tableLongNames) {
        this.tableLongNames = tableLongNames;
    }

    /**
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName 設定する tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return tableNames
     */
    public List<String> getTableNames() {
        return tableNames;
    }

    /**
     * @param tableNames 設定する tableNames
     */
    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    /**
     * @return updatable
     */
    public boolean isUpdatable() {
        return updatable;
    }

    /**
     * @param updatable 設定する updatable
     */
    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    /**
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page 設定する page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return pageAll
     */
    public int getPageAll() {
        return pageAll;
    }

    /**
     * @param pageAll 設定する pageAll
     */
    public void setPageAll(int pageAll) {
        this.pageAll = pageAll;
    }

    /**
     * @return values
     */
    public List<List<String>> getValues() {
        return values;
    }

    /**
     * @param values 設定する values
     */
    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    /**
     * @return colInfos
     */
    public List<S21CodeTableColumnInfo> getColInfos() {
        return colInfos;
    }

    /**
     * @param colInfos 設定する colInfos
     */
    public void setColInfos(List<S21CodeTableColumnInfo> colInfos) {
        this.colInfos = colInfos;
    }

    /**
     * @return condition
     */
    public Map<String, String> getCondition() {
        return condition;
    }

    /**
     * @param condition 設定する condition
     */
    public void setCondition(Map<String, String> condition) {
        this.condition = condition;
    }

    /**
     * @return searchTableLongNames
     */
    public List<String> getSearchTableLongNames() {
        return searchTableLongNames;
    }

    /**
     * @param searchTableLongNames 設定する searchTableLongNames
     */
    public void setSearchTableLongNames(List<String> searchTableLongNames) {
        this.searchTableLongNames = searchTableLongNames;
    }

    /**
     * @return searchTableNames
     */
    public List<String> getSearchTableNames() {
        return searchTableNames;
    }

    /**
     * @param searchTableNames 設定する searchTableNames
     */
    public void setSearchTableNames(List<String> searchTableNames) {
        this.searchTableNames = searchTableNames;
    }

    /**
     * @return searchTableUpdatable
     */
    public List<Boolean> getSearchTableVisible() {
        return searchTableVisible;
    }

    /**
     * @param searchTableVisible 設定する searchTableVisible
     */
    public void setSearchTableVisible(List<Boolean> searchTableVisible) {
        this.searchTableVisible = searchTableVisible;
    }

    /**
     * @return tableList
     */
    public boolean isTableSearchList() {
        return tableSearchList;
    }

    /**
     * @param tableList 設定する tableList
     */
    public void setTableSearchList(boolean tableList) {
        this.tableSearchList = tableList;
    }

    /**
     * @return searchLongName
     */
    public boolean isSearchLongName() {
        return searchLongName;
    }

    /**
     * @param searchLongName 設定する searchLongName
     */
    public void setSearchLongName(boolean searchLongName) {
        this.searchLongName = searchLongName;
    }

    /**
     * @return tableSearchKey
     */
    public String getTableSearchKey() {
        return tableSearchKey;
    }

    /**
     * @param tableSearchKey 設定する tableSearchKey
     */
    public void setTableSearchKey(String tableSearchKey) {
        this.tableSearchKey = tableSearchKey;
    }

    /**
     * @return delList
     */
    public List<Boolean> getDelList() {
        return delList;
    }

    /**
     * @param delList 設定する delList
     */
    public void setDelList(List<Boolean> delList) {
        this.delList = delList;
    }

    /**
     * @return conditionOld
     */
    public Map<String, String> getConditionOld() {
        return conditionOld;
    }

    /**
     * @param conditionOld 設定する conditionOld
     */
    public void setConditionOld(Map<String, String> conditionOld) {
        this.conditionOld = conditionOld;
    }

    /**
     * @return
     */
    public int getAllRecord() {
        return allRecord;
    }

    /**
     * @param allRecord
     */
    public void setAllRecord(int allRecord) {
        this.allRecord = allRecord;
    }
    
    /** カラムコメントのゲッター
     * 
     * @return
     */
    public Map getColumnComment() {
    	return this.columnComment;
    }
    
    /**
     * カラムコメントのセッター
     * @param columnComment
     */
    public void setColumnComment(Map columnComment) {
    	this.columnComment = columnComment;
    }
    
    /**
     * 1ページあたりの表示件数を取得する
     * @return
     */
    public int getDisplayNumber() {
    	return this.displayNumber;
    }
    
    /**
     * 1ページあたりの表示件数を設定する
     * @param displayNumber
     */
    public void setDisplayNumber(int displayNumber) {
    	this.displayNumber = displayNumber;
    }
    
    /**
     * テーブルのコメント一覧を取得する
     * @return コメント一覧
     */
    public List<String> getSearchTableComments() {
        return this.searchTableComments;
    }

    /**
     * テーブルのコメント一覧を設定する
     * @param searchTableComments コメント一覧
     */
    public void setSearchTableComments(List<String> searchTableComments) {
        this.searchTableComments = searchTableComments;
    }
    
    /**
     * テーブルの検索方式を取得する
     * @return
     */
    public String getSearchType() {
    	return this.searchType;
    }
    
    /**
     * テーブルの検索方式を設定する
     * @param searchType
     */
    public void setSearchType(String searchType) {
    	this.searchType = searchType;
    }
}
