//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170913135730000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7120BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7120 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7120BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** O*/
	public final business.servlet.NMAL7120.NMAL7120_OBMsgArray              O;

    /** P*/
	public final business.servlet.NMAL7120.NMAL7120_PBMsgArray              P;

    /** XX_PAGE_SHOW_FROM_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_10;

    /** XX_PAGE_SHOW_TO_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowToNum_10;

    /** XX_PAGE_SHOW_OF_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_10;

    /** XX_PAGE_SHOW_CUR_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowCurNum_10;

    /** XX_PAGE_SHOW_TOT_NUM_10*/
	public final EZDBBigDecimalItem              xxPageShowTotNum_10;

    /** XX_SORT_TBL_NM_01*/
	public final EZDBStringItem              xxSortTblNm_01;

    /** XX_SORT_ITEM_NM_01*/
	public final EZDBStringItem              xxSortItemNm_01;

    /** XX_SORT_ORD_BY_TXT_01*/
	public final EZDBStringItem              xxSortOrdByTxt_01;

    /** DS_ACCT_NUM_H1*/
	public final EZDBStringItem              dsAcctNum_H1;

    /** XX_LINK_PROT_01*/
	public final EZDBStringItem              xxLinkProt_01;

    /** DS_ACCT_NM_H1*/
	public final EZDBStringItem              dsAcctNm_H1;

    /** CSMP_NUM_H1*/
	public final EZDBStringItem              csmpNum_H1;

    /** DLR_REF_NUM_H1*/
	public final EZDBStringItem              dlrRefNum_H1;

    /** PRC_CATG_NM_H1*/
	public final EZDBStringItem              prcCatgNm_H1;

    /** XX_LINK_PROT_02*/
	public final EZDBStringItem              xxLinkProt_02;

    /** PRC_CONTR_NUM_H1*/
	public final EZDBStringItem              prcContrNum_H1;

    /** XX_LINK_PROT_03*/
	public final EZDBStringItem              xxLinkProt_03;

    /** EFF_FROM_DT_H1*/
	public final EZDBDateItem              effFromDt_H1;

    /** EFF_THRU_DT_H1*/
	public final EZDBDateItem              effThruDt_H1;

    /** CSMP_CONTR_ACTV_FLG_H1*/
	public final EZDBStringItem              csmpContrActvFlg_H1;

    /** CSMP_CONTR_ACTV_FLG_L1*/
	public final EZDBStringItemArray              csmpContrActvFlg_L1;

    /** XX_WF_PROC_STS_NM_L1*/
	public final EZDBStringItemArray              xxWfProcStsNm_L1;

    /** XX_MODE_NM_13_TXT_H1*/
	public final EZDBStringItem              xxModeNm13Txt_H1;

    /** RTL_DLR_CD_H1*/
	public final EZDBStringItem              rtlDlrCd_H1;

    /** RNW_CSMP_NUM_H1*/
	public final EZDBStringItem              rnwCsmpNum_H1;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** XX_DTL_CNT_H1*/
	public final EZDBBigDecimalItem              xxDtlCnt_H1;

    /** XX_DTL_CNT_H2*/
	public final EZDBBigDecimalItem              xxDtlCnt_H2;

    /** CSMP_NUM_MU*/
	public final EZDBStringItem              csmpNum_MU;

    /** DLR_REF_NUM_MU*/
	public final EZDBStringItem              dlrRefNum_MU;

    /** XX_LINK_PROT_04*/
	public final EZDBStringItem              xxLinkProt_04;

    /** PRC_CONTR_NUM_MU*/
	public final EZDBStringItem              prcContrNum_MU;

    /** CSMP_NUM_CMNT_TXT_MU*/
	public final EZDBStringItem              csmpNumCmntTxt_MU;

    /** EFF_FROM_DT_MU*/
	public final EZDBDateItem              effFromDt_MU;

    /** EFF_THRU_DT_MU*/
	public final EZDBDateItem              effThruDt_MU;

    /** CSMP_CONTR_ACTV_FLG_MU*/
	public final EZDBStringItem              csmpContrActvFlg_MU;

    /** CSMP_CONTR_ACTV_FLG_L2*/
	public final EZDBStringItemArray              csmpContrActvFlg_L2;

    /** XX_WF_PROC_STS_NM_L2*/
	public final EZDBStringItemArray              xxWfProcStsNm_L2;

    /** CUSA_REJ_DT_MU*/
	public final EZDBDateItem              cusaRejDt_MU;

    /** UPLFT_EQUIP_RATIO_MU*/
	public final EZDBBigDecimalItem              uplftEquipRatio_MU;

    /** UPLFT_ASRY_RATIO_MU*/
	public final EZDBBigDecimalItem              uplftAsryRatio_MU;

    /** RNW_CSMP_NUM_MU*/
	public final EZDBStringItem              rnwCsmpNum_MU;

    /** XX_CHK_BOX_AL*/
	public final EZDBStringItem              xxChkBox_AL;

    /** XX_MSG_TXT*/
	public final EZDBStringItem              xxMsgTxt;

    /** A*/
	public final business.servlet.NMAL7120.NMAL7120_ABMsgArray              A;


	/**
	 * NMAL7120BMsg is constructor.
	 * The initialization when the instance of NMAL7120BMsg is generated.
	 */
	public NMAL7120BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7120BMsg is constructor.
	 * The initialization when the instance of NMAL7120BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7120BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		O = (business.servlet.NMAL7120.NMAL7120_OBMsgArray)newMsgArray("O");
		P = (business.servlet.NMAL7120.NMAL7120_PBMsgArray)newMsgArray("P");
		xxPageShowFromNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_10");
		xxPageShowToNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowToNum_10");
		xxPageShowOfNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_10");
		xxPageShowCurNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowCurNum_10");
		xxPageShowTotNum_10 = (EZDBBigDecimalItem)newItem("xxPageShowTotNum_10");
		xxSortTblNm_01 = (EZDBStringItem)newItem("xxSortTblNm_01");
		xxSortItemNm_01 = (EZDBStringItem)newItem("xxSortItemNm_01");
		xxSortOrdByTxt_01 = (EZDBStringItem)newItem("xxSortOrdByTxt_01");
		dsAcctNum_H1 = (EZDBStringItem)newItem("dsAcctNum_H1");
		xxLinkProt_01 = (EZDBStringItem)newItem("xxLinkProt_01");
		dsAcctNm_H1 = (EZDBStringItem)newItem("dsAcctNm_H1");
		csmpNum_H1 = (EZDBStringItem)newItem("csmpNum_H1");
		dlrRefNum_H1 = (EZDBStringItem)newItem("dlrRefNum_H1");
		prcCatgNm_H1 = (EZDBStringItem)newItem("prcCatgNm_H1");
		xxLinkProt_02 = (EZDBStringItem)newItem("xxLinkProt_02");
		prcContrNum_H1 = (EZDBStringItem)newItem("prcContrNum_H1");
		xxLinkProt_03 = (EZDBStringItem)newItem("xxLinkProt_03");
		effFromDt_H1 = (EZDBDateItem)newItem("effFromDt_H1");
		effThruDt_H1 = (EZDBDateItem)newItem("effThruDt_H1");
		csmpContrActvFlg_H1 = (EZDBStringItem)newItem("csmpContrActvFlg_H1");
		csmpContrActvFlg_L1 = (EZDBStringItemArray)newItemArray("csmpContrActvFlg_L1");
		xxWfProcStsNm_L1 = (EZDBStringItemArray)newItemArray("xxWfProcStsNm_L1");
		xxModeNm13Txt_H1 = (EZDBStringItem)newItem("xxModeNm13Txt_H1");
		rtlDlrCd_H1 = (EZDBStringItem)newItem("rtlDlrCd_H1");
		rnwCsmpNum_H1 = (EZDBStringItem)newItem("rnwCsmpNum_H1");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		xxDtlCnt_H1 = (EZDBBigDecimalItem)newItem("xxDtlCnt_H1");
		xxDtlCnt_H2 = (EZDBBigDecimalItem)newItem("xxDtlCnt_H2");
		csmpNum_MU = (EZDBStringItem)newItem("csmpNum_MU");
		dlrRefNum_MU = (EZDBStringItem)newItem("dlrRefNum_MU");
		xxLinkProt_04 = (EZDBStringItem)newItem("xxLinkProt_04");
		prcContrNum_MU = (EZDBStringItem)newItem("prcContrNum_MU");
		csmpNumCmntTxt_MU = (EZDBStringItem)newItem("csmpNumCmntTxt_MU");
		effFromDt_MU = (EZDBDateItem)newItem("effFromDt_MU");
		effThruDt_MU = (EZDBDateItem)newItem("effThruDt_MU");
		csmpContrActvFlg_MU = (EZDBStringItem)newItem("csmpContrActvFlg_MU");
		csmpContrActvFlg_L2 = (EZDBStringItemArray)newItemArray("csmpContrActvFlg_L2");
		xxWfProcStsNm_L2 = (EZDBStringItemArray)newItemArray("xxWfProcStsNm_L2");
		cusaRejDt_MU = (EZDBDateItem)newItem("cusaRejDt_MU");
		uplftEquipRatio_MU = (EZDBBigDecimalItem)newItem("uplftEquipRatio_MU");
		uplftAsryRatio_MU = (EZDBBigDecimalItem)newItem("uplftAsryRatio_MU");
		rnwCsmpNum_MU = (EZDBStringItem)newItem("rnwCsmpNum_MU");
		xxChkBox_AL = (EZDBStringItem)newItem("xxChkBox_AL");
		xxMsgTxt = (EZDBStringItem)newItem("xxMsgTxt");
		A = (business.servlet.NMAL7120.NMAL7120_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7120BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7120BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"O", "O", null, "20", "business.servlet.NMAL7120.NMAL7120_OBMsgArray", null, null},
	
	{"P", "P", null, "30", "business.servlet.NMAL7120.NMAL7120_PBMsgArray", null, null},
	
	{"xxPageShowFromNum_10", "xxPageShowFromNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_10", "xxPageShowToNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_10", "xxPageShowOfNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_10", "xxPageShowCurNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_10", "xxPageShowTotNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_01", "xxSortTblNm_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_01", "xxSortItemNm_01", "01", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_01", "xxSortOrdByTxt_01", "01", null, TYPE_HANKAKUEISU, "4", null},
	{"dsAcctNum_H1", "dsAcctNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkProt_01", "xxLinkProt_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
	{"csmpNum_H1", "csmpNum_H1", "H1", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_H1", "dlrRefNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"prcCatgNm_H1", "prcCatgNm_H1", "H1", null, TYPE_HANKAKUEISU, "75", null},
	{"xxLinkProt_02", "xxLinkProt_02", "02", null, TYPE_HANKAKUEISU, "1", null},
	{"prcContrNum_H1", "prcContrNum_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_03", "xxLinkProt_03", "03", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H1", "effThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"csmpContrActvFlg_H1", "csmpContrActvFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"csmpContrActvFlg_L1", "csmpContrActvFlg_L1", "L1", "99", TYPE_HANKAKUEISU, "1", null},
	{"xxWfProcStsNm_L1", "xxWfProcStsNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"xxModeNm13Txt_H1", "xxModeNm13Txt_H1", "H1", null, TYPE_HANKAKUEISU, "13", null},
	{"rtlDlrCd_H1", "rtlDlrCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rnwCsmpNum_H1", "rnwCsmpNum_H1", "H1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxDtlCnt_H1", "xxDtlCnt_H1", "H1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxDtlCnt_H2", "xxDtlCnt_H2", "H2", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"csmpNum_MU", "csmpNum_MU", "MU", null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum_MU", "dlrRefNum_MU", "MU", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLinkProt_04", "xxLinkProt_04", "04", null, TYPE_HANKAKUEISU, "1", null},
	{"prcContrNum_MU", "prcContrNum_MU", "MU", null, TYPE_HANKAKUEISU, "50", null},
	{"csmpNumCmntTxt_MU", "csmpNumCmntTxt_MU", "MU", null, TYPE_HANKAKUEISU, "90", null},
	{"effFromDt_MU", "effFromDt_MU", "MU", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_MU", "effThruDt_MU", "MU", null, TYPE_NENTSUKIHI, "8", null},
	{"csmpContrActvFlg_MU", "csmpContrActvFlg_MU", "MU", null, TYPE_HANKAKUEISU, "1", null},
	{"csmpContrActvFlg_L2", "csmpContrActvFlg_L2", "L2", "99", TYPE_HANKAKUEISU, "1", null},
	{"xxWfProcStsNm_L2", "xxWfProcStsNm_L2", "L2", "99", TYPE_HANKAKUEISU, "30", null},
	{"cusaRejDt_MU", "cusaRejDt_MU", "MU", null, TYPE_NENTSUKIHI, "8", null},
	{"uplftEquipRatio_MU", "uplftEquipRatio_MU", "MU", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"uplftAsryRatio_MU", "uplftAsryRatio_MU", "MU", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"rnwCsmpNum_MU", "rnwCsmpNum_MU", "MU", null, TYPE_HANKAKUEISU, "15", null},
	{"xxChkBox_AL", "xxChkBox_AL", "AL", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxMsgTxt", "xxMsgTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	{"A", "A", null, "200", "business.servlet.NMAL7120.NMAL7120_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//O
		null,	//P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_10
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_10
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_10
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_10
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_10
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_01
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_01
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_01
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H1
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_01
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
        {"CSMP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum_H1
        {"DLR_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_H1
        {"PRC_CATG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_H1
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_02
        {"PRC_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum_H1
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_03
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_H1
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg_H1
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg_L1
        {"XX_WF_PROC_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfProcStsNm_L1
        {"XX_MODE_NM_13_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeNm13Txt_H1
        {"RTL_DLR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlDlrCd_H1
        {"RNW_CSMP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwCsmpNum_H1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_H1
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt_H2
        {"CSMP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum_MU
        {"DLR_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum_MU
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_04
        {"PRC_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum_MU
        {"CSMP_NUM_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNumCmntTxt_MU
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_MU
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_MU
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg_MU
        {"CSMP_CONTR_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpContrActvFlg_L2
        {"XX_WF_PROC_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWfProcStsNm_L2
        {"CUSA_REJ_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cusaRejDt_MU
        {"UPLFT_EQUIP_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftEquipRatio_MU
        {"UPLFT_ASRY_RATIO",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//uplftAsryRatio_MU
        {"RNW_CSMP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwCsmpNum_MU
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
        {"XX_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt
		null,	//A
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
