//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230920092557000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0100_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0100 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0100_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_CUST_REF_NUM_B*/
	public final EZDBStringItem              arCustRefNum_B;

    /** CONSL_BILL_NUM_B*/
	public final EZDBStringItem              conslBillNum_B;

    /** INV_EML_ADDR_B*/
	public final EZDBStringItem              invEmlAddr_B;

    /** XX_FILE_PATH_TXT_B*/
	public final EZDBStringItem              xxFilePathTxt_B;

    /** INV_FILE_URL_B*/
	public final EZDBStringItem              invFileUrl_B;

    /** EIP_RPT_RQST_PK_B*/
	public final EZDBBigDecimalItem              eipRptRqstPk_B;

    /** BILL_TO_DS_ACCT_NUM_B*/
	public final EZDBStringItem              billToDsAcctNum_B;

    /** BILL_TO_DS_ACCT_NM_B*/
	public final EZDBStringItem              billToDsAcctNm_B;


	/**
	 * NFDL0100_BBMsg is constructor.
	 * The initialization when the instance of NFDL0100_BBMsg is generated.
	 */
	public NFDL0100_BBMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0100_BBMsg is constructor.
	 * The initialization when the instance of NFDL0100_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0100_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arCustRefNum_B = (EZDBStringItem)newItem("arCustRefNum_B");
		conslBillNum_B = (EZDBStringItem)newItem("conslBillNum_B");
		invEmlAddr_B = (EZDBStringItem)newItem("invEmlAddr_B");
		xxFilePathTxt_B = (EZDBStringItem)newItem("xxFilePathTxt_B");
		invFileUrl_B = (EZDBStringItem)newItem("invFileUrl_B");
		eipRptRqstPk_B = (EZDBBigDecimalItem)newItem("eipRptRqstPk_B");
		billToDsAcctNum_B = (EZDBStringItem)newItem("billToDsAcctNum_B");
		billToDsAcctNm_B = (EZDBStringItem)newItem("billToDsAcctNm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0100_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0100_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arCustRefNum_B", "arCustRefNum_B", "B", null, TYPE_HANKAKUEISU, "35", null},
	{"conslBillNum_B", "conslBillNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"invEmlAddr_B", "invEmlAddr_B", "B", null, TYPE_HANKAKUEISU, "320", null},
	{"xxFilePathTxt_B", "xxFilePathTxt_B", "B", null, TYPE_HANKAKUEISU, "300", null},
	{"invFileUrl_B", "invFileUrl_B", "B", null, TYPE_HANKAKUEISU, "4000", null},
	{"eipRptRqstPk_B", "eipRptRqstPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"billToDsAcctNum_B", "billToDsAcctNum_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNm_B", "billToDsAcctNm_B", "B", null, TYPE_HANKAKUEISU, "360", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_B
        {"CONSL_BILL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillNum_B
        {"INV_EML_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invEmlAddr_B
        {"XX_FILE_PATH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFilePathTxt_B
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_B
        {"EIP_RPT_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_B
        {"BILL_TO_DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNum_B
        {"BILL_TO_DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNm_B
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
