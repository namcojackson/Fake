package com.canon.cusa.s21.framework.generictable.ap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.generictable.fw.S21NEException;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * S21CodeTableAccessorのprotectedメソッドをReflectionを使用して呼び出す。<br>
 * コードテーブルアクセス部品は、通常の業務アプリから更新を行えないようするため、
 * 更新系のAPIはprotectedで宣言し実装している。<br>
 * このためコードメンテアプリから更新を行う場合は本クラスを経由することで
 * コードテーブルアクセス部品の更新系メソッドの呼び出しを行う。<br>
 * 各APIの詳細はS21CodeTableAccessorを参照。<br>
 * @author Administrator
 */
public class S21CMCodetableReflectionInvoker {

    /**
     * S21CodeTableAccessor#create呼び出し。<br>
     * @param msg EZDTMsg
     */
    public static void invokeCreate(EZDTMsg msg) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("create", EZDTMsg.class);
            mthd.setAccessible(true);
            mthd.invoke(null, msg);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#update呼び出し。<br>
     * @param msg EZDTMsg
     */
    public static void invokeUpdate(EZDTMsg msg) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("update", EZDTMsg.class);
            mthd.setAccessible(true);
            mthd.invoke(null, msg);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#delete呼び出し。<br>
     * @param msg EZDTMsg
     */
    public static void invokeDelete(EZDTMsg msg) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("delete", EZDTMsg.class);
            mthd.setAccessible(true);
            mthd.invoke(null, msg);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#delete呼び出し。<br>
     * @param msg EZDTMsg
     */
    public static void invokeDeleteAll(String tableName) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("deleteAll", String.class);
            mthd.setAccessible(true);
            mthd.invoke(null, tableName);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#incrementSortNum呼び出し。<br>
     * @param tableName テーブル名
     * @param num 更新対象ソートカラム設定値
     */
    public static void invokeIncSortNum(String tableName, int num) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("incrementSortNum", String.class, int.class);
            mthd.setAccessible(true);
            mthd.invoke(null, tableName, num);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#incrementSortNum呼び出し。<br>
     * @param tableName テーブル名
     * @param num1 更新対象ソートカラム設定値
     * @param num2 更新対象ソートカラム設定値
     */
    public static void invokeIncSortNum(String tableName, int num1, int num2) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("incrementSortNum", String.class, int.class, int.class);
            mthd.setAccessible(true);
            mthd.invoke(null, tableName, num1, num2);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#decrementSortNum呼び出し。<br>
     * @param tableName テーブル名
     * @param num 更新対象ソートカラム設定値
     */
    public static void invokeDecSortNum(String tableName, int num) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("decrementSortNum", String.class, int.class);
            mthd.setAccessible(true);
            mthd.invoke(null, tableName, num);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#decrementSortNum呼び出し。<br>
     * @param tableName テーブル名
     * @param num1 更新対象ソートカラム設定値
     * @param num2 更新対象ソートカラム設定値
     */
    public static void invokeDecSortNum(String tableName, int num1, int num2) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("decrementSortNum", String.class, int.class, int.class);
            mthd.setAccessible(true);
            mthd.invoke(null, tableName, num1, num2);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#clearCache呼び出し。<br>
     * @param tableName テーブル名
     */
    public static void invokeClearCache(String tableName) {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("clearCache", String.class);
            mthd.setAccessible(true);
            mthd.invoke(null, tableName);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }

    /**
     * S21CodeTableAccessor#clearCache呼び出し。<br>
     */
    public static void invokeClearCacheAll() {

        try {
            Method mthd = S21CodeTableAccessor.class.getDeclaredMethod("clearCacheAll");
            mthd.setAccessible(true);
            mthd.invoke(null);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }
    
    public static EZDTMsg invokeMap2msg4cache(String codeTableName, Map<String, String> key) {

        try {
            Method mthd = S21MsgAccessor.class.getDeclaredMethod("map2msg4cache", String.class, Map.class);
            mthd.setAccessible(true);
            return (EZDTMsg)mthd.invoke(S21MsgAccessor.getInstance(), codeTableName, key);
        } catch (SecurityException e) {
            throw new S21NEException(e);
        } catch (NoSuchMethodException e) {
            throw new S21NEException(e);
        } catch (IllegalArgumentException e) {
            throw new S21NEException(e);
        } catch (IllegalAccessException e) {
            throw new S21NEException(e);
        } catch (InvocationTargetException e) {
            throw new S21NEException(e);
        }

    }
}
