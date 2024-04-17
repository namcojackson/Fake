package com.canon.cusa.s21.framework.codetable.fw;


/**
 * コードメンテナンス 入力値不正Exception。<br>
 * @author Administrator
 */
public class S21NEValidatorException extends S21NEException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 入力カラム名 */
    private String column;

    /**
     * コンストラクタ。<br>
     */
    public S21NEValidatorException() {
        super();
    }

    /**
     * コンストラクタ。<br>
     * @param e 元の例外
     */
    public S21NEValidatorException(Exception e) {
        super(e);
    }

    /**
     * コンストラクタ。<br>
     * @param errstr エラーメッセージ
     * @param e 元の例外
     */
    public S21NEValidatorException(String errstr, Exception e) {
        super(errstr, e);
    }

    /**
     * コンストラクタ。<br>
     * @param errcode エラーコード
     * @param errstr エラーメッセージ
     */
    public S21NEValidatorException(String errcode, String errstr) {
        super(errcode, errstr);
    }

    /**
     * コンストラクタ。<br>
     * @param errstr エラーメッセージ
     */
    public S21NEValidatorException(String errstr) {
        super(errstr);
    }

    /**
     * コンストラクタ。<br>
     * @param e 元の例外
     * @param column カラム名
     */
    public S21NEValidatorException(Exception e, String column) {
        super(e);
        this.column = column;
    }

    /**
     * @return column
     */
    public String getColumn() {
        return column;
    }

}
