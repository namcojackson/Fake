//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20181221103628000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC611001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC611001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC611001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** DS_ACCT_NUM*/
	public final EZDPStringItem              dsAcctNum;

    /** VND_CD_I*/
	public final EZDPStringItem              vndCd_I;

    /** VND_CD_O*/
	public final EZDPStringItem              vndCd_O;

    /** DS_CARR_ACCT_NUM*/
	public final EZDPStringItem              dsCarrAcctNum;

    /** DEF_ACCT_CARR_FLG*/
	public final EZDPStringItem              defAcctCarrFlg;

    /** xxMsgIdList*/
	public final business.parts.NMZC611001_xxMsgIdListPMsgArray              xxMsgIdList;

    /** LOC_NUM*/
	public final EZDPStringItem              locNum;

    /** LINE_BIZ_TP_CD*/
	public final EZDPStringItem              lineBizTpCd;

    /** DS_BIZ_AREA_CD*/
	public final EZDPStringItem              dsBizAreaCd;

    /** FRT_COND_CD*/
	public final EZDPStringItem              frtCondCd;

    /** SHPG_SVC_LVL_CD*/
	public final EZDPStringItem              shpgSvcLvlCd;


	/**
	 * NMZC611001PMsg is constructor.
	 * The initialization when the instance of NMZC611001PMsg is generated.
	 */
	public NMZC611001PMsg() {
		this(false, -1);
	}

	/**
	 * NMZC611001PMsg is constructor.
	 * The initialization when the instance of NMZC611001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC611001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		dsAcctNum = (EZDPStringItem)newItem("dsAcctNum");
		vndCd_I = (EZDPStringItem)newItem("vndCd_I");
		vndCd_O = (EZDPStringItem)newItem("vndCd_O");
		dsCarrAcctNum = (EZDPStringItem)newItem("dsCarrAcctNum");
		defAcctCarrFlg = (EZDPStringItem)newItem("defAcctCarrFlg");
		xxMsgIdList = (business.parts.NMZC611001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
		locNum = (EZDPStringItem)newItem("locNum");
		lineBizTpCd = (EZDPStringItem)newItem("lineBizTpCd");
		dsBizAreaCd = (EZDPStringItem)newItem("dsBizAreaCd");
		frtCondCd = (EZDPStringItem)newItem("frtCondCd");
		shpgSvcLvlCd = (EZDPStringItem)newItem("shpgSvcLvlCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC611001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC611001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"vndCd_I", "vndCd_I", "I", null, TYPE_HANKAKUEISU, "20", null},
	{"vndCd_O", "vndCd_O", "O", null, TYPE_HANKAKUEISU, "20", null},
	{"dsCarrAcctNum", "dsCarrAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"defAcctCarrFlg", "defAcctCarrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NMZC611001_xxMsgIdListPMsgArray", null, null},
	
	{"locNum", "locNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"lineBizTpCd", "lineBizTpCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsBizAreaCd", "dsBizAreaCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"frtCondCd", "frtCondCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"shpgSvcLvlCd", "shpgSvcLvlCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_I
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_O
        {"DS_CARR_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCarrAcctNum
        {"DEF_ACCT_CARR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defAcctCarrFlg
		null,	//xxMsgIdList
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd
        {"FRT_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondCd
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd
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

