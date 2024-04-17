//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170804050256000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3150_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3150_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** MDSE_WH_COND_PK_A*/
	public final EZDCBigDecimalItem              mdseWhCondPk_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** RTL_WH_CD_A*/
	public final EZDCStringItem              rtlWhCd_A;

    /** RTL_WH_NM_A*/
	public final EZDCStringItem              rtlWhNm_A;

    /** RTL_SWH_CD_A*/
	public final EZDCStringItem              rtlSwhCd_A;

    /** RTL_SWH_NM_A*/
	public final EZDCStringItem              rtlSwhNm_A;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;


	/**
	 * NLBL3150_ACMsg is constructor.
	 * The initialization when the instance of NLBL3150_ACMsg is generated.
	 */
	public NLBL3150_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3150_ACMsg is constructor.
	 * The initialization when the instance of NLBL3150_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3150_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		mdseWhCondPk_A = (EZDCBigDecimalItem)newItem("mdseWhCondPk_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		rtlWhCd_A = (EZDCStringItem)newItem("rtlWhCd_A");
		rtlWhNm_A = (EZDCStringItem)newItem("rtlWhNm_A");
		rtlSwhCd_A = (EZDCStringItem)newItem("rtlSwhCd_A");
		rtlSwhNm_A = (EZDCStringItem)newItem("rtlSwhNm_A");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3150_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3150_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseWhCondPk_A", "mdseWhCondPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCd_A", "rtlWhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A", "rtlWhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A", "rtlSwhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"MDSE_WH_COND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseWhCondPk_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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
