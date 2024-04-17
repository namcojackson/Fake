package com.canon.cusa.s21.api.NWZ.NWZC153001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.parts.NWZC153001_ordRtrnDtlListPMsg;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 * 2016/04/28   Fujitsu         Y.Taoka         Update          S21_NA#7606
 * 2016/09/05   Fujitsu         W.Honda         Update          S21_NA#12435
 * 2016/12/15   Fujitsu         T.Yoshida       Update          S21_NA#15837
 * 2017/02/02   Fujitsu         R.Nakamura      Update          S21_NA#17349
 * 2017/10/16   Fujitsu         R.Nakamura      Update          S21_NA#21507
 * 2018/01/11   Fujitsu         A.Kosai         Update          S21_NA#22372
 * 2018/08/07   Fujitsu         S.Takami        Update          S21_NA#25404
 * 2018/08/14   Fujitsu         K.Ishizuka      Update          S21_NA#27718
 * 2018/11/15   Fujitsu         K.Ishizuka      Update          S21_NA#27299
 *</pre>
 */
public class NWZC153001DetailBean implements Serializable {

    /** ID */
    public static final long serialVersionUID  = 1L;

    // Data Definition
    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** ORIG_CPO_ORD_NUM */
    private String origCpoOrdNum;

    /** ORIG_CPO_DTL_LINE_NUM */
    private String origCpoDtlLineNum; // ADD S21_NA#7606

    /** ORIG_CPO_DTL_LINE_SUB_NUM */
    private String origCpoDtlLineSubNum; // ADD S21_NA#7606

    /** XX_RQST_TP_CD */
    private String xxRqstTpCd;

    /** CONFIG_TP_CD */
    private String configTpCd; // ADD S21_NA#15837

    /** DS_CPO_RTRN_LINE_NUM */
    private String dsCpoRtrnLineNum;

    /** DS_CPO_RTRN_LINE_SUB_NUM */
    private String dsCpoRtrnLineSubNum;

    /** CPO_ORD_TP_CD */
    private String cpoOrdTpCd;

    /** MDSE_CD */
    private String mdseCd;

    /** MDSE_NM */
    private String mdseNm;

    /** ORIG_MDSE_CD */
    private String origMdseCd;

    /** ORIG_MDSE_NM */
    private String origMdseNm;

    /** DROP_SHIP_FLG */
    private String dropShipFlg;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** SHIP_TO_LOC_NM */
    private String shipToLocNm;

    /** SHIP_TO_ADDL_LOC_NM */
    private String shipToAddlLocNm;

    /** SHIP_TO_FIRST_LINE_ADDR */
    private String shipToFirstLineAddr;

    /** SHIP_TO_SCD_LINE_ADDR */
    private String shipToScdLineAddr;

    /** SHIP_TO_THIRD_LINE_ADDR */
    private String shipToThirdLineAddr;

    /** SHIP_TO_FRTH_LINE_ADDR */
    private String shipToFrthLineAddr;

    /** SHIP_TO_CTY_ADDR */
    private String shipToCtyAddr;

    /** SHIP_TO_ST_CD */
    private String shipToStCd;

    /** SHIP_TO_PROV_NM */
    private String shipToProvNm;

    /** SHIP_TO_POST_CD */
    private String shipToPostCd;

    /** SHIP_TO_CTRY_CD */
    private String shipToCtryCd;

    /** SHIP_TO_CNTY_NM */
    private String shipToCntyNm;

    /** SHIP_TO_FIRST_REF_CMNT_TXT */
    private String shipToFirstRefCmntTxt;

    /** SHIP_TO_SCD_REF_CMNT_TXT */
    private String shipToScdRefCmntTxt;

    /** ORD_QTY */
    private BigDecimal ordQty;

    /** ORD_CUST_UOM_QTY */
    private BigDecimal ordCustUomQty;

    /** INVTY_LOC_CD */
    private String invtyLocCd;

    /** ORIG_INV_NUM */
    private String origInvNum;

    /** CCY_CD */
    private String ccyCd;

    /** RQST_PICK_UP_DT */
    private String rqstPickUpDt;

    /** STK_STS_CD */
    private String stkStsCd;

    /** PMT_TERM_CASH_DISC_CD */
    private String pmtTermCashDiscCd;

    /** PMT_TERM_CD */
    private String pmtTermCd;

    /** CASH_DISC_TERM_CD */
    private String cashDiscTermCd;

    /** SLS_REP_OR_SLS_TEAM_TOC_CD */
    private String slsRepOrSlsTeamTocCd;

    /** CPO_DTL_CANC_FLG */
    private String cpoDtlCancFlg;

    /** CUST_MDSE_CD */
    private String custMdseCd;

    /** CUST_UOM_CD */
    private String custUomCd;

    /** MAN_PRC_FLG */
    private String manPrcFlg;

    /** SET_MDSE_CD */
    private String setMdseCd;

    /** TAX_FLG */
    private String taxFlg;

    /** PRC_TMG_CD */
    private String prcTmgCd;

    /** EXCH_RATE */
    private BigDecimal exchRate;

    /** MACH_CONFIG_NUM */
    private String machConfigNum;

    /** DS_ORD_POSN_NUM */
    private String dsOrdPosnNum;

    /** SVC_CONFIG_MSTR_PK */
    private BigDecimal svcConfigMstrPk;

    /** UNIT_NET_WT */
    private BigDecimal unitNetWt;

    /** XX_TOT_BASE_AMT */
    private BigDecimal xxTotBaseAmt;

    /** XX_TOT_DISC_AMT */
    private BigDecimal xxTotDiscAmt;

    /** XX_TOT_SPCL_PRC_AMT */
    private BigDecimal xxTotSpclPrcAmt;

    /** XX_TOT_NET_DISC_AMT */
    private BigDecimal xxTotNetDiscAmt;

    /** XX_TOT_NET_PRC_AMT */
    private BigDecimal xxTotNetPrcAmt;

    /** XX_TOT_FRT_AMT */
    private BigDecimal xxTotFrtAmt;

    /** XX_TOT_SPCL_CHRG_AMT */
    private BigDecimal xxTotSpclChrgAmt;

    /** XX_TOT_TAX_AMT */
    private BigDecimal xxTotTaxAmt;

    /** XX_SLS_AMT */
    private BigDecimal xxSlsAmt;

    /** XX_TOT_AMT */
    private BigDecimal xxTotAmt;

    /** DS_CONTR_NUM */
    private String dsContrNum;

    /** DS_CONTR_SQ_NUM */
    private String dsContrSqNum;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrPk;

    /** SVC_MACH_MSTR_PK_BK */
    private BigDecimal svcMachMstrPk_BK; // 2016/09/05 S21_NA#12435 Add

    /** DS_CPO_CONFIG_PK */
    private BigDecimal dsCpoConfigPk;

    /** DS_CPO_LINE_NUM */
    private BigDecimal dsCpoLineNum;

    /** DS_CPO_LINE_SUB_NUM */
    private BigDecimal dsCpoLineSubNum;

    /** DS_ORD_LINE_CATG_CD */
    private String dsOrdLineCatgCd;

    /** ORD_LINE_SRC_CD */
    private String ordLineSrcCd;

    /** RTRN_LINE_STS_CD */
    private String rtrnLineStsCd;

    /** OPEN_FLG */
    private String openFlg;

    /** RTL_WH_CD */
    private String rtlWhCd;

    /** RTL_SWH_CD */
    private String rtlSwhCd;

    /** SER_NUM */
    private String serNum;

    /** SER_NUM_BK */
    private String serNum_BK;

    /** FL_PRC_LIST_CD */
    private String flPrcListCd;

    /** DEAL_PRC_LIST_PRC_AMT */
    private BigDecimal dealPrcListPrcAmt;

