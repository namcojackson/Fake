//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171213111858000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0030_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZIL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0030_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** INTFC_ID_A*/
	public final EZDBStringItem              intfcId_A;

    /** BIZ_API_ID_A*/
	public final EZDBStringItem              bizApiId_A;

    /** INTFC_ID_DESC_TXT_A*/
	public final EZDBStringItem              intfcIdDescTxt_A;

    /** TRGT_BAT_JOB_NM_A*/
	public final EZDBStringItem              trgtBatJobNm_A;

    /** TRGT_BAT_JOB_DESC_TXT_A*/
	public final EZDBStringItem              trgtBatJobDescTxt_A;

    /** ACTV_FLG_A*/
	public final EZDBStringItem              actvFlg_A;

    /** FRCE_QUEUE_ENBL_FLG_A*/
	public final EZDBStringItem              frceQueueEnblFlg_A;


	/**
	 * ZZIL0030_ABMsg is constructor.
	 * The initialization when the instance of ZZIL0030_ABMsg is generated.
	 */
	public ZZIL0030_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0030_ABMsg is constructor.
	 * The initialization when the instance of ZZIL0030_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0030_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		intfcId_A = (EZDBStringItem)newItem("intfcId_A");
		bizApiId_A = (EZDBStringItem)newItem("bizApiId_A");
		intfcIdDescTxt_A = (EZDBStringItem)newItem("intfcIdDescTxt_A");
		trgtBatJobNm_A = (EZDBStringItem)newItem("trgtBatJobNm_A");
		trgtBatJobDescTxt_A = (EZDBStringItem)newItem("trgtBatJobDescTxt_A");
		actvFlg_A = (EZDBStringItem)newItem("actvFlg_A");
		frceQueueEnblFlg_A = (EZDBStringItem)newItem("frceQueueEnblFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0030_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0030_ABMsgArray();
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

        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"INTFC_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcId_A
        {"BIZ_API_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizApiId_A
        {"INTFC_ID_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcIdDescTxt_A
        {"TRGT_BAT_JOB_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trgtBatJobNm_A
        {"TRGT_BAT_JOB_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trgtBatJobDescTxt_A
        {"ACTV_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"FRCE_QUEUE_ENBL_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frceQueueEnblFlg_A
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

