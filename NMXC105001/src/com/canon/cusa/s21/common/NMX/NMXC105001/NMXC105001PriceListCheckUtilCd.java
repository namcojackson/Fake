/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC105001;

import java.math.BigDecimal;
import java.sql.ResultSet;
/**
 *<pre>
 * NMXC105001PriceListCheckUtilCd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Fujitsu         W.Honda         Create          N/A
 * 2017/04/06   Fujitsu         H.Kumagai       Update          N/A
 * 2018/09/04   Fujitsu         T.Noguchi       Update          QC#28019
 * 2018/11/26   Fujitsu         H.Kumagai       Update          QC#29300
 *</pre>
 */
public class NMXC105001PriceListCheckUtilCd {

    ResultSet rsSet = null;

    String prcQlfyTpCd = null;

    String prcCatgCd = null;

    String prcListTpCd = null;

    String pkgUomCd = null;

    String prcTermUomCd = null;

    BigDecimal prcFmlaPk = null;

    String prcRateTpCd = null;

    BigDecimal mdlId = null;

    BigDecimal prcMtrPkgPk = null;

    String mtrLbCd = null;

    BigDecimal prcMtrPkgBllgMtrPk = null;

    String prcSvcPlnTpCd = null;

    String prcSvcContrTpCd = null;

    String prcListBandCd = null;

    String prcSvcZoneCd = null;

    String prcSvcTierTpCd = null;

    String prcTierSvcOfferCd = null;

    BigDecimal splyAgmtPlnPk = null;

    String prcAddlChrgTpCd = null;

    String mktMdlSegCd = null;

    String prcPgmTpCd = null;

    String prcEquipTpCd = null;

    String sellToCustCd = null;

    String svcSegCd = null;

    String prcInOutRgCd = null;

    // Add Start 2018/07/17 QC#20267
    String mnfMdseCd = null;
    // Add End 2018/07/17 QC#20267

    // 2018/09/04 QC#28019 Add Start
    String pkgUomClsCd = null;

    String mdseCd = null;
    // 2018/09/04 QC#28019 Add End

    // Add Start 2018/11/26 QC#29300
    String PkgUomDescTxt = null;
    // Add End 2018/11/26 QC#29300

    public ResultSet getRsSet() {
        return rsSet;
    }

    public void setRsSet(ResultSet rsSet) {
        this.rsSet = rsSet;
    }

    public String getPrcQlfyTpCd() {
        return prcQlfyTpCd;
    }

