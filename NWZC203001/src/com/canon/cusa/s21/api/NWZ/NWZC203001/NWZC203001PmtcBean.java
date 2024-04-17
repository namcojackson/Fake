/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC203001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.parts.NWZC203001PMsg;
import business.parts.NWZC203001_APMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 * <pre>
 *  Credit Card Validation API.
 * This class defines the valiable used in the api application
 * program of BusinessID NWZC203001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/06/2011   SRA             S.Yamamoto       Create          N/A 
 * 2020/05/28   Fujitsu         C.Hara           Update          QC#56866
 * 2020/09/28   CITS            K.Ogino          Update          QC#56866
 * </pre>
 */
public class NWZC203001PmtcBean {

    /** Process Mode */
    private String xxProcMd;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Data */
    private String slsDt;

    /** Paymentech Customer Reference Number */
    private String pmtcCustRefNum;

    /** Paymentech Authorize Reference Number */
    private String pmtcAuthRefNum;

    /** Sell To Customer */
    private String sellToCust;

    /** Paymentech Authorize Date */
    private String pmtcAuthDt;

    /** Paymentech Settle Date */
    private String pmtcStlDt;

    /** Paymentech Authorize Amount */
    private BigDecimal pmtcAuthAmt;

    /** Paymentech Authorize Tax Amount */
    private BigDecimal pmtcAuthTaxAmt;

    /** Paymentech Transaction Type Code */
    private String pmtcTrxTpCd;

    /** First Transaction Info Text */
    private String pmtcFirstTrxInfoTxt;

    /** Second Transaction Info Text */
    private String pmtcScdTrxInfoTxt;

    /** Third Transaction Info Text */
    private String pmtcThirdTrxInfoTxt;

    /** Fourth Transaction Info Text */
    private String pmtcFrthTrxInfoTxt;

    /** Fifth Transaction Info Text */
    private String pmtcFifthTrxInfoTxt;

    /** First Transaction Info Number */
    private BigDecimal pmtcFirstTrxInfoNum;

    /** Second Transaction Info Number */
    private BigDecimal pmtcScdTrxInfoNum;

    /** Third Transaction Info Number */
    private BigDecimal pmtcThirdTrxInfoNum;

    /** Fourth Transaction Info Number */
    private BigDecimal pmtcFrthTrxInfoNum;

    /** Fifth Transaction Info Number */
    private BigDecimal pmtcFifthTrxInfoNum;

    /** Original Card Transaction PK */
    private BigDecimal origCrCardTrxPk;

    /** Credit Card Cancel Date */
    private String crCardCancDt;

    /** Paymentech Account Number */
    private String pmtcAcctNum;

    /** Paymentech Expiration Date */
    private String pmtcExprDtTxt;

    /** Paymentech Address Verification Service Zip Code */
    private String pmtcAvsZipTxt;

    /** Paymentech Address Verification Service Address line 1 */
    private String pmtcAvsAddr01;

    /** Paymentech Address Verification Service Address line 2 */
    private String pmtcAvsAddr02;

    /** Paymentech Address Verification Service City */
    private String pmtcAvsCtyTxt;

    /** Paymentech Address Verification Service State */
    private String pmtcAvsStCd;

    /** Paymentech Address Verification Service Name */
    private String pmtcAvsNm;

    /** Paymentech Address Verification Service Country Code */
    private String pmtcAvsCtryCdTxt;

    /** Credit Card Type Code */
    private String crCardTpCd;

    /** Credi Card Create Date */
    private String crCardCratDt;

    /** Paymentech Tax Indicator */
    private String pmtcTaxIndNum;

    /** Paymentech Profile Order Override Indicator */
    private String pmtcPrflOrdOvrdCd;

    /** Paymentech Order ID */
    private String pmtcOrdId;

    /** Paymentech Comments */
    private String pmtcCmntTxt;

    /** Paymentech Transaction Reference Index Code */
    private String pmtcTrxRefIdxCd;

    /** Paymentech Authorization Status Code */
    private String pmtcAuthStsCd;

    /** Paymentech Process Status Message */
    private String pmtcProcStsMsgTxt;

    /** CR_CARD_TRX_PK */
    private BigDecimal crCardTrxPk;

    /** DS_CR_CARD_PK */
    private BigDecimal dsCrCardPk;

    /** Paymentech Process Status */
    private String pmtcProcStsCd;

