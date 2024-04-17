//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20181008203853000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_ASMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0010_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CONFIG_MSTR_PK_A*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_A;

    /** SVC_MACH_TP_CD_A*/
	public final EZDSStringItem              svcMachTpCd_A;

    /** MDSE_CD_A*/
	public final EZDSStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDSStringItem              mdseDescShortTxt_A;

    /** MKT_MDL_NM_A*/
	public final EZDSStringItem              mktMdlNm_A;

    /** SER_NUM_A*/
	public final EZDSStringItem              serNum_A;

    /** ISTL_DT_A*/
	public final EZDSDateItem              istlDt_A;

    /** SVC_MACH_MSTR_STS_CD_A*/
	public final EZDSStringItem              svcMachMstrStsCd_A;

    /** SVC_MACH_MSTR_STS_DESC_TXT_A*/
	public final EZDSStringItem              svcMachMstrStsDescTxt_A;

    /** SVC_MACH_MSTR_PK_A1*/
	public final EZDSBigDecimalItem              svcMachMstrPk_A1;

    /** SVC_MACH_MSTR_PK_AA*/
	public final EZDSBigDecimalItem              svcMachMstrPk_AA;

    /** SVC_MACH_MSTR_PK_A2*/
	public final EZDSBigDecimalItem              svcMachMstrPk_A2;

    /** RDD_DT_A*/
	public final EZDSDateItem              rddDt_A;

    /** SVC_MACH_RMV_DT_A*/
	public final EZDSDateItem              svcMachRmvDt_A;

    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;


	/**
	 * NSAL0010_ASMsg is constructor.
	 * The initialization when the instance of NSAL0010_ASMsg is generated.
	 */
	public NSAL0010_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_ASMsg is constructor.
	 * The initialization when the instance of NSAL0010_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcConfigMstrPk_A = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_A");
		svcMachTpCd_A = (EZDSStringItem)newItem("svcMachTpCd_A");
		mdseCd_A = (EZDSStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDSStringItem)newItem("mdseDescShortTxt_A");
		mktMdlNm_A = (EZDSStringItem)newItem("mktMdlNm_A");
		serNum_A = (EZDSStringItem)newItem("serNum_A");
		istlDt_A = (EZDSDateItem)newItem("istlDt_A");
		svcMachMstrStsCd_A = (EZDSStringItem)newItem("svcMachMstrStsCd_A");
		svcMachMstrStsDescTxt_A = (EZDSStringItem)newItem("svcMachMstrStsDescTxt_A");
		svcMachMstrPk_A1 = (EZDSBigDecimalItem)newItem("svcMachMstrPk_A1");
		svcMachMstrPk_AA = (EZDSBigDecimalItem)newItem("svcMachMstrPk_AA");
		svcMachMstrPk_A2 = (EZDSBigDecimalItem)newItem("svcMachMstrPk_A2");
		rddDt_A = (EZDSDateItem)newItem("rddDt_A");
		svcMachRmvDt_A = (EZDSDateItem)newItem("svcMachRmvDt_A");
		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcConfigMstrPk_A", "svcConfigMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachTpCd_A", "svcMachTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"mktMdlNm_A", "mktMdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"istlDt_A", "istlDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"svcMachMstrStsCd_A", "svcMachMstrStsCd_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"svcMachMstrStsDescTxt_A", "svcMachMstrStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcMachMstrPk_A1", "svcMachMstrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk_AA", "svcMachMstrPk_AA", "AA", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk_A2", "svcMachMstrPk_A2", "A2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rddDt_A", "rddDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"svcMachRmvDt_A", "svcMachRmvDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A
        {"SVC_MACH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachTpCd_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"MKT_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlNm_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"ISTL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlDt_A
        {"SVC_MACH_MSTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsCd_A
        {"SVC_MACH_MSTR_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrStsDescTxt_A
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_AA
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A2
        {"RDD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rddDt_A
        {"SVC_MACH_RMV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachRmvDt_A
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

