//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180228111329000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6770_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6770 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6770_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** CTAC_PSN_PK_B1*/
	public final EZDCBigDecimalItem              ctacPsnPk_B1;

    /** CTAC_PSN_NUM_B1*/
	public final EZDCStringItem              ctacPsnNum_B1;

    /** DS_ACCT_NM_B1*/
	public final EZDCStringItem              dsAcctNm_B1;

    /** XX_DPLY_BY_ITEM_NM_B1*/
	public final EZDCStringItem              xxDplyByItemNm_B1;

    /** DS_ACCT_NUM_B1*/
	public final EZDCStringItem              dsAcctNum_B1;

    /** LOC_NUM_B1*/
	public final EZDCStringItem              locNum_B1;

    /** DS_LOC_NM_B1*/
	public final EZDCStringItem              dsLocNm_B1;

    /** CTAC_TP_CD_B1*/
	public final EZDCStringItem              ctacTpCd_B1;

    /** CTAC_TP_DESC_TXT_B1*/
	public final EZDCStringItem              ctacTpDescTxt_B1;

    /** CTAC_PSN_FIRST_NM_B1*/
	public final EZDCStringItem              ctacPsnFirstNm_B1;

    /** CTAC_PSN_LAST_NM_B1*/
	public final EZDCStringItem              ctacPsnLastNm_B1;

    /** DS_CTAC_PSN_SALT_NM_B1*/
	public final EZDCStringItem              dsCtacPsnSaltNm_B1;

    /** DS_CTAC_PSN_TTL_CD_B1*/
	public final EZDCStringItem              dsCtacPsnTtlCd_B1;

    /** DS_CTAC_PSN_TTL_NM_B1*/
	public final EZDCStringItem              dsCtacPsnTtlNm_B1;

    /** DS_CTAC_PNT_VAL_TXT_B1*/
	public final EZDCStringItem              dsCtacPntValTxt_B1;

    /** DS_CTAC_PSN_EXTN_NUM_B1*/
	public final EZDCStringItem              dsCtacPsnExtnNum_B1;

    /** DS_CTAC_PNT_VAL_TXT_B2*/
	public final EZDCStringItem              dsCtacPntValTxt_B2;

    /** DS_CTAC_PNT_VAL_TXT_B3*/
	public final EZDCStringItem              dsCtacPntValTxt_B3;

    /** DS_CTAC_PNT_VAL_TXT_B4*/
	public final EZDCStringItem              dsCtacPntValTxt_B4;

    /** DS_CTAC_PNT_VAL_TXT_B5*/
	public final EZDCStringItem              dsCtacPntValTxt_B5;

    /** DS_CTAC_PNT_PK_B1*/
	public final EZDCBigDecimalItem              dsCtacPntPk_B1;

    /** DS_CTAC_PNT_PK_B2*/
	public final EZDCBigDecimalItem              dsCtacPntPk_B2;

    /** DS_CTAC_PNT_PK_B3*/
	public final EZDCBigDecimalItem              dsCtacPntPk_B3;

    /** DS_CTAC_PNT_TP_NM_B1*/
	public final EZDCStringItem              dsCtacPntTpNm_B1;

    /** CTAC_PSN_CMNT_TXT_B1*/
	public final EZDCStringItem              ctacPsnCmntTxt_B1;

    /** BILL_TO_CUST_CD_B1*/
	public final EZDCStringItem              billToCustCd_B1;

    /** SHIP_TO_CUST_CD_B1*/
	public final EZDCStringItem              shipToCustCd_B1;


	/**
	 * NMAL6770_BCMsg is constructor.
	 * The initialization when the instance of NMAL6770_BCMsg is generated.
	 */
	public NMAL6770_BCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6770_BCMsg is constructor.
	 * The initialization when the instance of NMAL6770_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6770_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		ctacPsnPk_B1 = (EZDCBigDecimalItem)newItem("ctacPsnPk_B1");
		ctacPsnNum_B1 = (EZDCStringItem)newItem("ctacPsnNum_B1");
		dsAcctNm_B1 = (EZDCStringItem)newItem("dsAcctNm_B1");
		xxDplyByItemNm_B1 = (EZDCStringItem)newItem("xxDplyByItemNm_B1");
		dsAcctNum_B1 = (EZDCStringItem)newItem("dsAcctNum_B1");
		locNum_B1 = (EZDCStringItem)newItem("locNum_B1");
		dsLocNm_B1 = (EZDCStringItem)newItem("dsLocNm_B1");
		ctacTpCd_B1 = (EZDCStringItem)newItem("ctacTpCd_B1");
		ctacTpDescTxt_B1 = (EZDCStringItem)newItem("ctacTpDescTxt_B1");
		ctacPsnFirstNm_B1 = (EZDCStringItem)newItem("ctacPsnFirstNm_B1");
		ctacPsnLastNm_B1 = (EZDCStringItem)newItem("ctacPsnLastNm_B1");
		dsCtacPsnSaltNm_B1 = (EZDCStringItem)newItem("dsCtacPsnSaltNm_B1");
		dsCtacPsnTtlCd_B1 = (EZDCStringItem)newItem("dsCtacPsnTtlCd_B1");
		dsCtacPsnTtlNm_B1 = (EZDCStringItem)newItem("dsCtacPsnTtlNm_B1");
		dsCtacPntValTxt_B1 = (EZDCStringItem)newItem("dsCtacPntValTxt_B1");
		dsCtacPsnExtnNum_B1 = (EZDCStringItem)newItem("dsCtacPsnExtnNum_B1");
		dsCtacPntValTxt_B2 = (EZDCStringItem)newItem("dsCtacPntValTxt_B2");
		dsCtacPntValTxt_B3 = (EZDCStringItem)newItem("dsCtacPntValTxt_B3");
		dsCtacPntValTxt_B4 = (EZDCStringItem)newItem("dsCtacPntValTxt_B4");
		dsCtacPntValTxt_B5 = (EZDCStringItem)newItem("dsCtacPntValTxt_B5");
		dsCtacPntPk_B1 = (EZDCBigDecimalItem)newItem("dsCtacPntPk_B1");
		dsCtacPntPk_B2 = (EZDCBigDecimalItem)newItem("dsCtacPntPk_B2");
		dsCtacPntPk_B3 = (EZDCBigDecimalItem)newItem("dsCtacPntPk_B3");
		dsCtacPntTpNm_B1 = (EZDCStringItem)newItem("dsCtacPntTpNm_B1");
		ctacPsnCmntTxt_B1 = (EZDCStringItem)newItem("ctacPsnCmntTxt_B1");
		billToCustCd_B1 = (EZDCStringItem)newItem("billToCustCd_B1");
		shipToCustCd_B1 = (EZDCStringItem)newItem("shipToCustCd_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6770_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6770_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"ctacPsnPk_B1", "ctacPsnPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnNum_B1", "ctacPsnNum_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsAcctNm_B1", "dsAcctNm_B1", "B1", null, TYPE_HANKAKUEISU, "360", null},
	{"xxDplyByItemNm_B1", "xxDplyByItemNm_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"dsAcctNum_B1", "dsAcctNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_B1", "locNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsLocNm_B1", "dsLocNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctacTpCd_B1", "ctacTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacTpDescTxt_B1", "ctacTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"ctacPsnFirstNm_B1", "ctacPsnFirstNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_B1", "ctacPsnLastNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPsnSaltNm_B1", "dsCtacPsnSaltNm_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsCtacPsnTtlCd_B1", "dsCtacPsnTtlCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCtacPsnTtlNm_B1", "dsCtacPsnTtlNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"dsCtacPntValTxt_B1", "dsCtacPntValTxt_B1", "B1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum_B1", "dsCtacPsnExtnNum_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsCtacPntValTxt_B2", "dsCtacPntValTxt_B2", "B2", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_B3", "dsCtacPntValTxt_B3", "B3", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_B4", "dsCtacPntValTxt_B4", "B4", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_B5", "dsCtacPntValTxt_B5", "B5", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntPk_B1", "dsCtacPntPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntPk_B2", "dsCtacPntPk_B2", "B2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntPk_B3", "dsCtacPntPk_B3", "B3", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsCtacPntTpNm_B1", "dsCtacPntTpNm_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctacPsnCmntTxt_B1", "ctacPsnCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"billToCustCd_B1", "billToCustCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_B1", "shipToCustCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_B1
        {"CTAC_PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnNum_B1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B1
        {"XX_DPLY_BY_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByItemNm_B1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_B1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_B1
        {"DS_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsLocNm_B1
        {"CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpCd_B1
        {"CTAC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpDescTxt_B1
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_B1
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_B1
        {"DS_CTAC_PSN_SALT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnSaltNm_B1
        {"DS_CTAC_PSN_TTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnTtlCd_B1
        {"DS_CTAC_PSN_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnTtlNm_B1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B1
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum_B1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B2
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B3
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B4
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_B5
        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_B1
        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_B2
        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_B3
        {"DS_CTAC_PNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpNm_B1
        {"CTAC_PSN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnCmntTxt_B1
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_B1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_B1
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

