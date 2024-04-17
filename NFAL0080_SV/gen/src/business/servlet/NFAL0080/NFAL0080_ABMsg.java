//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100121185548000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0080_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0080_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** ELIG_COA_SEG_PTRN_CD_A*/
	public final EZDBStringItem              eligCoaSegPtrnCd_A;

    /** COA_SEG_LKUP_TP_CD_A1*/
	public final EZDBStringItemArray              coaSegLkupTpCd_A1;

    /** COA_SEG_LKUP_TP_CD_A2*/
	public final EZDBStringItemArray              coaSegLkupTpCd_A2;

    /** COA_SEG_LKUP_TP_CD_A3*/
	public final EZDBStringItem              coaSegLkupTpCd_A3;

    /** XX_CHK_BOX_OR*/
	public final EZDBStringItem              xxChkBox_OR;

    /** ELIG_COA_SEG_PTRN_CD_OR*/
	public final EZDBStringItem              eligCoaSegPtrnCd_OR;

    /** COA_SEG_LKUP_TP_CD_OR*/
	public final EZDBStringItem              coaSegLkupTpCd_OR;


	/**
	 * NFAL0080_ABMsg is constructor.
	 * The initialization when the instance of NFAL0080_ABMsg is generated.
	 */
	public NFAL0080_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0080_ABMsg is constructor.
	 * The initialization when the instance of NFAL0080_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0080_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		eligCoaSegPtrnCd_A = (EZDBStringItem)newItem("eligCoaSegPtrnCd_A");
		coaSegLkupTpCd_A1 = (EZDBStringItemArray)newItemArray("coaSegLkupTpCd_A1");
		coaSegLkupTpCd_A2 = (EZDBStringItemArray)newItemArray("coaSegLkupTpCd_A2");
		coaSegLkupTpCd_A3 = (EZDBStringItem)newItem("coaSegLkupTpCd_A3");
		xxChkBox_OR = (EZDBStringItem)newItem("xxChkBox_OR");
		eligCoaSegPtrnCd_OR = (EZDBStringItem)newItem("eligCoaSegPtrnCd_OR");
		coaSegLkupTpCd_OR = (EZDBStringItem)newItem("coaSegLkupTpCd_OR");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0080_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0080_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"eligCoaSegPtrnCd_A", "eligCoaSegPtrnCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"coaSegLkupTpCd_A1", "coaSegLkupTpCd_A1", "A1", "99", TYPE_HANKAKUEISU, "20", null},
	{"coaSegLkupTpCd_A2", "coaSegLkupTpCd_A2", "A2", "99", TYPE_HANKAKUEISU, "20", null},
	{"coaSegLkupTpCd_A3", "coaSegLkupTpCd_A3", "A3", null, TYPE_HANKAKUEISU, "20", null},
	{"xxChkBox_OR", "xxChkBox_OR", "OR", null, TYPE_HANKAKUEIJI, "1", null},
	{"eligCoaSegPtrnCd_OR", "eligCoaSegPtrnCd_OR", "OR", null, TYPE_HANKAKUEISU, "20", null},
	{"coaSegLkupTpCd_OR", "coaSegLkupTpCd_OR", "OR", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"ELIG_COA_SEG_PTRN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eligCoaSegPtrnCd_A
        {"COA_SEG_LKUP_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaSegLkupTpCd_A1
        {"COA_SEG_LKUP_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaSegLkupTpCd_A2
        {"COA_SEG_LKUP_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaSegLkupTpCd_A3
        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_OR
        {"ELIG_COA_SEG_PTRN_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eligCoaSegPtrnCd_OR
        {"COA_SEG_LKUP_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaSegLkupTpCd_OR
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
