//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180828143538000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0060_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRNT_DS_ASSET_MSTR_PK_E1*/
	public final EZDBBigDecimalItem              prntDsAssetMstrPk_E1;

    /** ASSET_TP_NM_E1*/
	public final EZDBStringItem              assetTpNm_E1;

    /** ASSET_TRX_DT_E1*/
	public final EZDBDateItem              assetTrxDt_E1;

    /** DEPC_VAL_AMT_E1*/
	public final EZDBBigDecimalItem              depcValAmt_E1;

    /** DR_COA_ACCT_CD_E1*/
	public final EZDBStringItem              drCoaAcctCd_E1;

    /** DEPC_VAL_AMT_E2*/
	public final EZDBBigDecimalItem              depcValAmt_E2;

    /** CR_COA_ACCT_CD_E1*/
	public final EZDBStringItem              crCoaAcctCd_E1;


	/**
	 * NLEL0060_EBMsg is constructor.
	 * The initialization when the instance of NLEL0060_EBMsg is generated.
	 */
	public NLEL0060_EBMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0060_EBMsg is constructor.
	 * The initialization when the instance of NLEL0060_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0060_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prntDsAssetMstrPk_E1 = (EZDBBigDecimalItem)newItem("prntDsAssetMstrPk_E1");
		assetTpNm_E1 = (EZDBStringItem)newItem("assetTpNm_E1");
		assetTrxDt_E1 = (EZDBDateItem)newItem("assetTrxDt_E1");
		depcValAmt_E1 = (EZDBBigDecimalItem)newItem("depcValAmt_E1");
		drCoaAcctCd_E1 = (EZDBStringItem)newItem("drCoaAcctCd_E1");
		depcValAmt_E2 = (EZDBBigDecimalItem)newItem("depcValAmt_E2");
		crCoaAcctCd_E1 = (EZDBStringItem)newItem("crCoaAcctCd_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0060_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0060_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prntDsAssetMstrPk_E1", "prntDsAssetMstrPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"assetTpNm_E1", "assetTpNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"assetTrxDt_E1", "assetTrxDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"depcValAmt_E1", "depcValAmt_E1", "E1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"drCoaAcctCd_E1", "drCoaAcctCd_E1", "E1", null, TYPE_HANKAKUEISU, "8", null},
	{"depcValAmt_E2", "depcValAmt_E2", "E2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crCoaAcctCd_E1", "crCoaAcctCd_E1", "E1", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk_E1
        {"ASSET_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpNm_E1
        {"ASSET_TRX_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//assetTrxDt_E1
        {"DEPC_VAL_AMT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//depcValAmt_E1
        {"DR_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//drCoaAcctCd_E1
        {"DEPC_VAL_AMT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//depcValAmt_E2
        {"CR_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCoaAcctCd_E1
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

