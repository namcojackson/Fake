//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181008203853000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_KSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_KSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FSR_NUM_KA*/
	public final EZDSStringItem              fsrNum_KA;

    /** XX_SCR_ITEM_54_TXT_K*/
	public final EZDSStringItem              xxScrItem54Txt_K;

    /** FSR_NUM_K*/
	public final EZDSStringItem              fsrNum_K;

    /** LOC_NM_K*/
	public final EZDSStringItem              locNm_K;

    /** SVC_TASK_STS_NM_K*/
	public final EZDSStringItem              svcTaskStsNm_K;

    /** SVC_TASK_RCV_DT_K*/
	public final EZDSDateItem              svcTaskRcvDt_K;

    /** SVC_PBLM_TP_DESC_TXT_K*/
	public final EZDSStringItem              svcPblmTpDescTxt_K;

    /** SVC_PBLM_CRCT_TP_DESC_TXT_K*/
	public final EZDSStringItem              svcPblmCrctTpDescTxt_K;

    /** SVC_TASK_SCHD_DT_K*/
	public final EZDSDateItem              svcTaskSchdDt_K;

    /** SVC_TASK_CLO_DT_K*/
	public final EZDSDateItem              svcTaskCloDt_K;

    /** XX_FULL_NM_K*/
	public final EZDSStringItem              xxFullNm_K;

    /** SVC_TASK_NUM_K*/
	public final EZDSStringItem              svcTaskNum_K;


	/**
	 * NSAL0010_KSMsg is constructor.
	 * The initialization when the instance of NSAL0010_KSMsg is generated.
	 */
	public NSAL0010_KSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_KSMsg is constructor.
	 * The initialization when the instance of NSAL0010_KSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_KSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		fsrNum_KA = (EZDSStringItem)newItem("fsrNum_KA");
		xxScrItem54Txt_K = (EZDSStringItem)newItem("xxScrItem54Txt_K");
		fsrNum_K = (EZDSStringItem)newItem("fsrNum_K");
		locNm_K = (EZDSStringItem)newItem("locNm_K");
		svcTaskStsNm_K = (EZDSStringItem)newItem("svcTaskStsNm_K");
		svcTaskRcvDt_K = (EZDSDateItem)newItem("svcTaskRcvDt_K");
		svcPblmTpDescTxt_K = (EZDSStringItem)newItem("svcPblmTpDescTxt_K");
		svcPblmCrctTpDescTxt_K = (EZDSStringItem)newItem("svcPblmCrctTpDescTxt_K");
		svcTaskSchdDt_K = (EZDSDateItem)newItem("svcTaskSchdDt_K");
		svcTaskCloDt_K = (EZDSDateItem)newItem("svcTaskCloDt_K");
		xxFullNm_K = (EZDSStringItem)newItem("xxFullNm_K");
		svcTaskNum_K = (EZDSStringItem)newItem("svcTaskNum_K");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_KSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_KSMsgArray();
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

        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_KA
        {"XX_SCR_ITEM_54_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem54Txt_K
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum_K
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_K
        {"SVC_TASK_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskStsNm_K
        {"SVC_TASK_RCV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskRcvDt_K
        {"SVC_PBLM_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpDescTxt_K
        {"SVC_PBLM_CRCT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmCrctTpDescTxt_K
        {"SVC_TASK_SCHD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskSchdDt_K
        {"SVC_TASK_CLO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskCloDt_K
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_K
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_K
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
