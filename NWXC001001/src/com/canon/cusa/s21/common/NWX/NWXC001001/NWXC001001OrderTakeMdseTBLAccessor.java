/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * It is a class that offers the function to acquire the representative
 * merchandise code.
 * 
 * The offered function is as follows.
 * - Acquisition of representative merchandise code
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         S.Sugino        Create          N/A
 * 10/28/2009   Fujitsu         K.Tajima        Update          N/A. added check logic of 'ORD_TAKE_MDSE_CD' digit.
 * 11/09/2009   Fujitsu         S.Sugino        Update          N/A. Use of cache.
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXOrdTakeMdseTMsgThreadLocalCache;

public class NWXC001001OrderTakeMdseTBLAccessor {

    /**
     * <pre>
     * The representative merchandise code is acquired.
     * It retrieves it to the ORD_TAKE_MDSE table subject to the parameter.
     * MDSE_CD of the ORD_TAKE_MDSE table is returned as a representative
     * merchandise code if there is a result.
     * "ordTakeMdseCd" of the parameter is returned if there is no result.
     * 
     * <u>Required parameter</u>
     * - Global Company Code
     * - Order Take Merchandise Code
     * 
     * It doesn't process when the required parameter is a unsetting,
     * and "ordTakeMdseCd" of the parameter is returned.
     * </pre>
     * 
     * @param glblCmpyCd Global Company Code
     * @param ordTakeMdseCd Order Take Merchandise Code
     * @return ORD_TAKE_MDSE.MDSE_CD or "ordTakeMdseCd" of parameter
     */
    public static String findMdseCd(String glblCmpyCd, String ordTakeMdseCd) {

        if (!hasValue(glblCmpyCd) || !hasValue(ordTakeMdseCd)) {
            return ordTakeMdseCd;
        }

        String mdseCd = ordTakeMdseCd;

        final ORD_TAKE_MDSETMsg ordTakeMdseTMsg = NWXOrdTakeMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (ordTakeMdseTMsg != null) {
            mdseCd = ordTakeMdseTMsg.mdseCd.getValue();
        }

        return mdseCd;
    }

}
