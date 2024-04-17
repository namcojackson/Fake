//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151222183346000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3120_RCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3120 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3120_RCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_RO*/
	public final EZDCStringItem              serNum_RO;

    /** MDSE_CD_RO*/
	public final EZDCStringItem              mdseCd_RO;

    /** CUR_LOC_NUM_RO*/
	public final EZDCStringItem              curLocNum_RO;

    /** LOC_NM_RO*/
	public final EZDCStringItem              locNm_RO;

    /** SVC_MACH_USG_STS_CD_RO*/
	public final EZDCStringItem              svcMachUsgStsCd_RO;

    /** SVC_MACH_MSTR_STS_CD_RO*/
	public final EZDCStringItem              svcMachMstrStsCd_RO;

    /** SVC_MACH_MSTR_PK_RO*/
	public final EZDCBigDecimalItem              svcMachMstrPk_RO;


	/**
	 * NLBL3120_RCMsg is constructor.
	 * The initialization when the instance of NLBL3120_RCMsg is generated.
	 */
	public NLBL3120_RCMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3120_RCMsg is constructor.
	 * The initialization when the instance of NLBL3120_RCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3120_RCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_RO = (EZDCStringItem)newItem("serNum_RO");
		mdseCd_RO = (EZDCStringItem)newItem("mdseCd_RO");
		curLocNum_RO = (EZDCStringItem)newItem("curLocNum_RO");
		locNm_RO = (EZDCStringItem)newItem("locNm_RO");
		svcMachUsgStsCd_RO = (EZDCStringItem)newItem("svcMachUsgStsCd_RO");
		svcMachMstrStsCd_RO = (EZDCStringItem)newItem("svcMachMstrStsCd_RO");
		svcMachMstrPk_RO = (EZDCBigDecimalItem)newItem("svcMachMstrPk_RO");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3120_RCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3120_RCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_RO", "serNum_RO", "RO", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_RO", "mdseCd_RO", "RO", null, TYPE_HANKAKUEISU, "16", null},
	{"curLocNum_RO", "curLocNum_RO", "RO", null, TYPE_HANKAKUEISU, "30", null},
	{"locNm_RO", "locNm_RO", "RO", null, TYPE_HANKAKUEISU, "60", null},
	{"svcMachUsgStsCd_RO", "svcMachUsgStsCd_RO", "RO", null, TYPE_HANKAKUEISU, "3", null},
	{"svcMachMstrStsCd_RO", "svcMachMstrStsCd_RO", "RO", null, TYPE_HANKAKUEISU, "5", null},
	{"svcMachMstrPk_RO", "svcMachMstrPk_RO", "RO", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_RO
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_RO
        {"CUR_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curLocNum_RO
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_RO
        {"SVC_MACH_USG_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachUsgStsCd_RO
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_RO
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_RO
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