    /** Paymentech Approval Status */
    private String pmtcApvlStsNum;

    /** Paymentech Version Code */
    private String pmtcVrsnCd;

    /** Paymentech Industry Type */
    private String pmtcIndyTp;

    /** Paymentech Bin Number */
    private String pmtcBinNum;

    /** Paymentech Merchant ID */
    private String pmtcMrcntId;

    /** Paymentech Terminal ID */
    private String pmtcTrmId;

    /** Paymentech Access Mode(Mode For Test) */
    private String pmtcAccesMode;

    // 2020/05/28 QC#56866 Add Start
    /** Paymentech Authorized Transaction Number */
    private String pmtcAuthTrxNum;

    /** Paymentech Authorized Postal Code */
    private String pmtcAuthPostCd;

    /** Paymentech Authorized Holder Name */
    private String pmtcAuthHldNm;

    /** Paymentech Authorized Address */
    private String pmtcAuthAddr;

    /** Paymentech Authorized City Address */
    private String pmtcAuthCtyAddr;

    /** Paymentech Authorized State Code */
    private String pmtcAuthStCd;

    /** Paymentech Authorized Freight Amount */
    private BigDecimal pmtcAuthFrtAmt;

    /** Paymentech Authorized Duty Amount */
    private BigDecimal pmtcAuthDtyAmt;

    /** Paymentech Authorized Country Code */
    private String pmtcAuthCtryCd;

    /** Paymentech Authorized From Zip Code*/
    private String pmtcAuthFromZipCd;

    /** Paymentech Authorized Discount Amount */
    private BigDecimal pmtcAuthDiscAmt;

    /** Paymentech Authorized Alt Tax Id */
    private String pmtcAuthAltTaxId;

    /** Paymentech Authorized Alt Tax Amount */
    private BigDecimal pmtcAuthAltTaxAmt;

    /** Paymentech Authorized Line Item Count */
    private BigDecimal pmtcAuthLineItemCnt;

    /** Paymentech Detail */
    private List<NWZC203001PmtcDtlBean> pmtcDtlList;
    // 2020/05/28 QC#56866 Add End

