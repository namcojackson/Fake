//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130814144815000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6670_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6670;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6670 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6670_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_A1*/
	public final EZDCStringItem              glblCmpyCd_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** ISTL_PMT_TERM_CD_A1*/
	public final EZDCStringItem              istlPmtTermCd_A1;

    /** ISTL_PMT_TERM_DTL_NUM_A1*/
	public final EZDCStringItem              istlPmtTermDtlNum_A1;

    /** ISTL_PMT_TERM_AOT_A1*/
	public final EZDCBigDecimalItem              istlPmtTermAot_A1;

    /** ISTL_PMT_TERM_PCT_A1*/
	public final EZDCBigDecimalItem              istlPmtTermPct_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDCStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDCStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL6670_ACMsg is constructor.
	 * The initialization when the instance of NMAL6670_ACMsg is generated.
	 */
	public NMAL6670_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6670_ACMsg is constructor.
	 * The initialization when the instance of NMAL6670_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6670_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_A1 = (EZDCStringItem)newItem("glblCmpyCd_A1");
		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		istlPmtTermCd_A1 = (EZDCStringItem)newItem("istlPmtTermCd_A1");
		istlPmtTermDtlNum_A1 = (EZDCStringItem)newItem("istlPmtTermDtlNum_A1");
		istlPmtTermAot_A1 = (EZDCBigDecimalItem)newItem("istlPmtTermAot_A1");
		istlPmtTermPct_A1 = (EZDCBigDecimalItem)newItem("istlPmtTermPct_A1");
		ezUpTime_A1 = (EZDCStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDCStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6670_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6670_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_A1", "glblCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"istlPmtTermCd_A1", "istlPmtTermCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"istlPmtTermDtlNum_A1", "istlPmtTermDtlNum_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"istlPmtTermAot_A1", "istlPmtTermAot_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"istlPmtTermPct_A1", "istlPmtTermPct_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"ISTL_PMT_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlPmtTermCd_A1
        {"ISTL_PMT_TERM_DTL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlPmtTermDtlNum_A1
        {"ISTL_PMT_TERM_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlPmtTermAot_A1
        {"ISTL_PMT_TERM_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlPmtTermPct_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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
