package com.canon.cusa.s21.framework.codetable.ap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEException;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidatorException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 CSVファイルアップロード業務処理。<br>
 * @author Administrator
 */
public class S21CMUploadTableBiz extends S21CMCacheRemoveBiz {

    /** 文字コード(UTF-8固定) */
    private static final String CHAR_UTF_8 = "UTF-8";

    /** エラーメッセージ:ファイル設定なし */
    private static final String ERR_MSG_NO_FILE = "ZZM9014E";

    /** エラーフィールド名:ファイル指定テキストボックス */
    private static final String FIELD_UPLOAD = "Upload";

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 対象テーブル取得
        S21CMUploadTableBean utb = (S21CMUploadTableBean) req.getRequest();

        // 全データ削除
        recordDelete(utb.getTableName());

        // 全データ登録
        recordInsert(req, utb);
    }

    /**
     * テーブル名称取得。<br>
     * @param req S21NEContainerDataBean
     * @see com.canon.cusa.s21.framework.codetable.ap.S21CMCacheRemoveBiz#getTableName(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    @Override
    protected String getTableName(S21NEContainerDataBean req) {
        // 対象テーブル取得
        S21CMUploadTableBean utb = (S21CMUploadTableBean) req.getRequest();
        return utb.getTableName();
    }

    /**
     * テーブル内容の全削除。<br>
     * @param tableName テーブル名
     */
    private void recordDelete(String tableName) {
        S21CMCodetableReflectionInvoker.invokeDeleteAll(tableName);
    }

    /**
     * レコードの挿入。<br>
     * @param msgs 挿入対象レコードを保持するEZDTMsgArray
     */
    private void recordInsert(S21NEContainerDataBean bean, S21CMUploadTableBean utb) {
        InputStreamReader isr;

        try {
            isr = new InputStreamReader(utb.getInputstream(), CHAR_UTF_8);
        } catch (UnsupportedEncodingException e) {
            // あり得ない
            throw new S21NEException(e);
        }

        LineNumberReader lnr = new LineNumberReader(isr);

        String line;
        try {

            S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getNonEzdColumnInfos(utb.getTableName());

            // 1行読み捨て(カラム名行)
            line = lnr.readLine();

            // エラー通知用カウンタ(カラム名行の読み捨てがあるので1から)
            int count = 1;

            // 1行読み込み
            line = lnr.readLine();

            while (line != null) {

                // EZDTMsgの作成
                EZDTMsg msg = createEZDTMsg(utb.getTableName(), infos, line, bean, count);

                // 書き込み
                S21CMCodetableReflectionInvoker.invokeCreate(msg);

                // 1行読み込み
                line = lnr.readLine();

                count++;
            }

            if (count == 1) {
                // No Date
                bean.setField(FIELD_UPLOAD);
                throw new EZDAbendException(new S21NEValidatorException(ERR_MSG_NO_FILE, ""));
            }
        } catch (IOException e) {
            // inputストリームはオンメモリのため発生しない
            throw new S21NEException(e);
        }

    }

    /**
     * CSVの一行からEZDTMsgに設定する。<br>
     * @param tableName テーブル名
     * @param infos カラム情報配列
     * @param line CSVの1行
     * @param bean S21NEContainerDataBean(エラー情報設定用)
     * @param count 処理中行カウンタ(エラー情報設定用)
     * @return EZDTMsg
     */
    private EZDTMsg createEZDTMsg(String tableName, S21CodeTableColumnInfo[] infos, String line, S21NEContainerDataBean bean, int count) {

        // CSVをHashMapに展開する
        Map<String, String> mapMsg = line2map(infos, line);

        try {
            // 入力必須チェック
            S21CMBaseValidator.neccesaryCheck(tableName, bean, mapMsg);

            // HashMapをEZDTMsgに設定する
            return S21CMCommonRequestFunc.createEZDTMsg(tableName, bean, mapMsg);

        } catch (EZDAbendException e) {
            String field = bean.getField();
            bean.setField("Line=" + (count + 1) + "," + field);
            throw e;
        }
    }

    /**
     * CSVの一行から要素を抜き出し、カラム名をキーにMapへ設定する。<br>
     * CSVの要素の並び順はEZDTMsgに定義された順とする。<br>
     * @param tableName テーブル名称
     * @param infos カラム情報配列
     * @param line CSVの1行
     * @return Map
     */
    private Map<String, String> line2map(S21CodeTableColumnInfo[] infos, String line) {

        Map<String, String> map = new HashMap<String, String>(infos.length);

        S21CMCSVTokenizer csvtokenizer = new S21CMCSVTokenizer(line);

        for (int i = 0; i < infos.length; i++) {
            S21CodeTableColumnInfo info = infos[i];

            // 要素を取得
            String val = csvtokenizer.nextElement();

            // カラム名をキーにMapへ設定
            map.put(info.getColumnName(), val);
        }

        return map;
    }
}