    /** FUNC_PRC_LIST_PRC_AMT */
    private BigDecimal funcPrcListPrcAmt;

    /** REF_CPO_DTL_LINE_NUM */
    private String refCpoDtlLineNum;

    /** REF_CPO_DTL_LINE_SUB_NUM */
    private String refCpoDtlLineSubNum;

    /** DPLY_LINE_REF_NUM */
    private String dplyLineRefNum;

    /** CR_REBIL_CD */
    private String crRebilCd;

    /** PRC_BASE_DT */
    private String prcBaseDt;

    /** PRC_CATG_CD */
    private String prcCatgCd;

    /** CSMP_CONTR_NUM */
    private String csmpContrNum;

    /** DLR_REF_NUM */
    private String dlrRefNum;

    /** CSMP_PRC_LIST_CD */
    private String csmpPrcListCd;

    /** BLLG_ATTRB_CUST_ACCT_CD */
    private String bllgAttrbCustAcctCd;

    /** FIRST_BLLG_ATTRB_NM */
    private String firstBllgAttrbNm;

    /** FIRST_BLLG_ATTRB_VAL_TXT */
    private String firstBllgAttrbValTxt;

    /** SCD_BLLG_ATTRB_NM */
    private String scdBllgAttrbNm;

    /** SCD_BLLG_ATTRB_VAL_TXT */
    private String scdBllgAttrbValTxt;

    /** THIRD_BLLG_ATTRB_NM */
    private String thirdBllgAttrbNm;

    /** THIRD_BLLG_ATTRB_VAL_TXT */
    private String thirdBllgAttrbValTxt;

    /** FRTH_BLLG_ATTRB_NM */
    private String frthBllgAttrbNm;

    /** FRTH_BLLG_ATTRB_VAL_TXT */
    private String frthBllgAttrbValTxt;

    /** FIFTH_BLLG_ATTRB_NM */
    private String fifthBllgAttrbNm;

    /** FIFTH_BLLG_ATTRB_VAL_TXT */
    private String fifthBllgAttrbValTxt;

    /** SIXTH_BLLG_ATTRB_NM */
    private String sixthBllgAttrbNm;

    /** SIXTH_BLLG_ATTRB_VAL_TXT */
    private String sixthBllgAttrbValTxt;

    /** ORD_SRC_REF_LINE_NUM */
    private String ordSrcRefLineNum;

    /** ORD_SRC_REF_LINE_SUB_NUM */
    private String ordSrcRefLineSubNum;

    /** BASE_CMPT_FLG */
    private String baseCmptFlg;

    /** TRX_CD */
    private String trxCd;

    /** TRX_RSN_CD */
    private String trxRsnCd;

    /** FUNC_SVC_REV_TRNSF_AMT */
    private BigDecimal funcSvcRevTrnsfAmt;

    /** DEAL_SVC_REV_TRNSF_AMT */
    private BigDecimal dealSvcRevTrnsfAmt;

    /** FUNC_SVC_COST_TRNSF_AMT */
    private BigDecimal funcSvcCostTrnsfAmt;

    /** DEAL_SVC_COST_TRNSF_AMT */
    private BigDecimal dealSvcCostTrnsfAmt;

    /** FUNC_MAN_FL_ADJ_AMT */
    private BigDecimal funcManFlAdjAmt;

    /** DEAL_MAN_FL_ADJ_AMT */
    private BigDecimal dealManFlAdjAmt;

    /** FUNC_MAN_REP_REV_ADJ_AMT */
    private BigDecimal funcManRepRevAdjAmt;

    /** DEAL_MAN_REP_REV_ADJ_AMT */
    private BigDecimal dealManRepRevAdjAmt;

    /** ORD_UPLD_REF_NUM */
    private String ordUpldRefNum;

    /** HDD_RMV_CD */
    private String hddRmvCd;

    /** RTRN_RSN_CD */
    private String rtrnRsnCd;

    /** THIRD_PTY_DSP_TP_CD */
    private String thirdPtyDspTpCd;

    /** RTRN_QTY */
    private BigDecimal rtrnQty;

    /** BIZ_PROC_LOG_PK */
    private BigDecimal bizProcLogPk;

    /** EVENT_ID */
    private String eventId;

    /** XX_VAL_UPD_FLG */
    private String xxValUpdFlg;

    /** DOC_ID */
    private String docId;

    /** ENT_DEAL_GRS_UNIT_PRC_AMT **/
    private BigDecimal entDealGrsUnitPrcAmt;

    /** ENT_DEAL_NET_UNIT_PRC_AMT **/
    private BigDecimal entDealNetUnitPrcAmt;

    /** ENT_CPO_DTL_DEAL_SLS_AMT **/
    private BigDecimal entCpoDtlDealSlsAmt;

    /** ENT_CPO_DTL_DEAL_NET_AMT **/
    private BigDecimal entCpoDtlDealNetAmt;

    /** ENT_FUNC_GRS_UNIT_PRC_AMT **/
    private BigDecimal entFuncGrsUnitPrcAmt; // 2015/12/17 S21_NA#2007 Add

    /** ENT_FUNC_NET_UNIT_PRC_AMT **/
    private BigDecimal entFuncNetUnitPrcAmt;

    /** ENT_CPO_DTL_FUNC_SLS_AMT **/
    private BigDecimal entCpoDtlFuncSlsAmt;

    /** ENT_CPO_DTL_FUNC_NET_AMT **/
    private BigDecimal entCpoDtlFuncNetAmt;

    /** CPO_DTL_DEAL_SLS_AMT **/
    private BigDecimal cpoDtlDealSlsAmt;

    /** CPO_DTL_DEAL_NET_AMT **/
    private BigDecimal cpoDtlDealNetAmt;

    /** CPO_DTL_FUNC_SLS_AMT **/
    private BigDecimal cpoDtlFuncSlsAmt;

    /** CPO_DTL_FUNC_NET_AMT **/
    private BigDecimal cpoDtlFuncNetAmt;

    /** DEAL_GRS_UNIT_PRC_AMT **/
    private BigDecimal dealGrsUnitPrcAmt;

    /** FUNC_GRS_UNIT_PRC_AMT **/
    private BigDecimal funcGrsUnitPrcAmt;

    // Add Start 2017/10/16 QC#21507
    /** CPO_SRC_TP_CD **/
    private String cpoSrcTpCd;

    /** ORD_SRC_REF_NUM **/
    private String ordSrcRefNum;
    // Add End 2017/10/16 QC#21507

    // 2018/01/11 S21_NA#22372 Add Start
    private BigDecimal funcUnitFlPrcAmt;
    // 2018/01/11 S21_NA#22372 Add End

    // 2018/08/07 S21_NA#25404 Add Start
    /** Change Reason Type Code */
    private String chngRsnTpCd;
    /** Biz Process Comment Text */
    private String bizProcCmntTxt;
    // 2018/08/07 S21_NA#25404 Add End
    
    // 2018/08/14 S21_NA#27718 Add Start
    /** Original Invoice Line Number */
    private String origInvLineNum;
    
    /** Original Invoice Line Sub Number */
    private String origInvLineSubNum;
    // 2018/08/14 S21_NA#27718 Add End
    
    // 2018/11/15 S21_NA#27299 Add Start
    /** Original Inventory Location Code */
    private String origInvtyLocCd; 
    
    /** Inventory Control Flag */
    private String invtyCtrlFlg;
    // 2018/11/15 S21_NA#27299 Add End

    /** discPrcList */
    private List<NWZC153001DiscountBean> discPrcList;

