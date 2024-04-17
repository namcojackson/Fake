package com.canon.cusa.s21.framework.codetable.ap;

import com.canon.cusa.s21.framework.codetable.fw.S21NEConfig;

/**
 * コードメンテナンス処理用 セキュリティ処理インタフェース。<br>
 * S21セキュリティ/認証処理に対する処理のインタフェースを定義する。<br>
 * 実装処理ではS21セキュリティ経由でログイン情報を取得するため、
 * 引数としてユーザ情報をアプリケーション側から渡すことはしない。<br>
 * @author Administrator
 */
public abstract class S21CMSecurityAccessor {

    /** プロパティのキー情報 */
    private static final String PROP_SECURITY = "security";

    /** 自身のインスタンス */
    private static S21CMSecurityAccessor instance = null;

    // インスタンス初期化
    static {
        try {
            String name = S21NEConfig.getInstance().getClassName(PROP_SECURITY);
            Class clazz = Class.forName(name);
            instance = (S21CMSecurityAccessor) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        } catch (InstantiationException e) {
            throw new ExceptionInInitializerError(e);
        } catch (IllegalAccessException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * インスタンス取得。<br>
     * @return インスタンス
     */
    public static S21CMSecurityAccessor getInstance() {
        return instance;
    }

    /**
     * コードメンテナンスアプリを使用可能であるかチェックする。<br>
     * @return 使用可否
     */
    public abstract boolean isOpenCodeMaintenanceApp();

    /**
     * コードメンテナンス管理を使用可能であるかチェックする。<br>
     * @return 使用可否
     */
    public abstract boolean isOpenCodeMaintenanceManagerApp();

    /**
     * 指定テーブルを参照可能であるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 参照可否
     */
    public abstract boolean isTableRead(String tableName);

    /**
     * 指定テーブルを更新可能であるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 更新可否
     */
    public abstract boolean isTableWrite(String tableName);

    /**
     * セキュリティ情報を消去する。<br>
     */
    public abstract void clear();
}
