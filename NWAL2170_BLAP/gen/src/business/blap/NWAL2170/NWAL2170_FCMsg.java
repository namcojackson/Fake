//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180803111238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_FCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL2170_FCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_IMPT_SVC_LINE_NUM_F*/
	public final EZDCBigDecimalItem              dsImptSvcLineNum_F;

    /** DS_ORD_POSN_NUM_F*/
	public final EZDCStringItem              dsOrdPosnNum_F;

    /** CPO_SVC_PRC_PK_F*/
	public final EZDCBigDecimalItem              cpoSvcPrcPk_F;

    /** CPO_SVC_DTL_PK_F*/
	public final EZDCBigDecimalItem              cpoSvcDtlPk_F;

    /** MDL_ID_F*/
	public final EZDCBigDecimalItem              mdlId_F;

    /** MAINT_PRC_CATG_CD_F*/
	public final EZDCStringItem              maintPrcCatgCd_F;

    /** MAINT_FL_PRC_CATG_CD_F*/
	public final EZDCStringItem              maintFlPrcCatgCd_F;

    /** PRC_MTR_PKG_PK_F*/
	public final EZDCBigDecimalItem              prcMtrPkgPk_F;

    /** BASE_PRC_DEAL_AMT_F*/
	public final EZDCBigDecimalItem              basePrcDealAmt_F;

    /** BASE_PRC_FUNC_AMT_F*/
	public final EZDCBigDecimalItem              basePrcFuncAmt_F;

    /** DEAL_PRC_LIST_PRC_AMT_F*/
	public final EZDCBigDecimalItem              dealPrcListPrcAmt_F;

    /** FUNC_PRC_LIST_PRC_AMT_F*/
	public final EZDCBigDecimalItem              funcPrcListPrcAmt_F;

    /** PRC_TIER_SVC_OFFER_CD_F*/
	public final EZDCStringItem              prcTierSvcOfferCd_F;


	/**
	 * NWAL2170_FCMsg is constructor.
	 * The initialization when the instance of NWAL2170_FCMsg is generated.
	 */
	public NWAL2170_FCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170_FCMsg is constructor.
	 * The initialization when the instance of NWAL2170_FCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170_FCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsImptSvcLineNum_F = (EZDCBigDecimalItem)newItem("dsImptSvcLineNum_F");
		dsOrdPosnNum_F = (EZDCStringItem)newItem("dsOrdPosnNum_F");
		cpoSvcPrcPk_F = (EZDCBigDecimalItem)newItem("cpoSvcPrcPk_F");
		cpoSvcDtlPk_F = (EZDCBigDecimalItem)newItem("cpoSvcDtlPk_F");
		mdlId_F = (EZDCBigDecimalItem)newItem("mdlId_F");
		maintPrcCatgCd_F = (EZDCStringItem)newItem("maintPrcCatgCd_F");
		maintFlPrcCatgCd_F = (EZDCStringItem)newItem("maintFlPrcCatgCd_F");
		prcMtrPkgPk_F = (EZDCBigDecimalItem)newItem("prcMtrPkgPk_F");
		basePrcDealAmt_F = (EZDCBigDecimalItem)newItem("basePrcDealAmt_F");
		basePrcFuncAmt_F = (EZDCBigDecimalItem)newItem("basePrcFuncAmt_F");
		dealPrcListPrcAmt_F = (EZDCBigDecimalItem)newItem("dealPrcListPrcAmt_F");
		funcPrcListPrcAmt_F = (EZDCBigDecimalItem)newItem("funcPrcListPrcAmt_F");
		prcTierSvcOfferCd_F = (EZDCStringItem)newItem("prcTierSvcOfferCd_F");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170_FCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170_FCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsImptSvcLineNum_F", "dsImptSvcLineNum_F", "F", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum_F", "dsOrdPosnNum_F", "F", null, TYPE_HANKAKUEISU, "6", null},
	{"cpoSvcPrcPk_F", "cpoSvcPrcPk_F", "F", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoSvcDtlPk_F", "cpoSvcDtlPk_F", "F", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_F", "mdlId_F", "F", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"maintPrcCatgCd_F", "maintPrcCatgCd_F", "F", null, TYPE_HANKAKUEISU, "10", null},
	{"maintFlPrcCatgCd_F", "maintFlPrcCatgCd_F", "F", null, TYPE_HANKAKUEISU, "10", null},
	{"prcMtrPkgPk_F", "prcMtrPkgPk_F", "F", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"basePrcDealAmt_F", "basePrcDealAmt_F", "F", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"basePrcFuncAmt_F", "basePrcFuncAmt_F", "F", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrcListPrcAmt_F", "dealPrcListPrcAmt_F", "F", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcPrcListPrcAmt_F", "funcPrcListPrcAmt_F", "F", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcTierSvcOfferCd_F", "prcTierSvcOfferCd_F", "F", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum_F
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_F
        {"CPO_SVC_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcPrcPk_F
        {"CPO_SVC_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk_F
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_F
        {"MAINT_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintPrcCatgCd_F
        {"MAINT_FL_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintFlPrcCatgCd_F
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_F
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_F
        {"BASE_PRC_FUNC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcFuncAmt_F
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_F
        {"FUNC_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcPrcListPrcAmt_F
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd_F
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
