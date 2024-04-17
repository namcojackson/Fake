//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160804171419000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC118001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NPZC118001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC118001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** XX_SRCH_NUM*/
	public final EZDPStringItem              xxSrchNum;

    /** RWS_STS_DESC_TXT*/
	public final EZDPStringItem              rwsStsDescTxt;

    /** TECH_CD*/
	public final EZDPStringItem              techCd;

    /** WH_CD*/
	public final EZDPStringItem              whCd;

    /** RTL_WH_NM*/
	public final EZDPStringItem              rtlWhNm;

    /** SVC_ASG_TECH_CD*/
	public final EZDPStringItem              svcAsgTechCd;

    /** XX_USR_SYS_DT_TXT*/
	public final EZDPStringItem              xxUsrSysDtTxt;

    /** RWS_DT_TM_CLO_TXT*/
	public final EZDPStringItem              rwsDtTmCloTxt;

    /** CLICK_KEY_TXT*/
	public final EZDPStringItem              clickKeyTxt;

    /** xxMsgIdList*/
	public final business.parts.NPZC118001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NPZC118001PMsg is constructor.
	 * The initialization when the instance of NPZC118001PMsg is generated.
	 */
	public NPZC118001PMsg() {
		this(false, -1);
	}

	/**
	 * NPZC118001PMsg is constructor.
	 * The initialization when the instance of NPZC118001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC118001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		xxSrchNum = (EZDPStringItem)newItem("xxSrchNum");
		rwsStsDescTxt = (EZDPStringItem)newItem("rwsStsDescTxt");
		techCd = (EZDPStringItem)newItem("techCd");
		whCd = (EZDPStringItem)newItem("whCd");
		rtlWhNm = (EZDPStringItem)newItem("rtlWhNm");
		svcAsgTechCd = (EZDPStringItem)newItem("svcAsgTechCd");
		xxUsrSysDtTxt = (EZDPStringItem)newItem("xxUsrSysDtTxt");
		rwsDtTmCloTxt = (EZDPStringItem)newItem("rwsDtTmCloTxt");
		clickKeyTxt = (EZDPStringItem)newItem("clickKeyTxt");
		xxMsgIdList = (business.parts.NPZC118001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC118001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC118001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxSrchNum", "xxSrchNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rwsStsDescTxt", "rwsStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"techCd", "techCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"whCd", "whCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcAsgTechCd", "svcAsgTechCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxUsrSysDtTxt", "xxUsrSysDtTxt", null, null, TYPE_HANKAKUEISU, "23", null},
	{"rwsDtTmCloTxt", "rwsDtTmCloTxt", null, null, TYPE_HANKAKUEISU, "23", null},
	{"clickKeyTxt", "clickKeyTxt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxMsgIdList", "xxMsgIdList", null, "200", "business.parts.NPZC118001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_SRCH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSrchNum
        {"RWS_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsStsDescTxt
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"SVC_ASG_TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcAsgTechCd
        {"XX_USR_SYS_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUsrSysDtTxt
        {"RWS_DT_TM_CLO_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsDtTmCloTxt
        {"CLICK_KEY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clickKeyTxt
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

