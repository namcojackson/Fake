//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160118105733000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3060CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3060CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** AR_TRX_NUM*/
	public final EZDCStringItem              arTrxNum;

    /** PMT_TERM_CASH_DISC_CD*/
	public final EZDCStringItem              pmtTermCashDiscCd;

    /** PMT_TERM_CASH_DISC_DESC_TXT*/
	public final EZDCStringItem              pmtTermCashDiscDescTxt;

    /** XX_DPLY_BY_ITEM_NM*/
	public final EZDCStringItem              xxDplyByItemNm;

    /** A*/
	public final business.blap.NFCL3060.NFCL3060_ACMsgArray              A;


	/**
	 * NFCL3060CMsg is constructor.
	 * The initialization when the instance of NFCL3060CMsg is generated.
	 */
	public NFCL3060CMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3060CMsg is constructor.
	 * The initialization when the instance of NFCL3060CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3060CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		arTrxNum = (EZDCStringItem)newItem("arTrxNum");
		pmtTermCashDiscCd = (EZDCStringItem)newItem("pmtTermCashDiscCd");
		pmtTermCashDiscDescTxt = (EZDCStringItem)newItem("pmtTermCashDiscDescTxt");
		xxDplyByItemNm = (EZDCStringItem)newItem("xxDplyByItemNm");
		A = (business.blap.NFCL3060.NFCL3060_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3060CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3060CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"arTrxNum", "arTrxNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"pmtTermCashDiscCd", "pmtTermCashDiscCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"pmtTermCashDiscDescTxt", "pmtTermCashDiscDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByItemNm", "xxDplyByItemNm", null, null, TYPE_HANKAKUEISU, "250", null},
	{"A", "A", null, "200", "business.blap.NFCL3060.NFCL3060_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd
        {"PMT_TERM_CASH_DISC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscDescTxt
        {"XX_DPLY_BY_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByItemNm
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
