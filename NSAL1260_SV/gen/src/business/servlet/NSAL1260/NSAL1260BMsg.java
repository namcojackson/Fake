//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160225081112000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1260BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1260;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1260 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1260BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** DS_CONTR_PK*/
	public final EZDBBigDecimalItem              dsContrPk;

    /** Q*/
	public final business.servlet.NSAL1260.NSAL1260_QBMsgArray              Q;

    /** DS_ACCT_NUM_H*/
	public final EZDBStringItem              dsAcctNum_H;

    /** DS_ACCT_NM_H*/
	public final EZDBStringItem              dsAcctNm_H;

    /** CONTR_VRSN_EFF_FROM_DT_H*/
	public final EZDBDateItem              contrVrsnEffFromDt_H;

    /** CONTR_VRSN_EFF_THRU_DT_H*/
	public final EZDBDateItem              contrVrsnEffThruDt_H;

    /** BLLG_CYCLE_DESC_TXT_H*/
	public final EZDBStringItem              bllgCycleDescTxt_H;

    /** BLLG_CYCLE_CD_H*/
	public final EZDBStringItem              bllgCycleCd_H;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_ERR_FLG*/
	public final EZDBStringItem              xxErrFlg;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** BLLG_CYCLE_CD_L*/
	public final EZDBStringItemArray              bllgCycleCd_L;

    /** BLLG_CYCLE_DESC_TXT_L*/
	public final EZDBStringItemArray              bllgCycleDescTxt_L;

    /** MTR_READ_METH_CD_L*/
	public final EZDBStringItemArray              mtrReadMethCd_L;

    /** MTR_READ_METH_DESC_TXT_L*/
	public final EZDBStringItemArray              mtrReadMethDescTxt_L;

    /** A*/
	public final business.servlet.NSAL1260.NSAL1260_ABMsgArray              A;


	/**
	 * NSAL1260BMsg is constructor.
	 * The initialization when the instance of NSAL1260BMsg is generated.
	 */
	public NSAL1260BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1260BMsg is constructor.
	 * The initialization when the instance of NSAL1260BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1260BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		dsContrPk = (EZDBBigDecimalItem)newItem("dsContrPk");
		Q = (business.servlet.NSAL1260.NSAL1260_QBMsgArray)newMsgArray("Q");
		dsAcctNum_H = (EZDBStringItem)newItem("dsAcctNum_H");
		dsAcctNm_H = (EZDBStringItem)newItem("dsAcctNm_H");
		contrVrsnEffFromDt_H = (EZDBDateItem)newItem("contrVrsnEffFromDt_H");
		contrVrsnEffThruDt_H = (EZDBDateItem)newItem("contrVrsnEffThruDt_H");
		bllgCycleDescTxt_H = (EZDBStringItem)newItem("bllgCycleDescTxt_H");
		bllgCycleCd_H = (EZDBStringItem)newItem("bllgCycleCd_H");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxErrFlg = (EZDBStringItem)newItem("xxErrFlg");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		bllgCycleCd_L = (EZDBStringItemArray)newItemArray("bllgCycleCd_L");
		bllgCycleDescTxt_L = (EZDBStringItemArray)newItemArray("bllgCycleDescTxt_L");
		mtrReadMethCd_L = (EZDBStringItemArray)newItemArray("mtrReadMethCd_L");
		mtrReadMethDescTxt_L = (EZDBStringItemArray)newItemArray("mtrReadMethDescTxt_L");
		A = (business.servlet.NSAL1260.NSAL1260_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1260BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1260BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"Q", "Q", null, "1000", "business.servlet.NSAL1260.NSAL1260_QBMsgArray", null, null},
	
	{"dsAcctNum_H", "dsAcctNum_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H", "dsAcctNm_H", "H", null, TYPE_HANKAKUEISU, "360", null},
	{"contrVrsnEffFromDt_H", "contrVrsnEffFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt_H", "contrVrsnEffThruDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleDescTxt_H", "bllgCycleDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleCd_H", "bllgCycleCd_H", "H", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxErrFlg", "xxErrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"bllgCycleCd_L", "bllgCycleCd_L", "L", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_L", "bllgCycleDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"mtrReadMethCd_L", "mtrReadMethCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethDescTxt_L", "mtrReadMethDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "100", "business.servlet.NSAL1260.NSAL1260_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
		null,	//Q
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrVrsnEffFromDt_H
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrVrsnEffThruDt_H
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_H
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_H
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_ERR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"BLLG_CYCLE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_L
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_L
        {"MTR_READ_METH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_L
        {"MTR_READ_METH_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethDescTxt_L
		null,	//A
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

