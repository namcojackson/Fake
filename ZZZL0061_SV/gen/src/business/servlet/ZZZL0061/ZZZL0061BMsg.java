//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20140623111629000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0061BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZZL0061;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0061 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0061BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FLG_NM*/
	public final EZDBStringItem              xxFlgNm;

    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** BAT_PROC_JOB_ID*/
	public final EZDBStringItem              batProcJobId;

    /** BAT_PROC_PGM_ID*/
	public final EZDBStringItem              batProcPgmId;

    /** A*/
	public final business.servlet.ZZZL0061.ZZZL0061_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;


	/**
	 * ZZZL0061BMsg is constructor.
	 * The initialization when the instance of ZZZL0061BMsg is generated.
	 */
	public ZZZL0061BMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0061BMsg is constructor.
	 * The initialization when the instance of ZZZL0061BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0061BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFlgNm = (EZDBStringItem)newItem("xxFlgNm");
		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		batProcJobId = (EZDBStringItem)newItem("batProcJobId");
		batProcPgmId = (EZDBStringItem)newItem("batProcPgmId");
		A = (business.servlet.ZZZL0061.ZZZL0061_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0061BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0061BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFlgNm", "xxFlgNm", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"batProcJobId", "batProcJobId", null, null, TYPE_HANKAKUEISU, "18", null},
	{"batProcPgmId", "batProcPgmId", null, null, TYPE_HANKAKUEISU, "64", null},
	{"A", "A", null, "40", "business.servlet.ZZZL0061.ZZZL0061_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FLG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm
        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"BAT_PROC_JOB_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcJobId
        {"BAT_PROC_PGM_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//batProcPgmId
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
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

