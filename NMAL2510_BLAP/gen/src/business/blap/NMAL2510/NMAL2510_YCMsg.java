//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190213100019000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_YCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2510 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_YCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD_Y1*/
	public final EZDCStringItem              orgCd_Y1;

    /** ORG_STRU_TP_CD_Y1*/
	public final EZDCStringItem              orgStruTpCd_Y1;

    /** PSN_CD_Y1*/
	public final EZDCStringItem              psnCd_Y1;

    /** ORG_FUNC_ROLE_TP_CD_Y1*/
	public final EZDCStringItem              orgFuncRoleTpCd_Y1;

    /** EFF_FROM_DT_Y1*/
	public final EZDCDateItem              effFromDt_Y1;

    /** _EZUpdateDateTime_Y1*/
	public final EZDCStringItem              ezUpTime_Y1;

    /** _EZUpTimeZone_Y1*/
	public final EZDCStringItem              ezUpTimeZone_Y1;


	/**
	 * NMAL2510_YCMsg is constructor.
	 * The initialization when the instance of NMAL2510_YCMsg is generated.
	 */
	public NMAL2510_YCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_YCMsg is constructor.
	 * The initialization when the instance of NMAL2510_YCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_YCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd_Y1 = (EZDCStringItem)newItem("orgCd_Y1");
		orgStruTpCd_Y1 = (EZDCStringItem)newItem("orgStruTpCd_Y1");
		psnCd_Y1 = (EZDCStringItem)newItem("psnCd_Y1");
		orgFuncRoleTpCd_Y1 = (EZDCStringItem)newItem("orgFuncRoleTpCd_Y1");
		effFromDt_Y1 = (EZDCDateItem)newItem("effFromDt_Y1");
		ezUpTime_Y1 = (EZDCStringItem)newItem("ezUpTime_Y1");
		ezUpTimeZone_Y1 = (EZDCStringItem)newItem("ezUpTimeZone_Y1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_YCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_YCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd_Y1", "orgCd_Y1", "Y1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_Y1", "orgStruTpCd_Y1", "Y1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_Y1", "psnCd_Y1", "Y1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_Y1", "orgFuncRoleTpCd_Y1", "Y1", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_Y1", "effFromDt_Y1", "Y1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_Y1", "ezUpTime_Y1", "Y1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_Y1", "ezUpTimeZone_Y1", "Y1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_Y1
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_Y1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_Y1
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_Y1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_Y1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_Y1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_Y1
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

