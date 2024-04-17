//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180117134628000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWXC005001_shpgPlnListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWXC005001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWXC005001_shpgPlnListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** CPO_DTL_LINE_NUM*/
	public final EZDPStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDPStringItem              cpoDtlLineSubNum;

    /** SHPG_PLN_NUM*/
	public final EZDPStringItem              shpgPlnNum;

    /** ORD_QTY*/
	public final EZDPBigDecimalItem              ordQty;

    /** HLD_RSN_CD*/
	public final EZDPStringItem              hldRsnCd;


	/**
	 * NWXC005001_shpgPlnListPMsg is constructor.
	 * The initialization when the instance of NWXC005001_shpgPlnListPMsg is generated.
	 */
	public NWXC005001_shpgPlnListPMsg() {
		this(false, -1);
	}

	/**
	 * NWXC005001_shpgPlnListPMsg is constructor.
	 * The initialization when the instance of NWXC005001_shpgPlnListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWXC005001_shpgPlnListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		cpoDtlLineNum = (EZDPStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDPStringItem)newItem("cpoDtlLineSubNum");
		shpgPlnNum = (EZDPStringItem)newItem("shpgPlnNum");
		ordQty = (EZDPBigDecimalItem)newItem("ordQty");
		hldRsnCd = (EZDPStringItem)newItem("hldRsnCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWXC005001_shpgPlnListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWXC005001_shpgPlnListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum", "cpoDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum", "cpoDtlLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"shpgPlnNum", "shpgPlnNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ordQty", "ordQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"hldRsnCd", "hldRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty
        {"HLD_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hldRsnCd
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
