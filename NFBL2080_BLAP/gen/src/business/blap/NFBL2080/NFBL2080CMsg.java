//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200519135115000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2080CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2080CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** VND_CD*/
	public final EZDCStringItem              vndCd;

    /** VND_INV_PROC_STS_CD_P1*/
	public final EZDCStringItem              vndInvProcStsCd_P1;

    /** VND_INV_PROC_STS_CD*/
	public final EZDCStringItemArray              vndInvProcStsCd;

    /** VND_INV_PROC_STS_NM*/
	public final EZDCStringItemArray              vndInvProcStsNm;

    /** INV_AR_PROC_STS_CD_P1*/
	public final EZDCStringItem              invArProcStsCd_P1;

    /** INV_AR_PROC_STS_CD*/
	public final EZDCStringItemArray              invArProcStsCd;

    /** INV_AR_PROC_STS_DESC_TXT*/
	public final EZDCStringItemArray              invArProcStsDescTxt;

    /** VND_INV_NUM*/
	public final EZDCStringItem              vndInvNum;

    /** SO_NUM*/
	public final EZDCStringItem              soNum;

    /** XX_CRAT_DT_S1*/
	public final EZDCDateItem              xxCratDt_S1;

    /** XX_CRAT_DT_S2*/
	public final EZDCDateItem              xxCratDt_S2;

    /** PO_ORD_NUM*/
	public final EZDCStringItem              poOrdNum;

    /** EDI_PO_ORD_NUM*/
	public final EZDCStringItem              ediPoOrdNum;

    /** BAT_ERR_MSG_TXT*/
	public final EZDCStringItem              batErrMsgTxt;

    /** VND_CD_HD*/
	public final EZDCStringItem              vndCd_HD;

    /** VND_INV_PROC_STS_CD_HD*/
	public final EZDCStringItem              vndInvProcStsCd_HD;

    /** INV_AR_PROC_STS_CD_HD*/
	public final EZDCStringItem              invArProcStsCd_HD;

    /** VND_INV_NUM_HD*/
	public final EZDCStringItem              vndInvNum_HD;

    /** SO_NUM_HD*/
	public final EZDCStringItem              soNum_HD;

    /** XX_CRAT_DT_HD*/
	public final EZDCDateItem              xxCratDt_HD;

    /** XX_CRAT_DT_HE*/
	public final EZDCDateItem              xxCratDt_HE;

    /** PO_ORD_NUM_HD*/
	public final EZDCStringItem              poOrdNum_HD;

    /** EDI_PO_ORD_NUM_HD*/
	public final EZDCStringItem              ediPoOrdNum_HD;

    /** BAT_ERR_MSG_TXT_HE*/
	public final EZDCStringItem              batErrMsgTxt_HE;

    /** XX_DPLY_TAB*/
	public final EZDCStringItem              xxDplyTab;

    /** XX_PAGE_SHOW_FROM_NUM_P1*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_P1;

    /** XX_PAGE_SHOW_TO_NUM_P1*/
	public final EZDCBigDecimalItem              xxPageShowToNum_P1;

    /** XX_PAGE_SHOW_OF_NUM_P1*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_P1;

    /** XX_PAGE_SHOW_CUR_NUM_P1*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_P1;

    /** XX_PAGE_SHOW_TOT_NUM_P1*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_P1;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NFBL2080.NFBL2080_ACMsgArray              A;

    /** LOC_NM_H1*/
	public final EZDCStringItem              locNm_H1;

    /** VND_INV_PROC_STS_CD_H1*/
	public final EZDCStringItem              vndInvProcStsCd_H1;

    /** VND_INV_NUM_H1*/
	public final EZDCStringItem              vndInvNum_H1;

    /** VND_INV_BOL_LINE_NUM_H1*/
	public final EZDCStringItem              vndInvBolLineNum_H1;

    /** PO_ORD_NUM_H1*/
	public final EZDCStringItem              poOrdNum_H1;

    /** EDI_PO_ORD_NUM_H1*/
	public final EZDCStringItem              ediPoOrdNum_H1;

    /** SO_NUM_H1*/
	public final EZDCStringItem              soNum_H1;

    /** XX_CRAT_DT_H1*/
	public final EZDCDateItem              xxCratDt_H1;

    /** CUST_ISS_PO_NUM_H1*/
	public final EZDCStringItem              custIssPoNum_H1;

    /** INV_TP_CD_H1*/
	public final EZDCStringItem              invTpCd_H1;

    /** ITRL_INTFC_ID_H1*/
	public final EZDCStringItem              itrlIntfcId_H1;

    /** CR_DR_RSN_CD_H1*/
	public final EZDCStringItem              crDrRsnCd_H1;

    /** XX_INV_CRAT_FLG_H1*/
	public final EZDCStringItem              xxInvCratFlg_H1;

    /** BAT_ERR_MSG_TXT_HP*/
	public final EZDCStringItem              batErrMsgTxt_HP;

    /** BAT_ERR_MSG_TXT_HC*/
	public final EZDCStringItemArray              batErrMsgTxt_HC;

    /** BAT_ERR_MSG_TXT_HD*/
	public final EZDCStringItemArray              batErrMsgTxt_HD;

    /** B*/
	public final business.blap.NFBL2080.NFBL2080_BCMsgArray              B;


	/**
	 * NFBL2080CMsg is constructor.
	 * The initialization when the instance of NFBL2080CMsg is generated.
	 */
	public NFBL2080CMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2080CMsg is constructor.
	 * The initialization when the instance of NFBL2080CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2080CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		vndCd = (EZDCStringItem)newItem("vndCd");
		vndInvProcStsCd_P1 = (EZDCStringItem)newItem("vndInvProcStsCd_P1");
		vndInvProcStsCd = (EZDCStringItemArray)newItemArray("vndInvProcStsCd");
		vndInvProcStsNm = (EZDCStringItemArray)newItemArray("vndInvProcStsNm");
		invArProcStsCd_P1 = (EZDCStringItem)newItem("invArProcStsCd_P1");
		invArProcStsCd = (EZDCStringItemArray)newItemArray("invArProcStsCd");
		invArProcStsDescTxt = (EZDCStringItemArray)newItemArray("invArProcStsDescTxt");
		vndInvNum = (EZDCStringItem)newItem("vndInvNum");
		soNum = (EZDCStringItem)newItem("soNum");
		xxCratDt_S1 = (EZDCDateItem)newItem("xxCratDt_S1");
		xxCratDt_S2 = (EZDCDateItem)newItem("xxCratDt_S2");
		poOrdNum = (EZDCStringItem)newItem("poOrdNum");
		ediPoOrdNum = (EZDCStringItem)newItem("ediPoOrdNum");
		batErrMsgTxt = (EZDCStringItem)newItem("batErrMsgTxt");
		vndCd_HD = (EZDCStringItem)newItem("vndCd_HD");
		vndInvProcStsCd_HD = (EZDCStringItem)newItem("vndInvProcStsCd_HD");
		invArProcStsCd_HD = (EZDCStringItem)newItem("invArProcStsCd_HD");
		vndInvNum_HD = (EZDCStringItem)newItem("vndInvNum_HD");
		soNum_HD = (EZDCStringItem)newItem("soNum_HD");
		xxCratDt_HD = (EZDCDateItem)newItem("xxCratDt_HD");
		xxCratDt_HE = (EZDCDateItem)newItem("xxCratDt_HE");
		poOrdNum_HD = (EZDCStringItem)newItem("poOrdNum_HD");
		ediPoOrdNum_HD = (EZDCStringItem)newItem("ediPoOrdNum_HD");
		batErrMsgTxt_HE = (EZDCStringItem)newItem("batErrMsgTxt_HE");
		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		xxPageShowFromNum_P1 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_P1");
		xxPageShowToNum_P1 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_P1");
		xxPageShowOfNum_P1 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_P1");
		xxPageShowCurNum_P1 = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_P1");
		xxPageShowTotNum_P1 = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_P1");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NFBL2080.NFBL2080_ACMsgArray)newMsgArray("A");
		locNm_H1 = (EZDCStringItem)newItem("locNm_H1");
		vndInvProcStsCd_H1 = (EZDCStringItem)newItem("vndInvProcStsCd_H1");
		vndInvNum_H1 = (EZDCStringItem)newItem("vndInvNum_H1");
		vndInvBolLineNum_H1 = (EZDCStringItem)newItem("vndInvBolLineNum_H1");
		poOrdNum_H1 = (EZDCStringItem)newItem("poOrdNum_H1");
		ediPoOrdNum_H1 = (EZDCStringItem)newItem("ediPoOrdNum_H1");
		soNum_H1 = (EZDCStringItem)newItem("soNum_H1");
		xxCratDt_H1 = (EZDCDateItem)newItem("xxCratDt_H1");
		custIssPoNum_H1 = (EZDCStringItem)newItem("custIssPoNum_H1");
		invTpCd_H1 = (EZDCStringItem)newItem("invTpCd_H1");
		itrlIntfcId_H1 = (EZDCStringItem)newItem("itrlIntfcId_H1");
		crDrRsnCd_H1 = (EZDCStringItem)newItem("crDrRsnCd_H1");
		xxInvCratFlg_H1 = (EZDCStringItem)newItem("xxInvCratFlg_H1");
		batErrMsgTxt_HP = (EZDCStringItem)newItem("batErrMsgTxt_HP");
		batErrMsgTxt_HC = (EZDCStringItemArray)newItemArray("batErrMsgTxt_HC");
		batErrMsgTxt_HD = (EZDCStringItemArray)newItemArray("batErrMsgTxt_HD");
		B = (business.blap.NFBL2080.NFBL2080_BCMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2080CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2080CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"vndCd", "vndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"vndInvProcStsCd_P1", "vndInvProcStsCd_P1", "P1", null, TYPE_HANKAKUEISU, "1", null},
	{"vndInvProcStsCd", "vndInvProcStsCd", null, "99", TYPE_HANKAKUEISU, "1", null},
	{"vndInvProcStsNm", "vndInvProcStsNm", null, "99", TYPE_HANKAKUEISU, "30", null},
	{"invArProcStsCd_P1", "invArProcStsCd_P1", "P1", null, TYPE_HANKAKUEISU, "1", null},
	{"invArProcStsCd", "invArProcStsCd", null, "99", TYPE_HANKAKUEISU, "1", null},
	{"invArProcStsDescTxt", "invArProcStsDescTxt", null, "99", TYPE_HANKAKUEISU, "50", null},
	{"vndInvNum", "vndInvNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxCratDt_S1", "xxCratDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCratDt_S2", "xxCratDt_S2", "S2", null, TYPE_NENTSUKIHI, "8", null},
	{"poOrdNum", "poOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ediPoOrdNum", "ediPoOrdNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"batErrMsgTxt", "batErrMsgTxt", null, null, TYPE_HANKAKUEISU, "400", null},
	{"vndCd_HD", "vndCd_HD", "HD", null, TYPE_HANKAKUEISU, "20", null},
	{"vndInvProcStsCd_HD", "vndInvProcStsCd_HD", "HD", null, TYPE_HANKAKUEISU, "1", null},
	{"invArProcStsCd_HD", "invArProcStsCd_HD", "HD", null, TYPE_HANKAKUEISU, "1", null},
	{"vndInvNum_HD", "vndInvNum_HD", "HD", null, TYPE_HANKAKUEISU, "15", null},
	{"soNum_HD", "soNum_HD", "HD", null, TYPE_HANKAKUEISU, "8", null},
	{"xxCratDt_HD", "xxCratDt_HD", "HD", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCratDt_HE", "xxCratDt_HE", "HE", null, TYPE_NENTSUKIHI, "8", null},
	{"poOrdNum_HD", "poOrdNum_HD", "HD", null, TYPE_HANKAKUEISU, "8", null},
	{"ediPoOrdNum_HD", "ediPoOrdNum_HD", "HD", null, TYPE_HANKAKUEISU, "35", null},
	{"batErrMsgTxt_HE", "batErrMsgTxt_HE", "HE", null, TYPE_HANKAKUEISU, "400", null},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum_P1", "xxPageShowFromNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_P1", "xxPageShowToNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_P1", "xxPageShowOfNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_P1", "xxPageShowCurNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_P1", "xxPageShowTotNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "200", "business.blap.NFBL2080.NFBL2080_ACMsgArray", null, null},
	
	{"locNm_H1", "locNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"vndInvProcStsCd_H1", "vndInvProcStsCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"vndInvNum_H1", "vndInvNum_H1", "H1", null, TYPE_HANKAKUEISU, "15", null},
	{"vndInvBolLineNum_H1", "vndInvBolLineNum_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"poOrdNum_H1", "poOrdNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"ediPoOrdNum_H1", "ediPoOrdNum_H1", "H1", null, TYPE_HANKAKUEISU, "35", null},
	{"soNum_H1", "soNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxCratDt_H1", "xxCratDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"custIssPoNum_H1", "custIssPoNum_H1", "H1", null, TYPE_HANKAKUEISU, "35", null},
	{"invTpCd_H1", "invTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"itrlIntfcId_H1", "itrlIntfcId_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"crDrRsnCd_H1", "crDrRsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxInvCratFlg_H1", "xxInvCratFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"batErrMsgTxt_HP", "batErrMsgTxt_HP", "HP", null, TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_HC", "batErrMsgTxt_HC", "HC", "99", TYPE_HANKAKUEISU, "400", null},
	{"batErrMsgTxt_HD", "batErrMsgTxt_HD", "HD", "99", TYPE_HANKAKUEISU, "400", null},
	{"B", "B", null, "200", "business.blap.NFBL2080.NFBL2080_BCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd
        {"VND_INV_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvProcStsCd_P1
        {"VND_INV_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvProcStsCd
        {"VND_INV_PROC_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvProcStsNm
        {"INV_AR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invArProcStsCd_P1
        {"INV_AR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invArProcStsCd
        {"INV_AR_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invArProcStsDescTxt
        {"VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvNum
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_S1
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_S2
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_HD
        {"VND_INV_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvProcStsCd_HD
        {"INV_AR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invArProcStsCd_HD
        {"VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvNum_HD
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_HD
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_HD
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_HE
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_HD
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_HD
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HE
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_P1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_P1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_P1
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_P1
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_P1
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H1
        {"VND_INV_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvProcStsCd_H1
        {"VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvNum_H1
        {"VND_INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvBolLineNum_H1
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_H1
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_H1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_H1
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_H1
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_H1
        {"INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpCd_H1
        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId_H1
        {"CR_DR_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crDrRsnCd_H1
        {"XX_INV_CRAT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvCratFlg_H1
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HP
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HC
        {"BAT_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batErrMsgTxt_HD
		null,	//B
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

