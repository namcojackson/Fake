package com.canon.cusa.s21.framework.generictable.ap;

import parts.common.EZDAbendException;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerUploadDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEValidatorException;

/**
 * コードメンテナンス画面 ファイルアップロード入力情報チェック。<br>
 * 検索条件なしでの検索用。<br>
 * @author Administrator
 */
public class S21CMUploadTableValidator extends S21CMSearchValidator {

    /** エラーメッセージ:ファイル設定なし */
    private static final String ERR_MSG_NO_FILE = "ZZM9014E";

    /** エラーフィールド名:ファイル指定テキストボックス */
    private static final String FIELD_UPLOAD = "Upload";

    /**
     * CSVファイルアップロード用入力データチェック。<br>
     * CSVアップロードはマルチパートメッセージであり、対象のファイルは
     * InputStreamとして設定されている。<br>
     * @param bean S21NEContainerDataBean
     * @see com.canon.cusa.s21.framework.generictable.ap.S21CMSearchValidator#valid(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    public void valid(S21NEContainerDataBean bean) {
        super.valid(bean);

        S21CMUploadTableBean utb = (S21CMUploadTableBean) bean.getRequest();

        // ファイルが指定されていれば解析、EZDTMsgArrayの作成を行う
        if (((S21NEContainerUploadDataBean) bean).getInputStream() != null) {
            // InputStreamの設定
            utb.setInputstream(((S21NEContainerUploadDataBean) bean).getInputStream());

            // フィル名称設定
            utb.setFileName(((S21NEContainerUploadDataBean) bean).getFileName());

        } else {
            // ファイル指定なし
            bean.setField(FIELD_UPLOAD);
            throw new EZDAbendException(new S21NEValidatorException(ERR_MSG_NO_FILE, ""));
        }
    }

    /**
     * 検索画面用入力情報Beanのインスタンスを生成する。<br>
     * アップロード用のBeanであるS21CMUploadTableBeanインスタンスを生成する。<br>
     * @return S21CMSearchInputBeanインスタンス
     */
    protected S21CMSearchInputBean createInputBean() {
        return new S21CMUploadTableBean();
    }

}
