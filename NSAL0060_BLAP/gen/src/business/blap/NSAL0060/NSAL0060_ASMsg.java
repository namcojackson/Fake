//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161202132202000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0060_ASMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSAL0060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0060_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_SR*/
	public final EZDSStringItem              xxChkBox_SR;

    /** MDL_GRP_ID_SR*/
	public final EZDSBigDecimalItem              mdlGrpId_SR;

    /** MDL_GRP_NM_SR*/
	public final EZDSStringItem              mdlGrpNm_SR;

    /** MDL_GRP_DESC_TXT_SR*/
	public final EZDSStringItem              mdlGrpDescTxt_SR;

    /** _EZUpdateDateTime_SR*/
	public final EZDSStringItem              ezUpTime_SR;

    /** _EZUpTimeZone_SR*/
	public final EZDSStringItem              ezUpTimeZone_SR;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDSStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDSStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDSStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDSStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDSStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0060_ASMsg is constructor.
	 * The initialization when the instance of NSAL0060_ASMsg is generated.
	 */
	public NSAL0060_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0060_ASMsg is constructor.
	 * The initialization when the instance of NSAL0060_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0060_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_SR = (EZDSStringItem)newItem("xxChkBox_SR");
		mdlGrpId_SR = (EZDSBigDecimalItem)newItem("mdlGrpId_SR");
		mdlGrpNm_SR = (EZDSStringItem)newItem("mdlGrpNm_SR");
		mdlGrpDescTxt_SR = (EZDSStringItem)newItem("mdlGrpDescTxt_SR");
		ezUpTime_SR = (EZDSStringItem)newItem("ezUpTime_SR");
		ezUpTimeZone_SR = (EZDSStringItem)newItem("ezUpTimeZone_SR");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0060_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0060_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_SR", "xxChkBox_SR", "SR", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdlGrpId_SR", "mdlGrpId_SR", "SR", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlGrpNm_SR", "mdlGrpNm_SR", "SR", null, TYPE_HANKAKUEISU, "20", null},
	{"mdlGrpDescTxt_SR", "mdlGrpDescTxt_SR", "SR", null, TYPE_HANKAKUEISU, "90", null},
	{"ezUpTime_SR", "ezUpTime_SR", "SR", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_SR", "ezUpTimeZone_SR", "SR", null, TYPE_HANKAKUEISU, "5", null},
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
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_SR
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_SR
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

