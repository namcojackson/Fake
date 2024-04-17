//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171204191917000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0200_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFAL0200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0200 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0200_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_SCR_COND_VAL_TXT_A*/
	public final EZDBStringItem              xxComnScrCondValTxt_A;

    /** XX_REC_NM_TXT_A*/
	public final EZDBStringItem              xxRecNmTxt_A;

    /** XX_RPT_TP_TXT_A*/
	public final EZDBStringItem              xxRptTpTxt_A;

    /** COA_START_ACTV_DT_A*/
	public final EZDBDateItem              coaStartActvDt_A;

    /** COA_END_ACTV_DT_A*/
	public final EZDBDateItem              coaEndActvDt_A;

    /** COA_GL_CMBN_ENBL_FLG_A*/
	public final EZDBStringItem              coaGlCmbnEnblFlg_A;

    /** COA_GL_CMBN_PSTG_ALLW_FLG_A*/
	public final EZDBStringItem              coaGlCmbnPstgAllwFlg_A;

    /** COA_GL_CMBN_BDG_ALLW_FLG_A*/
	public final EZDBStringItem              coaGlCmbnBdgAllwFlg_A;


	/**
	 * NFAL0200_ABMsg is constructor.
	 * The initialization when the instance of NFAL0200_ABMsg is generated.
	 */
	public NFAL0200_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0200_ABMsg is constructor.
	 * The initialization when the instance of NFAL0200_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0200_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnScrCondValTxt_A = (EZDBStringItem)newItem("xxComnScrCondValTxt_A");
		xxRecNmTxt_A = (EZDBStringItem)newItem("xxRecNmTxt_A");
		xxRptTpTxt_A = (EZDBStringItem)newItem("xxRptTpTxt_A");
		coaStartActvDt_A = (EZDBDateItem)newItem("coaStartActvDt_A");
		coaEndActvDt_A = (EZDBDateItem)newItem("coaEndActvDt_A");
		coaGlCmbnEnblFlg_A = (EZDBStringItem)newItem("coaGlCmbnEnblFlg_A");
		coaGlCmbnPstgAllwFlg_A = (EZDBStringItem)newItem("coaGlCmbnPstgAllwFlg_A");
		coaGlCmbnBdgAllwFlg_A = (EZDBStringItem)newItem("coaGlCmbnBdgAllwFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0200_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0200_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnScrCondValTxt_A", "xxComnScrCondValTxt_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"xxRecNmTxt_A", "xxRecNmTxt_A", "A", null, TYPE_HANKAKUEISU, "500", null},
	{"xxRptTpTxt_A", "xxRptTpTxt_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"coaStartActvDt_A", "coaStartActvDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"coaEndActvDt_A", "coaEndActvDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"coaGlCmbnEnblFlg_A", "coaGlCmbnEnblFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"coaGlCmbnPstgAllwFlg_A", "coaGlCmbnPstgAllwFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"coaGlCmbnBdgAllwFlg_A", "coaGlCmbnBdgAllwFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_SCR_COND_VAL_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrCondValTxt_A
        {"XX_REC_NM_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecNmTxt_A
        {"XX_RPT_TP_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRptTpTxt_A
        {"COA_START_ACTV_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//coaStartActvDt_A
        {"COA_END_ACTV_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//coaEndActvDt_A
        {"COA_GL_CMBN_ENBL_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaGlCmbnEnblFlg_A
        {"COA_GL_CMBN_PSTG_ALLW_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaGlCmbnPstgAllwFlg_A
        {"COA_GL_CMBN_BDG_ALLW_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaGlCmbnBdgAllwFlg_A
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

