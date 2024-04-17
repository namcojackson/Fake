//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160518092728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7150CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7150CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** EFF_FROM_DT_TD*/
	public final EZDCDateItem              effFromDt_TD;

    /** EFF_THRU_DT_TD*/
	public final EZDCDateItem              effThruDt_TD;

    /** XX_CHK_BOX_E*/
	public final EZDCStringItem              xxChkBox_E;

    /** XX_CHK_BOX_R*/
	public final EZDCStringItem              xxChkBox_R;

    /** XX_CHK_BOX_D*/
	public final EZDCStringItem              xxChkBox_D;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NMAL7150.NMAL7150_ACMsgArray              A;


	/**
	 * NMAL7150CMsg is constructor.
	 * The initialization when the instance of NMAL7150CMsg is generated.
	 */
	public NMAL7150CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7150CMsg is constructor.
	 * The initialization when the instance of NMAL7150CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7150CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		effFromDt_TD = (EZDCDateItem)newItem("effFromDt_TD");
		effThruDt_TD = (EZDCDateItem)newItem("effThruDt_TD");
		xxChkBox_E = (EZDCStringItem)newItem("xxChkBox_E");
		xxChkBox_R = (EZDCStringItem)newItem("xxChkBox_R");
		xxChkBox_D = (EZDCStringItem)newItem("xxChkBox_D");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NMAL7150.NMAL7150_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7150CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7150CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"effFromDt_TD", "effFromDt_TD", "TD", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_TD", "effThruDt_TD", "TD", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_E", "xxChkBox_E", "E", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_R", "xxChkBox_R", "R", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_D", "xxChkBox_D", "D", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "40", "business.blap.NMAL7150.NMAL7150_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_TD
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_TD
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_E
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_R
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
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
