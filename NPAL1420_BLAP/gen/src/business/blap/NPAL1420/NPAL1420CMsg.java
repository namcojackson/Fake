//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180202091923000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1420CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1420;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1420 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1420CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDCStringItem              xxModeCd;

    /** XX_MODE_CD_WH*/
	public final EZDCStringItem              xxModeCd_WH;

    /** RMNF_ORD_NUM*/
	public final EZDCStringItem              rmnfOrdNum;

    /** TECH_NM_H*/
	public final EZDCStringItem              techNm_H;

    /** RMNF_MAIN_UNIT_SER_NUM*/
	public final EZDCStringItem              rmnfMainUnitSerNum;

    /** RMNF_ORD_STS_DESC_TXT*/
	public final EZDCStringItem              rmnfOrdStsDescTxt;

    /** RMNF_TASK_PK*/
	public final EZDCBigDecimalItem              rmnfTaskPk;

    /** XX_MSG_TXT_WH*/
	public final EZDCStringItem              xxMsgTxt_WH;

    /** RTL_WH_CD_H*/
	public final EZDCStringItem              rtlWhCd_H;

    /** RTL_WH_NM_H*/
	public final EZDCStringItem              rtlWhNm_H;

    /** OPEN_STS_FLG_H*/
	public final EZDCStringItem              openStsFlg_H;

    /** XX_MSG_TXT_TS*/
	public final EZDCStringItem              xxMsgTxt_TS;

    /** RMNF_TASK_NUM*/
	public final EZDCStringItem              rmnfTaskNum;

    /** RMNF_TASK_START_DT*/
	public final EZDCDateItem              rmnfTaskStartDt;

    /** RMNF_TASK_END_DT*/
	public final EZDCDateItem              rmnfTaskEndDt;

    /** RMNF_TASK_DESC_TXT*/
	public final EZDCStringItem              rmnfTaskDescTxt;

    /** SPCL_INSTN_CMNT_TXT*/
	public final EZDCStringItem              spclInstnCmntTxt;

    /** TECH_TOC_CD*/
	public final EZDCStringItem              techTocCd;

    /** TECH_NM_L*/
	public final EZDCStringItem              techNm_L;

    /** RMNF_LBOR_CMNT_TXT*/
	public final EZDCStringItem              rmnfLborCmntTxt;

    /** RMNF_LBOR_AOT*/
	public final EZDCBigDecimalItem              rmnfLborAot;

    /** RMNF_COST_PER_HOUR_AMT*/
	public final EZDCBigDecimalItem              rmnfCostPerHourAmt;

    /** STD_CCY_CD_PH*/
	public final EZDCStringItem              stdCcyCd_PH;

    /** RMNF_LBOR_COST_AMT*/
	public final EZDCBigDecimalItem              rmnfLborCostAmt;

    /** STD_CCY_CD_LC*/
	public final EZDCStringItem              stdCcyCd_LC;

    /** PRT_UNIT_COST_AMT*/
	public final EZDCBigDecimalItem              prtUnitCostAmt;

    /** RTL_WH_CD*/
	public final EZDCStringItem              rtlWhCd;

    /** RTL_SWH_CD*/
	public final EZDCStringItem              rtlSwhCd;

    /** A*/
	public final business.blap.NPAL1420.NPAL1420_ACMsgArray              A;

    /** XX_NUM_H*/
	public final EZDCBigDecimalItem              xxNum_H;


	/**
	 * NPAL1420CMsg is constructor.
	 * The initialization when the instance of NPAL1420CMsg is generated.
	 */
	public NPAL1420CMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1420CMsg is constructor.
	 * The initialization when the instance of NPAL1420CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1420CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDCStringItem)newItem("xxModeCd");
		xxModeCd_WH = (EZDCStringItem)newItem("xxModeCd_WH");
		rmnfOrdNum = (EZDCStringItem)newItem("rmnfOrdNum");
		techNm_H = (EZDCStringItem)newItem("techNm_H");
		rmnfMainUnitSerNum = (EZDCStringItem)newItem("rmnfMainUnitSerNum");
		rmnfOrdStsDescTxt = (EZDCStringItem)newItem("rmnfOrdStsDescTxt");
		rmnfTaskPk = (EZDCBigDecimalItem)newItem("rmnfTaskPk");
		xxMsgTxt_WH = (EZDCStringItem)newItem("xxMsgTxt_WH");
		rtlWhCd_H = (EZDCStringItem)newItem("rtlWhCd_H");
		rtlWhNm_H = (EZDCStringItem)newItem("rtlWhNm_H");
		openStsFlg_H = (EZDCStringItem)newItem("openStsFlg_H");
		xxMsgTxt_TS = (EZDCStringItem)newItem("xxMsgTxt_TS");
		rmnfTaskNum = (EZDCStringItem)newItem("rmnfTaskNum");
		rmnfTaskStartDt = (EZDCDateItem)newItem("rmnfTaskStartDt");
		rmnfTaskEndDt = (EZDCDateItem)newItem("rmnfTaskEndDt");
		rmnfTaskDescTxt = (EZDCStringItem)newItem("rmnfTaskDescTxt");
		spclInstnCmntTxt = (EZDCStringItem)newItem("spclInstnCmntTxt");
		techTocCd = (EZDCStringItem)newItem("techTocCd");
		techNm_L = (EZDCStringItem)newItem("techNm_L");
		rmnfLborCmntTxt = (EZDCStringItem)newItem("rmnfLborCmntTxt");
		rmnfLborAot = (EZDCBigDecimalItem)newItem("rmnfLborAot");
		rmnfCostPerHourAmt = (EZDCBigDecimalItem)newItem("rmnfCostPerHourAmt");
		stdCcyCd_PH = (EZDCStringItem)newItem("stdCcyCd_PH");
		rmnfLborCostAmt = (EZDCBigDecimalItem)newItem("rmnfLborCostAmt");
		stdCcyCd_LC = (EZDCStringItem)newItem("stdCcyCd_LC");
		prtUnitCostAmt = (EZDCBigDecimalItem)newItem("prtUnitCostAmt");
		rtlWhCd = (EZDCStringItem)newItem("rtlWhCd");
		rtlSwhCd = (EZDCStringItem)newItem("rtlSwhCd");
		A = (business.blap.NPAL1420.NPAL1420_ACMsgArray)newMsgArray("A");
		xxNum_H = (EZDCBigDecimalItem)newItem("xxNum_H");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1420CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1420CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxModeCd_WH", "xxModeCd_WH", "WH", null, TYPE_HANKAKUEISU, "2", null},
	{"rmnfOrdNum", "rmnfOrdNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"techNm_H", "techNm_H", "H", null, TYPE_HANKAKUEISU, "45", null},
	{"rmnfMainUnitSerNum", "rmnfMainUnitSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rmnfOrdStsDescTxt", "rmnfOrdStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rmnfTaskPk", "rmnfTaskPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgTxt_WH", "xxMsgTxt_WH", "WH", null, TYPE_HANKAKUEISU, "300", null},
	{"rtlWhCd_H", "rtlWhCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_H", "rtlWhNm_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"openStsFlg_H", "openStsFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgTxt_TS", "xxMsgTxt_TS", "TS", null, TYPE_HANKAKUEISU, "300", null},
	{"rmnfTaskNum", "rmnfTaskNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"rmnfTaskStartDt", "rmnfTaskStartDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfTaskEndDt", "rmnfTaskEndDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfTaskDescTxt", "rmnfTaskDescTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"spclInstnCmntTxt", "spclInstnCmntTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"techTocCd", "techTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"techNm_L", "techNm_L", "L", null, TYPE_HANKAKUEISU, "45", null},
	{"rmnfLborCmntTxt", "rmnfLborCmntTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"rmnfLborAot", "rmnfLborAot", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"rmnfCostPerHourAmt", "rmnfCostPerHourAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"stdCcyCd_PH", "stdCcyCd_PH", "PH", null, TYPE_HANKAKUEISU, "3", null},
	{"rmnfLborCostAmt", "rmnfLborCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"stdCcyCd_LC", "stdCcyCd_LC", "LC", null, TYPE_HANKAKUEISU, "3", null},
	{"prtUnitCostAmt", "prtUnitCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"A", "A", null, "99", "business.blap.NPAL1420.NPAL1420_ACMsgArray", null, null},
	
	{"xxNum_H", "xxNum_H", "H", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_WH
        {"RMNF_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdNum
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_H
        {"RMNF_MAIN_UNIT_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMainUnitSerNum
        {"RMNF_ORD_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsDescTxt
        {"RMNF_TASK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskPk
        {"XX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt_WH
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H
        {"OPEN_STS_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openStsFlg_H
        {"XX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt_TS
        {"RMNF_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskNum
        {"RMNF_TASK_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskStartDt
        {"RMNF_TASK_END_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskEndDt
        {"RMNF_TASK_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskDescTxt
        {"SPCL_INSTN_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spclInstnCmntTxt
        {"TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_L
        {"RMNF_LBOR_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborCmntTxt
        {"RMNF_LBOR_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborAot
        {"RMNF_COST_PER_HOUR_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfCostPerHourAmt
        {"STD_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stdCcyCd_PH
        {"RMNF_LBOR_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborCostAmt
        {"STD_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stdCcyCd_LC
        {"PRT_UNIT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtUnitCostAmt
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
		null,	//A
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_H
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
