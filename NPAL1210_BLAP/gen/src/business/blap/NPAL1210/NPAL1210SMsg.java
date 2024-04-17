//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190904155438000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1210SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1210;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1210 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1210SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** APVL_HIST_SRC_TP_DESC_TXT*/
	public final EZDSStringItem              apvlHistSrcTpDescTxt;

    /** TRX_REF_NUM*/
	public final EZDSStringItem              trxRefNum;

    /** A*/
	public final business.blap.NPAL1210.NPAL1210_ASMsgArray              A;

    /** APVL_HIST_SRC_TP_CD*/
	public final EZDSStringItem              apvlHistSrcTpCd;


	/**
	 * NPAL1210SMsg is constructor.
	 * The initialization when the instance of NPAL1210SMsg is generated.
	 */
	public NPAL1210SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1210SMsg is constructor.
	 * The initialization when the instance of NPAL1210SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1210SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apvlHistSrcTpDescTxt = (EZDSStringItem)newItem("apvlHistSrcTpDescTxt");
		trxRefNum = (EZDSStringItem)newItem("trxRefNum");
		A = (business.blap.NPAL1210.NPAL1210_ASMsgArray)newMsgArray("A");
		apvlHistSrcTpCd = (EZDSStringItem)newItem("apvlHistSrcTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1210SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1210SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apvlHistSrcTpDescTxt", "apvlHistSrcTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"trxRefNum", "trxRefNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"A", "A", null, "200", "business.blap.NPAL1210.NPAL1210_ASMsgArray", null, null},
	
	{"apvlHistSrcTpCd", "apvlHistSrcTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"APVL_HIST_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistSrcTpDescTxt
        {"TRX_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRefNum
		null,	//A
        {"APVL_HIST_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistSrcTpCd
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
