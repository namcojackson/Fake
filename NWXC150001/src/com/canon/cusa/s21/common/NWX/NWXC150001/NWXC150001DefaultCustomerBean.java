/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Get Default Customer Data 04 Mode
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/28   Fujitsu         S.Takami        Create          S21_NA#8659
 * 2019/01/31   Fujitsu         Hd.Sugawara     Update          S21_NA#30097
 * </pre>
 */
public class NWXC150001DefaultCustomerBean {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** DS Trx Rule Type Code */
    private String dsTrxRuleTpCd = null;

    /** Account Number */
    private String dsAcctNum = null;

    /** Ship to Customer Code */
    private String shipToCustCd = null;

    /** Bill To Customer Code */
    private String billToCustCd = null;

    /** OnBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** Mode */
    private String xxMode = null;

    // Add Start 2019/01/31 QC#30097
    /** Select Flag */
    private String xxSelFlg = null;
    // Add End 2019/01/31 QC#30097

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public String getDsTrxRuleTpCd() {
        return dsTrxRuleTpCd;
    }

    public void setDsTrxRuleTpCd(String dsTrxRuleTpCd) {
        this.dsTrxRuleTpCd = dsTrxRuleTpCd;
    }

    public String getDsAcctNum() {
        return dsAcctNum;
    }

    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public ONBATCH_TYPE getOnBatchType() {
        return onBatchType;
    }

    public void setOnBatchType(ONBATCH_TYPE onBatchType) {
        this.onBatchType = onBatchType;
    }

    public String getXxMode() {
        return xxMode;
    }

    public void setXxMode(String xxMode) {
        this.xxMode = xxMode;
    }

    // Add Start 2019/01/31 QC#30097
    public String getXxSelFlg() {
        return xxSelFlg;
    }

    public void setXxSelFlg(String xxSelFlg) {
        this.xxSelFlg = xxSelFlg;
    }
    // Add End 2019/01/31 QC#30097
}
