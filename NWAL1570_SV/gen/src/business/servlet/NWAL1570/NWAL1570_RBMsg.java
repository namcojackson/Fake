//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240229113847000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1570_RBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1570;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1570 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1570_RBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RTRN_LINE_STS_SEL_FLG_RS*/
	public final EZDBStringItem              xxRtrnLineStsSelFlg_RS;

    /** RTRN_LINE_STS_CD_RS*/
	public final EZDBStringItem              rtrnLineStsCd_RS;

    /** RTRN_LINE_STS_NM_RS*/
	public final EZDBStringItem              rtrnLineStsNm_RS;


	/**
	 * NWAL1570_RBMsg is constructor.
	 * The initialization when the instance of NWAL1570_RBMsg is generated.
	 */
	public NWAL1570_RBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1570_RBMsg is constructor.
	 * The initialization when the instance of NWAL1570_RBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1570_RBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRtrnLineStsSelFlg_RS = (EZDBStringItem)newItem("xxRtrnLineStsSelFlg_RS");
		rtrnLineStsCd_RS = (EZDBStringItem)newItem("rtrnLineStsCd_RS");
		rtrnLineStsNm_RS = (EZDBStringItem)newItem("rtrnLineStsNm_RS");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1570_RBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1570_RBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRtrnLineStsSelFlg_RS", "xxRtrnLineStsSelFlg_RS", "RS", null, TYPE_HANKAKUEISU, "1", null},
	{"rtrnLineStsCd_RS", "rtrnLineStsCd_RS", "RS", null, TYPE_HANKAKUEISU, "2", null},
	{"rtrnLineStsNm_RS", "rtrnLineStsNm_RS", "RS", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RTRN_LINE_STS_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRtrnLineStsSelFlg_RS
        {"RTRN_LINE_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnLineStsCd_RS
        {"RTRN_LINE_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnLineStsNm_RS
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

