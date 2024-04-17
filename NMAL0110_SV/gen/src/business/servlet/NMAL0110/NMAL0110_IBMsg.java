//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231219170400000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0110_IBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0110_IBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_ITEM_RELN_TP_CD_I1*/
	public final EZDBStringItem              mdseItemRelnTpCd_I1;

    /** RELN_MDSE_CD_IB*/
	public final EZDBStringItem              relnMdseCd_IB;

    /** RELN_MDSE_CD_I1*/
	public final EZDBStringItem              relnMdseCd_I1;

    /** MDSE_DESC_SHORT_TXT_I1*/
	public final EZDBStringItem              mdseDescShortTxt_I1;

    /** _EZUpdateDateTime_I1*/
	public final EZDBStringItem              ezUpTime_I1;

    /** _EZUpTimeZone_I1*/
	public final EZDBStringItem              ezUpTimeZone_I1;


	/**
	 * NMAL0110_IBMsg is constructor.
	 * The initialization when the instance of NMAL0110_IBMsg is generated.
	 */
	public NMAL0110_IBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0110_IBMsg is constructor.
	 * The initialization when the instance of NMAL0110_IBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0110_IBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseItemRelnTpCd_I1 = (EZDBStringItem)newItem("mdseItemRelnTpCd_I1");
		relnMdseCd_IB = (EZDBStringItem)newItem("relnMdseCd_IB");
		relnMdseCd_I1 = (EZDBStringItem)newItem("relnMdseCd_I1");
		mdseDescShortTxt_I1 = (EZDBStringItem)newItem("mdseDescShortTxt_I1");
		ezUpTime_I1 = (EZDBStringItem)newItem("ezUpTime_I1");
		ezUpTimeZone_I1 = (EZDBStringItem)newItem("ezUpTimeZone_I1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0110_IBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0110_IBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseItemRelnTpCd_I1", "mdseItemRelnTpCd_I1", "I1", null, TYPE_HANKAKUEISU, "2", null},
	{"relnMdseCd_IB", "relnMdseCd_IB", "IB", null, TYPE_HANKAKUEISU, "16", null},
	{"relnMdseCd_I1", "relnMdseCd_I1", "I1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_I1", "mdseDescShortTxt_I1", "I1", null, TYPE_HANKAKUEISU, "250", null},
	{"ezUpTime_I1", "ezUpTime_I1", "I1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_I1", "ezUpTimeZone_I1", "I1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_ITEM_RELN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemRelnTpCd_I1
        {"RELN_MDSE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnMdseCd_IB
        {"RELN_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnMdseCd_I1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_I1
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_I1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_I1
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

