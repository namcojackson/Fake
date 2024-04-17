//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_istlInfoPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_istlInfoPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** SVC_ISTL_RULE_NUM*/
	public final EZDPStringItem              svcIstlRuleNum;

    /** ISTL_TECH_CD*/
	public final EZDPStringItem              istlTechCd;

    /** RQST_ISTL_DT*/
	public final EZDPDateItem              rqstIstlDt;

    /** RQST_ISTL_TM*/
	public final EZDPStringItem              rqstIstlTm;

    /** SVC_BY_SVC_PRVD_PTY_CD*/
	public final EZDPStringItem              svcBySvcPrvdPtyCd;

    /** ISTL_BY_SVC_PRVD_PTY_CD*/
	public final EZDPStringItem              istlBySvcPrvdPtyCd;


	/**
	 * NWZC225001_istlInfoPMsg is constructor.
	 * The initialization when the instance of NWZC225001_istlInfoPMsg is generated.
	 */
	public NWZC225001_istlInfoPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001_istlInfoPMsg is constructor.
	 * The initialization when the instance of NWZC225001_istlInfoPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001_istlInfoPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		svcIstlRuleNum = (EZDPStringItem)newItem("svcIstlRuleNum");
		istlTechCd = (EZDPStringItem)newItem("istlTechCd");
		rqstIstlDt = (EZDPDateItem)newItem("rqstIstlDt");
		rqstIstlTm = (EZDPStringItem)newItem("rqstIstlTm");
		svcBySvcPrvdPtyCd = (EZDPStringItem)newItem("svcBySvcPrvdPtyCd");
		istlBySvcPrvdPtyCd = (EZDPStringItem)newItem("istlBySvcPrvdPtyCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001_istlInfoPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001_istlInfoPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"svcIstlRuleNum", "svcIstlRuleNum", null, null, TYPE_HANKAKUEISU, "2", null},
	{"istlTechCd", "istlTechCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rqstIstlDt", "rqstIstlDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rqstIstlTm", "rqstIstlTm", null, null, TYPE_HANKAKUSUJI, "6", null},
	{"svcBySvcPrvdPtyCd", "svcBySvcPrvdPtyCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"istlBySvcPrvdPtyCd", "istlBySvcPrvdPtyCd", null, null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"SVC_ISTL_RULE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcIstlRuleNum
        {"ISTL_TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlTechCd
        {"RQST_ISTL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstIstlDt
        {"RQST_ISTL_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstIstlTm
        {"SVC_BY_SVC_PRVD_PTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcBySvcPrvdPtyCd
        {"ISTL_BY_SVC_PRVD_PTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlBySvcPrvdPtyCd
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

