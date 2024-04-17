//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191105015236000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1060BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1060BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_MSTR_PK*/
	public final EZDBBigDecimalItem              svcMachMstrPk;

    /** XX_FROM_DT*/
	public final EZDBDateItem              xxFromDt;

    /** DS_CONTR_DTL_PK*/
	public final EZDBBigDecimalItem              dsContrDtlPk;

    /** SER_NUM*/
	public final EZDBStringItem              serNum;

    /** A*/
	public final business.servlet.NSAL1060.NSAL1060_ABMsgArray              A;

    /** Q*/
	public final business.servlet.NSAL1060.NSAL1060_QBMsgArray              Q;


	/**
	 * NSAL1060BMsg is constructor.
	 * The initialization when the instance of NSAL1060BMsg is generated.
	 */
	public NSAL1060BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1060BMsg is constructor.
	 * The initialization when the instance of NSAL1060BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1060BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachMstrPk = (EZDBBigDecimalItem)newItem("svcMachMstrPk");
		xxFromDt = (EZDBDateItem)newItem("xxFromDt");
		dsContrDtlPk = (EZDBBigDecimalItem)newItem("dsContrDtlPk");
		serNum = (EZDBStringItem)newItem("serNum");
		A = (business.servlet.NSAL1060.NSAL1060_ABMsgArray)newMsgArray("A");
		Q = (business.servlet.NSAL1060.NSAL1060_QBMsgArray)newMsgArray("Q");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1060BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1060BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"A", "A", null, "200", "business.servlet.NSAL1060.NSAL1060_ABMsgArray", null, null},
	
	{"Q", "Q", null, "200", "business.servlet.NSAL1060.NSAL1060_QBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//xxFromDt
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
		null,	//A
		null,	//Q
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

