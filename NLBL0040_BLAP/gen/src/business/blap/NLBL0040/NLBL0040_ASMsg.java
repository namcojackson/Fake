//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130530053716000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040_ASMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NLBL0040_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ST_NM_A1*/
	public final EZDSStringItem              stNm_A1;

    /** SHPG_MODE_NM_A1*/
	public final EZDSStringItem              shpgModeNm_A1;

    /** DELY_LEAD_AOT_A1*/
	public final EZDSBigDecimalItem              delyLeadAot_A1;

    /** WH_CD_A1*/
	public final EZDSStringItem              whCd_A1;

    /** ST_CD_A1*/
	public final EZDSStringItem              stCd_A1;

    /** SHPG_MODE_CD_A1*/
	public final EZDSStringItem              shpgModeCd_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDSDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDSDateItem              effThruDt_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;


	/**
	 * NLBL0040_ASMsg is constructor.
	 * The initialization when the instance of NLBL0040_ASMsg is generated.
	 */
	public NLBL0040_ASMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040_ASMsg is constructor.
	 * The initialization when the instance of NLBL0040_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		stNm_A1 = (EZDSStringItem)newItem("stNm_A1");
		shpgModeNm_A1 = (EZDSStringItem)newItem("shpgModeNm_A1");
		delyLeadAot_A1 = (EZDSBigDecimalItem)newItem("delyLeadAot_A1");
		whCd_A1 = (EZDSStringItem)newItem("whCd_A1");
		stCd_A1 = (EZDSStringItem)newItem("stCd_A1");
		shpgModeCd_A1 = (EZDSStringItem)newItem("shpgModeCd_A1");
		effFromDt_A1 = (EZDSDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDSDateItem)newItem("effThruDt_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"stNm_A1", "stNm_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"shpgModeNm_A1", "shpgModeNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"delyLeadAot_A1", "delyLeadAot_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"whCd_A1", "whCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"stCd_A1", "stCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgModeCd_A1", "shpgModeCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_A1
        {"SHPG_MODE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeNm_A1
        {"DELY_LEAD_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyLeadAot_A1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_A1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A1
        {"SHPG_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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
