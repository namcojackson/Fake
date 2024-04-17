//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221012084143000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0350_ESMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0350_ESMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_BLLG_SCHD_PK_E1*/
	public final EZDSBigDecimalItem              dsContrBllgSchdPk_E1;

    /** DS_CONTR_BLLG_SCHD_SMRY_PK_E1*/
	public final EZDSBigDecimalItem              dsContrBllgSchdSmryPk_E1;

    /** DS_CONTR_BLLG_MTR_PK_E1*/
	public final EZDSBigDecimalItem              dsContrBllgMtrPk_E1;

    /** DS_CONTR_BLLG_SCHD_SQ_NUM_E1*/
	public final EZDSStringItem              dsContrBllgSchdSqNum_E1;

    /** DS_CONTR_BLLG_SCHD_LVL_NUM_E1*/
	public final EZDSStringItem              dsContrBllgSchdLvlNum_E1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_E1*/
	public final EZDSBigDecimalItem              dsContrPrcEffSqNum_E1;

    /** SKIP_RECOV_TP_CD_E3*/
	public final EZDSStringItem              skipRecovTpCd_E3;

    /** BLLG_SCHD_FROM_DT_E1*/
	public final EZDSDateItem              bllgSchdFromDt_E1;

    /** BLLG_SCHD_THRU_DT_E1*/
	public final EZDSDateItem              bllgSchdThruDt_E1;

    /** NEXT_BLLG_DT_E1*/
	public final EZDSDateItem              nextBllgDt_E1;

    /** NEXT_BLLG_DT_E2*/
	public final EZDSDateItem              nextBllgDt_E2;

    /** RVS_SCHD_DT_E1*/
	public final EZDSDateItem              rvsSchdDt_E1;

    /** BASE_PRC_DEAL_AMT_E1*/
	public final EZDSBigDecimalItem              basePrcDealAmt_E1;

    /** MTR_ENTRY_CPLT_FLG_E1*/
	public final EZDSStringItem              mtrEntryCpltFlg_E1;

    /** DS_BLLG_SCHD_STS_CD_E1*/
	public final EZDSStringItem              dsBllgSchdStsCd_E1;

    /** DS_BLLG_SCHD_STS_DESC_TXT_E1*/
	public final EZDSStringItem              dsBllgSchdStsDescTxt_E1;

    /** SVC_INV_NUM_E1*/
	public final EZDSStringItem              svcInvNum_E1;

    /** EIP_RPT_RQST_PK_E1*/
	public final EZDSBigDecimalItem              eipRptRqstPk_E1;

    /** INV_DT_E1*/
	public final EZDSDateItem              invDt_E1;

    /** BASE_ACTL_PRC_DEAL_AMT_E1*/
	public final EZDSBigDecimalItem              baseActlPrcDealAmt_E1;

    /** DEAL_TAX_AMT_E1*/
	public final EZDSBigDecimalItem              dealTaxAmt_E1;

    /** _EZUpdateDateTime_E1*/
	public final EZDSStringItem              ezUpTime_E1;

    /** _EZUpTimeZone_E1*/
	public final EZDSStringItem              ezUpTimeZone_E1;

    /** XX_REC_HIST_CRAT_TS_E1*/
	public final EZDSStringItem              xxRecHistCratTs_E1;

    /** XX_REC_HIST_CRAT_BY_NM_E1*/
	public final EZDSStringItem              xxRecHistCratByNm_E1;

    /** XX_REC_HIST_UPD_TS_E1*/
	public final EZDSStringItem              xxRecHistUpdTs_E1;

    /** XX_REC_HIST_UPD_BY_NM_E1*/
	public final EZDSStringItem              xxRecHistUpdByNm_E1;

    /** XX_REC_HIST_TBL_NM_E1*/
	public final EZDSStringItem              xxRecHistTblNm_E1;


	/**
	 * NSAL0350_ESMsg is constructor.
	 * The initialization when the instance of NSAL0350_ESMsg is generated.
	 */
	public NSAL0350_ESMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0350_ESMsg is constructor.
	 * The initialization when the instance of NSAL0350_ESMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0350_ESMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrBllgSchdPk_E1 = (EZDSBigDecimalItem)newItem("dsContrBllgSchdPk_E1");
		dsContrBllgSchdSmryPk_E1 = (EZDSBigDecimalItem)newItem("dsContrBllgSchdSmryPk_E1");
		dsContrBllgMtrPk_E1 = (EZDSBigDecimalItem)newItem("dsContrBllgMtrPk_E1");
		dsContrBllgSchdSqNum_E1 = (EZDSStringItem)newItem("dsContrBllgSchdSqNum_E1");
		dsContrBllgSchdLvlNum_E1 = (EZDSStringItem)newItem("dsContrBllgSchdLvlNum_E1");
		dsContrPrcEffSqNum_E1 = (EZDSBigDecimalItem)newItem("dsContrPrcEffSqNum_E1");
		skipRecovTpCd_E3 = (EZDSStringItem)newItem("skipRecovTpCd_E3");
		bllgSchdFromDt_E1 = (EZDSDateItem)newItem("bllgSchdFromDt_E1");
		bllgSchdThruDt_E1 = (EZDSDateItem)newItem("bllgSchdThruDt_E1");
		nextBllgDt_E1 = (EZDSDateItem)newItem("nextBllgDt_E1");
		nextBllgDt_E2 = (EZDSDateItem)newItem("nextBllgDt_E2");
		rvsSchdDt_E1 = (EZDSDateItem)newItem("rvsSchdDt_E1");
		basePrcDealAmt_E1 = (EZDSBigDecimalItem)newItem("basePrcDealAmt_E1");
		mtrEntryCpltFlg_E1 = (EZDSStringItem)newItem("mtrEntryCpltFlg_E1");
		dsBllgSchdStsCd_E1 = (EZDSStringItem)newItem("dsBllgSchdStsCd_E1");
		dsBllgSchdStsDescTxt_E1 = (EZDSStringItem)newItem("dsBllgSchdStsDescTxt_E1");
		svcInvNum_E1 = (EZDSStringItem)newItem("svcInvNum_E1");
		eipRptRqstPk_E1 = (EZDSBigDecimalItem)newItem("eipRptRqstPk_E1");
		invDt_E1 = (EZDSDateItem)newItem("invDt_E1");
		baseActlPrcDealAmt_E1 = (EZDSBigDecimalItem)newItem("baseActlPrcDealAmt_E1");
		dealTaxAmt_E1 = (EZDSBigDecimalItem)newItem("dealTaxAmt_E1");
		ezUpTime_E1 = (EZDSStringItem)newItem("ezUpTime_E1");
		ezUpTimeZone_E1 = (EZDSStringItem)newItem("ezUpTimeZone_E1");
		xxRecHistCratTs_E1 = (EZDSStringItem)newItem("xxRecHistCratTs_E1");
		xxRecHistCratByNm_E1 = (EZDSStringItem)newItem("xxRecHistCratByNm_E1");
		xxRecHistUpdTs_E1 = (EZDSStringItem)newItem("xxRecHistUpdTs_E1");
		xxRecHistUpdByNm_E1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_E1");
		xxRecHistTblNm_E1 = (EZDSStringItem)newItem("xxRecHistTblNm_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0350_ESMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0350_ESMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrBllgSchdPk_E1", "dsContrBllgSchdPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSmryPk_E1", "dsContrBllgSchdSmryPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_E1", "dsContrBllgMtrPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgSchdSqNum_E1", "dsContrBllgSchdSqNum_E1", "E1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrBllgSchdLvlNum_E1", "dsContrBllgSchdLvlNum_E1", "E1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrPrcEffSqNum_E1", "dsContrPrcEffSqNum_E1", "E1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"skipRecovTpCd_E3", "skipRecovTpCd_E3", "E3", null, TYPE_HANKAKUEISU, "2", null},
	{"bllgSchdFromDt_E1", "bllgSchdFromDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgSchdThruDt_E1", "bllgSchdThruDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_E1", "nextBllgDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"nextBllgDt_E2", "nextBllgDt_E2", "E2", null, TYPE_NENTSUKIHI, "8", null},
	{"rvsSchdDt_E1", "rvsSchdDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"basePrcDealAmt_E1", "basePrcDealAmt_E1", "E1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrEntryCpltFlg_E1", "mtrEntryCpltFlg_E1", "E1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsBllgSchdStsCd_E1", "dsBllgSchdStsCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsBllgSchdStsDescTxt_E1", "dsBllgSchdStsDescTxt_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcInvNum_E1", "svcInvNum_E1", "E1", null, TYPE_HANKAKUEISU, "13", null},
	{"eipRptRqstPk_E1", "eipRptRqstPk_E1", "E1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invDt_E1", "invDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"baseActlPrcDealAmt_E1", "baseActlPrcDealAmt_E1", "E1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealTaxAmt_E1", "dealTaxAmt_E1", "E1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezUpTime_E1", "ezUpTime_E1", "E1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_E1", "ezUpTimeZone_E1", "E1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_E1", "xxRecHistCratTs_E1", "E1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_E1", "xxRecHistCratByNm_E1", "E1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_E1", "xxRecHistUpdTs_E1", "E1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_E1", "xxRecHistUpdByNm_E1", "E1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_E1", "xxRecHistTblNm_E1", "E1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_BLLG_SCHD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdPk_E1
        {"DS_CONTR_BLLG_SCHD_SMRY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSmryPk_E1
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_E1
        {"DS_CONTR_BLLG_SCHD_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdSqNum_E1
        {"DS_CONTR_BLLG_SCHD_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgSchdLvlNum_E1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_E1
        {"SKIP_RECOV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//skipRecovTpCd_E3
        {"BLLG_SCHD_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdFromDt_E1
        {"BLLG_SCHD_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgSchdThruDt_E1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_E1
        {"NEXT_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextBllgDt_E2
        {"RVS_SCHD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvsSchdDt_E1
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_E1
        {"MTR_ENTRY_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCpltFlg_E1
        {"DS_BLLG_SCHD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsCd_E1
        {"DS_BLLG_SCHD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBllgSchdStsDescTxt_E1
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_E1
        {"EIP_RPT_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_E1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_E1
        {"BASE_ACTL_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseActlPrcDealAmt_E1
        {"DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealTaxAmt_E1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_E1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_E1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_E1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_E1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_E1
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

