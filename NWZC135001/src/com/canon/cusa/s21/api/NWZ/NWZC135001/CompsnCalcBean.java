package com.canon.cusa.s21.api.NWZ.NWZC135001;

import java.math.BigDecimal;

/**
 * <pre>
 * ShipFromBean                                                                                                                          
 *                                                                                                                          
 * Date         Company         Name            Create/Update   Defect No                                                                                                                           
 * ----------------------------------------------------------------------                                                                                                                           
 * 09/11/2009   Fujitsu         K.Kimura        Create          N/A                                                                                                                         
 *</pre>
 */

public class CompsnCalcBean {

    private String mdseCd = null;
    
    private String etaDt = null;
    
    private String psdDt = null;
    
    private String pddDt = null;
    
    private BigDecimal allocQty = BigDecimal.ZERO;
    
    private BigDecimal ordQty = BigDecimal.ZERO;
    
    private BigDecimal childQty = BigDecimal.ZERO;

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public BigDecimal getAllocQty() {
        return allocQty;
    }
    
    public void setAllocQty(BigDecimal allocQty) {
        this.allocQty = allocQty;
    }
    
    public String getEtaDt() {
        return etaDt;
    }
    
    public void setEtaDt(String etaDt) {
        this.etaDt = etaDt;
    }
    
    public String getPsdDt() {
        return psdDt;
    }
    
    public void setPsdDt(String psdDt) {
        this.psdDt = psdDt;
    }
    
    public String getPddDt() {
        return pddDt;
    }
    
    public void setPddDt(String pddDt) {
        this.pddDt = pddDt;
    }
    
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }
    
    public BigDecimal getChildQty() {
        return childQty;
    }
    
    public void setChildQty(BigDecimal childQty) {
        this.childQty = childQty;
    }

}
