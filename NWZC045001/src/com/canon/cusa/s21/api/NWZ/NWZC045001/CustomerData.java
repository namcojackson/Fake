package com.canon.cusa.s21.api.NWZ.NWZC045001;

import java.io.Serializable;

public class CustomerData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String billToCustCd = null;

    private String sellToCustCd = null;

    //  100202 Kaneda add begin    
    private String payerCustCd = null;
    //  100202 Kaneda add end
    
    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }
//  100202 Kaneda add begin
    public String getPayerCustCd() {
        return payerCustCd;
    }

    public void setPayerCustCd(String payerCustCd) {
        this.payerCustCd = payerCustCd;
    }
//  100202 Kaneda add end
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("billToCustCd=").append(this.getBillToCustCd()).append(',');
        sb.append("sellToCustCd=").append(this.getSellToCustCd());
        return sb.toString();
    }
}
