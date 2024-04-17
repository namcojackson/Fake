//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200605102527000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC005001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC005001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC005001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** INVTY_ORD_NUM*/
	public final EZDPStringItem              invtyOrdNum;

    /** APVL_HIST_SRC_TP_CD*/
	public final EZDPStringItem              apvlHistSrcTpCd;

    /** RQST_UPD_BY_PSN_CD*/
	public final EZDPStringItem              rqstUpdByPsnCd;

    /** PRCH_GRP_CD*/
	public final EZDPStringItem              prchGrpCd;

    /** PO_MDSE_TP_CD*/
	public final EZDPStringItem              poMdseTpCd;

    /** CYCLE_CNT_RSN_CD*/
	public final EZDPStringItem              cycleCntRsnCd;

    /** xxMsgIdList*/
	public final business.parts.NLZC005001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NLZC005001PMsg is constructor.
	 * The initialization when the instance of NLZC005001PMsg is generated.
	 */
	public NLZC005001PMsg() {
		this(false, -1);
	}

	/**
	 * NLZC005001PMsg is constructor.
	 * The initialization when the instance of NLZC005001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC005001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		invtyOrdNum = (EZDPStringItem)newItem("invtyOrdNum");
		apvlHistSrcTpCd = (EZDPStringItem)newItem("apvlHistSrcTpCd");
		rqstUpdByPsnCd = (EZDPStringItem)newItem("rqstUpdByPsnCd");
		prchGrpCd = (EZDPStringItem)newItem("prchGrpCd");
		poMdseTpCd = (EZDPStringItem)newItem("poMdseTpCd");
		cycleCntRsnCd = (EZDPStringItem)newItem("cycleCntRsnCd");
		xxMsgIdList = (business.parts.NLZC005001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC005001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC005001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invtyOrdNum", "invtyOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"apvlHistSrcTpCd", "apvlHistSrcTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rqstUpdByPsnCd", "rqstUpdByPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"prchGrpCd", "prchGrpCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"poMdseTpCd", "poMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"cycleCntRsnCd", "cycleCntRsnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NLZC005001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"INVTY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyOrdNum
        {"APVL_HIST_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistSrcTpCd
        {"RQST_UPD_BY_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstUpdByPsnCd
        {"PRCH_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpCd
        {"PO_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMdseTpCd
        {"CYCLE_CNT_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cycleCntRsnCd
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

