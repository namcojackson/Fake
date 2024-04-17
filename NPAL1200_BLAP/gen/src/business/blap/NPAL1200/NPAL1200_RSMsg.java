//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20150414104324000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200_RSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1200 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200_RSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TBL_SORT_COL_NM_S*/
	public final EZDSStringItem              xxTblSortColNm_S;

    /** XX_SORT_ORD_BY_TXT_S*/
	public final EZDSStringItem              xxSortOrdByTxt_S;


	/**
	 * NPAL1200_RSMsg is constructor.
	 * The initialization when the instance of NPAL1200_RSMsg is generated.
	 */
	public NPAL1200_RSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1200_RSMsg is constructor.
	 * The initialization when the instance of NPAL1200_RSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1200_RSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTblSortColNm_S = (EZDSStringItem)newItem("xxTblSortColNm_S");
		xxSortOrdByTxt_S = (EZDSStringItem)newItem("xxSortOrdByTxt_S");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1200_RSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1200_RSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTblSortColNm_S", "xxTblSortColNm_S", "S", null, TYPE_HANKAKUEISU, "30", null},
	{"xxSortOrdByTxt_S", "xxSortOrdByTxt_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TBL_SORT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_S
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_S
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

