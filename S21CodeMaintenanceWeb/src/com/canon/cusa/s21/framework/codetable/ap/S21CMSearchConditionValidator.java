package com.canon.cusa.s21.framework.codetable.ap;

import java.util.Map;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;

/**
 * コードメンテナンス画面 検索画面入力情報チェック。<br>
 * 検索条件ありでの検索用。<br>
 * @author Administrator
 */
public class S21CMSearchConditionValidator extends S21CMSearchValidator {

    /**
     * 検索条件をチェックし問題なければBeanに設定する。<br>
     * EZDTMsgへ値を設定する際に入力チェックが行われる。<br>
     * 入力チェックでエラーが発生した場合は、EZDAbendExceptionが発生するため、
     * 呼び出し元の適切な位置でcatchし入力チェックとして処理する。<br>
     * @param sib リクエスト情報保持Bean
     */
    protected void checkCondition(S21NEContainerDataBean bean) {

        S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();

        // 検索条件を保持するMapを作成
        Map<String, String> condMap = S21CMCommonRequestFunc.createMsgMap(sib.getTableName(), bean, S21CMRequestDef.REQ_EZMSG);
        sib.setCondMap(condMap);

        // 検索条件を保持するEZDTMsgを作成
        // 検索条件のフォーマットチェックも同時に行う
        sib.setCondition(S21CMCommonRequestFunc.createEZDTMsg(sib.getTableName(), bean, S21CMRequestDef.REQ_EZMSG));
    }
}
