//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180828143538000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_FBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NLEL0060_FBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ASSET_MSTR_PK_F1*/
	public final EZDBBigDecimalItem              dsAssetMstrPk_F1;

    /** PRNT_DS_ASSET_MSTR_PK_F1*/
	public final EZDBBigDecimalItem              prntDsAssetMstrPk_F1;

    /** PROC_MODE_NM_F1*/
	public final EZDBStringItem              procModeNm_F1;

    /** XX_DT_TM_F1*/
	public final EZDBStringItem              xxDtTm_F1;

    /** UPD_FLD_TXT_F1*/
	public final EZDBStringItem              updFldTxt_F1;

    /** OLD_VAL_TXT_F1*/
	public final EZDBStringItem              oldValTxt_F1;

    /** NEW_VAL_TXT_F1*/
	public final EZDBStringItem              newValTxt_F1;

    /** UPD_USR_ID_F1*/
	public final EZDBStringItem              updUsrId_F1;


	/**
	 * NLEL0060_FBMsg is constructor.
	 * The initialization when the instance of NLEL0060_FBMsg is generated.
	 */
	public NLEL0060_FBMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0060_FBMsg is constructor.
	 * The initialization when the instance of NLEL0060_FBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0060_FBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAssetMstrPk_F1 = (EZDBBigDecimalItem)newItem("dsAssetMstrPk_F1");
		prntDsAssetMstrPk_F1 = (EZDBBigDecimalItem)newItem("prntDsAssetMstrPk_F1");
		procModeNm_F1 = (EZDBStringItem)newItem("procModeNm_F1");
		xxDtTm_F1 = (EZDBStringItem)newItem("xxDtTm_F1");
		updFldTxt_F1 = (EZDBStringItem)newItem("updFldTxt_F1");
		oldValTxt_F1 = (EZDBStringItem)newItem("oldValTxt_F1");
		newValTxt_F1 = (EZDBStringItem)newItem("newValTxt_F1");
		updUsrId_F1 = (EZDBStringItem)newItem("updUsrId_F1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0060_FBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0060_FBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAssetMstrPk_F1", "dsAssetMstrPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prntDsAssetMstrPk_F1", "prntDsAssetMstrPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"procModeNm_F1", "procModeNm_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtTm_F1", "xxDtTm_F1", "F1", null, TYPE_HANKAKUEISU, "23", null},
	{"updFldTxt_F1", "updFldTxt_F1", "F1", null, TYPE_HANKAKUEISU, "50", null},
	{"oldValTxt_F1", "oldValTxt_F1", "F1", null, TYPE_HANKAKUEISU, "300", null},
	{"newValTxt_F1", "newValTxt_F1", "F1", null, TYPE_HANKAKUEISU, "300", null},
	{"updUsrId_F1", "updUsrId_F1", "F1", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ASSET_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetMstrPk_F1
        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk_F1
        {"PROC_MODE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procModeNm_F1
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_F1
        {"UPD_FLD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updFldTxt_F1
        {"OLD_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldValTxt_F1
        {"NEW_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newValTxt_F1
        {"UPD_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_F1
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
