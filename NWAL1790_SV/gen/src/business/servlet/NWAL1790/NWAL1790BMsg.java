//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160105113417000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1790BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1790;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDMsgArray;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1790 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1790BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_PROC_MD*/
	public final EZDBStringItem              xxProcMd;

    /** XX_RQST_FLG_ML*/
	public final EZDBStringItem              xxRqstFlg_ML;

    /** XX_RQST_FLG_PR*/
	public final EZDBStringItem              xxRqstFlg_PR;

    /** A*/
	public final business.servlet.NWAL1790.NWAL1790_ABMsgArray              A;


	/**
	 * NWAL1790BMsg is constructor.
	 * The initialization when the instance of NWAL1790BMsg is generated.
	 */
	public NWAL1790BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1790BMsg is constructor.
	 * The initialization when the instance of NWAL1790BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1790BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxProcMd = (EZDBStringItem)newItem("xxProcMd");
		xxRqstFlg_ML = (EZDBStringItem)newItem("xxRqstFlg_ML");
		xxRqstFlg_PR = (EZDBStringItem)newItem("xxRqstFlg_PR");
		A = (business.servlet.NWAL1790.NWAL1790_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1790BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1790BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxProcMd", "xxProcMd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxRqstFlg_ML", "xxRqstFlg_ML", "ML", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRqstFlg_PR", "xxRqstFlg_PR", "PR", null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "20", "business.servlet.NWAL1790.NWAL1790_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PROC_MD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcMd
        {"XX_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg_ML
        {"XX_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg_PR
		null,	//A
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
