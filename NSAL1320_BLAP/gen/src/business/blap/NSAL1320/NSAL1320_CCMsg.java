//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240325170114000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1320_CCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL1320_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SHELL_LINE_NUM_C*/
	public final EZDCBigDecimalItem              shellLineNum_C;

    /** DS_ORD_POSN_NUM_C*/
	public final EZDCStringItem              dsOrdPosnNum_C;

    /** DS_CONTR_DTL_PK_C*/
	public final EZDCBigDecimalItem              dsContrDtlPk_C;

    /** DS_CONTR_PK_C*/
	public final EZDCBigDecimalItem              dsContrPk_C;

    /** CPO_ORD_NUM_C*/
	public final EZDCStringItem              cpoOrdNum_C;

    /** CPO_DTL_LINE_NUM_C*/
	public final EZDCStringItem              cpoDtlLineNum_C;

    /** CPO_DTL_LINE_SUB_NUM_C*/
	public final EZDCStringItem              cpoDtlLineSubNum_C;

    /** SVC_CONFIG_MSTR_PK_C*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_C;

    /** CUST_ISS_PO_NUM_C*/
	public final EZDCStringItem              custIssPoNum_C;

    /** CUST_ISS_PO_DT_C*/
	public final EZDCDateItem              custIssPoDt_C;

    /** MTR_READ_METH_CD_C*/
	public final EZDCStringItem              mtrReadMethCd_C;

    /** SVC_PRC_CATG_CD_C*/
	public final EZDCStringItem              svcPrcCatgCd_C;

    /** BILL_WITH_EQUIP_INVD_FLG_C*/
	public final EZDCStringItem              billWithEquipInvdFlg_C;

    /** CR_REBIL_CD_C*/
	public final EZDCStringItem              crRebilCd_C;


	/**
	 * NSAL1320_CCMsg is constructor.
	 * The initialization when the instance of NSAL1320_CCMsg is generated.
	 */
	public NSAL1320_CCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1320_CCMsg is constructor.
	 * The initialization when the instance of NSAL1320_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1320_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		shellLineNum_C = (EZDCBigDecimalItem)newItem("shellLineNum_C");
		dsOrdPosnNum_C = (EZDCStringItem)newItem("dsOrdPosnNum_C");
		dsContrDtlPk_C = (EZDCBigDecimalItem)newItem("dsContrDtlPk_C");
		dsContrPk_C = (EZDCBigDecimalItem)newItem("dsContrPk_C");
		cpoOrdNum_C = (EZDCStringItem)newItem("cpoOrdNum_C");
		cpoDtlLineNum_C = (EZDCStringItem)newItem("cpoDtlLineNum_C");
		cpoDtlLineSubNum_C = (EZDCStringItem)newItem("cpoDtlLineSubNum_C");
		svcConfigMstrPk_C = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_C");
		custIssPoNum_C = (EZDCStringItem)newItem("custIssPoNum_C");
		custIssPoDt_C = (EZDCDateItem)newItem("custIssPoDt_C");
		mtrReadMethCd_C = (EZDCStringItem)newItem("mtrReadMethCd_C");
		svcPrcCatgCd_C = (EZDCStringItem)newItem("svcPrcCatgCd_C");
		billWithEquipInvdFlg_C = (EZDCStringItem)newItem("billWithEquipInvdFlg_C");
		crRebilCd_C = (EZDCStringItem)newItem("crRebilCd_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1320_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1320_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"shellLineNum_C", "shellLineNum_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum_C", "dsOrdPosnNum_C", "C", null, TYPE_HANKAKUEISU, "6", null},
	{"dsContrDtlPk_C", "dsContrDtlPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk_C", "dsContrPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoOrdNum_C", "cpoOrdNum_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum_C", "cpoDtlLineNum_C", "C", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_C", "cpoDtlLineSubNum_C", "C", null, TYPE_HANKAKUEISU, "3", null},
	{"svcConfigMstrPk_C", "svcConfigMstrPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"custIssPoNum_C", "custIssPoNum_C", "C", null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt_C", "custIssPoDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrReadMethCd_C", "mtrReadMethCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"svcPrcCatgCd_C", "svcPrcCatgCd_C", "C", null, TYPE_HANKAKUEISU, "4", null},
	{"billWithEquipInvdFlg_C", "billWithEquipInvdFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"crRebilCd_C", "crRebilCd_C", "C", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SHELL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shellLineNum_C
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_C
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_C
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_C
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_C
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_C
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_C
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_C
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_C
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt_C
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_C
        {"SVC_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPrcCatgCd_C
        {"BILL_WITH_EQUIP_INVD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billWithEquipInvdFlg_C
        {"CR_REBIL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd_C
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

