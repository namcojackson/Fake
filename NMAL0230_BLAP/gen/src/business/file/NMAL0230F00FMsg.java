//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170530111949000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0230F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL0230F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0230F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_ITEM_19_TXT_A1*/
	public final EZDFStringItem              xxScrItem19Txt_A1;

    /** XX_CUST_RSN_TXT_A1*/
	public final EZDFStringItem              xxCustRsnTxt_A1;

    /** XX_SCR_ITEM_50_TXT_A1*/
	public final EZDFStringItem              xxScrItem50Txt_A1;

    /** XX_DPLY_BY_ITEM_NM_A2*/
	public final EZDFStringItem              xxDplyByItemNm_A2;

    /** XX_SCR_ITEM_61_TXT_A1*/
	public final EZDFStringItem              xxScrItem61Txt_A1;

    /** XX_SCR_ITEM_50_TXT_A3*/
	public final EZDFStringItem              xxScrItem50Txt_A3;

    /** XX_SCR_ITEM_10_TXT_A1*/
	public final EZDFStringItem              xxScrItem10Txt_A1;

    /** XX_SCR_ITEM_19_TXT_A2*/
	public final EZDFStringItem              xxScrItem19Txt_A2;

    /** XX_SCR_ITEM_19_TXT_A3*/
	public final EZDFStringItem              xxScrItem19Txt_A3;

    /** XX_SCR_ITEM_10_TXT_A2*/
	public final EZDFStringItem              xxScrItem10Txt_A2;


	/**
	 * NMAL0230F00FMsg is constructor.
	 * The initialization when the instance of NMAL0230F00FMsg is generated.
	 */
	public NMAL0230F00FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0230F00FMsg is constructor.
	 * The initialization when the instance of NMAL0230F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0230F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrItem19Txt_A1 = (EZDFStringItem)newItem("xxScrItem19Txt_A1");
		xxCustRsnTxt_A1 = (EZDFStringItem)newItem("xxCustRsnTxt_A1");
		xxScrItem50Txt_A1 = (EZDFStringItem)newItem("xxScrItem50Txt_A1");
		xxDplyByItemNm_A2 = (EZDFStringItem)newItem("xxDplyByItemNm_A2");
		xxScrItem61Txt_A1 = (EZDFStringItem)newItem("xxScrItem61Txt_A1");
		xxScrItem50Txt_A3 = (EZDFStringItem)newItem("xxScrItem50Txt_A3");
		xxScrItem10Txt_A1 = (EZDFStringItem)newItem("xxScrItem10Txt_A1");
		xxScrItem19Txt_A2 = (EZDFStringItem)newItem("xxScrItem19Txt_A2");
		xxScrItem19Txt_A3 = (EZDFStringItem)newItem("xxScrItem19Txt_A3");
		xxScrItem10Txt_A2 = (EZDFStringItem)newItem("xxScrItem10Txt_A2");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0230F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0230F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrItem19Txt_A1", "xxScrItem19Txt_A1", "A1", null, TYPE_HANKAKUEISU, "19", null},
	{"xxCustRsnTxt_A1", "xxCustRsnTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxScrItem50Txt_A1", "xxScrItem50Txt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyByItemNm_A2", "xxDplyByItemNm_A2", "A2", null, TYPE_HANKAKUEISU, "250", null},
	{"xxScrItem61Txt_A1", "xxScrItem61Txt_A1", "A1", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem50Txt_A3", "xxScrItem50Txt_A3", "A3", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem10Txt_A1", "xxScrItem10Txt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxScrItem19Txt_A2", "xxScrItem19Txt_A2", "A2", null, TYPE_HANKAKUEISU, "19", null},
	{"xxScrItem19Txt_A3", "xxScrItem19Txt_A3", "A3", null, TYPE_HANKAKUEISU, "19", null},
	{"xxScrItem10Txt_A2", "xxScrItem10Txt_A2", "A2", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_ITEM_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem19Txt_A1
        {"XX_CUST_RSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustRsnTxt_A1
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_A1
        {"XX_DPLY_BY_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByItemNm_A2
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A1
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_A3
        {"XX_SCR_ITEM_10_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem10Txt_A1
        {"XX_SCR_ITEM_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem19Txt_A2
        {"XX_SCR_ITEM_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem19Txt_A3
        {"XX_SCR_ITEM_10_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem10Txt_A2
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