    /**
     * <pre>
     * Constructor, Mod QC#56866
     * </pre>
     * @param inMsg NWZC203001PMsg
     * @param constNum BigDecimal
     */
    public NWZC203001PmtcBean(NWZC203001PMsg inMsg, BigDecimal constNum) {
        this.setXxProcMd(inMsg.xxModeCd.getValue());
        this.setGlblCmpyCd(inMsg.glblCmpyCd.getValue());
        this.setSlsDt(inMsg.slsDt.getValue());
        this.setPmtcCustRefNum(inMsg.crCardCustRefNum.getValue());
        this.setPmtcAuthRefNum(inMsg.crCardAuthRefNum.getValue());
        this.setSellToCust(inMsg.sellToCustCd.getValue());
        this.setPmtcAuthDt(inMsg.crCardAuthDt.getValue());
        this.setPmtcStlDt(inMsg.crCardSetlDt.getValue());
        this.setPmtcAuthAmt(inMsg.crCardAuthAmt.getValue());
        this.setPmtcAuthTaxAmt(inMsg.crCardAuthTaxAmt.getValue());
        this.setPmtcTrxTpCd(inMsg.crCardTrxTpCd.getValue());
        this.setPmtcFirstTrxInfoTxt(inMsg.firstTrxInfoTxt.getValue());
        this.setPmtcScdTrxInfoTxt(inMsg.scdTrxInfoTxt.getValue());
        this.setPmtcThirdTrxInfoTxt(inMsg.thirdTrxInfoTxt.getValue());
        this.setPmtcFrthTrxInfoTxt(inMsg.frthTrxInfoTxt.getValue());
        this.setPmtcFifthTrxInfoTxt(inMsg.fifthTrxInfoTxt.getValue());
        this.setPmtcFirstTrxInfoNum(inMsg.firstTrxInfoNum.getValue());
        this.setPmtcScdTrxInfoNum(inMsg.scdTrxInfoNum.getValue());
        this.setPmtcThirdTrxInfoNum(inMsg.thirdTrxInfoNum.getValue());
        this.setPmtcFrthTrxInfoNum(inMsg.frthTrxInfoNum.getValue());
        this.setPmtcFifthTrxInfoNum(inMsg.fifthTrxInfoNum.getValue());
        this.setOrigCrCardTrxPk(inMsg.origCrCardTrxPk.getValue());
        this.setCrCardCancDt(inMsg.crCardCancDt.getValue());
        this.setPmtcAcctNum(inMsg.xxPmtcAcctNum.getValue());
        this.setPmtcExprDtTxt(inMsg.xxPmtcExprDtTxt.getValue());
        this.setPmtcAvsZipTxt(inMsg.xxPmtcAvsZipTxt.getValue());
        this.setPmtcAvsAddr01(inMsg.xxPmtcAvsAddr_01.getValue());
        this.setPmtcAvsAddr02(inMsg.xxPmtcAvsAddr_02.getValue());
        this.setPmtcAvsCtyTxt(inMsg.xxPmtcAvsCtyTxt.getValue());
        this.setPmtcAvsStCd(inMsg.xxPmtcAvsStCd.getValue());
        this.setPmtcAvsNm(inMsg.xxPmtcAvsNm.getValue());
        this.setPmtcAvsCtryCdTxt(inMsg.xxPmtcAvsCtryCdTxt.getValue());
        this.setCrCardTpCd(inMsg.crCardTpCd.getValue());
        this.setCrCardCratDt(inMsg.crCardCratDt.getValue());
        this.setPmtcTaxIndNum(inMsg.xxPmtcTaxIndNum.getValue());
        this.setPmtcPrflOrdOvrdCd(inMsg.xxPmtcPrflOrdOvrdCd.getValue());
        this.setPmtcOrdId(inMsg.xxPmtcOrdId.getValue());
        this.setPmtcCmntTxt(inMsg.xxPmtcCmntTxt.getValue());
        this.setPmtcTrxRefIdxCd(inMsg.xxPmtcTrxRefIdxCd.getValue());
        this.setPmtcAuthStsCd(inMsg.crCardAuthStsCd.getValue());
//        //this.setPmtcProcStsMsgTxt(inMsg.xxPmtcProcStsCd.getValue());
//        this.setPmtcProcStsMsgTxt(inMsg.xxPmtcProcStsMsgTxt.getValue());
        this.setCrCardTrxPk(inMsg.crCardTrxPk.getValue());
        this.setDsCrCardPk(inMsg.dsCrCardPk.getValue());
        this.setPmtcProcStsCd(inMsg.xxPmtcProcStsCd.getValue());
        this.setPmtcApvlStsNum(inMsg.xxPmtcApvlStsNum.getValue());
        // 2020/05/28 QC#56866 Add Start
        this.setPmtcAuthTrxNum(inMsg.crCardAuthTrxNum.getValue());
        this.setPmtcAuthPostCd(inMsg.crCardAuthPostCd.getValue());
        this.setPmtcAuthHldNm(inMsg.crCardAuthHldNm.getValue());
        this.setPmtcAuthAddr(inMsg.crCardAuthAddr.getValue());
        this.setPmtcAuthCtyAddr(inMsg.crCardAuthCtyAddr.getValue());
        this.setPmtcAuthStCd(inMsg.crCardAuthStCd.getValue());
        this.setPmtcAuthFrtAmt(inMsg.crCardAuthFrtAmt.getValue());
        this.setPmtcAuthDtyAmt(inMsg.crCardAuthDtyAmt.getValue());
        this.setPmtcAuthCtryCd(inMsg.crCardAuthCtryCd.getValue());
        this.setPmtcAuthFromZipCd(inMsg.crCardAuthFromZipCd.getValue());
        this.setPmtcAuthDiscAmt(inMsg.crCardAuthDiscAmt.getValue());
        this.setPmtcAuthAltTaxId(inMsg.crCardAuthAltTaxId.getValue());
        this.setPmtcAuthAltTaxAmt(inMsg.crCardAuthAltTaxAmt.getValue());
        this.setPmtcAuthLineItemCnt(inMsg.crCardAuthLineItemCnt.getValue());
        List<NWZC203001PmtcDtlBean> pmtcDtlList = new ArrayList<NWZC203001PmtcDtlBean>();
        for (int i = 0; i < inMsg.A.getValidCount(); i++) {
            // QC#56866 End
            NWZC203001_APMsg inDtlMsg = inMsg.A.no(i);
            NWZC203001PmtcDtlBean dtlBean = new NWZC203001PmtcDtlBean(inDtlMsg, inMsg.glblCmpyCd.getValue());
            pmtcDtlList.add(dtlBean);
            // QC#56866 Add
            if (ZYPCommonFunc.hasValue(constNum) && constNum.intValue() == i) {
                break;
            }
        }
        this.setPmtcDtlList(pmtcDtlList);
        // 2020/05/28 QC#56866 Add End
    }

