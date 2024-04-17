/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB045001;

import java.math.BigDecimal;

/**
 *<pre>
 * Model Duration Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class ModelDurationBean {

    /** MDL_ID*/
    private BigDecimal mdlId;

    /** MDL_DURN_TM_NUM*/
    private BigDecimal mdlDurnTmNum;

    /**
     * get  MDL_ID.
     * @return MDL_ID
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * set  MDL_ID.
     * @param mdlId MDL_ID
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    /**
     * get  MDL_DURN_TM_NUM.
     * @return MDL_DURN_TM_NUM
     */
    public BigDecimal getMdlDurnTmNum() {
        return mdlDurnTmNum;
    }

    /**
     * set  MDL_DURN_TM_NUM.
     * @param mdlDurnTmNum MDL_DURN_TM_NUM
     */
    public void setMdlDurnTmNum(BigDecimal mdlDurnTmNum) {
        this.mdlDurnTmNum = mdlDurnTmNum;
    }
}
