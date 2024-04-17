//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160229112644000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0580SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0580;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0580 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0580SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** DS_CONTR_PK*/
	public final EZDSBigDecimalItem              dsContrPk;

    /** _EZUpdateDateTime*/
	public final EZDSStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDSStringItem              ezUpTimeZone;

    /** DS_CONTR_STS_CD*/
	public final EZDSStringItem              dsContrStsCd;

    /** DS_CONTR_NUM*/
	public final EZDSStringItem              dsContrNum;

    /** DS_CONTR_CTRL_STS_CD*/
	public final EZDSStringItem              dsContrCtrlStsCd;

    /** DS_CONTR_CTRL_STS_NM*/
	public final EZDSStringItem              dsContrCtrlStsNm;

    /** DS_CONTR_CTRL_STS_CD_L*/
	public final EZDSStringItemArray              dsContrCtrlStsCd_L;

    /** DS_CONTR_CTRL_STS_NM_L*/
	public final EZDSStringItemArray              dsContrCtrlStsNm_L;

    /** DS_CONTR_CTRL_STS_CD_H*/
	public final EZDSStringItem              dsContrCtrlStsCd_H;

    /** SVC_MEMO_RSN_CD_L*/
	public final EZDSStringItemArray              svcMemoRsnCd_L;

    /** SVC_MEMO_RSN_DESC_TXT_L*/
	public final EZDSStringItemArray              svcMemoRsnDescTxt_L;

    /** SVC_MEMO_RSN_CD_H*/
	public final EZDSStringItem              svcMemoRsnCd_H;

    /** SVC_CMNT_TXT*/
	public final EZDSStringItem              svcCmntTxt;


	/**
	 * NSAL0580SMsg is constructor.
	 * The initialization when the instance of NSAL0580SMsg is generated.
	 */
	public NSAL0580SMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0580SMsg is constructor.
	 * The initialization when the instance of NSAL0580SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0580SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		dsContrPk = (EZDSBigDecimalItem)newItem("dsContrPk");
		ezUpTime = (EZDSStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDSStringItem)newItem("ezUpTimeZone");
		dsContrStsCd = (EZDSStringItem)newItem("dsContrStsCd");
		dsContrNum = (EZDSStringItem)newItem("dsContrNum");
		dsContrCtrlStsCd = (EZDSStringItem)newItem("dsContrCtrlStsCd");
		dsContrCtrlStsNm = (EZDSStringItem)newItem("dsContrCtrlStsNm");
		dsContrCtrlStsCd_L = (EZDSStringItemArray)newItemArray("dsContrCtrlStsCd_L");
		dsContrCtrlStsNm_L = (EZDSStringItemArray)newItemArray("dsContrCtrlStsNm_L");
		dsContrCtrlStsCd_H = (EZDSStringItem)newItem("dsContrCtrlStsCd_H");
		svcMemoRsnCd_L = (EZDSStringItemArray)newItemArray("svcMemoRsnCd_L");
		svcMemoRsnDescTxt_L = (EZDSStringItemArray)newItemArray("svcMemoRsnDescTxt_L");
		svcMemoRsnCd_H = (EZDSStringItem)newItem("svcMemoRsnCd_H");
		svcCmntTxt = (EZDSStringItem)newItem("svcCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0580SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0580SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrStsCd", "dsContrStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrCtrlStsCd", "dsContrCtrlStsCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrCtrlStsNm", "dsContrCtrlStsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrCtrlStsCd_L", "dsContrCtrlStsCd_L", "L", "99", TYPE_HANKAKUEISU, "4", null},
	{"dsContrCtrlStsNm_L", "dsContrCtrlStsNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsContrCtrlStsCd_H", "dsContrCtrlStsCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnCd_L", "svcMemoRsnCd_L", "L", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnDescTxt_L", "svcMemoRsnDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcMemoRsnCd_H", "svcMemoRsnCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"DS_CONTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_L
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm_L
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_H
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_L
        {"SVC_MEMO_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnDescTxt_L
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
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

