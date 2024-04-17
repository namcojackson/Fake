/**
 *  <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC400001;

import java.math.BigDecimal;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;

/**
 * <pre>
 * NWZC4000 : Return Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         S.Yoshida       Create          N/A
 * 2017/04/12   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1
 *</pre>
 */
public class ValidationManagerBean extends NWXC005001ValidationBean {

    /**
     * <pre>
     * Constructor
     * </pre
     * @param inputPMsg NWXC005001PMsg
     * @param cTMsg CPOTMsg
     * @param cdTMsg CPO_DTLTMsg
     * @param spTMsg SHPG_PLNTMsg
     */
    public ValidationManagerBean(//
            NWXC005001PMsg inputPMsg //
            , CPOTMsg cTMsg //
            , CPO_DTLTMsg cdTMsg //
            , SHPG_PLNTMsg spTMsg) {
        super(inputPMsg, cTMsg, cdTMsg, spTMsg);
    }

    /**
     * <pre>
     * Constructor
     * </pre
     * @param inputPMsg
     * @param cTMsg
     * @param cdTMsg
     * @param spTMsg
     * @param dccTMsg
     */
    public ValidationManagerBean(//
            NWXC005001PMsg inputPMsg //
            , CPOTMsg cTMsg //
            , CPO_DTLTMsg cdTMsg //
            , SHPG_PLNTMsg spTMsg //
            , DS_CPO_CONFIGTMsg dccTMsg) {
        super(inputPMsg, cTMsg, cdTMsg, spTMsg);
    }

    /** Order Quantity */
    private BigDecimal ordQty = null;

    /**
     * <pre>
     * Get Order Quantity
     * </pre>
     * @return Order Quantity
     */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /**
     * <pre>
     * Set Order Quantity
     * </pre>
     * @param ordQty Order Quantity
     */
    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

}
