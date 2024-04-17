//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161102110823000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7230_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7230;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7230 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7230_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FRT_ZONE_PK_A1*/
	public final EZDSBigDecimalItem              frtZonePk_A1;

    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** LINE_BIZ_TP_CD_A1*/
	public final EZDSStringItem              lineBizTpCd_A1;

    /** LINE_BIZ_TP_DESC_TXT_A1*/
	public final EZDSStringItem              lineBizTpDescTxt_A1;

    /** DS_ACCT_NUM_A1*/
	public final EZDSStringItem              dsAcctNum_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDSStringItem              dsAcctNm_A1;

    /** PRC_GRP_PK_A1*/
	public final EZDSBigDecimalItem              prcGrpPk_A1;

    /** PRC_GRP_NM_A1*/
	public final EZDSStringItem              prcGrpNm_A1;

    /** FRT_ZONE_NUM_A1*/
	public final EZDSBigDecimalItem              frtZoneNum_A1;

    /** SHIP_TO_ST_CD_A1*/
	public final EZDSStringItem              shipToStCd_A1;

    /** SHIP_TO_CTRY_CD_A1*/
	public final EZDSStringItem              shipToCtryCd_A1;

    /** SHIP_TO_FROM_POST_CD_A1*/
	public final EZDSStringItem              shipToFromPostCd_A1;

    /** SHIP_TO_THRU_POST_CD_A1*/
	public final EZDSStringItem              shipToThruPostCd_A1;

    /** EFF_FROM_DT_A1*/
	public final EZDSDateItem              effFromDt_A1;

    /** EFF_THRU_DT_A1*/
	public final EZDSDateItem              effThruDt_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDSStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDSStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDSStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDSStringItem              xxRecHistTblNm_A1;


	/**
	 * NMAL7230_ASMsg is constructor.
	 * The initialization when the instance of NMAL7230_ASMsg is generated.
	 */
	public NMAL7230_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7230_ASMsg is constructor.
	 * The initialization when the instance of NMAL7230_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7230_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		frtZonePk_A1 = (EZDSBigDecimalItem)newItem("frtZonePk_A1");
		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		lineBizTpCd_A1 = (EZDSStringItem)newItem("lineBizTpCd_A1");
		lineBizTpDescTxt_A1 = (EZDSStringItem)newItem("lineBizTpDescTxt_A1");
		dsAcctNum_A1 = (EZDSStringItem)newItem("dsAcctNum_A1");
		dsAcctNm_A1 = (EZDSStringItem)newItem("dsAcctNm_A1");
		prcGrpPk_A1 = (EZDSBigDecimalItem)newItem("prcGrpPk_A1");
		prcGrpNm_A1 = (EZDSStringItem)newItem("prcGrpNm_A1");
		frtZoneNum_A1 = (EZDSBigDecimalItem)newItem("frtZoneNum_A1");
		shipToStCd_A1 = (EZDSStringItem)newItem("shipToStCd_A1");
		shipToCtryCd_A1 = (EZDSStringItem)newItem("shipToCtryCd_A1");
		shipToFromPostCd_A1 = (EZDSStringItem)newItem("shipToFromPostCd_A1");
		shipToThruPostCd_A1 = (EZDSStringItem)newItem("shipToThruPostCd_A1");
		effFromDt_A1 = (EZDSDateItem)newItem("effFromDt_A1");
		effThruDt_A1 = (EZDSDateItem)newItem("effThruDt_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
		xxRecHistCratTs_A1 = (EZDSStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDSStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDSStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDSStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7230_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7230_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"frtZonePk_A1", "frtZonePk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"lineBizTpCd_A1", "lineBizTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_A1", "lineBizTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsAcctNum_A1", "dsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"prcGrpPk_A1", "prcGrpPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcGrpNm_A1", "prcGrpNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"frtZoneNum_A1", "frtZoneNum_A1", "A1", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"shipToStCd_A1", "shipToStCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"shipToCtryCd_A1", "shipToCtryCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"shipToFromPostCd_A1", "shipToFromPostCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"shipToThruPostCd_A1", "shipToThruPostCd_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"effFromDt_A1", "effFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A1", "effThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"FRT_ZONE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtZonePk_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A1
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_A1
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"PRC_GRP_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpPk_A1
        {"PRC_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcGrpNm_A1
        {"FRT_ZONE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtZoneNum_A1
        {"SHIP_TO_ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd_A1
        {"SHIP_TO_CTRY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtryCd_A1
        {"SHIP_TO_FROM_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToFromPostCd_A1
        {"SHIP_TO_THRU_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToThruPostCd_A1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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

