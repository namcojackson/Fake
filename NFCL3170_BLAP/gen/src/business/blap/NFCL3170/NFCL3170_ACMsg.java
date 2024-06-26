//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230209105745000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3170_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3170_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** DS_CUST_BANK_ACCT_RELN_PK_A1*/
	public final EZDCBigDecimalItem              dsCustBankAcctRelnPk_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDCStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** LOC_NUM_A1*/
	public final EZDCStringItem              locNum_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDCDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDCDateItem              effThruDt_A1;

    /** DS_ACCT_CUST_PK_A1*/
	public final EZDCBigDecimalItem              dsAcctCustPk_A1;


	/**
	 * NFCL3170_ACMsg is constructor.
	 * The initialization when the instance of NFCL3170_ACMsg is generated.
	 */
	public NFCL3170_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3170_ACMsg is constructor.
	 * The initialization when the instance of NFCL3170_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3170_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		dsCustBankAcctRelnPk_A1 = (EZDCBigDecimalItem)newItem("dsCustBankAcctRelnPk_A1");
		dsAcctNum_A1 = (EZDCStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		locNum_A1 = (EZDCStringItem)newItem("locNum_A1");
		effFromDt_A1 = (EZDCDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDCDateItem)newItem("effThruDt_A1");
		dsAcctCustPk_A1 = (EZDCBigDecimalItem)newItem("dsAcctCustPk_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3170_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3170_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsCustBankAcctRelnPk_A1", "dsCustBankAcctRelnPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"locNum_A1", "locNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsAcctCustPk_A1", "dsAcctCustPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"DS_CUST_BANK_ACCT_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustBankAcctRelnPk_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
        {"DS_ACCT_CUST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCustPk_A1
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

