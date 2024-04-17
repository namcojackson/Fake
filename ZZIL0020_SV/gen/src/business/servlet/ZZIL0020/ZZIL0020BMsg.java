//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091015175828000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0020BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZIL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0020BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TBL_NM*/
	public final EZDBStringItem              xxTblNm;

    /** XX_TBL_NM_C*/
	public final EZDBStringItemArray              xxTblNm_C;

    /** XX_TBL_NM_D*/
	public final EZDBStringItemArray              xxTblNm_D;

    /** XX_UPLD_FILE_NM*/
	public final EZDBStringItem              xxUpldFileNm;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** X*/
	public final business.servlet.ZZIL0020.ZZIL0020_XBMsgArray              X;


	/**
	 * ZZIL0020BMsg is constructor.
	 * The initialization when the instance of ZZIL0020BMsg is generated.
	 */
	public ZZIL0020BMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0020BMsg is constructor.
	 * The initialization when the instance of ZZIL0020BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0020BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTblNm = (EZDBStringItem)newItem("xxTblNm");
		xxTblNm_C = (EZDBStringItemArray)newItemArray("xxTblNm_C");
		xxTblNm_D = (EZDBStringItemArray)newItemArray("xxTblNm_D");
		xxUpldFileNm = (EZDBStringItem)newItem("xxUpldFileNm");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		X = (business.servlet.ZZIL0020.ZZIL0020_XBMsgArray)newMsgArray("X");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0020BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0020BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNm_C", "xxTblNm_C", "C", "5", TYPE_HANKAKUEISU, "30", null},
	{"xxTblNm_D", "xxTblNm_D", "D", "5", TYPE_HANKAKUEISU, "30", null},
	{"xxUpldFileNm", "xxUpldFileNm", null, null, TYPE_KONZAI, "256", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"X", "X", null, "40", "business.servlet.ZZIL0020.ZZIL0020_XBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
        {"XX_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_C
        {"XX_TBL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_D
        {"XX_UPLD_FILE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUpldFileNm
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//X
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
