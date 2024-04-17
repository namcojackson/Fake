//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170529183223000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0140_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0140 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0140_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CST_UPD_HIST_HDR_PK_A1*/
	public final EZDBBigDecimalItem              mdseCstUpdHistHdrPk_A1;

    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** MDSE_CST_UPD_TP_CD_A1*/
	public final EZDBStringItem              mdseCstUpdTpCd_A1;

    /** MDSE_CST_UPD_TP_NM_A1*/
	public final EZDBStringItem              mdseCstUpdTpNm_A1;

    /** XX_SCR_ITEM_54_TXT_A1*/
	public final EZDBStringItem              xxScrItem54Txt_A1;

    /** RQST_TOT_STD_COST_AMT_A1*/
	public final EZDBBigDecimalItem              rqstTotStdCostAmt_A1;

    /** RQST_TOT_STD_COST_AMT_SB*/
	public final EZDBBigDecimalItem              rqstTotStdCostAmt_SB;

    /** MDSE_CST_UPD_EFF_FROM_DT_A1*/
	public final EZDBDateItem              mdseCstUpdEffFromDt_A1;

    /** MDSE_CST_UPD_CRAT_DT_A1*/
	public final EZDBDateItem              mdseCstUpdCratDt_A1;

    /** MDSE_CST_UPD_STS_CD_A1*/
	public final EZDBStringItem              mdseCstUpdStsCd_A1;

    /** MDSE_CST_UPD_STS_NM_A1*/
	public final EZDBStringItem              mdseCstUpdStsNm_A1;

    /** MDSE_CST_UPD_GRP_TXT_A1*/
	public final EZDBStringItem              mdseCstUpdGrpTxt_A1;

    /** MDSE_CST_UPD_REF_TXT_A1*/
	public final EZDBStringItem              mdseCstUpdRefTxt_A1;

    /** MDSE_CST_UPD_CRAT_PSN_CD_A1*/
	public final EZDBStringItem              mdseCstUpdCratPsnCd_A1;

    /** XX_FULL_NM_A1*/
	public final EZDBStringItem              xxFullNm_A1;

    /** XX_DTL_CD_A1*/
	public final EZDBStringItem              xxDtlCd_A1;


	/**
	 * NMAL0140_ABMsg is constructor.
	 * The initialization when the instance of NMAL0140_ABMsg is generated.
	 */
	public NMAL0140_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0140_ABMsg is constructor.
	 * The initialization when the instance of NMAL0140_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0140_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCstUpdHistHdrPk_A1 = (EZDBBigDecimalItem)newItem("mdseCstUpdHistHdrPk_A1");
		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		mdseCstUpdTpCd_A1 = (EZDBStringItem)newItem("mdseCstUpdTpCd_A1");
		mdseCstUpdTpNm_A1 = (EZDBStringItem)newItem("mdseCstUpdTpNm_A1");
		xxScrItem54Txt_A1 = (EZDBStringItem)newItem("xxScrItem54Txt_A1");
		rqstTotStdCostAmt_A1 = (EZDBBigDecimalItem)newItem("rqstTotStdCostAmt_A1");
		rqstTotStdCostAmt_SB = (EZDBBigDecimalItem)newItem("rqstTotStdCostAmt_SB");
		mdseCstUpdEffFromDt_A1 = (EZDBDateItem)newItem("mdseCstUpdEffFromDt_A1");
		mdseCstUpdCratDt_A1 = (EZDBDateItem)newItem("mdseCstUpdCratDt_A1");
		mdseCstUpdStsCd_A1 = (EZDBStringItem)newItem("mdseCstUpdStsCd_A1");
		mdseCstUpdStsNm_A1 = (EZDBStringItem)newItem("mdseCstUpdStsNm_A1");
		mdseCstUpdGrpTxt_A1 = (EZDBStringItem)newItem("mdseCstUpdGrpTxt_A1");
		mdseCstUpdRefTxt_A1 = (EZDBStringItem)newItem("mdseCstUpdRefTxt_A1");
		mdseCstUpdCratPsnCd_A1 = (EZDBStringItem)newItem("mdseCstUpdCratPsnCd_A1");
		xxFullNm_A1 = (EZDBStringItem)newItem("xxFullNm_A1");
		xxDtlCd_A1 = (EZDBStringItem)newItem("xxDtlCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0140_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0140_ABMsgArray();
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
	{"xxScrItem54Txt_A1", "xxScrItem54Txt_A1", "A1", null, TYPE_HANKAKUEISU, "54", null},
	{"rqstTotStdCostAmt_A1", "rqstTotStdCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rqstTotStdCostAmt_SB", "rqstTotStdCostAmt_SB", "SB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mdseCstUpdEffFromDt_A1", "mdseCstUpdEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseCstUpdCratDt_A1", "mdseCstUpdCratDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseCstUpdStsCd_A1", "mdseCstUpdStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCstUpdStsNm_A1", "mdseCstUpdStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCstUpdGrpTxt_A1", "mdseCstUpdGrpTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCstUpdRefTxt_A1", "mdseCstUpdRefTxt_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCstUpdCratPsnCd_A1", "mdseCstUpdCratPsnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxFullNm_A1", "xxFullNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"xxDtlCd_A1", "xxDtlCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CST_UPD_HIST_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdHistHdrPk_A1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"MDSE_CST_UPD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpCd_A1
        {"MDSE_CST_UPD_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpNm_A1
        {"XX_SCR_ITEM_54_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem54Txt_A1
        {"RQST_TOT_STD_COST_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//rqstTotStdCostAmt_A1
        {"RQST_TOT_STD_COST_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//rqstTotStdCostAmt_SB
        {"MDSE_CST_UPD_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mdseCstUpdEffFromDt_A1
        {"MDSE_CST_UPD_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mdseCstUpdCratDt_A1
        {"MDSE_CST_UPD_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdStsCd_A1
        {"MDSE_CST_UPD_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdStsNm_A1
        {"MDSE_CST_UPD_GRP_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdGrpTxt_A1
        {"MDSE_CST_UPD_REF_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdRefTxt_A1
        {"MDSE_CST_UPD_CRAT_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdCratPsnCd_A1
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_A1
        {"XX_DTL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCd_A1
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

