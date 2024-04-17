//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161130113131000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1050_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1050_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** SVC_TERM_ATTRB_DESC_TXT_A*/
	public final EZDBStringItem              svcTermAttrbDescTxt_A;

    /** SVC_TERM_COND_ATTRB_NM_A*/
	public final EZDBStringItem              svcTermCondAttrbNm_A;

    /** SVC_TERM_COND_DATA_SRC_CD_A*/
	public final EZDBStringItem              svcTermCondDataSrcCd_A;

    /** SVC_TERM_COND_SRC_DESC_TXT_A*/
	public final EZDBStringItem              svcTermCondSrcDescTxt_A;

    /** SVC_TERM_DATA_TP_CD_1V*/
	public final EZDBStringItem              svcTermDataTpCd_1V;

    /** SVC_TERM_DATA_TP_DESC_TXT_A*/
	public final EZDBStringItem              svcTermDataTpDescTxt_A;

    /** SVC_TERM_ATTRB_SORT_NUM_A*/
	public final EZDBBigDecimalItem              svcTermAttrbSortNum_A;

    /** SVC_TERM_COND_CATG_PK_1V*/
	public final EZDBBigDecimalItem              svcTermCondCatgPk_1V;

    /** SVC_TERM_COND_CATG_DESC_TXT_A*/
	public final EZDBStringItem              svcTermCondCatgDescTxt_A;

    /** XX_CHK_BOX_AC*/
	public final EZDBStringItem              xxChkBox_AC;

    /** XX_CHK_BOX_AM*/
	public final EZDBStringItem              xxChkBox_AM;

    /** XX_CHK_BOX_AA*/
	public final EZDBStringItem              xxChkBox_AA;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_TO_DT_A*/
	public final EZDBDateItem              effToDt_A;

    /** SVC_TERM_COND_ATTRB_PK_A*/
	public final EZDBBigDecimalItem              svcTermCondAttrbPk_A;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDBStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDBStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDBStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDBStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDBStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL1050_ABMsg is constructor.
	 * The initialization when the instance of NSAL1050_ABMsg is generated.
	 */
	public NSAL1050_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1050_ABMsg is constructor.
	 * The initialization when the instance of NSAL1050_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1050_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		svcTermAttrbDescTxt_A = (EZDBStringItem)newItem("svcTermAttrbDescTxt_A");
		svcTermCondAttrbNm_A = (EZDBStringItem)newItem("svcTermCondAttrbNm_A");
		svcTermCondDataSrcCd_A = (EZDBStringItem)newItem("svcTermCondDataSrcCd_A");
		svcTermCondSrcDescTxt_A = (EZDBStringItem)newItem("svcTermCondSrcDescTxt_A");
		svcTermDataTpCd_1V = (EZDBStringItem)newItem("svcTermDataTpCd_1V");
		svcTermDataTpDescTxt_A = (EZDBStringItem)newItem("svcTermDataTpDescTxt_A");
		svcTermAttrbSortNum_A = (EZDBBigDecimalItem)newItem("svcTermAttrbSortNum_A");
		svcTermCondCatgPk_1V = (EZDBBigDecimalItem)newItem("svcTermCondCatgPk_1V");
		svcTermCondCatgDescTxt_A = (EZDBStringItem)newItem("svcTermCondCatgDescTxt_A");
		xxChkBox_AC = (EZDBStringItem)newItem("xxChkBox_AC");
		xxChkBox_AM = (EZDBStringItem)newItem("xxChkBox_AM");
		xxChkBox_AA = (EZDBStringItem)newItem("xxChkBox_AA");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effToDt_A = (EZDBDateItem)newItem("effToDt_A");
		svcTermCondAttrbPk_A = (EZDBBigDecimalItem)newItem("svcTermCondAttrbPk_A");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1050_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1050_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcTermAttrbDescTxt_A", "svcTermAttrbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondAttrbNm_A", "svcTermCondAttrbNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcTermCondDataSrcCd_A", "svcTermCondDataSrcCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcTermCondSrcDescTxt_A", "svcTermCondSrcDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermDataTpCd_1V", "svcTermDataTpCd_1V", "1V", null, TYPE_HANKAKUEISU, "2", null},
	{"svcTermDataTpDescTxt_A", "svcTermDataTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermAttrbSortNum_A", "svcTermAttrbSortNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"svcTermCondCatgPk_1V", "svcTermCondCatgPk_1V", "1V", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTermCondCatgDescTxt_A", "svcTermCondCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_AC", "xxChkBox_AC", "AC", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AM", "xxChkBox_AM", "AM", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AA", "xxChkBox_AA", "AA", null, TYPE_HANKAKUEIJI, "1", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt_A", "effToDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"svcTermCondAttrbPk_A", "svcTermCondAttrbPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
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

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"SVC_TERM_ATTRB_DESC_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbDescTxt_A
        {"SVC_TERM_COND_ATTRB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondAttrbNm_A
        {"SVC_TERM_COND_DATA_SRC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataSrcCd_A
        {"SVC_TERM_COND_SRC_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondSrcDescTxt_A
        {"SVC_TERM_DATA_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_1V
        {"SVC_TERM_DATA_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpDescTxt_A
        {"SVC_TERM_ATTRB_SORT_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbSortNum_A
        {"SVC_TERM_COND_CATG_PK", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgPk_1V
        {"SVC_TERM_COND_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgDescTxt_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AC
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AM
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AA
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effToDt_A
        {"SVC_TERM_COND_ATTRB_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondAttrbPk_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

