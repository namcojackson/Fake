//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20181002190630000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC040003PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC040003 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC040003PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** INV_NUM*/
	public final EZDPStringItem              invNum;

    /** INV_BOL_LINE_NUM*/
	public final EZDPStringItem              invBolLineNum;

    /** INV_LINE_NUM*/
	public final EZDPStringItem              invLineNum;

    /** INV_LINE_SUB_NUM*/
	public final EZDPStringItem              invLineSubNum;

    /** INV_LINE_SUB_TRX_NUM*/
	public final EZDPStringItem              invLineSubTrxNum;

    /** STK_STS_CD*/
	public final EZDPStringItem              stkStsCd;

    /** SLS_REP_TOC_CD*/
	public final EZDPStringItem              slsRepTocCd;

    /** BR_CD*/
	public final EZDPStringItem              brCd;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** MDSE_NM*/
	public final EZDPStringItem              mdseNm;

    /** COA_PROD_CD*/
	public final EZDPStringItem              coaProdCd;

    /** TRX_CD*/
	public final EZDPStringItem              trxCd;

    /** TRX_RSN_CD*/
	public final EZDPStringItem              trxRsnCd;

    /** ORD_QTY*/
	public final EZDPBigDecimalItem              ordQty;

    /** SHIP_QTY*/
	public final EZDPBigDecimalItem              shipQty;

    /** BO_QTY*/
	public final EZDPBigDecimalItem              boQty;

    /** MAN_PRC_FLG*/
	public final EZDPStringItem              manPrcFlg;

    /** DEAL_DISC_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealDiscUnitPrcAmt;

    /** DEAL_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealNetUnitPrcAmt;

    /** INV_LINE_DEAL_TAX_AMT*/
	public final EZDPBigDecimalItem              invLineDealTaxAmt;

    /** INV_LINE_DEAL_NET_AMT*/
	public final EZDPBigDecimalItem              invLineDealNetAmt;

    /** FUNC_DISC_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcDiscUnitPrcAmt;

    /** FUNC_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcNetUnitPrcAmt;

    /** INV_LINE_FUNC_TAX_AMT*/
	public final EZDPBigDecimalItem              invLineFuncTaxAmt;

    /** INV_LINE_FUNC_NET_AMT*/
	public final EZDPBigDecimalItem              invLineFuncNetAmt;

    /** TAX_FLG*/
	public final EZDPStringItem              taxFlg;

    /** TAX_PCT*/
	public final EZDPBigDecimalItem              taxPct;

    /** COA_ACCT_CD*/
	public final EZDPStringItem              coaAcctCd;

    /** COA_PROJ_CD*/
	public final EZDPStringItem              coaProjCd;

    /** CR_DR_RSN_SUB_CD*/
	public final EZDPStringItem              crDrRsnSubCd;

    /** SET_MDSE_CD*/
	public final EZDPStringItem              setMdseCd;

    /** SHIP_CMPL_COST_AMT*/
	public final EZDPBigDecimalItem              shipCmplCostAmt;

    /** DEAL_GRS_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealGrsUnitPrcAmt;

    /** DEAL_GRS_TOT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealGrsTotPrcAmt;

    /** FUNC_GRS_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcGrsUnitPrcAmt;

    /** FUNC_GRS_TOT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcGrsTotPrcAmt;

    /** SET_RATIO_KEEP_FLG*/
	public final EZDPStringItem              setRatioKeepFlg;

    /** UNIVS_PRMO_ID*/
	public final EZDPStringItem              univsPrmoId;

    /** PRMO_SHORT_NM*/
	public final EZDPStringItem              prmoShortNm;

    /** SRC_TRX_NUM*/
	public final EZDPStringItem              srcTrxNum;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              svcConfigMstrPk;

    /** DS_CONTR_NUM*/
	public final EZDPStringItem              dsContrNum;

    /** DS_CONTR_SQ_NUM*/
	public final EZDPStringItem              dsContrSqNum;

    /** ESPAC_LINE_SHIP_QTY*/
	public final EZDPBigDecimalItem              espacLineShipQty;

    /** FIRST_BLLG_ATTRB_VAL_TXT*/
	public final EZDPStringItem              firstBllgAttrbValTxt;

    /** SCD_BLLG_ATTRB_VAL_TXT*/
	public final EZDPStringItem              scdBllgAttrbValTxt;

    /** THIRD_BLLG_ATTRB_VAL_TXT*/
	public final EZDPStringItem              thirdBllgAttrbValTxt;

    /** FRTH_BLLG_ATTRB_VAL_TXT*/
	public final EZDPStringItem              frthBllgAttrbValTxt;

    /** FIFTH_BLLG_ATTRB_VAL_TXT*/
	public final EZDPStringItem              fifthBllgAttrbValTxt;

    /** SIXTH_BLLG_ATTRB_VAL_TXT*/
	public final EZDPStringItem              sixthBllgAttrbValTxt;

    /** UOM_CD*/
	public final EZDPStringItem              uomCd;

    /** SVC_INV_LINE_PK*/
	public final EZDPBigDecimalItem              svcInvLinePk;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** MDL_ID*/
	public final EZDPBigDecimalItem              mdlId;

    /** SVC_INV_CHRG_TP_CD*/
	public final EZDPStringItem              svcInvChrgTpCd;

    /** BLLG_PER_FROM_DT*/
	public final EZDPDateItem              bllgPerFromDt;

    /** BLLG_PER_THRU_DT*/
	public final EZDPDateItem              bllgPerThruDt;

    /** BLLG_COPY_QTY*/
	public final EZDPBigDecimalItem              bllgCopyQty;

    /** TAX_CALC_GEO_CD*/
	public final EZDPStringItem              taxCalcGeoCd;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** DS_CPO_LINE_NUM*/
	public final EZDPBigDecimalItem              dsCpoLineNum;

    /** DS_CPO_LINE_SUB_NUM*/
	public final EZDPBigDecimalItem              dsCpoLineSubNum;

    /** CUST_ISS_PO_NUM*/
	public final EZDPStringItem              custIssPoNum;

    /** CUST_ISS_PO_DT*/
	public final EZDPDateItem              custIssPoDt;

    /** INV_DPLY_QTY*/
	public final EZDPBigDecimalItem              invDplyQty;

    /** CPO_DTL_LINE_NUM*/
	public final EZDPStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDPStringItem              cpoDtlLineSubNum;

    /** SPLY_PGM_SHIP_QTY*/
	public final EZDPBigDecimalItem              splyPgmShipQty;

    /** SPLY_PGM_UNIT_AMT_RATE*/
	public final EZDPBigDecimalItem              splyPgmUnitAmtRate;

    /** PRC_CATG_CD*/
	public final EZDPStringItem              prcCatgCd;

    /** DS_ORD_LINE_CATG_CD*/
	public final EZDPStringItem              dsOrdLineCatgCd;

    /** BASE_CMPT_FLG*/
	public final EZDPStringItem              baseCmptFlg;

    /** CSMP_INV_PROC_STS_CD*/
	public final EZDPStringItem              csmpInvProcStsCd;

    /** CSMP_CONTR_NUM*/
	public final EZDPStringItem              csmpContrNum;

    /** MAN_INV_CRAT_CMNT_TXT*/
	public final EZDPStringItem              manInvCratCmntTxt;

    /** INV_LINE_CATG_CD*/
	public final EZDPStringItem              invLineCatgCd;

    /** ORIG_OR_CUST_MDSE_CD*/
	public final EZDPStringItem              origOrCustMdseCd;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** ORD_CUST_UOM_QTY*/
	public final EZDPBigDecimalItem              ordCustUomQty;

    /** xxMsgIdList*/
	public final business.parts.NWZC040003_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC040003PMsg is constructor.
	 * The initialization when the instance of NWZC040003PMsg is generated.
	 */
	public NWZC040003PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC040003PMsg is constructor.
	 * The initialization when the instance of NWZC040003PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC040003PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		invNum = (EZDPStringItem)newItem("invNum");
		invBolLineNum = (EZDPStringItem)newItem("invBolLineNum");
		invLineNum = (EZDPStringItem)newItem("invLineNum");
		invLineSubNum = (EZDPStringItem)newItem("invLineSubNum");
		invLineSubTrxNum = (EZDPStringItem)newItem("invLineSubTrxNum");
		stkStsCd = (EZDPStringItem)newItem("stkStsCd");
		slsRepTocCd = (EZDPStringItem)newItem("slsRepTocCd");
		brCd = (EZDPStringItem)newItem("brCd");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		mdseNm = (EZDPStringItem)newItem("mdseNm");
		coaProdCd = (EZDPStringItem)newItem("coaProdCd");
		trxCd = (EZDPStringItem)newItem("trxCd");
		trxRsnCd = (EZDPStringItem)newItem("trxRsnCd");
		ordQty = (EZDPBigDecimalItem)newItem("ordQty");
		shipQty = (EZDPBigDecimalItem)newItem("shipQty");
		boQty = (EZDPBigDecimalItem)newItem("boQty");
		manPrcFlg = (EZDPStringItem)newItem("manPrcFlg");
		dealDiscUnitPrcAmt = (EZDPBigDecimalItem)newItem("dealDiscUnitPrcAmt");
		dealNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("dealNetUnitPrcAmt");
		invLineDealTaxAmt = (EZDPBigDecimalItem)newItem("invLineDealTaxAmt");
		invLineDealNetAmt = (EZDPBigDecimalItem)newItem("invLineDealNetAmt");
		funcDiscUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcDiscUnitPrcAmt");
		funcNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcNetUnitPrcAmt");
		invLineFuncTaxAmt = (EZDPBigDecimalItem)newItem("invLineFuncTaxAmt");
		invLineFuncNetAmt = (EZDPBigDecimalItem)newItem("invLineFuncNetAmt");
		taxFlg = (EZDPStringItem)newItem("taxFlg");
		taxPct = (EZDPBigDecimalItem)newItem("taxPct");
		coaAcctCd = (EZDPStringItem)newItem("coaAcctCd");
		coaProjCd = (EZDPStringItem)newItem("coaProjCd");
		crDrRsnSubCd = (EZDPStringItem)newItem("crDrRsnSubCd");
		setMdseCd = (EZDPStringItem)newItem("setMdseCd");
		shipCmplCostAmt = (EZDPBigDecimalItem)newItem("shipCmplCostAmt");
		dealGrsUnitPrcAmt = (EZDPBigDecimalItem)newItem("dealGrsUnitPrcAmt");
		dealGrsTotPrcAmt = (EZDPBigDecimalItem)newItem("dealGrsTotPrcAmt");
		funcGrsUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcGrsUnitPrcAmt");
		funcGrsTotPrcAmt = (EZDPBigDecimalItem)newItem("funcGrsTotPrcAmt");
		setRatioKeepFlg = (EZDPStringItem)newItem("setRatioKeepFlg");
		univsPrmoId = (EZDPStringItem)newItem("univsPrmoId");
		prmoShortNm = (EZDPStringItem)newItem("prmoShortNm");
		srcTrxNum = (EZDPStringItem)newItem("srcTrxNum");
		svcConfigMstrPk = (EZDPBigDecimalItem)newItem("svcConfigMstrPk");
		dsContrNum = (EZDPStringItem)newItem("dsContrNum");
		dsContrSqNum = (EZDPStringItem)newItem("dsContrSqNum");
		espacLineShipQty = (EZDPBigDecimalItem)newItem("espacLineShipQty");
		firstBllgAttrbValTxt = (EZDPStringItem)newItem("firstBllgAttrbValTxt");
		scdBllgAttrbValTxt = (EZDPStringItem)newItem("scdBllgAttrbValTxt");
		thirdBllgAttrbValTxt = (EZDPStringItem)newItem("thirdBllgAttrbValTxt");
		frthBllgAttrbValTxt = (EZDPStringItem)newItem("frthBllgAttrbValTxt");
		fifthBllgAttrbValTxt = (EZDPStringItem)newItem("fifthBllgAttrbValTxt");
		sixthBllgAttrbValTxt = (EZDPStringItem)newItem("sixthBllgAttrbValTxt");
		uomCd = (EZDPStringItem)newItem("uomCd");
		svcInvLinePk = (EZDPBigDecimalItem)newItem("svcInvLinePk");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		mdlId = (EZDPBigDecimalItem)newItem("mdlId");
		svcInvChrgTpCd = (EZDPStringItem)newItem("svcInvChrgTpCd");
		bllgPerFromDt = (EZDPDateItem)newItem("bllgPerFromDt");
		bllgPerThruDt = (EZDPDateItem)newItem("bllgPerThruDt");
		bllgCopyQty = (EZDPBigDecimalItem)newItem("bllgCopyQty");
		taxCalcGeoCd = (EZDPStringItem)newItem("taxCalcGeoCd");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		dsCpoLineNum = (EZDPBigDecimalItem)newItem("dsCpoLineNum");
		dsCpoLineSubNum = (EZDPBigDecimalItem)newItem("dsCpoLineSubNum");
		custIssPoNum = (EZDPStringItem)newItem("custIssPoNum");
		custIssPoDt = (EZDPDateItem)newItem("custIssPoDt");
		invDplyQty = (EZDPBigDecimalItem)newItem("invDplyQty");
		cpoDtlLineNum = (EZDPStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDPStringItem)newItem("cpoDtlLineSubNum");
		splyPgmShipQty = (EZDPBigDecimalItem)newItem("splyPgmShipQty");
		splyPgmUnitAmtRate = (EZDPBigDecimalItem)newItem("splyPgmUnitAmtRate");
		prcCatgCd = (EZDPStringItem)newItem("prcCatgCd");
		dsOrdLineCatgCd = (EZDPStringItem)newItem("dsOrdLineCatgCd");
		baseCmptFlg = (EZDPStringItem)newItem("baseCmptFlg");
		csmpInvProcStsCd = (EZDPStringItem)newItem("csmpInvProcStsCd");
		csmpContrNum = (EZDPStringItem)newItem("csmpContrNum");
		manInvCratCmntTxt = (EZDPStringItem)newItem("manInvCratCmntTxt");
		invLineCatgCd = (EZDPStringItem)newItem("invLineCatgCd");
		origOrCustMdseCd = (EZDPStringItem)newItem("origOrCustMdseCd");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		ordCustUomQty = (EZDPBigDecimalItem)newItem("ordCustUomQty");
		xxMsgIdList = (business.parts.NWZC040003_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC040003PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC040003PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"invBolLineNum", "invBolLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum", "invLineNum", null, null, TYPE_HANKAKUEISU, "5", null},
	{"invLineSubNum", "invLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invLineSubTrxNum", "invLineSubTrxNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"stkStsCd", "stkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"slsRepTocCd", "slsRepTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"brCd", "brCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm", "mdseNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"trxCd", "trxCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"trxRsnCd", "trxRsnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shipQty", "shipQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"boQty", "boQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"manPrcFlg", "manPrcFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dealDiscUnitPrcAmt", "dealDiscUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealNetUnitPrcAmt", "dealNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invLineDealTaxAmt", "invLineDealTaxAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invLineDealNetAmt", "invLineDealNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcDiscUnitPrcAmt", "funcDiscUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcNetUnitPrcAmt", "funcNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invLineFuncTaxAmt", "invLineFuncTaxAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invLineFuncNetAmt", "invLineFuncNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"taxFlg", "taxFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"taxPct", "taxPct", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"coaAcctCd", "coaAcctCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaProjCd", "coaProjCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"crDrRsnSubCd", "crDrRsnSubCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"setMdseCd", "setMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"shipCmplCostAmt", "shipCmplCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealGrsUnitPrcAmt", "dealGrsUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealGrsTotPrcAmt", "dealGrsTotPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcGrsUnitPrcAmt", "funcGrsUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcGrsTotPrcAmt", "funcGrsTotPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"setRatioKeepFlg", "setRatioKeepFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"univsPrmoId", "univsPrmoId", null, null, TYPE_HANKAKUEISU, "28", null},
	{"prmoShortNm", "prmoShortNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"srcTrxNum", "srcTrxNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrSqNum", "dsContrSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"espacLineShipQty", "espacLineShipQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"firstBllgAttrbValTxt", "firstBllgAttrbValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"scdBllgAttrbValTxt", "scdBllgAttrbValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"thirdBllgAttrbValTxt", "thirdBllgAttrbValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"frthBllgAttrbValTxt", "frthBllgAttrbValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"fifthBllgAttrbValTxt", "fifthBllgAttrbValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"sixthBllgAttrbValTxt", "sixthBllgAttrbValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"uomCd", "uomCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcInvLinePk", "svcInvLinePk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"svcInvChrgTpCd", "svcInvChrgTpCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"bllgPerFromDt", "bllgPerFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerThruDt", "bllgPerThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCopyQty", "bllgCopyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"taxCalcGeoCd", "taxCalcGeoCd", null, null, TYPE_HANKAKUEISU, "9", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsCpoLineNum", "dsCpoLineNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsCpoLineSubNum", "dsCpoLineSubNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invDplyQty", "invDplyQty", null, null, TYPE_SEISU_SYOSU, "10", "2"},
	{"cpoDtlLineNum", "cpoDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum", "cpoDtlLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"splyPgmShipQty", "splyPgmShipQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"splyPgmUnitAmtRate", "splyPgmUnitAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dsOrdLineCatgCd", "dsOrdLineCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"baseCmptFlg", "baseCmptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"csmpInvProcStsCd", "csmpInvProcStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"manInvCratCmntTxt", "manInvCratCmntTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"invLineCatgCd", "invLineCatgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"origOrCustMdseCd", "origOrCustMdseCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ordCustUomQty", "ordCustUomQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC040003_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum
        {"INV_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubNum
        {"INV_LINE_SUB_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubTrxNum
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd
        {"BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//brCd
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty
        {"BO_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//boQty
        {"MAN_PRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manPrcFlg
        {"DEAL_DISC_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealDiscUnitPrcAmt
        {"DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealNetUnitPrcAmt
        {"INV_LINE_DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineDealTaxAmt
        {"INV_LINE_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineDealNetAmt
        {"FUNC_DISC_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcDiscUnitPrcAmt
        {"FUNC_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcNetUnitPrcAmt
        {"INV_LINE_FUNC_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineFuncTaxAmt
        {"INV_LINE_FUNC_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineFuncNetAmt
        {"TAX_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxFlg
        {"TAX_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPct
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd
        {"CR_DR_RSN_SUB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crDrRsnSubCd
        {"SET_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setMdseCd
        {"SHIP_CMPL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipCmplCostAmt
        {"DEAL_GRS_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealGrsUnitPrcAmt
        {"DEAL_GRS_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealGrsTotPrcAmt
        {"FUNC_GRS_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcGrsUnitPrcAmt
        {"FUNC_GRS_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcGrsTotPrcAmt
        {"SET_RATIO_KEEP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setRatioKeepFlg
        {"UNIVS_PRMO_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//univsPrmoId
        {"PRMO_SHORT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prmoShortNm
        {"SRC_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcTrxNum
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSqNum
        {"ESPAC_LINE_SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//espacLineShipQty
        {"FIRST_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstBllgAttrbValTxt
        {"SCD_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdBllgAttrbValTxt
        {"THIRD_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdBllgAttrbValTxt
        {"FRTH_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthBllgAttrbValTxt
        {"FIFTH_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthBllgAttrbValTxt
        {"SIXTH_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sixthBllgAttrbValTxt
        {"UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uomCd
        {"SVC_INV_LINE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvLinePk
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"SVC_INV_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvChrgTpCd
        {"BLLG_PER_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerFromDt
        {"BLLG_PER_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerThruDt
        {"BLLG_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCopyQty
        {"TAX_CALC_GEO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCalcGeoCd
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_CPO_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum
        {"DS_CPO_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineSubNum
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt
        {"INV_DPLY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDplyQty
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
        {"SPLY_PGM_SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyPgmShipQty
        {"SPLY_PGM_UNIT_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyPgmUnitAmtRate
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd
        {"BASE_CMPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseCmptFlg
        {"CSMP_INV_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpInvProcStsCd
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"MAN_INV_CRAT_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manInvCratCmntTxt
        {"INV_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineCatgCd
        {"ORIG_OR_CUST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origOrCustMdseCd
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"ORD_CUST_UOM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCustUomQty
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

