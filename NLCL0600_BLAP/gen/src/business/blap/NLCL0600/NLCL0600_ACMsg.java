//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160414023200000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0600_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0600 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0600_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** RTL_SWH_CD_A1*/
	public final EZDCStringItem              rtlSwhCd_A1;

    /** RTL_SWH_NM_A1*/
	public final EZDCStringItem              rtlSwhNm_A1;

    /** INVTY_LOC_CD_A1*/
	public final EZDCStringItem              invtyLocCd_A1;

    /** RTL_WH_CD_A1*/
	public final EZDCStringItem              rtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDCStringItem              rtlWhNm_A1;

    /** PHYS_INVTY_DT_A1*/
	public final EZDCDateItem              physInvtyDt_A1;

    /** PHYS_INVTY_CTRL_NM_A1*/
	public final EZDCStringItem              physInvtyCtrlNm_A1;

    /** PHYS_INVTY_CTRL_DESC_NM_A1*/
	public final EZDCStringItem              physInvtyCtrlDescNm_A1;

    /** LOC_TP_CD_A1*/
	public final EZDCStringItem              locTpCd_A1;

    /** XX_RQST_TS_A1*/
	public final EZDCStringItem              xxRqstTs_A1;

    /** XX_RQST_TZ_A1*/
	public final EZDCStringItem              xxRqstTz_A1;


	/**
	 * NLCL0600_ACMsg is constructor.
	 * The initialization when the instance of NLCL0600_ACMsg is generated.
	 */
	public NLCL0600_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0600_ACMsg is constructor.
	 * The initialization when the instance of NLCL0600_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0600_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		rtlSwhCd_A1 = (EZDCStringItem)newItem("rtlSwhCd_A1");
		rtlSwhNm_A1 = (EZDCStringItem)newItem("rtlSwhNm_A1");
		invtyLocCd_A1 = (EZDCStringItem)newItem("invtyLocCd_A1");
		rtlWhCd_A1 = (EZDCStringItem)newItem("rtlWhCd_A1");
		rtlWhNm_A1 = (EZDCStringItem)newItem("rtlWhNm_A1");
		physInvtyDt_A1 = (EZDCDateItem)newItem("physInvtyDt_A1");
		physInvtyCtrlNm_A1 = (EZDCStringItem)newItem("physInvtyCtrlNm_A1");
		physInvtyCtrlDescNm_A1 = (EZDCStringItem)newItem("physInvtyCtrlDescNm_A1");
		locTpCd_A1 = (EZDCStringItem)newItem("locTpCd_A1");
		xxRqstTs_A1 = (EZDCStringItem)newItem("xxRqstTs_A1");
		xxRqstTz_A1 = (EZDCStringItem)newItem("xxRqstTz_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0600_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0600_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"rtlSwhCd_A1", "rtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A1", "rtlSwhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"invtyLocCd_A1", "invtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd_A1", "rtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"physInvtyDt_A1", "physInvtyDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"physInvtyCtrlNm_A1", "physInvtyCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"physInvtyCtrlDescNm_A1", "physInvtyCtrlDescNm_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"locTpCd_A1", "locTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRqstTs_A1", "xxRqstTs_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRqstTz_A1", "xxRqstTz_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"PHYS_INVTY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyDt_A1
        {"PHYS_INVTY_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlNm_A1
        {"PHYS_INVTY_CTRL_DESC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlDescNm_A1
        {"LOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locTpCd_A1
        {"XX_RQST_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTs_A1
        {"XX_RQST_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTz_A1
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
