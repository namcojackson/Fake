package com.canon.cusa.s21.framework.codetable.ap;

import parts.common.EZDAbendException;
import parts.common.EZDLog;
import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDDBRetryRequestException;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBaseReqControl;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;

/**
 * コードメンテ画面 プレゼンテーションコントロール 共通処理。<br>
 * @author Administrator
 */
public abstract class S21CMBaseControl extends S21NEBaseReqControl {

    /** その他異常 */
    private static final String OTHER_ERROR = "ZZM9501E";

    /** Classファイル存在せず */
    private static final String CLASS_NOT_FOUND = "ZZXL0003E";

    /** XMLファイル存在せず */
    private static final String XML_NOT_FOUND = "ZZXL0004E";

    /** TMsgクラスとDB定義がアンマッチ */
    private static final String CONTRADICT_TABLE_DEF = "ZZXL0007E";

    /** EZDAbendに設定されているメッセージ(エラー変換用) */
    private static final String EZ_ABEND_XML_NOT_FOUND = "The XML file doesn't exist.";

    /**
     * システムエラー処理を行う。<br>
     * @param bean S21NEContainerDataBean
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEBaseReqControl#sysError(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void sysError(S21NEContainerDataBean bean) {

        // Exception退避
        Exception exp = bean.getExp();

        try {
            // 検索処理結果保持Beanの作成
            S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

            // 表示用Beanに詰め替え
            S21CMSearchScreenBean view = new S21CMSearchScreenBean();

            view.setTableNames(slb.getTableNames());
            view.setTableLongNames(slb.getTableLongNames());

            // 選択したテーブル名を設定
            if (bean.getRequest() instanceof S21CMSearchInputBean) {
                S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();
                view.setTableName(sib.getTableName());
            }

            // レスポンスとして設定
            bean.setResponse(view);

        } catch (Exception e) {
            // ここでのエラーは無視する
            // 本来は必要ないがCheckStyleのエラーを避けるためのダミー処理
            bean.setResponse(null);
        }

        // エラーメッセージ作成
        EZDMessageInfo msgInfo = null;

        // 個別エラーメッセージ出力
        if (exp instanceof IllegalArgumentException) {

            // ClassNotFoundExceptionの場合は専用のエラーメッセージを出力する
            if (exp.getCause() != null
                    && exp.getCause() instanceof ClassNotFoundException) {

                // 対象文字列抽出
                String className = extractErrMsg(exp);

                // エラーメッセージ作成
                msgInfo = new EZDMessageInfo(CLASS_NOT_FOUND, new String[] {className});

                // 画面にはExceptionを表示しない
                bean.setExp(null);
            }

        } else if (exp instanceof EZDAbendException) {

            // EZDAbendExceptionの場合は設定されているメッセージを出力する

            EZDAbendException ezexp = (EZDAbendException) exp;

            if (ezexp.getMessageInfo() != null) {
                // エラーメッセージ作成
                msgInfo = ezexp.getMessageInfo();

                // 画面にはExceptionを表示しない
                bean.setExp(null);

            } else if (exp.getCause() instanceof EZDDBRetryRequestException) {
                EZDDBRetryRequestException e = (EZDDBRetryRequestException) exp.getCause();
                if (904 == e.getErrorCode()) {
                    // 対象文字列抽出
                    String tableName = bean.getReq("selectTable");
                    String columnName = e.getMessage().replaceFirst(".*\"(.+)\".*", "$1");

                    // エラーメッセージ作成
                    msgInfo = new EZDMessageInfo(CONTRADICT_TABLE_DEF, new String[] {tableName, columnName});

                    // 画面にはExceptionを表示しない
                    bean.setExp(null);
                }
            } else {
                if (ezexp.getMessage().indexOf(EZ_ABEND_XML_NOT_FOUND) >= 0) {
                    // 対象文字列抽出
                    String className = extractErrMsg(ezexp);

                    // エラーメッセージ作成
                    msgInfo = new EZDMessageInfo(XML_NOT_FOUND, new String[] {className});

                    // 画面にはExceptionを表示しない
                    bean.setExp(null);
                }
            }
        }

        if (msgInfo == null) {
            // エラーメッセージ作成
            msgInfo = new EZDMessageInfo(OTHER_ERROR);
        }

        // コンテナに設定
        bean.setMsgInfo(msgInfo);

        // システムログ出力
        EZDLog.println(3, exp.getMessage());

        // 正常系と同じ画面へ遷移
        bean.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * Exceptionに設定されている文字列からエラー発生元の文字列を抜き出す。
     * @param exp Exception
     * @return 可変文字列
     */
    private String extractErrMsg(Exception exp) {

        // :以降の文字列を抜き出し
        String exmsg = exp.getMessage();

        int idx = exmsg.lastIndexOf(":");

        if (idx < 0) {
            return "";
        }

        return exmsg.substring(idx + 1).trim();
    }
}
