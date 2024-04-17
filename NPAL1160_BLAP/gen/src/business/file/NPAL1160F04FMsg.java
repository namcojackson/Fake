//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20230831192016000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1160F04FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NPAL1160F04 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1160F04FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** APVL_HRCH_TP_CD_E*/
	public final EZDFStringItem              apvlHrchTpCd_E;

    /** APVL_TEAM_POSN_TP_CD_E*/
	public final EZDFStringItem              apvlTeamPosnTpCd_E;

    /** PSN_CD_E*/
	public final EZDFStringItem              psnCd_E;

    /** FULL_PSN_NM_E*/
	public final EZDFStringItem              fullPsnNm_E;

    /** PRCH_GRP_CD_E*/
	public final EZDFStringItem              prchGrpCd_E;

    /** APVL_HIST_SRC_TP_CD_E*/
	public final EZDFStringItem              apvlHistSrcTpCd_E;

    /** PRCH_REQ_TP_CD_E*/
	public final EZDFStringItem              prchReqTpCd_E;

    /** APVL_LIMIT_AMT_E*/
	public final EZDFBigDecimalItem              apvlLimitAmt_E;


	/**
	 * NPAL1160F04FMsg is constructor.
	 * The initialization when the instance of NPAL1160F04FMsg is generated.
	 */
	public NPAL1160F04FMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1160F04FMsg is constructor.
	 * The initialization when the instance of NPAL1160F04FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1160F04FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apvlHrchTpCd_E = (EZDFStringItem)newItem("apvlHrchTpCd_E");
		apvlTeamPosnTpCd_E = (EZDFStringItem)newItem("apvlTeamPosnTpCd_E");
		psnCd_E = (EZDFStringItem)newItem("psnCd_E");
		fullPsnNm_E = (EZDFStringItem)newItem("fullPsnNm_E");
		prchGrpCd_E = (EZDFStringItem)newItem("prchGrpCd_E");
		apvlHistSrcTpCd_E = (EZDFStringItem)newItem("apvlHistSrcTpCd_E");
		prchReqTpCd_E = (EZDFStringItem)newItem("prchReqTpCd_E");
		apvlLimitAmt_E = (EZDFBigDecimalItem)newItem("apvlLimitAmt_E");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1160F04FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1160F04FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apvlHrchTpCd_E", "apvlHrchTpCd_E", "E", null, TYPE_HANKAKUEISU, "2", null},
	{"apvlTeamPosnTpCd_E", "apvlTeamPosnTpCd_E", "E", null, TYPE_HANKAKUEISU, "2", null},
	{"psnCd_E", "psnCd_E", "E", null, TYPE_HANKAKUEISU, "8", null},
	{"fullPsnNm_E", "fullPsnNm_E", "E", null, TYPE_HANKAKUEISU, "62", null},
	{"prchGrpCd_E", "prchGrpCd_E", "E", null, TYPE_HANKAKUEISU, "6", null},
	{"apvlHistSrcTpCd_E", "apvlHistSrcTpCd_E", "E", null, TYPE_HANKAKUEISU, "2", null},
	{"prchReqTpCd_E", "prchReqTpCd_E", "E", null, TYPE_HANKAKUEISU, "4", null},
	{"apvlLimitAmt_E", "apvlLimitAmt_E", "E", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"APVL_HRCH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHrchTpCd_E
        {"APVL_TEAM_POSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlTeamPosnTpCd_E
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_E
        {"FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_E
        {"PRCH_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpCd_E
        {"APVL_HIST_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistSrcTpCd_E
        {"PRCH_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_E
        {"APVL_LIMIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlLimitAmt_E
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
