package com.canon.cusa.s21.framework.generictable.ap;

/**
 * コードメンテナンス処理用 セキュリティ処理ダミー実装。<br>
 * @author Administrator
 */
public class S21CMSecurityAccessorStub extends S21CMSecurityAccessor {

    /**
     * コンストラクタ。<br>
     */
    protected S21CMSecurityAccessorStub() {
    }

    /**
     * コードメンテナンスアプリを使用可能であるかチェックする。<br>
     * @return 使用可否
     */
    public boolean isOpenCodeMaintenanceApp() {
        return true;
    }

    /**
     * コードメンテナンス管理を使用可能であるかチェックする。<br>
     * @return 使用可否
     */
    public boolean isOpenCodeMaintenanceManagerApp() {
        return true;
    }

    /**
     * 指定テーブルを参照可能であるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 参照可否
     */
    public boolean isTableRead(String tableName) {
        return true;
    }

    /**
     * 指定テーブルを更新可能であるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 更新可否
     */
    public boolean isTableWrite(String tableName) {
        return true;
    }

    @Override
    public void clear() {
    }

}
