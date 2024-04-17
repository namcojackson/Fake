//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20191226145107000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3200CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3200 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3200CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** USR_ID*/
	public final EZDCStringItem              usrId;

    /** SRCH_OPT_PK_PS*/
	public final EZDCBigDecimalItem              srchOptPk_PS;

    /** SRCH_OPT_PK_PD*/
	public final EZDCBigDecimalItemArray              srchOptPk_PD;

    /** SRCH_OPT_NM_PD*/
	public final EZDCStringItemArray              srchOptNm_PD;

    /** SRCH_OPT_NM_H1*/
	public final EZDCStringItem              srchOptNm_H1;

    /** TRX_HDR_NUM_H1*/
	public final EZDCStringItem              trxHdrNum_H1;

    /** SO_NUM_H1*/
	public final EZDCStringItem              soNum_H1;

    /** DS_SO_LINE_STS_CD_PS*/
	public final EZDCStringItem              dsSoLineStsCd_PS;

    /** DS_SO_LINE_STS_CD_PD*/
	public final EZDCStringItemArray              dsSoLineStsCd_PD;

    /** DS_SO_LINE_STS_DESC_TXT_PD*/
	public final EZDCStringItemArray              dsSoLineStsDescTxt_PD;

    /** SCE_ORD_TP_CD_PS*/
	public final EZDCStringItem              sceOrdTpCd_PS;

    /** SCE_ORD_TP_CD_PD*/
	public final EZDCStringItemArray              sceOrdTpCd_PD;

    /** SCE_ORD_TP_NM_PD*/
	public final EZDCStringItemArray              sceOrdTpNm_PD;

    /** SHPG_SVC_LVL_CD_PS*/
	public final EZDCStringItem              shpgSvcLvlCd_PS;

    /** SHPG_SVC_LVL_CD_PD*/
	public final EZDCStringItemArray              shpgSvcLvlCd_PD;

    /** SHPG_SVC_LVL_DESC_TXT_PD*/
	public final EZDCStringItemArray              shpgSvcLvlDescTxt_PD;

    /** XX_RTRN_INVTY_LOC_SRCH_TXT_RW*/
	public final EZDCStringItem              xxRtrnInvtyLocSrchTxt_RW;

    /** RTL_WH_NM_H1*/
	public final EZDCStringItem              rtlWhNm_H1;

    /** XX_RTRN_INVTY_LOC_SRCH_TXT_SW*/
	public final EZDCStringItem              xxRtrnInvtyLocSrchTxt_SW;

    /** RTL_SWH_NM_H1*/
	public final EZDCStringItem              rtlSwhNm_H1;

    /** SHIP_TO_CUST_CD_H1*/
	public final EZDCStringItem              shipToCustCd_H1;

    /** DS_ACCT_NM_H1*/
	public final EZDCStringItem              dsAcctNm_H1;

    /** CARR_CD_H1*/
	public final EZDCStringItem              carrCd_H1;

    /** CARR_NM_H1*/
	public final EZDCStringItem              carrNm_H1;

    /** MDSE_CD_H1*/
	public final EZDCStringItem              mdseCd_H1;

    /** MDSE_DESC_SHORT_TXT_H1*/
	public final EZDCStringItem              mdseDescShortTxt_H1;

    /** SVC_CONFIG_MSTR_PK_H1*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_H1;

    /** PRO_NUM_H1*/
	public final EZDCStringItem              proNum_H1;

    /** WMS_DROP_FLG_Y*/
	public final EZDCStringItem              wmsDropFlg_Y;

    /** WMS_DROP_FLG_N*/
	public final EZDCStringItem              wmsDropFlg_N;

    /** XX_TRX_DT_FR*/
	public final EZDCDateItem              xxTrxDt_FR;

    /** XX_TRX_DT_TO*/
	public final EZDCDateItem              xxTrxDt_TO;

    /** RDD_DT_FR*/
	public final EZDCDateItem              rddDt_FR;

    /** RDD_DT_TO*/
	public final EZDCDateItem              rddDt_TO;

    /** XX_TRX_DT_F3*/
	public final EZDCDateItem              xxTrxDt_F3;

    /** XX_TRX_DT_T3*/
	public final EZDCDateItem              xxTrxDt_T3;

    /** RQST_RCV_DT_TXT_CD*/
	public final EZDCStringItemArray              rqstRcvDtTxt_CD;

    /** RQST_RCV_DT_TXT_DI*/
	public final EZDCStringItemArray              rqstRcvDtTxt_DI;

    /** XX_DT_NM_F1*/
	public final EZDCStringItem              xxDtNm_F1;

    /** RQST_RCV_DT_TXT_F1*/
	public final EZDCStringItem              rqstRcvDtTxt_F1;

    /** XX_DT_NM_T1*/
	public final EZDCStringItem              xxDtNm_T1;

    /** RQST_RCV_DT_TXT_T1*/
	public final EZDCStringItem              rqstRcvDtTxt_T1;

    /** XX_DT_NM_F2*/
	public final EZDCStringItem              xxDtNm_F2;

    /** RQST_RCV_DT_TXT_F2*/
	public final EZDCStringItem              rqstRcvDtTxt_F2;

    /** XX_DT_NM_T2*/
	public final EZDCStringItem              xxDtNm_T2;

    /** RQST_RCV_DT_TXT_T2*/
	public final EZDCStringItem              rqstRcvDtTxt_T2;

    /** XX_DT_NM_F3*/
	public final EZDCStringItem              xxDtNm_F3;

    /** RQST_RCV_DT_TXT_F3*/
	public final EZDCStringItem              rqstRcvDtTxt_F3;

    /** XX_DT_NM_T3*/
	public final EZDCStringItem              xxDtNm_T3;

    /** RQST_RCV_DT_TXT_T3*/
	public final EZDCStringItem              rqstRcvDtTxt_T3;

    /** XX_EXST_FLG_BO*/
	public final EZDCStringItem              xxExstFlg_BO;

    /** XX_EXST_FLG_NO*/
	public final EZDCStringItem              xxExstFlg_NO;

    /** XX_DPLY_TAB*/
	public final EZDCStringItem              xxDplyTab;

    /** XX_TAB_PROT_01*/
	public final EZDCStringItem              xxTabProt_01;

    /** XX_TAB_PROT_02*/
	public final EZDCStringItem              xxTabProt_02;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A;

    /** XX_PAGE_SHOW_CUR_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_A;

    /** XX_PAGE_SHOW_TOT_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_A;

    /** A*/
	public final business.blap.NLBL3200.NLBL3200_ACMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_B;

    /** XX_PAGE_SHOW_CUR_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_B;

    /** XX_PAGE_SHOW_TOT_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_B;

    /** B*/
	public final business.blap.NLBL3200.NLBL3200_BCMsgArray              B;

    /** C*/
	public final business.blap.NLBL3200.NLBL3200_CCMsgArray              C;

    /** XX_LINK_ANCR_D1*/
	public final EZDCStringItem              xxLinkAncr_D1;

    /** CARR_CD_D1*/
	public final EZDCStringItem              carrCd_D1;

    /** CARR_NM_D1*/
	public final EZDCStringItem              carrNm_D1;

    /** TOT_FRT_AMT_D1*/
	public final EZDCBigDecimalItem              totFrtAmt_D1;

    /** PRO_NUM_D1*/
	public final EZDCStringItem              proNum_D1;

    /** XX_NUM_EV*/
	public final EZDCBigDecimalItem              xxNum_EV;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_WRN_SKIP_FLG*/
	public final EZDCStringItem              xxWrnSkipFlg;

    /** XX_TBL_NM_P1*/
	public final EZDCStringItem              xxTblNm_P1;

    /** XX_TBL_CD_COL_NM_P1*/
	public final EZDCStringItem              xxTblCdColNm_P1;

    /** XX_TBL_NM_COL_NM_P1*/
	public final EZDCStringItem              xxTblNmColNm_P1;

    /** XX_TBL_SORT_COL_NM_P1*/
	public final EZDCStringItem              xxTblSortColNm_P1;

    /** XX_SCR_NM_P1*/
	public final EZDCStringItem              xxScrNm_P1;

    /** XX_HDR_CD_LB_NM_P1*/
	public final EZDCStringItem              xxHdrCdLbNm_P1;

    /** XX_HDR_NM_LB_NM_P1*/
	public final EZDCStringItem              xxHdrNmLbNm_P1;

    /** XX_DTL_CD_LB_NM_P1*/
	public final EZDCStringItem              xxDtlCdLbNm_P1;

    /** XX_DTL_NM_LB_NM_P1*/
	public final EZDCStringItem              xxDtlNmLbNm_P1;

    /** XX_COND_CD_P1*/
	public final EZDCStringItem              xxCondCd_P1;

    /** XX_COND_NM_P1*/
	public final EZDCStringItem              xxCondNm_P1;

    /** P*/
	public final business.blap.NLBL3200.NLBL3200_PCMsgArray              P;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** S*/
	public final business.blap.NLBL3200.NLBL3200_SCMsgArray              S;

    /** ACTV_FLG_SC*/
	public final EZDCStringItem              actvFlg_SC;

    /** SO_NUM_PR*/
	public final EZDCStringItem              soNum_PR;

    /** SHPG_ORD_RPT_PRINT_RQST_SQ_PR*/
	public final EZDCBigDecimalItem              shpgOrdRptPrintRqstSq_PR;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** XX_MODE_CD_TB*/
	public final EZDCStringItem              xxModeCd_TB;

    /** XX_NUM*/
	public final EZDCBigDecimalItem              xxNum;

    /** T*/
	public final business.blap.NLBL3200.NLBL3200_TCMsgArray              T;


	/**
	 * NLBL3200CMsg is constructor.
	 * The initialization when the instance of NLBL3200CMsg is generated.
	 */
	public NLBL3200CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3200CMsg is constructor.
	 * The initialization when the instance of NLBL3200CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3200CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		usrId = (EZDCStringItem)newItem("usrId");
		srchOptPk_PS = (EZDCBigDecimalItem)newItem("srchOptPk_PS");
		srchOptPk_PD = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_PD");
		srchOptNm_PD = (EZDCStringItemArray)newItemArray("srchOptNm_PD");
		srchOptNm_H1 = (EZDCStringItem)newItem("srchOptNm_H1");
		trxHdrNum_H1 = (EZDCStringItem)newItem("trxHdrNum_H1");
		soNum_H1 = (EZDCStringItem)newItem("soNum_H1");
		dsSoLineStsCd_PS = (EZDCStringItem)newItem("dsSoLineStsCd_PS");
		dsSoLineStsCd_PD = (EZDCStringItemArray)newItemArray("dsSoLineStsCd_PD");
		dsSoLineStsDescTxt_PD = (EZDCStringItemArray)newItemArray("dsSoLineStsDescTxt_PD");
		sceOrdTpCd_PS = (EZDCStringItem)newItem("sceOrdTpCd_PS");
		sceOrdTpCd_PD = (EZDCStringItemArray)newItemArray("sceOrdTpCd_PD");
		sceOrdTpNm_PD = (EZDCStringItemArray)newItemArray("sceOrdTpNm_PD");
		shpgSvcLvlCd_PS = (EZDCStringItem)newItem("shpgSvcLvlCd_PS");
		shpgSvcLvlCd_PD = (EZDCStringItemArray)newItemArray("shpgSvcLvlCd_PD");
		shpgSvcLvlDescTxt_PD = (EZDCStringItemArray)newItemArray("shpgSvcLvlDescTxt_PD");
		xxRtrnInvtyLocSrchTxt_RW = (EZDCStringItem)newItem("xxRtrnInvtyLocSrchTxt_RW");
		rtlWhNm_H1 = (EZDCStringItem)newItem("rtlWhNm_H1");
		xxRtrnInvtyLocSrchTxt_SW = (EZDCStringItem)newItem("xxRtrnInvtyLocSrchTxt_SW");
		rtlSwhNm_H1 = (EZDCStringItem)newItem("rtlSwhNm_H1");
		shipToCustCd_H1 = (EZDCStringItem)newItem("shipToCustCd_H1");
		dsAcctNm_H1 = (EZDCStringItem)newItem("dsAcctNm_H1");
		carrCd_H1 = (EZDCStringItem)newItem("carrCd_H1");
		carrNm_H1 = (EZDCStringItem)newItem("carrNm_H1");
		mdseCd_H1 = (EZDCStringItem)newItem("mdseCd_H1");
		mdseDescShortTxt_H1 = (EZDCStringItem)newItem("mdseDescShortTxt_H1");
		svcConfigMstrPk_H1 = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_H1");
		proNum_H1 = (EZDCStringItem)newItem("proNum_H1");
		wmsDropFlg_Y = (EZDCStringItem)newItem("wmsDropFlg_Y");
		wmsDropFlg_N = (EZDCStringItem)newItem("wmsDropFlg_N");
		xxTrxDt_FR = (EZDCDateItem)newItem("xxTrxDt_FR");
		xxTrxDt_TO = (EZDCDateItem)newItem("xxTrxDt_TO");
		rddDt_FR = (EZDCDateItem)newItem("rddDt_FR");
		rddDt_TO = (EZDCDateItem)newItem("rddDt_TO");
		xxTrxDt_F3 = (EZDCDateItem)newItem("xxTrxDt_F3");
		xxTrxDt_T3 = (EZDCDateItem)newItem("xxTrxDt_T3");
		rqstRcvDtTxt_CD = (EZDCStringItemArray)newItemArray("rqstRcvDtTxt_CD");
		rqstRcvDtTxt_DI = (EZDCStringItemArray)newItemArray("rqstRcvDtTxt_DI");
		xxDtNm_F1 = (EZDCStringItem)newItem("xxDtNm_F1");
		rqstRcvDtTxt_F1 = (EZDCStringItem)newItem("rqstRcvDtTxt_F1");
		xxDtNm_T1 = (EZDCStringItem)newItem("xxDtNm_T1");
		rqstRcvDtTxt_T1 = (EZDCStringItem)newItem("rqstRcvDtTxt_T1");
		xxDtNm_F2 = (EZDCStringItem)newItem("xxDtNm_F2");
		rqstRcvDtTxt_F2 = (EZDCStringItem)newItem("rqstRcvDtTxt_F2");
		xxDtNm_T2 = (EZDCStringItem)newItem("xxDtNm_T2");
		rqstRcvDtTxt_T2 = (EZDCStringItem)newItem("rqstRcvDtTxt_T2");
		xxDtNm_F3 = (EZDCStringItem)newItem("xxDtNm_F3");
		rqstRcvDtTxt_F3 = (EZDCStringItem)newItem("rqstRcvDtTxt_F3");
		xxDtNm_T3 = (EZDCStringItem)newItem("xxDtNm_T3");
		rqstRcvDtTxt_T3 = (EZDCStringItem)newItem("rqstRcvDtTxt_T3");
		xxExstFlg_BO = (EZDCStringItem)newItem("xxExstFlg_BO");
		xxExstFlg_NO = (EZDCStringItem)newItem("xxExstFlg_NO");
		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		xxTabProt_01 = (EZDCStringItem)newItem("xxTabProt_01");
		xxTabProt_02 = (EZDCStringItem)newItem("xxTabProt_02");
		xxPageShowFromNum_A = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxPageShowCurNum_A = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_A");
		xxPageShowTotNum_A = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_A");
		A = (business.blap.NLBL3200.NLBL3200_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum_B = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDCBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_B");
		xxPageShowCurNum_B = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_B");
		xxPageShowTotNum_B = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_B");
		B = (business.blap.NLBL3200.NLBL3200_BCMsgArray)newMsgArray("B");
		C = (business.blap.NLBL3200.NLBL3200_CCMsgArray)newMsgArray("C");
		xxLinkAncr_D1 = (EZDCStringItem)newItem("xxLinkAncr_D1");
		carrCd_D1 = (EZDCStringItem)newItem("carrCd_D1");
		carrNm_D1 = (EZDCStringItem)newItem("carrNm_D1");
		totFrtAmt_D1 = (EZDCBigDecimalItem)newItem("totFrtAmt_D1");
		proNum_D1 = (EZDCStringItem)newItem("proNum_D1");
		xxNum_EV = (EZDCBigDecimalItem)newItem("xxNum_EV");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxWrnSkipFlg = (EZDCStringItem)newItem("xxWrnSkipFlg");
		xxTblNm_P1 = (EZDCStringItem)newItem("xxTblNm_P1");
		xxTblCdColNm_P1 = (EZDCStringItem)newItem("xxTblCdColNm_P1");
		xxTblNmColNm_P1 = (EZDCStringItem)newItem("xxTblNmColNm_P1");
		xxTblSortColNm_P1 = (EZDCStringItem)newItem("xxTblSortColNm_P1");
		xxScrNm_P1 = (EZDCStringItem)newItem("xxScrNm_P1");
		xxHdrCdLbNm_P1 = (EZDCStringItem)newItem("xxHdrCdLbNm_P1");
		xxHdrNmLbNm_P1 = (EZDCStringItem)newItem("xxHdrNmLbNm_P1");
		xxDtlCdLbNm_P1 = (EZDCStringItem)newItem("xxDtlCdLbNm_P1");
		xxDtlNmLbNm_P1 = (EZDCStringItem)newItem("xxDtlNmLbNm_P1");
		xxCondCd_P1 = (EZDCStringItem)newItem("xxCondCd_P1");
		xxCondNm_P1 = (EZDCStringItem)newItem("xxCondNm_P1");
		P = (business.blap.NLBL3200.NLBL3200_PCMsgArray)newMsgArray("P");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		S = (business.blap.NLBL3200.NLBL3200_SCMsgArray)newMsgArray("S");
		actvFlg_SC = (EZDCStringItem)newItem("actvFlg_SC");
		soNum_PR = (EZDCStringItem)newItem("soNum_PR");
		shpgOrdRptPrintRqstSq_PR = (EZDCBigDecimalItem)newItem("shpgOrdRptPrintRqstSq_PR");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		xxModeCd_TB = (EZDCStringItem)newItem("xxModeCd_TB");
		xxNum = (EZDCBigDecimalItem)newItem("xxNum");

		T = (business.blap.NLBL3200.NLBL3200_TCMsgArray)newMsgArray("T");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3200CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3200CMsgArray();
	}

	/**
	 * Definition of Sub class for Schema Item Array(Basic data,Visible Field)
	 */
	private static class NLBL3200CMsgSchemaContents1 {

		/**
		 * Array of Schema Items in Sub class(Basic data)
		 */
		private static final String[][] BASE_CONTENTS = {
		//["key name", "variable", "suffix", "array length", "item type", "digits", "after decimal point degits"] <- This is how to define.
			{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
			{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
			{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
			{"srchOptPk_PS", "srchOptPk_PS", "PS", null, TYPE_SEISU_SYOSU, "28", "0"},
			{"srchOptPk_PD", "srchOptPk_PD", "PD", "99", TYPE_SEISU_SYOSU, "28", "0"},
			{"srchOptNm_PD", "srchOptNm_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
			{"srchOptNm_H1", "srchOptNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
			{"trxHdrNum_H1", "trxHdrNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
			{"soNum_H1", "soNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
			{"dsSoLineStsCd_PS", "dsSoLineStsCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
			{"dsSoLineStsCd_PD", "dsSoLineStsCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
			{"dsSoLineStsDescTxt_PD", "dsSoLineStsDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
			{"sceOrdTpCd_PS", "sceOrdTpCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
			{"sceOrdTpCd_PD", "sceOrdTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
			{"sceOrdTpNm_PD", "sceOrdTpNm_PD", "PD", "99", TYPE_HANKAKUEISU, "30", null},
			{"shpgSvcLvlCd_PS", "shpgSvcLvlCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
			{"shpgSvcLvlCd_PD", "shpgSvcLvlCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
			{"shpgSvcLvlDescTxt_PD", "shpgSvcLvlDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
			{"xxRtrnInvtyLocSrchTxt_RW", "xxRtrnInvtyLocSrchTxt_RW", "RW", null, TYPE_HANKAKUEISU, "1000", null},
			{"rtlWhNm_H1", "rtlWhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxRtrnInvtyLocSrchTxt_SW", "xxRtrnInvtyLocSrchTxt_SW", "SW", null, TYPE_HANKAKUEISU, "1000", null},
			{"rtlSwhNm_H1", "rtlSwhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"shipToCustCd_H1", "shipToCustCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
			{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
			{"carrCd_H1", "carrCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
			{"carrNm_H1", "carrNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
			{"mdseCd_H1", "mdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
			{"mdseDescShortTxt_H1", "mdseDescShortTxt_H1", "H1", null, TYPE_HANKAKUEISU, "250", null},
			{"svcConfigMstrPk_H1", "svcConfigMstrPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
			{"proNum_H1", "proNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
			{"wmsDropFlg_Y", "wmsDropFlg_Y", "Y", null, TYPE_HANKAKUEISU, "1", null},
			{"wmsDropFlg_N", "wmsDropFlg_N", "N", null, TYPE_HANKAKUEISU, "1", null},
			{"xxTrxDt_FR", "xxTrxDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
			{"xxTrxDt_TO", "xxTrxDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
			{"rddDt_FR", "rddDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
			{"rddDt_TO", "rddDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
			{"xxTrxDt_F3", "xxTrxDt_F3", "F3", null, TYPE_NENTSUKIHI, "8", null},
			{"xxTrxDt_T3", "xxTrxDt_T3", "T3", null, TYPE_NENTSUKIHI, "8", null},
			{"rqstRcvDtTxt_CD", "rqstRcvDtTxt_CD", "CD", "2", TYPE_HANKAKUEISU, "10", null},
			{"rqstRcvDtTxt_DI", "rqstRcvDtTxt_DI", "DI", "2", TYPE_HANKAKUEISU, "10", null},
			{"xxDtNm_F1", "xxDtNm_F1", "F1", null, TYPE_HANKAKUEISU, "17", null},
			{"rqstRcvDtTxt_F1", "rqstRcvDtTxt_F1", "F1", null, TYPE_HANKAKUEISU, "10", null},
			{"xxDtNm_T1", "xxDtNm_T1", "T1", null, TYPE_HANKAKUEISU, "17", null},
			{"rqstRcvDtTxt_T1", "rqstRcvDtTxt_T1", "T1", null, TYPE_HANKAKUEISU, "10", null},
			{"xxDtNm_F2", "xxDtNm_F2", "F2", null, TYPE_HANKAKUEISU, "17", null},
			{"rqstRcvDtTxt_F2", "rqstRcvDtTxt_F2", "F2", null, TYPE_HANKAKUEISU, "10", null},
			{"xxDtNm_T2", "xxDtNm_T2", "T2", null, TYPE_HANKAKUEISU, "17", null},
			{"rqstRcvDtTxt_T2", "rqstRcvDtTxt_T2", "T2", null, TYPE_HANKAKUEISU, "10", null},
			{"xxDtNm_F3", "xxDtNm_F3", "F3", null, TYPE_HANKAKUEISU, "17", null},
			{"rqstRcvDtTxt_F3", "rqstRcvDtTxt_F3", "F3", null, TYPE_HANKAKUEISU, "10", null},
			{"xxDtNm_T3", "xxDtNm_T3", "T3", null, TYPE_HANKAKUEISU, "17", null},
			{"rqstRcvDtTxt_T3", "rqstRcvDtTxt_T3", "T3", null, TYPE_HANKAKUEISU, "10", null},
			{"xxExstFlg_BO", "xxExstFlg_BO", "BO", null, TYPE_HANKAKUEISU, "1", null},
			{"xxExstFlg_NO", "xxExstFlg_NO", NO, null, TYPE_HANKAKUEISU, "1", null},
			{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
			{"xxTabProt_01", "xxTabProt_01", "01", null, TYPE_HANKAKUEISU, "1", null},
			{"xxTabProt_02", "xxTabProt_02", "02", null, TYPE_HANKAKUEISU, "1", null},
			{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowCurNum_A", "xxPageShowCurNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowTotNum_A", "xxPageShowTotNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"A", "A", null, "20", "business.blap.NLBL3200.NLBL3200_ACMsgArray", null, null},
			{"xxPageShowFromNum_B", "xxPageShowFromNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowToNum_B", "xxPageShowToNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowOfNum_B", "xxPageShowOfNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowCurNum_B", "xxPageShowCurNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxPageShowTotNum_B", "xxPageShowTotNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"B", "B", null, "20", "business.blap.NLBL3200.NLBL3200_BCMsgArray", null, null},
			{"C", "C", null, "20", "business.blap.NLBL3200.NLBL3200_CCMsgArray", null, null},
			{"xxLinkAncr_D1", "xxLinkAncr_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
			{"carrCd_D1", "carrCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
			{"carrNm_D1", "carrNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
			{"totFrtAmt_D1", "totFrtAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
			{"proNum_D1", "proNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxNum_EV", "xxNum_EV", "EV", null, TYPE_SEISU_SYOSU, "5", "0"},
			{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
			{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
			{"xxTblNm_P1", "xxTblNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxTblCdColNm_P1", "xxTblCdColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxTblNmColNm_P1", "xxTblNmColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxTblSortColNm_P1", "xxTblSortColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxScrNm_P1", "xxScrNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
			{"xxHdrCdLbNm_P1", "xxHdrCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxHdrNmLbNm_P1", "xxHdrNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxDtlCdLbNm_P1", "xxDtlCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxDtlNmLbNm_P1", "xxDtlNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
			{"xxCondCd_P1", "xxCondCd_P1", "P1", null, TYPE_HANKAKUEISU, "20", null},
			{"xxCondNm_P1", "xxCondNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
			{"P", "P", null, "35", "business.blap.NLBL3200.NLBL3200_PCMsgArray", null, null},
			{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
			{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
			{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
			{"S", "S", null, "200", "business.blap.NLBL3200.NLBL3200_SCMsgArray", null, null},
			{"actvFlg_SC", "actvFlg_SC", "SC", null, TYPE_HANKAKUEISU, "1", null},
			{"soNum_PR", "soNum_PR", "PR", null, TYPE_HANKAKUEISU, "8", null},
			{"shpgOrdRptPrintRqstSq_PR", "shpgOrdRptPrintRqstSq_PR", "PR", null, TYPE_SEISU_SYOSU, "28", "0"},
			{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
			{"xxModeCd_TB", "xxModeCd_TB", "TB", null, TYPE_HANKAKUEISU, "2", null},
			{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
		};

		/**
		 * Array of Schema Items in Sub class(Visible Field)
		 */
		private static final String[][] DISP_CONTENTS = {
		// ["message display string","mandotry item", "minimum", "maximum", "I/O type", "yen", "comma", "zero suppress", "plus display flag", "display length", "display length after decimal point digit", "Dividing date", "Year display pattern", "zero pudding for date", "separator for date"] <- This is how to define
	        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
	        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
	        {"USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
	        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_PS
	        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_PD
	        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_PD
	        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_H1
	        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_H1
	        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_H1
	        {"DS_SO_LINE_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSoLineStsCd_PS
	        {"DS_SO_LINE_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSoLineStsCd_PD
	        {"DS_SO_LINE_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSoLineStsDescTxt_PD
	        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_PS
	        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_PD
	        {"SCE_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm_PD
	        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_PS
	        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_PD
	        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_PD
	        {"XX_RTRN_INVTY_LOC_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtrnInvtyLocSrchTxt_RW
	        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H1
	        {"XX_RTRN_INVTY_LOC_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtrnInvtyLocSrchTxt_SW
	        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_H1
	        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_H1
	        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
	        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_H1
	        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_H1
	        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H1
	        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_H1
	        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_H1
	        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_H1
	        {"WMS_DROP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropFlg_Y
	        {"WMS_DROP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsDropFlg_N
	        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_FR
	        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_TO
	        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_FR
	        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_TO
	        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_F3
	        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_T3
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_CD
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_DI
	        {"XX_DT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_F1
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_F1
	        {"XX_DT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_T1
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_T1
	        {"XX_DT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_F2
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_F2
	        {"XX_DT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_T2
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_T2
	        {"XX_DT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_F3
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_F3
	        {"XX_DT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_T3
	        {"RQST_RCV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRcvDtTxt_T3
	        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_BO
	        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_NO
	        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
	        {"XX_TAB_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_01
	        {"XX_TAB_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_02
	        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
	        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
	        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
	        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_A
	        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_A
			null,	//A
	        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
	        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
	        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
	        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_B
	        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_B
			null,	//B
			null,	//C
	        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_D1
	        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_D1
	        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_D1
	        {"TOT_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totFrtAmt_D1
	        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_D1
	        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EV
	        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
	        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
	        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_P1
	        {"XX_TBL_CD_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm_P1
	        {"XX_TBL_NM_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm_P1
	        {"XX_TBL_SORT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_P1
	        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_P1
	        {"XX_HDR_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm_P1
	        {"XX_HDR_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm_P1
	        {"XX_DTL_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm_P1
	        {"XX_DTL_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm_P1
	        {"XX_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondCd_P1
	        {"XX_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondNm_P1
			null,	//P
	        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
	        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
	        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
			null,	//S
	        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_SC
	        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_PR
	        {"SHPG_ORD_RPT_PRINT_RQST_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgOrdRptPrintRqstSq_PR
	        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
	        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_TB
	        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
		};

	}

	private static class NLBL3200CMsgSchemaContents2 {

		/**
		 * Array of Schema Items in Sub class(Basic data)
		 */
		private static final String[][] BASE_CONTENTS = {
		//["key name", "variable", "suffix", "array length", "item type", "digits", "after decimal point degits"] <- This is how to define.
			{"T", "T", null, "99", "business.blap.NLBL3200.NLBL3200_TCMsgArray", null, null},
		};

		/**
		 * Array of Schema Items in Sub class(Visible Field)
		 */
		private static final String[][] DISP_CONTENTS = {
		// ["message display string","mandotry item", "minimum", "maximum", "I/O type", "yen", "comma", "zero suppress", "plus display flag", "display length", "display length after decimal point digit", "Dividing date", "Year display pattern", "zero pudding for date", "separator for date"] <- This is how to define
			null,	//T
		};

	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = new String[101][NLBL3200CMsgSchemaContents1.BASE_CONTENTS[0].length];
	static {
		int arcpStartPoint=0;
	System.arraycopy(NLBL3200CMsgSchemaContents1.BASE_CONTENTS, 0, BASE_CONTENTS, arcpStartPoint, NLBL3200CMsgSchemaContents1.BASE_CONTENTS.length);
	arcpStartPoint = arcpStartPoint + NLBL3200CMsgSchemaContents1.BASE_CONTENTS.length;
	System.arraycopy(NLBL3200CMsgSchemaContents2.BASE_CONTENTS, 0, BASE_CONTENTS, arcpStartPoint, NLBL3200CMsgSchemaContents2.BASE_CONTENTS.length);
	arcpStartPoint = arcpStartPoint + NLBL3200CMsgSchemaContents2.BASE_CONTENTS.length;
	}

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = new String[101][15];
	static {
		int arcpStartPoint=0;
		System.arraycopy(NLBL3200CMsgSchemaContents1.DISP_CONTENTS, 0, DISP_CONTENTS, arcpStartPoint, NLBL3200CMsgSchemaContents1.DISP_CONTENTS.length);
		arcpStartPoint = arcpStartPoint + NLBL3200CMsgSchemaContents1.DISP_CONTENTS.length;
		System.arraycopy(NLBL3200CMsgSchemaContents2.DISP_CONTENTS, 0, DISP_CONTENTS, arcpStartPoint, NLBL3200CMsgSchemaContents2.DISP_CONTENTS.length);
		arcpStartPoint = arcpStartPoint + NLBL3200CMsgSchemaContents2.DISP_CONTENTS.length;
	}

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

