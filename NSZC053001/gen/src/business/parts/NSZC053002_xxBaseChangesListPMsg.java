//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220429144017000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC053002_xxBaseChangesListPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSZC053002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC053002_xxBaseChangesListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CR_REBIL_DTL_PK*/
	public final EZDPBigDecimalItem              svcCrRebilDtlPk;

    /** ORIG_SVC_INV_NUM*/
	public final EZDPStringItem              origSvcInvNum;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** BASE_BLLG_FROM_DT*/
	public final EZDPDateItem              baseBllgFromDt;

    /** BASE_BLLG_THRU_DT*/
	public final EZDPDateItem              baseBllgThruDt;

    /** NEW_BASE_DEAL_AMT*/
	public final EZDPBigDecimalItem              newBaseDealAmt;

    /** NEW_BASE_UNIT_AMT*/
	public final EZDPBigDecimalItem              newBaseUnitAmt;


	/**
	 * NSZC053002_xxBaseChangesListPMsg is constructor.
	 * The initialization when the instance of NSZC053002_xxBaseChangesListPMsg is generated.
	 */
	public NSZC053002_xxBaseChangesListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC053002_xxBaseChangesListPMsg is constructor.
	 * The initialization when the instance of NSZC053002_xxBaseChangesListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC053002_xxBaseChangesListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcCrRebilDtlPk = (EZDPBigDecimalItem)newItem("svcCrRebilDtlPk");
		origSvcInvNum = (EZDPStringItem)newItem("origSvcInvNum");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		serNum = (EZDPStringItem)newItem("serNum");
		baseBllgFromDt = (EZDPDateItem)newItem("baseBllgFromDt");
		baseBllgThruDt = (EZDPDateItem)newItem("baseBllgThruDt");
		newBaseDealAmt = (EZDPBigDecimalItem)newItem("newBaseDealAmt");
		newBaseUnitAmt = (EZDPBigDecimalItem)newItem("newBaseUnitAmt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC053002_xxBaseChangesListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC053002_xxBaseChangesListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcCrRebilDtlPk", "svcCrRebilDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"origSvcInvNum", "origSvcInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"baseBllgFromDt", "baseBllgFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"baseBllgThruDt", "baseBllgThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"newBaseDealAmt", "newBaseDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newBaseUnitAmt", "newBaseUnitAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk
        {"ORIG_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"BASE_BLLG_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgFromDt
        {"BASE_BLLG_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgThruDt
        {"NEW_BASE_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newBaseDealAmt
        {"NEW_BASE_UNIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newBaseUnitAmt
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

