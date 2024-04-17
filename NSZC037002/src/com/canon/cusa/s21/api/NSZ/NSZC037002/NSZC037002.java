/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC037002;

import parts.common.EZDPMsg;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Service Credit Check API ( <_ FSR Update API ). Super Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/09/2015   Hitachi         Y.Tsuchimoto    Create          NA
 * </pre>
 */
public class NSZC037002 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC037002() {
        super();
    }

    /**
     * execute
     * @param param EZDPMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(EZDPMsg param, final ONBATCH_TYPE onBatchType) {

    }
}
