//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240325170156000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1320_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1320;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1320 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1320_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TP_CD*/
	public final EZDBStringItem              xxTpCd;

    /** DS_CONTR_PK_B*/
	public final EZDBBigDecimalItem              dsContrPk_B;

    /** DS_CONTR_DTL_PK_B*/
	public final EZDBBigDecimalItem              dsContrDtlPk_B;

    /** SHELL_LINE_NUM_B*/
	public final EZDBBigDecimalItem              shellLineNum_B;

    /** XX_CHK_BOX_B*/
	public final EZDBStringItem              xxChkBox_B;

    /** DS_ORD_POSN_NUM*/
	public final EZDBStringItem              dsOrdPosnNum;

    /** DS_ORD_POSN_NUM_BX*/
	public final EZDBStringItem              dsOrdPosnNum_BX;

    /** XX_MODE_CD_B*/
	public final EZDBStringItem              xxModeCd_B;

    /** T_MDL_NM*/
	public final EZDBStringItem              t_MdlNm;

    /** MDL_ID*/
	public final EZDBBigDecimalItem              mdlId;

    /** DPLY_LINE_NUM*/
	public final EZDBStringItem              dplyLineNum;

    /** CPO_DTL_LINE_NUM*/
	public final EZDBStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDBStringItem              cpoDtlLineSubNum;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** XX_GENL_FLD_AREA_TXT_SH*/
	public final EZDBStringItem              xxGenlFldAreaTxt_SH;

    /** SVC_CONTR_BR_DESC_TXT*/
	public final EZDBStringItem              svcContrBrDescTxt;

    /** MTR_READ_METH_CD*/
	public final EZDBStringItem              mtrReadMethCd;

    /** CUST_ISS_PO_NUM*/
	public final EZDBStringItem              custIssPoNum;

    /** CUST_ISS_PO_DT*/
	public final EZDBDateItem              custIssPoDt;

    /** DS_PO_EXPR_DT*/
	public final EZDBDateItem              dsPoExprDt;

    /** XX_FREQ_CYCLE_CNT*/
	public final EZDBBigDecimalItem              xxFreqCycleCnt;

    /** BASE_BLLG_CYCLE_DESC_TXT*/
	public final EZDBStringItem              baseBllgCycleDescTxt;

    /** XX_DEAL_AMT_TT*/
	public final EZDBBigDecimalItem              xxDealAmt_TT;

    /** XX_DEAL_AMT_EQ*/
	public final EZDBBigDecimalItem              xxDealAmt_EQ;

    /** XX_DEAL_AMT_RT*/
	public final EZDBBigDecimalItem              xxDealAmt_RT;

    /** XX_DEAL_AMT_AC*/
	public final EZDBBigDecimalItem              xxDealAmt_AC;

    /** XX_DEAL_AMT_AD*/
	public final EZDBBigDecimalItem              xxDealAmt_AD;

    /** XX_TERM_AMT_TT*/
	public final EZDBBigDecimalItem              xxTermAmt_TT;

    /** XX_TERM_AMT_EQ*/
	public final EZDBBigDecimalItem              xxTermAmt_EQ;

    /** XX_TERM_AMT_RT*/
	public final EZDBBigDecimalItem              xxTermAmt_RT;

    /** XX_TERM_AMT_AC*/
	public final EZDBBigDecimalItem              xxTermAmt_AC;

    /** XX_TERM_AMT_AD*/
	public final EZDBBigDecimalItem              xxTermAmt_AD;

    /** XX_TOT_AMT_TT*/
	public final EZDBBigDecimalItem              xxTotAmt_TT;

    /** XX_TOT_AMT_TS*/
	public final EZDBBigDecimalItem              xxTotAmt_TS;

    /** XX_TOT_AMT_TE*/
	public final EZDBBigDecimalItem              xxTotAmt_TE;

    /** XX_TOT_AMT_TC*/
	public final EZDBBigDecimalItem              xxTotAmt_TC;

    /** XX_TOT_AMT_PT*/
	public final EZDBBigDecimalItem              xxTotAmt_PT;

    /** XX_TOT_AMT_PS*/
	public final EZDBBigDecimalItem              xxTotAmt_PS;

    /** XX_TOT_AMT_PE*/
	public final EZDBBigDecimalItem              xxTotAmt_PE;

    /** XX_TOT_AMT_PC*/
	public final EZDBBigDecimalItem              xxTotAmt_PC;

    /** TERM_MTH_AOT_F*/
	public final EZDBBigDecimalItem              termMthAot_F;

    /** BLLG_CYCLE_DESC_TXT_F*/
	public final EZDBStringItem              bllgCycleDescTxt_F;

    /** CR_REBIL_CD*/
	public final EZDBStringItem              crRebilCd;

    /** DS_PO_REQ_FLG*/
	public final EZDBStringItem              dsPoReqFlg;

    /** CONTR_AVAL_FLG*/
	public final EZDBStringItem              contrAvalFlg;

    /** SHPG_STS_CD*/
	public final EZDBStringItem              shpgStsCd;

    /** SHIP_FLG*/
	public final EZDBStringItem              shipFlg;


	/**
	 * NSAL1320_BBMsg is constructor.
	 * The initialization when the instance of NSAL1320_BBMsg is generated.
	 */
	public NSAL1320_BBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1320_BBMsg is constructor.
	 * The initialization when the instance of NSAL1320_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1320_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTpCd = (EZDBStringItem)newItem("xxTpCd");
		dsContrPk_B = (EZDBBigDecimalItem)newItem("dsContrPk_B");
		dsContrDtlPk_B = (EZDBBigDecimalItem)newItem("dsContrDtlPk_B");
		shellLineNum_B = (EZDBBigDecimalItem)newItem("shellLineNum_B");
		xxChkBox_B = (EZDBStringItem)newItem("xxChkBox_B");
		dsOrdPosnNum = (EZDBStringItem)newItem("dsOrdPosnNum");
		dsOrdPosnNum_BX = (EZDBStringItem)newItem("dsOrdPosnNum_BX");
		xxModeCd_B = (EZDBStringItem)newItem("xxModeCd_B");
		t_MdlNm = (EZDBStringItem)newItem("t_MdlNm");
		mdlId = (EZDBBigDecimalItem)newItem("mdlId");
		dplyLineNum = (EZDBStringItem)newItem("dplyLineNum");
		cpoDtlLineNum = (EZDBStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDBStringItem)newItem("cpoDtlLineSubNum");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		xxGenlFldAreaTxt_SH = (EZDBStringItem)newItem("xxGenlFldAreaTxt_SH");
		svcContrBrDescTxt = (EZDBStringItem)newItem("svcContrBrDescTxt");
		mtrReadMethCd = (EZDBStringItem)newItem("mtrReadMethCd");
		custIssPoNum = (EZDBStringItem)newItem("custIssPoNum");
		custIssPoDt = (EZDBDateItem)newItem("custIssPoDt");
		dsPoExprDt = (EZDBDateItem)newItem("dsPoExprDt");
		xxFreqCycleCnt = (EZDBBigDecimalItem)newItem("xxFreqCycleCnt");
		baseBllgCycleDescTxt = (EZDBStringItem)newItem("baseBllgCycleDescTxt");
		xxDealAmt_TT = (EZDBBigDecimalItem)newItem("xxDealAmt_TT");
		xxDealAmt_EQ = (EZDBBigDecimalItem)newItem("xxDealAmt_EQ");
		xxDealAmt_RT = (EZDBBigDecimalItem)newItem("xxDealAmt_RT");
		xxDealAmt_AC = (EZDBBigDecimalItem)newItem("xxDealAmt_AC");
		xxDealAmt_AD = (EZDBBigDecimalItem)newItem("xxDealAmt_AD");
		xxTermAmt_TT = (EZDBBigDecimalItem)newItem("xxTermAmt_TT");
		xxTermAmt_EQ = (EZDBBigDecimalItem)newItem("xxTermAmt_EQ");
		xxTermAmt_RT = (EZDBBigDecimalItem)newItem("xxTermAmt_RT");
		xxTermAmt_AC = (EZDBBigDecimalItem)newItem("xxTermAmt_AC");
		xxTermAmt_AD = (EZDBBigDecimalItem)newItem("xxTermAmt_AD");
		xxTotAmt_TT = (EZDBBigDecimalItem)newItem("xxTotAmt_TT");
		xxTotAmt_TS = (EZDBBigDecimalItem)newItem("xxTotAmt_TS");
		xxTotAmt_TE = (EZDBBigDecimalItem)newItem("xxTotAmt_TE");
		xxTotAmt_TC = (EZDBBigDecimalItem)newItem("xxTotAmt_TC");
		xxTotAmt_PT = (EZDBBigDecimalItem)newItem("xxTotAmt_PT");
		xxTotAmt_PS = (EZDBBigDecimalItem)newItem("xxTotAmt_PS");
		xxTotAmt_PE = (EZDBBigDecimalItem)newItem("xxTotAmt_PE");
		xxTotAmt_PC = (EZDBBigDecimalItem)newItem("xxTotAmt_PC");
		termMthAot_F = (EZDBBigDecimalItem)newItem("termMthAot_F");
		bllgCycleDescTxt_F = (EZDBStringItem)newItem("bllgCycleDescTxt_F");
		crRebilCd = (EZDBStringItem)newItem("crRebilCd");
		dsPoReqFlg = (EZDBStringItem)newItem("dsPoReqFlg");
		contrAvalFlg = (EZDBStringItem)newItem("contrAvalFlg");
		shpgStsCd = (EZDBStringItem)newItem("shpgStsCd");
		shipFlg = (EZDBStringItem)newItem("shipFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1320_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1320_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTpCd", "xxTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrPk_B", "dsContrPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_B", "dsContrDtlPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"shellLineNum_B", "shellLineNum_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
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
	{"xxGenlFldAreaTxt_SH", "xxGenlFldAreaTxt_SH", "SH", null, TYPE_HANKAKUEISU, "1000", null},
	{"svcContrBrDescTxt", "svcContrBrDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mtrReadMethCd", "mtrReadMethCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsPoExprDt", "dsPoExprDt", null, null, TYPE_NENTSUKIHI, "8", null},
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
	{"termMthAot_F", "termMthAot_F", "F", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bllgCycleDescTxt_F", "bllgCycleDescTxt_F", "F", null, TYPE_HANKAKUEISU, "50", null},
	{"crRebilCd", "crRebilCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsPoReqFlg", "dsPoReqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"contrAvalFlg", "contrAvalFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"shpgStsCd", "shpgStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"shipFlg", "shipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_B
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_B
        {"SHELL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shellLineNum_B
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_BX
        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_B
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_SH
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CUST_ISS_PO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//custIssPoDt
        {"DS_PO_EXPR_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//dsPoExprDt
        {"XX_FREQ_CYCLE_CNT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxFreqCycleCnt
        {"BASE_BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgCycleDescTxt
        {"XX_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_TT
        {"XX_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_EQ
        {"XX_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_RT
        {"XX_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_AC
        {"XX_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealAmt_AD
        {"XX_TERM_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_TT
        {"XX_TERM_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_EQ
        {"XX_TERM_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_RT
        {"XX_TERM_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_AC
        {"XX_TERM_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTermAmt_AD
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TT
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TS
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TE
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_TC
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PT
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PS
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PE
        {"XX_TOT_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotAmt_PC
        {"TERM_MTH_AOT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//termMthAot_F
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_F
        {"CR_REBIL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd
        {"DS_PO_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPoReqFlg
        {"CONTR_AVAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAvalFlg
        {"SHPG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd
        {"SHIP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFlg
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

