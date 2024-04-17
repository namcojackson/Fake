/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

import java.io.Serializable;

import business.parts.NWZC035002PMsg;

import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * OrderDataBean
 * This class is used to store the retrieval result of the retrieval using the parameter. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * </pre>
 */
public class OrderDataBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param msgMap S21ApiMessageMap
     * @param pMsg2 NWZC035002PMsg
     */
    public OrderDataBean(S21ApiMessageMap msgMap, NWZC035002PMsg pMsg2) {
        this.msgMap = msgMap;
        this.pMsg2 = pMsg2;
    }

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** NWZC035002PMsg */
    private NWZC035002PMsg pMsg2;

    /**
     * @return msgMap
     */
    public S21ApiMessageMap getMsgMap() {
        return msgMap;
    }

    /**
     * @return pMsg2
     */
    public NWZC035002PMsg getNWZC035002PMsg() {
        return pMsg2;
    }

}
