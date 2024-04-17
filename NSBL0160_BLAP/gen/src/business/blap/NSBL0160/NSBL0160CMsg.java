//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530175629000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0160CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0160;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0160 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0160CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MEMO_CATG_CD_HD*/
	public final EZDCStringItem              svcMemoCatgCd_HD;

    /** SVC_MEMO_TP_CD_HD*/
	public final EZDCStringItem              svcMemoTpCd_HD;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NSBL0160.NSBL0160_ACMsgArray              A;

    /** SVC_MEMO_RSN_CD_SP*/
	public final EZDCStringItemArray              svcMemoRsnCd_SP;

    /** SVC_MEMO_RSN_DESC_TXT_SP*/
	public final EZDCStringItemArray              svcMemoRsnDescTxt_SP;

    /** SVC_MEMO_RSN_CD_SC*/
	public final EZDCStringItem              svcMemoRsnCd_SC;

    /** XX_FROM_DT_SC*/
	public final EZDCDateItem              xxFromDt_SC;

    /** XX_THRU_DT_SC*/
	public final EZDCDateItem              xxThruDt_SC;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** B*/
	public final business.blap.NSBL0160.NSBL0160_BCMsgArray              B;

    /** C*/
	public final business.blap.NSBL0160.NSBL0160_CCMsgArray              C;

    /** XX_YES_NO_CD*/
	public final EZDCStringItem              xxYesNoCd;


	/**
	 * NSBL0160CMsg is constructor.
	 * The initialization when the instance of NSBL0160CMsg is generated.
	 */
	public NSBL0160CMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0160CMsg is constructor.
	 * The initialization when the instance of NSBL0160CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0160CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMemoCatgCd_HD = (EZDCStringItem)newItem("svcMemoCatgCd_HD");
		svcMemoTpCd_HD = (EZDCStringItem)newItem("svcMemoTpCd_HD");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NSBL0160.NSBL0160_ACMsgArray)newMsgArray("A");
		svcMemoRsnCd_SP = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_SP");
		svcMemoRsnDescTxt_SP = (EZDCStringItemArray)newItemArray("svcMemoRsnDescTxt_SP");
		svcMemoRsnCd_SC = (EZDCStringItem)newItem("svcMemoRsnCd_SC");
		xxFromDt_SC = (EZDCDateItem)newItem("xxFromDt_SC");
		xxThruDt_SC = (EZDCDateItem)newItem("xxThruDt_SC");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		B = (business.blap.NSBL0160.NSBL0160_BCMsgArray)newMsgArray("B");
		C = (business.blap.NSBL0160.NSBL0160_CCMsgArray)newMsgArray("C");
		xxYesNoCd = (EZDCStringItem)newItem("xxYesNoCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0160CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0160CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMemoCatgCd_HD", "svcMemoCatgCd_HD", "HD", null, TYPE_HANKAKUEISU, "2", null},
	{"svcMemoTpCd_HD", "svcMemoTpCd_HD", "HD", null, TYPE_HANKAKUEISU, "2", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "5", "business.blap.NSBL0160.NSBL0160_ACMsgArray", null, null},
	
	{"svcMemoRsnCd_SP", "svcMemoRsnCd_SP", "SP", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnDescTxt_SP", "svcMemoRsnDescTxt_SP", "SP", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcMemoRsnCd_SC", "svcMemoRsnCd_SC", "SC", null, TYPE_HANKAKUEISU, "4", null},
	{"xxFromDt_SC", "xxFromDt_SC", "SC", null, TYPE_NENTSUKIHI, "8", null},
	{"xxThruDt_SC", "xxThruDt_SC", "SC", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "40", "business.blap.NSBL0160.NSBL0160_BCMsgArray", null, null},
	
	{"C", "C", null, "5", "business.blap.NSBL0160.NSBL0160_CCMsgArray", null, null},
	
	{"xxYesNoCd", "xxYesNoCd", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MEMO_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoCatgCd_HD
        {"SVC_MEMO_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoTpCd_HD
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_SP
        {"SVC_MEMO_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnDescTxt_SP
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_SC
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt_SC
        {"XX_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxThruDt_SC
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//B
		null,	//C
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd
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
