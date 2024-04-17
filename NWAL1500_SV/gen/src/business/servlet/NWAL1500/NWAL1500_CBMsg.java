//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231110101027000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1500 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1500_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG_R*/
	public final EZDBStringItem              xxSmryLineFlg_R;

    /** XX_CHK_BOX_RC*/
	public final EZDBStringItem              xxChkBox_RC;

    /** DS_ORD_POSN_NUM_RC*/
	public final EZDBStringItem              dsOrdPosnNum_RC;

    /** CONFIG_TP_CD_RC*/
	public final EZDBStringItem              configTpCd_RC;

    /** SVC_CONFIG_MSTR_PK_RC*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_RC;

    /** MDL_NM_RC*/
	public final EZDBStringItem              mdlNm_RC;

    /** SHIP_TO_CUST_LOC_ADDR_RC*/
	public final EZDBStringItem              shipToCustLocAddr_RC;

    /** SHIP_TO_CUST_CD_RC*/
	public final EZDBStringItem              shipToCustCd_RC;

    /** SHIP_TO_CUST_ACCT_CD_RC*/
	public final EZDBStringItem              shipToCustAcctCd_RC;

    /** SHIP_TO_CUST_ACCT_NM_RC*/
	public final EZDBStringItem              shipToCustAcctNm_RC;

    /** ADD_SHIP_TO_LOC_NM_RC*/
	public final EZDBStringItem              addShipToLocNm_RC;

    /** BILL_TO_CUST_LOC_ADDR_RC*/
	public final EZDBStringItem              billToCustLocAddr_RC;

    /** BILL_TO_CUST_CD_RC*/
	public final EZDBStringItem              billToCustCd_RC;

    /** BILL_TO_CUST_ACCT_CD_RC*/
	public final EZDBStringItem              billToCustAcctCd_RC;

    /** BILL_TO_CUST_ACCT_NM_RC*/
	public final EZDBStringItem              billToCustAcctNm_RC;

    /** SOLD_TO_CUST_LOC_ADDR_RC*/
	public final EZDBStringItem              soldToCustLocAddr_RC;

    /** SELL_TO_CUST_CD_RC*/
	public final EZDBStringItem              sellToCustCd_RC;

    /** SOLD_TO_CUST_LOC_CD_RC*/
	public final EZDBStringItem              soldToCustLocCd_RC;

    /** SOLD_TO_CUST_ACCT_NM_RC*/
	public final EZDBStringItem              soldToCustAcctNm_RC;

    /** MDL_GRP_DESC_TXT_RC*/
	public final EZDBStringItem              mdlGrpDescTxt_RC;

    /** MDL_DESC_TXT_RC*/
	public final EZDBStringItem              mdlDescTxt_RC;

    /** SVC_SEG_DESC_TXT_RC*/
	public final EZDBStringItem              svcSegDescTxt_RC;

    /** SVC_ISTL_REQ_FLG_RC*/
	public final EZDBStringItem              svcIstlReqFlg_RC;

    /** SITE_SRVY_REQ_FLG_RC*/
	public final EZDBStringItem              siteSrvyReqFlg_RC;

    /** SLS_CR_SPL_FLG_RC*/
	public final EZDBStringItem              slsCrSplFlg_RC;

    /** DS_CPO_CONFIG_HLD_FLG_RC*/
	public final EZDBStringItem              dsCpoConfigHldFlg_RC;

    /** CRAT_USR_NM_RC*/
	public final EZDBStringItem              cratUsrNm_RC;

    /** CRAT_TS_DPLY_TXT_RC*/
	public final EZDBStringItem              cratTsDplyTxt_RC;

    /** UPD_USR_NM_RC*/
	public final EZDBStringItem              updUsrNm_RC;

    /** UPD_TS_DPLY_TXT_RC*/
	public final EZDBStringItem              updTsDplyTxt_RC;

    /** CONFIG_TOT_DEAL_NET_AMT_RC*/
	public final EZDBBigDecimalItem              configTotDealNetAmt_RC;

    /** CONFIG_TOT_DEAL_CHRG_AMT_RC*/
	public final EZDBBigDecimalItem              configTotDealChrgAmt_RC;

    /** CONFIG_TOT_DEAL_TAX_AMT_RC*/
	public final EZDBBigDecimalItem              configTotDealTaxAmt_RC;

    /** CONFIG_SUB_TOT_DEAL_AMT_RC*/
	public final EZDBBigDecimalItem              configSubTotDealAmt_RC;

    /** DROP_SHIP_FLG_RC*/
	public final EZDBStringItem              dropShipFlg_RC;

    /** DS_CPO_CONFIG_PK_RC*/
	public final EZDBBigDecimalItem              dsCpoConfigPk_RC;

    /** MDL_ID_RC*/
	public final EZDBBigDecimalItem              mdlId_RC;

    /** SHIP_TO_LOC_NM_RC*/
	public final EZDBStringItem              shipToLocNm_RC;

    /** SHIP_TO_ADDL_LOC_NM_RC*/
	public final EZDBStringItem              shipToAddlLocNm_RC;

    /** SHIP_TO_FIRST_LINE_ADDR_RC*/
	public final EZDBStringItem              shipToFirstLineAddr_RC;

    /** SHIP_TO_SCD_LINE_ADDR_RC*/
	public final EZDBStringItem              shipToScdLineAddr_RC;

    /** SHIP_TO_THIRD_LINE_ADDR_RC*/
	public final EZDBStringItem              shipToThirdLineAddr_RC;

    /** SHIP_TO_FRTH_LINE_ADDR_RC*/
	public final EZDBStringItem              shipToFrthLineAddr_RC;

    /** SHIP_TO_FIRST_REF_CMNT_TXT_RC*/
	public final EZDBStringItem              shipToFirstRefCmntTxt_RC;

    /** SHIP_TO_SCD_REF_CMNT_TXT_RC*/
	public final EZDBStringItem              shipToScdRefCmntTxt_RC;

    /** SHIP_TO_POST_CD_RC*/
	public final EZDBStringItem              shipToPostCd_RC;

    /** SHIP_TO_CTY_ADDR_RC*/
	public final EZDBStringItem              shipToCtyAddr_RC;

    /** SHIP_TO_ST_CD_RC*/
	public final EZDBStringItem              shipToStCd_RC;

    /** SHIP_TO_PROV_NM_RC*/
	public final EZDBStringItem              shipToProvNm_RC;

    /** SHIP_TO_CTRY_CD_RC*/
	public final EZDBStringItem              shipToCtryCd_RC;

    /** SHIP_TO_CNTY_NM_RC*/
	public final EZDBStringItem              shipToCntyNm_RC;

    /** PICK_SVC_CONFIG_MSTR_PK_RC*/
	public final EZDBBigDecimalItem              pickSvcConfigMstrPk_RC;

    /** CONFIG_NEW_FLG_RC*/
	public final EZDBStringItem              configNewFlg_RC;

    /** CSMP_CONTR_NUM_RC*/
	public final EZDBStringItem              csmpContrNum_RC;

    /** XX_IMAGE_TXT_RN*/
	public final EZDBStringItem              xxImageTxt_RN;

    /** DLR_REF_NUM_RC*/
	public final EZDBStringItem              dlrRefNum_RC;

    /** XX_IMAGE_TXT_RD*/
	public final EZDBStringItem              xxImageTxt_RD;

    /** CSMP_PRC_LIST_CD_RC*/
	public final EZDBStringItem              csmpPrcListCd_RC;

    /** PRC_CATG_NM_RC*/
	public final EZDBStringItem              prcCatgNm_RC;

    /** SVC_MDL_TP_CD_RC*/
	public final EZDBStringItem              svcMdlTpCd_RC;

    /** PRNT_MDSE_CD_RC*/
	public final EZDBStringItem              prntMdseCd_RC;

    /** ORIG_SHIP_TO_CUST_CD_RC*/
	public final EZDBStringItem              origShipToCustCd_RC;

    /** ORIG_SHIP_TO_LOC_NM_RC*/
	public final EZDBStringItem              origShipToLocNm_RC;

    /** ORIG_SHIP_TO_ADDL_LOC_NM_RC*/
	public final EZDBStringItem              origShipToAddlLocNm_RC;

    /** ORIG_SHIP_FIRST_LINE_ADDR_RC*/
	public final EZDBStringItem              origShipFirstLineAddr_RC;

    /** ORIG_SHIP_SCD_LINE_ADDR_RC*/
	public final EZDBStringItem              origShipScdLineAddr_RC;

    /** ORIG_SHIP_THIRD_LINE_ADDR_RC*/
	public final EZDBStringItem              origShipThirdLineAddr_RC;

    /** ORIG_SHIP_FRTH_LINE_ADDR_RC*/
	public final EZDBStringItem              origShipFrthLineAddr_RC;

    /** ORIG_SHIP_TO_POST_CD_RC*/
	public final EZDBStringItem              origShipToPostCd_RC;

    /** ORIG_SHIP_TO_CTY_ADDR_RC*/
	public final EZDBStringItem              origShipToCtyAddr_RC;

    /** ORIG_SHIP_TO_ST_CD_RC*/
	public final EZDBStringItem              origShipToStCd_RC;

    /** ORIG_SHIP_TO_CTRY_CD_RC*/
	public final EZDBStringItem              origShipToCtryCd_RC;

    /** ORIG_SHIP_TO_CNTY_NM_RC*/
	public final EZDBStringItem              origShipToCntyNm_RC;

    /** XX_EDT_MODE_FLG_RC*/
	public final EZDBStringItem              xxEdtModeFlg_RC;

    /** XX_IMAGE_TXT_CC*/
	public final EZDBStringItem              xxImageTxt_CC;

    /** XX_IMAGE_TXT_CA*/
	public final EZDBStringItem              xxImageTxt_CA;

    /** XX_IMAGE_TXT_CS*/
	public final EZDBStringItem              xxImageTxt_CS;

    /** XX_IMAGE_TXT_CN*/
	public final EZDBStringItem              xxImageTxt_CN;

    /** XX_IMAGE_TXT_CT*/
	public final EZDBStringItem              xxImageTxt_CT;

    /** XX_IMAGE_TXT_CB*/
	public final EZDBStringItem              xxImageTxt_CB;

    /** XX_IMAGE_TXT_CM*/
	public final EZDBStringItem              xxImageTxt_CM;

    /** XX_IMAGE_TXT_CD*/
	public final EZDBStringItem              xxImageTxt_CD;

    /** XX_YES_NO_CD_RC*/
	public final EZDBStringItem              xxYesNoCd_RC;

    /** XX_RES_FLTR_FLG_RC*/
	public final EZDBStringItem              xxResFltrFlg_RC;

    /** XX_PAGE_NUM_RC*/
	public final EZDBBigDecimalItem              xxPageNum_RC;


	/**
	 * NWAL1500_CBMsg is constructor.
	 * The initialization when the instance of NWAL1500_CBMsg is generated.
	 */
	public NWAL1500_CBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1500_CBMsg is constructor.
	 * The initialization when the instance of NWAL1500_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1500_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg_R = (EZDBStringItem)newItem("xxSmryLineFlg_R");
		xxChkBox_RC = (EZDBStringItem)newItem("xxChkBox_RC");
		dsOrdPosnNum_RC = (EZDBStringItem)newItem("dsOrdPosnNum_RC");
		configTpCd_RC = (EZDBStringItem)newItem("configTpCd_RC");
		svcConfigMstrPk_RC = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_RC");
		mdlNm_RC = (EZDBStringItem)newItem("mdlNm_RC");
		shipToCustLocAddr_RC = (EZDBStringItem)newItem("shipToCustLocAddr_RC");
		shipToCustCd_RC = (EZDBStringItem)newItem("shipToCustCd_RC");
		shipToCustAcctCd_RC = (EZDBStringItem)newItem("shipToCustAcctCd_RC");
		shipToCustAcctNm_RC = (EZDBStringItem)newItem("shipToCustAcctNm_RC");
		addShipToLocNm_RC = (EZDBStringItem)newItem("addShipToLocNm_RC");
		billToCustLocAddr_RC = (EZDBStringItem)newItem("billToCustLocAddr_RC");
		billToCustCd_RC = (EZDBStringItem)newItem("billToCustCd_RC");
		billToCustAcctCd_RC = (EZDBStringItem)newItem("billToCustAcctCd_RC");
		billToCustAcctNm_RC = (EZDBStringItem)newItem("billToCustAcctNm_RC");
		soldToCustLocAddr_RC = (EZDBStringItem)newItem("soldToCustLocAddr_RC");
		sellToCustCd_RC = (EZDBStringItem)newItem("sellToCustCd_RC");
		soldToCustLocCd_RC = (EZDBStringItem)newItem("soldToCustLocCd_RC");
		soldToCustAcctNm_RC = (EZDBStringItem)newItem("soldToCustAcctNm_RC");
		mdlGrpDescTxt_RC = (EZDBStringItem)newItem("mdlGrpDescTxt_RC");
		mdlDescTxt_RC = (EZDBStringItem)newItem("mdlDescTxt_RC");
		svcSegDescTxt_RC = (EZDBStringItem)newItem("svcSegDescTxt_RC");
		svcIstlReqFlg_RC = (EZDBStringItem)newItem("svcIstlReqFlg_RC");
		siteSrvyReqFlg_RC = (EZDBStringItem)newItem("siteSrvyReqFlg_RC");
		slsCrSplFlg_RC = (EZDBStringItem)newItem("slsCrSplFlg_RC");
		dsCpoConfigHldFlg_RC = (EZDBStringItem)newItem("dsCpoConfigHldFlg_RC");
		cratUsrNm_RC = (EZDBStringItem)newItem("cratUsrNm_RC");
		cratTsDplyTxt_RC = (EZDBStringItem)newItem("cratTsDplyTxt_RC");
		updUsrNm_RC = (EZDBStringItem)newItem("updUsrNm_RC");
		updTsDplyTxt_RC = (EZDBStringItem)newItem("updTsDplyTxt_RC");
		configTotDealNetAmt_RC = (EZDBBigDecimalItem)newItem("configTotDealNetAmt_RC");
		configTotDealChrgAmt_RC = (EZDBBigDecimalItem)newItem("configTotDealChrgAmt_RC");
		configTotDealTaxAmt_RC = (EZDBBigDecimalItem)newItem("configTotDealTaxAmt_RC");
		configSubTotDealAmt_RC = (EZDBBigDecimalItem)newItem("configSubTotDealAmt_RC");
		dropShipFlg_RC = (EZDBStringItem)newItem("dropShipFlg_RC");
		dsCpoConfigPk_RC = (EZDBBigDecimalItem)newItem("dsCpoConfigPk_RC");
		mdlId_RC = (EZDBBigDecimalItem)newItem("mdlId_RC");
		shipToLocNm_RC = (EZDBStringItem)newItem("shipToLocNm_RC");
		shipToAddlLocNm_RC = (EZDBStringItem)newItem("shipToAddlLocNm_RC");
		shipToFirstLineAddr_RC = (EZDBStringItem)newItem("shipToFirstLineAddr_RC");
		shipToScdLineAddr_RC = (EZDBStringItem)newItem("shipToScdLineAddr_RC");
		shipToThirdLineAddr_RC = (EZDBStringItem)newItem("shipToThirdLineAddr_RC");
		shipToFrthLineAddr_RC = (EZDBStringItem)newItem("shipToFrthLineAddr_RC");
		shipToFirstRefCmntTxt_RC = (EZDBStringItem)newItem("shipToFirstRefCmntTxt_RC");
		shipToScdRefCmntTxt_RC = (EZDBStringItem)newItem("shipToScdRefCmntTxt_RC");
		shipToPostCd_RC = (EZDBStringItem)newItem("shipToPostCd_RC");
		shipToCtyAddr_RC = (EZDBStringItem)newItem("shipToCtyAddr_RC");
		shipToStCd_RC = (EZDBStringItem)newItem("shipToStCd_RC");
		shipToProvNm_RC = (EZDBStringItem)newItem("shipToProvNm_RC");
		shipToCtryCd_RC = (EZDBStringItem)newItem("shipToCtryCd_RC");
		shipToCntyNm_RC = (EZDBStringItem)newItem("shipToCntyNm_RC");
		pickSvcConfigMstrPk_RC = (EZDBBigDecimalItem)newItem("pickSvcConfigMstrPk_RC");
		configNewFlg_RC = (EZDBStringItem)newItem("configNewFlg_RC");
		csmpContrNum_RC = (EZDBStringItem)newItem("csmpContrNum_RC");
		xxImageTxt_RN = (EZDBStringItem)newItem("xxImageTxt_RN");
		dlrRefNum_RC = (EZDBStringItem)newItem("dlrRefNum_RC");
		xxImageTxt_RD = (EZDBStringItem)newItem("xxImageTxt_RD");
		csmpPrcListCd_RC = (EZDBStringItem)newItem("csmpPrcListCd_RC");
		prcCatgNm_RC = (EZDBStringItem)newItem("prcCatgNm_RC");
		svcMdlTpCd_RC = (EZDBStringItem)newItem("svcMdlTpCd_RC");
		prntMdseCd_RC = (EZDBStringItem)newItem("prntMdseCd_RC");
		origShipToCustCd_RC = (EZDBStringItem)newItem("origShipToCustCd_RC");
		origShipToLocNm_RC = (EZDBStringItem)newItem("origShipToLocNm_RC");
		origShipToAddlLocNm_RC = (EZDBStringItem)newItem("origShipToAddlLocNm_RC");
		origShipFirstLineAddr_RC = (EZDBStringItem)newItem("origShipFirstLineAddr_RC");
		origShipScdLineAddr_RC = (EZDBStringItem)newItem("origShipScdLineAddr_RC");
		origShipThirdLineAddr_RC = (EZDBStringItem)newItem("origShipThirdLineAddr_RC");
		origShipFrthLineAddr_RC = (EZDBStringItem)newItem("origShipFrthLineAddr_RC");
		origShipToPostCd_RC = (EZDBStringItem)newItem("origShipToPostCd_RC");
		origShipToCtyAddr_RC = (EZDBStringItem)newItem("origShipToCtyAddr_RC");
		origShipToStCd_RC = (EZDBStringItem)newItem("origShipToStCd_RC");
		origShipToCtryCd_RC = (EZDBStringItem)newItem("origShipToCtryCd_RC");
		origShipToCntyNm_RC = (EZDBStringItem)newItem("origShipToCntyNm_RC");
		xxEdtModeFlg_RC = (EZDBStringItem)newItem("xxEdtModeFlg_RC");
		xxImageTxt_CC = (EZDBStringItem)newItem("xxImageTxt_CC");
		xxImageTxt_CA = (EZDBStringItem)newItem("xxImageTxt_CA");
		xxImageTxt_CS = (EZDBStringItem)newItem("xxImageTxt_CS");
		xxImageTxt_CN = (EZDBStringItem)newItem("xxImageTxt_CN");
		xxImageTxt_CT = (EZDBStringItem)newItem("xxImageTxt_CT");
		xxImageTxt_CB = (EZDBStringItem)newItem("xxImageTxt_CB");
		xxImageTxt_CM = (EZDBStringItem)newItem("xxImageTxt_CM");
		xxImageTxt_CD = (EZDBStringItem)newItem("xxImageTxt_CD");
		xxYesNoCd_RC = (EZDBStringItem)newItem("xxYesNoCd_RC");
		xxResFltrFlg_RC = (EZDBStringItem)newItem("xxResFltrFlg_RC");
		xxPageNum_RC = (EZDBBigDecimalItem)newItem("xxPageNum_RC");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1500_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1500_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg_R", "xxSmryLineFlg_R", "R", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_RC", "xxChkBox_RC", "RC", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsOrdPosnNum_RC", "dsOrdPosnNum_RC", "RC", null, TYPE_HANKAKUEISU, "6", null},
	{"configTpCd_RC", "configTpCd_RC", "RC", null, TYPE_HANKAKUEISU, "16", null},
	{"svcConfigMstrPk_RC", "svcConfigMstrPk_RC", "RC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlNm_RC", "mdlNm_RC", "RC", null, TYPE_HANKAKUEISU, "50", null},
	{"shipToCustLocAddr_RC", "shipToCustLocAddr_RC", "RC", null, TYPE_HANKAKUEISU, "300", null},
	{"shipToCustCd_RC", "shipToCustCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctCd_RC", "shipToCustAcctCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctNm_RC", "shipToCustAcctNm_RC", "RC", null, TYPE_HANKAKUEISU, "360", null},
	{"addShipToLocNm_RC", "addShipToLocNm_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"billToCustLocAddr_RC", "billToCustLocAddr_RC", "RC", null, TYPE_HANKAKUEISU, "300", null},
	{"billToCustCd_RC", "billToCustCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctCd_RC", "billToCustAcctCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctNm_RC", "billToCustAcctNm_RC", "RC", null, TYPE_HANKAKUEISU, "360", null},
	{"soldToCustLocAddr_RC", "soldToCustLocAddr_RC", "RC", null, TYPE_HANKAKUEISU, "300", null},
	{"sellToCustCd_RC", "sellToCustCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd_RC", "soldToCustLocCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustAcctNm_RC", "soldToCustAcctNm_RC", "RC", null, TYPE_HANKAKUEISU, "360", null},
	{"mdlGrpDescTxt_RC", "mdlGrpDescTxt_RC", "RC", null, TYPE_HANKAKUEISU, "90", null},
	{"mdlDescTxt_RC", "mdlDescTxt_RC", "RC", null, TYPE_HANKAKUEISU, "90", null},
	{"svcSegDescTxt_RC", "svcSegDescTxt_RC", "RC", null, TYPE_HANKAKUEISU, "50", null},
	{"svcIstlReqFlg_RC", "svcIstlReqFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"siteSrvyReqFlg_RC", "siteSrvyReqFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"slsCrSplFlg_RC", "slsCrSplFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCpoConfigHldFlg_RC", "dsCpoConfigHldFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"cratUsrNm_RC", "cratUsrNm_RC", "RC", null, TYPE_HANKAKUEISU, "93", null},
	{"cratTsDplyTxt_RC", "cratTsDplyTxt_RC", "RC", null, TYPE_HANKAKUEISU, "19", null},
	{"updUsrNm_RC", "updUsrNm_RC", "RC", null, TYPE_HANKAKUEISU, "93", null},
	{"updTsDplyTxt_RC", "updTsDplyTxt_RC", "RC", null, TYPE_HANKAKUEISU, "19", null},
	{"configTotDealNetAmt_RC", "configTotDealNetAmt_RC", "RC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"configTotDealChrgAmt_RC", "configTotDealChrgAmt_RC", "RC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"configTotDealTaxAmt_RC", "configTotDealTaxAmt_RC", "RC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"configSubTotDealAmt_RC", "configSubTotDealAmt_RC", "RC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dropShipFlg_RC", "dropShipFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCpoConfigPk_RC", "dsCpoConfigPk_RC", "RC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId_RC", "mdlId_RC", "RC", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"shipToLocNm_RC", "shipToLocNm_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToAddlLocNm_RC", "shipToAddlLocNm_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFirstLineAddr_RC", "shipToFirstLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToScdLineAddr_RC", "shipToScdLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToThirdLineAddr_RC", "shipToThirdLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFrthLineAddr_RC", "shipToFrthLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToFirstRefCmntTxt_RC", "shipToFirstRefCmntTxt_RC", "RC", null, TYPE_HANKAKUEISU, "90", null},
	{"shipToScdRefCmntTxt_RC", "shipToScdRefCmntTxt_RC", "RC", null, TYPE_HANKAKUEISU, "90", null},
	{"shipToPostCd_RC", "shipToPostCd_RC", "RC", null, TYPE_HANKAKUEISU, "15", null},
	{"shipToCtyAddr_RC", "shipToCtyAddr_RC", "RC", null, TYPE_HANKAKUEISU, "25", null},
	{"shipToStCd_RC", "shipToStCd_RC", "RC", null, TYPE_HANKAKUEISU, "2", null},
	{"shipToProvNm_RC", "shipToProvNm_RC", "RC", null, TYPE_HANKAKUEISU, "25", null},
	{"shipToCtryCd_RC", "shipToCtryCd_RC", "RC", null, TYPE_HANKAKUEISU, "3", null},
	{"shipToCntyNm_RC", "shipToCntyNm_RC", "RC", null, TYPE_HANKAKUEISU, "30", null},
	{"pickSvcConfigMstrPk_RC", "pickSvcConfigMstrPk_RC", "RC", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"configNewFlg_RC", "configNewFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"csmpContrNum_RC", "csmpContrNum_RC", "RC", null, TYPE_HANKAKUEISU, "15", null},
	{"xxImageTxt_RN", "xxImageTxt_RN", "RN", null, TYPE_HANKAKUEISU, "1", null},
	{"dlrRefNum_RC", "dlrRefNum_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"xxImageTxt_RD", "xxImageTxt_RD", "RD", null, TYPE_HANKAKUEISU, "1", null},
	{"csmpPrcListCd_RC", "csmpPrcListCd_RC", "RC", null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm_RC", "prcCatgNm_RC", "RC", null, TYPE_HANKAKUEISU, "75", null},
	{"svcMdlTpCd_RC", "svcMdlTpCd_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"prntMdseCd_RC", "prntMdseCd_RC", "RC", null, TYPE_HANKAKUEISU, "16", null},
	{"origShipToCustCd_RC", "origShipToCustCd_RC", "RC", null, TYPE_HANKAKUEISU, "20", null},
	{"origShipToLocNm_RC", "origShipToLocNm_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"origShipToAddlLocNm_RC", "origShipToAddlLocNm_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"origShipFirstLineAddr_RC", "origShipFirstLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"origShipScdLineAddr_RC", "origShipScdLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"origShipThirdLineAddr_RC", "origShipThirdLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"origShipFrthLineAddr_RC", "origShipFrthLineAddr_RC", "RC", null, TYPE_HANKAKUEISU, "60", null},
	{"origShipToPostCd_RC", "origShipToPostCd_RC", "RC", null, TYPE_HANKAKUEISU, "15", null},
	{"origShipToCtyAddr_RC", "origShipToCtyAddr_RC", "RC", null, TYPE_HANKAKUEISU, "25", null},
	{"origShipToStCd_RC", "origShipToStCd_RC", "RC", null, TYPE_HANKAKUEISU, "2", null},
	{"origShipToCtryCd_RC", "origShipToCtryCd_RC", "RC", null, TYPE_HANKAKUEISU, "3", null},
	{"origShipToCntyNm_RC", "origShipToCntyNm_RC", "RC", null, TYPE_HANKAKUEISU, "30", null},
	{"xxEdtModeFlg_RC", "xxEdtModeFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CC", "xxImageTxt_CC", "CC", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CA", "xxImageTxt_CA", "CA", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CS", "xxImageTxt_CS", "CS", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CN", "xxImageTxt_CN", "CN", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CT", "xxImageTxt_CT", "CT", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CB", "xxImageTxt_CB", "CB", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CM", "xxImageTxt_CM", "CM", null, TYPE_HANKAKUEISU, "1", null},
	{"xxImageTxt_CD", "xxImageTxt_CD", "CD", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_RC", "xxYesNoCd_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"xxResFltrFlg_RC", "xxResFltrFlg_RC", "RC", null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageNum_RC", "xxPageNum_RC", "RC", null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_R
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_RC
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_RC
        {"CONFIG_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configTpCd_RC
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_RC
        {"MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_RC
        {"SHIP_TO_CUST_LOC_ADDR",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustLocAddr_RC
        {"SHIP_TO_CUST_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_RC
        {"SHIP_TO_CUST_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_RC
        {"SHIP_TO_CUST_ACCT_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctNm_RC
        {"ADD_SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addShipToLocNm_RC
        {"BILL_TO_CUST_LOC_ADDR",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustLocAddr_RC
        {"BILL_TO_CUST_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_RC
        {"BILL_TO_CUST_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_RC
        {"BILL_TO_CUST_ACCT_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctNm_RC
        {"SOLD_TO_CUST_LOC_ADDR",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocAddr_RC
        {"SELL_TO_CUST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_RC
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_RC
        {"SOLD_TO_CUST_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustAcctNm_RC
        {"MDL_GRP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpDescTxt_RC
        {"MDL_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlDescTxt_RC
        {"SVC_SEG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSegDescTxt_RC
        {"SVC_ISTL_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcIstlReqFlg_RC
        {"SITE_SRVY_REQ_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//siteSrvyReqFlg_RC
        {"SLS_CR_SPL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCrSplFlg_RC
        {"DS_CPO_CONFIG_HLD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigHldFlg_RC
        {"CRAT_USR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratUsrNm_RC
        {"CRAT_TS_DPLY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratTsDplyTxt_RC
        {"UPD_USR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrNm_RC
        {"UPD_TS_DPLY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updTsDplyTxt_RC
        {"CONFIG_TOT_DEAL_NET_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//configTotDealNetAmt_RC
        {"CONFIG_TOT_DEAL_CHRG_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//configTotDealChrgAmt_RC
        {"CONFIG_TOT_DEAL_TAX_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//configTotDealTaxAmt_RC
        {"CONFIG_SUB_TOT_DEAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//configSubTotDealAmt_RC
        {"DROP_SHIP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dropShipFlg_RC
        {"DS_CPO_CONFIG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoConfigPk_RC
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_RC
        {"SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_RC
        {"SHIP_TO_ADDL_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToAddlLocNm_RC
        {"SHIP_TO_FIRST_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFirstLineAddr_RC
        {"SHIP_TO_SCD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToScdLineAddr_RC
        {"SHIP_TO_THIRD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToThirdLineAddr_RC
        {"SHIP_TO_FRTH_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFrthLineAddr_RC
        {"SHIP_TO_FIRST_REF_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFirstRefCmntTxt_RC
        {"SHIP_TO_SCD_REF_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToScdRefCmntTxt_RC
        {"SHIP_TO_POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToPostCd_RC
        {"SHIP_TO_CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtyAddr_RC
        {"SHIP_TO_ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd_RC
        {"SHIP_TO_PROV_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToProvNm_RC
        {"SHIP_TO_CTRY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtryCd_RC
        {"SHIP_TO_CNTY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCntyNm_RC
        {"PICK_SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pickSvcConfigMstrPk_RC
        {"CONFIG_NEW_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configNewFlg_RC
        {"CSMP_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum_RC
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_RN
        {"DLR_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_RC
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_RD
        {"CSMP_PRC_LIST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpPrcListCd_RC
        {"PRC_CATG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_RC
        {"SVC_MDL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMdlTpCd_RC
        {"PRNT_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntMdseCd_RC
        {"ORIG_SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToCustCd_RC
        {"ORIG_SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToLocNm_RC
        {"ORIG_SHIP_TO_ADDL_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToAddlLocNm_RC
        {"ORIG_SHIP_FIRST_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipFirstLineAddr_RC
        {"ORIG_SHIP_SCD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipScdLineAddr_RC
        {"ORIG_SHIP_THIRD_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipThirdLineAddr_RC
        {"ORIG_SHIP_FRTH_LINE_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipFrthLineAddr_RC
        {"ORIG_SHIP_TO_POST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToPostCd_RC
        {"ORIG_SHIP_TO_CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToCtyAddr_RC
        {"ORIG_SHIP_TO_ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToStCd_RC
        {"ORIG_SHIP_TO_CTRY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToCtryCd_RC
        {"ORIG_SHIP_TO_CNTY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origShipToCntyNm_RC
        {"XX_EDT_MODE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtModeFlg_RC
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CC
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CA
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CS
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CN
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CT
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CB
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CM
        {"XX_IMAGE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxImageTxt_CD
        {"XX_YES_NO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_RC
        {"XX_RES_FLTR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxResFltrFlg_RC
        {"XX_PAGE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageNum_RC
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