    <DWZC001001DiscountBean> NWZC153001DetailBean(NWZC153001_ordRtrnDtlListPMsg pMsg) {
        this.setXxRqstTpCd(pMsg.xxRqstTpCd.getValue());
        this.setConfigTpCd(pMsg.configTpCd.getValue());
        this.setDsCpoRtrnLineNum(pMsg.dsCpoRtrnLineNum.getValue());
        this.setDsCpoRtrnLineSubNum(pMsg.dsCpoRtrnLineSubNum.getValue());
        this.setCpoOrdTpCd(pMsg.cpoOrdTpCd.getValue());
        this.setMdseCd(pMsg.mdseCd.getValue());
        this.setOrigMdseCd(pMsg.origMdseCd.getValue());
        this.setDropShipFlg(pMsg.dropShipFlg.getValue());
        this.setShipToCustCd(pMsg.shipToCustCd.getValue());
        this.setShipToLocNm(pMsg.shipToLocNm.getValue());
        this.setShipToAddlLocNm(pMsg.shipToAddlLocNm.getValue());
        this.setShipToFirstLineAddr(pMsg.shipToFirstLineAddr.getValue());
        this.setShipToScdLineAddr(pMsg.shipToScdLineAddr.getValue());
        this.setShipToThirdLineAddr(pMsg.shipToThirdLineAddr.getValue());
        this.setShipToFrthLineAddr(pMsg.shipToFrthLineAddr.getValue());
        this.setShipToCtyAddr(pMsg.shipToCtyAddr.getValue());
        this.setShipToStCd(pMsg.shipToStCd.getValue());
        this.setShipToProvNm(pMsg.shipToProvNm.getValue());
        this.setShipToPostCd(pMsg.shipToPostCd.getValue());
        this.setShipToCtryCd(pMsg.shipToCtryCd.getValue());
        this.setShipToCntyNm(pMsg.shipToCntyNm.getValue());
        this.setShipToFirstRefCmntTxt(pMsg.shipToFirstRefCmntTxt.getValue());
        this.setShipToScdRefCmntTxt(pMsg.shipToScdRefCmntTxt.getValue());
        this.setOrdQty(pMsg.ordQty.getValue());
        this.setOrdCustUomQty(pMsg.ordCustUomQty.getValue());
        this.setInvtyLocCd(pMsg.invtyLocCd.getValue());
        this.setCcyCd(pMsg.ccyCd.getValue());
        this.setRqstPickUpDt(pMsg.rqstPickUpDt.getValue());
        this.setStkStsCd(pMsg.stkStsCd.getValue());
        this.setPmtTermCashDiscCd(pMsg.pmtTermCashDiscCd.getValue());
        this.setPmtTermCd(pMsg.pmtTermCd.getValue());
        this.setCashDiscTermCd(pMsg.cashDiscTermCd.getValue());
        this.setSlsRepOrSlsTeamTocCd(pMsg.slsRepOrSlsTeamTocCd.getValue());
        this.setCpoDtlCancFlg(pMsg.cpoDtlCancFlg.getValue());
        this.setCustMdseCd(pMsg.custMdseCd.getValue());
        this.setCustUomCd(pMsg.custUomCd.getValue());
        this.setManPrcFlg(pMsg.manPrcFlg.getValue());
        this.setTaxFlg(pMsg.taxFlg.getValue());
        this.setMachConfigNum(pMsg.machConfigNum.getValue());
        this.setDsOrdPosnNum(pMsg.dsOrdPosnNum.getValue());
        this.setSvcConfigMstrPk(pMsg.svcConfigMstrPk.getValue());
        this.setUnitNetWt(pMsg.unitNetWt.getValue());
        this.setXxTotBaseAmt(pMsg.xxTotBaseAmt.getValue());
        this.setXxTotDiscAmt(pMsg.xxTotDiscAmt.getValue());
        this.setXxTotSpclPrcAmt(pMsg.xxTotSpclPrcAmt.getValue());
        this.setXxTotNetDiscAmt(pMsg.xxTotNetDiscAmt.getValue());
        this.setXxTotNetPrcAmt(pMsg.xxTotNetPrcAmt.getValue());
        this.setXxTotFrtAmt(pMsg.xxTotFrtAmt.getValue());
        this.setXxTotSpclChrgAmt(pMsg.xxTotSpclChrgAmt.getValue());
        this.setXxTotTaxAmt(pMsg.xxTotTaxAmt.getValue());
        this.setXxSlsAmt(pMsg.xxSlsAmt.getValue());
        this.setXxTotAmt(pMsg.xxTotAmt.getValue());
        this.setDsContrNum(pMsg.dsContrNum.getValue());
        this.setDsContrSqNum(pMsg.dsContrSqNum.getValue());
        this.setSvcMachMstrPk(pMsg.svcMachMstrPk.getValue());
        this.setDsCpoConfigPk(pMsg.dsCpoConfigPk.getValue());
        this.setDsCpoLineNum(pMsg.dsCpoLineNum.getValue());
        this.setDsCpoLineSubNum(pMsg.dsCpoLineSubNum.getValue());
        this.setDsOrdLineCatgCd(pMsg.dsOrdLineCatgCd.getValue());
        this.setOrdLineSrcCd(pMsg.ordLineSrcCd.getValue());
        this.setRtlWhCd(pMsg.rtlWhCd.getValue());
        this.setRtlSwhCd(pMsg.rtlSwhCd.getValue());
        this.setSerNum(pMsg.serNum.getValue());
        this.setFlPrcListCd(pMsg.flPrcListCd.getValue());
        this.setDealPrcListPrcAmt(pMsg.dealPrcListPrcAmt.getValue());
        this.setFuncPrcListPrcAmt(pMsg.funcPrcListPrcAmt.getValue());
        this.setRefCpoDtlLineNum(pMsg.refCpoDtlLineNum.getValue());
        this.setRefCpoDtlLineSubNum(pMsg.refCpoDtlLineSubNum.getValue());
        this.setDplyLineRefNum(pMsg.dplyLineRefNum.getValue());
        this.setCrRebilCd(pMsg.crRebilCd.getValue());
        this.setPrcBaseDt(pMsg.prcBaseDt.getValue());
        this.setPrcCatgCd(pMsg.prcCatgCd.getValue());
        this.setCsmpContrNum(pMsg.csmpContrNum.getValue());
        this.setDlrRefNum(pMsg.dlrRefNum.getValue());
        this.setCsmpPrcListCd(pMsg.csmpPrcListCd.getValue());
        this.setBllgAttrbCustAcctCd(pMsg.bllgAttrbCustAcctCd.getValue());
        this.setFirstBllgAttrbNm(pMsg.firstBllgAttrbNm.getValue());
        this.setFirstBllgAttrbValTxt(pMsg.firstBllgAttrbValTxt.getValue());
        this.setScdBllgAttrbNm(pMsg.scdBllgAttrbNm.getValue());
        this.setScdBllgAttrbValTxt(pMsg.scdBllgAttrbValTxt.getValue());
        this.setThirdBllgAttrbNm(pMsg.thirdBllgAttrbNm.getValue());
        this.setThirdBllgAttrbValTxt(pMsg.thirdBllgAttrbValTxt.getValue());
        this.setFrthBllgAttrbNm(pMsg.frthBllgAttrbNm.getValue());
        this.setFrthBllgAttrbValTxt(pMsg.frthBllgAttrbValTxt.getValue());
        this.setFifthBllgAttrbNm(pMsg.fifthBllgAttrbNm.getValue());
        this.setFifthBllgAttrbValTxt(pMsg.fifthBllgAttrbValTxt.getValue());
        this.setSixthBllgAttrbNm(pMsg.sixthBllgAttrbNm.getValue());
        this.setSixthBllgAttrbValTxt(pMsg.sixthBllgAttrbValTxt.getValue());
        this.setOrdSrcRefLineNum(pMsg.ordSrcRefLineNum.getValue());
        this.setOrdSrcRefLineSubNum(pMsg.ordSrcRefLineSubNum.getValue());
        this.setBaseCmptFlg(pMsg.baseCmptFlg.getValue());
        this.setTrxCd(pMsg.trxCd.getValue());
        this.setTrxRsnCd(pMsg.trxRsnCd.getValue());
        this.setFuncSvcRevTrnsfAmt(pMsg.funcSvcRevTrnsfAmt.getValue());
        this.setDealSvcRevTrnsfAmt(pMsg.dealSvcRevTrnsfAmt.getValue());
        this.setFuncSvcCostTrnsfAmt(pMsg.funcSvcCostTrnsfAmt.getValue());
        this.setDealSvcCostTrnsfAmt(pMsg.dealSvcCostTrnsfAmt.getValue());
        this.setFuncManFlAdjAmt(pMsg.funcManFlAdjAmt.getValue());
        this.setDealManFlAdjAmt(pMsg.dealManFlAdjAmt.getValue());
        this.setFuncManRepRevAdjAmt(pMsg.funcManRepRevAdjAmt.getValue());
        this.setDealManRepRevAdjAmt(pMsg.dealManRepRevAdjAmt.getValue());
        this.setOrdUpldRefNum(pMsg.ordUpldRefNum.getValue());
        this.setHddRmvCd(pMsg.hddRmvCd.getValue());
        this.setRtrnRsnCd(pMsg.rtrnRsnCd.getValue());
        this.setThirdPtyDspTpCd(pMsg.thirdPtyDspTpCd.getValue());
        this.setRtrnQty(pMsg.rtrnQty.getValue());
        this.setBizProcLogPk(pMsg.bizProcLogPk.getValue());
        this.setEventId(pMsg.eventId.getValue());
        this.setXxValUpdFlg(pMsg.xxValUpdFlg.getValue());
        this.setDocId(pMsg.docId.getValue());
        this.setEntDealNetUnitPrcAmt(pMsg.entDealNetUnitPrcAmt.getValue());
        this.setOrigCpoOrdNum(pMsg.origCpoOrdNum.getValue()); // S21_NA#7606
        this.setOrigCpoDtlLineNum(pMsg.origCpoDtlLineNum.getValue()); // S21_NA#7606
        this.setOrigCpoDtlLineSubNum(pMsg.origCpoDtlLineSubNum.getValue()); // S21_NA#7606
        this.setDiscPrcList(new ArrayList<NWZC153001DiscountBean>());
        this.setOrigInvNum(pMsg.origInvNum.getValue()); // S21_NA#CreditRebill Req
        // Add Start 2017/10/16 QC#21507
        this.setCpoSrcTpCd(pMsg.cpoSrcTpCd.getValue());
        this.setOrdSrcRefNum(pMsg.ordSrcRefNum.getValue());
        // Add End 2017/10/16 QC#21507
        // 2018/01/11 S21_NA#22372 Add Start
        this.setFuncUnitFlPrcAmt(pMsg.funcUnitFlPrcAmt.getValue());
        // 2018/01/11 S21_NA#22372 Add End

        // 2018/08/07 S21_NA#25404 Add Start
        this.setChngRsnTpCd(pMsg.chngRsnTpCd.getValue());
        this.setBizProcCmntTxt(pMsg.bizProcCmntTxt.getValue());
        // 2018/08/07 S21_NA#25404 Add End
        // 2018/08/14 S21_NA#27718 Add Start
        this.setOrigInvLineNum(pMsg.origInvLineNum.getValue());
        this.setOrigInvLineSubNum(pMsg.origInvLineSubNum.getValue());
        // 2018/08/14 S21_NA#27718 Add End
        this.setOrigInvyLocCd(pMsg.origInvtyLocCd.getValue());
    }

