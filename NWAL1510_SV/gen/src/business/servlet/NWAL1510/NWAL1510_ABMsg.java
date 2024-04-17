//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230728112937000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1510_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1510 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1510_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CALL_STS_NM_A0*/
	public final EZDBStringItem              svcCallStsNm_A0;

    /** FSR_NUM_A0*/
	public final EZDBStringItem              fsrNum_A0;

    /** SVC_TASK_NUM_A0*/
	public final EZDBStringItem              svcTaskNum_A0;

    /** DS_SVC_CALL_TP_NM_A0*/
	public final EZDBStringItem              dsSvcCallTpNm_A0;

    /** FSR_VISIT_SCHD_DT_A0*/
	public final EZDBDateItem              fsrVisitSchdDt_A0;

    /** FSR_VISIT_SCHD_TM_A0*/
	public final EZDBStringItem              fsrVisitSchdTm_A0;

    /** FSR_CPLT_DT_A0*/
	public final EZDBDateItem              fsrCpltDt_A0;

    /** FSR_CPLT_TM_A0*/
	public final EZDBStringItem              fsrCpltTm_A0;

    /** TECH_CD_A0*/
	public final EZDBStringItem              techCd_A0;

    /** XX_TS_DSP_19_TXT_A0*/
	public final EZDBStringItem              xxTsDsp19Txt_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDBStringItem              ezUpTimeZone_A0;


	/**
	 * NWAL1510_ABMsg is constructor.
	 * The initialization when the instance of NWAL1510_ABMsg is generated.
	 */
	public NWAL1510_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1510_ABMsg is constructor.
	 * The initialization when the instance of NWAL1510_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1510_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcCallStsNm_A0 = (EZDBStringItem)newItem("svcCallStsNm_A0");
		fsrNum_A0 = (EZDBStringItem)newItem("fsrNum_A0");
		svcTaskNum_A0 = (EZDBStringItem)newItem("svcTaskNum_A0");
		dsSvcCallTpNm_A0 = (EZDBStringItem)newItem("dsSvcCallTpNm_A0");
		fsrVisitSchdDt_A0 = (EZDBDateItem)newItem("fsrVisitSchdDt_A0");
		fsrVisitSchdTm_A0 = (EZDBStringItem)newItem("fsrVisitSchdTm_A0");
		fsrCpltDt_A0 = (EZDBDateItem)newItem("fsrCpltDt_A0");
		fsrCpltTm_A0 = (EZDBStringItem)newItem("fsrCpltTm_A0");
		techCd_A0 = (EZDBStringItem)newItem("techCd_A0");
		xxTsDsp19Txt_A0 = (EZDBStringItem)newItem("xxTsDsp19Txt_A0");
		ezUpTimeZone_A0 = (EZDBStringItem)newItem("ezUpTimeZone_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1510_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1510_ABMsgArray();
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

        {"SVC_CALL_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCallStsNm_A0
        {"FSR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_A0
        {"SVC_TASK_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_A0
        {"DS_SVC_CALL_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpNm_A0
        {"FSR_VISIT_SCHD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//fsrVisitSchdDt_A0
        {"FSR_VISIT_SCHD_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrVisitSchdTm_A0
        {"FSR_CPLT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//fsrCpltDt_A0
        {"FSR_CPLT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrCpltTm_A0
        {"TECH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_A0
        {"XX_TS_DSP_19_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_A0
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
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
