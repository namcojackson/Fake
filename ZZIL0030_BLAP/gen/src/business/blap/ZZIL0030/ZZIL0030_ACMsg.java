//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171218171624000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0030_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0030 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0030_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;

    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** INTFC_ID_A*/
	public final EZDCStringItem              intfcId_A;

    /** BIZ_API_ID_A*/
	public final EZDCStringItem              bizApiId_A;

    /** INTFC_ID_DESC_TXT_A*/
	public final EZDCStringItem              intfcIdDescTxt_A;

    /** TRGT_BAT_JOB_NM_A*/
	public final EZDCStringItem              trgtBatJobNm_A;

    /** TRGT_BAT_JOB_DESC_TXT_A*/
	public final EZDCStringItem              trgtBatJobDescTxt_A;

    /** ACTV_FLG_A*/
	public final EZDCStringItem              actvFlg_A;

    /** FRCE_QUEUE_ENBL_FLG_A*/
	public final EZDCStringItem              frceQueueEnblFlg_A;


	/**
	 * ZZIL0030_ACMsg is constructor.
	 * The initialization when the instance of ZZIL0030_ACMsg is generated.
	 */
	public ZZIL0030_ACMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0030_ACMsg is constructor.
	 * The initialization when the instance of ZZIL0030_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0030_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		intfcId_A = (EZDCStringItem)newItem("intfcId_A");
		bizApiId_A = (EZDCStringItem)newItem("bizApiId_A");
		intfcIdDescTxt_A = (EZDCStringItem)newItem("intfcIdDescTxt_A");
		trgtBatJobNm_A = (EZDCStringItem)newItem("trgtBatJobNm_A");
		trgtBatJobDescTxt_A = (EZDCStringItem)newItem("trgtBatJobDescTxt_A");
		actvFlg_A = (EZDCStringItem)newItem("actvFlg_A");
		frceQueueEnblFlg_A = (EZDCStringItem)newItem("frceQueueEnblFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0030_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0030_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"intfcId_A", "intfcId_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"bizApiId_A", "bizApiId_A", "A", null, TYPE_HANKAKUEISU, "12", null},
	{"intfcIdDescTxt_A", "intfcIdDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"trgtBatJobNm_A", "trgtBatJobNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"trgtBatJobDescTxt_A", "trgtBatJobDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"frceQueueEnblFlg_A", "frceQueueEnblFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcId_A
        {"BIZ_API_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizApiId_A
        {"INTFC_ID_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcIdDescTxt_A
        {"TRGT_BAT_JOB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trgtBatJobNm_A
        {"TRGT_BAT_JOB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trgtBatJobDescTxt_A
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"FRCE_QUEUE_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frceQueueEnblFlg_A
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

