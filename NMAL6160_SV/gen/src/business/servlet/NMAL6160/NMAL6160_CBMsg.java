//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160616135507000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6160_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6160 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6160_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_ITEM_10_TXT_C*/
	public final EZDBStringItem              xxScrItem10Txt_C;

    /** LOC_NUM_C*/
	public final EZDBStringItem              locNum_C;

    /** ORG_CD_C*/
	public final EZDBStringItem              orgCd_C;

    /** XX_DS_MSG_ENTRY_TXT_C*/
	public final EZDBStringItem              xxDsMsgEntryTxt_C;


	/**
	 * NMAL6160_CBMsg is constructor.
	 * The initialization when the instance of NMAL6160_CBMsg is generated.
	 */
	public NMAL6160_CBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6160_CBMsg is constructor.
	 * The initialization when the instance of NMAL6160_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6160_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrItem10Txt_C = (EZDBStringItem)newItem("xxScrItem10Txt_C");
		locNum_C = (EZDBStringItem)newItem("locNum_C");
		orgCd_C = (EZDBStringItem)newItem("orgCd_C");
		xxDsMsgEntryTxt_C = (EZDBStringItem)newItem("xxDsMsgEntryTxt_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6160_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6160_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrItem10Txt_C", "xxScrItem10Txt_C", "C", null, TYPE_HANKAKUEISU, "10", null},
	{"locNum_C", "locNum_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"orgCd_C", "orgCd_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"xxDsMsgEntryTxt_C", "xxDsMsgEntryTxt_C", "C", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_ITEM_10_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem10Txt_C
        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_C
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_C
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt_C
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
