//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160125110155000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7210_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7210;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7210 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7210_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_FMLA_NM_A1*/
	public final EZDBStringItem              prcFmlaNm_A1;

    /** PRC_FMLA_DESC_TXT_A1*/
	public final EZDBStringItem              prcFmlaDescTxt_A1;

    /** PRC_FMLA_TP_NM_A1*/
	public final EZDBStringItem              prcFmlaTpNm_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** PRC_FMLA_PK_A1*/
	public final EZDBBigDecimalItem              prcFmlaPk_A1;


	/**
	 * NMAL7210_ABMsg is constructor.
	 * The initialization when the instance of NMAL7210_ABMsg is generated.
	 */
	public NMAL7210_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7210_ABMsg is constructor.
	 * The initialization when the instance of NMAL7210_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7210_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcFmlaNm_A1 = (EZDBStringItem)newItem("prcFmlaNm_A1");
		prcFmlaDescTxt_A1 = (EZDBStringItem)newItem("prcFmlaDescTxt_A1");
		prcFmlaTpNm_A1 = (EZDBStringItem)newItem("prcFmlaTpNm_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		prcFmlaPk_A1 = (EZDBBigDecimalItem)newItem("prcFmlaPk_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7210_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7210_ABMsgArray();
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
	{"prcFmlaPk_A1", "prcFmlaPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_FMLA_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaNm_A1
        {"PRC_FMLA_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaDescTxt_A1
        {"PRC_FMLA_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaTpNm_A1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"PRC_FMLA_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaPk_A1
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
