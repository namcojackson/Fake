//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171226035404000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_GCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0360_GCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_G1*/
	public final EZDCStringItem              xxChkBox_G1;

    /** DS_CONTR_BLLG_MTR_PK_G1*/
	public final EZDCBigDecimalItem              dsContrBllgMtrPk_G1;

    /** MTR_LB_NM_G1*/
	public final EZDCStringItem              mtrLbNm_G1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_G1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdMtrPk_G1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_G1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdSmryPk_G1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_G1*/
	public final EZDCStringItem              dsContrBllgSchdSqNum_G1;

    /** DS_CONTR_PRC_EFF_PK_G1*/
	public final EZDCBigDecimalItem              dsContrPrcEffPk_G1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_G1*/
	public final EZDCBigDecimalItem              dsContrPrcEffSqNum_G1;

    /** PER_SCHD_NUM_G1*/
	public final EZDCBigDecimalItem              perSchdNum_G1;

    /** BLLG_CYCLE_UOM_CD_G1*/
	public final EZDCStringItemArray              bllgCycleUomCd_G1;

    /** BLLG_CYCLE_UOM_NM_G2*/
	public final EZDCStringItemArray              bllgCycleUomNm_G2;

    /** PER_BLLG_CYCLE_CD_G1*/
	public final EZDCStringItem              perBllgCycleCd_G1;

    /** BLLG_SCHD_FROM_DT_G1*/
	public final EZDCDateItem              bllgSchdFromDt_G1;

    /** BLLG_SCHD_THRU_DT_G1*/
	public final EZDCDateItem              bllgSchdThruDt_G1;

    /** BLLG_CYCLE_CD_G1*/
	public final EZDCStringItem              bllgCycleCd_G1;

    /** BLLG_CYCLE_DESC_TXT_G1*/
	public final EZDCStringItem              bllgCycleDescTxt_G1;

    /** BLLG_CYCLE_START_MTH_G1*/
	public final EZDCStringItem              bllgCycleStartMth_G1;

    /** XS_MTR_COPY_QTY_G1*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_G1;

    /** XS_MTR_COPY_QTY_GS*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_GS;

    /** XS_MTR_AMT_RATE_G1*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_G1;

    /** CCY_CD_G1*/
	public final EZDCStringItem              ccyCd_G1;

    /** CONTR_XS_COPY_PK_G1*/
	public final EZDCBigDecimalItem              contrXsCopyPk_G1;

    /** XS_MTR_FIRST_FLG_G1*/
	public final EZDCStringItem              xsMtrFirstFlg_G1;

    /** INV_FLG_G1*/
	public final EZDCStringItem              invFlg_G1;

    /** XX_DTL_CNT_G1*/
	public final EZDCBigDecimalItem              xxDtlCnt_G1;

    /** _EZUpdateDateTime_G1*/
	public final EZDCStringItem              ezUpTime_G1;

    /** _EZUpTimeZone_G1*/
	public final EZDCStringItem              ezUpTimeZone_G1;

    /** XX_REC_HIST_CRAT_TS_G1*/
	public final EZDCStringItem              xxRecHistCratTs_G1;

    /** XX_REC_HIST_CRAT_BY_NM_G1*/
	public final EZDCStringItem              xxRecHistCratByNm_G1;

    /** XX_REC_HIST_UPD_TS_G1*/
	public final EZDCStringItem              xxRecHistUpdTs_G1;

    /** XX_REC_HIST_UPD_BY_NM_G1*/
	public final EZDCStringItem              xxRecHistUpdByNm_G1;

    /** XX_REC_HIST_TBL_NM_G1*/
	public final EZDCStringItem              xxRecHistTblNm_G1;

    /** _EZUpdateDateTime_GM*/
	public final EZDCStringItem              ezUpTime_GM;

    /** _EZUpTimeZone_GM*/
	public final EZDCStringItem              ezUpTimeZone_GM;


	/**
	 * NSAL0360_GCMsg is constructor.
	 * The initialization when the instance of NSAL0360_GCMsg is generated.
	 */
	public NSAL0360_GCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_GCMsg is constructor.
	 * The initialization when the instance of NSAL0360_GCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_GCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_G1 = (EZDCStringItem)newItem("xxChkBox_G1");
		dsContrBllgMtrPk_G1 = (EZDCBigDecimalItem)newItem("dsContrBllgMtrPk_G1");
		mtrLbNm_G1 = (EZDCStringItem)newItem("mtrLbNm_G1");
		dsContrBllgSchdMtrPk_G1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdMtrPk_G1");
		dsContrBllgSchdSmryPk_G1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdSmryPk_G1");
		dsContrBllgSchdSqNum_G1 = (EZDCStringItem)newItem("dsContrBllgSchdSqNum_G1");
		dsContrPrcEffPk_G1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffPk_G1");
		dsContrPrcEffSqNum_G1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffSqNum_G1");
		perSchdNum_G1 = (EZDCBigDecimalItem)newItem("perSchdNum_G1");
		bllgCycleUomCd_G1 = (EZDCStringItemArray)newItemArray("bllgCycleUomCd_G1");
		bllgCycleUomNm_G2 = (EZDCStringItemArray)newItemArray("bllgCycleUomNm_G2");
		perBllgCycleCd_G1 = (EZDCStringItem)newItem("perBllgCycleCd_G1");
		bllgSchdFromDt_G1 = (EZDCDateItem)newItem("bllgSchdFromDt_G1");
		bllgSchdThruDt_G1 = (EZDCDateItem)newItem("bllgSchdThruDt_G1");
		bllgCycleCd_G1 = (EZDCStringItem)newItem("bllgCycleCd_G1");
		bllgCycleDescTxt_G1 = (EZDCStringItem)newItem("bllgCycleDescTxt_G1");
		bllgCycleStartMth_G1 = (EZDCStringItem)newItem("bllgCycleStartMth_G1");
		xsMtrCopyQty_G1 = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_G1");
		xsMtrCopyQty_GS = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_GS");
		xsMtrAmtRate_G1 = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_G1");
		ccyCd_G1 = (EZDCStringItem)newItem("ccyCd_G1");
		contrXsCopyPk_G1 = (EZDCBigDecimalItem)newItem("contrXsCopyPk_G1");
		xsMtrFirstFlg_G1 = (EZDCStringItem)newItem("xsMtrFirstFlg_G1");
		invFlg_G1 = (EZDCStringItem)newItem("invFlg_G1");
		xxDtlCnt_G1 = (EZDCBigDecimalItem)newItem("xxDtlCnt_G1");
		ezUpTime_G1 = (EZDCStringItem)newItem("ezUpTime_G1");
		ezUpTimeZone_G1 = (EZDCStringItem)newItem("ezUpTimeZone_G1");
		xxRecHistCratTs_G1 = (EZDCStringItem)newItem("xxRecHistCratTs_G1");
		xxRecHistCratByNm_G1 = (EZDCStringItem)newItem("xxRecHistCratByNm_G1");
		xxRecHistUpdTs_G1 = (EZDCStringItem)newItem("xxRecHistUpdTs_G1");
		xxRecHistUpdByNm_G1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_G1");
		xxRecHistTblNm_G1 = (EZDCStringItem)newItem("xxRecHistTblNm_G1");
		ezUpTime_GM = (EZDCStringItem)newItem("ezUpTime_GM");
		ezUpTimeZone_GM = (EZDCStringItem)newItem("ezUpTimeZone_GM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_GCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_GCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_G1", "xxChkBox_G1", "G1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrBllgMtrPk_G1", "dsContrBllgMtrPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbNm_G1", "mtrLbNm_G1", "G1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrBllgSchdMtrPk_G1", "dsContrBllgSchdMtrPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_G1", "dsContrBllgSchdSmryPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_G1", "dsContrBllgSchdSqNum_G1", "G1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffPk_G1", "dsContrPrcEffPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_G1", "dsContrPrcEffSqNum_G1", "G1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"perSchdNum_G1", "perSchdNum_G1", "G1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"bllgCycleUomCd_G1", "bllgCycleUomCd_G1", "G1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleUomNm_G2", "bllgCycleUomNm_G2", "G2", "99", TYPE_HANKAKUEISU, "30", null},
	{"perBllgCycleCd_G1", "perBllgCycleCd_G1", "G1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgSchdFromDt_G1", "bllgSchdFromDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_G1", "bllgSchdThruDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_G1", "bllgCycleCd_G1", "G1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_G1", "bllgCycleDescTxt_G1", "G1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_G1", "bllgCycleStartMth_G1", "G1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xsMtrCopyQty_G1", "xsMtrCopyQty_G1", "G1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_GS", "xsMtrCopyQty_GS", "GS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_G1", "xsMtrAmtRate_G1", "G1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"ccyCd_G1", "ccyCd_G1", "G1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrXsCopyPk_G1", "contrXsCopyPk_G1", "G1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrFirstFlg_G1", "xsMtrFirstFlg_G1", "G1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_G1", "invFlg_G1", "G1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtlCnt_G1", "xxDtlCnt_G1", "G1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_G1", "ezUpTime_G1", "G1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_G1", "ezUpTimeZone_G1", "G1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_G1", "xxRecHistCratTs_G1", "G1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_G1", "xxRecHistCratByNm_G1", "G1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_G1", "xxRecHistUpdTs_G1", "G1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_G1", "xxRecHistUpdByNm_G1", "G1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_G1", "xxRecHistTblNm_G1", "G1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_GM", "ezUpTime_GM", "GM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_GM", "ezUpTimeZone_GM", "GM", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_G1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_G1
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_G1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_G1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_G1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_G1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_G1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_G1
        {"PER_SCHD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_G1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_G1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_G2
        {"PER_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_G1
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_G1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_G1
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_G1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_G1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_G1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_G1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_GS
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_G1
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_G1
        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_G1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_G1
        {"INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_G1
        {"XX_DTL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_G1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_G1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_G1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_G1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_G1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_G1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_G1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_G1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_GM
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_GM
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
