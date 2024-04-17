package com.canon.cusa.s21.framework.generictable.ap;

/**
 * テーブル名を保持するBeanのテーブル名称アクセッサインタフェース。<br>
 * @author Administrator
 */
public interface S21CMTableBean {

    /**
     * @return tableName
     */
    public String getTableName();

    /**
     * @param tableName 設定する tableName
     */
    public void setTableName(String tableName);
}
