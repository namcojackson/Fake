//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200218145637000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7180_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7180;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7180 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7180_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_GRP_PK_A1*/
	public final EZDBBigDecimalItem              prcGrpPk_A1;

    /** PRC_GRP_NM_A1*/
	public final EZDBStringItem              prcGrpNm_A1;

    /** PRC_GRP_DESC_TXT_A1*/
	public final EZDBStringItem              prcGrpDescTxt_A1;

    /** PRC_GRP_TP_CD_A1*/
	public final EZDBStringItem              prcGrpTpCd_A1;

    /** PRC_GRP_TP_DESC_TXT_A1*/
	public final EZDBStringItem              prcGrpTpDescTxt_A1;

    /** ACTV_FLG_A1*/
	public final EZDBStringItem              actvFlg_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** PRC_GRP_TRX_TP_DESC_TXT_A1*/
	public final EZDBStringItem              prcGrpTrxTpDescTxt_A1;


	/**
	 * NMAL7180_ABMsg is constructor.
	 * The initialization when the instance of NMAL7180_ABMsg is generated.
	 */
	public NMAL7180_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7180_ABMsg is constructor.
	 * The initialization when the instance of NMAL7180_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7180_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcGrpPk_A1 = (EZDBBigDecimalItem)newItem("prcGrpPk_A1");
		prcGrpNm_A1 = (EZDBStringItem)newItem("prcGrpNm_A1");
		prcGrpDescTxt_A1 = (EZDBStringItem)newItem("prcGrpDescTxt_A1");
		prcGrpTpCd_A1 = (EZDBStringItem)newItem("prcGrpTpCd_A1");
		prcGrpTpDescTxt_A1 = (EZDBStringItem)newItem("prcGrpTpDescTxt_A1");
		actvFlg_A1 = (EZDBStringItem)newItem("actvFlg_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		prcGrpTrxTpDescTxt_A1 = (EZDBStringItem)newItem("prcGrpTrxTpDescTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7180_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7180_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcGrpPk_A1", "prcGrpPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcGrpNm_A1", "prcGrpNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpDescTxt_A1", "prcGrpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpTpCd_A1", "prcGrpTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcGrpTpDescTxt_A1", "prcGrpTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"actvFlg_A1", "actvFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"prcGrpTrxTpDescTxt_A1", "prcGrpTrxTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_GRP_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpPk_A1
        {"PRC_GRP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpNm_A1
        {"PRC_GRP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpDescTxt_A1
        {"PRC_GRP_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTpCd_A1
        {"PRC_GRP_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTpDescTxt_A1
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"PRC_GRP_TRX_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrxTpDescTxt_A1
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
