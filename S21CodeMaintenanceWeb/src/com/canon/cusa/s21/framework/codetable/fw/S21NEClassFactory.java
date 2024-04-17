package com.canon.cusa.s21.framework.codetable.fw;

import java.util.HashMap;

/**
 * Factoryクラス。<br>
 * 定義ファイルに定義された情報をもとに各種クラスのインスタンスを
 * 生成する。<br>
 * 定義情報の取得はS21NEConfigを使用する。<br>
 * @author Administrator
 */
public final class S21NEClassFactory {

    /** インスタンス */
    private static S21NEClassFactory instance = new S21NEClassFactory();

    /** Classを保持する */
    private HashMap<String, Class> mapClazz = new HashMap<String, Class>();

    /** クラスタイプ:S21NEReqControl */
    public static final String TYPE_CONTROL = "control";

    /** クラスタイプ:S21NEValidator */
    public static final String TYPE_VALIDATOR = "validator";

    /** クラスタイプ:S21NEBusiness */
    public static final String TYPE_BIZ = "biz";

    /** クラスタイプ:S21NEDataBean */
    public static final String TYPE_BEAN = "bean";

    /**
     * コンストラクタ。<br>
     */
    private S21NEClassFactory() {
    }

    /**
     * 自身のインスタンスの取得。<br>
     * @return インスタンス
     */
    public static S21NEClassFactory getInstance() {
        return instance;
    }

    /**
     * 指定のアプリ、リクエストIDに対応するS21NEReqControlクラスのインスタンスを生成する。<br>
     * @param ap アプリケーション
     * @param reqID リクエストID
     * @return S21NEReqControlクラスのインスタンス
     */
    public S21NEReqControl createReqControl(String ap, String reqID) {
        return (S21NEReqControl) createInstance(ap, reqID, TYPE_CONTROL);
    }

    /**
     * 指定のアプリ、リクエストIDに対応するS21NEValidatorクラスのインスタンスを生成する。<br>
     * @param ap アプリケーション
     * @param reqID リクエストID
     * @return S21NEReqControlクラスのインスタンス
     */
    public S21NEValidator createValidator(String ap, String reqID) {
        return (S21NEValidator) createInstance(ap, reqID, TYPE_VALIDATOR);
    }

    /**
     * 指定のアプリ、リクエストIDに対応するS21NEBusinessクラスのインスタンスを生成する。<br>
     * @param ap アプリケーション
     * @param reqID リクエストID
     * @return S21NEReqControlクラスのインスタンス
     */
    public S21NEBusiness createBiz(String ap, String reqID) {
        return (S21NEBusiness) createInstance(ap, reqID, TYPE_BIZ);
    }

    /**
     * 指定のアプリ、リクエストIDに対応するS21NEDataBeanクラスのインスタンスを生成する。<br>
     * @param ap アプリケーション
     * @param reqID リクエストID
     * @return S21NEReqControlクラスのインスタンス
     */
    public S21NEDataBean createBean(String ap, String reqID) {
        return (S21NEDataBean) createInstance(ap, reqID, TYPE_BEAN);
    }

    /**
     * クラスインスタンスの生成を行う。<br>
     * @param ap アプリケーション
     * @param reqID リクエストID
     * @param type クラスタイプ
     * @return インスタンス
     */
    private Object createInstance(String ap, String reqID, String type) {

        // キー生成
        String key = makeKey(ap, reqID, type);

        // キャッシュからClassを取得
        Class clazz = mapClazz.get(key);

        // キャッシュから取得できなければ新たに作成する
        if (clazz == null) {

            // 定義情報から定義値を取得
            // 定義されていない場合はNullPointerExceptionで落とす
            String val = S21NEConfig.getInstance().getClassName(ap, reqID, type);

            try {
                clazz = Class.forName(val);
            } catch (ClassNotFoundException e) {
                throw new S21NEException(e);
            }

            // キャッシュに登録する
            mapClazz.put(key, clazz);
        }

        try {
            // Classから新たにインスタンスを作成する
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        }
    }

    /**
     * キー情報を作成する。<br>
     * @param str1 文字列1
     * @param str2 文字列2
     * @param str3 文字列3
     * @return キー情報
     */
    private String makeKey(String str1, String str2, String str3) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append("_").append(str2).append("_").append(str3);
        return sb.toString();
    }
}
