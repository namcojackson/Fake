package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.io.Serializable;

import business.db.BILL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;

public class NWXC220001CustomerBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** BILL_TO_CUSTTMsg */
    public BILL_TO_CUSTTMsg billToCustTMsg;

    /** SHIP_TO_CUSTTMsg */
    public SHIP_TO_CUSTTMsg shipToCustTMsg;

    /** Ship Cnty Nm */
    public String shipCntyNm;

    /** SELL_TO_CUSTTMsg */
    public SELL_TO_CUSTTMsg sellToCustTMsg;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[BILL_TO_CUSTTMsg]\n").append(this.billToCustTMsg.toString()).append("\n");
        sb.append("[SHIP_TO_CUSTTMsg]\n").append(this.shipToCustTMsg.toString()).append("\n");
        sb.append("Ship Cnty Nm : ").append(this.shipCntyNm).append("\n");
        sb.append("[SELL_TO_CUSTTMsg]\n").append(this.sellToCustTMsg.toString()).append("\n");

        return sb.toString();
    }

}
