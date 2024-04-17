//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160923132347000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2360_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2360 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2360_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_IMPT_ORD_SOM_APVL_PK*/
	public final EZDBBigDecimalItem              dsImptOrdSomApvlPk;

    /** SOM_STS_TXT*/
	public final EZDBStringItem              somStsTxt;

    /** SOM_APVL_TP_TXT*/
	public final EZDBStringItem              somApvlTpTxt;

    /** SOM_APVL_DESC_TXT*/
	public final EZDBStringItem              somApvlDescTxt;

    /** SOM_VRSN_ID*/
	public final EZDBBigDecimalItem              somVrsnId;

    /** SOM_APVR_NM*/
	public final EZDBStringItem              somApvrNm;

    /** WF_START_TS*/
	public final EZDBStringItem              wfStartTs;

    /** XX_DT_TM_FR*/
	public final EZDBStringItem              xxDtTm_FR;

    /** WF_END_TS*/
	public final EZDBStringItem              wfEndTs;

    /** XX_DT_TM_TO*/
	public final EZDBStringItem              xxDtTm_TO;

    /** APVL_NOTE_TXT*/
	public final EZDBStringItem              apvlNoteTxt;


	/**
	 * NWAL2360_ABMsg is constructor.
	 * The initialization when the instance of NWAL2360_ABMsg is generated.
	 */
	public NWAL2360_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2360_ABMsg is constructor.
	 * The initialization when the instance of NWAL2360_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2360_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsImptOrdSomApvlPk = (EZDBBigDecimalItem)newItem("dsImptOrdSomApvlPk");
		somStsTxt = (EZDBStringItem)newItem("somStsTxt");
		somApvlTpTxt = (EZDBStringItem)newItem("somApvlTpTxt");
		somApvlDescTxt = (EZDBStringItem)newItem("somApvlDescTxt");
		somVrsnId = (EZDBBigDecimalItem)newItem("somVrsnId");
		somApvrNm = (EZDBStringItem)newItem("somApvrNm");
		wfStartTs = (EZDBStringItem)newItem("wfStartTs");
		xxDtTm_FR = (EZDBStringItem)newItem("xxDtTm_FR");
		wfEndTs = (EZDBStringItem)newItem("wfEndTs");
		xxDtTm_TO = (EZDBStringItem)newItem("xxDtTm_TO");
		apvlNoteTxt = (EZDBStringItem)newItem("apvlNoteTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2360_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2360_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsImptOrdSomApvlPk", "dsImptOrdSomApvlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"somStsTxt", "somStsTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"somApvlTpTxt", "somApvlTpTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"somApvlDescTxt", "somApvlDescTxt", null, null, TYPE_HANKAKUEISU, "500", null},
	{"somVrsnId", "somVrsnId", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"somApvrNm", "somApvrNm", null, null, TYPE_HANKAKUEISU, "52", null},
	{"wfStartTs", "wfStartTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxDtTm_FR", "xxDtTm_FR", "FR", null, TYPE_HANKAKUEISU, "23", null},
	{"wfEndTs", "wfEndTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxDtTm_TO", "xxDtTm_TO", "TO", null, TYPE_HANKAKUEISU, "23", null},
	{"apvlNoteTxt", "apvlNoteTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_IMPT_ORD_SOM_APVL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdSomApvlPk
        {"SOM_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//somStsTxt
        {"SOM_APVL_TP_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//somApvlTpTxt
        {"SOM_APVL_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//somApvlDescTxt
        {"SOM_VRSN_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//somVrsnId
        {"SOM_APVR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//somApvrNm
        {"WF_START_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfStartTs
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_FR
        {"WF_END_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wfEndTs
        {"XX_DT_TM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_TO
        {"APVL_NOTE_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlNoteTxt
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
