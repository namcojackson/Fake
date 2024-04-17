//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230420094627000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7190_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7190;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7190 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7190_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** PRC_GRP_DTL_PK_A1*/
	public final EZDBBigDecimalItem              prcGrpDtlPk_A1;

    /** PRC_GRP_TRGT_TP_CD_A1*/
	public final EZDBStringItem              prcGrpTrgtTpCd_A1;

    /** PRC_GRP_TRGT_ATTRB_CD_P*/
	public final EZDBStringItemArray              prcGrpTrgtAttrbCd_P;

    /** PRC_GRP_TRGT_ATTRB_DESC_TXT_P*/
	public final EZDBStringItemArray              prcGrpTrgtAttrbDescTxt_P;

    /** PRC_GRP_TRGT_ATTRB_CD_A1*/
	public final EZDBStringItem              prcGrpTrgtAttrbCd_A1;

    /** PRC_GRP_OP_CD_P*/
	public final EZDBStringItemArray              prcGrpOpCd_P;

    /** PRC_GRP_OP_DESC_TXT_P*/
	public final EZDBStringItemArray              prcGrpOpDescTxt_P;

    /** PRC_GRP_OP_CD_A1*/
	public final EZDBStringItem              prcGrpOpCd_A1;

    /** PRC_GRP_FROM_TXT_A1*/
	public final EZDBStringItem              prcGrpFromTxt_A1;

    /** PRC_GRP_FROM_TXT_CD*/
	public final EZDBStringItem              prcGrpFromTxt_CD;

    /** DS_ACCT_NM_A1*/
	public final EZDBStringItem              dsAcctNm_A1;

    /** PRC_GRP_THRU_TXT_A1*/
	public final EZDBStringItem              prcGrpThruTxt_A1;

    /** PRC_GRP_THRU_TXT_CD*/
	public final EZDBStringItem              prcGrpThruTxt_CD;

    /** PRC_GRP_PRCD_NUM_A1*/
	public final EZDBBigDecimalItem              prcGrpPrcdNum_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDBDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDBDateItem              effThruDt_A1;

    /** PRC_GRP_OP_EQUAL_FLG_A1*/
	public final EZDBStringItem              prcGrpOpEqualFlg_A1;

    /** PRC_GRP_OP_NOT_EQUAL_FLG_A1*/
	public final EZDBStringItem              prcGrpOpNotEqualFlg_A1;

    /** PRC_GRP_OP_LIKE_FLG_A1*/
	public final EZDBStringItem              prcGrpOpLikeFlg_A1;

    /** PRC_GRP_OP_BTW_FLG_A1*/
	public final EZDBStringItem              prcGrpOpBtwFlg_A1;

    /** PRC_GRP_CONV_FLG_A1*/
	public final EZDBStringItem              prcGrpConvFlg_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL7190_ABMsg is constructor.
	 * The initialization when the instance of NMAL7190_ABMsg is generated.
	 */
	public NMAL7190_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7190_ABMsg is constructor.
	 * The initialization when the instance of NMAL7190_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7190_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		prcGrpDtlPk_A1 = (EZDBBigDecimalItem)newItem("prcGrpDtlPk_A1");
		prcGrpTrgtTpCd_A1 = (EZDBStringItem)newItem("prcGrpTrgtTpCd_A1");
		prcGrpTrgtAttrbCd_P = (EZDBStringItemArray)newItemArray("prcGrpTrgtAttrbCd_P");
		prcGrpTrgtAttrbDescTxt_P = (EZDBStringItemArray)newItemArray("prcGrpTrgtAttrbDescTxt_P");
		prcGrpTrgtAttrbCd_A1 = (EZDBStringItem)newItem("prcGrpTrgtAttrbCd_A1");
		prcGrpOpCd_P = (EZDBStringItemArray)newItemArray("prcGrpOpCd_P");
		prcGrpOpDescTxt_P = (EZDBStringItemArray)newItemArray("prcGrpOpDescTxt_P");
		prcGrpOpCd_A1 = (EZDBStringItem)newItem("prcGrpOpCd_A1");
		prcGrpFromTxt_A1 = (EZDBStringItem)newItem("prcGrpFromTxt_A1");
		prcGrpFromTxt_CD = (EZDBStringItem)newItem("prcGrpFromTxt_CD");
		dsAcctNm_A1 = (EZDBStringItem)newItem("dsAcctNm_A1");
		prcGrpThruTxt_A1 = (EZDBStringItem)newItem("prcGrpThruTxt_A1");
		prcGrpThruTxt_CD = (EZDBStringItem)newItem("prcGrpThruTxt_CD");
		prcGrpPrcdNum_A1 = (EZDBBigDecimalItem)newItem("prcGrpPrcdNum_A1");
		effFromDt_A1 = (EZDBDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDBDateItem)newItem("effThruDt_A1");
		prcGrpOpEqualFlg_A1 = (EZDBStringItem)newItem("prcGrpOpEqualFlg_A1");
		prcGrpOpNotEqualFlg_A1 = (EZDBStringItem)newItem("prcGrpOpNotEqualFlg_A1");
		prcGrpOpLikeFlg_A1 = (EZDBStringItem)newItem("prcGrpOpLikeFlg_A1");
		prcGrpOpBtwFlg_A1 = (EZDBStringItem)newItem("prcGrpOpBtwFlg_A1");
		prcGrpConvFlg_A1 = (EZDBStringItem)newItem("prcGrpConvFlg_A1");
		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7190_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7190_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcGrpDtlPk_A1", "prcGrpDtlPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcGrpTrgtTpCd_A1", "prcGrpTrgtTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcGrpTrgtAttrbCd_P", "prcGrpTrgtAttrbCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcGrpTrgtAttrbDescTxt_P", "prcGrpTrgtAttrbDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcGrpTrgtAttrbCd_A1", "prcGrpTrgtAttrbCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcGrpOpCd_P", "prcGrpOpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcGrpOpDescTxt_P", "prcGrpOpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcGrpOpCd_A1", "prcGrpOpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcGrpFromTxt_A1", "prcGrpFromTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpFromTxt_CD", "prcGrpFromTxt_CD", "CD", null, TYPE_HANKAKUEISU, "50", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"prcGrpThruTxt_A1", "prcGrpThruTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpThruTxt_CD", "prcGrpThruTxt_CD", "CD", null, TYPE_HANKAKUEISU, "50", null},
	{"prcGrpPrcdNum_A1", "prcGrpPrcdNum_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"prcGrpOpEqualFlg_A1", "prcGrpOpEqualFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"prcGrpOpNotEqualFlg_A1", "prcGrpOpNotEqualFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"prcGrpOpLikeFlg_A1", "prcGrpOpLikeFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"prcGrpOpBtwFlg_A1", "prcGrpOpBtwFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"prcGrpConvFlg_A1", "prcGrpConvFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"PRC_GRP_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpDtlPk_A1
        {"PRC_GRP_TRGT_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrgtTpCd_A1
        {"PRC_GRP_TRGT_ATTRB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrgtAttrbCd_P
        {"PRC_GRP_TRGT_ATTRB_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrgtAttrbDescTxt_P
        {"PRC_GRP_TRGT_ATTRB_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpTrgtAttrbCd_A1
        {"PRC_GRP_OP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpCd_P
        {"PRC_GRP_OP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpDescTxt_P
        {"PRC_GRP_OP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpCd_A1
        {"PRC_GRP_FROM_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpFromTxt_A1
        {"PRC_GRP_FROM_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpFromTxt_CD
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"PRC_GRP_THRU_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpThruTxt_A1
        {"PRC_GRP_THRU_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpThruTxt_CD
        {"PRC_GRP_PRCD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpPrcdNum_A1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A1
        {"PRC_GRP_OP_EQUAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpEqualFlg_A1
        {"PRC_GRP_OP_NOT_EQUAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpNotEqualFlg_A1
        {"PRC_GRP_OP_LIKE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpLikeFlg_A1
        {"PRC_GRP_OP_BTW_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpOpBtwFlg_A1
        {"PRC_GRP_CONV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpConvFlg_A1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

