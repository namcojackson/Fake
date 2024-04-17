/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFD.NFZC500001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * Write-Off WF Status Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NFZC500001TokenObjectLine implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** Primary Key */
    private BigDecimal pk;   // AR_WRT_OFF_RQST_PK

    //--- start add 2016/02/17
    private String billToCustCd;

    private String cpoOrdNum;

    private String arTrxNum;

    private String arTrxTpCd;    
    
    private BigDecimal arTrxBalPk;
    
    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    public String getArTrxNum() {
        return arTrxNum;
    }

    public void setArTrxNum(String arTrxNum) {
        this.arTrxNum = arTrxNum;
    }

    public String getArTrxTpCd() {
        return arTrxTpCd;
    }

    public void setArTrxTpCd(String arTrxTpCd) {
        this.arTrxTpCd = arTrxTpCd;
    }
    
    public BigDecimal getArTrxBalPk() {
        return arTrxBalPk;
    }

    public void setArTrxBalPk(BigDecimal arTrxBalPk) {
        this.arTrxBalPk = arTrxBalPk;
    }
    //--- end 2016/02/17

    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * get Primary Key
     * @return BigDecimal
     */
    public BigDecimal getPk() {
        return pk;
    }

    /**
     * set Primary Key
     * @param pk BigDecimal
     */
    public void setPk(BigDecimal pk) {
        this.pk = pk;
    }

}
