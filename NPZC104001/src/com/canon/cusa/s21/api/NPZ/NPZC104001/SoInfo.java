package com.canon.cusa.s21.api.NPZ.NPZC104001;

import java.io.Serializable;

/**
 *<pre>
 * DS SO API
 * SO Related Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2022   CITS            A.Cullano       Create          QC#59359
 *</pre>
 */
public class SoInfo implements Serializable {

    /** Serial Version UID */
//    private static final long serialVersionUID = 1L;

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

    /**
     * Constructor
     */
    public SoInfo() {

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

}
