/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC190001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import business.parts.NWZC157003PMsg;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/02   Fujitsu         A.Kosai         Create          N/A
 * 2018/02/08   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * </pre>
 */
public class ParamBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String dsOrdCatgCd;

    private String dsOrdTpCd;

    private String dsOrdRsnCd;

    private String lineBizTpCd;

    private String billToCustCd;

    private String billToCustAcctCd;

    private String shipToCustCd;

    private String shipToCustAcctCd;

    private String shipToLocNum;

    private String shipToAddlLocNm;

    private String shipToLocNm;

    private String shipToFirstLineAddr;

    private String shipToScdLineAddr;

    private String shipToThirdLineAddr;

    private String shipToFrthLineAddr;

    private String shipToPostCd;

    private String shipToCtyAddr;

    private String shipToStCd;

    private String shipToProvNm;

    private String shipToCtryCd;

    private String shipToCntyNm;

    private String shipToFirstRefCmntTxt;

    private String shipToScdRefCmntTxt;

    private String slsRepOrSlsTeamTocCd;

    private String lineBizRoleTpCd;

    private String coaBrCd;

    private String coaExtnCd;

    private String prcCatgCd;

    private String ccyCd;

    private String cpoOrdTpCd;

    private String defPrcCatgCd;

    private String svcPgmMdseCd;

    private String ordLineCatgCd;

    private boolean isSuplIncl;

    private boolean isLaserPlusContr;

    private BigDecimal myCsaCtacPsnPk;

    private BigDecimal ordCtacCtacPsnPk;

    private List<ParamDetailBean> detailBeanList;

    private List<NWZC157003PMsg> prcElemList;

    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    private String defShpgCmnt;
    // 2018/02/08 S21_NA#20297(Sol#379) Add End

    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    public String getDsOrdRsnCd() {
        return dsOrdRsnCd;
    }

    public void setDsOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    }

    public String getLineBizTpCd() {
        return lineBizTpCd;
    }

    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    public String getShipToLocNum() {
        return shipToLocNum;
    }

    public void setShipToLocNum(String shipToLocNum) {
        this.shipToLocNum = shipToLocNum;
    }

    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    public String getShipToLocNm() {
        return shipToLocNm;
    }

    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    public String getShipToPostCd() {
        return shipToPostCd;
    }

    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    public String getShipToStCd() {
        return shipToStCd;
    }

    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    public String getShipToProvNm() {
        return shipToProvNm;
    }

    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    }

    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    public String getShipToFirstRefCmntTxt() {
        return shipToFirstRefCmntTxt;
    }

    public void setShipToFirstRefCmntTxt(String shipToFirstRefCmntTxt) {
        this.shipToFirstRefCmntTxt = shipToFirstRefCmntTxt;
    }

    public String getShipToScdRefCmntTxt() {
        return shipToScdRefCmntTxt;
    }

    public void setShipToScdRefCmntTxt(String shipToScdRefCmntTxt) {
        this.shipToScdRefCmntTxt = shipToScdRefCmntTxt;
    }

    public String getSlsRepOrSlsTeamTocCd() {
        return slsRepOrSlsTeamTocCd;
    }

    public void setSlsRepOrSlsTeamTocCd(String slsRepOrSlsTeamTocCd) {
        this.slsRepOrSlsTeamTocCd = slsRepOrSlsTeamTocCd;
    }

    public String getLineBizRoleTpCd() {
        return lineBizRoleTpCd;
    }

    public void setLineBizRoleTpCd(String lineBizRoleTpCd) {
        this.lineBizRoleTpCd = lineBizRoleTpCd;
    }

    public String getCoaBrCd() {
        return coaBrCd;
    }

    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    public String getCoaExtnCd() {
        return coaExtnCd;
    }

    public void setCoaExtnCd(String coaExtnCd) {
        this.coaExtnCd = coaExtnCd;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public String getCcyCd() {
        return ccyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    public String getDefPrcCatgCd() {
        return defPrcCatgCd;
    }

    public void setDefPrcCatgCd(String defPrcCatgCd) {
        this.defPrcCatgCd = defPrcCatgCd;
    }

    public String getSvcPgmMdseCd() {
        return svcPgmMdseCd;
    }

    public void setSvcPgmMdseCd(String svcPgmMdseCd) {
        this.svcPgmMdseCd = svcPgmMdseCd;
    }

    public String getOrdLineCatgCd() {
        return ordLineCatgCd;
    }

    public void setOrdLineCatgCd(String ordLineCatgCd) {
        this.ordLineCatgCd = ordLineCatgCd;
    }

    public boolean isSuplIncl() {
        return isSuplIncl;
    }

    public void setSuplIncl(boolean isSuplIncl) {
        this.isSuplIncl = isSuplIncl;
    }

    public boolean isLaserPlusContr() {
        return isLaserPlusContr;
    }

    public void setLaserPlusContr(boolean isLaserPlusContr) {
        this.isLaserPlusContr = isLaserPlusContr;
    }

    public BigDecimal getMyCsaCtacPsnPk() {
        return myCsaCtacPsnPk;
    }

    public void setMyCsaCtacPsnPk(BigDecimal myCsaCtacPsnPk) {
        this.myCsaCtacPsnPk = myCsaCtacPsnPk;
    }

    public BigDecimal getOrdCtacCtacPsnPk() {
        return ordCtacCtacPsnPk;
    }

    public void setOrdCtacCtacPsnPk(BigDecimal ordCtacCtacPsnPk) {
        this.ordCtacCtacPsnPk = ordCtacCtacPsnPk;
    }

    public List<ParamDetailBean> getDetailBeanList() {
        return detailBeanList;
    }

    public void setDetailBeanList(List<ParamDetailBean> detailBeanList) {
        this.detailBeanList = detailBeanList;
    }

    public List<NWZC157003PMsg> getPrcElemList() {
        return prcElemList;
    }

    public void setPrcElemList(List<NWZC157003PMsg> prcElemList) {
        this.prcElemList = prcElemList;
    }

    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    public String getDefShpgCmnt() {
        return defShpgCmnt;
    }

    public void setDefShpgCmnt(String defShpgCmnt) {
        this.defShpgCmnt = defShpgCmnt;
    }
    // 2018/02/08 S21_NA#20297(Sol#379) Add End
}
