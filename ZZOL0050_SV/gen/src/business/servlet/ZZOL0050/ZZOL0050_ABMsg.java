//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20100520113000000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZOL0050_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZOL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZOL0050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZOL0050_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** UPLD_CSV_ID_A*/
	public final EZDBStringItem              upldCsvId_A;

    /** UPLD_CSV_NM_A*/
	public final EZDBStringItem              upldCsvNm_A;

    /** UPLD_CSV_FILE_ID_A*/
	public final EZDBStringItem              upldCsvFileId_A;

    /** UPLD_CSV_TEMP_TBL_ID_A*/
	public final EZDBStringItem              upldCsvTempTblId_A;

    /** _EZREQBusinessApplicationID_A*/
	public final EZDBStringItem              ezReqBusinessID_A;


	/**
	 * ZZOL0050_ABMsg is constructor.
	 * The initialization when the instance of ZZOL0050_ABMsg is generated.
	 */
	public ZZOL0050_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZOL0050_ABMsg is constructor.
	 * The initialization when the instance of ZZOL0050_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZOL0050_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		upldCsvId_A = (EZDBStringItem)newItem("upldCsvId_A");
		upldCsvNm_A = (EZDBStringItem)newItem("upldCsvNm_A");
		upldCsvFileId_A = (EZDBStringItem)newItem("upldCsvFileId_A");
		upldCsvTempTblId_A = (EZDBStringItem)newItem("upldCsvTempTblId_A");
		ezReqBusinessID_A = (EZDBStringItem)newItem("ezReqBusinessID_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZOL0050_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZOL0050_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"upldCsvId_A", "upldCsvId_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"upldCsvNm_A", "upldCsvNm_A", "A", null, TYPE_HANKAKUEISU, "64", null},
	{"upldCsvFileId_A", "upldCsvFileId_A", "A", null, TYPE_HANKAKUEISU, "11", null},
	{"upldCsvTempTblId_A", "upldCsvTempTblId_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"ezReqBusinessID_A", "ezReqBusinessID_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"UPLD_CSV_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvId_A
        {"UPLD_CSV_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvNm_A
        {"UPLD_CSV_FILE_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvFileId_A
        {"UPLD_CSV_TEMP_TBL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvTempTblId_A
        {"_EZREQBusinessApplicationID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessID_A
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
