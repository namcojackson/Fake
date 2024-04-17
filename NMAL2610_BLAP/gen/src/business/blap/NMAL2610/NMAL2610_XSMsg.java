//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20171121171621000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2610_XSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2610 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2610_XSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD_R1*/
	public final EZDSStringItem              orgCd_R1;

    /** PRNT_ORG_CD_R1*/
	public final EZDSStringItem              prntOrgCd_R1;

    /** ORG_STRU_TP_CD_R1*/
	public final EZDSStringItem              orgStruTpCd_R1;

    /** GNRN_TP_CD_R1*/
	public final EZDSStringItem              gnrnTpCd_R1;

    /** EFF_FROM_DT_R1*/
	public final EZDSDateItem              effFromDt_R1;

    /** _EZUpdateDateTime_R1*/
	public final EZDSStringItem              ezUpTime_R1;

    /** _EZUpTimeZone_R1*/
	public final EZDSStringItem              ezUpTimeZone_R1;

    /** DS_ORG_RELN_PK_R1*/
	public final EZDSBigDecimalItem              dsOrgRelnPk_R1;


	/**
	 * NMAL2610_XSMsg is constructor.
	 * The initialization when the instance of NMAL2610_XSMsg is generated.
	 */
	public NMAL2610_XSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2610_XSMsg is constructor.
	 * The initialization when the instance of NMAL2610_XSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2610_XSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd_R1 = (EZDSStringItem)newItem("orgCd_R1");
		prntOrgCd_R1 = (EZDSStringItem)newItem("prntOrgCd_R1");
		orgStruTpCd_R1 = (EZDSStringItem)newItem("orgStruTpCd_R1");
		gnrnTpCd_R1 = (EZDSStringItem)newItem("gnrnTpCd_R1");
		effFromDt_R1 = (EZDSDateItem)newItem("effFromDt_R1");
		ezUpTime_R1 = (EZDSStringItem)newItem("ezUpTime_R1");
		ezUpTimeZone_R1 = (EZDSStringItem)newItem("ezUpTimeZone_R1");
		dsOrgRelnPk_R1 = (EZDSBigDecimalItem)newItem("dsOrgRelnPk_R1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2610_XSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2610_XSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd_R1", "orgCd_R1", "R1", null, TYPE_HANKAKUEISU, "8", null},
	{"prntOrgCd_R1", "prntOrgCd_R1", "R1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_R1", "orgStruTpCd_R1", "R1", null, TYPE_HANKAKUEISU, "8", null},
	{"gnrnTpCd_R1", "gnrnTpCd_R1", "R1", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_R1", "effFromDt_R1", "R1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_R1", "ezUpTime_R1", "R1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_R1", "ezUpTimeZone_R1", "R1", null, TYPE_HANKAKUEISU, "5", null},
	{"dsOrgRelnPk_R1", "dsOrgRelnPk_R1", "R1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_R1
        {"PRNT_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntOrgCd_R1
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_R1
        {"GNRN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_R1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_R1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_R1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_R1
        {"DS_ORG_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrgRelnPk_R1
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

