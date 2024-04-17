//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100922170743000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0120BMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class ZZOL0120BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** GLBL_CMPY_CD_BK*/
	public final EZDBStringItem              glblCmpyCd_BK;

    /** GLBL_CMPY_NM*/
	public final EZDBStringItem              glblCmpyNm;

    /** MENU_PROC_GRP_CD*/
	public final EZDBStringItem              menuProcGrpCd;

    /** MENU_PROC_GRP_NM*/
	public final EZDBStringItem              menuProcGrpNm;

    /** MENU_PROC_GRP_SORT_NUM*/
	public final EZDBBigDecimalItem              menuProcGrpSortNum;

    /** MENU_PROC_GRP_DESC_TXT*/
	public final EZDBStringItem              menuProcGrpDescTxt;

    /** WF_APP_NM*/
	public final EZDBStringItem              wfAppNm;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.servlet.ZZOL0120.ZZOL0120_ABMsgArray              A;

    /** MENU_PROC_GRP_CD_B1*/
	public final EZDBStringItem              menuProcGrpCd_B1;

    /** MENU_PROC_GRP_NM_B1*/
	public final EZDBStringItem              menuProcGrpNm_B1;

    /** MENU_PROC_ID_B1*/
	public final EZDBStringItem              menuProcId_B1;

    /** MENU_PROC_NM_B1*/
	public final EZDBStringItem              menuProcNm_B1;

    /** XX_CHK_BOX_B1*/
	public final EZDBStringItem              xxChkBox_B1;

    /** OTH_SYS_URL_B1*/
	public final EZDBStringItem              othSysUrl_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDBStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDBStringItem              ezUpTimeZone_B1;

    /** XX_SORT_TBL_NM_B1*/
	public final EZDBStringItem              xxSortTblNm_B1;

    /** XX_SORT_ITEM_NM_B1*/
	public final EZDBStringItem              xxSortItemNm_B1;

    /** XX_SORT_ORD_BY_TXT_B1*/
	public final EZDBStringItem              xxSortOrdByTxt_B1;

    /** B*/
	public final business.servlet.ZZOL0120.ZZOL0120_BBMsgArray              B;

    /** MENU_PROC_GRP_CD_C1*/
	public final EZDBStringItem              menuProcGrpCd_C1;

    /** MENU_PROC_GRP_NM_C1*/
	public final EZDBStringItem              menuProcGrpNm_C1;

    /** MENU_PROC_ID_C1*/
	public final EZDBStringItem              menuProcId_C1;

    /** MENU_PROC_NM_C1*/
	public final EZDBStringItem              menuProcNm_C1;

    /** UP_TAB_CD_C1*/
	public final EZDBStringItem              upTabCd_C1;

    /** UP_TAB_NM_C1*/
	public final EZDBStringItem              upTabNm_C1;

    /** UP_TAB_SORT_NUM_C1*/
	public final EZDBBigDecimalItem              upTabSortNum_C1;

    /** BIZ_APP_NM_C1*/
	public final EZDBStringItem              bizAppNm_C1;

    /** BIZ_APP_ID_C1*/
	public final EZDBStringItem              bizAppId_C1;

    /** MY_PROC_USBLE_FLG_C1*/
	public final EZDBStringItem              myProcUsbleFlg_C1;

    /** UP_TAB_USBLE_FLG_C1*/
	public final EZDBStringItem              upTabUsbleFlg_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDBStringItem              ezUpTime_C1;

    /** _EZUpTimeZone_C1*/
	public final EZDBStringItem              ezUpTimeZone_C1;

    /** XX_SORT_TBL_NM_C1*/
	public final EZDBStringItem              xxSortTblNm_C1;

    /** XX_SORT_ITEM_NM_C1*/
	public final EZDBStringItem              xxSortItemNm_C1;

    /** XX_SORT_ORD_BY_TXT_C1*/
	public final EZDBStringItem              xxSortOrdByTxt_C1;

    /** C*/
	public final business.servlet.ZZOL0120.ZZOL0120_CBMsgArray              C;


	/**
	 * ZZOL0120BMsg is constructor.
	 * The initialization when the instance of ZZOL0120BMsg is generated.
	 */
	public ZZOL0120BMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0120BMsg is constructor.
	 * The initialization when the instance of ZZOL0120BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0120BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		glblCmpyCd_BK = (EZDBStringItem)newItem("glblCmpyCd_BK");
		glblCmpyNm = (EZDBStringItem)newItem("glblCmpyNm");
		menuProcGrpCd = (EZDBStringItem)newItem("menuProcGrpCd");
		menuProcGrpNm = (EZDBStringItem)newItem("menuProcGrpNm");
		menuProcGrpSortNum = (EZDBBigDecimalItem)newItem("menuProcGrpSortNum");
		menuProcGrpDescTxt = (EZDBStringItem)newItem("menuProcGrpDescTxt");
		wfAppNm = (EZDBStringItem)newItem("wfAppNm");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		A = (business.servlet.ZZOL0120.ZZOL0120_ABMsgArray)newMsgArray("A");
		menuProcGrpCd_B1 = (EZDBStringItem)newItem("menuProcGrpCd_B1");
		menuProcGrpNm_B1 = (EZDBStringItem)newItem("menuProcGrpNm_B1");
		menuProcId_B1 = (EZDBStringItem)newItem("menuProcId_B1");
		menuProcNm_B1 = (EZDBStringItem)newItem("menuProcNm_B1");
		xxChkBox_B1 = (EZDBStringItem)newItem("xxChkBox_B1");
		othSysUrl_B1 = (EZDBStringItem)newItem("othSysUrl_B1");
		ezUpTime_B1 = (EZDBStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDBStringItem)newItem("ezUpTimeZone_B1");
		xxSortTblNm_B1 = (EZDBStringItem)newItem("xxSortTblNm_B1");
		xxSortItemNm_B1 = (EZDBStringItem)newItem("xxSortItemNm_B1");
		xxSortOrdByTxt_B1 = (EZDBStringItem)newItem("xxSortOrdByTxt_B1");
		B = (business.servlet.ZZOL0120.ZZOL0120_BBMsgArray)newMsgArray("B");
		menuProcGrpCd_C1 = (EZDBStringItem)newItem("menuProcGrpCd_C1");
		menuProcGrpNm_C1 = (EZDBStringItem)newItem("menuProcGrpNm_C1");
		menuProcId_C1 = (EZDBStringItem)newItem("menuProcId_C1");
		menuProcNm_C1 = (EZDBStringItem)newItem("menuProcNm_C1");
		upTabCd_C1 = (EZDBStringItem)newItem("upTabCd_C1");
		upTabNm_C1 = (EZDBStringItem)newItem("upTabNm_C1");
		upTabSortNum_C1 = (EZDBBigDecimalItem)newItem("upTabSortNum_C1");
		bizAppNm_C1 = (EZDBStringItem)newItem("bizAppNm_C1");
		bizAppId_C1 = (EZDBStringItem)newItem("bizAppId_C1");
		myProcUsbleFlg_C1 = (EZDBStringItem)newItem("myProcUsbleFlg_C1");
		upTabUsbleFlg_C1 = (EZDBStringItem)newItem("upTabUsbleFlg_C1");
		ezUpTime_C1 = (EZDBStringItem)newItem("ezUpTime_C1");
		ezUpTimeZone_C1 = (EZDBStringItem)newItem("ezUpTimeZone_C1");
		xxSortTblNm_C1 = (EZDBStringItem)newItem("xxSortTblNm_C1");
		xxSortItemNm_C1 = (EZDBStringItem)newItem("xxSortItemNm_C1");
		xxSortOrdByTxt_C1 = (EZDBStringItem)newItem("xxSortOrdByTxt_C1");
		C = (business.servlet.ZZOL0120.ZZOL0120_CBMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0120BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0120BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyCd_BK", "glblCmpyCd_BK", "BK", null, TYPE_HANKAKUEISU, "4", null},
	{"glblCmpyNm", "glblCmpyNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"menuProcGrpCd", "menuProcGrpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"menuProcGrpNm", "menuProcGrpNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"menuProcGrpSortNum", "menuProcGrpSortNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"menuProcGrpDescTxt", "menuProcGrpDescTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"wfAppNm", "wfAppNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "30", "business.servlet.ZZOL0120.ZZOL0120_ABMsgArray", null, null},
	
	{"menuProcGrpCd_B1", "menuProcGrpCd_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"menuProcGrpNm_B1", "menuProcGrpNm_B1", "B1", null, TYPE_HANKAKUEISU, "100", null},
	{"menuProcId_B1", "menuProcId_B1", "B1", null, TYPE_HANKAKUEISU, "18", null},
	{"menuProcNm_B1", "menuProcNm_B1", "B1", null, TYPE_HANKAKUEISU, "100", null},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"othSysUrl_B1", "othSysUrl_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxSortTblNm_B1", "xxSortTblNm_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_B1", "xxSortItemNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_B1", "xxSortOrdByTxt_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"B", "B", null, "30", "business.servlet.ZZOL0120.ZZOL0120_BBMsgArray", null, null},
	
	{"menuProcGrpCd_C1", "menuProcGrpCd_C1", "C1", null, TYPE_HANKAKUEISU, "10", null},
	{"menuProcGrpNm_C1", "menuProcGrpNm_C1", "C1", null, TYPE_HANKAKUEISU, "100", null},
	{"menuProcId_C1", "menuProcId_C1", "C1", null, TYPE_HANKAKUEISU, "18", null},
	{"menuProcNm_C1", "menuProcNm_C1", "C1", null, TYPE_HANKAKUEISU, "100", null},
	{"upTabCd_C1", "upTabCd_C1", "C1", null, TYPE_HANKAKUEISU, "10", null},
	{"upTabNm_C1", "upTabNm_C1", "C1", null, TYPE_HANKAKUEISU, "12", null},
	{"upTabSortNum_C1", "upTabSortNum_C1", "C1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bizAppNm_C1", "bizAppNm_C1", "C1", null, TYPE_HANKAKUEISU, "100", null},
	{"bizAppId_C1", "bizAppId_C1", "C1", null, TYPE_HANKAKUEISU, "8", null},
	{"myProcUsbleFlg_C1", "myProcUsbleFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"upTabUsbleFlg_C1", "upTabUsbleFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_C1", "ezUpTime_C1", "C1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_C1", "ezUpTimeZone_C1", "C1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxSortTblNm_C1", "xxSortTblNm_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_C1", "xxSortItemNm_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_C1", "xxSortOrdByTxt_C1", "C1", null, TYPE_HANKAKUEISU, "4", null},
	{"C", "C", null, "50", "business.servlet.ZZOL0120.ZZOL0120_CBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"GLBL_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"GLBL_CMPY_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_BK
        {"GLBL_CMPY_NM",  NO,  null,null,"0", NO, NO, NO, NO,"15",null,null, null,  NO,  NO},	//glblCmpyNm
        {"MENU_PROC_GRP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpCd
        {"MENU_PROC_GRP_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpNm
        {"MENU_PROC_GRP_SORT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpSortNum
        {"MENU_PROC_GRP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpDescTxt
        {"WF_APP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfAppNm
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"XX_SORT_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"MENU_PROC_GRP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpCd_B1
        {"MENU_PROC_GRP_NM",  NO,  null,null,"0", NO, NO, NO, NO,"30",null,null, null,  NO,  NO},	//menuProcGrpNm_B1
        {"MENU_PROC_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId_B1
        {"MENU_PROC_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcNm_B1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"OTH_SYS_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//othSysUrl_B1
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
        {"XX_SORT_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_B1
        {"XX_SORT_ITEM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_B1
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_B1
		null,	//B
        {"MENU_PROC_GRP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcGrpCd_C1
        {"MENU_PROC_GRP_NM",  NO,  null,null,"0", NO, NO, NO, NO,"30",null,null, null,  NO,  NO},	//menuProcGrpNm_C1
        {"MENU_PROC_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId_C1
        {"MENU_PROC_NM",  NO,  null,null,"0", NO, NO, NO, NO,"30",null,null, null,  NO,  NO},	//menuProcNm_C1
        {"UP_TAB_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabCd_C1
        {"UP_TAB_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabNm_C1
        {"UP_TAB_SORT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabSortNum_C1
        {"BIZ_APP_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppNm_C1
        {"BIZ_APP_ID", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId_C1
        {"MY_PROC_USBLE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//myProcUsbleFlg_C1
        {"UP_TAB_USBLE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upTabUsbleFlg_C1
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C1
        {"XX_SORT_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_C1
        {"XX_SORT_ITEM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_C1
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_C1
		null,	//C
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

