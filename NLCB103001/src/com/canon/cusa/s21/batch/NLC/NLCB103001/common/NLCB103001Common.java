/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB103001.common;


import com.canon.cusa.s21.batch.NLC.NLCB103001.constant.NLCB103001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import parts.common.EZDDebugOutput;

import java.util.Arrays;
import java.util.List;


/**
 *<pre>
 *  Stock In Data of the Day (Common)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/15   Fujitsu         K.Fujita          Create          N/A
 * 2013/11/20   Fujitsu         A.Wada            Create        Def#3155
 *</pre>
 */
public class NLCB103001Common {

    /**
     * <pre>
     * </pre>
     * @param pStr          Arbitrary string output when debug log is output
     * @param pObjClass     Method call class object output when debug log is output
     */
    public static void dbgPlintln(String pStr, Object pObjClass) {

        EZDDebugOutput.println(1, NLCB103001Constant.PGM_ID + pStr, pObjClass);
    }

    // START 2013/11/20 A.Wada [Add Def#3155]
    /**
     * <pre>
     * </pre>
     * @param item          String 
     * @return List<String>
     */
    public static List<String> splitValue(String item) {
        if (ZYPCommonFunc.hasValue(item)) {
            String[] ss = item.split(",");
            if (ss.length > 0) {

                List<String> list = Arrays.asList(ss);
                if (list.size() > 0) {
                    return list;
                }
            }
        }
        return null;
    }
    // END   2013/11/20 A.Wada [Add Def#3155]

}
