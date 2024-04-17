//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160305152923000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1760_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1760;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1760 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1760_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_LIST_TP_CD_A*/
	public final EZDCStringItem              prcListTpCd_A;

    /** PRC_LIST_TP_DESC_TXT_A*/
	public final EZDCStringItem              prcListTpDescTxt_A;

    /** PRC_CATG_CD_A*/
	public final EZDCStringItem              prcCatgCd_A;

    /** PRC_CATG_NM_A*/
	public final EZDCStringItem              prcCatgNm_A;

    /** PRC_LIST_TP_NM_A*/
	public final EZDCStringItem              prcListTpNm_A;


	/**
	 * NWAL1760_ACMsg is constructor.
	 * The initialization when the instance of NWAL1760_ACMsg is generated.
	 */
	public NWAL1760_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1760_ACMsg is constructor.
	 * The initialization when the instance of NWAL1760_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1760_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcListTpCd_A = (EZDCStringItem)newItem("prcListTpCd_A");
		prcListTpDescTxt_A = (EZDCStringItem)newItem("prcListTpDescTxt_A");
		prcCatgCd_A = (EZDCStringItem)newItem("prcCatgCd_A");
		prcCatgNm_A = (EZDCStringItem)newItem("prcCatgNm_A");
		prcListTpNm_A = (EZDCStringItem)newItem("prcListTpNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1760_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1760_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcListTpCd_A", "prcListTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_A", "prcListTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcCatgCd_A", "prcCatgCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_A", "prcCatgNm_A", "A", null, TYPE_HANKAKUEISU, "75", null},
	{"prcListTpNm_A", "prcListTpNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_A
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_A
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_A
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_A
        {"PRC_LIST_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpNm_A
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
