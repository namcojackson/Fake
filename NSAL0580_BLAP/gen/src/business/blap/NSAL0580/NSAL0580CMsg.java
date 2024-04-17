//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160229112643000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0580CMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSAL0580 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0580CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** DS_CONTR_PK*/
	public final EZDCBigDecimalItem              dsContrPk;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** DS_CONTR_STS_CD*/
	public final EZDCStringItem              dsContrStsCd;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** DS_CONTR_CTRL_STS_CD*/
	public final EZDCStringItem              dsContrCtrlStsCd;

    /** DS_CONTR_CTRL_STS_NM*/
	public final EZDCStringItem              dsContrCtrlStsNm;

    /** DS_CONTR_CTRL_STS_CD_L*/
	public final EZDCStringItemArray              dsContrCtrlStsCd_L;

    /** DS_CONTR_CTRL_STS_NM_L*/
	public final EZDCStringItemArray              dsContrCtrlStsNm_L;

    /** DS_CONTR_CTRL_STS_CD_H*/
	public final EZDCStringItem              dsContrCtrlStsCd_H;

    /** SVC_MEMO_RSN_CD_L*/
	public final EZDCStringItemArray              svcMemoRsnCd_L;

    /** SVC_MEMO_RSN_DESC_TXT_L*/
	public final EZDCStringItemArray              svcMemoRsnDescTxt_L;

    /** SVC_MEMO_RSN_CD_H*/
	public final EZDCStringItem              svcMemoRsnCd_H;

    /** SVC_CMNT_TXT*/
	public final EZDCStringItem              svcCmntTxt;

    /** DS_CONTR_STS_CD_TO*/
	public final EZDCStringItem              dsContrStsCd_TO;

    /** DS_CONTR_DTL_STS_CD_TO*/
	public final EZDCStringItem              dsContrDtlStsCd_TO;

    /** CONTR_HLD_FLG_TO*/
	public final EZDCStringItem              contrHldFlg_TO;

    /** BLLG_HLD_FLG_TO*/
	public final EZDCStringItem              bllgHldFlg_TO;


	/**
	 * NSAL0580CMsg is constructor.
	 * The initialization when the instance of NSAL0580CMsg is generated.
	 */
	public NSAL0580CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0580CMsg is constructor.
	 * The initialization when the instance of NSAL0580CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0580CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		dsContrPk = (EZDCBigDecimalItem)newItem("dsContrPk");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		dsContrStsCd = (EZDCStringItem)newItem("dsContrStsCd");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		dsContrCtrlStsCd = (EZDCStringItem)newItem("dsContrCtrlStsCd");
		dsContrCtrlStsNm = (EZDCStringItem)newItem("dsContrCtrlStsNm");
		dsContrCtrlStsCd_L = (EZDCStringItemArray)newItemArray("dsContrCtrlStsCd_L");
		dsContrCtrlStsNm_L = (EZDCStringItemArray)newItemArray("dsContrCtrlStsNm_L");
		dsContrCtrlStsCd_H = (EZDCStringItem)newItem("dsContrCtrlStsCd_H");
		svcMemoRsnCd_L = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_L");
		svcMemoRsnDescTxt_L = (EZDCStringItemArray)newItemArray("svcMemoRsnDescTxt_L");
		svcMemoRsnCd_H = (EZDCStringItem)newItem("svcMemoRsnCd_H");
		svcCmntTxt = (EZDCStringItem)newItem("svcCmntTxt");
		dsContrStsCd_TO = (EZDCStringItem)newItem("dsContrStsCd_TO");
		dsContrDtlStsCd_TO = (EZDCStringItem)newItem("dsContrDtlStsCd_TO");
		contrHldFlg_TO = (EZDCStringItem)newItem("contrHldFlg_TO");
		bllgHldFlg_TO = (EZDCStringItem)newItem("bllgHldFlg_TO");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0580CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0580CMsgArray();
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
	{"dsContrStsCd_TO", "dsContrStsCd_TO", "TO", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrDtlStsCd_TO", "dsContrDtlStsCd_TO", "TO", null, TYPE_HANKAKUEISU, "4", null},
	{"contrHldFlg_TO", "contrHldFlg_TO", "TO", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgHldFlg_TO", "bllgHldFlg_TO", "TO", null, TYPE_HANKAKUEISU, "1", null},
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
        {"DS_CONTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd_TO
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd_TO
        {"CONTR_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrHldFlg_TO
        {"BLLG_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgHldFlg_TO
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
