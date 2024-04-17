//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230427160329000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1700 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_LIST_TP_CD_B*/
	public final EZDBStringItem              prcListTpCd_B;

    /** PRC_LIST_TP_DESC_TXT_B*/
	public final EZDBStringItem              prcListTpDescTxt_B;

    /** XX_CHK_BOX_B*/
	public final EZDBStringItem              xxChkBox_B;


	/**
	 * NWAL1700_BBMsg is constructor.
	 * The initialization when the instance of NWAL1700_BBMsg is generated.
	 */
	public NWAL1700_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1700_BBMsg is constructor.
	 * The initialization when the instance of NWAL1700_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1700_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcListTpCd_B = (EZDBStringItem)newItem("prcListTpCd_B");
		prcListTpDescTxt_B = (EZDBStringItem)newItem("prcListTpDescTxt_B");
		xxChkBox_B = (EZDBStringItem)newItem("xxChkBox_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1700_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1700_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcListTpCd_B", "prcListTpCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_B", "prcListTpDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_B
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_B
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
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
