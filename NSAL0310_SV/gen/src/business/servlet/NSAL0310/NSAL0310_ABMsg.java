//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230417152455000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0310_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0310;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0310 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0310_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DTL_STS_CD_A*/
	public final EZDBStringItem              xxDtlStsCd_A;

    /** DS_ACCT_NUM_A*/
	public final EZDBStringItem              dsAcctNum_A;

    /** SVC_CONFIG_MSTR_PK_A*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_A;

    /** SVC_MACH_TP_CD_A*/
	public final EZDBStringItem              svcMachTpCd_A;

    /** XX_CHK_BOX_AM*/
	public final EZDBStringItem              xxChkBox_AM;

    /** XX_CHK_BOX_AA*/
	public final EZDBStringItem              xxChkBox_AA;

    /** XX_LINK_PROT_AC*/
	public final EZDBStringItem              xxLinkProt_AC;

    /** XX_LINK_PROT_AE*/
	public final EZDBStringItem              xxLinkProt_AE;

    /** SVC_MACH_MSTR_PK_A*/
	public final EZDBBigDecimalItem              svcMachMstrPk_A;

    /** SER_NUM_A*/
	public final EZDBStringItem              serNum_A;

    /** MDL_NM_A*/
	public final EZDBStringItem              mdlNm_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDBStringItem              mdseDescShortTxt_A;

    /** IND_CUR_LOC_NUM_A*/
	public final EZDBStringItem              indCurLocNum_A;

    /** SHIP_TO_LOC_NM_A*/
	public final EZDBStringItem              shipToLocNm_A;

    /** XX_GENL_FLD_AREA_TXT_A*/
	public final EZDBStringItem              xxGenlFldAreaTxt_A;

    /** CONTR_EFF_FROM_DT_A*/
	public final EZDBDateItem              contrEffFromDt_A;

    /** CONTR_EFF_THRU_DT_A*/
	public final EZDBDateItem              contrEffThruDt_A;

    /** XX_CHK_BOX_AB*/
	public final EZDBStringItem              xxChkBox_AB;

    /** MDSE_NM_A*/
	public final EZDBStringItem              mdseNm_A;

    /** PRNT_DS_CONTR_DTL_PK_A*/
	public final EZDBBigDecimalItem              prntDsContrDtlPk_A;

    /** DS_CONTR_DTL_PK_A*/
	public final EZDBBigDecimalItem              dsContrDtlPk_A;


	/**
	 * NSAL0310_ABMsg is constructor.
	 * The initialization when the instance of NSAL0310_ABMsg is generated.
	 */
	public NSAL0310_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0310_ABMsg is constructor.
	 * The initialization when the instance of NSAL0310_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0310_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDtlStsCd_A = (EZDBStringItem)newItem("xxDtlStsCd_A");
		dsAcctNum_A = (EZDBStringItem)newItem("dsAcctNum_A");
		svcConfigMstrPk_A = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_A");
		svcMachTpCd_A = (EZDBStringItem)newItem("svcMachTpCd_A");
		xxChkBox_AM = (EZDBStringItem)newItem("xxChkBox_AM");
		xxChkBox_AA = (EZDBStringItem)newItem("xxChkBox_AA");
		xxLinkProt_AC = (EZDBStringItem)newItem("xxLinkProt_AC");
		xxLinkProt_AE = (EZDBStringItem)newItem("xxLinkProt_AE");
		svcMachMstrPk_A = (EZDBBigDecimalItem)newItem("svcMachMstrPk_A");
		serNum_A = (EZDBStringItem)newItem("serNum_A");
		mdlNm_A = (EZDBStringItem)newItem("mdlNm_A");
		mdseDescShortTxt_A = (EZDBStringItem)newItem("mdseDescShortTxt_A");
		indCurLocNum_A = (EZDBStringItem)newItem("indCurLocNum_A");
		shipToLocNm_A = (EZDBStringItem)newItem("shipToLocNm_A");
		xxGenlFldAreaTxt_A = (EZDBStringItem)newItem("xxGenlFldAreaTxt_A");
		contrEffFromDt_A = (EZDBDateItem)newItem("contrEffFromDt_A");
		contrEffThruDt_A = (EZDBDateItem)newItem("contrEffThruDt_A");
		xxChkBox_AB = (EZDBStringItem)newItem("xxChkBox_AB");
		mdseNm_A = (EZDBStringItem)newItem("mdseNm_A");
		prntDsContrDtlPk_A = (EZDBBigDecimalItem)newItem("prntDsContrDtlPk_A");
		dsContrDtlPk_A = (EZDBBigDecimalItem)newItem("dsContrDtlPk_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0310_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0310_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDtlStsCd_A", "xxDtlStsCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"svcConfigMstrPk_A", "svcConfigMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachTpCd_A", "svcMachTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_AM", "xxChkBox_AM", "AM", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_AA", "xxChkBox_AA", "AA", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLinkProt_AC", "xxLinkProt_AC", "AC", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLinkProt_AE", "xxLinkProt_AE", "AE", null, TYPE_HANKAKUEISU, "1", null},
	{"svcMachMstrPk_A", "svcMachMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"mdlNm_A", "mdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"indCurLocNum_A", "indCurLocNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"shipToLocNm_A", "shipToLocNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"xxGenlFldAreaTxt_A", "xxGenlFldAreaTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"contrEffFromDt_A", "contrEffFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt_A", "contrEffThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_AB", "xxChkBox_AB", "AB", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseNm_A", "mdseNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"prntDsContrDtlPk_A", "prntDsContrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_A", "dsContrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DTL_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlStsCd_A
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A
        {"SVC_MACH_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachTpCd_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AM
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AA
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_AC
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_AE
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"IND_CUR_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//indCurLocNum_A
        {"SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_A
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A
        {"CONTR_EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffFromDt_A
        {"CONTR_EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffThruDt_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AB
        {"MDSE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_A
        {"PRNT_DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsContrDtlPk_A
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A
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

