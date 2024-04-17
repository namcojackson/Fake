//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240410172747000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0150CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0150CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_MSTR_PK*/
	public final EZDCBigDecimalItem              svcMachMstrPk;

    /** MDSE_CD*/
	public final EZDCStringItem              mdseCd;

    /** SVC_BY_LINE_BIZ_TP_CD*/
	public final EZDCStringItem              svcByLineBizTpCd;

    /** SLD_BY_LINE_BIZ_TP_CD*/
	public final EZDCStringItem              sldByLineBizTpCd;

    /** CPO_ORD_NUM*/
	public final EZDCStringItem              cpoOrdNum;

    /** SCHD_AGMT_NUM*/
	public final EZDCStringItem              schdAgmtNum;

    /** BILL_TO_LOC_NM*/
	public final EZDCStringItem              billToLocNm;

    /** SHIP_TO_LOC_NM*/
	public final EZDCStringItem              shipToLocNm;

    /** XX_GENL_FLD_AREA_TXT_CT*/
	public final EZDCStringItem              xxGenlFldAreaTxt_CT;

    /** XX_GENL_FLD_AREA_TXT_FC*/
	public final EZDCStringItem              xxGenlFldAreaTxt_FC;

    /** XX_GENL_FLD_AREA_TXT_LS*/
	public final EZDCStringItem              xxGenlFldAreaTxt_LS;

    /** XX_GENL_FLD_AREA_TXT_TL*/
	public final EZDCStringItem              xxGenlFldAreaTxt_TL;

    /** XX_GENL_FLD_AREA_TXT_FX*/
	public final EZDCStringItem              xxGenlFldAreaTxt_FX;

    /** DS_CTAC_PNT_VAL_TXT_EM*/
	public final EZDCStringItem              dsCtacPntValTxt_EM;

    /** ADDRLINE1_L3000_IF*/
	public final EZDCStringItem              addrline1L3000If;

    /** XX_YES_NO_NM_MC*/
	public final EZDCStringItem              xxYesNoNm_MC;

    /** BLLG_SCHD_THRU_DT*/
	public final EZDCDateItem              bllgSchdThruDt;

    /** MTR_READ_METH_DESC_TXT*/
	public final EZDCStringItem              mtrReadMethDescTxt;

    /** SVC_MEMO_PK*/
	public final EZDCBigDecimalItem              svcMemoPk;

    /** SVC_CMNT_TXT*/
	public final EZDCStringItem              svcCmntTxt;

    /** SVC_CMNT_TXT_HD*/
	public final EZDCStringItem              svcCmntTxt_HD;

    /** _EZUpdateDateTime_SM*/
	public final EZDCStringItem              ezUpTime_SM;

    /** _EZUpTimeZone_SM*/
	public final EZDCStringItem              ezUpTimeZone_SM;

    /** DS_CONTR_PK*/
	public final EZDCBigDecimalItem              dsContrPk;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** DS_CONTR_CTRL_STS_NM*/
	public final EZDCStringItem              dsContrCtrlStsNm;

    /** DS_SUB_CONTR_PK*/
	public final EZDCBigDecimalItem              dsSubContrPk;

    /** XX_COA_BR_SRCH_TXT*/
	public final EZDCStringItem              xxCoaBrSrchTxt;

    /** PRNT_VND_NM*/
	public final EZDCStringItem              prntVndNm;

    /** SPLY_INCL_FLG*/
	public final EZDCStringItem              splyInclFlg;

    /** SPLY_CONTR_CHK_FLG*/
	public final EZDCStringItem              splyContrChkFlg;

    /** MDL_NM*/
	public final EZDCStringItem              mdlNm;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** ISTL_DT*/
	public final EZDCDateItem              istlDt;

    /** CONTR_VRSN_EFF_FROM_DT*/
	public final EZDCDateItem              contrVrsnEffFromDt;

    /** CONTR_VRSN_EFF_THRU_DT*/
	public final EZDCDateItem              contrVrsnEffThruDt;

    /** BLLG_CYCLE_DESC_TXT*/
	public final EZDCStringItem              bllgCycleDescTxt;

    /** DS_CONTR_CATG_DESC_TXT*/
	public final EZDCStringItem              dsContrCatgDescTxt;

    /** DS_INV_TGTR_TP_DESC_TXT*/
	public final EZDCStringItem              dsInvTgtrTpDescTxt;

    /** SVC_TERM_COND_DATA_DISP_TXT*/
	public final EZDCStringItem              svcTermCondDataDispTxt;

    /** XX_WRN_SKIP_FLG*/
	public final EZDCStringItem              xxWrnSkipFlg;

    /** XX_WRN_SKIP_FLG_MR*/
	public final EZDCStringItem              xxWrnSkipFlg_MR;

    /** XX_SET_FLG*/
	public final EZDCStringItem              xxSetFlg;

    /** DS_CONTR_CATG_CD_OH*/
	public final EZDCStringItem              dsContrCatgCd_OH;

    /** DS_ORD_CATG_DESC_TXT_OH*/
	public final EZDCStringItem              dsOrdCatgDescTxt_OH;

    /** DS_CONTR_NUM_OH*/
	public final EZDCStringItem              dsContrNum_OH;

    /** CONTR_MSG_TXT*/
	public final EZDCStringItem              contrMsgTxt;

    /** DS_CONTR_PK_SP*/
	public final EZDCBigDecimalItem              dsContrPk_SP;

    /** DS_CONTR_DTL_PK_SP*/
	public final EZDCBigDecimalItem              dsContrDtlPk_SP;

    /** XX_OPS_DT_CM*/
	public final EZDCDateItem              xxOpsDt_CM;

    /** SHR_DLR_FLG*/
	public final EZDCStringItem              shrDlrFlg;

    /** IWR_COND_NM*/
	public final EZDCStringItem              iwrCondNm;

    /** IWR_RGTN_STS_CD*/
	public final EZDCStringItem              iwrRgtnStsCd;

    /** IWR_COND_DESC_TXT*/
	public final EZDCStringItem              iwrCondDescTxt;

    /** IWR_RGTN_DT*/
	public final EZDCDateItem              iwrRgtnDt;

    /** IWR_DEINS_DT*/
	public final EZDCDateItem              iwrDeinsDt;

    /** READ_MTR_CNT_CM*/
	public final EZDCBigDecimalItem              readMtrCnt_CM;

    /** IWR_ENBL_FLG*/
	public final EZDCStringItem              iwrEnblFlg;

    /** DS_CONTR_DTL_PK*/
	public final EZDCBigDecimalItem              dsContrDtlPk;

    /** A*/
	public final business.blap.NSAL0150.NSAL0150_ACMsgArray              A;

    /** XX_DPLY_CTRL_FLG*/
	public final EZDCStringItem              xxDplyCtrlFlg;

    /** XX_DPLY_CTRL_FLG_BT*/
	public final EZDCStringItem              xxDplyCtrlFlg_BT;

    /** B*/
	public final business.blap.NSAL0150.NSAL0150_BCMsgArray              B;

    /** DS_MTR_READ_TP_GRP_CD_BS*/
	public final EZDCStringItem              dsMtrReadTpGrpCd_BS;

    /** XX_GENL_FLD_AREA_TXT_BC*/
	public final EZDCStringItemArray              xxGenlFldAreaTxt_BC;

    /** DS_MTR_READ_TP_GRP_CD_BD*/
	public final EZDCStringItemArray              dsMtrReadTpGrpCd_BD;

    /** SVC_TASK_NUM_B*/
	public final EZDCStringItem              svcTaskNum_B;

    /** DS_TEST_COPY_CLS_CD_BS*/
	public final EZDCStringItem              dsTestCopyClsCd_BS;

    /** DS_TEST_COPY_CLS_CD_BC*/
	public final EZDCStringItemArray              dsTestCopyClsCd_BC;

    /** DS_TEST_COPY_CLS_DESC_TXT_BD*/
	public final EZDCStringItemArray              dsTestCopyClsDescTxt_BD;

    /** FSR_NUM_B*/
	public final EZDCStringItem              fsrNum_B;

    /** FSR_VISIT_NUM_B*/
	public final EZDCStringItem              fsrVisitNum_B;

    /** C*/
	public final business.blap.NSAL0150.NSAL0150_CCMsgArray              C;

    /** D*/
	public final business.blap.NSAL0150.NSAL0150_DCMsgArray              D;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** E*/
	public final business.blap.NSAL0150.NSAL0150_ECMsgArray              E;

    /** P*/
	public final business.blap.NSAL0150.NSAL0150_PCMsgArray              P;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** SVC_CMNT_TXT_PP*/
	public final EZDCStringItem              svcCmntTxt_PP;


	/**
	 * NSAL0150CMsg is constructor.
	 * The initialization when the instance of NSAL0150CMsg is generated.
	 */
	public NSAL0150CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0150CMsg is constructor.
	 * The initialization when the instance of NSAL0150CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0150CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachMstrPk = (EZDCBigDecimalItem)newItem("svcMachMstrPk");
		mdseCd = (EZDCStringItem)newItem("mdseCd");
		svcByLineBizTpCd = (EZDCStringItem)newItem("svcByLineBizTpCd");
		sldByLineBizTpCd = (EZDCStringItem)newItem("sldByLineBizTpCd");
		cpoOrdNum = (EZDCStringItem)newItem("cpoOrdNum");
		schdAgmtNum = (EZDCStringItem)newItem("schdAgmtNum");
		billToLocNm = (EZDCStringItem)newItem("billToLocNm");
		shipToLocNm = (EZDCStringItem)newItem("shipToLocNm");
		xxGenlFldAreaTxt_CT = (EZDCStringItem)newItem("xxGenlFldAreaTxt_CT");
		xxGenlFldAreaTxt_FC = (EZDCStringItem)newItem("xxGenlFldAreaTxt_FC");
		xxGenlFldAreaTxt_LS = (EZDCStringItem)newItem("xxGenlFldAreaTxt_LS");
		xxGenlFldAreaTxt_TL = (EZDCStringItem)newItem("xxGenlFldAreaTxt_TL");
		xxGenlFldAreaTxt_FX = (EZDCStringItem)newItem("xxGenlFldAreaTxt_FX");
		dsCtacPntValTxt_EM = (EZDCStringItem)newItem("dsCtacPntValTxt_EM");
		addrline1L3000If = (EZDCStringItem)newItem("addrline1L3000If");
		xxYesNoNm_MC = (EZDCStringItem)newItem("xxYesNoNm_MC");
		bllgSchdThruDt = (EZDCDateItem)newItem("bllgSchdThruDt");
		mtrReadMethDescTxt = (EZDCStringItem)newItem("mtrReadMethDescTxt");
		svcMemoPk = (EZDCBigDecimalItem)newItem("svcMemoPk");
		svcCmntTxt = (EZDCStringItem)newItem("svcCmntTxt");
		svcCmntTxt_HD = (EZDCStringItem)newItem("svcCmntTxt_HD");
		ezUpTime_SM = (EZDCStringItem)newItem("ezUpTime_SM");
		ezUpTimeZone_SM = (EZDCStringItem)newItem("ezUpTimeZone_SM");
		dsContrPk = (EZDCBigDecimalItem)newItem("dsContrPk");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		dsContrCtrlStsNm = (EZDCStringItem)newItem("dsContrCtrlStsNm");
		dsSubContrPk = (EZDCBigDecimalItem)newItem("dsSubContrPk");
		xxCoaBrSrchTxt = (EZDCStringItem)newItem("xxCoaBrSrchTxt");
		prntVndNm = (EZDCStringItem)newItem("prntVndNm");
		splyInclFlg = (EZDCStringItem)newItem("splyInclFlg");
		splyContrChkFlg = (EZDCStringItem)newItem("splyContrChkFlg");
		mdlNm = (EZDCStringItem)newItem("mdlNm");
		serNum = (EZDCStringItem)newItem("serNum");
		istlDt = (EZDCDateItem)newItem("istlDt");
		contrVrsnEffFromDt = (EZDCDateItem)newItem("contrVrsnEffFromDt");
		contrVrsnEffThruDt = (EZDCDateItem)newItem("contrVrsnEffThruDt");
		bllgCycleDescTxt = (EZDCStringItem)newItem("bllgCycleDescTxt");
		dsContrCatgDescTxt = (EZDCStringItem)newItem("dsContrCatgDescTxt");
		dsInvTgtrTpDescTxt = (EZDCStringItem)newItem("dsInvTgtrTpDescTxt");
		svcTermCondDataDispTxt = (EZDCStringItem)newItem("svcTermCondDataDispTxt");
		xxWrnSkipFlg = (EZDCStringItem)newItem("xxWrnSkipFlg");
		xxWrnSkipFlg_MR = (EZDCStringItem)newItem("xxWrnSkipFlg_MR");
		xxSetFlg = (EZDCStringItem)newItem("xxSetFlg");
		dsContrCatgCd_OH = (EZDCStringItem)newItem("dsContrCatgCd_OH");
		dsOrdCatgDescTxt_OH = (EZDCStringItem)newItem("dsOrdCatgDescTxt_OH");
		dsContrNum_OH = (EZDCStringItem)newItem("dsContrNum_OH");
		contrMsgTxt = (EZDCStringItem)newItem("contrMsgTxt");
		dsContrPk_SP = (EZDCBigDecimalItem)newItem("dsContrPk_SP");
		dsContrDtlPk_SP = (EZDCBigDecimalItem)newItem("dsContrDtlPk_SP");
		xxOpsDt_CM = (EZDCDateItem)newItem("xxOpsDt_CM");
		shrDlrFlg = (EZDCStringItem)newItem("shrDlrFlg");
		iwrCondNm = (EZDCStringItem)newItem("iwrCondNm");
		iwrRgtnStsCd = (EZDCStringItem)newItem("iwrRgtnStsCd");
		iwrCondDescTxt = (EZDCStringItem)newItem("iwrCondDescTxt");
		iwrRgtnDt = (EZDCDateItem)newItem("iwrRgtnDt");
		iwrDeinsDt = (EZDCDateItem)newItem("iwrDeinsDt");
		readMtrCnt_CM = (EZDCBigDecimalItem)newItem("readMtrCnt_CM");
		iwrEnblFlg = (EZDCStringItem)newItem("iwrEnblFlg");
		dsContrDtlPk = (EZDCBigDecimalItem)newItem("dsContrDtlPk");
		A = (business.blap.NSAL0150.NSAL0150_ACMsgArray)newMsgArray("A");
		xxDplyCtrlFlg = (EZDCStringItem)newItem("xxDplyCtrlFlg");
		xxDplyCtrlFlg_BT = (EZDCStringItem)newItem("xxDplyCtrlFlg_BT");
		B = (business.blap.NSAL0150.NSAL0150_BCMsgArray)newMsgArray("B");
		dsMtrReadTpGrpCd_BS = (EZDCStringItem)newItem("dsMtrReadTpGrpCd_BS");
		xxGenlFldAreaTxt_BC = (EZDCStringItemArray)newItemArray("xxGenlFldAreaTxt_BC");
		dsMtrReadTpGrpCd_BD = (EZDCStringItemArray)newItemArray("dsMtrReadTpGrpCd_BD");
		svcTaskNum_B = (EZDCStringItem)newItem("svcTaskNum_B");
		dsTestCopyClsCd_BS = (EZDCStringItem)newItem("dsTestCopyClsCd_BS");
		dsTestCopyClsCd_BC = (EZDCStringItemArray)newItemArray("dsTestCopyClsCd_BC");
		dsTestCopyClsDescTxt_BD = (EZDCStringItemArray)newItemArray("dsTestCopyClsDescTxt_BD");
		fsrNum_B = (EZDCStringItem)newItem("fsrNum_B");
		fsrVisitNum_B = (EZDCStringItem)newItem("fsrVisitNum_B");
		C = (business.blap.NSAL0150.NSAL0150_CCMsgArray)newMsgArray("C");
		D = (business.blap.NSAL0150.NSAL0150_DCMsgArray)newMsgArray("D");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		E = (business.blap.NSAL0150.NSAL0150_ECMsgArray)newMsgArray("E");
		P = (business.blap.NSAL0150.NSAL0150_PCMsgArray)newMsgArray("P");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		svcCmntTxt_PP = (EZDCStringItem)newItem("svcCmntTxt_PP");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0150CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0150CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"svcByLineBizTpCd", "svcByLineBizTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sldByLineBizTpCd", "sldByLineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"schdAgmtNum", "schdAgmtNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToLocNm", "billToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"shipToLocNm", "shipToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"xxGenlFldAreaTxt_CT", "xxGenlFldAreaTxt_CT", "CT", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_FC", "xxGenlFldAreaTxt_FC", "FC", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_LS", "xxGenlFldAreaTxt_LS", "LS", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_TL", "xxGenlFldAreaTxt_TL", "TL", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxGenlFldAreaTxt_FX", "xxGenlFldAreaTxt_FX", "FX", null, TYPE_HANKAKUEISU, "1000", null},
	{"dsCtacPntValTxt_EM", "dsCtacPntValTxt_EM", "EM", null, TYPE_HANKAKUEISU, "320", null},
	{"addrline1L3000If", "addrline1L3000If", null, null, TYPE_HANKAKUEISU, "3000", null},
	{"xxYesNoNm_MC", "xxYesNoNm_MC", "MC", null, TYPE_HANKAKUEISU, "3", null},
	{"bllgSchdThruDt", "bllgSchdThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"mtrReadMethDescTxt", "mtrReadMethDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcMemoPk", "svcMemoPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"svcCmntTxt_HD", "svcCmntTxt_HD", "HD", null, TYPE_HANKAKUEISU, "4000", null},
	{"ezUpTime_SM", "ezUpTime_SM", "SM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_SM", "ezUpTimeZone_SM", "SM", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCtrlStsNm", "dsContrCtrlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsSubContrPk", "dsSubContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxCoaBrSrchTxt", "xxCoaBrSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"prntVndNm", "prntVndNm", null, null, TYPE_HANKAKUEISU, "240", null},
	{"splyInclFlg", "splyInclFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"splyContrChkFlg", "splyContrChkFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"istlDt", "istlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffFromDt", "contrVrsnEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt", "contrVrsnEffThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleDescTxt", "bllgCycleDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrCatgDescTxt", "dsContrCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsInvTgtrTpDescTxt", "dsInvTgtrTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataDispTxt", "svcTermCondDataDispTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxWrnSkipFlg_MR", "xxWrnSkipFlg_MR", "MR", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSetFlg", "xxSetFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrCatgCd_OH", "dsContrCatgCd_OH", "OH", null, TYPE_HANKAKUEISU, "3", null},
	{"dsOrdCatgDescTxt_OH", "dsOrdCatgDescTxt_OH", "OH", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrNum_OH", "dsContrNum_OH", "OH", null, TYPE_HANKAKUEISU, "30", null},
	{"contrMsgTxt", "contrMsgTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"dsContrPk_SP", "dsContrPk_SP", "SP", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_SP", "dsContrDtlPk_SP", "SP", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxOpsDt_CM", "xxOpsDt_CM", "CM", null, TYPE_NENTSUKIHI, "8", null},
	{"shrDlrFlg", "shrDlrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"iwrCondNm", "iwrCondNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"iwrRgtnStsCd", "iwrRgtnStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"iwrCondDescTxt", "iwrCondDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"iwrRgtnDt", "iwrRgtnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"iwrDeinsDt", "iwrDeinsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"readMtrCnt_CM", "readMtrCnt_CM", "CM", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"iwrEnblFlg", "iwrEnblFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"A", "A", null, "200", "business.blap.NSAL0150.NSAL0150_ACMsgArray", null, null},
	
	{"xxDplyCtrlFlg", "xxDplyCtrlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_BT", "xxDplyCtrlFlg_BT", "BT", null, TYPE_HANKAKUEISU, "1", null},
	{"B", "B", null, "200", "business.blap.NSAL0150.NSAL0150_BCMsgArray", null, null},
	
	{"dsMtrReadTpGrpCd_BS", "dsMtrReadTpGrpCd_BS", "BS", null, TYPE_HANKAKUEISU, "10", null},
	{"xxGenlFldAreaTxt_BC", "xxGenlFldAreaTxt_BC", "BC", "99", TYPE_HANKAKUEISU, "1000", null},
	{"dsMtrReadTpGrpCd_BD", "dsMtrReadTpGrpCd_BD", "BD", "99", TYPE_HANKAKUEISU, "10", null},
	{"svcTaskNum_B", "svcTaskNum_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	{"dsTestCopyClsCd_BS", "dsTestCopyClsCd_BS", "BS", null, TYPE_HANKAKUEISU, "1", null},
	{"dsTestCopyClsCd_BC", "dsTestCopyClsCd_BC", "BC", "99", TYPE_HANKAKUEISU, "1", null},
	{"dsTestCopyClsDescTxt_BD", "dsTestCopyClsDescTxt_BD", "BD", "99", TYPE_HANKAKUEISU, "50", null},
	{"fsrNum_B", "fsrNum_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	{"fsrVisitNum_B", "fsrVisitNum_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	{"C", "C", null, "200", "business.blap.NSAL0150.NSAL0150_CCMsgArray", null, null},
	
	{"D", "D", null, "200", "business.blap.NSAL0150.NSAL0150_DCMsgArray", null, null},
	
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"E", "E", null, "200", "business.blap.NSAL0150.NSAL0150_ECMsgArray", null, null},
	
	{"P", "P", null, "200", "business.blap.NSAL0150.NSAL0150_PCMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"svcCmntTxt_PP", "svcCmntTxt_PP", "PP", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SVC_BY_LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcByLineBizTpCd
        {"SLD_BY_LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sldByLineBizTpCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"SCHD_AGMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtNum
        {"BILL_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNm
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_CT
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_FC
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_LS
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_TL
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_FX
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_EM
        {"ADDRLINE1_L3000_IF",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addrline1L3000If
        {"XX_YES_NO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoNm_MC
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt
        {"MTR_READ_METH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethDescTxt
        {"SVC_MEMO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoPk
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_HD
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_SM
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_SM
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm
        {"DS_SUB_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSubContrPk
        {"XX_COA_BR_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCoaBrSrchTxt
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm
        {"SPLY_INCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyInclFlg
        {"SPLY_CONTR_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyContrChkFlg
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"ISTL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlDt
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffFromDt
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffThruDt
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt
        {"DS_CONTR_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgDescTxt
        {"DS_INV_TGTR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTgtrTpDescTxt
        {"SVC_TERM_COND_DATA_DISP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataDispTxt
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg_MR
        {"XX_SET_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSetFlg
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_OH
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_OH
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_OH
        {"CONTR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMsgTxt
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_SP
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_SP
        {"XX_OPS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOpsDt_CM
        {"SHR_DLR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shrDlrFlg
        {"IWR_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrCondNm
        {"IWR_RGTN_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrRgtnStsCd
        {"IWR_COND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrCondDescTxt
        {"IWR_RGTN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrRgtnDt
        {"IWR_DEINS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrDeinsDt
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_CM
        {"IWR_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrEnblFlg
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
		null,	//A
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_BT
		null,	//B
        {"DS_MTR_READ_TP_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpGrpCd_BS
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_BC
        {"DS_MTR_READ_TP_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpGrpCd_BD
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_B
        {"DS_TEST_COPY_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTestCopyClsCd_BS
        {"DS_TEST_COPY_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTestCopyClsCd_BC
        {"DS_TEST_COPY_CLS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTestCopyClsDescTxt_BD
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_B
        {"FSR_VISIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitNum_B
		null,	//C
		null,	//D
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
		null,	//E
		null,	//P
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_PP
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
