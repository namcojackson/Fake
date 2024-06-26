//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151201131501000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6870_HBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6870;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6870 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6870_HBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COND_LB_TXT_H*/
	public final EZDBStringItem              xxComnScrCondLbTxt_H;

    /** XX_COMN_SCR_COND_VAL_TXT_H*/
	public final EZDBStringItem              xxComnScrCondValTxt_H;

    /** XX_COMN_SCR_QUERY_FLTR_TXT_H*/
	public final EZDBStringItem              xxComnScrQueryFltrTxt_H;

    /** XX_COMN_SCR_QUERY_LIKE_FLG_H*/
	public final EZDBStringItem              xxComnScrQueryLikeFlg_H;


	/**
	 * NMAL6870_HBMsg is constructor.
	 * The initialization when the instance of NMAL6870_HBMsg is generated.
	 */
	public NMAL6870_HBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6870_HBMsg is constructor.
	 * The initialization when the instance of NMAL6870_HBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6870_HBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrCondLbTxt_H = (EZDBStringItem)newItem("xxComnScrCondLbTxt_H");
		xxComnScrCondValTxt_H = (EZDBStringItem)newItem("xxComnScrCondValTxt_H");
		xxComnScrQueryFltrTxt_H = (EZDBStringItem)newItem("xxComnScrQueryFltrTxt_H");
		xxComnScrQueryLikeFlg_H = (EZDBStringItem)newItem("xxComnScrQueryLikeFlg_H");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6870_HBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6870_HBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrCondLbTxt_H", "xxComnScrCondLbTxt_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"xxComnScrCondValTxt_H", "xxComnScrCondValTxt_H", "H", null, TYPE_HANKAKUEISU, "60", null},
	{"xxComnScrQueryFltrTxt_H", "xxComnScrQueryFltrTxt_H", "H", null, TYPE_HANKAKUEISU, "200", null},
	{"xxComnScrQueryLikeFlg_H", "xxComnScrQueryLikeFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COND_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrCondLbTxt_H
        {"XX_COMN_SCR_COND_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrCondValTxt_H
        {"XX_COMN_SCR_QUERY_FLTR_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryFltrTxt_H
        {"XX_COMN_SCR_QUERY_LIKE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrQueryLikeFlg_H
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

