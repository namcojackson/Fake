/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDDebugOutput;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
@SuppressWarnings("serial")
public class NLXC015008 implements Comparator, Serializable {

    /* */
    List sortKeyList = null;

    /* */
    private static final String KEY_SORT = "KEY";

    /* */
    private static final String KEY_ORDER = "ORDER";

    /* */
    private static final String KEY_TYPE = "TYPE";

    /* */
    public static final int VAL_SORT_TYPE_STR = 0;

    /* */
    public static final int VAL_SORT_TYPE_NUMBER = 1;

    /**
     */
    boolean sortTypeFlg = false;

    /**
     * <p>
     */
    @SuppressWarnings("unchecked")
    public void setSortKeyOrder(String[] sortKey, String[] orderFlg) {

        sortKeyList = new ArrayList();
        HashMap<String, String> keyMap = null;
        for (int i = 0; i < sortKey.length; i++) {
            keyMap = new HashMap<String, String>();
            keyMap.put(KEY_SORT, sortKey[i]);
            keyMap.put(KEY_ORDER, orderFlg[i]);
            sortKeyList.add(keyMap);
        }
    }

    /**
     * <p>
     */
    @SuppressWarnings("unchecked")
    public void setSortKeyOrder(String[] sortKey, String[] orderFlg, String[] type) {

        sortKeyList = new ArrayList();
        HashMap<String, String> keyMap = null;
        for (int i = 0; i < sortKey.length; i++) {
            keyMap = new HashMap<String, String>();
            keyMap.put(KEY_SORT, sortKey[i]);
            keyMap.put(KEY_ORDER, orderFlg[i]);
            keyMap.put(KEY_TYPE, type[i]);
            sortKeyList.add(keyMap);
        }
        sortTypeFlg = true;
    }

    /**
     * <p>
     */
    public int compare(Object obj, Object obj2) {

        int ret = 0;

        if (obj != null && obj2 != null) {
            if (sortTypeFlg) {
                ret = specifiedCompare(obj, obj2);
            } else {
                ret = noSpecifiedCompare(obj, obj2);
            }
        }
        return ret;
    }

    /**
     * <p>
     */
    private int noSpecifiedCompare(Object obj, Object obj2) {

        Object keyMap = null;
        String key = null;
        int order = 0;
        int ret = 0;

        for (int i = 0; i < sortKeyList.size(); i++) {

            keyMap = (Object) sortKeyList.get(i);
            key = (String) ((HashMap) keyMap).get(KEY_SORT);
            order = Integer.parseInt((String) ((HashMap) keyMap).get(KEY_ORDER));

            EZDDebugOutput.println(1, "key:" + key, this);
            EZDDebugOutput.println(1, "order:" + order, this);

            Object wkObj1 = ((HashMap) obj).get(key);
            Object wkObj2 = ((HashMap) obj2).get(key);
            ret = compareStr(wkObj1, wkObj2, order);

            if (ret != 0) {
                break;
            }
        }
        return ret;
    }

    /**
     * <p>
     */
    private int specifiedCompare(Object obj, Object obj2) {

        Object keyMap = null;
        String key = null;
        int order = 0;
        int type = 0;
        int ret = 0;

        for (int i = 0; i < sortKeyList.size(); i++) {

            keyMap = (Object) sortKeyList.get(i);
            key = (String) ((HashMap) keyMap).get(KEY_SORT);
            order = Integer.parseInt((String) ((HashMap) keyMap).get(KEY_ORDER));
            type = Integer.parseInt((String) ((HashMap) keyMap).get(KEY_TYPE));

            Object wkObj1 = ((HashMap) obj).get(key);
            Object wkObj2 = ((HashMap) obj2).get(key);

            if (type == VAL_SORT_TYPE_STR) {
                ret = compareStr(wkObj1, wkObj2, order);
            } else if (type == VAL_SORT_TYPE_NUMBER) {
                ret = compareNumber(wkObj1, wkObj2, order);
            } else {
                ret = compareStr(wkObj1, wkObj2, order);
            }

            if (ret != 0) {
                break;
            }
        }

        return ret;
    }

    /**
     * <p>
     */
    private int compareStr(Object obj, Object obj2, int order) {

        String pram1 = null;
        String pram2 = null;
        int ret = 0;

        switch (order) {
            case 1:
                pram1 = String.valueOf(obj2);
                pram2 = String.valueOf(obj);
                break;
            default:
                pram1 = String.valueOf(obj);
                pram2 = String.valueOf(obj2);
                break;
        }

        if (obj == null && obj2 != null) {
            ret = 1;
        } else if (obj != null && obj2 == null) {
            ret = -1;
        } else if (obj == null && obj2 == null) {
            ret = 0;
        } else {
            ret = pram1.compareTo(pram2);
        }

        return ret;
    }

    /**
     * <p>
     */
    private int compareNumber(Object obj, Object obj2, int order) {

        int ret = 0;

        if (obj == null && obj2 != null) {
            ret = 1;
        } else if (obj != null && obj2 == null) {
            ret = -1;
        } else if (obj == null && obj2 == null) {
            ret = 0;
        } else {
            BigDecimal pram1 = null;
            BigDecimal pram2 = null;
            switch (order) {
                case 1:
                    pram1 = new BigDecimal(String.valueOf(obj2));
                    pram2 = new BigDecimal(String.valueOf(obj));
                    break;
                default:
                    pram1 = new BigDecimal(String.valueOf(obj));
                    pram2 = new BigDecimal(String.valueOf(obj2));
                    break;
            }
            ret = pram1.compareTo(pram2);
        }
        return ret;
    }
}
