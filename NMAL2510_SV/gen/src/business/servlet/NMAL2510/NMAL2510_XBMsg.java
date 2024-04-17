//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190213095852000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_XBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2510;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2510 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_XBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_STRU_TP_CD_X1*/
	public final EZDBStringItem              orgStruTpCd_X1;

    /** TOC_CD_X1*/
	public final EZDBStringItem              tocCd_X1;

    /** ORG_CD_X1*/
	public final EZDBStringItem              orgCd_X1;

    /** GNRN_TP_CD_X1*/
	public final EZDBStringItem              gnrnTpCd_X1;

    /** _EZUpdateDateTime_X1*/
	public final EZDBStringItem              ezUpTime_X1;

    /** _EZUpTimeZone_X1*/
	public final EZDBStringItem              ezUpTimeZone_X1;

    /** TOC_CD_X2*/
	public final EZDBStringItem              tocCd_X2;

    /** ORG_CHNG_RQST_PK_X2*/
	public final EZDBBigDecimalItem              orgChngRqstPk_X2;

    /** _EZUpdateDateTime_X2*/
	public final EZDBStringItem              ezUpTime_X2;

    /** _EZUpTimeZone_X2*/
	public final EZDBStringItem              ezUpTimeZone_X2;

    /** TOC_CD_X3*/
	public final EZDBStringItem              tocCd_X3;

    /** PSN_CD_X3*/
	public final EZDBStringItem              psnCd_X3;

    /** EFF_FROM_DT_X3*/
	public final EZDBDateItem              effFromDt_X3;

    /** _EZUpdateDateTime_X3*/
	public final EZDBStringItem              ezUpTime_X3;

    /** _EZUpTimeZone_X3*/
	public final EZDBStringItem              ezUpTimeZone_X3;


	/**
	 * NMAL2510_XBMsg is constructor.
	 * The initialization when the instance of NMAL2510_XBMsg is generated.
	 */
	public NMAL2510_XBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2510_XBMsg is constructor.
	 * The initialization when the instance of NMAL2510_XBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2510_XBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgStruTpCd_X1 = (EZDBStringItem)newItem("orgStruTpCd_X1");
		tocCd_X1 = (EZDBStringItem)newItem("tocCd_X1");
		orgCd_X1 = (EZDBStringItem)newItem("orgCd_X1");
		gnrnTpCd_X1 = (EZDBStringItem)newItem("gnrnTpCd_X1");
		ezUpTime_X1 = (EZDBStringItem)newItem("ezUpTime_X1");
		ezUpTimeZone_X1 = (EZDBStringItem)newItem("ezUpTimeZone_X1");
		tocCd_X2 = (EZDBStringItem)newItem("tocCd_X2");
		orgChngRqstPk_X2 = (EZDBBigDecimalItem)newItem("orgChngRqstPk_X2");
		ezUpTime_X2 = (EZDBStringItem)newItem("ezUpTime_X2");
		ezUpTimeZone_X2 = (EZDBStringItem)newItem("ezUpTimeZone_X2");
		tocCd_X3 = (EZDBStringItem)newItem("tocCd_X3");
		psnCd_X3 = (EZDBStringItem)newItem("psnCd_X3");
		effFromDt_X3 = (EZDBDateItem)newItem("effFromDt_X3");
		ezUpTime_X3 = (EZDBStringItem)newItem("ezUpTime_X3");
		ezUpTimeZone_X3 = (EZDBStringItem)newItem("ezUpTimeZone_X3");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2510_XBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2510_XBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgStruTpCd_X1", "orgStruTpCd_X1", "X1", null, TYPE_HANKAKUEISU, "8", null},
	{"tocCd_X1", "tocCd_X1", "X1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_X1", "orgCd_X1", "X1", null, TYPE_HANKAKUEISU, "8", null},
	{"gnrnTpCd_X1", "gnrnTpCd_X1", "X1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_X1", "ezUpTime_X1", "X1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X1", "ezUpTimeZone_X1", "X1", null, TYPE_HANKAKUEISU, "5", null},
	{"tocCd_X2", "tocCd_X2", "X2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgChngRqstPk_X2", "orgChngRqstPk_X2", "X2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_X2", "ezUpTime_X2", "X2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X2", "ezUpTimeZone_X2", "X2", null, TYPE_HANKAKUEISU, "5", null},
	{"tocCd_X3", "tocCd_X3", "X3", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_X3", "psnCd_X3", "X3", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_X3", "effFromDt_X3", "X3", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_X3", "ezUpTime_X3", "X3", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X3", "ezUpTimeZone_X3", "X3", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_STRU_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_X1
        {"TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_X1
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_X1
        {"GNRN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gnrnTpCd_X1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X1
        {"TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_X2
        {"ORG_CHNG_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgChngRqstPk_X2
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X2
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X2
        {"TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_X3
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_X3
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_X3
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X3
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X3
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

