//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220328112903000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC046001_xxDsContrBrAllocListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC046001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC046001_xxDsContrBrAllocListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_CONTR_BR_ALLOC_PK*/
	public final EZDPBigDecimalItem              dsContrBrAllocPk;

    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** SVC_INV_CHRG_TP_CD*/
	public final EZDPStringItem              svcInvChrgTpCd;

    /** COA_BR_CD*/
	public final EZDPStringItem              coaBrCd;

    /** PRC_ALLOC_RATE*/
	public final EZDPBigDecimalItem              prcAllocRate;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MULT_MSG_DPLY_TXT*/
	public final EZDPStringItem              xxDsMultMsgDplyTxt;


	/**
	 * NSZC046001_xxDsContrBrAllocListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrBrAllocListPMsg is generated.
	 */
	public NSZC046001_xxDsContrBrAllocListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC046001_xxDsContrBrAllocListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrBrAllocListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC046001_xxDsContrBrAllocListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsContrBrAllocPk = (EZDPBigDecimalItem)newItem("dsContrBrAllocPk");
		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		svcInvChrgTpCd = (EZDPStringItem)newItem("svcInvChrgTpCd");
		coaBrCd = (EZDPStringItem)newItem("coaBrCd");
		prcAllocRate = (EZDPBigDecimalItem)newItem("prcAllocRate");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMultMsgDplyTxt = (EZDPStringItem)newItem("xxDsMultMsgDplyTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC046001_xxDsContrBrAllocListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC046001_xxDsContrBrAllocListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrBrAllocPk", "dsContrBrAllocPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvChrgTpCd", "svcInvChrgTpCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"prcAllocRate", "prcAllocRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMultMsgDplyTxt", "xxDsMultMsgDplyTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_BR_ALLOC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBrAllocPk
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_INV_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvChrgTpCd
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"PRC_ALLOC_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocRate
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt
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
