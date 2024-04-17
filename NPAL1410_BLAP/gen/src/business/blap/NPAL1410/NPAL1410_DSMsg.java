//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190402141927000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1410_DSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1410 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1410_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_D1*/
	public final EZDSStringItem              xxChkBox_D1;

    /** XX_POP_PRM_D1*/
	public final EZDSStringItem              xxPopPrm_D1;

    /** RMNF_TASK_DESC_TXT_D1*/
	public final EZDSStringItem              rmnfTaskDescTxt_D1;

    /** XX_POP_PRM_DF*/
	public final EZDSStringItem              xxPopPrm_DF;

    /** XX_POP_PRM_DT*/
	public final EZDSStringItem              xxPopPrm_DT;

    /** TECH_TOC_CD_D1*/
	public final EZDSStringItem              techTocCd_D1;

    /** TECH_NM_D1*/
	public final EZDSStringItem              techNm_D1;

    /** RMNF_LBOR_AOT_D1*/
	public final EZDSBigDecimalItem              rmnfLborAot_D1;

    /** RMNF_PRT_USG_COST_AMT_D1*/
	public final EZDSBigDecimalItem              rmnfPrtUsgCostAmt_D1;

    /** RMNF_LBOR_COST_AMT_D1*/
	public final EZDSBigDecimalItem              rmnfLborCostAmt_D1;

    /** RMNF_TOT_COST_AMT_D1*/
	public final EZDSBigDecimalItem              rmnfTotCostAmt_D1;

    /** RMNF_TASK_NUM_DH*/
	public final EZDSStringItem              rmnfTaskNum_DH;

    /** RMNF_TASK_PK_DH*/
	public final EZDSBigDecimalItem              rmnfTaskPk_DH;

    /** _EZUpdateDateTime_DH*/
	public final EZDSStringItem              ezUpTime_DH;

    /** _EZUpTimeZone_DH*/
	public final EZDSStringItem              ezUpTimeZone_DH;

    /** SCE_ORD_TP_CD_ES*/
	public final EZDSStringItem              sceOrdTpCd_ES;

    /** SCE_ORD_TP_CD_EC*/
	public final EZDSStringItem              sceOrdTpCd_EC;

    /** SCE_ORD_TP_NM_EN*/
	public final EZDSStringItem              sceOrdTpNm_EN;


	/**
	 * NPAL1410_DSMsg is constructor.
	 * The initialization when the instance of NPAL1410_DSMsg is generated.
	 */
	public NPAL1410_DSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1410_DSMsg is constructor.
	 * The initialization when the instance of NPAL1410_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1410_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_D1 = (EZDSStringItem)newItem("xxChkBox_D1");
		xxPopPrm_D1 = (EZDSStringItem)newItem("xxPopPrm_D1");
		rmnfTaskDescTxt_D1 = (EZDSStringItem)newItem("rmnfTaskDescTxt_D1");
		xxPopPrm_DF = (EZDSStringItem)newItem("xxPopPrm_DF");
		xxPopPrm_DT = (EZDSStringItem)newItem("xxPopPrm_DT");
		techTocCd_D1 = (EZDSStringItem)newItem("techTocCd_D1");
		techNm_D1 = (EZDSStringItem)newItem("techNm_D1");
		rmnfLborAot_D1 = (EZDSBigDecimalItem)newItem("rmnfLborAot_D1");
		rmnfPrtUsgCostAmt_D1 = (EZDSBigDecimalItem)newItem("rmnfPrtUsgCostAmt_D1");
		rmnfLborCostAmt_D1 = (EZDSBigDecimalItem)newItem("rmnfLborCostAmt_D1");
		rmnfTotCostAmt_D1 = (EZDSBigDecimalItem)newItem("rmnfTotCostAmt_D1");
		rmnfTaskNum_DH = (EZDSStringItem)newItem("rmnfTaskNum_DH");
		rmnfTaskPk_DH = (EZDSBigDecimalItem)newItem("rmnfTaskPk_DH");
		ezUpTime_DH = (EZDSStringItem)newItem("ezUpTime_DH");
		ezUpTimeZone_DH = (EZDSStringItem)newItem("ezUpTimeZone_DH");
		sceOrdTpCd_ES = (EZDSStringItem)newItem("sceOrdTpCd_ES");
		sceOrdTpCd_EC = (EZDSStringItem)newItem("sceOrdTpCd_EC");
		sceOrdTpNm_EN = (EZDSStringItem)newItem("sceOrdTpNm_EN");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1410_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1410_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_D1", "xxChkBox_D1", "D1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPopPrm_D1", "xxPopPrm_D1", "D1", null, TYPE_HANKAKUEISU, "300", null},
	{"rmnfTaskDescTxt_D1", "rmnfTaskDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "100", null},
	{"xxPopPrm_DF", "xxPopPrm_DF", "DF", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_DT", "xxPopPrm_DT", "DT", null, TYPE_HANKAKUEISU, "300", null},
	{"techTocCd_D1", "techTocCd_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_D1", "techNm_D1", "D1", null, TYPE_HANKAKUEISU, "45", null},
	{"rmnfLborAot_D1", "rmnfLborAot_D1", "D1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"rmnfPrtUsgCostAmt_D1", "rmnfPrtUsgCostAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rmnfLborCostAmt_D1", "rmnfLborCostAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rmnfTotCostAmt_D1", "rmnfTotCostAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rmnfTaskNum_DH", "rmnfTaskNum_DH", "DH", null, TYPE_HANKAKUEISU, "3", null},
	{"rmnfTaskPk_DH", "rmnfTaskPk_DH", "DH", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_DH", "ezUpTime_DH", "DH", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_DH", "ezUpTimeZone_DH", "DH", null, TYPE_HANKAKUEISU, "5", null},
	{"sceOrdTpCd_ES", "sceOrdTpCd_ES", "ES", null, TYPE_HANKAKUEISU, "2", null},
	{"sceOrdTpCd_EC", "sceOrdTpCd_EC", "EC", null, TYPE_HANKAKUEISU, "2", null},
	{"sceOrdTpNm_EN", "sceOrdTpNm_EN", "EN", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_D1
        {"RMNF_TASK_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskDescTxt_D1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_DF
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_DT
        {"TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd_D1
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_D1
        {"RMNF_LBOR_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborAot_D1
        {"RMNF_PRT_USG_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfPrtUsgCostAmt_D1
        {"RMNF_LBOR_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborCostAmt_D1
        {"RMNF_TOT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTotCostAmt_D1
        {"RMNF_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskNum_DH
        {"RMNF_TASK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskPk_DH
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DH
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DH
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_ES
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd_EC
        {"SCE_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpNm_EN
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

