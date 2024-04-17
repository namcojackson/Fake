package com.canon.cusa.s21.framework.codetable.ap;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDCommonConst;
import parts.common.EZDValidatorException;
import parts.dbcommon.EZDConnectionMgr;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidator;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * 入力チェック処理のベースクラス。<br>
 * 共通的に使用するメソッドを実装。<br>
 * @author Administrator
 */
public abstract class S21CMBaseValidator implements S21NEValidator {

    /** METAデータ NULL属性カラム */
    private static final String NULLABLE = "NULLABLE";

    /** null情報キャッシュ */
    private static Map<String, Map<String, Boolean>> nullCache = new HashMap<String, Map<String, Boolean>>();

    /**
     * 必須チェック。<br>
     * 入力必須項目に値が設定されているかチェックする。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param mapMsg Mapに展開した入力内容
     */
    protected static void neccesaryCheck(String tableName, S21NEContainerDataBean bean, Map<String, String> mapMsg) {
        neccesaryCheck(tableName, bean, mapMsg, null);
    }

    /**
     * 必須チェック。<br>
     * 入力必須項目に値が設定されているかチェックする。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param mapMsg Mapに展開した入力内容
     */
    protected static void neccesaryCheckExcludeSortCol(String tableName, S21NEContainerDataBean bean, Map<String, String> mapMsg) {

        // 非EZDカラム情報取得
        S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getNonEzdColumnInfos(tableName);

        // 除外リストを作成する
        List<String> sortCols = new ArrayList<String>();

        for (int i = 0; i < infos.length; i++) {
            if (infos[i].isS21sort()) {
                sortCols.add(infos[i].getColumnName());
            }
        }

        // 除外リスト付き必須チェックを行う
        neccesaryCheck(tableName, bean, mapMsg, sortCols);
    }

   /**
     * 必須チェック。<br>
     * 入力必須項目に値が設定されているかチェックする。<br>
     * 必須であるかどうかはJDBCを使用し、DBへ問い合わせを行う。<br>
     * ソートカラムなどのDB上は入力必須であるが、システム的な補完を行う
     * ために入力を省略可能なカラムは、excludeColsにカラム名を設定する。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param mapMsg Mapに展開した入力内容
     * @param excludeCols チェック除外カラム名リスト
     */
    protected static void neccesaryCheck(String tableName, S21NEContainerDataBean bean, Map<String, String> mapMsg, List<String> excludeCols) {

        // 非EZDカラム情報取得
        S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getNonEzdColumnInfos(tableName);

        // nullチェック情報
        Map<String, Boolean> nullCheckMap = createNullCheckInfo(tableName, infos);

        // nullチェック情報が作成できなければチェックしない
        if (nullCheckMap == null) {
            return;
        }

        for (int i = 0; i < infos.length; i++) {

            S21CodeTableColumnInfo info = infos[i];

            // 無視カラムリストのチェック
            if (excludeCols != null && excludeCols.contains(info.getColumnName())) {
                continue;
            }

            // nullを許すかどうかのフラグを取得
            boolean nullable = nullCheckMap.get(info.getColumnName());

            if (!nullable) {
                // 処理中カラム名
                // エラー処理にてエラー発生元特定のために設定する
                bean.setField(info.getColumnName());

                // nullを許さない場合は、入力されているかどうかをチェックする
                if (!mapMsg.containsKey(info.getColumnName())
                    || mapMsg.get(info.getColumnName()).trim().equals("")) {
                    // 入力必須エラー
                    throw new EZDAbendException(new EZDValidatorException(info.getColumnName(), EZDCommonConst.ERROR_HISSU));
                }
            }
        }
    }

    /**
     * nullを許可するかどうかをカラム単位に保持するMapを作成する。<br>
     * Mapのキーはカラム名、valueはnullを許可するかどうかのbool値。<br>
     * bool値はtrue=nullを許可、false=nullを許可しない。<br>
     * @param tableName テーブル名称
     * @param infos カラム情報配列
     * @return nullを許可するかどうかをカラム単位に保持するMap
     */
    protected static Map<String, Boolean> createNullCheckInfo(String tableName, S21CodeTableColumnInfo[] infos) {

        // EZDTMsgの必須項目はすべてNOが設定されており入力必須チェックとして
        // 使用できない
        // このためJDBCを使用してテーブルのカラム情報を取得し、
        // ISNULL属性を取得し、null不可であれば入力必須と判断する

        // テーブル単位のキャッシュから取得
        Map<String, Boolean> map = nullCache.get(tableName);

        if (map != null) {
            return map;
        }

        map = new HashMap<String, Boolean>(infos.length);

        Connection conn = EZDConnectionMgr.getInstance().getConnection();

        try {
            // メタデータ取得
            DatabaseMetaData meta = conn.getMetaData();

            for (int i = 0; i < infos.length; i++) {
                S21CodeTableColumnInfo info = infos[i];

                // カラム情報を取得
                ResultSet rs = meta.getColumns(null, null, tableName, info.getColumnName());

                // 先頭レコードのみをチェック
                rs.next();

                // NULL値を許可しているかチェック
                // NULL可であればtrue
                boolean nullable = (rs.getInt(NULLABLE) == DatabaseMetaData.attributeNullable);

                // 許可情報を設定
                map.put(info.getColumnName(), nullable);

                rs.close();
            }

            // キャッシュに登録
            nullCache.put(tableName, map);

            return map;

        } catch (SQLException e) {
            // 情報取得できないときは空のnullをreturn
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // 情報取得できないときは空のnullをreturn
                return null;
            }
        }
    }
}
