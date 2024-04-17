//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160412003748000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0610_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0610 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0610_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PHYS_INVTY_NUM_A*/
	public final EZDCStringItem              physInvtyNum_A;

    /** RTL_WH_CD_A*/
	public final EZDCStringItem              rtlWhCd_A;

    /** RTL_WH_NM_A*/
	public final EZDCStringItem              rtlWhNm_A;

    /** RTL_SWH_CD_A*/
	public final EZDCStringItem              rtlSwhCd_A;

    /** RTL_SWH_NM_A*/
	public final EZDCStringItem              rtlSwhNm_A;

    /** PHYS_INVTY_DT_A*/
	public final EZDCDateItem              physInvtyDt_A;

    /** PHYS_INVTY_CTRL_NM_A*/
	public final EZDCStringItem              physInvtyCtrlNm_A;

    /** PHYS_INVTY_STS_NM_A*/
	public final EZDCStringItem              physInvtyStsNm_A;

    /** PHYS_INVTY_START_TS_A*/
	public final EZDCStringItem              physInvtyStartTs_A;

    /** PHYS_INVTY_CTRL_DESC_NM_A*/
	public final EZDCStringItem              physInvtyCtrlDescNm_A;

    /** XX_SCR_ITEM_20_TXT_A*/
	public final EZDCStringItem              xxScrItem20Txt_A;


	/**
	 * NLCL0610_ACMsg is constructor.
	 * The initialization when the instance of NLCL0610_ACMsg is generated.
	 */
	public NLCL0610_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0610_ACMsg is constructor.
	 * The initialization when the instance of NLCL0610_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0610_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		physInvtyNum_A = (EZDCStringItem)newItem("physInvtyNum_A");
		rtlWhCd_A = (EZDCStringItem)newItem("rtlWhCd_A");
		rtlWhNm_A = (EZDCStringItem)newItem("rtlWhNm_A");
		rtlSwhCd_A = (EZDCStringItem)newItem("rtlSwhCd_A");
		rtlSwhNm_A = (EZDCStringItem)newItem("rtlSwhNm_A");
		physInvtyDt_A = (EZDCDateItem)newItem("physInvtyDt_A");
		physInvtyCtrlNm_A = (EZDCStringItem)newItem("physInvtyCtrlNm_A");
		physInvtyStsNm_A = (EZDCStringItem)newItem("physInvtyStsNm_A");
		physInvtyStartTs_A = (EZDCStringItem)newItem("physInvtyStartTs_A");
		physInvtyCtrlDescNm_A = (EZDCStringItem)newItem("physInvtyCtrlDescNm_A");
		xxScrItem20Txt_A = (EZDCStringItem)newItem("xxScrItem20Txt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0610_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0610_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"physInvtyNum_A", "physInvtyNum_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"rtlWhCd_A", "rtlWhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A", "rtlWhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A", "rtlSwhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"physInvtyDt_A", "physInvtyDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"physInvtyCtrlNm_A", "physInvtyCtrlNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"physInvtyStsNm_A", "physInvtyStsNm_A", "A", null, TYPE_HANKAKUEISU, "70", null},
	{"physInvtyStartTs_A", "physInvtyStartTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"physInvtyCtrlDescNm_A", "physInvtyCtrlDescNm_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"xxScrItem20Txt_A", "xxScrItem20Txt_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PHYS_INVTY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyNum_A
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A
        {"PHYS_INVTY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyDt_A
        {"PHYS_INVTY_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlNm_A
        {"PHYS_INVTY_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyStsNm_A
        {"PHYS_INVTY_START_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyStartTs_A
        {"PHYS_INVTY_CTRL_DESC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlDescNm_A
        {"XX_SCR_ITEM_20_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem20Txt_A
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

