//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190910154743000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1140SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1140 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1140SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INTFC_ID_0V*/
	public final EZDSStringItem              intfcId_0V;

    /** VND_CD_H1*/
	public final EZDSStringItem              vndCd_H1;

    /** DPLY_VND_NM_H1*/
	public final EZDSStringItem              dplyVndNm_H1;

    /** EDI_PO_ORD_NUM*/
	public final EZDSStringItem              ediPoOrdNum;

    /** EDI_PROC_STS_CD_0C*/
	public final EZDSStringItemArray              ediProcStsCd_0C;

    /** EDI_PROC_STS_NM_0D*/
	public final EZDSStringItemArray              ediProcStsNm_0D;

    /** EDI_PROC_STS_CD_0V*/
	public final EZDSStringItem              ediProcStsCd_0V;

    /** XX_CRAT_DT_H1*/
	public final EZDSDateItem              xxCratDt_H1;

    /** XX_CRAT_DT_H2*/
	public final EZDSDateItem              xxCratDt_H2;

    /** BAT_ERR_MSG_TXT_H1*/
	public final EZDSStringItem              batErrMsgTxt_H1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** XX_DPLY_TAB*/
	public final EZDSStringItem              xxDplyTab;

    /** XX_LINK_ANCR_T0*/
	public final EZDSStringItem              xxLinkAncr_T0;

    /** XX_LINK_ANCR_T1*/
	public final EZDSStringItem              xxLinkAncr_T1;

    /** PO_ACK_HDR_WRK_PK_HT*/
	public final EZDSBigDecimalItem              poAckHdrWrkPk_HT;

    /** ITRL_INTFC_ID*/
	public final EZDSStringItem              itrlIntfcId;

    /** DPLY_VND_NM_HT*/
	public final EZDSStringItem              dplyVndNm_HT;

    /** ACK_EDI_PROC_STS_NM*/
	public final EZDSStringItem              ackEdiProcStsNm;

    /** OPEN_PO_ACK_WRK_FLG*/
	public final EZDSStringItem              openPoAckWrkFlg;

    /** EDI_RCV_DATE_TS*/
	public final EZDSStringItem              ediRcvDateTs;

    /** XX_TS_DSP_19_TXT_HR*/
	public final EZDSStringItem              xxTsDsp19Txt_HR;

    /** EDI_PO_ORD_NUM_HT*/
	public final EZDSStringItem              ediPoOrdNum_HT;

    /** PO_ORD_NUM_HT*/
	public final EZDSStringItem              poOrdNum_HT;

    /** PO_ORD_NUM_SV*/
	public final EZDSStringItem              poOrdNum_SV;

    /** PO_ACK_NUM_HT*/
	public final EZDSStringItem              poAckNum_HT;

    /** VND_CPO_ORD_NUM*/
	public final EZDSStringItem              vndCpoOrdNum;

    /** BAT_ERR_MSG_TXT_HC*/
	public final EZDSStringItemArray              batErrMsgTxt_HC;

    /** BAT_ERR_MSG_TXT_HD*/
	public final EZDSStringItemArray              batErrMsgTxt_HD;

    /** BAT_ERR_MSG_TXT_HV*/
	public final EZDSStringItem              batErrMsgTxt_HV;

    /** VND_CD_HT*/
	public final EZDSStringItem              vndCd_HT;

    /** _EZUpdateDateTime_HT*/
	public final EZDSStringItem              ezUpTime_HT;

    /** _EZUpTimeZone_HT*/
	public final EZDSStringItem              ezUpTimeZone_HT;

    /** XX_CHK_BOX_B0*/
	public final EZDSStringItem              xxChkBox_B0;

    /** A*/
	public final business.blap.NPAL1140.NPAL1140_ASMsgArray              A;

    /** B*/
	public final business.blap.NPAL1140.NPAL1140_BSMsgArray              B;

    /** XX_NUM*/
	public final EZDSBigDecimalItem              xxNum;

    /** XX_TBL_NM*/
	public final EZDSStringItem              xxTblNm;

    /** XX_TBL_CD_COL_NM*/
	public final EZDSStringItem              xxTblCdColNm;

    /** XX_TBL_NM_COL_NM*/
	public final EZDSStringItem              xxTblNmColNm;

    /** XX_TBL_SORT_COL_NM*/
	public final EZDSStringItem              xxTblSortColNm;

    /** XX_SCR_NM*/
	public final EZDSStringItem              xxScrNm;

    /** XX_HDR_CD_LB_NM*/
	public final EZDSStringItem              xxHdrCdLbNm;

    /** XX_HDR_NM_LB_NM*/
	public final EZDSStringItem              xxHdrNmLbNm;

    /** XX_DTL_CD_LB_NM*/
	public final EZDSStringItem              xxDtlCdLbNm;

    /** XX_DTL_NM_LB_NM*/
	public final EZDSStringItem              xxDtlNmLbNm;

    /** XX_ERR_FLG*/
	public final EZDSStringItem              xxErrFlg;

    /** INTFC_ID_SV*/
	public final EZDSStringItem              intfcId_SV;

    /** EDI_PROC_STS_CD_SV*/
	public final EZDSStringItem              ediProcStsCd_SV;

    /** EDI_PO_ORD_NUM_SV*/
	public final EZDSStringItem              ediPoOrdNum_SV;

    /** BAT_ERR_MSG_TXT_SV*/
	public final EZDSStringItem              batErrMsgTxt_SV;

    /** XX_CRAT_DT_S1*/
	public final EZDSDateItem              xxCratDt_S1;

    /** XX_CRAT_DT_S2*/
	public final EZDSDateItem              xxCratDt_S2;


	/**
	 * NPAL1140SMsg is constructor.
	 * The initialization when the instance of NPAL1140SMsg is generated.
	 */
	public NPAL1140SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1140SMsg is constructor.
	 * The initialization when the instance of NPAL1140SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1140SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		intfcId_0V = (EZDSStringItem)newItem("intfcId_0V");
		vndCd_H1 = (EZDSStringItem)newItem("vndCd_H1");
		dplyVndNm_H1 = (EZDSStringItem)newItem("dplyVndNm_H1");
		ediPoOrdNum = (EZDSStringItem)newItem("ediPoOrdNum");
		ediProcStsCd_0C = (EZDSStringItemArray)newItemArray("ediProcStsCd_0C");
		ediProcStsNm_0D = (EZDSStringItemArray)newItemArray("ediProcStsNm_0D");
		ediProcStsCd_0V = (EZDSStringItem)newItem("ediProcStsCd_0V");
		xxCratDt_H1 = (EZDSDateItem)newItem("xxCratDt_H1");
		xxCratDt_H2 = (EZDSDateItem)newItem("xxCratDt_H2");
		batErrMsgTxt_H1 = (EZDSStringItem)newItem("batErrMsgTxt_H1");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxDplyTab = (EZDSStringItem)newItem("xxDplyTab");
		xxLinkAncr_T0 = (EZDSStringItem)newItem("xxLinkAncr_T0");
		xxLinkAncr_T1 = (EZDSStringItem)newItem("xxLinkAncr_T1");
		poAckHdrWrkPk_HT = (EZDSBigDecimalItem)newItem("poAckHdrWrkPk_HT");
		itrlIntfcId = (EZDSStringItem)newItem("itrlIntfcId");
		dplyVndNm_HT = (EZDSStringItem)newItem("dplyVndNm_HT");
		ackEdiProcStsNm = (EZDSStringItem)newItem("ackEdiProcStsNm");
		openPoAckWrkFlg = (EZDSStringItem)newItem("openPoAckWrkFlg");
		ediRcvDateTs = (EZDSStringItem)newItem("ediRcvDateTs");
		xxTsDsp19Txt_HR = (EZDSStringItem)newItem("xxTsDsp19Txt_HR");
		ediPoOrdNum_HT = (EZDSStringItem)newItem("ediPoOrdNum_HT");
		poOrdNum_HT = (EZDSStringItem)newItem("poOrdNum_HT");
		poOrdNum_SV = (EZDSStringItem)newItem("poOrdNum_SV");
		poAckNum_HT = (EZDSStringItem)newItem("poAckNum_HT");
		vndCpoOrdNum = (EZDSStringItem)newItem("vndCpoOrdNum");
		batErrMsgTxt_HC = (EZDSStringItemArray)newItemArray("batErrMsgTxt_HC");
		batErrMsgTxt_HD = (EZDSStringItemArray)newItemArray("batErrMsgTxt_HD");
		batErrMsgTxt_HV = (EZDSStringItem)newItem("batErrMsgTxt_HV");
		vndCd_HT = (EZDSStringItem)newItem("vndCd_HT");
		ezUpTime_HT = (EZDSStringItem)newItem("ezUpTime_HT");
		ezUpTimeZone_HT = (EZDSStringItem)newItem("ezUpTimeZone_HT");
		xxChkBox_B0 = (EZDSStringItem)newItem("xxChkBox_B0");
		A = (business.blap.NPAL1140.NPAL1140_ASMsgArray)newMsgArray("A");
		B = (business.blap.NPAL1140.NPAL1140_BSMsgArray)newMsgArray("B");
		xxNum = (EZDSBigDecimalItem)newItem("xxNum");
		xxTblNm = (EZDSStringItem)newItem("xxTblNm");
		xxTblCdColNm = (EZDSStringItem)newItem("xxTblCdColNm");
		xxTblNmColNm = (EZDSStringItem)newItem("xxTblNmColNm");
		xxTblSortColNm = (EZDSStringItem)newItem("xxTblSortColNm");
		xxScrNm = (EZDSStringItem)newItem("xxScrNm");
		xxHdrCdLbNm = (EZDSStringItem)newItem("xxHdrCdLbNm");
		xxHdrNmLbNm = (EZDSStringItem)newItem("xxHdrNmLbNm");
		xxDtlCdLbNm = (EZDSStringItem)newItem("xxDtlCdLbNm");
		xxDtlNmLbNm = (EZDSStringItem)newItem("xxDtlNmLbNm");
		xxErrFlg = (EZDSStringItem)newItem("xxErrFlg");
		intfcId_SV = (EZDSStringItem)newItem("intfcId_SV");
		ediProcStsCd_SV = (EZDSStringItem)newItem("ediProcStsCd_SV");
		ediPoOrdNum_SV = (EZDSStringItem)newItem("ediPoOrdNum_SV");
		batErrMsgTxt_SV = (EZDSStringItem)newItem("batErrMsgTxt_SV");
		xxCratDt_S1 = (EZDSDateItem)newItem("xxCratDt_S1");
		xxCratDt_S2 = (EZDSDateItem)newItem("xxCratDt_S2");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1140SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1140SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"intfcId_0V", "intfcId_0V", "0V", null, TYPE_HANKAKUEISU, "30", null},
	{"vndCd_H1", "vndCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"dplyVndNm_H1", "dplyVndNm_H1", "H1", null, TYPE_HANKAKUEISU, "320", null},
	{"ediPoOrdNum", "ediPoOrdNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"ediProcStsCd_0C", "ediProcStsCd_0C", "0C", "99", TYPE_HANKAKUEISU, "1", null},
	{"ediProcStsNm_0D", "ediProcStsNm_0D", "0D", "99", TYPE_HANKAKUEISU, "30", null},
	{"ediProcStsCd_0V", "ediProcStsCd_0V", "0V", null, TYPE_HANKAKUEISU, "1", null},
	{"xxCratDt_H1", "xxCratDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCratDt_H2", "xxCratDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"batErrMsgTxt_H1", "batErrMsgTxt_H1", "H1", null, TYPE_HANKAKUEISU, "400", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkAncr_T0", "xxLinkAncr_T0", "T0", null, TYPE_HANKAKUEISU, "30", null},
	{"xxLinkAncr_T1", "xxLinkAncr_T1", "T1", null, TYPE_HANKAKUEISU, "30", null},
	{"poAckHdrWrkPk_HT", "poAckHdrWrkPk_HT", "HT", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"itrlIntfcId", "itrlIntfcId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_HT", "dplyVndNm_HT", "HT", null, TYPE_HANKAKUEISU, "320", null},
	{"ackEdiProcStsNm", "ackEdiProcStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"openPoAckWrkFlg", "openPoAckWrkFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ediRcvDateTs", "ediRcvDateTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxTsDsp19Txt_HR", "xxTsDsp19Txt_HR", "HR", null, TYPE_HANKAKUEISU, "19", null},
	{"ediPoOrdNum_HT", "ediPoOrdNum_HT", "HT", null, TYPE_HANKAKUEISU, "35", null},
	{"poOrdNum_HT", "poOrdNum_HT", "HT", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdNum_SV", "poOrdNum_SV", "SV", null, TYPE_HANKAKUEISU, "8", null},
	{"poAckNum_HT", "poAckNum_HT", "HT", null, TYPE_HANKAKUEISU, "6", null},
	{"vndCpoOrdNum", "vndCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"batErrMsgTxt_HC", "batErrMsgTxt_HC", "HC", "200", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_HD", "batErrMsgTxt_HD", "HD", "200", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_HV", "batErrMsgTxt_HV", "HV", null, TYPE_HANKAKUEISU, "400", null},
	{"vndCd_HT", "vndCd_HT", "HT", null, TYPE_HANKAKUEISU, "20", null},
	{"ezUpTime_HT", "ezUpTime_HT", "HT", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_HT", "ezUpTimeZone_HT", "HT", null, TYPE_HANKAKUEISU, "5", null},
	{"xxChkBox_B0", "xxChkBox_B0", "B0", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "999", "business.blap.NPAL1140.NPAL1140_ASMsgArray", null, null},
	
	{"B", "B", null, "1000", "business.blap.NPAL1140.NPAL1140_BSMsgArray", null, null},
	
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblCdColNm", "xxTblCdColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNmColNm", "xxTblNmColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblSortColNm", "xxTblSortColNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxHdrCdLbNm", "xxHdrCdLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxHdrNmLbNm", "xxHdrNmLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlCdLbNm", "xxDtlCdLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlNmLbNm", "xxDtlNmLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxErrFlg", "xxErrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"intfcId_SV", "intfcId_SV", "SV", null, TYPE_HANKAKUEISU, "30", null},
	{"ediProcStsCd_SV", "ediProcStsCd_SV", "SV", null, TYPE_HANKAKUEISU, "1", null},
	{"ediPoOrdNum_SV", "ediPoOrdNum_SV", "SV", null, TYPE_HANKAKUEISU, "35", null},
	{"batErrMsgTxt_SV", "batErrMsgTxt_SV", "SV", null, TYPE_HANKAKUEISU, "400", null},
	{"xxCratDt_S1", "xxCratDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCratDt_S2", "xxCratDt_S2", "S2", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcId_0V
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_H1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_H1
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum
        {"EDI_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediProcStsCd_0C
        {"EDI_PROC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediProcStsNm_0D
        {"EDI_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediProcStsCd_0V
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_H1
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_H2
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_T0
        {"XX_LINK_ANCR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_T1
        {"PO_ACK_HDR_WRK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poAckHdrWrkPk_HT
        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_HT
        {"ACK_EDI_PROC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ackEdiProcStsNm
        {"OPEN_PO_ACK_WRK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openPoAckWrkFlg
        {"EDI_RCV_DATE_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediRcvDateTs
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_HR
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_HT
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_HT
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_SV
        {"PO_ACK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poAckNum_HT
        {"VND_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCpoOrdNum
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HC
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HD
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HV
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_HT
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_HT
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_HT
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B0
		null,	//A
		null,	//B
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
        {"XX_TBL_CD_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm
        {"XX_TBL_NM_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"XX_HDR_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm
        {"XX_HDR_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm
        {"XX_DTL_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm
        {"XX_DTL_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm
        {"XX_ERR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg
        {"INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcId_SV
        {"EDI_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediProcStsCd_SV
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_SV
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_SV
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_S1
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_S2
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

