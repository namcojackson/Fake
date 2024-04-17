//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231031100058000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_KBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_KBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FSR_NUM_KA*/
	public final EZDBStringItem              fsrNum_KA;

    /** XX_SCR_ITEM_54_TXT_K*/
	public final EZDBStringItem              xxScrItem54Txt_K;

    /** FSR_NUM_K*/
	public final EZDBStringItem              fsrNum_K;

    /** LOC_NM_K*/
	public final EZDBStringItem              locNm_K;

    /** SVC_TASK_STS_NM_K*/
	public final EZDBStringItem              svcTaskStsNm_K;

    /** SVC_TASK_RCV_DT_K*/
	public final EZDBDateItem              svcTaskRcvDt_K;

    /** SVC_PBLM_TP_DESC_TXT_K*/
	public final EZDBStringItem              svcPblmTpDescTxt_K;

    /** SVC_PBLM_CRCT_TP_DESC_TXT_K*/
	public final EZDBStringItem              svcPblmCrctTpDescTxt_K;

    /** SVC_TASK_SCHD_DT_K*/
	public final EZDBDateItem              svcTaskSchdDt_K;

    /** SVC_TASK_CLO_DT_K*/
	public final EZDBDateItem              svcTaskCloDt_K;

    /** XX_FULL_NM_K*/
	public final EZDBStringItem              xxFullNm_K;

    /** SVC_TASK_NUM_K*/
	public final EZDBStringItem              svcTaskNum_K;


	/**
	 * NSAL0010_KBMsg is constructor.
	 * The initialization when the instance of NSAL0010_KBMsg is generated.
	 */
	public NSAL0010_KBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_KBMsg is constructor.
	 * The initialization when the instance of NSAL0010_KBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_KBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		fsrNum_KA = (EZDBStringItem)newItem("fsrNum_KA");
		xxScrItem54Txt_K = (EZDBStringItem)newItem("xxScrItem54Txt_K");
		fsrNum_K = (EZDBStringItem)newItem("fsrNum_K");
		locNm_K = (EZDBStringItem)newItem("locNm_K");
		svcTaskStsNm_K = (EZDBStringItem)newItem("svcTaskStsNm_K");
		svcTaskRcvDt_K = (EZDBDateItem)newItem("svcTaskRcvDt_K");
		svcPblmTpDescTxt_K = (EZDBStringItem)newItem("svcPblmTpDescTxt_K");
		svcPblmCrctTpDescTxt_K = (EZDBStringItem)newItem("svcPblmCrctTpDescTxt_K");
		svcTaskSchdDt_K = (EZDBDateItem)newItem("svcTaskSchdDt_K");
		svcTaskCloDt_K = (EZDBDateItem)newItem("svcTaskCloDt_K");
		xxFullNm_K = (EZDBStringItem)newItem("xxFullNm_K");
		svcTaskNum_K = (EZDBStringItem)newItem("svcTaskNum_K");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_KBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_KBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"fsrNum_KA", "fsrNum_KA", "KA", null, TYPE_HANKAKUEISU, "10", null},
	{"xxScrItem54Txt_K", "xxScrItem54Txt_K", "K", null, TYPE_HANKAKUEISU, "54", null},
	{"fsrNum_K", "fsrNum_K", "K", null, TYPE_HANKAKUEISU, "10", null},
	{"locNm_K", "locNm_K", "K", null, TYPE_HANKAKUEISU, "60", null},
	{"svcTaskStsNm_K", "svcTaskStsNm_K", "K", null, TYPE_HANKAKUEISU, "30", null},
	{"svcTaskRcvDt_K", "svcTaskRcvDt_K", "K", null, TYPE_NENTSUKIHI, "8", null},
	{"svcPblmTpDescTxt_K", "svcPblmTpDescTxt_K", "K", null, TYPE_HANKAKUEISU, "500", null},
	{"svcPblmCrctTpDescTxt_K", "svcPblmCrctTpDescTxt_K", "K", null, TYPE_HANKAKUEISU, "500", null},
	{"svcTaskSchdDt_K", "svcTaskSchdDt_K", "K", null, TYPE_NENTSUKIHI, "8", null},
	{"svcTaskCloDt_K", "svcTaskCloDt_K", "K", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_K", "xxFullNm_K", "K", null, TYPE_HANKAKUEISU, "100", null},
	{"svcTaskNum_K", "svcTaskNum_K", "K", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"FSR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_KA
        {"XX_SCR_ITEM_54_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem54Txt_K
        {"FSR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_K
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_K
        {"SVC_TASK_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsNm_K
        {"SVC_TASK_RCV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//svcTaskRcvDt_K
        {"SVC_PBLM_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpDescTxt_K
        {"SVC_PBLM_CRCT_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmCrctTpDescTxt_K
        {"SVC_TASK_SCHD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//svcTaskSchdDt_K
        {"SVC_TASK_CLO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//svcTaskCloDt_K
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_K
        {"SVC_TASK_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_K
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

