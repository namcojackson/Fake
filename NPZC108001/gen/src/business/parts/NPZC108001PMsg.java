//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150824025603000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC108001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NPZC108001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC108001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** INVTY_LOC_CD*/
	public final EZDPStringItem              invtyLocCd;

    /** PROCR_TP_CD*/
	public final EZDPStringItem              procrTpCd;

    /** SRC_LOC_CD*/
	public final EZDPStringItem              srcLocCd;

    /** SRC_RTL_WH_CD*/
	public final EZDPStringItem              srcRtlWhCd;

    /** SRC_RTL_SWH_CD*/
	public final EZDPStringItem              srcRtlSwhCd;

    /** xxMsgIdList*/
	public final business.parts.NPZC108001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NPZC108001PMsg is constructor.
	 * The initialization when the instance of NPZC108001PMsg is generated.
	 */
	public NPZC108001PMsg() {
		this(false, -1);
	}

	/**
	 * NPZC108001PMsg is constructor.
	 * The initialization when the instance of NPZC108001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC108001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		invtyLocCd = (EZDPStringItem)newItem("invtyLocCd");
		procrTpCd = (EZDPStringItem)newItem("procrTpCd");
		srcLocCd = (EZDPStringItem)newItem("srcLocCd");
		srcRtlWhCd = (EZDPStringItem)newItem("srcRtlWhCd");
		srcRtlSwhCd = (EZDPStringItem)newItem("srcRtlSwhCd");
		xxMsgIdList = (business.parts.NPZC108001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC108001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC108001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"procrTpCd", "procrTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"srcLocCd", "srcLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"srcRtlWhCd", "srcRtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"srcRtlSwhCd", "srcRtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NPZC108001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"PROCR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpCd
        {"SRC_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcLocCd
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd
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
