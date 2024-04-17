/*
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLA.NLAB307001;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class RmaTrkStsInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** RTRN_TRK_NTFY_WRK_PK */
    private BigDecimal rtrnTrkNtfyWrkPk;

    /** RWS_NUM */
    private String rwsNum;

    /** RWS_DTL_LINE_NUM */
    private String rwsDtlLineNum;

    /** RTRN_TRK_STS_DESC_TXT */
    private String rtrnTrkStsDescTxt;

    /** ML_GRP_ID */
    private String mlGrpId;

    /** ML_TMPL_ID */
    private String mlTmplId;

    /** ORD_SLS_REP_USG_FLG */
    private String ordSlsRepUsgFlg;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** EML_ADDR */
    private String emlAddr;

    /**
     * Constructor
     */
    public RmaTrkStsInfo() {

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
     * @return rtrnTrkNtfyWrkPk
     */
    public BigDecimal getRtrnTrkNtfyWrkPk() {
        return rtrnTrkNtfyWrkPk;
    }

    /**
     * @param rtrnTrkNtfyWrkPk set rtrnTrkNtfyWrkPk
     */
    public void setRtrnTrkNtfyWrkPk(BigDecimal rtrnTrkNtfyWrkPk) {
        this.rtrnTrkNtfyWrkPk = rtrnTrkNtfyWrkPk;
    }

    /**
     * @return rwsNum
     */
    public String getRwsNum() {
        return rwsNum;
    }

    /**
     * @param rwsNum set rwsNum
     */
    public void setRwsNum(String rwsNum) {
        this.rwsNum = rwsNum;
    }

    /**
     * @return rwsDtlLineNum
     */
    public String getRwsDtlLineNum() {
        return rwsDtlLineNum;
    }

    /**
     * @param rwsDtlLineNum set rwsDtlLineNum
     */
    public void setRwsDtlLineNum(String rwsDtlLineNum) {
        this.rwsDtlLineNum = rwsDtlLineNum;
    }

    /**
     * @return rtrnTrkStsDescTxt
     */
    public String getRtrnTrkStsDescTxt() {
        return rtrnTrkStsDescTxt;
    }

    /**
     * @param rtrnTrkStsDescTxt set rtrnTrkStsDescTxt
     */
    public void setRtrnTrkStsDescTxt(String rtrnTrkStsDescTxt) {
        this.rtrnTrkStsDescTxt = rtrnTrkStsDescTxt;
    }

    /**
     * @return mlGrpId
     */
    public String getMlGrpId() {
        return mlGrpId;
    }

    /**
     * @param mlGrpId set mlGrpId
     */
    public void setMlGrpId(String mlGrpId) {
        this.mlGrpId = mlGrpId;
    }

    /**
     * @return mlTmplId
     */
    public String getMlTmplId() {
        return mlTmplId;
    }

    /**
     * @param mlTmplId set mlTmplId
     */
    public void setMlTmplId(String mlTmplId) {
        this.mlTmplId = mlTmplId;
    }

    /**
     * @return ordSlsRepUsgFlg
     */
    public String getOrdSlsRepUsgFlg() {
        return ordSlsRepUsgFlg;
    }

    /**
     * @param ordSlsRepUsgFlg set ordSlsRepUsgFlg
     */
    public void setOrdSlsRepUsgFlg(String ordSlsRepUsgFlg) {
        this.ordSlsRepUsgFlg = ordSlsRepUsgFlg;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum set cpoOrdNum
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @return emlAddr
     */
    public String getEmlAddr() {
        return emlAddr;
    }

    /**
     * @param emlAddr set emlAddr
     */
    public void setEmlAddr(String emlAddr) {
        this.emlAddr = emlAddr;
    }

}