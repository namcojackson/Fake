package com.canon.cusa.s21.framework.codetable.ap;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidator;

/**
 * リクエスト用入力チェック。<br>
 * 何もしない。<br>
 * @author Administrator
 */
public class S21CMNopValidator implements S21NEValidator {

    /**
     * 入力情報のチェックを行い、問題がなければリクエストをBeanに設定する。<br>
     * チェック結果はS21NERequestDataBeanに設定する。<br>
     * @param bean リクエスト情報コンテナ
     */
    public void valid(S21NEContainerDataBean bean) {
        // NOP
    }

}
