//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230411144945000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC005001_xxFsrIstlChkListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSZC005001_xxFsrIstlChkListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FSR_ISTL_CHK_LIST_PK*/
	public final EZDPBigDecimalItem              fsrIstlChkListPk;

    /** FSR_NUM*/
	public final EZDPStringItem              fsrNum;

    /** SVC_TASK_NUM*/
	public final EZDPStringItem              svcTaskNum;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              svcConfigMstrPk;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** MDL_ID*/
	public final EZDPBigDecimalItem              mdlId;

    /** MDL_NM*/
	public final EZDPStringItem              mdlNm;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** ISTL_CHK_VER_FLG*/
	public final EZDPStringItem              istlChkVerFlg;

    /** CRCT_SER_NUM*/
	public final EZDPStringItem              crctSerNum;


	/**
	 * NSZC005001_xxFsrIstlChkListPMsg is constructor.
	 * The initialization when the instance of NSZC005001_xxFsrIstlChkListPMsg is generated.
	 */
	public NSZC005001_xxFsrIstlChkListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC005001_xxFsrIstlChkListPMsg is constructor.
	 * The initialization when the instance of NSZC005001_xxFsrIstlChkListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC005001_xxFsrIstlChkListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		fsrIstlChkListPk = (EZDPBigDecimalItem)newItem("fsrIstlChkListPk");
		fsrNum = (EZDPStringItem)newItem("fsrNum");
		svcTaskNum = (EZDPStringItem)newItem("svcTaskNum");
		svcConfigMstrPk = (EZDPBigDecimalItem)newItem("svcConfigMstrPk");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		mdlId = (EZDPBigDecimalItem)newItem("mdlId");
		mdlNm = (EZDPStringItem)newItem("mdlNm");
		serNum = (EZDPStringItem)newItem("serNum");
		istlChkVerFlg = (EZDPStringItem)newItem("istlChkVerFlg");
		crctSerNum = (EZDPStringItem)newItem("crctSerNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC005001_xxFsrIstlChkListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC005001_xxFsrIstlChkListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"fsrIstlChkListPk", "fsrIstlChkListPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"fsrNum", "fsrNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcTaskNum", "svcTaskNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"istlChkVerFlg", "istlChkVerFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"crctSerNum", "crctSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"FSR_ISTL_CHK_LIST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrIstlChkListPk
        {"FSR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fsrNum
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"ISTL_CHK_VER_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlChkVerFlg
        {"CRCT_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crctSerNum
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

