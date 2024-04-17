//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118170919000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0730_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0730 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0730_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_B*/
	public final EZDBStringItem              glblCmpyCd_B;

    /** DS_CONTR_CR_CARD_PO_PK_B*/
	public final EZDBBigDecimalItem              dsContrCrCardPoPk_B;

    /** _EZUpdateDateTime_B*/
	public final EZDBStringItem              ezUpTime_B;

    /** _EZUpTimeZone_B*/
	public final EZDBStringItem              ezUpTimeZone_B;


	/**
	 * NSAL0730_BBMsg is constructor.
	 * The initialization when the instance of NSAL0730_BBMsg is generated.
	 */
	public NSAL0730_BBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0730_BBMsg is constructor.
	 * The initialization when the instance of NSAL0730_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0730_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_B = (EZDBStringItem)newItem("glblCmpyCd_B");
		dsContrCrCardPoPk_B = (EZDBBigDecimalItem)newItem("dsContrCrCardPoPk_B");
		ezUpTime_B = (EZDBStringItem)newItem("ezUpTime_B");
		ezUpTimeZone_B = (EZDBStringItem)newItem("ezUpTimeZone_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0730_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0730_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_B", "glblCmpyCd_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrCrCardPoPk_B", "dsContrCrCardPoPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_B
        {"DS_CONTR_CR_CARD_PO_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCrCardPoPk_B
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
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
