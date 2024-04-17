//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170419140123000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0420_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0420;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0420 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0420_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_RG_PK_A*/
	public final EZDBBigDecimalItem              svcRgPk_A;

    /** PSN_CD_A*/
	public final EZDBStringItem              psnCd_A;

    /** SVC_RG_NM_A*/
	public final EZDBStringItem              svcRgNm_A;

    /** LINE_BIZ_TP_DESC_TXT_A*/
	public final EZDBStringItem              lineBizTpDescTxt_A;

    /** LINE_BIZ_TP_CD_A*/
	public final EZDBStringItem              lineBizTpCd_A;

    /** SVC_CONTR_BR_CD_AL*/
	public final EZDBStringItem              svcContrBrCd_AL;

    /** SVC_CONTR_BR_CD_A*/
	public final EZDBStringItem              svcContrBrCd_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDBStringItem              svcContrBrDescTxt_A;

    /** XX_GENL_FLD_AREA_TXT_A*/
	public final EZDBStringItem              xxGenlFldAreaTxt_A;

    /** ORG_FUNC_ROLE_TP_NM_A*/
	public final EZDBStringItem              orgFuncRoleTpNm_A;


	/**
	 * NSAL0420_ABMsg is constructor.
	 * The initialization when the instance of NSAL0420_ABMsg is generated.
	 */
	public NSAL0420_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0420_ABMsg is constructor.
	 * The initialization when the instance of NSAL0420_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0420_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcRgPk_A = (EZDBBigDecimalItem)newItem("svcRgPk_A");
		psnCd_A = (EZDBStringItem)newItem("psnCd_A");
		svcRgNm_A = (EZDBStringItem)newItem("svcRgNm_A");
		lineBizTpDescTxt_A = (EZDBStringItem)newItem("lineBizTpDescTxt_A");
		lineBizTpCd_A = (EZDBStringItem)newItem("lineBizTpCd_A");
		svcContrBrCd_AL = (EZDBStringItem)newItem("svcContrBrCd_AL");
		svcContrBrCd_A = (EZDBStringItem)newItem("svcContrBrCd_A");
		svcContrBrDescTxt_A = (EZDBStringItem)newItem("svcContrBrDescTxt_A");
		xxGenlFldAreaTxt_A = (EZDBStringItem)newItem("xxGenlFldAreaTxt_A");
		orgFuncRoleTpNm_A = (EZDBStringItem)newItem("orgFuncRoleTpNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0420_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0420_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcRgPk_A", "svcRgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"psnCd_A", "psnCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"svcRgNm_A", "svcRgNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"lineBizTpDescTxt_A", "lineBizTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpCd_A", "lineBizTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"svcContrBrCd_AL", "svcContrBrCd_AL", "AL", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrCd_A", "svcContrBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxGenlFldAreaTxt_A", "xxGenlFldAreaTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"orgFuncRoleTpNm_A", "orgFuncRoleTpNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_RG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgPk_A
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A
        {"SVC_RG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgNm_A
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_A
        {"LINE_BIZ_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A
        {"SVC_CONTR_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_AL
        {"SVC_CONTR_BR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_A
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

