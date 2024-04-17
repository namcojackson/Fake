package com.canon.cusa.s21.framework.codetable.fw;

import java.io.Serializable;

/**
 * コードメンテナンス用Beanインタフェース。<br>
 * @author Administrator
 */
public interface S21NEDataBean extends Serializable {
        
    /**
     * @return tableName
     */
    public String getTableName();

    /**
     * @param tableName 設定する tableName
     */
    public void setTableName(String tableName);
}
