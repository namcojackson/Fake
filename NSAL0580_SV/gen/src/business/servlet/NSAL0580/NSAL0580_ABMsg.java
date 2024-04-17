//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151110125421000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0580_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0580;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0580 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0580_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_A*/
	public final EZDBBigDecimalItem              dsContrPk_A;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** DS_CONTR_DTL_PK*/
	public final EZDBBigDecimalItem              dsContrDtlPk;

    /** _EZUpdateDateTime_D*/
	public final EZDBStringItem              ezUpTime_D;

    /** _EZUpTimeZone_D*/
	public final EZDBStringItem              ezUpTimeZone_D;


	/**
	 * NSAL0580_ABMsg is constructor.
	 * The initialization when the instance of NSAL0580_ABMsg is generated.
	 */
	public NSAL0580_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0580_ABMsg is constructor.
	 * The initialization when the instance of NSAL0580_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0580_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_A = (EZDBBigDecimalItem)newItem("dsContrPk_A");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		dsContrDtlPk = (EZDBBigDecimalItem)newItem("dsContrDtlPk");
		ezUpTime_D = (EZDBStringItem)newItem("ezUpTime_D");
		ezUpTimeZone_D = (EZDBStringItem)newItem("ezUpTimeZone_D");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0580_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0580_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_A", "dsContrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_D", "ezUpTime_D", "D", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D", "ezUpTimeZone_D", "D", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D
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

