/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC501001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * Workflow Status update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFZC501001TokenObjectLine implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** PK */
    private BigDecimal arRfRqstPk;

    /**
     * getter : arRfRqstPk
     * @return BigDecimal
     */
    public BigDecimal getArRfRqstPk() {
        return arRfRqstPk;
    }

    /**
     * setter : arRfRqstPk
     * @param arRfRqstPk BigDecimal
     */
    public void setArRfRqstPk(BigDecimal arRfRqstPk) {
        this.arRfRqstPk = arRfRqstPk;
    }

}
