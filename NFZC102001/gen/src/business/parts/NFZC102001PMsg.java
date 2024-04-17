//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160928180813000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFZC102001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFZC102001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFZC102001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MST_CHK_FLG*/
	public final EZDPStringItem              xxMstChkFlg;

    /** XX_GL_CMBN_CHK_FLG*/
	public final EZDPStringItem              xxGlCmbnChkFlg;

    /** XX_ARCS_API_CHK_FLG*/
	public final EZDPStringItem              xxArcsApiChkFlg;

    /** COA_CMPY_CD*/
	public final EZDPStringItem              coaCmpyCd;

    /** COA_BR_CD*/
	public final EZDPStringItem              coaBrCd;

    /** COA_CC_CD*/
	public final EZDPStringItem              coaCcCd;

    /** COA_ACCT_CD*/
	public final EZDPStringItem              coaAcctCd;

    /** COA_PROD_CD*/
	public final EZDPStringItem              coaProdCd;

    /** COA_CH_CD*/
	public final EZDPStringItem              coaChCd;

    /** COA_AFFL_CD*/
	public final EZDPStringItem              coaAfflCd;

    /** COA_PROJ_CD*/
	public final EZDPStringItem              coaProjCd;

    /** COA_EXTN_CD*/
	public final EZDPStringItem              coaExtnCd;

    /** RESRC_OBJ_NM*/
	public final EZDPStringItem              resrcObjNm;

    /** BIZ_APP_ID*/
	public final EZDPStringItem              bizAppId;

    /** xxMsgIdList*/
	public final business.parts.NFZC102001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NFZC102001PMsg is constructor.
	 * The initialization when the instance of NFZC102001PMsg is generated.
	 */
	public NFZC102001PMsg() {
		this(false, -1);
	}

	/**
	 * NFZC102001PMsg is constructor.
	 * The initialization when the instance of NFZC102001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFZC102001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxMstChkFlg = (EZDPStringItem)newItem("xxMstChkFlg");
		xxGlCmbnChkFlg = (EZDPStringItem)newItem("xxGlCmbnChkFlg");
		xxArcsApiChkFlg = (EZDPStringItem)newItem("xxArcsApiChkFlg");
		coaCmpyCd = (EZDPStringItem)newItem("coaCmpyCd");
		coaBrCd = (EZDPStringItem)newItem("coaBrCd");
		coaCcCd = (EZDPStringItem)newItem("coaCcCd");
		coaAcctCd = (EZDPStringItem)newItem("coaAcctCd");
		coaProdCd = (EZDPStringItem)newItem("coaProdCd");
		coaChCd = (EZDPStringItem)newItem("coaChCd");
		coaAfflCd = (EZDPStringItem)newItem("coaAfflCd");
		coaProjCd = (EZDPStringItem)newItem("coaProjCd");
		coaExtnCd = (EZDPStringItem)newItem("coaExtnCd");
		resrcObjNm = (EZDPStringItem)newItem("resrcObjNm");
		bizAppId = (EZDPStringItem)newItem("bizAppId");
		xxMsgIdList = (business.parts.NFZC102001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NFZC102001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFZC102001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxMstChkFlg", "xxMstChkFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxGlCmbnChkFlg", "xxGlCmbnChkFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxArcsApiChkFlg", "xxArcsApiChkFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"coaCmpyCd", "coaCmpyCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd", "coaCcCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd", "coaAcctCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaChCd", "coaChCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"coaAfflCd", "coaAfflCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"coaProjCd", "coaProjCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"coaExtnCd", "coaExtnCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"resrcObjNm", "resrcObjNm", null, null, TYPE_HANKAKUEISU, "24", null},
	{"bizAppId", "bizAppId", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NFZC102001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MST_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMstChkFlg
        {"XX_GL_CMBN_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGlCmbnChkFlg
        {"XX_ARCS_API_CHK_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxArcsApiChkFlg
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd
        {"RESRC_OBJ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//resrcObjNm
        {"BIZ_APP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId
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

