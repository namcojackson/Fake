//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160218170222000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0770_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0770 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0770_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_STS_NM_A1*/
	public final EZDBStringItem              dsContrStsNm_A1;

    /** DS_CONTR_STS_NM_A2*/
	public final EZDBStringItem              dsContrStsNm_A2;

    /** DS_CONTR_TRK_RSN_DESC_TXT*/
	public final EZDBStringItem              dsContrTrkRsnDescTxt;

    /** STS_MEMO_TXT*/
	public final EZDBStringItem              stsMemoTxt;

    /** STS_MEMO_UPD_FULL_PSN_NM*/
	public final EZDBStringItem              stsMemoUpdFullPsnNm;

    /** XX_DT_TM*/
	public final EZDBStringItem              xxDtTm;


	/**
	 * NSAL0770_ABMsg is constructor.
	 * The initialization when the instance of NSAL0770_ABMsg is generated.
	 */
	public NSAL0770_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0770_ABMsg is constructor.
	 * The initialization when the instance of NSAL0770_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0770_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrStsNm_A1 = (EZDBStringItem)newItem("dsContrStsNm_A1");
		dsContrStsNm_A2 = (EZDBStringItem)newItem("dsContrStsNm_A2");
		dsContrTrkRsnDescTxt = (EZDBStringItem)newItem("dsContrTrkRsnDescTxt");
		stsMemoTxt = (EZDBStringItem)newItem("stsMemoTxt");
		stsMemoUpdFullPsnNm = (EZDBStringItem)newItem("stsMemoUpdFullPsnNm");
		xxDtTm = (EZDBStringItem)newItem("xxDtTm");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0770_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0770_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrStsNm_A1", "dsContrStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrStsNm_A2", "dsContrStsNm_A2", "A2", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrTrkRsnDescTxt", "dsContrTrkRsnDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"stsMemoTxt", "stsMemoTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"stsMemoUpdFullPsnNm", "stsMemoUpdFullPsnNm", null, null, TYPE_HANKAKUEISU, "62", null},
	{"xxDtTm", "xxDtTm", null, null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsNm_A1
        {"DS_CONTR_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsNm_A2
        {"DS_CONTR_TRK_RSN_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrTrkRsnDescTxt
        {"STS_MEMO_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stsMemoTxt
        {"STS_MEMO_UPD_FULL_PSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stsMemoUpdFullPsnNm
        {"XX_DT_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm
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
