//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151223195014000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7110_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7110_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CONTR_PK_A*/
	public final EZDBBigDecimalItem              prcContrPk_A;

    /** PRC_CONTR_NM_A*/
	public final EZDBStringItem              prcContrNm_A;

    /** PRC_CONTR_NUM_A*/
	public final EZDBStringItem              prcContrNum_A;

    /** ASSN_PGM_CONTR_FLG_A*/
	public final EZDBStringItem              assnPgmContrFlg_A;

    /** ACTV_FLG_A*/
	public final EZDBStringItem              actvFlg_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;


	/**
	 * NMAL7110_ABMsg is constructor.
	 * The initialization when the instance of NMAL7110_ABMsg is generated.
	 */
	public NMAL7110_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7110_ABMsg is constructor.
	 * The initialization when the instance of NMAL7110_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7110_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcContrPk_A = (EZDBBigDecimalItem)newItem("prcContrPk_A");
		prcContrNm_A = (EZDBStringItem)newItem("prcContrNm_A");
		prcContrNum_A = (EZDBStringItem)newItem("prcContrNum_A");
		assnPgmContrFlg_A = (EZDBStringItem)newItem("assnPgmContrFlg_A");
		actvFlg_A = (EZDBStringItem)newItem("actvFlg_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7110_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7110_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcContrPk_A", "prcContrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcContrNm_A", "prcContrNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"prcContrNum_A", "prcContrNum_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"assnPgmContrFlg_A", "assnPgmContrFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CONTR_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrPk_A
        {"PRC_CONTR_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNm_A
        {"PRC_CONTR_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum_A
        {"ASSN_PGM_CONTR_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assnPgmContrFlg_A
        {"ACTV_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
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

