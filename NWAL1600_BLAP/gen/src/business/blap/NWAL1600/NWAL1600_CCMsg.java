//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230627105750000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1600_CCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1600 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1600_CCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD_C*/
	public final EZDCStringItem              xxRqstTpCd_C;

    /** DS_ORD_POSN_NUM_C*/
	public final EZDCStringItem              dsOrdPosnNum_C;

    /** CONFIG_CATG_CD_C*/
	public final EZDCStringItem              configCatgCd_C;

    /** DS_CPO_SLS_CR_PK_C*/
	public final EZDCBigDecimalItem              dsCpoSlsCrPk_C;

    /** SLS_CR_QUOT_FLG_C*/
	public final EZDCStringItem              slsCrQuotFlg_C;

    /** SLS_REP_TOC_CD_C*/
	public final EZDCStringItem              slsRepTocCd_C;

    /** LINE_BIZ_ROLE_TP_CD_C*/
	public final EZDCStringItem              lineBizRoleTpCd_C;

    /** SLS_REP_CR_PCT_C*/
	public final EZDCBigDecimalItem              slsRepCrPct_C;


	/**
	 * NWAL1600_CCMsg is constructor.
	 * The initialization when the instance of NWAL1600_CCMsg is generated.
	 */
	public NWAL1600_CCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1600_CCMsg is constructor.
	 * The initialization when the instance of NWAL1600_CCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1600_CCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd_C = (EZDCStringItem)newItem("xxRqstTpCd_C");
		dsOrdPosnNum_C = (EZDCStringItem)newItem("dsOrdPosnNum_C");
		configCatgCd_C = (EZDCStringItem)newItem("configCatgCd_C");
		dsCpoSlsCrPk_C = (EZDCBigDecimalItem)newItem("dsCpoSlsCrPk_C");
		slsCrQuotFlg_C = (EZDCStringItem)newItem("slsCrQuotFlg_C");
		slsRepTocCd_C = (EZDCStringItem)newItem("slsRepTocCd_C");
		lineBizRoleTpCd_C = (EZDCStringItem)newItem("lineBizRoleTpCd_C");
		slsRepCrPct_C = (EZDCBigDecimalItem)newItem("slsRepCrPct_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1600_CCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1600_CCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd_C", "xxRqstTpCd_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdPosnNum_C", "dsOrdPosnNum_C", "C", null, TYPE_HANKAKUEISU, "6", null},
	{"configCatgCd_C", "configCatgCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCpoSlsCrPk_C", "dsCpoSlsCrPk_C", "C", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"slsCrQuotFlg_C", "slsCrQuotFlg_C", "C", null, TYPE_HANKAKUEISU, "1", null},
	{"slsRepTocCd_C", "slsRepTocCd_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizRoleTpCd_C", "lineBizRoleTpCd_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"slsRepCrPct_C", "slsRepCrPct_C", "C", null, TYPE_SEISU_SYOSU, "5", "2"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd_C
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_C
        {"CONFIG_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd_C
        {"DS_CPO_SLS_CR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoSlsCrPk_C
        {"SLS_CR_QUOT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCrQuotFlg_C
        {"SLS_REP_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_C
        {"LINE_BIZ_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpCd_C
        {"SLS_REP_CR_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_C
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

