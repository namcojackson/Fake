//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231106120030000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6700 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_PK_D1*/
	public final EZDCBigDecimalItem              ctacPsnPk_D1;

    /** CTAC_PSN_NUM_D1*/
	public final EZDCStringItem              ctacPsnNum_D1;

    /** DS_CTAC_PSN_RELN_PK_D1*/
	public final EZDCBigDecimalItem              dsCtacPsnRelnPk_D1;

    /** CTAC_TP_CD_D1*/
	public final EZDCStringItem              ctacTpCd_D1;

    /** CTAC_TP_DESC_TXT_D1*/
	public final EZDCStringItem              ctacTpDescTxt_D1;

    /** CTAC_PSN_FIRST_NM_D1*/
	public final EZDCStringItem              ctacPsnFirstNm_D1;

    /** CTAC_PSN_LAST_NM_D1*/
	public final EZDCStringItem              ctacPsnLastNm_D1;

    /** DS_CTAC_PSN_DEPT_NM_D1*/
	public final EZDCStringItem              dsCtacPsnDeptNm_D1;

    /** DS_CTAC_PNT_VAL_TXT_D1*/
	public final EZDCStringItem              dsCtacPntValTxt_D1;

    /** DS_CTAC_PNT_VAL_TXT_D2*/
	public final EZDCStringItem              dsCtacPntValTxt_D2;

    /** CTAC_PSN_EXTN_NUM_D1*/
	public final EZDCStringItem              ctacPsnExtnNum_D1;

    /** EFF_FROM_DT_D1*/
	public final EZDCDateItem              effFromDt_D1;

    /** EFF_THRU_DT_D1*/
	public final EZDCDateItem              effThruDt_D1;

    /** DS_PRIM_LOC_FLG_D1*/
	public final EZDCStringItem              dsPrimLocFlg_D1;

    /** DS_PRIM_LOC_FLG_DB*/
	public final EZDCStringItem              dsPrimLocFlg_DB;

    /** DPL_STS_NM_D1*/
	public final EZDCStringItem              dplStsNm_D1;

    /** _EZUpdateDateTime_D1*/
	public final EZDCStringItem              ezUpTime_D1;

    /** _EZUpTimeZone_D1*/
	public final EZDCStringItem              ezUpTimeZone_D1;

    /** _EZUpdateDateTime_D2*/
	public final EZDCStringItem              ezUpTime_D2;

    /** _EZUpTimeZone_D2*/
	public final EZDCStringItem              ezUpTimeZone_D2;

    /** _EZUpdateDateTime_DR*/
	public final EZDCStringItem              ezUpTime_DR;

    /** _EZUpTimeZone_DR*/
	public final EZDCStringItem              ezUpTimeZone_DR;

    /** XX_REC_HIST_CRAT_TS_D1*/
	public final EZDCStringItem              xxRecHistCratTs_D1;

    /** XX_REC_HIST_CRAT_BY_NM_D1*/
	public final EZDCStringItem              xxRecHistCratByNm_D1;

    /** XX_REC_HIST_UPD_TS_D1*/
	public final EZDCStringItem              xxRecHistUpdTs_D1;

    /** XX_REC_HIST_UPD_BY_NM_D1*/
	public final EZDCStringItem              xxRecHistUpdByNm_D1;

    /** XX_REC_HIST_TBL_NM_D1*/
	public final EZDCStringItem              xxRecHistTblNm_D1;


	/**
	 * NMAL6700_DCMsg is constructor.
	 * The initialization when the instance of NMAL6700_DCMsg is generated.
	 */
	public NMAL6700_DCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6700_DCMsg is constructor.
	 * The initialization when the instance of NMAL6700_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6700_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnPk_D1 = (EZDCBigDecimalItem)newItem("ctacPsnPk_D1");
		ctacPsnNum_D1 = (EZDCStringItem)newItem("ctacPsnNum_D1");
		dsCtacPsnRelnPk_D1 = (EZDCBigDecimalItem)newItem("dsCtacPsnRelnPk_D1");
		ctacTpCd_D1 = (EZDCStringItem)newItem("ctacTpCd_D1");
		ctacTpDescTxt_D1 = (EZDCStringItem)newItem("ctacTpDescTxt_D1");
		ctacPsnFirstNm_D1 = (EZDCStringItem)newItem("ctacPsnFirstNm_D1");
		ctacPsnLastNm_D1 = (EZDCStringItem)newItem("ctacPsnLastNm_D1");
		dsCtacPsnDeptNm_D1 = (EZDCStringItem)newItem("dsCtacPsnDeptNm_D1");
		dsCtacPntValTxt_D1 = (EZDCStringItem)newItem("dsCtacPntValTxt_D1");
		dsCtacPntValTxt_D2 = (EZDCStringItem)newItem("dsCtacPntValTxt_D2");
		ctacPsnExtnNum_D1 = (EZDCStringItem)newItem("ctacPsnExtnNum_D1");
		effFromDt_D1 = (EZDCDateItem)newItem("effFromDt_D1");
		effThruDt_D1 = (EZDCDateItem)newItem("effThruDt_D1");
		dsPrimLocFlg_D1 = (EZDCStringItem)newItem("dsPrimLocFlg_D1");
		dsPrimLocFlg_DB = (EZDCStringItem)newItem("dsPrimLocFlg_DB");
		dplStsNm_D1 = (EZDCStringItem)newItem("dplStsNm_D1");
		ezUpTime_D1 = (EZDCStringItem)newItem("ezUpTime_D1");
		ezUpTimeZone_D1 = (EZDCStringItem)newItem("ezUpTimeZone_D1");
		ezUpTime_D2 = (EZDCStringItem)newItem("ezUpTime_D2");
		ezUpTimeZone_D2 = (EZDCStringItem)newItem("ezUpTimeZone_D2");
		ezUpTime_DR = (EZDCStringItem)newItem("ezUpTime_DR");
		ezUpTimeZone_DR = (EZDCStringItem)newItem("ezUpTimeZone_DR");
		xxRecHistCratTs_D1 = (EZDCStringItem)newItem("xxRecHistCratTs_D1");
		xxRecHistCratByNm_D1 = (EZDCStringItem)newItem("xxRecHistCratByNm_D1");
		xxRecHistUpdTs_D1 = (EZDCStringItem)newItem("xxRecHistUpdTs_D1");
		xxRecHistUpdByNm_D1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_D1");
		xxRecHistTblNm_D1 = (EZDCStringItem)newItem("xxRecHistTblNm_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6700_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6700_DCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnPk_D1", "ctacPsnPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnNum_D1", "ctacPsnNum_D1", "D1", null, TYPE_HANKAKUEISU, "10", null},
	{"dsCtacPsnRelnPk_D1", "dsCtacPsnRelnPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacTpCd_D1", "ctacTpCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacTpDescTxt_D1", "ctacTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"ctacPsnFirstNm_D1", "ctacPsnFirstNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_D1", "ctacPsnLastNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPsnDeptNm_D1", "dsCtacPsnDeptNm_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCtacPntValTxt_D1", "dsCtacPntValTxt_D1", "D1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_D2", "dsCtacPntValTxt_D2", "D2", null, TYPE_HANKAKUEISU, "320", null},
	{"ctacPsnExtnNum_D1", "ctacPsnExtnNum_D1", "D1", null, TYPE_HANKAKUEISU, "10", null},
	{"effFromDt_D1", "effFromDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_D1", "effThruDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsPrimLocFlg_D1", "dsPrimLocFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsPrimLocFlg_DB", "dsPrimLocFlg_DB", "DB", null, TYPE_HANKAKUEISU, "1", null},
	{"dplStsNm_D1", "dplStsNm_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"ezUpTime_D1", "ezUpTime_D1", "D1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D1", "ezUpTimeZone_D1", "D1", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_D2", "ezUpTime_D2", "D2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D2", "ezUpTimeZone_D2", "D2", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_DR", "ezUpTime_DR", "DR", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_DR", "ezUpTimeZone_DR", "DR", null, TYPE_HANKAKUEISU, "5", null},
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

        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_D1
        {"CTAC_PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnNum_D1
        {"DS_CTAC_PSN_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnRelnPk_D1
        {"CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpCd_D1
        {"CTAC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpDescTxt_D1
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_D1
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_D1
        {"DS_CTAC_PSN_DEPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnDeptNm_D1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_D1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_D2
        {"CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnExtnNum_D1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_D1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_D1
        {"DS_PRIM_LOC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimLocFlg_D1
        {"DS_PRIM_LOC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPrimLocFlg_DB
        {"DPL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplStsNm_D1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DR
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DR
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_D1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_D1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_D1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_D1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_D1
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
