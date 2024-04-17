//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170831115855000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0630BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0630;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0630 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0630BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SVC_MEMO_RSN_CD_H*/
	public final EZDBStringItem              svcMemoRsnCd_H;

    /** SVC_MEMO_RSN_CD_L*/
	public final EZDBStringItemArray              svcMemoRsnCd_L;

    /** SVC_MEMO_RSN_NM_L*/
	public final EZDBStringItemArray              svcMemoRsnNm_L;

    /** SVC_CMNT_TXT*/
	public final EZDBStringItem              svcCmntTxt;

    /** XX_CHK_BOX_H*/
	public final EZDBStringItem              xxChkBox_H;

    /** P*/
	public final business.servlet.NSAL0630.NSAL0630_PBMsgArray              P;

    /** A*/
	public final business.servlet.NSAL0630.NSAL0630_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;


	/**
	 * NSAL0630BMsg is constructor.
	 * The initialization when the instance of NSAL0630BMsg is generated.
	 */
	public NSAL0630BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0630BMsg is constructor.
	 * The initialization when the instance of NSAL0630BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0630BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		svcMemoRsnCd_H = (EZDBStringItem)newItem("svcMemoRsnCd_H");
		svcMemoRsnCd_L = (EZDBStringItemArray)newItemArray("svcMemoRsnCd_L");
		svcMemoRsnNm_L = (EZDBStringItemArray)newItemArray("svcMemoRsnNm_L");
		svcCmntTxt = (EZDBStringItem)newItem("svcCmntTxt");
		xxChkBox_H = (EZDBStringItem)newItem("xxChkBox_H");
		P = (business.servlet.NSAL0630.NSAL0630_PBMsgArray)newMsgArray("P");
		A = (business.servlet.NSAL0630.NSAL0630_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0630BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0630BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnCd_H", "svcMemoRsnCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnCd_L", "svcMemoRsnCd_L", "L", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_L", "svcMemoRsnNm_L", "L", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_H", "xxChkBox_H", "H", null, TYPE_HANKAKUEIJI, "1", null},
	{"P", "P", null, "1000", "business.servlet.NSAL0630.NSAL0630_PBMsgArray", null, null},
	
	{"A", "A", null, "200", "business.servlet.NSAL0630.NSAL0630_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SVC_MEMO_RSN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H
        {"SVC_MEMO_RSN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_L
        {"SVC_MEMO_RSN_NM", YES,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_L
        {"SVC_CMNT_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H
		null,	//P
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
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
