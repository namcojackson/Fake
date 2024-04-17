/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
//import business.parts.NWZC150001_cpoDetailContactPersonListPMsgArray;
//import business.parts.NWZC150001_cpoHeaderContactPersonListPMsgArray;
//import business.parts.NWZC150001_cpoPickUpMachineListPMsgArray;
//import business.parts.NWZC150001_holdListPMsgArray;
//import business.parts.NWZC150001_mdsePromoListPMsgArray;
import business.parts.NWZC150001_priceListPMsgArray;
//import business.parts.NWZC150001_soSerialListPMsgArray;

/**
 * <pre>
 *CPO Update API Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         M.Yamada        Update          CSA
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2016/10/12   Fujitsu         K.Sato          Update          S21_NA#11964
 * 2016/10/25   Fujitsu         S.Takami        Update          S21_NA#4150
 * 2017/02/15   Fujitsu         S.Ohki          Update          S21_NA#16184
 * 2017/03/31   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2
 * 2017/10/13   Fujitsu         R.Nakamura      Update          S21_NA#20246(L3 Sol#454)
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/06/22   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/08/02   Fujitsu         S.Takami        Update          S21_NA#25404
 * </pre>
 */
/**
 * @author q05163
 *
 */
public class NWZC150001CpouBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String glblCmpyCd;

    private String cpoOrdNum;

    private String cpoOrdTpCd;

    private String cpoOrdTs;

    private String custIssPoNum;

    private String custIssPoDt;

    private String ordHdrStsCd;

    private String ordFuflLvlCd;

    private String adminPsnCd;

    private String endUsrNm;

    private String advRcptNum;

    private String trialLoanDurnFromDt;

    private String trialLoanDurnThruDt;

    private BigDecimal entCpoTotDealSlsAmt;

    private BigDecimal entCpoTotDealDiscAmt;

    private BigDecimal entCpoTotDealNetAmt;

    private BigDecimal entCpoTotFuncSlsAmt;

    private BigDecimal entCpoTotFuncDiscAmt;

    private BigDecimal entCpoTotFuncNetAmt;

    private BigDecimal cpoTotDealSlsAmt;

    private BigDecimal cpoTotDealDiscAmt;

    private BigDecimal cpoTotDealNetAmt;

    private BigDecimal cpoTotFuncNetAmt;

    private BigDecimal cpoTotFuncSlsAmt;

    private BigDecimal cpoTotFuncDiscAmt;

    private String addRddDt;

    private String addRsdDt;

    private String addShpgSvcLvlCd;

    private String addFrtChrgToCd;

    private String addFrtChrgMethCd;

    private String cancDelyLimitDt;

    private String cancShipLimitDt;

    private String addDropShipFlg;

    private String addShipToLocNm;

    private String addShipToAddlLocNm;

    private String addShipToFirstLineAddr;

    private String addShipToScdLineAddr;

    private String addShipToThirdLineAddr;

    private String addShipToFrthLineAddr;

    private String addShipToCtyAddr;

    private String addShipToStCd;

    private String addShipToProvNm;

    private String addShipToCntyNm;

    private String addShipToPostCd;

    private String addShipToCtryCd;

    private String addShipTo01RefCmntTxt;

    private String addShipTo02RefCmntTxt;

    private String dslpInfoFlg;

    private String addPmtMethCd;

    private String addPmtCondCd;

    private String addPmtTermCashDiscCd;

    private String addPmtTermCd;

    private String addCashDiscTermCd;

    private String addOrigCpoOrdNum;

    private String addOrigInvNum;

    private String crDrRsnCd;

    private String crDrRsnSubCd;

    private String cpoHldFlg;

    private String cpoCancFlg;

    private String cpoCancDt;

    private String trialLoanRqstNum;

    private String origOrdNum;

    private String sendInvFlg;

    private String attFileNm;

    private String reBillPairCpoOrdNum;

    private String addDslpPmtMethCd;

    private String addDslpPmtCondCd;

    private String addDslpPmtTermDiscCd;

    private String addDslpPmtTermCd;

    private String addDslpCashDiscTermCd;

    private String addDslpShpgMethCd;

    private String addDslpShpgCondCd;

    private String addDslpShpgTermCd;

    private String addDslpFrtChrgToCd;

    private String addDslpFrtChrgMethCd;

    private String addDslpSellToCustCd;

    private String addShipMethCd;

    private String addShipTermCd;

    private String submtFlg;

    private String openFlg;

    private String endUsrDropFlg;

    private String custStoreNum;

    private String custDeptNum;

    private String cpoSrcTpCd;

    private String payerCustCd;

    private String billToCustCd;

    private String addShipToCustCd;

    private String sellToCustCd;

    private String sellToFirstRefCmntTxt;

    private String sellToScdRefCmntTxt;

    private String sysSrcCd;

    private String chngRsnTpCd;

    private String bizProcCmntTxt;

    private String slsDt;

    private String rqstTpCd;

    private String reNumCpoOrdNum;

    private String canRcvInvAtSetCmptFlg;

    private String itrlFlg;

    private String exptLoadPortCd;

    private String destPortNm;

    private String coaBrCd;

    private String coaCcCd;

    private String coaAcctCd;

    private String coaProdCd;

    private String coaChCd;

    private String coaProjCd;

    private String reqOvngtLimitDt;

    private String revRecogMethCd;

    // 20130226 Defect#621 S.Iidaka Start
    private String contrCmplCd;

    // 20130226 Defect#621 S.Iidaka End

    private String slcnfTrxNum;

    // 20130129 Defect#2 M.Fuji Start
    private String fastTrkId;

    private String fastTrkNum;

    // 20130129 Defect#2 M.Fuji End
    private String ordSgnDt;

    private String orgRqstDelyDt;

    private String invRcpntCustCd;

    private String xrefRmaNum;

    // START MODIFY S.Yamamoto [OM004]
    //    private String lgcyOrdTpCd;
    //
    //    private String lgcyOrdRsnCd;

    private String dsOrdTpCd;

    private String dsOrdRsnCd;

    // END   MODIFY S.Yamamoto [OM004]

    private String custRefNum;

    private String crCardTpCd;

    private String dsPmtMethCd;

    // 20121221 M.Fuji Start

    /** prcBaseDt */
    private String prcBaseDt;

    /** prcCalcDt */
    private String prcCalcDt;

    /** prcMgtGrpCd */
    private String prcMgtGrpCd;

    /** frtCondCd */
    private String frtCondCd;

    /** shpgCondCd */
    private String shpgCondCd;

    /** natlAcctOffBalSheetFlg */
    private String natlAcctOffBalSheetFlg;

    /** Business Application ID*/
    private String bizAppId;

    /** dtlBeanList */
    private List<NWZC150001CpouDetailBean> dtlBeanList;

    /** dwzc001001HoldListPMsgArray */
//    private NWZC150001_holdListPMsgArray dwzc001001HoldListPMsgArray;
    private List<NWZC150001CpouHldBean> hldBeanList = null;

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** cpoHeaderContactPersonList */
//    private NWZC150001_cpoHeaderContactPersonListPMsgArray cpoHeaderContactPersonList;
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // START DELETE M.Fuji [OM040]
    //    /** otherRepList */
    //    private NWZC150001_otherRepListPMsgArray otherRepList;
    // END DELETE M.Fuji [OM040]

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** mdsePromoList */
//    private NWZC150001_mdsePromoListPMsgArray mdsePromoList;

//    /** soSerialList */
//    private NWZC150001_soSerialListPMsgArray soSerialList;

//    /** cpoDetailContactPersonList */
//    private NWZC150001_cpoDetailContactPersonListPMsgArray cpoDetailContactPersonList;
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    /** priceList */
    private NWZC150001_priceListPMsgArray priceList;

    // 20121221 M.Fuji  End

    // 20130118 Defect#111 M.Fuji Start
    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** cpoPickUpMachineList */
