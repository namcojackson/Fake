//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170531165735000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLBL0040F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WH_CD*/
	public final EZDFStringItem              whCd;

    /** ST_CD*/
	public final EZDFStringItem              stCd;

    /** SHPG_MODE_CD*/
	public final EZDFStringItem              shpgModeCd;

    /** MOVE_EFF_FROM_DT_TXT*/
	public final EZDFStringItem              moveEffFromDtTxt;

    /** MOVE_EFF_THRU_DT_TXT*/
	public final EZDFStringItem              moveEffThruDtTxt;

    /** DELY_LEAD_AOT*/
	public final EZDFBigDecimalItem              delyLeadAot;

    /** FROM_ZIP_CD*/
	public final EZDFStringItem              fromZipCd;

    /** TO_ZIP_CD*/
	public final EZDFStringItem              toZipCd;

    /** TRNSP_LT_AOT*/
	public final EZDFBigDecimalItem              trnspLtAot;


	/**
	 * NLBL0040F00FMsg is constructor.
	 * The initialization when the instance of NLBL0040F00FMsg is generated.
	 */
	public NLBL0040F00FMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040F00FMsg is constructor.
	 * The initialization when the instance of NLBL0040F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		whCd = (EZDFStringItem)newItem("whCd");
		stCd = (EZDFStringItem)newItem("stCd");
		shpgModeCd = (EZDFStringItem)newItem("shpgModeCd");
		moveEffFromDtTxt = (EZDFStringItem)newItem("moveEffFromDtTxt");
		moveEffThruDtTxt = (EZDFStringItem)newItem("moveEffThruDtTxt");
		delyLeadAot = (EZDFBigDecimalItem)newItem("delyLeadAot");
		fromZipCd = (EZDFStringItem)newItem("fromZipCd");
		toZipCd = (EZDFStringItem)newItem("toZipCd");
		trnspLtAot = (EZDFBigDecimalItem)newItem("trnspLtAot");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"whCd", "whCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"shpgModeCd", "shpgModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"moveEffFromDtTxt", "moveEffFromDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"moveEffThruDtTxt", "moveEffThruDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"delyLeadAot", "delyLeadAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"fromZipCd", "fromZipCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"toZipCd", "toZipCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"trnspLtAot", "trnspLtAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"SHPG_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd
        {"MOVE_EFF_FROM_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//moveEffFromDtTxt
        {"MOVE_EFF_THRU_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//moveEffThruDtTxt
        {"DELY_LEAD_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyLeadAot
        {"FROM_ZIP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromZipCd
        {"TO_ZIP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toZipCd
        {"TRNSP_LT_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trnspLtAot
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
