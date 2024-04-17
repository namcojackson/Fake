//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190118110523000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_ISMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1330 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_ISMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ORD_POSN_NUM_I*/
	public final EZDSStringItem              dsOrdPosnNum_I;

    /** MDL_ID_I*/
	public final EZDSBigDecimalItem              mdlId_I;

    /** T_MDL_NM_I*/
	public final EZDSStringItem              t_MdlNm_I;

    /** CPO_DTL_LINE_NUM_I*/
	public final EZDSStringItem              cpoDtlLineNum_I;

    /** CPO_DTL_LINE_SUB_NUM_I*/
	public final EZDSStringItem              cpoDtlLineSubNum_I;

    /** MTR_READ_METH_CD_I*/
	public final EZDSStringItem              mtrReadMethCd_I;

    /** DS_CONTR_DTL_PK_I*/
	public final EZDSBigDecimalItem              dsContrDtlPk_I;

    /** CR_REBIL_CD_I*/
	public final EZDSStringItem              crRebilCd_I;

    /** SVC_CONFIG_MSTR_PK_I*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_I;

    /** CUST_PO_NUM_I*/
	public final EZDSStringItem              custPoNum_I;

    /** PO_DT_I*/
	public final EZDSDateItem              poDt_I;

    /** DS_PO_EXPR_DT_I*/
	public final EZDSDateItem              dsPoExprDt_I;


	/**
	 * NSAL1330_ISMsg is constructor.
	 * The initialization when the instance of NSAL1330_ISMsg is generated.
	 */
	public NSAL1330_ISMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_ISMsg is constructor.
	 * The initialization when the instance of NSAL1330_ISMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_ISMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsOrdPosnNum_I = (EZDSStringItem)newItem("dsOrdPosnNum_I");
		mdlId_I = (EZDSBigDecimalItem)newItem("mdlId_I");
		t_MdlNm_I = (EZDSStringItem)newItem("t_MdlNm_I");
		cpoDtlLineNum_I = (EZDSStringItem)newItem("cpoDtlLineNum_I");
		cpoDtlLineSubNum_I = (EZDSStringItem)newItem("cpoDtlLineSubNum_I");
		mtrReadMethCd_I = (EZDSStringItem)newItem("mtrReadMethCd_I");
		dsContrDtlPk_I = (EZDSBigDecimalItem)newItem("dsContrDtlPk_I");
		crRebilCd_I = (EZDSStringItem)newItem("crRebilCd_I");
		svcConfigMstrPk_I = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_I");
		custPoNum_I = (EZDSStringItem)newItem("custPoNum_I");
		poDt_I = (EZDSDateItem)newItem("poDt_I");
		dsPoExprDt_I = (EZDSDateItem)newItem("dsPoExprDt_I");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_ISMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_ISMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsOrdPosnNum_I", "dsOrdPosnNum_I", "I", null, TYPE_HANKAKUEISU, "6", null},
	{"mdlId_I", "mdlId_I", "I", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"t_MdlNm_I", "t_MdlNm_I", "I", null, TYPE_HANKAKUEISU, "50", null},
	{"cpoDtlLineNum_I", "cpoDtlLineNum_I", "I", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_I", "cpoDtlLineSubNum_I", "I", null, TYPE_HANKAKUEISU, "3", null},
	{"mtrReadMethCd_I", "mtrReadMethCd_I", "I", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrDtlPk_I", "dsContrDtlPk_I", "I", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"crRebilCd_I", "crRebilCd_I", "I", null, TYPE_HANKAKUEISU, "20", null},
	{"svcConfigMstrPk_I", "svcConfigMstrPk_I", "I", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"custPoNum_I", "custPoNum_I", "I", null, TYPE_HANKAKUEISU, "35", null},
	{"poDt_I", "poDt_I", "I", null, TYPE_NENTSUKIHI, "8", null},
	{"dsPoExprDt_I", "dsPoExprDt_I", "I", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_I
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_I
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_I
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_I
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_I
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_I
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_I
        {"CR_REBIL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRebilCd_I
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_I
        {"CUST_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum_I
        {"PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDt_I
        {"DS_PO_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPoExprDt_I
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

