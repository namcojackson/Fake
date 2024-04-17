//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200226134941000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0030_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZML0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0030_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_NUM_A*/
	public final EZDBBigDecimalItem              xxNum_A;

    /** ML_SUBJ_TXT_A*/
	public final EZDBStringItem              mlSubjTxt_A;

    /** XX_SCR_ITEM_7_TXT_A*/
	public final EZDBStringItem              xxScrItem7Txt_A;

    /** XX_DT_TM_A*/
	public final EZDBStringItem              xxDtTm_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

    /** ML_USR_ADDR_A*/
	public final EZDBStringItem              mlUsrAddr_A;

    /** XX_RSLT_FLG_A*/
	public final EZDBStringItem              xxRsltFlg_A;


	/**
	 * ZZML0030_ABMsg is constructor.
	 * The initialization when the instance of ZZML0030_ABMsg is generated.
	 */
	public ZZML0030_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0030_ABMsg is constructor.
	 * The initialization when the instance of ZZML0030_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0030_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxNum_A = (EZDBBigDecimalItem)newItem("xxNum_A");
		mlSubjTxt_A = (EZDBStringItem)newItem("mlSubjTxt_A");
		xxScrItem7Txt_A = (EZDBStringItem)newItem("xxScrItem7Txt_A");
		xxDtTm_A = (EZDBStringItem)newItem("xxDtTm_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		mlUsrAddr_A = (EZDBStringItem)newItem("mlUsrAddr_A");
		xxRsltFlg_A = (EZDBStringItem)newItem("xxRsltFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0030_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0030_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxNum_A", "xxNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mlSubjTxt_A", "mlSubjTxt_A", "A", null, TYPE_KONZAI, "200", null},
	{"xxScrItem7Txt_A", "xxScrItem7Txt_A", "A", null, TYPE_HANKAKUEISU, "7", null},
	{"xxDtTm_A", "xxDtTm_A", "A", null, TYPE_HANKAKUEISU, "23", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"mlUsrAddr_A", "mlUsrAddr_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"xxRsltFlg_A", "xxRsltFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A
        {"ML_SUBJ_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlSubjTxt_A
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_A
        {"XX_DT_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_A
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"ML_USR_ADDR",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrAddr_A
        {"XX_RSLT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg_A
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
