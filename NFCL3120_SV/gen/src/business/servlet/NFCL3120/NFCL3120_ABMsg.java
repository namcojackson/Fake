//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160808200231000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3120_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL3120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3120 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3120_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** DS_BANK_ACCT_NUM_A1*/
	public final EZDBStringItem              dsBankAcctNum_A1;

    /** DS_BANK_ACCT_NM_A1*/
	public final EZDBStringItem              dsBankAcctNm_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDBStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** DS_BANK_CD_A1*/
	public final EZDBStringItem              dsBankCd_A1;

    /** DS_BANK_NM_A1*/
	public final EZDBStringItem              dsBankNm_A1;

    /** BANK_RTE_NUM_A1*/
	public final EZDBStringItem              bankRteNum_A1;

    /** DS_BANK_BR_NM_A1*/
	public final EZDBStringItem              dsBankBrNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** XX_NUM_A1*/
	public final EZDBBigDecimalItem              xxNum_A1;

    /** DS_BANK_ACCT_PK_A1*/
	public final EZDBBigDecimalItem              dsBankAcctPk_A1;


	/**
	 * NFCL3120_ABMsg is constructor.
	 * The initialization when the instance of NFCL3120_ABMsg is generated.
	 */
	public NFCL3120_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3120_ABMsg is constructor.
	 * The initialization when the instance of NFCL3120_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3120_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		dsBankAcctNum_A1 = (EZDBStringItem)newItem("dsBankAcctNum_A1");
		dsBankAcctNm_A1 = (EZDBStringItem)newItem("dsBankAcctNm_A1");
		dsAcctNum_A1 = (EZDBStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		dsBankCd_A1 = (EZDBStringItem)newItem("dsBankCd_A1");
		dsBankNm_A1 = (EZDBStringItem)newItem("dsBankNm_A1");
		bankRteNum_A1 = (EZDBStringItem)newItem("bankRteNum_A1");
		dsBankBrNm_A1 = (EZDBStringItem)newItem("dsBankBrNm_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		xxNum_A1 = (EZDBBigDecimalItem)newItem("xxNum_A1");
		dsBankAcctPk_A1 = (EZDBBigDecimalItem)newItem("dsBankAcctPk_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3120_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3120_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsBankAcctNum_A1", "dsBankAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "14", null},
	{"dsBankAcctNm_A1", "dsBankAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "80", null},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"dsBankCd_A1", "dsBankCd_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsBankNm_A1", "dsBankNm_A1", "A1", null, TYPE_HANKAKUEISU, "70", null},
	{"bankRteNum_A1", "bankRteNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsBankBrNm_A1", "dsBankBrNm_A1", "A1", null, TYPE_HANKAKUEISU, "80", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxNum_A1", "xxNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"dsBankAcctPk_A1", "dsBankAcctPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"DS_BANK_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNum_A1
        {"DS_BANK_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNm_A1
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"DS_BANK_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankCd_A1
        {"DS_BANK_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankNm_A1
        {"BANK_RTE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bankRteNum_A1
        {"DS_BANK_BR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankBrNm_A1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A1
        {"DS_BANK_ACCT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctPk_A1
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

