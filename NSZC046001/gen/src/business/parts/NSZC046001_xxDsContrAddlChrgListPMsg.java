//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220328112903000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC046001_xxDsContrAddlChrgListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC046001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC046001_xxDsContrAddlChrgListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_CONTR_ADDL_CHRG_PK*/
	public final EZDPBigDecimalItem              dsContrAddlChrgPk;

    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** ADDL_CHRG_TP_CD*/
	public final EZDPStringItem              addlChrgTpCd;

    /** SVC_BILL_BY_TP_CD*/
	public final EZDPStringItem              svcBillByTpCd;

    /** ADDL_CHRG_INV_TP_CD*/
	public final EZDPStringItem              addlChrgInvTpCd;

    /** CCY_CD*/
	public final EZDPStringItem              ccyCd;

    /** ADDL_CHRG_FLAT_DEAL_PRC_AMT*/
	public final EZDPBigDecimalItem              addlChrgFlatDealPrcAmt;

    /** ADDL_CHRG_FLAT_FUNC_PRC_AMT*/
	public final EZDPBigDecimalItem              addlChrgFlatFuncPrcAmt;

    /** ADDL_CHRG_APLC_PCT*/
	public final EZDPBigDecimalItem              addlChrgAplcPct;

    /** PRINT_DTL_FLG*/
	public final EZDPStringItem              printDtlFlg;

    /** BLLG_CYCLE_CD*/
	public final EZDPStringItem              bllgCycleCd;

    /** EFF_FROM_DT*/
	public final EZDPDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDPDateItem              effThruDt;

    /** TRMN_DT*/
	public final EZDPDateItem              trmnDt;

    /** ADDL_CHRG_INVD_FLG*/
	public final EZDPStringItem              addlChrgInvdFlg;

    /** INV_UP_TO_DT*/
	public final EZDPDateItem              invUpToDt;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MULT_MSG_DPLY_TXT*/
	public final EZDPStringItem              xxDsMultMsgDplyTxt;


	/**
	 * NSZC046001_xxDsContrAddlChrgListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrAddlChrgListPMsg is generated.
	 */
	public NSZC046001_xxDsContrAddlChrgListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC046001_xxDsContrAddlChrgListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrAddlChrgListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC046001_xxDsContrAddlChrgListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsContrAddlChrgPk = (EZDPBigDecimalItem)newItem("dsContrAddlChrgPk");
		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		addlChrgTpCd = (EZDPStringItem)newItem("addlChrgTpCd");
		svcBillByTpCd = (EZDPStringItem)newItem("svcBillByTpCd");
		addlChrgInvTpCd = (EZDPStringItem)newItem("addlChrgInvTpCd");
		ccyCd = (EZDPStringItem)newItem("ccyCd");
		addlChrgFlatDealPrcAmt = (EZDPBigDecimalItem)newItem("addlChrgFlatDealPrcAmt");
		addlChrgFlatFuncPrcAmt = (EZDPBigDecimalItem)newItem("addlChrgFlatFuncPrcAmt");
		addlChrgAplcPct = (EZDPBigDecimalItem)newItem("addlChrgAplcPct");
		printDtlFlg = (EZDPStringItem)newItem("printDtlFlg");
		bllgCycleCd = (EZDPStringItem)newItem("bllgCycleCd");
		effFromDt = (EZDPDateItem)newItem("effFromDt");
		effThruDt = (EZDPDateItem)newItem("effThruDt");
		trmnDt = (EZDPDateItem)newItem("trmnDt");
		addlChrgInvdFlg = (EZDPStringItem)newItem("addlChrgInvdFlg");
		invUpToDt = (EZDPDateItem)newItem("invUpToDt");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMultMsgDplyTxt = (EZDPStringItem)newItem("xxDsMultMsgDplyTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC046001_xxDsContrAddlChrgListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC046001_xxDsContrAddlChrgListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrAddlChrgPk", "dsContrAddlChrgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"addlChrgTpCd", "addlChrgTpCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"svcBillByTpCd", "svcBillByTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"addlChrgInvTpCd", "addlChrgInvTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ccyCd", "ccyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"addlChrgFlatDealPrcAmt", "addlChrgFlatDealPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlChrgFlatFuncPrcAmt", "addlChrgFlatFuncPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlChrgAplcPct", "addlChrgAplcPct", null, null, TYPE_SEISU_SYOSU, "5", "2"},
	{"printDtlFlg", "printDtlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleCd", "bllgCycleCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"trmnDt", "trmnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"addlChrgInvdFlg", "addlChrgInvdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invUpToDt", "invUpToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMultMsgDplyTxt", "xxDsMultMsgDplyTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_ADDL_CHRG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrAddlChrgPk
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"ADDL_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgTpCd
        {"SVC_BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBillByTpCd
        {"ADDL_CHRG_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgInvTpCd
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd
        {"ADDL_CHRG_FLAT_DEAL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgFlatDealPrcAmt
        {"ADDL_CHRG_FLAT_FUNC_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgFlatFuncPrcAmt
        {"ADDL_CHRG_APLC_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgAplcPct
        {"PRINT_DTL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//printDtlFlg
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"TRMN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trmnDt
        {"ADDL_CHRG_INVD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgInvdFlg
        {"INV_UP_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invUpToDt
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt
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

