//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160122084449000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC035001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC035001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC035001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_CONTR_NUM*/
	public final EZDPStringItem              dsContrNum;

    /** PRNT_DS_CONTR_BLLG_SCHD_PK*/
	public final EZDPBigDecimalItem              prntDsContrBllgSchdPk;

    /** BLLG_DT*/
	public final EZDPDateItem              bllgDt;

    /** SVC_CR_REBIL_PK*/
	public final EZDPBigDecimalItem              svcCrRebilPk;

    /** SVC_CR_REBIL_DTL_PK*/
	public final EZDPBigDecimalItem              svcCrRebilDtlPk;

    /** FLEET_READ_ROLL_UP_PK*/
	public final EZDPBigDecimalItem              fleetReadRollUpPk;

    /** xxMsgIdList*/
	public final business.parts.NSZC035001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC035001PMsg is constructor.
	 * The initialization when the instance of NSZC035001PMsg is generated.
	 */
	public NSZC035001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC035001PMsg is constructor.
	 * The initialization when the instance of NSZC035001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC035001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsContrNum = (EZDPStringItem)newItem("dsContrNum");
		prntDsContrBllgSchdPk = (EZDPBigDecimalItem)newItem("prntDsContrBllgSchdPk");
		bllgDt = (EZDPDateItem)newItem("bllgDt");
		svcCrRebilPk = (EZDPBigDecimalItem)newItem("svcCrRebilPk");
		svcCrRebilDtlPk = (EZDPBigDecimalItem)newItem("svcCrRebilDtlPk");
		fleetReadRollUpPk = (EZDPBigDecimalItem)newItem("fleetReadRollUpPk");
		xxMsgIdList = (business.parts.NSZC035001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC035001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC035001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prntDsContrBllgSchdPk", "prntDsContrBllgSchdPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"bllgDt", "bllgDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcCrRebilPk", "svcCrRebilPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilDtlPk", "svcCrRebilDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"fleetReadRollUpPk", "fleetReadRollUpPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgIdList", "xxMsgIdList", null, "2000", "business.parts.NSZC035001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"PRNT_DS_CONTR_BLLG_SCHD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsContrBllgSchdPk
        {"BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgDt
        {"SVC_CR_REBIL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk
        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk
        {"FLEET_READ_ROLL_UP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fleetReadRollUpPk
		null,	//xxMsgIdList
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

