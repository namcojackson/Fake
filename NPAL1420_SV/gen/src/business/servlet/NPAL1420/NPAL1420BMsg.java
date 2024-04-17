//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180202091425000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1420BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1420;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1420 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1420BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDBStringItem              xxModeCd;

    /** XX_MODE_CD_WH*/
	public final EZDBStringItem              xxModeCd_WH;

    /** RMNF_ORD_NUM*/
	public final EZDBStringItem              rmnfOrdNum;

    /** TECH_NM_H*/
	public final EZDBStringItem              techNm_H;

    /** RMNF_MAIN_UNIT_SER_NUM*/
	public final EZDBStringItem              rmnfMainUnitSerNum;

    /** RMNF_ORD_STS_DESC_TXT*/
	public final EZDBStringItem              rmnfOrdStsDescTxt;

    /** RMNF_TASK_PK*/
	public final EZDBBigDecimalItem              rmnfTaskPk;

    /** XX_MSG_TXT_WH*/
	public final EZDBStringItem              xxMsgTxt_WH;

    /** RTL_WH_CD_H*/
	public final EZDBStringItem              rtlWhCd_H;

    /** RTL_WH_NM_H*/
	public final EZDBStringItem              rtlWhNm_H;

    /** OPEN_STS_FLG_H*/
	public final EZDBStringItem              openStsFlg_H;

    /** XX_MSG_TXT_TS*/
	public final EZDBStringItem              xxMsgTxt_TS;

    /** RMNF_TASK_NUM*/
	public final EZDBStringItem              rmnfTaskNum;

    /** RMNF_TASK_START_DT*/
	public final EZDBDateItem              rmnfTaskStartDt;

    /** RMNF_TASK_END_DT*/
	public final EZDBDateItem              rmnfTaskEndDt;

    /** RMNF_TASK_DESC_TXT*/
	public final EZDBStringItem              rmnfTaskDescTxt;

    /** SPCL_INSTN_CMNT_TXT*/
	public final EZDBStringItem              spclInstnCmntTxt;

    /** TECH_TOC_CD_AC*/
	public final EZDBStringItem              techTocCd_AC;

    /** TECH_TOC_CD*/
	public final EZDBStringItem              techTocCd;

    /** TECH_NM_L*/
	public final EZDBStringItem              techNm_L;

    /** RMNF_LBOR_CMNT_TXT*/
	public final EZDBStringItem              rmnfLborCmntTxt;

    /** RMNF_LBOR_AOT*/
	public final EZDBBigDecimalItem              rmnfLborAot;

    /** RMNF_COST_PER_HOUR_AMT*/
	public final EZDBBigDecimalItem              rmnfCostPerHourAmt;

    /** STD_CCY_CD_PH*/
	public final EZDBStringItem              stdCcyCd_PH;

    /** RMNF_LBOR_COST_AMT*/
	public final EZDBBigDecimalItem              rmnfLborCostAmt;

    /** STD_CCY_CD_LC*/
	public final EZDBStringItem              stdCcyCd_LC;

    /** PRT_UNIT_COST_AMT*/
	public final EZDBBigDecimalItem              prtUnitCostAmt;

    /** RTL_WH_CD*/
	public final EZDBStringItem              rtlWhCd;

    /** RTL_SWH_CD*/
	public final EZDBStringItem              rtlSwhCd;

    /** A*/
	public final business.servlet.NPAL1420.NPAL1420_ABMsgArray              A;

    /** XX_POP_PRM_P0*/
	public final EZDBStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDBStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDBStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDBStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDBStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDBStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDBStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDBStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDBStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDBStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDBStringItem              xxPopPrm_PA;

    /** XX_POP_PRM_PB*/
	public final EZDBStringItem              xxPopPrm_PB;

    /** XX_POP_PRM_PC*/
	public final EZDBStringItem              xxPopPrm_PC;

    /** XX_POP_PRM_PD*/
	public final EZDBStringItem              xxPopPrm_PD;

    /** R*/
	public final business.servlet.NPAL1420.NPAL1420_RBMsgArray              R;


	/**
	 * NPAL1420BMsg is constructor.
	 * The initialization when the instance of NPAL1420BMsg is generated.
	 */
	public NPAL1420BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1420BMsg is constructor.
	 * The initialization when the instance of NPAL1420BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1420BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDBStringItem)newItem("xxModeCd");
		xxModeCd_WH = (EZDBStringItem)newItem("xxModeCd_WH");
		rmnfOrdNum = (EZDBStringItem)newItem("rmnfOrdNum");
		techNm_H = (EZDBStringItem)newItem("techNm_H");
		rmnfMainUnitSerNum = (EZDBStringItem)newItem("rmnfMainUnitSerNum");
		rmnfOrdStsDescTxt = (EZDBStringItem)newItem("rmnfOrdStsDescTxt");
		rmnfTaskPk = (EZDBBigDecimalItem)newItem("rmnfTaskPk");
		xxMsgTxt_WH = (EZDBStringItem)newItem("xxMsgTxt_WH");
		rtlWhCd_H = (EZDBStringItem)newItem("rtlWhCd_H");
		rtlWhNm_H = (EZDBStringItem)newItem("rtlWhNm_H");
		openStsFlg_H = (EZDBStringItem)newItem("openStsFlg_H");
		xxMsgTxt_TS = (EZDBStringItem)newItem("xxMsgTxt_TS");
		rmnfTaskNum = (EZDBStringItem)newItem("rmnfTaskNum");
		rmnfTaskStartDt = (EZDBDateItem)newItem("rmnfTaskStartDt");
		rmnfTaskEndDt = (EZDBDateItem)newItem("rmnfTaskEndDt");
		rmnfTaskDescTxt = (EZDBStringItem)newItem("rmnfTaskDescTxt");
		spclInstnCmntTxt = (EZDBStringItem)newItem("spclInstnCmntTxt");
		techTocCd_AC = (EZDBStringItem)newItem("techTocCd_AC");
		techTocCd = (EZDBStringItem)newItem("techTocCd");
		techNm_L = (EZDBStringItem)newItem("techNm_L");
		rmnfLborCmntTxt = (EZDBStringItem)newItem("rmnfLborCmntTxt");
		rmnfLborAot = (EZDBBigDecimalItem)newItem("rmnfLborAot");
		rmnfCostPerHourAmt = (EZDBBigDecimalItem)newItem("rmnfCostPerHourAmt");
		stdCcyCd_PH = (EZDBStringItem)newItem("stdCcyCd_PH");
		rmnfLborCostAmt = (EZDBBigDecimalItem)newItem("rmnfLborCostAmt");
		stdCcyCd_LC = (EZDBStringItem)newItem("stdCcyCd_LC");
		prtUnitCostAmt = (EZDBBigDecimalItem)newItem("prtUnitCostAmt");
		rtlWhCd = (EZDBStringItem)newItem("rtlWhCd");
		rtlSwhCd = (EZDBStringItem)newItem("rtlSwhCd");
		A = (business.servlet.NPAL1420.NPAL1420_ABMsgArray)newMsgArray("A");
		xxPopPrm_P0 = (EZDBStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDBStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDBStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDBStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDBStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDBStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDBStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDBStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDBStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDBStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDBStringItem)newItem("xxPopPrm_PA");
		xxPopPrm_PB = (EZDBStringItem)newItem("xxPopPrm_PB");
		xxPopPrm_PC = (EZDBStringItem)newItem("xxPopPrm_PC");
		xxPopPrm_PD = (EZDBStringItem)newItem("xxPopPrm_PD");
		R = (business.servlet.NPAL1420.NPAL1420_RBMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1420BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1420BMsgArray();
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
	{"techTocCd_AC", "techTocCd_AC", "AC", null, TYPE_HANKAKUEISU, "8", null},
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
	{"A", "A", null, "99", "business.servlet.NPAL1420.NPAL1420_ABMsgArray", null, null},
	
	{"xxPopPrm_P0", "xxPopPrm_P0", "P0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P3", "xxPopPrm_P3", "P3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P4", "xxPopPrm_P4", "P4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P5", "xxPopPrm_P5", "P5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P6", "xxPopPrm_P6", "P6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P7", "xxPopPrm_P7", "P7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P8", "xxPopPrm_P8", "P8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P9", "xxPopPrm_P9", "P9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PA", "xxPopPrm_PA", "PA", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PB", "xxPopPrm_PB", "PB", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PC", "xxPopPrm_PC", "PC", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PD", "xxPopPrm_PD", "PD", null, TYPE_HANKAKUEISU, "300", null},
	{"R", "R", null, "10", "business.servlet.NPAL1420.NPAL1420_RBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"XX_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_WH
        {"RMNF_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdNum
        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_H
        {"RMNF_MAIN_UNIT_SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMainUnitSerNum
        {"RMNF_ORD_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsDescTxt
        {"RMNF_TASK_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskPk
        {"XX_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt_WH
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H
        {"OPEN_STS_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openStsFlg_H
        {"XX_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgTxt_TS
        {"RMNF_TASK_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskNum
        {"RMNF_TASK_START_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rmnfTaskStartDt
        {"RMNF_TASK_END_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rmnfTaskEndDt
        {"RMNF_TASK_DESC_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfTaskDescTxt
        {"SPCL_INSTN_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spclInstnCmntTxt
        {"TECH_TOC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd_AC
        {"TECH_TOC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd
        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_L
        {"RMNF_LBOR_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborCmntTxt
        {"RMNF_LBOR_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborAot
        {"RMNF_COST_PER_HOUR_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfCostPerHourAmt
        {"STD_CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stdCcyCd_PH
        {"RMNF_LBOR_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfLborCostAmt
        {"STD_CCY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stdCcyCd_LC
        {"PRT_UNIT_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtUnitCostAmt
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PB
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PC
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PD
		null,	//R
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
