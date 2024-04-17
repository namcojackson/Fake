//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160330113129000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0350BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0350;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0350 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0350BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** ORG_CD_HL*/
	public final EZDBStringItem              orgCd_HL;

    /** ORG_CD_HT*/
	public final EZDBStringItem              orgCd_HT;

    /** ORG_DESC_TXT_H*/
	public final EZDBStringItem              orgDescTxt_H;

    /** XX_FROM_DT_H*/
	public final EZDBDateItem              xxFromDt_H;

    /** XX_FROM_DT_P*/
	public final EZDBDateItem              xxFromDt_P;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** L*/
	public final business.servlet.NSBL0350.NSBL0350_LBMsgArray              L;

    /** A*/
	public final business.servlet.NSBL0350.NSBL0350_ABMsgArray              A;

    /** XX_POP_PRM_0*/
	public final EZDBStringItem              xxPopPrm_0;

    /** XX_POP_PRM_1*/
	public final EZDBStringItem              xxPopPrm_1;

    /** XX_POP_PRM_2*/
	public final EZDBStringItem              xxPopPrm_2;

    /** XX_POP_PRM_3*/
	public final EZDBStringItem              xxPopPrm_3;

    /** XX_POP_PRM_4*/
	public final EZDBStringItem              xxPopPrm_4;

    /** XX_POP_PRM_5*/
	public final EZDBStringItem              xxPopPrm_5;

    /** XX_POP_PRM_6*/
	public final EZDBStringItem              xxPopPrm_6;


	/**
	 * NSBL0350BMsg is constructor.
	 * The initialization when the instance of NSBL0350BMsg is generated.
	 */
	public NSBL0350BMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0350BMsg is constructor.
	 * The initialization when the instance of NSBL0350BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0350BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		orgCd_HL = (EZDBStringItem)newItem("orgCd_HL");
		orgCd_HT = (EZDBStringItem)newItem("orgCd_HT");
		orgDescTxt_H = (EZDBStringItem)newItem("orgDescTxt_H");
		xxFromDt_H = (EZDBDateItem)newItem("xxFromDt_H");
		xxFromDt_P = (EZDBDateItem)newItem("xxFromDt_P");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		L = (business.servlet.NSBL0350.NSBL0350_LBMsgArray)newMsgArray("L");
		A = (business.servlet.NSBL0350.NSBL0350_ABMsgArray)newMsgArray("A");
		xxPopPrm_0 = (EZDBStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDBStringItem)newItem("xxPopPrm_1");
		xxPopPrm_2 = (EZDBStringItem)newItem("xxPopPrm_2");
		xxPopPrm_3 = (EZDBStringItem)newItem("xxPopPrm_3");
		xxPopPrm_4 = (EZDBStringItem)newItem("xxPopPrm_4");
		xxPopPrm_5 = (EZDBStringItem)newItem("xxPopPrm_5");
		xxPopPrm_6 = (EZDBStringItem)newItem("xxPopPrm_6");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0350BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0350BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"orgCd_HL", "orgCd_HL", "HL", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_HT", "orgCd_HT", "HT", null, TYPE_HANKAKUEISU, "8", null},
	{"orgDescTxt_H", "orgDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"xxFromDt_H", "xxFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFromDt_P", "xxFromDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"L", "L", null, "50", "business.servlet.NSBL0350.NSBL0350_LBMsgArray", null, null},
	
	{"A", "A", null, "50", "business.servlet.NSBL0350.NSBL0350_ABMsgArray", null, null},
	
	{"xxPopPrm_0", "xxPopPrm_0", "0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_1", "xxPopPrm_1", "1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_2", "xxPopPrm_2", "2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_3", "xxPopPrm_3", "3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_4", "xxPopPrm_4", "4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_5", "xxPopPrm_5", "5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_6", "xxPopPrm_6", "6", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_HL
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_HT
        {"ORG_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_H
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_H
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt_P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//L
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_6
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
