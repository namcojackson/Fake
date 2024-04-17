//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160114025513000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC130001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NPZC130001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC130001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** XX_PROC_TP_CD*/
	public final EZDPStringItem              xxProcTpCd;

    /** TRX_REF_NUM*/
	public final EZDPStringItem              trxRefNum;

    /** APVL_HIST_SRC_TP_CD*/
	public final EZDPStringItem              apvlHistSrcTpCd;

    /** xxMsgIdList*/
	public final business.parts.NPZC130001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NPZC130001PMsg is constructor.
	 * The initialization when the instance of NPZC130001PMsg is generated.
	 */
	public NPZC130001PMsg() {
		this(false, -1);
	}

	/**
	 * NPZC130001PMsg is constructor.
	 * The initialization when the instance of NPZC130001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC130001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		xxProcTpCd = (EZDPStringItem)newItem("xxProcTpCd");
		trxRefNum = (EZDPStringItem)newItem("trxRefNum");
		apvlHistSrcTpCd = (EZDPStringItem)newItem("apvlHistSrcTpCd");
		xxMsgIdList = (business.parts.NPZC130001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC130001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC130001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxProcTpCd", "xxProcTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"trxRefNum", "trxRefNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"apvlHistSrcTpCd", "apvlHistSrcTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NPZC130001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"XX_PROC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcTpCd
        {"TRX_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRefNum
        {"APVL_HIST_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistSrcTpCd
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
