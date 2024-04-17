//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20200226172518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0030_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0030_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_B*/
	public final EZDSBigDecimalItem              xxNum_B;

    /** ML_ADDR_TP_CD_B*/
	public final EZDSStringItem              mlAddrTpCd_B;

    /** ML_USR_ADDR_B*/
	public final EZDSStringItem              mlUsrAddr_B;

    /** ML_USR_NM_B*/
	public final EZDSStringItem              mlUsrNm_B;

    /** ML_USR_ID_B*/
	public final EZDSStringItem              mlUsrId_B;


	/**
	 * ZZML0030_BSMsg is constructor.
	 * The initialization when the instance of ZZML0030_BSMsg is generated.
	 */
	public ZZML0030_BSMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0030_BSMsg is constructor.
	 * The initialization when the instance of ZZML0030_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0030_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_B = (EZDSBigDecimalItem)newItem("xxNum_B");
		mlAddrTpCd_B = (EZDSStringItem)newItem("mlAddrTpCd_B");
		mlUsrAddr_B = (EZDSStringItem)newItem("mlUsrAddr_B");
		mlUsrNm_B = (EZDSStringItem)newItem("mlUsrNm_B");
		mlUsrId_B = (EZDSStringItem)newItem("mlUsrId_B");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0030_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0030_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_B", "xxNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mlAddrTpCd_B", "mlAddrTpCd_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"mlUsrAddr_B", "mlUsrAddr_B", "B", null, TYPE_HANKAKUEISU, "240", null},
	{"mlUsrNm_B", "mlUsrNm_B", "B", null, TYPE_KONZAI, "100", null},
	{"mlUsrId_B", "mlUsrId_B", "B", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_B
        {"ML_ADDR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlAddrTpCd_B
        {"ML_USR_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrAddr_B
        {"ML_USR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrNm_B
        {"ML_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrId_B
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
