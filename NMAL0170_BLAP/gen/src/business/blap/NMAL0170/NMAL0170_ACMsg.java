//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170213142338000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0170_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0170_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** SUPD_TO_MDSE_CD_A1*/
	public final EZDCStringItem              supdToMdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_AT*/
	public final EZDCStringItem              mdseDescShortTxt_AT;

    /** SUPD_FROM_MDSE_CD_A1*/
	public final EZDCStringItem              supdFromMdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_AF*/
	public final EZDCStringItem              mdseDescShortTxt_AF;

    /** SUPD_RELN_STAGE_DT_A1*/
	public final EZDCDateItem              supdRelnStageDt_A1;

    /** MDSE_ITEM_TP_NM_A1*/
	public final EZDCStringItem              mdseItemTpNm_A1;

    /** MDSE_ITEM_CLS_TP_NM_A1*/
	public final EZDCStringItem              mdseItemClsTpNm_A1;

    /** SUPD_RELN_STAGE_PK_A1*/
	public final EZDCBigDecimalItem              supdRelnStagePk_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDCStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDCStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDCStringItem              xxRecHistTblNm_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDCStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL0170_ACMsg is constructor.
	 * The initialization when the instance of NMAL0170_ACMsg is generated.
	 */
	public NMAL0170_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0170_ACMsg is constructor.
	 * The initialization when the instance of NMAL0170_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0170_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		supdToMdseCd_A1 = (EZDCStringItem)newItem("supdToMdseCd_A1");
		mdseDescShortTxt_AT = (EZDCStringItem)newItem("mdseDescShortTxt_AT");
		supdFromMdseCd_A1 = (EZDCStringItem)newItem("supdFromMdseCd_A1");
		mdseDescShortTxt_AF = (EZDCStringItem)newItem("mdseDescShortTxt_AF");
		supdRelnStageDt_A1 = (EZDCDateItem)newItem("supdRelnStageDt_A1");
		mdseItemTpNm_A1 = (EZDCStringItem)newItem("mdseItemTpNm_A1");
		mdseItemClsTpNm_A1 = (EZDCStringItem)newItem("mdseItemClsTpNm_A1");
		supdRelnStagePk_A1 = (EZDCBigDecimalItem)newItem("supdRelnStagePk_A1");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
		ezUpTimeZone_A1 = (EZDCStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0170_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0170_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"supdToMdseCd_A1", "supdToMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_AT", "mdseDescShortTxt_AT", "AT", null, TYPE_HANKAKUEISU, "250", null},
	{"supdFromMdseCd_A1", "supdFromMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_AF", "mdseDescShortTxt_AF", "AF", null, TYPE_HANKAKUEISU, "250", null},
	{"supdRelnStageDt_A1", "supdRelnStageDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseItemTpNm_A1", "mdseItemTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"mdseItemClsTpNm_A1", "mdseItemClsTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"supdRelnStagePk_A1", "supdRelnStagePk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"SUPD_TO_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdToMdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_AT
        {"SUPD_FROM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFromMdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_AF
        {"SUPD_RELN_STAGE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdRelnStageDt_A1
        {"MDSE_ITEM_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_A1
        {"MDSE_ITEM_CLS_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpNm_A1
        {"SUPD_RELN_STAGE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdRelnStagePk_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

