//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180328155319000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2800_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2800 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2800_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PROS_RVW_STS_CD_A1*/
	public final EZDSStringItem              prosRvwStsCd_A1;

    /** RVW_PROS_NUM_A1*/
	public final EZDSStringItem              rvwProsNum_A1;

    /** BEF_DS_ACCT_NM_A1*/
	public final EZDSStringItem              befDsAcctNm_A1;

    /** BEF_LOC_FIRST_LINE_ADDR_A1*/
	public final EZDSStringItem              befLocFirstLineAddr_A1;

    /** BEF_LOC_SCD_LINE_ADDR_A1*/
	public final EZDSStringItem              befLocScdLineAddr_A1;

    /** BEF_LOC_THIRD_LINE_ADDR_A1*/
	public final EZDSStringItem              befLocThirdLineAddr_A1;

    /** BEF_LOC_FRTH_LINE_ADDR_A1*/
	public final EZDSStringItem              befLocFrthLineAddr_A1;

    /** BEF_SHIP_TO_CTY_ADDR_A1*/
	public final EZDSStringItem              befShipToCtyAddr_A1;

    /** BEF_SHIP_TO_ST_NM_A1*/
	public final EZDSStringItem              befShipToStNm_A1;

    /** BEF_SHIP_TO_POST_CD_A1*/
	public final EZDSStringItem              befShipToPostCd_A1;

    /** BEF_SHIP_TO_CNTY_NM_A1*/
	public final EZDSStringItem              befShipToCntyNm_A1;

    /** BEF_CTRY_NM_A1*/
	public final EZDSStringItem              befCtryNm_A1;

    /** XX_SCR_ITEM_61_TXT_A1*/
	public final EZDSStringItem              xxScrItem61Txt_A1;

    /** LINE_BIZ_TP_DESC_TXT_A1*/
	public final EZDSStringItem              lineBizTpDescTxt_A1;

    /** RESRC_TRTY_ORG_NM_A1*/
	public final EZDSStringItem              resrcTrtyOrgNm_A1;

    /** CANDI_TRTY_NM_A1*/
	public final EZDSStringItem              candiTrtyNm_A1;

    /** XTRNL_CRAT_DT_TM_TS_A1*/
	public final EZDSStringItem              xtrnlCratDtTmTs_A1;

    /** XX_DT_TXT_A1*/
	public final EZDSStringItem              xxDtTxt_A1;

    /** MATCH_ACCT_LOC_NUM_A1*/
	public final EZDSStringItem              matchAcctLocNum_A1;

    /** AFT_TRTY_ORG_CD_A1*/
	public final EZDSStringItem              aftTrtyOrgCd_A1;

    /** TRTY_ORG_NM_A1*/
	public final EZDSStringItem              trtyOrgNm_A1;

    /** XX_SCR_ITEM_61_TXT_A2*/
	public final EZDSStringItem              xxScrItem61Txt_A2;

    /** AFT_LOC_NUM_A1*/
	public final EZDSStringItem              aftLocNum_A1;

    /** AFT_LOC_FIRST_LINE_ADDR_A1*/
	public final EZDSStringItem              aftLocFirstLineAddr_A1;

    /** AFT_LOC_SCD_LINE_ADDR_A1*/
	public final EZDSStringItem              aftLocScdLineAddr_A1;

    /** AFT_LOC_THIRD_LINE_ADDR_A1*/
	public final EZDSStringItem              aftLocThirdLineAddr_A1;

    /** AFT_LOC_FRTH_LINE_ADDR_A1*/
	public final EZDSStringItem              aftLocFrthLineAddr_A1;

    /** AFT_LOC_CTY_ADDR_A1*/
	public final EZDSStringItem              aftLocCtyAddr_A1;

    /** AFT_LOC_ST_CD_A1*/
	public final EZDSStringItem              aftLocStCd_A1;

    /** AFT_LOC_POST_CD_A1*/
	public final EZDSStringItem              aftLocPostCd_A1;

    /** CNTY_NM_A1*/
	public final EZDSStringItem              cntyNm_A1;

    /** AFT_CTRY_CD_A1*/
	public final EZDSStringItem              aftCtryCd_A1;

    /** BEF_TEL_NUM_A1*/
	public final EZDSStringItem              befTelNum_A1;

    /** AFT_TEL_NUM_A1*/
	public final EZDSStringItem              aftTelNum_A1;

    /** BEF_FAX_NUM_A1*/
	public final EZDSStringItem              befFaxNum_A1;

    /** AFT_FAX_NUM_A1*/
	public final EZDSStringItem              aftFaxNum_A1;

    /** DS_ACCT_URL_A1*/
	public final EZDSStringItem              dsAcctUrl_A1;

    /** XTRNL_LAST_CRAT_DT_TM_TS_A1*/
	public final EZDSStringItem              xtrnlLastCratDtTmTs_A1;

    /** XX_DT_TXT_A2*/
	public final EZDSStringItem              xxDtTxt_A2;

    /** BEF_DUNS_NUM_A1*/
	public final EZDSStringItem              befDunsNum_A1;

    /** BEF_DS_CUST_SIC_CD_A1*/
	public final EZDSStringItem              befDsCustSicCd_A1;

    /** BEF_DS_ULT_DUNS_NUM_A1*/
	public final EZDSStringItem              befDsUltDunsNum_A1;

    /** AFT_DS_ACCT_DUNS_NM_A1*/
	public final EZDSStringItem              aftDsAcctDunsNm_A1;

    /** AFT_DS_LOC_REV_AMT_A1*/
	public final EZDSBigDecimalItem              aftDsLocRevAmt_A1;

    /** AFT_DUNS_NUM_A1*/
	public final EZDSStringItem              aftDunsNum_A1;

    /** AFT_DS_CUST_SIC_DESC_TXT_A1*/
	public final EZDSStringItem              aftDsCustSicDescTxt_A1;

    /** AFT_DS_LOC_EMP_NUM_A1*/
	public final EZDSBigDecimalItem              aftDsLocEmpNum_A1;

    /** AFT_DS_CUST_SIC_CD_A1*/
	public final EZDSStringItem              aftDsCustSicCd_A1;

    /** AFT_DS_ULT_DUNS_NUM_A1*/
	public final EZDSStringItem              aftDsUltDunsNum_A1;

    /** AFT_DS_PRNT_DUNS_NUM_A1*/
	public final EZDSStringItem              aftDsPrntDunsNum_A1;

    /** AFT_HQ_DUNS_NUM_A1*/
	public final EZDSStringItem              aftHqDunsNum_A1;

    /** AFT_DS_CMPY_SIC_CD_A1*/
	public final EZDSStringItem              aftDsCmpySicCd_A1;

    /** AFT_DS_CMPY_SIC_DESC_TXT_A1*/
	public final EZDSStringItem              aftDsCmpySicDescTxt_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDSStringItem              dsAcctNum_A1;

    /** LOC_NUM_A1*/
	public final EZDSStringItem              locNum_A1;

    /** DS_ACCT_RVW_PROS_PK_A1*/
	public final EZDSBigDecimalItem              dsAcctRvwProsPk_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;

    /** XX_PG_FLG_A1*/
	public final EZDSStringItem              xxPgFlg_A1;

    /** DS_XREF_ACCT_TP_CD_A1*/
	public final EZDSStringItem              dsXrefAcctTpCd_A1;

    /** ACCT_SRC_TXT_A1*/
	public final EZDSStringItem              acctSrcTxt_A1;

    /** PROS_RVW_STS_CD_AB*/
	public final EZDSStringItem              prosRvwStsCd_AB;

    /** BEF_PSN_NUM_A1*/
	public final EZDSStringItem              befPsnNum_A1;

    /** BEF_DS_LOC_EMP_NUM_A1*/
	public final EZDSBigDecimalItem              befDsLocEmpNum_A1;

    /** AFT_DS_ACCT_NM_A1*/
	public final EZDSStringItem              aftDsAcctNm_A1;

    /** AFT_DS_XREF_ACCT_CD_A1*/
	public final EZDSStringItem              aftDsXrefAcctCd_A1;

    /** EML_ADDR_A1*/
	public final EZDSStringItem              emlAddr_A1;

    /** AFT_LOC_FIRST_LINE_ADDR_AH*/
	public final EZDSStringItem              aftLocFirstLineAddr_AH;

    /** AFT_LOC_SCD_LINE_ADDR_AH*/
	public final EZDSStringItem              aftLocScdLineAddr_AH;

    /** AFT_LOC_THIRD_LINE_ADDR_AH*/
	public final EZDSStringItem              aftLocThirdLineAddr_AH;

    /** AFT_LOC_FRTH_LINE_ADDR_AH*/
	public final EZDSStringItem              aftLocFrthLineAddr_AH;

    /** AFT_LOC_CTY_ADDR_AH*/
	public final EZDSStringItem              aftLocCtyAddr_AH;

    /** AFT_LOC_ST_CD_AH*/
	public final EZDSStringItem              aftLocStCd_AH;

    /** AFT_LOC_POST_CD_AH*/
	public final EZDSStringItem              aftLocPostCd_AH;

    /** AFT_TEL_NUM_AH*/
	public final EZDSStringItem              aftTelNum_AH;

    /** AFT_FAX_NUM_AH*/
	public final EZDSStringItem              aftFaxNum_AH;

    /** AFT_DS_ACCT_DUNS_NM_AH*/
	public final EZDSStringItem              aftDsAcctDunsNm_AH;

    /** AFT_DS_LOC_REV_AMT_AH*/
	public final EZDSBigDecimalItem              aftDsLocRevAmt_AH;

    /** AFT_DUNS_NUM_AH*/
	public final EZDSStringItem              aftDunsNum_AH;

    /** AFT_DS_CUST_SIC_DESC_TXT_AH*/
	public final EZDSStringItem              aftDsCustSicDescTxt_AH;

    /** AFT_DS_LOC_EMP_NUM_AH*/
	public final EZDSBigDecimalItem              aftDsLocEmpNum_AH;

    /** AFT_DS_CUST_SIC_CD_AH*/
	public final EZDSStringItem              aftDsCustSicCd_AH;

    /** AFT_DS_ULT_DUNS_NUM_AH*/
	public final EZDSStringItem              aftDsUltDunsNum_AH;

    /** AFT_DS_PRNT_DUNS_NUM_AH*/
	public final EZDSStringItem              aftDsPrntDunsNum_AH;

    /** AFT_HQ_DUNS_NUM_AH*/
	public final EZDSStringItem              aftHqDunsNum_AH;

    /** AFT_DS_CMPY_SIC_CD_AH*/
	public final EZDSStringItem              aftDsCmpySicCd_AH;

    /** AFT_DS_CMPY_SIC_DESC_TXT_AH*/
	public final EZDSStringItem              aftDsCmpySicDescTxt_AH;


	/**
	 * NMAL2800_ASMsg is constructor.
	 * The initialization when the instance of NMAL2800_ASMsg is generated.
	 */
	public NMAL2800_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2800_ASMsg is constructor.
	 * The initialization when the instance of NMAL2800_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2800_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prosRvwStsCd_A1 = (EZDSStringItem)newItem("prosRvwStsCd_A1");
		rvwProsNum_A1 = (EZDSStringItem)newItem("rvwProsNum_A1");
		befDsAcctNm_A1 = (EZDSStringItem)newItem("befDsAcctNm_A1");
		befLocFirstLineAddr_A1 = (EZDSStringItem)newItem("befLocFirstLineAddr_A1");
		befLocScdLineAddr_A1 = (EZDSStringItem)newItem("befLocScdLineAddr_A1");
		befLocThirdLineAddr_A1 = (EZDSStringItem)newItem("befLocThirdLineAddr_A1");
		befLocFrthLineAddr_A1 = (EZDSStringItem)newItem("befLocFrthLineAddr_A1");
		befShipToCtyAddr_A1 = (EZDSStringItem)newItem("befShipToCtyAddr_A1");
		befShipToStNm_A1 = (EZDSStringItem)newItem("befShipToStNm_A1");
		befShipToPostCd_A1 = (EZDSStringItem)newItem("befShipToPostCd_A1");
		befShipToCntyNm_A1 = (EZDSStringItem)newItem("befShipToCntyNm_A1");
		befCtryNm_A1 = (EZDSStringItem)newItem("befCtryNm_A1");
		xxScrItem61Txt_A1 = (EZDSStringItem)newItem("xxScrItem61Txt_A1");
		lineBizTpDescTxt_A1 = (EZDSStringItem)newItem("lineBizTpDescTxt_A1");
		resrcTrtyOrgNm_A1 = (EZDSStringItem)newItem("resrcTrtyOrgNm_A1");
		candiTrtyNm_A1 = (EZDSStringItem)newItem("candiTrtyNm_A1");
		xtrnlCratDtTmTs_A1 = (EZDSStringItem)newItem("xtrnlCratDtTmTs_A1");
		xxDtTxt_A1 = (EZDSStringItem)newItem("xxDtTxt_A1");
		matchAcctLocNum_A1 = (EZDSStringItem)newItem("matchAcctLocNum_A1");
		aftTrtyOrgCd_A1 = (EZDSStringItem)newItem("aftTrtyOrgCd_A1");
		trtyOrgNm_A1 = (EZDSStringItem)newItem("trtyOrgNm_A1");
		xxScrItem61Txt_A2 = (EZDSStringItem)newItem("xxScrItem61Txt_A2");
		aftLocNum_A1 = (EZDSStringItem)newItem("aftLocNum_A1");
		aftLocFirstLineAddr_A1 = (EZDSStringItem)newItem("aftLocFirstLineAddr_A1");
		aftLocScdLineAddr_A1 = (EZDSStringItem)newItem("aftLocScdLineAddr_A1");
		aftLocThirdLineAddr_A1 = (EZDSStringItem)newItem("aftLocThirdLineAddr_A1");
		aftLocFrthLineAddr_A1 = (EZDSStringItem)newItem("aftLocFrthLineAddr_A1");
		aftLocCtyAddr_A1 = (EZDSStringItem)newItem("aftLocCtyAddr_A1");
		aftLocStCd_A1 = (EZDSStringItem)newItem("aftLocStCd_A1");
		aftLocPostCd_A1 = (EZDSStringItem)newItem("aftLocPostCd_A1");
		cntyNm_A1 = (EZDSStringItem)newItem("cntyNm_A1");
		aftCtryCd_A1 = (EZDSStringItem)newItem("aftCtryCd_A1");
		befTelNum_A1 = (EZDSStringItem)newItem("befTelNum_A1");
		aftTelNum_A1 = (EZDSStringItem)newItem("aftTelNum_A1");
		befFaxNum_A1 = (EZDSStringItem)newItem("befFaxNum_A1");
		aftFaxNum_A1 = (EZDSStringItem)newItem("aftFaxNum_A1");
		dsAcctUrl_A1 = (EZDSStringItem)newItem("dsAcctUrl_A1");
		xtrnlLastCratDtTmTs_A1 = (EZDSStringItem)newItem("xtrnlLastCratDtTmTs_A1");
		xxDtTxt_A2 = (EZDSStringItem)newItem("xxDtTxt_A2");
		befDunsNum_A1 = (EZDSStringItem)newItem("befDunsNum_A1");
		befDsCustSicCd_A1 = (EZDSStringItem)newItem("befDsCustSicCd_A1");
		befDsUltDunsNum_A1 = (EZDSStringItem)newItem("befDsUltDunsNum_A1");
		aftDsAcctDunsNm_A1 = (EZDSStringItem)newItem("aftDsAcctDunsNm_A1");
		aftDsLocRevAmt_A1 = (EZDSBigDecimalItem)newItem("aftDsLocRevAmt_A1");
		aftDunsNum_A1 = (EZDSStringItem)newItem("aftDunsNum_A1");
		aftDsCustSicDescTxt_A1 = (EZDSStringItem)newItem("aftDsCustSicDescTxt_A1");
		aftDsLocEmpNum_A1 = (EZDSBigDecimalItem)newItem("aftDsLocEmpNum_A1");
		aftDsCustSicCd_A1 = (EZDSStringItem)newItem("aftDsCustSicCd_A1");
		aftDsUltDunsNum_A1 = (EZDSStringItem)newItem("aftDsUltDunsNum_A1");
		aftDsPrntDunsNum_A1 = (EZDSStringItem)newItem("aftDsPrntDunsNum_A1");
		aftHqDunsNum_A1 = (EZDSStringItem)newItem("aftHqDunsNum_A1");
		aftDsCmpySicCd_A1 = (EZDSStringItem)newItem("aftDsCmpySicCd_A1");
		aftDsCmpySicDescTxt_A1 = (EZDSStringItem)newItem("aftDsCmpySicDescTxt_A1");
		dsAcctNum_A1 = (EZDSStringItem)newItem("dsAcctNum_A1");
		locNum_A1 = (EZDSStringItem)newItem("locNum_A1");
		dsAcctRvwProsPk_A1 = (EZDSBigDecimalItem)newItem("dsAcctRvwProsPk_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
		xxPgFlg_A1 = (EZDSStringItem)newItem("xxPgFlg_A1");
		dsXrefAcctTpCd_A1 = (EZDSStringItem)newItem("dsXrefAcctTpCd_A1");
		acctSrcTxt_A1 = (EZDSStringItem)newItem("acctSrcTxt_A1");
		prosRvwStsCd_AB = (EZDSStringItem)newItem("prosRvwStsCd_AB");
		befPsnNum_A1 = (EZDSStringItem)newItem("befPsnNum_A1");
		befDsLocEmpNum_A1 = (EZDSBigDecimalItem)newItem("befDsLocEmpNum_A1");
		aftDsAcctNm_A1 = (EZDSStringItem)newItem("aftDsAcctNm_A1");
		aftDsXrefAcctCd_A1 = (EZDSStringItem)newItem("aftDsXrefAcctCd_A1");
		emlAddr_A1 = (EZDSStringItem)newItem("emlAddr_A1");
		aftLocFirstLineAddr_AH = (EZDSStringItem)newItem("aftLocFirstLineAddr_AH");
		aftLocScdLineAddr_AH = (EZDSStringItem)newItem("aftLocScdLineAddr_AH");
		aftLocThirdLineAddr_AH = (EZDSStringItem)newItem("aftLocThirdLineAddr_AH");
		aftLocFrthLineAddr_AH = (EZDSStringItem)newItem("aftLocFrthLineAddr_AH");
		aftLocCtyAddr_AH = (EZDSStringItem)newItem("aftLocCtyAddr_AH");
		aftLocStCd_AH = (EZDSStringItem)newItem("aftLocStCd_AH");
		aftLocPostCd_AH = (EZDSStringItem)newItem("aftLocPostCd_AH");
		aftTelNum_AH = (EZDSStringItem)newItem("aftTelNum_AH");
		aftFaxNum_AH = (EZDSStringItem)newItem("aftFaxNum_AH");
		aftDsAcctDunsNm_AH = (EZDSStringItem)newItem("aftDsAcctDunsNm_AH");
		aftDsLocRevAmt_AH = (EZDSBigDecimalItem)newItem("aftDsLocRevAmt_AH");
		aftDunsNum_AH = (EZDSStringItem)newItem("aftDunsNum_AH");
		aftDsCustSicDescTxt_AH = (EZDSStringItem)newItem("aftDsCustSicDescTxt_AH");
		aftDsLocEmpNum_AH = (EZDSBigDecimalItem)newItem("aftDsLocEmpNum_AH");
		aftDsCustSicCd_AH = (EZDSStringItem)newItem("aftDsCustSicCd_AH");
		aftDsUltDunsNum_AH = (EZDSStringItem)newItem("aftDsUltDunsNum_AH");
		aftDsPrntDunsNum_AH = (EZDSStringItem)newItem("aftDsPrntDunsNum_AH");
		aftHqDunsNum_AH = (EZDSStringItem)newItem("aftHqDunsNum_AH");
		aftDsCmpySicCd_AH = (EZDSStringItem)newItem("aftDsCmpySicCd_AH");
		aftDsCmpySicDescTxt_AH = (EZDSStringItem)newItem("aftDsCmpySicDescTxt_AH");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2800_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2800_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prosRvwStsCd_A1", "prosRvwStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"rvwProsNum_A1", "rvwProsNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"befDsAcctNm_A1", "befDsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"befLocFirstLineAddr_A1", "befLocFirstLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befLocScdLineAddr_A1", "befLocScdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befLocThirdLineAddr_A1", "befLocThirdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befLocFrthLineAddr_A1", "befLocFrthLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befShipToCtyAddr_A1", "befShipToCtyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befShipToStNm_A1", "befShipToStNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befShipToPostCd_A1", "befShipToPostCd_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befShipToCntyNm_A1", "befShipToCntyNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"befCtryNm_A1", "befCtryNm_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"xxScrItem61Txt_A1", "xxScrItem61Txt_A1", "A1", null, TYPE_HANKAKUEISU, "61", null},
	{"lineBizTpDescTxt_A1", "lineBizTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"resrcTrtyOrgNm_A1", "resrcTrtyOrgNm_A1", "A1", null, TYPE_HANKAKUEISU, "2000", null},
	{"candiTrtyNm_A1", "candiTrtyNm_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xtrnlCratDtTmTs_A1", "xtrnlCratDtTmTs_A1", "A1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTxt_A1", "xxDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"matchAcctLocNum_A1", "matchAcctLocNum_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	{"aftTrtyOrgCd_A1", "aftTrtyOrgCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyOrgNm_A1", "trtyOrgNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem61Txt_A2", "xxScrItem61Txt_A2", "A2", null, TYPE_HANKAKUEISU, "61", null},
	{"aftLocNum_A1", "aftLocNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"aftLocFirstLineAddr_A1", "aftLocFirstLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocScdLineAddr_A1", "aftLocScdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocThirdLineAddr_A1", "aftLocThirdLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocFrthLineAddr_A1", "aftLocFrthLineAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocCtyAddr_A1", "aftLocCtyAddr_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocStCd_A1", "aftLocStCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"aftLocPostCd_A1", "aftLocPostCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"cntyNm_A1", "cntyNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"aftCtryCd_A1", "aftCtryCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"befTelNum_A1", "befTelNum_A1", "A1", null, TYPE_HANKAKUEISU, "51", null},
	{"aftTelNum_A1", "aftTelNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"befFaxNum_A1", "befFaxNum_A1", "A1", null, TYPE_HANKAKUEISU, "51", null},
	{"aftFaxNum_A1", "aftFaxNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctUrl_A1", "dsAcctUrl_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xtrnlLastCratDtTmTs_A1", "xtrnlLastCratDtTmTs_A1", "A1", null, TYPE_HANKAKUSUJI, "14", null},
	{"xxDtTxt_A2", "xxDtTxt_A2", "A2", null, TYPE_HANKAKUEISU, "10", null},
	{"befDunsNum_A1", "befDunsNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"befDsCustSicCd_A1", "befDsCustSicCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"befDsUltDunsNum_A1", "befDsUltDunsNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsAcctDunsNm_A1", "aftDsAcctDunsNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"aftDsLocRevAmt_A1", "aftDsLocRevAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"aftDunsNum_A1", "aftDunsNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsCustSicDescTxt_A1", "aftDsCustSicDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"aftDsLocEmpNum_A1", "aftDsLocEmpNum_A1", "A1", null, TYPE_SEISU_SYOSU, "8", "0"},
	{"aftDsCustSicCd_A1", "aftDsCustSicCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"aftDsUltDunsNum_A1", "aftDsUltDunsNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsPrntDunsNum_A1", "aftDsPrntDunsNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"aftHqDunsNum_A1", "aftHqDunsNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsCmpySicCd_A1", "aftDsCmpySicCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"aftDsCmpySicDescTxt_A1", "aftDsCmpySicDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNum_A1", "locNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctRvwProsPk_A1", "dsAcctRvwProsPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxPgFlg_A1", "xxPgFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsXrefAcctTpCd_A1", "dsXrefAcctTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"acctSrcTxt_A1", "acctSrcTxt_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"prosRvwStsCd_AB", "prosRvwStsCd_AB", "AB", null, TYPE_HANKAKUEISU, "2", null},
	{"befPsnNum_A1", "befPsnNum_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"befDsLocEmpNum_A1", "befDsLocEmpNum_A1", "A1", null, TYPE_SEISU_SYOSU, "8", "0"},
	{"aftDsAcctNm_A1", "aftDsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"aftDsXrefAcctCd_A1", "aftDsXrefAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"emlAddr_A1", "emlAddr_A1", "A1", null, TYPE_HANKAKUEISU, "320", null},
	{"aftLocFirstLineAddr_AH", "aftLocFirstLineAddr_AH", "AH", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocScdLineAddr_AH", "aftLocScdLineAddr_AH", "AH", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocThirdLineAddr_AH", "aftLocThirdLineAddr_AH", "AH", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocFrthLineAddr_AH", "aftLocFrthLineAddr_AH", "AH", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocCtyAddr_AH", "aftLocCtyAddr_AH", "AH", null, TYPE_HANKAKUEISU, "60", null},
	{"aftLocStCd_AH", "aftLocStCd_AH", "AH", null, TYPE_HANKAKUEISU, "2", null},
	{"aftLocPostCd_AH", "aftLocPostCd_AH", "AH", null, TYPE_HANKAKUEISU, "15", null},
	{"aftTelNum_AH", "aftTelNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"aftFaxNum_AH", "aftFaxNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsAcctDunsNm_AH", "aftDsAcctDunsNm_AH", "AH", null, TYPE_HANKAKUEISU, "60", null},
	{"aftDsLocRevAmt_AH", "aftDsLocRevAmt_AH", "AH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"aftDunsNum_AH", "aftDunsNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsCustSicDescTxt_AH", "aftDsCustSicDescTxt_AH", "AH", null, TYPE_HANKAKUEISU, "100", null},
	{"aftDsLocEmpNum_AH", "aftDsLocEmpNum_AH", "AH", null, TYPE_SEISU_SYOSU, "8", "0"},
	{"aftDsCustSicCd_AH", "aftDsCustSicCd_AH", "AH", null, TYPE_HANKAKUEISU, "30", null},
	{"aftDsUltDunsNum_AH", "aftDsUltDunsNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsPrntDunsNum_AH", "aftDsPrntDunsNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"aftHqDunsNum_AH", "aftHqDunsNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"aftDsCmpySicCd_AH", "aftDsCmpySicCd_AH", "AH", null, TYPE_HANKAKUEISU, "30", null},
	{"aftDsCmpySicDescTxt_AH", "aftDsCmpySicDescTxt_AH", "AH", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PROS_RVW_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsCd_A1
        {"RVW_PROS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvwProsNum_A1
        {"BEF_DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befDsAcctNm_A1
        {"BEF_LOC_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befLocFirstLineAddr_A1
        {"BEF_LOC_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befLocScdLineAddr_A1
        {"BEF_LOC_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befLocThirdLineAddr_A1
        {"BEF_LOC_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befLocFrthLineAddr_A1
        {"BEF_SHIP_TO_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToCtyAddr_A1
        {"BEF_SHIP_TO_ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToStNm_A1
        {"BEF_SHIP_TO_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToPostCd_A1
        {"BEF_SHIP_TO_CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befShipToCntyNm_A1
        {"BEF_CTRY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befCtryNm_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A1
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_A1
        {"RESRC_TRTY_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//resrcTrtyOrgNm_A1
        {"CANDI_TRTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//candiTrtyNm_A1
        {"XTRNL_CRAT_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlCratDtTmTs_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A1
        {"MATCH_ACCT_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//matchAcctLocNum_A1
        {"AFT_TRTY_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftTrtyOrgCd_A1
        {"TRTY_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyOrgNm_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A2
        {"AFT_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocNum_A1
        {"AFT_LOC_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocFirstLineAddr_A1
        {"AFT_LOC_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocScdLineAddr_A1
        {"AFT_LOC_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocThirdLineAddr_A1
        {"AFT_LOC_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocFrthLineAddr_A1
        {"AFT_LOC_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocCtyAddr_A1
        {"AFT_LOC_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocStCd_A1
        {"AFT_LOC_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocPostCd_A1
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_A1
        {"AFT_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftCtryCd_A1
        {"BEF_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befTelNum_A1
        {"AFT_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftTelNum_A1
        {"BEF_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befFaxNum_A1
        {"AFT_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftFaxNum_A1
        {"DS_ACCT_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctUrl_A1
        {"XTRNL_LAST_CRAT_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlLastCratDtTmTs_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A2
        {"BEF_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befDunsNum_A1
        {"BEF_DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befDsCustSicCd_A1
        {"BEF_DS_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befDsUltDunsNum_A1
        {"AFT_DS_ACCT_DUNS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsAcctDunsNm_A1
        {"AFT_DS_LOC_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsLocRevAmt_A1
        {"AFT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDunsNum_A1
        {"AFT_DS_CUST_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCustSicDescTxt_A1
        {"AFT_DS_LOC_EMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsLocEmpNum_A1
        {"AFT_DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCustSicCd_A1
        {"AFT_DS_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsUltDunsNum_A1
        {"AFT_DS_PRNT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsPrntDunsNum_A1
        {"AFT_HQ_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftHqDunsNum_A1
        {"AFT_DS_CMPY_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCmpySicCd_A1
        {"AFT_DS_CMPY_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCmpySicDescTxt_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A1
        {"DS_ACCT_RVW_PROS_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRvwProsPk_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_PG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_A1
        {"DS_XREF_ACCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsXrefAcctTpCd_A1
        {"ACCT_SRC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctSrcTxt_A1
        {"PROS_RVW_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prosRvwStsCd_AB
        {"BEF_PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befPsnNum_A1
        {"BEF_DS_LOC_EMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befDsLocEmpNum_A1
        {"AFT_DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsAcctNm_A1
        {"AFT_DS_XREF_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsXrefAcctCd_A1
        {"EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//emlAddr_A1
        {"AFT_LOC_FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocFirstLineAddr_AH
        {"AFT_LOC_SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocScdLineAddr_AH
        {"AFT_LOC_THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocThirdLineAddr_AH
        {"AFT_LOC_FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocFrthLineAddr_AH
        {"AFT_LOC_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocCtyAddr_AH
        {"AFT_LOC_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocStCd_AH
        {"AFT_LOC_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftLocPostCd_AH
        {"AFT_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftTelNum_AH
        {"AFT_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftFaxNum_AH
        {"AFT_DS_ACCT_DUNS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsAcctDunsNm_AH
        {"AFT_DS_LOC_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsLocRevAmt_AH
        {"AFT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDunsNum_AH
        {"AFT_DS_CUST_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCustSicDescTxt_AH
        {"AFT_DS_LOC_EMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsLocEmpNum_AH
        {"AFT_DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCustSicCd_AH
        {"AFT_DS_ULT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsUltDunsNum_AH
        {"AFT_DS_PRNT_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsPrntDunsNum_AH
        {"AFT_HQ_DUNS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftHqDunsNum_AH
        {"AFT_DS_CMPY_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCmpySicCd_AH
        {"AFT_DS_CMPY_SIC_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftDsCmpySicDescTxt_AH
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

