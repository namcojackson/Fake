//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20100921133535000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFAL9020_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFAL9020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFAL9020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFAL9020_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AJE_ID_A*/
	public final EZDCStringItem              ajeId_A;

    /** SYS_SRC_NM_A*/
	public final EZDCStringItem              sysSrcNm_A;

    /** TRX_NM_A*/
	public final EZDCStringItem              trxNm_A;

    /** TRX_RSN_NM_A*/
	public final EZDCStringItem              trxRsnNm_A;

    /** AJE_PTRN_IND_TP_CD_01*/
	public final EZDCStringItem              ajePtrnIndTpCd_01;

    /** AJE_PTRN_IND_TP_NM_01*/
	public final EZDCStringItem              ajePtrnIndTpNm_01;

    /** AJE_PTRN_ACTL_CD_01*/
	public final EZDCStringItem              ajePtrnActlCd_01;

    /** AJE_PTRN_ACTL_NM_01*/
	public final EZDCStringItem              ajePtrnActlNm_01;

    /** AJE_PTRN_IND_TP_CD_02*/
	public final EZDCStringItem              ajePtrnIndTpCd_02;

    /** AJE_PTRN_IND_TP_NM_02*/
	public final EZDCStringItem              ajePtrnIndTpNm_02;

    /** AJE_PTRN_ACTL_CD_02*/
	public final EZDCStringItem              ajePtrnActlCd_02;

    /** AJE_PTRN_ACTL_NM_02*/
	public final EZDCStringItem              ajePtrnActlNm_02;

    /** AJE_PTRN_IND_TP_CD_03*/
	public final EZDCStringItem              ajePtrnIndTpCd_03;

    /** AJE_PTRN_IND_TP_NM_03*/
	public final EZDCStringItem              ajePtrnIndTpNm_03;

    /** AJE_PTRN_ACTL_CD_03*/
	public final EZDCStringItem              ajePtrnActlCd_03;

    /** AJE_PTRN_ACTL_NM_03*/
	public final EZDCStringItem              ajePtrnActlNm_03;

    /** JRNL_CATG_CD_A*/
	public final EZDCStringItem              jrnlCatgCd_A;

    /** JRNL_CATG_NM_A*/
	public final EZDCStringItem              jrnlCatgNm_A;


	/**
	 * NFAL9020_ACMsg is constructor.
	 * The initialization when the instance of NFAL9020_ACMsg is generated.
	 */
	public NFAL9020_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFAL9020_ACMsg is constructor.
	 * The initialization when the instance of NFAL9020_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFAL9020_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ajeId_A = (EZDCStringItem)newItem("ajeId_A");
		sysSrcNm_A = (EZDCStringItem)newItem("sysSrcNm_A");
		trxNm_A = (EZDCStringItem)newItem("trxNm_A");
		trxRsnNm_A = (EZDCStringItem)newItem("trxRsnNm_A");
		ajePtrnIndTpCd_01 = (EZDCStringItem)newItem("ajePtrnIndTpCd_01");
		ajePtrnIndTpNm_01 = (EZDCStringItem)newItem("ajePtrnIndTpNm_01");
		ajePtrnActlCd_01 = (EZDCStringItem)newItem("ajePtrnActlCd_01");
		ajePtrnActlNm_01 = (EZDCStringItem)newItem("ajePtrnActlNm_01");
		ajePtrnIndTpCd_02 = (EZDCStringItem)newItem("ajePtrnIndTpCd_02");
		ajePtrnIndTpNm_02 = (EZDCStringItem)newItem("ajePtrnIndTpNm_02");
		ajePtrnActlCd_02 = (EZDCStringItem)newItem("ajePtrnActlCd_02");
		ajePtrnActlNm_02 = (EZDCStringItem)newItem("ajePtrnActlNm_02");
		ajePtrnIndTpCd_03 = (EZDCStringItem)newItem("ajePtrnIndTpCd_03");
		ajePtrnIndTpNm_03 = (EZDCStringItem)newItem("ajePtrnIndTpNm_03");
		ajePtrnActlCd_03 = (EZDCStringItem)newItem("ajePtrnActlCd_03");
		ajePtrnActlNm_03 = (EZDCStringItem)newItem("ajePtrnActlNm_03");
		jrnlCatgCd_A = (EZDCStringItem)newItem("jrnlCatgCd_A");
		jrnlCatgNm_A = (EZDCStringItem)newItem("jrnlCatgNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFAL9020_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFAL9020_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ajeId_A", "ajeId_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"sysSrcNm_A", "sysSrcNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"trxNm_A", "trxNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"trxRsnNm_A", "trxRsnNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"ajePtrnIndTpCd_01", "ajePtrnIndTpCd_01", "01", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpNm_01", "ajePtrnIndTpNm_01", "01", null, TYPE_HANKAKUEISU, "100", null},
	{"ajePtrnActlCd_01", "ajePtrnActlCd_01", "01", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlNm_01", "ajePtrnActlNm_01", "01", null, TYPE_HANKAKUEISU, "70", null},
	{"ajePtrnIndTpCd_02", "ajePtrnIndTpCd_02", "02", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpNm_02", "ajePtrnIndTpNm_02", "02", null, TYPE_HANKAKUEISU, "100", null},
	{"ajePtrnActlCd_02", "ajePtrnActlCd_02", "02", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlNm_02", "ajePtrnActlNm_02", "02", null, TYPE_HANKAKUEISU, "70", null},
	{"ajePtrnIndTpCd_03", "ajePtrnIndTpCd_03", "03", null, TYPE_HANKAKUEISU, "3", null},
	{"ajePtrnIndTpNm_03", "ajePtrnIndTpNm_03", "03", null, TYPE_HANKAKUEISU, "100", null},
	{"ajePtrnActlCd_03", "ajePtrnActlCd_03", "03", null, TYPE_HANKAKUEISU, "20", null},
	{"ajePtrnActlNm_03", "ajePtrnActlNm_03", "03", null, TYPE_HANKAKUEISU, "70", null},
	{"jrnlCatgCd_A", "jrnlCatgCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"jrnlCatgNm_A", "jrnlCatgNm_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AJE_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeId_A
        {"SYS_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcNm_A
        {"TRX_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxNm_A
        {"TRX_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnNm_A
        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_01
        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_01
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_01
        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_01
        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_02
        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_02
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_02
        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_02
        {"AJE_PTRN_IND_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpCd_03
        {"AJE_PTRN_IND_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnIndTpNm_03
        {"AJE_PTRN_ACTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlCd_03
        {"AJE_PTRN_ACTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajePtrnActlNm_03
        {"JRNL_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlCatgCd_A
        {"JRNL_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlCatgNm_A
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

