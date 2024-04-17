//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220427134618000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC068001_CsaSupplementalTimeListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC068001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC068001_CsaSupplementalTimeListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** START_TS*/
	public final EZDPStringItem              startTs;

    /** END_TS*/
	public final EZDPStringItem              endTs;

    /** XTRNL_STS_REF_TXT*/
	public final EZDPStringItem              xtrnlStsRefTxt;

    /** XTRNL_CALL_TP_REF_TXT*/
	public final EZDPStringItem              xtrnlCallTpRefTxt;

    /** SVC_CMNT_TXT*/
	public final EZDPStringItem              svcCmntTxt;

    /** SVC_TRVL_TM_NUM*/
	public final EZDPBigDecimalItem              svcTrvlTmNum;

    /** SVC_SUPPL_TASK_NUM*/
	public final EZDPStringItem              svcSupplTaskNum;


	/**
	 * NSZC068001_CsaSupplementalTimeListPMsg is constructor.
	 * The initialization when the instance of NSZC068001_CsaSupplementalTimeListPMsg is generated.
	 */
	public NSZC068001_CsaSupplementalTimeListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC068001_CsaSupplementalTimeListPMsg is constructor.
	 * The initialization when the instance of NSZC068001_CsaSupplementalTimeListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC068001_CsaSupplementalTimeListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		startTs = (EZDPStringItem)newItem("startTs");
		endTs = (EZDPStringItem)newItem("endTs");
		xtrnlStsRefTxt = (EZDPStringItem)newItem("xtrnlStsRefTxt");
		xtrnlCallTpRefTxt = (EZDPStringItem)newItem("xtrnlCallTpRefTxt");
		svcCmntTxt = (EZDPStringItem)newItem("svcCmntTxt");
		svcTrvlTmNum = (EZDPBigDecimalItem)newItem("svcTrvlTmNum");
		svcSupplTaskNum = (EZDPStringItem)newItem("svcSupplTaskNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC068001_CsaSupplementalTimeListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC068001_CsaSupplementalTimeListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"startTs", "startTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"endTs", "endTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xtrnlStsRefTxt", "xtrnlStsRefTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xtrnlCallTpRefTxt", "xtrnlCallTpRefTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"svcTrvlTmNum", "svcTrvlTmNum", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"svcSupplTaskNum", "svcSupplTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"START_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startTs
        {"END_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//endTs
        {"XTRNL_STS_REF_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlStsRefTxt
        {"XTRNL_CALL_TP_REF_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlCallTpRefTxt
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"SVC_TRVL_TM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTrvlTmNum
        {"SVC_SUPPL_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSupplTaskNum
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

