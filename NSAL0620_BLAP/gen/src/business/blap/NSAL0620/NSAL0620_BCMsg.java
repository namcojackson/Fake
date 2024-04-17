//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240208110828000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0620_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0620;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0620 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0620_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDCStringItem              xxChkBox_B;

    /** SVC_RG_NM_B*/
	public final EZDCStringItem              svcRgNm_B;

    /** SVC_RG_DESC_TXT_B*/
	public final EZDCStringItem              svcRgDescTxt_B;


	/**
	 * NSAL0620_BCMsg is constructor.
	 * The initialization when the instance of NSAL0620_BCMsg is generated.
	 */
	public NSAL0620_BCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0620_BCMsg is constructor.
	 * The initialization when the instance of NSAL0620_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0620_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDCStringItem)newItem("xxChkBox_B");
		svcRgNm_B = (EZDCStringItem)newItem("svcRgNm_B");
		svcRgDescTxt_B = (EZDCStringItem)newItem("svcRgDescTxt_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0620_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0620_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcRgNm_B", "svcRgNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"svcRgDescTxt_B", "svcRgDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"SVC_RG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgNm_B
        {"SVC_RG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgDescTxt_B
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

