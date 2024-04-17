//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20181115103514000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC153001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWZC153001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC153001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_RQST_TP_CD*/
	public final EZDPStringItem              xxRqstTpCd;

    /** XX_SCR_EDT_TP_CD*/
	public final EZDPStringItem              xxScrEdtTpCd;

    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** CPO_ORD_NUM_BK*/
	public final EZDPStringItem              cpoOrdNum_BK;

    /** CPO_ORD_TP_CD*/
	public final EZDPStringItem              cpoOrdTpCd;

    /** CUST_ISS_PO_NUM*/
	public final EZDPStringItem              custIssPoNum;

    /** CPO_SRC_TP_CD*/
	public final EZDPStringItem              cpoSrcTpCd;

    /** ORD_FUFL_LVL_CD*/
	public final EZDPStringItem              ordFuflLvlCd;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** SELL_TO_FIRST_REF_CMNT_TXT*/
	public final EZDPStringItem              sellToFirstRefCmntTxt;

    /** SELL_TO_SCD_REF_CMNT_TXT*/
	public final EZDPStringItem              sellToScdRefCmntTxt;

    /** ADMIN_PSN_CD*/
	public final EZDPStringItem              adminPsnCd;

    /** PAYER_CUST_CD*/
	public final EZDPStringItem              payerCustCd;

    /** ADD_RDD_DT*/
	public final EZDPDateItem              addRddDt;

    /** ADD_RSD_DT*/
	public final EZDPDateItem              addRsdDt;

    /** ADD_SHPG_SVC_LVL_CD*/
	public final EZDPStringItem              addShpgSvcLvlCd;

    /** ADD_FRT_CHRG_TO_CD*/
	public final EZDPStringItem              addFrtChrgToCd;

    /** ADD_FRT_CHRG_METH_CD*/
	public final EZDPStringItem              addFrtChrgMethCd;

    /** ADD_DROP_SHIP_FLG*/
	public final EZDPStringItem              addDropShipFlg;

    /** ADD_SHIP_TO_CUST_CD*/
	public final EZDPStringItem              addShipToCustCd;

    /** ADD_SHIP_TO_LOC_NM*/
	public final EZDPStringItem              addShipToLocNm;

    /** ADD_SHIP_TO_ADDL_LOC_NM*/
	public final EZDPStringItem              addShipToAddlLocNm;

    /** ADD_SHIP_TO_FIRST_LINE_ADDR*/
	public final EZDPStringItem              addShipToFirstLineAddr;

    /** ADD_SHIP_TO_SCD_LINE_ADDR*/
	public final EZDPStringItem              addShipToScdLineAddr;

    /** ADD_SHIP_TO_THIRD_LINE_ADDR*/
	public final EZDPStringItem              addShipToThirdLineAddr;

    /** ADD_SHIP_TO_FRTH_LINE_ADDR*/
	public final EZDPStringItem              addShipToFrthLineAddr;

    /** ADD_SHIP_TO_CTY_ADDR*/
	public final EZDPStringItem              addShipToCtyAddr;

    /** ADD_SHIP_TO_ST_CD*/
	public final EZDPStringItem              addShipToStCd;

    /** ADD_SHIP_TO_PROV_NM*/
	public final EZDPStringItem              addShipToProvNm;

    /** ADD_SHIP_TO_POST_CD*/
	public final EZDPStringItem              addShipToPostCd;

    /** ADD_SHIP_TO_CTRY_CD*/
	public final EZDPStringItem              addShipToCtryCd;

    /** ADD_SHIP_TO_CNTY_NM*/
	public final EZDPStringItem              addShipToCntyNm;

    /** ADD_SHIP_TO_01_REF_CMNT_TXT*/
	public final EZDPStringItem              addShipTo01RefCmntTxt;

    /** ADD_SHIP_TO_02_REF_CMNT_TXT*/
	public final EZDPStringItem              addShipTo02RefCmntTxt;

    /** ADD_PMT_TERM_CASH_DISC_CD*/
	public final EZDPStringItem              addPmtTermCashDiscCd;

    /** CHNG_RSN_TP_CD*/
	public final EZDPStringItem              chngRsnTpCd;

    /** BIZ_PROC_CMNT_TXT*/
	public final EZDPStringItem              bizProcCmntTxt;

    /** SYS_SRC_CD*/
	public final EZDPStringItem              sysSrcCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** CUST_ISS_PO_DT*/
	public final EZDPDateItem              custIssPoDt;

    /** CARR_ACCT_NUM*/
	public final EZDPStringItem              carrAcctNum;

    /** ORD_SGN_DT*/
	public final EZDPDateItem              ordSgnDt;

    /** ORG_RQST_DELY_DT*/
	public final EZDPDateItem              orgRqstDelyDt;

    /** INV_RCPNT_CUST_CD*/
	public final EZDPStringItem              invRcpntCustCd;

    /** DS_ORD_TP_CD*/
	public final EZDPStringItem              dsOrdTpCd;

    /** DS_ORD_RSN_CD*/
	public final EZDPStringItem              dsOrdRsnCd;

    /** DS_PMT_METH_CD*/
	public final EZDPStringItem              dsPmtMethCd;

    /** LEASE_ORD_FLG*/
	public final EZDPStringItem              leaseOrdFlg;

    /** PRC_BASE_DT*/
	public final EZDPDateItem              prcBaseDt;

    /** PRC_CALC_DT*/
	public final EZDPDateItem              prcCalcDt;

    /** FIRST_ORG_CD*/
	public final EZDPStringItem              firstOrgCd;

    /** SCD_ORG_CD*/
	public final EZDPStringItem              scdOrgCd;

    /** DS_ORD_CATG_CD*/
	public final EZDPStringItem              dsOrdCatgCd;

    /** BILL_TO_CUST_ACCT_CD*/
	public final EZDPStringItem              billToCustAcctCd;

    /** SHIP_TO_CUST_ACCT_CD*/
	public final EZDPStringItem              shipToCustAcctCd;

    /** SOLD_TO_CUST_LOC_CD*/
	public final EZDPStringItem              soldToCustLocCd;

    /** NEGO_DEAL_AMT*/
	public final EZDPBigDecimalItem              negoDealAmt;

    /** SLS_REP_TOC_CD*/
	public final EZDPStringItem              slsRepTocCd;

    /** PRC_CATG_CD*/
	public final EZDPStringItem              prcCatgCd;

    /** FL_PRC_LIST_CD*/
	public final EZDPStringItem              flPrcListCd;

    /** AQU_NUM*/
	public final EZDPStringItem              aquNum;

    /** ORD_SRC_IMPT_DT*/
	public final EZDPDateItem              ordSrcImptDt;

    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** PRC_CONTR_NUM*/
	public final EZDPStringItem              prcContrNum;

    /** LOAN_PER_DAYS_AOT*/
	public final EZDPBigDecimalItem              loanPerDaysAot;

    /** CSMP_CONTR_NUM*/
	public final EZDPStringItem              csmpContrNum;

    /** LEASE_CMPY_PO_NUM*/
	public final EZDPStringItem              leaseCmpyPoNum;

    /** LEASE_PRCH_OPT_CD*/
	public final EZDPStringItem              leasePrchOptCd;

    /** LEASE_TERM_CD*/
	public final EZDPStringItem              leaseTermCd;

    /** LEASE_TERM_MTH_AOT*/
	public final EZDPBigDecimalItem              leaseTermMthAot;

    /** LEASE_PMT_FREQ_CD*/
	public final EZDPStringItem              leasePmtFreqCd;

    /** LEASE_TOT_PMT_AMT*/
	public final EZDPBigDecimalItem              leaseTotPmtAmt;

    /** ORD_LOG_TP_CD*/
	public final EZDPStringItem              ordLogTpCd;

    /** DLR_REF_NUM*/
	public final EZDPStringItem              dlrRefNum;

    /** FRT_COND_CD*/
	public final EZDPStringItem              frtCondCd;

    /** CARR_SVC_LVL_CD*/
	public final EZDPStringItem              carrSvcLvlCd;

    /** SPCL_HDLG_TP_CD*/
	public final EZDPStringItem              spclHdlgTpCd;

    /** PRE_PMT_CHK_NUM*/
	public final EZDPStringItem              prePmtChkNum;

    /** PRE_PMT_AMT*/
	public final EZDPBigDecimalItem              prePmtAmt;

    /** PRE_PMT_TP_CD*/
	public final EZDPStringItem              prePmtTpCd;

    /** CR_REBIL_RSN_CATG_CD*/
	public final EZDPStringItem              crRebilRsnCatgCd;

    /** DS_CR_CARD_PK*/
	public final EZDPBigDecimalItem              dsCrCardPk;

    /** ORIG_CPO_ORD_NUM*/
	public final EZDPStringItem              origCpoOrdNum;

    /** ORIG_INV_NUM*/
	public final EZDPStringItem              origInvNum;

    /** BIZ_PROC_LOG_PK*/
	public final EZDPBigDecimalItem              bizProcLogPk;

    /** DI_CHK_HLD_FLG*/
	public final EZDPStringItem              diChkHldFlg;

    /** XX_VAL_UPD_FLG*/
	public final EZDPStringItem              xxValUpdFlg;

    /** ADD_ORIG_CPO_ORD_NUM*/
	public final EZDPStringItem              addOrigCpoOrdNum;

    /** RE_BILL_PAIR_CPO_ORD_NUM*/
	public final EZDPStringItem              reBillPairCpoOrdNum;

    /** DS_CPO_PRC_IND*/
	public final EZDPStringItem              dsCpoPrcInd;

    /** ordRtrnDtlList*/
	public final business.parts.NWZC153001_ordRtrnDtlListPMsgArray              ordRtrnDtlList;

    /** prcCalcList*/
	public final business.parts.NWZC153001_prcCalcListPMsgArray              prcCalcList;

    /** hldList*/
	public final business.parts.NWZC153001_hldListPMsgArray              hldList;

    /** xxMsgIdList*/
	public final business.parts.NWZC153001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC153001PMsg is constructor.
	 * The initialization when the instance of NWZC153001PMsg is generated.
	 */
	public NWZC153001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC153001PMsg is constructor.
	 * The initialization when the instance of NWZC153001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC153001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxRqstTpCd = (EZDPStringItem)newItem("xxRqstTpCd");
		xxScrEdtTpCd = (EZDPStringItem)newItem("xxScrEdtTpCd");
		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		cpoOrdNum_BK = (EZDPStringItem)newItem("cpoOrdNum_BK");
		cpoOrdTpCd = (EZDPStringItem)newItem("cpoOrdTpCd");
		custIssPoNum = (EZDPStringItem)newItem("custIssPoNum");
		cpoSrcTpCd = (EZDPStringItem)newItem("cpoSrcTpCd");
		ordFuflLvlCd = (EZDPStringItem)newItem("ordFuflLvlCd");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		sellToFirstRefCmntTxt = (EZDPStringItem)newItem("sellToFirstRefCmntTxt");
		sellToScdRefCmntTxt = (EZDPStringItem)newItem("sellToScdRefCmntTxt");
		adminPsnCd = (EZDPStringItem)newItem("adminPsnCd");
		payerCustCd = (EZDPStringItem)newItem("payerCustCd");
		addRddDt = (EZDPDateItem)newItem("addRddDt");
		addRsdDt = (EZDPDateItem)newItem("addRsdDt");
		addShpgSvcLvlCd = (EZDPStringItem)newItem("addShpgSvcLvlCd");
		addFrtChrgToCd = (EZDPStringItem)newItem("addFrtChrgToCd");
		addFrtChrgMethCd = (EZDPStringItem)newItem("addFrtChrgMethCd");
		addDropShipFlg = (EZDPStringItem)newItem("addDropShipFlg");
		addShipToCustCd = (EZDPStringItem)newItem("addShipToCustCd");
		addShipToLocNm = (EZDPStringItem)newItem("addShipToLocNm");
		addShipToAddlLocNm = (EZDPStringItem)newItem("addShipToAddlLocNm");
		addShipToFirstLineAddr = (EZDPStringItem)newItem("addShipToFirstLineAddr");
		addShipToScdLineAddr = (EZDPStringItem)newItem("addShipToScdLineAddr");
		addShipToThirdLineAddr = (EZDPStringItem)newItem("addShipToThirdLineAddr");
		addShipToFrthLineAddr = (EZDPStringItem)newItem("addShipToFrthLineAddr");
		addShipToCtyAddr = (EZDPStringItem)newItem("addShipToCtyAddr");
		addShipToStCd = (EZDPStringItem)newItem("addShipToStCd");
		addShipToProvNm = (EZDPStringItem)newItem("addShipToProvNm");
		addShipToPostCd = (EZDPStringItem)newItem("addShipToPostCd");
		addShipToCtryCd = (EZDPStringItem)newItem("addShipToCtryCd");
		addShipToCntyNm = (EZDPStringItem)newItem("addShipToCntyNm");
		addShipTo01RefCmntTxt = (EZDPStringItem)newItem("addShipTo01RefCmntTxt");
		addShipTo02RefCmntTxt = (EZDPStringItem)newItem("addShipTo02RefCmntTxt");
		addPmtTermCashDiscCd = (EZDPStringItem)newItem("addPmtTermCashDiscCd");
		chngRsnTpCd = (EZDPStringItem)newItem("chngRsnTpCd");
		bizProcCmntTxt = (EZDPStringItem)newItem("bizProcCmntTxt");
		sysSrcCd = (EZDPStringItem)newItem("sysSrcCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		custIssPoDt = (EZDPDateItem)newItem("custIssPoDt");
		carrAcctNum = (EZDPStringItem)newItem("carrAcctNum");
		ordSgnDt = (EZDPDateItem)newItem("ordSgnDt");
		orgRqstDelyDt = (EZDPDateItem)newItem("orgRqstDelyDt");
		invRcpntCustCd = (EZDPStringItem)newItem("invRcpntCustCd");
		dsOrdTpCd = (EZDPStringItem)newItem("dsOrdTpCd");
		dsOrdRsnCd = (EZDPStringItem)newItem("dsOrdRsnCd");
		dsPmtMethCd = (EZDPStringItem)newItem("dsPmtMethCd");
		leaseOrdFlg = (EZDPStringItem)newItem("leaseOrdFlg");
		prcBaseDt = (EZDPDateItem)newItem("prcBaseDt");
		prcCalcDt = (EZDPDateItem)newItem("prcCalcDt");
		firstOrgCd = (EZDPStringItem)newItem("firstOrgCd");
		scdOrgCd = (EZDPStringItem)newItem("scdOrgCd");
		dsOrdCatgCd = (EZDPStringItem)newItem("dsOrdCatgCd");
		billToCustAcctCd = (EZDPStringItem)newItem("billToCustAcctCd");
		shipToCustAcctCd = (EZDPStringItem)newItem("shipToCustAcctCd");
		soldToCustLocCd = (EZDPStringItem)newItem("soldToCustLocCd");
		negoDealAmt = (EZDPBigDecimalItem)newItem("negoDealAmt");
		slsRepTocCd = (EZDPStringItem)newItem("slsRepTocCd");
		prcCatgCd = (EZDPStringItem)newItem("prcCatgCd");
		flPrcListCd = (EZDPStringItem)newItem("flPrcListCd");
		aquNum = (EZDPStringItem)newItem("aquNum");
		ordSrcImptDt = (EZDPDateItem)newItem("ordSrcImptDt");
		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		prcContrNum = (EZDPStringItem)newItem("prcContrNum");
		loanPerDaysAot = (EZDPBigDecimalItem)newItem("loanPerDaysAot");
		csmpContrNum = (EZDPStringItem)newItem("csmpContrNum");
		leaseCmpyPoNum = (EZDPStringItem)newItem("leaseCmpyPoNum");
		leasePrchOptCd = (EZDPStringItem)newItem("leasePrchOptCd");
		leaseTermCd = (EZDPStringItem)newItem("leaseTermCd");
		leaseTermMthAot = (EZDPBigDecimalItem)newItem("leaseTermMthAot");
		leasePmtFreqCd = (EZDPStringItem)newItem("leasePmtFreqCd");
		leaseTotPmtAmt = (EZDPBigDecimalItem)newItem("leaseTotPmtAmt");
		ordLogTpCd = (EZDPStringItem)newItem("ordLogTpCd");
		dlrRefNum = (EZDPStringItem)newItem("dlrRefNum");
		frtCondCd = (EZDPStringItem)newItem("frtCondCd");
		carrSvcLvlCd = (EZDPStringItem)newItem("carrSvcLvlCd");
		spclHdlgTpCd = (EZDPStringItem)newItem("spclHdlgTpCd");
		prePmtChkNum = (EZDPStringItem)newItem("prePmtChkNum");
		prePmtAmt = (EZDPBigDecimalItem)newItem("prePmtAmt");
		prePmtTpCd = (EZDPStringItem)newItem("prePmtTpCd");
		crRebilRsnCatgCd = (EZDPStringItem)newItem("crRebilRsnCatgCd");
		dsCrCardPk = (EZDPBigDecimalItem)newItem("dsCrCardPk");
		origCpoOrdNum = (EZDPStringItem)newItem("origCpoOrdNum");
		origInvNum = (EZDPStringItem)newItem("origInvNum");
		bizProcLogPk = (EZDPBigDecimalItem)newItem("bizProcLogPk");
		diChkHldFlg = (EZDPStringItem)newItem("diChkHldFlg");
		xxValUpdFlg = (EZDPStringItem)newItem("xxValUpdFlg");
		addOrigCpoOrdNum = (EZDPStringItem)newItem("addOrigCpoOrdNum");
		reBillPairCpoOrdNum = (EZDPStringItem)newItem("reBillPairCpoOrdNum");
		dsCpoPrcInd = (EZDPStringItem)newItem("dsCpoPrcInd");
		ordRtrnDtlList = (business.parts.NWZC153001_ordRtrnDtlListPMsgArray)newMsgArray("ordRtrnDtlList");
		prcCalcList = (business.parts.NWZC153001_prcCalcListPMsgArray)newMsgArray("prcCalcList");
		hldList = (business.parts.NWZC153001_hldListPMsgArray)newMsgArray("hldList");
		xxMsgIdList = (business.parts.NWZC153001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC153001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC153001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRqstTpCd", "xxRqstTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrEdtTpCd", "xxScrEdtTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_BK", "cpoOrdNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdTpCd", "cpoOrdTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"cpoSrcTpCd", "cpoSrcTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ordFuflLvlCd", "ordFuflLvlCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToFirstRefCmntTxt", "sellToFirstRefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"sellToScdRefCmntTxt", "sellToScdRefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"adminPsnCd", "adminPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"payerCustCd", "payerCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"addRddDt", "addRddDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"addRsdDt", "addRsdDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"addShpgSvcLvlCd", "addShpgSvcLvlCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"addFrtChrgToCd", "addFrtChrgToCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"addFrtChrgMethCd", "addFrtChrgMethCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"addDropShipFlg", "addDropShipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"addShipToCustCd", "addShipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"addShipToLocNm", "addShipToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addShipToAddlLocNm", "addShipToAddlLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addShipToFirstLineAddr", "addShipToFirstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addShipToScdLineAddr", "addShipToScdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addShipToThirdLineAddr", "addShipToThirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addShipToFrthLineAddr", "addShipToFrthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addShipToCtyAddr", "addShipToCtyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"addShipToStCd", "addShipToStCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"addShipToProvNm", "addShipToProvNm", null, null, TYPE_HANKAKUEISU, "25", null},
	{"addShipToPostCd", "addShipToPostCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"addShipToCtryCd", "addShipToCtryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"addShipToCntyNm", "addShipToCntyNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"addShipTo01RefCmntTxt", "addShipTo01RefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"addShipTo02RefCmntTxt", "addShipTo02RefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"addPmtTermCashDiscCd", "addPmtTermCashDiscCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"chngRsnTpCd", "chngRsnTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"bizProcCmntTxt", "bizProcCmntTxt", null, null, TYPE_HANKAKUEISU, "400", null},
	{"sysSrcCd", "sysSrcCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"carrAcctNum", "carrAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ordSgnDt", "ordSgnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"orgRqstDelyDt", "orgRqstDelyDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invRcpntCustCd", "invRcpntCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdRsnCd", "dsOrdRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsPmtMethCd", "dsPmtMethCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"leaseOrdFlg", "leaseOrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcBaseDt", "prcBaseDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prcCalcDt", "prcCalcDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"firstOrgCd", "firstOrgCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"scdOrgCd", "scdOrgCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"billToCustAcctCd", "billToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctCd", "shipToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd", "soldToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"negoDealAmt", "negoDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"flPrcListCd", "flPrcListCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"aquNum", "aquNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"ordSrcImptDt", "ordSrcImptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcContrNum", "prcContrNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"loanPerDaysAot", "loanPerDaysAot", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"leaseCmpyPoNum", "leaseCmpyPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"leasePrchOptCd", "leasePrchOptCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"leaseTermCd", "leaseTermCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"leaseTermMthAot", "leaseTermMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"leasePmtFreqCd", "leasePmtFreqCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"leaseTotPmtAmt", "leaseTotPmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ordLogTpCd", "ordLogTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"frtCondCd", "frtCondCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"carrSvcLvlCd", "carrSvcLvlCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"spclHdlgTpCd", "spclHdlgTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"prePmtChkNum", "prePmtChkNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prePmtAmt", "prePmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prePmtTpCd", "prePmtTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"crRebilRsnCatgCd", "crRebilRsnCatgCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsCrCardPk", "dsCrCardPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"origCpoOrdNum", "origCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"origInvNum", "origInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"bizProcLogPk", "bizProcLogPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"diChkHldFlg", "diChkHldFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxValUpdFlg", "xxValUpdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"addOrigCpoOrdNum", "addOrigCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"reBillPairCpoOrdNum", "reBillPairCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsCpoPrcInd", "dsCpoPrcInd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ordRtrnDtlList", "ordRtrnDtlList", null, "5000", "business.parts.NWZC153001_ordRtrnDtlListPMsgArray", null, null},
	
	{"prcCalcList", "prcCalcList", null, "100000", "business.parts.NWZC153001_prcCalcListPMsgArray", null, null},
	
	{"hldList", "hldList", null, "999", "business.parts.NWZC153001_hldListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC153001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd
        {"XX_SCR_EDT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEdtTpCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_BK
        {"CPO_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTpCd
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CPO_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSrcTpCd
        {"ORD_FUFL_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordFuflLvlCd
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SELL_TO_FIRST_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToFirstRefCmntTxt
        {"SELL_TO_SCD_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToScdRefCmntTxt
        {"ADMIN_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPsnCd
        {"PAYER_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd
        {"ADD_RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addRddDt
        {"ADD_RSD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addRsdDt
        {"ADD_SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShpgSvcLvlCd
        {"ADD_FRT_CHRG_TO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addFrtChrgToCd
        {"ADD_FRT_CHRG_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addFrtChrgMethCd
        {"ADD_DROP_SHIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addDropShipFlg
        {"ADD_SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToCustCd
        {"ADD_SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToLocNm
        {"ADD_SHIP_TO_ADDL_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToAddlLocNm
        {"ADD_SHIP_TO_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToFirstLineAddr
        {"ADD_SHIP_TO_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToScdLineAddr
        {"ADD_SHIP_TO_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToThirdLineAddr
        {"ADD_SHIP_TO_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToFrthLineAddr
        {"ADD_SHIP_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToCtyAddr
        {"ADD_SHIP_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToStCd
        {"ADD_SHIP_TO_PROV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToProvNm
        {"ADD_SHIP_TO_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToPostCd
        {"ADD_SHIP_TO_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToCtryCd
        {"ADD_SHIP_TO_CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToCntyNm
        {"ADD_SHIP_TO_01_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipTo01RefCmntTxt
        {"ADD_SHIP_TO_02_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipTo02RefCmntTxt
        {"ADD_PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addPmtTermCashDiscCd
        {"CHNG_RSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chngRsnTpCd
        {"BIZ_PROC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizProcCmntTxt
        {"SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt
        {"CARR_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrAcctNum
        {"ORD_SGN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSgnDt
        {"ORG_RQST_DELY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgRqstDelyDt
        {"INV_RCPNT_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invRcpntCustCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"DS_ORD_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdRsnCd
        {"DS_PMT_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPmtMethCd
        {"LEASE_ORD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseOrdFlg
        {"PRC_BASE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBaseDt
        {"PRC_CALC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCalcDt
        {"FIRST_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstOrgCd
        {"SCD_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdOrgCd
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd
        {"NEGO_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//negoDealAmt
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"FL_PRC_LIST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//flPrcListCd
        {"AQU_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aquNum
        {"ORD_SRC_IMPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcImptDt
        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"PRC_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum
        {"LOAN_PER_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//loanPerDaysAot
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"LEASE_CMPY_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyPoNum
        {"LEASE_PRCH_OPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePrchOptCd
        {"LEASE_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseTermCd
        {"LEASE_TERM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseTermMthAot
        {"LEASE_PMT_FREQ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePmtFreqCd
        {"LEASE_TOT_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseTotPmtAmt
        {"ORD_LOG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLogTpCd
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"FRT_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondCd
        {"CARR_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrSvcLvlCd
        {"SPCL_HDLG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spclHdlgTpCd
        {"PRE_PMT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prePmtChkNum
        {"PRE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prePmtAmt
        {"PRE_PMT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prePmtTpCd
        {"CR_REBIL_RSN_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilRsnCatgCd
        {"DS_CR_CARD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCrCardPk
        {"ORIG_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origCpoOrdNum
        {"ORIG_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origInvNum
        {"BIZ_PROC_LOG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizProcLogPk
        {"DI_CHK_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//diChkHldFlg
        {"XX_VAL_UPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxValUpdFlg
        {"ADD_ORIG_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addOrigCpoOrdNum
        {"RE_BILL_PAIR_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reBillPairCpoOrdNum
        {"DS_CPO_PRC_IND",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoPrcInd
		null,	//ordRtrnDtlList
		null,	//prcCalcList
		null,	//hldList
		null,	//xxMsgIdList
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}

