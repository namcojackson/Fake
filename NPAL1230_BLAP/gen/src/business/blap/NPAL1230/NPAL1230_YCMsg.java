//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160414053008000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1230_YCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1230;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1230 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1230_YCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ASL_QLFY_RELN_PK_Y*/
	public final EZDCBigDecimalItem              aslQlfyRelnPk_Y;

    /** ASL_QLFY_TP_CD_Y*/
	public final EZDCStringItem              aslQlfyTpCd_Y;

    /** ASL_QLFY_REF_CD_Y*/
	public final EZDCStringItem              aslQlfyRefCd_Y;


	/**
	 * NPAL1230_YCMsg is constructor.
	 * The initialization when the instance of NPAL1230_YCMsg is generated.
	 */
	public NPAL1230_YCMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1230_YCMsg is constructor.
	 * The initialization when the instance of NPAL1230_YCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1230_YCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		aslQlfyRelnPk_Y = (EZDCBigDecimalItem)newItem("aslQlfyRelnPk_Y");
		aslQlfyTpCd_Y = (EZDCStringItem)newItem("aslQlfyTpCd_Y");
		aslQlfyRefCd_Y = (EZDCStringItem)newItem("aslQlfyRefCd_Y");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1230_YCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1230_YCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"aslQlfyRelnPk_Y", "aslQlfyRelnPk_Y", "Y", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"aslQlfyTpCd_Y", "aslQlfyTpCd_Y", "Y", null, TYPE_HANKAKUEISU, "2", null},
	{"aslQlfyRefCd_Y", "aslQlfyRefCd_Y", "Y", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ASL_QLFY_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslQlfyRelnPk_Y
        {"ASL_QLFY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslQlfyTpCd_Y
        {"ASL_QLFY_REF_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslQlfyRefCd_Y
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
