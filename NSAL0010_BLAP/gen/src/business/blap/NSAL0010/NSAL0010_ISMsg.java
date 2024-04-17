//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181008203853000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_ISMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_ISMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_MSTR_TRK_PK_I*/
	public final EZDSBigDecimalItem              svcMachMstrTrkPk_I;

    /** TRX_RGTN_DT_I*/
	public final EZDSDateItem              trxRgtnDt_I;

    /** MACH_HIST_ATTRB_LONG_NM_I*/
	public final EZDSStringItem              machHistAttrbLongNm_I;

    /** OLD_VAL_TXT_I*/
	public final EZDSStringItem              oldValTxt_I;

    /** NEW_VAL_TXT_I*/
	public final EZDSStringItem              newValTxt_I;

    /** UPD_USR_ID_I*/
	public final EZDSStringItem              updUsrId_I;

    /** XX_YES_NO_CD_IA*/
	public final EZDSStringItem              xxYesNoCd_IA;

    /** XX_YES_NO_CD_I*/
	public final EZDSStringItem              xxYesNoCd_I;


	/**
	 * NSAL0010_ISMsg is constructor.
	 * The initialization when the instance of NSAL0010_ISMsg is generated.
	 */
	public NSAL0010_ISMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_ISMsg is constructor.
	 * The initialization when the instance of NSAL0010_ISMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_ISMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachMstrTrkPk_I = (EZDSBigDecimalItem)newItem("svcMachMstrTrkPk_I");
		trxRgtnDt_I = (EZDSDateItem)newItem("trxRgtnDt_I");
		machHistAttrbLongNm_I = (EZDSStringItem)newItem("machHistAttrbLongNm_I");
		oldValTxt_I = (EZDSStringItem)newItem("oldValTxt_I");
		newValTxt_I = (EZDSStringItem)newItem("newValTxt_I");
		updUsrId_I = (EZDSStringItem)newItem("updUsrId_I");
		xxYesNoCd_IA = (EZDSStringItem)newItem("xxYesNoCd_IA");
		xxYesNoCd_I = (EZDSStringItem)newItem("xxYesNoCd_I");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_ISMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_ISMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachMstrTrkPk_I", "svcMachMstrTrkPk_I", "I", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"trxRgtnDt_I", "trxRgtnDt_I", "I", null, TYPE_NENTSUKIHI, "8", null},
	{"machHistAttrbLongNm_I", "machHistAttrbLongNm_I", "I", null, TYPE_HANKAKUEISU, "200", null},
	{"oldValTxt_I", "oldValTxt_I", "I", null, TYPE_HANKAKUEISU, "300", null},
	{"newValTxt_I", "newValTxt_I", "I", null, TYPE_HANKAKUEISU, "300", null},
	{"updUsrId_I", "updUsrId_I", "I", null, TYPE_HANKAKUEISU, "16", null},
	{"xxYesNoCd_IA", "xxYesNoCd_IA", "IA", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_I", "xxYesNoCd_I", "I", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_MSTR_TRK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrTrkPk_I
        {"TRX_RGTN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRgtnDt_I
        {"MACH_HIST_ATTRB_LONG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//machHistAttrbLongNm_I
        {"OLD_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldValTxt_I
        {"NEW_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newValTxt_I
        {"UPD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_I
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_IA
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_I
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

