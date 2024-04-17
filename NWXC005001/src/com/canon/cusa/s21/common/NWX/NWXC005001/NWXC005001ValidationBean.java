/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC005001;

import java.math.BigDecimal;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWXC005001PMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2009   Fujitsu         T.Kaneda        Create          N/A
 * 07/23/2012   Fujitsu         S.Iidaka        Create          N/A
 * 09/10/2015   Fujitsu         T.Yoshida       Update          For CSA
 *</pre>
 */
public class NWXC005001ValidationBean {

    /** NWXC005001PMsg */
    private NWXC005001PMsg inputPMsg;

    /** CPOTMsg */
    private CPOTMsg cTMsg;

    /** CPO_DTLTMsg */
    private CPO_DTLTMsg cdTMsg;

    /** SHPG_PLNTMsg */
    private SHPG_PLNTMsg spTMsg;

    /** Order Quantity */
    private BigDecimal ordQty;

    /** DS_CPO_CONFIGTMsg */
    private DS_CPO_CONFIGTMsg dsccTMsg;

    /**
     * Constructor
     * @param inputPMsg NWXC005001PMsg
     * @param cTMsg CPOTMsg
     * @param cdTMsg CPO_DTLTMsg
     * @param spTMsg SHPG_PLNTMsg
     */
    public NWXC005001ValidationBean(NWXC005001PMsg inputPMsg, CPOTMsg cTMsg, CPO_DTLTMsg cdTMsg, SHPG_PLNTMsg spTMsg) {
        this.inputPMsg = inputPMsg;
        this.cTMsg = cTMsg;
        this.cdTMsg = cdTMsg;
        this.spTMsg = spTMsg;
    }

    /**
     * Constructor
     * @param inputPMsg NWXC005001PMsg
     * @param cTMsg CPOTMsg
     * @param cdTMsg CPO_DTLTMsg
     * @param spTMsg SHPG_PLNTMsg
     * @param dsccTMsg DS_CPO_CONFIGTMsg
     */
    public NWXC005001ValidationBean(NWXC005001PMsg inputPMsg, CPOTMsg cTMsg, CPO_DTLTMsg cdTMsg, SHPG_PLNTMsg spTMsg, DS_CPO_CONFIGTMsg dsccTMsg) {
        this.inputPMsg = inputPMsg;
        this.cTMsg = cTMsg;
        this.cdTMsg = cdTMsg;
        this.spTMsg = spTMsg;
        this.dsccTMsg = dsccTMsg;
    }

    /**
     * get NWXC005001PMsg
     * @return NWXC005001PMsg
     */
    public NWXC005001PMsg getInputPMsg() {
        return inputPMsg;
    }

    /**
     * get CPOTMsg
     * @return CPOTMsg
     */
    public CPOTMsg getCTMsg() {
        return cTMsg;
    }

    /**
     * get CPO_DTLTMsg
     * @return CPO_DTLTMsg
     */
    public CPO_DTLTMsg getCdTMsg() {
        return cdTMsg;
    }

    /**
     * get DS_CPOTMsg
     * @return DS_CPOTMsg
     */
    public CPOTMsg getDscTMsg() {
        return cTMsg;
    }

    /**
     * get DS_CPO_DTLTMsg
     * @return DS_CPO_DTLTMsg
     */
    public CPO_DTLTMsg getDscdTMsg() {
        return cdTMsg;
    }

    /**
     * get SHPG_PLNTMsg
     * @return SHPG_PLNTMsg
     */
    public SHPG_PLNTMsg getSpTMsg() {
        return spTMsg;
    }

    /**
     * get DS_CPO_CONFIGTMsg
     * @return DS_CPO_CONFIGTMsg
     */
    public DS_CPO_CONFIGTMsg getDsccTMsg() {
        return dsccTMsg;
    }

    /**
     * get Order Quantity
     * @return Order Quantity
     */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /**
     * set Order Quantity
     * @param ordQty Order Quantity
     */
    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

}
