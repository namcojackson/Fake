/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC004001;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.db.DS_CONTR_DTLTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Hitachi         K.Kishimoto     Create          N/A
 * 2016/02/03   Hitachi         Y.Takeno        Update          QC#871
 * 2016/04/07   Hitachi         A.Kohinata      Update          QC#6566
 * 2016/09/27   Hitachi         T.Kanasaka      Update          QC#9905
 * 2018/07/04   Hitachi         K.Kojima        Update          QC#23685-1
 * 2018/07/09   Hitachi         K.Kojima        Update          QC#23685-1
 *</pre>
 */
public class GetDefCoaTrxCdInfoBean {

    /**
     * Global Company Code
     */
    private String glblCmpyCd;

    /**
     * Service Machine Master PK
     */
    private BigDecimal svcMachMstrPk;

    // START 2016/09/27 T.Kanasaka [QC#9905, ADD]
    /**
     * Direct Sales Contract PK
     */
    private BigDecimal dsContrPk;
    // END 2016/09/27 T.Kanasaka [QC#9905, ADD]

    /**
     * Direct Sales Contract Detail PK
     */
    private BigDecimal dsContrDtlPk;

    /**
     * Direct Sales Contract Billing Meter PK
     */
    private BigDecimal dsContrBllgMtrPk;

    /**
     * Direct Sales Contract Additional Charge PK
     */
    private BigDecimal dsContrAddlChrgPk;

    /**
     * Merchandise Code
     */
    private String mdseCd;

    /**
     * Direct Sales Account Number
     */
    private String dsAcctNum;

    /**
     * Base Charge Flag
     */
    private String baseChrgFlg;

    /**
     * Usage Charge Flag
     */
    private String usgChrgFlg;

    /**
     * Additional Charge Flag
     */
    private String addlChrgFlg;

    // START 2016/02/03 Y.Takeno [QC#871, ADD]

    /**
     * Mode Code
     */
    private String xxModeCd;

    /**
     * TOC Code
     */
    private String tocCd;

    /**
     * Intangible Merchandise Code
     */
    private String intgMdseCd;

    /**
     * Service Program Merchandise Code
     */
    private String svcPgmMdseCd;

    // END   2016/02/03 Y.Takeno [QC#871, ADD]

    // START 2016/04/07 [QC#6566, ADD]
    /**
     * Fleet Line Flag
     */
    private String fleetLineFlg;

    /**
     * Service Line of Business Code
     */
    private String svcLineBizCd;
    // END 2016/04/07 [QC#6566, ADD]

    // START 2018/07/04 K.Kojima [QC#23685-1,ADD]
    /**
     * Contract Info
     */
    private Map<String, Object> contrInfo;

    /**
     * DS_CONTR_DTLTMsg
     */
    private DS_CONTR_DTLTMsg dsContrDtlTMsg;
    // END 2018/07/04 K.Kojima [QC#23685-1,ADD]

    // START 2018/07/09 K.Kojima [QC#23685-1,ADD]
    /**
     * Model ID
     */
    private BigDecimal mdlId;
    // END 2018/07/09 K.Kojima [QC#23685-1,ADD]

    /**
     * Out List Information Bean
     */
    private List<GetDefCoaTrxCdForOutListInfoBean> outListInfoBean;

