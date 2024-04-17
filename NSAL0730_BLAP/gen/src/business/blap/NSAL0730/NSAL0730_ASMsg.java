//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20190118170853000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0730_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0730;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0730 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0730_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_CTRL_FLG*/
	public final EZDSStringItem              xxDplyCtrlFlg;

    /** XX_SMRY_LINE_FLG*/
	public final EZDSStringItem              xxSmryLineFlg;

    /** XX_CHK_BOX_D1*/
	public final EZDSStringItem              xxChkBox_D1;

    /** XX_CHK_BOX_D2*/
	public final EZDSStringItem              xxChkBox_D2;

    /** XX_CHK_BOX_D3*/
	public final EZDSStringItem              xxChkBox_D3;

    /** XX_SCR_ITEM_34_TXT*/
	public final EZDSStringItem              xxScrItem34Txt;

    /** SER_NUM*/
	public final EZDSStringItem              serNum;

    /** MTR_LB_DESC_TXT*/
	public final EZDSStringItem              mtrLbDescTxt;

    /** CUST_PO_NUM_A*/
	public final EZDSStringItem              custPoNum_A;

    /** PO_FROM_DT_A*/
	public final EZDSDateItem              poFromDt_A;

    /** PO_DT_A*/
	public final EZDSDateItem              poDt_A;

    /** XX_LINK_PROT_DL*/
	public final EZDSStringItem              xxLinkProt_DL;

    /** XX_LINK_PROT_AL*/
	public final EZDSStringItem              xxLinkProt_AL;

    /** LEASE_CMPY_FLG*/
	public final EZDSStringItem              leaseCmpyFlg;

    /** XX_DS_MSG_ENTRY_TXT*/
	public final EZDSStringItem              xxDsMsgEntryTxt;

    /** DS_CONTR_CATG_CD*/
	public final EZDSStringItem              dsContrCatgCd;

    /** DS_CONTR_NUM*/
	public final EZDSStringItem              dsContrNum;

    /** DS_CONTR_STS_CD*/
	public final EZDSStringItem              dsContrStsCd;

    /** DS_CONTR_CR_CARD_PO_PK*/
	public final EZDSBigDecimalItem              dsContrCrCardPoPk;

    /** DS_CONTR_MACH_LVL_NUM*/
	public final EZDSStringItem              dsContrMachLvlNum;

    /** _EZUpdateDateTime*/
	public final EZDSStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDSStringItem              ezUpTimeZone;

    /** DS_CONTR_DTL_PK*/
	public final EZDSBigDecimalItem              dsContrDtlPk;

    /** SVC_MACH_MSTR_PK*/
	public final EZDSBigDecimalItem              svcMachMstrPk;

    /** DS_CONTR_DTL_STS_CD*/
	public final EZDSStringItem              dsContrDtlStsCd;

    /** DS_CONTR_DTL_TP_CD*/
	public final EZDSStringItem              dsContrDtlTpCd;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDSBigDecimalItem              dsContrBllgMtrPk;

    /** BLLG_MTR_LB_CD*/
	public final EZDSStringItem              bllgMtrLbCd;

    /** DS_CONTR_BLLG_MTR_STS_CD*/
	public final EZDSStringItem              dsContrBllgMtrStsCd;

    /** XX_BTN_FLG*/
	public final EZDSStringItem              xxBtnFlg;

    /** DS_CONTR_PK_P*/
	public final EZDSBigDecimalItem              dsContrPk_P;

    /** XX_EXST_FLG_D1*/
	public final EZDSStringItem              xxExstFlg_D1;

    /** XX_EXST_FLG_D2*/
	public final EZDSStringItem              xxExstFlg_D2;

    /** XX_EXST_FLG_D3*/
	public final EZDSStringItem              xxExstFlg_D3;

    /** XX_FLG_NM*/
	public final EZDSStringItem              xxFlgNm;

    /** XX_ROW_NUM_A*/
	public final EZDSBigDecimalItem              xxRowNum_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDSStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDSStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDSStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDSStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDSStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0730_ASMsg is constructor.
	 * The initialization when the instance of NSAL0730_ASMsg is generated.
	 */
	public NSAL0730_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0730_ASMsg is constructor.
	 * The initialization when the instance of NSAL0730_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0730_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyCtrlFlg = (EZDSStringItem)newItem("xxDplyCtrlFlg");
		xxSmryLineFlg = (EZDSStringItem)newItem("xxSmryLineFlg");
		xxChkBox_D1 = (EZDSStringItem)newItem("xxChkBox_D1");
		xxChkBox_D2 = (EZDSStringItem)newItem("xxChkBox_D2");
		xxChkBox_D3 = (EZDSStringItem)newItem("xxChkBox_D3");
		xxScrItem34Txt = (EZDSStringItem)newItem("xxScrItem34Txt");
		serNum = (EZDSStringItem)newItem("serNum");
		mtrLbDescTxt = (EZDSStringItem)newItem("mtrLbDescTxt");
		custPoNum_A = (EZDSStringItem)newItem("custPoNum_A");
		poFromDt_A = (EZDSDateItem)newItem("poFromDt_A");
		poDt_A = (EZDSDateItem)newItem("poDt_A");
		xxLinkProt_DL = (EZDSStringItem)newItem("xxLinkProt_DL");
		xxLinkProt_AL = (EZDSStringItem)newItem("xxLinkProt_AL");
		leaseCmpyFlg = (EZDSStringItem)newItem("leaseCmpyFlg");
		xxDsMsgEntryTxt = (EZDSStringItem)newItem("xxDsMsgEntryTxt");
		dsContrCatgCd = (EZDSStringItem)newItem("dsContrCatgCd");
		dsContrNum = (EZDSStringItem)newItem("dsContrNum");
		dsContrStsCd = (EZDSStringItem)newItem("dsContrStsCd");
		dsContrCrCardPoPk = (EZDSBigDecimalItem)newItem("dsContrCrCardPoPk");
		dsContrMachLvlNum = (EZDSStringItem)newItem("dsContrMachLvlNum");
		ezUpTime = (EZDSStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDSStringItem)newItem("ezUpTimeZone");
		dsContrDtlPk = (EZDSBigDecimalItem)newItem("dsContrDtlPk");
		svcMachMstrPk = (EZDSBigDecimalItem)newItem("svcMachMstrPk");
		dsContrDtlStsCd = (EZDSStringItem)newItem("dsContrDtlStsCd");
		dsContrDtlTpCd = (EZDSStringItem)newItem("dsContrDtlTpCd");
		dsContrBllgMtrPk = (EZDSBigDecimalItem)newItem("dsContrBllgMtrPk");
		bllgMtrLbCd = (EZDSStringItem)newItem("bllgMtrLbCd");
		dsContrBllgMtrStsCd = (EZDSStringItem)newItem("dsContrBllgMtrStsCd");
		xxBtnFlg = (EZDSStringItem)newItem("xxBtnFlg");
		dsContrPk_P = (EZDSBigDecimalItem)newItem("dsContrPk_P");
		xxExstFlg_D1 = (EZDSStringItem)newItem("xxExstFlg_D1");
		xxExstFlg_D2 = (EZDSStringItem)newItem("xxExstFlg_D2");
		xxExstFlg_D3 = (EZDSStringItem)newItem("xxExstFlg_D3");
		xxFlgNm = (EZDSStringItem)newItem("xxFlgNm");
		xxRowNum_A = (EZDSBigDecimalItem)newItem("xxRowNum_A");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0730_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0730_ASMsgArray();
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

        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg
        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_D3
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt
        {"CUST_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum_A
        {"PO_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poFromDt_A
        {"PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDt_A
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_DL
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_AL
        {"LEASE_CMPY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyFlg
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd
        {"DS_CONTR_CR_CARD_PO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCrCardPoPk
        {"DS_CONTR_MACH_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrMachLvlNum
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd
        {"DS_CONTR_DTL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlTpCd
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"DS_CONTR_BLLG_MTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrStsCd
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_P
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_D1
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_D2
        {"XX_EXST_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExstFlg_D3
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
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

