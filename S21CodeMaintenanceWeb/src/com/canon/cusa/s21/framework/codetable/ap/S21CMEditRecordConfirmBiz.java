package com.canon.cusa.s21.framework.codetable.ap;

import java.math.BigDecimal;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 レコード入力確認処理。<br>
 * @author Administrator
 */
public class S21CMEditRecordConfirmBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 検索処理結果保持Beanの作成
        S21CMEditRecordBean erb = new S21CMEditRecordBean();

        // レスポンス情報としてS21CMEditRecordBeanを設定
        req.setResponse(erb);

        // 入力情報取得
        S21CMEditRecordInputBean inb = (S21CMEditRecordInputBean) req.getRequest();

        // テーブル名称設定
        erb.setTableName(inb.getTableName());
        erb.setTableLongName(S21CodeTableAccessor.getLongName(inb.getTableName()));

        // EZDTMsgは入力内容のソート番号をチェックして設定
        erb.setMsg(checkSortNum(inb.getMsg()));

        // 種別はそのまま設定
        erb.setType(inb.getType());

        // カラム情報を取得
        erb.setColInfos(S21CodeTableAccessor.getColumnInfoAll(inb.getTableName()));

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * ソート番号が設定されていなければ、現時点の最大値+1で採番する。<br>
     * ソートカラムが存在しないか、設定済みであれば何も処理しない。<br>
     * @param msg EZDTMsg
     * @return EZDTMsg
     */
    private EZDTMsg checkSortNum(EZDTMsg msg) {

        // ソートカラム取得
        String sortColName = S21MsgAccessor.getInstance().getContainSortNum(msg);

        // ソートカラムがなければ何もしない
        if (sortColName == null) {
            return msg;
        }

        S21CodeTableColumnInfo info = S21CodeTableAccessor.getColumnInfo(msg.getTableName(), sortColName);

        // ソートキーは数値型のみ
        if (info.getItemType() != S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
            return msg;
        }

        // 値が設定済みであれば何もしない
        if (S21MsgAccessor.getInstance().getMsgValueString(msg, info) != null) {
            return msg;
        }

        // ソートカラム値を採番する
        BigDecimal max = BigDecimal.ZERO;

        // 全レコード取得
        EZDTMsgArray finds = S21CodeTableAccessor.findAll(msg);

        // ソートされているので一番最後のレコードを見ればいいはずですが念のため全部見る
        for (int i = 0; i < finds.length(); i++) {
            EZDTMsg find = (EZDTMsg) finds.get(i);
            BigDecimal val = find.getValueBigDecimal(info.getKeyName(), -1);

            if (val == null) {
                continue;
            } else if (max.compareTo(val) < 0) {
                max = val;
            }
        }

        // 最大値 + 1を設定する
        msg.setValue(info.getKeyName(), -1, max.add(BigDecimal.ONE));

        return msg;
    }
}
