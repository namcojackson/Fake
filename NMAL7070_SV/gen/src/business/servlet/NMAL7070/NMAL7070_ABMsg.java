//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160407141007000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7070_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7070 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7070_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SPLY_AGMT_PLN_PK_A*/
	public final EZDBBigDecimalItem              splyAgmtPlnPk_A;

    /** SPLY_AGMT_PLN_NM_A*/
	public final EZDBStringItem              splyAgmtPlnNm_A;

    /** SPLY_AGMT_PLN_SHORT_NM_A*/
	public final EZDBStringItem              splyAgmtPlnShortNm_A;

    /** SPLY_AGMT_PLN_DESC_TXT_A*/
	public final EZDBStringItem              splyAgmtPlnDescTxt_A;

    /** SPLY_AGMT_PLN_TP_NM_A*/
	public final EZDBStringItem              splyAgmtPlnTpNm_A;

    /** XX_SCR_ITEM_8_TXT_A*/
	public final EZDBStringItem              xxScrItem8Txt_A;

    /** SPLY_AGMT_DOC_TP_NM_A*/
	public final EZDBStringItem              splyAgmtDocTpNm_A;

    /** ANN_TERM_CAP_NUM_A*/
	public final EZDBBigDecimalItem              annTermCapNum_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** XX_DT_10_DT_CD*/
	public final EZDBDateItem              xxDt10Dt_CD;

    /** XX_FULL_NM_CB*/
	public final EZDBStringItem              xxFullNm_CB;

    /** XX_DT_10_DT_UD*/
	public final EZDBDateItem              xxDt10Dt_UD;

    /** XX_FULL_NM_UB*/
	public final EZDBStringItem              xxFullNm_UB;


	/**
	 * NMAL7070_ABMsg is constructor.
	 * The initialization when the instance of NMAL7070_ABMsg is generated.
	 */
	public NMAL7070_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7070_ABMsg is constructor.
	 * The initialization when the instance of NMAL7070_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7070_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		splyAgmtPlnPk_A = (EZDBBigDecimalItem)newItem("splyAgmtPlnPk_A");
		splyAgmtPlnNm_A = (EZDBStringItem)newItem("splyAgmtPlnNm_A");
		splyAgmtPlnShortNm_A = (EZDBStringItem)newItem("splyAgmtPlnShortNm_A");
		splyAgmtPlnDescTxt_A = (EZDBStringItem)newItem("splyAgmtPlnDescTxt_A");
		splyAgmtPlnTpNm_A = (EZDBStringItem)newItem("splyAgmtPlnTpNm_A");
		xxScrItem8Txt_A = (EZDBStringItem)newItem("xxScrItem8Txt_A");
		splyAgmtDocTpNm_A = (EZDBStringItem)newItem("splyAgmtDocTpNm_A");
		annTermCapNum_A = (EZDBBigDecimalItem)newItem("annTermCapNum_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		xxDt10Dt_CD = (EZDBDateItem)newItem("xxDt10Dt_CD");
		xxFullNm_CB = (EZDBStringItem)newItem("xxFullNm_CB");
		xxDt10Dt_UD = (EZDBDateItem)newItem("xxDt10Dt_UD");
		xxFullNm_UB = (EZDBStringItem)newItem("xxFullNm_UB");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7070_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7070_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"splyAgmtPlnPk_A", "splyAgmtPlnPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyAgmtPlnNm_A", "splyAgmtPlnNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnShortNm_A", "splyAgmtPlnShortNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnDescTxt_A", "splyAgmtPlnDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnTpNm_A", "splyAgmtPlnTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem8Txt_A", "xxScrItem8Txt_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"splyAgmtDocTpNm_A", "splyAgmtDocTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"annTermCapNum_A", "annTermCapNum_A", "A", null, TYPE_SEISU_SYOSU, "9", "0"},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDt10Dt_CD", "xxDt10Dt_CD", "CD", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_CB", "xxFullNm_CB", "CB", null, TYPE_HANKAKUEISU, "100", null},
	{"xxDt10Dt_UD", "xxDt10Dt_UD", "UD", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_UB", "xxFullNm_UB", "UB", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SPLY_AGMT_PLN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnPk_A
        {"SPLY_AGMT_PLN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnNm_A
        {"SPLY_AGMT_PLN_SHORT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnShortNm_A
        {"SPLY_AGMT_PLN_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnDescTxt_A
        {"SPLY_AGMT_PLN_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpNm_A
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_A
        {"SPLY_AGMT_DOC_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpNm_A
        {"ANN_TERM_CAP_NUM",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//annTermCapNum_A
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"XX_DT_10_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxDt10Dt_CD
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_CB
        {"XX_DT_10_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxDt10Dt_UD
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_UB
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
