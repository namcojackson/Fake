//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20191109152746000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2240_ESMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2240 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2240_ESMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_PK_E0*/
	public final EZDSBigDecimalItem              ctacPsnPk_E0;

    /** DEL_FLG_E0*/
	public final EZDSStringItem              delFlg_E0;

    /** CTAC_PSN_TP_CD_E0*/
	public final EZDSStringItem              ctacPsnTpCd_E0;

    /** CTAC_PSN_FIRST_NM_E0*/
	public final EZDSStringItem              ctacPsnFirstNm_E0;

    /** CTAC_PSN_LAST_NM_E0*/
	public final EZDSStringItem              ctacPsnLastNm_E0;

    /** CTAC_PSN_TEL_NUM_E0*/
	public final EZDSStringItem              ctacPsnTelNum_E0;

    /** CTAC_PSN_EXTN_NUM_E0*/
	public final EZDSStringItem              ctacPsnExtnNum_E0;

    /** CTAC_PSN_EML_ADDR_E0*/
	public final EZDSStringItem              ctacPsnEmlAddr_E0;

    /** CTAC_PSN_FAX_NUM_E0*/
	public final EZDSStringItem              ctacPsnFaxNum_E0;

    /** _EZUpTimeZone_E0*/
	public final EZDSStringItem              ezUpTimeZone_E0;

    /** _EZUpdateDateTime_E0*/
	public final EZDSStringItem              ezUpTime_E0;


	/**
	 * NWAL2240_ESMsg is constructor.
	 * The initialization when the instance of NWAL2240_ESMsg is generated.
	 */
	public NWAL2240_ESMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2240_ESMsg is constructor.
	 * The initialization when the instance of NWAL2240_ESMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2240_ESMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnPk_E0 = (EZDSBigDecimalItem)newItem("ctacPsnPk_E0");
		delFlg_E0 = (EZDSStringItem)newItem("delFlg_E0");
		ctacPsnTpCd_E0 = (EZDSStringItem)newItem("ctacPsnTpCd_E0");
		ctacPsnFirstNm_E0 = (EZDSStringItem)newItem("ctacPsnFirstNm_E0");
		ctacPsnLastNm_E0 = (EZDSStringItem)newItem("ctacPsnLastNm_E0");
		ctacPsnTelNum_E0 = (EZDSStringItem)newItem("ctacPsnTelNum_E0");
		ctacPsnExtnNum_E0 = (EZDSStringItem)newItem("ctacPsnExtnNum_E0");
		ctacPsnEmlAddr_E0 = (EZDSStringItem)newItem("ctacPsnEmlAddr_E0");
		ctacPsnFaxNum_E0 = (EZDSStringItem)newItem("ctacPsnFaxNum_E0");
		ezUpTimeZone_E0 = (EZDSStringItem)newItem("ezUpTimeZone_E0");
		ezUpTime_E0 = (EZDSStringItem)newItem("ezUpTime_E0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2240_ESMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2240_ESMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnPk_E0", "ctacPsnPk_E0", "E0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"delFlg_E0", "delFlg_E0", "E0", null, TYPE_HANKAKUEISU, "1", null},
	{"ctacPsnTpCd_E0", "ctacPsnTpCd_E0", "E0", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnFirstNm_E0", "ctacPsnFirstNm_E0", "E0", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_E0", "ctacPsnLastNm_E0", "E0", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnTelNum_E0", "ctacPsnTelNum_E0", "E0", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnExtnNum_E0", "ctacPsnExtnNum_E0", "E0", null, TYPE_HANKAKUEISU, "10", null},
	{"ctacPsnEmlAddr_E0", "ctacPsnEmlAddr_E0", "E0", null, TYPE_HANKAKUEISU, "320", null},
	{"ctacPsnFaxNum_E0", "ctacPsnFaxNum_E0", "E0", null, TYPE_HANKAKUEISU, "20", null},
	{"ezUpTimeZone_E0", "ezUpTimeZone_E0", "E0", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_E0", "ezUpTime_E0", "E0", null, TYPE_HANKAKUEISU, "17", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_E0
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_E0
        {"CTAC_PSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTpCd_E0
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_E0
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_E0
        {"CTAC_PSN_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTelNum_E0
        {"CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnExtnNum_E0
        {"CTAC_PSN_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr_E0
        {"CTAC_PSN_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFaxNum_E0
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E0
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E0
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
