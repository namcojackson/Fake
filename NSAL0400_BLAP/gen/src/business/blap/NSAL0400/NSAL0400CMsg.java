//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220912153614000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0400CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0400 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0400CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** P*/
	public final business.blap.NSAL0400.NSAL0400_PCMsgArray              P;

    /** CONTR_CLO_DT_H*/
	public final EZDCDateItem              contrCloDt_H;

    /** SUPPR_CR_FLG_H*/
	public final EZDCStringItem              supprCrFlg_H;

    /** CONTR_TRMN_FLG_H*/
	public final EZDCStringItem              contrTrmnFlg_H;

    /** SVC_MEMO_RSN_CD_FC*/
	public final EZDCStringItemArray              svcMemoRsnCd_FC;

    /** XX_SCR_ITEM_130_TXT_FD*/
	public final EZDCStringItemArray              xxScrItem130Txt_FD;

    /** SVC_MEMO_RSN_CD_FS*/
	public final EZDCStringItem              svcMemoRsnCd_FS;

    /** SVC_CMNT_TXT_F*/
	public final EZDCStringItem              svcCmntTxt_F;

    /** XX_RSLT_FLG*/
	public final EZDCStringItem              xxRsltFlg;

    /** XX_RSLT_FLG_W*/
	public final EZDCStringItem              xxRsltFlg_W;

    /** A*/
	public final business.blap.NSAL0400.NSAL0400_ACMsgArray              A;

    /** B*/
	public final business.blap.NSAL0400.NSAL0400_BCMsgArray              B;


	/**
	 * NSAL0400CMsg is constructor.
	 * The initialization when the instance of NSAL0400CMsg is generated.
	 */
	public NSAL0400CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0400CMsg is constructor.
	 * The initialization when the instance of NSAL0400CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0400CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		P = (business.blap.NSAL0400.NSAL0400_PCMsgArray)newMsgArray("P");
		contrCloDt_H = (EZDCDateItem)newItem("contrCloDt_H");
		supprCrFlg_H = (EZDCStringItem)newItem("supprCrFlg_H");
		contrTrmnFlg_H = (EZDCStringItem)newItem("contrTrmnFlg_H");
		svcMemoRsnCd_FC = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_FC");
		xxScrItem130Txt_FD = (EZDCStringItemArray)newItemArray("xxScrItem130Txt_FD");
		svcMemoRsnCd_FS = (EZDCStringItem)newItem("svcMemoRsnCd_FS");
		svcCmntTxt_F = (EZDCStringItem)newItem("svcCmntTxt_F");
		xxRsltFlg = (EZDCStringItem)newItem("xxRsltFlg");
		xxRsltFlg_W = (EZDCStringItem)newItem("xxRsltFlg_W");
		A = (business.blap.NSAL0400.NSAL0400_ACMsgArray)newMsgArray("A");
		B = (business.blap.NSAL0400.NSAL0400_BCMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0400CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0400CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"P", "P", null, "2000", "business.blap.NSAL0400.NSAL0400_PCMsgArray", null, null},
	
	{"contrCloDt_H", "contrCloDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"supprCrFlg_H", "supprCrFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"contrTrmnFlg_H", "contrTrmnFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"svcMemoRsnCd_FC", "svcMemoRsnCd_FC", "FC", "99", TYPE_HANKAKUEISU, "4", null},
	{"xxScrItem130Txt_FD", "xxScrItem130Txt_FD", "FD", "99", TYPE_HANKAKUEISU, "130", null},
	{"svcMemoRsnCd_FS", "svcMemoRsnCd_FS", "FS", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_F", "svcCmntTxt_F", "F", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxRsltFlg_W", "xxRsltFlg_W", "W", null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "2000", "business.blap.NSAL0400.NSAL0400_ACMsgArray", null, null},
	
	{"B", "B", null, "2000", "business.blap.NSAL0400.NSAL0400_BCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
		null,	//P
        {"CONTR_CLO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDt_H
        {"SUPPR_CR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supprCrFlg_H
        {"CONTR_TRMN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrTrmnFlg_H
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_FC
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_FD
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_FS
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_F
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg_W
		null,	//A
		null,	//B
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
