//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180803095224000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170_UBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NWAL2170_UBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_SVC_ADDL_CHRG_PRC_PK_U*/
	public final EZDBBigDecimalItem              cpoSvcAddlChrgPrcPk_U;

    /** CPO_SVC_DTL_PK_U*/
	public final EZDBBigDecimalItem              cpoSvcDtlPk_U;

    /** CPO_ORD_NUM_U*/
	public final EZDBStringItem              cpoOrdNum_U;

    /** DS_ORD_POSN_NUM_U*/
	public final EZDBStringItem              dsOrdPosnNum_U;

    /** CPO_DTL_LINE_NUM_U*/
	public final EZDBStringItem              cpoDtlLineNum_U;

    /** CPO_DTL_LINE_SUB_NUM_U*/
	public final EZDBStringItem              cpoDtlLineSubNum_U;

    /** ADDL_CHRG_PRC_CATG_CD_U*/
	public final EZDBStringItem              addlChrgPrcCatgCd_U;

    /** ADDL_CHRG_MDSE_CD_U*/
	public final EZDBStringItem              addlChrgMdseCd_U;

    /** ADDL_CHRG_PRC_DEAL_AMT_U*/
	public final EZDBBigDecimalItem              addlChrgPrcDealAmt_U;

    /** ADDL_CHRG_PRC_FUNC_AMT_U*/
	public final EZDBBigDecimalItem              addlChrgPrcFuncAmt_U;

    /** DEAL_PRC_LIST_PRC_AMT_U*/
	public final EZDBBigDecimalItem              dealPrcListPrcAmt_U;

    /** FUNC_PRC_LIST_PRC_AMT_U*/
	public final EZDBBigDecimalItem              funcPrcListPrcAmt_U;

    /** SVC_PRC_CATG_CD_U*/
	public final EZDBStringItem              svcPrcCatgCd_U;

    /** BILL_WITH_EQUIP_INVD_FLG_U*/
	public final EZDBStringItem              billWithEquipInvdFlg_U;

    /** CR_REBIL_CD_U*/
	public final EZDBStringItem              crRebilCd_U;


	/**
	 * NWAL2170_UBMsg is constructor.
	 * The initialization when the instance of NWAL2170_UBMsg is generated.
	 */
	public NWAL2170_UBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170_UBMsg is constructor.
	 * The initialization when the instance of NWAL2170_UBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170_UBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoSvcAddlChrgPrcPk_U = (EZDBBigDecimalItem)newItem("cpoSvcAddlChrgPrcPk_U");
		cpoSvcDtlPk_U = (EZDBBigDecimalItem)newItem("cpoSvcDtlPk_U");
		cpoOrdNum_U = (EZDBStringItem)newItem("cpoOrdNum_U");
		dsOrdPosnNum_U = (EZDBStringItem)newItem("dsOrdPosnNum_U");
		cpoDtlLineNum_U = (EZDBStringItem)newItem("cpoDtlLineNum_U");
		cpoDtlLineSubNum_U = (EZDBStringItem)newItem("cpoDtlLineSubNum_U");
		addlChrgPrcCatgCd_U = (EZDBStringItem)newItem("addlChrgPrcCatgCd_U");
		addlChrgMdseCd_U = (EZDBStringItem)newItem("addlChrgMdseCd_U");
		addlChrgPrcDealAmt_U = (EZDBBigDecimalItem)newItem("addlChrgPrcDealAmt_U");
		addlChrgPrcFuncAmt_U = (EZDBBigDecimalItem)newItem("addlChrgPrcFuncAmt_U");
		dealPrcListPrcAmt_U = (EZDBBigDecimalItem)newItem("dealPrcListPrcAmt_U");
		funcPrcListPrcAmt_U = (EZDBBigDecimalItem)newItem("funcPrcListPrcAmt_U");
		svcPrcCatgCd_U = (EZDBStringItem)newItem("svcPrcCatgCd_U");
		billWithEquipInvdFlg_U = (EZDBStringItem)newItem("billWithEquipInvdFlg_U");
		crRebilCd_U = (EZDBStringItem)newItem("crRebilCd_U");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170_UBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170_UBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoSvcAddlChrgPrcPk_U", "cpoSvcAddlChrgPrcPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoSvcDtlPk_U", "cpoSvcDtlPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoOrdNum_U", "cpoOrdNum_U", "U", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum_U", "dsOrdPosnNum_U", "U", null, TYPE_HANKAKUEISU, "6", null},
	{"cpoDtlLineNum_U", "cpoDtlLineNum_U", "U", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_U", "cpoDtlLineSubNum_U", "U", null, TYPE_HANKAKUEISU, "3", null},
	{"addlChrgPrcCatgCd_U", "addlChrgPrcCatgCd_U", "U", null, TYPE_HANKAKUEISU, "10", null},
	{"addlChrgMdseCd_U", "addlChrgMdseCd_U", "U", null, TYPE_HANKAKUEISU, "16", null},
	{"addlChrgPrcDealAmt_U", "addlChrgPrcDealAmt_U", "U", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlChrgPrcFuncAmt_U", "addlChrgPrcFuncAmt_U", "U", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrcListPrcAmt_U", "dealPrcListPrcAmt_U", "U", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcPrcListPrcAmt_U", "funcPrcListPrcAmt_U", "U", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"svcPrcCatgCd_U", "svcPrcCatgCd_U", "U", null, TYPE_HANKAKUEISU, "4", null},
	{"billWithEquipInvdFlg_U", "billWithEquipInvdFlg_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	{"crRebilCd_U", "crRebilCd_U", "U", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_SVC_ADDL_CHRG_PRC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcAddlChrgPrcPk_U
        {"CPO_SVC_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk_U
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_U
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_U
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_U
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_U
        {"ADDL_CHRG_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgPrcCatgCd_U
        {"ADDL_CHRG_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgMdseCd_U
        {"ADDL_CHRG_PRC_DEAL_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgPrcDealAmt_U
        {"ADDL_CHRG_PRC_FUNC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgPrcFuncAmt_U
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_U
        {"FUNC_PRC_LIST_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcPrcListPrcAmt_U
        {"SVC_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrcCatgCd_U
        {"BILL_WITH_EQUIP_INVD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billWithEquipInvdFlg_U
        {"CR_REBIL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd_U
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
