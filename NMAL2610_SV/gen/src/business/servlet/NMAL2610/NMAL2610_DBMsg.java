//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171117183034000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2610_DBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2610 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2610_DBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_D1*/
	public final EZDBStringItem              xxChkBox_D1;

    /** PSN_FIRST_NM_D1*/
	public final EZDBStringItem              psnFirstNm_D1;

    /** PSN_LAST_NM_D1*/
	public final EZDBStringItem              psnLastNm_D1;

    /** XX_PSN_NM_D1*/
	public final EZDBStringItem              xxPsnNm_D1;

    /** PSN_CD_D1*/
	public final EZDBStringItem              psnCd_D1;

    /** PSN_NUM_D1*/
	public final EZDBStringItem              psnNum_D1;

    /** ACCT_TEAM_ROLE_TP_CD_P1*/
	public final EZDBStringItem              acctTeamRoleTpCd_P1;

    /** EFF_FROM_DT_D1*/
	public final EZDBDateItem              effFromDt_D1;

    /** EFF_THRU_DT_D1*/
	public final EZDBDateItem              effThruDt_D1;

    /** EFF_FROM_DT_D2*/
	public final EZDBDateItem              effFromDt_D2;

    /** EFF_THRU_DT_D2*/
	public final EZDBDateItem              effThruDt_D2;

    /** GNRN_TP_CD_D1*/
	public final EZDBStringItem              gnrnTpCd_D1;

    /** _EZUpdateDateTime_X4*/
	public final EZDBStringItem              ezUpTime_X4;

    /** _EZUpTimeZone_X4*/
	public final EZDBStringItem              ezUpTimeZone_X4;

    /** ORG_CD_X4*/
	public final EZDBStringItem              orgCd_X4;

    /** ORG_STRU_TP_CD_X4*/
	public final EZDBStringItem              orgStruTpCd_X4;

    /** PSN_CD_X4*/
	public final EZDBStringItem              psnCd_X4;

    /** ORG_FUNC_ROLE_TP_CD_X4*/
	public final EZDBStringItem              orgFuncRoleTpCd_X4;

    /** EFF_FROM_DT_X4*/
	public final EZDBDateItem              effFromDt_X4;

    /** PSN_FIRST_NM_DB*/
	public final EZDBStringItem              psnFirstNm_DB;

    /** PSN_LAST_NM_DB*/
	public final EZDBStringItem              psnLastNm_DB;

    /** XX_PSN_NM_DB*/
	public final EZDBStringItem              xxPsnNm_DB;

    /** PSN_CD_DB*/
	public final EZDBStringItem              psnCd_DB;

    /** PSN_NUM_DB*/
	public final EZDBStringItem              psnNum_DB;

    /** ACCT_TEAM_ROLE_TP_CD_DB*/
	public final EZDBStringItem              acctTeamRoleTpCd_DB;

    /** EFF_FROM_DT_DB*/
	public final EZDBDateItem              effFromDt_DB;

    /** EFF_THRU_DT_DB*/
	public final EZDBDateItem              effThruDt_DB;

    /** GNRN_TP_CD_DB*/
	public final EZDBStringItem              gnrnTpCd_DB;

    /** ORG_FUNC_ROLE_TP_CD_DB*/
	public final EZDBStringItem              orgFuncRoleTpCd_DB;

    /** XX_REC_HIST_CRAT_TS_D1*/
	public final EZDBStringItem              xxRecHistCratTs_D1;

    /** XX_REC_HIST_CRAT_BY_NM_D1*/
	public final EZDBStringItem              xxRecHistCratByNm_D1;

    /** XX_REC_HIST_UPD_TS_D1*/
	public final EZDBStringItem              xxRecHistUpdTs_D1;

    /** XX_REC_HIST_UPD_BY_NM_D1*/
	public final EZDBStringItem              xxRecHistUpdByNm_D1;

    /** XX_REC_HIST_TBL_NM_D1*/
	public final EZDBStringItem              xxRecHistTblNm_D1;


	/**
	 * NMAL2610_DBMsg is constructor.
	 * The initialization when the instance of NMAL2610_DBMsg is generated.
	 */
	public NMAL2610_DBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2610_DBMsg is constructor.
	 * The initialization when the instance of NMAL2610_DBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2610_DBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_D1 = (EZDBStringItem)newItem("xxChkBox_D1");
		psnFirstNm_D1 = (EZDBStringItem)newItem("psnFirstNm_D1");
		psnLastNm_D1 = (EZDBStringItem)newItem("psnLastNm_D1");
		xxPsnNm_D1 = (EZDBStringItem)newItem("xxPsnNm_D1");
		psnCd_D1 = (EZDBStringItem)newItem("psnCd_D1");
		psnNum_D1 = (EZDBStringItem)newItem("psnNum_D1");
		acctTeamRoleTpCd_P1 = (EZDBStringItem)newItem("acctTeamRoleTpCd_P1");
		effFromDt_D1 = (EZDBDateItem)newItem("effFromDt_D1");
		effThruDt_D1 = (EZDBDateItem)newItem("effThruDt_D1");
		effFromDt_D2 = (EZDBDateItem)newItem("effFromDt_D2");
		effThruDt_D2 = (EZDBDateItem)newItem("effThruDt_D2");
		gnrnTpCd_D1 = (EZDBStringItem)newItem("gnrnTpCd_D1");
		ezUpTime_X4 = (EZDBStringItem)newItem("ezUpTime_X4");
		ezUpTimeZone_X4 = (EZDBStringItem)newItem("ezUpTimeZone_X4");
		orgCd_X4 = (EZDBStringItem)newItem("orgCd_X4");
		orgStruTpCd_X4 = (EZDBStringItem)newItem("orgStruTpCd_X4");
		psnCd_X4 = (EZDBStringItem)newItem("psnCd_X4");
		orgFuncRoleTpCd_X4 = (EZDBStringItem)newItem("orgFuncRoleTpCd_X4");
		effFromDt_X4 = (EZDBDateItem)newItem("effFromDt_X4");
		psnFirstNm_DB = (EZDBStringItem)newItem("psnFirstNm_DB");
		psnLastNm_DB = (EZDBStringItem)newItem("psnLastNm_DB");
		xxPsnNm_DB = (EZDBStringItem)newItem("xxPsnNm_DB");
		psnCd_DB = (EZDBStringItem)newItem("psnCd_DB");
		psnNum_DB = (EZDBStringItem)newItem("psnNum_DB");
		acctTeamRoleTpCd_DB = (EZDBStringItem)newItem("acctTeamRoleTpCd_DB");
		effFromDt_DB = (EZDBDateItem)newItem("effFromDt_DB");
		effThruDt_DB = (EZDBDateItem)newItem("effThruDt_DB");
		gnrnTpCd_DB = (EZDBStringItem)newItem("gnrnTpCd_DB");
		orgFuncRoleTpCd_DB = (EZDBStringItem)newItem("orgFuncRoleTpCd_DB");
		xxRecHistCratTs_D1 = (EZDBStringItem)newItem("xxRecHistCratTs_D1");
		xxRecHistCratByNm_D1 = (EZDBStringItem)newItem("xxRecHistCratByNm_D1");
		xxRecHistUpdTs_D1 = (EZDBStringItem)newItem("xxRecHistUpdTs_D1");
		xxRecHistUpdByNm_D1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_D1");
		xxRecHistTblNm_D1 = (EZDBStringItem)newItem("xxRecHistTblNm_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2610_DBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2610_DBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_D1", "xxChkBox_D1", "D1", null, TYPE_HANKAKUEIJI, "1", null},
	{"psnFirstNm_D1", "psnFirstNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_D1", "psnLastNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_D1", "xxPsnNm_D1", "D1", null, TYPE_HANKAKUEISU, "62", null},
	{"psnCd_D1", "psnCd_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnNum_D1", "psnNum_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"acctTeamRoleTpCd_P1", "acctTeamRoleTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_D1", "effFromDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_D1", "effThruDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_D2", "effFromDt_D2", "D2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_D2", "effThruDt_D2", "D2", null, TYPE_NENTSUKIHI, "8", null},
	{"gnrnTpCd_D1", "gnrnTpCd_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_X4", "ezUpTime_X4", "X4", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X4", "ezUpTimeZone_X4", "X4", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_X4", "orgCd_X4", "X4", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_X4", "orgStruTpCd_X4", "X4", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_X4", "psnCd_X4", "X4", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_X4", "orgFuncRoleTpCd_X4", "X4", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_X4", "effFromDt_X4", "X4", null, TYPE_NENTSUKIHI, "8", null},
	{"psnFirstNm_DB", "psnFirstNm_DB", "DB", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_DB", "psnLastNm_DB", "DB", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_DB", "xxPsnNm_DB", "DB", null, TYPE_HANKAKUEISU, "62", null},
	{"psnCd_DB", "psnCd_DB", "DB", null, TYPE_HANKAKUEISU, "8", null},
	{"psnNum_DB", "psnNum_DB", "DB", null, TYPE_HANKAKUEISU, "50", null},
	{"acctTeamRoleTpCd_DB", "acctTeamRoleTpCd_DB", "DB", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_DB", "effFromDt_DB", "DB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_DB", "effThruDt_DB", "DB", null, TYPE_NENTSUKIHI, "8", null},
	{"gnrnTpCd_DB", "gnrnTpCd_DB", "DB", null, TYPE_HANKAKUEISU, "1", null},
	{"orgFuncRoleTpCd_DB", "orgFuncRoleTpCd_DB", "DB", null, TYPE_HANKAKUEISU, "8", null},
	{"xxRecHistCratTs_D1", "xxRecHistCratTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_D1", "xxRecHistCratByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_D1", "xxRecHistUpdTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_D1", "xxRecHistUpdByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_D1", "xxRecHistTblNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"PSN_FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_D1
        {"PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_D1
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_D1
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_D1
        {"PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_D1
        {"ACCT_TEAM_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctTeamRoleTpCd_P1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_D1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_D1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_D2
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_D2
        {"GNRN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_D1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X4
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X4
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_X4
        {"ORG_STRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_X4
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_X4
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_X4
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_X4
        {"PSN_FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_DB
        {"PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_DB
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_DB
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_DB
        {"PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_DB
        {"ACCT_TEAM_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctTeamRoleTpCd_DB
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_DB
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_DB
        {"GNRN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_DB
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_DB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_D1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_D1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_D1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_D1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_D1
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
