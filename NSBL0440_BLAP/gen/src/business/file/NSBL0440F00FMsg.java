//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180202140301000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0440F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0440F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0440F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_A*/
	public final EZDFStringItem              serNum_A;

    /** SVC_MOD_PLN_ID_A*/
	public final EZDFStringItem              svcModPlnId_A;

    /** T_MDL_NM_A*/
	public final EZDFStringItem              t_MdlNm_A;

    /** SVC_MOD_PROC_STS_DESC_TXT_A*/
	public final EZDFStringItem              svcModProcStsDescTxt_A;

    /** FLD_SVC_BR_CD_A*/
	public final EZDFStringItem              fldSvcBrCd_A;

    /** SVC_MNF_MOD_NUM_A*/
	public final EZDFStringItem              svcMnfModNum_A;

    /** TECH_NM_A*/
	public final EZDFStringItem              techNm_A;

    /** SVC_TASK_NUM_A*/
	public final EZDFStringItem              svcTaskNum_A;

    /** XX_DT_TXT_A*/
	public final EZDFStringItem              xxDtTxt_A;


	/**
	 * NSBL0440F00FMsg is constructor.
	 * The initialization when the instance of NSBL0440F00FMsg is generated.
	 */
	public NSBL0440F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0440F00FMsg is constructor.
	 * The initialization when the instance of NSBL0440F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0440F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_A = (EZDFStringItem)newItem("serNum_A");
		svcModPlnId_A = (EZDFStringItem)newItem("svcModPlnId_A");
		t_MdlNm_A = (EZDFStringItem)newItem("t_MdlNm_A");
		svcModProcStsDescTxt_A = (EZDFStringItem)newItem("svcModProcStsDescTxt_A");
		fldSvcBrCd_A = (EZDFStringItem)newItem("fldSvcBrCd_A");
		svcMnfModNum_A = (EZDFStringItem)newItem("svcMnfModNum_A");
		techNm_A = (EZDFStringItem)newItem("techNm_A");
		svcTaskNum_A = (EZDFStringItem)newItem("svcTaskNum_A");
		xxDtTxt_A = (EZDFStringItem)newItem("xxDtTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0440F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0440F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcModPlnId_A", "svcModPlnId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"t_MdlNm_A", "t_MdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcModProcStsDescTxt_A", "svcModProcStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"fldSvcBrCd_A", "fldSvcBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcMnfModNum_A", "svcMnfModNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"techNm_A", "techNm_A", "A", null, TYPE_HANKAKUEISU, "45", null},
	{"svcTaskNum_A", "svcTaskNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_A", "xxDtTxt_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_MOD_PLN_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPlnId_A
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A
        {"SVC_MOD_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModProcStsDescTxt_A
        {"FLD_SVC_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fldSvcBrCd_A
        {"SVC_MNF_MOD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMnfModNum_A
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_A
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A
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

