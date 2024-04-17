//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180803111238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2170CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2170 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2170CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_ITEM_50_TXT_P*/
	public final EZDCStringItem              xxScrItem50Txt_P;

    /** DS_IMPT_ORD_PK_P*/
	public final EZDCBigDecimalItem              dsImptOrdPk_P;

    /** P*/
	public final business.blap.NWAL2170.NWAL2170_PCMsgArray              P;

    /** BIZ_ID_FR*/
	public final EZDCStringItem              bizId_FR;

    /** DS_ACCT_NM_P*/
	public final EZDCStringItem              dsAcctNm_P;

    /** DS_ACCT_NUM_P*/
	public final EZDCStringItem              dsAcctNum_P;

    /** SOLD_TO_CUST_LOC_CD_P*/
	public final EZDCStringItem              soldToCustLocCd_P;

    /** BILL_BY_TP_CD_P*/
	public final EZDCStringItem              billByTpCd_P;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** XX_PAGE_CD_L*/
	public final EZDCStringItemArray              xxPageCd_L;

    /** XX_PAGE_NM_L*/
	public final EZDCStringItemArray              xxPageNm_L;

    /** XX_PAGE_CD*/
	public final EZDCStringItem              xxPageCd;

    /** XX_SCR_ITEM_50_TXT*/
	public final EZDCStringItem              xxScrItem50Txt;

    /** REF_CPO_ORD_NUM*/
	public final EZDCStringItem              refCpoOrdNum;

    /** ORD_SRC_REF_NUM*/
	public final EZDCStringItem              ordSrcRefNum;

    /** ORD_HDR_STS_CD*/
	public final EZDCStringItem              ordHdrStsCd;

    /** IMPT_STS_CD*/
	public final EZDCStringItem              imptStsCd;

    /** RNTL_ORD_FLG*/
	public final EZDCStringItem              rntlOrdFlg;

    /** CPO_SVC_PK*/
	public final EZDCBigDecimalItem              cpoSvcPk;

    /** PRC_BASE_DT*/
	public final EZDCDateItem              prcBaseDt;

    /** DS_ORD_CATG_CD*/
	public final EZDCStringItem              dsOrdCatgCd;

    /** DS_ORD_TP_CD*/
	public final EZDCStringItem              dsOrdTpCd;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** EX_MSG_TXT_01*/
	public final EZDCStringItem              exMsgTxt_01;

    /** XX_CELL_IDX*/
	public final EZDCBigDecimalItem              xxCellIdx;

    /** XX_RQST_FLG*/
	public final EZDCStringItem              xxRqstFlg;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** A*/
	public final business.blap.NWAL2170.NWAL2170_ACMsgArray              A;

    /** B*/
	public final business.blap.NWAL2170.NWAL2170_BCMsgArray              B;

    /** PRC_SVC_CONTR_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              prcSvcContrTpDescTxt_L;

    /** PRC_SVC_CONTR_TP_CD_L*/
	public final EZDCStringItemArray              prcSvcContrTpCd_L;

    /** PRC_SVC_PLN_TP_DESC_TXT_L*/
	public final EZDCStringItemArray              prcSvcPlnTpDescTxt_L;

    /** PRC_SVC_PLN_TP_CD_L*/
	public final EZDCStringItemArray              prcSvcPlnTpCd_L;

    /** XX_FLG_NM_L*/
	public final EZDCStringItemArray              xxFlgNm_L;

    /** BILL_WITH_EQUIP_FLG_L*/
	public final EZDCStringItemArray              billWithEquipFlg_L;

    /** DS_CONTR_CATG_DESC_TXT_L*/
	public final EZDCStringItemArray              dsContrCatgDescTxt_L;

    /** DS_CONTR_CATG_CD_L*/
	public final EZDCStringItemArray              dsContrCatgCd_L;

    /** BLLG_CYCLE_DESC_TXT_L*/
	public final EZDCStringItemArray              bllgCycleDescTxt_L;

    /** BLLG_CYCLE_CD_L*/
	public final EZDCStringItemArray              bllgCycleCd_L;

    /** L*/
	public final business.blap.NWAL2170.NWAL2170_LCMsgArray              L;

    /** MTR_READ_METH_DESC_TXT_L*/
	public final EZDCStringItemArray              mtrReadMethDescTxt_L;

    /** MTR_READ_METH_CD_L*/
	public final EZDCStringItemArray              mtrReadMethCd_L;

    /** D*/
	public final business.blap.NWAL2170.NWAL2170_DCMsgArray              D;

    /** MTR_READ_METH_CD_X*/
	public final EZDCStringItem              mtrReadMethCd_X;

    /** CUST_ISS_PO_NUM_X*/
	public final EZDCStringItem              custIssPoNum_X;

    /** CUST_ISS_PO_DT_X*/
	public final EZDCDateItem              custIssPoDt_X;

    /** R*/
	public final business.blap.NWAL2170.NWAL2170_RCMsgArray              R;

    /** S*/
	public final business.blap.NWAL2170.NWAL2170_SCMsgArray              S;

    /** T*/
	public final business.blap.NWAL2170.NWAL2170_TCMsgArray              T;

    /** U*/
	public final business.blap.NWAL2170.NWAL2170_UCMsgArray              U;

    /** F*/
	public final business.blap.NWAL2170.NWAL2170_FCMsgArray              F;

    /** C*/
	public final business.blap.NWAL2170.NWAL2170_CCMsgArray              C;

    /** G*/
	public final business.blap.NWAL2170.NWAL2170_GCMsgArray              G;

    /** H*/
	public final business.blap.NWAL2170.NWAL2170_HCMsgArray              H;

    /** K*/
	public final business.blap.NWAL2170.NWAL2170_KCMsgArray              K;

    /** J*/
	public final business.blap.NWAL2170.NWAL2170_JCMsgArray              J;


	/**
	 * NWAL2170CMsg is constructor.
	 * The initialization when the instance of NWAL2170CMsg is generated.
	 */
	public NWAL2170CMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2170CMsg is constructor.
	 * The initialization when the instance of NWAL2170CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2170CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrItem50Txt_P = (EZDCStringItem)newItem("xxScrItem50Txt_P");
		dsImptOrdPk_P = (EZDCBigDecimalItem)newItem("dsImptOrdPk_P");
		P = (business.blap.NWAL2170.NWAL2170_PCMsgArray)newMsgArray("P");
		bizId_FR = (EZDCStringItem)newItem("bizId_FR");
		dsAcctNm_P = (EZDCStringItem)newItem("dsAcctNm_P");
		dsAcctNum_P = (EZDCStringItem)newItem("dsAcctNum_P");
		soldToCustLocCd_P = (EZDCStringItem)newItem("soldToCustLocCd_P");
		billByTpCd_P = (EZDCStringItem)newItem("billByTpCd_P");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		xxPageCd_L = (EZDCStringItemArray)newItemArray("xxPageCd_L");
		xxPageNm_L = (EZDCStringItemArray)newItemArray("xxPageNm_L");
		xxPageCd = (EZDCStringItem)newItem("xxPageCd");
		xxScrItem50Txt = (EZDCStringItem)newItem("xxScrItem50Txt");
		refCpoOrdNum = (EZDCStringItem)newItem("refCpoOrdNum");
		ordSrcRefNum = (EZDCStringItem)newItem("ordSrcRefNum");
		ordHdrStsCd = (EZDCStringItem)newItem("ordHdrStsCd");
		imptStsCd = (EZDCStringItem)newItem("imptStsCd");
		rntlOrdFlg = (EZDCStringItem)newItem("rntlOrdFlg");
		cpoSvcPk = (EZDCBigDecimalItem)newItem("cpoSvcPk");
		prcBaseDt = (EZDCDateItem)newItem("prcBaseDt");
		dsOrdCatgCd = (EZDCStringItem)newItem("dsOrdCatgCd");
		dsOrdTpCd = (EZDCStringItem)newItem("dsOrdTpCd");
		serNum = (EZDCStringItem)newItem("serNum");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		exMsgTxt_01 = (EZDCStringItem)newItem("exMsgTxt_01");
		xxCellIdx = (EZDCBigDecimalItem)newItem("xxCellIdx");
		xxRqstFlg = (EZDCStringItem)newItem("xxRqstFlg");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		A = (business.blap.NWAL2170.NWAL2170_ACMsgArray)newMsgArray("A");
		B = (business.blap.NWAL2170.NWAL2170_BCMsgArray)newMsgArray("B");
		prcSvcContrTpDescTxt_L = (EZDCStringItemArray)newItemArray("prcSvcContrTpDescTxt_L");
		prcSvcContrTpCd_L = (EZDCStringItemArray)newItemArray("prcSvcContrTpCd_L");
		prcSvcPlnTpDescTxt_L = (EZDCStringItemArray)newItemArray("prcSvcPlnTpDescTxt_L");
		prcSvcPlnTpCd_L = (EZDCStringItemArray)newItemArray("prcSvcPlnTpCd_L");
		xxFlgNm_L = (EZDCStringItemArray)newItemArray("xxFlgNm_L");
		billWithEquipFlg_L = (EZDCStringItemArray)newItemArray("billWithEquipFlg_L");
		dsContrCatgDescTxt_L = (EZDCStringItemArray)newItemArray("dsContrCatgDescTxt_L");
		dsContrCatgCd_L = (EZDCStringItemArray)newItemArray("dsContrCatgCd_L");
		bllgCycleDescTxt_L = (EZDCStringItemArray)newItemArray("bllgCycleDescTxt_L");
		bllgCycleCd_L = (EZDCStringItemArray)newItemArray("bllgCycleCd_L");
		L = (business.blap.NWAL2170.NWAL2170_LCMsgArray)newMsgArray("L");
		mtrReadMethDescTxt_L = (EZDCStringItemArray)newItemArray("mtrReadMethDescTxt_L");
		mtrReadMethCd_L = (EZDCStringItemArray)newItemArray("mtrReadMethCd_L");
		D = (business.blap.NWAL2170.NWAL2170_DCMsgArray)newMsgArray("D");
		mtrReadMethCd_X = (EZDCStringItem)newItem("mtrReadMethCd_X");
		custIssPoNum_X = (EZDCStringItem)newItem("custIssPoNum_X");
		custIssPoDt_X = (EZDCDateItem)newItem("custIssPoDt_X");
		R = (business.blap.NWAL2170.NWAL2170_RCMsgArray)newMsgArray("R");
		S = (business.blap.NWAL2170.NWAL2170_SCMsgArray)newMsgArray("S");
		T = (business.blap.NWAL2170.NWAL2170_TCMsgArray)newMsgArray("T");
		U = (business.blap.NWAL2170.NWAL2170_UCMsgArray)newMsgArray("U");
		F = (business.blap.NWAL2170.NWAL2170_FCMsgArray)newMsgArray("F");
		C = (business.blap.NWAL2170.NWAL2170_CCMsgArray)newMsgArray("C");
		G = (business.blap.NWAL2170.NWAL2170_GCMsgArray)newMsgArray("G");
		H = (business.blap.NWAL2170.NWAL2170_HCMsgArray)newMsgArray("H");
		K = (business.blap.NWAL2170.NWAL2170_KCMsgArray)newMsgArray("K");
		J = (business.blap.NWAL2170.NWAL2170_JCMsgArray)newMsgArray("J");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2170CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2170CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrItem50Txt_P", "xxScrItem50Txt_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"dsImptOrdPk_P", "dsImptOrdPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"P", "P", null, "10", "business.blap.NWAL2170.NWAL2170_PCMsgArray", null, null},
	
	{"bizId_FR", "bizId_FR", "FR", null, TYPE_HANKAKUEISU, "10", null},
	{"dsAcctNm_P", "dsAcctNm_P", "P", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNum_P", "dsAcctNum_P", "P", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd_P", "soldToCustLocCd_P", "P", null, TYPE_HANKAKUEISU, "20", null},
	{"billByTpCd_P", "billByTpCd_P", "P", null, TYPE_HANKAKUEISU, "2", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageCd_L", "xxPageCd_L", "L", "2", TYPE_HANKAKUEISU, "5", null},
	{"xxPageNm_L", "xxPageNm_L", "L", "2", TYPE_HANKAKUEISU, "50", null},
	{"xxPageCd", "xxPageCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"xxScrItem50Txt", "xxScrItem50Txt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"refCpoOrdNum", "refCpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"ordHdrStsCd", "ordHdrStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"imptStsCd", "imptStsCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rntlOrdFlg", "rntlOrdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"cpoSvcPk", "cpoSvcPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcBaseDt", "prcBaseDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"exMsgTxt_01", "exMsgTxt_01", "01", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxRqstFlg", "xxRqstFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.blap.NWAL2170.NWAL2170_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NWAL2170.NWAL2170_BCMsgArray", null, null},
	
	{"prcSvcContrTpDescTxt_L", "prcSvcContrTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcSvcContrTpCd_L", "prcSvcContrTpCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcSvcPlnTpDescTxt_L", "prcSvcPlnTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcSvcPlnTpCd_L", "prcSvcPlnTpCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"xxFlgNm_L", "xxFlgNm_L", "L", "10", TYPE_HANKAKUEISU, "4", null},
	{"billWithEquipFlg_L", "billWithEquipFlg_L", "L", "10", TYPE_HANKAKUEISU, "1", null},
	{"dsContrCatgDescTxt_L", "dsContrCatgDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsContrCatgCd_L", "dsContrCatgCd_L", "L", "99", TYPE_HANKAKUEISU, "3", null},
	{"bllgCycleDescTxt_L", "bllgCycleDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleCd_L", "bllgCycleCd_L", "L", "99", TYPE_HANKAKUEISU, "1", null},
	{"L", "L", null, "99", "business.blap.NWAL2170.NWAL2170_LCMsgArray", null, null},
	
	{"mtrReadMethDescTxt_L", "mtrReadMethDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"mtrReadMethCd_L", "mtrReadMethCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"D", "D", null, "200", "business.blap.NWAL2170.NWAL2170_DCMsgArray", null, null},
	
	{"mtrReadMethCd_X", "mtrReadMethCd_X", "X", null, TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum_X", "custIssPoNum_X", "X", null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt_X", "custIssPoDt_X", "X", null, TYPE_NENTSUKIHI, "8", null},
	{"R", "R", null, "999", "business.blap.NWAL2170.NWAL2170_RCMsgArray", null, null},
	
	{"S", "S", null, "999", "business.blap.NWAL2170.NWAL2170_SCMsgArray", null, null},
	
	{"T", "T", null, "999", "business.blap.NWAL2170.NWAL2170_TCMsgArray", null, null},
	
	{"U", "U", null, "999", "business.blap.NWAL2170.NWAL2170_UCMsgArray", null, null},
	
	{"F", "F", null, "999", "business.blap.NWAL2170.NWAL2170_FCMsgArray", null, null},
	
	{"C", "C", null, "999", "business.blap.NWAL2170.NWAL2170_CCMsgArray", null, null},
	
	{"G", "G", null, "999", "business.blap.NWAL2170.NWAL2170_GCMsgArray", null, null},
	
	{"H", "H", null, "999", "business.blap.NWAL2170.NWAL2170_HCMsgArray", null, null},
	
	{"K", "K", null, "999", "business.blap.NWAL2170.NWAL2170_KCMsgArray", null, null},
	
	{"J", "J", null, "999", "business.blap.NWAL2170.NWAL2170_JCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_P
        {"DS_IMPT_ORD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdPk_P
		null,	//P
        {"BIZ_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizId_FR
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_P
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_P
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_P
        {"BILL_BY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billByTpCd_P
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_PAGE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageCd_L
        {"XX_PAGE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageNm_L
        {"XX_PAGE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageCd
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt
        {"REF_CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//refCpoOrdNum
        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"ORD_HDR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordHdrStsCd
        {"IMPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptStsCd
        {"RNTL_ORD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rntlOrdFlg
        {"CPO_SVC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcPk
        {"PRC_BASE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBaseDt
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"EX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exMsgTxt_01
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
        {"XX_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
		null,	//A
		null,	//B
        {"PRC_SVC_CONTR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpDescTxt_L
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd_L
        {"PRC_SVC_PLN_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpDescTxt_L
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd_L
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_L
        {"BILL_WITH_EQUIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billWithEquipFlg_L
        {"DS_CONTR_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgDescTxt_L
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_L
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_L
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_L
		null,	//L
        {"MTR_READ_METH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethDescTxt_L
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_L
		null,	//D
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_X
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_X
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt_X
		null,	//R
		null,	//S
		null,	//T
		null,	//U
		null,	//F
		null,	//C
		null,	//G
		null,	//H
		null,	//K
		null,	//J
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

