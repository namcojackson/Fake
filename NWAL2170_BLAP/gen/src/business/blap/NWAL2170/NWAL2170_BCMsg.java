//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180803111238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2170_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TP_CD*/
	public final EZDCStringItem              xxTpCd;

    /** CPO_SVC_CONFIG_REF_PK*/
	public final EZDCBigDecimalItem              cpoSvcConfigRefPk;

    /** DS_IMPT_SVC_LINE_NUM_B*/
	public final EZDCBigDecimalItem              dsImptSvcLineNum_B;

    /** XX_CHK_BOX_B*/
	public final EZDCStringItem              xxChkBox_B;

    /** DS_ORD_POSN_NUM*/
	public final EZDCStringItem              dsOrdPosnNum;

    /** DS_ORD_POSN_NUM_BX*/
	public final EZDCStringItem              dsOrdPosnNum_BX;

    /** XX_MODE_CD_B*/
	public final EZDCStringItem              xxModeCd_B;

    /** T_MDL_NM*/
	public final EZDCStringItem              t_MdlNm;

    /** MDL_ID*/
	public final EZDCBigDecimalItem              mdlId;

    /** DPLY_LINE_NUM*/
	public final EZDCStringItem              dplyLineNum;

    /** CPO_DTL_LINE_NUM*/
	public final EZDCStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDCStringItem              cpoDtlLineSubNum;

    /** MDSE_CD*/
	public final EZDCStringItem              mdseCd;

    /** XX_SHIP_TO_ACCT_NM_SRCH_TXT*/
	public final EZDCStringItem              xxShipToAcctNmSrchTxt;

    /** MTR_READ_METH_CD*/
	public final EZDCStringItem              mtrReadMethCd;

    /** CUST_ISS_PO_NUM*/
	public final EZDCStringItem              custIssPoNum;

    /** CUST_ISS_PO_DT*/
	public final EZDCDateItem              custIssPoDt;

    /** DS_PO_EXPR_DT*/
	public final EZDCDateItem              dsPoExprDt;

    /** CPO_SVC_PRC_PK*/
	public final EZDCBigDecimalItem              cpoSvcPrcPk;

    /** XX_FREQ_CYCLE_CNT*/
	public final EZDCBigDecimalItem              xxFreqCycleCnt;

    /** BASE_BLLG_CYCLE_DESC_TXT*/
	public final EZDCStringItem              baseBllgCycleDescTxt;

    /** XX_DEAL_AMT_TT*/
	public final EZDCBigDecimalItem              xxDealAmt_TT;

    /** XX_DEAL_AMT_EQ*/
	public final EZDCBigDecimalItem              xxDealAmt_EQ;

    /** XX_DEAL_AMT_RT*/
	public final EZDCBigDecimalItem              xxDealAmt_RT;

    /** XX_DEAL_AMT_AC*/
	public final EZDCBigDecimalItem              xxDealAmt_AC;

    /** XX_DEAL_AMT_AD*/
	public final EZDCBigDecimalItem              xxDealAmt_AD;

    /** XX_TERM_AMT_TT*/
	public final EZDCBigDecimalItem              xxTermAmt_TT;

    /** XX_TERM_AMT_EQ*/
	public final EZDCBigDecimalItem              xxTermAmt_EQ;

    /** XX_TERM_AMT_RT*/
	public final EZDCBigDecimalItem              xxTermAmt_RT;

    /** XX_TERM_AMT_AC*/
	public final EZDCBigDecimalItem              xxTermAmt_AC;

    /** XX_TERM_AMT_AD*/
	public final EZDCBigDecimalItem              xxTermAmt_AD;

    /** XX_TOT_AMT_TT*/
	public final EZDCBigDecimalItem              xxTotAmt_TT;

    /** XX_TOT_AMT_TS*/
	public final EZDCBigDecimalItem              xxTotAmt_TS;

    /** XX_TOT_AMT_TE*/
	public final EZDCBigDecimalItem              xxTotAmt_TE;

    /** XX_TOT_AMT_TC*/
	public final EZDCBigDecimalItem              xxTotAmt_TC;

    /** XX_TOT_AMT_PT*/
	public final EZDCBigDecimalItem              xxTotAmt_PT;

    /** XX_TOT_AMT_PS*/
	public final EZDCBigDecimalItem              xxTotAmt_PS;

    /** XX_TOT_AMT_PE*/
	public final EZDCBigDecimalItem              xxTotAmt_PE;

    /** XX_TOT_AMT_PC*/
	public final EZDCBigDecimalItem              xxTotAmt_PC;

    /** BLLG_CYCLE_DESC_TXT_F*/
	public final EZDCStringItem              bllgCycleDescTxt_F;

    /** CR_REBIL_CD*/
	public final EZDCStringItem              crRebilCd;

    /** DS_PO_REQ_FLG*/
	public final EZDCStringItem              dsPoReqFlg;

    /** SHPG_STS_CD*/
	public final EZDCStringItem              shpgStsCd;


	/**
	 * NWAL2170_BCMsg is constructor.
	 * The initialization when the instance of NWAL2170_BCMsg is generated.
	 */
	public NWAL2170_BCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170_BCMsg is constructor.
	 * The initialization when the instance of NWAL2170_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTpCd = (EZDCStringItem)newItem("xxTpCd");
		cpoSvcConfigRefPk = (EZDCBigDecimalItem)newItem("cpoSvcConfigRefPk");
		dsImptSvcLineNum_B = (EZDCBigDecimalItem)newItem("dsImptSvcLineNum_B");
		xxChkBox_B = (EZDCStringItem)newItem("xxChkBox_B");
		dsOrdPosnNum = (EZDCStringItem)newItem("dsOrdPosnNum");
		dsOrdPosnNum_BX = (EZDCStringItem)newItem("dsOrdPosnNum_BX");
		xxModeCd_B = (EZDCStringItem)newItem("xxModeCd_B");
		t_MdlNm = (EZDCStringItem)newItem("t_MdlNm");
		mdlId = (EZDCBigDecimalItem)newItem("mdlId");
		dplyLineNum = (EZDCStringItem)newItem("dplyLineNum");
		cpoDtlLineNum = (EZDCStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDCStringItem)newItem("cpoDtlLineSubNum");
		mdseCd = (EZDCStringItem)newItem("mdseCd");
		xxShipToAcctNmSrchTxt = (EZDCStringItem)newItem("xxShipToAcctNmSrchTxt");
		mtrReadMethCd = (EZDCStringItem)newItem("mtrReadMethCd");
		custIssPoNum = (EZDCStringItem)newItem("custIssPoNum");
		custIssPoDt = (EZDCDateItem)newItem("custIssPoDt");
		dsPoExprDt = (EZDCDateItem)newItem("dsPoExprDt");
		cpoSvcPrcPk = (EZDCBigDecimalItem)newItem("cpoSvcPrcPk");
		xxFreqCycleCnt = (EZDCBigDecimalItem)newItem("xxFreqCycleCnt");
		baseBllgCycleDescTxt = (EZDCStringItem)newItem("baseBllgCycleDescTxt");
		xxDealAmt_TT = (EZDCBigDecimalItem)newItem("xxDealAmt_TT");
		xxDealAmt_EQ = (EZDCBigDecimalItem)newItem("xxDealAmt_EQ");
		xxDealAmt_RT = (EZDCBigDecimalItem)newItem("xxDealAmt_RT");
		xxDealAmt_AC = (EZDCBigDecimalItem)newItem("xxDealAmt_AC");
		xxDealAmt_AD = (EZDCBigDecimalItem)newItem("xxDealAmt_AD");
		xxTermAmt_TT = (EZDCBigDecimalItem)newItem("xxTermAmt_TT");
		xxTermAmt_EQ = (EZDCBigDecimalItem)newItem("xxTermAmt_EQ");
		xxTermAmt_RT = (EZDCBigDecimalItem)newItem("xxTermAmt_RT");
		xxTermAmt_AC = (EZDCBigDecimalItem)newItem("xxTermAmt_AC");
		xxTermAmt_AD = (EZDCBigDecimalItem)newItem("xxTermAmt_AD");
		xxTotAmt_TT = (EZDCBigDecimalItem)newItem("xxTotAmt_TT");
		xxTotAmt_TS = (EZDCBigDecimalItem)newItem("xxTotAmt_TS");
		xxTotAmt_TE = (EZDCBigDecimalItem)newItem("xxTotAmt_TE");
		xxTotAmt_TC = (EZDCBigDecimalItem)newItem("xxTotAmt_TC");
		xxTotAmt_PT = (EZDCBigDecimalItem)newItem("xxTotAmt_PT");
		xxTotAmt_PS = (EZDCBigDecimalItem)newItem("xxTotAmt_PS");
		xxTotAmt_PE = (EZDCBigDecimalItem)newItem("xxTotAmt_PE");
		xxTotAmt_PC = (EZDCBigDecimalItem)newItem("xxTotAmt_PC");
		bllgCycleDescTxt_F = (EZDCStringItem)newItem("bllgCycleDescTxt_F");
		crRebilCd = (EZDCStringItem)newItem("crRebilCd");
		dsPoReqFlg = (EZDCStringItem)newItem("dsPoReqFlg");
		shpgStsCd = (EZDCStringItem)newItem("shpgStsCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTpCd", "xxTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"cpoSvcConfigRefPk", "cpoSvcConfigRefPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsImptSvcLineNum_B", "dsImptSvcLineNum_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdPosnNum_BX", "dsOrdPosnNum_BX", "BX", null, TYPE_HANKAKUEISU, "6", null},
	{"xxModeCd_B", "xxModeCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"dplyLineNum", "dplyLineNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlLineNum", "cpoDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum", "cpoDtlLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"xxShipToAcctNmSrchTxt", "xxShipToAcctNmSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"mtrReadMethCd", "mtrReadMethCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsPoExprDt", "dsPoExprDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cpoSvcPrcPk", "cpoSvcPrcPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxFreqCycleCnt", "xxFreqCycleCnt", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"baseBllgCycleDescTxt", "baseBllgCycleDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDealAmt_TT", "xxDealAmt_TT", "TT", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDealAmt_EQ", "xxDealAmt_EQ", "EQ", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDealAmt_RT", "xxDealAmt_RT", "RT", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDealAmt_AC", "xxDealAmt_AC", "AC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDealAmt_AD", "xxDealAmt_AD", "AD", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTermAmt_TT", "xxTermAmt_TT", "TT", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTermAmt_EQ", "xxTermAmt_EQ", "EQ", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTermAmt_RT", "xxTermAmt_RT", "RT", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTermAmt_AC", "xxTermAmt_AC", "AC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTermAmt_AD", "xxTermAmt_AD", "AD", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_TT", "xxTotAmt_TT", "TT", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_TS", "xxTotAmt_TS", "TS", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_TE", "xxTotAmt_TE", "TE", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_TC", "xxTotAmt_TC", "TC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_PT", "xxTotAmt_PT", "PT", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_PS", "xxTotAmt_PS", "PS", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_PE", "xxTotAmt_PE", "PE", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxTotAmt_PC", "xxTotAmt_PC", "PC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"bllgCycleDescTxt_F", "bllgCycleDescTxt_F", "F", null, TYPE_HANKAKUEISU, "50", null},
	{"crRebilCd", "crRebilCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsPoReqFlg", "dsPoReqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"shpgStsCd", "shpgStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd
        {"CPO_SVC_CONFIG_REF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcConfigRefPk
        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum_B
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_BX
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_B
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"XX_SHIP_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxShipToAcctNmSrchTxt
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt
        {"DS_PO_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPoExprDt
        {"CPO_SVC_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcPrcPk
        {"XX_FREQ_CYCLE_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFreqCycleCnt
        {"BASE_BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgCycleDescTxt
        {"XX_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_TT
        {"XX_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_EQ
        {"XX_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_RT
        {"XX_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_AC
        {"XX_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_AD
        {"XX_TERM_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_TT
        {"XX_TERM_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_EQ
        {"XX_TERM_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_RT
        {"XX_TERM_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_AC
        {"XX_TERM_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_AD
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TT
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TS
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TE
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TC
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PT
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PS
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PE
        {"XX_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PC
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_F
        {"CR_REBIL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd
        {"DS_PO_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPoReqFlg
        {"SHPG_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd
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

