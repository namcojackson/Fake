//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230831165134000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2020_SCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2020_SCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_HDR_NUM_SN*/
	public final EZDCStringItem              xxHdrNum_SN;

    /** XX_DPLY_TRX_NUM_TXT_SN*/
	public final EZDCStringItem              xxDplyTrxNumTxt_SN;

    /** MDSE_CD_SN*/
	public final EZDCStringItem              mdseCd_SN;

    /** SER_NUM_SN*/
	public final EZDCStringItem              serNum_SN;

    /** XX_EDT_MODE_FLG_SN*/
	public final EZDCStringItem              xxEdtModeFlg_SN;

    /** XX_TRX_REF_PK_SN*/
	public final EZDCBigDecimalItem              xxTrxRefPk_SN;

    /** XX_RQST_TP_CD_SN*/
	public final EZDCStringItem              xxRqstTpCd_SN;

    /** RTL_SWH_CD_SN*/
	public final EZDCStringItem              rtlSwhCd_SN;

    /** INVTY_LOC_CD_SN*/
	public final EZDCStringItem              invtyLocCd_SN;

    /** SVC_CONFIG_MSTR_PK_SN*/
	public final EZDCBigDecimalItem              svcConfigMstrPk_SN;

    /** SER_NUM_TAKE_FLG_SN*/
	public final EZDCStringItem              serNumTakeFlg_SN;


	/**
	 * NLAL2020_SCMsg is constructor.
	 * The initialization when the instance of NLAL2020_SCMsg is generated.
	 */
	public NLAL2020_SCMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2020_SCMsg is constructor.
	 * The initialization when the instance of NLAL2020_SCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2020_SCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxHdrNum_SN = (EZDCStringItem)newItem("xxHdrNum_SN");
		xxDplyTrxNumTxt_SN = (EZDCStringItem)newItem("xxDplyTrxNumTxt_SN");
		mdseCd_SN = (EZDCStringItem)newItem("mdseCd_SN");
		serNum_SN = (EZDCStringItem)newItem("serNum_SN");
		xxEdtModeFlg_SN = (EZDCStringItem)newItem("xxEdtModeFlg_SN");
		xxTrxRefPk_SN = (EZDCBigDecimalItem)newItem("xxTrxRefPk_SN");
		xxRqstTpCd_SN = (EZDCStringItem)newItem("xxRqstTpCd_SN");
		rtlSwhCd_SN = (EZDCStringItem)newItem("rtlSwhCd_SN");
		invtyLocCd_SN = (EZDCStringItem)newItem("invtyLocCd_SN");
		svcConfigMstrPk_SN = (EZDCBigDecimalItem)newItem("svcConfigMstrPk_SN");
		serNumTakeFlg_SN = (EZDCStringItem)newItem("serNumTakeFlg_SN");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2020_SCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2020_SCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxHdrNum_SN", "xxHdrNum_SN", "SN", null, TYPE_HANKAKUEISU, "8", null},
	{"xxDplyTrxNumTxt_SN", "xxDplyTrxNumTxt_SN", "SN", null, TYPE_HANKAKUEISU, "15", null},
	{"mdseCd_SN", "mdseCd_SN", "SN", null, TYPE_HANKAKUEISU, "16", null},
	{"serNum_SN", "serNum_SN", "SN", null, TYPE_HANKAKUEISU, "30", null},
	{"xxEdtModeFlg_SN", "xxEdtModeFlg_SN", "SN", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTrxRefPk_SN", "xxTrxRefPk_SN", "SN", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRqstTpCd_SN", "xxRqstTpCd_SN", "SN", null, TYPE_HANKAKUEISU, "1", null},
	{"rtlSwhCd_SN", "rtlSwhCd_SN", "SN", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocCd_SN", "invtyLocCd_SN", "SN", null, TYPE_HANKAKUEISU, "20", null},
	{"svcConfigMstrPk_SN", "svcConfigMstrPk_SN", "SN", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNumTakeFlg_SN", "serNumTakeFlg_SN", "SN", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNum_SN
        {"XX_DPLY_TRX_NUM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTrxNumTxt_SN
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_SN
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_SN
        {"XX_EDT_MODE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtModeFlg_SN
        {"XX_TRX_REF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxRefPk_SN
        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd_SN
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_SN
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_SN
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_SN
        {"SER_NUM_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_SN
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

