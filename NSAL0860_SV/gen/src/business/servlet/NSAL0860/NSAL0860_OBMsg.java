//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160614081412000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0860_OBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0860 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0860_OBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_O*/
	public final EZDBStringItem              serNum_O;

    /** MDSE_CD_O*/
	public final EZDBStringItem              mdseCd_O;

    /** CUR_LOC_NUM_O*/
	public final EZDBStringItem              curLocNum_O;

    /** LOC_NM_O*/
	public final EZDBStringItem              locNm_O;

    /** SVC_MACH_USG_STS_CD_O*/
	public final EZDBStringItem              svcMachUsgStsCd_O;

    /** SVC_MACH_MSTR_STS_CD_O*/
	public final EZDBStringItem              svcMachMstrStsCd_O;

    /** SVC_MACH_MSTR_PK_O*/
	public final EZDBBigDecimalItem              svcMachMstrPk_O;

    /** SVC_MACH_TP_CD_O*/
	public final EZDBStringItem              svcMachTpCd_O;


	/**
	 * NSAL0860_OBMsg is constructor.
	 * The initialization when the instance of NSAL0860_OBMsg is generated.
	 */
	public NSAL0860_OBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0860_OBMsg is constructor.
	 * The initialization when the instance of NSAL0860_OBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0860_OBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_O = (EZDBStringItem)newItem("serNum_O");
		mdseCd_O = (EZDBStringItem)newItem("mdseCd_O");
		curLocNum_O = (EZDBStringItem)newItem("curLocNum_O");
		locNm_O = (EZDBStringItem)newItem("locNm_O");
		svcMachUsgStsCd_O = (EZDBStringItem)newItem("svcMachUsgStsCd_O");
		svcMachMstrStsCd_O = (EZDBStringItem)newItem("svcMachMstrStsCd_O");
		svcMachMstrPk_O = (EZDBBigDecimalItem)newItem("svcMachMstrPk_O");
		svcMachTpCd_O = (EZDBStringItem)newItem("svcMachTpCd_O");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0860_OBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0860_OBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_O", "serNum_O", "O", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_O", "mdseCd_O", "O", null, TYPE_HANKAKUEISU, "16", null},
	{"curLocNum_O", "curLocNum_O", "O", null, TYPE_HANKAKUEISU, "30", null},
	{"locNm_O", "locNm_O", "O", null, TYPE_HANKAKUEISU, "60", null},
	{"svcMachUsgStsCd_O", "svcMachUsgStsCd_O", "O", null, TYPE_HANKAKUEISU, "3", null},
	{"svcMachMstrStsCd_O", "svcMachMstrStsCd_O", "O", null, TYPE_HANKAKUEISU, "5", null},
	{"svcMachMstrPk_O", "svcMachMstrPk_O", "O", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachTpCd_O", "svcMachTpCd_O", "O", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_O
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_O
        {"CUR_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curLocNum_O
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_O
        {"SVC_MACH_USG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachUsgStsCd_O
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_O
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_O
        {"SVC_MACH_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachTpCd_O
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

