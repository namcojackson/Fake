//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180727133558000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3300_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL3300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3300 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3300_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** HRCH_EFF_LVL_TP_CD_B1*/
	public final EZDBStringItem              hrchEffLvlTpCd_B1;

    /** HRCH_EFF_LVL_TP_NM_B1*/
	public final EZDBStringItem              hrchEffLvlTpNm_B1;

    /** LOC_NUM_B1*/
	public final EZDBStringItem              locNum_B1;

    /** DS_ACCT_REF_ATTRB_NUM_B1*/
	public final EZDBStringItem              dsAcctRefAttrbNum_B1;

    /** BLLG_ATTRB_NM_B1*/
	public final EZDBStringItem              bllgAttrbNm_B1;

    /** BLLG_ATTRB_VAL_TXT_B1*/
	public final EZDBStringItem              bllgAttrbValTxt_B1;

    /** BLLG_ATTRB_ENBL_FLG_B1*/
	public final EZDBStringItem              bllgAttrbEnblFlg_B1;

    /** BLLG_ATTRB_REQ_FLG_B1*/
	public final EZDBStringItem              bllgAttrbReqFlg_B1;


	/**
	 * NMAL3300_BBMsg is constructor.
	 * The initialization when the instance of NMAL3300_BBMsg is generated.
	 */
	public NMAL3300_BBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3300_BBMsg is constructor.
	 * The initialization when the instance of NMAL3300_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3300_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		hrchEffLvlTpCd_B1 = (EZDBStringItem)newItem("hrchEffLvlTpCd_B1");
		hrchEffLvlTpNm_B1 = (EZDBStringItem)newItem("hrchEffLvlTpNm_B1");
		locNum_B1 = (EZDBStringItem)newItem("locNum_B1");
		dsAcctRefAttrbNum_B1 = (EZDBStringItem)newItem("dsAcctRefAttrbNum_B1");
		bllgAttrbNm_B1 = (EZDBStringItem)newItem("bllgAttrbNm_B1");
		bllgAttrbValTxt_B1 = (EZDBStringItem)newItem("bllgAttrbValTxt_B1");
		bllgAttrbEnblFlg_B1 = (EZDBStringItem)newItem("bllgAttrbEnblFlg_B1");
		bllgAttrbReqFlg_B1 = (EZDBStringItem)newItem("bllgAttrbReqFlg_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3300_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3300_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"hrchEffLvlTpCd_B1", "hrchEffLvlTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"hrchEffLvlTpNm_B1", "hrchEffLvlTpNm_B1", "B1", null, TYPE_HANKAKUEISU, "40", null},
	{"locNum_B1", "locNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctRefAttrbNum_B1", "dsAcctRefAttrbNum_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgAttrbNm_B1", "bllgAttrbNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbValTxt_B1", "bllgAttrbValTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbEnblFlg_B1", "bllgAttrbEnblFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgAttrbReqFlg_B1", "bllgAttrbReqFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"HRCH_EFF_LVL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrchEffLvlTpCd_B1
        {"HRCH_EFF_LVL_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrchEffLvlTpNm_B1
        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_B1
        {"DS_ACCT_REF_ATTRB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRefAttrbNum_B1
        {"BLLG_ATTRB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbNm_B1
        {"BLLG_ATTRB_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbValTxt_B1
        {"BLLG_ATTRB_ENBL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbEnblFlg_B1
        {"BLLG_ATTRB_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbReqFlg_B1
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

