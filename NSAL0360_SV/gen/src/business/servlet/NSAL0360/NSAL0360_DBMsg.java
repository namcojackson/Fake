//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171226035306000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_DBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0360_DBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_D1*/
	public final EZDBStringItem              xxChkBox_D1;

    /** DS_CONTR_BLLG_MTR_PK_D1*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk_D1;

    /** MTR_LB_NM_D1*/
	public final EZDBStringItem              mtrLbNm_D1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_D1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdMtrPk_D1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_D1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdSmryPk_D1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_D1*/
	public final EZDBStringItem              dsContrBllgSchdSqNum_D1;

    /** DS_CONTR_PRC_EFF_PK_D1*/
	public final EZDBBigDecimalItem              dsContrPrcEffPk_D1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_D1*/
	public final EZDBBigDecimalItem              dsContrPrcEffSqNum_D1;

    /** PER_SCHD_NUM_D1*/
	public final EZDBBigDecimalItem              perSchdNum_D1;

    /** BLLG_CYCLE_UOM_CD_D1*/
	public final EZDBStringItemArray              bllgCycleUomCd_D1;

    /** BLLG_CYCLE_UOM_NM_D2*/
	public final EZDBStringItemArray              bllgCycleUomNm_D2;

    /** PER_BLLG_CYCLE_CD_D1*/
	public final EZDBStringItem              perBllgCycleCd_D1;

    /** BLLG_SCHD_FROM_DT_D1*/
	public final EZDBDateItem              bllgSchdFromDt_D1;

    /** BLLG_SCHD_THRU_DT_D1*/
	public final EZDBDateItem              bllgSchdThruDt_D1;

    /** BLLG_CYCLE_CD_D1*/
	public final EZDBStringItem              bllgCycleCd_D1;

    /** BLLG_CYCLE_DESC_TXT_D1*/
	public final EZDBStringItem              bllgCycleDescTxt_D1;

    /** BLLG_CYCLE_START_MTH_D1*/
	public final EZDBStringItem              bllgCycleStartMth_D1;

    /** XS_MTR_COPY_QTY_D1*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_D1;

    /** XS_MTR_COPY_QTY_DS*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_DS;

    /** XS_MTR_AMT_RATE_D1*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_D1;

    /** CCY_CD_D1*/
	public final EZDBStringItem              ccyCd_D1;

    /** CONTR_XS_COPY_PK_D1*/
	public final EZDBBigDecimalItem              contrXsCopyPk_D1;

    /** XS_MTR_FIRST_FLG_D1*/
	public final EZDBStringItem              xsMtrFirstFlg_D1;

    /** INV_FLG_D1*/
	public final EZDBStringItem              invFlg_D1;

    /** XX_DTL_CNT_D1*/
	public final EZDBBigDecimalItem              xxDtlCnt_D1;

    /** _EZUpdateDateTime_D1*/
	public final EZDBStringItem              ezUpTime_D1;

    /** _EZUpTimeZone_D1*/
	public final EZDBStringItem              ezUpTimeZone_D1;

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

    /** _EZUpdateDateTime_DM*/
	public final EZDBStringItem              ezUpTime_DM;

    /** _EZUpTimeZone_DM*/
	public final EZDBStringItem              ezUpTimeZone_DM;


	/**
	 * NSAL0360_DBMsg is constructor.
	 * The initialization when the instance of NSAL0360_DBMsg is generated.
	 */
	public NSAL0360_DBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_DBMsg is constructor.
	 * The initialization when the instance of NSAL0360_DBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_DBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_D1 = (EZDBStringItem)newItem("xxChkBox_D1");
		dsContrBllgMtrPk_D1 = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk_D1");
		mtrLbNm_D1 = (EZDBStringItem)newItem("mtrLbNm_D1");
		dsContrBllgSchdMtrPk_D1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdMtrPk_D1");
		dsContrBllgSchdSmryPk_D1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdSmryPk_D1");
		dsContrBllgSchdSqNum_D1 = (EZDBStringItem)newItem("dsContrBllgSchdSqNum_D1");
		dsContrPrcEffPk_D1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffPk_D1");
		dsContrPrcEffSqNum_D1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffSqNum_D1");
		perSchdNum_D1 = (EZDBBigDecimalItem)newItem("perSchdNum_D1");
		bllgCycleUomCd_D1 = (EZDBStringItemArray)newItemArray("bllgCycleUomCd_D1");
		bllgCycleUomNm_D2 = (EZDBStringItemArray)newItemArray("bllgCycleUomNm_D2");
		perBllgCycleCd_D1 = (EZDBStringItem)newItem("perBllgCycleCd_D1");
		bllgSchdFromDt_D1 = (EZDBDateItem)newItem("bllgSchdFromDt_D1");
		bllgSchdThruDt_D1 = (EZDBDateItem)newItem("bllgSchdThruDt_D1");
		bllgCycleCd_D1 = (EZDBStringItem)newItem("bllgCycleCd_D1");
		bllgCycleDescTxt_D1 = (EZDBStringItem)newItem("bllgCycleDescTxt_D1");
		bllgCycleStartMth_D1 = (EZDBStringItem)newItem("bllgCycleStartMth_D1");
		xsMtrCopyQty_D1 = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_D1");
		xsMtrCopyQty_DS = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_DS");
		xsMtrAmtRate_D1 = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_D1");
		ccyCd_D1 = (EZDBStringItem)newItem("ccyCd_D1");
		contrXsCopyPk_D1 = (EZDBBigDecimalItem)newItem("contrXsCopyPk_D1");
		xsMtrFirstFlg_D1 = (EZDBStringItem)newItem("xsMtrFirstFlg_D1");
		invFlg_D1 = (EZDBStringItem)newItem("invFlg_D1");
		xxDtlCnt_D1 = (EZDBBigDecimalItem)newItem("xxDtlCnt_D1");
		ezUpTime_D1 = (EZDBStringItem)newItem("ezUpTime_D1");
		ezUpTimeZone_D1 = (EZDBStringItem)newItem("ezUpTimeZone_D1");
		xxRecHistCratTs_D1 = (EZDBStringItem)newItem("xxRecHistCratTs_D1");
		xxRecHistCratByNm_D1 = (EZDBStringItem)newItem("xxRecHistCratByNm_D1");
		xxRecHistUpdTs_D1 = (EZDBStringItem)newItem("xxRecHistUpdTs_D1");
		xxRecHistUpdByNm_D1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_D1");
		xxRecHistTblNm_D1 = (EZDBStringItem)newItem("xxRecHistTblNm_D1");
		ezUpTime_DM = (EZDBStringItem)newItem("ezUpTime_DM");
		ezUpTimeZone_DM = (EZDBStringItem)newItem("ezUpTimeZone_DM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_DBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_DBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_D1", "xxChkBox_D1", "D1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrBllgMtrPk_D1", "dsContrBllgMtrPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbNm_D1", "mtrLbNm_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrBllgSchdMtrPk_D1", "dsContrBllgSchdMtrPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_D1", "dsContrBllgSchdSmryPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_D1", "dsContrBllgSchdSqNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffPk_D1", "dsContrPrcEffPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_D1", "dsContrPrcEffSqNum_D1", "D1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"perSchdNum_D1", "perSchdNum_D1", "D1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"bllgCycleUomCd_D1", "bllgCycleUomCd_D1", "D1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleUomNm_D2", "bllgCycleUomNm_D2", "D2", "99", TYPE_HANKAKUEISU, "30", null},
	{"perBllgCycleCd_D1", "perBllgCycleCd_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgSchdFromDt_D1", "bllgSchdFromDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_D1", "bllgSchdThruDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_D1", "bllgCycleCd_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_D1", "bllgCycleDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_D1", "bllgCycleStartMth_D1", "D1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xsMtrCopyQty_D1", "xsMtrCopyQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_DS", "xsMtrCopyQty_DS", "DS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_D1", "xsMtrAmtRate_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"ccyCd_D1", "ccyCd_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrXsCopyPk_D1", "contrXsCopyPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrFirstFlg_D1", "xsMtrFirstFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_D1", "invFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtlCnt_D1", "xxDtlCnt_D1", "D1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_D1", "ezUpTime_D1", "D1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D1", "ezUpTimeZone_D1", "D1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_D1", "xxRecHistCratTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_D1", "xxRecHistCratByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_D1", "xxRecHistUpdTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_D1", "xxRecHistUpdByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_D1", "xxRecHistTblNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_DM", "ezUpTime_DM", "DM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_DM", "ezUpTimeZone_DM", "DM", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_D1
        {"MTR_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_D1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_D1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_D1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_D1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_D1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_D1
        {"PER_SCHD_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_D1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_D1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_D2
        {"PER_BLLG_CYCLE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_D1
        {"BLLG_SCHD_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdFromDt_D1
        {"BLLG_SCHD_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdThruDt_D1
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_D1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_D1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_D1
        {"XS_MTR_COPY_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_D1
        {"XS_MTR_COPY_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_DS
        {"XS_MTR_AMT_RATE", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_D1
        {"CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_D1
        {"CONTR_XS_COPY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_D1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_D1
        {"INV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_D1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_D1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_D1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_D1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_D1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_D1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_D1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DM
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DM
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
