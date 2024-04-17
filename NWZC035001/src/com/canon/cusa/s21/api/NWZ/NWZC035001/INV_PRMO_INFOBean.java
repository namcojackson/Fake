/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

import java.io.Serializable;
import java.util.List;

import business.db.INV_PRMO_INFOTMsg;

import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * INV_PRMO_INFOBean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * </pre>
 */
public class INV_PRMO_INFOBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param msgMap S21ApiMessageMap
     * @param invPrmoInfoTMsgList List of INV_PRMO_INFOTMsg
     */
    public INV_PRMO_INFOBean(S21ApiMessageMap msgMap, List<INV_PRMO_INFOTMsg> invPrmoInfoTMsgList) {
        this.msgMap = msgMap;
        this.invPrmoInfoTMsgList = invPrmoInfoTMsgList;
    }

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** List of INV_PRMO_INFOTMsg */
    private List<INV_PRMO_INFOTMsg> invPrmoInfoTMsgList;

    /**
     * @return invPrmoInfoTMsgList
     */
    public List<INV_PRMO_INFOTMsg> getInvPrmoInfoTMsgList() {
        return invPrmoInfoTMsgList;
    }

    /**
     * @return msgMap
     */
    public S21ApiMessageMap getMsgMap() {
        return msgMap;
    }

}
