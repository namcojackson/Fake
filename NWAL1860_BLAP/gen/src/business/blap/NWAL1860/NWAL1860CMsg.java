//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160202175451000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1860CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1860 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1860CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TRX_LINE_NUM_LIST_TXT*/
	public final EZDCStringItem              xxTrxLineNumListTxt;

    /** ORD_QTY*/
	public final EZDCBigDecimalItem              ordQty;

    /** SHPG_INTVL_CD*/
	public final EZDCStringItem              shpgIntvlCd;

    /** SHPG_INTVL_CD_P*/
	public final EZDCStringItemArray              shpgIntvlCd_P;

    /** SHPG_INTVL_DESC_TXT_P*/
	public final EZDCStringItemArray              shpgIntvlDescTxt_P;

    /** XX_DAY*/
	public final EZDCStringItem              xxDay;

    /** XX_DAY_P1*/
	public final EZDCStringItemArray              xxDay_P1;

    /** XX_DAY_P2*/
	public final EZDCStringItemArray              xxDay_P2;

    /** XX_MTH_DT_P*/
	public final EZDCStringItemArray              xxMthDt_P;

    /** XX_MTH_DPLY_DESC_TXT_P*/
	public final EZDCStringItemArray              xxMthDplyDescTxt_P;

    /** XX_MTH_DT_ST*/
	public final EZDCStringItem              xxMthDt_ST;

    /** XX_MTH_DT_EN*/
	public final EZDCStringItem              xxMthDt_EN;

    /** XX_YR_DT_P1*/
	public final EZDCDateItemArray              xxYrDt_P1;

    /** XX_YR_DT_P2*/
	public final EZDCDateItemArray              xxYrDt_P2;

    /** XX_YR_DT_ST*/
	public final EZDCDateItem              xxYrDt_ST;

    /** XX_YR_DT_EN*/
	public final EZDCDateItem              xxYrDt_EN;

    /** SCHD_AGMT_VLD_FROM_DT*/
	public final EZDCDateItem              schdAgmtVldFromDt;

    /** SCHD_AGMT_VLD_THRU_DT*/
	public final EZDCDateItem              schdAgmtVldThruDt;

    /** PDD_DT*/
	public final EZDCDateItem              pddDt;

    /** SHPG_INTVL_MTH_NUM*/
	public final EZDCBigDecimalItem              shpgIntvlMthNum;


	/**
	 * NWAL1860CMsg is constructor.
	 * The initialization when the instance of NWAL1860CMsg is generated.
	 */
	public NWAL1860CMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1860CMsg is constructor.
	 * The initialization when the instance of NWAL1860CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1860CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTrxLineNumListTxt = (EZDCStringItem)newItem("xxTrxLineNumListTxt");
		ordQty = (EZDCBigDecimalItem)newItem("ordQty");
		shpgIntvlCd = (EZDCStringItem)newItem("shpgIntvlCd");
		shpgIntvlCd_P = (EZDCStringItemArray)newItemArray("shpgIntvlCd_P");
		shpgIntvlDescTxt_P = (EZDCStringItemArray)newItemArray("shpgIntvlDescTxt_P");
		xxDay = (EZDCStringItem)newItem("xxDay");
		xxDay_P1 = (EZDCStringItemArray)newItemArray("xxDay_P1");
		xxDay_P2 = (EZDCStringItemArray)newItemArray("xxDay_P2");
		xxMthDt_P = (EZDCStringItemArray)newItemArray("xxMthDt_P");
		xxMthDplyDescTxt_P = (EZDCStringItemArray)newItemArray("xxMthDplyDescTxt_P");
		xxMthDt_ST = (EZDCStringItem)newItem("xxMthDt_ST");
		xxMthDt_EN = (EZDCStringItem)newItem("xxMthDt_EN");
		xxYrDt_P1 = (EZDCDateItemArray)newItemArray("xxYrDt_P1");
		xxYrDt_P2 = (EZDCDateItemArray)newItemArray("xxYrDt_P2");
		xxYrDt_ST = (EZDCDateItem)newItem("xxYrDt_ST");
		xxYrDt_EN = (EZDCDateItem)newItem("xxYrDt_EN");
		schdAgmtVldFromDt = (EZDCDateItem)newItem("schdAgmtVldFromDt");
		schdAgmtVldThruDt = (EZDCDateItem)newItem("schdAgmtVldThruDt");
		pddDt = (EZDCDateItem)newItem("pddDt");
		shpgIntvlMthNum = (EZDCBigDecimalItem)newItem("shpgIntvlMthNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1860CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1860CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTrxLineNumListTxt", "xxTrxLineNumListTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shpgIntvlCd", "shpgIntvlCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"shpgIntvlCd_P", "shpgIntvlCd_P", "P", "99", TYPE_HANKAKUEISU, "10", null},
	{"shpgIntvlDescTxt_P", "shpgIntvlDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxDay", "xxDay", null, null, TYPE_HANKAKUSUJI, "2", null},
	{"xxDay_P1", "xxDay_P1", "P1", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxDay_P2", "xxDay_P2", "P2", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxMthDt_P", "xxMthDt_P", "P", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxMthDplyDescTxt_P", "xxMthDplyDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxMthDt_ST", "xxMthDt_ST", "ST", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxMthDt_EN", "xxMthDt_EN", "EN", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxYrDt_P1", "xxYrDt_P1", "P1", "99", TYPE_NEN, "4", null},
	{"xxYrDt_P2", "xxYrDt_P2", "P2", "99", TYPE_NEN, "4", null},
	{"xxYrDt_ST", "xxYrDt_ST", "ST", null, TYPE_NEN, "4", null},
	{"xxYrDt_EN", "xxYrDt_EN", "EN", null, TYPE_NEN, "4", null},
	{"schdAgmtVldFromDt", "schdAgmtVldFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"schdAgmtVldThruDt", "schdAgmtVldThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"pddDt", "pddDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"shpgIntvlMthNum", "shpgIntvlMthNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TRX_LINE_NUM_LIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxLineNumListTxt
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"SHPG_INTVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlCd
        {"SHPG_INTVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlCd_P
        {"SHPG_INTVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlDescTxt_P
        {"XX_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDay
        {"XX_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDay_P1
        {"XX_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDay_P2
        {"XX_MTH_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMthDt_P
        {"XX_MTH_DPLY_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMthDplyDescTxt_P
        {"XX_MTH_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMthDt_ST
        {"XX_MTH_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMthDt_EN
        {"XX_YR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYrDt_P1
        {"XX_YR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYrDt_P2
        {"XX_YR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYrDt_ST
        {"XX_YR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYrDt_EN
        {"SCHD_AGMT_VLD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtVldFromDt
        {"SCHD_AGMT_VLD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtVldThruDt
        {"PDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pddDt
        {"SHPG_INTVL_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlMthNum
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

