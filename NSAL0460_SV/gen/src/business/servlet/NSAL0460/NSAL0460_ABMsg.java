//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240216091639000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0460_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0460;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0460 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0460_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM*/
	public final EZDBStringItem              dsContrNum;

    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** SER_NUM*/
	public final EZDBStringItem              serNum;

    /** CONTR_EFF_FROM_DT*/
	public final EZDBDateItem              contrEffFromDt;

    /** CONTR_EFF_FROM_DT_P*/
	public final EZDBDateItem              contrEffFromDt_P;

    /** MDL_MTR_LB_CD*/
	public final EZDBStringItem              mdlMtrLbCd;

    /** MTR_LB_DESC_TXT*/
	public final EZDBStringItem              mtrLbDescTxt;

    /** MTR_READ_DT*/
	public final EZDBDateItem              mtrReadDt;

    /** READ_MTR_CNT*/
	public final EZDBBigDecimalItem              readMtrCnt;

    /** SVC_CMNT_TXT_A*/
	public final EZDBStringItem              svcCmntTxt_A;

    /** DS_CONTR_PK*/
	public final EZDBBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDBBigDecimalItem              dsContrDtlPk;

    /** SVC_MACH_MSTR_PK*/
	public final EZDBBigDecimalItem              svcMachMstrPk;

    /** SVC_PHYS_MTR_PK*/
	public final EZDBBigDecimalItem              svcPhysMtrPk;

    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** XX_ROW_NUM_C*/
	public final EZDBBigDecimalItem              xxRowNum_C;

    /** XX_ROW_NUM_D*/
	public final EZDBBigDecimalItem              xxRowNum_D;

    /** ACTV_FLG*/
	public final EZDBStringItem              actvFlg;

    /** XX_READ_ONLY_FLG*/
	public final EZDBStringItem              xxReadOnlyFlg;

    /** DS_MTR_READ_TP_GRP_CD*/
	public final EZDBStringItem              dsMtrReadTpGrpCd;

    /** DS_MTR_READ_TP_CD*/
	public final EZDBStringItem              dsMtrReadTpCd;

    /** XX_WRN_SKIP_FLG*/
	public final EZDBStringItem              xxWrnSkipFlg;

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


	/**
	 * NSAL0460_ABMsg is constructor.
	 * The initialization when the instance of NSAL0460_ABMsg is generated.
	 */
	public NSAL0460_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0460_ABMsg is constructor.
	 * The initialization when the instance of NSAL0460_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0460_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum = (EZDBStringItem)newItem("dsContrNum");
		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		serNum = (EZDBStringItem)newItem("serNum");
		contrEffFromDt = (EZDBDateItem)newItem("contrEffFromDt");
		contrEffFromDt_P = (EZDBDateItem)newItem("contrEffFromDt_P");
		mdlMtrLbCd = (EZDBStringItem)newItem("mdlMtrLbCd");
		mtrLbDescTxt = (EZDBStringItem)newItem("mtrLbDescTxt");
		mtrReadDt = (EZDBDateItem)newItem("mtrReadDt");
		readMtrCnt = (EZDBBigDecimalItem)newItem("readMtrCnt");
		svcCmntTxt_A = (EZDBStringItem)newItem("svcCmntTxt_A");
		dsContrPk = (EZDBBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDBBigDecimalItem)newItem("dsContrDtlPk");
		svcMachMstrPk = (EZDBBigDecimalItem)newItem("svcMachMstrPk");
		svcPhysMtrPk = (EZDBBigDecimalItem)newItem("svcPhysMtrPk");
		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		xxRowNum_C = (EZDBBigDecimalItem)newItem("xxRowNum_C");
		xxRowNum_D = (EZDBBigDecimalItem)newItem("xxRowNum_D");
		actvFlg = (EZDBStringItem)newItem("actvFlg");
		xxReadOnlyFlg = (EZDBStringItem)newItem("xxReadOnlyFlg");
		dsMtrReadTpGrpCd = (EZDBStringItem)newItem("dsMtrReadTpGrpCd");
		dsMtrReadTpCd = (EZDBStringItem)newItem("dsMtrReadTpCd");
		xxWrnSkipFlg = (EZDBStringItem)newItem("xxWrnSkipFlg");
		xxRecHistCratTs = (EZDBStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDBStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDBStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDBStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDBStringItem)newItem("xxRecHistTblNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0460_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0460_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"contrEffFromDt", "contrEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffFromDt_P", "contrEffFromDt_P", "P", null, TYPE_NENTSUKIHI, "8", null},
	{"mdlMtrLbCd", "mdlMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt", "mtrLbDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mtrReadDt", "mtrReadDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"readMtrCnt", "readMtrCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"svcCmntTxt_A", "svcCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "4000", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrPk", "svcPhysMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRowNum_C", "xxRowNum_C", "C", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRowNum_D", "xxRowNum_D", "D", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxReadOnlyFlg", "xxReadOnlyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsMtrReadTpGrpCd", "dsMtrReadTpGrpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"dsMtrReadTpCd", "dsMtrReadTpCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CONTR_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffFromDt
        {"CONTR_EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//contrEffFromDt_P
        {"MDL_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlMtrLbCd
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt
        {"MTR_READ_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mtrReadDt
        {"READ_MTR_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt
        {"SVC_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_A
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SVC_PHYS_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_C
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_D
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"XX_READ_ONLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxReadOnlyFlg
        {"DS_MTR_READ_TP_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpGrpCd
        {"DS_MTR_READ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpCd
        {"XX_WRN_SKIP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
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
