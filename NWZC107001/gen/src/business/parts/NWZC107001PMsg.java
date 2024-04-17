//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090728092406000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC107001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC107001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC107001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** xxMsgIdList*/
	public final business.parts.NWZC107001_xxMsgIdListPMsgArray              xxMsgIdList;

    /** XX_SYS_SRC_CD*/
	public final EZDPStringItem              xxSysSrcCd;

    /** XX_RQST_TP_CD*/
	public final EZDPStringItem              xxRqstTpCd;

    /** XX_PRTL_ACPT_FLG*/
	public final EZDPStringItem              xxPrtlAcptFlg;

    /** TRX_SRC_TP_CD*/
	public final EZDPStringItem              trxSrcTpCd;

    /** TRX_CD*/
	public final EZDPStringItem              trxCd;

    /** TRX_RSN_CD*/
	public final EZDPStringItem              trxRsnCd;

    /** TRX_HDR_NUM*/
	public final EZDPStringItem              trxHdrNum;

    /** TRX_LINE_NUM*/
	public final EZDPStringItem              trxLineNum;

    /** TRX_LINE_SUB_NUM*/
	public final EZDPStringItem              trxLineSubNum;

    /** CUST_ISS_PO_NUM*/
	public final EZDPStringItem              custIssPoNum;

    /** ROSS_ORD_TP_CD*/
	public final EZDPStringItem              rossOrdTpCd;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** SET_MDSE_CD*/
	public final EZDPStringItem              setMdseCd;

    /** INVTY_LOC_CD*/
	public final EZDPStringItem              invtyLocCd;

    /** LOC_STS_CD*/
	public final EZDPStringItem              locStsCd;

    /** STK_STS_CD*/
	public final EZDPStringItem              stkStsCd;

    /** SHIP_CPLT_CD*/
	public final EZDPStringItem              shipCpltCd;

    /** XX_RQST_QTY*/
	public final EZDPBigDecimalItem              xxRqstQty;

    /** SLS_REP_TOC_CD*/
	public final EZDPStringItem              slsRepTocCd;

    /** FRT_CHRG_METH_CD*/
	public final EZDPStringItem              frtChrgMethCd;

    /** FRT_CHRG_TO_CD*/
	public final EZDPStringItem              frtChrgToCd;

    /** CARR_CD*/
	public final EZDPStringItem              carrCd;

    /** CARR_ACCT_NUM*/
	public final EZDPStringItem              carrAcctNum;

    /** SHPG_SVC_LVL_CD*/
	public final EZDPStringItem              shpgSvcLvlCd;

    /** XX_ORD_TS*/
	public final EZDPStringItem              xxOrdTs;

    /** RDD_DT*/
	public final EZDPDateItem              rddDt;

    /** RSD_DT*/
	public final EZDPDateItem              rsdDt;

    /** XX_UNIT_PRC*/
	public final EZDPBigDecimalItem              xxUnitPrc;

    /** XX_SLS_AMT*/
	public final EZDPBigDecimalItem              xxSlsAmt;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** SHIP_TO_CUST_CD*/
	public final EZDPStringItem              shipToCustCd;

    /** DROP_SHIP_FLG*/
	public final EZDPStringItem              dropShipFlg;

    /** XX_SHIP_TO_NAME*/
	public final EZDPStringItem              xxShipToName;

    /** XX_SHIP_TO_NAME_ADDL*/
	public final EZDPStringItem              xxShipToNameAddl;

    /** SHIP_TO_FIRST_LINE_ADDR*/
	public final EZDPStringItem              shipToFirstLineAddr;

    /** SHIP_TO_SCD_LINE_ADDR*/
	public final EZDPStringItem              shipToScdLineAddr;

    /** SHIP_TO_THIRD_LINE_ADDR*/
	public final EZDPStringItem              shipToThirdLineAddr;

    /** SHIP_TO_FRTH_LINE_ADDR*/
	public final EZDPStringItem              shipToFrthLineAddr;

    /** SHIP_TO_CTY_ADDR*/
	public final EZDPStringItem              shipToCtyAddr;

    /** SHIP_TO_CNTY_NM*/
	public final EZDPStringItem              shipToCntyNm;

    /** XX_SHIP_TO_PROV_NAME*/
	public final EZDPStringItem              xxShipToProvName;

    /** SHIP_TO_ST_CD*/
	public final EZDPStringItem              shipToStCd;

    /** SHIP_TO_POST_CD*/
	public final EZDPStringItem              shipToPostCd;

    /** SHIP_TO_CTRY_CD*/
	public final EZDPStringItem              shipToCtryCd;

    /** SHIP_TO_FIRST_REF_CMNT_TXT*/
	public final EZDPStringItem              shipToFirstRefCmntTxt;

    /** SHIP_TO_SCD_REF_CMNT_TXT*/
	public final EZDPStringItem              shipToScdRefCmntTxt;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** ALLOC_QTY*/
	public final EZDPBigDecimalItem              allocQty;


	/**
	 * NWZC107001PMsg is constructor.
	 * The initialization when the instance of NWZC107001PMsg is generated.
	 */
	public NWZC107001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC107001PMsg is constructor.
	 * The initialization when the instance of NWZC107001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC107001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxMsgIdList = (business.parts.NWZC107001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
		xxSysSrcCd = (EZDPStringItem)newItem("xxSysSrcCd");
		xxRqstTpCd = (EZDPStringItem)newItem("xxRqstTpCd");
		xxPrtlAcptFlg = (EZDPStringItem)newItem("xxPrtlAcptFlg");
		trxSrcTpCd = (EZDPStringItem)newItem("trxSrcTpCd");
		trxCd = (EZDPStringItem)newItem("trxCd");
		trxRsnCd = (EZDPStringItem)newItem("trxRsnCd");
		trxHdrNum = (EZDPStringItem)newItem("trxHdrNum");
		trxLineNum = (EZDPStringItem)newItem("trxLineNum");
		trxLineSubNum = (EZDPStringItem)newItem("trxLineSubNum");
		custIssPoNum = (EZDPStringItem)newItem("custIssPoNum");
		rossOrdTpCd = (EZDPStringItem)newItem("rossOrdTpCd");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		setMdseCd = (EZDPStringItem)newItem("setMdseCd");
		invtyLocCd = (EZDPStringItem)newItem("invtyLocCd");
		locStsCd = (EZDPStringItem)newItem("locStsCd");
		stkStsCd = (EZDPStringItem)newItem("stkStsCd");
		shipCpltCd = (EZDPStringItem)newItem("shipCpltCd");
		xxRqstQty = (EZDPBigDecimalItem)newItem("xxRqstQty");
		slsRepTocCd = (EZDPStringItem)newItem("slsRepTocCd");
		frtChrgMethCd = (EZDPStringItem)newItem("frtChrgMethCd");
		frtChrgToCd = (EZDPStringItem)newItem("frtChrgToCd");
		carrCd = (EZDPStringItem)newItem("carrCd");
		carrAcctNum = (EZDPStringItem)newItem("carrAcctNum");
		shpgSvcLvlCd = (EZDPStringItem)newItem("shpgSvcLvlCd");
		xxOrdTs = (EZDPStringItem)newItem("xxOrdTs");
		rddDt = (EZDPDateItem)newItem("rddDt");
		rsdDt = (EZDPDateItem)newItem("rsdDt");
		xxUnitPrc = (EZDPBigDecimalItem)newItem("xxUnitPrc");
		xxSlsAmt = (EZDPBigDecimalItem)newItem("xxSlsAmt");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		shipToCustCd = (EZDPStringItem)newItem("shipToCustCd");
		dropShipFlg = (EZDPStringItem)newItem("dropShipFlg");
		xxShipToName = (EZDPStringItem)newItem("xxShipToName");
		xxShipToNameAddl = (EZDPStringItem)newItem("xxShipToNameAddl");
		shipToFirstLineAddr = (EZDPStringItem)newItem("shipToFirstLineAddr");
		shipToScdLineAddr = (EZDPStringItem)newItem("shipToScdLineAddr");
		shipToThirdLineAddr = (EZDPStringItem)newItem("shipToThirdLineAddr");
		shipToFrthLineAddr = (EZDPStringItem)newItem("shipToFrthLineAddr");
		shipToCtyAddr = (EZDPStringItem)newItem("shipToCtyAddr");
		shipToCntyNm = (EZDPStringItem)newItem("shipToCntyNm");
		xxShipToProvName = (EZDPStringItem)newItem("xxShipToProvName");
		shipToStCd = (EZDPStringItem)newItem("shipToStCd");
		shipToPostCd = (EZDPStringItem)newItem("shipToPostCd");
		shipToCtryCd = (EZDPStringItem)newItem("shipToCtryCd");
		shipToFirstRefCmntTxt = (EZDPStringItem)newItem("shipToFirstRefCmntTxt");
		shipToScdRefCmntTxt = (EZDPStringItem)newItem("shipToScdRefCmntTxt");
		slsDt = (EZDPDateItem)newItem("slsDt");
		allocQty = (EZDPBigDecimalItem)newItem("allocQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC107001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC107001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC107001_xxMsgIdListPMsgArray", null, null},
	
	{"xxSysSrcCd", "xxSysSrcCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxRqstTpCd", "xxRqstTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPrtlAcptFlg", "xxPrtlAcptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"trxSrcTpCd", "trxSrcTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxCd", "trxCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxRsnCd", "trxRsnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"trxHdrNum", "trxHdrNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"trxLineNum", "trxLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxLineSubNum", "trxLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"rossOrdTpCd", "rossOrdTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"setMdseCd", "setMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"locStsCd", "locStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"stkStsCd", "stkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"shipCpltCd", "shipCpltCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxRqstQty", "xxRqstQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"frtChrgMethCd", "frtChrgMethCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"frtChrgToCd", "frtChrgToCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"carrCd", "carrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"carrAcctNum", "carrAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shpgSvcLvlCd", "shpgSvcLvlCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxOrdTs", "xxOrdTs", null, null, TYPE_HANKAKUEISU, "17", null},
	{"rddDt", "rddDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rsdDt", "rsdDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxUnitPrc", "xxUnitPrc", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxSlsAmt", "xxSlsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dropShipFlg", "dropShipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxShipToName", "xxShipToName", null, null, TYPE_HANKAKUEISU, "60", null},
	{"xxShipToNameAddl", "xxShipToNameAddl", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFirstLineAddr", "shipToFirstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipToScdLineAddr", "shipToScdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipToThirdLineAddr", "shipToThirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFrthLineAddr", "shipToFrthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipToCtyAddr", "shipToCtyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"shipToCntyNm", "shipToCntyNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxShipToProvName", "xxShipToProvName", null, null, TYPE_HANKAKUEISU, "25", null},
	{"shipToStCd", "shipToStCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"shipToPostCd", "shipToPostCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"shipToCtryCd", "shipToCtryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"shipToFirstRefCmntTxt", "shipToFirstRefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"shipToScdRefCmntTxt", "shipToScdRefCmntTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"allocQty", "allocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
		null,	//xxMsgIdList
        {"XX_SYS_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSysSrcCd
        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd
        {"XX_PRTL_ACPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPrtlAcptFlg
        {"TRX_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxSrcTpCd
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum
        {"TRX_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineSubNum
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"ROSS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rossOrdTpCd
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SET_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setMdseCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"LOC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locStsCd
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd
        {"SHIP_CPLT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCpltCd
        {"XX_RQST_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstQty
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"FRT_CHRG_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtChrgMethCd
        {"FRT_CHRG_TO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtChrgToCd
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd
        {"CARR_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrAcctNum
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd
        {"XX_ORD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdTs
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt
        {"RSD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rsdDt
        {"XX_UNIT_PRC",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUnitPrc
        {"XX_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSlsAmt
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"DROP_SHIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dropShipFlg
        {"XX_SHIP_TO_NAME",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToName
        {"XX_SHIP_TO_NAME_ADDL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToNameAddl
        {"SHIP_TO_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFirstLineAddr
        {"SHIP_TO_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToScdLineAddr
        {"SHIP_TO_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToThirdLineAddr
        {"SHIP_TO_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFrthLineAddr
        {"SHIP_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtyAddr
        {"SHIP_TO_CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCntyNm
        {"XX_SHIP_TO_PROV_NAME",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToProvName
        {"SHIP_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd
        {"SHIP_TO_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToPostCd
        {"SHIP_TO_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtryCd
        {"SHIP_TO_FIRST_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFirstRefCmntTxt
        {"SHIP_TO_SCD_REF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToScdRefCmntTxt
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//allocQty
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
