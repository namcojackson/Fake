//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20090901165645000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0080_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0080_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_A*/
	public final EZDCBigDecimalItem              xxNum_A;

    /** ML_GRP_ID_A*/
	public final EZDCStringItem              mlGrpId_A;

    /** ML_GRP_NM_A*/
	public final EZDCStringItem              mlGrpNm_A;

    /** ML_GRP_DESC_TXT_A*/
	public final EZDCStringItem              mlGrpDescTxt_A;


	/**
	 * ZZML0080_ACMsg is constructor.
	 * The initialization when the instance of ZZML0080_ACMsg is generated.
	 */
	public ZZML0080_ACMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0080_ACMsg is constructor.
	 * The initialization when the instance of ZZML0080_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0080_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_A = (EZDCBigDecimalItem)newItem("xxNum_A");
		mlGrpId_A = (EZDCStringItem)newItem("mlGrpId_A");
		mlGrpNm_A = (EZDCStringItem)newItem("mlGrpNm_A");
		mlGrpDescTxt_A = (EZDCStringItem)newItem("mlGrpDescTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0080_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0080_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_A", "xxNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mlGrpId_A", "mlGrpId_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"mlGrpNm_A", "mlGrpNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"mlGrpDescTxt_A", "mlGrpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A
        {"ML_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpId_A
        {"ML_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpNm_A
        {"ML_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpDescTxt_A
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
