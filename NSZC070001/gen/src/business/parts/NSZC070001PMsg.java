//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190830084322000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC070001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC070001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC070001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** USR_ID*/
	public final EZDPStringItem              usrId;

    /** FSR_NUM*/
	public final EZDPStringItem              fsrNum;

    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** SVC_LBOR_TP_CD*/
	public final EZDPStringItem              svcLborTpCd;

    /** SVC_PBLM_TP_CD*/
	public final EZDPStringItem              svcPblmTpCd;

    /** SVC_PBLM_LOC_TP_CD*/
	public final EZDPStringItem              svcPblmLocTpCd;

    /** SVC_PBLM_RSN_TP_CD*/
	public final EZDPStringItem              svcPblmRsnTpCd;

    /** SVC_PBLM_CRCT_TP_CD*/
	public final EZDPStringItem              svcPblmCrctTpCd;

    /** IWR_STS_CD*/
	public final EZDPStringItem              iwrStsCd;

    /** MACH_DOWN_FLG*/
	public final EZDPStringItem              machDownFlg;

    /** CR_CARD_CUST_REF_NUM*/
	public final EZDPStringItem              crCardCustRefNum;

    /** CR_CARD_AUTH_REF_NUM*/
	public final EZDPStringItem              crCardAuthRefNum;

    /** CR_CARD_LAST_DIGIT_NUM*/
	public final EZDPStringItem              crCardLastDigitNum;

    /** CR_CARD_EXPR_YR_MTH*/
	public final EZDPDateItem              crCardExprYrMth;

    /** CR_CARD_HLD_NM*/
	public final EZDPStringItem              crCardHldNm;

    /** CR_CARD_AUTH_AMT*/
	public final EZDPBigDecimalItem              crCardAuthAmt;

    /** CR_CARD_TP_CD*/
	public final EZDPStringItem              crCardTpCd;

    /** CUST_PO_NUM*/
	public final EZDPStringItem              custPoNum;

    /** CUST_PO_DT*/
	public final EZDPDateItem              custPoDt;

    /** PO_VER_FLG*/
	public final EZDPStringItem              poVerFlg;

    /** ITT_NUM*/
	public final EZDPStringItem              ittNum;

    /** xxLaborList*/
	public final business.parts.NSZC070001_xxLaborListPMsgArray              xxLaborList;

    /** xxPartsUsageList*/
	public final business.parts.NSZC070001_xxPartsUsageListPMsgArray              xxPartsUsageList;

    /** xxExpenseList*/
	public final business.parts.NSZC070001_xxExpenseListPMsgArray              xxExpenseList;

    /** xxChargeableList*/
	public final business.parts.NSZC070001_xxChargeableListPMsgArray              xxChargeableList;

    /** xxFsrIstlChkList*/
	public final business.parts.NSZC070001_xxFsrIstlChkListPMsgArray              xxFsrIstlChkList;

    /** svcMemoList*/
	public final business.parts.NSZC070001_svcMemoListPMsgArray              svcMemoList;

    /** ISTL_CPLT_FLG*/
	public final EZDPStringItem              istlCpltFlg;

    /** xxMsgIdList*/
	public final business.parts.NSZC070001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC070001PMsg is constructor.
	 * The initialization when the instance of NSZC070001PMsg is generated.
	 */
	public NSZC070001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC070001PMsg is constructor.
	 * The initialization when the instance of NSZC070001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC070001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		usrId = (EZDPStringItem)newItem("usrId");
		fsrNum = (EZDPStringItem)newItem("fsrNum");
		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		svcLborTpCd = (EZDPStringItem)newItem("svcLborTpCd");
		svcPblmTpCd = (EZDPStringItem)newItem("svcPblmTpCd");
		svcPblmLocTpCd = (EZDPStringItem)newItem("svcPblmLocTpCd");
		svcPblmRsnTpCd = (EZDPStringItem)newItem("svcPblmRsnTpCd");
		svcPblmCrctTpCd = (EZDPStringItem)newItem("svcPblmCrctTpCd");
		iwrStsCd = (EZDPStringItem)newItem("iwrStsCd");
		machDownFlg = (EZDPStringItem)newItem("machDownFlg");
		crCardCustRefNum = (EZDPStringItem)newItem("crCardCustRefNum");
		crCardAuthRefNum = (EZDPStringItem)newItem("crCardAuthRefNum");
		crCardLastDigitNum = (EZDPStringItem)newItem("crCardLastDigitNum");
		crCardExprYrMth = (EZDPDateItem)newItem("crCardExprYrMth");
		crCardHldNm = (EZDPStringItem)newItem("crCardHldNm");
		crCardAuthAmt = (EZDPBigDecimalItem)newItem("crCardAuthAmt");
		crCardTpCd = (EZDPStringItem)newItem("crCardTpCd");
		custPoNum = (EZDPStringItem)newItem("custPoNum");
		custPoDt = (EZDPDateItem)newItem("custPoDt");
		poVerFlg = (EZDPStringItem)newItem("poVerFlg");
		ittNum = (EZDPStringItem)newItem("ittNum");
		xxLaborList = (business.parts.NSZC070001_xxLaborListPMsgArray)newMsgArray("xxLaborList");
		xxPartsUsageList = (business.parts.NSZC070001_xxPartsUsageListPMsgArray)newMsgArray("xxPartsUsageList");
		xxExpenseList = (business.parts.NSZC070001_xxExpenseListPMsgArray)newMsgArray("xxExpenseList");
		xxChargeableList = (business.parts.NSZC070001_xxChargeableListPMsgArray)newMsgArray("xxChargeableList");
		xxFsrIstlChkList = (business.parts.NSZC070001_xxFsrIstlChkListPMsgArray)newMsgArray("xxFsrIstlChkList");
		svcMemoList = (business.parts.NSZC070001_svcMemoListPMsgArray)newMsgArray("svcMemoList");
		istlCpltFlg = (EZDPStringItem)newItem("istlCpltFlg");
		xxMsgIdList = (business.parts.NSZC070001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC070001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC070001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"usrId", "usrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcLborTpCd", "svcLborTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"svcPblmTpCd", "svcPblmTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcPblmLocTpCd", "svcPblmLocTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcPblmRsnTpCd", "svcPblmRsnTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcPblmCrctTpCd", "svcPblmCrctTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"iwrStsCd", "iwrStsCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"machDownFlg", "machDownFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"crCardCustRefNum", "crCardCustRefNum", null, null, TYPE_HANKAKUEISU, "40", null},
	{"crCardAuthRefNum", "crCardAuthRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"crCardLastDigitNum", "crCardLastDigitNum", null, null, TYPE_HANKAKUEISU, "4", null},
	{"crCardExprYrMth", "crCardExprYrMth", null, null, TYPE_NENTSUKI, "6", null},
	{"crCardHldNm", "crCardHldNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"crCardAuthAmt", "crCardAuthAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crCardTpCd", "crCardTpCd", null, null, TYPE_HANKAKUEISU, "5", null},
	{"custPoNum", "custPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custPoDt", "custPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"poVerFlg", "poVerFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ittNum", "ittNum", null, null, TYPE_HANKAKUEISU, "25", null},
	{"xxLaborList", "xxLaborList", null, "1000", "business.parts.NSZC070001_xxLaborListPMsgArray", null, null},
	
	{"xxPartsUsageList", "xxPartsUsageList", null, "100", "business.parts.NSZC070001_xxPartsUsageListPMsgArray", null, null},
	
	{"xxExpenseList", "xxExpenseList", null, "100", "business.parts.NSZC070001_xxExpenseListPMsgArray", null, null},
	
	{"xxChargeableList", "xxChargeableList", null, "100", "business.parts.NSZC070001_xxChargeableListPMsgArray", null, null},
	
	{"xxFsrIstlChkList", "xxFsrIstlChkList", null, "2000", "business.parts.NSZC070001_xxFsrIstlChkListPMsgArray", null, null},
	
	{"svcMemoList", "svcMemoList", null, "100", "business.parts.NSZC070001_svcMemoListPMsgArray", null, null},
	
	{"istlCpltFlg", "istlCpltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC070001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usrId
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SVC_LBOR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLborTpCd
        {"SVC_PBLM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpCd
        {"SVC_PBLM_LOC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmLocTpCd
        {"SVC_PBLM_RSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmRsnTpCd
        {"SVC_PBLM_CRCT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmCrctTpCd
        {"IWR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//iwrStsCd
        {"MACH_DOWN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//machDownFlg
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum
        {"CR_CARD_AUTH_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardAuthRefNum
        {"CR_CARD_LAST_DIGIT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardLastDigitNum
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardExprYrMth
        {"CR_CARD_HLD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardHldNm
        {"CR_CARD_AUTH_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardAuthAmt
        {"CR_CARD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardTpCd
        {"CUST_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum
        {"CUST_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoDt
        {"PO_VER_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poVerFlg
        {"ITT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ittNum
		null,	//xxLaborList
		null,	//xxPartsUsageList
		null,	//xxExpenseList
		null,	//xxChargeableList
		null,	//xxFsrIstlChkList
		null,	//svcMemoList
        {"ISTL_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlCpltFlg
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
