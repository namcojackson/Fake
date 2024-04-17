//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221122095106000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0730_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0730 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0730_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** AR_ADJ_NUM_A1*/
	public final EZDBStringItem              arAdjNum_A1;

    /** AR_ADJ_TP_CD_A1*/
	public final EZDBStringItem              arAdjTpCd_A1;

    /** AR_ADJ_TP_DESC_TXT_A1*/
	public final EZDBStringItem              arAdjTpDescTxt_A1;

    /** GL_DT_A1*/
	public final EZDBDateItem              glDt_A1;

    /** DEAL_APPLY_AMT_A1*/
	public final EZDBBigDecimalItem              dealApplyAmt_A1;

    /** ADJ_CMNT_TXT_A1*/
	public final EZDBStringItem              adjCmntTxt_A1;

    /** AR_WRT_OFF_NOTE_TXT_A1*/
	public final EZDBStringItem              arWrtOffNoteTxt_A1;

    /** XX_CMNT_TXT_A1*/
	public final EZDBStringItem              xxCmntTxt_A1;

    /** _EZInUserID_A1*/
	public final EZDBStringItem              ezInUserID_A1;


	/**
	 * NFCL0730_ABMsg is constructor.
	 * The initialization when the instance of NFCL0730_ABMsg is generated.
	 */
	public NFCL0730_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0730_ABMsg is constructor.
	 * The initialization when the instance of NFCL0730_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0730_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		arAdjNum_A1 = (EZDBStringItem)newItem("arAdjNum_A1");
		arAdjTpCd_A1 = (EZDBStringItem)newItem("arAdjTpCd_A1");
		arAdjTpDescTxt_A1 = (EZDBStringItem)newItem("arAdjTpDescTxt_A1");
		glDt_A1 = (EZDBDateItem)newItem("glDt_A1");
		dealApplyAmt_A1 = (EZDBBigDecimalItem)newItem("dealApplyAmt_A1");
		adjCmntTxt_A1 = (EZDBStringItem)newItem("adjCmntTxt_A1");
		arWrtOffNoteTxt_A1 = (EZDBStringItem)newItem("arWrtOffNoteTxt_A1");
		xxCmntTxt_A1 = (EZDBStringItem)newItem("xxCmntTxt_A1");
		ezInUserID_A1 = (EZDBStringItem)newItem("ezInUserID_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0730_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0730_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"arAdjNum_A1", "arAdjNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"arAdjTpCd_A1", "arAdjTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpDescTxt_A1", "arAdjTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"glDt_A1", "glDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dealApplyAmt_A1", "dealApplyAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"adjCmntTxt_A1", "adjCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "65", null},
	{"arWrtOffNoteTxt_A1", "arWrtOffNoteTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCmntTxt_A1", "xxCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezInUserID_A1", "ezInUserID_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"AR_ADJ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjNum_A1
        {"AR_ADJ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_A1
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_A1
        {"GL_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//glDt_A1
        {"DEAL_APPLY_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAmt_A1
        {"ADJ_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCmntTxt_A1
        {"AR_WRT_OFF_NOTE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteTxt_A1
        {"XX_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A1
        {"_EZInUserID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInUserID_A1
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
