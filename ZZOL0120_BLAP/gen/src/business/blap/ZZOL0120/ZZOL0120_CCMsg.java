//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20091020095954000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0120_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0120 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0120_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C2*/
	public final EZDCStringItem              xxChkBox_C2;

    /** UP_TAB_CD_C2*/
	public final EZDCStringItem              upTabCd_C2;

    /** UP_TAB_NM_C2*/
	public final EZDCStringItem              upTabNm_C2;

    /** UP_TAB_SORT_NUM_C2*/
	public final EZDCBigDecimalItem              upTabSortNum_C2;

    /** BIZ_APP_NM_C2*/
	public final EZDCStringItem              bizAppNm_C2;

    /** BIZ_APP_ID_C2*/
	public final EZDCStringItem              bizAppId_C2;

    /** MY_PROC_USBLE_FLG_C2*/
	public final EZDCStringItem              myProcUsbleFlg_C2;

    /** UP_TAB_USBLE_FLG_C2*/
	public final EZDCStringItem              upTabUsbleFlg_C2;

    /** _EZUpdateDateTime_C2*/
	public final EZDCStringItem              ezUpTime_C2;

    /** _EZUpTimeZone_C2*/
	public final EZDCStringItem              ezUpTimeZone_C2;


	/**
	 * ZZOL0120_CCMsg is constructor.
	 * The initialization when the instance of ZZOL0120_CCMsg is generated.
	 */
	public ZZOL0120_CCMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0120_CCMsg is constructor.
	 * The initialization when the instance of ZZOL0120_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0120_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C2 = (EZDCStringItem)newItem("xxChkBox_C2");
		upTabCd_C2 = (EZDCStringItem)newItem("upTabCd_C2");
		upTabNm_C2 = (EZDCStringItem)newItem("upTabNm_C2");
		upTabSortNum_C2 = (EZDCBigDecimalItem)newItem("upTabSortNum_C2");
		bizAppNm_C2 = (EZDCStringItem)newItem("bizAppNm_C2");
		bizAppId_C2 = (EZDCStringItem)newItem("bizAppId_C2");
		myProcUsbleFlg_C2 = (EZDCStringItem)newItem("myProcUsbleFlg_C2");
		upTabUsbleFlg_C2 = (EZDCStringItem)newItem("upTabUsbleFlg_C2");
		ezUpTime_C2 = (EZDCStringItem)newItem("ezUpTime_C2");
		ezUpTimeZone_C2 = (EZDCStringItem)newItem("ezUpTimeZone_C2");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0120_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0120_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C2", "xxChkBox_C2", "C2", null, TYPE_HANKAKUEIJI, "1", null},
	{"upTabCd_C2", "upTabCd_C2", "C2", null, TYPE_HANKAKUEISU, "10", null},
	{"upTabNm_C2", "upTabNm_C2", "C2", null, TYPE_HANKAKUEISU, "12", null},
	{"upTabSortNum_C2", "upTabSortNum_C2", "C2", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bizAppNm_C2", "bizAppNm_C2", "C2", null, TYPE_HANKAKUEISU, "100", null},
	{"bizAppId_C2", "bizAppId_C2", "C2", null, TYPE_HANKAKUEISU, "8", null},
	{"myProcUsbleFlg_C2", "myProcUsbleFlg_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"upTabUsbleFlg_C2", "upTabUsbleFlg_C2", "C2", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_C2", "ezUpTime_C2", "C2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_C2", "ezUpTimeZone_C2", "C2", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C2
        {"UP_TAB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabCd_C2
        {"UP_TAB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabNm_C2
        {"UP_TAB_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabSortNum_C2
        {"BIZ_APP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppNm_C2
        {"BIZ_APP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId_C2
        {"MY_PROC_USBLE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//myProcUsbleFlg_C2
        {"UP_TAB_USBLE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabUsbleFlg_C2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C2
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
