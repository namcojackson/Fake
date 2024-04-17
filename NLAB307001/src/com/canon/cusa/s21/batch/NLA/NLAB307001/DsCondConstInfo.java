/*
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLA.NLAB307001;

import java.io.Serializable;

/**
 *<pre>
 * RMA Tracking Notification Batch
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class DsCondConstInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** DS_COND_CONST_CD */
    private String dsCondConstCd;

    /** DS_COND_CONST_VAL_TXT_01 */
    private String dsCondConstValTxt01;

    /** DS_COND_CONST_VAL_TXT_02 */
    private String dsCondConstValTxt02;

    /** SCHD_COORD_STS_CD */
    private String schdCoordStsCd;

    /** NTFY_ML_SEND_FLG */
    private String ntfyMlSendFlg;

    /**
     * Constructor
     */
    public DsCondConstInfo() {

    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd set glblCmpyCd
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return dsCondConstCd
     */
    public String getDsCondConstCd() {
        return dsCondConstCd;
    }

    /**
     * @param dsCondConstCd set dsCondConstCd
     */
    public void setDsCondConstCd(String dsCondConstCd) {
        this.dsCondConstCd = dsCondConstCd;
    }

    /**
     * @return dsCondConstValTxt01
     */
    public String getDsCondConstValTxt01() {
        return dsCondConstValTxt01;
    }

    /**
     * @param dsCondConstValTxt01 set dsCondConstValTxt01
     */
    public void setDsCondConstValTxt01(String dsCondConstValTxt01) {
        this.dsCondConstValTxt01 = dsCondConstValTxt01;
    }

    /**
     * @return dsCondConstValTxt02
     */
    public String getDsCondConstValTxt02() {
        return dsCondConstValTxt02;
    }

    /**
     * @param dsCondConstValTxt02 set dsCondConstValTxt02
     */
    public void setDsCondConstValTxt02(String dsCondConstValTxt02) {
        this.dsCondConstValTxt02 = dsCondConstValTxt02;
    }

    /**
     * @return schdCoordStsCd
     */
    public String getSchdCoordStsCd() {
        return schdCoordStsCd;
    }

    /**
     * @param schdCoordStsCd set schdCoordStsCd
     */
    public void setSchdCoordStsCd(String schdCoordStsCd) {
        this.schdCoordStsCd = schdCoordStsCd;
    }

    /**
     * @return ntfyMlSendFlg
     */
    public String getNtfyMlSendFlg() {
        return ntfyMlSendFlg;
    }

    /**
     * @param ntfyMlSendFlg set ntfyMlSendFlg
     */
    public void setNtfyMlSendFlg(String ntfyMlSendFlg) {
        this.ntfyMlSendFlg = ntfyMlSendFlg;
    }

}