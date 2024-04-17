//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20150903222458000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1370SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1370;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1370 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1370SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RTL_WH_CD_FR*/
	public final EZDSStringItem              rtlWhCd_FR;

    /** RTL_WH_NM_FR*/
	public final EZDSStringItem              rtlWhNm_FR;

    /** RTL_SWH_CD_FR*/
	public final EZDSStringItem              rtlSwhCd_FR;

    /** RTL_SWH_NM_FR*/
	public final EZDSStringItem              rtlSwhNm_FR;

    /** RTL_WH_CD_TO*/
	public final EZDSStringItem              rtlWhCd_TO;

    /** RTL_WH_NM_TO*/
	public final EZDSStringItem              rtlWhNm_TO;

    /** RTL_SWH_CD_TO*/
	public final EZDSStringItem              rtlSwhCd_TO;

    /** RTL_SWH_NM_TO*/
	public final EZDSStringItem              rtlSwhNm_TO;

    /** MRP_ENBL_FLG*/
	public final EZDSStringItem              mrpEnblFlg;

    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** XX_POP_PRM_EV*/
	public final EZDSStringItem              xxPopPrm_EV;

    /** P*/
	public final business.blap.NPAL1370.NPAL1370_PSMsgArray              P;


	/**
	 * NPAL1370SMsg is constructor.
	 * The initialization when the instance of NPAL1370SMsg is generated.
	 */
	public NPAL1370SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1370SMsg is constructor.
	 * The initialization when the instance of NPAL1370SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1370SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rtlWhCd_FR = (EZDSStringItem)newItem("rtlWhCd_FR");
		rtlWhNm_FR = (EZDSStringItem)newItem("rtlWhNm_FR");
		rtlSwhCd_FR = (EZDSStringItem)newItem("rtlSwhCd_FR");
		rtlSwhNm_FR = (EZDSStringItem)newItem("rtlSwhNm_FR");
		rtlWhCd_TO = (EZDSStringItem)newItem("rtlWhCd_TO");
		rtlWhNm_TO = (EZDSStringItem)newItem("rtlWhNm_TO");
		rtlSwhCd_TO = (EZDSStringItem)newItem("rtlSwhCd_TO");
		rtlSwhNm_TO = (EZDSStringItem)newItem("rtlSwhNm_TO");
		mrpEnblFlg = (EZDSStringItem)newItem("mrpEnblFlg");
		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		xxPopPrm_EV = (EZDSStringItem)newItem("xxPopPrm_EV");
		P = (business.blap.NPAL1370.NPAL1370_PSMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1370SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1370SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rtlWhCd_FR", "rtlWhCd_FR", "FR", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_FR", "rtlWhNm_FR", "FR", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_FR", "rtlSwhCd_FR", "FR", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_FR", "rtlSwhNm_FR", "FR", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd_TO", "rtlWhCd_TO", "TO", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_TO", "rtlWhNm_TO", "TO", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_TO", "rtlSwhCd_TO", "TO", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_TO", "rtlSwhNm_TO", "TO", null, TYPE_HANKAKUEISU, "30", null},
	{"mrpEnblFlg", "mrpEnblFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPopPrm_EV", "xxPopPrm_EV", "EV", null, TYPE_HANKAKUEISU, "300", null},
	{"P", "P", null, "10", "business.blap.NPAL1370.NPAL1370_PSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_FR
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_FR
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_FR
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_FR
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_TO
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_TO
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_TO
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_TO
        {"MRP_ENBL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
		null,	//P
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
