//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171226035404000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0360_JCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0360_JCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_J1*/
	public final EZDCStringItem              xxChkBox_J1;

    /** DS_CONTR_BLLG_MTR_PK_J1*/
	public final EZDCBigDecimalItem              dsContrBllgMtrPk_J1;

    /** MTR_LB_NM_J1*/
	public final EZDCStringItem              mtrLbNm_J1;

    /** DS_CONTR_BLLG_SCHD_MTR_PK_J1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdMtrPk_J1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_J1*/
	public final EZDCBigDecimalItem              dsContrBllgSchdSmryPk_J1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_J1*/
	public final EZDCStringItem              dsContrBllgSchdSqNum_J1;

    /** DS_CONTR_PRC_EFF_PK_J1*/
	public final EZDCBigDecimalItem              dsContrPrcEffPk_J1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_J1*/
	public final EZDCBigDecimalItem              dsContrPrcEffSqNum_J1;

    /** PER_SCHD_NUM_J1*/
	public final EZDCBigDecimalItem              perSchdNum_J1;

    /** BLLG_CYCLE_UOM_CD_J1*/
	public final EZDCStringItemArray              bllgCycleUomCd_J1;

    /** BLLG_CYCLE_UOM_NM_J2*/
	public final EZDCStringItemArray              bllgCycleUomNm_J2;

    /** PER_BLLG_CYCLE_CD_J1*/
	public final EZDCStringItem              perBllgCycleCd_J1;

    /** BLLG_SCHD_FROM_DT_J1*/
	public final EZDCDateItem              bllgSchdFromDt_J1;

    /** BLLG_SCHD_THRU_DT_J1*/
	public final EZDCDateItem              bllgSchdThruDt_J1;

    /** BLLG_CYCLE_CD_J1*/
	public final EZDCStringItem              bllgCycleCd_J1;

    /** BLLG_CYCLE_DESC_TXT_J1*/
	public final EZDCStringItem              bllgCycleDescTxt_J1;

    /** BLLG_CYCLE_START_MTH_J1*/
	public final EZDCStringItem              bllgCycleStartMth_J1;

    /** XS_MTR_COPY_QTY_J1*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_J1;

    /** XS_MTR_COPY_QTY_JS*/
	public final EZDCBigDecimalItem              xsMtrCopyQty_JS;

    /** XS_MTR_AMT_RATE_J1*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_J1;

    /** CCY_CD_J1*/
	public final EZDCStringItem              ccyCd_J1;

    /** CONTR_XS_COPY_PK_J1*/
	public final EZDCBigDecimalItem              contrXsCopyPk_J1;

    /** XS_MTR_FIRST_FLG_J1*/
	public final EZDCStringItem              xsMtrFirstFlg_J1;

    /** INV_FLG_J1*/
	public final EZDCStringItem              invFlg_J1;

    /** XX_DTL_CNT_J1*/
	public final EZDCBigDecimalItem              xxDtlCnt_J1;

    /** _EZUpdateDateTime_J1*/
	public final EZDCStringItem              ezUpTime_J1;

    /** _EZUpTimeZone_J1*/
	public final EZDCStringItem              ezUpTimeZone_J1;

    /** XX_REC_HIST_CRAT_TS_J1*/
	public final EZDCStringItem              xxRecHistCratTs_J1;

    /** XX_REC_HIST_CRAT_BY_NM_J1*/
	public final EZDCStringItem              xxRecHistCratByNm_J1;

    /** XX_REC_HIST_UPD_TS_J1*/
	public final EZDCStringItem              xxRecHistUpdTs_J1;

    /** XX_REC_HIST_UPD_BY_NM_J1*/
	public final EZDCStringItem              xxRecHistUpdByNm_J1;

    /** XX_REC_HIST_TBL_NM_J1*/
	public final EZDCStringItem              xxRecHistTblNm_J1;

    /** _EZUpdateDateTime_JM*/
	public final EZDCStringItem              ezUpTime_JM;

    /** _EZUpTimeZone_JM*/
	public final EZDCStringItem              ezUpTimeZone_JM;


	/**
	 * NSAL0360_JCMsg is constructor.
	 * The initialization when the instance of NSAL0360_JCMsg is generated.
	 */
	public NSAL0360_JCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0360_JCMsg is constructor.
	 * The initialization when the instance of NSAL0360_JCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0360_JCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_J1 = (EZDCStringItem)newItem("xxChkBox_J1");
		dsContrBllgMtrPk_J1 = (EZDCBigDecimalItem)newItem("dsContrBllgMtrPk_J1");
		mtrLbNm_J1 = (EZDCStringItem)newItem("mtrLbNm_J1");
		dsContrBllgSchdMtrPk_J1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdMtrPk_J1");
		dsContrBllgSchdSmryPk_J1 = (EZDCBigDecimalItem)newItem("dsContrBllgSchdSmryPk_J1");
		dsContrBllgSchdSqNum_J1 = (EZDCStringItem)newItem("dsContrBllgSchdSqNum_J1");
		dsContrPrcEffPk_J1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffPk_J1");
		dsContrPrcEffSqNum_J1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffSqNum_J1");
		perSchdNum_J1 = (EZDCBigDecimalItem)newItem("perSchdNum_J1");
		bllgCycleUomCd_J1 = (EZDCStringItemArray)newItemArray("bllgCycleUomCd_J1");
		bllgCycleUomNm_J2 = (EZDCStringItemArray)newItemArray("bllgCycleUomNm_J2");
		perBllgCycleCd_J1 = (EZDCStringItem)newItem("perBllgCycleCd_J1");
		bllgSchdFromDt_J1 = (EZDCDateItem)newItem("bllgSchdFromDt_J1");
		bllgSchdThruDt_J1 = (EZDCDateItem)newItem("bllgSchdThruDt_J1");
		bllgCycleCd_J1 = (EZDCStringItem)newItem("bllgCycleCd_J1");
		bllgCycleDescTxt_J1 = (EZDCStringItem)newItem("bllgCycleDescTxt_J1");
		bllgCycleStartMth_J1 = (EZDCStringItem)newItem("bllgCycleStartMth_J1");
		xsMtrCopyQty_J1 = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_J1");
		xsMtrCopyQty_JS = (EZDCBigDecimalItem)newItem("xsMtrCopyQty_JS");
		xsMtrAmtRate_J1 = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_J1");
		ccyCd_J1 = (EZDCStringItem)newItem("ccyCd_J1");
		contrXsCopyPk_J1 = (EZDCBigDecimalItem)newItem("contrXsCopyPk_J1");
		xsMtrFirstFlg_J1 = (EZDCStringItem)newItem("xsMtrFirstFlg_J1");
		invFlg_J1 = (EZDCStringItem)newItem("invFlg_J1");
		xxDtlCnt_J1 = (EZDCBigDecimalItem)newItem("xxDtlCnt_J1");
		ezUpTime_J1 = (EZDCStringItem)newItem("ezUpTime_J1");
		ezUpTimeZone_J1 = (EZDCStringItem)newItem("ezUpTimeZone_J1");
		xxRecHistCratTs_J1 = (EZDCStringItem)newItem("xxRecHistCratTs_J1");
		xxRecHistCratByNm_J1 = (EZDCStringItem)newItem("xxRecHistCratByNm_J1");
		xxRecHistUpdTs_J1 = (EZDCStringItem)newItem("xxRecHistUpdTs_J1");
		xxRecHistUpdByNm_J1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_J1");
		xxRecHistTblNm_J1 = (EZDCStringItem)newItem("xxRecHistTblNm_J1");
		ezUpTime_JM = (EZDCStringItem)newItem("ezUpTime_JM");
		ezUpTimeZone_JM = (EZDCStringItem)newItem("ezUpTimeZone_JM");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0360_JCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0360_JCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_J1", "xxChkBox_J1", "J1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrBllgMtrPk_J1", "dsContrBllgMtrPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbNm_J1", "mtrLbNm_J1", "J1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrBllgSchdMtrPk_J1", "dsContrBllgSchdMtrPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_J1", "dsContrBllgSchdSmryPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_J1", "dsContrBllgSchdSqNum_J1", "J1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffPk_J1", "dsContrPrcEffPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_J1", "dsContrPrcEffSqNum_J1", "J1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"perSchdNum_J1", "perSchdNum_J1", "J1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"bllgCycleUomCd_J1", "bllgCycleUomCd_J1", "J1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleUomNm_J2", "bllgCycleUomNm_J2", "J2", "99", TYPE_HANKAKUEISU, "30", null},
	{"perBllgCycleCd_J1", "perBllgCycleCd_J1", "J1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgSchdFromDt_J1", "bllgSchdFromDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_J1", "bllgSchdThruDt_J1", "J1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_J1", "bllgCycleCd_J1", "J1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_J1", "bllgCycleDescTxt_J1", "J1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_J1", "bllgCycleStartMth_J1", "J1", null, TYPE_HANKAKUSUJI, "2", null},
	{"xsMtrCopyQty_J1", "xsMtrCopyQty_J1", "J1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrCopyQty_JS", "xsMtrCopyQty_JS", "JS", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_J1", "xsMtrAmtRate_J1", "J1", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"ccyCd_J1", "ccyCd_J1", "J1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrXsCopyPk_J1", "contrXsCopyPk_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrFirstFlg_J1", "xsMtrFirstFlg_J1", "J1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_J1", "invFlg_J1", "J1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtlCnt_J1", "xxDtlCnt_J1", "J1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_J1", "ezUpTime_J1", "J1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_J1", "ezUpTimeZone_J1", "J1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_J1", "xxRecHistCratTs_J1", "J1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_J1", "xxRecHistCratByNm_J1", "J1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_J1", "xxRecHistUpdTs_J1", "J1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_J1", "xxRecHistUpdByNm_J1", "J1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_J1", "xxRecHistTblNm_J1", "J1", null, TYPE_HANKAKUEISU, "60", null},
	{"ezUpTime_JM", "ezUpTime_JM", "JM", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_JM", "ezUpTimeZone_JM", "JM", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_J1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_J1
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_J1
        {"DS_CONTR_BLLG_SCHD_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdMtrPk_J1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_J1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_J1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_J1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_J1
        {"PER_SCHD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perSchdNum_J1
        {"BLLG_CYCLE_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomCd_J1
        {"BLLG_CYCLE_UOM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleUomNm_J2
        {"PER_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//perBllgCycleCd_J1
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_J1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_J1
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_J1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_J1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_J1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_J1
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_JS
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_J1
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_J1
        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_J1
        {"XS_MTR_FIRST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_J1
        {"INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_J1
        {"XX_DTL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_J1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_J1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_J1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_J1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_J1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_J1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_J1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_J1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_JM
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_JM
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
