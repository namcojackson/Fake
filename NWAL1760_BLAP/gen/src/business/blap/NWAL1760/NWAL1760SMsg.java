//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160305152924000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1760SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1760;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1760 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1760SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ORD_CATG_DESC_TXT*/
	public final EZDSStringItem              dsOrdCatgDescTxt;

    /** DS_ORD_TP_DESC_TXT*/
	public final EZDSStringItem              dsOrdTpDescTxt;

    /** CSMP_NUM*/
	public final EZDSStringItem              csmpNum;

    /** DLR_REF_NUM*/
	public final EZDSStringItem              dlrRefNum;

    /** CUST_ACCT_NUM*/
	public final EZDSStringItem              custAcctNum;

    /** DS_ACCT_NM*/
	public final EZDSStringItem              dsAcctNm;

    /** GLBL_CMPY_CD*/
	public final EZDSStringItem              glblCmpyCd;

    /** PRC_BASE_DT*/
	public final EZDSDateItem              prcBaseDt;

    /** PRC_CTX_TP_CD*/
	public final EZDSStringItem              prcCtxTpCd;

    /** DS_ORD_CATG_CD*/
	public final EZDSStringItem              dsOrdCatgCd;

    /** DS_ORD_TP_CD*/
	public final EZDSStringItem              dsOrdTpCd;

    /** PRC_CONTR_NUM*/
	public final EZDSStringItem              prcContrNum;

    /** COA_BR_CD*/
	public final EZDSStringItem              coaBrCd;

    /** PRC_CATG_NM*/
	public final EZDSStringItem              prcCatgNm;

    /** A*/
	public final business.blap.NWAL1760.NWAL1760_ASMsgArray              A;


	/**
	 * NWAL1760SMsg is constructor.
	 * The initialization when the instance of NWAL1760SMsg is generated.
	 */
	public NWAL1760SMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1760SMsg is constructor.
	 * The initialization when the instance of NWAL1760SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1760SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsOrdCatgDescTxt = (EZDSStringItem)newItem("dsOrdCatgDescTxt");
		dsOrdTpDescTxt = (EZDSStringItem)newItem("dsOrdTpDescTxt");
		csmpNum = (EZDSStringItem)newItem("csmpNum");
		dlrRefNum = (EZDSStringItem)newItem("dlrRefNum");
		custAcctNum = (EZDSStringItem)newItem("custAcctNum");
		dsAcctNm = (EZDSStringItem)newItem("dsAcctNm");
		glblCmpyCd = (EZDSStringItem)newItem("glblCmpyCd");
		prcBaseDt = (EZDSDateItem)newItem("prcBaseDt");
		prcCtxTpCd = (EZDSStringItem)newItem("prcCtxTpCd");
		dsOrdCatgCd = (EZDSStringItem)newItem("dsOrdCatgCd");
		dsOrdTpCd = (EZDSStringItem)newItem("dsOrdTpCd");
		prcContrNum = (EZDSStringItem)newItem("prcContrNum");
		coaBrCd = (EZDSStringItem)newItem("coaBrCd");
		prcCatgNm = (EZDSStringItem)newItem("prcCatgNm");
		A = (business.blap.NWAL1760.NWAL1760_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1760SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1760SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsOrdCatgDescTxt", "dsOrdCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt", "dsOrdTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"csmpNum", "csmpNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dlrRefNum", "dlrRefNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"custAcctNum", "custAcctNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcBaseDt", "prcBaseDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"prcCtxTpCd", "prcCtxTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"prcContrNum", "prcContrNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"A", "A", null, "200", "business.blap.NWAL1760.NWAL1760_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt
        {"CSMP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpNum
        {"DLR_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrRefNum
        {"CUST_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custAcctNum
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"PRC_BASE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBaseDt
        {"PRC_CTX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCtxTpCd
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"PRC_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
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
