//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160225103634000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0360BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0360 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0360BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** ORG_CD_HL*/
	public final EZDBStringItem              orgCd_HL;

    /** ORG_CD_HT*/
	public final EZDBStringItem              orgCd_HT;

    /** CRAT_DT_H*/
	public final EZDBDateItem              cratDt_H;

    /** L*/
	public final business.servlet.NSBL0360.NSBL0360_LBMsgArray              L;

    /** B*/
	public final business.servlet.NSBL0360.NSBL0360_BBMsgArray              B;

    /** A*/
	public final business.servlet.NSBL0360.NSBL0360_ABMsgArray              A;

    /** XX_POP_PRM_0*/
	public final EZDBStringItem              xxPopPrm_0;

    /** XX_POP_PRM_1*/
	public final EZDBStringItem              xxPopPrm_1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;


	/**
	 * NSBL0360BMsg is constructor.
	 * The initialization when the instance of NSBL0360BMsg is generated.
	 */
	public NSBL0360BMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0360BMsg is constructor.
	 * The initialization when the instance of NSBL0360BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0360BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		orgCd_HL = (EZDBStringItem)newItem("orgCd_HL");
		orgCd_HT = (EZDBStringItem)newItem("orgCd_HT");
		cratDt_H = (EZDBDateItem)newItem("cratDt_H");
		L = (business.servlet.NSBL0360.NSBL0360_LBMsgArray)newMsgArray("L");
		B = (business.servlet.NSBL0360.NSBL0360_BBMsgArray)newMsgArray("B");
		A = (business.servlet.NSBL0360.NSBL0360_ABMsgArray)newMsgArray("A");
		xxPopPrm_0 = (EZDBStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDBStringItem)newItem("xxPopPrm_1");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0360BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0360BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"orgCd_HL", "orgCd_HL", "HL", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_HT", "orgCd_HT", "HT", null, TYPE_HANKAKUEISU, "8", null},
	{"cratDt_H", "cratDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"L", "L", null, "50", "business.servlet.NSBL0360.NSBL0360_LBMsgArray", null, null},
	
	{"B", "B", null, "50", "business.servlet.NSBL0360.NSBL0360_BBMsgArray", null, null},
	
	{"A", "A", null, "50", "business.servlet.NSBL0360.NSBL0360_ABMsgArray", null, null},
	
	{"xxPopPrm_0", "xxPopPrm_0", "0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_1", "xxPopPrm_1", "1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_HL
        {"ORG_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_HT
        {"CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cratDt_H
		null,	//L
		null,	//B
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
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

