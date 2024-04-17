//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160125111909000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7210F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL7210F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7210F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_FMLA_NM_A1*/
	public final EZDFStringItem              prcFmlaNm_A1;

    /** PRC_FMLA_DESC_TXT_A1*/
	public final EZDFStringItem              prcFmlaDescTxt_A1;

    /** PRC_FMLA_TP_NM_A1*/
	public final EZDFStringItem              prcFmlaTpNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDFDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDFDateItem              effThruDt_A1;


	/**
	 * NMAL7210F00FMsg is constructor.
	 * The initialization when the instance of NMAL7210F00FMsg is generated.
	 */
	public NMAL7210F00FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7210F00FMsg is constructor.
	 * The initialization when the instance of NMAL7210F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7210F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcFmlaNm_A1 = (EZDFStringItem)newItem("prcFmlaNm_A1");
		prcFmlaDescTxt_A1 = (EZDFStringItem)newItem("prcFmlaDescTxt_A1");
		prcFmlaTpNm_A1 = (EZDFStringItem)newItem("prcFmlaTpNm_A1");
		effFromDt_A1 = (EZDFDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDFDateItem)newItem("effThruDt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7210F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7210F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcFmlaNm_A1", "prcFmlaNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcFmlaDescTxt_A1", "prcFmlaDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcFmlaTpNm_A1", "prcFmlaTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_FMLA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaNm_A1
        {"PRC_FMLA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaDescTxt_A1
        {"PRC_FMLA_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaTpNm_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
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

