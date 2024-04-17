//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170530084938000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0200_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDMsgArray;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0200 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0200_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** MDSE_STRU_ELMNT_TP_CD_A1*/
	public final EZDBStringItem              mdseStruElmntTpCd_A1;

    /** PROD_CTRL_CD_A1*/
	public final EZDBStringItem              prodCtrlCd_A1;

    /** PROD_CTRL_NM_A1*/
	public final EZDBStringItem              prodCtrlNm_A1;

    /** SCD_PROD_HRCH_CD_A1*/
	public final EZDBStringItem              scdProdHrchCd_A1;

    /** XX_LINK_ANCR_A1*/
	public final EZDBStringItem              xxLinkAncr_A1;

    /** XX_DPLY_BY_CTRL_ITEM_CD_NM_A1*/
	public final EZDBStringItem              xxDplyByCtrlItemCdNm_A1;

    /** XX_BTN_FLG_A1*/
	public final EZDBStringItem              xxBtnFlg_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;

    /** XX_MST_CHK_FLG_A1*/
	public final EZDBStringItem              xxMstChkFlg_A1;


	/**
	 * NMAL0200_ABMsg is constructor.
	 * The initialization when the instance of NMAL0200_ABMsg is generated.
	 */
	public NMAL0200_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0200_ABMsg is constructor.
	 * The initialization when the instance of NMAL0200_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0200_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		mdseStruElmntTpCd_A1 = (EZDBStringItem)newItem("mdseStruElmntTpCd_A1");
		prodCtrlCd_A1 = (EZDBStringItem)newItem("prodCtrlCd_A1");
		prodCtrlNm_A1 = (EZDBStringItem)newItem("prodCtrlNm_A1");
		scdProdHrchCd_A1 = (EZDBStringItem)newItem("scdProdHrchCd_A1");
		xxLinkAncr_A1 = (EZDBStringItem)newItem("xxLinkAncr_A1");
		xxDplyByCtrlItemCdNm_A1 = (EZDBStringItem)newItem("xxDplyByCtrlItemCdNm_A1");
		xxBtnFlg_A1 = (EZDBStringItem)newItem("xxBtnFlg_A1");
		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
		xxMstChkFlg_A1 = (EZDBStringItem)newItem("xxMstChkFlg_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0200_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0200_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseStruElmntTpCd_A1", "mdseStruElmntTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"prodCtrlCd_A1", "prodCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"prodCtrlNm_A1", "prodCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"scdProdHrchCd_A1", "scdProdHrchCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxLinkAncr_A1", "xxLinkAncr_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDplyByCtrlItemCdNm_A1", "xxDplyByCtrlItemCdNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxBtnFlg_A1", "xxBtnFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxMstChkFlg_A1", "xxMstChkFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"MDSE_STRU_ELMNT_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpCd_A1
        {"PROD_CTRL_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prodCtrlCd_A1
        {"PROD_CTRL_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prodCtrlNm_A1
        {"SCD_PROD_HRCH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdHrchCd_A1
        {"XX_LINK_ANCR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_A1
        {"XX_DPLY_BY_CTRL_ITEM_CD_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCtrlItemCdNm_A1
        {"XX_BTN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_MST_CHK_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMstChkFlg_A1
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
