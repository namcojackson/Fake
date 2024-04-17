//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531163816000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1040_SBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL1040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL1040 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL1040_SBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** STK_STS_CD_SS*/
	public final EZDBStringItem              stkStsCd_SS;

    /** XX_SCR_ITEM_61_TXT_SS*/
	public final EZDBStringItem              xxScrItem61Txt_SS;

    /** XX_CHK_BOX_SS*/
	public final EZDBStringItem              xxChkBox_SS;

    /** STK_STS_CD_SB*/
	public final EZDBStringItem              stkStsCd_SB;

    /** XX_SCR_ITEM_61_TXT_SB*/
	public final EZDBStringItem              xxScrItem61Txt_SB;

    /** XX_CHK_BOX_SB*/
	public final EZDBStringItem              xxChkBox_SB;


	/**
	 * NLCL1040_SBMsg is constructor.
	 * The initialization when the instance of NLCL1040_SBMsg is generated.
	 */
	public NLCL1040_SBMsg() {
		this(false, -1);
	}

	/**
	 * NLCL1040_SBMsg is constructor.
	 * The initialization when the instance of NLCL1040_SBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL1040_SBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		stkStsCd_SS = (EZDBStringItem)newItem("stkStsCd_SS");
		xxScrItem61Txt_SS = (EZDBStringItem)newItem("xxScrItem61Txt_SS");
		xxChkBox_SS = (EZDBStringItem)newItem("xxChkBox_SS");
		stkStsCd_SB = (EZDBStringItem)newItem("stkStsCd_SB");
		xxScrItem61Txt_SB = (EZDBStringItem)newItem("xxScrItem61Txt_SB");
		xxChkBox_SB = (EZDBStringItem)newItem("xxChkBox_SB");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL1040_SBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL1040_SBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"stkStsCd_SS", "stkStsCd_SS", "SS", null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrItem61Txt_SS", "xxScrItem61Txt_SS", "SS", null, TYPE_HANKAKUEISU, "61", null},
	{"xxChkBox_SS", "xxChkBox_SS", "SS", null, TYPE_HANKAKUEIJI, "1", null},
	{"stkStsCd_SB", "stkStsCd_SB", "SB", null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrItem61Txt_SB", "xxScrItem61Txt_SB", "SB", null, TYPE_HANKAKUEISU, "61", null},
	{"xxChkBox_SB", "xxChkBox_SB", "SB", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_SS
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_SS
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SS
        {"STK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_SB
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_SB
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SB
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
