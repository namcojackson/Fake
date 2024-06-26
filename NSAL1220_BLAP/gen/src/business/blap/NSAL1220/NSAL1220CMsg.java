//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161202083222000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1220CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1220;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1220 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1220CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** A*/
	public final business.blap.NSAL1220.NSAL1220_ACMsgArray              A;

    /** PRC_ALLOC_RATE_T*/
	public final EZDCBigDecimalItem              prcAllocRate_T;

    /** DS_CONTR_PK*/
	public final EZDCBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDCBigDecimalItem              dsContrDtlPk;

    /** SVC_INV_CHRG_TP_CD*/
	public final EZDCStringItem              svcInvChrgTpCd;

    /** COA_BR_CD*/
	public final EZDCStringItem              coaBrCd;

    /** XX_MODE_CD*/
	public final EZDCStringItem              xxModeCd;


	/**
	 * NSAL1220CMsg is constructor.
	 * The initialization when the instance of NSAL1220CMsg is generated.
	 */
	public NSAL1220CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1220CMsg is constructor.
	 * The initialization when the instance of NSAL1220CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1220CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		A = (business.blap.NSAL1220.NSAL1220_ACMsgArray)newMsgArray("A");
		prcAllocRate_T = (EZDCBigDecimalItem)newItem("prcAllocRate_T");
		dsContrPk = (EZDCBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDCBigDecimalItem)newItem("dsContrDtlPk");
		svcInvChrgTpCd = (EZDCStringItem)newItem("svcInvChrgTpCd");
		coaBrCd = (EZDCStringItem)newItem("coaBrCd");
		xxModeCd = (EZDCStringItem)newItem("xxModeCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1220CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1220CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "40", "business.blap.NSAL1220.NSAL1220_ACMsgArray", null, null},
	
	{"prcAllocRate_T", "prcAllocRate_T", "T", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvChrgTpCd", "svcInvChrgTpCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
		null,	//A
        {"PRC_ALLOC_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocRate_T
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_INV_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvChrgTpCd
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
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