    // Add Start 2017/02/02 QC#17349
    public NWZC153001DetailBean() {
        // TODO 自動生成されたコンストラクタスタブ
    }
    // Add End 2017/02/02 QC#17349

    /** @return glblCmpyCd */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** @return xxRqstTpCd */
    public String getXxRqstTpCd() {
        return xxRqstTpCd;
    }

    /** @return configTpCd */
    public String getConfigTpCd() { // ADD S21_NA#15837
        return configTpCd;
    }

    /** @return cpoOrdNum */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /** @return origCpoOrdNum */
    public String getOrigCpoOrdNum() {
        return origCpoOrdNum;
    }

    /** @return origCpoDtlLineNum */
    public String getOrigCpoDtlLineNum() { // S21_NA#7606
        return origCpoDtlLineNum;
    }

    /** @return origCpoDtlLineSubNum */
    public String getOrigCpoDtlLineSubNum() { // S21_NA#7606
        return origCpoDtlLineSubNum;
    }

    /** @return dsCpoRtrnLineNum */
    public String getDsCpoRtrnLineNum() {
        return dsCpoRtrnLineNum;
    }

    /** @return dsCpoRtrnLineSubNum */
    public String getDsCpoRtrnLineSubNum() {
        return dsCpoRtrnLineSubNum;
    }

    /** @return cpoOrdTpCd */
    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    /** @return mdseCd */
    public String getMdseCd() {
        return mdseCd;
    }

    /** @return mdseNm */
    public String getMdseNm() {
        return mdseNm;
    }

    /** @return origMdseCd */
    public String getOrigMdseCd() {
        return origMdseCd;
    }

    /** @return origMdseNm */
    public String getOrigMdseNm() {
        return origMdseNm;
    }

    /** @return dropShipFlg */
    public String getDropShipFlg() {
        return dropShipFlg;
    }

    /** @return shipToCustCd */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /** @return shipToLocNm */
    public String getShipToLocNm() {
        return shipToLocNm;
    }

    /** @return shipToAddlLocNm */
    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    /** @return shipToFirstLineAddr */
    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    /** @return shipToScdLineAddr */
    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    /** @return shipToThirdLineAddr */
    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    /** @return shipToFrthLineAddr */
    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    /** @return shipToCtyAddr */
    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    /** @return shipToStCd */
    public String getShipToStCd() {
        return shipToStCd;
    }

    /** @return shipToProvNm */
    public String getShipToProvNm() {
        return shipToProvNm;
    }

    /** @return shipToPostCd */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /** @return shipToCtryCd */
    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    /** @return shipToCntyNm */
    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    /** @return shipToFirstRefCmntTxt */
    public String getShipToFirstRefCmntTxt() {
        return shipToFirstRefCmntTxt;
    }

    /** @return shipToScdRefCmntTxt */
    public String getShipToScdRefCmntTxt() {
        return shipToScdRefCmntTxt;
    }

    /** @return ordQty */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /** @return ordCustUomQty */
    public BigDecimal getOrdCustUomQty() {
        return ordCustUomQty;
    }

    /** @return invtyLocCd */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /** @return origInvNum */
    public String getOrigInvNum() {
        return origInvNum;
    }

    /** @return ccyCd */
    public String getCcyCd() {
        return ccyCd;
    }

    /** @return rqstPickUpDt */
    public String getRqstPickUpDt() {
        return rqstPickUpDt;
    }

    /** @return stkStsCd */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /** @return pmtTermCashDiscCd */
    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    /** @return pmtTermCd */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /** @return cashDiscTermCd */
    public String getCashDiscTermCd() {
        return cashDiscTermCd;
    }

    /** @return slsRepOrSlsTeamTocCd */
    public String getSlsRepOrSlsTeamTocCd() {
        return slsRepOrSlsTeamTocCd;
    }

    /** @return cpoDtlCancFlg */
    public String getCpoDtlCancFlg() {
        return cpoDtlCancFlg;
    }

    /** @return custMdseCd */
    public String getCustMdseCd() {
        return custMdseCd;
    }

    /** @return custUomCd */
    public String getCustUomCd() {
        return custUomCd;
    }

    /** @return manPrcFlg */
    public String getManPrcFlg() {
        return manPrcFlg;
    }

