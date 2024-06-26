//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20140623105746000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0060_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZZL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0060_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BAT_PROC_JOB_ID_A*/
	public final EZDBStringItem              batProcJobId_A;

    /** BAT_TBL_NM_A*/
	public final EZDBStringItem              batTblNm_A;

    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;


	/**
	 * ZZZL0060_ABMsg is constructor.
	 * The initialization when the instance of ZZZL0060_ABMsg is generated.
	 */
	public ZZZL0060_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0060_ABMsg is constructor.
	 * The initialization when the instance of ZZZL0060_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0060_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		batProcJobId_A = (EZDBStringItem)newItem("batProcJobId_A");
		batTblNm_A = (EZDBStringItem)newItem("batTblNm_A");
		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0060_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0060_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"batProcJobId_A", "batProcJobId_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"batTblNm_A", "batTblNm_A", "A", null, TYPE_HANKAKUEISU, "64", null},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BAT_PROC_JOB_ID", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId_A
        {"BAT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batTblNm_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