//    private NWZC150001_cpoPickUpMachineListPMsgArray cpoPickUpMachineList;
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // 20130118 Defect#111 M.Fuji End

    // 2015/08/27 CSA Add Start
    private String dsOrdCatgCd;

    private String billToCustAcctCd;

    private String shipToCustAcctCd;

    private String soldToCustLocCd;

    private BigDecimal negoDealAmt;

    private String slsRepTocCd;

    private String prcCatgCd;

    private String flPrcListCd;

    private String aquNum;

    private String ordSrcImptDt;

    private String ordSrcRefNum;

    private String prcContrNum;

    private BigDecimal loanPerDaysAot;

    private String csmpContrNum;

    private String custAgmtNum;

    private String leaseCmpyPoNum;

    private String leasePrchOptCd;

    private String leaseTermCd;

    private BigDecimal leaseTermMthAot;

    private String leasePmtFreqCd;

    private BigDecimal leaseTotPmtAmt;

    private String diChkHldFlg;

    private String wfRejFlg;

    private String ordLogTpCd;

    private String dlrRefNum;

    private BigDecimal cpoUpdVrsnNum;

    private String carrSvcLvlCd;

    private String spclHdlgTpCd;

    private String prePmtChkNum;

    private BigDecimal prePmtAmt;

    private String prePmtTpCd;

    private String crRebilRsnCatgCd;

    private BigDecimal dsCrCardPk;

    private String cpoRtrnDtlUpdFlg;

    // 2015/08/27 CSA Add End

    private String carrAcctNum; // 2015/12/01 S21_NA#1270 Add

    private String xxValUpdFlg; // S21_NA#1952

    private String dclnSvcCd; // 2016/08/09 S21_NA#8388 Add

    // 2016/09/05 S21_NA#6523 Add Start
    private String dsCpoPrcInd;

    private String dsCpoCratUsrId;

    private String dsCpoCratTs;

    private String dsCpoUpdUsrId;

    private String dsCpoUpdTs;

    private String ordBookReqUsrId;

    private String ordBookReqTs;
    // 2016/09/05 S21_NA#6523 Add End

    private String ordSrcImptTs; // 2016/10/12 S21_NA#11964 Add

    private BigDecimal dsImptOrdPk; // 2017/02/15 S21_NA#16184 Add

    // 2017/03/31 S21_NA#Review structure Lv.2 Add Start
    private BigDecimal ordQty_A1;
    // 2017/03/31 S21_NA#Review structure Lv.2 Add End

    /** Obtained New CPO_ORD_NUM at first creation */
    private String cpoOrdNum_A1;

    /** No Error Check Mode 2017/03/31 S21_NA#Review structure Lv.2 Add */
    private String noValidationFlg = "N";

    /** Only Validation 2017/03/31 S21_NA#Review structure Lv.2 Add */
    private String onlyValidationFlg = "N";

    // 2018/05/11 QC#22139 Add Start
    private String quotePrintCmntTxt;

    private String ordPrintCmntTxt;

    private String shpgCmntPrintCd;
    // 2018/05/11 QC#22139 Add End

    // 2018/06/22 QC#20154 Add Start
    private List<BigDecimal> shipToChgToWmsSendList;
    // 2018/06/22 QC#20154 Add End

    // 2018/08/02 S21_NA#25404 Add Start
    private String rtnDtlChngRsnTpCd;

    private String rtnDtlbizProcCmntTxt;

    public NWZC150001CpouBean(NWZC150001PMsg pMsg) {

        // CPO
        this.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        this.setCpoOrdNum(pMsg.cpoOrdNum.getValue());
        this.setCpoOrdTpCd(pMsg.cpoOrdTpCd.getValue());
        this.setCustIssPoNum(pMsg.custIssPoNum.getValue());
        this.setCustIssPoDt(pMsg.custIssPoDt.getValue());
        this.setCpoSrcTpCd(pMsg.cpoSrcTpCd.getValue());
        this.setOrdFuflLvlCd(pMsg.ordFuflLvlCd.getValue());
        this.setBillToCustCd(pMsg.billToCustCd.getValue());
        this.setSellToCustCd(pMsg.sellToCustCd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
        // Add Start 2017/10/13 QC##20246(L3 Sol#454)
        this.setSellToFirstRefCmntTxt(pMsg.sellToFirstRefCmntTxt.getValue());
        // Add End 2017/10/13 QC##20246(L3 Sol#454)
        // this.setSellToScdRefCmntTxt(pMsg.sellToScdRefCmntTxt.getValue());        TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Mod End
        this.setAdminPsnCd(pMsg.adminPsnCd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
//        this.setPayerCustCd(pMsg.payerCustCd.getValue());
        this.setPayerCustCd(pMsg.billToCustCd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Mod End
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setEndUsrNm(pMsg.endUsrNm.getValue());                               TODO
//        this.setAdvRcptNum(pMsg.advRcptNum.getValue());                           TODO
//        this.setTrialLoanDurnFromDt(pMsg.trialLoanDurnFromDt.getValue());         TODO
//        this.setTrialLoanDurnThruDt(pMsg.trialLoanDurnThruDt.getValue());         TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setAddRddDt(pMsg.addRddDt.getValue());
        this.setAddRsdDt(pMsg.addRsdDt.getValue());
        this.setAddShpgSvcLvlCd(pMsg.addShpgSvcLvlCd.getValue());
        this.setAddFrtChrgToCd(pMsg.addFrtChrgToCd.getValue());
        this.setAddFrtChrgMethCd(pMsg.addFrtChrgMethCd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setCancDelyLimitDt(pMsg.cancDelyLimitDt.getValue());
//        this.setCancShipLimitDt(pMsg.cancShipLimitDt.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setAddDropShipFlg(pMsg.addDropShipFlg.getValue());
        this.setAddShipToCustCd(pMsg.addShipToCustCd.getValue());
        this.setAddShipToLocNm(pMsg.addShipToLocNm.getValue());
        this.setAddShipToAddlLocNm(pMsg.addShipToAddlLocNm.getValue());
        this.setAddShipToAddlLocNm(pMsg.addShipToAddlLocNm.getValue());
        this.setAddShipToFirstLineAddr(pMsg.addShipToFirstLineAddr.getValue());
        this.setAddShipToScdLineAddr(pMsg.addShipToScdLineAddr.getValue());
        this.setAddShipToThirdLineAddr(pMsg.addShipToThirdLineAddr.getValue());
        this.setAddShipToFrthLineAddr(pMsg.addShipToFrthLineAddr.getValue());
        this.setAddShipToCtyAddr(pMsg.addShipToCtyAddr.getValue());
        this.setAddShipToStCd(pMsg.addShipToStCd.getValue());
        this.setAddShipToProvNm(pMsg.addShipToProvNm.getValue());
        this.setAddShipToPostCd(pMsg.addShipToPostCd.getValue());
        this.setAddShipToCtryCd(pMsg.addShipToCtryCd.getValue());
        this.setAddShipToCntyNm(pMsg.addShipToCntyNm.getValue());
        this.setAddShipTo01RefCmntTxt(pMsg.addShipTo01RefCmntTxt.getValue());
        this.setAddShipTo02RefCmntTxt(pMsg.addShipTo02RefCmntTxt.getValue());
        this.setAddPmtTermCashDiscCd(pMsg.addPmtTermCashDiscCd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setAddPmtTermCd(pMsg.addPmtTermCd.getValue());                           TODO
//        this.setAddPmtMethCd(pMsg.addPmtMethCd.getValue());                           TODO
//        this.setAddPmtCondCd(pMsg.addPmtCondCd.getValue());                           TODO
//        this.setAddShipTermCd(pMsg.addShipTermCd.getValue());                         TODO
//        this.setAddShipMethCd(pMsg.addShipMethCd.getValue());                         TODO
//        this.setDslpInfoFlg(pMsg.dslpInfoFlg.getValue());                             DSLP Delete
//        this.setAddDslpPmtTermDiscCd(pMsg.addDslpPmtTermDiscCd.getValue());           DSLP Delete
//        this.setAddDslpPmtTermCd(pMsg.addDslpPmtTermCd.getValue());                   DSLP Delete
//        this.setAddDslpPmtMethCd(pMsg.addDslpPmtMethCd.getValue());                   DSLP Delete
//        this.setAddDslpPmtCondCd(pMsg.addDslpPmtCondCd.getValue());                   DSLP Delete
//        this.setAddDslpShpgTermCd(pMsg.addDslpShpgTermCd.getValue());                 DSLP Delete
//        this.setAddDslpShpgMethCd(pMsg.addDslpShpgMethCd.getValue());                 DSLP Delete
//        this.setAddDslpSellToCustCd(pMsg.addDslpSellToCustCd.getValue());             DSLP Delete
//        this.setAddDslpFrtChrgToCd(pMsg.addDslpFrtChrgToCd.getValue());               DSLP Delete
//        this.setAddDslpFrtChrgMethCd(pMsg.addDslpFrtChrgMethCd.getValue());           DSLP Delete
//        this.setAddDslpCashDiscTermCd(pMsg.addDslpCashDiscTermCd.getValue());         DSLP Delete
        // this.setAddCashDiscTermCd(pMsg.addCashDiscTermCd.getValue());                TODO, Set at callCpoUpdateAPI, callCpoUpdateAPI2, callCpoUpdateAPIForCancel
//        this.setTrialLoanRqstNum(pMsg.trialLoanRqstNum.getValue());                   TODO
//        this.setEndUsrDropFlg(pMsg.endUsrDropFlg.getValue());                         TODO
//        this.setCustStoreNum(pMsg.custStoreNum.getValue());                           TODO
//        this.setCustDeptNum(pMsg.custDeptNum.getValue());                             TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setChngRsnTpCd(pMsg.chngRsnTpCd.getValue());
        this.setBizProcCmntTxt(pMsg.bizProcCmntTxt.getValue());
        this.setSysSrcCd(pMsg.sysSrcCd.getValue());
        this.setSlsDt(pMsg.slsDt.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setRqstTpCd(pMsg.xxRqstTpCd.getValue());                                 TODO, Set at callCpoUpdateAPI, callCpoUpdateAPI2, callCpoUpdateAPIForCancel
//        this.setExptLoadPortCd(pMsg.exptLoadPortCd.getValue());                       TODO
//        this.setDestPortNm(pMsg.destPortNm.getValue());                               TODO
//        this.setCoaBrCd(pMsg.coaBrCd.getValue());                                     TODO Set at Pricing.
//        this.setCoaCcCd(pMsg.coaCcCd.getValue());                                     TODO
//        this.setCoaAcctCd(pMsg.coaAcctCd.getValue());                                 TODO
//        this.setCoaProdCd(pMsg.coaProdCd.getValue());                                 TODO
//        this.setCoaChCd(pMsg.coaChCd.getValue());                                     TODO
//        this.setCoaProjCd(pMsg.coaProjCd.getValue());                                 TODO
//        this.setReqOvngtLimitDt(pMsg.reqOvngtLimitDt.getValue());                     TODO
        // 20130226 Defect#621 S.Iidaka Start
//        this.setContrCmplCd(pMsg.contrCmplCd.getValue());                             TODO
        // 20130226 Defect#621 S.Iidaka End
//        this.setslcnfTrxNum(pMsg.slcnfTrxNum.getValue());                             TODO
        // 20130129 Defect#2 M.Fuji Start
//        this.setFastTrkId(pMsg.fastTrkId.getValue());                                 TODO
//        this.setFastTrkNum(pMsg.fastTrkNum.getValue());                               TODO
        // 20130129 Defect#2 M.Fuji End
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setOrdSgnDt(pMsg.ordSgnDt.getValue());
        this.setOrgRqstDelyDt(pMsg.orgRqstDelyDt.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setInvRcpntCustCd(pMsg.invRcpntCustCd.getValue());                       TODO
//        this.setXrefRmaNum(pMsg.xrefRmaNum.getValue());                               TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        // START MODIFY S.Yamamoto [OM004]
        //        this.setLgcyOrdTpCd(pMsg.lgcyOrdTpCd.getValue());
        //        this.setLgcyOrdRsnCd(pMsg.lgcyOrdRsnCd.getValue());
        this.setDSOrdTpCd(pMsg.dsOrdTpCd.getValue());
        this.setDSOrdRsnCd(pMsg.dsOrdRsnCd.getValue());
        // END   MODIFY S.Yamamoto [OM004]
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setCustRefNum(pMsg.custRefNum.getValue());                               TODO
//        this.setCrCardTpCd(pMsg.crCardTpCd.getValue());                               TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setDsPmtMethCd(pMsg.dsPmtMethCd.getValue());

        // 20121122 M.Fuji WDS Solution#104,015 Pricing Start
        this.setPrcBaseDt(pMsg.prcBaseDt.getValue());
        this.setPrcCalcDt(pMsg.prcCalcDt.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setPrcMgtGrpCd(pMsg.prcMgtGrpCd.getValue());                             TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setFrtCondCd(pMsg.frtCondCd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setShpgCondCd(pMsg.shpgCondCd.getValue());                               TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        // 20121122 M.Fuji WDS Solution#104,015 Pricing End

        // 2015/08/27 CSA Add Start 
        this.setDsOrdCatgCd(pMsg.dsOrdCatgCd.getValue());
        this.setBillToCustAcctCd(pMsg.billToCustAcctCd.getValue());
        this.setShipToCustAcctCd(pMsg.shipToCustAcctCd.getValue());
        this.setSoldToCustLocCd(pMsg.soldToCustLocCd.getValue());
        this.setNegoDealAmt(pMsg.negoDealAmt.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setSlsRepTocCd(pMsg.slsRepTocCd.getValue());                             TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setPrcCatgCd(pMsg.prcCatgCd.getValue());
        this.setFlPrcListCd(pMsg.flPrcListCd.getValue());
        this.setAquNum(pMsg.aquNum.getValue());
        this.setOrdSrcImptDt(pMsg.ordSrcImptDt.getValue());
        this.setOrdSrcRefNum(pMsg.ordSrcRefNum.getValue());
        this.setPrcContrNum(pMsg.prcContrNum.getValue());
        this.setLoanPerDaysAot(pMsg.loanPerDaysAot.getValue());
        this.setCsmpContrNum(pMsg.csmpContrNum.getValue());
        this.setLeaseCmpyPoNum(pMsg.leaseCmpyPoNum.getValue());
        this.setLeasePrchOptCd(pMsg.leasePrchOptCd.getValue());
        this.setLeaseTermCd(pMsg.leaseTermCd.getValue());
        this.setLeaseTermMthAot(pMsg.leaseTermMthAot.getValue());
        this.setLeasePmtFreqCd(pMsg.leasePmtFreqCd.getValue());
        this.setLeaseTotPmtAmt(pMsg.leaseTotPmtAmt.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setDiChkHldFlg(pMsg.diChkHldFlg.getValue());                             TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        this.setOrdLogTpCd(pMsg.ordLogTpCd.getValue());
        this.setDlrRefNum(pMsg.dlrRefNum.getValue());
        this.setCarrSvcLvlCd(pMsg.carrSvcLvlCd.getValue());
        this.setSpclHdlgTpCd(pMsg.spclHdlgTpCd.getValue());
        this.setPrePmtChkNum(pMsg.prePmtChkNum.getValue());
        this.setPrePmtAmt(pMsg.prePmtAmt.getValue());
        this.setPrePmtTpCd(pMsg.prePmtTpCd.getValue());
        this.setCrRebilRsnCatgCd(pMsg.crRebilRsnCatgCd.getValue());
        this.setDsCrCardPk(pMsg.dsCrCardPk.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setCpoRtrnDtlUpdFlg(pMsg.xxCpoRtrnDtlUpdFlg.getValue());                 TODO
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        // 2015/08/27 CSA Add End 
        this.setCarrAcctNum(pMsg.carrAcctNum.getValue()); // 2015/12/01 S21_NA#1270 Add
        this.setXxValUpdFlg(pMsg.xxValUpdFlg.getValue()); // S21_NA#1952
        // 2018/05/11 QC#22139 Add Start
        this.setQuotePrintCmntTxt(pMsg.quotePrintCmntTxt.getValue());
        this.setOrdPrintCmntTxt(pMsg.ordPrintCmntTxt.getValue());
        this.setshpgCmntPrintCd(pMsg.shpgCmntPrintCd.getValue());
        // 2018/05/11 QC#22139 Add End

        // Config position :  ds cpo config Pk
        Map<String, BigDecimal> posConfigPk = new HashMap<String, BigDecimal>();
        for (int i = 0; i <= pMsg.cpoConfig.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(pMsg.cpoConfig.no(i).dsOrdPosnNum) //
                    && S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, pMsg.cpoConfig.no(i).configCatgCd.getValue())) {
                posConfigPk.put(pMsg.cpoConfig.no(i).dsOrdPosnNum.getValue(), pMsg.cpoConfig.no(i).dsCpoConfigPk.getValue());
            }
        }
        // CPO_DTL
        this.dtlBeanList = new ArrayList<NWZC150001CpouDetailBean>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {

            NWZC150001CpouDetailBean cpouDtlBean = new NWZC150001CpouDetailBean(pMsg.A.no(i));
            cpouDtlBean.setGlblCmpyCd(this.glblCmpyCd);
            cpouDtlBean.setCpoOrdNum(this.cpoOrdNum);

            BigDecimal dsCpoConfigPk = posConfigPk.get(cpouDtlBean.getDsOrdPosnNum());
            if (dsCpoConfigPk != null) {
                cpouDtlBean.setDsCpoConfigPk(dsCpoConfigPk);
            }
            this.dtlBeanList.add(cpouDtlBean);
        }

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setDwzc001001HoldListPMsgArray(pMsg.holdList);
//        this.setCpoHeaderContactPersonList(pMsg.cpoHeaderContactPersonList);
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End

        // START DELETE M.Fuji [OM040]
        //        this.setOtherRepList(pMsg.otherRepList);
        // END DELETE M.Fuji [OM040]
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setMdsePromoList(pMsg.mdsePromoList);
//        this.setSoSerialList(pMsg.soSerialList);
//        this.setCpoDetailContactPersonList(pMsg.cpoDetailContactPersonList);
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End

        // 20121122 M.Fuji WDS Solution#104,015 Pricing
        this.setPriceList(pMsg.priceList);

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
        // 20130118 Defect#111 M.Fuji Start
//        this.setCpoPickUpMachineList(pMsg.cpoPickUpMachineList);                      TODO
        // 20130118 Defect#111 M.Fuji End
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End

        // S21_NA#10893 ADD Start
        this.setAddOrigCpoOrdNum(pMsg.addOrigCpoOrdNum.getValue());
        this.setReBillPairCpoOrdNum(pMsg.reBillPairCpoOrdNum.getValue());
        // S21_NA#10893 ADD End

        this.setDclnSvcCd(pMsg.dclnSvcCd.getValue()); // 2016/08/09 S21_NA#8388 Add

        // 2016/09/05 S21_NA#6523 Add Start
        this.setDsCpoPrcInd(pMsg.dsCpoPrcInd.getValue());
        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        this.setDsCpoCratUsrId(pMsg.dsCpoCratUsrId.getValue());                       TODO, Set at callCpoUpdateAPI etc
//        this.setDsCpoCratTs(pMsg.dsCpoCratTs.getValue());                             TODO, Set at callCpoUpdateAPI etc
//        this.setDsCpoUpdUsrId(pMsg.dsCpoUpdUsrId.getValue());                         TODO, Set at callCpoUpdateAPI etc
//        this.setDsCpoUpdTs(pMsg.dsCpoUpdTs.getValue());                               TODO, Set at callCpoUpdateAPI etc
//        this.setOrdBookReqUsrId(pMsg.ordBookReqUsrId.getValue());                     TODO, Set at callCpoUpdateAPI etc
//        this.setOrdBookReqTs(pMsg.ordBookReqTs.getValue());                           TODO, Set at callCpoUpdateAPI etc
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        // 2016/09/05 S21_NA#6523 Add End
        // 2016/10/25 S21_NA#4150 Add Start
        if (ZYPCommonFunc.hasValue(pMsg.bizAppId)) {
            this.setBizAppId(pMsg.bizAppId.getValue());
        } else {
            this.setBizAppId(null);
        }
        // 2016/10/25 S21_NA#4150 Add End

        this.setOrdSrcImptTs(pMsg.ordSrcImptTs.getValue()); // 2016/10/12 S21_NA#11964 Add

        this.setDsImptOrdPk(pMsg.dsImptOrdPk.getValue()); // 2017/02/15 S21_NA#16184 Add

        this.hldBeanList = new ArrayList<NWZC150001CpouHldBean>();

        // 2018/08/02 S21_NA#25404 Add Start
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.chngRsnTpCd_B1)) {
                this.setRtnDtlChngRsnTpCd(rtnDtlPMsg.chngRsnTpCd_B1.getValue());
                this.setRtnDtlbizProcCmntTxt(rtnDtlPMsg.bizProcCmntTxt_B1.getValue());
                break;
            }
        }
        // 2018/08/02 S21_NA#25404 Add End
    }

    public String addShipMethCd() {
        return addDslpFrtChrgToCd;
    }

    public String getAddCashDiscTermCd() {
        return addCashDiscTermCd;
    }

    public String getAddDropShipFlg() {
        return addDropShipFlg;
    }

    public String getAddDslpCashDiscTermCd() {
        return addDslpCashDiscTermCd;
    }

    public String getAddDslpFrtChrgMethCd() {
        return addDslpFrtChrgMethCd;
    }

    public String getAddDslpFrtChrgToCd() {
        return addDslpFrtChrgToCd;
    }

    public String getAddDslpPmtCondCd() {
        return addDslpPmtCondCd;
    }

    public String getAddDslpPmtMethCd() {
        return addDslpPmtMethCd;
    }

    public String getAddDslpPmtTermDiscCd() {
        return addDslpPmtTermDiscCd;
    }

    public String getAddDslpPmtTermCd() {
        return addDslpPmtTermCd;
    }

    public String getAddDslpSellToCustCd() {
        return addDslpSellToCustCd;
    }

    public String getAddDslpShpgCondCd() {
        return addDslpShpgCondCd;
    }

    public String getAddDslpShpgMethCd() {
        return addDslpShpgMethCd;
    }

    public String getAddDslpShpgTermCd() {
        return addDslpShpgTermCd;
    }

    public String getAddFrtChrgMethCd() {
        return addFrtChrgMethCd;
    }

    public String getAddFrtChrgToCd() {
        return addFrtChrgToCd;
    }

    public String getAddOrigCpoOrdNum() {
        return addOrigCpoOrdNum;
    }

    public String getAddOrigInvNum() {
        return addOrigInvNum;
    }

    public String getAddPmtCondCd() {
        return addPmtCondCd;
    }

    public String getAddPmtMethCd() {
        return addPmtMethCd;
    }

    public String getAddPmtTermCashDiscCd() {
        return addPmtTermCashDiscCd;
    }

    public String getAddPmtTermCd() {
        return addPmtTermCd;
    }

    public String getAddRddDt() {
        return addRddDt;
    }

    public String getAddRsdDt() {
        return addRsdDt;
    }

    public String getAddShipMethCd() {
        return addShipMethCd;
    }

    public String getAddShipTermCd() {
        return addShipTermCd;
    }

    public String getAddShipTo01RefCmntTxt() {
        return addShipTo01RefCmntTxt;
    }

    public String getAddShipTo02RefCmntTxt() {
        return addShipTo02RefCmntTxt;
    }

    public String getAddShipToAddlLocNm() {
        return addShipToAddlLocNm;
    }

    public String getAddShipToCntyNm() {
        return addShipToCntyNm;
    }

    public String getAddShipToCtryCd() {
        return addShipToCtryCd;
    }

    public String getAddShipToCtyAddr() {
        return addShipToCtyAddr;
    }

    public String getAddShipToCustCd() {
        return addShipToCustCd;
    }

    public String getAddShipToFirstLineAddr() {
        return addShipToFirstLineAddr;
    }

    public String getAddShipToFrthLineAddr() {
        return addShipToFrthLineAddr;
    }

    public String getAddShipToLocNm() {
        return addShipToLocNm;
    }

    public String getAddShipToPostCd() {
        return addShipToPostCd;
    }

    public String getAddShipToProvNm() {
        return addShipToProvNm;
    }

    public String getAddShipToScdLineAddr() {
        return addShipToScdLineAddr;
    }

    public String getAddShipToStCd() {
        return addShipToStCd;
    }

    public String getAddShipToThirdLineAddr() {
        return addShipToThirdLineAddr;
    }

    public String getAddShpgSvcLvlCd() {
        return addShpgSvcLvlCd;
    }

    public String getAdminPsnCd() {
        return adminPsnCd;
    }

    public String getAdvRcptNum() {
        return advRcptNum;
    }

    public String getAttFileNm() {
        return attFileNm;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public String getBizProcCmntTxt() {
        return bizProcCmntTxt;
    }

    public String getCancDelyLimitDt() {
        return cancDelyLimitDt;
    }

    public String getCancShipLimitDt() {
        return cancShipLimitDt;
    }

    public String getCanRcvInvAtSetCmptFlg() {
        return canRcvInvAtSetCmptFlg;
    }

    public String getChngRsnTpCd() {
        return chngRsnTpCd;
    }

    public String getCoaAcctCd() {
        return coaAcctCd;
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

    public String getCoaProdCd() {
        return coaProdCd;
    }

    public String getCoaProjCd() {
        return coaProjCd;
    }

    public String getCpoCancDt() {
        return cpoCancDt;
    }

    public String getCpoCancFlg() {
        return cpoCancFlg;
    }

    public String getCpoHldFlg() {
        return cpoHldFlg;
    }

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    public BigDecimal getCpoTotDealDiscAmt() {
        return cpoTotDealDiscAmt;
    }

    public BigDecimal getCpoTotDealNetAmt() {
        return cpoTotDealNetAmt;
    }

    public BigDecimal getCpoTotDealSlsAmt() {
        return cpoTotDealSlsAmt;
    }

    public BigDecimal getCpoTotFuncDiscAmt() {
        return cpoTotFuncDiscAmt;
    }

    public BigDecimal getCpoTotFuncNetAmt() {
        return cpoTotFuncNetAmt;
    }

    public BigDecimal getCpoTotFuncSlsAmt() {
        return cpoTotFuncSlsAmt;
    }

    public String getCrDrRsnCd() {
        return crDrRsnCd;
    }

    public String getCrDrRsnSubCd() {
        return crDrRsnSubCd;
    }

    public String getCustDeptNum() {
        return custDeptNum;
    }

    public String getCustIssPoDt() {
        return custIssPoDt;
    }

    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    public String getCustStoreNum() {
        return custStoreNum;
    }

    public String getDestPortNm() {
        return destPortNm;
    }

    public List<NWZC150001CpouDetailBean> getDtlBeanList() {
        return dtlBeanList;
    }

    public String getDslpInfoFlg() {
        return dslpInfoFlg;
    }

    public String getEndUsrDropFlg() {
        return endUsrDropFlg;
    }

    public String getEndUsrNm() {
        return endUsrNm;
    }

    public BigDecimal getEntCpoTotDealDiscAmt() {
        return entCpoTotDealDiscAmt;
    }

    public BigDecimal getEntCpoTotDealNetAmt() {
        return entCpoTotDealNetAmt;
    }

    public BigDecimal getEntCpoTotDealSlsAmt() {
        return entCpoTotDealSlsAmt;
    }

    public BigDecimal getEntCpoTotFuncDiscAmt() {
        return entCpoTotFuncDiscAmt;
    }

    public BigDecimal getEntCpoTotFuncNetAmt() {
        return entCpoTotFuncNetAmt;
    }

    public BigDecimal getEntCpoTotFuncSlsAmt() {
        return entCpoTotFuncSlsAmt;
    }

    public String getExptLoadPortCd() {
        return exptLoadPortCd;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public String getItrlFlg() {
        return itrlFlg;
    }

    public String getOpenFlg() {
        return openFlg;
    }

    public String getOrdFuflLvlCd() {
        return ordFuflLvlCd;
    }

    public String getOrdHdrStsCd() {
        return ordHdrStsCd;
    }

    public String getOrigOrdNum() {
        return origOrdNum;
    }

    public String getPayerCustCd() {
        return payerCustCd;
    }

    public String getReBillPairCpoOrdNum() {
        return reBillPairCpoOrdNum;
    }

    public String getReNumCpoOrdNum() {
        return reNumCpoOrdNum;
    }

    public String getReqOvngtLimitDt() {
        return reqOvngtLimitDt;
    }

    public String getRevRecogMethCd() {
        return revRecogMethCd;
    }

    public String getRqstTpCd() {
        return rqstTpCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public String getSellToFirstRefCmntTxt() {
        return sellToFirstRefCmntTxt;
    }

    public String getSellToScdRefCmntTxt() {
        return sellToScdRefCmntTxt;
    }

    public String getSendInvFlg() {
        return sendInvFlg;
    }

    public String getSlsDt() {
        return slsDt;
    }

    public String getSubmtFlg() {
        return submtFlg;
    }

    public String getSysSrcCd() {
        return sysSrcCd;
    }

    public String getTrialLoanDurnFromDt() {
        return trialLoanDurnFromDt;
    }

    public String getTrialLoanDurnThruDt() {
        return trialLoanDurnThruDt;
    }

    public String getTrialLoanRqstNum() {
        return trialLoanRqstNum;
    }

    // 20130226 Defect#621 S.Iidaka Start
    public String getContrCmplCd() {
        return contrCmplCd;
        // 20130226 Defect#621 S.Iidaka End
    }

    public String getslcnfTrxNum() {
        return slcnfTrxNum;
    }

    public String getFastTrkId() {
        return fastTrkId;
    }

    public String getFastTrkNum() {
        return fastTrkNum;
    }

    public String getOrdSgnDt() {
        return ordSgnDt;
    }

    public String getOrgRqstDelyDt() {
        return orgRqstDelyDt;
    }

    public String getInvRcpntCustCd() {
        return invRcpntCustCd;
    }

    public String getXrefRmaNum() {
        return xrefRmaNum;
    }

    // START MODIFY S.Yamamoto [OM004]
    //    public String getLgcyOrdTpCd() {
    //        return lgcyOrdTpCd;
    //    }
    //
    //    public String getLgcyOrdRsnCd() {
    //        return lgcyOrdRsnCd;
    //    }

    public String getDSOrdTpCd() {
        return dsOrdTpCd;
    }

    public String getDSOrdRsnCd() {
        return dsOrdRsnCd;
    }

    // END   MODIFY S.Yamamoto [OM004]

    public String getCustRefNum() {
        return custRefNum;
    }

    public String getCrCardTpCd() {
        return crCardTpCd;
    }

    public String getDsPmtMethCd() {
        return dsPmtMethCd;
    }

    /** @return prcBaseDt */
    public String getPrcBaseDt() {
        return prcBaseDt;
    }

    /** @return prcCalcDt */
    public String getprcCalcDt() {
        return prcCalcDt;
    }

    /** @return prcMgtGrpCd */
    public String getPrcMgtGrpCd() {
        return prcMgtGrpCd;
    }

    /** @return frtCondCd */
    public String getFrtCondCd() {
        return frtCondCd;
    }

    /** @return shpgCondCd */
    public String getShpgCondCd() {
        return shpgCondCd;
    }

    /** @return slcnfTrxNum */
    public String getSlcnfTrxNum() {
        return slcnfTrxNum;
    }

    /** @return prcCalcDt */
    public String getPrcCalcDt() {
        return prcCalcDt;
    }

    /** @return natlAcctOffBalSheetFlg */
    public String getNatlAcctOffBalSheetFlg() {
        return natlAcctOffBalSheetFlg;
    }

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** @return dwzc001001HoldListPMsgArray */
//    public NWZC150001_holdListPMsgArray getDwzc001001HoldListPMsgArray() {
//        return dwzc001001HoldListPMsgArray;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // START DELETE M.Fuji [OM040]
    //    /** @return otherRepList */
    //    public NWZC150001_otherRepListPMsgArray getOtherRepList() {
    //        return otherRepList;
    //    }
    // END DELETE M.Fuji [OM040]

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** @return mdsePromoList */
//    public NWZC150001_mdsePromoListPMsgArray getMdsePromoList() {
//        return mdsePromoList;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
    /** @return soSerialList */
//    public NWZC150001_soSerialListPMsgArray getSoSerialList() {
//        return soSerialList;
//    }

    //    /** @return cpoDetailContactPersonList */
//    public NWZC150001_cpoDetailContactPersonListPMsgArray getCpoDetailContactPersonList() {
//        return cpoDetailContactPersonList;
//    }

//    /** @return cpoHeaderContactPersonList */
//    public NWZC150001_cpoHeaderContactPersonListPMsgArray getCpoHeaderContactPersonList() {
//        return cpoHeaderContactPersonList;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    /** @return priceList */
    public NWZC150001_priceListPMsgArray getPriceList() {
        return priceList;
    }

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    public NWZC150001_cpoPickUpMachineListPMsgArray getCpoPickUpMachineList() {
//        return cpoPickUpMachineList;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    public void setAddCashDiscTermCd(String addCashDiscTermCd) {
        this.addCashDiscTermCd = addCashDiscTermCd;
    }

    public void setAddDropShipFlg(String addDropShipFlg) {
        this.addDropShipFlg = addDropShipFlg;
    }

    public void setAddDslpCashDiscTermCd(String addDslpCashDiscTermCd) {
        this.addDslpCashDiscTermCd = addDslpCashDiscTermCd;
    }

    public void setAddDslpFrtChrgMethCd(String addDslpFrtChrgMethCd) {
        this.addDslpFrtChrgMethCd = addDslpFrtChrgMethCd;
    }

    public void setAddDslpFrtChrgToCd(String addDslpFrtChrgToCd) {
        this.addDslpFrtChrgToCd = addDslpFrtChrgToCd;
    }

    public void setAddDslpPmtCondCd(String addDslpPmtCondCd) {
        this.addDslpPmtCondCd = addDslpPmtCondCd;
    }

    public void setAddDslpPmtMethCd(String addDslpPmtMethCd) {
        this.addDslpPmtMethCd = addDslpPmtMethCd;
    }

    public void setAddDslpPmtTermDiscCd(String addDslpPmtTermDiscCd) {
        this.addDslpPmtTermDiscCd = addDslpPmtTermDiscCd;
    }

    public void setAddDslpPmtTermCd(String addDslpPmtTermCd) {
        this.addDslpPmtTermCd = addDslpPmtTermCd;
    }

    public void setAddDslpSellToCustCd(String addDslpSellToCustCd) {
        this.addDslpSellToCustCd = addDslpSellToCustCd;
    }

    public void setAddDslpShpgCondCd(String addDslpShpgCondCd) {
        this.addDslpShpgCondCd = addDslpShpgCondCd;
    }

    public void setAddDslpShpgMethCd(String addDslpShpgMethCd) {
        this.addDslpShpgMethCd = addDslpShpgMethCd;
    }

    public void setAddDslpShpgTermCd(String addDslpShpgTermCd) {
        this.addDslpShpgTermCd = addDslpShpgTermCd;
    }

    public void setAddFrtChrgMethCd(String addFrtChrgMethCd) {
        this.addFrtChrgMethCd = addFrtChrgMethCd;
    }

    public void setAddFrtChrgToCd(String addFrtChrgToCd) {
        this.addFrtChrgToCd = addFrtChrgToCd;
    }

    public void setAddOrigCpoOrdNum(String addOrigCpoOrdNum) {
        this.addOrigCpoOrdNum = addOrigCpoOrdNum;
    }

    public void setAddOrigInvNum(String addOrigInvNum) {
        this.addOrigInvNum = addOrigInvNum;
    }

    public void setAddPmtCondCd(String addPmtCondCd) {
        this.addPmtCondCd = addPmtCondCd;
    }

    public void setAddPmtMethCd(String addPmtMethCd) {
        this.addPmtMethCd = addPmtMethCd;
    }

    public void setAddPmtTermCashDiscCd(String addPmtTermCashDiscCd) {
        this.addPmtTermCashDiscCd = addPmtTermCashDiscCd;
    }

    public void setAddPmtTermCd(String addPmtTermCd) {
        this.addPmtTermCd = addPmtTermCd;
    }

    public void setAddRddDt(String addRddDt) {
        this.addRddDt = addRddDt;
    }

    public void setAddRsdDt(String addRsdDt) {
        this.addRsdDt = addRsdDt;
    }

    public void setAddShipMethCd(String addShipMethCd) {
        this.addShipMethCd = addShipMethCd;
    }

    public void setAddShipTermCd(String addShipTermCd) {
        this.addShipTermCd = addShipTermCd;
    }

    public void setAddShipTo01RefCmntTxt(String addShipTo01RefCmntTxt) {
        this.addShipTo01RefCmntTxt = addShipTo01RefCmntTxt;
    }

    public void setAddShipTo02RefCmntTxt(String addShipTo02RefCmntTxt) {
        this.addShipTo02RefCmntTxt = addShipTo02RefCmntTxt;
    }

    public void setAddShipToAddlLocNm(String addShipToAddlLocNm) {
        this.addShipToAddlLocNm = addShipToAddlLocNm;
    }

    public void setAddShipToCntyNm(String addShipToCntyNm) {
        this.addShipToCntyNm = addShipToCntyNm;
    }

    public void setAddShipToCtryCd(String addShipToCtryCd) {
        this.addShipToCtryCd = addShipToCtryCd;
    }

    public void setAddShipToCtyAddr(String addShipToCtyAddr) {
        this.addShipToCtyAddr = addShipToCtyAddr;
    }

    public void setAddShipToCustCd(String addShipToCustCd) {
        this.addShipToCustCd = addShipToCustCd;
    }

    public void setAddShipToFirstLineAddr(String addShipToFirstLineAddr) {
        this.addShipToFirstLineAddr = addShipToFirstLineAddr;
    }

    public void setAddShipToFrthLineAddr(String addShipToFrthLineAddr) {
        this.addShipToFrthLineAddr = addShipToFrthLineAddr;
    }

    public void setAddShipToLocNm(String addShipToLocNm) {
        this.addShipToLocNm = addShipToLocNm;
    }

    public void setAddShipToPostCd(String addShipToPostCd) {
        this.addShipToPostCd = addShipToPostCd;
    }

    public void setAddShipToProvNm(String addShipToProvNm) {
        this.addShipToProvNm = addShipToProvNm;
    }

    public void setAddShipToScdLineAddr(String addShipToScdLineAddr) {
        this.addShipToScdLineAddr = addShipToScdLineAddr;
    }

    public void setAddShipToStCd(String addShipToStCd) {
        this.addShipToStCd = addShipToStCd;
    }

    public void setAddShipToThirdLineAddr(String addShipToThirdLineAddr) {
        this.addShipToThirdLineAddr = addShipToThirdLineAddr;
    }

    public void setAddShpgSvcLvlCd(String addShpgSvcLvlCd) {
        this.addShpgSvcLvlCd = addShpgSvcLvlCd;
    }

    public void setAdminPsnCd(String adminPsnCd) {
        this.adminPsnCd = adminPsnCd;
    }

    public void setAdvRcptNum(String advRcptNum) {
        this.advRcptNum = advRcptNum;
    }

    public void setAttFileNm(String attFileNm) {
        this.attFileNm = attFileNm;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public void setBizProcCmntTxt(String bizProcCmntTxt) {
        this.bizProcCmntTxt = bizProcCmntTxt;
    }

    public void setCancDelyLimitDt(String cancDelyLimitDt) {
        this.cancDelyLimitDt = cancDelyLimitDt;
    }

    public void setCancShipLimitDt(String cancShipLimitDt) {
        this.cancShipLimitDt = cancShipLimitDt;
    }

    public void setCanRcvInvAtSetCmptFlg(String canRcvInvAtSetCmptFlg) {
        this.canRcvInvAtSetCmptFlg = canRcvInvAtSetCmptFlg;
    }

    public void setChngRsnTpCd(String chngRsnTpCd) {
        this.chngRsnTpCd = chngRsnTpCd;
    }

    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
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

    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    public void setCoaProjCd(String coaProjCd) {
        this.coaProjCd = coaProjCd;
    }

    public void setCpoCancDt(String cpoCancDt) {
        this.cpoCancDt = cpoCancDt;
    }

    public void setCpoCancFlg(String cpoCancFlg) {
        this.cpoCancFlg = cpoCancFlg;
    }

    public void setCpoHldFlg(String cpoHldFlg) {
        this.cpoHldFlg = cpoHldFlg;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    }

    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    }

    public void setCpoTotDealDiscAmt(BigDecimal cpoTotDealDiscAmt) {
        this.cpoTotDealDiscAmt = cpoTotDealDiscAmt;
    }

    public void setCpoTotDealNetAmt(BigDecimal cpoTotDealNetAmt) {
        this.cpoTotDealNetAmt = cpoTotDealNetAmt;
    }

    public void setCpoTotDealSlsAmt(BigDecimal cpoTotDealSlsAmt) {
        this.cpoTotDealSlsAmt = cpoTotDealSlsAmt;
    }

    public void setCpoTotFuncDiscAmt(BigDecimal cpoTotFuncDiscAmt) {
        this.cpoTotFuncDiscAmt = cpoTotFuncDiscAmt;
    }

    public void setCpoTotFuncNetAmt(BigDecimal cpoTotFuncNetAmt) {
        this.cpoTotFuncNetAmt = cpoTotFuncNetAmt;
    }

    public void setCpoTotFuncSlsAmt(BigDecimal cpoTotFuncSlsAmt) {
        this.cpoTotFuncSlsAmt = cpoTotFuncSlsAmt;
    }

    public void setCrDrRsnCd(String crDrRsnCd) {
        this.crDrRsnCd = crDrRsnCd;
    }

    public void setCrDrRsnSubCd(String crDrRsnSubCd) {
        this.crDrRsnSubCd = crDrRsnSubCd;
    }

    public void setCustDeptNum(String custDeptNum) {
        this.custDeptNum = custDeptNum;
    }

    public void setCustIssPoDt(String custIssPoDt) {
        this.custIssPoDt = custIssPoDt;
    }

    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    public void setCustStoreNum(String custStoreNum) {
        this.custStoreNum = custStoreNum;
    }

    public void setDestPortNm(String destPortNm) {
        this.destPortNm = destPortNm;
    }

    public void setDtlBeanList(List<NWZC150001CpouDetailBean> dtlBeanList) {
        this.dtlBeanList = dtlBeanList;
    }

    public void setDslpInfoFlg(String dslpInfoFlg) {
        this.dslpInfoFlg = dslpInfoFlg;
    }

    public void setEndUsrDropFlg(String endUsrDropFlg) {
        this.endUsrDropFlg = endUsrDropFlg;
    }

    public void setEndUsrNm(String endUsrNm) {
        this.endUsrNm = endUsrNm;
    }

    public void setEntCpoTotDealDiscAmt(BigDecimal entCpoTotDealDiscAmt) {
        this.entCpoTotDealDiscAmt = entCpoTotDealDiscAmt;
    }

    public void setEntCpoTotDealNetAmt(BigDecimal entCpoTotDealNetAmt) {
        this.entCpoTotDealNetAmt = entCpoTotDealNetAmt;
    }

    public void setEntCpoTotDealSlsAmt(BigDecimal entCpoTotDealSlsAmt) {
        this.entCpoTotDealSlsAmt = entCpoTotDealSlsAmt;
    }

    public void setEntCpoTotFuncDiscAmt(BigDecimal entCpoTotFuncDiscAmt) {
        this.entCpoTotFuncDiscAmt = entCpoTotFuncDiscAmt;
    }

    public void setEntCpoTotFuncNetAmt(BigDecimal entCpoTotFuncNetAmt) {
        this.entCpoTotFuncNetAmt = entCpoTotFuncNetAmt;
    }

    public void setEntCpoTotFuncSlsAmt(BigDecimal entCpoTotFuncSlsAmt) {
        this.entCpoTotFuncSlsAmt = entCpoTotFuncSlsAmt;
    }

    public void setExptLoadPortCd(String exptLoadPortCd) {
        this.exptLoadPortCd = exptLoadPortCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public void setItrlFlg(String itrlFlg) {
        this.itrlFlg = itrlFlg;
    }

    public void setOpenFlg(String openFlg) {
        this.openFlg = openFlg;
    }

    public void setOrdFuflLvlCd(String ordFuflLvlCd) {
        this.ordFuflLvlCd = ordFuflLvlCd;
    }

    public void setOrdHdrStsCd(String ordHdrStsCd) {
        this.ordHdrStsCd = ordHdrStsCd;
    }

    public void setOrigOrdNum(String origOrdNum) {
        this.origOrdNum = origOrdNum;
    }

    public void setPayerCustCd(String payerCustCd) {
        this.payerCustCd = payerCustCd;
    }

    public void setReBillPairCpoOrdNum(String reBillPairCpoOrdNum) {
        this.reBillPairCpoOrdNum = reBillPairCpoOrdNum;
    }

    public void setReNumCpoOrdNum(String reNumCpoOrdNum) {
        this.reNumCpoOrdNum = reNumCpoOrdNum;
    }

    public void setReqOvngtLimitDt(String reqOvngtLimitDt) {
        this.reqOvngtLimitDt = reqOvngtLimitDt;
    }

    public void setRevRecogMethCd(String revRecogMethCd) {
        this.revRecogMethCd = revRecogMethCd;
    }

    public void setRqstTpCd(String rqstTpCd) {
        this.rqstTpCd = rqstTpCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public void setSellToFirstRefCmntTxt(String sellToFirstRefCmntTxt) {
        this.sellToFirstRefCmntTxt = sellToFirstRefCmntTxt;
    }

    public void setSellToScdRefCmntTxt(String sellToScdRefCmntTxt) {
        this.sellToScdRefCmntTxt = sellToScdRefCmntTxt;
    }

    public void setSendInvFlg(String sendInvFlg) {
        this.sendInvFlg = sendInvFlg;
    }

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public void setSubmtFlg(String submtFlg) {
        this.submtFlg = submtFlg;
    }

    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    public void setTrialLoanDurnFromDt(String trialLoanDurnFromDt) {
        this.trialLoanDurnFromDt = trialLoanDurnFromDt;
    }

    public void setTrialLoanDurnThruDt(String trialLoanDurnThruDt) {
        this.trialLoanDurnThruDt = trialLoanDurnThruDt;
    }

    public void setTrialLoanRqstNum(String trialLoanRqstNum) {
        this.trialLoanRqstNum = trialLoanRqstNum;
    }

    // 20130226 Defect#621 S.Iidaka Start
    public void setContrCmplCd(String contrCmplCd) {
        this.contrCmplCd = contrCmplCd;
        // 20130226 Defect#621 S.Iidaka End
    }

    public void setslcnfTrxNum(String slcnfTrxNum) {
        this.slcnfTrxNum = slcnfTrxNum;
    }

    public void setFastTrkId(String fastTrkId) {
        this.fastTrkId = fastTrkId;
    }

    public void setFastTrkNum(String fastTrkNum) {
        this.fastTrkNum = fastTrkNum;
    }

    public void setOrdSgnDt(String ordSgnDt) {
        this.ordSgnDt = ordSgnDt;
    }

    public void setOrgRqstDelyDt(String orgRqstDelyDt) {
        this.orgRqstDelyDt = orgRqstDelyDt;
    }

    public void setInvRcpntCustCd(String invRcpntCustCd) {
        this.invRcpntCustCd = invRcpntCustCd;
    }

    public void setXrefRmaNum(String xrefRmaNum) {
        this.xrefRmaNum = xrefRmaNum;
    }

    // START MODIFY S.Yamamoto [OM004]
    //    public void setLgcyOrdTpCd(String lgcyOrdTpCd) {
    //        this.lgcyOrdTpCd = lgcyOrdTpCd;
    //    }
    //
    //    public void setLgcyOrdRsnCd(String lgcyOrdRsnCd) {
    //        this.lgcyOrdRsnCd = lgcyOrdRsnCd;
    //    }

    public void setDSOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    public void setDSOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    }

    // END   MODIFY S.Yamamoto [OM004]

    public void setCustRefNum(String custRefNum) {
        this.custRefNum = custRefNum;
    }

    public void setCrCardTpCd(String crCardTpCd) {
        this.crCardTpCd = crCardTpCd;
    }

    public void setDsPmtMethCd(String dsPmtMethCd) {
        this.dsPmtMethCd = dsPmtMethCd;
    }

    /** @param prcBaseDt */
    public void setPrcBaseDt(String prcBaseDt) {
        this.prcBaseDt = prcBaseDt;
    }

    /** @param prcCalcDt */
    public void setPrcCalcDt(String prcCalcDt) {
        this.prcCalcDt = prcCalcDt;
    }

    /** @param prcMgtGrpCd */
    public void setPrcMgtGrpCd(String prcMgtGrpCd) {
        this.prcMgtGrpCd = prcMgtGrpCd;
    }

    /** @param frtCondCd */
    public void setFrtCondCd(String frtCondCd) {
        this.frtCondCd = frtCondCd;
    }

    /** @param shpgCondCd */
    public void setShpgCondCd(String shpgCondCd) {
        this.shpgCondCd = shpgCondCd;
    }

    /** @param slcnfTrxNum */
    public void setSlcnfTrxNum(String slcnfTrxNum) {
        this.slcnfTrxNum = slcnfTrxNum;
    }

    /** @param natlAcctOffBalSheetFlg */
    public void setNatlAcctOffBalSheetFlg(String natlAcctOffBalSheetFlg) {
        this.natlAcctOffBalSheetFlg = natlAcctOffBalSheetFlg;
    }

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** @param dwzc001001HoldListPMsgArray */
//    public void setDwzc001001HoldListPMsgArray(NWZC150001_holdListPMsgArray dwzc001001HoldListPMsgArray) {
//        this.dwzc001001HoldListPMsgArray = dwzc001001HoldListPMsgArray;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // START DELETE M.Fuji [OM040]
    //    /** @param otherRepList */
    //    public void setOtherRepList(NWZC150001_otherRepListPMsgArray otherRepList) {
    //        this.otherRepList = otherRepList;
    //    }
    // END DELETE M.Fuji [OM040]

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** @param mdsePromoList */
//    public void setMdsePromoList(NWZC150001_mdsePromoListPMsgArray mdsePromoList) {
//        this.mdsePromoList = mdsePromoList;
//    }

//    /** @param cpoDetailContactPersonList */
//    public void setCpoDetailContactPersonList(NWZC150001_cpoDetailContactPersonListPMsgArray cpoDetailContactPersonList) {
//        this.cpoDetailContactPersonList = cpoDetailContactPersonList;
//    }

//    /** @param soSerialList */
//    public void setSoSerialList(NWZC150001_soSerialListPMsgArray soSerialList) {
//        this.soSerialList = soSerialList;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    /** @param cpoHeaderContactPersonList */
//    public void setCpoHeaderContactPersonList(NWZC150001_cpoHeaderContactPersonListPMsgArray cpoHeaderContactPersonList) {
//        this.cpoHeaderContactPersonList = cpoHeaderContactPersonList;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    /** @param priceList */
    public void setPriceList(NWZC150001_priceListPMsgArray priceList) {
        this.priceList = priceList;
    }

    // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//    public void setCpoPickUpMachineList(NWZC150001_cpoPickUpMachineListPMsgArray cpoPickUpMachineList) {
//        this.cpoPickUpMachineList = cpoPickUpMachineList;
//    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Del End

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    }

    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }

    public void setNegoDealAmt(BigDecimal negoDealAmt) {
        this.negoDealAmt = negoDealAmt;
    }

    public BigDecimal getNegoDealAmt() {
        return negoDealAmt;
    }

    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    }

    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    public void setAquNum(String aquNum) {
        this.aquNum = aquNum;
    }

    public String getAquNum() {
        return aquNum;
    }

    public void setOrdSrcImptDt(String ordSrcImptDt) {
        this.ordSrcImptDt = ordSrcImptDt;
    }

    public String getOrdSrcImptDt() {
        return ordSrcImptDt;
    }

    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    }

    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }

    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    }

    public String getPrcContrNum() {
        return prcContrNum;
    }

    public void setLoanPerDaysAot(BigDecimal loanPerDaysAot) {
        this.loanPerDaysAot = loanPerDaysAot;
    }

    public BigDecimal getLoanPerDaysAot() {
        return loanPerDaysAot;
    }

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    }

    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    public void setCustAgmtNum(String custAgmtNum) {
        this.custAgmtNum = custAgmtNum;
    }

    public String getCustAgmtNum() {
        return custAgmtNum;
    }

    public void setLeaseCmpyPoNum(String leaseCmpyPoNum) {
        this.leaseCmpyPoNum = leaseCmpyPoNum;
    }

    public String getLeaseCmpyPoNum() {
        return leaseCmpyPoNum;
    }

    public void setLeasePrchOptCd(String leasePrchOptCd) {
        this.leasePrchOptCd = leasePrchOptCd;
    }

    public String getLeasePrchOptCd() {
        return leasePrchOptCd;
    }

    public void setLeaseTermCd(String leaseTermCd) {
        this.leaseTermCd = leaseTermCd;
    }

    public String getLeaseTermCd() {
        return leaseTermCd;
    }

    public void setLeaseTermMthAot(BigDecimal leaseTermMthAot) {
        this.leaseTermMthAot = leaseTermMthAot;
    }

    public BigDecimal getLeaseTermMthAot() {
        return leaseTermMthAot;
    }

    public void setLeasePmtFreqCd(String leasePmtFreqCd) {
        this.leasePmtFreqCd = leasePmtFreqCd;
    }

    public String getLeasePmtFreqCd() {
        return leasePmtFreqCd;
    }

    public void setLeaseTotPmtAmt(BigDecimal leaseTotPmtAmt) {
        this.leaseTotPmtAmt = leaseTotPmtAmt;
    }

    public BigDecimal getLeaseTotPmtAmt() {
        return leaseTotPmtAmt;
    }

    public void setDiChkHldFlg(String diChkHldFlg) {
        this.diChkHldFlg = diChkHldFlg;
    }

    public String getDiChkHldFlg() {
        return diChkHldFlg;
    }

    // 2015/08/27 CSA Add Start
    public void setWfRejFlg(String wfRejFlg) {
        this.wfRejFlg = wfRejFlg;
    }

    public String getWfRejFlg() {
        return wfRejFlg;
    }

    public String getOrdLogTpCd() {
        return ordLogTpCd;
    }

    public void setOrdLogTpCd(String ordLogTpCd) {
        this.ordLogTpCd = ordLogTpCd;
    }

    public String getDlrRefNum() {
        return dlrRefNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    public void setCpoUpdVrsnNum(BigDecimal cpoUpdVrsnNum) {
        this.cpoUpdVrsnNum = cpoUpdVrsnNum;
    }

    public BigDecimal getCpoUpdVrsnNum() {
        return cpoUpdVrsnNum;
    }

    public String getCarrSvcLvlCd() {
        return carrSvcLvlCd;
    }

    public void setCarrSvcLvlCd(String carrSvcLvlCd) {
        this.carrSvcLvlCd = carrSvcLvlCd;
    }

    public String getSpclHdlgTpCd() {
        return spclHdlgTpCd;
    }

    public void setSpclHdlgTpCd(String spclHdlgTpCd) {
        this.spclHdlgTpCd = spclHdlgTpCd;
    }

    public String getPrePmtChkNum() {
        return prePmtChkNum;
    }

    public void setPrePmtChkNum(String prePmtChkNum) {
        this.prePmtChkNum = prePmtChkNum;
    }

    public BigDecimal getPrePmtAmt() {
        return prePmtAmt;
    }

    public void setPrePmtAmt(BigDecimal prePmtAmt) {
        this.prePmtAmt = prePmtAmt;
    }

    public String getPrePmtTpCd() {
        return prePmtTpCd;
    }

    public void setPrePmtTpCd(String prePmtTpCd) {
        this.prePmtTpCd = prePmtTpCd;
    }

    public String getCrRebilRsnCatgCd() {
        return crRebilRsnCatgCd;
    }

    public void setCrRebilRsnCatgCd(String crRebilRsnCatgCd) {
        this.crRebilRsnCatgCd = crRebilRsnCatgCd;
    }

    public BigDecimal getDsCrCardPk() {
        return dsCrCardPk;
    }

    public void setDsCrCardPk(BigDecimal dsCrCardPk) {
        this.dsCrCardPk = dsCrCardPk;
    }

    // 2015/08/27 CSA Add End

    /**
     * @param cpoRtrnDtlUpdFlg cpoRtrnDtlUpdFlg
     */
    public void setCpoRtrnDtlUpdFlg(String cpoRtrnDtlUpdFlg) {
        this.cpoRtrnDtlUpdFlg = cpoRtrnDtlUpdFlg;
    }

    /**
     * @return cpoRtrnDtlUpdFlg
     */
    public String getCpoRtrnDtlUpdFlg() {
        return cpoRtrnDtlUpdFlg;
    }

    // 2015/12/01 S21_NA#1270 Add Start
    /**
     * Set Carrier Account Number
     * @param carrAcctNum Carrier Account Number
     */
    public void setCarrAcctNum(String carrAcctNum) {
        this.carrAcctNum = carrAcctNum;
    }
    /**
     * Get Carrier Account number
     * @return Carrier Account Number
     */
    public String getCarrAcctNum() {
        return this.carrAcctNum;
    }
    // 2015/12/01 S21_NA#1270 Add End

    /**
     * Set value update flag
     * S21_NA#1952
     * @param xxValUpdFlg value update flag
     */
    public void setXxValUpdFlg(String xxValUpdFlg) {
        this.xxValUpdFlg = xxValUpdFlg;
    }

    /**
     * Get value update flag
     * S21_NA#1952
     * @return value update flag
     */
    public String getXxValUpdFlg() {
        return this.xxValUpdFlg;
    }

    // 2016/08/09 S21_NA#8388 Add Start
    /**
     * Set dclnSvcCd
     * @param dclnSvcCd String
     */
    public void setDclnSvcCd(String dclnSvcCd) {
        this.dclnSvcCd = dclnSvcCd;
    }
    /**
     * Get dclnSvcCd
     * @return String dclnSvcCd
     */
    public String getDclnSvcCd() {
        return this.dclnSvcCd;
    }
    // 2016/08/09 S21_NA#8388 Add End

    // 2016/09/05 S21_NA#6523 Add Start
    public void setDsCpoPrcInd(String dsCpoPrcInd) {
        this.dsCpoPrcInd = dsCpoPrcInd;
    }

    public String getDsCpoPrcInd() {
        return this.dsCpoPrcInd;
    }

    public void setDsCpoCratUsrId(String dsCpoCratUsrId) {
        this.dsCpoCratUsrId = dsCpoCratUsrId;
    }

    public String getDsCpoCratUsrId() {
        return this.dsCpoCratUsrId;
    }

    public void setDsCpoCratTs(String dsCpoCratTs) {
        this.dsCpoCratTs = dsCpoCratTs;
    }

    public String getDsCpoCratTs() {
        return this.dsCpoCratTs;
    }

    public void setDsCpoUpdUsrId(String dsCpoUpdUsrId) {
        this.dsCpoUpdUsrId = dsCpoUpdUsrId;
    }

    public String getDsCpoUpdUsrId() {
        return this.dsCpoUpdUsrId;
    }

    public void setDsCpoUpdTs(String dsCpoUpdTs) {
        this.dsCpoUpdTs = dsCpoUpdTs;
    }

    public String getDsCpoUpdTs() {
        return this.dsCpoUpdTs;
    }

    public void setOrdBookReqUsrId(String ordBookReqUsrId) {
        this.ordBookReqUsrId = ordBookReqUsrId;
    }

    public String getOrdBookReqUsrId() {
        return this.ordBookReqUsrId;
    }

    public void setOrdBookReqTs(String ordBookReqTs) {
        this.ordBookReqTs = ordBookReqTs;
    }

    public String getOrdBookReqTs() {
        return this.ordBookReqTs;
    }
    // 2016/09/05 S21_NA#6523 Add End
    // 2016/10/12 S21_NA#11964 Add Start
    /**
     * Set ordSrcImptTs
     * @param ordSrcImptTs String
     */
    public void setOrdSrcImptTs(String ordSrcImptTs) {
        this.ordSrcImptTs = ordSrcImptTs;
    }
    /**
     * Get ordSrcImptTs
     * @return String ordSrcImptTs
     */
    public String getOrdSrcImptTs() {
        return this.ordSrcImptTs;
    }
    // 2016/10/12 S21_NA#11964 Add End

    // 2016/10/25 S21_NA#4150 Add Start
    /**
     * Get Business Application ID
     * @return Business Application ID
     */
    public String getBizAppId() {
        return bizAppId;
    }

    /**
     * Set Business Application ID
     * @param bizAppId Business Application ID
     */
    public void setBizAppId(String bizAppId) {
        this.bizAppId = bizAppId;
    }
    // 2016/10/25 S21_NA#4150 Add End
    // 2017/02/15 S21_NA#16184 Add Start
    /**
     * Set DsImptOrdPk
     * @param dsImptOrdPk BigDecimal
     */
    public void setDsImptOrdPk(BigDecimal dsImptOrdPk) {
        this.dsImptOrdPk = dsImptOrdPk;
    }
    /**
     * Get dsImptOrdPk
     * @return BigDecimal dsImptOrdPk
     */
    public BigDecimal getDsImptOrdPk() {
        return this.dsImptOrdPk;
    }
    // 2017/02/15 S21_NA#16184 Add End

    // 2017/03/31 S21_NA#Review structure Lv.2 Add Start
    public void setHldBeanList(List<NWZC150001CpouHldBean> hldBeanList) {
        this.hldBeanList = hldBeanList;
    }

    public List<NWZC150001CpouHldBean> getHldBeanList() {
        return this.hldBeanList;
    }

    public void addHldBeanList(NWZC150001CpouHldBean hldBean) {
        if (this.hldBeanList == null) {
            this.hldBeanList = new ArrayList<NWZC150001CpouHldBean>(0);
        }
        this.hldBeanList.add(hldBean);
    }

    /**
     * <pre>
     * Get OrdQty_A1 value
     * </pre>
     * @return ordQty_A1
     */
    public BigDecimal getOrdQty_A1() {
        return ordQty_A1;
    }

    /**
     * <pre>
     * Set ordQty_A1 value
     * </pre>
     * @param ordQty_A1 setting ordQty_A1
     */
    public void setOrdQty_A1(BigDecimal ordQty_A1) {
        this.ordQty_A1 = ordQty_A1;
    }

    /**
     * <pre>
     * Get CPO_ORD_NUM for new
     * </pre>
     * @return cpoOrdNum_A1
     */
    public String getCpoOrdNum_A1() {
        return cpoOrdNum_A1;
    }

    /**
     * <pre>
     * Set CPO_ORD_NUM for new
     * </pre>
     * @param cpoOrdNum_A1 new CPO_ORD_NUM
     */
    public void setCpoOrdNum_A1(String cpoOrdNum_A1) {
        this.cpoOrdNum_A1 = cpoOrdNum_A1;
    }

    /**
     * <pre>
     * Get No validation Flag
     * </pre>
     * @return noValidationFlg
     */
    public String getNoValidationFlg() {
        return noValidationFlg;
    }

    /**
     * <pre>
     * Set No validation Flag
     * </pre>
     * @param noValidationFlg "Y" no validation check, "N" do validation check
     */
    public void setNoValidationFlg(String noValidationFlg) {
        this.noValidationFlg = noValidationFlg;
    }

    /**
     * <pre>
     * Get Only Validation Flag
     * </pre>
     * @return onlyValidationFlg
     */
    public String getOnlyValidationFlg() {
        return onlyValidationFlg;
    }

    /**
     * <pre>
     * Set onlyValidation Flag
     * </pre>
     * @param onlyValidationFlg "Y": Only validation, no save data. "N": validation and save Order
     */
    public void setOnlyValidationFlg(String onlyValidationFlg) {
        this.onlyValidationFlg = onlyValidationFlg;
    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Add End

    // 2018/05/11 QC#22139 Add Start
    /**
     * <pre>
     * Get Quote Print Comment Text
     * </pre>
     * @return quotePrintCmntTxt
     */
    public String getQuotePrintCmntTxt() {
        return quotePrintCmntTxt;
    }

    /**
     * <pre>
     * Set Quote Print Comment Text
     * </pre>
     * @param quotePrintCmntTxt
     */
    public void setQuotePrintCmntTxt(String quotePrintCmntTxt) {
        this.quotePrintCmntTxt = quotePrintCmntTxt;
    }

    /**
     * <pre>
     * Get Order Print Comment Text
     * </pre>
     * @return ordPrintCmntTxt
     */
    public String getOrdPrintCmntTxt() {
        return ordPrintCmntTxt;
    }

    /**
     * <pre>
     * Set Order Print Comment Text
     * </pre>
     * @param ordPrintCmntTxt
     */
    public void setOrdPrintCmntTxt(String ordPrintCmntTxt) {
        this.ordPrintCmntTxt = ordPrintCmntTxt;
    }

    /**
     * <pre>
     * Get Shipping Comment Print Code
     * </pre>
     * @return shpgCmntPrintCd
     */
    public String getshpgCmntPrintCd() {
        return shpgCmntPrintCd;
    }

    /**
     * <pre>
     * Set Shipping Comment Print Code
     * </pre>
     * @param shpgCmntPrintCd
     */
    public void setshpgCmntPrintCd(String shpgCmntPrintCd) {
        this.shpgCmntPrintCd = shpgCmntPrintCd;
    }
    // 2018/05/11 QC#22139 Add End

    // 2018/06/22 QC#20154 Add Start
    public void setShipToChgToWmsSendList(List<BigDecimal> shipToChgToWmsSendList) {
        this.shipToChgToWmsSendList = shipToChgToWmsSendList;
    }

    public List<BigDecimal> getShipToChgToWmsSendList() {
        return shipToChgToWmsSendList;
    }
    // 2018/06/22 QC#20154 Add End

    // 2018/08/02 S21_NA#25404 Add Start
    /** @return rtnDtlChngRsnTpCd*/
    public String getRtnDtlChngRsnTpCd() {
        return rtnDtlChngRsnTpCd;
    }

    /** @param rtnDtlChngRsnTpCd*/
    public void setRtnDtlChngRsnTpCd(String rtnDtlChngRsnTpCd) {
        this.rtnDtlChngRsnTpCd = rtnDtlChngRsnTpCd;
    }

    /** @return rtnDtlbizProcCmntTxt */
    public String getRtnDtlbizProcCmntTxt() {
        return rtnDtlbizProcCmntTxt;
    }

    /** @param rtnDtlbizProcCmntTxt */
    public void setRtnDtlbizProcCmntTxt(String rtnDtlbizProcCmntTxt) {
        this.rtnDtlbizProcCmntTxt = rtnDtlbizProcCmntTxt;
    }
    // 2018/08/02 S21_NA#25404 Add End
}
