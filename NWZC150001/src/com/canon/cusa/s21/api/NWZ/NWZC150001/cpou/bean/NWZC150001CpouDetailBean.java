/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.parts.NWZC150001_APMsg;

/**
 * <pre>
 *CPO Update API Detail Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         M.Yamada        Update          CSA
 * 2015/12/03   Fujitsu         S.Takami        Update          S21_NA#1407
 * 2015/12/10   Fujitsu         S.Takami        Update          S21_NA#1771
 * 2015/12/14   Fujitsu         S.Takami        Update          S21_NA#1845
 * 2015/12/22   Fujitsu         T.Yoshida       Update          S21_NA#2171
 * 2015/12/24   Fujitsu         S.Takami        Update          S21_NA#2009
 * 2016/04/28   Fujitsu         Y.Taoka         Update          S21_NA#7606
 * 2016/08/24   Fujitsu         H.Nagashima     Update          S21_NA#13747
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2016/12/14   Fujitsu         Y.Kanefusa      Update          S21_NA#16425
 * 2017/04/04   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2
 * 2017/10/16   Fujitsu         R.Nakamura      Update          S21_NA#21507
 * 2017/10/20   Fujitsu         T.Aoi           Update          S21_NA#21730
 * 2018/01/11   Fujitsu         A.Kosai         Update          S21_NA#22372
 * 2018/08/02   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/11/15   Fujitsu         S.Takami        Update          S21_NA#54199
 * 2021/02/04   CITS            K.Ogino         Update          QC#58230
 * </pre>
 */
