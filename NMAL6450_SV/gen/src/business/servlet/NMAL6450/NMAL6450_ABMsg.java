//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100715161101000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6450_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6450;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6450 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6450_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_A1*/
	public final EZDBStringItem              glblCmpyCd_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** PMT_TERM_CD_A1*/
	public final EZDBStringItem              pmtTermCd_A1;

    /** PMT_TERM_NM_A1*/
	public final EZDBStringItem              pmtTermNm_A1;

    /** PMT_TERM_SORT_NUM_A1*/
	public final EZDBBigDecimalItem              pmtTermSortNum_A1;

    /** PMT_TERM_DESC_TXT_A1*/
	public final EZDBStringItem              pmtTermDescTxt_A1;

    /** PMT_TERM_AOT_A1*/
	public final EZDBBigDecimalItem              pmtTermAot_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL6450_ABMsg is constructor.
	 * The initialization when the instance of NMAL6450_ABMsg is generated.
	 */
	public NMAL6450_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6450_ABMsg is constructor.
	 * The initialization when the instance of NMAL6450_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6450_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_A1 = (EZDBStringItem)newItem("glblCmpyCd_A1");
		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		pmtTermCd_A1 = (EZDBStringItem)newItem("pmtTermCd_A1");
		pmtTermNm_A1 = (EZDBStringItem)newItem("pmtTermNm_A1");
		pmtTermSortNum_A1 = (EZDBBigDecimalItem)newItem("pmtTermSortNum_A1");
		pmtTermDescTxt_A1 = (EZDBStringItem)newItem("pmtTermDescTxt_A1");
		pmtTermAot_A1 = (EZDBBigDecimalItem)newItem("pmtTermAot_A1");
		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6450_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6450_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_A1", "glblCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"pmtTermCd_A1", "pmtTermCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"pmtTermNm_A1", "pmtTermNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"pmtTermSortNum_A1", "pmtTermSortNum_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"pmtTermDescTxt_A1", "pmtTermDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"pmtTermAot_A1", "pmtTermAot_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"PMT_TERM_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCd_A1
        {"PMT_TERM_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermNm_A1
        {"PMT_TERM_SORT_NUM", YES,  "0","999","1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermSortNum_A1
        {"PMT_TERM_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermDescTxt_A1
        {"PMT_TERM_AOT", YES,  "0","999","1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermAot_A1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

