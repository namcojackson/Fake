//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170227165608000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0710_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0710;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0710 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0710_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_A1*/
	public final EZDCBigDecimalItem              dsContrPk_A1;

    /** DS_CONTR_DTL_PK_A1*/
	public final EZDCBigDecimalItem              dsContrDtlPk_A1;

    /** DS_CONTR_CTRL_STS_CD_AH*/
	public final EZDCStringItem              dsContrCtrlStsCd_AH;

    /** DS_CONTR_CTRL_STS_CD_AD*/
	public final EZDCStringItem              dsContrCtrlStsCd_AD;

    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** DS_CONTR_CATG_CD_A1*/
	public final EZDCStringItem              dsContrCatgCd_A1;

    /** DS_CONTR_NUM_A1*/
	public final EZDCStringItem              dsContrNum_A1;

    /** XX_SCR_ITEM_34_TXT_A1*/
	public final EZDCStringItem              xxScrItem34Txt_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** CONTR_VRSN_EFF_FROM_DT_A1*/
	public final EZDCDateItem              contrVrsnEffFromDt_A1;

    /** CONTR_VRSN_EFF_THRU_DT_A1*/
	public final EZDCDateItem              contrVrsnEffThruDt_A1;

    /** DS_CONTR_CTRL_STS_NM_A1*/
	public final EZDCStringItem              dsContrCtrlStsNm_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDCStringItem              xxChkBox_A2;

    /** SER_NUM_A1*/
	public final EZDCStringItem              serNum_A1;

    /** T_MDL_NM_A1*/
	public final EZDCStringItem              t_MdlNm_A1;

    /** XX_DPLY_CTRL_FLG_A1*/
	public final EZDCStringItem              xxDplyCtrlFlg_A1;

    /** XX_DPLY_CTRL_FLG_A2*/
	public final EZDCStringItem              xxDplyCtrlFlg_A2;

    /** XX_DPLY_CTRL_FLG_A3*/
	public final EZDCStringItem              xxDplyCtrlFlg_A3;

    /** XX_DPLY_CTRL_FLG_A4*/
	public final EZDCStringItem              xxDplyCtrlFlg_A4;

    /** XX_DPLY_CTRL_FLG_A5*/
	public final EZDCStringItem              xxDplyCtrlFlg_A5;

    /** MTR_READ_METH_CD_A1*/
	public final EZDCStringItem              mtrReadMethCd_A1;

    /** MTR_READ_METH_NM_A1*/
	public final EZDCStringItem              mtrReadMethNm_A1;

    /** MTR_READ_METH_CD_A2*/
	public final EZDCStringItem              mtrReadMethCd_A2;

    /** MTR_READ_METH_NM_A2*/
	public final EZDCStringItem              mtrReadMethNm_A2;

    /** XX_GENL_FLD_AREA_TXT_A1*/
	public final EZDCStringItem              xxGenlFldAreaTxt_A1;

    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** _EZUpdateDateTime_D1*/
	public final EZDCStringItem              ezUpTime_D1;

    /** _EZUpTimeZone_D1*/
	public final EZDCStringItem              ezUpTimeZone_D1;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDCStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDCStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDCStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDCStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDCStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0710_ACMsg is constructor.
	 * The initialization when the instance of NSAL0710_ACMsg is generated.
	 */
	public NSAL0710_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0710_ACMsg is constructor.
	 * The initialization when the instance of NSAL0710_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0710_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_A1 = (EZDCBigDecimalItem)newItem("dsContrPk_A1");
		dsContrDtlPk_A1 = (EZDCBigDecimalItem)newItem("dsContrDtlPk_A1");
		dsContrCtrlStsCd_AH = (EZDCStringItem)newItem("dsContrCtrlStsCd_AH");
		dsContrCtrlStsCd_AD = (EZDCStringItem)newItem("dsContrCtrlStsCd_AD");
		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		dsContrCatgCd_A1 = (EZDCStringItem)newItem("dsContrCatgCd_A1");
		dsContrNum_A1 = (EZDCStringItem)newItem("dsContrNum_A1");
		xxScrItem34Txt_A1 = (EZDCStringItem)newItem("xxScrItem34Txt_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		contrVrsnEffFromDt_A1 = (EZDCDateItem)newItem("contrVrsnEffFromDt_A1");
		contrVrsnEffThruDt_A1 = (EZDCDateItem)newItem("contrVrsnEffThruDt_A1");
		dsContrCtrlStsNm_A1 = (EZDCStringItem)newItem("dsContrCtrlStsNm_A1");
		xxChkBox_A2 = (EZDCStringItem)newItem("xxChkBox_A2");
		serNum_A1 = (EZDCStringItem)newItem("serNum_A1");
		t_MdlNm_A1 = (EZDCStringItem)newItem("t_MdlNm_A1");
		xxDplyCtrlFlg_A1 = (EZDCStringItem)newItem("xxDplyCtrlFlg_A1");
		xxDplyCtrlFlg_A2 = (EZDCStringItem)newItem("xxDplyCtrlFlg_A2");
		xxDplyCtrlFlg_A3 = (EZDCStringItem)newItem("xxDplyCtrlFlg_A3");
		xxDplyCtrlFlg_A4 = (EZDCStringItem)newItem("xxDplyCtrlFlg_A4");
		xxDplyCtrlFlg_A5 = (EZDCStringItem)newItem("xxDplyCtrlFlg_A5");
		mtrReadMethCd_A1 = (EZDCStringItem)newItem("mtrReadMethCd_A1");
		mtrReadMethNm_A1 = (EZDCStringItem)newItem("mtrReadMethNm_A1");
		mtrReadMethCd_A2 = (EZDCStringItem)newItem("mtrReadMethCd_A2");
		mtrReadMethNm_A2 = (EZDCStringItem)newItem("mtrReadMethNm_A2");
		xxGenlFldAreaTxt_A1 = (EZDCStringItem)newItem("xxGenlFldAreaTxt_A1");
		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		ezUpTime_D1 = (EZDCStringItem)newItem("ezUpTime_D1");
		ezUpTimeZone_D1 = (EZDCStringItem)newItem("ezUpTimeZone_D1");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0710_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0710_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_A1", "dsContrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_A1", "dsContrDtlPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrCtrlStsCd_AH", "dsContrCtrlStsCd_AH", "AH", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrCtrlStsCd_AD", "dsContrCtrlStsCd_AD", "AD", null, TYPE_HANKAKUEISU, "4", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrCatgCd_A1", "dsContrCatgCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem34Txt_A1", "xxScrItem34Txt_A1", "A1", null, TYPE_HANKAKUEISU, "34", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"contrVrsnEffFromDt_A1", "contrVrsnEffFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrVrsnEffThruDt_A1", "contrVrsnEffThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrCtrlStsNm_A1", "dsContrCtrlStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm_A1", "t_MdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyCtrlFlg_A1", "xxDplyCtrlFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A2", "xxDplyCtrlFlg_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A3", "xxDplyCtrlFlg_A3", "A3", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A4", "xxDplyCtrlFlg_A4", "A4", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A5", "xxDplyCtrlFlg_A5", "A5", null, TYPE_HANKAKUEISU, "1", null},
	{"mtrReadMethCd_A1", "mtrReadMethCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethNm_A1", "mtrReadMethNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mtrReadMethCd_A2", "mtrReadMethCd_A2", "A2", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrReadMethNm_A2", "mtrReadMethNm_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"xxGenlFldAreaTxt_A1", "xxGenlFldAreaTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezUpTime_D1", "ezUpTime_D1", "D1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D1", "ezUpTimeZone_D1", "D1", null, TYPE_HANKAKUEISU, "5", null},
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

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_A1
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A1
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_AH
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_AD
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_A1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"XX_SCR_ITEM_34_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem34Txt_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"CONTR_VRSN_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffFromDt_A1
        {"CONTR_VRSN_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrVrsnEffThruDt_A1
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A1
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A2
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A3
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A4
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A5
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_A1
        {"MTR_READ_METH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethNm_A1
        {"MTR_READ_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethCd_A2
        {"MTR_READ_METH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadMethNm_A2
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_A1
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D1
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

