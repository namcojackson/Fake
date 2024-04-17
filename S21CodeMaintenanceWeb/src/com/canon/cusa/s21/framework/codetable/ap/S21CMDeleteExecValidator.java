package com.canon.cusa.s21.framework.codetable.ap;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 検索画面入力削除実行。<br>
 * 削除実行処理。<br>
 * @author Administrator
 */
public class S21CMDeleteExecValidator extends S21CMSearchValidator {

    /**
     * 削除レコードの情報をBeanに設定する。<br>
     * @param sib リクエスト情報保持Bean
     */
    @Override
    protected void checkCondition(S21NEContainerDataBean bean) {

        S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();

        List<EZDTMsg> delRecs = new ArrayList<EZDTMsg>();

        for (int i = 0; i < sib.getRecordCount(); i++) {
            // 一覧の内容をEZDTMsgとして情報を設定
            EZDTMsg msg = S21CMCommonRequestFunc.createEZDTMsg(sib.getTableName(), bean, S21CMRequestDef.REQ_EZMSG_KEY, "_" + i);
            delRecs.add(msg);
        }

        // 入力情報へ削除要求のレコード情報を設定
        sib.setDelRecs(delRecs);
    }

}
