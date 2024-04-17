//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160601110830000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6880SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6880;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6880 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6880SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** VND_SHIP_TO_CUST_CD*/
	public final EZDSStringItem              vndShipToCustCd;

    /** RTL_WH_CD*/
	public final EZDSStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDSStringItem              rtlWhNm;

    /** XX_BTN_FLG*/
	public final EZDSStringItem              xxBtnFlg;

    /** A*/
	public final business.blap.NMAL6880.NMAL6880_ASMsgArray              A;

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

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_FILE_DATA*/
	public final EZDSMimeSourceItem              xxFileData;


	/**
	 * NMAL6880SMsg is constructor.
	 * The initialization when the instance of NMAL6880SMsg is generated.
	 */
	public NMAL6880SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6880SMsg is constructor.
	 * The initialization when the instance of NMAL6880SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6880SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		vndShipToCustCd = (EZDSStringItem)newItem("vndShipToCustCd");
		rtlWhCd = (EZDSStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDSStringItem)newItem("rtlWhNm");
		xxBtnFlg = (EZDSStringItem)newItem("xxBtnFlg");
		A = (business.blap.NMAL6880.NMAL6880_ASMsgArray)newMsgArray("A");
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
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxFileData = (EZDSMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6880SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6880SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"vndShipToCustCd", "vndShipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxBtnFlg", "xxBtnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "1000", "business.blap.NMAL6880.NMAL6880_ASMsgArray", null, null},
	
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
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"VND_SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndShipToCustCd
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg
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
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

