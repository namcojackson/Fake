//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20151214101331000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3060_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3060;

import parts.common.*;

/**
 * It is NLBL3060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3060_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_C1*/
	public final EZDSStringItem              glblCmpyCd_C1;

    /** XX_CHK_BOX_C1*/
	public final EZDSStringItem              xxChkBox_C1;

    /** RTL_WH_CD_C1*/
	public final EZDSStringItem              rtlWhCd_C1;

    /** RTL_WH_NM_C1*/
	public final EZDSStringItem              rtlWhNm_C1;

    /** SCHD_COORD_PSN_CD_C1*/
	public final EZDSStringItem              schdCoordPsnCd_C1;

    /** FULL_PSN_NM_C1*/
	public final EZDSStringItem              fullPsnNm_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDSDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDSDateItem              effThruDt_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDSStringItem              ezUpTime_C1;

    /** _EZUpTimeZone_C1*/
	public final EZDSStringItem              ezUpTimeZone_C1;
	
    /** RTL_WH_CATG_CD_C1*/
    public final EZDBStringItemArray              rtlWhCatgCd_C1;
    
    /** RTL_WH_CATG_NM_C1*/
    public final EZDBStringItemArray              rtlWhCatgNm_C1;


	/**
	 * NLBL3060_CSMsg is constructor.
	 * The initialization when the instance of NLBL3060_CSMsg is generated.
	 */
	public NLBL3060_CSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3060_CSMsg is constructor.
	 * The initialization when the instance of NLBL3060_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3060_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_C1 = (EZDSStringItem)newItem("glblCmpyCd_C1");
		xxChkBox_C1 = (EZDSStringItem)newItem("xxChkBox_C1");
		rtlWhCd_C1 = (EZDSStringItem)newItem("rtlWhCd_C1");
		rtlWhNm_C1 = (EZDSStringItem)newItem("rtlWhNm_C1");
		schdCoordPsnCd_C1 = (EZDSStringItem)newItem("schdCoordPsnCd_C1");
		fullPsnNm_C1 = (EZDSStringItem)newItem("fullPsnNm_C1");
		effFromDt_C1 = (EZDSDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDSDateItem)newItem("effThruDt_C1");
		ezUpTime_C1 = (EZDSStringItem)newItem("ezUpTime_C1");
		ezUpTimeZone_C1 = (EZDSStringItem)newItem("ezUpTimeZone_C1");
		rtlWhCatgCd_C1 = (EZDBStringItemArray)newItemArray("rtlWhCatgCd_C1");
		rtlWhCatgNm_C1 = (EZDBStringItemArray)newItemArray("rtlWhCatgNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3060_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3060_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_C1", "glblCmpyCd_C1", "C1", null, TYPE_HANKAKUEISU, "4", null},
	{"xxChkBox_C1", "xxChkBox_C1", "C1", null, TYPE_HANKAKUEIJI, "1", null},
	{"rtlWhCd_C1", "rtlWhCd_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_C1", "rtlWhNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"schdCoordPsnCd_C1", "schdCoordPsnCd_C1", "C1", null, TYPE_HANKAKUEISU, "8", null},
	{"fullPsnNm_C1", "fullPsnNm_C1", "C1", null, TYPE_HANKAKUEISU, "62", null},
	{"effFromDt_C1", "effFromDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_C1", "effThruDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_C1", "ezUpTime_C1", "C1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_C1", "ezUpTimeZone_C1", "C1", null, TYPE_HANKAKUEISU, "5", null},
	{"rtlWhCatgCd_C1", "rtlWhCatgCd_C1", "C1", "99", TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCatgNm_C1", "rtlWhCatgNm_C1", "C1", "99", TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_C1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_C1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_C1
        {"SCHD_COORD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordPsnCd_C1
        {"FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_C1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_C1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_C1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C1
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //rtlWhCatgCd_C1
        {"RTL_WH_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO}, //rtlWhCatgNm_C1
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
