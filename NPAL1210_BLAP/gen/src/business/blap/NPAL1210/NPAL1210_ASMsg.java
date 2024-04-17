//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190904155438000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1210_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1210;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1210 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1210_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SEQ_NUMBER_A1*/
	public final EZDSBigDecimalItem              seqNumber_A1;

    /** APVL_BY_PSN_CD_A1*/
	public final EZDSStringItem              apvlByPsnCd_A1;

    /** APVL_FULL_PSN_NM_A1*/
	public final EZDSStringItem              apvlFullPsnNm_A1;

    /** APVL_HIST_ACT_TP_DESC_TXT_A1*/
	public final EZDSStringItem              apvlHistActTpDescTxt_A1;

    /** XX_DT_TM_A1*/
	public final EZDSStringItem              xxDtTm_A1;

    /** APVL_HIST_TXT_A1*/
	public final EZDSStringItem              apvlHistTxt_A1;


	/**
	 * NPAL1210_ASMsg is constructor.
	 * The initialization when the instance of NPAL1210_ASMsg is generated.
	 */
	public NPAL1210_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1210_ASMsg is constructor.
	 * The initialization when the instance of NPAL1210_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1210_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		seqNumber_A1 = (EZDSBigDecimalItem)newItem("seqNumber_A1");
		apvlByPsnCd_A1 = (EZDSStringItem)newItem("apvlByPsnCd_A1");
		apvlFullPsnNm_A1 = (EZDSStringItem)newItem("apvlFullPsnNm_A1");
		apvlHistActTpDescTxt_A1 = (EZDSStringItem)newItem("apvlHistActTpDescTxt_A1");
		xxDtTm_A1 = (EZDSStringItem)newItem("xxDtTm_A1");
		apvlHistTxt_A1 = (EZDSStringItem)newItem("apvlHistTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1210_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1210_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"seqNumber_A1", "seqNumber_A1", "A1", null, TYPE_SEISU_SYOSU, "30", "0"},
	{"apvlByPsnCd_A1", "apvlByPsnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"apvlFullPsnNm_A1", "apvlFullPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "62", null},
	{"apvlHistActTpDescTxt_A1", "apvlHistActTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtTm_A1", "xxDtTm_A1", "A1", null, TYPE_HANKAKUEISU, "23", null},
	{"apvlHistTxt_A1", "apvlHistTxt_A1", "A1", null, TYPE_HANKAKUEISU, "2000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SEQ_NUMBER",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//seqNumber_A1
        {"APVL_BY_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlByPsnCd_A1
        {"APVL_FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlFullPsnNm_A1
        {"APVL_HIST_ACT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistActTpDescTxt_A1
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A1
        {"APVL_HIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlHistTxt_A1
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