    /** @return setMdseCd */
    public String getSetMdseCd() {
        return setMdseCd;
    }

    /** @return taxFlg */
    public String getTaxFlg() {
        return taxFlg;
    }

    /** @return prcTmgCd */
    public String getPrcTmgCd() {
        return prcTmgCd;
    }

    /** @return exchRate */
    public BigDecimal getExchRate() {
        return exchRate;
    }

    /** @return machConfigNum */
    public String getMachConfigNum() {
        return machConfigNum;
    }

    /** @return dsOrdPosnNum */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /** @return svcConfigMstrPk */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /** @return unitNetWt */
    public BigDecimal getUnitNetWt() {
        return unitNetWt;
    }

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

    /** @return dsContrNum */
    public String getDsContrNum() {
        return dsContrNum;
    }

    /** @return dsContrSqNum */
    public String getDsContrSqNum() {
        return dsContrSqNum;
    }

    /** @return svcMachMstrPk */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /** @return svcMachMstrPk_BK */
    public BigDecimal getSvcMachMstrPk_BK() { // 2016/09/05 S21_NA#12435 Add
        return svcMachMstrPk_BK;
    }

    /** @return dsCpoConfigPk */
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    /** @return dsCpoLineNum */
    public BigDecimal getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    /** @return dsCpoLineSubNum */
    public BigDecimal getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    /** @return dsOrdLineCatgCd */
    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }

    /** @return ordLineSrcCd */
    public String getOrdLineSrcCd() {
        return ordLineSrcCd;
    }

    /** @return rtrnLineStsCd */
    public String getRtrnLineStsCd() {
        return rtrnLineStsCd;
    }

    /** @return openFlg */
    public String getOpenFlg() {
        return openFlg;
    }

    /** @return rtlWhCd */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /** @return rtlSwhCd */
    public String getRtlSwhCd() {
        return rtlSwhCd;
    }

    /** @return serNum */
    public String getSerNum() {
        return serNum;
    }

    /** @return serNum_BK */
    public String getSerNum_BK() {
        return serNum_BK;
    }

    /** @return flPrcListCd */
    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    /** @return dealPrcListPrcAmt */
    public BigDecimal getDealPrcListPrcAmt() {
        return dealPrcListPrcAmt;
    }

    /** @return funcPrcListPrcAmt */
    public BigDecimal getFuncPrcListPrcAmt() {
        return funcPrcListPrcAmt;
    }

    /** @return refCpoDtlLineNum */
    public String getRefCpoDtlLineNum() {
        return refCpoDtlLineNum;
    }

    /** @return refCpoDtlLineSubNum */
    public String getRefCpoDtlLineSubNum() {
        return refCpoDtlLineSubNum;
    }

    /** @return dplyLineRefNum */
    public String getDplyLineRefNum() {
        return dplyLineRefNum;
    }

    /** @return crRebilCd */
    public String getCrRebilCd() {
        return crRebilCd;
    }

    /** @return prcBaseDt */
    public String getPrcBaseDt() {
        return prcBaseDt;
    }

    /** @return prcCatgCd */
    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    /** @return csmpContrNum */
    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    /** @return dlrRefNum */
    public String getDlrRefNum() {
        return dlrRefNum;
    }

    /** @return csmpPrcListCd */
    public String getCsmpPrcListCd() {
        return csmpPrcListCd;
    }

    /** @return bllgAttrbCustAcctCd */
    public String getBllgAttrbCustAcctCd() {
        return bllgAttrbCustAcctCd;
    }

    /** @return firstBllgAttrbNm */
    public String getFirstBllgAttrbNm() {
        return firstBllgAttrbNm;
    }

    /** @return firstBllgAttrbValTxt */
    public String getFirstBllgAttrbValTxt() {
        return firstBllgAttrbValTxt;
    }

    /** @return scdBllgAttrbNm */
    public String getScdBllgAttrbNm() {
        return scdBllgAttrbNm;
    }

    /** @return scdBllgAttrbValTxt */
    public String getScdBllgAttrbValTxt() {
        return scdBllgAttrbValTxt;
    }

    /** @return thirdBllgAttrbNm */
    public String getThirdBllgAttrbNm() {
        return thirdBllgAttrbNm;
    }

    /** @return thirdBllgAttrbValTxt */
    public String getThirdBllgAttrbValTxt() {
        return thirdBllgAttrbValTxt;
    }

    /** @return frthBllgAttrbNm */
    public String getFrthBllgAttrbNm() {
        return frthBllgAttrbNm;
    }

    /** @return frthBllgAttrbValTxt */
    public String getFrthBllgAttrbValTxt() {
        return frthBllgAttrbValTxt;
    }

    /** @return fifthBllgAttrbNm */
    public String getFifthBllgAttrbNm() {
        return fifthBllgAttrbNm;
    }

    /** @return fifthBllgAttrbValTxt */
    public String getFifthBllgAttrbValTxt() {
        return fifthBllgAttrbValTxt;
    }

    /** @return sixthBllgAttrbNm */
    public String getSixthBllgAttrbNm() {
        return sixthBllgAttrbNm;
    }

    /** @return sixthBllgAttrbValTxt */
    public String getSixthBllgAttrbValTxt() {
        return sixthBllgAttrbValTxt;
    }

    /** @return ordSrcRefLineNum */
    public String getOrdSrcRefLineNum() {
        return ordSrcRefLineNum;
    }

    /** @return ordSrcRefLineSubNum */
    public String getOrdSrcRefLineSubNum() {
        return ordSrcRefLineSubNum;
    }

    /** @return baseCmptFlg */
    public String getBaseCmptFlg() {
        return baseCmptFlg;
    }

    /** @return trxCd */
    public String getTrxCd() {
        return trxCd;
    }

    /** @return trxRsnCd */
    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    /** @return funcSvcRevTrnsfAmt */
    public BigDecimal getFuncSvcRevTrnsfAmt() {
        return funcSvcRevTrnsfAmt;
    }

    /** @return dealSvcRevTrnsfAmt */
    public BigDecimal getDealSvcRevTrnsfAmt() {
        return dealSvcRevTrnsfAmt;
    }

    /** @return funcSvcCostTrnsfAmt */
    public BigDecimal getFuncSvcCostTrnsfAmt() {
        return funcSvcCostTrnsfAmt;
    }

    /** @return dealSvcCostTrnsfAmt */
    public BigDecimal getDealSvcCostTrnsfAmt() {
        return dealSvcCostTrnsfAmt;
    }

    /** @return funcManFlAdjAmt */
    public BigDecimal getFuncManFlAdjAmt() {
        return funcManFlAdjAmt;
    }

    /** @return dealManFlAdjAmt */
    public BigDecimal getDealManFlAdjAmt() {
        return dealManFlAdjAmt;
    }

    /** @return funcManRepRevAdjAmt */
    public BigDecimal getFuncManRepRevAdjAmt() {
        return funcManRepRevAdjAmt;
    }

    /** @return dealManRepRevAdjAmt */
    public BigDecimal getDealManRepRevAdjAmt() {
        return dealManRepRevAdjAmt;
    }

    /** @return ordUpldRefNum */
    public String getOrdUpldRefNum() {
        return ordUpldRefNum;
    }

    /** @return hddRmvCd */
    public String getHddRmvCd() {
        return hddRmvCd;
    }

    /** @return rtrnRsnCd */
    public String getRtrnRsnCd() {
        return rtrnRsnCd;
    }

    /** @return thirdPtyDspTpCd */
    public String getThirdPtyDspTpCd() {
        return thirdPtyDspTpCd;
    }

    /** @return rtrnQty */
    public BigDecimal getRtrnQty() {
        return rtrnQty;
    }

    /** @return bizProcLogPk */
    public BigDecimal getBizProcLogPk() {
        return bizProcLogPk;
    }

    /** @return eventId */
    public String getEventId() {
        return eventId;
    }

    /** @return xxValUpdFlg */
    public String getXxValUpdFlg() {
        return xxValUpdFlg;
    }

    /** @return docId */
    public String getDocId() {
        return docId;
    }

    /** @return entDealGrsUnitPrcAmt */
    public BigDecimal getEntDealGrsUnitPrcAmt() {
        return entDealGrsUnitPrcAmt;
    }

    /** @return entCpoDtlDealSlsAmt **/
    public BigDecimal getEntCpoDtlDealSlsAmt() {
        return entCpoDtlDealSlsAmt;
    }

    /** @return entCpoDtlDealNetAmt **/
    public BigDecimal getEntCpoDtlDealNetAmt() {
        return entCpoDtlDealNetAmt;
    }

    /** @return entDealNetUnitPrcAmt **/
    public BigDecimal getEntDealNetUnitPrcAmt() {
        return entDealNetUnitPrcAmt;
    }

    /** @return entFuncGrsUnitPrcAmt **/
    public BigDecimal getEntFuncGrsUnitPrcAmt() {
        return entFuncGrsUnitPrcAmt;
    }

    /** @return entFuncNetUnitPrcAmt **/
    public BigDecimal getEntFuncNetUnitPrcAmt() {
        return entFuncNetUnitPrcAmt;
    }

    /** @return entCpoDtlFuncSlsAmt **/
    public BigDecimal getEntCpoDtlFuncSlsAmt() {
        return entCpoDtlFuncSlsAmt;
    }

    /** @return entCpoDtlFuncNetAmt **/
    public BigDecimal getEntCpoDtlFuncNetAmt() {
        return entCpoDtlFuncNetAmt;
    }

    /** @return cpoDtlDealSlsAmt **/
    public BigDecimal getCpoDtlDealSlsAmt() {
        return cpoDtlDealSlsAmt;
    }

    /** @return cpoDtlDealNetAmt **/
    public BigDecimal getCpoDtlDealNetAmt() {
        return cpoDtlDealNetAmt;
    }

    /** @return cpoDtlFuncSlsAmt **/
    public BigDecimal getCpoDtlFuncSlsAmt() {
        return cpoDtlFuncSlsAmt;
    }

    /** @return cpoDtlFuncNetAmt **/
    public BigDecimal getCpoDtlFuncNetAmt() {
        return cpoDtlFuncNetAmt;
    }

    /** @return dealGrsUnitPrcAmt **/
    public BigDecimal getDealGrsUnitPrcAmt() {
        return dealGrsUnitPrcAmt;
    }

    /** @return funcGrsUnitPrcAmt **/
    public BigDecimal getFuncGrsUnitPrcAmt() {
        return funcGrsUnitPrcAmt;
    }

    // Add Start 2017/10/16 QC#21507
    /** @return cpoSrcTpCd **/
    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    /** @return ordSrcRefNum **/
    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }
    // Add Start 2017/10/16 QC#21507

    // 2018/01/11 S21_NA#22372 Add Start
    /** @return funcUnitFlPrcAmt **/
    public BigDecimal getFuncUnitFlPrcAmt() {
        return funcUnitFlPrcAmt;
    }
    // 2018/01/11 S21_NA#22372 Add End

    /** @return discPrcList */
    public List<NWZC153001DiscountBean> getDiscPrcList() {
        return discPrcList;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    };

    public void setXxRqstTpCd(String xxRqstTpCd) {
        this.xxRqstTpCd = xxRqstTpCd;
    };

    public void setConfigTpCd(String configTpCd) { // ADD S21_NA#15837
        this.configTpCd = configTpCd;
    };

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    };

    public void setOrigCpoOrdNum(String origCpoOrdNum) {
        this.origCpoOrdNum = origCpoOrdNum;
    };

    public void setOrigCpoDtlLineNum(String origCpoDtlLineNum) { // ADD S21_NA#7606
        this.origCpoDtlLineNum = origCpoDtlLineNum;
    };

    public void setOrigCpoDtlLineSubNum(String origCpoDtlLineSubNum) { // ADD S21_NA#7606
        this.origCpoDtlLineSubNum = origCpoDtlLineSubNum;
    };

    public void setDsCpoRtrnLineNum(String dsCpoRtrnLineNum) {
        this.dsCpoRtrnLineNum = dsCpoRtrnLineNum;
    };

    public void setDsCpoRtrnLineSubNum(String dsCpoRtrnLineSubNum) {
        this.dsCpoRtrnLineSubNum = dsCpoRtrnLineSubNum;
    };

    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    };

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    };

    public void setMdseNm(String mdseNm) {
        this.mdseNm = mdseNm;
    };

    public void setOrigMdseCd(String origMdseCd) {
        this.origMdseCd = origMdseCd;
    };

    public void setOrigMdseNm(String origMdseNm) {
        this.origMdseNm = origMdseNm;
    };

    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    };

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    };

    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    };

    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    };

    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    };

    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    };

    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    };

    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    };

    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    };

    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    };

    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    };

    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    };

    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    };

    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    };

    public void setShipToFirstRefCmntTxt(String shipToFirstRefCmntTxt) {
        this.shipToFirstRefCmntTxt = shipToFirstRefCmntTxt;
    };

    public void setShipToScdRefCmntTxt(String shipToScdRefCmntTxt) {
        this.shipToScdRefCmntTxt = shipToScdRefCmntTxt;
    };

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    };

    public void setOrdCustUomQty(BigDecimal ordCustUomQty) {
        this.ordCustUomQty = ordCustUomQty;
    };

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    };

    public void setOrigInvNum(String origInvNum) {
        this.origInvNum = origInvNum;
    };

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    };

    public void setRqstPickUpDt(String rqstPickUpDt) {
        this.rqstPickUpDt = rqstPickUpDt;
    };

    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    };

    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    };

    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    };

    public void setCashDiscTermCd(String cashDiscTermCd) {
        this.cashDiscTermCd = cashDiscTermCd;
    };

    public void setSlsRepOrSlsTeamTocCd(String slsRepOrSlsTeamTocCd) {
        this.slsRepOrSlsTeamTocCd = slsRepOrSlsTeamTocCd;
    };

    public void setCpoDtlCancFlg(String cpoDtlCancFlg) {
        this.cpoDtlCancFlg = cpoDtlCancFlg;
    };

    public void setCustMdseCd(String custMdseCd) {
        this.custMdseCd = custMdseCd;
    };

    public void setCustUomCd(String custUomCd) {
        this.custUomCd = custUomCd;
    };

    public void setManPrcFlg(String manPrcFlg) {
        this.manPrcFlg = manPrcFlg;
    };

    public void setSetMdseCd(String setMdseCd) {
        this.setMdseCd = setMdseCd;
    };

    public void setTaxFlg(String taxFlg) {
        this.taxFlg = taxFlg;
    };

    public void setPrcTmgCd(String prcTmgCd) {
        this.prcTmgCd = prcTmgCd;
    };

    public void setExchRate(BigDecimal exchRate) {
        this.exchRate = exchRate;
    };

    public void setMachConfigNum(String machConfigNum) {
        this.machConfigNum = machConfigNum;
    };

    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    };

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    };

    public void setUnitNetWt(BigDecimal unitNetWt) {
        this.unitNetWt = unitNetWt;
    };

    public void setXxTotBaseAmt(BigDecimal xxTotBaseAmt) {
        this.xxTotBaseAmt = xxTotBaseAmt;
    };

    public void setXxTotDiscAmt(BigDecimal xxTotDiscAmt) {
        this.xxTotDiscAmt = xxTotDiscAmt;
    };

    public void setXxTotSpclPrcAmt(BigDecimal xxTotSpclPrcAmt) {
        this.xxTotSpclPrcAmt = xxTotSpclPrcAmt;
    };

    public void setXxTotNetDiscAmt(BigDecimal xxTotNetDiscAmt) {
        this.xxTotNetDiscAmt = xxTotNetDiscAmt;
    };

    public void setXxTotNetPrcAmt(BigDecimal xxTotNetPrcAmt) {
        this.xxTotNetPrcAmt = xxTotNetPrcAmt;
    };

    public void setXxTotFrtAmt(BigDecimal xxTotFrtAmt) {
        this.xxTotFrtAmt = xxTotFrtAmt;
    };

    public void setXxTotSpclChrgAmt(BigDecimal xxTotSpclChrgAmt) {
        this.xxTotSpclChrgAmt = xxTotSpclChrgAmt;
    };

    public void setXxTotTaxAmt(BigDecimal xxTotTaxAmt) {
        this.xxTotTaxAmt = xxTotTaxAmt;
    };

    public void setXxSlsAmt(BigDecimal xxSlsAmt) {
        this.xxSlsAmt = xxSlsAmt;
    };

    public void setXxTotAmt(BigDecimal xxTotAmt) {
        this.xxTotAmt = xxTotAmt;
    };

    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    };

    public void setDsContrSqNum(String dsContrSqNum) {
        this.dsContrSqNum = dsContrSqNum;
    };

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    };

    public void setSvcMachMstrPk_BK(BigDecimal svcMachMstrPk_BK) { // 2016/09/05 S21_NA#12435 Add
        this.svcMachMstrPk_BK = svcMachMstrPk_BK;
    };

    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    };

    public void setDsCpoLineNum(BigDecimal dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    };

    public void setDsCpoLineSubNum(BigDecimal dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    };

    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    };

    public void setOrdLineSrcCd(String ordLineSrcCd) {
        this.ordLineSrcCd = ordLineSrcCd;
    };

    public void setRtrnLineStsCd(String rtrnLineStsCd) {
        this.rtrnLineStsCd = rtrnLineStsCd;
    };

    public void setOpenFlg(String openFlg) {
        this.openFlg = openFlg;
    };

    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    };

    public void setRtlSwhCd(String rtlSwhCd) {
        this.rtlSwhCd = rtlSwhCd;
    };

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    };

    public void setSerNum_BK(String serNum_BK) {
        this.serNum_BK = serNum_BK;
    };

    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    };

    public void setDealPrcListPrcAmt(BigDecimal dealPrcListPrcAmt) {
        this.dealPrcListPrcAmt = dealPrcListPrcAmt;
    };

    public void setFuncPrcListPrcAmt(BigDecimal funcPrcListPrcAmt) {
        this.funcPrcListPrcAmt = funcPrcListPrcAmt;
    };

    public void setRefCpoDtlLineNum(String refCpoDtlLineNum) {
        this.refCpoDtlLineNum = refCpoDtlLineNum;
    };

    public void setRefCpoDtlLineSubNum(String refCpoDtlLineSubNum) {
        this.refCpoDtlLineSubNum = refCpoDtlLineSubNum;
    };

    public void setDplyLineRefNum(String dplyLineRefNum) {
        this.dplyLineRefNum = dplyLineRefNum;
    };

    public void setCrRebilCd(String crRebilCd) {
        this.crRebilCd = crRebilCd;
    };

    public void setPrcBaseDt(String prcBaseDt) {
        this.prcBaseDt = prcBaseDt;
    };

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    };

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    };

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    };

    public void setCsmpPrcListCd(String csmpPrcListCd) {
        this.csmpPrcListCd = csmpPrcListCd;
    };

    public void setBllgAttrbCustAcctCd(String bllgAttrbCustAcctCd) {
        this.bllgAttrbCustAcctCd = bllgAttrbCustAcctCd;
    };

    public void setFirstBllgAttrbNm(String firstBllgAttrbNm) {
        this.firstBllgAttrbNm = firstBllgAttrbNm;
    };

    public void setFirstBllgAttrbValTxt(String firstBllgAttrbValTxt) {
        this.firstBllgAttrbValTxt = firstBllgAttrbValTxt;
    };

    public void setScdBllgAttrbNm(String scdBllgAttrbNm) {
        this.scdBllgAttrbNm = scdBllgAttrbNm;
    };

    public void setScdBllgAttrbValTxt(String scdBllgAttrbValTxt) {
        this.scdBllgAttrbValTxt = scdBllgAttrbValTxt;
    };

    public void setThirdBllgAttrbNm(String thirdBllgAttrbNm) {
        this.thirdBllgAttrbNm = thirdBllgAttrbNm;
    };

    public void setThirdBllgAttrbValTxt(String thirdBllgAttrbValTxt) {
        this.thirdBllgAttrbValTxt = thirdBllgAttrbValTxt;
    };

    public void setFrthBllgAttrbNm(String frthBllgAttrbNm) {
        this.frthBllgAttrbNm = frthBllgAttrbNm;
    };

    public void setFrthBllgAttrbValTxt(String frthBllgAttrbValTxt) {
        this.frthBllgAttrbValTxt = frthBllgAttrbValTxt;
    };

    public void setFifthBllgAttrbNm(String fifthBllgAttrbNm) {
        this.fifthBllgAttrbNm = fifthBllgAttrbNm;
    };

    public void setFifthBllgAttrbValTxt(String fifthBllgAttrbValTxt) {
        this.fifthBllgAttrbValTxt = fifthBllgAttrbValTxt;
    };

    public void setSixthBllgAttrbNm(String sixthBllgAttrbNm) {
        this.sixthBllgAttrbNm = sixthBllgAttrbNm;
    };

    public void setSixthBllgAttrbValTxt(String sixthBllgAttrbValTxt) {
        this.sixthBllgAttrbValTxt = sixthBllgAttrbValTxt;
    };

    public void setOrdSrcRefLineNum(String ordSrcRefLineNum) {
        this.ordSrcRefLineNum = ordSrcRefLineNum;
    };

    public void setOrdSrcRefLineSubNum(String ordSrcRefLineSubNum) {
        this.ordSrcRefLineSubNum = ordSrcRefLineSubNum;
    };

    public void setBaseCmptFlg(String baseCmptFlg) {
        this.baseCmptFlg = baseCmptFlg;
    };

    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    };

    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    };

    public void setFuncSvcRevTrnsfAmt(BigDecimal funcSvcRevTrnsfAmt) {
        this.funcSvcRevTrnsfAmt = funcSvcRevTrnsfAmt;
    };

    public void setDealSvcRevTrnsfAmt(BigDecimal dealSvcRevTrnsfAmt) {
        this.dealSvcRevTrnsfAmt = dealSvcRevTrnsfAmt;
    };

    public void setFuncSvcCostTrnsfAmt(BigDecimal funcSvcCostTrnsfAmt) {
        this.funcSvcCostTrnsfAmt = funcSvcCostTrnsfAmt;
    };

    public void setDealSvcCostTrnsfAmt(BigDecimal dealSvcCostTrnsfAmt) {
        this.dealSvcCostTrnsfAmt = dealSvcCostTrnsfAmt;
    };

    public void setFuncManFlAdjAmt(BigDecimal funcManFlAdjAmt) {
        this.funcManFlAdjAmt = funcManFlAdjAmt;
    };

    public void setDealManFlAdjAmt(BigDecimal dealManFlAdjAmt) {
        this.dealManFlAdjAmt = dealManFlAdjAmt;
    };

    public void setFuncManRepRevAdjAmt(BigDecimal funcManRepRevAdjAmt) {
        this.funcManRepRevAdjAmt = funcManRepRevAdjAmt;
    };

    public void setDealManRepRevAdjAmt(BigDecimal dealManRepRevAdjAmt) {
        this.dealManRepRevAdjAmt = dealManRepRevAdjAmt;
    };

    public void setOrdUpldRefNum(String ordUpldRefNum) {
        this.ordUpldRefNum = ordUpldRefNum;
    };

    public void setHddRmvCd(String hddRmvCd) {
        this.hddRmvCd = hddRmvCd;
    };

    public void setRtrnRsnCd(String rtrnRsnCd) {
        this.rtrnRsnCd = rtrnRsnCd;
    };

    public void setThirdPtyDspTpCd(String thirdPtyDspTpCd) {
        this.thirdPtyDspTpCd = thirdPtyDspTpCd;
    };

    public void setRtrnQty(BigDecimal rtrnQty) {
        this.rtrnQty = rtrnQty;
    };

    public void setBizProcLogPk(BigDecimal bizProcLogPk) {
        this.bizProcLogPk = bizProcLogPk;
    };

    public void setEventId(String eventId) {
        this.eventId = eventId;
    };

    public void setXxValUpdFlg(String xxValUpdFlg) {
        this.xxValUpdFlg = xxValUpdFlg;
    };

    public void setDocId(String docId) {
        this.docId = docId;
    };

    /** @param entDealGrsUnitPrcAmt */
    public void setEntDealGrsUnitPrcAmt(BigDecimal entDealGrsUnitPrcAmt) {
        this.entDealGrsUnitPrcAmt = entDealGrsUnitPrcAmt;
    }

    /** @param entDealNetUnitPrcAmt */
    public void setEntDealNetUnitPrcAmt(BigDecimal entDealNetUnitPrcAmt) {
        this.entDealNetUnitPrcAmt = entDealNetUnitPrcAmt;
    }

    /** @return entFuncGrsUnitPrcAmt **/
    public BigDecimal setEntFuncGrsUnitPrcAmt(BigDecimal entFuncGrsUnitPrcAmt) {
        return this.entFuncGrsUnitPrcAmt = entFuncGrsUnitPrcAmt;
    }

    /** @return entFuncNetUnitPrcAmt **/
    public BigDecimal setEntFuncNetUnitPrcAmt(BigDecimal entFuncNetUnitPrcAmt) {
        return this.entFuncNetUnitPrcAmt = entFuncNetUnitPrcAmt;
    }

    /** @param entCpoDtlDealSlsAmt **/
    public void setEntCpoDtlDealSlsAmt(BigDecimal entCpoDtlDealSlsAmt) {
        this.entCpoDtlDealSlsAmt = entCpoDtlDealSlsAmt;
    }

    /** @param entCpoDtlDealNetAmt **/
    public void setEntCpoDtlDealNetAmt(BigDecimal entCpoDtlDealNetAmt) {
        this.entCpoDtlDealNetAmt = entCpoDtlDealNetAmt;
    }

    /** @param entCpoDtlFuncSlsAmt **/
    public void setEntCpoDtlFuncSlsAmt(BigDecimal entCpoDtlFuncSlsAmt) {
        this.entCpoDtlFuncSlsAmt = entCpoDtlFuncSlsAmt;
    }

    /** @param entCpoDtlFuncNetAmt **/
    public void setEntCpoDtlFuncNetAmt(BigDecimal entCpoDtlFuncNetAmt) {
        this.entCpoDtlFuncNetAmt = entCpoDtlFuncNetAmt;
    }

    /** @param cpoDtlDealSlsAmt **/
    public void setCpoDtlDealSlsAmt(BigDecimal cpoDtlDealSlsAmt) {
        this.cpoDtlDealSlsAmt = cpoDtlDealSlsAmt;
    }

    /** @param cpoDtlDealNetAmt **/
    public void setCpoDtlDealNetAmt(BigDecimal cpoDtlDealNetAmt) {
        this.cpoDtlDealNetAmt = cpoDtlDealNetAmt;
    }

    /** @param cpoDtlFuncSlsAmt **/
    public void setCpoDtlFuncSlsAmt(BigDecimal cpoDtlFuncSlsAmt) {
        this.cpoDtlFuncSlsAmt = cpoDtlFuncSlsAmt;
    }

    /** @param cpoDtlFuncNetAmt **/
    public void setCpoDtlFuncNetAmt(BigDecimal cpoDtlFuncNetAmt) {
        this.cpoDtlFuncNetAmt = cpoDtlFuncNetAmt;
    }

    /** @param dealGrsUnitPrcAmt **/
    public void setDealGrsUnitPrcAmt(BigDecimal dealGrsUnitPrcAmt) {
        this.dealGrsUnitPrcAmt = dealGrsUnitPrcAmt;
    }

    /** @param funcGrsUnitPrcAmt **/
    public void setFuncGrsUnitPrcAmt(BigDecimal funcGrsUnitPrcAmt) {
        this.funcGrsUnitPrcAmt = funcGrsUnitPrcAmt;
    }

    // Add Start 2017/10/16 QC#21507
    /** @param cpoSrcTpCd **/
    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    }

    /** @param ordSrcRefNum **/
    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    }
    // Add End 2017/10/16 QC#21507

    // 2018/01/11 S21_NA#22372 Add Start
    public void setFuncUnitFlPrcAmt(BigDecimal funcUnitFlPrcAmt) {
        this.funcUnitFlPrcAmt = funcUnitFlPrcAmt;
    }
    // 2018/01/11 S21_NA#22372 Add End

    /** @param discPrcList */
    public void setDiscPrcList(List<NWZC153001DiscountBean> discPrcList) {
        this.discPrcList = discPrcList;
    }

    // 2018/08/07 S21_NA#25404 Add Start
    /** @return chngRsnTpCd */
    public String getChngRsnTpCd() {
        return chngRsnTpCd;
    }

    /** @param chngRsnTpCd*/
    public void setChngRsnTpCd(String chngRsnTpCd) {
        this.chngRsnTpCd = chngRsnTpCd;
    }

    /** @return bizProcCmntTxt */
    public String getBizProcCmntTxt() {
        return bizProcCmntTxt;
    }

    /** @param bizProcCmntTxt */
    public void setBizProcCmntTxt(String bizProcCmntTxt) {
        this.bizProcCmntTxt = bizProcCmntTxt;
    }
    // 2018/08/07 S21_NA#25404 Add End
    
    // 2018/08/14 S21_NA#27718 Add Start
    /** @return origInvLineNum */
    public String getOrigInvLineNum() {
        return origInvLineNum;
    }

    /** @param origInvLineNum*/
    public void setOrigInvLineNum(String origInvLineNum) {
        this.origInvLineNum = origInvLineNum;
    }

    /** @return origInvLineSubNum */
    public String getOrigInvLineSubNum() {
        return origInvLineSubNum;
    }

    /** @param origInvLineSubNum */
    public void setOrigInvLineSubNum(String origInvLineSubNum) {
        this.origInvLineSubNum = origInvLineSubNum;
    }
    // 2018/08/14 S21_NA#27718 Add End
    
    // 2018/11/15 S21_NA#27299 Add Start
    /** @return origInvtyLocCd */
    public String getOrigInvyLocCd() {
        return origInvtyLocCd;
    }

    /** @param origInvtyLocCd */
    public void setOrigInvyLocCd(String origInvtyLocCd) {
        this.origInvtyLocCd = origInvtyLocCd;
    }
    
    /** @return invtyCtrlFlg */
    public String getInvtyCtrlFlg() {
        return invtyCtrlFlg;
    }

    /** @param invtyCtrlFlg */
    public void setInvtyCtrlFlg(String invtyCtrlFlg) {
    // QC#56558 2020/04/14 Mod Start
    //    this.invtyCtrlFlg = origInvtyLocCd;
        this.invtyCtrlFlg = invtyCtrlFlg;
    // QC#56558 2020/04/14 Mod End
    }
    // 2018/11/15 S21_NA#27299 Add End
}
