//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200615223318000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC061001_expMdseListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC061001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC061001_expMdseListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** EXP_QTY*/
	public final EZDPBigDecimalItem              expQty;

    /** PKG_UOM_CD*/
	public final EZDPStringItem              pkgUomCd;


	/**
	 * NSZC061001_expMdseListPMsg is constructor.
	 * The initialization when the instance of NSZC061001_expMdseListPMsg is generated.
	 */
	public NSZC061001_expMdseListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC061001_expMdseListPMsg is constructor.
	 * The initialization when the instance of NSZC061001_expMdseListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC061001_expMdseListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		expQty = (EZDPBigDecimalItem)newItem("expQty");
		pkgUomCd = (EZDPStringItem)newItem("pkgUomCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC061001_expMdseListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC061001_expMdseListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"expQty", "expQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"pkgUomCd", "pkgUomCd", null, null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"EXP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//expQty
        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd
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

