//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220801144058000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2620SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2620;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2620 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2620SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** AR_RF_RQST_PK_H*/
	public final EZDSBigDecimalItem              arRfRqstPk_H;

    /** AR_RF_TP_CD_C*/
	public final EZDSStringItemArray              arRfTpCd_C;

    /** AR_RF_TP_DESC_TXT_D*/
	public final EZDSStringItemArray              arRfTpDescTxt_D;

    /** AR_RF_TP_CD_H*/
	public final EZDSStringItem              arRfTpCd_H;

    /** BILL_TO_CUST_ACCT_CD_H*/
	public final EZDSStringItem              billToCustAcctCd_H;

    /** DS_ACCT_NM_H*/
	public final EZDSStringItem              dsAcctNm_H;

    /** AR_RF_STS_CD_C*/
	public final EZDSStringItemArray              arRfStsCd_C;

    /** AR_RF_STS_DESC_TXT_D*/
	public final EZDSStringItemArray              arRfStsDescTxt_D;

    /** AR_RF_STS_CD_H*/
	public final EZDSStringItem              arRfStsCd_H;

    /** AR_RF_CRAT_DT_F*/
	public final EZDSDateItem              arRfCratDt_F;

    /** AR_RF_CRAT_DT_T*/
	public final EZDSDateItem              arRfCratDt_T;

    /** AP_PMT_CHK_NUM_H*/
	public final EZDSStringItem              apPmtChkNum_H;

    /** DEAL_RF_AMT_H*/
	public final EZDSBigDecimalItem              dealRfAmt_H;

    /** A*/
	public final business.blap.NFCL2620.NFCL2620_ASMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDSBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDSBigDecimalItem              xxPageShowTotNum;

    /** XX_POP_PRM_P0*/
	public final EZDSStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDSStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDSStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDSStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDSStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDSStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDSStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDSStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDSStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDSStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDSStringItem              xxPopPrm_PA;

    /** XX_POP_PRM_PB*/
	public final EZDSStringItem              xxPopPrm_PB;

    /** XX_POP_PRM_PC*/
	public final EZDSStringItem              xxPopPrm_PC;

    /** XX_POP_PRM_PD*/
	public final EZDSStringItem              xxPopPrm_PD;

    /** XX_POP_PRM_PE*/
	public final EZDSStringItem              xxPopPrm_PE;

    /** XX_POP_PRM_PF*/
	public final EZDSStringItem              xxPopPrm_PF;

    /** XX_POP_PRM_PG*/
	public final EZDSStringItem              xxPopPrm_PG;

    /** XX_POP_PRM_PH*/
	public final EZDSStringItem              xxPopPrm_PH;

    /** XX_POP_PRM_PI*/
	public final EZDSStringItem              xxPopPrm_PI;

    /** XX_POP_PRM_PJ*/
	public final EZDSStringItem              xxPopPrm_PJ;

    /** XX_POP_PRM_PK*/
	public final EZDSStringItem              xxPopPrm_PK;

    /** XX_POP_PRM_PL*/
	public final EZDSStringItem              xxPopPrm_PL;

    /** XX_POP_PRM_PM*/
	public final EZDSStringItem              xxPopPrm_PM;

    /** XX_POP_PRM_PN*/
	public final EZDSStringItem              xxPopPrm_PN;

    /** XX_SCR_EVENT_NM*/
	public final EZDSStringItem              xxScrEventNm;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;


	/**
	 * NFCL2620SMsg is constructor.
	 * The initialization when the instance of NFCL2620SMsg is generated.
	 */
	public NFCL2620SMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2620SMsg is constructor.
	 * The initialization when the instance of NFCL2620SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2620SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		arRfRqstPk_H = (EZDSBigDecimalItem)newItem("arRfRqstPk_H");
		arRfTpCd_C = (EZDSStringItemArray)newItemArray("arRfTpCd_C");
		arRfTpDescTxt_D = (EZDSStringItemArray)newItemArray("arRfTpDescTxt_D");
		arRfTpCd_H = (EZDSStringItem)newItem("arRfTpCd_H");
		billToCustAcctCd_H = (EZDSStringItem)newItem("billToCustAcctCd_H");
		dsAcctNm_H = (EZDSStringItem)newItem("dsAcctNm_H");
		arRfStsCd_C = (EZDSStringItemArray)newItemArray("arRfStsCd_C");
		arRfStsDescTxt_D = (EZDSStringItemArray)newItemArray("arRfStsDescTxt_D");
		arRfStsCd_H = (EZDSStringItem)newItem("arRfStsCd_H");
		arRfCratDt_F = (EZDSDateItem)newItem("arRfCratDt_F");
		arRfCratDt_T = (EZDSDateItem)newItem("arRfCratDt_T");
		apPmtChkNum_H = (EZDSStringItem)newItem("apPmtChkNum_H");
		dealRfAmt_H = (EZDSBigDecimalItem)newItem("dealRfAmt_H");
		A = (business.blap.NFCL2620.NFCL2620_ASMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDSBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDSBigDecimalItem)newItem("xxPageShowTotNum");
		xxPopPrm_P0 = (EZDSStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDSStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDSStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDSStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDSStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDSStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDSStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDSStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDSStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDSStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDSStringItem)newItem("xxPopPrm_PA");
		xxPopPrm_PB = (EZDSStringItem)newItem("xxPopPrm_PB");
		xxPopPrm_PC = (EZDSStringItem)newItem("xxPopPrm_PC");
		xxPopPrm_PD = (EZDSStringItem)newItem("xxPopPrm_PD");
		xxPopPrm_PE = (EZDSStringItem)newItem("xxPopPrm_PE");
		xxPopPrm_PF = (EZDSStringItem)newItem("xxPopPrm_PF");
		xxPopPrm_PG = (EZDSStringItem)newItem("xxPopPrm_PG");
		xxPopPrm_PH = (EZDSStringItem)newItem("xxPopPrm_PH");
		xxPopPrm_PI = (EZDSStringItem)newItem("xxPopPrm_PI");
		xxPopPrm_PJ = (EZDSStringItem)newItem("xxPopPrm_PJ");
		xxPopPrm_PK = (EZDSStringItem)newItem("xxPopPrm_PK");
		xxPopPrm_PL = (EZDSStringItem)newItem("xxPopPrm_PL");
		xxPopPrm_PM = (EZDSStringItem)newItem("xxPopPrm_PM");
		xxPopPrm_PN = (EZDSStringItem)newItem("xxPopPrm_PN");
		xxScrEventNm = (EZDSStringItem)newItem("xxScrEventNm");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2620SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2620SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"arRfRqstPk_H", "arRfRqstPk_H", "H", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arRfTpCd_C", "arRfTpCd_C", "C", "99", TYPE_HANKAKUEISU, "2", null},
	{"arRfTpDescTxt_D", "arRfTpDescTxt_D", "D", "99", TYPE_HANKAKUEISU, "50", null},
	{"arRfTpCd_H", "arRfTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"billToCustAcctCd_H", "billToCustAcctCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H", "dsAcctNm_H", "H", null, TYPE_HANKAKUEISU, "360", null},
	{"arRfStsCd_C", "arRfStsCd_C", "C", "99", TYPE_HANKAKUEISU, "2", null},
	{"arRfStsDescTxt_D", "arRfStsDescTxt_D", "D", "99", TYPE_HANKAKUEISU, "50", null},
	{"arRfStsCd_H", "arRfStsCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"arRfCratDt_F", "arRfCratDt_F", "F", null, TYPE_NENTSUKIHI, "8", null},
	{"arRfCratDt_T", "arRfCratDt_T", "T", null, TYPE_NENTSUKIHI, "8", null},
	{"apPmtChkNum_H", "apPmtChkNum_H", "H", null, TYPE_HANKAKUEISU, "15", null},
	{"dealRfAmt_H", "dealRfAmt_H", "H", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"A", "A", null, "1000", "business.blap.NFCL2620.NFCL2620_ASMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
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
	{"xxPopPrm_PE", "xxPopPrm_PE", "PE", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PF", "xxPopPrm_PF", "PF", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PG", "xxPopPrm_PG", "PG", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PH", "xxPopPrm_PH", "PH", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PI", "xxPopPrm_PI", "PI", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PJ", "xxPopPrm_PJ", "PJ", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PK", "xxPopPrm_PK", "PK", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PL", "xxPopPrm_PL", "PL", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PM", "xxPopPrm_PM", "PM", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PN", "xxPopPrm_PN", "PN", null, TYPE_HANKAKUEISU, "300", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"AR_RF_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfRqstPk_H
        {"AR_RF_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfTpCd_C
        {"AR_RF_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfTpDescTxt_D
        {"AR_RF_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfTpCd_H
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_H
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H
        {"AR_RF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfStsCd_C
        {"AR_RF_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfStsDescTxt_D
        {"AR_RF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfStsCd_H
        {"AR_RF_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfCratDt_F
        {"AR_RF_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRfCratDt_T
        {"AP_PMT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apPmtChkNum_H
        {"DEAL_RF_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRfAmt_H
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PB
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PC
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PD
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PE
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PF
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PG
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PH
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PI
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PJ
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PK
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PL
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PM
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PN
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
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

