//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170224142311000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0660BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0660;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0660 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0660BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SVC_MEMO_RSN_CD_H1*/
	public final EZDBStringItemArray              svcMemoRsnCd_H1;

    /** SVC_MEMO_RSN_NM_H2*/
	public final EZDBStringItemArray              svcMemoRsnNm_H2;

    /** SVC_MEMO_RSN_CD_H3*/
	public final EZDBStringItem              svcMemoRsnCd_H3;

    /** SVC_CMNT_TXT_H1*/
	public final EZDBStringItem              svcCmntTxt_H1;

    /** XX_CHK_BOX_AL*/
	public final EZDBStringItem              xxChkBox_AL;

    /** XX_ROW_NUM*/
	public final EZDBBigDecimalItem              xxRowNum;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** P*/
	public final business.servlet.NSAL0660.NSAL0660_PBMsgArray              P;

    /** A*/
	public final business.servlet.NSAL0660.NSAL0660_ABMsgArray              A;


	/**
	 * NSAL0660BMsg is constructor.
	 * The initialization when the instance of NSAL0660BMsg is generated.
	 */
	public NSAL0660BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0660BMsg is constructor.
	 * The initialization when the instance of NSAL0660BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0660BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		svcMemoRsnCd_H1 = (EZDBStringItemArray)newItemArray("svcMemoRsnCd_H1");
		svcMemoRsnNm_H2 = (EZDBStringItemArray)newItemArray("svcMemoRsnNm_H2");
		svcMemoRsnCd_H3 = (EZDBStringItem)newItem("svcMemoRsnCd_H3");
		svcCmntTxt_H1 = (EZDBStringItem)newItem("svcCmntTxt_H1");
		xxChkBox_AL = (EZDBStringItem)newItem("xxChkBox_AL");
		xxRowNum = (EZDBBigDecimalItem)newItem("xxRowNum");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		P = (business.servlet.NSAL0660.NSAL0660_PBMsgArray)newMsgArray("P");
		A = (business.servlet.NSAL0660.NSAL0660_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0660BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0660BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnCd_H1", "svcMemoRsnCd_H1", "H1", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_H2", "svcMemoRsnNm_H2", "H2", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcMemoRsnCd_H3", "svcMemoRsnCd_H3", "H3", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_H1", "svcCmntTxt_H1", "H1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_AL", "xxChkBox_AL", "AL", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"P", "P", null, "1000", "business.servlet.NSAL0660.NSAL0660_PBMsgArray", null, null},
	
	{"A", "A", null, "200", "business.servlet.NSAL0660.NSAL0660_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SVC_MEMO_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H1
        {"SVC_MEMO_RSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_H2
        {"SVC_MEMO_RSN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H3
        {"SVC_CMNT_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_H1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//P
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

