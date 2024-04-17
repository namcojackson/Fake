//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160916131748000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0430_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0430;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0430 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0430_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_MTR_READ_TP_GRP_CD_B*/
	public final EZDBStringItem              dsMtrReadTpGrpCd_B;

    /** XX_GENL_FLD_AREA_TXT_B*/
	public final EZDBStringItem              xxGenlFldAreaTxt_B;


	/**
	 * NSAL0430_BBMsg is constructor.
	 * The initialization when the instance of NSAL0430_BBMsg is generated.
	 */
	public NSAL0430_BBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0430_BBMsg is constructor.
	 * The initialization when the instance of NSAL0430_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0430_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsMtrReadTpGrpCd_B = (EZDBStringItem)newItem("dsMtrReadTpGrpCd_B");
		xxGenlFldAreaTxt_B = (EZDBStringItem)newItem("xxGenlFldAreaTxt_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0430_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0430_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsMtrReadTpGrpCd_B", "dsMtrReadTpGrpCd_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	{"xxGenlFldAreaTxt_B", "xxGenlFldAreaTxt_B", "B", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_MTR_READ_TP_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpGrpCd_B
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_B
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
