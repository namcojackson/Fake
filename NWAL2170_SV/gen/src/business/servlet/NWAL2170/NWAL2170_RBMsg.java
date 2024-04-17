//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180803095224000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_RBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2170 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2170_RBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_SVC_PRC_PK_R*/
	public final EZDBBigDecimalItem              cpoSvcPrcPk_R;

    /** CPO_SVC_DTL_PK_R*/
	public final EZDBBigDecimalItem              cpoSvcDtlPk_R;

    /** MDL_ID_R*/
	public final EZDBBigDecimalItem              mdlId_R;

    /** XX_TOT_QTY_CNT_R*/
	public final EZDBBigDecimalItem              xxTotQtyCnt_R;

    /** MAINT_PRC_CATG_CD_R*/
	public final EZDBStringItem              maintPrcCatgCd_R;

    /** MAINT_FL_PRC_CATG_CD_R*/
	public final EZDBStringItem              maintFlPrcCatgCd_R;

    /** PRC_MTR_PKG_PK_R*/
	public final EZDBBigDecimalItem              prcMtrPkgPk_R;

    /** BASE_PRC_DEAL_AMT_R*/
	public final EZDBBigDecimalItem              basePrcDealAmt_R;

    /** BASE_PRC_FUNC_AMT_R*/
	public final EZDBBigDecimalItem              basePrcFuncAmt_R;

    /** DEAL_PRC_LIST_PRC_AMT_R*/
	public final EZDBBigDecimalItem              dealPrcListPrcAmt_R;

    /** FUNC_PRC_LIST_PRC_AMT_R*/
	public final EZDBBigDecimalItem              funcPrcListPrcAmt_R;

    /** PRC_TIER_SVC_OFFER_CD_R*/
	public final EZDBStringItem              prcTierSvcOfferCd_R;


	/**
	 * NWAL2170_RBMsg is constructor.
	 * The initialization when the instance of NWAL2170_RBMsg is generated.
	 */
	public NWAL2170_RBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170_RBMsg is constructor.
	 * The initialization when the instance of NWAL2170_RBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170_RBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoSvcPrcPk_R = (EZDBBigDecimalItem)newItem("cpoSvcPrcPk_R");
		cpoSvcDtlPk_R = (EZDBBigDecimalItem)newItem("cpoSvcDtlPk_R");
		mdlId_R = (EZDBBigDecimalItem)newItem("mdlId_R");
		xxTotQtyCnt_R = (EZDBBigDecimalItem)newItem("xxTotQtyCnt_R");
		maintPrcCatgCd_R = (EZDBStringItem)newItem("maintPrcCatgCd_R");
		maintFlPrcCatgCd_R = (EZDBStringItem)newItem("maintFlPrcCatgCd_R");
		prcMtrPkgPk_R = (EZDBBigDecimalItem)newItem("prcMtrPkgPk_R");
		basePrcDealAmt_R = (EZDBBigDecimalItem)newItem("basePrcDealAmt_R");
		basePrcFuncAmt_R = (EZDBBigDecimalItem)newItem("basePrcFuncAmt_R");
		dealPrcListPrcAmt_R = (EZDBBigDecimalItem)newItem("dealPrcListPrcAmt_R");
		funcPrcListPrcAmt_R = (EZDBBigDecimalItem)newItem("funcPrcListPrcAmt_R");
		prcTierSvcOfferCd_R = (EZDBStringItem)newItem("prcTierSvcOfferCd_R");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170_RBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170_RBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoSvcPrcPk_R", "cpoSvcPrcPk_R", "R", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoSvcDtlPk_R", "cpoSvcDtlPk_R", "R", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_R", "mdlId_R", "R", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"xxTotQtyCnt_R", "xxTotQtyCnt_R", "R", null, TYPE_SEISU_SYOSU, "11", "0"},
	{"maintPrcCatgCd_R", "maintPrcCatgCd_R", "R", null, TYPE_HANKAKUEISU, "10", null},
	{"maintFlPrcCatgCd_R", "maintFlPrcCatgCd_R", "R", null, TYPE_HANKAKUEISU, "10", null},
	{"prcMtrPkgPk_R", "prcMtrPkgPk_R", "R", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"basePrcDealAmt_R", "basePrcDealAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"basePrcFuncAmt_R", "basePrcFuncAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrcListPrcAmt_R", "dealPrcListPrcAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcPrcListPrcAmt_R", "funcPrcListPrcAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcTierSvcOfferCd_R", "prcTierSvcOfferCd_R", "R", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_SVC_PRC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcPrcPk_R
        {"CPO_SVC_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk_R
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_R
        {"XX_TOT_QTY_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotQtyCnt_R
        {"MAINT_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintPrcCatgCd_R
        {"MAINT_FL_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintFlPrcCatgCd_R
        {"PRC_MTR_PKG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_R
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_R
        {"BASE_PRC_FUNC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcFuncAmt_R
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_R
        {"FUNC_PRC_LIST_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcPrcListPrcAmt_R
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd_R
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
