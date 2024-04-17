/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import java.math.BigDecimal;

/**
 * <pre>
 * Default Term Condition Information Bean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         T.Kanasaka      Create          QC#15136
 * </pre>
 */
public class DefSvcTermCondInfoBean {

    /** Service Term Condition Attribute PK */
    private BigDecimal svcTermCondAttrbPk;

    /** Service Term Attribute Map Value Code */
    private String svcTermAttrbMapValCd;

    /** Attribute Update Available Flag */
    private String attrbUpdAvalFlg;

    /**
     * Get Service Term Condition Attribute PK
     * @return Service Term Condition Attribute PK
     */
    public BigDecimal getSvcTermCondAttrbPk() {
        return svcTermCondAttrbPk;
    }

    /**
     * Set Service Term Condition Attribute PK
     * @param svcTermAttrbMapValCd Service Term Condition Attribute PK
     */
    public void setSvcTermCondAttrbPk(BigDecimal svcTermCondAttrbPk) {
        this.svcTermCondAttrbPk = svcTermCondAttrbPk;
    }

    /**
     * Get Service Term Attribute Map Value Code
     * @return Service Term Attribute Map Value Code
     */
    public String getSvcTermAttrbMapValCd() {
        return svcTermAttrbMapValCd;
    }

    /**
     * Set Service Term Attribute Map Value Code
     * @param svcTermAttrbMapValCd Service Term Attribute Map Value Code
     */
    public void setSvcTermAttrbMapValCd(String svcTermAttrbMapValCd) {
        this.svcTermAttrbMapValCd = svcTermAttrbMapValCd;
    }

    /**
     * Get Attribute Update Available Flag
     * @return Attribute Update Available Flag
     */
    public String getAttrbUpdAvalFlg() {
        return attrbUpdAvalFlg;
    }

    /**
     * Set Attribute Update Available Flag
     * @param attrbUpdAvalFlg Attribute Update Available Flag
     */
    public void setAttrbUpdAvalFlg(String attrbUpdAvalFlg) {
        this.attrbUpdAvalFlg = attrbUpdAvalFlg;
    }
}
