/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC184001;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   Fujitsu         Kamide          Create
 * 09/15/2016   Hitachi         Y.Takeno        Update          QC#13158
 * </pre>
 */
public class ParamBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String dsOrdCatgCd;

    private String dsOrdTpCd;

    private String lineBizTpCd;

    private String billToCustCd;

    private String billToCustAcctCd;

    private String soldToCustLocCd;

    private String sellToCustCd;

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

    private String coaBrCd; // 2016/09/15 QC#13158

    private String coaExtnCd;

    private String dsOrdLineCatdCd;

    private String prcCatgCd;

    private String ccyCd;

    private List<ParamDetailBean> detailBeanList;

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

    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }

    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
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

    public String getCoaExtnCd() {
        return coaExtnCd;
    }

    public void setCoaExtnCd(String coaExtnCd) {
        this.coaExtnCd = coaExtnCd;
    }

    public String getDsOrdLineCatdCd() {
        return dsOrdLineCatdCd;
    }

    public void setDsOrdLineCatdCd(String dsOrdLineCatdCd) {
        this.dsOrdLineCatdCd = dsOrdLineCatdCd;
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

    public List<ParamDetailBean> getDetailBeanList() {
        return detailBeanList;
    }

    public void setDetailBeanList(List<ParamDetailBean> detailBeanList) {
        this.detailBeanList = detailBeanList;
    }

 // 2016/09/15 QC#13158 Add Start
    public String getCoaBrCd() {
        return coaBrCd;
    }

    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }
// 2016/09/15 QC#13158 Add End

}
