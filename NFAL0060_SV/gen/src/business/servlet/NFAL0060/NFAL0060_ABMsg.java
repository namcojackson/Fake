//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160324114211000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0060_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0060_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

    /** AJE_PTRN_IND_TP_CD_A*/
	public final EZDBStringItem              ajePtrnIndTpCd_A;

    /** AJE_PTRN_IND_TP_NM_A*/
	public final EZDBStringItem              ajePtrnIndTpNm_A;

    /** AJE_PTRN_ACTL_CD_A*/
	public final EZDBStringItem              ajePtrnActlCd_A;

    /** AJE_PTRN_ACTL_NM_A*/
	public final EZDBStringItem              ajePtrnActlNm_A;

    /** AJE_INTFC_COL_TXT_A*/
	public final EZDBStringItem              ajeIntfcColTxt_A;


	/**
	 * NFAL0060_ABMsg is constructor.
	 * The initialization when the instance of NFAL0060_ABMsg is generated.
	 */
	public NFAL0060_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0060_ABMsg is constructor.
	 * The initialization when the instance of NFAL0060_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0060_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		ajePtrnIndTpCd_A = (EZDBStringItem)newItem("ajePtrnIndTpCd_A");
		ajePtrnIndTpNm_A = (EZDBStringItem)newItem("ajePtrnIndTpNm_A");
		ajePtrnActlCd_A = (EZDBStringItem)newItem("ajePtrnActlCd_A");
		ajePtrnActlNm_A = (EZDBStringItem)newItem("ajePtrnActlNm_A");
		ajeIntfcColTxt_A = (EZDBStringItem)newItem("ajeIntfcColTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0060_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0060_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"ajePtrnIndTpCd_A", "ajePtrnIndTpCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpNm_A", "ajePtrnIndTpNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"ajePtrnActlCd_A", "ajePtrnActlCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlNm_A", "ajePtrnActlNm_A", "A", null, TYPE_HANKAKUEISU, "70", null},
	{"ajeIntfcColTxt_A", "ajeIntfcColTxt_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_A
        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_A
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_A
        {"AJE_PTRN_ACTL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_A
        {"AJE_INTFC_COL_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeIntfcColTxt_A
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

