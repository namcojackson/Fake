//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20110104134113000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0040CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0040CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** JVM_NM_C*/
	public final EZDCStringItemArray              jvmNm_C;

    /** JVM_NM_D*/
	public final EZDCStringItemArray              jvmNm_D;

    /** JVM_NM_S*/
	public final EZDCStringItem              jvmNm_S;

    /** XX_FROM_DT*/
	public final EZDCDateItem              xxFromDt;

    /** XX_HRS_FC*/
	public final EZDCStringItemArray              xxHrs_FC;

    /** XX_HRS_FD*/
	public final EZDCStringItemArray              xxHrs_FD;

    /** XX_HRS_FS*/
	public final EZDCStringItem              xxHrs_FS;

    /** XX_MN_FC*/
	public final EZDCStringItemArray              xxMn_FC;

    /** XX_MN_FD*/
	public final EZDCStringItemArray              xxMn_FD;

    /** XX_MN_FS*/
	public final EZDCStringItem              xxMn_FS;

    /** BIZ_START_TS*/
	public final EZDCStringItem              bizStartTs;

    /** XX_TO_DT*/
	public final EZDCDateItem              xxToDt;

    /** XX_HRS_TC*/
	public final EZDCStringItemArray              xxHrs_TC;

    /** XX_HRS_TD*/
	public final EZDCStringItemArray              xxHrs_TD;

    /** XX_HRS_TS*/
	public final EZDCStringItem              xxHrs_TS;

    /** XX_MN_TC*/
	public final EZDCStringItemArray              xxMn_TC;

    /** XX_MN_TD*/
	public final EZDCStringItemArray              xxMn_TD;

    /** XX_MN_TS*/
	public final EZDCStringItem              xxMn_TS;

    /** BIZ_END_TS*/
	public final EZDCStringItem              bizEndTs;

    /** XX_RADIO_BTN*/
	public final EZDCBigDecimalItem              xxRadioBtn;

    /** XX_TOT_CNT*/
	public final EZDCBigDecimalItem              xxTotCnt;

    /** XX_CHK_BOX*/
	public final EZDCStringItem              xxChkBox;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.ZZZL0040.ZZZL0040_ACMsgArray              A;


	/**
	 * ZZZL0040CMsg is constructor.
	 * The initialization when the instance of ZZZL0040CMsg is generated.
	 */
	public ZZZL0040CMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0040CMsg is constructor.
	 * The initialization when the instance of ZZZL0040CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0040CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		jvmNm_C = (EZDCStringItemArray)newItemArray("jvmNm_C");
		jvmNm_D = (EZDCStringItemArray)newItemArray("jvmNm_D");
		jvmNm_S = (EZDCStringItem)newItem("jvmNm_S");
		xxFromDt = (EZDCDateItem)newItem("xxFromDt");
		xxHrs_FC = (EZDCStringItemArray)newItemArray("xxHrs_FC");
		xxHrs_FD = (EZDCStringItemArray)newItemArray("xxHrs_FD");
		xxHrs_FS = (EZDCStringItem)newItem("xxHrs_FS");
		xxMn_FC = (EZDCStringItemArray)newItemArray("xxMn_FC");
		xxMn_FD = (EZDCStringItemArray)newItemArray("xxMn_FD");
		xxMn_FS = (EZDCStringItem)newItem("xxMn_FS");
		bizStartTs = (EZDCStringItem)newItem("bizStartTs");
		xxToDt = (EZDCDateItem)newItem("xxToDt");
		xxHrs_TC = (EZDCStringItemArray)newItemArray("xxHrs_TC");
		xxHrs_TD = (EZDCStringItemArray)newItemArray("xxHrs_TD");
		xxHrs_TS = (EZDCStringItem)newItem("xxHrs_TS");
		xxMn_TC = (EZDCStringItemArray)newItemArray("xxMn_TC");
		xxMn_TD = (EZDCStringItemArray)newItemArray("xxMn_TD");
		xxMn_TS = (EZDCStringItem)newItem("xxMn_TS");
		bizEndTs = (EZDCStringItem)newItem("bizEndTs");
		xxRadioBtn = (EZDCBigDecimalItem)newItem("xxRadioBtn");
		xxTotCnt = (EZDCBigDecimalItem)newItem("xxTotCnt");
		xxChkBox = (EZDCStringItem)newItem("xxChkBox");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.ZZZL0040.ZZZL0040_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0040CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0040CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"jvmNm_C", "jvmNm_C", "C", "20", TYPE_HANKAKUEISU, "20", null},
	{"jvmNm_D", "jvmNm_D", "D", "20", TYPE_HANKAKUEISU, "20", null},
	{"jvmNm_S", "jvmNm_S", "S", null, TYPE_HANKAKUEISU, "20", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_FC", "xxHrs_FC", "FC", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrs_FD", "xxHrs_FD", "FD", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrs_FS", "xxHrs_FS", "FS", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxMn_FC", "xxMn_FC", "FC", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxMn_FD", "xxMn_FD", "FD", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxMn_FS", "xxMn_FS", "FS", null, TYPE_HANKAKUSUJI, "2", null},
	{"bizStartTs", "bizStartTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxToDt", "xxToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxHrs_TC", "xxHrs_TC", "TC", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrs_TD", "xxHrs_TD", "TD", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxHrs_TS", "xxHrs_TS", "TS", null, TYPE_HANKAKUSUJI, "2", null},
	{"xxMn_TC", "xxMn_TC", "TC", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxMn_TD", "xxMn_TD", "TD", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxMn_TS", "xxMn_TS", "TS", null, TYPE_HANKAKUSUJI, "2", null},
	{"bizEndTs", "bizEndTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxTotCnt", "xxTotCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.blap.ZZZL0040.ZZZL0040_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"JVM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_C
        {"JVM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_D
        {"JVM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_S
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_FC
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_FD
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_FS
        {"XX_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMn_FC
        {"XX_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMn_FD
        {"XX_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMn_FS
        {"BIZ_START_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizStartTs
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_TC
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_TD
        {"XX_HRS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHrs_TS
        {"XX_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMn_TC
        {"XX_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMn_TD
        {"XX_MN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMn_TS
        {"BIZ_END_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizEndTs
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
        {"XX_TOT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
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

