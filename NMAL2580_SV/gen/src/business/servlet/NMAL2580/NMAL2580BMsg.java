//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20171208180454000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2580BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2580;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2580 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2580BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRTY_UPD_RQST_HDR_PK*/
	public final EZDBBigDecimalItem              trtyUpdRqstHdrPk;

    /** RQST_USR_ID*/
	public final EZDBStringItem              rqstUsrId;

    /** FILL_103_TXT*/
	public final EZDBStringItem              fill103Txt;

    /** RQST_CRAT_SYS_TP_CD*/
	public final EZDBStringItem              rqstCratSysTpCd;

    /** RQST_CRAT_SYS_TP_CD_PC*/
	public final EZDBStringItemArray              rqstCratSysTpCd_PC;

    /** RQST_CRAT_SYS_TP_DESC_TXT_PC*/
	public final EZDBStringItemArray              rqstCratSysTpDescTxt_PC;

    /** RQST_RSLT_TP_CD*/
	public final EZDBStringItem              rqstRsltTpCd;

    /** RQST_RSLT_TP_CD_PC*/
	public final EZDBStringItemArray              rqstRsltTpCd_PC;

    /** RQST_RSLT_TP_DESC_TXT_PC*/
	public final EZDBStringItemArray              rqstRsltTpDescTxt_PC;

    /** RQST_RSLT_CMNT_TXT*/
	public final EZDBStringItem              rqstRsltCmntTxt;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** EFF_TO_DT*/
	public final EZDBDateItem              effToDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** _EZInAplID*/
	public final EZDBStringItem              ezInAplID;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** TRTY_UPD_RQST_HDR_PK_DL*/
	public final EZDBBigDecimalItem              trtyUpdRqstHdrPk_DL;

    /** A*/
	public final business.servlet.NMAL2580.NMAL2580_ABMsgArray              A;


	/**
	 * NMAL2580BMsg is constructor.
	 * The initialization when the instance of NMAL2580BMsg is generated.
	 */
	public NMAL2580BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2580BMsg is constructor.
	 * The initialization when the instance of NMAL2580BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2580BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trtyUpdRqstHdrPk = (EZDBBigDecimalItem)newItem("trtyUpdRqstHdrPk");
		rqstUsrId = (EZDBStringItem)newItem("rqstUsrId");
		fill103Txt = (EZDBStringItem)newItem("fill103Txt");
		rqstCratSysTpCd = (EZDBStringItem)newItem("rqstCratSysTpCd");
		rqstCratSysTpCd_PC = (EZDBStringItemArray)newItemArray("rqstCratSysTpCd_PC");
		rqstCratSysTpDescTxt_PC = (EZDBStringItemArray)newItemArray("rqstCratSysTpDescTxt_PC");
		rqstRsltTpCd = (EZDBStringItem)newItem("rqstRsltTpCd");
		rqstRsltTpCd_PC = (EZDBStringItemArray)newItemArray("rqstRsltTpCd_PC");
		rqstRsltTpDescTxt_PC = (EZDBStringItemArray)newItemArray("rqstRsltTpDescTxt_PC");
		rqstRsltCmntTxt = (EZDBStringItem)newItem("rqstRsltCmntTxt");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		effToDt = (EZDBDateItem)newItem("effToDt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		ezInAplID = (EZDBStringItem)newItem("ezInAplID");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		trtyUpdRqstHdrPk_DL = (EZDBBigDecimalItem)newItem("trtyUpdRqstHdrPk_DL");
		A = (business.servlet.NMAL2580.NMAL2580_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2580BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2580BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trtyUpdRqstHdrPk", "trtyUpdRqstHdrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"rqstUsrId", "rqstUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"fill103Txt", "fill103Txt", null, null, TYPE_HANKAKUEISU, "103", null},
	{"rqstCratSysTpCd", "rqstCratSysTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rqstCratSysTpCd_PC", "rqstCratSysTpCd_PC", "PC", "99", TYPE_HANKAKUEISU, "2", null},
	{"rqstCratSysTpDescTxt_PC", "rqstCratSysTpDescTxt_PC", "PC", "99", TYPE_HANKAKUEISU, "50", null},
	{"rqstRsltTpCd", "rqstRsltTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rqstRsltTpCd_PC", "rqstRsltTpCd_PC", "PC", "99", TYPE_HANKAKUEISU, "2", null},
	{"rqstRsltTpDescTxt_PC", "rqstRsltTpDescTxt_PC", "PC", "99", TYPE_HANKAKUEISU, "50", null},
	{"rqstRsltCmntTxt", "rqstRsltCmntTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt", "effToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ezInAplID", "ezInAplID", null, null, TYPE_HANKAKUEISU, "64", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"trtyUpdRqstHdrPk_DL", "trtyUpdRqstHdrPk_DL", "DL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"A", "A", null, "100", "business.servlet.NMAL2580.NMAL2580_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRTY_UPD_RQST_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyUpdRqstHdrPk
        {"RQST_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstUsrId
        {"FILL_103_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fill103Txt
        {"RQST_CRAT_SYS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstCratSysTpCd
        {"RQST_CRAT_SYS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstCratSysTpCd_PC
        {"RQST_CRAT_SYS_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstCratSysTpDescTxt_PC
        {"RQST_RSLT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRsltTpCd
        {"RQST_RSLT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRsltTpCd_PC
        {"RQST_RSLT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRsltTpDescTxt_PC
        {"RQST_RSLT_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRsltCmntTxt
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"EFF_TO_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effToDt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"_EZInAplID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezInAplID
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"TRTY_UPD_RQST_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyUpdRqstHdrPk_DL
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

