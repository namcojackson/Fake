//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140618174623000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0600_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0600 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0600_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_TR*/
	public final EZDCStringItem              glblCmpyCd_TR;

    /** ALMS_UNIVS_UNIQ_ID_TR*/
	public final EZDCStringItem              almsUnivsUniqId_TR;

    /** ALMS_SYS_NM_TR*/
	public final EZDCStringItem              almsSysNm_TR;

    /** ALMS_HOST_NM_TR*/
	public final EZDCStringItem              almsHostNm_TR;

    /** ALMS_USR_ID_TR*/
	public final EZDCStringItem              almsUsrId_TR;

    /** ALMS_MSG_ID_TR*/
	public final EZDCStringItem              almsMsgId_TR;

    /** ALMS_FWK_VRSN_TXT_TR*/
	public final EZDCStringItem              almsFwkVrsnTxt_TR;

    /** ALMS_APP_VRSN_TXT_TR*/
	public final EZDCStringItem              almsAppVrsnTxt_TR;

    /** ALMS_DB_VRSN_TXT_TR*/
	public final EZDCStringItem              almsDbVrsnTxt_TR;

    /** ALMS_REL_VRSN_TXT_TR*/
	public final EZDCStringItem              almsRelVrsnTxt_TR;

    /** ALMS_PGM_ID_TR*/
	public final EZDCStringItem              almsPgmId_TR;

    /** ALMS_JOB_ID_TR*/
	public final EZDCStringItem              almsJobId_TR;

    /** XX_ALMS_ONL_BAT_FLG_TXT_TR*/
	public final EZDCStringItem              xxAlmsOnlBatFlgTxt_TR;

    /** ALMS_JVM_NM_TR*/
	public final EZDCStringItem              almsJvmNm_TR;

    /** ALMS_BAT_PROC_ID_TR*/
	public final EZDCBigDecimalItem              almsBatProcId_TR;

    /** ALMS_SEVTY_LVL_TXT_TR*/
	public final EZDCStringItem              almsSevtyLvlTxt_TR;

    /** XX_ABEND_MSG_TXT_TR*/
	public final EZDCStringItem              xxAbendMsgTxt_TR;

    /** ALMS_MSG_TXT_TR*/
	public final EZDCStringItem              almsMsgTxt_TR;

    /** XX_LOG_DTL_TXT_TR*/
	public final EZDCStringItem              xxLogDtlTxt_TR;

    /** ALMS_MSG_DTL_CLOD_TR*/
	public final EZDCMimeSourceItem              almsMsgDtlClod_TR;

    /** XX_DT_TM_FR*/
	public final EZDCStringItem              xxDtTm_FR;

    /** XX_DT_TM_TO*/
	public final EZDCStringItem              xxDtTm_TO;

    /** _EZRegistrationDateTime_TR*/
	public final EZDCStringItem              ezInTime_TR;

    /** _EZInTimeZone_TR*/
	public final EZDCStringItem              ezInTimeZone_TR;


	/**
	 * ZZOL0600_ACMsg is constructor.
	 * The initialization when the instance of ZZOL0600_ACMsg is generated.
	 */
	public ZZOL0600_ACMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0600_ACMsg is constructor.
	 * The initialization when the instance of ZZOL0600_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0600_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_TR = (EZDCStringItem)newItem("glblCmpyCd_TR");
		almsUnivsUniqId_TR = (EZDCStringItem)newItem("almsUnivsUniqId_TR");
		almsSysNm_TR = (EZDCStringItem)newItem("almsSysNm_TR");
		almsHostNm_TR = (EZDCStringItem)newItem("almsHostNm_TR");
		almsUsrId_TR = (EZDCStringItem)newItem("almsUsrId_TR");
		almsMsgId_TR = (EZDCStringItem)newItem("almsMsgId_TR");
		almsFwkVrsnTxt_TR = (EZDCStringItem)newItem("almsFwkVrsnTxt_TR");
		almsAppVrsnTxt_TR = (EZDCStringItem)newItem("almsAppVrsnTxt_TR");
		almsDbVrsnTxt_TR = (EZDCStringItem)newItem("almsDbVrsnTxt_TR");
		almsRelVrsnTxt_TR = (EZDCStringItem)newItem("almsRelVrsnTxt_TR");
		almsPgmId_TR = (EZDCStringItem)newItem("almsPgmId_TR");
		almsJobId_TR = (EZDCStringItem)newItem("almsJobId_TR");
		xxAlmsOnlBatFlgTxt_TR = (EZDCStringItem)newItem("xxAlmsOnlBatFlgTxt_TR");
		almsJvmNm_TR = (EZDCStringItem)newItem("almsJvmNm_TR");
		almsBatProcId_TR = (EZDCBigDecimalItem)newItem("almsBatProcId_TR");
		almsSevtyLvlTxt_TR = (EZDCStringItem)newItem("almsSevtyLvlTxt_TR");
		xxAbendMsgTxt_TR = (EZDCStringItem)newItem("xxAbendMsgTxt_TR");
		almsMsgTxt_TR = (EZDCStringItem)newItem("almsMsgTxt_TR");
		xxLogDtlTxt_TR = (EZDCStringItem)newItem("xxLogDtlTxt_TR");
		almsMsgDtlClod_TR = (EZDCMimeSourceItem)newItem("almsMsgDtlClod_TR");
		xxDtTm_FR = (EZDCStringItem)newItem("xxDtTm_FR");
		xxDtTm_TO = (EZDCStringItem)newItem("xxDtTm_TO");
		ezInTime_TR = (EZDCStringItem)newItem("ezInTime_TR");
		ezInTimeZone_TR = (EZDCStringItem)newItem("ezInTimeZone_TR");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0600_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0600_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_TR", "glblCmpyCd_TR", "TR", null, TYPE_HANKAKUEISU, "4", null},
	{"almsUnivsUniqId_TR", "almsUnivsUniqId_TR", "TR", null, TYPE_HANKAKUEISU, "40", null},
	{"almsSysNm_TR", "almsSysNm_TR", "TR", null, TYPE_HANKAKUEISU, "10", null},
	{"almsHostNm_TR", "almsHostNm_TR", "TR", null, TYPE_HANKAKUEISU, "30", null},
	{"almsUsrId_TR", "almsUsrId_TR", "TR", null, TYPE_HANKAKUEISU, "16", null},
	{"almsMsgId_TR", "almsMsgId_TR", "TR", null, TYPE_HANKAKUEISU, "15", null},
	{"almsFwkVrsnTxt_TR", "almsFwkVrsnTxt_TR", "TR", null, TYPE_HANKAKUEISU, "40", null},
	{"almsAppVrsnTxt_TR", "almsAppVrsnTxt_TR", "TR", null, TYPE_HANKAKUEISU, "40", null},
	{"almsDbVrsnTxt_TR", "almsDbVrsnTxt_TR", "TR", null, TYPE_HANKAKUEISU, "40", null},
	{"almsRelVrsnTxt_TR", "almsRelVrsnTxt_TR", "TR", null, TYPE_HANKAKUEISU, "40", null},
	{"almsPgmId_TR", "almsPgmId_TR", "TR", null, TYPE_HANKAKUEISU, "50", null},
	{"almsJobId_TR", "almsJobId_TR", "TR", null, TYPE_HANKAKUEISU, "18", null},
	{"xxAlmsOnlBatFlgTxt_TR", "xxAlmsOnlBatFlgTxt_TR", "TR", null, TYPE_HANKAKUEISU, "6", null},
	{"almsJvmNm_TR", "almsJvmNm_TR", "TR", null, TYPE_HANKAKUEISU, "20", null},
	{"almsBatProcId_TR", "almsBatProcId_TR", "TR", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"almsSevtyLvlTxt_TR", "almsSevtyLvlTxt_TR", "TR", null, TYPE_HANKAKUEISU, "1", null},
	{"xxAbendMsgTxt_TR", "xxAbendMsgTxt_TR", "TR", null, TYPE_HANKAKUEISU, "200", null},
	{"almsMsgTxt_TR", "almsMsgTxt_TR", "TR", null, TYPE_HANKAKUEISU, "200", null},
	{"xxLogDtlTxt_TR", "xxLogDtlTxt_TR", "TR", null, TYPE_HANKAKUEISU, "10000", null},
	{"almsMsgDtlClod_TR", "almsMsgDtlClod_TR", "TR", null, TYPE_UPLOAD, null, null},
	{"xxDtTm_FR", "xxDtTm_FR", "FR", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_TO", "xxDtTm_TO", "TO", null, TYPE_HANKAKUEISU, "23", null},
	{"ezInTime_TR", "ezInTime_TR", "TR", null, TYPE_HANKAKUEISU, "17", null},
	{"ezInTimeZone_TR", "ezInTimeZone_TR", "TR", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_TR
        {"ALMS_UNIVS_UNIQ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsUnivsUniqId_TR
        {"ALMS_SYS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsSysNm_TR
        {"ALMS_HOST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsHostNm_TR
        {"ALMS_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsUsrId_TR
        {"ALMS_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsMsgId_TR
        {"ALMS_FWK_VRSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsFwkVrsnTxt_TR
        {"ALMS_APP_VRSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsAppVrsnTxt_TR
        {"ALMS_DB_VRSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsDbVrsnTxt_TR
        {"ALMS_REL_VRSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsRelVrsnTxt_TR
        {"ALMS_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsPgmId_TR
        {"ALMS_JOB_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsJobId_TR
        {"XX_ALMS_ONL_BAT_FLG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAlmsOnlBatFlgTxt_TR
        {"ALMS_JVM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsJvmNm_TR
        {"ALMS_BAT_PROC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsBatProcId_TR
        {"ALMS_SEVTY_LVL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsSevtyLvlTxt_TR
        {"XX_ABEND_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAbendMsgTxt_TR
        {"ALMS_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsMsgTxt_TR
        {"XX_LOG_DTL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLogDtlTxt_TR
        {"ALMS_MSG_DTL_CLOD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//almsMsgDtlClod_TR
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_FR
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_TO
        {"_EZRegistrationDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTime_TR
        {"_EZInTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInTimeZone_TR
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
