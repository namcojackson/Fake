/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class EndOfLifeBean {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrPk;

    /** EOL_DT */
    private String eolDt;

    /** CONTR_AVAL_FLG */
    private String contrAvalFlg;

    /** SVC_AVAL_FLG */
    private String svcAvalFlg;

    /** EOL_SVC_CONTR_CMNT_TXT */
    private String eolSvcContrCmntTxt;

    /** EOL_TM_MAT_CMNT_TXT */
    private String eolTmMatCmntTxt;

    /** EOL_TECH_SPRT_CMNT_TXT */
    private String eolTechSprtCmntTxt;

    /** EOL_OTH_CMNT_TXT */
    private String eolOthCmntTxt;

    /** Massage Id */
    private String xxMsgId;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    public String getEolDt() {
        return eolDt;
    }

    public void setEolDt(String eolDt) {
        this.eolDt = eolDt;
    }

    public String getContrAvalFlg() {
        return contrAvalFlg;
    }

    public void setContrAvalFlg(String contrAvalFlg) {
        this.contrAvalFlg = contrAvalFlg;
    }

    public String getSvcAvalFlg() {
        return svcAvalFlg;
    }

    public void setSvcAvalFlg(String svcAvalFlg) {
        this.svcAvalFlg = svcAvalFlg;
    }

    public String getEolSvcContrCmntTxt() {
        return eolSvcContrCmntTxt;
    }

    public void setEolSvcContrCmntTxt(String eolSvcContrCmntTxt) {
        this.eolSvcContrCmntTxt = eolSvcContrCmntTxt;
    }

    public String getEolTmMatCmntTxt() {
        return eolTmMatCmntTxt;
    }

    public void setEolTmMatCmntTxt(String eolTmMatCmntTxt) {
        this.eolTmMatCmntTxt = eolTmMatCmntTxt;
    }

    public String getEolTechSprtCmntTxt() {
        return eolTechSprtCmntTxt;
    }

    public void setEolTechSprtCmntTxt(String eolTechSprtCmntTxt) {
        this.eolTechSprtCmntTxt = eolTechSprtCmntTxt;
    }

    public String getEolOthCmntTxt() {
        return eolOthCmntTxt;
    }

    public void setEolOthCmntTxt(String eolOthCmntTxt) {
        this.eolOthCmntTxt = eolOthCmntTxt;
    }

    public String getXxMsgId() {
        return xxMsgId;
    }

    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }
}
