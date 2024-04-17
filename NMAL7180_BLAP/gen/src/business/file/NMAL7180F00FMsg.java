//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20200218145207000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7180F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL7180F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7180F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RADIO_BTN*/
	public final EZDFBigDecimalItem              xxRadioBtn;

    /** PRC_GRP_NM_A1*/
	public final EZDFStringItem              prcGrpNm_A1;

    /** PRC_GRP_DESC_TXT_A1*/
	public final EZDFStringItem              prcGrpDescTxt_A1;

    /** PRC_GRP_TP_DESC_TXT_A1*/
	public final EZDFStringItem              prcGrpTpDescTxt_A1;

    /** ACTV_FLG_A1*/
	public final EZDFStringItem              actvFlg_A1;

    /** XX_DT_TXT_FR*/
	public final EZDFStringItem              xxDtTxt_FR;

    /** XX_DT_TXT_TH*/
	public final EZDFStringItem              xxDtTxt_TH;

    /** PRC_GRP_TRX_TP_DESC_TXT_A1*/
	public final EZDFStringItem              prcGrpTrxTpDescTxt_A1;


	/**
	 * NMAL7180F00FMsg is constructor.
	 * The initialization when the instance of NMAL7180F00FMsg is generated.
	 */
	public NMAL7180F00FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7180F00FMsg is constructor.
	 * The initialization when the instance of NMAL7180F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7180F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRadioBtn = (EZDFBigDecimalItem)newItem("xxRadioBtn");
		prcGrpNm_A1 = (EZDFStringItem)newItem("prcGrpNm_A1");
		prcGrpDescTxt_A1 = (EZDFStringItem)newItem("prcGrpDescTxt_A1");
		prcGrpTpDescTxt_A1 = (EZDFStringItem)newItem("prcGrpTpDescTxt_A1");
		actvFlg_A1 = (EZDFStringItem)newItem("actvFlg_A1");
		xxDtTxt_FR = (EZDFStringItem)newItem("xxDtTxt_FR");
		xxDtTxt_TH = (EZDFStringItem)newItem("xxDtTxt_TH");
		prcGrpTrxTpDescTxt_A1 = (EZDFStringItem)newItem("prcGrpTrxTpDescTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7180F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7180F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcGrpNm_A1", "prcGrpNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpDescTxt_A1", "prcGrpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpTpDescTxt_A1", "prcGrpTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"actvFlg_A1", "actvFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTxt_FR", "xxDtTxt_FR", "FR", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_TH", "xxDtTxt_TH", "TH", null, TYPE_HANKAKUEISU, "10", null},
	{"prcGrpTrxTpDescTxt_A1", "prcGrpTrxTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
        {"PRC_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpNm_A1
        {"PRC_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpDescTxt_A1
        {"PRC_GRP_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTpDescTxt_A1
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_FR
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_TH
        {"PRC_GRP_TRX_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrxTpDescTxt_A1
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

