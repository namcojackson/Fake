//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180419113030000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1230_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1230;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1230 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1230_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_P1*/
	public final EZDBBigDecimalItem              xxRowNum_P1;

    /** APP_FUNC_ID_P1*/
	public final EZDBStringItem              appFuncId_P1;

    /** COA_CMPY_CD_P1*/
	public final EZDBStringItem              coaCmpyCd_P1;

    /** COA_EXTN_CD_P1*/
	public final EZDBStringItem              coaExtnCd_P1;

    /** COA_BR_CD_P1*/
	public final EZDBStringItem              coaBrCd_P1;

    /** COA_CC_CD_P1*/
	public final EZDBStringItem              coaCcCd_P1;

    /** COA_ACCT_CD_P1*/
	public final EZDBStringItem              coaAcctCd_P1;

    /** COA_PROJ_CD_P1*/
	public final EZDBStringItem              coaProjCd_P1;

    /** COA_PROD_CD_P1*/
	public final EZDBStringItem              coaProdCd_P1;

    /** COA_AFFL_CD_P1*/
	public final EZDBStringItem              coaAfflCd_P1;

    /** COA_CH_CD_P1*/
	public final EZDBStringItem              coaChCd_P1;


	/**
	 * NSAL1230_PBMsg is constructor.
	 * The initialization when the instance of NSAL1230_PBMsg is generated.
	 */
	public NSAL1230_PBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1230_PBMsg is constructor.
	 * The initialization when the instance of NSAL1230_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1230_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_P1 = (EZDBBigDecimalItem)newItem("xxRowNum_P1");
		appFuncId_P1 = (EZDBStringItem)newItem("appFuncId_P1");
		coaCmpyCd_P1 = (EZDBStringItem)newItem("coaCmpyCd_P1");
		coaExtnCd_P1 = (EZDBStringItem)newItem("coaExtnCd_P1");
		coaBrCd_P1 = (EZDBStringItem)newItem("coaBrCd_P1");
		coaCcCd_P1 = (EZDBStringItem)newItem("coaCcCd_P1");
		coaAcctCd_P1 = (EZDBStringItem)newItem("coaAcctCd_P1");
		coaProjCd_P1 = (EZDBStringItem)newItem("coaProjCd_P1");
		coaProdCd_P1 = (EZDBStringItem)newItem("coaProdCd_P1");
		coaAfflCd_P1 = (EZDBStringItem)newItem("coaAfflCd_P1");
		coaChCd_P1 = (EZDBStringItem)newItem("coaChCd_P1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1230_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1230_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_P1", "xxRowNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"appFuncId_P1", "appFuncId_P1", "P1", null, TYPE_HANKAKUEISU, "100", null},
	{"coaCmpyCd_P1", "coaCmpyCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnCd_P1", "coaExtnCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_P1", "coaBrCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_P1", "coaCcCd_P1", "P1", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd_P1", "coaAcctCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProjCd_P1", "coaProjCd_P1", "P1", null, TYPE_HANKAKUEISU, "4", null},
	{"coaProdCd_P1", "coaProdCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaAfflCd_P1", "coaAfflCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaChCd_P1", "coaChCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_P1
        {"APP_FUNC_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//appFuncId_P1
        {"COA_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_P1
        {"COA_EXTN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_P1
        {"COA_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_P1
        {"COA_CC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_P1
        {"COA_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_P1
        {"COA_PROJ_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_P1
        {"COA_PROD_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_P1
        {"COA_AFFL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_P1
        {"COA_CH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_P1
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

