//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130530053716000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0040 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** FROM_ZIP_CD_B1*/
	public final EZDSStringItem              fromZipCd_B1;

    /** TO_ZIP_CD_B1*/
	public final EZDSStringItem              toZipCd_B1;

    /** TRNSP_LT_AOT_B1*/
	public final EZDSBigDecimalItem              trnspLtAot_B1;

    /** _EZCancelFlag_B1*/
	public final EZDSStringItem              ezCancelFlag_B1;

    /** ST_CD_B1*/
	public final EZDSStringItem              stCd_B1;

    /** SHPG_MODE_CD_B1*/
	public final EZDSStringItem              shpgModeCd_B1;

    /** ZIP_SQ_NUM_B1*/
	public final EZDSStringItem              zipSqNum_B1;

    /** EFF_FROM_DT_B1*/
	public final EZDSDateItem              effFromDt_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;


	/**
	 * NLBL0040_BSMsg is constructor.
	 * The initialization when the instance of NLBL0040_BSMsg is generated.
	 */
	public NLBL0040_BSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040_BSMsg is constructor.
	 * The initialization when the instance of NLBL0040_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		fromZipCd_B1 = (EZDSStringItem)newItem("fromZipCd_B1");
		toZipCd_B1 = (EZDSStringItem)newItem("toZipCd_B1");
		trnspLtAot_B1 = (EZDSBigDecimalItem)newItem("trnspLtAot_B1");
		ezCancelFlag_B1 = (EZDSStringItem)newItem("ezCancelFlag_B1");
		stCd_B1 = (EZDSStringItem)newItem("stCd_B1");
		shpgModeCd_B1 = (EZDSStringItem)newItem("shpgModeCd_B1");
		zipSqNum_B1 = (EZDSStringItem)newItem("zipSqNum_B1");
		effFromDt_B1 = (EZDSDateItem)newItem("effFromDt_B1");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040_BSMsgArray();
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"FROM_ZIP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromZipCd_B1
        {"TO_ZIP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toZipCd_B1
        {"TRNSP_LT_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trnspLtAot_B1
        {"_EZCancelFlag",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCancelFlag_B1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B1
        {"SHPG_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_B1
        {"ZIP_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zipSqNum_B1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
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

