//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230413135822000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1020_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL1020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL1020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL1020_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_AD*/
	public final EZDCStringItem              xxChkBox_AD;

    /** INVTY_ORD_LINE_NUM_A1*/
	public final EZDCStringItem              invtyOrdLineNum_A1;

    /** MDSE_CD_A1*/
	public final EZDCStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDCStringItem              mdseDescShortTxt_A1;

    /** STK_STS_CD_AP*/
	public final EZDCStringItem              stkStsCd_AP;

    /** INVTY_AVAL_QTY_AI*/
	public final EZDCBigDecimalItem              invtyAvalQty_AI;

    /** INVTY_AVAL_QTY_AO*/
	public final EZDCBigDecimalItem              invtyAvalQty_AO;

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


	/**
	 * NLCL1020_ACMsg is constructor.
	 * The initialization when the instance of NLCL1020_ACMsg is generated.
	 */
	public NLCL1020_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLCL1020_ACMsg is constructor.
	 * The initialization when the instance of NLCL1020_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL1020_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_AD = (EZDCStringItem)newItem("xxChkBox_AD");
		invtyOrdLineNum_A1 = (EZDCStringItem)newItem("invtyOrdLineNum_A1");
		mdseCd_A1 = (EZDCStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDCStringItem)newItem("mdseDescShortTxt_A1");
		stkStsCd_AP = (EZDCStringItem)newItem("stkStsCd_AP");
		invtyAvalQty_AI = (EZDCBigDecimalItem)newItem("invtyAvalQty_AI");
		invtyAvalQty_AO = (EZDCBigDecimalItem)newItem("invtyAvalQty_AO");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL1020_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL1020_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_AD", "xxChkBox_AD", "AD", null, TYPE_HANKAKUEIJI, "1", null},
	{"invtyOrdLineNum_A1", "invtyOrdLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"stkStsCd_AP", "stkStsCd_AP", "AP", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyAvalQty_AI", "invtyAvalQty_AI", "AI", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invtyAvalQty_AO", "invtyAvalQty_AO", "AO", null, TYPE_SEISU_SYOSU, "10", "0"},
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AD
        {"INVTY_ORD_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdLineNum_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_AP
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty_AI
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty_AO
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
