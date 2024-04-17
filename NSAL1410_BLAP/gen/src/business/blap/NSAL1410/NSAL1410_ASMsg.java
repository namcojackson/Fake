//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220624092108000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1410_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1410 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1410_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_A*/
	public final EZDSBigDecimalItem              dsContrPk_A;

    /** DS_CONTR_NUM_A*/
	public final EZDSStringItem              dsContrNum_A;

    /** BILL_TO_CUST_CD_A*/
	public final EZDSStringItem              billToCustCd_A;

    /** LOC_NM_A*/
	public final EZDSStringItem              locNm_A;

    /** SVC_CONTR_BR_CD_A*/
	public final EZDSStringItem              svcContrBrCd_A;

    /** SVC_CONTR_BR_DESC_TXT_A*/
	public final EZDSStringItem              svcContrBrDescTxt_A;

    /** SVC_LINE_BIZ_CD_A*/
	public final EZDSStringItem              svcLineBizCd_A;

    /** XX_GENL_FLD_AREA_TXT_A*/
	public final EZDSStringItem              xxGenlFldAreaTxt_A;

    /** PSN_CD_A1*/
	public final EZDSStringItem              psnCd_A1;

    /** XX_PSN_NM_A1*/
	public final EZDSStringItem              xxPsnNm_A1;

    /** PSN_CD_A2*/
	public final EZDSStringItem              psnCd_A2;

    /** XX_PSN_NM_A2*/
	public final EZDSStringItem              xxPsnNm_A2;

    /** VLD_MSG_TXT_A*/
	public final EZDSStringItem              vldMsgTxt_A;

    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;


	/**
	 * NSAL1410_ASMsg is constructor.
	 * The initialization when the instance of NSAL1410_ASMsg is generated.
	 */
	public NSAL1410_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1410_ASMsg is constructor.
	 * The initialization when the instance of NSAL1410_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1410_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_A = (EZDSBigDecimalItem)newItem("dsContrPk_A");
		dsContrNum_A = (EZDSStringItem)newItem("dsContrNum_A");
		billToCustCd_A = (EZDSStringItem)newItem("billToCustCd_A");
		locNm_A = (EZDSStringItem)newItem("locNm_A");
		svcContrBrCd_A = (EZDSStringItem)newItem("svcContrBrCd_A");
		svcContrBrDescTxt_A = (EZDSStringItem)newItem("svcContrBrDescTxt_A");
		svcLineBizCd_A = (EZDSStringItem)newItem("svcLineBizCd_A");
		xxGenlFldAreaTxt_A = (EZDSStringItem)newItem("xxGenlFldAreaTxt_A");
		psnCd_A1 = (EZDSStringItem)newItem("psnCd_A1");
		xxPsnNm_A1 = (EZDSStringItem)newItem("xxPsnNm_A1");
		psnCd_A2 = (EZDSStringItem)newItem("psnCd_A2");
		xxPsnNm_A2 = (EZDSStringItem)newItem("xxPsnNm_A2");
		vldMsgTxt_A = (EZDSStringItem)newItem("vldMsgTxt_A");
		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1410_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1410_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_A", "dsContrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A", "locNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"svcContrBrCd_A", "svcContrBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_A", "svcContrBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcLineBizCd_A", "svcLineBizCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"xxGenlFldAreaTxt_A", "xxGenlFldAreaTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"psnCd_A1", "psnCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_A1", "xxPsnNm_A1", "A1", null, TYPE_HANKAKUEISU, "62", null},
	{"psnCd_A2", "psnCd_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_A2", "xxPsnNm_A2", "A2", null, TYPE_HANKAKUEISU, "62", null},
	{"vldMsgTxt_A", "vldMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_A
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_A
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_A
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A2
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A2
        {"VLD_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMsgTxt_A
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
