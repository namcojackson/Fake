//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20091006193113000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0051_PSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZOL0051;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0051 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0051_PSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_P*/
	public final EZDSBigDecimalItem              xxNum_P;

    /** UPLD_CSV_RST_PROC_NM_P*/
	public final EZDSStringItem              upldCsvRstProcNm_P;

    /** MENU_PROC_ID_P*/
	public final EZDSStringItem              menuProcId_P;

    /** _EZUpdateDateTime_P*/
	public final EZDSStringItem              ezUpTime_P;

    /** _EZUpTimeZone_P*/
	public final EZDSStringItem              ezUpTimeZone_P;


	/**
	 * ZZOL0051_PSMsg is constructor.
	 * The initialization when the instance of ZZOL0051_PSMsg is generated.
	 */
	public ZZOL0051_PSMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0051_PSMsg is constructor.
	 * The initialization when the instance of ZZOL0051_PSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0051_PSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_P = (EZDSBigDecimalItem)newItem("xxNum_P");
		upldCsvRstProcNm_P = (EZDSStringItem)newItem("upldCsvRstProcNm_P");
		menuProcId_P = (EZDSStringItem)newItem("menuProcId_P");
		ezUpTime_P = (EZDSStringItem)newItem("ezUpTime_P");
		ezUpTimeZone_P = (EZDSStringItem)newItem("ezUpTimeZone_P");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0051_PSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0051_PSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_P", "xxNum_P", "P", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"upldCsvRstProcNm_P", "upldCsvRstProcNm_P", "P", null, TYPE_HANKAKUEISU, "100", null},
	{"menuProcId_P", "menuProcId_P", "P", null, TYPE_HANKAKUEISU, "18", null},
	{"ezUpTime_P", "ezUpTime_P", "P", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_P", "ezUpTimeZone_P", "P", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_P
        {"UPLD_CSV_RST_PROC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvRstProcNm_P
        {"MENU_PROC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//menuProcId_P
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_P
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_P
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
