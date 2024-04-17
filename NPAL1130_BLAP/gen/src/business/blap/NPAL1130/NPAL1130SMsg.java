//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230417172251000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1130SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1130 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1130SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDSDateItem              slsDt;

    /** XX_LINK_ANCR_H1*/
	public final EZDSStringItem              xxLinkAncr_H1;

    /** MDSE_CD*/
	public final EZDSStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDSStringItem              mdseDescShortTxt;

    /** ORD_TAKE_MDSE_CD*/
	public final EZDSStringItem              ordTakeMdseCd;

    /** SUPD_FLG*/
	public final EZDSStringItem              supdFlg;

    /** XX_LINK_ANCR_H2*/
	public final EZDSStringItem              xxLinkAncr_H2;

    /** RTL_WH_CD*/
	public final EZDSStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDSStringItem              rtlWhNm;

    /** RTL_SWH_CD*/
	public final EZDSStringItem              rtlSwhCd;

    /** RTL_SWH_NM*/
	public final EZDSStringItem              rtlSwhNm;

    /** INVTY_LOC_CD*/
	public final EZDSStringItem              invtyLocCd;

    /** MRP_PLN_NM*/
	public final EZDSStringItem              mrpPlnNm;

    /** ROP_QTY*/
	public final EZDSBigDecimalItem              ropQty;

    /** MAX_ORD_QTY*/
	public final EZDSBigDecimalItem              maxOrdQty;

    /** PROCR_TP_DESC_TXT*/
	public final EZDSStringItem              procrTpDescTxt;

    /** SRC_RTL_WH_CD_S1*/
	public final EZDSStringItem              srcRtlWhCd_S1;

    /** RTL_WH_NM_S1*/
	public final EZDSStringItem              rtlWhNm_S1;

    /** XX_CHK_BOX_CO*/
	public final EZDSStringItem              xxChkBox_CO;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_FILE_DATA*/
	public final EZDSMimeSourceItem              xxFileData;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** S*/
	public final business.blap.NPAL1130.NPAL1130_SSMsgArray              S;

    /** A*/
	public final business.blap.NPAL1130.NPAL1130_ASMsgArray              A;

    /** B*/
	public final business.blap.NPAL1130.NPAL1130_BSMsgArray              B;

    /** C*/
	public final business.blap.NPAL1130.NPAL1130_CSMsgArray              C;

    /** P*/
	public final business.blap.NPAL1130.NPAL1130_PSMsgArray              P;

    /** XX_NUM*/
	public final EZDSBigDecimalItem              xxNum;


	/**
	 * NPAL1130SMsg is constructor.
	 * The initialization when the instance of NPAL1130SMsg is generated.
	 */
	public NPAL1130SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1130SMsg is constructor.
	 * The initialization when the instance of NPAL1130SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1130SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		slsDt = (EZDSDateItem)newItem("slsDt");
		xxLinkAncr_H1 = (EZDSStringItem)newItem("xxLinkAncr_H1");
		mdseCd = (EZDSStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDSStringItem)newItem("mdseDescShortTxt");
		ordTakeMdseCd = (EZDSStringItem)newItem("ordTakeMdseCd");
		supdFlg = (EZDSStringItem)newItem("supdFlg");
		xxLinkAncr_H2 = (EZDSStringItem)newItem("xxLinkAncr_H2");
		rtlWhCd = (EZDSStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDSStringItem)newItem("rtlWhNm");
		rtlSwhCd = (EZDSStringItem)newItem("rtlSwhCd");
		rtlSwhNm = (EZDSStringItem)newItem("rtlSwhNm");
		invtyLocCd = (EZDSStringItem)newItem("invtyLocCd");
		mrpPlnNm = (EZDSStringItem)newItem("mrpPlnNm");
		ropQty = (EZDSBigDecimalItem)newItem("ropQty");
		maxOrdQty = (EZDSBigDecimalItem)newItem("maxOrdQty");
		procrTpDescTxt = (EZDSStringItem)newItem("procrTpDescTxt");
		srcRtlWhCd_S1 = (EZDSStringItem)newItem("srcRtlWhCd_S1");
		rtlWhNm_S1 = (EZDSStringItem)newItem("rtlWhNm_S1");
		xxChkBox_CO = (EZDSStringItem)newItem("xxChkBox_CO");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxFileData = (EZDSMimeSourceItem)newItem("xxFileData");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		S = (business.blap.NPAL1130.NPAL1130_SSMsgArray)newMsgArray("S");
		A = (business.blap.NPAL1130.NPAL1130_ASMsgArray)newMsgArray("A");
		B = (business.blap.NPAL1130.NPAL1130_BSMsgArray)newMsgArray("B");
		C = (business.blap.NPAL1130.NPAL1130_CSMsgArray)newMsgArray("C");
		P = (business.blap.NPAL1130.NPAL1130_PSMsgArray)newMsgArray("P");
		xxNum = (EZDSBigDecimalItem)newItem("xxNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1130SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1130SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxLinkAncr_H1", "xxLinkAncr_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"ordTakeMdseCd", "ordTakeMdseCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"supdFlg", "supdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxLinkAncr_H2", "xxLinkAncr_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm", "rtlSwhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"mrpPlnNm", "mrpPlnNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"ropQty", "ropQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"maxOrdQty", "maxOrdQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"procrTpDescTxt", "procrTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"srcRtlWhCd_S1", "srcRtlWhCd_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_S1", "rtlWhNm_S1", "S1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxChkBox_CO", "xxChkBox_CO", "CO", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"S", "S", null, "10", "business.blap.NPAL1130.NPAL1130_SSMsgArray", null, null},
	
	{"A", "A", null, "1000", "business.blap.NPAL1130.NPAL1130_ASMsgArray", null, null},
	
	{"B", "B", null, "1000", "business.blap.NPAL1130.NPAL1130_BSMsgArray", null, null},
	
	{"C", "C", null, "1000", "business.blap.NPAL1130.NPAL1130_CSMsgArray", null, null},
	
	{"P", "P", null, "12", "business.blap.NPAL1130.NPAL1130_PSMsgArray", null, null},
	
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_H1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"ORD_TAKE_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTakeMdseCd
        {"SUPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFlg
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_H2
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"MRP_PLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpPlnNm
        {"ROP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ropQty
        {"MAX_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxOrdQty
        {"PROCR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpDescTxt
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd_S1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_S1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_CO
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//S
		null,	//A
		null,	//B
		null,	//C
		null,	//P
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
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

