//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190626131904000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0380CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0380;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0380 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0380CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** P*/
	public final business.blap.NSAL0380.NSAL0380_PCMsgArray              P;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SER_NUM_HF*/
	public final EZDCStringItem              serNum_HF;

    /** SER_NUM_HT*/
	public final EZDCStringItem              serNum_HT;

    /** SVC_MACH_MSTR_PK_HF*/
	public final EZDCBigDecimalItem              svcMachMstrPk_HF;

    /** SVC_MACH_MSTR_PK_HT*/
	public final EZDCBigDecimalItem              svcMachMstrPk_HT;

    /** CONTR_AUTO_RNW_TP_CD_H*/
	public final EZDCStringItem              contrAutoRnwTpCd_H;

    /** RNW_BASE_FLG_H*/
	public final EZDCStringItem              rnwBaseFlg_H;

    /** RNW_USG_FLG_H*/
	public final EZDCStringItem              rnwUsgFlg_H;

    /** AUTO_RNW_FLG_H*/
	public final EZDCStringItem              autoRnwFlg_H;

    /** RNW_PRC_METH_CD_H*/
	public final EZDCStringItem              rnwPrcMethCd_H;

    /** BEF_END_RNW_DAYS_AOT_H*/
	public final EZDCBigDecimalItem              befEndRnwDaysAot_H;

    /** MAX_PRC_UP_RATIO_H*/
	public final EZDCBigDecimalItem              maxPrcUpRatio_H;

    /** BASE_PRC_UP_RATIO_H*/
	public final EZDCBigDecimalItem              basePrcUpRatio_H;

    /** MTR_PRC_UP_RATIO_H*/
	public final EZDCBigDecimalItem              mtrPrcUpRatio_H;

    /** MAX_CONTR_RNW_CNT_H*/
	public final EZDCBigDecimalItem              maxContrRnwCnt_H;

    /** SVC_MEMO_RSN_CD_H*/
	public final EZDCStringItem              svcMemoRsnCd_H;

    /** SVC_CMNT_TXT_H*/
	public final EZDCStringItem              svcCmntTxt_H;

    /** CONTR_AUTO_RNW_TP_CD_PL*/
	public final EZDCStringItemArray              contrAutoRnwTpCd_PL;

    /** CONTR_AUTO_RNW_TP_DESC_TXT_PL*/
	public final EZDCStringItemArray              contrAutoRnwTpDescTxt_PL;

    /** RNW_PRC_METH_CD_PL*/
	public final EZDCStringItemArray              rnwPrcMethCd_PL;

    /** RNW_PRC_METH_DESC_TXT_PL*/
	public final EZDCStringItemArray              rnwPrcMethDescTxt_PL;

    /** SVC_MEMO_RSN_CD_PL*/
	public final EZDCStringItemArray              svcMemoRsnCd_PL;

    /** SVC_MEMO_RSN_DESC_TXT_PL*/
	public final EZDCStringItemArray              svcMemoRsnDescTxt_PL;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** A*/
	public final business.blap.NSAL0380.NSAL0380_ACMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_NUM_EV*/
	public final EZDCBigDecimalItem              xxNum_EV;


	/**
	 * NSAL0380CMsg is constructor.
	 * The initialization when the instance of NSAL0380CMsg is generated.
	 */
	public NSAL0380CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0380CMsg is constructor.
	 * The initialization when the instance of NSAL0380CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0380CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		P = (business.blap.NSAL0380.NSAL0380_PCMsgArray)newMsgArray("P");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		serNum_HF = (EZDCStringItem)newItem("serNum_HF");
		serNum_HT = (EZDCStringItem)newItem("serNum_HT");
		svcMachMstrPk_HF = (EZDCBigDecimalItem)newItem("svcMachMstrPk_HF");
		svcMachMstrPk_HT = (EZDCBigDecimalItem)newItem("svcMachMstrPk_HT");
		contrAutoRnwTpCd_H = (EZDCStringItem)newItem("contrAutoRnwTpCd_H");
		rnwBaseFlg_H = (EZDCStringItem)newItem("rnwBaseFlg_H");
		rnwUsgFlg_H = (EZDCStringItem)newItem("rnwUsgFlg_H");
		autoRnwFlg_H = (EZDCStringItem)newItem("autoRnwFlg_H");
		rnwPrcMethCd_H = (EZDCStringItem)newItem("rnwPrcMethCd_H");
		befEndRnwDaysAot_H = (EZDCBigDecimalItem)newItem("befEndRnwDaysAot_H");
		maxPrcUpRatio_H = (EZDCBigDecimalItem)newItem("maxPrcUpRatio_H");
		basePrcUpRatio_H = (EZDCBigDecimalItem)newItem("basePrcUpRatio_H");
		mtrPrcUpRatio_H = (EZDCBigDecimalItem)newItem("mtrPrcUpRatio_H");
		maxContrRnwCnt_H = (EZDCBigDecimalItem)newItem("maxContrRnwCnt_H");
		svcMemoRsnCd_H = (EZDCStringItem)newItem("svcMemoRsnCd_H");
		svcCmntTxt_H = (EZDCStringItem)newItem("svcCmntTxt_H");
		contrAutoRnwTpCd_PL = (EZDCStringItemArray)newItemArray("contrAutoRnwTpCd_PL");
		contrAutoRnwTpDescTxt_PL = (EZDCStringItemArray)newItemArray("contrAutoRnwTpDescTxt_PL");
		rnwPrcMethCd_PL = (EZDCStringItemArray)newItemArray("rnwPrcMethCd_PL");
		rnwPrcMethDescTxt_PL = (EZDCStringItemArray)newItemArray("rnwPrcMethDescTxt_PL");
		svcMemoRsnCd_PL = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_PL");
		svcMemoRsnDescTxt_PL = (EZDCStringItemArray)newItemArray("svcMemoRsnDescTxt_PL");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		A = (business.blap.NSAL0380.NSAL0380_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxNum_EV = (EZDCBigDecimalItem)newItem("xxNum_EV");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0380CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0380CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"P", "P", null, "200", "business.blap.NSAL0380.NSAL0380_PCMsgArray", null, null},
	
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"serNum_HF", "serNum_HF", "HF", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_HT", "serNum_HT", "HT", null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk_HF", "svcMachMstrPk_HF", "HF", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk_HT", "svcMachMstrPk_HT", "HT", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrAutoRnwTpCd_H", "contrAutoRnwTpCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"rnwBaseFlg_H", "rnwBaseFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"rnwUsgFlg_H", "rnwUsgFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"autoRnwFlg_H", "autoRnwFlg_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"rnwPrcMethCd_H", "rnwPrcMethCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"befEndRnwDaysAot_H", "befEndRnwDaysAot_H", "H", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"maxPrcUpRatio_H", "maxPrcUpRatio_H", "H", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"basePrcUpRatio_H", "basePrcUpRatio_H", "H", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"mtrPrcUpRatio_H", "mtrPrcUpRatio_H", "H", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"maxContrRnwCnt_H", "maxContrRnwCnt_H", "H", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"svcMemoRsnCd_H", "svcMemoRsnCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_H", "svcCmntTxt_H", "H", null, TYPE_HANKAKUEISU, "4000", null},
	{"contrAutoRnwTpCd_PL", "contrAutoRnwTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "2", null},
	{"contrAutoRnwTpDescTxt_PL", "contrAutoRnwTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"rnwPrcMethCd_PL", "rnwPrcMethCd_PL", "PL", "99", TYPE_HANKAKUEISU, "2", null},
	{"rnwPrcMethDescTxt_PL", "rnwPrcMethDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcMemoRsnCd_PL", "svcMemoRsnCd_PL", "PL", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnDescTxt_PL", "svcMemoRsnDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "200", "business.blap.NSAL0380.NSAL0380_ACMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxNum_EV", "xxNum_EV", "EV", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//P
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_HF
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_HT
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_HF
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_HT
        {"CONTR_AUTO_RNW_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpCd_H
        {"RNW_BASE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwBaseFlg_H
        {"RNW_USG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwUsgFlg_H
        {"AUTO_RNW_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoRnwFlg_H
        {"RNW_PRC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd_H
        {"BEF_END_RNW_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//befEndRnwDaysAot_H
        {"MAX_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcUpRatio_H
        {"BASE_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcUpRatio_H
        {"MTR_PRC_UP_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrPrcUpRatio_H
        {"MAX_CONTR_RNW_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxContrRnwCnt_H
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_H
        {"CONTR_AUTO_RNW_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpCd_PL
        {"CONTR_AUTO_RNW_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrAutoRnwTpDescTxt_PL
        {"RNW_PRC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd_PL
        {"RNW_PRC_METH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethDescTxt_PL
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_PL
        {"SVC_MEMO_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnDescTxt_PL
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EV
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
