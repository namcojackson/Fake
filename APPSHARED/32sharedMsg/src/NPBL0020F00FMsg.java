//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20171226101041000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPBL0020F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPBL0020F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NPBL0020F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_A1*/
	public final EZDFStringItem              mdseCd_A1;

    /** PRCH_REQ_DISP_QTY_A1*/
	public final EZDFBigDecimalItem              prchReqDispQty_A1;

    /** SRC_RTL_WH_CD_A1*/
	public final EZDFStringItem              srcRtlWhCd_A1;

    /** SRC_RTL_SWH_CD_A1*/
	public final EZDFStringItem              srcRtlSwhCd_A1;

    /** STK_STS_CD_A1*/
	public final EZDFStringItem              stkStsCd_A1;

    /** DEST_RTL_WH_CD_A1*/
	public final EZDFStringItem              destRtlWhCd_A1;

    /** DEST_RTL_SWH_CD_A1*/
	public final EZDFStringItem              destRtlSwhCd_A1;

    /** SHIP_TO_CUST_CD_A1*/
	public final EZDFStringItem              shipToCustCd_A1;

    /** SHIP_TO_LOC_NM_A1*/
	public final EZDFStringItem              shipToLocNm_A1;

    /** XX_SCR_ITEM_50_TXT_A1*/
	public final EZDFStringItem              xxScrItem50Txt_A1;

    /** PRCH_REQ_LINE_CMNT_TXT_A1*/
	public final EZDFStringItem              prchReqLineCmntTxt_A1;


	/**
	 * NPBL0020F00FMsg is constructor.
	 * The initialization when the instance of NPBL0020F00FMsg is generated.
	 */
	public NPBL0020F00FMsg() {
		this(false, -1);
	}

	/**
	 * NPBL0020F00FMsg is constructor.
	 * The initialization when the instance of NPBL0020F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPBL0020F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_A1 = (EZDFStringItem)newItem("mdseCd_A1");
		prchReqDispQty_A1 = (EZDFBigDecimalItem)newItem("prchReqDispQty_A1");
		srcRtlWhCd_A1 = (EZDFStringItem)newItem("srcRtlWhCd_A1");
		srcRtlSwhCd_A1 = (EZDFStringItem)newItem("srcRtlSwhCd_A1");
		stkStsCd_A1 = (EZDFStringItem)newItem("stkStsCd_A1");
		destRtlWhCd_A1 = (EZDFStringItem)newItem("destRtlWhCd_A1");
		destRtlSwhCd_A1 = (EZDFStringItem)newItem("destRtlSwhCd_A1");
		shipToCustCd_A1 = (EZDFStringItem)newItem("shipToCustCd_A1");
		shipToLocNm_A1 = (EZDFStringItem)newItem("shipToLocNm_A1");
		xxScrItem50Txt_A1 = (EZDFStringItem)newItem("xxScrItem50Txt_A1");
		prchReqLineCmntTxt_A1 = (EZDFStringItem)newItem("prchReqLineCmntTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPBL0020F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPBL0020F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"prchReqDispQty_A1", "prchReqDispQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"srcRtlWhCd_A1", "srcRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"srcRtlSwhCd_A1", "srcRtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"stkStsCd_A1", "stkStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"destRtlWhCd_A1", "destRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"destRtlSwhCd_A1", "destRtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_A1", "shipToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToLocNm_A1", "shipToLocNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxScrItem50Txt_A1", "xxScrItem50Txt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"prchReqLineCmntTxt_A1", "prchReqLineCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "240", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"PRCH_REQ_DISP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqDispQty_A1
        {"SRC_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlWhCd_A1
        {"SRC_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srcRtlSwhCd_A1
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A1
        {"DEST_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlWhCd_A1
        {"DEST_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlSwhCd_A1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A1
        {"SHIP_TO_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_A1
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_A1
        {"PRCH_REQ_LINE_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineCmntTxt_A1
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

