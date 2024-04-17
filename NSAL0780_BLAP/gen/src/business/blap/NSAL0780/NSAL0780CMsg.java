//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170310105823000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0780CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0780;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0780 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0780CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** DS_ACCT_NM_LK*/
	public final EZDCStringItem              dsAcctNm_LK;

    /** DS_ACCT_NM_H*/
	public final EZDCStringItem              dsAcctNm_H;

    /** DS_ACCT_NUM_LK*/
	public final EZDCStringItem              dsAcctNum_LK;

    /** DS_ACCT_NUM_H*/
	public final EZDCStringItem              dsAcctNum_H;

    /** SER_NUM_LK*/
	public final EZDCStringItem              serNum_LK;

    /** SER_NUM_H*/
	public final EZDCStringItem              serNum_H;

    /** DS_CONTR_NUM_LK*/
	public final EZDCStringItem              dsContrNum_LK;

    /** DS_CONTR_NUM_H*/
	public final EZDCStringItem              dsContrNum_H;

    /** BLLG_FROM_DT_H*/
	public final EZDCDateItem              bllgFromDt_H;

    /** BLLG_THRU_DT_H*/
	public final EZDCDateItem              bllgThruDt_H;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** XX_ROW_NUM_H*/
	public final EZDCBigDecimalItem              xxRowNum_H;

    /** A*/
	public final business.blap.NSAL0780.NSAL0780_ACMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** P*/
	public final business.blap.NSAL0780.NSAL0780_PCMsgArray              P;


	/**
	 * NSAL0780CMsg is constructor.
	 * The initialization when the instance of NSAL0780CMsg is generated.
	 */
	public NSAL0780CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0780CMsg is constructor.
	 * The initialization when the instance of NSAL0780CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0780CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		dsAcctNm_LK = (EZDCStringItem)newItem("dsAcctNm_LK");
		dsAcctNm_H = (EZDCStringItem)newItem("dsAcctNm_H");
		dsAcctNum_LK = (EZDCStringItem)newItem("dsAcctNum_LK");
		dsAcctNum_H = (EZDCStringItem)newItem("dsAcctNum_H");
		serNum_LK = (EZDCStringItem)newItem("serNum_LK");
		serNum_H = (EZDCStringItem)newItem("serNum_H");
		dsContrNum_LK = (EZDCStringItem)newItem("dsContrNum_LK");
		dsContrNum_H = (EZDCStringItem)newItem("dsContrNum_H");
		bllgFromDt_H = (EZDCDateItem)newItem("bllgFromDt_H");
		bllgThruDt_H = (EZDCDateItem)newItem("bllgThruDt_H");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		xxRowNum_H = (EZDCBigDecimalItem)newItem("xxRowNum_H");
		A = (business.blap.NSAL0780.NSAL0780_ACMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		P = (business.blap.NSAL0780.NSAL0780_PCMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0780CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0780CMsgArray();
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
	{"xxRowNum_H", "xxRowNum_H", "H", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "40", "business.blap.NSAL0780.NSAL0780_ACMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"P", "P", null, "1000", "business.blap.NSAL0780.NSAL0780_PCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_LK
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_LK
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_LK
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_LK
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H
        {"BLLG_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFromDt_H
        {"BLLG_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgThruDt_H
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_H
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
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
