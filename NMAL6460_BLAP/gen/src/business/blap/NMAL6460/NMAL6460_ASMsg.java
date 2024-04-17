//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160519111729000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6460_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6460;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6460 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6460_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_A1*/
	public final EZDSStringItem              glblCmpyCd_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** PMT_TERM_CASH_DISC_CD_A1*/
	public final EZDSStringItem              pmtTermCashDiscCd_A1;

    /** PMT_TERM_CASH_DISC_NM_A1*/
	public final EZDSStringItem              pmtTermCashDiscNm_A1;

    /** PMT_TERM_CASH_DISC_SORT_NUM_A1*/
	public final EZDSBigDecimalItem              pmtTermCashDiscSortNum_A1;

    /** PMT_TERM_CASH_DISC_DESC_TXT_A1*/
	public final EZDSStringItem              pmtTermCashDiscDescTxt_A1;

    /** PMT_TERM_CD_A1*/
	public final EZDSStringItem              pmtTermCd_A1;

    /** PMT_TERM_NM_A1*/
	public final EZDSStringItem              pmtTermNm_A1;

    /** CASH_DISC_TERM_CD_A1*/
	public final EZDSStringItem              cashDiscTermCd_A1;

    /** EDI_CASH_DISC_DUE_AOT_A1*/
	public final EZDSBigDecimalItem              ediCashDiscDueAot_A1;

    /** EDI_CASH_DISC_PCT_A1*/
	public final EZDSBigDecimalItem              ediCashDiscPct_A1;

    /** PMT_TERM_SEND_FAX_FLG_A1*/
	public final EZDSStringItem              pmtTermSendFaxFlg_A1;

    /** ISTL_PMT_TERM_FLG_A1*/
	public final EZDSStringItem              istlPmtTermFlg_A1;

    /** ACTL_EXCH_RATE_TP_NUM_A1*/
	public final EZDSStringItem              actlExchRateTpNum_A1;

    /** PMT_TERM_CONSL_FLG_A1*/
	public final EZDSStringItem              pmtTermConslFlg_A1;

    /** PMT_TERM_CONSL_LAST_DOM_FLG_A1*/
	public final EZDSStringItem              pmtTermConslLastDomFlg_A1;

    /** PMT_TERM_CONSL_DUE_DAY_A1*/
	public final EZDSStringItem              pmtTermConslDueDay_A1;

    /** PMT_TERM_CONSL_ADD_MTH_NUM_A1*/
	public final EZDSBigDecimalItem              pmtTermConslAddMthNum_A1;

    /** PMT_CASH_FLG_A1*/
	public final EZDSStringItem              pmtCashFlg_A1;

    /** PMT_CC_FLG_A1*/
	public final EZDSStringItem              pmtCcFlg_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL6460_ASMsg is constructor.
	 * The initialization when the instance of NMAL6460_ASMsg is generated.
	 */
	public NMAL6460_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6460_ASMsg is constructor.
	 * The initialization when the instance of NMAL6460_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6460_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_A1 = (EZDSStringItem)newItem("glblCmpyCd_A1");
		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		pmtTermCashDiscCd_A1 = (EZDSStringItem)newItem("pmtTermCashDiscCd_A1");
		pmtTermCashDiscNm_A1 = (EZDSStringItem)newItem("pmtTermCashDiscNm_A1");
		pmtTermCashDiscSortNum_A1 = (EZDSBigDecimalItem)newItem("pmtTermCashDiscSortNum_A1");
		pmtTermCashDiscDescTxt_A1 = (EZDSStringItem)newItem("pmtTermCashDiscDescTxt_A1");
		pmtTermCd_A1 = (EZDSStringItem)newItem("pmtTermCd_A1");
		pmtTermNm_A1 = (EZDSStringItem)newItem("pmtTermNm_A1");
		cashDiscTermCd_A1 = (EZDSStringItem)newItem("cashDiscTermCd_A1");
		ediCashDiscDueAot_A1 = (EZDSBigDecimalItem)newItem("ediCashDiscDueAot_A1");
		ediCashDiscPct_A1 = (EZDSBigDecimalItem)newItem("ediCashDiscPct_A1");
		pmtTermSendFaxFlg_A1 = (EZDSStringItem)newItem("pmtTermSendFaxFlg_A1");
		istlPmtTermFlg_A1 = (EZDSStringItem)newItem("istlPmtTermFlg_A1");
		actlExchRateTpNum_A1 = (EZDSStringItem)newItem("actlExchRateTpNum_A1");
		pmtTermConslFlg_A1 = (EZDSStringItem)newItem("pmtTermConslFlg_A1");
		pmtTermConslLastDomFlg_A1 = (EZDSStringItem)newItem("pmtTermConslLastDomFlg_A1");
		pmtTermConslDueDay_A1 = (EZDSStringItem)newItem("pmtTermConslDueDay_A1");
		pmtTermConslAddMthNum_A1 = (EZDSBigDecimalItem)newItem("pmtTermConslAddMthNum_A1");
		pmtCashFlg_A1 = (EZDSStringItem)newItem("pmtCashFlg_A1");
		pmtCcFlg_A1 = (EZDSStringItem)newItem("pmtCcFlg_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6460_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6460_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_A1", "glblCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"pmtTermCashDiscCd_A1", "pmtTermCashDiscCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"pmtTermCashDiscNm_A1", "pmtTermCashDiscNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"pmtTermCashDiscSortNum_A1", "pmtTermCashDiscSortNum_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"pmtTermCashDiscDescTxt_A1", "pmtTermCashDiscDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"pmtTermCd_A1", "pmtTermCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"pmtTermNm_A1", "pmtTermNm_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"cashDiscTermCd_A1", "cashDiscTermCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"ediCashDiscDueAot_A1", "ediCashDiscDueAot_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ediCashDiscPct_A1", "ediCashDiscPct_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"pmtTermSendFaxFlg_A1", "pmtTermSendFaxFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"istlPmtTermFlg_A1", "istlPmtTermFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"actlExchRateTpNum_A1", "actlExchRateTpNum_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pmtTermConslFlg_A1", "pmtTermConslFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pmtTermConslLastDomFlg_A1", "pmtTermConslLastDomFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pmtTermConslDueDay_A1", "pmtTermConslDueDay_A1", "A1", null, TYPE_HANKAKUSUJI, "2", null},
	{"pmtTermConslAddMthNum_A1", "pmtTermConslAddMthNum_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"pmtCashFlg_A1", "pmtCashFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pmtCcFlg_A1", "pmtCcFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd_A1
        {"PMT_TERM_CASH_DISC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscNm_A1
        {"PMT_TERM_CASH_DISC_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscSortNum_A1
        {"PMT_TERM_CASH_DISC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscDescTxt_A1
        {"PMT_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCd_A1
        {"PMT_TERM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermNm_A1
        {"CASH_DISC_TERM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cashDiscTermCd_A1
        {"EDI_CASH_DISC_DUE_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediCashDiscDueAot_A1
        {"EDI_CASH_DISC_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediCashDiscPct_A1
        {"PMT_TERM_SEND_FAX_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermSendFaxFlg_A1
        {"ISTL_PMT_TERM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlPmtTermFlg_A1
        {"ACTL_EXCH_RATE_TP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actlExchRateTpNum_A1
        {"PMT_TERM_CONSL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermConslFlg_A1
        {"PMT_TERM_CONSL_LAST_DOM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermConslLastDomFlg_A1
        {"PMT_TERM_CONSL_DUE_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermConslDueDay_A1
        {"PMT_TERM_CONSL_ADD_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermConslAddMthNum_A1
        {"PMT_CASH_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtCashFlg_A1
        {"PMT_CC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtCcFlg_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

