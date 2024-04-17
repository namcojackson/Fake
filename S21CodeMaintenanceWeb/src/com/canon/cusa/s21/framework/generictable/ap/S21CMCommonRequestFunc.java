package com.canon.cusa.s21.framework.generictable.ap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBMsgDefaultValidator;
import parts.common.EZDTMsg;
import parts.common.EZDValidatorException;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEValidatorException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfoContainer;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * HttpRequest共通アクセス処理クラス。<br>
 * HttpRequestとEZDTMsg関連の共通的な処理を行うstaticメソッド群。<br>
 * @author Administrator
 */
public class S21CMCommonRequestFunc {

    /**
     * リクエストから文字列を取得。<br>
     * @param req HttpServletRequest
     * @param key キー
     * @return 設定値
     */
    protected static String getReqString(S21NEContainerDataBean bean, String key) {
        String val = bean.getReq(key);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * リクエストからint値を取得。<br>
     * @param req HttpServletRequest
     * @param key キー
     * @return 設定値
     */
    protected static int getReqInt(S21NEContainerDataBean bean, String key) {
        String val = bean.getReq(key);
        if (val == null || val.equals("")) {
            return Integer.MIN_VALUE;
        } else {
            return Integer.parseInt(val);
        }
    }

    /**
     * リクエストからBool値を取得。<br>
     * @param req HttpServletRequest
     * @param key キー
     * @return 設定値
     */
    protected static boolean getReqBoolean(S21NEContainerDataBean bean, String key) {
        String val = bean.getReq(key);
        if (val == null || val.equals("")) {
            return false;
        } else {
            return val.equalsIgnoreCase("true");
        }
    }

    /**
     * 入力された情報からEZDTmsgを作成する。<br>
     * EZDTMsgへ値を設定する際に入力チェックがEZDにより行われる。<br>
     * 入力チェックでエラーが発生した場合は、EZDAbendExceptionが発生するため、
     * 呼び出し元の適切な位置でcatchし入力チェックとして処理する。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param prefix EZDTMsgの入力時プリフィックス
     * @return EZDTMsgインスタンス
     */
    protected static EZDTMsg createEZDTMsg(String tableName, S21NEContainerDataBean bean, String prefix) {
        return createEZDTMsg(tableName, bean, prefix, "");
    }

    /**
     * 入力された情報からEZDTMsgを作成する。<br>
     * EZDTMsgへ値を設定する際に入力チェックがEZDにより行われる。<br>
     * 入力チェックでエラーが発生した場合は、EZDAbendExceptionが発生するため、
     * 呼び出し元の適切な位置でcatchし入力チェックとして処理する。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param prefix EZDTMsgの入力時接頭子
     * @param suffix EZDTMsgの入力時接尾子
     * @return EZDTMsgインスタンス
     */
    protected static EZDTMsg createEZDTMsg(String tableName, S21NEContainerDataBean bean, String prefix, String suffix) {

        // EZDTMsgインスタンスを生成
        EZDTMsg msg = S21MsgAccessor.getInstance().createTMsgInstance(tableName);

        // 非EZDカラム情報取得
        //S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getNonEzdColumnInfos(tableName);
        List<S21CodeTableColumnInfo> infos = S21GenericTableAccessor.getColumnInfoAll(tableName);

        for (int i = 0; i < infos.size(); i++) {
            S21CodeTableColumnInfo info = infos.get(i);

            // 入力値を取得
            String val = getReqString(bean, prefix + info.getColumnName() + suffix);
            val = trimRightCharacter(val);

            // EZDTMsgに値を設定する
            setEZDTMsg(bean, msg, info, val);
        }

        return msg;
    }
    
    /**
     * 引数で渡されてた文字列の右トリムを行います
     * @param target 置換対象となる文字列
     * @return 変換後の文字列 
     */
    private static String trimRightCharacter (String target){
        if(target==null){
            return "";
        }
        return target.replaceAll(" +$","");
    }

    /**
     * Mapに展開した入力内容からEZDTMsgを作成する。<br>
     * EZDTMsgへ値を設定する際に入力チェックがEZDにより行われる。<br>
     * 入力チェックでエラーが発生した場合は、EZDAbendExceptionが発生するため、
     * 呼び出し元の適切な位置でcatchし入力チェックとして処理する。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param mapMsg Mapに展開した入力内容
     * @return EZDTMsgインスタンス
     */
    protected static EZDTMsg createEZDTMsg(String tableName, S21NEContainerDataBean bean, Map<String, String> mapMsg) {

        // EZDTMsgインスタンスを生成
        EZDTMsg msg = S21MsgAccessor.getInstance().createTMsgInstance(tableName);

        // 非EZDカラム情報取得
        S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getNonEzdColumnInfos(tableName);

        for (int i = 0; i < infos.length; i++) {
            S21CodeTableColumnInfo info = infos[i];

            // 入力値を取得
            String val = mapMsg.get(info.getColumnName());
            
            // EZDTMsgに値を設定する
            setEZDTMsg(bean, msg, info, val);
        }

        return msg;
    }
    
    /**
     * DBの検索結果を EDZMsg に格納する
     * @param tableName
     * @param bean
     * @param mapMsg
     * @return
     */
    protected static EZDTMsg createEZDTMsgAll(String tableName, S21NEContainerDataBean bean, Map mapMsg) {

        // EZDTMsgインスタンスを生成
        EZDTMsg msg = S21MsgAccessor.getInstance().createTMsgInstance(tableName);

        // カラム情報取得
        S21CodeTableColumnInfoContainer infoCont = S21MsgAccessor.getInstance().getColumnInfoCont(tableName);
        S21CodeTableColumnInfo[] infos = infoCont.getAll();

        for (int i = 0; i < infos.length; i++) {
            S21CodeTableColumnInfo info = infos[i];

            // 入力値を取得
            if (info.getItemType() == S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
            	BigDecimal val = (BigDecimal) mapMsg.get(info.getColumnName());
            	if (val != null) {
                    setEZDTMsg(bean, msg, info, val.toString());
            	}
            } else {
            	String val = (String) mapMsg.get(info.getColumnName());
                // EZDTMsgに値を設定する
                setEZDTMsg(bean, msg, info, val);
            }
        }
        return msg;
    }

    /**
     * EZDTMsgに値を設定する。<br>
     * EZDTMsgへ値を設定する際に入力チェックがEZDにより行われる。<br>
     * 入力チェックでエラーが発生した場合は、EZDAbendExceptionが発生するため、
     * 呼び出し元の適切な位置でcatchし入力チェックとして処理する。<br>
     * @param bean S21NEContainerDataBean
     * @param msg EZDTMsg
     * @param info info
     * @param val 設定値
     */
    private static void setEZDTMsg(S21NEContainerDataBean bean, EZDTMsg msg, S21CodeTableColumnInfo info, String val) {
    	
        if (val != null && val.length() > 0) {

            try {
                // 処理中カラム名
                // エラー処理にてエラー発生元特定のために設定する
                bean.setField(info.getColumnName());

                // 入力値チェック
                EZDBMsgDefaultValidator dv = new EZDBMsgDefaultValidator();
                dv.checkInputText(val, msg.getAttr(info.getKeyName()), false, false);
            } catch (EZDValidatorException e) {
                // 入力チェックエラー
                // EZDTMsgでの入力エラーはEZDAbendExceptionとなるため
                // このcatchが動くことはない
                throw new S21NEValidatorException(e, info.getColumnName());
            }

            // 指定カラムに入力値を設定
            if (info.getItemType() == S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
                msg.setValue(info.getKeyName(), -1, new BigDecimal(val));
            } else {
                msg.setValue(info.getKeyName(), -1, val);
            }
        }
    }

    /**
     * 入力された情報からEZDTMsg相当のMapを作成する。<br>
     * チェック処理は行わない。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param prefix EZDTMsgの入力時接頭子
     * @return EZDTMsg相当のMapインスタンス
     */
    protected static Map<String, String> createMsgMap(String tableName, S21NEContainerDataBean bean, String prefix) {
        return createMsgMap(tableName, bean, prefix, "");
    }

    /**
     * 入力された情報からEZDTMsg相当のMapを作成する。<br>
     * チェック処理は行わない。<br>
     * @param tableName テーブル名称
     * @param bean S21NEContainerDataBean
     * @param prefix EZDTMsgの入力時接頭子
     * @param suffix EZDTMsgの入力時接尾子
     * @return EZDTMsg相当のMapインスタンス
     */
    protected static Map<String, String> createMsgMap(String tableName, S21NEContainerDataBean bean, String prefix, String suffix) {

        // 非EZDカラム情報取得
        S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getNonEzdColumnInfos(tableName);

        Map<String, String> map = new HashMap<String, String>(infos.length);

        for (int i = 0; i < infos.length; i++) {
            S21CodeTableColumnInfo info = infos[i];

            String val = getReqString(bean, prefix + info.getColumnName() + suffix);

            if (!val.equals("")) {
                map.put(info.getColumnName(), val);
            }
        }

        return map;
    }

}
