//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091105160433000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0110_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0110_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SYS_MENU_GRP_CD_B1*/
	public final EZDBStringItem              sysMenuGrpCd_B1;

    /** SYS_MENU_NM_B1*/
	public final EZDBStringItem              sysMenuNm_B1;

    /** SYS_USBLE_FLG_B1*/
	public final EZDBStringItem              sysUsbleFlg_B1;

    /** BIZ_APP_ID_B1*/
	public final EZDBStringItem              bizAppId_B1;

    /** WF_APP_NM_B1*/
	public final EZDBStringItem              wfAppNm_B1;

    /** RQST_URL_B1*/
	public final EZDBStringItem              rqstUrl_B1;


	/**
	 * ZZOL0110_BBMsg is constructor.
	 * The initialization when the instance of ZZOL0110_BBMsg is generated.
	 */
	public ZZOL0110_BBMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0110_BBMsg is constructor.
	 * The initialization when the instance of ZZOL0110_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0110_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		sysMenuGrpCd_B1 = (EZDBStringItem)newItem("sysMenuGrpCd_B1");
		sysMenuNm_B1 = (EZDBStringItem)newItem("sysMenuNm_B1");
		sysUsbleFlg_B1 = (EZDBStringItem)newItem("sysUsbleFlg_B1");
		bizAppId_B1 = (EZDBStringItem)newItem("bizAppId_B1");
		wfAppNm_B1 = (EZDBStringItem)newItem("wfAppNm_B1");
		rqstUrl_B1 = (EZDBStringItem)newItem("rqstUrl_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0110_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0110_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"sysMenuGrpCd_B1", "sysMenuGrpCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"sysMenuNm_B1", "sysMenuNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"sysUsbleFlg_B1", "sysUsbleFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"bizAppId_B1", "bizAppId_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"wfAppNm_B1", "wfAppNm_B1", "B1", null, TYPE_HANKAKUEISU, "100", null},
	{"rqstUrl_B1", "rqstUrl_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SYS_MENU_GRP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysMenuGrpCd_B1
        {"SYS_MENU_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysMenuNm_B1
        {"SYS_USBLE_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysUsbleFlg_B1
        {"BIZ_APP_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId_B1
        {"WF_APP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfAppNm_B1
        {"RQST_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstUrl_B1
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