public class NWZC150001CpouDetailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String glblCmpyCd;

    private String cpoOrdNum;

    private String cpoDtlLineNum;

    private String cpoDtlLineSubNum;

    private String cpoOrdTs;

    private String cpoOrdSubmtTs;

    private String cpoOrdTpCd;

    private String fifthProdCtrlCd;

    private String mdseCd;

    private String mdseNm;

    private String origMdseCd;

    private String origMdseNm;

    private String setMdseCd;

    private String dropShipFlg;

    private String shipToCustCd;

    private String shipToLocNm;

    private String shipToAddlLocNm;

    private String shipToFirstLineAddr;

    private String shipToScdLineAddr;

    private String shipToThirdLineAddr;

    private String shipToFrthLineAddr;

    private String shipToFirstRefCmntTxt;

    private String shipToScdRefCmntTxt;

    private String shipToCtyAddr;

    private String shipToStCd;

    private String shipToProvNm;

    private String shipToCntyNm;

    private String shipToPostCd;

    private String shipToCtryCd;

    private BigDecimal ordQty;

    private BigDecimal ordCustUomQty;

    private BigDecimal shipQty;

    private BigDecimal cancQty;

    private String invtyLocCd;

    private BigDecimal entDealNetUnitPrcAmt;

    private BigDecimal entCpoDtlDealNetAmt;

    private BigDecimal entCpoDtlDealSlsAmt;

    private BigDecimal entFuncNetUnitPrcAmt;

    private BigDecimal entCpoDtlFuncNetAmt;

    private BigDecimal entCpoDtlFuncSlsAmt;

    private BigDecimal cpoDtlDealNetAmt;

    private BigDecimal cpoDtlDealSlsAmt;

    private BigDecimal cpoDtlFuncNetAmt;

    private BigDecimal cpoDtlFuncSlsAmt;

    private String ccyCd;

    private BigDecimal exchRate;

    private String taxFlg;

    private String rddDt;

    private String rsdDt;

    private String expdShipDt;

    private String shipCpltCd;

    private String uomCpltFlg;

    private String ordLineStsCd;

    private String stkStsCd;

    private String pmtTermCashDiscCd;

    private String pmtTermCd;

    private String pmtMethCd;

    private String pmtCondCd;

    private String cashDiscTermCd;

    private String frtChrgToCd;

    private String frtChrgMethCd;

    private String slsRepOrSlsTeamTocCd;

    private String shpgSvcLvlCd;

    private String cancDelyLimitDt;

    private String cancShipLimitDt;

    private String cpoDtlCancDt;

    private String cpoDtlCancFlg;

    private String custMdseCd;

    private String custUomCd;

    private String ediStsCd;

    private String ediNum;

    private String ediSubNum;

    private String cpoDtlHldFlg;

    private String manPrcFlg;

    private String prcTmgCd;

    private String trialLoanRqstNum;

    private String trialLoanLineNum;

    private String trialLoanLineSubNum;

    private String crDrRsnSubCd;

    private String origCpoOrdNum;

    private String origInvNum;

    private String dslpInfoFlg;

    private BigDecimal dslpDealNetUnitPrcAmt;

    private String dslpPmtTermCashDiscCd;

    private String dslpPmtTermCd;

    private String dslpCashDiscTermCd;

    private String dslpFrtChrgToCd;

    private String dslpFrtChrgMethCd;

    private String dslpCcyCd;

    private String submtFlg;

    private String openFlg;

    private String carrCd;

    private String carrAcctNum;

    private String carrAcctShipNum;

    private String coaAcctCd;

    private String coaProjCd;

    private String invNum;

    private String exptFlg;

    private String setItemShipCpltFlg;

    private String distItemFlg;

    private String exptShpgMethCd;

    private String exptShpgTermCd;

    private String dtlRqstTpCd;

    private String mdseCdForMstrSrch;

    private String intgFlg;

    private String thirdPtyVndDropYFlg;

    private String reNumCpoDtlLineNum;

    private String reNumCpoDtlLineSubNum;

    private String shipCpltCdOld;

    private String firstProdCtrlCd;

    private String alocFlg;

    private String shpgPlnNum;

    private String preModifyFlg;

    private BigDecimal dealGrsUnitPrcAmt;

    private BigDecimal funcGrsUnitPrcAmt;

    private String defInvtyLocCd;

    private String defShpgSvcLvlCd;

    private String defFrtChrgToCd;

    private String defFrtChrgMethCd;

    private String coaBrCd;

    private String coaChCd;

    private String coaCcCd;

    private String coaProdCd;

    private String coaCmpyCd;

    private String coaExtnCd;

    private String coaAfflCd;

    private String prcCatgCd;

    private String shpgPlnDplyLineNum;

    private String defPmtTermCd;

    private String defCashDiscTermCd;

    private String manPmtFlg;

    private String custIstlFlg;

    // START MODIFY M.Fuji [OM028]
    //    private String machConfigNum;
    //
    //    private String bomLvlLineNum;
    private String dsOrdPosnNum;

    private BigDecimal svcConfigMstrPk;

    // END MODIFY M.Fuji [OM028]

    // START MODIFY M.Fuji [Defect#2394]
    private String assetMgtFlg;

    // END MODIFY M.Fuji [Defect#2394]

    private String lgcyIntfcDsCpoLineNum;

    private BigDecimal dsSiteSrvyPk;

    private String otsdStepNum;

    private String insdStepNum;

    private String stairCrawReqFlg;

    private String flgtStairNum;

    private String elevAvalFlg;

    private String elevAvalFromHourMn;

    private String elevAvalToHourMn;

    private String elevApptReqFlg;

    private String elevCtacTelNum;

    private String elevProtReqFlg;

    private BigDecimal elevWdt;

    private BigDecimal elevDepthNum;

    private String elevCtacPsnNm;

    private BigDecimal elevCapWt;

    private BigDecimal elevDoorHgt;

    private BigDecimal elevDoorWdt;

    private BigDecimal stairAndLdgWdt;

    private BigDecimal crdrWdt;

    private BigDecimal doorWdt;

    private String loadDockAvalFlg;

    private BigDecimal loadDockHgt;

    private String trctrAndTrailAccsFlg;

    private BigDecimal bldgEntDoorHgt;

    private BigDecimal bldgEntDoorWdt;

    private String loadDockAvalFromHourMn;

    private String loadDockAvalToHourMn;

    private String carrDelyTmHourMn;

    private String delyTrnspOptCd;

    private String siteSrvyAddlCmntTxt;

    // 20130107 M.Fuji Defect#62 Start
    private String cmpyInfoAptBldgNm;

    private String cmpyInfoCtacNm;

    private String cmpyInfoDeptNm;

    private String cmpyInfoFlNm;

    private String cmpyInfoTelNum;

    private String siteSrvyReqFlg;

    // 20130107 M.Fuji Defect#62 End

    // 20121126 M.Fuji WDS Solution#XXX Intangible Start
    private String mdseThirdPtyVndDropFlg;

    // 20121126 M.Fuji WDS Solution#XXX Intangible End

    private String configItmFlg;

    // 20121127 M.Fuji WDS Solution#104,015 Pricing Start

    // 20130410 M.Fuji Order Driven Start
    private String vndInvtyLocCd;

    // 20130410 M.Fuji Order Driven End

    /** unitNetWt */
    private BigDecimal unitNetWt;

    // START MODIFY S.Yamamoto [OM003]
    /** mdseFrtClsCd */
    private String mdseFrtClsCd;

    /** mdsePrcGrpCd */
    private String mdsePrcGrpCd;

    /** mdsePrcGrpGrsWt */
    private BigDecimal mdsePrcGrpGrsWt;

    /** frtCondCd */
    private String frtCondCd;

    // END   MODIFY S.Yamamoto [OM003]

    // START ADD M.Fuji [OM010]
    /** dsContrNum */
    private String dsContrNum;

    /** dsContrSqNum */
    private String dsContrSqNum;

    // END   ADD M.Fuji [OM010]

    /** xxTotBaseAmt */
    private BigDecimal xxTotBaseAmt;

    /** xxTotDiscAmt */
    private BigDecimal xxTotDiscAmt;

    /** xxTotSpclPrcAmt */
    private BigDecimal xxTotSpclPrcAmt;

    /** xxTotNetDiscAmt */
    private BigDecimal xxTotNetDiscAmt;

    /** xxTotNetPrcAmt */
    private BigDecimal xxTotNetPrcAmt;

    /** xxTotFrtAmt */
    private BigDecimal xxTotFrtAmt;

    /** xxTotSpclChrgAmt */
    private BigDecimal xxTotSpclChrgAmt;

    /** xxTotTaxAmt */
    private BigDecimal xxTotTaxAmt;

    /** xxSlsAmt */
    private BigDecimal xxSlsAmt;

    /** xxTotAmt */
    private BigDecimal xxTotAmt;

    /** bomHeaderFlg */
    private String bomHeaderFlg;

    // START ADD M.Fuji [OM028]
    /** baseCmptFlg */
    private String baseCmptFlg;

    // END ADD M.Fuji [OM028]

    /** entDealGrsUnitPrcAmt */
    private BigDecimal entDealGrsUnitPrcAmt;

    /** lineTotDealFrtAmt */
    private BigDecimal lineTotDealFrtAmt;

    /** lineTotFuncFrtAmt */
    private BigDecimal lineTotFuncFrtAmt;

    /** discPrcList */
    private List<NWZC150001CpouDiscountBean> discPrcList;

    // 20121127 M.Fuji WDS Solution#104,015 Pricing End

    // 2015/08/27 CSA Add Start
    private BigDecimal dsCpoLineNum;

    private BigDecimal dsCpoLineSubNum;

    private String dsOrdLineCatgCd;

    private String ordLineSrcCd;

    private String rtlWhCd;

    private String rtlSwhCd;

    private String serNum;

    private String flPrcListCd;

    private BigDecimal dealPrcListPrcAmt;

    private BigDecimal funcPrcListPrcAmt;

    private String refCpoDtlLineNum;

    private String refCpoDtlLineSubNum;

    private BigDecimal cpoDtlDealTaxAmt;

    private String dplyLineRefNum;

    private String crRebilCd;

    private String prcBaseDt;

    private String splyTpCd;

    private String splyNm;

    private String splyCtyAddr;

    private String splyStCd;

    private String splyPostCd;

    private String csmpContrNum;

    private String dlrRefNum;

    private String csmpPrcListCd;

    private String rntlTrmnDt;

    private String firstBllgAttrbNm;

    private String firstBllgAttrbValTxt;

    private String scdBllgAttrbNm;

    private String scdBllgAttrbValTxt;

    private String thirdBllgAttrbNm;

    private String thirdBllgAttrbValTxt;

    private String frthBllgAttrbNm;

    private String frthBllgAttrbValTxt;

    private String fifthBllgAttrbNm;

    private String fifthBllgAttrbValTxt;

    private String sixthBllgAttrbNm;

    private String sixthBllgAttrbValTxt;

    private String splyFirstAddr;

    private String bllgAttrbCustAcctCd;

    private String sbstMdseCd;

    private String ordSrcRefLineNum;

    private String ordSrcRefLineSubNum;

    private String carrSvcLvlCd;

    private String supdLockFlg;

    private BigDecimal prcListEquipConfigNum;

    private String trxCd;

    private String trxRsnCd;

    private BigDecimal loanBalQty;

    private BigDecimal funcSvcRevTrnsfAmt;

    private BigDecimal dealSvcRevTrnsfAmt;

    private BigDecimal funcSvcCostTrnsfAmt;

    private BigDecimal dealSvcCostTrnsfAmt;

    private BigDecimal funcManFlAdjAmt;

    private BigDecimal dealManFlAdjAmt;

    private BigDecimal funcManRepRevAdjAmt;

    private BigDecimal dealManRepRevAdjAmt;

    private String shopItemFlg;

    private String ordUpldRefNum;

    private String hardDriveRmvInclFlg;

    private String hardDriveEraseInclFlg;

    private String convCompFlg;

    private BigDecimal pickSvcConfigMstrPk;

    private BigDecimal svcMachMstrPk;

    // 2015/08/27 CSA Add End

    private BigDecimal dsCpoConfigPk; // 2015/12/03 S21_NA#1407 Add

    private String origCpoDtlLineNum; // S21_NA#7606

    private String origCpoDtlLineSubNum; // S21_NA#7606

    // 2016/09/05 S21_NA#6523 Add Start
    private String dsCpoDtlCratUsrId;
    private String dsCpoDtlCratTs;
    private String dsCpoDtlUpdUsrId;
    private String dsCpoDtlUpdTs;
    // 2016/09/05 S21_NA#6523 Add End

    private String convProcStsCd; // QC#16425 2016/12/14 Add

    // Add Start 2017/10/16 QC#21507
    private String cpoSrcTpCd;
    private String ordSrcRefNum;
    // Add End 2017/10/16 QC#21507

    private String origInvtyLocCd; // 2017/10/20 QC#21730 Add

    // 2018/01/11 S21_NA#22372 Add Start
    private BigDecimal funcUnitFlPrcAmt;
    // 2018/01/11 S21_NA#22372 Add End

    // 2018/08/02 S21_NA#25404 Add Start
    private String chngRsnTpCd;
    private String bizProcCmntTxt;
    // 2018/08/02 S21_NA#25404 Add End

    // 2019/10/04 S21_NA#51372 Add Start
    private String prntVndNm;
    // 2019/10/04 S21_NA#51372 Add End

    // 2019/11/15 S21_NA#54199 Add Start
    private String ordLineDplyStsCd = null;
    // 2019/11/15 S21_NA#54199 Add End

    // QC#58230
    private String prodCondCd = null;

    public NWZC150001CpouDetailBean(NWZC150001_APMsg pMsg) {

        this.setDtlRqstTpCd(pMsg.xxRqstTpCd_A1.getValue());
        this.setCpoDtlLineNum(pMsg.cpoDtlLineNum_A1.getValue());
        this.setCpoDtlLineSubNum(pMsg.cpoDtlLineSubNum_A1.getValue());
        this.setCpoOrdTpCd(pMsg.cpoOrdTpCd_A1.getValue());
        this.setMdseCd(pMsg.mdseCd_A1.getValue());
        this.setOrigMdseCd(pMsg.origMdseCd_A1.getValue());
        this.setDropShipFlg(pMsg.dropShipFlg_A1.getValue());
        this.setShipToCustCd(pMsg.shipToCustCd_A1.getValue());
        this.setShipToLocNm(pMsg.shipToLocNm_A1.getValue());
        this.setShipToAddlLocNm(pMsg.shipToAddlLocNm_A1.getValue());
        this.setShipToFirstLineAddr(pMsg.shipToFirstLineAddr_A1.getValue());
        this.setShipToScdLineAddr(pMsg.shipToScdLineAddr_A1.getValue());
        this.setShipToThirdLineAddr(pMsg.shipToThirdLineAddr_A1.getValue());
        this.setShipToFrthLineAddr(pMsg.shipToFrthLineAddr_A1.getValue());
        this.setShipToCtyAddr(pMsg.shipToCtyAddr_A1.getValue());
        this.setShipToStCd(pMsg.shipToStCd_A1.getValue());
        this.setShipToProvNm(pMsg.shipToProvNm_A1.getValue());
        this.setShipToPostCd(pMsg.shipToPostCd_A1.getValue());
        this.setShipToCtryCd(pMsg.shipToCtryCd_A1.getValue());
        this.setShipToCntyNm(pMsg.shipToCntyNm_A1.getValue());
        this.setShipToFirstRefCmntTxt(pMsg.shipToFirstRefCmntTxt_A1.getValue());
        this.setShipToScdRefCmntTxt(pMsg.shipToScdRefCmntTxt_A1.getValue());
        this.setOrdQty(pMsg.ordQty_A1.getValue());
        this.setOrdCustUomQty(pMsg.ordCustUomQty_A1.getValue());
        this.setInvtyLocCd(pMsg.invtyLocCd_A1.getValue());
        this.setEntDealNetUnitPrcAmt(pMsg.entDealNetUnitPrcAmt_A1.getValue());
        this.setCcyCd(pMsg.ccyCd_A1.getValue());
        this.setRddDt(pMsg.rddDt_A1.getValue());
        this.setRsdDt(pMsg.rsdDt_A1.getValue());
        this.setShipCpltCd(pMsg.shipCpltCd_A1.getValue());
        this.setUomCpltFlg(pMsg.uomCpltFlg_A1.getValue());
        this.setStkStsCd(pMsg.stkStsCd_A1.getValue());
        this.setPmtTermCashDiscCd(pMsg.pmtTermCashDiscCd_A1.getValue());
        // TODO this.setPmtTermCd(pMsg.pmtTermCd.getValue());
        this.setCashDiscTermCd(pMsg.cashDiscTermCd_A1.getValue());
        this.setFrtChrgToCd(pMsg.frtChrgToCd_A1.getValue());
        this.setFrtChrgMethCd(pMsg.frtChrgMethCd_A1.getValue());
        this.setShpgSvcLvlCd(pMsg.shpgSvcLvlCd_A1.getValue());
        this.setSlsRepOrSlsTeamTocCd(pMsg.slsRepOrSlsTeamTocCd_A1.getValue());
     // TODO this.setCpoDtlCancFlg(pMsg.cpoDtlCancFlg.getValue());
        this.setCustMdseCd(pMsg.custMdseCd_A1.getValue());
        this.setCustUomCd(pMsg.custUomCd_A1.getValue());
        this.setEdiStsCd(pMsg.ediStsCd_A1.getValue());
        this.setEdiNum(pMsg.ediNum_A1.getValue());
        this.setEdiSubNum(pMsg.ediSubNum_A1.getValue());
        this.setManPrcFlg(pMsg.manPrcFlg_A1.getValue());
        this.setCarrCd(pMsg.carrCd_A1.getValue());
        this.setCarrAcctNum(pMsg.carrAcctNum_A1.getValue());
     // TODO this.setCarrAcctShipNum(pMsg.carrAcctShipNum.getValue());
        this.setCoaAcctCd(pMsg.coaAcctCd_A1.getValue());
        this.setCoaProjCd(pMsg.coaProjCd_A1.getValue());
        this.setSetItemShipCpltFlg(pMsg.setItemShipCpltFlg_A1.getValue());
     // TODO this.setTaxFlg(pMsg.taxFlg.getValue());
//        this.setTrialLoanRqstNum(pMsg.trialLoanRqstNum.getValue());
//        this.setTrialLoanLineNum(pMsg.trialLoanLineNum.getValue());
//        this.setTrialLoanLineSubNum(pMsg.trialLoanLineSubNum.getValue());
     // TODO this.setExptShpgTermCd(pMsg.exptShpgTermCd.getValue());
     // TODO this.setExptShpgMethCd(pMsg.exptShpgMethCd.getValue());
//        this.setDslpInfoFlg(pMsg.dslpInfoFlg.getValue());
//        this.setDslpPmtTermCashDiscCd(pMsg.dslpPmtTermCashDiscCd.getValue());
//        this.setDslpPmtTermCd(pMsg.dslpPmtTermCd.getValue());
//        this.setDslpCashDiscTermCd(pMsg.dslpCashDiscTermCd.getValue());
//        this.setDslpFrtChrgToCd(pMsg.dslpFrtChrgToCd.getValue());
//        this.setDslpFrtChrgMethCd(pMsg.dslpFrtChrgMethCd.getValue());
//        this.setDslpCcyCd(pMsg.dslpCcyCd.getValue());
//        this.setDslpDealNetUnitPrcAmt(pMsg.dslpDealNetUnitPrcAmt.getValue());
     // TODO this.setPmtMethCd(pMsg.pmtMethCd.getValue());
     // TODO this.setPmtCondCd(pMsg.pmtCondCd.getValue());
        this.setCoaBrCd(pMsg.coaBrCd_A1.getValue());
        this.setCoaCcCd(pMsg.coaCcCd_A1.getValue());
        this.setCoaChCd(pMsg.coaChCd_A1.getValue());
        this.setCoaProdCd(pMsg.coaProdCd_A1.getValue());
     // TODO this.setShpgPlnDplyLineNum(pMsg.shpgPlnDplyLineNum.getValue());
        // START MODIFY M.Fuji [OM028]
        //this.setBomLvlLineNum(pMsg.bomLvlLineNum.getValue());
        this.setDsOrdPosnNum(pMsg.dsOrdPosnNum_A1.getValue());
        // START MODIFY M.Fuji [OM028]
     // TODO Start
        /*
        this.setLgcyIntfcDsCpoLineNum(pMsg.lgcyIntfcDsCpoLineNum.getValue());
        this.setDsSiteSrvyPk(pMsg.dsSiteSrvyPk.getValue());
        this.setOtsdStepNum(pMsg.otsdStepNum.getValue());
        this.setInsdStepNum(pMsg.insdStepNum.getValue());
        this.setStairCrawReqFlg(pMsg.stairCrawReqFlg.getValue());
        this.setFlgtStairNum(pMsg.flgtStairNum.getValue());
        this.setElevAvalFlg(pMsg.elevAvalFlg.getValue());
        this.setElevAvalFromHourMn(pMsg.elevAvalFromHourMn.getValue());
        this.setElevAvalToHourMn(pMsg.elevAvalToHourMn.getValue());
        this.setElevApptReqFlg(pMsg.elevApptReqFlg.getValue());
        this.setElevCtacTelNum(pMsg.elevCtacTelNum.getValue());
        this.setElevProtReqFlg(pMsg.elevProtReqFlg.getValue());
        this.setElevWdt(pMsg.elevWdt.getValue());
        this.setElevDepthNum(pMsg.elevDepthNum.getValue());
        this.setElevCtacPsnNm(pMsg.elevCtacPsnNm.getValue());
        this.setElevCapWt(pMsg.elevCapWt.getValue());
        this.setElevDoorHgt(pMsg.elevDoorHgt.getValue());
        this.setElevDoorWdt(pMsg.elevDoorWdt.getValue());
        this.setStairAndLdgWdt(pMsg.stairAndLdgWdt.getValue());
        this.setCrdrWdt(pMsg.crdrWdt.getValue());
        this.setDoorWdt(pMsg.doorWdt.getValue());
        this.setLoadDockAvalFlg(pMsg.loadDockAvalFlg.getValue());
        this.setLoadDockHgt(pMsg.loadDockHgt.getValue());
        this.setTrctrAndTrailAccsFlg(pMsg.trctrAndTrailAccsFlg.getValue());
        this.setBldgEntDoorHgt(pMsg.bldgEntDoorHgt.getValue());
        this.setBldgEntDoorWdt(pMsg.bldgEntDoorWdt.getValue());
        this.setLoadDockAvalFromHourMn(pMsg.loadDockAvalFromHourMn.getValue());
        this.setLoadDockAvalToHourMn(pMsg.loadDockAvalToHourMn.getValue());
        this.setCarrDelyTmHourMn(pMsg.carrDelyTmHourMn.getValue());
        this.setDelyTrnspOptCd(pMsg.delyTrnspOptCd.getValue());
        this.setSiteSrvyAddlCmntTxt(pMsg.siteSrvyAddlCmntTxt.getValue());
        // 20130107 M.Fuji Defect#62 Start
        this.setCmpyInfoAptBldgNm(pMsg.cmpyInfoAptBldgNm.getValue());
        this.setCmpyInfoCtacNm(pMsg.cmpyInfoCtacNm.getValue());
        this.setCmpyInfoDeptNm(pMsg.cmpyInfoDeptNm.getValue());
        this.setCmpyInfoFlNm(pMsg.cmpyInfoFlNm.getValue());
        this.setCmpyInfoTelNum(pMsg.cmpyInfoTelNum.getValue());
        this.setSiteSrvyReqFlg(pMsg.siteSrvyReqFlg.getValue());
        // 20130107 M.Fuji Defect#62 End
        this.setConfigItmFlg(pMsg.configItemFlg.getValue());
        */
        this.setCustIstlFlg(pMsg.custIstlFlg_A1.getValue());
        // START MODIFY M.Fuji [OM028]
        //        this.setMachConfigNum(pMsg.machConfigNum.getValue());
        this.setSvcConfigMstrPk(pMsg.svcConfigMstrPk_A1.getValue());
        // END MODIFY M.Fuji [OM028]
        // 20130410 M.Fuji Order Driven Start
        this.setVndInvtyLocCd(pMsg.vndInvtyLocCd_A1.getValue());
        // 20130410 M.Fuji Order Driven End

        // 20121127 M.Fuji WDS Solution#104,015 Pricing Start
        this.setUnitNetWt(pMsg.unitNetWt_A1.getValue());
        // START MODIFY S.Yamamoto [OM003]
     // TODO this.setMdseFrtClsCd(pMsg.mdseFrtClsCd.getValue());
     // TODO this.setMdsePrcGrpCd(pMsg.mdsePrcGrpCd.getValue());
     // TODO this.setMdsePrcGrpGrsWt(pMsg.mdsePrcGrpGrsWt.getValue());
        this.setFrtCondCd(pMsg.frtCondCd_A1.getValue());
        // END   MODIFY S.Yamamoto [OM003]
        // START ADD M.Fuji [OM010]
        this.setDsContrNum(pMsg.dsContrNum_A1.getValue());
        this.setDsContrSqNum(pMsg.dsContrSqNum_A1.getValue());
        // END   ADD M.Fuji [OM010]
        this.setXxTotBaseAmt(pMsg.xxTotBaseAmt_A1.getValue());
        this.setXxTotDiscAmt(pMsg.xxTotDiscAmt_A1.getValue());
        this.setXxTotSpclPrcAmt(pMsg.xxTotSpclPrcAmt_A1.getValue());
        this.setXxTotNetDiscAmt(pMsg.xxTotNetDiscAmt_A1.getValue());
        this.setXxTotNetPrcAmt(pMsg.xxTotNetPrcAmt_A1.getValue());
        this.setXxTotFrtAmt(pMsg.xxTotFrtAmt_A1.getValue());
        this.setXxTotSpclChrgAmt(pMsg.xxTotSpclChrgAmt_A1.getValue());
        this.setXxTotTaxAmt(pMsg.xxTotTaxAmt_A1.getValue());
        this.setXxSlsAmt(pMsg.xxSlsAmt_A1.getValue());
        this.setXxTotAmt(pMsg.xxTotAmt_A1.getValue());
        this.setDiscPrcList(new ArrayList<NWZC150001CpouDiscountBean>());
        // 20121127 M.Fuji WDS Solution#104,015 Pricing End
        // START ADD S.Yamamoto [MEX-LC004]
        this.setPrcCatgCd(pMsg.prcCatgCd_A1.getValue());
        // END   ADD S.Yamamoto [MEX-LC004]

        // 2015/08/27 CSA Add Start 
        this.setDsCpoLineNum(pMsg.dsCpoLineNum_A1.getValue());
        this.setDsCpoLineSubNum(pMsg.dsCpoLineSubNum_A1.getValue());
        this.setDsOrdLineCatgCd(pMsg.dsOrdLineCatgCd_A1.getValue());
        this.setOrdLineSrcCd(pMsg.ordLineSrcCd_A1.getValue());
        this.setRtlWhCd(pMsg.rtlWhCd_A1.getValue());
        this.setRtlSwhCd(pMsg.rtlSwhCd_A1.getValue());
        this.setSerNum(pMsg.serNum_A1.getValue());
        this.setFlPrcListCd(pMsg.flPrcListCd_A1.getValue());
        this.setDplyLineRefNum(pMsg.dplyLineRefNum_A1.getValue());
        this.setCrRebilCd(pMsg.crRebilCd_A1.getValue());
        this.setPrcBaseDt(pMsg.prcBaseDt_A1.getValue());
        this.setCsmpContrNum(pMsg.csmpContrNum_A1.getValue());
        this.setDlrRefNum(pMsg.dlrRefNum_A1.getValue());
        this.setCsmpPrcListCd(pMsg.csmpPrcListCd_A1.getValue());
        this.setBllgAttrbCustAcctCd(pMsg.bllgAttrbCustAcctCd_A1.getValue());
        this.setSbstMdseCd(pMsg.sbstMdseCd_A1.getValue());
        this.setOrdSrcRefLineNum(pMsg.ordSrcRefLineNum_A1.getValue());
        this.setOrdSrcRefLineSubNum(pMsg.ordSrcRefLineSubNum_A1.getValue());
        this.setCarrSvcLvlCd(pMsg.carrSvcLvlCd_A1.getValue());
        this.setSupdLockFlg(pMsg.supdLockFlg_A1.getValue());
        this.setPrcListEquipConfigNum(pMsg.prcListEquipConfigNum_A1.getValue());
        this.setTrxCd(pMsg.trxCd_A1.getValue());
        this.setTrxRsnCd(pMsg.trxRsnCd_A1.getValue());
        this.setLoanBalQty(pMsg.loanBalQty_A1.getValue());
        this.setFuncSvcRevTrnsfAmt(pMsg.funcSvcRevTrnsfAmt_A1.getValue());
        this.setDealSvcRevTrnsfAmt(pMsg.dealSvcRevTrnsfAmt_A1.getValue());
        this.setFuncSvcCostTrnsfAmt(pMsg.funcSvcCostTrnsfAmt_A1.getValue());
        this.setDealSvcCostTrnsfAmt(pMsg.dealSvcCostTrnsfAmt_A1.getValue());
        this.setFuncManFlAdjAmt(pMsg.funcManFlAdjAmt_A1.getValue());
        this.setDealManFlAdjAmt(pMsg.dealManFlAdjAmt_A1.getValue());
        this.setFuncManRepRevAdjAmt(pMsg.funcManRepRevAdjAmt_A1.getValue());
        this.setDealManRepRevAdjAmt(pMsg.dealManRepRevAdjAmt_A1.getValue());
        this.setShopItemFlg(pMsg.shopItemFlg_A1.getValue());
        this.setOrdUpldRefNum(pMsg.ordUpldRefNum_A1.getValue());

        this.setHardDriveRmvInclFlg(pMsg.hardDriveRmvInclFlg_A1.getValue());
        this.setHardDriveEraseInclFlg(pMsg.hardDriveEraseInclFlg_A1.getValue());
        this.setPickSvcConfigMstrPk(pMsg.pickSvcConfigMstrPk_A1.getValue());
        this.setSvcMachMstrPk(pMsg.svcMachMstrPk_A1.getValue());

        // 2015/08/27 CSA Add End
        this.setBaseCmptFlg(pMsg.baseCmptFlg_A1.getValue()); // 2015/12/03 S21_NA#1407 Add
        this.setDsCpoConfigPk(pMsg.dsCpoConfigPk_A1.getValue()); // 2015/12/03 S21_NA#1407 Add

        // 2015/12/10 CSA Add S21_NA#1771 Start
        this.setFirstBllgAttrbNm(pMsg.firstBllgAttrbNm_A1.getValue());
        this.setFirstBllgAttrbValTxt(pMsg.firstBllgAttrbValTxt_A1.getValue());
        this.setScdBllgAttrbNm(pMsg.scdBllgAttrbNm_A1.getValue());
        this.setScdBllgAttrbValTxt(pMsg.scdBllgAttrbValTxt_A1.getValue());
        this.setThirdBllgAttrbNm(pMsg.thirdBllgAttrbNm_A1.getValue());
        this.setThirdBllgAttrbValTxt(pMsg.thirdBllgAttrbValTxt_A1.getValue());
        this.setFrthBllgAttrbNm(pMsg.frthBllgAttrbNm_A1.getValue());
        this.setFrthBllgAttrbValTxt(pMsg.frthBllgAttrbValTxt_A1.getValue());
        this.setFifthBllgAttrbNm(pMsg.fifthBllgAttrbNm_A1.getValue());
        this.setFifthBllgAttrbValTxt(pMsg.fifthBllgAttrbValTxt_A1.getValue());
        this.setSixthBllgAttrbNm(pMsg.sixthBllgAttrbNm_A1.getValue());
        this.setSixthBllgAttrbValTxt(pMsg.sixthBllgAttrbValTxt_A1.getValue());
        // 2015/12/10 CSA Add S21_NA#1771 End
        // 2015/12/14 CSA Add S21_NA#1845 Start
        this.setDealPrcListPrcAmt(pMsg.dealPrcListPrcAmt_A1.getValue());
        this.setFuncPrcListPrcAmt(pMsg.funcPrcListPrcAmt_A1.getValue());
        // 2015/12/14 CSA Add S21_NA#1845 End
        // 2015/12/22 CSA Add S21_NA#2171 Start
        this.setSplyTpCd(pMsg.splyTpCd_A1.getValue());
        this.setSplyNm(pMsg.splyNm_A1.getValue());
        this.setSplyFirstAddr(pMsg.splyFirstAddr_A1.getValue());
        this.setSplyCtyAddr(pMsg.splyCtyAddr_A1.getValue());
        this.setSplyStCd(pMsg.splyStCd_A1.getValue());
        this.setSplyPostCd(pMsg.splyPostCd_A1.getValue());
        // 2015/12/22 CSA Add S21_NA#2171 End
        // 2015/12/24 CSA Add S21_NA#2009 Start
        this.setRefCpoDtlLineNum(pMsg.refCpoDtlLineNum_A1.getValue());
        this.setRefCpoDtlLineSubNum(pMsg.refCpoDtlLineSubNum_A1.getValue());
        // 2015/12/24 CSA Add S21_NA#2009 End

        this.setOrigCpoOrdNum(pMsg.origCpoOrdNum_A1.getValue()); // S21_NA#7606
        this.setOrigCpoDtlLineNum(pMsg.origCpoDtlLineNum_A1.getValue()); // S21_NA#7606
        this.setOrigCpoDtlLineSubNum(pMsg.origCpoDtlLineSubNum_A1.getValue()); // S21_NA#7606

        this.setOrigInvNum(pMsg.origInvNum_A1.getValue()); // S21_NA#CreditRebill Req
        // 2016/08/24 QC#13747 add Start
        this.setCoaCmpyCd(pMsg.coaCmpyCd_A1.getValue());
        this.setCoaAfflCd(pMsg.coaAfflCd_A1.getValue());
        this.setCoaExtnCd(pMsg.coaExtnCd_A1.getValue());
        // 2016/08/24 QC#13747 add End

    // TODO
        /* Update Columns!!!!
        // 2016/09/05 S21_NA#6523 Add Start
        this.setDsCpoDtlCratUsrId(pMsg.dsCpoDtlCratUsrId.getValue());
        this.setDsCpoDtlCratTs(pMsg.dsCpoDtlCratTs.getValue());
        this.setDsCpoDtlUpdUsrId(pMsg.dsCpoDtlUpdUsrId.getValue());
        this.setDsCpoDtlUpdTs(pMsg.dsCpoDtlUpdTs.getValue());
        // 2016/09/05 S21_NA#6523 Add End
         * 
         */
        // TODO this.setConvProcStsCd(pMsg.convProcStsCd.getValue()); // QC#16425 2016/12/14 Add
        // Add Start 2017/10/16 QC#21507
        this.setCpoSrcTpCd(pMsg.cpoSrcTpCd_A1.getValue());
        this.setOrdSrcRefNum(pMsg.ordSrcRefNum_A1.getValue());
        // Add End 2017/10/16 QC#21507

        this.setOrigInvtyLocCd(pMsg.origInvtyLocCd_A1.getValue()); // 2017/10/20 QC#21730 Add

        // 2018/01/11 S21_NA#22372 Add Start
        this.setFuncUnitFlPrcAmt(pMsg.funcUnitFlPrcAmt_A1.getValue());
        // 2018/01/11 S21_NA#22372 Add End

        // 2018/08/02 S21_NA#25404 Add Start
        this.setChngRsnTpCd(pMsg.chngRsnTpCd_A1.getValue());
        this.setBizProcCmntTxt(pMsg.bizProcCmntTxt_A1.getValue());
        // 2018/08/02 S21_NA#25404 Add End

        // 2019/10/04 S21_NA#51372 Add Start
        this.setPrntVndNm(pMsg.prntVndNm_A1.getValue());
        // 2019/10/04 S21_NA#51372 Add End

        // QC#58230
        this.setProdCondCd(pMsg.prodCondCd.getValue());
    }

    public String getAlocFlg() {
        return alocFlg;
    }

    public String getCancDelyLimitDt() {
        return cancDelyLimitDt;
    }

    public BigDecimal getCancQty() {
        return cancQty;
    }

    public String getCancShipLimitDt() {
        return cancShipLimitDt;
    }

    public String getCarrAcctNum() {
        return carrAcctNum;
    }

    public String getCarrAcctShipNum() {
        return carrAcctShipNum;
    }

    public String getCarrCd() {
        return carrCd;
    }

    public String getCashDiscTermCd() {
        return cashDiscTermCd;
    }

    public String getCcyCd() {
        return ccyCd;
    }

    public String getCoaAcctCd() {
        return coaAcctCd;
    }

    public String getCoaAfflCd() {
        return coaAfflCd;
    }

    public String getCoaBrCd() {
        return coaBrCd;
    }

    public String getCoaCcCd() {
        return coaCcCd;
    }

    public String getCoaChCd() {
        return coaChCd;
    }

    public String getCoaCmpyCd() {
        return coaCmpyCd;
    }

    public String getCoaExtnCd() {
        return coaExtnCd;
    }

    public String getCoaProdCd() {
        return coaProdCd;
    }

    public String getCoaProjCd() {
        return coaProjCd;
    }

    public String getCpoDtlCancDt() {
        return cpoDtlCancDt;
    }

    public String getCpoDtlCancFlg() {
        return cpoDtlCancFlg;
    }

    public BigDecimal getCpoDtlDealNetAmt() {
        return cpoDtlDealNetAmt;
    }

    public BigDecimal getCpoDtlDealSlsAmt() {
        return cpoDtlDealSlsAmt;
    }

    public BigDecimal getCpoDtlFuncNetAmt() {
        return cpoDtlFuncNetAmt;
    }

    public BigDecimal getCpoDtlFuncSlsAmt() {
        return cpoDtlFuncSlsAmt;
    }

    public String getCpoDtlHldFlg() {
        return cpoDtlHldFlg;
    }

    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public String getCpoOrdSubmtTs() {
        return cpoOrdSubmtTs;
    }

    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    public String getCrDrRsnSubCd() {
        return crDrRsnSubCd;
    }

    public String getCustMdseCd() {
        return custMdseCd;
    }

    public String getCustUomCd() {
        return custUomCd;
    }

    public BigDecimal getDealGrsUnitPrcAmt() {
        return dealGrsUnitPrcAmt;
    }

    public String getDefFrtChrgMethCd() {
        return defFrtChrgMethCd;
    }

    public String getDefFrtChrgToCd() {
        return defFrtChrgToCd;
    }

    public String getDefInvtyLocCd() {
        return defInvtyLocCd;
    }

    public String getDefShpgSvcLvlCd() {
        return defShpgSvcLvlCd;
    }

    public String getDistItemFlg() {
        return distItemFlg;
    }

    public String getDropShipFlg() {
        return dropShipFlg;
    }

    public String getDslpCashDiscTermCd() {
        return dslpCashDiscTermCd;
    }

    public String getDslpCcyCd() {
        return dslpCcyCd;
    }

    public BigDecimal getDslpDealNetUnitPrcAmt() {
        return dslpDealNetUnitPrcAmt;
    }

    public String getDslpFrtChrgMethCd() {
        return dslpFrtChrgMethCd;
    }

    public String getDslpFrtChrgToCd() {
        return dslpFrtChrgToCd;
    }

    public String getDslpInfoFlg() {
        return dslpInfoFlg;
    }

    public String getDslpPmtTermCashDiscCd() {
        return dslpPmtTermCashDiscCd;
    }

    public String getDslpPmtTermCd() {
        return dslpPmtTermCd;
    }

    public String getDtlRqstTpCd() {
        return dtlRqstTpCd;
    }

    public String getEdiNum() {
        return ediNum;
    }

    public String getEdiStsCd() {
        return ediStsCd;
    }

    public String getEdiSubNum() {
        return ediSubNum;
    }

    public BigDecimal getEntCpoDtlDealNetAmt() {
        return entCpoDtlDealNetAmt;
    }

    public BigDecimal getEntCpoDtlDealSlsAmt() {
        return entCpoDtlDealSlsAmt;
    }

    public BigDecimal getEntCpoDtlFuncNetAmt() {
        return entCpoDtlFuncNetAmt;
    }

    public BigDecimal getEntCpoDtlFuncSlsAmt() {
        return entCpoDtlFuncSlsAmt;
    }

    public BigDecimal getEntDealNetUnitPrcAmt() {
        return entDealNetUnitPrcAmt;
    }

    public BigDecimal getEntFuncNetUnitPrcAmt() {
        return entFuncNetUnitPrcAmt;
    }

    public BigDecimal getExchRate() {
        return exchRate;
    }

    public String getExpdShipDt() {
        return expdShipDt;
    }

    public String getExptFlg() {
        return exptFlg;
    }

    public String getExptShpgMethCd() {
        return exptShpgMethCd;
    }

    public String getExptShpgTermCd() {
        return exptShpgTermCd;
    }

    public String getFifthProdCtrlCd() {
        return fifthProdCtrlCd;
    }

    public String getFirstProdCtrlCd() {
        return firstProdCtrlCd;
    }

    public String getFrtChrgMethCd() {
        return frtChrgMethCd;
    }

    public String getFrtChrgToCd() {
        return frtChrgToCd;
    }

    public BigDecimal getFuncGrsUnitPrcAmt() {
        return funcGrsUnitPrcAmt;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public String getIntgFlg() {
        return intgFlg;
    }

    public String getInvNum() {
        return invNum;
    }

    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    public String getManPrcFlg() {
        return manPrcFlg;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public String getMdseCdForMstrSrch() {
        return mdseCdForMstrSrch;
    }

    public String getMdseNm() {
        return mdseNm;
    }

    public String getOpenFlg() {
        return openFlg;
    }

    public BigDecimal getOrdCustUomQty() {
        return ordCustUomQty;
    }

    public String getOrdLineStsCd() {
        return ordLineStsCd;
    }

    public BigDecimal getOrdQty() {
        return ordQty;
    }

    public String getOrigCpoOrdNum() {
        return origCpoOrdNum;
    }

    public String getOrigInvNum() {
        return origInvNum;
    }

    public String getOrigMdseCd() {
        return origMdseCd;
    }

    public String getOrigMdseNm() {
        return origMdseNm;
    }

    public String getPmtCondCd() {
        return pmtCondCd;
    }

    public String getPmtMethCd() {
        return pmtMethCd;
    }

    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    public String getPmtTermCd() {
        return pmtTermCd;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public String getPrcTmgCd() {
        return prcTmgCd;
    }

    public String getPreModifyFlg() {
        return preModifyFlg;
    }

    public String getRddDt() {
        return rddDt;
    }

    public String getReNumCpoDtlLineNum() {
        return reNumCpoDtlLineNum;
    }

    public String getReNumCpoDtlLineSubNum() {
        return reNumCpoDtlLineSubNum;
    }

    public String getRsdDt() {
        return rsdDt;
    }

    public String getSetItemShipCpltFlg() {
        return setItemShipCpltFlg;
    }

    public String getSetMdseCd() {
        return setMdseCd;
    }

    public String getShipCpltCd() {
        return shipCpltCd;
    }

    public String getShipCpltCdOld() {
        return shipCpltCdOld;
    }

    public BigDecimal getShipQty() {
        return shipQty;
    }

    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    public String getShipToFirstRefCmntTxt() {
        return shipToFirstRefCmntTxt;
    }

    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    public String getShipToLocNm() {
        return shipToLocNm;
    }

    public String getShipToPostCd() {
        return shipToPostCd;
    }

    public String getShipToProvNm() {
        return shipToProvNm;
    }

    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    public String getShipToScdRefCmntTxt() {
        return shipToScdRefCmntTxt;
    }

    public String getShipToStCd() {
        return shipToStCd;
    }

    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    public String getShpgPlnDplyLineNum() {
        return shpgPlnDplyLineNum;
    }

    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    public String getSlsRepOrSlsTeamTocCd() {
        return slsRepOrSlsTeamTocCd;
    }

    public String getStkStsCd() {
        return stkStsCd;
    }

    public String getSubmtFlg() {
        return submtFlg;
    }

    public String getTaxFlg() {
        return taxFlg;
    }

    public String getThirdPtyVndDropYFlg() {
        return thirdPtyVndDropYFlg;
    }

    public String getTrialLoanLineNum() {
        return trialLoanLineNum;
    }

    public String getTrialLoanLineSubNum() {
        return trialLoanLineSubNum;
    }

    public String getTrialLoanRqstNum() {
        return trialLoanRqstNum;
    }

    public String getUomCpltFlg() {
        return uomCpltFlg;
    }

    // START MODIFY M.Fuji [OM028]
    //    public String getBomLvlLineNum() {
    //        return bomLvlLineNum;
    //    }

    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    // END MODIFY M.Fuji [OM028]

    public String getLgcyIntfcDsCpoLineNum() {
        return lgcyIntfcDsCpoLineNum;
    }

    public BigDecimal getDsSiteSrvyPk() {
        return dsSiteSrvyPk;
    }

    public String getOtsdStepNum() {
        return otsdStepNum;
    }

    public String getInsdStepNum() {
        return insdStepNum;
    }

    public String getStairCrawReqFlg() {
        return stairCrawReqFlg;
    }

    public String getFlgtStairNum() {
        return flgtStairNum;
    }

    public String getElevAvalFlg() {
        return elevAvalFlg;
    }

    public String getElevAvalFromHourMn() {
        return elevAvalFromHourMn;
    }

    public String getElevAvalToHourMn() {
        return elevAvalToHourMn;
    }

    public String getElevApptReqFlg() {
        return elevApptReqFlg;
    }

    public String getElevCtacTelNum() {
        return elevCtacTelNum;
    }

    public String getElevProtReqFlg() {
        return elevProtReqFlg;
    }

    public BigDecimal getElevWdt() {
        return elevWdt;
    }

    public BigDecimal getElevDepthNum() {
        return elevDepthNum;
    }

    public String getElevCtacPsnNm() {
        return elevCtacPsnNm;
    }

    public BigDecimal getElevCapWt() {
        return elevCapWt;
    }

    public BigDecimal getElevDoorHgt() {
        return elevDoorHgt;
    }

    public BigDecimal getElevDoorWdt() {
        return elevDoorWdt;
    }

    public BigDecimal getStairAndLdgWdt() {
        return stairAndLdgWdt;
    }

    public BigDecimal getCrdrWdt() {
        return crdrWdt;
    }

    public BigDecimal getDoorWdt() {
        return doorWdt;
    }

    public String getLoadDockAvalFlg() {
        return loadDockAvalFlg;
    }

    public BigDecimal getLoadDockHgt() {
        return loadDockHgt;
    }

    public String getTrctrAndTrailAccsFlg() {
        return trctrAndTrailAccsFlg;
    }

    public BigDecimal getBldgEntDoorHgt() {
        return bldgEntDoorHgt;
    }

    public BigDecimal getBldgEntDoorWdt() {
        return bldgEntDoorWdt;
    }

    public String getLoadDockAvalFromHourMn() {
        return loadDockAvalFromHourMn;
    }

    public String getLoadDockAvalToHourMn() {
        return loadDockAvalToHourMn;
    }

    public String getCarrDelyTmHourMn() {
        return carrDelyTmHourMn;
    }

    public String getDelyTrnspOptCd() {
        return delyTrnspOptCd;
    }

    public String getSiteSrvyAddlCmntTxt() {
        return siteSrvyAddlCmntTxt;
    }

    // 20130107 M.Fuji Defect#62 Start
    public String getCmpyInfoAptBldgNm() {
        return cmpyInfoAptBldgNm;
    }

    public String getCmpyInfoCtacNm() {
        return cmpyInfoCtacNm;
    }

    public String getCmpyInfoDeptNm() {
        return cmpyInfoDeptNm;
    }

    public String getCmpyInfoFlNm() {
        return cmpyInfoFlNm;
    }

    public String getCmpyInfoTelNum() {
        return cmpyInfoTelNum;
    }

    public String getSiteSrvyReqFlg() {
        return siteSrvyReqFlg;
    }

    // 20130107 M.Fuji Defect#62 End

    public String getConfigItmFlg() {
        return configItmFlg;
    }

    public String getCustIstlFlg() {
        return custIstlFlg;
    }

    // START MODIFY M.Fuji [OM028]
    //    public String getMachConfigNum() {
    //        return machConfigNum;
    //    }

    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    // END MODIFY M.Fuji [OM028]

    // START MODIFY M.FUJI [Defect#2394]
    public String getAssetMgtFlg() {
        return assetMgtFlg;
    }

    // END MODIFY M.FUJI [Defect#2394]

    // 20121126 M.Fuji WDS Solution#XXX Intangible Start
    public String getMdseThirdPtyVndDropFlg() {
        return mdseThirdPtyVndDropFlg;
    }

    // 20121126 M.Fuji WDS Solution#XXX Intangible End

    // 20130410 M.Fuji Order Driven Start
    /** @return vndInvtyLocCd */
    public String getVndInvtyLocCd() {
        return vndInvtyLocCd;
    }

    // 20130410 M.Fuji Order Driven End

    // 20121127 M.Fuji WDS Solution#104,015 Pricing Start
    /** @return unitNetWt */
    public BigDecimal getUnitNetWt() {
        return unitNetWt;
    }

    // START MODIFY S.Yamamoto [OM003]
    /** @return mdseFrtClsCd */
    public String getMdseFrtClsCd() {
        return mdseFrtClsCd;
    }

    /** @return mdsePrcGrpCd */
    public String getMdsePrcGrpCd() {
        return mdsePrcGrpCd;
    }

    /** @return mdsePrcGrpGrsWt */
    public BigDecimal getMdsePrcGrpGrsWt() {
        return mdsePrcGrpGrsWt;
    }

    /** @return frtCondCd */
    public String getFrtCondCd() {
        return frtCondCd;
    }

    // END   MODIFY S.Yamamoto [OM003]

    // START ADD M.Fuji [OM010]
    /** @return dsContrNum */
    public String getDsContrNum() {
        return dsContrNum;
    }

    /** @return dsContrSqNum */
    public String getDsContrSqNum() {
        return dsContrSqNum;
    }

    // END ADD M.Fuji [OM010]

    /** @return xxTotBaseAmt */
    public BigDecimal getXxTotBaseAmt() {
        return xxTotBaseAmt;
    }

    /** @return xxTotDiscAmt */
    public BigDecimal getXxTotDiscAmt() {
        return xxTotDiscAmt;
    }

    /** @return xxTotSpclPrcAmt */
    public BigDecimal getXxTotSpclPrcAmt() {
        return xxTotSpclPrcAmt;
    }

    /** @return xxTotNetDiscAmt */
    public BigDecimal getXxTotNetDiscAmt() {
        return xxTotNetDiscAmt;
    }

    /** @return xxTotNetPrcAmt */
    public BigDecimal getXxTotNetPrcAmt() {
        return xxTotNetPrcAmt;
    }

    /** @return xxTotFrtAmt */
    public BigDecimal getXxTotFrtAmt() {
        return xxTotFrtAmt;
    }

    /** @return xxTotSpclChrgAmt */
    public BigDecimal getXxTotSpclChrgAmt() {
        return xxTotSpclChrgAmt;
    }

    /** @return xxTotTaxAmt */
    public BigDecimal getXxTotTaxAmt() {
        return xxTotTaxAmt;
    }

    /** @return xxSlsAmt */
    public BigDecimal getXxSlsAmt() {
        return xxSlsAmt;
    }

    /** @return xxTotAmt */
    public BigDecimal getXxTotAmt() {
        return xxTotAmt;
    }

    /** @return bomHeaderFlg */
    public String getBomHeaderFlg() {
        return bomHeaderFlg;
    }

    // START ADD M.Fuji [OM028]
    /** @return baseCmptFlg */
    public String getBaseCmptFlg() {
        return baseCmptFlg;
    }

    // END ADD M.Fuji [OM028]

    /** @return entDealGrsUnitPrcAmt */
    public BigDecimal getEntDealGrsUnitPrcAmt() {
        return entDealGrsUnitPrcAmt;
    }

    /** @return lineTotDealFrtAmt */
    public BigDecimal getLineTotDealFrtAmt() {
        return lineTotDealFrtAmt;
    }

    /** @return lineTotFuncFrtAmt */
    public BigDecimal getLineTotFuncFrtAmt() {
        return lineTotFuncFrtAmt;
    }

    /** @return discPrcList */
    public List<NWZC150001CpouDiscountBean> getDiscPrcList() {
        return discPrcList;
    }

    /** @return origCpoDtlLineNum */ // S21_NA#7606
    public String getOrigCpoDtlLineNum() {
        return origCpoDtlLineNum;
    }

    /** @return origCpoDtlLineSubNum */ // S21_NA#7606
    public String getOrigCpoDtlLineSubNum() {
        return origCpoDtlLineSubNum;
    }

    // Add Start 2017/10/16 QC#21507
    /** @return cpoSrcTpCd */
    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    /** @return ordSrcRefNum */
    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }
    // Add End 2017/10/16 QC#21507

    // 2017/10/20 QC#21730 Add Start
    /** @return origInvtyLocCd */
    public String getOrigInvtyLocCd() {
        return origInvtyLocCd;
    }
    // 2017/10/20 QC#21730 Add End

    // 20121127 M.Fuji WDS Solution#104,015 Pricing End

    // 2018/01/11 S21_NA#22372 Add Start
    /** @return funcUnitFlPrcAmt */
    public BigDecimal getFuncUnitFlPrcAmt() {
        return funcUnitFlPrcAmt;
    }
    // 2018/01/11 S21_NA#22372 Add End

    public void setAlocFlg(String alocFlg) {
        this.alocFlg = alocFlg;
    }

    public void setCancDelyLimitDt(String cancDelyLimitDt) {
        this.cancDelyLimitDt = cancDelyLimitDt;
    }

    public void setCancQty(BigDecimal cancQty) {
        this.cancQty = cancQty;
    }

    public void setCancShipLimitDt(String cancShipLimitDt) {
        this.cancShipLimitDt = cancShipLimitDt;
    }

    public void setCarrAcctNum(String carrAcctNum) {
        this.carrAcctNum = carrAcctNum;
    }

    public void setCarrAcctShipNum(String carrAcctShipNum) {
        this.carrAcctShipNum = carrAcctShipNum;
    }

    public void setCarrCd(String carrCd) {
        this.carrCd = carrCd;
    }

    public void setCashDiscTermCd(String cashDiscTermCd) {
        this.cashDiscTermCd = cashDiscTermCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
    }

    public void setCoaAfflCd(String coaAfflCd) {
        this.coaAfflCd = coaAfflCd;
    }

    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    public void setCoaCcCd(String coaCcCd) {
        this.coaCcCd = coaCcCd;
    }

    public void setCoaChCd(String coaChCd) {
        this.coaChCd = coaChCd;
    }

    public void setCoaCmpyCd(String coaCmpyCd) {
        this.coaCmpyCd = coaCmpyCd;
    }

    public void setCoaExtnCd(String coaExtnCd) {
        this.coaExtnCd = coaExtnCd;
    }

    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    public void setCoaProjCd(String coaProjCd) {
        this.coaProjCd = coaProjCd;
    }

    public void setCpoDtlCancDt(String cpoDtlCancDt) {
        this.cpoDtlCancDt = cpoDtlCancDt;
    }

    public void setCpoDtlCancFlg(String cpoDtlCancFlg) {
        this.cpoDtlCancFlg = cpoDtlCancFlg;
    }

    public void setCpoDtlDealNetAmt(BigDecimal cpoDtlDealNetAmt) {
        this.cpoDtlDealNetAmt = cpoDtlDealNetAmt;
    }

    public void setCpoDtlDealSlsAmt(BigDecimal cpoDtlDealSlsAmt) {
        this.cpoDtlDealSlsAmt = cpoDtlDealSlsAmt;
    }

    public void setCpoDtlFuncNetAmt(BigDecimal cpoDtlFuncNetAmt) {
        this.cpoDtlFuncNetAmt = cpoDtlFuncNetAmt;
    }

    public void setCpoDtlFuncSlsAmt(BigDecimal cpoDtlFuncSlsAmt) {
        this.cpoDtlFuncSlsAmt = cpoDtlFuncSlsAmt;
    }

    public void setCpoDtlHldFlg(String cpoDtlHldFlg) {
        this.cpoDtlHldFlg = cpoDtlHldFlg;
    }

    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    public void setCpoOrdSubmtTs(String cpoOrdSubmtTs) {
        this.cpoOrdSubmtTs = cpoOrdSubmtTs;
    }

    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    }

    public void setCrDrRsnSubCd(String crDrRsnSubCd) {
        this.crDrRsnSubCd = crDrRsnSubCd;
    }

    public void setCustMdseCd(String custMdseCd) {
        this.custMdseCd = custMdseCd;
    }

    public void setCustUomCd(String custUomCd) {
        this.custUomCd = custUomCd;
    }

    public void setDealGrsUnitPrcAmt(BigDecimal dealGrsUnitPrcAmt) {
        this.dealGrsUnitPrcAmt = dealGrsUnitPrcAmt;
    }

    public void setDefFrtChrgMethCd(String defFrtChrgMethCd) {
        this.defFrtChrgMethCd = defFrtChrgMethCd;
    }

    public void setDefFrtChrgToCd(String defFrtChrgToCd) {
        this.defFrtChrgToCd = defFrtChrgToCd;
    }

    public void setDefInvtyLocCd(String defInvtyLocCd) {
        this.defInvtyLocCd = defInvtyLocCd;
    }

    public void setDefShpgSvcLvlCd(String defShpgSvcLvlCd) {
        this.defShpgSvcLvlCd = defShpgSvcLvlCd;
    }

    public void setDistItemFlg(String distItemFlg) {
        this.distItemFlg = distItemFlg;
    }

    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    }

    public void setDslpCashDiscTermCd(String dslpCashDiscTermCd) {
        this.dslpCashDiscTermCd = dslpCashDiscTermCd;
    }

    public void setDslpCcyCd(String dslpCcyCd) {
        this.dslpCcyCd = dslpCcyCd;
    }

    public void setDslpDealNetUnitPrcAmt(BigDecimal dslpDealNetUnitPrcAmt) {
        this.dslpDealNetUnitPrcAmt = dslpDealNetUnitPrcAmt;
    }

    public void setDslpFrtChrgMethCd(String dslpFrtChrgMethCd) {
        this.dslpFrtChrgMethCd = dslpFrtChrgMethCd;
    }

    public void setDslpFrtChrgToCd(String dslpFrtChrgToCd) {
        this.dslpFrtChrgToCd = dslpFrtChrgToCd;
    }

    public void setDslpInfoFlg(String dslpInfoFlg) {
        this.dslpInfoFlg = dslpInfoFlg;
    }

    public void setDslpPmtTermCashDiscCd(String dslpPmtTermCashDiscCd) {
        this.dslpPmtTermCashDiscCd = dslpPmtTermCashDiscCd;
    }

    public void setDslpPmtTermCd(String dslpPmtTermCd) {
        this.dslpPmtTermCd = dslpPmtTermCd;
    }

    public void setDtlRqstTpCd(String dtlRqstTpCd) {
        this.dtlRqstTpCd = dtlRqstTpCd;
    }

    public void setEdiNum(String ediNum) {
        this.ediNum = ediNum;
    }

    public void setEdiStsCd(String ediStsCd) {
        this.ediStsCd = ediStsCd;
    }

    public void setEdiSubNum(String ediSubNum) {
        this.ediSubNum = ediSubNum;
    }

    public void setEntCpoDtlDealNetAmt(BigDecimal entCpoDtlDealNetAmt) {
        this.entCpoDtlDealNetAmt = entCpoDtlDealNetAmt;
    }

    public void setEntCpoDtlDealSlsAmt(BigDecimal entCpoDtlDealSlsAmt) {
        this.entCpoDtlDealSlsAmt = entCpoDtlDealSlsAmt;
    }

    public void setEntCpoDtlFuncNetAmt(BigDecimal entCpoDtlFuncNetAmt) {
        this.entCpoDtlFuncNetAmt = entCpoDtlFuncNetAmt;
    }

    public void setEntCpoDtlFuncSlsAmt(BigDecimal entCpoDtlFuncSlsAmt) {
        this.entCpoDtlFuncSlsAmt = entCpoDtlFuncSlsAmt;
    }

    public void setEntDealNetUnitPrcAmt(BigDecimal entDealNetUnitPrcAmt) {
        this.entDealNetUnitPrcAmt = entDealNetUnitPrcAmt;
    }

    public void setEntFuncNetUnitPrcAmt(BigDecimal entFuncNetUnitPrcAmt) {
        this.entFuncNetUnitPrcAmt = entFuncNetUnitPrcAmt;
    }

    public void setExchRate(BigDecimal exchRate) {
        this.exchRate = exchRate;
    }

    public void setExpdShipDt(String expdShipDt) {
        this.expdShipDt = expdShipDt;
    }

    public void setExptFlg(String exptFlg) {
        this.exptFlg = exptFlg;
    }

    public void setExptShpgMethCd(String exptShpgMethCd) {
        this.exptShpgMethCd = exptShpgMethCd;
    }

    public void setExptShpgTermCd(String exptShpgTermCd) {
        this.exptShpgTermCd = exptShpgTermCd;
    }

    public void setFifthProdCtrlCd(String fifthProdCtrlCd) {
        this.fifthProdCtrlCd = fifthProdCtrlCd;
    }

    public void setFirstProdCtrlCd(String firstProdCtrlCd) {
        this.firstProdCtrlCd = firstProdCtrlCd;
    }

    public void setFrtChrgMethCd(String frtChrgMethCd) {
        this.frtChrgMethCd = frtChrgMethCd;
    }

    public void setFrtChrgToCd(String frtChrgToCd) {
        this.frtChrgToCd = frtChrgToCd;
    }

    public void setFuncGrsUnitPrcAmt(BigDecimal funcGrsUnitPrcAmt) {
        this.funcGrsUnitPrcAmt = funcGrsUnitPrcAmt;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public void setIntgFlg(String intgFlg) {
        this.intgFlg = intgFlg;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    public void setManPrcFlg(String manPrcFlg) {
        this.manPrcFlg = manPrcFlg;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public void setMdseCdForMstrSrch(String mdseCdForMstrSrch) {
        this.mdseCdForMstrSrch = mdseCdForMstrSrch;
    }

    public void setMdseNm(String mdseNm) {
        this.mdseNm = mdseNm;
    }

    public void setOpenFlg(String openFlg) {
        this.openFlg = openFlg;
    }

    public void setOrdCustUomQty(BigDecimal ordCustUomQty) {
        this.ordCustUomQty = ordCustUomQty;
    }

    public void setOrdLineStsCd(String ordLineStsCd) {
        this.ordLineStsCd = ordLineStsCd;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    public void setOrigCpoOrdNum(String origCpoOrdNum) {
        this.origCpoOrdNum = origCpoOrdNum;
    }

    public void setOrigInvNum(String origInvNum) {
        this.origInvNum = origInvNum;
    }

    public void setOrigMdseCd(String origMdseCd) {
        this.origMdseCd = origMdseCd;
    }

    public void setOrigMdseNm(String origMdseNm) {
        this.origMdseNm = origMdseNm;
    }

    public void setPmtCondCd(String pmtCondCd) {
        this.pmtCondCd = pmtCondCd;
    }

    public void setPmtMethCd(String pmtMethCd) {
        this.pmtMethCd = pmtMethCd;
    }

    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public void setPrcTmgCd(String prcTmgCd) {
        this.prcTmgCd = prcTmgCd;
    }

    public void setPreModifyFlg(String preModifyFlg) {
        this.preModifyFlg = preModifyFlg;
    }

    public void setRddDt(String rddDt) {
        this.rddDt = rddDt;
    }

    public void setReNumCpoDtlLineNum(String reNumCpoDtlLineNum) {
        this.reNumCpoDtlLineNum = reNumCpoDtlLineNum;
    }

    public void setReNumCpoDtlLineSubNum(String reNumCpoDtlLineSubNum) {
        this.reNumCpoDtlLineSubNum = reNumCpoDtlLineSubNum;
    }

    public void setRsdDt(String rsdDt) {
        this.rsdDt = rsdDt;
    }

    public void setSetItemShipCpltFlg(String setItemShipCpltFlg) {
        this.setItemShipCpltFlg = setItemShipCpltFlg;
    }

    public void setSetMdseCd(String setMdseCd) {
        this.setMdseCd = setMdseCd;
    }

    public void setShipCpltCd(String shipCpltCd) {
        this.shipCpltCd = shipCpltCd;
    }

    public void setShipCpltCdOld(String shipCpltCdOld) {
        this.shipCpltCdOld = shipCpltCdOld;
    }

    public void setShipQty(BigDecimal shipQty) {
        this.shipQty = shipQty;
    }

    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    public void setShipToFirstRefCmntTxt(String shipToFirstRefCmntTxt) {
        this.shipToFirstRefCmntTxt = shipToFirstRefCmntTxt;
    }

    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    }

    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    public void setShipToScdRefCmntTxt(String shipToScdRefCmntTxt) {
        this.shipToScdRefCmntTxt = shipToScdRefCmntTxt;
    }

    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    public void setShpgPlnDplyLineNum(String shpgPlnDplyLineNum) {
        this.shpgPlnDplyLineNum = shpgPlnDplyLineNum;
    }

    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    public void setSlsRepOrSlsTeamTocCd(String slsRepOrSlsTeamTocCd) {
        this.slsRepOrSlsTeamTocCd = slsRepOrSlsTeamTocCd;
    }

    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    public void setSubmtFlg(String submtFlg) {
        this.submtFlg = submtFlg;
    }

    public void setTaxFlg(String taxFlg) {
        this.taxFlg = taxFlg;
    }

    public void setThirdPtyVndDropYFlg(String thirdPtyVndDropYFlg) {
        this.thirdPtyVndDropYFlg = thirdPtyVndDropYFlg;
    }

    public void setTrialLoanLineNum(String trialLoanLineNum) {
        this.trialLoanLineNum = trialLoanLineNum;
    }

    public void setTrialLoanLineSubNum(String trialLoanLineSubNum) {
        this.trialLoanLineSubNum = trialLoanLineSubNum;
    }

    public void setTrialLoanRqstNum(String trialLoanRqstNum) {
        this.trialLoanRqstNum = trialLoanRqstNum;
    }

    public void setUomCpltFlg(String uomCpltFlg) {
        this.uomCpltFlg = uomCpltFlg;
    }

    public String getDefPmtTermCd() {
        return defPmtTermCd;
    }

    public void setDefPmtTermCd(String defPmtTermCd) {
        this.defPmtTermCd = defPmtTermCd;
    }

    public String getDefCashDiscTermCd() {
        return defCashDiscTermCd;
    }

    public void setDefCashDiscTermCd(String defCashDiscTermCd) {
        this.defCashDiscTermCd = defCashDiscTermCd;
    }

    public String getManPmtFlg() {
        return manPmtFlg;
    }

    public void setManPmtFlg(String manPmtFlg) {
        this.manPmtFlg = manPmtFlg;
    }

    // START MODIFY M.Fuji [OM028]
    //    public void setBomLvlLineNum(String bomLvlLineNum) {
    //        this.bomLvlLineNum = bomLvlLineNum;
    //    }

    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    // START MODIFY M.Fuji [OM028]

    public void setLgcyIntfcDsCpoLineNum(String lgcyIntfcDsCpoLineNum) {
        this.lgcyIntfcDsCpoLineNum = lgcyIntfcDsCpoLineNum;
    }

    public void setDsSiteSrvyPk(BigDecimal dsSiteSrvyPk) {
        this.dsSiteSrvyPk = dsSiteSrvyPk;
    }

    public void setOtsdStepNum(String otsdStepNum) {
        this.otsdStepNum = otsdStepNum;
    }

    public void setInsdStepNum(String insdStepNum) {
        this.insdStepNum = insdStepNum;
    }

    public void setStairCrawReqFlg(String stairCrawReqFlg) {
        this.stairCrawReqFlg = stairCrawReqFlg;
    }

    public void setFlgtStairNum(String flgtStairNum) {
        this.flgtStairNum = flgtStairNum;
    }

    public void setElevAvalFlg(String elevAvalFlg) {
        this.elevAvalFlg = elevAvalFlg;
    }

    public void setElevAvalFromHourMn(String elevAvalFromHourMn) {
        this.elevAvalFromHourMn = elevAvalFromHourMn;
    }

    public void setElevAvalToHourMn(String elevAvalToHourMn) {
        this.elevAvalToHourMn = elevAvalToHourMn;
    }

    public void setElevApptReqFlg(String elevApptReqFlg) {
        this.elevApptReqFlg = elevApptReqFlg;
    }

    public void setElevCtacTelNum(String elevCtacTelNum) {
        this.elevCtacTelNum = elevCtacTelNum;
    }

    public void setElevProtReqFlg(String elevProtReqFlg) {
        this.elevProtReqFlg = elevProtReqFlg;
    }

    public void setElevWdt(BigDecimal elevWdt) {
        this.elevWdt = elevWdt;
    }

    public void setElevDepthNum(BigDecimal elevDepthNum) {
        this.elevDepthNum = elevDepthNum;
    }

    public void setElevCtacPsnNm(String elevCtacPsnNm) {
        this.elevCtacPsnNm = elevCtacPsnNm;
    }

    public void setElevCapWt(BigDecimal elevCapWt) {
        this.elevCapWt = elevCapWt;
    }

    public void setElevDoorHgt(BigDecimal elevDoorHgt) {
        this.elevDoorHgt = elevDoorHgt;
    }

    public void setElevDoorWdt(BigDecimal elevDoorWdt) {
        this.elevDoorWdt = elevDoorWdt;
    }

    public void setStairAndLdgWdt(BigDecimal stairAndLdgWdt) {
        this.stairAndLdgWdt = stairAndLdgWdt;
    }

    public void setCrdrWdt(BigDecimal crdrWdt) {
        this.crdrWdt = crdrWdt;
    }

    public void setDoorWdt(BigDecimal doorWdt) {
        this.doorWdt = doorWdt;
    }

    public void setLoadDockAvalFlg(String loadDockAvalFlg) {
        this.loadDockAvalFlg = loadDockAvalFlg;
    }

    public void setLoadDockHgt(BigDecimal loadDockHgt) {
        this.loadDockHgt = loadDockHgt;
    }

    public void setTrctrAndTrailAccsFlg(String trctrAndTrailAccsFlg) {
        this.trctrAndTrailAccsFlg = trctrAndTrailAccsFlg;
    }

    public void setBldgEntDoorHgt(BigDecimal bldgEntDoorHgt) {
        this.bldgEntDoorHgt = bldgEntDoorHgt;
    }

    public void setBldgEntDoorWdt(BigDecimal bldgEntDoorWdt) {
        this.bldgEntDoorWdt = bldgEntDoorWdt;
    }

    public void setLoadDockAvalFromHourMn(String loadDockAvalFromHourMn) {
        this.loadDockAvalFromHourMn = loadDockAvalFromHourMn;
    }

    public void setLoadDockAvalToHourMn(String loadDockAvalToHourMn) {
        this.loadDockAvalToHourMn = loadDockAvalToHourMn;
    }

    public void setCarrDelyTmHourMn(String carrDelyTmHourMn) {
        this.carrDelyTmHourMn = carrDelyTmHourMn;
    }

    public void setDelyTrnspOptCd(String delyTrnspOptCd) {
        this.delyTrnspOptCd = delyTrnspOptCd;
    }

    public void setSiteSrvyAddlCmntTxt(String siteSrvyAddlCmntTxt) {
        this.siteSrvyAddlCmntTxt = siteSrvyAddlCmntTxt;
    }

    // 20130107 M.Fuji Defect#62 Start
    public void setCmpyInfoAptBldgNm(String cmpyInfoAptBldgNm) {
        this.cmpyInfoAptBldgNm = cmpyInfoAptBldgNm;
    }

    public void setCmpyInfoCtacNm(String cmpyInfoCtacNm) {
        this.cmpyInfoCtacNm = cmpyInfoCtacNm;
    }

    public void setCmpyInfoDeptNm(String cmpyInfoDeptNm) {
        this.cmpyInfoDeptNm = cmpyInfoDeptNm;
    }

    public void setCmpyInfoFlNm(String cmpyInfoFlNm) {
        this.cmpyInfoFlNm = cmpyInfoFlNm;
    }

    public void setCmpyInfoTelNum(String cmpyInfoTelNum) {
        this.cmpyInfoTelNum = cmpyInfoTelNum;
    }

    public void setSiteSrvyReqFlg(String siteSrvyReqFlg) {
        this.siteSrvyReqFlg = siteSrvyReqFlg;
    }

    // 20130107 M.Fuji Defect#62 End

    public void setConfigItmFlg(String configItmFlg) {
        this.configItmFlg = configItmFlg;
    }

    public void setCustIstlFlg(String custIstlFlg) {
        this.custIstlFlg = custIstlFlg;
    }

    // START MODIFY M.Fuji [OM028]
    //    public void setMachConfigNum(String machConfigNum) {
    //        this.machConfigNum = machConfigNum;
    //    }

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    // END MODIFY M.Fuji [OM028]

    // START ADD M.FUJI [Defect#2394]
    public void setAssetMgtFlg(String assetMgtFlg) {
        this.assetMgtFlg = assetMgtFlg;
    }

    // END ADD M.FUJI [Defect#2394]

    // 20121126 M.Fuji WDS Solution#XXX Intangible Start
    public void setMdseThirdPtyVndDropFlg(String mdseThirdPtyVndDropFlg) {
        this.mdseThirdPtyVndDropFlg = mdseThirdPtyVndDropFlg;
    }

    // 20121126 M.Fuji WDS Solution#XXX Intangible End

    // 20130410 M.Fuji Order Driven Start
    /** @param vndInvtyLocCd */
    public void setVndInvtyLocCd(String vndInvtyLocCd) {
        this.vndInvtyLocCd = vndInvtyLocCd;
    }

    // 20130410 M.Fuji Order Driven End

    // 20121127 M.Fuji WDS Solution#104,015 Pricing Start
    /** @param unitNetWt */
    public void setUnitNetWt(BigDecimal unitNetWt) {
        this.unitNetWt = unitNetWt;
    }

    // START MODIFY S.Yamamoto [OM003]
    /** @param mdseFrtClsCd */
    public void setMdseFrtClsCd(String mdseFrtClsCd) {
        this.mdseFrtClsCd = mdseFrtClsCd;
    }

    /** @param mdsePrcGrpCd */
    public void setMdsePrcGrpCd(String mdsePrcGrpCd) {
        this.mdsePrcGrpCd = mdsePrcGrpCd;
    }

    /** @param mdsePrcGrpGrsWt */
    public void setMdsePrcGrpGrsWt(BigDecimal mdsePrcGrpGrsWt) {
        this.mdsePrcGrpGrsWt = mdsePrcGrpGrsWt;
    }

    /** @param frtCondCd */
    public void setFrtCondCd(String frtCondCd) {
        this.frtCondCd = frtCondCd;
    }

    // END   MODIFY S.Yamamoto [OM003]

    // START ADD M.Fuji [OM010]
    /** @param dsContrNum */
    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    /** @param dsContrSqNum */
    public void setDsContrSqNum(String dsContrSqNum) {
        this.dsContrSqNum = dsContrSqNum;
    }

    // END   ADD M.Fuji [OM010]
    /** @param xxTotBaseAmt */
    public void setXxTotBaseAmt(BigDecimal xxTotBaseAmt) {
        this.xxTotBaseAmt = xxTotBaseAmt;
    }

    /** @param xxTotDiscAmt */
    public void setXxTotDiscAmt(BigDecimal xxTotDiscAmt) {
        this.xxTotDiscAmt = xxTotDiscAmt;
    }

    /** @param xxTotSpclPrcAmt */
    public void setXxTotSpclPrcAmt(BigDecimal xxTotSpclPrcAmt) {
        this.xxTotSpclPrcAmt = xxTotSpclPrcAmt;
    }

    /** @param xxTotNetDiscAmt */
    public void setXxTotNetDiscAmt(BigDecimal xxTotNetDiscAmt) {
        this.xxTotNetDiscAmt = xxTotNetDiscAmt;
    }

    /** @param xxTotNetPrcAmt */
    public void setXxTotNetPrcAmt(BigDecimal xxTotNetPrcAmt) {
        this.xxTotNetPrcAmt = xxTotNetPrcAmt;
    }

    /** @param xxTotFrtAmt */
    public void setXxTotFrtAmt(BigDecimal xxTotFrtAmt) {
        this.xxTotFrtAmt = xxTotFrtAmt;
    }

    /** @param xxTotSpclChrgAmt */
    public void setXxTotSpclChrgAmt(BigDecimal xxTotSpclChrgAmt) {
        this.xxTotSpclChrgAmt = xxTotSpclChrgAmt;
    }

    /** @param xxTotTaxAmt */
    public void setXxTotTaxAmt(BigDecimal xxTotTaxAmt) {
        this.xxTotTaxAmt = xxTotTaxAmt;
    }

    /** @param xxSlsAmt */
    public void setXxSlsAmt(BigDecimal xxSlsAmt) {
        this.xxSlsAmt = xxSlsAmt;
    }

    /** @param xxTotAmt */
    public void setXxTotAmt(BigDecimal xxTotAmt) {
        this.xxTotAmt = xxTotAmt;
    }

    /** @param bomHeaderFlg */
    public void setBomHeaderFlg(String bomHeaderFlg) {
        this.bomHeaderFlg = bomHeaderFlg;
    }

    // START ADD M.Fuji [OM028]
    /** @param baseCmptFlg */
    public void setBaseCmptFlg(String baseCmptFlg) {
        this.baseCmptFlg = baseCmptFlg;
    }

    // END ADD M.Fuji [OM028]

    /** @param entDealGrsUnitPrcAmt */
    public void setEntDealGrsUnitPrcAmt(BigDecimal entDealGrsUnitPrcAmt) {
        this.entDealGrsUnitPrcAmt = entDealGrsUnitPrcAmt;
    }

    /** @param lineTotDealFrtAmt */
    public void setLineTotDealFrtAmt(BigDecimal lineTotDealFrtAmt) {
        this.lineTotDealFrtAmt = lineTotDealFrtAmt;
    }

    /** @param lineTotFuncFrtAmt */
    public void setLineTotFuncFrtAmt(BigDecimal lineTotFuncFrtAmt) {
        this.lineTotFuncFrtAmt = lineTotFuncFrtAmt;
    }

    /** @param discPrcList */
    public void setDiscPrcList(List<NWZC150001CpouDiscountBean> discPrcList) {
        this.discPrcList = discPrcList;
    }

    // 20121127 M.Fuji WDS Solution#104,015 Pricing End

    public void setDsCpoLineNum(BigDecimal dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    }

    public BigDecimal getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    public void setDsCpoLineSubNum(BigDecimal dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    }

    public BigDecimal getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    }

    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }

    public void setOrdLineSrcCd(String ordLineSrcCd) {
        this.ordLineSrcCd = ordLineSrcCd;
    }

    public String getOrdLineSrcCd() {
        return ordLineSrcCd;
    }

    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    public String getRtlWhCd() {
        return rtlWhCd;
    }

    public void setRtlSwhCd(String rtlSwhCd) {
        this.rtlSwhCd = rtlSwhCd;
    }

    public String getRtlSwhCd() {
        return rtlSwhCd;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    }

    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    public void setDealPrcListPrcAmt(BigDecimal dealPrcListPrcAmt) {
        this.dealPrcListPrcAmt = dealPrcListPrcAmt;
    }

    public BigDecimal getDealPrcListPrcAmt() {
        return dealPrcListPrcAmt;
    }

    public void setCpoDtlDealTaxAmt(BigDecimal cpoDtlDealTaxAmt) {
        this.cpoDtlDealTaxAmt = cpoDtlDealTaxAmt;
    }

    public BigDecimal getCpoDtlDealTaxAmt() {
        return cpoDtlDealTaxAmt;
    }

    public void setDplyLineRefNum(String dplyLineRefNum) {
        this.dplyLineRefNum = dplyLineRefNum;
    }

    public String getDplyLineRefNum() {
        return dplyLineRefNum;
    }

    public void setCrRebilCd(String crRebilCd) {
        this.crRebilCd = crRebilCd;
    }

    public String getCrRebilCd() {
        return crRebilCd;
    }

    public void setPrcBaseDt(String prcBaseDt) {
        this.prcBaseDt = prcBaseDt;
    }

    public String getPrcBaseDt() {
        return prcBaseDt;
    }

    public BigDecimal getFuncPrcListPrcAmt() {
        return funcPrcListPrcAmt;
    }

    public void setFuncPrcListPrcAmt(BigDecimal funcPrcListPrcAmt) {
        this.funcPrcListPrcAmt = funcPrcListPrcAmt;
    }

    public String getRefCpoDtlLineNum() {
        return refCpoDtlLineNum;
    }

    public void setRefCpoDtlLineNum(String refCpoDtlLineNum) {
        this.refCpoDtlLineNum = refCpoDtlLineNum;
    }

    public String getRefCpoDtlLineSubNum() {
        return refCpoDtlLineSubNum;
    }

    public void setRefCpoDtlLineSubNum(String refCpoDtlLineSubNum) {
        this.refCpoDtlLineSubNum = refCpoDtlLineSubNum;
    }

    public String getSplyTpCd() {
        return splyTpCd;
    }

    public void setSplyTpCd(String splyTpCd) {
        this.splyTpCd = splyTpCd;
    }

    public String getSplyNm() {
        return splyNm;
    }

    public void setSplyNm(String splyNm) {
        this.splyNm = splyNm;
    }

    public String getSplyCtyAddr() {
        return splyCtyAddr;
    }

    public void setSplyCtyAddr(String splyCtyAddr) {
        this.splyCtyAddr = splyCtyAddr;
    }

    public String getSplyStCd() {
        return splyStCd;
    }

    public void setSplyStCd(String splyStCd) {
        this.splyStCd = splyStCd;
    }

    public String getSplyPostCd() {
        return splyPostCd;
    }

    public void setSplyPostCd(String splyPostCd) {
        this.splyPostCd = splyPostCd;
    }

    public String getDlrRefNum() {
        return dlrRefNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    public String getRntlTrmnDt() {
        return rntlTrmnDt;
    }

    public void setRntlTrmnDt(String rntlTrmnDt) {
        this.rntlTrmnDt = rntlTrmnDt;
    }

    public String getFirstBllgAttrbNm() {
        return firstBllgAttrbNm;
    }

    public void setFirstBllgAttrbNm(String firstBllgAttrbNm) {
        this.firstBllgAttrbNm = firstBllgAttrbNm;
    }

    public String getFirstBllgAttrbValTxt() {
        return firstBllgAttrbValTxt;
    }

    public void setFirstBllgAttrbValTxt(String firstBllgAttrbValTxt) {
        this.firstBllgAttrbValTxt = firstBllgAttrbValTxt;
    }

    public String getScdBllgAttrbNm() {
        return scdBllgAttrbNm;
    }

    public void setScdBllgAttrbNm(String scdBllgAttrbNm) {
        this.scdBllgAttrbNm = scdBllgAttrbNm;
    }

    public String getScdBllgAttrbValTxt() {
        return scdBllgAttrbValTxt;
    }

    public void setScdBllgAttrbValTxt(String scdBllgAttrbValTxt) {
        this.scdBllgAttrbValTxt = scdBllgAttrbValTxt;
    }

    public String getThirdBllgAttrbNm() {
        return thirdBllgAttrbNm;
    }

    public void setThirdBllgAttrbNm(String thirdBllgAttrbNm) {
        this.thirdBllgAttrbNm = thirdBllgAttrbNm;
    }

    public String getThirdBllgAttrbValTxt() {
        return thirdBllgAttrbValTxt;
    }

    public void setThirdBllgAttrbValTxt(String thirdBllgAttrbValTxt) {
        this.thirdBllgAttrbValTxt = thirdBllgAttrbValTxt;
    }

    public String getFrthBllgAttrbNm() {
        return frthBllgAttrbNm;
    }

    public void setFrthBllgAttrbNm(String frthBllgAttrbNm) {
        this.frthBllgAttrbNm = frthBllgAttrbNm;
    }

    public String getFrthBllgAttrbValTxt() {
        return frthBllgAttrbValTxt;
    }

    public void setFrthBllgAttrbValTxt(String frthBllgAttrbValTxt) {
        this.frthBllgAttrbValTxt = frthBllgAttrbValTxt;
    }

    public String getFifthBllgAttrbNm() {
        return fifthBllgAttrbNm;
    }

    public void setFifthBllgAttrbNm(String fifthBllgAttrbNm) {
        this.fifthBllgAttrbNm = fifthBllgAttrbNm;
    }

    public String getFifthBllgAttrbValTxt() {
        return fifthBllgAttrbValTxt;
    }

    public void setFifthBllgAttrbValTxt(String fifthBllgAttrbValTxt) {
        this.fifthBllgAttrbValTxt = fifthBllgAttrbValTxt;
    }

    public String getSixthBllgAttrbNm() {
        return sixthBllgAttrbNm;
    }

    public void setSixthBllgAttrbNm(String sixthBllgAttrbNm) {
        this.sixthBllgAttrbNm = sixthBllgAttrbNm;
    }

    public String getSixthBllgAttrbValTxt() {
        return sixthBllgAttrbValTxt;
    }

    public void setSixthBllgAttrbValTxt(String sixthBllgAttrbValTxt) {
        this.sixthBllgAttrbValTxt = sixthBllgAttrbValTxt;
    }

    public String getSplyFirstAddr() {
        return splyFirstAddr;
    }

    public void setSplyFirstAddr(String splyFirstAddr) {
        this.splyFirstAddr = splyFirstAddr;
    }

    // 2015/08/27 CSA Add Start 
    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    }

    public String getCsmpPrcListCd() {
        return csmpPrcListCd;
    }

    public void setCsmpPrcListCd(String csmpPrcListCd) {
        this.csmpPrcListCd = csmpPrcListCd;
    }

    public String getBllgAttrbCustAcctCd() {
        return bllgAttrbCustAcctCd;
    }

    public void setBllgAttrbCustAcctCd(String bllgAttrbCustAcctCd) {
        this.bllgAttrbCustAcctCd = bllgAttrbCustAcctCd;
    }

    public String getSbstMdseCd() {
        return sbstMdseCd;
    }

    public void setSbstMdseCd(String sbstMdseCd) {
        this.sbstMdseCd = sbstMdseCd;
    }

    public String getOrdSrcRefLineNum() {
        return ordSrcRefLineNum;
    }

    public void setOrdSrcRefLineNum(String ordSrcRefLineNum) {
        this.ordSrcRefLineNum = ordSrcRefLineNum;
    }

    public String getOrdSrcRefLineSubNum() {
        return ordSrcRefLineSubNum;
    }

    public void setOrdSrcRefLineSubNum(String ordSrcRefLineSubNum) {
        this.ordSrcRefLineSubNum = ordSrcRefLineSubNum;
    }

    public String getCarrSvcLvlCd() {
        return carrSvcLvlCd;
    }

    public void setCarrSvcLvlCd(String carrSvcLvlCd) {
        this.carrSvcLvlCd = carrSvcLvlCd;
    }

    public String getSupdLockFlg() {
        return supdLockFlg;
    }

    public void setSupdLockFlg(String supdLockFlg) {
        this.supdLockFlg = supdLockFlg;
    }

    public BigDecimal getPrcListEquipConfigNum() {
        return prcListEquipConfigNum;
    }

    public void setPrcListEquipConfigNum(BigDecimal prcListEquipConfigNum) {
        this.prcListEquipConfigNum = prcListEquipConfigNum;
    }

    public String getTrxCd() {
        return trxCd;
    }

    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    }

    public BigDecimal getLoanBalQty() {
        return loanBalQty;
    }

    public void setLoanBalQty(BigDecimal loanBalQty) {
        this.loanBalQty = loanBalQty;
    }

    public BigDecimal getFuncSvcRevTrnsfAmt() {
        return funcSvcRevTrnsfAmt;
    }

    public void setFuncSvcRevTrnsfAmt(BigDecimal funcSvcRevTrnsfAmt) {
        this.funcSvcRevTrnsfAmt = funcSvcRevTrnsfAmt;
    }

    public BigDecimal getDealSvcRevTrnsfAmt() {
        return dealSvcRevTrnsfAmt;
    }

    public void setDealSvcRevTrnsfAmt(BigDecimal dealSvcRevTrnsfAmt) {
        this.dealSvcRevTrnsfAmt = dealSvcRevTrnsfAmt;
    }

    public BigDecimal getFuncSvcCostTrnsfAmt() {
        return funcSvcCostTrnsfAmt;
    }

    public void setFuncSvcCostTrnsfAmt(BigDecimal funcSvcCostTrnsfAmt) {
        this.funcSvcCostTrnsfAmt = funcSvcCostTrnsfAmt;
    }

    public BigDecimal getDealSvcCostTrnsfAmt() {
        return dealSvcCostTrnsfAmt;
    }

    public void setDealSvcCostTrnsfAmt(BigDecimal dealSvcCostTrnsfAmt) {
        this.dealSvcCostTrnsfAmt = dealSvcCostTrnsfAmt;
    }

    public BigDecimal getFuncManFlAdjAmt() {
        return funcManFlAdjAmt;
    }

    public void setFuncManFlAdjAmt(BigDecimal funcManFlAdjAmt) {
        this.funcManFlAdjAmt = funcManFlAdjAmt;
    }

    public BigDecimal getDealManFlAdjAmt() {
        return dealManFlAdjAmt;
    }

    public void setDealManFlAdjAmt(BigDecimal dealManFlAdjAmt) {
        this.dealManFlAdjAmt = dealManFlAdjAmt;
    }

    public BigDecimal getFuncManRepRevAdjAmt() {
        return funcManRepRevAdjAmt;
    }

    public void setFuncManRepRevAdjAmt(BigDecimal funcManRepRevAdjAmt) {
        this.funcManRepRevAdjAmt = funcManRepRevAdjAmt;
    }

    public BigDecimal getDealManRepRevAdjAmt() {
        return dealManRepRevAdjAmt;
    }

    public void setDealManRepRevAdjAmt(BigDecimal dealManRepRevAdjAmt) {
        this.dealManRepRevAdjAmt = dealManRepRevAdjAmt;
    }

    public String getShopItemFlg() {
        return shopItemFlg;
    }

    public void setShopItemFlg(String shopItemFlg) {
        this.shopItemFlg = shopItemFlg;
    }

    public String getOrdUpldRefNum() {
        return ordUpldRefNum;
    }

    public void setOrdUpldRefNum(String ordUpldRefNum) {
        this.ordUpldRefNum = ordUpldRefNum;
    }

    /**
     * @return hardDriveRmvInclFlg
     */
    public String getHardDriveRmvInclFlg() {
        return hardDriveRmvInclFlg;
    }

    /**
     * @param hardDriveRmvInclFlg hardDriveRmvInclFlg
     */
    public void setHardDriveRmvInclFlg(String hardDriveRmvInclFlg) {
        this.hardDriveRmvInclFlg = hardDriveRmvInclFlg;
    }

    /**
     * @return hardDriveEraseInclFlg
     */
    public String getHardDriveEraseInclFlg() {
        return hardDriveEraseInclFlg;
    }

    /**
     * @param hardDriveEraseInclFlg hardDriveEraseInclFlg
     */
    public void setHardDriveEraseInclFlg(String hardDriveEraseInclFlg) {
        this.hardDriveEraseInclFlg = hardDriveEraseInclFlg;
    }

    /**
     * @return convCompFlg
     */
    public String getConvCompFlg() {
        return convCompFlg;
    }

    /**
     * @param convCompFlg convCompFlg
     */
    public void setConvCompFlg(String convCompFlg) {
        this.convCompFlg = convCompFlg;
    }

    /**
     * @return pickSvcConfigMstrPk
     */
    public BigDecimal getPickSvcConfigMstrPk() {
        return pickSvcConfigMstrPk;
    }

    /**
     * @param pickSvcConfigMstrPk pickSvcConfigMstrPk
     */
    public void setPickSvcConfigMstrPk(BigDecimal pickSvcConfigMstrPk) {
        this.pickSvcConfigMstrPk = pickSvcConfigMstrPk;
    }

    /**
     * @return svcMachMstrPk
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * @param svcMachMstrPk svcMachMstrPk
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    // 2015/08/27 CSA Add End 

 // 2015/12/03 S21_NA#1407 Add start
    /** Get DS CPO Config PK
     * @return DS CPO Config PK
     */
    public BigDecimal getDsCpoConfigPk() {
        return this.dsCpoConfigPk;
    }

    /**
     * Set DS CPO Config PK
     * @param dsCpoConfigPk DS CPO Config PK
     */
    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }
 // 2015/12/03 S21_NA#1407 Add end

    // S21_NA#7606
    /**
     * Set ORIG_CPO_DTL_LINE_NUM
     * @param origCpoDtlLineNum ORIG_CPO_DTL_LINE_NUM
     */
    public void setOrigCpoDtlLineNum(String origCpoDtlLineNum) {
        this.origCpoDtlLineNum = origCpoDtlLineNum;
    }

    /**
     * Set ORIG_CPO_DTL_LINE_SUB_NUM
     * @param origCpoDtlLineSubNum ORIG_CPO_DTL_LINE_SUB_NUM
     */
    public void setOrigCpoDtlLineSubNum(String origCpoDtlLineSubNum) {
        this.origCpoDtlLineSubNum = origCpoDtlLineSubNum;
    }

    // 2016/09/05 S21_NA#6523 Add Start
    public String getDsCpoDtlCratUsrId() {
        return dsCpoDtlCratUsrId;
    }

    public void setDsCpoDtlCratUsrId(String dsCpoDtlCratUsrId) {
        this.dsCpoDtlCratUsrId = dsCpoDtlCratUsrId;
    }

    public String getDsCpoDtlCratTs() {
        return dsCpoDtlCratTs;
    }

    public void setDsCpoDtlCratTs(String dsCpoDtlCratTs) {
        this.dsCpoDtlCratTs = dsCpoDtlCratTs;
    }

    public String getDsCpoDtlUpdUsrId() {
        return dsCpoDtlUpdUsrId;
    }

    public void setDsCpoDtlUpdUsrId(String dsCpoDtlUpdUsrId) {
        this.dsCpoDtlUpdUsrId = dsCpoDtlUpdUsrId;
    }

    public String getDsCpoDtlUpdTs() {
        return dsCpoDtlUpdTs;
    }

    public void setDsCpoDtlUpdTs(String dsCpoDtlUpdTs) {
        this.dsCpoDtlUpdTs = dsCpoDtlUpdTs;
    }
    // 2016/09/05 S21_NA#6523 Add End

    // QC#16425 2016/12/14 Add Start
    public String getConvProcStsCd() {
        return convProcStsCd;
    }

    public void setConvProcStsCd(String convProcStsCd) {
        this.convProcStsCd = convProcStsCd;
    }
    // QC#16425 2016/12/14 Add End

    // Add Start 2017/10/16 QC#21507
    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    }

    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    }
    // Add End 2017/10/16 QC#21507

    // 2017/10/20 QC#21730 Add Start
    public void setOrigInvtyLocCd(String origInvtyLocCd) {
        this.origInvtyLocCd = origInvtyLocCd;
    }
    // 2017/10/20 QC#21730 Add End

    // 2018/01/11 S21_NA#22372 Add Start
    public void setFuncUnitFlPrcAmt(BigDecimal funcUnitFlPrcAmt) {
        this.funcUnitFlPrcAmt =  funcUnitFlPrcAmt;
    }
    // 2018/01/11 S21_NA#22372 Add End

    // 2018/08/02 S21_NA#25404 Add Start
    public String getChngRsnTpCd() {
        return chngRsnTpCd;
    }

    public void setChngRsnTpCd(String chngRsnTpCd) {
        this.chngRsnTpCd = chngRsnTpCd;
    }

    public String getBizProcCmntTxt() {
        return bizProcCmntTxt;
    }

    public void setBizProcCmntTxt(String bizProcCmntTxt) {
        this.bizProcCmntTxt = bizProcCmntTxt;
    }
    // 2018/08/02 S21_NA#25404 Add End

    // 2019/10/04 S21_NA#51372 Add Start
    public String getPrntVndNm() {
        return prntVndNm;
    }

    public void setPrntVndNm(String prntVndNm) {
        this.prntVndNm = prntVndNm;
    }
    // 2019/10/04 S21_NA#51372 Add End

    // 2019/11/15 S21_NA#54199 Add Start
    public String getOrdLineDplyStsCd() {
        return ordLineDplyStsCd;
    }

    public void setOrdLineDplyStsCd(String ordLineDplyStsCd) {
        this.ordLineDplyStsCd = ordLineDplyStsCd;
    }
    // 2019/11/15 S21_NA#54199 Add End

    // Q#58230 Add
    public String getProdCondCd() {
        return prodCondCd;
    }

    public void setProdCondCd(String prodCondCd) {
        this.prodCondCd = prodCondCd;
    }
}
