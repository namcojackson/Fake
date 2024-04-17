//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240325170114000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1320_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1320 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1320_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_D*/
	public final EZDCBigDecimalItem              dsContrPk_D;

    /** DS_CONTR_DTL_PK_D*/
	public final EZDCBigDecimalItem              dsContrDtlPk_D;

    /** SHELL_LINE_NUM_D*/
	public final EZDCBigDecimalItem              shellLineNum_D;

    /** SVC_PGM_MDSE_CD_D*/
	public final EZDCStringItem              svcPgmMdseCd_D;

    /** MDSE_DESC_SHORT_TXT_D*/
	public final EZDCStringItem              mdseDescShortTxt_D;

    /** PRC_SVC_CONTR_TP_CD_D*/
	public final EZDCStringItem              prcSvcContrTpCd_D;

    /** PRC_SVC_PLN_TP_CD_D*/
	public final EZDCStringItem              prcSvcPlnTpCd_D;

    /** BILL_WITH_EQUIP_FLG_D*/
	public final EZDCStringItem              billWithEquipFlg_D;

    /** FROM_PER_MTH_NUM_D*/
	public final EZDCBigDecimalItem              fromPerMthNum_D;

    /** TO_PER_MTH_NUM_D*/
	public final EZDCBigDecimalItem              toPerMthNum_D;

    /** TERM_MTH_AOT_D*/
	public final EZDCBigDecimalItem              termMthAot_D;

    /** DS_CONTR_CATG_CD_D*/
	public final EZDCStringItem              dsContrCatgCd_D;

    /** BASE_BLLG_CYCLE_CD_D*/
	public final EZDCStringItem              baseBllgCycleCd_D;

    /** USG_BLLG_CYCLE_CD_D*/
	public final EZDCStringItem              usgBllgCycleCd_D;

    /** BILL_BY_TP_CD_D*/
	public final EZDCStringItem              billByTpCd_D;

    /** XX_TOT_AMT_DS*/
	public final EZDCBigDecimalItem              xxTotAmt_DS;

    /** DS_ACCT_NM_D*/
	public final EZDCStringItem              dsAcctNm_D;

    /** DS_ACCT_NUM_D*/
	public final EZDCStringItem              dsAcctNum_D;

    /** XX_GENL_FLD_AREA_TXT_DB*/
	public final EZDCStringItem              xxGenlFldAreaTxt_DB;

    /** SOLD_TO_CUST_LOC_CD_D*/
	public final EZDCStringItem              soldToCustLocCd_D;

    /** DS_ORD_POSN_NUM_D*/
	public final EZDCStringItem              dsOrdPosnNum_D;

    /** T_MDL_NM_D*/
	public final EZDCStringItem              t_MdlNm_D;

    /** MDL_ID_D*/
	public final EZDCBigDecimalItem              mdlId_D;

    /** DPLY_LINE_NUM_D*/
	public final EZDCStringItem              dplyLineNum_D;

    /** CPO_DTL_LINE_NUM_D*/
	public final EZDCStringItem              cpoDtlLineNum_D;

    /** CPO_DTL_LINE_SUB_NUM_D*/
	public final EZDCStringItem              cpoDtlLineSubNum_D;

    /** MDSE_CD_D*/
	public final EZDCStringItem              mdseCd_D;

    /** XX_GENL_FLD_AREA_TXT_DS*/
	public final EZDCStringItem              xxGenlFldAreaTxt_DS;

    /** MTR_READ_METH_CD_D*/
	public final EZDCStringItem              mtrReadMethCd_D;

    /** CUST_ISS_PO_NUM_D*/
	public final EZDCStringItem              custIssPoNum_D;

    /** CUST_ISS_PO_DT_D*/
	public final EZDCDateItem              custIssPoDt_D;

    /** XX_TOT_AMT_D1*/
	public final EZDCBigDecimalItem              xxTotAmt_D1;

    /** XX_TOT_AMT_D2*/
	public final EZDCBigDecimalItem              xxTotAmt_D2;

    /** XX_TOT_AMT_D3*/
	public final EZDCBigDecimalItem              xxTotAmt_D3;

    /** XX_TOT_AMT_D4*/
	public final EZDCBigDecimalItem              xxTotAmt_D4;

    /** CR_REBIL_CD_D*/
	public final EZDCStringItem              crRebilCd_D;

    /** SHPG_STS_CD_D*/
	public final EZDCStringItem              shpgStsCd_D;


	/**
	 * NSAL1320_DCMsg is constructor.
	 * The initialization when the instance of NSAL1320_DCMsg is generated.
	 */
	public NSAL1320_DCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1320_DCMsg is constructor.
	 * The initialization when the instance of NSAL1320_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1320_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_D = (EZDCBigDecimalItem)newItem("dsContrPk_D");
		dsContrDtlPk_D = (EZDCBigDecimalItem)newItem("dsContrDtlPk_D");
		shellLineNum_D = (EZDCBigDecimalItem)newItem("shellLineNum_D");
		svcPgmMdseCd_D = (EZDCStringItem)newItem("svcPgmMdseCd_D");
		mdseDescShortTxt_D = (EZDCStringItem)newItem("mdseDescShortTxt_D");
		prcSvcContrTpCd_D = (EZDCStringItem)newItem("prcSvcContrTpCd_D");
		prcSvcPlnTpCd_D = (EZDCStringItem)newItem("prcSvcPlnTpCd_D");
		billWithEquipFlg_D = (EZDCStringItem)newItem("billWithEquipFlg_D");
		fromPerMthNum_D = (EZDCBigDecimalItem)newItem("fromPerMthNum_D");
		toPerMthNum_D = (EZDCBigDecimalItem)newItem("toPerMthNum_D");
		termMthAot_D = (EZDCBigDecimalItem)newItem("termMthAot_D");
		dsContrCatgCd_D = (EZDCStringItem)newItem("dsContrCatgCd_D");
		baseBllgCycleCd_D = (EZDCStringItem)newItem("baseBllgCycleCd_D");
		usgBllgCycleCd_D = (EZDCStringItem)newItem("usgBllgCycleCd_D");
		billByTpCd_D = (EZDCStringItem)newItem("billByTpCd_D");
		xxTotAmt_DS = (EZDCBigDecimalItem)newItem("xxTotAmt_DS");
		dsAcctNm_D = (EZDCStringItem)newItem("dsAcctNm_D");
		dsAcctNum_D = (EZDCStringItem)newItem("dsAcctNum_D");
		xxGenlFldAreaTxt_DB = (EZDCStringItem)newItem("xxGenlFldAreaTxt_DB");
		soldToCustLocCd_D = (EZDCStringItem)newItem("soldToCustLocCd_D");
		dsOrdPosnNum_D = (EZDCStringItem)newItem("dsOrdPosnNum_D");
		t_MdlNm_D = (EZDCStringItem)newItem("t_MdlNm_D");
		mdlId_D = (EZDCBigDecimalItem)newItem("mdlId_D");
		dplyLineNum_D = (EZDCStringItem)newItem("dplyLineNum_D");
		cpoDtlLineNum_D = (EZDCStringItem)newItem("cpoDtlLineNum_D");
		cpoDtlLineSubNum_D = (EZDCStringItem)newItem("cpoDtlLineSubNum_D");
		mdseCd_D = (EZDCStringItem)newItem("mdseCd_D");
		xxGenlFldAreaTxt_DS = (EZDCStringItem)newItem("xxGenlFldAreaTxt_DS");
		mtrReadMethCd_D = (EZDCStringItem)newItem("mtrReadMethCd_D");
		custIssPoNum_D = (EZDCStringItem)newItem("custIssPoNum_D");
		custIssPoDt_D = (EZDCDateItem)newItem("custIssPoDt_D");
		xxTotAmt_D1 = (EZDCBigDecimalItem)newItem("xxTotAmt_D1");
		xxTotAmt_D2 = (EZDCBigDecimalItem)newItem("xxTotAmt_D2");
		xxTotAmt_D3 = (EZDCBigDecimalItem)newItem("xxTotAmt_D3");
		xxTotAmt_D4 = (EZDCBigDecimalItem)newItem("xxTotAmt_D4");
		crRebilCd_D = (EZDCStringItem)newItem("crRebilCd_D");
		shpgStsCd_D = (EZDCStringItem)newItem("shpgStsCd_D");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1320_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1320_DCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_D", "dsContrPk_D", "D", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_D", "dsContrDtlPk_D", "D", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"shellLineNum_D", "shellLineNum_D", "D", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"svcPgmMdseCd_D", "svcPgmMdseCd_D", "D", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_D", "mdseDescShortTxt_D", "D", null, TYPE_HANKAKUEISU, "250", null},
	{"prcSvcContrTpCd_D", "prcSvcContrTpCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcPlnTpCd_D", "prcSvcPlnTpCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"billWithEquipFlg_D", "billWithEquipFlg_D", "D", null, TYPE_HANKAKUEISU, "1", null},
	{"fromPerMthNum_D", "fromPerMthNum_D", "D", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"toPerMthNum_D", "toPerMthNum_D", "D", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"termMthAot_D", "termMthAot_D", "D", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dsContrCatgCd_D", "dsContrCatgCd_D", "D", null, TYPE_HANKAKUEISU, "3", null},
	{"baseBllgCycleCd_D", "baseBllgCycleCd_D", "D", null, TYPE_HANKAKUEISU, "1", null},
	{"usgBllgCycleCd_D", "usgBllgCycleCd_D", "D", null, TYPE_HANKAKUEISU, "1", null},
	{"billByTpCd_D", "billByTpCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTotAmt_DS", "xxTotAmt_DS", "DS", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsAcctNm_D", "dsAcctNm_D", "D", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNum_D", "dsAcctNum_D", "D", null, TYPE_HANKAKUEISU, "20", null},
	{"xxGenlFldAreaTxt_DB", "xxGenlFldAreaTxt_DB", "DB", null, TYPE_HANKAKUEISU, "1000", null},
	{"soldToCustLocCd_D", "soldToCustLocCd_D", "D", null, TYPE_HANKAKUEISU, "20", null},
	{"dsOrdPosnNum_D", "dsOrdPosnNum_D", "D", null, TYPE_HANKAKUEISU, "6", null},
	{"t_MdlNm_D", "t_MdlNm_D", "D", null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId_D", "mdlId_D", "D", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"dplyLineNum_D", "dplyLineNum_D", "D", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlLineNum_D", "cpoDtlLineNum_D", "D", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_D", "cpoDtlLineSubNum_D", "D", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_D", "mdseCd_D", "D", null, TYPE_HANKAKUEISU, "16", null},
	{"xxGenlFldAreaTxt_DS", "xxGenlFldAreaTxt_DS", "DS", null, TYPE_HANKAKUEISU, "1000", null},
	{"mtrReadMethCd_D", "mtrReadMethCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum_D", "custIssPoNum_D", "D", null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt_D", "custIssPoDt_D", "D", null, TYPE_NENTSUKIHI, "8", null},
	{"xxTotAmt_D1", "xxTotAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_D2", "xxTotAmt_D2", "D2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_D3", "xxTotAmt_D3", "D3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_D4", "xxTotAmt_D4", "D4", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crRebilCd_D", "crRebilCd_D", "D", null, TYPE_HANKAKUEISU, "20", null},
	{"shpgStsCd_D", "shpgStsCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_D
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_D
        {"SHELL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shellLineNum_D
        {"SVC_PGM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd_D
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_D
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd_D
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd_D
        {"BILL_WITH_EQUIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billWithEquipFlg_D
        {"FROM_PER_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromPerMthNum_D
        {"TO_PER_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toPerMthNum_D
        {"TERM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termMthAot_D
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_D
        {"BASE_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgCycleCd_D
        {"USG_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgBllgCycleCd_D
        {"BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billByTpCd_D
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_DS
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_D
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_D
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_DB
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_D
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_D
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_D
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_D
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_D
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_D
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_D
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_D
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_DS
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_D
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_D
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt_D
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_D1
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_D2
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_D3
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_D4
        {"CR_REBIL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd_D
        {"SHPG_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd_D
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

