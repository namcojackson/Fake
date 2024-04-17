//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170804050257000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3150_BSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLBL3150 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3150_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDSStringItem              xxChkBox_B;

    /** MDSE_WH_COND_PK_B*/
	public final EZDSBigDecimalItem              mdseWhCondPk_B;

    /** MDSE_CD_B*/
	public final EZDSStringItem              mdseCd_B;

    /** MDSE_DESC_SHORT_TXT_B*/
	public final EZDSStringItem              mdseDescShortTxt_B;

    /** RTL_WH_CD_B*/
	public final EZDSStringItem              rtlWhCd_B;

    /** RTL_WH_NM_B*/
	public final EZDSStringItem              rtlWhNm_B;

    /** RTL_SWH_CD_B*/
	public final EZDSStringItem              rtlSwhCd_B;

    /** RTL_SWH_NM_B*/
	public final EZDSStringItem              rtlSwhNm_B;

    /** _EZUpdateDateTime_B*/
	public final EZDSStringItem              ezUpTime_B;

    /** _EZUpTimeZone_B*/
	public final EZDSStringItem              ezUpTimeZone_B;


	/**
	 * NLBL3150_BSMsg is constructor.
	 * The initialization when the instance of NLBL3150_BSMsg is generated.
	 */
	public NLBL3150_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3150_BSMsg is constructor.
	 * The initialization when the instance of NLBL3150_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3150_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDSStringItem)newItem("xxChkBox_B");
		mdseWhCondPk_B = (EZDSBigDecimalItem)newItem("mdseWhCondPk_B");
		mdseCd_B = (EZDSStringItem)newItem("mdseCd_B");
		mdseDescShortTxt_B = (EZDSStringItem)newItem("mdseDescShortTxt_B");
		rtlWhCd_B = (EZDSStringItem)newItem("rtlWhCd_B");
		rtlWhNm_B = (EZDSStringItem)newItem("rtlWhNm_B");
		rtlSwhCd_B = (EZDSStringItem)newItem("rtlSwhCd_B");
		rtlSwhNm_B = (EZDSStringItem)newItem("rtlSwhNm_B");
		ezUpTime_B = (EZDSStringItem)newItem("ezUpTime_B");
		ezUpTimeZone_B = (EZDSStringItem)newItem("ezUpTimeZone_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3150_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3150_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseWhCondPk_B", "mdseWhCondPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_B", "mdseCd_B", "B", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B", "mdseDescShortTxt_B", "B", null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCd_B", "rtlWhCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_B", "rtlWhNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_B", "rtlSwhCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_B", "rtlSwhNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"MDSE_WH_COND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseWhCondPk_B
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_B
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_B
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_B
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_B
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
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
