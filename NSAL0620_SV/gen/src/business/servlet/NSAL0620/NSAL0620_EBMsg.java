//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240208102635000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0620_EBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0620_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_E*/
	public final EZDBStringItem              xxChkBox_E;

    /** MTR_LB_CD_E*/
	public final EZDBStringItem              mtrLbCd_E;

    /** MTR_LB_NM_E*/
	public final EZDBStringItem              mtrLbNm_E;


	/**
	 * NSAL0620_EBMsg is constructor.
	 * The initialization when the instance of NSAL0620_EBMsg is generated.
	 */
	public NSAL0620_EBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0620_EBMsg is constructor.
	 * The initialization when the instance of NSAL0620_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0620_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_E = (EZDBStringItem)newItem("xxChkBox_E");
		mtrLbCd_E = (EZDBStringItem)newItem("mtrLbCd_E");
		mtrLbNm_E = (EZDBStringItem)newItem("mtrLbNm_E");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0620_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0620_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_E", "xxChkBox_E", "E", null, TYPE_HANKAKUEIJI, "1", null},
	{"mtrLbCd_E", "mtrLbCd_E", "E", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_E", "mtrLbNm_E", "E", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E
        {"MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_E
        {"MTR_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_E
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

