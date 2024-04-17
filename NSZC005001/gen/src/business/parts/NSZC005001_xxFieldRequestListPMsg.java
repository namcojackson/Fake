//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230411144945000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC005001_xxFieldRequestListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC005001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC005001_xxFieldRequestListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** DS_ACCT_NM*/
	public final EZDPStringItem              dsAcctNm;

    /** FIRST_LINE_ADDR*/
	public final EZDPStringItem              firstLineAddr;

    /** SCD_LINE_ADDR*/
	public final EZDPStringItem              scdLineAddr;

    /** CTY_ADDR*/
	public final EZDPStringItem              ctyAddr;

    /** ST_CD*/
	public final EZDPStringItem              stCd;

    /** POST_CD*/
	public final EZDPStringItem              postCd;

    /** CTRY_CD*/
	public final EZDPStringItem              ctryCd;

    /** SVC_PBLM_TP_CD*/
	public final EZDPStringItem              svcPblmTpCd;

    /** SVC_CMNT_TXT*/
	public final EZDPStringItem              svcCmntTxt;

    /** DS_SVC_CALL_TP_CD*/
	public final EZDPStringItem              dsSvcCallTpCd;

    /** XX_ALL_PSN_NM*/
	public final EZDPStringItem              xxAllPsnNm;

    /** PSN_CD*/
	public final EZDPStringItem              psnCd;


	/**
	 * NSZC005001_xxFieldRequestListPMsg is constructor.
	 * The initialization when the instance of NSZC005001_xxFieldRequestListPMsg is generated.
	 */
	public NSZC005001_xxFieldRequestListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC005001_xxFieldRequestListPMsg is constructor.
	 * The initialization when the instance of NSZC005001_xxFieldRequestListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC005001_xxFieldRequestListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum = (EZDPStringItem)newItem("serNum");
		dsAcctNm = (EZDPStringItem)newItem("dsAcctNm");
		firstLineAddr = (EZDPStringItem)newItem("firstLineAddr");
		scdLineAddr = (EZDPStringItem)newItem("scdLineAddr");
		ctyAddr = (EZDPStringItem)newItem("ctyAddr");
		stCd = (EZDPStringItem)newItem("stCd");
		postCd = (EZDPStringItem)newItem("postCd");
		ctryCd = (EZDPStringItem)newItem("ctryCd");
		svcPblmTpCd = (EZDPStringItem)newItem("svcPblmTpCd");
		svcCmntTxt = (EZDPStringItem)newItem("svcCmntTxt");
		dsSvcCallTpCd = (EZDPStringItem)newItem("dsSvcCallTpCd");
		xxAllPsnNm = (EZDPStringItem)newItem("xxAllPsnNm");
		psnCd = (EZDPStringItem)newItem("psnCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC005001_xxFieldRequestListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC005001_xxFieldRequestListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr", "scdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ctyAddr", "ctyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"ctryCd", "ctryCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcPblmTpCd", "svcPblmTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"dsSvcCallTpCd", "dsSvcCallTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxAllPsnNm", "xxAllPsnNm", null, null, TYPE_HANKAKUEISU, "99", null},
	{"psnCd", "psnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctryCd
        {"SVC_PBLM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPblmTpCd
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"DS_SVC_CALL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSvcCallTpCd
        {"XX_ALL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllPsnNm
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd
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
