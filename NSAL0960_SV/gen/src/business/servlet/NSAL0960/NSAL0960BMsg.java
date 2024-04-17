//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161130084108000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0960BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0960;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0960 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0960BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** X*/
	public final business.servlet.NSAL0960.NSAL0960_XBMsgArray              X;

    /** GLBL_CMPY_CD_H*/
	public final EZDBStringItem              glblCmpyCd_H;

    /** SLS_DT_H*/
	public final EZDBDateItem              slsDt_H;

    /** XX_FILE_DATA_H*/
	public final EZDBMimeSourceItem              xxFileData_H;

    /** XX_POP_PRM_SE*/
	public final EZDBStringItem              xxPopPrm_SE;

    /** DS_CONTR_VLD_LIST_PK_H*/
	public final EZDBBigDecimalItem              dsContrVldListPk_H;

    /** DS_CONTR_VLD_LIST_NM_H*/
	public final EZDBStringItem              dsContrVldListNm_H;

    /** DS_CONTR_VLD_LIST_DESC_TXT_H*/
	public final EZDBStringItem              dsContrVldListDescTxt_H;

    /** EFF_FROM_DT_H*/
	public final EZDBDateItem              effFromDt_H;

    /** EFF_TO_DT_H*/
	public final EZDBDateItem              effToDt_H;

    /** VLD_ACT_CD_HC*/
	public final EZDBStringItemArray              vldActCd_HC;

    /** VLD_ACT_DESC_TXT_HC*/
	public final EZDBStringItemArray              vldActDescTxt_HC;

    /** _EZUpTimeZone_H*/
	public final EZDBStringItem              ezUpTimeZone_H;

    /** _EZUpdateDateTime_H*/
	public final EZDBStringItem              ezUpTime_H;

    /** XX_RADIO_BTN_A*/
	public final EZDBBigDecimalItem              xxRadioBtn_A;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_REC_HIST_CRAT_TS*/
	public final EZDBStringItem              xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM*/
	public final EZDBStringItem              xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS*/
	public final EZDBStringItem              xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM*/
	public final EZDBStringItem              xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM*/
	public final EZDBStringItem              xxRecHistTblNm;

    /** A*/
	public final business.servlet.NSAL0960.NSAL0960_ABMsgArray              A;


	/**
	 * NSAL0960BMsg is constructor.
	 * The initialization when the instance of NSAL0960BMsg is generated.
	 */
	public NSAL0960BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0960BMsg is constructor.
	 * The initialization when the instance of NSAL0960BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0960BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		X = (business.servlet.NSAL0960.NSAL0960_XBMsgArray)newMsgArray("X");
		glblCmpyCd_H = (EZDBStringItem)newItem("glblCmpyCd_H");
		slsDt_H = (EZDBDateItem)newItem("slsDt_H");
		xxFileData_H = (EZDBMimeSourceItem)newItem("xxFileData_H");
		xxPopPrm_SE = (EZDBStringItem)newItem("xxPopPrm_SE");
		dsContrVldListPk_H = (EZDBBigDecimalItem)newItem("dsContrVldListPk_H");
		dsContrVldListNm_H = (EZDBStringItem)newItem("dsContrVldListNm_H");
		dsContrVldListDescTxt_H = (EZDBStringItem)newItem("dsContrVldListDescTxt_H");
		effFromDt_H = (EZDBDateItem)newItem("effFromDt_H");
		effToDt_H = (EZDBDateItem)newItem("effToDt_H");
		vldActCd_HC = (EZDBStringItemArray)newItemArray("vldActCd_HC");
		vldActDescTxt_HC = (EZDBStringItemArray)newItemArray("vldActDescTxt_HC");
		ezUpTimeZone_H = (EZDBStringItem)newItem("ezUpTimeZone_H");
		ezUpTime_H = (EZDBStringItem)newItem("ezUpTime_H");
		xxRadioBtn_A = (EZDBBigDecimalItem)newItem("xxRadioBtn_A");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxRecHistCratTs = (EZDBStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDBStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDBStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDBStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDBStringItem)newItem("xxRecHistTblNm");
		A = (business.servlet.NSAL0960.NSAL0960_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0960BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0960BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"X", "X", null, "20", "business.servlet.NSAL0960.NSAL0960_XBMsgArray", null, null},
	
	{"glblCmpyCd_H", "glblCmpyCd_H", "H", null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt_H", "slsDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFileData_H", "xxFileData_H", "H", null, TYPE_UPLOAD, null, null},
	{"xxPopPrm_SE", "xxPopPrm_SE", "SE", null, TYPE_HANKAKUEISU, "300", null},
	{"dsContrVldListPk_H", "dsContrVldListPk_H", "H", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrVldListNm_H", "dsContrVldListNm_H", "H", null, TYPE_HANKAKUEISU, "60", null},
	{"dsContrVldListDescTxt_H", "dsContrVldListDescTxt_H", "H", null, TYPE_HANKAKUEISU, "120", null},
	{"effFromDt_H", "effFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt_H", "effToDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"vldActCd_HC", "vldActCd_HC", "HC", "99", TYPE_HANKAKUEISU, "2", null},
	{"vldActDescTxt_HC", "vldActDescTxt_HC", "HC", "99", TYPE_HANKAKUEISU, "50", null},
	{"ezUpTimeZone_H", "ezUpTimeZone_H", "H", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_H", "ezUpTime_H", "H", null, TYPE_HANKAKUEISU, "17", null},
	{"xxRadioBtn_A", "xxRadioBtn_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"A", "A", null, "200", "business.servlet.NSAL0960.NSAL0960_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//X
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_H
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//slsDt_H
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_H
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_SE
        {"DS_CONTR_VLD_LIST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldListPk_H
        {"DS_CONTR_VLD_LIST_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldListNm_H
        {"DS_CONTR_VLD_LIST_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrVldListDescTxt_H
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H
        {"EFF_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effToDt_H
        {"VLD_ACT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldActCd_HC
        {"VLD_ACT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldActDescTxt_HC
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
		null,	//A
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
