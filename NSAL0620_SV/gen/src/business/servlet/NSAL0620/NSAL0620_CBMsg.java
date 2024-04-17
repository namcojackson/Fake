//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240208102635000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0620_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0620;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0620 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0620_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C*/
	public final EZDBStringItem              xxChkBox_C;

    /** SVC_CONTR_BR_CD_C*/
	public final EZDBStringItem              svcContrBrCd_C;

    /** SVC_CONTR_BR_DESC_TXT_C*/
	public final EZDBStringItem              svcContrBrDescTxt_C;


	/**
	 * NSAL0620_CBMsg is constructor.
	 * The initialization when the instance of NSAL0620_CBMsg is generated.
	 */
	public NSAL0620_CBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0620_CBMsg is constructor.
	 * The initialization when the instance of NSAL0620_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0620_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C = (EZDBStringItem)newItem("xxChkBox_C");
		svcContrBrCd_C = (EZDBStringItem)newItem("svcContrBrCd_C");
		svcContrBrDescTxt_C = (EZDBStringItem)newItem("svcContrBrDescTxt_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0620_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0620_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C", "xxChkBox_C", "C", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcContrBrCd_C", "svcContrBrCd_C", "C", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_C", "svcContrBrDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C
        {"SVC_CONTR_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_C
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_C
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
