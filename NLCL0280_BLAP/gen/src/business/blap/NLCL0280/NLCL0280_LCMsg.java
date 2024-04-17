//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230406162513000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0280_LCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0280;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0280 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0280_LCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** LOC_STS_CD_LS*/
	public final EZDCStringItem              locStsCd_LS;

    /** XX_SCR_ITEM_61_TXT_LS*/
	public final EZDCStringItem              xxScrItem61Txt_LS;

    /** XX_CHK_BOX_LS*/
	public final EZDCStringItem              xxChkBox_LS;

    /** XX_CHK_BOX_LB*/
	public final EZDCStringItem              xxChkBox_LB;


	/**
	 * NLCL0280_LCMsg is constructor.
	 * The initialization when the instance of NLCL0280_LCMsg is generated.
	 */
	public NLCL0280_LCMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0280_LCMsg is constructor.
	 * The initialization when the instance of NLCL0280_LCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0280_LCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		locStsCd_LS = (EZDCStringItem)newItem("locStsCd_LS");
		xxScrItem61Txt_LS = (EZDCStringItem)newItem("xxScrItem61Txt_LS");
		xxChkBox_LS = (EZDCStringItem)newItem("xxChkBox_LS");
		xxChkBox_LB = (EZDCStringItem)newItem("xxChkBox_LB");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0280_LCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0280_LCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"locStsCd_LS", "locStsCd_LS", "LS", null, TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem61Txt_LS", "xxScrItem61Txt_LS", "LS", null, TYPE_HANKAKUEISU, "61", null},
	{"xxChkBox_LS", "xxChkBox_LS", "LS", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_LB", "xxChkBox_LB", "LB", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"LOC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locStsCd_LS
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_LS
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_LS
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_LB
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

