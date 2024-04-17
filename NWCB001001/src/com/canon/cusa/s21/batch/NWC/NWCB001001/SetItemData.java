package com.canon.cusa.s21.batch.NWC.NWCB001001;

import static java.math.BigDecimal.ZERO;

import java.io.Serializable;
import java.math.BigDecimal;

public class SetItemData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** mdse composition type cd */
    private String mdseCmpsnTpCd;
    /** parent mdse cd */
    private String prntMdseCd;
    /** child mdse cd */
    private String childMdseCd;
    /** child order take mdse cd */
    private String childOrdTakeMdseCd;
    /** shipping plan parent mdse cd */
    private String spPrntMdseCd;
    /** shipping plan child mdse cd */
    private String spChildMdseCd;
    /** child mdse qty */
    private BigDecimal childMdseQty = ZERO;
    /** inventory control flag */
    private String invtyCtrlFlg;
    /** shipping plan qty */
    private BigDecimal ordQty = ZERO;
    /** set parent qty */
    private BigDecimal setItemQty = ZERO;
    /** recognition processed qty */
    private BigDecimal prcdQty = ZERO;

    public SetItemData() {
    }

    public String getChildMdseCd() {
        return childMdseCd;
    }

    public BigDecimal getChildMdseQty() {
        return childMdseQty;
    }

    public String getChildOrdTakeMdseCd() {
        return childOrdTakeMdseCd;
    }

    public String getInvtyCtrlFlg() {
        return invtyCtrlFlg;
    }

    public String getMdseCmpsnTpCd() {
        return mdseCmpsnTpCd;
    }

    public BigDecimal getOrdQty() {
        return ordQty;
    }

    public BigDecimal getPrcdQty() {
        return prcdQty;
    }

    public String getPrntMdseCd() {
        return prntMdseCd;
    }

    public BigDecimal getSetItemQty() {
        return setItemQty;
    }

    public String getSpChildMdseCd() {
        return spChildMdseCd;
    }

    public String getSpPrntMdseCd() {
        return spPrntMdseCd;
    }

    public void setChildMdseCd(String childMdseCd) {
        this.childMdseCd = childMdseCd;
    }

    public void setChildMdseQty(BigDecimal childMdseQty) {
        this.childMdseQty = childMdseQty;
    }

    public void setChildOrdTakeMdseCd(String childOrdTakeMdseCd) {
        this.childOrdTakeMdseCd = childOrdTakeMdseCd;
    }

    public void setInvtyCtrlFlg(String invtyCtrlFlg) {
        this.invtyCtrlFlg = invtyCtrlFlg;
    }

    public void setMdseCmpsnTpCd(String mdseCmpsnTpCd) {
        this.mdseCmpsnTpCd = mdseCmpsnTpCd;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    public void setPrcdQty(BigDecimal prcdQty) {
        this.prcdQty = prcdQty;
    }

    public void setPrntMdseCd(String prntMdseCd) {
        this.prntMdseCd = prntMdseCd;
    }

    public void setSetItemQty(BigDecimal setItemQty) {
        this.setItemQty = setItemQty;
    }

    public void setSpChildMdseCd(String spChildMdseCd) {
        this.spChildMdseCd = spChildMdseCd;
    }

    public void setSpPrntMdseCd(String spPrntMdseCd) {
        this.spPrntMdseCd = spPrntMdseCd;
    }

}
