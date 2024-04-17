//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231108082939000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_PK_C1*/
	public final EZDBBigDecimalItem              ctacPsnPk_C1;

    /** CTAC_PSN_NUM_C1*/
	public final EZDBStringItem              ctacPsnNum_C1;

    /** DS_CTAC_PSN_RELN_PK_C1*/
	public final EZDBBigDecimalItem              dsCtacPsnRelnPk_C1;

    /** CTAC_PSN_FIRST_NM_C1*/
	public final EZDBStringItem              ctacPsnFirstNm_C1;

    /** CTAC_PSN_LAST_NM_C1*/
	public final EZDBStringItem              ctacPsnLastNm_C1;

    /** CTAC_TP_CD_C1*/
	public final EZDBStringItem              ctacTpCd_C1;

    /** CTAC_TP_DESC_TXT_C1*/
	public final EZDBStringItem              ctacTpDescTxt_C1;

    /** DS_CTAC_PSN_DEPT_NM_C1*/
	public final EZDBStringItem              dsCtacPsnDeptNm_C1;

    /** DS_CTAC_PNT_VAL_TXT_C1*/
	public final EZDBStringItem              dsCtacPntValTxt_C1;

    /** DS_CTAC_PNT_VAL_TXT_C2*/
	public final EZDBStringItem              dsCtacPntValTxt_C2;

    /** DS_CTAC_PSN_EXTN_NUM_C1*/
	public final EZDBStringItem              dsCtacPsnExtnNum_C1;

    /** EFF_FROM_DT_C1*/
	public final EZDBDateItem              effFromDt_C1;

    /** EFF_THRU_DT_C1*/
	public final EZDBDateItem              effThruDt_C1;

    /** DS_PRIM_LOC_FLG_C1*/
	public final EZDBStringItem              dsPrimLocFlg_C1;

    /** DS_PRIM_LOC_FLG_CB*/
	public final EZDBStringItem              dsPrimLocFlg_CB;

    /** DS_ACCT_STS_NM_C1*/
	public final EZDBStringItem              dsAcctStsNm_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDBStringItem              ezUpTime_C1;

    /** _EZUpTimeZone_C1*/
	public final EZDBStringItem              ezUpTimeZone_C1;

    /** XX_REC_HIST_CRAT_TS_C1*/
	public final EZDBStringItem              xxRecHistCratTs_C1;

    /** XX_REC_HIST_CRAT_BY_NM_C1*/
	public final EZDBStringItem              xxRecHistCratByNm_C1;

    /** XX_REC_HIST_UPD_TS_C1*/
	public final EZDBStringItem              xxRecHistUpdTs_C1;

    /** XX_REC_HIST_UPD_BY_NM_C1*/
	public final EZDBStringItem              xxRecHistUpdByNm_C1;

    /** XX_REC_HIST_TBL_NM_C1*/
	public final EZDBStringItem              xxRecHistTblNm_C1;


	/**
	 * NMAL6720_CBMsg is constructor.
	 * The initialization when the instance of NMAL6720_CBMsg is generated.
	 */
	public NMAL6720_CBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_CBMsg is constructor.
	 * The initialization when the instance of NMAL6720_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnPk_C1 = (EZDBBigDecimalItem)newItem("ctacPsnPk_C1");
		ctacPsnNum_C1 = (EZDBStringItem)newItem("ctacPsnNum_C1");
		dsCtacPsnRelnPk_C1 = (EZDBBigDecimalItem)newItem("dsCtacPsnRelnPk_C1");
		ctacPsnFirstNm_C1 = (EZDBStringItem)newItem("ctacPsnFirstNm_C1");
		ctacPsnLastNm_C1 = (EZDBStringItem)newItem("ctacPsnLastNm_C1");
		ctacTpCd_C1 = (EZDBStringItem)newItem("ctacTpCd_C1");
		ctacTpDescTxt_C1 = (EZDBStringItem)newItem("ctacTpDescTxt_C1");
		dsCtacPsnDeptNm_C1 = (EZDBStringItem)newItem("dsCtacPsnDeptNm_C1");
		dsCtacPntValTxt_C1 = (EZDBStringItem)newItem("dsCtacPntValTxt_C1");
		dsCtacPntValTxt_C2 = (EZDBStringItem)newItem("dsCtacPntValTxt_C2");
		dsCtacPsnExtnNum_C1 = (EZDBStringItem)newItem("dsCtacPsnExtnNum_C1");
		effFromDt_C1 = (EZDBDateItem)newItem("effFromDt_C1");
		effThruDt_C1 = (EZDBDateItem)newItem("effThruDt_C1");
		dsPrimLocFlg_C1 = (EZDBStringItem)newItem("dsPrimLocFlg_C1");
		dsPrimLocFlg_CB = (EZDBStringItem)newItem("dsPrimLocFlg_CB");
		dsAcctStsNm_C1 = (EZDBStringItem)newItem("dsAcctStsNm_C1");
		ezUpTime_C1 = (EZDBStringItem)newItem("ezUpTime_C1");
		ezUpTimeZone_C1 = (EZDBStringItem)newItem("ezUpTimeZone_C1");
		xxRecHistCratTs_C1 = (EZDBStringItem)newItem("xxRecHistCratTs_C1");
		xxRecHistCratByNm_C1 = (EZDBStringItem)newItem("xxRecHistCratByNm_C1");
		xxRecHistUpdTs_C1 = (EZDBStringItem)newItem("xxRecHistUpdTs_C1");
		xxRecHistUpdByNm_C1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_C1");
		xxRecHistTblNm_C1 = (EZDBStringItem)newItem("xxRecHistTblNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnPk_C1", "ctacPsnPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnNum_C1", "ctacPsnNum_C1", "C1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsCtacPsnRelnPk_C1", "dsCtacPsnRelnPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnFirstNm_C1", "ctacPsnFirstNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_C1", "ctacPsnLastNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacTpCd_C1", "ctacTpCd_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacTpDescTxt_C1", "ctacTpDescTxt_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPsnDeptNm_C1", "dsCtacPsnDeptNm_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPntValTxt_C1", "dsCtacPntValTxt_C1", "C1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_C2", "dsCtacPntValTxt_C2", "C2", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum_C1", "dsCtacPsnExtnNum_C1", "C1", null, TYPE_HANKAKUEISU, "10", null},
	{"effFromDt_C1", "effFromDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_C1", "effThruDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsPrimLocFlg_C1", "dsPrimLocFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsPrimLocFlg_CB", "dsPrimLocFlg_CB", "CB", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctStsNm_C1", "dsAcctStsNm_C1", "C1", null, TYPE_HANKAKUEISU, "20", null},
	{"ezUpTime_C1", "ezUpTime_C1", "C1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_C1", "ezUpTimeZone_C1", "C1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_C1", "xxRecHistCratTs_C1", "C1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_C1", "xxRecHistCratByNm_C1", "C1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_C1", "xxRecHistUpdTs_C1", "C1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_C1", "xxRecHistUpdByNm_C1", "C1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_C1", "xxRecHistTblNm_C1", "C1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_PSN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_C1
        {"CTAC_PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnNum_C1
        {"DS_CTAC_PSN_RELN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnRelnPk_C1
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_C1
        {"CTAC_PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_C1
        {"CTAC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpCd_C1
        {"CTAC_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpDescTxt_C1
        {"DS_CTAC_PSN_DEPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnDeptNm_C1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_C1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_C2
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum_C1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_C1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_C1
        {"DS_PRIM_LOC_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimLocFlg_C1
        {"DS_PRIM_LOC_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimLocFlg_CB
        {"DS_ACCT_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctStsNm_C1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_C1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_C1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_C1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_C1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_C1
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

