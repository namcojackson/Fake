//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220324145020000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0150_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0150 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0150_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C*/
	public final EZDBStringItem              xxChkBox_C;

    /** MTR_LB_CD_C*/
	public final EZDBStringItem              mtrLbCd_C;

    /** MTR_LB_DESC_TXT_C*/
	public final EZDBStringItem              mtrLbDescTxt_C;


	/**
	 * NSAL0150_CBMsg is constructor.
	 * The initialization when the instance of NSAL0150_CBMsg is generated.
	 */
	public NSAL0150_CBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0150_CBMsg is constructor.
	 * The initialization when the instance of NSAL0150_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0150_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C = (EZDBStringItem)newItem("xxChkBox_C");
		mtrLbCd_C = (EZDBStringItem)newItem("mtrLbCd_C");
		mtrLbDescTxt_C = (EZDBStringItem)newItem("mtrLbDescTxt_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0150_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0150_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C", "xxChkBox_C", "C", null, TYPE_HANKAKUEIJI, "1", null},
	{"mtrLbCd_C", "mtrLbCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_C", "mtrLbDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C
        {"MTR_LB_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_C
        {"MTR_LB_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_C
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

