//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20101129140921000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0030_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0030_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_A*/
	public final EZDSStringItem              glblCmpyCd_A;

    /** OPS_USR_ID_A*/
	public final EZDSStringItem              opsUsrId_A;

    /** JVM_NM_A*/
	public final EZDSStringItem              jvmNm_A;

    /** BIZ_ID_A*/
	public final EZDSStringItem              bizId_A;

    /** SCR_APP_ID_A*/
	public final EZDSStringItem              scrAppId_A;

    /** OPS_TS_A*/
	public final EZDSStringItem              opsTs_A;

    /** BIZ_ELPS_TM_TXT_A*/
	public final EZDSStringItem              bizElpsTmTxt_A;

    /** GLBL_AREA_DATA_CNT_A*/
	public final EZDSBigDecimalItem              glblAreaDataCnt_A;


	/**
	 * ZZZL0030_ASMsg is constructor.
	 * The initialization when the instance of ZZZL0030_ASMsg is generated.
	 */
	public ZZZL0030_ASMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0030_ASMsg is constructor.
	 * The initialization when the instance of ZZZL0030_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0030_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_A = (EZDSStringItem)newItem("glblCmpyCd_A");
		opsUsrId_A = (EZDSStringItem)newItem("opsUsrId_A");
		jvmNm_A = (EZDSStringItem)newItem("jvmNm_A");
		bizId_A = (EZDSStringItem)newItem("bizId_A");
		scrAppId_A = (EZDSStringItem)newItem("scrAppId_A");
		opsTs_A = (EZDSStringItem)newItem("opsTs_A");
		bizElpsTmTxt_A = (EZDSStringItem)newItem("bizElpsTmTxt_A");
		glblAreaDataCnt_A = (EZDSBigDecimalItem)newItem("glblAreaDataCnt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0030_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0030_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_A", "glblCmpyCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"opsUsrId_A", "opsUsrId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"jvmNm_A", "jvmNm_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"bizId_A", "bizId_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"scrAppId_A", "scrAppId_A", "A", null, TYPE_HANKAKUEISU, "64", null},
	{"opsTs_A", "opsTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"bizElpsTmTxt_A", "bizElpsTmTxt_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"glblAreaDataCnt_A", "glblAreaDataCnt_A", "A", null, TYPE_SEISU_SYOSU, "16", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A
        {"OPS_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//opsUsrId_A
        {"JVM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_A
        {"BIZ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizId_A
        {"SCR_APP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrAppId_A
        {"OPS_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//opsTs_A
        {"BIZ_ELPS_TM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizElpsTmTxt_A
        {"GLBL_AREA_DATA_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblAreaDataCnt_A
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
