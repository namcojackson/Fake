//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160712121008000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7160BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7160 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7160BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** INV_NUM*/
	public final EZDBStringItem              invNum;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** MDSE_CD_LK*/
	public final EZDBStringItem              mdseCd_LK;

    /** CSMP_CONTR_NUM*/
	public final EZDBStringItem              csmpContrNum;

    /** DLR_REF_NUM*/
	public final EZDBStringItem              dlrRefNum;

    /** CSMP_INV_ERR_DT_FR*/
	public final EZDBDateItem              csmpInvErrDt_FR;

    /** CSMP_INV_ERR_DT_TO*/
	public final EZDBDateItem              csmpInvErrDt_TO;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NMAL7160.NMAL7160_ABMsgArray              A;

    /** XX_POP_PRM_00*/
	public final EZDBStringItem              xxPopPrm_00;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;


	/**
	 * NMAL7160BMsg is constructor.
	 * The initialization when the instance of NMAL7160BMsg is generated.
	 */
	public NMAL7160BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7160BMsg is constructor.
	 * The initialization when the instance of NMAL7160BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7160BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		invNum = (EZDBStringItem)newItem("invNum");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		mdseCd_LK = (EZDBStringItem)newItem("mdseCd_LK");
		csmpContrNum = (EZDBStringItem)newItem("csmpContrNum");
		dlrRefNum = (EZDBStringItem)newItem("dlrRefNum");
		csmpInvErrDt_FR = (EZDBDateItem)newItem("csmpInvErrDt_FR");
		csmpInvErrDt_TO = (EZDBDateItem)newItem("csmpInvErrDt_TO");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NMAL7160.NMAL7160_ABMsgArray)newMsgArray("A");
		xxPopPrm_00 = (EZDBStringItem)newItem("xxPopPrm_00");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7160BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7160BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseCd_LK", "mdseCd_LK", "LK", null, TYPE_HANKAKUEISU, "16", null},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"csmpInvErrDt_FR", "csmpInvErrDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"csmpInvErrDt_TO", "csmpInvErrDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.servlet.NMAL7160.NMAL7160_ABMsgArray", null, null},
	
	{"xxPopPrm_00", "xxPopPrm_00", "00", null, TYPE_HANKAKUEISU, "300", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_LK
        {"CSMP_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"DLR_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"CSMP_INV_ERR_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//csmpInvErrDt_FR
        {"CSMP_INV_ERR_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//csmpInvErrDt_TO
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_00
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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
