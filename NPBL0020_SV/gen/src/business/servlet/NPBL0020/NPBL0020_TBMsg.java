//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230707082300000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPBL0020_TBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPBL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPBL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPBL0020_TBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_HDR_NUM_T1*/
	public final EZDBStringItem              xxHdrNum_T1;

    /** XX_DPLY_TRX_NUM_TXT_T1*/
	public final EZDBStringItem              xxDplyTrxNumTxt_T1;

    /** MDSE_CD_T1*/
	public final EZDBStringItem              mdseCd_T1;

    /** SER_NUM_T1*/
	public final EZDBStringItem              serNum_T1;

    /** XX_EDT_MODE_FLG_T1*/
	public final EZDBStringItem              xxEdtModeFlg_T1;

    /** XX_TRX_REF_PK_T1*/
	public final EZDBBigDecimalItem              xxTrxRefPk_T1;

    /** XX_RQST_TP_CD_T1*/
	public final EZDBStringItem              xxRqstTpCd_T1;

    /** RTL_SWH_CD_T1*/
	public final EZDBStringItem              rtlSwhCd_T1;

    /** INVTY_LOC_CD_T1*/
	public final EZDBStringItem              invtyLocCd_T1;

    /** SVC_CONFIG_MSTR_PK_T1*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_T1;

    /** SER_NUM_TAKE_FLG_T1*/
	public final EZDBStringItem              serNumTakeFlg_T1;


	/**
	 * NPBL0020_TBMsg is constructor.
	 * The initialization when the instance of NPBL0020_TBMsg is generated.
	 */
	public NPBL0020_TBMsg() {
		this(false, -1);
	}

	/**
	 * NPBL0020_TBMsg is constructor.
	 * The initialization when the instance of NPBL0020_TBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPBL0020_TBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxHdrNum_T1 = (EZDBStringItem)newItem("xxHdrNum_T1");
		xxDplyTrxNumTxt_T1 = (EZDBStringItem)newItem("xxDplyTrxNumTxt_T1");
		mdseCd_T1 = (EZDBStringItem)newItem("mdseCd_T1");
		serNum_T1 = (EZDBStringItem)newItem("serNum_T1");
		xxEdtModeFlg_T1 = (EZDBStringItem)newItem("xxEdtModeFlg_T1");
		xxTrxRefPk_T1 = (EZDBBigDecimalItem)newItem("xxTrxRefPk_T1");
		xxRqstTpCd_T1 = (EZDBStringItem)newItem("xxRqstTpCd_T1");
		rtlSwhCd_T1 = (EZDBStringItem)newItem("rtlSwhCd_T1");
		invtyLocCd_T1 = (EZDBStringItem)newItem("invtyLocCd_T1");
		svcConfigMstrPk_T1 = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_T1");
		serNumTakeFlg_T1 = (EZDBStringItem)newItem("serNumTakeFlg_T1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPBL0020_TBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPBL0020_TBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxHdrNum_T1", "xxHdrNum_T1", "T1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxDplyTrxNumTxt_T1", "xxDplyTrxNumTxt_T1", "T1", null, TYPE_HANKAKUEISU, "15", null},
	{"mdseCd_T1", "mdseCd_T1", "T1", null, TYPE_HANKAKUEISU, "16", null},
	{"serNum_T1", "serNum_T1", "T1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxEdtModeFlg_T1", "xxEdtModeFlg_T1", "T1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTrxRefPk_T1", "xxTrxRefPk_T1", "T1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRqstTpCd_T1", "xxRqstTpCd_T1", "T1", null, TYPE_HANKAKUEISU, "1", null},
	{"rtlSwhCd_T1", "rtlSwhCd_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocCd_T1", "invtyLocCd_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"svcConfigMstrPk_T1", "svcConfigMstrPk_T1", "T1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNumTakeFlg_T1", "serNumTakeFlg_T1", "T1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_HDR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNum_T1
        {"XX_DPLY_TRX_NUM_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTrxNumTxt_T1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_T1
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_T1
        {"XX_EDT_MODE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtModeFlg_T1
        {"XX_TRX_REF_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxRefPk_T1
        {"XX_RQST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd_T1
        {"RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_T1
        {"INVTY_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_T1
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_T1
        {"SER_NUM_TAKE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumTakeFlg_T1
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
