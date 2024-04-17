//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230406162516000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0280_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0280;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0280 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0280_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RTL_WH_CD_A1*/
	public final EZDSStringItem              rtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDSStringItem              rtlWhNm_A1;

    /** RTL_SWH_CD_A1*/
	public final EZDSStringItem              rtlSwhCd_A1;

    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDSStringItem              mdseDescShortTxt_A1;

    /** XX_SCR_ITEM_61_TXT_PJ*/
	public final EZDSStringItem              xxScrItem61Txt_PJ;

    /** XX_SCR_ITEM_61_TXT_MD*/
	public final EZDSStringItem              xxScrItem61Txt_MD;

    /** XX_SCR_ITEM_61_TXT_PD*/
	public final EZDSStringItem              xxScrItem61Txt_PD;

    /** XX_LINK_ANCR_FI*/
	public final EZDSStringItem              xxLinkAncr_FI;

    /** XX_RELN_TRGT_FLG_A1*/
	public final EZDSStringItem              xxRelnTrgtFlg_A1;

    /** SPLY_ITEM_NUM_A1*/
	public final EZDSStringItem              splyItemNum_A1;

    /** MNF_ITEM_CD_A1*/
	public final EZDSStringItem              mnfItemCd_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_A1;

    /** T_MDL_NM_A1*/
	public final EZDSStringItem              t_MdlNm_A1;

    /** XX_LINK_ANCR_SE*/
	public final EZDSStringItem              xxLinkAncr_SE;

    /** SER_NUM_FLG_A1*/
	public final EZDSStringItem              serNumFlg_A1;

    /** INVTY_TRX_PK_A1*/
	public final EZDSBigDecimalItem              invtyTrxPk_A1;

    /** INVTY_TRX_TP_DESC_TXT_A1*/
	public final EZDSStringItem              invtyTrxTpDescTxt_A1;

    /** XX_SCR_ITEM_61_TXT_AX*/
	public final EZDSStringItem              xxScrItem61Txt_AX;

    /** XX_SCR_ITEM_61_TXT_AR*/
	public final EZDSStringItem              xxScrItem61Txt_AR;

    /** INVTY_TRX_QTY_A1*/
	public final EZDSBigDecimalItem              invtyTrxQty_A1;

    /** XX_SCR_ITEM_61_TXT_SS*/
	public final EZDSStringItem              xxScrItem61Txt_SS;

    /** XX_SCR_ITEM_61_TXT_AS*/
	public final EZDSStringItem              xxScrItem61Txt_AS;

    /** INVTY_TRX_SLP_NUM_A1*/
	public final EZDSStringItem              invtyTrxSlpNum_A1;

    /** DPLY_LINE_NUM_A1*/
	public final EZDSStringItem              dplyLineNum_A1;

    /** SO_NUM_A1*/
	public final EZDSStringItem              soNum_A1;

    /** SO_SLP_NUM_A1*/
	public final EZDSStringItem              soSlpNum_A1;

    /** RWS_NUM_A1*/
	public final EZDSStringItem              rwsNum_A1;

    /** RWS_DTL_LINE_NUM_A1*/
	public final EZDSStringItem              rwsDtlLineNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDSStringItem              dsAcctNm_A1;

    /** XX_TS_DSP_19_TXT_A1*/
	public final EZDSStringItem              xxTsDsp19Txt_A1;

    /** SHIP_TO_LOC_CUST_NM_A1*/
	public final EZDSStringItem              shipToLocCustNm_A1;

    /** AJE_LINK_FLG_A1*/
	public final EZDSStringItem              ajeLinkFlg_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDSStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDSStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDSStringItem              xxRecHistTblNm_A1;


	/**
	 * NLCL0280_ASMsg is constructor.
	 * The initialization when the instance of NLCL0280_ASMsg is generated.
	 */
	public NLCL0280_ASMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0280_ASMsg is constructor.
	 * The initialization when the instance of NLCL0280_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0280_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rtlWhCd_A1 = (EZDSStringItem)newItem("rtlWhCd_A1");
		rtlWhNm_A1 = (EZDSStringItem)newItem("rtlWhNm_A1");
		rtlSwhCd_A1 = (EZDSStringItem)newItem("rtlSwhCd_A1");
		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDSStringItem)newItem("mdseDescShortTxt_A1");
		xxScrItem61Txt_PJ = (EZDSStringItem)newItem("xxScrItem61Txt_PJ");
		xxScrItem61Txt_MD = (EZDSStringItem)newItem("xxScrItem61Txt_MD");
		xxScrItem61Txt_PD = (EZDSStringItem)newItem("xxScrItem61Txt_PD");
		xxLinkAncr_FI = (EZDSStringItem)newItem("xxLinkAncr_FI");
		xxRelnTrgtFlg_A1 = (EZDSStringItem)newItem("xxRelnTrgtFlg_A1");
		splyItemNum_A1 = (EZDSStringItem)newItem("splyItemNum_A1");
		mnfItemCd_A1 = (EZDSStringItem)newItem("mnfItemCd_A1");
		svcConfigMstrPk_A1 = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_A1");
		t_MdlNm_A1 = (EZDSStringItem)newItem("t_MdlNm_A1");
		xxLinkAncr_SE = (EZDSStringItem)newItem("xxLinkAncr_SE");
		serNumFlg_A1 = (EZDSStringItem)newItem("serNumFlg_A1");
		invtyTrxPk_A1 = (EZDSBigDecimalItem)newItem("invtyTrxPk_A1");
		invtyTrxTpDescTxt_A1 = (EZDSStringItem)newItem("invtyTrxTpDescTxt_A1");
		xxScrItem61Txt_AX = (EZDSStringItem)newItem("xxScrItem61Txt_AX");
		xxScrItem61Txt_AR = (EZDSStringItem)newItem("xxScrItem61Txt_AR");
		invtyTrxQty_A1 = (EZDSBigDecimalItem)newItem("invtyTrxQty_A1");
		xxScrItem61Txt_SS = (EZDSStringItem)newItem("xxScrItem61Txt_SS");
		xxScrItem61Txt_AS = (EZDSStringItem)newItem("xxScrItem61Txt_AS");
		invtyTrxSlpNum_A1 = (EZDSStringItem)newItem("invtyTrxSlpNum_A1");
		dplyLineNum_A1 = (EZDSStringItem)newItem("dplyLineNum_A1");
		soNum_A1 = (EZDSStringItem)newItem("soNum_A1");
		soSlpNum_A1 = (EZDSStringItem)newItem("soSlpNum_A1");
		rwsNum_A1 = (EZDSStringItem)newItem("rwsNum_A1");
		rwsDtlLineNum_A1 = (EZDSStringItem)newItem("rwsDtlLineNum_A1");
		dsAcctNm_A1 = (EZDSStringItem)newItem("dsAcctNm_A1");
		xxTsDsp19Txt_A1 = (EZDSStringItem)newItem("xxTsDsp19Txt_A1");
		shipToLocCustNm_A1 = (EZDSStringItem)newItem("shipToLocCustNm_A1");
		ajeLinkFlg_A1 = (EZDSStringItem)newItem("ajeLinkFlg_A1");
		xxRecHistCratTs_A1 = (EZDSStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDSStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDSStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDSStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0280_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0280_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A1", "rtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxScrItem61Txt_PJ", "xxScrItem61Txt_PJ", "PJ", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_MD", "xxScrItem61Txt_MD", "MD", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_PD", "xxScrItem61Txt_PD", "PD", null, TYPE_HANKAKUEISU, "61", null},
	{"xxLinkAncr_FI", "xxLinkAncr_FI", "FI", null, TYPE_HANKAKUEISU, "30", null},
	{"xxRelnTrgtFlg_A1", "xxRelnTrgtFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"splyItemNum_A1", "splyItemNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mnfItemCd_A1", "mnfItemCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"t_MdlNm_A1", "t_MdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkAncr_SE", "xxLinkAncr_SE", "SE", null, TYPE_HANKAKUEISU, "30", null},
	{"serNumFlg_A1", "serNumFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyTrxPk_A1", "invtyTrxPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invtyTrxTpDescTxt_A1", "invtyTrxTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem61Txt_AX", "xxScrItem61Txt_AX", "AX", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_AR", "xxScrItem61Txt_AR", "AR", null, TYPE_HANKAKUEISU, "61", null},
	{"invtyTrxQty_A1", "invtyTrxQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxScrItem61Txt_SS", "xxScrItem61Txt_SS", "SS", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_AS", "xxScrItem61Txt_AS", "AS", null, TYPE_HANKAKUEISU, "61", null},
	{"invtyTrxSlpNum_A1", "invtyTrxSlpNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"dplyLineNum_A1", "dplyLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"soNum_A1", "soNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"soSlpNum_A1", "soSlpNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"rwsNum_A1", "rwsNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"rwsDtlLineNum_A1", "rwsDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxTsDsp19Txt_A1", "xxTsDsp19Txt_A1", "A1", null, TYPE_HANKAKUEISU, "19", null},
	{"shipToLocCustNm_A1", "shipToLocCustNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"ajeLinkFlg_A1", "ajeLinkFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_PJ
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_MD
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_PD
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_FI
        {"XX_RELN_TRGT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRelnTrgtFlg_A1
        {"SPLY_ITEM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyItemNum_A1
        {"MNF_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A1
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_SE
        {"SER_NUM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumFlg_A1
        {"INVTY_TRX_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxPk_A1
        {"INVTY_TRX_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxTpDescTxt_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_AX
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_AR
        {"INVTY_TRX_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxQty_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_SS
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_AS
        {"INVTY_TRX_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxSlpNum_A1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_A1
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum_A1
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_A1
        {"RWS_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtlLineNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_A1
        {"SHIP_TO_LOC_CUST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocCustNm_A1
        {"AJE_LINK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeLinkFlg_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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

