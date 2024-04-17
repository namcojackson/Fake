package com.canon.cusa.s21.framework.generictable.ap;

import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.generictable.fw.S21NEDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * 検索処理結果表示用Bean。<br>
 * @author Administrator
 */
public class S21CMSearchListBean implements S21NEDataBean, S21CMTableBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 選択テーブル名 */
    private String tableName;

    /** 選択テーブルロングネーム */
    private String tableLongName;

    /** 選択テーブル情報 */
    private List<S21CodeTableColumnInfo> colInfo;

    /** テーブル一覧(テーブル名) */
    private List<String> tableNames;

    /** テーブル一覧(ロングネーム) */
    private List<String> tableLongNames;

    /** 検索条件 */
    private EZDTMsg condition;

    /** 検索結果 */
    private EZDTMsgArray msgs;

    /** カラム情報 */
    private List<S21CodeTableColumnInfo> columns;

    /** 検索テーブル一覧(テーブル名) */
    private List<String> searchTableNames;

    /** 検索テーブル一覧(ロングネーム) */
    private List<String> searchTableLongNames;

    /** 検索テーブル一覧(変更可否フラグ) */
    private List<Boolean> searchTableVisible;

    /** 表示ページ */
    private int viewPage;

    /** 全件数 */
    private int allRecord;
    
    /** カラムコメント情報 */
    private Map<String, String> columnComment;
    
    /** 1ページあたりの表示件数 */
    private int displayNumber;
    
    /** 検索テーブル一覧(コメント) */
    private List<String> searchTableComments;

    /**
     * @return colInfo
     */
    public List<S21CodeTableColumnInfo> getColInfo() {
        return colInfo;
    }

    /**
     * @param colInfo 設定する colInfo
     */
    public void setColInfo(List<S21CodeTableColumnInfo> colInfo) {
        this.colInfo = colInfo;
    }

    /**
     * @return condition
     */
    public EZDTMsg getCondition() {
        return condition;
    }

    /**
     * @param condition 設定する condition
     */
    public void setCondition(EZDTMsg condition) {
        this.condition = condition;
    }

    /**
     * @return msgs
     */
    public EZDTMsgArray getMsgs() {
        return msgs;
    }

    /**
     * @param msgs 設定する msgs
     */
    public void setMsgs(EZDTMsgArray msgs) {
        this.msgs = msgs;
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
     * @return columns
     */
    public List<S21CodeTableColumnInfo> getColumns() {
        return columns;
    }

    /**
     * @param columns 設定する columns
     */
    public void setColumns(List<S21CodeTableColumnInfo> columns) {
        this.columns = columns;
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
     * @param searchTableUpdatable 設定する searchTableUpdatable
     */
    public void setSearchTableVisible(List<Boolean> searchTableUpdatable) {
        this.searchTableVisible = searchTableUpdatable;
    }

    /**
     * @return viewPage
     */
    public int getViewPage() {
        return viewPage;
    }

    /**
     * @param viewPage 設定する viewPage
     */
    public void setViewPage(int viewPage) {
        this.viewPage = viewPage;
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
    
    /**
     * カラムコメントを取得する
     * @return
     */
    public Map<String, String> getColumnComment() {
        return columnComment;
    }

    /**
     * カラムコメントを設定する
     * @param columnComment カラムコメント
     */
    public void setColumnComment(Map<String, String> columnComment) {
        this.columnComment = columnComment;
    }
    
    /**
     * 1ページあたりの表示件数を設定する
     * @param displayNumber
     */
    public void setDisplayNumber(int displayNumber) {
    	this.displayNumber = displayNumber;
    }
    
    /**
     * 1ページあたりの表示件数を取得する
     * @return
     */
    public int getDisplayNumber() {
    	return this.displayNumber;
    }
    
    /**
     * @return searchTableComments
     */
    public List<String> getSearchTableComments() {
        return searchTableComments;
    }

    /**
     * @param searchTableLongNames 設定する searchTableLongNames
     */
    public void setSearchTableComments(List<String> searchTableComments) {
        this.searchTableComments = searchTableComments;
    }

}
