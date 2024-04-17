//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161219011322000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1400_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1400 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1400_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RMNF_RTL_WH_CD_A1*/
	public final EZDBStringItem              rmnfRtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDBStringItem              rtlWhNm_A1;

    /** RMNF_RTL_SWH_CD_A1*/
	public final EZDBStringItem              rmnfRtlSwhCd_A1;

    /** RTL_SWH_NM_A1*/
	public final EZDBStringItem              rtlSwhNm_A1;

    /** RMNF_ORD_NUM_A1*/
	public final EZDBStringItem              rmnfOrdNum_A1;

    /** RMNF_ORD_STS_CD_A1*/
	public final EZDBStringItem              rmnfOrdStsCd_A1;

    /** OWNR_TECH_TOC_CD_A1*/
	public final EZDBStringItem              ownrTechTocCd_A1;

    /** TECH_NM_A1*/
	public final EZDBStringItem              techNm_A1;

    /** RMNF_START_DT_A1*/
	public final EZDBDateItem              rmnfStartDt_A1;

    /** RMNF_END_DT_A1*/
	public final EZDBDateItem              rmnfEndDt_A1;

    /** RMNF_MAIN_UNIT_SER_NUM_A1*/
	public final EZDBStringItem              rmnfMainUnitSerNum_A1;

    /** T_MDL_NM_A1*/
	public final EZDBStringItem              t_MdlNm_A1;

    /** RMNF_MAIN_UNIT_MDSE_CD_A1*/
	public final EZDBStringItem              rmnfMainUnitMdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** RMNF_ORD_STS_DESC_TXT_A1*/
	public final EZDBStringItem              rmnfOrdStsDescTxt_A1;

    /** RMNF_PRT_USG_COST_AMT_A1*/
	public final EZDBBigDecimalItem              rmnfPrtUsgCostAmt_A1;

    /** RMNF_TOT_LBOR_COST_AMT_A1*/
	public final EZDBBigDecimalItem              rmnfTotLborCostAmt_A1;

    /** RMNF_OTH_COST_AMT_A1*/
	public final EZDBBigDecimalItem              rmnfOthCostAmt_A1;

    /** RMNF_TOT_COST_AMT_A1*/
	public final EZDBBigDecimalItem              rmnfTotCostAmt_A1;


	/**
	 * NPAL1400_ABMsg is constructor.
	 * The initialization when the instance of NPAL1400_ABMsg is generated.
	 */
	public NPAL1400_ABMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1400_ABMsg is constructor.
	 * The initialization when the instance of NPAL1400_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1400_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rmnfRtlWhCd_A1 = (EZDBStringItem)newItem("rmnfRtlWhCd_A1");
		rtlWhNm_A1 = (EZDBStringItem)newItem("rtlWhNm_A1");
		rmnfRtlSwhCd_A1 = (EZDBStringItem)newItem("rmnfRtlSwhCd_A1");
		rtlSwhNm_A1 = (EZDBStringItem)newItem("rtlSwhNm_A1");
		rmnfOrdNum_A1 = (EZDBStringItem)newItem("rmnfOrdNum_A1");
		rmnfOrdStsCd_A1 = (EZDBStringItem)newItem("rmnfOrdStsCd_A1");
		ownrTechTocCd_A1 = (EZDBStringItem)newItem("ownrTechTocCd_A1");
		techNm_A1 = (EZDBStringItem)newItem("techNm_A1");
		rmnfStartDt_A1 = (EZDBDateItem)newItem("rmnfStartDt_A1");
		rmnfEndDt_A1 = (EZDBDateItem)newItem("rmnfEndDt_A1");
		rmnfMainUnitSerNum_A1 = (EZDBStringItem)newItem("rmnfMainUnitSerNum_A1");
		t_MdlNm_A1 = (EZDBStringItem)newItem("t_MdlNm_A1");
		rmnfMainUnitMdseCd_A1 = (EZDBStringItem)newItem("rmnfMainUnitMdseCd_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		rmnfOrdStsDescTxt_A1 = (EZDBStringItem)newItem("rmnfOrdStsDescTxt_A1");
		rmnfPrtUsgCostAmt_A1 = (EZDBBigDecimalItem)newItem("rmnfPrtUsgCostAmt_A1");
		rmnfTotLborCostAmt_A1 = (EZDBBigDecimalItem)newItem("rmnfTotLborCostAmt_A1");
		rmnfOthCostAmt_A1 = (EZDBBigDecimalItem)newItem("rmnfOthCostAmt_A1");
		rmnfTotCostAmt_A1 = (EZDBBigDecimalItem)newItem("rmnfTotCostAmt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1400_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1400_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rmnfRtlWhCd_A1", "rmnfRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rmnfRtlSwhCd_A1", "rmnfRtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_A1", "rtlSwhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"rmnfOrdNum_A1", "rmnfOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"rmnfOrdStsCd_A1", "rmnfOrdStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"ownrTechTocCd_A1", "ownrTechTocCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_A1", "techNm_A1", "A1", null, TYPE_HANKAKUEISU, "45", null},
	{"rmnfStartDt_A1", "rmnfStartDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfEndDt_A1", "rmnfEndDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfMainUnitSerNum_A1", "rmnfMainUnitSerNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm_A1", "t_MdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rmnfMainUnitMdseCd_A1", "rmnfMainUnitMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"rmnfOrdStsDescTxt_A1", "rmnfOrdStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rmnfPrtUsgCostAmt_A1", "rmnfPrtUsgCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rmnfTotLborCostAmt_A1", "rmnfTotLborCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rmnfOthCostAmt_A1", "rmnfOthCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rmnfTotCostAmt_A1", "rmnfTotCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RMNF_RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfRtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"RMNF_RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfRtlSwhCd_A1
        {"RTL_SWH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_A1
        {"RMNF_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdNum_A1
        {"RMNF_ORD_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsCd_A1
        {"OWNR_TECH_TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ownrTechTocCd_A1
        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_A1
        {"RMNF_START_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rmnfStartDt_A1
        {"RMNF_END_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rmnfEndDt_A1
        {"RMNF_MAIN_UNIT_SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMainUnitSerNum_A1
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A1
        {"RMNF_MAIN_UNIT_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMainUnitMdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"RMNF_ORD_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsDescTxt_A1
        {"RMNF_PRT_USG_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfPrtUsgCostAmt_A1
        {"RMNF_TOT_LBOR_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTotLborCostAmt_A1
        {"RMNF_OTH_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOthCostAmt_A1
        {"RMNF_TOT_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTotCostAmt_A1
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
