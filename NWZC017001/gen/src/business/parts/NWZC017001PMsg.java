//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090715171738000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC017001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC017001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC017001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** CPO_ORD_NUM_I*/
	public final EZDPStringItem              cpoOrdNum_I;

    /** CPO_DTL_LINE_NUM_I*/
	public final EZDPStringItem              cpoDtlLineNum_I;

    /** CPO_DTL_LINE_SUB_NUM_I*/
	public final EZDPStringItem              cpoDtlLineSubNum_I;

    /** SHPG_PLN_NUM_I*/
	public final EZDPStringItem              shpgPlnNum_I;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** CPO_ORD_NUM_O*/
	public final EZDPStringItem              cpoOrdNum_O;

    /** CPO_DTL_LINE_NUM_O*/
	public final EZDPStringItem              cpoDtlLineNum_O;

    /** CPO_DTL_LINE_SUB_NUM_O*/
	public final EZDPStringItem              cpoDtlLineSubNum_O;

    /** SHPG_PLN_NUM_O*/
	public final EZDPStringItem              shpgPlnNum_O;

    /** HLD_RSN_CD*/
	public final EZDPStringItem              hldRsnCd;

    /** xxMsgIdList*/
	public final business.parts.NWZC017001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC017001PMsg is constructor.
	 * The initialization when the instance of NWZC017001PMsg is generated.
	 */
	public NWZC017001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC017001PMsg is constructor.
	 * The initialization when the instance of NWZC017001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC017001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		cpoOrdNum_I = (EZDPStringItem)newItem("cpoOrdNum_I");
		cpoDtlLineNum_I = (EZDPStringItem)newItem("cpoDtlLineNum_I");
		cpoDtlLineSubNum_I = (EZDPStringItem)newItem("cpoDtlLineSubNum_I");
		shpgPlnNum_I = (EZDPStringItem)newItem("shpgPlnNum_I");
		slsDt = (EZDPDateItem)newItem("slsDt");
		cpoOrdNum_O = (EZDPStringItem)newItem("cpoOrdNum_O");
		cpoDtlLineNum_O = (EZDPStringItem)newItem("cpoDtlLineNum_O");
		cpoDtlLineSubNum_O = (EZDPStringItem)newItem("cpoDtlLineSubNum_O");
		shpgPlnNum_O = (EZDPStringItem)newItem("shpgPlnNum_O");
		hldRsnCd = (EZDPStringItem)newItem("hldRsnCd");
		xxMsgIdList = (business.parts.NWZC017001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC017001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC017001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"cpoOrdNum_I", "cpoOrdNum_I", "I", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum_I", "cpoDtlLineNum_I", "I", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_I", "cpoDtlLineSubNum_I", "I", null, TYPE_HANKAKUEISU, "3", null},
	{"shpgPlnNum_I", "shpgPlnNum_I", "I", null, TYPE_HANKAKUEISU, "8", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cpoOrdNum_O", "cpoOrdNum_O", "O", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum_O", "cpoDtlLineNum_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_O", "cpoDtlLineSubNum_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	{"shpgPlnNum_O", "shpgPlnNum_O", "O", null, TYPE_HANKAKUEISU, "8", null},
	{"hldRsnCd", "hldRsnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NWZC017001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_I
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_I
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_I
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_I
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_O
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_O
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_O
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_O
        {"HLD_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hldRsnCd
		null,	//xxMsgIdList
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

