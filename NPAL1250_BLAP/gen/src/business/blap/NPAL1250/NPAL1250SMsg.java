//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160530215810000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1250SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1250;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1250 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1250SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** SELL_TO_CUST_CD*/
	public final EZDSStringItem              sellToCustCd;

    /** DS_ACCT_NM*/
	public final EZDSStringItem              dsAcctNm;

    /** SHIP_TO_CUST_CD*/
	public final EZDSStringItem              shipToCustCd;

    /** LOC_NM*/
	public final EZDSStringItem              locNm;

    /** BIG_DEAL_NUM*/
	public final EZDSStringItem              bigDealNum;

    /** A*/
	public final business.blap.NPAL1250.NPAL1250_ASMsgArray              A;


	/**
	 * NPAL1250SMsg is constructor.
	 * The initialization when the instance of NPAL1250SMsg is generated.
	 */
	public NPAL1250SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1250SMsg is constructor.
	 * The initialization when the instance of NPAL1250SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1250SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		sellToCustCd = (EZDSStringItem)newItem("sellToCustCd");
		dsAcctNm = (EZDSStringItem)newItem("dsAcctNm");
		shipToCustCd = (EZDSStringItem)newItem("shipToCustCd");
		locNm = (EZDSStringItem)newItem("locNm");
		bigDealNum = (EZDSStringItem)newItem("bigDealNum");
		A = (business.blap.NPAL1250.NPAL1250_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1250SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1250SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"locNm", "locNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"bigDealNum", "bigDealNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"A", "A", null, "99", "business.blap.NPAL1250.NPAL1250_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm
        {"BIG_DEAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bigDealNum
		null,	//A
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

