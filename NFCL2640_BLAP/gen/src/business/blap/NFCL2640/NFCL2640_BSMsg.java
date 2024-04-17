//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160202110818000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2640_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2640 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2640_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_STMT_DT_B1*/
	public final EZDSDateItem              arStmtDt_B1;

    /** AR_STMT_STS_CD_B1*/
	public final EZDSStringItem              arStmtStsCd_B1;

    /** AR_STMT_STS_CD_B2*/
	public final EZDSStringItemArray              arStmtStsCd_B2;

    /** AR_STMT_STS_NM_B2*/
	public final EZDSStringItemArray              arStmtStsNm_B2;

    /** AR_STMT_DATA_CRAT_DT_B1*/
	public final EZDSDateItem              arStmtDataCratDt_B1;

    /** AR_STMT_PRINT_DT_B1*/
	public final EZDSDateItem              arStmtPrintDt_B1;

    /** LATE_FEE_CALC_FLG_B1*/
	public final EZDSStringItem              lateFeeCalcFlg_B1;

    /** AR_STMT_CTRL_PK_B1*/
	public final EZDSBigDecimalItem              arStmtCtrlPk_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;


	/**
	 * NFCL2640_BSMsg is constructor.
	 * The initialization when the instance of NFCL2640_BSMsg is generated.
	 */
	public NFCL2640_BSMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2640_BSMsg is constructor.
	 * The initialization when the instance of NFCL2640_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2640_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arStmtDt_B1 = (EZDSDateItem)newItem("arStmtDt_B1");
		arStmtStsCd_B1 = (EZDSStringItem)newItem("arStmtStsCd_B1");
		arStmtStsCd_B2 = (EZDSStringItemArray)newItemArray("arStmtStsCd_B2");
		arStmtStsNm_B2 = (EZDSStringItemArray)newItemArray("arStmtStsNm_B2");
		arStmtDataCratDt_B1 = (EZDSDateItem)newItem("arStmtDataCratDt_B1");
		arStmtPrintDt_B1 = (EZDSDateItem)newItem("arStmtPrintDt_B1");
		lateFeeCalcFlg_B1 = (EZDSStringItem)newItem("lateFeeCalcFlg_B1");
		arStmtCtrlPk_B1 = (EZDSBigDecimalItem)newItem("arStmtCtrlPk_B1");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2640_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2640_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arStmtDt_B1", "arStmtDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"arStmtStsCd_B1", "arStmtStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"arStmtStsCd_B2", "arStmtStsCd_B2", "B2", "99", TYPE_HANKAKUEISU, "1", null},
	{"arStmtStsNm_B2", "arStmtStsNm_B2", "B2", "99", TYPE_HANKAKUEISU, "30", null},
	{"arStmtDataCratDt_B1", "arStmtDataCratDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"arStmtPrintDt_B1", "arStmtPrintDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"lateFeeCalcFlg_B1", "lateFeeCalcFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"arStmtCtrlPk_B1", "arStmtCtrlPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_STMT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtDt_B1
        {"AR_STMT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtStsCd_B1
        {"AR_STMT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtStsCd_B2
        {"AR_STMT_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtStsNm_B2
        {"AR_STMT_DATA_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtDataCratDt_B1
        {"AR_STMT_PRINT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtPrintDt_B1
        {"LATE_FEE_CALC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lateFeeCalcFlg_B1
        {"AR_STMT_CTRL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtCtrlPk_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
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

