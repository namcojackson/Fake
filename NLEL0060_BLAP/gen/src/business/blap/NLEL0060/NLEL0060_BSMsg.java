//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180828143954000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLEL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0060_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** PRNT_DS_ASSET_MSTR_PK_B1*/
	public final EZDSBigDecimalItem              prntDsAssetMstrPk_B1;

    /** ASSET_TP_DESC_TXT_B1*/
	public final EZDSStringItem              assetTpDescTxt_B1;

    /** XX_SCR_ITEM_50_TXT_B2*/
	public final EZDSStringItem              xxScrItem50Txt_B2;

    /** ASSET_COA_CMPY_CD_B1*/
	public final EZDSStringItem              assetCoaCmpyCd_B1;

    /** ASSET_COA_BR_CD_B1*/
	public final EZDSStringItem              assetCoaBrCd_B1;

    /** ASSET_COA_CC_CD_B1*/
	public final EZDSStringItem              assetCoaCcCd_B1;

    /** ASSET_COA_ACCT_CD_B1*/
	public final EZDSStringItem              assetCoaAcctCd_B1;

    /** ASSET_COA_PROD_CD_B1*/
	public final EZDSStringItem              assetCoaProdCd_B1;

    /** ASSET_COA_CH_CD_B1*/
	public final EZDSStringItem              assetCoaChCd_B1;

    /** ASSET_COA_AFFL_CD_B1*/
	public final EZDSStringItem              assetCoaAfflCd_B1;

    /** ASSET_COA_PROJ_CD_B1*/
	public final EZDSStringItem              assetCoaProjCd_B1;

    /** ASSET_COA_EXTN_CD_B1*/
	public final EZDSStringItem              assetCoaExtnCd_B1;

    /** EXP_COA_CMPY_CD_B1*/
	public final EZDSStringItem              expCoaCmpyCd_B1;

    /** EXP_COA_BR_CD_B1*/
	public final EZDSStringItem              expCoaBrCd_B1;

    /** EXP_COA_CC_CD_B1*/
	public final EZDSStringItem              expCoaCcCd_B1;

    /** EXP_COA_ACCT_CD_B1*/
	public final EZDSStringItem              expCoaAcctCd_B1;

    /** EXP_COA_PROD_CD_B1*/
	public final EZDSStringItem              expCoaProdCd_B1;

    /** EXP_COA_CH_CD_B1*/
	public final EZDSStringItem              expCoaChCd_B1;

    /** EXP_COA_AFFL_CD_B1*/
	public final EZDSStringItem              expCoaAfflCd_B1;

    /** EXP_COA_PROJ_CD_B1*/
	public final EZDSStringItem              expCoaProjCd_B1;

    /** EXP_COA_EXTN_CD_B1*/
	public final EZDSStringItem              expCoaExtnCd_B1;

    /** XX_SCR_ITEM_10_TXT_B1*/
	public final EZDSStringItem              xxScrItem10Txt_B1;

    /** FIRST_LINE_ADDR_B1*/
	public final EZDSStringItem              firstLineAddr_B1;

    /** SCD_LINE_ADDR_B1*/
	public final EZDSStringItem              scdLineAddr_B1;

    /** THIRD_LINE_ADDR_B1*/
	public final EZDSStringItem              thirdLineAddr_B1;

    /** FRTH_LINE_ADDR_B1*/
	public final EZDSStringItem              frthLineAddr_B1;

    /** XX_ALL_LINE_ADDR_B1*/
	public final EZDSStringItem              xxAllLineAddr_B1;

    /** CTY_ADDR_B1*/
	public final EZDSStringItem              ctyAddr_B1;

    /** ST_CD_B1*/
	public final EZDSStringItem              stCd_B1;

    /** POST_CD_B1*/
	public final EZDSStringItem              postCd_B1;

    /** ASG_DTL_CMNT_TXT_B1*/
	public final EZDSStringItem              asgDtlCmntTxt_B1;

    /** ASSET_COA_ACCT_CD_B2*/
	public final EZDSStringItem              assetCoaAcctCd_B2;

    /** DEPC_COA_ACCT_CD_B2*/
	public final EZDSStringItem              depcCoaAcctCd_B2;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;

    /** SHIP_TO_CUST_CD_B1*/
	public final EZDSStringItem              shipToCustCd_B1;

    /** SVC_MACH_MSTR_PK_B1*/
	public final EZDSBigDecimalItem              svcMachMstrPk_B1;

    /** ASSET_STS_CD_B1*/
	public final EZDSStringItem              assetStsCd_B1;

    /** ASSET_TP_CD_B1*/
	public final EZDSStringItem              assetTpCd_B1;

    /** COA_PROJ_CD_B1*/
	public final EZDSStringItem              coaProjCd_B1;

    /** COA_PROD_CD_B1*/
	public final EZDSStringItem              coaProdCd_B1;

    /** COA_BR_CD_B1*/
	public final EZDSStringItem              coaBrCd_B1;

    /** COA_CC_CD_B1*/
	public final EZDSStringItem              coaCcCd_B1;

    /** COA_EXTN_CD_B1*/
	public final EZDSStringItem              coaExtnCd_B1;

    /** AJE_COA_BR_CD_B1*/
	public final EZDSStringItem              ajeCoaBrCd_B1;

    /** AJE_COA_CC_CD_B1*/
	public final EZDSStringItem              ajeCoaCcCd_B1;

    /** AJE_COA_BU_CD_B1*/
	public final EZDSStringItem              ajeCoaBuCd_B1;

    /** COA_EXTN_CD_B2*/
	public final EZDSStringItem              coaExtnCd_B2;

    /** COA_BR_CD_B2*/
	public final EZDSStringItem              coaBrCd_B2;

    /** COA_CC_CD_B2*/
	public final EZDSStringItem              coaCcCd_B2;

    /** COA_CH_CD_B1*/
	public final EZDSStringItem              coaChCd_B1;

    /** COA_AFFL_CD_B1*/
	public final EZDSStringItem              coaAfflCd_B1;

    /** PROC_MODE_CD_B1*/
	public final EZDSStringItem              procModeCd_B1;

    /** FIRST_LINE_ADDR_BB*/
	public final EZDSStringItem              firstLineAddr_BB;

    /** CTY_ADDR_BB*/
	public final EZDSStringItem              ctyAddr_BB;

    /** ST_CD_BB*/
	public final EZDSStringItem              stCd_BB;

    /** POST_CD_BB*/
	public final EZDSStringItem              postCd_BB;

    /** ASG_DTL_CMNT_TXT_BB*/
	public final EZDSStringItem              asgDtlCmntTxt_BB;

    /** XX_SCR_ITEM_50_TXT_BC*/
	public final EZDSStringItem              xxScrItem50Txt_BC;

    /** XX_SCR_ITEM_10_TXT_BB*/
	public final EZDSStringItem              xxScrItem10Txt_BB;

    /** COA_ACCT_CD_B*/
	public final EZDSStringItem              coaAcctCd_B;

    /** COA_BR_CD_B*/
	public final EZDSStringItem              coaBrCd_B;

    /** COA_CC_CD_B*/
	public final EZDSStringItem              coaCcCd_B;

    /** COA_EXTN_CD_B*/
	public final EZDSStringItem              coaExtnCd_B;

    /** PSN_NUM_B*/
	public final EZDSStringItem              psnNum_B;

    /** PSN_NUM_BB*/
	public final EZDSStringItem              psnNum_BB;


	/**
	 * NLEL0060_BSMsg is constructor.
	 * The initialization when the instance of NLEL0060_BSMsg is generated.
	 */
	public NLEL0060_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0060_BSMsg is constructor.
	 * The initialization when the instance of NLEL0060_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0060_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		prntDsAssetMstrPk_B1 = (EZDSBigDecimalItem)newItem("prntDsAssetMstrPk_B1");
		assetTpDescTxt_B1 = (EZDSStringItem)newItem("assetTpDescTxt_B1");
		xxScrItem50Txt_B2 = (EZDSStringItem)newItem("xxScrItem50Txt_B2");
		assetCoaCmpyCd_B1 = (EZDSStringItem)newItem("assetCoaCmpyCd_B1");
		assetCoaBrCd_B1 = (EZDSStringItem)newItem("assetCoaBrCd_B1");
		assetCoaCcCd_B1 = (EZDSStringItem)newItem("assetCoaCcCd_B1");
		assetCoaAcctCd_B1 = (EZDSStringItem)newItem("assetCoaAcctCd_B1");
		assetCoaProdCd_B1 = (EZDSStringItem)newItem("assetCoaProdCd_B1");
		assetCoaChCd_B1 = (EZDSStringItem)newItem("assetCoaChCd_B1");
		assetCoaAfflCd_B1 = (EZDSStringItem)newItem("assetCoaAfflCd_B1");
		assetCoaProjCd_B1 = (EZDSStringItem)newItem("assetCoaProjCd_B1");
		assetCoaExtnCd_B1 = (EZDSStringItem)newItem("assetCoaExtnCd_B1");
		expCoaCmpyCd_B1 = (EZDSStringItem)newItem("expCoaCmpyCd_B1");
		expCoaBrCd_B1 = (EZDSStringItem)newItem("expCoaBrCd_B1");
		expCoaCcCd_B1 = (EZDSStringItem)newItem("expCoaCcCd_B1");
		expCoaAcctCd_B1 = (EZDSStringItem)newItem("expCoaAcctCd_B1");
		expCoaProdCd_B1 = (EZDSStringItem)newItem("expCoaProdCd_B1");
		expCoaChCd_B1 = (EZDSStringItem)newItem("expCoaChCd_B1");
		expCoaAfflCd_B1 = (EZDSStringItem)newItem("expCoaAfflCd_B1");
		expCoaProjCd_B1 = (EZDSStringItem)newItem("expCoaProjCd_B1");
		expCoaExtnCd_B1 = (EZDSStringItem)newItem("expCoaExtnCd_B1");
		xxScrItem10Txt_B1 = (EZDSStringItem)newItem("xxScrItem10Txt_B1");
		firstLineAddr_B1 = (EZDSStringItem)newItem("firstLineAddr_B1");
		scdLineAddr_B1 = (EZDSStringItem)newItem("scdLineAddr_B1");
		thirdLineAddr_B1 = (EZDSStringItem)newItem("thirdLineAddr_B1");
		frthLineAddr_B1 = (EZDSStringItem)newItem("frthLineAddr_B1");
		xxAllLineAddr_B1 = (EZDSStringItem)newItem("xxAllLineAddr_B1");
		ctyAddr_B1 = (EZDSStringItem)newItem("ctyAddr_B1");
		stCd_B1 = (EZDSStringItem)newItem("stCd_B1");
		postCd_B1 = (EZDSStringItem)newItem("postCd_B1");
		asgDtlCmntTxt_B1 = (EZDSStringItem)newItem("asgDtlCmntTxt_B1");
		assetCoaAcctCd_B2 = (EZDSStringItem)newItem("assetCoaAcctCd_B2");
		depcCoaAcctCd_B2 = (EZDSStringItem)newItem("depcCoaAcctCd_B2");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
		shipToCustCd_B1 = (EZDSStringItem)newItem("shipToCustCd_B1");
		svcMachMstrPk_B1 = (EZDSBigDecimalItem)newItem("svcMachMstrPk_B1");
		assetStsCd_B1 = (EZDSStringItem)newItem("assetStsCd_B1");
		assetTpCd_B1 = (EZDSStringItem)newItem("assetTpCd_B1");
		coaProjCd_B1 = (EZDSStringItem)newItem("coaProjCd_B1");
		coaProdCd_B1 = (EZDSStringItem)newItem("coaProdCd_B1");
		coaBrCd_B1 = (EZDSStringItem)newItem("coaBrCd_B1");
		coaCcCd_B1 = (EZDSStringItem)newItem("coaCcCd_B1");
		coaExtnCd_B1 = (EZDSStringItem)newItem("coaExtnCd_B1");
		ajeCoaBrCd_B1 = (EZDSStringItem)newItem("ajeCoaBrCd_B1");
		ajeCoaCcCd_B1 = (EZDSStringItem)newItem("ajeCoaCcCd_B1");
		ajeCoaBuCd_B1 = (EZDSStringItem)newItem("ajeCoaBuCd_B1");
		coaExtnCd_B2 = (EZDSStringItem)newItem("coaExtnCd_B2");
		coaBrCd_B2 = (EZDSStringItem)newItem("coaBrCd_B2");
		coaCcCd_B2 = (EZDSStringItem)newItem("coaCcCd_B2");
		coaChCd_B1 = (EZDSStringItem)newItem("coaChCd_B1");
		coaAfflCd_B1 = (EZDSStringItem)newItem("coaAfflCd_B1");
		procModeCd_B1 = (EZDSStringItem)newItem("procModeCd_B1");
		firstLineAddr_BB = (EZDSStringItem)newItem("firstLineAddr_BB");
		ctyAddr_BB = (EZDSStringItem)newItem("ctyAddr_BB");
		stCd_BB = (EZDSStringItem)newItem("stCd_BB");
		postCd_BB = (EZDSStringItem)newItem("postCd_BB");
		asgDtlCmntTxt_BB = (EZDSStringItem)newItem("asgDtlCmntTxt_BB");
		xxScrItem50Txt_BC = (EZDSStringItem)newItem("xxScrItem50Txt_BC");
		xxScrItem10Txt_BB = (EZDSStringItem)newItem("xxScrItem10Txt_BB");
		coaAcctCd_B = (EZDSStringItem)newItem("coaAcctCd_B");
		coaBrCd_B = (EZDSStringItem)newItem("coaBrCd_B");
		coaCcCd_B = (EZDSStringItem)newItem("coaCcCd_B");
		coaExtnCd_B = (EZDSStringItem)newItem("coaExtnCd_B");
		psnNum_B = (EZDSStringItem)newItem("psnNum_B");
		psnNum_BB = (EZDSStringItem)newItem("psnNum_BB");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0060_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0060_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"prntDsAssetMstrPk_B1", "prntDsAssetMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"assetTpDescTxt_B1", "assetTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem50Txt_B2", "xxScrItem50Txt_B2", "B2", null, TYPE_HANKAKUEISU, "50", null},
	{"assetCoaCmpyCd_B1", "assetCoaCmpyCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"assetCoaBrCd_B1", "assetCoaBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"assetCoaCcCd_B1", "assetCoaCcCd_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"assetCoaAcctCd_B1", "assetCoaAcctCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"assetCoaProdCd_B1", "assetCoaProdCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"assetCoaChCd_B1", "assetCoaChCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"assetCoaAfflCd_B1", "assetCoaAfflCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"assetCoaProjCd_B1", "assetCoaProjCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"assetCoaExtnCd_B1", "assetCoaExtnCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"expCoaCmpyCd_B1", "expCoaCmpyCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"expCoaBrCd_B1", "expCoaBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"expCoaCcCd_B1", "expCoaCcCd_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"expCoaAcctCd_B1", "expCoaAcctCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"expCoaProdCd_B1", "expCoaProdCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"expCoaChCd_B1", "expCoaChCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"expCoaAfflCd_B1", "expCoaAfflCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"expCoaProjCd_B1", "expCoaProjCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"expCoaExtnCd_B1", "expCoaExtnCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxScrItem10Txt_B1", "xxScrItem10Txt_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	{"firstLineAddr_B1", "firstLineAddr_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr_B1", "scdLineAddr_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr_B1", "thirdLineAddr_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr_B1", "frthLineAddr_B1", "B1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxAllLineAddr_B1", "xxAllLineAddr_B1", "B1", null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr_B1", "ctyAddr_B1", "B1", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_B1", "stCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_B1", "postCd_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"asgDtlCmntTxt_B1", "asgDtlCmntTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"assetCoaAcctCd_B2", "assetCoaAcctCd_B2", "B2", null, TYPE_HANKAKUEISU, "8", null},
	{"depcCoaAcctCd_B2", "depcCoaAcctCd_B2", "B2", null, TYPE_HANKAKUEISU, "8", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"shipToCustCd_B1", "shipToCustCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"svcMachMstrPk_B1", "svcMachMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"assetStsCd_B1", "assetStsCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"assetTpCd_B1", "assetTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProjCd_B1", "coaProjCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"coaProdCd_B1", "coaProdCd_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaBrCd_B1", "coaBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_B1", "coaCcCd_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"coaExtnCd_B1", "coaExtnCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"ajeCoaBrCd_B1", "ajeCoaBrCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"ajeCoaCcCd_B1", "ajeCoaCcCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"ajeCoaBuCd_B1", "ajeCoaBuCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"coaExtnCd_B2", "coaExtnCd_B2", "B2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_B2", "coaBrCd_B2", "B2", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_B2", "coaCcCd_B2", "B2", null, TYPE_HANKAKUEISU, "6", null},
	{"coaChCd_B1", "coaChCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"coaAfflCd_B1", "coaAfflCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"procModeCd_B1", "procModeCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"firstLineAddr_BB", "firstLineAddr_BB", "BB", null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr_BB", "ctyAddr_BB", "BB", null, TYPE_HANKAKUEISU, "25", null},
	{"stCd_BB", "stCd_BB", "BB", null, TYPE_HANKAKUEISU, "2", null},
	{"postCd_BB", "postCd_BB", "BB", null, TYPE_HANKAKUEISU, "15", null},
	{"asgDtlCmntTxt_BB", "asgDtlCmntTxt_BB", "BB", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxScrItem50Txt_BC", "xxScrItem50Txt_BC", "BC", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem10Txt_BB", "xxScrItem10Txt_BB", "BB", null, TYPE_HANKAKUEISU, "10", null},
	{"coaAcctCd_B", "coaAcctCd_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"coaBrCd_B", "coaBrCd_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_B", "coaCcCd_B", "B", null, TYPE_HANKAKUEISU, "6", null},
	{"coaExtnCd_B", "coaExtnCd_B", "B", null, TYPE_HANKAKUEISU, "3", null},
	{"psnNum_B", "psnNum_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"psnNum_BB", "psnNum_BB", "BB", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk_B1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_B1
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_B2
        {"ASSET_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaCmpyCd_B1
        {"ASSET_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaBrCd_B1
        {"ASSET_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaCcCd_B1
        {"ASSET_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAcctCd_B1
        {"ASSET_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaProdCd_B1
        {"ASSET_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaChCd_B1
        {"ASSET_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAfflCd_B1
        {"ASSET_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaProjCd_B1
        {"ASSET_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaExtnCd_B1
        {"EXP_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaCmpyCd_B1
        {"EXP_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaBrCd_B1
        {"EXP_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaCcCd_B1
        {"EXP_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaAcctCd_B1
        {"EXP_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaProdCd_B1
        {"EXP_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaChCd_B1
        {"EXP_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaAfflCd_B1
        {"EXP_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaProjCd_B1
        {"EXP_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expCoaExtnCd_B1
        {"XX_SCR_ITEM_10_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem10Txt_B1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_B1
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr_B1
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr_B1
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr_B1
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_B1
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_B1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B1
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_B1
        {"ASG_DTL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgDtlCmntTxt_B1
        {"ASSET_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAcctCd_B2
        {"DEPC_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcCoaAcctCd_B2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_B1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_B1
        {"ASSET_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetStsCd_B1
        {"ASSET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_B1
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_B1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_B1
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_B1
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_B1
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_B1
        {"AJE_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaBrCd_B1
        {"AJE_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaCcCd_B1
        {"AJE_COA_BU_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCoaBuCd_B1
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_B2
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_B2
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_B2
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_B1
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_B1
        {"PROC_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procModeCd_B1
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr_BB
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_BB
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_BB
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_BB
        {"ASG_DTL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asgDtlCmntTxt_BB
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_BC
        {"XX_SCR_ITEM_10_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem10Txt_BB
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_B
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_B
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_B
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_B
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_B
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_BB
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

