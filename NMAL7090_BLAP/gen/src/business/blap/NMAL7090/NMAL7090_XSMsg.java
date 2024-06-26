//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160512151637000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7090_XSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7090 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7090_XSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_LIST_EQUIP_RQST_PK_B*/
	public final EZDSBigDecimalItem              prcListEquipRqstPk_B;

    /** OLD_MDSE_CD_B*/
	public final EZDSStringItem              oldMdseCd_B;

    /** OLD_MDSE_DESC_SHORT_TXT_B*/
	public final EZDSStringItem              oldMdseDescShortTxt_B;

    /** OLD_TMTH_TOT_STD_COST_AMT_B*/
	public final EZDSBigDecimalItem              oldTmthTotStdCostAmt_B;

    /** NEW_MDSE_CD_B*/
	public final EZDSStringItem              newMdseCd_B;

    /** NEW_MDSE_DESC_SHORT_TXT_B*/
	public final EZDSStringItem              newMdseDescShortTxt_B;

    /** NEW_TMTH_TOT_STD_COST_AMT_B*/
	public final EZDSBigDecimalItem              newTmthTotStdCostAmt_B;

    /** PRC_LIST_TP_CD_BP*/
	public final EZDSStringItemArray              prcListTpCd_BP;

    /** PRC_LIST_TP_DESC_TXT_BP*/
	public final EZDSStringItemArray              prcListTpDescTxt_BP;

    /** PRC_LIST_TP_CD_BS*/
	public final EZDSStringItem              prcListTpCd_BS;

    /** PRC_LIST_ACT_TP_CD_BP*/
	public final EZDSStringItemArray              prcListActTpCd_BP;

    /** PRC_LIST_ACT_TP_DESC_TXT_BP*/
	public final EZDSStringItemArray              prcListActTpDescTxt_BP;

    /** PRC_LIST_ACT_TP_CD_BS*/
	public final EZDSStringItem              prcListActTpCd_BS;

    /** PRC_LISTS_DESC_TXT_B*/
	public final EZDSStringItem              prcListsDescTxt_B;

    /** RETAN_ORIG_PRC_FLG_B*/
	public final EZDSStringItem              retanOrigPrcFlg_B;

    /** NEW_PRC_AMT_B*/
	public final EZDSBigDecimalItem              newPrcAmt_B;

    /** NEW_MIN_PRC_AMT_B*/
	public final EZDSBigDecimalItem              newMinPrcAmt_B;

    /** NEW_MAX_PRC_AMT_B*/
	public final EZDSBigDecimalItem              newMaxPrcAmt_B;

    /** NEW_MLY_PMT_AMT_B*/
	public final EZDSBigDecimalItem              newMlyPmtAmt_B;

    /** NEW_MIN_LEASE_PMT_AMT_B*/
	public final EZDSBigDecimalItem              newMinLeasePmtAmt_B;

    /** NEW_MAX_LEASE_PMT_AMT_B*/
	public final EZDSBigDecimalItem              newMaxLeasePmtAmt_B;

    /** SUBMT_FLG_B*/
	public final EZDSStringItem              submtFlg_B;

    /** RQST_DSCD_FLG_B*/
	public final EZDSStringItem              rqstDscdFlg_B;

    /** XX_NUM_B*/
	public final EZDSBigDecimalItem              xxNum_B;


	/**
	 * NMAL7090_XSMsg is constructor.
	 * The initialization when the instance of NMAL7090_XSMsg is generated.
	 */
	public NMAL7090_XSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7090_XSMsg is constructor.
	 * The initialization when the instance of NMAL7090_XSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7090_XSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcListEquipRqstPk_B = (EZDSBigDecimalItem)newItem("prcListEquipRqstPk_B");
		oldMdseCd_B = (EZDSStringItem)newItem("oldMdseCd_B");
		oldMdseDescShortTxt_B = (EZDSStringItem)newItem("oldMdseDescShortTxt_B");
		oldTmthTotStdCostAmt_B = (EZDSBigDecimalItem)newItem("oldTmthTotStdCostAmt_B");
		newMdseCd_B = (EZDSStringItem)newItem("newMdseCd_B");
		newMdseDescShortTxt_B = (EZDSStringItem)newItem("newMdseDescShortTxt_B");
		newTmthTotStdCostAmt_B = (EZDSBigDecimalItem)newItem("newTmthTotStdCostAmt_B");
		prcListTpCd_BP = (EZDSStringItemArray)newItemArray("prcListTpCd_BP");
		prcListTpDescTxt_BP = (EZDSStringItemArray)newItemArray("prcListTpDescTxt_BP");
		prcListTpCd_BS = (EZDSStringItem)newItem("prcListTpCd_BS");
		prcListActTpCd_BP = (EZDSStringItemArray)newItemArray("prcListActTpCd_BP");
		prcListActTpDescTxt_BP = (EZDSStringItemArray)newItemArray("prcListActTpDescTxt_BP");
		prcListActTpCd_BS = (EZDSStringItem)newItem("prcListActTpCd_BS");
		prcListsDescTxt_B = (EZDSStringItem)newItem("prcListsDescTxt_B");
		retanOrigPrcFlg_B = (EZDSStringItem)newItem("retanOrigPrcFlg_B");
		newPrcAmt_B = (EZDSBigDecimalItem)newItem("newPrcAmt_B");
		newMinPrcAmt_B = (EZDSBigDecimalItem)newItem("newMinPrcAmt_B");
		newMaxPrcAmt_B = (EZDSBigDecimalItem)newItem("newMaxPrcAmt_B");
		newMlyPmtAmt_B = (EZDSBigDecimalItem)newItem("newMlyPmtAmt_B");
		newMinLeasePmtAmt_B = (EZDSBigDecimalItem)newItem("newMinLeasePmtAmt_B");
		newMaxLeasePmtAmt_B = (EZDSBigDecimalItem)newItem("newMaxLeasePmtAmt_B");
		submtFlg_B = (EZDSStringItem)newItem("submtFlg_B");
		rqstDscdFlg_B = (EZDSStringItem)newItem("rqstDscdFlg_B");
		xxNum_B = (EZDSBigDecimalItem)newItem("xxNum_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7090_XSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7090_XSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcListEquipRqstPk_B", "prcListEquipRqstPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"oldMdseCd_B", "oldMdseCd_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"oldMdseDescShortTxt_B", "oldMdseDescShortTxt_B", "B", null, TYPE_HANKAKUEISU, "250", null},
	{"oldTmthTotStdCostAmt_B", "oldTmthTotStdCostAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMdseCd_B", "newMdseCd_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"newMdseDescShortTxt_B", "newMdseDescShortTxt_B", "B", null, TYPE_HANKAKUEISU, "250", null},
	{"newTmthTotStdCostAmt_B", "newTmthTotStdCostAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcListTpCd_BP", "prcListTpCd_BP", "BP", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_BP", "prcListTpDescTxt_BP", "BP", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListTpCd_BS", "prcListTpCd_BS", "BS", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpCd_BP", "prcListActTpCd_BP", "BP", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpDescTxt_BP", "prcListActTpDescTxt_BP", "BP", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListActTpCd_BS", "prcListActTpCd_BS", "BS", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListsDescTxt_B", "prcListsDescTxt_B", "B", null, TYPE_HANKAKUEISU, "4000", null},
	{"retanOrigPrcFlg_B", "retanOrigPrcFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"newPrcAmt_B", "newPrcAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMinPrcAmt_B", "newMinPrcAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMaxPrcAmt_B", "newMaxPrcAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMlyPmtAmt_B", "newMlyPmtAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMinLeasePmtAmt_B", "newMinLeasePmtAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMaxLeasePmtAmt_B", "newMaxLeasePmtAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"submtFlg_B", "submtFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"rqstDscdFlg_B", "rqstDscdFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxNum_B", "xxNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_LIST_EQUIP_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipRqstPk_B
        {"OLD_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_B
        {"OLD_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseDescShortTxt_B
        {"OLD_TMTH_TOT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldTmthTotStdCostAmt_B
        {"NEW_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseCd_B
        {"NEW_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseDescShortTxt_B
        {"NEW_TMTH_TOT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newTmthTotStdCostAmt_B
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_BP
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_BP
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_BS
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_BP
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_BP
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_BS
        {"PRC_LISTS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListsDescTxt_B
        {"RETAN_ORIG_PRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//retanOrigPrcFlg_B
        {"NEW_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newPrcAmt_B
        {"NEW_MIN_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMinPrcAmt_B
        {"NEW_MAX_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMaxPrcAmt_B
        {"NEW_MLY_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMlyPmtAmt_B
        {"NEW_MIN_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMinLeasePmtAmt_B
        {"NEW_MAX_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMaxLeasePmtAmt_B
        {"SUBMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//submtFlg_B
        {"RQST_DSCD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstDscdFlg_B
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_B
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

