//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180605185915000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0170SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0170 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0170SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** BILL_TO_CUST_ACCT_NM_H*/
	public final EZDSStringItem              billToCustAcctNm_H;

    /** BILL_TO_CUST_ACCT_CD_H*/
	public final EZDSStringItem              billToCustAcctCd_H;

    /** SHIP_TO_LOC_NM_H*/
	public final EZDSStringItem              shipToLocNm_H;

    /** SHIP_TO_CUST_CD_H*/
	public final EZDSStringItem              shipToCustCd_H;

    /** INV_NUM_FR*/
	public final EZDSStringItem              invNum_FR;

    /** INV_NUM_TO*/
	public final EZDSStringItem              invNum_TO;

    /** SRC_SYS_DOC_NUM*/
	public final EZDSStringItem              srcSysDocNum;

    /** MDL_NM_H*/
	public final EZDSStringItem              mdlNm_H;

    /** GRP_INV_NUM_H*/
	public final EZDSStringItem              grpInvNum_H;

    /** CUST_ISS_PO_NUM_H*/
	public final EZDSStringItem              custIssPoNum_H;

    /** XX_CHK_BOX_CL*/
	public final EZDSStringItem              xxChkBox_CL;

    /** SER_NUM_H*/
	public final EZDSStringItem              serNum_H;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

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

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NFDL0170.NFDL0170_ASMsgArray              A;

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

    /** XX_POP_PRM_PO*/
	public final EZDSStringItem              xxPopPrm_PO;


	/**
	 * NFDL0170SMsg is constructor.
	 * The initialization when the instance of NFDL0170SMsg is generated.
	 */
	public NFDL0170SMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0170SMsg is constructor.
	 * The initialization when the instance of NFDL0170SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0170SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		billToCustAcctNm_H = (EZDSStringItem)newItem("billToCustAcctNm_H");
		billToCustAcctCd_H = (EZDSStringItem)newItem("billToCustAcctCd_H");
		shipToLocNm_H = (EZDSStringItem)newItem("shipToLocNm_H");
		shipToCustCd_H = (EZDSStringItem)newItem("shipToCustCd_H");
		invNum_FR = (EZDSStringItem)newItem("invNum_FR");
		invNum_TO = (EZDSStringItem)newItem("invNum_TO");
		srcSysDocNum = (EZDSStringItem)newItem("srcSysDocNum");
		mdlNm_H = (EZDSStringItem)newItem("mdlNm_H");
		grpInvNum_H = (EZDSStringItem)newItem("grpInvNum_H");
		custIssPoNum_H = (EZDSStringItem)newItem("custIssPoNum_H");
		xxChkBox_CL = (EZDSStringItem)newItem("xxChkBox_CL");
		serNum_H = (EZDSStringItem)newItem("serNum_H");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDSBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDSBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NFDL0170.NFDL0170_ASMsgArray)newMsgArray("A");
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
		xxPopPrm_PO = (EZDSStringItem)newItem("xxPopPrm_PO");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0170SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0170SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"billToCustAcctNm_H", "billToCustAcctNm_H", "H", null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustAcctCd_H", "billToCustAcctCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToLocNm_H", "shipToLocNm_H", "H", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToCustCd_H", "shipToCustCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"invNum_FR", "invNum_FR", "FR", null, TYPE_HANKAKUEISU, "13", null},
	{"invNum_TO", "invNum_TO", "TO", null, TYPE_HANKAKUEISU, "13", null},
	{"srcSysDocNum", "srcSysDocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mdlNm_H", "mdlNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"grpInvNum_H", "grpInvNum_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"custIssPoNum_H", "custIssPoNum_H", "H", null, TYPE_HANKAKUEISU, "35", null},
	{"xxChkBox_CL", "xxChkBox_CL", "CL", null, TYPE_HANKAKUEIJI, "1", null},
	{"serNum_H", "serNum_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "9999", "business.blap.NFDL0170.NFDL0170_ASMsgArray", null, null},
	
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
	{"xxPopPrm_PO", "xxPopPrm_PO", "PO", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"BILL_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctNm_H
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_H
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_H
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_H
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_FR
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_TO
        {"SRC_SYS_DOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcSysDocNum
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_H
        {"GRP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum_H
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_H
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_CL
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
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
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PO
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

