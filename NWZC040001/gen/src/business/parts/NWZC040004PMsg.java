//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170406115244000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC040004PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWZC040004 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC040004PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** INV_NUM*/
	public final EZDPStringItem              invNum;

    /** INV_BOL_LINE_NUM*/
	public final EZDPStringItem              invBolLineNum;

    /** INV_LINE_NUM*/
	public final EZDPStringItem              invLineNum;

    /** INV_LINE_SUB_NUM*/
	public final EZDPStringItem              invLineSubNum;

    /** INV_LINE_SUB_TRX_NUM*/
	public final EZDPStringItem              invLineSubTrxNum;

    /** PRMO_CATG_PK*/
	public final EZDPBigDecimalItem              prmoCatgPk;

    /** PRMO_PK*/
	public final EZDPBigDecimalItem              prmoPk;

    /** DEAL_PK*/
	public final EZDPBigDecimalItem              dealPk;

    /** PRC_DT*/
	public final EZDPDateItem              prcDt;

    /** PRMO_QTY*/
	public final EZDPBigDecimalItem              prmoQty;

    /** DEAL_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealUnitPrcAmt;

    /** DEAL_LAST_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealLastNetUnitPrcAmt;

    /** DEAL_NET_AMT*/
	public final EZDPBigDecimalItem              dealNetAmt;

    /** DEAL_DISC_AMT*/
	public final EZDPBigDecimalItem              dealDiscAmt;

    /** DEAL_PRMO_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              dealPrmoNetUnitPrcAmt;

    /** DEAL_PER_UNIT_FIX_AMT*/
	public final EZDPBigDecimalItem              dealPerUnitFixAmt;

    /** DEAL_SLS_PCT_NUM*/
	public final EZDPBigDecimalItem              dealSlsPctNum;

    /** FUNC_PER_UNIT_FIX_AMT*/
	public final EZDPBigDecimalItem              funcPerUnitFixAmt;

    /** FUNC_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcUnitPrcAmt;

    /** FUNC_LAST_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcLastNetUnitPrcAmt;

    /** FUNC_NET_AMT*/
	public final EZDPBigDecimalItem              funcNetAmt;

    /** FUNC_DISC_AMT*/
	public final EZDPBigDecimalItem              funcDiscAmt;

    /** FUNC_PRMO_NET_UNIT_PRC_AMT*/
	public final EZDPBigDecimalItem              funcPrmoNetUnitPrcAmt;

    /** COA_ACCT_CD*/
	public final EZDPStringItem              coaAcctCd;

    /** INV_PRMO_INFO_SQ_NUM*/
	public final EZDPStringItem              invPrmoInfoSqNum;

    /** xxMsgIdList*/
	public final business.parts.NWZC040004_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC040004PMsg is constructor.
	 * The initialization when the instance of NWZC040004PMsg is generated.
	 */
	public NWZC040004PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC040004PMsg is constructor.
	 * The initialization when the instance of NWZC040004PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC040004PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		invNum = (EZDPStringItem)newItem("invNum");
		invBolLineNum = (EZDPStringItem)newItem("invBolLineNum");
		invLineNum = (EZDPStringItem)newItem("invLineNum");
		invLineSubNum = (EZDPStringItem)newItem("invLineSubNum");
		invLineSubTrxNum = (EZDPStringItem)newItem("invLineSubTrxNum");
		prmoCatgPk = (EZDPBigDecimalItem)newItem("prmoCatgPk");
		prmoPk = (EZDPBigDecimalItem)newItem("prmoPk");
		dealPk = (EZDPBigDecimalItem)newItem("dealPk");
		prcDt = (EZDPDateItem)newItem("prcDt");
		prmoQty = (EZDPBigDecimalItem)newItem("prmoQty");
		dealUnitPrcAmt = (EZDPBigDecimalItem)newItem("dealUnitPrcAmt");
		dealLastNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("dealLastNetUnitPrcAmt");
		dealNetAmt = (EZDPBigDecimalItem)newItem("dealNetAmt");
		dealDiscAmt = (EZDPBigDecimalItem)newItem("dealDiscAmt");
		dealPrmoNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("dealPrmoNetUnitPrcAmt");
		dealPerUnitFixAmt = (EZDPBigDecimalItem)newItem("dealPerUnitFixAmt");
		dealSlsPctNum = (EZDPBigDecimalItem)newItem("dealSlsPctNum");
		funcPerUnitFixAmt = (EZDPBigDecimalItem)newItem("funcPerUnitFixAmt");
		funcUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcUnitPrcAmt");
		funcLastNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcLastNetUnitPrcAmt");
		funcNetAmt = (EZDPBigDecimalItem)newItem("funcNetAmt");
		funcDiscAmt = (EZDPBigDecimalItem)newItem("funcDiscAmt");
		funcPrmoNetUnitPrcAmt = (EZDPBigDecimalItem)newItem("funcPrmoNetUnitPrcAmt");
		coaAcctCd = (EZDPStringItem)newItem("coaAcctCd");
		invPrmoInfoSqNum = (EZDPStringItem)newItem("invPrmoInfoSqNum");
		xxMsgIdList = (business.parts.NWZC040004_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC040004PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC040004PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"invBolLineNum", "invBolLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum", "invLineNum", null, null, TYPE_HANKAKUEISU, "5", null},
	{"invLineSubNum", "invLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"invLineSubTrxNum", "invLineSubTrxNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"prmoCatgPk", "prmoCatgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prmoPk", "prmoPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dealPk", "dealPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcDt", "prcDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prmoQty", "prmoQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dealUnitPrcAmt", "dealUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealLastNetUnitPrcAmt", "dealLastNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealNetAmt", "dealNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealDiscAmt", "dealDiscAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrmoNetUnitPrcAmt", "dealPrmoNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPerUnitFixAmt", "dealPerUnitFixAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealSlsPctNum", "dealSlsPctNum", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"funcPerUnitFixAmt", "funcPerUnitFixAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcUnitPrcAmt", "funcUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcLastNetUnitPrcAmt", "funcLastNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcNetAmt", "funcNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcDiscAmt", "funcDiscAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcPrmoNetUnitPrcAmt", "funcPrmoNetUnitPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"coaAcctCd", "coaAcctCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invPrmoInfoSqNum", "invPrmoInfoSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC040004_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum
        {"INV_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubNum
        {"INV_LINE_SUB_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubTrxNum
        {"PRMO_CATG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prmoCatgPk
        {"PRMO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prmoPk
        {"DEAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPk
        {"PRC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDt
        {"PRMO_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prmoQty
        {"DEAL_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealUnitPrcAmt
        {"DEAL_LAST_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealLastNetUnitPrcAmt
        {"DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealNetAmt
        {"DEAL_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealDiscAmt
        {"DEAL_PRMO_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrmoNetUnitPrcAmt
        {"DEAL_PER_UNIT_FIX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPerUnitFixAmt
        {"DEAL_SLS_PCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealSlsPctNum
        {"FUNC_PER_UNIT_FIX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcPerUnitFixAmt
        {"FUNC_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcUnitPrcAmt
        {"FUNC_LAST_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcLastNetUnitPrcAmt
        {"FUNC_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcNetAmt
        {"FUNC_DISC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcDiscAmt
        {"FUNC_PRMO_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcPrmoNetUnitPrcAmt
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd
        {"INV_PRMO_INFO_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrmoInfoSqNum
		null,	//xxMsgIdList
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
