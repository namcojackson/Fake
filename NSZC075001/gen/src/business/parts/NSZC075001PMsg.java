//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190726053114000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC075001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC075001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC075001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** FLD_RQST_SRC_PGM_CD*/
	public final EZDPStringItem              fldRqstSrcPgmCd;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** DS_SVC_CALL_TP_CD*/
	public final EZDPStringItem              dsSvcCallTpCd;

    /** XTRNL_CALL_TP_REF_TXT*/
	public final EZDPStringItem              xtrnlCallTpRefTxt;

    /** SVC_PBLM_TP_CD*/
	public final EZDPStringItem              svcPblmTpCd;

    /** XTRNL_PBLM_TP_REF_TXT*/
	public final EZDPStringItem              xtrnlPblmTpRefTxt;

    /** PSN_CD*/
	public final EZDPStringItem              psnCd;

    /** XX_ALL_PSN_NM*/
	public final EZDPStringItem              xxAllPsnNm;

    /** SVC_CMNT_TXT*/
	public final EZDPStringItem              svcCmntTxt;

    /** LOC_NM*/
	public final EZDPStringItem              locNm;

    /** ADDL_LOC_NM*/
	public final EZDPStringItem              addlLocNm;

    /** FIRST_LINE_ADDR*/
	public final EZDPStringItem              firstLineAddr;

    /** SCD_LINE_ADDR*/
	public final EZDPStringItem              scdLineAddr;

    /** THIRD_LINE_ADDR*/
	public final EZDPStringItem              thirdLineAddr;

    /** FRTH_LINE_ADDR*/
	public final EZDPStringItem              frthLineAddr;

    /** CTY_ADDR*/
	public final EZDPStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDPStringItem              stCd;

    /** POST_CD*/
	public final EZDPStringItem              postCd;

    /** CTRY_CD*/
	public final EZDPStringItem              ctryCd;

    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;

    /** CLICK_KEY_TXT*/
	public final EZDPStringItem              clickKeyTxt;

    /** XX_RQST_SEND_FLG*/
	public final EZDPStringItem              xxRqstSendFlg;

    /** XX_RQST_FLG_NC*/
	public final EZDPStringItem              xxRqstFlg_NC;

    /** T_MDL_NM*/
	public final EZDPStringItem              t_MdlNm;

    /** xxMsgIdList*/
	public final business.parts.NSZC075001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC075001PMsg is constructor.
	 * The initialization when the instance of NSZC075001PMsg is generated.
	 */
	public NSZC075001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC075001PMsg is constructor.
	 * The initialization when the instance of NSZC075001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC075001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		fldRqstSrcPgmCd = (EZDPStringItem)newItem("fldRqstSrcPgmCd");
		serNum = (EZDPStringItem)newItem("serNum");
		dsSvcCallTpCd = (EZDPStringItem)newItem("dsSvcCallTpCd");
		xtrnlCallTpRefTxt = (EZDPStringItem)newItem("xtrnlCallTpRefTxt");
		svcPblmTpCd = (EZDPStringItem)newItem("svcPblmTpCd");
		xtrnlPblmTpRefTxt = (EZDPStringItem)newItem("xtrnlPblmTpRefTxt");
		psnCd = (EZDPStringItem)newItem("psnCd");
		xxAllPsnNm = (EZDPStringItem)newItem("xxAllPsnNm");
		svcCmntTxt = (EZDPStringItem)newItem("svcCmntTxt");
		locNm = (EZDPStringItem)newItem("locNm");
		addlLocNm = (EZDPStringItem)newItem("addlLocNm");
		firstLineAddr = (EZDPStringItem)newItem("firstLineAddr");
		scdLineAddr = (EZDPStringItem)newItem("scdLineAddr");
		thirdLineAddr = (EZDPStringItem)newItem("thirdLineAddr");
		frthLineAddr = (EZDPStringItem)newItem("frthLineAddr");
		ctyAddr = (EZDPStringItem)newItem("ctyAddr");
		stCd = (EZDPStringItem)newItem("stCd");
		postCd = (EZDPStringItem)newItem("postCd");
		ctryCd = (EZDPStringItem)newItem("ctryCd");
		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
		clickKeyTxt = (EZDPStringItem)newItem("clickKeyTxt");
		xxRqstSendFlg = (EZDPStringItem)newItem("xxRqstSendFlg");
		xxRqstFlg_NC = (EZDPStringItem)newItem("xxRqstFlg_NC");
		t_MdlNm = (EZDPStringItem)newItem("t_MdlNm");
		xxMsgIdList = (business.parts.NSZC075001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC075001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC075001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"fldRqstSrcPgmCd", "fldRqstSrcPgmCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsSvcCallTpCd", "dsSvcCallTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xtrnlCallTpRefTxt", "xtrnlCallTpRefTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcPblmTpCd", "svcPblmTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xtrnlPblmTpRefTxt", "xtrnlPblmTpRefTxt", null, null, TYPE_HANKAKUEISU, "500", null},
	{"psnCd", "psnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxAllPsnNm", "xxAllPsnNm", null, null, TYPE_HANKAKUEISU, "99", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"locNm", "locNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"addlLocNm", "addlLocNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr", "scdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"thirdLineAddr", "thirdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"frthLineAddr", "frthLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"ctryCd", "ctryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"clickKeyTxt", "clickKeyTxt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxRqstSendFlg", "xxRqstSendFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxRqstFlg_NC", "xxRqstFlg_NC", "NC", null, TYPE_HANKAKUEISU, "1", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxMsgIdList", "xxMsgIdList", null, "2000", "business.parts.NSZC075001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"FLD_RQST_SRC_PGM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fldRqstSrcPgmCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"DS_SVC_CALL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpCd
        {"XTRNL_CALL_TP_REF_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlCallTpRefTxt
        {"SVC_PBLM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpCd
        {"XTRNL_PBLM_TP_REF_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xtrnlPblmTpRefTxt
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm
        {"ADDL_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlLocNm
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr
        {"THIRD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdLineAddr
        {"FRTH_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthLineAddr
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"CLICK_KEY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clickKeyTxt
        {"XX_RQST_SEND_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstSendFlg
        {"XX_RQST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg_NC
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
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

