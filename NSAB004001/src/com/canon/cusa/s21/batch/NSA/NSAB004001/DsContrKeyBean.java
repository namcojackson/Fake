package com.canon.cusa.s21.batch.NSA.NSAB004001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * DsContrKeyBean for Contract mode change / auto approval Batch.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/10/2015   Hitachi         T.Harada        Create          
 * </pre>
 */
public class DsContrKeyBean {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** DS_CONTR_PK */
    private BigDecimal dsContrPk;

    /** dsContrExistsFlg */
    private String dsContrExistFlg = ZYPConstant.FLG_OFF_N;

    /** dsContrDtlExistsFlg */
    private String dsContrDtlExistFlg = ZYPConstant.FLG_OFF_N;

    /** dsContrBllgMtrExistFlg */
    private String dsContrBllgMtrExistFlg = ZYPConstant.FLG_OFF_N;

    /** dsContrPrcEffExistFlg */
    private String dsContrPrcEffExistFlg = ZYPConstant.FLG_OFF_N;

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return dsContrPk
     */
    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    /**
     * @param dsContrPk BigDecimal
     */
    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    /**
     * @return dsContrExistFlg
     */
    public String getDsContrExistFlg() {
        return dsContrExistFlg;
    }

    /**
     * @param dsContrExistFlg String
     */
    public void setDsContrExistFlg(String dsContrExistFlg) {
        this.dsContrExistFlg = dsContrExistFlg;
    }

    /**
     * @return dsContrDtlExistFlg
     */
    public String getDsContrDtlExistFlg() {
        return dsContrDtlExistFlg;
    }

    /**
     * @param dsContrDtlExistFlg String
     */
    public void setDsContrDtlExistFlg(String dsContrDtlExistFlg) {
        this.dsContrDtlExistFlg = dsContrDtlExistFlg;
    }

    /**
     * @return dsContrBllgMtrExistFlg
     */
    public String getDsContrBllgMtrExistFlg() {
        return dsContrBllgMtrExistFlg;
    }

    /**
     * @param dsContrBllgMtrExistFlg String
     */
    public void setDsContrBllgMtrExistFlg(String dsContrBllgMtrExistFlg) {
        this.dsContrBllgMtrExistFlg = dsContrBllgMtrExistFlg;
    }

    /**
     * @return dsContrPrcEffExistFlg
     */
    public String getDsContrPrcEffExistFlg() {
        return dsContrPrcEffExistFlg;
    }

    /**
     * @param dsContrPrcEffExistFlg String
     */
    public void setDsContrPrcEffExistFlg(String dsContrPrcEffExistFlg) {
        this.dsContrPrcEffExistFlg = dsContrPrcEffExistFlg;
    }

}