    /** @param xxProcMd */
    public void setXxProcMd(String xxProcMd) {
        this.xxProcMd = xxProcMd;
    }

    /** @param glblCmpyCd */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /** @param slsDt */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /** @param pmtcCustRefNum */
    public void setPmtcCustRefNum(String pmtcCustRefNum) {
        this.pmtcCustRefNum = pmtcCustRefNum;
    }

    /** @param pmtcAuthRefNum */
    public void setPmtcAuthRefNum(String pmtcAuthRefNum) {
        this.pmtcAuthRefNum = pmtcAuthRefNum;
    }

    /** @param sellToCust */
    public void setSellToCust(String sellToCust) {
        this.sellToCust = sellToCust;
    }

    /** @param pmtcAuthDt */
    public void setPmtcAuthDt(String pmtcAuthDt) {
        this.pmtcAuthDt = pmtcAuthDt;
    }

    /** @param pmtcStlDt */
    public void setPmtcStlDt(String pmtcStlDt) {
        this.pmtcStlDt = pmtcStlDt;
    }

    /** @param pmtcAuthAmt */
    public void setPmtcAuthAmt(BigDecimal pmtcAuthAmt) {
        this.pmtcAuthAmt = pmtcAuthAmt;
    }

    /** @param pmtcAuthTaxAmt */
    public void setPmtcAuthTaxAmt(BigDecimal pmtcAuthTaxAmt) {
        this.pmtcAuthTaxAmt = pmtcAuthTaxAmt;
    }

    /** @param pmtcTrxTpCd */
    public void setPmtcTrxTpCd(String pmtcTrxTpCd) {
        this.pmtcTrxTpCd = pmtcTrxTpCd;
    }

    /** @param pmtcFirstTrxInfoTxt */
    public void setPmtcFirstTrxInfoTxt(String pmtcFirstTrxInfoTxt) {
        this.pmtcFirstTrxInfoTxt = pmtcFirstTrxInfoTxt;
    }

    /** @param pmtcScdTrxInfoTxt */
    public void setPmtcScdTrxInfoTxt(String pmtcScdTrxInfoTxt) {
        this.pmtcScdTrxInfoTxt = pmtcScdTrxInfoTxt;
    }

    /** @param pmtcThirdTrxInfoTxt */
    public void setPmtcThirdTrxInfoTxt(String pmtcThirdTrxInfoTxt) {
        this.pmtcThirdTrxInfoTxt = pmtcThirdTrxInfoTxt;
    }

    /** @param pmtcFrthTrxInfoTxt */
    public void setPmtcFrthTrxInfoTxt(String pmtcFrthTrxInfoTxt) {
        this.pmtcFrthTrxInfoTxt = pmtcFrthTrxInfoTxt;
    }

    /** @param pmtcFifthTrxInfoTxt */
    public void setPmtcFifthTrxInfoTxt(String pmtcFifthTrxInfoTxt) {
        this.pmtcFifthTrxInfoTxt = pmtcFifthTrxInfoTxt;
    }

    /** @param pmtcFirstTrxInfoNum */
    public void setPmtcFirstTrxInfoNum(BigDecimal pmtcFirstTrxInfoNum) {
        this.pmtcFirstTrxInfoNum = pmtcFirstTrxInfoNum;
    }

    /** @param pmtcScdTrxInfoNum */
    public void setPmtcScdTrxInfoNum(BigDecimal pmtcScdTrxInfoNum) {
        this.pmtcScdTrxInfoNum = pmtcScdTrxInfoNum;
    }

    /** @param pmtcThirdTrxInfoNum */
    public void setPmtcThirdTrxInfoNum(BigDecimal pmtcThirdTrxInfoNum) {
        this.pmtcThirdTrxInfoNum = pmtcThirdTrxInfoNum;
    }

    /** @param pmtcFrthTrxInfoNum */
    public void setPmtcFrthTrxInfoNum(BigDecimal pmtcFrthTrxInfoNum) {
        this.pmtcFrthTrxInfoNum = pmtcFrthTrxInfoNum;
    }

