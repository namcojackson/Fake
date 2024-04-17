//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100922170743000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0120_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0120 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0120_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B2*/
	public final EZDBStringItem              xxChkBox_B2;

    /** MENU_PROC_ID_B2*/
	public final EZDBStringItem              menuProcId_B2;

    /** MENU_PROC_NM_B2*/
	public final EZDBStringItem              menuProcNm_B2;

    /** OTH_SYS_FLG_B2*/
	public final EZDBStringItem              othSysFlg_B2;

    /** OTH_SYS_URL_B2*/
	public final EZDBStringItem              othSysUrl_B2;

    /** _EZUpdateDateTime_B2*/
	public final EZDBStringItem              ezUpTime_B2;

    /** _EZUpTimeZone_B2*/
	public final EZDBStringItem              ezUpTimeZone_B2;


	/**
	 * ZZOL0120_BBMsg is constructor.
	 * The initialization when the instance of ZZOL0120_BBMsg is generated.
	 */
	public ZZOL0120_BBMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0120_BBMsg is constructor.
	 * The initialization when the instance of ZZOL0120_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0120_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B2 = (EZDBStringItem)newItem("xxChkBox_B2");
		menuProcId_B2 = (EZDBStringItem)newItem("menuProcId_B2");
		menuProcNm_B2 = (EZDBStringItem)newItem("menuProcNm_B2");
		othSysFlg_B2 = (EZDBStringItem)newItem("othSysFlg_B2");
		othSysUrl_B2 = (EZDBStringItem)newItem("othSysUrl_B2");
		ezUpTime_B2 = (EZDBStringItem)newItem("ezUpTime_B2");
		ezUpTimeZone_B2 = (EZDBStringItem)newItem("ezUpTimeZone_B2");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0120_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0120_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B2", "xxChkBox_B2", "B2", null, TYPE_HANKAKUEIJI, "1", null},
	{"menuProcId_B2", "menuProcId_B2", "B2", null, TYPE_HANKAKUEISU, "18", null},
	{"menuProcNm_B2", "menuProcNm_B2", "B2", null, TYPE_HANKAKUEISU, "100", null},
	{"othSysFlg_B2", "othSysFlg_B2", "B2", null, TYPE_HANKAKUEISU, "1", null},
	{"othSysUrl_B2", "othSysUrl_B2", "B2", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTime_B2", "ezUpTime_B2", "B2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B2", "ezUpTimeZone_B2", "B2", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B2
        {"MENU_PROC_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId_B2
        {"MENU_PROC_NM",  NO,  null,null,"0", NO, NO, NO, NO,"30",null,null, null,  NO,  NO},	//menuProcNm_B2
        {"OTH_SYS_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//othSysFlg_B2
        {"OTH_SYS_URL",  NO,  null,null,"0", NO, NO, NO, NO,"50",null,null, null,  NO,  NO},	//othSysUrl_B2
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B2
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B2
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

