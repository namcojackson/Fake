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
public class NLZC300001InnerOutDataComparator implements Comparator<NLZC300001InnerOutData>, Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * Compare by mdseCd -> glblCmpyCd -> whCd -> locationStatus -> stockStatus
     * 
     * @param arg1 NLZC300001InternalOutData
     * @param arg0 NLZC300001InternalOutData
     * @return int
     */
    public int compare(NLZC300001InnerOutData arg0, NLZC300001InnerOutData arg1) {
        int result = 0;

        result = arg0.getMdseCd().compareTo(arg1.getMdseCd());
        if (result != 0) {
            return result;
        }

        result = arg0.getGlblCmpyCd().compareTo(arg1.getGlblCmpyCd());
        if (result != 0) {
            return result;
        }

        result = arg0.getWhCd().compareTo(arg1.getWhCd());
        if (result != 0) {
            return result;
        }

        result = arg0.getLocationStatus().compareTo(arg1.getLocationStatus());
        if (result != 0) {
            return result;
        }

        result = arg0.getStockStatus().compareTo(arg1.getStockStatus());

        return result;

    }
}