    /** @param pmtcFifthTrxInfoNum */
    public void setPmtcFifthTrxInfoNum(BigDecimal pmtcFifthTrxInfoNum) {
        this.pmtcFifthTrxInfoNum = pmtcFifthTrxInfoNum;
    }

    /** @param origCrCardTrxPk */
    public void setOrigCrCardTrxPk(BigDecimal origCrCardTrxPk) {
        this.origCrCardTrxPk = origCrCardTrxPk;
    }

    /** @param crCardCancDt */
    public void setCrCardCancDt(String crCardCancDt) {
        this.crCardCancDt = crCardCancDt;
    }

    /** @param pmtcAcctNum */
    public void setPmtcAcctNum(String pmtcAcctNum) {
        this.pmtcAcctNum = pmtcAcctNum;
    }

    /** @param pmtcExprDtTxt */
    public void setPmtcExprDtTxt(String pmtcExprDtTxt) {
        this.pmtcExprDtTxt = pmtcExprDtTxt;
    }

    /** @param pmtcAvsZipTxt */
    public void setPmtcAvsZipTxt(String pmtcAvsZipTxt) {
        this.pmtcAvsZipTxt = pmtcAvsZipTxt;
    }

    /** @param pmtcAvsAddr01 */
    public void setPmtcAvsAddr01(String pmtcAvsAddr01) {
        this.pmtcAvsAddr01 = pmtcAvsAddr01;
    }

    /** @param pmtcAvsAddr02 */
    public void setPmtcAvsAddr02(String pmtcAvsAddr02) {
        this.pmtcAvsAddr02 = pmtcAvsAddr02;
    }

    /** @param pmtcAvsCtyTxt */
    public void setPmtcAvsCtyTxt(String pmtcAvsCtyTxt) {
        this.pmtcAvsCtyTxt = pmtcAvsCtyTxt;
    }

    /** @param pmtcAvsStCd */
    public void setPmtcAvsStCd(String pmtcAvsStCd) {
        this.pmtcAvsStCd = pmtcAvsStCd;
    }

    /** @param pmtcAvsNm */
    public void setPmtcAvsNm(String pmtcAvsNm) {
        this.pmtcAvsNm = pmtcAvsNm;
    }

    /** @param pmtcAvsCtryCdTxt */
    public void setPmtcAvsCtryCdTxt(String pmtcAvsCtryCdTxt) {
        this.pmtcAvsCtryCdTxt = pmtcAvsCtryCdTxt;
    }

    /** @param crCardTpCd */
    public void setCrCardTpCd(String crCardTpCd) {
        this.crCardTpCd = crCardTpCd;
    }

    /** @param crCardCratDt */
    public void setCrCardCratDt(String crCardCratDt) {
        this.crCardCratDt = crCardCratDt;
    }

    /** @param pmtcTaxIndNum */
    public void setPmtcTaxIndNum(String pmtcTaxIndNum) {
        this.pmtcTaxIndNum = pmtcTaxIndNum;
    }

    /** @param pmtcPrflOrdOvrdCd */
    public void setPmtcPrflOrdOvrdCd(String pmtcPrflOrdOvrdCd) {
        this.pmtcPrflOrdOvrdCd = pmtcPrflOrdOvrdCd;
    }

    /** @param pmtcOrdId */
    public void setPmtcOrdId(String pmtcOrdId) {
        this.pmtcOrdId = pmtcOrdId;
    }

    /** @param pmtcCmntTxt */
    public void setPmtcCmntTxt(String pmtcCmntTxt) {
        this.pmtcCmntTxt = pmtcCmntTxt;
    }

    /** @param pmtcTrxRefIdxCd */
    public void setPmtcTrxRefIdxCd(String pmtcTrxRefIdxCd) {
        this.pmtcTrxRefIdxCd = pmtcTrxRefIdxCd;
    }

    /** @param pmtcAuthStsCd */
    public void setPmtcAuthStsCd(String pmtcAuthStsCd) {
        this.pmtcAuthStsCd = pmtcAuthStsCd;
    }

    /** @param pmtcProcStsMsgTxt */
    public void setPmtcProcStsMsgTxt(String pmtcProcStsMsgTxt) {
        this.pmtcProcStsMsgTxt = pmtcProcStsMsgTxt;
    }

