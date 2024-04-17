//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160914093253000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0020_BBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWWL0020_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_ACT_DTL_COL_PK_B0*/
	public final EZDBBigDecimalItem              ntfyActDtlColPk_B0;

    /** _EZUpdateDateTime_B0*/
	public final EZDBStringItem              ezUpTime_B0;

    /** _EZUpTimeZone_B0*/
	public final EZDBStringItem              ezUpTimeZone_B0;

    /** NTFY_ACT_DTL_COL_SORT_NUM_B0*/
	public final EZDBBigDecimalItem              ntfyActDtlColSortNum_B0;

    /** HDR_LB_NM_B0*/
	public final EZDBStringItem              hdrLbNm_B0;

    /** PLACE_HLD_NM_B0*/
	public final EZDBStringItem              placeHldNm_B0;


	/**
	 * NWWL0020_BBMsg is constructor.
	 * The initialization when the instance of NWWL0020_BBMsg is generated.
	 */
	public NWWL0020_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0020_BBMsg is constructor.
	 * The initialization when the instance of NWWL0020_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0020_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyActDtlColPk_B0 = (EZDBBigDecimalItem)newItem("ntfyActDtlColPk_B0");
		ezUpTime_B0 = (EZDBStringItem)newItem("ezUpTime_B0");
		ezUpTimeZone_B0 = (EZDBStringItem)newItem("ezUpTimeZone_B0");
		ntfyActDtlColSortNum_B0 = (EZDBBigDecimalItem)newItem("ntfyActDtlColSortNum_B0");
		hdrLbNm_B0 = (EZDBStringItem)newItem("hdrLbNm_B0");
		placeHldNm_B0 = (EZDBStringItem)newItem("placeHldNm_B0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0020_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0020_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyActDtlColPk_B0", "ntfyActDtlColPk_B0", "B0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_B0", "ezUpTime_B0", "B0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B0", "ezUpTimeZone_B0", "B0", null, TYPE_HANKAKUEISU, "5", null},
	{"ntfyActDtlColSortNum_B0", "ntfyActDtlColSortNum_B0", "B0", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"hdrLbNm_B0", "hdrLbNm_B0", "B0", null, TYPE_HANKAKUEISU, "60", null},
	{"placeHldNm_B0", "placeHldNm_B0", "B0", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_ACT_DTL_COL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDtlColPk_B0
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B0
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B0
        {"NTFY_ACT_DTL_COL_SORT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDtlColSortNum_B0
        {"HDR_LB_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hdrLbNm_B0
        {"PLACE_HLD_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//placeHldNm_B0
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

