//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170802125203000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6790_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6790;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6790 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6790_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_PK_A1*/
	public final EZDCBigDecimalItem              ctacPsnPk_A1;

    /** CTAC_PSN_NUM_A1*/
	public final EZDCStringItem              ctacPsnNum_A1;

    /** CTAC_PSN_LAST_NM_A1*/
	public final EZDCStringItem              ctacPsnLastNm_A1;

    /** CTAC_PSN_FIRST_NM_A1*/
	public final EZDCStringItem              ctacPsnFirstNm_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDCStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** LOC_NUM_A1*/
	public final EZDCStringItem              locNum_A1;

    /** XX_DPLY_BY_ITEM_NM_A1*/
	public final EZDCStringItem              xxDplyByItemNm_A1;

    /** DS_LOC_NM_A1*/
	public final EZDCStringItem              dsLocNm_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDCStringItem              xxChkBox_A2;

    /** CTAC_TP_CD_A1*/
	public final EZDCStringItem              ctacTpCd_A1;

    /** CTAC_TP_NM_A1*/
	public final EZDCStringItem              ctacTpNm_A1;

    /** DS_CTAC_PSN_DEPT_CD_A1*/
	public final EZDCStringItem              dsCtacPsnDeptCd_A1;

    /** DS_CTAC_PSN_DEPT_NM_A1*/
	public final EZDCStringItem              dsCtacPsnDeptNm_A1;

    /** DS_CTAC_PSN_TTL_CD_A1*/
	public final EZDCStringItem              dsCtacPsnTtlCd_A1;

    /** DS_CTAC_PSN_TTL_NM_A1*/
	public final EZDCStringItem              dsCtacPsnTtlNm_A1;

    /** SER_NUM_A1*/
	public final EZDCStringItem              serNum_A1;

    /** DS_PRIM_CTAC_TP_CD_A1*/
	public final EZDCStringItem              dsPrimCtacTpCd_A1;

    /** DS_CTAC_PNT_TP_NM_A1*/
	public final EZDCStringItem              dsCtacPntTpNm_A1;

    /** CTAC_PSN_CMNT_TXT_A1*/
	public final EZDCStringItem              ctacPsnCmntTxt_A1;

    /** DS_PRIM_LOC_FLG_A1*/
	public final EZDCStringItem              dsPrimLocFlg_A1;

    /** XX_DPLY_CTRL_FLG_A1*/
	public final EZDCStringItem              xxDplyCtrlFlg_A1;


	/**
	 * NMAL6790_ACMsg is constructor.
	 * The initialization when the instance of NMAL6790_ACMsg is generated.
	 */
	public NMAL6790_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6790_ACMsg is constructor.
	 * The initialization when the instance of NMAL6790_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6790_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnPk_A1 = (EZDCBigDecimalItem)newItem("ctacPsnPk_A1");
		ctacPsnNum_A1 = (EZDCStringItem)newItem("ctacPsnNum_A1");
		ctacPsnLastNm_A1 = (EZDCStringItem)newItem("ctacPsnLastNm_A1");
		ctacPsnFirstNm_A1 = (EZDCStringItem)newItem("ctacPsnFirstNm_A1");
		dsAcctNum_A1 = (EZDCStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		locNum_A1 = (EZDCStringItem)newItem("locNum_A1");
		xxDplyByItemNm_A1 = (EZDCStringItem)newItem("xxDplyByItemNm_A1");
		dsLocNm_A1 = (EZDCStringItem)newItem("dsLocNm_A1");
		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		xxChkBox_A2 = (EZDCStringItem)newItem("xxChkBox_A2");
		ctacTpCd_A1 = (EZDCStringItem)newItem("ctacTpCd_A1");
		ctacTpNm_A1 = (EZDCStringItem)newItem("ctacTpNm_A1");
		dsCtacPsnDeptCd_A1 = (EZDCStringItem)newItem("dsCtacPsnDeptCd_A1");
		dsCtacPsnDeptNm_A1 = (EZDCStringItem)newItem("dsCtacPsnDeptNm_A1");
		dsCtacPsnTtlCd_A1 = (EZDCStringItem)newItem("dsCtacPsnTtlCd_A1");
		dsCtacPsnTtlNm_A1 = (EZDCStringItem)newItem("dsCtacPsnTtlNm_A1");
		serNum_A1 = (EZDCStringItem)newItem("serNum_A1");
		dsPrimCtacTpCd_A1 = (EZDCStringItem)newItem("dsPrimCtacTpCd_A1");
		dsCtacPntTpNm_A1 = (EZDCStringItem)newItem("dsCtacPntTpNm_A1");
		ctacPsnCmntTxt_A1 = (EZDCStringItem)newItem("ctacPsnCmntTxt_A1");
		dsPrimLocFlg_A1 = (EZDCStringItem)newItem("dsPrimLocFlg_A1");
		xxDplyCtrlFlg_A1 = (EZDCStringItem)newItem("xxDplyCtrlFlg_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6790_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6790_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnPk_A1", "ctacPsnPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnNum_A1", "ctacPsnNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"ctacPsnLastNm_A1", "ctacPsnLastNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnFirstNm_A1", "ctacPsnFirstNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"locNum_A1", "locNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDplyByItemNm_A1", "xxDplyByItemNm_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"dsLocNm_A1", "dsLocNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"ctacTpCd_A1", "ctacTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacTpNm_A1", "ctacTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPsnDeptCd_A1", "dsCtacPsnDeptCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"dsCtacPsnDeptNm_A1", "dsCtacPsnDeptNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPsnTtlCd_A1", "dsCtacPsnTtlCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCtacPsnTtlNm_A1", "dsCtacPsnTtlNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsPrimCtacTpCd_A1", "dsPrimCtacTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCtacPntTpNm_A1", "dsCtacPntTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctacPsnCmntTxt_A1", "ctacPsnCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"dsPrimLocFlg_A1", "dsPrimLocFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A1", "xxDplyCtrlFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_A1
        {"CTAC_PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnNum_A1
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_A1
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A1
        {"XX_DPLY_BY_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByItemNm_A1
        {"DS_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocNm_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpCd_A1
        {"CTAC_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpNm_A1
        {"DS_CTAC_PSN_DEPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnDeptCd_A1
        {"DS_CTAC_PSN_DEPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnDeptNm_A1
        {"DS_CTAC_PSN_TTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnTtlCd_A1
        {"DS_CTAC_PSN_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnTtlNm_A1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"DS_PRIM_CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimCtacTpCd_A1
        {"DS_CTAC_PNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpNm_A1
        {"CTAC_PSN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnCmntTxt_A1
        {"DS_PRIM_LOC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimLocFlg_A1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A1
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
