//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230427160329000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1700_FBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1700 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1700_FBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_CATG_CTX_TP_CD_F*/
	public final EZDBStringItem              ordCatgCtxTpCd_F;

    /** DS_ORD_CATG_DESC_TXT_F*/
	public final EZDBStringItem              dsOrdCatgDescTxt_F;

    /** DS_ORD_TP_DESC_TXT_F*/
	public final EZDBStringItem              dsOrdTpDescTxt_F;


	/**
	 * NWAL1700_FBMsg is constructor.
	 * The initialization when the instance of NWAL1700_FBMsg is generated.
	 */
	public NWAL1700_FBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1700_FBMsg is constructor.
	 * The initialization when the instance of NWAL1700_FBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1700_FBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordCatgCtxTpCd_F = (EZDBStringItem)newItem("ordCatgCtxTpCd_F");
		dsOrdCatgDescTxt_F = (EZDBStringItem)newItem("dsOrdCatgDescTxt_F");
		dsOrdTpDescTxt_F = (EZDBStringItem)newItem("dsOrdTpDescTxt_F");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1700_FBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1700_FBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordCatgCtxTpCd_F", "ordCatgCtxTpCd_F", "F", null, TYPE_HANKAKUEISU, "30", null},
	{"dsOrdCatgDescTxt_F", "dsOrdCatgDescTxt_F", "F", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt_F", "dsOrdTpDescTxt_F", "F", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_CATG_CTX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordCatgCtxTpCd_F
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_F
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_F
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