    public void setPrcQlfyTpCd(String prcQlfyTpCd) {
        this.prcQlfyTpCd = prcQlfyTpCd;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public String getPrcListTpCd() {
        return prcListTpCd;
    }

    public void setPrcListTpCd(String prcListTpCd) {
        this.prcListTpCd = prcListTpCd;
    }

    public String getPkgUomCd() {
        return pkgUomCd;
    }

    public void setPkgUomCd(String pkgUomCd) {
        this.pkgUomCd = pkgUomCd;
    }

    public String getPrcTermUomCd() {
        return prcTermUomCd;
    }

    public void setPrcTermUomCd(String prcTermUomCd) {
        this.prcTermUomCd = prcTermUomCd;
    }

    public BigDecimal getPrcFmlaPk() {
        return prcFmlaPk;
    }

    public void setPrcFmlaPk(BigDecimal prcFmlaPk) {
        this.prcFmlaPk = prcFmlaPk;
    }

    public String getPrcRateTpCd() {
        return prcRateTpCd;
    }

    public void setPrcRateTpCd(String prcRateTpCd) {
        this.prcRateTpCd = prcRateTpCd;
    }

    public BigDecimal getMdlId() {
        return mdlId;
    }

    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    public BigDecimal getPrcMtrPkgPk() {
        return prcMtrPkgPk;
    }

    public void setPrcMtrPkgPk(BigDecimal prcMtrPkgPk) {
        this.prcMtrPkgPk = prcMtrPkgPk;
    }

    public String getMtrLbCd() {
        return mtrLbCd;
    }

    public void setMtrLbCd(String mtrLbCd) {
        this.mtrLbCd = mtrLbCd;
    }

    public BigDecimal getPrcMtrPkgBllgMtrPk() {
        return prcMtrPkgBllgMtrPk;
    }

    public void setPrcMtrPkgBllgMtrPk(BigDecimal prcMtrPkgBllgMtrPk) {
        this.prcMtrPkgBllgMtrPk = prcMtrPkgBllgMtrPk;
    }

    public String getPrcSvcPlnTpCd() {
        return prcSvcPlnTpCd;
    }

    public void setPrcSvcPlnTpCd(String prcSvcPlnTpCd) {
        this.prcSvcPlnTpCd = prcSvcPlnTpCd;
    }

    public String getPrcSvcContrTpCd() {
        return prcSvcContrTpCd;
    }

    public void setPrcSvcContrTpCd(String prcSvcContrTpCd) {
        this.prcSvcContrTpCd = prcSvcContrTpCd;
    }

    public String getPrcListBandCd() {
        return prcListBandCd;
    }

    public void setPrcListBandCd(String prcListBandCd) {
        this.prcListBandCd = prcListBandCd;
    }

    public String getPrcSvcZoneCd() {
        return prcSvcZoneCd;
    }

    public void setPrcSvcZoneCd(String prcSvcZoneCd) {
        this.prcSvcZoneCd = prcSvcZoneCd;
    }

    public String getPrcSvcTierTpCd() {
        return prcSvcTierTpCd;
    }

    public void setPrcSvcTierTpCd(String prcSvcTierTpCd) {
        this.prcSvcTierTpCd = prcSvcTierTpCd;
    }

    public String getPrcTierSvcOfferCd() {
        return prcTierSvcOfferCd;
    }

    public void setPrcTierSvcOfferCd(String prcTierSvcOfferCd) {
        this.prcTierSvcOfferCd = prcTierSvcOfferCd;
    }

    public BigDecimal getSplyAgmtPlnPk() {
        return splyAgmtPlnPk;
    }

    public void setSplyAgmtPlnPk(BigDecimal splyAgmtPlnPk) {
        this.splyAgmtPlnPk = splyAgmtPlnPk;
    }

    public String getPrcAddlChrgTpCd() {
        return prcAddlChrgTpCd;
    }

    public void setPrcAddlChrgTpCd(String prcAddlChrgTpCd) {
        this.prcAddlChrgTpCd = prcAddlChrgTpCd;
    }

    public String getMktMdlSegCd() {
        return mktMdlSegCd;
    }

    public void setMktMdlSegCd(String mktMdlSegCd) {
        this.mktMdlSegCd = mktMdlSegCd;
    }

    public String getPrcPgmTpCd() {
        return prcPgmTpCd;
    }

    public void setPrcPgmTpCd(String prcPgmTpCd) {
        this.prcPgmTpCd = prcPgmTpCd;
    }

    public String getPrcEquipTpCd() {
        return prcEquipTpCd;
    }

    public void setPrcEquipTpCd(String prcEquipTpCd) {
        this.prcEquipTpCd = prcEquipTpCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getSvcSegCd() {
        return svcSegCd;
    }

    public void setSvcSegCd(String svcSegCd) {
        this.svcSegCd = svcSegCd;
    }

    public String getPrcInOutRgCd() {
        return prcInOutRgCd;
    }

    public void setPrcInOutRgCd(String prcInOutRgCd) {
        this.prcInOutRgCd = prcInOutRgCd;
    }
    
    // Add Start 2018/07/17 QC#20267
    public String getMnfMdseCd() {
        return mnfMdseCd;
    }

    public void setMnfMdseCd(String mnfMdseCd) {
        this.mnfMdseCd = mnfMdseCd;
    }
    // Add End 2018/07/17 QC#20267

    // 2018/09/04 QC#28019 Add Start
    public String getPkgUomClsCd() {
        return pkgUomClsCd;
    }

    public void setPkgUomClsCd(String pkgUomClsCd) {
        this.pkgUomClsCd = pkgUomClsCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }
    // 2018/09/04 QC#28019 Add End

    // Add Start 2018/11/26 QC#29300
    public String getPkgUomDescTxt() {
        return PkgUomDescTxt;
    }

    public void setPkgUomDescTxt(String PkgUomDescTxt) {
        this.PkgUomDescTxt = PkgUomDescTxt;
    }
    // Add End 2018/11/26 QC#29300

    public void allClear() {
        rsSet = null;

        prcQlfyTpCd = null;

        prcCatgCd = null;

        prcListTpCd = null;

        pkgUomCd = null;

        prcTermUomCd = null;

        prcFmlaPk = null;

        prcRateTpCd = null;

        mdlId = null;

        prcMtrPkgPk = null;

        mtrLbCd = null;

        prcMtrPkgBllgMtrPk = null;

        prcSvcPlnTpCd = null;

        prcSvcContrTpCd = null;

        prcListBandCd = null;

        prcSvcZoneCd = null;

        prcSvcTierTpCd = null;

        prcTierSvcOfferCd = null;

        splyAgmtPlnPk = null;

        prcAddlChrgTpCd = null;

        mktMdlSegCd = null;

        prcPgmTpCd = null;

        prcEquipTpCd = null;

        sellToCustCd = null;

        svcSegCd = null;

        prcInOutRgCd = null;

        // Add Start 2018/07/17 QC#20267
        mnfMdseCd = null;
        // Add End 2018/07/17 QC#20267

        // 2018/09/04 QC#28019 Add Start
        pkgUomClsCd = null;

        mdseCd = null;
        // 2018/09/04 QC#28019 Add End

        // Add Start 2018/11/26 QC#29300
        PkgUomDescTxt = null;
        // Add End 2018/11/26 QC#29300
    }
}
