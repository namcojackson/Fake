package com.canon.cusa.s21.framework.generictable.ap;

import java.util.Enumeration;

/**
 * ダブルクォートを意識したStringTokenizeクラス。<br>
 * データ前後のスペースの除去は行わない。<br>
 * 解析するデータは改行を含まない1行のカンマ(",")で区切られたCSV形式文字列。<br>
 * 区切り文字はカンマ固定。<br>
 * ダブルクォートで囲まれた要素の場合のみエンクォートされたダブルクォートを
 * 通常のダブルクォートに変換する。<br>
 * また、ダブルクォートで囲まれている要素内のカンマは要素区切りとして判断しない。<br>
 * 要素がダブルクォートで始まっている場合、対応するダブルクォートまでを要素とする。<br>
 * 対応するダブルクォートが存在しない場合は、文字列の最後までを要素とする。<br>
 * 要素がダブルクォートで始まっていない場合、カンマまでを要素とする。<br>
 * @author Administrator
 */
public class S21CMCSVTokenizer implements Enumeration<String> {

    /** 解析もと文字列 */
    private String value;

    /** 解析インデックス */
    private int pos = 0;

    /** 解析データ有無 */
    private boolean hasData = true;

    /**
     * CSV Tokenizerインスタンスを作成する。<br>
     * @param csvline 解析する文字列
     */
    public S21CMCSVTokenizer(String csvline) {
        value = csvline;
    }

    /**
     * 次のエレメントが存在するか。<br>
     * @return 次のエレメントが存在するのであればtrue
     * @see java.util.Enumeration#hasMoreElements()
     */
    public boolean hasMoreElements() {
        return hasData;
    }

    /**
     * 次のエレメントを取得する。<br>
     * @return 次のエレメント
     * @see java.util.Enumeration#nextElement()
     */
    public String nextElement() {

        // 最後が空データに対する対応
        if (pos >= value.length()) {
            hasData = false;
            return "";
        }

        // 指定位置から最後までの文字列取得
        String val = value.substring(pos);

        String token;

        if (val.startsWith("\"")) {
            // ダブルクォートで囲まれた部分の切り出し
            token = getDoubleQuoteString(val);

            int idx = val.indexOf(",", token.length());

            if (idx == -1) {
                pos += token.length();
                hasData = false;
            } else {
                pos += idx + 1;
            }

            return pulloutDoubleQuote(token);

        } else {
            // ダブルクォートから開始していない
            // 次のカンマまでをトークンとする
            int idx = val.indexOf(",");

            if (idx == -1) {
                token = val;
                pos += token.length();
                hasData = false;
            } else {
                token = val.substring(0, idx);
                pos += idx + 1;
            }

            return token;
        }
    }

    /**
     * 先頭がダブルクォートの場合の文字列切り出し処理。<br>
     * カンマを意識せずダブルクォートで囲まれた部分を切り出す。<br>
     * ただし連続したダブルクォートはエンクォートとしてそのままとする。<br>
     * 文字列の最後までダブルクォートが現れない場合は、最後までを切り出す。<br>
     * @param val 入力文字
     * @return ダブルクォートで囲まれた範囲
     */
    private String getDoubleQuoteString(String val) {

        // ダブルクォートフラグ
        boolean finddq = false;

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            if (c == '\"') {
                if (i == 0) {
                    // ダブルクォートで始まっているのが前提なので先頭のダブルクォートは無視
                    finddq = false;
                } else if (!finddq) {
                    // 直前にダブルクォートのない場合はフラグだけ設定
                    finddq = true;
                } else {
                    // 直前にダブルクォートが出現した場合はフラグをリセット
                    finddq = false;
                }
            } else if (finddq) {
                // 直前にダブルクォートが出現し直近がダブルクォートでなければそこまでのデータを抜き出す
                return sb.toString();
            } else {
                // ダブルクォート以外で直前がダブルクォートでない場合は何もしない
                finddq = false;
            }

            // 読み込んだ一文字を設定
            sb.append(c);
        }

        // ダブルクォートで閉じていない場合は最後までを取りだす
        return sb.toString();
    }

    /**
     * ダブルクォートで囲まれた文字から有効な文字列を抜き取る。<br>
     * 先頭と最後のダブルクォートを取り、エンクォートされているダブルクォートは
     * 一つのダブルクォートに置換する。<br>
     * @param val ダブルクォートで囲まれている文字列
     * @return 有効な文字列
     */
    private String pulloutDoubleQuote(String val) {

        // 先頭と最後のダブルクォートを取る
        if (val.startsWith("\"") && val.endsWith("\"")) {
            val = val.substring(1, val.length() - 1);
        }

        // 並んでいる"を一つにする(""->")
        val = val.replace("\"\"", "\"");

        return val;
    }
}
