//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160128112518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1160_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1160 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1160_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CRAT_DT_DA*/
	public final EZDBDateItem              cratDt_DA;

    /** PROC_DT_DA*/
	public final EZDBDateItem              procDt_DA;

    /** XX_SCR_ITEM_61_TXT_A*/
	public final EZDBStringItem              xxScrItem61Txt_A;

    /** ABUSE_ACT_DESC_TXT*/
	public final EZDBStringItem              abuseActDescTxt;

    /** ABUSE_ACT_CMNT_TXT*/
	public final EZDBStringItem              abuseActCmntTxt;


	/**
	 * NSAL1160_ABMsg is constructor.
	 * The initialization when the instance of NSAL1160_ABMsg is generated.
	 */
	public NSAL1160_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1160_ABMsg is constructor.
	 * The initialization when the instance of NSAL1160_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1160_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cratDt_DA = (EZDBDateItem)newItem("cratDt_DA");
		procDt_DA = (EZDBDateItem)newItem("procDt_DA");
		xxScrItem61Txt_A = (EZDBStringItem)newItem("xxScrItem61Txt_A");
		abuseActDescTxt = (EZDBStringItem)newItem("abuseActDescTxt");
		abuseActCmntTxt = (EZDBStringItem)newItem("abuseActCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1160_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1160_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cratDt_DA", "cratDt_DA", "DA", null, TYPE_NENTSUKIHI, "8", null},
	{"procDt_DA", "procDt_DA", "DA", null, TYPE_NENTSUKIHI, "8", null},
	{"xxScrItem61Txt_A", "xxScrItem61Txt_A", "A", null, TYPE_HANKAKUEISU, "61", null},
	{"abuseActDescTxt", "abuseActDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"abuseActCmntTxt", "abuseActCmntTxt", null, null, TYPE_HANKAKUEISU, "3000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cratDt_DA
        {"PROC_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//procDt_DA
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A
        {"ABUSE_ACT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseActDescTxt
        {"ABUSE_ACT_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abuseActCmntTxt
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
