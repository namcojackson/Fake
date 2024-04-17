package com.canon.cusa.s21.framework.generictable.fw;

/**
 * コードメンテナンス用入力チェックインタフェース。<br>
 * @author Administrator
 */
public interface S21NEValidator {

    /**
     * 入力情報のチェックを行い、問題がなければリクエストをBeanに設定する。<br>
     * チェック結果はS21NERequestDataBeanに設定する。<br>
     * @param bean リクエスト情報コンテナ
     */
    public void valid(S21NEContainerDataBean bean);
}
