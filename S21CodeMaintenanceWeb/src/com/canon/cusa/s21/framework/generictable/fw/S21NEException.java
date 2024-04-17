package com.canon.cusa.s21.framework.generictable.fw;

import parts.common.EZDAbendException;

/**
 * コードメンテナンスFW用Exception。<br>
 * @author Administrator
 */
public class S21NEException extends EZDAbendException {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ。<br>
     */
    public S21NEException() {
        super();
    }

    /**
     * コンストラクタ。<br>
     * @param e 元の例外
     */
    public S21NEException(Exception e) {
        super(e);
    }

    /**
     * コンストラクタ。<br>
     * @param errstr エラーメッセージ
     * @param e 元の例外
     */
    public S21NEException(String errstr, Exception e) {
        super(errstr, e);
    }

    /**
     * コンストラクタ。<br>
     * @param errcode エラーコード
     * @param errstr エラーメッセージ
     */
    public S21NEException(String errcode, String errstr) {
        super(errcode, errstr);
    }

    /**
     * コンストラクタ。<br>
     * @param errstr エラーメッセージ
     */
    public S21NEException(String errstr) {
        super(errstr);
    }

}
