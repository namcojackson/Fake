//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20090610155705000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC210002PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC210002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC210002PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** xxMsgIdList*/
	public final business.parts.NLZC210002_xxMsgIdListPMsgArray              xxMsgIdList;

    /** WH_CD*/
	public final EZDPStringItem              whCd;

    /** SO_NUM*/
	public final EZDPStringItem              soNum;

    /** SCE_ORD_TP_CD*/
	public final EZDPStringItem              sceOrdTpCd;

    /** SO_PROC_STS_CD*/
	public final EZDPStringItem              soProcStsCd;

    /** SHIP_DT_TM_TS*/
	public final EZDPStringItem              shipDtTmTs;

    /** SO_SLP_NUM*/
	public final EZDPStringItem              soSlpNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** SER_TAKE_DT_TM_TS*/
	public final EZDPStringItem              serTakeDtTmTs;


	/**
	 * NLZC210002PMsg is constructor.
	 * The initialization when the instance of NLZC210002PMsg is generated.
	 */
	public NLZC210002PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC210002PMsg is constructor.
	 * The initialization when the instance of NLZC210002PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC210002PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxMsgIdList = (business.parts.NLZC210002_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
		whCd = (EZDPStringItem)newItem("whCd");
		soNum = (EZDPStringItem)newItem("soNum");
		sceOrdTpCd = (EZDPStringItem)newItem("sceOrdTpCd");
		soProcStsCd = (EZDPStringItem)newItem("soProcStsCd");
		shipDtTmTs = (EZDPStringItem)newItem("shipDtTmTs");
		soSlpNum = (EZDPStringItem)newItem("soSlpNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		serNum = (EZDPStringItem)newItem("serNum");
		serTakeDtTmTs = (EZDPStringItem)newItem("serTakeDtTmTs");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC210002PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC210002PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC210002_xxMsgIdListPMsgArray", null, null},
	
	{"whCd", "whCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"sceOrdTpCd", "sceOrdTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"soProcStsCd", "soProcStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"shipDtTmTs", "shipDtTmTs", null, null, TYPE_HANKAKUSUJI, "14", null},
	{"soSlpNum", "soSlpNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"serTakeDtTmTs", "serTakeDtTmTs", null, null, TYPE_HANKAKUSUJI, "14", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
		null,	//xxMsgIdList
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
        {"SCE_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sceOrdTpCd
        {"SO_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soProcStsCd
        {"SHIP_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipDtTmTs
        {"SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soSlpNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"SER_TAKE_DT_TM_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTakeDtTmTs
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
