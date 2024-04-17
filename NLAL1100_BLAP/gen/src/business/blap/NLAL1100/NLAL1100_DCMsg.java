//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190514112626000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL1100_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL1100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL1100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL1100_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** UPD_USR_ID_D1*/
	public final EZDCStringItem              updUsrId_D1;

    /** XX_SCR_ITEM_130_TXT_D1*/
	public final EZDCStringItem              xxScrItem130Txt_D1;

    /** XX_TS_DSP_19_TXT_D1*/
	public final EZDCStringItem              xxTsDsp19Txt_D1;

    /** RTRN_TRX_CMNT_TXT_D1*/
	public final EZDCStringItem              rtrnTrxCmntTxt_D1;

    /** MAN_CRAT_FLG_D1*/
	public final EZDCStringItem              manCratFlg_D1;


	/**
	 * NLAL1100_DCMsg is constructor.
	 * The initialization when the instance of NLAL1100_DCMsg is generated.
	 */
	public NLAL1100_DCMsg() {
		this(false, -1);
	}

	/**
	 * NLAL1100_DCMsg is constructor.
	 * The initialization when the instance of NLAL1100_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL1100_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		updUsrId_D1 = (EZDCStringItem)newItem("updUsrId_D1");
		xxScrItem130Txt_D1 = (EZDCStringItem)newItem("xxScrItem130Txt_D1");
		xxTsDsp19Txt_D1 = (EZDCStringItem)newItem("xxTsDsp19Txt_D1");
		rtrnTrxCmntTxt_D1 = (EZDCStringItem)newItem("rtrnTrxCmntTxt_D1");
		manCratFlg_D1 = (EZDCStringItem)newItem("manCratFlg_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL1100_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL1100_DCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"updUsrId_D1", "updUsrId_D1", "D1", null, TYPE_HANKAKUEISU, "16", null},
	{"xxScrItem130Txt_D1", "xxScrItem130Txt_D1", "D1", null, TYPE_HANKAKUEISU, "130", null},
	{"xxTsDsp19Txt_D1", "xxTsDsp19Txt_D1", "D1", null, TYPE_HANKAKUEISU, "19", null},
	{"rtrnTrxCmntTxt_D1", "rtrnTrxCmntTxt_D1", "D1", null, TYPE_HANKAKUEISU, "180", null},
	{"manCratFlg_D1", "manCratFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"UPD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_D1
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_D1
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_D1
        {"RTRN_TRX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnTrxCmntTxt_D1
        {"MAN_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manCratFlg_D1
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