    /** @param crCardTrxPk */
    public void setCrCardTrxPk(BigDecimal crCardTrxPk) {
        this.crCardTrxPk = crCardTrxPk;
    }

    /** @param dsCrCardPk */
    public void setDsCrCardPk(BigDecimal dsCrCardPk) {
        this.dsCrCardPk = dsCrCardPk;
    }

    /** @param pmtcProcStsCd */
    public void setPmtcProcStsCd(String pmtcProcStsCd) {
        this.pmtcProcStsCd = pmtcProcStsCd;
    }

    /** @param pmtcApvlStsNum */
    public void setPmtcApvlStsNum(String pmtcApvlStsNum) {
        this.pmtcApvlStsNum = pmtcApvlStsNum;
    }

    /** @param pmtcVrsnCd */
    public void setPmtcVrsnCd(String pmtcVrsnCd) {
        this.pmtcVrsnCd = pmtcVrsnCd;
    }

    /** @param pmtcIndyTp */
    public void setPmtcIndyTp(String pmtcIndyTp) {
        this.pmtcIndyTp = pmtcIndyTp;
    }

    /** @param pmtcBinNum */
    public void setPmtcBinNum(String pmtcBinNum) {
        this.pmtcBinNum = pmtcBinNum;
    }

    /** @param pmtcMrcntId */
    public void setPmtcMrcntId(String pmtcMrcntId) {
        this.pmtcMrcntId = pmtcMrcntId;
    }

    /** @param pmtcTrmId */
    public void setPmtcTrmId(String pmtcTrmId) {
        this.pmtcTrmId = pmtcTrmId;
    }

    /** @param pmtcAccesMode */
    public void setPmtcAccesMode(String pmtcAccesMode) {
        this.pmtcAccesMode = pmtcAccesMode;
    }

    // 2020/05/28 QC#56866 Add Start
    public void setPmtcAuthTrxNum(String pmtcAuthTrxNum) {
        this.pmtcAuthTrxNum = pmtcAuthTrxNum;
    }

    public void setPmtcAuthPostCd(String pmtcAuthPostCd) {
        this.pmtcAuthPostCd = pmtcAuthPostCd;
    }

    public void setPmtcAuthHldNm(String pmtcAuthHldNm) {
        this.pmtcAuthHldNm = pmtcAuthHldNm;
    }

    public void setPmtcAuthAddr(String pmtcAuthAddr) {
        this.pmtcAuthAddr = pmtcAuthAddr;
    }

    public void setPmtcAuthCtyAddr(String pmtcAuthCtyAddr) {
        this.pmtcAuthCtyAddr = pmtcAuthCtyAddr;
    }

    public void setPmtcAuthStCd(String pmtcAuthStCd) {
        this.pmtcAuthStCd = pmtcAuthStCd;
    }

    public void setPmtcAuthFrtAmt(BigDecimal pmtcAuthFrtAmt) {
        this.pmtcAuthFrtAmt = pmtcAuthFrtAmt;
    }

    public void setPmtcAuthDtyAmt(BigDecimal pmtcAuthDtyAmt) {
        this.pmtcAuthDtyAmt = pmtcAuthDtyAmt;
    }

    public void setPmtcAuthCtryCd(String pmtcAuthCtryCd) {
        this.pmtcAuthCtryCd = pmtcAuthCtryCd;
    }

    public void setPmtcAuthFromZipCd(String pmtcAuthFromZipCd) {
        this.pmtcAuthFromZipCd = pmtcAuthFromZipCd;
    }

    public void setPmtcAuthDiscAmt(BigDecimal pmtcAuthDiscAmt) {
        this.pmtcAuthDiscAmt = pmtcAuthDiscAmt;
    }

    public void setPmtcAuthAltTaxId(String pmtcAuthAltTaxId) {
        this.pmtcAuthAltTaxId = pmtcAuthAltTaxId;
    }

    public void setPmtcAuthAltTaxAmt(BigDecimal pmtcAuthAltTaxAmt) {
        this.pmtcAuthAltTaxAmt = pmtcAuthAltTaxAmt;
    }

    public void setPmtcAuthLineItemCnt(BigDecimal pmtcAuthLineItemCnt) {
        this.pmtcAuthLineItemCnt = pmtcAuthLineItemCnt;
    }

    public void setPmtcDtlList(List<NWZC203001PmtcDtlBean> pmtcDtlList) {
        this.pmtcDtlList = pmtcDtlList;
    }
    // 2020/05/28 QC#56866 Add End

