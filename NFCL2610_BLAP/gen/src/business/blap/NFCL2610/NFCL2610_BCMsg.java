//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220727142342000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2610_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2610 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2610_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRNT_VND_CD*/
	public final EZDCStringItem              prntVndCd;

    /** PRNT_VND_PK*/
	public final EZDCBigDecimalItem              prntVndPk;

    /** PRNT_VND_NM*/
	public final EZDCStringItem              prntVndNm;

    /** XX_SCR_ITEM_61_TXT*/
	public final EZDCStringItem              xxScrItem61Txt;

    /** XX_CHK_BOX_4*/
	public final EZDCStringItem              xxChkBox_4;

    /** VND_CD*/
	public final EZDCStringItem              vndCd;

    /** XX_LOC_NM_P*/
	public final EZDCStringItem              xxLocNm_P;

    /** VND_TP_DESC_TXT*/
	public final EZDCStringItem              vndTpDescTxt;

    /** FIRST_LINE_ADDR*/
	public final EZDCStringItem              firstLineAddr;

    /** SCD_LINE_ADDR*/
	public final EZDCStringItem              scdLineAddr;

    /** RTL_CTR_CTY_ADDR*/
	public final EZDCStringItem              rtlCtrCtyAddr;

    /** ST_CD*/
	public final EZDCStringItem              stCd;

    /** POST_CD*/
	public final EZDCStringItem              postCd;

    /** XX_TO_DT*/
	public final EZDCDateItem              xxToDt;


	/**
	 * NFCL2610_BCMsg is constructor.
	 * The initialization when the instance of NFCL2610_BCMsg is generated.
	 */
	public NFCL2610_BCMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2610_BCMsg is constructor.
	 * The initialization when the instance of NFCL2610_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2610_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prntVndCd = (EZDCStringItem)newItem("prntVndCd");
		prntVndPk = (EZDCBigDecimalItem)newItem("prntVndPk");
		prntVndNm = (EZDCStringItem)newItem("prntVndNm");
		xxScrItem61Txt = (EZDCStringItem)newItem("xxScrItem61Txt");
		xxChkBox_4 = (EZDCStringItem)newItem("xxChkBox_4");
		vndCd = (EZDCStringItem)newItem("vndCd");
		xxLocNm_P = (EZDCStringItem)newItem("xxLocNm_P");
		vndTpDescTxt = (EZDCStringItem)newItem("vndTpDescTxt");
		firstLineAddr = (EZDCStringItem)newItem("firstLineAddr");
		scdLineAddr = (EZDCStringItem)newItem("scdLineAddr");
		rtlCtrCtyAddr = (EZDCStringItem)newItem("rtlCtrCtyAddr");
		stCd = (EZDCStringItem)newItem("stCd");
		postCd = (EZDCStringItem)newItem("postCd");
		xxToDt = (EZDCDateItem)newItem("xxToDt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2610_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2610_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prntVndCd", "prntVndCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prntVndPk", "prntVndPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prntVndNm", "prntVndNm", null, null, TYPE_HANKAKUEISU, "240", null},
	{"xxScrItem61Txt", "xxScrItem61Txt", null, null, TYPE_HANKAKUEISU, "61", null},
	{"xxChkBox_4", "xxChkBox_4", "4", null, TYPE_HANKAKUEIJI, "1", null},
	{"vndCd", "vndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxLocNm_P", "xxLocNm_P", "P", null, TYPE_HANKAKUEISU, "60", null},
	{"vndTpDescTxt", "vndTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"firstLineAddr", "firstLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"scdLineAddr", "scdLineAddr", null, null, TYPE_HANKAKUEISU, "60", null},
	{"rtlCtrCtyAddr", "rtlCtrCtyAddr", null, null, TYPE_HANKAKUEISU, "25", null},
	{"stCd", "stCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"postCd", "postCd", null, null, TYPE_HANKAKUEISU, "15", null},
	{"xxToDt", "xxToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd
        {"PRNT_VND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndPk
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_4
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd
        {"XX_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocNm_P
        {"VND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndTpDescTxt
        {"FIRST_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstLineAddr
        {"SCD_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdLineAddr
        {"RTL_CTR_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlCtrCtyAddr
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt
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
