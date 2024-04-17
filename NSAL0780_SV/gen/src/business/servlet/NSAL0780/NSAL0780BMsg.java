//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170310111821000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0780BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0780;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0780 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0780BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** DS_ACCT_NM_LK*/
	public final EZDBStringItem              dsAcctNm_LK;

    /** DS_ACCT_NM_H*/
	public final EZDBStringItem              dsAcctNm_H;

    /** DS_ACCT_NUM_LK*/
	public final EZDBStringItem              dsAcctNum_LK;

    /** DS_ACCT_NUM_H*/
	public final EZDBStringItem              dsAcctNum_H;

    /** SER_NUM_LK*/
	public final EZDBStringItem              serNum_LK;

    /** SER_NUM_H*/
	public final EZDBStringItem              serNum_H;

    /** DS_CONTR_NUM_LK*/
	public final EZDBStringItem              dsContrNum_LK;

    /** DS_CONTR_NUM_H*/
	public final EZDBStringItem              dsContrNum_H;

    /** BLLG_FROM_DT_H*/
	public final EZDBDateItem              bllgFromDt_H;

    /** BLLG_THRU_DT_H*/
	public final EZDBDateItem              bllgThruDt_H;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDBStringItem              xxChkBox_H2;

    /** A*/
	public final business.servlet.NSAL0780.NSAL0780_ABMsgArray              A;

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

    /** XX_POP_PRM_00*/
	public final EZDBStringItem              xxPopPrm_00;

    /** XX_POP_PRM_01*/
	public final EZDBStringItem              xxPopPrm_01;

    /** XX_POP_PRM_02*/
	public final EZDBStringItem              xxPopPrm_02;

    /** XX_POP_PRM_03*/
	public final EZDBStringItem              xxPopPrm_03;

    /** XX_POP_PRM_04*/
	public final EZDBStringItem              xxPopPrm_04;

    /** XX_POP_PRM_05*/
	public final EZDBStringItem              xxPopPrm_05;

    /** XX_POP_PRM_06*/
	public final EZDBStringItem              xxPopPrm_06;

    /** XX_POP_PRM_07*/
	public final EZDBStringItem              xxPopPrm_07;

    /** XX_POP_PRM_08*/
	public final EZDBStringItem              xxPopPrm_08;

    /** XX_POP_PRM_09*/
	public final EZDBStringItem              xxPopPrm_09;

    /** XX_POP_PRM_10*/
	public final EZDBStringItem              xxPopPrm_10;

    /** XX_POP_PRM_11*/
	public final EZDBStringItem              xxPopPrm_11;

    /** XX_POP_PRM_12*/
	public final EZDBStringItem              xxPopPrm_12;

    /** XX_POP_PRM_13*/
	public final EZDBStringItem              xxPopPrm_13;

    /** XX_POP_PRM_14*/
	public final EZDBStringItem              xxPopPrm_14;

    /** DS_CONTR_PK_P*/
	public final EZDBBigDecimalItem              dsContrPk_P;

    /** DS_CONTR_DTL_PK_P*/
	public final EZDBBigDecimalItem              dsContrDtlPk_P;

    /** X*/
	public final business.servlet.NSAL0780.NSAL0780_XBMsgArray              X;

    /** O*/
	public final business.servlet.NSAL0780.NSAL0780_OBMsgArray              O;

    /** P*/
	public final business.servlet.NSAL0780.NSAL0780_PBMsgArray              P;


	/**
	 * NSAL0780BMsg is constructor.
	 * The initialization when the instance of NSAL0780BMsg is generated.
	 */
	public NSAL0780BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0780BMsg is constructor.
	 * The initialization when the instance of NSAL0780BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0780BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		dsAcctNm_LK = (EZDBStringItem)newItem("dsAcctNm_LK");
		dsAcctNm_H = (EZDBStringItem)newItem("dsAcctNm_H");
		dsAcctNum_LK = (EZDBStringItem)newItem("dsAcctNum_LK");
		dsAcctNum_H = (EZDBStringItem)newItem("dsAcctNum_H");
		serNum_LK = (EZDBStringItem)newItem("serNum_LK");
		serNum_H = (EZDBStringItem)newItem("serNum_H");
		dsContrNum_LK = (EZDBStringItem)newItem("dsContrNum_LK");
		dsContrNum_H = (EZDBStringItem)newItem("dsContrNum_H");
		bllgFromDt_H = (EZDBDateItem)newItem("bllgFromDt_H");
		bllgThruDt_H = (EZDBDateItem)newItem("bllgThruDt_H");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDBStringItem)newItem("xxChkBox_H2");
		A = (business.servlet.NSAL0780.NSAL0780_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxPopPrm_00 = (EZDBStringItem)newItem("xxPopPrm_00");
		xxPopPrm_01 = (EZDBStringItem)newItem("xxPopPrm_01");
		xxPopPrm_02 = (EZDBStringItem)newItem("xxPopPrm_02");
		xxPopPrm_03 = (EZDBStringItem)newItem("xxPopPrm_03");
		xxPopPrm_04 = (EZDBStringItem)newItem("xxPopPrm_04");
		xxPopPrm_05 = (EZDBStringItem)newItem("xxPopPrm_05");
		xxPopPrm_06 = (EZDBStringItem)newItem("xxPopPrm_06");
		xxPopPrm_07 = (EZDBStringItem)newItem("xxPopPrm_07");
		xxPopPrm_08 = (EZDBStringItem)newItem("xxPopPrm_08");
		xxPopPrm_09 = (EZDBStringItem)newItem("xxPopPrm_09");
		xxPopPrm_10 = (EZDBStringItem)newItem("xxPopPrm_10");
		xxPopPrm_11 = (EZDBStringItem)newItem("xxPopPrm_11");
		xxPopPrm_12 = (EZDBStringItem)newItem("xxPopPrm_12");
		xxPopPrm_13 = (EZDBStringItem)newItem("xxPopPrm_13");
		xxPopPrm_14 = (EZDBStringItem)newItem("xxPopPrm_14");
		dsContrPk_P = (EZDBBigDecimalItem)newItem("dsContrPk_P");
		dsContrDtlPk_P = (EZDBBigDecimalItem)newItem("dsContrDtlPk_P");
		X = (business.servlet.NSAL0780.NSAL0780_XBMsgArray)newMsgArray("X");
		O = (business.servlet.NSAL0780.NSAL0780_OBMsgArray)newMsgArray("O");
		P = (business.servlet.NSAL0780.NSAL0780_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0780BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0780BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsAcctNm_LK", "dsAcctNm_LK", "LK", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNm_H", "dsAcctNm_H", "H", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNum_LK", "dsAcctNum_LK", "LK", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_H", "dsAcctNum_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"serNum_LK", "serNum_LK", "LK", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_H", "serNum_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrNum_LK", "dsContrNum_LK", "LK", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrNum_H", "dsContrNum_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"bllgFromDt_H", "bllgFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgThruDt_H", "bllgThruDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "40", "business.servlet.NSAL0780.NSAL0780_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPopPrm_00", "xxPopPrm_00", "00", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_01", "xxPopPrm_01", "01", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_02", "xxPopPrm_02", "02", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_03", "xxPopPrm_03", "03", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_04", "xxPopPrm_04", "04", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_05", "xxPopPrm_05", "05", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_06", "xxPopPrm_06", "06", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_07", "xxPopPrm_07", "07", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_08", "xxPopPrm_08", "08", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_09", "xxPopPrm_09", "09", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_10", "xxPopPrm_10", "10", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_11", "xxPopPrm_11", "11", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_12", "xxPopPrm_12", "12", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_13", "xxPopPrm_13", "13", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_14", "xxPopPrm_14", "14", null, TYPE_HANKAKUEISU, "300", null},
	{"dsContrPk_P", "dsContrPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_P", "dsContrDtlPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"X", "X", null, "200", "business.servlet.NSAL0780.NSAL0780_XBMsgArray", null, null},
	
	{"O", "O", null, "1000", "business.servlet.NSAL0780.NSAL0780_OBMsgArray", null, null},
	
	{"P", "P", null, "1000", "business.servlet.NSAL0780.NSAL0780_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_LK
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_LK
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_LK
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_LK
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H
        {"BLLG_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgFromDt_H
        {"BLLG_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgThruDt_H
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_00
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_01
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_02
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_03
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_04
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_05
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_06
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_07
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_08
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_09
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_10
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_11
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_12
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_13
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_14
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_P
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_P
		null,	//X
		null,	//O
		null,	//P
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

