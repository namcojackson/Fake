//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20171207080623000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL6720F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_PK_C1*/
	public final EZDFBigDecimalItem              ctacPsnPk_C1;

    /** CTAC_TP_DESC_TXT_C1*/
	public final EZDFStringItem              ctacTpDescTxt_C1;

    /** CTAC_PSN_FIRST_NM_C1*/
	public final EZDFStringItem              ctacPsnFirstNm_C1;

    /** CTAC_PSN_LAST_NM_C1*/
	public final EZDFStringItem              ctacPsnLastNm_C1;

    /** DS_CTAC_PSN_DEPT_NM_C1*/
	public final EZDFStringItem              dsCtacPsnDeptNm_C1;

    /** DS_CTAC_PNT_VAL_TXT_C1*/
	public final EZDFStringItem              dsCtacPntValTxt_C1;

    /** DS_CTAC_PNT_VAL_TXT_C2*/
	public final EZDFStringItem              dsCtacPntValTxt_C2;

    /** DS_CTAC_PSN_EXTN_NUM_C1*/
	public final EZDFStringItem              dsCtacPsnExtnNum_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDFDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDFDateItem              effThruDt_C1;

    /** DS_PRIM_LOC_FLG_C1*/
	public final EZDFStringItem              dsPrimLocFlg_C1;

    /** DS_ACCT_STS_NM_C1*/
	public final EZDFStringItem              dsAcctStsNm_C1;


	/**
	 * NMAL6720F00FMsg is constructor.
	 * The initialization when the instance of NMAL6720F00FMsg is generated.
	 */
	public NMAL6720F00FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720F00FMsg is constructor.
	 * The initialization when the instance of NMAL6720F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnPk_C1 = (EZDFBigDecimalItem)newItem("ctacPsnPk_C1");
		ctacTpDescTxt_C1 = (EZDFStringItem)newItem("ctacTpDescTxt_C1");
		ctacPsnFirstNm_C1 = (EZDFStringItem)newItem("ctacPsnFirstNm_C1");
		ctacPsnLastNm_C1 = (EZDFStringItem)newItem("ctacPsnLastNm_C1");
		dsCtacPsnDeptNm_C1 = (EZDFStringItem)newItem("dsCtacPsnDeptNm_C1");
		dsCtacPntValTxt_C1 = (EZDFStringItem)newItem("dsCtacPntValTxt_C1");
		dsCtacPntValTxt_C2 = (EZDFStringItem)newItem("dsCtacPntValTxt_C2");
		dsCtacPsnExtnNum_C1 = (EZDFStringItem)newItem("dsCtacPsnExtnNum_C1");
		effFromDt_C1 = (EZDFDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDFDateItem)newItem("effThruDt_C1");
		dsPrimLocFlg_C1 = (EZDFStringItem)newItem("dsPrimLocFlg_C1");
		dsAcctStsNm_C1 = (EZDFStringItem)newItem("dsAcctStsNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnPk_C1", "ctacPsnPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacTpDescTxt_C1", "ctacTpDescTxt_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"ctacPsnFirstNm_C1", "ctacPsnFirstNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_C1", "ctacPsnLastNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPsnDeptNm_C1", "dsCtacPsnDeptNm_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPntValTxt_C1", "dsCtacPntValTxt_C1", "C1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_C2", "dsCtacPntValTxt_C2", "C2", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum_C1", "dsCtacPsnExtnNum_C1", "C1", null, TYPE_HANKAKUEISU, "10", null},
	{"effFromDt_C1", "effFromDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_C1", "effThruDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsPrimLocFlg_C1", "dsPrimLocFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctStsNm_C1", "dsAcctStsNm_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_C1
        {"CTAC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpDescTxt_C1
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_C1
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_C1
        {"DS_CTAC_PSN_DEPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnDeptNm_C1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_C1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_C2
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum_C1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_C1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_C1
        {"DS_PRIM_LOC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimLocFlg_C1
        {"DS_ACCT_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctStsNm_C1
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
