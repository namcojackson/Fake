package com.canon.cusa.s21.framework.codetable.ap;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEValidatorException;

/**
 * コードテーブル更新系共通コミット/ロールバック後処理業務ベース。<br>
 * コミット後処理、ロールバック後処理でキャッシュの削除を行う。<br>
 * コードテーブルの更新を行う業務処理は、本クラスを継承したクラスとする。<br>
 * <br>
 * コードテーブルの更新を行った場合、DBの内容とキャッシュの内容を同期する必要がある。<br>
 * キャッシュをコミット、ロールバック前に更新した場合、不測のエラー発生での
 * キャッシュ内容のロールバックを行うことができないため、コードテーブルアクセッサでは
 * 更新系APIでのキャッシュ内容の同期は行っていない。<br>
 * 更新時のコミット、ロールバック後にキャッシュ内容を削除することで、
 * 次回のコードテーブルアクセスのタイミングでキャッシュ内容の再構築
 * を強制的に行うことでDBとキャッシュ内容の同期を行う。<br>
 * @author Administrator
 */
public abstract class S21CMCacheRemoveBiz extends S21NEBusiness {

    /** 対象レコードなし */
    private static final String ERR_NOT_FOUND = "ZZXL0002E";

    /** キー重複 */
    private static final String ERR_KEY_DUPLICATE = "ZZXL0001E";

    /**
     * コミット後処理。<br>
     * コードテーブル用のキャッシュをクリアする。<br>
     * DBとキャッシュの内容を同期するため、キャッシュ内に存在する更新対象テーブルの
     * 情報を消去する。<br>
     * 消去することで次回のコードテーブルアクセスにより、キャッシュ内容が
     * 再構築され、キャッシュとDB内容の動機を図る。<br>
     * @param req S21NEContainerDataBean
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness#commitProc(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void commitProc(S21NEContainerDataBean req) {
        // キャッシュクリア処理

        // キャッシュ削除
        S21CMCodetableReflectionInvoker.invokeClearCache(getTableName(req));
    }

    /**
     * ロールバック後処理。<br>
     * コードテーブル用のキャッシュをクリアする。<br>
     * DBとキャッシュの内容を同期するため、キャッシュ内に存在する更新対象テーブルの
     * 情報を消去する。<br>
     * 消去することで次回のコードテーブルアクセスにより、キャッシュ内容が
     * 再構築され、キャッシュとDB内容の動機を図る。<br>
     * @param req S21NEContainerDataBean
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness#commitProc(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void rollbackProc(S21NEContainerDataBean req) {
        // キャッシュクリア処理

        // キャッシュ削除
        S21CMCodetableReflectionInvoker.invokeClearCache(getTableName(req));
    }

    /**
     * 削除するテーブル名称を取得。<br>
     * @param req S21NEContainerDataBean
     * @return テーブル名称
     */
    protected abstract String getTableName(S21NEContainerDataBean req);

    /**
     * EZDTMsgに設定されているリターンコードをチェック。<br>
     * @param msg EZDTMsg
     */
    protected void checkReturnCode(EZDTMsg msg) {

        // EZDリターンコード取得
        String rtnCode = msg.getReturnCode();

        if (rtnCode == null || rtnCode.equals("")) {
            // リターンコードなし
            return;
        } else if (rtnCode.equals("0000")) {
            // NORMAL
            return;
        } else if (rtnCode.equals("2000")) {
            // NOT FOUND
            //throw new EZDAbendException(new S21NEValidatorException(ERR_NOT_FOUND, ""));
            throw new S21NEValidatorException(ERR_NOT_FOUND, "");
        } else if (rtnCode.equals("2300")) {
            // DUPLICATE
            //throw new EZDAbendException(new S21NEValidatorException(ERR_KEY_DUPLICATE, ""));
            throw new S21NEValidatorException(ERR_KEY_DUPLICATE, "");
        }
    }
}
