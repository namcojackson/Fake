//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220613140304000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0150CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0150CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** P*/
	public final business.blap.NMAL0150.NMAL0150_PCMsgArray              P;

    /** XX_PAGE_SHOW_FROM_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_10;

    /** XX_PAGE_SHOW_FROM_NUM_BK*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_BK;

    /** XX_PAGE_SHOW_TO_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowToNum_10;

    /** XX_PAGE_SHOW_OF_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_10;

    /** XX_PAGE_SHOW_CUR_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_10;

    /** XX_PAGE_SHOW_TOT_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_10;

    /** XX_SORT_TBL_NM_01*/
	public final EZDCStringItem              xxSortTblNm_01;

    /** XX_SORT_ITEM_NM_01*/
	public final EZDCStringItem              xxSortItemNm_01;

    /** XX_SORT_ORD_BY_TXT_01*/
	public final EZDCStringItem              xxSortOrdByTxt_01;

    /** MDSE_CST_UPD_TP_CD_H1*/
	public final EZDCStringItem              mdseCstUpdTpCd_H1;

    /** MDSE_CST_UPD_TP_CD_L1*/
	public final EZDCStringItemArray              mdseCstUpdTpCd_L1;

    /** MDSE_CST_UPD_TP_NM_L1*/
	public final EZDCStringItemArray              mdseCstUpdTpNm_L1;

    /** MDSE_CST_UPD_GRP_TXT_H1*/
	public final EZDCStringItem              mdseCstUpdGrpTxt_H1;

    /** MDSE_CST_UPD_DESC_TXT_H1*/
	public final EZDCStringItem              mdseCstUpdDescTxt_H1;

    /** MDSE_CST_UPD_REF_TXT_H1*/
	public final EZDCStringItem              mdseCstUpdRefTxt_H1;

    /** MDSE_CST_UPD_REF_ID_H1*/
	public final EZDCStringItem              mdseCstUpdRefId_H1;

    /** MDSE_CST_UPD_CRAT_PSN_CD_H1*/
	public final EZDCStringItem              mdseCstUpdCratPsnCd_H1;

    /** XX_FULL_NM_H1*/
	public final EZDCStringItem              xxFullNm_H1;

    /** MDSE_CST_UPD_CRAT_DT_H1*/
	public final EZDCDateItem              mdseCstUpdCratDt_H1;

    /** MDSE_CST_UPD_HIST_HDR_PK_H1*/
	public final EZDCBigDecimalItem              mdseCstUpdHistHdrPk_H1;

    /** _EZUpdateDateTime_H1*/
	public final EZDCStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDCStringItem              ezUpTimeZone_H1;

    /** XX_CHK_BOX_AL*/
	public final EZDCStringItem              xxChkBox_AL;

    /** XX_FILE_DATA_H1*/
	public final EZDCMimeSourceItem              xxFileData_H1;

    /** XX_REC_HIST_CRAT_TS*/
	public final EZDCStringItem              xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM*/
	public final EZDCStringItem              xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS*/
	public final EZDCStringItem              xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM*/
	public final EZDCStringItem              xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM*/
	public final EZDCStringItem              xxRecHistTblNm;

    /** A*/
	public final business.blap.NMAL0150.NMAL0150_ACMsgArray              A;

    /** B*/
	public final business.blap.NMAL0150.NMAL0150_BCMsgArray              B;


	/**
	 * NMAL0150CMsg is constructor.
	 * The initialization when the instance of NMAL0150CMsg is generated.
	 */
	public NMAL0150CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0150CMsg is constructor.
	 * The initialization when the instance of NMAL0150CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0150CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		P = (business.blap.NMAL0150.NMAL0150_PCMsgArray)newMsgArray("P");
		xxPageShowFromNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_10");
		xxPageShowFromNum_BK = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_BK");
		xxPageShowToNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_10");
		xxPageShowOfNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_10");
		xxPageShowCurNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_10");
		xxPageShowTotNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_10");
		xxSortTblNm_01 = (EZDCStringItem)newItem("xxSortTblNm_01");
		xxSortItemNm_01 = (EZDCStringItem)newItem("xxSortItemNm_01");
		xxSortOrdByTxt_01 = (EZDCStringItem)newItem("xxSortOrdByTxt_01");
		mdseCstUpdTpCd_H1 = (EZDCStringItem)newItem("mdseCstUpdTpCd_H1");
		mdseCstUpdTpCd_L1 = (EZDCStringItemArray)newItemArray("mdseCstUpdTpCd_L1");
		mdseCstUpdTpNm_L1 = (EZDCStringItemArray)newItemArray("mdseCstUpdTpNm_L1");
		mdseCstUpdGrpTxt_H1 = (EZDCStringItem)newItem("mdseCstUpdGrpTxt_H1");
		mdseCstUpdDescTxt_H1 = (EZDCStringItem)newItem("mdseCstUpdDescTxt_H1");
		mdseCstUpdRefTxt_H1 = (EZDCStringItem)newItem("mdseCstUpdRefTxt_H1");
		mdseCstUpdRefId_H1 = (EZDCStringItem)newItem("mdseCstUpdRefId_H1");
		mdseCstUpdCratPsnCd_H1 = (EZDCStringItem)newItem("mdseCstUpdCratPsnCd_H1");
		xxFullNm_H1 = (EZDCStringItem)newItem("xxFullNm_H1");
		mdseCstUpdCratDt_H1 = (EZDCDateItem)newItem("mdseCstUpdCratDt_H1");
		mdseCstUpdHistHdrPk_H1 = (EZDCBigDecimalItem)newItem("mdseCstUpdHistHdrPk_H1");
		ezUpTime_H1 = (EZDCStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDCStringItem)newItem("ezUpTimeZone_H1");
		xxChkBox_AL = (EZDCStringItem)newItem("xxChkBox_AL");
		xxFileData_H1 = (EZDCMimeSourceItem)newItem("xxFileData_H1");
		xxRecHistCratTs = (EZDCStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDCStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDCStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDCStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDCStringItem)newItem("xxRecHistTblNm");
		A = (business.blap.NMAL0150.NMAL0150_ACMsgArray)newMsgArray("A");
		B = (business.blap.NMAL0150.NMAL0150_BCMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0150CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0150CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"P", "P", null, "30", "business.blap.NMAL0150.NMAL0150_PCMsgArray", null, null},
	
	{"xxPageShowFromNum_10", "xxPageShowFromNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum_BK", "xxPageShowFromNum_BK", "BK", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_10", "xxPageShowToNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_10", "xxPageShowOfNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_10", "xxPageShowCurNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_10", "xxPageShowTotNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_01", "xxSortTblNm_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_01", "xxSortItemNm_01", "01", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_01", "xxSortOrdByTxt_01", "01", null, TYPE_HANKAKUEISU, "4", null},
	{"mdseCstUpdTpCd_H1", "mdseCstUpdTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCstUpdTpCd_L1", "mdseCstUpdTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseCstUpdTpNm_L1", "mdseCstUpdTpNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"mdseCstUpdGrpTxt_H1", "mdseCstUpdGrpTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCstUpdDescTxt_H1", "mdseCstUpdDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCstUpdRefTxt_H1", "mdseCstUpdRefTxt_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCstUpdRefId_H1", "mdseCstUpdRefId_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCstUpdCratPsnCd_H1", "mdseCstUpdCratPsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxFullNm_H1", "xxFullNm_H1", "H1", null, TYPE_HANKAKUEISU, "100", null},
	{"mdseCstUpdCratDt_H1", "mdseCstUpdCratDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseCstUpdHistHdrPk_H1", "mdseCstUpdHistHdrPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxChkBox_AL", "xxChkBox_AL", "AL", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxFileData_H1", "xxFileData_H1", "H1", null, TYPE_UPLOAD, null, null},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"A", "A", null, "200", "business.blap.NMAL0150.NMAL0150_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NMAL0150.NMAL0150_BCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_10
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_BK
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_10
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_10
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_10
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_10
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_01
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_01
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_01
        {"MDSE_CST_UPD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpCd_H1
        {"MDSE_CST_UPD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpCd_L1
        {"MDSE_CST_UPD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdTpNm_L1
        {"MDSE_CST_UPD_GRP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdGrpTxt_H1
        {"MDSE_CST_UPD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdDescTxt_H1
        {"MDSE_CST_UPD_REF_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdRefTxt_H1
        {"MDSE_CST_UPD_REF_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdRefId_H1
        {"MDSE_CST_UPD_CRAT_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdCratPsnCd_H1
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_H1
        {"MDSE_CST_UPD_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdCratDt_H1
        {"MDSE_CST_UPD_HIST_HDR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCstUpdHistHdrPk_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AL
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_H1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
		null,	//A
		null,	//B
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
