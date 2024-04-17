package com.canon.cusa.s21.framework.generictable.ap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.generictable.ap.S21CMEditRecordBean.S21CMEditType;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEValidatorException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 変更/追加実行処理。<br>
 * @author Administrator
 */
public class S21CMEditRecordUpdateBiz extends S21CMCacheRemoveBiz {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 対象テーブル取得
        S21CMEditRecordInputBean inb = (S21CMEditRecordInputBean) req.getRequest();

        // 追加要求
        if (inb.getType() == S21CMEditType.ADD) {
            create(inb.getMsg());

        // 更新要求
        } else if (inb.getType() == S21CMEditType.EDIT) {
            update(inb.getMsg());
        }
    }

    /**
     * 指定のEZDTMsgを更新する。<br>
     * ソートカラムの設定情報をチェックし、必要であれば他レコードの
     * ソートカラム値の更新を行う。<br>
     * @param msg 更新EZDTMsg
     */
    private void update(EZDTMsg msg) {

        // ソートカラム取得
        String sortColName = S21MsgAccessor.getInstance().getContainSortNum(msg);

        // ソートカラムが存在するか
        if (sortColName == null) {
            // ソートカラムがなければ単純更新
//            S21CMCodetableReflectionInvoker.invokeUpdate(msg);
        	S21GenericTableAccessor.update(msg);

            // リターンコードチェック
            checkReturnCode(msg);

        } else {
            // ソートカラムが存在する場合は他のレコードのソートキーを更新
            updateSortNum(sortColName, msg);
        }
    }

    /**
     * 新旧のEZDTMsgのソートカラム設定値を比較したうえでソートカラムを
     * 考慮した更新処理を行う。<br>
     * @param columnNmae カラム名
     * @param newMsg 新EZDTMsg
     */
    private void updateSortNum(String columnNmae, EZDTMsg newMsg) {

        BigDecimal newVal = null;
        BigDecimal oldVal = null;

        // 現在の値を取得
//        EZDTMsg oldMsg = S21CodeTableAccessor.findByKey(newMsg);
        EZDTMsg oldMsg = S21GenericTableAccessor.findByKey(newMsg);

        // 変更前のレコードが存在しない
        if (oldMsg == null) {
            throw new EZDAbendException(new S21NEValidatorException("ZZXL0006E", ""));
        }

//        S21CodeTableColumnInfo info = S21CodeTableAccessor.getColumnInfo(newMsg.getTableName(), columnNmae);
        S21CodeTableColumnInfo info = S21GenericTableAccessor.getColumnInfo(newMsg.getTableName(), columnNmae);

        // ソートキーは数値型のみ
        if (oldMsg != null && info.getItemType() == S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
            newVal = newMsg.getValueBigDecimal(info.getKeyName(), -1);
            oldVal = oldMsg.getValueBigDecimal(info.getKeyName(), -1);
        }

        // 数値以外か変更されていない
        if (newVal == null || oldVal == null
                || newVal.equals(oldVal)) {
            // ソートカラムが変更されていなければ単純更新
//            S21CMCodetableReflectionInvoker.invokeUpdate(newMsg);
        	S21GenericTableAccessor.update(newMsg);

            // リターンコードチェック
            checkReturnCode(newMsg);

            return;
        }

        // ソートキーが使用されているかどうか確認
        Map<String, String> map = new HashMap<String, String>();
        map.put(columnNmae, newVal.toPlainString());
//        EZDTMsgArray finds = S21CodeTableAccessor.findByCondition(newMsg.getTableName(), map);
        EZDTMsgArray finds = S21GenericTableAccessor.findByCondition(newMsg.getTableName(), map);

        // 見つかる場合は1レコード、見つからなければそのまま
        // 同じソート値が複数レコードある場合は、同じ値で追加する
        if (finds.length() != 1) {
            // 単純更新
//            S21CMCodetableReflectionInvoker.invokeUpdate(newMsg);
        	S21GenericTableAccessor.update(newMsg);

            // リターンコードチェック
            checkReturnCode(newMsg);

            return;
        }

        if (newVal.compareTo(oldVal) > 0) {
            // 後ろへ移動
            S21CMCodetableReflectionInvoker.invokeDecSortNum(newMsg.getTableName(),
                                                            oldVal.intValue(),
                                                            newVal.intValue() + 1);
        } else {
            // 前へ移動
            S21CMCodetableReflectionInvoker.invokeIncSortNum(newMsg.getTableName(),
                                                            newVal.intValue() - 1,
                                                            oldVal.intValue());
        }

        // 更新
//        S21CMCodetableReflectionInvoker.invokeUpdate(newMsg);
        S21GenericTableAccessor.update(newMsg);

        // リターンコードチェック
        checkReturnCode(newMsg);
    }

    /**
     * EZDTMsgを追加する。<br>
     * @param msg 追加EZDTMsg
     */
    private void create(EZDTMsg msg) {

        // ソートカラム取得
        String sortColName = S21MsgAccessor.getInstance().getContainSortNum(msg);

        // ソートカラムがなければ単純追加
        if (sortColName == null) {
//            S21CMCodetableReflectionInvoker.invokeCreate(msg);
            S21GenericTableAccessor.create(msg);

            // リターンコードチェック
            checkReturnCode(msg);

            return;
        }

        BigDecimal val = null;

        //S21CodeTableColumnInfo info = S21CodeTableAccessor.getColumnInfo(msg.getTableName(), sortColName);
        S21CodeTableColumnInfo info = S21GenericTableAccessor.getColumnInfo(msg.getTableName(), sortColName);

        // ソートキーは数値型のみ
        if (info.getItemType() == S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
            val = msg.getValueBigDecimal(info.getKeyName(), -1);
        }

        // 数値以外か(数値以外はあり得ない)
        if (val == null) {
            // ソートカラムが数値でなければ単純追加
//            S21CMCodetableReflectionInvoker.invokeCreate(msg);
            S21GenericTableAccessor.create(msg);

            // リターンコードチェック
            checkReturnCode(msg);

            return;
        }

        // ソートキーが使用されているかどうか確認
        Map<String, String> map = new HashMap<String, String>();
        map.put(sortColName, val.toPlainString());
//        EZDTMsgArray finds = S21CodeTableAccessor.findByCondition(msg.getTableName(), map);
        EZDTMsgArray finds = S21GenericTableAccessor.findByCondition(msg.getTableName(), map);

        // 見つかる場合は1レコード、見つからなければそのまま
        // 同じソート値が複数レコードある場合は、同じ値で追加する
        if (finds.length() != 1) {
            // 単純追加
//            S21CMCodetableReflectionInvoker.invokeCreate(msg);
            S21GenericTableAccessor.create(msg);

            // リターンコードチェック
            checkReturnCode(msg);

        } else {
            // 後ろへ移動
            S21CMCodetableReflectionInvoker.invokeIncSortNum(msg.getTableName(),
                                                            val.intValue() - 1);

            // 追加
//            S21CMCodetableReflectionInvoker.invokeCreate(msg);
            S21GenericTableAccessor.create(msg);

            // リターンコードチェック
            checkReturnCode(msg);
        }
    }

    /**
     * 削除するテーブル名称を取得。<br>
     * @param req S21NEContainerDataBean
     * @return テーブル名称
     * @see com.canon.cusa.s21.framework.generictable.ap.S21CMCacheRemoveBiz#getTableName(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected String getTableName(S21NEContainerDataBean req) {
        // 対象テーブル取得
        S21CMEditRecordInputBean inb = (S21CMEditRecordInputBean) req.getRequest();
        return inb.getTableName();
    }
}
