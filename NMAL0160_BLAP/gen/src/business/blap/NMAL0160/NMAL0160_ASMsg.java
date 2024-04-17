//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161110091804000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0160_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0160 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0160_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CST_UPD_HIST_HDR_PK_A1*/
	public final EZDSBigDecimalItem              mdseCstUpdHistHdrPk_A1;

    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDSStringItem              mdseDescShortTxt_A1;

    /** MDSE_CST_UPD_TP_CD_A1*/
	public final EZDSStringItem              mdseCstUpdTpCd_A1;

    /** MDSE_CST_UPD_TP_NM_A1*/
	public final EZDSStringItem              mdseCstUpdTpNm_A1;

    /** MDSE_CST_UPD_GRP_TXT_A1*/
	public final EZDSStringItem              mdseCstUpdGrpTxt_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDSStringItem              coaMdseTpCd_A1;

    /** COA_PROD_CD_A1*/
	public final EZDSStringItem              coaProdCd_A1;

    /** THIS_MTH_TOT_STD_COST_AMT_A1*/
	public final EZDSBigDecimalItem              thisMthTotStdCostAmt_A1;

    /** RQST_TOT_STD_COST_AMT_A1*/
	public final EZDSBigDecimalItem              rqstTotStdCostAmt_A1;

    /** INVTY_QTY_A1*/
	public final EZDSBigDecimalItem              invtyQty_A1;

    /** XX_EST_SC_TOT_COST_AMT_A1*/
	public final EZDSBigDecimalItem              xxEstScTotCostAmt_A1;

    /** MDSE_CST_UPD_STS_CD_A1*/
	public final EZDSStringItem              mdseCstUpdStsCd_A1;

    /** MDSE_CST_UPD_EFF_FROM_DT_A1*/
	public final EZDSDateItem              mdseCstUpdEffFromDt_A1;

    /** MDSE_CST_UPD_CRAT_DT_A1*/
	public final EZDSDateItem              mdseCstUpdCratDt_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;

    /** _EZUpdateDateTime_M1*/
	public final EZDSStringItem              ezUpTime_M1;

    /** _EZUpTimeZone_M1*/
	public final EZDSStringItem              ezUpTimeZone_M1;

    /** _EZUpdateDateTime_D1*/
	public final EZDSStringItem              ezUpTime_D1;

    /** _EZUpTimeZone_D1*/
	public final EZDSStringItem              ezUpTimeZone_D1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDSStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDSStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDSStringItem              xxRecHistTblNm_A1;

    /** XX_REC_HIST_CRAT_TS_H1*/
	public final EZDSStringItem              xxRecHistCratTs_H1;

    /** XX_REC_HIST_CRAT_BY_NM_H1*/
	public final EZDSStringItem              xxRecHistCratByNm_H1;

    /** XX_REC_HIST_UPD_TS_H1*/
	public final EZDSStringItem              xxRecHistUpdTs_H1;

    /** XX_REC_HIST_UPD_BY_NM_H1*/
	public final EZDSStringItem              xxRecHistUpdByNm_H1;

    /** XX_REC_HIST_TBL_NM_H1*/
	public final EZDSStringItem              xxRecHistTblNm_H1;

    /** XX_REC_HIST_CRAT_BY_NM_B1*/
	public final EZDSStringItem              xxRecHistCratByNm_B1;

    /** XX_REC_HIST_UPD_BY_NM_B1*/
	public final EZDSStringItem              xxRecHistUpdByNm_B1;


	/**
	 * NMAL0160_ASMsg is constructor.
	 * The initialization when the instance of NMAL0160_ASMsg is generated.
	 */
	public NMAL0160_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0160_ASMsg is constructor.
	 * The initialization when the instance of NMAL0160_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0160_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCstUpdHistHdrPk_A1 = (EZDSBigDecimalItem)newItem("mdseCstUpdHistHdrPk_A1");
		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDSStringItem)newItem("mdseDescShortTxt_A1");
		mdseCstUpdTpCd_A1 = (EZDSStringItem)newItem("mdseCstUpdTpCd_A1");
		mdseCstUpdTpNm_A1 = (EZDSStringItem)newItem("mdseCstUpdTpNm_A1");
		mdseCstUpdGrpTxt_A1 = (EZDSStringItem)newItem("mdseCstUpdGrpTxt_A1");
		coaMdseTpCd_A1 = (EZDSStringItem)newItem("coaMdseTpCd_A1");
		coaProdCd_A1 = (EZDSStringItem)newItem("coaProdCd_A1");
		thisMthTotStdCostAmt_A1 = (EZDSBigDecimalItem)newItem("thisMthTotStdCostAmt_A1");
		rqstTotStdCostAmt_A1 = (EZDSBigDecimalItem)newItem("rqstTotStdCostAmt_A1");
		invtyQty_A1 = (EZDSBigDecimalItem)newItem("invtyQty_A1");
		xxEstScTotCostAmt_A1 = (EZDSBigDecimalItem)newItem("xxEstScTotCostAmt_A1");
		mdseCstUpdStsCd_A1 = (EZDSStringItem)newItem("mdseCstUpdStsCd_A1");
		mdseCstUpdEffFromDt_A1 = (EZDSDateItem)newItem("mdseCstUpdEffFromDt_A1");
		mdseCstUpdCratDt_A1 = (EZDSDateItem)newItem("mdseCstUpdCratDt_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
		ezUpTime_M1 = (EZDSStringItem)newItem("ezUpTime_M1");
		ezUpTimeZone_M1 = (EZDSStringItem)newItem("ezUpTimeZone_M1");
		ezUpTime_D1 = (EZDSStringItem)newItem("ezUpTime_D1");
		ezUpTimeZone_D1 = (EZDSStringItem)newItem("ezUpTimeZone_D1");
		xxRecHistCratTs_A1 = (EZDSStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDSStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDSStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDSStringItem)newItem("xxRecHistTblNm_A1");
		xxRecHistCratTs_H1 = (EZDSStringItem)newItem("xxRecHistCratTs_H1");
		xxRecHistCratByNm_H1 = (EZDSStringItem)newItem("xxRecHistCratByNm_H1");
		xxRecHistUpdTs_H1 = (EZDSStringItem)newItem("xxRecHistUpdTs_H1");
		xxRecHistUpdByNm_H1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_H1");
		xxRecHistTblNm_H1 = (EZDSStringItem)newItem("xxRecHistTblNm_H1");
		xxRecHistCratByNm_B1 = (EZDSStringItem)newItem("xxRecHistCratByNm_B1");
		xxRecHistUpdByNm_B1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0160_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0160_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCstUpdHistHdrPk_A1", "mdseCstUpdHistHdrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseCstUpdTpCd_A1", "mdseCstUpdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCstUpdTpNm_A1", "mdseCstUpdTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCstUpdGrpTxt_A1", "mdseCstUpdGrpTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaMdseTpCd_A1", "coaMdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProdCd_A1", "coaProdCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"thisMthTotStdCostAmt_A1", "thisMthTotStdCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rqstTotStdCostAmt_A1", "rqstTotStdCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invtyQty_A1", "invtyQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxEstScTotCostAmt_A1", "xxEstScTotCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mdseCstUpdStsCd_A1", "mdseCstUpdStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCstUpdEffFromDt_A1", "mdseCstUpdEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseCstUpdCratDt_A1", "mdseCstUpdCratDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_M1", "ezUpTime_M1", "M1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_M1", "ezUpTimeZone_M1", "M1", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_D1", "ezUpTime_D1", "D1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D1", "ezUpTimeZone_D1", "D1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxRecHistCratTs_H1", "xxRecHistCratTs_H1", "H1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_H1", "xxRecHistCratByNm_H1", "H1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_H1", "xxRecHistUpdTs_H1", "H1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_H1", "xxRecHistUpdByNm_H1", "H1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_H1", "xxRecHistTblNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxRecHistCratByNm_B1", "xxRecHistCratByNm_B1", "B1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdByNm_B1", "xxRecHistUpdByNm_B1", "B1", null, TYPE_HANKAKUEISU, "150", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CST_UPD_HIST_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdHistHdrPk_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"MDSE_CST_UPD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpCd_A1
        {"MDSE_CST_UPD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpNm_A1
        {"MDSE_CST_UPD_GRP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdGrpTxt_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"THIS_MTH_TOT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thisMthTotStdCostAmt_A1
        {"RQST_TOT_STD_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstTotStdCostAmt_A1
        {"INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyQty_A1
        {"XX_EST_SC_TOT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEstScTotCostAmt_A1
        {"MDSE_CST_UPD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdStsCd_A1
        {"MDSE_CST_UPD_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdEffFromDt_A1
        {"MDSE_CST_UPD_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdCratDt_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_M1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_M1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_H1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_H1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_H1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_H1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_H1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B1
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
