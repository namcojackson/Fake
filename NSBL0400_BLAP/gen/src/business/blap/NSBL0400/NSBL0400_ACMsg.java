//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180704105837000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0400_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0400 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0400_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MOD_PK_A*/
	public final EZDCBigDecimalItem              svcModPk_A;

    /** XX_DPLY_BY_CD_NM_CNCT_TXT_A*/
	public final EZDCStringItem              xxDplyByCdNmCnctTxt_A;

    /** SVC_MOD_PLN_ID_A*/
	public final EZDCStringItem              svcModPlnId_A;

    /** SVC_MOD_NM_A*/
	public final EZDCStringItem              svcModNm_A;

    /** SVC_MOD_CMNT_TXT_A*/
	public final EZDCStringItem              svcModCmntTxt_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDCStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDCStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDCStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDCStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDCStringItem              xxRecHistTblNm_A;


	/**
	 * NSBL0400_ACMsg is constructor.
	 * The initialization when the instance of NSBL0400_ACMsg is generated.
	 */
	public NSBL0400_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0400_ACMsg is constructor.
	 * The initialization when the instance of NSBL0400_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0400_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcModPk_A = (EZDCBigDecimalItem)newItem("svcModPk_A");
		xxDplyByCdNmCnctTxt_A = (EZDCStringItem)newItem("xxDplyByCdNmCnctTxt_A");
		svcModPlnId_A = (EZDCStringItem)newItem("svcModPlnId_A");
		svcModNm_A = (EZDCStringItem)newItem("svcModNm_A");
		svcModCmntTxt_A = (EZDCStringItem)newItem("svcModCmntTxt_A");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0400_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0400_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcModPk_A", "svcModPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxDplyByCdNmCnctTxt_A", "xxDplyByCdNmCnctTxt_A", "A", null, TYPE_HANKAKUEISU, "70", null},
	{"svcModPlnId_A", "svcModPlnId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"svcModNm_A", "svcModNm_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"svcModCmntTxt_A", "svcModCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "80", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MOD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPk_A
        {"XX_DPLY_BY_CD_NM_CNCT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyByCdNmCnctTxt_A
        {"SVC_MOD_PLN_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModPlnId_A
        {"SVC_MOD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModNm_A
        {"SVC_MOD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModCmntTxt_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

