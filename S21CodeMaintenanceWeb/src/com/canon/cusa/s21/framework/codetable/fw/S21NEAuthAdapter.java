package com.canon.cusa.s21.framework.codetable.fw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.security.S21AuthorizationAction;
import com.canon.cusa.s21.framework.security.S21AuthorizationException;
import com.canon.cusa.s21.framework.security.context.S21SecurityContext;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;

/**
 * S21Authorizationに対する共通的な処理。<br>
 * プロファイル取得部品、S21Authorizationをラップする。<br>
 * @author Administrator
 */
public class S21NEAuthAdapter {

    private Map<String, List<S21AuthorizationAction>> actionMap = null;

    private void createActionMap() {

        actionMap = new HashMap<String, List<S21AuthorizationAction>>();

        // ログイン中ユーザの情報を保持しているContextを取得する
        S21SecurityContext context = S21SecurityContextHolder.getContext();

        S21AuthorizationAction[] actions;

        try {
            // ユーザに紐づくActionを全て取得する
            actions = context.getAuthentication().getAuthorizationActions();
        } catch (S21AuthorizationException e) {
            return;
        }

        for (S21AuthorizationAction action : actions) {
            String resourceObjName = action.getName();
            String className = action.getResourceClass();

            String key = resourceObjName + ";" + className;
            List<S21AuthorizationAction> list = actionMap.get(key);

            if (list == null) {
                list = new ArrayList<S21AuthorizationAction>();
                actionMap.put(key, list);
            }

            list.add(action);
        }
    }

    /**
     * AuthorizeActionクラスから指定名称のリソースを取得する。<br>
     * リソースが存在しない場合は空のListをreturnする。
     * @param resourceObjName リソース名
     * @param className クラス名
     * @return S21AuthorizationActionを保持するList
     */
    public List<S21AuthorizationAction> getResourceAction(String resourceObjName, String className) {

        if (actionMap == null) {
            createActionMap();
        }

        String key = resourceObjName + ";" + className;

        List<S21AuthorizationAction> list = actionMap.get(key);

        if (list == null) {
            return new ArrayList<S21AuthorizationAction>();
        }

        return list;

//        // ログイン中ユーザの情報を保持しているContextを取得する
//        S21SecurityContext context = S21SecurityContextHolder.getContext();
//
//        List<S21AuthorizationAction> list = new ArrayList<S21AuthorizationAction>();
//        S21AuthorizationAction[] actions;
//
//        try {
//            // ユーザに紐づくActionを全て取得する
//            actions = context.getAuthentication().getAuthorizationActions();
//        } catch (S21AuthorizationException e) {
//            return list;
//        }
//
//        for (S21AuthorizationAction action : actions) {
//            // 指定のリソース名、クラス名であるかチェックする
//            if (action.getName().equals(resourceObjName)
//                    && action.getResourceClass().equals(className)) {
//                list.add(action);
//            }
//        }
//
//        return list;
    }

    // 下記の処理では複数のresource name="CODE TABLE"に対応不可(テストデータ)
    // 実環境で複数のロールに紐づくCODE TABLE定義に対応できるか不明のため使用していない
//    /**
//     * AuthorizeActionクラスから指定名称のリソースを取得する。<br>
//     * リソースが存在しない場合は空のListをreturnする。<br>
//     * @param className クラス名
//     * @return S21ProtectedResourceを保持するList
//     */
//    public List<S21ProtectedResource> getResources(String className) {
//
//        // ログイン中ユーザの情報を保持しているContextを取得する
//        S21SecurityContext context = S21SecurityContextHolder.getContext();
//
//        try {
//            // ユーザに紐づくActionを全て取得する
//            S21AuthorizationAction action = context.getAuthentication().getAuthorizationAction(className);
//
//            // リソースをreturnする
//            return action.getResources();
//        } catch (S21AuthorizationException e) {
//            // 空をreturnする
//            return new ArrayList<S21ProtectedResource>();
//        }
//    }
}
