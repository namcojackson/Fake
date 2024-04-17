/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NAZ.NAZC006001;

import java.util.List;

import business.parts.NAZC006001PMsg;

import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 * <pre>
 * Retail Validation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/23/2011   Fujitsu         N.Yamaguchi     Create          N/A
 * 01/13/2012   CSAI            T.Hakodate      Update          #1601
 * 12/06/2013   Fujitsu         Y.Taoka         Update          DEF.2957
 *</pre>
 */

public class NAZC006001 extends S21ApiCommonBase implements NAZC006001Constant {

    /** constructor */
    public NAZC006001() {
        super();
    }

    /**
     * NAZC006001PMsg Validation API
     * @param param NAZC006001PMsg Interface
     * @param onBatch ONBATCH_TYPE Interface
     */
    public void execute(final NAZC006001PMsg param, final ONBATCH_TYPE onBatch) {
    }

    /**
     * NAZC006001PMsg Allocation API (Changeable list type)
     * @param params NAZC006001PMsg Interface
     * @param onBatch ONBATCH_TYPE Interface
     */
    public void execute(final List<NAZC006001PMsg> params, final ONBATCH_TYPE onBatch) {

    }
}
