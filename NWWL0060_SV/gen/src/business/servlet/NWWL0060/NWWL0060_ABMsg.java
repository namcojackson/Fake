//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160906152738000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0060_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0060_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_SBSCR_PK_A*/
	public final EZDBBigDecimalItem              ntfySbscrPk_A;

    /** NTFY_SBSCR_FLG_A*/
	public final EZDBStringItem              ntfySbscrFlg_A;

    /** NTFY_HDR_PK_A*/
	public final EZDBBigDecimalItem              ntfyHdrPk_A;

    /** NTFY_HDR_ID_A*/
	public final EZDBStringItem              ntfyHdrId_A;

    /** NTFY_HDR_NM_A*/
	public final EZDBStringItem              ntfyHdrNm_A;

    /** NTFY_HDR_DESC_TXT_A*/
	public final EZDBStringItem              ntfyHdrDescTxt_A;

    /** NTFY_BIZ_AREA_TP_DESC_TXT_A*/
	public final EZDBStringItem              ntfyBizAreaTpDescTxt_A;

    /** NTFY_SUB_AREA_TP_DESC_TXT_A*/
	public final EZDBStringItem              ntfySubAreaTpDescTxt_A;

    /** NTFY_DIST_LIST_NM_LIST_TXT_A*/
	public final EZDBStringItem              ntfyDistListNmListTxt_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;


	/**
	 * NWWL0060_ABMsg is constructor.
	 * The initialization when the instance of NWWL0060_ABMsg is generated.
	 */
	public NWWL0060_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0060_ABMsg is constructor.
	 * The initialization when the instance of NWWL0060_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0060_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfySbscrPk_A = (EZDBBigDecimalItem)newItem("ntfySbscrPk_A");
		ntfySbscrFlg_A = (EZDBStringItem)newItem("ntfySbscrFlg_A");
		ntfyHdrPk_A = (EZDBBigDecimalItem)newItem("ntfyHdrPk_A");
		ntfyHdrId_A = (EZDBStringItem)newItem("ntfyHdrId_A");
		ntfyHdrNm_A = (EZDBStringItem)newItem("ntfyHdrNm_A");
		ntfyHdrDescTxt_A = (EZDBStringItem)newItem("ntfyHdrDescTxt_A");
		ntfyBizAreaTpDescTxt_A = (EZDBStringItem)newItem("ntfyBizAreaTpDescTxt_A");
		ntfySubAreaTpDescTxt_A = (EZDBStringItem)newItem("ntfySubAreaTpDescTxt_A");
		ntfyDistListNmListTxt_A = (EZDBStringItem)newItem("ntfyDistListNmListTxt_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0060_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0060_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfySbscrPk_A", "ntfySbscrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ntfySbscrFlg_A", "ntfySbscrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyHdrPk_A", "ntfyHdrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ntfyHdrId_A", "ntfyHdrId_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyHdrNm_A", "ntfyHdrNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyHdrDescTxt_A", "ntfyHdrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpDescTxt_A", "ntfyBizAreaTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"ntfySubAreaTpDescTxt_A", "ntfySubAreaTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"ntfyDistListNmListTxt_A", "ntfyDistListNmListTxt_A", "A", null, TYPE_HANKAKUEISU, "2000", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_SBSCR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySbscrPk_A
        {"NTFY_SBSCR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySbscrFlg_A
        {"NTFY_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrPk_A
        {"NTFY_HDR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrId_A
        {"NTFY_HDR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrNm_A
        {"NTFY_HDR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrDescTxt_A
        {"NTFY_BIZ_AREA_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpDescTxt_A
        {"NTFY_SUB_AREA_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpDescTxt_A
        {"NTFY_DIST_LIST_NM_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListNmListTxt_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

