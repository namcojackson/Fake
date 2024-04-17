package com.canon.cusa.s21.api.NLZ.NLZC205001;

import java.io.Serializable;

/**
 *<pre>
 * DS SO API
 * SO-Serial Related Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2013   Fujitsu         J.Yasukawa      Create          OCE WDS R-WH003
 * 11/02/2015   CITS            M.Ito           Update          N/A
 * 03/13/2017   CITS            K.Ogino         Update          DS table integration
 *</pre>
 */
public class SoSerialInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** TRX_SRC_TP_CD*/
    private String             trxSrcTpCd;

    /** MDSE_CD*/
    private String             mdseCd;

    /** SO_SLP_NUM*/
    private String             soSlpNum;

    /** TRX_HDR_NUM*/
    private String             trxHdrNum;

    /** TRX_LINE_NUM*/
    private String             trxLineNum;

    /** TRX_LINE_SUB_NUM*/
    private String             trxLineSubNum;

    /** SER_NUM*/
    private String             serNum;

    /**
     * Constructor
     */
    public SoSerialInfo() {

    }

    /**
     * @return trxSrcTpCd
     */
    public String getTrxSrcTpCd() {
        return trxSrcTpCd;
    }

    /**
     * @param trxSrcTpCd set trxSrcTpCd
     */
    public void setTrxSrcTpCd(String trxSrcTpCd) {
        this.trxSrcTpCd = trxSrcTpCd;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd set mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return soSlpNum
     */
    public String getSoSlpNum() {
        return soSlpNum;
    }

    /**
     * @param soSlpNum set soSlpNum
     */
    public void setSoSlpNum(String soSlpNum) {
        this.soSlpNum = soSlpNum;
    }

    /**
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * @param trxHdrNum set trxHdrNum
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
     * @param trxLineNum set trxLineNum
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
     * @param trxLineSubNum set trxLineSubNum
     */
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * @param serNum set serNum
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

}
