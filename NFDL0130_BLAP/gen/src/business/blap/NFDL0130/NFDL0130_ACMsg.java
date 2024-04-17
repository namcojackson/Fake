//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160912182546000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0130_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0130 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0130_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_WRK_ITEM_CD_A*/
	public final EZDCStringItem              cltWrkItemCd_A;

    /** CLT_WRK_ITEM_NM_A*/
	public final EZDCStringItem              cltWrkItemNm_A;

    /** CLT_WRK_TP_CD_SV*/
	public final EZDCStringItem              cltWrkTpCd_SV;

    /** CLT_WRK_TP_CD_CD*/
	public final EZDCStringItemArray              cltWrkTpCd_CD;

    /** CLT_WRK_TP_NM_SC*/
	public final EZDCStringItemArray              cltWrkTpNm_SC;

    /** CLT_WRK_CATG_CD_A*/
	public final EZDCStringItem              cltWrkCatgCd_A;

    /** CLT_WRK_CATG_NM_A*/
	public final EZDCStringItem              cltWrkCatgNm_A;

    /** CLT_WRK_WAIT_DAYS_AOT_A*/
	public final EZDCBigDecimalItem              cltWrkWaitDaysAot_A;

    /** CLT_WRK_ESCL_WAIT_DAYS_AOT_A*/
	public final EZDCBigDecimalItem              cltWrkEsclWaitDaysAot_A;

    /** CLT_WRK_OPT_ITEM_FLG_A*/
	public final EZDCStringItem              cltWrkOptItemFlg_A;

    /** CLT_WRK_ESCL_FLG_A*/
	public final EZDCStringItem              cltWrkEsclFlg_A;

    /** CLT_WRK_NTFY_FLG_A*/
	public final EZDCStringItem              cltWrkNtfyFlg_A;

    /** CLT_WRK_DESC_TXT_A*/
	public final EZDCStringItem              cltWrkDescTxt_A;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;

    /** XX_TP_CD_A*/
	public final EZDCStringItem              xxTpCd_A;


	/**
	 * NFDL0130_ACMsg is constructor.
	 * The initialization when the instance of NFDL0130_ACMsg is generated.
	 */
	public NFDL0130_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0130_ACMsg is constructor.
	 * The initialization when the instance of NFDL0130_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0130_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltWrkItemCd_A = (EZDCStringItem)newItem("cltWrkItemCd_A");
		cltWrkItemNm_A = (EZDCStringItem)newItem("cltWrkItemNm_A");
		cltWrkTpCd_SV = (EZDCStringItem)newItem("cltWrkTpCd_SV");
		cltWrkTpCd_CD = (EZDCStringItemArray)newItemArray("cltWrkTpCd_CD");
		cltWrkTpNm_SC = (EZDCStringItemArray)newItemArray("cltWrkTpNm_SC");
		cltWrkCatgCd_A = (EZDCStringItem)newItem("cltWrkCatgCd_A");
		cltWrkCatgNm_A = (EZDCStringItem)newItem("cltWrkCatgNm_A");
		cltWrkWaitDaysAot_A = (EZDCBigDecimalItem)newItem("cltWrkWaitDaysAot_A");
		cltWrkEsclWaitDaysAot_A = (EZDCBigDecimalItem)newItem("cltWrkEsclWaitDaysAot_A");
		cltWrkOptItemFlg_A = (EZDCStringItem)newItem("cltWrkOptItemFlg_A");
		cltWrkEsclFlg_A = (EZDCStringItem)newItem("cltWrkEsclFlg_A");
		cltWrkNtfyFlg_A = (EZDCStringItem)newItem("cltWrkNtfyFlg_A");
		cltWrkDescTxt_A = (EZDCStringItem)newItem("cltWrkDescTxt_A");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
		xxTpCd_A = (EZDCStringItem)newItem("xxTpCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0130_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0130_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltWrkItemCd_A", "cltWrkItemCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"cltWrkItemNm_A", "cltWrkItemNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"cltWrkTpCd_SV", "cltWrkTpCd_SV", "SV", null, TYPE_HANKAKUEISU, "2", null},
	{"cltWrkTpCd_CD", "cltWrkTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"cltWrkTpNm_SC", "cltWrkTpNm_SC", "SC", "99", TYPE_HANKAKUEISU, "100", null},
	{"cltWrkCatgCd_A", "cltWrkCatgCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"cltWrkCatgNm_A", "cltWrkCatgNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"cltWrkWaitDaysAot_A", "cltWrkWaitDaysAot_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cltWrkEsclWaitDaysAot_A", "cltWrkEsclWaitDaysAot_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cltWrkOptItemFlg_A", "cltWrkOptItemFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"cltWrkEsclFlg_A", "cltWrkEsclFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"cltWrkNtfyFlg_A", "cltWrkNtfyFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"cltWrkDescTxt_A", "cltWrkDescTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxTpCd_A", "xxTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_WRK_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkItemCd_A
        {"CLT_WRK_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkItemNm_A
        {"CLT_WRK_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkTpCd_SV
        {"CLT_WRK_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkTpCd_CD
        {"CLT_WRK_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkTpNm_SC
        {"CLT_WRK_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkCatgCd_A
        {"CLT_WRK_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkCatgNm_A
        {"CLT_WRK_WAIT_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkWaitDaysAot_A
        {"CLT_WRK_ESCL_WAIT_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkEsclWaitDaysAot_A
        {"CLT_WRK_OPT_ITEM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkOptItemFlg_A
        {"CLT_WRK_ESCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkEsclFlg_A
        {"CLT_WRK_NTFY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkNtfyFlg_A
        {"CLT_WRK_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkDescTxt_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_A
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

