//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170913135645000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7120_BSMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL7120 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7120_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** CSMP_CONTR_PK_B1*/
	public final EZDSBigDecimalItem              csmpContrPk_B1;

    /** DS_ACCT_NUM_B1*/
	public final EZDSStringItem              dsAcctNum_B1;

    /** DS_ACCT_NM_B1*/
	public final EZDSStringItem              dsAcctNm_B1;

    /** CSMP_NUM_B1*/
	public final EZDSStringItem              csmpNum_B1;

    /** DLR_REF_NUM_B1*/
	public final EZDSStringItem              dlrRefNum_B1;

    /** PRC_CATG_CD_B1*/
	public final EZDSStringItem              prcCatgCd_B1;

    /** PRC_CATG_NM_B1*/
	public final EZDSStringItem              prcCatgNm_B1;

    /** PRC_CONTR_NUM_B1*/
	public final EZDSStringItem              prcContrNum_B1;

    /** ORIG_COA_BR_CD_B1*/
	public final EZDSStringItem              origCoaBrCd_B1;

    /** RTL_DLR_CD_B1*/
	public final EZDSStringItem              rtlDlrCd_B1;

    /** EFF_FROM_DT_B1*/
	public final EZDSDateItem              effFromDt_B1;

    /** EFF_THRU_DT_B1*/
	public final EZDSDateItem              effThruDt_B1;

    /** CUSA_REJ_DT_B1*/
	public final EZDSDateItem              cusaRejDt_B1;

    /** ERL_TRMN_DT_B1*/
	public final EZDSDateItem              erlTrmnDt_B1;

    /** RNW_CSMP_NUM_B1*/
	public final EZDSStringItem              rnwCsmpNum_B1;

    /** UPLFT_EQUIP_RATIO_B1*/
	public final EZDSBigDecimalItem              uplftEquipRatio_B1;

    /** UPLFT_ASRY_RATIO_B1*/
	public final EZDSBigDecimalItem              uplftAsryRatio_B1;

    /** CSMP_NUM_CMNT_TXT_B1*/
	public final EZDSStringItem              csmpNumCmntTxt_B1;

    /** CSMP_CONTR_ACTV_FLG_B1*/
	public final EZDSStringItem              csmpContrActvFlg_B1;

    /** XX_MODE_NM_13_TXT_B1*/
	public final EZDSStringItem              xxModeNm13Txt_B1;

    /** CUST_MBR_ID_B1*/
	public final EZDSStringItem              custMbrId_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;


	/**
	 * NMAL7120_BSMsg is constructor.
	 * The initialization when the instance of NMAL7120_BSMsg is generated.
	 */
	public NMAL7120_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7120_BSMsg is constructor.
	 * The initialization when the instance of NMAL7120_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7120_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		csmpContrPk_B1 = (EZDSBigDecimalItem)newItem("csmpContrPk_B1");
		dsAcctNum_B1 = (EZDSStringItem)newItem("dsAcctNum_B1");
		dsAcctNm_B1 = (EZDSStringItem)newItem("dsAcctNm_B1");
		csmpNum_B1 = (EZDSStringItem)newItem("csmpNum_B1");
		dlrRefNum_B1 = (EZDSStringItem)newItem("dlrRefNum_B1");
		prcCatgCd_B1 = (EZDSStringItem)newItem("prcCatgCd_B1");
		prcCatgNm_B1 = (EZDSStringItem)newItem("prcCatgNm_B1");
		prcContrNum_B1 = (EZDSStringItem)newItem("prcContrNum_B1");
		origCoaBrCd_B1 = (EZDSStringItem)newItem("origCoaBrCd_B1");
		rtlDlrCd_B1 = (EZDSStringItem)newItem("rtlDlrCd_B1");
		effFromDt_B1 = (EZDSDateItem)newItem("effFromDt_B1");
		effThruDt_B1 = (EZDSDateItem)newItem("effThruDt_B1");
		cusaRejDt_B1 = (EZDSDateItem)newItem("cusaRejDt_B1");
		erlTrmnDt_B1 = (EZDSDateItem)newItem("erlTrmnDt_B1");
		rnwCsmpNum_B1 = (EZDSStringItem)newItem("rnwCsmpNum_B1");
		uplftEquipRatio_B1 = (EZDSBigDecimalItem)newItem("uplftEquipRatio_B1");
		uplftAsryRatio_B1 = (EZDSBigDecimalItem)newItem("uplftAsryRatio_B1");
		csmpNumCmntTxt_B1 = (EZDSStringItem)newItem("csmpNumCmntTxt_B1");
		csmpContrActvFlg_B1 = (EZDSStringItem)newItem("csmpContrActvFlg_B1");
		xxModeNm13Txt_B1 = (EZDSStringItem)newItem("xxModeNm13Txt_B1");
		custMbrId_B1 = (EZDSStringItem)newItem("custMbrId_B1");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7120_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7120_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"csmpContrPk_B1", "csmpContrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctNum_B1", "dsAcctNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_B1", "dsAcctNm_B1", "B1", null, TYPE_HANKAKUEISU, "360", null},
	{"csmpNum_B1", "csmpNum_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_B1", "dlrRefNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"prcCatgCd_B1", "prcCatgCd_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_B1", "prcCatgNm_B1", "B1", null, TYPE_HANKAKUEISU, "75", null},
	{"prcContrNum_B1", "prcContrNum_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"origCoaBrCd_B1", "origCoaBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"rtlDlrCd_B1", "rtlDlrCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_B1", "effFromDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_B1", "effThruDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"cusaRejDt_B1", "cusaRejDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"erlTrmnDt_B1", "erlTrmnDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"rnwCsmpNum_B1", "rnwCsmpNum_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"uplftEquipRatio_B1", "uplftEquipRatio_B1", "B1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"uplftAsryRatio_B1", "uplftAsryRatio_B1", "B1", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"csmpNumCmntTxt_B1", "csmpNumCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "90", null},
	{"csmpContrActvFlg_B1", "csmpContrActvFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxModeNm13Txt_B1", "xxModeNm13Txt_B1", "B1", null, TYPE_HANKAKUEISU, "13", null},
	{"custMbrId_B1", "custMbrId_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"CSMP_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrPk_B1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_B1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B1
        {"CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum_B1
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_B1
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_B1
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_B1
        {"PRC_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum_B1
        {"ORIG_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origCoaBrCd_B1
        {"RTL_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlDlrCd_B1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_B1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_B1
        {"CUSA_REJ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cusaRejDt_B1
        {"ERL_TRMN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//erlTrmnDt_B1
        {"RNW_CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwCsmpNum_B1
        {"UPLFT_EQUIP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftEquipRatio_B1
        {"UPLFT_ASRY_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftAsryRatio_B1
        {"CSMP_NUM_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNumCmntTxt_B1
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg_B1
        {"XX_MODE_NM_13_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeNm13Txt_B1
        {"CUST_MBR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custMbrId_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
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