    /** @return xxProcMd */
    public String getXxProcMd() {
        return xxProcMd;
    }

    /** @return glblCmpyCd */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** @return slsDt */
    public String getSlsDt() {
        return slsDt;
    }

    /** @return pmtcCustRefNum */
    public String getPmtcCustRefNum() {
        return pmtcCustRefNum;
    }

    /** @return PmtcAuthRefNum */
    public String getPmtcAuthRefNum() {
        return pmtcAuthRefNum;
    }

    /** @return SellToCust */
    public String getSellToCust() {
        return sellToCust;
    }

    /** @return PmtcAuthDt */
    public String getPmtcAuthDt() {
        return pmtcAuthDt;
    }

    /** @return PmtcStlDt */
    public String getPmtcStlDt() {
        return pmtcStlDt;
    }

    /** @return PmtcAuthAmt */
    public BigDecimal getPmtcAuthAmt() {
        return pmtcAuthAmt;
    }

    /** @return PmtcAuthTaxAmt */
    public BigDecimal getPmtcAuthTaxAmt() {
        return pmtcAuthTaxAmt;
    }

    /** @return PmtcTrxTpCd */
    public String getPmtcTrxTpCd() {
        return pmtcTrxTpCd;
    }

    /** @return PmtcFirstTrxInfoTxt */
    public String getPmtcFirstTrxInfoTxt() {
        return pmtcFirstTrxInfoTxt;
    }

    /** @return PmtcScdTrxInfoTxt */
    public String getPmtcScdTrxInfoTxt() {
        return pmtcScdTrxInfoTxt;
    }

    /** @return PmtcThirdTrxInfoTxt */
    public String getPmtcThirdTrxInfoTxt() {
        return pmtcThirdTrxInfoTxt;
    }

    /** @return PmtcFrthTrxInfoTxt */
    public String getPmtcFrthTrxInfoTxt() {
        return pmtcFrthTrxInfoTxt;
    }

    /** @return PmtcFifthTrxInfoTxt */
    public String getPmtcFifthTrxInfoTxt() {
        return pmtcFifthTrxInfoTxt;
    }

    /** @return PmtcFirstTrxInfoNum */
    public BigDecimal getPmtcFirstTrxInfoNum() {
        return pmtcFirstTrxInfoNum;
    }

    /** @return PmtcScdTrxInfoNum */
    public BigDecimal getPmtcScdTrxInfoNum() {
        return pmtcScdTrxInfoNum;
    }

    /** @return PmtcThirdTrxInfoNum */
    public BigDecimal getPmtcThirdTrxInfoNum() {
        return pmtcThirdTrxInfoNum;
    }

    /** @return PmtcFrthTrxInfoNum */
    public BigDecimal getPmtcFrthTrxInfoNum() {
        return pmtcFrthTrxInfoNum;
    }

    /** @return PmtcFifthTrxInfoNum */
    public BigDecimal getPmtcFifthTrxInfoNum() {
        return pmtcFifthTrxInfoNum;
    }

    /** @return OrigCrCardTrxPk */
    public BigDecimal getOrigCrCardTrxPk() {
        return origCrCardTrxPk;
    }

    /** @return CrCardCancDt */
    public String getCrCardCancDt() {
        return crCardCancDt;
    }

    /** @return PmtcAcctNum */
    public String getPmtcAcctNum() {
        return pmtcAcctNum;
    }

    /** @return PmtcExprDtTxt */
    public String getPmtcExprDtTxt() {
        return pmtcExprDtTxt;
    }

    /** @return PmtcAvsZipTxt */
    public String getPmtcAvsZipTxt() {
        return pmtcAvsZipTxt;
    }

    /** @return PmtcAvsAddr01 */
    public String getPmtcAvsAddr01() {
        return pmtcAvsAddr01;
    }

    /** @return PmtcAvsAddr02 */
    public String getPmtcAvsAddr02() {
        return pmtcAvsAddr02;
    }

    /** @return PmtcAvsCtyTxt */
    public String getPmtcAvsCtyTxt() {
        return pmtcAvsCtyTxt;
    }

    /** @return PmtcAvsStCd */
    public String getPmtcAvsStCd() {
        return pmtcAvsStCd;
    }

