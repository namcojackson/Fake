//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20181026083903000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3070F01FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLBL3070F01 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3070F01FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG*/
	public final EZDFStringItem              xxSmryLineFlg;

    /** XX_CHK_BOX_1*/
	public final EZDFStringItem              xxChkBox_1;

    /** XX_CHK_BOX_2*/
	public final EZDFStringItem              xxChkBox_2;

    /** XX_SEL_RADIO_BTN_OBJ*/
	public final EZDFStringItem              xxSelRadioBtnObj;

    /** SHIP_SVC_CONFIG_MSTR_PK*/
	public final EZDFBigDecimalItem              shipSvcConfigMstrPk;

    /** CPO_ORD_NUM*/
	public final EZDFStringItem              cpoOrdNum;

    /** DPLY_LINE_NUM*/
	public final EZDFStringItem              dplyLineNum;

    /** SO_NUM*/
	public final EZDFStringItem              soNum;

    /** SO_SLP_NUM*/
	public final EZDFStringItem              soSlpNum;

    /** SCHD_COORD_PSN_NM*/
	public final EZDFStringItem              schdCoordPsnNm;

    /** RTL_WH_NM*/
	public final EZDFStringItem              rtlWhNm;

    /** RTL_SWH_CD*/
	public final EZDFStringItem              rtlSwhCd;

    /** SHIP_TO_ST_CD*/
	public final EZDFStringItem              shipToStCd;

    /** XX_PSN_FIRST_MID_LAST_NM*/
	public final EZDFStringItem              xxPsnFirstMidLastNm;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;

    /** BACK_ORD_IMPCT_TP_DESC_TXT*/
	public final EZDFStringItem              backOrdImpctTpDescTxt;

    /** SHIP_QTY*/
	public final EZDFBigDecimalItem              shipQty;

    /** SER_NUM*/
	public final EZDFStringItem              serNum;

    /** XX_DT_TXT_RD*/
	public final EZDFStringItem              xxDtTxt_RD;

    /** XX_DT_TM_SD*/
	public final EZDFStringItem              xxDtTm_SD;

    /** XX_DT_TM_OR*/
	public final EZDFStringItem              xxDtTm_OR;

    /** XX_DT_TM_PC*/
	public final EZDFStringItem              xxDtTm_PC;

    /** XX_DT_TM_DS*/
	public final EZDFStringItem              xxDtTm_DS;

    /** XX_DT_TM_TA*/
	public final EZDFStringItem              xxDtTm_TA;

    /** XX_DT_TM_DD*/
	public final EZDFStringItem              xxDtTm_DD;

    /** XX_DT_TM_SP*/
	public final EZDFStringItem              xxDtTm_SP;

    /** CARR_RSN_DESC_TXT*/
	public final EZDFStringItem              carrRsnDescTxt;

    /** XX_DT_TM_AD*/
	public final EZDFStringItem              xxDtTm_AD;

    /** XX_DT_TXT_ID*/
	public final EZDFStringItem              xxDtTxt_ID;

    /** SVC_TASK_STS_DESC_TXT*/
	public final EZDFStringItem              svcTaskStsDescTxt;

    /** DS_SO_LINE_STS_DESC_TXT*/
	public final EZDFStringItem              dsSoLineStsDescTxt;

    /** CARR_NM*/
	public final EZDFStringItem              carrNm;

    /** PRO_NUM*/
	public final EZDFStringItem              proNum;

    /** SHPG_SVC_LVL_DESC_TXT*/
	public final EZDFStringItem              shpgSvcLvlDescTxt;

    /** SCHD_COORD_STS_DESC_TXT*/
	public final EZDFStringItem              schdCoordStsDescTxt;

    /** XX_DT_TM_DR*/
	public final EZDFStringItem              xxDtTm_DR;

    /** TOT_FRT_AMT*/
	public final EZDFBigDecimalItem              totFrtAmt;

    /** DS_ACCT_NM*/
	public final EZDFStringItem              dsAcctNm;

    /** SHIP_TO_CTY_ADDR*/
	public final EZDFStringItem              shipToCtyAddr;

    /** PICK_SVC_CONFIG_MSTR_PK*/
	public final EZDFBigDecimalItem              pickSvcConfigMstrPk;

    /** STK_STS_CD*/
	public final EZDFStringItem              stkStsCd;

    /** DS_ORD_CATG_DESC_TXT*/
	public final EZDFStringItem              dsOrdCatgDescTxt;


	/**
	 * NLBL3070F01FMsg is constructor.
	 * The initialization when the instance of NLBL3070F01FMsg is generated.
	 */
	public NLBL3070F01FMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3070F01FMsg is constructor.
	 * The initialization when the instance of NLBL3070F01FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3070F01FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg = (EZDFStringItem)newItem("xxSmryLineFlg");
		xxChkBox_1 = (EZDFStringItem)newItem("xxChkBox_1");
		xxChkBox_2 = (EZDFStringItem)newItem("xxChkBox_2");
		xxSelRadioBtnObj = (EZDFStringItem)newItem("xxSelRadioBtnObj");
		shipSvcConfigMstrPk = (EZDFBigDecimalItem)newItem("shipSvcConfigMstrPk");
		cpoOrdNum = (EZDFStringItem)newItem("cpoOrdNum");
		dplyLineNum = (EZDFStringItem)newItem("dplyLineNum");
		soNum = (EZDFStringItem)newItem("soNum");
		soSlpNum = (EZDFStringItem)newItem("soSlpNum");
		schdCoordPsnNm = (EZDFStringItem)newItem("schdCoordPsnNm");
		rtlWhNm = (EZDFStringItem)newItem("rtlWhNm");
		rtlSwhCd = (EZDFStringItem)newItem("rtlSwhCd");
		shipToStCd = (EZDFStringItem)newItem("shipToStCd");
		xxPsnFirstMidLastNm = (EZDFStringItem)newItem("xxPsnFirstMidLastNm");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		backOrdImpctTpDescTxt = (EZDFStringItem)newItem("backOrdImpctTpDescTxt");
		shipQty = (EZDFBigDecimalItem)newItem("shipQty");
		serNum = (EZDFStringItem)newItem("serNum");
		xxDtTxt_RD = (EZDFStringItem)newItem("xxDtTxt_RD");
		xxDtTm_SD = (EZDFStringItem)newItem("xxDtTm_SD");
		xxDtTm_OR = (EZDFStringItem)newItem("xxDtTm_OR");
		xxDtTm_PC = (EZDFStringItem)newItem("xxDtTm_PC");
		xxDtTm_DS = (EZDFStringItem)newItem("xxDtTm_DS");
		xxDtTm_TA = (EZDFStringItem)newItem("xxDtTm_TA");
		xxDtTm_DD = (EZDFStringItem)newItem("xxDtTm_DD");
		xxDtTm_SP = (EZDFStringItem)newItem("xxDtTm_SP");
		carrRsnDescTxt = (EZDFStringItem)newItem("carrRsnDescTxt");
		xxDtTm_AD = (EZDFStringItem)newItem("xxDtTm_AD");
		xxDtTxt_ID = (EZDFStringItem)newItem("xxDtTxt_ID");
		svcTaskStsDescTxt = (EZDFStringItem)newItem("svcTaskStsDescTxt");
		dsSoLineStsDescTxt = (EZDFStringItem)newItem("dsSoLineStsDescTxt");
		carrNm = (EZDFStringItem)newItem("carrNm");
		proNum = (EZDFStringItem)newItem("proNum");
		shpgSvcLvlDescTxt = (EZDFStringItem)newItem("shpgSvcLvlDescTxt");
		schdCoordStsDescTxt = (EZDFStringItem)newItem("schdCoordStsDescTxt");
		xxDtTm_DR = (EZDFStringItem)newItem("xxDtTm_DR");
		totFrtAmt = (EZDFBigDecimalItem)newItem("totFrtAmt");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		shipToCtyAddr = (EZDFStringItem)newItem("shipToCtyAddr");
		pickSvcConfigMstrPk = (EZDFBigDecimalItem)newItem("pickSvcConfigMstrPk");
		stkStsCd = (EZDFStringItem)newItem("stkStsCd");
		dsOrdCatgDescTxt = (EZDFStringItem)newItem("dsOrdCatgDescTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3070F01FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3070F01FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg", "xxSmryLineFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_1", "xxChkBox_1", "1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_2", "xxChkBox_2", "2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxSelRadioBtnObj", "xxSelRadioBtnObj", null, null, TYPE_HANKAKUEISU, "10", null},
	{"shipSvcConfigMstrPk", "shipSvcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum", "dplyLineNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"soSlpNum", "soSlpNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"schdCoordPsnNm", "schdCoordPsnNm", null, null, TYPE_HANKAKUEISU, "90", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToStCd", "shipToStCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxPsnFirstMidLastNm", "xxPsnFirstMidLastNm", null, null, TYPE_HANKAKUEISU, "90", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"backOrdImpctTpDescTxt", "backOrdImpctTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"shipQty", "shipQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtTxt_RD", "xxDtTxt_RD", "RD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTm_SD", "xxDtTm_SD", "SD", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_OR", "xxDtTm_OR", "OR", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_PC", "xxDtTm_PC", "PC", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_DS", "xxDtTm_DS", "DS", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_TA", "xxDtTm_TA", "TA", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_DD", "xxDtTm_DD", "DD", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_SP", "xxDtTm_SP", "SP", null, TYPE_HANKAKUEISU, "23", null},
	{"carrRsnDescTxt", "carrRsnDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_AD", "xxDtTm_AD", "AD", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTxt_ID", "xxDtTxt_ID", "ID", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskStsDescTxt", "svcTaskStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsSoLineStsDescTxt", "dsSoLineStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"carrNm", "carrNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"proNum", "proNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"shpgSvcLvlDescTxt", "shpgSvcLvlDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"schdCoordStsDescTxt", "schdCoordStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_DR", "xxDtTm_DR", "DR", null, TYPE_HANKAKUEISU, "23", null},
	{"totFrtAmt", "totFrtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"shipToCtyAddr", "shipToCtyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"pickSvcConfigMstrPk", "pickSvcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"stkStsCd", "stkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdCatgDescTxt", "dsOrdCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_2
        {"XX_SEL_RADIO_BTN_OBJ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelRadioBtnObj
        {"SHIP_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipSvcConfigMstrPk
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum
        {"SCHD_COORD_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordPsnNm
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"SHIP_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd
        {"XX_PSN_FIRST_MID_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnFirstMidLastNm
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"BACK_ORD_IMPCT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpDescTxt
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_RD
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_SD
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_OR
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_PC
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_DS
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_TA
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_DD
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_SP
        {"CARR_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrRsnDescTxt
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_ID
        {"SVC_TASK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsDescTxt
        {"DS_SO_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSoLineStsDescTxt
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum
        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt
        {"SCHD_COORD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordStsDescTxt
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_DR
        {"TOT_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFrtAmt
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"SHIP_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtyAddr
        {"PICK_SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickSvcConfigMstrPk
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt
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
