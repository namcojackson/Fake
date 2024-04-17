//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160122113603000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1080CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1080CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** CUST_INCDT_ID*/
	public final EZDCStringItem              custIncdtId;

    /** SVC_CR_REBIL_STS_DESC_TXT*/
	public final EZDCStringItem              svcCrRebilStsDescTxt;

    /** SVC_CR_REBIL_DESC_TXT*/
	public final EZDCStringItem              svcCrRebilDescTxt;

    /** SVC_CR_REBIL_PK*/
	public final EZDCBigDecimalItem              svcCrRebilPk;

    /** SVC_INV_NUM*/
	public final EZDCStringItem              svcInvNum;

    /** DS_CONTR_NUM*/
	public final EZDCStringItem              dsContrNum;

    /** CONSL_BILL_PK*/
	public final EZDCBigDecimalItem              conslBillPk;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** BLLG_PER_FROM_DT*/
	public final EZDCDateItem              bllgPerFromDt;

    /** BLLG_PER_TO_DT*/
	public final EZDCDateItem              bllgPerToDt;


	/**
	 * NSAL1080CMsg is constructor.
	 * The initialization when the instance of NSAL1080CMsg is generated.
	 */
	public NSAL1080CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1080CMsg is constructor.
	 * The initialization when the instance of NSAL1080CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1080CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		custIncdtId = (EZDCStringItem)newItem("custIncdtId");
		svcCrRebilStsDescTxt = (EZDCStringItem)newItem("svcCrRebilStsDescTxt");
		svcCrRebilDescTxt = (EZDCStringItem)newItem("svcCrRebilDescTxt");
		svcCrRebilPk = (EZDCBigDecimalItem)newItem("svcCrRebilPk");
		svcInvNum = (EZDCStringItem)newItem("svcInvNum");
		dsContrNum = (EZDCStringItem)newItem("dsContrNum");
		conslBillPk = (EZDCBigDecimalItem)newItem("conslBillPk");
		serNum = (EZDCStringItem)newItem("serNum");
		bllgPerFromDt = (EZDCDateItem)newItem("bllgPerFromDt");
		bllgPerToDt = (EZDCDateItem)newItem("bllgPerToDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1080CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1080CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"custIncdtId", "custIncdtId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcCrRebilStsDescTxt", "svcCrRebilStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"svcCrRebilDescTxt", "svcCrRebilDescTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"svcCrRebilPk", "svcCrRebilPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvNum", "svcInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"conslBillPk", "conslBillPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"bllgPerFromDt", "bllgPerFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerToDt", "bllgPerToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"CUST_INCDT_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIncdtId
        {"SVC_CR_REBIL_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilStsDescTxt
        {"SVC_CR_REBIL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDescTxt
        {"SVC_CR_REBIL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"CONSL_BILL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"BLLG_PER_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerFromDt
        {"BLLG_PER_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerToDt
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
