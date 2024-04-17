/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.common.NWX.NWXC100001;

/**
 * <pre>
 * Billing Block check.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/12   Fujitsu         S.Tsunaki       Create          N/A
 *</pre>
 */
public class NWXC100001CheckHoldConditionBean {

    /** Global Company Code */
    private String glblCmpyCd;

    /** CPO_ORD_NUM / TRX_HDR_NUM */
    private String trxHdrNum;

    /** CPO_DTL_LINE_NUM / TRX_LINE_NUM */
    private String trxLineNum;

    /** CPO_DTL_LINE_SUB_NUM / TRX_LINE_SUB_NUM */
    private String trxLineSubNum;

    /** TRX_SRC_TP_CD */
    private String trxSrcTpCd;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    /** PO_REQ_FLG */
    private String poReqFlg;

    /** MDSE_CD */
    private String mdseCd;

    /** SHIP_CPLT_CD */
    private String shipCpltCd;

    /** SHPG_PLN_NUM */
    private String setShpgPlnNum;

    /**
     * Contructor.
     * @param glblCmpyCd Global Company Code
     * @param trxHdrNum CPO_ORD_NUM / TRX_HDR_NUM
     * @param trxLineNum CPO_DTL_LINE_NUM / TRX_LINE_NUM
     * @param trxLineSubNum CPO_DTL_LINE_SUB_NUM / TRX_LINE_SUB_NUM
     * @param trxSrcTpCd TRX_SRC_TP_CD
     * @param shpgPlnNum SHPG_PLN_NUM
     * @param poReqFlg PO_REQ_FLG
     * @param mdseCd MDSE_CD
     * @param shipCpltCd SHIP_CPLT_CD
     * @param setShpgPlnNum SHPG_PLN_NUM
     */
    public NWXC100001CheckHoldConditionBean(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum, String trxSrcTpCd, String shpgPlnNum, String poReqFlg, String mdseCd, String shipCpltCd, String setShpgPlnNum) {
        this.glblCmpyCd = glblCmpyCd;
        this.trxHdrNum = trxHdrNum;
        this.trxLineNum = trxLineNum;
        this.trxLineSubNum = trxLineSubNum;
        this.trxSrcTpCd = trxSrcTpCd;
        this.shpgPlnNum = shpgPlnNum;
        this.poReqFlg = poReqFlg;
        this.mdseCd = mdseCd;
        this.shipCpltCd = shipCpltCd;
        this.setShpgPlnNum = setShpgPlnNum;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }
    /**
     * @param glblCmpyCd Setter glblCmpyCd
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }
    /**
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }
    /**
     * @param trxHdrNum Setter trxHdrNum
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }
    /**
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }
    /**
     * @param trxLineNum Setter trxLineNum
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }
    /**
     * @return trxLineSubNum
     */
    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }
    /**
     * @param trxLineSubNum Setter trxLineSubNum
     */
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }
    /**
     * @return trxSrcTpCd
     */
    public String getTrxSrcTpCd() {
        return trxSrcTpCd;
    }
    /**
     * @param trxSrcTpCd Setter trxSrcTpCd
     */
    public void setTrxSrcTpCd(String trxSrcTpCd) {
        this.trxSrcTpCd = trxSrcTpCd;
    }
    /**
     * @return shpgPlnNum
     */
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }
    /**
     * @param shpgPlnNum Setter shpgPlnNum
     */
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }
    /**
     * @return poReqFlg
     */
    public String getPoReqFlg() {
        return poReqFlg;
    }
    /**
     * @param poReqFlg Setter poReqFlg
     */
    public void setPoReqFlg(String poReqFlg) {
        this.poReqFlg = poReqFlg;
    }
    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }
    /**
     * @param mdseCd Setter mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }
    /**
     * @return shipCpltCd
     */
    public String getShipCpltCd() {
        return shipCpltCd;
    }
    /**
     * @param shipCpltCd Setter shipCpltCd
     */
    public void setShipCpltCd(String shipCpltCd) {
        this.shipCpltCd = shipCpltCd;
    }
    /**
     * @return setShpgPlnNum
     */
    public String getSetShpgPlnNum() {
        return setShpgPlnNum;
    }
    /**
     * @param setShpgPlnNum Setter setShpgPlnNum
     */
    public void setSetShpgPlnNum(String setShpgPlnNum) {
        this.setShpgPlnNum = setShpgPlnNum;
    }
}
