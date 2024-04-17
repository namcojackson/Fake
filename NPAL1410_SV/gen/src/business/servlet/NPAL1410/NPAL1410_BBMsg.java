//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190402141953000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1410_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1410 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1410_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDBStringItem              xxChkBox_B1;

    /** MDSE_CD_B1*/
	public final EZDBStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDBStringItem              mdseDescShortTxt_B1;

    /** PRT_RQST_QTY_B1*/
	public final EZDBBigDecimalItem              prtRqstQty_B1;

    /** THIS_MTH_TOT_STD_COST_AMT_B1*/
	public final EZDBBigDecimalItem              thisMthTotStdCostAmt_B1;

    /** INVTY_AVAL_QTY_B1*/
	public final EZDBBigDecimalItem              invtyAvalQty_B1;

    /** RTRN_REQ_PRT_FLG_B1*/
	public final EZDBStringItem              rtrnReqPrtFlg_B1;

    /** ASSET_RECOV_COST_AMT_B1*/
	public final EZDBBigDecimalItem              assetRecovCostAmt_B1;

    /** PROCR_TP_NM_B1*/
	public final EZDBStringItem              procrTpNm_B1;

    /** PRCH_REQ_NUM_B1*/
	public final EZDBStringItem              prchReqNum_B1;

    /** DPLY_VND_NM_B1*/
	public final EZDBStringItem              dplyVndNm_B1;

    /** XX_FLG_NM_B1*/
	public final EZDBStringItem              xxFlgNm_B1;

    /** RMNF_PRT_QTY_B1*/
	public final EZDBBigDecimalItem              rmnfPrtQty_B1;

    /** RMNF_PRT_RQST_PROC_CD_B1*/
	public final EZDBStringItem              rmnfPrtRqstProcCd_B1;

    /** RMNF_PRT_REQ_PK_B1*/
	public final EZDBBigDecimalItem              rmnfPrtReqPk_B1;

    /** RMNF_PRT_REQ_LINE_NUM_BH*/
	public final EZDBStringItem              rmnfPrtReqLineNum_BH;

    /** RMNF_STD_PRT_FLG_BH*/
	public final EZDBStringItem              rmnfStdPrtFlg_BH;

    /** SRC_RTL_SWH_CD_BH*/
	public final EZDBStringItem              srcRtlSwhCd_BH;

    /** SRC_INVTY_LOC_CD_BH*/
	public final EZDBStringItem              srcInvtyLocCd_BH;


	/**
	 * NPAL1410_BBMsg is constructor.
	 * The initialization when the instance of NPAL1410_BBMsg is generated.
	 */
	public NPAL1410_BBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1410_BBMsg is constructor.
	 * The initialization when the instance of NPAL1410_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1410_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDBStringItem)newItem("xxChkBox_B1");
		mdseCd_B1 = (EZDBStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDBStringItem)newItem("mdseDescShortTxt_B1");
		prtRqstQty_B1 = (EZDBBigDecimalItem)newItem("prtRqstQty_B1");
		thisMthTotStdCostAmt_B1 = (EZDBBigDecimalItem)newItem("thisMthTotStdCostAmt_B1");
		invtyAvalQty_B1 = (EZDBBigDecimalItem)newItem("invtyAvalQty_B1");
		rtrnReqPrtFlg_B1 = (EZDBStringItem)newItem("rtrnReqPrtFlg_B1");
		assetRecovCostAmt_B1 = (EZDBBigDecimalItem)newItem("assetRecovCostAmt_B1");
		procrTpNm_B1 = (EZDBStringItem)newItem("procrTpNm_B1");
		prchReqNum_B1 = (EZDBStringItem)newItem("prchReqNum_B1");
		dplyVndNm_B1 = (EZDBStringItem)newItem("dplyVndNm_B1");
		xxFlgNm_B1 = (EZDBStringItem)newItem("xxFlgNm_B1");
		rmnfPrtQty_B1 = (EZDBBigDecimalItem)newItem("rmnfPrtQty_B1");
		rmnfPrtRqstProcCd_B1 = (EZDBStringItem)newItem("rmnfPrtRqstProcCd_B1");
		rmnfPrtReqPk_B1 = (EZDBBigDecimalItem)newItem("rmnfPrtReqPk_B1");
		rmnfPrtReqLineNum_BH = (EZDBStringItem)newItem("rmnfPrtReqLineNum_BH");
		rmnfStdPrtFlg_BH = (EZDBStringItem)newItem("rmnfStdPrtFlg_BH");
		srcRtlSwhCd_BH = (EZDBStringItem)newItem("srcRtlSwhCd_BH");
		srcInvtyLocCd_BH = (EZDBStringItem)newItem("srcInvtyLocCd_BH");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1410_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1410_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"prtRqstQty_B1", "prtRqstQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"thisMthTotStdCostAmt_B1", "thisMthTotStdCostAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invtyAvalQty_B1", "invtyAvalQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rtrnReqPrtFlg_B1", "rtrnReqPrtFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"assetRecovCostAmt_B1", "assetRecovCostAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"procrTpNm_B1", "procrTpNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"prchReqNum_B1", "prchReqNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyVndNm_B1", "dplyVndNm_B1", "B1", null, TYPE_HANKAKUEISU, "320", null},
	{"xxFlgNm_B1", "xxFlgNm_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"rmnfPrtQty_B1", "rmnfPrtQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"rmnfPrtRqstProcCd_B1", "rmnfPrtRqstProcCd_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"rmnfPrtReqPk_B1", "rmnfPrtReqPk_B1", "B1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rmnfPrtReqLineNum_BH", "rmnfPrtReqLineNum_BH", "BH", null, TYPE_HANKAKUEISU, "3", null},
	{"rmnfStdPrtFlg_BH", "rmnfStdPrtFlg_BH", "BH", null, TYPE_HANKAKUEISU, "1", null},
	{"srcRtlSwhCd_BH", "srcRtlSwhCd_BH", "BH", null, TYPE_HANKAKUEISU, "20", null},
	{"srcInvtyLocCd_BH", "srcInvtyLocCd_BH", "BH", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"PRT_RQST_QTY", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prtRqstQty_B1
        {"THIS_MTH_TOT_STD_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thisMthTotStdCostAmt_B1
        {"INVTY_AVAL_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty_B1
        {"RTRN_REQ_PRT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrnReqPrtFlg_B1
        {"ASSET_RECOV_COST_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetRecovCostAmt_B1
        {"PROCR_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procrTpNm_B1
        {"PRCH_REQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_B1
        {"DPLY_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_B1
        {"XX_FLG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_B1
        {"RMNF_PRT_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfPrtQty_B1
        {"RMNF_PRT_RQST_PROC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfPrtRqstProcCd_B1
        {"RMNF_PRT_REQ_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfPrtReqPk_B1
        {"RMNF_PRT_REQ_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfPrtReqLineNum_BH
        {"RMNF_STD_PRT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfStdPrtFlg_BH
        {"SRC_RTL_SWH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd_BH
        {"SRC_INVTY_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcInvtyLocCd_BH
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

