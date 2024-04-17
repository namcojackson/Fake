//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190118170851000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0730CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0730 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0730CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** CUST_PO_NUM*/
	public final EZDCStringItem              custPoNum;

    /** PO_FROM_DT*/
	public final EZDCDateItem              poFromDt;

    /** PO_DT*/
	public final EZDCDateItem              poDt;

    /** SVC_MEMO_RSN_CD_H1*/
	public final EZDCStringItemArray              svcMemoRsnCd_H1;

    /** SVC_MEMO_RSN_NM_H2*/
	public final EZDCStringItemArray              svcMemoRsnNm_H2;

    /** SVC_MEMO_RSN_CD_H3*/
	public final EZDCStringItem              svcMemoRsnCd_H3;

    /** SVC_CMNT_TXT*/
	public final EZDCStringItem              svcCmntTxt;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_ROW_NUM*/
	public final EZDCBigDecimalItem              xxRowNum;

    /** A*/
	public final business.blap.NSAL0730.NSAL0730_ACMsgArray              A;

    /** B*/
	public final business.blap.NSAL0730.NSAL0730_BCMsgArray              B;

    /** P*/
	public final business.blap.NSAL0730.NSAL0730_PCMsgArray              P;

    /** XX_NUM*/
	public final EZDCBigDecimalItem              xxNum;

    /** C*/
	public final business.blap.NSAL0730.NSAL0730_CCMsgArray              C;


	/**
	 * NSAL0730CMsg is constructor.
	 * The initialization when the instance of NSAL0730CMsg is generated.
	 */
	public NSAL0730CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0730CMsg is constructor.
	 * The initialization when the instance of NSAL0730CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0730CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		custPoNum = (EZDCStringItem)newItem("custPoNum");
		poFromDt = (EZDCDateItem)newItem("poFromDt");
		poDt = (EZDCDateItem)newItem("poDt");
		svcMemoRsnCd_H1 = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_H1");
		svcMemoRsnNm_H2 = (EZDCStringItemArray)newItemArray("svcMemoRsnNm_H2");
		svcMemoRsnCd_H3 = (EZDCStringItem)newItem("svcMemoRsnCd_H3");
		svcCmntTxt = (EZDCStringItem)newItem("svcCmntTxt");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxRowNum = (EZDCBigDecimalItem)newItem("xxRowNum");
		A = (business.blap.NSAL0730.NSAL0730_ACMsgArray)newMsgArray("A");
		B = (business.blap.NSAL0730.NSAL0730_BCMsgArray)newMsgArray("B");
		P = (business.blap.NSAL0730.NSAL0730_PCMsgArray)newMsgArray("P");
		xxNum = (EZDCBigDecimalItem)newItem("xxNum");
		C = (business.blap.NSAL0730.NSAL0730_CCMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0730CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0730CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"custPoNum", "custPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"poFromDt", "poFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"poDt", "poDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcMemoRsnCd_H1", "svcMemoRsnCd_H1", "H1", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_H2", "svcMemoRsnNm_H2", "H2", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcMemoRsnCd_H3", "svcMemoRsnCd_H3", "H3", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt", "svcCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.NSAL0730.NSAL0730_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NSAL0730.NSAL0730_BCMsgArray", null, null},
	
	{"P", "P", null, "1000", "business.blap.NSAL0730.NSAL0730_PCMsgArray", null, null},
	
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"C", "C", null, "200", "business.blap.NSAL0730.NSAL0730_CCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CUST_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum
        {"PO_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poFromDt
        {"PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDt
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H1
        {"SVC_MEMO_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_H2
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H3
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
		null,	//A
		null,	//B
		null,	//P
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
		null,	//C
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

