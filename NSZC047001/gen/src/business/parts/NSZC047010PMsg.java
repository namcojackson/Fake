//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200923163337000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC047010PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC047010 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC047010PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDPBigDecimalItem              dsContrBllgMtrPk;

    /** DEL_FLG*/
	public final EZDPStringItem              delFlg;

    /** xxMsgIdList*/
	public final business.parts.NSZC047010_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC047010PMsg is constructor.
	 * The initialization when the instance of NSZC047010PMsg is generated.
	 */
	public NSZC047010PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC047010PMsg is constructor.
	 * The initialization when the instance of NSZC047010PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC047010PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		dsContrBllgMtrPk = (EZDPBigDecimalItem)newItem("dsContrBllgMtrPk");
		delFlg = (EZDPStringItem)newItem("delFlg");
		xxMsgIdList = (business.parts.NSZC047010_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC047010PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC047010PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"delFlg", "delFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC047010_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg
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
