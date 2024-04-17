package com.canon.cusa.s21.framework.codetable.ap;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContextHolder;

/**
 * コードメンテナンス処理用 セキュリティ処理実装。<br>
 * @author Administrator
 */
public class S21CMSecurityAccessorImpl extends S21CMSecurityAccessor {

    private static final String CM_AUTH_TABLE = "CM_AUTH_TABLE";

    @Override
    public boolean isOpenCodeMaintenanceApp() {
        return true;
    }

    @Override
    public boolean isOpenCodeMaintenanceManagerApp() {
        return true;
    }

    @Override
    public boolean isTableRead(String tableName) {

//        S21CMAuthTable auth = getAuthTable();
//
//        return auth.isViewableTable(tableName);
        return true;
    }

    @Override
    public boolean isTableWrite(String tableName) {

//        S21CMAuthTable auth = getAuthTable();
//
//        return auth.isEditableTable(tableName);
        return true;
    }

    @Override
    public void clear() {
        S21NEContextHolder.getContext().setAttribute(CM_AUTH_TABLE, null);
    }

//    protected S21CMAuthTable getAuthTable() {
//
//        S21CMAuthTable auth = (S21CMAuthTable) S21NEContextHolder.getContext().getAttribute("CM_AUTH_TABLE");
//
//        if (auth == null) {
//            auth = new S21CMAuthTable();
//            S21NEContextHolder.getContext().setAttribute(CM_AUTH_TABLE, auth);
//        }
//
//        return auth;
//    }
}
