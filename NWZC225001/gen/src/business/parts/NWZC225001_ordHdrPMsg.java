//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_ordHdrPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_ordHdrPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** DS_ORD_CATG_CD*/
	public final EZDPStringItem              dsOrdCatgCd;

    /** DS_ORD_TP_CD*/
	public final EZDPStringItem              dsOrdTpCd;

    /** CUST_ISS_PO_NUM*/
	public final EZDPStringItem              custIssPoNum;

    /** CUST_ISS_PO_DT*/
	public final EZDPDateItem              custIssPoDt;

    /** DS_PO_EXPR_DT*/
	public final EZDPDateItem              dsPoExprDt;

    /** BILL_TO_CUST_ACCT_CD*/
	public final EZDPStringItem              billToCustAcctCd;

    /** BILL_TO_LOC_NUM*/
	public final EZDPStringItem              billToLocNum;

    /** SHIP_TO_CUST_ACCT_CD*/
	public final EZDPStringItem              shipToCustAcctCd;

    /** SHIP_TO_LOC_NUM*/
	public final EZDPStringItem              shipToLocNum;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** SOLD_TO_LOC_NUM*/
	public final EZDPStringItem              soldToLocNum;

    /** PRC_CONTR_PK*/
	public final EZDPBigDecimalItem              prcContrPk;

    /** CSMP_CONTR_NUM*/
	public final EZDPStringItem              csmpContrNum;

    /** DLR_REF_NUM*/
	public final EZDPStringItem              dlrRefNum;

    /** ORD_SGN_DT*/
	public final EZDPDateItem              ordSgnDt;

    /** AQU_NUM*/
	public final EZDPStringItem              aquNum;

    /** SLS_REP_TOC_CD*/
	public final EZDPStringItem              slsRepTocCd;

    /** LOAN_PER_DAYS_AOT*/
	public final EZDPBigDecimalItem              loanPerDaysAot;

    /** LEASE_CMPY_PO_NUM*/
	public final EZDPStringItem              leaseCmpyPoNum;

    /** LEASE_END_TERM_PRCH_OPT_CD*/
	public final EZDPStringItem              leaseEndTermPrchOptCd;

    /** LEASE_TERM_MTH_AOT*/
	public final EZDPBigDecimalItem              leaseTermMthAot;

    /** LEASE_PMT_FREQ_CD*/
	public final EZDPStringItem              leasePmtFreqCd;

    /** ORD_LOG_TP_CD*/
	public final EZDPStringItem              ordLogTpCd;

    /** MAINT_ONLY_FLG*/
	public final EZDPStringItem              maintOnlyFlg;

    /** ADMIN_PSN_CD*/
	public final EZDPStringItem              adminPsnCd;

    /** SLS_QUOTE_NM*/
	public final EZDPStringItem              slsQuoteNm;


	/**
	 * NWZC225001_ordHdrPMsg is constructor.
	 * The initialization when the instance of NWZC225001_ordHdrPMsg is generated.
	 */
	public NWZC225001_ordHdrPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001_ordHdrPMsg is constructor.
	 * The initialization when the instance of NWZC225001_ordHdrPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001_ordHdrPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		dsOrdCatgCd = (EZDPStringItem)newItem("dsOrdCatgCd");
		dsOrdTpCd = (EZDPStringItem)newItem("dsOrdTpCd");
		custIssPoNum = (EZDPStringItem)newItem("custIssPoNum");
		custIssPoDt = (EZDPDateItem)newItem("custIssPoDt");
		dsPoExprDt = (EZDPDateItem)newItem("dsPoExprDt");
		billToCustAcctCd = (EZDPStringItem)newItem("billToCustAcctCd");
		billToLocNum = (EZDPStringItem)newItem("billToLocNum");
		shipToCustAcctCd = (EZDPStringItem)newItem("shipToCustAcctCd");
		shipToLocNum = (EZDPStringItem)newItem("shipToLocNum");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		soldToLocNum = (EZDPStringItem)newItem("soldToLocNum");
		prcContrPk = (EZDPBigDecimalItem)newItem("prcContrPk");
		csmpContrNum = (EZDPStringItem)newItem("csmpContrNum");
		dlrRefNum = (EZDPStringItem)newItem("dlrRefNum");
		ordSgnDt = (EZDPDateItem)newItem("ordSgnDt");
		aquNum = (EZDPStringItem)newItem("aquNum");
		slsRepTocCd = (EZDPStringItem)newItem("slsRepTocCd");
		loanPerDaysAot = (EZDPBigDecimalItem)newItem("loanPerDaysAot");
		leaseCmpyPoNum = (EZDPStringItem)newItem("leaseCmpyPoNum");
		leaseEndTermPrchOptCd = (EZDPStringItem)newItem("leaseEndTermPrchOptCd");
		leaseTermMthAot = (EZDPBigDecimalItem)newItem("leaseTermMthAot");
		leasePmtFreqCd = (EZDPStringItem)newItem("leasePmtFreqCd");
		ordLogTpCd = (EZDPStringItem)newItem("ordLogTpCd");
		maintOnlyFlg = (EZDPStringItem)newItem("maintOnlyFlg");
		adminPsnCd = (EZDPStringItem)newItem("adminPsnCd");
		slsQuoteNm = (EZDPStringItem)newItem("slsQuoteNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001_ordHdrPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001_ordHdrPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsPoExprDt", "dsPoExprDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"billToCustAcctCd", "billToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToLocNum", "billToLocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"shipToCustAcctCd", "shipToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToLocNum", "shipToLocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soldToLocNum", "soldToLocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prcContrPk", "prcContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ordSgnDt", "ordSgnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"aquNum", "aquNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"loanPerDaysAot", "loanPerDaysAot", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"leaseCmpyPoNum", "leaseCmpyPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"leaseEndTermPrchOptCd", "leaseEndTermPrchOptCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"leaseTermMthAot", "leaseTermMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"leasePmtFreqCd", "leasePmtFreqCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"ordLogTpCd", "ordLogTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"maintOnlyFlg", "maintOnlyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"adminPsnCd", "adminPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"slsQuoteNm", "slsQuoteNm", null, null, TYPE_HANKAKUEISU, "360", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt
        {"DS_PO_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPoExprDt
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd
        {"BILL_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd
        {"SHIP_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNum
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SOLD_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToLocNum
        {"PRC_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrPk
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"ORD_SGN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSgnDt
        {"AQU_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aquNum
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"LOAN_PER_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//loanPerDaysAot
        {"LEASE_CMPY_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyPoNum
        {"LEASE_END_TERM_PRCH_OPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseEndTermPrchOptCd
        {"LEASE_TERM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseTermMthAot
        {"LEASE_PMT_FREQ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leasePmtFreqCd
        {"ORD_LOG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLogTpCd
        {"MAINT_ONLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintOnlyFlg
        {"ADMIN_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPsnCd
        {"SLS_QUOTE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsQuoteNm
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

