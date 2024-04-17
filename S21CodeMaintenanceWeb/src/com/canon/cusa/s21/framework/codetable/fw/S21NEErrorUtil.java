package com.canon.cusa.s21.framework.codetable.fw;

import parts.common.EZDCommonConst;

/**
 * EZD互換エラー処理。<br>
 * 各種のエラー処理、エラー結果の出力をEZDに合わせるため、
 * EZD内のエラー処理を行う。<br>
 * @author Administrator
 */
public class S21NEErrorUtil {

    /**
     * 入力エラーコードをEZDエラーメッセージコードに変換する。<br>
     * @param code 入力エラーコード
     * @return EZDエラーメッセージコード
     */
    public static String convMessageCode(int code) {

        String messageCode = "";
        if (code == EZDCommonConst.ERROR_HISSU) {
            messageCode = "ZZM9000E";
        } else if (code == EZDCommonConst.ERROR_KETA) {
            messageCode = "ZZM9001E";
        } else if (code == EZDCommonConst.ERROR_SAIDAI) {
            messageCode = "ZZM9002E";
        } else if (code == EZDCommonConst.ERROR_SAISYO) {
            messageCode = "ZZM9003E";
        } else if (code == EZDCommonConst.ERROR_SUCHI) {
            messageCode = "ZZM9004E";
        } else if (code == EZDCommonConst.ERROR_ZENKAKU) {
            messageCode = "ZZM9005E";
        } else if (code == EZDCommonConst.ERROR_ZENKAKUKANA) {
            messageCode = "ZZM9011E";
        } else if (code == EZDCommonConst.ERROR_HANKAKU) {
            messageCode = "ZZM9006E";
        } else if (code == EZDCommonConst.ERROR_HANKAKUKANA) {
            messageCode = "ZZM9007E";
        } else if (code == EZDCommonConst.ERROR_HANKAKUEISU) {
            messageCode = "ZZM9008E";
        } else if (code == EZDCommonConst.ERROR_HANKAKUEIJI) {
            messageCode = "ZZM9009E";
        } else if (code == EZDCommonConst.ERROR_HANKAKUSUJI) {
            messageCode = "ZZM9012E";
        } else if (code == EZDCommonConst.ERROR_HIZUKE) {
            messageCode = "ZZM9010E";
//        } else {
//// 07/08/08 [S08030] MOD start
////          messageCode = "ZZM9501E";
//            throw new EZDAbendException(EZDMessageFunc.clspGetLogMessage("LGM002301") + code);
//// 07/08/08 [S08030] MOD end
        }

        return messageCode;
    }
}
