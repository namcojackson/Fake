//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160609101156000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1090_FSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1090 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1090_FSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_TOC_CD_F*/
	public final EZDSStringItem              techTocCd_F;

    /** TECH_NM_F*/
	public final EZDSStringItem              techNm_F;

    /** RTL_WH_CD_F*/
	public final EZDSStringItem              rtlWhCd_F;

    /** RTL_WH_NM_F*/
	public final EZDSStringItem              rtlWhNm_F;

    /** RTL_SWH_CD_F*/
	public final EZDSStringItem              rtlSwhCd_F;

    /** RTL_SWH_NM_F*/
	public final EZDSStringItem              rtlSwhNm_F;

    /** LOC_NM_F*/
	public final EZDSStringItem              locNm_F;

    /** SHIP_TO_CUST_CD_F*/
	public final EZDSStringItem              shipToCustCd_F;

    /** CARR_CD_F*/
	public final EZDSStringItem              carrCd_F;

    /** CARR_NM_F*/
	public final EZDSStringItem              carrNm_F;


	/**
	 * NPAL1090_FSMsg is constructor.
	 * The initialization when the instance of NPAL1090_FSMsg is generated.
	 */
	public NPAL1090_FSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1090_FSMsg is constructor.
	 * The initialization when the instance of NPAL1090_FSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1090_FSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techTocCd_F = (EZDSStringItem)newItem("techTocCd_F");
		techNm_F = (EZDSStringItem)newItem("techNm_F");
		rtlWhCd_F = (EZDSStringItem)newItem("rtlWhCd_F");
		rtlWhNm_F = (EZDSStringItem)newItem("rtlWhNm_F");
		rtlSwhCd_F = (EZDSStringItem)newItem("rtlSwhCd_F");
		rtlSwhNm_F = (EZDSStringItem)newItem("rtlSwhNm_F");
		locNm_F = (EZDSStringItem)newItem("locNm_F");
		shipToCustCd_F = (EZDSStringItem)newItem("shipToCustCd_F");
		carrCd_F = (EZDSStringItem)newItem("carrCd_F");
		carrNm_F = (EZDSStringItem)newItem("carrNm_F");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1090_FSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1090_FSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techTocCd_F", "techTocCd_F", "F", null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_F", "techNm_F", "F", null, TYPE_HANKAKUEISU, "45", null},
	{"rtlWhCd_F", "rtlWhCd_F", "F", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_F", "rtlWhNm_F", "F", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_F", "rtlSwhCd_F", "F", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_F", "rtlSwhNm_F", "F", null, TYPE_HANKAKUEISU, "30", null},
	{"locNm_F", "locNm_F", "F", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToCustCd_F", "shipToCustCd_F", "F", null, TYPE_HANKAKUEISU, "20", null},
	{"carrCd_F", "carrCd_F", "F", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_F", "carrNm_F", "F", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd_F
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_F
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_F
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_F
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_F
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_F
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_F
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_F
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_F
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_F
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

