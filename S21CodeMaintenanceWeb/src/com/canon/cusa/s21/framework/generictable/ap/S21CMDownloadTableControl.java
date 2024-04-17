package com.canon.cusa.s21.framework.generictable.ap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canon.cusa.s21.framework.generictable.fw.S21NEConfig;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * コードメンテナンス画面 CSVダウンロードイベント。<br>
 * テーブル一覧選択イベント処理とほぼ同じ処理を行い、処理結果をCSVとして
 * ストリームへ出力する。<br>
 * @author Administrator
 */
public class S21CMDownloadTableControl extends S21CMSelectTableControl {

    /** HTML:CONTENT_TYPE */
    private static final String HTML_OCTET_UTF_8 = "application/octet-stream-dummy; charset=UTF-8";

    /** HTML:CONTENT-DISPOSITION */
    private static final String HTML_CONTENT_DISPOSITION = "Content-Disposition";

    /** HTML:ATTACHMENT FILENAME */
    private static final String HTML_ATTACHMENT_FILENAME = "attachment; filename=\"";

    /** HTML:ATTACHMENT FILENAME Closure */
    private static final String HTML_ATTACHMENT_FILENAME_CLOSURE = ".csv\"";

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void control(S21NEContainerDataBean bean) {

        // ダウンロードリクエストフラグ設定
        S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();
        sib.setDownload(true);

        // 業務処理起動
        fireBiz(bean);

        // 業務処理結果をコンテナのレスポンスから取得
        S21CMSearchListBean result = (S21CMSearchListBean) bean.getResponse();

        // 表示用Beanに詰め替え
        S21CMSearchScreenBean view = new S21CMSearchScreenBean();
        view.setTableNames(result.getTableNames());
        view.setTableLongNames(result.getTableLongNames());
        view.setAllRecord(result.getAllRecord());

        // 選択情報
        view.setTableName(result.getTableName());
        view.setTableLongName(result.getTableLongName());

        // 非EZDカラム情報を設定
        List<S21CodeTableColumnInfo> columns = chooseNoEzdColumns(result.getColInfo());
        view.setColInfos(columns);

        // EZDTMsgの内容を設定
        view.setValues(convertMsgArray2List(result.getMsgs(), columns));

        // レスポンスとして設定
        bean.setResponse(view);

    }

    /**
     * 次画面遷移。<br>
     * CSVをforwardするためS21NEBaseReqControl#succeedFwdをオーバライドする。<br>
     * CSVファイルを対象テーブル名.csvのファイル名で出力する。<br>
     * @param req リクエスト
     * @param resp レスポンス
     * @param bean リクエスト情報コンテナ
     * @throws ServletException
     * @throws IOException
     */
    protected void succeedFwd(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {

        S21CMSearchScreenBean view = (S21CMSearchScreenBean) bean.getResponse();

        boolean dblquotes = S21NEConfig.getInstance().isDispOption("cvs", "doublequotes", true);

        PrintWriter pw = resp.getWriter();

        // CSVヘッダーの作成
        writeCsvHeader(resp, pw, view);

        List<List<String>> msgs = view.getValues();

        // 値の書き込み
        writeMsgs(msgs, pw, dblquotes);

        int count = msgs.size();

        while (count < view.getAllRecord()) {

            pw.flush();

            // ダウンロードリクエストフラグ設定
            S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();

            // ページ更新
            sib.setViewPage(sib.getViewPage() + 1);

            // 業務処理起動
            fireBiz(bean);

            // 戻り値の編集
            S21CMSearchListBean result = (S21CMSearchListBean) bean.getResponse();
            List<S21CodeTableColumnInfo> columns = chooseNoEzdColumns(result.getColInfo());
            msgs = convertMsgArray2List(result.getMsgs(), columns);

            // 値の書き込み
            writeMsgs(msgs, pw, dblquotes);

            if (msgs.size() == 0) {
                break;
            }

            count += msgs.size();

        }

        // 後処理
        pw.flush();
        pw.close();
    }

    /**
     * 値をCSV形式で書き込み
     * @param msgs メッセージ
     * @param pw 書き込み先
     * @param dblquotes ダブルクォートフラグ
     */
    private void writeMsgs(List<List<String>> msgs, PrintWriter pw, boolean dblquotes) {

        // レコード出力
        Iterator<List<String>> itRec = msgs.iterator();
        while (itRec.hasNext()) {

            // 1レコードの情報を取得
            List<String> line = itRec.next();

            Iterator<String> itVal = line.iterator();
            while (itVal.hasNext()) {
                String val = itVal.next();

                if (val == null) {
                    val = "";
                }

                // "をエンクォートする
                if (val.indexOf("\"") >= 0) {
                    val = val.replace("\"", "\"\"");
                }

                if (dblquotes) {
                    pw.write("\"");
                }

                // カラム情報を出力する
                pw.write(val);

                if (dblquotes) {
                    pw.write("\"");
                }

                if (itVal.hasNext()) {
                    pw.write(",");
                }
            }

            // 改行
            pw.write("\r\n");
        }
    }

    /**
     * CSVヘッダの作成
     * @param resp
     * @param view
     * @return
     * @throws IOException
     */
    private void writeCsvHeader(HttpServletResponse resp, PrintWriter pw, S21CMSearchScreenBean view) throws IOException {

        resp.setContentType(HTML_OCTET_UTF_8);

        String attachment = HTML_ATTACHMENT_FILENAME + view.getTableName() + HTML_ATTACHMENT_FILENAME_CLOSURE;
        resp.setHeader(HTML_CONTENT_DISPOSITION, attachment);

        // カラム名出力
        Iterator<S21CodeTableColumnInfo> itCol = view.getColInfos().iterator();
        while (itCol.hasNext()) {
            S21CodeTableColumnInfo info = itCol.next();
            pw.write(info.getColumnName());
            if (itCol.hasNext()) {
                pw.write(",");
            }
        }

        // 改行
        pw.write("\r\n");
    }
}
