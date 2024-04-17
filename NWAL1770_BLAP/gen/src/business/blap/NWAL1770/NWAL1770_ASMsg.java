//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160125093954000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1770_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1770 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1770_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDSStringItem              xxChkBox_A;

    /** SPLY_QUOTE_CTAC_PSN_PK_A*/
	public final EZDSBigDecimalItem              splyQuoteCtacPsnPk_A;

    /** CTAC_PSN_PK_A*/
	public final EZDSBigDecimalItem              ctacPsnPk_A;

    /** CTAC_PSN_TP_CD_A*/
	public final EZDSStringItem              ctacPsnTpCd_A;

    /** CTAC_PSN_FIRST_NM_A*/
	public final EZDSStringItem              ctacPsnFirstNm_A;

    /** CTAC_PSN_FIRST_NM_LK*/
	public final EZDSStringItem              ctacPsnFirstNm_LK;

    /** CTAC_PSN_LAST_NM_A*/
	public final EZDSStringItem              ctacPsnLastNm_A;

    /** CTAC_PSN_TEL_NUM_A*/
	public final EZDSStringItem              ctacPsnTelNum_A;

    /** CTAC_PSN_EXTN_NUM_A*/
	public final EZDSStringItem              ctacPsnExtnNum_A;

    /** CTAC_PSN_EML_ADDR_A*/
	public final EZDSStringItem              ctacPsnEmlAddr_A;

    /** CTAC_PSN_FAX_NUM_A*/
	public final EZDSStringItem              ctacPsnFaxNum_A;

    /** DS_CTAC_PNT_PK_A*/
	public final EZDSBigDecimalItem              dsCtacPntPk_A;

    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;


	/**
	 * NWAL1770_ASMsg is constructor.
	 * The initialization when the instance of NWAL1770_ASMsg is generated.
	 */
	public NWAL1770_ASMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1770_ASMsg is constructor.
	 * The initialization when the instance of NWAL1770_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1770_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDSStringItem)newItem("xxChkBox_A");
		splyQuoteCtacPsnPk_A = (EZDSBigDecimalItem)newItem("splyQuoteCtacPsnPk_A");
		ctacPsnPk_A = (EZDSBigDecimalItem)newItem("ctacPsnPk_A");
		ctacPsnTpCd_A = (EZDSStringItem)newItem("ctacPsnTpCd_A");
		ctacPsnFirstNm_A = (EZDSStringItem)newItem("ctacPsnFirstNm_A");
		ctacPsnFirstNm_LK = (EZDSStringItem)newItem("ctacPsnFirstNm_LK");
		ctacPsnLastNm_A = (EZDSStringItem)newItem("ctacPsnLastNm_A");
		ctacPsnTelNum_A = (EZDSStringItem)newItem("ctacPsnTelNum_A");
		ctacPsnExtnNum_A = (EZDSStringItem)newItem("ctacPsnExtnNum_A");
		ctacPsnEmlAddr_A = (EZDSStringItem)newItem("ctacPsnEmlAddr_A");
		ctacPsnFaxNum_A = (EZDSStringItem)newItem("ctacPsnFaxNum_A");
		dsCtacPntPk_A = (EZDSBigDecimalItem)newItem("dsCtacPntPk_A");
		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1770_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1770_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"splyQuoteCtacPsnPk_A", "splyQuoteCtacPsnPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnPk_A", "ctacPsnPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ctacPsnTpCd_A", "ctacPsnTpCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnFirstNm_A", "ctacPsnFirstNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnFirstNm_LK", "ctacPsnFirstNm_LK", "LK", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnLastNm_A", "ctacPsnLastNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"ctacPsnTelNum_A", "ctacPsnTelNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"ctacPsnExtnNum_A", "ctacPsnExtnNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"ctacPsnEmlAddr_A", "ctacPsnEmlAddr_A", "A", null, TYPE_HANKAKUEISU, "320", null},
	{"ctacPsnFaxNum_A", "ctacPsnFaxNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsCtacPntPk_A", "dsCtacPntPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"SPLY_QUOTE_CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteCtacPsnPk_A
        {"CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnPk_A
        {"CTAC_PSN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTpCd_A
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_A
        {"CTAC_PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFirstNm_LK
        {"CTAC_PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnLastNm_A
        {"CTAC_PSN_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnTelNum_A
        {"CTAC_PSN_EXTN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnExtnNum_A
        {"CTAC_PSN_EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnEmlAddr_A
        {"CTAC_PSN_FAX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctacPsnFaxNum_A
        {"DS_CTAC_PNT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk_A
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

