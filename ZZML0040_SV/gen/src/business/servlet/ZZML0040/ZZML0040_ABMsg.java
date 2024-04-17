//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20130815171641000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0040_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZML0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0040 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0040_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** XX_NUM_A*/
	public final EZDBBigDecimalItem              xxNum_A;

    /** ML_TMPL_ID_A*/
	public final EZDBStringItem              mlTmplId_A;

    /** LANG_NM_A*/
	public final EZDBStringItem              langNm_A;

    /** ML_SUBJ_TMPL_TXT_A*/
	public final EZDBStringItem              mlSubjTmplTxt_A;


	/**
	 * ZZML0040_ABMsg is constructor.
	 * The initialization when the instance of ZZML0040_ABMsg is generated.
	 */
	public ZZML0040_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0040_ABMsg is constructor.
	 * The initialization when the instance of ZZML0040_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0040_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		xxNum_A = (EZDBBigDecimalItem)newItem("xxNum_A");
		mlTmplId_A = (EZDBStringItem)newItem("mlTmplId_A");
		langNm_A = (EZDBStringItem)newItem("langNm_A");
		mlSubjTmplTxt_A = (EZDBStringItem)newItem("mlSubjTmplTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0040_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0040_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxNum_A", "xxNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mlTmplId_A", "mlTmplId_A", "A", null, TYPE_HANKAKUEISU, "12", null},
	{"langNm_A", "langNm_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"mlSubjTmplTxt_A", "mlSubjTmplTxt_A", "A", null, TYPE_KONZAI, "200", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"XX_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A
        {"ML_TMPL_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlTmplId_A
        {"LANG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//langNm_A
        {"ML_SUBJ_TMPL_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlSubjTmplTxt_A
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

