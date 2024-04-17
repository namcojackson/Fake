//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160512105114000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMA7020001FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMA7020001 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMA7020001FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CATG_NM*/
	public final EZDFStringItem              prcCatgNm;

    /** DS_ACCT_NUM*/
	public final EZDFStringItem              dsAcctNum;

    /** DS_ACCT_NM*/
	public final EZDFStringItem              dsAcctNm;

    /** REQ_FLG*/
	public final EZDFStringItem              reqFlg;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;

    /** DEF_PRC_LIST_FLG*/
	public final EZDFStringItem              defPrcListFlg;


	/**
	 * NMA7020001FMsg is constructor.
	 * The initialization when the instance of NMA7020001FMsg is generated.
	 */
	public NMA7020001FMsg() {
		this(false, -1);
	}

	/**
	 * NMA7020001FMsg is constructor.
	 * The initialization when the instance of NMA7020001FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMA7020001FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCatgNm = (EZDFStringItem)newItem("prcCatgNm");
		dsAcctNum = (EZDFStringItem)newItem("dsAcctNum");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		reqFlg = (EZDFStringItem)newItem("reqFlg");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
		defPrcListFlg = (EZDFStringItem)newItem("defPrcListFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NMA7020001FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMA7020001FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"reqFlg", "reqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"defPrcListFlg", "defPrcListFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//reqFlg
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"DEF_PRC_LIST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defPrcListFlg
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

