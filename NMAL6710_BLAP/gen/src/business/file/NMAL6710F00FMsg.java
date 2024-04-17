//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160104175907000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6710F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6710F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6710F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_A*/
	public final EZDFStringItem              dsAcctNum_A;

    /** DS_ACCT_RELN_TP_NM_A*/
	public final EZDFStringItem              dsAcctRelnTpNm_A;

    /** DS_ACCT_NM_A*/
	public final EZDFStringItem              dsAcctNm_A;

    /** XX_ALL_LINE_ADDR_A*/
	public final EZDFStringItem              xxAllLineAddr_A;

    /** LOC_NUM_A*/
	public final EZDFStringItem              locNum_A;

    /** XX_YES_NO_CD_A*/
	public final EZDFStringItem              xxYesNoCd_A;

    /** DS_ACCT_TP_NM_A*/
	public final EZDFStringItem              dsAcctTpNm_A;

    /** XX_CTL_NM_A*/
	public final EZDFStringItem              xxCtlNm_A;

    /** RELN_DS_ACCT_NUM_A*/
	public final EZDFStringItem              relnDsAcctNum_A;

    /** DS_ACCT_NUM_A2*/
	public final EZDFStringItem              dsAcctNum_A2;

    /** BILL_TO_CUST_CD_A*/
	public final EZDFStringItem              billToCustCd_A;

    /** SHIP_TO_CUST_CD_A*/
	public final EZDFStringItem              shipToCustCd_A;


	/**
	 * NMAL6710F00FMsg is constructor.
	 * The initialization when the instance of NMAL6710F00FMsg is generated.
	 */
	public NMAL6710F00FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6710F00FMsg is constructor.
	 * The initialization when the instance of NMAL6710F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6710F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_A = (EZDFStringItem)newItem("dsAcctNum_A");
		dsAcctRelnTpNm_A = (EZDFStringItem)newItem("dsAcctRelnTpNm_A");
		dsAcctNm_A = (EZDFStringItem)newItem("dsAcctNm_A");
		xxAllLineAddr_A = (EZDFStringItem)newItem("xxAllLineAddr_A");
		locNum_A = (EZDFStringItem)newItem("locNum_A");
		xxYesNoCd_A = (EZDFStringItem)newItem("xxYesNoCd_A");
		dsAcctTpNm_A = (EZDFStringItem)newItem("dsAcctTpNm_A");
		xxCtlNm_A = (EZDFStringItem)newItem("xxCtlNm_A");
		relnDsAcctNum_A = (EZDFStringItem)newItem("relnDsAcctNum_A");
		dsAcctNum_A2 = (EZDFStringItem)newItem("dsAcctNum_A2");
		billToCustCd_A = (EZDFStringItem)newItem("billToCustCd_A");
		shipToCustCd_A = (EZDFStringItem)newItem("shipToCustCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6710F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6710F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctRelnTpNm_A", "dsAcctRelnTpNm_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A", "dsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_A", "xxAllLineAddr_A", "A", null, TYPE_HANKAKUEISU, "400", null},
	{"locNum_A", "locNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxYesNoCd_A", "xxYesNoCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctTpNm_A", "dsAcctTpNm_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"xxCtlNm_A", "xxCtlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"relnDsAcctNum_A", "relnDsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_A2", "dsAcctNum_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_A", "shipToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"DS_ACCT_RELN_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnTpNm_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_A
        {"DS_ACCT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpNm_A
        {"XX_CTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCtlNm_A
        {"RELN_DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnDsAcctNum_A
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A2
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A
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
