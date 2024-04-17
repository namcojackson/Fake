//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160701181241000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7130_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7130 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7130_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CATG_NM_B1*/
	public final EZDBStringItem              prcCatgNm_B1;

    /** PRC_LIST_TP_DESC_TXT_B1*/
	public final EZDBStringItem              prcListTpDescTxt_B1;

    /** ACTV_FLG_B1*/
	public final EZDBStringItem              actvFlg_B1;

    /** XX_SCR_ITEM_20_TXT_B1*/
	public final EZDBStringItem              xxScrItem20Txt_B1;

    /** EFF_FROM_DT_B1*/
	public final EZDBDateItem              effFromDt_B1;

    /** EFF_THRU_DT_B1*/
	public final EZDBDateItem              effThruDt_B1;

    /** PRC_CATG_CD_B1*/
	public final EZDBStringItem              prcCatgCd_B1;


	/**
	 * NMAL7130_BBMsg is constructor.
	 * The initialization when the instance of NMAL7130_BBMsg is generated.
	 */
	public NMAL7130_BBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7130_BBMsg is constructor.
	 * The initialization when the instance of NMAL7130_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7130_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCatgNm_B1 = (EZDBStringItem)newItem("prcCatgNm_B1");
		prcListTpDescTxt_B1 = (EZDBStringItem)newItem("prcListTpDescTxt_B1");
		actvFlg_B1 = (EZDBStringItem)newItem("actvFlg_B1");
		xxScrItem20Txt_B1 = (EZDBStringItem)newItem("xxScrItem20Txt_B1");
		effFromDt_B1 = (EZDBDateItem)newItem("effFromDt_B1");
		effThruDt_B1 = (EZDBDateItem)newItem("effThruDt_B1");
		prcCatgCd_B1 = (EZDBStringItem)newItem("prcCatgCd_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7130_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7130_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCatgNm_B1", "prcCatgNm_B1", "B1", null, TYPE_HANKAKUEISU, "75", null},
	{"prcListTpDescTxt_B1", "prcListTpDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"actvFlg_B1", "actvFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrItem20Txt_B1", "xxScrItem20Txt_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_B1", "effFromDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_B1", "effThruDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"prcCatgCd_B1", "prcCatgCd_B1", "B1", null, TYPE_HANKAKUEISU, "10", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CATG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_B1
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_B1
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_B1
        {"XX_SCR_ITEM_20_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem20Txt_B1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_B1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_B1
        {"PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_B1
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
