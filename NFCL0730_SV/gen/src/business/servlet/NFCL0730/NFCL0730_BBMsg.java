//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221122095106000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0730_BBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NFCL0730_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_ADJ_NUM_B1*/
	public final EZDBStringItem              arAdjNum_B1;

    /** AR_ADJ_TP_CD_B1*/
	public final EZDBStringItem              arAdjTpCd_B1;

    /** AR_ADJ_TP_DESC_TXT_B1*/
	public final EZDBStringItem              arAdjTpDescTxt_B1;

    /** GL_DT_B1*/
	public final EZDBDateItem              glDt_B1;

    /** DEAL_APPLY_AMT_B1*/
	public final EZDBBigDecimalItem              dealApplyAmt_B1;

    /** ADJ_CMNT_TXT_B1*/
	public final EZDBStringItem              adjCmntTxt_B1;

    /** AR_WRT_OFF_NOTE_TXT_B1*/
	public final EZDBStringItem              arWrtOffNoteTxt_B1;

    /** XX_CMNT_TXT_B1*/
	public final EZDBStringItem              xxCmntTxt_B1;

    /** _EZInUserID_B1*/
	public final EZDBStringItem              ezInUserID_B1;


	/**
	 * NFCL0730_BBMsg is constructor.
	 * The initialization when the instance of NFCL0730_BBMsg is generated.
	 */
	public NFCL0730_BBMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0730_BBMsg is constructor.
	 * The initialization when the instance of NFCL0730_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0730_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arAdjNum_B1 = (EZDBStringItem)newItem("arAdjNum_B1");
		arAdjTpCd_B1 = (EZDBStringItem)newItem("arAdjTpCd_B1");
		arAdjTpDescTxt_B1 = (EZDBStringItem)newItem("arAdjTpDescTxt_B1");
		glDt_B1 = (EZDBDateItem)newItem("glDt_B1");
		dealApplyAmt_B1 = (EZDBBigDecimalItem)newItem("dealApplyAmt_B1");
		adjCmntTxt_B1 = (EZDBStringItem)newItem("adjCmntTxt_B1");
		arWrtOffNoteTxt_B1 = (EZDBStringItem)newItem("arWrtOffNoteTxt_B1");
		xxCmntTxt_B1 = (EZDBStringItem)newItem("xxCmntTxt_B1");
		ezInUserID_B1 = (EZDBStringItem)newItem("ezInUserID_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0730_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0730_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arAdjNum_B1", "arAdjNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"arAdjTpCd_B1", "arAdjTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpDescTxt_B1", "arAdjTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"glDt_B1", "glDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"dealApplyAmt_B1", "dealApplyAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"adjCmntTxt_B1", "adjCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "65", null},
	{"arWrtOffNoteTxt_B1", "arWrtOffNoteTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCmntTxt_B1", "xxCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezInUserID_B1", "ezInUserID_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_ADJ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjNum_B1
        {"AR_ADJ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_B1
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_B1
        {"GL_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//glDt_B1
        {"DEAL_APPLY_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyAmt_B1
        {"ADJ_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCmntTxt_B1
        {"AR_WRT_OFF_NOTE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteTxt_B1
        {"XX_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_B1
        {"_EZInUserID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInUserID_B1
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

