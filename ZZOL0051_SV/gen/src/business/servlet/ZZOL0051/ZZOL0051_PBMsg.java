//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20091006193134000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0051_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0051;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0051 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0051_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_P*/
	public final EZDBBigDecimalItem              xxNum_P;

    /** XX_CHK_BOX_P*/
	public final EZDBStringItem              xxChkBox_P;

    /** UPLD_CSV_RST_PROC_NM_P*/
	public final EZDBStringItem              upldCsvRstProcNm_P;

    /** MENU_PROC_ID_P*/
	public final EZDBStringItem              menuProcId_P;


	/**
	 * ZZOL0051_PBMsg is constructor.
	 * The initialization when the instance of ZZOL0051_PBMsg is generated.
	 */
	public ZZOL0051_PBMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0051_PBMsg is constructor.
	 * The initialization when the instance of ZZOL0051_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0051_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_P = (EZDBBigDecimalItem)newItem("xxNum_P");
		xxChkBox_P = (EZDBStringItem)newItem("xxChkBox_P");
		upldCsvRstProcNm_P = (EZDBStringItem)newItem("upldCsvRstProcNm_P");
		menuProcId_P = (EZDBStringItem)newItem("menuProcId_P");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0051_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0051_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_P", "xxNum_P", "P", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_P", "xxChkBox_P", "P", null, TYPE_HANKAKUEIJI, "1", null},
	{"upldCsvRstProcNm_P", "upldCsvRstProcNm_P", "P", null, TYPE_HANKAKUEISU, "100", null},
	{"menuProcId_P", "menuProcId_P", "P", null, TYPE_HANKAKUEISU, "18", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_P
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_P
        {"UPLD_CSV_RST_PROC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRstProcNm_P
        {"MENU_PROC_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId_P
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
