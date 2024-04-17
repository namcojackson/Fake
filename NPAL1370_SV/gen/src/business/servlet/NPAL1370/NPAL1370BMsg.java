//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160705024658000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1370BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1370;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1370 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1370BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RTL_WH_CD_FR*/
	public final EZDBStringItem              rtlWhCd_FR;

    /** RTL_WH_NM_FR*/
	public final EZDBStringItem              rtlWhNm_FR;

    /** RTL_SWH_CD_FR*/
	public final EZDBStringItem              rtlSwhCd_FR;

    /** RTL_SWH_NM_FR*/
	public final EZDBStringItem              rtlSwhNm_FR;

    /** RTL_WH_CD_TO*/
	public final EZDBStringItem              rtlWhCd_TO;

    /** RTL_WH_NM_TO*/
	public final EZDBStringItem              rtlWhNm_TO;

    /** RTL_SWH_CD_TO*/
	public final EZDBStringItem              rtlSwhCd_TO;

    /** RTL_SWH_NM_TO*/
	public final EZDBStringItem              rtlSwhNm_TO;

    /** MRP_ENBL_FLG*/
	public final EZDBStringItem              mrpEnblFlg;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** XX_POP_PRM_EV*/
	public final EZDBStringItem              xxPopPrm_EV;

    /** P*/
	public final business.servlet.NPAL1370.NPAL1370_PBMsgArray              P;


	/**
	 * NPAL1370BMsg is constructor.
	 * The initialization when the instance of NPAL1370BMsg is generated.
	 */
	public NPAL1370BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1370BMsg is constructor.
	 * The initialization when the instance of NPAL1370BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1370BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rtlWhCd_FR = (EZDBStringItem)newItem("rtlWhCd_FR");
		rtlWhNm_FR = (EZDBStringItem)newItem("rtlWhNm_FR");
		rtlSwhCd_FR = (EZDBStringItem)newItem("rtlSwhCd_FR");
		rtlSwhNm_FR = (EZDBStringItem)newItem("rtlSwhNm_FR");
		rtlWhCd_TO = (EZDBStringItem)newItem("rtlWhCd_TO");
		rtlWhNm_TO = (EZDBStringItem)newItem("rtlWhNm_TO");
		rtlSwhCd_TO = (EZDBStringItem)newItem("rtlSwhCd_TO");
		rtlSwhNm_TO = (EZDBStringItem)newItem("rtlSwhNm_TO");
		mrpEnblFlg = (EZDBStringItem)newItem("mrpEnblFlg");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		xxPopPrm_EV = (EZDBStringItem)newItem("xxPopPrm_EV");
		P = (business.servlet.NPAL1370.NPAL1370_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1370BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1370BMsgArray();
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
	{"P", "P", null, "10", "business.servlet.NPAL1370.NPAL1370_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_FR
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_FR
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_FR
        {"RTL_SWH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_FR
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_TO
        {"RTL_WH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_TO
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_TO
        {"RTL_SWH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_TO
        {"MRP_ENBL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mrpEnblFlg
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
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

