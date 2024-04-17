//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170209163749000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC114001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSZC114001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC114001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_CUST_CATG_ID_H*/
	public final EZDPBigDecimalItem              xxCustCatgId_H;

    /** XX_CUST_INCDT_STS_CD_H*/
	public final EZDPStringItem              xxCustIncdtStsCd_H;

    /** XX_CUST_RECUR_FLG*/
	public final EZDPStringItem              xxCustRecurFlg;

    /** BLLG_FLG*/
	public final EZDPStringItem              bllgFlg;

    /** XX_CUST_ORG_ID*/
	public final EZDPBigDecimalItem              xxCustOrgId;

    /** XX_CUST_ACCT_ID*/
	public final EZDPBigDecimalItem              xxCustAcctId;

    /** SELL_TO_LOC_NM*/
	public final EZDPStringItem              sellToLocNm;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** INV_NUM*/
	public final EZDPStringItem              invNum;

    /** DS_CONTR_NUM*/
	public final EZDPStringItem              dsContrNum;

    /** DS_CONTR_NUM_HM*/
	public final EZDPStringItem              dsContrNum_HM;

    /** XX_CUST_ORD_NUM*/
	public final EZDPBigDecimalItem              xxCustOrdNum;

    /** ORD_TP_DESC_TXT*/
	public final EZDPStringItem              ordTpDescTxt;

    /** XX_CUST_PSN_NM*/
	public final EZDPStringItem              xxCustPsnNm;

    /** WORK_TEL_NUM*/
	public final EZDPStringItem              workTelNum;

    /** EML_ADDR*/
	public final EZDPStringItem              emlAddr;

    /** XX_CUST_TP_CD*/
	public final EZDPStringItem              xxCustTpCd;

    /** XX_CUST_CTAC_PSN_NM*/
	public final EZDPStringItem              xxCustCtacPsnNm;

    /** CTAC_PSN_TEL_NUM*/
	public final EZDPStringItem              ctacPsnTelNum;

    /** CTAC_PSN_EML_ADDR*/
	public final EZDPStringItem              ctacPsnEmlAddr;

    /** SVC_MEMO_FLG_H*/
	public final EZDPStringItem              svcMemoFlg_H;

    /** XX_CUST_ATTRB_TXT_H1*/
	public final EZDPStringItem              xxCustAttrbTxt_H1;

    /** XX_CUST_ATTRB_TXT_H2*/
	public final EZDPStringItem              xxCustAttrbTxt_H2;

    /** XX_CUST_ATTRB_TXT_H3*/
	public final EZDPStringItem              xxCustAttrbTxt_H3;

    /** XX_CUST_ATTRB_TXT_H4*/
	public final EZDPStringItem              xxCustAttrbTxt_H4;

    /** XX_CUST_ATTRB_TXT_H5*/
	public final EZDPStringItem              xxCustAttrbTxt_H5;

    /** XX_CUST_ATTRB_TXT_H6*/
	public final EZDPStringItem              xxCustAttrbTxt_H6;

    /** XX_CUST_ATTRB_TXT_H7*/
	public final EZDPStringItem              xxCustAttrbTxt_H7;

    /** XX_CUST_ATTRB_TXT_H8*/
	public final EZDPStringItem              xxCustAttrbTxt_H8;

    /** XX_CUST_ATTRB_TXT_H9*/
	public final EZDPStringItem              xxCustAttrbTxt_H9;

    /** XX_CUST_ATTRB_TXT_HA*/
	public final EZDPStringItem              xxCustAttrbTxt_HA;

    /** XX_CUST_ATTRB_TXT_HB*/
	public final EZDPStringItem              xxCustAttrbTxt_HB;

    /** XX_CUST_ATTRB_TXT_HC*/
	public final EZDPStringItem              xxCustAttrbTxt_HC;

    /** XX_CUST_ATTRB_TXT_HD*/
	public final EZDPStringItem              xxCustAttrbTxt_HD;

    /** XX_CUST_ATTRB_TXT_HE*/
	public final EZDPStringItem              xxCustAttrbTxt_HE;

    /** XX_CUST_ATTRB_TXT_HF*/
	public final EZDPStringItem              xxCustAttrbTxt_HF;

    /** XX_CUST_OWNR_ROLE_ID*/
	public final EZDPStringItem              xxCustOwnrRoleId;

    /** XX_CUST_OWNR_USR_ID*/
	public final EZDPStringItem              xxCustOwnrUsrId;

    /** XX_CUST_OWNR_DEPT_CD*/
	public final EZDPStringItem              xxCustOwnrDeptCd;

    /** CRAT_BY_USR_ID_H*/
	public final EZDPStringItem              cratByUsrId_H;

    /** XX_CUST_CRAT_BY_ROLE_ID*/
	public final EZDPStringItem              xxCustCratByRoleId;

    /** CRAT_BY_USR_ID_HR*/
	public final EZDPStringItem              cratByUsrId_HR;

    /** XX_CUST_CRAT_BY_DEPT_CD*/
	public final EZDPStringItem              xxCustCratByDeptCd;

    /** XX_CUST_CATG_ID_L*/
	public final EZDPBigDecimalItem              xxCustCatgId_L;

    /** XX_CUST_INCDT_STS_CD_L*/
	public final EZDPStringItem              xxCustIncdtStsCd_L;

    /** XX_CUST_SEVTY_CD*/
	public final EZDPStringItem              xxCustSevtyCd;

    /** XX_CUST_RSN_CD*/
	public final EZDPStringItem              xxCustRsnCd;

    /** XX_CUST_RSN_TXT*/
	public final EZDPStringItem              xxCustRsnTxt;

    /** SVC_MEMO_FLG_L*/
	public final EZDPStringItem              svcMemoFlg_L;

    /** XX_CUST_ATTRB_TXT_L1*/
	public final EZDPStringItem              xxCustAttrbTxt_L1;

    /** XX_CUST_ATTRB_TXT_L2*/
	public final EZDPStringItem              xxCustAttrbTxt_L2;

    /** XX_CUST_ATTRB_TXT_L3*/
	public final EZDPStringItem              xxCustAttrbTxt_L3;

    /** XX_CUST_ATTRB_TXT_L4*/
	public final EZDPStringItem              xxCustAttrbTxt_L4;

    /** XX_CUST_ATTRB_TXT_L5*/
	public final EZDPStringItem              xxCustAttrbTxt_L5;

    /** CRAT_BY_USR_ID_L*/
	public final EZDPStringItem              cratByUsrId_L;

    /** SVC_CMNT_TXT*/
	public final EZDPStringItem              svcCmntTxt;

    /** XX_CUST_SEND_EML_FLG*/
	public final EZDPStringItem              xxCustSendEmlFlg;

    /** CUST_INCDT_ID*/
	public final EZDPStringItem              custIncdtId;

    /** xxMsgIdList*/
	public final business.parts.NSZC114001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC114001PMsg is constructor.
	 * The initialization when the instance of NSZC114001PMsg is generated.
	 */
	public NSZC114001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC114001PMsg is constructor.
	 * The initialization when the instance of NSZC114001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC114001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxCustCatgId_H = (EZDPBigDecimalItem)newItem("xxCustCatgId_H");
		xxCustIncdtStsCd_H = (EZDPStringItem)newItem("xxCustIncdtStsCd_H");
		xxCustRecurFlg = (EZDPStringItem)newItem("xxCustRecurFlg");
		bllgFlg = (EZDPStringItem)newItem("bllgFlg");
		xxCustOrgId = (EZDPBigDecimalItem)newItem("xxCustOrgId");
		xxCustAcctId = (EZDPBigDecimalItem)newItem("xxCustAcctId");
		sellToLocNm = (EZDPStringItem)newItem("sellToLocNm");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		invNum = (EZDPStringItem)newItem("invNum");
		dsContrNum = (EZDPStringItem)newItem("dsContrNum");
		dsContrNum_HM = (EZDPStringItem)newItem("dsContrNum_HM");
		xxCustOrdNum = (EZDPBigDecimalItem)newItem("xxCustOrdNum");
		ordTpDescTxt = (EZDPStringItem)newItem("ordTpDescTxt");
		xxCustPsnNm = (EZDPStringItem)newItem("xxCustPsnNm");
		workTelNum = (EZDPStringItem)newItem("workTelNum");
		emlAddr = (EZDPStringItem)newItem("emlAddr");
		xxCustTpCd = (EZDPStringItem)newItem("xxCustTpCd");
		xxCustCtacPsnNm = (EZDPStringItem)newItem("xxCustCtacPsnNm");
		ctacPsnTelNum = (EZDPStringItem)newItem("ctacPsnTelNum");
		ctacPsnEmlAddr = (EZDPStringItem)newItem("ctacPsnEmlAddr");
		svcMemoFlg_H = (EZDPStringItem)newItem("svcMemoFlg_H");
		xxCustAttrbTxt_H1 = (EZDPStringItem)newItem("xxCustAttrbTxt_H1");
		xxCustAttrbTxt_H2 = (EZDPStringItem)newItem("xxCustAttrbTxt_H2");
		xxCustAttrbTxt_H3 = (EZDPStringItem)newItem("xxCustAttrbTxt_H3");
		xxCustAttrbTxt_H4 = (EZDPStringItem)newItem("xxCustAttrbTxt_H4");
		xxCustAttrbTxt_H5 = (EZDPStringItem)newItem("xxCustAttrbTxt_H5");
		xxCustAttrbTxt_H6 = (EZDPStringItem)newItem("xxCustAttrbTxt_H6");
		xxCustAttrbTxt_H7 = (EZDPStringItem)newItem("xxCustAttrbTxt_H7");
		xxCustAttrbTxt_H8 = (EZDPStringItem)newItem("xxCustAttrbTxt_H8");
		xxCustAttrbTxt_H9 = (EZDPStringItem)newItem("xxCustAttrbTxt_H9");
		xxCustAttrbTxt_HA = (EZDPStringItem)newItem("xxCustAttrbTxt_HA");
		xxCustAttrbTxt_HB = (EZDPStringItem)newItem("xxCustAttrbTxt_HB");
		xxCustAttrbTxt_HC = (EZDPStringItem)newItem("xxCustAttrbTxt_HC");
		xxCustAttrbTxt_HD = (EZDPStringItem)newItem("xxCustAttrbTxt_HD");
		xxCustAttrbTxt_HE = (EZDPStringItem)newItem("xxCustAttrbTxt_HE");
		xxCustAttrbTxt_HF = (EZDPStringItem)newItem("xxCustAttrbTxt_HF");
		xxCustOwnrRoleId = (EZDPStringItem)newItem("xxCustOwnrRoleId");
		xxCustOwnrUsrId = (EZDPStringItem)newItem("xxCustOwnrUsrId");
		xxCustOwnrDeptCd = (EZDPStringItem)newItem("xxCustOwnrDeptCd");
		cratByUsrId_H = (EZDPStringItem)newItem("cratByUsrId_H");
		xxCustCratByRoleId = (EZDPStringItem)newItem("xxCustCratByRoleId");
		cratByUsrId_HR = (EZDPStringItem)newItem("cratByUsrId_HR");
		xxCustCratByDeptCd = (EZDPStringItem)newItem("xxCustCratByDeptCd");
		xxCustCatgId_L = (EZDPBigDecimalItem)newItem("xxCustCatgId_L");
		xxCustIncdtStsCd_L = (EZDPStringItem)newItem("xxCustIncdtStsCd_L");
		xxCustSevtyCd = (EZDPStringItem)newItem("xxCustSevtyCd");
		xxCustRsnCd = (EZDPStringItem)newItem("xxCustRsnCd");
		xxCustRsnTxt = (EZDPStringItem)newItem("xxCustRsnTxt");
		svcMemoFlg_L = (EZDPStringItem)newItem("svcMemoFlg_L");
		xxCustAttrbTxt_L1 = (EZDPStringItem)newItem("xxCustAttrbTxt_L1");
		xxCustAttrbTxt_L2 = (EZDPStringItem)newItem("xxCustAttrbTxt_L2");
		xxCustAttrbTxt_L3 = (EZDPStringItem)newItem("xxCustAttrbTxt_L3");
		xxCustAttrbTxt_L4 = (EZDPStringItem)newItem("xxCustAttrbTxt_L4");
		xxCustAttrbTxt_L5 = (EZDPStringItem)newItem("xxCustAttrbTxt_L5");
		cratByUsrId_L = (EZDPStringItem)newItem("cratByUsrId_L");
		svcCmntTxt = (EZDPStringItem)newItem("svcCmntTxt");
		xxCustSendEmlFlg = (EZDPStringItem)newItem("xxCustSendEmlFlg");
		custIncdtId = (EZDPStringItem)newItem("custIncdtId");
		xxMsgIdList = (business.parts.NSZC114001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC114001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC114001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxCustCatgId_H", "xxCustCatgId_H", "H", null, TYPE_SEISU_SYOSU, "38", "0"},
	{"xxCustIncdtStsCd_H", "xxCustIncdtStsCd_H", "H", null, TYPE_HANKAKUEISU, "90", null},
	{"xxCustRecurFlg", "xxCustRecurFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"bllgFlg", "bllgFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxCustOrgId", "xxCustOrgId", null, null, TYPE_SEISU_SYOSU, "38", "0"},
	{"xxCustAcctId", "xxCustAcctId", null, null, TYPE_SEISU_SYOSU, "38", "0"},
	{"sellToLocNm", "sellToLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrNum_HM", "dsContrNum_HM", "HM", null, TYPE_HANKAKUEISU, "30", null},
	{"xxCustOrdNum", "xxCustOrdNum", null, null, TYPE_SEISU_SYOSU, "38", "0"},
	{"ordTpDescTxt", "ordTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxCustPsnNm", "xxCustPsnNm", null, null, TYPE_HANKAKUEISU, "450", null},
	{"workTelNum", "workTelNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"emlAddr", "emlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	{"xxCustTpCd", "xxCustTpCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxCustCtacPsnNm", "xxCustCtacPsnNm", null, null, TYPE_HANKAKUEISU, "450", null},
	{"ctacPsnTelNum", "ctacPsnTelNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnEmlAddr", "ctacPsnEmlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	{"svcMemoFlg_H", "svcMemoFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"xxCustAttrbTxt_H1", "xxCustAttrbTxt_H1", "H1", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H2", "xxCustAttrbTxt_H2", "H2", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H3", "xxCustAttrbTxt_H3", "H3", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H4", "xxCustAttrbTxt_H4", "H4", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H5", "xxCustAttrbTxt_H5", "H5", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H6", "xxCustAttrbTxt_H6", "H6", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H7", "xxCustAttrbTxt_H7", "H7", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H8", "xxCustAttrbTxt_H8", "H8", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_H9", "xxCustAttrbTxt_H9", "H9", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_HA", "xxCustAttrbTxt_HA", "HA", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_HB", "xxCustAttrbTxt_HB", "HB", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_HC", "xxCustAttrbTxt_HC", "HC", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_HD", "xxCustAttrbTxt_HD", "HD", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_HE", "xxCustAttrbTxt_HE", "HE", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_HF", "xxCustAttrbTxt_HF", "HF", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustOwnrRoleId", "xxCustOwnrRoleId", null, null, TYPE_HANKAKUEISU, "300", null},
	{"xxCustOwnrUsrId", "xxCustOwnrUsrId", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxCustOwnrDeptCd", "xxCustOwnrDeptCd", null, null, TYPE_HANKAKUEISU, "300", null},
	{"cratByUsrId_H", "cratByUsrId_H", "H", null, TYPE_HANKAKUEISU, "16", null},
	{"xxCustCratByRoleId", "xxCustCratByRoleId", null, null, TYPE_HANKAKUEISU, "300", null},
	{"cratByUsrId_HR", "cratByUsrId_HR", "HR", null, TYPE_HANKAKUEISU, "16", null},
	{"xxCustCratByDeptCd", "xxCustCratByDeptCd", null, null, TYPE_HANKAKUEISU, "300", null},
	{"xxCustCatgId_L", "xxCustCatgId_L", "L", null, TYPE_SEISU_SYOSU, "38", "0"},
	{"xxCustIncdtStsCd_L", "xxCustIncdtStsCd_L", "L", null, TYPE_HANKAKUEISU, "90", null},
	{"xxCustSevtyCd", "xxCustSevtyCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxCustRsnCd", "xxCustRsnCd", null, null, TYPE_HANKAKUEISU, "250", null},
	{"xxCustRsnTxt", "xxCustRsnTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"svcMemoFlg_L", "svcMemoFlg_L", "L", null, TYPE_HANKAKUEISU, "1", null},
	{"xxCustAttrbTxt_L1", "xxCustAttrbTxt_L1", "L1", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_L2", "xxCustAttrbTxt_L2", "L2", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_L3", "xxCustAttrbTxt_L3", "L3", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_L4", "xxCustAttrbTxt_L4", "L4", null, TYPE_HANKAKUEISU, "750", null},
	{"xxCustAttrbTxt_L5", "xxCustAttrbTxt_L5", "L5", null, TYPE_HANKAKUEISU, "750", null},
	{"cratByUsrId_L", "cratByUsrId_L", "L", null, TYPE_HANKAKUEISU, "16", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxCustSendEmlFlg", "xxCustSendEmlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"custIncdtId", "custIncdtId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC114001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_CUST_CATG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCatgId_H
        {"XX_CUST_INCDT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustIncdtStsCd_H
        {"XX_CUST_RECUR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustRecurFlg
        {"BLLG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFlg
        {"XX_CUST_ORG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustOrgId
        {"XX_CUST_ACCT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAcctId
        {"SELL_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToLocNm
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_HM
        {"XX_CUST_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustOrdNum
        {"ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordTpDescTxt
        {"XX_CUST_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustPsnNm
        {"WORK_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//workTelNum
        {"EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//emlAddr
        {"XX_CUST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustTpCd
        {"XX_CUST_CTAC_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCtacPsnNm
        {"CTAC_PSN_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTelNum
        {"CTAC_PSN_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr
        {"SVC_MEMO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoFlg_H
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H1
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H2
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H3
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H4
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H5
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H6
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H7
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H8
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_H9
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_HA
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_HB
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_HC
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_HD
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_HE
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_HF
        {"XX_CUST_OWNR_ROLE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustOwnrRoleId
        {"XX_CUST_OWNR_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustOwnrUsrId
        {"XX_CUST_OWNR_DEPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustOwnrDeptCd
        {"CRAT_BY_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratByUsrId_H
        {"XX_CUST_CRAT_BY_ROLE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCratByRoleId
        {"CRAT_BY_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratByUsrId_HR
        {"XX_CUST_CRAT_BY_DEPT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCratByDeptCd
        {"XX_CUST_CATG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCatgId_L
        {"XX_CUST_INCDT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustIncdtStsCd_L
        {"XX_CUST_SEVTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustSevtyCd
        {"XX_CUST_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustRsnCd
        {"XX_CUST_RSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustRsnTxt
        {"SVC_MEMO_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoFlg_L
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_L1
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_L2
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_L3
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_L4
        {"XX_CUST_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustAttrbTxt_L5
        {"CRAT_BY_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratByUsrId_L
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"XX_CUST_SEND_EML_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustSendEmlFlg
        {"CUST_INCDT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtId
		null,	//xxMsgIdList
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
