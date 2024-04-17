//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20150311152609000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6040_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6040_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ZEROTH_PROD_CTRL_CD_A1*/
	public final EZDCStringItem              zerothProdCtrlCd_A1;

    /** ZEROTH_PROD_CTRL_NM_A1*/
	public final EZDCStringItem              zerothProdCtrlNm_A1;

    /** FIRST_PROD_CTRL_CD_A1*/
	public final EZDCStringItem              firstProdCtrlCd_A1;

    /** FIRST_PROD_CTRL_NM_A1*/
	public final EZDCStringItem              firstProdCtrlNm_A1;

    /** SCD_PROD_CTRL_CD_A1*/
	public final EZDCStringItem              scdProdCtrlCd_A1;

    /** SCD_PROD_CTRL_NM_A1*/
	public final EZDCStringItem              scdProdCtrlNm_A1;

    /** THIRD_PROD_CTRL_CD_A1*/
	public final EZDCStringItem              thirdProdCtrlCd_A1;

    /** THIRD_PROD_CTRL_NM_A1*/
	public final EZDCStringItem              thirdProdCtrlNm_A1;

    /** FRTH_PROD_CTRL_CD_A1*/
	public final EZDCStringItem              frthProdCtrlCd_A1;

    /** FRTH_PROD_CTRL_NM_A1*/
	public final EZDCStringItem              frthProdCtrlNm_A1;

    /** FIFTH_PROD_CTRL_CD_A1*/
	public final EZDCStringItem              fifthProdCtrlCd_A1;

    /** FIFTH_PROD_CTRL_NM_A1*/
	public final EZDCStringItem              fifthProdCtrlNm_A1;

    /** MDSE_CD_A1*/
	public final EZDCStringItem              mdseCd_A1;

    /** MDSE_NM_A1*/
	public final EZDCStringItem              mdseNm_A1;

    /** UPC_CD_A1*/
	public final EZDCStringItem              upcCd_A1;

    /** MDSE_CATG_NM_A1*/
	public final EZDCStringItem              mdseCatgNm_A1;


	/**
	 * NMAL6040_ACMsg is constructor.
	 * The initialization when the instance of NMAL6040_ACMsg is generated.
	 */
	public NMAL6040_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6040_ACMsg is constructor.
	 * The initialization when the instance of NMAL6040_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6040_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		zerothProdCtrlCd_A1 = (EZDCStringItem)newItem("zerothProdCtrlCd_A1");
		zerothProdCtrlNm_A1 = (EZDCStringItem)newItem("zerothProdCtrlNm_A1");
		firstProdCtrlCd_A1 = (EZDCStringItem)newItem("firstProdCtrlCd_A1");
		firstProdCtrlNm_A1 = (EZDCStringItem)newItem("firstProdCtrlNm_A1");
		scdProdCtrlCd_A1 = (EZDCStringItem)newItem("scdProdCtrlCd_A1");
		scdProdCtrlNm_A1 = (EZDCStringItem)newItem("scdProdCtrlNm_A1");
		thirdProdCtrlCd_A1 = (EZDCStringItem)newItem("thirdProdCtrlCd_A1");
		thirdProdCtrlNm_A1 = (EZDCStringItem)newItem("thirdProdCtrlNm_A1");
		frthProdCtrlCd_A1 = (EZDCStringItem)newItem("frthProdCtrlCd_A1");
		frthProdCtrlNm_A1 = (EZDCStringItem)newItem("frthProdCtrlNm_A1");
		fifthProdCtrlCd_A1 = (EZDCStringItem)newItem("fifthProdCtrlCd_A1");
		fifthProdCtrlNm_A1 = (EZDCStringItem)newItem("fifthProdCtrlNm_A1");
		mdseCd_A1 = (EZDCStringItem)newItem("mdseCd_A1");
		mdseNm_A1 = (EZDCStringItem)newItem("mdseNm_A1");
		upcCd_A1 = (EZDCStringItem)newItem("upcCd_A1");
		mdseCatgNm_A1 = (EZDCStringItem)newItem("mdseCatgNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6040_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6040_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"zerothProdCtrlCd_A1", "zerothProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"zerothProdCtrlNm_A1", "zerothProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"firstProdCtrlCd_A1", "firstProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"firstProdCtrlNm_A1", "firstProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"scdProdCtrlCd_A1", "scdProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"scdProdCtrlNm_A1", "scdProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"thirdProdCtrlCd_A1", "thirdProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"thirdProdCtrlNm_A1", "thirdProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"frthProdCtrlCd_A1", "frthProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"frthProdCtrlNm_A1", "frthProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"fifthProdCtrlCd_A1", "fifthProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"fifthProdCtrlNm_A1", "fifthProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_A1", "mdseNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"upcCd_A1", "upcCd_A1", "A1", null, TYPE_HANKAKUEISU, "12", null},
	{"mdseCatgNm_A1", "mdseCatgNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ZEROTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlCd_A1
        {"ZEROTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlNm_A1
        {"FIRST_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlCd_A1
        {"FIRST_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlNm_A1
        {"SCD_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlCd_A1
        {"SCD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlNm_A1
        {"THIRD_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlCd_A1
        {"THIRD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlNm_A1
        {"FRTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlCd_A1
        {"FRTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlNm_A1
        {"FIFTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthProdCtrlCd_A1
        {"FIFTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthProdCtrlNm_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_A1
        {"UPC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upcCd_A1
        {"MDSE_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCatgNm_A1
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
