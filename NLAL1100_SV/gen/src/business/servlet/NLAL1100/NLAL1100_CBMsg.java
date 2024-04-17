//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190514112259000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLAL1100_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLAL1100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL1100 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL1100_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** UPD_USR_ID_C1*/
	public final EZDBStringItem              updUsrId_C1;

    /** XX_SCR_ITEM_130_TXT_C1*/
	public final EZDBStringItem              xxScrItem130Txt_C1;

    /** XX_TS_DSP_19_TXT_C1*/
	public final EZDBStringItem              xxTsDsp19Txt_C1;

    /** RTRN_TRX_CMNT_TXT_C1*/
	public final EZDBStringItem              rtrnTrxCmntTxt_C1;

    /** MAN_CRAT_FLG_C1*/
	public final EZDBStringItem              manCratFlg_C1;


	/**
	 * NLAL1100_CBMsg is constructor.
	 * The initialization when the instance of NLAL1100_CBMsg is generated.
	 */
	public NLAL1100_CBMsg() {
		this(false, -1);
	}

	/**
	 * NLAL1100_CBMsg is constructor.
	 * The initialization when the instance of NLAL1100_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL1100_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		updUsrId_C1 = (EZDBStringItem)newItem("updUsrId_C1");
		xxScrItem130Txt_C1 = (EZDBStringItem)newItem("xxScrItem130Txt_C1");
		xxTsDsp19Txt_C1 = (EZDBStringItem)newItem("xxTsDsp19Txt_C1");
		rtrnTrxCmntTxt_C1 = (EZDBStringItem)newItem("rtrnTrxCmntTxt_C1");
		manCratFlg_C1 = (EZDBStringItem)newItem("manCratFlg_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL1100_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL1100_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"updUsrId_C1", "updUsrId_C1", "C1", null, TYPE_HANKAKUEISU, "16", null},
	{"xxScrItem130Txt_C1", "xxScrItem130Txt_C1", "C1", null, TYPE_HANKAKUEISU, "130", null},
	{"xxTsDsp19Txt_C1", "xxTsDsp19Txt_C1", "C1", null, TYPE_HANKAKUEISU, "19", null},
	{"rtrnTrxCmntTxt_C1", "rtrnTrxCmntTxt_C1", "C1", null, TYPE_HANKAKUEISU, "180", null},
	{"manCratFlg_C1", "manCratFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"UPD_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_C1
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_C1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_C1
        {"RTRN_TRX_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnTrxCmntTxt_C1
        {"MAN_CRAT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manCratFlg_C1
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

