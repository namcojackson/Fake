//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160512151637000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7090_CSMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL7090_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** OLD_MDSE_CD_C*/
	public final EZDSStringItem              oldMdseCd_C;

    /** OLD_MDSE_DESC_SHORT_TXT_C*/
	public final EZDSStringItem              oldMdseDescShortTxt_C;

    /** OLD_TMTH_TOT_STD_COST_AMT_C*/
	public final EZDSBigDecimalItem              oldTmthTotStdCostAmt_C;

    /** NEW_MDSE_CD_C*/
	public final EZDSStringItem              newMdseCd_C;

    /** NEW_MDSE_DESC_SHORT_TXT_C*/
	public final EZDSStringItem              newMdseDescShortTxt_C;

    /** NEW_TMTH_TOT_STD_COST_AMT_C*/
	public final EZDSBigDecimalItem              newTmthTotStdCostAmt_C;

    /** PRC_LIST_TP_CD_C*/
	public final EZDSStringItem              prcListTpCd_C;

    /** PRC_LIST_TP_DESC_TXT_C*/
	public final EZDSStringItem              prcListTpDescTxt_C;

    /** PRC_LIST_ACT_TP_CD_C*/
	public final EZDSStringItem              prcListActTpCd_C;

    /** PRC_LIST_ACT_TP_DESC_TXT_C*/
	public final EZDSStringItem              prcListActTpDescTxt_C;

    /** PRC_LISTS_DESC_TXT_C*/
	public final EZDSStringItem              prcListsDescTxt_C;

    /** RETAN_ORIG_PRC_FLG_C*/
	public final EZDSStringItem              retanOrigPrcFlg_C;

    /** NEW_PRC_AMT_C*/
	public final EZDSBigDecimalItem              newPrcAmt_C;

    /** NEW_MIN_PRC_AMT_C*/
	public final EZDSBigDecimalItem              newMinPrcAmt_C;

    /** NEW_MAX_PRC_AMT_C*/
	public final EZDSBigDecimalItem              newMaxPrcAmt_C;

    /** NEW_MLY_PMT_AMT_C*/
	public final EZDSBigDecimalItem              newMlyPmtAmt_C;

    /** NEW_MIN_LEASE_PMT_AMT_C*/
	public final EZDSBigDecimalItem              newMinLeasePmtAmt_C;

    /** NEW_MAX_LEASE_PMT_AMT_C*/
	public final EZDSBigDecimalItem              newMaxLeasePmtAmt_C;

    /** RQST_STS_TP_CD_C*/
	public final EZDSStringItem              rqstStsTpCd_C;

    /** RQST_STS_TP_DESC_TXT_C*/
	public final EZDSStringItem              rqstStsTpDescTxt_C;

    /** PRC_LIST_EQUIP_RQST_PK_C*/
	public final EZDSBigDecimalItem              prcListEquipRqstPk_C;

    /** CRAT_USR_ID_C*/
	public final EZDSStringItem              cratUsrId_C;

    /** FIRST_NM_CC*/
	public final EZDSStringItem              firstNm_CC;

    /** LAST_NM_CC*/
	public final EZDSStringItem              lastNm_CC;

    /** XX_ALL_PSN_NM_CC*/
	public final EZDSStringItem              xxAllPsnNm_CC;

    /** CRAT_DT_C*/
	public final EZDSDateItem              cratDt_C;

    /** LAST_UPD_USR_ID_C*/
	public final EZDSStringItem              lastUpdUsrId_C;

    /** FIRST_NM_CU*/
	public final EZDSStringItem              firstNm_CU;

    /** LAST_NM_CU*/
	public final EZDSStringItem              lastNm_CU;

    /** XX_ALL_PSN_NM_CU*/
	public final EZDSStringItem              xxAllPsnNm_CU;

    /** LAST_UPD_DT_C*/
	public final EZDSDateItem              lastUpdDt_C;


	/**
	 * NMAL7090_CSMsg is constructor.
	 * The initialization when the instance of NMAL7090_CSMsg is generated.
	 */
	public NMAL7090_CSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7090_CSMsg is constructor.
	 * The initialization when the instance of NMAL7090_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7090_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		oldMdseCd_C = (EZDSStringItem)newItem("oldMdseCd_C");
		oldMdseDescShortTxt_C = (EZDSStringItem)newItem("oldMdseDescShortTxt_C");
		oldTmthTotStdCostAmt_C = (EZDSBigDecimalItem)newItem("oldTmthTotStdCostAmt_C");
		newMdseCd_C = (EZDSStringItem)newItem("newMdseCd_C");
		newMdseDescShortTxt_C = (EZDSStringItem)newItem("newMdseDescShortTxt_C");
		newTmthTotStdCostAmt_C = (EZDSBigDecimalItem)newItem("newTmthTotStdCostAmt_C");
		prcListTpCd_C = (EZDSStringItem)newItem("prcListTpCd_C");
		prcListTpDescTxt_C = (EZDSStringItem)newItem("prcListTpDescTxt_C");
		prcListActTpCd_C = (EZDSStringItem)newItem("prcListActTpCd_C");
		prcListActTpDescTxt_C = (EZDSStringItem)newItem("prcListActTpDescTxt_C");
		prcListsDescTxt_C = (EZDSStringItem)newItem("prcListsDescTxt_C");
		retanOrigPrcFlg_C = (EZDSStringItem)newItem("retanOrigPrcFlg_C");
		newPrcAmt_C = (EZDSBigDecimalItem)newItem("newPrcAmt_C");
		newMinPrcAmt_C = (EZDSBigDecimalItem)newItem("newMinPrcAmt_C");
		newMaxPrcAmt_C = (EZDSBigDecimalItem)newItem("newMaxPrcAmt_C");
		newMlyPmtAmt_C = (EZDSBigDecimalItem)newItem("newMlyPmtAmt_C");
		newMinLeasePmtAmt_C = (EZDSBigDecimalItem)newItem("newMinLeasePmtAmt_C");
		newMaxLeasePmtAmt_C = (EZDSBigDecimalItem)newItem("newMaxLeasePmtAmt_C");
		rqstStsTpCd_C = (EZDSStringItem)newItem("rqstStsTpCd_C");
		rqstStsTpDescTxt_C = (EZDSStringItem)newItem("rqstStsTpDescTxt_C");
		prcListEquipRqstPk_C = (EZDSBigDecimalItem)newItem("prcListEquipRqstPk_C");
		cratUsrId_C = (EZDSStringItem)newItem("cratUsrId_C");
		firstNm_CC = (EZDSStringItem)newItem("firstNm_CC");
		lastNm_CC = (EZDSStringItem)newItem("lastNm_CC");
		xxAllPsnNm_CC = (EZDSStringItem)newItem("xxAllPsnNm_CC");
		cratDt_C = (EZDSDateItem)newItem("cratDt_C");
		lastUpdUsrId_C = (EZDSStringItem)newItem("lastUpdUsrId_C");
		firstNm_CU = (EZDSStringItem)newItem("firstNm_CU");
		lastNm_CU = (EZDSStringItem)newItem("lastNm_CU");
		xxAllPsnNm_CU = (EZDSStringItem)newItem("xxAllPsnNm_CU");
		lastUpdDt_C = (EZDSDateItem)newItem("lastUpdDt_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7090_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7090_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"oldMdseCd_C", "oldMdseCd_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"oldMdseDescShortTxt_C", "oldMdseDescShortTxt_C", "C", null, TYPE_HANKAKUEISU, "250", null},
	{"oldTmthTotStdCostAmt_C", "oldTmthTotStdCostAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMdseCd_C", "newMdseCd_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"newMdseDescShortTxt_C", "newMdseDescShortTxt_C", "C", null, TYPE_HANKAKUEISU, "250", null},
	{"newTmthTotStdCostAmt_C", "newTmthTotStdCostAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcListTpCd_C", "prcListTpCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_C", "prcListTpDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"prcListActTpCd_C", "prcListActTpCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpDescTxt_C", "prcListActTpDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"prcListsDescTxt_C", "prcListsDescTxt_C", "C", null, TYPE_HANKAKUEISU, "4000", null},
	{"retanOrigPrcFlg_C", "retanOrigPrcFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"newPrcAmt_C", "newPrcAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMinPrcAmt_C", "newMinPrcAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMaxPrcAmt_C", "newMaxPrcAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMlyPmtAmt_C", "newMlyPmtAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMinLeasePmtAmt_C", "newMinLeasePmtAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newMaxLeasePmtAmt_C", "newMaxLeasePmtAmt_C", "C", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rqstStsTpCd_C", "rqstStsTpCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"rqstStsTpDescTxt_C", "rqstStsTpDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"prcListEquipRqstPk_C", "prcListEquipRqstPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cratUsrId_C", "cratUsrId_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"firstNm_CC", "firstNm_CC", "CC", null, TYPE_HANKAKUEISU, "30", null},
	{"lastNm_CC", "lastNm_CC", "CC", null, TYPE_HANKAKUEISU, "30", null},
	{"xxAllPsnNm_CC", "xxAllPsnNm_CC", "CC", null, TYPE_HANKAKUEISU, "99", null},
	{"cratDt_C", "cratDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	{"lastUpdUsrId_C", "lastUpdUsrId_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"firstNm_CU", "firstNm_CU", "CU", null, TYPE_HANKAKUEISU, "30", null},
	{"lastNm_CU", "lastNm_CU", "CU", null, TYPE_HANKAKUEISU, "30", null},
	{"xxAllPsnNm_CU", "xxAllPsnNm_CU", "CU", null, TYPE_HANKAKUEISU, "99", null},
	{"lastUpdDt_C", "lastUpdDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"OLD_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_C
        {"OLD_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseDescShortTxt_C
        {"OLD_TMTH_TOT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldTmthTotStdCostAmt_C
        {"NEW_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseCd_C
        {"NEW_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseDescShortTxt_C
        {"NEW_TMTH_TOT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newTmthTotStdCostAmt_C
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_C
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_C
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_C
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_C
        {"PRC_LISTS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListsDescTxt_C
        {"RETAN_ORIG_PRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//retanOrigPrcFlg_C
        {"NEW_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newPrcAmt_C
        {"NEW_MIN_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMinPrcAmt_C
        {"NEW_MAX_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMaxPrcAmt_C
        {"NEW_MLY_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMlyPmtAmt_C
        {"NEW_MIN_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMinLeasePmtAmt_C
        {"NEW_MAX_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMaxLeasePmtAmt_C
        {"RQST_STS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpCd_C
        {"RQST_STS_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpDescTxt_C
        {"PRC_LIST_EQUIP_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipRqstPk_C
        {"CRAT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratUsrId_C
        {"FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstNm_CC
        {"LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastNm_CC
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm_CC
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt_C
        {"LAST_UPD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastUpdUsrId_C
        {"FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstNm_CU
        {"LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastNm_CU
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm_CU
        {"LAST_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastUpdDt_C
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

