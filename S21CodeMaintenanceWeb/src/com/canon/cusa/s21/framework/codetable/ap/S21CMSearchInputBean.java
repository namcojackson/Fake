package com.canon.cusa.s21.framework.codetable.ap;

import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.codetable.fw.S21NEDataBean;

/**
 * コードメンテナンス画面 検索画面入力情報保持Bean。<br>
 * @author Administrator
 */
public class S21CMSearchInputBean implements S21NEDataBean, S21CMTableBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** テーブル選択 */
    private String tableName;

    /** テーブル検索キー */
    private String tableSearchKey;

    /** テーブル検索キー */
    private boolean searchLongName;

    /** テーブル選択条件 */
    private EZDTMsg condition;

    /** テーブル選択条件 */
    private Map<String, String> condMap;

    /** 削除テーブル選択 */
    private List<EZDTMsg> delRecs;

    /** 表示レコード件数 */
    private int recordCount;

    /** 表示ページ */
    private int viewPage;

    /** 条件検索での表示フラグ */
    private boolean condSearch;

    /** ダウンロードフラグ */
    private boolean download;

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
     * @return delRecs
     */
    public List<EZDTMsg> getDelRecs() {
        return delRecs;
    }

    /**
     * @param delRecs 設定する delRecs
     */
    public void setDelRecs(List<EZDTMsg> delRecs) {
        this.delRecs = delRecs;
    }

    /**
     * @return recordCount
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * @param recordCount 設定する recordCount
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
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
     * @return condMap
     */
    public Map<String, String> getCondMap() {
        return condMap;
    }

    /**
     * @param condMap 設定する condMap
     */
    public void setCondMap(Map<String, String> condMap) {
        this.condMap = condMap;
    }

    /**
     * @return
     */
    public boolean isDownload() {
        return download;
    }

    /**
     * @param download
     */
    public void setDownload(boolean download) {
        this.download = download;
    }

}
