//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161201095243000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2070_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2070 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2070_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** RTL_INV_PK_A1*/
	public final EZDSBigDecimalItem              rtlInvPk_A1;

    /** RTL_INV_LINE_PK_A1*/
	public final EZDSBigDecimalItem              rtlInvLinePk_A1;

    /** RTL_INV_STS_CD_A1*/
	public final EZDSStringItem              rtlInvStsCd_A1;

    /** BILL_TO_CUST_CD_A1*/
	public final EZDSStringItem              billToCustCd_A1;

    /** SELL_TO_CUST_CD_A1*/
	public final EZDSStringItem              sellToCustCd_A1;

    /** SER_NUM_A1*/
	public final EZDSStringItem              serNum_A1;

    /** MDL_NM_A1*/
	public final EZDSStringItem              mdlNm_A1;

    /** RTL_INV_APVL_DT_A1*/
	public final EZDSDateItem              rtlInvApvlDt_A1;

    /** BLLG_PER_FROM_DT_A1*/
	public final EZDSDateItem              bllgPerFromDt_A1;

    /** BLLG_PER_THRU_DT_A1*/
	public final EZDSDateItem              bllgPerThruDt_A1;

    /** RTL_INV_LINE_NUM_A1*/
	public final EZDSStringItem              rtlInvLineNum_A1;

    /** RTL_INV_CHRG_TP_DESC_TXT_A1*/
	public final EZDSStringItem              rtlInvChrgTpDescTxt_A1;

    /** SHIP_QTY_A1*/
	public final EZDSBigDecimalItem              shipQty_A1;

    /** DEAL_GRS_UNIT_PRC_AMT_A1*/
	public final EZDSBigDecimalItem              dealGrsUnitPrcAmt_A1;

    /** SLS_TAX_RATE_A1*/
	public final EZDSBigDecimalItem              slsTaxRate_A1;

    /** BLLG_BIZ_TP_CD_A1*/
	public final EZDSStringItem              bllgBizTpCd_A1;

    /** RTL_DIV_CD_A1*/
	public final EZDSStringItem              rtlDivCd_A1;

    /** RTL_INV_NUM_A1*/
	public final EZDSStringItem              rtlInvNum_A1;

    /** ITRL_RTL_SMRY_INV_NUM_A1*/
	public final EZDSStringItem              itrlRtlSmryInvNum_A1;

    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** SVC_DLR_CD_A1*/
	public final EZDSStringItem              svcDlrCd_A1;

    /** INSTL_POST_CD_A1*/
	public final EZDSStringItem              instlPostCd_A1;

    /** INSTL_CD_A1*/
	public final EZDSStringItem              instlCd_A1;

    /** ISTL_SUB_LOC_CD_A1*/
	public final EZDSStringItem              istlSubLocCd_A1;

    /** INV_LINE_CRAT_DT_A1*/
	public final EZDSDateItem              invLineCratDt_A1;

    /** INV_LINE_MOD_DT_A1*/
	public final EZDSDateItem              invLineModDt_A1;

    /** AP_INV_ROSS_STS_CD_A1*/
	public final EZDSStringItem              apInvRossStsCd_A1;


	/**
	 * NFBL2070_ASMsg is constructor.
	 * The initialization when the instance of NFBL2070_ASMsg is generated.
	 */
	public NFBL2070_ASMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2070_ASMsg is constructor.
	 * The initialization when the instance of NFBL2070_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2070_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		rtlInvPk_A1 = (EZDSBigDecimalItem)newItem("rtlInvPk_A1");
		rtlInvLinePk_A1 = (EZDSBigDecimalItem)newItem("rtlInvLinePk_A1");
		rtlInvStsCd_A1 = (EZDSStringItem)newItem("rtlInvStsCd_A1");
		billToCustCd_A1 = (EZDSStringItem)newItem("billToCustCd_A1");
		sellToCustCd_A1 = (EZDSStringItem)newItem("sellToCustCd_A1");
		serNum_A1 = (EZDSStringItem)newItem("serNum_A1");
		mdlNm_A1 = (EZDSStringItem)newItem("mdlNm_A1");
		rtlInvApvlDt_A1 = (EZDSDateItem)newItem("rtlInvApvlDt_A1");
		bllgPerFromDt_A1 = (EZDSDateItem)newItem("bllgPerFromDt_A1");
		bllgPerThruDt_A1 = (EZDSDateItem)newItem("bllgPerThruDt_A1");
		rtlInvLineNum_A1 = (EZDSStringItem)newItem("rtlInvLineNum_A1");
		rtlInvChrgTpDescTxt_A1 = (EZDSStringItem)newItem("rtlInvChrgTpDescTxt_A1");
		shipQty_A1 = (EZDSBigDecimalItem)newItem("shipQty_A1");
		dealGrsUnitPrcAmt_A1 = (EZDSBigDecimalItem)newItem("dealGrsUnitPrcAmt_A1");
		slsTaxRate_A1 = (EZDSBigDecimalItem)newItem("slsTaxRate_A1");
		bllgBizTpCd_A1 = (EZDSStringItem)newItem("bllgBizTpCd_A1");
		rtlDivCd_A1 = (EZDSStringItem)newItem("rtlDivCd_A1");
		rtlInvNum_A1 = (EZDSStringItem)newItem("rtlInvNum_A1");
		itrlRtlSmryInvNum_A1 = (EZDSStringItem)newItem("itrlRtlSmryInvNum_A1");
		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		svcDlrCd_A1 = (EZDSStringItem)newItem("svcDlrCd_A1");
		instlPostCd_A1 = (EZDSStringItem)newItem("instlPostCd_A1");
		instlCd_A1 = (EZDSStringItem)newItem("instlCd_A1");
		istlSubLocCd_A1 = (EZDSStringItem)newItem("istlSubLocCd_A1");
		invLineCratDt_A1 = (EZDSDateItem)newItem("invLineCratDt_A1");
		invLineModDt_A1 = (EZDSDateItem)newItem("invLineModDt_A1");
		apInvRossStsCd_A1 = (EZDSStringItem)newItem("apInvRossStsCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2070_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2070_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"rtlInvPk_A1", "rtlInvPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rtlInvLinePk_A1", "rtlInvLinePk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rtlInvStsCd_A1", "rtlInvStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"billToCustCd_A1", "billToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd_A1", "sellToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdlNm_A1", "mdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"rtlInvApvlDt_A1", "rtlInvApvlDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerFromDt_A1", "bllgPerFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerThruDt_A1", "bllgPerThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"rtlInvLineNum_A1", "rtlInvLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"rtlInvChrgTpDescTxt_A1", "rtlInvChrgTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"shipQty_A1", "shipQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dealGrsUnitPrcAmt_A1", "dealGrsUnitPrcAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"slsTaxRate_A1", "slsTaxRate_A1", "A1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"bllgBizTpCd_A1", "bllgBizTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlDivCd_A1", "rtlDivCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlInvNum_A1", "rtlInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"itrlRtlSmryInvNum_A1", "itrlRtlSmryInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"svcDlrCd_A1", "svcDlrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"instlPostCd_A1", "instlPostCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"instlCd_A1", "instlCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"istlSubLocCd_A1", "istlSubLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"invLineCratDt_A1", "invLineCratDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invLineModDt_A1", "invLineModDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"apInvRossStsCd_A1", "apInvRossStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"RTL_INV_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvPk_A1
        {"RTL_INV_LINE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvLinePk_A1
        {"RTL_INV_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvStsCd_A1
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A1
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_A1
        {"RTL_INV_APVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvApvlDt_A1
        {"BLLG_PER_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerFromDt_A1
        {"BLLG_PER_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerThruDt_A1
        {"RTL_INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvLineNum_A1
        {"RTL_INV_CHRG_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvChrgTpDescTxt_A1
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty_A1
        {"DEAL_GRS_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealGrsUnitPrcAmt_A1
        {"SLS_TAX_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsTaxRate_A1
        {"BLLG_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgBizTpCd_A1
        {"RTL_DIV_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlDivCd_A1
        {"RTL_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlInvNum_A1
        {"ITRL_RTL_SMRY_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlRtlSmryInvNum_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"SVC_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcDlrCd_A1
        {"INSTL_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlPostCd_A1
        {"INSTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//instlCd_A1
        {"ISTL_SUB_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlSubLocCd_A1
        {"INV_LINE_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineCratDt_A1
        {"INV_LINE_MOD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineModDt_A1
        {"AP_INV_ROSS_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvRossStsCd_A1
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

