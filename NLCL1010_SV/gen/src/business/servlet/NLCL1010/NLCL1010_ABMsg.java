//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170531162243000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1010_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL1010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL1010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL1010_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** SER_NUM_A1*/
	public final EZDBStringItem              serNum_A1;

    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** SER_TRX_EVENT_DESC_TXT_A1*/
	public final EZDBStringItem              serTrxEventDescTxt_A1;

    /** XX_DT_NM_A1*/
	public final EZDBStringItem              xxDtNm_A1;

    /** MAN_CRAT_FLG_A1*/
	public final EZDBStringItem              manCratFlg_A1;

    /** XX_SCR_ITEM_500_TXT_A1*/
	public final EZDBStringItem              xxScrItem500Txt_A1;

    /** XX_SCR_ITEM_500_TXT_A2*/
	public final EZDBStringItem              xxScrItem500Txt_A2;

    /** SER_TRX_SRC_TP_DESC_TXT_A1*/
	public final EZDBStringItem              serTrxSrcTpDescTxt_A1;

    /** SER_TRX_SRC_HDR_NUM_A1*/
	public final EZDBStringItem              serTrxSrcHdrNum_A1;

    /** SER_TRX_SRC_LINE_NUM_A1*/
	public final EZDBStringItem              serTrxSrcLineNum_A1;

    /** SER_TRX_SRC_LINE_SUB_NUM_A1*/
	public final EZDBStringItem              serTrxSrcLineSubNum_A1;

    /** SER_TRX_REF_NUM_A1*/
	public final EZDBStringItem              serTrxRefNum_A1;

    /** STK_STS_DESC_TXT_A1*/
	public final EZDBStringItem              stkStsDescTxt_A1;

    /** STK_STS_DESC_TXT_A2*/
	public final EZDBStringItem              stkStsDescTxt_A2;

    /** ORIG_MDSE_CD_A1*/
	public final EZDBStringItem              origMdseCd_A1;

    /** XX_LINK_ANCR_A1*/
	public final EZDBStringItem              xxLinkAncr_A1;

    /** XX_YES_NO_CD_A1*/
	public final EZDBStringItem              xxYesNoCd_A1;

    /** XX_DPLY_BY_ITEM_CNCT_LB_NM_A3*/
	public final EZDBStringItem              xxDplyByItemCnctLbNm_A3;

    /** SER_TRX_PK_A1*/
	public final EZDBBigDecimalItem              serTrxPk_A1;

    /** SER_TRX_TS_A1*/
	public final EZDBStringItem              serTrxTs_A1;

    /** SER_TRX_EVENT_CD_A1*/
	public final EZDBStringItem              serTrxEventCd_A1;

    /** SER_TRX_SRC_TP_CD_A1*/
	public final EZDBStringItem              serTrxSrcTpCd_A1;

    /** FROM_LOC_CD_A1*/
	public final EZDBStringItem              fromLocCd_A1;

    /** TO_LOC_CD_A1*/
	public final EZDBStringItem              toLocCd_A1;

    /** OLD_MDSE_CD_A1*/
	public final EZDBStringItem              oldMdseCd_A1;

    /** SER_TRX_CMNT_TXT_A1*/
	public final EZDBStringItem              serTrxCmntTxt_A1;

    /** SER_ERR_STS_CD_A1*/
	public final EZDBStringItem              serErrStsCd_A1;

    /** STK_STS_CD_A1*/
	public final EZDBStringItem              stkStsCd_A1;

    /** STK_STS_CD_A2*/
	public final EZDBStringItem              stkStsCd_A2;

    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;

    /** SER_TRX_CMNT_TXT_AS*/
	public final EZDBStringItem              serTrxCmntTxt_AS;


	/**
	 * NLCL1010_ABMsg is constructor.
	 * The initialization when the instance of NLCL1010_ABMsg is generated.
	 */
	public NLCL1010_ABMsg() {
		this(false, -1);
	}

	/**
	 * NLCL1010_ABMsg is constructor.
	 * The initialization when the instance of NLCL1010_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL1010_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		serNum_A1 = (EZDBStringItem)newItem("serNum_A1");
		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		serTrxEventDescTxt_A1 = (EZDBStringItem)newItem("serTrxEventDescTxt_A1");
		xxDtNm_A1 = (EZDBStringItem)newItem("xxDtNm_A1");
		manCratFlg_A1 = (EZDBStringItem)newItem("manCratFlg_A1");
		xxScrItem500Txt_A1 = (EZDBStringItem)newItem("xxScrItem500Txt_A1");
		xxScrItem500Txt_A2 = (EZDBStringItem)newItem("xxScrItem500Txt_A2");
		serTrxSrcTpDescTxt_A1 = (EZDBStringItem)newItem("serTrxSrcTpDescTxt_A1");
		serTrxSrcHdrNum_A1 = (EZDBStringItem)newItem("serTrxSrcHdrNum_A1");
		serTrxSrcLineNum_A1 = (EZDBStringItem)newItem("serTrxSrcLineNum_A1");
		serTrxSrcLineSubNum_A1 = (EZDBStringItem)newItem("serTrxSrcLineSubNum_A1");
		serTrxRefNum_A1 = (EZDBStringItem)newItem("serTrxRefNum_A1");
		stkStsDescTxt_A1 = (EZDBStringItem)newItem("stkStsDescTxt_A1");
		stkStsDescTxt_A2 = (EZDBStringItem)newItem("stkStsDescTxt_A2");
		origMdseCd_A1 = (EZDBStringItem)newItem("origMdseCd_A1");
		xxLinkAncr_A1 = (EZDBStringItem)newItem("xxLinkAncr_A1");
		xxYesNoCd_A1 = (EZDBStringItem)newItem("xxYesNoCd_A1");
		xxDplyByItemCnctLbNm_A3 = (EZDBStringItem)newItem("xxDplyByItemCnctLbNm_A3");
		serTrxPk_A1 = (EZDBBigDecimalItem)newItem("serTrxPk_A1");
		serTrxTs_A1 = (EZDBStringItem)newItem("serTrxTs_A1");
		serTrxEventCd_A1 = (EZDBStringItem)newItem("serTrxEventCd_A1");
		serTrxSrcTpCd_A1 = (EZDBStringItem)newItem("serTrxSrcTpCd_A1");
		fromLocCd_A1 = (EZDBStringItem)newItem("fromLocCd_A1");
		toLocCd_A1 = (EZDBStringItem)newItem("toLocCd_A1");
		oldMdseCd_A1 = (EZDBStringItem)newItem("oldMdseCd_A1");
		serTrxCmntTxt_A1 = (EZDBStringItem)newItem("serTrxCmntTxt_A1");
		serErrStsCd_A1 = (EZDBStringItem)newItem("serErrStsCd_A1");
		stkStsCd_A1 = (EZDBStringItem)newItem("stkStsCd_A1");
		stkStsCd_A2 = (EZDBStringItem)newItem("stkStsCd_A2");
		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
		serTrxCmntTxt_AS = (EZDBStringItem)newItem("serTrxCmntTxt_AS");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL1010_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL1010_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"serTrxEventDescTxt_A1", "serTrxEventDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtNm_A1", "xxDtNm_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"manCratFlg_A1", "manCratFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrItem500Txt_A1", "xxScrItem500Txt_A1", "A1", null, TYPE_HANKAKUEISU, "500", null},
	{"xxScrItem500Txt_A2", "xxScrItem500Txt_A2", "A2", null, TYPE_HANKAKUEISU, "500", null},
	{"serTrxSrcTpDescTxt_A1", "serTrxSrcTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"serTrxSrcHdrNum_A1", "serTrxSrcHdrNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"serTrxSrcLineNum_A1", "serTrxSrcLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"serTrxSrcLineSubNum_A1", "serTrxSrcLineSubNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"serTrxRefNum_A1", "serTrxRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"stkStsDescTxt_A1", "stkStsDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"stkStsDescTxt_A2", "stkStsDescTxt_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"origMdseCd_A1", "origMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"xxLinkAncr_A1", "xxLinkAncr_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxYesNoCd_A1", "xxYesNoCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyByItemCnctLbNm_A3", "xxDplyByItemCnctLbNm_A3", "A3", null, TYPE_HANKAKUEISU, "150", null},
	{"serTrxPk_A1", "serTrxPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serTrxTs_A1", "serTrxTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"serTrxEventCd_A1", "serTrxEventCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"serTrxSrcTpCd_A1", "serTrxSrcTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"fromLocCd_A1", "fromLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"toLocCd_A1", "toLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"oldMdseCd_A1", "oldMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"serTrxCmntTxt_A1", "serTrxCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "1000", null},
	{"serErrStsCd_A1", "serErrStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"stkStsCd_A1", "stkStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"stkStsCd_A2", "stkStsCd_A2", "A2", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"serTrxCmntTxt_AS", "serTrxCmntTxt_AS", "AS", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"SER_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"SER_TRX_EVENT_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,"25",null,null, null,  NO,  NO},	//serTrxEventDescTxt_A1
        {"XX_DT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtNm_A1
        {"MAN_CRAT_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manCratFlg_A1
        {"XX_SCR_ITEM_500_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem500Txt_A1
        {"XX_SCR_ITEM_500_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem500Txt_A2
        {"SER_TRX_SRC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcTpDescTxt_A1
        {"SER_TRX_SRC_HDR_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcHdrNum_A1
        {"SER_TRX_SRC_LINE_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcLineNum_A1
        {"SER_TRX_SRC_LINE_SUB_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcLineSubNum_A1
        {"SER_TRX_REF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxRefNum_A1
        {"STK_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt_A1
        {"STK_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt_A2
        {"ORIG_MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origMdseCd_A1
        {"XX_LINK_ANCR",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkAncr_A1
        {"XX_YES_NO_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_A1
        {"XX_DPLY_BY_ITEM_CNCT_LB_NM",  NO,  null,null,"0", NO, NO, NO, NO,"45",null,null, null,  NO,  NO},	//xxDplyByItemCnctLbNm_A3
        {"SER_TRX_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxPk_A1
        {"SER_TRX_TS",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxTs_A1
        {"SER_TRX_EVENT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxEventCd_A1
        {"SER_TRX_SRC_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxSrcTpCd_A1
        {"FROM_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromLocCd_A1
        {"TO_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//toLocCd_A1
        {"OLD_MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_A1
        {"SER_TRX_CMNT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxCmntTxt_A1
        {"SER_ERR_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serErrStsCd_A1
        {"STK_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A1
        {"STK_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A2
        {"_EZUpdateDateTime",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"SER_TRX_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serTrxCmntTxt_AS
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

