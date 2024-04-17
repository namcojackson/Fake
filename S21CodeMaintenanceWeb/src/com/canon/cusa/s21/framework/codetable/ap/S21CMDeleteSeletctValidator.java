package com.canon.cusa.s21.framework.codetable.ap;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidatorException;

/**
 * コードメンテナンス画面 検索画面入力削除情報チェック。<br>
 * 検索画面から削除要求での検索用。<br>
 * @author Administrator
 */
public class S21CMDeleteSeletctValidator extends S21CMSearchValidator {

    /**
     * 削除レコードの情報をBeanに設定する。<br>
     * @param sib リクエスト情報保持Bean
     */
    @Override
    protected void checkCondition(S21NEContainerDataBean bean) {

        S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();

        List<EZDTMsg> delRecs = new ArrayList<EZDTMsg>();

        // 削除対象ありフラグ
        boolean del = false;

        for (int i = 0; i < sib.getRecordCount(); i++) {
            // Deleteチェックボックスがチェックされているか?
            if (S21CMCommonRequestFunc.getReqBoolean(bean, S21CMRequestDef.REQ_DEL_INDEX + i)) {
                // チェックされていればEZDTMsgとして情報を設定
                EZDTMsg msg = S21CMCommonRequestFunc.createEZDTMsg(sib.getTableName(), bean, S21CMRequestDef.REQ_EZMSG_KEY, "_" + i);
                delRecs.add(msg);

                del = true;
            }
        }

        // 削除対象が存在しなければ入力チェックエラーとする
        if (del == false) {
            // ファイル指定なし
            bean.setField("Del");
            throw new EZDAbendException(new S21NEValidatorException("ZZM9000E", ""));
        }

        // 入力情報へ削除要求のレコード情報を設定
        sib.setDelRecs(delRecs);
    }

}
