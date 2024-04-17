//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161202132200000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0060_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0060_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_SR*/
	public final EZDCStringItem              xxChkBox_SR;

    /** MDL_GRP_ID_SR*/
	public final EZDCBigDecimalItem              mdlGrpId_SR;

    /** MDL_GRP_NM_SR*/
	public final EZDCStringItem              mdlGrpNm_SR;

    /** MDL_GRP_DESC_TXT_SR*/
	public final EZDCStringItem              mdlGrpDescTxt_SR;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDCStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDCStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDCStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDCStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDCStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0060_ACMsg is constructor.
	 * The initialization when the instance of NSAL0060_ACMsg is generated.
	 */
	public NSAL0060_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0060_ACMsg is constructor.
	 * The initialization when the instance of NSAL0060_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0060_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_SR = (EZDCStringItem)newItem("xxChkBox_SR");
		mdlGrpId_SR = (EZDCBigDecimalItem)newItem("mdlGrpId_SR");
		mdlGrpNm_SR = (EZDCStringItem)newItem("mdlGrpNm_SR");
		mdlGrpDescTxt_SR = (EZDCStringItem)newItem("mdlGrpDescTxt_SR");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0060_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0060_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_SR", "xxChkBox_SR", "SR", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdlGrpId_SR", "mdlGrpId_SR", "SR", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlGrpNm_SR", "mdlGrpNm_SR", "SR", null, TYPE_HANKAKUEISU, "20", null},
	{"mdlGrpDescTxt_SR", "mdlGrpDescTxt_SR", "SR", null, TYPE_HANKAKUEISU, "90", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SR
        {"MDL_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpId_SR
        {"MDL_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpNm_SR
        {"MDL_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpDescTxt_SR
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

