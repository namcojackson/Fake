package com.canon.cusa.s21.framework.codetable.ap;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidatorException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 削除レコード選択イベント<br>
 * @author Administrator
 */
public class S21CMDeleteSeletctBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 入力情報を取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = createDeleteList(sib);

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * 削除対象テーブル検索処理。<br>
     * @param sib 検索条件Bean
     * @return 検索結果Bean
     */
    private S21CMSearchListBean createDeleteList(S21CMSearchInputBean sib) {

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

        // 削除対象レコードのキー情報を保持するEZDTMsgを取得
        List<EZDTMsg> delMsgs = sib.getDelRecs();

        // 削除対象レコードを検索しなおし、配列に設定する
        List<EZDTMsg> findMsgs = new ArrayList<EZDTMsg>();
        for (int i = 0; i < delMsgs.size(); i++) {
            EZDTMsg msg = S21CodeTableAccessor.findByKey(delMsgs.get(i));
            if (msg != null) {
                findMsgs.add(msg);
            }
        }

        // 実行前に削除されている
        if (findMsgs.size() == 0) {
            throw new EZDAbendException(new S21NEValidatorException("ZZXL0005E", ""));
        }

        // EZDTMsg配列作成
        EZDTMsg[] msgs = findMsgs.toArray(new EZDTMsg[findMsgs.size()]);

        // EZDTMsgに対応するEZDTMsgArrayを作成
        EZDTMsgArray array = S21MsgAccessor.getInstance().createTMsgArrayInstance(sib.getTableName());
        array.setMsgList(msgs);

        // テーブル内容を設定する
        slb.setMsgs(array);

        // カラム情報を取得する
        slb.setColInfo(S21CodeTableAccessor.getColumnInfoAll(sib.getTableName()));

        // 検索テーブル情報
        slb.setTableName(sib.getTableName());
        slb.setTableLongName(S21CodeTableAccessor.getLongName(sib.getTableName()));

        return slb;
    }
}
