//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190109181337000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7260_TCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7260;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7260 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7260_TCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DEL_FLG_T*/
	public final EZDCStringItem              delFlg_T;

    /** PRNT_PRC_ADJ_DTL_PK_T*/
	public final EZDCBigDecimalItem              prntPrcAdjDtlPk_T;

    /** QTY_DISC_DTL_QTY_T*/
	public final EZDCBigDecimalItem              qtyDiscDtlQty_T;

    /** PRC_RULE_DTL_RATE_T*/
	public final EZDCBigDecimalItem              prcRuleDtlRate_T;

    /** PRC_RULE_DTL_TXT_T*/
	public final EZDCStringItem              prcRuleDtlTxt_T;

    /** PRC_ADJ_DTL_PK_T*/
	public final EZDCBigDecimalItem              prcAdjDtlPk_T;

    /** PRC_RULE_DTL_PK_T*/
	public final EZDCBigDecimalItem              prcRuleDtlPk_T;

    /** _EZUpdateDateTime_T1*/
	public final EZDCStringItem              ezUpTime_T1;

    /** _EZUpTimeZone_T1*/
	public final EZDCStringItem              ezUpTimeZone_T1;

    /** SPEC_COND_PRC_PK_T*/
	public final EZDCBigDecimalItem              specCondPrcPk_T;

    /** _EZUpdateDateTime_T3*/
	public final EZDCStringItem              ezUpTime_T3;

    /** _EZUpTimeZone_T3*/
	public final EZDCStringItem              ezUpTimeZone_T3;

    /** XX_MODE_CD_T*/
	public final EZDCStringItem              xxModeCd_T;


	/**
	 * NMAL7260_TCMsg is constructor.
	 * The initialization when the instance of NMAL7260_TCMsg is generated.
	 */
	public NMAL7260_TCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7260_TCMsg is constructor.
	 * The initialization when the instance of NMAL7260_TCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7260_TCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		delFlg_T = (EZDCStringItem)newItem("delFlg_T");
		prntPrcAdjDtlPk_T = (EZDCBigDecimalItem)newItem("prntPrcAdjDtlPk_T");
		qtyDiscDtlQty_T = (EZDCBigDecimalItem)newItem("qtyDiscDtlQty_T");
		prcRuleDtlRate_T = (EZDCBigDecimalItem)newItem("prcRuleDtlRate_T");
		prcRuleDtlTxt_T = (EZDCStringItem)newItem("prcRuleDtlTxt_T");
		prcAdjDtlPk_T = (EZDCBigDecimalItem)newItem("prcAdjDtlPk_T");
		prcRuleDtlPk_T = (EZDCBigDecimalItem)newItem("prcRuleDtlPk_T");
		ezUpTime_T1 = (EZDCStringItem)newItem("ezUpTime_T1");
		ezUpTimeZone_T1 = (EZDCStringItem)newItem("ezUpTimeZone_T1");
		specCondPrcPk_T = (EZDCBigDecimalItem)newItem("specCondPrcPk_T");
		ezUpTime_T3 = (EZDCStringItem)newItem("ezUpTime_T3");
		ezUpTimeZone_T3 = (EZDCStringItem)newItem("ezUpTimeZone_T3");
		xxModeCd_T = (EZDCStringItem)newItem("xxModeCd_T");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7260_TCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7260_TCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"delFlg_T", "delFlg_T", "T", null, TYPE_HANKAKUEISU, "1", null},
	{"prntPrcAdjDtlPk_T", "prntPrcAdjDtlPk_T", "T", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"qtyDiscDtlQty_T", "qtyDiscDtlQty_T", "T", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"prcRuleDtlRate_T", "prcRuleDtlRate_T", "T", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"prcRuleDtlTxt_T", "prcRuleDtlTxt_T", "T", null, TYPE_HANKAKUEISU, "50", null},
	{"prcAdjDtlPk_T", "prcAdjDtlPk_T", "T", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleDtlPk_T", "prcRuleDtlPk_T", "T", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_T1", "ezUpTime_T1", "T1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_T1", "ezUpTimeZone_T1", "T1", null, TYPE_HANKAKUEISU, "5", null},
	{"specCondPrcPk_T", "specCondPrcPk_T", "T", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_T3", "ezUpTime_T3", "T3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_T3", "ezUpTimeZone_T3", "T3", null, TYPE_HANKAKUEISU, "5", null},
	{"xxModeCd_T", "xxModeCd_T", "T", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_T
        {"PRNT_PRC_ADJ_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntPrcAdjDtlPk_T
        {"QTY_DISC_DTL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//qtyDiscDtlQty_T
        {"PRC_RULE_DTL_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleDtlRate_T
        {"PRC_RULE_DTL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleDtlTxt_T
        {"PRC_ADJ_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAdjDtlPk_T
        {"PRC_RULE_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleDtlPk_T
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_T1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_T1
        {"SPEC_COND_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//specCondPrcPk_T
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_T3
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_T3
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_T
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

