//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161025104717000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2590_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2590;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2590 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2590_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTY_ADDR_A*/
	public final EZDCStringItem              ctyAddr_A;

    /** ST_CD_A*/
	public final EZDCStringItem              stCd_A;

    /** POST_CD_A*/
	public final EZDCStringItem              postCd_A;

    /** CNTY_NM_A*/
	public final EZDCStringItem              cntyNm_A;


	/**
	 * NMAL2590_ACMsg is constructor.
	 * The initialization when the instance of NMAL2590_ACMsg is generated.
	 */
	public NMAL2590_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2590_ACMsg is constructor.
	 * The initialization when the instance of NMAL2590_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2590_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctyAddr_A = (EZDCStringItem)newItem("ctyAddr_A");
		stCd_A = (EZDCStringItem)newItem("stCd_A");
		postCd_A = (EZDCStringItem)newItem("postCd_A");
		cntyNm_A = (EZDCStringItem)newItem("cntyNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2590_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2590_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctyAddr_A", "ctyAddr_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_A", "stCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_A", "postCd_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"cntyNm_A", "cntyNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_A
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_A
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

