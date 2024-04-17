//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090827114343000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZXL0030_DSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZXL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZXL0030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZXL0030_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CAL_DT_D*/
	public final EZDSDateItem              calDt_D;

    /** XX_CHK_BOX_D*/
	public final EZDSStringItem              xxChkBox_D;


	/**
	 * ZZXL0030_DSMsg is constructor.
	 * The initialization when the instance of ZZXL0030_DSMsg is generated.
	 */
	public ZZXL0030_DSMsg() {
		this(false, -1);
	}

	/**
	 * ZZXL0030_DSMsg is constructor.
	 * The initialization when the instance of ZZXL0030_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZXL0030_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		calDt_D = (EZDSDateItem)newItem("calDt_D");
		xxChkBox_D = (EZDSStringItem)newItem("xxChkBox_D");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZXL0030_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZXL0030_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"calDt_D", "calDt_D", "D", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_D", "xxChkBox_D", "D", null, TYPE_HANKAKUEIJI, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CAL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calDt_D
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D
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

