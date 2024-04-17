//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151216110344000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030_RBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLAL2030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030_RBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_RO*/
	public final EZDBStringItem              serNum_RO;

    /** MDSE_CD_RO*/
	public final EZDBStringItem              mdseCd_RO;

    /** CUR_LOC_NUM_RO*/
	public final EZDBStringItem              curLocNum_RO;

    /** LOC_NM_RO*/
	public final EZDBStringItem              locNm_RO;

    /** SVC_MACH_USG_STS_CD_RO*/
	public final EZDBStringItem              svcMachUsgStsCd_RO;

    /** SVC_MACH_MSTR_STS_CD_RO*/
	public final EZDBStringItem              svcMachMstrStsCd_RO;

    /** SVC_MACH_MSTR_PK_RO*/
	public final EZDBBigDecimalItem              svcMachMstrPk_RO;


	/**
	 * NLAL2030_RBMsg is constructor.
	 * The initialization when the instance of NLAL2030_RBMsg is generated.
	 */
	public NLAL2030_RBMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2030_RBMsg is constructor.
	 * The initialization when the instance of NLAL2030_RBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2030_RBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_RO = (EZDBStringItem)newItem("serNum_RO");
		mdseCd_RO = (EZDBStringItem)newItem("mdseCd_RO");
		curLocNum_RO = (EZDBStringItem)newItem("curLocNum_RO");
		locNm_RO = (EZDBStringItem)newItem("locNm_RO");
		svcMachUsgStsCd_RO = (EZDBStringItem)newItem("svcMachUsgStsCd_RO");
		svcMachMstrStsCd_RO = (EZDBStringItem)newItem("svcMachMstrStsCd_RO");
		svcMachMstrPk_RO = (EZDBBigDecimalItem)newItem("svcMachMstrPk_RO");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2030_RBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2030_RBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_RO", "serNum_RO", "RO", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_RO", "mdseCd_RO", "RO", null, TYPE_HANKAKUEISU, "16", null},
	{"curLocNum_RO", "curLocNum_RO", "RO", null, TYPE_HANKAKUEISU, "30", null},
	{"locNm_RO", "locNm_RO", "RO", null, TYPE_HANKAKUEISU, "60", null},
	{"svcMachUsgStsCd_RO", "svcMachUsgStsCd_RO", "RO", null, TYPE_HANKAKUEISU, "3", null},
	{"svcMachMstrStsCd_RO", "svcMachMstrStsCd_RO", "RO", null, TYPE_HANKAKUEISU, "5", null},
	{"svcMachMstrPk_RO", "svcMachMstrPk_RO", "RO", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_RO
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_RO
        {"CUR_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curLocNum_RO
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_RO
        {"SVC_MACH_USG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachUsgStsCd_RO
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_RO
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_RO
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

