//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151015103654000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC047004_xxMtrLineListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC047004 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC047004_xxMtrLineListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_BLLG_MTR_PK_ML*/
	public final EZDPBigDecimalItem              dsContrBllgMtrPk_ML;

    /** CONTR_XS_COPY_PK_ML*/
	public final EZDPBigDecimalItem              contrXsCopyPk_ML;

    /** XS_MTR_COPY_QTY_ML*/
	public final EZDPBigDecimalItem              xsMtrCopyQty_ML;

    /** XS_MTR_AMT_RATE_ML*/
	public final EZDPBigDecimalItem              xsMtrAmtRate_ML;

    /** XS_MTR_FIRST_FLG_ML*/
	public final EZDPStringItem              xsMtrFirstFlg_ML;

    /** DS_CONTR_PRC_EFF_PK_ML*/
	public final EZDPBigDecimalItem              dsContrPrcEffPk_ML;

    /** DS_CONTR_PRC_EFF_MTR_PK_ML*/
	public final EZDPBigDecimalItem              dsContrPrcEffMtrPk_ML;


	/**
	 * NSZC047004_xxMtrLineListPMsg is constructor.
	 * The initialization when the instance of NSZC047004_xxMtrLineListPMsg is generated.
	 */
	public NSZC047004_xxMtrLineListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC047004_xxMtrLineListPMsg is constructor.
	 * The initialization when the instance of NSZC047004_xxMtrLineListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC047004_xxMtrLineListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrBllgMtrPk_ML = (EZDPBigDecimalItem)newItem("dsContrBllgMtrPk_ML");
		contrXsCopyPk_ML = (EZDPBigDecimalItem)newItem("contrXsCopyPk_ML");
		xsMtrCopyQty_ML = (EZDPBigDecimalItem)newItem("xsMtrCopyQty_ML");
		xsMtrAmtRate_ML = (EZDPBigDecimalItem)newItem("xsMtrAmtRate_ML");
		xsMtrFirstFlg_ML = (EZDPStringItem)newItem("xsMtrFirstFlg_ML");
		dsContrPrcEffPk_ML = (EZDPBigDecimalItem)newItem("dsContrPrcEffPk_ML");
		dsContrPrcEffMtrPk_ML = (EZDPBigDecimalItem)newItem("dsContrPrcEffMtrPk_ML");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC047004_xxMtrLineListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC047004_xxMtrLineListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrBllgMtrPk_ML", "dsContrBllgMtrPk_ML", "ML", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrXsCopyPk_ML", "contrXsCopyPk_ML", "ML", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xsMtrCopyQty_ML", "xsMtrCopyQty_ML", "ML", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_ML", "xsMtrAmtRate_ML", "ML", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"xsMtrFirstFlg_ML", "xsMtrFirstFlg_ML", "ML", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrPrcEffPk_ML", "dsContrPrcEffPk_ML", "ML", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffMtrPk_ML", "dsContrPrcEffMtrPk_ML", "ML", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_ML
        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_ML
        {"XS_MTR_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrCopyQty_ML
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_ML
        {"XS_MTR_FIRST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrFirstFlg_ML
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_ML
        {"DS_CONTR_PRC_EFF_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffMtrPk_ML
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
