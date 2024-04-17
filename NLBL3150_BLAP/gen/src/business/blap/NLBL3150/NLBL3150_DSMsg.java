//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170804050257000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3150_DSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3150 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3150_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_WH_COND_PK_D*/
	public final EZDSBigDecimalItem              mdseWhCondPk_D;

    /** MDSE_CD_D*/
	public final EZDSStringItem              mdseCd_D;

    /** RTL_WH_CD_D*/
	public final EZDSStringItem              rtlWhCd_D;

    /** RTL_SWH_CD_D*/
	public final EZDSStringItem              rtlSwhCd_D;


	/**
	 * NLBL3150_DSMsg is constructor.
	 * The initialization when the instance of NLBL3150_DSMsg is generated.
	 */
	public NLBL3150_DSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3150_DSMsg is constructor.
	 * The initialization when the instance of NLBL3150_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3150_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseWhCondPk_D = (EZDSBigDecimalItem)newItem("mdseWhCondPk_D");
		mdseCd_D = (EZDSStringItem)newItem("mdseCd_D");
		rtlWhCd_D = (EZDSStringItem)newItem("rtlWhCd_D");
		rtlSwhCd_D = (EZDSStringItem)newItem("rtlSwhCd_D");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3150_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3150_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseWhCondPk_D", "mdseWhCondPk_D", "D", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_D", "mdseCd_D", "D", null, TYPE_HANKAKUEISU, "16", null},
	{"rtlWhCd_D", "rtlWhCd_D", "D", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_D", "rtlSwhCd_D", "D", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_WH_COND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseWhCondPk_D
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_D
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_D
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_D
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

