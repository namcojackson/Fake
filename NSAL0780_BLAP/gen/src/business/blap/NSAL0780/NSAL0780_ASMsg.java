//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170310105825000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0780_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0780;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0780 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0780_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_A*/
	public final EZDSBigDecimalItem              dsContrPk_A;

    /** DS_CONTR_NUM_A*/
	public final EZDSStringItem              dsContrNum_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDSStringItem              mtrLbDescTxt_A;

    /** BLLG_FROM_DT_A*/
	public final EZDSDateItem              bllgFromDt_A;

    /** BLLG_THRU_DT_A*/
	public final EZDSDateItem              bllgThruDt_A;

    /** CUR_FLEET_READ_MTR_CNT_A*/
	public final EZDSBigDecimalItem              curFleetReadMtrCnt_A;

    /** TEST_COPY_QTY_A*/
	public final EZDSBigDecimalItem              testCopyQty_A;

    /** MTR_ENTRY_CPLT_FLG_A*/
	public final EZDSStringItem              mtrEntryCpltFlg_A;

    /** FLEET_CALC_PROC_DESC_TXT_A*/
	public final EZDSStringItem              fleetCalcProcDescTxt_A;

    /** VLD_MSG_TXT_A*/
	public final EZDSStringItem              vldMsgTxt_A;

    /** FLEET_READ_ROLL_UP_PK_A*/
	public final EZDSBigDecimalItem              fleetReadRollUpPk_A;

    /** SVC_CR_REBIL_DTL_PK_A*/
	public final EZDSBigDecimalItem              svcCrRebilDtlPk_A;


	/**
	 * NSAL0780_ASMsg is constructor.
	 * The initialization when the instance of NSAL0780_ASMsg is generated.
	 */
	public NSAL0780_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0780_ASMsg is constructor.
	 * The initialization when the instance of NSAL0780_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0780_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_A = (EZDSBigDecimalItem)newItem("dsContrPk_A");
		dsContrNum_A = (EZDSStringItem)newItem("dsContrNum_A");
		mtrLbDescTxt_A = (EZDSStringItem)newItem("mtrLbDescTxt_A");
		bllgFromDt_A = (EZDSDateItem)newItem("bllgFromDt_A");
		bllgThruDt_A = (EZDSDateItem)newItem("bllgThruDt_A");
		curFleetReadMtrCnt_A = (EZDSBigDecimalItem)newItem("curFleetReadMtrCnt_A");
		testCopyQty_A = (EZDSBigDecimalItem)newItem("testCopyQty_A");
		mtrEntryCpltFlg_A = (EZDSStringItem)newItem("mtrEntryCpltFlg_A");
		fleetCalcProcDescTxt_A = (EZDSStringItem)newItem("fleetCalcProcDescTxt_A");
		vldMsgTxt_A = (EZDSStringItem)newItem("vldMsgTxt_A");
		fleetReadRollUpPk_A = (EZDSBigDecimalItem)newItem("fleetReadRollUpPk_A");
		svcCrRebilDtlPk_A = (EZDSBigDecimalItem)newItem("svcCrRebilDtlPk_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0780_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0780_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_A", "dsContrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgFromDt_A", "bllgFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgThruDt_A", "bllgThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"curFleetReadMtrCnt_A", "curFleetReadMtrCnt_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"testCopyQty_A", "testCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mtrEntryCpltFlg_A", "mtrEntryCpltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"fleetCalcProcDescTxt_A", "fleetCalcProcDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"vldMsgTxt_A", "vldMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"fleetReadRollUpPk_A", "fleetReadRollUpPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilDtlPk_A", "svcCrRebilDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"BLLG_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFromDt_A
        {"BLLG_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgThruDt_A
        {"CUR_FLEET_READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curFleetReadMtrCnt_A
        {"TEST_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//testCopyQty_A
        {"MTR_ENTRY_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCpltFlg_A
        {"FLEET_CALC_PROC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fleetCalcProcDescTxt_A
        {"VLD_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMsgTxt_A
        {"FLEET_READ_ROLL_UP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fleetReadRollUpPk_A
        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk_A
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
