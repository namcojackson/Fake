//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231031100058000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_LBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_LBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_NUM_LA*/
	public final EZDBStringItem              invNum_LA;

    /** INV_NUM_L*/
	public final EZDBStringItem              invNum_L;

    /** SYS_SRC_DESC_TXT_L*/
	public final EZDBStringItem              sysSrcDescTxt_L;

    /** DS_CONTR_NUM_LA*/
	public final EZDBStringItem              dsContrNum_LA;

    /** DS_CONTR_NUM_L*/
	public final EZDBStringItem              dsContrNum_L;

    /** DS_CONTR_PK_L*/
	public final EZDBBigDecimalItem              dsContrPk_L;

    /** LOC_NM_L1*/
	public final EZDBStringItem              locNm_L1;

    /** LOC_NM_L2*/
	public final EZDBStringItem              locNm_L2;

    /** INV_TP_DESC_TXT_L*/
	public final EZDBStringItem              invTpDescTxt_L;

    /** INV_TOT_DEAL_NET_AMT_L*/
	public final EZDBBigDecimalItem              invTotDealNetAmt_L;

    /** DEAL_RMNG_BAL_GRS_AMT_L*/
	public final EZDBBigDecimalItem              dealRmngBalGrsAmt_L;

    /** XX_APPLY_GRS_AMT_L*/
	public final EZDBBigDecimalItem              xxApplyGrsAmt_L;

    /** NET_DUE_DT_L*/
	public final EZDBDateItem              netDueDt_L;

    /** CPO_ORD_NUM_L*/
	public final EZDBStringItem              cpoOrdNum_L;

    /** INV_DT_L*/
	public final EZDBDateItem              invDt_L;

    /** ACCT_DT_L*/
	public final EZDBDateItem              acctDt_L;

    /** EIP_RPT_RQST_PK_L*/
	public final EZDBBigDecimalItem              eipRptRqstPk_L;


	/**
	 * NSAL0010_LBMsg is constructor.
	 * The initialization when the instance of NSAL0010_LBMsg is generated.
	 */
	public NSAL0010_LBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_LBMsg is constructor.
	 * The initialization when the instance of NSAL0010_LBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_LBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invNum_LA = (EZDBStringItem)newItem("invNum_LA");
		invNum_L = (EZDBStringItem)newItem("invNum_L");
		sysSrcDescTxt_L = (EZDBStringItem)newItem("sysSrcDescTxt_L");
		dsContrNum_LA = (EZDBStringItem)newItem("dsContrNum_LA");
		dsContrNum_L = (EZDBStringItem)newItem("dsContrNum_L");
		dsContrPk_L = (EZDBBigDecimalItem)newItem("dsContrPk_L");
		locNm_L1 = (EZDBStringItem)newItem("locNm_L1");
		locNm_L2 = (EZDBStringItem)newItem("locNm_L2");
		invTpDescTxt_L = (EZDBStringItem)newItem("invTpDescTxt_L");
		invTotDealNetAmt_L = (EZDBBigDecimalItem)newItem("invTotDealNetAmt_L");
		dealRmngBalGrsAmt_L = (EZDBBigDecimalItem)newItem("dealRmngBalGrsAmt_L");
		xxApplyGrsAmt_L = (EZDBBigDecimalItem)newItem("xxApplyGrsAmt_L");
		netDueDt_L = (EZDBDateItem)newItem("netDueDt_L");
		cpoOrdNum_L = (EZDBStringItem)newItem("cpoOrdNum_L");
		invDt_L = (EZDBDateItem)newItem("invDt_L");
		acctDt_L = (EZDBDateItem)newItem("acctDt_L");
		eipRptRqstPk_L = (EZDBBigDecimalItem)newItem("eipRptRqstPk_L");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_LBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_LBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invNum_LA", "invNum_LA", "LA", null, TYPE_HANKAKUEISU, "13", null},
	{"invNum_L", "invNum_L", "L", null, TYPE_HANKAKUEISU, "13", null},
	{"sysSrcDescTxt_L", "sysSrcDescTxt_L", "L", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrNum_LA", "dsContrNum_LA", "LA", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrNum_L", "dsContrNum_L", "L", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrPk_L", "dsContrPk_L", "L", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"locNm_L1", "locNm_L1", "L1", null, TYPE_HANKAKUEISU, "60", null},
	{"locNm_L2", "locNm_L2", "L2", null, TYPE_HANKAKUEISU, "60", null},
	{"invTpDescTxt_L", "invTpDescTxt_L", "L", null, TYPE_HANKAKUEISU, "50", null},
	{"invTotDealNetAmt_L", "invTotDealNetAmt_L", "L", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_L", "dealRmngBalGrsAmt_L", "L", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxApplyGrsAmt_L", "xxApplyGrsAmt_L", "L", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"netDueDt_L", "netDueDt_L", "L", null, TYPE_NENTSUKIHI, "8", null},
	{"cpoOrdNum_L", "cpoOrdNum_L", "L", null, TYPE_HANKAKUEISU, "8", null},
	{"invDt_L", "invDt_L", "L", null, TYPE_NENTSUKIHI, "8", null},
	{"acctDt_L", "acctDt_L", "L", null, TYPE_NENTSUKIHI, "8", null},
	{"eipRptRqstPk_L", "eipRptRqstPk_L", "L", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_LA
        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_L
        {"SYS_SRC_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcDescTxt_L
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_LA
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_L
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_L
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_L1
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_L2
        {"INV_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpDescTxt_L
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_L
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_L
        {"XX_APPLY_GRS_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxApplyGrsAmt_L
        {"NET_DUE_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//netDueDt_L
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_L
        {"INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDt_L
        {"ACCT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//acctDt_L
        {"EIP_RPT_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_L
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

