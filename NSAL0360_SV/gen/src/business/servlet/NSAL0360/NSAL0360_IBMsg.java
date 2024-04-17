//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171226035306000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_IBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0360_IBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_I1*/
	public final EZDBStringItem              xxChkBox_I1;

    /** DS_CONTR_BLLG_MTR_PK_I1*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk_I1;

    /** MTR_LB_NM_I1*/
	public final EZDBStringItem              mtrLbNm_I1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_I1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdMtrPk_I1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_I1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdSmryPk_I1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_I1*/
	public final EZDBStringItem              dsContrBllgSchdSqNum_I1;

    /** DS_CONTR_PRC_EFF_PK_I1*/
	public final EZDBBigDecimalItem              dsContrPrcEffPk_I1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_I1*/
	public final EZDBBigDecimalItem              dsContrPrcEffSqNum_I1;

    /** PER_SCHD_NUM_I1*/
	public final EZDBBigDecimalItem              perSchdNum_I1;

    /** BLLG_CYCLE_UOM_CD_I1*/
	public final EZDBStringItemArray              bllgCycleUomCd_I1;

    /** BLLG_CYCLE_UOM_NM_I2*/
	public final EZDBStringItemArray              bllgCycleUomNm_I2;

    /** PER_BLLG_CYCLE_CD_I1*/
	public final EZDBStringItem              perBllgCycleCd_I1;

    /** BLLG_SCHD_FROM_DT_I1*/
	public final EZDBDateItem              bllgSchdFromDt_I1;

    /** BLLG_SCHD_THRU_DT_I1*/
	public final EZDBDateItem              bllgSchdThruDt_I1;

    /** BLLG_CYCLE_CD_I1*/
	public final EZDBStringItem              bllgCycleCd_I1;

    /** BLLG_CYCLE_DESC_TXT_I1*/
	public final EZDBStringItem              bllgCycleDescTxt_I1;

    /** BLLG_CYCLE_START_MTH_I1*/
	public final EZDBStringItem              bllgCycleStartMth_I1;

    /** XS_MTR_COPY_QTY_I1*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_I1;

    /** XS_MTR_COPY_QTY_IS*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_IS;

    /** XS_MTR_AMT_RATE_I1*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_I1;

    /** CCY_CD_I1*/
	public final EZDBStringItem              ccyCd_I1;

    /** CONTR_XS_COPY_PK_I1*/
	public final EZDBBigDecimalItem              contrXsCopyPk_I1;

    /** XS_MTR_FIRST_FLG_I1*/
	public final EZDBStringItem              xsMtrFirstFlg_I1;

    /** INV_FLG_I1*/
	public final EZDBStringItem              invFlg_I1;

    /** XX_DTL_CNT_I1*/
	public final EZDBBigDecimalItem              xxDtlCnt_I1;

    /** _EZUpdateDateTime_I1*/
	public final EZDBStringItem              ezUpTime_I1;

    /** _EZUpTimeZone_I1*/
	public final EZDBStringItem              ezUpTimeZone_I1;

    /** XX_REC_HIST_CRAT_TS_I1*/
	public final EZDBStringItem              xxRecHistCratTs_I1;

    /** XX_REC_HIST_CRAT_BY_NM_I1*/
	public final EZDBStringItem              xxRecHistCratByNm_I1;

    /** XX_REC_HIST_UPD_TS_I1*/
	public final EZDBStringItem              xxRecHistUpdTs_I1;

    /** XX_REC_HIST_UPD_BY_NM_I1*/
	public final EZDBStringItem              xxRecHistUpdByNm_I1;

    /** XX_REC_HIST_TBL_NM_I1*/
	public final EZDBStringItem              xxRecHistTblNm_I1;

    /** _EZUpdateDateTime_IM*/
	public final EZDBStringItem              ezUpTime_IM;

    /** _EZUpTimeZone_IM*/
	public final EZDBStringItem              ezUpTimeZone_IM;


	/**
	 * NSAL0360_IBMsg is constructor.
	 * The initialization when the instance of NSAL0360_IBMsg is generated.
	 */
	public NSAL0360_IBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_IBMsg is constructor.
	 * The initialization when the instance of NSAL0360_IBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_IBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_I1 = (EZDBStringItem)newItem("xxChkBox_I1");
		dsContrBllgMtrPk_I1 = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk_I1");
		mtrLbNm_I1 = (EZDBStringItem)newItem("mtrLbNm_I1");
		dsContrBllgSchdMtrPk_I1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdMtrPk_I1");
		dsContrBllgSchdSmryPk_I1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdSmryPk_I1");
		dsContrBllgSchdSqNum_I1 = (EZDBStringItem)newItem("dsContrBllgSchdSqNum_I1");
		dsContrPrcEffPk_I1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffPk_I1");
		dsContrPrcEffSqNum_I1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffSqNum_I1");
		perSchdNum_I1 = (EZDBBigDecimalItem)newItem("perSchdNum_I1");
		bllgCycleUomCd_I1 = (EZDBStringItemArray)newItemArray("bllgCycleUomCd_I1");
		bllgCycleUomNm_I2 = (EZDBStringItemArray)newItemArray("bllgCycleUomNm_I2");
		perBllgCycleCd_I1 = (EZDBStringItem)newItem("perBllgCycleCd_I1");
		bllgSchdFromDt_I1 = (EZDBDateItem)newItem("bllgSchdFromDt_I1");
		bllgSchdThruDt_I1 = (EZDBDateItem)newItem("bllgSchdThruDt_I1");
		bllgCycleCd_I1 = (EZDBStringItem)newItem("bllgCycleCd_I1");
		bllgCycleDescTxt_I1 = (EZDBStringItem)newItem("bllgCycleDescTxt_I1");
		bllgCycleStartMth_I1 = (EZDBStringItem)newItem("bllgCycleStartMth_I1");
		xsMtrCopyQty_I1 = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_I1");
		xsMtrCopyQty_IS = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_IS");
		xsMtrAmtRate_I1 = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_I1");
		ccyCd_I1 = (EZDBStringItem)newItem("ccyCd_I1");
		contrXsCopyPk_I1 = (EZDBBigDecimalItem)newItem("contrXsCopyPk_I1");
		xsMtrFirstFlg_I1 = (EZDBStringItem)newItem("xsMtrFirstFlg_I1");
		invFlg_I1 = (EZDBStringItem)newItem("invFlg_I1");
		xxDtlCnt_I1 = (EZDBBigDecimalItem)newItem("xxDtlCnt_I1");
		ezUpTime_I1 = (EZDBStringItem)newItem("ezUpTime_I1");
		ezUpTimeZone_I1 = (EZDBStringItem)newItem("ezUpTimeZone_I1");
		xxRecHistCratTs_I1 = (EZDBStringItem)newItem("xxRecHistCratTs_I1");
		xxRecHistCratByNm_I1 = (EZDBStringItem)newItem("xxRecHistCratByNm_I1");
		xxRecHistUpdTs_I1 = (EZDBStringItem)newItem("xxRecHistUpdTs_I1");
		xxRecHistUpdByNm_I1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_I1");
		xxRecHistTblNm_I1 = (EZDBStringItem)newItem("xxRecHistTblNm_I1");
		ezUpTime_IM = (EZDBStringItem)newItem("ezUpTime_IM");
		ezUpTimeZone_IM = (EZDBStringItem)newItem("ezUpTimeZone_IM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_IBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_IBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_I1", "xxChkBox_I1", "I1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrBllgMtrPk_I1", "dsContrBllgMtrPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbNm_I1", "mtrLbNm_I1", "I1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrBllgSchdMtrPk_I1", "dsContrBllgSchdMtrPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_I1", "dsContrBllgSchdSmryPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_I1", "dsContrBllgSchdSqNum_I1", "I1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffPk_I1", "dsContrPrcEffPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_I1", "dsContrPrcEffSqNum_I1", "I1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"perSchdNum_I1", "perSchdNum_I1", "I1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"bllgCycleUomCd_I1", "bllgCycleUomCd_I1", "I1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleUomNm_I2", "bllgCycleUomNm_I2", "I2", "99", TYPE_HANKAKUEISU, "30", null},
	{"perBllgCycleCd_I1", "perBllgCycleCd_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgSchdFromDt_I1", "bllgSchdFromDt_I1", "I1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_I1", "bllgSchdThruDt_I1", "I1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_I1", "bllgCycleCd_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_I1", "bllgCycleDescTxt_I1", "I1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_I1", "bllgCycleStartMth_I1", "I1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xsMtrCopyQty_I1", "xsMtrCopyQty_I1", "I1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_IS", "xsMtrCopyQty_IS", "IS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_I1", "xsMtrAmtRate_I1", "I1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"ccyCd_I1", "ccyCd_I1", "I1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrXsCopyPk_I1", "contrXsCopyPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrFirstFlg_I1", "xsMtrFirstFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_I1", "invFlg_I1", "I1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtlCnt_I1", "xxDtlCnt_I1", "I1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_I1", "ezUpTime_I1", "I1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_I1", "ezUpTimeZone_I1", "I1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_I1", "xxRecHistCratTs_I1", "I1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_I1", "xxRecHistCratByNm_I1", "I1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_I1", "xxRecHistUpdTs_I1", "I1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_I1", "xxRecHistUpdByNm_I1", "I1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_I1", "xxRecHistTblNm_I1", "I1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_IM", "ezUpTime_IM", "IM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_IM", "ezUpTimeZone_IM", "IM", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_I1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_I1
        {"MTR_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_I1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_I1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_I1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_I1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_I1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_I1
        {"PER_SCHD_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_I1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_I1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_I2
        {"PER_BLLG_CYCLE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_I1
        {"BLLG_SCHD_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdFromDt_I1
        {"BLLG_SCHD_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdThruDt_I1
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_I1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_I1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_I1
        {"XS_MTR_COPY_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_I1
        {"XS_MTR_COPY_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_IS
        {"XS_MTR_AMT_RATE", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_I1
        {"CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_I1
        {"CONTR_XS_COPY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_I1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_I1
        {"INV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_I1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_I1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_I1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_I1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_I1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_I1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_I1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_I1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_I1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_IM
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_IM
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
