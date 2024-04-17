package com.canon.cusa.s21.framework.generictable.fw;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import parts.common.EZDStringUtil;

/**
 * @author Administrator
 */
public class S21NEContainerUploadDataBean extends S21NEContainerDataBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** Content-Type */
    private static final String CONTENT_TYPE = "Content-Type";

    /** Content-Disposition */
    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    /** boundary= */
    private static final String BOUNDARY = "boundary=";

    /** name=\" */
    private static final String NAME = "name=\"";

    /** 受信バッファ */
    private static final int BUFF_SIZE = 4096;

    /** ファイルアップロード情報 */
    private InputStream inputStream = null;

    /** コンテンツ境界文字列 */
    private String startBoundary;

    /** コンテンツ境界文字列 */
    private String endBoundary;

    private String fileName = null;

    /**
     * コンストラクタ。<br>
     * リクエストから共通的な情報を抜き出し設定する。<br>
     * @param req condition
     */
    public S21NEContainerUploadDataBean(HttpServletRequest req) {

        try {
            // ヘッダ解析
            parseHeader(req);

            // HttpRequest解析
            parse(req.getInputStream(), req.getContentLength());

        } catch (IOException e) {
            throw new S21NEException(e);
        }

        // リクエストIDを設定
        this.setReqID(getReq(S21NERequestDef.REQ_REQ_ID));

        // 画面IDを設定
        this.setScreenID(getReq(S21NERequestDef.REQ_SCREEN));
    }

    /**
     * @return is
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * HttpRequestのヘッダからboundaryキーを取得。<br>
     * @param req
     */
    private void parseHeader(HttpServletRequest req) {
        String content = req.getContentType();
        int idx = content.indexOf(BOUNDARY) + BOUNDARY.length();

        String boundary = content.substring(idx);
        startBoundary = "--" + boundary;
        endBoundary = "--" + boundary + "--";
    }

    /**
     * マルチパートリクエストを解析する。<br>
     * ファイルは一つだけに対応。<br>
     * @param is ServletInputStream
     * @throws IOException
     */
    private void parse(ServletInputStream is, int contLen) throws IOException {

        // Content解析中フラグ
        boolean content = false;

        // ファイル解析中フラグ
        boolean file = false;

        // パラメータ解析中フラグ
        boolean param = false;

        // 読み飛ばし
        boolean skipBlankLine = false;

        // 開業読み飛ばし
        boolean skipCRLF = false;

        // 読み込みバッファ
        byte[] buff = new byte[BUFF_SIZE];

        // リクエスト情報保持用
        Map<String, String[]> map = new HashMap<String, String[]>();

        // パラメータ用
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // File出力用
        OutputStream uploados;

        // 最大ファイルサイズ
        long memLength = S21NEConfig.getInstance().getDispOptionLong("cvs", "maxmemsize", 1000000);

        // 0はとりあえず
        if (contLen > memLength) {
            fileName = EZDStringUtil.getTempFilePath(null, null, null, S21NEConfig.getInstance().getTmpDir(), null, true);
            uploados = new FileOutputStream(fileName);
        } else {
            // ファイル情報保持用
            uploados = new ByteArrayOutputStream();
        }

        // 解析中コンテキスト名
        String name = "";

        int len;
        while ((len = is.readLine(buff, 0, BUFF_SIZE)) >= 0) {

            String line = new String(buff, 0, len);

            if (line.indexOf(startBoundary) >= 0) {

                // コンテンツ終了

                if (file) {
                    // ファイル情報

                    if (uploados instanceof ByteArrayOutputStream) {
                        ByteArrayOutputStream os = (ByteArrayOutputStream) uploados;

                        byte[] b = os.toByteArray();

                        if (b.length > 0) {
                            inputStream = new ByteArrayInputStream(b, 0, b.length);
                        }
                    } else {
                        uploados.flush();
                        uploados.close();
                        inputStream = new FileInputStream(fileName);
                    }

                    skipCRLF = false;

                } else if (param) {

                    // パラメータ情報

                    String val = baos.toString();

                    // 末尾の改行削除(改行はCR+LF)
                    val = val.substring(0, val.length() - 2);

                    // リクエスト情報として設定
                    map.put(name, new String[] {val});

                } else {
                    // 解析開始
                    content = true;
                }

                // 解析フラグリセット
                file = false;
                param = false;

            } else if (content) {

                // コンテンツ解析

                if ((file || param) && !skipBlankLine) {
                    // ファイル情報読み込み
                    // パラメータ情報読み込み

                    if (file) {
                        // 改行のみの行はすぐには書き込まない
                        if (skipCRLF) {
                            uploados.write('\r');
                            uploados.write('\n');
                            skipCRLF = false;
                        }
                        if (line.equals("\r\n")) {
                            skipCRLF = true;
                        } else {
                            // アップロード用ストリームに出力
                            uploados.write(buff, 0, len);
                        }
                    } else if (param) {
                        // コンテンツ用ストリームに出力
                        baos.write(buff, 0, len);
                    }

                } else if (line.startsWith(CONTENT_DISPOSITION)) {
                    // 出力情報格納用インスタンス生成
                    baos = new ByteArrayOutputStream();

                    int idx1 = line.indexOf(NAME) + NAME.length();
                    int idx2 = line.indexOf("\"", idx1 + 1);
                    if (idx1 >= 0 && idx2 >= 0) {
                        name = line.substring(idx1, idx2);
                    } else {
                        name = "";
                    }

                    // ファイル名は無視

                    // パラメータとして解析
                    param = true;

                    // 先頭にある改行をスキップ
                    skipBlankLine = true;

                } else if (line.startsWith(CONTENT_TYPE)) {

                    // Content-Type指定があればファイルとして解析
                    file = true;
                    param = false;

                } else if (skipBlankLine) {
                    skipBlankLine = false;
                }
            }
        }

        // リクエスト情報の設定
        this.setReq(map);
    }

    /**
     * @return
     */
    public String getFileName() {
        return fileName;
    }
}
