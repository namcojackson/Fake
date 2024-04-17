//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160808200330000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3120_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3120 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3120_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** DS_BANK_ACCT_NUM_A1*/
	public final EZDSStringItem              dsBankAcctNum_A1;

    /** DS_BANK_ACCT_NM_A1*/
	public final EZDSStringItem              dsBankAcctNm_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDSStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDSStringItem              dsAcctNm_A1;

    /** DS_BANK_CD_A1*/
	public final EZDSStringItem              dsBankCd_A1;

    /** DS_BANK_NM_A1*/
	public final EZDSStringItem              dsBankNm_A1;

    /** BANK_RTE_NUM_A1*/
	public final EZDSStringItem              bankRteNum_A1;

    /** DS_BANK_BR_NM_A1*/
	public final EZDSStringItem              dsBankBrNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDSDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDSDateItem              effThruDt_A1;

    /** XX_NUM_A1*/
	public final EZDSBigDecimalItem              xxNum_A1;

    /** DS_BANK_ACCT_PK_A1*/
	public final EZDSBigDecimalItem              dsBankAcctPk_A1;


	/**
	 * NFCL3120_ASMsg is constructor.
	 * The initialization when the instance of NFCL3120_ASMsg is generated.
	 */
	public NFCL3120_ASMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3120_ASMsg is constructor.
	 * The initialization when the instance of NFCL3120_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3120_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		dsBankAcctNum_A1 = (EZDSStringItem)newItem("dsBankAcctNum_A1");
		dsBankAcctNm_A1 = (EZDSStringItem)newItem("dsBankAcctNm_A1");
		dsAcctNum_A1 = (EZDSStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDSStringItem)newItem("dsAcctNm_A1");
		dsBankCd_A1 = (EZDSStringItem)newItem("dsBankCd_A1");
		dsBankNm_A1 = (EZDSStringItem)newItem("dsBankNm_A1");
		bankRteNum_A1 = (EZDSStringItem)newItem("bankRteNum_A1");
		dsBankBrNm_A1 = (EZDSStringItem)newItem("dsBankBrNm_A1");
		effFromDt_A1 = (EZDSDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDSDateItem)newItem("effThruDt_A1");
		xxNum_A1 = (EZDSBigDecimalItem)newItem("xxNum_A1");
		dsBankAcctPk_A1 = (EZDSBigDecimalItem)newItem("dsBankAcctPk_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3120_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3120_ASMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"DS_BANK_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNum_A1
        {"DS_BANK_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNm_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"DS_BANK_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankCd_A1
        {"DS_BANK_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankNm_A1
        {"BANK_RTE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bankRteNum_A1
        {"DS_BANK_BR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankBrNm_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A1
        {"DS_BANK_ACCT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctPk_A1
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
