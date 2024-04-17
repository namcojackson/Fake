/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.common.NLX.NLXC025001;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;


/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/25/2009   Fujitsu           H.Haga           Create          N/A
 *</pre>
 */
public final class NLXC025001 {

    /**
     */
    private NLXC025001() {
    }


    /**
     */
    public static void outputLog(int logLevel, String msgId, String[] param, ONBATCH_TYPE onBatchType, Object cls) {

        if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {
            S21InfoLogOutput.println(msgId, param);
        } else {
            EZDDebugOutput.println(logLevel, S21MessageFunc.clspGetMessage(msgId, param), cls);
        }
    }
}
