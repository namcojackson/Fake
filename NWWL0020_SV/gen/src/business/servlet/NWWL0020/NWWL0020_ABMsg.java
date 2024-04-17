//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160914093253000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0020_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0020_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_ACT_DTL_PK_A0*/
	public final EZDBBigDecimalItem              ntfyActDtlPk_A0;

    /** _EZUpdateDateTime_A0*/
	public final EZDBStringItem              ezUpTime_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDBStringItem              ezUpTimeZone_A0;

    /** NTFY_ACT_ID_A0*/
	public final EZDBStringItem              ntfyActId_A0;

    /** NTFY_ACT_NM_A0*/
	public final EZDBStringItem              ntfyActNm_A0;

    /** NTFY_ACT_DESC_TXT_A0*/
	public final EZDBStringItem              ntfyActDescTxt_A0;

    /** NTFY_ACT_TP_CD_A0*/
	public final EZDBStringItem              ntfyActTpCd_A0;

    /** NTFY_ACT_TP_DESC_TXT_A0*/
	public final EZDBStringItem              ntfyActTpDescTxt_A0;


	/**
	 * NWWL0020_ABMsg is constructor.
	 * The initialization when the instance of NWWL0020_ABMsg is generated.
	 */
	public NWWL0020_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0020_ABMsg is constructor.
	 * The initialization when the instance of NWWL0020_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0020_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyActDtlPk_A0 = (EZDBBigDecimalItem)newItem("ntfyActDtlPk_A0");
		ezUpTime_A0 = (EZDBStringItem)newItem("ezUpTime_A0");
		ezUpTimeZone_A0 = (EZDBStringItem)newItem("ezUpTimeZone_A0");
		ntfyActId_A0 = (EZDBStringItem)newItem("ntfyActId_A0");
		ntfyActNm_A0 = (EZDBStringItem)newItem("ntfyActNm_A0");
		ntfyActDescTxt_A0 = (EZDBStringItem)newItem("ntfyActDescTxt_A0");
		ntfyActTpCd_A0 = (EZDBStringItem)newItem("ntfyActTpCd_A0");
		ntfyActTpDescTxt_A0 = (EZDBStringItem)newItem("ntfyActTpDescTxt_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0020_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0020_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyActDtlPk_A0", "ntfyActDtlPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A0", "ezUpTime_A0", "A0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	{"ntfyActId_A0", "ntfyActId_A0", "A0", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyActNm_A0", "ntfyActNm_A0", "A0", null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyActDescTxt_A0", "ntfyActDescTxt_A0", "A0", null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyActTpCd_A0", "ntfyActTpCd_A0", "A0", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfyActTpDescTxt_A0", "ntfyActTpDescTxt_A0", "A0", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_ACT_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDtlPk_A0
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A0
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
        {"NTFY_ACT_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActId_A0
        {"NTFY_ACT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActNm_A0
        {"NTFY_ACT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDescTxt_A0
        {"NTFY_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActTpCd_A0
        {"NTFY_ACT_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActTpDescTxt_A0
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
