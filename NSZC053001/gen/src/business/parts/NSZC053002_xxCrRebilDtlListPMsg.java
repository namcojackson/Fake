//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220429144017000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC053002_xxCrRebilDtlListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSZC053002_xxCrRebilDtlListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CR_REBIL_DTL_PK*/
	public final EZDPBigDecimalItem              svcCrRebilDtlPk;

    /** ORIG_SVC_INV_NUM*/
	public final EZDPStringItem              origSvcInvNum;

    /** CR_SVC_INV_NUM*/
	public final EZDPStringItem              crSvcInvNum;

    /** REBIL_SVC_INV_NUM*/
	public final EZDPStringItem              rebilSvcInvNum;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** INV_PRINT_FLG*/
	public final EZDPStringItem              invPrintFlg;

    /** BLLG_MTR_LB_CD*/
	public final EZDPStringItem              bllgMtrLbCd;


	/**
	 * NSZC053002_xxCrRebilDtlListPMsg is constructor.
	 * The initialization when the instance of NSZC053002_xxCrRebilDtlListPMsg is generated.
	 */
	public NSZC053002_xxCrRebilDtlListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC053002_xxCrRebilDtlListPMsg is constructor.
	 * The initialization when the instance of NSZC053002_xxCrRebilDtlListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC053002_xxCrRebilDtlListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcCrRebilDtlPk = (EZDPBigDecimalItem)newItem("svcCrRebilDtlPk");
		origSvcInvNum = (EZDPStringItem)newItem("origSvcInvNum");
		crSvcInvNum = (EZDPStringItem)newItem("crSvcInvNum");
		rebilSvcInvNum = (EZDPStringItem)newItem("rebilSvcInvNum");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		serNum = (EZDPStringItem)newItem("serNum");
		invPrintFlg = (EZDPStringItem)newItem("invPrintFlg");
		bllgMtrLbCd = (EZDPStringItem)newItem("bllgMtrLbCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC053002_xxCrRebilDtlListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC053002_xxCrRebilDtlListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcCrRebilDtlPk", "svcCrRebilDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"origSvcInvNum", "origSvcInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"crSvcInvNum", "crSvcInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"rebilSvcInvNum", "rebilSvcInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"invPrintFlg", "invPrintFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk
        {"ORIG_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum
        {"CR_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crSvcInvNum
        {"REBIL_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rebilSvcInvNum
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"INV_PRINT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintFlg
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
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
