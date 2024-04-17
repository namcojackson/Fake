//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20150708192741000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8820_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8820;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8820 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8820_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ASG_CHK_B*/
	public final EZDSStringItem              asgChk_B;

    /** ASG_CD_B*/
	public final EZDSStringItem              asgCd_B;

    /** ASG_NM_B*/
	public final EZDSStringItem              asgNm_B;

    /** ASG_TP_B*/
	public final EZDSStringItem              asgTp_B;

    /** ASG_CMNT_B*/
	public final EZDSStringItem              asgCmnt_B;


	/**
	 * NYEL8820_BSMsg is constructor.
	 * The initialization when the instance of NYEL8820_BSMsg is generated.
	 */
	public NYEL8820_BSMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8820_BSMsg is constructor.
	 * The initialization when the instance of NYEL8820_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8820_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		asgChk_B = (EZDSStringItem)newItem("asgChk_B");
		asgCd_B = (EZDSStringItem)newItem("asgCd_B");
		asgNm_B = (EZDSStringItem)newItem("asgNm_B");
		asgTp_B = (EZDSStringItem)newItem("asgTp_B");
		asgCmnt_B = (EZDSStringItem)newItem("asgCmnt_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8820_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8820_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"asgChk_B", "asgChk_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"asgCd_B", "asgCd_B", "B", null, TYPE_HANKAKUEISU, "32", null},
	{"asgNm_B", "asgNm_B", "B", null, TYPE_HANKAKUEISU, "255", null},
	{"asgTp_B", "asgTp_B", "B", null, TYPE_HANKAKUEISU, "32", null},
	{"asgCmnt_B", "asgCmnt_B", "B", null, TYPE_HANKAKUEISU, "1024", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ASG_CHK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgChk_B
        {"ASG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgCd_B
        {"ASG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgNm_B
        {"ASG_TP",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgTp_B
        {"ASG_CMNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgCmnt_B
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

