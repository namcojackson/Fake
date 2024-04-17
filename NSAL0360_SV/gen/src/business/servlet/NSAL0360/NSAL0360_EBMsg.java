//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171226035306000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0360 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0360_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_E1*/
	public final EZDBStringItem              xxChkBox_E1;

    /** DS_CONTR_BLLG_MTR_PK_E1*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk_E1;

    /** MTR_LB_NM_E1*/
	public final EZDBStringItem              mtrLbNm_E1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_E1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdMtrPk_E1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_E1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdSmryPk_E1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_E1*/
	public final EZDBStringItem              dsContrBllgSchdSqNum_E1;

    /** DS_CONTR_PRC_EFF_PK_E1*/
	public final EZDBBigDecimalItem              dsContrPrcEffPk_E1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_E1*/
	public final EZDBBigDecimalItem              dsContrPrcEffSqNum_E1;

    /** PER_SCHD_NUM_E1*/
	public final EZDBBigDecimalItem              perSchdNum_E1;

    /** BLLG_CYCLE_UOM_CD_E1*/
	public final EZDBStringItemArray              bllgCycleUomCd_E1;

    /** BLLG_CYCLE_UOM_NM_E2*/
	public final EZDBStringItemArray              bllgCycleUomNm_E2;

    /** PER_BLLG_CYCLE_CD_E1*/
	public final EZDBStringItem              perBllgCycleCd_E1;

    /** BLLG_SCHD_FROM_DT_E1*/
	public final EZDBDateItem              bllgSchdFromDt_E1;

    /** BLLG_SCHD_THRU_DT_E1*/
	public final EZDBDateItem              bllgSchdThruDt_E1;

    /** BLLG_CYCLE_CD_E1*/
	public final EZDBStringItem              bllgCycleCd_E1;

    /** BLLG_CYCLE_DESC_TXT_E1*/
	public final EZDBStringItem              bllgCycleDescTxt_E1;

    /** BLLG_CYCLE_START_MTH_E1*/
	public final EZDBStringItem              bllgCycleStartMth_E1;

    /** XS_MTR_COPY_QTY_E1*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_E1;

    /** XS_MTR_COPY_QTY_ES*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_ES;

    /** XS_MTR_AMT_RATE_E1*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_E1;

    /** CCY_CD_E1*/
	public final EZDBStringItem              ccyCd_E1;

    /** CONTR_XS_COPY_PK_E1*/
	public final EZDBBigDecimalItem              contrXsCopyPk_E1;

    /** XS_MTR_FIRST_FLG_E1*/
	public final EZDBStringItem              xsMtrFirstFlg_E1;

    /** INV_FLG_E1*/
	public final EZDBStringItem              invFlg_E1;

    /** XX_DTL_CNT_E1*/
	public final EZDBBigDecimalItem              xxDtlCnt_E1;

    /** _EZUpdateDateTime_E1*/
	public final EZDBStringItem              ezUpTime_E1;

    /** _EZUpTimeZone_E1*/
	public final EZDBStringItem              ezUpTimeZone_E1;

    /** XX_REC_HIST_CRAT_TS_E1*/
	public final EZDBStringItem              xxRecHistCratTs_E1;

    /** XX_REC_HIST_CRAT_BY_NM_E1*/
	public final EZDBStringItem              xxRecHistCratByNm_E1;

    /** XX_REC_HIST_UPD_TS_E1*/
	public final EZDBStringItem              xxRecHistUpdTs_E1;

    /** XX_REC_HIST_UPD_BY_NM_E1*/
	public final EZDBStringItem              xxRecHistUpdByNm_E1;

    /** XX_REC_HIST_TBL_NM_E1*/
	public final EZDBStringItem              xxRecHistTblNm_E1;

    /** _EZUpdateDateTime_EM*/
	public final EZDBStringItem              ezUpTime_EM;

    /** _EZUpTimeZone_EM*/
	public final EZDBStringItem              ezUpTimeZone_EM;


	/**
	 * NSAL0360_EBMsg is constructor.
	 * The initialization when the instance of NSAL0360_EBMsg is generated.
	 */
	public NSAL0360_EBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_EBMsg is constructor.
	 * The initialization when the instance of NSAL0360_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_E1 = (EZDBStringItem)newItem("xxChkBox_E1");
		dsContrBllgMtrPk_E1 = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk_E1");
		mtrLbNm_E1 = (EZDBStringItem)newItem("mtrLbNm_E1");
		dsContrBllgSchdMtrPk_E1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdMtrPk_E1");
		dsContrBllgSchdSmryPk_E1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdSmryPk_E1");
		dsContrBllgSchdSqNum_E1 = (EZDBStringItem)newItem("dsContrBllgSchdSqNum_E1");
		dsContrPrcEffPk_E1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffPk_E1");
		dsContrPrcEffSqNum_E1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffSqNum_E1");
		perSchdNum_E1 = (EZDBBigDecimalItem)newItem("perSchdNum_E1");
		bllgCycleUomCd_E1 = (EZDBStringItemArray)newItemArray("bllgCycleUomCd_E1");
		bllgCycleUomNm_E2 = (EZDBStringItemArray)newItemArray("bllgCycleUomNm_E2");
		perBllgCycleCd_E1 = (EZDBStringItem)newItem("perBllgCycleCd_E1");
		bllgSchdFromDt_E1 = (EZDBDateItem)newItem("bllgSchdFromDt_E1");
		bllgSchdThruDt_E1 = (EZDBDateItem)newItem("bllgSchdThruDt_E1");
		bllgCycleCd_E1 = (EZDBStringItem)newItem("bllgCycleCd_E1");
		bllgCycleDescTxt_E1 = (EZDBStringItem)newItem("bllgCycleDescTxt_E1");
		bllgCycleStartMth_E1 = (EZDBStringItem)newItem("bllgCycleStartMth_E1");
		xsMtrCopyQty_E1 = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_E1");
		xsMtrCopyQty_ES = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_ES");
		xsMtrAmtRate_E1 = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_E1");
		ccyCd_E1 = (EZDBStringItem)newItem("ccyCd_E1");
		contrXsCopyPk_E1 = (EZDBBigDecimalItem)newItem("contrXsCopyPk_E1");
		xsMtrFirstFlg_E1 = (EZDBStringItem)newItem("xsMtrFirstFlg_E1");
		invFlg_E1 = (EZDBStringItem)newItem("invFlg_E1");
		xxDtlCnt_E1 = (EZDBBigDecimalItem)newItem("xxDtlCnt_E1");
		ezUpTime_E1 = (EZDBStringItem)newItem("ezUpTime_E1");
		ezUpTimeZone_E1 = (EZDBStringItem)newItem("ezUpTimeZone_E1");
		xxRecHistCratTs_E1 = (EZDBStringItem)newItem("xxRecHistCratTs_E1");
		xxRecHistCratByNm_E1 = (EZDBStringItem)newItem("xxRecHistCratByNm_E1");
		xxRecHistUpdTs_E1 = (EZDBStringItem)newItem("xxRecHistUpdTs_E1");
		xxRecHistUpdByNm_E1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_E1");
		xxRecHistTblNm_E1 = (EZDBStringItem)newItem("xxRecHistTblNm_E1");
		ezUpTime_EM = (EZDBStringItem)newItem("ezUpTime_EM");
		ezUpTimeZone_EM = (EZDBStringItem)newItem("ezUpTimeZone_EM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_E1", "xxChkBox_E1", "E1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrBllgMtrPk_E1", "dsContrBllgMtrPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbNm_E1", "mtrLbNm_E1", "E1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrBllgSchdMtrPk_E1", "dsContrBllgSchdMtrPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_E1", "dsContrBllgSchdSmryPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_E1", "dsContrBllgSchdSqNum_E1", "E1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffPk_E1", "dsContrPrcEffPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_E1", "dsContrPrcEffSqNum_E1", "E1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"perSchdNum_E1", "perSchdNum_E1", "E1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"bllgCycleUomCd_E1", "bllgCycleUomCd_E1", "E1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleUomNm_E2", "bllgCycleUomNm_E2", "E2", "99", TYPE_HANKAKUEISU, "30", null},
	{"perBllgCycleCd_E1", "perBllgCycleCd_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgSchdFromDt_E1", "bllgSchdFromDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_E1", "bllgSchdThruDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_E1", "bllgCycleCd_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_E1", "bllgCycleDescTxt_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_E1", "bllgCycleStartMth_E1", "E1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xsMtrCopyQty_E1", "xsMtrCopyQty_E1", "E1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_ES", "xsMtrCopyQty_ES", "ES", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_E1", "xsMtrAmtRate_E1", "E1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"ccyCd_E1", "ccyCd_E1", "E1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrXsCopyPk_E1", "contrXsCopyPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrFirstFlg_E1", "xsMtrFirstFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_E1", "invFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtlCnt_E1", "xxDtlCnt_E1", "E1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_E1", "ezUpTime_E1", "E1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_E1", "ezUpTimeZone_E1", "E1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_E1", "xxRecHistCratTs_E1", "E1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_E1", "xxRecHistCratByNm_E1", "E1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_E1", "xxRecHistUpdTs_E1", "E1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_E1", "xxRecHistUpdByNm_E1", "E1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_E1", "xxRecHistTblNm_E1", "E1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_EM", "ezUpTime_EM", "EM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_EM", "ezUpTimeZone_EM", "EM", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_E1
        {"MTR_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_E1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_E1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_E1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_E1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_E1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_E1
        {"PER_SCHD_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_E1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_E1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_E2
        {"PER_BLLG_CYCLE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_E1
        {"BLLG_SCHD_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdFromDt_E1
        {"BLLG_SCHD_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdThruDt_E1
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_E1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_E1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_E1
        {"XS_MTR_COPY_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_E1
        {"XS_MTR_COPY_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_ES
        {"XS_MTR_AMT_RATE", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_E1
        {"CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_E1
        {"CONTR_XS_COPY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_E1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_E1
        {"INV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_E1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_E1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_E1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_E1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_E1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_E1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_E1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_EM
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_EM
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

