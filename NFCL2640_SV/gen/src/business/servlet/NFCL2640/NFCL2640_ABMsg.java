//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160308105642000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2640_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL2640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2640 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2640_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_STMT_DT_A1*/
	public final EZDBDateItem              arStmtDt_A1;

    /** AR_STMT_STS_CD_A1*/
	public final EZDBStringItem              arStmtStsCd_A1;

    /** AR_STMT_STS_CD_A2*/
	public final EZDBStringItemArray              arStmtStsCd_A2;

    /** AR_STMT_STS_NM_A2*/
	public final EZDBStringItemArray              arStmtStsNm_A2;

    /** AR_STMT_DATA_CRAT_DT_A1*/
	public final EZDBDateItem              arStmtDataCratDt_A1;

    /** AR_STMT_PRINT_DT_A1*/
	public final EZDBDateItem              arStmtPrintDt_A1;

    /** LATE_FEE_CALC_FLG_A1*/
	public final EZDBStringItem              lateFeeCalcFlg_A1;

    /** AR_STMT_CTRL_PK_A1*/
	public final EZDBBigDecimalItem              arStmtCtrlPk_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;


	/**
	 * NFCL2640_ABMsg is constructor.
	 * The initialization when the instance of NFCL2640_ABMsg is generated.
	 */
	public NFCL2640_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2640_ABMsg is constructor.
	 * The initialization when the instance of NFCL2640_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2640_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arStmtDt_A1 = (EZDBDateItem)newItem("arStmtDt_A1");
		arStmtStsCd_A1 = (EZDBStringItem)newItem("arStmtStsCd_A1");
		arStmtStsCd_A2 = (EZDBStringItemArray)newItemArray("arStmtStsCd_A2");
		arStmtStsNm_A2 = (EZDBStringItemArray)newItemArray("arStmtStsNm_A2");
		arStmtDataCratDt_A1 = (EZDBDateItem)newItem("arStmtDataCratDt_A1");
		arStmtPrintDt_A1 = (EZDBDateItem)newItem("arStmtPrintDt_A1");
		lateFeeCalcFlg_A1 = (EZDBStringItem)newItem("lateFeeCalcFlg_A1");
		arStmtCtrlPk_A1 = (EZDBBigDecimalItem)newItem("arStmtCtrlPk_A1");
		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2640_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2640_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arStmtDt_A1", "arStmtDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"arStmtStsCd_A1", "arStmtStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"arStmtStsCd_A2", "arStmtStsCd_A2", "A2", "99", TYPE_HANKAKUEISU, "1", null},
	{"arStmtStsNm_A2", "arStmtStsNm_A2", "A2", "99", TYPE_HANKAKUEISU, "30", null},
	{"arStmtDataCratDt_A1", "arStmtDataCratDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"arStmtPrintDt_A1", "arStmtPrintDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"lateFeeCalcFlg_A1", "lateFeeCalcFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"arStmtCtrlPk_A1", "arStmtCtrlPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_STMT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arStmtDt_A1
        {"AR_STMT_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtStsCd_A1
        {"AR_STMT_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtStsCd_A2
        {"AR_STMT_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtStsNm_A2
        {"AR_STMT_DATA_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arStmtDataCratDt_A1
        {"AR_STMT_PRINT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arStmtPrintDt_A1
        {"LATE_FEE_CALC_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lateFeeCalcFlg_A1
        {"AR_STMT_CTRL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arStmtCtrlPk_A1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

