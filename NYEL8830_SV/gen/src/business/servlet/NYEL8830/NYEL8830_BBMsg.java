//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180516095534000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8830_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NYEL8830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NYEL8830 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NYEL8830_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_B*/
	public final EZDBBigDecimalItem              xxRowNum_B;

    /** WF_WRK_ITEM_PK_B*/
	public final EZDBBigDecimalItem              wfWrkItemPk_B;

    /** XX_WF_FYI_BTN_VIS_FLG_B*/
	public final EZDBStringItem              xxWfFyiBtnVisFlg_B;

    /** XX_WF_APR_CHK_VIS_FLG_B*/
	public final EZDBStringItem              xxWfAprChkVisFlg_B;

    /** WF_WRK_ITEM_ID_B*/
	public final EZDBStringItem              wfWrkItemId_B;

    /** WF_WRK_ITEM_NM_B*/
	public final EZDBStringItem              wfWrkItemNm_B;

    /** ACT_WF_COND_NM_B*/
	public final EZDBStringItem              actWfCondNm_B;

    /** XX_WF_ACT_OP_NM_B*/
	public final EZDBStringItem              xxWfActOpNm_B;

    /** WF_CMNT_TXT_B*/
	public final EZDBStringItem              wfCmntTxt_B;

    /** XX_SCR_ITEM_130_TXT_B1*/
	public final EZDBStringItem              xxScrItem130Txt_B1;

    /** XX_SCR_ITEM_130_TXT_B2*/
	public final EZDBStringItem              xxScrItem130Txt_B2;

    /** XX_SCR_ITEM_130_TXT_B3*/
	public final EZDBStringItem              xxScrItem130Txt_B3;

    /** XX_SCR_ITEM_130_TXT_B4*/
	public final EZDBStringItem              xxScrItem130Txt_B4;

    /** XX_SCR_ITEM_130_TXT_B5*/
	public final EZDBStringItem              xxScrItem130Txt_B5;

    /** WF_BIZ_ATTRB_TXT_B1*/
	public final EZDBStringItem              wfBizAttrbTxt_B1;

    /** WF_BIZ_ATTRB_TXT_B2*/
	public final EZDBStringItem              wfBizAttrbTxt_B2;

    /** WF_BIZ_ATTRB_TXT_B3*/
	public final EZDBStringItem              wfBizAttrbTxt_B3;

    /** WF_BIZ_ATTRB_TXT_B4*/
	public final EZDBStringItem              wfBizAttrbTxt_B4;

    /** WF_BIZ_ATTRB_TXT_B5*/
	public final EZDBStringItem              wfBizAttrbTxt_B5;

    /** WF_BIZ_ATTRB_LB_TXT_B1*/
	public final EZDBStringItem              wfBizAttrbLbTxt_B1;

    /** WF_BIZ_ATTRB_LB_TXT_B2*/
	public final EZDBStringItem              wfBizAttrbLbTxt_B2;

    /** WF_BIZ_ATTRB_LB_TXT_B3*/
	public final EZDBStringItem              wfBizAttrbLbTxt_B3;

    /** WF_BIZ_ATTRB_LB_TXT_B4*/
	public final EZDBStringItem              wfBizAttrbLbTxt_B4;

    /** WF_BIZ_ATTRB_LB_TXT_B5*/
	public final EZDBStringItem              wfBizAttrbLbTxt_B5;

    /** XX_WF_ASG_FROM_NM_B*/
	public final EZDBStringItem              xxWfAsgFromNm_B;

    /** XX_WF_ASG_TO_NM_B*/
	public final EZDBStringItem              xxWfAsgToNm_B;

    /** XX_DT_TM_BT*/
	public final EZDBStringItem              xxDtTm_BT;

    /** WF_WRK_ITEM_ACT_CNT_B*/
	public final EZDBBigDecimalItem              wfWrkItemActCnt_B;


	/**
	 * NYEL8830_BBMsg is constructor.
	 * The initialization when the instance of NYEL8830_BBMsg is generated.
	 */
	public NYEL8830_BBMsg() {
		this(false, -1);
	}

	/**
	 * NYEL8830_BBMsg is constructor.
	 * The initialization when the instance of NYEL8830_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NYEL8830_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_B = (EZDBBigDecimalItem)newItem("xxRowNum_B");
		wfWrkItemPk_B = (EZDBBigDecimalItem)newItem("wfWrkItemPk_B");
		xxWfFyiBtnVisFlg_B = (EZDBStringItem)newItem("xxWfFyiBtnVisFlg_B");
		xxWfAprChkVisFlg_B = (EZDBStringItem)newItem("xxWfAprChkVisFlg_B");
		wfWrkItemId_B = (EZDBStringItem)newItem("wfWrkItemId_B");
		wfWrkItemNm_B = (EZDBStringItem)newItem("wfWrkItemNm_B");
		actWfCondNm_B = (EZDBStringItem)newItem("actWfCondNm_B");
		xxWfActOpNm_B = (EZDBStringItem)newItem("xxWfActOpNm_B");
		wfCmntTxt_B = (EZDBStringItem)newItem("wfCmntTxt_B");
		xxScrItem130Txt_B1 = (EZDBStringItem)newItem("xxScrItem130Txt_B1");
		xxScrItem130Txt_B2 = (EZDBStringItem)newItem("xxScrItem130Txt_B2");
		xxScrItem130Txt_B3 = (EZDBStringItem)newItem("xxScrItem130Txt_B3");
		xxScrItem130Txt_B4 = (EZDBStringItem)newItem("xxScrItem130Txt_B4");
		xxScrItem130Txt_B5 = (EZDBStringItem)newItem("xxScrItem130Txt_B5");
		wfBizAttrbTxt_B1 = (EZDBStringItem)newItem("wfBizAttrbTxt_B1");
		wfBizAttrbTxt_B2 = (EZDBStringItem)newItem("wfBizAttrbTxt_B2");
		wfBizAttrbTxt_B3 = (EZDBStringItem)newItem("wfBizAttrbTxt_B3");
		wfBizAttrbTxt_B4 = (EZDBStringItem)newItem("wfBizAttrbTxt_B4");
		wfBizAttrbTxt_B5 = (EZDBStringItem)newItem("wfBizAttrbTxt_B5");
		wfBizAttrbLbTxt_B1 = (EZDBStringItem)newItem("wfBizAttrbLbTxt_B1");
		wfBizAttrbLbTxt_B2 = (EZDBStringItem)newItem("wfBizAttrbLbTxt_B2");
		wfBizAttrbLbTxt_B3 = (EZDBStringItem)newItem("wfBizAttrbLbTxt_B3");
		wfBizAttrbLbTxt_B4 = (EZDBStringItem)newItem("wfBizAttrbLbTxt_B4");
		wfBizAttrbLbTxt_B5 = (EZDBStringItem)newItem("wfBizAttrbLbTxt_B5");
		xxWfAsgFromNm_B = (EZDBStringItem)newItem("xxWfAsgFromNm_B");
		xxWfAsgToNm_B = (EZDBStringItem)newItem("xxWfAsgToNm_B");
		xxDtTm_BT = (EZDBStringItem)newItem("xxDtTm_BT");
		wfWrkItemActCnt_B = (EZDBBigDecimalItem)newItem("wfWrkItemActCnt_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NYEL8830_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NYEL8830_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_B", "xxRowNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"wfWrkItemPk_B", "wfWrkItemPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxWfFyiBtnVisFlg_B", "xxWfFyiBtnVisFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxWfAprChkVisFlg_B", "xxWfAprChkVisFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"wfWrkItemId_B", "wfWrkItemId_B", "B", null, TYPE_HANKAKUEISU, "40", null},
	{"wfWrkItemNm_B", "wfWrkItemNm_B", "B", null, TYPE_HANKAKUEISU, "40", null},
	{"actWfCondNm_B", "actWfCondNm_B", "B", null, TYPE_HANKAKUEISU, "40", null},
	{"xxWfActOpNm_B", "xxWfActOpNm_B", "B", null, TYPE_HANKAKUEISU, "1024", null},
	{"wfCmntTxt_B", "wfCmntTxt_B", "B", null, TYPE_HANKAKUEISU, "400", null},
	{"xxScrItem130Txt_B1", "xxScrItem130Txt_B1", "B1", null, TYPE_HANKAKUEISU, "130", null},
	{"xxScrItem130Txt_B2", "xxScrItem130Txt_B2", "B2", null, TYPE_HANKAKUEISU, "130", null},
	{"xxScrItem130Txt_B3", "xxScrItem130Txt_B3", "B3", null, TYPE_HANKAKUEISU, "130", null},
	{"xxScrItem130Txt_B4", "xxScrItem130Txt_B4", "B4", null, TYPE_HANKAKUEISU, "130", null},
	{"xxScrItem130Txt_B5", "xxScrItem130Txt_B5", "B5", null, TYPE_HANKAKUEISU, "130", null},
	{"wfBizAttrbTxt_B1", "wfBizAttrbTxt_B1", "B1", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbTxt_B2", "wfBizAttrbTxt_B2", "B2", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbTxt_B3", "wfBizAttrbTxt_B3", "B3", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbTxt_B4", "wfBizAttrbTxt_B4", "B4", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbTxt_B5", "wfBizAttrbTxt_B5", "B5", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbLbTxt_B1", "wfBizAttrbLbTxt_B1", "B1", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbLbTxt_B2", "wfBizAttrbLbTxt_B2", "B2", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbLbTxt_B3", "wfBizAttrbLbTxt_B3", "B3", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbLbTxt_B4", "wfBizAttrbLbTxt_B4", "B4", null, TYPE_HANKAKUEISU, "40", null},
	{"wfBizAttrbLbTxt_B5", "wfBizAttrbLbTxt_B5", "B5", null, TYPE_HANKAKUEISU, "40", null},
	{"xxWfAsgFromNm_B", "xxWfAsgFromNm_B", "B", null, TYPE_HANKAKUEISU, "255", null},
	{"xxWfAsgToNm_B", "xxWfAsgToNm_B", "B", null, TYPE_HANKAKUEISU, "1024", null},
	{"xxDtTm_BT", "xxDtTm_BT", "BT", null, TYPE_HANKAKUEISU, "23", null},
	{"wfWrkItemActCnt_B", "wfWrkItemActCnt_B", "B", null, TYPE_SEISU_SYOSU, "6", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_B
        {"WF_WRK_ITEM_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemPk_B
        {"XX_WF_FYI_BTN_VIS_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfFyiBtnVisFlg_B
        {"XX_WF_APR_CHK_VIS_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAprChkVisFlg_B
        {"WF_WRK_ITEM_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemId_B
        {"WF_WRK_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemNm_B
        {"ACT_WF_COND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actWfCondNm_B
        {"XX_WF_ACT_OP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfActOpNm_B
        {"WF_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfCmntTxt_B
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_B1
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_B2
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_B3
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_B4
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_B5
        {"WF_BIZ_ATTRB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbTxt_B1
        {"WF_BIZ_ATTRB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbTxt_B2
        {"WF_BIZ_ATTRB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbTxt_B3
        {"WF_BIZ_ATTRB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbTxt_B4
        {"WF_BIZ_ATTRB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbTxt_B5
        {"WF_BIZ_ATTRB_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbLbTxt_B1
        {"WF_BIZ_ATTRB_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbLbTxt_B2
        {"WF_BIZ_ATTRB_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbLbTxt_B3
        {"WF_BIZ_ATTRB_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbLbTxt_B4
        {"WF_BIZ_ATTRB_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfBizAttrbLbTxt_B5
        {"XX_WF_ASG_FROM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgFromNm_B
        {"XX_WF_ASG_TO_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfAsgToNm_B
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_BT
        {"WF_WRK_ITEM_ACT_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfWrkItemActCnt_B
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

