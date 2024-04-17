//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200210145613000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1510_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1510 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1510_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CALL_STS_NM_A0*/
	public final EZDCStringItem              svcCallStsNm_A0;

    /** FSR_NUM_A0*/
	public final EZDCStringItem              fsrNum_A0;

    /** SVC_TASK_NUM_A0*/
	public final EZDCStringItem              svcTaskNum_A0;

    /** DS_SVC_CALL_TP_NM_A0*/
	public final EZDCStringItem              dsSvcCallTpNm_A0;

    /** FSR_VISIT_SCHD_DT_A0*/
	public final EZDCDateItem              fsrVisitSchdDt_A0;

    /** FSR_VISIT_SCHD_TM_A0*/
	public final EZDCStringItem              fsrVisitSchdTm_A0;

    /** FSR_CPLT_DT_A0*/
	public final EZDCDateItem              fsrCpltDt_A0;

    /** FSR_CPLT_TM_A0*/
	public final EZDCStringItem              fsrCpltTm_A0;

    /** TECH_CD_A0*/
	public final EZDCStringItem              techCd_A0;

    /** XX_TS_DSP_19_TXT_A0*/
	public final EZDCStringItem              xxTsDsp19Txt_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDCStringItem              ezUpTimeZone_A0;


	/**
	 * NWAL1510_ACMsg is constructor.
	 * The initialization when the instance of NWAL1510_ACMsg is generated.
	 */
	public NWAL1510_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1510_ACMsg is constructor.
	 * The initialization when the instance of NWAL1510_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1510_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcCallStsNm_A0 = (EZDCStringItem)newItem("svcCallStsNm_A0");
		fsrNum_A0 = (EZDCStringItem)newItem("fsrNum_A0");
		svcTaskNum_A0 = (EZDCStringItem)newItem("svcTaskNum_A0");
		dsSvcCallTpNm_A0 = (EZDCStringItem)newItem("dsSvcCallTpNm_A0");
		fsrVisitSchdDt_A0 = (EZDCDateItem)newItem("fsrVisitSchdDt_A0");
		fsrVisitSchdTm_A0 = (EZDCStringItem)newItem("fsrVisitSchdTm_A0");
		fsrCpltDt_A0 = (EZDCDateItem)newItem("fsrCpltDt_A0");
		fsrCpltTm_A0 = (EZDCStringItem)newItem("fsrCpltTm_A0");
		techCd_A0 = (EZDCStringItem)newItem("techCd_A0");
		xxTsDsp19Txt_A0 = (EZDCStringItem)newItem("xxTsDsp19Txt_A0");
		ezUpTimeZone_A0 = (EZDCStringItem)newItem("ezUpTimeZone_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1510_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1510_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcCallStsNm_A0", "svcCallStsNm_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"fsrNum_A0", "fsrNum_A0", "A0", null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskNum_A0", "svcTaskNum_A0", "A0", null, TYPE_HANKAKUEISU, "10", null},
	{"dsSvcCallTpNm_A0", "dsSvcCallTpNm_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"fsrVisitSchdDt_A0", "fsrVisitSchdDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"fsrVisitSchdTm_A0", "fsrVisitSchdTm_A0", "A0", null, TYPE_HANKAKUSUJI, "6", null},
	{"fsrCpltDt_A0", "fsrCpltDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"fsrCpltTm_A0", "fsrCpltTm_A0", "A0", null, TYPE_HANKAKUSUJI, "6", null},
	{"techCd_A0", "techCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"xxTsDsp19Txt_A0", "xxTsDsp19Txt_A0", "A0", null, TYPE_HANKAKUEISU, "19", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CALL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCallStsNm_A0
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_A0
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_A0
        {"DS_SVC_CALL_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpNm_A0
        {"FSR_VISIT_SCHD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitSchdDt_A0
        {"FSR_VISIT_SCHD_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitSchdTm_A0
        {"FSR_CPLT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrCpltDt_A0
        {"FSR_CPLT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrCpltTm_A0
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_A0
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_A0
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
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

