//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160726190255000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0010F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0010F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0010F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NTFY_HDR_ID*/
	public final EZDFStringItem              ntfyHdrId;

    /** NTFY_HDR_NM*/
	public final EZDFStringItem              ntfyHdrNm;

    /** NTFY_HDR_DESC_TXT*/
	public final EZDFStringItem              ntfyHdrDescTxt;

    /** NTFY_BIZ_AREA_TP_NM*/
	public final EZDFStringItem              ntfyBizAreaTpNm;

    /** NTFY_SUB_AREA_TP_NM*/
	public final EZDFStringItem              ntfySubAreaTpNm;

    /** XX_DT_TXT_FR*/
	public final EZDFStringItem              xxDtTxt_FR;

    /** XX_DT_TXT_TH*/
	public final EZDFStringItem              xxDtTxt_TH;


	/**
	 * NWWL0010F00FMsg is constructor.
	 * The initialization when the instance of NWWL0010F00FMsg is generated.
	 */
	public NWWL0010F00FMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0010F00FMsg is constructor.
	 * The initialization when the instance of NWWL0010F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0010F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ntfyHdrId = (EZDFStringItem)newItem("ntfyHdrId");
		ntfyHdrNm = (EZDFStringItem)newItem("ntfyHdrNm");
		ntfyHdrDescTxt = (EZDFStringItem)newItem("ntfyHdrDescTxt");
		ntfyBizAreaTpNm = (EZDFStringItem)newItem("ntfyBizAreaTpNm");
		ntfySubAreaTpNm = (EZDFStringItem)newItem("ntfySubAreaTpNm");
		xxDtTxt_FR = (EZDFStringItem)newItem("xxDtTxt_FR");
		xxDtTxt_TH = (EZDFStringItem)newItem("xxDtTxt_TH");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0010F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0010F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ntfyHdrId", "ntfyHdrId", null, null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyHdrNm", "ntfyHdrNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyHdrDescTxt", "ntfyHdrDescTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpNm", "ntfyBizAreaTpNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"ntfySubAreaTpNm", "ntfySubAreaTpNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"xxDtTxt_FR", "xxDtTxt_FR", "FR", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_TH", "xxDtTxt_TH", "TH", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NTFY_HDR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrId
        {"NTFY_HDR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrNm
        {"NTFY_HDR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrDescTxt
        {"NTFY_BIZ_AREA_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpNm
        {"NTFY_SUB_AREA_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpNm
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_FR
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_TH
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

