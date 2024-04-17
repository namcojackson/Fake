//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20181214185455000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1570F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1570F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1570F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_ORD_INQ_REF_NUM_A1*/
	public final EZDFStringItem              xxDplyOrdInqRefNum_A1;

    /** XX_DPLY_ORD_LINE_NUM_A1*/
	public final EZDFStringItem              xxDplyOrdLineNum_A1;

    /** ORD_QTY_A1*/
	public final EZDFBigDecimalItem              ordQty_A1;

    /** XX_TOT_AMT_A1*/
	public final EZDFBigDecimalItem              xxTotAmt_A1;

    /** XX_HDR_DPLY_STS_NM_A1*/
	public final EZDFStringItem              xxHdrDplyStsNm_A1;

    /** XX_LINE_DPLY_STS_NM_A1*/
	public final EZDFStringItem              xxLineDplyStsNm_A1;

    /** MDSE_CD_A1*/
	public final EZDFStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDFStringItem              mdseDescShortTxt_A1;

    /** RTL_WH_CD_A1*/
	public final EZDFStringItem              rtlWhCd_A1;

    /** RTL_WH_DESC_TXT_A1*/
	public final EZDFStringItem              rtlWhDescTxt_A1;

    /** RTL_SWH_NM_A1*/
	public final EZDFStringItem              rtlSwhNm_A1;

    /** SHIP_TO_CUST_ACCT_CD_A1*/
	public final EZDFStringItem              shipToCustAcctCd_A1;

    /** SHIP_TO_CUST_ACCT_NM_A1*/
	public final EZDFStringItem              shipToCustAcctNm_A1;

    /** SHIP_TO_CUST_LOC_CD_A1*/
	public final EZDFStringItem              shipToCustLocCd_A1;

    /** BILL_TO_CUST_ACCT_CD_A1*/
	public final EZDFStringItem              billToCustAcctCd_A1;

    /** BILL_TO_CUST_ACCT_NM_A1*/
	public final EZDFStringItem              billToCustAcctNm_A1;

    /** BILL_TO_CUST_LOC_CD_A1*/
	public final EZDFStringItem              billToCustLocCd_A1;

    /** SOLD_TO_CUST_ACCT_CD_A1*/
	public final EZDFStringItem              soldToCustAcctCd_A1;

    /** SOLD_TO_CUST_ACCT_NM_A1*/
	public final EZDFStringItem              soldToCustAcctNm_A1;

    /** SOLD_TO_CUST_LOC_CD_A1*/
	public final EZDFStringItem              soldToCustLocCd_A1;

    /** XX_DT_TXT_OD*/
	public final EZDFStringItem              xxDtTxt_OD;

    /** XX_DT_TXT_BD*/
	public final EZDFStringItem              xxDtTxt_BD;

    /** XX_DT_TXT_RD*/
	public final EZDFStringItem              xxDtTxt_RD;

    /** XX_DT_TXT_PD*/
	public final EZDFStringItem              xxDtTxt_PD;

    /** XX_DT_TXT_DD*/
	public final EZDFStringItem              xxDtTxt_DD;

    /** XX_DT_TXT_SD*/
	public final EZDFStringItem              xxDtTxt_SD;

    /** XX_DT_TXT_TD*/
	public final EZDFStringItem              xxDtTxt_TD;

    /** XX_DT_TXT_ID*/
	public final EZDFStringItem              xxDtTxt_ID;

    /** DS_ORD_CATG_DESC_TXT_A1*/
	public final EZDFStringItem              dsOrdCatgDescTxt_A1;

    /** DS_ORD_TP_DESC_TXT_A1*/
	public final EZDFStringItem              dsOrdTpDescTxt_A1;

    /** DS_ORD_RSN_DESC_TXT_A1*/
	public final EZDFStringItem              dsOrdRsnDescTxt_A1;

    /** CPO_SRC_TP_DESC_TXT_A1*/
	public final EZDFStringItem              cpoSrcTpDescTxt_A1;

    /** ORD_SRC_REF_NUM_A1*/
	public final EZDFStringItem              ordSrcRefNum_A1;

    /** DS_ORD_LINE_CATG_DESC_TXT_A1*/
	public final EZDFStringItem              dsOrdLineCatgDescTxt_A1;

    /** ORD_LINE_SRC_NM_A1*/
	public final EZDFStringItem              ordLineSrcNm_A1;

    /** PRC_CATG_DESC_TXT_A1*/
	public final EZDFStringItem              prcCatgDescTxt_A1;

    /** CUST_ISS_PO_NUM_A1*/
	public final EZDFStringItem              custIssPoNum_A1;

    /** LEASE_CMPY_PO_NUM_A1*/
	public final EZDFStringItem              leaseCmpyPoNum_A1;

    /** COA_EXTN_DESC_TXT_A1*/
	public final EZDFStringItem              coaExtnDescTxt_A1;

    /** COA_BR_DESC_TXT_A1*/
	public final EZDFStringItem              coaBrDescTxt_A1;

    /** SLS_REP_PSN_NUM_A1*/
	public final EZDFStringItem              slsRepPsnNum_A1;

    /** TOC_NM_A1*/
	public final EZDFStringItem              tocNm_A1;

    /** CSMP_CONTR_NUM_A1*/
	public final EZDFStringItem              csmpContrNum_A1;

    /** PRC_CONTR_NM_A1*/
	public final EZDFStringItem              prcContrNm_A1;

    /** ZEROTH_PROD_CTRL_NM_A1*/
	public final EZDFStringItem              zerothProdCtrlNm_A1;

    /** FIRST_PROD_CTRL_NM_A1*/
	public final EZDFStringItem              firstProdCtrlNm_A1;

    /** SCD_PROD_CTRL_NM_A1*/
	public final EZDFStringItem              scdProdCtrlNm_A1;

    /** THIRD_PROD_CTRL_NM_A1*/
	public final EZDFStringItem              thirdProdCtrlNm_A1;

    /** FRTH_PROD_CTRL_NM_A1*/
	public final EZDFStringItem              frthProdCtrlNm_A1;

    /** T_MDL_NM_A1*/
	public final EZDFStringItem              t_MdlNm_A1;

    /** COA_PROD_DESC_TXT_A1*/
	public final EZDFStringItem              coaProdDescTxt_A1;

    /** COA_MDSE_TP_DESC_TXT_A1*/
	public final EZDFStringItem              coaMdseTpDescTxt_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDFBigDecimalItem              svcConfigMstrPk_A1;

    /** DS_CONTR_NUM_A1*/
	public final EZDFStringItem              dsContrNum_A1;

    /** SER_NUM_A1*/
	public final EZDFStringItem              serNum_A1;

    /** PRCH_REQ_NUM_A1*/
	public final EZDFStringItem              prchReqNum_A1;

    /** PO_NUM_A1*/
	public final EZDFStringItem              poNum_A1;

    /** SO_NUM_A1*/
	public final EZDFStringItem              soNum_A1;

    /** INV_NUM_A1*/
	public final EZDFStringItem              invNum_A1;

    /** DPLY_VND_NM_A1*/
	public final EZDFStringItem              dplyVndNm_A1;

    /** AQU_NUM_A1*/
	public final EZDFStringItem              aquNum_A1;


	/**
	 * NWAL1570F00FMsg is constructor.
	 * The initialization when the instance of NWAL1570F00FMsg is generated.
	 */
	public NWAL1570F00FMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1570F00FMsg is constructor.
	 * The initialization when the instance of NWAL1570F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1570F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyOrdInqRefNum_A1 = (EZDFStringItem)newItem("xxDplyOrdInqRefNum_A1");
		xxDplyOrdLineNum_A1 = (EZDFStringItem)newItem("xxDplyOrdLineNum_A1");
		ordQty_A1 = (EZDFBigDecimalItem)newItem("ordQty_A1");
		xxTotAmt_A1 = (EZDFBigDecimalItem)newItem("xxTotAmt_A1");
		xxHdrDplyStsNm_A1 = (EZDFStringItem)newItem("xxHdrDplyStsNm_A1");
		xxLineDplyStsNm_A1 = (EZDFStringItem)newItem("xxLineDplyStsNm_A1");
		mdseCd_A1 = (EZDFStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDFStringItem)newItem("mdseDescShortTxt_A1");
		rtlWhCd_A1 = (EZDFStringItem)newItem("rtlWhCd_A1");
		rtlWhDescTxt_A1 = (EZDFStringItem)newItem("rtlWhDescTxt_A1");
		rtlSwhNm_A1 = (EZDFStringItem)newItem("rtlSwhNm_A1");
		shipToCustAcctCd_A1 = (EZDFStringItem)newItem("shipToCustAcctCd_A1");
		shipToCustAcctNm_A1 = (EZDFStringItem)newItem("shipToCustAcctNm_A1");
		shipToCustLocCd_A1 = (EZDFStringItem)newItem("shipToCustLocCd_A1");
		billToCustAcctCd_A1 = (EZDFStringItem)newItem("billToCustAcctCd_A1");
		billToCustAcctNm_A1 = (EZDFStringItem)newItem("billToCustAcctNm_A1");
		billToCustLocCd_A1 = (EZDFStringItem)newItem("billToCustLocCd_A1");
		soldToCustAcctCd_A1 = (EZDFStringItem)newItem("soldToCustAcctCd_A1");
		soldToCustAcctNm_A1 = (EZDFStringItem)newItem("soldToCustAcctNm_A1");
		soldToCustLocCd_A1 = (EZDFStringItem)newItem("soldToCustLocCd_A1");
		xxDtTxt_OD = (EZDFStringItem)newItem("xxDtTxt_OD");
		xxDtTxt_BD = (EZDFStringItem)newItem("xxDtTxt_BD");
		xxDtTxt_RD = (EZDFStringItem)newItem("xxDtTxt_RD");
		xxDtTxt_PD = (EZDFStringItem)newItem("xxDtTxt_PD");
		xxDtTxt_DD = (EZDFStringItem)newItem("xxDtTxt_DD");
		xxDtTxt_SD = (EZDFStringItem)newItem("xxDtTxt_SD");
		xxDtTxt_TD = (EZDFStringItem)newItem("xxDtTxt_TD");
		xxDtTxt_ID = (EZDFStringItem)newItem("xxDtTxt_ID");
		dsOrdCatgDescTxt_A1 = (EZDFStringItem)newItem("dsOrdCatgDescTxt_A1");
		dsOrdTpDescTxt_A1 = (EZDFStringItem)newItem("dsOrdTpDescTxt_A1");
		dsOrdRsnDescTxt_A1 = (EZDFStringItem)newItem("dsOrdRsnDescTxt_A1");
		cpoSrcTpDescTxt_A1 = (EZDFStringItem)newItem("cpoSrcTpDescTxt_A1");
		ordSrcRefNum_A1 = (EZDFStringItem)newItem("ordSrcRefNum_A1");
		dsOrdLineCatgDescTxt_A1 = (EZDFStringItem)newItem("dsOrdLineCatgDescTxt_A1");
		ordLineSrcNm_A1 = (EZDFStringItem)newItem("ordLineSrcNm_A1");
		prcCatgDescTxt_A1 = (EZDFStringItem)newItem("prcCatgDescTxt_A1");
		custIssPoNum_A1 = (EZDFStringItem)newItem("custIssPoNum_A1");
		leaseCmpyPoNum_A1 = (EZDFStringItem)newItem("leaseCmpyPoNum_A1");
		coaExtnDescTxt_A1 = (EZDFStringItem)newItem("coaExtnDescTxt_A1");
		coaBrDescTxt_A1 = (EZDFStringItem)newItem("coaBrDescTxt_A1");
		slsRepPsnNum_A1 = (EZDFStringItem)newItem("slsRepPsnNum_A1");
		tocNm_A1 = (EZDFStringItem)newItem("tocNm_A1");
		csmpContrNum_A1 = (EZDFStringItem)newItem("csmpContrNum_A1");
		prcContrNm_A1 = (EZDFStringItem)newItem("prcContrNm_A1");
		zerothProdCtrlNm_A1 = (EZDFStringItem)newItem("zerothProdCtrlNm_A1");
		firstProdCtrlNm_A1 = (EZDFStringItem)newItem("firstProdCtrlNm_A1");
		scdProdCtrlNm_A1 = (EZDFStringItem)newItem("scdProdCtrlNm_A1");
		thirdProdCtrlNm_A1 = (EZDFStringItem)newItem("thirdProdCtrlNm_A1");
		frthProdCtrlNm_A1 = (EZDFStringItem)newItem("frthProdCtrlNm_A1");
		t_MdlNm_A1 = (EZDFStringItem)newItem("t_MdlNm_A1");
		coaProdDescTxt_A1 = (EZDFStringItem)newItem("coaProdDescTxt_A1");
		coaMdseTpDescTxt_A1 = (EZDFStringItem)newItem("coaMdseTpDescTxt_A1");
		svcConfigMstrPk_A1 = (EZDFBigDecimalItem)newItem("svcConfigMstrPk_A1");
		dsContrNum_A1 = (EZDFStringItem)newItem("dsContrNum_A1");
		serNum_A1 = (EZDFStringItem)newItem("serNum_A1");
		prchReqNum_A1 = (EZDFStringItem)newItem("prchReqNum_A1");
		poNum_A1 = (EZDFStringItem)newItem("poNum_A1");
		soNum_A1 = (EZDFStringItem)newItem("soNum_A1");
		invNum_A1 = (EZDFStringItem)newItem("invNum_A1");
		dplyVndNm_A1 = (EZDFStringItem)newItem("dplyVndNm_A1");
		aquNum_A1 = (EZDFStringItem)newItem("aquNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1570F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1570F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyOrdInqRefNum_A1", "xxDplyOrdInqRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyOrdLineNum_A1", "xxDplyOrdLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"ordQty_A1", "ordQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxTotAmt_A1", "xxTotAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxHdrDplyStsNm_A1", "xxHdrDplyStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxLineDplyStsNm_A1", "xxLineDplyStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhDescTxt_A1", "rtlWhDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"rtlSwhNm_A1", "rtlSwhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"shipToCustAcctCd_A1", "shipToCustAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctNm_A1", "shipToCustAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"shipToCustLocCd_A1", "shipToCustLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctCd_A1", "billToCustAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctNm_A1", "billToCustAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustLocCd_A1", "billToCustLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustAcctCd_A1", "soldToCustAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustAcctNm_A1", "soldToCustAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"soldToCustLocCd_A1", "soldToCustLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDtTxt_OD", "xxDtTxt_OD", "OD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_BD", "xxDtTxt_BD", "BD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_RD", "xxDtTxt_RD", "RD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_PD", "xxDtTxt_PD", "PD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_DD", "xxDtTxt_DD", "DD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_SD", "xxDtTxt_SD", "SD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_TD", "xxDtTxt_TD", "TD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_ID", "xxDtTxt_ID", "ID", null, TYPE_HANKAKUEISU, "10", null},
	{"dsOrdCatgDescTxt_A1", "dsOrdCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt_A1", "dsOrdTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdRsnDescTxt_A1", "dsOrdRsnDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"cpoSrcTpDescTxt_A1", "cpoSrcTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"ordSrcRefNum_A1", "ordSrcRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdLineCatgDescTxt_A1", "dsOrdLineCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"ordLineSrcNm_A1", "ordLineSrcNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"prcCatgDescTxt_A1", "prcCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "2000", null},
	{"custIssPoNum_A1", "custIssPoNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"leaseCmpyPoNum_A1", "leaseCmpyPoNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"coaExtnDescTxt_A1", "coaExtnDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaBrDescTxt_A1", "coaBrDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepPsnNum_A1", "slsRepPsnNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"tocNm_A1", "tocNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"csmpContrNum_A1", "csmpContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"prcContrNm_A1", "prcContrNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"zerothProdCtrlNm_A1", "zerothProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"firstProdCtrlNm_A1", "firstProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"scdProdCtrlNm_A1", "scdProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"thirdProdCtrlNm_A1", "thirdProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"frthProdCtrlNm_A1", "frthProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"t_MdlNm_A1", "t_MdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaProdDescTxt_A1", "coaProdDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaMdseTpDescTxt_A1", "coaMdseTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"prchReqNum_A1", "prchReqNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"poNum_A1", "poNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"soNum_A1", "soNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"invNum_A1", "invNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"dplyVndNm_A1", "dplyVndNm_A1", "A1", null, TYPE_HANKAKUEISU, "320", null},
	{"aquNum_A1", "aquNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DPLY_ORD_INQ_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyOrdInqRefNum_A1
        {"XX_DPLY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyOrdLineNum_A1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_A1
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_A1
        {"XX_HDR_DPLY_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrDplyStsNm_A1
        {"XX_LINE_DPLY_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineDplyStsNm_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhDescTxt_A1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A1
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_A1
        {"SHIP_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctNm_A1
        {"SHIP_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocCd_A1
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A1
        {"BILL_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctNm_A1
        {"BILL_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocCd_A1
        {"SOLD_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustAcctCd_A1
        {"SOLD_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustAcctNm_A1
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_OD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_BD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_RD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_PD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_DD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_SD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_TD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_ID
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A1
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_A1
        {"DS_ORD_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdRsnDescTxt_A1
        {"CPO_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSrcTpDescTxt_A1
        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum_A1
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_A1
        {"ORD_LINE_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineSrcNm_A1
        {"PRC_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgDescTxt_A1
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A1
        {"LEASE_CMPY_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyPoNum_A1
        {"COA_EXTN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnDescTxt_A1
        {"COA_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrDescTxt_A1
        {"SLS_REP_PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepPsnNum_A1
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A1
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum_A1
        {"PRC_CONTR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNm_A1
        {"ZEROTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlNm_A1
        {"FIRST_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlNm_A1
        {"SCD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlNm_A1
        {"THIRD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlNm_A1
        {"FRTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlNm_A1
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A1
        {"COA_PROD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdDescTxt_A1
        {"COA_MDSE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpDescTxt_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"PRCH_REQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_A1
        {"PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_A1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_A1
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_A1
        {"AQU_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aquNum_A1
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
