//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20171026110910000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0430F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSBL0430F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0430F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** SVC_MOD_FROM_SER_NUM*/
	public final EZDFStringItem              svcModFromSerNum;

    /** SVC_MOD_TO_SER_NUM*/
	public final EZDFStringItem              svcModToSerNum;

    /** AUTO_CRAT_CALL_FLG*/
	public final EZDFStringItem              autoCratCallFlg;

    /** AUTO_CRAT_RFRS_TMG_DESC_TXT*/
	public final EZDFStringItem              autoCratRfrsTmgDescTxt;


	/**
	 * NSBL0430F00FMsg is constructor.
	 * The initialization when the instance of NSBL0430F00FMsg is generated.
	 */
	public NSBL0430F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0430F00FMsg is constructor.
	 * The initialization when the instance of NSBL0430F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0430F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDFStringItem)newItem("mdseCd");
		svcModFromSerNum = (EZDFStringItem)newItem("svcModFromSerNum");
		svcModToSerNum = (EZDFStringItem)newItem("svcModToSerNum");
		autoCratCallFlg = (EZDFStringItem)newItem("autoCratCallFlg");
		autoCratRfrsTmgDescTxt = (EZDFStringItem)newItem("autoCratRfrsTmgDescTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0430F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0430F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"svcModFromSerNum", "svcModFromSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcModToSerNum", "svcModToSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"autoCratCallFlg", "autoCratCallFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"autoCratRfrsTmgDescTxt", "autoCratRfrsTmgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SVC_MOD_FROM_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModFromSerNum
        {"SVC_MOD_TO_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModToSerNum
        {"AUTO_CRAT_CALL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoCratCallFlg
        {"AUTO_CRAT_RFRS_TMG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoCratRfrsTmgDescTxt
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

