//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170703164352000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_NSMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NLGL0010_NSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_LINE_NUM_N1*/
	public final EZDSBigDecimalItem              wmsLineNum_N1;

    /** WMS_MDSE_CD_N1*/
	public final EZDSStringItem              wmsMdseCd_N1;

    /** S80_STK_STS_CD_N1*/
	public final EZDSStringItem              s80StkStsCd_N1;

    /** S80_STK_STS_CD_TO_CD_N1*/
	public final EZDSStringItem              s80StkStsCdToCd_N1;

    /** MDSE_DESC_SHORT_TXT_N1*/
	public final EZDSStringItem              mdseDescShortTxt_N1;

    /** WMS_SHIP_QTY_N1*/
	public final EZDSBigDecimalItem              wmsShipQty_N1;

    /** IND_SER_ID_N1*/
	public final EZDSStringItem              indSerId_N1;

    /** MDSE_CD_SET_CD_N1*/
	public final EZDSStringItem              mdseCdSetCd_N1;

    /** SHIP_SET_QTY_N1*/
	public final EZDSBigDecimalItem              shipSetQty_N1;

    /** CUST_MDSE_CD_N1*/
	public final EZDSStringItem              custMdseCd_N1;

    /** ORIG_SO_ID_N1*/
	public final EZDSStringItem              origSoId_N1;

    /** ORIG_LINE_NUM_N1*/
	public final EZDSBigDecimalItem              origLineNum_N1;

    /** TOT_WT_AMT_NUM_N1*/
	public final EZDSBigDecimalItem              totWtAmtNum_N1;

    /** TOT_VOL_AMT_NUM_N1*/
	public final EZDSBigDecimalItem              totVolAmtNum_N1;

    /** UNIT_CRTN_QTY_N1*/
	public final EZDSBigDecimalItem              unitCrtnQty_N1;

    /** UNIT_PLT_QTY_N1*/
	public final EZDSBigDecimalItem              unitPltQty_N1;

    /** EST_CSE_AMT_NUM_N1*/
	public final EZDSBigDecimalItem              estCseAmtNum_N1;

    /** EST_PLT_AMT_NUM_N1*/
	public final EZDSBigDecimalItem              estPltAmtNum_N1;

    /** WMS_UPC_CD_N1*/
	public final EZDSStringItem              wmsUpcCd_N1;

    /** FILL_20_TXT_N1*/
	public final EZDSStringItem              fill20Txt_N1;

    /** EST_CSE_AMT_NUM_N2*/
	public final EZDSBigDecimalItem              estCseAmtNum_N2;

    /** EST_PLT_AMT_NUM_N2*/
	public final EZDSBigDecimalItem              estPltAmtNum_N2;

    /** FILL_40_TXT_N1*/
	public final EZDSStringItem              fill40Txt_N1;

    /** SVC_CONFIG_MSTR_PK_N1*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_N1;

    /** BACK_ORD_FLG_N1*/
	public final EZDSStringItem              backOrdFlg_N1;

    /** BACK_ORD_IMPCT_TP_CD_N1*/
	public final EZDSStringItem              backOrdImpctTpCd_N1;

    /** RMV_CONFIG_FLG_N1*/
	public final EZDSStringItem              rmvConfigFlg_N1;

    /** IND_CONFIG_FLG_N1*/
	public final EZDSStringItem              indConfigFlg_N1;

    /** SER_NUM_N1*/
	public final EZDSStringItem              serNum_N1;


	/**
	 * NLGL0010_NSMsg is constructor.
	 * The initialization when the instance of NLGL0010_NSMsg is generated.
	 */
	public NLGL0010_NSMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_NSMsg is constructor.
	 * The initialization when the instance of NLGL0010_NSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_NSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsLineNum_N1 = (EZDSBigDecimalItem)newItem("wmsLineNum_N1");
		wmsMdseCd_N1 = (EZDSStringItem)newItem("wmsMdseCd_N1");
		s80StkStsCd_N1 = (EZDSStringItem)newItem("s80StkStsCd_N1");
		s80StkStsCdToCd_N1 = (EZDSStringItem)newItem("s80StkStsCdToCd_N1");
		mdseDescShortTxt_N1 = (EZDSStringItem)newItem("mdseDescShortTxt_N1");
		wmsShipQty_N1 = (EZDSBigDecimalItem)newItem("wmsShipQty_N1");
		indSerId_N1 = (EZDSStringItem)newItem("indSerId_N1");
		mdseCdSetCd_N1 = (EZDSStringItem)newItem("mdseCdSetCd_N1");
		shipSetQty_N1 = (EZDSBigDecimalItem)newItem("shipSetQty_N1");
		custMdseCd_N1 = (EZDSStringItem)newItem("custMdseCd_N1");
		origSoId_N1 = (EZDSStringItem)newItem("origSoId_N1");
		origLineNum_N1 = (EZDSBigDecimalItem)newItem("origLineNum_N1");
		totWtAmtNum_N1 = (EZDSBigDecimalItem)newItem("totWtAmtNum_N1");
		totVolAmtNum_N1 = (EZDSBigDecimalItem)newItem("totVolAmtNum_N1");
		unitCrtnQty_N1 = (EZDSBigDecimalItem)newItem("unitCrtnQty_N1");
		unitPltQty_N1 = (EZDSBigDecimalItem)newItem("unitPltQty_N1");
		estCseAmtNum_N1 = (EZDSBigDecimalItem)newItem("estCseAmtNum_N1");
		estPltAmtNum_N1 = (EZDSBigDecimalItem)newItem("estPltAmtNum_N1");
		wmsUpcCd_N1 = (EZDSStringItem)newItem("wmsUpcCd_N1");
		fill20Txt_N1 = (EZDSStringItem)newItem("fill20Txt_N1");
		estCseAmtNum_N2 = (EZDSBigDecimalItem)newItem("estCseAmtNum_N2");
		estPltAmtNum_N2 = (EZDSBigDecimalItem)newItem("estPltAmtNum_N2");
		fill40Txt_N1 = (EZDSStringItem)newItem("fill40Txt_N1");
		svcConfigMstrPk_N1 = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_N1");
		backOrdFlg_N1 = (EZDSStringItem)newItem("backOrdFlg_N1");
		backOrdImpctTpCd_N1 = (EZDSStringItem)newItem("backOrdImpctTpCd_N1");
		rmvConfigFlg_N1 = (EZDSStringItem)newItem("rmvConfigFlg_N1");
		indConfigFlg_N1 = (EZDSStringItem)newItem("indConfigFlg_N1");
		serNum_N1 = (EZDSStringItem)newItem("serNum_N1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_NSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_NSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsLineNum_N1", "wmsLineNum_N1", "N1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"wmsMdseCd_N1", "wmsMdseCd_N1", "N1", null, TYPE_HANKAKUEISU, "30", null},
	{"s80StkStsCd_N1", "s80StkStsCd_N1", "N1", null, TYPE_HANKAKUEISU, "2", null},
	{"s80StkStsCdToCd_N1", "s80StkStsCdToCd_N1", "N1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseDescShortTxt_N1", "mdseDescShortTxt_N1", "N1", null, TYPE_HANKAKUEISU, "250", null},
	{"wmsShipQty_N1", "wmsShipQty_N1", "N1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"indSerId_N1", "indSerId_N1", "N1", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCdSetCd_N1", "mdseCdSetCd_N1", "N1", null, TYPE_HANKAKUEISU, "25", null},
	{"shipSetQty_N1", "shipSetQty_N1", "N1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"custMdseCd_N1", "custMdseCd_N1", "N1", null, TYPE_HANKAKUEISU, "30", null},
	{"origSoId_N1", "origSoId_N1", "N1", null, TYPE_HANKAKUEISU, "15", null},
	{"origLineNum_N1", "origLineNum_N1", "N1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"totWtAmtNum_N1", "totWtAmtNum_N1", "N1", null, TYPE_SEISU_SYOSU, "15", "4"},
	{"totVolAmtNum_N1", "totVolAmtNum_N1", "N1", null, TYPE_SEISU_SYOSU, "15", "2"},
	{"unitCrtnQty_N1", "unitCrtnQty_N1", "N1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"unitPltQty_N1", "unitPltQty_N1", "N1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"estCseAmtNum_N1", "estCseAmtNum_N1", "N1", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"estPltAmtNum_N1", "estPltAmtNum_N1", "N1", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"wmsUpcCd_N1", "wmsUpcCd_N1", "N1", null, TYPE_HANKAKUEISU, "25", null},
	{"fill20Txt_N1", "fill20Txt_N1", "N1", null, TYPE_HANKAKUEISU, "20", null},
	{"estCseAmtNum_N2", "estCseAmtNum_N2", "N2", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"estPltAmtNum_N2", "estPltAmtNum_N2", "N2", null, TYPE_SEISU_SYOSU, "10", "2"},
	{"fill40Txt_N1", "fill40Txt_N1", "N1", null, TYPE_HANKAKUEISU, "40", null},
	{"svcConfigMstrPk_N1", "svcConfigMstrPk_N1", "N1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"backOrdFlg_N1", "backOrdFlg_N1", "N1", null, TYPE_HANKAKUEISU, "1", null},
	{"backOrdImpctTpCd_N1", "backOrdImpctTpCd_N1", "N1", null, TYPE_HANKAKUEISU, "2", null},
	{"rmvConfigFlg_N1", "rmvConfigFlg_N1", "N1", null, TYPE_HANKAKUEISU, "1", null},
	{"indConfigFlg_N1", "indConfigFlg_N1", "N1", null, TYPE_HANKAKUEISU, "1", null},
	{"serNum_N1", "serNum_N1", "N1", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsLineNum_N1
        {"WMS_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_N1
        {"S80_STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//s80StkStsCd_N1
        {"S80_STK_STS_CD_TO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//s80StkStsCdToCd_N1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_N1
        {"WMS_SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsShipQty_N1
        {"IND_SER_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indSerId_N1
        {"MDSE_CD_SET_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCdSetCd_N1
        {"SHIP_SET_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipSetQty_N1
        {"CUST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMdseCd_N1
        {"ORIG_SO_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSoId_N1
        {"ORIG_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origLineNum_N1
        {"TOT_WT_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totWtAmtNum_N1
        {"TOT_VOL_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totVolAmtNum_N1
        {"UNIT_CRTN_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitCrtnQty_N1
        {"UNIT_PLT_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPltQty_N1
        {"EST_CSE_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estCseAmtNum_N1
        {"EST_PLT_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estPltAmtNum_N1
        {"WMS_UPC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsUpcCd_N1
        {"FILL_20_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill20Txt_N1
        {"EST_CSE_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estCseAmtNum_N2
        {"EST_PLT_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//estPltAmtNum_N2
        {"FILL_40_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill40Txt_N1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_N1
        {"BACK_ORD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdFlg_N1
        {"BACK_ORD_IMPCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//backOrdImpctTpCd_N1
        {"RMV_CONFIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvConfigFlg_N1
        {"IND_CONFIG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indConfigFlg_N1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_N1
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
