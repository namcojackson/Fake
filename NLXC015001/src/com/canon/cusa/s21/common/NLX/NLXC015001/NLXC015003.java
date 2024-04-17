/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import java.util.ArrayList;
import java.util.List;

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
public class NLXC015003 {

    /* */
    public static final String ASC = "ASC";

    /* */
    public static final String DESC = "DESC";

    /* */
    private List<String> keyList = new ArrayList<String>();

    /* */
    private List<String> orderByList = new ArrayList<String>();

    /**
     * 
     * <pre>
     * 
     *
     * S21SortKey sortKey = new S21SortKey();
     * sortKey.add( "acctCd", S21SortKey.DESC );
     * sortKey.add( "invQty", S21SortKey.ASC );
     * </pre>
     * 
     */
    public void add(String itemNm, String orderBy) {
        if (orderBy == null || (!ASC.equals(orderBy.trim()) && !DESC.equals(orderBy.trim()))) {
        }
        keyList.add(itemNm);
        orderByList.add(orderBy);
    }

    int size() {
        return keyList.size();
    }

    String getKey(int i) {
        String value = "";
        if (size() > i) {
            value = (String) keyList.get(i);
        }
        return value;
    }

    String getOrderBy(int i) {
        String orderBy = ASC;
        if (size() > i) {
            orderBy = orderByList.get(i);
        }
        return orderBy;
    }
}
