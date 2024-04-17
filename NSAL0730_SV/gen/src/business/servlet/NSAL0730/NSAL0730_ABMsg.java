//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118170919000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0730_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0730 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0730_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_CTRL_FLG*/
	public final EZDBStringItem              xxDplyCtrlFlg;

    /** XX_SMRY_LINE_FLG*/
	public final EZDBStringItem              xxSmryLineFlg;

    /** XX_CHK_BOX_D1*/
	public final EZDBStringItem              xxChkBox_D1;

    /** XX_CHK_BOX_D2*/
	public final EZDBStringItem              xxChkBox_D2;

    /** XX_CHK_BOX_D3*/
	public final EZDBStringItem              xxChkBox_D3;

    /** XX_SCR_ITEM_34_TXT*/
	public final EZDBStringItem              xxScrItem34Txt;

    /** SER_NUM*/
	public final EZDBStringItem              serNum;

    /** MTR_LB_DESC_TXT*/
	public final EZDBStringItem              mtrLbDescTxt;

    /** CUST_PO_NUM_A*/
	public final EZDBStringItem              custPoNum_A;

    /** PO_FROM_DT_A*/
	public final EZDBDateItem              poFromDt_A;

    /** PO_DT_A*/
	public final EZDBDateItem              poDt_A;

    /** XX_LINK_PROT_DL*/
	public final EZDBStringItem              xxLinkProt_DL;

    /** XX_LINK_PROT_AL*/
	public final EZDBStringItem              xxLinkProt_AL;

    /** LEASE_CMPY_FLG*/
	public final EZDBStringItem              leaseCmpyFlg;

    /** XX_DS_MSG_ENTRY_TXT*/
	public final EZDBStringItem              xxDsMsgEntryTxt;

    /** DS_CONTR_CATG_CD*/
	public final EZDBStringItem              dsContrCatgCd;

    /** DS_CONTR_NUM*/
	public final EZDBStringItem              dsContrNum;

    /** DS_CONTR_STS_CD*/
	public final EZDBStringItem              dsContrStsCd;

    /** DS_CONTR_CR_CARD_PO_PK*/
	public final EZDBBigDecimalItem              dsContrCrCardPoPk;

    /** DS_CONTR_MACH_LVL_NUM*/
	public final EZDBStringItem              dsContrMachLvlNum;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** DS_CONTR_DTL_PK*/
	public final EZDBBigDecimalItem              dsContrDtlPk;

    /** SVC_MACH_MSTR_PK*/
	public final EZDBBigDecimalItem              svcMachMstrPk;

    /** DS_CONTR_DTL_STS_CD*/
	public final EZDBStringItem              dsContrDtlStsCd;

    /** DS_CONTR_DTL_TP_CD*/
	public final EZDBStringItem              dsContrDtlTpCd;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk;

    /** BLLG_MTR_LB_CD*/
	public final EZDBStringItem              bllgMtrLbCd;

    /** DS_CONTR_BLLG_MTR_STS_CD*/
	public final EZDBStringItem              dsContrBllgMtrStsCd;

    /** XX_BTN_FLG*/
	public final EZDBStringItem              xxBtnFlg;

    /** DS_CONTR_PK_P*/
	public final EZDBBigDecimalItem              dsContrPk_P;

    /** XX_EXST_FLG_D1*/
	public final EZDBStringItem              xxExstFlg_D1;

    /** XX_EXST_FLG_D2*/
	public final EZDBStringItem              xxExstFlg_D2;

    /** XX_EXST_FLG_D3*/
	public final EZDBStringItem              xxExstFlg_D3;

    /** XX_FLG_NM*/
	public final EZDBStringItem              xxFlgNm;

    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDBStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDBStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDBStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDBStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDBStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0730_ABMsg is constructor.
	 * The initialization when the instance of NSAL0730_ABMsg is generated.
	 */
	public NSAL0730_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0730_ABMsg is constructor.
	 * The initialization when the instance of NSAL0730_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0730_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyCtrlFlg = (EZDBStringItem)newItem("xxDplyCtrlFlg");
		xxSmryLineFlg = (EZDBStringItem)newItem("xxSmryLineFlg");
		xxChkBox_D1 = (EZDBStringItem)newItem("xxChkBox_D1");
		xxChkBox_D2 = (EZDBStringItem)newItem("xxChkBox_D2");
		xxChkBox_D3 = (EZDBStringItem)newItem("xxChkBox_D3");
		xxScrItem34Txt = (EZDBStringItem)newItem("xxScrItem34Txt");
		serNum = (EZDBStringItem)newItem("serNum");
		mtrLbDescTxt = (EZDBStringItem)newItem("mtrLbDescTxt");
		custPoNum_A = (EZDBStringItem)newItem("custPoNum_A");
		poFromDt_A = (EZDBDateItem)newItem("poFromDt_A");
		poDt_A = (EZDBDateItem)newItem("poDt_A");
		xxLinkProt_DL = (EZDBStringItem)newItem("xxLinkProt_DL");
		xxLinkProt_AL = (EZDBStringItem)newItem("xxLinkProt_AL");
		leaseCmpyFlg = (EZDBStringItem)newItem("leaseCmpyFlg");
		xxDsMsgEntryTxt = (EZDBStringItem)newItem("xxDsMsgEntryTxt");
		dsContrCatgCd = (EZDBStringItem)newItem("dsContrCatgCd");
		dsContrNum = (EZDBStringItem)newItem("dsContrNum");
		dsContrStsCd = (EZDBStringItem)newItem("dsContrStsCd");
		dsContrCrCardPoPk = (EZDBBigDecimalItem)newItem("dsContrCrCardPoPk");
		dsContrMachLvlNum = (EZDBStringItem)newItem("dsContrMachLvlNum");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		dsContrDtlPk = (EZDBBigDecimalItem)newItem("dsContrDtlPk");
		svcMachMstrPk = (EZDBBigDecimalItem)newItem("svcMachMstrPk");
		dsContrDtlStsCd = (EZDBStringItem)newItem("dsContrDtlStsCd");
		dsContrDtlTpCd = (EZDBStringItem)newItem("dsContrDtlTpCd");
		dsContrBllgMtrPk = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk");
		bllgMtrLbCd = (EZDBStringItem)newItem("bllgMtrLbCd");
		dsContrBllgMtrStsCd = (EZDBStringItem)newItem("dsContrBllgMtrStsCd");
		xxBtnFlg = (EZDBStringItem)newItem("xxBtnFlg");
		dsContrPk_P = (EZDBBigDecimalItem)newItem("dsContrPk_P");
		xxExstFlg_D1 = (EZDBStringItem)newItem("xxExstFlg_D1");
		xxExstFlg_D2 = (EZDBStringItem)newItem("xxExstFlg_D2");
		xxExstFlg_D3 = (EZDBStringItem)newItem("xxExstFlg_D3");
		xxFlgNm = (EZDBStringItem)newItem("xxFlgNm");
		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0730_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0730_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyCtrlFlg", "xxDplyCtrlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSmryLineFlg", "xxSmryLineFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_D1", "xxChkBox_D1", "D1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_D2", "xxChkBox_D2", "D2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_D3", "xxChkBox_D3", "D3", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxScrItem34Txt", "xxScrItem34Txt", null, null, TYPE_HANKAKUEISU, "34", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbDescTxt", "mtrLbDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"custPoNum_A", "custPoNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"poFromDt_A", "poFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"poDt_A", "poDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"xxLinkProt_DL", "xxLinkProt_DL", "DL", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLinkProt_AL", "xxLinkProt_AL", "AL", null, TYPE_HANKAKUEISU, "1", null},
	{"leaseCmpyFlg", "leaseCmpyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxDsMsgEntryTxt", "xxDsMsgEntryTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"dsContrCatgCd", "dsContrCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrStsCd", "dsContrStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrCrCardPoPk", "dsContrCrCardPoPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrMachLvlNum", "dsContrMachLvlNum", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlStsCd", "dsContrDtlStsCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrDtlTpCd", "dsContrDtlTpCd", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrBllgMtrStsCd", "dsContrBllgMtrStsCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxBtnFlg", "xxBtnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrPk_P", "dsContrPk_P", "P", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxExstFlg_D1", "xxExstFlg_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxExstFlg_D2", "xxExstFlg_D2", "D2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxExstFlg_D3", "xxExstFlg_D3", "D3", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFlgNm", "xxFlgNm", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
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

        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg
        {"XX_SMRY_LINE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D2
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D3
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt
        {"CUST_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum_A
        {"PO_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//poFromDt_A
        {"PO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//poDt_A
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_DL
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_AL
        {"LEASE_CMPY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyFlg
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt
        {"DS_CONTR_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd
        {"DS_CONTR_CR_CARD_PO_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCrCardPoPk
        {"DS_CONTR_MACH_LVL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrMachLvlNum
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd
        {"DS_CONTR_DTL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlTpCd
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"DS_CONTR_BLLG_MTR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrStsCd
        {"XX_BTN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_P
        {"XX_EXST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_D1
        {"XX_EXST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_D2
        {"XX_EXST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_D3
        {"XX_FLG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

