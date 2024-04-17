//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151223134925000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWXC021001_APMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWXC021001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWXC021001_APMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CTAC_PSN_PK*/
	public final EZDPBigDecimalItem              ctacPsnPk;

    /** CTAC_TP_CD*/
	public final EZDPStringItem              ctacTpCd;

    /** CTAC_PSN_FIRST_NM*/
	public final EZDPStringItem              ctacPsnFirstNm;

    /** CTAC_PSN_LAST_NM*/
	public final EZDPStringItem              ctacPsnLastNm;

    /** DS_CTAC_PNT_VAL_TXT_PH*/
	public final EZDPStringItem              dsCtacPntValTxt_PH;

    /** DS_CTAC_PNT_VAL_TXT_ML*/
	public final EZDPStringItem              dsCtacPntValTxt_ML;

    /** DS_CTAC_PNT_VAL_TXT_FX*/
	public final EZDPStringItem              dsCtacPntValTxt_FX;

    /** DS_CTAC_PSN_EXTN_NUM*/
	public final EZDPStringItem              dsCtacPsnExtnNum;


	/**
	 * NWXC021001_APMsg is constructor.
	 * The initialization when the instance of NWXC021001_APMsg is generated.
	 */
	public NWXC021001_APMsg() {
		this(false, -1);
	}

	/**
	 * NWXC021001_APMsg is constructor.
	 * The initialization when the instance of NWXC021001_APMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWXC021001_APMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ctacPsnPk = (EZDPBigDecimalItem)newItem("ctacPsnPk");
		ctacTpCd = (EZDPStringItem)newItem("ctacTpCd");
		ctacPsnFirstNm = (EZDPStringItem)newItem("ctacPsnFirstNm");
		ctacPsnLastNm = (EZDPStringItem)newItem("ctacPsnLastNm");
		dsCtacPntValTxt_PH = (EZDPStringItem)newItem("dsCtacPntValTxt_PH");
		dsCtacPntValTxt_ML = (EZDPStringItem)newItem("dsCtacPntValTxt_ML");
		dsCtacPntValTxt_FX = (EZDPStringItem)newItem("dsCtacPntValTxt_FX");
		dsCtacPsnExtnNum = (EZDPStringItem)newItem("dsCtacPsnExtnNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NWXC021001_APMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWXC021001_APMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ctacPsnPk", "ctacPsnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacTpCd", "ctacTpCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnFirstNm", "ctacPsnFirstNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm", "ctacPsnLastNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsCtacPntValTxt_PH", "dsCtacPntValTxt_PH", "PH", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_ML", "dsCtacPntValTxt_ML", "ML", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_FX", "dsCtacPntValTxt_FX", "FX", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPsnExtnNum", "dsCtacPsnExtnNum", null, null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk
        {"CTAC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacTpCd
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_PH
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_ML
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_FX
        {"DS_CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPsnExtnNum
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
