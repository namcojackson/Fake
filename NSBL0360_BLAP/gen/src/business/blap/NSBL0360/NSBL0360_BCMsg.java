//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160224083349000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0360_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0360 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0360_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_DESC_TXT_B*/
	public final EZDCStringItem              orgDescTxt_B;

    /** TOT_IN_PROC_TASK_CNT_B*/
	public final EZDCBigDecimalItem              totInProcTaskCnt_B;

    /** PRT_WAIT_TASK_CNT_B*/
	public final EZDCBigDecimalItem              prtWaitTaskCnt_B;

    /** SPCL_WAIT_TASK_CNT_B*/
	public final EZDCBigDecimalItem              spclWaitTaskCnt_B;

    /** OTH_OPEN_TASK_CNT_B*/
	public final EZDCBigDecimalItem              othOpenTaskCnt_B;

    /** CUST_TASK_CNT_B*/
	public final EZDCBigDecimalItem              custTaskCnt_B;

    /** ESCL_TASK_CNT_B*/
	public final EZDCBigDecimalItem              esclTaskCnt_B;

    /** CRAT_TASK_CNT_B*/
	public final EZDCBigDecimalItem              cratTaskCnt_B;

    /** CRAT_TASK_PER_TECH_RATE_B*/
	public final EZDCBigDecimalItem              cratTaskPerTechRate_B;

    /** AFT_HOUR_TASK_CNT_B*/
	public final EZDCBigDecimalItem              aftHourTaskCnt_B;

    /** AFT_HOUR_TASK_PER_TECH_RATE_B*/
	public final EZDCBigDecimalItem              aftHourTaskPerTechRate_B;

    /** CLO_TASK_CNT_B*/
	public final EZDCBigDecimalItem              cloTaskCnt_B;

    /** CLO_TASK_PER_TECH_RATE_B*/
	public final EZDCBigDecimalItem              cloTaskPerTechRate_B;

    /** PRT_FAIL_CNT_B*/
	public final EZDCBigDecimalItem              prtFailCnt_B;

    /** POST_ENTRY_TASK_CNT_B*/
	public final EZDCBigDecimalItem              postEntryTaskCnt_B;

    /** AVAL_TECH_CNT_B*/
	public final EZDCBigDecimalItem              avalTechCnt_B;

    /** RSP_TM_CUST_TASK_RATE_B*/
	public final EZDCBigDecimalItem              rspTmCustTaskRate_B;

    /** RSP_TM_ALL_TASK_RATE_B*/
	public final EZDCBigDecimalItem              rspTmAllTaskRate_B;

    /** ORG_CD_B*/
	public final EZDCStringItem              orgCd_B;

    /** ORG_LAYER_NUM_B*/
	public final EZDCBigDecimalItem              orgLayerNum_B;

    /** XX_DT_TM_B1*/
	public final EZDCStringItem              xxDtTm_B1;

    /** XX_DT_TM_B2*/
	public final EZDCStringItem              xxDtTm_B2;


	/**
	 * NSBL0360_BCMsg is constructor.
	 * The initialization when the instance of NSBL0360_BCMsg is generated.
	 */
	public NSBL0360_BCMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0360_BCMsg is constructor.
	 * The initialization when the instance of NSBL0360_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0360_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgDescTxt_B = (EZDCStringItem)newItem("orgDescTxt_B");
		totInProcTaskCnt_B = (EZDCBigDecimalItem)newItem("totInProcTaskCnt_B");
		prtWaitTaskCnt_B = (EZDCBigDecimalItem)newItem("prtWaitTaskCnt_B");
		spclWaitTaskCnt_B = (EZDCBigDecimalItem)newItem("spclWaitTaskCnt_B");
		othOpenTaskCnt_B = (EZDCBigDecimalItem)newItem("othOpenTaskCnt_B");
		custTaskCnt_B = (EZDCBigDecimalItem)newItem("custTaskCnt_B");
		esclTaskCnt_B = (EZDCBigDecimalItem)newItem("esclTaskCnt_B");
		cratTaskCnt_B = (EZDCBigDecimalItem)newItem("cratTaskCnt_B");
		cratTaskPerTechRate_B = (EZDCBigDecimalItem)newItem("cratTaskPerTechRate_B");
		aftHourTaskCnt_B = (EZDCBigDecimalItem)newItem("aftHourTaskCnt_B");
		aftHourTaskPerTechRate_B = (EZDCBigDecimalItem)newItem("aftHourTaskPerTechRate_B");
		cloTaskCnt_B = (EZDCBigDecimalItem)newItem("cloTaskCnt_B");
		cloTaskPerTechRate_B = (EZDCBigDecimalItem)newItem("cloTaskPerTechRate_B");
		prtFailCnt_B = (EZDCBigDecimalItem)newItem("prtFailCnt_B");
		postEntryTaskCnt_B = (EZDCBigDecimalItem)newItem("postEntryTaskCnt_B");
		avalTechCnt_B = (EZDCBigDecimalItem)newItem("avalTechCnt_B");
		rspTmCustTaskRate_B = (EZDCBigDecimalItem)newItem("rspTmCustTaskRate_B");
		rspTmAllTaskRate_B = (EZDCBigDecimalItem)newItem("rspTmAllTaskRate_B");
		orgCd_B = (EZDCStringItem)newItem("orgCd_B");
		orgLayerNum_B = (EZDCBigDecimalItem)newItem("orgLayerNum_B");
		xxDtTm_B1 = (EZDCStringItem)newItem("xxDtTm_B1");
		xxDtTm_B2 = (EZDCStringItem)newItem("xxDtTm_B2");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0360_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0360_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgDescTxt_B", "orgDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"totInProcTaskCnt_B", "totInProcTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"prtWaitTaskCnt_B", "prtWaitTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"spclWaitTaskCnt_B", "spclWaitTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"othOpenTaskCnt_B", "othOpenTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"custTaskCnt_B", "custTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"esclTaskCnt_B", "esclTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cratTaskCnt_B", "cratTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cratTaskPerTechRate_B", "cratTaskPerTechRate_B", "B", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"aftHourTaskCnt_B", "aftHourTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"aftHourTaskPerTechRate_B", "aftHourTaskPerTechRate_B", "B", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"cloTaskCnt_B", "cloTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"cloTaskPerTechRate_B", "cloTaskPerTechRate_B", "B", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"prtFailCnt_B", "prtFailCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"postEntryTaskCnt_B", "postEntryTaskCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"avalTechCnt_B", "avalTechCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rspTmCustTaskRate_B", "rspTmCustTaskRate_B", "B", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"rspTmAllTaskRate_B", "rspTmAllTaskRate_B", "B", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"orgCd_B", "orgCd_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"orgLayerNum_B", "orgLayerNum_B", "B", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"xxDtTm_B1", "xxDtTm_B1", "B1", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_B2", "xxDtTm_B2", "B2", null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_B
        {"TOT_IN_PROC_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totInProcTaskCnt_B
        {"PRT_WAIT_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtWaitTaskCnt_B
        {"SPCL_WAIT_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spclWaitTaskCnt_B
        {"OTH_OPEN_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//othOpenTaskCnt_B
        {"CUST_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custTaskCnt_B
        {"ESCL_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//esclTaskCnt_B
        {"CRAT_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratTaskCnt_B
        {"CRAT_TASK_PER_TECH_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratTaskPerTechRate_B
        {"AFT_HOUR_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftHourTaskCnt_B
        {"AFT_HOUR_TASK_PER_TECH_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aftHourTaskPerTechRate_B
        {"CLO_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cloTaskCnt_B
        {"CLO_TASK_PER_TECH_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cloTaskPerTechRate_B
        {"PRT_FAIL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtFailCnt_B
        {"POST_ENTRY_TASK_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postEntryTaskCnt_B
        {"AVAL_TECH_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//avalTechCnt_B
        {"RSP_TM_CUST_TASK_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rspTmCustTaskRate_B
        {"RSP_TM_ALL_TASK_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rspTmAllTaskRate_B
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_B
        {"ORG_LAYER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgLayerNum_B
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_B2
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

