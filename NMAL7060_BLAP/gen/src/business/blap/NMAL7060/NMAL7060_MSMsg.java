//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180821154635000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7060_MSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7060_MSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_MTR_PKG_PHYS_MTR_PK_M1*/
	public final EZDSBigDecimalItem              prcMtrPkgPhysMtrPk_M1;

    /** _EZUpdateDateTime_MU*/
	public final EZDSStringItem              ezUpTime_MU;

    /** _EZUpTimeZone_MU*/
	public final EZDSStringItem              ezUpTimeZone_MU;


	/**
	 * NMAL7060_MSMsg is constructor.
	 * The initialization when the instance of NMAL7060_MSMsg is generated.
	 */
	public NMAL7060_MSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7060_MSMsg is constructor.
	 * The initialization when the instance of NMAL7060_MSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7060_MSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcMtrPkgPhysMtrPk_M1 = (EZDSBigDecimalItem)newItem("prcMtrPkgPhysMtrPk_M1");
		ezUpTime_MU = (EZDSStringItem)newItem("ezUpTime_MU");
		ezUpTimeZone_MU = (EZDSStringItem)newItem("ezUpTimeZone_MU");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7060_MSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7060_MSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcMtrPkgPhysMtrPk_M1", "prcMtrPkgPhysMtrPk_M1", "M1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_MU", "ezUpTime_MU", "MU", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_MU", "ezUpTimeZone_MU", "MU", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_MTR_PKG_PHYS_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPhysMtrPk_M1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_MU
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_MU
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
