//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180517090200000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0560CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0560;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0560 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0560CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** DS_CONTR_DTL_PK_H1*/
	public final EZDCBigDecimalItem              dsContrDtlPk_H1;

    /** XX_MODE_CD_H1*/
	public final EZDCStringItem              xxModeCd_H1;

    /** DS_CONTR_NUM_H1*/
	public final EZDCStringItem              dsContrNum_H1;

    /** SER_NUM_H1*/
	public final EZDCStringItem              serNum_H1;

    /** T_MDL_NM_H1*/
	public final EZDCStringItem              t_MdlNm_H1;

    /** MDSE_DESC_SHORT_TXT_H1*/
	public final EZDCStringItem              mdseDescShortTxt_H1;

    /** CCY_CD_H1*/
	public final EZDCStringItem              ccyCd_H1;

    /** CONTR_CLO_DAY_H1*/
	public final EZDCStringItem              contrCloDay_H1;

    /** BASE_BLLG_TMG_CD_H1*/
	public final EZDCStringItem              baseBllgTmgCd_H1;

    /** BASE_BLLG_CYCLE_CD_H1*/
	public final EZDCStringItem              baseBllgCycleCd_H1;

    /** XX_FIRST_SCD_CTY_ST_ADDR_H1*/
	public final EZDCStringItem              xxFirstScdCtyStAddr_H1;

    /** CONTR_EFF_FROM_DT_H1*/
	public final EZDCDateItem              contrEffFromDt_H1;

    /** CONTR_EFF_THRU_DT_H1*/
	public final EZDCDateItem              contrEffThruDt_H1;

    /** BASE_BLLG_LAST_BLLG_DT_H1*/
	public final EZDCDateItem              baseBllgLastBllgDt_H1;

    /** _EZUpdateDateTime_H1*/
	public final EZDCStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDCStringItem              ezUpTimeZone_H1;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A;

    /** XX_SEL_NUM*/
	public final EZDCStringItem              xxSelNum;

    /** XX_RADIO_BTN_A*/
	public final EZDCBigDecimalItem              xxRadioBtn_A;

    /** BLLG_CYCLE_CD_A1*/
	public final EZDCStringItemArray              bllgCycleCd_A1;

    /** BLLG_CYCLE_NM_A2*/
	public final EZDCStringItemArray              bllgCycleNm_A2;

    /** SVC_MEMO_RSN_CD_A1*/
	public final EZDCStringItemArray              svcMemoRsnCd_A1;

    /** SVC_MEMO_RSN_NM_A2*/
	public final EZDCStringItemArray              svcMemoRsnNm_A2;

    /** ROW_SQ_NUM_A*/
	public final EZDCBigDecimalItem              rowSqNum_A;

    /** UPD_CTRL_FLG_A*/
	public final EZDCStringItem              updCtrlFlg_A;

    /** DS_CONTR_DTL_TP_CD_A*/
	public final EZDCStringItem              dsContrDtlTpCd_A;

    /** DS_CONTR_CATG_CD_A*/
	public final EZDCStringItem              dsContrCatgCd_A;

    /** A*/
	public final business.blap.NSAL0560.NSAL0560_ACMsgArray              A;

    /** SVC_MEMO_RSN_CD_H3*/
	public final EZDCStringItem              svcMemoRsnCd_H3;

    /** SVC_CMNT_TXT_H1*/
	public final EZDCStringItem              svcCmntTxt_H1;

    /** B*/
	public final business.blap.NSAL0560.NSAL0560_BCMsgArray              B;

    /** C*/
	public final business.blap.NSAL0560.NSAL0560_CCMsgArray              C;


	/**
	 * NSAL0560CMsg is constructor.
	 * The initialization when the instance of NSAL0560CMsg is generated.
	 */
	public NSAL0560CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0560CMsg is constructor.
	 * The initialization when the instance of NSAL0560CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0560CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		dsContrDtlPk_H1 = (EZDCBigDecimalItem)newItem("dsContrDtlPk_H1");
		xxModeCd_H1 = (EZDCStringItem)newItem("xxModeCd_H1");
		dsContrNum_H1 = (EZDCStringItem)newItem("dsContrNum_H1");
		serNum_H1 = (EZDCStringItem)newItem("serNum_H1");
		t_MdlNm_H1 = (EZDCStringItem)newItem("t_MdlNm_H1");
		mdseDescShortTxt_H1 = (EZDCStringItem)newItem("mdseDescShortTxt_H1");
		ccyCd_H1 = (EZDCStringItem)newItem("ccyCd_H1");
		contrCloDay_H1 = (EZDCStringItem)newItem("contrCloDay_H1");
		baseBllgTmgCd_H1 = (EZDCStringItem)newItem("baseBllgTmgCd_H1");
		baseBllgCycleCd_H1 = (EZDCStringItem)newItem("baseBllgCycleCd_H1");
		xxFirstScdCtyStAddr_H1 = (EZDCStringItem)newItem("xxFirstScdCtyStAddr_H1");
		contrEffFromDt_H1 = (EZDCDateItem)newItem("contrEffFromDt_H1");
		contrEffThruDt_H1 = (EZDCDateItem)newItem("contrEffThruDt_H1");
		baseBllgLastBllgDt_H1 = (EZDCDateItem)newItem("baseBllgLastBllgDt_H1");
		ezUpTime_H1 = (EZDCStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDCStringItem)newItem("ezUpTimeZone_H1");
		xxPageShowFromNum_A = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxSelNum = (EZDCStringItem)newItem("xxSelNum");
		xxRadioBtn_A = (EZDCBigDecimalItem)newItem("xxRadioBtn_A");
		bllgCycleCd_A1 = (EZDCStringItemArray)newItemArray("bllgCycleCd_A1");
		bllgCycleNm_A2 = (EZDCStringItemArray)newItemArray("bllgCycleNm_A2");
		svcMemoRsnCd_A1 = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_A1");
		svcMemoRsnNm_A2 = (EZDCStringItemArray)newItemArray("svcMemoRsnNm_A2");
		rowSqNum_A = (EZDCBigDecimalItem)newItem("rowSqNum_A");
		updCtrlFlg_A = (EZDCStringItem)newItem("updCtrlFlg_A");
		dsContrDtlTpCd_A = (EZDCStringItem)newItem("dsContrDtlTpCd_A");
		dsContrCatgCd_A = (EZDCStringItem)newItem("dsContrCatgCd_A");
		A = (business.blap.NSAL0560.NSAL0560_ACMsgArray)newMsgArray("A");
		svcMemoRsnCd_H3 = (EZDCStringItem)newItem("svcMemoRsnCd_H3");
		svcCmntTxt_H1 = (EZDCStringItem)newItem("svcCmntTxt_H1");
		B = (business.blap.NSAL0560.NSAL0560_BCMsgArray)newMsgArray("B");
		C = (business.blap.NSAL0560.NSAL0560_CCMsgArray)newMsgArray("C");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0560CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0560CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrDtlPk_H1", "dsContrDtlPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxModeCd_H1", "xxModeCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrNum_H1", "dsContrNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_H1", "serNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm_H1", "t_MdlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseDescShortTxt_H1", "mdseDescShortTxt_H1", "H1", null, TYPE_HANKAKUEISU, "250", null},
	{"ccyCd_H1", "ccyCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"contrCloDay_H1", "contrCloDay_H1", "H1", null, TYPE_HANKAKUSUJI, "2", null},
	{"baseBllgTmgCd_H1", "baseBllgTmgCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"baseBllgCycleCd_H1", "baseBllgCycleCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFirstScdCtyStAddr_H1", "xxFirstScdCtyStAddr_H1", "H1", null, TYPE_HANKAKUEISU, "150", null},
	{"contrEffFromDt_H1", "contrEffFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt_H1", "contrEffThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"baseBllgLastBllgDt_H1", "baseBllgLastBllgDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSelNum", "xxSelNum", null, null, TYPE_HANKAKUEISU, "5", null},
	{"xxRadioBtn_A", "xxRadioBtn_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bllgCycleCd_A1", "bllgCycleCd_A1", "A1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleNm_A2", "bllgCycleNm_A2", "A2", "99", TYPE_HANKAKUEISU, "15", null},
	{"svcMemoRsnCd_A1", "svcMemoRsnCd_A1", "A1", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_A2", "svcMemoRsnNm_A2", "A2", "99", TYPE_HANKAKUEISU, "30", null},
	{"rowSqNum_A", "rowSqNum_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"updCtrlFlg_A", "updCtrlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrDtlTpCd_A", "dsContrDtlTpCd_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"dsContrCatgCd_A", "dsContrCatgCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"A", "A", null, "200", "business.blap.NSAL0560.NSAL0560_ACMsgArray", null, null},
	
	{"svcMemoRsnCd_H3", "svcMemoRsnCd_H3", "H3", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_H1", "svcCmntTxt_H1", "H1", null, TYPE_HANKAKUEISU, "4000", null},
	{"B", "B", null, "200", "business.blap.NSAL0560.NSAL0560_BCMsgArray", null, null},
	
	{"C", "C", null, "200", "business.blap.NSAL0560.NSAL0560_CCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_H1
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_H1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H1
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_H1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_H1
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_H1
        {"CONTR_CLO_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDay_H1
        {"BASE_BLLG_TMG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgTmgCd_H1
        {"BASE_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgCycleCd_H1
        {"XX_FIRST_SCD_CTY_ST_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFirstScdCtyStAddr_H1
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt_H1
        {"CONTR_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffThruDt_H1
        {"BASE_BLLG_LAST_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgLastBllgDt_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_SEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelNum
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_A1
        {"BLLG_CYCLE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleNm_A2
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_A1
        {"SVC_MEMO_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_A2
        {"ROW_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rowSqNum_A
        {"UPD_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updCtrlFlg_A
        {"DS_CONTR_DTL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlTpCd_A
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_A
		null,	//A
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H3
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_H1
		null,	//B
		null,	//C
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

