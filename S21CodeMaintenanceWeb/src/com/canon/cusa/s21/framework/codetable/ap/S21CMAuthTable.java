package com.canon.cusa.s21.framework.codetable.ap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import com.canon.cusa.s21.framework.codetable.fw.S21NEAuthAdapter;
import com.canon.cusa.s21.framework.security.S21AuthorizationAction;
import com.canon.cusa.s21.framework.security.S21AuthorizationException;
import com.canon.cusa.s21.framework.security.S21PermissionType;
import com.canon.cusa.s21.framework.security.S21ProtectedResource;
import com.canon.cusa.s21.framework.security.as.ResourceClass;
import com.canon.cusa.s21.framework.security.authorization.S21PermissionAttribute;
import com.canon.cusa.s21.framework.security.authorization.S21PermissionValues;
import com.canon.cusa.s21.framework.security.ext.as.S21ASAuthorizationAction;

/**
 * S21Authorizationに対する共通的な処理。<br>
 * プロファイル取得部品、S21Authorizationをラップする。<br>
 * @author Administrator
 */
public class S21CMAuthTable extends S21NEAuthAdapter {

    /** リソースクラス コードテーブル用定数値 */
    private static final String CLASS_CODE_TABLE = "CODE TABLE";

    /** パーミッション情報 */
    Map<String, List<String>> mergePermVals = new HashMap<String, List<String>>();

    /** Action情報 */
    Map<String, List<S21AuthorizationAction>> mapAuthActions = null;;

    /**
     * 現在ログイン中のユーザが参照可能テーブルであるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 参照可能であればtrue、そうでなければfalse
     */
    public boolean isViewableTable(String tableName) {

        // 紐づくPermissionを取得する
        List<String> listPermissions = mergeTablePermissionValues(tableName);

        // READが含まれているかチェックする
        return listPermissions.contains(S21PermissionValues.ACL_READ)
            || listPermissions.contains(S21PermissionValues.ACL_UPDATE);
    }

    /**
     * 現在ログイン中のユーザが編集可能テーブルであるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 編集可能であればtrue、そうでなければfalse
     */
    public boolean isEditableTable(String tableName) {

        // 紐づくPermissionを取得する
        List<String> listPermissions = mergeTablePermissionValues(tableName);

        // UPDATEが含まれているかチェックする
        return listPermissions.contains(S21PermissionValues.ACL_UPDATE);
    }

    /**
     * テーブルに紐づく全てのPermissionを取得する。<br>
     * 登録されていない場合は空のListをreturnする。<br>
     * @param tableName テーブル名称
     * @return S21PermissionTypeを保持するList
     * @throws S21AuthorizationException
     */
    private List<String> mergeTablePermissionValues(String tableName) {

        List<String> list = mergePermVals.get(tableName);

        if (list != null) {
            return list;
        }

        list = new ArrayList<String>();

        List<S21AuthorizationAction> actions = getActionTable(tableName);

        if (actions == null) {
            return list;
        }

        for (S21AuthorizationAction action : actions) {
            try {
                Attributes attrs = ((S21ASAuthorizationAction) action).getPermissionAttributes(S21PermissionType.ACL);

                if (attrs != null) {
                    Attribute attr = attrs.get(S21PermissionAttribute.ACL_MASK);

                    NamingEnumeration en = attr.getAll();
                    while (en.hasMoreElements()) {
                        String value = en.next().toString();
                        list.add(value);
                    }
                }
            } catch (NamingException e) {
                return list;
            } catch (S21AuthorizationException e) {
                return list;
            }
        }

        mergePermVals.put(tableName, list);

        return list;
    }

    /**
     * テーブル単位のアクション情報を作成。<br>
     */
    private void createActionTableMap() {

        mapAuthActions = new HashMap<String, List<S21AuthorizationAction>>();

        List<S21AuthorizationAction> rootActions = getResourceAction(CLASS_CODE_TABLE, ResourceClass.GROUP);

        try {
            for (S21AuthorizationAction action : rootActions) {

                // リソースを取得
                List<S21ProtectedResource> listResources = action.getResources();

                // getResourceActionで直接テーブル情報を取得しても問題ないはず
                // 念のためチェックしてから取得する

                // CODE TABLEの子リソースに指定のテーブルが存在していればActionを取得する
                for (S21ProtectedResource resource : listResources) {
                    String name = resource.getResourceName();
                    mapAuthActions.put(name, getResourceAction(name, ResourceClass.DATA));
                }
            }
        } catch (S21AuthorizationException e) {
            // No Value
        }
    }

    /**
     * コードテーブルとして登録されているリソース情報(Action)を取得する。<br>
     * 登録されていない場合は空のListをreturnする。<br>
     * @param tableName テーブル名称
     * @return S21AuthorizationActionを保持するList
     */
    private List<S21AuthorizationAction> getActionTable(String tableName) {

        if (mapAuthActions == null) {
            createActionTableMap();
        }

        return mapAuthActions.get(tableName);

//        List<S21AuthorizationAction> list = new ArrayList<S21AuthorizationAction>();
//
//        List<S21AuthorizationAction> rootActions = getResourceAction(CLASS_CODE_TABLE, ResourceClass.GROUP);
//
//        try {
//            for (S21AuthorizationAction action : rootActions) {
//
//                // リソースを取得
//                List<S21ProtectedResource> listResources = action.getResources();
//
//                // getResourceActionで直接テーブル情報を取得しても問題ないはず
//                // 念のためチェックしてから取得する
//
//                // CODE TABLEの子リソースに指定のテーブルが存在していればActionを取得する
//                for (S21ProtectedResource resource : listResources) {
//                    if (resource.getResourceName().equals(tableName)) {
//                        list.addAll(getResourceAction(tableName, ResourceClass.DATA));
//                    }
//                }
//            }
//
//        } catch (S21AuthorizationException e) {
//            return list;
//        }
//
//        return list;

//        List<S21AuthorizationAction> list = new ArrayList<S21AuthorizationAction>();
//
//        List<S21ProtectedResource> resources = getResources(CLASS_CODE_TABLE);
//
//        // CODE TABLEの子リソースに指定のテーブルが存在していればActionを取得する
//        for (S21ProtectedResource resource : resources) {
//            if (resource.getResourceName().equals(tableName)) {
//                list.addAll(getResourceAction(tableName, ResourceClass.DATA));
//            }
//        }
//
//        return list;
    }
}
