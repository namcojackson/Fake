//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191129153436000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1380BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1380;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1380 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1380BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SHELL_LINE_NUM*/
	public final EZDBBigDecimalItem              shellLineNum;

    /** SVC_PGM_MDSE_CD*/
	public final EZDBStringItem              svcPgmMdseCd;

    /** TERM_MTH_AOT*/
	public final EZDBBigDecimalItem              termMthAot;

    /** SPLY_BASE_AMT*/
	public final EZDBBigDecimalItem              splyBaseAmt;

    /** SPLY_BASE_AMT_H*/
	public final EZDBBigDecimalItem              splyBaseAmt_H;

    /** SPLY_AGMT_PLN_PK*/
	public final EZDBBigDecimalItem              splyAgmtPlnPk;

    /** SPLY_AGMT_PLN_NM*/
	public final EZDBStringItem              splyAgmtPlnNm;

    /** SPLY_AGMT_PLN_SHORT_NM*/
	public final EZDBStringItem              splyAgmtPlnShortNm;

    /** SPLY_AGMT_PLN_DESC_TXT*/
	public final EZDBStringItem              splyAgmtPlnDescTxt;

    /** SPLY_AGMT_PLN_TP_CD*/
	public final EZDBStringItem              splyAgmtPlnTpCd;

    /** SPLY_AGMT_PLN_TP_DESC_TXT*/
	public final EZDBStringItem              splyAgmtPlnTpDescTxt;

    /** SPLY_AGMT_DOC_TP_CD*/
	public final EZDBStringItem              splyAgmtDocTpCd;

    /** SPLY_AGMT_DOC_TP_DESC_TXT*/
	public final EZDBStringItem              splyAgmtDocTpDescTxt;

    /** ANN_TERM_CAP_NUM*/
	public final EZDBBigDecimalItem              annTermCapNum;

    /** QTY_CONTR_CAP_QTY*/
	public final EZDBBigDecimalItem              qtyContrCapQty;

    /** QTY_CONTR_CAP_QTY_H*/
	public final EZDBBigDecimalItem              qtyContrCapQty_H;

    /** MDL_ID*/
	public final EZDBBigDecimalItem              mdlId;

    /** PRC_MTR_PKG_PK*/
	public final EZDBBigDecimalItem              prcMtrPkgPk;

    /** PRC_SVC_PLN_TP_CD*/
	public final EZDBStringItem              prcSvcPlnTpCd;

    /** PRC_SVC_CONTR_TP_CD*/
	public final EZDBStringItem              prcSvcContrTpCd;

    /** BLLG_MTR_LB_CD*/
	public final EZDBStringItem              bllgMtrLbCd;

    /** PRC_LIST_BAND_DESC_TXT*/
	public final EZDBStringItem              prcListBandDescTxt;

    /** PRC_CATG_CD*/
	public final EZDBStringItem              prcCatgCd;

    /** PRC_LIST_BAND_CD*/
	public final EZDBStringItem              prcListBandCd;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** SCHD_CRAT_TMPL_PK*/
	public final EZDBBigDecimalItem              schdCratTmplPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDBBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_DTL_GRP_NUM*/
	public final EZDBBigDecimalItem              dsContrDtlGrpNum;

    /** REF_CPO_ORD_NUM*/
	public final EZDBStringItem              refCpoOrdNum;

    /** SHPG_INTVL_CD_P*/
	public final EZDBStringItemArray              shpgIntvlCd_P;

    /** SHPG_INTVL_DESC_TXT_P*/
	public final EZDBStringItemArray              shpgIntvlDescTxt_P;

    /** SCR_ENT_AVAL_FLG*/
	public final EZDBStringItem              scrEntAvalFlg;

    /** A*/
	public final business.servlet.NSAL1380.NSAL1380_ABMsgArray              A;


	/**
	 * NSAL1380BMsg is constructor.
	 * The initialization when the instance of NSAL1380BMsg is generated.
	 */
	public NSAL1380BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1380BMsg is constructor.
	 * The initialization when the instance of NSAL1380BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1380BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		shellLineNum = (EZDBBigDecimalItem)newItem("shellLineNum");
		svcPgmMdseCd = (EZDBStringItem)newItem("svcPgmMdseCd");
		termMthAot = (EZDBBigDecimalItem)newItem("termMthAot");
		splyBaseAmt = (EZDBBigDecimalItem)newItem("splyBaseAmt");
		splyBaseAmt_H = (EZDBBigDecimalItem)newItem("splyBaseAmt_H");
		splyAgmtPlnPk = (EZDBBigDecimalItem)newItem("splyAgmtPlnPk");
		splyAgmtPlnNm = (EZDBStringItem)newItem("splyAgmtPlnNm");
		splyAgmtPlnShortNm = (EZDBStringItem)newItem("splyAgmtPlnShortNm");
		splyAgmtPlnDescTxt = (EZDBStringItem)newItem("splyAgmtPlnDescTxt");
		splyAgmtPlnTpCd = (EZDBStringItem)newItem("splyAgmtPlnTpCd");
		splyAgmtPlnTpDescTxt = (EZDBStringItem)newItem("splyAgmtPlnTpDescTxt");
		splyAgmtDocTpCd = (EZDBStringItem)newItem("splyAgmtDocTpCd");
		splyAgmtDocTpDescTxt = (EZDBStringItem)newItem("splyAgmtDocTpDescTxt");
		annTermCapNum = (EZDBBigDecimalItem)newItem("annTermCapNum");
		qtyContrCapQty = (EZDBBigDecimalItem)newItem("qtyContrCapQty");
		qtyContrCapQty_H = (EZDBBigDecimalItem)newItem("qtyContrCapQty_H");
		mdlId = (EZDBBigDecimalItem)newItem("mdlId");
		prcMtrPkgPk = (EZDBBigDecimalItem)newItem("prcMtrPkgPk");
		prcSvcPlnTpCd = (EZDBStringItem)newItem("prcSvcPlnTpCd");
		prcSvcContrTpCd = (EZDBStringItem)newItem("prcSvcContrTpCd");
		bllgMtrLbCd = (EZDBStringItem)newItem("bllgMtrLbCd");
		prcListBandDescTxt = (EZDBStringItem)newItem("prcListBandDescTxt");
		prcCatgCd = (EZDBStringItem)newItem("prcCatgCd");
		prcListBandCd = (EZDBStringItem)newItem("prcListBandCd");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		schdCratTmplPk = (EZDBBigDecimalItem)newItem("schdCratTmplPk");
		dsContrDtlPk = (EZDBBigDecimalItem)newItem("dsContrDtlPk");
		dsContrDtlGrpNum = (EZDBBigDecimalItem)newItem("dsContrDtlGrpNum");
		refCpoOrdNum = (EZDBStringItem)newItem("refCpoOrdNum");
		shpgIntvlCd_P = (EZDBStringItemArray)newItemArray("shpgIntvlCd_P");
		shpgIntvlDescTxt_P = (EZDBStringItemArray)newItemArray("shpgIntvlDescTxt_P");
		scrEntAvalFlg = (EZDBStringItem)newItem("scrEntAvalFlg");
		A = (business.servlet.NSAL1380.NSAL1380_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1380BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1380BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"shellLineNum", "shellLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"svcPgmMdseCd", "svcPgmMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"termMthAot", "termMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"splyBaseAmt", "splyBaseAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"splyBaseAmt_H", "splyBaseAmt_H", "H", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"splyAgmtPlnPk", "splyAgmtPlnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyAgmtPlnNm", "splyAgmtPlnNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnShortNm", "splyAgmtPlnShortNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnDescTxt", "splyAgmtPlnDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnTpCd", "splyAgmtPlnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnTpDescTxt", "splyAgmtPlnTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtDocTpCd", "splyAgmtDocTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtDocTpDescTxt", "splyAgmtDocTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"annTermCapNum", "annTermCapNum", null, null, TYPE_SEISU_SYOSU, "11", "0"},
	{"qtyContrCapQty", "qtyContrCapQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"qtyContrCapQty_H", "qtyContrCapQty_H", "H", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"prcMtrPkgPk", "prcMtrPkgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcSvcPlnTpCd", "prcSvcPlnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcContrTpCd", "prcSvcContrTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcListBandDescTxt", "prcListBandDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcListBandCd", "prcListBandCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"schdCratTmplPk", "schdCratTmplPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlGrpNum", "dsContrDtlGrpNum", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"refCpoOrdNum", "refCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"shpgIntvlCd_P", "shpgIntvlCd_P", "P", "99", TYPE_HANKAKUEISU, "10", null},
	{"shpgIntvlDescTxt_P", "shpgIntvlDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"scrEntAvalFlg", "scrEntAvalFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "200", "business.servlet.NSAL1380.NSAL1380_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SHELL_LINE_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shellLineNum
        {"SVC_PGM_MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd
        {"TERM_MTH_AOT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termMthAot
        {"SPLY_BASE_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyBaseAmt
        {"SPLY_BASE_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyBaseAmt_H
        {"SPLY_AGMT_PLN_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnPk
        {"SPLY_AGMT_PLN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnNm
        {"SPLY_AGMT_PLN_SHORT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnShortNm
        {"SPLY_AGMT_PLN_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnDescTxt
        {"SPLY_AGMT_PLN_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpCd
        {"SPLY_AGMT_PLN_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpDescTxt
        {"SPLY_AGMT_DOC_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd
        {"SPLY_AGMT_DOC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpDescTxt
        {"ANN_TERM_CAP_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//annTermCapNum
        {"QTY_CONTR_CAP_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyContrCapQty
        {"QTY_CONTR_CAP_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyContrCapQty_H
        {"MDL_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"PRC_MTR_PKG_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd
        {"BLLG_MTR_LB_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt
        {"PRC_CATG_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"PRC_LIST_BAND_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandCd
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"SCHD_CRAT_TMPL_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCratTmplPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_DTL_GRP_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlGrpNum
        {"REF_CPO_ORD_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//refCpoOrdNum
        {"SHPG_INTVL_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlCd_P
        {"SHPG_INTVL_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlDescTxt_P
        {"SCR_ENT_AVAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrEntAvalFlg
		null,	//A
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
