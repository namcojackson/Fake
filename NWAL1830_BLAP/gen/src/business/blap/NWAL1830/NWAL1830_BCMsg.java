//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200512133134000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1830_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1830 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1830_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ORD_LINE_CATG_CD_BA*/
	public final EZDCStringItem              dsOrdLineCatgCd_BA;

    /** XX_SCR_ITEM_50_TXT_B1*/
	public final EZDCStringItem              xxScrItem50Txt_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** DPLY_LINE_NUM_B1*/
	public final EZDCStringItem              dplyLineNum_B1;

    /** MDSE_CD_B1*/
	public final EZDCStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDCStringItem              mdseDescShortTxt_B1;

    /** ORD_QTY_B1*/
	public final EZDCBigDecimalItem              ordQty_B1;

    /** SHIP_QTY_B1*/
	public final EZDCBigDecimalItem              shipQty_B1;

    /** MDL_NM_B1*/
	public final EZDCStringItem              mdlNm_B1;

    /** ORD_LINE_STS_DESC_TXT_B1*/
	public final EZDCStringItem              ordLineStsDescTxt_B1;

    /** DS_ORD_LINE_CATG_DESC_TXT_B1*/
	public final EZDCStringItem              dsOrdLineCatgDescTxt_B1;

    /** COA_MDSE_TP_DESC_TXT_B1*/
	public final EZDCStringItem              coaMdseTpDescTxt_B1;

    /** COA_PROD_DESC_TXT_B1*/
	public final EZDCStringItem              coaProdDescTxt_B1;

    /** RTL_WH_NM_B1*/
	public final EZDCStringItem              rtlWhNm_B1;

    /** RTL_WH_NM_LK*/
	public final EZDCStringItem              rtlWhNm_LK;

    /** SER_NUM_B1*/
	public final EZDCStringItem              serNum_B1;

    /** PROC_FLG_B1*/
	public final EZDCStringItem              procFlg_B1;

    /** CPO_ORD_NUM_B2*/
	public final EZDCStringItem              cpoOrdNum_B2;

    /** DPLY_LINE_NUM_B2*/
	public final EZDCStringItem              dplyLineNum_B2;

    /** ORD_LINE_STS_DESC_TXT_B2*/
	public final EZDCStringItem              ordLineStsDescTxt_B2;

    /** CPO_ORD_NUM_B1*/
	public final EZDCStringItem              cpoOrdNum_B1;

    /** CPO_DTL_LINE_NUM_B1*/
	public final EZDCStringItem              cpoDtlLineNum_B1;

    /** CPO_DTL_LINE_SUB_NUM_B1*/
	public final EZDCStringItem              cpoDtlLineSubNum_B1;

    /** DS_ORD_POSN_NUM_B1*/
	public final EZDCStringItem              dsOrdPosnNum_B1;

    /** DS_CPO_CONFIG_PK_B1*/
	public final EZDCBigDecimalItem              dsCpoConfigPk_B1;

    /** XX_DPLY_CTRL_FLG_B1*/
	public final EZDCStringItem              xxDplyCtrlFlg_B1;

    /** LOAN_BAL_QTY_B1*/
	public final EZDCBigDecimalItem              loanBalQty_B1;

    /** ORD_LINE_STS_CD_B1*/
	public final EZDCStringItem              ordLineStsCd_B1;

    /** CPO_OPEN_FLG_B1*/
	public final EZDCStringItem              cpoOpenFlg_B1;

    /** SVC_MACH_MAINT_AVAL_FLG_B1*/
	public final EZDCStringItem              svcMachMaintAvalFlg_B1;

    /** SVC_MACH_MSTR_STS_CD_B1*/
	public final EZDCStringItem              svcMachMstrStsCd_B1;

    /** RTL_WH_CD_B1*/
	public final EZDCStringItem              rtlWhCd_B1;

    /** RTL_SWH_CD_B1*/
	public final EZDCStringItem              rtlSwhCd_B1;

    /** SET_MDSE_CD_B1*/
	public final EZDCStringItem              setMdseCd_B1;

    /** SVC_MACH_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              svcMachMstrPk_B1;

    /** MDSE_CD_BM*/
	public final EZDCStringItem              mdseCd_BM;

    /** BASE_CMPT_FLG_B1*/
	public final EZDCStringItem              baseCmptFlg_B1;

    /** INSTL_BASE_CTRL_FLG_B1*/
	public final EZDCStringItem              instlBaseCtrlFlg_B1;

    /** INVTY_CTRL_FLG_B1*/
	public final EZDCStringItem              invtyCtrlFlg_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDCStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDCStringItem              ezUpTimeZone_B1;

    /** XX_ROW_NUM_B1*/
	public final EZDCBigDecimalItem              xxRowNum_B1;

    /** MDSE_CD_SP*/
	public final EZDCStringItem              mdseCd_SP;


	/**
	 * NWAL1830_BCMsg is constructor.
	 * The initialization when the instance of NWAL1830_BCMsg is generated.
	 */
	public NWAL1830_BCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1830_BCMsg is constructor.
	 * The initialization when the instance of NWAL1830_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1830_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsOrdLineCatgCd_BA = (EZDCStringItem)newItem("dsOrdLineCatgCd_BA");
		xxScrItem50Txt_B1 = (EZDCStringItem)newItem("xxScrItem50Txt_B1");
		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		dplyLineNum_B1 = (EZDCStringItem)newItem("dplyLineNum_B1");
		mdseCd_B1 = (EZDCStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDCStringItem)newItem("mdseDescShortTxt_B1");
		ordQty_B1 = (EZDCBigDecimalItem)newItem("ordQty_B1");
		shipQty_B1 = (EZDCBigDecimalItem)newItem("shipQty_B1");
		mdlNm_B1 = (EZDCStringItem)newItem("mdlNm_B1");
		ordLineStsDescTxt_B1 = (EZDCStringItem)newItem("ordLineStsDescTxt_B1");
		dsOrdLineCatgDescTxt_B1 = (EZDCStringItem)newItem("dsOrdLineCatgDescTxt_B1");
		coaMdseTpDescTxt_B1 = (EZDCStringItem)newItem("coaMdseTpDescTxt_B1");
		coaProdDescTxt_B1 = (EZDCStringItem)newItem("coaProdDescTxt_B1");
		rtlWhNm_B1 = (EZDCStringItem)newItem("rtlWhNm_B1");
		rtlWhNm_LK = (EZDCStringItem)newItem("rtlWhNm_LK");
		serNum_B1 = (EZDCStringItem)newItem("serNum_B1");
		procFlg_B1 = (EZDCStringItem)newItem("procFlg_B1");
		cpoOrdNum_B2 = (EZDCStringItem)newItem("cpoOrdNum_B2");
		dplyLineNum_B2 = (EZDCStringItem)newItem("dplyLineNum_B2");
		ordLineStsDescTxt_B2 = (EZDCStringItem)newItem("ordLineStsDescTxt_B2");
		cpoOrdNum_B1 = (EZDCStringItem)newItem("cpoOrdNum_B1");
		cpoDtlLineNum_B1 = (EZDCStringItem)newItem("cpoDtlLineNum_B1");
		cpoDtlLineSubNum_B1 = (EZDCStringItem)newItem("cpoDtlLineSubNum_B1");
		dsOrdPosnNum_B1 = (EZDCStringItem)newItem("dsOrdPosnNum_B1");
		dsCpoConfigPk_B1 = (EZDCBigDecimalItem)newItem("dsCpoConfigPk_B1");
		xxDplyCtrlFlg_B1 = (EZDCStringItem)newItem("xxDplyCtrlFlg_B1");
		loanBalQty_B1 = (EZDCBigDecimalItem)newItem("loanBalQty_B1");
		ordLineStsCd_B1 = (EZDCStringItem)newItem("ordLineStsCd_B1");
		cpoOpenFlg_B1 = (EZDCStringItem)newItem("cpoOpenFlg_B1");
		svcMachMaintAvalFlg_B1 = (EZDCStringItem)newItem("svcMachMaintAvalFlg_B1");
		svcMachMstrStsCd_B1 = (EZDCStringItem)newItem("svcMachMstrStsCd_B1");
		rtlWhCd_B1 = (EZDCStringItem)newItem("rtlWhCd_B1");
		rtlSwhCd_B1 = (EZDCStringItem)newItem("rtlSwhCd_B1");
		setMdseCd_B1 = (EZDCStringItem)newItem("setMdseCd_B1");
		svcMachMstrPk_B1 = (EZDCBigDecimalItem)newItem("svcMachMstrPk_B1");
		mdseCd_BM = (EZDCStringItem)newItem("mdseCd_BM");
		baseCmptFlg_B1 = (EZDCStringItem)newItem("baseCmptFlg_B1");
		instlBaseCtrlFlg_B1 = (EZDCStringItem)newItem("instlBaseCtrlFlg_B1");
		invtyCtrlFlg_B1 = (EZDCStringItem)newItem("invtyCtrlFlg_B1");
		ezUpTime_B1 = (EZDCStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDCStringItem)newItem("ezUpTimeZone_B1");
		xxRowNum_B1 = (EZDCBigDecimalItem)newItem("xxRowNum_B1");
		mdseCd_SP = (EZDCStringItem)newItem("mdseCd_SP");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1830_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1830_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsOrdLineCatgCd_BA", "dsOrdLineCatgCd_BA", "BA", null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrItem50Txt_B1", "xxScrItem50Txt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dplyLineNum_B1", "dplyLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"ordQty_B1", "ordQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shipQty_B1", "shipQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mdlNm_B1", "mdlNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"ordLineStsDescTxt_B1", "ordLineStsDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdLineCatgDescTxt_B1", "dsOrdLineCatgDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaMdseTpDescTxt_B1", "coaMdseTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaProdDescTxt_B1", "coaProdDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhNm_B1", "rtlWhNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhNm_LK", "rtlWhNm_LK", "LK", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_B1", "serNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"procFlg_B1", "procFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"cpoOrdNum_B2", "cpoOrdNum_B2", "B2", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum_B2", "dplyLineNum_B2", "B2", null, TYPE_HANKAKUEISU, "20", null},
	{"ordLineStsDescTxt_B2", "ordLineStsDescTxt_B2", "B2", null, TYPE_HANKAKUEISU, "50", null},
	{"cpoOrdNum_B1", "cpoOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum_B1", "cpoDtlLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_B1", "cpoDtlLineSubNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsOrdPosnNum_B1", "dsOrdPosnNum_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"dsCpoConfigPk_B1", "dsCpoConfigPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxDplyCtrlFlg_B1", "xxDplyCtrlFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"loanBalQty_B1", "loanBalQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordLineStsCd_B1", "ordLineStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"cpoOpenFlg_B1", "cpoOpenFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"svcMachMaintAvalFlg_B1", "svcMachMaintAvalFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"svcMachMstrStsCd_B1", "svcMachMstrStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"rtlWhCd_B1", "rtlWhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_B1", "rtlSwhCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"setMdseCd_B1", "setMdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"svcMachMstrPk_B1", "svcMachMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_BM", "mdseCd_BM", "BM", null, TYPE_HANKAKUEISU, "16", null},
	{"baseCmptFlg_B1", "baseCmptFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"instlBaseCtrlFlg_B1", "instlBaseCtrlFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyCtrlFlg_B1", "invtyCtrlFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRowNum_B1", "xxRowNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mdseCd_SP", "mdseCd_SP", "SP", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ORD_LINE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgCd_BA
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_B1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_B1
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty_B1
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_B1
        {"ORD_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsDescTxt_B1
        {"DS_ORD_LINE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdLineCatgDescTxt_B1
        {"COA_MDSE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpDescTxt_B1
        {"COA_PROD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdDescTxt_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_LK
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B1
        {"PROC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procFlg_B1
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_B2
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_B2
        {"ORD_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsDescTxt_B2
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_B1
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_B1
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_B1
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_B1
        {"DS_CPO_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_B1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_B1
        {"LOAN_BAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//loanBalQty_B1
        {"ORD_LINE_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsCd_B1
        {"CPO_OPEN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOpenFlg_B1
        {"SVC_MACH_MAINT_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMaintAvalFlg_B1
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_B1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B1
        {"SET_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//setMdseCd_B1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_BM
        {"BASE_CMPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseCmptFlg_B1
        {"INSTL_BASE_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlBaseCtrlFlg_B1
        {"INVTY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyCtrlFlg_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_SP
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