    /** @return PmtcAvsNm */
    public String getPmtcAvsNm() {
        return pmtcAvsNm;
    }

    /** @return PmtcAvsCtryCdTxt */
    public String getPmtcAvsCtryCdTxt() {
        return pmtcAvsCtryCdTxt;
    }

    /** @return CrCardTpCd */
    public String getCrCardTpCd() {
        return crCardTpCd;
    }

    /** @return CrCardCratDt */
    public String getCrCardCratDt() {
        return crCardCratDt;
    }

    /** @return PmtcTaxIndNum */
    public String getPmtcTaxIndNum() {
        return pmtcTaxIndNum;
    }

    /** @return PmtcPrflOrdOvrdCd */
    public String getPmtcPrflOrdOvrdCd() {
        return pmtcPrflOrdOvrdCd;
    }

    /** @return PmtcOrdId */
    public String getPmtcOrdId() {
        return pmtcOrdId;
    }

    /** @return PmtcCmntTxt */
    public String getPmtcCmntTxt() {
        return pmtcCmntTxt;
    }

    /** @return PmtcTrxRefIdxCd */
    public String getPmtcTrxRefIdxCd() {
        return pmtcTrxRefIdxCd;
    }

    /** @return PmtcAuthStsCd */
    public String getPmtcAuthStsCd() {
        return pmtcAuthStsCd;
    }

    /** @return PmtcProcStsMsgTxt */
    public String getPmtcProcStsMsgTxt() {
        return pmtcProcStsMsgTxt;
    }

    /** @return crCardTrxPk */
    public BigDecimal getCrCardTrxPk() {
        return crCardTrxPk;
    }

    /** @return DsCrCardPk */
    public BigDecimal getDsCrCardPk() {
        return dsCrCardPk;
    }

    /** @return PmtcProcStsCd */
    public String getPmtcProcStsCd() {
        return pmtcProcStsCd;
    }

    /** @return PmtcApvlStsNum */
    public String getPmtcApvlStsNum() {
        return pmtcApvlStsNum;
    }

    /** @return pmtcVrsnCd */
    public String getPmtcVrsnCd() {
        return pmtcVrsnCd;
    }

    /** @return pmtcIndyTp */
    public String getPmtcIndyTp() {
        return pmtcIndyTp;
    }

    /** @return pmtcBinNum */
    public String getPmtcBinNum() {
        return pmtcBinNum;
    }

    /** @return pmtcMrcntId */
    public String getPmtcMrcntId() {
        return pmtcMrcntId;
    }

    /** @return pmtcTrmId */
    public String getPmtcTrmId() {
        return pmtcTrmId;
    }

    /** @return pmtcAccesMode */
    public String getPmtcAccesMode() {
        return pmtcAccesMode;
    }

    // 2020/05/28 QC#56866 Add Start
    public String getPmtcAuthTrxNum() {
        return pmtcAuthTrxNum;
    }

    public String getPmtcAuthPostCd() {
        return pmtcAuthPostCd;
    }

    public String getPmtcAuthHldNm() {
        return pmtcAuthHldNm;
    }

    public String getPmtcAuthAddr() {
        return pmtcAuthAddr;
    }

    public String getPmtcAuthCtyAddr() {
        return pmtcAuthCtyAddr;
    }

    public String getPmtcAuthStCd() {
        return pmtcAuthStCd;
    }

    public BigDecimal getPmtcAuthFrtAmt() {
        return pmtcAuthFrtAmt;
    }

    public BigDecimal getPmtcAuthDtyAmt() {
        return pmtcAuthDtyAmt;
    }

    public String getPmtcAuthCtryCd() {
        return pmtcAuthCtryCd;
    }

    public String getPmtcAuthFromZipCd() {
        return pmtcAuthFromZipCd;
    }

    public BigDecimal getPmtcAuthDiscAmt() {
        return pmtcAuthDiscAmt;
    }

    public String getPmtcAuthAltTaxId() {
        return pmtcAuthAltTaxId;
    }

    public BigDecimal getPmtcAuthAltTaxAmt() {
        return pmtcAuthAltTaxAmt;
    }

    public BigDecimal getPmtcAuthLineItemCnt() {
        return pmtcAuthLineItemCnt;
    }

    public List<NWZC203001PmtcDtlBean> getPmtcDtlList() {
        return pmtcDtlList;
    }
    // 2020/05/28 QC#56866 Add Start
}
