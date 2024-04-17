/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parts.common.EZDItem;

/**
 * <pre>
 * </pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
class NLXC015004 {

    /* */
    private List<String> keyList = new ArrayList<String>();

    /* */
    private HashMap<String, Object[]> sortItemMap = new HashMap<String, Object[]>();

    void add(String key, String orderBy, EZDItem ezdItem, int itemType) {
        keyList.add(key);
        sortItemMap.put(key, new Object[] {ezdItem, orderBy, Integer.valueOf(itemType) });
    }

    EZDItem getEZDItem(String key) {
        return (EZDItem) ((Object[]) sortItemMap.get(key))[0];
    }

    String getOrderBy(String key) {
        return (String) ((Object[]) sortItemMap.get(key))[1];
    }

    int getItemType(String key) {
        return ((Integer) ((Object[]) sortItemMap.get(key))[2]).intValue();
    }

    String[] getKeys() {
        return (String[]) keyList.toArray(new String[0]);
    }

}
