//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240325104816000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1440BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1440;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1440 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1440BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRCH_REQ_NUM*/
	public final EZDBStringItem              prchReqNum;

    /** PRCH_REQ_LINE_NUM*/
	public final EZDBStringItem              prchReqLineNum;

    /** PRCH_REQ_LINE_SUB_NUM*/
	public final EZDBBigDecimalItem              prchReqLineSubNum;

    /** PRCH_REQ_NUM_H*/
	public final EZDBStringItem              prchReqNum_H;

    /** PRCH_REQ_TP_DESC_TXT_H*/
	public final EZDBStringItem              prchReqTpDescTxt_H;

    /** PRCH_REQ_CRAT_DT_H*/
	public final EZDBDateItem              prchReqCratDt_H;

    /** PRCH_REQ_STS_DESC_TXT_H*/
	public final EZDBStringItem              prchReqStsDescTxt_H;

    /** FULL_PSN_NM_H*/
	public final EZDBStringItem              fullPsnNm_H;

    /** A*/
	public final business.servlet.NPAL1440.NPAL1440_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;


	/**
	 * NPAL1440BMsg is constructor.
	 * The initialization when the instance of NPAL1440BMsg is generated.
	 */
	public NPAL1440BMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1440BMsg is constructor.
	 * The initialization when the instance of NPAL1440BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1440BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prchReqNum = (EZDBStringItem)newItem("prchReqNum");
		prchReqLineNum = (EZDBStringItem)newItem("prchReqLineNum");
		prchReqLineSubNum = (EZDBBigDecimalItem)newItem("prchReqLineSubNum");
		prchReqNum_H = (EZDBStringItem)newItem("prchReqNum_H");
		prchReqTpDescTxt_H = (EZDBStringItem)newItem("prchReqTpDescTxt_H");
		prchReqCratDt_H = (EZDBDateItem)newItem("prchReqCratDt_H");
		prchReqStsDescTxt_H = (EZDBStringItem)newItem("prchReqStsDescTxt_H");
		fullPsnNm_H = (EZDBStringItem)newItem("fullPsnNm_H");
		A = (business.servlet.NPAL1440.NPAL1440_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1440BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1440BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prchReqNum", "prchReqNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"prchReqLineNum", "prchReqLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"prchReqLineSubNum", "prchReqLineSubNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prchReqNum_H", "prchReqNum_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"prchReqTpDescTxt_H", "prchReqTpDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"prchReqCratDt_H", "prchReqCratDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"prchReqStsDescTxt_H", "prchReqStsDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"fullPsnNm_H", "fullPsnNm_H", "H", null, TYPE_HANKAKUEISU, "62", null},
	{"A", "A", null, "200", "business.servlet.NPAL1440.NPAL1440_ABMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRCH_REQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum
        {"PRCH_REQ_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineNum
        {"PRCH_REQ_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqLineSubNum
        {"PRCH_REQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_H
        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_H
        {"PRCH_REQ_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//prchReqCratDt_H
        {"PRCH_REQ_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqStsDescTxt_H
        {"FULL_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_H
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
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
