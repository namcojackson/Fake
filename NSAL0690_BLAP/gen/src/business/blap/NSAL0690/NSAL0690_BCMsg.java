//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171027191114000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0690_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0690;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0690 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0690_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_B1*/
	public final EZDCBigDecimalItem              dsContrPk_B1;

    /** DS_CONTR_DTL_PK_B1*/
	public final EZDCBigDecimalItem              dsContrDtlPk_B1;

    /** DS_CONTR_CATG_CD_B1*/
	public final EZDCStringItem              dsContrCatgCd_B1;

    /** DS_CONTR_NUM_B1*/
	public final EZDCStringItem              dsContrNum_B1;

    /** DS_CONTR_SRC_TP_CD_B1*/
	public final EZDCStringItem              dsContrSrcTpCd_B1;

    /** CONTR_RNW_AVAL_FLG_BH*/
	public final EZDCStringItem              contrRnwAvalFlg_BH;

    /** CONTR_RNW_AVAL_FLG_BD*/
	public final EZDCStringItem              contrRnwAvalFlg_BD;

    /** XX_CHK_BOX_B1*/
	public final EZDCStringItem              xxChkBox_B1;

    /** MDSE_CD_B1*/
	public final EZDCStringItem              mdseCd_B1;

    /** SVC_MACH_MSTR_PK_B1*/
	public final EZDCBigDecimalItem              svcMachMstrPk_B1;

    /** SER_NUM_B1*/
	public final EZDCStringItem              serNum_B1;

    /** T_MDL_NM_B1*/
	public final EZDCStringItem              t_MdlNm_B1;

    /** XX_SCR_ITEM_8_TXT_B1*/
	public final EZDCStringItem              xxScrItem8Txt_B1;

    /** DS_CONTR_CTRL_STS_NM_B1*/
	public final EZDCStringItem              dsContrCtrlStsNm_B1;

    /** CONTR_EFF_FROM_DT_B1*/
	public final EZDCDateItem              contrEffFromDt_B1;

    /** XX_DISC_RATIO_B1*/
	public final EZDCBigDecimalItem              xxDiscRatio_B1;

    /** CONTR_EFF_THRU_DT_B1*/
	public final EZDCDateItem              contrEffThruDt_B1;

    /** XX_THRU_DT_B1*/
	public final EZDCDateItem              xxThruDt_B1;

    /** BASE_PRC_DEAL_AMT_B1*/
	public final EZDCBigDecimalItem              basePrcDealAmt_B1;

    /** NEW_BASE_DEAL_AMT_B1*/
	public final EZDCBigDecimalItem              newBaseDealAmt_B1;

    /** XX_GENL_FLD_AREA_TXT_B1*/
	public final EZDCStringItem              xxGenlFldAreaTxt_B1;

    /** DS_CONTR_DTL_TP_CD_B1*/
	public final EZDCStringItem              dsContrDtlTpCd_B1;

    /** RNW_PRC_METH_CD_B1*/
	public final EZDCStringItem              rnwPrcMethCd_B1;

    /** _EZUpdateDateTime_H*/
	public final EZDCStringItem              ezUpTime_H;

    /** _EZUpTimeZone_H*/
	public final EZDCStringItem              ezUpTimeZone_H;

    /** _EZUpdateDateTime_D*/
	public final EZDCStringItem              ezUpTime_D;

    /** _EZUpTimeZone_D*/
	public final EZDCStringItem              ezUpTimeZone_D;

    /** XX_REC_HIST_CRAT_TS_B*/
	public final EZDCStringItem              xxRecHistCratTs_B;

    /** XX_REC_HIST_CRAT_BY_NM_B*/
	public final EZDCStringItem              xxRecHistCratByNm_B;

    /** XX_REC_HIST_UPD_TS_B*/
	public final EZDCStringItem              xxRecHistUpdTs_B;

    /** XX_REC_HIST_UPD_BY_NM_B*/
	public final EZDCStringItem              xxRecHistUpdByNm_B;

    /** XX_REC_HIST_TBL_NM_B*/
	public final EZDCStringItem              xxRecHistTblNm_B;


	/**
	 * NSAL0690_BCMsg is constructor.
	 * The initialization when the instance of NSAL0690_BCMsg is generated.
	 */
	public NSAL0690_BCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0690_BCMsg is constructor.
	 * The initialization when the instance of NSAL0690_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0690_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_B1 = (EZDCBigDecimalItem)newItem("dsContrPk_B1");
		dsContrDtlPk_B1 = (EZDCBigDecimalItem)newItem("dsContrDtlPk_B1");
		dsContrCatgCd_B1 = (EZDCStringItem)newItem("dsContrCatgCd_B1");
		dsContrNum_B1 = (EZDCStringItem)newItem("dsContrNum_B1");
		dsContrSrcTpCd_B1 = (EZDCStringItem)newItem("dsContrSrcTpCd_B1");
		contrRnwAvalFlg_BH = (EZDCStringItem)newItem("contrRnwAvalFlg_BH");
		contrRnwAvalFlg_BD = (EZDCStringItem)newItem("contrRnwAvalFlg_BD");
		xxChkBox_B1 = (EZDCStringItem)newItem("xxChkBox_B1");
		mdseCd_B1 = (EZDCStringItem)newItem("mdseCd_B1");
		svcMachMstrPk_B1 = (EZDCBigDecimalItem)newItem("svcMachMstrPk_B1");
		serNum_B1 = (EZDCStringItem)newItem("serNum_B1");
		t_MdlNm_B1 = (EZDCStringItem)newItem("t_MdlNm_B1");
		xxScrItem8Txt_B1 = (EZDCStringItem)newItem("xxScrItem8Txt_B1");
		dsContrCtrlStsNm_B1 = (EZDCStringItem)newItem("dsContrCtrlStsNm_B1");
		contrEffFromDt_B1 = (EZDCDateItem)newItem("contrEffFromDt_B1");
		xxDiscRatio_B1 = (EZDCBigDecimalItem)newItem("xxDiscRatio_B1");
		contrEffThruDt_B1 = (EZDCDateItem)newItem("contrEffThruDt_B1");
		xxThruDt_B1 = (EZDCDateItem)newItem("xxThruDt_B1");
		basePrcDealAmt_B1 = (EZDCBigDecimalItem)newItem("basePrcDealAmt_B1");
		newBaseDealAmt_B1 = (EZDCBigDecimalItem)newItem("newBaseDealAmt_B1");
		xxGenlFldAreaTxt_B1 = (EZDCStringItem)newItem("xxGenlFldAreaTxt_B1");
		dsContrDtlTpCd_B1 = (EZDCStringItem)newItem("dsContrDtlTpCd_B1");
		rnwPrcMethCd_B1 = (EZDCStringItem)newItem("rnwPrcMethCd_B1");
		ezUpTime_H = (EZDCStringItem)newItem("ezUpTime_H");
		ezUpTimeZone_H = (EZDCStringItem)newItem("ezUpTimeZone_H");
		ezUpTime_D = (EZDCStringItem)newItem("ezUpTime_D");
		ezUpTimeZone_D = (EZDCStringItem)newItem("ezUpTimeZone_D");
		xxRecHistCratTs_B = (EZDCStringItem)newItem("xxRecHistCratTs_B");
		xxRecHistCratByNm_B = (EZDCStringItem)newItem("xxRecHistCratByNm_B");
		xxRecHistUpdTs_B = (EZDCStringItem)newItem("xxRecHistUpdTs_B");
		xxRecHistUpdByNm_B = (EZDCStringItem)newItem("xxRecHistUpdByNm_B");
		xxRecHistTblNm_B = (EZDCStringItem)newItem("xxRecHistTblNm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0690_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0690_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_B1", "dsContrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_B1", "dsContrDtlPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrCatgCd_B1", "dsContrCatgCd_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrNum_B1", "dsContrNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrSrcTpCd_B1", "dsContrSrcTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	{"contrRnwAvalFlg_BH", "contrRnwAvalFlg_BH", "BH", null, TYPE_HANKAKUEISU, "1", null},
	{"contrRnwAvalFlg_BD", "contrRnwAvalFlg_BD", "BD", null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"svcMachMstrPk_B1", "svcMachMstrPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_B1", "serNum_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm_B1", "t_MdlNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem8Txt_B1", "xxScrItem8Txt_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"dsContrCtrlStsNm_B1", "dsContrCtrlStsNm_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"contrEffFromDt_B1", "contrEffFromDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDiscRatio_B1", "xxDiscRatio_B1", "B1", null, TYPE_SEISU_SYOSU, "9", "2"},
	{"contrEffThruDt_B1", "contrEffThruDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxThruDt_B1", "xxThruDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"basePrcDealAmt_B1", "basePrcDealAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"newBaseDealAmt_B1", "newBaseDealAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxGenlFldAreaTxt_B1", "xxGenlFldAreaTxt_B1", "B1", null, TYPE_HANKAKUEISU, "1000", null},
	{"dsContrDtlTpCd_B1", "dsContrDtlTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "6", null},
	{"rnwPrcMethCd_B1", "rnwPrcMethCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"ezUpTime_H", "ezUpTime_H", "H", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H", "ezUpTimeZone_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_D", "ezUpTime_D", "D", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D", "ezUpTimeZone_D", "D", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_B", "xxRecHistCratTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_B", "xxRecHistCratByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_B", "xxRecHistUpdTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_B", "xxRecHistUpdByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_B", "xxRecHistTblNm_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_B1
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_B1
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_B1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_B1
        {"DS_CONTR_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSrcTpCd_B1
        {"CONTR_RNW_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrRnwAvalFlg_BH
        {"CONTR_RNW_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrRnwAvalFlg_BD
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_B1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B1
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_B1
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_B1
        {"DS_CONTR_CTRL_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsNm_B1
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt_B1
        {"XX_DISC_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDiscRatio_B1
        {"CONTR_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffThruDt_B1
        {"XX_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxThruDt_B1
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_B1
        {"NEW_BASE_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newBaseDealAmt_B1
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_B1
        {"DS_CONTR_DTL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlTpCd_B1
        {"RNW_PRC_METH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rnwPrcMethCd_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_B
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_B
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_B
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
