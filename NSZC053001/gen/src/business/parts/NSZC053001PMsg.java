//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220503175004000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC053001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC053001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC053001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** SVC_CR_REBIL_PK*/
	public final EZDPBigDecimalItem              svcCrRebilPk;

    /** CUST_INCDT_ID*/
	public final EZDPStringItem              custIncdtId;

    /** CUST_INCDT_LINE_ID*/
	public final EZDPStringItem              custIncdtLineId;

    /** SVC_CR_REBIL_RSN_CD*/
	public final EZDPStringItem              svcCrRebilRsnCd;

    /** SVC_CR_REBIL_RSN_TXT*/
	public final EZDPStringItem              svcCrRebilRsnTxt;

    /** SVC_CR_REBIL_DESC_TXT*/
	public final EZDPStringItem              svcCrRebilDescTxt;

    /** SVC_CR_REBIL_SRC_NM*/
	public final EZDPStringItem              svcCrRebilSrcNm;

    /** CUST_CARE_RGTN_PSN_CD*/
	public final EZDPStringItem              custCareRgtnPsnCd;

    /** SVC_CONTR_BR_CD*/
	public final EZDPStringItem              svcContrBrCd;

    /** CUST_INCDT_ASG_PSN_CD*/
	public final EZDPStringItem              custIncdtAsgPsnCd;

    /** SVC_CR_REBIL_PROC_CD*/
	public final EZDPStringItem              svcCrRebilProcCd;

    /** xxCrRebilDtlList*/
	public final business.parts.NSZC053001_xxCrRebilDtlListPMsgArray              xxCrRebilDtlList;

    /** XX_RSLT_STS_CD*/
	public final EZDPStringItem              xxRsltStsCd;

    /** xxMsgIdList*/
	public final business.parts.NSZC053001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC053001PMsg is constructor.
	 * The initialization when the instance of NSZC053001PMsg is generated.
	 */
	public NSZC053001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC053001PMsg is constructor.
	 * The initialization when the instance of NSZC053001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC053001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		svcCrRebilPk = (EZDPBigDecimalItem)newItem("svcCrRebilPk");
		custIncdtId = (EZDPStringItem)newItem("custIncdtId");
		custIncdtLineId = (EZDPStringItem)newItem("custIncdtLineId");
		svcCrRebilRsnCd = (EZDPStringItem)newItem("svcCrRebilRsnCd");
		svcCrRebilRsnTxt = (EZDPStringItem)newItem("svcCrRebilRsnTxt");
		svcCrRebilDescTxt = (EZDPStringItem)newItem("svcCrRebilDescTxt");
		svcCrRebilSrcNm = (EZDPStringItem)newItem("svcCrRebilSrcNm");
		custCareRgtnPsnCd = (EZDPStringItem)newItem("custCareRgtnPsnCd");
		svcContrBrCd = (EZDPStringItem)newItem("svcContrBrCd");
		custIncdtAsgPsnCd = (EZDPStringItem)newItem("custIncdtAsgPsnCd");
		svcCrRebilProcCd = (EZDPStringItem)newItem("svcCrRebilProcCd");
		xxCrRebilDtlList = (business.parts.NSZC053001_xxCrRebilDtlListPMsgArray)newMsgArray("xxCrRebilDtlList");
		xxRsltStsCd = (EZDPStringItem)newItem("xxRsltStsCd");
		xxMsgIdList = (business.parts.NSZC053001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC053001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC053001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcCrRebilPk", "svcCrRebilPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"custIncdtId", "custIncdtId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"custIncdtLineId", "custIncdtLineId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcCrRebilRsnCd", "svcCrRebilRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcCrRebilRsnTxt", "svcCrRebilRsnTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"svcCrRebilDescTxt", "svcCrRebilDescTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"svcCrRebilSrcNm", "svcCrRebilSrcNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"custCareRgtnPsnCd", "custCareRgtnPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"svcContrBrCd", "svcContrBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"custIncdtAsgPsnCd", "custIncdtAsgPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"svcCrRebilProcCd", "svcCrRebilProcCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxCrRebilDtlList", "xxCrRebilDtlList", null, "200", "business.parts.NSZC053001_xxCrRebilDtlListPMsgArray", null, null},
	
	{"xxRsltStsCd", "xxRsltStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC053001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SVC_CR_REBIL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk
        {"CUST_INCDT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtId
        {"CUST_INCDT_LINE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtLineId
        {"SVC_CR_REBIL_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilRsnCd
        {"SVC_CR_REBIL_RSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilRsnTxt
        {"SVC_CR_REBIL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDescTxt
        {"SVC_CR_REBIL_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilSrcNm
        {"CUST_CARE_RGTN_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCareRgtnPsnCd
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd
        {"CUST_INCDT_ASG_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtAsgPsnCd
        {"SVC_CR_REBIL_PROC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilProcCd
		null,	//xxCrRebilDtlList
        {"XX_RSLT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltStsCd
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

