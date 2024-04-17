package com.canon.cusa.s21.framework.generictable.ap;

import java.util.Map;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面編集共通処理。<br>
 * @author Administrator
 */
public class S21CMEditCommon {

    /**
     * EZDTMsgの内容をMapに展開する。<br>
     * @param msg EZDTMsg
     * @return msgを展開したMapオブジェクト
     */
    public static Map<String, String> convertMsg2Map(EZDTMsg msg) {
        // Mapに変換
        Map<String, String> map = S21MsgAccessor.getInstance().msg2map(msg);
        return map;
    }

}
