//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221012084143000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0350_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0350;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0350 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0350_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_BLLG_SCHD_PK_C1*/
	public final EZDSBigDecimalItem              dsContrBllgSchdPk_C1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_C1*/
	public final EZDSBigDecimalItem              dsContrBllgSchdSmryPk_C1;

    /** DS_CONTR_BLLG_MTR_PK_C1*/
	public final EZDSBigDecimalItem              dsContrBllgMtrPk_C1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_C1*/
	public final EZDSStringItem              dsContrBllgSchdSqNum_C1;

    /** DS_CONTR_BLLG_SCHD_LVL_NUM_C1*/
	public final EZDSStringItem              dsContrBllgSchdLvlNum_C1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_C1*/
	public final EZDSBigDecimalItem              dsContrPrcEffSqNum_C1;

    /** SKIP_RECOV_TP_CD_C3*/
	public final EZDSStringItem              skipRecovTpCd_C3;

    /** BLLG_SCHD_FROM_DT_C1*/
	public final EZDSDateItem              bllgSchdFromDt_C1;

    /** BLLG_SCHD_THRU_DT_C1*/
	public final EZDSDateItem              bllgSchdThruDt_C1;

    /** NEXT_BLLG_DT_C1*/
	public final EZDSDateItem              nextBllgDt_C1;

    /** NEXT_BLLG_DT_C2*/
	public final EZDSDateItem              nextBllgDt_C2;

    /** RVS_SCHD_DT_C1*/
	public final EZDSDateItem              rvsSchdDt_C1;

    /** BASE_PRC_DEAL_AMT_C1*/
	public final EZDSBigDecimalItem              basePrcDealAmt_C1;

    /** MTR_ENTRY_CPLT_FLG_C1*/
	public final EZDSStringItem              mtrEntryCpltFlg_C1;

    /** DS_BLLG_SCHD_STS_CD_C1*/
	public final EZDSStringItem              dsBllgSchdStsCd_C1;

    /** DS_BLLG_SCHD_STS_DESC_TXT_C1*/
	public final EZDSStringItem              dsBllgSchdStsDescTxt_C1;

    /** SVC_INV_NUM_C1*/
	public final EZDSStringItem              svcInvNum_C1;

    /** EIP_RPT_RQST_PK_C1*/
	public final EZDSBigDecimalItem              eipRptRqstPk_C1;

    /** INV_DT_C1*/
	public final EZDSDateItem              invDt_C1;

    /** BASE_ACTL_PRC_DEAL_AMT_C1*/
	public final EZDSBigDecimalItem              baseActlPrcDealAmt_C1;

    /** DEAL_TAX_AMT_C1*/
	public final EZDSBigDecimalItem              dealTaxAmt_C1;

    /** _EZUpdateDateTime_C1*/
	public final EZDSStringItem              ezUpTime_C1;

    /** _EZUpTimeZone_C1*/
	public final EZDSStringItem              ezUpTimeZone_C1;

    /** XX_REC_HIST_CRAT_TS_C1*/
	public final EZDSStringItem              xxRecHistCratTs_C1;

    /** XX_REC_HIST_CRAT_BY_NM_C1*/
	public final EZDSStringItem              xxRecHistCratByNm_C1;

    /** XX_REC_HIST_UPD_TS_C1*/
	public final EZDSStringItem              xxRecHistUpdTs_C1;

    /** XX_REC_HIST_UPD_BY_NM_C1*/
	public final EZDSStringItem              xxRecHistUpdByNm_C1;

    /** XX_REC_HIST_TBL_NM_C1*/
	public final EZDSStringItem              xxRecHistTblNm_C1;


	/**
	 * NSAL0350_CSMsg is constructor.
	 * The initialization when the instance of NSAL0350_CSMsg is generated.
	 */
	public NSAL0350_CSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0350_CSMsg is constructor.
	 * The initialization when the instance of NSAL0350_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0350_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrBllgSchdPk_C1 = (EZDSBigDecimalItem)newItem("dsContrBllgSchdPk_C1");
		dsContrBllgSchdSmryPk_C1 = (EZDSBigDecimalItem)newItem("dsContrBllgSchdSmryPk_C1");
		dsContrBllgMtrPk_C1 = (EZDSBigDecimalItem)newItem("dsContrBllgMtrPk_C1");
		dsContrBllgSchdSqNum_C1 = (EZDSStringItem)newItem("dsContrBllgSchdSqNum_C1");
		dsContrBllgSchdLvlNum_C1 = (EZDSStringItem)newItem("dsContrBllgSchdLvlNum_C1");
		dsContrPrcEffSqNum_C1 = (EZDSBigDecimalItem)newItem("dsContrPrcEffSqNum_C1");
		skipRecovTpCd_C3 = (EZDSStringItem)newItem("skipRecovTpCd_C3");
		bllgSchdFromDt_C1 = (EZDSDateItem)newItem("bllgSchdFromDt_C1");
		bllgSchdThruDt_C1 = (EZDSDateItem)newItem("bllgSchdThruDt_C1");
		nextBllgDt_C1 = (EZDSDateItem)newItem("nextBllgDt_C1");
		nextBllgDt_C2 = (EZDSDateItem)newItem("nextBllgDt_C2");
		rvsSchdDt_C1 = (EZDSDateItem)newItem("rvsSchdDt_C1");
		basePrcDealAmt_C1 = (EZDSBigDecimalItem)newItem("basePrcDealAmt_C1");
		mtrEntryCpltFlg_C1 = (EZDSStringItem)newItem("mtrEntryCpltFlg_C1");
		dsBllgSchdStsCd_C1 = (EZDSStringItem)newItem("dsBllgSchdStsCd_C1");
		dsBllgSchdStsDescTxt_C1 = (EZDSStringItem)newItem("dsBllgSchdStsDescTxt_C1");
		svcInvNum_C1 = (EZDSStringItem)newItem("svcInvNum_C1");
		eipRptRqstPk_C1 = (EZDSBigDecimalItem)newItem("eipRptRqstPk_C1");
		invDt_C1 = (EZDSDateItem)newItem("invDt_C1");
		baseActlPrcDealAmt_C1 = (EZDSBigDecimalItem)newItem("baseActlPrcDealAmt_C1");
		dealTaxAmt_C1 = (EZDSBigDecimalItem)newItem("dealTaxAmt_C1");
		ezUpTime_C1 = (EZDSStringItem)newItem("ezUpTime_C1");
		ezUpTimeZone_C1 = (EZDSStringItem)newItem("ezUpTimeZone_C1");
		xxRecHistCratTs_C1 = (EZDSStringItem)newItem("xxRecHistCratTs_C1");
		xxRecHistCratByNm_C1 = (EZDSStringItem)newItem("xxRecHistCratByNm_C1");
		xxRecHistUpdTs_C1 = (EZDSStringItem)newItem("xxRecHistUpdTs_C1");
		xxRecHistUpdByNm_C1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_C1");
		xxRecHistTblNm_C1 = (EZDSStringItem)newItem("xxRecHistTblNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0350_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0350_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrBllgSchdPk_C1", "dsContrBllgSchdPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_C1", "dsContrBllgSchdSmryPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_C1", "dsContrBllgMtrPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_C1", "dsContrBllgSchdSqNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrBllgSchdLvlNum_C1", "dsContrBllgSchdLvlNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffSqNum_C1", "dsContrPrcEffSqNum_C1", "C1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"skipRecovTpCd_C3", "skipRecovTpCd_C3", "C3", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgSchdFromDt_C1", "bllgSchdFromDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_C1", "bllgSchdThruDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_C1", "nextBllgDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_C2", "nextBllgDt_C2", "C2", null, TYPE_NENTSUKIHI, "8", null},
	{"rvsSchdDt_C1", "rvsSchdDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"basePrcDealAmt_C1", "basePrcDealAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrEntryCpltFlg_C1", "mtrEntryCpltFlg_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsBllgSchdStsCd_C1", "dsBllgSchdStsCd_C1", "C1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsBllgSchdStsDescTxt_C1", "dsBllgSchdStsDescTxt_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcInvNum_C1", "svcInvNum_C1", "C1", null, TYPE_HANKAKUEISU, "13", null},
	{"eipRptRqstPk_C1", "eipRptRqstPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invDt_C1", "invDt_C1", "C1", null, TYPE_NENTSUKIHI, "8", null},
	{"baseActlPrcDealAmt_C1", "baseActlPrcDealAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealTaxAmt_C1", "dealTaxAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
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

        {"DS_CONTR_BLLG_SCHD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdPk_C1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_C1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_C1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_C1
        {"DS_CONTR_BLLG_SCHD_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdLvlNum_C1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_C1
        {"SKIP_RECOV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//skipRecovTpCd_C3
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_C1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_C1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_C1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_C2
        {"RVS_SCHD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvsSchdDt_C1
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_C1
        {"MTR_ENTRY_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCpltFlg_C1
        {"DS_BLLG_SCHD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsCd_C1
        {"DS_BLLG_SCHD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsDescTxt_C1
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_C1
        {"EIP_RPT_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_C1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_C1
        {"BASE_ACTL_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseActlPrcDealAmt_C1
        {"DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealTaxAmt_C1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_C1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_C1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_C1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_C1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_C1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_C1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_C1
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
