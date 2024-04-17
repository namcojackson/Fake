//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118094538000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0390_ABMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL0390_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM_A0*/
	public final EZDBStringItem              dsContrNum_A0;

    /** SER_NUM_A0*/
	public final EZDBStringItem              serNum_A0;

    /** MTR_LB_DESC_TXT_A0*/
	public final EZDBStringItem              mtrLbDescTxt_A0;

    /** CR_CARD_CUST_REF_NUM_A0*/
	public final EZDBStringItem              crCardCustRefNum_A0;

    /** CR_CARD_EXPR_YR_MTH_A0*/
	public final EZDBDateItem              crCardExprYrMth_A0;

    /** CR_CARD_CUST_REF_NUM_A1*/
	public final EZDBStringItem              crCardCustRefNum_A1;

    /** CR_CARD_EXPR_YR_MTH_A1*/
	public final EZDBDateItem              crCardExprYrMth_A1;

    /** LEASE_CMPY_FLG_A0*/
	public final EZDBStringItem              leaseCmpyFlg_A0;

    /** XX_CNT_DPLY_FLG_A0*/
	public final EZDBStringItem              xxCntDplyFlg_A0;

    /** XX_DS_MSG_ENTRY_TXT_A0*/
	public final EZDBStringItem              xxDsMsgEntryTxt_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDBStringItem              ezUpTimeZone_A0;

    /** _EZUpdateDateTime_A0*/
	public final EZDBStringItem              ezUpTime_A0;

    /** ALT_PAYER_CUST_CD_A0*/
	public final EZDBStringItem              altPayerCustCd_A0;

    /** DS_ACCT_NUM_A0*/
	public final EZDBStringItem              dsAcctNum_A0;

    /** SELL_TO_CUST_CD_A0*/
	public final EZDBStringItem              sellToCustCd_A0;

    /** XX_DTL_NM_A0*/
	public final EZDBStringItem              xxDtlNm_A0;

    /** XX_NUM_A0*/
	public final EZDBBigDecimalItem              xxNum_A0;

    /** XX_DPLY_CTRL_FLG_A0*/
	public final EZDBStringItem              xxDplyCtrlFlg_A0;

    /** XX_DPLY_CTRL_FLG_A1*/
	public final EZDBStringItem              xxDplyCtrlFlg_A1;

    /** XX_SMRY_LINE_FLG_A0*/
	public final EZDBStringItem              xxSmryLineFlg_A0;

    /** XX_CHK_BOX_A0*/
	public final EZDBStringItem              xxChkBox_A0;

    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** DS_CONTR_MACH_LVL_NUM_A0*/
	public final EZDBStringItem              dsContrMachLvlNum_A0;

    /** DS_CONTR_PK_A0*/
	public final EZDBBigDecimalItem              dsContrPk_A0;

    /** DS_CONTR_DTL_PK_A0*/
	public final EZDBBigDecimalItem              dsContrDtlPk_A0;

    /** DS_CONTR_BLLG_MTR_PK_A0*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk_A0;

    /** DS_CONTR_CR_CARD_PO_PK_A0*/
	public final EZDBBigDecimalItem              dsContrCrCardPoPk_A0;

    /** XX_REC_HIST_CRAT_TS_A0*/
	public final EZDBStringItem              xxRecHistCratTs_A0;

    /** XX_REC_HIST_CRAT_BY_NM_A0*/
	public final EZDBStringItem              xxRecHistCratByNm_A0;

    /** XX_REC_HIST_UPD_TS_A0*/
	public final EZDBStringItem              xxRecHistUpdTs_A0;

    /** XX_REC_HIST_UPD_BY_NM_A0*/
	public final EZDBStringItem              xxRecHistUpdByNm_A0;

    /** XX_REC_HIST_TBL_NM_A0*/
	public final EZDBStringItem              xxRecHistTblNm_A0;


	/**
	 * NSAL0390_ABMsg is constructor.
	 * The initialization when the instance of NSAL0390_ABMsg is generated.
	 */
	public NSAL0390_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0390_ABMsg is constructor.
	 * The initialization when the instance of NSAL0390_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0390_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum_A0 = (EZDBStringItem)newItem("dsContrNum_A0");
		serNum_A0 = (EZDBStringItem)newItem("serNum_A0");
		mtrLbDescTxt_A0 = (EZDBStringItem)newItem("mtrLbDescTxt_A0");
		crCardCustRefNum_A0 = (EZDBStringItem)newItem("crCardCustRefNum_A0");
		crCardExprYrMth_A0 = (EZDBDateItem)newItem("crCardExprYrMth_A0");
		crCardCustRefNum_A1 = (EZDBStringItem)newItem("crCardCustRefNum_A1");
		crCardExprYrMth_A1 = (EZDBDateItem)newItem("crCardExprYrMth_A1");
		leaseCmpyFlg_A0 = (EZDBStringItem)newItem("leaseCmpyFlg_A0");
		xxCntDplyFlg_A0 = (EZDBStringItem)newItem("xxCntDplyFlg_A0");
		xxDsMsgEntryTxt_A0 = (EZDBStringItem)newItem("xxDsMsgEntryTxt_A0");
		ezUpTimeZone_A0 = (EZDBStringItem)newItem("ezUpTimeZone_A0");
		ezUpTime_A0 = (EZDBStringItem)newItem("ezUpTime_A0");
		altPayerCustCd_A0 = (EZDBStringItem)newItem("altPayerCustCd_A0");
		dsAcctNum_A0 = (EZDBStringItem)newItem("dsAcctNum_A0");
		sellToCustCd_A0 = (EZDBStringItem)newItem("sellToCustCd_A0");
		xxDtlNm_A0 = (EZDBStringItem)newItem("xxDtlNm_A0");
		xxNum_A0 = (EZDBBigDecimalItem)newItem("xxNum_A0");
		xxDplyCtrlFlg_A0 = (EZDBStringItem)newItem("xxDplyCtrlFlg_A0");
		xxDplyCtrlFlg_A1 = (EZDBStringItem)newItem("xxDplyCtrlFlg_A1");
		xxSmryLineFlg_A0 = (EZDBStringItem)newItem("xxSmryLineFlg_A0");
		xxChkBox_A0 = (EZDBStringItem)newItem("xxChkBox_A0");
		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		dsContrMachLvlNum_A0 = (EZDBStringItem)newItem("dsContrMachLvlNum_A0");
		dsContrPk_A0 = (EZDBBigDecimalItem)newItem("dsContrPk_A0");
		dsContrDtlPk_A0 = (EZDBBigDecimalItem)newItem("dsContrDtlPk_A0");
		dsContrBllgMtrPk_A0 = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk_A0");
		dsContrCrCardPoPk_A0 = (EZDBBigDecimalItem)newItem("dsContrCrCardPoPk_A0");
		xxRecHistCratTs_A0 = (EZDBStringItem)newItem("xxRecHistCratTs_A0");
		xxRecHistCratByNm_A0 = (EZDBStringItem)newItem("xxRecHistCratByNm_A0");
		xxRecHistUpdTs_A0 = (EZDBStringItem)newItem("xxRecHistUpdTs_A0");
		xxRecHistUpdByNm_A0 = (EZDBStringItem)newItem("xxRecHistUpdByNm_A0");
		xxRecHistTblNm_A0 = (EZDBStringItem)newItem("xxRecHistTblNm_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0390_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0390_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum_A0", "dsContrNum_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_A0", "serNum_A0", "A0", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbDescTxt_A0", "mtrLbDescTxt_A0", "A0", null, TYPE_HANKAKUEISU, "50", null},
	{"crCardCustRefNum_A0", "crCardCustRefNum_A0", "A0", null, TYPE_HANKAKUEISU, "40", null},
	{"crCardExprYrMth_A0", "crCardExprYrMth_A0", "A0", null, TYPE_NENTSUKI, "6", null},
	{"crCardCustRefNum_A1", "crCardCustRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"crCardExprYrMth_A1", "crCardExprYrMth_A1", "A1", null, TYPE_NENTSUKI, "6", null},
	{"leaseCmpyFlg_A0", "leaseCmpyFlg_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"xxCntDplyFlg_A0", "xxCntDplyFlg_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDsMsgEntryTxt_A0", "xxDsMsgEntryTxt_A0", "A0", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_A0", "ezUpTime_A0", "A0", null, TYPE_HANKAKUEISU, "17", null},
	{"altPayerCustCd_A0", "altPayerCustCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNum_A0", "dsAcctNum_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd_A0", "sellToCustCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDtlNm_A0", "xxDtlNm_A0", "A0", null, TYPE_HANKAKUEISU, "70", null},
	{"xxNum_A0", "xxNum_A0", "A0", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxDplyCtrlFlg_A0", "xxDplyCtrlFlg_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A1", "xxDplyCtrlFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSmryLineFlg_A0", "xxSmryLineFlg_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_A0", "xxChkBox_A0", "A0", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrMachLvlNum_A0", "dsContrMachLvlNum_A0", "A0", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrPk_A0", "dsContrPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_A0", "dsContrDtlPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_A0", "dsContrBllgMtrPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrCrCardPoPk_A0", "dsContrCrCardPoPk_A0", "A0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRecHistCratTs_A0", "xxRecHistCratTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A0", "xxRecHistCratByNm_A0", "A0", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A0", "xxRecHistUpdTs_A0", "A0", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A0", "xxRecHistUpdByNm_A0", "A0", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A0", "xxRecHistTblNm_A0", "A0", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A0
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A0
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A0
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum_A0
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//crCardExprYrMth_A0
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum_A1
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//crCardExprYrMth_A1
        {"LEASE_CMPY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyFlg_A0
        {"XX_CNT_DPLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCntDplyFlg_A0
        {"XX_DS_MSG_ENTRY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMsgEntryTxt_A0
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A0
        {"ALT_PAYER_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//altPayerCustCd_A0
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A0
        {"SELL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A0
        {"XX_DTL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNm_A0
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A0
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A0
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A1
        {"XX_SMRY_LINE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_A0
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A0
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"DS_CONTR_MACH_LVL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrMachLvlNum_A0
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A0
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A0
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_A0
        {"DS_CONTR_CR_CARD_PO_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCrCardPoPk_A0
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A0
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A0
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A0
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A0
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A0
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

