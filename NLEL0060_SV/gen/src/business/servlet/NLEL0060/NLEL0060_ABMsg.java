//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180828143538000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0060_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** DS_ASSET_MSTR_PK_A1*/
	public final EZDBBigDecimalItem              dsAssetMstrPk_A1;

    /** DS_ASSET_DESC_TXT_A1*/
	public final EZDBStringItem              dsAssetDescTxt_A1;

    /** ASSET_TP_DESC_TXT_A1*/
	public final EZDBStringItem              assetTpDescTxt_A1;

    /** ASSET_STS_CD_A1*/
	public final EZDBStringItem              assetStsCd_A1;

    /** ASSET_STS_DESC_TXT_A1*/
	public final EZDBStringItem              assetStsDescTxt_A1;

    /** DEPC_MTH_NUM_A1*/
	public final EZDBStringItem              depcMthNum_A1;

    /** TOT_ASSET_QTY_A1*/
	public final EZDBBigDecimalItem              totAssetQty_A1;

    /** DEPC_START_DT_A1*/
	public final EZDBDateItem              depcStartDt_A1;

    /** SVC_CONFIG_MSTR_PK_A1*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_A1;

    /** SER_NUM_A1*/
	public final EZDBStringItem              serNum_A1;

    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDBStringItem              coaMdseTpCd_A1;

    /** COA_PROD_CD_A1*/
	public final EZDBStringItem              coaProdCd_A1;

    /** ASSET_TAG_NUM_A1*/
	public final EZDBStringItem              assetTagNum_A1;

    /** DS_ASSET_GRP_INIT_BOOK_AMT_A1*/
	public final EZDBBigDecimalItem              dsAssetGrpInitBookAmt_A1;

    /** CUR_VAL_AMT_A1*/
	public final EZDBBigDecimalItem              curValAmt_A1;

    /** AMT_CHNG_RSN_TP_CD_A1*/
	public final EZDBStringItem              amtChngRsnTpCd_A1;

    /** AMT_CHNG_RSN_TP_NM_A1*/
	public final EZDBStringItem              amtChngRsnTpNm_A1;

    /** PRNT_DS_ASSET_MSTR_PK_A1*/
	public final EZDBBigDecimalItem              prntDsAssetMstrPk_A1;

    /** CPO_ORD_NUM_A1*/
	public final EZDBStringItem              cpoOrdNum_A1;

    /** DPLY_LINE_NUM_A1*/
	public final EZDBStringItem              dplyLineNum_A1;

    /** DS_CONTR_NUM_A1*/
	public final EZDBStringItem              dsContrNum_A1;

    /** CONTR_EFF_FROM_DT_A1*/
	public final EZDBDateItem              contrEffFromDt_A1;

    /** CONTR_EFF_THRU_DT_A1*/
	public final EZDBDateItem              contrEffThruDt_A1;

    /** INV_NUM_A1*/
	public final EZDBStringItem              invNum_A1;

    /** CUST_ISS_PO_NUM_A1*/
	public final EZDBStringItem              custIssPoNum_A1;

    /** VND_CD_A1*/
	public final EZDBStringItem              vndCd_A1;

    /** PRNT_VND_NM_A1*/
	public final EZDBStringItem              prntVndNm_A1;

    /** SVC_INV_NUM_A1*/
	public final EZDBStringItem              svcInvNum_A1;

    /** INV_DT_A1*/
	public final EZDBDateItem              invDt_A1;

    /** LAST_DEPC_YR_MTH_A1*/
	public final EZDBDateItem              lastDepcYrMth_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** SVC_MACH_MSTR_PK_A1*/
	public final EZDBBigDecimalItem              svcMachMstrPk_A1;

    /** MAN_ENTRY_FLG_A1*/
	public final EZDBStringItem              manEntryFlg_A1;

    /** SHIP_TO_CUST_ACCT_CD_A1*/
	public final EZDBStringItem              shipToCustAcctCd_A1;

    /** DEPC_CNT_A1*/
	public final EZDBBigDecimalItem              depcCnt_A1;

    /** INIT_BOOK_AMT_A1*/
	public final EZDBBigDecimalItem              initBookAmt_A1;

    /** RSDL_VAL_AMT_A1*/
	public final EZDBBigDecimalItem              rsdlValAmt_A1;

    /** XX_PG_FLG_A1*/
	public final EZDBStringItem              xxPgFlg_A1;

    /** DTL_CMNT_TXT_A1*/
	public final EZDBStringItem              dtlCmntTxt_A1;

    /** ADJ_COA_ACCT_CD_A1*/
	public final EZDBStringItem              adjCoaAcctCd_A1;


	/**
	 * NLEL0060_ABMsg is constructor.
	 * The initialization when the instance of NLEL0060_ABMsg is generated.
	 */
	public NLEL0060_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0060_ABMsg is constructor.
	 * The initialization when the instance of NLEL0060_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0060_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		dsAssetMstrPk_A1 = (EZDBBigDecimalItem)newItem("dsAssetMstrPk_A1");
		dsAssetDescTxt_A1 = (EZDBStringItem)newItem("dsAssetDescTxt_A1");
		assetTpDescTxt_A1 = (EZDBStringItem)newItem("assetTpDescTxt_A1");
		assetStsCd_A1 = (EZDBStringItem)newItem("assetStsCd_A1");
		assetStsDescTxt_A1 = (EZDBStringItem)newItem("assetStsDescTxt_A1");
		depcMthNum_A1 = (EZDBStringItem)newItem("depcMthNum_A1");
		totAssetQty_A1 = (EZDBBigDecimalItem)newItem("totAssetQty_A1");
		depcStartDt_A1 = (EZDBDateItem)newItem("depcStartDt_A1");
		svcConfigMstrPk_A1 = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_A1");
		serNum_A1 = (EZDBStringItem)newItem("serNum_A1");
		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		coaMdseTpCd_A1 = (EZDBStringItem)newItem("coaMdseTpCd_A1");
		coaProdCd_A1 = (EZDBStringItem)newItem("coaProdCd_A1");
		assetTagNum_A1 = (EZDBStringItem)newItem("assetTagNum_A1");
		dsAssetGrpInitBookAmt_A1 = (EZDBBigDecimalItem)newItem("dsAssetGrpInitBookAmt_A1");
		curValAmt_A1 = (EZDBBigDecimalItem)newItem("curValAmt_A1");
		amtChngRsnTpCd_A1 = (EZDBStringItem)newItem("amtChngRsnTpCd_A1");
		amtChngRsnTpNm_A1 = (EZDBStringItem)newItem("amtChngRsnTpNm_A1");
		prntDsAssetMstrPk_A1 = (EZDBBigDecimalItem)newItem("prntDsAssetMstrPk_A1");
		cpoOrdNum_A1 = (EZDBStringItem)newItem("cpoOrdNum_A1");
		dplyLineNum_A1 = (EZDBStringItem)newItem("dplyLineNum_A1");
		dsContrNum_A1 = (EZDBStringItem)newItem("dsContrNum_A1");
		contrEffFromDt_A1 = (EZDBDateItem)newItem("contrEffFromDt_A1");
		contrEffThruDt_A1 = (EZDBDateItem)newItem("contrEffThruDt_A1");
		invNum_A1 = (EZDBStringItem)newItem("invNum_A1");
		custIssPoNum_A1 = (EZDBStringItem)newItem("custIssPoNum_A1");
		vndCd_A1 = (EZDBStringItem)newItem("vndCd_A1");
		prntVndNm_A1 = (EZDBStringItem)newItem("prntVndNm_A1");
		svcInvNum_A1 = (EZDBStringItem)newItem("svcInvNum_A1");
		invDt_A1 = (EZDBDateItem)newItem("invDt_A1");
		lastDepcYrMth_A1 = (EZDBDateItem)newItem("lastDepcYrMth_A1");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		svcMachMstrPk_A1 = (EZDBBigDecimalItem)newItem("svcMachMstrPk_A1");
		manEntryFlg_A1 = (EZDBStringItem)newItem("manEntryFlg_A1");
		shipToCustAcctCd_A1 = (EZDBStringItem)newItem("shipToCustAcctCd_A1");
		depcCnt_A1 = (EZDBBigDecimalItem)newItem("depcCnt_A1");
		initBookAmt_A1 = (EZDBBigDecimalItem)newItem("initBookAmt_A1");
		rsdlValAmt_A1 = (EZDBBigDecimalItem)newItem("rsdlValAmt_A1");
		xxPgFlg_A1 = (EZDBStringItem)newItem("xxPgFlg_A1");
		dtlCmntTxt_A1 = (EZDBStringItem)newItem("dtlCmntTxt_A1");
		adjCoaAcctCd_A1 = (EZDBStringItem)newItem("adjCoaAcctCd_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0060_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0060_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsAssetMstrPk_A1", "dsAssetMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAssetDescTxt_A1", "dsAssetDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"assetTpDescTxt_A1", "assetTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"assetStsCd_A1", "assetStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"assetStsDescTxt_A1", "assetStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"depcMthNum_A1", "depcMthNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"totAssetQty_A1", "totAssetQty_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"depcStartDt_A1", "depcStartDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"svcConfigMstrPk_A1", "svcConfigMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"coaMdseTpCd_A1", "coaMdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProdCd_A1", "coaProdCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"assetTagNum_A1", "assetTagNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAssetGrpInitBookAmt_A1", "dsAssetGrpInitBookAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"curValAmt_A1", "curValAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"amtChngRsnTpCd_A1", "amtChngRsnTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"amtChngRsnTpNm_A1", "amtChngRsnTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"prntDsAssetMstrPk_A1", "prntDsAssetMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cpoOrdNum_A1", "cpoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyLineNum_A1", "dplyLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"contrEffFromDt_A1", "contrEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt_A1", "contrEffThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invNum_A1", "invNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"custIssPoNum_A1", "custIssPoNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"vndCd_A1", "vndCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"prntVndNm_A1", "prntVndNm_A1", "A1", null, TYPE_HANKAKUEISU, "240", null},
	{"svcInvNum_A1", "svcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"invDt_A1", "invDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"lastDepcYrMth_A1", "lastDepcYrMth_A1", "A1", null, TYPE_NENTSUKI, "6", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"svcMachMstrPk_A1", "svcMachMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"manEntryFlg_A1", "manEntryFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"shipToCustAcctCd_A1", "shipToCustAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"depcCnt_A1", "depcCnt_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"initBookAmt_A1", "initBookAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rsdlValAmt_A1", "rsdlValAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxPgFlg_A1", "xxPgFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"dtlCmntTxt_A1", "dtlCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"adjCoaAcctCd_A1", "adjCoaAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"DS_ASSET_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetMstrPk_A1
        {"DS_ASSET_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetDescTxt_A1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_A1
        {"ASSET_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetStsCd_A1
        {"ASSET_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetStsDescTxt_A1
        {"DEPC_MTH_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcMthNum_A1
        {"TOT_ASSET_QTY",  NO,  "0",null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totAssetQty_A1
        {"DEPC_START_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//depcStartDt_A1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A1
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"COA_PROD_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"ASSET_TAG_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTagNum_A1
        {"DS_ASSET_GRP_INIT_BOOK_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//dsAssetGrpInitBookAmt_A1
        {"CUR_VAL_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//curValAmt_A1
        {"AMT_CHNG_RSN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//amtChngRsnTpCd_A1
        {"AMT_CHNG_RSN_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//amtChngRsnTpNm_A1
        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk_A1
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A1
        {"DPLY_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A1
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"CONTR_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffFromDt_A1
        {"CONTR_EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffThruDt_A1
        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A1
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A1
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A1
        {"PRNT_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm_A1
        {"SVC_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_A1
        {"INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDt_A1
        {"LAST_DEPC_YR_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//lastDepcYrMth_A1
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A1
        {"MAN_ENTRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manEntryFlg_A1
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_A1
        {"DEPC_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcCnt_A1
        {"INIT_BOOK_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//initBookAmt_A1
        {"RSDL_VAL_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rsdlValAmt_A1
        {"XX_PG_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPgFlg_A1
        {"DTL_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtlCmntTxt_A1
        {"ADJ_COA_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCoaAcctCd_A1
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