    /**
     * Get Global Company Code
     * @return Global Company Code
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set Global Company Code
     * @param glblCmpyCd Global Company Code
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get Service Machine Master PK
     * @return Service Machine Master PK
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * Set Service Machine Master PK
     * @param svcMachMstrPk Service Machine Master PK
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * Get Direct Sales Contract PK
     * @return Direct Sales Contract PK
     */
    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    /**
     * Set Direct Sales Contrac PK
     * @param dsContrPk Direct Sales Contract PK
     */
    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    // START 2016/09/27 T.Kanasaka [QC#9905, ADD]
    /**
     * Get Direct Sales Contract Detail PK
     * @return Direct Sales Contract Detail PK
     */
    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    /**
     * Set Direct Sales Contract Detail PK
     * @param dsContrDtlPk Direct Sales Contract Detail PK
     */
    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }
    // END 2016/09/27 T.Kanasaka [QC#9905, ADD]

    /**
     * Get Direct Sales Contract Billing Meter PK
     * @return Direct Sales Contract Billing Meter PK
     */
    public BigDecimal getDsContrBllgMtrPk() {
        return dsContrBllgMtrPk;
    }

    /**
     * Set Direct Sales Contract Billing Meter PK
     * @param dsContrBllgMtrPk Direct Sales Contract Billing Meter PK
     */
    public void setDsContrBllgMtrPk(BigDecimal dsContrBllgMtrPk) {
        this.dsContrBllgMtrPk = dsContrBllgMtrPk;
    }

    /**
     * Get Direct Sales Contract Additional Charge PK
     * @return Direct Sales Contract Additional Charge PK
     */
    public BigDecimal getDsContrAddlChrgPk() {
        return dsContrAddlChrgPk;
    }

    /**
     * Set Direct Sales Contract Additional Charge PK
     * @param dsContrAddlChrgPk Direct Sales Contract Additional
     * Charge PK
     */
    public void setDsContrAddlChrgPk(BigDecimal dsContrAddlChrgPk) {
        this.dsContrAddlChrgPk = dsContrAddlChrgPk;
    }

    /**
     * Get Merchandise Code
     * @return Merchandise Code
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Set Merchandise Code
     * @param mdseCd Merchandise Code
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * Get Direct Sales Account Number
     * @return Direct Sales Account Number
     */
    public String getDsAcctNum() {
        return dsAcctNum;
    }

    /**
     * Set Direct Sales Account Number
     * @param dsAcctNum Direct Sales Account Number
     */
    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }

    /**
     * Get Base Charge Flag
     * @return Base Charge Flag
     */
    public String getBaseChrgFlg() {
        return baseChrgFlg;
    }

    /**
     * Set Base Charge Flag
     * @param baseChrgFlg Base Charge Flag
     */
    public void setBaseChrgFlg(String baseChrgFlg) {
        this.baseChrgFlg = baseChrgFlg;
    }

    /**
     * Get Usage Charge Flag
     * @return Usage Charge Flag
     */
    public String getUsgChrgFlg() {
        return usgChrgFlg;
    }

    /**
     * Set Usage Charge Flag
     * @param usgChrgFlg Usage Charge Flag
     */
    public void setUsgChrgFlg(String usgChrgFlg) {
        this.usgChrgFlg = usgChrgFlg;
    }

    /**
     * Get Additional Charge Flag
     * @return Additional Charge Flag
     */
    public String getAddlChrgFlg() {
        return addlChrgFlg;
    }

    /**
     * Set Additional Charge Flag
     * @param addlChrgFlg Additional Charge Flag
     */
    public void setAddlChrgFlg(String addlChrgFlg) {
        this.addlChrgFlg = addlChrgFlg;
    }

    // START 2016/02/03 Y.Takeno [QC#871, ADD]

    /**
     * Get Mode Code
     * @return Mode Code
     */
    public String getXxModeCd() {
        return xxModeCd;
    }

    /**
     * Set Mode Code
     * @param xxModeCd Mode Code
     */
    public void setXxModeCd(String xxModeCd) {
        this.xxModeCd = xxModeCd;
    }

    /**
     * Get TOC Code
     * @return TOC Code
     */
    public String getTocCd() {
        return tocCd;
    }

    /**
     * Set TOC Code 
     * @param tocCd TOC Code
     */
    public void setTocCd(String tocCd) {
        this.tocCd = tocCd;
    }

    /**
     * Get Intangible Merchandise Code
     * @return Intangible Merchandise Code
     */
    public String getIntgMdseCd() {
        return intgMdseCd;
    }

    /**
     * Set Intangible Merchandise Code
     * @param intgMdseCd Intangible Merchandise Code
     */
    public void setIntgMdseCd(String intgMdseCd) {
        this.intgMdseCd = intgMdseCd;
    }

    /**
     * Get Service Program Merchandise Code
     * @return Service Program Merchandise Code
     */
    public String getSvcPgmMdseCd() {
        return svcPgmMdseCd;
    }

    /**
     * Set Service Program Merchandise Code
     * @param svcPgmMdseCd Service Program Merchandise Code
     */
    public void setSvcPgmMdseCd(String svcPgmMdseCd) {
        this.svcPgmMdseCd = svcPgmMdseCd;
    }

    // END   2016/02/03 Y.Takeno [QC#871, ADD]

    // START 2016/04/07 [QC#6566, ADD]
    /**
     * Get Fleet Line Flag
     * @return Fleet Line Flag
     */
    public String getFleetLineFlg() {
        return fleetLineFlg;
    }

    /**
     * Set Fleet Line Flag
     * @param fleetLineFlg Fleet Line Flag
     */
    public void setFleetLineFlg(String fleetLineFlg) {
        this.fleetLineFlg = fleetLineFlg;
    }

    /**
     * Get Service Line of Business Code
     * @return Service Line of Business Code
     */
    public String getSvcLineBizCd() {
        return svcLineBizCd;
    }

    /**
     * Set Service Line of Business Code
     * @param svcLineBizCd Service Line of Business Code
     */
    public void setSvcLineBizCd(String svcLineBizCd) {
        this.svcLineBizCd = svcLineBizCd;
    }
    // END 2016/04/07 [QC#6566, ADD]

    // START 2018/07/04 K.Kojima [QC#23685-1,ADD]
    /**
     * Get Contract Info
     */
    public Map<String, Object> getContrInfo() {
        return this.contrInfo;
    }

    /**
     * Set Contract Info
     * @param contrInfo Contract Info
     */
    public void setContrInfo(Map<String, Object> contrInfo) {
        this.contrInfo = contrInfo;
    }

    /**
     * Get DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtlTMsg() {
        return this.dsContrDtlTMsg;
    }

    /**
     * Set DS_CONTR_DTLTMsg
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     */
    public void setDsContrDtlTMsg(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        this.dsContrDtlTMsg = dsContrDtlTMsg;
    }

    // END 2018/07/04 K.Kojima [QC#23685-1,ADD]

    // START 2018/07/09 K.Kojima [QC#23685-1,ADD]
    /**
     * Get Model ID
     */
    public BigDecimal getMdlId() {
        return this.mdlId;
    }

    /**
     * Set Model ID
     * @param mdlId Model ID
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }
    // END 2018/07/09 K.Kojima [QC#23685-1,ADD]

    /**
     * Get Out List Information Bean
     * @return Out List Information Bean
     */
    public List<GetDefCoaTrxCdForOutListInfoBean> getOutLisstInfoBean() {
        return outListInfoBean;
    }

    /**
     * Set Out List Information Bean
     * @param outListInfoBean Out List Information Bean
     */
    public void setOutListInfoBean(List<GetDefCoaTrxCdForOutListInfoBean> outListInfoBean) {
        this.outListInfoBean = outListInfoBean;
    }
}
