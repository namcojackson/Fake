//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180417144507000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1360CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1360 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1360CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MTR_READ_METH_CD_P*/
	public final EZDCStringItem              mtrReadMethCd_P;

    /** CUST_ISS_PO_NUM_P*/
	public final EZDCStringItem              custIssPoNum_P;

    /** CUST_ISS_PO_DT_P*/
	public final EZDCDateItem              custIssPoDt_P;

    /** DS_PO_EXPR_DT_P*/
	public final EZDCDateItem              dsPoExprDt_P;

    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** MTR_READ_METH_CD*/
	public final EZDCStringItem              mtrReadMethCd;

    /** MTR_READ_METH_CD_L*/
	public final EZDCStringItemArray              mtrReadMethCd_L;

    /** MTR_READ_METH_DESC_TXT_L*/
	public final EZDCStringItemArray              mtrReadMethDescTxt_L;

    /** CUST_ISS_PO_NUM*/
	public final EZDCStringItem              custIssPoNum;

    /** CUST_ISS_PO_DT*/
	public final EZDCDateItem              custIssPoDt;


	/**
	 * NSAL1360CMsg is constructor.
	 * The initialization when the instance of NSAL1360CMsg is generated.
	 */
	public NSAL1360CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1360CMsg is constructor.
	 * The initialization when the instance of NSAL1360CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1360CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mtrReadMethCd_P = (EZDCStringItem)newItem("mtrReadMethCd_P");
		custIssPoNum_P = (EZDCStringItem)newItem("custIssPoNum_P");
		custIssPoDt_P = (EZDCDateItem)newItem("custIssPoDt_P");
		dsPoExprDt_P = (EZDCDateItem)newItem("dsPoExprDt_P");
		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		mtrReadMethCd = (EZDCStringItem)newItem("mtrReadMethCd");
		mtrReadMethCd_L = (EZDCStringItemArray)newItemArray("mtrReadMethCd_L");
		mtrReadMethDescTxt_L = (EZDCStringItemArray)newItemArray("mtrReadMethDescTxt_L");
		custIssPoNum = (EZDCStringItem)newItem("custIssPoNum");
		custIssPoDt = (EZDCDateItem)newItem("custIssPoDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1360CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1360CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mtrReadMethCd_P", "mtrReadMethCd_P", "P", null, TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum_P", "custIssPoNum_P", "P", null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt_P", "custIssPoDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"dsPoExprDt_P", "dsPoExprDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"mtrReadMethCd", "mtrReadMethCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethCd_L", "mtrReadMethCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethDescTxt_L", "mtrReadMethDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt", "custIssPoDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_P
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_P
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt_P
        {"DS_PO_EXPR_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsPoExprDt_P
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_L
        {"MTR_READ_METH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethDescTxt_L
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt
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
