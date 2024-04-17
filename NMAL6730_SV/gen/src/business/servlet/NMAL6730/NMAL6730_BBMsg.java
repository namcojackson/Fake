//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20150415112301000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6730_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6730 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6730_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_REF_ATTRB_PK_B1*/
	public final EZDBBigDecimalItem              dsAcctRefAttrbPk_B1;

    /** XX_CTL_NM_B1*/
	public final EZDBStringItem              xxCtlNm_B1;

    /** BLLG_ATTRB_NM_B1*/
	public final EZDBStringItem              bllgAttrbNm_B1;

    /** BLLG_ATTRB_VAL_TXT_B1*/
	public final EZDBStringItem              bllgAttrbValTxt_B1;

    /** BLLG_ATTRB_ENBL_FLG_B1*/
	public final EZDBStringItem              bllgAttrbEnblFlg_B1;

    /** BLLG_ATTRB_REQ_FLG_B1*/
	public final EZDBStringItem              bllgAttrbReqFlg_B1;


	/**
	 * NMAL6730_BBMsg is constructor.
	 * The initialization when the instance of NMAL6730_BBMsg is generated.
	 */
	public NMAL6730_BBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6730_BBMsg is constructor.
	 * The initialization when the instance of NMAL6730_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6730_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctRefAttrbPk_B1 = (EZDBBigDecimalItem)newItem("dsAcctRefAttrbPk_B1");
		xxCtlNm_B1 = (EZDBStringItem)newItem("xxCtlNm_B1");
		bllgAttrbNm_B1 = (EZDBStringItem)newItem("bllgAttrbNm_B1");
		bllgAttrbValTxt_B1 = (EZDBStringItem)newItem("bllgAttrbValTxt_B1");
		bllgAttrbEnblFlg_B1 = (EZDBStringItem)newItem("bllgAttrbEnblFlg_B1");
		bllgAttrbReqFlg_B1 = (EZDBStringItem)newItem("bllgAttrbReqFlg_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6730_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6730_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctRefAttrbPk_B1", "dsAcctRefAttrbPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxCtlNm_B1", "xxCtlNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbNm_B1", "bllgAttrbNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbValTxt_B1", "bllgAttrbValTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgAttrbEnblFlg_B1", "bllgAttrbEnblFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgAttrbReqFlg_B1", "bllgAttrbReqFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_REF_ATTRB_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRefAttrbPk_B1
        {"XX_CTL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCtlNm_B1
        {"BLLG_ATTRB_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbNm_B1
        {"BLLG_ATTRB_VAL_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgAttrbValTxt_B1
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
