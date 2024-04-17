//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170601124214000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3050_LBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL3050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3050_LBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRX_HDR_NUM_TP_CD_L1*/
	public final EZDBStringItem              trxHdrNumTpCd_L1;

    /** TRX_HDR_NUM_L1*/
	public final EZDBStringItem              trxHdrNum_L1;

    /** XX_ORD_TS_L1*/
	public final EZDBStringItem              xxOrdTs_L1;

    /** DELY_COORD_STS_DESC_TXT_L1*/
	public final EZDBStringItem              delyCoordStsDescTxt_L1;

    /** SHIP_TO_ACCT_NM_L1*/
	public final EZDBStringItem              shipToAcctNm_L1;

    /** SHIP_TO_ADDR_L1*/
	public final EZDBStringItem              shipToAddr_L1;

    /** SHIP_TO_CTY_ADDR_L1*/
	public final EZDBStringItem              shipToCtyAddr_L1;

    /** SHIP_TO_ST_CD_L1*/
	public final EZDBStringItem              shipToStCd_L1;

    /** XX_TOT_CNT_LO*/
	public final EZDBBigDecimalItem              xxTotCnt_LO;

    /** XX_TOT_CNT_LI*/
	public final EZDBBigDecimalItem              xxTotCnt_LI;

    /** XX_TBL_SORT_COL_NM_LS*/
	public final EZDBStringItem              xxTblSortColNm_LS;

    /** CUT_OFF_AOT_LS*/
	public final EZDBBigDecimalItem              cutOffAot_LS;


	/**
	 * NLBL3050_LBMsg is constructor.
	 * The initialization when the instance of NLBL3050_LBMsg is generated.
	 */
	public NLBL3050_LBMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3050_LBMsg is constructor.
	 * The initialization when the instance of NLBL3050_LBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3050_LBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trxHdrNumTpCd_L1 = (EZDBStringItem)newItem("trxHdrNumTpCd_L1");
		trxHdrNum_L1 = (EZDBStringItem)newItem("trxHdrNum_L1");
		xxOrdTs_L1 = (EZDBStringItem)newItem("xxOrdTs_L1");
		delyCoordStsDescTxt_L1 = (EZDBStringItem)newItem("delyCoordStsDescTxt_L1");
		shipToAcctNm_L1 = (EZDBStringItem)newItem("shipToAcctNm_L1");
		shipToAddr_L1 = (EZDBStringItem)newItem("shipToAddr_L1");
		shipToCtyAddr_L1 = (EZDBStringItem)newItem("shipToCtyAddr_L1");
		shipToStCd_L1 = (EZDBStringItem)newItem("shipToStCd_L1");
		xxTotCnt_LO = (EZDBBigDecimalItem)newItem("xxTotCnt_LO");
		xxTotCnt_LI = (EZDBBigDecimalItem)newItem("xxTotCnt_LI");
		xxTblSortColNm_LS = (EZDBStringItem)newItem("xxTblSortColNm_LS");
		cutOffAot_LS = (EZDBBigDecimalItem)newItem("cutOffAot_LS");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3050_LBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3050_LBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trxHdrNumTpCd_L1", "trxHdrNumTpCd_L1", "L1", null, TYPE_HANKAKUEISU, "2", null},
	{"trxHdrNum_L1", "trxHdrNum_L1", "L1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxOrdTs_L1", "xxOrdTs_L1", "L1", null, TYPE_HANKAKUEISU, "17", null},
	{"delyCoordStsDescTxt_L1", "delyCoordStsDescTxt_L1", "L1", null, TYPE_HANKAKUEISU, "50", null},
	{"shipToAcctNm_L1", "shipToAcctNm_L1", "L1", null, TYPE_HANKAKUEISU, "360", null},
	{"shipToAddr_L1", "shipToAddr_L1", "L1", null, TYPE_HANKAKUEISU, "120", null},
	{"shipToCtyAddr_L1", "shipToCtyAddr_L1", "L1", null, TYPE_HANKAKUEISU, "25", null},
	{"shipToStCd_L1", "shipToStCd_L1", "L1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTotCnt_LO", "xxTotCnt_LO", "LO", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxTotCnt_LI", "xxTotCnt_LI", "LI", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxTblSortColNm_LS", "xxTblSortColNm_LS", "LS", null, TYPE_HANKAKUEISU, "30", null},
	{"cutOffAot_LS", "cutOffAot_LS", "LS", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRX_HDR_NUM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNumTpCd_L1
        {"TRX_HDR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_L1
        {"XX_ORD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdTs_L1
        {"DELY_COORD_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyCoordStsDescTxt_L1
        {"SHIP_TO_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToAcctNm_L1
        {"SHIP_TO_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToAddr_L1
        {"SHIP_TO_CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCtyAddr_L1
        {"SHIP_TO_ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToStCd_L1
        {"XX_TOT_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt_LO
        {"XX_TOT_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt_LI
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_LS
        {"CUT_OFF_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cutOffAot_LS
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

