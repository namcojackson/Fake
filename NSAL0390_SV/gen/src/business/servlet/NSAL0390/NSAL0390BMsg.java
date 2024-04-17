//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118094538000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0390BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0390;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0390 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0390BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** P*/
	public final business.servlet.NSAL0390.NSAL0390_PBMsgArray              P;

    /** CR_CARD_CUST_REF_NUM_P*/
	public final EZDBStringItem              crCardCustRefNum_P;

    /** CR_CARD_EXPR_YR_MTH_P*/
	public final EZDBDateItem              crCardExprYrMth_P;

    /** CUST_PO_NUM_P*/
	public final EZDBStringItem              custPoNum_P;

    /** PO_DT_P*/
	public final EZDBDateItem              poDt_P;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SER_NUM_HF*/
	public final EZDBStringItem              serNum_HF;

    /** SER_NUM_HT*/
	public final EZDBStringItem              serNum_HT;

    /** SVC_MACH_MSTR_PK_HF*/
	public final EZDBBigDecimalItem              svcMachMstrPk_HF;

    /** SVC_MACH_MSTR_PK_HT*/
	public final EZDBBigDecimalItem              svcMachMstrPk_HT;

    /** CR_CARD_CUST_REF_NUM_H*/
	public final EZDBStringItem              crCardCustRefNum_H;

    /** CR_CARD_EXPR_YR_MTH_H*/
	public final EZDBDateItem              crCardExprYrMth_H;

    /** SVC_MEMO_RSN_CD_L*/
	public final EZDBStringItemArray              svcMemoRsnCd_L;

    /** SVC_MEMO_RSN_NM_L*/
	public final EZDBStringItemArray              svcMemoRsnNm_L;

    /** SVC_MEMO_RSN_CD_H*/
	public final EZDBStringItem              svcMemoRsnCd_H;

    /** SVC_CMNT_TXT_H*/
	public final EZDBStringItem              svcCmntTxt_H;

    /** XX_CHK_BOX_H0*/
	public final EZDBStringItem              xxChkBox_H0;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** A*/
	public final business.servlet.NSAL0390.NSAL0390_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_NUM_EV*/
	public final EZDBBigDecimalItem              xxNum_EV;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** DS_CR_CARD_PK*/
	public final EZDBBigDecimalItem              dsCrCardPk;

    /** XX_POP_PRM_01*/
	public final EZDBStringItem              xxPopPrm_01;

    /** XX_POP_PRM_02*/
	public final EZDBStringItem              xxPopPrm_02;

    /** XX_POP_PRM_03*/
	public final EZDBStringItem              xxPopPrm_03;

    /** XX_POP_PRM_04*/
	public final EZDBStringItem              xxPopPrm_04;

    /** XX_POP_PRM_05*/
	public final EZDBStringItem              xxPopPrm_05;

    /** XX_POP_PRM_06*/
	public final EZDBStringItem              xxPopPrm_06;

    /** XX_POP_PRM_07*/
	public final EZDBStringItem              xxPopPrm_07;

    /** XX_POP_PRM_08*/
	public final EZDBStringItem              xxPopPrm_08;

    /** XX_POP_PRM_09*/
	public final EZDBStringItem              xxPopPrm_09;

    /** XX_POP_PRM_10*/
	public final EZDBStringItem              xxPopPrm_10;

    /** XX_POP_PRM_11*/
	public final EZDBStringItem              xxPopPrm_11;

    /** XX_POP_PRM_12*/
	public final EZDBStringItem              xxPopPrm_12;

    /** XX_POP_PRM_13*/
	public final EZDBStringItem              xxPopPrm_13;

    /** DS_CONTR_PK_P*/
	public final EZDBBigDecimalItem              dsContrPk_P;

    /** DS_CONTR_DTL_PK_P*/
	public final EZDBBigDecimalItem              dsContrDtlPk_P;

    /** O*/
	public final business.servlet.NSAL0390.NSAL0390_OBMsgArray              O;

    /** SVC_MEMO_CATG_CD_P1*/
	public final EZDBStringItem              svcMemoCatgCd_P1;

    /** SVC_MEMO_TP_CD_P1*/
	public final EZDBStringItem              svcMemoTpCd_P1;

    /** XX_COMN_SCR_COL_LB_TXT_H1*/
	public final EZDBStringItem              xxComnScrColLbTxt_H1;

    /** XX_COMN_SCR_COL_LB_TXT_H2*/
	public final EZDBStringItem              xxComnScrColLbTxt_H2;

    /** XX_COMN_SCR_COL_LB_TXT_H3*/
	public final EZDBStringItem              xxComnScrColLbTxt_H3;

    /** XX_COMN_SCR_COL_LB_TXT_H4*/
	public final EZDBStringItem              xxComnScrColLbTxt_H4;

    /** XX_COMN_SCR_COL_LB_TXT_H5*/
	public final EZDBStringItem              xxComnScrColLbTxt_H5;

    /** XX_COMN_SCR_COL_LB_TXT_L1*/
	public final EZDBStringItem              xxComnScrColLbTxt_L1;

    /** XX_COMN_SCR_COL_LB_TXT_L2*/
	public final EZDBStringItem              xxComnScrColLbTxt_L2;

    /** XX_COMN_SCR_COL_LB_TXT_L3*/
	public final EZDBStringItem              xxComnScrColLbTxt_L3;

    /** XX_COMN_SCR_COL_LB_TXT_L4*/
	public final EZDBStringItem              xxComnScrColLbTxt_L4;

    /** XX_COMN_SCR_COL_LB_TXT_L5*/
	public final EZDBStringItem              xxComnScrColLbTxt_L5;

    /** XX_COMN_SCR_COL_LB_TXT_C1*/
	public final EZDBStringItem              xxComnScrColLbTxt_C1;

    /** XX_COMN_SCR_COL_LB_TXT_C2*/
	public final EZDBStringItem              xxComnScrColLbTxt_C2;

    /** XX_COMN_SCR_COL_LB_TXT_C3*/
	public final EZDBStringItem              xxComnScrColLbTxt_C3;

    /** XX_COMN_SCR_COL_LB_TXT_C4*/
	public final EZDBStringItem              xxComnScrColLbTxt_C4;

    /** XX_COMN_SCR_COL_LB_TXT_C5*/
	public final EZDBStringItem              xxComnScrColLbTxt_C5;

    /** XX_COMN_SCR_COL_LB_TXT_V1*/
	public final EZDBStringItem              xxComnScrColLbTxt_V1;

    /** XX_COMN_SCR_COL_LB_TXT_V2*/
	public final EZDBStringItem              xxComnScrColLbTxt_V2;

    /** XX_COMN_SCR_COL_LB_TXT_V3*/
	public final EZDBStringItem              xxComnScrColLbTxt_V3;

    /** XX_COMN_SCR_COL_LB_TXT_V4*/
	public final EZDBStringItem              xxComnScrColLbTxt_V4;

    /** XX_COMN_SCR_COL_LB_TXT_V5*/
	public final EZDBStringItem              xxComnScrColLbTxt_V5;


	/**
	 * NSAL0390BMsg is constructor.
	 * The initialization when the instance of NSAL0390BMsg is generated.
	 */
	public NSAL0390BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0390BMsg is constructor.
	 * The initialization when the instance of NSAL0390BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0390BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		P = (business.servlet.NSAL0390.NSAL0390_PBMsgArray)newMsgArray("P");
		crCardCustRefNum_P = (EZDBStringItem)newItem("crCardCustRefNum_P");
		crCardExprYrMth_P = (EZDBDateItem)newItem("crCardExprYrMth_P");
		custPoNum_P = (EZDBStringItem)newItem("custPoNum_P");
		poDt_P = (EZDBDateItem)newItem("poDt_P");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		serNum_HF = (EZDBStringItem)newItem("serNum_HF");
		serNum_HT = (EZDBStringItem)newItem("serNum_HT");
		svcMachMstrPk_HF = (EZDBBigDecimalItem)newItem("svcMachMstrPk_HF");
		svcMachMstrPk_HT = (EZDBBigDecimalItem)newItem("svcMachMstrPk_HT");
		crCardCustRefNum_H = (EZDBStringItem)newItem("crCardCustRefNum_H");
		crCardExprYrMth_H = (EZDBDateItem)newItem("crCardExprYrMth_H");
		svcMemoRsnCd_L = (EZDBStringItemArray)newItemArray("svcMemoRsnCd_L");
		svcMemoRsnNm_L = (EZDBStringItemArray)newItemArray("svcMemoRsnNm_L");
		svcMemoRsnCd_H = (EZDBStringItem)newItem("svcMemoRsnCd_H");
		svcCmntTxt_H = (EZDBStringItem)newItem("svcCmntTxt_H");
		xxChkBox_H0 = (EZDBStringItem)newItem("xxChkBox_H0");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		A = (business.servlet.NSAL0390.NSAL0390_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxNum_EV = (EZDBBigDecimalItem)newItem("xxNum_EV");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		dsCrCardPk = (EZDBBigDecimalItem)newItem("dsCrCardPk");
		xxPopPrm_01 = (EZDBStringItem)newItem("xxPopPrm_01");
		xxPopPrm_02 = (EZDBStringItem)newItem("xxPopPrm_02");
		xxPopPrm_03 = (EZDBStringItem)newItem("xxPopPrm_03");
		xxPopPrm_04 = (EZDBStringItem)newItem("xxPopPrm_04");
		xxPopPrm_05 = (EZDBStringItem)newItem("xxPopPrm_05");
		xxPopPrm_06 = (EZDBStringItem)newItem("xxPopPrm_06");
		xxPopPrm_07 = (EZDBStringItem)newItem("xxPopPrm_07");
		xxPopPrm_08 = (EZDBStringItem)newItem("xxPopPrm_08");
		xxPopPrm_09 = (EZDBStringItem)newItem("xxPopPrm_09");
		xxPopPrm_10 = (EZDBStringItem)newItem("xxPopPrm_10");
		xxPopPrm_11 = (EZDBStringItem)newItem("xxPopPrm_11");
		xxPopPrm_12 = (EZDBStringItem)newItem("xxPopPrm_12");
		xxPopPrm_13 = (EZDBStringItem)newItem("xxPopPrm_13");
		dsContrPk_P = (EZDBBigDecimalItem)newItem("dsContrPk_P");
		dsContrDtlPk_P = (EZDBBigDecimalItem)newItem("dsContrDtlPk_P");
		O = (business.servlet.NSAL0390.NSAL0390_OBMsgArray)newMsgArray("O");
		svcMemoCatgCd_P1 = (EZDBStringItem)newItem("svcMemoCatgCd_P1");
		svcMemoTpCd_P1 = (EZDBStringItem)newItem("svcMemoTpCd_P1");
		xxComnScrColLbTxt_H1 = (EZDBStringItem)newItem("xxComnScrColLbTxt_H1");
		xxComnScrColLbTxt_H2 = (EZDBStringItem)newItem("xxComnScrColLbTxt_H2");
		xxComnScrColLbTxt_H3 = (EZDBStringItem)newItem("xxComnScrColLbTxt_H3");
		xxComnScrColLbTxt_H4 = (EZDBStringItem)newItem("xxComnScrColLbTxt_H4");
		xxComnScrColLbTxt_H5 = (EZDBStringItem)newItem("xxComnScrColLbTxt_H5");
		xxComnScrColLbTxt_L1 = (EZDBStringItem)newItem("xxComnScrColLbTxt_L1");
		xxComnScrColLbTxt_L2 = (EZDBStringItem)newItem("xxComnScrColLbTxt_L2");
		xxComnScrColLbTxt_L3 = (EZDBStringItem)newItem("xxComnScrColLbTxt_L3");
		xxComnScrColLbTxt_L4 = (EZDBStringItem)newItem("xxComnScrColLbTxt_L4");
		xxComnScrColLbTxt_L5 = (EZDBStringItem)newItem("xxComnScrColLbTxt_L5");
		xxComnScrColLbTxt_C1 = (EZDBStringItem)newItem("xxComnScrColLbTxt_C1");
		xxComnScrColLbTxt_C2 = (EZDBStringItem)newItem("xxComnScrColLbTxt_C2");
		xxComnScrColLbTxt_C3 = (EZDBStringItem)newItem("xxComnScrColLbTxt_C3");
		xxComnScrColLbTxt_C4 = (EZDBStringItem)newItem("xxComnScrColLbTxt_C4");
		xxComnScrColLbTxt_C5 = (EZDBStringItem)newItem("xxComnScrColLbTxt_C5");
		xxComnScrColLbTxt_V1 = (EZDBStringItem)newItem("xxComnScrColLbTxt_V1");
		xxComnScrColLbTxt_V2 = (EZDBStringItem)newItem("xxComnScrColLbTxt_V2");
		xxComnScrColLbTxt_V3 = (EZDBStringItem)newItem("xxComnScrColLbTxt_V3");
		xxComnScrColLbTxt_V4 = (EZDBStringItem)newItem("xxComnScrColLbTxt_V4");
		xxComnScrColLbTxt_V5 = (EZDBStringItem)newItem("xxComnScrColLbTxt_V5");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0390BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0390BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"P", "P", null, "1000", "business.servlet.NSAL0390.NSAL0390_PBMsgArray", null, null},
	
	{"crCardCustRefNum_P", "crCardCustRefNum_P", "P", null, TYPE_HANKAKUEISU, "40", null},
	{"crCardExprYrMth_P", "crCardExprYrMth_P", "P", null, TYPE_NENTSUKI, "6", null},
	{"custPoNum_P", "custPoNum_P", "P", null, TYPE_HANKAKUEISU, "35", null},
	{"poDt_P", "poDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"serNum_HF", "serNum_HF", "HF", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_HT", "serNum_HT", "HT", null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk_HF", "svcMachMstrPk_HF", "HF", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk_HT", "svcMachMstrPk_HT", "HT", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"crCardCustRefNum_H", "crCardCustRefNum_H", "H", null, TYPE_HANKAKUEISU, "40", null},
	{"crCardExprYrMth_H", "crCardExprYrMth_H", "H", null, TYPE_NENTSUKI, "6", null},
	{"svcMemoRsnCd_L", "svcMemoRsnCd_L", "L", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_L", "svcMemoRsnNm_L", "L", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcMemoRsnCd_H", "svcMemoRsnCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_H", "svcCmntTxt_H", "H", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxChkBox_H0", "xxChkBox_H0", "H0", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "200", "business.servlet.NSAL0390.NSAL0390_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxNum_EV", "xxNum_EV", "EV", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsCrCardPk", "dsCrCardPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxPopPrm_01", "xxPopPrm_01", "01", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_02", "xxPopPrm_02", "02", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_03", "xxPopPrm_03", "03", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_04", "xxPopPrm_04", "04", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_05", "xxPopPrm_05", "05", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_06", "xxPopPrm_06", "06", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_07", "xxPopPrm_07", "07", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_08", "xxPopPrm_08", "08", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_09", "xxPopPrm_09", "09", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_10", "xxPopPrm_10", "10", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_11", "xxPopPrm_11", "11", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_12", "xxPopPrm_12", "12", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_13", "xxPopPrm_13", "13", null, TYPE_HANKAKUEISU, "300", null},
	{"dsContrPk_P", "dsContrPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_P", "dsContrDtlPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"O", "O", null, "1000", "business.servlet.NSAL0390.NSAL0390_OBMsgArray", null, null},
	
	{"svcMemoCatgCd_P1", "svcMemoCatgCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"svcMemoTpCd_P1", "svcMemoTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxComnScrColLbTxt_H1", "xxComnScrColLbTxt_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_H2", "xxComnScrColLbTxt_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_H3", "xxComnScrColLbTxt_H3", "H3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_H4", "xxComnScrColLbTxt_H4", "H4", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_H5", "xxComnScrColLbTxt_H5", "H5", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_L1", "xxComnScrColLbTxt_L1", "L1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_L2", "xxComnScrColLbTxt_L2", "L2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_L3", "xxComnScrColLbTxt_L3", "L3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_L4", "xxComnScrColLbTxt_L4", "L4", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_L5", "xxComnScrColLbTxt_L5", "L5", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_C1", "xxComnScrColLbTxt_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_C2", "xxComnScrColLbTxt_C2", "C2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_C3", "xxComnScrColLbTxt_C3", "C3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_C4", "xxComnScrColLbTxt_C4", "C4", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_C5", "xxComnScrColLbTxt_C5", "C5", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_V1", "xxComnScrColLbTxt_V1", "V1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_V2", "xxComnScrColLbTxt_V2", "V2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_V3", "xxComnScrColLbTxt_V3", "V3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_V4", "xxComnScrColLbTxt_V4", "V4", null, TYPE_HANKAKUEISU, "30", null},
	{"xxComnScrColLbTxt_V5", "xxComnScrColLbTxt_V5", "V5", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//P
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum_P
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//crCardExprYrMth_P
        {"CUST_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum_P
        {"PO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//poDt_P
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_HF
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_HT
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_HF
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_HT
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum_H
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//crCardExprYrMth_H
        {"SVC_MEMO_RSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_L
        {"SVC_MEMO_RSN_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_L
        {"SVC_MEMO_RSN_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_H
        {"SVC_CMNT_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_H
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H0
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_EV
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"DS_CR_CARD_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCrCardPk
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_01
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_02
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_03
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_04
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_05
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_06
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_07
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_08
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_09
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_10
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_11
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_12
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_13
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_P
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_P
		null,	//O
        {"SVC_MEMO_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoCatgCd_P1
        {"SVC_MEMO_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoTpCd_P1
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_H1
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_H2
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_H3
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_H4
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_H5
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_L1
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_L2
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_L3
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_L4
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_L5
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_C1
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_C2
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_C3
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_C4
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_C5
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_V1
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_V2
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_V3
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_V4
        {"XX_COMN_SCR_COL_LB_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColLbTxt_V5
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

