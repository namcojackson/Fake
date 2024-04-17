//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20100526173835000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4690_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL4690;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL4690 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL4690_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MSTR_ATT_DATA_PK_A*/
	public final EZDCBigDecimalItem              mstrAttDataPk_A;

    /** MSTR_ATT_DATA_NM_A*/
	public final EZDCStringItem              mstrAttDataNm_A;

    /** MSTR_BIZ_ID_A*/
	public final EZDCStringItem              mstrBizId_A;

    /** MSTR_ACTV_NM_A*/
	public final EZDCStringItem              mstrActvNm_A;

    /** XX_COND_NM_A*/
	public final EZDCStringItem              xxCondNm_A;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** XX_ALL_PTY_ADDR_A*/
	public final EZDCStringItem              xxAllPtyAddr_A;

    /** MSTR_ATT_DATA_VOL_A*/
	public final EZDCBigDecimalItem              mstrAttDataVol_A;

    /** MSTR_ATT_DATA_DESC_TXT_A*/
	public final EZDCStringItem              mstrAttDataDescTxt_A;


	/**
	 * NMAL4690_ACMsg is constructor.
	 * The initialization when the instance of NMAL4690_ACMsg is generated.
	 */
	public NMAL4690_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL4690_ACMsg is constructor.
	 * The initialization when the instance of NMAL4690_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL4690_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mstrAttDataPk_A = (EZDCBigDecimalItem)newItem("mstrAttDataPk_A");
		mstrAttDataNm_A = (EZDCStringItem)newItem("mstrAttDataNm_A");
		mstrBizId_A = (EZDCStringItem)newItem("mstrBizId_A");
		mstrActvNm_A = (EZDCStringItem)newItem("mstrActvNm_A");
		xxCondNm_A = (EZDCStringItem)newItem("xxCondNm_A");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		xxAllPtyAddr_A = (EZDCStringItem)newItem("xxAllPtyAddr_A");
		mstrAttDataVol_A = (EZDCBigDecimalItem)newItem("mstrAttDataVol_A");
		mstrAttDataDescTxt_A = (EZDCStringItem)newItem("mstrAttDataDescTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL4690_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL4690_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mstrAttDataPk_A", "mstrAttDataPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mstrAttDataNm_A", "mstrAttDataNm_A", "A", null, TYPE_HANKAKUEISU, "256", null},
	{"mstrBizId_A", "mstrBizId_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"mstrActvNm_A", "mstrActvNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"xxCondNm_A", "xxCondNm_A", "A", null, TYPE_HANKAKUEISU, "70", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"xxAllPtyAddr_A", "xxAllPtyAddr_A", "A", null, TYPE_HANKAKUEISU, "160", null},
	{"mstrAttDataVol_A", "mstrAttDataVol_A", "A", null, TYPE_SEISU_SYOSU, "20", "0"},
	{"mstrAttDataDescTxt_A", "mstrAttDataDescTxt_A", "A", null, TYPE_HANKAKUEISU, "500", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MSTR_ATT_DATA_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataPk_A
        {"MSTR_ATT_DATA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataNm_A
        {"MSTR_BIZ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrBizId_A
        {"MSTR_ACTV_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrActvNm_A
        {"XX_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondNm_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"XX_ALL_PTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPtyAddr_A
        {"MSTR_ATT_DATA_VOL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataVol_A
        {"MSTR_ATT_DATA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataDescTxt_A
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

