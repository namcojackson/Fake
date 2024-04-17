//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160229171952000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010F02FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010F02 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010F02FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DT_TXT_I*/
	public final EZDFStringItem              xxDtTxt_I;

    /** UPD_FLD_TXT_I*/
	public final EZDFStringItem              updFldTxt_I;

    /** OLD_VAL_TXT_I*/
	public final EZDFStringItem              oldValTxt_I;

    /** NEW_VAL_TXT_I*/
	public final EZDFStringItem              newValTxt_I;

    /** UPD_USR_ID_I*/
	public final EZDFStringItem              updUsrId_I;

    /** XX_YES_NO_CD_I*/
	public final EZDFStringItem              xxYesNoCd_I;


	/**
	 * NSAL0010F02FMsg is constructor.
	 * The initialization when the instance of NSAL0010F02FMsg is generated.
	 */
	public NSAL0010F02FMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010F02FMsg is constructor.
	 * The initialization when the instance of NSAL0010F02FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010F02FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDtTxt_I = (EZDFStringItem)newItem("xxDtTxt_I");
		updFldTxt_I = (EZDFStringItem)newItem("updFldTxt_I");
		oldValTxt_I = (EZDFStringItem)newItem("oldValTxt_I");
		newValTxt_I = (EZDFStringItem)newItem("newValTxt_I");
		updUsrId_I = (EZDFStringItem)newItem("updUsrId_I");
		xxYesNoCd_I = (EZDFStringItem)newItem("xxYesNoCd_I");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010F02FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010F02FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDtTxt_I", "xxDtTxt_I", "I", null, TYPE_HANKAKUEISU, "10", null},
	{"updFldTxt_I", "updFldTxt_I", "I", null, TYPE_HANKAKUEISU, "50", null},
	{"oldValTxt_I", "oldValTxt_I", "I", null, TYPE_HANKAKUEISU, "300", null},
	{"newValTxt_I", "newValTxt_I", "I", null, TYPE_HANKAKUEISU, "300", null},
	{"updUsrId_I", "updUsrId_I", "I", null, TYPE_HANKAKUEISU, "16", null},
	{"xxYesNoCd_I", "xxYesNoCd_I", "I", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_I
        {"UPD_FLD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updFldTxt_I
        {"OLD_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldValTxt_I
        {"NEW_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newValTxt_I
        {"UPD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_I
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_I
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

