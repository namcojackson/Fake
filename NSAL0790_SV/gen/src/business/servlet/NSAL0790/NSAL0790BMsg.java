//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170321112451000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0790BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0790;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0790 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0790BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** DS_CONTR_PK_H*/
	public final EZDBBigDecimalItem              dsContrPk_H;

    /** DS_CONTR_NUM_H*/
	public final EZDBStringItem              dsContrNum_H;

    /** FLEET_CALC_PROC_CD_H*/
	public final EZDBStringItem              fleetCalcProcCd_H;

    /** FLEET_CALC_PROC_DESC_TXT_H*/
	public final EZDBStringItem              fleetCalcProcDescTxt_H;

    /** VLD_MSG_TXT_H*/
	public final EZDBStringItem              vldMsgTxt_H;

    /** A*/
	public final business.servlet.NSAL0790.NSAL0790_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** SVC_MACH_MSTR_PK_P*/
	public final EZDBBigDecimalItem              svcMachMstrPk_P;

    /** P*/
	public final business.servlet.NSAL0790.NSAL0790_PBMsgArray              P;


	/**
	 * NSAL0790BMsg is constructor.
	 * The initialization when the instance of NSAL0790BMsg is generated.
	 */
	public NSAL0790BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0790BMsg is constructor.
	 * The initialization when the instance of NSAL0790BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0790BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		dsContrPk_H = (EZDBBigDecimalItem)newItem("dsContrPk_H");
		dsContrNum_H = (EZDBStringItem)newItem("dsContrNum_H");
		fleetCalcProcCd_H = (EZDBStringItem)newItem("fleetCalcProcCd_H");
		fleetCalcProcDescTxt_H = (EZDBStringItem)newItem("fleetCalcProcDescTxt_H");
		vldMsgTxt_H = (EZDBStringItem)newItem("vldMsgTxt_H");
		A = (business.servlet.NSAL0790.NSAL0790_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		svcMachMstrPk_P = (EZDBBigDecimalItem)newItem("svcMachMstrPk_P");
		P = (business.servlet.NSAL0790.NSAL0790_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0790BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0790BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrPk_H", "dsContrPk_H", "H", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_H", "dsContrNum_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"fleetCalcProcCd_H", "fleetCalcProcCd_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"fleetCalcProcDescTxt_H", "fleetCalcProcDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"vldMsgTxt_H", "vldMsgTxt_H", "H", null, TYPE_HANKAKUEISU, "1000", null},
	{"A", "A", null, "40", "business.servlet.NSAL0790.NSAL0790_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"svcMachMstrPk_P", "svcMachMstrPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"P", "P", null, "1000", "business.servlet.NSAL0790.NSAL0790_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_H
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H
        {"FLEET_CALC_PROC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fleetCalcProcCd_H
        {"FLEET_CALC_PROC_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fleetCalcProcDescTxt_H
        {"VLD_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMsgTxt_H
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_P
		null,	//P
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
