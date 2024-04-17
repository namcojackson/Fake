//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20150327121725000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6810_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6810;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6810 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6810_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CRAT_TMPL_PK_A1*/
	public final EZDBBigDecimalItem              mdseCratTmplPk_A1;

    /** MDSE_ITEM_TP_DESC_TXT_A1*/
	public final EZDBStringItem              mdseItemTpDescTxt_A1;

    /** MDSE_CRAT_TMPL_NM_A1*/
	public final EZDBStringItem              mdseCratTmplNm_A1;

    /** MDSE_ITEM_TP_CD_A1*/
	public final EZDBStringItem              mdseItemTpCd_A1;


	/**
	 * NMAL6810_ABMsg is constructor.
	 * The initialization when the instance of NMAL6810_ABMsg is generated.
	 */
	public NMAL6810_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6810_ABMsg is constructor.
	 * The initialization when the instance of NMAL6810_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6810_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCratTmplPk_A1 = (EZDBBigDecimalItem)newItem("mdseCratTmplPk_A1");
		mdseItemTpDescTxt_A1 = (EZDBStringItem)newItem("mdseItemTpDescTxt_A1");
		mdseCratTmplNm_A1 = (EZDBStringItem)newItem("mdseCratTmplNm_A1");
		mdseItemTpCd_A1 = (EZDBStringItem)newItem("mdseItemTpCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6810_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6810_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCratTmplPk_A1", "mdseCratTmplPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseItemTpDescTxt_A1", "mdseItemTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCratTmplNm_A1", "mdseCratTmplNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseItemTpCd_A1", "mdseItemTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CRAT_TMPL_PK", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCratTmplPk_A1
        {"MDSE_ITEM_TP_DESC_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt_A1
        {"MDSE_CRAT_TMPL_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCratTmplNm_A1
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_A1
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
