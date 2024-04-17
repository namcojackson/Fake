/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC300001;

import java.io.Serializable;
import java.util.Comparator;

/**
 * <pre>
 * Inventory Reference API.
 *
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/28/2012   Fujitsu         Y.Mori          Create          N/A
 *</pre>
 */
public class NLZC300001InnerParamComparator implements Comparator<NLZC300001InnerParam>, Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * Comare by sellToCustCd -> rsd
     * 
     * @param arg0 NLZC300001InternalParam
     * @param arg1 NLZC300001InternalParam
     * @return int
     */
    public int compare(NLZC300001InnerParam arg0, NLZC300001InnerParam arg1) {
        int result = 0;

        if (arg0.getSellToCustCd() != null && arg1.getSellToCustCd() != null) {
            result = arg0.getSellToCustCd().compareTo(arg1.getSellToCustCd());

        } else if (arg0.getSellToCustCd() != null && arg1.getSellToCustCd() == null) {
            result = 1;

        } else if (arg0.getSellToCustCd() == null && arg1.getSellToCustCd() != null) {
            result = -1;
        }

        if (result != 0) {
            return result;
        }

        if (arg0.getRsd() != null && arg1.getRsd() != null) {
            result = arg0.getRsd().compareTo(arg1.getRsd());

        } else if (arg0.getRsd() != null && arg1.getRsd() == null) {
            result = 1;

        } else if (arg0.getRsd() == null && arg1.getRsd() != null) {
            result = -1;
        }

        return result;

    }
}
