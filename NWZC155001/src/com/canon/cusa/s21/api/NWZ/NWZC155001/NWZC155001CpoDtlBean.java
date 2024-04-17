package com.canon.cusa.s21.api.NWZ.NWZC155001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 * 2017/09/16   Fujitsu         S.Ohki          Update          S21_NA#21064
 * 2017/10/24   Fujitsu         S.Takami        Update          S21_NA#22035
 * 2017/12/21   Fujitsu         K.Ishizuka      Update          S21_NA#19804(Sol#349)
 * 2018/03/15   Fujitsu         K.Ishziuka      Update          S21_NA#24253
 *</pre>
 */
public class NWZC155001CpoDtlBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** DS_CPO_CONFIG_PK */
    private BigDecimal dsCpoConfigPk;

    /** CONFIG_CATG_CD */
    private String configCatgCd;

    /** DS_ORD_POSN_NUM */
    private String dsOrdPosnNum;

    /** DS_CPO_LINE_NUM */
    private String dsCpoLineNum;

    /** DS_CPO_LINE_SUB_NUM */
    private String dsCpoLineSubNum;

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum;

    /** ORD_QTY */
    private BigDecimal ordQty;

    /** CPO_DTL_FUNC_NET_AMT */
    private BigDecimal cpoDtlFuncNetAmt;

    /** COA_BR_CD */
    private String coaBrCd;

    /** HDD_RMV_CD */
    private String hddRmvCd;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrPk;

    /** SVC_CONFIG_MSTR_PK */
    private BigDecimal svcConfigMstrPk;

    /** SER_NUM */
    private String serNum;

    /** UOM_CD */
    private String uomCd;

    /** FUNC_NET_AMT */
    private BigDecimal funcNetAmt;

    /**** PRICING API ****/
    /** BILL_TO_CUST_CD */
    private String billToCustCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** DS_ACCT_NUM_SH */
    private String dsAcctNum_SH;

    /** DS_ACCT_NUM_BL */
    private String dsAcctNum_BL;

    /** CSMP_NUM */
    private String csmpNum;

    /** DLR_REF_NUM */
    private String dlrRefNum;

    /** PRC_CONTR_NUM */
    private String prcContrNum;

    /** CCY_CD */
    private String ccyCd;

    /** PRC_LIST_EQUIP_CONFIG_NUM */
    private BigDecimal prcListEquipConfigNum;

    /** DS_ORD_LINE_CATG_CD */
    private String dsOrdLineCatgCd;

    /** ORD_CUST_UOM_QTY */
    private BigDecimal uomQty;

    /** INV_QTY */
    private BigDecimal invQty;

    /** MDL_ID */
    private BigDecimal mdlId;

    /** MDL_NM */
    private String mdlNm;

    /** CTY_ADDR_SH */
    private String ctyAddr_SH;

    /** ST_CD_SH */
    private String stCd_SH;

    /** CTRY_CD_SH */
    private String ctryCd_SH;

    /** OPEN_FLG */
    private String openFlg;

    /** MDSE_CD */
    private String mdseCd;

    /** FIRST_BIZ_CTX_ATTRB_NUM */
    private BigDecimal firstBizCtxAttrbNum;

    /** FIRST_BLLG_ATTRB_NM */
    private String firstBllgAttrbNm;

    /** SCD_BLLG_ATTRB_NM */
    private String scdBllgAttrbNm;

    /** THIRD_BLLG_ATTRB_NM */
    private String thirdBllgAttrbNm;

    /** FRTH_BLLG_ATTRB_NM */
    private String frthBllgAttrbNm;

    /** FIFTH_BLLG_ATTRB_NM */
    private String fifthBllgAttrbNm;

    /** FIRST_BLLG_ATTRB_VAL_TXT */
    private String firstBllgAttrbValTxt;

    /** SCD_BLLG_ATTRB_VAL_TXT */
    private String scdBllgAttrbValTxt;

    /** THIRD_BLLG_ATTRB_VAL_TXT */
    private String thirdBllgAttrbValTxt;

    /** FRTH_BLLG_ATTRB_VAL_TXT */
    private String frthBllgAttrbValTxt;

    /** FIFTH_BLLG_ATTRB_VAL_TXT */
    private String fifthBllgAttrbValTxt;

    /** PRC_CATG_CD */
    private String prcCatgCd;

    /** FL_PRC_LIST_CD */
    private String flPrcListCd;

    /** ENT_FUNC_NET_UNIT_PRC_AMT */
    private BigDecimal entFuncNetUnitPrcAmt;

    /** ENT_DEAL_NET_UNIT_PRC_AMT */
    private BigDecimal entDealNetUnitPrcAmt;

    /** FUNC_PRC_LIST_PRC_AMT */
    private BigDecimal funcPrcListPrcAmt;

    /** CR_REBIL_CD */
    private String crRebilCd;

    // 2017/09/16 S21_NA#21064 Add Start
    /** RTRN_LINE_STS_CD */
    private String rtrnLineStsCd;
    // 2017/09/16 S21_NA#21064 Add End
    private String ordLineStsCd;    // QC#22874

    // 2017/10/24 S21_NA#22035 Add Start
    /** RTL_WH_CD */
    private String rtlWhCd;

    /** RTL_SWH_CD */
    private String rtlSWhCd;
    // 2017/10/24 S21_NA#22035 Add End
    
    /** slsRepTocCd */
    private String slsRepTocCd; // S21_NA#19804 ADD
    
    /** configTpCd */
    private String configTpCd; // 2018/03/15 S21_NA#24253 add
    
    /** @return glblCmpyCd */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** @return hddRmvCd */
    public String getHddRmvCd() {
        return hddRmvCd;
    }

    /** @return svcMachMstrPk */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /** @return svcConfigMstrPk */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /** @return serNum */
    public String getSerNum() {
        return serNum;
    }

    /** @return cpoOrdNum */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /** @return dsCpoConfigPk */
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    /** @return configCatgCd */
    public String getConfigCatgCd() {
        return configCatgCd;
    }

    /** @return dsOrdPosnNum */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /** @return dsCpoLineNum */
    public String getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    /** @return dsCpoLineSubNum */
    public String getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    /** @return cpoDtlLineNum */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /** @return cpoDtlLineSubNum */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /** @return ordQty */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /** @return cpoDtlFuncNetAmt */
    public BigDecimal getCpoDtlFuncNetAmt() {
        return cpoDtlFuncNetAmt;
    }

    /** @return billToCustCd */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /** @return ShipToCustCd */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /** @return DsAcctNum_SH */
    public String getDsAcctNum_SH() {
        return dsAcctNum_SH;
    }

    /** @return DsAcctNum_BL */
    public String getDsAcctNum_BL() {
        return dsAcctNum_BL;
    }

    /** @return CsmpNum */
    public String getCsmpNum() {
        return csmpNum;
    }

    /** @return DlrRefNum */
    public String getDlrRefNum() {
        return dlrRefNum;
    }

    /** @return PrcContrNum */
    public String getPrcContrNum() {
        return prcContrNum;
    }

    /** @return CoaBrCd */
    public String getCoaBrCd() {
        return coaBrCd;
    }

    /** @return CcyCd */
    public String getCcyCd() {
        return ccyCd;
    }

    /** @return PrcListEquipConfigNum */
    public BigDecimal getPrcListEquipConfigNum() {
        return prcListEquipConfigNum;
    }

    /** @return DsOrdLineCatgCd */
    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }

    /** @return UomQty */
    public BigDecimal getUomQty() {
        return uomQty;
    }

    /** @return InvQty */
    public BigDecimal getInvQty() {
        return invQty;
    }

    /** @return MdlId */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /** @return MdlNm */
    public String getMdlNm() {
        return mdlNm;
    }

    /** @return CtyAddr_SH */
    public String getCtyAddr_SH() {
        return ctyAddr_SH;
    }

    /** @return StCd_SH */
    public String getStCd_SH() {
        return stCd_SH;
    }

    /** @return CtryCd_SH */
    public String getCtryCd_SH() {
        return ctryCd_SH;
    }

    /** @return OpenFlg */
    public String getOpenFlg() {
        return openFlg;
    }

    /** @return mdseCd */
    public String getMdseCd() {
        return mdseCd;
    }

    /** @return funcNetAmt */
    public BigDecimal getFuncNetAmt() {
        return funcNetAmt;
    }

    /** @return uomCd */
    public String getUomCd() {
        return uomCd;
    }

    /** @return firstBizCtxAttrbNum */
    public BigDecimal getFirstBizCtxAttrbNum() {
        return firstBizCtxAttrbNum;
    }

    /** @return firstBllgAttrbNm */
    public String getFirstBllgAttrbNm() {
        return firstBllgAttrbNm;
    }

    /** @return scdBllgAttrbNm */
    public String getScdBllgAttrbNm() {
        return scdBllgAttrbNm;
    }

    /** @return thirdBllgAttrbNm */
    public String getThirdBllgAttrbNm() {
        return thirdBllgAttrbNm;
    }

    /** @return frthBllgAttrbNm */
    public String getFrthBllgAttrbNm() {
        return frthBllgAttrbNm;
    }

    /** @return fifthBllgAttrbNm */
    public String getFifthBllgAttrbNm() {
        return fifthBllgAttrbNm;
    }

    /** @return firstBllgAttrbValTxt */
    public String getFirstBllgAttrbValTxt() {
        return firstBllgAttrbValTxt;
    }

    /** @return scdBllgAttrbValTxt */
    public String getScdBllgAttrbValTxt() {
        return scdBllgAttrbValTxt;
    }

    /** @return thirdBllgAttrbValTxt */
    public String getThirdBllgAttrbValTxt() {
        return thirdBllgAttrbValTxt;
    }

    /** @return frthBllgAttrbValTxt */
    public String getFrthBllgAttrbValTxt() {
        return frthBllgAttrbValTxt;
    }

    /** @return fifthBllgAttrbValTxt */
    public String getFifthBllgAttrbValTxt() {
        return fifthBllgAttrbValTxt;
    }

    /** @return PrcCatgCd */
    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    /** @return flPrcListCd */
    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    /** @return entFuncNetUnitPrcAmt */
    public BigDecimal getEntFuncNetUnitPrcAmt() {
        return entFuncNetUnitPrcAmt;
    }

    /** @return entDealNetUnitPrcAmt */
    public BigDecimal getEntDealNetUnitPrcAmt() {
        return entDealNetUnitPrcAmt;
    }

    /** @return funcPrcListPrcAmt */
    public BigDecimal getFuncPrcListPrcAmt() {
        return funcPrcListPrcAmt;
    }

    /** @return crRebilCd */
    public String getCrRebilCd() {
        return crRebilCd;
    }

    // 2017/09/16 S21_NA#21064 Add Start
    /** @return rtrnLineStsCd */
    public String getRtrnLineStsCd() {
        return rtrnLineStsCd;
    }
    // 2017/09/16 S21_NA#21064 Add End
    
    // S21_NA#19804 ADD
    /** @return slsRepTocCd */
    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    };

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    };

    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }

    public void setConfigCatgCd(String configCatgCd) {
        this.configCatgCd = configCatgCd;
    }

    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    public void setDsCpoLineNum(String dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    }

    public void setDsCpoLineSubNum(String dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    };

    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    public void setCpoDtlFuncNetAmt(BigDecimal cpoDtlFuncNetAmt) {
        this.cpoDtlFuncNetAmt = cpoDtlFuncNetAmt;
    };

    public void setHddRmvCd(String hddRmvCd) {
        this.hddRmvCd = hddRmvCd;
    };

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    };

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    };

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public void setDsAcctNum_SH(String dsAcctNum_SH) {
        this.dsAcctNum_SH = dsAcctNum_SH;
    }

    public void setDsAcctNum_BL(String dsAcctNum_BL) {
        this.dsAcctNum_BL = dsAcctNum_BL;
    }

    public void setCsmpNum(String csmpNum) {
        this.csmpNum = csmpNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    }

    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public void setPrcListEquipConfigNum(BigDecimal prcListEquipConfigNum) {
        this.prcListEquipConfigNum = prcListEquipConfigNum;
    }

    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    }

    public void setUomQty(BigDecimal uomQty) {
        this.uomQty = uomQty;
    }

    public void setInvQty(BigDecimal invQty) {
        this.invQty = invQty;
    }

    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }

    public void setCtyAddr_SH(String ctyAddr_SH) {
        this.ctyAddr_SH = ctyAddr_SH;
    }

    public void setStCd_SH(String stCd_SH) {
        this.stCd_SH = stCd_SH;
    }

    public void setCtryCd_SH(String ctryCd_SH) {
        this.ctryCd_SH = ctryCd_SH;
    }

    public void setOpenFlg(String openFlg) {
        this.openFlg = openFlg;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public void setUomCd(String uomCd) {
        this.uomCd = uomCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public void setFirstBizCtxAttrbNum(BigDecimal firstBizCtxAttrbNum) {
        this.firstBizCtxAttrbNum = firstBizCtxAttrbNum;
    }

    public void setFirstBllgAttrbNm(String firstBllgAttrbNm) {
        this.firstBllgAttrbNm = firstBllgAttrbNm;
    }

    public void setScdBllgAttrbNm(String scdBllgAttrbNm) {
        this.scdBllgAttrbNm = scdBllgAttrbNm;
    }

    public void setThirdBllgAttrbNm(String thirdBllgAttrbNm) {
        this.thirdBllgAttrbNm = thirdBllgAttrbNm;
    }

    public void setFrthBllgAttrbNm(String frthBllgAttrbNm) {
        this.frthBllgAttrbNm = frthBllgAttrbNm;
    }

    public void setFifthBllgAttrbNm(String fifthBllgAttrbNm) {
        this.fifthBllgAttrbNm = fifthBllgAttrbNm;
    }

    public void setFirstBllgAttrbValTxt(String firstBllgAttrbValTxt) {
        this.firstBllgAttrbValTxt = firstBllgAttrbValTxt;
    }

    public void setScdBllgAttrbValTxt(String scdBllgAttrbValTxt) {
        this.scdBllgAttrbValTxt = scdBllgAttrbValTxt;
    }

    public void setThirdBllgAttrbValTxt(String thirdBllgAttrbValTxt) {
        this.thirdBllgAttrbValTxt = thirdBllgAttrbValTxt;
    }

    public void setFrthBllgAttrbValTxt(String frthBllgAttrbValTxt) {
        this.frthBllgAttrbValTxt = frthBllgAttrbValTxt;
    }

    public void setFifthBllgAttrbValTxt(String fifthBllgAttrbValTxt) {
        this.fifthBllgAttrbValTxt = fifthBllgAttrbValTxt;
    }

    public void setFuncNetAmt(BigDecimal funcNetAmt) {
        this.funcNetAmt = funcNetAmt;
    }

    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    }

    public void setEntFuncNetUnitPrcAmt(BigDecimal entFuncNetUnitPrcAmt) {
        this.entFuncNetUnitPrcAmt = entFuncNetUnitPrcAmt;
    }

    public void setEntDealNetUnitPrcAmt(BigDecimal entDealNetUnitPrcAmt) {
        this.entDealNetUnitPrcAmt = entDealNetUnitPrcAmt;
    }

    public void setFuncPrcListPrcAmt(BigDecimal funcPrcListPrcAmt) {
        this.funcPrcListPrcAmt = funcPrcListPrcAmt;
    }

    public void setCrRebilCd(String crRebilCd) {
        this.crRebilCd = crRebilCd;
    }

    // 2017/09/16 S21_NA#21064 Add Start
    public void setRtrnLineStsCd(String rtrnLineStsCd) {
        this.rtrnLineStsCd = rtrnLineStsCd;
    }
    // 2017/09/16 S21_NA#21064 Add End

    // 2017/10/24 S21_NA#22035 Add Start
    /** @return RtlWhCd */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * Set Retail Warehouse Code
     * @param rtlWhCd Retail Warehouse Code
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /** @return RtlSWhCd */
    public String getRtlSWhCd() {
        return rtlSWhCd;
    }

    /**
     * Set Retail Sub Warehouse Code
     * @param rtlSWhCd Retail Sub Warehouse Code
     */
    public void setRtlSWhCd(String rtlSWhCd) {
        this.rtlSWhCd = rtlSWhCd;
    }
    // 2017/10/24 S21_NA#22035 Add End

    /** @param ordLineStsCd Order Line Status Code */
    public void setOrdLineStsCd(String ordLineStsCd) {
        this.ordLineStsCd = ordLineStsCd;
    }

    /** @return ordLineStsCd */
    public String getOrdLineStsCd() {
        return ordLineStsCd;
    }
    
    // S21_NA#19804 ADD
    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    // 2018/03/15 S21_NA#24253 Add Start
    public void setConfigTpCd(String configTpCd) {
        this.configTpCd = configTpCd;
    }

    public String getConfigTpCd() {
        return configTpCd;
    }
    // 2018/03/15 S21_NA#24253 Add End
}
