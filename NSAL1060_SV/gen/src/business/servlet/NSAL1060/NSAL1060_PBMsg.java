//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151127115048000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1060_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1060_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_PHYS_MTR_PK_P*/
	public final EZDBBigDecimalItem              svcPhysMtrPk_P;

    /** MTR_LB_CD_P*/
	public final EZDBStringItem              mtrLbCd_P;

    /** MTR_READ_DT_P*/
	public final EZDBDateItem              mtrReadDt_P;

    /** READ_MTR_CNT_P*/
	public final EZDBBigDecimalItem              readMtrCnt_P;


	/**
	 * NSAL1060_PBMsg is constructor.
	 * The initialization when the instance of NSAL1060_PBMsg is generated.
	 */
	public NSAL1060_PBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1060_PBMsg is constructor.
	 * The initialization when the instance of NSAL1060_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1060_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcPhysMtrPk_P = (EZDBBigDecimalItem)newItem("svcPhysMtrPk_P");
		mtrLbCd_P = (EZDBStringItem)newItem("mtrLbCd_P");
		mtrReadDt_P = (EZDBDateItem)newItem("mtrReadDt_P");
		readMtrCnt_P = (EZDBBigDecimalItem)newItem("readMtrCnt_P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1060_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1060_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcPhysMtrPk_P", "svcPhysMtrPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbCd_P", "mtrLbCd_P", "P", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrReadDt_P", "mtrReadDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"readMtrCnt_P", "readMtrCnt_P", "P", null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_PHYS_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk_P
        {"MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_P
        {"MTR_READ_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//mtrReadDt_P
        {"READ_MTR_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_P
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

