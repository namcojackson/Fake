//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170913135643000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7120_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7120 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7120_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** CSMP_CONTR_PK_A1*/
	public final EZDCBigDecimalItem              csmpContrPk_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDCStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** CSMP_NUM_A1*/
	public final EZDCStringItem              csmpNum_A1;

    /** DLR_REF_NUM_A1*/
	public final EZDCStringItem              dlrRefNum_A1;

    /** PRC_CATG_CD_A1*/
	public final EZDCStringItem              prcCatgCd_A1;

    /** PRC_CATG_NM_A1*/
	public final EZDCStringItem              prcCatgNm_A1;

    /** PRC_CONTR_NUM_A1*/
	public final EZDCStringItem              prcContrNum_A1;

    /** ORIG_COA_BR_CD_A1*/
	public final EZDCStringItem              origCoaBrCd_A1;

    /** RTL_DLR_CD_A1*/
	public final EZDCStringItem              rtlDlrCd_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDCDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDCDateItem              effThruDt_A1;

    /** CUSA_REJ_DT_A1*/
	public final EZDCDateItem              cusaRejDt_A1;

    /** ERL_TRMN_DT_A1*/
	public final EZDCDateItem              erlTrmnDt_A1;

    /** RNW_CSMP_NUM_A1*/
	public final EZDCStringItem              rnwCsmpNum_A1;

    /** UPLFT_EQUIP_RATIO_A1*/
	public final EZDCBigDecimalItem              uplftEquipRatio_A1;

    /** UPLFT_ASRY_RATIO_A1*/
	public final EZDCBigDecimalItem              uplftAsryRatio_A1;

    /** CSMP_NUM_CMNT_TXT_A1*/
	public final EZDCStringItem              csmpNumCmntTxt_A1;

    /** CSMP_CONTR_ACTV_FLG_A1*/
	public final EZDCStringItem              csmpContrActvFlg_A1;

    /** XX_MODE_NM_13_TXT_A1*/
	public final EZDCStringItem              xxModeNm13Txt_A1;

    /** CUST_MBR_ID_A1*/
	public final EZDCStringItem              custMbrId_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDCStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDCStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL7120_ACMsg is constructor.
	 * The initialization when the instance of NMAL7120_ACMsg is generated.
	 */
	public NMAL7120_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7120_ACMsg is constructor.
	 * The initialization when the instance of NMAL7120_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7120_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		csmpContrPk_A1 = (EZDCBigDecimalItem)newItem("csmpContrPk_A1");
		dsAcctNum_A1 = (EZDCStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		csmpNum_A1 = (EZDCStringItem)newItem("csmpNum_A1");
		dlrRefNum_A1 = (EZDCStringItem)newItem("dlrRefNum_A1");
		prcCatgCd_A1 = (EZDCStringItem)newItem("prcCatgCd_A1");
		prcCatgNm_A1 = (EZDCStringItem)newItem("prcCatgNm_A1");
		prcContrNum_A1 = (EZDCStringItem)newItem("prcContrNum_A1");
		origCoaBrCd_A1 = (EZDCStringItem)newItem("origCoaBrCd_A1");
		rtlDlrCd_A1 = (EZDCStringItem)newItem("rtlDlrCd_A1");
		effFromDt_A1 = (EZDCDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDCDateItem)newItem("effThruDt_A1");
		cusaRejDt_A1 = (EZDCDateItem)newItem("cusaRejDt_A1");
		erlTrmnDt_A1 = (EZDCDateItem)newItem("erlTrmnDt_A1");
		rnwCsmpNum_A1 = (EZDCStringItem)newItem("rnwCsmpNum_A1");
		uplftEquipRatio_A1 = (EZDCBigDecimalItem)newItem("uplftEquipRatio_A1");
		uplftAsryRatio_A1 = (EZDCBigDecimalItem)newItem("uplftAsryRatio_A1");
		csmpNumCmntTxt_A1 = (EZDCStringItem)newItem("csmpNumCmntTxt_A1");
		csmpContrActvFlg_A1 = (EZDCStringItem)newItem("csmpContrActvFlg_A1");
		xxModeNm13Txt_A1 = (EZDCStringItem)newItem("xxModeNm13Txt_A1");
		custMbrId_A1 = (EZDCStringItem)newItem("custMbrId_A1");
		ezUpTime_A1 = (EZDCStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDCStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7120_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7120_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"csmpContrPk_A1", "csmpContrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"csmpNum_A1", "csmpNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_A1", "dlrRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"prcCatgCd_A1", "prcCatgCd_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_A1", "prcCatgNm_A1", "A1", null, TYPE_HANKAKUEISU, "75", null},
	{"prcContrNum_A1", "prcContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"origCoaBrCd_A1", "origCoaBrCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlDlrCd_A1", "rtlDlrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"cusaRejDt_A1", "cusaRejDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"erlTrmnDt_A1", "erlTrmnDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"rnwCsmpNum_A1", "rnwCsmpNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"uplftEquipRatio_A1", "uplftEquipRatio_A1", "A1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"uplftAsryRatio_A1", "uplftAsryRatio_A1", "A1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"csmpNumCmntTxt_A1", "csmpNumCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "90", null},
	{"csmpContrActvFlg_A1", "csmpContrActvFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxModeNm13Txt_A1", "xxModeNm13Txt_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"custMbrId_A1", "custMbrId_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"CSMP_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrPk_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum_A1
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_A1
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_A1
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_A1
        {"PRC_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum_A1
        {"ORIG_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origCoaBrCd_A1
        {"RTL_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlDlrCd_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
        {"CUSA_REJ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cusaRejDt_A1
        {"ERL_TRMN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//erlTrmnDt_A1
        {"RNW_CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwCsmpNum_A1
        {"UPLFT_EQUIP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftEquipRatio_A1
        {"UPLFT_ASRY_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftAsryRatio_A1
        {"CSMP_NUM_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNumCmntTxt_A1
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg_A1
        {"XX_MODE_NM_13_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeNm13Txt_A1
        {"CUST_MBR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMbrId_A1
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

