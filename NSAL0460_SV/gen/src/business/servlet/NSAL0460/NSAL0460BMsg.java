//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240216091639000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0460BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0460;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0460 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0460BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** SVC_MEMO_RSN_CD_PS*/
	public final EZDBStringItem              svcMemoRsnCd_PS;

    /** SVC_MEMO_RSN_CD_PC*/
	public final EZDBStringItemArray              svcMemoRsnCd_PC;

    /** SVC_MEMO_RSN_NM_PC*/
	public final EZDBStringItemArray              svcMemoRsnNm_PC;

    /** SVC_CMNT_TXT*/
	public final EZDBStringItem              svcCmntTxt;

    /** XX_HLD_FLG*/
	public final EZDBStringItem              xxHldFlg;

    /** XX_CHK_BOX_H*/
	public final EZDBStringItem              xxChkBox_H;

    /** A*/
	public final business.servlet.NSAL0460.NSAL0460_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** DS_CONTR_PK_OP*/
	public final EZDBBigDecimalItem              dsContrPk_OP;

    /** DS_CONTR_DTL_PK_OP*/
	public final EZDBBigDecimalItem              dsContrDtlPk_OP;

    /** SVC_MACH_MSTR_PK_OP*/
	public final EZDBBigDecimalItem              svcMachMstrPk_OP;

    /** START_DT_OP*/
	public final EZDBDateItem              startDt_OP;

    /** P*/
	public final business.servlet.NSAL0460.NSAL0460_PBMsgArray              P;

    /** Q*/
	public final business.servlet.NSAL0460.NSAL0460_QBMsgArray              Q;


	/**
	 * NSAL0460BMsg is constructor.
	 * The initialization when the instance of NSAL0460BMsg is generated.
	 */
	public NSAL0460BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0460BMsg is constructor.
	 * The initialization when the instance of NSAL0460BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0460BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		svcMemoRsnCd_PS = (EZDBStringItem)newItem("svcMemoRsnCd_PS");
		svcMemoRsnCd_PC = (EZDBStringItemArray)newItemArray("svcMemoRsnCd_PC");
		svcMemoRsnNm_PC = (EZDBStringItemArray)newItemArray("svcMemoRsnNm_PC");
		svcCmntTxt = (EZDBStringItem)newItem("svcCmntTxt");
		xxHldFlg = (EZDBStringItem)newItem("xxHldFlg");
		xxChkBox_H = (EZDBStringItem)newItem("xxChkBox_H");
		A = (business.servlet.NSAL0460.NSAL0460_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		dsContrPk_OP = (EZDBBigDecimalItem)newItem("dsContrPk_OP");
		dsContrDtlPk_OP = (EZDBBigDecimalItem)newItem("dsContrDtlPk_OP");
		svcMachMstrPk_OP = (EZDBBigDecimalItem)newItem("svcMachMstrPk_OP");
		startDt_OP = (EZDBDateItem)newItem("startDt_OP");
		P = (business.servlet.NSAL0460.NSAL0460_PBMsgArray)newMsgArray("P");
		Q = (business.servlet.NSAL0460.NSAL0460_QBMsgArray)newMsgArray("Q");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0460BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0460BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"svcMemoRsnCd_PS", "svcMemoRsnCd_PS", "PS", null, TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnCd_PC", "svcMemoRsnCd_PC", "PC", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_PC", "svcMemoRsnNm_PC", "PC", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxHldFlg", "xxHldFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_H", "xxChkBox_H", "H", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "200", "business.servlet.NSAL0460.NSAL0460_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"dsContrPk_OP", "dsContrPk_OP", "OP", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_OP", "dsContrDtlPk_OP", "OP", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk_OP", "svcMachMstrPk_OP", "OP", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"startDt_OP", "startDt_OP", "OP", null, TYPE_NENTSUKIHI, "8", null},
	{"P", "P", null, "200", "business.servlet.NSAL0460.NSAL0460_PBMsgArray", null, null},
	
	{"Q", "Q", null, "200", "business.servlet.NSAL0460.NSAL0460_QBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"SVC_MEMO_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_PS
        {"SVC_MEMO_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_PC
        {"SVC_MEMO_RSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_PC
        {"SVC_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"XX_HLD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHldFlg
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_OP
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_OP
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_OP
        {"START_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//startDt_OP
		null,	//P
		null,	//Q
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
