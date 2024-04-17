//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160219135054000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7220_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7220;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7220 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7220_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_FMLA_TP_CD_A1*/
	public final EZDCStringItem              prcFmlaTpCd_A1;

    /** PRC_CATG_NM_A1*/
	public final EZDCStringItem              prcCatgNm_A1;

    /** PRC_FMLA_OP_CD_A1*/
	public final EZDCStringItem              prcFmlaOpCd_A1;

    /** PRC_FMLA_NUM_A1*/
	public final EZDCBigDecimalItem              prcFmlaNum_A1;

    /** PRC_FUNC_TP_CD_A1*/
	public final EZDCStringItem              prcFuncTpCd_A1;

    /** PRC_CATG_CD_A1*/
	public final EZDCStringItem              prcCatgCd_A1;


	/**
	 * NMAL7220_ACMsg is constructor.
	 * The initialization when the instance of NMAL7220_ACMsg is generated.
	 */
	public NMAL7220_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7220_ACMsg is constructor.
	 * The initialization when the instance of NMAL7220_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7220_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcFmlaTpCd_A1 = (EZDCStringItem)newItem("prcFmlaTpCd_A1");
		prcCatgNm_A1 = (EZDCStringItem)newItem("prcCatgNm_A1");
		prcFmlaOpCd_A1 = (EZDCStringItem)newItem("prcFmlaOpCd_A1");
		prcFmlaNum_A1 = (EZDCBigDecimalItem)newItem("prcFmlaNum_A1");
		prcFuncTpCd_A1 = (EZDCStringItem)newItem("prcFuncTpCd_A1");
		prcCatgCd_A1 = (EZDCStringItem)newItem("prcCatgCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7220_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7220_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcFmlaTpCd_A1", "prcFmlaTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcCatgNm_A1", "prcCatgNm_A1", "A1", null, TYPE_HANKAKUEISU, "75", null},
	{"prcFmlaOpCd_A1", "prcFmlaOpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcFmlaNum_A1", "prcFmlaNum_A1", "A1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"prcFuncTpCd_A1", "prcFuncTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcCatgCd_A1", "prcCatgCd_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_FMLA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaTpCd_A1
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_A1
        {"PRC_FMLA_OP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaOpCd_A1
        {"PRC_FMLA_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaNum_A1
        {"PRC_FUNC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFuncTpCd_A1
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_A1
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

