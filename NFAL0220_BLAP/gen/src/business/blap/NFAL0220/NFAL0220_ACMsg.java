//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160621092201000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0220_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL0220;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL0220 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0220_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** XX_NUM_A*/
	public final EZDCBigDecimalItem              xxNum_A;

    /** XX_SCR_ITEM_61_TXT_A*/
	public final EZDCStringItem              xxScrItem61Txt_A;

    /** COA_CMPY_CD_A*/
	public final EZDCStringItem              coaCmpyCd_A;

    /** COA_BR_CD_A*/
	public final EZDCStringItem              coaBrCd_A;

    /** COA_CC_CD_A*/
	public final EZDCStringItem              coaCcCd_A;

    /** COA_ACCT_CD_A*/
	public final EZDCStringItem              coaAcctCd_A;

    /** COA_PROD_CD_A*/
	public final EZDCStringItem              coaProdCd_A;

    /** COA_CH_CD_A*/
	public final EZDCStringItem              coaChCd_A;

    /** COA_AFFL_CD_A*/
	public final EZDCStringItem              coaAfflCd_A;

    /** COA_PROJ_CD_A*/
	public final EZDCStringItem              coaProjCd_A;

    /** COA_EXTN_CD_A*/
	public final EZDCStringItem              coaExtnCd_A;

    /** DR_CR_TP_CD_A*/
	public final EZDCStringItem              drCrTpCd_A;

    /** JRNL_DEAL_AMT_A1*/
	public final EZDCBigDecimalItem              jrnlDealAmt_A1;

    /** JRNL_DEAL_AMT_A2*/
	public final EZDCBigDecimalItem              jrnlDealAmt_A2;

    /** MAN_JRNL_LINE_TXT_A*/
	public final EZDCStringItem              manJrnlLineTxt_A;

    /** DS_ACCT_NUM_A*/
	public final EZDCStringItem              dsAcctNum_A;

    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;

    /** JRNL_ENTRY_SRC_TP_CD_LS*/
	public final EZDCStringItem              jrnlEntrySrcTpCd_LS;

    /** JRNL_ENTRY_SRC_VAL_TXT_A*/
	public final EZDCStringItem              jrnlEntrySrcValTxt_A;

    /** MAN_JRNL_ENTRY_DTL_PK_A*/
	public final EZDCBigDecimalItem              manJrnlEntryDtlPk_A;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;


	/**
	 * NFAL0220_ACMsg is constructor.
	 * The initialization when the instance of NFAL0220_ACMsg is generated.
	 */
	public NFAL0220_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0220_ACMsg is constructor.
	 * The initialization when the instance of NFAL0220_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0220_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		xxNum_A = (EZDCBigDecimalItem)newItem("xxNum_A");
		xxScrItem61Txt_A = (EZDCStringItem)newItem("xxScrItem61Txt_A");
		coaCmpyCd_A = (EZDCStringItem)newItem("coaCmpyCd_A");
		coaBrCd_A = (EZDCStringItem)newItem("coaBrCd_A");
		coaCcCd_A = (EZDCStringItem)newItem("coaCcCd_A");
		coaAcctCd_A = (EZDCStringItem)newItem("coaAcctCd_A");
		coaProdCd_A = (EZDCStringItem)newItem("coaProdCd_A");
		coaChCd_A = (EZDCStringItem)newItem("coaChCd_A");
		coaAfflCd_A = (EZDCStringItem)newItem("coaAfflCd_A");
		coaProjCd_A = (EZDCStringItem)newItem("coaProjCd_A");
		coaExtnCd_A = (EZDCStringItem)newItem("coaExtnCd_A");
		drCrTpCd_A = (EZDCStringItem)newItem("drCrTpCd_A");
		jrnlDealAmt_A1 = (EZDCBigDecimalItem)newItem("jrnlDealAmt_A1");
		jrnlDealAmt_A2 = (EZDCBigDecimalItem)newItem("jrnlDealAmt_A2");
		manJrnlLineTxt_A = (EZDCStringItem)newItem("manJrnlLineTxt_A");
		dsAcctNum_A = (EZDCStringItem)newItem("dsAcctNum_A");
		serNum_A = (EZDCStringItem)newItem("serNum_A");
		jrnlEntrySrcTpCd_LS = (EZDCStringItem)newItem("jrnlEntrySrcTpCd_LS");
		jrnlEntrySrcValTxt_A = (EZDCStringItem)newItem("jrnlEntrySrcValTxt_A");
		manJrnlEntryDtlPk_A = (EZDCBigDecimalItem)newItem("manJrnlEntryDtlPk_A");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0220_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0220_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxNum_A", "xxNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxScrItem61Txt_A", "xxScrItem61Txt_A", "A", null, TYPE_HANKAKUEISU, "61", null},
	{"coaCmpyCd_A", "coaCmpyCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_A", "coaBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_A", "coaCcCd_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd_A", "coaAcctCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_A", "coaProdCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"coaChCd_A", "coaChCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"coaAfflCd_A", "coaAfflCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"coaProjCd_A", "coaProjCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"coaExtnCd_A", "coaExtnCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"drCrTpCd_A", "drCrTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"jrnlDealAmt_A1", "jrnlDealAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"jrnlDealAmt_A2", "jrnlDealAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"manJrnlLineTxt_A", "manJrnlLineTxt_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"jrnlEntrySrcTpCd_LS", "jrnlEntrySrcTpCd_LS", "LS", null, TYPE_HANKAKUEISU, "3", null},
	{"jrnlEntrySrcValTxt_A", "jrnlEntrySrcValTxt_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"manJrnlEntryDtlPk_A", "manJrnlEntryDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_A
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_A
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_A
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_A
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_A
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_A
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_A
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_A
        {"DR_CR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//drCrTpCd_A
        {"JRNL_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlDealAmt_A1
        {"JRNL_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlDealAmt_A2
        {"MAN_JRNL_LINE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manJrnlLineTxt_A
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"JRNL_ENTRY_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlEntrySrcTpCd_LS
        {"JRNL_ENTRY_SRC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlEntrySrcValTxt_A
        {"MAN_JRNL_ENTRY_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manJrnlEntryDtlPk_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

