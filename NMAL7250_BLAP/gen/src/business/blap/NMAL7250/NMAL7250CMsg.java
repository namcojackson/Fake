//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530144511000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7250CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7250;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7250 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7250CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** P*/
	public final business.blap.NMAL7250.NMAL7250_PCMsgArray              P;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** PRC_RULE_NM*/
	public final EZDCStringItem              prcRuleNm;

    /** DEF_RULE_PRCD_NUM*/
	public final EZDCBigDecimalItem              defRulePrcdNum;

    /** PRC_RULE_CATG_CD_P*/
	public final EZDCStringItemArray              prcRuleCatgCd_P;

    /** PRC_RULE_CATG_DESC_TXT_P*/
	public final EZDCStringItemArray              prcRuleCatgDescTxt_P;

    /** PRC_RULE_CATG_CD*/
	public final EZDCStringItem              prcRuleCatgCd;

    /** PRC_RULE_TRX_CATG_CD_P*/
	public final EZDCStringItemArray              prcRuleTrxCatgCd_P;

    /** PRC_RULE_TRX_CATG_DESC_TXT_P*/
	public final EZDCStringItemArray              prcRuleTrxCatgDescTxt_P;

    /** PRC_RULE_TRX_CATG_CD*/
	public final EZDCStringItem              prcRuleTrxCatgCd;

    /** XX_LINK_ANCR_OC*/
	public final EZDCStringItem              xxLinkAncr_OC;

    /** DS_ORD_CATG_CD*/
	public final EZDCStringItem              dsOrdCatgCd;

    /** XX_LINK_ANCR_OT*/
	public final EZDCStringItem              xxLinkAncr_OT;

    /** DS_ORD_TP_CD*/
	public final EZDCStringItem              dsOrdTpCd;

    /** PRC_RULE_ATTRB_CD_P*/
	public final EZDCStringItemArray              prcRuleAttrbCd_P;

    /** PRC_RULE_ATTRB_DESC_TXT_P*/
	public final EZDCStringItemArray              prcRuleAttrbDescTxt_P;

    /** PRC_RULE_ATTRB_CD*/
	public final EZDCStringItem              prcRuleAttrbCd;

    /** PRC_RULE_COND_FROM_TXT*/
	public final EZDCStringItem              prcRuleCondFromTxt;

    /** XX_LINK_ANCR_AC*/
	public final EZDCStringItem              xxLinkAncr_AC;

    /** DS_ACCT_NUM*/
	public final EZDCStringItem              dsAcctNum;

    /** XX_LINK_ANCR_AN*/
	public final EZDCStringItem              xxLinkAncr_AN;

    /** DS_ACCT_NM*/
	public final EZDCStringItem              dsAcctNm;

    /** XX_LINK_ANCR_CN*/
	public final EZDCStringItem              xxLinkAncr_CN;

    /** CSMP_CONTR_NUM*/
	public final EZDCStringItem              csmpContrNum;

    /** XX_LINK_ANCR_CG*/
	public final EZDCStringItem              xxLinkAncr_CG;

    /** PRC_GRP_PK_CG*/
	public final EZDCBigDecimalItem              prcGrpPk_CG;

    /** XX_LINK_ANCR_BR*/
	public final EZDCStringItem              xxLinkAncr_BR;

    /** COA_BR_CD*/
	public final EZDCStringItem              coaBrCd;

    /** LINE_BIZ_TP_CD_P*/
	public final EZDCStringItemArray              lineBizTpCd_P;

    /** LINE_BIZ_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              lineBizTpDescTxt_P;

    /** LINE_BIZ_TP_CD*/
	public final EZDCStringItem              lineBizTpCd;

    /** XX_LINK_ANCR_MA*/
	public final EZDCStringItem              xxLinkAncr_MA;

    /** PRC_GRP_PK_MA*/
	public final EZDCBigDecimalItem              prcGrpPk_MA;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** PRC_DISP_REC_TP_CD_P*/
	public final EZDCStringItemArray              prcDispRecTpCd_P;

    /** PRC_DISP_REC_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              prcDispRecTpDescTxt_P;

    /** PRC_DISP_REC_TP_CD*/
	public final EZDCStringItem              prcDispRecTpCd;

    /** PRC_RULE_COND_TP_CD_P*/
	public final EZDCStringItemArray              prcRuleCondTpCd_P;

    /** PRC_RULE_COND_TP_DESC_TXT_P*/
	public final EZDCStringItemArray              prcRuleCondTpDescTxt_P;

    /** PRC_RULE_COND_TP_CD*/
	public final EZDCStringItem              prcRuleCondTpCd;

    /** A*/
	public final business.blap.NMAL7250.NMAL7250_ACMsgArray              A;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** SRCH_OPT_PK*/
	public final EZDCBigDecimalItem              srchOptPk;

    /** SRCH_OPT_PK_L1*/
	public final EZDCBigDecimalItemArray              srchOptPk_L1;

    /** SRCH_OPT_NM_L1*/
	public final EZDCStringItemArray              srchOptNm_L1;

    /** SRCH_OPT_NM*/
	public final EZDCStringItem              srchOptNm;


	/**
	 * NMAL7250CMsg is constructor.
	 * The initialization when the instance of NMAL7250CMsg is generated.
	 */
	public NMAL7250CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7250CMsg is constructor.
	 * The initialization when the instance of NMAL7250CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7250CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		P = (business.blap.NMAL7250.NMAL7250_PCMsgArray)newMsgArray("P");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		prcRuleNm = (EZDCStringItem)newItem("prcRuleNm");
		defRulePrcdNum = (EZDCBigDecimalItem)newItem("defRulePrcdNum");
		prcRuleCatgCd_P = (EZDCStringItemArray)newItemArray("prcRuleCatgCd_P");
		prcRuleCatgDescTxt_P = (EZDCStringItemArray)newItemArray("prcRuleCatgDescTxt_P");
		prcRuleCatgCd = (EZDCStringItem)newItem("prcRuleCatgCd");
		prcRuleTrxCatgCd_P = (EZDCStringItemArray)newItemArray("prcRuleTrxCatgCd_P");
		prcRuleTrxCatgDescTxt_P = (EZDCStringItemArray)newItemArray("prcRuleTrxCatgDescTxt_P");
		prcRuleTrxCatgCd = (EZDCStringItem)newItem("prcRuleTrxCatgCd");
		xxLinkAncr_OC = (EZDCStringItem)newItem("xxLinkAncr_OC");
		dsOrdCatgCd = (EZDCStringItem)newItem("dsOrdCatgCd");
		xxLinkAncr_OT = (EZDCStringItem)newItem("xxLinkAncr_OT");
		dsOrdTpCd = (EZDCStringItem)newItem("dsOrdTpCd");
		prcRuleAttrbCd_P = (EZDCStringItemArray)newItemArray("prcRuleAttrbCd_P");
		prcRuleAttrbDescTxt_P = (EZDCStringItemArray)newItemArray("prcRuleAttrbDescTxt_P");
		prcRuleAttrbCd = (EZDCStringItem)newItem("prcRuleAttrbCd");
		prcRuleCondFromTxt = (EZDCStringItem)newItem("prcRuleCondFromTxt");
		xxLinkAncr_AC = (EZDCStringItem)newItem("xxLinkAncr_AC");
		dsAcctNum = (EZDCStringItem)newItem("dsAcctNum");
		xxLinkAncr_AN = (EZDCStringItem)newItem("xxLinkAncr_AN");
		dsAcctNm = (EZDCStringItem)newItem("dsAcctNm");
		xxLinkAncr_CN = (EZDCStringItem)newItem("xxLinkAncr_CN");
		csmpContrNum = (EZDCStringItem)newItem("csmpContrNum");
		xxLinkAncr_CG = (EZDCStringItem)newItem("xxLinkAncr_CG");
		prcGrpPk_CG = (EZDCBigDecimalItem)newItem("prcGrpPk_CG");
		xxLinkAncr_BR = (EZDCStringItem)newItem("xxLinkAncr_BR");
		coaBrCd = (EZDCStringItem)newItem("coaBrCd");
		lineBizTpCd_P = (EZDCStringItemArray)newItemArray("lineBizTpCd_P");
		lineBizTpDescTxt_P = (EZDCStringItemArray)newItemArray("lineBizTpDescTxt_P");
		lineBizTpCd = (EZDCStringItem)newItem("lineBizTpCd");
		xxLinkAncr_MA = (EZDCStringItem)newItem("xxLinkAncr_MA");
		prcGrpPk_MA = (EZDCBigDecimalItem)newItem("prcGrpPk_MA");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		prcDispRecTpCd_P = (EZDCStringItemArray)newItemArray("prcDispRecTpCd_P");
		prcDispRecTpDescTxt_P = (EZDCStringItemArray)newItemArray("prcDispRecTpDescTxt_P");
		prcDispRecTpCd = (EZDCStringItem)newItem("prcDispRecTpCd");
		prcRuleCondTpCd_P = (EZDCStringItemArray)newItemArray("prcRuleCondTpCd_P");
		prcRuleCondTpDescTxt_P = (EZDCStringItemArray)newItemArray("prcRuleCondTpDescTxt_P");
		prcRuleCondTpCd = (EZDCStringItem)newItem("prcRuleCondTpCd");
		A = (business.blap.NMAL7250.NMAL7250_ACMsgArray)newMsgArray("A");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		srchOptPk = (EZDCBigDecimalItem)newItem("srchOptPk");
		srchOptPk_L1 = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_L1");
		srchOptNm_L1 = (EZDCStringItemArray)newItemArray("srchOptNm_L1");
		srchOptNm = (EZDCStringItem)newItem("srchOptNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7250CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7250CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"P", "P", null, "30", "business.blap.NMAL7250.NMAL7250_PCMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcRuleNm", "prcRuleNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"defRulePrcdNum", "defRulePrcdNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcRuleCatgCd_P", "prcRuleCatgCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCatgDescTxt_P", "prcRuleCatgDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCatgCd", "prcRuleCatgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleTrxCatgCd_P", "prcRuleTrxCatgCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcRuleTrxCatgDescTxt_P", "prcRuleTrxCatgDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcRuleTrxCatgCd", "prcRuleTrxCatgCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxLinkAncr_OC", "xxLinkAncr_OC", "OC", null, TYPE_HANKAKUEISU, "30", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxLinkAncr_OT", "xxLinkAncr_OT", "OT", null, TYPE_HANKAKUEISU, "30", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcRuleAttrbCd_P", "prcRuleAttrbCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcRuleAttrbDescTxt_P", "prcRuleAttrbDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcRuleAttrbCd", "prcRuleAttrbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCondFromTxt", "prcRuleCondFromTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkAncr_AC", "xxLinkAncr_AC", "AC", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkAncr_AN", "xxLinkAncr_AN", "AN", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"xxLinkAncr_CN", "xxLinkAncr_CN", "CN", null, TYPE_HANKAKUEISU, "30", null},
	{"csmpContrNum", "csmpContrNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"xxLinkAncr_CG", "xxLinkAncr_CG", "CG", null, TYPE_HANKAKUEISU, "30", null},
	{"prcGrpPk_CG", "prcGrpPk_CG", "CG", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxLinkAncr_BR", "xxLinkAncr_BR", "BR", null, TYPE_HANKAKUEISU, "30", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"lineBizTpCd_P", "lineBizTpCd_P", "P", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_P", "lineBizTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxLinkAncr_MA", "xxLinkAncr_MA", "MA", null, TYPE_HANKAKUEISU, "30", null},
	{"prcGrpPk_MA", "prcGrpPk_MA", "MA", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prcDispRecTpCd_P", "prcDispRecTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcDispRecTpDescTxt_P", "prcDispRecTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcDispRecTpCd", "prcDispRecTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCondTpCd_P", "prcRuleCondTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCondTpDescTxt_P", "prcRuleCondTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCondTpCd", "prcRuleCondTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"A", "A", null, "200", "business.blap.NMAL7250.NMAL7250_ACMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"srchOptPk", "srchOptPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_L1", "srchOptPk_L1", "L1", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L1", "srchOptNm_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm", "srchOptNm", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"PRC_RULE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleNm
        {"DEF_RULE_PRCD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defRulePrcdNum
        {"PRC_RULE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgCd_P
        {"PRC_RULE_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgDescTxt_P
        {"PRC_RULE_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgCd
        {"PRC_RULE_TRX_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleTrxCatgCd_P
        {"PRC_RULE_TRX_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleTrxCatgDescTxt_P
        {"PRC_RULE_TRX_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleTrxCatgCd
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_OC
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_OT
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"PRC_RULE_ATTRB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleAttrbCd_P
        {"PRC_RULE_ATTRB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleAttrbDescTxt_P
        {"PRC_RULE_ATTRB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleAttrbCd
        {"PRC_RULE_COND_FROM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondFromTxt
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_AC
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_AN
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_CN
        {"CSMP_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrNum
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_CG
        {"PRC_GRP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpPk_CG
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_BR
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_P
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_P
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_MA
        {"PRC_GRP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpPk_MA
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"PRC_DISP_REC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDispRecTpCd_P
        {"PRC_DISP_REC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDispRecTpDescTxt_P
        {"PRC_DISP_REC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcDispRecTpCd
        {"PRC_RULE_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpCd_P
        {"PRC_RULE_COND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpDescTxt_P
        {"PRC_RULE_COND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpCd
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L1
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L1
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm
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
