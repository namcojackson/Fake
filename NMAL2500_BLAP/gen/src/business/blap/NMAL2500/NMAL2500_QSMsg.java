//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160128152132000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2500_QSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2500_QSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PSN_CD_Q1*/
	public final EZDSStringItem              psnCd_Q1;

    /** XX_PSN_NM_Q1*/
	public final EZDSStringItem              xxPsnNm_Q1;


	/**
	 * NMAL2500_QSMsg is constructor.
	 * The initialization when the instance of NMAL2500_QSMsg is generated.
	 */
	public NMAL2500_QSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2500_QSMsg is constructor.
	 * The initialization when the instance of NMAL2500_QSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2500_QSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		psnCd_Q1 = (EZDSStringItem)newItem("psnCd_Q1");
		xxPsnNm_Q1 = (EZDSStringItem)newItem("xxPsnNm_Q1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2500_QSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2500_QSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"psnCd_Q1", "psnCd_Q1", "Q1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_Q1", "xxPsnNm_Q1", "Q1", null, TYPE_HANKAKUEISU, "62", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_Q1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_Q1
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

