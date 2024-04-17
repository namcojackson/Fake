//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160309142629000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2680_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2680;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2680 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2680_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_RULE_PK_A1*/
	public final EZDSBigDecimalItem              trtyRulePk_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDSStringItem              dsAcctNum_A1;

    /** LOC_NUM_A1*/
	public final EZDSStringItem              locNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDSStringItem              dsAcctNm_A1;

    /** DS_ACCT_TP_CD_A1*/
	public final EZDSStringItem              dsAcctTpCd_A1;

    /** DS_ACCT_TP_NM_A1*/
	public final EZDSStringItem              dsAcctTpNm_A1;

    /** XX_ALL_LINE_ADDR_A1*/
	public final EZDSStringItem              xxAllLineAddr_A1;


	/**
	 * NMAL2680_ASMsg is constructor.
	 * The initialization when the instance of NMAL2680_ASMsg is generated.
	 */
	public NMAL2680_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2680_ASMsg is constructor.
	 * The initialization when the instance of NMAL2680_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2680_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyRulePk_A1 = (EZDSBigDecimalItem)newItem("trtyRulePk_A1");
		dsAcctNum_A1 = (EZDSStringItem)newItem("dsAcctNum_A1");
		locNum_A1 = (EZDSStringItem)newItem("locNum_A1");
		dsAcctNm_A1 = (EZDSStringItem)newItem("dsAcctNm_A1");
		dsAcctTpCd_A1 = (EZDSStringItem)newItem("dsAcctTpCd_A1");
		dsAcctTpNm_A1 = (EZDSStringItem)newItem("dsAcctTpNm_A1");
		xxAllLineAddr_A1 = (EZDSStringItem)newItem("xxAllLineAddr_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2680_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2680_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyRulePk_A1", "trtyRulePk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_A1", "locNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctTpCd_A1", "dsAcctTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctTpNm_A1", "dsAcctTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxAllLineAddr_A1", "xxAllLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "400", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_RULE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRulePk_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"DS_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpCd_A1
        {"DS_ACCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_A1
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A1
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
