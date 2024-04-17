//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160712121008000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7160_ABMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL7160_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** CPO_ORD_NUM_A*/
	public final EZDBStringItem              cpoOrdNum_A;

    /** DPLY_LINE_NUM_A*/
	public final EZDBStringItem              dplyLineNum_A;

    /** INV_NUM_A*/
	public final EZDBStringItem              invNum_A;

    /** INV_BOL_LINE_NUM_A*/
	public final EZDBStringItem              invBolLineNum_A;

    /** INV_LINE_NUM_A*/
	public final EZDBStringItem              invLineNum_A;

    /** INV_LINE_SUB_NUM_A*/
	public final EZDBStringItem              invLineSubNum_A;

    /** INV_LINE_SUB_TRX_NUM_A*/
	public final EZDBStringItem              invLineSubTrxNum_A;

    /** MDSE_CD_A*/
	public final EZDBStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDBStringItem              mdseDescShortTxt_A;

    /** CSMP_CONTR_NUM_A*/
	public final EZDBStringItem              csmpContrNum_A;

    /** DLR_REF_NUM_A*/
	public final EZDBStringItem              dlrRefNum_A;

    /** CSMP_INV_ERR_DT_A*/
	public final EZDBDateItem              csmpInvErrDt_A;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;


	/**
	 * NMAL7160_ABMsg is constructor.
	 * The initialization when the instance of NMAL7160_ABMsg is generated.
	 */
	public NMAL7160_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7160_ABMsg is constructor.
	 * The initialization when the instance of NMAL7160_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7160_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		cpoOrdNum_A = (EZDBStringItem)newItem("cpoOrdNum_A");
		dplyLineNum_A = (EZDBStringItem)newItem("dplyLineNum_A");
		invNum_A = (EZDBStringItem)newItem("invNum_A");
		invBolLineNum_A = (EZDBStringItem)newItem("invBolLineNum_A");
		invLineNum_A = (EZDBStringItem)newItem("invLineNum_A");
		invLineSubNum_A = (EZDBStringItem)newItem("invLineSubNum_A");
		invLineSubTrxNum_A = (EZDBStringItem)newItem("invLineSubTrxNum_A");
		mdseCd_A = (EZDBStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDBStringItem)newItem("mdseDescShortTxt_A");
		csmpContrNum_A = (EZDBStringItem)newItem("csmpContrNum_A");
		dlrRefNum_A = (EZDBStringItem)newItem("dlrRefNum_A");
		csmpInvErrDt_A = (EZDBDateItem)newItem("csmpInvErrDt_A");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7160_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7160_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"cpoOrdNum_A", "cpoOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum_A", "dplyLineNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"invNum_A", "invNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"invBolLineNum_A", "invBolLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum_A", "invLineNum_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"invLineSubNum_A", "invLineSubNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineSubTrxNum_A", "invLineSubTrxNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"csmpContrNum_A", "csmpContrNum_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_A", "dlrRefNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"csmpInvErrDt_A", "csmpInvErrDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"CPO_ORD_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A
        {"DPLY_LINE_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A
        {"INV_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A
        {"INV_BOL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum_A
        {"INV_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum_A
        {"INV_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubNum_A
        {"INV_LINE_SUB_TRX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubTrxNum_A
        {"MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"CSMP_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum_A
        {"DLR_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_A
        {"CSMP_INV_ERR_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//csmpInvErrDt_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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
