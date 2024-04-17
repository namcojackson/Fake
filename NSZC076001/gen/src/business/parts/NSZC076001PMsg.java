//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151209132002000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC076001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC076001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC076001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** SVC_SUPPL_FROM_DT*/
	public final EZDPDateItem              svcSupplFromDt;

    /** SVC_SUPPL_FROM_TM*/
	public final EZDPStringItem              svcSupplFromTm;

    /** SVC_SUPPL_TO_DT*/
	public final EZDPDateItem              svcSupplToDt;

    /** SVC_SUPPL_TO_TM*/
	public final EZDPStringItem              svcSupplToTm;

    /** SVC_TRVL_TM_NUM*/
	public final EZDPBigDecimalItem              svcTrvlTmNum;

    /** XTRNL_STS_REF_TXT*/
	public final EZDPStringItem              xtrnlStsRefTxt;

    /** SVC_SUPPL_TASK_TP_DESC_TXT*/
	public final EZDPStringItem              svcSupplTaskTpDescTxt;

    /** SVC_CMNT_TXT*/
	public final EZDPStringItem              svcCmntTxt;

    /** PSN_CD*/
	public final EZDPStringItem              psnCd;

    /** xxMsgIdList*/
	public final business.parts.NSZC076001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC076001PMsg is constructor.
	 * The initialization when the instance of NSZC076001PMsg is generated.
	 */
	public NSZC076001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC076001PMsg is constructor.
	 * The initialization when the instance of NSZC076001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC076001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		svcSupplFromDt = (EZDPDateItem)newItem("svcSupplFromDt");
		svcSupplFromTm = (EZDPStringItem)newItem("svcSupplFromTm");
		svcSupplToDt = (EZDPDateItem)newItem("svcSupplToDt");
		svcSupplToTm = (EZDPStringItem)newItem("svcSupplToTm");
		svcTrvlTmNum = (EZDPBigDecimalItem)newItem("svcTrvlTmNum");
		xtrnlStsRefTxt = (EZDPStringItem)newItem("xtrnlStsRefTxt");
		svcSupplTaskTpDescTxt = (EZDPStringItem)newItem("svcSupplTaskTpDescTxt");
		svcCmntTxt = (EZDPStringItem)newItem("svcCmntTxt");
		psnCd = (EZDPStringItem)newItem("psnCd");
		xxMsgIdList = (business.parts.NSZC076001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC076001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC076001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcSupplFromDt", "svcSupplFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcSupplFromTm", "svcSupplFromTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"svcSupplToDt", "svcSupplToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcSupplToTm", "svcSupplToTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"svcTrvlTmNum", "svcTrvlTmNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xtrnlStsRefTxt", "xtrnlStsRefTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcSupplTaskTpDescTxt", "svcSupplTaskTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"psnCd", "psnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxMsgIdList", "xxMsgIdList", null, "2000", "business.parts.NSZC076001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SVC_SUPPL_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplFromDt
        {"SVC_SUPPL_FROM_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplFromTm
        {"SVC_SUPPL_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplToDt
        {"SVC_SUPPL_TO_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplToTm
        {"SVC_TRVL_TM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTrvlTmNum
        {"XTRNL_STS_REF_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlStsRefTxt
        {"SVC_SUPPL_TASK_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplTaskTpDescTxt
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd
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

