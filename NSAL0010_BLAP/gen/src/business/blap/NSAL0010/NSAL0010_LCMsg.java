//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231010122637000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_LCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_LCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_NUM_LA*/
	public final EZDCStringItem              invNum_LA;

    /** INV_NUM_L*/
	public final EZDCStringItem              invNum_L;

    /** SYS_SRC_DESC_TXT_L*/
	public final EZDCStringItem              sysSrcDescTxt_L;

    /** DS_CONTR_NUM_LA*/
	public final EZDCStringItem              dsContrNum_LA;

    /** DS_CONTR_NUM_L*/
	public final EZDCStringItem              dsContrNum_L;

    /** DS_CONTR_PK_L*/
	public final EZDCBigDecimalItem              dsContrPk_L;

    /** LOC_NM_L1*/
	public final EZDCStringItem              locNm_L1;

    /** LOC_NM_L2*/
	public final EZDCStringItem              locNm_L2;

    /** INV_TP_DESC_TXT_L*/
	public final EZDCStringItem              invTpDescTxt_L;

    /** INV_TOT_DEAL_NET_AMT_L*/
	public final EZDCBigDecimalItem              invTotDealNetAmt_L;

    /** DEAL_RMNG_BAL_GRS_AMT_L*/
	public final EZDCBigDecimalItem              dealRmngBalGrsAmt_L;

    /** XX_APPLY_GRS_AMT_L*/
	public final EZDCBigDecimalItem              xxApplyGrsAmt_L;

    /** NET_DUE_DT_L*/
	public final EZDCDateItem              netDueDt_L;

    /** CPO_ORD_NUM_L*/
	public final EZDCStringItem              cpoOrdNum_L;

    /** INV_DT_L*/
	public final EZDCDateItem              invDt_L;

    /** ACCT_DT_L*/
	public final EZDCDateItem              acctDt_L;

    /** EIP_RPT_RQST_PK_L*/
	public final EZDCBigDecimalItem              eipRptRqstPk_L;


	/**
	 * NSAL0010_LCMsg is constructor.
	 * The initialization when the instance of NSAL0010_LCMsg is generated.
	 */
	public NSAL0010_LCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_LCMsg is constructor.
	 * The initialization when the instance of NSAL0010_LCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_LCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invNum_LA = (EZDCStringItem)newItem("invNum_LA");
		invNum_L = (EZDCStringItem)newItem("invNum_L");
		sysSrcDescTxt_L = (EZDCStringItem)newItem("sysSrcDescTxt_L");
		dsContrNum_LA = (EZDCStringItem)newItem("dsContrNum_LA");
		dsContrNum_L = (EZDCStringItem)newItem("dsContrNum_L");
		dsContrPk_L = (EZDCBigDecimalItem)newItem("dsContrPk_L");
		locNm_L1 = (EZDCStringItem)newItem("locNm_L1");
		locNm_L2 = (EZDCStringItem)newItem("locNm_L2");
		invTpDescTxt_L = (EZDCStringItem)newItem("invTpDescTxt_L");
		invTotDealNetAmt_L = (EZDCBigDecimalItem)newItem("invTotDealNetAmt_L");
		dealRmngBalGrsAmt_L = (EZDCBigDecimalItem)newItem("dealRmngBalGrsAmt_L");
		xxApplyGrsAmt_L = (EZDCBigDecimalItem)newItem("xxApplyGrsAmt_L");
		netDueDt_L = (EZDCDateItem)newItem("netDueDt_L");
		cpoOrdNum_L = (EZDCStringItem)newItem("cpoOrdNum_L");
		invDt_L = (EZDCDateItem)newItem("invDt_L");
		acctDt_L = (EZDCDateItem)newItem("acctDt_L");
		eipRptRqstPk_L = (EZDCBigDecimalItem)newItem("eipRptRqstPk_L");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_LCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_LCMsgArray();
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

        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_LA
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_L
        {"SYS_SRC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcDescTxt_L
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_LA
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_L
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_L
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_L1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_L2
        {"INV_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpDescTxt_L
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_L
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_L
        {"XX_APPLY_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxApplyGrsAmt_L
        {"NET_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//netDueDt_L
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_L
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_L
        {"ACCT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctDt_L
        {"EIP_RPT_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_L
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
