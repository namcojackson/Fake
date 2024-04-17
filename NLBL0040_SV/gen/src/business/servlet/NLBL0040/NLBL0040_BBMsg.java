//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151005112546000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0040 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDBStringItem              xxChkBox_B1;

    /** FROM_ZIP_CD_B1*/
	public final EZDBStringItem              fromZipCd_B1;

    /** TO_ZIP_CD_B1*/
	public final EZDBStringItem              toZipCd_B1;

    /** TRNSP_LT_AOT_B1*/
	public final EZDBBigDecimalItem              trnspLtAot_B1;

    /** _EZCancelFlag_B1*/
	public final EZDBStringItem              ezCancelFlag_B1;

    /** ST_CD_B1*/
	public final EZDBStringItem              stCd_B1;

    /** SHPG_MODE_CD_B1*/
	public final EZDBStringItem              shpgModeCd_B1;

    /** ZIP_SQ_NUM_B1*/
	public final EZDBStringItem              zipSqNum_B1;

    /** EFF_FROM_DT_B1*/
	public final EZDBDateItem              effFromDt_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDBStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDBStringItem              ezUpTimeZone_B1;


	/**
	 * NLBL0040_BBMsg is constructor.
	 * The initialization when the instance of NLBL0040_BBMsg is generated.
	 */
	public NLBL0040_BBMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040_BBMsg is constructor.
	 * The initialization when the instance of NLBL0040_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDBStringItem)newItem("xxChkBox_B1");
		fromZipCd_B1 = (EZDBStringItem)newItem("fromZipCd_B1");
		toZipCd_B1 = (EZDBStringItem)newItem("toZipCd_B1");
		trnspLtAot_B1 = (EZDBBigDecimalItem)newItem("trnspLtAot_B1");
		ezCancelFlag_B1 = (EZDBStringItem)newItem("ezCancelFlag_B1");
		stCd_B1 = (EZDBStringItem)newItem("stCd_B1");
		shpgModeCd_B1 = (EZDBStringItem)newItem("shpgModeCd_B1");
		zipSqNum_B1 = (EZDBStringItem)newItem("zipSqNum_B1");
		effFromDt_B1 = (EZDBDateItem)newItem("effFromDt_B1");
		ezUpTime_B1 = (EZDBStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDBStringItem)newItem("ezUpTimeZone_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"fromZipCd_B1", "fromZipCd_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"toZipCd_B1", "toZipCd_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"trnspLtAot_B1", "trnspLtAot_B1", "B1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ezCancelFlag_B1", "ezCancelFlag_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"stCd_B1", "stCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgModeCd_B1", "shpgModeCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"zipSqNum_B1", "zipSqNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"effFromDt_B1", "effFromDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"FROM_ZIP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromZipCd_B1
        {"TO_ZIP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toZipCd_B1
        {"TRNSP_LT_AOT", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//trnspLtAot_B1
        {"_EZCancelFlag",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCancelFlag_B1
        {"ST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B1
        {"SHPG_MODE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_B1
        {"ZIP_SQ_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zipSqNum_B1
        {"EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//effFromDt_B1
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
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

