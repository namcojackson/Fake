//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170703164352000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_ESMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_ESMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_LINE_NUM_E1*/
	public final EZDSBigDecimalItem              wmsLineNum_E1;

    /** WMS_MDSE_CD_E1*/
	public final EZDSStringItem              wmsMdseCd_E1;

    /** S80_STK_STS_CD_E1*/
	public final EZDSStringItem              s80StkStsCd_E1;

    /** S80_STK_STS_CD_TO_CD_E1*/
	public final EZDSStringItem              s80StkStsCdToCd_E1;

    /** MDSE_DESC_SHORT_TXT_E2*/
	public final EZDSStringItem              mdseDescShortTxt_E2;

    /** WMS_SHIP_QTY_E1*/
	public final EZDSBigDecimalItem              wmsShipQty_E1;

    /** IND_SER_ID_E1*/
	public final EZDSStringItem              indSerId_E1;

    /** MDSE_CD_SET_CD_E1*/
	public final EZDSStringItem              mdseCdSetCd_E1;

    /** SHIP_SET_QTY_E1*/
	public final EZDSBigDecimalItem              shipSetQty_E1;

    /** CUST_MDSE_CD_E1*/
	public final EZDSStringItem              custMdseCd_E1;

    /** ORIG_SO_ID_E1*/
	public final EZDSStringItem              origSoId_E1;

    /** ORIG_LINE_NUM_E1*/
	public final EZDSBigDecimalItem              origLineNum_E1;

    /** TOT_WT_AMT_NUM_E1*/
	public final EZDSBigDecimalItem              totWtAmtNum_E1;

    /** TOT_VOL_AMT_NUM_E1*/
	public final EZDSBigDecimalItem              totVolAmtNum_E1;

    /** UNIT_CRTN_QTY_E1*/
	public final EZDSBigDecimalItem              unitCrtnQty_E1;

    /** UNIT_PLT_QTY_E1*/
	public final EZDSBigDecimalItem              unitPltQty_E1;

    /** EST_CSE_AMT_NUM_E1*/
	public final EZDSBigDecimalItem              estCseAmtNum_E1;

    /** EST_PLT_AMT_NUM_E1*/
	public final EZDSBigDecimalItem              estPltAmtNum_E1;

    /** WMS_UPC_CD_E1*/
	public final EZDSStringItem              wmsUpcCd_E1;

    /** FILL_20_TXT_E1*/
	public final EZDSStringItem              fill20Txt_E1;

    /** EST_CSE_AMT_NUM_E2*/
	public final EZDSBigDecimalItem              estCseAmtNum_E2;

    /** EST_PLT_AMT_NUM_E2*/
	public final EZDSBigDecimalItem              estPltAmtNum_E2;

    /** FILL_40_TXT_E1*/
	public final EZDSStringItem              fill40Txt_E1;

    /** SVC_CONFIG_MSTR_PK_E1*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_E1;

    /** BACK_ORD_FLG_E1*/
	public final EZDSStringItem              backOrdFlg_E1;

    /** BACK_ORD_IMPCT_TP_CD_E1*/
	public final EZDSStringItem              backOrdImpctTpCd_E1;

    /** RMV_CONFIG_FLG_E1*/
	public final EZDSStringItem              rmvConfigFlg_E1;

    /** IND_CONFIG_FLG_E1*/
	public final EZDSStringItem              indConfigFlg_E1;

    /** SER_NUM_E1*/
	public final EZDSStringItem              serNum_E1;

    /** MDSE_DESC_SHORT_TXT_E1*/
	public final EZDSStringItem              mdseDescShortTxt_E1;

    /** UPC_CD_E1*/
	public final EZDSStringItem              upcCd_E1;


	/**
	 * NLGL0010_ESMsg is constructor.
	 * The initialization when the instance of NLGL0010_ESMsg is generated.
	 */
	public NLGL0010_ESMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_ESMsg is constructor.
	 * The initialization when the instance of NLGL0010_ESMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_ESMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsLineNum_E1 = (EZDSBigDecimalItem)newItem("wmsLineNum_E1");
		wmsMdseCd_E1 = (EZDSStringItem)newItem("wmsMdseCd_E1");
		s80StkStsCd_E1 = (EZDSStringItem)newItem("s80StkStsCd_E1");
		s80StkStsCdToCd_E1 = (EZDSStringItem)newItem("s80StkStsCdToCd_E1");
		mdseDescShortTxt_E2 = (EZDSStringItem)newItem("mdseDescShortTxt_E2");
		wmsShipQty_E1 = (EZDSBigDecimalItem)newItem("wmsShipQty_E1");
		indSerId_E1 = (EZDSStringItem)newItem("indSerId_E1");
		mdseCdSetCd_E1 = (EZDSStringItem)newItem("mdseCdSetCd_E1");
		shipSetQty_E1 = (EZDSBigDecimalItem)newItem("shipSetQty_E1");
		custMdseCd_E1 = (EZDSStringItem)newItem("custMdseCd_E1");
		origSoId_E1 = (EZDSStringItem)newItem("origSoId_E1");
		origLineNum_E1 = (EZDSBigDecimalItem)newItem("origLineNum_E1");
		totWtAmtNum_E1 = (EZDSBigDecimalItem)newItem("totWtAmtNum_E1");
		totVolAmtNum_E1 = (EZDSBigDecimalItem)newItem("totVolAmtNum_E1");
		unitCrtnQty_E1 = (EZDSBigDecimalItem)newItem("unitCrtnQty_E1");
		unitPltQty_E1 = (EZDSBigDecimalItem)newItem("unitPltQty_E1");
		estCseAmtNum_E1 = (EZDSBigDecimalItem)newItem("estCseAmtNum_E1");
		estPltAmtNum_E1 = (EZDSBigDecimalItem)newItem("estPltAmtNum_E1");
		wmsUpcCd_E1 = (EZDSStringItem)newItem("wmsUpcCd_E1");
		fill20Txt_E1 = (EZDSStringItem)newItem("fill20Txt_E1");
		estCseAmtNum_E2 = (EZDSBigDecimalItem)newItem("estCseAmtNum_E2");
		estPltAmtNum_E2 = (EZDSBigDecimalItem)newItem("estPltAmtNum_E2");
		fill40Txt_E1 = (EZDSStringItem)newItem("fill40Txt_E1");
		svcConfigMstrPk_E1 = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_E1");
		backOrdFlg_E1 = (EZDSStringItem)newItem("backOrdFlg_E1");
		backOrdImpctTpCd_E1 = (EZDSStringItem)newItem("backOrdImpctTpCd_E1");
		rmvConfigFlg_E1 = (EZDSStringItem)newItem("rmvConfigFlg_E1");
		indConfigFlg_E1 = (EZDSStringItem)newItem("indConfigFlg_E1");
		serNum_E1 = (EZDSStringItem)newItem("serNum_E1");
		mdseDescShortTxt_E1 = (EZDSStringItem)newItem("mdseDescShortTxt_E1");
		upcCd_E1 = (EZDSStringItem)newItem("upcCd_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_ESMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_ESMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsLineNum_E1", "wmsLineNum_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsMdseCd_E1", "wmsMdseCd_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"s80StkStsCd_E1", "s80StkStsCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"s80StkStsCdToCd_E1", "s80StkStsCdToCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseDescShortTxt_E2", "mdseDescShortTxt_E2", "E2", null, TYPE_HANKAKUEISU, "250", null},
	{"wmsShipQty_E1", "wmsShipQty_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"indSerId_E1", "indSerId_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCdSetCd_E1", "mdseCdSetCd_E1", "E1", null, TYPE_HANKAKUEISU, "25", null},
	{"shipSetQty_E1", "shipSetQty_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"custMdseCd_E1", "custMdseCd_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"origSoId_E1", "origSoId_E1", "E1", null, TYPE_HANKAKUEISU, "15", null},
	{"origLineNum_E1", "origLineNum_E1", "E1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"totWtAmtNum_E1", "totWtAmtNum_E1", "E1", null, TYPE_SEISU_SYOSU, "15", "4"},
	{"totVolAmtNum_E1", "totVolAmtNum_E1", "E1", null, TYPE_SEISU_SYOSU, "15", "2"},
	{"unitCrtnQty_E1", "unitCrtnQty_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"unitPltQty_E1", "unitPltQty_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"estCseAmtNum_E1", "estCseAmtNum_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"estPltAmtNum_E1", "estPltAmtNum_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"wmsUpcCd_E1", "wmsUpcCd_E1", "E1", null, TYPE_HANKAKUEISU, "25", null},
	{"fill20Txt_E1", "fill20Txt_E1", "E1", null, TYPE_HANKAKUEISU, "20", null},
	{"estCseAmtNum_E2", "estCseAmtNum_E2", "E2", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"estPltAmtNum_E2", "estPltAmtNum_E2", "E2", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"fill40Txt_E1", "fill40Txt_E1", "E1", null, TYPE_HANKAKUEISU, "40", null},
	{"svcConfigMstrPk_E1", "svcConfigMstrPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"backOrdFlg_E1", "backOrdFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"backOrdImpctTpCd_E1", "backOrdImpctTpCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"rmvConfigFlg_E1", "rmvConfigFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"indConfigFlg_E1", "indConfigFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"serNum_E1", "serNum_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseDescShortTxt_E1", "mdseDescShortTxt_E1", "E1", null, TYPE_HANKAKUEISU, "250", null},
	{"upcCd_E1", "upcCd_E1", "E1", null, TYPE_HANKAKUEISU, "12", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsLineNum_E1
        {"WMS_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_E1
        {"S80_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//s80StkStsCd_E1
        {"S80_STK_STS_CD_TO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//s80StkStsCdToCd_E1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_E2
        {"WMS_SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipQty_E1
        {"IND_SER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indSerId_E1
        {"MDSE_CD_SET_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCdSetCd_E1
        {"SHIP_SET_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipSetQty_E1
        {"CUST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_E1
        {"ORIG_SO_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSoId_E1
        {"ORIG_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origLineNum_E1
        {"TOT_WT_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totWtAmtNum_E1
        {"TOT_VOL_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totVolAmtNum_E1
        {"UNIT_CRTN_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitCrtnQty_E1
        {"UNIT_PLT_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPltQty_E1
        {"EST_CSE_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estCseAmtNum_E1
        {"EST_PLT_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estPltAmtNum_E1
        {"WMS_UPC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsUpcCd_E1
        {"FILL_20_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill20Txt_E1
        {"EST_CSE_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estCseAmtNum_E2
        {"EST_PLT_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estPltAmtNum_E2
        {"FILL_40_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill40Txt_E1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_E1
        {"BACK_ORD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdFlg_E1
        {"BACK_ORD_IMPCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpCd_E1
        {"RMV_CONFIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvConfigFlg_E1
        {"IND_CONFIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indConfigFlg_E1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_E1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_E1
        {"UPC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upcCd_E1
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

