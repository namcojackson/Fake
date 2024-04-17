//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20131112120639000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0071_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZML0071;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZML0071 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZML0071_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ML_USR_ADDR_PK_A*/
	public final EZDSBigDecimalItem              mlUsrAddrPk_A;

    /** ML_GRP_ID_A*/
	public final EZDSStringItem              mlGrpId_A;

    /** ML_GRP_NM_A*/
	public final EZDSStringItem              mlGrpNm_A;

    /** ML_KEY_FIRST_CD_A*/
	public final EZDSStringItem              mlKeyFirstCd_A;

    /** ML_KEY_SCD_CD_A*/
	public final EZDSStringItem              mlKeyScdCd_A;

    /** ML_KEY_THIRD_CD_A*/
	public final EZDSStringItem              mlKeyThirdCd_A;

    /** ML_GRP_DESC_TXT_A*/
	public final EZDSStringItem              mlGrpDescTxt_A;

    /** ML_USR_ID_A*/
	public final EZDSStringItem              mlUsrId_A;

    /** ML_USR_ADDR_A*/
	public final EZDSStringItem              mlUsrAddr_A;

    /** ML_USR_NM_A*/
	public final EZDSStringItem              mlUsrNm_A;

    /** ML_USR_LOC_ID_A*/
	public final EZDSStringItem              mlUsrLocId_A;

    /** ML_USR_DESC_TXT_A*/
	public final EZDSStringItem              mlUsrDescTxt_A;

    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;


	/**
	 * ZZML0071_ASMsg is constructor.
	 * The initialization when the instance of ZZML0071_ASMsg is generated.
	 */
	public ZZML0071_ASMsg() {
		this(false, -1);
	}

	/**
	 * ZZML0071_ASMsg is constructor.
	 * The initialization when the instance of ZZML0071_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZML0071_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mlUsrAddrPk_A = (EZDSBigDecimalItem)newItem("mlUsrAddrPk_A");
		mlGrpId_A = (EZDSStringItem)newItem("mlGrpId_A");
		mlGrpNm_A = (EZDSStringItem)newItem("mlGrpNm_A");
		mlKeyFirstCd_A = (EZDSStringItem)newItem("mlKeyFirstCd_A");
		mlKeyScdCd_A = (EZDSStringItem)newItem("mlKeyScdCd_A");
		mlKeyThirdCd_A = (EZDSStringItem)newItem("mlKeyThirdCd_A");
		mlGrpDescTxt_A = (EZDSStringItem)newItem("mlGrpDescTxt_A");
		mlUsrId_A = (EZDSStringItem)newItem("mlUsrId_A");
		mlUsrAddr_A = (EZDSStringItem)newItem("mlUsrAddr_A");
		mlUsrNm_A = (EZDSStringItem)newItem("mlUsrNm_A");
		mlUsrLocId_A = (EZDSStringItem)newItem("mlUsrLocId_A");
		mlUsrDescTxt_A = (EZDSStringItem)newItem("mlUsrDescTxt_A");
		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZML0071_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZML0071_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mlUsrAddrPk_A", "mlUsrAddrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mlGrpId_A", "mlGrpId_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"mlGrpNm_A", "mlGrpNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"mlKeyFirstCd_A", "mlKeyFirstCd_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mlKeyScdCd_A", "mlKeyScdCd_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mlKeyThirdCd_A", "mlKeyThirdCd_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mlGrpDescTxt_A", "mlGrpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "300", null},
	{"mlUsrId_A", "mlUsrId_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"mlUsrAddr_A", "mlUsrAddr_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"mlUsrNm_A", "mlUsrNm_A", "A", null, TYPE_KONZAI, "100", null},
	{"mlUsrLocId_A", "mlUsrLocId_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"mlUsrDescTxt_A", "mlUsrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "300", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ML_USR_ADDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrAddrPk_A
        {"ML_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpId_A
        {"ML_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpNm_A
        {"ML_KEY_FIRST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlKeyFirstCd_A
        {"ML_KEY_SCD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlKeyScdCd_A
        {"ML_KEY_THIRD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlKeyThirdCd_A
        {"ML_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlGrpDescTxt_A
        {"ML_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrId_A
        {"ML_USR_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrAddr_A
        {"ML_USR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrNm_A
        {"ML_USR_LOC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrLocId_A
        {"ML_USR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlUsrDescTxt_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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
