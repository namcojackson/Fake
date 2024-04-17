//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160810104136000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0050_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0050_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_HDR_ID_B*/
	public final EZDBStringItem              ntfyHdrId_B;

    /** NTFY_HDR_NM_B*/
	public final EZDBStringItem              ntfyHdrNm_B;

    /** NTFY_HDR_DESC_TXT_B*/
	public final EZDBStringItem              ntfyHdrDescTxt_B;

    /** NTFY_BIZ_AREA_TP_DESC_TXT_B*/
	public final EZDBStringItem              ntfyBizAreaTpDescTxt_B;

    /** NTFY_SUB_AREA_TP_DESC_TXT_B*/
	public final EZDBStringItem              ntfySubAreaTpDescTxt_B;

    /** EFF_FROM_DT_B*/
	public final EZDBDateItem              effFromDt_B;

    /** EFF_THRU_DT_B*/
	public final EZDBDateItem              effThruDt_B;

    /** NTFY_HDR_ACTV_FLG_B*/
	public final EZDBStringItem              ntfyHdrActvFlg_B;


	/**
	 * NWWL0050_BBMsg is constructor.
	 * The initialization when the instance of NWWL0050_BBMsg is generated.
	 */
	public NWWL0050_BBMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0050_BBMsg is constructor.
	 * The initialization when the instance of NWWL0050_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0050_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyHdrId_B = (EZDBStringItem)newItem("ntfyHdrId_B");
		ntfyHdrNm_B = (EZDBStringItem)newItem("ntfyHdrNm_B");
		ntfyHdrDescTxt_B = (EZDBStringItem)newItem("ntfyHdrDescTxt_B");
		ntfyBizAreaTpDescTxt_B = (EZDBStringItem)newItem("ntfyBizAreaTpDescTxt_B");
		ntfySubAreaTpDescTxt_B = (EZDBStringItem)newItem("ntfySubAreaTpDescTxt_B");
		effFromDt_B = (EZDBDateItem)newItem("effFromDt_B");
		effThruDt_B = (EZDBDateItem)newItem("effThruDt_B");
		ntfyHdrActvFlg_B = (EZDBStringItem)newItem("ntfyHdrActvFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0050_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0050_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyHdrId_B", "ntfyHdrId_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyHdrNm_B", "ntfyHdrNm_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyHdrDescTxt_B", "ntfyHdrDescTxt_B", "B", null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpDescTxt_B", "ntfyBizAreaTpDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"ntfySubAreaTpDescTxt_B", "ntfySubAreaTpDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_B", "effFromDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_B", "effThruDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"ntfyHdrActvFlg_B", "ntfyHdrActvFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_HDR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrId_B
        {"NTFY_HDR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrNm_B
        {"NTFY_HDR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrDescTxt_B
        {"NTFY_BIZ_AREA_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpDescTxt_B
        {"NTFY_SUB_AREA_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpDescTxt_B
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_B
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_B
        {"NTFY_HDR_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrActvFlg_B
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
