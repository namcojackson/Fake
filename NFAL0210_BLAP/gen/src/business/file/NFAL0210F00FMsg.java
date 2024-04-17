//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160614164722000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL0210F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFAL0210F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL0210F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RADIO_BTN_A*/
	public final EZDFBigDecimalItem              xxRadioBtn_A;

    /** MAN_JRNL_NM_A*/
	public final EZDFStringItem              manJrnlNm_A;

    /** JRNL_CATG_DESC_TXT_A*/
	public final EZDFStringItem              jrnlCatgDescTxt_A;

    /** GL_PER_NM_A*/
	public final EZDFStringItem              glPerNm_A;

    /** XX_DT_TXT_A1*/
	public final EZDFStringItem              xxDtTxt_A1;

    /** GL_SEND_CPLT_FLG_A*/
	public final EZDFStringItem              glSendCpltFlg_A;

    /** RVSL_CPLT_FLG_A*/
	public final EZDFStringItem              rvslCpltFlg_A;

    /** XX_DT_TXT_A2*/
	public final EZDFStringItem              xxDtTxt_A2;

    /** MAN_JRNL_CPLT_FLG_A*/
	public final EZDFStringItem              manJrnlCpltFlg_A;

    /** AUTO_RVSL_FLG_A*/
	public final EZDFStringItem              autoRvslFlg_A;


	/**
	 * NFAL0210F00FMsg is constructor.
	 * The initialization when the instance of NFAL0210F00FMsg is generated.
	 */
	public NFAL0210F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFAL0210F00FMsg is constructor.
	 * The initialization when the instance of NFAL0210F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL0210F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRadioBtn_A = (EZDFBigDecimalItem)newItem("xxRadioBtn_A");
		manJrnlNm_A = (EZDFStringItem)newItem("manJrnlNm_A");
		jrnlCatgDescTxt_A = (EZDFStringItem)newItem("jrnlCatgDescTxt_A");
		glPerNm_A = (EZDFStringItem)newItem("glPerNm_A");
		xxDtTxt_A1 = (EZDFStringItem)newItem("xxDtTxt_A1");
		glSendCpltFlg_A = (EZDFStringItem)newItem("glSendCpltFlg_A");
		rvslCpltFlg_A = (EZDFStringItem)newItem("rvslCpltFlg_A");
		xxDtTxt_A2 = (EZDFStringItem)newItem("xxDtTxt_A2");
		manJrnlCpltFlg_A = (EZDFStringItem)newItem("manJrnlCpltFlg_A");
		autoRvslFlg_A = (EZDFStringItem)newItem("autoRvslFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL0210F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL0210F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRadioBtn_A", "xxRadioBtn_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"manJrnlNm_A", "manJrnlNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"jrnlCatgDescTxt_A", "jrnlCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"glPerNm_A", "glPerNm_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_A1", "xxDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"glSendCpltFlg_A", "glSendCpltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"rvslCpltFlg_A", "rvslCpltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTxt_A2", "xxDtTxt_A2", "A2", null, TYPE_HANKAKUEISU, "10", null},
	{"manJrnlCpltFlg_A", "manJrnlCpltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"autoRvslFlg_A", "autoRvslFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A
        {"MAN_JRNL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manJrnlNm_A
        {"JRNL_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlCatgDescTxt_A
        {"GL_PER_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glPerNm_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A1
        {"GL_SEND_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glSendCpltFlg_A
        {"RVSL_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rvslCpltFlg_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A2
        {"MAN_JRNL_CPLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manJrnlCpltFlg_A
        {"AUTO_RVSL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoRvslFlg_A
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
