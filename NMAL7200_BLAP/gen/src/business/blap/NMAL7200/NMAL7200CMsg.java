//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160905111136000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7200CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7200 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7200CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_ITEM_29_TXT*/
	public final EZDCStringItem              xxScrItem29Txt;

    /** PRC_GRP_NM*/
	public final EZDCStringItem              prcGrpNm;

    /** PRC_GRP_DESC_TXT*/
	public final EZDCStringItem              prcGrpDescTxt;

    /** XX_RADIO_BTN*/
	public final EZDCBigDecimalItem              xxRadioBtn;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NMAL7200.NMAL7200_ACMsgArray              A;


	/**
	 * NMAL7200CMsg is constructor.
	 * The initialization when the instance of NMAL7200CMsg is generated.
	 */
	public NMAL7200CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7200CMsg is constructor.
	 * The initialization when the instance of NMAL7200CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7200CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrItem29Txt = (EZDCStringItem)newItem("xxScrItem29Txt");
		prcGrpNm = (EZDCStringItem)newItem("prcGrpNm");
		prcGrpDescTxt = (EZDCStringItem)newItem("prcGrpDescTxt");
		xxRadioBtn = (EZDCBigDecimalItem)newItem("xxRadioBtn");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NMAL7200.NMAL7200_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7200CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7200CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrItem29Txt", "xxScrItem29Txt", null, null, TYPE_HANKAKUEISU, "29", null},
	{"prcGrpNm", "prcGrpNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpDescTxt", "prcGrpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.blap.NMAL7200.NMAL7200_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_ITEM_29_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem29Txt
        {"PRC_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpNm
        {"PRC_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpDescTxt
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

