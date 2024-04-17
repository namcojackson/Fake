//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130530053716000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040_TSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040_TSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ST_NM_T1*/
	public final EZDSStringItem              stNm_T1;

    /** SHPG_MODE_NM_T1*/
	public final EZDSStringItem              shpgModeNm_T1;

    /** DELY_LEAD_AOT_T1*/
	public final EZDSBigDecimalItem              delyLeadAot_T1;

    /** WH_CD_T1*/
	public final EZDSStringItem              whCd_T1;

    /** ST_CD_T1*/
	public final EZDSStringItem              stCd_T1;

    /** SHPG_MODE_CD_T1*/
	public final EZDSStringItem              shpgModeCd_T1;

    /** EFF_FROM_DT_T1*/
	public final EZDSDateItem              effFromDt_T1;

    /** EFF_THRU_DT_T1*/
	public final EZDSDateItem              effThruDt_T1;

    /** _EZUpdateDateTime_T1*/
	public final EZDSStringItem              ezUpTime_T1;

    /** _EZUpTimeZone_T1*/
	public final EZDSStringItem              ezUpTimeZone_T1;


	/**
	 * NLBL0040_TSMsg is constructor.
	 * The initialization when the instance of NLBL0040_TSMsg is generated.
	 */
	public NLBL0040_TSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040_TSMsg is constructor.
	 * The initialization when the instance of NLBL0040_TSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040_TSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		stNm_T1 = (EZDSStringItem)newItem("stNm_T1");
		shpgModeNm_T1 = (EZDSStringItem)newItem("shpgModeNm_T1");
		delyLeadAot_T1 = (EZDSBigDecimalItem)newItem("delyLeadAot_T1");
		whCd_T1 = (EZDSStringItem)newItem("whCd_T1");
		stCd_T1 = (EZDSStringItem)newItem("stCd_T1");
		shpgModeCd_T1 = (EZDSStringItem)newItem("shpgModeCd_T1");
		effFromDt_T1 = (EZDSDateItem)newItem("effFromDt_T1");
		effThruDt_T1 = (EZDSDateItem)newItem("effThruDt_T1");
		ezUpTime_T1 = (EZDSStringItem)newItem("ezUpTime_T1");
		ezUpTimeZone_T1 = (EZDSStringItem)newItem("ezUpTimeZone_T1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040_TSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040_TSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"stNm_T1", "stNm_T1", "T1", null, TYPE_HANKAKUEISU, "25", null},
	{"shpgModeNm_T1", "shpgModeNm_T1", "T1", null, TYPE_HANKAKUEISU, "30", null},
	{"delyLeadAot_T1", "delyLeadAot_T1", "T1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"whCd_T1", "whCd_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"stCd_T1", "stCd_T1", "T1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgModeCd_T1", "shpgModeCd_T1", "T1", null, TYPE_HANKAKUEISU, "2", null},
	{"effFromDt_T1", "effFromDt_T1", "T1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_T1", "effThruDt_T1", "T1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_T1", "ezUpTime_T1", "T1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_T1", "ezUpTimeZone_T1", "T1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_T1
        {"SHPG_MODE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeNm_T1
        {"DELY_LEAD_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyLeadAot_T1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_T1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_T1
        {"SHPG_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_T1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_T1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_T1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_T1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_T1
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
