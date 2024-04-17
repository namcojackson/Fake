//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171226035404000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0360 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0360_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_D1*/
	public final EZDCStringItem              xxChkBox_D1;

    /** DS_CONTR_BLLG_MTR_PK_D1*/
	public final EZDCBigDecimalItem              dsContrBllgMtrPk_D1;

    /** MTR_LB_NM_D1*/
	public final EZDCStringItem              mtrLbNm_D1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_D1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdMtrPk_D1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_D1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdSmryPk_D1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_D1*/
	public final EZDCStringItem              dsContrBllgSchdSqNum_D1;

    /** DS_CONTR_PRC_EFF_PK_D1*/
	public final EZDCBigDecimalItem              dsContrPrcEffPk_D1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_D1*/
	public final EZDCBigDecimalItem              dsContrPrcEffSqNum_D1;

    /** PER_SCHD_NUM_D1*/
	public final EZDCBigDecimalItem              perSchdNum_D1;

    /** BLLG_CYCLE_UOM_CD_D1*/
	public final EZDCStringItemArray              bllgCycleUomCd_D1;

    /** BLLG_CYCLE_UOM_NM_D2*/
	public final EZDCStringItemArray              bllgCycleUomNm_D2;

    /** PER_BLLG_CYCLE_CD_D1*/
	public final EZDCStringItem              perBllgCycleCd_D1;

    /** BLLG_SCHD_FROM_DT_D1*/
	public final EZDCDateItem              bllgSchdFromDt_D1;

    /** BLLG_SCHD_THRU_DT_D1*/
	public final EZDCDateItem              bllgSchdThruDt_D1;

    /** BLLG_CYCLE_CD_D1*/
	public final EZDCStringItem              bllgCycleCd_D1;

    /** BLLG_CYCLE_DESC_TXT_D1*/
	public final EZDCStringItem              bllgCycleDescTxt_D1;

    /** BLLG_CYCLE_START_MTH_D1*/
	public final EZDCStringItem              bllgCycleStartMth_D1;

    /** XS_MTR_COPY_QTY_D1*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_D1;

    /** XS_MTR_COPY_QTY_DS*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_DS;

    /** XS_MTR_AMT_RATE_D1*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_D1;

    /** CCY_CD_D1*/
	public final EZDCStringItem              ccyCd_D1;

    /** CONTR_XS_COPY_PK_D1*/
	public final EZDCBigDecimalItem              contrXsCopyPk_D1;

    /** XS_MTR_FIRST_FLG_D1*/
	public final EZDCStringItem              xsMtrFirstFlg_D1;

    /** INV_FLG_D1*/
	public final EZDCStringItem              invFlg_D1;

    /** XX_DTL_CNT_D1*/
	public final EZDCBigDecimalItem              xxDtlCnt_D1;

    /** _EZUpdateDateTime_D1*/
	public final EZDCStringItem              ezUpTime_D1;

    /** _EZUpTimeZone_D1*/
	public final EZDCStringItem              ezUpTimeZone_D1;

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

    /** _EZUpdateDateTime_DM*/
	public final EZDCStringItem              ezUpTime_DM;

    /** _EZUpTimeZone_DM*/
	public final EZDCStringItem              ezUpTimeZone_DM;


	/**
	 * NSAL0360_DCMsg is constructor.
	 * The initialization when the instance of NSAL0360_DCMsg is generated.
	 */
	public NSAL0360_DCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_DCMsg is constructor.
	 * The initialization when the instance of NSAL0360_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_D1 = (EZDCStringItem)newItem("xxChkBox_D1");
		dsContrBllgMtrPk_D1 = (EZDCBigDecimalItem)newItem("dsContrBllgMtrPk_D1");
		mtrLbNm_D1 = (EZDCStringItem)newItem("mtrLbNm_D1");
		dsContrBllgSchdMtrPk_D1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdMtrPk_D1");
		dsContrBllgSchdSmryPk_D1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdSmryPk_D1");
		dsContrBllgSchdSqNum_D1 = (EZDCStringItem)newItem("dsContrBllgSchdSqNum_D1");
		dsContrPrcEffPk_D1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffPk_D1");
		dsContrPrcEffSqNum_D1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffSqNum_D1");
		perSchdNum_D1 = (EZDCBigDecimalItem)newItem("perSchdNum_D1");
		bllgCycleUomCd_D1 = (EZDCStringItemArray)newItemArray("bllgCycleUomCd_D1");
		bllgCycleUomNm_D2 = (EZDCStringItemArray)newItemArray("bllgCycleUomNm_D2");
		perBllgCycleCd_D1 = (EZDCStringItem)newItem("perBllgCycleCd_D1");
		bllgSchdFromDt_D1 = (EZDCDateItem)newItem("bllgSchdFromDt_D1");
		bllgSchdThruDt_D1 = (EZDCDateItem)newItem("bllgSchdThruDt_D1");
		bllgCycleCd_D1 = (EZDCStringItem)newItem("bllgCycleCd_D1");
		bllgCycleDescTxt_D1 = (EZDCStringItem)newItem("bllgCycleDescTxt_D1");
		bllgCycleStartMth_D1 = (EZDCStringItem)newItem("bllgCycleStartMth_D1");
		xsMtrCopyQty_D1 = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_D1");
		xsMtrCopyQty_DS = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_DS");
		xsMtrAmtRate_D1 = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_D1");
		ccyCd_D1 = (EZDCStringItem)newItem("ccyCd_D1");
		contrXsCopyPk_D1 = (EZDCBigDecimalItem)newItem("contrXsCopyPk_D1");
		xsMtrFirstFlg_D1 = (EZDCStringItem)newItem("xsMtrFirstFlg_D1");
		invFlg_D1 = (EZDCStringItem)newItem("invFlg_D1");
		xxDtlCnt_D1 = (EZDCBigDecimalItem)newItem("xxDtlCnt_D1");
		ezUpTime_D1 = (EZDCStringItem)newItem("ezUpTime_D1");
		ezUpTimeZone_D1 = (EZDCStringItem)newItem("ezUpTimeZone_D1");
		xxRecHistCratTs_D1 = (EZDCStringItem)newItem("xxRecHistCratTs_D1");
		xxRecHistCratByNm_D1 = (EZDCStringItem)newItem("xxRecHistCratByNm_D1");
		xxRecHistUpdTs_D1 = (EZDCStringItem)newItem("xxRecHistUpdTs_D1");
		xxRecHistUpdByNm_D1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_D1");
		xxRecHistTblNm_D1 = (EZDCStringItem)newItem("xxRecHistTblNm_D1");
		ezUpTime_DM = (EZDCStringItem)newItem("ezUpTime_DM");
		ezUpTimeZone_DM = (EZDCStringItem)newItem("ezUpTimeZone_DM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_DCMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_D1
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_D1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_D1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_D1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_D1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_D1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_D1
        {"PER_SCHD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_D1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_D1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_D2
        {"PER_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_D1
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_D1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_D1
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_D1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_D1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_D1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_D1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_DS
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_D1
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_D1
        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_D1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_D1
        {"INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_D1
        {"XX_DTL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_D1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_D1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_D1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_D1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_D1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_D1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DM
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DM
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

