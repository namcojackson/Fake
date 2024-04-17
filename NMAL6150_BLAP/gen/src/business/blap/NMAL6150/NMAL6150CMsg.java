//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160509164823000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6150CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6150CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FIRST_LINE_ADDR_O1*/
	public final EZDCStringItem              firstLineAddr_O1;

    /** SCD_LINE_ADDR_O1*/
	public final EZDCStringItem              scdLineAddr_O1;

    /** THIRD_LINE_ADDR_O1*/
	public final EZDCStringItem              thirdLineAddr_O1;

    /** FRTH_LINE_ADDR_O1*/
	public final EZDCStringItem              frthLineAddr_O1;

    /** CTY_ADDR_O1*/
	public final EZDCStringItem              ctyAddr_O1;

    /** ST_CD_O2*/
	public final EZDCStringItemArray              stCd_O2;

    /** ST_NM_O2*/
	public final EZDCStringItemArray              stNm_O2;

    /** ST_CD_O1*/
	public final EZDCStringItem              stCd_O1;

    /** POST_CD_O1*/
	public final EZDCStringItem              postCd_O1;

    /** CNTY_NM_O1*/
	public final EZDCStringItem              cntyNm_O1;

    /** PROV_NM_O1*/
	public final EZDCStringItem              provNm_O1;

    /** FIRST_LINE_ADDR_S1*/
	public final EZDCStringItem              firstLineAddr_S1;

    /** SCD_LINE_ADDR_S1*/
	public final EZDCStringItem              scdLineAddr_S1;

    /** THIRD_LINE_ADDR_S1*/
	public final EZDCStringItem              thirdLineAddr_S1;

    /** FRTH_LINE_ADDR_S1*/
	public final EZDCStringItem              frthLineAddr_S1;

    /** CTY_ADDR_S1*/
	public final EZDCStringItem              ctyAddr_S1;

    /** ST_CD_S2*/
	public final EZDCStringItemArray              stCd_S2;

    /** ST_NM_S2*/
	public final EZDCStringItemArray              stNm_S2;

    /** ST_CD_S1*/
	public final EZDCStringItem              stCd_S1;

    /** POST_CD_S1*/
	public final EZDCStringItem              postCd_S1;

    /** CNTY_NM_S1*/
	public final EZDCStringItem              cntyNm_S1;

    /** PROV_NM_S1*/
	public final EZDCStringItem              provNm_S1;


	/**
	 * NMAL6150CMsg is constructor.
	 * The initialization when the instance of NMAL6150CMsg is generated.
	 */
	public NMAL6150CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6150CMsg is constructor.
	 * The initialization when the instance of NMAL6150CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6150CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		firstLineAddr_O1 = (EZDCStringItem)newItem("firstLineAddr_O1");
		scdLineAddr_O1 = (EZDCStringItem)newItem("scdLineAddr_O1");
		thirdLineAddr_O1 = (EZDCStringItem)newItem("thirdLineAddr_O1");
		frthLineAddr_O1 = (EZDCStringItem)newItem("frthLineAddr_O1");
		ctyAddr_O1 = (EZDCStringItem)newItem("ctyAddr_O1");
		stCd_O2 = (EZDCStringItemArray)newItemArray("stCd_O2");
		stNm_O2 = (EZDCStringItemArray)newItemArray("stNm_O2");
		stCd_O1 = (EZDCStringItem)newItem("stCd_O1");
		postCd_O1 = (EZDCStringItem)newItem("postCd_O1");
		cntyNm_O1 = (EZDCStringItem)newItem("cntyNm_O1");
		provNm_O1 = (EZDCStringItem)newItem("provNm_O1");
		firstLineAddr_S1 = (EZDCStringItem)newItem("firstLineAddr_S1");
		scdLineAddr_S1 = (EZDCStringItem)newItem("scdLineAddr_S1");
		thirdLineAddr_S1 = (EZDCStringItem)newItem("thirdLineAddr_S1");
		frthLineAddr_S1 = (EZDCStringItem)newItem("frthLineAddr_S1");
		ctyAddr_S1 = (EZDCStringItem)newItem("ctyAddr_S1");
		stCd_S2 = (EZDCStringItemArray)newItemArray("stCd_S2");
		stNm_S2 = (EZDCStringItemArray)newItemArray("stNm_S2");
		stCd_S1 = (EZDCStringItem)newItem("stCd_S1");
		postCd_S1 = (EZDCStringItem)newItem("postCd_S1");
		cntyNm_S1 = (EZDCStringItem)newItem("cntyNm_S1");
		provNm_S1 = (EZDCStringItem)newItem("provNm_S1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6150CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6150CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"firstLineAddr_O1", "firstLineAddr_O1", "O1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_O1", "scdLineAddr_O1", "O1", null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr_O1", "thirdLineAddr_O1", "O1", null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr_O1", "frthLineAddr_O1", "O1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_O1", "ctyAddr_O1", "O1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_O2", "stCd_O2", "O2", "99", TYPE_HANKAKUEISU, "2", null},
	{"stNm_O2", "stNm_O2", "O2", "99", TYPE_HANKAKUEISU, "25", null},
	{"stCd_O1", "stCd_O1", "O1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_O1", "postCd_O1", "O1", null, TYPE_HANKAKUEISU, "15", null},
	{"cntyNm_O1", "cntyNm_O1", "O1", null, TYPE_HANKAKUEISU, "30", null},
	{"provNm_O1", "provNm_O1", "O1", null, TYPE_HANKAKUEISU, "25", null},
	{"firstLineAddr_S1", "firstLineAddr_S1", "S1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_S1", "scdLineAddr_S1", "S1", null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr_S1", "thirdLineAddr_S1", "S1", null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr_S1", "frthLineAddr_S1", "S1", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_S1", "ctyAddr_S1", "S1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_S2", "stCd_S2", "S2", "99", TYPE_HANKAKUEISU, "2", null},
	{"stNm_S2", "stNm_S2", "S2", "99", TYPE_HANKAKUEISU, "25", null},
	{"stCd_S1", "stCd_S1", "S1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_S1", "postCd_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	{"cntyNm_S1", "cntyNm_S1", "S1", null, TYPE_HANKAKUEISU, "30", null},
	{"provNm_S1", "provNm_S1", "S1", null, TYPE_HANKAKUEISU, "25", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_O1
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_O1
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr_O1
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr_O1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_O1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_O2
        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_O2
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_O1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_O1
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_O1
        {"PROV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//provNm_O1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_S1
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_S1
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr_S1
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr_S1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_S1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_S2
        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_S2
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_S1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_S1
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_S1
        {"PROV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//provNm_S1
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
