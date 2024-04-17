//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170307132047000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_XSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2520;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2520 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_XSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD_X1*/
	public final EZDSStringItem              orgCd_X1;

    /** PRNT_ORG_CD_X1*/
	public final EZDSStringItem              prntOrgCd_X1;

    /** ORG_STRU_TP_CD_X1*/
	public final EZDSStringItem              orgStruTpCd_X1;

    /** GNRN_TP_CD_X1*/
	public final EZDSStringItem              gnrnTpCd_X1;

    /** EFF_FROM_DT_X1*/
	public final EZDSDateItem              effFromDt_X1;

    /** _EZUpdateDateTime_X1*/
	public final EZDSStringItem              ezUpTime_X1;

    /** _EZUpTimeZone_X1*/
	public final EZDSStringItem              ezUpTimeZone_X1;

    /** DS_ORG_RELN_PK_X1*/
	public final EZDSBigDecimalItem              dsOrgRelnPk_X1;


	/**
	 * NMAL2520_XSMsg is constructor.
	 * The initialization when the instance of NMAL2520_XSMsg is generated.
	 */
	public NMAL2520_XSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2520_XSMsg is constructor.
	 * The initialization when the instance of NMAL2520_XSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2520_XSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd_X1 = (EZDSStringItem)newItem("orgCd_X1");
		prntOrgCd_X1 = (EZDSStringItem)newItem("prntOrgCd_X1");
		orgStruTpCd_X1 = (EZDSStringItem)newItem("orgStruTpCd_X1");
		gnrnTpCd_X1 = (EZDSStringItem)newItem("gnrnTpCd_X1");
		effFromDt_X1 = (EZDSDateItem)newItem("effFromDt_X1");
		ezUpTime_X1 = (EZDSStringItem)newItem("ezUpTime_X1");
		ezUpTimeZone_X1 = (EZDSStringItem)newItem("ezUpTimeZone_X1");
		dsOrgRelnPk_X1 = (EZDSBigDecimalItem)newItem("dsOrgRelnPk_X1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2520_XSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2520_XSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd_X1", "orgCd_X1", "X1", null, TYPE_HANKAKUEISU, "8", null},
	{"prntOrgCd_X1", "prntOrgCd_X1", "X1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_X1", "orgStruTpCd_X1", "X1", null, TYPE_HANKAKUEISU, "8", null},
	{"gnrnTpCd_X1", "gnrnTpCd_X1", "X1", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_X1", "effFromDt_X1", "X1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_X1", "ezUpTime_X1", "X1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X1", "ezUpTimeZone_X1", "X1", null, TYPE_HANKAKUEISU, "5", null},
	{"dsOrgRelnPk_X1", "dsOrgRelnPk_X1", "X1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_X1
        {"PRNT_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntOrgCd_X1
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_X1
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_X1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_X1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X1
        {"DS_ORG_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrgRelnPk_X1
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
