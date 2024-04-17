//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230414084049000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0290CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0290 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0290CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM*/
	public final EZDCBigDecimalItem              xxNum;

    /** XX_FILE_DATA_UP*/
	public final EZDCMimeSourceItem              xxFileData_UP;

    /** ADJ_TRX_TP_CD_H*/
	public final EZDCStringItem              adjTrxTpCd_H;

    /** ADJ_TRX_TP_CD_LC*/
	public final EZDCStringItemArray              adjTrxTpCd_LC;

    /** ADJ_TRX_TP_DESC_TXT_LD*/
	public final EZDCStringItemArray              adjTrxTpDescTxt_LD;

    /** LOC_STS_CD_H*/
	public final EZDCStringItem              locStsCd_H;

    /** STK_STS_CD_LC*/
	public final EZDCStringItemArray              stkStsCd_LC;

    /** STK_STS_DESC_TXT_LD*/
	public final EZDCStringItemArray              stkStsDescTxt_LD;

    /** RTL_WH_CD_HL*/
	public final EZDCStringItem              rtlWhCd_HL;

    /** RTL_WH_CD_H*/
	public final EZDCStringItem              rtlWhCd_H;

    /** RTL_WH_NM_H*/
	public final EZDCStringItem              rtlWhNm_H;

    /** INVTY_ORD_NUM_H*/
	public final EZDCStringItem              invtyOrdNum_H;

    /** INVTY_ORD_NUM_HD*/
	public final EZDCStringItem              invtyOrdNum_HD;

    /** INVTY_LOC_CD_H*/
	public final EZDCStringItem              invtyLocCd_H;

    /** ACCT_ALIAS_AVAL_FLG_H*/
	public final EZDCStringItem              acctAliasAvalFlg_H;

    /** ACCT_REQ_FLG_H*/
	public final EZDCStringItem              acctReqFlg_H;

    /** DEST_SWH_REQ_FLG_H*/
	public final EZDCStringItem              destSwhReqFlg_H;

    /** ADJ_QTY_INCR_FLG_H*/
	public final EZDCStringItem              adjQtyIncrFlg_H;

    /** ADJ_QTY_DECR_FLG_H*/
	public final EZDCStringItem              adjQtyDecrFlg_H;

    /** TRX_CD_H*/
	public final EZDCStringItem              trxCd_H;

    /** TRX_RSN_CD_H*/
	public final EZDCStringItem              trxRsnCd_H;

    /** INVTY_ORD_TP_CD_H*/
	public final EZDCStringItem              invtyOrdTpCd_H;

    /** COA_CMPY_CD_H*/
	public final EZDCStringItem              coaCmpyCd_H;

    /** COA_BR_CD_H*/
	public final EZDCStringItem              coaBrCd_H;

    /** COA_ACCT_CD_H*/
	public final EZDCStringItem              coaAcctCd_H;

    /** COA_PROD_CD_H*/
	public final EZDCStringItem              coaProdCd_H;

    /** COA_CH_CD_H*/
	public final EZDCStringItem              coaChCd_H;

    /** COA_CC_CD_H*/
	public final EZDCStringItem              coaCcCd_H;

    /** COA_AFFL_CD_H*/
	public final EZDCStringItem              coaAfflCd_H;

    /** COA_EXTN_CD_H*/
	public final EZDCStringItem              coaExtnCd_H;

    /** COA_PROJ_CD_H*/
	public final EZDCStringItem              coaProjCd_H;

    /** COA_CMPY_CD_HG*/
	public final EZDCStringItem              coaCmpyCd_HG;

    /** COA_BR_CD_HG*/
	public final EZDCStringItem              coaBrCd_HG;

    /** COA_ACCT_CD_HG*/
	public final EZDCStringItem              coaAcctCd_HG;

    /** COA_PROD_CD_HG*/
	public final EZDCStringItem              coaProdCd_HG;

    /** COA_CH_CD_HG*/
	public final EZDCStringItem              coaChCd_HG;

    /** COA_CC_CD_HG*/
	public final EZDCStringItem              coaCcCd_HG;

    /** COA_AFFL_CD_HG*/
	public final EZDCStringItem              coaAfflCd_HG;

    /** COA_EXTN_CD_HG*/
	public final EZDCStringItem              coaExtnCd_HG;

    /** COA_PROJ_CD_HG*/
	public final EZDCStringItem              coaProjCd_HG;

    /** COA_ENBL_FLG_H*/
	public final EZDCStringItem              coaEnblFlg_H;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A;

    /** SVC_CONFIG_MSTR_PK_HL*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_HL;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDCBigDecimalItem              svcConfigMstrPk;

    /** RTL_SWH_CD_LC*/
	public final EZDCStringItemArray              rtlSwhCd_LC;

    /** RTL_SWH_CD_LD*/
	public final EZDCStringItemArray              rtlSwhCd_LD;

    /** ADJ_CATG_CD_LC*/
	public final EZDCStringItemArray              adjCatgCd_LC;

    /** ADJ_CATG_DESC_TXT_LD*/
	public final EZDCStringItemArray              adjCatgDescTxt_LD;

    /** A*/
	public final business.blap.NLCL0290.NLCL0290_ACMsgArray              A;

    /** XX_WRN_SKIP_FLG_A*/
	public final EZDCStringItem              xxWrnSkipFlg_A;

    /** X*/
	public final business.blap.NLCL0290.NLCL0290_XCMsgArray              X;

    /** XX_MSG_PRM_TXT*/
	public final EZDCStringItem              xxMsgPrmTxt;


	/**
	 * NLCL0290CMsg is constructor.
	 * The initialization when the instance of NLCL0290CMsg is generated.
	 */
	public NLCL0290CMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0290CMsg is constructor.
	 * The initialization when the instance of NLCL0290CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0290CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum = (EZDCBigDecimalItem)newItem("xxNum");
		xxFileData_UP = (EZDCMimeSourceItem)newItem("xxFileData_UP");
		adjTrxTpCd_H = (EZDCStringItem)newItem("adjTrxTpCd_H");
		adjTrxTpCd_LC = (EZDCStringItemArray)newItemArray("adjTrxTpCd_LC");
		adjTrxTpDescTxt_LD = (EZDCStringItemArray)newItemArray("adjTrxTpDescTxt_LD");
		locStsCd_H = (EZDCStringItem)newItem("locStsCd_H");
		stkStsCd_LC = (EZDCStringItemArray)newItemArray("stkStsCd_LC");
		stkStsDescTxt_LD = (EZDCStringItemArray)newItemArray("stkStsDescTxt_LD");
		rtlWhCd_HL = (EZDCStringItem)newItem("rtlWhCd_HL");
		rtlWhCd_H = (EZDCStringItem)newItem("rtlWhCd_H");
		rtlWhNm_H = (EZDCStringItem)newItem("rtlWhNm_H");
		invtyOrdNum_H = (EZDCStringItem)newItem("invtyOrdNum_H");
		invtyOrdNum_HD = (EZDCStringItem)newItem("invtyOrdNum_HD");
		invtyLocCd_H = (EZDCStringItem)newItem("invtyLocCd_H");
		acctAliasAvalFlg_H = (EZDCStringItem)newItem("acctAliasAvalFlg_H");
		acctReqFlg_H = (EZDCStringItem)newItem("acctReqFlg_H");
		destSwhReqFlg_H = (EZDCStringItem)newItem("destSwhReqFlg_H");
		adjQtyIncrFlg_H = (EZDCStringItem)newItem("adjQtyIncrFlg_H");
		adjQtyDecrFlg_H = (EZDCStringItem)newItem("adjQtyDecrFlg_H");
		trxCd_H = (EZDCStringItem)newItem("trxCd_H");
		trxRsnCd_H = (EZDCStringItem)newItem("trxRsnCd_H");
		invtyOrdTpCd_H = (EZDCStringItem)newItem("invtyOrdTpCd_H");
		coaCmpyCd_H = (EZDCStringItem)newItem("coaCmpyCd_H");
		coaBrCd_H = (EZDCStringItem)newItem("coaBrCd_H");
		coaAcctCd_H = (EZDCStringItem)newItem("coaAcctCd_H");
		coaProdCd_H = (EZDCStringItem)newItem("coaProdCd_H");
		coaChCd_H = (EZDCStringItem)newItem("coaChCd_H");
		coaCcCd_H = (EZDCStringItem)newItem("coaCcCd_H");
		coaAfflCd_H = (EZDCStringItem)newItem("coaAfflCd_H");
		coaExtnCd_H = (EZDCStringItem)newItem("coaExtnCd_H");
		coaProjCd_H = (EZDCStringItem)newItem("coaProjCd_H");
		coaCmpyCd_HG = (EZDCStringItem)newItem("coaCmpyCd_HG");
		coaBrCd_HG = (EZDCStringItem)newItem("coaBrCd_HG");
		coaAcctCd_HG = (EZDCStringItem)newItem("coaAcctCd_HG");
		coaProdCd_HG = (EZDCStringItem)newItem("coaProdCd_HG");
		coaChCd_HG = (EZDCStringItem)newItem("coaChCd_HG");
		coaCcCd_HG = (EZDCStringItem)newItem("coaCcCd_HG");
		coaAfflCd_HG = (EZDCStringItem)newItem("coaAfflCd_HG");
		coaExtnCd_HG = (EZDCStringItem)newItem("coaExtnCd_HG");
		coaProjCd_HG = (EZDCStringItem)newItem("coaProjCd_HG");
		coaEnblFlg_H = (EZDCStringItem)newItem("coaEnblFlg_H");
		xxPageShowFromNum_A = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A");
		svcConfigMstrPk_HL = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_HL");
		svcConfigMstrPk = (EZDCBigDecimalItem)newItem("svcConfigMstrPk");
		rtlSwhCd_LC = (EZDCStringItemArray)newItemArray("rtlSwhCd_LC");
		rtlSwhCd_LD = (EZDCStringItemArray)newItemArray("rtlSwhCd_LD");
		adjCatgCd_LC = (EZDCStringItemArray)newItemArray("adjCatgCd_LC");
		adjCatgDescTxt_LD = (EZDCStringItemArray)newItemArray("adjCatgDescTxt_LD");
		A = (business.blap.NLCL0290.NLCL0290_ACMsgArray)newMsgArray("A");
		xxWrnSkipFlg_A = (EZDCStringItem)newItem("xxWrnSkipFlg_A");
		X = (business.blap.NLCL0290.NLCL0290_XCMsgArray)newMsgArray("X");
		xxMsgPrmTxt = (EZDCStringItem)newItem("xxMsgPrmTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0290CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0290CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData_UP", "xxFileData_UP", "UP", null, TYPE_UPLOAD, null, null},
	{"adjTrxTpCd_H", "adjTrxTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"adjTrxTpCd_LC", "adjTrxTpCd_LC", "LC", "99", TYPE_HANKAKUEISU, "2", null},
	{"adjTrxTpDescTxt_LD", "adjTrxTpDescTxt_LD", "LD", "99", TYPE_HANKAKUEISU, "50", null},
	{"locStsCd_H", "locStsCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"stkStsCd_LC", "stkStsCd_LC", "LC", "99", TYPE_HANKAKUEISU, "1", null},
	{"stkStsDescTxt_LD", "stkStsDescTxt_LD", "LD", "99", TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd_HL", "rtlWhCd_HL", "HL", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_H", "rtlWhCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_H", "rtlWhNm_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"invtyOrdNum_H", "invtyOrdNum_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"invtyOrdNum_HD", "invtyOrdNum_HD", "HD", null, TYPE_HANKAKUEISU, "8", null},
	{"invtyLocCd_H", "invtyLocCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"acctAliasAvalFlg_H", "acctAliasAvalFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"acctReqFlg_H", "acctReqFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"destSwhReqFlg_H", "destSwhReqFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"adjQtyIncrFlg_H", "adjQtyIncrFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"adjQtyDecrFlg_H", "adjQtyDecrFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"trxCd_H", "trxCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"trxRsnCd_H", "trxRsnCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"invtyOrdTpCd_H", "invtyOrdTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"coaCmpyCd_H", "coaCmpyCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_H", "coaBrCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"coaAcctCd_H", "coaAcctCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_H", "coaProdCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"coaChCd_H", "coaChCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_H", "coaCcCd_H", "H", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAfflCd_H", "coaAfflCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_H", "coaExtnCd_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"coaProjCd_H", "coaProjCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"coaCmpyCd_HG", "coaCmpyCd_HG", "HG", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_HG", "coaBrCd_HG", "HG", null, TYPE_HANKAKUEISU, "3", null},
	{"coaAcctCd_HG", "coaAcctCd_HG", "HG", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_HG", "coaProdCd_HG", "HG", null, TYPE_HANKAKUEISU, "8", null},
	{"coaChCd_HG", "coaChCd_HG", "HG", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_HG", "coaCcCd_HG", "HG", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAfflCd_HG", "coaAfflCd_HG", "HG", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_HG", "coaExtnCd_HG", "HG", null, TYPE_HANKAKUEISU, "3", null},
	{"coaProjCd_HG", "coaProjCd_HG", "HG", null, TYPE_HANKAKUEISU, "4", null},
	{"coaEnblFlg_H", "coaEnblFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"svcConfigMstrPk_HL", "svcConfigMstrPk_HL", "HL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rtlSwhCd_LC", "rtlSwhCd_LC", "LC", "99", TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_LD", "rtlSwhCd_LD", "LD", "99", TYPE_HANKAKUEISU, "20", null},
	{"adjCatgCd_LC", "adjCatgCd_LC", "LC", "99", TYPE_HANKAKUEISU, "2", null},
	{"adjCatgDescTxt_LD", "adjCatgDescTxt_LD", "LD", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "40", "business.blap.NLCL0290.NLCL0290_ACMsgArray", null, null},
	
	{"xxWrnSkipFlg_A", "xxWrnSkipFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"X", "X", null, "30", "business.blap.NLCL0290.NLCL0290_XCMsgArray", null, null},
	
	{"xxMsgPrmTxt", "xxMsgPrmTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_UP
        {"ADJ_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjTrxTpCd_H
        {"ADJ_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjTrxTpCd_LC
        {"ADJ_TRX_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjTrxTpDescTxt_LD
        {"LOC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locStsCd_H
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_LC
        {"STK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt_LD
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_HL
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H
        {"INVTY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdNum_H
        {"INVTY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdNum_HD
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_H
        {"ACCT_ALIAS_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctAliasAvalFlg_H
        {"ACCT_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctReqFlg_H
        {"DEST_SWH_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destSwhReqFlg_H
        {"ADJ_QTY_INCR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjQtyIncrFlg_H
        {"ADJ_QTY_DECR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjQtyDecrFlg_H
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_H
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_H
        {"INVTY_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdTpCd_H
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_H
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_H
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_H
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_H
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_H
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_H
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_H
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_H
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_H
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_HG
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_HG
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_HG
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_HG
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_HG
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_HG
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_HG
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_HG
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_HG
        {"COA_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaEnblFlg_H
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_HL
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_LC
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_LD
        {"ADJ_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCatgCd_LC
        {"ADJ_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCatgDescTxt_LD
		null,	//A
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg_A
		null,	//X
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt
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

