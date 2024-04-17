//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171226035306000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_HBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0360_HBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** DS_CONTR_BLLG_MTR_PK_H1*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk_H1;

    /** MTR_LB_NM_H1*/
	public final EZDBStringItem              mtrLbNm_H1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_H1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdMtrPk_H1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_H1*/
	public final EZDBBigDecimalItem              dsContrBllgSchdSmryPk_H1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_H1*/
	public final EZDBStringItem              dsContrBllgSchdSqNum_H1;

    /** DS_CONTR_PRC_EFF_PK_H1*/
	public final EZDBBigDecimalItem              dsContrPrcEffPk_H1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_H1*/
	public final EZDBBigDecimalItem              dsContrPrcEffSqNum_H1;

    /** PER_SCHD_NUM_H1*/
	public final EZDBBigDecimalItem              perSchdNum_H1;

    /** BLLG_CYCLE_UOM_CD_H1*/
	public final EZDBStringItemArray              bllgCycleUomCd_H1;

    /** BLLG_CYCLE_UOM_NM_H2*/
	public final EZDBStringItemArray              bllgCycleUomNm_H2;

    /** PER_BLLG_CYCLE_CD_H1*/
	public final EZDBStringItem              perBllgCycleCd_H1;

    /** BLLG_SCHD_FROM_DT_H1*/
	public final EZDBDateItem              bllgSchdFromDt_H1;

    /** BLLG_SCHD_THRU_DT_H1*/
	public final EZDBDateItem              bllgSchdThruDt_H1;

    /** BLLG_CYCLE_CD_H1*/
	public final EZDBStringItem              bllgCycleCd_H1;

    /** BLLG_CYCLE_DESC_TXT_H1*/
	public final EZDBStringItem              bllgCycleDescTxt_H1;

    /** BLLG_CYCLE_START_MTH_H1*/
	public final EZDBStringItem              bllgCycleStartMth_H1;

    /** XS_MTR_COPY_QTY_H1*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_H1;

    /** XS_MTR_COPY_QTY_HS*/
	public final EZDBBigDecimalItem              xsMtrCopyQty_HS;

    /** XS_MTR_AMT_RATE_H1*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_H1;

    /** CCY_CD_H1*/
	public final EZDBStringItem              ccyCd_H1;

    /** CONTR_XS_COPY_PK_H1*/
	public final EZDBBigDecimalItem              contrXsCopyPk_H1;

    /** XS_MTR_FIRST_FLG_H1*/
	public final EZDBStringItem              xsMtrFirstFlg_H1;

    /** INV_FLG_H1*/
	public final EZDBStringItem              invFlg_H1;

    /** XX_DTL_CNT_H1*/
	public final EZDBBigDecimalItem              xxDtlCnt_H1;

    /** _EZUpdateDateTime_H1*/
	public final EZDBStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDBStringItem              ezUpTimeZone_H1;

    /** XX_REC_HIST_CRAT_TS_H1*/
	public final EZDBStringItem              xxRecHistCratTs_H1;

    /** XX_REC_HIST_CRAT_BY_NM_H1*/
	public final EZDBStringItem              xxRecHistCratByNm_H1;

    /** XX_REC_HIST_UPD_TS_H1*/
	public final EZDBStringItem              xxRecHistUpdTs_H1;

    /** XX_REC_HIST_UPD_BY_NM_H1*/
	public final EZDBStringItem              xxRecHistUpdByNm_H1;

    /** XX_REC_HIST_TBL_NM_H1*/
	public final EZDBStringItem              xxRecHistTblNm_H1;

    /** _EZUpdateDateTime_HM*/
	public final EZDBStringItem              ezUpTime_HM;

    /** _EZUpTimeZone_HM*/
	public final EZDBStringItem              ezUpTimeZone_HM;


	/**
	 * NSAL0360_HBMsg is constructor.
	 * The initialization when the instance of NSAL0360_HBMsg is generated.
	 */
	public NSAL0360_HBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_HBMsg is constructor.
	 * The initialization when the instance of NSAL0360_HBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_HBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		dsContrBllgMtrPk_H1 = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk_H1");
		mtrLbNm_H1 = (EZDBStringItem)newItem("mtrLbNm_H1");
		dsContrBllgSchdMtrPk_H1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdMtrPk_H1");
		dsContrBllgSchdSmryPk_H1 = (EZDBBigDecimalItem)newItem("dsContrBllgSchdSmryPk_H1");
		dsContrBllgSchdSqNum_H1 = (EZDBStringItem)newItem("dsContrBllgSchdSqNum_H1");
		dsContrPrcEffPk_H1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffPk_H1");
		dsContrPrcEffSqNum_H1 = (EZDBBigDecimalItem)newItem("dsContrPrcEffSqNum_H1");
		perSchdNum_H1 = (EZDBBigDecimalItem)newItem("perSchdNum_H1");
		bllgCycleUomCd_H1 = (EZDBStringItemArray)newItemArray("bllgCycleUomCd_H1");
		bllgCycleUomNm_H2 = (EZDBStringItemArray)newItemArray("bllgCycleUomNm_H2");
		perBllgCycleCd_H1 = (EZDBStringItem)newItem("perBllgCycleCd_H1");
		bllgSchdFromDt_H1 = (EZDBDateItem)newItem("bllgSchdFromDt_H1");
		bllgSchdThruDt_H1 = (EZDBDateItem)newItem("bllgSchdThruDt_H1");
		bllgCycleCd_H1 = (EZDBStringItem)newItem("bllgCycleCd_H1");
		bllgCycleDescTxt_H1 = (EZDBStringItem)newItem("bllgCycleDescTxt_H1");
		bllgCycleStartMth_H1 = (EZDBStringItem)newItem("bllgCycleStartMth_H1");
		xsMtrCopyQty_H1 = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_H1");
		xsMtrCopyQty_HS = (EZDBBigDecimalItem)newItem("xsMtrCopyQty_HS");
		xsMtrAmtRate_H1 = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_H1");
		ccyCd_H1 = (EZDBStringItem)newItem("ccyCd_H1");
		contrXsCopyPk_H1 = (EZDBBigDecimalItem)newItem("contrXsCopyPk_H1");
		xsMtrFirstFlg_H1 = (EZDBStringItem)newItem("xsMtrFirstFlg_H1");
		invFlg_H1 = (EZDBStringItem)newItem("invFlg_H1");
		xxDtlCnt_H1 = (EZDBBigDecimalItem)newItem("xxDtlCnt_H1");
		ezUpTime_H1 = (EZDBStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDBStringItem)newItem("ezUpTimeZone_H1");
		xxRecHistCratTs_H1 = (EZDBStringItem)newItem("xxRecHistCratTs_H1");
		xxRecHistCratByNm_H1 = (EZDBStringItem)newItem("xxRecHistCratByNm_H1");
		xxRecHistUpdTs_H1 = (EZDBStringItem)newItem("xxRecHistUpdTs_H1");
		xxRecHistUpdByNm_H1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_H1");
		xxRecHistTblNm_H1 = (EZDBStringItem)newItem("xxRecHistTblNm_H1");
		ezUpTime_HM = (EZDBStringItem)newItem("ezUpTime_HM");
		ezUpTimeZone_HM = (EZDBStringItem)newItem("ezUpTimeZone_HM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_HBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_HBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrBllgMtrPk_H1", "dsContrBllgMtrPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbNm_H1", "mtrLbNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrBllgSchdMtrPk_H1", "dsContrBllgSchdMtrPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_H1", "dsContrBllgSchdSmryPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_H1", "dsContrBllgSchdSqNum_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffPk_H1", "dsContrPrcEffPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_H1", "dsContrPrcEffSqNum_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"perSchdNum_H1", "perSchdNum_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"bllgCycleUomCd_H1", "bllgCycleUomCd_H1", "H1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleUomNm_H2", "bllgCycleUomNm_H2", "H2", "99", TYPE_HANKAKUEISU, "30", null},
	{"perBllgCycleCd_H1", "perBllgCycleCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgSchdFromDt_H1", "bllgSchdFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_H1", "bllgSchdThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_H1", "bllgCycleCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_H1", "bllgCycleDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_H1", "bllgCycleStartMth_H1", "H1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xsMtrCopyQty_H1", "xsMtrCopyQty_H1", "H1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_HS", "xsMtrCopyQty_HS", "HS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_H1", "xsMtrAmtRate_H1", "H1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"ccyCd_H1", "ccyCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrXsCopyPk_H1", "contrXsCopyPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrFirstFlg_H1", "xsMtrFirstFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_H1", "invFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtlCnt_H1", "xxDtlCnt_H1", "H1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_H1", "xxRecHistCratTs_H1", "H1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_H1", "xxRecHistCratByNm_H1", "H1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_H1", "xxRecHistUpdTs_H1", "H1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_H1", "xxRecHistUpdByNm_H1", "H1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_H1", "xxRecHistTblNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_HM", "ezUpTime_HM", "HM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_HM", "ezUpTimeZone_HM", "HM", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_H1
        {"MTR_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_H1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_H1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_H1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_H1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_H1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_H1
        {"PER_SCHD_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_H1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_H1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_H2
        {"PER_BLLG_CYCLE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_H1
        {"BLLG_SCHD_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdFromDt_H1
        {"BLLG_SCHD_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgSchdThruDt_H1
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_H1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_H1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_H1
        {"XS_MTR_COPY_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_H1
        {"XS_MTR_COPY_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_HS
        {"XS_MTR_AMT_RATE", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_H1
        {"CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_H1
        {"CONTR_XS_COPY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_H1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_H1
        {"INV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_H1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_H1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_H1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_H1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_H1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_H1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_H1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_HM
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_HM
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
